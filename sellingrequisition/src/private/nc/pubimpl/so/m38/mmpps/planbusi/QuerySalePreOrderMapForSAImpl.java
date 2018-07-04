package nc.pubimpl.so.m38.mmpps.planbusi;

import nc.pubitf.so.m38.mmpps.planbusi.IQuerySalePreOrderMapForSA;
import nc.pubitf.so.m38.mmpps.planbusi.mapvo.SalePreOrderSADemandMapVO;
import nc.vo.pub.BusinessException;

public class QuerySalePreOrderMapForSAImpl implements
    IQuerySalePreOrderMapForSA {

  @Override
  public SalePreOrderSADemandMapVO getSADemandMapVO() throws BusinessException {
    SalePreOrderSADemandMapVO saDemand = new SalePreOrderSADemandMapVO();
    return saDemand;
  }
}
