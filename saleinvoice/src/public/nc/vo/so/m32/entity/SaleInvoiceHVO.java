package nc.vo.so.m32.entity;

import nc.vo.credit.creditaudit.entity.CreditAuditHVO;
import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>销售发票主表VO
 * <li>...
 * </ul>
 * <p>
 * <b>变更历史（可选）：</b>
 * <p>
 * XXX版本增加XXX的支持。
 * <p>
 * <p>
 * 
 * @version 本版本号 V60
 * @since 上一版本号
 * @author fengjb
 * @time 2009-7-3 上午09:58:34
 */
public class SaleInvoiceHVO extends SuperVO {

  /**
   * // modify by jilu for EHP1合到633 20140703
   * 结算方式 张云鹏 V63 奥的亮
   */
  public static final String CBALANCETYPEID = "cbalancetypeid";

  // 审批人
  public static final String APPROVER = "approver";

  // 制单人
  public static final String BILLMAKER = "billmaker";

  // 费用冲抵
  public static final String BSUBUNITFLAG = "bsubunitflag";

  // 是否已经传金税
  public static final String BTOGOLDTAXFLAG = "btogoldtaxflag";

  /**
   * 三角贸易(61)
   */
  public static final String BTRIATRADEFLAG = "btriatradeflag";

  // 业务流程
  public static final String CBIZTYPEID = "cbiztypeid";

  // 本位币
  public static final String CCURRENCYID = "ccurrencyid";

  // 客户银行账号
  public static final String CCUSTBANKACCID = "ccustbankaccid";

  // 客户开户银行
  public static final String CCUSTBANKID = "ccustbankid";

  // 客户名称
  public static final String CINVOICECUSTID = "cinvoicecustid";

  // 对冲来源主表
  public static final String COPPOSESRCID = "copposesrcid";

  // 币种
  public static final String CORIGCURRENCYID = "corigcurrencyid";

  // 收付款协议
  public static final String CPAYTERMID = "cpaytermid";

  // 制单时间
  public static final String CREATIONTIME = "creationtime";

  // 创建人
  public static final String CREATOR = "creator";

  /**
   * 收货国家/地区(61)
   */
  public static final String CRECECOUNTRYID = "crececountryid";

  // 发票主实体
  public static final String CSALEINVOICEID = "csaleinvoiceid";

  /**
   * 发货国家/地区(61)
   */
  public static final String CSENDCOUNTRYID = "csendcountryid";

  /**
   * 报税国家/地区(61)
   */
  public static final String CTAXCOUNTRYID = "ctaxcountryid";

  /**
   * 贸易术语（61）
   */
  public static final String CTRADEWORDID = "ctradewordid";

  // 发票类型
  public static final String CTRANTYPEID = "ctrantypeid";

  // 开票日期
  public static final String DBILLDATE = "dbilldate";

  // 制单日期
  public static final String DMAKEDATE = "dmakedate";

  // dr
  public static final String DR = "dr";

  /**
   * 购销类型(61)
   */
  public static final String FBUYSELLFLAG = "fbuysellflag";

  // 对冲标记
  public static final String FOPPOSEFLAG = "fopposeflag";

  // 单据状态
  public static final String FSTATUSFLAG = "fstatusflag";

  // 打印次数
  public static final String IPRINTCOUNT = "iprintcount";

  // 最后修改时间
  public static final String MODIFIEDTIME = "modifiedtime";

  // 最后修改人
  public static final String MODIFIER = "modifier";

  // 折本汇率
  public static final String NEXCHANGERATE = "nexchangerate";

  // 全局本位币汇率
  public static final String NGLOBALEXCHGRATE = "nglobalexchgrate";

  // 集团本位币汇率
  public static final String NGROUPEXCHGRATE = "ngroupexchgrate";

  // 发票折扣
  public static final String NHVOICEDISRATE = "nhvoicedisrate";

  // 总数量
  public static final String NTOTALASTNUM = "ntotalastnum";

  // 价税合计
  public static final String NTOTALORIGMNY = "ntotalorigmny";

  // 冲减金额
  public static final String NTOTALORIGSUBMNY = "ntotalorigsubmny";

  // 集团
  public static final String PK_GROUP = "pk_group";

  // 开票组织
  public static final String PK_ORG = "pk_org";

  // 开票组织版本
  public static final String PK_ORG_V = "pk_org_v";

  // 审批时间
  public static final String TAUDITTIME = "taudittime";

