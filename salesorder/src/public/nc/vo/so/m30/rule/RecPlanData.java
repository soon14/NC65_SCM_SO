package nc.vo.so.m30.rule;

import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.scmpub.payterm.recv.IRecvPlanData;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;

public class RecPlanData implements IRecvPlanData {

  private static final long serialVersionUID = 1L;

  private SaleOrderHVO hvo;

  public RecPlanData(SaleOrderVO vo) {
    this.hvo = vo.getParentVO();
  }

  @Override
  public String getCorigcurrencyid() {
    return this.hvo.getCorigcurrencyid();
  }

  @Override
  public UFDate getFeffdatetype(String feffdatetype) {
    if (null == feffdatetype) {
      return null;
    }
    if (nc.vo.bd.payment.IPaymentUtil.SALE_ORDER_DATE.equals(feffdatetype)) {
      return this.hvo.getDbilldate();
    }
    return null;
  }

  @Override
  public UFDouble getNtotalorigmny() {
    return this.hvo.getNtotalorigmny();
  }

  @Override
  public String getPk_payterm() {
    return this.hvo.getCpaytermid();
  }

  @Override
  public String getVbillcode() {
    return this.hvo.getVbillcode();
  }

  @Override
  public String getCcustomerid() {
    return this.hvo.getCcustomerid();
  }

  @Override
  public String getPk_org() {
    return this.hvo.getPk_org();
  }
}
