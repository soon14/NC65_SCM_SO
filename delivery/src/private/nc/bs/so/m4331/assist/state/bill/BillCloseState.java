package nc.bs.so.m4331.assist.state.bill;

import java.util.ArrayList;
import java.util.List;

import nc.bs.so.m4331.assist.state.BillStateUtil;
import nc.bs.so.m4331.assist.state.StateCalculateUtil;
import nc.bs.so.m4331.assist.state.row.RowCloseState;
import nc.bs.so.m4331.plugin.StatePlugInPoint;
import nc.impl.pubapp.bill.state.AbstractBillState;
import nc.impl.pubapp.bill.state.IState;
import nc.impl.pubapp.bill.state.ITransitionState;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.m4331.entity.DeliveryHVO;
import nc.vo.so.m4331.entity.DeliveryVO;
import nc.vo.so.m4331.entity.DeliveryViewVO;
import nc.vo.so.pub.enumeration.BillStatus;

/**
 * 发货单整单关闭状态
 *
 * @author 祝会征
 * @since 6.0
 * @time 2010-04-08 上午09:27:07
 */
public class BillCloseState extends AbstractBillState<DeliveryVO> implements
    ITransitionState<DeliveryVO, DeliveryViewVO> {

  public BillCloseState() {
    super(DeliveryHVO.FSTATUSFLAG, BillStatus.CLOSED.value());
  }

  /**
   * 预订单整单关闭后处理行关闭
   */
  @Override
  public IState<DeliveryViewVO> getTransitTargetState() {
    return new RowCloseState();
  }

  @Override
  public boolean isAutoTransitable(DeliveryVO vo) {
    boolean isAuto = true;
    if (this.isThisState(vo)) {
      isAuto = false;
    }
    else if (!this.isPrevStateValid(vo)) {
      isAuto = false;
    }
    else {
      // 根据行状态判断是否可以整单关闭
      StateCalculateUtil util = new StateCalculateUtil();
      isAuto = util.isAutoBillCloseByRowState(vo);
    }
    return isAuto;
  }

  @Override
  public boolean isPrevStateValid(DeliveryVO vo) {
    // 只有整单打开状态之后的整单状态才可以
    BillStateUtil statePriority = new BillStateUtil();
    return statePriority.canBeExecuteState(vo);
  }

  @Override
  public List<IState<DeliveryVO>> next() {
    List<IState<DeliveryVO>> list = new ArrayList<IState<DeliveryVO>>();
    return list;
  }

  @Override
  public void setState(DeliveryVO[] bills) {
    AroundProcesser<DeliveryVO> processer =
        new AroundProcesser<DeliveryVO>(StatePlugInPoint.BillCloseState);
    // this.addRule(processer);
    TimeLog.logStart();
    DeliveryVO[] vos = processer.before(bills);
    TimeLog.info("整单关闭前执行业务规则"); /*-=notranslate=-*/
    vos[0].getParentVO().getStatus();
    TimeLog.logStart();
    super.setState(vos);
    TimeLog.info("整单关闭前执行业务规则"); /*-=notranslate=-*/
    TimeLog.logStart();
    processer.after(vos);
    TimeLog.info("整单关闭后执行业务规则"); /*-=notranslate=-*/
  }
}