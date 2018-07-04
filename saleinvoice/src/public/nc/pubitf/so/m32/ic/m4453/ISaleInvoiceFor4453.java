package nc.pubitf.so.m32.ic.m4453;

import nc.vo.pub.BusinessException;

/**
 * 销售发票给签收途损单的接口
 * 
 * @since 6.0
 * @version 2011-9-7 上午10:20:06
 * @author 么贵敬
 */
public interface ISaleInvoiceFor4453 {

  /**
   * 判断签收途损单 是否有下游发票
   * 
   * @param ids 出库单表头ID
   * @param bids 出库单表体id
   * @throws BusinessException
   */
  void isHasDest(String[] ids, String[] bids) throws BusinessException;
}
