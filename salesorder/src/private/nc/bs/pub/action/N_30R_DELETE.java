/**   
 * Copyright  2018 Yonyou. All rights reserved.
 * @Description: TODO
 * @author: wangzy   
 * @date: 2018年6月6日 上午10:09:42 
 * @version: V6.5   
 */
package nc.bs.pub.action;

import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.so.m30.plugin.BP30PlugInPoint;
import nc.bs.so.m30.revise.rule.ReWriteSaleOrderForRevise;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.impl.so.m30.action.main.DeleteSaleOrderReviseAction;
import nc.vo.so.m30.entity.SaleOrderVO;

/**
 * @Description: TODO
 * @author: wangzy
 * @date: 2018年6月6日 上午10:09:42
 */
public class N_30R_DELETE extends AbstractPfAction<SaleOrderVO> {

	@Override
	protected CompareAroundProcesser<SaleOrderVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		// TODO 自动生成的方法存根
		CompareAroundProcesser<SaleOrderVO> processor = new CompareAroundProcesser<SaleOrderVO>(
				BP30PlugInPoint.ReviseDeleteBP);
		// 删除后回写上一个版本的单据号
		processor.addAfterFinalRule(new ReWriteSaleOrderForRevise());
		return processor;
	}

	@Override
	protected SaleOrderVO[] processBP(Object userObj,
			SaleOrderVO[] clientFullVOs, SaleOrderVO[] originBills) {
		// TODO 自动生成的方法存根
		DeleteSaleOrderReviseAction action = new DeleteSaleOrderReviseAction();
		return action.delete(clientFullVOs, originBills);
	}

}
