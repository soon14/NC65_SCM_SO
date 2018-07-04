package nc.ui.so.salequotation.action;

import nc.ui.pubapp.uif2app.actions.CopyAction;
import nc.vo.so.salequotation.entity.AggSalequotationHVO;

public class SalequoCopyAction extends CopyAction {

  /**
   * 
   */
  private static final long serialVersionUID = -1446865082233218518L;

  @Override
  protected boolean isActionEnable() {
    boolean enable = super.isActionEnable();
    AggSalequotationHVO bill =
        (AggSalequotationHVO) this.getModel().getSelectedData();
    if (bill == null) {
      enable = false;
    }
    else {
      boolean hasBody =
          bill.getChildrenVO() != null && bill.getChildrenVO().length > 0;
      enable = enable && hasBody;
    }
    return enable;
  }
}
