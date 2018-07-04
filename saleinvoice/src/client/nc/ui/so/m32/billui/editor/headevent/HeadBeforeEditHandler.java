package nc.ui.so.m32.billui.editor.headevent;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailBeforeEditEvent;
import nc.ui.so.m32.billui.rule.IsEditableCheckRule;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.vo.so.m32.entity.SaleInvoiceHVO;
import nc.vo.so.pub.enumeration.BillStatus;
import nc.vo.so.pub.keyvalue.IKeyValue;

/**
 * 
 * <p>
 * <b>本类主要完成以下功能：</b>
 * 
 * <ul>
 * <li>销售发票表头编辑前事件，设置参照约束性条件
 * </ul>
 * 
 * <p>
 * 
 * @version 本版本号 6.0
 * @since
 * @author fengjb
 * @time 2010-8-10 上午10:01:14
 */
public class HeadBeforeEditHandler implements
    IAppEventHandler<CardHeadTailBeforeEditEvent> {

  @Override
  public void handleAppEvent(CardHeadTailBeforeEditEvent e) {
    
    String editKey = e.getKey();
    // add by wangshu6 for 636 2015-01-09 销售发票修订支持修改 
    // 判断状态，审批中修改
    BillCardPanel cardPanel = e.getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);
    Integer status = keyValue.getHeadIntegerValue(SaleInvoiceHVO.FSTATUSFLAG);
    boolean isEditable = Boolean.FALSE;
    if (BillStatus.AUDITING.equalsValue(status)) {
      // 校验当前编辑字段是否可修改；
      isEditable =
          new IsEditableCheckRule().check(e.getBillCardPanel(), -1,
              e.getKey());
      if (!isEditable) {
        e.setReturnValue(Boolean.valueOf(Boolean.FALSE));
      }
    }
    // end 
    
    // 默认设为可编辑
    e.setReturnValue(Boolean.TRUE);
    // 折本汇率、全局折本汇率、集团折本汇率
    if (SaleInvoiceHVO.NEXCHANGERATE.equals(editKey)
        || SaleInvoiceHVO.NGLOBALEXCHGRATE.equals(editKey)
        || SaleInvoiceHVO.NGROUPEXCHGRATE.equals(editKey)) {
      ExchgRateEditHandler handler = new ExchgRateEditHandler();
      handler.beforeEdit(e);
    }
    // 交易类型
    else if (SaleInvoiceHVO.CTRANTYPEID.equals(editKey)) {
      TrantypeEditHandler handler = new TrantypeEditHandler();
      handler.beforeEdit(e);
    }
    // 客户开户银行
    else if (SaleInvoiceHVO.CCUSTBANKID.equals(editKey)) {
      CustBankEditHandler handler = new CustBankEditHandler();
      handler.beforeEdit(e);
    }
    // 开户银行帐号
    else if (SaleInvoiceHVO.CCUSTBANKACCID.equals(editKey)) {
      CustBankaccEditHandler handler = new CustBankaccEditHandler();
      handler.beforeEdit(e);
    }
    // 发票折扣
    else if (SaleInvoiceHVO.NHVOICEDISRATE.equals(editKey)) {
      NhvoicedisrateEditHandler handler = new NhvoicedisrateEditHandler();
      handler.beforeEdit(e);
    }
    // 币种
    else if (SaleInvoiceHVO.CORIGCURRENCYID.equals(editKey)) {
      CorigcurrencyidEditHandler handler = new CorigcurrencyidEditHandler();
      handler.beforeEdit(e);
    }
    else if(SaleInvoiceHVO.CINVOICECUSTID.equals(editKey)){
      InvoiceCustEditHandler handler=new InvoiceCustEditHandler();
      handler.beforeEdit(e);
    }

  }
}
