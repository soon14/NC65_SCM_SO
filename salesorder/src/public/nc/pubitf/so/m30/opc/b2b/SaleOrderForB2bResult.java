package nc.pubitf.so.m30.opc.b2b;

import java.io.Serializable;

import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;

/**
 * B2B预订单返回结果参数类
 * 
 * @since 6.5
 * @version 2014-04-02 14:56:14
 * @author zhangyfr
 */
public class SaleOrderForB2bResult implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 116991112972746850L;

  /**
   * 促销表体行ID
   */
  private String cpromotpriceid;

  /**
   * 客户
   */
  private String ccustomerid;

  /**
   * 出库关闭
   */
  private UFBoolean bboutendflag;

  /**
   * 发货关闭
   */
  private UFBoolean bbsendendflag;

  /**
   * 报价单位数量
   */
  private UFDouble nqtunitnum;

  /**
   * 累计发货单位数量
   */
  private UFDouble ntotalsendnum;

  /**
   * 累计出库单位数量
   */
  private UFDouble ntotaloutnum;

  /**
   * 来源标题行ID
   */
  private String csrcbid;

  /**
   * 获得来源表体行ID
   * 
   * @return csrcbid
   */
  public String getCsrcbid() {
    return this.csrcbid;
  }

  /**
   * 设置来源表体行ID
   * 
   * @param csrcbid
   */
  public void setCsrcbid(String csrcbid) {
    this.csrcbid = csrcbid;
  }

  /**
   * 获得促销表体行ID
   * 
   * @return cpromotpriceid
   */
  public String getCpromotpriceid() {
    return this.cpromotpriceid;
  }

  /**
   * 设置促销表体行ID
   * 
   * @param cpromotpriceid
   */
  public void setCpromotpriceid(String cpromotpriceid) {
    this.cpromotpriceid = cpromotpriceid;
  }

  /**
   * 获得客户
   * 
   * @return ccustomerid
   */
  public String getCcustomerid() {
    return this.ccustomerid;
  }

  /**
   * 设置客户
   * 
   * @param ccustomerid
   */
  public void setCcustomerid(String ccustomerid) {
    this.ccustomerid = ccustomerid;
  }

  /**
   * 获得出库关闭
   * 
   * @return bboutendflag
   */
  public UFBoolean getBboutendflag() {
    return this.bboutendflag;
  }

  /**
   * 设置出库关闭
   * 
   * @param bboutendflag
   */
  public void setBboutendflag(UFBoolean bboutendflag) {
    this.bboutendflag = bboutendflag;
  }

  /**
   * 获得发货关闭
   * 
   * @return bbsendendflag
   */
  public UFBoolean getBbsendendflag() {
    return this.bbsendendflag;
  }

  /**
   * 设置发货关闭
   * 
   * @param bbsendendflag
   */
  public void setBbsendendflag(UFBoolean bbsendendflag) {
    this.bbsendendflag = bbsendendflag;
  }

  /**
   * 获得报价单位数量
   * 
   * @return nqtunitnum
   */
  public UFDouble getNqtunitnum() {
    return this.nqtunitnum;
  }

  /**
   * 设置报价单位数量
   * 
   * @param nqtunitnum
   */
  public void setNqtunitnum(UFDouble nqtunitnum) {
    this.nqtunitnum = nqtunitnum;
  }

  /**
   * 获得累计发货数量
   * 
   * @return ntotalsendnums
   */
  public UFDouble getNtotalsendnum() {
    return this.ntotalsendnum;
  }

  /**
   * 设置累计出库数量
   * 
   * @param ntotalsendnum
   */
  public void setNtotalsendnum(UFDouble ntotalsendnum) {
    this.ntotalsendnum = ntotalsendnum;
  }

  /**
   * 获得累计出库数量
   * 
   * @return ntotalsendnum
   */
  public UFDouble getNtotaloutnum() {
    return this.ntotaloutnum;
  }

  /**
   * 设置累计出库数量
   * 
   * @param ntotaloutnum
   */
  public void setNtotaloutnum(UFDouble ntotaloutnum) {
    this.ntotaloutnum = ntotaloutnum;
  }

}
