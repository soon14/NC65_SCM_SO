package nc.ui.so.m30.billui.editor.headevent;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.AppUiContext;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailBeforeEditEvent;
import nc.ui.so.m30.billui.rule.AssociateConstractRule;
import nc.ui.so.m30.billui.rule.ClearBodyValueRule;
import nc.ui.so.m30.billui.rule.MatchLargessRule;
import nc.ui.so.m30.billui.rule.SrcTypeRule;
import nc.ui.so.m30.billui.view.SaleOrderBillForm;
import nc.ui.so.m30.pub.SaleOrderCalculator;
import nc.ui.so.m30.pub.SaleOrderFindPriceConfig;
import nc.ui.so.pub.findprice.FindSalePrice;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.rule.HeadTotalCalculateRule;
import nc.vo.so.m30.util.SaleOrderClientContext;
import nc.vo.so.m30trantype.entity.M30TranTypeVO;
import nc.vo.so.m32.entity.SaleInvoiceHVO;
import nc.vo.so.pub.SOItemKey;
import nc.vo.so.pub.enumeration.AskPriceRule;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.rule.BodyValueRowRule;
import nc.vo.so.pub.rule.SOExchangeRateRule;
import nc.vo.so.pub.rule.SOGlobalExchangeRate;
import nc.vo.so.pub.rule.SOGroupExchangeRate;
import nc.vo.so.pub.util.SOSysParaInitUtil;

public class OrigCurrencyEditHandler {

	private SaleOrderBillForm billform;

	public void setBillform(SaleOrderBillForm billform) {
		this.billform = billform;
	}

	public SaleOrderBillForm getBillform() {
		return this.billform;
	}

	public void beforeEdit(CardHeadTailBeforeEditEvent e) {

		// 来源是合同的：币种不可以编辑
		BillCardPanel cardPanel = e.getBillCardPanel();
		IKeyValue keyValue = new CardKeyValue(cardPanel);

		SrcTypeRule srcrule = new SrcTypeRule(keyValue);
		e.setReturnValue(Boolean.valueOf(!srcrule.isBillSrcCT()));

	}

	public void afterEdit(CardHeadTailAfterEditEvent e) {

		BillCardPanel cardPanel = e.getBillCardPanel();
		IKeyValue keyValue = new CardKeyValue(cardPanel);
		BodyValueRowRule countutil = new BodyValueRowRule(keyValue);
		int[] rows = countutil.getMarNotNullRows();

		// 1.计算表体行折本汇率
		SOExchangeRateRule exraterule = new SOExchangeRateRule(keyValue);
		exraterule.calcBodyExchangeRates(rows);
		// 2.全局本位币汇率
		SOGlobalExchangeRate globalraterule = new SOGlobalExchangeRate(keyValue);
		if (globalraterule
				.isGlobalExchgRateChange(SaleOrderHVO.CORIGCURRENCYID)) {
			globalraterule.calcGlobalExchangeRate(rows);
		}

		// 3.集团本位币汇率
		SOGroupExchangeRate groupraterule = new SOGroupExchangeRate(keyValue);
		if (groupraterule.isGroupExchgRateChange(SaleOrderHVO.CORIGCURRENCYID)) {
			groupraterule.calcGroupExchangeRate(rows);
		}
		// 4.数量单价金额运算
		SaleOrderCalculator calculator = new SaleOrderCalculator(cardPanel);
	  int[] changerows = exraterule.getRateChangeRow();
		calculator.calculate(changerows, SaleOrderBVO.NEXCHANGERATE);

		// 5.询价
		String tranTypeCode = keyValue
				.getHeadStringValue(SaleOrderHVO.VTRANTYPECODE);
		String pk_group = AppUiContext.getInstance().getPkGroup();
		SaleOrderClientContext cache = this.getBillform().getM30ClientContext();
		M30TranTypeVO m30transvo = cache.getTransType(tranTypeCode, pk_group);
		SaleOrderFindPriceConfig config = new SaleOrderFindPriceConfig(
				cardPanel, m30transvo);
		ClearBodyValueRule clearrule = new ClearBodyValueRule(keyValue, null);
		
		// add 2015.6.26 by zhangby5 20150127 陈恩宇提出：修改币种，清空单价金额。
		// 经刘达确认，修改币种，需要根据询价条件判断是否要询价，不询价则清空单价金额，任何情况都要清空特征码相关字段
		// 先清空特征码相关字段（特征价会影响询到的价格）
		clearrule.clearAllFfileKey(rows);
		if (isFindPrice(config, keyValue)) {
			FindSalePrice findPrice = new FindSalePrice(cardPanel, config);
			findPrice.findPriceAfterEdit(rows, SaleOrderHVO.CORIGCURRENCYID);
		} else {
			clearrule.clearAllPriceKey(rows);
		}
		// end
		
		// 6.关联合同
		String trantypecode = keyValue
				.getHeadStringValue(SaleOrderHVO.VTRANTYPECODE);
		SaleOrderClientContext clientcontex = this.billform
				.getM30ClientContext();
		M30TranTypeVO trantypevo = clientcontex.getTransType(trantypecode,
				pk_group);
		AssociateConstractRule asctrule = new AssociateConstractRule(cardPanel,
				trantypevo);
		asctrule.associateCT(rows);

		// 7.整单重新匹配买赠
		MatchLargessRule matchlarrule = new MatchLargessRule(cardPanel,
				trantypevo);
		matchlarrule.matchLargess(rows);
		
		for(int row : rows){
	    	// 计算冲抵前金额
	        UFDouble norigtaxmny =
	            keyValue.getBodyUFDoubleValue(row, SaleOrderBVO.NORIGTAXMNY);
	        UFDouble norigsubmny =
	            keyValue.getBodyUFDoubleValue(row, SaleOrderBVO.NORIGSUBMNY);
	        keyValue.setBodyValue(row, SaleOrderBVO.NBFORIGSUBMNY,
	            MathTool.add(norigtaxmny, norigsubmny));
	    }

		// 8.表头价税合计
		HeadTotalCalculateRule totalrule = new HeadTotalCalculateRule(keyValue);
		totalrule.calculateHeadTotal();
		
  	//9.重新选择币种之后，直接清空客户银行账号, add by liylr 2015-07-11
    keyValue.setHeadValue(SaleOrderHVO.CCUSTBANKACCID, null);
    keyValue.setHeadValue(SaleOrderHVO.CCUSTBANKID, null);

	}

	private boolean isFindPrice(SaleOrderFindPriceConfig config,
			IKeyValue keyValue) {

		// 1.询价策略,判定是否询价
		Integer askrule = config.getAskPriceRule();
		if (AskPriceRule.ASKPRICE_NO.equalsValue(askrule)) {
			return false;
		}
		// 2.编辑字段是否触发询价
		if (!this.isTrigFindPrice(SaleOrderHVO.CORIGCURRENCYID, keyValue)) {
			return false;
		}

		return true;
	}

	private boolean isTrigFindPrice(String editkey, IKeyValue keyValue) {
		// 判断如果是价格项 就触发询价
		if (editkey.equals(SOItemKey.CPRICEITEMID)) {
			return true;
		}
		// 销售询价触发条件，判定是否询价
		String pk_org = keyValue.getHeadStringValue(SOItemKey.PK_ORG);
		String[] so21 = null;

		so21 = SOSysParaInitUtil.getSO21(pk_org);

		// 询价触发条件为空
		if (null == so21 || so21.length == 0) {
			return false;
		}
		for (String condition : so21) {
			if (editkey.equals(condition)) {
				return true;
			}
		}
		return false;
	}
}
