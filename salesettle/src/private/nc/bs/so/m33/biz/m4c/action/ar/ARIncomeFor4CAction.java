package nc.bs.so.m33.biz.m4c.action.ar;

import nc.bs.so.m33.biz.m4c.bp.square.ar.SquareARIncomeFor4CBP;
import nc.bs.so.m33.biz.m4c.rule.toar.FillNewChangeRateFor4CRule;
import nc.bs.so.m33.plugin.ActionPlugInPoint;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.m33.m4c.entity.SquareOutVO;
import nc.vo.so.m33.m4c.entity.SquareOutVOUtils;

public class ARIncomeFor4CAction {

  /**
   * 应收结算
   *
   * @param sqvos
   */
  public void execIncome(SquareOutVO[] svos) {
    // 检查是否可以传应收
    SquareOutVO[] sqvos =
        SquareOutVOUtils.getInstance().filterIncomeableVO(svos);
    if (sqvos == null) {
      return;
    }

    AroundProcesser<SquareOutVO> processer =
        new AroundProcesser<SquareOutVO>(ActionPlugInPoint.ARIncomeFor4C);

    // 增加执行前业务规则
    this.addBeforeRule(processer);

    TimeLog.logStart();
    processer.before(sqvos);

    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006010_0","04006010-0011")/*@res "保存前执行业务规则"*/);

    // 传确认应收处理
    new SquareARIncomeFor4CBP().square(sqvos);
  }

  private void addBeforeRule(AroundProcesser<SquareOutVO> processer) {

    // 销售出库单应收结算前汇率处理
    IRule<SquareOutVO> rule = new FillNewChangeRateFor4CRule();
    processer.addBeforeRule(rule);

  }
}