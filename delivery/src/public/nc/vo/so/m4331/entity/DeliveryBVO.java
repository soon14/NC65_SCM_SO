package nc.vo.so.m4331.entity;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

/**
 * 
 * 
 * 发货单子实体
 *
 */
public class DeliveryBVO extends SuperVO {

 
  /**
   * 特征码
   */
  public static final String CMFFILEID="cmffileid";
  
  /**
   * 客户物料编码
   */
  public static final String CCUSTMATERIALID = "ccustmaterialid";

  /**
   * 设置客户物料编码
   * 
   */
  public void setCcustmaterialid(String ccustmaterialid) {
    this.setAttributeValue(DeliveryBVO.CCUSTMATERIALID, ccustmaterialid);
  }

  /**
   * 获取客户物料编码
   * 
   * @return 客户物料编码
   */
  public String getCcustmaterialid() {
    return (String) this.getAttributeValue(DeliveryBVO.CCUSTMATERIALID);
  }

  /** 待垫运费 */
  public static final String BADVFEEFLAG = "badvfeeflag";

  /** 是否已报检 */
  public static final String BCHECKFLAG = "bcheckflag";

  /** 发货安排关闭来源发货标记,发货单不用 */
  public static final String BCLOSESRCFLAG = "bclosesrcflag";

  /** 赠品 */
  public static final String BLARGESSFLAG = "blargessflag";

  /** 出库关闭 */
  public static final String BOUTENDFLAG = "boutendflag";

  /** 收入结算关闭 */
  public static final String BBARSETTLEFLAG = "bbarsettleflag";

  /** 是否已经质检 */
  public static final String BQUALITYFLAG = "bqualityflag";

  /** 运输关闭 */
  public static final String BTRANSENDFLAG = "btransendflag";

  /**
   * 三角贸易
   */
  public static final String BTRIATRADEFLAG = "btriatradeflag";

  /** 是否根据质检结果入库 */
  public static final String BUSECHECKFLAG = "busecheckflag";

  /** 应收组织 */
  public static final String CARORGID = "carorgid";

  /** 应收组织版本 */
  public static final String CARORGVID = "carorgvid";

  /** 单位 */
  public static final String CASTUNITID = "castunitid";

  /** 渠道类型 */
  public static final String CCHANNELTYPEID = "cchanneltypeid";

  /** 司机 */
  public static final String CCHAUFFEURID = "cchauffeurid";

  /** 本位币 */
  public static final String CCURRENCYID = "ccurrencyid";

  /** 发货单质检表id */
  public static final String CDELIVERYBBID = "cdeliverybbid";

  /** 发货单子表ID */
  public static final String CDELIVERYBID = "cdeliverybid";

  /** 发货单主表_主键 */
  public static final String CDELIVERYID = "cdeliveryid";

  /** 销售部门 */
  public static final String CDEPTID = "cdeptid";

  /** 销售部门版本 */
  public static final String CDEPTVID = "cdeptvid";

  /** 销售业务员 */
  public static final String CEMPLOYEEID = "cemployeeid";

  /** 源头单据表体ID */
  public static final String CFIRSTBID = "cfirstbid";

  /** 源头单据表头ID */
  public static final String CFIRSTID = "cfirstid";

  /** 散户 */
  public static final String CFREECUSTID = "cfreecustid";

  /** 收货库存组织 */
  public static final String CINSTOCKORGID = "cinstockorgid";

  /** 收货库存组织版本 */
  public static final String CINSTOCKORGVID = "cinstockorgvid";

  /** 收货仓库 */
  public static final String CINSTORDOCID = "cinstordocid";

  /** 开票客户 */
  public static final String CINVOICECUSTID = "cinvoicecustid";

  /** 物料版本 */
  public static final String CMATERIALID = "cmaterialid";

  /** 物料编码 */
  public static final String CMATERIALVID = "cmaterialvid";

  /** 订单客户 */
  public static final String CORDERCUSTID = "cordercustid";

  /**
   * 原产地区
   */
  public static final String CORIGAREAID = "corigareaid";

  /**
   * 原产国
   */
  public static final String CORIGCOUNTRYID = "corigcountryid";

  /** 原币 */
  public static final String CORIGCURRENCYID = "corigcurrencyid";

  /** 价格组成 */
  public static final String CPRICEFORMID = "cpriceformid";

  /** 产品线 */
  public static final String CPRODLINEID = "cprodlineid";

  /** 生产厂商 */
  public static final String CPRODUCTORID = "cproductorid";

  /** 结算利润中心最新版本 */
  public static final String CPROFITCENTERID = "cprofitcenterid";

  /** 结算利润中心 */
  public static final String CPROFITCENTERVID = "cprofitcentervid";

  /** 项目 */
  public static final String CPROJECTID = "cprojectid";

  /** 报价单位 */
  public static final String CQTUNITID = "cqtunitid";

  /** 质量等级 */
  public static final String CQUALITYLEVELID = "cqualitylevelid";

  /**
   * 收货国家/地区
   */
  public static final String CRECECOUNTRYID = "crececountryid";

  /** 收货地点 */
  public static final String CRECEIVEADDDOCID = "creceiveadddocid";

  /** 收货地址 */
  public static final String CRECEIVEADDRID = "creceiveaddrid";

  /** 收货地区 */
  public static final String CRECEIVEAREAID = "creceiveareaid";

  /** 收货客户 */
  public static final String CRECEIVECUSTID = "creceivecustid";

  /** 收货联系人 */
  public static final String CRECEIVEPERSONID = "creceivepersonid";

  /** 退货原因 */
  public static final String CRETREASONID = "cretreasonid";

  /** 行号 */
  public static final String CROWNO = "crowno";

  /** 销售组织 */
  public static final String CSALEORGID = "csaleorgid";

  /** 销售组织版本 */
  public static final String CSALEORGVID = "csaleorgvid";

  /** 发货地点 */
  public static final String CSENDADDDOCID = "csendadddocid";

  /** 发货地址 */
  public static final String CSENDADDRID = "csendaddrid";

  /** 发货地区 */
  public static final String CSENDAREAID = "csendareaid";

  /**
   * 发货国家/地区
   */
  public static final String CSENDCOUNTRYID = "csendcountryid";

  /** 发货联系人 */
  public static final String CSENDPERSONID = "csendpersonid";

  /** 发货库存组织 */
  public static final String CSENDSTOCKORGID = "csendstockorgid";

  /** 发货库存组织版本 */
  public static final String CSENDSTOCKORGVID = "csendstockorgvid";

  /** 发货仓库 */
  public static final String CSENDSTORDOCID = "csendstordocid";

  /** 结算财务组织 */
  public static final String CSETTLEORGID = "csettleorgid";

  /** 结算财务组织版本 */
  public static final String CSETTLEORGVID = "csettleorgvid";

  /** 货位 */
  public static final String CSPACEID = "cspaceid";

  /** 来源单据表体ID */
  public static final String CSRCBID = "csrcbid";

  /** 来源单据表头ID */
  public static final String CSRCID = "csrcid";

  /** 押运员 */
  public static final String CSUPERCARGOID = "csupercargoid";

  /**
   * 税码
   */
  public static final String CTAXCODEID = "ctaxcodeid";

  /**
   * 报税国家/地区
   */
  public static final String CTAXCOUNTRYID = "ctaxcountryid";

  /** 承运商 */
  public static final String CTRANSCUSTID = "ctranscustid";

  /** 主单位 */
  public static final String CUNITID = "cunitid";

  /** 车辆 */
  public static final String CVEHICLEID = "cvehicleid";

  /** 车型 */
  public static final String CVEHICLETYPEID = "cvehicletypeid";

  /** 供应商 */
  public static final String CVENDORID = "cvendorid";

  /** 单据日期 */
  public static final String DBILLDATE = "dbilldate";

  /** dr */
  public static final String DR = "dr";

  /** 要求收货日期 */
  public static final String DRECEIVEDATE = "dreceivedate";

  /** 发货日期 */
  public static final String DSENDDATE = "dsenddate";

  /**
   * 购销类型
   */
  public static final String FBUYSELLFLAG = "fbuysellflag";

  /** 备注 */
  public static final String FROWNOTE = "frownote";

  /**
   * 扣税类别
   */
  public static final String FTAXTYPEFLAG = "ftaxtypeflag";

  /**
   * 
   */
  public static final String MAINMETAPATH = DeliveryBVO.CDELIVERYBID + ".";

  /** 数量 */
  public static final String NASTNUM = "nastnum";

  /**
   * 计税金额
   */
  public static final String NCALTAXMNY = "ncaltaxmny";

  /** 本币折扣额 */
  public static final String NDISCOUNT = "ndiscount";

  /** 整单折扣 */
  public static final String NDISCOUNTRATE = "ndiscountrate";

  /** 折本汇率 */
  public static final String NEXCHANGERATE = "nexchangerate";

  /** 全局本位币汇率 */
  public static final String NGLOBALEXCHGRATE = "nglobalexchgrate";

  /** 全局本币无税金额 */
  public static final String NGLOBALMNY = "nglobalmny";

  /** 全局本币价税合计 */
  public static final String NGLOBALTAXMNY = "nglobaltaxmny";

  /** 集团本位币汇率 */
  public static final String NGROUPEXCHGRATE = "ngroupexchgrate";

  /** 集团本币无税金额 */
  public static final String NGROUPMNY = "ngroupmny";

  /** 集团本币价税合计 */
  public static final String NGROUPTAXMNY = "ngrouptaxmny";

  /** 单品折扣 */
  public static final String NITEMDISCOUNTRATE = "nitemdiscountrate";

  /** 本币无税金额 */
  public static final String NMNY = "nmny";

  /** 本币主无税净价 */
  public static final String NNETPRICE = "nnetprice";

  /** 主数量 */
  public static final String NNUM = "nnum";

  /** 折扣额 */
  public static final String NORIGDISCOUNT = "norigdiscount";

  /** 无税金额 */
  public static final String NORIGMNY = "norigmny";

  /** 主无税净价 */
  public static final String NORIGNETPRICE = "norignetprice";

  /** 主无税单价 */
  public static final String NORIGPRICE = "norigprice";

