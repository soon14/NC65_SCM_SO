package nc.ui.so.tranmatrel.billhandler;

import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.OrgChangedEvent;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.pubapp.uif2app.view.BillForm;
import nc.ui.pubapp.uif2app.view.util.BillPanelUtils;
import nc.vo.uif2.LoginContext;

public class PubAppOrgEditHandler implements IAppEventHandler<OrgChangedEvent> {
  private LoginContext context;

  private BillForm editor;

  private BillManageModel model;

  public PubAppOrgEditHandler(BillForm editor, BillManageModel model,
      LoginContext context) {
    this.editor = editor;
    this.model = model;
    this.context = context;
  }

  public LoginContext getContext() {
    return this.context;
  }

  public BillForm getEditor() {
    return this.editor;
  }

  public BillManageModel getModel() {
    return this.model;
  }

  @Override
  public void handleAppEvent(OrgChangedEvent e) {
    // 只在新增时响应公共组织选择框发出的变化事件
    // if (this.model.getUiState() == UIState.ADD) {
    // //
    // }
    BillPanelUtils.setOrgForAllRef(this.editor.getBillCardPanel(),
        this.model.getContext());
  }

  public void setContext(LoginContext context) {
    this.context = context;
  }

  public void setEditor(BillForm editor) {
    this.editor = editor;
  }

  public void setModel(BillManageModel model) {
    this.model = model;
  }

}
