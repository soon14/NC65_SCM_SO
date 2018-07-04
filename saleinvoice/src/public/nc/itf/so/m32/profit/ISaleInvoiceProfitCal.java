package nc.itf.so.m32.profit;

import nc.vo.pub.BusinessException;
import nc.vo.so.entry.ProfitVO;

/**
 * 销售发票毛利预估——功能/算法概述
 * <ol>
 * <li>根据传入的销售票订单头ID的集合计算所有订单行进行毛利预估
 * <li>
 * </ol>
 * <b>代码示例：</b>样例说明
 * 
 * <pre>
 * 代码片断
 * </pre>
 * 
 * @since 6.0
 * @version 2011-7-14 上午9:05:00
 * @author 旷宗义
 */
public interface ISaleInvoiceProfitCal {

  /**
   * 毛利计算需要批量获取成本域和成本单价
   * 
   * @param hids 销售发票头ID数组
   * @return 计算好的毛利VO
   * @throws BusinessException
   */
  ProfitVO[] caculate32Profit(String[] hids) throws BusinessException;

}
