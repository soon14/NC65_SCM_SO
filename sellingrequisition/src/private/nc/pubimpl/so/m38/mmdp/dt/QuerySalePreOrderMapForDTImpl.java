package nc.pubimpl.so.m38.mmdp.dt;

import nc.pubitf.so.m38.mmdp.dt.mapvo.SalePreOrderDTDemandMapVO;
import nc.vo.pub.BusinessException;

public class QuerySalePreOrderMapForDTImpl implements
    nc.pubitf.so.m38.mmdp.dt.IQuerySalePreOrderMapForDT {

  @Override
  public SalePreOrderDTDemandMapVO getDTDemandMapVO() throws BusinessException {
    SalePreOrderDTDemandMapVO dtDemand = new SalePreOrderDTDemandMapVO();
    return dtDemand;
  }
}
