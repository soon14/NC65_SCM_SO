package nc.bs.so.m30.rule.rewrite.m5805;

import java.util.List;
import java.util.Map;

import nc.bs.so.m30.maintain.util.RewriteBillUtil;
import nc.impl.pubapp.bill.rewrite.BillRewriter;
import nc.impl.pubapp.bill.rewrite.RewritePara;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.so.m30.entity.SaleOrderVO;

/**
 * @author zhangjjs
 * @date 2018-3-19
 * 
 */
public class RewriteForM5805DeleteRule implements IRule<SaleOrderVO> {

	@Override
	public void process(SaleOrderVO[] vos) {
		// 调用回写类直接进行回写操作
		RewriteBillUtil rewriteUtil = new RewriteBillUtil();
		BillRewriter srctool = rewriteUtil.getSrc5805BillRewriter();
		Map<String, List<RewritePara>> rwParaMap = srctool.splitForDelete(vos);
		// 回写进口明细单
		if (null != rwParaMap) {
			List<RewritePara> paramList = rwParaMap.get("5805");
			if (null != paramList && !paramList.isEmpty()) {
				rewriteUtil.reWriteSrc5805(paramList);
			}
		}
	}
	
}

