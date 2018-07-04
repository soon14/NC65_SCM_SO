package nc.bs.so.m30.state.row;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nc.bs.so.m30.plugin.StatePlugInPoint;
import nc.bs.so.m30.rule.atp.SaleOrderViewATPAfterRule;
import nc.bs.so.m30.rule.atp.SaleOrderViewATPBeforeRule;
import nc.bs.so.m30.rule.credit.RenovateARByBidsBeginRule;
import nc.bs.so.m30.rule.credit.RenovateARByBidsEndRule;
import nc.bs.so.m30.rule.rewrite.m35.Rewrite35WhenOutOpen;
import nc.bs.so.m30.rule.rewrite.me.RewriteME35WhenOutOpen;
import nc.bs.so.m30.rule.rewrite.opc.RewriteOPCWhenOutOpen;
import nc.bs.so.m30.rule.rewrite.price.RewriteProPirceWhenOutOpen;
import nc.bs.so.m30.state.StateCalculateUtil;
import nc.impl.pubapp.bill.state.AbstractRowState;
import nc.impl.pubapp.bill.state.IState;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.itf.so.m33.ref.so.m30.SOSaleOrderServicesUtil;
import nc.pubitf.so.m30.balend.BalOpenPara;
import nc.vo.bd.material.MaterialVO;
import nc.vo.credit.engrossmaintain.pub.action.M30EngrossAction;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.m30.balend.enumeration.BalOpenTrigger;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderViewVO;
import nc.vo.so.pub.enumeration.BillStatus;
import nc.vo.so.pub.util.SOSysInitGroupQuery;

/**
 * 销售订单出库打开
 * 
 * @author 刘志伟
 * @time 2010-01-28 下午13:49:07
 */
public class OutOpenState extends AbstractRowState<SaleOrderViewVO> {

  private StateCalculateUtil stateCalculateUtil;

  public OutOpenState() {
    super(SaleOrderBVO.class, SaleOrderBVO.BBOUTENDFLAG, UFBoolean.FALSE);
  }

  @Override
  public boolean isAutoTransitable(SaleOrderViewVO vo) {
    if (this.isThisState(vo) || !this.isPrevStateValid(vo)) {
      return false;
    }

    return this.getStateCalculateUtil().isAutoTransitOutOpen(vo);
  }

  /**
   * 订单行是否满足手工出库打开的条件
   * 
   * @param view
   * @return
   */
  public boolean isManualOutOpen(SaleOrderViewVO view) {
    return this.getStateCalculateUtil().isManualOutOpen(view);
  }

  /**
   * 是否可以出库打开4出库单
   * 
   * @param vo
   * @return boolean
   */
  public boolean isOutOpen(SaleOrderViewVO vo,
      Map<String, MaterialVO> materrialmaps) {
    if (this.isThisState(vo) || !this.isPrevStateValid(vo)) {
      return false;
    }
    return this.getStateCalculateUtil().isOutOpen(vo, materrialmaps);
  }

  /**
   * 是否可以出库打开4途损单
   * 
   * @param vo
   * @return boolean
   */
  public boolean isOutOpenFor4453(SaleOrderViewVO vo,
      Map<String, MaterialVO> materrialmaps) {
    if (this.isThisState(vo) || !this.isPrevStateValid(vo)) {
      return false;
    }
    return this.getStateCalculateUtil().isOutOpenFor4453(vo, materrialmaps);
  }

  /**
   * 是否可以出库打开4修订
   * 
   * @param vo
   * @return boolean
   */
  public boolean isOutOpenForRevise(SaleOrderViewVO vo,
      SaleOrderViewVO originVo, Map<String, MaterialVO> materrialmaps) {
    if (this.isThisState(vo) || !this.isPrevStateValid(vo)) {
      return false;
    }
    return this.getStateCalculateUtil().isOutOpenForRevise(vo, originVo,
        materrialmaps);
  }

  @Override
  public boolean isPrevStateValid(SaleOrderViewVO view) {
    // 只有审批态、自由态、整单关闭态可以打开
    Integer status = view.getHead().getFstatusflag();
    boolean flag =
        BillStatus.AUDIT.equalsValue(status)
        || BillStatus.FREE.equalsValue(status)
        || BillStatus.AUDITING.equalsValue(status)
        || BillStatus.CLOSED.equalsValue(status);
    return flag;
  }

