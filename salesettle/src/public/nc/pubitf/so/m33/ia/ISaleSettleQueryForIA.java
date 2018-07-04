package nc.pubitf.so.m33.ia;

import nc.vo.ic.m4c.entity.SaleOutHeadVO;
import nc.vo.pub.BusinessException;
import nc.vo.scmpub.parameter.ia.QueryParaVO;
import nc.vo.so.m32.entity.SaleInvoiceHVO;

/**
 * 
 * 销售结算提供给存货核算的查询接口
 * 
 * @since 6.36
 * @version 2014-12-3 下午2:19:10
 * @author zhangby5
 */
public interface ISaleSettleQueryForIA {

  /**
   * 表头开票日期在当前会计期间，开票组织等于当前成本域对应的财务组织，自由态的销售发票表头
   * 
   * @param paraVO 关帐检查查询参数vo
   * @return 自由态的销售发票表头
   * @throws BusinessException
   */
  public SaleInvoiceHVO[] queryFreeSaleInvoiceHVO(QueryParaVO paraVO)
      throws BusinessException;

  /**
   * 表头开票日期在当前会计期间，财务组织等于当前业务单元，已审批未结算的销售出库单
   * 
   * @param paraVO 关帐检查查询参数vo
   * @return 已审批未结算的销售出库单表头
   * @throws BusinessException
   */
  public SaleOutHeadVO[] queryUnSquareSaleOutHeadVO(QueryParaVO paraVO)
      throws BusinessException;

}
