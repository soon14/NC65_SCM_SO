package nc.pubimpl.so.m33.so.m30;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.bs.so.m33.biz.m4c.bp.cancelsquare.cancelar.CancelARRushIncomeFor4CBP;
import nc.bs.so.m33.biz.m4c.bp.cancelsquare.cancelia.CancelIARegisterCreditFor4COrderEndBP;
import nc.bs.so.m33.biz.m4c.bp.square.ar.SquareARRushIncomeFor4CBP;
import nc.bs.so.m33.biz.m4c.bp.square.ia.SquareIARegisterCreditFor4COrderEndBP;
import nc.bs.so.m33.maintain.m4c.query.QuerySquare4CVOBP;
import nc.bs.so.m33.maintain.m4c.query.QuerySquare4CVOBizBP;
import nc.impl.pubapp.env.BSContext;
import nc.itf.scmpub.reference.arap.ArapServicesForSOUtil;
import nc.itf.so.m33.ref.ia.mi5.IAI5For4CServicesUtil;
import nc.pubimpl.so.m33.self.pub.Square434CQueryImpl;
import nc.pubitf.so.m33.so.m30.IProcess4CAdjustFor30BalEnd;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m33.m4c.entity.SquareOutBVO;
import nc.vo.so.m33.m4c.entity.SquareOutDetailVO;
import nc.vo.so.m33.m4c.entity.SquareOutVO;
import nc.vo.so.m33.m4c.entity.SquareOutVOUtils;
import nc.vo.so.m33.m4c.entity.SquareOutViewVO;
import nc.vo.so.m33.pub.util.SquareCalculatorForVO;
import nc.vo.so.pub.votools.SoVoTools;
import nc.vo.trade.checkrule.VOChecker;

