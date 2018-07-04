package nc.bs.so.m33.biz.m4c.action.ar;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nc.bs.so.m33.biz.m4c.bp.square.ar.SquareARRushIncomeFor4CSBP;
import nc.bs.so.m33.biz.m4c.bp.square.ar.SquareETIncomeFor4CBP;
import nc.bs.so.m33.biz.m4c.rule.toar.FillNewChangeRateFor4CRule;
import nc.bs.so.m33.plugin.ActionPlugInPoint;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.pubimpl.so.m33.self.pub.Square434CQueryImpl;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.m33.m4c.entity.SquareOutBVO;
import nc.vo.so.m33.m4c.entity.SquareOutDetailVO;
import nc.vo.so.m33.m4c.entity.SquareOutVO;
import nc.vo.so.m33.m4c.entity.SquareOutVOUtils;
import nc.vo.so.pub.util.AggVOUtil;
import nc.vo.trade.checkrule.VOChecker;

public class ETIncomeFor4CAction {
  /**
   * 暂估应收
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
        new AroundProcesser<SquareOutVO>(ActionPlugInPoint.ETIncomeFor4C);

    // 增加执行前业务规则
    this.addBeforeRule(processer);

    TimeLog.logStart();
    processer.before(sqvos);
    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006010_0","04006010-0011")/*@res "保存前执行业务规则"*/);

    // 过滤基于签收开票的退回出库单和普通出库单
    List<SquareOutVO[]> retlist = this.filterSquareOutVO(sqvos);

    // 传回冲应收--基于签收开票的退回出库单配暂估应收
    new SquareARRushIncomeFor4CSBP().square(retlist.get(1));

    // 传暂估应收
    new SquareETIncomeFor4CBP().square(retlist.get(0));

  }

  private void addBeforeRule(AroundProcesser<SquareOutVO> processer) {

    // 销售出库单应收结算前汇率处理
    IRule<SquareOutVO> rule = new FillNewChangeRateFor4CRule();
    processer.addBeforeRule(rule);

  }

  /**
   * 过滤基于签收开票的退回出库单和普通出库单
   *
   * @param vos
   * @return List<SquareOutVO[]>[0] -- 正常的出库待结算单
   *         List<SquareOutVO[]>[1] -- 基于签收开票的出库待结算单
   */
  private List<SquareOutVO[]> filterSquareOutVO(SquareOutVO[] vos) {
    List<SquareOutVO> lreturnsvo = new ArrayList<SquareOutVO>();
    List<SquareOutVO> lsvo = new ArrayList<SquareOutVO>();
    for (SquareOutVO svo : vos) {
      UFBoolean flag = svo.getParentVO().getBreturnoutstock();
      if (!VOChecker.isEmpty(flag) && flag.booleanValue()) {
        lreturnsvo.add(svo);
      }
      else {
        lsvo.add(svo);
      }
    }
    SquareOutVO[] retRushVos = null;
    if (lreturnsvo.size() > 0) {
      retRushVos = lreturnsvo.toArray(new SquareOutVO[lreturnsvo.size()]);

      // 查询上游出库单暂估应收记录
      String[] outBids =
          AggVOUtil.getDistinctItemFieldArray(vos, SquareOutBVO.CSRCBID,
              String.class);
      Map<String, SquareOutDetailVO> outmap =
          new Square434CQueryImpl().queryETIncomeDvosBy4CBID(outBids);
      for (SquareOutVO svo : retRushVos) {
        for (SquareOutBVO bvo : svo.getChildrenVO()) {
          String srcbid = bvo.getCsrcbid();
          SquareOutDetailVO dvo = outmap.get(srcbid);
          // 上游出库单做过回冲应收
          if (dvo != null) {
            // 用回冲结算结算批次号暂存上游出库结算单id,为以后传回冲应收使用
            bvo.setProcesseid(dvo.getCsalesquaredid());
          }
        }
      }
    }

    SquareOutVO[] sVos = null;
    if (lsvo.size() > 0) {
      sVos = lsvo.toArray(new SquareOutVO[lsvo.size()]);
    }
    List<SquareOutVO[]> retlist = new ArrayList<SquareOutVO[]>();
    retlist.add(sVos);
    retlist.add(retRushVos);
    return retlist;
  }
}