package nc.ui.so.m32.billui.action.link;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;

import nc.ui.bd.pub.BDFuncletInitData;
import nc.ui.bd.pub.BDFuncletWindowLauncher;
import nc.ui.pub.bill.BillItem;
import nc.ui.pub.linkoperate.ILinkType;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.ui.so.m32.billui.view.SaleInvoiceEditor;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.UIState;
import nc.ui.uif2.model.AbstractAppModel;
import nc.vo.scmpub.res.SCMActionCode;
import nc.vo.so.m32.entity.SaleInvoiceHVO;
import nc.vo.so.m32.entity.SaleInvoiceVO;

public class QueryCustInfoAction extends NCAction {

  /**
     * 
     */
  private static final long serialVersionUID = -5703244765698988233L;

  private SaleInvoiceEditor editor;

  private AbstractAppModel model;

  public QueryCustInfoAction() {
    super();
    this.initializeAction();
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {
    SaleInvoiceVO vo = null;
    if (this.getModel().getUiState() == UIState.EDIT
        || this.getModel().getUiState() == UIState.ADD) {
      vo = (SaleInvoiceVO) this.editor.getValue();
    }
    else {
      vo = (SaleInvoiceVO) this.model.getSelectedData();
    }
    String custid = vo.getParentVO().getCinvoicecustid();

    BillItem billItem =
        this.editor.getBillCardPanel().getHeadItem(
            SaleInvoiceHVO.CINVOICECUSTID);

    String mdid = billItem.getMetaDataProperty().getRefBusinessEntity().getID();
    BDFuncletInitData initData =
        new BDFuncletInitData(mdid, ILinkType.LINK_TYPE_QUERY, custid);

    int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
    int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
    BDFuncletWindowLauncher.openFuncNodeDialog(null, initData, null, false,
        false, new Dimension(screenWidth, screenHeight), null);
  }

  public SaleInvoiceEditor getEditor() {
    return this.editor;
  }

  public AbstractAppModel getModel() {
    return this.model;
  }

  public void setEditor(SaleInvoiceEditor editor) {
    this.editor = editor;
  }

  public void setModel(AbstractAppModel model) {
    this.model = model;
    model.addAppEventListener(this);
  }

  @Override
  protected boolean isActionEnable() {
    boolean isEnable = false;
    if (null != this.model.getSelectedData()) {
      isEnable = true;
    }
    return isEnable;
  }

  private void initializeAction() {
    SCMActionInitializer.initializeAction(this, SCMActionCode.SO_QUERYCUSTINFO);
  }
}