  // 最后传金税时间
  public static final String TGOLDTAXTIME = "tgoldtaxtime";

  // 时间戳
  public static final String TS = "ts";

  // 发票号
  public static final String VBILLCODE = "vbillcode";

  // 信用证号
  public static final String VCREDITNUM = "vcreditnum";

  /**
   * 客户VAT注册码（61）
   */
  public static final String VCUSTVATCODE = "vcustvatcode";

  // 自定义项1
  public static final String VDEF1 = "vdef1";

  // 自定义项10
  public static final String VDEF10 = "vdef10";

  // 自定义项11
  public static final String VDEF11 = "vdef11";

  // 自定义项12
  public static final String VDEF12 = "vdef12";

  // ntotalmny
  // public static final String NTOTALMNY = "ntotalmny";

  // 自定义项13
  public static final String VDEF13 = "vdef13";

  // 自定义项14
  public static final String VDEF14 = "vdef14";

  // 自定义项15
  public static final String VDEF15 = "vdef15";

  // 自定义项16
  public static final String VDEF16 = "vdef16";

  // 自定义项17
  public static final String VDEF17 = "vdef17";

  // 自定义项18
  public static final String VDEF18 = "vdef18";

  // 自定义项19
  public static final String VDEF19 = "vdef19";

  // 自定义项2
  public static final String VDEF2 = "vdef2";

  // 自定义项20
  public static final String VDEF20 = "vdef20";

  // 自定义项3
  public static final String VDEF3 = "vdef3";

  // 自定义项4
  public static final String VDEF4 = "vdef4";

  // 自定义项5
  public static final String VDEF5 = "vdef5";

  // 自定义项6
  public static final String VDEF6 = "vdef6";

  // 自定义项7
  public static final String VDEF7 = "vdef7";

  // 自定义项8
  public static final String VDEF8 = "vdef8";

  // 自定义项9
  public static final String VDEF9 = "vdef9";

  // 金税票号
  public static final String VGOLDTAXCODE = "vgoldtaxcode";

  // 备注
  public static final String VNOTE = "vnote";

  // 对冲来源发票号
  public static final String VOPPOSESRCCODE = "vopposesrccode";

  // 客户打印名称
  public static final String VPRINTCUSTNAME = "vprintcustname";

  // 发票类型编码
  public static final String VTRANTYPECODE = "vtrantypecode";

  /**
   * VAT注册码（61）
   */
  public static final String VVATCODE = "vvatcode";

  /**
   * 
   */
  private static final long serialVersionUID = 6045685362006746144L;

  /**
   * SaleInvoiceHVO 的构造子
   */
  public SaleInvoiceHVO() {
    super();
  }

  public String getApprover() {
    return (String) this.getAttributeValue(SaleInvoiceHVO.APPROVER);
  }

  public String getBillmaker() {
    return (String) this.getAttributeValue(SaleInvoiceHVO.BILLMAKER);
  }

  public UFBoolean getBsubunitflag() {
    return (UFBoolean) this.getAttributeValue(SaleInvoiceHVO.BSUBUNITFLAG);
  }

  public UFBoolean getBtogoldtaxflag() {
    return (UFBoolean) this.getAttributeValue(SaleInvoiceHVO.BTOGOLDTAXFLAG);
  }

  /**
   * 获得三角贸易
   * 
   * @return 三角贸易
   */
  public UFBoolean getBtriatradeflag() {
    return (UFBoolean) this.getAttributeValue(SaleInvoiceHVO.BTRIATRADEFLAG);
  }

  public String getCbiztypeid() {
    return (String) this.getAttributeValue(SaleInvoiceHVO.CBIZTYPEID);
  }

  public String getCcurrencyid() {
    return (String) this.getAttributeValue(SaleInvoiceHVO.CCURRENCYID);
  }

  public String getCcustbankaccid() {
    return (String) this.getAttributeValue(SaleInvoiceHVO.CCUSTBANKACCID);
  }

  public String getCcustbankid() {
    return (String) this.getAttributeValue(SaleInvoiceHVO.CCUSTBANKID);
  }

  public String getCinvoicecustid() {
    return (String) this.getAttributeValue(SaleInvoiceHVO.CINVOICECUSTID);
  }

  public String getCopposesrcid() {
    return (String) this.getAttributeValue(SaleInvoiceHVO.COPPOSESRCID);
  }

