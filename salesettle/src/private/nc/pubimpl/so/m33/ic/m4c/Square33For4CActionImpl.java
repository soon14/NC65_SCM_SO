package nc.pubimpl.so.m33.ic.m4c;

import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.so.m33.m4c.entity.SquareOutBVO;
import nc.vo.so.m33.m4c.entity.SquareOutVO;
import nc.vo.so.m33.m4c.entity.SquareOutVOUtils;
import nc.vo.so.m33.m4c.entity.SquareOutViewVO;
import nc.vo.so.pub.util.ArrayUtil;
import nc.vo.so.pub.util.SOVOChecker;
import nc.vo.so.pub.votools.SoVoTools;
import nc.vo.trade.checkrule.VOChecker;

import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;

import nc.pubitf.so.m33.ic.m4c.ISquareAcionFor4C;

import nc.bs.so.m33.biz.m4c.action.ar.ARIncomeFor4CAction;
import nc.bs.so.m33.biz.m4c.action.ar.ETIncomeFor4CAction;
import nc.bs.so.m33.biz.m4c.action.ia.IACostFor4CAction;
import nc.bs.so.m33.biz.m4c.action.ia.IARegisterFor4CAction;
import nc.bs.so.m33.biz.m4c.action.outrush.OutRushFor4CAction;
import nc.bs.so.m33.maintain.m4c.UpdateSquare4CFlagBP;
import nc.bs.so.m33.maintain.m4c.query.QuerySquare4CVOBP;

import nc.impl.pubapp.pattern.data.bill.tool.BillConcurrentTool;
import nc.impl.pubapp.pattern.data.view.tool.ViewConcurrentTool;
import nc.impl.pubapp.pattern.data.vo.tool.VOConcurrentTool;

/**
 * 销售出库结算
 * 
 * @since 6.1
 * @version 2013-03-22 14:35:33
 * @author yixl
 */
public class Square33For4CActionImpl implements ISquareAcionFor4C {

