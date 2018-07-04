package nc.pf.so.function;

import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.pubitf.ic.split.ISplitBillByStoreAdmin;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.so.m4331.entity.DeliveryBVO;

public class DeliverySplitByStoreAdmin {
  public List<String> splitByStoreAdmin(AggregatedValueObject vo)
      throws BusinessException {
    ISplitBillByStoreAdmin service =
        NCLocator.getInstance().lookup(ISplitBillByStoreAdmin.class);
    return service.splitByStoreAdmin(vo, new String[] {
      DeliveryBVO.CSENDSTOCKORGID, DeliveryBVO.CSENDSTORDOCID,
      DeliveryBVO.CMATERIALID
    });
  }
}
