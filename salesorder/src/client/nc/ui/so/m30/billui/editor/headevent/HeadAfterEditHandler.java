package nc.ui.so.m30.billui.editor.headevent;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent;
import nc.ui.so.m30.billui.view.SaleOrderBillForm;
import nc.vo.so.m30.entity.SaleOrderHVO;

public class HeadAfterEditHandler implements
		IAppEventHandler<CardHeadTailAfterEditEvent> {

	private SaleOrderBillForm billform;

	public SaleOrderBillForm getBillform() {
		return this.billform;
	}

	public void setBillform(SaleOrderBillForm billform) {
		this.billform = billform;
	}

	@Override
	public void handleAppEvent(CardHeadTailAfterEditEvent e) {
		// 效率优化，减少合计次数
		BillCardPanel cardPanel = e.getBillCardPanel();
		cardPanel.getBillModel().setNeedCalculate(false);

		String editkey = e.getKey();
		// 交易类型
		if (SaleOrderHVO.CTRANTYPEID.equals(editkey)) {
			TranTypeEditHandler handler = new TranTypeEditHandler();
			handler.setBillform(this.billform);
			handler.afterEdit(e);
		}
		// 客户
		else if (SaleOrderHVO.CCUSTOMERID.equals(editkey)) {
			CustomerEditHandler handler = new CustomerEditHandler();
			handler.setBillform(this.billform);
			handler.afterEdit(e);
		}

		// 表头收货客户
		else if (SaleOrderHVO.CHRECEIVECUSTID.equals(editkey)) {
			HeadReceiveCustEditHandler handler = new HeadReceiveCustEditHandler();
			handler.afterEdit(e);
		}

		// 表头收货地址
		else if (SaleOrderHVO.CHRECEIVEADDID.equals(editkey)) {
			HeadReceiveaddrEditHandler handler = new HeadReceiveaddrEditHandler();
			handler.afterEdit(e);
		}

		// 开票客户
		else if (SaleOrderHVO.CINVOICECUSTID.equals(editkey)) {
			InvoiceCustEditHandler handler = new InvoiceCustEditHandler();
			handler.afterEdit(e);
		}

		// 结算方式
		else if (SaleOrderHVO.CBALANCETYPEID.equals(editkey)) {
			BalanceTypeEditHandler handler = new BalanceTypeEditHandler();
			handler.setBillform(this.billform);
			handler.afterEdit(e);
		}
		// 销售渠道类型
		else if (SaleOrderHVO.CCHANNELTYPEID.equals(editkey)) {
			ChannelTypeEditHandler handler = new ChannelTypeEditHandler();
			handler.setBillform(this.billform);
			handler.afterEdit(e);
		}
		// 运输方式
		else if (SaleOrderHVO.CTRANSPORTTYPEID.equals(editkey)) {
			TransportTypeEditHandler handler = new TransportTypeEditHandler();
			handler.setBillform(this.billform);
			handler.afterEdit(e);
		}
		// 贸易术语
		else if (SaleOrderHVO.CTRADEWORDID.equals(editkey)) {
			TradewordEditHandler handler = new TradewordEditHandler();
			handler.afterEdit(e);
		}
		// 币种
		else if (SaleOrderHVO.CORIGCURRENCYID.equals(editkey)) {
			OrigCurrencyEditHandler handler = new OrigCurrencyEditHandler();
			handler.setBillform(this.billform);
			handler.afterEdit(e);
		}
		// 单据日期
		else if (SaleOrderHVO.DBILLDATE.equals(editkey)) {
			BillDateEditHandler handler = new BillDateEditHandler();
			handler.setBillform(this.billform);
			handler.afterEdit(e);
		}
		// 整单折扣
		else if (SaleOrderHVO.NDISCOUNTRATE.equals(editkey)) {
			DiscountRateEditHandler handler = new DiscountRateEditHandler();
			handler.afterEdit(e);
		}
		// 表头价税合计
		else if (SaleOrderHVO.NTOTALORIGMNY.equals(editkey)) {
			TotalOrigMnyEditHandler handler = new TotalOrigMnyEditHandler();
			handler.afterEdit(e);
		}
		// 表头冲减金额合计
		else if (SaleOrderHVO.NTOTALORIGSUBMNY.equals(editkey)) {
			TotalOrigSubMnyEditHandler handler = new TotalOrigSubMnyEditHandler();
			handler.setBillform(this.billform);
			handler.afterEdit(e);
		}
		// 收付款协议
		else if (SaleOrderHVO.CPAYTERMID.equals(editkey)) {
			PayTermEditHandler handler = new PayTermEditHandler();
			handler.afterEdit(e);
		}
		// 预收款比率
		else if (SaleOrderHVO.NPRECEIVERATE.equals(editkey)) {
			PreceiveRateEditHandler handler = new PreceiveRateEditHandler();
			handler.afterEdit(e);
		}
		// 收款限额
		else if (SaleOrderHVO.NPRECEIVEQUOTA.equals(editkey)) {
			PreceiveQuotaEditHandler handler = new PreceiveQuotaEditHandler();
			handler.afterEdit(e);
		}
		// 本次收款金额
		else if (SaleOrderHVO.NTHISRECEIVEMNY.equals(editkey)) {
			ThisReceiveMnyEditHandler handler = new ThisReceiveMnyEditHandler();
			handler.setBillform(this.billform);
			handler.afterEdit(e);
		}
		/**
		 * add by lyw 2017-06-09 
		 * 新增表头汇率编辑后事件
		 */
		else if ("exchange_rate".equals(editkey)) {
			DlflHeadAfterEdithandler handler = new DlflHeadAfterEdithandler();
			handler.setBillform(this.billform);
			handler.afterEdit(e);
		}
		/**
		 * add by lyw 2017-06-12 
		 * 新增表头采购币种编辑后事件
		 */
		else if ("buyccurrencyid".equals(editkey)) {
			DlflHeadAfterEdithandler handler = new DlflHeadAfterEdithandler();
			handler.setBillform(this.billform);
			handler.afterEdit(e);
		}

		/**
		 * @author wangzym
		 * @version 2017-03-02 新增表头代理费率的编辑后事件的监听 没有通过静态常量来获取String字符串
		 */
		// 代理费率专用类
		else if ("dlfl".equals(editkey)) {
			DlflHeadAfterEdithandler handler = new DlflHeadAfterEdithandler();
			handler.setBillform(this.billform);
			handler.afterEdit(e);

		}
		// 单价取整专用类
		else if ("djqz".equals(editkey)) {
			JgqzHeadAfterEditHandler handler = new JgqzHeadAfterEditHandler();
			handler.setBillform(this.billform);
			handler.afterEdit(e);

		}
		// 基准日期表头表体共用类
		else if ("jzrq".equals(editkey)) {
			JhqHeadAndBodyEditHandler handler = new JhqHeadAndBodyEditHandler();
			handler.setBillform(this.billform);
			handler.afterEdit(e);

		}
		// 交货期表头表体共用类
		else if ("jhq".equals(editkey)) {
			JhqHeadAndBodyEditHandler handler = new JhqHeadAndBodyEditHandler();
			handler.setBillform(this.billform);
			handler.afterEdit(e);

		}
		/****************** wangzym modify End**************************/
		// 效率优化，减少合计次数
		cardPanel.getBillModel().setNeedCalculate(true);

	}
}
