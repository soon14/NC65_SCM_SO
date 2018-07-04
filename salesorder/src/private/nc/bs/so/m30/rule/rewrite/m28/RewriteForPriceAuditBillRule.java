package nc.bs.so.m30.rule.rewrite.m28;

import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.data.vo.VOUpdate;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.pp.m28.entity.PriceAuditItemVO;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.log.TimeLog;

/**
 * 
 * @author wangzym
 * @version 2017-06-07 上游的订货执行数量没有改动
 */

public class RewriteForPriceAuditBillRule implements IRule<SaleOrderVO> {

	@Override
	public void process(nc.vo.so.m30.entity.SaleOrderVO[] vos) {
		// TODO 自动生成的方法存根
		// 只有一个Aggvo
		// 需要判断是否来源单据类型为价格审批单
		SaleOrderBVO[] bvos = (SaleOrderBVO[]) vos[0].getChildrenVO();
		String[] srcPk = new String[bvos.length];
		UFDouble[] nums = new UFDouble[bvos.length];
		for (int i = 0; i < bvos.length; i++) {
			SaleOrderBVO SaleOrderBVO = bvos[i];
			if ((SaleOrderBVO.getAttributeValue("csrcid")) == null
					|| !"28".equals(SaleOrderBVO.getAttributeValue("vsrctype"))) {
				// 不需要回写没有取到上游的来源单据
				return;
			}
			UFDouble num = (UFDouble) SaleOrderBVO.getAttributeValue("nastnum");
			String csrcbid = SaleOrderBVO.getAttributeValue("csrcbid")
					.toString();
			srcPk[i] = csrcbid;
			nums[i] = num;

		}
		this.rewrite(srcPk, nums);

	}

	/**
	 * 回写动作
	 * 
	 * @param nums
	 * 
	 * @param paras
	 *            当前的来源单据字段数组
	 */
	private void rewrite(String[] srcPk, UFDouble[] nums) {
		if (srcPk.length == 0) {
			return;
		}
		String[] names = new String[] { "hasnordastnum" };
		VOUpdate<PriceAuditItemVO> bo = new VOUpdate<PriceAuditItemVO>();
		PriceAuditItemVO[] vos = new PriceAuditItemVO[srcPk.length];

		for (int i = 0; i < vos.length; i++) {
			PriceAuditItemVO PriceAuditItemVO = new PriceAuditItemVO();
			VOQuery<PriceAuditItemVO> query = new VOQuery<PriceAuditItemVO>(
					PriceAuditItemVO.class);
			PriceAuditItemVO[] old = query.query(new String[] { srcPk[i] });
			UFDouble oldnum = (UFDouble) old[0]
					.getAttributeValue("hasnordastnum");

			/**
			 * 2017-08-18 oldsum 采购方案数量 改为nordastnum订货数量，以订货数量为准做标准校验
			 */
			UFDouble oldsum = (UFDouble) old[0].getAttributeValue("nordastnum");

			UFDouble num = null;
			if (oldnum == null) {
				num = nums[i];
			} else {

				num = nums[i].add(oldnum);
			}
			if (oldsum.doubleValue() < num.doubleValue()) {
				ExceptionUtils.wrappBusinessException("修改后的数量超出上游最大可用量");
			} else {
				PriceAuditItemVO.setPrimaryKey(old[0].getPrimaryKey());
				PriceAuditItemVO.setAttributeValue("hasnordastnum", num);

				vos[i] = PriceAuditItemVO;
			}
		}
		PriceAuditItemVO[] newvo = bo.update(vos, names);
		TimeLog.info("更新数据库"); /* -=notranslate=- */

	}

}
