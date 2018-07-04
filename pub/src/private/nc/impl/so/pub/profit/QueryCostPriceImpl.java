package nc.impl.so.pub.profit;

import java.util.HashMap;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.itf.scmpub.ia.ICostPriceQuery;
import nc.itf.scmpub.reference.uap.org.CostRegionPubService;
import nc.itf.so.pub.profit.IQueryCostPrice;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;

public class QueryCostPriceImpl implements IQueryCostPrice {

  /**
   * 方法功能描述：毛利计算需要批量获取成本域和成本单价
   * 
   * @param cstockorgids 库存组织ID数组
   * @param cstordocids 仓库ID数组
   * @param cmaterialids 物料ID数组
   * @param vbatchs 批次数组
   * @return Map<Key：库存组织+仓库+物料+批次，Value：成本单价>
   * @since 6.0
   * @author 旷宗义
   * @throws BusinessException
   * @time 2011-7-12 下午18:54:31
   */
  @Override
  public Map<String, UFDouble> queryCostPrice(String[] cstockorgids,
      String[] cstordocids, String[] cmaterialids, String[] vbatchs)
      throws BusinessException {
    Map<String, UFDouble> costpricemap = new HashMap<String, UFDouble>();
    ICostPriceQuery control =
        NCLocator.getInstance().lookup(ICostPriceQuery.class);
    // 获取初始化数组长度
    int intCount = cmaterialids.length;
    // 初始化成本域、物料和批次参数数组
    String[] pk_orgs = new String[intCount];
    // 根据库存组织和仓库批量获取成本域
    Map<String, String> costmap =
        CostRegionPubService.queryCostRegionIDByStockOrgsAndStordocs(
            cstockorgids, cstordocids);
    // 初始化成本域数组
    for (int i = 0; i < intCount; i++) {
      pk_orgs[i] = costmap.get(cstockorgids[i] + cstordocids[i]);
    }

    /*
     * // 调用获取成本单价接口
     * UFDouble[] icprice = control.getCostPrice(pk_orgs, cmaterialids,
     * vbatchs);
     * for (int i = 0; i < intCount; i++) {
     * String strKey =
     * cstockorgids[i] + cstordocids[i] + cmaterialids[i] + vbatchs[i];
     * if (!costpricemap.containsKey(strKey)) {
     * costpricemap.put(strKey, icprice[i]);
     * }
     * }
     */

    // 调用获取成本单价接口-

    Map<String, UFDouble> icpricemap =
        control.getCostPriceMap(pk_orgs, cmaterialids, vbatchs);
    for (int i = 0; i < intCount; i++) {
      String strKey =
          cstockorgids[i] + cstordocids[i] + cmaterialids[i] + vbatchs[i];
      UFDouble costprice =
          icpricemap.get(costmap.get(cstockorgids[i] + cstordocids[i]) + "|"
              + cmaterialids[i] + "|" + vbatchs[i]);
      if (!costpricemap.containsKey(strKey)) {
        costpricemap.put(strKey, costprice);
      }
    }

    return costpricemap;
  }
}
