package nc.bs.so.m30.state.row;

import java.util.ArrayList;
import java.util.List;

import nc.bs.so.m30.plugin.StatePlugInPoint;
import nc.bs.so.m30.state.BillStateUtil;
import nc.bs.so.m30.state.StateCalculateUtil;
import nc.impl.pubapp.bill.state.AbstractRowState;
import nc.impl.pubapp.bill.state.IState;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.itf.so.m33.ref.so.m30.SOSaleOrderServicesUtil;
import nc.pubitf.so.m30.balend.BalEndPara;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.m30.balend.enumeration.BalEndTrigger;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderViewVO;

public class InvoiceCloseState extends AbstractRowState<SaleOrderViewVO> {

  private StateCalculateUtil stateCalculateUtil;

  public InvoiceCloseState() {
    super(SaleOrderBVO.class, SaleOrderBVO.BBINVOICENDFLAG, UFBoolean.TRUE);
  }

  @Override
  public boolean isAutoTransitable(SaleOrderViewVO vo) {
    if (this.isThisState(vo) || !this.isPrevStateValid(vo)) {
      return false;
    }

    return this.getStateCalculateUtil().isAutoTransitInvoiceClose(vo);
  }

  /**
   * 是否可以开票关闭
   * 
   * @param vo
   * @return boolean
   */
  public boolean isInvoiceClose(SaleOrderViewVO vo) {
    if (this.isThisState(vo) || !this.isPrevStateValid(vo)) {
      return false;
    }
    return this.getStateCalculateUtil().isInvoiceClose(vo);
  }
  
  
  /**
   * 修订是否可以开票关闭
   * 
   * @param vo
   * @return boolean
   */
  public boolean isReviseInvoiceClose(SaleOrderViewVO vo, SaleOrderViewVO originView) {
    if (this.isThisState(vo) || !this.isPrevStateValid(vo)) {
      return false;
    }
    return this.getStateCalculateUtil().isReviseInvoiceClose(vo,originView);
  }

  /**
   * 是否可以开票关闭4途损单
   * 
   * @param vo
   * @return boolean
   */
  public boolean isInvoiceCloseFor4453(SaleOrderViewVO vo) {
    if (this.isThisState(vo) || !this.isPrevStateValid(vo)) {
      return false;
    }
    return this.getStateCalculateUtil().isInvoiceCloseFor4453(vo);
  }

  /**
   * 出库对冲是否可以开票打开
   * 
   * @param vo
   * @return boolean
   */
  public boolean isInvoiceCloseForOutRush(SaleOrderViewVO vo) {
    if (this.isThisState(vo) || !this.isPrevStateValid(vo)) {
      return false;
    }
    return this.getStateCalculateUtil().isInvoiceCloseForOutRush(vo);
  }

  @Override
  public boolean isPrevStateValid(SaleOrderViewVO vo) {
    // 只有整单打开状态以及之后的整单状态才可以做行状态的操作
    BillStateUtil statePriority = new BillStateUtil();
    return statePriority.canBeExecuteState(vo);
  }

  @Override
  public List<IState<SaleOrderViewVO>> next() {
    List<IState<SaleOrderViewVO>> list =
        new ArrayList<IState<SaleOrderViewVO>>();
    list.add(new OutCloseState());
    list.add(new SendCloseState());
    list.add(new RowCloseState());
    return list;
  }

  @Override
  public void setState(SaleOrderViewVO[] views) {
    AroundProcesser<SaleOrderViewVO> processer =
        new AroundProcesser<SaleOrderViewVO>(StatePlugInPoint.InvoiceCloseState);

    TimeLog.logStart();
    SaleOrderViewVO[] vos = processer.before(views);
    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4006011_0", "04006011-0144")/*@res "发票关闭前执行业务规则"*/);

    TimeLog.logStart();
    super.setState(vos);
    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4006011_0", "04006011-0145")/*@res "修改表体状态为发票关闭"*/);

    TimeLog.logStart();
    processer.after(vos);
    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4006011_0", "04006011-0146")/*@res "发票关闭后执行业务规则"*/);

    /**
     * 处理结算关闭，由于历史代码原因，没有根据状态机进行越迁方式进行编写
     * 正确的写法应该在next()方法中和ArSettleCloseState中isAutoTransitable（）写逻辑，
     */
    this.processOrderSquareClose(views);
  }

  private StateCalculateUtil getStateCalculateUtil() {
    if (this.stateCalculateUtil == null) {
      this.stateCalculateUtil = new StateCalculateUtil();
    }
    return this.stateCalculateUtil;
  }

  /**
   * 处理结算关闭
   * 
   * @param views
   */
  private void processOrderSquareClose(SaleOrderViewVO[] views) {
    // 销售订单表体id
    int len = views.length;
    String[] orderbids = new String[len];
    for (int i = 0; i < len; i++) {
      orderbids[i] = views[i].getBody().getCsaleorderbid();
    }
    BalEndTrigger trigger = BalEndTrigger.VOICE_CLOSE;
    BalEndPara para = new BalEndPara(orderbids, trigger);
    try {
      SOSaleOrderServicesUtil.processAutoBalEnd(para);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    /*** 2012-04-28 冯加滨 幺贵敬 只有手工操作需要刷新前台TS，因此迁移到最外层取处理 ***/
    // // 结算关闭刷新ts，结算关闭标志
    // new SaleOrderVOUtil().refreshViewForSettleClose(views);
  }

}
