package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.so.billinformation.plugin.bpplugin.BillinformationPluginPoint;
import nc.vo.so.billinformation.AggBillInforMationVO;
import nc.itf.so.IBillinformationMaintain;

public class N_BI10_DELETE extends AbstractPfAction<AggBillInforMationVO> {

	@Override
	protected CompareAroundProcesser<AggBillInforMationVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<AggBillInforMationVO> processor = new CompareAroundProcesser<AggBillInforMationVO>(
				BillinformationPluginPoint.SCRIPT_DELETE);
		// TODO 在此处添加前后规则
		return processor;
	}

	@Override
	protected AggBillInforMationVO[] processBP(Object userObj,
			AggBillInforMationVO[] clientFullVOs, AggBillInforMationVO[] originBills) {
		IBillinformationMaintain operator = NCLocator.getInstance().lookup(
				IBillinformationMaintain.class);
		try {
			operator.delete(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return clientFullVOs;
	}

}
