package nc.vo.so.m30.cashsale;

import java.io.Serializable;
import java.util.Map;

import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.sobalance.entity.SoBalanceVO;
import nc.vo.so.m35.paravo.OffsetParaVO;

public class CashSaleData implements Serializable {
  private static final long serialVersionUID = 8803564138790043151L;

  private SaleOrderVO ordervo;

  private SoBalanceVO sobalancevo;
  
  private Map<Integer, OffsetParaVO> offparamap;

  public SaleOrderVO getOrdervo() {
    return this.ordervo;
  }

  public void setOrdervo(SaleOrderVO ordervo) {
    this.ordervo = ordervo;
  }

  public SoBalanceVO getSobalancevo() {
    return this.sobalancevo;
  }

  public void setSobalancevo(SoBalanceVO sobalancevo) {
    this.sobalancevo = sobalancevo;
  }

  public Map<Integer, OffsetParaVO> getOffparamap() {
    return this.offparamap;
  }

  public void setOffparamap(Map<Integer, OffsetParaVO> offparamap) {
    this.offparamap = offparamap;
  }

}
