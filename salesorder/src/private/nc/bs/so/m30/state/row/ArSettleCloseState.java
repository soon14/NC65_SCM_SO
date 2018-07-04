package nc.bs.so.m30.state.row;

import java.util.ArrayList;
import java.util.List;

import nc.vo.credit.engrossmaintain.pub.action.M30EngrossAction;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderViewVO;

import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;

import nc.bs.so.m30.plugin.StatePlugInPoint;
import nc.bs.so.m30.rule.credit.RenovateARByBidsBeginRule;
import nc.bs.so.m30.rule.credit.RenovateARByBidsEndRule;
import nc.bs.so.m30.state.BillStateUtil;
import nc.bs.so.m30.state.StateCalculateUtil;
import nc.bs.so.m30.state.rule.ARCloseProcessRule;

import nc.impl.pubapp.bill.state.AbstractRowState;
import nc.impl.pubapp.bill.state.IState;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;

/**
 * 收入结算关闭
 * 
 * @since 6.1
 * @version 2013-05-28 14:18:32
 * @author yixl
 */
public class ArSettleCloseState extends AbstractRowState<SaleOrderViewVO> {

  private StateCalculateUtil stateCalculateUtil;

  /**
   * 收入结算关闭构造子
   */
  public ArSettleCloseState() {
    super(SaleOrderBVO.class, SaleOrderBVO.BBARSETTLEFLAG, UFBoolean.TRUE);
  }

  @Override
  public boolean isAutoTransitable(SaleOrderViewVO vo) {
    if (this.isThisState(vo) || !this.isPrevStateValid(vo)) {
      return false;
    }

    return this.getStateCalculateUtil().isAutoTransitArSettleClose(vo);
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
            StatePlugInPoint.ArSettleCloseState);
    this.addRule(processer);
    TimeLog.logStart();
    SaleOrderViewVO[] vos = processer.before(views);
    TimeLog.info("收入结算关闭前执行业务规则"); /*-=notranslate=-*/

    TimeLog.logStart();
    super.setState(vos);
    TimeLog.info("修改表体状态为收入结算关闭"); /*-=notranslate=-*/

    TimeLog.logStart();
    processer.after(vos);
    TimeLog.info("收入结算关闭后执行业务规则"); /*-=notranslate=-*/
  }

  private void addRule(AroundProcesser<SaleOrderViewVO> processer) {

    if (SysInitGroupQuery.isCREDITEnabled()) {
      // 更新信用调用前
      processer.addBeforeRule(new RenovateARByBidsBeginRule(
          M30EngrossAction.M30SettleClose));
    }

    // --------------------------------------
    if (SysInitGroupQuery.isCREDITEnabled()) {
      // 更新信用调用后
      processer.addAfterRule(new RenovateARByBidsEndRule(
          M30EngrossAction.M30SettleClose));
    }

    // 应收结算关闭处理回冲应收
    processer.addAfterRule(new ARCloseProcessRule());
  }

  private StateCalculateUtil getStateCalculateUtil() {
    if (this.stateCalculateUtil == null) {
      this.stateCalculateUtil = new StateCalculateUtil();
    }
    return this.stateCalculateUtil;
  }

}
