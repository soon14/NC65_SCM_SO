package nc.bs.so.m32.maintain.rule.insert;

import nc.bs.scmpub.app.flow.billcode.BillCodeInfoBuilder;
import nc.bs.so.pub.rule.rowno.SORowNoUtil;
import nc.impl.pubapp.bill.billcode.BillCodeInfo;
import nc.impl.pubapp.bill.billcode.BillCodeUtils;
import nc.impl.pubapp.pattern.database.DBTool;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pubapp.AppContext;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m32.entity.SaleInvoiceBVO;
import nc.vo.so.m32.entity.SaleInvoiceHVO;
import nc.vo.so.m32.entity.SaleInvoiceVO;
import nc.vo.so.m32.rule.BodyUpdateByHead;
import nc.vo.so.m32.util.HeadTotalCalcUtil;
import nc.vo.so.pub.enumeration.BillStatus;
import nc.vo.so.pub.keyvalue.VOKeyValue;
import nc.vo.trade.checkrule.VOChecker;

/**
 * @description
 * 销售发票保存前填充默认值
 * @scene
 * 销售发票新增保存前
 * @param
 * 无
 * @since 6.3
 * @version 2012-12-21 上午08:59:00
 * @author yaogj
 */
public class FillNewDefaultRule implements IRule<SaleInvoiceVO> {

  @Override
  public void process(SaleInvoiceVO[] invoices) {

    for (SaleInvoiceVO invoicevo : invoices) {
      // 填充新增时默认值
      this.setHeadDefault(invoicevo);
      this.setBodyDefault(invoicevo);

      // 填空行号
      SORowNoUtil.fillupRowNo(invoicevo);
    }
    // 填充单据号
    this.setBillCode(invoices);
    // 填充单据主键（为了能处理销售发票自动费用冲抵回写）
    this.setBillIDs(invoices);

  }

  /**
   * 方法功能描述：新增时填充发票单据号。
   * <p>
   * <b>参数说明</b>
   * 
   * @param invoicevo
   *          <p>
   * @author 冯加滨
   * @time 2010-1-21 下午02:43:32
   */
  private void setBillCode(SaleInvoiceVO[] invoices) {

    BillCodeInfo info =
        BillCodeInfoBuilder.buildBillCodeInfo(SOBillType.Invoice.getCode(),
            SaleInvoiceHVO.VBILLCODE, SaleInvoiceHVO.PK_GROUP,
            SaleInvoiceHVO.PK_ORG, SaleInvoiceHVO.VTRANTYPECODE);
    BillCodeUtils util = new BillCodeUtils(info);
    util.createBillCode(invoices);

  }

  /**
   * 补充单据的表头ID 在自动冲抵的时候使用
   * 
   * @param invoices 发票vos
   */
  private void setBillIDs(SaleInvoiceVO[] invoices) {
    for (SaleInvoiceVO vo : invoices) {
      SaleInvoiceHVO hvo = vo.getParentVO();
      SaleInvoiceBVO[] bvos = vo.getChildrenVO();
      if (null != hvo.getPrimaryKey()) {
        continue;
      }
      int len = bvos.length;
      DBTool dao = new DBTool();
      String[] hid = dao.getOIDs(1);
      hvo.setCsaleinvoiceid(hid[0]);
      for (int i = 0; i < len; i++) {
        bvos[i].setCsaleinvoiceid(hid[0]);
      }
    }
  }

  /**
   * 方法功能描述：设置发票子表新增默认值。
   * <p>
   * <b>参数说明</b>
   * 
   * @param invoice
   *          <p>
   * @author 冯加滨
   * @time 2010-1-21 下午02:18:23
   */
  private void setBodyDefault(SaleInvoiceVO invoice) {
    // 使用表头开票组织、单据日期填充表体
    VOKeyValue<SaleInvoiceVO> keyValue = new VOKeyValue<SaleInvoiceVO>(invoice);
    BodyUpdateByHead rule = new BodyUpdateByHead(keyValue);
    String[] redunKeys = new String[] {
      SaleInvoiceBVO.PK_ORG, SaleInvoiceBVO.DBILLDATE, SaleInvoiceBVO.PK_GROUP
    };
    rule.updateAllBodyRedunValue(redunKeys);
  }

  /**
   * 方法功能描述：设置发票主表新增默认值。
   * <p>
   * <b>参数说明</b>
   * 
   * @param parent
   *          <p>
   * @author fengjb
   * @time 2010-1-20 上午09:52:18
   */
  private void setHeadDefault(SaleInvoiceVO invoice) {

    SaleInvoiceHVO head = invoice.getParentVO();
    String groupid = AppContext.getInstance().getPkGroup();

    // 集团
    if (VOChecker.isEmpty(head.getPk_group())) {
      head.setPk_group(groupid);
    }
    // 状态
    if (null == head.getFstatusflag()) {
      head.setFstatusflag(BillStatus.FREE.getIntegerValue());
    }
    // 合计数量 、金额
    HeadTotalCalcUtil.getInstance().calcHeadTotalValue(new SaleInvoiceVO[] {
      invoice
    });

  }
}
