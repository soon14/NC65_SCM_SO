package nc.ui.so.m4331.billui.action;

import java.awt.event.ActionEvent;

import nc.ui.pubapp.pub.power.PowerCheckUtils;
import nc.ui.pubapp.uif2app.actions.EditAction;
import nc.ui.so.m4331.billui.view.DeliveryEditor;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.ui.uif2.UIState;
import nc.ui.uif2.model.BillManageModel;
import nc.vo.pubapp.pub.power.PowerActionEnum;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.scmpub.res.billtype.TOBillType;
import nc.vo.so.m4331.entity.DeliveryBVO;
import nc.vo.so.m4331.entity.DeliveryHVO;
import nc.vo.so.m4331.entity.DeliveryVO;
import nc.vo.so.pub.SOItemKey;
import nc.vo.so.pub.enumeration.BillStatus;
import nc.vo.trade.checkrule.VOChecker;

public class DeliveryEditAction extends EditAction {

  private static final long serialVersionUID = -3566521350890738317L;

  private DeliveryEditor view;

  @Override
  public void doAction(ActionEvent e) throws Exception {
    DeliveryVO selectedData = (DeliveryVO) this.getModel().getSelectedData();
    // 业务操作维护权限
    PowerCheckUtils.checkHasPermission(new DeliveryVO[] {
      selectedData
    }, SOBillType.Delivery.getCode(), PowerActionEnum.EDIT.getActioncode(),
        DeliveryHVO.VBILLCODE);
    super.doAction(e);

    this.setUpdateBillDefValue();
    if (!VOChecker.isEmpty(selectedData)) {
      String billtype = selectedData.getChildrenVO()[0].getVsrctype();
      if (VOChecker.isEmpty(billtype)) {
        return;
      }
      if (billtype.equals(TOBillType.TransOrder.getCode())) {
        // 来自调拨订单的发货库存组织不允许编辑
        this.view.getBillCardPanel().getBodyItem(DeliveryBVO.CSENDSTOCKORGVID)
            .setEdit(false);
        // 来自调拨订单的客户允许为空
        this.view.getBillCardPanel().getBodyItem(DeliveryBVO.CORDERCUSTID)
            .setNull(false);
      }
      else {
        // 来自销售订单的发货库存组织允许编辑
        this.view.getBillCardPanel().getBodyItem(DeliveryBVO.CSENDSTOCKORGVID)
            .setEdit(true);
        // 来自销售订单的客户不允许为空
        this.view.getBillCardPanel().getBodyItem(DeliveryBVO.CORDERCUSTID)
            .setNull(true);
      }
    }
  }

  public DeliveryEditor getView() {
    return this.view;
  }

  public void setView(DeliveryEditor view) {
    this.view = view;
  }

  @Override
  protected boolean isActionEnable() {
    BillManageModel mangemodel = (BillManageModel) this.getModel();
    boolean isEnable =
        mangemodel.getUiState() == UIState.NOT_EDIT
            && null != mangemodel.getSelectedData();

    Object[] selectedRows = mangemodel.getSelectedOperaDatas();
    if (selectedRows==null||selectedRows.length > 1) {
      return false;
    }
    if (isEnable) {

      DeliveryVO selectedData = (DeliveryVO) mangemodel.getSelectedData();
      Integer billstatus = selectedData.getParentVO().getFstatusflag();
      isEnable =
          BillStatus.FREE.equalsValue(billstatus)
              || BillStatus.NOPASS.equalsValue(billstatus);
    }
    return isEnable;
  }

  private void setUpdateBillDefValue() {
    CardKeyValue keyValue = new CardKeyValue(this.getView().getBillCardPanel());
    keyValue.setHeadValue(SOItemKey.FSTATUSFLAG,
        BillStatus.FREE.getIntegerValue());
  }
}
