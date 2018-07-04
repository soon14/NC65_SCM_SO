package nc.vo.so.pub.keyvalue;

/**
 * 销售管理组单据默认的字段映射关系实现
 * 
 * @since 6.0
 * @version 2012-2-6 上午08:54:09
 * @author fengjb
 */
public class SOKeyRela implements IKeyRela {

  /** 集团 */
  private String pk_group = "pk_group";

  /** 主组织 */
  private String pk_org = "pk_org";
  
  /** 主组织 */
  private String pk_org_v = "pk_org_v";

  /** 业务流程 */
  private String cbiztypeid = "cbiztypeid";

  /** 交易类型编码 */
  private String vtrantypecode = "vtrantypecode";

  /** 交易类型 */
  private String ctrantypeid = "ctrantypeid";

  /** 单据号 */
  private String vbillcode = "vbillcode";

  /** 单据日期 */
  private String dbilldate = "dbilldate";

  /** 单据状态 */
  private String fstatusflag = "fstatusflag";

  /** 审批流状态 */
  private String fpfstatusflag = "fpfstatusflag";

  /** 制单人 */
  private String billmaker = "billmaker";

  /** 制单日期 */
  private String dmakedate = "dmakedate";

  /** 创建人 */
  private String creator = "creator";

  /** 创建时间 */
  private String creationtime = "creationtime";

  /** 修改人 */
  private String modifier = "modifier";

  /** 修改时间 */
  private String modifiedtime = "modifiedtime";

  /** 审批人 */
  private String approver = "approver";

  /** 审批时间 */
  private String taudittime = "taudittime";

  /** 合计数量 */
  private String ntotalnum = "ntotalnum";

  /** 金额合计 */
  private String ntotalorigmny = "ntotalorigmny";

  /** 删除标志 */
  private String dr = "dr";

  /** 原币 */
  private String corigcurrencyid = "corigcurrencyid";

  /** 客户 */
  private String ccustomerid = "ccustomerid";

  /** 开票客户 */
  private String cinvoicecustid = "cinvoicecustid";

  /** 散户 */
  private String cfreecustid = "cfreecustid";

  /** 部门 */
  private String cdeptid = "cdeptid";

  /** 部门最新版本 */
  private String cdeptvid = "cdeptvid";

  /** 业务员 */
  private String cemployeeid = "cemployeeid";

  /** 结算方式 */
  private String cbalancetypeid = "cbalancetypeid";

  /** 销售渠道类型 */
  private String cchanneltypeid = "cchanneltypeid";

  /** 开户银行 */
  private String ccustbankid = "ccustbankid";

  /** 开户银行账户 */
  private String ccustbankaccid = "ccustbankaccid";

  /** 收款协议 */
  private String cpaytermid = "cpaytermid";

  /** 运输方式 */
  private String ctransporttypeid = "ctransporttypeid";

  /** 行号 */
  private String crowno = "crowno";

  /** 物料 */
  private String cmaterialvid = "cmaterialvid";

  /** 物料最新版本 */
  private String cmaterialid = "cmaterialid";

  /** 产品线 */
  private String cprodlineid = "cprodlineid";

  /** 劳务标志 */
  private String blaborflag = "blaborflag";

  /** 折扣标志 */
  private String bdiscountflag = "bdiscountflag";

  /** 赠品标志 */
  private String blargessflag = "blargessflag";

  /** 生产厂商 */
  private String cproductorid = "cproductorid";

  /** 项目 */
  private String cprojectid = "cprojectid";

  /** 供应商 */
  private String cvendorid = "cvendorid";

  /** 工厂 */
  private String cfactoryid = "cfactoryid";

  /** 质量等级 */
  private String cqualitylevelid = "cqualitylevelid";

  /** 自由辅助属性1 */
  private String vfree1 = "vfree1";

  /** 自由辅助属性2 */
  private String vfree2 = "vfree2";

  /** 自由辅助属性3 */
  private String vfree3 = "vfree3";

  /** 自由辅助属性4 */
  private String vfree4 = "vfree4";

  /** 自由辅助属性5 */
  private String vfree5 = "vfree5";

  /** 自由辅助属性6 */
  private String vfree6 = "vfree6";

  /** 自由辅助属性7 */
  private String vfree7 = "vfree7";

  /** 自由辅助属性8 */
  private String vfree8 = "vfree8";

  /** 自由辅助属性9 */
  private String vfree9 = "vfree9";

  /** 自由辅助属性10 */
  private String vfree10 = "vfree10";

  /** 主单位 */
  private String cunitid = "cunitid";

  /** 单位 */
  private String castunitid = "castunitid";

  /** 换算率 */
  private String vchangerate = "vchangerate";

  /** 报价单位 */
  private String cqtunitid = "cqtunitid";

