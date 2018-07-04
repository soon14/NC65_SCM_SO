package nc.vo.so.m33.m32.entity;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;
import nc.vo.so.m32.entity.SaleInvoiceHVO;

/**
 * 销售发票待结算清单主实体
 * 
 * @since 6.0
 * @version 2012-1-5 上午08:52:46
 * @author fengjb
 */
public class SquareInvHVO extends SuperVO {

  private static final long serialVersionUID = -1617457479336555778L;
  
  /** begin 结算方式 张云鹏 V63 奥的亮 */
  public static String CBALANCETYPEID = "cbalancetypeid";

  /**
   * 
   * 获取结算方式
   * 
   * @return 结算方式
   */
  public String getCbalancetypeid() {
    return (String) this.getAttributeValue(SaleInvoiceHVO.CBALANCETYPEID);
  }

  /**
   * 设置结算方式
   * 
   * @param cbalancetypeid
   */
  public void setCbalancetypeid(String cbalancetypeid) {
    this.setAttributeValue(SaleInvoiceHVO.CBALANCETYPEID, cbalancetypeid);
  }

  /** end 结算方式 张云鹏 V63 奥的亮 */

  // 销售发票待结算清单主实体路径
  public static final String ENTITYNAME = "so.SquareInvHVO";

  // 删除标志dr
  public static final String DR = "dr";

  /**
   * 是否自动成本结算
   */
  public static final String BAUTOSQUARECOST = "bautosquarecost";

  /**
   * 是否自动收入结算
   */
  public static final String BAUTOSQUAREINCOME = "bautosquareincome";

  /**
   * 三角贸易(V61)
   */
  public static final String BTRIATRADEFLAG = "btriatradeflag";

  /**
   * 业务流程
   */
  public static final String CBIZTYPEID = "cbiztypeid";

  /**
   * 开户银行账户
   */
  public static final String CCUSTBANKACCID = "ccustbankaccid";

  /**
   * 开票客户
   */
  public static final String CINVOICECUSTID = "cinvoicecustid";

  /**
   * 发票收付款协议
   */
  public static final String CPAYTERMID = "cpaytermid";

  /**
   * 销售发票结算单主实体
   */
  public static final String CSALESQUAREID = "csalesquareid";

  /**
   * 销售发票主实体
   */
  public static final String CSQUAREBILLID = "csquarebillid";

  /**
   * 销售发票交易类型实体
   */
  public static final String CTRANTYPEID = "ctrantypeid";

  /**
   * 销售发票审核日期
   */
  public static final String DBILLAPPROVEDATE = "dbillapprovedate";

  /**
   * 销售发票单据日期
   */
  public static final String DBILLDATE = "dbilldate";

  /**
   * 集团
   */
  public static final String PK_GROUP = "pk_group";

  /**
   * 结算财务组织
   */
  public static final String PK_ORG = "pk_org";

  /**
   * 结算财务组织版本
   */
  public static final String PK_ORG_V = "pk_org_v";

  /**
   * 时间戳
   */
  public static final String TS = "ts";

  /**
   * 销售发票单据号
   */
  public static final String VBILLCODE = "vbillcode";

  /**
   * 自定义项1
   */
  public static final String VDEF1 = "vdef1";

  /**
   * 自定义项10
   */
  public static final String VDEF10 = "vdef10";

  /**
   * 自定义项11
   */
  public static final String VDEF11 = "vdef11";

  /**
   * 自定义项12
   */
  public static final String VDEF12 = "vdef12";

  /**
   * 自定义项13
   */
  public static final String VDEF13 = "vdef13";

  /**
   * 自定义项14
   */
  public static final String VDEF14 = "vdef14";

  /**
   * 自定义项15
   */
  public static final String VDEF15 = "vdef15";

  /**
   * 自定义项16
   */
  public static final String VDEF16 = "vdef16";

  /**
   * 自定义项17
   */
  public static final String VDEF17 = "vdef17";

  /**
   * 自定义项18
   */
  public static final String VDEF18 = "vdef18";

  /**
   * 自定义项19
   */
  public static final String VDEF19 = "vdef19";

  /**
   * 自定义项2
   */
  public static final String VDEF2 = "vdef2";

  /**
   * 自定义项20
   */
  public static final String VDEF20 = "vdef20";

  /**
   * 自定义项3
   */
  public static final String VDEF3 = "vdef3";

  /**
   * 自定义项4
   */
  public static final String VDEF4 = "vdef4";

