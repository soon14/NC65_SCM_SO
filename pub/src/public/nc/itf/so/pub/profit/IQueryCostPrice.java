package nc.itf.so.pub.profit;

import java.util.Map;

import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;

/**
 * 毛利计算需要批量获取成本域和成本单价——功能/算法概述
 * <ol>
 * <li>根据库存组织和仓库批量获取成本域
 * <li>根据获取的成本域、物料和批次批量获取成本单价
 * </ol>
 * <b>代码示例：</b>样例说明
 * 
 * <pre>
 * 代码片断
 * </pre>
 * 
 * @since 6.0
 * @version 2011-7-13 下午15:05:00
 * @author 旷宗义
 * @see
 */

public interface IQueryCostPrice {

  /**
   * 毛利计算需要批量获取成本域和成本单价
   * 
   * @param cstockorgids 库存组织ID数组
   * @param cstordocids 仓库ID数组
   * @param cmaterialids 物料ID数组
   * @param vbatchs 批次数组
   * @return Map<Key：库存组织+仓库+物料+批次，Value：成本单价>
   * @throws BusinessException
   * @see
   */

  Map<String, UFDouble> queryCostPrice(String[] cstockorgids,
      String[] cstordocids, String[] cmaterialids, String[] vbatchs)
      throws BusinessException;
}
