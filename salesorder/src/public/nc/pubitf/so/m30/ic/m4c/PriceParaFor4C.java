package nc.pubitf.so.m30.ic.m4c;

import nc.pubitf.ic.m4c.m30.ISaleOutPriceParaFor30;
import nc.vo.pub.lang.UFDouble;
import nc.vo.so.m30.entity.SaleOrderBVO;

public class PriceParaFor4C implements ISaleOutPriceParaFor30 {
  private SaleOrderBVO bvo;

  public PriceParaFor4C(SaleOrderBVO bvo) {
    this.bvo = bvo;
  }

  @Override
  public String getHid() {
    return this.bvo.getCsaleorderid();
  }

  @Override
  public UFDouble getNnetprice() {
    return this.bvo.getNnetprice();
  }

  @Override
  public UFDouble getNorignetprice() {
    return this.bvo.getNorignetprice();
  }

  @Override
  public UFDouble getNorigprice() {
    return this.bvo.getNorigprice();
  }

  @Override
  public UFDouble getNorigtaxnetprice() {
    return this.bvo.getNorigtaxnetprice();
  }

  @Override
  public UFDouble getNorigtaxprice() {
    return this.bvo.getNorigtaxprice();
  }

  @Override
  public UFDouble getNprice() {
    return this.bvo.getNprice();
  }

  @Override
  public UFDouble getNqtnetprice() {
    return this.bvo.getNqtnetprice();
  }

  @Override
  public UFDouble getNqtorignetprice() {
    return this.bvo.getNqtorignetprice();
  }

  @Override
  public UFDouble getNqtorigprice() {
    return this.bvo.getNqtorigprice();
  }

  @Override
  public UFDouble getNqtorigtaxnetprc() {
    return this.bvo.getNqtorigtaxnetprc();
  }

  @Override
  public UFDouble getNqtorigtaxprice() {
    return this.bvo.getNqtorigtaxprice();
  }

  @Override
  public UFDouble getNqtprice() {
    return this.bvo.getNqtprice();
  }

  @Override
  public UFDouble getNqttaxnetprice() {
    return this.bvo.getNqttaxnetprice();
  }

  @Override
  public UFDouble getNqttaxprice() {
    return this.bvo.getNqttaxprice();
  }

  @Override
  public UFDouble getNtaxnetprice() {
    return this.bvo.getNtaxnetprice();
  }

  @Override
  public UFDouble getNtaxprice() {
    return this.bvo.getNtaxprice();
  }

  @Override
  public UFDouble getNitemdiscountrate() {
    return this.bvo.getNitemdiscountrate();
  }

}
