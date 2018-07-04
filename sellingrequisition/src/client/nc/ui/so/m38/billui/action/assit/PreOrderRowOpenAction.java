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
 * 预订单打开Action
 * 
 * @since 6.1
 * @version 2013-03-19 13:47:30
 * @author liujingn
 */
public class PreOrderRowOpenAction extends NCAction {

  /**
   *
   */
  private static final long serialVersionUID = 1753068143628383725L;

  private PreOrderEditor editor;

  private AbstractAppModel model;

  private PreOrderListView listView;

  /**
   * 设置列表界面
   * 
   * @param listView
   */
  public void setListView(PreOrderListView listView) {
    this.listView = listView;
  }

  /**
   *
   */
  public PreOrderRowOpenAction() {
    super();
    SCMActionInitializer.initializeAction(this, SCMActionCode.SCM_LINEOPEN);
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

    int[] rows = this.getBodySelRows();

    PreOrderVO bill = (PreOrderVO) this.model.getSelectedData();
    PreOrderVO[] ret = null;
    IPreOrderAssitFunc service =
        NCLocator.getInstance().lookup(IPreOrderAssitFunc.class);
    try {
      ret = service.openPreOrderRows(bill, rows);
      // 后台变化VO与前台合并
      ClientBillCombinServer<PreOrderVO> util =
          new ClientBillCombinServer<PreOrderVO>();
      util.combine(new PreOrderVO[] {
        bill
      }, ret);
    }
    catch (BusinessException ex) {

      ExceptionUtils.wrappException(ex);
    }
    this.model.directlyUpdate(new PreOrderVO[] {
      bill
    });
    this.showQueryInfo();
  }

  /**
   * 
   */
  protected void showQueryInfo() {
    ShowStatusBarMsgUtil.showStatusBarMsg(
        nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006012_0",
            "04006012-0009")/*@res "当前行打开成功。"*/, this.getModel().getContext());
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
    this.model.addAppEventListener(this);
  }

  @Override
  protected boolean isActionEnable() {
    if (this.model.getSelectedData() == null) {
      return false;
    }
    if (this.model.getUiState() == UIState.NOT_EDIT) {
      PreOrderVO bill = (PreOrderVO) this.model.getSelectedData();
      Integer fstatusflag = bill.getParentVO().getFstatusflag();
      if (BillStatus.FREE.equalsValue(fstatusflag)) {
        return false;
      }
      if (BillStatus.CLOSED.equalsValue(fstatusflag)) {
        return true;
      }

      else if (BillStatus.AUDIT.equalsValue(fstatusflag)) {
        // 如果卡片、列表时都没有选中行，则按钮不可用
        int cardselRow =
            this.editor.getBillCardPanel().getBillTable().getSelectedRow();
        int listselRow =
            this.listView.getBillListPanel().getBodyTable().getSelectedRow();
        if (cardselRow == -1 && listselRow == -1) {
          return false;
        }
        int[] rows = this.getBodySelRows();

        if (rows.length > 1) {
          return true;
        }
        else if (rows.length == 1) {
          int row = rows[0];

          PreOrderBVO[] items = bill.getChildrenVO();
          if (null == items || items.length == 0) {
            return false;
          }
          if (row >= items.length) {
            return true;
          }
          PreOrderBVO item = items[rows[0]];
          if (UFBoolean.FALSE.equals(item.getBlineclose())) {
            return false;
          }
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
