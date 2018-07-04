package nc.ui.so.m4331.billui.editor.bodyevent;

import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.ui.so.m4331.billui.pub.rule.CheckCountryChangeRule;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.ui.so.pub.util.BodyEditEventUtil;
import nc.vo.pubapp.scale.PosEnum;
import nc.vo.so.m4331.entity.DeliveryBVO;
import nc.vo.so.pub.keyvalue.IKeyValue;

public class ReceCustEditHandler {

	public void afterEdit(CardBodyAfterEditEvent e) {
		int[] rows = BodyEditEventUtil.getInstance().getAfterEditRow(e);
		IKeyValue keyValue = new CardKeyValue(e.getBillCardPanel());
		CheckCountryChangeRule rule = new CheckCountryChangeRule(keyValue);
		rule.checkRecCountry(rows, DeliveryBVO.CRECEIVECUSTID, 
				PosEnum.body, new String[]{(String) e.getOldValue()});		
	}
}