  public String getCorigcurrencyid() {
    return (String) this.getAttributeValue(SaleInvoiceHVO.CORIGCURRENCYID);
  }

  public String getCpaytermid() {
    return (String) this.getAttributeValue(SaleInvoiceHVO.CPAYTERMID);
  }

  public UFDateTime getCreationtime() {
    return (UFDateTime) this.getAttributeValue(SaleInvoiceHVO.CREATIONTIME);
  }

  public String getCreator() {
    return (String) this.getAttributeValue(SaleInvoiceHVO.CREATOR);
  }

  /**
   * 获取收货国家/地区
   * 
   * @return 收获国家/地区
   */
  public String getCrececountryid() {
    return (String) this.getAttributeValue(SaleInvoiceHVO.CRECECOUNTRYID);
  }

  public String getCsaleinvoiceid() {
    return (String) this.getAttributeValue(SaleInvoiceHVO.CSALEINVOICEID);
  }

  /**
   * 发货国家/地区
   * 
   * @return 发货国家/地区
   */
  public String getCsendcountryid() {
    return (String) this.getAttributeValue(SaleInvoiceHVO.CSENDCOUNTRYID);
  }

  /**
   * 获得计税国家
   * 
   * @return 计税国家
   */
  public String getCtaxcountryid() {
    return (String) this.getAttributeValue(SaleInvoiceHVO.CTAXCOUNTRYID);
  }

  /**
   * 获得贸易术语
   * 
   * @return 贸易术语
   */
  public String getCtradewordid() {
    return (String) this.getAttributeValue(SaleInvoiceHVO.CTRADEWORDID);
  }

  public String getCtrantypeid() {
    return (String) this.getAttributeValue(SaleInvoiceHVO.CTRANTYPEID);
  }

  public UFDate getDbilldate() {
    return (UFDate) this.getAttributeValue(SaleInvoiceHVO.DBILLDATE);
  }

  public UFDate getDmakedate() {
    return (UFDate) this.getAttributeValue(SaleInvoiceHVO.DMAKEDATE);
  }

  public Integer getDr() {
    return (Integer) this.getAttributeValue(SaleInvoiceHVO.DR);
  }

  /**
   * 获得购销类型
   * 
   * @return 购销类型
   */
  public Integer getFbuysellflag() {
    return (Integer) this.getAttributeValue(SaleInvoiceHVO.FBUYSELLFLAG);
  }

  public Integer getFopposeflag() {
    return (Integer) this.getAttributeValue(SaleInvoiceHVO.FOPPOSEFLAG);
  }

  public Integer getFstatusflag() {
    return (Integer) this.getAttributeValue(SaleInvoiceHVO.FSTATUSFLAG);
  }

  public Integer getIprintcount() {
    return (Integer) this.getAttributeValue(SaleInvoiceHVO.IPRINTCOUNT);
  }

  @Override
  public IVOMeta getMetaData() {
    IVOMeta meta = VOMetaFactory.getInstance().getVOMeta("so.saleinvoice");
    return meta;
  }

  public UFDateTime getModifiedtime() {
    return (UFDateTime) this.getAttributeValue(SaleInvoiceHVO.MODIFIEDTIME);
  }

  public String getModifier() {
    return (String) this.getAttributeValue(SaleInvoiceHVO.MODIFIER);
  }

  public UFDouble getNexchangerate() {
    return (UFDouble) this.getAttributeValue(SaleInvoiceHVO.NEXCHANGERATE);
  }

  public UFDouble getNglobalexchgrate() {
    return (UFDouble) this.getAttributeValue(SaleInvoiceHVO.NGLOBALEXCHGRATE);
  }

  public UFDouble getNgroupexchgrate() {
    return (UFDouble) this.getAttributeValue(SaleInvoiceHVO.NGROUPEXCHGRATE);
  }

  public UFDouble getNhvoicedisrate() {
    return (UFDouble) this.getAttributeValue(SaleInvoiceHVO.NHVOICEDISRATE);
  }

  public UFDouble getNtotalastnum() {
    return (UFDouble) this.getAttributeValue(SaleInvoiceHVO.NTOTALASTNUM);
  }

  public UFDouble getNtotalorigmny() {
    return (UFDouble) this.getAttributeValue(SaleInvoiceHVO.NTOTALORIGMNY);
  }

  public UFDouble getNtotalorigsubmny() {
    return (UFDouble) this.getAttributeValue(SaleInvoiceHVO.NTOTALORIGSUBMNY);
  }