  /** 价税合计 */
  public static final String NORIGTAXMNY = "norigtaxmny";

  /** 主含税净价 */
  public static final String NORIGTAXNETPRICE = "norigtaxnetprice";

  /** 主含税单价 */
  public static final String NORIGTAXPRICE = "norigtaxprice";

  /** 件数 */
  public static final String NPIECE = "npiece";

  /** 本币主无税单价 */
  public static final String NPRICE = "nprice";

  /** 本币无税净价 */
  public static final String NQTNETPRICE = "nqtnetprice";

  /** 无税净价 */
  public static final String NQTORIGNETPRICE = "nqtorignetprice";

  /** 无税单价 */
  public static final String NQTORIGPRICE = "nqtorigprice";

  /** 含税净价 */
  public static final String NQTORIGTAXNETPRC = "nqtorigtaxnetprc";

  /** 含税单价 */
  public static final String NQTORIGTAXPRICE = "nqtorigtaxprice";

  /** 本币无税单价 */
  public static final String NQTPRICE = "nqtprice";

  /** 本币含税净价 */
  public static final String NQTTAXNETPRICE = "nqttaxnetprice";

  /** 本币含税单价 */
  public static final String NQTTAXPRICE = "nqttaxprice";

  /** 报价数量 */
  public static final String NQTUNITNUM = "nqtunitnum";

  /** 预留数量 */
  public static final String NREQRSNUM = "nreqrsnum";

  /** 税额 */
  public static final String NTAX = "ntax";

  /** 本币价税合计 */
  public static final String NTAXMNY = "ntaxmny";

  /** 本币主含税净价 */
  public static final String NTAXNETPRICE = "ntaxnetprice";

  /** 本币主含税单价 */
  public static final String NTAXPRICE = "ntaxprice";

  /** 税率 */
  public static final String NTAXRATE = "ntaxrate";

  /** 累计确认应收数量 */
  public static final String NTOTALARNUM = "ntotalarnum";

  /** 累计合格数量 */
  public static final String NTOTALELIGNUM = "ntotalelignum";

  /** 累计暂估应收数量 */
  public static final String NTOTALESTARNUM = "ntotalestarnum";

  /** 累计应发未出库数量 */
  public static final String NTOTALNOTOUTNUM = "ntotalnotoutnum";

  /** 累计出库数量 */
  public static final String NTOTALOUTNUM = "ntotaloutnum";

  /** 累计报检数量 */
  public static final String NTOTALREPORTNUM = "ntotalreportnum";

  /** 累计出库对冲数量 */
  public static final String NTOTALRUSHNUM = "ntotalrushnum";

  /** 累计运输数量 */
  public static final String NTOTALTRANSNUM = "ntotaltransnum";

  /** 累计不合格数量 */
  public static final String NTOTALUNELIGNUM = "ntotalunelignum";

  /** 累计途损数量 */
  public static final String NTRANSLOSSNUM = "ntranslossnum";

  /** 体积 */
  public static final String NVOLUME = "nvolume";

  /** 重量 */
  public static final String NWEIGHT = "nweight";

  /** 批次档案 */
  public static final String PK_BATCHCODE = "pk_batchcode";

  /** 所属集团 */
  public static final String PK_GROUP = "pk_group";

  /** 物流组织 */
  public static final String PK_ORG = "pk_org";

  private static final long serialVersionUID = 1L;

  /**
   * srcbts（不允许编辑）
   */
  public static final String SRCBTS = "srcbts";

  /**
   * srcts（不允许编辑）
   */
  public static final String SRCTS = "srcts";

  /** 时间戳 */
  public static final String TS = "ts";

  /** 发货单质检表ts */
  public static final String TTS = "tts";

  /** 批次号 */
  public static final String VBATCHCODE = "vbatchcode";

  /** 自定义项1 */
  public static final String VBDEF1 = "vbdef1";

  /** 自定义项10 */
  public static final String VBDEF10 = "vbdef10";

  /** 自定义项11 */
  public static final String VBDEF11 = "vbdef11";

  /** 自定义项12 */
  public static final String VBDEF12 = "vbdef12";

  /** 自定义项13 */
  public static final String VBDEF13 = "vbdef13";

  /** 自定义项14 */
  public static final String VBDEF14 = "vbdef14";

  /** 自定义项15 */
  public static final String VBDEF15 = "vbdef15";

  /** 自定义项16 */
  public static final String VBDEF16 = "vbdef16";

  /** 自定义项17 */
  public static final String VBDEF17 = "vbdef17";

  /** 自定义项18 */
  public static final String VBDEF18 = "vbdef18";

  /** 自定义项19 */
  public static final String VBDEF19 = "vbdef19";

  /** 自定义项2 */
  public static final String VBDEF2 = "vbdef2";

  /** 自定义项20 */
  public static final String VBDEF20 = "vbdef20";

  /** 自定义项3 */
  public static final String VBDEF3 = "vbdef3";

  /** 自定义项4 */
  public static final String VBDEF4 = "vbdef4";

  /** 自定义项5 */
  public static final String VBDEF5 = "vbdef5";

  /** 自定义项6 */
  public static final String VBDEF6 = "vbdef6";

  /** 自定义项7 */
  public static final String VBDEF7 = "vbdef7";

  /** 自定义项8 */
  public static final String VBDEF8 = "vbdef8";

  /** 自定义项9 */
  public static final String VBDEF9 = "vbdef9";

  /** 换算率 */
  public static final String VCHANGERATE = "vchangerate";

  /** 源头来源日期 */
  public static final String VFIRSTBILLDATE = "vfirstbilldate";

  /** 源头单据号 */
  public static final String VFIRSTCODE = "vfirstcode";

  /** 源头单据行号 */
  public static final String VFIRSTROWNO = "vfirstrowno";

  /** 源头交易类型 */
  public static final String VFIRSTTRANTYPE = "vfirsttrantype";

  /** 源头单据类型 */
  public static final String VFIRSTTYPE = "vfirsttype";

  /** 自由辅助属性1 */
  public static final String VFREE1 = "vfree1";

  /** 自由辅助属性10 */
  public static final String VFREE10 = "vfree10";

  /** 自由辅助属性2 */
  public static final String VFREE2 = "vfree2";

  /** 自由辅助属性3 */
  public static final String VFREE3 = "vfree3";

  /** 自由辅助属性4 */
  public static final String VFREE4 = "vfree4";

  /** 自由辅助属性5 */
  public static final String VFREE5 = "vfree5";

  /** 自由辅助属性6 */
  public static final String VFREE6 = "vfree6";

  /** 自由辅助属性7 */
  public static final String VFREE7 = "vfree7";

  /** 自由辅助属性8 */
  public static final String VFREE8 = "vfree8";

  /** 自由辅助属性9 */
  public static final String VFREE9 = "vfree9";

  /** 报价换算率 */
  public static final String VQTUNITRATE = "vqtunitrate";

  /** 收货联系电话 */
  public static final String VRECEIVETEL = "vreceivetel";

  /** 退货责任处理方式 */
  public static final String VRETURNMODE = "vreturnmode";

  /** 发货联系电话 */
  public static final String VSENDTEL = "vsendtel";

  /** 来源单据号 */
  public static final String VSRCCODE = "vsrccode";

  /** 来源单据行号 */
  public static final String VSRCROWNO = "vsrcrowno";

  /** 来源交易类型 */
  public static final String VSRCTRANTYPE = "vsrctrantype";

  /** 来源单据类型 */
  public static final String VSRCTYPE = "vsrctype";

  /** 发货利润中心 */
  public static final String CSPROFITCENTERVID = "csprofitcentervid";

  /** 发货利润中心最新版本 */
  public static final String CSPROFITCENTERID = "csprofitcenterid";

  /** 收货利润中心 */
  public static final String CRPROFITCENTERVID = "crprofitcentervid";

  /** 收货利润中心最新版本 */
  public static final String CRPROFITCENTERID = "crprofitcenterid";

  /**
   * 
   * @return 待垫运费
   */
  public UFBoolean getBadvfeeflag() {
    return (UFBoolean) this.getAttributeValue(DeliveryBVO.BADVFEEFLAG);
  }

  /**
   * 
   * @return 是否已报检
   */
  public UFBoolean getBcheckflag() {
    return (UFBoolean) this.getAttributeValue(DeliveryBVO.BCHECKFLAG);
  }

  /**
   * 
   * @return 发货安排关闭来源发货标记
   */
  public UFBoolean getBclosesrcflag() {
    return (UFBoolean) this.getAttributeValue(DeliveryBVO.BCLOSESRCFLAG);
  }

  /**
   * 收入结算关闭
   * 
   * @return 收入结算关闭
   */
  public UFBoolean getBbarsettleflag() {
    return (UFBoolean) this.getAttributeValue(DeliveryBVO.BBARSETTLEFLAG);
  }

  /**
   * 
   * @return 赠品
   */
  public UFBoolean getBlargessflag() {
    return (UFBoolean) this.getAttributeValue(DeliveryBVO.BLARGESSFLAG);
  }

  /**
   * 
   * @return 出库关闭
   */
  public UFBoolean getBoutendflag() {
    return (UFBoolean) this.getAttributeValue(DeliveryBVO.BOUTENDFLAG);
  }

  /**
   * 
   * @return 是否已经质检
   */
  public UFBoolean getBqualityflag() {
    return (UFBoolean) this.getAttributeValue(DeliveryBVO.BQUALITYFLAG);
  }

  /**
   * 
   * @return 运输关闭
   */
  public UFBoolean getBtransendflag() {
    return (UFBoolean) this.getAttributeValue(DeliveryBVO.BTRANSENDFLAG);
  }

  /**
   * 获取三角贸易
   * 
   * @return 三角贸易
   */
  public UFBoolean getBtriatradeflag() {
    return (UFBoolean) this.getAttributeValue(DeliveryBVO.BTRIATRADEFLAG);
  }

  /**
   * 
   * @return 是否根据质检结果入库
   */
  public UFBoolean getBusecheckflag() {
    return (UFBoolean) this.getAttributeValue(DeliveryBVO.BUSECHECKFLAG);
  }

  /**
   * 
   * @return 应收组织
   */
  public String getCarorgid() {
    return (String) this.getAttributeValue(DeliveryBVO.CARORGID);
  }