  /** 报价换算率 */
  private String vqtunitrate = "vqtunitrate";

  /** 主数量 */
  private String nnum = "nnum";

  /** 数量 */
  private String nastnum = "nastnum";

  /** 报价单位数量 */
  private String nqtunitnum = "nqtunitnum";

  /** 税率 */
  private String ntaxrate = "ntaxrate";

  /** 整单折扣 */
  private String ndiscountrate = "ndiscountrate";

  /** 单品折扣 */
  private String nitemdiscountrate = "nitemdiscountrate";

  /** 本位币 */
  private String ccurrencyid = "ccurrencyid";

  /** 折本汇率 */
  private String nexchangerate = "nexchangerate";

  /** 含税单价 */
  private String nqtorigtaxprice = "nqtorigtaxprice";

  /** 无税单价 */
  private String nqtorigprice = "nqtorigprice";

  /** 含税净价 */
  private String nqtorigtaxnetprc = "nqtorigtaxnetprc";

  /** 无税净价 */
  private String nqtorignetprice = "nqtorignetprice";

  /** 主单位含税单价 */
  private String norigtaxprice = "norigtaxprice";

  /** 主单位无税单价 */
  private String norigprice = "norigprice";

  /** 主单位含税净价 */
  private String norigtaxnetprice = "norigtaxnetprice";

  /** 主单位无税净价 */
  private String norignetprice = "norignetprice";

  /** 无税金额 */
  private String norigmny = "norigmny";

  /** 价税合计 */
  private String norigtaxmny = "norigtaxmny";

  /** 折扣额 */
  private String norigdiscount = "norigdiscount";

  /** 本币含税单价 */
  private String nqttaxprice = "nqttaxprice";

  /** 本币无税单价 */
  private String nqtprice = "nqtprice";

  /** 本币含税净价 */
  private String nqttaxnetprice = "nqttaxnetprice";

  /** 本币无税净价 */
  private String nqtnetprice = "nqtnetprice";

  /** 主本币含税单价 */
  private String ntaxprice = "ntaxprice";

  /** 主本币无税单价 */
  private String nprice = "nprice";

  /** 主本币含税净价 */
  private String ntaxnetprice = "ntaxnetprice";

  /** 主本币无税净价 */
  private String nnetprice = "nnetprice";

  /** 本币税额 */
  private String ntax = "ntax";

  /** 本币无税金额 */
  private String nmny = "nmny";

  /** 本币价税合计 */
  private String ntaxmny = "ntaxmny";

  /** 本币折扣额 */
  private String ndiscount = "ndiscount";

  /** 全局折本汇率 */
  private String nglobalexchgrate = "nglobalexchgrate";

  /** 全局本币无税金额 */
  private String nglobalmny = "nglobalmny";

  /** 全局本币价税合计 */
  private String nglobaltaxmny = "nglobaltaxmny";

  /** 集团折本汇率 */
  private String ngroupexchgrate = "ngroupexchgrate";

  /** 集团本币无税金额 */
  private String ngroupmny = "ngroupmny";

  /** 集团本币价税合计 */
  private String ngrouptaxmny = "ngrouptaxmny";

  /** 询价原币含税单价 */
  private String naskqtorigtaxprc = "naskqtorigtaxprc";

  /** 询价原币含税净价 */
  private String naskqtorigtxntprc = "naskqtorigtxntprc";

  /** 询价原币无税净价 */
  private String naskqtorignetprice = "naskqtorignetprice";

  /** 询价原币无税单价 */
  private String naskqtorigprice = "naskqtorigprice";

  /** 价格组成 */
  private String cpriceformid = "cpriceformid";

  /** 价格项 */
  private String cpriceitemid = "cpriceitemid";

  /** 价目表 */
  private String cpriceitemtableid = "cpriceitemtableid";

  /** 定价策略 */
  private String cpricepolicyid = "cpricepolicyid";

  /** 到货日期 */
  private String dreceivedate = "dreceivedate";

  /** 发货日期 */
  private String dsenddate = "dsenddate";

  /** 收货客户 */
  private String creceivecustid = "creceivecustid";

  /** 收货地点 */
  private String creceiveadddocid = "creceiveadddocid";

  /** 收货地址 */
  private String creceiveaddrid = "creceiveaddrid";

  /** 收货地区 */
  private String creceiveareaid = "creceiveareaid";

  /** 物流组织最新版本 */
  private String ctrafficorgid = "ctrafficorgid";

  /** 物流组织 */
  private String ctrafficorgvid = "ctrafficorgvid";
  
  /** 物流组织最新版本 */
  private String csaleorgid = "csaleorgid";

  /** 物流组织 */
  private String csaleorgvid = "csaleorgvid";