  @Override
  public List<IState<SaleOrderViewVO>> next() {
    List<IState<SaleOrderViewVO>> list =
        new ArrayList<IState<SaleOrderViewVO>>();
    list.add(new InvoiceOpenState());
    list.add(new RowOpenState());
    return list;
  }

  @Override
  public void setState(SaleOrderViewVO[] views) {
    AroundProcesser<SaleOrderViewVO> processer =
        new AroundProcesser<SaleOrderViewVO>(StatePlugInPoint.OutOpenState);
    this.addRule(processer);

    TimeLog.logStart();
    SaleOrderViewVO[] vos = processer.before(views);
    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4006011_0", "04006011-0153")/*@res "出库打开前执行业务规则"*/);

    TimeLog.logStart();
    super.setState(vos);
    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4006011_0", "04006011-0154")/*@res "修改表体状态为出库打开"*/);

    TimeLog.logStart();
    processer.after(vos);
    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4006011_0", "04006011-0155")/*@res "出库打开后执行业务规则"*/);

    /**
     * 处理结算打开，由于历史代码原因，没有根据状态机进行越迁方式进行编写
     * 正确的写法应该在next()方法中和ArSettleOpenState中isAutoTransitable（）写逻辑，
     */
    this.processOrderSquareOpen(views);

  }

  private void addRule(AroundProcesser<SaleOrderViewVO> processer) {

    if (SysInitGroupQuery.isCREDITEnabled()) {
      // 更新信用调用前
      processer.addBeforeRule(new RenovateARByBidsBeginRule(
          M30EngrossAction.M30OutOpen));
    }
    boolean icEnable = SysInitGroupQuery.isICEnabled();
    if (icEnable) {
      // 可用量变更前
      processer.addBeforeRule(new SaleOrderViewATPBeforeRule());

      // 可用量变更后
      processer.addAfterRule(new SaleOrderViewATPAfterRule());
    }

    // --------------------------------------
    if (SysInitGroupQuery.isCREDITEnabled()) {
      // 更新信用调用前
      processer.addAfterRule(new RenovateARByBidsEndRule(
          M30EngrossAction.M30OutOpen));
    }

    // 调整冲抵关系
    if (SOSysInitGroupQuery.isMeEnabled()) {
      processer.addBeforeRule(new RewriteME35WhenOutOpen());
    }
    else {
      processer.addAfterRule(new Rewrite35WhenOutOpen());
    }
    // 回写电子销售出库打开状态
    if(SysInitGroupQuery.isOPCEnabled()){
      processer.addAfterRule(new RewriteOPCWhenOutOpen());
    }
    // 回写促销价格表数量
    if (SysInitGroupQuery.isPRICEEnabled()) {
      processer.addAfterRule(new RewriteProPirceWhenOutOpen());
    }
  }

  private StateCalculateUtil getStateCalculateUtil() {
    if (this.stateCalculateUtil == null) {
      this.stateCalculateUtil = new StateCalculateUtil();
    }
    return this.stateCalculateUtil;
  }

  /**
   * 处理结算打开
   * 
   * @param views
   */
  private void processOrderSquareOpen(SaleOrderViewVO[] views) {
    // 销售订单表体id
    int len = views.length;
    String[] orderbids = new String[len];
    for (int i = 0; i < len; i++) {
      orderbids[i] = views[i].getBody().getCsaleorderbid();
    }
    BalOpenTrigger trigger = BalOpenTrigger.OUT_OPEN;
    BalOpenPara para = new BalOpenPara(orderbids, trigger);
    try {
      SOSaleOrderServicesUtil.processAutoBalOpen(para);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    /*** 2012-04-28 冯加滨 幺贵敬 只有手工操作需要刷新前台TS，因此迁移到最外层取处理 ***/
    // // 结算关闭刷新ts，结算关闭标志
    // new SaleOrderVOUtil().refreshViewForSettleClose(views);
  }

}
