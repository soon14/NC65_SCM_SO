package nc.bs.so.m33.biz.m4453.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.bs.so.m33.biz.m4453.bp.square.ar.SquareARIncomeFor4453BP;
import nc.bs.so.m33.biz.m4453.bp.square.ar.SquareARRushIncomeFor4453BP;
import nc.bs.so.m33.biz.m4453.bp.square.ia.SquareIACostFor4453BP;
import nc.bs.so.m33.biz.m4453.bp.square.ia.SquareIARegisterFor4453BP;
import nc.bs.so.m33.maintain.m4453.InsertSquare4453BP;
import nc.bs.so.m33.plugin.ServicePlugInPoint;
import nc.impl.pubapp.pattern.data.bill.tool.BillConcurrentTool;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.itf.scmpub.reference.uap.pf.PfServiceScmUtil;
import nc.itf.so.m33.ref.so.m30.SOSaleOrderServicesUtil;
import nc.md.model.impl.MDEnum;
import nc.pubitf.so.m30.balend.BalEndPara;
import nc.pubitf.so.m33.self.pub.ISquare434CQuery;
import nc.vo.ic.m4453.entity.WastageVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.scmpub.res.billtype.ICBillType;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m30.balend.enumeration.BalEndTrigger;
import nc.vo.so.m33.enumeration.SquareType;
import nc.vo.so.m33.m4453.entity.SquareWasBVO;
import nc.vo.so.m33.m4453.entity.SquareWasVO;
import nc.vo.so.m33.m4453.entity.SquareWasVOUtils;
import nc.vo.so.m33.m4453.entity.SquareWasViewVO;
import nc.vo.so.m33.m4c.entity.SquareOutDetailVO;
import nc.vo.so.pub.util.AggVOUtil;
import nc.vo.trade.checkrule.VOChecker;

public class SquareFor4453Action {

  public void soSquare(WastageVO[] wasvos) {
    // 1.推式生成途损待结算单
    SquareWasVO[] swvos = this.pushSquareWasVO(wasvos);

    // 2.根据途损单查询上游销售出库结算单的已结算数据
    SquareOutDetailVO[] soutdvos = this.getSquareOutDetail(swvos);

    // 3.根据上游销售出库结算单动作设置途损待结算单的动作
    SquareWasViewVO[] swvvos =
        SquareWasVOUtils.getInstance().changeToSaleSquareViewVO(swvos);
    Map<SquareType, List<SquareWasViewVO>> msvo =
        this.setSquareFlagFrom334C(soutdvos, swvvos);

    // 4.途损待结算单根据结算标志结算
    this.execSquare(msvo);

    // 5.途损单审批影响订单结算关闭
    this.processOrderSquareClose(swvos);
  }

  /**
   * 方法功能描述：途损待结算单根据结算标志结算
   * 
   * @param swvvos
   *          <p>
   * @author zhangcheng
   * @time 2010-8-30 上午10:51:40
   */
  private void execSquare(Map<SquareType, List<SquareWasViewVO>> msvo) {
    if (SysInitGroupQuery.isAREnabled()) {
      // 传确认应收
      new SquareARIncomeFor4453BP().square(msvo.get(SquareType.SQUARETYPE_AR));

      // 传回冲应收
      new SquareARRushIncomeFor4453BP().square(msvo
          .get(SquareType.SQUARETYPE_ARRUSH));
    }

    if (SysInitGroupQuery.isIAEnabled()) {
      // 传成本
      new SquareIACostFor4453BP().square(msvo.get(SquareType.SQUARETYPE_IA));

      // 传发出商品
      new SquareIARegisterFor4453BP().square(msvo
          .get(SquareType.SQUARETYPE_REG_DEBIT));
    }

  }

  /**
   * 根据途损单查询上游销售出库结算单的已结算数据
   * <p>
   * <b>参数说明</b>
   * 
   * @param svos
   *          <p>
   * @author zhangcheng
   * @time 2010-8-30 上午09:05:01
   */
  private SquareOutDetailVO[] getSquareOutDetail(SquareWasVO[] svos) {
    // 上游出库单行id
    String[] outBids =
        AggVOUtil.getDistinctItemFieldArray(svos, SquareWasBVO.CSRCBID,
            String.class);

    // 销售出库结算查询接口,查询销售出库结算单
    ISquare434CQuery square4cQry =
        NCLocator.getInstance().lookup(ISquare434CQuery.class);
    SquareOutDetailVO[] sodvos =
        square4cQry.querySquareOutDetailVOBy4CBID(outBids);

    return sodvos;
  }

