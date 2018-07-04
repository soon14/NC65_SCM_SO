package nc.vo.so.pub.keyvalue;

/**
 * 为使命名不符合规范的单据能使用公共规则，建立销售字段的映射关系接口
 * 
 * @since 6.0
 * @version 2012-2-6 上午08:54:18
 * @author fengjb
 */
public interface IKeyRela {

  // ----------------单据标准字段----------------------------//
  /** 集团 */
  String getPk_groupKey();

  void setPk_groupKey(String key);

  /** 主组织 */
  String getPk_orgKey();

  void setPk_orgKey(String key);
  
  /** 主组织_V */
  String getPk_org_vKey();

  void setPk_org_vKey(String key);

  /** 业务流程 */
  String getCbiztypeidKey();

  void setCbiztypeidKey(String key);

  /** 交易类型编码 */
  String getVtrantypecodeKey();

  void setVtrantypecodeKey(String key);

  /** 交易类型 */
  String getCtrantypeidKey();

  void setCtrantypeidKey(String key);

  /** 单据号 */
  String getVbillcodeKey();

  void setVbillcodeKey(String key);

  /** 单据日期 */
  String getDbilldateKey();

  void setDbilldateKey(String key);

  /** 单据状态 */
  String getFstatusflagKey();

  void setFstatusflagKey(String key);

  /** 审批流状态 */
  String getFpfstatusflagKey();

  void setFpfstatusflagKey(String key);

  /** 制单人 */
  String getBillmakerKey();

  void setBillmakerKey(String key);

  /** 制单日期 */
  String getDmakedateKey();

  void setDmakedateKey(String key);

  /** 创建人 */
  String getCreatorKey();

  void setCreatorKey(String key);

  /** 创建时间 */

  String getCreationtimeKey();

  void setCreationtimeKey(String key);

  /** 修改人 */
  String getModifierKey();

  void setModifierKey(String key);

  /** 修改时间 */
  String getModifiedtimeKey();

  void setModifiedtimeKey(String key);

  /** 审批人 */
  String getApproverKey();

  void setApproverKey(String key);

  /** 审批时间 */

  String getTaudittimeKey();

  void setTaudittimeKey(String key);

  /** 合计数量 */
  String getNtotalnumKey();

  void setNtotalnumKey(String key);

  /** 金额合计 */
  String getNtotalorigmnyKey();

  void setNtotalorigmnyKey(String key);

  /** 删除标志 */
  String getDrKey();

  void setDrKey(String key);

  // ----------------币种、客户、部门、银行、结算方式、渠道类型字段----------------------------//
  /** 原币 */
  String getCorigcurrencyidKey();

  void setCorigcurrencyidKey(String key);

  /** 订单客户 */
  String getCcustomeridKey();

  void setCcustomeridKey(String key);

  /** 开票客户 */
  String getCinvoicecustidKey();

  void setCinvoicecustidKey(String key);

  /** 散户 */
  String getCfreecustidKey();

  void setCfreecustidKey(String key);

  /** 部门 最新版本 */
  String getCdeptidKey();

  void setCdeptidKey(String key);

  /** 部门 */
  String getCdeptvidKey();

  void setCdeptvidKey(String key);

  /** 业务员 */
  String getCemployeeidKey();

  void setCemployeeidKey(String key);

  /** 结算方式 */
  String getCbalancetypeidKey();

  void setCbalancetypeidKey(String key);

  /** 销售渠道类型 */
  String getCchanneltypeidKey();

  void setCchanneltypeidKey(String key);

  /** 开户银行 */
  String getCcustbankidKey();

  void setCcustbankidKey(String key);

  /** 开户银行账户 */
  String getCcustbankaccidKey();

  void setCcustbankaccidKey(String key);

  /** 收款协议 */
  String getCpaytermidKey();

  void setCpaytermidKey(String key);

  /** 运输方式 */
  String getCtransporttypeidKey();

  void setCtransporttypeidKey(String key);

