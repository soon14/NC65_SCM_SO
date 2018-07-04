package nc.pubitf.so.m4331.api;

import java.io.Serializable;

/**
 * @description
 * 发货单查询API条件构造常量类
 * @scene
 *
 * @param
 *
 *
 * @since 6.5
 * @version 2015-11-12 上午10:57:20
 * @author 刘景
 */
public interface IDeliveryVO extends Serializable {
  
  /**
   * 发货单主实体
   */
  public static final String CDELIVERYID = "cdeliveryid";
  /**
   * 集团
   */
  public static final String PK_GROUP = "pk_group";
  /**
   * 物流组织
   */
  public static final String PK_ORG = "pk_org";
  /**
   * 物流组织版本
   */
  public static final String PK_ORG_V = "pk_org_v";
  /**
   * 单据号
   */
  public static final String VBILLCODE = "vbillcode";
  /**
   * 业务流程
   */
  public static final String CBIZTYPEID = "cbiztypeid";
  /**
   * 发货类型
   */
  public static final String CTRANTYPEID = "ctrantypeid";
  /**
   * 发货类型编码
   */
  public static final String VTRANTYPECODE = "vtrantypecode";
  /**
   * 单据日期
   */
  public static final String DBILLDATE = "dbilldate";
  /**
   * 发货计划员
   */
  public static final String CSENDEMPLOYEEID = "csendemployeeid";
  /**
   * 发货部门最新版本
   */
  public static final String CSENDDEPTID = "csenddeptid";
  /**
   * 发货部门
   */
  public static final String CSENDDEPTVID = "csenddeptvid";
  /**
   * 运输方式
   */
  public static final String CTRANSPORTTYPEID = "ctransporttypeid";
  /**
   * 贸易术语
   */
  public static final String CTRADEWORDID = "ctradewordid";
  /**
   * 运输路线
   */
  public static final String CTRANSPORTROUTEID = "ctransportrouteid";
  /**
   * 总数量
   */
  public static final String NTOTALASTNUM = "ntotalastnum";
  /**
   * 总重量
   */
  public static final String NTOTALWEIGHT = "ntotalweight";
  /**
   * 总体积
   */
  public static final String NTOTALVOLUME = "ntotalvolume";
  /**
   * 总件数
   */
  public static final String NTOTALPIECE = "ntotalpiece";
  /**
   * 单据状态
   */
  public static final String FSTATUSFLAG = "fstatusflag";
  /**
   * 备注
   */
  public static final String VNOTE = "vnote";
  /**
   * 创建人
   */
  public static final String CREATOR = "creator";
  /**
   * 制单人
   */
  public static final String BILLMAKER = "billmaker";
  /**
   * 创建时间
   */
  public static final String CREATIONTIME = "creationtime";
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
   * 打印次数
   */
  public static final String IPRINTCOUNT = "iprintcount";
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
   * 制单日期
   */
  public static final String DMAKEDATE = "dmakedate";
  /**
   * 单据码
   */
  public static final String BC_03 = "bc_03";
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
   * 发货单表体vo.发货单子实体
   */
  public static final String CDELIVERYBID_CDELIVERYBID = "cdeliverybid.cdeliverybid";
  /**
   * 发货单表体vo.物流组织
   */
  public static final String CDELIVERYBID_PK_ORG = "cdeliverybid.pk_org";
  /**
   * 发货单表体vo.单据日期
   */
  public static final String CDELIVERYBID_DBILLDATE = "cdeliverybid.dbilldate";
  /**
   * 发货单表体vo.行号
   */
  public static final String CDELIVERYBID_CROWNO = "cdeliverybid.crowno";
  /**
   * 发货单表体vo.客户物料码
   */
  public static final String CDELIVERYBID_CCUSTMATERIALID = "cdeliverybid.ccustmaterialid";
  /**
   * 发货单表体vo.客户编码
   */
  public static final String CDELIVERYBID_CORDERCUSTID = "cdeliverybid.cordercustid";
  /**
   * 发货单表体vo.散户
   */
  public static final String CDELIVERYBID_CFREECUSTID = "cdeliverybid.cfreecustid";
  /**
   * 发货单表体vo.开票客户
   */
  public static final String CDELIVERYBID_CINVOICECUSTID = "cdeliverybid.cinvoicecustid";
  /**
   * 发货单表体vo.物料档案
   */
  public static final String CDELIVERYBID_CMATERIALID = "cdeliverybid.cmaterialid";
  /**
   * 发货单表体vo.物料编码
   */
  public static final String CDELIVERYBID_CMATERIALVID = "cdeliverybid.cmaterialvid";
  /**
   * 发货单表体vo.供应商
   */
  public static final String CDELIVERYBID_CVENDORID = "cdeliverybid.cvendorid";
  /**
   * 发货单表体vo.项目
   */
  public static final String CDELIVERYBID_CPROJECTID = "cdeliverybid.cprojectid";
  /**
   * 发货单表体vo.质量等级
   */
  public static final String CDELIVERYBID_CQUALITYLEVELID = "cdeliverybid.cqualitylevelid";
  /**
   * 发货单表体vo.原产国
   */
  public static final String CDELIVERYBID_CORIGCOUNTRYID = "cdeliverybid.corigcountryid";
  /**
   * 发货单表体vo.原产地区
   */
  public static final String CDELIVERYBID_CORIGAREAID = "cdeliverybid.corigareaid";
  /**
   * 发货单表体vo.生产厂商
   */
  public static final String CDELIVERYBID_CPRODUCTORID = "cdeliverybid.cproductorid";
  /**
   * 发货单表体vo.单位
   */
  public static final String CDELIVERYBID_CASTUNITID = "cdeliverybid.castunitid";
  /**
   * 发货单表体vo.数量
   */
  public static final String CDELIVERYBID_NASTNUM = "cdeliverybid.nastnum";
  /**
   * 发货单表体vo.主单位
   */
  public static final String CDELIVERYBID_CUNITID = "cdeliverybid.cunitid";
  /**
   * 发货单表体vo.主数量
   */
  public static final String CDELIVERYBID_NNUM = "cdeliverybid.nnum";
  /**
   * 发货单表体vo.换算率
   */
  public static final String CDELIVERYBID_VCHANGERATE = "cdeliverybid.vchangerate";
  /**
   * 发货单表体vo.报价单位
   */
  public static final String CDELIVERYBID_CQTUNITID = "cdeliverybid.cqtunitid";
  /**
   * 发货单表体vo.报价数量
   */
  public static final String CDELIVERYBID_NQTUNITNUM = "cdeliverybid.nqtunitnum";
  /**
   * 发货单表体vo.报价换算率
   */
  public static final String CDELIVERYBID_VQTUNITRATE = "cdeliverybid.vqtunitrate";
  /**
   * 发货单表体vo.是否已报检
   */
  public static final String CDELIVERYBID_BCHECKFLAG = "cdeliverybid.bcheckflag";
  /**
   * 发货单表体vo.是否根据质检结果入库
   */
  public static final String CDELIVERYBID_BUSECHECKFLAG = "cdeliverybid.busecheckflag";
  /**
   * 发货单表体vo.累计报检数量
   */
  public static final String CDELIVERYBID_NTOTALREPORTNUM = "cdeliverybid.ntotalreportnum";
  /**
   * 发货单表体vo.累计合格数量
   */
  public static final String CDELIVERYBID_NTOTALELIGNUM = "cdeliverybid.ntotalelignum";
  /**
   * 发货单表体vo.累计不合格数量
   */
  public static final String CDELIVERYBID_NTOTALUNELIGNUM = "cdeliverybid.ntotalunelignum";
  /**
   * 发货单表体vo.重量
   */
  public static final String CDELIVERYBID_NWEIGHT = "cdeliverybid.nweight";
  /**
   * 发货单表体vo.体积
   */
  public static final String CDELIVERYBID_NVOLUME = "cdeliverybid.nvolume";
  /**
   * 发货单表体vo.件数
   */
  public static final String CDELIVERYBID_NPIECE = "cdeliverybid.npiece";
  /**
   * 发货单表体vo.赠品
   */
  public static final String CDELIVERYBID_BLARGESSFLAG = "cdeliverybid.blargessflag";
  /**
   * 发货单表体vo.批次档案
   */
  public static final String CDELIVERYBID_PK_BATCHCODE = "cdeliverybid.pk_batchcode";
  /**
   * 发货单表体vo.批次号
   */
  public static final String CDELIVERYBID_VBATCHCODE = "cdeliverybid.vbatchcode";
  /**
   * 发货单表体vo.原币
   */
  public static final String CDELIVERYBID_CORIGCURRENCYID = "cdeliverybid.corigcurrencyid";
  /**
   * 发货单表体vo.折本汇率
   */
  public static final String CDELIVERYBID_NEXCHANGERATE = "cdeliverybid.nexchangerate";
  /**
   * 发货单表体vo.本位币
   */
  public static final String CDELIVERYBID_CCURRENCYID = "cdeliverybid.ccurrencyid";
  /**
   * 发货单表体vo.税码
   */
  public static final String CDELIVERYBID_CTAXCODEID = "cdeliverybid.ctaxcodeid";
  /**
   * 发货单表体vo.税率
   */
  public static final String CDELIVERYBID_NTAXRATE = "cdeliverybid.ntaxrate";
  /**
   * 发货单表体vo.计税金额
   */
  public static final String CDELIVERYBID_NCALTAXMNY = "cdeliverybid.ncaltaxmny";
  /**
   * 发货单表体vo.扣税类别
   */
  public static final String CDELIVERYBID_FTAXTYPEFLAG = "cdeliverybid.ftaxtypeflag";
  /**
   * 发货单表体vo.整单折扣
   */
  public static final String CDELIVERYBID_NDISCOUNTRATE = "cdeliverybid.ndiscountrate";
  /**
   * 发货单表体vo.单品折扣
   */
  public static final String CDELIVERYBID_NITEMDISCOUNTRATE = "cdeliverybid.nitemdiscountrate";
  /**
   * 发货单表体vo.主含税单价
   */
  public static final String CDELIVERYBID_NORIGTAXPRICE = "cdeliverybid.norigtaxprice";
  /**
   * 发货单表体vo.主无税单价
   */
  public static final String CDELIVERYBID_NORIGPRICE = "cdeliverybid.norigprice";
  /**
   * 发货单表体vo.主含税净价
   */
  public static final String CDELIVERYBID_NORIGTAXNETPRICE = "cdeliverybid.norigtaxnetprice";
  /**
   * 发货单表体vo.主无税净价
   */
  public static final String CDELIVERYBID_NORIGNETPRICE = "cdeliverybid.norignetprice";
  /**
   * 发货单表体vo.含税单价
   */
  public static final String CDELIVERYBID_NQTORIGTAXPRICE = "cdeliverybid.nqtorigtaxprice";
  /**
   * 发货单表体vo.无税单价
   */
  public static final String CDELIVERYBID_NQTORIGPRICE = "cdeliverybid.nqtorigprice";
  /**
   * 发货单表体vo.含税净价
   */
  public static final String CDELIVERYBID_NQTORIGTAXNETPRC = "cdeliverybid.nqtorigtaxnetprc";
  /**
   * 发货单表体vo.无税净价
   */
  public static final String CDELIVERYBID_NQTORIGNETPRICE = "cdeliverybid.nqtorignetprice";
  /**
   * 发货单表体vo.无税金额
   */
  public static final String CDELIVERYBID_NORIGMNY = "cdeliverybid.norigmny";
  /**
   * 发货单表体vo.价税合计
   */
  public static final String CDELIVERYBID_NORIGTAXMNY = "cdeliverybid.norigtaxmny";
  /**
   * 发货单表体vo.折扣额
   */
  public static final String CDELIVERYBID_NORIGDISCOUNT = "cdeliverybid.norigdiscount";
  /**
   * 发货单表体vo.本币主含税单价
   */
  public static final String CDELIVERYBID_NTAXPRICE = "cdeliverybid.ntaxprice";
  /**
   * 发货单表体vo.本币主无税单价
   */
  public static final String CDELIVERYBID_NPRICE = "cdeliverybid.nprice";
  /**
   * 发货单表体vo.本币主含税净价
   */
  public static final String CDELIVERYBID_NTAXNETPRICE = "cdeliverybid.ntaxnetprice";
  /**
   * 发货单表体vo.本币主无税净价
   */
  public static final String CDELIVERYBID_NNETPRICE = "cdeliverybid.nnetprice";
  /**
   * 发货单表体vo.本币含税单价
   */
  public static final String CDELIVERYBID_NQTTAXPRICE = "cdeliverybid.nqttaxprice";
  /**
   * 发货单表体vo.本币无税单价
   */
  public static final String CDELIVERYBID_NQTPRICE = "cdeliverybid.nqtprice";
  /**
   * 发货单表体vo.本币含税净价
   */
  public static final String CDELIVERYBID_NQTTAXNETPRICE = "cdeliverybid.nqttaxnetprice";
  /**
   * 发货单表体vo.本币无税净价
   */
  public static final String CDELIVERYBID_NQTNETPRICE = "cdeliverybid.nqtnetprice";
  /**
   * 发货单表体vo.税额
   */
  public static final String CDELIVERYBID_NTAX = "cdeliverybid.ntax";
  /**
   * 发货单表体vo.本币无税金额
   */
  public static final String CDELIVERYBID_NMNY = "cdeliverybid.nmny";
  /**
   * 发货单表体vo.本币价税合计
   */
  public static final String CDELIVERYBID_NTAXMNY = "cdeliverybid.ntaxmny";
  /**
   * 发货单表体vo.本币折扣额
   */
  public static final String CDELIVERYBID_NDISCOUNT = "cdeliverybid.ndiscount";
  /**
   * 发货单表体vo.源头单据类型
   */
  public static final String CDELIVERYBID_VFIRSTTYPE = "cdeliverybid.vfirsttype";
  /**
   * 发货单表体vo.源头单据号
   */
  public static final String CDELIVERYBID_VFIRSTCODE = "cdeliverybid.vfirstcode";
  /**
   * 发货单表体vo.源头交易类型
   */
  public static final String CDELIVERYBID_VFIRSTTRANTYPE = "cdeliverybid.vfirsttrantype";
  /**
   * 发货单表体vo.源头单据行号
   */
  public static final String CDELIVERYBID_VFIRSTROWNO = "cdeliverybid.vfirstrowno";
  /**
   * 发货单表体vo.源头单据表头ID
   */
  public static final String CDELIVERYBID_CFIRSTID = "cdeliverybid.cfirstid";
  /**
   * 发货单表体vo.源头单据表体ID
   */
  public static final String CDELIVERYBID_CFIRSTBID = "cdeliverybid.cfirstbid";
  /**
   * 发货单表体vo.来源单据类型
   */
  public static final String CDELIVERYBID_VSRCTYPE = "cdeliverybid.vsrctype";
  /**
   * 发货单表体vo.来源单据号
   */
  public static final String CDELIVERYBID_VSRCCODE = "cdeliverybid.vsrccode";
  /**
   * 发货单表体vo.来源交易类型
   */
  public static final String CDELIVERYBID_VSRCTRANTYPE = "cdeliverybid.vsrctrantype";
  /**
   * 发货单表体vo.来源单据行号
   */
  public static final String CDELIVERYBID_VSRCROWNO = "cdeliverybid.vsrcrowno";
  /**
   * 发货单表体vo.来源单据表头ID
   */
  public static final String CDELIVERYBID_CSRCID = "cdeliverybid.csrcid";
  /**
   * 发货单表体vo.来源单据表体ID
   */
  public static final String CDELIVERYBID_CSRCBID = "cdeliverybid.csrcbid";
  /**
   * 发货单表体vo.销售组织
   */
  public static final String CDELIVERYBID_CSALEORGID = "cdeliverybid.csaleorgid";
  /**
   * 发货单表体vo.销售组织
   */
  public static final String CDELIVERYBID_CSALEORGVID = "cdeliverybid.csaleorgvid";
  /**
   * 发货单表体vo.发货库存组织
   */
  public static final String CDELIVERYBID_CSENDSTOCKORGID = "cdeliverybid.csendstockorgid";
  /**
   * 发货单表体vo.发货库存组织
   */
  public static final String CDELIVERYBID_CSENDSTOCKORGVID = "cdeliverybid.csendstockorgvid";
  /**
   * 发货单表体vo.发货仓库
   */
  public static final String CDELIVERYBID_CSENDSTORDOCID = "cdeliverybid.csendstordocid";
  /**
   * 发货单表体vo.收货客户
   */
  public static final String CDELIVERYBID_CRECEIVECUSTID = "cdeliverybid.creceivecustid";
  /**
   * 发货单表体vo.收货地区
   */
  public static final String CDELIVERYBID_CRECEIVEAREAID = "cdeliverybid.creceiveareaid";
  /**
   * 发货单表体vo.收货地址
   */
  public static final String CDELIVERYBID_CRECEIVEADDRID = "cdeliverybid.creceiveaddrid";
  /**
   * 发货单表体vo.收货库存组织
   */
  public static final String CDELIVERYBID_CINSTOCKORGID = "cdeliverybid.cinstockorgid";
  /**
   * 发货单表体vo.收货库存组织
   */
  public static final String CDELIVERYBID_CINSTOCKORGVID = "cdeliverybid.cinstockorgvid";
  /**
   * 发货单表体vo.收货仓库
   */
  public static final String CDELIVERYBID_CINSTORDOCID = "cdeliverybid.cinstordocid";
  /**
   * 发货单表体vo.发货日期
   */
  public static final String CDELIVERYBID_DSENDDATE = "cdeliverybid.dsenddate";
  /**
   * 发货单表体vo.要求收货日期
   */
  public static final String CDELIVERYBID_DRECEIVEDATE = "cdeliverybid.dreceivedate";
  /**
   * 发货单表体vo.押运员
   */
  public static final String CDELIVERYBID_CSUPERCARGOID = "cdeliverybid.csupercargoid";
  /**
   * 发货单表体vo.承运商
   */
  public static final String CDELIVERYBID_CTRANSCUSTID = "cdeliverybid.ctranscustid";
  /**
   * 发货单表体vo.车型
   */
  public static final String CDELIVERYBID_CVEHICLETYPEID = "cdeliverybid.cvehicletypeid";
  /**
   * 发货单表体vo.车辆
   */
  public static final String CDELIVERYBID_CVEHICLEID = "cdeliverybid.cvehicleid";
  /**
   * 发货单表体vo.司机
   */
  public static final String CDELIVERYBID_CCHAUFFEURID = "cdeliverybid.cchauffeurid";
  /**
   * 发货单表体vo.货位
   */
  public static final String CDELIVERYBID_CSPACEID = "cdeliverybid.cspaceid";
  /**
   * 发货单表体vo.产品线
   */
  public static final String CDELIVERYBID_CPRODLINEID = "cdeliverybid.cprodlineid";
  /**
   * 发货单表体vo.累计运输数量
   */
  public static final String CDELIVERYBID_NTOTALTRANSNUM = "cdeliverybid.ntotaltransnum";
  /**
   * 发货单表体vo.运输关闭
   */
  public static final String CDELIVERYBID_BTRANSENDFLAG = "cdeliverybid.btransendflag";
  /**
   * 发货单表体vo.累计出库数量
   */
  public static final String CDELIVERYBID_NTOTALOUTNUM = "cdeliverybid.ntotaloutnum";
  /**
   * 发货单表体vo.出库关闭
   */
  public static final String CDELIVERYBID_BOUTENDFLAG = "cdeliverybid.boutendflag";
  /**
   * 发货单表体vo.备注
   */
  public static final String CDELIVERYBID_FROWNOTE = "cdeliverybid.frownote";
  /**
   * 发货单表体vo.自由辅助属性1
   */
  public static final String CDELIVERYBID_VFREE1 = "cdeliverybid.vfree1";
  /**
   * 发货单表体vo.自由辅助属性2
   */
  public static final String CDELIVERYBID_VFREE2 = "cdeliverybid.vfree2";
  /**
   * 发货单表体vo.自由辅助属性3
   */
  public static final String CDELIVERYBID_VFREE3 = "cdeliverybid.vfree3";
  /**
   * 发货单表体vo.自由辅助属性4
   */
  public static final String CDELIVERYBID_VFREE4 = "cdeliverybid.vfree4";
  /**
   * 发货单表体vo.自由辅助属性5
   */
  public static final String CDELIVERYBID_VFREE5 = "cdeliverybid.vfree5";
  /**
   * 发货单表体vo.自由辅助属性6
   */
  public static final String CDELIVERYBID_VFREE6 = "cdeliverybid.vfree6";
  /**
   * 发货单表体vo.自由辅助属性7
   */
  public static final String CDELIVERYBID_VFREE7 = "cdeliverybid.vfree7";
  /**
   * 发货单表体vo.自由辅助属性8
   */
  public static final String CDELIVERYBID_VFREE8 = "cdeliverybid.vfree8";
  /**
   * 发货单表体vo.自由辅助属性9
   */
  public static final String CDELIVERYBID_VFREE9 = "cdeliverybid.vfree9";
  /**
   * 发货单表体vo.自由辅助属性10
   */
  public static final String CDELIVERYBID_VFREE10 = "cdeliverybid.vfree10";
  /**
   * 发货单表体vo.自定义项1
   */
  public static final String CDELIVERYBID_VBDEF1 = "cdeliverybid.vbdef1";
  /**
   * 发货单表体vo.自定义项2
   */
  public static final String CDELIVERYBID_VBDEF2 = "cdeliverybid.vbdef2";
  /**
   * 发货单表体vo.自定义项3
   */
  public static final String CDELIVERYBID_VBDEF3 = "cdeliverybid.vbdef3";
  /**
   * 发货单表体vo.自定义项4
   */
  public static final String CDELIVERYBID_VBDEF4 = "cdeliverybid.vbdef4";
  /**
   * 发货单表体vo.自定义项5
   */
  public static final String CDELIVERYBID_VBDEF5 = "cdeliverybid.vbdef5";
  /**
   * 发货单表体vo.自定义项6
   */
  public static final String CDELIVERYBID_VBDEF6 = "cdeliverybid.vbdef6";
  /**
   * 发货单表体vo.自定义项7
   */
  public static final String CDELIVERYBID_VBDEF7 = "cdeliverybid.vbdef7";
  /**
   * 发货单表体vo.自定义项8
   */
  public static final String CDELIVERYBID_VBDEF8 = "cdeliverybid.vbdef8";
  /**
   * 发货单表体vo.自定义项9
   */
  public static final String CDELIVERYBID_VBDEF9 = "cdeliverybid.vbdef9";
  /**
   * 发货单表体vo.自定义项10
   */
  public static final String CDELIVERYBID_VBDEF10 = "cdeliverybid.vbdef10";
  /**
   * 发货单表体vo.自定义项11
   */
  public static final String CDELIVERYBID_VBDEF11 = "cdeliverybid.vbdef11";
  /**
   * 发货单表体vo.自定义项12
   */
  public static final String CDELIVERYBID_VBDEF12 = "cdeliverybid.vbdef12";
  /**
   * 发货单表体vo.自定义项13
   */
  public static final String CDELIVERYBID_VBDEF13 = "cdeliverybid.vbdef13";
  /**
   * 发货单表体vo.自定义项14
   */
  public static final String CDELIVERYBID_VBDEF14 = "cdeliverybid.vbdef14";
  /**
   * 发货单表体vo.自定义项15
   */
  public static final String CDELIVERYBID_VBDEF15 = "cdeliverybid.vbdef15";
  /**
   * 发货单表体vo.自定义项16
   */
  public static final String CDELIVERYBID_VBDEF16 = "cdeliverybid.vbdef16";
  /**
   * 发货单表体vo.自定义项17
   */
  public static final String CDELIVERYBID_VBDEF17 = "cdeliverybid.vbdef17";
  /**
   * 发货单表体vo.自定义项18
   */
  public static final String CDELIVERYBID_VBDEF18 = "cdeliverybid.vbdef18";
  /**
   * 发货单表体vo.自定义项19
   */
  public static final String CDELIVERYBID_VBDEF19 = "cdeliverybid.vbdef19";
  /**
   * 发货单表体vo.自定义项20
   */
  public static final String CDELIVERYBID_VBDEF20 = "cdeliverybid.vbdef20";
  /**
   * 发货单表体vo.发货联系人
   */
  public static final String CDELIVERYBID_CSENDPERSONID = "cdeliverybid.csendpersonid";
  /**
   * 发货单表体vo.发货联系电话
   */
  public static final String CDELIVERYBID_VSENDTEL = "cdeliverybid.vsendtel";
  /**
   * 发货单表体vo.收货联系人
   */
  public static final String CDELIVERYBID_CRECEIVEPERSONID = "cdeliverybid.creceivepersonid";
  /**
   * 发货单表体vo.收货联系电话
   */
  public static final String CDELIVERYBID_VRECEIVETEL = "cdeliverybid.vreceivetel";
  /**
   * 发货单表体vo.收货地点
   */
  public static final String CDELIVERYBID_CRECEIVEADDDOCID = "cdeliverybid.creceiveadddocid";
  /**
   * 发货单表体vo.发货地区
   */
  public static final String CDELIVERYBID_CSENDAREAID = "cdeliverybid.csendareaid";
  /**
   * 发货单表体vo.发货地点
   */
  public static final String CDELIVERYBID_CSENDADDDOCID = "cdeliverybid.csendadddocid";
  /**
   * 发货单表体vo.发货地址
   */
  public static final String CDELIVERYBID_CSENDADDRID = "cdeliverybid.csendaddrid";
  /**
   * 发货单表体vo.订单发货关闭
   */
  public static final String CDELIVERYBID_BCLOSESRCFLAG = "cdeliverybid.bclosesrcflag";
  /**
   * 发货单表体vo.结算利润中心最新版本
   */
  public static final String CDELIVERYBID_CPROFITCENTERID = "cdeliverybid.cprofitcenterid";
  /**
   * 发货单表体vo.结算利润中心
   */
  public static final String CDELIVERYBID_CPROFITCENTERVID = "cdeliverybid.cprofitcentervid";
  /**
   * 发货单表体vo.应收组织最新版本
   */
  public static final String CDELIVERYBID_CARORGID = "cdeliverybid.carorgid";
  /**
   * 发货单表体vo.应收组织
   */
  public static final String CDELIVERYBID_CARORGVID = "cdeliverybid.carorgvid";
  /**
   * 发货单表体vo.结算财务组织
   */
  public static final String CDELIVERYBID_CSETTLEORGID = "cdeliverybid.csettleorgid";
  /**
   * 发货单表体vo.销售部门最新版本
   */
  public static final String CDELIVERYBID_CDEPTID = "cdeliverybid.cdeptid";
  /**
   * 发货单表体vo.销售部门
   */
  public static final String CDELIVERYBID_CDEPTVID = "cdeliverybid.cdeptvid";
  /**
   * 发货单表体vo.销售业务员
   */
  public static final String CDELIVERYBID_CEMPLOYEEID = "cdeliverybid.cemployeeid";
  /**
   * 发货单表体vo.结算财务组织
   */
  public static final String CDELIVERYBID_CSETTLEORGVID = "cdeliverybid.csettleorgvid";
  /**
   * 发货单表体vo.收货国家/地区
   */
  public static final String CDELIVERYBID_CRECECOUNTRYID = "cdeliverybid.crececountryid";
  /**
   * 发货单表体vo.发货国家/地区
   */
  public static final String CDELIVERYBID_CSENDCOUNTRYID = "cdeliverybid.csendcountryid";
  /**
   * 发货单表体vo.报税国家/地区
   */
  public static final String CDELIVERYBID_CTAXCOUNTRYID = "cdeliverybid.ctaxcountryid";
  /**
   * 发货单表体vo.购销类型
   */
  public static final String CDELIVERYBID_FBUYSELLFLAG = "cdeliverybid.fbuysellflag";
  /**
   * 发货单表体vo.三角贸易
   */
  public static final String CDELIVERYBID_BTRIATRADEFLAG = "cdeliverybid.btriatradeflag";
  /**
   * 发货单表体vo.销售渠道
   */
  public static final String CDELIVERYBID_CCHANNELTYPEID = "cdeliverybid.cchanneltypeid";
  /**
   * 发货单表体vo.源头单据日期
   */
  public static final String CDELIVERYBID_VFIRSTBILLDATE = "cdeliverybid.vfirstbilldate";
  /**
   * 发货单表体vo.累计途损数量
   */
  public static final String CDELIVERYBID_NTRANSLOSSNUM = "cdeliverybid.ntranslossnum";
  /**
   * 发货单表体vo.累计出库对冲数量
   */
  public static final String CDELIVERYBID_NTOTALRUSHNUM = "cdeliverybid.ntotalrushnum";
  /**
   * 发货单表体vo.累计暂估应收数量
   */
  public static final String CDELIVERYBID_NTOTALESTARNUM = "cdeliverybid.ntotalestarnum";
  /**
   * 发货单表体vo.累计确认应收数量
   */
  public static final String CDELIVERYBID_NTOTALARNUM = "cdeliverybid.ntotalarnum";
  /**
   * 发货单表体vo.预留数量
   */
  public static final String CDELIVERYBID_NREQRSNUM = "cdeliverybid.nreqrsnum";
  /**
   * 发货单表体vo.是否已质检
   */
  public static final String CDELIVERYBID_BQUALITYFLAG = "cdeliverybid.bqualityflag";
  /**
   * 发货单表体vo.待垫运费
   */
  public static final String CDELIVERYBID_BADVFEEFLAG = "cdeliverybid.badvfeeflag";
  /**
   * 发货单表体vo.所属集团
   */
  public static final String CDELIVERYBID_PK_GROUP = "cdeliverybid.pk_group";
  /**
   * 发货单表体vo.价格组成
   */
  public static final String CDELIVERYBID_CPRICEFORMID = "cdeliverybid.cpriceformid";
  /**
   * 发货单表体vo.退货原因
   */
  public static final String CDELIVERYBID_CRETREASONID = "cdeliverybid.cretreasonid";
  /**
   * 发货单表体vo.退货责任处理方式
   */
  public static final String CDELIVERYBID_VRETURNMODE = "cdeliverybid.vreturnmode";
  /**
   * 发货单表体vo.累计应发未出库主数量
   */
  public static final String CDELIVERYBID_NTOTALNOTOUTNUM = "cdeliverybid.ntotalnotoutnum";
  /**
   * 发货单表体vo.全局本币价税合计
   */
  public static final String CDELIVERYBID_NGLOBALTAXMNY = "cdeliverybid.nglobaltaxmny";
  /**
   * 发货单表体vo.全局本币无税金额
   */
  public static final String CDELIVERYBID_NGLOBALMNY = "cdeliverybid.nglobalmny";
  /**
   * 发货单表体vo.全局本位币汇率
   */
  public static final String CDELIVERYBID_NGLOBALEXCHGRATE = "cdeliverybid.nglobalexchgrate";
  /**
   * 发货单表体vo.集团本币价税合计
   */
  public static final String CDELIVERYBID_NGROUPTAXMNY = "cdeliverybid.ngrouptaxmny";
  /**
   * 发货单表体vo.集团本币无税金额
   */
  public static final String CDELIVERYBID_NGROUPMNY = "cdeliverybid.ngroupmny";
  /**
   * 发货单表体vo.集团本位币汇率
   */
  public static final String CDELIVERYBID_NGROUPEXCHGRATE = "cdeliverybid.ngroupexchgrate";
  /**
   * 发货单表体vo.发货单质检表id
   */
  public static final String CDELIVERYBID_CDELIVERYBBID = "cdeliverybid.cdeliverybbid";
  /**
   * 发货单表体vo.发货单质检表ts
   */
  public static final String CDELIVERYBID_TTS = "cdeliverybid.tts";
  /**
   * 发货单表体vo.来源单据表头时间戳
   */
  public static final String CDELIVERYBID_SRCTS = "cdeliverybid.srcts";
  /**
   * 发货单表体vo.来源单据表体时间戳
   */
  public static final String CDELIVERYBID_SRCBTS = "cdeliverybid.srcbts";
  /**
   * 发货单表体vo.收入结算关闭
   */
  public static final String CDELIVERYBID_BBARSETTLEFLAG = "cdeliverybid.bbarsettleflag";
  /**
   * 发货单表体vo.发货利润中心
   */
  public static final String CDELIVERYBID_CSPROFITCENTERVID = "cdeliverybid.csprofitcentervid";
  /**
   * 发货单表体vo.发货利润中心最新版本
   */
  public static final String CDELIVERYBID_CSPROFITCENTERID = "cdeliverybid.csprofitcenterid";
  /**
   * 发货单表体vo.收货利润中心
   */
  public static final String CDELIVERYBID_CRPROFITCENTERVID = "cdeliverybid.crprofitcentervid";
  /**
   * 发货单表体vo.收货利润中心最新版本
   */
  public static final String CDELIVERYBID_CRPROFITCENTERID = "cdeliverybid.crprofitcenterid";
  /**
   * 发货单表体vo.特征码
   */
  public static final String CDELIVERYBID_CMFFILEID = "cdeliverybid.cmffileid";
  /**
   * 发货单表体vo.vostatus
   */
  public static final String CDELIVERYBID_STATUS = "cdeliverybid.status";
  /**
   * 发货单表体vo.dr
   */
  public static final String CDELIVERYBID_DR = "cdeliverybid.dr";
  /**
   * 发货单表体vo.ts
   */
  public static final String CDELIVERYBID_TS = "cdeliverybid.ts";
  /**
   * 发货单表体vo.发货单质检表vo.发货单质检实体
   */
  public static final String CDELIVERYBID_CDELIVERYCID_CDELIVERYCID = "cdeliverybid.cdeliverycid.cdeliverycid";
  /**
   * 发货单表体vo.发货单质检表vo.质检结果行
   */
  public static final String CDELIVERYBID_CDELIVERYCID_CROWNO = "cdeliverybid.cdeliverycid.crowno";
  /**
   * 发货单表体vo.发货单质检表vo.物料版本
   */
  public static final String CDELIVERYBID_CDELIVERYCID_CMATERIALID = "cdeliverybid.cdeliverycid.cmaterialid";
  /**
   * 发货单表体vo.发货单质检表vo.物料编码
   */
  public static final String CDELIVERYBID_CDELIVERYCID_CMATERIALVID = "cdeliverybid.cdeliverycid.cmaterialvid";
  /**
   * 发货单表体vo.发货单质检表vo.供应商
   */
  public static final String CDELIVERYBID_CDELIVERYCID_CVENDORID = "cdeliverybid.cdeliverycid.cvendorid";
  /**
   * 发货单表体vo.发货单质检表vo.项目
   */
  public static final String CDELIVERYBID_CDELIVERYCID_CPROJECTID = "cdeliverybid.cdeliverycid.cprojectid";
  /**
   * 发货单表体vo.发货单质检表vo.质量等级
   */
  public static final String CDELIVERYBID_CDELIVERYCID_CQUALITYLEVELID = "cdeliverybid.cdeliverycid.cqualitylevelid";
  /**
   * 发货单表体vo.发货单质检表vo.生产厂商
   */
  public static final String CDELIVERYBID_CDELIVERYCID_CPRODUCTORID = "cdeliverybid.cdeliverycid.cproductorid";
  /**
   * 发货单表体vo.发货单质检表vo.自由辅助属性1
   */
  public static final String CDELIVERYBID_CDELIVERYCID_VFREE1 = "cdeliverybid.cdeliverycid.vfree1";
  /**
   * 发货单表体vo.发货单质检表vo.自由辅助属性2
   */
  public static final String CDELIVERYBID_CDELIVERYCID_VFREE2 = "cdeliverybid.cdeliverycid.vfree2";
  /**
   * 发货单表体vo.发货单质检表vo.自由辅助属性3
   */
  public static final String CDELIVERYBID_CDELIVERYCID_VFREE3 = "cdeliverybid.cdeliverycid.vfree3";
  /**
   * 发货单表体vo.发货单质检表vo.自由辅助属性4
   */
  public static final String CDELIVERYBID_CDELIVERYCID_VFREE4 = "cdeliverybid.cdeliverycid.vfree4";
  /**
   * 发货单表体vo.发货单质检表vo.自由辅助属性5
   */
  public static final String CDELIVERYBID_CDELIVERYCID_VFREE5 = "cdeliverybid.cdeliverycid.vfree5";
  /**
   * 发货单表体vo.发货单质检表vo.自由辅助属性6
   */
  public static final String CDELIVERYBID_CDELIVERYCID_VFREE6 = "cdeliverybid.cdeliverycid.vfree6";
  /**
   * 发货单表体vo.发货单质检表vo.自由辅助属性7
   */
  public static final String CDELIVERYBID_CDELIVERYCID_VFREE7 = "cdeliverybid.cdeliverycid.vfree7";
  /**
   * 发货单表体vo.发货单质检表vo.自由辅助属性8
   */
  public static final String CDELIVERYBID_CDELIVERYCID_VFREE8 = "cdeliverybid.cdeliverycid.vfree8";
  /**
   * 发货单表体vo.发货单质检表vo.自由辅助属性9
   */
  public static final String CDELIVERYBID_CDELIVERYCID_VFREE9 = "cdeliverybid.cdeliverycid.vfree9";
  /**
   * 发货单表体vo.发货单质检表vo.自由辅助属性10
   */
  public static final String CDELIVERYBID_CDELIVERYCID_VFREE10 = "cdeliverybid.cdeliverycid.vfree10";
  /**
   * 发货单表体vo.发货单质检表vo.批次档案
   */
  public static final String CDELIVERYBID_CDELIVERYCID_PK_BATCHCODE = "cdeliverybid.cdeliverycid.pk_batchcode";
  /**
   * 发货单表体vo.发货单质检表vo.批次号
   */
  public static final String CDELIVERYBID_CDELIVERYCID_VBATCHCODE = "cdeliverybid.cdeliverycid.vbatchcode";
  /**
   * 发货单表体vo.发货单质检表vo.单位
   */
  public static final String CDELIVERYBID_CDELIVERYCID_CASTUNITID = "cdeliverybid.cdeliverycid.castunitid";
  /**
   * 发货单表体vo.发货单质检表vo.数量
   */
  public static final String CDELIVERYBID_CDELIVERYCID_NASTNUM = "cdeliverybid.cdeliverycid.nastnum";
  /**
   * 发货单表体vo.发货单质检表vo.主单位
   */
  public static final String CDELIVERYBID_CDELIVERYCID_CUNITID = "cdeliverybid.cdeliverycid.cunitid";
  /**
   * 发货单表体vo.发货单质检表vo.主数量
   */
  public static final String CDELIVERYBID_CDELIVERYCID_NNUM = "cdeliverybid.cdeliverycid.nnum";
  /**
   * 发货单表体vo.发货单质检表vo.换算率
   */
  public static final String CDELIVERYBID_CDELIVERYCID_VCHANGERATE = "cdeliverybid.cdeliverycid.vchangerate";
  /**
   * 发货单表体vo.发货单质检表vo.报价单位
   */
  public static final String CDELIVERYBID_CDELIVERYCID_CQTUNITID = "cdeliverybid.cdeliverycid.cqtunitid";
  /**
   * 发货单表体vo.发货单质检表vo.质检报价数量
   */
  public static final String CDELIVERYBID_CDELIVERYCID_NQTUNITNUM = "cdeliverybid.cdeliverycid.nqtunitnum";
  /**
   * 发货单表体vo.发货单质检表vo.报价换算率
   */
  public static final String CDELIVERYBID_CDELIVERYCID_VQTUNITRATE = "cdeliverybid.cdeliverycid.vqtunitrate";
  /**
   * 发货单表体vo.发货单质检表vo.质检单据号
   */
  public static final String CDELIVERYBID_CDELIVERYCID_VCHECKCODE = "cdeliverybid.cdeliverycid.vcheckcode";
  /**
   * 发货单表体vo.发货单质检表vo.质检日期
   */
  public static final String CDELIVERYBID_CDELIVERYCID_DCHECKDATE = "cdeliverybid.cdeliverycid.dcheckdate";
  /**
   * 发货单表体vo.发货单质检表vo.检验状态
   */
  public static final String CDELIVERYBID_CDELIVERYCID_CCHECKSTATEBID = "cdeliverybid.cdeliverycid.ccheckstatebid";
  /**
   * 发货单表体vo.发货单质检表vo.建议处理方式
   */
  public static final String CDELIVERYBID_CDELIVERYCID_CDEFECTPROCESSID = "cdeliverybid.cdeliverycid.cdefectprocessid";
  /**
   * 发货单表体vo.发货单质检表vo.是否合格
   */
  public static final String CDELIVERYBID_CDELIVERYCID_BELIGFLAG = "cdeliverybid.cdeliverycid.beligflag";
  /**
   * 发货单表体vo.发货单质检表vo.质检报告是否可入库
   */
  public static final String CDELIVERYBID_CDELIVERYCID_BCHECKINFLAG = "cdeliverybid.cdeliverycid.bcheckinflag";
  /**
   * 发货单表体vo.发货单质检表vo.赠品
   */
  public static final String CDELIVERYBID_CDELIVERYCID_BLARGESSFLAG = "cdeliverybid.cdeliverycid.blargessflag";
  /**
   * 发货单表体vo.发货单质检表vo.物料改判
   */
  public static final String CDELIVERYBID_CDELIVERYCID_BPRICECHGFLAG = "cdeliverybid.cdeliverycid.bpricechgflag";
  /**
   * 发货单表体vo.发货单质检表vo.税率
   */
  public static final String CDELIVERYBID_CDELIVERYCID_NTAXRATE = "cdeliverybid.cdeliverycid.ntaxrate";
  /**
   * 发货单表体vo.发货单质检表vo.整单折扣
   */
  public static final String CDELIVERYBID_CDELIVERYCID_NDISCOUNTRATE = "cdeliverybid.cdeliverycid.ndiscountrate";
  /**
   * 发货单表体vo.发货单质检表vo.单品折扣
   */
  public static final String CDELIVERYBID_CDELIVERYCID_NITEMDISCOUNTRATE = "cdeliverybid.cdeliverycid.nitemdiscountrate";
  /**
   * 发货单表体vo.发货单质检表vo.主含税单价
   */
  public static final String CDELIVERYBID_CDELIVERYCID_NORIGTAXPRICE = "cdeliverybid.cdeliverycid.norigtaxprice";
  /**
   * 发货单表体vo.发货单质检表vo.主无税单价
   */
  public static final String CDELIVERYBID_CDELIVERYCID_NORIGPRICE = "cdeliverybid.cdeliverycid.norigprice";
  /**
   * 发货单表体vo.发货单质检表vo.主含税净价
   */
  public static final String CDELIVERYBID_CDELIVERYCID_NORIGTAXNETPRICE = "cdeliverybid.cdeliverycid.norigtaxnetprice";
  /**
   * 发货单表体vo.发货单质检表vo.主无税净价
   */
  public static final String CDELIVERYBID_CDELIVERYCID_NORIGNETPRICE = "cdeliverybid.cdeliverycid.norignetprice";
  /**
   * 发货单表体vo.发货单质检表vo.含税单价
   */
  public static final String CDELIVERYBID_CDELIVERYCID_NQTORIGTAXPRICE = "cdeliverybid.cdeliverycid.nqtorigtaxprice";
  /**
   * 发货单表体vo.发货单质检表vo.无税单价
   */
  public static final String CDELIVERYBID_CDELIVERYCID_NQTORIGPRICE = "cdeliverybid.cdeliverycid.nqtorigprice";
  /**
   * 发货单表体vo.发货单质检表vo.含税净价
   */
  public static final String CDELIVERYBID_CDELIVERYCID_NQTORIGTAXNETPRC = "cdeliverybid.cdeliverycid.nqtorigtaxnetprc";
  /**
   * 发货单表体vo.发货单质检表vo.无税净价
   */
  public static final String CDELIVERYBID_CDELIVERYCID_NQTORIGNETPRICE = "cdeliverybid.cdeliverycid.nqtorignetprice";
  /**
   * 发货单表体vo.发货单质检表vo.无税金额
   */
  public static final String CDELIVERYBID_CDELIVERYCID_NORIGMNY = "cdeliverybid.cdeliverycid.norigmny";
  /**
   * 发货单表体vo.发货单质检表vo.价税合计
   */
  public static final String CDELIVERYBID_CDELIVERYCID_NORIGTAXMNY = "cdeliverybid.cdeliverycid.norigtaxmny";
  /**
   * 发货单表体vo.发货单质检表vo.折扣额
   */
  public static final String CDELIVERYBID_CDELIVERYCID_NORIGDISCOUNT = "cdeliverybid.cdeliverycid.norigdiscount";
  /**
   * 发货单表体vo.发货单质检表vo.本币主含税单价
   */
  public static final String CDELIVERYBID_CDELIVERYCID_NTAXPRICE = "cdeliverybid.cdeliverycid.ntaxprice";
  /**
   * 发货单表体vo.发货单质检表vo.本币主无税单价
   */
  public static final String CDELIVERYBID_CDELIVERYCID_NPRICE = "cdeliverybid.cdeliverycid.nprice";
  /**
   * 发货单表体vo.发货单质检表vo.本币主含税净价
   */
  public static final String CDELIVERYBID_CDELIVERYCID_NTAXNETPRICE = "cdeliverybid.cdeliverycid.ntaxnetprice";
  /**
   * 发货单表体vo.发货单质检表vo.本币主无税净价
   */
  public static final String CDELIVERYBID_CDELIVERYCID_NNETPRICE = "cdeliverybid.cdeliverycid.nnetprice";
  /**
   * 发货单表体vo.发货单质检表vo.本币含税单价
   */
  public static final String CDELIVERYBID_CDELIVERYCID_NQTTAXPRICE = "cdeliverybid.cdeliverycid.nqttaxprice";
  /**
   * 发货单表体vo.发货单质检表vo.本币无税单价
   */
  public static final String CDELIVERYBID_CDELIVERYCID_NQTPRICE = "cdeliverybid.cdeliverycid.nqtprice";
  /**
   * 发货单表体vo.发货单质检表vo.本币含税净价
   */
  public static final String CDELIVERYBID_CDELIVERYCID_NQTTAXNETPRICE = "cdeliverybid.cdeliverycid.nqttaxnetprice";
  /**
   * 发货单表体vo.发货单质检表vo.本币无税净价
   */
  public static final String CDELIVERYBID_CDELIVERYCID_NQTNETPRICE = "cdeliverybid.cdeliverycid.nqtnetprice";
  /**
   * 发货单表体vo.发货单质检表vo.本币税额
   */
  public static final String CDELIVERYBID_CDELIVERYCID_NTAX = "cdeliverybid.cdeliverycid.ntax";
  /**
   * 发货单表体vo.发货单质检表vo.本币无税金额
   */
  public static final String CDELIVERYBID_CDELIVERYCID_NMNY = "cdeliverybid.cdeliverycid.nmny";
  /**
   * 发货单表体vo.发货单质检表vo.本币价税合计
   */
  public static final String CDELIVERYBID_CDELIVERYCID_NTAXMNY = "cdeliverybid.cdeliverycid.ntaxmny";
  /**
   * 发货单表体vo.发货单质检表vo.本币折扣额
   */
  public static final String CDELIVERYBID_CDELIVERYCID_NDISCOUNT = "cdeliverybid.cdeliverycid.ndiscount";
  /**
   * 发货单表体vo.发货单质检表vo.原币
   */
  public static final String CDELIVERYBID_CDELIVERYCID_CORIGCURRENCYID = "cdeliverybid.cdeliverycid.corigcurrencyid";
  /**
   * 发货单表体vo.发货单质检表vo.折本汇率
   */
  public static final String CDELIVERYBID_CDELIVERYCID_NEXCHANGERATE = "cdeliverybid.cdeliverycid.nexchangerate";
  /**
   * 发货单表体vo.发货单质检表vo.本位币
   */
  public static final String CDELIVERYBID_CDELIVERYCID_CCURRENCYID = "cdeliverybid.cdeliverycid.ccurrencyid";
  /**
   * 发货单表体vo.发货单质检表vo.累计运输数量
   */
  public static final String CDELIVERYBID_CDELIVERYCID_NTOTALTRANSNUM = "cdeliverybid.cdeliverycid.ntotaltransnum";
  /**
   * 发货单表体vo.发货单质检表vo.运输关闭
   */
  public static final String CDELIVERYBID_CDELIVERYCID_BTRANSENDFLAG = "cdeliverybid.cdeliverycid.btransendflag";
  /**
   * 发货单表体vo.发货单质检表vo.累计出库数量
   */
  public static final String CDELIVERYBID_CDELIVERYCID_NTOTALOUTNUM = "cdeliverybid.cdeliverycid.ntotaloutnum";
  /**
   * 发货单表体vo.发货单质检表vo.出库关闭
   */
  public static final String CDELIVERYBID_CDELIVERYCID_BOUTENDFLAG = "cdeliverybid.cdeliverycid.boutendflag";
  /**
   * 发货单表体vo.发货单质检表vo.备注
   */
  public static final String CDELIVERYBID_CDELIVERYCID_VROWNOTE = "cdeliverybid.cdeliverycid.vrownote";
  /**
   * 发货单表体vo.发货单质检表vo.接收单行号
   */
  public static final String CDELIVERYBID_CDELIVERYCID_VSRCROWNO = "cdeliverybid.cdeliverycid.vsrcrowno";
  /**
   * 发货单表体vo.发货单质检表vo.物流组织
   */
  public static final String CDELIVERYBID_CDELIVERYCID_PK_ORG = "cdeliverybid.cdeliverycid.pk_org";
  /**
   * 发货单表体vo.发货单质检表vo.所属集团
   */
  public static final String CDELIVERYBID_CDELIVERYCID_PK_GROUP = "cdeliverybid.cdeliverycid.pk_group";
  /**
   * 发货单表体vo.发货单质检表vo.累计应发未出库主数量
   */
  public static final String CDELIVERYBID_CDELIVERYCID_NTOTALNOTOUTNUM = "cdeliverybid.cdeliverycid.ntotalnotoutnum";
  /**
   * 发货单表体vo.发货单质检表vo.来源单据id
   */
  public static final String CDELIVERYBID_CDELIVERYCID_CSRCID = "cdeliverybid.cdeliverycid.csrcid";
  /**
   * 发货单表体vo.发货单质检表vo.全局本币价税合计
   */
  public static final String CDELIVERYBID_CDELIVERYCID_NGLOBALTAXMNY = "cdeliverybid.cdeliverycid.nglobaltaxmny";
  /**
   * 发货单表体vo.发货单质检表vo.全局本币无税金额
   */
  public static final String CDELIVERYBID_CDELIVERYCID_NGLOBALMNY = "cdeliverybid.cdeliverycid.nglobalmny";
  /**
   * 发货单表体vo.发货单质检表vo.全局本位币汇率
   */
  public static final String CDELIVERYBID_CDELIVERYCID_NGLOBALEXCHGRATE = "cdeliverybid.cdeliverycid.nglobalexchgrate";
  /**
   * 发货单表体vo.发货单质检表vo.集团本币价税合计
   */
  public static final String CDELIVERYBID_CDELIVERYCID_NGROUPTAXMNY = "cdeliverybid.cdeliverycid.ngrouptaxmny";
  /**
   * 发货单表体vo.发货单质检表vo.集团本币无税金额
   */
  public static final String CDELIVERYBID_CDELIVERYCID_NGROUPMNY = "cdeliverybid.cdeliverycid.ngroupmny";
  /**
   * 发货单表体vo.发货单质检表vo.集团本位币汇率
   */
  public static final String CDELIVERYBID_CDELIVERYCID_NGROUPEXCHGRATE = "cdeliverybid.cdeliverycid.ngroupexchgrate";
  /**
   * 发货单表体vo.发货单质检表vo.收货国家/地区
   */
  public static final String CDELIVERYBID_CDELIVERYCID_CRECECOUNTRYID = "cdeliverybid.cdeliverycid.crececountryid";
  /**
   * 发货单表体vo.发货单质检表vo.发货国/地区
   */
  public static final String CDELIVERYBID_CDELIVERYCID_CSENDCOUNTRYID = "cdeliverybid.cdeliverycid.csendcountryid";
  /**
   * 发货单表体vo.发货单质检表vo.报税国/地区
   */
  public static final String CDELIVERYBID_CDELIVERYCID_CTAXCOUNTRYID = "cdeliverybid.cdeliverycid.ctaxcountryid";
  /**
   * 发货单表体vo.发货单质检表vo.购销类型
   */
  public static final String CDELIVERYBID_CDELIVERYCID_FBUYSELLFLAG = "cdeliverybid.cdeliverycid.fbuysellflag";
  /**
   * 发货单表体vo.发货单质检表vo.三角贸易
   */
  public static final String CDELIVERYBID_CDELIVERYCID_BTRIATRADEFLAG = "cdeliverybid.cdeliverycid.btriatradeflag";
  /**
   * 发货单表体vo.发货单质检表vo.税码
   */
  public static final String CDELIVERYBID_CDELIVERYCID_CTAXCODEID = "cdeliverybid.cdeliverycid.ctaxcodeid";
  /**
   * 发货单表体vo.发货单质检表vo.扣税类别
   */
  public static final String CDELIVERYBID_CDELIVERYCID_FTAXTYPEFLAG = "cdeliverybid.cdeliverycid.ftaxtypeflag";
  /**
   * 发货单表体vo.发货单质检表vo.原产国
   */
  public static final String CDELIVERYBID_CDELIVERYCID_CORIGCOUNTRYID = "cdeliverybid.cdeliverycid.corigcountryid";
  /**
   * 发货单表体vo.发货单质检表vo.原产地区
   */
  public static final String CDELIVERYBID_CDELIVERYCID_CORIGAREAID = "cdeliverybid.cdeliverycid.corigareaid";
  /**
   * 发货单表体vo.发货单质检表vo.计税金额
   */
  public static final String CDELIVERYBID_CDELIVERYCID_NCALTAXMNY = "cdeliverybid.cdeliverycid.ncaltaxmny";
  /**
   * 发货单表体vo.发货单质检表vo.vostatus
   */
  public static final String CDELIVERYBID_CDELIVERYCID_STATUS = "cdeliverybid.cdeliverycid.status";
  /**
   * 发货单表体vo.发货单质检表vo.dr
   */
  public static final String CDELIVERYBID_CDELIVERYCID_DR = "cdeliverybid.cdeliverycid.dr";
  /**
   * 发货单表体vo.发货单质检表vo.ts
   */
  public static final String CDELIVERYBID_CDELIVERYCID_TS = "cdeliverybid.cdeliverycid.ts";
}


