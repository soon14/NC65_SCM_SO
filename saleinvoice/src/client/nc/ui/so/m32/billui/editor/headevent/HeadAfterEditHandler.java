package nc.ui.so.m32.billui.editor.headevent;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.so.m32.billui.view.SaleInvoiceEditor;
import nc.vo.so.m32.entity.SaleInvoiceHVO;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>销售发票表头编辑后事件，完成发票表头字段编辑后续处理
 * </ul>
 * <p>
 * 
 * @version 本版本号 6.0
 * @since 上一版本号 5.6
 * @author 冯加滨
 * @time 2010-3-1 下午03:01:17
 */
public class HeadAfterEditHandler implements
    IAppEventHandler<CardHeadTailAfterEditEvent> {

  private SaleInvoiceEditor editor;

  private BillManageModel model;

  public SaleInvoiceEditor getEditor() {
    return this.editor;
  }

  public BillManageModel getModel() {
    return this.model;
  }

  @Override
  public void handleAppEvent(CardHeadTailAfterEditEvent e) {
    // 效率优化，减少合计次数
    BillCardPanel cardPanel = e.getBillCardPanel();
    cardPanel.getBillModel().setNeedCalculate(false);

    // 表头编辑字段
    String editkey = e.getKey();
    // 单据日期
    if (SaleInvoiceHVO.DBILLDATE.equals(editkey)) {
      BillDateEditHandler handler = new BillDateEditHandler();
      handler.afterEdit(e);
    }
    // 发票折扣
    else if (SaleInvoiceHVO.NHVOICEDISRATE.equals(editkey)) {
      InvoiceDisEditHandler handler = new InvoiceDisEditHandler();
      handler.afterEdit(e);
    }
    // 开票客户
    else if (SaleInvoiceHVO.CINVOICECUSTID.equals(editkey)) {
      InvoiceCustEditHandler handler = new InvoiceCustEditHandler();
      handler.afterEdit(e);

    }
    // 客户开户银行
    else if (SaleInvoiceHVO.CCUSTBANKID.equals(editkey)) {
      CustBankEditHandler handler = new CustBankEditHandler();
      handler.afterEdit(e);
    }
    // 客户银行帐号
    else if (SaleInvoiceHVO.CCUSTBANKACCID.equals(editkey)) {
      CustBankaccEditHandler handler = new CustBankaccEditHandler();
      handler.afterEdit(e);
    }
    // 原币币种
    else if (SaleInvoiceHVO.CORIGCURRENCYID.equals(editkey)) {
      OrigCurrencyEditHandler handler = new OrigCurrencyEditHandler();
      handler.afterEdit(e);
    }
    // 折本汇率、集团本位币汇率、全局本位币汇率
    else if (SaleInvoiceHVO.NEXCHANGERATE.equals(editkey)
        || SaleInvoiceHVO.NGLOBALEXCHGRATE.equals(editkey)
        || SaleInvoiceHVO.NGROUPEXCHGRATE.equals(editkey)) {
      ExchgRateEditHandler handler = new ExchgRateEditHandler();
      handler.afterEdit(e);
    }
    // 冲抵金额
    else if (SaleInvoiceHVO.NTOTALORIGSUBMNY.equals(editkey)) {
      TotalOrigSubMnyEditHandler handler = new TotalOrigSubMnyEditHandler();
      handler.afterEdit(e, this.getEditor(), this.getModel());
    }
    // 效率优化，减少合计次数
    cardPanel.getBillModel().setNeedCalculate(true);
  }

  public void setEditor(SaleInvoiceEditor editor) {
    this.editor = editor;
  }

  public void setModel(BillManageModel model) {
    this.model = model;
  }

}