  // ----------------物料相关字段----------------------------//
  /** 行号 */
  String getCrownoKey();

  void setCrownoKey(String key);

  /** 物料版本 */
  String getCmaterialvidKey();

  void setCmaterialvidKey(String key);

  /** 物料最新版本 */
  String getCmaterialidKey();

  void setCmaterialidKey(String key);

  /** 产品线 */
  String getCprodlineidKey();

  void setCprodlineidKey(String key);

  /** 劳务标志 */
  String getBlaborflagKey();

  void setBlaborflagKey(String key);

  /** 折扣标志 */
  String getBdiscountflagKey();

  void setBdiscountflagKey(String key);

  /** 赠品标志 */
  String getBlargessflagKey();

  void setBlargessflagKey(String key);

  /** 生产厂商 */
  String getCproductoridKey();

  void setCproductoridKey(String key);

  /** 项目 */
  String getCprojectidKey();

  void setCprojectidKey(String key);

  /** 供应商 */
  String getCvendoridKey();

  void setCvendoridKey(String key);

  /** 工厂 */
  String getCfactoryidKey();

  void setCfactoryidKey(String key);

  /** 质量等级 */
  String getCqualitylevelidKey();

  void setCqualitylevelidKey(String key);

  // ----------------自由辅助属性----------------------------//
  /** 自由辅助属性1 */
  String getVfree1Key();

  void setVfree1Key(String key);

  /** 自由辅助属性2 */
  String getVfree2Key();

  void setVfree2Key(String key);

  /** 自由辅助属性3 */
  String getVfree3Key();

  void setVfree3Key(String key);

  /** 自由辅助属性4 */
  String getVfree4Key();

  void setVfree4Key(String key);

  /** 自由辅助属性5 */
  String getVfree5Key();

  void setVfree5Key(String key);

  /** 自由辅助属性6 */
  String getVfree6Key();

  void setVfree6Key(String key);

  /** 自由辅助属性7 */
  String getVfree7Key();

  void setVfree7Key(String key);

  /** 自由辅助属性8 */
  String getVfree8Key();

  void setVfree8Key(String key);

  /** 自由辅助属性9 */
  String getVfree9Key();

  void setVfree9Key(String key);

  /** 自由辅助属性10 */
  String getVfree10Key();

  void setVfree10Key(String key);

  // ----------------数量单价金额字段----------------------------//
  /** 主单位 */
  String getCunitidKey();

  void setCunitidKey(String key);

  /** 单位 */
  String getCastunitidKey();

  void setCastunitidKey(String key);

  /** 换算率 */
  String getVchangerateKey();

  void setVchangerateKey(String key);

  /** 报价单位 */
  String getCqtunitidKey();

  void setCqtunitidKey(String key);

  /** 报价换算率 */
  String getVqtunitrateKey();

  void setVqtunitrateKey(String key);

  /** 主数量 */
  String getNnumKey();

  void setNnumKey(String key);

  /** 数量 */
  String getNastnumKey();

  void setNastnumKey(String key);

  /** 报价数量 */
  String getNqtunitnumKey();

  void setNqtunitnumKey(String key);

  /** 税率 */
  String getNtaxrateKey();

  void setNtaxrateKey(String key);

  /** 整单折扣 */
  String getNdiscountrateKey();

  void setNdiscountrateKey(String key);

  /** 单品折扣 */
  String getNitemdiscountrateKey();

  void setNitemdiscountrateKey(String key);

  /** 本位币 */
  String getCcurrencyidKey();

  void setCcurrencyidKey(String key);

  /** 折本汇率 */
  String getNexchangerateKey();

  void setNexchangerateKey(String key);

  /** 含税单价 */
  String getNqtorigtaxpriceKey();

  void setNqtorigtaxpriceKey(String key);

  /** 无税单价 */
  String getNqtorigpriceKey();

  void setNqtorigpriceKey(String key);

  /** 含税净价 */
  String getNqtorigtaxnetprcKey();

  void setNqtorigtaxnetprcKey(String key);

