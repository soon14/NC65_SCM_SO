package nc.ui.so.mbuylargess.editor.headevent;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailBeforeEditEvent;
import nc.ui.scmpub.ref.FilterMeasdocRefUtils;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.mbuylargess.entity.BuyLargessHVO;
import nc.vo.so.pub.keyvalue.IKeyValue;

/**
 * 单位编辑前过滤
 * 
 * @since 6.3
 * @version 2012-11-13 下午03:23:13
 * @author 梁吉明
 */
@SuppressWarnings("restriction")
public class AstUnitEditHandler {

  public void beforeEdit(CardHeadTailBeforeEditEvent e) {

    BillCardPanel cardPanel = e.getBillCardPanel();
    IKeyValue keyvalue = new CardKeyValue(cardPanel);
    String material = keyvalue.getHeadStringValue(BuyLargessHVO.CBUYMARID);
    if (!PubAppTool.isNull(material)) {
      // 物料不为空，只能参照物料对应的计量单位
      BillItem astunititem = cardPanel.getHeadItem(BuyLargessHVO.CBUYUNITID);
      FilterMeasdocRefUtils astunitFilter =
          new FilterMeasdocRefUtils(astunititem);

      astunitFilter.setMaterialPk(material);
    }
    else {
      // 物料为空，参照重置（清除前台缓存）
      BillItem astunititem = cardPanel.getHeadItem(BuyLargessHVO.CBUYUNITID);
      FilterMeasdocRefUtils astunitFilter =
          new FilterMeasdocRefUtils(astunititem);

      astunitFilter.setMaterialPk(material);
    }
    e.setReturnValue(true);
  }

}
