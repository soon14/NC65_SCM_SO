package nc.ui.so.m30.billui.view;

import nc.ui.scmpub.tempsave.action.TempDataDefaultLoader;
import nc.vo.so.m30.sobalance.entity.SoBalanceVO;

/**
 * @description
 *
 * @scene
 * 
 * @param
 * 
 *
 * @since 6.5
 * @version 2015年8月27日 上午10:27:17
 * @author wangweir
 */

public class SaleOrderTempDataLoader extends TempDataDefaultLoader {

  /**
   *
   */
  @Override
  public void loadTempData(Object data) {
    super.loadTempData(data);
    this.resetSaleOrderClientContext();
  }

  /**
   * 销售订单特殊业务，需要重置SaleOrderClientContext信息
   */
  private void resetSaleOrderClientContext() {
    SaleOrderBillForm billForm = (SaleOrderBillForm) this.getEditor();
    billForm.setTempvo(null);
    billForm.setSobalancevo(new SoBalanceVO());
    billForm.setThisGatheringMny(null);
    billForm.setIsCashSale(false);
  }

}
