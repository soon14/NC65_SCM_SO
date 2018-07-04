package nc.bs.so.buylargess.maintain;

import nc.bs.so.buylargess.plugin.BPMblargessPlugInPoint;
import nc.impl.pubapp.pattern.data.bill.BillDelete;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.mbuylargess.entity.BuyLargessVO;

public class DeleteMblargessInBP {

	public void delete(BuyLargessVO[] bills) {
		AroundProcesser<BuyLargessVO> processer = new AroundProcesser<BuyLargessVO>(
				BPMblargessPlugInPoint.DeleteMblargessInBP);

		// 增加执行前业务规则
		this.addBeforeRule(processer);
		TimeLog.logStart();
		processer.before(bills);
		TimeLog.info("删除前执行业务规则"); /* -=notranslate=- */

		TimeLog.logStart();
		BillDelete<BuyLargessVO> deleteaction = new BillDelete<BuyLargessVO>();
		deleteaction.delete(bills);
		TimeLog.info("删除"); /* -=notranslate=- */

		TimeLog.logStart();
		processer.after(bills);
		TimeLog.info("删除后执行业务规则"); /* -=notranslate=- */
	}

	private void addBeforeRule(AroundProcesser<BuyLargessVO> processer) {
	}
}
