package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.so.billinformation.plugin.bpplugin.BillinformationPluginPoint;
import nc.vo.so.billinformation.AggBillInforMationVO;
import nc.itf.so.IBillinformationMaintain;

public class N_BI10_SAVEBASE extends AbstractPfAction<AggBillInforMationVO> {

	@Override
	protected CompareAroundProcesser<AggBillInforMationVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<AggBillInforMationVO> processor = null;
		AggBillInforMationVO[] clientFullVOs = (AggBillInforMationVO[]) this.getVos();
		if (!StringUtil.isEmptyWithTrim(clientFullVOs[0].getParentVO()
				.getPrimaryKey())) {
			processor = new CompareAroundProcesser<AggBillInforMationVO>(
					BillinformationPluginPoint.SCRIPT_UPDATE);
		} else {
			processor = new CompareAroundProcesser<AggBillInforMationVO>(
					BillinformationPluginPoint.SCRIPT_INSERT);
		}
		// TODO 在此处添加前后规则
		IRule<AggBillInforMationVO> rule = null;

		return processor;
	}

	@Override
	protected AggBillInforMationVO[] processBP(Object userObj,
			AggBillInforMationVO[] clientFullVOs, AggBillInforMationVO[] originBills) {

		AggBillInforMationVO[] bills = null;
		try {
			IBillinformationMaintain operator = NCLocator.getInstance()
					.lookup(IBillinformationMaintain.class);
			if (!StringUtil.isEmptyWithTrim(clientFullVOs[0].getParentVO()
					.getPrimaryKey())) {
				bills = operator.update(clientFullVOs, originBills);
			} else {
				bills = operator.insert(clientFullVOs, originBills);
			}
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}
}
