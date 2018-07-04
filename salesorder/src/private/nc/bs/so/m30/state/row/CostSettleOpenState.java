package nc.bs.so.m30.state.row;

import java.util.ArrayList;
import java.util.List;

import nc.bs.so.m30.plugin.StatePlugInPoint;
import nc.bs.so.m30.state.StateCalculateUtil;
import nc.bs.so.m30.state.rule.IAOpenProcessRule;
import nc.impl.pubapp.bill.state.AbstractRowState;
import nc.impl.pubapp.bill.state.IState;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderViewVO;

public class CostSettleOpenState extends AbstractRowState<SaleOrderViewVO> {

  private StateCalculateUtil stateCalculateUtil;

  public CostSettleOpenState() {
    super(SaleOrderBVO.class, SaleOrderBVO.BBCOSTSETTLEFLAG, UFBoolean.FALSE);
  }

  @Override
  public boolean isAutoTransitable(SaleOrderViewVO vo) {
    if (this.isThisState(vo) || !this.isPrevStateValid(vo)) {
      return false;
    }

    return this.getStateCalculateUtil().isAutoTransitCostSettleOpen(vo);
  }

  @Override
  public boolean isPrevStateValid(SaleOrderViewVO vo) {
    if (this.isThisState(vo)) {
      return false;
    }
    return true;
  }

  @Override
  public List<IState<SaleOrderViewVO>> next() {
    List<IState<SaleOrderViewVO>> list =
        new ArrayList<IState<SaleOrderViewVO>>();
    list.add(new RowOpenState());
    return list;
  }

  @Override
  public void setState(SaleOrderViewVO[] views) {
    AroundProcesser<SaleOrderViewVO> processer =
        new AroundProcesser<SaleOrderViewVO>(
            StatePlugInPoint.CostSettleOpenState);
    this.addRule(processer);

    TimeLog.logStart();
    SaleOrderViewVO[] vos = processer.before(views);
    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4006011_0", "04006011-0141")/*@res "成本结算打开前执行业务规则"*/);

    TimeLog.logStart();
    super.setState(vos);
    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4006011_0", "04006011-0142")/*@res "修改表体状态为成本结算打开"*/);

    TimeLog.logStart();
    processer.after(vos);
    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4006011_0", "04006011-0143")/*@res "成本结算打开后执行业务规则"*/);
  }

  private void addRule(AroundProcesser<SaleOrderViewVO> processer) {
    // 成本结算关闭处理发出商品
    processer.addAfterRule(new IAOpenProcessRule());
  }

  private StateCalculateUtil getStateCalculateUtil() {
    if (this.stateCalculateUtil == null) {
      this.stateCalculateUtil = new StateCalculateUtil();
    }
    return this.stateCalculateUtil;
  }

}