  /** 发货库存组织最新版本 */
  private String csendstockorgid = "csendstockorgid";

  /** 发货库存组织 */
  private String csendstockorgvid = "csendstockorgvid";

  /** 发货仓库 */
  private String csendstordocid = "csendstordocid";

  /** 结算财务组织最新版本 */
  private String csettleorgid = "csettleorgid";

  /** 结算财务组织 */
  private String csettleorgvid = "csettleorgvid";

  /** 应收组织 最新版本 */
  private String carorgid = "carorgid";

  /** 应收组织 */
  private String carorgvid = "carorgvid";

  /** 利润中心最新版本 */
  private String cprofitcenterid = "cprofitcenterid";

  /** 利润中心 */
  private String cprofitcentervid = "cprofitcentervid";

  /** 源头单据行id */
  private String cfirstbid = "cfirstbid";

  /** 行状态 */
  private String frowstatus = "frowstatus";

  /** 赠品价格分摊方式 */
  private String flargesstypeflag = "flargesstypeflag";

  /** 收货国家/地区 */
  private String crececountryid = "crececountryid";

  /** 发货国家/地区 */
  private String csendcountryid = "csendcountryid";

  /** 报税国家/地区 */
  private String ctaxcountryid = "ctaxcountryid";

  /** 三角贸易 */
  private String btriatradeflag = "btriatradeflag";

  /** 原产地区 */
  private String corigareaid = "corigareaid";

  /** 原产国 */
  private String corigcountryid = "corigcountryid";

  /** 贸易术语 */
  private String ctradewordid = "ctradewordid";

  /** 税码 */
  private String ctaxcodeid = "ctaxcodeid";

  /** 扣税类别 */
  private String ftaxtypeflag = "ftaxtypeflag";

  /** 购销类型 */
  private String fbuysellflag = "fbuysellflag";

  /** 计税金额 */
  private String ncaltaxmny = "ncaltaxmny";

  @Override
  public String getPk_groupKey() {
    return this.pk_group;
  }

  @Override
  public void setPk_groupKey(String key) {
    this.pk_group = key;
  }

  @Override
  public String getPk_orgKey() {
    return this.pk_org;
  }

  @Override
  public void setPk_orgKey(String key) {
    this.pk_org = key;
  }

  @Override
  public String getCbiztypeidKey() {
    return this.cbiztypeid;
  }

  @Override
  public void setCbiztypeidKey(String key) {
    this.cbiztypeid = key;
  }

  @Override
  public String getVtrantypecodeKey() {
    return this.vtrantypecode;
  }

  @Override
  public void setVtrantypecodeKey(String key) {
    this.vtrantypecode = key;
  }

  @Override
  public String getCtrantypeidKey() {
    return this.ctrantypeid;
  }

  @Override
  public void setCtrantypeidKey(String key) {
    this.ctrantypeid = key;
  }

  @Override
  public String getVbillcodeKey() {
    return this.vbillcode;
  }

  @Override
  public void setVbillcodeKey(String key) {
    this.vbillcode = key;
  }

  @Override
  public String getDbilldateKey() {
    return this.dbilldate;
  }

  @Override
  public void setDbilldateKey(String key) {
    this.dbilldate = key;
  }

  @Override
  public String getFstatusflagKey() {
    return this.fstatusflag;
  }

  @Override
  public void setFstatusflagKey(String key) {
    this.fstatusflag = key;
  }

  @Override
  public String getFpfstatusflagKey() {
    return this.fpfstatusflag;
  }

  @Override
  public void setFpfstatusflagKey(String key) {
    this.fpfstatusflag = key;
  }

  @Override
  public String getBillmakerKey() {
    return this.billmaker;
  }

  @Override
  public void setBillmakerKey(String key) {
    this.billmaker = key;
  }

  @Override
  public String getDmakedateKey() {
    return this.dmakedate;
  }

  @Override
  public void setDmakedateKey(String key) {
    this.dmakedate = key;
  }

  @Override
  public String getCreatorKey() {
    return this.creator;
  }

  @Override
  public void setCreatorKey(String key) {
    this.creator = key;
  }

  @Override
  public String getCreationtimeKey() {
    return this.creationtime;
  }

  @Override
  public void setCreationtimeKey(String key) {
    this.creationtime = key;
  }

  @Override
  public String getModifierKey() {
    return this.modifier;
  }

  @Override
  public void setModifierKey(String key) {
    this.modifier = key;
  }

  @Override
  public String getModifiedtimeKey() {
    return this.modifiedtime;
  }

  @Override
  public void setModifiedtimeKey(String key) {
    this.modifiedtime = key;
  }

  @Override
  public String getApproverKey() {
    return this.approver;
  }

  @Override
  public void setApproverKey(String key) {
    this.approver = key;
  }

