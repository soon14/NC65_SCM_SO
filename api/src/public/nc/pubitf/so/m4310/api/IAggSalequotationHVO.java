package nc.pubitf.so.m4310.api;

import java.io.Serializable;

/**
 * @description
 * 报价单查询API条件构造常量类
 * @scene
 *
 * @param
 *
 *
 * @since 6.5
 * @version 2015-11-12 上午10:57:40
 * @author 刘景
 */
public interface IAggSalequotationHVO extends Serializable {
  
  /**
   * 销售报价单主实体
   */
  public static final String PK_SALEQUOTATION = "pk_salequotation";
  /**
   * 集团
   */
  public static final String PK_GROUP = "pk_group";
  /**
   * 销售组织版本
   */
  public static final String PK_ORG_V = "pk_org_v";
  /**
   * 销售组织
   */
  public static final String PK_ORG = "pk_org";
  /**
   * 报价单号
   */
  public static final String VBILLCODE = "vbillcode";
  /**
   * 报价单类型编码
   */
  public static final String VTRANTYPE = "vtrantype";
  /**
   * 报价单类型
   */
  public static final String CTRANTYPEID = "ctrantypeid";
  /**
   * 报价日期
   */
  public static final String DQUOTEDATE = "dquotedate";
  /**
   * 失效日期
   */
  public static final String DENDDATE = "denddate";
  /**
   * 客户
   */
  public static final String PK_CUSTOMER = "pk_customer";
  /**
   * 渠道类型
   */
  public static final String PK_CHANNELTYPE = "pk_channeltype";
  /**
   * 业务员
   */
  public static final String CEMPLOYEEID = "cemployeeid";
  /**
   * 部门
   */
  public static final String PK_DEPT_V = "pk_dept_v";
  /**
   * 部门
   */
  public static final String PK_DEPT = "pk_dept";
  /**
   * 收款协议
   */
  public static final String PK_PAYTERM = "pk_payterm";
  /**
   * 结算方式
   */
  public static final String PK_BALATYPE = "pk_balatype";
  /**
   * 整单折扣(%)
   */
  public static final String NDISCOUNT = "ndiscount";
  /**
   * 币种
   */
  public static final String PK_CURRTYPE = "pk_currtype";
  /**
   * 运输方式
   */
  public static final String CSENDTYPEID = "csendtypeid";
  /**
   * 总数量
   */
  public static final String NTOTALNUM = "ntotalnum";
  /**
   * 价税合计
   */
  public static final String NTOTALMNY = "ntotalmny";
  /**
   * 单据状态
   */
  public static final String FSTATUSFLAG = "fstatusflag";
  /**
   * 备注
   */
  public static final String VNOTE = "vnote";
  /**
   * 自定义项1
   */
  public static final String VDEF1 = "vdef1";
  /**
   * 自定义项2
   */
  public static final String VDEF2 = "vdef2";
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
   * 自定义项20
   */
  public static final String VDEF20 = "vdef20";
  /**
   * 创建人
   */
  public static final String CREATOR = "creator";
  /**
   * 制单人
   */
  public static final String BILLMAKER = "billmaker";
  /**
   * 制单时间
   */
  public static final String DBILLDATE = "dbilldate";
  /**
   * 审批人
   */
  public static final String APPROVER = "approver";
  /**
   * 审核日期
   */
  public static final String TAUDITTIME = "taudittime";
  /**
   * 最后修改人
   */
  public static final String MODIFIER = "modifier";
  /**
   * 最后修改时间
   */
  public static final String MODIFIEDTIME = "modifiedtime";
  /**
   * 创建时间
   */
  public static final String CREATIONTIME = "creationtime";
  /**
   * 制单日期
   */
  public static final String DMAKEDATE = "dmakedate";
  /**
   * 整单来源单据类型
   */
  public static final String VBILLSRCTYPE = "vbillsrctype";
  /**
   * 整单来源单据ID
   */
  public static final String CBILLSRCID = "cbillsrcid";
  /**
   * vostatus
   */
  public static final String STATUS = "status";
  /**
   * dr
   */
  public static final String DR = "dr";
  /**
   * ts
   */
  public static final String TS = "ts";
  /**
   * 报价单子表.销售报价单子实体
   */
  public static final String SALEQUOTATIONDETAIL_PK_SALEQUOTATION_B = "salequotationdetail.pk_salequotation_b";
  /**
   * 报价单子表.集团
   */
  public static final String SALEQUOTATIONDETAIL_PK_GROUP = "salequotationdetail.pk_group";
  /**
   * 报价单子表.销售组织版本信息
   */
  public static final String SALEQUOTATIONDETAIL_PK_ORG_V = "salequotationdetail.pk_org_v";
  /**
   * 报价单子表.销售组织
   */
  public static final String SALEQUOTATIONDETAIL_PK_ORG = "salequotationdetail.pk_org";
  /**
   * 报价单子表.行号
   */
  public static final String SALEQUOTATIONDETAIL_CROWNO = "salequotationdetail.crowno";
  /**
   * 报价单子表.客户物料码
   */
  public static final String SALEQUOTATIONDETAIL_CCUSTMATERIALID = "salequotationdetail.ccustmaterialid";
  /**
   * 报价单子表.物料编码
   */
  public static final String SALEQUOTATIONDETAIL_PK_MATERIAL_V = "salequotationdetail.pk_material_v";
  /**
   * 报价单子表.物料
   */
  public static final String SALEQUOTATIONDETAIL_PK_MATERIAL = "salequotationdetail.pk_material";
  /**
   * 报价单子表.质量等级
   */
  public static final String SALEQUOTATIONDETAIL_PK_QUALITYLEVEL = "salequotationdetail.pk_qualitylevel";
  /**
   * 报价单子表.项目
   */
  public static final String SALEQUOTATIONDETAIL_PK_PROJECT = "salequotationdetail.pk_project";
  /**
   * 报价单子表.生产厂商
   */
  public static final String SALEQUOTATIONDETAIL_PK_PRODUCTOR = "salequotationdetail.pk_productor";
  /**
   * 报价单子表.供应商
   */
  public static final String SALEQUOTATIONDETAIL_PK_SUPPLIER = "salequotationdetail.pk_supplier";
  /**
   * 报价单子表.单位
   */
  public static final String SALEQUOTATIONDETAIL_CASTUNITID = "salequotationdetail.castunitid";
  /**
   * 报价单子表.数量
   */
  public static final String SALEQUOTATIONDETAIL_NASSISTNUM = "salequotationdetail.nassistnum";
  /**
   * 报价单子表.换算率
   */
  public static final String SALEQUOTATIONDETAIL_NCHANGERATE = "salequotationdetail.nchangerate";
  /**
   * 报价单子表.主单位
   */
  public static final String SALEQUOTATIONDETAIL_PK_UNIT = "salequotationdetail.pk_unit";
  /**
   * 报价单子表.主数量
   */
  public static final String SALEQUOTATIONDETAIL_NNUM = "salequotationdetail.nnum";
  /**
   * 报价单子表.报价单位
   */
  public static final String SALEQUOTATIONDETAIL_CQTUNITID = "salequotationdetail.cqtunitid";
  /**
   * 报价单子表.报价数量
   */
  public static final String SALEQUOTATIONDETAIL_NQTNUM = "salequotationdetail.nqtnum";
  /**
   * 报价单子表.报价换算率
   */
  public static final String SALEQUOTATIONDETAIL_NQTCHANGERATE = "salequotationdetail.nqtchangerate";
  /**
   * 报价单子表.收货地区
   */
  public static final String SALEQUOTATIONDETAIL_PK_AREACL = "salequotationdetail.pk_areacl";
  /**
   * 报价单子表.自由辅助属性1
   */
  public static final String SALEQUOTATIONDETAIL_VFREE1 = "salequotationdetail.vfree1";
  /**
   * 报价单子表.自由辅助属性2
   */
  public static final String SALEQUOTATIONDETAIL_VFREE2 = "salequotationdetail.vfree2";
  /**
   * 报价单子表.自由辅助属性3
   */
  public static final String SALEQUOTATIONDETAIL_VFREE3 = "salequotationdetail.vfree3";
  /**
   * 报价单子表.自由辅助属性4
   */
  public static final String SALEQUOTATIONDETAIL_VFREE4 = "salequotationdetail.vfree4";
  /**
   * 报价单子表.自由辅助属性5
   */
  public static final String SALEQUOTATIONDETAIL_VFREE5 = "salequotationdetail.vfree5";
  /**
   * 报价单子表.自由辅助属性6
   */
  public static final String SALEQUOTATIONDETAIL_VFREE6 = "salequotationdetail.vfree6";
  /**
   * 报价单子表.自由辅助属性7
   */
  public static final String SALEQUOTATIONDETAIL_VFREE7 = "salequotationdetail.vfree7";
  /**
   * 报价单子表.自由辅助属性8
   */
  public static final String SALEQUOTATIONDETAIL_VFREE8 = "salequotationdetail.vfree8";
  /**
   * 报价单子表.自由辅助属性9
   */
  public static final String SALEQUOTATIONDETAIL_VFREE9 = "salequotationdetail.vfree9";
  /**
   * 报价单子表.自由辅助属性10
   */
  public static final String SALEQUOTATIONDETAIL_VFREE10 = "salequotationdetail.vfree10";
  /**
   * 报价单子表.无税单价
   */
  public static final String SALEQUOTATIONDETAIL_NQTORIGPRICE = "salequotationdetail.nqtorigprice";
  /**
   * 报价单子表.税码
   */
  public static final String SALEQUOTATIONDETAIL_CTAXCODEID = "salequotationdetail.ctaxcodeid";
  /**
   * 报价单子表.税率(%)
   */
  public static final String SALEQUOTATIONDETAIL_NTAXRATE = "salequotationdetail.ntaxrate";
  /**
   * 报价单子表.扣税类别
   */
  public static final String SALEQUOTATIONDETAIL_FTAXTYPEFLAG = "salequotationdetail.ftaxtypeflag";
  /**
   * 报价单子表.含税单价
   */
  public static final String SALEQUOTATIONDETAIL_NQTORIGTAXPRICE = "salequotationdetail.nqtorigtaxprice";
  /**
   * 报价单子表.整单折扣(%)
   */
  public static final String SALEQUOTATIONDETAIL_NDISCOUNTRATE = "salequotationdetail.ndiscountrate";
  /**
   * 报价单子表.单品折扣(%)
   */
  public static final String SALEQUOTATIONDETAIL_NITEMDISCOUNTRATE = "salequotationdetail.nitemdiscountrate";
  /**
   * 报价单子表.无税净价
   */
  public static final String SALEQUOTATIONDETAIL_NQTORIGNETPRICE = "salequotationdetail.nqtorignetprice";
  /**
   * 报价单子表.含税净价
   */
  public static final String SALEQUOTATIONDETAIL_NQTORIGTAXNETPRC = "salequotationdetail.nqtorigtaxnetprc";
  /**
   * 报价单子表.无税金额
   */
  public static final String SALEQUOTATIONDETAIL_NORIGMNY = "salequotationdetail.norigmny";
  /**
   * 报价单子表.价税合计
   */
  public static final String SALEQUOTATIONDETAIL_NORIGTAXMNY = "salequotationdetail.norigtaxmny";
  /**
   * 报价单子表.折扣额
   */
  public static final String SALEQUOTATIONDETAIL_NORIGDISCOUNT = "salequotationdetail.norigdiscount";
  /**
   * 报价单子表.赠品
   */
  public static final String SALEQUOTATIONDETAIL_BLARGESSFLAG = "salequotationdetail.blargessflag";
  /**
   * 报价单子表.定价策略
   */
  public static final String SALEQUOTATIONDETAIL_PK_PRICEPOLICY = "salequotationdetail.pk_pricepolicy";
  /**
   * 报价单子表.价目表
   */
  public static final String SALEQUOTATIONDETAIL_PK_TARIFFDEF = "salequotationdetail.pk_tariffdef";
  /**
   * 报价单子表.价格项
   */
  public static final String SALEQUOTATIONDETAIL_PK_PRICETYPE = "salequotationdetail.pk_pricetype";
  /**
   * 报价单子表.价格组成
   */
  public static final String SALEQUOTATIONDETAIL_VPRICEDETAIL = "salequotationdetail.vpricedetail";
  /**
   * 报价单子表.收货国家/地区
   */
  public static final String SALEQUOTATIONDETAIL_CRECECOUNTRYID = "salequotationdetail.crececountryid";
  /**
   * 报价单子表.发货国家/地区
   */
  public static final String SALEQUOTATIONDETAIL_CSENDCOUNTRYID = "salequotationdetail.csendcountryid";
  /**
   * 报价单子表.报税国家/地区
   */
  public static final String SALEQUOTATIONDETAIL_CTAXCOUNTRYID = "salequotationdetail.ctaxcountryid";
  /**
   * 报价单子表.购销类型
   */
  public static final String SALEQUOTATIONDETAIL_FBUYSELLFLAG = "salequotationdetail.fbuysellflag";
  /**
   * 报价单子表.三角贸易
   */
  public static final String SALEQUOTATIONDETAIL_BTRIATRADEFLAG = "salequotationdetail.btriatradeflag";
  /**
   * 报价单子表.累计生成订单主数量
   */
  public static final String SALEQUOTATIONDETAIL_NORDERNUM = "salequotationdetail.nordernum";
  /**
   * 报价单子表.累计生成合同主数量
   */
  public static final String SALEQUOTATIONDETAIL_NCONTRACTNUM = "salequotationdetail.ncontractnum";
  /**
   * 报价单子表.备注
   */
  public static final String SALEQUOTATIONDETAIL_VBNOTE = "salequotationdetail.vbnote";
  /**
   * 报价单子表.主无税单价
   */
  public static final String SALEQUOTATIONDETAIL_NORIGPRICE = "salequotationdetail.norigprice";
  /**
   * 报价单子表.主含税单价
   */
  public static final String SALEQUOTATIONDETAIL_NORIGTAXPRICE = "salequotationdetail.norigtaxprice";
  /**
   * 报价单子表.主无税净价
   */
  public static final String SALEQUOTATIONDETAIL_NORIGNETPRICE = "salequotationdetail.norignetprice";
  /**
   * 报价单子表.主含税净价
   */
  public static final String SALEQUOTATIONDETAIL_NORIGTAXNETPRICE = "salequotationdetail.norigtaxnetprice";
  /**
   * 报价单子表.自定义项1
   */
  public static final String SALEQUOTATIONDETAIL_VBDEF1 = "salequotationdetail.vbdef1";
  /**
   * 报价单子表.自定义项2
   */
  public static final String SALEQUOTATIONDETAIL_VBDEF2 = "salequotationdetail.vbdef2";
  /**
   * 报价单子表.自定义项3
   */
  public static final String SALEQUOTATIONDETAIL_VBDEF3 = "salequotationdetail.vbdef3";
  /**
   * 报价单子表.自定义项4
   */
  public static final String SALEQUOTATIONDETAIL_VBDEF4 = "salequotationdetail.vbdef4";
  /**
   * 报价单子表.自定义项5
   */
  public static final String SALEQUOTATIONDETAIL_VBDEF5 = "salequotationdetail.vbdef5";
  /**
   * 报价单子表.自定义项6
   */
  public static final String SALEQUOTATIONDETAIL_VBDEF6 = "salequotationdetail.vbdef6";
  /**
   * 报价单子表.自定义项7
   */
  public static final String SALEQUOTATIONDETAIL_VBDEF7 = "salequotationdetail.vbdef7";
  /**
   * 报价单子表.自定义项8
   */
  public static final String SALEQUOTATIONDETAIL_VBDEF8 = "salequotationdetail.vbdef8";
  /**
   * 报价单子表.自定义项9
   */
  public static final String SALEQUOTATIONDETAIL_VBDEF9 = "salequotationdetail.vbdef9";
  /**
   * 报价单子表.自定义项10
   */
  public static final String SALEQUOTATIONDETAIL_VBDEF10 = "salequotationdetail.vbdef10";
  /**
   * 报价单子表.自定义项11
   */
  public static final String SALEQUOTATIONDETAIL_VBDEF11 = "salequotationdetail.vbdef11";
  /**
   * 报价单子表.自定义项12
   */
  public static final String SALEQUOTATIONDETAIL_VBDEF12 = "salequotationdetail.vbdef12";
  /**
   * 报价单子表.自定义项13
   */
  public static final String SALEQUOTATIONDETAIL_VBDEF13 = "salequotationdetail.vbdef13";
  /**
   * 报价单子表.自定义项14
   */
  public static final String SALEQUOTATIONDETAIL_VBDEF14 = "salequotationdetail.vbdef14";
  /**
   * 报价单子表.自定义项15
   */
  public static final String SALEQUOTATIONDETAIL_VBDEF15 = "salequotationdetail.vbdef15";
  /**
   * 报价单子表.自定义项16
   */
  public static final String SALEQUOTATIONDETAIL_VBDEF16 = "salequotationdetail.vbdef16";
  /**
   * 报价单子表.自定义项17
   */
  public static final String SALEQUOTATIONDETAIL_VBDEF17 = "salequotationdetail.vbdef17";
  /**
   * 报价单子表.自定义项18
   */
  public static final String SALEQUOTATIONDETAIL_VBDEF18 = "salequotationdetail.vbdef18";
  /**
   * 报价单子表.自定义项19
   */
  public static final String SALEQUOTATIONDETAIL_VBDEF19 = "salequotationdetail.vbdef19";
  /**
   * 报价单子表.自定义项20
   */
  public static final String SALEQUOTATIONDETAIL_VBDEF20 = "salequotationdetail.vbdef20";
  /**
   * 报价单子表.来源单据类型
   */
  public static final String SALEQUOTATIONDETAIL_VSRCTYPE = "salequotationdetail.vsrctype";
  /**
   * 报价单子表.来源交易类型
   */
  public static final String SALEQUOTATIONDETAIL_VSRCTRANTYPE = "salequotationdetail.vsrctrantype";
  /**
   * 报价单子表.来源单据号
   */
  public static final String SALEQUOTATIONDETAIL_VSRCCODE = "salequotationdetail.vsrccode";
  /**
   * 报价单子表.来源单据行号
   */
  public static final String SALEQUOTATIONDETAIL_VSRCROWNO = "salequotationdetail.vsrcrowno";
  /**
   * 报价单子表.来源单据主表
   */
  public static final String SALEQUOTATIONDETAIL_CSRCID = "salequotationdetail.csrcid";
  /**
   * 报价单子表.来源单据附表
   */
  public static final String SALEQUOTATIONDETAIL_CSRCBID = "salequotationdetail.csrcbid";
  /**
   * 报价单子表.源头单据类型
   */
  public static final String SALEQUOTATIONDETAIL_VFIRSTTYPE = "salequotationdetail.vfirsttype";
  /**
   * 报价单子表.源头交易类型
   */
  public static final String SALEQUOTATIONDETAIL_VFIRSTTRANTYPE = "salequotationdetail.vfirsttrantype";
  /**
   * 报价单子表.源头单据号
   */
  public static final String SALEQUOTATIONDETAIL_VFIRSTCODE = "salequotationdetail.vfirstcode";
  /**
   * 报价单子表.源头单据主表
   */
  public static final String SALEQUOTATIONDETAIL_CFIRSTID = "salequotationdetail.cfirstid";
  /**
   * 报价单子表.源头单据附表
   */
  public static final String SALEQUOTATIONDETAIL_CFIRSTBID = "salequotationdetail.cfirstbid";
  /**
   * 报价单子表.源头单据行号
   */
  public static final String SALEQUOTATIONDETAIL_VFIRSTROWNO = "salequotationdetail.vfirstrowno";
  /**
   * 报价单子表.来源单据表头时间戳
   */
  public static final String SALEQUOTATIONDETAIL_SRCTS = "salequotationdetail.srcts";
  /**
   * 报价单子表.来源单据表体时间戳
   */
  public static final String SALEQUOTATIONDETAIL_SRCBTS = "salequotationdetail.srcbts";
  /**
   * 报价单子表.vostatus
   */
  public static final String SALEQUOTATIONDETAIL_STATUS = "salequotationdetail.status";
  /**
   * 报价单子表.dr
   */
  public static final String SALEQUOTATIONDETAIL_DR = "salequotationdetail.dr";
  /**
   * 报价单子表.ts
   */
  public static final String SALEQUOTATIONDETAIL_TS = "salequotationdetail.ts";
}


