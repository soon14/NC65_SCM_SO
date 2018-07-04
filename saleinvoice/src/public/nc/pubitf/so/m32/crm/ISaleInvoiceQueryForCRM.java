package nc.pubitf.so.m32.crm;

import nc.vo.pub.BusinessException;
import nc.vo.so.m32.entity.SaleInvoiceBVO;
import nc.vo.so.m32.entity.SaleInvoiceHVO;

import nc.pubitf.so.m4310.crm.CRMQueryPara;

/**
 * 为CRM提供的销售发票查询接口
 * 
 * @since 6.3.1
 * @version 2013-08-06 20:27:17
 * @author 张云枫
 */
public interface ISaleInvoiceQueryForCRM {

  /**
   * 根据CRM参数对象查询销售发票
   * 
   * @param queryPara CRM参数对象
   * 
   * @return 销售发票表头VO
   * @throws BusinessException
   */
  public SaleInvoiceHVO[] querySaleInvoice(CRMQueryPara queryPara)
      throws BusinessException;

  /**
   * 根据主键查询销售发票
   * 
   * @param id 销售订单主键ID
   * 
   * @return 销售订单表体VO
   * @throws BusinessException
   */
  public SaleInvoiceBVO[] querySaleInvoiceById(String id)
      throws BusinessException;
}
