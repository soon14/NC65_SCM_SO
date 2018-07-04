package nc.pubimpl.so.m30.mmpps.planbusi;

import nc.pubitf.so.m30.mmpps.planbusi.IQuerySaleOrderMapForSA;
import nc.pubitf.so.m30.mmpps.planbusi.mapvo.SaleOrderSADemandMapVO;
import nc.vo.pub.BusinessException;

public class QuerySaleOrderMapForSAImpl implements IQuerySaleOrderMapForSA {

  @Override
  public SaleOrderSADemandMapVO getSADemandMapVO() throws BusinessException {
    SaleOrderSADemandMapVO saDemand = new SaleOrderSADemandMapVO();
    return saDemand;
  }
}
