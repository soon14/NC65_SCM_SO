package nc.ui.so.m38.billui.action.link;

import java.awt.event.ActionEvent;

import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.ui.so.m38.billui.view.PreOrderEditor;
import nc.ui.so.pub.dlg.ProfitDlg;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.UIState;
import nc.vo.scmpub.res.SCMActionCode;
import nc.vo.so.entry.ProfitVO;
import nc.vo.so.m38.entity.PreOrderVO;
import nc.vo.so.m38.util.PreOrderVOUtil;

public class PreOrderEstProfitAction extends NCAction {

  /**
   * 
   */
  private static final long serialVersionUID = -8650273137247490924L;

  private PreOrderEditor editor;

  private BillManageModel model;

  public PreOrderEstProfitAction() {
    super();
    SCMActionInitializer.initializeAction(this,
        SCMActionCode.SCM_CROSSPROFITRPT);
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {

    PreOrderVOUtil voutil = new PreOrderVOUtil();
    ProfitVO[] viewvos = null;
    // 编辑和新增状态前台计算毛利
    if (this.model.getUiState() == UIState.EDIT
        || this.model.getUiState() == UIState.ADD) {
      PreOrderVO[] vos = new PreOrderVO[] {
        (PreOrderVO) this.editor.getValue()
      };
      viewvos = voutil.changeToProfitVO(vos);
    }
    else {
      // 列表状态和非编辑态后台计算毛利
      Object[] objs = this.model.getSelectedOperaDatas();
      int intCount = objs.length;
      String[] hids = new String[intCount];
      for (int i = 0; i < intCount; i++) {
        PreOrderVO vo = (PreOrderVO) objs[i];
        hids[i] = vo.getPrimaryKey();
      }
      viewvos = voutil.changeToProfitVO(hids);
    }

    ProfitDlg profitDlg =
        new ProfitDlg(this.getEditor().getBillCardPanel(), viewvos);
    profitDlg.showModal();
  }

  public PreOrderEditor getEditor() {
    return this.editor;
  }

  public BillManageModel getModel() {
    return this.model;
  }

  public void setEditor(PreOrderEditor editor) {
    this.editor = editor;
  }

  public void setModel(BillManageModel model) {
    this.model = model;
    this.model.addAppEventListener(this);
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