  /**
   * 自定义项5
   */
  public static final String VDEF5 = "vdef5";

  /**
   * 自定义项6
   */
  public static final String VDEF6 = "vdef6";

  /**
   * 自定义项7
   */
  public static final String VDEF7 = "vdef7";

  /**
   * 自定义项8
   */
  public static final String VDEF8 = "vdef8";

  /**
   * 自定义项9
   */
  public static final String VDEF9 = "vdef9";

  /**
   * 备注
   */
  public static final String VNOTE = "vnote";

  /**
   * 销售发票交易类型
   */
  public static final String VTRANTYPECODE = "vtrantypecode";

  /******* V61新增字段 *******/
  /**
   * 收货国家/地区
   */
  public static final String CRECECOUNTRYID = "crececountryid";

  /**
   * 发货国家/地区
   */
  public static final String CSENDCOUNTRYID = "csendcountryid";

  /**
   * 报税国家/地区
   */
  public static final String CTAXCOUNTRYID = "ctaxcountryid";

  /**
   * 购销类型
   */
  public static final String FBUYSELLFLAG = "fbuysellflag";

  /**
   * VAT注册码
   */
  public static final String VVATCODE = "vvatcode";

  /**
   * 客户VAT注册码
   */
  public static final String VCUSTVATCODE = "vcustvatcode";

  /**
   * 获取是否自动成本结算
   * 
   * @return 是否自动成本结算
   */
  public UFBoolean getBautosquarecost() {
    return (UFBoolean) this.getAttributeValue(SquareInvHVO.BAUTOSQUARECOST);
  }

  /**
   * 设置是否自动成本结算
   * 
   * @param bautosquarecost 是否自动成本结算
   */
  public void setBautosquarecost(UFBoolean bautosquarecost) {
    this.setAttributeValue(SquareInvHVO.BAUTOSQUARECOST, bautosquarecost);
  }

  /**
   * 获取是否自动收入结算
   * 
   * @return 是否自动收入结算
   */
  public UFBoolean getBautosquareincome() {
    return (UFBoolean) this.getAttributeValue(SquareInvHVO.BAUTOSQUAREINCOME);
  }

  /**
   * 设置是否自动收入结算
   * 
   * @param bautosquareincome 是否自动收入结算
   */
  public void setBautosquareincome(UFBoolean bautosquareincome) {
    this.setAttributeValue(SquareInvHVO.BAUTOSQUAREINCOME, bautosquareincome);
  }

  /**
   * 获取三角贸易
   * 
   * @return 三角贸易
   */
  public UFBoolean getBtriatradeflag() {
    return (UFBoolean) this.getAttributeValue(SquareInvHVO.BTRIATRADEFLAG);
  }

  /**
   * 设置三角贸易
   * 
   * @param btriatradeflag 三角贸易
   */
  public void setBtriatradeflag(UFBoolean btriatradeflag) {
    this.setAttributeValue(SquareInvHVO.BTRIATRADEFLAG, btriatradeflag);
  }

  /**
   * 获取业务流程
   * 
   * @return 业务流程
   */
  public String getCbiztypeid() {
    return (String) this.getAttributeValue(SquareInvHVO.CBIZTYPEID);
  }

  /**
   * 设置业务流程
   * 
   * @param cbiztypeid 业务流程
   */
  public void setCbiztypeid(String cbiztypeid) {
    this.setAttributeValue(SquareInvHVO.CBIZTYPEID, cbiztypeid);
  }

  /**
   * 获取开户银行账户
   * 
   * @return 开户银行账户
   */
  public String getCcustbankaccid() {
    return (String) this.getAttributeValue(SquareInvHVO.CCUSTBANKACCID);
  }

  /**
   * 设置开户银行账户
   * 
   * @param ccustbankaccid 开户银行账户
   */
  public void setCcustbankaccid(String ccustbankaccid) {
    this.setAttributeValue(SquareInvHVO.CCUSTBANKACCID, ccustbankaccid);
  }

  /**
   * 获取开票客户
   * 
   * @return 开票客户
   */
  public String getCinvoicecustid() {
    return (String) this.getAttributeValue(SquareInvHVO.CINVOICECUSTID);
  }

  /**
   * 设置开票客户
   * 
   * @param cinvoicecustid 开票客户
   */
  public void setCinvoicecustid(String cinvoicecustid) {
    this.setAttributeValue(SquareInvHVO.CINVOICECUSTID, cinvoicecustid);
  }

