package nc.pubimpl.so.m4331.to.m5x;

import java.util.Map;

import nc.pubimpl.so.m4331.pub.PubDeliveryIsReverse;
import nc.pubitf.so.m4331.to.m5x.IDeliveryFor5X;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;

public class DeliveryFor5XImpl implements IDeliveryFor5X {

  @Override
  public Map<String, UFBoolean> queryReverseFlag(String[] bids)
      throws BusinessException {
    return new PubDeliveryIsReverse().queryReverseFlag(bids);
  }
}
