package nc.vo.so.entry;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class ProfitVO extends SuperVO {

  // 折扣类
  public static final String BDISCOUNTFLAG = "bdiscountflag";

  // 服务类
  public static final String BLABORFLAG = "blaborflag";

  // 赠品
  public static final String BLARGESSFLAG = "blargessflag";

  // 主单位
  public static final String CASTUNITID = "castunitid";

  // 财务组织
  public static final String CFINANCEORG = "cfinanceorg";

  // 物料编码
  public static final String CMATERIALID = "cmaterialid";

  // 物料版本（用于取是否为折扣劳务）
  public static final String CMATERIALVID = "cmaterialvid";

  // 币种
  public static final String CORIGCURRENCYID = "corigcurrencyid";

  // 库存组织
  public static final String CSTOCKORGID = "cstockorgid";

  // 仓 库
  public static final String CSTORDOCID = "cstordocid";

  // 主数量
  public static final String NASTNUM = "nastnum";

  // 无税净价
  public static final String NQTORIGNETPRICE = "nqtorignetprice";

  // 成本
  public static final String NTOTALCOST = "ntotalcost";

  // 销售收入
  public static final String NTOTALINCOME = "ntotalincome";

  // 毛利
  public static final String NTOTALPROFIT = "ntotalprofit";

  // 毛利率
  public static final String NTOTALPROFITRATE = "ntotalprofitrate";

  public static final String PK_ORG = "pk_org";

  // 时间戳
  public static final String TS = "ts";

  // 批次
  public static final String VBATCHCODE = "vbatchcode";

  
  /**
   * 成本域
   */
  private String pk_costregion;
  
  public String getPk_costregion() {
    return pk_costregion;
  }

  public void setPk_costregion(String pk_costregion) {
    this.pk_costregion = pk_costregion;
  }

  /**
   * 订单类型
   */
  private String ordertantype;

  public String getOrdertantype() {
    return ordertantype;
  }

  public void setOrdertantype(String ordertantype) {
    this.ordertantype = ordertantype;
  }

  private static final long serialVersionUID = -6621821890604697342L;

  /**
   * 按照默认方式创建构造子.
   * 
   * 创建日期:2011-01-25 18:42:55
   */
  public ProfitVO() {
    super();
  }

  public UFBoolean getBdiscountflag() {
    return (UFBoolean) this.getAttributeValue(ProfitVO.BDISCOUNTFLAG);
  }

  public UFBoolean getBlaborflag() {
    return (UFBoolean) this.getAttributeValue(ProfitVO.BLABORFLAG);
  }

  public UFBoolean getBlargessflag() {
    return (UFBoolean) this.getAttributeValue(ProfitVO.BLARGESSFLAG);
  }

  public String getCastunitid() {
    return (String) this.getAttributeValue(ProfitVO.CASTUNITID);
  }

  public String getCfinanceorg() {
    return (String) this.getAttributeValue(ProfitVO.CFINANCEORG);
  }

  public String getCmaterialid() {
    return (String) this.getAttributeValue(ProfitVO.CMATERIALID);
  }

  public String getCmaterialvid() {
    return (String) this.getAttributeValue(ProfitVO.CMATERIALVID);
  }

  public String getCorigcurrencyid() {
    return (String) this.getAttributeValue(ProfitVO.CORIGCURRENCYID);
  }

  public String getCstockorgid() {
    return (String) this.getAttributeValue(ProfitVO.CSTOCKORGID);
  }

  public String getCstordocid() {
    return (String) this.getAttributeValue(ProfitVO.CSTORDOCID);
  }

  @Override
  public IVOMeta getMetaData() {
    IVOMeta meta = VOMetaFactory.getInstance().getVOMeta("so.profit");
    return meta;
  }

  public UFDouble getNastnum() {
    return (UFDouble) this.getAttributeValue(ProfitVO.NASTNUM);
  }

  public UFDouble getNqtorignetprice() {
    return (UFDouble) this.getAttributeValue(ProfitVO.NQTORIGNETPRICE);
  }

  public UFDouble getNtotalcost() {
    return (UFDouble) this.getAttributeValue(ProfitVO.NTOTALCOST);
  }

  public UFDouble getNtotalincome() {
    return (UFDouble) this.getAttributeValue(ProfitVO.NTOTALINCOME);
  }

  public UFDouble getNtotalprofit() {
    return (UFDouble) this.getAttributeValue(ProfitVO.NTOTALPROFIT);
  }

  public UFDouble getNtotalprofitrate() {
    return (UFDouble) this.getAttributeValue(ProfitVO.NTOTALPROFITRATE);
  }

  public String getPk_org() {
    return (String) this.getAttributeValue(ProfitVO.PK_ORG);
  }

  public UFDateTime getTs() {
    return (UFDateTime) this.getAttributeValue(ProfitVO.TS);
  }

  public String getVbatchcode() {
    return (String) this.getAttributeValue(ProfitVO.VBATCHCODE);
  }

  public void setBdiscountflag(UFBoolean bdiscountflag) {
    this.setAttributeValue(ProfitVO.BDISCOUNTFLAG, bdiscountflag);
  }

  public void setBlaborflag(UFBoolean blaborflag) {
    this.setAttributeValue(ProfitVO.BLABORFLAG, blaborflag);
  }

  public void setBlargessflag(UFBoolean blargessflag) {
    this.setAttributeValue(ProfitVO.BLARGESSFLAG, blargessflag);
  }

  public void setCastunitid(String castunitid) {
    this.setAttributeValue(ProfitVO.CASTUNITID, castunitid);
  }

  public void setCfinanceorg(String cfinanceorg) {
    this.setAttributeValue(ProfitVO.CFINANCEORG, cfinanceorg);
  }

  public void setCmaterialid(String cmaterialid) {
    this.setAttributeValue(ProfitVO.CMATERIALID, cmaterialid);
  }

  public void setCmaterialvid(String cmaterialvid) {
    this.setAttributeValue(ProfitVO.CMATERIALVID, cmaterialvid);
  }

  public void setCorigcurrencyid(String corigcurrencyid) {
    this.setAttributeValue(ProfitVO.CORIGCURRENCYID, corigcurrencyid);
  }

  public void setCstockorgid(String cstockorgid) {
    this.setAttributeValue(ProfitVO.CSTOCKORGID, cstockorgid);
  }

  public void setCstordocid(String cstordocid) {
    this.setAttributeValue(ProfitVO.CSTORDOCID, cstordocid);
  }

  public void setNastnum(UFDouble nastnum) {
    this.setAttributeValue(ProfitVO.NASTNUM, nastnum);
  }

  public void setNqtorignetprice(UFDouble nqtorignetprice) {
    this.setAttributeValue(ProfitVO.NQTORIGNETPRICE, nqtorignetprice);
  }

  public void setNtotalcost(UFDouble ntotalcost) {
    this.setAttributeValue(ProfitVO.NTOTALCOST, ntotalcost);
  }

  public void setNtotalincome(UFDouble ntotalincome) {
    this.setAttributeValue(ProfitVO.NTOTALINCOME, ntotalincome);
  }

  public void setNtotalprofit(UFDouble ntotalprofit) {
    this.setAttributeValue(ProfitVO.NTOTALPROFIT, ntotalprofit);
  }

  public void setNtotalprofitrate(UFDouble ntotalprofitrate) {
    this.setAttributeValue(ProfitVO.NTOTALPROFITRATE, ntotalprofitrate);
  }

  public void setPk_org(String pk_org) {
    this.setAttributeValue(ProfitVO.PK_ORG, pk_org);
  }

  public void setTs(UFDateTime ts) {
    this.setAttributeValue(ProfitVO.TS, ts);
  }

  public void setVbatchcode(String vbatchcode) {
    this.setAttributeValue(ProfitVO.VBATCHCODE, vbatchcode);
  }

}
