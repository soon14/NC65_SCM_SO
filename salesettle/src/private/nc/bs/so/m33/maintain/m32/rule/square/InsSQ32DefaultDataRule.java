package nc.bs.so.m33.maintain.m32.rule.square;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.vo.bd.income.IncomeVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.scmpub.res.billtype.ICBillType;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderViewVO;
import nc.vo.so.m33.m32.entity.SquareInvBVO;
import nc.vo.so.m33.m32.entity.SquareInvHVO;
import nc.vo.so.m33.m32.entity.SquareInvVO;
import nc.vo.so.m33.m32.entity.SquareInvVOUtils;
import nc.vo.so.m33.m32.entity.SquareInvViewVO;
import nc.vo.so.m33.pub.biz.toia.ProcessToIA;
import nc.vo.so.m33.pub.biz.toia.ProcessToIAPara;
import nc.vo.so.pub.votools.SoVoTools;
import nc.vo.trade.checkrule.VOChecker;

import nc.itf.scmpub.reference.uap.bd.payterm.PaytermService;
import nc.itf.scmpub.reference.uap.org.CostRegionPubService;
import nc.itf.scmpub.reference.uap.org.StockOrgPubService;
import nc.itf.so.m33.ref.so.m30.SOSaleOrderServicesUtil;
import nc.itf.so.m33.ref.to.settlerule.TOSettleRuleServicesUtil;

import nc.pubitf.to.settlerule.ic.MatchSettleRuleResult;
import nc.pubitf.to.settlerule.so.MatchSettleRuleVOForSo;

import nc.impl.pubapp.pattern.rule.IRule;

/**
 * @description
 * 销售发票结算前默认值填充规则
 * @scene
 * 销售发票结算前
 * @param
 * 无
 * @since 6.1
 * @version 2012-10-31 14:31:45
 * @author 冯加彬
 */
public class InsSQ32DefaultDataRule implements IRule<SquareInvVO> {

  @Override
  public void process(SquareInvVO[] vos) {

    SquareInvViewVO[] svvos =
        SquareInvVOUtils.getInstance().changeToSaleSquareViewVO(vos);

    // 从订单补充数据
    this.setDataFromSaleOrder(svvos);

    // 设置是否传成本
    this.setBCostFlag(svvos);

    // 设置是否传应收
    this.setARFlag(svvos);

    // 设置起效日期
    this.setEffectdate(svvos);
  }

  private void setBCostFlag(SquareInvViewVO[] svvos) {
    // 区分单组织跨组织数据并过滤劳务费用行
    List<SquareInvViewVO[]> list = this.filterSingleSpanData(svvos);
    SquareInvViewVO[] singlevos = list.get(0);
    SquareInvViewVO[] spanlevos = list.get(1);

    // 设置成本域:单组织
    this.setCostOrgForSingle(singlevos);

    // 设置是否传成本:单组织、跨组织数据
    this.setBCostForPubFlag(svvos);

    // 设置单组织是否传成本
    this.setBCostForSingleFlag(singlevos);

    // 设置跨组织是否传成本
    this.setBCostForSpanFlag(spanlevos);
  }

  private void setBCostForSingleFlag(SquareInvViewVO[] singlevos) {

    if (null == singlevos || singlevos.length == 0) {
      return;
    }
    // 排除已经设置不传成本的行
    List<SquareInvViewVO> l_single_cost = new ArrayList<SquareInvViewVO>();
    for (SquareInvViewVO view : singlevos) {
      if (view.getItem().getBcost().booleanValue()) {
        l_single_cost.add(view);
      }
    }
    if (l_single_cost.size() == 0) {
      return;
    }

    SquareInvViewVO[] svvos = new SquareInvViewVO[l_single_cost.size()];
    l_single_cost.toArray(svvos);

    ProcessToIAPara[] paras = this.getProcessToIAPara(svvos);
    ProcessToIA iaprc = new ProcessToIA();
    Map<String, UFBoolean> ret = iaprc.getSingleToIAResult(paras);
    int index = 0;
    for (SquareInvViewVO view : svvos) {
      UFBoolean flag = ret.get(String.valueOf(index));
      view.getItem().setBcost(flag);
      index++;
    }

  }

