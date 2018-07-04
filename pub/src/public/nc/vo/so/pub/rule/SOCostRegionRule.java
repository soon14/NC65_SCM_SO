package nc.vo.so.pub.rule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.itf.scmpub.reference.uap.org.CostRegionPubService;
import nc.itf.scmpub.reference.uap.org.StockOrgPubService;
import nc.pubitf.to.settlerule.ic.MatchSettleRuleResult;
import nc.pubitf.to.settlerule.so.IMatchSettleruleServiceForSo;
import nc.pubitf.to.settlerule.so.MatchSettleRuleVOForSo;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.trade.checkrule.VOChecker;

/**
 * 销售管理成本获取公共规则
 * 
 * @since 6.5
 * @version 2015-6-13 下午02:36:21
 * @author 刘景
 */
public class SOCostRegionRule {

  /**
   * 成本域查询</br>
   * 
   * 单组织：通过库存组织+仓库获得成本域</br>
   * 跨组织：优先通过内部交易结算规则获得成本域，其次结算财务组织获得成本域
   * @param 发货库存组织old、仓库id、订单交易类型、结算财务oid、物料oid
   * @return paras Map<查询参数,成本域>
   */
  public Map<String, String> queryCostRegion(CostRegionPara[] paras) {
    // 区分单组织跨组织数据
    List<CostRegionPara[]> list = this.filterSingleSpanData(paras);
    CostRegionPara[] singlevos = list.get(0);
    CostRegionPara[] spanlevos = list.get(1);

    // 设置成本域:单组织
    this.setCostOrgForSingle(singlevos);

    // 设置成本域:跨组织
    this.setCostOrgForSpan(spanlevos);

    Map<String, String> costregionmap = retCostRegion(paras);

    return costregionmap;
  }

  /**
   * 构建成本map的key
   * 
   * @param cinfinanceorgid 财务组织
   * @param cmaterialid 物料
   * @param tOrdertantype 订单类型
   * @param stockorgid 发货库存组织
   * @param stordocid 仓库
   * @return
   */
  public String getCostRegionMapKey(String cinfinanceorgid, String cmaterialid,
      String tOrdertantype, String stockorgid, String stordocid) {
    StringBuffer costpara = new StringBuffer();
    costpara.append(cinfinanceorgid);
    costpara.append(cmaterialid);
    costpara.append(tOrdertantype);
    costpara.append(stockorgid);
    costpara.append(stordocid);
    return costpara.toString();
  }

  private Map<String, String> retCostRegion(CostRegionPara[] paras) {
    Map<String, String> costregionmap = new HashMap<String, String>();
    if (null == paras || paras.length == 0) {
      return costregionmap;
    }
    for (CostRegionPara para : paras) {
      String costparakey =
          getCostRegionMapKey(para.getCinfinanceorgid(), para.getCmaterialid(),
              para.getOrdertantype(), para.getStockorgid(), para.getStordocid());
      costregionmap.put(costparakey, para.getCcostorgid());
    }
    return costregionmap;
  }

  private void setCostOrgForSpan(CostRegionPara[] paras) {
    if (VOChecker.isEmpty(paras)) {
      return;
    }
    // 获取内部结算规则
    Map<MatchSettleRuleVOForSo, MatchSettleRuleResult> m_index_cr =
        this.getMatchSettleRuleResult(paras);

    // 通过内部交易结算规则设置 成本域
    List<CostRegionPara> l_ByFinanceList =
        this.setCostOrgByTOSettleRule(m_index_cr, paras);

    // 通过销售结算财务组织设置 成本域
    int size = l_ByFinanceList.size();
    if (size > 0) {
      this.setCostOrgByFinance(l_ByFinanceList
          .toArray(new CostRegionPara[size]));
    }

  }

  private void setCostOrgByFinance(CostRegionPara[] paras) {
    Set<String> stockorgidset = new HashSet<String>();
    for (CostRegionPara para : paras) {
      stockorgidset.add(para.getCinfinanceorgid());
    }
    Map<String, String> m_fico =
        CostRegionPubService
            .getDefaultCostRegionMapByFinanceOrgIDS(stockorgidset
                .toArray(new String[stockorgidset.size()]));

    if (m_fico != null) {
      for (CostRegionPara para : paras) {
        para.setCcostorgid(m_fico.get(para.getCinfinanceorgid()));
      }
    }
  }

