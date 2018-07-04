package nc.bs.so.m30.state.row;

import java.util.ArrayList;
import java.util.List;

import nc.bs.so.m30.plugin.StatePlugInPoint;
import nc.bs.so.m30.rule.credit.RenovateARByBidsBeginRule;
import nc.bs.so.m30.rule.credit.RenovateARByBidsEndRule;
import nc.bs.so.m30.state.StateCalculateUtil;
import nc.bs.so.m30.state.rule.AROpenProcessRule;
import nc.impl.pubapp.bill.state.AbstractRowState;
import nc.impl.pubapp.bill.state.IState;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.vo.credit.engrossmaintain.pub.action.M30EngrossAction;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderViewVO;

public class ArSettleOpenState extends AbstractRowState<SaleOrderViewVO> {

  private StateCalculateUtil stateCalculateUtil;

  public ArSettleOpenState() {
    super(SaleOrderBVO.class, SaleOrderBVO.BBARSETTLEFLAG, UFBoolean.FALSE);
  }

  @Override
  public boolean isAutoTransitable(SaleOrderViewVO vo) {
    if (this.isThisState(vo) || !this.isPrevStateValid(vo)) {
      return false;
    }

    return this.getStateCalculateUtil().isAutoTransitArSettleOpen(vo);
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
        new AroundProcesser<SaleOrderViewVO>(StatePlugInPoint.ArSettleOpenState);
    this.addRule(processer);
    TimeLog.logStart();
    SaleOrderViewVO[] vos = processer.before(views);
    TimeLog.info("收入结算打开前执行业务规则"); /*-=notranslate=-*/

    TimeLog.logStart();
    super.setState(vos);
    TimeLog.info("修改表体状态为收入结算打开"); /*-=notranslate=-*/

    TimeLog.logStart();
    processer.after(vos);
    TimeLog.info("收入结算打开后执行业务规则"); /*-=notranslate=-*/
  }

  private void addRule(AroundProcesser<SaleOrderViewVO> processer) {
    if (SysInitGroupQuery.isCREDITEnabled()) {
      // 更新信用调用前
      processer.addBeforeRule(new RenovateARByBidsBeginRule(
          M30EngrossAction.M30SettleOpen));
    }

    // --------------------------------------
    if (SysInitGroupQuery.isCREDITEnabled()) {
      // 更新信用调用前
      processer.addAfterRule(new RenovateARByBidsEndRule(
          M30EngrossAction.M30SettleOpen));
    }

    // 应收结算打开处理回冲应收
    processer.addAfterRule(new AROpenProcessRule());
  }

  private StateCalculateUtil getStateCalculateUtil() {
    if (this.stateCalculateUtil == null) {
      this.stateCalculateUtil = new StateCalculateUtil();
    }
    return this.stateCalculateUtil;
  }
}