  /**
   * 获取发票收付款协议
   * 
   * @return 发票收付款协议
   */
  public String getCpaytermid() {
    return (String) this.getAttributeValue(SquareInvHVO.CPAYTERMID);
  }

  /**
   * 设置发票收付款协议
   * 
   * @param cpaytermid 发票收付款协议
   */
  public void setCpaytermid(String cpaytermid) {
    this.setAttributeValue(SquareInvHVO.CPAYTERMID, cpaytermid);
  }

  /**
   * 获取收货国家/地区
   * 
   * @return 收货国家/地区
   */
  public String getCrececountryid() {
    return (String) this.getAttributeValue(SquareInvHVO.CRECECOUNTRYID);
  }

  /**
   * 设置收货国家/地区
   * 
   * @param crececountryid 收货国家/地区
   */
  public void setCrececountryid(String crececountryid) {
    this.setAttributeValue(SquareInvHVO.CRECECOUNTRYID, crececountryid);
  }

  /**
   * 获取销售发票结算单主实体
   * 
   * @return 销售发票结算单主实体
   */
  public String getCsalesquareid() {
    return (String) this.getAttributeValue(SquareInvHVO.CSALESQUAREID);
  }

  /**
   * 设置销售发票结算单主实体
   * 
   * @param csalesquareid 销售发票结算单主实体
   */
  public void setCsalesquareid(String csalesquareid) {
    this.setAttributeValue(SquareInvHVO.CSALESQUAREID, csalesquareid);
  }

  /**
   * 获取发货国家/地区
   * 
   * @return 发货国家/地区
   */
  public String getCsendcountryid() {
    return (String) this.getAttributeValue(SquareInvHVO.CSENDCOUNTRYID);
  }

  /**
   * 设置发货国家/地区
   * 
   * @param csendcountryid 发货国家/地区
   */
  public void setCsendcountryid(String csendcountryid) {
    this.setAttributeValue(SquareInvHVO.CSENDCOUNTRYID, csendcountryid);
  }

  /**
   * 获取销售发票主实体
   * 
   * @return 销售发票主实体
   */
  public String getCsquarebillid() {
    return (String) this.getAttributeValue(SquareInvHVO.CSQUAREBILLID);
  }

  /**
   * 设置销售发票主实体
   * 
   * @param csquarebillid 销售发票主实体
   */
  public void setCsquarebillid(String csquarebillid) {
    this.setAttributeValue(SquareInvHVO.CSQUAREBILLID, csquarebillid);
  }

  /**
   * 获取报税国家/地区
   * 
   * @return 报税国家/地区
   */
  public String getCtaxcountryid() {
    return (String) this.getAttributeValue(SquareInvHVO.CTAXCOUNTRYID);
  }

  /**
   * 设置报税国家/地区
   * 
   * @param ctaxcountryid 报税国家/地区
   */
  public void setCtaxcountryid(String ctaxcountryid) {
    this.setAttributeValue(SquareInvHVO.CTAXCOUNTRYID, ctaxcountryid);
  }

  /**
   * 获取销售发票交易类型实体
   * 
   * @return 销售发票交易类型实体
   */
  public String getCtrantypeid() {
    return (String) this.getAttributeValue(SquareInvHVO.CTRANTYPEID);
  }

  /**
   * 设置销售发票交易类型实体
   * 
   * @param ctrantypeid 销售发票交易类型实体
   */
  public void setCtrantypeid(String ctrantypeid) {
    this.setAttributeValue(SquareInvHVO.CTRANTYPEID, ctrantypeid);
  }

  /**
   * 获取销售发票审核日期
   * 
   * @return 销售发票审核日期
   */
  public UFDate getDbillapprovedate() {
    return (UFDate) this.getAttributeValue(SquareInvHVO.DBILLAPPROVEDATE);
  }

  /**
   * 设置销售发票审核日期
   * 
   * @param dbillapprovedate 销售发票审核日期
   */
  public void setDbillapprovedate(UFDate dbillapprovedate) {
    this.setAttributeValue(SquareInvHVO.DBILLAPPROVEDATE, dbillapprovedate);
  }

  /**
   * 获取销售发票单据日期
   * 
   * @return 销售发票单据日期
   */
  public UFDate getDbilldate() {
    return (UFDate) this.getAttributeValue(SquareInvHVO.DBILLDATE);
  }

  /**
   * 设置销售发票单据日期
   * 
   * @param dbilldate 销售发票单据日期
   */
  public void setDbilldate(UFDate dbilldate) {
    this.setAttributeValue(SquareInvHVO.DBILLDATE, dbilldate);
  }