  /**
   * 途损单审批影响订单结算关闭
   * 
   * @param swvos
   */
  private void processOrderSquareClose(SquareWasVO[] swvos) {
    // 源头销售订单表体id
    String[] orderbids =
        AggVOUtil.getDistinctItemFieldArray(swvos, SquareWasBVO.CFIRSTBID,
            String.class);
    BalEndTrigger trigger = BalEndTrigger.WAST_AUDIT;
    BalEndPara para = new BalEndPara(orderbids, trigger);
    try {
      SOSaleOrderServicesUtil.processAutoBalEnd(para);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

  /**
   * 方法功能描述：推式生成途损待结算单
   * <p>
   * <b>参数说明</b>
   * 
   * @param wasvos
   *          <p>
   * @author zhangcheng
   * @time 2010-8-30 上午09:05:01
   */
  private SquareWasVO[] pushSquareWasVO(WastageVO[] wasvos) {
    // 上游途损单加锁
    BillConcurrentTool tool = new BillConcurrentTool();
    tool.lockBill(wasvos);

    // 进行4453到3353的VO对照
    SquareWasVO[] sqwvos =
        PfServiceScmUtil.executeVOChange(ICBillType.WastageBill.getCode(),
            SOBillType.SquareWas.getCode(), wasvos);

    AroundProcesser<SquareWasVO> processer =
        new AroundProcesser<SquareWasVO>(ServicePlugInPoint.Push33For4453);
    processer.before(sqwvos);
    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4006010_0", "04006010-0017")/*@res "调用结算单保存BP前规则"*/);

    TimeLog.logStart();
    SquareWasVO[] vos = new InsertSquare4453BP().insert(sqwvos);
    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4006010_0", "04006010-0018")/*@res "调用结算单新增保存BP"*/);

    TimeLog.logStart();
    processer.after(vos);
    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4006010_0", "04006010-0019")/*@res "调用结算单保存BP后规则"*/);

    return vos;
  }

  private void setARData(SquareWasViewVO svvo, SquareOutDetailVO tempVo,
      Map<SquareType, List<SquareWasViewVO>> map) {
    // 收入结算
    Integer artype = svvo.getItem().getFpreartype();
    if (!VOChecker.isEmpty(artype)) {
      // 途损单传回冲应收必须上游出库单做过暂估应收
      if (SquareType.SQUARETYPE_ARRUSH.getIntValue() == artype.intValue()) {
        if (VOChecker.isEmpty(tempVo)
            || SquareType.SQUARETYPE_ET.getIntValue() != tempVo
                .getFsquaretype().intValue()) {
          return;
        }
        // 用processeid暂时存储出库单结算单id(后面传回冲应收应收用)
        svvo.getItem().setProcesseid(tempVo.getCsalesquaredid());

        // 回冲数量是途损可回冲数量和出库单暂估数量的最小值
        UFDouble netnum = tempVo.getNnum();
        SquareWasVOUtils.getInstance().setNthisETRushNumUseMinNum(svvo, netnum);

        // 根据结算类型分类存储
        this.setMapList(artype, map, svvo);
      }
    }
  }

  private void setIAData(SquareWasViewVO iasvvo, SquareOutDetailVO tempVo,
      Map<SquareType, List<SquareWasViewVO>> map) {
    // 成本结算
    Integer iatype = iasvvo.getItem().getFpreiatype();
    if (!VOChecker.isEmpty(iatype)) {
      // 途损单传反相发出商品必须上游出库单做过发出商品
      if (SquareType.SQUARETYPE_REG_DEBIT.getIntValue() == iatype.intValue()) {
        if (VOChecker.isEmpty(tempVo)
            || SquareType.SQUARETYPE_REG_DEBIT.getIntValue() != tempVo
                .getFsquaretype().intValue()) {
          return;
        }

        // 发出商品数量是途损可用数量和出库单发出商品数量的最小值
        UFDouble nregnum = tempVo.getNnum();
        SquareWasVOUtils.getInstance().setNthisREGNumUseMinNum(iasvvo, nregnum);

        // 根据结算类型分类存储
        this.setMapList(iatype, map, iasvvo);
      }
    }
  }

  private void setMapList(Integer iKey,
      Map<SquareType, List<SquareWasViewVO>> map, SquareWasViewVO svvo) {
    SquareType key = MDEnum.valueOf(SquareType.class, iKey);
    List<SquareWasViewVO> list = map.get(key);
    if (list == null) {
      list = new ArrayList<SquareWasViewVO>();
      map.put(key, list);
    }
    list.add(svvo);
  }

  /**
   * 方法功能描述：根据上游销售出库结算单动作设置途损待结算单的动作
   * 1.如果途损单传应收或成本，直接传，不再看上游出库单
   * 2.如果途损单传回冲和发出商品，需要看上游出库单是否传过暂估或者发出商品
   * 
   * @param soutdvos
   * @param swvos
   *          <p>
   * @author zhangcheng
   * @time 2010-8-30 上午10:51:37
   */
  private Map<SquareType, List<SquareWasViewVO>> setSquareFlagFrom334C(
      SquareOutDetailVO[] soutdvos, SquareWasViewVO[] swvos) {
    // <出库单行id,List<SquareOutDetailVO>>，只记录暂估和发出商品结算类型的
    Map<String, List<SquareOutDetailVO>> mOutBidSvo =
        new HashMap<String, List<SquareOutDetailVO>>();
    if (!VOChecker.isEmpty(soutdvos)) {
      for (SquareOutDetailVO sdvo : soutdvos) {
        String outBid = sdvo.getCsquarebillbid();
        int outsqtype = sdvo.getFsquaretype().intValue();
        if (SquareType.SQUARETYPE_ET.getIntValue() == outsqtype
            || SquareType.SQUARETYPE_REG_DEBIT.getIntValue() == outsqtype) {
          List<SquareOutDetailVO> list = mOutBidSvo.get(outBid);
          if (VOChecker.isEmpty(list)) {
            list = new ArrayList<SquareOutDetailVO>();
            mOutBidSvo.put(outBid, list);
          }
          list.add(sdvo);
        }
      }
    }

    // 准备途损待结算单数据
    Map<SquareType, List<SquareWasViewVO>> map =
        new HashMap<SquareType, List<SquareWasViewVO>>();
    for (SquareWasViewVO svvo : swvos) {
      List<SquareOutDetailVO> listsdvo =
          mOutBidSvo.get(svvo.getItem().getCsrcbid());
      // 说明上游出库单是收入成本结算
      if (SquareType.SQUARETYPE_AR.equalsValue(svvo.getItem().getFpreartype())
          || SquareType.SQUARETYPE_IA.equalsValue(svvo.getItem()
              .getFpreiatype())) {
        SquareWasViewVO iasvvo = (SquareWasViewVO) svvo.clone();
        if (SquareType.SQUARETYPE_AR
            .equalsValue(svvo.getItem().getFpreartype())) {
          // 设置应收结算数据
          this.setMapList(SquareType.SQUARETYPE_AR.getIntegerValue(), map, svvo);
        }
        if (SquareType.SQUARETYPE_IA
            .equalsValue(svvo.getItem().getFpreiatype())) {
          // 设置成本结算数据，需要克隆一个新vo
          this.setMapList(SquareType.SQUARETYPE_IA.getIntegerValue(), map,
              iasvvo);
        }
      }

      if (!VOChecker.isEmpty(listsdvo)) {
        for (SquareOutDetailVO tempVo : listsdvo) {
          SquareWasViewVO iasvvo = (SquareWasViewVO) svvo.clone();
          // 设置应收结算数据
          this.setARData(svvo, tempVo, map);
          // 设置成本结算数据，需要克隆一个新vo
          this.setIAData(iasvvo, tempVo, map);
        }
      }
    } // end for

    return map;
  }
}
