package nc.vo.so.report.invoicesummary;

import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.entity.view.AbstractDataView;
import nc.vo.pubapp.pattern.model.meta.entity.view.DataViewMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.view.IDataViewMeta;

/**
 * 销售发票执行汇总视图VO
 * 
 * @since 6.3
 * @version 2012-09-10 09:21:11
 * @author 梁吉明
 */
public class InvSummaryViewVO extends AbstractDataView {

  /**
   * 
   */
  private static final long serialVersionUID = -7793492081245563347L;

  @Override
  public IDataViewMeta getMetaData() {
    // IDataViewMeta主要描述当前视图的组成实体
    IDataViewMeta viewMeta =
    // 视图工具类创建类，方法，获取元数据
        DataViewMetaFactory.getInstance().getDataViewMeta(
            InvSummaryViewMeta.class);
    return viewMeta;
  }

  /**
   * 发票主实体_主键
   */
  public static final String CSALEINVOICEID = "csaleinvoiceid";

  /**
   * 发票子实体_主键
   */
  public static final String CSALEINVOICEBID = "csaleinvoicebid";

  /**
   * 开票组织
   */
  public static final String PK_ORG = "pk_org";

  /**
   * 开票日期
   */
  public static final String DBILLDATE = "dbilldate";

  /**
   * 订单客户
   */
  public static final String CORDERCUSTID = "cordercustid";

  /**
   * 销售渠道类型
   */
  public static final String CCHANNELTYPEID = "cchanneltypeid";

  /**
   * 客户基本分类
   */
  public static final String PK_CUSTCLASS = "pk_custclass";

  /**
   * 客户销售分类
   */
  public static final String PK_CUSTSALECLASS = "pk_custsaleclass";

  /**
   * 地区分类
   */
  public static final String PK_AREACL = "pk_areacl";

  /**
   * 开票客户
   */
  public static final String CINVOICECUSTID = "cinvoicecustid";

  /**
   * 销售组织
   */
  public static final String CSALEORGID = "csaleorgid";

  /**
   * 销售部门
   */
  public static final String CDEPTID = "cdeptid";

  /**
   * 销售业务员
   */
  public static final String CEMPLOYEEID = "cemployeeid";

  /**
   * 库存组织
   */
  public static final String CSENDSTOCKORGID = "csendstockorgid";

  /**
   * 物料
   */
  public static final String CMATERIALID = "cmaterialid";

  /**
   * 主单位
   */
  public static final String CUNITID = "cunitid";

  /**
   * 批次号
   */
  public static final String VBATCHCODE = "vbatchcode";

  /**
   * 物料基本分类
   */
  public static final String PK_MARBASCLASS = "pk_marbasclass";

  /**
   * 物料销售分类
   */
  public static final String PK_MARSALECLASS = "pk_marsaleclass";

  /**
   * 币种
   */
  public static final String CORIGCURRENCYID = "corigcurrencyid";

  /**
   * 统计服务和价格折扣
   */
  /**
   * 发票类型
   */
  public static final String CTRANTYPEID = "ctrantypeid";

  /**
   * 订单类型(订单交易类型)
   */
  public static final String VFIRSTTRANTYPE = "vfirsttrantype";

  /**
   * 赠品
   */
  public static final String BLARGESSFLAG = "blargessflag";

  /**
   * 主数量
   */
  public static final String NNUM = "nnum";

  /**
   * 主含税净价(报表处理)
   * 
   */
 
  public static final String NORIGTAXNETPRICE="norigtaxnetprice";
  /**
   * 无税金额
   */
  public static final String NORIGMNY = "norigmny";

  /**
   * 价税合计
   */
  public static final String NORIGTAXMNY = "norigtaxmny";

  /**
   * 税额(本币)
   */
  public static final String NTAX = "ntax";

  /**
   * 折扣额
   */
  public static final String NORIGDISCOUNT = "norigdiscount";

  /**
   * 应收主数量
   */
  public static final String NSHOULDRECEIVNUM = "nshouldreceivnum";

  /**
   * 应收金额
   */
  public static final String NSHOULDRECEIVMNY = "nshouldreceivmny";

  /**
   * 收款金额（报表处理）
   */
  public static final String RECEIVMNY="receivmny";
  
  /**
   * 应收余额
   */
  public static final String NTOTALRECEIVMNY = "ntotalreceivmny";

  /**
   * 服务类
   */
  public static final String BLABORFLAG = "blaborflag";

  /**
   * 折扣类
   */
  public static final String BDISCOUNTFLAG = "bdiscountflag";

  /**
   * 
   * @return 表头id
   */
  public String getCsaleinvoiceid() {
    return (String) this.getAttributeValue(InvSummaryViewVO.CSALEINVOICEID);
  }

