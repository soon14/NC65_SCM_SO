package nc.bs.so.m4331.maintain.rule.insert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.bs.so.m4331.maintain.rule.util.RewriteBillUtil;
import nc.impl.pubapp.bill.rewrite.BillRewriter;
import nc.impl.pubapp.bill.rewrite.RewritePara;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.scmpub.res.billtype.TOBillType;
import nc.vo.so.m4331.entity.DeliveryBVO;
import nc.vo.so.m4331.entity.DeliveryVO;
import nc.vo.trade.checkrule.VOChecker;

/**
 * @description <p>
 *              <b>本类主要完成以下功能：</b>
 *              <ul>
 *              <li>发货单新增保存后回写来源单据
 *              </ul>
 *              <p>
 * @scene 销售发货单保存后
 * @param 无
 * @version 本版本号 6.0
 * @since 上一版本号 5.6
 * @author 祝会征
 * @time 2010-1-21 下午04:57:49
 */
public class RewriteBillInsertRule implements IRule<DeliveryVO> {

	@Override
	public void process(DeliveryVO[] vos) {
		RewriteBillUtil rewriteUtil = new RewriteBillUtil();
		BillRewriter srctool = rewriteUtil.getSrcBillRewriter();
		Map<String, List<RewritePara>> srcParaIndex = srctool
				.splitForInsert(vos);
		Map<String, UFBoolean> map = new HashMap<String, UFBoolean>();
		for (DeliveryVO vo : vos) {
			DeliveryBVO[] bvos = vo.getChildrenVO();
			for (DeliveryBVO bvo : bvos) {
				map.put(bvo.getCsrcbid(), bvo.getBclosesrcflag());
			}
		}
		// add by zhangby5 适配流程平台，此发货单回写销售订单走业务流回写
		List<RewritePara> srcTranOrder = srcParaIndex.get(TOBillType.TransOrder
				.getCode());
		if (!VOChecker.isEmpty(srcTranOrder)) {
			rewriteUtil.reWriteSrc5X(srcTranOrder, map);
		}
		List<RewritePara> src = srcParaIndex.get("30");
		if (!VOChecker.isEmpty(src)) {
			rewriteUtil.reWriteSrc30(src, map);

		}
	}
}
