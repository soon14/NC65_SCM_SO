package nc.bs.so.buylargess.maintain;

import nc.bs.so.buylargess.maintain.rule.BuyLargessDefaultValueRule;
import nc.bs.so.buylargess.maintain.rule.BuyLargessIntegralCheck;
import nc.bs.so.buylargess.maintain.rule.BuyLargessPriorityCodeRule;
import nc.bs.so.buylargess.maintain.rule.BuyLargessUniqueCheck;
import nc.bs.so.buylargess.maintain.rule.BuyLargessValidateCheck;
import nc.bs.so.buylargess.plugin.BPMblargessPlugInPoint;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.mbuylargess.entity.BuyLargessVO;

public class UpdateMblargessInBP {

  public BuyLargessVO[] update(BuyLargessVO[] bills, BuyLargessVO[] originBills) {
    AroundProcesser<BuyLargessVO> processer =
        new AroundProcesser<BuyLargessVO>(
            BPMblargessPlugInPoint.UpdateMblargessInBP);

    // 增加执行前业务规则
    this.addBeforeRule(processer);

    TimeLog.logStart();
    processer.before(bills);
    TimeLog.info("修改保存前执行业务规则"); /*-=notranslate=-*/

    TimeLog.logStart();
    BillUpdate<BuyLargessVO> bo = new BillUpdate<BuyLargessVO>();
    BuyLargessVO[] vos = bo.update(bills, originBills);
    TimeLog.info("保存修改单据到数据库"); /*-=notranslate=-*/
    return vos;

  }

  private void addBeforeRule(AroundProcesser<BuyLargessVO> processer) {
    // 设置起始日期和截止日期
    IRule<BuyLargessVO> rule = new BuyLargessDefaultValueRule();
    processer.addBeforeRule(rule);
    // 数据完整性校验
    rule = new BuyLargessIntegralCheck();
    processer.addBeforeRule(rule);
    // 数据唯一性校验
    rule = new BuyLargessUniqueCheck();
    processer.addBeforeRule(rule);
    // 数据合法性校验
    rule = new BuyLargessValidateCheck();
    processer.addBeforeRule(rule);
    // 优先码重新生成
    rule = new BuyLargessPriorityCodeRule();
    processer.addBeforeRule(rule);
  }
}
