package nc.ui.so.m4331.billui.action.lineaction;

import nc.ui.uif2.UIState;
import nc.vo.scmpub.res.billtype.TOBillType;
import nc.vo.so.m4331.entity.DeliveryVO;
import nc.vo.trade.checkrule.VOChecker;

/**
 * 
 * @since 6.0
 * @version 2010-11-12 下午03:13:03
 * @author 祝会征
 */
public class Ref5XAddLineAction extends RefAddLineAction {
  private static final long serialVersionUID = 1L;

  @Override
  public boolean isEnabled() {

    // 调拨订单做发货安排时，不支持参照增行
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
    if (TOBillType.TransOrder.getCode().equals(srcType)) {
      isEnable = true;
    }
    return isEnable;
  }
}
