package nc.pubitf.so.m30.api;

import java.io.Serializable;

/**
 * @description
 * 销售订单查询API条件构造常量类
 * @scene
 *
 * @param
 *
 *
 * @since 6.5
 * @version 2015-11-12 上午10:58:15
 * @author 刘景
 */
public interface ISaleOrderVO extends Serializable {
  
  /**
   * 销售订单主实体
   */
  public static final String CSALEORDERID = "csaleorderid";
  /**
   * 集团
   */
  public static final String PK_GROUP = "pk_group";
  /**
   * 销售组织
   */
  public static final String PK_ORG = "pk_org";
  /**
   * 销售组织版本
   */
  public static final String PK_ORG_V = "pk_org_v";
  /**
   * 订单类型
   */
  public static final String CTRANTYPEID = "ctrantypeid";
  /**
   * 订单类型编码
   */
  public static final String VTRANTYPECODE = "vtrantypecode";
  /**
   * 赠品兑付类型
   */
  public static final String CARSUBTYPEID = "carsubtypeid";
  /**
   * 业务流程
   */
  public static final String CBIZTYPEID = "cbiztypeid";
  /**
   * 单据号
   */
  public static final String VBILLCODE = "vbillcode";
  /**
   * 单据日期
   */
  public static final String DBILLDATE = "dbilldate";
  /**
   * 客户
   */
  public static final String CCUSTOMERID = "ccustomerid";
  /**
   * 是否散户
   */
  public static final String BFREECUSTFLAG = "bfreecustflag";
  /**
   * 散户
   */
  public static final String CFREECUSTID = "cfreecustid";
  /**
   * 部门
   */
  public static final String CDEPTVID = "cdeptvid";
  /**
   * 部门
   */
  public static final String CDEPTID = "cdeptid";
  /**
   * 业务员
   */
  public static final String CEMPLOYEEID = "cemployeeid";
  /**
   * 原币
   */
  public static final String CORIGCURRENCYID = "corigcurrencyid";
  /**
   * 开票客户
   */
  public static final String CINVOICECUSTID = "cinvoicecustid";
  /**
   * 开户银行
   */
  public static final String CCUSTBANKID = "ccustbankid";
  /**
   * 开户银行账户
   */
  public static final String CCUSTBANKACCID = "ccustbankaccid";
  /**
   * 销售渠道类型
   */
  public static final String CCHANNELTYPEID = "cchanneltypeid";
  /**
   * 收款协议
   */
  public static final String CPAYTERMID = "cpaytermid";
  /**
   * 运输方式
   */
  public static final String CTRANSPORTTYPEID = "ctransporttypeid";
  /**
   * 贸易术语
   */
  public static final String CTRADEWORDID = "ctradewordid";
  /**
   * 整单折扣
   */
  public static final String NDISCOUNTRATE = "ndiscountrate";
  /**
   * 信用证号
   */
  public static final String VCREDITNUM = "vcreditnum";
  /**
   * 结算方式
   */
  public static final String CBALANCETYPEID = "cbalancetypeid";
  /**
   * 代垫运费
   */
  public static final String BADVFEEFLAG = "badvfeeflag";
  /**
   * 订单收款比例
   */
  public static final String NPRECEIVERATE = "npreceiverate";
  /**
   * 订单收款限额
   */
  public static final String NPRECEIVEQUOTA = "npreceivequota";
  /**
   * 收款限额控制预收
   */
  public static final String BPRECEIVEFLAG = "bpreceiveflag";
  /**
   * 实际预收款金额
   */
  public static final String NPRECEIVEMNY = "npreceivemny";
  /**
   * 实际收款金额
   */
  public static final String NRECEIVEDMNY = "nreceivedmny";
  /**
   * 本次收款金额
   */
  public static final String NTHISRECEIVEMNY = "nthisreceivemny";
  /**
   * 总数量
   */
  public static final String NTOTALNUM = "ntotalnum";
  /**
   * 合计重量
   */
  public static final String NTOTALWEIGHT = "ntotalweight";
  /**
   * 合计体积
   */
  public static final String NTOTALVOLUME = "ntotalvolume";
  /**
   * 总件数
   */
  public static final String NTOTALPIECE = "ntotalpiece";
  /**
   * 价税合计
   */
  public static final String NTOTALORIGMNY = "ntotalorigmny";
  /**
   * 赠品价税合计
   */
  public static final String NLRGTOTALORIGMNY = "nlrgtotalorigmny";
  /**
   * 冲抵前金额
   */
  public static final String NTOTALMNY = "ntotalmny";
  /**
   * 费用冲抵金额
   */
  public static final String NTOTALORIGSUBMNY = "ntotalorigsubmny";
  /**
   * 是否冲抵
   */
  public static final String BOFFSETFLAG = "boffsetflag";
  /**
   * 对方订单号
   */
  public static final String VCOOPPOHCODE = "vcooppohcode";
  /**
   * 由采购订单协同生成
   */
  public static final String BPOCOOPTOMEFLAG = "bpocooptomeflag";
  /**
   * 下游目的单据主组织
   */
  public static final String DEST_PK_ORG = "dest_pk_org";
  /**
   * 已协同生成采购订单
   */
  public static final String BCOOPTOPOFLAG = "bcooptopoflag";
  /**
   * 单据状态
   */
  public static final String FSTATUSFLAG = "fstatusflag";
  /**
   * 审批流状态
   */
  public static final String FPFSTATUSFLAG = "fpfstatusflag";
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
   * 制单人
   */
  public static final String BILLMAKER = "billmaker";
  /**
   * 制单日期
   */
  public static final String DMAKEDATE = "dmakedate";
  /**
   * 创建人
   */
  public static final String CREATOR = "creator";
  /**
   * 创建时间
   */
  public static final String CREATIONTIME = "creationtime";
  /**
   * 修改人
   */
  public static final String MODIFIER = "modifier";
  /**
   * 修改时间
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
   * 修订人
   */
  public static final String CREVISERID = "creviserid";
  /**
   * 修订版本号
   */
  public static final String IVERSION = "iversion";
  /**
   * 修订理由
   */
  public static final String VREVISEREASON = "vrevisereason";
  /**
   * 修订时间
   */
  public static final String TREVISETIME = "trevisetime";
  /**
   * 发货关闭
   */
  public static final String BSENDENDFLAG = "bsendendflag";
  /**
   * 出库关闭
   */
  public static final String BOUTENDFLAG = "boutendflag";
  /**
   * 开票关闭
   */
  public static final String BINVOICENDFLAG = "binvoicendflag";
  /**
   * 成本结算关闭
   */
  public static final String BCOSTSETTLEFLAG = "bcostsettleflag";
  /**
   * 收入结算关闭
   */
  public static final String BARSETTLEFLAG = "barsettleflag";
  /**
   * 打印次数
   */
  public static final String IPRINTCOUNT = "iprintcount";
  /**
   * 整单来源单据类型
   */
  public static final String VBILLSRCTYPE = "vbillsrctype";
  /**
   * 整单来源单据ID
   */
  public static final String CBILLSRCID = "cbillsrcid";
  /**
   * 收货客户
   */
  public static final String CHRECEIVECUSTID = "chreceivecustid";
  /**
   * 收货地址
   */
  public static final String CHRECEIVEADDID = "chreceiveaddid";
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
   * 销售订单子实体.销售订单子实体
   */
  public static final String SO_SALEORDER_B_CSALEORDERBID = "so_saleorder_b.csaleorderbid";
  /**
   * 销售订单子实体.集团
   */
  public static final String SO_SALEORDER_B_PK_GROUP = "so_saleorder_b.pk_group";
  /**
   * 销售订单子实体.销售组织
   */
  public static final String SO_SALEORDER_B_PK_ORG = "so_saleorder_b.pk_org";
  /**
   * 销售订单子实体.单据日期
   */
  public static final String SO_SALEORDER_B_DBILLDATE = "so_saleorder_b.dbilldate";
  /**
   * 销售订单子实体.行号
   */
  public static final String SO_SALEORDER_B_CROWNO = "so_saleorder_b.crowno";
  /**
   * 销售订单子实体.客户物料码
   */
  public static final String SO_SALEORDER_B_CCUSTMATERIALID = "so_saleorder_b.ccustmaterialid";
  /**
   * 销售订单子实体.物料编码
   */
  public static final String SO_SALEORDER_B_CMATERIALVID = "so_saleorder_b.cmaterialvid";
  /**
   * 销售订单子实体.物料
   */
  public static final String SO_SALEORDER_B_CMATERIALID = "so_saleorder_b.cmaterialid";
  /**
   * 销售订单子实体.供应商
   */
  public static final String SO_SALEORDER_B_CVENDORID = "so_saleorder_b.cvendorid";
  /**
   * 销售订单子实体.项目
   */
  public static final String SO_SALEORDER_B_CPROJECTID = "so_saleorder_b.cprojectid";
  /**
   * 销售订单子实体.生产厂商
   */
  public static final String SO_SALEORDER_B_CPRODUCTORID = "so_saleorder_b.cproductorid";
  /**
   * 销售订单子实体.工厂
   */
  public static final String SO_SALEORDER_B_CFACTORYID = "so_saleorder_b.cfactoryid";
  /**
   * 销售订单子实体.质量等级
   */
  public static final String SO_SALEORDER_B_CQUALITYLEVELID = "so_saleorder_b.cqualitylevelid";
  /**
   * 销售订单子实体.原产国
   */
  public static final String SO_SALEORDER_B_CORIGCOUNTRYID = "so_saleorder_b.corigcountryid";
  /**
   * 销售订单子实体.原产地区
   */
  public static final String SO_SALEORDER_B_CORIGAREAID = "so_saleorder_b.corigareaid";
  /**
   * 销售订单子实体.主单位
   */
  public static final String SO_SALEORDER_B_CUNITID = "so_saleorder_b.cunitid";
  /**
   * 销售订单子实体.单位
   */
  public static final String SO_SALEORDER_B_CASTUNITID = "so_saleorder_b.castunitid";
  /**
   * 销售订单子实体.主数量
   */
  public static final String SO_SALEORDER_B_NNUM = "so_saleorder_b.nnum";
  /**
   * 销售订单子实体.数量
   */
  public static final String SO_SALEORDER_B_NASTNUM = "so_saleorder_b.nastnum";
  /**
   * 销售订单子实体.换算率
   */
  public static final String SO_SALEORDER_B_VCHANGERATE = "so_saleorder_b.vchangerate";
  /**
   * 销售订单子实体.报价单位
   */
  public static final String SO_SALEORDER_B_CQTUNITID = "so_saleorder_b.cqtunitid";
  /**
   * 销售订单子实体.报价单位数量
   */
  public static final String SO_SALEORDER_B_NQTUNITNUM = "so_saleorder_b.nqtunitnum";
  /**
   * 销售订单子实体.报价换算率
   */
  public static final String SO_SALEORDER_B_VQTUNITRATE = "so_saleorder_b.vqtunitrate";
  /**
   * 销售订单子实体.整单折扣
   */
  public static final String SO_SALEORDER_B_NDISCOUNTRATE = "so_saleorder_b.ndiscountrate";
  /**
   * 销售订单子实体.单品折扣
   */
  public static final String SO_SALEORDER_B_NITEMDISCOUNTRATE = "so_saleorder_b.nitemdiscountrate";
  /**
   * 销售订单子实体.税码
   */
  public static final String SO_SALEORDER_B_CTAXCODEID = "so_saleorder_b.ctaxcodeid";
  /**
   * 销售订单子实体.税率
   */
  public static final String SO_SALEORDER_B_NTAXRATE = "so_saleorder_b.ntaxrate";
  /**
   * 销售订单子实体.扣税类别
   */
  public static final String SO_SALEORDER_B_FTAXTYPEFLAG = "so_saleorder_b.ftaxtypeflag";
  /**
   * 销售订单子实体.本位币
   */
  public static final String SO_SALEORDER_B_CCURRENCYID = "so_saleorder_b.ccurrencyid";
  /**
   * 销售订单子实体.折本汇率
   */
  public static final String SO_SALEORDER_B_NEXCHANGERATE = "so_saleorder_b.nexchangerate";
  /**
   * 销售订单子实体.含税单价
   */
  public static final String SO_SALEORDER_B_NQTORIGTAXPRICE = "so_saleorder_b.nqtorigtaxprice";
  /**
   * 销售订单子实体.无税单价
   */
  public static final String SO_SALEORDER_B_NQTORIGPRICE = "so_saleorder_b.nqtorigprice";
  /**
   * 销售订单子实体.含税净价
   */
  public static final String SO_SALEORDER_B_NQTORIGTAXNETPRC = "so_saleorder_b.nqtorigtaxnetprc";
  /**
   * 销售订单子实体.无税净价
   */
  public static final String SO_SALEORDER_B_NQTORIGNETPRICE = "so_saleorder_b.nqtorignetprice";
  /**
   * 销售订单子实体.主含税单价
   */
  public static final String SO_SALEORDER_B_NORIGTAXPRICE = "so_saleorder_b.norigtaxprice";
  /**
   * 销售订单子实体.主无税单价
   */
  public static final String SO_SALEORDER_B_NORIGPRICE = "so_saleorder_b.norigprice";
  /**
   * 销售订单子实体.主含税净价
   */
  public static final String SO_SALEORDER_B_NORIGTAXNETPRICE = "so_saleorder_b.norigtaxnetprice";
  /**
   * 销售订单子实体.主无税净价
   */
  public static final String SO_SALEORDER_B_NORIGNETPRICE = "so_saleorder_b.norignetprice";
  /**
   * 销售订单子实体.税额
   */
  public static final String SO_SALEORDER_B_NTAX = "so_saleorder_b.ntax";
  /**
   * 销售订单子实体.计税金额
   */
  public static final String SO_SALEORDER_B_NCALTAXMNY = "so_saleorder_b.ncaltaxmny";
  /**
   * 销售订单子实体.无税金额
   */
  public static final String SO_SALEORDER_B_NORIGMNY = "so_saleorder_b.norigmny";
  /**
   * 销售订单子实体.价税合计
   */
  public static final String SO_SALEORDER_B_NORIGTAXMNY = "so_saleorder_b.norigtaxmny";
  /**
   * 销售订单子实体.折扣额
   */
  public static final String SO_SALEORDER_B_NORIGDISCOUNT = "so_saleorder_b.norigdiscount";
  /**
   * 销售订单子实体.冲抵前金额
   */
  public static final String SO_SALEORDER_B_NBFORIGSUBMNY = "so_saleorder_b.nbforigsubmny";
  /**
   * 销售订单子实体.本币含税单价
   */
  public static final String SO_SALEORDER_B_NQTTAXPRICE = "so_saleorder_b.nqttaxprice";
  /**
   * 销售订单子实体.本币无税单价
   */
  public static final String SO_SALEORDER_B_NQTPRICE = "so_saleorder_b.nqtprice";
  /**
   * 销售订单子实体.本币含税净价
   */
  public static final String SO_SALEORDER_B_NQTTAXNETPRICE = "so_saleorder_b.nqttaxnetprice";
  /**
   * 销售订单子实体.本币无税净价
   */
  public static final String SO_SALEORDER_B_NQTNETPRICE = "so_saleorder_b.nqtnetprice";
  /**
   * 销售订单子实体.主本币含税单价
   */
  public static final String SO_SALEORDER_B_NTAXPRICE = "so_saleorder_b.ntaxprice";
  /**
   * 销售订单子实体.主本币无税单价
   */
  public static final String SO_SALEORDER_B_NPRICE = "so_saleorder_b.nprice";
  /**
   * 销售订单子实体.主本币含税净价
   */
  public static final String SO_SALEORDER_B_NTAXNETPRICE = "so_saleorder_b.ntaxnetprice";
  /**
   * 销售订单子实体.主本币无税净价
   */
  public static final String SO_SALEORDER_B_NNETPRICE = "so_saleorder_b.nnetprice";
  /**
   * 销售订单子实体.本币无税金额
   */
  public static final String SO_SALEORDER_B_NMNY = "so_saleorder_b.nmny";
  /**
   * 销售订单子实体.本币价税合计
   */
  public static final String SO_SALEORDER_B_NTAXMNY = "so_saleorder_b.ntaxmny";
  /**
   * 销售订单子实体.本币折扣额
   */
  public static final String SO_SALEORDER_B_NDISCOUNT = "so_saleorder_b.ndiscount";
  /**
   * 销售订单子实体.集团本位币汇率
   */
  public static final String SO_SALEORDER_B_NGROUPEXCHGRATE = "so_saleorder_b.ngroupexchgrate";
  /**
   * 销售订单子实体.集团本币无税金额
   */
  public static final String SO_SALEORDER_B_NGROUPMNY = "so_saleorder_b.ngroupmny";
  /**
   * 销售订单子实体.集团本币价税合计
   */
  public static final String SO_SALEORDER_B_NGROUPTAXMNY = "so_saleorder_b.ngrouptaxmny";
  /**
   * 销售订单子实体.全局本位币汇率
   */
  public static final String SO_SALEORDER_B_NGLOBALEXCHGRATE = "so_saleorder_b.nglobalexchgrate";
  /**
   * 销售订单子实体.全局本币无税金额
   */
  public static final String SO_SALEORDER_B_NGLOBALMNY = "so_saleorder_b.nglobalmny";
  /**
   * 销售订单子实体.全局本币价税合计
   */
  public static final String SO_SALEORDER_B_NGLOBALTAXMNY = "so_saleorder_b.nglobaltaxmny";
  /**
   * 销售订单子实体.询价原币含税单价
   */
  public static final String SO_SALEORDER_B_NASKQTORIGTAXPRC = "so_saleorder_b.naskqtorigtaxprc";
  /**
   * 销售订单子实体.询价原币无税单价
   */
  public static final String SO_SALEORDER_B_NASKQTORIGPRICE = "so_saleorder_b.naskqtorigprice";
  /**
   * 销售订单子实体.询价原币含税净价
   */
  public static final String SO_SALEORDER_B_NASKQTORIGTXNTPRC = "so_saleorder_b.naskqtorigtxntprc";
  /**
   * 销售订单子实体.询价原币无税净价
   */
  public static final String SO_SALEORDER_B_NASKQTORIGNETPRICE = "so_saleorder_b.naskqtorignetprice";
  /**
   * 销售订单子实体.重量
   */
  public static final String SO_SALEORDER_B_NWEIGHT = "so_saleorder_b.nweight";
  /**
   * 销售订单子实体.体积
   */
  public static final String SO_SALEORDER_B_NVOLUME = "so_saleorder_b.nvolume";
  /**
   * 销售订单子实体.件数
   */
  public static final String SO_SALEORDER_B_NPIECE = "so_saleorder_b.npiece";
  /**
   * 销售订单子实体.产品线
   */
  public static final String SO_SALEORDER_B_CPRODLINEID = "so_saleorder_b.cprodlineid";
  /**
   * 销售订单子实体.批次号
   */
  public static final String SO_SALEORDER_B_VBATCHCODE = "so_saleorder_b.vbatchcode";
  /**
   * 销售订单子实体.批次档案
   */
  public static final String SO_SALEORDER_B_PK_BATCHCODE = "so_saleorder_b.pk_batchcode";
  /**
   * 销售订单子实体.服务类
   */
  public static final String SO_SALEORDER_B_BLABORFLAG = "so_saleorder_b.blaborflag";
  /**
   * 销售订单子实体.折扣类
   */
  public static final String SO_SALEORDER_B_BDISCOUNTFLAG = "so_saleorder_b.bdiscountflag";
  /**
   * 销售订单子实体.赠品
   */
  public static final String SO_SALEORDER_B_BLARGESSFLAG = "so_saleorder_b.blargessflag";
  /**
   * 销售订单子实体.捆绑存货
   */
  public static final String SO_SALEORDER_B_BBINDFLAG = "so_saleorder_b.bbindflag";
  /**
   * 销售订单子实体.发货日期
   */
  public static final String SO_SALEORDER_B_DSENDDATE = "so_saleorder_b.dsenddate";
  /**
   * 销售订单子实体.到货日期
   */
  public static final String SO_SALEORDER_B_DRECEIVEDATE = "so_saleorder_b.dreceivedate";
  /**
   * 销售订单子实体.收货客户
   */
  public static final String SO_SALEORDER_B_CRECEIVECUSTID = "so_saleorder_b.creceivecustid";
  /**
   * 销售订单子实体.收货地区
   */
  public static final String SO_SALEORDER_B_CRECEIVEAREAID = "so_saleorder_b.creceiveareaid";
  /**
   * 销售订单子实体.收货地址
   */
  public static final String SO_SALEORDER_B_CRECEIVEADDRID = "so_saleorder_b.creceiveaddrid";
  /**
   * 销售订单子实体.收货地点
   */
  public static final String SO_SALEORDER_B_CRECEIVEADDDOCID = "so_saleorder_b.creceiveadddocid";
  /**
   * 销售订单子实体.发货库存组织
   */
  public static final String SO_SALEORDER_B_CSENDSTOCKORGVID = "so_saleorder_b.csendstockorgvid";
  /**
   * 销售订单子实体.发货库存组织最新版本
   */
  public static final String SO_SALEORDER_B_CSENDSTOCKORGID = "so_saleorder_b.csendstockorgid";
  /**
   * 销售订单子实体.发货仓库
   */
  public static final String SO_SALEORDER_B_CSENDSTORDOCID = "so_saleorder_b.csendstordocid";
  /**
   * 销售订单子实体.物流组织
   */
  public static final String SO_SALEORDER_B_CTRAFFICORGVID = "so_saleorder_b.ctrafficorgvid";
  /**
   * 销售订单子实体.物流组织
   */
  public static final String SO_SALEORDER_B_CTRAFFICORGID = "so_saleorder_b.ctrafficorgid";
  /**
   * 销售订单子实体.结算财务组织
   */
  public static final String SO_SALEORDER_B_CSETTLEORGVID = "so_saleorder_b.csettleorgvid";
  /**
   * 销售订单子实体.结算财务组织
   */
  public static final String SO_SALEORDER_B_CSETTLEORGID = "so_saleorder_b.csettleorgid";
  /**
   * 销售订单子实体.收货国家/地区
   */
  public static final String SO_SALEORDER_B_CRECECOUNTRYID = "so_saleorder_b.crececountryid";
  /**
   * 销售订单子实体.发货国家/地区
   */
  public static final String SO_SALEORDER_B_CSENDCOUNTRYID = "so_saleorder_b.csendcountryid";
  /**
   * 销售订单子实体.报税国家/地区
   */
  public static final String SO_SALEORDER_B_CTAXCOUNTRYID = "so_saleorder_b.ctaxcountryid";
  /**
   * 销售订单子实体.购销类型
   */
  public static final String SO_SALEORDER_B_FBUYSELLFLAG = "so_saleorder_b.fbuysellflag";
  /**
   * 销售订单子实体.三角贸易
   */
  public static final String SO_SALEORDER_B_BTRIATRADEFLAG = "so_saleorder_b.btriatradeflag";
  /**
   * 销售订单子实体.应收组织
   */
  public static final String SO_SALEORDER_B_CARORGVID = "so_saleorder_b.carorgvid";
  /**
   * 销售订单子实体.应收组织最新版本
   */
  public static final String SO_SALEORDER_B_CARORGID = "so_saleorder_b.carorgid";
  /**
   * 销售订单子实体.结算利润中心
   */
  public static final String SO_SALEORDER_B_CPROFITCENTERVID = "so_saleorder_b.cprofitcentervid";
  /**
   * 销售订单子实体.结算利润中心最新版本
   */
  public static final String SO_SALEORDER_B_CPROFITCENTERID = "so_saleorder_b.cprofitcenterid";
  /**
   * 销售订单子实体.修订理由
   */
  public static final String SO_SALEORDER_B_VBREVISEREASON = "so_saleorder_b.vbrevisereason";
  /**
   * 销售订单子实体.行状态
   */
  public static final String SO_SALEORDER_B_FROWSTATUS = "so_saleorder_b.frowstatus";
  /**
   * 销售订单子实体.行备注
   */
  public static final String SO_SALEORDER_B_VROWNOTE = "so_saleorder_b.vrownote";
  /**
   * 销售订单子实体.价格组成
   */
  public static final String SO_SALEORDER_B_CPRICEFORMID = "so_saleorder_b.cpriceformid";
  /**
   * 销售订单子实体.价格政策
   */
  public static final String SO_SALEORDER_B_CPRICEPOLICYID = "so_saleorder_b.cpricepolicyid";
  /**
   * 销售订单子实体.价格项目
   */
  public static final String SO_SALEORDER_B_CPRICEITEMID = "so_saleorder_b.cpriceitemid";
  /**
   * 销售订单子实体.价目表
   */
  public static final String SO_SALEORDER_B_CPRICEITEMTABLEID = "so_saleorder_b.cpriceitemtableid";
  /**
   * 销售订单子实体.借出转销售
   */
  public static final String SO_SALEORDER_B_BJCZXSFLAG = "so_saleorder_b.bjczxsflag";
  /**
   * 销售订单子实体.合同类型
   */
  public static final String SO_SALEORDER_B_VCTTYPE = "so_saleorder_b.vcttype";
  /**
   * 销售订单子实体.销售合同号
   */
  public static final String SO_SALEORDER_B_VCTCODE = "so_saleorder_b.vctcode";
  /**
   * 销售订单子实体.合同主表
   */
  public static final String SO_SALEORDER_B_CCTMANAGEID = "so_saleorder_b.cctmanageid";
  /**
   * 销售订单子实体.合同子表
   */
  public static final String SO_SALEORDER_B_CCTMANAGEBID = "so_saleorder_b.cctmanagebid";
  /**
   * 销售订单子实体.来源单据类型
   */
  public static final String SO_SALEORDER_B_VSRCTYPE = "so_saleorder_b.vsrctype";
  /**
   * 销售订单子实体.来源交易类型
   */
  public static final String SO_SALEORDER_B_VSRCTRANTYPE = "so_saleorder_b.vsrctrantype";
  /**
   * 销售订单子实体.来源单据号
   */
  public static final String SO_SALEORDER_B_VSRCCODE = "so_saleorder_b.vsrccode";
  /**
   * 销售订单子实体.来源单据行号
   */
  public static final String SO_SALEORDER_B_VSRCROWNO = "so_saleorder_b.vsrcrowno";
  /**
   * 销售订单子实体.来源单据主表
   */
  public static final String SO_SALEORDER_B_CSRCID = "so_saleorder_b.csrcid";
  /**
   * 销售订单子实体.来源单据附表
   */
  public static final String SO_SALEORDER_B_CSRCBID = "so_saleorder_b.csrcbid";
  /**
   * 销售订单子实体.源头单据类型
   */
  public static final String SO_SALEORDER_B_VFIRSTTYPE = "so_saleorder_b.vfirsttype";
  /**
   * 销售订单子实体.源头交易类型
   */
  public static final String SO_SALEORDER_B_VFIRSTTRANTYPE = "so_saleorder_b.vfirsttrantype";
  /**
   * 销售订单子实体.源头单据号
   */
  public static final String SO_SALEORDER_B_VFIRSTCODE = "so_saleorder_b.vfirstcode";
  /**
   * 销售订单子实体.源头单据主表
   */
  public static final String SO_SALEORDER_B_CFIRSTID = "so_saleorder_b.cfirstid";
  /**
   * 销售订单子实体.源头单据子表
   */
  public static final String SO_SALEORDER_B_CFIRSTBID = "so_saleorder_b.cfirstbid";
  /**
   * 销售订单子实体.源头单据行号
   */
  public static final String SO_SALEORDER_B_VFIRSTROWNO = "so_saleorder_b.vfirstrowno";
  /**
   * 销售订单子实体.退货原因
   */
  public static final String SO_SALEORDER_B_CRETREASONID = "so_saleorder_b.cretreasonid";
  /**
   * 销售订单子实体.退货政策
   */
  public static final String SO_SALEORDER_B_CRETPOLICYID = "so_saleorder_b.cretpolicyid";
  /**
   * 销售订单子实体.退货责任处理方式
   */
  public static final String SO_SALEORDER_B_VRETURNMODE = "so_saleorder_b.vreturnmode";
  /**
   * 销售订单子实体.退换货标记
   */
  public static final String SO_SALEORDER_B_FRETEXCHANGE = "so_saleorder_b.fretexchange";
  /**
   * 销售订单子实体.换货行对应退货行
   */
  public static final String SO_SALEORDER_B_CEXCHANGESRCRETID = "so_saleorder_b.cexchangesrcretid";
  /**
   * 销售订单子实体.赠品行对应来源订单行ID
   */
  public static final String SO_SALEORDER_B_CLARGESSSRCID = "so_saleorder_b.clargesssrcid";
  /**
   * 销售订单子实体.捆绑件对应来源订单行ID
   */
  public static final String SO_SALEORDER_B_CBINDSRCID = "so_saleorder_b.cbindsrcid";
  /**
   * 销售订单子实体.赠品价格分摊方式
   */
  public static final String SO_SALEORDER_B_FLARGESSTYPEFLAG = "so_saleorder_b.flargesstypeflag";
  /**
   * 销售订单子实体.赠品价格分摊前无税金额
   */
  public static final String SO_SALEORDER_B_NLARGESSMNY = "so_saleorder_b.nlargessmny";
  /**
   * 销售订单子实体.赠品价格分摊前价税合计
   */
  public static final String SO_SALEORDER_B_NLARGESSTAXMNY = "so_saleorder_b.nlargesstaxmny";
  /**
   * 销售订单子实体.预订单行关闭
   */
  public static final String SO_SALEORDER_B_BPREROWCLOSEFLAG = "so_saleorder_b.bprerowcloseflag";
  /**
   * 销售订单子实体.自由辅助属性1
   */
  public static final String SO_SALEORDER_B_VFREE1 = "so_saleorder_b.vfree1";
  /**
   * 销售订单子实体.自由辅助属性2
   */
  public static final String SO_SALEORDER_B_VFREE2 = "so_saleorder_b.vfree2";
  /**
   * 销售订单子实体.自由辅助属性3
   */
  public static final String SO_SALEORDER_B_VFREE3 = "so_saleorder_b.vfree3";
  /**
   * 销售订单子实体.自由辅助属性4
   */
  public static final String SO_SALEORDER_B_VFREE4 = "so_saleorder_b.vfree4";
  /**
   * 销售订单子实体.自由辅助属性5
   */
  public static final String SO_SALEORDER_B_VFREE5 = "so_saleorder_b.vfree5";
  /**
   * 销售订单子实体.自由辅助属性6
   */
  public static final String SO_SALEORDER_B_VFREE6 = "so_saleorder_b.vfree6";
  /**
   * 销售订单子实体.自由辅助属性7
   */
  public static final String SO_SALEORDER_B_VFREE7 = "so_saleorder_b.vfree7";
  /**
   * 销售订单子实体.自由辅助属性8
   */
  public static final String SO_SALEORDER_B_VFREE8 = "so_saleorder_b.vfree8";
  /**
   * 销售订单子实体.自由辅助属性9
   */
  public static final String SO_SALEORDER_B_VFREE9 = "so_saleorder_b.vfree9";
  /**
   * 销售订单子实体.自由辅助属性10
   */
  public static final String SO_SALEORDER_B_VFREE10 = "so_saleorder_b.vfree10";
  /**
   * 销售订单子实体.自定义项1
   */
  public static final String SO_SALEORDER_B_VBDEF1 = "so_saleorder_b.vbdef1";
  /**
   * 销售订单子实体.自定义项2
   */
  public static final String SO_SALEORDER_B_VBDEF2 = "so_saleorder_b.vbdef2";
  /**
   * 销售订单子实体.自定义项3
   */
  public static final String SO_SALEORDER_B_VBDEF3 = "so_saleorder_b.vbdef3";
  /**
   * 销售订单子实体.自定义项4
   */
  public static final String SO_SALEORDER_B_VBDEF4 = "so_saleorder_b.vbdef4";
  /**
   * 销售订单子实体.自定义项5
   */
  public static final String SO_SALEORDER_B_VBDEF5 = "so_saleorder_b.vbdef5";
  /**
   * 销售订单子实体.自定义项6
   */
  public static final String SO_SALEORDER_B_VBDEF6 = "so_saleorder_b.vbdef6";
  /**
   * 销售订单子实体.自定义项7
   */
  public static final String SO_SALEORDER_B_VBDEF7 = "so_saleorder_b.vbdef7";
  /**
   * 销售订单子实体.自定义项8
   */
  public static final String SO_SALEORDER_B_VBDEF8 = "so_saleorder_b.vbdef8";
  /**
   * 销售订单子实体.自定义项9
   */
  public static final String SO_SALEORDER_B_VBDEF9 = "so_saleorder_b.vbdef9";
  /**
   * 销售订单子实体.自定义项10
   */
  public static final String SO_SALEORDER_B_VBDEF10 = "so_saleorder_b.vbdef10";
  /**
   * 销售订单子实体.自定义项11
   */
  public static final String SO_SALEORDER_B_VBDEF11 = "so_saleorder_b.vbdef11";
  /**
   * 销售订单子实体.自定义项12
   */
  public static final String SO_SALEORDER_B_VBDEF12 = "so_saleorder_b.vbdef12";
  /**
   * 销售订单子实体.自定义项13
   */
  public static final String SO_SALEORDER_B_VBDEF13 = "so_saleorder_b.vbdef13";
  /**
   * 销售订单子实体.自定义项14
   */
  public static final String SO_SALEORDER_B_VBDEF14 = "so_saleorder_b.vbdef14";
  /**
   * 销售订单子实体.自定义项15
   */
  public static final String SO_SALEORDER_B_VBDEF15 = "so_saleorder_b.vbdef15";
  /**
   * 销售订单子实体.自定义项16
   */
  public static final String SO_SALEORDER_B_VBDEF16 = "so_saleorder_b.vbdef16";
  /**
   * 销售订单子实体.自定义项17
   */
  public static final String SO_SALEORDER_B_VBDEF17 = "so_saleorder_b.vbdef17";
  /**
   * 销售订单子实体.自定义项18
   */
  public static final String SO_SALEORDER_B_VBDEF18 = "so_saleorder_b.vbdef18";
  /**
   * 销售订单子实体.自定义项19
   */
  public static final String SO_SALEORDER_B_VBDEF19 = "so_saleorder_b.vbdef19";
  /**
   * 销售订单子实体.自定义项20
   */
  public static final String SO_SALEORDER_B_VBDEF20 = "so_saleorder_b.vbdef20";
  /**
   * 销售订单子实体.发货关闭
   */
  public static final String SO_SALEORDER_B_BBSENDENDFLAG = "so_saleorder_b.bbsendendflag";
  /**
   * 销售订单子实体.开票关闭
   */
  public static final String SO_SALEORDER_B_BBINVOICENDFLAG = "so_saleorder_b.bbinvoicendflag";
  /**
   * 销售订单子实体.出库关闭
   */
  public static final String SO_SALEORDER_B_BBOUTENDFLAG = "so_saleorder_b.bboutendflag";
  /**
   * 销售订单子实体.成本结算关闭
   */
  public static final String SO_SALEORDER_B_BBCOSTSETTLEFLAG = "so_saleorder_b.bbcostsettleflag";
  /**
   * 销售订单子实体.收入结算关闭
   */
  public static final String SO_SALEORDER_B_BBARSETTLEFLAG = "so_saleorder_b.bbarsettleflag";
  /**
   * 销售订单子实体.关闭/打开原因
   */
  public static final String SO_SALEORDER_B_VCLOSEREASON = "so_saleorder_b.vclosereason";
  /**
   * 销售订单子实体.发货审批主数量
   */
  public static final String SO_SALEORDER_B_NSENDAUDITNUM = "so_saleorder_b.nsendauditnum";
  /**
   * 销售订单子实体.出库审批主数量
   */
  public static final String SO_SALEORDER_B_NOUTAUDITNUM = "so_saleorder_b.noutauditnum";
  /**
   * 销售订单子实体.发票审批主数量
   */
  public static final String SO_SALEORDER_B_NINVOICEAUDITNUM = "so_saleorder_b.ninvoiceauditnum";
  /**
   * 销售订单子实体.出库未签字主数量
   */
  public static final String SO_SALEORDER_B_NOUTNOTAUDITNUM = "so_saleorder_b.noutnotauditnum";
  /**
   * 销售订单子实体.途损单未审核主数量
   */
  public static final String SO_SALEORDER_B_NLOSSNOTAUDITNUM = "so_saleorder_b.nlossnotauditnum";
  /**
   * 销售订单子实体.累计发货主数量
   */
  public static final String SO_SALEORDER_B_NTOTALSENDNUM = "so_saleorder_b.ntotalsendnum";
  /**
   * 销售订单子实体.累计开票主数量
   */
  public static final String SO_SALEORDER_B_NTOTALINVOICENUM = "so_saleorder_b.ntotalinvoicenum";
  /**
   * 销售订单子实体.累计出库主数量
   */
  public static final String SO_SALEORDER_B_NTOTALOUTNUM = "so_saleorder_b.ntotaloutnum";
  /**
   * 销售订单子实体.累计应发未出库主数量
   */
  public static final String SO_SALEORDER_B_NTOTALNOTOUTNUM = "so_saleorder_b.ntotalnotoutnum";
  /**
   * 销售订单子实体.累计签收主数量
   */
  public static final String SO_SALEORDER_B_NTOTALSIGNNUM = "so_saleorder_b.ntotalsignnum";
  /**
   * 销售订单子实体.累计途损主数量
   */
  public static final String SO_SALEORDER_B_NTRANSLOSSNUM = "so_saleorder_b.ntranslossnum";
  /**
   * 销售订单子实体.累计出库对冲主数量
   */
  public static final String SO_SALEORDER_B_NTOTALRUSHNUM = "so_saleorder_b.ntotalrushnum";
  /**
   * 销售订单子实体.累计暂估应收主数量
   */
  public static final String SO_SALEORDER_B_NTOTALESTARNUM = "so_saleorder_b.ntotalestarnum";
  /**
   * 销售订单子实体.累计确认应收主数量
   */
  public static final String SO_SALEORDER_B_NTOTALARNUM = "so_saleorder_b.ntotalarnum";
  /**
   * 销售订单子实体.累计成本结算主数量
   */
  public static final String SO_SALEORDER_B_NTOTALCOSTNUM = "so_saleorder_b.ntotalcostnum";
  /**
   * 销售订单子实体.累计暂估应收金额
   */
  public static final String SO_SALEORDER_B_NTOTALESTARMNY = "so_saleorder_b.ntotalestarmny";
  /**
   * 销售订单子实体.累计确认应收金额
   */
  public static final String SO_SALEORDER_B_NTOTALARMNY = "so_saleorder_b.ntotalarmny";
  /**
   * 销售订单子实体.累计财务核销金额
   */
  public static final String SO_SALEORDER_B_NTOTALPAYMNY = "so_saleorder_b.ntotalpaymny";
  /**
   * 销售订单子实体.累计冲抵金额
   */
  public static final String SO_SALEORDER_B_NORIGSUBMNY = "so_saleorder_b.norigsubmny";
  /**
   * 销售订单子实体.累计安排委外订单主数量
   */
  public static final String SO_SALEORDER_B_NARRANGESCORNUM = "so_saleorder_b.narrangescornum";
  /**
   * 销售订单子实体.累计安排请购单主数量
   */
  public static final String SO_SALEORDER_B_NARRANGEPOAPPNUM = "so_saleorder_b.narrangepoappnum";
  /**
   * 销售订单子实体.累计安排调拨订单主数量
   */
  public static final String SO_SALEORDER_B_NARRANGETOORNUM = "so_saleorder_b.narrangetoornum";
  /**
   * 销售订单子实体.累计安排调拨申请主数量
   */
  public static final String SO_SALEORDER_B_NARRANGETOAPPNUM = "so_saleorder_b.narrangetoappnum";
  /**
   * 销售订单子实体.累计安排生产订单主数量
   */
  public static final String SO_SALEORDER_B_NARRANGEMONUM = "so_saleorder_b.narrangemonum";
  /**
   * 销售订单子实体.累计安排采购订单主数量
   */
  public static final String SO_SALEORDER_B_NARRANGEPONUM = "so_saleorder_b.narrangeponum";
  /**
   * 销售订单子实体.累计生成计划订单主数量
   */
  public static final String SO_SALEORDER_B_NTOTALPLONUM = "so_saleorder_b.ntotalplonum";
  /**
   * 销售订单子实体.预留主数量
   */
  public static final String SO_SALEORDER_B_NREQRSNUM = "so_saleorder_b.nreqrsnum";
  /**
   * 销售订单子实体.累计退货主数量
   */
  public static final String SO_SALEORDER_B_NTOTALRETURNNUM = "so_saleorder_b.ntotalreturnnum";
  /**
   * 销售订单子实体.累计发出商品主数量
   */
  public static final String SO_SALEORDER_B_NTOTALTRADENUM = "so_saleorder_b.ntotaltradenum";
  /**
   * 销售订单子实体.是否货源安排完毕
   */
  public static final String SO_SALEORDER_B_BARRANGEDFLAG = "so_saleorder_b.barrangedflag";
  /**
   * 销售订单子实体.最后货源安排人
   */
  public static final String SO_SALEORDER_B_CARRANGEPERSONID = "so_saleorder_b.carrangepersonid";
  /**
   * 销售订单子实体.最后货源安排时间
   */
  public static final String SO_SALEORDER_B_TLASTARRANGETIME = "so_saleorder_b.tlastarrangetime";
  /**
   * 销售订单子实体.发货未完成量
   */
  public static final String SO_SALEORDER_B_NSENDUNFINISEDNUM = "so_saleorder_b.nsendunfinisednum";
  /**
   * 销售订单子实体.出库未完成量
   */
  public static final String SO_SALEORDER_B_NOUTUNFINISEDNUM = "so_saleorder_b.noutunfinisednum";
  /**
   * 销售订单子实体.发票未完成量
   */
  public static final String SO_SALEORDER_B_NINVUNFINISEDNUM = "so_saleorder_b.ninvunfinisednum";
  /**
   * 销售订单子实体.未传确认应收主数量
   */
  public static final String SO_SALEORDER_B_NNOTARNUM = "so_saleorder_b.nnotarnum";
  /**
   * 销售订单子实体.未传存货核算主数量
   */
  public static final String SO_SALEORDER_B_NNOTCOSTNUM = "so_saleorder_b.nnotcostnum";
  /**
   * 销售订单子实体.结算关闭
   */
  public static final String SO_SALEORDER_B_BBSETTLEENDFLAG = "so_saleorder_b.bbsettleendflag";
  /**
   * 销售订单子实体.来源单据表头时间戳
   */
  public static final String SO_SALEORDER_B_SRCTS = "so_saleorder_b.srcts";
  /**
   * 销售订单子实体.来源单据表体时间戳
   */
  public static final String SO_SALEORDER_B_SRCBTS = "so_saleorder_b.srcbts";
  /**
   * 销售订单子实体.来源组织
   */
  public static final String SO_SALEORDER_B_SRCORGID = "so_saleorder_b.srcorgid";
  /**
   * 销售订单子实体.买赠促销类型
   */
  public static final String SO_SALEORDER_B_CBUYPROMOTTYPEID = "so_saleorder_b.cbuypromottypeid";
  /**
   * 销售订单子实体.询价促销类型
   */
  public static final String SO_SALEORDER_B_CPRCPROMOTTYPEID = "so_saleorder_b.cprcpromottypeid";
  /**
   * 销售订单子实体.客户订单号
   */
  public static final String SO_SALEORDER_B_VCUSTOMBILLCODE = "so_saleorder_b.vcustombillcode";
  /**
   * 销售订单子实体.买赠活动
   */
  public static final String SO_SALEORDER_B_CBUYLARGESSACTID = "so_saleorder_b.cbuylargessactid";
  /**
   * 销售订单子实体.价格促销活动
   */
  public static final String SO_SALEORDER_B_CPRICEPROMTACTID = "so_saleorder_b.cpricepromtactid";
  /**
   * 销售订单子实体.买赠设置
   */
  public static final String SO_SALEORDER_B_CBUYLARGESSID = "so_saleorder_b.cbuylargessid";
  /**
   * 销售订单子实体.累计安排进口合同主数量
   */
  public static final String SO_SALEORDER_B_NARRANGEITCNUM = "so_saleorder_b.narrangeitcnum";
  /**
   * 销售订单子实体.发货利润中心
   */
  public static final String SO_SALEORDER_B_CSPROFITCENTERVID = "so_saleorder_b.csprofitcentervid";
  /**
   * 销售订单子实体.发货利润中心最新版本
   */
  public static final String SO_SALEORDER_B_CSPROFITCENTERID = "so_saleorder_b.csprofitcenterid";
  /**
   * 销售订单子实体.赠品兑付
   */
  public static final String SO_SALEORDER_B_BLRGCASHFLAG = "so_saleorder_b.blrgcashflag";
  /**
   * 销售订单子实体.主记账单价
   */
  public static final String SO_SALEORDER_B_NACCPRICE = "so_saleorder_b.naccprice";
  /**
   * 销售订单子实体.促销价格表行
   */
  public static final String SO_SALEORDER_B_CPROMOTPRICEID = "so_saleorder_b.cpromotpriceid";
  /**
   * 销售订单子实体.特征码
   */
  public static final String SO_SALEORDER_B_CMFFILEID = "so_saleorder_b.cmffileid";
  /**
   * 销售订单子实体.特征价
   */
  public static final String SO_SALEORDER_B_NMFFILEPRICE = "so_saleorder_b.nmffileprice";
  /**
   * 销售订单子实体.vostatus
   */
  public static final String SO_SALEORDER_B_STATUS = "so_saleorder_b.status";
  /**
   * 销售订单子实体.dr
   */
  public static final String SO_SALEORDER_B_DR = "so_saleorder_b.dr";
  /**
   * 销售订单子实体.ts
   */
  public static final String SO_SALEORDER_B_TS = "so_saleorder_b.ts";
}


