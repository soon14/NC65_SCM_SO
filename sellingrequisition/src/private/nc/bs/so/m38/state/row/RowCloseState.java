package nc.bs.so.m38.state.row;

import java.util.ArrayList;
import java.util.List;

import nc.bs.so.m38.plugin.StatePlugInPoint;
import nc.bs.so.m38.state.BillStateUtil;
import nc.bs.so.m38.state.StateCalculateUtil;
import nc.bs.so.m38.state.bill.BillCloseState;
import nc.impl.pubapp.bill.state.AbstractRowState;
import nc.impl.pubapp.bill.state.IState;
import nc.impl.pubapp.bill.state.ITransitionState;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.m38.entity.PreOrderBVO;
import nc.vo.so.m38.entity.PreOrderVO;
import nc.vo.so.m38.entity.PreOrderViewVO;

/**
 * 预订单行关闭状态
 * 
 * @author 刘志伟
 * @since 6.0
 * @time 2010-04-08 上午09:27:07
 */
public class RowCloseState extends AbstractRowState<PreOrderViewVO> implements
    ITransitionState<PreOrderViewVO, PreOrderVO> {

  private StateCalculateUtil stateCalculateUtil;

  public RowCloseState() {
    super(PreOrderBVO.class, PreOrderBVO.BLINECLOSE, UFBoolean.TRUE);
  }

  /**
   * 预订单行关闭后处理整单关闭
   */
  @Override
  public IState<PreOrderVO> getTransitTargetState() {
    return new BillCloseState();
  }

  /**
   * 执行next/TransitTargetState时判断
   * 
   * @param vo
   * @return boolean
   */
  @Override
  public boolean isAutoTransitable(PreOrderViewVO vo) {
    if (this.isThisState(vo) || !this.isPrevStateValid(vo)) {
      return false;
    }
    return this.getStateCalculateUtil().isAutoTransitRowClose(vo);
  }

  @Override
  public boolean isPrevStateValid(PreOrderViewVO vo) {
    // 只有整单打开状态以及之后的整单状态才可以做行状态的操作
    BillStateUtil statePriority = new BillStateUtil();
    return statePriority.canBeExecuteState(vo);
  }

  /**
   * 是否可以行关闭
   * 
   * @param vo
   * @return boolean
   */
  public boolean isRowClose(PreOrderViewVO vo) {
    if (this.isThisState(vo) || !this.isPrevStateValid(vo)) {
      return false;
    }
    return this.getStateCalculateUtil().isRowClose(vo);
  }

  @Override
  public List<IState<PreOrderViewVO>> next() {
    List<IState<PreOrderViewVO>> list = new ArrayList<IState<PreOrderViewVO>>();
    return list;
  }

  @Override
  public void setState(PreOrderViewVO[] views) {
    AroundProcesser<PreOrderViewVO> processer =
        new AroundProcesser<PreOrderViewVO>(StatePlugInPoint.RowCloseState);

    TimeLog.logStart();
    PreOrderViewVO[] vos = processer.before(views);
    TimeLog.info("行关闭前执行业务规则"); /*-=notranslate=-*/

    TimeLog.logStart();
    super.setState(vos);
    TimeLog.info("修改表体状态为行关闭"); /*-=notranslate=-*/

    TimeLog.logStart();
    processer.after(vos);
    TimeLog.info("行关闭后执行业务规则"); /*-=notranslate=-*/
  }

  private StateCalculateUtil getStateCalculateUtil() {
    if (this.stateCalculateUtil == null) {
      this.stateCalculateUtil = new StateCalculateUtil();
    }
    return this.stateCalculateUtil;
  }

}
