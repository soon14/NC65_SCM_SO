package nc.pubitf.so.m33.so.m30;

import java.util.Map;

/**
 * 销售结算提供给销售订单结算关闭查询接口
 * 
 * @since 6.0
 * @version 2011-8-4 上午10:58:17
 * @author zhangcheng
 */
public interface IQuerySquareBillFor30SqEnd {

  /**
   * 查询当前业务流程中结算单据类型，销售出库单结算或者销售发票结算
   * 
   * @param orderbids 销售订单行id(不可重复)
   * @param busiids 业务流程id (不可重复)
   * @return (业务流程ID,结算单据类型[]{0 应收结算单据类型 1 成本结算单据类型} )
   *         如果没有参与结算相应的值为null
   */
  Map<String, String[]> querySquareBillFor30SqEnd(String[] orderbids,
      String[] busiids);

}