  @Override
  public String getTaudittimeKey() {
    return this.taudittime;
  }

  @Override
  public void setTaudittimeKey(String key) {
    this.taudittime = key;
  }

  @Override
  public String getNtotalnumKey() {
    return this.ntotalnum;
  }

  @Override
  public void setNtotalnumKey(String key) {
    this.ntotalnum = key;
  }

  @Override
  public String getNtotalorigmnyKey() {
    return this.ntotalorigmny;
  }

  @Override
  public void setNtotalorigmnyKey(String key) {
    this.ntotalorigmny = key;
  }

  @Override
  public String getDrKey() {
    return this.dr;
  }

  @Override
  public void setDrKey(String key) {
    this.dr = key;
  }

  @Override
  public String getCorigcurrencyidKey() {
    return this.corigcurrencyid;
  }

  @Override
  public void setCorigcurrencyidKey(String key) {
    this.corigcurrencyid = key;
  }

  @Override
  public String getCcustomeridKey() {
    return this.ccustomerid;
  }

  @Override
  public void setCcustomeridKey(String key) {
    this.ccustomerid = key;
  }

  @Override
  public String getCinvoicecustidKey() {
    return this.cinvoicecustid;
  }

  @Override
  public void setCinvoicecustidKey(String key) {
    this.cinvoicecustid = key;
  }

  @Override
  public String getCfreecustidKey() {
    return this.cfreecustid;
  }

  @Override
  public void setCfreecustidKey(String key) {
    this.cfreecustid = key;
  }

  @Override
  public String getCdeptidKey() {
    return this.cdeptid;
  }

  @Override
  public void setCdeptidKey(String key) {
    this.cdeptid = key;
  }

  @Override
  public String getCdeptvidKey() {
    return this.cdeptvid;
  }

  @Override
  public void setCdeptvidKey(String key) {
    this.cdeptvid = key;
  }

  @Override
  public String getCemployeeidKey() {
    return this.cemployeeid;
  }

  @Override
  public void setCemployeeidKey(String key) {
    this.cemployeeid = key;
  }

  @Override
  public String getCbalancetypeidKey() {
    return this.cbalancetypeid;
  }

  @Override
  public void setCbalancetypeidKey(String key) {
    this.cbalancetypeid = key;
  }

  @Override
  public String getCchanneltypeidKey() {
    return this.cchanneltypeid;
  }

  @Override
  public void setCchanneltypeidKey(String key) {
    this.cchanneltypeid = key;
  }

  @Override
  public String getCcustbankidKey() {
    return this.ccustbankid;
  }

  @Override
  public void setCcustbankidKey(String key) {
    this.ccustbankid = key;
  }

  @Override
  public String getCcustbankaccidKey() {
    return this.ccustbankaccid;
  }

  @Override
  public void setCcustbankaccidKey(String key) {
    this.ccustbankaccid = key;
  }

  @Override
  public String getCpaytermidKey() {
    return this.cpaytermid;
  }

  @Override
  public void setCpaytermidKey(String key) {
    this.cpaytermid = key;
  }

  @Override
  public String getCtransporttypeidKey() {
    return this.ctransporttypeid;
  }

  @Override
  public void setCtransporttypeidKey(String key) {
    this.ctransporttypeid = key;
  }

  @Override
  public String getCrownoKey() {
    return this.crowno;
  }

  @Override
  public void setCrownoKey(String key) {
    this.crowno = key;
  }

  @Override
  public String getCmaterialvidKey() {
    return this.cmaterialvid;
  }

  @Override
  public void setCmaterialvidKey(String key) {
    this.cmaterialvid = key;
  }

  @Override
  public String getCmaterialidKey() {
    return this.cmaterialid;
  }

  @Override
  public void setCmaterialidKey(String key) {
    this.cmaterialid = key;
  }

  @Override
  public String getCprodlineidKey() {
    return this.cprodlineid;
  }

  @Override
  public void setCprodlineidKey(String key) {
    this.cprodlineid = key;
  }

  @Override
  public String getBlaborflagKey() {
    return this.blaborflag;
  }

  @Override
  public void setBlaborflagKey(String key) {
    this.blaborflag = key;
  }

  @Override
  public String getBdiscountflagKey() {
    return this.bdiscountflag;
  }

  @Override
  public void setBdiscountflagKey(String key) {
    this.bdiscountflag = key;
  }

  @Override
  public String getBlargessflagKey() {
    return this.blargessflag;
  }

  @Override
  public void setBlargessflagKey(String key) {
    this.blargessflag = key;
  }

  @Override
  public String getCproductoridKey() {
    return this.cproductorid;
  }

  @Override
  public void setCproductoridKey(String key) {
    this.cproductorid = key;
  }

