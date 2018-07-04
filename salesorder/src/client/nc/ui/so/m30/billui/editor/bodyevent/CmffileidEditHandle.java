package nc.ui.so.m30.billui.editor.bodyevent;

import java.util.HashMap;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.pubitf.bd.feature.ffile.IPubFFileQueryService;
import nc.ui.bd.feature.ref.model.FFileSkuCodeRefModel;
import nc.ui.ml.NCLangRes;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.so.m30.billui.view.SaleOrderBillForm;
import nc.ui.so.m30.pub.SaleOrderCalculator;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.ui.so.pub.util.BodyEditEventUtil;
import nc.ui.so.pub.util.BodyItemEditCheckUtil;
import nc.ui.uif2.ShowStatusBarMsgUtil;
import nc.vo.bd.feature.ffile.param.FFileDlgParam;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.util.FeatureSelectUtil;
import nc.vo.so.pub.SOConstant;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.rule.SOCalConditionRule;

/**
 * 特征码编辑事件
 * 
 * @author zhangby5
 * @version 636
 */
public class CmffileidEditHandle {

	public void beforeEdit(CardBodyBeforeEditEvent e) {

		BillCardPanel cardPanel = e.getBillCardPanel();
		IKeyValue keyvalue = new CardKeyValue(cardPanel);
		int row = e.getRow();
		String cmaterialvid = keyvalue.getBodyStringValue(row,
				SaleOrderBVO.CMATERIALVID);
		if (PubAppTool.isNull(cmaterialvid)) {
			ShowStatusBarMsgUtil.showStatusBarMsg(NCLangRes.getInstance()
					.getStrByID("4006011_0", "04006011-0508")/* 请先录入物料 */, e
					.getContext());
			e.setReturnValue(false);
		}
		UIRefPane uiPane = (UIRefPane) cardPanel.getBodyItem(e.getKey())
				.getComponent();
		FFileDlgParam param = new FFileDlgParam();
		param.setCmaterialvid(cmaterialvid);
		param.setCmaterialid(keyvalue.getBodyStringValue(row,
				SaleOrderBVO.CMATERIALID));
		param.setPk_group(keyvalue.getHeadStringValue(SaleOrderHVO.PK_GROUP));
		param.setLoginContext(e.getContext());
		FFileSkuCodeRefModel refModel = (FFileSkuCodeRefModel) uiPane
				.getRefModel();
		refModel.setParams(param);
		refModel.setUiRefPane(uiPane);
		refModel.reset();
	}

	public void afterEdit(CardBodyAfterEditEvent e, SaleOrderBillForm billform) {

		BillCardPanel cardPanel = e.getBillCardPanel();
		IKeyValue keyValue = new CardKeyValue(cardPanel);
		int[] rows = BodyEditEventUtil.getInstance().getAfterEditRow(e);
		int length = rows.length;
		if (length == 0) {
			return;
		}
		String cmffileid = keyValue.getBodyStringValue(rows[0],
				SaleOrderBVO.CMFFILEID);
		String corigcurrencyid = keyValue
				.getHeadStringValue(SaleOrderHVO.CORIGCURRENCYID);

		Map<String, UFDouble> nmffilepriceMap = new HashMap<>();
		if (!PubAppTool.isNull(cmffileid)) {
			// 根据币种和特征码到匹配特征价目表得到特征价
			IPubFFileQueryService ffileQueryService = NCLocator.getInstance()
					.lookup(IPubFFileQueryService.class);
			try {
				nmffilepriceMap = ffileQueryService.queryPriceByPK(
						new String[] { cmffileid }, corigcurrencyid);
			} catch (BusinessException ex) {
				ExceptionUtils.wrappException(ex);
			}
		}
		// 含税优先或无税优先
		boolean isTaxPrior = SOCalConditionRule.isTaxPrior();
		// 若特征价位空则特征价设为0
		UFDouble nmffileprice = nmffilepriceMap == null ? UFDouble.ZERO_DBL
				: nmffilepriceMap.get(cmffileid);
		for (int row : rows) {
			// 处理得到的特征价
			this.processReturnPrice(keyValue, row, nmffileprice, cmffileid,
					isTaxPrior, billform);
			keyValue.setBodyValue(row, SaleOrderBVO.NMFFILEPRICE, nmffileprice);
		}
		// 清空特征码暂存VO
		FeatureSelectUtil.clearRowsValue(keyValue, rows, SOConstant.AGGFFILEVO);
		SaleOrderCalculator calculator = new SaleOrderCalculator(cardPanel);
		calculator.calculate(rows, isTaxPrior ? SaleOrderBVO.NQTORIGTAXNETPRC
				: SaleOrderBVO.NQTORIGNETPRICE);

	}
	
	private void processReturnPrice(IKeyValue keyValue, int row,
			UFDouble nmffileprice, String cmffileid, boolean isTaxPrior,
			SaleOrderBillForm billform) {
		if (isTaxPrior) {
			UFDouble nqtorigtaxprice = keyValue.getBodyUFDoubleValue(row,
					SaleOrderBVO.NQTORIGTAXNETPRC);
			keyValue.setBodyValue(row, SaleOrderBVO.NQTORIGTAXNETPRC, this
					.getTotalPrice(nqtorigtaxprice, nmffileprice, cmffileid,
							row, keyValue, billform));
		} else {
			UFDouble nqtorigprice = keyValue.getBodyUFDoubleValue(row,
					SaleOrderBVO.NQTORIGNETPRICE);
			keyValue.setBodyValue(row, SaleOrderBVO.NQTORIGNETPRICE, this
					.getTotalPrice(nqtorigprice, nmffileprice, cmffileid, row,
							keyValue, billform));
		}
	}

	private UFDouble getTotalPrice(UFDouble nprice, UFDouble nmffileprice,
			String cmffileid, int row, IKeyValue keyValue,
			SaleOrderBillForm billform) {
		// 原价格为空且不能编辑，则赋值为空
		if (null == nprice
				&& !BodyItemEditCheckUtil.checkPriceEditable(billform, row)) {
			return null;
		}
		return MathTool.add(nprice, MathTool.sub(nmffileprice, keyValue
					.getBodyUFDoubleValue(row, SaleOrderBVO.NMFFILEPRICE)));
	}
}
