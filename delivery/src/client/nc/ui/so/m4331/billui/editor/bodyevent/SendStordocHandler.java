package nc.ui.so.m4331.billui.editor.bodyevent;

import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.scmpub.ref.FilterWareHouseRefUtils;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.m4331.entity.DeliveryBVO;
import nc.vo.so.pub.keyvalue.IKeyValue;

public class SendStordocHandler {

  /**
   * 方法功能描述：发货单表体发货仓库编辑前事件
   * 
   * @author 祝会征
   * @time 2010-6-7 下午03:42:00
   */
  public void beforeEdit(CardBodyBeforeEditEvent e) {
    BillCardPanel cardPanel = e.getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);

    String sendstock =
        keyValue.getBodyStringValue(e.getRow(), DeliveryBVO.CSENDSTOCKORGID);

    if (PubAppTool.isNull(sendstock)) {
      e.setReturnValue(Boolean.FALSE);
      String message =
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006002_0",
              "04006002-0017")
      /*@res "请先录入发货库存组织"*/;
      e.getBillCardPanel().transferFocus();
      ExceptionUtils.wrappBusinessException(message);
    }

    String csprofitcenterid =
        keyValue.getBodyStringValue(e.getRow(), DeliveryBVO.CSPROFITCENTERID);

    // 发货仓库参照发货库存组织下的发货仓库
    BillItem warehouse = cardPanel.getBodyItem(DeliveryBVO.CSENDSTORDOCID);
    FilterWareHouseRefUtils filter =
        new FilterWareHouseRefUtils((UIRefPane) warehouse.getComponent());
    if (!PubAppTool.isNull(csprofitcenterid)) {
      // 根据发货利润中心过滤
      filter.filterByLiabcenter(csprofitcenterid);
    }
    filter.filterItemRefByOrg(sendstock);
    filter.filterDirectStorc();
  }
}
