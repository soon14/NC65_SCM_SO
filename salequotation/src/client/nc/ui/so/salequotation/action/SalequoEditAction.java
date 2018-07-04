package nc.ui.so.salequotation.action;

import java.awt.event.ActionEvent;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.pub.power.PowerCheckUtils;
import nc.ui.pubapp.uif2app.actions.EditAction;
import nc.ui.so.salequotation.model.SalequoModel;
import nc.ui.so.salequotation.view.SalequoBillForm;
import nc.ui.uif2.UIState;
import nc.vo.pubapp.pub.power.PowerActionEnum;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.salequotation.entity.AggSalequotationHVO;
import nc.vo.so.salequotation.entity.BillStatusEnum;
import nc.vo.so.salequotation.entity.SalequotationHVO;

public class SalequoEditAction extends EditAction {

  /**
   * 
   */
  private static final long serialVersionUID = -3231363437504455188L;

  private SalequoBillForm editor;

  @Override
  public void doAction(ActionEvent e) throws Exception {
    AggSalequotationHVO aggVO =
        (AggSalequotationHVO) this.getModel().getSelectedData();
    // 业务操作维护权限
    PowerCheckUtils.checkHasPermission(new AggSalequotationHVO[] {
      aggVO
    }, SOBillType.SaleQuotation.getCode(),
        PowerActionEnum.EDIT.getActioncode(), SalequotationHVO.VBILLCODE);

    super.doAction(e);
    this.getModel();
    Integer status = null;

    if (aggVO != null) {
      SalequotationHVO hvo = aggVO.getParentVO();
      if (hvo != null) {
        status = hvo.getFstatusflag();
      }
    }
    if (status != null && status.intValue() == BillStatusEnum.C_NOPASS) {
      BillCardPanel panel = this.getEditor().getBillCardPanel();
      panel.getHeadItem(SalequotationHVO.FSTATUSFLAG).setValue(
          Integer.valueOf(BillStatusEnum.C_FREE));
      panel.getTailItem(SalequotationHVO.APPROVER).setValue(null);
      panel.getTailItem(SalequotationHVO.TAUDITTIME).setValue(null);
    }
  }

  public SalequoBillForm getEditor() {
    return this.editor;
  }

  public void setEditor(SalequoBillForm editor) {
    this.editor = editor;
  }

  @Override
  protected boolean isActionEnable() {
    // 多选时按钮都可用
    SalequoModel model = (SalequoModel) this.getModel();
    if (model.getSelectedOperaRows() != null
        && model.getSelectedOperaRows().length > 1) {
      return true;
    }

    Integer status = null;
    AggSalequotationHVO aggVO =
        (AggSalequotationHVO) this.getModel().getSelectedData();
    if (aggVO != null) {
      SalequotationHVO hvo = aggVO.getParentVO();
      if (hvo != null) {
        status = hvo.getFstatusflag();
      }
    }
    boolean billStatusFlag =
        status != null
            && (status.intValue() == BillStatusEnum.C_FREE || status.intValue() == BillStatusEnum.C_NOPASS);
    return this.getModel().getUiState() == UIState.NOT_EDIT && billStatusFlag;
  }

}
