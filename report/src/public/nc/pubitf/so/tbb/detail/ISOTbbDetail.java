package nc.pubitf.so.tbb.detail;

import nc.vo.pub.BusinessException;
import nc.vo.so.m30.entity.SaleOrderViewVO;
import nc.vo.so.m32.entity.SaleInvoiceViewVO;
import nc.vo.tb.obj.NtbParamVO;

/**
 * 预算联查销售订单
 * 
 * @since 6.0
 * @version 2011-6-24 下午04:17:57
 * @author 祝会征
 */
public interface ISOTbbDetail {
  // TODO 冯加滨 2012.03.14
  SaleOrderViewVO[] getSaleorderDetail(NtbParamVO ntbparamvos)
      throws BusinessException;

  SaleInvoiceViewVO[] getInvoiceDetail(NtbParamVO ntbparamvos)
      throws BusinessException;

}