  public String getPk_group() {
    return (String) this.getAttributeValue(SaleInvoiceHVO.PK_GROUP);
  }

  public String getPk_org() {
    return (String) this.getAttributeValue(SaleInvoiceHVO.PK_ORG);
  }

  public String getPk_org_v() {
    return (String) this.getAttributeValue(SaleInvoiceHVO.PK_ORG_V);
  }

  public UFDate getTaudittime() {
    return (UFDate) this.getAttributeValue(SaleInvoiceHVO.TAUDITTIME);
  }

  public UFDateTime getTgoldtaxtime() {
    return (UFDateTime) this.getAttributeValue(SaleInvoiceHVO.TGOLDTAXTIME);
  }

  public UFDateTime getTs() {
    return (UFDateTime) this.getAttributeValue(SaleInvoiceHVO.TS);
  }

  public String getVbillcode() {
    return (String) this.getAttributeValue(SaleInvoiceHVO.VBILLCODE);
  }

  public String getVcreditnum() {
    return (String) this.getAttributeValue(SaleInvoiceHVO.VCREDITNUM);
  }

  // public UFDouble getNtotalmny() {
  // return (UFDouble) this.getAttributeValue(SaleInvoiceHVO.NTOTALMNY);
  // }

  /**
   * 获取VAT注册码
   * 
   * @return VAT注册码
   */
  public String getVcustvatcode() {
    return (String) this.getAttributeValue(SaleInvoiceHVO.VCUSTVATCODE);
  }

  public String getVdef1() {
    return (String) this.getAttributeValue(SaleInvoiceHVO.VDEF1);
  }

  public String getVdef10() {
    return (String) this.getAttributeValue(SaleInvoiceHVO.VDEF10);
  }

  public String getVdef11() {
    return (String) this.getAttributeValue(SaleInvoiceHVO.VDEF11);
  }

  public String getVdef12() {
    return (String) this.getAttributeValue(SaleInvoiceHVO.VDEF12);
  }

  public String getVdef13() {
    return (String) this.getAttributeValue(SaleInvoiceHVO.VDEF13);
  }

  public String getVdef14() {
    return (String) this.getAttributeValue(SaleInvoiceHVO.VDEF14);
  }

  public String getVdef15() {
    return (String) this.getAttributeValue(SaleInvoiceHVO.VDEF15);
  }

  public String getVdef16() {
    return (String) this.getAttributeValue(SaleInvoiceHVO.VDEF16);
  }

  public String getVdef17() {
    return (String) this.getAttributeValue(SaleInvoiceHVO.VDEF17);
  }

  public String getVdef18() {
    return (String) this.getAttributeValue(SaleInvoiceHVO.VDEF18);
  }

  public String getVdef19() {
    return (String) this.getAttributeValue(SaleInvoiceHVO.VDEF19);
  }

  public String getVdef2() {
    return (String) this.getAttributeValue(SaleInvoiceHVO.VDEF2);
  }

  public String getVdef20() {
    return (String) this.getAttributeValue(SaleInvoiceHVO.VDEF20);
  }

  public String getVdef3() {
    return (String) this.getAttributeValue(SaleInvoiceHVO.VDEF3);
  }

  public String getVdef4() {
    return (String) this.getAttributeValue(SaleInvoiceHVO.VDEF4);
  }

  public String getVdef5() {
    return (String) this.getAttributeValue(SaleInvoiceHVO.VDEF5);
  }

  public String getVdef6() {
    return (String) this.getAttributeValue(SaleInvoiceHVO.VDEF6);
  }

  public String getVdef7() {
    return (String) this.getAttributeValue(SaleInvoiceHVO.VDEF7);
  }

  public String getVdef8() {
    return (String) this.getAttributeValue(SaleInvoiceHVO.VDEF8);
  }

  public String getVdef9() {
    return (String) this.getAttributeValue(SaleInvoiceHVO.VDEF9);
  }

  public String getVgoldtaxcode() {
    return (String) this.getAttributeValue(SaleInvoiceHVO.VGOLDTAXCODE);
  }

  public String getVnote() {
    return (String) this.getAttributeValue(SaleInvoiceHVO.VNOTE);
  }

  public String getVopposesrccode() {
    return (String) this.getAttributeValue(SaleInvoiceHVO.VOPPOSESRCCODE);
  }

