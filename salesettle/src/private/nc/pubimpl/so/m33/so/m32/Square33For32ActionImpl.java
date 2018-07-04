package nc.pubimpl.so.m33.so.m32;

import nc.bs.so.m33.biz.m32.action.ar.ADIncomeFor32Action;
import nc.bs.so.m33.biz.m32.action.ar.ARIncomeFor32Action;
import nc.bs.so.m33.biz.m32.action.ia.IACostFor32Action;
import nc.bs.so.m33.maintain.m32.UpdateSquare32FlagBP;
import nc.bs.so.m33.maintain.m32.query.QuerySquare32VOBP;
import nc.impl.pubapp.pattern.data.bill.tool.BillConcurrentTool;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.pubitf.so.m33.so.m32.ISquareAcionFor32;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.so.m33.m32.entity.SquareInvVO;
import nc.vo.so.m33.m32.entity.SquareInvVOUtils;
import nc.vo.so.pub.votools.SoVoTools;
import nc.vo.trade.checkrule.VOChecker;

public class Square33For32ActionImpl implements ISquareAcionFor32 {

  /**
   * 销售发票自动成本结算
   * 
   * @param voInvoice
   * @throws BusinessException
   */
  @Override
  public void autoSquareCostSrv(AbstractBill[] vos) throws BusinessException {

    if (!SysInitGroupQuery.isIAEnabled()) {
      return;
    }

    try {
      // 对上游发票加锁
      BillConcurrentTool tool = new BillConcurrentTool();
      tool.lockBill(vos);

      // 查询结算单
      SquareInvVO[] sqvos = this.querySquareInvVOBy32ID(vos);

      // 对发票待结算单加锁
      tool.lockBill(sqvos);

      // 检查是否可以传存货
      sqvos = SquareInvVOUtils.getInstance().filterCostableVO(sqvos);
      if (sqvos == null) {
        return;
      }

      // 销售发票自动成本结算更新表体结算标志位
      new UpdateSquare32FlagBP().updateSquareBFlagForAutoIACost(sqvos);

      // 传成本结算
      new IACostFor32Action().execCost(sqvos);
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
  }

  /**
   * 销售发票自动应收结算
   * 
   * @param voInvoice
   * @throws BusinessException
   */
  @Override
  public void autoSquareIncomeSrv(AbstractBill[] vos) throws BusinessException {

    if (!SysInitGroupQuery.isAREnabled()) {
      return;
    }

    try {
      // 上游销售发票加锁
      BillConcurrentTool tool = new BillConcurrentTool();
      tool.lockBill(vos);

      // 查询结算单
      SquareInvVO[] sqvos = this.querySquareInvVOBy32ID(vos);

      // 对发票待结算单加锁
      tool.lockBill(sqvos);

      // 检查是否可以传应收
      sqvos = SquareInvVOUtils.getInstance().filterIncomeableVO(sqvos);
      if (sqvos == null) {
        return;
      }

      // 销售发票自动收入结算更新表体结算标志位
      new UpdateSquare32FlagBP().updateSquareBFlagForAutoARIncome(sqvos);

      // 传应收处理
      new ARIncomeFor32Action().execIncome(sqvos);
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
  }

  /**
   * 销售发票差额传应收
   * 上游出库单必须配置自动应收结算，销售发票根据单价的差异传差额确认应收
   * 
   * @param voInvoice
   * @throws BusinessException
   */
  @Override
  public void squareAdjustIncomeSrv(AbstractBill[] vos)
      throws BusinessException {

    if (!SysInitGroupQuery.isAREnabled()) {
      return;
    }

    try {
      // 上游销售发票加锁
      BillConcurrentTool tool = new BillConcurrentTool();
      tool.lockBill(vos);

      // 查询结算单
      SquareInvVO[] sqvos = this.querySquareInvVOBy32ID(vos);

      // 对发票待结算单加锁
      tool.lockBill(sqvos);

      // 检查是否可以传应收
      sqvos = SquareInvVOUtils.getInstance().filterIncomeableVO(sqvos);
      if (sqvos == null) {
        return;
      }

      // 销售发票差额传应收更新表体结算标志位
      new UpdateSquare32FlagBP().updateSquareBFlagForAdjustIncome(sqvos);

      // 差额传应收
      new ADIncomeFor32Action().execIncome(sqvos);
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
  }

  private SquareInvVO[] querySquareInvVOBy32ID(AbstractBill[] vos) {
    SquareInvVO[] sqvos =
        new QuerySquare32VOBP().querySquareInvVOBy32ID(SoVoTools
            .getVOPKValues(vos));
    if (VOChecker.isEmpty(sqvos)) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006010_0", "04006010-0062")/*@res "结算数据缺失，销售发票审核未生成待结算数据"*/);
    }
    return sqvos;
  }

}
