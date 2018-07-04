package nc.bs.so.m38.maintain;

import nc.bs.so.m38.maintain.rule.CheckDateRule;
import nc.bs.so.m38.maintain.rule.insert.CheckBillAfterRule;
import nc.bs.so.m38.maintain.rule.insert.CheckBillBeforeRule;
import nc.bs.so.m38.maintain.rule.insert.RewritePriceFormRule;
import nc.bs.so.m38.maintain.rule.update.FillDataBeforeRule;
import nc.bs.so.m38.plugin.BPPlugInPoint;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.impl.pubapp.pattern.rule.ICompareRule;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.pubapp.util.SetUpdateAuditInfoRule;
import nc.vo.so.m38.entity.PreOrderVO;

/**
 * 预订单修改保存动作
 * 
 * @author 刘志伟
 */
public class UpdatePreOrderBP {
  public PreOrderVO[] update(PreOrderVO[] bills, PreOrderVO[] originBills) {
    CompareAroundProcesser<PreOrderVO> processer =
        new CompareAroundProcesser<PreOrderVO>(BPPlugInPoint.UpdateBP);

    // 增加执行前业务规则
    this.addBeforeRule(processer);

    // 增加执行后业务规则
    this.addAfterRule(processer);

    TimeLog.logStart();
    processer.before(bills, originBills);
    TimeLog.info("调用修改保存前BP插入点"); /* -=notranslate=- */

    TimeLog.logStart();
    BillUpdate<PreOrderVO> bo = new BillUpdate<PreOrderVO>();
    PreOrderVO[] vos = bo.update(bills, originBills);
    TimeLog.info("保存修改单据到数据库"); /* -=notranslate=- */

    TimeLog.logStart();
    processer.after(vos, originBills);
    TimeLog.info("调用修改保存后BP插入点"); /* -=notranslate=- */

    return vos;
  }

  private void addAfterRule(CompareAroundProcesser<PreOrderVO> processer) {
    IRule<PreOrderVO> rule = new CheckBillAfterRule();
    processer.addAfterRule(rule);

    rule = new RewritePriceFormRule();
    processer.addAfterRule(rule);
  }

  private void addBeforeRule(CompareAroundProcesser<PreOrderVO> processer) {
    ICompareRule<PreOrderVO> fillRule = new FillDataBeforeRule();
    processer.addBeforeRule(fillRule);

    // 填充审计信息:最后修改人、最后修改时间
    IRule<PreOrderVO> Rule = new SetUpdateAuditInfoRule<PreOrderVO>();
    processer.addBeforeRule(Rule);

    IRule<PreOrderVO> rule = new CheckBillBeforeRule();
    processer.addBeforeRule(rule);
    // 日期检查
    rule = new CheckDateRule();
    processer.addBeforeRule(rule);
  }

}
