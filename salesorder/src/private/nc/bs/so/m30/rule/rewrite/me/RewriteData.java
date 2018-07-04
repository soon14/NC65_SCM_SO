package nc.bs.so.m30.rule.rewrite.me;

import nc.pubitf.me.m35meext.so.IRewriteData;
import nc.vo.pub.lang.UFDouble;

/**
 * 回写营销费用参数接口实现
 * 
 * @since 6.3
 * @version 2014-06-27 14:27:28
 * @author 刘景
 */
public class RewriteData implements IRewriteData {

  /**
   * 币种
   * 
   */
  public String ccurrencyid;

  /**
   * 订单本次途损数量
   */
  public UFDouble ncurtranslossnum;

  /**
   * 订单行财务组织
   * 
   */
  public String financeorg;

  /**
   * 
   * 订单行费用冲抵金额
   */

  public UFDouble norigsubmny;

  /**
   * 订单行累计途损主数量
   */

  public UFDouble ntranslossnum;

  /**
   * 
   * 销售订单id
   */
  public String saleorderid;

  /**
   * 
   * 销售订单行id
   */

  public String saleorderbid;

  /**
   * 
   * 订单行主数量
   */
  public UFDouble nnum;

  /**
   * 
   * 订单行累计签收主数量
   */

  public UFDouble ntotalsignnum;

  /**
   * 
   * 订单行累计出库主数量
   */

  public UFDouble ntotalsendnum;

  /**
   * 
   * 订单行本次签收主数量
   */
  public UFDouble ncursignnum;

  /**
   * 订单行是否出库关闭(签收途损单保存或删除时传递)
   * 
   */

  public boolean isoutclosed;

  /**
   * 
   * 是否赠品兑付销售订单
   */

  public boolean islrgcash;
  
  /**
   * 订单行本次出库变化量
   */
  public UFDouble nchangenum;

  public UFDouble getNchangenum() {
	return nchangenum;
  }

  public void setNchangenum(UFDouble nchangenum) {
	this.nchangenum = nchangenum;
  }

@Override
  public String getCcurrencyid() {
    return this.ccurrencyid;
  }

  @Override
  public String getFinanceorg() {
    return this.financeorg;
  }

  @Override
  public UFDouble getNorigsubmny() {
    return this.norigsubmny;
  }

  @Override
  public UFDouble getNtranslossnum() {
    return this.ntranslossnum;
  }

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
  public UFDouble getNtotalsignnum() {
    return this.ntotalsignnum;
  }

  @Override
  public UFDouble getNtotalsendnum() {
    return this.ntotalsendnum;
  }

  @Override
  public UFDouble getNcursignnum() {
    return this.ncursignnum;
  }

  @Override
  public boolean getIsOutClosed() {
    return this.isoutclosed;
  }

  @Override
  public boolean getIsLrgCash() {
    return this.islrgcash;
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

  /**
   * 设置订单行费用冲抵金额
   * 
   * @param norigsubmny
   */
  public void setNorigsubmny(UFDouble norigsubmny) {
    this.norigsubmny = norigsubmny;
  }

  /**
   * 设置订单行累计途损主数量
   * 
   * @param ntranslossnum
   */
  public void setNtranslossnum(UFDouble ntranslossnum) {
    this.ntranslossnum = ntranslossnum;
  }

  /**
   * 设置订单表头id
   * 
   * @param saleorderid
   */
  public void setSaleorderid(String saleorderid) {
    this.saleorderid = saleorderid;
  }

  /**
   * 设置订单行id
   * 
   * @param saleorderbid
   */
  public void setSaleorderbid(String saleorderbid) {
    this.saleorderbid = saleorderbid;
  }

  /**
   * 设置订单行主数量
   * 
   * @param nnum
   */
  public void setNnum(UFDouble nnum) {
    this.nnum = nnum;
  }

  /**
   * 
   * 设置订单行累计签收主数量
   * 
   * @param ntotalsignnum
   */
  public void setNtotalsignnum(UFDouble ntotalsignnum) {
    this.ntotalsignnum = ntotalsignnum;
  }

  /**
   * 设置 订单行累计出库主数量
   * 
   * @param ntotalsendnum
   */
  public void setNtotalsendnum(UFDouble ntotalsendnum) {
    this.ntotalsendnum = ntotalsendnum;
  }

  /**
   * 设置订单行本次签收主数量
   * 
   * @param ncursignnum
   */
  public void setNcursignnum(UFDouble ncursignnum) {
    this.ncursignnum = ncursignnum;
  }

  /**
   * 设置订单行是否出库关闭(签收途损单保存或删除时传递)
   * 
   * @param isoutclosed
   */
  public void setIsoutclosed(boolean isoutclosed) {
    this.isoutclosed = isoutclosed;
  }

  /**
   * 设置是否赠品兑付销售订单
   * 
   * @param islrgcash
   */
  public void setIslrgcash(boolean islrgcash) {
    this.islrgcash = islrgcash;
  }
  
  @Override
  public UFDouble getNcurtranslossnum() {
    return this.ncurtranslossnum;
  }

  /**
   * 设置本次途损数量
   * 
   * @param ncurtranslossnum
   */
  public void setNcurtranslossnum(UFDouble ncurtranslossnum) {
    this.ncurtranslossnum = ncurtranslossnum;
  }

}
