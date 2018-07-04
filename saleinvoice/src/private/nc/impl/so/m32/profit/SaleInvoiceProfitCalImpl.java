package nc.impl.so.m32.profit;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.so.entry.ProfitVO;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m32.entity.SaleInvoiceBVO;
import nc.vo.so.m32.entity.SaleInvoiceHVO;
import nc.vo.so.m32.entity.SaleInvoiceVO;
import nc.vo.so.pub.rule.CostRegionPara;
import nc.vo.so.pub.rule.SOCostRegionRule;
import nc.vo.so.pub.util.ProfitCaculateUtil;

import nc.itf.so.m32.profit.ISaleInvoiceProfitCal;

import nc.impl.pubapp.pattern.data.bill.BillQuery;

/**
 * 销售发票毛利预估——功能/算法概述
 * <ol>
 * <li>根据传入的销售发票头ID的集合计算所有订单行进行毛利预估
 * <li>
 * </ol>
 * <b>代码示例：</b>样例说明
 * 
 * <pre>
 * 代码片断
 * </pre>
 * 
 * @since 6.0
 * @version 2011-7-13 下午16:05:00
 * @author 旷宗义
 * @see ISaleInvoiceProfitCal
 */

public class SaleInvoiceProfitCalImpl implements ISaleInvoiceProfitCal {

  @Override
  public ProfitVO[] caculate32Profit(String[] hids) throws BusinessException {
    // 查询销售发票VO
    SaleInvoiceVO[] bills = null;
    BillQuery<SaleInvoiceVO> query =
        new BillQuery<SaleInvoiceVO>(SaleInvoiceVO.class);
    bills = query.query(hids);
    // 初始化返回毛利VO
    List<ProfitVO> vievowlist = new ArrayList<ProfitVO>();
    
    CostRegionPara[] paras = null;
    for (SaleInvoiceVO vo : bills) {
      SaleInvoiceBVO[] bvos = vo.getChildrenVO();
      SaleInvoiceHVO hvo = vo.getParentVO();
      paras = new CostRegionPara[bvos.length];
      int i = 0;
      for (SaleInvoiceBVO bvo : bvos) {
        paras[i] = new CostRegionPara();
        paras[i].setCinfinanceorgid(hvo.getPk_org());
        paras[i].setCmaterialid(bvo.getCmaterialid());
        paras[i].setOrdertantype(bvo.getVfirsttrantype());
        paras[i].setStockorgid(bvo.getCsendstockorgid());
        paras[i].setStordocid(bvo.getCsendstordocid());
        i++;
      }
    }
    SOCostRegionRule rule = new SOCostRegionRule();
    Map<String, String> costregionmap = rule.queryCostRegion(paras);
    
    

    // 设置毛利VO基础数据
    for (SaleInvoiceVO vo : bills) {
      SaleInvoiceHVO hvo = vo.getParentVO();
      SaleInvoiceBVO[] bvos = vo.getChildrenVO();
      for (SaleInvoiceBVO bvo : bvos) {
        ProfitVO profitvo = new ProfitVO();
        profitvo.setCstockorgid(bvo.getCsendstockorgid());
        profitvo.setCstordocid(bvo.getCsendstordocid());
        profitvo.setCmaterialid(bvo.getCmaterialid());
        profitvo.setCmaterialvid(bvo.getCmaterialvid());
        profitvo.setNastnum(bvo.getNnum());
        profitvo.setVbatchcode(bvo.getVbatchcode());
        profitvo.setNqtorignetprice(bvo.getNnetprice());
        profitvo.setCastunitid(bvo.getCunitid());
        profitvo.setCorigcurrencyid(hvo.getCcurrencyid());
        UFDouble totalincome = bvo.getNmny();
        if (bvo.getBlargessflag().booleanValue()) {
          totalincome = UFDouble.ZERO_DBL;
        }
        profitvo.setNtotalincome(totalincome);
        profitvo.setPk_org(bvo.getCsaleorgid());
        profitvo.setCfinanceorg(bvo.getPk_org());
        profitvo.setBlargessflag(bvo.getBlargessflag());
        
        profitvo.setOrdertantype(bvo.getVfirsttrantype());
        // 获得成本域
        String costparakey =
            rule.getCostRegionMapKey(profitvo.getCfinanceorg(),
                profitvo.getCmaterialid(), profitvo.getOrdertantype(),
                profitvo.getCstockorgid(), profitvo.getCstordocid());
        String pk_costregion = costregionmap.get(costparakey);
        profitvo.setPk_costregion(pk_costregion);
        vievowlist.add(profitvo);
      }
    }
    // 批量调用取成本域和成本单价接口计算毛利
    ProfitCaculateUtil cacProfigUtil = new ProfitCaculateUtil(vievowlist);
    cacProfigUtil.caculateProfit();

    ProfitVO[] profitvos = vievowlist.toArray(new ProfitVO[vievowlist.size()]);
    return profitvos;
  }
}
