package nc.ui.so.m4331.billui.editor.bodyevent;

import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.scmpub.ref.FilterVehicleRefUtils;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.vo.so.m4331.entity.DeliveryBVO;
import nc.vo.so.m4331.entity.DeliveryHVO;

/**
 * 车辆编辑事件
 * 
 * @since 6.0
 * @version 2011-3-24 下午02:50:43
 * @author 祝会征
 */
public class VehicleEditHandler {
  /**
   * 车辆编辑前事件
   * 
   * @param e
   */
  public void beforeEdit(CardBodyBeforeEditEvent e) {
    int row = e.getRow();
    BillCardPanel cardPanel = e.getBillCardPanel();
    CardKeyValue keyValue = new CardKeyValue(cardPanel);
    String pk_org = keyValue.getHeadStringValue(DeliveryHVO.PK_ORG);
    String cvehicletypeid =
      keyValue.getBodyStringValue(row, DeliveryBVO.CVEHICLETYPEID);
    BillItem item = cardPanel.getBodyItem(DeliveryBVO.CVEHICLEID);
    UIRefPane pane = (UIRefPane) item.getComponent();
    pane.setPk_org(pk_org);
    FilterVehicleRefUtils utils =
      new FilterVehicleRefUtils((UIRefPane) item.getComponent());
    utils.filterVehicleRefByType(cvehicletypeid);
  }
}
