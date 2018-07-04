package nc.bs.so.m33.maintain.m4453;

import nc.bs.so.m33.maintain.m4453.rule.square.InsSQ4453CheckRule;
import nc.bs.so.m33.maintain.m4453.rule.square.InsSQ4453DefaultDataRule;
import nc.bs.so.m33.plugin.BPPlugInPoint;
import nc.impl.pubapp.pattern.data.bill.BillInsert;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.m33.m4453.entity.SquareWasVO;

public class InsertSquare4453BP {

  public SquareWasVO[] insert(SquareWasVO[] bills) {
    AroundProcesser<SquareWasVO> processer =
        new AroundProcesser<SquareWasVO>(BPPlugInPoint.InsertSquare4453BP);

    // 增加执行前业务规则
    this.addBeforeRule(processer);

    // 增加执行后业务规则
    // this.addAfterRule(processer);

    TimeLog.logStart();
    processer.before(bills);

    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006010_0","04006010-0011")/*@res "保存前执行业务规则"*/);

    TimeLog.logStart();
    BillInsert<SquareWasVO> bo = new BillInsert<SquareWasVO>();
    SquareWasVO[] vos = bo.insert(bills);

    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006010_0","04006010-0039")/*@res "保存单据到数据库"*/);

    TimeLog.logStart();
    processer.after(vos);

    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006010_0","04006010-0040")/*@res "保存后执行业务规则"*/);

    return vos;
  }

  private void addBeforeRule(AroundProcesser<SquareWasVO> processer) {

    // 销售结算单保存前设置默认数据规则
    IRule<SquareWasVO> rule = new InsSQ4453DefaultDataRule();
    processer.addBeforeRule(rule);

    // 销售结算单保存校验规则
    rule = new InsSQ4453CheckRule();
    processer.addBeforeRule(rule);

  }

}