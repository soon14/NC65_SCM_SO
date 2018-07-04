/**   
 * Copyright  2018 Yonyou. All rights reserved.
 * @Description: TODO
 * @author: wangzy   
 * @date: 2018年5月30日 上午9:05:21 
 * @version: V6.5   
 */
package nc.bs.so.m30.revise;

import nc.bs.so.m30.plugin.BP30PlugInPoint;
import nc.bs.so.m30.revise.rule.FillDataHistoryRule;
import nc.bs.so.m30.revise.rule.RewriteVbillcodeFor30R;
import nc.bs.so.m30.revise.rule.UpdateVersionNumRule;
import nc.impl.pubapp.pattern.data.bill.BillInsert;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.impl.pubapp.pattern.rule.ICompareRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.m30.revise.entity.SaleOrderHistoryVO;

/**
 * @Description: TODO
 * @author: wangzy
 * @date: 2018年5月30日 上午9:05:21
 */
public class SaveBaseReviseSaleOrderBP {

	public SaleOrderHistoryVO[] update(SaleOrderHistoryVO[] bills,
			SaleOrderHistoryVO[] originBills) throws BusinessException {
		CompareAroundProcesser<SaleOrderHistoryVO> processer = new CompareAroundProcesser<SaleOrderHistoryVO>(
				BP30PlugInPoint.ReviseSaveBaseBP);

		TimeLog.logStart();
		this.addBeforeRule(processer);
		// processer.before(bills, originBills);
		TimeLog.info("调用修改保存前BP插入点"); /* -=notranslate=- */

		TimeLog.logStart();
		// 更新销售订单修订数据
		BillUpdate<SaleOrderHistoryVO> bo = new BillUpdate<SaleOrderHistoryVO>();
		SaleOrderHistoryVO[] vo = (SaleOrderHistoryVO[]) bo.update(bills,
				originBills);
		TimeLog.info("保存修改单据到数据库"); /* -=notranslate=- */
		// 暂时保存不用回写等操作，框架先打好方便维护
		// processer.addAfterRule(new RewriteVbillcodeFor30R());
		TimeLog.logStart();
		// processer.after(bills, originBills);
		TimeLog.info("调用修改保存后BP插入点");/* -=notranslate=- */

		return vo;
	}

	private void addBeforeRule(
			CompareAroundProcesser<SaleOrderHistoryVO> processer) {

	}
}
