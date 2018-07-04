package nc.impl.so.m30.action.main;

import java.util.ArrayList;
import java.util.List;

import nc.bs.pub.action.N_30_APPROVE;
import nc.bs.so.m30.maintain.rule.delete.RewritePromotePriceDeleteRule;
import nc.bs.so.m30.plugin.Action30PlugInPoint;
import nc.bs.so.m30.rule.approve.ApproveStateRule;
import nc.bs.so.m30.rule.approve.BusiLog;
import nc.bs.so.m30.rule.approve.CheckApprovableRule;
import nc.bs.so.m30.rule.atp.SaleOrderVOATPAfterRule;
import nc.bs.so.m30.rule.atp.SaleOrderVOATPBeforeRule;
import nc.bs.so.m30.rule.credit.RenovateARByHidsBeginRule;
import nc.bs.so.m30.rule.credit.RenovateARByHidsEndRule;
import nc.bs.so.m30.rule.m35.ArsubOffsetAfterApproveRule;
import nc.bs.so.m30.rule.me.SaleOrderVOApproveAfterRule;
import nc.bs.so.m30.rule.msg.SendToGfAndTable;
import nc.impl.pubapp.pattern.data.vo.VOUpdate;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.vo.credit.engrossmaintain.pub.action.M30EngrossAction;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.pub.enumeration.BillStatus;
import nc.vo.so.pub.rule.SOPfStatusChgRule;

/**
 * 销售订单审批动作
 * 
 * @since 6.0
 * @version 2012-2-7 下午01:09:56
 * @author fengjb
 * @modify 王梓懿 wangzym 增加审批后的回填中间表和鞍钢股份 2017-04-17
 */
public class ApproveSaleOrderAction {

	/**
	 * 审批操作
	 * 
	 * @param bills
	 * @param script
	 * @return 审批后数据
	 */
	public Object approve(SaleOrderVO[] bills, N_30_APPROVE script) {

		Object ret = null;
		try {

			// 注入点
			AroundProcesser<SaleOrderVO> processer = new AroundProcesser<SaleOrderVO>(
					Action30PlugInPoint.ApproveAction);

			TimeLog.logStart();
			this.addBeforeRule(processer);
			processer.before(bills);
			TimeLog.info("调用审批前操作插入点"); /* -=notranslate=- */

			/************* 该组件为批动作工作流处理，不能进行修改 *********************/
			ret = script.procActionFlow(script.getPfParameterVO());
			/************** 返回结果 *************************************************/

			// 转换流程平台审批流状态到业务单据状态，并持久化
			SaleOrderVO[] newbills = script.getVos();
			this.updateNewBillStatus(newbills);
			TimeLog.logStart();

			Integer newbillstatus = newbills[0].getParentVO().getFstatusflag();
			String vtranstype = newbills[0].getParentVO().getVtrantypecode();
			// 如果交易类型是xxxx则需要传股份数据库而且要存到中间表

			this.addAfterRule(processer, newbillstatus);
			//add by wangzy 2017-04-17
			//需要传股份的交易类型
			//去掉审批后推单，单独出来按钮
		/*	if ("30-Cxx-22".equals(vtranstype) ) {
				//传股份数据库
				processer.addAfterRule(new SendToGfAndTable());
			}*/
			//end
			processer.after(newbills);
			TimeLog.info("调用审批后操作插入点"); /* -=notranslate=- */

			// 审批通过时，流程平台返回的参数为null,此时需要返回最新的数据，其他情况下(审批中)只能返回null,否则会走驱动动作
			if (null == ret) {
				ret = newbills;
			}
		} catch (Exception ex) {
			ExceptionUtils.wrappException(ex);
		}
		return ret;
	}

	private void updateNewBillStatus(SaleOrderVO[] newbills) {

		SOPfStatusChgRule statuschgrule = new SOPfStatusChgRule();
		SaleOrderHVO[] updateheads = new SaleOrderHVO[newbills.length];
		List<SaleOrderBVO> listbody = new ArrayList<SaleOrderBVO>();
		int i = 0;
		for (SaleOrderVO ordervo : newbills) {
			statuschgrule.changePfToBillStatus(ordervo);
			updateheads[i++] = ordervo.getParentVO();
			for (SaleOrderBVO bvo : ordervo.getChildrenVO()) {
				listbody.add(bvo);
			}
		}
		String[] headupname = new String[] { SaleOrderHVO.FSTATUSFLAG };
		VOUpdate<SaleOrderHVO> headupsrv = new VOUpdate<SaleOrderHVO>();
		headupsrv.update(updateheads, headupname);

		String[] bodyupname = new String[] { SaleOrderBVO.FROWSTATUS };
		VOUpdate<SaleOrderBVO> bodyupsrv = new VOUpdate<SaleOrderBVO>();
		SaleOrderBVO[] updatebodys = listbody.toArray(new SaleOrderBVO[listbody
				.size()]);
		bodyupsrv.update(updatebodys, bodyupname);
	}

	private void addAfterRule(AroundProcesser<SaleOrderVO> processer,
			Integer newbillstatus) {

		IRule<SaleOrderVO> rule = null;

		// 信用检查
		if (SysInitGroupQuery.isCREDITEnabled()) {
			rule = new RenovateARByHidsEndRule(M30EngrossAction.M30Approve);
			processer.addAfterRule(rule);
		}
		boolean icEnable = SysInitGroupQuery.isICEnabled();
		if (icEnable) {
			// 可用量
			rule = new SaleOrderVOATPAfterRule();
			processer.addAfterRule(rule);
		}

		// 索芙特用：审批后赠品兑付
		rule = new ArsubOffsetAfterApproveRule();
		processer.addAfterRule(rule);

		if (BillStatus.AUDIT.equalsValue(newbillstatus)) {
			// 审批后各种状态的处理
			rule = new ApproveStateRule();
			processer.addAfterRule(rule);
		}

		if (BillStatus.NOPASS.equalsValue(newbillstatus)) {
			// 回写促销价格表 zhangby5 for 恒安限量促销
			if (SysInitGroupQuery.isPRICEEnabled()) {
				rule = new RewritePromotePriceDeleteRule();
				processer.addAfterRule(rule);
			}
		}
		// 审批后生成订单收益表数据
		rule = new SaleOrderVOApproveAfterRule();
		processer.addAfterRule(rule);

	}

	private void addBeforeRule(AroundProcesser<SaleOrderVO> processer) {
		// 检查单据是否可以审批
		IRule<SaleOrderVO> rule = new CheckApprovableRule();
		processer.addBeforeRule(rule);
		// 信用检查
		if (SysInitGroupQuery.isCREDITEnabled()) {
			rule = new RenovateARByHidsBeginRule(M30EngrossAction.M30Approve);
			processer.addBeforeRule(rule);
		}
		boolean icEnable = SysInitGroupQuery.isICEnabled();
		if (icEnable) {
			// 可用量
			rule = new SaleOrderVOATPBeforeRule();
			processer.addBeforeRule(rule);
		}

		rule = new BusiLog();
		processer.addBeforeRule(rule);
	}
}