  public String getVprintcustname() {
    return (String) this.getAttributeValue(SaleInvoiceHVO.VPRINTCUSTNAME);
  }

  public String getVtrantypecode() {
    return (String) this.getAttributeValue(SaleInvoiceHVO.VTRANTYPECODE);
  }

  /**
   * 获取VAT注册码
   * 
   * @return VAT注册码
   */
  public String getVvatcode() {
    return (String) this.getAttributeValue(SaleInvoiceHVO.VVATCODE);
  }

  public void setApprover(String approver) {
    this.setAttributeValue(SaleInvoiceHVO.APPROVER, approver);
  }

  public void setBillmaker(String billmaker) {
    this.setAttributeValue(SaleInvoiceHVO.BILLMAKER, billmaker);
  }

  public void setBsubunitflag(UFBoolean bsubunitflag) {
    this.setAttributeValue(SaleInvoiceHVO.BSUBUNITFLAG, bsubunitflag);
  }

  public void setBtogoldtaxflag(UFBoolean btogoldtaxflag) {
    this.setAttributeValue(SaleInvoiceHVO.BTOGOLDTAXFLAG, btogoldtaxflag);
  }

  /**
   * 设置三角贸易
   * 
   * @param btriatradeflag 三角贸易
   */
  public void setBtriatradeflag(UFBoolean btriatradeflag) {
    this.setAttributeValue(SaleInvoiceHVO.BTRIATRADEFLAG, btriatradeflag);
  }

  public void setCbiztypeid(String cbiztypeid) {
    this.setAttributeValue(SaleInvoiceHVO.CBIZTYPEID, cbiztypeid);
  }

  public void setCcurrencyid(String ccurrencyid) {
    this.setAttributeValue(SaleInvoiceHVO.CCURRENCYID, ccurrencyid);
  }

  public void setCcustbankaccid(String ccustbankaccid) {
    this.setAttributeValue(SaleInvoiceHVO.CCUSTBANKACCID, ccustbankaccid);
  }

  public void setCcustbankid(String ccustbankid) {
    this.setAttributeValue(SaleInvoiceHVO.CCUSTBANKID, ccustbankid);
  }

  public void setCinvoicecustid(String cinvoicecustid) {
    this.setAttributeValue(SaleInvoiceHVO.CINVOICECUSTID, cinvoicecustid);
  }

  public void setCopposesrcid(String copposesrcid) {
    this.setAttributeValue(SaleInvoiceHVO.COPPOSESRCID, copposesrcid);
  }

  public void setCorigcurrencyid(String corigcurrencyid) {
    this.setAttributeValue(SaleInvoiceHVO.CORIGCURRENCYID, corigcurrencyid);
  }

  public void setCpaytermid(String cpaytermid) {
    this.setAttributeValue(SaleInvoiceHVO.CPAYTERMID, cpaytermid);
  }

  public void setCreationtime(UFDateTime creationtime) {
    this.setAttributeValue(SaleInvoiceHVO.CREATIONTIME, creationtime);
  }

  public void setCreator(String creator) {
    this.setAttributeValue(SaleInvoiceHVO.CREATOR, creator);
  }

  /**
   * 设置收货国家/地区
   * 
   * @param crececountryid 收货国家/地区
   */
  public void setCrececountryid(String crececountryid) {
    this.setAttributeValue(SaleInvoiceHVO.CRECECOUNTRYID, crececountryid);
  }

  public void setCsaleinvoiceid(String csaleinvoiceid) {
    this.setAttributeValue(SaleInvoiceHVO.CSALEINVOICEID, csaleinvoiceid);
  }

  /**
   * 设置发货国家/地区
   * 
   * @param csendcountryid 发货国家/地区
   */
  public void setCsendcountryid(String csendcountryid) {
    this.setAttributeValue(SaleInvoiceHVO.CSENDCOUNTRYID, csendcountryid);
  }

  /**
   * 设置计税国家/地区
   * 
   * @param ctaxcountryid 计税国家/地区
   */
  public void setCtaxcountryid(String ctaxcountryid) {
    this.setAttributeValue(SaleInvoiceHVO.CTAXCOUNTRYID, ctaxcountryid);
  }

  public void setCtradewordid(String ctradewordid) {
    this.setAttributeValue(SaleInvoiceHVO.CTRADEWORDID, ctradewordid);
  }