  /** 无税净价 */
  String getNqtorignetpriceKey();

  void setNqtorignetpriceKey(String key);

  /** 主单位含税单价 */
  String getNorigtaxpriceKey();

  void setNorigtaxpriceKey(String key);

  /** 主单位无税单价 */
  String getNorigpriceKey();

  void setNorigpriceKey(String key);

  /** 主单位含税净价 */
  String getNorigtaxnetpriceKey();

  void setNorigtaxnetpriceKey(String key);

  /** 主单位无税净价 */
  String getNorignetpriceKey();

  void setNorignetpriceKey(String key);

  /** 无税金额 */
  String getNorigmnyKey();

  void setNorigmnyKey(String key);

  /** 价税合计 */
  String getNorigtaxmnyKey();

  void setNorigtaxmnyKey(String key);

  /** 折扣额 */
  String getNorigdiscountKey();

  void setNorigdiscountKey(String key);

  /** 本币含税单价 */
  String getNqttaxpriceKey();

  void setNqttaxpriceKey(String key);

  /** 本币无税单价 */
  String getNqtpriceKey();

  void setNqtpriceKey(String key);

  /** 本币含税净价 */
  String getNqttaxnetpriceKey();

  void setNqttaxnetpriceKey(String key);

  /** 本币无税净价 */
  String getNqtnetpriceKey();

  void setNqtnetpriceKey(String key);

  /** 主本币含税单价 */
  String getNtaxpriceKey();

  void setNtaxpriceKey(String key);

  /** 主本币无税单价 */
  String getNpriceKey();

  void setNpriceKey(String key);

  /** 主本币含税净价 */
  String getNtaxnetpriceKey();

  void setNtaxnetpriceKey(String key);

  /** 主本币无税净价 */
  String getNnetpriceKey();

  void setNnetpriceKey(String key);

  /** 税额 */
  String getNtaxKey();

  void setNtaxKey(String key);

  /** 本币无税金额 */
  String getNmnyKey();

  void setNmnyKey(String key);

  /** 本币价税合计 */
  String getNtaxmnyKey();

  void setNtaxmnyKey(String key);

  /** 本币折扣额 */
  String getNdiscountKey();

  void setNdiscountKey(String key);

  // ----------------集团、全局----------------------------
  /** 全局折本汇率 */
  String getNglobalexchgrateKey();

  void setNglobalexchgrateKey(String key);

  /** 全局本币无税金额 */
  String getNglobalmnyKey();

  void setNglobalmnyKey(String key);

  /** 全局本币价税合计 */
  String getNglobaltaxmnyKey();

  void setNglobaltaxmnyKey(String key);

  /** 集团折本汇率 */
  String getNgroupexchgrateKey();

  void setNgroupexchgrateKey(String key);

  /** 集团本币无税金额 */
  String getNgroupmnyKey();

  void setNgroupmnyKey(String key);

  /** 集团本币价税合计 */
  String getNgrouptaxmnyKey();

  void setNgrouptaxmnyKey(String key);

  // ----------------询价单价----------------------------
  /** 询价原币含税单价 */
  String getNaskqtorigtaxprcKey();

  void setNaskqtorigtaxprcKey(String key);

  /** 询价原币含税净价 */
  String getNaskqtorigtxntprcKey();

  void setNaskqtorigtxntprcKey(String key);

  /** 询价原币无税净价 */
  String getNaskqtorignetpriceKey();

  void setNaskqtorignetpriceKey(String key);

  /** 询价原币无税单价 */
  String getNaskqtorigpriceKey();

  void setNaskqtorigpriceKey(String key);

  // ----------------询价字段----------------------------//
  /** 价格组成 */
  String getCpriceformidKey();

  void setCpriceformidKey(String key);

  /** 价格项 */
  String getCpriceitemidKey();

  void setCpriceitemidKey(String key);

  /** 价目表 */
  String getCpriceitemtableidKey();

  void setCpriceitemtableidKey(String key);

