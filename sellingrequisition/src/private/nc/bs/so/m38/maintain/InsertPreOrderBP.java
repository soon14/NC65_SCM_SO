package nc.bs.so.m38.maintain;

import nc.bs.so.m38.maintain.rule.CheckDateRule;
import nc.bs.so.m38.maintain.rule.insert.CheckBillAfterRule;
import nc.bs.so.m38.maintain.rule.insert.CheckBillBeforeRule;
import nc.bs.so.m38.maintain.rule.insert.FillDataBeforeRule;
import nc.bs.so.m38.maintain.rule.insert.RewritePriceFormRule;
import nc.bs.so.m38.plugin.BPPlugInPoint;
import nc.bs.so.pub.rule.FillBillTailInfoRuleForIns;
import nc.impl.pubapp.pattern.data.bill.BillInsert;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.pubapp.util.SetAddAuditInfoRule;
import nc.vo.scmpub.rule.SaleOrgEnableCheckRule;
import nc.vo.so.m38.entity.PreOrderVO;

/**
 * 预订单新增保存BP
 * 
 * @author 刘志伟
 */
public class InsertPreOrderBP {

  public PreOrderVO[] insert(PreOrderVO[] bills) {
    AroundProcesser<PreOrderVO> processer =
        new AroundProcesser<PreOrderVO>(BPPlugInPoint.InsertBP);

    TimeLog.logStart();
    // 增加执行前业务规则
    this.addBeforeRule(processer);

    processer.before(bills);
    TimeLog.info("调用新增保存前BP插入点"); /* -=notranslate=- */

    TimeLog.logStart();
    BillInsert<PreOrderVO> bo = new BillInsert<PreOrderVO>();
    PreOrderVO[] vos = bo.insert(bills);
    TimeLog.info("保存单据到数据库"); /* -=notranslate=- */

    TimeLog.logStart();
    // 增加执行后业务规则
    this.addAfterRule(processer);
    processer.after(vos);
    TimeLog.info("调用新增保存后BP插入点"); /* -=notranslate=- */

    return vos;
  }

  private void addAfterRule(AroundProcesser<PreOrderVO> processer) {

    IRule<PreOrderVO> rule = new CheckBillAfterRule();
    processer.addAfterRule(rule);

    rule = new RewritePriceFormRule();
    processer.addAfterRule(rule);

  }

  private void addBeforeRule(AroundProcesser<PreOrderVO> processer) {

    // 销售组织停用检查
    IRule<PreOrderVO> rule = new SaleOrgEnableCheckRule<PreOrderVO>();
    processer.addBeforeRule(rule);

    // 数据合法性检查
    rule = new CheckBillBeforeRule();
    processer.addBeforeRule(rule);
    // 日期检查
    rule = new CheckDateRule();
    processer.addBeforeRule(rule);

    // 填充默认值
    rule = new FillDataBeforeRule();
    processer.addBeforeRule(rule);

    // 填充制单信息
    rule = new FillBillTailInfoRuleForIns<PreOrderVO>();
    processer.addBeforeRule(rule);

    // 填充审计信息
    // 填充审计信息:创建人、创建时间、最后修改人、最后修改时间
    rule = new SetAddAuditInfoRule<PreOrderVO>();
    processer.addBeforeRule(rule);
    /* rule = new SetUpdateAuditInfoRule<PreOrderVO>();
     * processer.addBeforeRule(rule); */

  }
}