  /**
   * 
   * @return 应收组织版本
   */
  public String getCarorgvid() {
    return (String) this.getAttributeValue(DeliveryBVO.CARORGVID);
  }

  /**
   * 
   * @return 单位
   */
  public String getCastunitid() {
    return (String) this.getAttributeValue(DeliveryBVO.CASTUNITID);
  }

  /**
   * 
   * @return 渠道类型
   */
  public String getCchanneltypeid() {
    return (String) this.getAttributeValue(DeliveryBVO.CCHANNELTYPEID);
  }

  /**
   * 
   * @return 司机
   */
  public String getCchauffeurid() {
    return (String) this.getAttributeValue(DeliveryBVO.CCHAUFFEURID);
  }

  /**
   * 
   * @return 本位币
   */
  public String getCcurrencyid() {
    return (String) this.getAttributeValue(DeliveryBVO.CCURRENCYID);
  }

  /**
   * 
   * @return 发货单质检表id
   */
  public String getCdeliverybbid() {
    return (String) this.getAttributeValue(DeliveryBVO.CDELIVERYBBID);
  }

  /**
   * 
   * @return 发货单子表ID
   */
  public String getCdeliverybid() {
    return (String) this.getAttributeValue(DeliveryBVO.CDELIVERYBID);
  }

  /**
   * 
   * @return 发货单主表_主键
   */
  public String getCdeliveryid() {
    return (String) this.getAttributeValue(DeliveryBVO.CDELIVERYID);
  }

  /**
   * 
   * @return 销售部门
   */
  public String getCdeptid() {
    return (String) this.getAttributeValue(DeliveryBVO.CDEPTID);
  }

  /**
   * 
   * @return 销售部门版本
   */
  public String getCdeptvid() {
    return (String) this.getAttributeValue(DeliveryBVO.CDEPTVID);
  }

  /**
   * 
   * @return 销售业务员
   */
  public String getCemployeeid() {
    return (String) this.getAttributeValue(DeliveryBVO.CEMPLOYEEID);
  }

  /**
   * 
   * @return 源头单据表体ID
   */
  public String getCfirstbid() {
    return (String) this.getAttributeValue(DeliveryBVO.CFIRSTBID);
  }

  /**
   * 
   * @return 源头单据表头ID
   */
  public String getCfirstid() {
    return (String) this.getAttributeValue(DeliveryBVO.CFIRSTID);
  }

  /**
   * 
   * @return 散户
   */
  public String getCfreecustid() {
    return (String) this.getAttributeValue(DeliveryBVO.CFREECUSTID);
  }

  /**
   * 
   * @return 收货库存组织
   */
  public String getCinstockorgid() {
    return (String) this.getAttributeValue(DeliveryBVO.CINSTOCKORGID);
  }

  /**
   * 
   * @return 收货库存组织版本
   */
  public String getCinstockorgvid() {
    return (String) this.getAttributeValue(DeliveryBVO.CINSTOCKORGVID);
  }

  /**
   * 
   * @return 收货仓库
   */
  public String getCinstordocid() {
    return (String) this.getAttributeValue(DeliveryBVO.CINSTORDOCID);
  }

  /**
   * 
   * @return 开票客户
   */
  public String getCinvoicecustid() {
    return (String) this.getAttributeValue(DeliveryBVO.CINVOICECUSTID);
  }

  /**
   * 
   * @return 物料版本
   */
  public String getCmaterialid() {
    return (String) this.getAttributeValue(DeliveryBVO.CMATERIALID);
  }

  /**
   * 
   * @return 物料编码
   */
  public String getCmaterialvid() {
    return (String) this.getAttributeValue(DeliveryBVO.CMATERIALVID);
  }

  /**
   * 
   * @return 订单客户
   */
  public String getCordercustid() {
    return (String) this.getAttributeValue(DeliveryBVO.CORDERCUSTID);
  }

  /**
   * 获取原产地区
   * 
   * @return 原产地区
   */
  public String getCorigareaid() {
    return (String) this.getAttributeValue(DeliveryBVO.CORIGAREAID);
  }

  /**
   * 获取原产国
   * 
   * @return 原产国
   */
  public String getCorigcountryid() {
    return (String) this.getAttributeValue(DeliveryBVO.CORIGCOUNTRYID);
  }

  /**
   * 
   * @return
   */
  public String getCorigcurrencyid() {
    return (String) this.getAttributeValue(DeliveryBVO.CORIGCURRENCYID);
  }

  /**
   * 
   * @return
   */
  public String getCpriceformid() {
    return (String) this.getAttributeValue(DeliveryBVO.CPRICEFORMID);
  }

  /**
   * 
   * @return 产品线
   */
  public String getCprodlineid() {
    return (String) this.getAttributeValue(DeliveryBVO.CPRODLINEID);
  }

  /**
   * 
   * @return 生产厂商
   */
  public String getCproductorid() {
    return (String) this.getAttributeValue(DeliveryBVO.CPRODUCTORID);
  }

  /**
   * 
   * @return 结算利润中心
   */
  public String getCprofitcenterid() {
    return (String) this.getAttributeValue(DeliveryBVO.CPROFITCENTERID);
  }

  /**
   * 
   * @return 结算利润中心版本
   */
  public String getCprofitcentervid() {
    return (String) this.getAttributeValue(DeliveryBVO.CPROFITCENTERVID);
  }

  /**
   * 
   * @return 项目
   */
  public String getCprojectid() {
    return (String) this.getAttributeValue(DeliveryBVO.CPROJECTID);
  }

  /**
   * 
   * @return 报价单位
   */
  public String getCqtunitid() {
    return (String) this.getAttributeValue(DeliveryBVO.CQTUNITID);
  }

  /**
   * 
   * @return 质量等级
   */
  public String getCqualitylevelid() {
    return (String) this.getAttributeValue(DeliveryBVO.CQUALITYLEVELID);
  }

  /**
   * 获取收货国家/地区
   * 
   * @return 收获国家/地区
   */
  public String getCrececountryid() {
    return (String) this.getAttributeValue(DeliveryBVO.CRECECOUNTRYID);
  }

  /**
   * 
   * @return 收货地点
   */
  public String getCreceiveadddocid() {
    return (String) this.getAttributeValue(DeliveryBVO.CRECEIVEADDDOCID);
  }

  /**
   * 
   * @return 收货地址
   */
  public String getCreceiveaddrid() {
    return (String) this.getAttributeValue(DeliveryBVO.CRECEIVEADDRID);
  }

  /**
   * 
   * @return 收货地区
   */
  public String getCreceiveareaid() {
    return (String) this.getAttributeValue(DeliveryBVO.CRECEIVEAREAID);
  }

  /**
   * 
   * @return 收货客户
   */
  public String getCreceivecustid() {
    return (String) this.getAttributeValue(DeliveryBVO.CRECEIVECUSTID);
  }

  /**
   * 
   * @return 收货联系人
   */
  public String getCreceivepersonid() {
    return (String) this.getAttributeValue(DeliveryBVO.CRECEIVEPERSONID);
  }

  /**
   * 
   * @return 退货原因
   */
  public String getCretreasonid() {
    return (String) this.getAttributeValue(DeliveryBVO.CRETREASONID);
  }

  /**
   * 
   * @return 行号
   */
  public String getCrowno() {
    return (String) this.getAttributeValue(DeliveryBVO.CROWNO);
  }

  /**
   * 
   * @return 销售组织
   */
  public String getCsaleorgid() {
    return (String) this.getAttributeValue(DeliveryBVO.CSALEORGID);
  }

  /**
   * 
   * @return 销售组织版本
   */
  public String getCsaleorgvid() {
    return (String) this.getAttributeValue(DeliveryBVO.CSALEORGVID);
  }

  /**
   * 
   * @return 发货地点
   */
  public String getCsendadddocid() {
    return (String) this.getAttributeValue(DeliveryBVO.CSENDADDDOCID);
  }

  /**
   * 
   * @return 发货地址
   */
  public String getCsendaddrid() {
    return (String) this.getAttributeValue(DeliveryBVO.CSENDADDRID);
  }

  /**
   * 
   * @return 发货地区
   */
  public String getCsendareaid() {
    return (String) this.getAttributeValue(DeliveryBVO.CSENDAREAID);
  }

  /**
   * 获取发货国家/地区
   * 
   * @return 发货国家/地区
   */
  public String getCsendcountryid() {
    return (String) this.getAttributeValue(DeliveryBVO.CSENDCOUNTRYID);
  }

  /**
   * 
   * @return 发货联系人
   */
  public String getCsendpersonid() {
    return (String) this.getAttributeValue(DeliveryBVO.CSENDPERSONID);
  }

  /**
   * 
   * @return 发货库存组织
   */
  public String getCsendstockorgid() {
    return (String) this.getAttributeValue(DeliveryBVO.CSENDSTOCKORGID);
  }

  /**
   * 
   * @return 发货库存组织版本
   */
  public String getCsendstockorgvid() {
    return (String) this.getAttributeValue(DeliveryBVO.CSENDSTOCKORGVID);
  }

  /**
   * 
   * @return 发货仓库
   */
  public String getCsendstordocid() {
    return (String) this.getAttributeValue(DeliveryBVO.CSENDSTORDOCID);
  }

  /**
   * 
   * @return 结算财务组织
   */
  public String getCsettleorgid() {
    return (String) this.getAttributeValue(DeliveryBVO.CSETTLEORGID);
  }

  /**
   * 
   * @return 结算财务组织版本
   */
  public String getCsettleorgvid() {
    return (String) this.getAttributeValue(DeliveryBVO.CSETTLEORGVID);
  }

  /**
   * 
   * @return 货位
   */
  public String getCspaceid() {
    return (String) this.getAttributeValue(DeliveryBVO.CSPACEID);
  }

  /**
   * 
   * @return 来源单据表体ID
   */
  public String getCsrcbid() {
    return (String) this.getAttributeValue(DeliveryBVO.CSRCBID);
  }

  /**
   * 
   * @return 来源单据表头ID
   */
  public String getCsrcid() {
    return (String) this.getAttributeValue(DeliveryBVO.CSRCID);
  }

