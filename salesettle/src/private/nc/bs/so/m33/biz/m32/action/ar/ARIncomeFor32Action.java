package nc.bs.so.m33.biz.m32.action.ar;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.bs.so.m33.biz.m32.bp.square.toar.SquareARIncomeFor32BP;
import nc.bs.so.m33.biz.m32.bp.square.toar.SquareARRushIncomeFor32BP;
import nc.bs.so.m33.biz.m32.rule.toar.FillNewChangeRateFor32Rule;
import nc.bs.so.m33.plugin.ActionPlugInPoint;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.pubitf.so.m33.self.pub.ISquare434CQuery;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m33.m32.entity.SquareInvBVO;
import nc.vo.so.m33.m32.entity.SquareInvVO;
import nc.vo.so.m33.m32.entity.SquareInvVOUtils;
import nc.vo.so.m33.m32.entity.SquareInvViewVO;
import nc.vo.so.m33.m4c.entity.SquareOutDetailVO;
import nc.vo.so.pub.util.AggVOUtil;
import nc.vo.trade.checkrule.VOChecker;

public class ARIncomeFor32Action {

  /**
   * 应收结算（如果出库单做过暂估应收，需要先传回冲应收）
   * 
   * @param vos
   */
  public void execIncome(SquareInvVO[] vos) {

    AroundProcesser<SquareInvVO> processer =
        new AroundProcesser<SquareInvVO>(ActionPlugInPoint.ARIncomeFor32);

    // 增加执行前业务规则
    this.addBeforeRule(processer);

    TimeLog.logStart();
    processer.before(vos);
    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4006010_0", "04006010-0011")/*@res "保存前执行业务规则"*/);

    // 获取回冲应收数据
    SquareInvVO[] et_svos = this.filterSquareInvVO(vos);

    // 传回冲应收，数量取反（相对于确认应收）
    this.processARRushData(et_svos);

    // 上游出库单做过暂估应收，发票传回冲应收
    new SquareARRushIncomeFor32BP().square(et_svos);

    // 传确认应收处理
    new SquareARIncomeFor32BP().square(vos);
  }

  private void addBeforeRule(AroundProcesser<SquareInvVO> processer) {
    // 销售发票应收结算前汇率处理
    IRule<SquareInvVO> rule = new FillNewChangeRateFor32Rule();
    processer.addBeforeRule(rule);
  }

  /**
   * 根据上游出库单行是否后做过暂估应收将待结算VO分出回冲应收数据
   * 
   * @param vos
   * @return SquareInvVO[] -- 回冲应收vo
   * 
   */
  private SquareInvVO[] filterSquareInvVO(SquareInvVO[] vos) {

    // 回冲应收数据
    SquareInvVO[] etvos = null;

    // 销售出库结算查询接口
    ISquare434CQuery square4cQry =
        NCLocator.getInstance().lookup(ISquare434CQuery.class);

    // 查询上游出库单暂估应收记录
    String[] outBids =
        AggVOUtil.getDistinctItemFieldArray(vos, SquareInvBVO.CSRCBID,
            String.class);
    Map<String, SquareOutDetailVO> outmap =
        square4cQry.queryETIncomeDvosBy4CBID(outBids);

    if (outmap.size() > 0) {
      // 回冲应收
      List<SquareInvViewVO> l_viewvo_et = new ArrayList<SquareInvViewVO>();
      SquareInvViewVO[] sviewvos =
          SquareInvVOUtils.getInstance().changeToSaleSquareViewVO(vos);
      for (SquareInvViewVO svo : sviewvos) {
        String srcbid = svo.getItem().getCsrcbid();
        SquareOutDetailVO dvo = outmap.get(srcbid);
        // 上游出库单做过回冲应收
        if (dvo != null) {
          SquareInvViewVO rushsvo = (SquareInvViewVO) svo.clone();
          // 用回冲结算结算批次号暂存上游出库结算单id,为以后传回冲应收使用
          rushsvo.getItem().setProcesseid(dvo.getCsalesquaredid());
          l_viewvo_et.add(rushsvo);
        }

      }
      etvos =
          SquareInvVOUtils.getInstance().combineBill(
              l_viewvo_et.toArray(new SquareInvViewVO[0]));
    }

    return etvos;
  }

  /**
   * 传回冲应收，数量取反（相对于确认应收）
   * 
   * @param et_svos
   */
  private void processARRushData(SquareInvVO[] et_svos) {
    if (VOChecker.isEmpty(et_svos)) {
      return;
    }
    for (SquareInvVO svo : et_svos) {
      for (SquareInvBVO bvo : svo.getChildrenVO()) {
        bvo.setNthisnum(MathTool.oppose(bvo.getNthisnum()));
      }
    }
  }

}