  /**
   * 
   * @param csaleinvoiceid
   */
  public void setCsaleinvoiceid(String csaleinvoiceid) {
    this.setAttributeValue(InvSummaryViewVO.CSALEINVOICEID, csaleinvoiceid);
  }

  /**
   * 
   * @return 表体id
   */
  public String getCsaleinvoicebid() {
    return (String) this.getAttributeValue(InvSummaryViewVO.CSALEINVOICEBID);
  }

  /**
   * 
   * @param csaleinvoicebid
   */
  public void setCsaleinvoicebid(String csaleinvoicebid) {
    this.setAttributeValue(InvSummaryViewVO.CSALEINVOICEBID, csaleinvoicebid);
  }

  /**
   * 
   * @return 开票组织
   */
  public String getPk_org() {
    return (String) this.getAttributeValue(InvSummaryViewVO.PK_ORG);
  }

  /**
   * 
   * @param pk_org
   */
  public void setPk_org(String pk_org) {
    this.setAttributeValue(InvSummaryViewVO.PK_ORG, pk_org);
  }

  /**
   * 
   * @return 主单位
   */
  public String getCunitid() {
    return (String) super.getAttributeValue(InvSummaryViewVO.CUNITID);
  }

  /**
   * 设置主单位
   * 
   * @param cunitid
   */
  public void setCunitid(String cunitid) {
    super.setAttributeValue(InvSummaryViewVO.CUNITID, cunitid);
  }

  /**
   * 
   * @return 开票日期
   */
  public UFDate getDbilldate() {
    return (UFDate) this.getAttributeValue(InvSummaryViewVO.DBILLDATE);
  }

  /**
   * 
   * @param dbilldate
   */
  public void setDbilldate(UFDate dbilldate) {
    this.setAttributeValue(InvSummaryViewVO.DBILLDATE, dbilldate);
  }

  /**
   * 
   * @return 订单客户
   */
  public String getCordercustid() {
    return (String) this.getAttributeValue(InvSummaryViewVO.CORDERCUSTID);
  }

  /**
   * 
   * @param cordercustid
   */
  public void setCordercustid(String cordercustid) {
    this.setAttributeValue(InvSummaryViewVO.CORDERCUSTID, cordercustid);
  }

  /**
   * 
   * @return 销售渠道类型
   */
  public String getChanneltypeid() {
    return (String) this.getAttributeValue(InvSummaryViewVO.CCHANNELTYPEID);
  }

  /**
   * 
   * @param channeltypeid
   */
  public void setChanneltypeid(String channeltypeid) {
    this.setAttributeValue(InvSummaryViewVO.CCHANNELTYPEID, channeltypeid);
  }

  /**
   * 
   * @return 地区分类
   */
  public String getPk_areacl() {
    return (String) super.getAttributeValue(InvSummaryViewVO.PK_AREACL);
  }

  /**
   * 
   * @return 客户基本分类
   */
  public String getPk_custclass() {
    return (String) super.getAttributeValue(InvSummaryViewVO.PK_CUSTCLASS);
  }

  /**
   * 
   * @return 客户销售分类
   */
  public String getPk_custsaleclass() {
    return (String) super.getAttributeValue(InvSummaryViewVO.PK_CUSTSALECLASS);
  }

  /**
   * 
   * @return 物料基本分类
   */
  public String getPk_marbasclass() {
    return (String) super.getAttributeValue(InvSummaryViewVO.PK_MARBASCLASS);
  }

  /**
   * 
   * @return 物料销售分类
   */
  public String getPk_marsaleclass() {
    return (String) super.getAttributeValue(InvSummaryViewVO.PK_MARSALECLASS);
  }

  /**
   * 设置地区分类
   * 
   * @param pk_areacl
   */
  public void setPk_areacl(String pk_areacl) {
    super.setAttributeValue(InvSummaryViewVO.PK_AREACL, pk_areacl);
  }

  /**
   * 设置客户基本分类
   * 
   * @param pk_custclass
   */
  public void setPk_custclass(String pk_custclass) {
    super.setAttributeValue(InvSummaryViewVO.PK_CUSTCLASS, pk_custclass);
  }

  /**
   * 设置客户销售分类
   * 
   * @param pk_custsaleclass
   */
  public void setPk_custsaleclass(String pk_custsaleclass) {
    super
        .setAttributeValue(InvSummaryViewVO.PK_CUSTSALECLASS, pk_custsaleclass);
  }

  /**
   * 设置物料基本分类
   * 
   * @param pk_marbasclass
   */
  public void setPk_marbasclass(String pk_marbasclass) {
    super.setAttributeValue(InvSummaryViewVO.PK_MARBASCLASS, pk_marbasclass);
  }

