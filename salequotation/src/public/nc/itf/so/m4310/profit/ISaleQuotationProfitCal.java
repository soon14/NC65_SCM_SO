package nc.itf.so.m4310.profit;

import nc.vo.pub.BusinessException;
import nc.vo.so.entry.ProfitVO;

/**
 * 销售报价单毛利预估——功能/算法概述
 * 
 * 根据传入的销售报价单头ID的集合计算所有订单行进行毛利预估
 * 
 * @since 6.0
 * @version 2011-9-2 上午10:54:51
 * @author 么贵敬
 */
public interface ISaleQuotationProfitCal {

  /**
   * 毛利计算需要批量获取成本域和成本单价
   * 
   * @param hids 销售订单头ID数组
   * @return 计算好的毛利VO
   * @throws BusinessException
   * @see
   */
  ProfitVO[] caculate4310Profit(String[] hids) throws BusinessException;

}
