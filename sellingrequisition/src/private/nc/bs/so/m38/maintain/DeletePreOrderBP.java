package nc.bs.so.m38.maintain;

import nc.bs.so.m38.maintain.rule.delete.DeleteBillAfterRule;
import nc.bs.so.m38.maintain.rule.delete.DeleteBillBeforeRule;
import nc.bs.so.m38.maintain.rule.delete.DeletePriceFormRule;
import nc.bs.so.m38.plugin.BPPlugInPoint;
import nc.impl.pubapp.pattern.data.bill.BillDelete;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.m38.entity.PreOrderVO;

public class DeletePreOrderBP {
  public void delete(PreOrderVO[] bills) {
    AroundProcesser<PreOrderVO> processer =
        new AroundProcesser<PreOrderVO>(BPPlugInPoint.DeleteBP);

    // 增加执行前业务规则
    this.addBeforeRule(processer);

    // 增加执行后业务规则
    this.addAfterRule(processer);

    TimeLog.logStart();
    processer.before(bills);
    TimeLog.info("调用删除前BP插入点"); /*-=notranslate=-*/

    TimeLog.logStart();
    BillDelete<PreOrderVO> bo = new BillDelete<PreOrderVO>();
    bo.delete(bills);
    TimeLog.info("写数据库，删除单据"); /*-=notranslate=-*/

    TimeLog.logStart();
    processer.after(bills);
    TimeLog.info("调用删除后BP插入点"); /*-=notranslate=-*/
  }

  private void addAfterRule(AroundProcesser<PreOrderVO> processer) {
    // 退回单据号规则
    IRule<PreOrderVO> rule = new DeleteBillAfterRule();
    processer.addAfterRule(rule);

    // 删除价格组成信息
    rule = new DeletePriceFormRule();
    processer.addAfterRule(rule);
  }

  private void addBeforeRule(AroundProcesser<PreOrderVO> processer) {
    // 删除前数据合法性校验
    IRule<PreOrderVO> rule = new DeleteBillBeforeRule();
    processer.addBeforeRule(rule);

  }
}
