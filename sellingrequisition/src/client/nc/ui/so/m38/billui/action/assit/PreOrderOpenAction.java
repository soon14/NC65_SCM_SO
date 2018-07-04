package nc.ui.so.m38.billui.action.assit;

import java.awt.event.ActionEvent;

import nc.bs.framework.common.NCLocator;
import nc.itf.so.m38.IPreOrderAssitFunc;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.ui.so.m38.billui.view.PreOrderEditor;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.ShowStatusBarMsgUtil;
import nc.ui.uif2.UIState;
import nc.ui.uif2.model.AbstractAppModel;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.transfer.bill.ClientBillCombinServer;
import nc.vo.scmpub.res.SCMActionCode;
import nc.vo.so.m38.entity.PreOrderVO;
import nc.vo.so.pub.enumeration.BillStatus;

public class PreOrderOpenAction extends NCAction {

  /**
   *
   */
  private static final long serialVersionUID = 7259715510438781095L;

  private PreOrderEditor editor;

  private AbstractAppModel model;

  public PreOrderOpenAction() {
    super();
    SCMActionInitializer.initializeAction(this, SCMActionCode.SCM_BILLOPEN);
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {
    Object obj = this.model.getSelectedData();
    PreOrderVO[] bills = new PreOrderVO[] {
      (PreOrderVO) obj
    };
    PreOrderVO[] ret = null;
    IPreOrderAssitFunc service =
        NCLocator.getInstance().lookup(IPreOrderAssitFunc.class);
    try {
      ret = service.openPreOrder(bills);
      // 后台变化VO与前台合并
      ClientBillCombinServer<PreOrderVO> util =
          new ClientBillCombinServer<PreOrderVO>();
      util.combine(bills, ret);
    }
    catch (BusinessException ex) {

      ExceptionUtils.wrappException(ex);
    }
    this.model.directlyUpdate(bills);
    this.showQueryInfo();
  }
  protected void showQueryInfo() {
    ShowStatusBarMsgUtil.showStatusBarMsg(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006012_0","04006012-0003")/*@res "当前单据整单打开成功。"*/, this.getModel()
        .getContext());
  }
  public PreOrderEditor getEditor() {
    return this.editor;
  }

  public AbstractAppModel getModel() {
    return this.model;
  }

  public void setEditor(PreOrderEditor editor) {
    this.editor = editor;
  }

  public void setModel(AbstractAppModel model) {
    this.model = model;
    this.model.addAppEventListener(this);
  }

  @Override
  protected boolean isActionEnable() {
    // 没有选中单据，置灰
    if (this.model.getSelectedData() == null) {
      return false;
    }
    if (this.model.getUiState() == UIState.ADD
        || this.model.getUiState() == UIState.EDIT) {
      return false;
    }
    // 未整单关闭的单据，按钮不可用
    PreOrderVO vo = (PreOrderVO) this.model.getSelectedData();
    Integer fstatusflag = vo.getParentVO().getFstatusflag();
    if (!BillStatus.CLOSED.equalsValue(fstatusflag)) {
      return false;
    }
    return true;
  }
}