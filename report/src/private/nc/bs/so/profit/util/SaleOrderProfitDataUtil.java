package nc.bs.so.profit.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ArrayUtils;

import nc.vo.bd.material.MaterialVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pub.query.ConditionVO;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.scmpub.parameter.AskCostPriceParaVO;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.pub.votools.SoVoTools;
import nc.vo.so.report.paravo.ProfitContext;
import nc.vo.so.report.profit.OrderProfitViewVO;

import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.itf.scmpub.reference.uap.org.StockOrgPubService;
import nc.itf.so.report.RemoteCallService;

import nc.pub.smart.context.SmartContext;

import nc.ui.querytemplate.querytree.QueryScheme;

public class SaleOrderProfitDataUtil {

  /**
   * 销售组织级次对应关系
   */
  private Map<String, String> saleorglevelmap = new HashMap<String, String>();

  /**
   * 构造方法
   */
  public SaleOrderProfitDataUtil() {
    // TODO
  }

  public void processData(OrderProfitViewVO[] viewvos, SmartContext content) {
    TimeLog.logStart();
    String key =
        com.ufida.report.anareport.FreeReportContextKey.KEY_IQUERYCONDITION;
    com.ufida.report.anareport.base.BaseQueryCondition condition =
        (com.ufida.report.anareport.base.BaseQueryCondition) content
            .getAttribute(key);
    QueryScheme scheme = (QueryScheme) condition.getUserObject();
    ConditionVO[] conds = (ConditionVO[]) scheme.get("logicalcondition");
    // 销售组织汇总级次
    UFDouble saleorglevel = UFDouble.ZERO_DBL;
    // 物料分类级次
    UFDouble cmaterialmarbasclasslevel = UFDouble.ZERO_DBL;
    for (ConditionVO cond : conds) {
      if (cond.getFieldCode().equals(ProfitContext.SALEORGLEVEL)) {
        // 销售组织级次
        saleorglevel = new UFDouble(cond.getValue());

      }
      else if (cond.getFieldCode().equals(
          ProfitContext.CMATERIALMARBASCLASSLEVEL)) {
        cmaterialmarbasclasslevel = new UFDouble(cond.getValue());
      }
    }
    // 获取销售订单子表ID集
    List<String> bidlist = new ArrayList<String>();
    List<String> saleorgidlist = new ArrayList<String>();
    List<String> materidlist = new ArrayList<String>();
    List<AskCostPriceParaVO> costpricelist =
        new ArrayList<AskCostPriceParaVO>();

    Map<String, String> m_st_fin =
        StockOrgPubService.queryFinanceOrgIDByStockOrgID(SoVoTools
            .getVOsOnlyValues(viewvos, SaleOrderBVO.CSENDSTOCKORGID));
    // 库存组织对应的结算财务组织
    String cendstordocfin = null;

    for (OrderProfitViewVO viewvo : viewvos) {
      Object ob = viewvo.getAttributeValue(SaleOrderBVO.CSALEORDERBID);
      bidlist.add(null == ob ? null : ob.toString());
      ob = viewvo.getAttributeValue(SaleOrderBVO.PK_ORG);
      saleorgidlist.add(null == ob ? null : ob.toString());
      ob = viewvo.getAttributeValue(SaleOrderBVO.CMATERIALID);
      materidlist.add(null == ob ? null : ob.toString());
      AskCostPriceParaVO costparavo = new AskCostPriceParaVO();
      costparavo.setCinventoryid(null == ob ? null : ob.toString());
      ob = viewvo.getAttributeValue(SaleOrderBVO.PK_ORG);
      costparavo.setPk_org(null == ob ? null : ob.toString());
      // 订单结算财务组织
      String orderfin =
          (String) viewvo.getAttributeValue(SaleOrderBVO.CSETTLEORGID);
      costparavo.setPk_financeorg(null == ob ? null : ob.toString());
      // 单组织销售传库存组织和仓库 、跨组织的时候不传，就去结算财务组织的成本
      ob = viewvo.getAttributeValue(SaleOrderBVO.CSENDSTOCKORGID);
      cendstordocfin = m_st_fin.get(ob);
      // 单组织才需要传库存组织和仓库来取成本
      if (orderfin.equals(cendstordocfin)) {
        costparavo.setPk_stockorg(null == ob ? null : ob.toString());
        ob = viewvo.getAttributeValue(SaleOrderBVO.CSENDSTORDOCID);
        costparavo.setPk_stordoc(null == ob ? null : ob.toString());
      }
      ob = viewvo.getAttributeValue(SaleOrderBVO.VBATCHCODE);
      costparavo.setVbatch(null == ob ? null : ob.toString());
      costpricelist.add(costparavo);
    }
    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4006005_0", "04006005-0002")/* @res "处理数据：准备5000取成本价的参数" */);
    Map<String, String> materialmarbasmap = new HashMap<String, String>();
    if (!MathTool.isZero(cmaterialmarbasclasslevel)) {
      String[] cmaterialids =
          materidlist.toArray(new String[materidlist.size()]);
      materialmarbasmap =
          RemoteCallService.queryMarBasClassIDByClassLevelAndMaterialOIDs(
              cmaterialids, cmaterialmarbasclasslevel.intValue());
    }
    Map<String, UFDouble[]>  costmap = new HashMap<String, UFDouble[]>();
   // 启用存货核算模块
    if (SysInitGroupQuery.isIAEnabled()) {
      costmap= RemoteCallService.getSOCostMny(bidlist.toArray(new String[bidlist
            .size()]));
    }
    Map<String, UFDouble> ntotalreceivmnymap = new HashMap<String, UFDouble>();
    // 启用应收模块
    if (SysInitGroupQuery.isAREnabled()) {
      ntotalreceivmnymap =
          RemoteCallService.getNotaxForSoorder(bidlist
              .toArray(new String[bidlist.size()]));
    }

    TimeLog.logStart();
    Map<String, UFDouble> costprice =
        RemoteCallService.getPriceBySCM02(costpricelist
            .toArray(new AskCostPriceParaVO[costpricelist.size()]));
    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4006005_0", "04006005-0003")/* @res "处理数据：取5000数据的成本价" */);
    TimeLog.logStart();
    for (OrderProfitViewVO viewvo : viewvos) {
      // 计算要统计的金额数量字段
      this.addTotalKeys(viewvo, costprice, costmap, ntotalreceivmnymap,
          m_st_fin);

      this.processLevel(viewvo, materialmarbasmap, saleorglevel.intValue());

    }
    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4006005_0", "04006005-0004")/* @res "处理数据：5000行数据的计算和处理级次" */);
  }

