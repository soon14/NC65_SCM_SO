package nc.bs.so.m38.state.row;

import java.util.ArrayList;
import java.util.List;

import nc.bs.so.m38.plugin.StatePlugInPoint;
import nc.bs.so.m38.state.BillStateUtil;
import nc.bs.so.m38.state.StateCalculateUtil;
import nc.bs.so.m38.state.bill.BillOpenState;
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
 * 预订单行打开状态
 * 
 * @author 刘志伟
 * @since 6.0
 * @time 2010-04-08 上午09:27:07
 */
public class RowOpenState extends AbstractRowState<PreOrderViewVO> implements
    ITransitionState<PreOrderViewVO, PreOrderVO> {

  private StateCalculateUtil stateCalculateUtil;

  public RowOpenState() {
    super(PreOrderBVO.class, PreOrderBVO.BLINECLOSE, UFBoolean.FALSE);
  }

  /**
   * 预订单行打开后处理整单打开
   */
  @Override
  public IState<PreOrderVO> getTransitTargetState() {
    return new BillOpenState();
  }

  @Override
  public boolean isAutoTransitable(PreOrderViewVO vo) {
    if (this.isThisState(vo) || !this.isPrevStateValid(vo)) {
      return false;
    }
    return this.getStateCalculateUtil().isAutoTransitRowOpen(vo);
  }

  @Override
  public boolean isPrevStateValid(PreOrderViewVO vo) {
    // 只有整单打开状态以及之后的整单状态才可以做行状态的操作
    BillStateUtil statePriority = new BillStateUtil();
    return statePriority.canBeExecuteState(vo);
  }

  /**
   * 是否可以行打开
   */
  public boolean isRowOpen(PreOrderViewVO vo) {
    if (this.isThisState(vo) || !this.isPrevStateValid(vo)) {
      return false;
    }
    return this.getStateCalculateUtil().isRowOpen(vo);
  }

  @Override
  public List<IState<PreOrderViewVO>> next() {
    List<IState<PreOrderViewVO>> list = new ArrayList<IState<PreOrderViewVO>>();
    // TODO 请添加后续需要处理的状态
    return list;
  }

  @Override
  public void setState(PreOrderViewVO[] views) {
    AroundProcesser<PreOrderViewVO> processer =
        new AroundProcesser<PreOrderViewVO>(StatePlugInPoint.RowOpenState);

    TimeLog.logStart();
    PreOrderViewVO[] vos = processer.before(views);
    TimeLog.info("行打开前执行业务规则"); /*-=notranslate=-*/

    TimeLog.logStart();
    super.setState(vos);
    TimeLog.info("修改表体状态为行打开"); /*-=notranslate=-*/

    TimeLog.logStart();
    processer.after(vos);

    TimeLog.info("行打开后执行业务规则"); /*-=notranslate=-*/
  }

  private StateCalculateUtil getStateCalculateUtil() {
    if (this.stateCalculateUtil == null) {
      this.stateCalculateUtil = new StateCalculateUtil();
    }
    return this.stateCalculateUtil;
  }

}
