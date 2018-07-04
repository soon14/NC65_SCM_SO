package nc.ui.so.m30.billui.action;

import java.awt.event.ActionEvent;

import nc.bs.uif2.IActionCode;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.actions.AbstractReferenceAction;
import nc.ui.pubapp.uif2app.view.BillForm;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.ui.uif2.UIState;
import nc.ui.uif2.actions.ActionInitializer;
import nc.ui.uif2.model.AbstractAppModel;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.pub.keyvalue.IKeyValue;

@SuppressWarnings("serial")
public class SaleOrderAddAction extends AbstractReferenceAction {
  private BillForm editor;

  private AbstractAppModel model;
  
  public SaleOrderAddAction() {
    super();
    ActionInitializer.initializeAction(this, IActionCode.ADD);
   }


  @Override
  public void doAction(ActionEvent e) throws Exception {
    try {
      this.model.setUiState(UIState.ADD);
    }
    catch (Exception ex) {
      this.model.setUiState(UIState.NOT_EDIT);
      throw ex;
    }
    // 公共组织选择框在新增时没有发事件,导致表头的组织没有设置,这里补上对组织的设置
    BillCardPanel cardPanel = this.editor.getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);
    String pk_org = keyValue.getHeadStringValue(SaleOrderHVO.PK_ORG);
    if (PubAppTool.isNull(pk_org)) {
      pk_org = this.getModel().getContext().getPk_org();
      keyValue.setHeadValue(SaleOrderHVO.PK_ORG, pk_org);
      cardPanel.getBillData().loadEditHeadRelation(SaleOrderHVO.PK_ORG);
    }

  }

  public BillForm getEditor() {
    return this.editor;
  }

  public AbstractAppModel getModel() {
    return this.model;
  }

  public void setEditor(BillForm editor) {
    this.editor = editor;
  }

  public void setModel(AbstractAppModel model) {
    this.model = model;
    model.addAppEventListener(this);
  }

  @Override
  protected boolean isActionEnable() {
    return this.model.getUiState() == UIState.NOT_EDIT;
  }

  @Override
  protected boolean isManual() {
    return true;
  }
}
