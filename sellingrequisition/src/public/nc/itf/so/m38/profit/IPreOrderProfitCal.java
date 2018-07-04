package nc.itf.so.m38.profit;

import nc.vo.pub.BusinessException;
import nc.vo.so.entry.ProfitVO;

/**
 * 预订单毛利预估——功能/算法概述
 * <ol>
 * <li>根据传入的预订单头ID的集合计算所有订单行进行毛利预估
 * </ol>
 * 
 * @since 6.0
 * @version 2011-7-14 上午10:05:00
 * @author 旷宗义
 * @see
 */
public interface IPreOrderProfitCal {

  /**
   * 毛利计算需要批量获取成本域和成本单价
   * 
   * @param hids 预订单头ID数组
   * @return 计算好的毛利VO
   * @throws BusinessException
   * @see
   */
  ProfitVO[] caculate38Profit(String[] hids) throws BusinessException;

}