  @Override
  public String getCprojectidKey() {
    return this.cprojectid;
  }

  @Override
  public void setCprojectidKey(String key) {
    this.cprojectid = key;
  }

  @Override
  public String getCvendoridKey() {
    return this.cvendorid;
  }

  @Override
  public void setCvendoridKey(String key) {
    this.cvendorid = key;
  }

  @Override
  public String getCfactoryidKey() {
    return this.cfactoryid;
  }

  @Override
  public void setCfactoryidKey(String key) {
    this.cfactoryid = key;
  }

  @Override
  public String getCqualitylevelidKey() {
    return this.cqualitylevelid;
  }

  @Override
  public void setCqualitylevelidKey(String key) {
    this.cqualitylevelid = key;
  }

  @Override
  public String getVfree1Key() {
    return this.vfree1;
  }

  @Override
  public void setVfree1Key(String key) {
    this.vfree1 = key;
  }

  @Override
  public String getVfree2Key() {
    return this.vfree2;
  }

  @Override
  public void setVfree2Key(String key) {
    this.vfree2 = key;
  }

  @Override
  public String getVfree3Key() {
    return this.vfree3;
  }

  @Override
  public void setVfree3Key(String key) {
    this.vfree3 = key;
  }

  @Override
  public String getVfree4Key() {
    return this.vfree4;
  }

  @Override
  public void setVfree4Key(String key) {
    this.vfree4 = key;
  }

  @Override
  public String getVfree5Key() {
    return this.vfree5;
  }

  @Override
  public void setVfree5Key(String key) {
    this.vfree5 = key;
  }

  @Override
  public String getVfree6Key() {
    return this.vfree6;
  }

  @Override
  public void setVfree6Key(String key) {
    this.vfree6 = key;
  }

  @Override
  public String getVfree7Key() {
    return this.vfree7;
  }

  @Override
  public void setVfree7Key(String key) {
    this.vfree7 = key;
  }

  @Override
  public String getVfree8Key() {
    return this.vfree8;
  }

  @Override
  public void setVfree8Key(String key) {
    this.vfree8 = key;
  }

  @Override
  public String getVfree9Key() {
    return this.vfree9;
  }

  @Override
  public void setVfree9Key(String key) {
    this.vfree9 = key;
  }

  @Override
  public String getVfree10Key() {
    return this.vfree10;
  }

  @Override
  public void setVfree10Key(String key) {
    this.vfree10 = key;
  }

  @Override
  public String getCunitidKey() {
    return this.cunitid;
  }

  @Override
  public void setCunitidKey(String key) {
    this.cunitid = key;
  }

  @Override
  public String getCastunitidKey() {
    return this.castunitid;
  }

  @Override
  public void setCastunitidKey(String key) {
    this.castunitid = key;
  }

  @Override
  public String getVchangerateKey() {
    return this.vchangerate;
  }

  @Override
  public void setVchangerateKey(String key) {
    this.vchangerate = key;
  }

  @Override
  public String getCqtunitidKey() {
    return this.cqtunitid;
  }

  @Override
  public void setCqtunitidKey(String key) {
    this.cqtunitid = key;
  }

  @Override
  public String getVqtunitrateKey() {
    return this.vqtunitrate;
  }

  @Override
  public void setVqtunitrateKey(String key) {
    this.vqtunitrate = key;
  }

  @Override
  public String getNnumKey() {
    return this.nnum;
  }

  @Override
  public void setNnumKey(String key) {
    this.nnum = key;
  }

  @Override
  public String getNastnumKey() {
    return this.nastnum;
  }

  @Override
  public void setNastnumKey(String key) {
    this.nastnum = key;
  }

  @Override
  public String getNqtunitnumKey() {
    return this.nqtunitnum;
  }

  @Override
  public void setNqtunitnumKey(String key) {
    this.nqtunitnum = key;
  }

  @Override
  public String getNtaxrateKey() {
    return this.ntaxrate;
  }

  @Override
  public void setNtaxrateKey(String key) {
    this.ntaxrate = key;
  }

  @Override
  public String getNdiscountrateKey() {
    return this.ndiscountrate;
  }

  @Override
  public void setNdiscountrateKey(String key) {
    this.ndiscountrate = key;
  }

  @Override
  public String getNitemdiscountrateKey() {
    return this.nitemdiscountrate;
  }

  @Override
  public void setNitemdiscountrateKey(String key) {
    this.nitemdiscountrate = key;
  }

  @Override
  public String getCcurrencyidKey() {
    return this.ccurrencyid;
  }

  @Override
  public void setCcurrencyidKey(String key) {
    this.ccurrencyid = key;
  }

  @Override
  public String getNexchangerateKey() {
    return this.nexchangerate;
  }

