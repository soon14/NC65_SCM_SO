package nc.ui.so.m4331.billui.editor.headevent;

import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent;
import nc.ui.so.m4331.billui.pub.rule.CheckCountryChangeRule;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.vo.pubapp.scale.PosEnum;
import nc.vo.so.m4331.entity.DeliveryBVO;
import nc.vo.so.m4331.entity.DeliveryHVO;
import nc.vo.so.pub.keyvalue.IKeyValue;

public class HeadAfterEditHandler implements
    IAppEventHandler<CardHeadTailAfterEditEvent> {

  @Override
  public void handleAppEvent(CardHeadTailAfterEditEvent e) {
    String editKey = e.getKey();
    if (DeliveryHVO.DBILLDATE.equals(editKey)) {
      int row = e.getBillCardPanel().getRowCount();
      for (int i = 0; i < row; i++) {
        e.getBillCardPanel().setBodyValueAt(e.getValue(), i,
            DeliveryBVO.DBILLDATE);
      }
    } 
    else if(DeliveryHVO.CTRADEWORDID.equals(editKey)) {
      int row = e.getBillCardPanel().getRowCount();
      int[] rows = new int[row]; 
      for(int i = 0; i < row; i++) {
    	  rows[i] = row;
      }
      String[] oldValues = new String[]{e.getOldValue().toString()};
      IKeyValue value = new CardKeyValue(e.getBillCardPanel());
      CheckCountryChangeRule rule = new CheckCountryChangeRule(value);
      rule.checkRecCountry(rows, e.getKey(), PosEnum.head, oldValues);
    }
  }
}