  /**
   * 
   * @return 押运员
   */
  public String getCsupercargoid() {
    return (String) this.getAttributeValue(DeliveryBVO.CSUPERCARGOID);
  }

  /**
   * 获取税码
   * 
   * @return 税码
   */
  public String getCtaxcodeid() {
    return (String) this.getAttributeValue(DeliveryBVO.CTAXCODEID);
  }

  /**
   * 获取报税国家和地区
   * 
   * @return 报税国家/地区
   */
  public String getCtaxcountryid() {
    return (String) this.getAttributeValue(DeliveryBVO.CTAXCOUNTRYID);
  }

  /**
   * 
   * @return 承运商
   */
  public String getCtranscustid() {
    return (String) this.getAttributeValue(DeliveryBVO.CTRANSCUSTID);
  }

  /**
   * 
   * @return 主单位
   */
  public String getCunitid() {
    return (String) this.getAttributeValue(DeliveryBVO.CUNITID);
  }

  /**
   * 
   * @return 车辆
   */
  public String getCvehicleid() {
    return (String) this.getAttributeValue(DeliveryBVO.CVEHICLEID);
  }

  /**
   * 
   * @return 车型
   */
  public String getCvehicletypeid() {
    return (String) this.getAttributeValue(DeliveryBVO.CVEHICLETYPEID);
  }

  /**
   * 
   * @return 供应商
   */
  public String getCvendorid() {
    return (String) this.getAttributeValue(DeliveryBVO.CVENDORID);
  }

  /**
   * 
   * @return 单据日期
   */
  public UFDate getDbilldate() {
    return (UFDate) this.getAttributeValue(DeliveryBVO.DBILLDATE);
  }

  /**
   * 
   * @return DR
   */
  public Integer getDr() {
    return (Integer) this.getAttributeValue(DeliveryBVO.DR);
  }

  /**
   * 
   * @return 要求收货日期
   */
  public UFDate getDreceivedate() {
    return (UFDate) this.getAttributeValue(DeliveryBVO.DRECEIVEDATE);
  }

  /**
   * 
   * @return 发货日期
   */
  public UFDate getDsenddate() {
    return (UFDate) this.getAttributeValue(DeliveryBVO.DSENDDATE);
  }

  /**
   * 获取购销类型
   * 
   * @return 购销类型
   */
  public Integer getFbuysellflag() {
    return (Integer) this.getAttributeValue(DeliveryBVO.FBUYSELLFLAG);
  }

  /**
   * 
   * @return 备注
   */
  public String getFrownote() {
    return (String) this.getAttributeValue(DeliveryBVO.FROWNOTE);
  }

  /**
   * 获取报税类别
   * 
   * @return 报税类别
   */
  public Integer getFtaxtypeflag() {
    return (Integer) this.getAttributeValue(DeliveryBVO.FTAXTYPEFLAG);
  }

  @Override
  public IVOMeta getMetaData() {
    IVOMeta meta = VOMetaFactory.getInstance().getVOMeta("so.delivery_b");
    return meta;
  }

  /**
   * 
   * @return
   */
  public UFDouble getNastnum() {
    return (UFDouble) this.getAttributeValue(DeliveryBVO.NASTNUM);
  }

  /**
   * 获取价税金额
   * 
   * @return 计税金额
   */
  public UFDouble getNcaltaxmny() {
    return (UFDouble) this.getAttributeValue(DeliveryBVO.NCALTAXMNY);
  }

  /**
   * 
   * @return 本币折扣额
   */
  public UFDouble getNdiscount() {
    return (UFDouble) this.getAttributeValue(DeliveryBVO.NDISCOUNT);
  }

  /**
   * 
   * @return 整单折扣
   */
  public UFDouble getNdiscountrate() {
    return (UFDouble) this.getAttributeValue(DeliveryBVO.NDISCOUNTRATE);
  }

  /**
   * 
   * @return 折本汇率
   */
  public UFDouble getNexchangerate() {
    return (UFDouble) this.getAttributeValue(DeliveryBVO.NEXCHANGERATE);
  }

  /**
   * 
   * @return 全局本位币汇率
   */
  public UFDouble getNglobalexchgrate() {
    return (UFDouble) this.getAttributeValue(DeliveryBVO.NGLOBALEXCHGRATE);
  }

  /**
   * 
   * @return 全局本币无税金额
   */
  public UFDouble getNglobalmny() {
    return (UFDouble) this.getAttributeValue(DeliveryBVO.NGLOBALMNY);
  }

  /**
   * 
   * @return
   */
  public UFDouble getNglobaltaxmny() {
    return (UFDouble) this.getAttributeValue(DeliveryBVO.NGLOBALTAXMNY);
  }

  /**
   * 
   * @return
   */
  public UFDouble getNgroupexchgrate() {
    return (UFDouble) this.getAttributeValue(DeliveryBVO.NGROUPEXCHGRATE);
  }

  /**
   * 
   * @return
   */
  public UFDouble getNgroupmny() {
    return (UFDouble) this.getAttributeValue(DeliveryBVO.NGROUPMNY);
  }

  /**
   * 
   * @return
   */
  public UFDouble getNgrouptaxmny() {
    return (UFDouble) this.getAttributeValue(DeliveryBVO.NGROUPTAXMNY);
  }

  /**
   * 
   * @return
   */
  public UFDouble getNitemdiscountrate() {
    return (UFDouble) this.getAttributeValue(DeliveryBVO.NITEMDISCOUNTRATE);
  }

  /**
   * 
   * @return
   */
  public UFDouble getNmny() {
    return (UFDouble) this.getAttributeValue(DeliveryBVO.NMNY);
  }

  /**
   * 
   * @return
   */
  public UFDouble getNnetprice() {
    return (UFDouble) this.getAttributeValue(DeliveryBVO.NNETPRICE);
  }

  /**
   * 
   * @return
   */
  public UFDouble getNnum() {
    return (UFDouble) this.getAttributeValue(DeliveryBVO.NNUM);
  }

  /**
   * 
   * @return
   */
  public UFDouble getNorigdiscount() {
    return (UFDouble) this.getAttributeValue(DeliveryBVO.NORIGDISCOUNT);
  }

  /**
   * 
   * @return
   */
  public UFDouble getNorigmny() {
    return (UFDouble) this.getAttributeValue(DeliveryBVO.NORIGMNY);
  }

  /**
   * 
   * @return
   */
  public UFDouble getNorignetprice() {
    return (UFDouble) this.getAttributeValue(DeliveryBVO.NORIGNETPRICE);
  }

  /**
   * 
   * @return
   */
  public UFDouble getNorigprice() {
    return (UFDouble) this.getAttributeValue(DeliveryBVO.NORIGPRICE);
  }

  /**
   * 
   * @return
   */
  public UFDouble getNorigtaxmny() {
    return (UFDouble) this.getAttributeValue(DeliveryBVO.NORIGTAXMNY);
  }

  /**
   * 
   * @return
   */
  public UFDouble getNorigtaxnetprice() {
    return (UFDouble) this.getAttributeValue(DeliveryBVO.NORIGTAXNETPRICE);
  }

  /**
   * 
   * @return
   */
  public UFDouble getNorigtaxprice() {
    return (UFDouble) this.getAttributeValue(DeliveryBVO.NORIGTAXPRICE);
  }

  /**
   * 
   * @return
   */
  public UFDouble getNpiece() {
    return (UFDouble) this.getAttributeValue(DeliveryBVO.NPIECE);
  }

  /**
   * 
   * @return
   */
  public UFDouble getNprice() {
    return (UFDouble) this.getAttributeValue(DeliveryBVO.NPRICE);
  }

  /**
   * 
   * @return
   */
  public UFDouble getNqtnetprice() {
    return (UFDouble) this.getAttributeValue(DeliveryBVO.NQTNETPRICE);
  }

  /**
   * 
   * @return
   */
  public UFDouble getNqtorignetprice() {
    return (UFDouble) this.getAttributeValue(DeliveryBVO.NQTORIGNETPRICE);
  }

  /**
   * 
   * @return
   */
  public UFDouble getNqtorigprice() {
    return (UFDouble) this.getAttributeValue(DeliveryBVO.NQTORIGPRICE);
  }

  /**
   * 
   * @return
   */
  public UFDouble getNqtorigtaxnetprc() {
    return (UFDouble) this.getAttributeValue(DeliveryBVO.NQTORIGTAXNETPRC);
  }

  /**
   * 
   * @return
   */
  public UFDouble getNqtorigtaxprice() {
    return (UFDouble) this.getAttributeValue(DeliveryBVO.NQTORIGTAXPRICE);
  }

  /**
   * 
   * @return
   */
  public UFDouble getNqtprice() {
    return (UFDouble) this.getAttributeValue(DeliveryBVO.NQTPRICE);
  }

  /**
   * 
   * @return
   */
  public UFDouble getNqttaxnetprice() {
    return (UFDouble) this.getAttributeValue(DeliveryBVO.NQTTAXNETPRICE);
  }

  /**
   * 
   * @return
   */
  public UFDouble getNqttaxprice() {
    return (UFDouble) this.getAttributeValue(DeliveryBVO.NQTTAXPRICE);
  }

  /**
   * 
   * @return
   */
  public UFDouble getNqtunitnum() {
    return (UFDouble) this.getAttributeValue(DeliveryBVO.NQTUNITNUM);
  }

  /**
   * 
   * @return
   */
  public UFDouble getNreqrsnum() {
    return (UFDouble) this.getAttributeValue(DeliveryBVO.NREQRSNUM);
  }

  /**
   * 
   * @return
   */
  public UFDouble getNtax() {
    return (UFDouble) this.getAttributeValue(DeliveryBVO.NTAX);
  }

  /**
   * 
   * @return
   */
  public UFDouble getNtaxmny() {
    return (UFDouble) this.getAttributeValue(DeliveryBVO.NTAXMNY);
  }

  /**
   * 
   * @return
   */
  public UFDouble getNtaxnetprice() {
    return (UFDouble) this.getAttributeValue(DeliveryBVO.NTAXNETPRICE);
  }

  /**
   * 
   * @return
   */
  public UFDouble getNtaxprice() {
    return (UFDouble) this.getAttributeValue(DeliveryBVO.NTAXPRICE);
  }