  /**
   * 设置物料销售分类
   * 
   * @param pk_marsaleclass
   */
  public void setPk_marsaleclass(String pk_marsaleclass) {
    super.setAttributeValue(InvSummaryViewVO.PK_MARSALECLASS, pk_marsaleclass);
  }

  /**
   * 
   * @return 开票客户
   */
  public String getCinvoicecustid() {
    return (String) this.getAttributeValue(InvSummaryViewVO.CINVOICECUSTID);
  }

  /**
   * 
   * @param cinvoicecustid
   */
  public void setCinvoicecustid(String cinvoicecustid) {
    this.setAttributeValue(InvSummaryViewVO.CINVOICECUSTID, cinvoicecustid);
  }

  /**
   * 
   * @return 部门
   */
  public String getCdeptid() {
    return (String) this.getAttributeValue(InvSummaryViewVO.CDEPTID);
  }

  /**
   * 
   * @param cdeptvid
   */
  public void setCdeptid(String cdeptvid) {
    this.setAttributeValue(InvSummaryViewVO.CDEPTID, cdeptvid);
  }

  /**
   * 
   * @return 业务员
   */
  public String getCemployeeid() {
    return (String) this.getAttributeValue(InvSummaryViewVO.CEMPLOYEEID);
  }

  /**
   * 
   * @param cemployeeid
   */
  public void setCemployeeid(String cemployeeid) {
    this.setAttributeValue(InvSummaryViewVO.CEMPLOYEEID, cemployeeid);
  }

  /**
   * 
   * @return 销售组织
   * 
   */
  public String getCsaleorgid() {
    return (String) this.getAttributeValue(InvSummaryViewVO.CSALEORGID);
  }

  /**
   * 
   * @param csaleorgid
   */
  public void setCsaleorgid(String csaleorgid) {
    this.setAttributeValue(InvSummaryViewVO.CSALEORGID, csaleorgid);
  }

  /**
   * 
   * @return 库存组织
   */
  public String getCsendstockorgid() {
    return (String) this.getAttributeValue(InvSummaryViewVO.CSENDSTOCKORGID);
  }

  /**
   * 
   * @param csendstockorgid
   */
  public void setCsendstockorgid(String csendstockorgid) {
    this.setAttributeValue(InvSummaryViewVO.CSENDSTOCKORGID, csendstockorgid);
  }

  /**
   * 
   * @return 物料
   */
  public String getCmaterialid() {
    return (String) super.getAttributeValue(InvSummaryViewVO.CMATERIALID);
  }

  /**
   * 设置物料
   * 
   * @param cmaterialid
   */
  public void setCmaterialid(String cmaterialid) {
    super.setAttributeValue(InvSummaryViewVO.CMATERIALID, cmaterialid);
  }

  /**
   * 
   * @return 批次号
   */
  public String getVbatchcode() {
    return (String) super.getAttributeValue(InvSummaryViewVO.VBATCHCODE);
  }

  /**
   * 设置批次号
   * 
   * @param vbatchcode
   */
  public void setVbatchcode(String vbatchcode) {
    super.setAttributeValue(InvSummaryViewVO.VBATCHCODE, vbatchcode);
  }

  /**
   * 
   * @return 币种
   */
  public String getCorigcurrencyid() {
    return (String) super.getAttributeValue(InvSummaryViewVO.CORIGCURRENCYID);
  }

  /**
   * 设置币种
   * 
   * @param corigcurrencyid
   */
  public void setCorigcurrencyid(String corigcurrencyid) {
    super.setAttributeValue(InvSummaryViewVO.CORIGCURRENCYID, corigcurrencyid);
  }

  /**
   * 
   * @return 发票类型
   */
  public String getCtrantypeid() {
    return (String) super.getAttributeValue(InvSummaryViewVO.CTRANTYPEID);
  }

  /**
   * 发票类型
   * 
   * @param ctrantypeid
   */
  public void setCtrantypeid(String ctrantypeid) {
    super.setAttributeValue(InvSummaryViewVO.CTRANTYPEID, ctrantypeid);
  }

  /**
   * 
   * @return 订单类型
   */
  public String getVfirsttrantype() {
    return (String) super.getAttributeValue(InvSummaryViewVO.VFIRSTTRANTYPE);
  }

  /**
   * 订单类型
   * 
   * @param vfirsttrantype
   */
  public void setVfirsttrantypee(String vfirsttrantype) {
    super.setAttributeValue(InvSummaryViewVO.VFIRSTTRANTYPE, vfirsttrantype);
  }

  /**
   * 
   * @return 赠品
   */
  public UFBoolean getBlargessflag() {
    return (UFBoolean) this.getAttributeValue(InvSummaryViewVO.BLARGESSFLAG);
  }

