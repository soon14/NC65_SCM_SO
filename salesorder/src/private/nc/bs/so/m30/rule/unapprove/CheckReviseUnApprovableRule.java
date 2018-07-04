package nc.bs.so.m30.rule.unapprove;

import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.pubapp.pub.power.BillPowerChecker;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.pub.enumeration.BillStatus;

/**
 * @description
 *  销售订单修订取消审批前检查是否可以弃审
 * @scene
 *  销售订单修订取消审批前
 * @param
 * 
 *
 * @since 6.3
 * @version 2015-1-9 下午3:03:33
 * @author wangshu6
 */
public class CheckReviseUnApprovableRule implements IRule<SaleOrderVO> {

  @Override
  public void process(SaleOrderVO[] bills) {
    for (SaleOrderVO bill : bills) {
      SaleOrderHVO header = bill.getParentVO();
      if (!BillStatus.AUDIT.equalsValue(header.getFstatusflag())
          && !BillStatus.AUDITING.equalsValue(header.getFstatusflag())
          && !BillStatus.NOPASS.equalsValue(header.getFstatusflag())) {

        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4006011_0", "04006011-0074")/*
                                                                     * @res
                                                                     * "单据为非审批状态或审批中状态，不可进行弃审！"
                                                                     */);
      }
      // 审批者权限

      boolean ishaveper =
          BillPowerChecker.hasApproverPermission(bill,
              SOBillType.Order.getCode());
      if (!ishaveper) {
        ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
            .getStrByID("4006011_0", "04006011-0432")/* 当前用户对单据不具有审批者权限. */);
      }

      // 销售订单修改后，订单修订应该不允许弃审（暂时控制删除和删行）
      int bodyrownum = bill.getChildrenVO().length;
      String csaleorderid = header.getCsaleorderid();
      DataAccessUtils queryUtil = new DataAccessUtils();
      SqlBuilder sql = new SqlBuilder();
      sql.append("select so_saleorder.csaleorderbid from so_saleorder_b so_saleorder where ");
      sql.append("so_saleorder.csaleorderid", csaleorderid);
      sql.append(" and ");
      sql.append("so_saleorder.dr", 0);
      IRowSet result = queryUtil.query(sql.toString());
      if (result.size() == 0 || bodyrownum != result.size()) {
        ExceptionUtils
            .wrappBusinessException(NCLangResOnserver.getInstance().getStrByID(
                "4006011_0", "04006011-0527")/* 销售订单已被删除或者删行，请在列表界面刷新单据. */);
      }
    }
  }
}
