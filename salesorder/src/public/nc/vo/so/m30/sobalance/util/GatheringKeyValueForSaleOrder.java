package nc.vo.so.m30.sobalance.util;

import nc.vo.pub.lang.UFDouble;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.pub.keyvalue.IKeyValue;

public class GatheringKeyValueForSaleOrder extends AbstractGatheringKeyValue {

  private IKeyValue keyValue;

  public GatheringKeyValueForSaleOrder(IKeyValue keyValue) {
    this.keyValue = keyValue;
  }

  @Override
  public String getPk_org() {
    return this.keyValue.getBodyStringValue(0, SaleOrderBVO.CARORGID);
  }

  @Override
  public String getPk_currtype() {
    return this.keyValue.getHeadStringValue(SaleOrderHVO.CORIGCURRENCYID);
  }

  @Override
  public String getCustomer() {
    return this.keyValue.getHeadStringValue(SaleOrderHVO.CINVOICECUSTID);
  }

  @Override
  public String getSo_org() {
    return this.keyValue.getHeadStringValue(SaleOrderHVO.PK_ORG);
  }

  @Override
  public String getSo_ordertype() {
    return this.keyValue.getHeadStringValue(SaleOrderHVO.VTRANTYPECODE);
  }

  @Override
  public String getSett_org() {
    return this.keyValue.getBodyStringValue(0, SaleOrderBVO.CSETTLEORGID);
  }

  @Override
  public String getSo_deptid() {
    return this.keyValue.getHeadStringValue(SaleOrderHVO.CDEPTID);
  }

  @Override
  public String getSo_psndoc() {
    return this.keyValue.getHeadStringValue(SaleOrderHVO.CEMPLOYEEID);
  }

  @Override
  public String getSo_transtype() {
    return this.keyValue.getHeadStringValue(SaleOrderHVO.CCHANNELTYPEID);
  }

  @Override
  public String getOrdercubasdoc() {
    return this.keyValue.getHeadStringValue(SaleOrderHVO.CCUSTOMERID);
  }

  @Override
  public String[] getProductlines() {
    int rowCount = this.keyValue.getBodyCount();
    String[] cprodlineids = new String[rowCount];
    for (int i = 0; i < rowCount; i++) {
      cprodlineids[i] =
          this.keyValue.getBodyStringValue(i, SaleOrderBVO.CPRODLINEID);
    }
    return cprodlineids;
  }

  @Override
  public UFDouble getMoney() {
    return this.keyValue.getHeadUFDoubleValue(SaleOrderHVO.NTOTALORIGMNY);
  }
}
