/**
 * 
 */
package nc.ui.so.m30.billui.action.printaction;

import java.awt.event.ActionEvent;

import nc.vo.pubapp.pub.power.PowerActionEnum;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;

import nc.ui.pubapp.pub.power.PowerCheckUtils;
import nc.ui.pubapp.uif2app.actions.MetaDataBasedPrintAction;

/**
 * 销售订单打印操作
 * 
 * @since 6.3
 * @version 2013-01-23 13:04:41
 * @author 冯加彬
 */
public class SaleOrderPrintAction extends MetaDataBasedPrintAction {

  /**
   * 
   */
  private static final long serialVersionUID = 993409051896966967L;

  @Override
  public void doAction(ActionEvent e) throws Exception {
    SaleOrderVO bill = (SaleOrderVO) this.getModel().getSelectedData();
    SaleOrderVO[] printbills = new SaleOrderVO[] {
      bill
    };
    // 业务操作维护权限
    PowerCheckUtils.checkHasPermission(printbills, SOBillType.Order.getCode(),
        PowerActionEnum.PRINT.getActioncode(), SaleOrderHVO.VBILLCODE);
    // BillManageModel model = (BillManageModel) this.getModel();
    // IPrintListener printlistener = new SOPrintListenter<SaleOrderVO>(model);
    // super.setIPrintListenter(printlistener);

    super.doAction(e);
  }
}
