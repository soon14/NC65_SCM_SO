package nc.ui.so.m32.billui.editor.headevent;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailBeforeEditEvent;
import nc.ui.scmpub.ref.FilterBankRefUtils;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.vo.so.m32.entity.SaleInvoiceHVO;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>客户开户银行编辑事件
 * </ul>
 * <p>
 * 
 * @version 本版本号 6.0
 * @since
 * @author fengjb
 * @time 2010-8-6 下午03:13:22
 */
public class CustBankEditHandler {
  /**
   * 
   * 方法功能描述：客户开户银行编辑后事件，清空客户银行帐号。
   * <p>
   * <b>参数说明</b>
   * @param e
   * <p>
   * @author fengjb
   * @time 2010-9-10 上午10:08:19
   */
  public void afterEdit(CardHeadTailAfterEditEvent e) {
    BillCardPanel cardPanel = e.getBillCardPanel();
    CardKeyValue keyValue = new CardKeyValue(cardPanel);
    keyValue.setHeadValue(SaleInvoiceHVO.CCUSTBANKACCID, null);
  }

  /**
   * 
   * 方法功能描述：客户开户银行编辑前事件，根据客户设置约束条件。
   * <p>
   * <b>参数说明</b>
   * @param e
   * <p>
   * @author fengjb
   * @time 2010-9-10 上午10:07:48
   */
  public void beforeEdit(CardHeadTailBeforeEditEvent e) {
    BillCardPanel cardPanel = e.getBillCardPanel();
    CardKeyValue keyValue = new CardKeyValue(cardPanel);
    // 开票客户为空时，客户开户银行帐号参照不出来
    String invoicecust =
        keyValue.getHeadStringValue(SaleInvoiceHVO.CINVOICECUSTID);

    BillItem bankitem = cardPanel.getHeadItem(SaleInvoiceHVO.CCUSTBANKID);

    FilterBankRefUtils refUtil = new FilterBankRefUtils(bankitem);
    refUtil.filterItemRefByCust(invoicecust);
  }
}
