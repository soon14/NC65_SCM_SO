package nc.pubimpl.so.m30.ic.pub;

import nc.pubitf.me.m35meext.so.IVoucherData;
import nc.vo.pub.lang.UFDouble;

/**
 * 订单出库关闭，签收途损影响客户费用单对账金额及凭证参数实现
 * 
 * @since 6.3
 * @version 2014-07-02 09:45:13
 * @author 刘景
 */
public class VoucherData implements IVoucherData {

  private String saleorderid;

  private String saleorderbid;

  private UFDouble nnum;

  private UFDouble neffectnum;

  private UFDouble norigsubmny;

  private UFDouble norigcaccountmny;

  private Boolean islrgcash;

  private String ccurrencyid;

  private String financeorg;

  @Override
  public String getSaleorderid() {
    return this.saleorderid;
  }

  @Override
  public String getSaleorderbid() {
    return this.saleorderbid;
  }

  @Override
  public UFDouble getNnum() {
    return this.nnum;
  }

  @Override
  public UFDouble getNeffectnum() {
    return this.neffectnum;
  }

  @Override
  public UFDouble getNorigsubmny() {
    return this.norigsubmny;
  }

  @Override
  public UFDouble getNorigcaccountmny() {
    return this.norigcaccountmny;
  }

  @Override
  public Boolean getIsLrgCash() {
    return this.islrgcash;
  }

  /**
   * 销售订单id
   * 
   * @param saleorderid
   */
  public void setSaleorderid(String saleorderid) {
    this.saleorderid = saleorderid;
  }

  /**
   * 销售订单行id
   * 
   * @param saleorderbid
   */
  public void setSaleorderbid(String saleorderbid) {
    this.saleorderbid = saleorderbid;
  }

  /**
   * 销售订单行主数量
   * 
   * @param nnum
   */
  public void setNnum(UFDouble nnum) {
    this.nnum = nnum;
  }

  /**
   * 本次出库或签收主数量
   * 
   * @param neffectnum
   */
  public void setNeffectnum(UFDouble neffectnum) {
    this.neffectnum = neffectnum;
  }

  /**
   * 销售订单行冲抵金额
   * 
   * @param norigsubmny
   */
  public void setNorigsubmny(UFDouble norigsubmny) {
    this.norigsubmny = norigsubmny;
  }

  /**
   * 销售订单行冲抵记账金额：<br>
   * 当订单为赠品兑付订单时，取 订单行记账单价* 订单行主数量；
   * 否则，取订单行冲抵金额
   * 
   * 
   * @param norigcaccountmny
   */
  public void setNorigcaccountmny(UFDouble norigcaccountmny) {
    this.norigcaccountmny = norigcaccountmny;
  }

  /**
   * 是否赠品兑付订单
   * 
   * @param islrgcash
   */
  public void setIslrgcash(Boolean islrgcash) {
    this.islrgcash = islrgcash;
  }

  @Override
  public String getCcurrencyid() {
    return this.ccurrencyid;
  }

  /**
   * 设置币种
   * 
   * @param ccurrencyid
   */
  public void setCcurrencyid(String ccurrencyid) {
    this.ccurrencyid = ccurrencyid;
  }

  /**
   * 设置财务组织
   * 
   * @param financeorg
   */
  public void setFinanceorg(String financeorg) {
    this.financeorg = financeorg;
  }

  @Override
  public String getFinanceorg() {
    return this.financeorg;
  }

}
