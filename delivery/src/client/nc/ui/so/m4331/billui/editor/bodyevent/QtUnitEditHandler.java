package nc.ui.so.m4331.billui.editor.bodyevent;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.scmpub.ref.FilterMeasdocRefUtils;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.m4331.entity.DeliveryBVO;
import nc.vo.so.m4331.rule.UnitChangeRateRule;

public class QtUnitEditHandler {
  /**
   * 方法功能描述：发货单报价单位编辑后获得报价换算率
   * 
   * @author 祝会征
   * @time 2010-6-7 下午03:47:39
   */
  public void afterEdit(CardBodyAfterEditEvent e) {
    BillCardPanel cardPanel = e.getBillCardPanel();
    CardKeyValue keyValue = new CardKeyValue(cardPanel);
    UnitChangeRateRule chgraterule = new UnitChangeRateRule(keyValue);
    chgraterule.calcQTChangeRate(e.getRow());
  }

  /**
   * 方法功能描述：发货单表体报价单位编辑前事件
   * 
   * @author 祝会征
   * @time 2010-6-7 下午03:42:00
   */
  public void beforeEdit(CardBodyBeforeEditEvent e) {
    BillCardPanel cardPanel = e.getBillCardPanel();
    CardKeyValue keyValue = new CardKeyValue(cardPanel);
    // 物料为空，不可编辑
    String material =
        keyValue.getBodyStringValue(e.getRow(), DeliveryBVO.CMATERIALID);

    
    if (PubAppTool.isNull(material)) {
      e.setReturnValue(Boolean.FALSE);
      return;
    }
    // 物料不为空，只能参照物料对应的计量单位
    BillItem item = cardPanel.getBodyItem(DeliveryBVO.CQTUNITID);
    FilterMeasdocRefUtils astunitFilter = new FilterMeasdocRefUtils(item);
    astunitFilter.setMaterialPk(material);
  }

}
