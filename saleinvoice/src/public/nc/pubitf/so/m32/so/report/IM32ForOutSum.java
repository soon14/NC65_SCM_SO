package nc.pubitf.so.m32.so.report;

import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.pub.MapList;
import nc.vo.so.m32.entity.SaleInvoiceBVO;

/**
 * 销售发票为销售出库出库提供的借口
 * 
 * @since 6.3
 * @version 2012-08-28 09:59:40
 * @author 梁吉明
 */
public interface IM32ForOutSum {

  /**
   * 
   * @param outhids
   * @param outbids
   * @return
   * @throws BusinessException
   */
  MapList<String, SaleInvoiceBVO> queryInvoiceFromOut(String[] outhids,
      String[] outbids) throws BusinessException;
}
