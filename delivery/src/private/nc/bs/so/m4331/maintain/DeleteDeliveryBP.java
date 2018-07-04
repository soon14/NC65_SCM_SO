package nc.bs.so.m4331.maintain;

import nc.bs.so.m4331.maintain.rule.credit.RenovateARByHidsBeginRule;
import nc.bs.so.m4331.maintain.rule.credit.RenovateARByHidsEndRule;
import nc.bs.so.m4331.maintain.rule.delete.CheckEnableDeleteRule;
import nc.bs.so.m4331.maintain.rule.delete.ReturnBillCodeRule;
import nc.bs.so.m4331.maintain.rule.delete.RewriteBillDeleteRule;
import nc.bs.so.m4331.maintain.rule.reverse.ReserveDeleteRule;
import nc.bs.so.m4331.plugin.BP4331PlugInPoint;
import nc.impl.pubapp.pattern.data.bill.BillDelete;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.vo.credit.engrossmaintain.pub.action.M4331EngrossAction;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.m4331.entity.DeliveryVO;

public class DeleteDeliveryBP {

  public void delete(DeliveryVO[] bills) {

    AroundProcesser<DeliveryVO> processer =
        new AroundProcesser<DeliveryVO>(BP4331PlugInPoint.DeleteAction);

    // 增加执行前业务规则
    this.addBeforeRule(processer);

    // 增加执行后业务规则
    this.addAfterRule(processer);

    TimeLog.logStart();
    processer.before(bills);
    TimeLog.info("删除前执行业务规则"); /*-=notranslate=-*/

    TimeLog.logStart();
    BillDelete<DeliveryVO> bo = new BillDelete<DeliveryVO>();
    bo.delete(bills);
    TimeLog.info("写数据库，删除单据"); /*-=notranslate=-*/

    TimeLog.logStart();
    processer.after(bills);
    TimeLog.info("删除后执行业务规则"); /*-=notranslate=-*/

  }

  private void addAfterRule(AroundProcesser<DeliveryVO> processer) {
    // 信用占用检查
    IRule<DeliveryVO> rule =
        new RenovateARByHidsEndRule(M4331EngrossAction.M4331Delete);
    processer.addAfterRule(rule);
    rule = new RewriteBillDeleteRule();
    processer.addAfterRule(rule);
    rule = new ReturnBillCodeRule();
    processer.addAfterRule(rule);
  }

  /**
   * 方法功能描述：添加删除前规则。
   * <p>
   * <b>参数说明</b>
   * 
   * @param processer
   *          <p>
   * @author 祝会征
   * @time 2010-1-22 上午08:57:00
   */
  private void addBeforeRule(AroundProcesser<DeliveryVO> processer) {
    // 校验删除
    IRule<DeliveryVO> rule = new CheckEnableDeleteRule();
    processer.addBeforeRule(rule);

    // 信用占用检查
    rule = new RenovateARByHidsBeginRule(M4331EngrossAction.M4331Delete);
    processer.addBeforeRule(rule);

    boolean icEnable = SysInitGroupQuery.isICEnabled();
    if (icEnable) {
      // 调用预留调整
      rule = new ReserveDeleteRule();
      processer.addBeforeRule(rule);
    }

  }

}
