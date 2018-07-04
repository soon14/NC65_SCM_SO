/**
 * 
 */
package nc.ui.so.m30.billui.editor.headevent;

import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent;
import nc.ui.so.m30.billui.view.SaleOrderBillForm;
import nc.ui.so.m30.pub.SaleOrderCalculator;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.rule.BodyValueRowRule;

/**
 * @author wangzym
 * @version 2017年3月2日 下午3:57:03
 */
public class DlflHeadAfterEdithandler {

	private SaleOrderBillForm billform;

	public DlflHeadAfterEdithandler() {
		// TODO 自动生成的构造函数存根
	}

	public void afterEdit(CardHeadTailAfterEditEvent e) {

		BillCardPanel panel = e.getBillCardPanel();
		IKeyValue keyValue = new CardKeyValue(panel);
		//物料为空的行不参与计算
		BodyValueRowRule countutil = new BodyValueRowRule(keyValue);
		int[] rows = countutil.getMarNotNullRows();
		// 表体采购价格
		UFDouble cgjg;
		// 表体汇率
		UFDouble exchange_rate = keyValue.getHeadUFDoubleValue("exchange_rate");
		// 表头代理费率
		UFDouble dlfl = keyValue.getHeadUFDoubleValue("dlfl");
		// 表体税率
		UFDouble ntaxrate;
		//采购币种
		String buyccurrencyid = keyValue.getHeadStringValue("buyccurrencyid");
		if (exchange_rate == null) {
			ExceptionUtils.wrappBusinessException("汇率不能为空，请输入汇率！");
		}
		if ( buyccurrencyid == null ) {
			ExceptionUtils.wrappBusinessException("采购币种不能为空，请输入采购币种！");
		}
		if ( dlfl == null ) {
			ExceptionUtils.wrappBusinessException("代理费率不能为空，请输入代理费率！");
		}
		//代理费率，如果采购币种为人民币，费率按百分比计算
		if (buyccurrencyid.equals("1002Z0100000000001K1")) {
			dlfl = dlfl.div(100.00).add(new UFDouble(1).doubleValue());
		} else {
			dlfl = keyValue.getHeadUFDoubleValue("dlfl");
		}

		UFDouble nqtorigprice = UFDouble.ZERO_DBL;
		int djqz = keyValue.getHeadIntegerValue("djqz") == null ? 2 : keyValue
				.getHeadIntegerValue("djqz");
		for (int i = 0; i < rows.length; i++) {
			//采购价格
			cgjg = keyValue.getBodyUFDoubleValue(rows[i], "cgjg");
			//税率
			ntaxrate = keyValue.getBodyUFDoubleValue(rows[i], "ntaxrate");
			
			// 如果自制,可能会多加一行数据，此行数据不计算
/*			if (cgjg != null && exchange_rate != null && ntaxrate != null && dlfl != null) {
				nqtorigprice = (cgjg.multiply(exchange_rate).multiply(dlfl))
						.div(ntaxrate.div(100.00).add(new Double(1).doubleValue()));
				//keyValue.setBodyValue(i, "nqtorigprice", nqtorigprice);
				// 此处查看是否需要取整
				if (djqz == 1) {
					// 需要取整，直接调取整类
					JgqzHeadAfterEditHandler handler = new JgqzHeadAfterEditHandler();
					handler.setBillform(this.billform);
					handler.afterEdit(e);
				}
			}
*/		
		 /**
		 * add by lyw 2017-6-9
		 * 根据代理费率，计算无税单价
		 */
			if (cgjg != null ) {
				nqtorigprice = cgjg;
			} else {
				ExceptionUtils.wrappBusinessException ("采购单价不能为空，请先输入采购单价");
			}
		if (dlfl != null) {
			if (buyccurrencyid.equals("1002Z0100000000001K1")) {
				nqtorigprice = nqtorigprice.multiply(dlfl);
				nqtorigprice = nqtorigprice.div(ntaxrate.div(100).add(new UFDouble(1).doubleValue()));
			} else {
				nqtorigprice = nqtorigprice.multiply(dlfl).multiply(exchange_rate).div(ntaxrate.div(100).add(new UFDouble(1).doubleValue()));
			}
			keyValue.setBodyValue(rows[i], "nqtorigprice", nqtorigprice);
			keyValue.setBodyValue(rows[i], "nqtorignetprice", nqtorigprice);
		    SaleOrderCalculator calculator = new SaleOrderCalculator(panel);
		    calculator.calculate(rows[i], SaleOrderBVO.NQTORIGNETPRICE);
			if (djqz == 1) {
				// 需要取整，直接调取整类
				JgqzHeadAfterEditHandler handler = new JgqzHeadAfterEditHandler();
				handler.setBillform(this.billform);
				handler.afterEdit(e);
			}
		}
	}

}

	/**
	 * @return billform
	 */
	public SaleOrderBillForm getBillform() {
		return billform;
	}

	/**
	 * @param billform
	 *            要设置的 billform
	 */
	public void setBillform(SaleOrderBillForm billform) {
		this.billform = billform;
	}

}
