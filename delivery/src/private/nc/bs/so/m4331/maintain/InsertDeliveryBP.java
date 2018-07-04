package nc.bs.so.m4331.maintain;

import nc.bs.so.m4331.maintain.rule.atp.DeliveryVOATPBeforeRule;
import nc.bs.so.m4331.maintain.rule.credit.RenovateARByHidsBeginRule;
import nc.bs.so.m4331.maintain.rule.credit.RenovateARByHidsEndRule;
import nc.bs.so.m4331.maintain.rule.insert.CheckBillCodeRule;
import nc.bs.so.m4331.maintain.rule.insert.CheckNewNullRule;
import nc.bs.so.m4331.maintain.rule.insert.CheckValityRule;
import nc.bs.so.m4331.maintain.rule.insert.FillNewDefaultRule;
import nc.bs.so.m4331.maintain.rule.insert.RewriteBillInsertRule;
import nc.bs.so.m4331.maintain.rule.material.MaterielDistributeCheckRule;
import nc.bs.so.m4331.maintain.rule.reverse.AutoReserveRule;
import nc.bs.so.m4331.plugin.BP4331PlugInPoint;
import nc.bs.so.pub.rule.FillBillTailInfoRuleForIns;
import nc.impl.pubapp.bd.userdef.UserDefSaveRule;
import nc.impl.pubapp.pattern.data.bill.BillInsert;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.vo.credit.engrossmaintain.pub.action.M4331EngrossAction;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.pubapp.util.SetAddAuditInfoRule;
import nc.vo.scmpub.rule.TrafficOrgEnableCheckRule;
import nc.vo.so.m4331.entity.DeliveryBVO;
import nc.vo.so.m4331.entity.DeliveryHVO;
import nc.vo.so.m4331.entity.DeliveryVO;
import nc.vo.so.m4331.rule.DeliveryMarginRule;

public class InsertDeliveryBP {

  private void addAfterRule(AroundProcesser<DeliveryVO> processer) {
    // 单据号重复性校验
    IRule<DeliveryVO> rule = new CheckBillCodeRule();
    processer.addAfterRule(rule);
    // 信用占用检查
    rule = new RenovateARByHidsEndRule(M4331EngrossAction.M4331Insert);
    processer.addAfterRule(rule);
    // 回写
    rule = new RewriteBillInsertRule();
    processer.addAfterRule(rule);

    boolean icEnable = SysInitGroupQuery.isICEnabled();
    if (icEnable) {
      // 自动预留
      rule = new AutoReserveRule();
      processer.addAfterRule(rule);
    }

  }

  private void addBeforeRule(AroundProcesser<DeliveryVO> processer) {

    // 新增非空校验
    IRule<DeliveryVO> rule = new CheckNewNullRule();
    processer.addBeforeRule(rule);

    // 物流组织停用检查
    rule = new TrafficOrgEnableCheckRule<DeliveryVO>();
    processer.addBeforeRule(rule);
    // 数据合法性校验
    rule = new CheckValityRule();
    processer.addBeforeRule(rule);
    // 填充默认值
    rule = new FillNewDefaultRule();
    processer.addBeforeRule(rule);
    // 填充制单信息
    rule = new FillBillTailInfoRuleForIns<DeliveryVO>();
    processer.addBeforeRule(rule);

    // 填充审计信息:创建人、创建时间、最后修改人、最后修改时间
    rule = new SetAddAuditInfoRule<DeliveryVO>();
    processer.addBeforeRule(rule);

    // 检查物料是否分配到对应的库存组织
    rule = new MaterielDistributeCheckRule();
    processer.addBeforeRule(rule);

    
    // 信用占用检查
    rule = new RenovateARByHidsBeginRule(M4331EngrossAction.M4331Insert);
    processer.addBeforeRule(rule);

    boolean icEnable = SysInitGroupQuery.isICEnabled();
    if (icEnable) {
      // 新增前可用量检查
      rule = new DeliveryVOATPBeforeRule();
      processer.addBeforeRule(rule);
    }
    // 校验表头表体录入的自定义项是否启用
    rule = new UserDefSaveRule<DeliveryVO>(new Class[] {
      DeliveryHVO.class, DeliveryBVO.class
    });
    processer.addBeforeRule(rule);
  }

  public DeliveryVO[] insert(DeliveryVO[] bills) {
    AroundProcesser<DeliveryVO> processer =
        new AroundProcesser<DeliveryVO>(BP4331PlugInPoint.InsertAction);

    // 增加执行前业务规则
    this.addBeforeRule(processer);

    // 增加执行后业务规则
    this.addAfterRule(processer);

    TimeLog.logStart();
    processer.before(bills);
    // buxh 发货安排界面重复安排的情况 要在保存再处理一下尾差
    DeliveryMarginRule margin = new DeliveryMarginRule();
    margin.process(bills);
    TimeLog.info("保存前执行业务规则"); /* -=notranslate=- */

    TimeLog.logStart();
    BillInsert<DeliveryVO> bo = new BillInsert<DeliveryVO>();
    DeliveryVO[] vos = bo.insert(bills);
    TimeLog.info("保存单据到数据库"); /* -=notranslate=- */

    TimeLog.logStart();
    processer.after(vos);
    TimeLog.info("保存后执行业务规则"); /* -=notranslate=- */

    return vos;
  }

}
