package nc.ui.so.report.tbb.m32;

import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.vo.pubapp.AppContext;

public class SaleinvoiceDetailEditor extends ShowUpableBillForm {
  private static final long serialVersionUID = 1L;

  @Override
  public void initUI() {
    this.setShowOrgPanel(false);
    super.initUI();
    String pk_group = AppContext.getInstance().getPkGroup();
    InvoiceDetailPrecion.getInstance().setCardPrecision(pk_group,
        this.getBillCardPanel());
  }
}