  private List<SquareInvViewVO[]> filterSingleSpanData(SquareInvViewVO[] svvos) {
    // 获取库存组织对应的财务组织
    Map<String, String> m_st_fin = this.queryFinanceOrgIDByStockOrgID(svvos);

    List<SquareInvViewVO> l_single = new ArrayList<SquareInvViewVO>();
    List<SquareInvViewVO> l_span = new ArrayList<SquareInvViewVO>();
    // 区分单组织、跨组织销售数据
    for (SquareInvViewVO view : svvos) {
      String pkorg = view.getHead().getPk_org();
      String sendstock = view.getItem().getCsendstockorgid();
      boolean bfeediscount =
          view.getItem().getBlaborflag().booleanValue()
              || view.getItem().getBdiscountflag().booleanValue();
      // 库存组织为空,费用折扣类存货
      if (PubAppTool.isNull(sendstock) || bfeediscount) {
        view.getItem().setBcost(UFBoolean.FALSE);
      }
      // 单组织
      else if (pkorg.equals(m_st_fin.get(sendstock))) {
        l_single.add(view);
      }
      // 跨组织
      else {
        l_span.add(view);
      }
    }

    List<SquareInvViewVO[]> list = new ArrayList<SquareInvViewVO[]>();
    int size = l_single.size();
    if (size > 0) {
      list.add(l_single.toArray(new SquareInvViewVO[size]));
    }
    else {
      list.add(null);
    }
    size = l_span.size();
    if (size > 0) {
      list.add(l_span.toArray(new SquareInvViewVO[size]));
    }
    else {
      list.add(null);
    }

    return list;
  }

