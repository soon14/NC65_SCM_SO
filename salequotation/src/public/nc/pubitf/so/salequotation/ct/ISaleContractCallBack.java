package nc.pubitf.so.salequotation.ct;

import nc.vo.pub.BusinessException;
import nc.vo.so.salequotation.entity.QuatationRewritePara;

/**
 * 销售合同参照报价单时回写生成销售订单数量服务，提供给销售合同的服务
 * 
 * @author chenyyb
 * 
 */
public interface ISaleContractCallBack {

  void saleContractCallBack(QuatationRewritePara[] vos)
      throws BusinessException;
}
