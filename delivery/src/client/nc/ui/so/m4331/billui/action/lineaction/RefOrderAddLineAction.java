package nc.ui.so.m4331.billui.action.lineaction;

import nc.ui.uif2.UIState;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m4331.entity.DeliveryVO;
import nc.vo.trade.checkrule.VOChecker;

public class RefOrderAddLineAction extends RefAddLineAction {
  private static final long serialVersionUID = 1L;

  @Override
  public boolean isEnabled() {
    boolean isEnable = false;
    String srcType = null;
    if (this.getModel().getUiState().equals(UIState.ADD)) {
      srcType = this.getModel().getSourceType();
    }
    else {
      DeliveryVO vo = (DeliveryVO) this.getModel().getSelectedData();
      if (VOChecker.isEmpty(vo.getChildrenVO())) {
        isEnable = false;
      }
      srcType = vo.getChildrenVO()[0].getVsrctype();
    }
    if (SOBillType.Order.getCode().equals(srcType)) {
      isEnable = true;
    }
    return isEnable;
  }
}