  /**
   * 
   * @param blargessflag
   */
  public void setBlargessflag(UFBoolean blargessflag) {
    this.setAttributeValue(InvSummaryViewVO.BLARGESSFLAG, blargessflag);
  }

  /**
   * 
   * @return 主数量
   */
  public UFDouble getNnum() {
    return (UFDouble) this.getAttributeValue(InvSummaryViewVO.NNUM);
  }

  /**
   * 
   * @param nnum
   */
  public void setNnum(UFDouble nnum) {
    this.setAttributeValue(InvSummaryViewVO.NNUM, nnum);
  }

  /**
   * 
   * @return 无税金额
   */
  public UFDouble getNorigmny() {
    return (UFDouble) this.getAttributeValue(InvSummaryViewVO.NORIGMNY);
  }

  /**
   * 
   * @param norigmny
   */
  public void setNorigmny(UFDouble norigmny) {
    this.setAttributeValue(InvSummaryViewVO.NORIGMNY, norigmny);
  }

  /**
   * 
   * @return 价税合计
   */
  public UFDouble getNorigtaxmny() {
    return (UFDouble) this.getAttributeValue(InvSummaryViewVO.NORIGTAXMNY);
  }

  /**
   * 
   * @param norigtaxmny
   */
  public void setNorigtaxmny(UFDouble norigtaxmny) {
    this.setAttributeValue(InvSummaryViewVO.NORIGTAXMNY, norigtaxmny);
  }

  /**
   * 
   * @return 税额
   */
  public UFDouble getNtax() {
    return (UFDouble) this.getAttributeValue(InvSummaryViewVO.NTAX);
  }

  /**
   * 
   * @param ntax
   */
  public void setNtax(UFDouble ntax) {
    this.setAttributeValue(InvSummaryViewVO.NTAX, ntax);
  }

  /**
   * 
   * @return 折扣额
   */
  public UFDouble getNorigdiscount() {
    return (UFDouble) this.getAttributeValue(InvSummaryViewVO.NORIGDISCOUNT);
  }

  /**
   * 
   * @param norigdiscount
   */
  public void setNorigdiscount(UFDouble norigdiscount) {
    this.setAttributeValue(InvSummaryViewVO.NORIGDISCOUNT, norigdiscount);
  }

  /**
   * 
   * @return 应收主数量
   */
  public UFDouble getNshouldreceivnum() {
    return (UFDouble) super
        .getAttributeValue(InvSummaryViewVO.NSHOULDRECEIVNUM);
  }

  /**
   * 设置应收主数量
   * 
   * @param nshouldreceivnum
   * 
   */
  public void setNshouldreceivnum(UFDouble nshouldreceivnum) {
    super
        .setAttributeValue(InvSummaryViewVO.NSHOULDRECEIVNUM, nshouldreceivnum);
  }

  /**
   * 设置应收余额
   * 
   * @param nshouldreceivmny
   */
  public void setNshouldreceivmny(UFDouble nshouldreceivmny) {
    super
        .setAttributeValue(InvSummaryViewVO.NSHOULDRECEIVMNY, nshouldreceivmny);
  }

  /**
   * 
   * @return 应收余额
   */
  public UFDouble getNshouldreceivmny() {
    return (UFDouble) super
        .getAttributeValue(InvSummaryViewVO.NSHOULDRECEIVMNY);
  }

  /**
   * 设置应收金额
   * 
   * @param ntotalreceivmny
   */
  public void setNtotalreceivmny(UFDouble ntotalreceivmny) {
    super.setAttributeValue(InvSummaryViewVO.NTOTALRECEIVMNY, ntotalreceivmny);
  }

  /**
   * 
   * @return 应收金额
   */
  public UFDouble getNtotalreceivmny() {
    return (UFDouble) super.getAttributeValue(InvSummaryViewVO.NTOTALRECEIVMNY);
  }

  /**
   * 
   * @return 服务类
   */
  public UFBoolean getBlaborflag() {
    return (UFBoolean) this.getAttributeValue(InvSummaryViewVO.BLABORFLAG);
  }

  /**
   * 
   * @param blaborflag
   */
  public void setBlaborflag(UFBoolean blaborflag) {
    this.setAttributeValue(InvSummaryViewVO.BLABORFLAG, blaborflag);
  }

  /**
   * 
   * @return 折扣类
   */
  public UFBoolean getBdiscountflag() {
    return (UFBoolean) this.getAttributeValue(InvSummaryViewVO.BDISCOUNTFLAG);
  }

  /**
   * 
   * @param bdiscountflag
   */
  public void setBdiscountflag(UFBoolean bdiscountflag) {
    this.setAttributeValue(InvSummaryViewVO.BDISCOUNTFLAG, bdiscountflag);
  }

}
