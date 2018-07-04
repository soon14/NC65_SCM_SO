package nc.bs.so.m33.biz.m4c.bp.pub;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.bs.so.m33.maintain.m4c.query.QuerySquare4CVOBP;
import nc.bs.so.m33.maintain.m4c.query.QuerySquare4CVOBizBP;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.m33.enumeration.SquareType;
import nc.vo.so.m33.m4c.entity.SquareOutBVO;
import nc.vo.so.m33.m4c.entity.SquareOutDetailVO;
import nc.vo.so.m33.m4c.entity.SquareOutVOUtils;
import nc.vo.so.m33.m4c.entity.SquareOutViewVO;
import nc.vo.so.m33.pub.util.SquareCalculatorForVO;
import nc.vo.so.pub.calculator.PriceNumMnyCalculatorForVO;
import nc.vo.so.pub.util.SOCurrencyUtil;
import nc.vo.so.pub.votools.SoVoTools;
import nc.vo.trade.checkrule.VOChecker;

/**
 * 销售出库待结算单
 * 根据当前结算数量重新计算数量单价金额
 * 并进行尾差处理
 * 
 * @since 6.0
 * @version 2011-6-20 下午04:30:27
 * @author zhangcheng
 */
public class ProcessWC {

  /**
   * 处理尾差
   */
  public void processMnyForWC(SquareOutViewVO[] retsvos) {
    SquareOutBVO[] bvos =
        SquareOutVOUtils.getInstance().getSquareOutBVO(retsvos);
    String[] bids =
        SoVoTools.getVOsOnlyValues(bvos, SquareOutBVO.CSALESQUAREBID);
    // 查询原始的销售出库待结算单<bid,SquareOutViewVO>
    Map<String, SquareOutViewVO> moldview = this.getOldSquareOutViewVO(bids);

    // 手工收入成本结算明细表vo
    SquareOutDetailVO[] sdvos =
        new QuerySquare4CVOBizBP().queryManualDetailVOBySQBIDandSQType(bids,
            new SquareType[] {
            SquareType.SQUARETYPE_AR, SquareType.SQUARETYPE_IA
        });

    // 获取所有的历史价格
    Map<String, UFDouble> mapsprice = new HashMap<String, UFDouble>();
    Set<String> setchgbid = new HashSet<String>();
    for (SquareOutDetailVO dvo : sdvos) {
      String squarebid = dvo.getCsalesquarebid();
      // 已经发生价格改变
      if (setchgbid.contains(squarebid)) {
        continue;
      }
      UFDouble taxnetprice = dvo.getNorigtaxnetprice();
      // 未包含此价格，需要放置到MAP中
      if (mapsprice.containsKey(squarebid)) {
        UFDouble oldtaxnetprice = mapsprice.get(squarebid);
        if (!MathTool.equals(oldtaxnetprice, taxnetprice)) {
          setchgbid.add(squarebid);
        }
      }
      else {
        mapsprice.put(squarebid, taxnetprice);
      }
    }
    // 已经结算过的销售出库结算单累计信息<bid,dvo(累计信息数量、金额)>
    Map<String, SquareOutDetailVO> msquaredvo =
        this.getManualSquareDetailVO(sdvos);

    // 尾差处理，价格变动不再处理
    for (SquareOutViewVO view : retsvos) {
      String squarebid = view.getItem().getCsalesquarebid();
      if (setchgbid.contains(squarebid)) {
        continue;
      }

      UFDouble newtaxnetprice = view.getItem().getNorigtaxnetprice();
      // 原始的销售出库待结算单
      SquareOutViewVO oview = moldview.get(view.getItem().getCsalesquarebid());
      // sprice只可能1条记录，多条记录说明多次改价，上面代码已经跳出
      UFDouble oldtaxnetprice = mapsprice.get(squarebid);
      // 价格相同，尾差处理,因为订单修订、签收、结算改价所以需要比较历史明细价格
      if (MathTool.equals(newtaxnetprice, oldtaxnetprice)) {
        // 已结算销售出库结算单（累计信息）
        SquareOutDetailVO sqedview =
            msquaredvo.get(view.getItem().getCsalesquarebid());
        // 原币信息
        UFDouble totaltaxmny =
            MathTool.sub(oview.getItem().getNorigtaxmny(),
                sqedview.getNorigtaxmny());
        view.getItem().setNorigtaxmny(totaltaxmny);
        UFDouble totalmny =
            MathTool.sub(oview.getItem().getNorigmny(), sqedview.getNorigmny());
        view.getItem().setNorigmny(totalmny);
        // TODO 2012.02.16 fbinly v61删除原币税额字段
        // UFDouble totaltax =
        // MathTool.sub(oview.getItem().getNorigtax(), sqedview.getNorigtax());
        // view.getItem().setNorigtax(totaltax);

        String corgcurcy = view.getItem().getCorigcurrencyid();
        String ccurcy = view.getItem().getCcurrencyid();
        // 如果原币本币币种不一致，则本币不挤尾差
        if (PubAppTool.isEqual(corgcurcy, ccurcy)) {
          // 本币信息
          UFDouble totaltaxmnylocal =
              MathTool.sub(oview.getItem().getNtaxmny(), sqedview.getNtaxmny());
          view.getItem().setNtaxmny(totaltaxmnylocal);
          UFDouble totalmnylocal =
              MathTool.sub(oview.getItem().getNmny(), sqedview.getNmny());
          view.getItem().setNmny(totalmnylocal);
          UFDouble totaltaxlocal =
              MathTool.sub(oview.getItem().getNtax(), sqedview.getNtax());
          view.getItem().setNtax(totaltaxlocal);
        }

        // 集团、全局信息
        UFDouble ngrouptaxmny =
            MathTool.sub(oview.getItem().getNgrouptaxmny(),
                sqedview.getNgrouptaxmny());
        view.getItem().setNgrouptaxmny(ngrouptaxmny);
        UFDouble ngroupmny =
            MathTool.sub(oview.getItem().getNgroupmny(),
                sqedview.getNgroupmny());
        view.getItem().setNgrouptaxmny(ngroupmny);
        UFDouble nglobaltaxmny =
            MathTool.sub(oview.getItem().getNglobaltaxmny(),
                sqedview.getNglobaltaxmny());
        view.getItem().setNglobaltaxmny(nglobaltaxmny);
        UFDouble nglobalmny =
            MathTool.sub(oview.getItem().getNglobalmny(),
                sqedview.getNglobalmny());
        view.getItem().setNglobaltaxmny(nglobalmny);

      }
    }

  }

