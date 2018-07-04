package nc.ui.so.mbuylargess.editor.bodyevent;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.scmpub.ref.FilterMeasdocRefUtils;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.mbuylargess.entity.BuyLargessBVO;
import nc.vo.so.pub.keyvalue.IKeyValue;

public class AstUnitEditHandler {

  public void beforeEdit(CardBodyBeforeEditEvent e) {
    int row = e.getRow();
    BillCardPanel cardPanel = e.getBillCardPanel();
    IKeyValue keyvalue = new CardKeyValue(cardPanel);
    String material =
        keyvalue.getBodyStringValue(row, BuyLargessBVO.PK_MATERIAL);
    if (!PubAppTool.isNull(material)) {
      // 物料不为空，只能参照物料对应的计量单位
      BillItem astunititem = cardPanel.getBodyItem(BuyLargessBVO.PK_MEASDOC);
      FilterMeasdocRefUtils astunitFilter =
          new FilterMeasdocRefUtils(astunititem);
      astunitFilter.setMaterialPk(material);
      e.setReturnValue(Boolean.TRUE);
    }
    else {
      e.setReturnValue(Boolean.FALSE);
    }

  }

}