  public UFDouble getNtaxrate() {
    return (UFDouble) this.getAttributeValue(DeliveryBVO.NTAXRATE);
  }

  public UFDouble getNtotalarnum() {
    return (UFDouble) this.getAttributeValue(DeliveryBVO.NTOTALARNUM);
  }

  public UFDouble getNtotalelignum() {
    return (UFDouble) this.getAttributeValue(DeliveryBVO.NTOTALELIGNUM);
  }

  public UFDouble getNtotalestarnum() {
    return (UFDouble) this.getAttributeValue(DeliveryBVO.NTOTALESTARNUM);
  }

  public UFDouble getNtotalnotoutnum() {
    return (UFDouble) this.getAttributeValue(DeliveryBVO.NTOTALNOTOUTNUM);
  }

  public UFDouble getNtotaloutnum() {
    return (UFDouble) this.getAttributeValue(DeliveryBVO.NTOTALOUTNUM);
  }

  public UFDouble getNtotalreportnum() {
    return (UFDouble) this.getAttributeValue(DeliveryBVO.NTOTALREPORTNUM);
  }

  public UFDouble getNtotalrushnum() {
    return (UFDouble) this.getAttributeValue(DeliveryBVO.NTOTALRUSHNUM);
  }

  public UFDouble getNtotaltransnum() {
    return (UFDouble) this.getAttributeValue(DeliveryBVO.NTOTALTRANSNUM);
  }

  public UFDouble getNtotalunelignum() {
    return (UFDouble) this.getAttributeValue(DeliveryBVO.NTOTALUNELIGNUM);
  }

  public UFDouble getNtranslossnum() {
    return (UFDouble) this.getAttributeValue(DeliveryBVO.NTRANSLOSSNUM);
  }

  public UFDouble getNvolume() {
    return (UFDouble) this.getAttributeValue(DeliveryBVO.NVOLUME);
  }

  public UFDouble getNweight() {
    return (UFDouble) this.getAttributeValue(DeliveryBVO.NWEIGHT);
  }

  public String getPk_batchcode() {
    return (String) this.getAttributeValue(DeliveryBVO.PK_BATCHCODE);
  }

  public String getPk_group() {
    return (String) this.getAttributeValue(DeliveryBVO.PK_GROUP);
  }

  public String getPk_org() {
    return (String) this.getAttributeValue(DeliveryBVO.PK_ORG);
  }

  /**
   * 获取来源时间戳
   * 
   * @return 时间戳
   */
  public UFDateTime getSrcbts() {
    return (UFDateTime) this.getAttributeValue(DeliveryBVO.SRCBTS);
  }

  /**
   * 获取来源时间戳
   * 
   * @return 时间戳
   */
  public UFDateTime getSrcts() {
    return (UFDateTime) this.getAttributeValue(DeliveryBVO.SRCTS);
  }

  public UFDateTime getTs() {
    return (UFDateTime) this.getAttributeValue(DeliveryBVO.TS);
  }

  public UFDateTime getTts() {
    return ValueUtils.getUFDateTime(this.getAttributeValue(DeliveryBVO.TTS));
  }

  public String getVbatchcode() {
    return (String) this.getAttributeValue(DeliveryBVO.VBATCHCODE);
  }

  public String getVbdef1() {
    return (String) this.getAttributeValue(DeliveryBVO.VBDEF1);
  }

  public String getVbdef10() {
    return (String) this.getAttributeValue(DeliveryBVO.VBDEF10);
  }

  public String getVbdef11() {
    return (String) this.getAttributeValue(DeliveryBVO.VBDEF11);
  }

  public String getVbdef12() {
    return (String) this.getAttributeValue(DeliveryBVO.VBDEF12);
  }

  public String getVbdef13() {
    return (String) this.getAttributeValue(DeliveryBVO.VBDEF13);
  }

  public String getVbdef14() {
    return (String) this.getAttributeValue(DeliveryBVO.VBDEF14);
  }

  public String getVbdef15() {
    return (String) this.getAttributeValue(DeliveryBVO.VBDEF15);
  }

  public String getVbdef16() {
    return (String) this.getAttributeValue(DeliveryBVO.VBDEF16);
  }

  public String getVbdef17() {
    return (String) this.getAttributeValue(DeliveryBVO.VBDEF17);
  }

  public String getVbdef18() {
    return (String) this.getAttributeValue(DeliveryBVO.VBDEF18);
  }

  public String getVbdef19() {
    return (String) this.getAttributeValue(DeliveryBVO.VBDEF19);
  }

  public String getVbdef2() {
    return (String) this.getAttributeValue(DeliveryBVO.VBDEF2);
  }

  public String getVbdef20() {
    return (String) this.getAttributeValue(DeliveryBVO.VBDEF20);
  }

  public String getVbdef3() {
    return (String) this.getAttributeValue(DeliveryBVO.VBDEF3);
  }

  public String getVbdef4() {
    return (String) this.getAttributeValue(DeliveryBVO.VBDEF4);
  }

  public String getVbdef5() {
    return (String) this.getAttributeValue(DeliveryBVO.VBDEF5);
  }

  public String getVbdef6() {
    return (String) this.getAttributeValue(DeliveryBVO.VBDEF6);
  }

  public String getVbdef7() {
    return (String) this.getAttributeValue(DeliveryBVO.VBDEF7);
  }

  public String getVbdef8() {
    return (String) this.getAttributeValue(DeliveryBVO.VBDEF8);
  }

  public String getVbdef9() {
    return (String) this.getAttributeValue(DeliveryBVO.VBDEF9);
  }

  public String getVchangerate() {
    return (String) this.getAttributeValue(DeliveryBVO.VCHANGERATE);
  }

  public UFDate getVfirstbilldate() {
    return (UFDate) this.getAttributeValue(DeliveryBVO.VFIRSTBILLDATE);
  }

  public String getVfirstcode() {
    return (String) this.getAttributeValue(DeliveryBVO.VFIRSTCODE);
  }

  public String getVfirstrowno() {
    return (String) this.getAttributeValue(DeliveryBVO.VFIRSTROWNO);
  }

  public String getVfirsttrantype() {
    return (String) this.getAttributeValue(DeliveryBVO.VFIRSTTRANTYPE);
  }

  public String getVfirsttype() {
    return (String) this.getAttributeValue(DeliveryBVO.VFIRSTTYPE);
  }

  public String getVfree1() {
    return (String) this.getAttributeValue(DeliveryBVO.VFREE1);
  }

  public String getVfree10() {
    return (String) this.getAttributeValue(DeliveryBVO.VFREE10);
  }

  public String getVfree2() {
    return (String) this.getAttributeValue(DeliveryBVO.VFREE2);
  }

  public String getVfree3() {
    return (String) this.getAttributeValue(DeliveryBVO.VFREE3);
  }

  public String getVfree4() {
    return (String) this.getAttributeValue(DeliveryBVO.VFREE4);
  }

  public String getVfree5() {
    return (String) this.getAttributeValue(DeliveryBVO.VFREE5);
  }

  public String getVfree6() {
    return (String) this.getAttributeValue(DeliveryBVO.VFREE6);
  }

  public String getVfree7() {
    return (String) this.getAttributeValue(DeliveryBVO.VFREE7);
  }

  public String getVfree8() {
    return (String) this.getAttributeValue(DeliveryBVO.VFREE8);
  }

  public String getVfree9() {
    return (String) this.getAttributeValue(DeliveryBVO.VFREE9);
  }

  public String getVqtunitrate() {
    return (String) this.getAttributeValue(DeliveryBVO.VQTUNITRATE);
  }

  public String getVreceivetel() {
    return (String) this.getAttributeValue(DeliveryBVO.VRECEIVETEL);
  }

  public String getVreturnmode() {
    return (String) this.getAttributeValue(DeliveryBVO.VRETURNMODE);
  }

  public String getVsendtel() {
    return (String) this.getAttributeValue(DeliveryBVO.VSENDTEL);
  }

  public String getVsrccode() {
    return (String) this.getAttributeValue(DeliveryBVO.VSRCCODE);
  }

  public String getVsrcrowno() {
    return (String) this.getAttributeValue(DeliveryBVO.VSRCROWNO);
  }

  public String getVsrctrantype() {
    return (String) this.getAttributeValue(DeliveryBVO.VSRCTRANTYPE);
  }

  public String getVsrctype() {
    return (String) this.getAttributeValue(DeliveryBVO.VSRCTYPE);
  }

  /**
   * 获取发货利润中心最新版本
   * 
   * @return 发货利润中心最新版本
   */
  public String getCsprofitcentervid() {
    return (String) this.getAttributeValue(DeliveryBVO.CSPROFITCENTERVID);
  }

  /**
   * 获取发货利润中心
   * 
   * @return 发货利润中心
   */
  public String getCsprofitcenterid() {
    return (String) this.getAttributeValue(DeliveryBVO.CSPROFITCENTERID);
  }

  /**
   * 获取收货利润中心最新版本
   * 
   * @return 收货利润中心最新版本
   */
  public String getCrprofitcentervid() {
    return (String) this.getAttributeValue(DeliveryBVO.CRPROFITCENTERVID);
  }

  /**
   * 获取收货利润中心
   * 
   * @return 收货利润中心
   */
  public String getCrprofitcenterid() {
    return (String) this.getAttributeValue(DeliveryBVO.CRPROFITCENTERID);
  }

  public void setBadvfeeflag(UFBoolean badvfeeflag) {
    this.setAttributeValue(DeliveryBVO.BADVFEEFLAG, badvfeeflag);
  }

  public void setBcheckflag(UFBoolean bcheckflag) {
    this.setAttributeValue(DeliveryBVO.BCHECKFLAG, bcheckflag);
  }

  public void setBclosesrcflag(UFBoolean bclosesrcflag) {
    this.setAttributeValue(DeliveryBVO.BCLOSESRCFLAG, bclosesrcflag);
  }

  public void setBlargessflag(UFBoolean blargessflag) {
    this.setAttributeValue(DeliveryBVO.BLARGESSFLAG, blargessflag);
  }

  public void setBoutendflag(UFBoolean boutendflag) {
    this.setAttributeValue(DeliveryBVO.BOUTENDFLAG, boutendflag);
  }

