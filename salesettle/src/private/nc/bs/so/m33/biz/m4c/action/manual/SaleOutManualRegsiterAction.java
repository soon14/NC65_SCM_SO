package nc.bs.so.m33.biz.m4c.action.manual;

import java.util.HashMap;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.bs.ml.NCLangResOnserver;
import nc.bs.so.m33.biz.m4453.bp.cancelsquare.CancelSquareWasDetailPubFor4453BP;
import nc.bs.so.m33.biz.m4453.bp.square.ia.SquareIARegisterFor4453BP;
import nc.bs.so.m33.biz.m4c.action.ia.IARegisterFor4CAction;
import nc.bs.so.m33.biz.m4c.bp.cancelsquare.CancelSquareFor4CPubBP;
import nc.bs.so.m33.biz.m4c.bp.cancelsquare.manual.CancelManualREGFor4CPubBP;
import nc.bs.so.m33.biz.m4c.rule.toia.CheckForCancelREG4CRule;
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

public class SaleOutManualRegsiterAction {

  public void manualRegister(SquareOutViewVO[] vos) {

    SquareOutVO[] svos = SquareOutVOUtils.getInstance().combineBill(vos);

    // 1.补充数据
    SquareOutVOUtils.getInstance().fillDataForManualSquare(vos);

    // 2.发出商品
    new IARegisterFor4CAction().execCost(svos);

    // 销售出库单表体id
    String[] outBids =
        AggVOUtil.getDistinctItemFieldArray(svos, SquareOutBVO.CSQUAREBILLBID,
            String.class);

    // 3.处理途损
    this.processWastageForManualRegister(outBids, svos);

    // 4.处理基于签收开票的退回的出库单
    this.processReturnOutForManualRegister(outBids, svos);
  }

  public void manualUnRegister(SquareOutViewVO[] vos) {
    // 增加执行前业务规则
    AroundProcesser<SquareOutViewVO> processer =
        new AroundProcesser<SquareOutViewVO>(BPPlugInPoint.ManualREG);
    this.addManualREGBeforeRule(processer);
    processer.before(vos);

    // 待结算单
    SquareOutVO[] sqvos = SquareOutVOUtils.getInstance().combineBill(vos);

    // 销售出库单表体id
    String[] outBids =
        AggVOUtil.getDistinctItemFieldArray(sqvos, SquareOutBVO.CSQUAREBILLBID,
            String.class);

    // 3.处理途损
    this.processWastageForManualUnRegister(outBids);

    // 4.处理基于签收开票的退回的出库单
    this.processReturnOutForManualUnRegister(outBids);

    // 出库单取消发出商品
    new CancelManualREGFor4CPubBP().unSquare(vos);
  }

  private void addManualREGBeforeRule(AroundProcesser<SquareOutViewVO> processer) {
    IRule<SquareOutViewVO> rule = new CheckForCancelREG4CRule();
    processer.addBeforeRule(rule);
  }

