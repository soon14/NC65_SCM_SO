package nc.bs.so.m32.maintain.rule.insert;

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
 * @description <p>
 *              <b>本类主要完成以下功能：</b>
 *              <ul>
 *              <li>销售发票新增保存后回写来源单据
 *              </ul>
 *              <p>
 * @scene 销售发票新增保存后
 * @param 无
 * @version 本版本号 6.0
 * @since 上一版本号 5.6
 * @author 冯加滨
 * @time 2010-1-21 下午04:57:49
 */
public class RewriteBillInsertRule implements IRule<SaleInvoiceVO> {

	@Override
	public void process(SaleInvoiceVO[] vos) {

		// 回写来源单据
		RewriteBillUtil rewriteUtil = new RewriteBillUtil();
		// 缓存组织信息
		rewriteUtil.catcheOrg(vos);

		BillRewriter srctool = rewriteUtil.getSrcBillRewriter();
		Map<String, List<RewritePara>> srcParaIndex = srctool
				.splitForInsert(vos);

		List<RewritePara> srcSaleOut = srcParaIndex.get(ICBillType.SaleOut
				.getCode());
		List<RewritePara> srcSaleOrder = srcParaIndex.get("30");
		if (!VOChecker.isEmpty(srcSaleOut)) {
			rewriteUtil.reWriteSrc4C(srcSaleOut);
		}
		if (!VOChecker.isEmpty(srcSaleOrder)) {
			rewriteUtil.reWriteSrc30(srcSaleOrder);
		}
	}

}
