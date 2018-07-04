package nc.bs.so.m33.maintain.m4c;

import nc.bs.so.m33.maintain.m4c.rule.square.InsSQ4CCheckRule;
import nc.bs.so.m33.maintain.m4c.rule.square.InsSQ4CDefaultDataRule;
import nc.bs.so.m33.plugin.BPPlugInPoint;
import nc.impl.pubapp.pattern.data.bill.BillInsert;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.m33.m4c.entity.SquareOutVO;

public class InsertSquare4CBP {

  private void addBeforeRule(AroundProcesser<SquareOutVO> processer) {

    // 销售结算单保存前设置默认数据规则
    IRule<SquareOutVO> rule = new InsSQ4CDefaultDataRule();
    processer.addBeforeRule(rule);

    // 销售结算单保存校验规则
    rule = new InsSQ4CCheckRule();
    processer.addBeforeRule(rule);

  }

  public SquareOutVO[] insert(SquareOutVO[] bills) {
    AroundProcesser<SquareOutVO> processer =
        new AroundProcesser<SquareOutVO>(BPPlugInPoint.InsertSquare4CBP);

    // 增加执行前业务规则
    this.addBeforeRule(processer);

    // 增加执行后业务规则
    // this.addAfterRule(processer);

    TimeLog.logStart();
    processer.before(bills);
    TimeLog.info("保存前执行业务规则");/*-=notranslate=-*/

    TimeLog.logStart();
    BillInsert<SquareOutVO> bo = new BillInsert<SquareOutVO>();
    SquareOutVO[] vos = bo.insert(bills);
    TimeLog.info("保存单据到数据库");/*-=notranslate=-*/

    TimeLog.logStart();
    processer.after(vos);
    TimeLog.info("保存后执行业务规则");/*-=notranslate=-*/

    return vos;
  }

  /*
   * private void addAfterRule(AroundProcesser<SquareOutVO> processer) { }
   */

}