  @Override
  public void setNexchangerateKey(String key) {
    this.nexchangerate = key;
  }

  @Override
  public String getNqtorigtaxpriceKey() {
    return this.nqtorigtaxprice;
  }

  @Override
  public void setNqtorigtaxpriceKey(String key) {
    this.nqtorigtaxprice = key;
  }

  @Override
  public String getNqtorigpriceKey() {
    return this.nqtorigprice;
  }

  @Override
  public void setNqtorigpriceKey(String key) {
    this.nqtorigprice = key;
  }

  @Override
  public String getNqtorigtaxnetprcKey() {
    return this.nqtorigtaxnetprc;
  }

  @Override
  public void setNqtorigtaxnetprcKey(String key) {
    this.nqtorigtaxnetprc = key;
  }

  @Override
  public String getNqtorignetpriceKey() {
    return this.nqtorignetprice;
  }

  @Override
  public void setNqtorignetpriceKey(String key) {
    this.nqtorignetprice = key;
  }

  @Override
  public String getNorigtaxpriceKey() {
    return this.norigtaxprice;
  }

  @Override
  public void setNorigtaxpriceKey(String key) {
    this.norigtaxprice = key;
  }

  @Override
  public String getNorigpriceKey() {
    return this.norigprice;
  }

  @Override
  public void setNorigpriceKey(String key) {
    this.norigprice = key;
  }

  @Override
  public String getNorigtaxnetpriceKey() {
    return this.norigtaxnetprice;
  }

  @Override
  public void setNorigtaxnetpriceKey(String key) {
    this.norigtaxnetprice = key;
  }

  @Override
  public String getNorignetpriceKey() {
    return this.norignetprice;
  }

  @Override
  public void setNorignetpriceKey(String key) {
    this.norignetprice = key;
  }

  @Override
  public String getNorigmnyKey() {
    return this.norigmny;
  }

  @Override
  public void setNorigmnyKey(String key) {
    this.norigmny = key;
  }

  @Override
  public String getNorigtaxmnyKey() {
    return this.norigtaxmny;
  }

  @Override
  public void setNorigtaxmnyKey(String key) {
    this.norigtaxmny = key;
  }

  @Override
  public String getNorigdiscountKey() {
    return this.norigdiscount;
  }

  @Override
  public void setNorigdiscountKey(String key) {
    this.norigdiscount = key;
  }

  @Override
  public String getNqttaxpriceKey() {
    return this.nqttaxprice;
  }

  @Override
  public void setNqttaxpriceKey(String key) {
    this.nqttaxprice = key;
  }

  @Override
  public String getNqtpriceKey() {
    return this.nqtprice;
  }

  @Override
  public void setNqtpriceKey(String key) {
    this.nqtprice = key;
  }

  @Override
  public String getNqttaxnetpriceKey() {
    return this.nqttaxnetprice;
  }

  @Override
  public void setNqttaxnetpriceKey(String key) {
    this.nqttaxnetprice = key;
  }

  @Override
  public String getNqtnetpriceKey() {
    return this.nqtnetprice;
  }

  @Override
  public void setNqtnetpriceKey(String key) {
    this.nqtnetprice = key;
  }

  @Override
  public String getNtaxpriceKey() {
    return this.ntaxprice;
  }

  @Override
  public void setNtaxpriceKey(String key) {
    this.ntaxprice = key;
  }

  @Override
  public String getNpriceKey() {
    return this.nprice;
  }

  @Override
  public void setNpriceKey(String key) {
    this.nprice = key;
  }

  @Override
  public String getNtaxnetpriceKey() {
    return this.ntaxnetprice;
  }

  @Override
  public void setNtaxnetpriceKey(String key) {
    this.ntaxnetprice = key;
  }

  @Override
  public String getNnetpriceKey() {
    return this.nnetprice;
  }

  @Override
  public void setNnetpriceKey(String key) {
    this.nnetprice = key;
  }

  @Override
  public String getNtaxKey() {
    return this.ntax;
  }

  @Override
  public void setNtaxKey(String key) {
    this.ntax = key;
  }

  @Override
  public String getNmnyKey() {
    return this.nmny;
  }

  @Override
  public void setNmnyKey(String key) {
    this.nmny = key;
  }

  @Override
  public String getNtaxmnyKey() {
    return this.ntaxmny;
  }

  @Override
  public void setNtaxmnyKey(String key) {
    this.ntaxmny = key;
  }

  @Override
  public String getNdiscountKey() {
    return this.ndiscount;
  }

  @Override
  public void setNdiscountKey(String key) {
    this.ndiscount = key;
  }

  @Override
  public String getNglobalexchgrateKey() {
    return this.nglobalexchgrate;
  }

  @Override
  public void setNglobalexchgrateKey(String key) {
    this.nglobalexchgrate = key;
  }

