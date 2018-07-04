package nc.bs.so.m32.maintain.rule.update;

import nc.bs.scmpub.app.flow.billcode.BillCodeInfoBuilder;
import nc.bs.so.pub.rule.rowno.SORowNoUtil;
import nc.impl.pubapp.bill.billcode.BillCodeInfo;
import nc.impl.pubapp.bill.billcode.BillCodeUtils;
import nc.impl.pubapp.pattern.rule.ICompareRule;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFDate;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m32.entity.SaleInvoiceBVO;
import nc.vo.so.m32.entity.SaleInvoiceHVO;
import nc.vo.so.m32.entity.SaleInvoiceVO;
import nc.vo.so.m32.util.HeadTotalCalcUtil;
import nc.vo.trade.checkrule.VOChecker;

/**
 * @description
 * 销售发票修改保存操作前修改保存时填充默认值
 * @scene
 * 销售发票修改保存前
 * @param
 * 无
 * @since 6.0
 * @version 2011-8-15 上午09:07:02
 * @author 么贵敬
 */
public class FillUpdateDefaultRule implements ICompareRule<SaleInvoiceVO> {

  @Override
  public void process(SaleInvoiceVO[] vos, SaleInvoiceVO[] originVOs) {

    for (SaleInvoiceVO invoicevo : vos) {
      // 填充修改保存时默认值
      this.setHeadDefault(invoicevo);
      this.setBodyDefault(invoicevo);
      // 填空行号
      SORowNoUtil.fillupRowNo(invoicevo);
    }
    // 填充单据号
    this.setBillCode(vos, originVOs);

  }

  /**
   * 方法功能描述：修改保存前单据号处理。
   * <p>
   * <b>参数说明</b>
   * 
   * @param vos
   * @param originVOs
   *          <p>
   * @author 冯加滨
   * @time 2010-1-21 下午06:59:14
   */
  private void setBillCode(SaleInvoiceVO[] vos, SaleInvoiceVO[] originVOs) {
    BillCodeInfo info =
        BillCodeInfoBuilder.buildBillCodeInfo(SOBillType.Invoice.getCode(),
            SaleInvoiceHVO.VBILLCODE, SaleInvoiceHVO.PK_GROUP,
            SaleInvoiceHVO.PK_ORG, SaleInvoiceHVO.VTRANTYPECODE);
    BillCodeUtils util = new BillCodeUtils(info);

    util.upadteBillCode(vos, originVOs);

  }

  /**
   * 方法功能描述：修改保存前表体默认值填充。
   * <p>
   * <b>参数说明</b>
   * 
   * @param invoicevo
   *          <p>
   * @author 冯加滨
   * @time 2010-1-21 下午06:59:37
   */
  private void setBodyDefault(SaleInvoiceVO invoicevo) {
    if (VOChecker.isEmpty(invoicevo.getChildrenVO())) {
      return;
    }

    // 使用表头开票组织、单据日期填充表体
    String invoiceorg = invoicevo.getParentVO().getPk_org();
    String pk_group = invoicevo.getParentVO().getPk_group();
    UFDate billdate = invoicevo.getParentVO().getDbilldate();
    for (SaleInvoiceBVO bvo : invoicevo.getChildrenVO()) {
      if (bvo.getStatus() == VOStatus.NEW) {
        bvo.setPk_org(invoiceorg);
        bvo.setPk_group(pk_group);
        bvo.setDbilldate(billdate);
      }
    }
  }

  /**
   * 方法功能描述：修改保存前表头默认值填充。
   * <p>
   * <b>参数说明</b>
   * 
   * @param invoicevo
   *          <p>
   * @author 冯加滨
   * @time 2010-1-21 下午07:00:02
   */
  private void setHeadDefault(SaleInvoiceVO voInvoice) {

    // SaleInvoiceHVO head = voInvoice.getParentVO();

    // AuditInfoUtils.setUpdateAuditInfo(head);

    // 合计数量 、金额
    HeadTotalCalcUtil.getInstance().calcHeadTotalValue(new SaleInvoiceVO[] {
      voInvoice
    });

  }

}
