package nc.bs.so.m30.state.bill;

import java.util.ArrayList;
import java.util.List;

import nc.bs.so.m30.plugin.StatePlugInPoint;
import nc.bs.so.m30.state.BillStateUtil;
import nc.bs.so.m30.state.StateCalculateUtil;
import nc.bs.so.m30.state.row.RowOpenState;
import nc.impl.pubapp.bill.state.AbstractBillState;
import nc.impl.pubapp.bill.state.IState;
import nc.impl.pubapp.bill.state.ITransitionState;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.entity.SaleOrderViewVO;
import nc.vo.so.pub.enumeration.BillStatus;

public class BillOpenState extends AbstractBillState<SaleOrderVO> implements
    ITransitionState<SaleOrderVO, SaleOrderViewVO> {

  private StateCalculateUtil stateCalculateUtil;

  public BillOpenState() {
    super(SaleOrderHVO.FSTATUSFLAG, BillStatus.AUDIT.value());
  }

  @Override
  public IState<SaleOrderViewVO> getTransitTargetState() {
    return new RowOpenState();
  }

  @Override
  public boolean isAutoTransitable(SaleOrderVO vo) {
    if (this.isThisState(vo) || !this.isPrevStateValid(vo)) {
      return false;
    }

    return this.getStateCalculateUtil().isAutoTransitBillOpen(vo);
  }

  @Override
  public boolean isPrevStateValid(SaleOrderVO vo) {
    // 只有整单关闭状态才可以打开
    BillStateUtil statePriority = new BillStateUtil();
    return statePriority.canBeExecuteState(vo);
  }

  @Override
  public List<IState<SaleOrderVO>> next() {
    List<IState<SaleOrderVO>> list = new ArrayList<IState<SaleOrderVO>>();
    return list;
  }

  @Override
  public void setState(SaleOrderVO[] bills) {
    AroundProcesser<SaleOrderVO> processer =
        new AroundProcesser<SaleOrderVO>(StatePlugInPoint.BillOpenState);
    TimeLog.logStart();

    SaleOrderVO[] vos = processer.before(bills);
    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4006011_0", "04006011-0135")/*@res "整单打开前执行业务规则"*/);

    TimeLog.logStart();
    super.setState(vos);
    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4006011_0", "04006011-0136")/*@res "修改表头状态为整单打开"*/);

    TimeLog.logStart();
    processer.after(vos);
    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4006011_0", "04006011-0137")/*@res "整单打开后执行业务规则"*/);
  }

  private StateCalculateUtil getStateCalculateUtil() {
    if (this.stateCalculateUtil == null) {
      this.stateCalculateUtil = new StateCalculateUtil();
    }
    return this.stateCalculateUtil;
  }

}