  public void setBqualityflag(UFBoolean bqualityflag) {
    this.setAttributeValue(DeliveryBVO.BQUALITYFLAG, bqualityflag);
  }

  public void setBtransendflag(UFBoolean btransendflag) {
    this.setAttributeValue(DeliveryBVO.BTRANSENDFLAG, btransendflag);
  }

  /**
   * 设置 三角贸易
   * 
   * @param btriatradeflag 三角贸易
   */
  public void setBtriatradeflag(UFBoolean btriatradeflag) {
    this.setAttributeValue(DeliveryBVO.BTRIATRADEFLAG, btriatradeflag);
  }

  public void setBusecheckflag(UFBoolean busecheckflag) {
    this.setAttributeValue(DeliveryBVO.BUSECHECKFLAG, busecheckflag);
  }

  public void setCarorgid(String carorgid) {
    this.setAttributeValue(DeliveryBVO.CARORGID, carorgid);
  }

  public void setCarorgvid(String carorgvid) {
    this.setAttributeValue(DeliveryBVO.CARORGVID, carorgvid);
  }

  public void setCastunitid(String castunitid) {
    this.setAttributeValue(DeliveryBVO.CASTUNITID, castunitid);
  }

  public void setCchanneltypeid(String newCchanneltypeid) {
    this.setAttributeValue(DeliveryBVO.CCHANNELTYPEID, newCchanneltypeid);
  }

  public void setCchauffeurid(String cchauffeurid) {
    this.setAttributeValue(DeliveryBVO.CCHAUFFEURID, cchauffeurid);
  }

  public void setCcurrencyid(String ccurrencyid) {
    this.setAttributeValue(DeliveryBVO.CCURRENCYID, ccurrencyid);
  }

  public void setCdeliverybbid(String cdeliverybbid) {
    this.setAttributeValue(DeliveryBVO.CDELIVERYBBID, cdeliverybbid);
  }

  public void setCdeliverybid(String cdeliverybid) {
    this.setAttributeValue(DeliveryBVO.CDELIVERYBID, cdeliverybid);
  }

  public void setCdeliveryid(String cdeliveryid) {
    this.setAttributeValue(DeliveryBVO.CDELIVERYID, cdeliveryid);
  }

  public void setCdeptid(String cdeptid) {
    this.setAttributeValue(DeliveryBVO.CDEPTID, cdeptid);
  }

  public void setCdeptvid(String cdeptvid) {
    this.setAttributeValue(DeliveryBVO.CDEPTVID, cdeptvid);
  }

  public void setCemployeeid(String cemployeeid) {
    this.setAttributeValue(DeliveryBVO.CEMPLOYEEID, cemployeeid);
  }

  public void setCfirstbid(String cfirstbid) {
    this.setAttributeValue(DeliveryBVO.CFIRSTBID, cfirstbid);
  }

  public void setCfirstid(String cfirstid) {
    this.setAttributeValue(DeliveryBVO.CFIRSTID, cfirstid);
  }

  public void setCfreecustid(String cfreecustid) {
    this.setAttributeValue(DeliveryBVO.CFREECUSTID, cfreecustid);
  }

  public void setCinstockorgid(String cinstockorgid) {
    this.setAttributeValue(DeliveryBVO.CINSTOCKORGID, cinstockorgid);
  }

  public void setCinstockorgvid(String cinstockorgvid) {
    this.setAttributeValue(DeliveryBVO.CINSTOCKORGVID, cinstockorgvid);
  }

  public void setCinstordocid(String cinstordocid) {
    this.setAttributeValue(DeliveryBVO.CINSTORDOCID, cinstordocid);
  }

  public void setCinvoicecustid(String cinvoicecustid) {
    this.setAttributeValue(DeliveryBVO.CINVOICECUSTID, cinvoicecustid);
  }

  public void setCmaterialid(String cmaterialid) {
    this.setAttributeValue(DeliveryBVO.CMATERIALID, cmaterialid);
  }

  public void setCmaterialvid(String cmaterialvid) {
    this.setAttributeValue(DeliveryBVO.CMATERIALVID, cmaterialvid);
  }

  public void setCordercustid(String cordercustid) {
    this.setAttributeValue(DeliveryBVO.CORDERCUSTID, cordercustid);
  }

  /**
   * 设置原产地区
   * 
   * @param corigareaid 原产地区
   */
  public void setCorigareaid(String corigareaid) {
    this.setAttributeValue(DeliveryBVO.CORIGAREAID, corigareaid);
  }

  /**
   * 设置原产国
   * 
   * @param corigcountryid 原产国
   */
  public void setCorigcountryid(String corigcountryid) {
    this.setAttributeValue(DeliveryBVO.CORIGCOUNTRYID, corigcountryid);
  }

  public void setCorigcurrencyid(String corigcurrencyid) {
    this.setAttributeValue(DeliveryBVO.CORIGCURRENCYID, corigcurrencyid);
  }

  public void setCpriceformid(String cpriceformid) {
    this.setAttributeValue(DeliveryBVO.CPRICEFORMID, cpriceformid);
  }

  public void setCprodlineid(String cprodlineid) {
    this.setAttributeValue(DeliveryBVO.CPRODLINEID, cprodlineid);
  }

  public void setCproductorid(String cproductorid) {
    this.setAttributeValue(DeliveryBVO.CPRODUCTORID, cproductorid);
  }

  public void setCprofitcenterid(String cprofitcenterid) {
    this.setAttributeValue(DeliveryBVO.CPROFITCENTERID, cprofitcenterid);
  }

  public void setCprofitcentervid(String cprofitcentervid) {
    this.setAttributeValue(DeliveryBVO.CPROFITCENTERVID, cprofitcentervid);
  }

  public void setCprojectid(String cprojectid) {
    this.setAttributeValue(DeliveryBVO.CPROJECTID, cprojectid);
  }

  public void setCqtunitid(String cqtunitid) {
    this.setAttributeValue(DeliveryBVO.CQTUNITID, cqtunitid);
  }

  public void setCqualitylevelid(String cqualitylevelid) {
    this.setAttributeValue(DeliveryBVO.CQUALITYLEVELID, cqualitylevelid);
  }

  /**
   * 设置收货国家/地区
   * 
   * @param crececountryid 收货国家/地区
   */
  public void setCrececountryid(String crececountryid) {
    this.setAttributeValue(DeliveryBVO.CRECECOUNTRYID, crececountryid);
  }

  public void setCreceiveadddocid(String creceiveadddocid) {
    this.setAttributeValue(DeliveryBVO.CRECEIVEADDDOCID, creceiveadddocid);
  }

  public void setCreceiveaddrid(String creceiveaddrid) {
    this.setAttributeValue(DeliveryBVO.CRECEIVEADDRID, creceiveaddrid);
  }

  public void setCreceiveareaid(String creceiveareaid) {
    this.setAttributeValue(DeliveryBVO.CRECEIVEAREAID, creceiveareaid);
  }

  public void setCreceivecustid(String creceivecustid) {
    this.setAttributeValue(DeliveryBVO.CRECEIVECUSTID, creceivecustid);
  }

  public void setCreceivepersonid(String creceivepersonid) {
    this.setAttributeValue(DeliveryBVO.CRECEIVEPERSONID, creceivepersonid);
  }

  public void setCretreasonid(String cretreasonid) {
    this.setAttributeValue(DeliveryBVO.CRETREASONID, cretreasonid);
  }

  public void setCrowno(String crowno) {
    this.setAttributeValue(DeliveryBVO.CROWNO, crowno);
  }

  public void setCsaleorgid(String csaleorgid) {
    this.setAttributeValue(DeliveryBVO.CSALEORGID, csaleorgid);
  }

  public void setCsaleorgvid(String csaleorgvid) {
    this.setAttributeValue(DeliveryBVO.CSALEORGVID, csaleorgvid);
  }

  public void setCsendadddocid(String csendadddocid) {
    this.setAttributeValue(DeliveryBVO.CSENDADDDOCID, csendadddocid);
  }

  public void setCsendaddrid(String csendaddrid) {
    this.setAttributeValue(DeliveryBVO.CSENDADDRID, csendaddrid);
  }

  public void setCsendareaid(String csendareaid) {
    this.setAttributeValue(DeliveryBVO.CSENDAREAID, csendareaid);
  }

  /**
   * 设置发货国家/地区
   * 
   * @param csendcountryid 发货国家/地区
   */
  public void setCsendcountryid(String csendcountryid) {
    this.setAttributeValue(DeliveryBVO.CSENDCOUNTRYID, csendcountryid);
  }

  public void setCsendpersonid(String csendpersonid) {
    this.setAttributeValue(DeliveryBVO.CSENDPERSONID, csendpersonid);
  }

  public void setCsendstockorgid(String csendstockorgid) {
    this.setAttributeValue(DeliveryBVO.CSENDSTOCKORGID, csendstockorgid);
  }

  public void setCsendstockorgvid(String csendstockorgvid) {
    this.setAttributeValue(DeliveryBVO.CSENDSTOCKORGVID, csendstockorgvid);
  }

  public void setCsendstordocid(String csendstordocid) {
    this.setAttributeValue(DeliveryBVO.CSENDSTORDOCID, csendstordocid);
  }

  public void setCsettleorgid(String csettleorgid) {
    this.setAttributeValue(DeliveryBVO.CSETTLEORGID, csettleorgid);
  }

  public void setCsettleorgvid(String csettleorgvid) {
    this.setAttributeValue(DeliveryBVO.CSETTLEORGVID, csettleorgvid);
  }

  public void setCspaceid(String cspaceid) {
    this.setAttributeValue(DeliveryBVO.CSPACEID, cspaceid);
  }

  public void setCsrcbid(String csrcbid) {
    this.setAttributeValue(DeliveryBVO.CSRCBID, csrcbid);
  }

  public void setCsrcid(String csrcid) {
    this.setAttributeValue(DeliveryBVO.CSRCID, csrcid);
  }

  public void setCsupercargoid(String csupercargoid) {
    this.setAttributeValue(DeliveryBVO.CSUPERCARGOID, csupercargoid);
  }