  public void setCtrantypeid(String ctrantypeid) {
    this.setAttributeValue(SaleInvoiceHVO.CTRANTYPEID, ctrantypeid);
  }

  public void setDbilldate(UFDate dbilldate) {
    this.setAttributeValue(SaleInvoiceHVO.DBILLDATE, dbilldate);
  }

  public void setDmakedate(UFDate dmakedate) {
    this.setAttributeValue(CreditAuditHVO.DMAKEDATE, dmakedate);
  }

  public void setDr(Integer dr) {
    this.setAttributeValue(SaleInvoiceHVO.DR, dr);
  }

  public void setFbuysellflag(Integer fbuysellflag) {
    this.setAttributeValue(SaleInvoiceHVO.FBUYSELLFLAG, fbuysellflag);
  }

  public void setFopposeflag(Integer fopposeflag) {
    this.setAttributeValue(SaleInvoiceHVO.FOPPOSEFLAG, fopposeflag);
  }

  public void setFstatusflag(Integer fstatusflag) {
    this.setAttributeValue(SaleInvoiceHVO.FSTATUSFLAG, fstatusflag);
  }

  public void setIprintcount(Integer iprintcount) {
    this.setAttributeValue(SaleInvoiceHVO.IPRINTCOUNT, iprintcount);
  }

  public void setModifiedtime(UFDateTime modifiedtime) {
    this.setAttributeValue(SaleInvoiceHVO.MODIFIEDTIME, modifiedtime);
  }

  public void setModifier(String modifier) {
    this.setAttributeValue(SaleInvoiceHVO.MODIFIER, modifier);
  }

  public void setNexchangerate(UFDouble nexchangerate) {
    this.setAttributeValue(SaleInvoiceHVO.NEXCHANGERATE, nexchangerate);
  }

  public void setNglobalexchgrate(UFDouble nglobalexchgrate) {
    this.setAttributeValue(SaleInvoiceHVO.NGLOBALEXCHGRATE, nglobalexchgrate);
  }

  public void setNgroupexchgrate(UFDouble ngroupexchgrate) {
    this.setAttributeValue(SaleInvoiceHVO.NGROUPEXCHGRATE, ngroupexchgrate);
  }

  public void setNhvoicedisrate(UFDouble nhvoicedisrate) {
    this.setAttributeValue(SaleInvoiceHVO.NHVOICEDISRATE, nhvoicedisrate);
  }

  public void setNtotalastnum(UFDouble ntotalastnum) {
    this.setAttributeValue(SaleInvoiceHVO.NTOTALASTNUM, ntotalastnum);
  }

  public void setNtotalorigmny(UFDouble ntotalorigmny) {
    this.setAttributeValue(SaleInvoiceHVO.NTOTALORIGMNY, ntotalorigmny);
  }

  public void setNtotalorigsubmny(UFDouble ntotalorigsubmny) {
    this.setAttributeValue(SaleInvoiceHVO.NTOTALORIGSUBMNY, ntotalorigsubmny);
  }

  // public void setNtotalmny(UFDouble ntotalmny) {
  // this.setAttributeValue(SaleInvoiceHVO.NTOTALMNY, ntotalmny);
  // }

  public void setPk_group(String pk_group) {
    this.setAttributeValue(SaleInvoiceHVO.PK_GROUP, pk_group);
  }

  public void setPk_org(String pk_org) {
    this.setAttributeValue(SaleInvoiceHVO.PK_ORG, pk_org);
  }

  public void setPk_org_v(String pk_org_v) {
    this.setAttributeValue(SaleInvoiceHVO.PK_ORG_V, pk_org_v);
  }

  public void setTaudittime(UFDate taudittime) {
    this.setAttributeValue(SaleInvoiceHVO.TAUDITTIME, taudittime);
  }

  public void setTgoldtaxtime(UFDateTime tgoldtaxtime) {
    this.setAttributeValue(SaleInvoiceHVO.TGOLDTAXTIME, tgoldtaxtime);
  }

  public void setTs(UFDateTime ts) {
    this.setAttributeValue(SaleInvoiceHVO.TS, ts);
  }

  public void setVbillcode(String vbillcode) {
    this.setAttributeValue(SaleInvoiceHVO.VBILLCODE, vbillcode);
  }

  public void setVcreditnum(String vcreditnum) {
    this.setAttributeValue(SaleInvoiceHVO.VCREDITNUM, vcreditnum);
  }

