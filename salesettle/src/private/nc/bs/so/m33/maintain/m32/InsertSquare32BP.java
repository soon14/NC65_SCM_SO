package nc.bs.so.m33.maintain.m32;

import nc.bs.so.m33.maintain.m32.rule.square.InsSQ32CheckRule;
import nc.bs.so.m33.maintain.m32.rule.square.InsSQ32DefaultDataRule;
import nc.bs.so.m33.plugin.BPPlugInPoint;
import nc.impl.pubapp.pattern.data.bill.BillInsert;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.m33.m32.entity.SquareInvVO;

public class InsertSquare32BP {

  private void addBeforeRule(AroundProcesser<SquareInvVO> processer) {

    // 销售结算单保存前设置默认数据规则
    IRule<SquareInvVO> rule = new InsSQ32DefaultDataRule();
    processer.addBeforeRule(rule);

    // 销售结算单保存校验规则
    rule = new InsSQ32CheckRule();
    processer.addBeforeRule(rule);

  }

  public SquareInvVO[] insert(SquareInvVO[] bills) {
    AroundProcesser<SquareInvVO> processer =
        new AroundProcesser<SquareInvVO>(BPPlugInPoint.InsertSquare32BP);

    // 增加执行前业务规则
    this.addBeforeRule(processer);

    // 增加执行后业务规则
    // this.addAfterRule(processer);


    TimeLog.logStart();
    processer.before(bills);

    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006010_0","04006010-0011")/*@res "保存前执行业务规则"*/);


    TimeLog.logStart();
    BillInsert<SquareInvVO> bo = new BillInsert<SquareInvVO>();
    SquareInvVO[] vos = bo.insert(bills);

    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006010_0","04006010-0039")/*@res "保存单据到数据库"*/);


    TimeLog.logStart();
    processer.after(vos);

    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006010_0","04006010-0040")/*@res "保存后执行业务规则"*/);

    return vos;
  }

  /*
   * private void addAfterRule(AroundProcesser<SquareInvVO> processer) { }
   */

}