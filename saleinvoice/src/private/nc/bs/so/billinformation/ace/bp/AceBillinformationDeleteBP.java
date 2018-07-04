package nc.bs.so.billinformation.ace.bp;

import nc.bs.so.billinformation.plugin.bpplugin.BillinformationPluginPoint;
import nc.vo.so.billinformation.AggBillInforMationVO;

import nc.impl.pubapp.pattern.data.bill.template.DeleteBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;


/**
 * 标准单据删除BP
 */
public class AceBillinformationDeleteBP {

	public void delete(AggBillInforMationVO[] bills) {

		DeleteBPTemplate<AggBillInforMationVO> bp = new DeleteBPTemplate<AggBillInforMationVO>(
				BillinformationPluginPoint.DELETE);
		// 增加执行前规则
		this.addBeforeRule(bp.getAroundProcesser());
		// 增加执行后业务规则
		this.addAfterRule(bp.getAroundProcesser());
		bp.delete(bills);
	}

	private void addBeforeRule(AroundProcesser<AggBillInforMationVO> processer) {
		// TODO 前规则
		/*IRule<AggBillInforMationVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.BillDeleteStatusCheckRule();
		processer.addBeforeRule(rule);*/
	}

	/**
	 * 删除后业务规则
	 * 
	 * @param processer
	 */
	private void addAfterRule(AroundProcesser<AggBillInforMationVO> processer) {
		// TODO 后规则

	}
}