  /** 定价策略 */
  String getCpricepolicyidKey();

  void setCpricepolicyidKey(String key);

  // ----------------收货信息字段----------------------------//

  /** 到货日期 */
  String getDreceivedateKey();

  void setDreceivedateKey(String key);

  /** 发货日期 */
  String getDsenddateKey();

  void setDsenddateKey(String key);

  /** 收货客户 */
  String getCreceivecustidKey();

  void setCreceivecustidKey(String key);

  /** 收货地点 */
  String getCreceiveadddocidKey();

  void setCreceiveadddocidKey(String key);

  /** 收货地址 */
  String getCreceiveaddridKey();

  void setCreceiveaddridKey(String key);

  /** 收货地区 */
  String getCreceiveareaidKey();

  void setCreceiveareaidKey(String key);

  // ----------------组织信息字段----------------------------//
  /** 销售组织最新版本 */
  String getCsaleorgidKey();

  void setCsaleorgidKey(String key);

  /** 销售组织 */
  String getCsaleorgvidKey();

  void setCsaleorgvidKey(String key);
  
  /** 物流组织最新版本 */
  String getCtrafficorgidKey();

  void setCtrafficorgidKey(String key);

  /** 物流组织 */
  String getCtrafficorgvidKey();

  void setCtrafficorgvidKey(String key);

  /** 发货库存组织最新版本 */
  String getCsendstockorgidKey();

  void setCsendstockorgidKey(String key);

  /** 发货库存组织 */
  String getCsendstockorgvidKey();

  void setCsendstockorgvidKey(String key);

  /** 发货仓库 */
  String getCsendstordocidKey();

  void setCsendstordocidKey(String key);

  /** 结算财务组织最新版本 */
  String getCsettleorgidKey();

  void setCsettleorgidKey(String key);

  /** 结算财务组织 */
  String getCsettleorgvidKey();

  void setCsettleorgvidKey(String key);

  /** 应收组织 最新版本 */
  String getCarorgidKey();

  void setCarorgidKey(String key);

  /** 应收组织 */
  String getCarorgvidKey();

  void setCarorgvidKey(String key);

  /** 利润中心最新版本 */
  String getCprofitcenteridKey();

  void setCprofitcenteridKey(String key);

  /** 利润中心 */
  String getCprofitcentervidKey();

  void setCprofitcentervidKey(String key);

  // ----------------源头单据信息字段----------------------------//
  /** 源头单据行id */
  String getCfirstbidKey();

  void setCfirstbidKey(String key);

  /** 行状态 */
  String getFrowstatusKey();

  void setFrowstatusKey(String key);

  /** 赠品价格分摊方式 */
  String getFlargesstypeflagKey();

  void setFlargesstypeflagKey(String key);

  // ----------------V61新增字段----------------------------//
  /** 收货国家/地区 */
  String getCrececountryidKey();

  void setCrececountryidKey(String key);

  /** 发货国家/地区 */
  String getCsendcountryidKey();

  void setCsendcountryidKey(String key);

  /** 报税国家/地区 */
  String getCtaxcountryidKey();

  void setCtaxcountryidKey(String key);

  /** 三角贸易 */
  String getBtriatradeflagKey();

  void setBtriatradeflagKey(String key);

  /** 原产地区 */
  String getCorigareaidKey();

  void setCorigareaidKey(String key);

  /** 原产国 */
  String getCorigcountryidKey();

  void setCorigcountryidKey(String key);

  /** 贸易术语 */
  String getCtradewordidKey();

  void setCtradewordidKey(String key);

  /** 税码 */
  String getCtaxcodeidKey();

  void setCtaxcodeidKey(String key);

  /** 扣税类别 */
  String getFtaxtypeflagKey();

  void setFtaxtypeflagKey(String key);

  /** 购销类型 */
  String getFbuysellflagKey();

  void setFbuysellflagKey(String key);

  /** 计税金额 */
  String getNcaltaxmnyKey();

  void setNcaltaxmnyKey(String key);

}
