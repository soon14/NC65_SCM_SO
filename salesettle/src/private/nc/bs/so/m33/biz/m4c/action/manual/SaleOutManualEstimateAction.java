package nc.bs.so.m33.biz.m4c.action.manual;

import java.util.HashMap;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.bs.ml.NCLangResOnserver;
import nc.bs.so.m33.biz.m4453.bp.cancelsquare.CancelSquareWasDetailPubFor4453BP;
import nc.bs.so.m33.biz.m4453.bp.square.ar.SquareARRushIncomeFor4453BP;
import nc.bs.so.m33.biz.m4c.bp.cancelsquare.CancelSquareFor4CPubBP;
import nc.bs.so.m33.biz.m4c.bp.cancelsquare.manual.CancelManualETFor4CPubBP;
import nc.bs.so.m33.biz.m4c.bp.square.ar.SquareARRushIncomeFor4CSBP;
import nc.bs.so.m33.biz.m4c.bp.square.ar.SquareETIncomeFor4CBP;
import nc.bs.so.m33.biz.m4c.rule.toar.CheckForCancelET4CRule;
import nc.bs.so.m33.maintain.m4c.query.QuerySquare4CVOBP;
import nc.bs.so.m33.plugin.BPPlugInPoint;
import nc.impl.pubapp.pattern.pub.LockOperator;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.itf.so.m33.ref.ic.m4453.ICM4453ServiceUtil;
import nc.itf.so.m33.ref.ic.m4c.ICM4CServiceUtil;
import nc.pubimpl.so.m33.self.pub.Square434CQueryImpl;
import nc.pubitf.so.m33.self.pub.ISquare4353Query;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m33.m4453.entity.SquareWasDetailVO;
import nc.vo.so.m33.m4453.entity.SquareWasVOUtils;
import nc.vo.so.m33.m4453.entity.SquareWasViewVO;
import nc.vo.so.m33.m4c.entity.SquareOutBVO;
import nc.vo.so.m33.m4c.entity.SquareOutDetailVO;
import nc.vo.so.m33.m4c.entity.SquareOutVO;
import nc.vo.so.m33.m4c.entity.SquareOutVOUtils;
import nc.vo.so.m33.m4c.entity.SquareOutViewVO;
import nc.vo.so.pub.util.AggVOUtil;
import nc.vo.so.pub.votools.SoVoTools;
import nc.vo.trade.checkrule.VOChecker;

public class SaleOutManualEstimateAction {

  public void manualEstimate(SquareOutViewVO[] vos) {

    SquareOutVO[] svos = SquareOutVOUtils.getInstance().combineBill(vos);

    // 1.补充数据
    SquareOutVOUtils.getInstance().fillDataForManualSquare(vos);

    // 2.暂估应收
    new SquareETIncomeFor4CBP().square(svos);

    // 销售出库单表体id
    String[] outBids =
        AggVOUtil.getDistinctItemFieldArray(svos, SquareOutBVO.CSQUAREBILLBID,
            String.class);

    // 重新刷新销售出库待结算单
    SquareOutVO[] newsvos = new QuerySquare4CVOBP().refushVO(svos);

    // 3.处理途损
    this.processWastageForManualEstimate(outBids, newsvos);

    // 4.处理基于签收开票的退回的出库单
    this.processReturnOutForManualEstimate(outBids, newsvos);

  }

  public void manualUnEstimate(SquareOutViewVO[] vos) {
    // 增加执行前业务规则
    AroundProcesser<SquareOutViewVO> processer =
        new AroundProcesser<SquareOutViewVO>(BPPlugInPoint.ManualET);
    this.addManualETBeforeRule(processer);
    processer.before(vos);

    // 待结算单
    SquareOutVO[] sqvos = SquareOutVOUtils.getInstance().combineBill(vos);

    // 销售出库单表体id
    String[] outBids =
        AggVOUtil.getDistinctItemFieldArray(sqvos, SquareOutBVO.CSQUAREBILLBID,
            String.class);

    // 3.处理途损
    this.processWastageForManualUnEstimate(outBids);

    // 4.处理基于签收开票的退回的出库单
    this.processReturnOutForManualUnEstimate(outBids);

    // 出库单取消暂估应收
    new CancelManualETFor4CPubBP().unSquare(vos);
  }

  private void addManualETBeforeRule(AroundProcesser<SquareOutViewVO> processer) {
    IRule<SquareOutViewVO> rule = new CheckForCancelET4CRule();
    processer.addBeforeRule(rule);
  }

