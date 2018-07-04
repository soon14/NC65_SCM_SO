package nc.bs.so.m30.rule.unapprove;

import java.util.Map;

import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.pubapp.pub.power.BillPowerChecker;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.scmpub.util.StringUtil;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.pub.enumeration.BillStatus;
import nc.vo.so.pub.util.ArrayUtil;

import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;

import nc.pubitf.ic.m4c.I4CQueryPubService;
import nc.pubitf.scmf.dm.m4816.IPrePaidServiceForM30;
import nc.pubitf.so.m32.so.m30.IQuery32For30;

import nc.bs.framework.common.NCLocator;
import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.rule.IRule;

/**
 * 
 * @description
 *              销售订单取消审批前检查是否可以弃审
 * @scene
 *        销售订单取消审批前
 * @param 无
 */
public class CheckUnApprovableRule implements IRule<SaleOrderVO> {

  @Override
  public void process(SaleOrderVO[] bills) {
    for (SaleOrderVO bill : bills) {
      SaleOrderHVO header = bill.getParentVO();
      if (!BillStatus.AUDIT.equalsValue(header.getFstatusflag())
          && !BillStatus.AUDITING.equalsValue(header.getFstatusflag())
            && !BillStatus.NOPASS.equalsValue(header.getFstatusflag())) {

        ExceptionUtils
            .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
                .getStrByID("4006011_0", "04006011-0074")/*@res "单据为非审批状态或审批中状态，不可进行弃审！"*/);
      }
      // 审批者权限

      boolean ishaveper =
          BillPowerChecker.hasApproverPermission(bill,
              SOBillType.Order.getCode());
      if (!ishaveper) {
        ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
            .getStrByID("4006011_0", "04006011-0432")/*当前用户对单据不具有审批者权限.*/);
      }
      // 是否存在下游单据
      this.checkIsExistPostBusiness(bill);

