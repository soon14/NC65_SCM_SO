package nc.ui.so.salequotation.action;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;

import nc.ui.bd.pub.BDFuncletInitData;
import nc.ui.bd.pub.BDFuncletWindowLauncher;
import nc.ui.pub.bill.BillItem;
import nc.ui.pub.linkoperate.ILinkType;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.ui.so.salequotation.view.SalequoBillForm;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.UIState;
import nc.ui.uif2.model.AbstractAppModel;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.scmpub.res.SCMActionCode;
import nc.vo.so.salequotation.entity.AggSalequotationHVO;
import nc.vo.so.salequotation.entity.SalequotationHVO;

public class SalequoCustInfoAction extends NCAction {

  private static final long serialVersionUID = -9170011437208606106L;

  private SalequoBillForm editor;

  private AbstractAppModel model;

  public SalequoCustInfoAction() {
    super();
    SCMActionInitializer.initializeAction(this, SCMActionCode.SO_QUERYCUSTINFO);
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {
    AggSalequotationHVO vo = null;
    if (this.getModel().getUiState() == UIState.EDIT
        || this.getModel().getUiState() == UIState.ADD) {
      vo = (AggSalequotationHVO) this.editor.getValue();
    }
    else {
      vo = (AggSalequotationHVO) this.model.getSelectedData();
    }
    String custid = vo.getParentVO().getPk_customer();
    if (PubAppTool.isNull(custid)) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006009_0", "04006009-0072")/*@res "客户不能为空"*/);
    }

    BillItem billItem =
        this.editor.getBillCardPanel()
            .getHeadItem(SalequotationHVO.PK_CUSTOMER);

    String mdid = billItem.getMetaDataProperty().getRefBusinessEntity().getID();
    BDFuncletInitData initData =
        new BDFuncletInitData(mdid, ILinkType.LINK_TYPE_QUERY, custid);

    int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
    int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
    BDFuncletWindowLauncher.openFuncNodeDialog(null, initData, null, false,
        false, new Dimension(screenWidth, screenHeight), null);
  }

  public SalequoBillForm getEditor() {
    return this.editor;
  }

  public AbstractAppModel getModel() {
    return this.model;
  }

  public void setEditor(SalequoBillForm editor) {
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

}
