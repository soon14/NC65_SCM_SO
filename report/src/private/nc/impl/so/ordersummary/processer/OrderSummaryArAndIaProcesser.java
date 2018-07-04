package nc.impl.so.ordersummary.processer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.bs.ml.NCLangResOnserver;
import nc.impl.so.pub.summary.util.ReportSummaryARForSOUtil;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.itf.scmpub.reference.uap.org.StockOrgPubService;
import nc.itf.so.report.RemoteCallService;
import nc.pubitf.arap.receivable.IArapReceivableBillPubServiceForSCM;
import nc.pubitf.so.m32.so.m30.IQuery32For30;
import nc.vo.arap.itfvo.ReceivableBillInfoVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.scmpub.parameter.AskCostPriceParaVO;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.pub.votools.SoVoTools;
import nc.vo.so.report.ordersummary.OrderSummaryViewVO;

/**
 * 处理收入和成本信息
 * 
 * @since 6.3
 * @version 2012-10-18 14:37:44
 * @author 梁吉明
 */
public class OrderSummaryArAndIaProcesser {

  /**
   * 收入和成本信息
   * 
   * @param views
   */
  public void queryArAndIa(OrderSummaryViewVO[] views) {
    // 1.取表头id与表体id
    String[] orderheadids = this.getOrderHeadIds(views);
    String[] orderbodyids = this.getOrderBodyIds(views);
    // 2.填写开票信息
    this.setInvoiceMny(views, orderheadids, orderbodyids);
    // 3.取出库单收入和成本信息，并赋值给VO
    this.setOrderArAndIa(views, orderheadids, orderbodyids);
  }

  private void setInvoiceMny(OrderSummaryViewVO[] views, String[] orderheadids,
      String[] orderbodyids) {
    Map<String, UFDouble> mapinvoice =
        this.getInvoiceIds(orderheadids, orderbodyids);
    if (null != mapinvoice && mapinvoice.size() != 0) {
      for (OrderSummaryViewVO view : views) {
        if (view.getBlargessflag().equals(UFBoolean.TRUE)) {
          view.setNinvoiceorigtaxmny(UFDouble.ZERO_DBL);
        }
        else {
          String orderbid = view.getCsaleorderbid();
          if (mapinvoice.containsKey(orderbid)) {
            UFDouble totalinvmny = mapinvoice.get(orderbid);
            view.setNinvoiceorigtaxmny(totalinvmny);
          }
        }
      }
    }
  }

  private Map<String, UFDouble> getInvoiceIds(String[] orderheadids,
      String[] orderbodyids) {
    IQuery32For30 m32srv = NCLocator.getInstance().lookup(IQuery32For30.class);
    Map<String, UFDouble> mapinvoice = null;
    try {
      mapinvoice = m32srv.getInvoiceNorigtaxmny(orderheadids, orderbodyids);
      return mapinvoice;
    }
    catch (BusinessException ex) {
      ExceptionUtils.wrappException(ex);
    }
    return mapinvoice;

  }

  private void setOrderArAndIa(OrderSummaryViewVO[] views,
      String[] orderheadids, String[] orderbodyids) {
    this.setOrderArInfor(views, orderheadids, orderbodyids);
    this.setOrderIaInfor(views, orderbodyids);
  }

  private void setOrderIaInfor(OrderSummaryViewVO[] views, String[] orderbodyids) {

    List<AskCostPriceParaVO> costpricelist =
        new ArrayList<AskCostPriceParaVO>();
    Map<String, String> m_st_fin =
        StockOrgPubService.queryFinanceOrgIDByStockOrgID(SoVoTools
            .getVOsOnlyValues(views, SaleOrderBVO.CSENDSTOCKORGID));
    // 库存组织对应的结算财务组织
    String cendstordocfin = null;

    // 拼取成本价的参数
    for (OrderSummaryViewVO view : views) {
      Object ob = view.getAttributeValue(SaleOrderBVO.CMATERIALID);
      AskCostPriceParaVO costparavo = new AskCostPriceParaVO();
      costparavo.setCinventoryid(null == ob ? null : ob.toString());
      ob = view.getAttributeValue(SaleOrderBVO.PK_ORG);
      costparavo.setPk_org(null == ob ? null : ob.toString());
      // 订单结算财务组织
      String orderfin =
          (String) view.getAttributeValue(SaleOrderBVO.CSETTLEORGID);
      costparavo.setPk_financeorg(null == ob ? null : ob.toString());
      // 单组织销售传库存组织和仓库 、跨组织的时候不传，就去结算财务组织的成本
      ob = view.getAttributeValue(SaleOrderBVO.CSENDSTOCKORGID);
      cendstordocfin = m_st_fin.get(ob);
      // 单组织才需要传库存组织和仓库来取成本
      if (orderfin.equals(cendstordocfin)) {
        costparavo.setPk_stockorg(null == ob ? null : ob.toString());
        ob = view.getAttributeValue(SaleOrderBVO.CSENDSTORDOCID);
        costparavo.setPk_stordoc(null == ob ? null : ob.toString());
      }
      ob = view.getAttributeValue(SaleOrderBVO.VBATCHCODE);
      costparavo.setVbatch(null == ob ? null : ob.toString());
      costpricelist.add(costparavo);
    }

    // 取成本价
    Map<String, UFDouble> costprice =
        RemoteCallService.getPriceBySCM02(costpricelist
            .toArray(new AskCostPriceParaVO[costpricelist.size()]));

    // 取销售订单行传入存货核算的已成本计算的金额和未成本计算的数量
    Map<String, UFDouble[]> iainfor =
        RemoteCallService.getSOReportCostMny(orderbodyids);

    Map<String, UFDouble> ntotalreceivmnymap = new HashMap<String, UFDouble>();
    // 启用应收模块
    if (SysInitGroupQuery.isAREnabled()) {
      ntotalreceivmnymap = RemoteCallService.getNotaxForSoorder(orderbodyids);
    }

    for (OrderSummaryViewVO view : views) {
      String orderbid = view.getCsaleorderbid();
      if (null != iainfor && iainfor.containsKey(orderbid)) {
        // 计算要统计的金额数量字段
        this.addTotalKeys(view, costprice, iainfor, ntotalreceivmnymap,
            m_st_fin);
      }
    }
  }

