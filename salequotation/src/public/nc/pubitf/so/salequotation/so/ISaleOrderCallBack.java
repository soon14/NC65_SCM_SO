package nc.pubitf.so.salequotation.so;

import nc.vo.pub.BusinessException;
import nc.vo.so.salequotation.entity.QuatationRewritePara;

/**
 * 销售订单参照报价单时回写生成销售订单数量服务,提供给销售订单的服务
 * 
 * @author chenyyb
 * 
 */
public interface ISaleOrderCallBack {

  void saleOrderCallBack(QuatationRewritePara[] vos) throws BusinessException;
}
