package nc.bs.so.m4331.maintain;

import nc.bs.so.m4331.maintain.rule.atp.DeliveryVOATPBeforeRule;
import nc.bs.so.m4331.maintain.rule.credit.RenovateARByHidsBeginRule;
import nc.bs.so.m4331.maintain.rule.credit.RenovateARByHidsEndRule;
import nc.bs.so.m4331.maintain.rule.insert.CheckBillCodeRule;
import nc.bs.so.m4331.maintain.rule.insert.CheckValityRule;
import nc.bs.so.m4331.maintain.rule.material.MaterielDistributeCheckRule;
import nc.bs.so.m4331.maintain.rule.reverse.AutoReserveRule;
import nc.bs.so.m4331.maintain.rule.reverse.ReserveUpdateRule;
import nc.bs.so.m4331.maintain.rule.update.CheckUpdateNullRule;
import nc.bs.so.m4331.maintain.rule.update.FillUpdateDefaultRule;
import nc.bs.so.m4331.maintain.rule.update.RewriteBillUpdateRule;
import nc.bs.so.m4331.plugin.BP4331PlugInPoint;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.impl.pubapp.pattern.rule.ICompareRule;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.vo.credit.engrossmaintain.pub.action.M4331EngrossAction;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.pubapp.util.SetUpdateAuditInfoRule;
import nc.vo.so.m4331.entity.DeliveryVO;

public class UpdateDeliveryBP {

  public DeliveryVO[] update(DeliveryVO[] bills, DeliveryVO[] originBills) {
    CompareAroundProcesser<DeliveryVO> processer =
        new CompareAroundProcesser<DeliveryVO>(BP4331PlugInPoint.UpdateAction);
    // 增加执行前业务规则
    this.addBeforeRule(processer);
    // 增加执行后业务规则
    this.addAfterRule(processer);

    // 可用量检查 要传入originBills 所以单独处理
    AroundProcesser<DeliveryVO> atpprocesser =
        new AroundProcesser<DeliveryVO>(BP4331PlugInPoint.UpdateActionForATP);
    boolean icEnable = SysInitGroupQuery.isICEnabled();
    if (icEnable) {
      // 修改前可用量检查
      IRule<DeliveryVO> rule = new DeliveryVOATPBeforeRule();
      atpprocesser.addBeforeRule(rule);
    }

    TimeLog.logStart();
    atpprocesser.before(originBills);
    processer.before(bills, originBills);
    TimeLog.info("修改保存前执行业务规则"); /* -=notranslate=- */
    TimeLog.logStart();
    BillUpdate<DeliveryVO> bo = new BillUpdate<DeliveryVO>();
    DeliveryVO[] vos = bo.update(bills, originBills);
    TimeLog.info("修改保存单据到数据库"); /* -=notranslate=- */

    TimeLog.logStart();
    processer.after(bills, originBills);
    atpprocesser.after(originBills);
    TimeLog.info("修改保存后执行业务规则"); /* -=notranslate=- */

    return vos;
  }

  private void addAfterRule(CompareAroundProcesser<DeliveryVO> processer) {
    // 信用占用检查
    IRule<DeliveryVO> rule =
        new RenovateARByHidsEndRule(M4331EngrossAction.M4331Edit);
    processer.addAfterRule(rule);
    // 单据号规则
    rule = new CheckBillCodeRule();
    processer.addAfterRule(rule);
    // 回写来源单据
    ICompareRule<DeliveryVO> comRule = new RewriteBillUpdateRule();
    processer.addAfterRule(comRule);
    boolean icEnable = SysInitGroupQuery.isICEnabled();
    if (icEnable) {
      // 调用预留调整
      rule = new ReserveUpdateRule();
      processer.addAfterRule(rule);
      // 自动预留
      rule = new AutoReserveRule();
      processer.addAfterRule(rule);
    }

  }

  private void addBeforeRule(CompareAroundProcesser<DeliveryVO> processer) {

    // 修改非空校验
    IRule<DeliveryVO> rule = new CheckUpdateNullRule();
    processer.addBeforeRule(rule);
    // 数据合法性校验
    rule = new CheckValityRule();
    processer.addBeforeRule(rule);
    // 物料和库存组织检查判断
    rule = new MaterielDistributeCheckRule();
    processer.addBeforeRule(rule);

    // 审计信息:最后修改人、最后修改时间
    rule = new SetUpdateAuditInfoRule<DeliveryVO>();
    processer.addBeforeRule(rule);

    // 填充默认值
    ICompareRule<DeliveryVO> fillRule = new FillUpdateDefaultRule();
    processer.addBeforeRule(fillRule);
    // 信用占用检查
    rule = new RenovateARByHidsBeginRule(M4331EngrossAction.M4331Edit);
    processer.addBeforeRule(rule);
  }

}