  /**
   * 计算要统计的金额数量字段
   * 
   * @param viewvo
   * @param costprice
   * @param costmap
   * @param m_st_fin
   */
  private void addTotalKeys(OrderSummaryViewVO viewvo,
      Map<String, UFDouble> costprice, Map<String, UFDouble[]> costmap,
      Map<String, UFDouble> ntotalreceivmnymap, Map<String, String> m_st_fin) {

    String key = this.createKey(viewvo, m_st_fin);
    // 订单主数量
    UFDouble nordernnum = viewvo.getNnum();

    // 应收主数量(确认应收主数量（订单上他=确认+回冲）+暂估应收主数量）
    UFDouble ntotalreceivnum =
        MathTool.add(
            (UFDouble) viewvo.getAttributeValue(SaleOrderBVO.NTOTALESTARNUM),
            (UFDouble) viewvo.getAttributeValue(SaleOrderBVO.NTOTALARNUM));

    // 累计成本结算主数量
    UFDouble ntotalcostnum =
        costmap.get((String) viewvo
            .getAttributeValue(SaleOrderBVO.CSALEORDERBID))[2];

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

    viewvo.setNtotalcostmny(ntotalcostmny);
  }

  private String createKey(OrderSummaryViewVO viewvo,
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

  private UFDouble getNtotalcostmny(UFDouble ntotalsettlecostmny,
      UFDouble mainnum, UFDouble ntotalcostnum, UFDouble outprice) {
    UFDouble num = MathTool.sub(mainnum, ntotalcostnum);
    UFDouble mny = num.multiply(null == outprice ? new UFDouble(0) : outprice);
    return MathTool.add(ntotalsettlecostmny, mny);
  }

  /**
   * 主数量
   * 
   * @param view
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

  private void setOrderArInfor(OrderSummaryViewVO[] views,
      String[] orderheadids, String[] orderbodyids) {
    // add by wangshu6 for v636 报表立即执行报错 2015-1-28 先判断是否已用关联模块，
    if(!SysInitGroupQuery.isAPEnabled()){
      ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance().getStrByID("4006005_0", "04006005-0028")/*应收模块未启用！*/);
      return;
    }
    // end
    Map<String, ReceivableBillInfoVO> arinfo = null;
    IArapReceivableBillPubServiceForSCM arsrv =
        NCLocator.getInstance().lookup(
            IArapReceivableBillPubServiceForSCM.class);
    try {
      arinfo =
          arsrv.queryReceivableBillInfoBySrcBillExcludeTusun(orderheadids,
              orderbodyids);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    for (OrderSummaryViewVO view : views) {
      String orderbid = view.getCsaleorderbid();
      // 应收主数量 源头为本订单行的应收单主数量合计
      // 应收金额 源头为本订单行的应收单价税合计金额；
      // 应收余额 源头为本订单行的应收单余额合计；

      UFDouble narnum = null;
      UFDouble narremainmny = null;
      UFDouble localmoney = null;
      if (null != arinfo && arinfo.containsKey(orderbid)) {
        ReceivableBillInfoVO arbvo = arinfo.get(orderbid);
        narnum = MathTool.add(narnum, arbvo.getQuantity_de());
        // 应收单行.组织本币金额
        localmoney = MathTool.add(localmoney, arbvo.getLocal_money_de());
        // 应收单行.组织本币余额
        narremainmny = MathTool.add(narremainmny, arbvo.getLocal_money_bal());
      }
      view.setNshouldreceivnum(narnum);
      view.setNshouldreceivmny(localmoney);
      view.setNtotalreceivmny(narremainmny);
    }
  }

  private String[] getOrderBodyIds(OrderSummaryViewVO[] views) {
    Set<String> orderbodyid = new HashSet<String>();
    for (OrderSummaryViewVO view : views) {
      orderbodyid.add(view.getCsaleorderbid());
    }
    String[] orderbodyids = new String[orderbodyid.size()];
    orderbodyid.toArray(orderbodyids);
    return orderbodyids;
  }

  private String[] getOrderHeadIds(OrderSummaryViewVO[] views) {
    Set<String> orderheadid = new HashSet<String>();
    for (OrderSummaryViewVO view : views) {
      orderheadid.add(view.getCsaleorderid());
    }
    String[] orderheadids = new String[orderheadid.size()];
    orderheadid.toArray(orderheadids);
    return orderheadids;
  }
}
