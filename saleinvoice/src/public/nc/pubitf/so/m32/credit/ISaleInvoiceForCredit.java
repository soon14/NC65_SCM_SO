package nc.pubitf.so.m32.credit;

import java.util.Map;

import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;

/**
 * 销售发票给信用提供的接口
 * 
 * @since 6.0
 * @version 2011-6-27 下午05:17:31
 * @author 么贵敬
 */
public interface ISaleInvoiceForCredit {
  /**
   * 根据发票ID查询单据日期和审核日期
   * 
   * @param bids 销售发票行id数组
   * @return Map<String,UFDate[]> --- <销售发票行id,UFDate[0]:单据日期 UFDate[1]:审核日期>
   * @throws BusinessException
   */
  Map<String, UFDate[]> getBusiDateBy32Bids(String[] bids)
      throws BusinessException;

  /**
   * 根据出库单ID查询单据日期和审核日期
   * 
   * @param bids 销售出库单行id数组
   * @return
   * @throws BusinessException
   */
  Map<String, UFDate[]> getBusiDateBy4CBids(String[] bids)
      throws BusinessException;
}
