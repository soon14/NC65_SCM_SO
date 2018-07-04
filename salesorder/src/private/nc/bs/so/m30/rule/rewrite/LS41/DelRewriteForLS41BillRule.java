/**
 * 
 */
package nc.bs.so.m30.rule.rewrite.LS41;

import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.data.vo.VOUpdate;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.lm.lsdlxy.LsxywtbBVO;
import nc.vo.pp.m28.entity.PriceAuditItemVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.log.TimeLog;

/**
 * @author wangzym
 * @version 2017年06月06日 下午2:05:44 2017-05-03
 * 
 */
public class DelRewriteForLS41BillRule implements
		IRule<nc.vo.so.m30.entity.SaleOrderVO> {

	@Override
	public void process(SaleOrderVO[] vos) {
		// TODO 自动生成的方法存根
		// 只有一个Aggvo
		SaleOrderBVO[] bvos = (SaleOrderBVO[]) vos[0].getChildrenVO();
		String[] srcPk = new String[bvos.length];
		for (int i = 0; i < bvos.length; i++) {
			SaleOrderBVO SaleOrderBVO = bvos[i];
			if ((SaleOrderBVO.getAttributeValue("csrcbid")) == null
					|| !"LS41".equals(SaleOrderBVO
							.getAttributeValue("vsrctype"))) {
				// 不需要回写没有取到上游的来源单据
				// 或者取到的来源单据类型不是价审单
				return;
			}
			String csrcid = SaleOrderBVO.getAttributeValue("csrcbid")
					.toString();
			srcPk[i] = csrcid;

		}
		this.rewrite(srcPk);

	}

	/**
	 * @Title: rewrite
	 * @Description: TODO
	 * @param srcPk
	 * @return: void
	 */
	private void rewrite(String[] srcPk) {
		// TODO 自动生成的方法存根
		if (srcPk.length == 0) {
			return;
		}
		LsxywtbBVO[] bvos = new LsxywtbBVO[srcPk.length];
		for (int i = 0; i < srcPk.length; i++) {
			String bpk = srcPk[i];
			LsxywtbBVO bvo = new LsxywtbBVO();
			bvo.setPrimaryKey(bpk);
			// 回写为0 以便下次还能拉单
			bvo.setAttributeValue("bdef1", 0);
			bvos[i]=bvo;

		}
		VOUpdate<LsxywtbBVO> bo = new VOUpdate<LsxywtbBVO>();

		LsxywtbBVO[] newvo = bo.update(bvos, new String[] { "bdef1" });
		TimeLog.info("更新数据库"); /* -=notranslate=- */
	}

}
