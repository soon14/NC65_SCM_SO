package nc.ui.so.salequotation.action;


import nc.ui.pubapp.uif2app.actions.pflow.DeleteScriptAction;
import nc.ui.uif2.UIState;
import nc.vo.so.salequotation.entity.AggSalequotationHVO;
import nc.vo.so.salequotation.entity.BillStatusEnum;
import nc.vo.so.salequotation.entity.SalequotationHVO;

public class SalequoDelAction extends DeleteScriptAction {

  /**
   * 
   */
  private static final long serialVersionUID = -1270247562389329133L;

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
            && (status.intValue() == BillStatusEnum.C_FREE || status.intValue() == BillStatusEnum.C_INVALIDATE);
    return this.getModel().getUiState() == UIState.NOT_EDIT && billStatusFlag;
  }

}
