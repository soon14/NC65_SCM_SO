package nc.vo.so.pub.execinfo;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

/**
 * 执行情况
 * 
 * @since 6.0
 * @version 2010-12-31 上午10:15:24
 * @author 祝会征
 */
public class ExecInfoReportVO extends SuperVO {
  /** 物料编码 */
  public static final String CMATERIALID = "cmaterialid";

  /** 计量单位 */
  public static final String CUNITID = "cunitid";

  /** dr */
  public static final String DR = "dr";

  /** 未开票金额 */
  public static final String NEEDINVOICEMONEY = "needinvoicemoney";

  /** 未开票数量 */
  public static final String NEEDINVOICENUM = "needinvoicenum";

  /** 未出库数量 */
  public static final String NEEDOUTNUM = "needoutnum";

  /** 未付款金额 */
  public static final String NEEDPAYMONEY = "needpaymoney";

  /** 未发货数量 */
  public static final String NEEDSENDNUM = "needsendnum";

  /** 数量 */
  public static final String NNUM = "nnum";

  /** 已开票数量 */
  public static final String NTOTALINVOICENUM = "ntotalinvoicenum";

  /** 已出库数量 */
  public static final String NTOTALOUTNUM = "ntotaloutnum";

  /** 已发货数量 */
  public static final String NTOTALSENDNUM = "ntotalsendnum";

  /** 应发数量 */
  public static final String SHOULDSENDNUM = "shouldsendnum";

  /** 已开票金额 */
  public static final String TOTALINVOICEMONEY = "totalinvoicemoney";

  /** 已付款金额 */
  public static final String TOTALPAYMONEY = "totalpaymoney";

  /** 时间戳 */
  public static final String TS = "ts";

  public static final String CCURRENCYID = "ccurrencyid";

  private static final long serialVersionUID = 1L;

  public String getCcurrencyid() {
    return (String) this.getAttributeValue(ExecInfoReportVO.CCURRENCYID);
  }

  public String getCmaterialid() {
    return (String) this.getAttributeValue(ExecInfoReportVO.CMATERIALID);
  }

  public String getCunitid() {
    return (String) this.getAttributeValue(ExecInfoReportVO.CUNITID);
  }

  public Integer getDr() {
    return (Integer) this.getAttributeValue(ExecInfoReportVO.DR);
  }

  @Override
  public IVOMeta getMetaData() {
    IVOMeta meta = VOMetaFactory.getInstance().getVOMeta("so.execinfo");
    return meta;
  }

  public UFDouble getNeedinvoicemoney() {
    return (UFDouble) this.getAttributeValue(ExecInfoReportVO.NEEDINVOICEMONEY);
  }

  public UFDouble getNeedinvoicenum() {
    return (UFDouble) this.getAttributeValue(ExecInfoReportVO.NEEDINVOICENUM);
  }

  public UFDouble getNeedoutnum() {
    return (UFDouble) this.getAttributeValue(ExecInfoReportVO.NEEDOUTNUM);
  }

  public UFDouble getNeedpaymoney() {
    return (UFDouble) this.getAttributeValue(ExecInfoReportVO.NEEDPAYMONEY);
  }

  public UFDouble getNeedsendnum() {
    return (UFDouble) this.getAttributeValue(ExecInfoReportVO.NEEDSENDNUM);
  }

  public UFDouble getNnum() {
    return (UFDouble) this.getAttributeValue(ExecInfoReportVO.NNUM);
  }

  public UFDouble getNtotalinvoicenum() {
    return (UFDouble) this.getAttributeValue(ExecInfoReportVO.NTOTALINVOICENUM);
  }

  public UFDouble getNtotaloutnum() {
    return (UFDouble) this.getAttributeValue(ExecInfoReportVO.NTOTALOUTNUM);
  }

  public UFDouble getNtotalsendnum() {
    return (UFDouble) this.getAttributeValue(ExecInfoReportVO.NTOTALSENDNUM);
  }

