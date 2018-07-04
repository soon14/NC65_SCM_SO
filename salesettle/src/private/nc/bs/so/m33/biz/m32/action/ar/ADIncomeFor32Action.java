package nc.bs.so.m33.biz.m32.action.ar;

import nc.bs.so.m33.biz.m32.bp.square.toar.SquareARIncomeFor32BP;
import nc.bs.so.m33.biz.m32.rule.toar.AdjustIncomeFor32Rule;
import nc.bs.so.m33.biz.m32.rule.toar.CheckADIncomeDataRule;
import nc.bs.so.m33.biz.m32.rule.toar.FillNewChangeRateFor32Rule;
import nc.bs.so.m33.plugin.ActionPlugInPoint;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.so.m33.m32.entity.SquareInvVO;
import nc.vo.trade.checkrule.VOChecker;

/**
 * 差额传应收动作
 * 上游出库单必须配置自动应收结算，销售发票根据单价的差异传差额确认应收
 * 
 * @author zhangcheng
 * 
 */
public class ADIncomeFor32Action {

  public void execIncome(SquareInvVO[] vos) {
    AroundProcesser<SquareInvVO> processer =
        new AroundProcesser<SquareInvVO>(ActionPlugInPoint.ADIncomeFor32);

    // 增加执行前业务规则
    SquareInvVO[] filtervos = this.addBeforeRule(processer, vos);
    if (VOChecker.isEmpty(filtervos)) {
      return;
    }

    // 传差额确认应收处理
    new SquareARIncomeFor32BP().square(filtervos);
  }

  private SquareInvVO[] addBeforeRule(AroundProcesser<SquareInvVO> processer,
      SquareInvVO[] vos) {
    // 差额传应收业务校验
    IRule<SquareInvVO> rule = new CheckADIncomeDataRule();
    processer.addBeforeRule(rule);

    // 过滤差额传应收数据
    AdjustIncomeFor32Rule filterrule = new AdjustIncomeFor32Rule();
    SquareInvVO[] filtervos = filterrule.process(vos);
    if (VOChecker.isEmpty(filtervos)) {
      return null;
    }

    // 销售发票应收结算前汇率处理
    rule = new FillNewChangeRateFor32Rule();
    processer.addBeforeRule(rule);
    processer.before(filtervos);

    return filtervos;
  }

}