  private void addTotalKeys(OrderProfitViewVO viewvo,
      Map<String, UFDouble> costprice, Map<String, UFDouble[]> costmap,
      Map<String, UFDouble> ntotalreceivmnymap, Map<String, String> m_st_fin) {

    String key = this.createKey(viewvo, m_st_fin);
    // 订单主数量
    UFDouble nordernnum = viewvo.getNordernnum();
    // 主本币无税净价
    UFDouble nnetprice = viewvo.getNnetprice();
    // 应收主数量(确认应收主数量（订单上他=确认+回冲）+暂估应收主数量）
    UFDouble ntotalreceivnum =
        MathTool.add(viewvo.getNtotalestarnum(), viewvo.getNtotalarnum());
    // 累计成本结算主数量
    UFDouble ntotalcostnum = viewvo.getNtotalcostnum();
    UFDouble[] values =
        costmap.get(viewvo.getAttributeValue(SaleOrderBVO.CSALEORDERBID));
    if (!ArrayUtils.isEmpty(values) && values.length > 2) {
      ntotalcostnum = values[2];
    }
    // 应收无税金额
    UFDouble ntotalreceivmny =
        ntotalreceivmnymap.get(viewvo
            .getAttributeValue(SaleOrderBVO.CSALEORDERBID));

    // 主数量
    UFBoolean bbcostsettleflag =
        (UFBoolean) viewvo.getAttributeValue(SaleOrderBVO.BBCOSTSETTLEFLAG);
    UFBoolean bbarsettleflag =
        (UFBoolean) viewvo.getAttributeValue(SaleOrderBVO.BBARSETTLEFLAG);
    if (bbcostsettleflag.booleanValue() && bbarsettleflag.booleanValue()) {
      bbcostsettleflag = UFBoolean.TRUE;
    }
    else {
      bbcostsettleflag = UFBoolean.FALSE;
    }
    UFDouble nmainnum =
        this.getMainnum(nordernnum, ntotalreceivnum, ntotalcostnum,
            bbcostsettleflag);
    // 无税金额=应收无税金额 + （主数量 - 应收主数量）* 订单本币无税净价(在数据加工中计算)
    UFDouble nntaxmny =
        this.getNmny(ntotalreceivmny, nmainnum, ntotalreceivnum, nnetprice);
    // 调用外模块取价规则
    UFDouble outprice = costprice.get(key);
    // 成本结算成本金额----成本结算成本金额 =
    // 订单生成存货核算单据已经成本计算部分的成本金额+订单生成存货核算单据未成本计算部分的主数量
    // *（外模块取成本规则：结存单价or参考成本or计划价）（数据加工中计算）
    UFDouble ntotalsettlecostmny =
        this.getNtotalsettlecostmny(
            (String) viewvo.getAttributeValue(SaleOrderBVO.CSALEORDERBID),
            costmap, outprice);
    // 成本金额---存货核算成本金额 + （主数量-传存货核算主数量）* （外模块取成本规则：结存单价or参考成本or计划价）（数据加工中计算）

    UFDouble ntotalcostmny =
        this.getNtotalcostmny(ntotalsettlecostmny, nmainnum, ntotalcostnum,
            outprice);
    // 无税单价 = 无税金额 / 主数量(界面公式处理)
    UFDouble nntaxprice = UFDouble.ZERO_DBL;
    // 成本单价(界面公式处理)
    UFDouble ncostprice = UFDouble.ZERO_DBL;
    // if (!MathTool.isZero(nmainnum)) {
    // nntaxprice = nntaxmny.div(nmainnum);
    // ncostprice = ntotalcostmny.div(nmainnum);
    // }
    // 毛利
    UFDouble nprofitmny = MathTool.sub(nntaxmny, ntotalcostmny);
    // 毛利率
    UFDouble nprofitcate = UFDouble.ZERO_DBL;

    viewvo.setNmainnum(nmainnum);
    viewvo.setNtotalreceivnum(ntotalreceivnum);
    viewvo.setNtotalreceivmny(ntotalreceivmny);
    viewvo.setNntaxmny(nntaxmny);
    viewvo.setNntaxprice(nntaxprice);
    viewvo.setNtotalcostnum(ntotalcostnum);
    viewvo.setNtotalsettlecostmny(ntotalsettlecostmny);
    viewvo.setNtotalcostmny(ntotalcostmny);
    viewvo.setNcostprice(ncostprice);
    viewvo.setNprofitmny(nprofitmny);
    viewvo.setNprofitcate(nprofitcate);
  }

