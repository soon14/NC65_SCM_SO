package nc.ui.so.m38.billui.action;

import java.awt.event.ActionEvent;

import nc.desktop.ui.WorkbenchEnvironment;
import nc.ui.pubapp.pub.power.PowerCheckUtils;
import nc.ui.pubapp.uif2app.actions.EditAction;
import nc.ui.so.m38.billui.view.PreOrderEditor;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.ui.uif2.UIState;
import nc.ui.uif2.model.BillManageModel;
import nc.vo.pubapp.pub.power.PowerActionEnum;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m38.entity.PreOrderHVO;
import nc.vo.so.m38.entity.PreOrderVO;
import nc.vo.so.pub.enumeration.BillStatus;
import nc.vo.so.pub.keyvalue.AbstractKeyValue;

public class PreOrderEditAction extends EditAction {

  /**
   * 
   */
  private static final long serialVersionUID = 3428195573031462098L;

  private PreOrderEditor editor;

  @Override
  public void doAction(ActionEvent e) throws Exception {
    PreOrderVO bill = (PreOrderVO) this.getModel().getSelectedData();
    // 业务操作维护权限
    PowerCheckUtils.checkHasPermission(new PreOrderVO[] {
      bill
    }, SOBillType.PreOrder.getCode(), PowerActionEnum.EDIT.getActioncode(),
        PreOrderHVO.VBILLCODE);
    this.getModel().setUiState(UIState.EDIT);
    this.getEditor().setCardEdit();
  }

  public PreOrderEditor getEditor() {
    return this.editor;
  }

  public void setEditor(PreOrderEditor editor) {
    this.editor = editor;
  }

  @Override
  protected boolean isActionEnable() {
    BillManageModel mangemodel = (BillManageModel) this.getModel();
    boolean isEnable =
        mangemodel.getUiState() == UIState.NOT_EDIT
            && null != mangemodel.getSelectedData();

    if (isEnable) {
      Object[] selectedRows = mangemodel.getSelectedOperaDatas();
      PreOrderVO selectedData = (PreOrderVO) mangemodel.getSelectedData();
      Integer billstatus = selectedData.getParentVO().getFstatusflag();

      isEnable =
          null != selectedRows && selectedRows.length > 1
              || BillStatus.FREE.equalsValue(billstatus)
              || BillStatus.NOPASS.equalsValue(billstatus);
    }
    return isEnable;
  }
}
