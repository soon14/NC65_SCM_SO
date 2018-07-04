package nc.ui.so.m30.billui.editor.bodyevent;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.pub.keyvalue.IKeyValue;

public class ExchangeRateEditHandler {

  public void beforeEdit(CardBodyBeforeEditEvent e) {

    int editrow = e.getRow();
    BillCardPanel cardPanel = e.getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);
    // 原币币种
    String localcurid =
        keyValue.getHeadStringValue(SaleOrderHVO.CORIGCURRENCYID);
    // 组织本位币
    String orgcurid =
        keyValue.getBodyStringValue(editrow, SaleOrderBVO.CCURRENCYID);

    // 没有原币或者组织本位币
    if (PubAppTool.isNull(localcurid) || PubAppTool.isNull(orgcurid)
        || localcurid.equals(orgcurid)) {
      e.setReturnValue(false);
      return;
    }

    e.setReturnValue(true);

  }

}