  /**
   * 手工发出商品的时候处理基于签收开票的退回的出库单，传发出商品
   * 
   * @param outBids -- 来源出库单表体id
   *          <p>
   * @author zhangcheng
   * @time 2010-9-20 下午04:27:28
   */
  private void processReturnOutForManualRegister(String[] outBids,
      SquareOutVO[] svos) {
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
              "04006010-0090")/*手工发出商品的时候处理签收开票的退回的出库单发生并发操作*/);

      // 查询还没有结算过发出商品结算的出库待结算单
      SquareOutViewVO[] soutvos =
          new Square434CQueryImpl()
              .querySquareOutViewVOByBIDForNoREGSquare(retBackOutBids);
      if (VOChecker.isEmpty(soutvos)) {
        return;
      }

      SquareOutViewVO[] swvos =
          SquareOutVOUtils.getInstance().filterSignReturnOut(soutvos);
      // 没有签收开票的退回的出库单
      if (VOChecker.isEmpty(swvos)) {
        return;
      }

      // 重新读取销售出库待结算单
      SquareOutVO[] newsvos = new QuerySquare4CVOBP().refushVO(svos);

      // 设置本次结算数量
      this.setNthisREGNumUseMinNum(swvos, newsvos);

      // 基于签收开票的退回的出库单发出商品
      SquareOutVO[] sqvos = SquareOutVOUtils.getInstance().combineBill(swvos);
      new IARegisterFor4CAction().execCost(sqvos);
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }
  }

  /**
   * 手工取消发出商品的时取消退回的出库单发出商品
   * 
   * @param outBids -- 上游出库单Bids
   */
  private void processReturnOutForManualUnRegister(String[] outBids) {
    // 查询下游基于签收开票的退回的出库单
    String[] retBackOutBids = null;

    try {
      retBackOutBids = ICM4CServiceUtil.queryRetBidsBySource(outBids);

      // 没有签收开票退回的出库结算单
      if (VOChecker.isEmpty(retBackOutBids)) {
        return;
      }

      // 退回出库单表体id加锁
      LockOperator lp = new LockOperator();
      lp.lock(
          retBackOutBids,
          NCLangResOnserver.getInstance().getStrByID("4006010_0",
              "04006010-0091")/*手工取消发出商品的时候处理签收开票的退回的出库单发生并发操作*/);

      // 查询做过发出商品的出库结算单、出库待结算单
      SquareOutDetailVO[] sdvos =
          new Square434CQueryImpl()
              .queryREGCostSquareOutDetailVOBy4CBID(retBackOutBids);
      // 没有做过发出商品
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
   * 手工发出商品的时候处理途损，传发出商品
   * <p>
   * 
   * @param outBids -- 来源出库单表体id
   *          <p>
   * @author zhangcheng
   * @time 2010-9-20 下午04:27:28
   */
  private void processWastageForManualRegister(String[] outBids,
      SquareOutVO[] svos) {
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
              "04006010-0092")/*手工发出商品的时候处理途损发生并发操作*/);

      // 查询还没有结算过的途损待结算单
      ISquare4353Query qry53 =
          NCLocator.getInstance().lookup(ISquare4353Query.class);
      SquareWasViewVO[] swvos =
          qry53.querySquareWasViewVOByBIDForNoREGSquare(wasBids);

      // 没有途损待结算单
      if (VOChecker.isEmpty(swvos)) {
        return;
      }

      // 重新读取销售出库待结算单
      SquareOutVO[] newsvos = new QuerySquare4CVOBP().refushVO(svos);

      // 设置本次结算数量
      this.setNthisREGNumUseMinNum(swvos, newsvos);

      // 途损单发出商品
      new SquareIARegisterFor4453BP().square(swvos);

    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }
  }

  /**
   * 手工取消发出商品的时取消途损发出商品
   * 
   * @param outBids -- 上游出库单Bids
   */
  private void processWastageForManualUnRegister(String[] outBids) {
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
              "04006010-0093")/*手工取消发出商品的时候处理途损单发生并发操作*/);

      // 查询做过发出商品结算的途损结算单、途损待结算单
      ISquare4353Query iwasSvr =
          NCLocator.getInstance().lookup(ISquare4353Query.class);
      SquareWasDetailVO[] swdvos =
          iwasSvr.querySquareWasDetailVOByBIDForREGSquare(wasBids);
      // 没有做过发出商品
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

  private void setNthisREGNumUseMinNum(SquareOutViewVO[] swvos,
      SquareOutVO[] svos) {
    Map<String, SquareOutBVO> map = new HashMap<String, SquareOutBVO>();
    for (SquareOutVO svo : svos) {
      for (SquareOutBVO bvo : svo.getChildrenVO()) {
        map.put(bvo.getCsquarebillbid(), bvo);
      }
    }
    for (SquareOutViewVO view : swvos) {
      SquareOutBVO bvo = map.get(view.getItem().getCsrcbid());
      UFDouble nregnum = bvo.getNsquareregnum();
      SquareOutVOUtils.getInstance().setNthisREGNumUseMinNum(view, nregnum);
    }
  }

  private void setNthisREGNumUseMinNum(SquareWasViewVO[] swvos,
      SquareOutVO[] svos) {
    Map<String, SquareOutBVO> map = new HashMap<String, SquareOutBVO>();
    for (SquareOutVO svo : svos) {
      for (SquareOutBVO bvo : svo.getChildrenVO()) {
        map.put(bvo.getCsquarebillbid(), bvo);
      }
    }
    for (SquareWasViewVO view : swvos) {
      SquareOutBVO bvo = map.get(view.getItem().getCsrcbid());
      UFDouble nregnum = bvo.getNsquareregnum();
      SquareWasVOUtils.getInstance().setNthisREGNumUseMinNum(view, nregnum);
    }
  }

}
