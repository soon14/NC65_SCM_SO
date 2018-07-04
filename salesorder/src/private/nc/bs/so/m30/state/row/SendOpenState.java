package nc.bs.so.m30.state.row;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nc.bs.so.m30.plugin.StatePlugInPoint;
import nc.bs.so.m30.rule.atp.SaleOrderViewATPAfterRule;
import nc.bs.so.m30.rule.atp.SaleOrderViewATPBeforeRule;
import nc.bs.so.m30.rule.credit.RenovateARByBidsBeginRule;
import nc.bs.so.m30.rule.credit.RenovateARByBidsEndRule;
import nc.bs.so.m30.rule.rewrite.price.RewriteProPirceWhenSendOpen;
import nc.bs.so.m30.state.StateCalculateUtil;
import nc.impl.pubapp.bill.state.AbstractRowState;
import nc.impl.pubapp.bill.state.IState;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.vo.bd.material.MaterialVO;
import nc.vo.credit.engrossmaintain.pub.action.M30EngrossAction;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderViewVO;
import nc.vo.so.pub.enumeration.BillStatus;

/**
 * 销售订单发货打开
 * 
 * @author 刘志伟
 * @time 2010-01-28 下午13:49:07
 */
public class SendOpenState extends AbstractRowState<SaleOrderViewVO> {

  private StateCalculateUtil stateCalculateUtil;

  public SendOpenState() {
    super(SaleOrderBVO.class, SaleOrderBVO.BBSENDENDFLAG, UFBoolean.FALSE);
  }

  @Override
  public boolean isAutoTransitable(SaleOrderViewVO vo) {
    if (this.isThisState(vo) || !this.isPrevStateValid(vo)) {
      return false;
    }
    return this.getStateCalculateUtil().isAutoTransitSendOpen(vo);
  }

  /**
   * 订单行是否满足手工发货打开的条件
   * 
   * @param view
   * @return
   */
  public boolean isManualSendOpen(SaleOrderViewVO view) {
    return this.getStateCalculateUtil().isManualSendOpen(view);
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

  /**
   * 是否可以发货打开
   * 
   * @param vo
   * @return boolean
   */
  public boolean isSendOpen(SaleOrderViewVO vo,
      Map<String, MaterialVO> materrialmaps) {
    if (this.isThisState(vo) || !this.isPrevStateValid(vo)) {
      return false;
    }
    return this.getStateCalculateUtil().isSendOpen(vo, materrialmaps);
  }

  /**
   * 途损时是否可以打开
   * 
   * @param vo
   * @return boolean
   */
  public boolean isSendOpenFor4453(SaleOrderViewVO vo,
      Map<String, MaterialVO> materrialmaps) {
    if (this.isThisState(vo) || !this.isPrevStateValid(vo)) {
      return false;
    }
    return this.getStateCalculateUtil().isSendOpenFor4453(vo, materrialmaps);
  }

  /**
   * 修订时是否可以打开
   * 
   * @param vo
   * @return boolean
   */
  public boolean isSendOpenForRevise(SaleOrderViewVO vo,
      SaleOrderViewVO originVo, Map<String, MaterialVO> materrialmaps) {
    if (this.isThisState(vo) || !this.isPrevStateValid(vo)) {
      return false;
    }
    return this.getStateCalculateUtil().isSendOpenForRevise(vo, originVo,
        materrialmaps);
  }

  @Override
  public List<IState<SaleOrderViewVO>> next() {
    List<IState<SaleOrderViewVO>> list =
        new ArrayList<IState<SaleOrderViewVO>>();
    list.add(new OutOpenState());
    list.add(new InvoiceOpenState());
    list.add(new RowOpenState());
    return list;
  }

  @Override
  public void setState(SaleOrderViewVO[] views) {
    AroundProcesser<SaleOrderViewVO> processer =
        new AroundProcesser<SaleOrderViewVO>(StatePlugInPoint.SendOpenState);
    this.addRule(processer);

    TimeLog.logStart();
    SaleOrderViewVO[] vos = processer.before(views);
    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4006011_0", "04006011-0159")/*@res "发货打开前执行业务规则"*/);

    TimeLog.logStart();
    super.setState(vos);
    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4006011_0", "04006011-0160")/*@res "修改表体状态为发货打开"*/);

    TimeLog.logStart();
    processer.after(vos);
    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4006011_0", "04006011-0161")/*@res "发货打开后执行业务规则"*/);
  }

  private void addRule(AroundProcesser<SaleOrderViewVO> processer) {
    if (SysInitGroupQuery.isCREDITEnabled()) {
      // 更新信用调用前
      processer.addBeforeRule(new RenovateARByBidsBeginRule(
          M30EngrossAction.M30SendOpen));
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
      // 更新信用调用后
      processer.addAfterRule(new RenovateARByBidsEndRule(
          M30EngrossAction.M30SendOpen));
    }
    
    // 回写促销价格表数量
    if (SysInitGroupQuery.isPRICEEnabled()) {
      processer.addBeforeRule(new RewriteProPirceWhenSendOpen());
    }

  }

  private StateCalculateUtil getStateCalculateUtil() {
    if (this.stateCalculateUtil == null) {
      this.stateCalculateUtil = new StateCalculateUtil();
    }
    return this.stateCalculateUtil;
  }

}