  @Override
  public void autoSquareCostSrv(AbstractBill[] vos) throws BusinessException {

    if (!SysInitGroupQuery.isIAEnabled()) {
      return;
    }

    try {
      // 对上游出库单加锁
      BillConcurrentTool tool = new BillConcurrentTool();
      tool.lockBill(vos);

      // 查询结算单
      SquareOutVO[] sqvos = this.querySquareOutVOBy4CID(vos);
      if (VOChecker.isEmpty(sqvos)) {
        return;
      }

      // 对出库待结算单加锁
      tool.lockBill(sqvos);

      // 检查是否可以传存货
      sqvos = SquareOutVOUtils.getInstance().filterCostableVO(sqvos);
      if (sqvos == null) {
        return;
      }

      // 销售出库单自动成本结算更新表体结算标志位
      new UpdateSquare4CFlagBP().updateSquareBFlagForAutoIACost(sqvos);

      // 传成本结算
      new IACostFor4CAction().execCost(sqvos);
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
  }

  @Override
  public void autoSquareEstimateSrv(AbstractBill[] vos)
      throws BusinessException {

    if (!SysInitGroupQuery.isAREnabled()) {
      return;
    }

    try {
      // 加锁
      BillConcurrentTool tool = new BillConcurrentTool();
      tool.lockBill(vos);

      // 查询结算单
      SquareOutVO[] sqvos = this.querySquareOutVOBy4CID(vos);
      if (VOChecker.isEmpty(sqvos)) {
        return;
      }

      // 对出库待结算单加锁
      tool.lockBill(sqvos);

      // 检查是否可以传应收
      sqvos = SquareOutVOUtils.getInstance().filterIncomeableVO(sqvos);
      if (sqvos == null) {
        return;
      }

      // 销售出库单自动暂估收入更新表体结算标志位
      new UpdateSquare4CFlagBP().updateSquareBFlagForAutoETIncome(sqvos);

      // 传暂估应收处理
      new ETIncomeFor4CAction().execIncome(sqvos);
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
  }

  @Override
  public void autoSquareIncomeSrv(AbstractBill[] vos) throws BusinessException {

    if (!SysInitGroupQuery.isAREnabled()) {
      return;
    }

    try {
      // 加锁
      BillConcurrentTool tool = new BillConcurrentTool();
      tool.lockBill(vos);

      // 查询结算单
      SquareOutVO[] sqvos = this.querySquareOutVOBy4CID(vos);
      if (VOChecker.isEmpty(sqvos)) {
        return;
      }

      // 对出库待结算单加锁
      tool.lockBill(sqvos);

      // 检查是否可以传应收
      sqvos = SquareOutVOUtils.getInstance().filterIncomeableVO(sqvos);
      if (sqvos == null) {
        return;
      }

      // 销售出库单自动收入结算更新表体结算标志位
      new UpdateSquare4CFlagBP().updateSquareBFlagForAutoARIncome(sqvos);

      // 传应收处理
      new ARIncomeFor4CAction().execIncome(sqvos);
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
  }

  @Override
  public void autoSquareRegisterSrv(AbstractBill[] vos)
      throws BusinessException {

    if (!SysInitGroupQuery.isIAEnabled()) {
      return;
    }

    try {
      // 加锁
      BillConcurrentTool tool = new BillConcurrentTool();
      tool.lockBill(vos);

      // 查询结算单
      SquareOutVO[] sqvos = this.querySquareOutVOBy4CID(vos);
      if (VOChecker.isEmpty(sqvos)) {
        return;
      }

      // 对出库待结算单加锁
      tool.lockBill(sqvos);

      // 检查是否可以传存货
      sqvos = SquareOutVOUtils.getInstance().filterCostableVO(sqvos);
      if (sqvos == null) {
        return;
      }

      // 销售出库单自动计入发出商品更新表体结算标志位
      new UpdateSquare4CFlagBP().updateSquareBFlagForAutoIARegister(sqvos);

      // 普通出库单传发出商品
      new IARegisterFor4CAction().execCost(sqvos);
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
  }

  @Override
  public void manualSquareCostSrv(AbstractBill[] vos) throws BusinessException {

    if (!SysInitGroupQuery.isIAEnabled()) {
      return;
    }

    try {
      // 加锁
      BillConcurrentTool tool = new BillConcurrentTool();
      tool.lockBill(vos);

      // 查询结算单
      SquareOutVO[] sqvos = this.querySquareOutVOBy4CID(vos);
      if (VOChecker.isEmpty(sqvos)) {
        return;
      }

      // 对出库待结算单加锁
      tool.lockBill(sqvos);

      // 销售出库单手工成本结算更新表体结算标志位
      new UpdateSquare4CFlagBP().updateSquareBFlagForManualIACost(sqvos);
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
  }

  @Override
  public void manualSquareIncomeSrv(AbstractBill[] vos)
      throws BusinessException {

    if (!SysInitGroupQuery.isAREnabled()) {
      return;
    }

    try {
      // 加锁
      BillConcurrentTool tool = new BillConcurrentTool();
      tool.lockBill(vos);

      // 查询结算单
      SquareOutVO[] sqvos = this.querySquareOutVOBy4CID(vos);
      if (VOChecker.isEmpty(sqvos)) {
        return;
      }

      // 对出库待结算单加锁
      tool.lockBill(sqvos);

      // 销售出库单手工收入结算更新表体结算标志位
      new UpdateSquare4CFlagBP().updateSquareBFlagForManualARIncome(sqvos);
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
  }

  @Override
  public UFDouble outRush(String[] bluebids, String[] redbids)
      throws BusinessException {
    try {
      // 对出库单行id加锁
      String[] ids = ArrayUtil.combinArrays(bluebids, redbids);
      VOConcurrentTool votool = new VOConcurrentTool();
      votool.lock(SquareOutBVO.class, ids);

      // 查询蓝字结算单
      SquareOutViewVO[] bluevos =
          new QuerySquare4CVOBP().querySquareOutViewVOBy4CBID(bluebids);
      if (SOVOChecker.isEmpty(bluevos)) {
        return null;
      }

      // 查询红字结算单
      SquareOutViewVO[] redvos =
          new QuerySquare4CVOBP().querySquareOutViewVOBy4CBID(redbids);
      if (SOVOChecker.isEmpty(redvos)) {
        return null;
      }

      // 对出库待结算单加锁
      SquareOutViewVO[] vos = ArrayUtil.combinArrays(bluevos, redvos);
      ViewConcurrentTool viewtool = new ViewConcurrentTool();
      viewtool.lock(vos);

      // 出库对冲
      OutRushFor4CAction rushFor4CAction = new OutRushFor4CAction();
      return rushFor4CAction.execOutRush(bluevos, redvos);
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return null;
  }

  private SquareOutVO[] querySquareOutVOBy4CID(AbstractBill[] vos) {
    SquareOutVO[] sqvos =
        new QuerySquare4CVOBP().querySquareOutVOBy4CID(SoVoTools
            .getVOPKValues(vos));
    return sqvos;
  }

}
