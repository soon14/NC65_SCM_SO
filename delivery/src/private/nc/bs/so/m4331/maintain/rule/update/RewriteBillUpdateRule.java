package nc.bs.so.m4331.maintain.rule.update;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.bs.so.m4331.maintain.rule.util.RewriteBillUtil;
import nc.impl.pubapp.bill.rewrite.BillRewriter;
import nc.impl.pubapp.bill.rewrite.RewritePara;
import nc.impl.pubapp.pattern.rule.ICompareRule;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.scmpub.res.billtype.TOBillType;
import nc.vo.so.m4331.entity.DeliveryBVO;
import nc.vo.so.m4331.entity.DeliveryVO;
import nc.vo.trade.checkrule.VOChecker;

/**
 * @description <p>
 *              <b>本类主要完成以下功能：</b>
 *              <ul>
 *              <li>发货单修改保存回写来源单据
 * @scene 销售发货单修改保存后
 * @param 无
 * @version 本版本号 6.0
 * @since 上一版本号 5.6
 * @author 祝会征
 * @time 2010-1-21 下午04:57:49
 */
public class RewriteBillUpdateRule implements ICompareRule<DeliveryVO> {

	@Override
	public void process(DeliveryVO[] vos, DeliveryVO[] originVOs) {
		// 回写来源单据
		RewriteBillUtil rewriteUtil = new RewriteBillUtil();
		BillRewriter srctool = rewriteUtil.getSrcBillRewriter();
		Map<String, List<RewritePara>> srcParaIndex = srctool.splitForUpdate(
				vos, originVOs);

		Map<String, UFBoolean> map = new HashMap<String, UFBoolean>();
		for (DeliveryVO vo : vos) {
			DeliveryBVO[] bvos = vo.getChildrenVO();
			for (DeliveryBVO bvo : bvos) {
				map.put(bvo.getCdeliverybid(), bvo.getBclosesrcflag());
			}
		}
		// add by zhangby5 适配流程平台，此发货单回写销售订单走业务流回写
		List<RewritePara> srcTranOut = srcParaIndex.get(TOBillType.TransOrder
				.getCode());
		if (!VOChecker.isEmpty(srcTranOut)) {
			rewriteUtil.reWriteSrc5X(srcTranOut, map);
		}

		List<RewritePara> src = srcParaIndex.get("30");
		if (!VOChecker.isEmpty(src)) {
			rewriteUtil.reWriteSrc30(src, map);

		}

	}
}