  /**
   * 获取购销类型
   * 
   * @return 购销类型
   */
  public Integer getFbuysellflag() {
    return (Integer) this.getAttributeValue(SquareInvHVO.FBUYSELLFLAG);
  }

  /**
   * 设置购销类型
   * 
   * @param fbuysellflag 购销类型
   */
  public void setFbuysellflag(Integer fbuysellflag) {
    this.setAttributeValue(SquareInvHVO.FBUYSELLFLAG, fbuysellflag);
  }

  /**
   * 获取集团
   * 
   * @return 集团
   */
  public String getPk_group() {
    return (String) this.getAttributeValue(SquareInvHVO.PK_GROUP);
  }

  /**
   * 设置集团
   * 
   * @param pk_group 集团
   */
  public void setPk_group(String pk_group) {
    this.setAttributeValue(SquareInvHVO.PK_GROUP, pk_group);
  }

  /**
   * 获取结算财务组织
   * 
   * @return 结算财务组织
   */
  public String getPk_org() {
    return (String) this.getAttributeValue(SquareInvHVO.PK_ORG);
  }

  /**
   * 设置结算财务组织
   * 
   * @param pk_org 结算财务组织
   */
  public void setPk_org(String pk_org) {
    this.setAttributeValue(SquareInvHVO.PK_ORG, pk_org);
  }

  /**
   * 获取结算财务组织版本
   * 
   * @return 结算财务组织版本
   */
  public String getPk_org_v() {
    return (String) this.getAttributeValue(SquareInvHVO.PK_ORG_V);
  }

  /**
   * 设置结算财务组织版本
   * 
   * @param pk_org_v 结算财务组织版本
   */
  public void setPk_org_v(String pk_org_v) {
    this.setAttributeValue(SquareInvHVO.PK_ORG_V, pk_org_v);
  }

  /**
   * 获取时间戳
   * 
   * @return 时间戳
   */
  public UFDateTime getTs() {
    return (UFDateTime) this.getAttributeValue(SquareInvHVO.TS);
  }

  /**
   * 设置时间戳
   * 
   * @param ts 时间戳
   */
  public void setTs(UFDateTime ts) {
    this.setAttributeValue(SquareInvHVO.TS, ts);
  }

  /**
   * 获取销售发票单据号
   * 
   * @return 销售发票单据号
   */
  public String getVbillcode() {
    return (String) this.getAttributeValue(SquareInvHVO.VBILLCODE);
  }

  /**
   * 设置销售发票单据号
   * 
   * @param vbillcode 销售发票单据号
   */
  public void setVbillcode(String vbillcode) {
    this.setAttributeValue(SquareInvHVO.VBILLCODE, vbillcode);
  }

  /**
   * 获取客户VAT注册码
   * 
   * @return 客户VAT注册码
   */
  public String getVcustvatcode() {
    return (String) this.getAttributeValue(SquareInvHVO.VCUSTVATCODE);
  }

  /**
   * 设置客户VAT注册码
   * 
   * @param vcustvatcode 客户VAT注册码
   */
  public void setVcustvatcode(String vcustvatcode) {
    this.setAttributeValue(SquareInvHVO.VCUSTVATCODE, vcustvatcode);
  }

  /**
   * 获取自定义项1
   * 
   * @return 自定义项1
   */
  public String getVdef1() {
    return (String) this.getAttributeValue(SquareInvHVO.VDEF1);
  }

  /**
   * 设置自定义项1
   * 
   * @param vdef1 自定义项1
   */
  public void setVdef1(String vdef1) {
    this.setAttributeValue(SquareInvHVO.VDEF1, vdef1);
  }

  /**
   * 获取自定义项10
   * 
   * @return 自定义项10
   */
  public String getVdef10() {
    return (String) this.getAttributeValue(SquareInvHVO.VDEF10);
  }

  /**
   * 设置自定义项10
   * 
   * @param vdef10 自定义项10
   */
  public void setVdef10(String vdef10) {
    this.setAttributeValue(SquareInvHVO.VDEF10, vdef10);
  }

  /**
   * 获取自定义项11
   * 
   * @return 自定义项11
   */
  public String getVdef11() {
    return (String) this.getAttributeValue(SquareInvHVO.VDEF11);
  }

  /**
   * 设置自定义项11
   * 
   * @param vdef11 自定义项11
   */
  public void setVdef11(String vdef11) {
    this.setAttributeValue(SquareInvHVO.VDEF11, vdef11);
  }

