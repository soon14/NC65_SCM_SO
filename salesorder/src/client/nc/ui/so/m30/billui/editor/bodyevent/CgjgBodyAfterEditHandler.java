/**
 * 
 */
package nc.ui.so.m30.billui.editor.bodyevent;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.ui.so.m30.billui.view.SaleOrderBillForm;
import nc.ui.so.m30.pub.SaleOrderCalculator;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.pub.keyvalue.IKeyValue;

/**
 * @author wangzym
 * @version 2017年3月2日 下午7:39:18
 */

/**
 *  原本用于表头“单价取整”编辑后事件 改造成“采购价格” 的编辑后事件 要求与其“单价取整”编辑后事件一致
 * @author Daniel
 *
 */
public class CgjgBodyAfterEditHandler {

	private SaleOrderBillForm billform;

	public CgjgBodyAfterEditHandler() {
		// TODO 自动生成的构造函数存根
	}

	// 价格取整的编辑后事件
	public void afterEdit(CardBodyAfterEditEvent e) {

		BillCardPanel panel = e.getBillCardPanel();
		IKeyValue keyValue = new CardKeyValue(panel);
		int i = e.getRow();
		// 判断是否价格取整
		int yon = (int) (keyValue.getHeadValue("djqz") == null ? 2 : keyValue
				.getHeadValue("djqz"));
		// 表体的无税单价
		UFDouble oldnqtorigprice;
		UFDouble nqtorigprice;
		if (!MathTool.isZero(keyValue.getHeadUFDoubleValue("dlfl"))
				&& keyValue.getHeadUFDoubleValue("dlfl") != null) {
			if (yon == 1) {
				// 这样取值有问题，如果代理费率变化，不能沿用旧的值要重新计算出旧的表体单价，再重新取整；
				oldnqtorigprice = keyValue.getBodyUFDoubleValue(i,
						"nqtorigprice") == null ? UFDouble.ZERO_DBL
						: keyValue.getBodyUFDoubleValue(i, "nqtorigprice");
				

				// 表头汇率
				UFDouble exchange_rate = keyValue.getHeadUFDoubleValue("exchange_rate");
				this.reCal(e, i,exchange_rate);
				
				oldnqtorigprice = keyValue.getBodyUFDoubleValue(i,
						"nqtorigprice") == null ? UFDouble.ZERO_DBL
						: keyValue.getBodyUFDoubleValue(i, "nqtorigprice");
				
				//====lijj 取整逻辑修改 先按两位精度进行处理 再取整  ====
				nqtorigprice = oldnqtorigprice.setScale(2, UFDouble.ROUND_HALF_UP);
				nqtorigprice = nqtorigprice.setScale(0, UFDouble.ROUND_HALF_UP);
				//====lijj 取整逻辑修改 先按两位精度进行处理 再取整  ====
				/*
				double getInt = Math.round(oldnqtorigprice.doubleValue());
				nqtorigprice = new UFDouble(getInt); // (oldnqtorigprice);
				*/
				
				keyValue.setBodyValue(i, "nqtorigprice", nqtorigprice);
				/**
				 * add by lyw 计算金额等 2017-6-9
				 */
				keyValue.setBodyValue(i, "nqtorignetprice", nqtorigprice);
			    SaleOrderCalculator calculator = new SaleOrderCalculator(panel);
			    calculator.calculate(i, SaleOrderBVO.NQTORIGNETPRICE);
			} else if (yon == 2) {
				// 表体采购价格
				UFDouble cgjg = keyValue.getBodyUFDoubleValue(i, "cgjg");
				// 表体汇率
				UFDouble exchange_rate = keyValue.getHeadUFDoubleValue("exchange_rate");
				// 表头代理费率
				UFDouble dlfl = keyValue.getHeadUFDoubleValue("dlfl");
				// 表体税率
				UFDouble ntaxrate = keyValue.getBodyUFDoubleValue(i, "ntaxrate");
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

				nqtorigprice = UFDouble.ZERO_DBL;

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
					keyValue.setBodyValue(i, "nqtorigprice", nqtorigprice);
					keyValue.setBodyValue(i, "nqtorignetprice", nqtorigprice);
				    SaleOrderCalculator calculator = new SaleOrderCalculator(panel);
				    calculator.calculate(i, SaleOrderBVO.NQTORIGNETPRICE);
				}
			}
		}

	}

	public void reCal(CardBodyAfterEditEvent e, int i, UFDouble exchange_rate) {

		BillCardPanel panel = e.getBillCardPanel();
		IKeyValue keyValue = new CardKeyValue(panel);
		// 表体采购价格
		UFDouble cgjg;

		
		// 表头代理费率
		UFDouble dlfl;
		// 表体税率
		UFDouble ntaxrate;

		UFDouble nqtorigprice = UFDouble.ZERO_DBL;
		cgjg = keyValue.getBodyUFDoubleValue(i, "cgjg");

		ntaxrate = keyValue.getBodyUFDoubleValue(i, "ntaxrate");
		dlfl = keyValue.getHeadUFDoubleValue("dlfl");
		//采购币种
		String buyccurrencyid = keyValue.getHeadStringValue("buyccurrencyid");
		//代理费率，如果采购币种为人民币，费率按百分比计算
		if (buyccurrencyid.equals("1002Z0100000000001K1")) {
			dlfl = dlfl.div(100.00).add(new UFDouble(1).doubleValue());
		} 
		
		// 如果自制,可能会多加一行数据，此行数据不计算
		if (cgjg != null && exchange_rate != null && ntaxrate != null
				&& dlfl != null) {

			
			if (buyccurrencyid.equals("1002Z0100000000001K1")) {
				nqtorigprice = cgjg.multiply(dlfl)
						.div(ntaxrate.div(100.00).add(new Double(1).doubleValue()));
			}else{
				nqtorigprice = cgjg.multiply(dlfl).multiply(exchange_rate)
					.div(ntaxrate.div(100.00).add(new Double(1).doubleValue()));
			}
			keyValue.setBodyValue(i, "nqtorigprice", nqtorigprice);

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
