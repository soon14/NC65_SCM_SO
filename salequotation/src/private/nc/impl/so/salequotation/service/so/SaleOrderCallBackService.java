package nc.impl.so.salequotation.service.so;

import nc.bs.so.salequotation.bp.RewriteBillOperateBP;
import nc.bs.so.salequotation.bp.RewriteSaleOrderNumBP;
import nc.pubitf.so.salequotation.so.ISaleOrderCallBack;
import nc.vo.so.salequotation.entity.QuatationRewritePara;

/**
 * 销售订单参照报价单时回写生成销售订单数量服务,提供给销售订单的服务
 * 
 * @author chenyyb
 * 
 */
public class SaleOrderCallBackService implements ISaleOrderCallBack {

  @Override
  public void saleOrderCallBack(QuatationRewritePara[] vos) {
    new RewriteSaleOrderNumBP().rewriteSaleOrderNum(vos);
    new RewriteBillOperateBP().operateBill(vos);
  }

}