      // add by wangshu6 审批前校验当前单据的版本号与修订数据最大版本号，如不一致，不允许审批
      String reviseLastesIversion = this.getReviseLatestIversion(bill);
      if(StringUtil.isEmptyTrimSpace(reviseLastesIversion)){
        return;
      }
      Integer iversion = bill.getParentVO().getIversion();
      int newiversion=-1;
      if(iversion!=null){
        newiversion=iversion.intValue();
      }
      if (newiversion!=Integer.parseInt(reviseLastesIversion)) {
        ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance().getStrByID("4006011_0", "04006011-0516")/*当前销售订单有未审批的修订新版本，不可进行弃审！*/);
      }
    }
  }

  /**
   * 获得当前单据的修订最高版本号
   * 
   * @param bill
   */
  private String getReviseLatestIversion(SaleOrderVO bill) {

    String reviseLastesIversion = this.queryMaxIversionFromOrderHistory(bill);
    if (reviseLastesIversion == null) {
      return null;
    }
    return reviseLastesIversion;

  }

  /**
   * 从销售订单修订表中查询最新版本号
   * 
   * @param vos 销售订单修订vo
   * @return 版本号
   */
  public String queryMaxIversionFromOrderHistory(SaleOrderVO vo) {

    String[] iversions = null;
    SaleOrderHVO head = vo.getParentVO();
    String csaleorderid = head.getCsaleorderid();
    SqlBuilder sql = new SqlBuilder();
    sql.append("select iversion ");
    sql.append("from so_orderhistory where csaleorderid = '");
    sql.append(csaleorderid);
    sql.append("' and iversion = (select max(iversion) from so_orderhistory");
    sql.append(" where csaleorderid = '");
    sql.append(csaleorderid);
    sql.append("')");
    sql.append(" and dr = 0");

    DataAccessUtils dataUtil = new DataAccessUtils();
    IRowSet set = dataUtil.query(sql.toString());
    iversions = set.toOneDimensionStringArray();
    if (ArrayUtil.isEmpty(iversions)) {
      return null;
    }
    return iversions[0];
  }

  private void checkIsExistPostBusiness(SaleOrderVO billvo) {
    UFBoolean iscoop = billvo.getParentVO().getBcooptopoflag();
    if (null != iscoop && iscoop.booleanValue()) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006011_0", "04006011-0075")/*@res "销售订单已经协同生成采购订单，不能弃审。"*/);
    }
    SaleOrderBVO[] bodyvos = billvo.getChildrenVO();
    for (SaleOrderBVO bodyvo : bodyvos) {
      // 累计开票数量
      UFDouble ntotalinvoicenum = bodyvo.getNtotalinvoicenum();
      if (MathTool.compareTo(ntotalinvoicenum, UFDouble.ZERO_DBL) != 0) {
        ExceptionUtils
            .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
                .getStrByID("4006011_0", "04006011-0076")/*@res "订单已经开票，不可进行弃审！"*/);
      }
      // 累计应发出库数量
      UFDouble ntotalnotoutnum = bodyvo.getNtotalnotoutnum();
      if (MathTool.compareTo(ntotalnotoutnum, UFDouble.ZERO_DBL) != 0) {
        ExceptionUtils
            .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
                .getStrByID("4006011_0", "04006011-0077")/*@res "订单已经应发出库，不可进行弃审！"*/);
      }
      // 累计实发出库数量
      UFDouble ntotaloutnum = bodyvo.getNtotaloutnum();
      if (MathTool.compareTo(ntotaloutnum, UFDouble.ZERO_DBL) != 0) {
        ExceptionUtils
            .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
                .getStrByID("4006011_0", "04006011-0078")/*@res "订单已经实发出库，不可进行弃审！"*/);
      }
      // 累计发货数量
      UFDouble ntotalsendnum = bodyvo.getNtotalsendnum();
      if (MathTool.compareTo(ntotalsendnum, UFDouble.ZERO_DBL) != 0) {
        ExceptionUtils
            .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
                .getStrByID("4006011_0", "04006011-0079")/*@res "订单已经发货，不可进行弃审！"*/);
      }
      // 累计退货数量
      UFDouble ntotalreturnnum = bodyvo.getNtotalreturnnum();
      if (MathTool.compareTo(ntotalreturnnum, UFDouble.ZERO_DBL) != 0) {
        ExceptionUtils
            .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
                .getStrByID("4006011_0", "04006011-0080")/*@res "订单已经退货，不可进行弃审！"*/);
      }
      // 累计安排采购订单数量
      UFDouble narrangeponum = bodyvo.getNarrangeponum();
      if (MathTool.compareTo(narrangeponum, UFDouble.ZERO_DBL) != 0) {
        ExceptionUtils
            .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
                .getStrByID("4006011_0", "04006011-0081")/*@res "订单已经生成采购订单，不可进行弃审！"*/);
      }

      // 累计安排调入申请数量
      UFDouble narrangetoappnum = bodyvo.getNarrangetoappnum();
      if (MathTool.compareTo(narrangetoappnum, UFDouble.ZERO_DBL) != 0) {
        ExceptionUtils
            .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
                .getStrByID("4006011_0", "04006011-0072")/*@res "订单已经生成调入申请，不可进行弃审！"*/);
      }
      // 累计安排请购单数量
      UFDouble narrangepoappnum = bodyvo.getNarrangepoappnum();
      if (MathTool.compareTo(narrangepoappnum, UFDouble.ZERO_DBL) != 0) {
        ExceptionUtils
            .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
                .getStrByID("4006011_0", "04006011-0073")/*@res "订单已经生成请购单，不可进行弃审！"*/);
      }
      // 累计安排调拨订单数量
      UFDouble narrangetoornum = bodyvo.getNarrangetoornum();
      if (MathTool.compareTo(narrangetoornum, UFDouble.ZERO_DBL) != 0) {
        ExceptionUtils
            .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
                .getStrByID("4006011_0", "04006011-0082")/*@res "订单已经生成调拨订单，不可进行弃审！"*/);
      }
      // 累计安排委外订单数量
      UFDouble narrangescornum = bodyvo.getNarrangescornum();
      if (MathTool.compareTo(narrangescornum, UFDouble.ZERO_DBL) != 0) {
        ExceptionUtils
            .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
                .getStrByID("4006011_0", "04006011-0083")/*@res "订单已经生成委外订单，不可进行弃审！"*/);
      }
      // 累计安排生产订单数量
      UFDouble narrangemonum = bodyvo.getNarrangemonum();
      if (MathTool.compareTo(narrangemonum, UFDouble.ZERO_DBL) != 0) {
        ExceptionUtils
            .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
                .getStrByID("4006011_0", "04006011-0084")/*@res "订单已经生成生产订单，不可进行弃审！"*/);
      }
      // 累计安排进口合同数量
      UFDouble narrangeitcnum = bodyvo.getNarrangeitcnum();
      if (MathTool.compareTo(narrangeitcnum, UFDouble.ZERO_DBL) != 0) {
        ExceptionUtils
            .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
                .getStrByID("4006011_0", "04006011-0480")/*@res "订单已经生成进口合同，不可进行弃审！"*/);
      }
      // 累计生成计划订单数量
      UFDouble ntotalplonu = bodyvo.getNtotalplonum();
      if (MathTool.compareTo(ntotalplonu, UFDouble.ZERO_DBL) != 0) {
        ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
            .getStrByID("4006011_0", "04006011-0449")/*订单已经生成计划订单，不可进行弃审！*/);
      }
    }
    // 调用接口检查是否存在下游出库单、发票
    String orderhid = billvo.getParentVO().getCsaleorderid();
    String[] orderhids = new String[] {
      orderhid
    };
    try {
      if (SysInitGroupQuery.isICEnabled()) {
        I4CQueryPubService outqrysrv =
            NCLocator.getInstance().lookup(I4CQueryPubService.class);
        Map<String, UFBoolean> map4cexist =
            outqrysrv.existBill(orderhids, true, SOBillType.Order.getCode());
        UFBoolean isexit4c = map4cexist.get(orderhid);
        if (isexit4c.booleanValue()) {
          ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
              .getStrByID("4006011_0", "04006011-0433")/*订单已经出库，不可进行弃审*/);
        }
      }

      IQuery32For30 invqrysrv =
          NCLocator.getInstance().lookup(IQuery32For30.class);
      Map<String, UFBoolean> map32exist =
          invqrysrv.isExistNextInvoice(orderhids);
      UFBoolean isexit32 = map32exist.get(orderhid);
      if (isexit32.booleanValue()) {
        ExceptionUtils
            .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
                .getStrByID("4006011_0", "04006011-0076")/*@res "订单已经开票，不可进行弃审！"*/);
      }

      if (SysInitGroupQuery.isDMEnabled()) {
        // 代垫费用单
        IPrePaidServiceForM30 prepaidsrv =
            NCLocator.getInstance().lookup(IPrePaidServiceForM30.class);
        boolean isnextinvoice = false;

        isnextinvoice = prepaidsrv.hasNextInvoice(orderhids);

        if (isnextinvoice) {
          ExceptionUtils
              .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
                  .getNCLangRes().getStrByID("4006011_0", "04006011-0085")/*@res "订单已经生成代垫费用单，不可进行弃审！"*/);
        }
      }
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }
}
