package nc.pubimpl.so.m30.mmdp.dt;

import nc.pubitf.so.m30.mmdp.dt.IQuerySaleOrderMapForDT;
import nc.pubitf.so.m30.mmdp.dt.mapvo.SaleOrderDTDemandMapVO;
import nc.vo.pub.BusinessException;

public class QuerySaleOrderMapForDTImpl implements IQuerySaleOrderMapForDT {

  @Override
  public SaleOrderDTDemandMapVO getDTDemandMapVO() throws BusinessException {
    SaleOrderDTDemandMapVO dtDemand = new SaleOrderDTDemandMapVO();
    return dtDemand;
  }

}
