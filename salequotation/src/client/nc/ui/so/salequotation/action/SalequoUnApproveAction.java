package nc.ui.so.salequotation.action;

import nc.ui.pubapp.uif2app.actions.pflow.UNApproveScriptAction;
import nc.ui.uif2.UIState;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.salequotation.entity.AggSalequotationHVO;
import nc.vo.so.salequotation.entity.BillStatusEnum;
import nc.vo.so.salequotation.entity.SalequotationBVO;
import nc.vo.so.salequotation.entity.SalequotationHVO;

public class SalequoUnApproveAction extends UNApproveScriptAction {

  /**
   *
   */
  private static final long serialVersionUID = -1209568378179386243L;


  @Override
  protected boolean isActionEnable() {
    // 多选时按钮都可用
    if (this.getModel().getSelectedOperaRows() != null
        && this.getModel().getSelectedOperaRows().length > 1) {
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
            && (status.intValue() == BillStatusEnum.C_AUDIT || status
                .intValue() == BillStatusEnum.C_AUDITING || status
                .intValue() == BillStatusEnum.C_NOPASS);
    return (this.getModel().getUiState() == UIState.INIT || this.getModel()
        .getUiState() == UIState.NOT_EDIT) && billStatusFlag;
  }

}