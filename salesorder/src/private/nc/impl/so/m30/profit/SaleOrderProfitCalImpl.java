package nc.impl.so.m30.profit;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nc.impl.pubapp.pattern.data.bill.BillQuery;
import nc.itf.so.m30.profit.ISaleOrderProfitCal;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.so.entry.ProfitVO;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.pub.rule.CostRegionPara;
import nc.vo.so.pub.rule.SOCostRegionRule;
import nc.vo.so.pub.util.ProfitCaculateUtil;

/**
 * 销售订单毛利预估——功能/算法概述
 * <ol>
 * <li>根据传入的销售订单头ID的集合计算所有订单行进行毛利预估
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
 * @see
 */

public class SaleOrderProfitCalImpl implements ISaleOrderProfitCal {

  /**
   * 毛利计算需要批量获取成本域和成本单价
   * 
   * @param hids 销售订单头ID数组
   * @return 计算好的毛利VO
   * @throws BusinessException
   * @see
   */
  @Override
  public ProfitVO[] caculate30Profit(String[] hids) throws BusinessException {
    // 查询销售订单VO
    SaleOrderVO[] bills = null;
    BillQuery<SaleOrderVO> query =
        new BillQuery<SaleOrderVO>(SaleOrderVO.class);
    bills = query.query(hids);
    // 初始化返回毛利VO
    List<ProfitVO> vievowlist = new ArrayList<ProfitVO>();
    CostRegionPara[] paras = null;
    for (SaleOrderVO vo : bills) {
      SaleOrderBVO[] bvos = vo.getChildrenVO();
      SaleOrderHVO hvo = vo.getParentVO();
      paras = new CostRegionPara[bvos.length];
      int i = 0;
      for (SaleOrderBVO bvo : bvos) {
        paras[i] = new CostRegionPara();
        paras[i].setCinfinanceorgid(bvo.getCsettleorgid());
        paras[i].setCmaterialid(bvo.getCmaterialid());
        paras[i].setOrdertantype(hvo.getCtrantypeid());
        paras[i].setStockorgid(bvo.getCsendstockorgid());
        paras[i].setStordocid(bvo.getCsendstordocid());
        i++;
      }
    }
    SOCostRegionRule rule = new SOCostRegionRule();
    Map<String, String> costregionmap = rule.queryCostRegion(paras);
    
    for (SaleOrderVO vo : bills) {
      SaleOrderBVO[] bvos = vo.getChildrenVO();
      SaleOrderHVO hvo = vo.getParentVO();
      for (SaleOrderBVO bvo : bvos) {
        ProfitVO profitvo = new ProfitVO();
        profitvo.setCstockorgid(bvo.getCsendstockorgid());
        profitvo.setCstordocid(bvo.getCsendstordocid());
        profitvo.setCmaterialid(bvo.getCmaterialid());
        profitvo.setCmaterialvid(bvo.getCmaterialvid());
        // 改成了主数量
        profitvo.setNastnum(bvo.getNnum());
        profitvo.setVbatchcode(bvo.getVbatchcode());
        profitvo.setNqtorignetprice(bvo.getNnetprice());
        profitvo.setCastunitid(bvo.getCunitid());
        profitvo.setCorigcurrencyid(bvo.getCcurrencyid());
        UFDouble totalincome = bvo.getNmny();
        if (bvo.getBlargessflag().booleanValue()) {
          totalincome = UFDouble.ZERO_DBL;
        }
        profitvo.setNtotalincome(totalincome);
        profitvo.setPk_org(bvo.getPk_org());
        profitvo.setCfinanceorg(bvo.getCsettleorgid());
        profitvo.setBlargessflag(bvo.getBlargessflag());
        profitvo.setOrdertantype(hvo.getCtrantypeid());
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
