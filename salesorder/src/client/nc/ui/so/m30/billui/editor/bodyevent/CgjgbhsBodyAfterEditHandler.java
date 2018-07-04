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
 * @author 王梓懿
 * @version 2018年6月22日 下午13:44:11 
 * 解决以采购无税金额为基准的业务逻辑
 */

public class CgjgbhsBodyAfterEditHandler {

	private SaleOrderBillForm billform;

	// 价格取整的编辑后事件
	public void afterEdit(CardBodyAfterEditEvent e) {

		int row = e.getRow();
		// 输入的不含税的采购价格
		UFDouble cgjgbhs = (UFDouble) e.getValue();
		// 原采购价格
		UFDouble oldcgjg = (UFDouble) e.getBillCardPanel().getBodyValueAt(row,
				"cgjg");
		// 税率
		UFDouble ntaxrate = e.getBillCardPanel()
				.getBodyValueAt(row, "ntaxrate") == null ? UFDouble.ZERO_DBL
				: ((UFDouble) e.getBillCardPanel().getBodyValueAt(row,
						"ntaxrate")).div(new UFDouble(100));
		// 数量
		UFDouble nastnum = e.getBillCardPanel().getBodyValueAt(row, "nastnum") == null ? UFDouble.ZERO_DBL
				: (UFDouble) e.getBillCardPanel()
						.getBodyValueAt(row, "nastnum");
		// 最新指示，根据采购无税价格算采购含税价格 2018-06-22
		UFDouble cgjg = cgjgbhs.add(cgjgbhs.multiply(ntaxrate));
		e.getBillCardPanel().setBodyValueAt(cgjg, row, "cgjg");
		// 采购价税合计 cgjshj->cgjg*nastnum;
		e.getBillCardPanel().setBodyValueAt(nastnum.multiply(cgjg), row,
				"cgjshj");
		// 采购税额 cgse->cgjg*nastnum-cgjg/(1+ntaxrate/100)*nastnum;
		e.getBillCardPanel().setBodyValueAt(
				nastnum.multiply(cgjg).sub(cgjgbhs.multiply(nastnum)), row,
				"cgse");

		// 走一下采购价格的编辑后事件
		CgjgBodyAfterEditHandler handler = new CgjgBodyAfterEditHandler();
		handler.setBillform(billform);
		handler.afterEdit(new CardBodyAfterEditEvent(e.getBillCardPanel(),
				"bodytable1", row, "cgjg", ntaxrate, oldcgjg));

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
