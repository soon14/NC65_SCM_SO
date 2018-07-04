package nc.bs.so.m32.maintain.rule.delete;

import java.util.List;
import java.util.Map;

import nc.bs.so.m32.maintain.rule.util.RewriteBillUtil;
import nc.cmbd.vo.scmpub.res.billtype.SOBillType;
import nc.impl.pubapp.bill.rewrite.BillRewriter;
import nc.impl.pubapp.bill.rewrite.RewritePara;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.scmpub.res.billtype.ICBillType;
import nc.vo.so.m32.entity.SaleInvoiceVO;
import nc.vo.trade.checkrule.VOChecker;

/**
 * @description 销售发票删除操作后的回写规则 包含回写来源和源头
 * @scene 销售发票删除保存后
 * @param 无
 * @since 6.3
 * @version 2012-12-21 上午09:04:31
 * @author yaogj
 */
public class RewriteBillDeleteRule implements IRule<SaleInvoiceVO> {

	@Override
	public void process(SaleInvoiceVO[] vos) {

		// 回写来源单据
		RewriteBillUtil rewriteUtil = new RewriteBillUtil();
		// 缓存组织信息
		rewriteUtil.catcheOrg(vos);

		BillRewriter srctool = rewriteUtil.getSrcBillRewriter();
		Map<String, List<RewritePara>> srcParaIndex = srctool
				.splitForDelete(vos);

		List<RewritePara> srcSaleOut = srcParaIndex.get(ICBillType.SaleOut
				.getCode());
		List<RewritePara> srcSaleOrder = srcParaIndex.get("30");
		if (!VOChecker.isEmpty(srcSaleOut)) {
			rewriteUtil.reWriteSrc4C(srcSaleOut);
			// wangzy add增加对于销售订单的数量回写
			rewriteUtil.reWriteSrc30(srcSaleOut);
		}
		if (!VOChecker.isEmpty(srcSaleOrder)) {
			rewriteUtil.reWriteSrc30(srcSaleOrder);
		}

	}

}
