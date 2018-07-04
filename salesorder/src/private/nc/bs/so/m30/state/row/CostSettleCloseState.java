package nc.bs.so.m30.state.row;

import java.util.ArrayList;
import java.util.List;

import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderViewVO;

import nc.bs.so.m30.plugin.StatePlugInPoint;
import nc.bs.so.m30.state.BillStateUtil;
import nc.bs.so.m30.state.StateCalculateUtil;
import nc.bs.so.m30.state.rule.IACloseProcessRule;

import nc.impl.pubapp.bill.state.AbstractRowState;
import nc.impl.pubapp.bill.state.IState;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;

/**
 * 成本结算关闭
 * 
 * @since 6.1
 * @version 2013-05-28 14:16:32
 * @author yixl
 */
public class CostSettleCloseState extends AbstractRowState<SaleOrderViewVO> {

  private StateCalculateUtil stateCalculateUtil;

  /**
   * 成本结算关闭构造子
   */
  public CostSettleCloseState() {
    super(SaleOrderBVO.class, SaleOrderBVO.BBCOSTSETTLEFLAG, UFBoolean.TRUE);
  }

  @Override
  public boolean isAutoTransitable(SaleOrderViewVO vo) {
    if (this.isThisState(vo) || !this.isPrevStateValid(vo)) {
      return false;
    }

    return this.getStateCalculateUtil().isAutoTransitCostSettleClose(vo);
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
    list.add(new RowCloseState());
    return list;
  }

  @Override
  public void setState(SaleOrderViewVO[] views) {
    AroundProcesser<SaleOrderViewVO> processer =
        new AroundProcesser<SaleOrderViewVO>(
            StatePlugInPoint.CostSettleCloseState);
    this.addRule(processer);

    TimeLog.logStart();
    SaleOrderViewVO[] vos = processer.before(views);
    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4006011_0", "04006011-0138")/*@res "成本结算关闭前执行业务规则"*/);

    TimeLog.logStart();
    super.setState(vos);
    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4006011_0", "04006011-0139")/*@res "修改表体状态为成本结算关闭"*/);

    TimeLog.logStart();
    processer.after(vos);
    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4006011_0", "04006011-0140")/*@res "成本结算关闭后执行业务规则"*/);
  }

  private void addRule(AroundProcesser<SaleOrderViewVO> processer) {
    // 成本结算关闭处理发出商品
    processer.addAfterRule(new IACloseProcessRule());
  }

  private StateCalculateUtil getStateCalculateUtil() {
    if (this.stateCalculateUtil == null) {
      this.stateCalculateUtil = new StateCalculateUtil();
    }
    return this.stateCalculateUtil;
  }

}
