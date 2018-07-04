package nc.ui.so.m30.billui.editor.bodyevent;

import java.util.Arrays;
import java.util.List;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillScrollPane;
import nc.ui.pubapp.AppUiContext;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.ui.so.m30.billui.editor.headevent.JhqHeadAndBodyEditHandler;
import nc.ui.so.m30.billui.view.SaleOrderBillForm;
import nc.ui.so.m30.pub.SaleOrderCalculator;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.ui.so.pub.util.BodyEditEventUtil;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.util.SaleOrderClientContext;
import nc.vo.so.m30trantype.entity.M30TranTypeVO;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.util.SOFreeUtil;

/**
 * 订单表体编辑后派发类
 * 
 * @since 6.0
 * @version 2011-6-9 下午01:28:27
 * @author fengjb
 */
public class BodyAfterEditHandler implements
		IAppEventHandler<CardBodyAfterEditEvent> {

	private SaleOrderBillForm billform;

	public SaleOrderBillForm getBillform() {
		return this.billform;
	}

	@Override
	public void handleAppEvent(CardBodyAfterEditEvent e) {

		int[] editrows = BodyEditEventUtil.getInstance().getAfterEditRow(e);
		if (null == editrows) {
			return;
		}

		BillCardPanel cardPanel = e.getBillCardPanel();
		// boolean istotalshow = cardPanel.getBodyPanel().isTatolRow();
		BillScrollPane bodypanl = cardPanel.getBodyPanel();
		bodypanl.setTotalRowShow(false);

		String editKey = e.getKey();
		// 物料
		if (SaleOrderBVO.CMATERIALVID.equals(editKey)) {
			MaterialEditHandler handler = new MaterialEditHandler();
			handler.afterEdit(e, this.billform);
		}
		// 客户物料码(V63新加)
		else if (SaleOrderBVO.CCUSTMATERIALID.equals(editKey)) {
			CustMaterialEditHandler handler = new CustMaterialEditHandler();
			handler.afterEdit(e, this.billform);
		}
		// 数量
		else if (SaleOrderBVO.NASTNUM.equals(editKey)) {
			AstNumEditHandler handler = new AstNumEditHandler();
			handler.afterEdit(e, this.billform);
		}
		// 主数量
		else if (SaleOrderBVO.NNUM.equals(editKey)) {
			NumEditHandler handler = new NumEditHandler();
			handler.afterEdit(e, this.billform);
		}
		// 报价单位数量
		else if (SaleOrderBVO.NQTUNITNUM.equals(editKey)) {
			QtUnitNumEditHandler handler = new QtUnitNumEditHandler();
			handler.afterEdit(e, this.billform);
		}
		// 单位
		else if (SaleOrderBVO.CASTUNITID.equals(editKey)) {
			AstUnitEditHandler handler = new AstUnitEditHandler();
			handler.afterEdit(e, this.billform);
		}
		// 报价计量单位
		else if (SaleOrderBVO.CQTUNITID.equals(editKey)) {
			QtUnitEditHandler handler = new QtUnitEditHandler();
			handler.afterEdit(e);
		}
		// 赠品标志
		else if (SaleOrderBVO.BLARGESSFLAG.equals(editKey)) {
			LargessFlagEditHandler handler = new LargessFlagEditHandler();
			handler.afterEdit(e);
		}
		// 批次号
		else if (SaleOrderBVO.VBATCHCODE.equals(editKey)) {
			BatchCodeEditHandler handler = new BatchCodeEditHandler();
			handler.afterEdit(e, this.billform);
		}
		// 质量等级
		else if (SaleOrderBVO.CQUALITYLEVELID.equals(editKey)) {
			QualitylevelEditHandler handler = new QualitylevelEditHandler();
			handler.afterEdit(e);
		}
		// 发货库存组织
		else if (SaleOrderBVO.CSENDSTOCKORGVID.equals(editKey)) {
			SendStockOrgEditHandler handler = new SendStockOrgEditHandler();
			handler.afterEdit(e, this.billform);
		}
		// 发货仓库
		else if (SaleOrderBVO.CSENDSTORDOCID.equals(editKey)) {
			SendStordocEditHandler handler = new SendStordocEditHandler();
			handler.afterEdit(e);
		}
		// 结算财务组织
		else if (SaleOrderBVO.CSETTLEORGVID.equals(editKey)) {
			SettleOrgEditHandler handler = new SettleOrgEditHandler();
			handler.afterEdit(e);
		}
		// 收货地区
		else if (SaleOrderBVO.CRECEIVEAREAID.equals(editKey)) {
			ReceiveAreaEditHandler handler = new ReceiveAreaEditHandler();
			handler.afterEdit(e);
		}

		// 价格项
		else if (SaleOrderBVO.CPRICEITEMID.equals(editKey)) {
			PriceItemEditHandler handler = new PriceItemEditHandler();
			handler.afterEdit(e);
		}
		// 税码
		else if (SaleOrderBVO.CTAXCODEID.equals(editKey)) {
			TaxCodeEditHandler handler = new TaxCodeEditHandler();
			handler.afterEdit(e);
		}
		// 扣税类别
		else if (SaleOrderBVO.FTAXTYPEFLAG.equals(editKey)) {
			TaxTypeFlagEditHandler handler = new TaxTypeFlagEditHandler();
			handler.afterEdit(e);
		}
		// 计税金额
		else if (SaleOrderBVO.NCALTAXMNY.equals(editKey)) {
			CaltaxmnyEditHandler handler = new CaltaxmnyEditHandler();
			handler.afterEdit(e);
		}
		// 收货客户
		else if (SaleOrderBVO.CRECEIVECUSTID.equals(editKey)) {
			ReceiveCustEditHandler handler = new ReceiveCustEditHandler();
			handler.afterEdit(e);
		}
		// 收货地址
		else if (SaleOrderBVO.CRECEIVEADDRID.equals(editKey)) {
			ReceiveaddrEditHandler handler = new ReceiveaddrEditHandler();
			handler.afterEdit(e);
		}
		// 发货国家
		else if (SaleOrderBVO.CSENDCOUNTRYID.equals(editKey)) {
			SendCountryEditHandler handler = new SendCountryEditHandler();
			handler.afterEdit(e);
		}
		// 收货国家
		else if (SaleOrderBVO.CRECECOUNTRYID.equals(editKey)) {
			ReceCountryEditHandler handler = new ReceCountryEditHandler();
			handler.afterEdit(e);
		}
		// 报税国家
		else if (SaleOrderBVO.CTAXCOUNTRYID.equals(editKey)) {
			TaxCountryEditHandler handler = new TaxCountryEditHandler();
			handler.afterEdit(e);
		}// 生产厂商(V63新加)
		else if (SaleOrderBVO.CPRODUCTORID.equals(editKey)) {
			ProductorEditHandler handler = new ProductorEditHandler();
			handler.afterEdit(e);
		}// 供应商(V63新加)
		else if (SaleOrderBVO.CVENDORID.equals(editKey)) {
			VendorEditHandler handler = new VendorEditHandler();
			handler.afterEdit(e);
		}// 结算利润中心(原利润中心 V65新加)
		else if (SaleOrderBVO.CPROFITCENTERVID.equals(editKey)) {
			ProfitCenterEditHandler handler = new ProfitCenterEditHandler();
			handler.afterEdit(e);
		}// 特征码编辑(636新加)
		else if (SaleOrderBVO.CMFFILEID.equals(editKey)) {
			CmffileidEditHandle handler = new CmffileidEditHandle();
			handler.afterEdit(e, billform);
		}// 发货利润中心(65新加)
		else if (SaleOrderBVO.CSPROFITCENTERVID.equals(editKey)) {
			CsprofitcenteridEditHandle handler = new CsprofitcenteridEditHandle();
			handler.afterEdit(e, billform);
		}
		// 自由辅助属性
		else if (SOFreeUtil.isFreeKey(editKey)) {
			FreeEditHandler handler = new FreeEditHandler();
			handler.afterEdit(e);
		} else {
			// 编辑后先执行数量单价金额计算(calculator内会过滤掉不需要计算的字段)
			IKeyValue keyValue = new CardKeyValue(cardPanel);
			String trantypecode = keyValue
					.getHeadStringValue(SaleOrderHVO.VTRANTYPECODE);
			String pk_group = AppUiContext.getInstance().getPkGroup();
			SaleOrderClientContext clientcontex = this.billform
					.getM30ClientContext();
			M30TranTypeVO trantypevo = clientcontex.getTransType(trantypecode,
					pk_group);
			SaleOrderCalculator calculator = new SaleOrderCalculator(cardPanel);
			calculator.setTranTypeVO(trantypevo);
			calculator.calculate(editrows, editKey);
		}
		// jilu for 恒安
		if (this.getPirceMnyList().contains(editKey)) {
			IKeyValue keyValue = new CardKeyValue(cardPanel);
			keyValue.setBodyValue(e.getRow(), SaleOrderBVO.CPROMOTPRICEID, null);
		}

		/**
		 * @author wzy
		 * @since 2017-03-03
		 */
		if ("csjhq".equals(editKey)) {
			JhqHeadAndBodyEditHandler handler = new JhqHeadAndBodyEditHandler();
			handler.setBillform(billform);
			handler.afterEdit(e);

		} else if ("desnddate".equals(editKey)) {

		} else if("cgjg".equals(editKey)){
			CgjgBodyAfterEditHandler handler = new CgjgBodyAfterEditHandler();
			handler.setBillform(billform);
			handler.afterEdit(e);
		}else if("cgjgbhs".equals(editKey)){
			
			CgjgbhsBodyAfterEditHandler handler = new CgjgbhsBodyAfterEditHandler();
			handler.setBillform(billform);
			handler.afterEdit(e);
		}

		// 这里不能使用istotalshow 因为 它的值可能中间被程序改过
		bodypanl.setTotalRowShow(true);
	}

	public void setBillform(SaleOrderBillForm billform) {
		this.billform = billform;
	}

	/**
	 * 取单价金额字段List（给询价后字段编辑性）
	 * 
	 * @return priceAndMny
	 */
	private List<String> getPirceMnyList() {
		// 询价相关单价金额
		String[] priceAndMnys = new String[] { SaleOrderBVO.NORIGTAXPRICE,
				SaleOrderBVO.NORIGPRICE, SaleOrderBVO.NORIGTAXNETPRICE,
				SaleOrderBVO.NORIGNETPRICE, SaleOrderBVO.NQTORIGTAXPRICE,
				SaleOrderBVO.NQTORIGPRICE, SaleOrderBVO.NQTORIGTAXNETPRC,
				SaleOrderBVO.NQTORIGNETPRICE, SaleOrderBVO.NTAXPRICE,
				SaleOrderBVO.NPRICE, SaleOrderBVO.NTAXNETPRICE,
				SaleOrderBVO.NNETPRICE, SaleOrderBVO.NQTTAXPRICE,
				SaleOrderBVO.NQTPRICE, SaleOrderBVO.NQTTAXNETPRICE,
				SaleOrderBVO.NQTNETPRICE, SaleOrderBVO.NORIGTAXMNY,
				SaleOrderBVO.NORIGMNY, SaleOrderBVO.NORIGDISCOUNT,
				SaleOrderBVO.NTAXMNY, SaleOrderBVO.NMNY, SaleOrderBVO.NTAX,
				SaleOrderBVO.NDISCOUNT };
		List<String> priceAndMny = Arrays.asList(priceAndMnys);
		return priceAndMny;
	}

}