  /**
   * 设置税码
   * 
   * @param ctaxcodeid 税码
   */
  public void setCtaxcodeid(String ctaxcodeid) {
    this.setAttributeValue(DeliveryBVO.CTAXCODEID, ctaxcodeid);
  }

  /**
   * 设置报税国家/地区
   * 
   * @param ctaxcountryid 报税国家/地区
   */
  public void setCtaxcountryid(String ctaxcountryid) {
    this.setAttributeValue(DeliveryBVO.CTAXCOUNTRYID, ctaxcountryid);
  }

  public void setCtranscustid(String ctranscustid) {
    this.setAttributeValue(DeliveryBVO.CTRANSCUSTID, ctranscustid);
  }

  public void setCunitid(String cunitid) {
    this.setAttributeValue(DeliveryBVO.CUNITID, cunitid);
  }

  public void setCvehicleid(String cvehicleid) {
    this.setAttributeValue(DeliveryBVO.CVEHICLEID, cvehicleid);
  }

  public void setCvehicletypeid(String cvehicletypeid) {
    this.setAttributeValue(DeliveryBVO.CVEHICLETYPEID, cvehicletypeid);
  }

  public void setCvendorid(String cvendorid) {
    this.setAttributeValue(DeliveryBVO.CVENDORID, cvendorid);
  }

  public void setDbilldate(UFDate dbilldate) {
    this.setAttributeValue(DeliveryBVO.DBILLDATE, dbilldate);
  }

  public void setDr(Integer dr) {
    this.setAttributeValue(DeliveryBVO.DR, dr);
  }

  public void setDreceivedate(UFDate receivedate) {
    this.setAttributeValue(DeliveryBVO.DRECEIVEDATE, receivedate);
  }

  public void setDsenddate(UFDate dsenddate) {
    this.setAttributeValue(DeliveryBVO.DSENDDATE, dsenddate);
  }

  /**
   * 设置购销类型
   * 
   * @param fbuysellflag 购销类型
   */
  public void setFbuysellflag(Integer fbuysellflag) {
    this.setAttributeValue(DeliveryBVO.FBUYSELLFLAG, fbuysellflag);
  }

  public void setFrownote(String frownote) {
    this.setAttributeValue(DeliveryBVO.FROWNOTE, frownote);
  }

  /**
   * 设置扣税类别
   * 
   * @param ftaxtypeflag 扣税类别
   */
  public void setFtaxtypeflag(Integer ftaxtypeflag) {
    this.setAttributeValue(DeliveryBVO.FTAXTYPEFLAG, ftaxtypeflag);
  }

  public void setNastnum(UFDouble nastnum) {
    this.setAttributeValue(DeliveryBVO.NASTNUM, nastnum);
  }

  /**
   * 设置计税金额
   * 
   * @param ncaltaxmny 计税金额
   */
  public void setNcaltaxmny(UFDouble ncaltaxmny) {
    this.setAttributeValue(DeliveryBVO.NCALTAXMNY, ncaltaxmny);
  }

  public void setNdiscount(UFDouble ndiscount) {
    this.setAttributeValue(DeliveryBVO.NDISCOUNT, ndiscount);
  }

  public void setNdiscountrate(UFDouble ndiscountrate) {
    this.setAttributeValue(DeliveryBVO.NDISCOUNTRATE, ndiscountrate);
  }

  public void setNexchangerate(UFDouble nexchangerate) {
    this.setAttributeValue(DeliveryBVO.NEXCHANGERATE, nexchangerate);
  }

  public void setNglobalexchgrate(UFDouble nglobalexchgrate) {
    this.setAttributeValue(DeliveryBVO.NGLOBALEXCHGRATE, nglobalexchgrate);
  }

  public void setNglobalmny(UFDouble nglobalmny) {
    this.setAttributeValue(DeliveryBVO.NGLOBALMNY, nglobalmny);
  }

  public void setNglobaltaxmny(UFDouble nglobaltaxmny) {
    this.setAttributeValue(DeliveryBVO.NGLOBALTAXMNY, nglobaltaxmny);
  }

  public void setNgroupexchgrate(UFDouble ngroupexchgrate) {
    this.setAttributeValue(DeliveryBVO.NGROUPEXCHGRATE, ngroupexchgrate);
  }

  public void setNgroupmny(UFDouble ngroupmny) {
    this.setAttributeValue(DeliveryBVO.NGROUPMNY, ngroupmny);
  }

  public void setNgrouptaxmny(UFDouble ngrouptaxmny) {
    this.setAttributeValue(DeliveryBVO.NGROUPTAXMNY, ngrouptaxmny);
  }

  public void setNitemdiscountrate(UFDouble nitemdiscountrate) {
    this.setAttributeValue(DeliveryBVO.NITEMDISCOUNTRATE, nitemdiscountrate);
  }

  public void setNmny(UFDouble nmny) {
    this.setAttributeValue(DeliveryBVO.NMNY, nmny);
  }

  public void setNnetprice(UFDouble nnetprice) {
    this.setAttributeValue(DeliveryBVO.NNETPRICE, nnetprice);
  }

  public void setNnum(UFDouble nnum) {
    this.setAttributeValue(DeliveryBVO.NNUM, nnum);
  }

  public void setNorigdiscount(UFDouble norigdiscount) {
    this.setAttributeValue(DeliveryBVO.NORIGDISCOUNT, norigdiscount);
  }

  public void setNorigmny(UFDouble norigmny) {
    this.setAttributeValue(DeliveryBVO.NORIGMNY, norigmny);
  }

  public void setNorignetprice(UFDouble norignetprice) {
    this.setAttributeValue(DeliveryBVO.NORIGNETPRICE, norignetprice);
  }

  public void setNorigprice(UFDouble norigprice) {
    this.setAttributeValue(DeliveryBVO.NORIGPRICE, norigprice);
  }

  public void setNorigtaxmny(UFDouble norigtaxmny) {
    this.setAttributeValue(DeliveryBVO.NORIGTAXMNY, norigtaxmny);
  }

  public void setNorigtaxnetprice(UFDouble norigtaxnetprice) {
    this.setAttributeValue(DeliveryBVO.NORIGTAXNETPRICE, norigtaxnetprice);
  }

  public void setNorigtaxprice(UFDouble norigtaxprice) {
    this.setAttributeValue(DeliveryBVO.NORIGTAXPRICE, norigtaxprice);
  }

  public void setNpiece(UFDouble npiece) {
    this.setAttributeValue(DeliveryBVO.NPIECE, npiece);
  }

  public void setNprice(UFDouble nprice) {
    this.setAttributeValue(DeliveryBVO.NPRICE, nprice);
  }

  public void setNqtnetprice(UFDouble nqtnetprice) {
    this.setAttributeValue(DeliveryBVO.NQTNETPRICE, nqtnetprice);
  }

  public void setNqtorignetprice(UFDouble nqtorignetprice) {
    this.setAttributeValue(DeliveryBVO.NQTORIGNETPRICE, nqtorignetprice);
  }

  public void setNqtorigprice(UFDouble nqtorigprice) {
    this.setAttributeValue(DeliveryBVO.NQTORIGPRICE, nqtorigprice);
  }

  public void setNqtorigtaxnetprc(UFDouble nqtorigtaxnetprc) {
    this.setAttributeValue(DeliveryBVO.NQTORIGTAXNETPRC, nqtorigtaxnetprc);
  }

  public void setNqtorigtaxprice(UFDouble nqtorigtaxprice) {
    this.setAttributeValue(DeliveryBVO.NQTORIGTAXPRICE, nqtorigtaxprice);
  }

  public void setNqtprice(UFDouble nqtprice) {
    this.setAttributeValue(DeliveryBVO.NQTPRICE, nqtprice);
  }

  public void setNqttaxnetprice(UFDouble nqttaxnetprice) {
    this.setAttributeValue(DeliveryBVO.NQTTAXNETPRICE, nqttaxnetprice);
  }

  public void setNqttaxprice(UFDouble nqttaxprice) {
    this.setAttributeValue(DeliveryBVO.NQTTAXPRICE, nqttaxprice);
  }

  public void setNqtunitnum(UFDouble nqtunitnum) {
    this.setAttributeValue(DeliveryBVO.NQTUNITNUM, nqtunitnum);
  }

  public void setNreqrsnum(UFDouble nreqrsnum) {
    this.setAttributeValue(DeliveryBVO.NREQRSNUM, nreqrsnum);
  }

  public void setNtax(UFDouble ntax) {
    this.setAttributeValue(DeliveryBVO.NTAX, ntax);
  }

  public void setNtaxmny(UFDouble ntaxmny) {
    this.setAttributeValue(DeliveryBVO.NTAXMNY, ntaxmny);
  }

  public void setNtaxnetprice(UFDouble ntaxnetprice) {
    this.setAttributeValue(DeliveryBVO.NTAXNETPRICE, ntaxnetprice);
  }

  public void setNtaxprice(UFDouble ntaxprice) {
    this.setAttributeValue(DeliveryBVO.NTAXPRICE, ntaxprice);
  }

  public void setNtaxrate(UFDouble ntaxrate) {
    this.setAttributeValue(DeliveryBVO.NTAXRATE, ntaxrate);
  }

  public void setNtotalarnum(UFDouble ntotalarnum) {
    this.setAttributeValue(DeliveryBVO.NTOTALARNUM, ntotalarnum);
  }

  public void setNtotalelignum(UFDouble ntotalelignum) {
    this.setAttributeValue(DeliveryBVO.NTOTALELIGNUM, ntotalelignum);
  }

  public void setNtotalestarnum(UFDouble ntotalestarnum) {
    this.setAttributeValue(DeliveryBVO.NTOTALESTARNUM, ntotalestarnum);
  }

  public void setNtotalnotoutnum(UFDouble ntotalnotoutnum) {
    this.setAttributeValue(DeliveryBVO.NTOTALNOTOUTNUM, ntotalnotoutnum);
  }

  public void setNtotaloutnum(UFDouble ntotaloutnum) {
    this.setAttributeValue(DeliveryBVO.NTOTALOUTNUM, ntotaloutnum);
  }

  public void setNtotalreportnum(UFDouble ntotalreportnum) {
    this.setAttributeValue(DeliveryBVO.NTOTALREPORTNUM, ntotalreportnum);
  }

