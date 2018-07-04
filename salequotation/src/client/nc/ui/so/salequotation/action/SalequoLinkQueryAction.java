package nc.ui.so.salequotation.action;

import java.awt.event.ActionEvent;

import nc.ui.pub.beans.UIDialog;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.pubapp.uif2app.view.BillForm;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.ui.trade.billgraph.SourceBillFlowDlg;
import nc.ui.uif2.NCAction;
import nc.vo.scmpub.res.SCMActionCode;
import nc.vo.so.salequotation.entity.AggSalequotationHVO;
import nc.vo.so.salequotation.entity.SalequotationHVO;

public class SalequoLinkQueryAction extends NCAction {

  /**
   * 
   */
  private static final long serialVersionUID = -4487842315129887417L;

  private String billType;

  private BillForm editor;

  private BillManageModel model;

  public SalequoLinkQueryAction() {
    super();
    SCMActionInitializer
        .initializeAction(this, SCMActionCode.SCM_BILLLINKQUERY);
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {
    AggSalequotationHVO aggVO =
        (AggSalequotationHVO) this.model.getSelectedData();
    if (aggVO != null) {
      SalequotationHVO hvo = aggVO.getParentVO();
      if (hvo == null) {
        return;
      }
      String billID = hvo.getPrimaryKey();
      String billNO = hvo.getVbillcode();
      SourceBillFlowDlg billFlowDlG =
          new SourceBillFlowDlg(null, this.billType, billID, billNO);
      if (billFlowDlG.showModal() == UIDialog.ID_OK) {
        // do nothing
        return;
      }
    }
  }

  public String getBillType() {
    return this.billType;
  }

  public BillForm getEditor() {
    return this.editor;
  }

  public BillManageModel getModel() {
    return this.model;
  }

  public void setBillType(String billType) {
    this.billType = billType;
  }

  public void setEditor(BillForm editor) {
    this.editor = editor;
  }

  public void setModel(BillManageModel model) {
    this.model = model;
    model.addAppEventListener(this);
  }

  @Override
  protected boolean isActionEnable() {
    AggSalequotationHVO aggVO =
        (AggSalequotationHVO) this.model.getSelectedData();
    if (aggVO != null) {
      SalequotationHVO hvo = (SalequotationHVO) aggVO.getParentVO();
      if (hvo != null) {
        return true;
      }
    }
    return false;
  }

}