  /**
   * 获取自定义项12
   * 
   * @return 自定义项12
   */
  public String getVdef12() {
    return (String) this.getAttributeValue(SquareInvHVO.VDEF12);
  }

  /**
   * 设置自定义项12
   * 
   * @param vdef12 自定义项12
   */
  public void setVdef12(String vdef12) {
    this.setAttributeValue(SquareInvHVO.VDEF12, vdef12);
  }

  /**
   * 获取自定义项13
   * 
   * @return 自定义项13
   */
  public String getVdef13() {
    return (String) this.getAttributeValue(SquareInvHVO.VDEF13);
  }

  /**
   * 设置自定义项13
   * 
   * @param vdef13 自定义项13
   */
  public void setVdef13(String vdef13) {
    this.setAttributeValue(SquareInvHVO.VDEF13, vdef13);
  }

  /**
   * 获取自定义项14
   * 
   * @return 自定义项14
   */
  public String getVdef14() {
    return (String) this.getAttributeValue(SquareInvHVO.VDEF14);
  }

  /**
   * 设置自定义项14
   * 
   * @param vdef14 自定义项14
   */
  public void setVdef14(String vdef14) {
    this.setAttributeValue(SquareInvHVO.VDEF14, vdef14);
  }

  /**
   * 获取自定义项15
   * 
   * @return 自定义项15
   */
  public String getVdef15() {
    return (String) this.getAttributeValue(SquareInvHVO.VDEF15);
  }

  /**
   * 设置自定义项15
   * 
   * @param vdef15 自定义项15
   */
  public void setVdef15(String vdef15) {
    this.setAttributeValue(SquareInvHVO.VDEF15, vdef15);
  }

  /**
   * 获取自定义项16
   * 
   * @return 自定义项16
   */
  public String getVdef16() {
    return (String) this.getAttributeValue(SquareInvHVO.VDEF16);
  }

  /**
   * 设置自定义项16
   * 
   * @param vdef16 自定义项16
   */
  public void setVdef16(String vdef16) {
    this.setAttributeValue(SquareInvHVO.VDEF16, vdef16);
  }

  /**
   * 获取自定义项17
   * 
   * @return 自定义项17
   */
  public String getVdef17() {
    return (String) this.getAttributeValue(SquareInvHVO.VDEF17);
  }

  /**
   * 设置自定义项17
   * 
   * @param vdef17 自定义项17
   */
  public void setVdef17(String vdef17) {
    this.setAttributeValue(SquareInvHVO.VDEF17, vdef17);
  }

  /**
   * 获取自定义项18
   * 
   * @return 自定义项18
   */
  public String getVdef18() {
    return (String) this.getAttributeValue(SquareInvHVO.VDEF18);
  }

  /**
   * 设置自定义项18
   * 
   * @param vdef18 自定义项18
   */
  public void setVdef18(String vdef18) {
    this.setAttributeValue(SquareInvHVO.VDEF18, vdef18);
  }

  /**
   * 获取自定义项19
   * 
   * @return 自定义项19
   */
  public String getVdef19() {
    return (String) this.getAttributeValue(SquareInvHVO.VDEF19);
  }

  /**
   * 设置自定义项19
   * 
   * @param vdef19 自定义项19
   */
  public void setVdef19(String vdef19) {
    this.setAttributeValue(SquareInvHVO.VDEF19, vdef19);
  }

  /**
   * 获取自定义项2
   * 
   * @return 自定义项2
   */
  public String getVdef2() {
    return (String) this.getAttributeValue(SquareInvHVO.VDEF2);
  }

  /**
   * 设置自定义项2
   * 
   * @param vdef2 自定义项2
   */
  public void setVdef2(String vdef2) {
    this.setAttributeValue(SquareInvHVO.VDEF2, vdef2);
  }

  /**
   * 获取自定义项20
   * 
   * @return 自定义项20
   */
  public String getVdef20() {
    return (String) this.getAttributeValue(SquareInvHVO.VDEF20);
  }

  /**
   * 设置自定义项20
   * 
   * @param vdef20 自定义项20
   */
  public void setVdef20(String vdef20) {
    this.setAttributeValue(SquareInvHVO.VDEF20, vdef20);
  }

  /**
   * 获取自定义项3
   * 
   * @return 自定义项3
   */
  public String getVdef3() {
    return (String) this.getAttributeValue(SquareInvHVO.VDEF3);
  }

