package nc.vo.so.m30.sobalance.util;

import java.util.ArrayList;
import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.pubitf.so.m30.so.balance.ISaleOrderForBalance;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m30.entity.SaleOrderViewVO;
import nc.vo.so.m30.sobalance.entity.SoBalanceHVO;
import nc.vo.so.pub.keyvalue.IKeyValue;

public class GatheringKeyValueForSoBalance extends AbstractGatheringKeyValue {

  private IKeyValue keyValue;

  private ISaleOrderForBalance service;

  public GatheringKeyValueForSoBalance(IKeyValue keyValue) {
    this.keyValue = keyValue;
  }

  @Override
  public String getPk_org() {
    return this.keyValue.getHeadStringValue(SoBalanceHVO.CARORGID);
  }

  @Override
  public String getPk_currtype() {
    return this.keyValue.getHeadStringValue(SoBalanceHVO.CORIGCURRENCYID);
  }

  @Override
  public String getCustomer() {
    return this.keyValue.getHeadStringValue(SoBalanceHVO.CINVOICECUSTID);
  }

  @Override
  public String getSo_org() {
    return this.keyValue.getHeadStringValue(SoBalanceHVO.PK_ORG);
  }

  @Override
  public String getSo_ordertype() {
    return this.keyValue.getHeadStringValue(SoBalanceHVO.VTRANTYPECODE);
  }

  @Override
  public String getSett_org() {
    return this.getCsettleorgid(this.keyValue
        .getHeadStringValue(SoBalanceHVO.CSALEORDERID));
  }

  @Override
  public String getSo_deptid() {
    return this.keyValue.getHeadStringValue(SoBalanceHVO.CDEPTID);
  }

  @Override
  public String getSo_psndoc() {
    return this.keyValue.getHeadStringValue(SoBalanceHVO.CEMPLOYEEID);
  }

  @Override
  public String getSo_transtype() {
    return this.keyValue.getHeadStringValue(SoBalanceHVO.CCHANNELTYPEID);
  }

  @Override
  public String getOrdercubasdoc() {
    return this.keyValue.getHeadStringValue(SoBalanceHVO.CCUSTOMERID);
  }

  @Override
  public String[] getProductlines() {
    SaleOrderViewVO[] views = null;
    try {
      views =
          this.getSaleOrderService().querySaleOrderViewVOByHIDs(new String[] {
            this.keyValue.getHeadStringValue(SoBalanceHVO.CSALEORDERID)
          });
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    List<String> idList = new ArrayList<String>();
    if (views != null && views.length > 0) {
      for (SaleOrderViewVO view : views) {
        idList.add(view.getBody().getCprodlineid());
      }
    }
    return idList.toArray(new String[idList.size()]);
  }

  @Override
  public UFDouble getMoney() {
    return this.keyValue.getHeadUFDoubleValue(SoBalanceHVO.NTOTALORIGTAXMNY);
  }

  private String getCsettleorgid(String hid) {
    SaleOrderViewVO[] views = null;
    try {
      views =
          this.getSaleOrderService().querySaleOrderViewVOByHIDs(new String[] {
            hid
          });
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    if (views != null && views.length > 0) {
      return views[0].getBody().getCsettleorgid();
    }
    return null;
  }

  private ISaleOrderForBalance getSaleOrderService() {
    if (this.service == null) {
      this.service = NCLocator.getInstance().lookup(ISaleOrderForBalance.class);
    }
    return this.service;
  }
}
