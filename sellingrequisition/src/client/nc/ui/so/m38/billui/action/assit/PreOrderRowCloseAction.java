package nc.ui.so.m38.billui.action.assit;

import java.awt.event.ActionEvent;

import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.transfer.bill.ClientBillCombinServer;
import nc.vo.scmpub.res.SCMActionCode;
import nc.vo.so.m38.entity.PreOrderBVO;
import nc.vo.so.m38.entity.PreOrderVO;
import nc.vo.so.pub.enumeration.BillStatus;

import nc.itf.so.m38.IPreOrderAssitFunc;

import nc.bs.framework.common.NCLocator;

import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.ui.so.m38.billui.view.PreOrderEditor;
import nc.ui.so.m38.billui.view.PreOrderListView;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.ShowStatusBarMsgUtil;
import nc.ui.uif2.UIState;
import nc.ui.uif2.model.AbstractAppModel;

/**
 * 预订单行关闭按钮
 * 
 * @since 6.1
 * @version 2013-03-18 15:37:41
 * @author liujingn
 */
public class PreOrderRowCloseAction extends NCAction {

  /**
   *
   */
  private static final long serialVersionUID = 5154115102907511740L;

  private PreOrderEditor editor;

  private PreOrderListView listView;

  private AbstractAppModel model;

  /**
   * 设置列表界面
   * 
   * @param listView
   */
  public void setListView(PreOrderListView listView) {
    this.listView = listView;
  }

  /**
   * 构造方法
   */
  public PreOrderRowCloseAction() {
    super();
    SCMActionInitializer.initializeAction(this, SCMActionCode.SCM_LINECLOSE);
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {
    // 如果卡片、列表时都没有选中行，则按钮不可用
    int cardselRow =
        this.editor.getBillCardPanel().getBillTable().getSelectedRow();
    int listselRow =
        this.listView.getBillListPanel().getBodyTable().getSelectedRow();
    if (cardselRow == -1 && listselRow == -1) {
      String message =
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006012_0",
              "04006012-0007")/*@res "请先选中行"*/;
      ExceptionUtils.wrappBusinessException(message);
    }
    // 获取表体选择的行
    int[] newrows = this.getBodySelRows();
    PreOrderVO selectedData = (PreOrderVO) this.getModel().getSelectedData();
    PreOrderVO[] ret = null;
    IPreOrderAssitFunc service =
        NCLocator.getInstance().lookup(IPreOrderAssitFunc.class);
    try {
      ret = service.closePreOrderRows(selectedData, newrows);
      // 后台变化VO与前台合并
      ClientBillCombinServer<PreOrderVO> util =
          new ClientBillCombinServer<PreOrderVO>();
      util.combine(new PreOrderVO[] {
        selectedData
      }, ret);
    }
    catch (BusinessException ex) {

      ExceptionUtils.wrappException(ex);
    }
    this.model.directlyUpdate(new PreOrderVO[] {
      selectedData
    });
    this.showQueryInfo();
  }

  /**
   * 
   */
  protected void showQueryInfo() {
    ShowStatusBarMsgUtil.showStatusBarMsg(
        nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006012_0",
            "04006012-0008")/*@res "当前行关闭成功。"*/, this.getModel().getContext());
  }

  /**
   * 
   * @return PreOrderEditor
   */
  public PreOrderEditor getEditor() {
    return this.editor;
  }

  /**
   * 
   * @return AbstractAppModel
   */
  public AbstractAppModel getModel() {
    return this.model;
  }

  /**
   * 
   * @param editor
   */
  public void setEditor(PreOrderEditor editor) {
    this.editor = editor;
  }

  /**
   * 
   * @param model
   */
  public void setModel(AbstractAppModel model) {
    this.model = model;
    // 添加按钮状态设置监听(isActionEnable)
    this.model.addAppEventListener(this);
  }

  @Override
  protected boolean isActionEnable() {
    // 如果卡片、列表时都没有选中行，则按钮不可用
    int cardselRow =
        this.editor.getBillCardPanel().getBillTable().getSelectedRow();
    int listselRow =
        this.listView.getBillListPanel().getBodyTable().getSelectedRow();
    if (cardselRow == -1 && listselRow == -1) {
      return false;
    }
    if (this.model.getUiState() == UIState.NOT_EDIT) {

      PreOrderVO selectedData = (PreOrderVO) this.getModel().getSelectedData();
      if (selectedData == null) {
        return false;
      }
      // 当单据不是审核状态不能做行关闭动作
      if (!BillStatus.AUDIT.equalsValue(selectedData.getParentVO()
          .getFstatusflag())) {
        return false;
      }
      // 获取表体选择的行
      int[] rows = this.getBodySelRows();
      // 如果是多选，则按钮可用
      if (rows.length > 1) {
        return true;
      }
      else if (rows.length == 1) {
        int row = rows[0];
        // 当在修改界面，新增了n行然后做取消动作时，现在可能选中的焦点和界面上数据的行数不符合，则不可以进行行操作
        PreOrderBVO[] items = selectedData.getChildrenVO();
        if (row >= items.length) {
          return false;
        }
        PreOrderBVO item = items[rows[0]];
        // 如果是行关闭已关闭s则按钮置灰
        if (UFBoolean.TRUE.equals(item.getBlineclose())) {
          return false;
        }
      }
    }
    return true;
  }

  private int[] getBodySelRows() {
    int[] rows = null;
    // 卡片界面
    if (((ShowUpableBillForm) this.editor).isComponentVisible()) {
      rows = this.editor.getBillCardPanel().getBillTable().getSelectedRows();
    }
    else {// 列表界面
      rows = this.listView.getBillListPanel().getBodyTable().getSelectedRows();
    }
    return rows;
  }

}
