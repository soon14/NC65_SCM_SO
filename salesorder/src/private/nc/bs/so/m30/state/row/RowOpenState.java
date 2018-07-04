package nc.bs.so.m30.state.row;

import java.util.ArrayList;
import java.util.List;

import nc.bs.so.m30.plugin.StatePlugInPoint;
import nc.bs.so.m30.state.BillStateUtil;
import nc.bs.so.m30.state.StateCalculateUtil;
import nc.bs.so.m30.state.bill.BillOpenState;
import nc.bs.so.m30.state.rule.RowStateReWriteZ3Rule;
import nc.impl.pubapp.bill.state.AbstractRowState;
import nc.impl.pubapp.bill.state.IState;
import nc.impl.pubapp.bill.state.ITransitionState;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.entity.SaleOrderViewVO;
import nc.vo.so.pub.enumeration.BillStatus;

/**
 * 销售订单行打开
 * 
 * @author 刘志伟
 * @time 2010-01-28 下午13:49:07
 */
public class RowOpenState extends AbstractRowState<SaleOrderViewVO> implements
    ITransitionState<SaleOrderViewVO, SaleOrderVO> {

  private StateCalculateUtil stateCalculateUtil;

  public RowOpenState() {
    super(SaleOrderBVO.class, SaleOrderBVO.FROWSTATUS, BillStatus.AUDIT.value());
  }

  @Override
  public IState<SaleOrderVO> getTransitTargetState() {
    return new BillOpenState();
  }

  @Override
  public boolean isAutoTransitable(SaleOrderViewVO vo) {
    if (this.isThisState(vo) || !this.isPrevStateValid(vo)) {
      return false;
    }

    return this.getStateCalculateUtil().isAutoTransitRowOpen(vo);
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
    list.add(new SendOpenState());
    list.add(new OutOpenState());
    list.add(new InvoiceOpenState());
    list.add(new ArSettleOpenState());
    list.add(new CostSettleOpenState());
    return list;
  }

  @Override
  public void setState(SaleOrderViewVO[] views) {
    AroundProcesser<SaleOrderViewVO> processer =
        new AroundProcesser<SaleOrderViewVO>(StatePlugInPoint.RowOpenState);
    this.addRule(processer);
    TimeLog.logStart();
    SaleOrderViewVO[] vos = processer.before(views);
    TimeLog.info("行打开前执行业务规则"); /*-=notranslate=-*/

    TimeLog.logStart();
    super.setState(vos);
    TimeLog.info("修改表体状态为行关闭"); /*-=notranslate=-*/

    TimeLog.logStart();
    processer.after(vos);
    TimeLog.info("行打开后执行业务规则"); /*-=notranslate=-*/
  }

  private void addRule(AroundProcesser<SaleOrderViewVO> processer) {
    // 订单关闭回写合同
    processer.addAfterRule(new RowStateReWriteZ3Rule(false));
  }

  private StateCalculateUtil getStateCalculateUtil() {
    if (this.stateCalculateUtil == null) {
      this.stateCalculateUtil = new StateCalculateUtil();
    }
    return this.stateCalculateUtil;
  }
}
