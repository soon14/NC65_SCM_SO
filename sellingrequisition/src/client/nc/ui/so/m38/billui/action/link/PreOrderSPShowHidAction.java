package nc.ui.so.m38.billui.action.link;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;

import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.ui.pub.beans.UIPanel;
import nc.ui.pub.style.Style;
import nc.ui.pubapp.uif2app.view.CompositeBillDataPrepare;
import nc.ui.scmf.ic.onhand.OnhandPanelAdaptor;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.ui.so.m38.billui.view.PreOrderEditor;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.TangramContainer;
import nc.ui.uif2.model.AbstractAppModel;
import nc.vo.scmpub.res.SCMActionCode;

@SuppressWarnings("restriction")
public class PreOrderSPShowHidAction extends NCAction {

  /**
   * 
   */
  private static final long serialVersionUID = -7560507076285833102L;

  private OnhandPanelAdaptor adaptor;

  private TangramContainer contain;

  private PreOrderEditor editor;

  private nc.vo.uif2.LoginContext logincontext;

  private AbstractAppModel model;

  private CompositeBillDataPrepare userdefitemPreparator;

  public PreOrderSPShowHidAction() {
    super();
    SCMActionInitializer.initializeAction(this, SCMActionCode.SCM_ONHANDINFO);
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {

    if (!SysInitGroupQuery.isICEnabled()) {
      return;
    }

    this.editor.showMeUp();

    if (this.editor.getExtendedPanel() == null) {
      if (this.adaptor == null) {
        OnhandPanelAdaptor adaptor =
            new OnhandPanelAdaptor("nc.ui.ic.onhand.pane.QueryOnHandInfoPanel");
        UIPanel refpanel = adaptor.getRefPane();
        this.adaptor = adaptor;
        this.adaptor.setOnhandPanelSrc(this.editor);
        this.adaptor.setUserdefitemPreparator(this.userdefitemPreparator);
        this.adaptor.setLogincontext(this.logincontext);
        this.adaptor.initialize();
        // 显示控制
        refpanel.setPreferredSize(new Dimension(300, 180));
        refpanel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0,
            Style.getColor("系统按钮条.背景"))); /*-=notranslate=-*/
        this.editor.add(refpanel, BorderLayout.SOUTH);
        this.editor.setExtendedPanel(adaptor);
      }
    }
    else {
      this.adaptor = this.editor.getExtendedPanel();
    }
    // this.editor.showMeUp();
    UIPanel refpanel = this.adaptor.getRefPane();
    if (this.adaptor.isComponentDisplayable()) {
      refpanel.setVisible(false);
      this.adaptor.setIsComponentDisplayable(false);
    }
    else {
      refpanel.setVisible(true);
      this.adaptor.setIsComponentDisplayable(true);
    }
    this.contain.resetLayout();
    this.editor.showMeUp();
    int selectRow =
        this.editor.getBillCardPanel().getBillTable().getSelectedRow();
    if (selectRow == -1) {
      this.adaptor.clearData();
    }
    else {
      this.adaptor.freshData(selectRow);
    }

  }

  public OnhandPanelAdaptor getAdaptor() {
    return this.adaptor;
  }

  public TangramContainer getContain() {
    return this.contain;
  }

  public PreOrderEditor getEditor() {
    return this.editor;
  }

  public nc.vo.uif2.LoginContext getLogincontext() {
    return this.logincontext;
  }

  public AbstractAppModel getModel() {
    return this.model;
  }

  public CompositeBillDataPrepare getUserdefitemPreparator() {
    return this.userdefitemPreparator;
  }

  public void setAdaptor(OnhandPanelAdaptor adaptor) {
    this.adaptor = adaptor;
  }

  public void setContain(TangramContainer contain) {
    this.contain = contain;
  }

  public void setEditor(PreOrderEditor editor) {
    this.editor = editor;
  }

  public void setLogincontext(nc.vo.uif2.LoginContext logincontext) {
    this.logincontext = logincontext;
  }

  public void setModel(AbstractAppModel model) {
    this.model = model;
    this.model.addAppEventListener(this);
  }

  public void setUserdefitemPreparator(
      CompositeBillDataPrepare userdefitemPreparator) {
    this.userdefitemPreparator = userdefitemPreparator;
  }

  @Override
  protected boolean isActionEnable() {
    // 没有选中单据，置灰
    if (this.model.getSelectedData() == null) {
      return false;
    }
    return true;
  }

}
