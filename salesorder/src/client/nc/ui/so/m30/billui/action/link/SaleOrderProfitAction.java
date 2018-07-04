package nc.ui.so.m30.billui.action.link;

import java.awt.event.ActionEvent;
import java.util.HashSet;
import java.util.Set;

import nc.ui.ml.NCLangRes;
import nc.ui.pubapp.uif2app.AppUiState;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.ui.so.m30.billui.view.SaleOrderBillForm;
import nc.ui.so.pub.dlg.ProfitDlg;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.UIState;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.scmpub.res.SCMActionCode;
import nc.vo.so.entry.ProfitVO;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.util.SaleOrderVOUtil;

/**
 * 销售订单毛利预估
 * 
 * @since 6.1
 * @version 2013-03-18 13:15:54
 * @author yixl
 */
public class SaleOrderProfitAction extends NCAction {

  private static final long serialVersionUID = 3256634527888024890L;

  /** 订单卡控件 */
  private SaleOrderBillForm editor;

  private BillManageModel model;

  /** 构造函数 */
  public SaleOrderProfitAction() {
    super();
    this.initializeAction();
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {
    ProfitVO[] viewvos = null;
    SaleOrderVOUtil voutil = new SaleOrderVOUtil();

    // 编辑和新增状态前台计算毛利
    if (this.model.getUiState() == UIState.EDIT
        || this.model.getUiState() == UIState.ADD) {
      SaleOrderVO editvo = (SaleOrderVO) this.editor.getValue();
      if (!this.isEditBillValiate(editvo)) {
        ExceptionUtils.wrappBusinessException(NCLangRes.getInstance()
            .getStrByID("4006011_0", "04006011-0430")/*必须存在物料、库存组织都不为空行*/);
      }
      SaleOrderVO[] vos = new SaleOrderVO[] {
        editvo
      };
      viewvos = voutil.changeToProfitVO(vos);

    }
    else {
      // 列表状态和非编辑态后台计算毛利
      Object[] objs = this.model.getSelectedOperaDatas();
      int intCount = objs.length;
      // 过滤重复ID 销售订单修订可能存在这种情况 add by zhangby5 for 65
      Set<String> hids = new HashSet<String>();
      for (int i = 0; i < intCount; i++) {
        SaleOrderVO vo = (SaleOrderVO) objs[i];
        hids.add(vo.getParentVO().getCsaleorderid());
      }
      viewvos = voutil.changeToProfitVO(hids.toArray(new String[0]));
    }

    ProfitDlg profitDlg =
        new ProfitDlg(this.getEditor().getBillCardPanel(), viewvos, true);
    profitDlg.showModal();

  }

  private boolean isEditBillValiate(SaleOrderVO editvo) {
    if (null == editvo || null == editvo.getChildrenVO()
        || editvo.getChildrenVO().length == 0) {
      return false;
    }

    for (SaleOrderBVO bvo : editvo.getChildrenVO()) {
      String marterialvid = bvo.getCmaterialvid();
      String sendstockorg = bvo.getCsendstockorgid();
      if (!PubAppTool.isNull(marterialvid) && !PubAppTool.isNull(sendstockorg)) {
        return true;
      }
    }
    return false;
  }

  /**
   * 获得销售订单billForm
   * 
   * @return SaleOrderBillForm
   */
  public SaleOrderBillForm getEditor() {
    return this.editor;
  }

  /**
   * 设置销售订单billForm
   * 
   * @param editor
   */
  public void setEditor(SaleOrderBillForm editor) {
    this.editor = editor;
  }

  /**
   * 获得销售订单model
   * 
   * @param model
   */
  public void setModel(BillManageModel model) {
    model.addAppEventListener(this);
    this.model = model;
  }

  @Override
  protected boolean isActionEnable() {
    AppUiState uistate = this.model.getAppUiState();
    boolean broenable =
        this.model.getUiState() == UIState.NOT_EDIT
            && this.model.getSelectedData() != null;

    return broenable || uistate == AppUiState.EDIT || uistate == AppUiState.ADD
        || uistate == AppUiState.COPY_ADD
        || uistate == AppUiState.TRANSFERBILL_ADD;

  }

  private void initializeAction() {
    SCMActionInitializer.initializeAction(this,
        SCMActionCode.SCM_CROSSPROFITRPT);
  }

}
