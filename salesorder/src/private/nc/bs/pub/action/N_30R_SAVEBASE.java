/**   
 * Copyright  2018 Yonyou. All rights reserved.
 * @Description: TODO
 * @author: wangzy   
 * @date: 2018年5月30日 上午8:59:56 
 * @version: V6.5   
 */
package nc.bs.pub.action;

import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.so.m30.plugin.BP30PlugInPoint;
import nc.bs.so.m30.revise.rule.CheckExistWorkflowRule;
import nc.bs.so.m30.rule.approve.CheckMaxIversionRule;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.impl.so.m30.action.main.CommitSaleOrderAction;
import nc.impl.so.m30.action.main.SaveBaseSaleOrderReviseAction;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.revise.entity.SaleOrderHistoryVO;

/**
 * 销售订单修改保存
 * 
 * @since 6.5
 * @version 2018-05-30 下午2:49:00
 * @author 王梓懿
 */
public class N_30R_SAVEBASE extends AbstractPfAction<SaleOrderHistoryVO> {
	/**
	 * 构造方法
	 */
	public N_30R_SAVEBASE() {
		super();
	}

	@Override
	protected CompareAroundProcesser<SaleOrderHistoryVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<SaleOrderHistoryVO> processor = new CompareAroundProcesser<SaleOrderHistoryVO>(
				BP30PlugInPoint.ReviseSaveBaseBP);
		this.addBeforeRule(processor);
		return processor;
	}

	@Override
	protected SaleOrderHistoryVO[] processBP(Object userObj,
			SaleOrderHistoryVO[] clientFullVOs, SaleOrderHistoryVO[] originBills) {
		SaveBaseSaleOrderReviseAction action = new SaveBaseSaleOrderReviseAction();
		return action.saveBase(clientFullVOs, originBills);
	}

	private void addBeforeRule(
			CompareAroundProcesser<SaleOrderHistoryVO> processor) {
		/*
		 * // 校验单据状态是否可以提交 IRule rule = new CheckExistWorkflowRule();
		 * processor.addBeforeRule(rule);
		 */
		// 校验提交版本是否是修订表中最新版本
		IRule rule = new CheckMaxIversionRule();
		processor.addBeforeRule(rule);
	}
}