  public UFDouble getShouldsendnum() {
    return (UFDouble) this.getAttributeValue(ExecInfoReportVO.SHOULDSENDNUM);
  }

  public UFDouble getTotalinvoicemoney() {
    return (UFDouble) this
        .getAttributeValue(ExecInfoReportVO.TOTALINVOICEMONEY);
  }

  public UFDouble getTotalpaymoney() {
    return (UFDouble) this.getAttributeValue(ExecInfoReportVO.TOTALPAYMONEY);
  }

  public UFDateTime getTs() {
    return (UFDateTime) this.getAttributeValue(ExecInfoReportVO.TS);
  }

  public void setCcurrencyid(String ccurrencyid) {
    this.setAttributeValue(ExecInfoReportVO.CCURRENCYID, ccurrencyid);
  }

  public void setCmaterialid(String cmaterialid) {
    this.setAttributeValue(ExecInfoReportVO.CMATERIALID, cmaterialid);
  }

  public void setCunitid(String cunitid) {
    this.setAttributeValue(ExecInfoReportVO.CUNITID, cunitid);
  }

  public void setDr(Integer dr) {
    this.setAttributeValue(ExecInfoReportVO.DR, dr);
  }

  public void setNeedinvoicemoney(UFDouble needinvoicemoney) {
    this.setAttributeValue(ExecInfoReportVO.NEEDINVOICEMONEY, needinvoicemoney);
  }

  public void setNeedinvoicenum(UFDouble needinvoicenum) {
    this.setAttributeValue(ExecInfoReportVO.NEEDINVOICENUM, needinvoicenum);
  }

  public void setNeedoutnum(UFDouble needoutnum) {
    this.setAttributeValue(ExecInfoReportVO.NEEDOUTNUM, needoutnum);
  }

  /**
   * 未付款金额或者待收金额
   * 
   * @param needpaymoney
   */
  public void setNeedpaymoney(UFDouble needpaymoney) {
    this.setAttributeValue(ExecInfoReportVO.NEEDPAYMONEY, needpaymoney);
  }

  public void setNeedsendnum(UFDouble needsendnum) {
    this.setAttributeValue(ExecInfoReportVO.NEEDSENDNUM, needsendnum);
  }

  public void setNnum(UFDouble nnum) {
    this.setAttributeValue(ExecInfoReportVO.NNUM, nnum);
  }

  /**
   * 开票数量或者是结算数量
   * 
   * @param ntotalinvoicenum
   */
  public void setNtotalinvoicenum(UFDouble ntotalinvoicenum) {
    this.setAttributeValue(ExecInfoReportVO.NTOTALINVOICENUM, ntotalinvoicenum);
  }

  public void setNtotaloutnum(UFDouble ntotaloutnum) {
    this.setAttributeValue(ExecInfoReportVO.NTOTALOUTNUM, ntotaloutnum);
  }

  public void setNtotalsendnum(UFDouble ntotalsendnum) {
    this.setAttributeValue(ExecInfoReportVO.NTOTALSENDNUM, ntotalsendnum);
  }

  public void setShouldsendnum(UFDouble shouldsendnum) {
    this.setAttributeValue(ExecInfoReportVO.SHOULDSENDNUM, shouldsendnum);
  }

  /**
   * 开票金额或者是结算金额
   * 
   * @param totalinvoicemoney
   */
  public void setTotalinvoicemoney(UFDouble totalinvoicemoney) {
    this.setAttributeValue(ExecInfoReportVO.TOTALINVOICEMONEY,
        totalinvoicemoney);
  }

  /**
   * 已付款金额或者收款金额
   * 
   * @param totalpaymoney
   */
  public void setTotalpaymoney(UFDouble totalpaymoney) {
    this.setAttributeValue(ExecInfoReportVO.TOTALPAYMONEY, totalpaymoney);
  }

  public void setTs(UFDateTime ts) {
    this.setAttributeValue(ExecInfoReportVO.TS, ts);
  }
}
