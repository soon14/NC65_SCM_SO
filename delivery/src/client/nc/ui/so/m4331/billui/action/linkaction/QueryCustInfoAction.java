package nc.ui.so.m4331.billui.action.linkaction;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.util.HashSet;
import java.util.Set;

import nc.ui.bd.pub.BDFuncletInitData;
import nc.ui.bd.pub.BDFuncletWindowLauncher;
import nc.ui.pub.bill.BillItem;
import nc.ui.pub.linkoperate.ILinkType;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.ui.so.m4331.billui.model.DeliveryManageModel;
import nc.ui.so.m4331.billui.view.DeliveryEditor;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.UIState;
import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.SCMActionCode;
import nc.vo.so.m4331.entity.DeliveryBVO;
import nc.vo.so.m4331.entity.DeliveryVO;

public class QueryCustInfoAction extends NCAction {

  private static final long serialVersionUID = -5703244765698988233L;

  private DeliveryEditor editor;

  private DeliveryManageModel model;

  public QueryCustInfoAction() {
    super();
    this.initializeAction();
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {
    DeliveryVO vo = null;
    if (this.getModel().getUiState() == UIState.EDIT
        || this.getModel().getUiState() == UIState.ADD) {
      vo = (DeliveryVO) this.editor.getValue();
    }
    else {
      vo = (DeliveryVO) this.model.getSelectedData();
    }
    Set<String> custids = new HashSet<String>();

    int selectrow =
        this.editor.getBillCardPanel().getBillTable().getSelectedRow();
    if (selectrow == -1) {
      DeliveryBVO[] bvos = vo.getChildrenVO();
      for (DeliveryBVO bvo : bvos) {
        custids.add(bvo.getCordercustid());
      }
    }
    else {
      custids.add(vo.getChildrenVO()[selectrow].getCordercustid());
    }
    if (custids.size() > 1) {
      ExceptionUtils.wrappBusinessException(NCLangRes4VoTransl.getNCLangRes()
          .getStrByID("4006005_0", "04006005-0000")/*@res "«Î—°‘Ò––£°"*/);
    }

    BillItem billItem =
        this.editor.getBillCardPanel().getBodyItem(DeliveryBVO.CORDERCUSTID);

    String mdid = billItem.getMetaDataProperty().getRefBusinessEntity().getID();
    BDFuncletInitData initData =
        new BDFuncletInitData(mdid, ILinkType.LINK_TYPE_QUERY,
            custids.toArray(new String[custids.size()])[0]);

    int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
    int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
    BDFuncletWindowLauncher.openFuncNodeDialog(null, initData, null, false,
        false, new Dimension(screenWidth, screenHeight), null);
  }

  public DeliveryEditor getEditor() {
    return this.editor;
  }

  public DeliveryManageModel getModel() {
    return this.model;
  }

  public void setEditor(DeliveryEditor editor) {
    this.editor = editor;
  }

  public void setModel(DeliveryManageModel model) {
    this.model = model;
    model.addAppEventListener(this);
  }

  @Override
  protected boolean isActionEnable() {
    boolean isEnable = this.model.getSelectedData() != null;
    return isEnable;
  }

  private void initializeAction() {
    SCMActionInitializer.initializeAction(this, SCMActionCode.SO_QUERYCUSTINFO);
  }
}
