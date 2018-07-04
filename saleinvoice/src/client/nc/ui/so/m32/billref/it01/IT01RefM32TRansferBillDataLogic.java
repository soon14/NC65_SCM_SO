package nc.ui.so.m32.billref.it01;

import nc.ui.pubapp.billref.dest.DefaultBillDataLogic;

public class IT01RefM32TRansferBillDataLogic extends DefaultBillDataLogic{

	@Override
	public void doTransferAddLogic(Object selectedData) {

		// 把数据设置在界面上
		super.doTransferAddLogic(selectedData);

//		BillCardPanel cardPanel = this.getBillForm().getBillCardPanel();
//		IKeyValue keyValue = new CardKeyValue(cardPanel);
//
//		// 1.表头合计
//		HeadTotalCalculateRule totalrule = new HeadTotalCalculateRule(keyValue);
//		totalrule.calculateHeadTotal();
	}
}
