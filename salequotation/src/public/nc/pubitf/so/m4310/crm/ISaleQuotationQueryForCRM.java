package nc.pubitf.so.m4310.crm;

import nc.vo.pub.BusinessException;
import nc.vo.so.salequotation.entity.SalequotationBVO;
import nc.vo.so.salequotation.entity.SalequotationHVO;

/**
 * 为CRM提供的报价单查询接口
 * 
 * @since 6.3.1
 * @version 2013-08-06 08:41:07
 * @author 张云枫
 */
public interface ISaleQuotationQueryForCRM {

  /**
   * 根据CRM参数对象查询销售报价单表头字段
   * 
   * @param queryPara CRM参数对象
   * 
   * @return SalequotationHVO[] 报价单表头VO
   * @throws BusinessException
   */
  SalequotationHVO[] querySaleQuotationVOs(CRMQueryPara queryPara)
      throws BusinessException;

  /**
   * 根据报价单主键ID查询出报价单聚合VO
   * 
   * @param id 报价单主键ID
   * @return 报价单表体VO
   * @throws BusinessException
   */
  SalequotationBVO[] querySaleQuotationVOsById(String id)
      throws BusinessException;
}
