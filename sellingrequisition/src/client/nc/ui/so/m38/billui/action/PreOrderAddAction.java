package nc.ui.so.m38.billui.action;

import java.awt.event.ActionEvent;

import nc.ui.pubapp.uif2app.actions.AddAction;
import nc.ui.so.m38.billui.view.PreOrderEditor;

public class PreOrderAddAction extends AddAction {

  private static final long serialVersionUID = 6208040513606771758L;

  private PreOrderEditor editor;

  @Override
  public void doAction(ActionEvent e) throws Exception {
    super.doAction(e);
    // 公共组织选择框新增时没有发生事件，导致没有发起对表头组织设置，这里补上组织设值
    /* BillCardPanel cardpanel = this.getEditor().getBillCardPanel();
     IKeyValue keyValue = new CardKeyValue(cardpanel);
     String org = keyValue.getHeadStringValue(PreOrderHVO.PK_ORG);
     if (PubAppTool.isNull(org)) {
       org = this.model.getContext().getPk_org();
       keyValue.setHeadValue(PreOrderHVO.PK_ORG, org);
       cardpanel.getBillData().loadEditHeadRelation(PreOrderHVO.PK_ORG);
     }*/
  }

  public PreOrderEditor getEditor() {
    return this.editor;
  }

  public void setEditor(PreOrderEditor editor) {
    this.editor = editor;
  }

}
