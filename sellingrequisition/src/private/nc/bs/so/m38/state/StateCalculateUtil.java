package nc.bs.so.m38.state;

import java.util.HashMap;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.env.BSContext;
import nc.itf.so.m38trantype.IM38TranTypeService;
import nc.pubitf.so.m38.so.m30.Rewrite30Para;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m38.entity.PreOrderBVO;
import nc.vo.so.m38.entity.PreOrderVO;
import nc.vo.so.m38.entity.PreOrderViewVO;
import nc.vo.so.m38trantype.entity.M38TranTypeVO;
import nc.vo.so.pub.enumeration.BillStatus;

/**
 * 预订单状态跃迁判断工具
 * 
 * @author 刘志伟
 * @since 6.0
 * @time 2010-04-08 上午09:27:07
 */
public class StateCalculateUtil {

  Map<String, UFBoolean> maparronce = new HashMap<String, UFBoolean>();

  private Map<String, Rewrite30Para> mParas;

  /**
   * 方法功能描述：根据行状态判断是否可以整单关闭。
   * <p>
   * <ul>
   * <li>所有行关闭，则整单关闭(表头转关闭状态) ：return true
   * <li>有一行打开，则整单打开(表头转审批状态) ：return false
   * </ul>
   * 
   * @param vo 待计算的单据VO
   * @return 是否自动关闭
   *         <p>
   * @author 刘志伟
   * @time 2010-04-09 下午02:43:18
   */
  public boolean isAutoTransitBillClose(PreOrderVO vo) {
    PreOrderBVO[] items = vo.getChildrenVO();
    for (PreOrderBVO item : items) {
      UFBoolean blineclose = item.getBlineclose();
      if (!UFBoolean.TRUE.equals(blineclose)) {
        return false;
      }
    }
    return true;
  }

  /**
   * 根据行状态判断是否可以整单打开
   * 
   * @since 6.0
   * @version 2010-12-14 上午09:02:17
   * @author 刘志伟
   */
  public boolean isAutoTransitBillOpen(PreOrderVO vo) {
    return !this.isAutoTransitBillClose(vo);
  }

  /**
   * 整单关闭导致行直接关闭
   * 
   * @param views
   * @return
   */
  public boolean isAutoTransitRowClose(PreOrderViewVO view) {
    return BillStatus.CLOSED.equalsValue(view.getHead().getFstatusflag());
  }

  /**
   * 整单打开导致行直接打开
   * 
   * @param views
   * @return
   */
  public boolean isAutoTransitRowOpen(PreOrderViewVO view) {
    return BillStatus.AUDIT.equalsValue(view.getHead().getFstatusflag());
  }

  /**
   * 回写正数时：当前累计安排数量 >= 主数量 行可以自动关闭 。
   * <p>特例:如果当前回写正数而上游已经手工关闭且当前累计数量>=主数量时，处理为手工关闭状态自动改为自动关闭状态(业务默认为转换，此不用写代码控制)，
   * 所以下次回写负数时需要自动打开的。
   * 
   * @param views
   * @return 能否自动关闭
   */
  public boolean isRowClose(PreOrderViewVO view) {
    // 只安排一次时，直接返回
    if (this.isArrangeOnce(view).booleanValue()) {
      return true;
    }
    PreOrderBVO item = view.getItem();
    Rewrite30Para para = this.getRewrite30Paras().get(item.getCpreorderbid());
    // 回写负数永远不关闭
    if (MathTool.compareTo(para.getNnum(), UFDouble.ZERO_DBL) < 0) {
      return false;
    }

    // 回写正数时：(当前累计安排数量 >= 主数量) 行可以自动关闭 。
    return MathTool.compareTo(item.getNarrnum(), item.getNnum()) >= 0;
  }

  /**
   * 回写负数时:自动关闭 && (当前累计安排数量 < 主数量) 行可以自动打开
   * 
   * @param views PreOrderViewVO
   * @return boolean 能否自动打开
   */
  public boolean isRowOpen(PreOrderViewVO view) {
    PreOrderBVO item = view.getItem();
    Rewrite30Para para = this.getRewrite30Paras().get(item.getCpreorderbid());
    // 非关闭单据不能打开 || 回写正数(包含0)永远不打开
    if (!item.getBlineclose().booleanValue()
        || MathTool.compareTo(para.getNnum(), UFDouble.ZERO_DBL) >= 0) {
      return false;
    }

    // 源累计安排数量 = 当前累计安排数量 - 当前安排数量
    UFDouble origArrnum = MathTool.sub(item.getNarrnum(), para.getNnum());
    // 是否自动关闭 (源累计安排数量 >= 主数量)
    boolean bautoClose =
        MathTool.compareTo(origArrnum, item.getNnum()) >= 0 ? true : false;

    // 回写负数时:自动关闭 && (当前累计安排数量 < 主数量) 行可以自动打开
    return bautoClose
        && MathTool.compareTo(item.getNarrnum(), item.getNnum()) < 0;
  }

  @SuppressWarnings("unchecked")
  private Map<String, Rewrite30Para> getRewrite30Paras() {
    if (this.mParas == null) {
      this.mParas =
          (Map<String, Rewrite30Para>) BSContext.getInstance().getSession(
              Rewrite30Para.class.getName());
    }
    return this.mParas;
  }

  private UFBoolean isArrangeOnce(PreOrderViewVO preview) {

    String ctrantypeid = preview.getHead().getCtrantypeid();
    if (this.maparronce.containsKey(ctrantypeid)) {
      return this.maparronce.get(ctrantypeid);
    }

    IM38TranTypeService transrv =
        NCLocator.getInstance().lookup(IM38TranTypeService.class);
    M38TranTypeVO tranvo = null;
    try {
      tranvo = transrv.queryTranTypeVO(ctrantypeid);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    UFBoolean arronce = UFBoolean.FALSE;
    if (null != tranvo && null != tranvo.getBarrange()) {
      arronce = tranvo.getBarrange();
    }
    this.maparronce.put(ctrantypeid, arronce);
    return arronce;

  }

}