  /**
   * 手工暂估应收的时候处理基于签收开票的退回的出库单，传回冲应收
   * 
   * @param outBids -- 来源出库单表体id
   * @param svos -- 退回的出库上游销售出库单待结算单
   *          <p>
   * @author zhangcheng
   * @time 2010-9-20 下午04:27:28
   */
  private void processReturnOutForManualEstimate(String[] outBids,
      SquareOutVO[] svos) {
    // 查询下游基于签收开票的退回的出库单
    String[] retBackOutBids = null;

    try {
      retBackOutBids = ICM4CServiceUtil.queryRetBidsBySource(outBids);

      // 没有签收开票退回的出库结算单
      if (VOChecker.isEmpty(retBackOutBids)) {
        return;
      }

      // 退回的出库单表体id加锁
      LockOperator lp = new LockOperator();
      lp.lock(
          retBackOutBids,
          NCLangResOnserver.getInstance().getStrByID("4006010_0",
              "04006010-0086")/*手工暂估应收的时候处理签收开票的退回的出库单发生并发操作*/);

      // 查询还没有传回冲应收的出库待结算单
      SquareOutViewVO[] soutvos =
          new Square434CQueryImpl()
              .querySquareOutViewVOByBIDForNoETRushSquare(retBackOutBids);
      if (VOChecker.isEmpty(soutvos)) {
        return;
      }

      SquareOutViewVO[] swvos =
          SquareOutVOUtils.getInstance().filterSignReturnOut(soutvos);
      // 没有签收开票的退回的出库单
      if (VOChecker.isEmpty(swvos)) {
        return;
      }

      // 设置本次结算数量、上游销售出库单结算明细id
      this.setDataForARRush(swvos, svos);

      // 基于签收开票的退回的出库单传回冲应收
      SquareOutVO[] sqvos = SquareOutVOUtils.getInstance().combineBill(swvos);
      new SquareARRushIncomeFor4CSBP().square(sqvos);
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }
  }

  /**
   * 手工取消暂估应收的时取消退回的出库单暂估应收
   * 
   * @param outBids -- 上游出库单Bids
   */
  private void processReturnOutForManualUnEstimate(String[] outBids) {
    // 查询下游基于签收开票的退回的出库单
    String[] retBackOutBids = null;

    try {
      retBackOutBids = ICM4CServiceUtil.queryRetBidsBySource(outBids);

      // 没有签收开票退回的出库结算单
      if (VOChecker.isEmpty(retBackOutBids)) {
        return;
      }

      // 途损单表体id加锁
      LockOperator lp = new LockOperator();
      lp.lock(
          retBackOutBids,
          NCLangResOnserver.getInstance().getStrByID("4006010_0",
              "04006010-0087")/*手工取消暂估应收的时候处理签收开票的退回的出库单发生并发操作*/);

      // 查询做过发出商品的出库结算单、出库待结算单
      SquareOutDetailVO[] sdvos =
          new Square434CQueryImpl()
              .queryETIncomeSquareOutDetailVOBy4CBID(retBackOutBids);

      // 没有做过暂估应收
      if (VOChecker.isEmpty(sdvos)) {
        return;
      }
      SquareOutViewVO[] svvos =
          new QuerySquare4CVOBP().querySquareOutViewVOByBID(SoVoTools
              .getVOsOnlyValues(sdvos, SquareOutDetailVO.CSALESQUAREBID));

      // 取消结算明细
      new CancelSquareFor4CPubBP().cancelSquare(sdvos, SquareOutVOUtils
          .getInstance().combineBill(svvos));

    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }
  }

  /**
   * 手工暂估应收的时候处理途损，传回冲应收
   * 
   * @param outBids -- 来源出库单表体id
   * @param svos -- 途损单上游销售出库单待结算单
   *          <p>
   * @author zhangcheng
   * @time 2010-9-20 下午04:27:28
   */
  private void processWastageForManualEstimate(String[] outBids,
      SquareOutVO[] svos) {
    // 查询下游的途损单
    String[] wasBids;
    try {
      wasBids = ICM4453ServiceUtil.queryWastageBidsBySource(outBids);

      // 没有途损单结算单
      if (VOChecker.isEmpty(wasBids)) {
        return;
      }

      // 途损单表体id加锁
      LockOperator lp = new LockOperator();
      lp.lock(
          wasBids,
          NCLangResOnserver.getInstance().getStrByID("4006010_0",
              "04006010-0088")/*手工暂估应收的时候处理途损发生并发操作*/);

      // 查询还没有回冲应收结算过的途损待结算单
      ISquare4353Query qry53 =
          NCLocator.getInstance().lookup(ISquare4353Query.class);
      SquareWasViewVO[] swvos =
          qry53.querySquareWasViewVOByBIDForNoETRushSquare(wasBids);

      if (VOChecker.isEmpty(swvos)) {
        return;
      }

      // 设置本次结算数量、上游销售出库单结算明细id
      this.setDataForARRush(swvos, svos);

      // 途损单传回冲应收
      new SquareARRushIncomeFor4453BP().square(swvos);

    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }

  }