  @Override
  public String getNglobalmnyKey() {
    return this.nglobalmny;
  }

  @Override
  public void setNglobalmnyKey(String key) {
    this.nglobalmny = key;
  }

  @Override
  public String getNglobaltaxmnyKey() {
    return this.nglobaltaxmny;
  }

  @Override
  public void setNglobaltaxmnyKey(String key) {
    this.nglobaltaxmny = key;
  }

  @Override
  public String getNgroupexchgrateKey() {
    return this.ngroupexchgrate;
  }

  @Override
  public void setNgroupexchgrateKey(String key) {
    this.ngroupexchgrate = key;
  }

  @Override
  public String getNgroupmnyKey() {
    return this.ngroupmny;
  }

  @Override
  public void setNgroupmnyKey(String key) {
    this.ngroupmny = key;
  }

  @Override
  public String getNgrouptaxmnyKey() {
    return this.ngrouptaxmny;
  }

  @Override
  public void setNgrouptaxmnyKey(String key) {
    this.ngrouptaxmny = key;
  }

  @Override
  public String getNaskqtorigtaxprcKey() {
    return this.naskqtorigtaxprc;
  }

  @Override
  public void setNaskqtorigtaxprcKey(String key) {
    this.naskqtorigtaxprc = key;
  }

  @Override
  public String getNaskqtorigtxntprcKey() {
    return this.naskqtorigtxntprc;
  }

  @Override
  public void setNaskqtorigtxntprcKey(String key) {
    this.naskqtorigtxntprc = key;
  }

  @Override
  public String getNaskqtorignetpriceKey() {
    return this.naskqtorignetprice;
  }

  @Override
  public void setNaskqtorignetpriceKey(String key) {
    this.naskqtorignetprice = key;
  }

  @Override
  public String getNaskqtorigpriceKey() {
    return this.naskqtorigprice;
  }

  @Override
  public void setNaskqtorigpriceKey(String key) {
    this.naskqtorigprice = key;
  }

  @Override
  public String getCpriceformidKey() {
    return this.cpriceformid;
  }

  @Override
  public void setCpriceformidKey(String key) {
    this.cpriceformid = key;
  }

  @Override
  public String getCpriceitemidKey() {
    return this.cpriceitemid;
  }

  @Override
  public void setCpriceitemidKey(String key) {
    this.cpriceitemid = key;
  }

  @Override
  public String getCpriceitemtableidKey() {
    return this.cpriceitemtableid;
  }

  @Override
  public void setCpriceitemtableidKey(String key) {
    this.cpriceitemtableid = key;
  }

  @Override
  public String getCpricepolicyidKey() {
    return this.cpricepolicyid;
  }

  @Override
  public void setCpricepolicyidKey(String key) {
    this.cpricepolicyid = key;
  }

  @Override
  public String getDreceivedateKey() {
    return this.dreceivedate;
  }

  @Override
  public void setDreceivedateKey(String key) {
    this.dreceivedate = key;
  }

  @Override
  public String getDsenddateKey() {
    return this.dsenddate;
  }

  @Override
  public void setDsenddateKey(String key) {
    this.dsenddate = key;
  }

  @Override
  public String getCreceivecustidKey() {
    return this.creceivecustid;
  }

  @Override
  public void setCreceivecustidKey(String key) {
    this.creceivecustid = key;
  }

  @Override
  public String getCreceiveadddocidKey() {
    return this.creceiveadddocid;
  }

  @Override
  public void setCreceiveadddocidKey(String key) {
    this.creceiveadddocid = key;
  }

  @Override
  public String getCreceiveaddridKey() {
    return this.creceiveaddrid;
  }

  @Override
  public void setCreceiveaddridKey(String key) {
    this.creceiveaddrid = key;
  }

  @Override
  public String getCreceiveareaidKey() {
    return this.creceiveareaid;
  }

  @Override
  public void setCreceiveareaidKey(String key) {
    this.creceiveareaid = key;
  }

  @Override
  public String getCtrafficorgidKey() {
    return this.ctrafficorgid;
  }

  @Override
  public void setCtrafficorgidKey(String key) {
    this.ctrafficorgid = key;
  }

  @Override
  public String getCtrafficorgvidKey() {
    return this.ctrafficorgvid;
  }

  @Override
  public void setCsaleorgvidKey(String key) {
    this.csaleorgvid = key;
  }
  
  @Override
  public String getCsaleorgidKey() {
    return this.csaleorgid;
  }

  @Override
  public void setCsaleorgidKey(String key) {
    this.csaleorgid = key;
  }

  @Override
  public String getCsaleorgvidKey() {
    return this.csaleorgvid;
  }

  @Override
  public void setCtrafficorgvidKey(String key) {
    this.ctrafficorgvid = key;
  }

