/**
 * 
 */
package nc.ui.so.m30.billui.editor.headevent;

import nc.bs.ra.common.UFDoubleUtil;
import nc.ui.ml.NCLangRes;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent;
import nc.ui.so.m30.billui.view.SaleOrderBillForm;
import nc.ui.so.m30.pub.SaleOrderCalculator;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.vo.arap.verify.UFDoubleTool;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.pub.keyvalue.IKeyValue;

/**
 * @author wangzym
 * @version 2017年3月2日 下午7:39:18
 */
public class JgqzHeadAfterEditHandler {

	private SaleOrderBillForm billform;

	public JgqzHeadAfterEditHandler() {
		// TODO 自动生成的构造函数存根
	}

	// 价格取整的编辑后事件
	public void afterEdit(CardHeadTailAfterEditEvent e) {

		BillCardPanel panel = e.getBillCardPanel();
		IKeyValue keyValue = new CardKeyValue(panel);
		int blens = keyValue.getBodyCount();
		// 判断是否价格取整
		int yon = (int) (keyValue.getHeadValue("djqz") == null ? 2 : keyValue
				.getHeadValue("djqz"));
		// 表体的无税单价
		UFDouble oldnqtorigprice;
		UFDouble nqtorigprice;
		if (!MathTool.isZero(keyValue.getHeadUFDoubleValue("dlfl"))
				&& keyValue.getHeadUFDoubleValue("dlfl") != null) {
			if (yon == 1) {
				for (int i = 0; i < blens; i++) {
					// 这样取值有问题，如果代理费率变化，不能沿用旧的值要重新计算出旧的表体单价，再重新取整；
					oldnqtorigprice = keyValue.getBodyUFDoubleValue(i,
							"nqtorigprice") == null ? UFDouble.ZERO_DBL
							: keyValue.getBodyUFDoubleValue(i, "nqtorigprice");
					// 如果oldnqtorigprice为空可能两种情况，或者第一次选择为需要取整要重新计算一遍再进行取整
					if (oldnqtorigprice == UFDouble.ZERO_DBL
							|| oldnqtorigprice.toString().equals("0.00")) {

						this.reCal(e, i);
					}
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
				}
			} else if (yon == 2) {
				DlflHeadAfterEdithandler handler = new DlflHeadAfterEdithandler();
				handler.setBillform(this.billform);
				handler.afterEdit(e);

			}
		}

	}

	public void reCal(CardHeadTailAfterEditEvent e, int i) {

		BillCardPanel panel = e.getBillCardPanel();
		IKeyValue keyValue = new CardKeyValue(panel);
		int blens = keyValue.getBodyCount();
		// 表体采购价格
		UFDouble cgjg;
		// 表体汇率
		UFDouble exchange_rate;
		// 表头代理费率
		UFDouble dlfl;
		// 表体税率
		UFDouble ntaxrate;

		UFDouble nqtorigprice = UFDouble.ZERO_DBL;
		int djqz = keyValue.getHeadIntegerValue("djqz") == null ? 2 : keyValue
				.getHeadIntegerValue("djqz");
		cgjg = keyValue.getBodyUFDoubleValue(i, "cgjg");
		exchange_rate = keyValue.getBodyUFDoubleValue(i, "exchange_rate");
		ntaxrate = keyValue.getBodyUFDoubleValue(i, "ntaxrate");
		dlfl = keyValue.getHeadUFDoubleValue("dlfl");
		// 如果自制,可能会多加一行数据，此行数据不计算
		if (cgjg != null && exchange_rate != null && ntaxrate != null
				&& dlfl != null) {

			nqtorigprice = (cgjg.multiply(exchange_rate).multiply(dlfl))
					.div(ntaxrate.div(100.00).add(new Double(1).doubleValue()));
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
