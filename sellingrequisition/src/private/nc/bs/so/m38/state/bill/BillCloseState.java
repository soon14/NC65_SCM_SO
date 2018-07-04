package nc.bs.so.m38.state.bill;

import java.util.ArrayList;
import java.util.List;

import nc.bs.so.m38.plugin.StatePlugInPoint;
import nc.bs.so.m38.state.BillStateUtil;
import nc.bs.so.m38.state.StateCalculateUtil;
import nc.bs.so.m38.state.row.RowCloseState;
import nc.impl.pubapp.bill.state.AbstractBillState;
import nc.impl.pubapp.bill.state.IState;
import nc.impl.pubapp.bill.state.ITransitionState;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.m38.entity.PreOrderHVO;
import nc.vo.so.m38.entity.PreOrderVO;
import nc.vo.so.m38.entity.PreOrderViewVO;
import nc.vo.so.pub.enumeration.BillStatus;

/**
 * 预订单整单关闭状态
 * 
 * @author 刘志伟
 * @since 6.0
 * @time 2010-04-08 上午09:27:07
 */
public class BillCloseState extends AbstractBillState<PreOrderVO> implements
    ITransitionState<PreOrderVO, PreOrderViewVO> {

  private StateCalculateUtil stateCalculateUtil;

  public BillCloseState() {
    super(PreOrderHVO.FSTATUSFLAG, BillStatus.CLOSED.value());
  }

  /**
   * 预订单整单关闭后处理行关闭
   */
  @Override
  public IState<PreOrderViewVO> getTransitTargetState() {
    return new RowCloseState();
  }

  @Override
  public boolean isAutoTransitable(PreOrderVO vo) {
    if (this.isThisState(vo) || !this.isPrevStateValid(vo)) {
      return false;
    }

    return this.getStateCalculateUtil().isAutoTransitBillClose(vo);
  }

  @Override
  public boolean isPrevStateValid(PreOrderVO vo) {
    // 只有整单打开状态之后的整单状态才可以
    BillStateUtil statePriority = new BillStateUtil();
    return statePriority.canBeExecuteState(vo);
  }

  @Override
  public List<IState<PreOrderVO>> next() {
    List<IState<PreOrderVO>> list = new ArrayList<IState<PreOrderVO>>();
    return list;
  }

  @Override
  public void setState(PreOrderVO[] bills) {
    AroundProcesser<PreOrderVO> processer =
        new AroundProcesser<PreOrderVO>(StatePlugInPoint.BillCloseState);

    TimeLog.logStart();
    PreOrderVO[] vos = processer.before(bills);
    TimeLog.info("整单关闭前执行业务规则"); /*-=notranslate=-*/

    TimeLog.logStart();
    super.setState(vos);
    TimeLog.info("整单关闭前执行业务规则"); /*-=notranslate=-*/

    TimeLog.logStart();
    processer.after(vos);
    TimeLog.info("整单关闭后执行业务规则"); /*-=notranslate=-*/
  }

  private StateCalculateUtil getStateCalculateUtil() {
    if (this.stateCalculateUtil == null) {
      this.stateCalculateUtil = new StateCalculateUtil();
    }
    return this.stateCalculateUtil;
  }
}
