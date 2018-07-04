/**
 * 
 */
package nc.bs.so.m30.rule.rewrite.m28;

import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.data.vo.VOUpdate;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.pp.m28.entity.PriceAuditItemVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.log.TimeLog;

/**
 * @author wangzym
 * @version 2017年06月06日 下午2:05:44 2017-05-03
 * 
 */
public class DelRewriteForPriceAuditBillRule implements
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
					|| !"28".equals(SaleOrderBVO.getAttributeValue("vsrctype"))) {
				// 不需要回写没有取到上游的来源单据
				// 或者取到的来源单据类型不是价审单
				return;
			}
			String csrcid = SaleOrderBVO.getAttributeValue("csrcbid")
					.toString();
			srcPk[i] = csrcid;

		}
		this.rewrite(srcPk, bvos);

	}

	/**
	 * 回写动作
	 * 
	 * @param bvos
	 * 
	 * @param paras
	 *            当前的来源单据字段数组
	 */
	private void rewrite(String[] srcPk, SaleOrderBVO[] bvos) {
		if (srcPk.length == 0) {
			return;
		}
		String[] names = new String[] { "hasnordastnum" };
		VOUpdate<PriceAuditItemVO> bo = new VOUpdate<PriceAuditItemVO>();
		PriceAuditItemVO[] vos = new PriceAuditItemVO[srcPk.length];

		for (int i = 0; i < vos.length; i++) {
			PriceAuditItemVO PriceAuditItemVO = new PriceAuditItemVO();
			// 2017-05-23 新增对上游累计数量的回写释放数量
			VOQuery<PriceAuditItemVO> query = new VOQuery<PriceAuditItemVO>(
					PriceAuditItemVO.class);
			PriceAuditItemVO[] tempVO = query.query(new String[] { srcPk[i] });
			UFDouble oldNum = (UFDouble) tempVO[0]
					.getAttributeValue("hasnordastnum");
			UFDouble newNum = oldNum.sub((UFDouble) bvos[i]
					.getAttributeValue("nastnum"));
			PriceAuditItemVO.setAttributeValue("hasnordastnum", newNum);

			PriceAuditItemVO.setPrimaryKey(srcPk[i]);
			vos[i] = PriceAuditItemVO;
		}
		PriceAuditItemVO[] newvo = bo.update(vos, names);
		TimeLog.info("更新数据库"); /* -=notranslate=- */

	}

}
