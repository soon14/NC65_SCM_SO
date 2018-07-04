package nc.bs.so.m32.maintain.rule.update;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m32.entity.SaleInvoiceHVO;
import nc.vo.so.m32.entity.SaleInvoiceVO;
import nc.vo.so.pub.enumeration.BillStatus;

/**
 * @description 销售发票修改保存前校验并跟新单据状态（非自由态的单据不允许修改）
 * @scene 销售发票修改保存前
 * @param 无
 * @since 6.0
 * @version 2011-5-19 下午02:44:10
 * @author 么贵敬
 */
public class CheckBillState implements IRule<SaleInvoiceVO> {

	@Override
	public void process(SaleInvoiceVO[] invoices) {

		for (SaleInvoiceVO invoicevo : invoices) {
			// 表头合法性校验
			this.checkHeadValidity(invoicevo.getParentVO());
			// 合法性校验后需要更新审批不通过的单据状态 add by zhangby5
			this.reSetBillStatusForNoPass(invoicevo);
		}

	}

	/**
	 * 校验单据状态
	 * 
	 * @param head
	 */
	private void checkHeadValidity(SaleInvoiceHVO head) {

		// modify by wangshu6 for 636 2015-01-19 销售发票审批中支持修改
		if (!BillStatus.FREE.getIntegerValue().equals(head.getFstatusflag())
				&& !BillStatus.NOPASS.getIntegerValue().equals(
						head.getFstatusflag())
				&& !BillStatus.AUDITING.getIntegerValue().equals(
						head.getFstatusflag())) {

			ExceptionUtils
					.wrappBusinessException(NCLangRes4VoTransl.getNCLangRes()
							.getStrByID("4006008_0", "04006008-0018")/*
																	 * @res
																	 * "非自由态或审批不通过的单据不允许修改"
																	 */);
		}
	}

	private void reSetBillStatusForNoPass(SaleInvoiceVO vo) {
		if (vo.getParentVO().getFstatusflag().intValue() == BillStatus.NOPASS
				.getIntValue()) {
			vo.getParentVO().setFstatusflag(BillStatus.FREE.getIntegerValue());
		}
	}
}
