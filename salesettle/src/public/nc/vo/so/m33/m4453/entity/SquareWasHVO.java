package nc.vo.so.m33.m4453.entity;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class SquareWasHVO extends SuperVO {

  private static final long serialVersionUID = -1617457479336555778L;

  // 途损单待结算清单主实体路径
  public static final String ENTITYNAME = "so.SquareWasHVO";

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
   * 三角贸易
   */
  public static final String BTRIATRADEFLAG = "btriatradeflag";

  /**
   * 业务流程
   */
  public static final String CBIZTYPEID = "cbiztypeid";

  /**
   * 途损结算单主实体
   */
  public static final String CSALESQUAREID = "csalesquareid";

  /**
   * 库存组织最新版本
   */
  public static final String CSENDSTOCKORGID = "csendstockorgid";

  /**
   * 库存组织版本
   */
  public static final String CSENDSTOCKORGVID = "csendstockorgvid";

  /**
   * 仓库
   */
  public static final String CSENDSTORDOCID = "csendstordocid";

  /**
   * 途损单主实体
   */
  public static final String CSQUAREBILLID = "csquarebillid";

  /**
   * 途损单交易类型实体
   */
  public static final String CTRANTYPEID = "ctrantypeid";

  /**
   * 销售出库单库管员
   */
  public static final String CWHSMANAGERID = "cwhsmanagerid";

  /**
   * 途损单单据日期
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
   * 途损单单据号
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
   * 途损单交易类型
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
   * 获取是否自动成本结算
   * 
   * @return 是否自动成本结算
   */
  public UFBoolean getBautosquarecost() {
    return (UFBoolean) this.getAttributeValue(SquareWasHVO.BAUTOSQUARECOST);
  }

  /**
   * 设置是否自动成本结算
   * 
   * @param bautosquarecost 是否自动成本结算
   */
  public void setBautosquarecost(UFBoolean bautosquarecost) {
    this.setAttributeValue(SquareWasHVO.BAUTOSQUARECOST, bautosquarecost);
  }

  /**
   * 获取是否自动收入结算
   * 
   * @return 是否自动收入结算
   */
  public UFBoolean getBautosquareincome() {
    return (UFBoolean) this.getAttributeValue(SquareWasHVO.BAUTOSQUAREINCOME);
  }

  /**
   * 设置是否自动收入结算
   * 
   * @param bautosquareincome 是否自动收入结算
   */
  public void setBautosquareincome(UFBoolean bautosquareincome) {
    this.setAttributeValue(SquareWasHVO.BAUTOSQUAREINCOME, bautosquareincome);
  }

  /**
   * 获取三角贸易
   * 
   * @return 三角贸易
   */
  public UFBoolean getBtriatradeflag() {
    return (UFBoolean) this.getAttributeValue(SquareWasHVO.BTRIATRADEFLAG);
  }

  /**
   * 设置三角贸易
   * 
   * @param btriatradeflag 三角贸易
   */
  public void setBtriatradeflag(UFBoolean btriatradeflag) {
    this.setAttributeValue(SquareWasHVO.BTRIATRADEFLAG, btriatradeflag);
  }

  /**
   * 获取业务流程
   * 
   * @return 业务流程
   */
  public String getCbiztypeid() {
    return (String) this.getAttributeValue(SquareWasHVO.CBIZTYPEID);
  }

  /**
   * 设置业务流程
   * 
   * @param cbiztypeid 业务流程
   */
  public void setCbiztypeid(String cbiztypeid) {
    this.setAttributeValue(SquareWasHVO.CBIZTYPEID, cbiztypeid);
  }

  /**
   * 获取收货国家/地区
   * 
   * @return 收货国家/地区
   */
  public String getCrececountryid() {
    return (String) this.getAttributeValue(SquareWasHVO.CRECECOUNTRYID);
  }

  /**
   * 设置收货国家/地区
   * 
   * @param crececountryid 收货国家/地区
   */
  public void setCrececountryid(String crececountryid) {
    this.setAttributeValue(SquareWasHVO.CRECECOUNTRYID, crececountryid);
  }

  /**
   * 获取途损结算单主实体
   * 
   * @return 途损结算单主实体
   */
  public String getCsalesquareid() {
    return (String) this.getAttributeValue(SquareWasHVO.CSALESQUAREID);
  }

  /**
   * 设置途损结算单主实体
   * 
   * @param csalesquareid 途损结算单主实体
   */
  public void setCsalesquareid(String csalesquareid) {
    this.setAttributeValue(SquareWasHVO.CSALESQUAREID, csalesquareid);
  }

  /**
   * 获取发货国家/地区
   * 
   * @return 发货国家/地区
   */
  public String getCsendcountryid() {
    return (String) this.getAttributeValue(SquareWasHVO.CSENDCOUNTRYID);
  }

  /**
   * 设置发货国家/地区
   * 
   * @param csendcountryid 发货国家/地区
   */
  public void setCsendcountryid(String csendcountryid) {
    this.setAttributeValue(SquareWasHVO.CSENDCOUNTRYID, csendcountryid);
  }

  /**
   * 获取库存组织最新版本
   * 
   * @return 库存组织最新版本
   */
  public String getCsendstockorgid() {
    return (String) this.getAttributeValue(SquareWasHVO.CSENDSTOCKORGID);
  }

  /**
   * 设置库存组织最新版本
   * 
   * @param csendstockorgid 库存组织最新版本
   */
  public void setCsendstockorgid(String csendstockorgid) {
    this.setAttributeValue(SquareWasHVO.CSENDSTOCKORGID, csendstockorgid);
  }

  /**
   * 获取库存组织版本
   * 
   * @return 库存组织版本
   */
  public String getCsendstockorgvid() {
    return (String) this.getAttributeValue(SquareWasHVO.CSENDSTOCKORGVID);
  }

  /**
   * 设置库存组织版本
   * 
   * @param csendstockorgvid 库存组织版本
   */
  public void setCsendstockorgvid(String csendstockorgvid) {
    this.setAttributeValue(SquareWasHVO.CSENDSTOCKORGVID, csendstockorgvid);
  }

  /**
   * 获取仓库
   * 
   * @return 仓库
   */
  public String getCsendstordocid() {
    return (String) this.getAttributeValue(SquareWasHVO.CSENDSTORDOCID);
  }

  /**
   * 设置仓库
   * 
   * @param csendstordocid 仓库
   */
  public void setCsendstordocid(String csendstordocid) {
    this.setAttributeValue(SquareWasHVO.CSENDSTORDOCID, csendstordocid);
  }

  /**
   * 获取途损单主实体
   * 
   * @return 途损单主实体
   */
  public String getCsquarebillid() {
    return (String) this.getAttributeValue(SquareWasHVO.CSQUAREBILLID);
  }

  /**
   * 设置途损单主实体
   * 
   * @param csquarebillid 途损单主实体
   */
  public void setCsquarebillid(String csquarebillid) {
    this.setAttributeValue(SquareWasHVO.CSQUAREBILLID, csquarebillid);
  }

  /**
   * 获取报税国家/地区
   * 
   * @return 报税国家/地区
   */
  public String getCtaxcountryid() {
    return (String) this.getAttributeValue(SquareWasHVO.CTAXCOUNTRYID);
  }

  /**
   * 设置报税国家/地区
   * 
   * @param ctaxcountryid 报税国家/地区
   */
  public void setCtaxcountryid(String ctaxcountryid) {
    this.setAttributeValue(SquareWasHVO.CTAXCOUNTRYID, ctaxcountryid);
  }

  /**
   * 获取途损单交易类型实体
   * 
   * @return 途损单交易类型实体
   */
  public String getCtrantypeid() {
    return (String) this.getAttributeValue(SquareWasHVO.CTRANTYPEID);
  }

  /**
   * 设置途损单交易类型实体
   * 
   * @param ctrantypeid 途损单交易类型实体
   */
  public void setCtrantypeid(String ctrantypeid) {
    this.setAttributeValue(SquareWasHVO.CTRANTYPEID, ctrantypeid);
  }

  /**
   * 获取销售出库单库管员
   * 
   * @return 销售出库单库管员
   */
  public String getCwhsmanagerid() {
    return (String) this.getAttributeValue(SquareWasHVO.CWHSMANAGERID);
  }

  /**
   * 设置销售出库单库管员
   * 
   * @param cwhsmanagerid 销售出库单库管员
   */
  public void setCwhsmanagerid(String cwhsmanagerid) {
    this.setAttributeValue(SquareWasHVO.CWHSMANAGERID, cwhsmanagerid);
  }

  /**
   * 获取途损单单据日期
   * 
   * @return 途损单单据日期
   */
  public UFDate getDbilldate() {
    return (UFDate) this.getAttributeValue(SquareWasHVO.DBILLDATE);
  }

  /**
   * 设置途损单单据日期
   * 
   * @param dbilldate 途损单单据日期
   */
  public void setDbilldate(UFDate dbilldate) {
    this.setAttributeValue(SquareWasHVO.DBILLDATE, dbilldate);
  }

  /**
   * 获取购销类型
   * 
   * @return 购销类型
   */
  public Integer getFbuysellflag() {
    return (Integer) this.getAttributeValue(SquareWasHVO.FBUYSELLFLAG);
  }

  /**
   * 设置购销类型
   * 
   * @param fbuysellflag 购销类型
   */
  public void setFbuysellflag(Integer fbuysellflag) {
    this.setAttributeValue(SquareWasHVO.FBUYSELLFLAG, fbuysellflag);
  }

  /**
   * 获取集团
   * 
   * @return 集团
   */
  public String getPk_group() {
    return (String) this.getAttributeValue(SquareWasHVO.PK_GROUP);
  }

  /**
   * 设置集团
   * 
   * @param pk_group 集团
   */
  public void setPk_group(String pk_group) {
    this.setAttributeValue(SquareWasHVO.PK_GROUP, pk_group);
  }

  /**
   * 获取结算财务组织
   * 
   * @return 结算财务组织
   */
  public String getPk_org() {
    return (String) this.getAttributeValue(SquareWasHVO.PK_ORG);
  }

  /**
   * 设置结算财务组织
   * 
   * @param pk_org 结算财务组织
   */
  public void setPk_org(String pk_org) {
    this.setAttributeValue(SquareWasHVO.PK_ORG, pk_org);
  }

  /**
   * 获取结算财务组织版本
   * 
   * @return 结算财务组织版本
   */
  public String getPk_org_v() {
    return (String) this.getAttributeValue(SquareWasHVO.PK_ORG_V);
  }

  /**
   * 设置结算财务组织版本
   * 
   * @param pk_org_v 结算财务组织版本
   */
  public void setPk_org_v(String pk_org_v) {
    this.setAttributeValue(SquareWasHVO.PK_ORG_V, pk_org_v);
  }

  /**
   * 获取时间戳
   * 
   * @return 时间戳
   */
  public UFDateTime getTs() {
    return (UFDateTime) this.getAttributeValue(SquareWasHVO.TS);
  }

  /**
   * 设置时间戳
   * 
   * @param ts 时间戳
   */
  public void setTs(UFDateTime ts) {
    this.setAttributeValue(SquareWasHVO.TS, ts);
  }

  /**
   * 获取途损单单据号
   * 
   * @return 途损单单据号
   */
  public String getVbillcode() {
    return (String) this.getAttributeValue(SquareWasHVO.VBILLCODE);
  }

  /**
   * 设置途损单单据号
   * 
   * @param vbillcode 途损单单据号
   */
  public void setVbillcode(String vbillcode) {
    this.setAttributeValue(SquareWasHVO.VBILLCODE, vbillcode);
  }

  /**
   * 获取自定义项1
   * 
   * @return 自定义项1
   */
  public String getVdef1() {
    return (String) this.getAttributeValue(SquareWasHVO.VDEF1);
  }

  /**
   * 设置自定义项1
   * 
   * @param vdef1 自定义项1
   */
  public void setVdef1(String vdef1) {
    this.setAttributeValue(SquareWasHVO.VDEF1, vdef1);
  }

  /**
   * 获取自定义项10
   * 
   * @return 自定义项10
   */
  public String getVdef10() {
    return (String) this.getAttributeValue(SquareWasHVO.VDEF10);
  }

  /**
   * 设置自定义项10
   * 
   * @param vdef10 自定义项10
   */
  public void setVdef10(String vdef10) {
    this.setAttributeValue(SquareWasHVO.VDEF10, vdef10);
  }

  /**
   * 获取自定义项11
   * 
   * @return 自定义项11
   */
  public String getVdef11() {
    return (String) this.getAttributeValue(SquareWasHVO.VDEF11);
  }

  /**
   * 设置自定义项11
   * 
   * @param vdef11 自定义项11
   */
  public void setVdef11(String vdef11) {
    this.setAttributeValue(SquareWasHVO.VDEF11, vdef11);
  }

  /**
   * 获取自定义项12
   * 
   * @return 自定义项12
   */
  public String getVdef12() {
    return (String) this.getAttributeValue(SquareWasHVO.VDEF12);
  }

  /**
   * 设置自定义项12
   * 
   * @param vdef12 自定义项12
   */
  public void setVdef12(String vdef12) {
    this.setAttributeValue(SquareWasHVO.VDEF12, vdef12);
  }

  /**
   * 获取自定义项13
   * 
   * @return 自定义项13
   */
  public String getVdef13() {
    return (String) this.getAttributeValue(SquareWasHVO.VDEF13);
  }

  /**
   * 设置自定义项13
   * 
   * @param vdef13 自定义项13
   */
  public void setVdef13(String vdef13) {
    this.setAttributeValue(SquareWasHVO.VDEF13, vdef13);
  }

  /**
   * 获取自定义项14
   * 
   * @return 自定义项14
   */
  public String getVdef14() {
    return (String) this.getAttributeValue(SquareWasHVO.VDEF14);
  }

  /**
   * 设置自定义项14
   * 
   * @param vdef14 自定义项14
   */
  public void setVdef14(String vdef14) {
    this.setAttributeValue(SquareWasHVO.VDEF14, vdef14);
  }

  /**
   * 获取自定义项15
   * 
   * @return 自定义项15
   */
  public String getVdef15() {
    return (String) this.getAttributeValue(SquareWasHVO.VDEF15);
  }

  /**
   * 设置自定义项15
   * 
   * @param vdef15 自定义项15
   */
  public void setVdef15(String vdef15) {
    this.setAttributeValue(SquareWasHVO.VDEF15, vdef15);
  }

  /**
   * 获取自定义项16
   * 
   * @return 自定义项16
   */
  public String getVdef16() {
    return (String) this.getAttributeValue(SquareWasHVO.VDEF16);
  }

  /**
   * 设置自定义项16
   * 
   * @param vdef16 自定义项16
   */
  public void setVdef16(String vdef16) {
    this.setAttributeValue(SquareWasHVO.VDEF16, vdef16);
  }

  /**
   * 获取自定义项17
   * 
   * @return 自定义项17
   */
  public String getVdef17() {
    return (String) this.getAttributeValue(SquareWasHVO.VDEF17);
  }

  /**
   * 设置自定义项17
   * 
   * @param vdef17 自定义项17
   */
  public void setVdef17(String vdef17) {
    this.setAttributeValue(SquareWasHVO.VDEF17, vdef17);
  }

  /**
   * 获取自定义项18
   * 
   * @return 自定义项18
   */
  public String getVdef18() {
    return (String) this.getAttributeValue(SquareWasHVO.VDEF18);
  }

  /**
   * 设置自定义项18
   * 
   * @param vdef18 自定义项18
   */
  public void setVdef18(String vdef18) {
    this.setAttributeValue(SquareWasHVO.VDEF18, vdef18);
  }

  /**
   * 获取自定义项19
   * 
   * @return 自定义项19
   */
  public String getVdef19() {
    return (String) this.getAttributeValue(SquareWasHVO.VDEF19);
  }

  /**
   * 设置自定义项19
   * 
   * @param vdef19 自定义项19
   */
  public void setVdef19(String vdef19) {
    this.setAttributeValue(SquareWasHVO.VDEF19, vdef19);
  }

  /**
   * 获取自定义项2
   * 
   * @return 自定义项2
   */
  public String getVdef2() {
    return (String) this.getAttributeValue(SquareWasHVO.VDEF2);
  }

  /**
   * 设置自定义项2
   * 
   * @param vdef2 自定义项2
   */
  public void setVdef2(String vdef2) {
    this.setAttributeValue(SquareWasHVO.VDEF2, vdef2);
  }

  /**
   * 获取自定义项20
   * 
   * @return 自定义项20
   */
  public String getVdef20() {
    return (String) this.getAttributeValue(SquareWasHVO.VDEF20);
  }

  /**
   * 设置自定义项20
   * 
   * @param vdef20 自定义项20
   */
  public void setVdef20(String vdef20) {
    this.setAttributeValue(SquareWasHVO.VDEF20, vdef20);
  }

  /**
   * 获取自定义项3
   * 
   * @return 自定义项3
   */
  public String getVdef3() {
    return (String) this.getAttributeValue(SquareWasHVO.VDEF3);
  }

  /**
   * 设置自定义项3
   * 
   * @param vdef3 自定义项3
   */
  public void setVdef3(String vdef3) {
    this.setAttributeValue(SquareWasHVO.VDEF3, vdef3);
  }

  /**
   * 获取自定义项4
   * 
   * @return 自定义项4
   */
  public String getVdef4() {
    return (String) this.getAttributeValue(SquareWasHVO.VDEF4);
  }

  /**
   * 设置自定义项4
   * 
   * @param vdef4 自定义项4
   */
  public void setVdef4(String vdef4) {
    this.setAttributeValue(SquareWasHVO.VDEF4, vdef4);
  }

  /**
   * 获取自定义项5
   * 
   * @return 自定义项5
   */
  public String getVdef5() {
    return (String) this.getAttributeValue(SquareWasHVO.VDEF5);
  }

  /**
   * 设置自定义项5
   * 
   * @param vdef5 自定义项5
   */
  public void setVdef5(String vdef5) {
    this.setAttributeValue(SquareWasHVO.VDEF5, vdef5);
  }

  /**
   * 获取自定义项6
   * 
   * @return 自定义项6
   */
  public String getVdef6() {
    return (String) this.getAttributeValue(SquareWasHVO.VDEF6);
  }

  /**
   * 设置自定义项6
   * 
   * @param vdef6 自定义项6
   */
  public void setVdef6(String vdef6) {
    this.setAttributeValue(SquareWasHVO.VDEF6, vdef6);
  }

  /**
   * 获取自定义项7
   * 
   * @return 自定义项7
   */
  public String getVdef7() {
    return (String) this.getAttributeValue(SquareWasHVO.VDEF7);
  }

  /**
   * 设置自定义项7
   * 
   * @param vdef7 自定义项7
   */
  public void setVdef7(String vdef7) {
    this.setAttributeValue(SquareWasHVO.VDEF7, vdef7);
  }

  /**
   * 获取自定义项8
   * 
   * @return 自定义项8
   */
  public String getVdef8() {
    return (String) this.getAttributeValue(SquareWasHVO.VDEF8);
  }

  /**
   * 设置自定义项8
   * 
   * @param vdef8 自定义项8
   */
  public void setVdef8(String vdef8) {
    this.setAttributeValue(SquareWasHVO.VDEF8, vdef8);
  }

  /**
   * 获取自定义项9
   * 
   * @return 自定义项9
   */
  public String getVdef9() {
    return (String) this.getAttributeValue(SquareWasHVO.VDEF9);
  }

  /**
   * 设置自定义项9
   * 
   * @param vdef9 自定义项9
   */
  public void setVdef9(String vdef9) {
    this.setAttributeValue(SquareWasHVO.VDEF9, vdef9);
  }

  /**
   * 获取备注
   * 
   * @return 备注
   */
  public String getVnote() {
    return (String) this.getAttributeValue(SquareWasHVO.VNOTE);
  }

  /**
   * 设置备注
   * 
   * @param vnote 备注
   */
  public void setVnote(String vnote) {
    this.setAttributeValue(SquareWasHVO.VNOTE, vnote);
  }

  /**
   * 获取途损单交易类型
   * 
   * @return 途损单交易类型
   */
  public String getVtrantypecode() {
    return (String) this.getAttributeValue(SquareWasHVO.VTRANTYPECODE);
  }

  /**
   * 设置途损单交易类型
   * 
   * @param vtrantypecode 途损单交易类型
   */
  public void setVtrantypecode(String vtrantypecode) {
    this.setAttributeValue(SquareWasHVO.VTRANTYPECODE, vtrantypecode);
  }

  @Override
  public IVOMeta getMetaData() {
    IVOMeta meta =
        VOMetaFactory.getInstance().getVOMeta(SquareWasHVO.ENTITYNAME);
    return meta;
  }

  public void setDr(Integer dr) {
    this.setAttributeValue(SquareWasHVO.DR, dr);
  }

  public Integer getDr() {
    return (Integer) this.getAttributeValue(SquareWasHVO.DR);
  }
}
