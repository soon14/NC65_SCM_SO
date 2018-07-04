package nc.ui.so.m32.billref.ic.vmi.impl;

import nc.ui.pub.beans.UIDialog;
import nc.ui.so.m32.billref.ic.vmi.InvoiceToVmiQueryDlgContainer;
import nc.ui.so.m32.billref.ic.vmi.SaleInvoiceVmiDLG;
import nc.ui.so.m32.billref.ic.vmi.itf.IInvoiceToVmiControl;
import nc.ui.uif2.model.AbstractAppModel;
import nc.vo.ic.m50.entity.VmiSumGenerateParam;
import nc.vo.so.m32.entity.SaleInvoiceViewVO;

public class InvoiceToVmiControl implements IInvoiceToVmiControl {

  private AbstractAppModel model;

  private InvoiceToVmiQueryDlgContainer querydlgcontainer;

  private SaleInvoiceVmiDLG vmiDlg;

  public InvoiceToVmiQueryDlgContainer getQuerydlgcontainer() {
    if (null == this.querydlgcontainer) {
      this.querydlgcontainer =
          new InvoiceToVmiQueryDlgContainer(this.model.getContext());
    }
    return this.querydlgcontainer;
  }

  @Override
  public SaleInvoiceViewVO[] getSelectedVOs() {
    if (null == this.vmiDlg) {
      return null;
    }
    return this.vmiDlg.getSelectedVOs();
  }

  @Override
  public VmiSumGenerateParam getVmiSumGenerateParam() {
    return this.getQuerydlgcontainer().getVmiSumGenerateParam();
  }

  @Override
  public void queryAndLoadInvoice(AbstractAppModel pmodel) {
    this.model = pmodel;

    if (UIDialog.ID_OK == this.getQuerydlgcontainer().getQryCondDLGDelegator()
        .showModal()) {
      this.vmiDlg = new SaleInvoiceVmiDLG(this.getQuerydlgcontainer());
      this.vmiDlg.showModal();
    }
  }

  public void setQuerydlgcontainer(
      InvoiceToVmiQueryDlgContainer querydlgcontainer) {
    this.querydlgcontainer = querydlgcontainer;
  }

}