  /**
   * 本次结算数量不等于行数量的需要重新计算金额
   * 
   * @param svos
   */
  public void reCalNumMny(SquareOutViewVO[] svos) {
    this.setNewExchangeRate(svos);
    // 需要重新计算金额的vo
    List<SquareOutViewVO> list = new ArrayList<SquareOutViewVO>();
    for (SquareOutViewVO svo : svos) {
      UFDouble nthisnum = svo.getItem().getNthisnum();
      UFDouble nnum = svo.getItem().getNnum();
      if (!MathTool.equals(nthisnum, nnum)) {
        list.add(svo);
      }
    }
    // 本次结算数量不等于行数量的需要重新计算金额
    if (list.size() > 0) {
      SquareOutViewVO[] views = list.toArray(new SquareOutViewVO[list.size()]);
      SquareOutBVO[] bvos =
          SquareOutVOUtils.getInstance().getSquareOutBVO(views);
      new SquareCalculatorForVO().calculate(bvos, SquareOutBVO.NTHISNUM);
    }

  }

  /**
   * 查询待手工结算数据的时候处理尾差
   * 
   * @param svos
   */
  public void reCalNumMnyAndWCForManualSquareQuery(SquareOutViewVO[] svos) {

    // 需要重新计算金额的vo
    List<SquareOutViewVO> list = new ArrayList<SquareOutViewVO>();
    for (SquareOutViewVO svo : svos) {
      UFDouble nthisnum = svo.getItem().getNthisnum();
      UFDouble nnum = svo.getItem().getNnum();
      if (!MathTool.equals(nthisnum, nnum)) {
        list.add(svo);
      }
    }
    // 本次结算数量不等于行数量的需要重新计算金额
    if (list.size() > 0) {
      SquareOutViewVO[] views = list.toArray(new SquareOutViewVO[list.size()]);
      SquareOutBVO[] bvos =
          SquareOutVOUtils.getInstance().getSquareOutBVO(views);
      new SquareCalculatorForVO().calculate(bvos, SquareOutBVO.NTHISNUM);
      this.processMnyForWC(views);
    }

  }