  private List<CostRegionPara> setCostOrgByTOSettleRule(
      Map<MatchSettleRuleVOForSo, MatchSettleRuleResult> m_index_cr,
      CostRegionPara[] paras) {
    String costorgid = null;
    // 需要通过销售结算财务组织找成本域的vo
    List<CostRegionPara> l_ByFinanceList = new ArrayList<CostRegionPara>();
    for (CostRegionPara para : paras) {

      MatchSettleRuleVOForSo matchpara = this.getMatchSettleRuleVOForSo(para);
      // 内部结算规则
      MatchSettleRuleResult mrlt = m_index_cr.get(matchpara);
      if (mrlt == null) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4006010_0", "04006010-0036")/*
                                                                     * @res
                                                                     * "跨组织销售没有匹配到内部交易结算规则！"
                                                                     */);
      }
      else {
        costorgid = mrlt.getCostRegion();
        // 设置成本域
        if (costorgid == null) {
          l_ByFinanceList.add(para);
        }
        else {
          para.setCcostorgid(costorgid);
        }
      } // end else
    }
    return l_ByFinanceList;
  }

  private Map<MatchSettleRuleVOForSo, MatchSettleRuleResult> getMatchSettleRuleResult(
      CostRegionPara[] paras) {
    // 准备读取内部结算规则参数
    List<MatchSettleRuleVOForSo> l_matchSettle =
        new ArrayList<MatchSettleRuleVOForSo>();
    for (CostRegionPara para : paras) {
      MatchSettleRuleVOForSo mso = this.getMatchSettleRuleVOForSo(para);
      l_matchSettle.add(mso);
    }

    // 读取内部结算规则
    Map<MatchSettleRuleVOForSo, MatchSettleRuleResult> m_index_cr = null;
    try {
      if (!SysInitGroupQuery.isTOEnabled()) {
        new HashMap<MatchSettleRuleVOForSo, MatchSettleRuleResult>();
      }
      IMatchSettleruleServiceForSo bo =
          NCLocator.getInstance().lookup(IMatchSettleruleServiceForSo.class);
      m_index_cr = bo.matchSettleruleForSo(l_matchSettle);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappBusinessException(e.getMessage());
    }
    if (m_index_cr == null || m_index_cr.size() == 0) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4006010_0", "04006010-0036")/*
                                                                   * @res
                                                                   * "跨组织销售没有匹配到内部交易结算规则！"
                                                                   */);
    }
    return m_index_cr;
  }

  /**
   * 准备内部结算规则接口参数VO
   * 
   * @param hvo
   * @param bvo
   * @param index
   * @return
   */
  private MatchSettleRuleVOForSo getMatchSettleRuleVOForSo(CostRegionPara para) {
    MatchSettleRuleVOForSo mstoVo = new MatchSettleRuleVOForSo();
    String pk_group = AppContext.getInstance().getPkGroup();
    mstoVo.setPk_group(pk_group);
    // 源头订单交易类型
    mstoVo.setCtranstype(para.getOrdertantype());
    // 发货库存组织
    mstoVo.setCoutstockorgid(para.getStockorgid());
    // 物料oid
    mstoVo.setCinventoryid(para.getCmaterialid());
    // 结算财务组织
    mstoVo.setCinfinanceorgid(para.getCinfinanceorgid());
    return mstoVo;
  }

  /**
   * 发货库存组织所属财务组织和结算财务组织一致调用UAP接口获取成本域
   * 
   * @param m_stockOrg
   * @param l_eqList
   */
  private void setCostOrgByStockOrgsAndStordocs(String[] stockorgids,
      String[] stordocids, CostRegionPara[] paras) {
    // <key:库存组织id+仓库id，value为成本域id>
    Map<String, String> m_CostRegion =
        CostRegionPubService.queryCostRegionIDByStockOrgsAndStordocs(
            stockorgids, stordocids);
    if (m_CostRegion == null || m_CostRegion.size() == 0) {
      return;
    }
    for (CostRegionPara para : paras) {
      String key = para.getStockorgid() + para.getStordocid();
      para.setCcostorgid(m_CostRegion.get(key));
    }

  }

  private void setCostOrgForSingle(CostRegionPara[] paras) {
    if (null == paras || paras.length == 0) {
      return;
    }
    // 库存组织
    List<String> s_stockOrg = new ArrayList<String>();
    // 仓库
    List<String> s_storddoc = new ArrayList<String>();

    for (CostRegionPara para : paras) {
      // 库存组织
      String stockOrg = para.getStockorgid();
      s_stockOrg.add(stockOrg);
      // 仓库
      String storddoc = para.getStordocid();
      s_storddoc.add(storddoc);
    }

    String[] stockorgids = s_stockOrg.toArray(new String[s_stockOrg.size()]);
    String[] stordocids = s_storddoc.toArray(new String[s_storddoc.size()]);
    this.setCostOrgByStockOrgsAndStordocs(stockorgids, stordocids, paras);

  }

  private List<CostRegionPara[]> filterSingleSpanData(CostRegionPara[] paras) {
    // 获取库存组织对应的财务组织
    Map<String, String> m_st_fin = this.queryFinanceOrgIDByStockOrgID(paras);

    List<CostRegionPara> l_single = new ArrayList<CostRegionPara>();
    List<CostRegionPara> l_span = new ArrayList<CostRegionPara>();
    // 区分单组织、跨组织销售数据
    for (CostRegionPara para : paras) {
      String pkorg = para.getCinfinanceorgid();
      String sendstock = para.getStockorgid();
      // 单组织
      if (pkorg.equals(m_st_fin.get(sendstock))) {
        l_single.add(para);
      }
      // 跨组织
      else {
        l_span.add(para);
      }
    }

    List<CostRegionPara[]> list = new ArrayList<CostRegionPara[]>();
    int size = l_single.size();
    if (size > 0) {
      list.add(l_single.toArray(new CostRegionPara[size]));
    }
    else {
      list.add(null);
    }
    size = l_span.size();
    if (size > 0) {
      list.add(l_span.toArray(new CostRegionPara[size]));
    }
    else {
      list.add(null);
    }

    return list;
  }

  private Map<String, String> queryFinanceOrgIDByStockOrgID(
      CostRegionPara[] paras) {
    Set<String> stockorgidset = new HashSet<String>();
    for (CostRegionPara para : paras) {
      stockorgidset.add(para.getStockorgid());
    }
    Map<String, String> m_st_fin =
        StockOrgPubService.queryFinanceOrgIDByStockOrgID(stockorgidset
            .toArray(new String[stockorgidset.size()]));
    // <发货库存组织,对应的财务组织>
    return m_st_fin;
  }

}
