package nc.pubitf.so.m32.api;

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
 * @version 2015-11-12 上午10:58:02
 * @author 刘景
 */
public interface ISaleInvoiceVO extends Serializable {
  
  /**
   * 发票主实体
   */
  public static final String CSALEINVOICEID = "csaleinvoiceid";
  /**
   * 集团
   */
  public static final String PK_GROUP = "pk_group";
  /**
   * 开票组织
   */
  public static final String PK_ORG = "pk_org";
  /**
   * 开票组织版本
   */
  public static final String PK_ORG_V = "pk_org_v";
  /**
   * 发票号
   */
  public static final String VBILLCODE = "vbillcode";
  /**
   * 业务流程
   */
  public static final String CBIZTYPEID = "cbiztypeid";
  /**
   * 发票类型
   */
  public static final String CTRANTYPEID = "ctrantypeid";
  /**
   * 发票类型编码
   */
  public static final String VTRANTYPECODE = "vtrantypecode";
  /**
   * 开票日期
   */
  public static final String DBILLDATE = "dbilldate";
  /**
   * 客户名称
   */
  public static final String CINVOICECUSTID = "cinvoicecustid";
  /**
   * 客户打印名称
   */
  public static final String VPRINTCUSTNAME = "vprintcustname";
  /**
   * 客户开户银行
   */
  public static final String CCUSTBANKID = "ccustbankid";
  /**
   * 客户银行账号
   */
  public static final String CCUSTBANKACCID = "ccustbankaccid";
  /**
   * 收款协议
   */
  public static final String CPAYTERMID = "cpaytermid";
  /**
   * 信用证号
   */
  public static final String VCREDITNUM = "vcreditnum";
  /**
   * 金税票号
   */
  public static final String VGOLDTAXCODE = "vgoldtaxcode";
  /**
   * 是否已经传金税
   */
  public static final String BTOGOLDTAXFLAG = "btogoldtaxflag";
  /**
   * 最后传金税时间
   */
  public static final String TGOLDTAXTIME = "tgoldtaxtime";
  /**
   * 币种
   */
  public static final String CORIGCURRENCYID = "corigcurrencyid";
  /**
   * 折本汇率
   */
  public static final String NEXCHANGERATE = "nexchangerate";
  /**
   * 本位币
   */
  public static final String CCURRENCYID = "ccurrencyid";
  /**
   * 集团本位币汇率
   */
  public static final String NGROUPEXCHGRATE = "ngroupexchgrate";
  /**
   * 全局本位币汇率
   */
  public static final String NGLOBALEXCHGRATE = "nglobalexchgrate";
  /**
   * 发票折扣
   */
  public static final String NHVOICEDISRATE = "nhvoicedisrate";
  /**
   * 总数量
   */
  public static final String NTOTALASTNUM = "ntotalastnum";
  /**
   * 冲抵金额
   */
  public static final String NTOTALORIGSUBMNY = "ntotalorigsubmny";
  /**
   * 价税合计
   */
  public static final String NTOTALORIGMNY = "ntotalorigmny";
  /**
   * 发货国家/地区
   */
  public static final String CSENDCOUNTRYID = "csendcountryid";
  /**
   * 收货国家/地区
   */
  public static final String CRECECOUNTRYID = "crececountryid";
  /**
   * 报税国家/地区
   */
  public static final String CTAXCOUNTRYID = "ctaxcountryid";
  /**
   * 购销类型
   */
  public static final String FBUYSELLFLAG = "fbuysellflag";
  /**
   * 三角贸易
   */
  public static final String BTRIATRADEFLAG = "btriatradeflag";
  /**
   * VAT注册码
   */
  public static final String VVATCODE = "vvatcode";
  /**
   * 客户VAT注册码
   */
  public static final String VCUSTVATCODE = "vcustvatcode";
  /**
   * 贸易术语
   */
  public static final String CTRADEWORDID = "ctradewordid";
  /**
   * 冲抵标记
   */
  public static final String BSUBUNITFLAG = "bsubunitflag";
  /**
   * 对冲标记
   */
  public static final String FOPPOSEFLAG = "fopposeflag";
  /**
   * 对冲来源发票号
   */
  public static final String VOPPOSESRCCODE = "vopposesrccode";
  /**
   * 对冲来源主表
   */
  public static final String COPPOSESRCID = "copposesrcid";
  /**
   * 备注
   */
  public static final String VNOTE = "vnote";
  /**
   * 单据状态
   */
  public static final String FSTATUSFLAG = "fstatusflag";
  /**
   * 创建人
   */
  public static final String CREATOR = "creator";
  /**
   * 制单人
   */
  public static final String BILLMAKER = "billmaker";
  /**
   * 制单日期
   */
  public static final String DMAKEDATE = "dmakedate";
  /**
   * 创建时间
   */
  public static final String CREATIONTIME = "creationtime";
  /**
   * 最后修改人
   */
  public static final String MODIFIER = "modifier";
  /**
   * 最后修改时间
   */
  public static final String MODIFIEDTIME = "modifiedtime";
  /**
   * 审批人
   */
  public static final String APPROVER = "approver";
  /**
   * 审核日期
   */
  public static final String TAUDITTIME = "taudittime";
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
   * 结算方式
   */
  public static final String CBALANCETYPEID = "cbalancetypeid";
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
   * 发票子实体.发票子实体
   */
  public static final String CSALEINVOICEBID_CSALEINVOICEBID = "csaleinvoicebid.csaleinvoicebid";
  /**
   * 发票子实体.集团
   */
  public static final String CSALEINVOICEBID_PK_GROUP = "csaleinvoicebid.pk_group";
  /**
   * 发票子实体.开票组织
   */
  public static final String CSALEINVOICEBID_PK_ORG = "csaleinvoicebid.pk_org";
  /**
   * 发票子实体.开票日期
   */
  public static final String CSALEINVOICEBID_DBILLDATE = "csaleinvoicebid.dbilldate";
  /**
   * 发票子实体.行号
   */
  public static final String CSALEINVOICEBID_CROWNO = "csaleinvoicebid.crowno";
  /**
   * 发票子实体.客户物料码
   */
  public static final String CSALEINVOICEBID_CCUSTMATERIALID = "csaleinvoicebid.ccustmaterialid";
  /**
   * 发票子实体.物料档案
   */
  public static final String CSALEINVOICEBID_CMATERIALID = "csaleinvoicebid.cmaterialid";
  /**
   * 发票子实体.物料编码
   */
  public static final String CSALEINVOICEBID_CMATERIALVID = "csaleinvoicebid.cmaterialvid";
  /**
   * 发票子实体.供应商
   */
  public static final String CSALEINVOICEBID_CVENDORID = "csaleinvoicebid.cvendorid";
  /**
   * 发票子实体.项目
   */
  public static final String CSALEINVOICEBID_CPROJECTID = "csaleinvoicebid.cprojectid";
  /**
   * 发票子实体.生产厂商
   */
  public static final String CSALEINVOICEBID_CPRODUCTORID = "csaleinvoicebid.cproductorid";
  /**
   * 发票子实体.单位
   */
  public static final String CSALEINVOICEBID_CASTUNITID = "csaleinvoicebid.castunitid";
  /**
   * 发票子实体.数量
   */
  public static final String CSALEINVOICEBID_NASTNUM = "csaleinvoicebid.nastnum";
  /**
   * 发票子实体.主单位
   */
  public static final String CSALEINVOICEBID_CUNITID = "csaleinvoicebid.cunitid";
  /**
   * 发票子实体.主数量
   */
  public static final String CSALEINVOICEBID_NNUM = "csaleinvoicebid.nnum";
  /**
   * 发票子实体.换算率
   */
  public static final String CSALEINVOICEBID_VCHANGERATE = "csaleinvoicebid.vchangerate";
  /**
   * 发票子实体.报价单位
   */
  public static final String CSALEINVOICEBID_CQTUNITID = "csaleinvoicebid.cqtunitid";
  /**
   * 发票子实体.报价换算率
   */
  public static final String CSALEINVOICEBID_VQTUNITRATE = "csaleinvoicebid.vqtunitrate";
  /**
   * 发票子实体.报价数量
   */
  public static final String CSALEINVOICEBID_NQTUNITNUM = "csaleinvoicebid.nqtunitnum";
  /**
   * 发票子实体.折扣类
   */
  public static final String CSALEINVOICEBID_BDISCOUNTFLAG = "csaleinvoicebid.bdiscountflag";
  /**
   * 发票子实体.服务类
   */
  public static final String CSALEINVOICEBID_BLABORFLAG = "csaleinvoicebid.blaborflag";
  /**
   * 发票子实体.赠品
   */
  public static final String CSALEINVOICEBID_BLARGESSFLAG = "csaleinvoicebid.blargessflag";
  /**
   * 发票子实体.批次档案
   */
  public static final String CSALEINVOICEBID_PK_BATCHCODE = "csaleinvoicebid.pk_batchcode";
  /**
   * 发票子实体.批次号
   */
  public static final String CSALEINVOICEBID_VBATCHCODE = "csaleinvoicebid.vbatchcode";
  /**
   * 发票子实体.税码
   */
  public static final String CSALEINVOICEBID_CTAXCODEID = "csaleinvoicebid.ctaxcodeid";
  /**
   * 发票子实体.税率
   */
  public static final String CSALEINVOICEBID_NTAXRATE = "csaleinvoicebid.ntaxrate";
  /**
   * 发票子实体.扣税类别
   */
  public static final String CSALEINVOICEBID_FTAXTYPEFLAG = "csaleinvoicebid.ftaxtypeflag";
  /**
   * 发票子实体.整单折扣
   */
  public static final String CSALEINVOICEBID_NDISCOUNTRATE = "csaleinvoicebid.ndiscountrate";
  /**
   * 发票子实体.单品折扣
   */
  public static final String CSALEINVOICEBID_NITEMDISCOUNTRATE = "csaleinvoicebid.nitemdiscountrate";
  /**
   * 发票子实体.发票折扣
   */
  public static final String CSALEINVOICEBID_NINVOICEDISRATE = "csaleinvoicebid.ninvoicedisrate";
  /**
   * 发票子实体.主含税单价
   */
  public static final String CSALEINVOICEBID_NORIGTAXPRICE = "csaleinvoicebid.norigtaxprice";
  /**
   * 发票子实体.主无税单价
   */
  public static final String CSALEINVOICEBID_NORIGPRICE = "csaleinvoicebid.norigprice";
  /**
   * 发票子实体.主含税净价
   */
  public static final String CSALEINVOICEBID_NORIGTAXNETPRICE = "csaleinvoicebid.norigtaxnetprice";
  /**
   * 发票子实体.主无税净价
   */
  public static final String CSALEINVOICEBID_NORIGNETPRICE = "csaleinvoicebid.norignetprice";
  /**
   * 发票子实体.含税单价
   */
  public static final String CSALEINVOICEBID_NQTORIGTAXPRICE = "csaleinvoicebid.nqtorigtaxprice";
  /**
   * 发票子实体.无税单价
   */
  public static final String CSALEINVOICEBID_NQTORIGPRICE = "csaleinvoicebid.nqtorigprice";
  /**
   * 发票子实体.含税净价
   */
  public static final String CSALEINVOICEBID_NQTORIGTAXNETPRC = "csaleinvoicebid.nqtorigtaxnetprc";
  /**
   * 发票子实体.无税净价
   */
  public static final String CSALEINVOICEBID_NQTORIGNETPRICE = "csaleinvoicebid.nqtorignetprice";
  /**
   * 发票子实体.税额
   */
  public static final String CSALEINVOICEBID_NTAX = "csaleinvoicebid.ntax";
  /**
   * 发票子实体.计税金额
   */
  public static final String CSALEINVOICEBID_NCALTAXMNY = "csaleinvoicebid.ncaltaxmny";
  /**
   * 发票子实体.无税金额
   */
  public static final String CSALEINVOICEBID_NORIGMNY = "csaleinvoicebid.norigmny";
  /**
   * 发票子实体.价税合计
   */
  public static final String CSALEINVOICEBID_NORIGTAXMNY = "csaleinvoicebid.norigtaxmny";
  /**
   * 发票子实体.折扣额
   */
  public static final String CSALEINVOICEBID_NORIGDISCOUNT = "csaleinvoicebid.norigdiscount";
  /**
   * 发票子实体.主本币含税单价
   */
  public static final String CSALEINVOICEBID_NTAXPRICE = "csaleinvoicebid.ntaxprice";
  /**
   * 发票子实体.主本币无税单价
   */
  public static final String CSALEINVOICEBID_NPRICE = "csaleinvoicebid.nprice";
  /**
   * 发票子实体.主本币含税净价
   */
  public static final String CSALEINVOICEBID_NTAXNETPRICE = "csaleinvoicebid.ntaxnetprice";
  /**
   * 发票子实体.主本币无税净价
   */
  public static final String CSALEINVOICEBID_NNETPRICE = "csaleinvoicebid.nnetprice";
  /**
   * 发票子实体.本币含税单价
   */
  public static final String CSALEINVOICEBID_NQTTAXPRICE = "csaleinvoicebid.nqttaxprice";
  /**
   * 发票子实体.本币无税单价
   */
  public static final String CSALEINVOICEBID_NQTPRICE = "csaleinvoicebid.nqtprice";
  /**
   * 发票子实体.本币含税净价
   */
  public static final String CSALEINVOICEBID_NQTTAXNETPRICE = "csaleinvoicebid.nqttaxnetprice";
  /**
   * 发票子实体.本币无税净价
   */
  public static final String CSALEINVOICEBID_NQTNETPRICE = "csaleinvoicebid.nqtnetprice";
  /**
   * 发票子实体.本币无税金额
   */
  public static final String CSALEINVOICEBID_NMNY = "csaleinvoicebid.nmny";
  /**
   * 发票子实体.本币价税合计
   */
  public static final String CSALEINVOICEBID_NTAXMNY = "csaleinvoicebid.ntaxmny";
  /**
   * 发票子实体.本币折扣额
   */
  public static final String CSALEINVOICEBID_NDISCOUNT = "csaleinvoicebid.ndiscount";
  /**
   * 发票子实体.费用冲抵金额
   */
  public static final String CSALEINVOICEBID_NORIGSUBMNY = "csaleinvoicebid.norigsubmny";
  /**
   * 发票子实体.集团本币无税金额
   */
  public static final String CSALEINVOICEBID_NGROUPMNY = "csaleinvoicebid.ngroupmny";
  /**
   * 发票子实体.集团本币价税合计
   */
  public static final String CSALEINVOICEBID_NGROUPTAXMNY = "csaleinvoicebid.ngrouptaxmny";
  /**
   * 发票子实体.全局本币无税金额
   */
  public static final String CSALEINVOICEBID_NGLOBALMNY = "csaleinvoicebid.nglobalmny";
  /**
   * 发票子实体.全局本币价税合计
   */
  public static final String CSALEINVOICEBID_NGLOBALTAXMNY = "csaleinvoicebid.nglobaltaxmny";
  /**
   * 发票子实体.源头单据类型
   */
  public static final String CSALEINVOICEBID_VFIRSTTYPE = "csaleinvoicebid.vfirsttype";
  /**
   * 发票子实体.源头单据号
   */
  public static final String CSALEINVOICEBID_VFIRSTCODE = "csaleinvoicebid.vfirstcode";
  /**
   * 发票子实体.源头交易类型
   */
  public static final String CSALEINVOICEBID_VFIRSTTRANTYPE = "csaleinvoicebid.vfirsttrantype";
  /**
   * 发票子实体.源头单据行号
   */
  public static final String CSALEINVOICEBID_VFIRSTROWNO = "csaleinvoicebid.vfirstrowno";
  /**
   * 发票子实体.源头单据主表
   */
  public static final String CSALEINVOICEBID_CFIRSTID = "csaleinvoicebid.cfirstid";
  /**
   * 发票子实体.源头单据子表
   */
  public static final String CSALEINVOICEBID_CFIRSTBID = "csaleinvoicebid.cfirstbid";
  /**
   * 发票子实体.来源单据类型
   */
  public static final String CSALEINVOICEBID_VSRCTYPE = "csaleinvoicebid.vsrctype";
  /**
   * 发票子实体.来源单据号
   */
  public static final String CSALEINVOICEBID_VSRCCODE = "csaleinvoicebid.vsrccode";
  /**
   * 发票子实体.来源交易类型
   */
  public static final String CSALEINVOICEBID_VSRCTRANTYPE = "csaleinvoicebid.vsrctrantype";
  /**
   * 发票子实体.来源单据行号
   */
  public static final String CSALEINVOICEBID_VSRCROWNO = "csaleinvoicebid.vsrcrowno";
  /**
   * 发票子实体.来源单据主表
   */
  public static final String CSALEINVOICEBID_CSRCID = "csaleinvoicebid.csrcid";
  /**
   * 发票子实体.来源单据子表
   */
  public static final String CSALEINVOICEBID_CSRCBID = "csaleinvoicebid.csrcbid";
  /**
   * 发票子实体.对冲来源子表
   */
  public static final String CSALEINVOICEBID_COPPOSESRCBID = "csaleinvoicebid.copposesrcbid";
  /**
   * 发票子实体.销售组织
   */
  public static final String CSALEINVOICEBID_CSALEORGID = "csaleinvoicebid.csaleorgid";
  /**
   * 发票子实体.销售组织
   */
  public static final String CSALEINVOICEBID_CSALEORGVID = "csaleinvoicebid.csaleorgvid";
  /**
   * 发票子实体.结算利润中心最新版本
   */
  public static final String CSALEINVOICEBID_CPROFITCENTERID = "csaleinvoicebid.cprofitcenterid";
  /**
   * 发票子实体.结算利润中心
   */
  public static final String CSALEINVOICEBID_CPROFITCENTERVID = "csaleinvoicebid.cprofitcentervid";
  /**
   * 发票子实体.应收组织
   */
  public static final String CSALEINVOICEBID_CARORGID = "csaleinvoicebid.carorgid";
  /**
   * 发票子实体.应收组织最新版本
   */
  public static final String CSALEINVOICEBID_CARORGVID = "csaleinvoicebid.carorgvid";
  /**
   * 发票子实体.订单客户
   */
  public static final String CSALEINVOICEBID_CORDERCUSTID = "csaleinvoicebid.cordercustid";
  /**
   * 发票子实体.是否散户
   */
  public static final String CSALEINVOICEBID_BFREECUSTFLAG = "csaleinvoicebid.bfreecustflag";
  /**
   * 发票子实体.散户
   */
  public static final String CSALEINVOICEBID_CFREECUSTID = "csaleinvoicebid.cfreecustid";
  /**
   * 发票子实体.销售部门
   */
  public static final String CSALEINVOICEBID_CDEPTID = "csaleinvoicebid.cdeptid";
  /**
   * 发票子实体.销售部门最新版本
   */
  public static final String CSALEINVOICEBID_CDEPTVID = "csaleinvoicebid.cdeptvid";
  /**
   * 发票子实体.销售业务员
   */
  public static final String CSALEINVOICEBID_CEMPLOYEEID = "csaleinvoicebid.cemployeeid";
  /**
   * 发票子实体.销售渠道类型
   */
  public static final String CSALEINVOICEBID_CCHANNELTYPEID = "csaleinvoicebid.cchanneltypeid";
  /**
   * 发票子实体.收货客户
   */
  public static final String CSALEINVOICEBID_CRECEIVECUSTID = "csaleinvoicebid.creceivecustid";
  /**
   * 发票子实体.收货地址
   */
  public static final String CSALEINVOICEBID_CRECEIVEADDRID = "csaleinvoicebid.creceiveaddrid";
  /**
   * 发票子实体.运输方式
   */
  public static final String CSALEINVOICEBID_CTRANSPORTTYPEID = "csaleinvoicebid.ctransporttypeid";
  /**
   * 发票子实体.库存组织
   */
  public static final String CSALEINVOICEBID_CSENDSTOCKORGID = "csaleinvoicebid.csendstockorgid";
  /**
   * 发票子实体.库存组织
   */
  public static final String CSALEINVOICEBID_CSENDSTOCKORGVID = "csaleinvoicebid.csendstockorgvid";
  /**
   * 发票子实体.仓库
   */
  public static final String CSALEINVOICEBID_CSENDSTORDOCID = "csaleinvoicebid.csendstordocid";
  /**
   * 发票子实体.产品线
   */
  public static final String CSALEINVOICEBID_CPRODLINEID = "csaleinvoicebid.cprodlineid";
  /**
   * 发票子实体.收支项目
   */
  public static final String CSALEINVOICEBID_CCOSTSUBJID = "csaleinvoicebid.ccostsubjid";
  /**
   * 发票子实体.合同号
   */
  public static final String CSALEINVOICEBID_CCTMANAGEID = "csaleinvoicebid.cctmanageid";
  /**
   * 发票子实体.寄存供应商
   */
  public static final String CSALEINVOICEBID_CVMIVENDERID = "csaleinvoicebid.cvmivenderid";
  /**
   * 发票子实体.消耗汇总号
   */
  public static final String CSALEINVOICEBID_VSUMCODE = "csaleinvoicebid.vsumcode";
  /**
   * 发票子实体.消耗汇总主键
   */
  public static final String CSALEINVOICEBID_CSUMID = "csaleinvoicebid.csumid";
  /**
   * 发票子实体.累计应发未出库数量
   */
  public static final String CSALEINVOICEBID_NSHOULDOUTNUM = "csaleinvoicebid.nshouldoutnum";
  /**
   * 发票子实体.累计出库数量
   */
  public static final String CSALEINVOICEBID_NTOTALOUTNUM = "csaleinvoicebid.ntotaloutnum";
  /**
   * 发票子实体.累计确认应收数量
   */
  public static final String CSALEINVOICEBID_NTOTALINCOMENUM = "csaleinvoicebid.ntotalincomenum";
  /**
   * 发票子实体.累计确认应收金额
   */
  public static final String CSALEINVOICEBID_NTOTALINCOMEMNY = "csaleinvoicebid.ntotalincomemny";
  /**
   * 发票子实体.累计成本结算数量
   */
  public static final String CSALEINVOICEBID_NTOTALCOSTNUM = "csaleinvoicebid.ntotalcostnum";
  /**
   * 发票子实体.累计收款金额
   */
  public static final String CSALEINVOICEBID_NTOTALPAYMNY = "csaleinvoicebid.ntotalpaymny";
  /**
   * 发票子实体.备注
   */
  public static final String CSALEINVOICEBID_VROWNOTE = "csaleinvoicebid.vrownote";
  /**
   * 发票子实体.物料辅助属性1
   */
  public static final String CSALEINVOICEBID_VFREE1 = "csaleinvoicebid.vfree1";
  /**
   * 发票子实体.物料辅助属性2
   */
  public static final String CSALEINVOICEBID_VFREE2 = "csaleinvoicebid.vfree2";
  /**
   * 发票子实体.物料辅助属性3
   */
  public static final String CSALEINVOICEBID_VFREE3 = "csaleinvoicebid.vfree3";
  /**
   * 发票子实体.物料辅助属性4
   */
  public static final String CSALEINVOICEBID_VFREE4 = "csaleinvoicebid.vfree4";
  /**
   * 发票子实体.物料辅助属性5
   */
  public static final String CSALEINVOICEBID_VFREE5 = "csaleinvoicebid.vfree5";
  /**
   * 发票子实体.物料辅助属性6
   */
  public static final String CSALEINVOICEBID_VFREE6 = "csaleinvoicebid.vfree6";
  /**
   * 发票子实体.物料辅助属性7
   */
  public static final String CSALEINVOICEBID_VFREE7 = "csaleinvoicebid.vfree7";
  /**
   * 发票子实体.物料辅助属性8
   */
  public static final String CSALEINVOICEBID_VFREE8 = "csaleinvoicebid.vfree8";
  /**
   * 发票子实体.物料辅助属性9
   */
  public static final String CSALEINVOICEBID_VFREE9 = "csaleinvoicebid.vfree9";
  /**
   * 发票子实体.物料辅助属性10
   */
  public static final String CSALEINVOICEBID_VFREE10 = "csaleinvoicebid.vfree10";
  /**
   * 发票子实体.自定义项1
   */
  public static final String CSALEINVOICEBID_VBDEF1 = "csaleinvoicebid.vbdef1";
  /**
   * 发票子实体.自定义项2
   */
  public static final String CSALEINVOICEBID_VBDEF2 = "csaleinvoicebid.vbdef2";
  /**
   * 发票子实体.自定义项3
   */
  public static final String CSALEINVOICEBID_VBDEF3 = "csaleinvoicebid.vbdef3";
  /**
   * 发票子实体.自定义项4
   */
  public static final String CSALEINVOICEBID_VBDEF4 = "csaleinvoicebid.vbdef4";
  /**
   * 发票子实体.自定义项5
   */
  public static final String CSALEINVOICEBID_VBDEF5 = "csaleinvoicebid.vbdef5";
  /**
   * 发票子实体.自定义项6
   */
  public static final String CSALEINVOICEBID_VBDEF6 = "csaleinvoicebid.vbdef6";
  /**
   * 发票子实体.自定义项7
   */
  public static final String CSALEINVOICEBID_VBDEF7 = "csaleinvoicebid.vbdef7";
  /**
   * 发票子实体.自定义项8
   */
  public static final String CSALEINVOICEBID_VBDEF8 = "csaleinvoicebid.vbdef8";
  /**
   * 发票子实体.自定义项9
   */
  public static final String CSALEINVOICEBID_VBDEF9 = "csaleinvoicebid.vbdef9";
  /**
   * 发票子实体.自定义项10
   */
  public static final String CSALEINVOICEBID_VBDEF10 = "csaleinvoicebid.vbdef10";
  /**
   * 发票子实体.自定义项11
   */
  public static final String CSALEINVOICEBID_VBDEF11 = "csaleinvoicebid.vbdef11";
  /**
   * 发票子实体.自定义项12
   */
  public static final String CSALEINVOICEBID_VBDEF12 = "csaleinvoicebid.vbdef12";
  /**
   * 发票子实体.自定义项13
   */
  public static final String CSALEINVOICEBID_VBDEF13 = "csaleinvoicebid.vbdef13";
  /**
   * 发票子实体.自定义项14
   */
  public static final String CSALEINVOICEBID_VBDEF14 = "csaleinvoicebid.vbdef14";
  /**
   * 发票子实体.自定义项15
   */
  public static final String CSALEINVOICEBID_VBDEF15 = "csaleinvoicebid.vbdef15";
  /**
   * 发票子实体.自定义项16
   */
  public static final String CSALEINVOICEBID_VBDEF16 = "csaleinvoicebid.vbdef16";
  /**
   * 发票子实体.自定义项17
   */
  public static final String CSALEINVOICEBID_VBDEF17 = "csaleinvoicebid.vbdef17";
  /**
   * 发票子实体.自定义项18
   */
  public static final String CSALEINVOICEBID_VBDEF18 = "csaleinvoicebid.vbdef18";
  /**
   * 发票子实体.自定义项19
   */
  public static final String CSALEINVOICEBID_VBDEF19 = "csaleinvoicebid.vbdef19";
  /**
   * 发票子实体.自定义项20
   */
  public static final String CSALEINVOICEBID_VBDEF20 = "csaleinvoicebid.vbdef20";
  /**
   * 发票子实体.来源单据表头时间戳
   */
  public static final String CSALEINVOICEBID_SRCTS = "csaleinvoicebid.srcts";
  /**
   * 发票子实体.来源单据表体时间戳
   */
  public static final String CSALEINVOICEBID_SRCBTS = "csaleinvoicebid.srcbts";
  /**
   * 发票子实体.可出库数量
   */
  public static final String CSALEINVOICEBID_NCANOUTNUM = "csaleinvoicebid.ncanoutnum";
  /**
   * 发票子实体.来源单据数量
   */
  public static final String CSALEINVOICEBID_NSRCNUM = "csaleinvoicebid.nsrcnum";
  /**
   * 发票子实体.物料基本分类
   */
  public static final String CSALEINVOICEBID_CMARBASCALSSID = "csaleinvoicebid.cmarbascalssid";
  /**
   * 发票子实体.发货利润中心
   */
  public static final String CSALEINVOICEBID_CSPROFITCENTERVID = "csaleinvoicebid.csprofitcentervid";
  /**
   * 发票子实体.发货利润中心最新版本
   */
  public static final String CSALEINVOICEBID_CSPROFITCENTERID = "csaleinvoicebid.csprofitcenterid";
  /**
   * 发票子实体.特征码
   */
  public static final String CSALEINVOICEBID_CMFFILEID = "csaleinvoicebid.cmffileid";
  /**
   * 发票子实体.vostatus
   */
  public static final String CSALEINVOICEBID_STATUS = "csaleinvoicebid.status";
  /**
   * 发票子实体.dr
   */
  public static final String CSALEINVOICEBID_DR = "csaleinvoicebid.dr";
  /**
   * 发票子实体.ts
   */
  public static final String CSALEINVOICEBID_TS = "csaleinvoicebid.ts";
}