public class Process4CAdjustFor30BalEndImpl implements
    IProcess4CAdjustFor30BalEnd {

  @Override
  public void process4CAdjust(String[] ordBids) {
    // 查询对应的销售出库结算单
    SquareOutViewVO[] sovvos =
        new Square4CQryFor30SqEndImpl()
            .queryETViewVOByOrdBIDForOrderEnd(ordBids);
    if (VOChecker.isEmpty(sovvos)) {
      return;
    }

    // 过滤暂估未回冲完全的销售出库待结算单，同时设置应回冲数量
    sovvos = this.filterET(sovvos);

    if (!VOChecker.isEmpty(sovvos)) {
      SquareOutVO[] sqvos = SquareOutVOUtils.getInstance().combineBill(sovvos);
      // 传回冲应收处理
      new SquareARRushIncomeFor4CBP().square(sqvos);
    }
  }

  @Override
  public void process4CReg(String[] ordBids) {
    // 查询对应的销售出库结算单
    SquareOutViewVO[] sovvos =
        new Square4CQryFor30SqEndImpl()
            .queryREGViewVOByOrdBIDForOrderEnd(ordBids);
    if (VOChecker.isEmpty(sovvos)) {
      return;
    }

    // 过滤发出商品借方未完全贷方回冲的销售出库待结算单，同时设置应传发出商品贷方数量
    sovvos = this.filterREG(sovvos);

    if (!VOChecker.isEmpty(sovvos)) {
      SquareOutVO[] sqvos = SquareOutVOUtils.getInstance().combineBill(sovvos);
      // 传贷方发出商品
      new SquareIARegisterCreditFor4COrderEndBP().square(sqvos);
    }

  }

  @Override
  public void unProcess4CAdjust(String[] ordBids) {
    QuerySquare4CVOBP qry = new QuerySquare4CVOBP();
    String[] sqbids = qry.querySquareOutVOPKsBy30BID(ordBids);
    if (sqbids == null || sqbids.length == 0) {
      return;
    }
    SquareOutViewVO[] soviewvos = qry.querySquareOutViewVOByBID(sqbids);
    SquareOutVO[] soutvos =
        SquareOutVOUtils.getInstance().combineBill(soviewvos);
    SquareOutDetailVO[] rushVOs =
        new Square434CQueryImpl().queryRushIncomeSquareOutDetailVOByBID(sqbids);
    if (rushVOs != null && rushVOs.length > 0) {
      // 设置本次取消结算数量
      SquareOutVO[] nsqvos =
          SquareOutVOUtils.getInstance().setNthisnumForCancelSquare(rushVOs,
              soutvos);
      // 将销售出库待结算单缓存
      BSContext.getInstance().setSession(SquareOutVO.class.getName(), nsqvos);
      // 取消回冲
      new CancelARRushIncomeFor4CBP().cancelSquare(rushVOs);
      // 释放缓存
      BSContext.getInstance().removeSession(SquareOutVO.class.getName());
    }
  }

  @Override
  public void unProcess4CReg(String[] ordBids) {
    QuerySquare4CVOBP qry = new QuerySquare4CVOBP();
    String[] sqbids = qry.querySquareOutVOPKsBy30BID(ordBids);
    // 流程中无出库
    if (VOChecker.isEmpty(sqbids)) {
      return;
    }
    SquareOutViewVO[] soviewvos = qry.querySquareOutViewVOByBID(sqbids);
    SquareOutVO[] soutvos =
        SquareOutVOUtils.getInstance().combineBill(soviewvos);
    SquareOutDetailVO[] regVOs =
        new Square434CQueryImpl().queryREGCreditSquareOutDetailVOByBID(sqbids);
    if (regVOs != null && regVOs.length > 0) {
      // 设置本次取消结算数量
      SquareOutVO[] nsqvos =
          SquareOutVOUtils.getInstance().setNthisnumForCancelSquare(regVOs,
              soutvos);
      // 将销售出库待结算单缓存
      BSContext.getInstance().setSession(SquareOutVO.class.getName(), nsqvos);
      // 取消回冲
      new CancelIARegisterCreditFor4COrderEndBP().cancelSquare(regVOs);
      // 释放缓存
      BSContext.getInstance().removeSession(SquareOutVO.class.getName());
    }
  }

  /**
   * 过滤暂估未回冲完全的销售出库待结算单，同时设置应回冲数量
   * 
   * @param sovvos -- 暂估未回冲完全的销售出库待结算单
   * @return
   */
  private SquareOutViewVO[] filterET(SquareOutViewVO[] sovvos) {
    String[] bids =
        SoVoTools.getVOsOnlyValues(sovvos, SquareOutBVO.CSALESQUAREBID);
    // 暂估的销售出库结算单
    SquareOutDetailVO[] dvos =
        new QuerySquare4CVOBizBP().queryETSquareOutDetailVOBySQBID(bids);
    String[] sqdids =
        SoVoTools.getVOsOnlyValues(dvos, SquareOutDetailVO.CSALESQUAREDID);
    // <did,回冲数量>
    Map<String, UFDouble> mdidarrushnum =
        ArapServicesForSOUtil.querySaleOutETNotAllSquare(sqdids);
    if (null == mdidarrushnum || mdidarrushnum.size() == 0) {
      return null;
    }
    // <bid,回冲数量>
    Map<String, UFDouble> mbidarrushnum = new HashMap<String, UFDouble>();
    // <bid,did>
    Map<String, String> mbiddid = new HashMap<String, String>();
    for (SquareOutDetailVO dvo : dvos) {
      String did = dvo.getCsalesquaredid();
      mbidarrushnum.put(dvo.getCsalesquarebid(), mdidarrushnum.get(did));
      mbiddid.put(dvo.getCsalesquarebid(), dvo.getCsalesquaredid());
    }

    // 设置回冲数量
    List<SquareOutViewVO> list = new ArrayList<SquareOutViewVO>();
    for (SquareOutViewVO view : sovvos) {
      String bid = view.getItem().getCsalesquarebid();
      UFDouble arrushnum = mbidarrushnum.get(bid);
      // 已经完全回冲
      if (MathTool.isZero(arrushnum)) {
        continue;
      }
      // 存储暂估销售出库结算单id，为后续回冲使用
      view.getItem().setProcesseid(mbiddid.get(bid));
      // 应收应付要求数量符号和暂估数量一样,而销售结算存储符号与暂估数量相反,所以取反
      view.getItem().setNthisnum(MathTool.oppose(arrushnum));
      list.add(view);
    }

    SquareOutViewVO[] retvos = null;
    if (list.size() > 0) {
      retvos = list.toArray(new SquareOutViewVO[list.size()]);
    }
    return retvos;
  }

  /**
   * 过滤发出商品未完全传贷方发出商品的销售出库待结算单，同时设置传贷方发出商品数量
   * 
   * @param sovvos -- 发出商品未完全传贷方发出商品的销售出库待结算单
   * @return
   */
  private SquareOutViewVO[] filterREG(SquareOutViewVO[] sovvos) {
    String[] bids =
        SoVoTools.getVOsOnlyValues(sovvos, SquareOutBVO.CSALESQUAREBID);
    // 发出商品的销售出库结算单
    SquareOutDetailVO[] dvos =
        new QuerySquare4CVOBizBP().queryREGDebitSquareOutDetailVOBySQBID(bids);
    String[] sqdids =
        SoVoTools.getVOsOnlyValues(dvos, SquareOutDetailVO.CSALESQUAREDID);
    String[] outhids =
        SoVoTools.getVOsOnlyValues(sovvos, SquareOutBVO.CSQUAREBILLID);
    // <did,贷方发出商品>
    Map<String, UFDouble> mdidregnum =
        IAI5For4CServicesUtil.querySaleOutRegNotAllSquare(outhids, sqdids);
    if (null == mdidregnum || mdidregnum.size() == 0) {
      return null;
    }
    // <bid,贷方发出商品>
    Map<String, UFDouble> mbidregnum = new HashMap<String, UFDouble>();
    for (SquareOutDetailVO dvo : dvos) {
      String did = dvo.getCsalesquaredid();
      mbidregnum.put(dvo.getCsalesquarebid(), mdidregnum.get(did));
    }

    // 设置贷方发出商品数量
    List<SquareOutViewVO> list = new ArrayList<SquareOutViewVO>();
    for (SquareOutViewVO view : sovvos) {
      String bid = view.getItem().getCsalesquarebid();
      UFDouble regnum = mbidregnum.get(bid);
      // 已经完全传贷方发出商品
      if (MathTool.isZero(regnum)) {
        continue;
      }
      view.getItem().setNthisnum(regnum);
      list.add(view);
    }

    SquareOutViewVO[] retvos = null;
    if (list.size() > 0) {
      retvos = list.toArray(new SquareOutViewVO[list.size()]);
      SquareOutBVO[] bvos =
          SquareOutVOUtils.getInstance().getSquareOutBVO(retvos);
      new SquareCalculatorForVO().calculate(bvos, SquareOutBVO.NTHISNUM);
    }
    return retvos;
  }

}