  /**
   * 设置自定义项3
   * 
   * @param vdef3 自定义项3
   */
  public void setVdef3(String vdef3) {
    this.setAttributeValue(SquareInvHVO.VDEF3, vdef3);
  }

  /**
   * 获取自定义项4
   * 
   * @return 自定义项4
   */
  public String getVdef4() {
    return (String) this.getAttributeValue(SquareInvHVO.VDEF4);
  }

  /**
   * 设置自定义项4
   * 
   * @param vdef4 自定义项4
   */
  public void setVdef4(String vdef4) {
    this.setAttributeValue(SquareInvHVO.VDEF4, vdef4);
  }

  /**
   * 获取自定义项5
   * 
   * @return 自定义项5
   */
  public String getVdef5() {
    return (String) this.getAttributeValue(SquareInvHVO.VDEF5);
  }

  /**
   * 设置自定义项5
   * 
   * @param vdef5 自定义项5
   */
  public void setVdef5(String vdef5) {
    this.setAttributeValue(SquareInvHVO.VDEF5, vdef5);
  }

  /**
   * 获取自定义项6
   * 
   * @return 自定义项6
   */
  public String getVdef6() {
    return (String) this.getAttributeValue(SquareInvHVO.VDEF6);
  }

  /**
   * 设置自定义项6
   * 
   * @param vdef6 自定义项6
   */
  public void setVdef6(String vdef6) {
    this.setAttributeValue(SquareInvHVO.VDEF6, vdef6);
  }

  /**
   * 获取自定义项7
   * 
   * @return 自定义项7
   */
  public String getVdef7() {
    return (String) this.getAttributeValue(SquareInvHVO.VDEF7);
  }

  /**
   * 设置自定义项7
   * 
   * @param vdef7 自定义项7
   */
  public void setVdef7(String vdef7) {
    this.setAttributeValue(SquareInvHVO.VDEF7, vdef7);
  }

  /**
   * 获取自定义项8
   * 
   * @return 自定义项8
   */
  public String getVdef8() {
    return (String) this.getAttributeValue(SquareInvHVO.VDEF8);
  }

  /**
   * 设置自定义项8
   * 
   * @param vdef8 自定义项8
   */
  public void setVdef8(String vdef8) {
    this.setAttributeValue(SquareInvHVO.VDEF8, vdef8);
  }

  /**
   * 获取自定义项9
   * 
   * @return 自定义项9
   */
  public String getVdef9() {
    return (String) this.getAttributeValue(SquareInvHVO.VDEF9);
  }

  /**
   * 设置自定义项9
   * 
   * @param vdef9 自定义项9
   */
  public void setVdef9(String vdef9) {
    this.setAttributeValue(SquareInvHVO.VDEF9, vdef9);
  }

  /**
   * 获取备注
   * 
   * @return 备注
   */
  public String getVnote() {
    return (String) this.getAttributeValue(SquareInvHVO.VNOTE);
  }

  /**
   * 设置备注
   * 
   * @param vnote 备注
   */
  public void setVnote(String vnote) {
    this.setAttributeValue(SquareInvHVO.VNOTE, vnote);
  }

  /**
   * 获取销售发票交易类型
   * 
   * @return 销售发票交易类型
   */
  public String getVtrantypecode() {
    return (String) this.getAttributeValue(SquareInvHVO.VTRANTYPECODE);
  }

  /**
   * 设置销售发票交易类型
   * 
   * @param vtrantypecode 销售发票交易类型
   */
  public void setVtrantypecode(String vtrantypecode) {
    this.setAttributeValue(SquareInvHVO.VTRANTYPECODE, vtrantypecode);
  }

  /**
   * 获取VAT注册码
   * 
   * @return VAT注册码
   */
  public String getVvatcode() {
    return (String) this.getAttributeValue(SquareInvHVO.VVATCODE);
  }

  /**
   * 设置VAT注册码
   * 
   * @param vvatcode VAT注册码
   */
  public void setVvatcode(String vvatcode) {
    this.setAttributeValue(SquareInvHVO.VVATCODE, vvatcode);
  }

  @Override
  public IVOMeta getMetaData() {
    IVOMeta meta =
        VOMetaFactory.getInstance().getVOMeta(SquareInvHVO.ENTITYNAME);
    return meta;
  }

  public void setDr(Integer dr) {
    this.setAttributeValue(SquareInvHVO.DR, dr);
  }

  public Integer getDr() {
    return (Integer) this.getAttributeValue(SquareInvHVO.DR);
  }
}