  private Map<MatchSettleRuleVOForSo, MatchSettleRuleResult> getMatchSettleRuleResult(
      SquareInvViewVO[] svvos) {
    // 准备读取内部结算规则参数
    List<MatchSettleRuleVOForSo> l_matchSettle =
        new ArrayList<MatchSettleRuleVOForSo>();
    for (SquareInvViewVO view : svvos) {
      MatchSettleRuleVOForSo mso =
          this.getMatchSettleRuleVOForSo(view.getHead(), view.getItem());
      l_matchSettle.add(mso);
    }

    // 读取内部结算规则
    Map<MatchSettleRuleVOForSo, MatchSettleRuleResult> m_index_cr = null;
    try {
      m_index_cr = TOSettleRuleServicesUtil.matchSettlerule(l_matchSettle);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappBusinessException(e.getMessage());
    }
    if (m_index_cr == null || m_index_cr.size() == 0) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006010_0", "04006010-0036")/*@res "跨组织销售没有匹配到内部交易结算规则！"*/);
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
  private MatchSettleRuleVOForSo getMatchSettleRuleVOForSo(SquareInvHVO hvo,
      SquareInvBVO bvo) {
    MatchSettleRuleVOForSo mstoVo = new MatchSettleRuleVOForSo();
    mstoVo.setPk_group(hvo.getPk_group());
    // 源头订单交易类型
    mstoVo.setCtranstype(bvo.getVfirsttrantype());
    // 发货库存组织
    mstoVo.setCoutstockorgid(bvo.getCsendstockorgid());
    // 物料oid
    mstoVo.setCinventoryid(bvo.getCmaterialid());
    // 结算财务组织
    mstoVo.setCinfinanceorgid(bvo.getPk_org());
    return mstoVo;
  }

  private ProcessToIAPara[] getProcessToIAPara(SquareInvViewVO[] svvos) {
    ProcessToIAPara[] paras = new ProcessToIAPara[svvos.length];
    int index = 0;
    for (SquareInvViewVO view : svvos) {
      SquareInvBVO bvo = view.getItem();
      // 出库单交易类型
      String saleOutTransType = null;
      if (ICBillType.SaleOut.getCode().equals(bvo.getVsrctype())) {
        saleOutTransType = bvo.getVsrctrantype();
      }
      // 物料
      String materialvid = bvo.getCmaterialvid();
      // 仓库
      String stordocid = bvo.getCsendstordocid();
      // 结算财务组织
      String pk_org = view.getHead().getPk_org();
      paras[index] = new ProcessToIAPara();
      paras[index].setId(String.valueOf(index));
      paras[index].setFinorgoid(pk_org);
      paras[index].setMaterialvid(materialvid);
      paras[index].setSaleOutTransType(saleOutTransType);
      paras[index].setStordocid(stordocid);
      paras[index].setBdiscountflag(ValueUtils.getBoolean(bvo
          .getBdiscountflag()));
      paras[index].setBlaborflag(ValueUtils.getBoolean(bvo.getBlaborflag()));
      index++;
    }
    return paras;
  }

  private Map<String, String> queryFinanceOrgIDByStockOrgID(
      SquareInvViewVO[] svvos) {
    // 批量根据库存组织ID获取对应的财务组织ID <库存组织，库存组织所属的财务组织>
    SquareInvBVO[] bvos = SquareInvVOUtils.getInstance().getSquareInvBVO(svvos);
    Map<String, String> m_st_fin = null;
    m_st_fin =
        StockOrgPubService.queryFinanceOrgIDByStockOrgID(SoVoTools
            .getVOsOnlyValues(bvos, SquareInvBVO.CSENDSTOCKORGID));
    // <发货库存组织,对应的财务组织>
    return m_st_fin;
  }

  private void setARFlag(SquareInvViewVO[] svvos) {
    for (SquareInvViewVO view : svvos) {
      boolean blar = view.getItem().getBlargessflag().booleanValue();
      view.getItem().setBincome(UFBoolean.valueOf(!blar));
    }
  }

  private void setBCostForPubFlag(SquareInvViewVO[] svvos) {
    if (!VOChecker.isEmpty(svvos)) {
      ProcessToIAPara[] paras = this.getProcessToIAPara(svvos);
      ProcessToIA iaprc = new ProcessToIA();
      Map<String, UFBoolean> ret = iaprc.getPubToIAResult(paras);
      int index = 0;
      for (SquareInvViewVO view : svvos) {
        UFBoolean flag = ret.get(String.valueOf(index));
        view.getItem().setBcost(flag);
        index++;
      }
    }
  }

  private void setBCostForSpanFlag(SquareInvViewVO[] spanvos) {

    if (null == spanvos || spanvos.length == 0) {
      return;
    }
    // 排除已经设置不传成本的行
    List<SquareInvViewVO> l_span_cost = new ArrayList<SquareInvViewVO>();
    for (SquareInvViewVO view : spanvos) {
      if (view.getItem().getBcost().booleanValue()) {
        l_span_cost.add(view);
      }
    }
    if (l_span_cost.size() == 0) {
      return;
    }

    SquareInvViewVO[] spanlevos = new SquareInvViewVO[l_span_cost.size()];
    l_span_cost.toArray(spanlevos);
    Map<MatchSettleRuleVOForSo, MatchSettleRuleResult> m_index_cr =
        this.setCostOrgForSpan(spanlevos);
    if (VOChecker.isEmpty(m_index_cr)) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006010_0", "04006010-0036")/*@res "跨组织销售没有匹配到内部交易结算规则！"*/);
    }

    if (!VOChecker.isEmpty(spanlevos)) {
      // 设置是否传存货
      for (SquareInvViewVO view : spanlevos) {
        MatchSettleRuleVOForSo para =
            this.getMatchSettleRuleVOForSo(view.getHead(), view.getItem());
        // 内部结算规则
        MatchSettleRuleResult mrlt = m_index_cr.get(para);
        // 设置是否可以传存货(成本结算或者发出商品)
        if (mrlt == null) {
          ExceptionUtils
              .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
                  .getNCLangRes().getStrByID("4006010_0", "04006010-0036")/*@res "跨组织销售没有匹配到内部交易结算规则！"*/);
        }
        else {
          view.getItem().setBcost(mrlt.getSendtoia());
        }
      }
    }

  }

  private void setCostOrgByFinance(SquareInvViewVO[] svvos) {
    SquareInvBVO[] bvos = SquareInvVOUtils.getInstance().getSquareInvBVO(svvos);
    String[] financeorgids =
        SoVoTools.getVOsOnlyValues(bvos, SquareInvBVO.PK_ORG);
    Map<String, String> m_fico = null;
    m_fico =
        CostRegionPubService
            .getDefaultCostRegionMapByFinanceOrgIDS(financeorgids);

    if (m_fico != null) {
      for (SquareInvViewVO view : svvos) {
        view.getItem().setCcostorgid(m_fico.get(view.getHead().getPk_org()));
      }
    }
  }

  /**
   * 发货库存组织所属财务组织和结算财务组织不一致调用TO接口获取成本域
   * 
   * @param l_matchSettle
   * @param l_unEqList
   */
  private void setCostOrgBySpan(
      Map<MatchSettleRuleVOForSo, MatchSettleRuleResult> m_index_cr,
      SquareInvViewVO[] svvos) {
    // 通过内部交易结算规则设置 成本域
    List<SquareInvViewVO> l_ByFinanceList =
        this.setCostOrgByTOSettleRule(m_index_cr, svvos);

    // 通过销售结算财务组织设置 成本域
    int size = l_ByFinanceList.size();
    if (size > 0) {
      this.setCostOrgByFinance(l_ByFinanceList
          .toArray(new SquareInvViewVO[size]));
    }
  }

  /**
   * 发货库存组织所属财务组织和结算财务组织一致调用UAP接口获取成本域
   * 
   * @param m_stockOrg
   * @param l_eqList
   */
  private void setCostOrgByStockOrgsAndStordocs(String[] stockorgids,
      String[] stordocids, SquareInvViewVO[] svvos) {
    // <key:库存组织id+仓库id，value为成本域id>
    Map<String, String> m_CostRegion =
        CostRegionPubService.queryCostRegionIDByStockOrgsAndStordocs(
            stockorgids, stordocids);
    if (m_CostRegion == null || m_CostRegion.size() == 0) {
      return;
    }
    SquareInvBVO bvo = null;
    for (SquareInvViewVO view : svvos) {
      bvo = view.getItem();
      String key = bvo.getCsendstockorgid() + bvo.getCsendstordocid();
      bvo.setCcostorgid(m_CostRegion.get(key));
    }

  }

  private List<SquareInvViewVO> setCostOrgByTOSettleRule(
      Map<MatchSettleRuleVOForSo, MatchSettleRuleResult> m_index_cr,
      SquareInvViewVO[] svvos) {
    String costorgid = null;
    // 需要通过销售结算财务组织找成本域的vo
    List<SquareInvViewVO> l_ByFinanceList = new ArrayList<SquareInvViewVO>();
    for (SquareInvViewVO view : svvos) {
      SquareInvBVO bvo = view.getItem();
      MatchSettleRuleVOForSo para =
          this.getMatchSettleRuleVOForSo(view.getHead(), bvo);
      // 内部结算规则
      MatchSettleRuleResult mrlt = m_index_cr.get(para);
      if (mrlt == null) {
        ExceptionUtils
            .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
                .getStrByID("4006010_0", "04006010-0036")/*@res "跨组织销售没有匹配到内部交易结算规则！"*/);
      }
      else {
        costorgid = mrlt.getCostRegion();
        // 设置成本域
        if (costorgid == null) {
          l_ByFinanceList.add(view);
        }
        else {
          bvo.setCcostorgid(costorgid);
        }
      } // end else
    }
    return l_ByFinanceList;
  }

  private void setCostOrgForSingle(SquareInvViewVO[] singlevos) {
    if (null == singlevos || singlevos.length == 0) {
      return;
    }
    // 库存组织
    List<String> s_stockOrg = new ArrayList<String>();
    // 仓库
    List<String> s_storddoc = new ArrayList<String>();

    for (SquareInvViewVO view : singlevos) {
      // 库存组织
      String stockOrg = view.getItem().getCsendstockorgid();
      s_stockOrg.add(stockOrg);

      // 仓库
      String storddoc = view.getItem().getCsendstordocid();
      s_storddoc.add(storddoc);
    }

    String[] stockorgids = s_stockOrg.toArray(new String[s_stockOrg.size()]);
    String[] stordocids = s_storddoc.toArray(new String[s_storddoc.size()]);
    this.setCostOrgByStockOrgsAndStordocs(stockorgids, stordocids, singlevos);

  }

  private Map<MatchSettleRuleVOForSo, MatchSettleRuleResult> setCostOrgForSpan(
      SquareInvViewVO[] svvos) {
    Map<MatchSettleRuleVOForSo, MatchSettleRuleResult> m_index_cr =
        new HashMap<MatchSettleRuleVOForSo, MatchSettleRuleResult>();
    if (!VOChecker.isEmpty(svvos)) {
      // 获取内部结算规则
      m_index_cr = this.getMatchSettleRuleResult(svvos);
      this.setCostOrgBySpan(m_index_cr, svvos);
    }
    return m_index_cr;
  }

  private void setDataFromSaleOrder(SquareInvViewVO[] svvos) {
    String[] ordbids =
        SoVoTools.getVOsOnlyValues(SquareInvVOUtils.getInstance()
            .getSquareInvBVO(svvos), SquareInvBVO.CFIRSTBID);

    if (VOChecker.isEmpty(ordbids)) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006010_0", "04006010-0037")/*@res "数据错误：销售发票没有源头信息"*/);
    }

    Map<String, SaleOrderViewVO> map = new HashMap<String, SaleOrderViewVO>();
    try {
      SaleOrderViewVO[] views =
          SOSaleOrderServicesUtil.querySaleOrderViewVOs(ordbids, new String[] {
            SaleOrderBVO.CSALEORDERBID, SaleOrderBVO.CPRODLINEID,
            SaleOrderHVO.CCHANNELTYPEID, SaleOrderBVO.VCTCODE
          });

      if (VOChecker.isEmpty(views)) {
        ExceptionUtils
            .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
                .getStrByID("4006010_0", "04006010-0038")/*@res "数据错误：销售发票源头应该是销售订单"*/);
      }

      for (SaleOrderViewVO view : views) {
        map.put(view.getBody().getCsaleorderbid(), view);
      }
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }

    // cchanneltypeid cprolineid
    for (SquareInvViewVO view : svvos) {
      SaleOrderViewVO m30view = map.get(view.getItem().getCfirstbid());
      if (!VOChecker.isEmpty(m30view)) {
        SquareInvBVO bvo = view.getItem();
        bvo.setCchanneltypeid(m30view.getHead().getCchanneltypeid());
        bvo.setCprolineid(m30view.getBody().getCprodlineid());
        bvo.setVctcode(m30view.getBody().getVctcode());
      }
    }
  }

  private void setEffectdate(SquareInvViewVO[] svvos) {
    String[] paytermids =
        SoVoTools.getVOsOnlyValues(SquareInvVOUtils.getInstance()
            .getSquareInvHVO(svvos), SquareInvHVO.CPAYTERMID);
    Map<String, IncomeVO> map = null;
    if (!VOChecker.isEmpty(paytermids)) {
      map = PaytermService.queryIncomeByPk(paytermids);
    }
    if (VOChecker.isEmpty(map)) {
      // 无收付款协议，直接取单据日期
      this.setEffectdateWithNoPayterm(svvos);
    }
    else {
      this.setEffectdateWithPayterm(map, svvos);
    }
  }

  private void setEffectdateWithNoPayterm(SquareInvViewVO[] svvos) {
    for (SquareInvViewVO view : svvos) {
      view.getItem().setDeffectdate(view.getHead().getDbilldate());
    }
  }

  private void setEffectdateWithPayterm(Map<String, IncomeVO> map,
      SquareInvViewVO[] svvos) {
    for (SquareInvViewVO view : svvos) {
      IncomeVO pay = map.get(view.getHead().getCpaytermid());
      UFDate deffdate = pay.getEffectdate();
      view.getItem().setDeffectdate(deffdate);
    }
  }

}