  public void setVcustvatcode(String vcustvatcode) {
    this.setAttributeValue(SaleInvoiceHVO.VCUSTVATCODE, vcustvatcode);
  }

  public void setVdef1(String vdef1) {
    this.setAttributeValue(SaleInvoiceHVO.VDEF1, vdef1);
  }

  public void setVdef10(String vdef10) {
    this.setAttributeValue(SaleInvoiceHVO.VDEF10, vdef10);
  }

  public void setVdef11(String vdef11) {
    this.setAttributeValue(SaleInvoiceHVO.VDEF11, vdef11);
  }

  public void setVdef12(String vdef12) {
    this.setAttributeValue(SaleInvoiceHVO.VDEF12, vdef12);
  }

  public void setVdef13(String vdef13) {
    this.setAttributeValue(SaleInvoiceHVO.VDEF13, vdef13);
  }

  public void setVdef14(String vdef14) {
    this.setAttributeValue(SaleInvoiceHVO.VDEF14, vdef14);
  }

  public void setVdef15(String vdef15) {
    this.setAttributeValue(SaleInvoiceHVO.VDEF15, vdef15);
  }

  public void setVdef16(String vdef16) {
    this.setAttributeValue(SaleInvoiceHVO.VDEF16, vdef16);
  }

  public void setVdef17(String vdef17) {
    this.setAttributeValue(SaleInvoiceHVO.VDEF17, vdef17);
  }

  public void setVdef18(String vdef18) {
    this.setAttributeValue(SaleInvoiceHVO.VDEF18, vdef18);
  }

  public void setVdef19(String vdef19) {
    this.setAttributeValue(SaleInvoiceHVO.VDEF19, vdef19);
  }

  public void setVdef2(String vdef2) {
    this.setAttributeValue(SaleInvoiceHVO.VDEF2, vdef2);
  }

  public void setVdef20(String vdef20) {
    this.setAttributeValue(SaleInvoiceHVO.VDEF20, vdef20);
  }

  public void setVdef3(String vdef3) {
    this.setAttributeValue(SaleInvoiceHVO.VDEF3, vdef3);
  }

  public void setVdef4(String vdef4) {
    this.setAttributeValue(SaleInvoiceHVO.VDEF4, vdef4);
  }

  public void setVdef5(String vdef5) {
    this.setAttributeValue(SaleInvoiceHVO.VDEF5, vdef5);
  }

  public void setVdef6(String vdef6) {
    this.setAttributeValue(SaleInvoiceHVO.VDEF6, vdef6);
  }

  public void setVdef7(String vdef7) {
    this.setAttributeValue(SaleInvoiceHVO.VDEF7, vdef7);
  }

  public void setVdef8(String vdef8) {
    this.setAttributeValue(SaleInvoiceHVO.VDEF8, vdef8);
  }

  public void setVdef9(String vdef9) {
    this.setAttributeValue(SaleInvoiceHVO.VDEF9, vdef9);
  }

  public void setVgoldtaxcode(String vgoldtaxcode) {
    this.setAttributeValue(SaleInvoiceHVO.VGOLDTAXCODE, vgoldtaxcode);
  }

  public void setVnote(String vnote) {
    this.setAttributeValue(SaleInvoiceHVO.VNOTE, vnote);
  }

  public void setVopposesrccode(String vopposesrccode) {
    this.setAttributeValue(SaleInvoiceHVO.VOPPOSESRCCODE, vopposesrccode);
  }

  public void setVprintcustname(String vprintcustname) {
    this.setAttributeValue(SaleInvoiceHVO.VPRINTCUSTNAME, vprintcustname);
  }

  public void setVtrantypecode(String vtrantypecode) {
    this.setAttributeValue(SaleInvoiceHVO.VTRANTYPECODE, vtrantypecode);
  }

  public void setVvatcode(String vvatcode) {
    this.setAttributeValue(SaleInvoiceHVO.VVATCODE, vvatcode);
  }

  /**
   * 
   * @return 结算方式 张云鹏 V63 奥的亮
   */
  public String getCbalancetypeid() {
    return (String) this.getAttributeValue(SaleInvoiceHVO.CBALANCETYPEID);
  }

  /**
   * 
   * @param cbalancetypeid 结算方式 张云鹏 V63 奥的亮
   */
  public void setCbalancetypeid(String cbalancetypeid) {
    this.setAttributeValue(SaleInvoiceHVO.CBALANCETYPEID, cbalancetypeid);
  }
}