  /**
   * 获取已经结算数据
   * 
   * @return <销售出库待结算单bid,SquareOutDetailVO>
   *         SquareOutDetailVO中存的是累计主数量、累计原币价税合计、无税金额、税额
   * @param sdvos --- 销售出库结算单
   */
  private Map<String, SquareOutDetailVO> getManualSquareDetailVO(
      SquareOutDetailVO[] sdvos) {
    // <销售出库待结算单bid,SquareOutDetailVO>
    // SquareOutDetailVO中存的是累计主数量、累计原币价税合计、无税金额、税额
    Map<String, SquareOutDetailVO> msquaredvo =
        new HashMap<String, SquareOutDetailVO>();
    for (SquareOutDetailVO dvo : sdvos) {
      SquareOutDetailVO tempdvo = msquaredvo.get(dvo.getCsalesquarebid());
      if (VOChecker.isEmpty(tempdvo)) {
        msquaredvo.put(dvo.getCsalesquarebid(), dvo);
      }
      // 结算类型相同的才累加结算信息
      else if (tempdvo.getFsquaretype().intValue() == dvo.getFsquaretype()
          .intValue()) {
        // 数量
        UFDouble totalnum =
            MathTool.add(tempdvo.getNsquarenum(), dvo.getNsquarenum());
        tempdvo.setNsquarenum(totalnum);
        // 价税合计
        UFDouble totaltaxmny =
            MathTool.add(tempdvo.getNorigtaxmny(), dvo.getNorigtaxmny());
        UFDouble totaltaxmnylocal =
            MathTool.add(tempdvo.getNtaxmny(), dvo.getNtaxmny());
        tempdvo.setNorigtaxmny(totaltaxmny);
        tempdvo.setNtaxmny(totaltaxmnylocal);
        // 无税金额
        UFDouble totalmny =
            MathTool.add(tempdvo.getNorigmny(), dvo.getNorigmny());
        UFDouble totalmnylocal = MathTool.add(tempdvo.getNmny(), dvo.getNmny());
        tempdvo.setNorigmny(totalmny);
        tempdvo.setNmny(totalmnylocal);
        // TODO 2012.02.16 fbinly v61删除原币税额字段
        // // 税额
        // UFDouble totaltax =
        // MathTool.add(tempdvo.getNorigtax(), dvo.getNorigtax());
        // tempdvo.setNorigtax(totaltax);
        UFDouble totaltaxlocal = MathTool.add(tempdvo.getNtax(), dvo.getNtax());
        tempdvo.setNtax(totaltaxlocal);
        // 2012.02.16 fbinly v61新增字段
        UFDouble totalcaltaxmny =
            MathTool.add(tempdvo.getNcaltaxmny(), dvo.getNcaltaxmny());
        tempdvo.setNcaltaxmny(totalcaltaxmny);
        // 集团金额
        UFDouble totalgrouptaxmny =
            MathTool.add(tempdvo.getNgrouptaxmny(), dvo.getNgrouptaxmny());
        UFDouble totalgroupmny =
            MathTool.add(tempdvo.getNgroupmny(), dvo.getNgroupmny());
        tempdvo.setNgrouptaxmny(totalgrouptaxmny);
        tempdvo.setNgroupmny(totalgroupmny);
        // 全局金额
        UFDouble totalglobaltaxmny =
            MathTool.add(tempdvo.getNglobaltaxmny(), dvo.getNglobaltaxmny());
        UFDouble totalglobalmny =
            MathTool.add(tempdvo.getNglobalmny(), dvo.getNglobalmny());
        tempdvo.setNglobaltaxmny(totalglobaltaxmny);
        tempdvo.setNglobalmny(totalglobalmny);
      }
    }
    return msquaredvo;
  }

  /**
   * 查询原始的销售出库待结算单
   */
  private Map<String, SquareOutViewVO> getOldSquareOutViewVO(String[] bids) {
    SquareOutViewVO[] oldview =
        new QuerySquare4CVOBP().querySquareOutViewVOByBID(bids);
    // <bid,SquareOutViewVO>
    Map<String, SquareOutViewVO> moldview =
        new HashMap<String, SquareOutViewVO>();
    for (SquareOutViewVO view : oldview) {
      moldview.put(view.getItem().getCsalesquarebid(), view);
    }
    return moldview;
  }

  private void setNewExchangeRate(SquareOutViewVO[] svos) {
    Map<String, UFDouble> mapexrate = new HashMap<String, UFDouble>();
    for (SquareOutViewVO svo : svos) {
      SquareOutBVO bvo = svo.getItem();
      String corigcurrencyid = bvo.getCorigcurrencyid();
      String ccurrencyorgid = bvo.getCcurrencyid();
      String csettleorgid = bvo.getPk_org();
      UFDouble exchangerate = bvo.getNexchangerate();
      if (!PubAppTool.isNull(corigcurrencyid)
          && !PubAppTool.isNull(ccurrencyorgid)
          && !PubAppTool.isNull(csettleorgid)) {
        String key = csettleorgid + ccurrencyorgid;
        if (mapexrate.containsKey(key)) {
          exchangerate = mapexrate.get(key);
        }
        else {
          exchangerate =
              SOCurrencyUtil.getInCurrencyRateByOrg(csettleorgid,
                  corigcurrencyid, ccurrencyorgid, AppContext.getInstance()
                  .getBusiDate());
        }
        bvo.setNexchangerate(exchangerate);
      }
    }

    SquareOutBVO[] bvos = SquareOutVOUtils.getInstance().getSquareOutBVO(svos);
    new PriceNumMnyCalculatorForVO().calculateLocal(bvos);
  }

}