  public void setNtotalrushnum(UFDouble ntotalrushnum) {
    this.setAttributeValue(DeliveryBVO.NTOTALRUSHNUM, ntotalrushnum);
  }

  public void setNtotaltransnum(UFDouble ntotaltransnum) {
    this.setAttributeValue(DeliveryBVO.NTOTALTRANSNUM, ntotaltransnum);
  }

  public void setNtotalunelignum(UFDouble ntotalunelignum) {
    this.setAttributeValue(DeliveryBVO.NTOTALUNELIGNUM, ntotalunelignum);
  }

  public void setNtranslossnum(UFDouble ntranslossnum) {
    this.setAttributeValue(DeliveryBVO.NTRANSLOSSNUM, ntranslossnum);
  }

  public void setNvolume(UFDouble nvolume) {
    this.setAttributeValue(DeliveryBVO.NVOLUME, nvolume);
  }

  public void setNweight(UFDouble nweight) {
    this.setAttributeValue(DeliveryBVO.NWEIGHT, nweight);
  }

  public void setPk_batchcode(String pk_batchcode) {
    this.setAttributeValue(DeliveryBVO.PK_BATCHCODE, pk_batchcode);
  }

  public void setPk_group(String pk_group) {
    this.setAttributeValue(DeliveryBVO.PK_GROUP, pk_group);
  }

  public void setPk_org(String pk_org) {
    this.setAttributeValue(DeliveryBVO.PK_ORG, pk_org);
  }

  /**
   * 设置来源时间戳
   * 
   * @param ts 时间戳
   */
  public void setSrcbts(UFDateTime srcbts) {
    this.setAttributeValue(DeliveryBVO.SRCBTS, srcbts);
  }

  /**
   * 设置来源时间戳
   * 
   * @param ts 时间戳
   */
  public void setSrcts(UFDateTime srcts) {
    this.setAttributeValue(DeliveryBVO.SRCTS, srcts);
  }

  public void setTs(UFDateTime ts) {
    this.setAttributeValue(DeliveryBVO.TS, ts);
  }

  public void setTts(UFDateTime tts) {
    this.setAttributeValue(DeliveryBVO.TTS, tts);
  }

  public void setVbatchcode(String vbatchcode) {
    this.setAttributeValue(DeliveryBVO.VBATCHCODE, vbatchcode);
  }

  public void setVbdef1(String vbdef1) {
    this.setAttributeValue(DeliveryBVO.VBDEF1, vbdef1);
  }

  public void setVbdef10(String vbdef10) {
    this.setAttributeValue(DeliveryBVO.VBDEF10, vbdef10);
  }

  public void setVbdef11(String vbdef11) {
    this.setAttributeValue(DeliveryBVO.VBDEF11, vbdef11);
  }

  public void setVbdef12(String vbdef12) {
    this.setAttributeValue(DeliveryBVO.VBDEF12, vbdef12);
  }

  public void setVbdef13(String vbdef13) {
    this.setAttributeValue(DeliveryBVO.VBDEF13, vbdef13);
  }

  public void setVbdef14(String vbdef14) {
    this.setAttributeValue(DeliveryBVO.VBDEF14, vbdef14);
  }

  public void setVbdef15(String vbdef15) {
    this.setAttributeValue(DeliveryBVO.VBDEF15, vbdef15);
  }

  public void setVbdef16(String vbdef16) {
    this.setAttributeValue(DeliveryBVO.VBDEF16, vbdef16);
  }

  public void setVbdef17(String vbdef17) {
    this.setAttributeValue(DeliveryBVO.VBDEF17, vbdef17);
  }

  public void setVbdef18(String vbdef18) {
    this.setAttributeValue(DeliveryBVO.VBDEF18, vbdef18);
  }

  public void setVbdef19(String vbdef19) {
    this.setAttributeValue(DeliveryBVO.VBDEF19, vbdef19);
  }

  public void setVbdef2(String vbdef2) {
    this.setAttributeValue(DeliveryBVO.VBDEF2, vbdef2);
  }

  public void setVbdef20(String vbdef20) {
    this.setAttributeValue(DeliveryBVO.VBDEF20, vbdef20);
  }

  public void setVbdef3(String vbdef3) {
    this.setAttributeValue(DeliveryBVO.VBDEF3, vbdef3);
  }

  public void setVbdef4(String vbdef4) {
    this.setAttributeValue(DeliveryBVO.VBDEF4, vbdef4);
  }

  public void setVbdef5(String vbdef5) {
    this.setAttributeValue(DeliveryBVO.VBDEF5, vbdef5);
  }

  public void setVbdef6(String vbdef6) {
    this.setAttributeValue(DeliveryBVO.VBDEF6, vbdef6);
  }

  public void setVbdef7(String vbdef7) {
    this.setAttributeValue(DeliveryBVO.VBDEF7, vbdef7);
  }

  public void setVbdef8(String vbdef8) {
    this.setAttributeValue(DeliveryBVO.VBDEF8, vbdef8);
  }

  public void setVbdef9(String vbdef9) {
    this.setAttributeValue(DeliveryBVO.VBDEF9, vbdef9);
  }

  public void setVchangerate(String vchangerate) {
    this.setAttributeValue(DeliveryBVO.VCHANGERATE, vchangerate);
  }

  public void setVfirstbilldate(UFDate vfirstbilldate) {
    this.setAttributeValue(DeliveryBVO.VFIRSTBILLDATE, vfirstbilldate);
  }

  public void setVfirstcode(String vfirstcode) {
    this.setAttributeValue(DeliveryBVO.VFIRSTCODE, vfirstcode);
  }

  public void setVfirstrowno(String vfirstrowno) {
    this.setAttributeValue(DeliveryBVO.VFIRSTROWNO, vfirstrowno);
  }

  public void setVfirsttrantype(String vfirsttrantype) {
    this.setAttributeValue(DeliveryBVO.VFIRSTTRANTYPE, vfirsttrantype);
  }

  public void setVfirsttype(String vfirsttype) {
    this.setAttributeValue(DeliveryBVO.VFIRSTTYPE, vfirsttype);
  }

  public void setVfree1(String vfree1) {
    this.setAttributeValue(DeliveryBVO.VFREE1, vfree1);
  }

  public void setVfree10(String vfree10) {
    this.setAttributeValue(DeliveryBVO.VFREE10, vfree10);
  }

  public void setVfree2(String vfree2) {
    this.setAttributeValue(DeliveryBVO.VFREE2, vfree2);
  }

  public void setVfree3(String vfree3) {
    this.setAttributeValue(DeliveryBVO.VFREE3, vfree3);
  }

  public void setVfree4(String vfree4) {
    this.setAttributeValue(DeliveryBVO.VFREE4, vfree4);
  }

  public void setVfree5(String vfree5) {
    this.setAttributeValue(DeliveryBVO.VFREE5, vfree5);
  }

  public void setVfree6(String vfree6) {
    this.setAttributeValue(DeliveryBVO.VFREE6, vfree6);
  }

  public void setVfree7(String vfree7) {
    this.setAttributeValue(DeliveryBVO.VFREE7, vfree7);
  }

  public void setVfree8(String vfree8) {
    this.setAttributeValue(DeliveryBVO.VFREE8, vfree8);
  }

  public void setVfree9(String vfree9) {
    this.setAttributeValue(DeliveryBVO.VFREE9, vfree9);
  }

  public void setVqtunitrate(String vqtunitrate) {
    this.setAttributeValue(DeliveryBVO.VQTUNITRATE, vqtunitrate);
  }

  public void setVreceivetel(String vreceivetel) {
    this.setAttributeValue(DeliveryBVO.VRECEIVETEL, vreceivetel);
  }

  public void setVreturnmode(String vreturnmode) {
    this.setAttributeValue(DeliveryBVO.VRETURNMODE, vreturnmode);
  }

  public void setVsendtel(String vsendtel) {
    this.setAttributeValue(DeliveryBVO.VSENDTEL, vsendtel);
  }

  public void setVsrccode(String vsrccode) {
    this.setAttributeValue(DeliveryBVO.VSRCCODE, vsrccode);
  }

  public void setVsrcrowno(String vsrcrowno) {
    this.setAttributeValue(DeliveryBVO.VSRCROWNO, vsrcrowno);
  }

  public void setVsrctrantype(String vsrctrantype) {
    this.setAttributeValue(DeliveryBVO.VSRCTRANTYPE, vsrctrantype);
  }

  public void setVsrctype(String vsrctype) {
    this.setAttributeValue(DeliveryBVO.VSRCTYPE, vsrctype);
  }

  /**
   * 设置发货利润中心
   * 
   * @param csprofitcentervid 发货利润中心
   */
  public void setCsprofitcentervid(String csprofitcentervid) {
    this.setAttributeValue(DeliveryBVO.CSPROFITCENTERVID, csprofitcentervid);
  }

  /**
   * 设置发货利润中心最新版本
   * 
   * @param csprofitcenterid 发货利润中心最新版本
   */
  public void setCsprofitcenterid(String csprofitcenterid) {
    this.setAttributeValue(DeliveryBVO.CSPROFITCENTERID, csprofitcenterid);
  }

  /**
   * 设置收货利润中心
   * 
   * @param crprofitcentervid 收货利润中心
   */
  public void setCrprofitcentervid(String crprofitcentervid) {
    this.setAttributeValue(DeliveryBVO.CRPROFITCENTERVID, crprofitcentervid);
  }

  /**
   * 设置收货利润中心最新版本
   * 
   * @param crprofitcenterid 收货利润中心最新版本
   */
  public void setCrprofitcenterid(String crprofitcenterid) {
    this.setAttributeValue(DeliveryBVO.CRPROFITCENTERID, crprofitcenterid);
  }
  
  /**
   * 获取特征码
   * 
   * @return 特征码
   */
  public String  getCmffileid() {
    return (String) this.getAttributeValue(DeliveryBVO.CMFFILEID);
  }

  /**
   * 设置特征码
   * 
   * @param 特征码
   */
  public void setCmffileid(String cmffileid) {
    this.setAttributeValue(DeliveryBVO.CMFFILEID, cmffileid);
  }
  
}
