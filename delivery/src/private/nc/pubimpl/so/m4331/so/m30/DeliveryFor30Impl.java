package nc.pubimpl.so.m4331.so.m30;

import java.util.Map;

import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.so.m30.entity.SaleOrderViewVO;

import nc.pubitf.so.m4331.so.m30.IDeliveryFor30;
import nc.pubitf.so.m4331.so.m30.IDeliveryPriceParaFor30;

import nc.pubimpl.so.m4331.pub.PubDeliveryIsReverse;
import nc.pubimpl.so.m4331.so.m30.bp.QueryAppNumFor30BP;
import nc.pubimpl.so.m4331.so.m30.bp.Renovate4331PriceBP;
import nc.pubimpl.so.m4331.so.m30.bp.RewriteArsettleStateBP;

/**
 * 发货单给订单提供的接口类
 * 
 * @since 6.3
 * @version 2013-1-8 下午01:10:13
 * @author yaogj
 */
public class DeliveryFor30Impl implements IDeliveryFor30 {

  @Override
  public Map<String, UFBoolean> queryReverseFlag(String[] bids)
      throws BusinessException {
    return new PubDeliveryIsReverse().queryReverseFlag(bids);
  }

  @Override
  public void renovatePrice(Map<String, IDeliveryPriceParaFor30> paraMap)
      throws BusinessException {
    Renovate4331PriceBP bp = new Renovate4331PriceBP();
    bp.renovatePrice(paraMap);
  }

  @Override
  public Map<String, UFDouble> queryAppNum(String[] srcBids)
      throws BusinessException {
    return new QueryAppNumFor30BP().queryAppNum(srcBids);
  }

  @Override
  public void rewriteArSettle(SaleOrderViewVO[] viewvos)
      throws BusinessException {
    RewriteArsettleStateBP bp = new RewriteArsettleStateBP();
    bp.processArsettleState(viewvos);
  }
}