  /**
   * 手工取消暂估应收的时取消途损暂估应收
   * 
   * @param outBids -- 上游出库单Bids
   */
  private void processWastageForManualUnEstimate(String[] outBids) {

    // 查询下游途损单
    String[] wasBids = null;

    try {
      wasBids = ICM4453ServiceUtil.queryWastageBidsBySource(outBids);

      // 没有途损单结算单
      if (VOChecker.isEmpty(wasBids)) {
        return;
      }

      // 途损单表体id加锁
      LockOperator lp = new LockOperator();
      lp.lock(
          wasBids,
          NCLangResOnserver.getInstance().getStrByID("4006010_0",
              "04006010-0089")/*手工取消暂估应收的时候处理途损单发生并发操作*/);

      // 查询做过暂估应收的途损结算单、途损待结算单
      ISquare4353Query iwasSvr =
          NCLocator.getInstance().lookup(ISquare4353Query.class);
      SquareWasDetailVO[] swdvos =
          iwasSvr.querySquareWasDetailVOByBIDForETRushSquare(wasBids);
      // 没有做过暂估应收
      if (VOChecker.isEmpty(swdvos)) {
        return;
      }
      SquareWasViewVO[] swbvos =
          iwasSvr.querySquareWasViewVOByBID(SoVoTools.getVOsOnlyValues(swdvos,
              SquareWasDetailVO.CSALESQUAREBID));

      // 途损结算单取消结算
      new CancelSquareWasDetailPubFor4453BP().cancelSquare(swdvos,
          SquareWasVOUtils.getInstance().combineBill(swbvos));

    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }

  }

  /**
   * 设置本次结算数量、上游销售出库单结算单id
   * （签收开票的退回的出库单回冲接口使用）
   * 
   * @param wasvos
   * @param svos
   */
  private void setDataForARRush(SquareOutViewVO[] outvos, SquareOutVO[] svos) {
    Map<String, SquareOutBVO> micbid = new HashMap<String, SquareOutBVO>();
    for (SquareOutVO svo : svos) {
      for (SquareOutBVO bvo : svo.getChildrenVO()) {
        micbid.put(bvo.getCsquarebillbid(), bvo);
      }
    }

    // 设置本次结算数量、上游暂估销售出库单结算明细id（途损回冲接口使用）
    for (SquareOutViewVO view : outvos) {
      String icbid = view.getItem().getCsrcbid();
      view.getItem().setProcesseid(micbid.get(icbid).getCsalesquaredid());
      SquareOutBVO bvo = micbid.get(icbid);
      UFDouble netnum = bvo.getNsquareestnum();
      SquareOutVOUtils.getInstance().setNthisETRushNumUseMinNum(view, netnum);

    }
  }

  /**
   * 设置本次结算数量、上游销售出库单结算单id（途损回冲接口使用）
   * 
   * @param wasvos
   * @param svos
   */
  private void setDataForARRush(SquareWasViewVO[] wasvos, SquareOutVO[] svos) {
    Map<String, SquareOutBVO> micbid = new HashMap<String, SquareOutBVO>();
    for (SquareOutVO svo : svos) {
      for (SquareOutBVO bvo : svo.getChildrenVO()) {
        micbid.put(bvo.getCsquarebillbid(), bvo);
      }
    }

    // 设置本次结算数量、上游暂估销售出库单结算明细id（途损回冲接口使用）
    for (SquareWasViewVO view : wasvos) {
      String icbid = view.getItem().getCsrcbid();
      view.getItem().setProcesseid(micbid.get(icbid).getCsalesquaredid());
      SquareOutBVO bvo = micbid.get(icbid);
      UFDouble netnum = bvo.getNsquareestnum();
      SquareWasVOUtils.getInstance().setNthisETRushNumUseMinNum(view, netnum);
    }
  }

}