  private String createKey(OrderProfitViewVO viewvo,
      Map<String, String> m_st_fin) {

    String cendstordocfin =
        m_st_fin.get(viewvo.getAttributeValue(SaleOrderBVO.CSENDSTOCKORGID));
    StringBuffer ret = new StringBuffer();
    Object ob = viewvo.getAttributeValue(SaleOrderBVO.PK_ORG);
    ret.append(null == ob ? "null|" : ob.toString() + "|");

    ob = viewvo.getAttributeValue(SaleOrderBVO.CSETTLEORGID);
    ret.append(null == ob ? "null|" : ob.toString() + "|");
    // 单组织就传了库存组织加仓库
    if (null != ob && ob.equals(cendstordocfin)) {
      ob = viewvo.getAttributeValue(SaleOrderBVO.CSENDSTOCKORGID);
      ret.append(null == ob ? "null|" : ob.toString() + "|");
      ob = viewvo.getAttributeValue(SaleOrderBVO.CSENDSTORDOCID);
      ret.append(null == ob ? "null|" : ob.toString() + "|");
    }
    else {
      // 跨组织没传库存组织加仓库
      ret.append("null|");
      ret.append("null|");
    }

    ob = viewvo.getAttributeValue(SaleOrderBVO.CMATERIALID);
    ret.append(null == ob ? "null|" : ob.toString() + "|");
    ob = viewvo.getAttributeValue(SaleOrderBVO.VBATCHCODE);
    ret.append(null == ob ? "null" : ob.toString());

    return ret.toString();
  }

