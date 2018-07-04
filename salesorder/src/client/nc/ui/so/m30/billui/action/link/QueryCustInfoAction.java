package nc.ui.so.m30.billui.action.link;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;

import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.scmpub.res.SCMActionCode;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;

import nc.desktop.ui.WorkbenchEnvironment;

import nc.ui.bd.pub.BDFuncletInitData;
import nc.ui.bd.pub.BDFuncletWindowLauncher;
import nc.ui.ml.NCLangRes;
import nc.ui.pub.bill.BillItem;
import nc.ui.pub.linkoperate.ILinkType;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.ui.so.m30.billui.view.SaleOrderBillForm;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.UIState;
import nc.ui.uif2.model.AbstractAppModel;

public class QueryCustInfoAction extends NCAction {

  /**
     * 
     */
  private static final long serialVersionUID = -5703244765698988233L;

  /** 订单卡控件 */
  private SaleOrderBillForm editor;

  private AbstractAppModel model;

  public QueryCustInfoAction() {
    super();
    this.initializeAction();
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {
    SaleOrderVO vo = null;
    if (this.getModel().getUiState() == UIState.EDIT
        || this.getModel().getUiState() == UIState.ADD) {
      vo = (SaleOrderVO) this.editor.getValue();
    }
    else {
      vo = (SaleOrderVO) this.model.getSelectedData();
    }
    String custid = vo.getParentVO().getCcustomerid();
    if (PubAppTool.isNull(custid)) {
      ExceptionUtils.wrappBusinessException(NCLangRes.getInstance().getStrByID(
          "4006011_0", "04006011-0431")/*客户不能为空*/);
    }
    BillItem billItem =
        this.editor.getBillCardPanel().getHeadItem(SaleOrderHVO.CCUSTOMERID);

    String mdid = billItem.getMetaDataProperty().getRefBusinessEntity().getID();
    BDFuncletInitData initData =
        new BDFuncletInitData(mdid, ILinkType.LINK_TYPE_QUERY, custid);

    int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
    int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
    BDFuncletWindowLauncher.openFuncNodeDialog(WorkbenchEnvironment
        .getInstance().getWorkbench().getParent(), initData, null, false,
        false, new Dimension(screenWidth, screenHeight), null);

  }

  public SaleOrderBillForm getEditor() {
    return this.editor;
  }

  public AbstractAppModel getModel() {
    return this.model;
  }

  public void setEditor(SaleOrderBillForm editor) {
    this.editor = editor;
  }

  public void setModel(AbstractAppModel model) {
    this.model = model;
    model.addAppEventListener(this);
  }

  @Override
  protected boolean isActionEnable() {
    boolean broenable =
        this.model.getUiState() == UIState.NOT_EDIT
            && this.model.getSelectedData() != null;

    return broenable || this.model.getUiState() == UIState.EDIT
        || this.model.getUiState() == UIState.ADD;
  }

  private void initializeAction() {
    SCMActionInitializer.initializeAction(this, SCMActionCode.SO_QUERYCUSTINFO);
  }
}
