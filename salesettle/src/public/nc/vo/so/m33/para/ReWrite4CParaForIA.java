package nc.vo.so.m33.para;

import nc.vo.pub.lang.UFDouble;

/**
 * 提供给存货核算的参数VO
 * 
 * @since 6.0
 * @version 2011-6-1 下午01:23:24
 * @author 么贵敬
 */
public class ReWrite4CParaForIA {

  /**
   * 来源明细ID
   */
  private String csrcbid;

  /**
   * 成本金额
   */
  private UFDouble ncostmny;

  /**
   * 成本单价
   */
  private UFDouble ncostprice;

  /**
   * 获得来源明细ID
   * 
   * @return 来源明细ID
   */
  public String getCsrcbid() {
    return this.csrcbid;
  }

  /**
   * 获得成本金额
   * 
   * @return 成本金额
   */
  public UFDouble getNcostmny() {
    return this.ncostmny;
  }

  /**
   * 设置来源明细ID
   * 
   * @param csrcbid
   */
  public void setCsrcbid(String csrcbid) {
    this.csrcbid = csrcbid;
  }

  /**
   * 设置成本金额
   * 
   * @param ncostmny
   */
  public void setNcostmny(UFDouble ncostmny) {
    this.ncostmny = ncostmny;
  }

  /**
   * 获得成本单价
   * 
   * @return 成本单价
   */
  public UFDouble getNcostprice() {
    return this.ncostprice;
  }

  /**
   * 设置成本单价
   * 
   * @param ncostprice
   */
  public void setNcostprice(UFDouble ncostprice) {
    this.ncostprice = ncostprice;
  }

}