  @Override
  public String getCsendstockorgidKey() {
    return this.csendstockorgid;
  }

  @Override
  public void setCsendstockorgidKey(String key) {
    this.csendstockorgid = key;
  }

  @Override
  public String getCsendstockorgvidKey() {
    return this.csendstockorgvid;
  }

  @Override
  public void setCsendstockorgvidKey(String key) {
    this.csendstockorgvid = key;
  }

  @Override
  public String getCsendstordocidKey() {
    return this.csendstordocid;
  }

  @Override
  public void setCsendstordocidKey(String key) {
    this.csendstordocid = key;
  }

  @Override
  public String getCsettleorgidKey() {
    return this.csettleorgid;
  }

  @Override
  public void setCsettleorgidKey(String key) {
    this.csettleorgid = key;
  }

  @Override
  public String getCsettleorgvidKey() {
    return this.csettleorgvid;
  }

  @Override
  public void setCsettleorgvidKey(String key) {
    this.csettleorgvid = key;
  }

  @Override
  public String getCarorgidKey() {
    return this.carorgid;
  }

  @Override
  public void setCarorgidKey(String key) {
    this.carorgid = key;
  }

  @Override
  public String getCarorgvidKey() {
    return this.carorgvid;
  }

  @Override
  public void setCarorgvidKey(String key) {
    this.carorgvid = key;
  }

  @Override
  public String getCprofitcenteridKey() {
    return this.cprofitcenterid;
  }

  @Override
  public void setCprofitcenteridKey(String key) {
    this.cprofitcenterid = key;
  }

  @Override
  public String getCprofitcentervidKey() {
    return this.cprofitcentervid;
  }

  @Override
  public void setCprofitcentervidKey(String key) {
    this.cprofitcentervid = key;
  }

  @Override
  public String getCfirstbidKey() {
    return this.cfirstbid;
  }

  @Override
  public void setCfirstbidKey(String key) {
    this.cfirstbid = key;
  }

  @Override
  public String getFrowstatusKey() {
    return this.frowstatus;
  }

  @Override
  public void setFrowstatusKey(String key) {
    this.frowstatus = key;
  }

  @Override
  public String getFlargesstypeflagKey() {
    return this.flargesstypeflag;
  }

  @Override
  public void setFlargesstypeflagKey(String key) {
    this.flargesstypeflag = key;
  }

  @Override
  public String getCrececountryidKey() {
    return this.crececountryid;
  }

  @Override
  public void setCrececountryidKey(String key) {
    this.crececountryid = key;
  }

  @Override
  public String getCsendcountryidKey() {
    return this.csendcountryid;
  }

  @Override
  public void setCsendcountryidKey(String key) {
    this.csendcountryid = key;
  }

  @Override
  public String getCtaxcountryidKey() {
    return this.ctaxcountryid;
  }

  @Override
  public void setCtaxcountryidKey(String key) {
    this.ctaxcountryid = key;
  }

  @Override
  public String getBtriatradeflagKey() {
    return this.btriatradeflag;
  }

  @Override
  public void setBtriatradeflagKey(String key) {
    this.btriatradeflag = key;
  }

  @Override
  public String getCorigareaidKey() {
    return this.corigareaid;
  }

  @Override
  public void setCorigareaidKey(String key) {
    this.corigareaid = key;
  }

  @Override
  public String getCorigcountryidKey() {
    return this.corigcountryid;
  }

  @Override
  public void setCorigcountryidKey(String key) {
    this.corigcountryid = key;
  }

  @Override
  public String getCtradewordidKey() {
    return this.ctradewordid;
  }

  @Override
  public void setCtradewordidKey(String key) {
    this.ctradewordid = key;
  }

  @Override
  public String getCtaxcodeidKey() {
    return this.ctaxcodeid;
  }

  @Override
  public void setCtaxcodeidKey(String key) {
    this.ctaxcodeid = key;
  }

  @Override
  public String getFtaxtypeflagKey() {
    return this.ftaxtypeflag;
  }

  @Override
  public void setFtaxtypeflagKey(String key) {
    this.ftaxtypeflag = key;
  }

  @Override
  public String getFbuysellflagKey() {
    return this.fbuysellflag;
  }

  @Override
  public void setFbuysellflagKey(String key) {
    this.fbuysellflag = key;
  }

  @Override
  public String getNcaltaxmnyKey() {
    return this.ncaltaxmny;
  }

  @Override
  public void setNcaltaxmnyKey(String key) {
    this.ncaltaxmny = key;
  }

  @Override
  public String getPk_org_vKey() {
    return this.pk_org_v;
  }

  @Override
  public void setPk_org_vKey(String key) {
    this.pk_org_v=key;
  }

}