  /**
   * 计算主数量
   * 
   * @param ordernnum 订单主数量
   * @param ntotalreceivnum 应收主数量
   * @param ntotalcostnum 成本结算主数量
   * @param bbcostsettleflag
   * @return
   */
  private UFDouble getMainnum(UFDouble ordernnum, UFDouble ntotalreceivnum,
      UFDouble ntotalcostnum, UFBoolean bbcostsettleflag) {
    UFDouble retmainnum = UFDouble.ZERO_DBL;
    // 结算关闭 取 应收主数量和成本结算主数量最大值
    if (bbcostsettleflag.booleanValue()) {
      if (MathTool.absCompareTo(ntotalreceivnum, ntotalcostnum) > 0) {
        retmainnum = ntotalreceivnum;
      }
      else {
        retmainnum = ntotalcostnum;
      }
    }
    // 为结算关闭取 订单主数量、应收主数量和成本结算主数量最大值
    else {
      retmainnum = ordernnum;
      if (MathTool.absCompareTo(ntotalcostnum, retmainnum) > 0) {
        retmainnum = ntotalcostnum;
      }
      if (MathTool.absCompareTo(ntotalreceivnum, retmainnum) > 0) {
        retmainnum = ntotalreceivnum;
      }
    }
    return retmainnum;
  }

  /**
   * 无税金额=应收无税金额 + （主数量 - 应收主数量）* 订单本币无税净价
   * 
   * @param ntotalallmny
   * @param mainnum
   * @param ntotalallnum
   * @param nnetprice
   * @return
   */
  private UFDouble getNmny(UFDouble ntotalallmny, UFDouble mainnum,
      UFDouble ntotalallnum, UFDouble nnetprice) {
    UFDouble price = nnetprice;
    if (null == nnetprice) {
      price = new UFDouble(0);
    }

    UFDouble ret = MathTool.sub(mainnum, ntotalallnum).multiply(price);
    return MathTool.add(ntotalallmny, ret);
  }

  private UFDouble getNtotalcostmny(UFDouble ntotalsettlecostmny,
      UFDouble mainnum, UFDouble ntotalcostnum, UFDouble outprice) {
    UFDouble num = MathTool.sub(mainnum, ntotalcostnum);
    UFDouble mny = num.multiply(null == outprice ? new UFDouble(0) : outprice);
    return MathTool.add(ntotalsettlecostmny, mny);
  }

  /**
   * 成本结算成本金额
   * 
   * @param key 订单表体id
   * @param costmap
   * @param outprice 成本单价
   * @return
   */
  private UFDouble getNtotalsettlecostmny(String key,
      Map<String, UFDouble[]> costmap, UFDouble outprice) {
    UFDouble[] ncost = costmap.get(key);
    if (null == ncost) {
      return UFDouble.ZERO_DBL;
    }
    UFDouble mny =
        ncost[1].multiply(null == outprice ? new UFDouble(0) : outprice);
    mny = MathTool.add(mny, ncost[0]);
    return mny;
  }

  private void processLevel(OrderProfitViewVO viewvo,
      Map<String, String> materialmarbasmap, int saleorglevel) {
    // 为末级则不处理，应该基本分类本身就是末级。
    if (saleorglevel != 0) {
      String cmarid =
          viewvo.getAttributeValue(SaleOrderBVO.CMATERIALID).toString();
      viewvo.setAttributeValue(MaterialVO.PK_MARBASCLASS,
          materialmarbasmap.get(cmarid));
      String oldsaleorgid =
          viewvo.getAttributeValue(SaleOrderBVO.PK_ORG).toString();

      String newsaleorgid = this.saleorglevelmap.get(oldsaleorgid);
      if (null == newsaleorgid) {
        newsaleorgid =
            RemoteCallService.querySaleorgIDByLevel(oldsaleorgid, saleorglevel);
        this.saleorglevelmap.put(oldsaleorgid, newsaleorgid);
      }
      viewvo.setAttributeValue(SaleOrderBVO.PK_ORG, newsaleorgid);
    }
  }

}
