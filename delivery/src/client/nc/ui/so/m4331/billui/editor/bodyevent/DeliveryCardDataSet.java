package nc.ui.so.m4331.billui.editor.bodyevent;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.calculator.data.BillCardPanelDataSet;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.calculator.data.IRelationForItems;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.so.m4331.entity.DeliveryBVO;

public class DeliveryCardDataSet extends BillCardPanelDataSet {
  private final UFDouble hundred = new UFDouble(0.01);

  public DeliveryCardDataSet(BillCardPanel cardPanel, int row,
      IRelationForItems item) {
    super(cardPanel, row, item);
  }

  @Override
  public UFDouble getNdiscountrate() {
    UFDouble disrate = super.getNdiscountrate();
    
    UFDouble itemdisrate =
        ValueUtils.getUFDouble(this
            .getAttributeValue(DeliveryBVO.NITEMDISCOUNTRATE));
    itemdisrate = itemdisrate == null ? UFDouble.ZERO_DBL : itemdisrate;
    disrate = disrate.multiply(itemdisrate).multiply(this.hundred);
    return disrate;
  }

  @Override
  public void setNdiscountrate(UFDouble value) {
    return;
  }
}
