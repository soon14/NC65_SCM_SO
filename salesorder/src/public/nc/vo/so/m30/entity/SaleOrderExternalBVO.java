package nc.vo.so.m30.entity;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

/**
 * 销售订单附表VO
 * 
 * @since 6.3
 * @version 2013-5-24 上午09:55:17
 * @author dongli2
 */
public class SaleOrderExternalBVO extends SuperVO {

  /**
   * 序列化
   */
  private static final long serialVersionUID = -8535812509240954618L;

  @Override
  public IVOMeta getMetaData() {
    IVOMeta meta = VOMetaFactory.getInstance().getVOMeta("so.so_saleorder_b");
    return meta;
  }

  /**
   * 客户物料编码
   */
  public static final String CCUSTMATERIALID = "ccustmaterialid";

  /**
   * 是否货源安排完毕
   */
  public static final String BARRANGEDFLAG = "barrangedflag";

  /**
   * 收入结算关闭
   */
  public static final String BBARSETTLEFLAG = "bbarsettleflag";

  /**
   * 成本结算关闭
   */
  public static final String BBCOSTSETTLEFLAG = "bbcostsettleflag";

  /**
   * 捆绑存货
   */
  public static final String BBINDFLAG = "bbindflag";

  /**
   * 开票关闭
   */
  public static final String BBINVOICENDFLAG = "bbinvoicendflag";

  /**
   * 出库关闭
   */
  public static final String BBOUTENDFLAG = "bboutendflag";

  /**
   * 发货关闭
   */
  public static final String BBSENDENDFLAG = "bbsendendflag";

  /**
   * bbsettleendflag
   */
  public static final String BBSETTLEENDFLAG = "bbsettleendflag";

  /**
   * 折扣类
   */
  public static final String BDISCOUNTFLAG = "bdiscountflag";

  /**
   * 借出转销售
   */
  public static final String BJCZXSFLAG = "bjczxsflag";

  /**
   * 服务类
   */
  public static final String BLABORFLAG = "blaborflag";

  /**
   * 赠品
   */
  public static final String BLARGESSFLAG = "blargessflag";

  /**
   * bprerowcloseflag
   */
  public static final String BPREROWCLOSEFLAG = "bprerowcloseflag";

  /**
   * 三角贸易
   */
  public static final String BTRIATRADEFLAG = "btriatradeflag";

  /**
   * 应收组织最新版本
   */
  public static final String CARORGID = "carorgid";

  /**
   * 应收组织
   */
  public static final String CARORGVID = "carorgvid";

  /**
   * 最后货源安排人
   */
  public static final String CARRANGEPERSONID = "carrangepersonid";

  /**
   * 单位
   */
  public static final String CASTUNITID = "castunitid";

  /**
   * 捆绑件对应来源订单行ID
   */
  public static final String CBINDSRCID = "cbindsrcid";

  /**
   * 品牌
   */
  public static final String CBRANDID = "cbrandid";

  /**
   * 合同子表
   */
  public static final String CCTMANAGEBID = "cctmanagebid";

  /**
   * 合同主表
   */
  public static final String CCTMANAGEID = "cctmanageid";

  /**
   * 本位币
   */
  public static final String CCURRENCYID = "ccurrencyid";

  /**
   * 换货行对应退货行
   */
  public static final String CEXCHANGESRCRETID = "cexchangesrcretid";

  /**
   * 工厂
   */
  public static final String CFACTORYID = "cfactoryid";

  /**
   * 源头单据子表
   */
  public static final String CFIRSTBID = "cfirstbid";

  /**
   * 源头单据主表
   */
  public static final String CFIRSTID = "cfirstid";

  /**
   * 赠品行对应来源订单行ID
   */
  public static final String CLARGESSSRCID = "clargesssrcid";

  /**
   * 物料最新版本
   */
  public static final String CMATERIALID = "cmaterialid";

  /**
   * 物料编码
   */
  public static final String CMATERIALVID = "cmaterialvid";

  /**
   * 原产地区
   */
  public static final String CORIGAREAID = "corigareaid";

  /**
   * 原产国
   */
  public static final String CORIGCOUNTRYID = "corigcountryid";

  /**
   * 价格组成
   */
  public static final String CPRICEFORMID = "cpriceformid";

  /**
   * 价格项目
   */
  public static final String CPRICEITEMID = "cpriceitemid";

  /**
   * 价目表
   */
  public static final String CPRICEITEMTABLEID = "cpriceitemtableid";

  /**
   * 价格政策
   */
  public static final String CPRICEPOLICYID = "cpricepolicyid";

  /**
   * 产品线
   */
  public static final String CPRODLINEID = "cprodlineid";

  /**
   * 生产厂商
   */
  public static final String CPRODUCTORID = "cproductorid";

  /**
   * 利润中心最新版本
   */
  public static final String CPROFITCENTERID = "cprofitcenterid";

  /**
   * 利润中心
   */
  public static final String CPROFITCENTERVID = "cprofitcentervid";

  /**
   * 项目
   */
  public static final String CPROJECTID = "cprojectid";

  /**
   * 报价单位
   */
  public static final String CQTUNITID = "cqtunitid";

  /**
   * 质量等级
   */
  public static final String CQUALITYLEVELID = "cqualitylevelid";

  /**
   * 收货国家/地区
   */
  public static final String CRECECOUNTRYID = "crececountryid";

  /**
   * 收货地点
   */
  public static final String CRECEIVEADDDOCID = "creceiveadddocid";

  /**
   * 收货地址
   */
  public static final String CRECEIVEADDRID = "creceiveaddrid";

  /**
   * 收货地区
   */
  public static final String CRECEIVEAREAID = "creceiveareaid";

  /**
   * 收货客户
   */
  public static final String CRECEIVECUSTID = "creceivecustid";

  /**
   * 退货政策
   */
  public static final String CRETPOLICYID = "cretpolicyid";

  /**
   * 退货原因
   */
  public static final String CRETREASONID = "cretreasonid";

  /**
   * 行号
   */
  public static final String CROWNO = "crowno";

  /**
   * 销售订单附表
   */
  public static final String CSALEORDERBID = "csaleorderbid";

  /**
   * 销售订单主表_主键
   */
  public static final String CSALEORDERID = "csaleorderid";

  /**
   * 发货国家/地区
   */
  public static final String CSENDCOUNTRYID = "csendcountryid";

  /**
   * 发货库存组织最新版本
   */
  public static final String CSENDSTOCKORGID = "csendstockorgid";

  /**
   * 发货库存组织
   */
  public static final String CSENDSTOCKORGVID = "csendstockorgvid";

  /**
   * 发货仓库
   */
  public static final String CSENDSTORDOCID = "csendstordocid";

  /**
   * 结算财务组织最新版本
   */
  public static final String CSETTLEORGID = "csettleorgid";

  /**
   * 结算财务组织
   */
  public static final String CSETTLEORGVID = "csettleorgvid";

  /**
   * 来源单据附表
   */
  public static final String CSRCBID = "csrcbid";

  /**
   * 来源单据主表
   */
  public static final String CSRCID = "csrcid";

  /**
   * 税码
   */
  public static final String CTAXCODEID = "ctaxcodeid";

  /**
   * 报税国家/地区
   */
  public static final String CTAXCOUNTRYID = "ctaxcountryid";

  /**
   * 物流组织最新版本
   */
  public static final String CTRAFFICORGID = "ctrafficorgid";

  /**
   * 物流组织
   */
  public static final String CTRAFFICORGVID = "ctrafficorgvid";

  /**
   * 主单位
   */
  public static final String CUNITID = "cunitid";

  /**
   * 供应商
   */
  public static final String CVENDORID = "cvendorid";

  /**
   * 单据日期
   */
  public static final String DBILLDATE = "dbilldate";

  /**
   * dr
   */
  public static final String DR = "dr";

  /**
   * 到货日期
   */
  public static final String DRECEIVEDATE = "dreceivedate";

  /**
   * 发货日期
   */
  public static final String DSENDDATE = "dsenddate";

  /**
   * 购销类型
   */
  public static final String FBUYSELLFLAG = "fbuysellflag";

  /**
   * 赠品价格分摊方式
   */
  public static final String FLARGESSTYPEFLAG = "flargesstypeflag";

  /**
   * 退换货标记
   */
  public static final String FRETEXCHANGE = "fretexchange";

  /**
   * 行状态
   */
  public static final String FROWSTATUS = "frowstatus";

  /**
   * 扣税类别
   */
  public static final String FTAXTYPEFLAG = "ftaxtypeflag";

  public static final String METAPATH = "so_saleorder_b.";

  /**
   * 累计安排生产订单数量
   */
  public static final String NARRANGEMONUM = "narrangemonum";

  /**
   * 累计安排请购单数量
   */
  public static final String NARRANGEPOAPPNUM = "narrangepoappnum";

  /**
   * 累计安排采购订单数量
   */
  public static final String NARRANGEPONUM = "narrangeponum";

  /**
   * 累计安排委外订单数量
   */
  public static final String NARRANGESCORNUM = "narrangescornum";

  /**
   * 累计安排调拨申请数量
   */
  public static final String NARRANGETOAPPNUM = "narrangetoappnum";

  /**
   * 累计安排调拨订单数量
   */
  public static final String NARRANGETOORNUM = "narrangetoornum";

  /**
   * 询价原币无税净价
   */
  public static final String NASKQTORIGNETPRICE = "naskqtorignetprice";

  /**
   * 询价原币无税单价
   */
  public static final String NASKQTORIGPRICE = "naskqtorigprice";

  /**
   * 询价原币含税单价
   */
  public static final String NASKQTORIGTAXPRC = "naskqtorigtaxprc";

  /**
   * 询价原币含税净价
   */
  public static final String NASKQTORIGTXNTPRC = "naskqtorigtxntprc";

  /**
   * 数量
   */
  public static final String NASTNUM = "nastnum";

  /**
   * 冲减前金额
   */
  public static final String NBFORIGSUBMNY = "nbforigsubmny";

  /**
   * 计税金额
   */
  public static final String NCALTAXMNY = "ncaltaxmny";

  /**
   * 本币折扣额
   */
  public static final String NDISCOUNT = "ndiscount";

  /**
   * 整单折扣
   */
  public static final String NDISCOUNTRATE = "ndiscountrate";

  /**
   * 折本汇率
   */
  public static final String NEXCHANGERATE = "nexchangerate";

  /**
   * 全局本位币汇率
   */
  public static final String NGLOBALEXCHGRATE = "nglobalexchgrate";

  /**
   * 全局本币无税金额
   */
  public static final String NGLOBALMNY = "nglobalmny";

  /**
   * 全局本币价税合计
   */
  public static final String NGLOBALTAXMNY = "nglobaltaxmny";

  /**
   * 集团本位币汇率
   */
  public static final String NGROUPEXCHGRATE = "ngroupexchgrate";

  /**
   * 集团本币无税金额
   */
  public static final String NGROUPMNY = "ngroupmny";

  /**
   * 集团本币价税合计
   */
  public static final String NGROUPTAXMNY = "ngrouptaxmny";

  /**
   * ninvoiceauditnum
   */
  public static final String NINVOICEAUDITNUM = "ninvoiceauditnum";

  /**
   * ninvunfinisednum
   */
  public static final String NINVUNFINISEDNUM = "ninvunfinisednum";

  /**
   * 单品折扣率
   */
  public static final String NITEMDISCOUNTRATE = "nitemdiscountrate";

  /**
   * 赠品价格分摊前无税金额
   */
  public static final String NLARGESSMNY = "nlargessmny";

  /**
   * 赠品价格分摊前价税合计
   */
  public static final String NLARGESSTAXMNY = "nlargesstaxmny";

  /**
   * nlossnotauditnum
   */
  public static final String NLOSSNOTAUDITNUM = "nlossnotauditnum";

  /**
   * 本币无税金额
   */
  public static final String NMNY = "nmny";

  /**
   * 主本币无税净价
   */
  public static final String NNETPRICE = "nnetprice";

  /**
   * nnotarnum
   */
  public static final String NNOTARNUM = "nnotarnum";

  /**
   * nnotcostnum
   */
  public static final String NNOTCOSTNUM = "nnotcostnum";

  /**
   * 主数量
   */
  public static final String NNUM = "nnum";

  /**
   * 折扣额
   */
  public static final String NORIGDISCOUNT = "norigdiscount";

  /**
   * 无税金额
   */
  public static final String NORIGMNY = "norigmny";

  /**
   * 主无税净价
   */
  public static final String NORIGNETPRICE = "norignetprice";

  /**
   * 主无税单价
   */
  public static final String NORIGPRICE = "norigprice";

  /**
   * 累计冲抵金额
   */
  public static final String NORIGSUBMNY = "norigsubmny";

  /**
   * 价税合计
   */
  public static final String NORIGTAXMNY = "norigtaxmny";

  /**
   * 税额
   */
  // public static final String NORIGTAX = "norigtax";

  /**
   * 主含税净价
   */
  public static final String NORIGTAXNETPRICE = "norigtaxnetprice";

  /**
   * 主含税单价
   */
  public static final String NORIGTAXPRICE = "norigtaxprice";

  /**
   * noutauditnum
   */
  public static final String NOUTAUDITNUM = "noutauditnum";

  /**
   * noutnotauditnum
   */
  public static final String NOUTNOTAUDITNUM = "noutnotauditnum";

  /**
   * noutunfinisednum
   */
  public static final String NOUTUNFINISEDNUM = "noutunfinisednum";

  /**
   * 件数
   */
  public static final String NPIECE = "npiece";

  /**
   * 主本币无税单价
   */
  public static final String NPRICE = "nprice";

  /**
   * 本币无税净价
   */
  public static final String NQTNETPRICE = "nqtnetprice";

  /**
   * 无税净价
   */
  public static final String NQTORIGNETPRICE = "nqtorignetprice";

  /**
   * 无税单价
   */
  public static final String NQTORIGPRICE = "nqtorigprice";

  /**
   * 含税净价
   */
  public static final String NQTORIGTAXNETPRC = "nqtorigtaxnetprc";

  /**
   * 含税单价
   */
  public static final String NQTORIGTAXPRICE = "nqtorigtaxprice";

  /**
   * 本币无税单价
   */
  public static final String NQTPRICE = "nqtprice";

  /**
   * 本币含税净价
   */
  public static final String NQTTAXNETPRICE = "nqttaxnetprice";

  /**
   * 本币含税单价
   */
  public static final String NQTTAXPRICE = "nqttaxprice";

  /**
   * 报价单位数量
   */
  public static final String NQTUNITNUM = "nqtunitnum";

  /**
   * 预留数量
   */
  public static final String NREQRSNUM = "nreqrsnum";

  /**
   * nsendauditnum
   */
  public static final String NSENDAUDITNUM = "nsendauditnum";

  /**
   * nsendunfinisednum
   */
  public static final String NSENDUNFINISEDNUM = "nsendunfinisednum";

  /**
   * 本币税额
   */
  public static final String NTAX = "ntax";

  /**
   * 本币价税合计
   */
  public static final String NTAXMNY = "ntaxmny";

  /**
   * 主本币含税净价
   */
  public static final String NTAXNETPRICE = "ntaxnetprice";

  /**
   * 主本币含税单价
   */
  public static final String NTAXPRICE = "ntaxprice";

  /**
   * 税率
   */
  public static final String NTAXRATE = "ntaxrate";

  /**
   * 累计确认应收金额
   */
  public static final String NTOTALARMNY = "ntotalarmny";

  /**
   * 累计确认应收数量
   */
  public static final String NTOTALARNUM = "ntotalarnum";

  /**
   * 累计成本结算数量
   */
  public static final String NTOTALCOSTNUM = "ntotalcostnum";

  /**
   * 累计暂估应收金额
   */
  public static final String NTOTALESTARMNY = "ntotalestarmny";

  /**
   * 累计暂估应收数量
   */
  public static final String NTOTALESTARNUM = "ntotalestarnum";

  /**
   * 累计开票数量
   */
  public static final String NTOTALINVOICENUM = "ntotalinvoicenum";

  /**
   * 累计应发未出库数量
   */
  public static final String NTOTALNOTOUTNUM = "ntotalnotoutnum";

  /**
   * 累计出库数量
   */
  public static final String NTOTALOUTNUM = "ntotaloutnum";

  /**
   * 累计财务核销金额
   */
  public static final String NTOTALPAYMNY = "ntotalpaymny";

  /**
   * 累计生成计划订单数量
   */
  public static final String NTOTALPLONUM = "ntotalplonum";

  /**
   * 累计退货数量
   */
  public static final String NTOTALRETURNNUM = "ntotalreturnnum";

  /**
   * 累计出库对冲数量
   */
  public static final String NTOTALRUSHNUM = "ntotalrushnum";

  /**
   * 累计发货数量
   */
  public static final String NTOTALSENDNUM = "ntotalsendnum";

  /**
   * 累计签收数量
   */
  public static final String NTOTALSIGNNUM = "ntotalsignnum";

  /**
   * 累计发出商品数量
   */
  public static final String NTOTALTRADENUM = "ntotaltradenum";

  /**
   * 累计途损数量
   */
  public static final String NTRANSLOSSNUM = "ntranslossnum";

  /**
   * 体积
   */
  public static final String NVOLUME = "nvolume";

  /**
   * 重量
   */
  public static final String NWEIGHT = "nweight";

  /**
   * 批次档案
   */
  public static final String PK_BATCHCODE = "pk_batchcode";

  /**
   * 集团
   */
  public static final String PK_GROUP = "pk_group";

  /**
   * 销售组织
   */
  public static final String PK_ORG = "pk_org";

  /**
   * srcbts（不允许编辑）
   */
  public static final String SRCBTS = "srcbts";

  /**
   * srcorgid（不允许编辑）
   */
  public static final String SRCORGID = "srcorgid";

  /**
   * srcts（不允许编辑）
   */
  public static final String SRCTS = "srcts";

  /**
   * 最后货源安排时间
   */
  public static final String TLASTARRANGETIME = "tlastarrangetime";

  /**
   * 时间戳
   */
  public static final String TS = "ts";

  /**
   * 批次
   */
  public static final String VBATCHCODE = "vbatchcode";

  /**
   * 自定义项1
   */
  public static final String VBDEF1 = "vbdef1";

  /**
   * 自定义项10
   */
  public static final String VBDEF10 = "vbdef10";

  /**
   * 自定义项11
   */
  public static final String VBDEF11 = "vbdef11";

  /**
   * 自定义项12
   */
  public static final String VBDEF12 = "vbdef12";

  /**
   * 自定义项13
   */
  public static final String VBDEF13 = "vbdef13";

  /**
   * 自定义项14
   */
  public static final String VBDEF14 = "vbdef14";

  /**
   * 自定义项15
   */
  public static final String VBDEF15 = "vbdef15";

  /**
   * 自定义项16
   */
  public static final String VBDEF16 = "vbdef16";

  /**
   * 自定义项17
   */
  public static final String VBDEF17 = "vbdef17";

  /**
   * 自定义项18
   */
  public static final String VBDEF18 = "vbdef18";

  /**
   * 自定义项19
   */
  public static final String VBDEF19 = "vbdef19";

  /**
   * 自定义项2
   */
  public static final String VBDEF2 = "vbdef2";

  /**
   * 自定义项20
   */
  public static final String VBDEF20 = "vbdef20";

  /**
   * 自定义项3
   */
  public static final String VBDEF3 = "vbdef3";

  /**
   * 自定义项4
   */
  public static final String VBDEF4 = "vbdef4";

  /**
   * 自定义项5
   */
  public static final String VBDEF5 = "vbdef5";

  /**
   * 自定义项6
   */
  public static final String VBDEF6 = "vbdef6";

  /**
   * 自定义项7
   */
  public static final String VBDEF7 = "vbdef7";

  /**
   * 自定义项8
   */
  public static final String VBDEF8 = "vbdef8";

  /**
   * 自定义项9
   */
  public static final String VBDEF9 = "vbdef9";

  /**
   * 修订理由
   */
  public static final String VBREVISEREASON = "vbrevisereason";

  /**
   * 换算率
   */
  public static final String VCHANGERATE = "vchangerate";

  /**
   * 关闭原因
   */
  public static final String VCLOSEREASON = "vclosereason";

  /**
   * 销售合同号
   */
  public static final String VCTCODE = "vctcode";

  /**
   * vcttype
   */
  public static final String VCTTYPE = "vcttype";

  /**
   * 源头单据号
   */
  public static final String VFIRSTCODE = "vfirstcode";

  /**
   * 源头单据行号
   */
  public static final String VFIRSTROWNO = "vfirstrowno";

  /**
   * 源头交易类型
   */
  public static final String VFIRSTTRANTYPE = "vfirsttrantype";

  /**
   * 源头单据类型
   */
  public static final String VFIRSTTYPE = "vfirsttype";

  /**
   * 自由辅助属性1
   */
  public static final String VFREE1 = "vfree1";

  /**
   * 自由辅助属性10
   */
  public static final String VFREE10 = "vfree10";

  /**
   * 自由辅助属性2
   */
  public static final String VFREE2 = "vfree2";

  /**
   * 自由辅助属性3
   */
  public static final String VFREE3 = "vfree3";

  /**
   * 自由辅助属性4
   */
  public static final String VFREE4 = "vfree4";

  /**
   * 自由辅助属性5
   */
  public static final String VFREE5 = "vfree5";

  /**
   * 自由辅助属性6
   */
  public static final String VFREE6 = "vfree6";

  /**
   * 自由辅助属性7
   */
  public static final String VFREE7 = "vfree7";

  /**
   * 自由辅助属性8
   */
  public static final String VFREE8 = "vfree8";

  /**
   * 自由辅助属性9
   */
  public static final String VFREE9 = "vfree9";

  /**
   * 报价换算率
   */
  public static final String VQTUNITRATE = "vqtunitrate";

  /**
   * 退货责任处理方式
   */
  public static final String VRETURNMODE = "vreturnmode";

  /**
   * 行备注
   */
  public static final String VROWNOTE = "vrownote";

  /**
   * 来源单据号
   */
  public static final String VSRCCODE = "vsrccode";

  /**
   * 来源单据行号
   */
  public static final String VSRCROWNO = "vsrcrowno";

  /**
   * 来源交易类型
   */
  public static final String VSRCTRANTYPE = "vsrctrantype";

  /**
   * 来源单据类型
   */
  public static final String VSRCTYPE = "vsrctype";

  /**
   * 获取行号
   * 
   * @return行号
   */
  public String getCrowno() {
    return (String) this.getAttributeValue(CROWNO);
  }

  /**
   * 设置行号
   * 
   * @param crowno行号
   */
  public void setCrowno(final String crowno) {
    this.setAttributeValue(CROWNO, crowno);
  }

  /**
   * 获取物料编码
   * 
   * @return物料编码
   */
  public String getCmaterialvid() {
    return (String) this.getAttributeValue(CMATERIALVID);
  }

  /**
   * 设置物料编码
   * 
   * @param cmaterialvid 物料编码
   */
  public void setCmaterialvid(String cmaterialvid) {
    this.setAttributeValue(CMATERIALVID, cmaterialvid);
  }

  /**
   * 获取供应商
   * 
   * @return供应商
   */
  public String getCvendorid() {
    return (String) this.getAttributeValue(CVENDORID);
  }

  /**
   * 设置供应商
   * 
   * @param cvendorid供应商
   */
  public void setCvendorid(String cvendorid) {
    this.setAttributeValue(CVENDORID, cvendorid);
  }

  /**
   * 获取项目
   * 
   * @return项目
   */
  public String getCprojectid() {
    return (String) this.getAttributeValue(CPROJECTID);
  }

  /**
   * 设置项目
   * 
   * @param cprojectid
   */
  public void setCprojectid(String cprojectid) {
    this.setAttributeValue(CPROJECTID, cprojectid);
  }

  /**
   * 获取工厂
   * 
   * @return工厂
   */
  public String getCfactoryid() {
    return (String) this.getAttributeValue(CFACTORYID);
  }

  /**
   * 设置工厂
   * 
   * @param cfactoryid
   */
  public void setCfactoryid(String cfactoryid) {
    this.setAttributeValue(CFACTORYID, cfactoryid);
  }

  /**
   * 获取生产厂商
   * 
   * @return生产厂商
   */
  public String getCproductorid() {
    return (String) this.getAttributeValue(CPRODUCTORID);
  }

  /**
   * 设置生产厂商
   * 
   * @param cproductorid生产厂商
   */
  public void setCproductorid(String cproductorid) {
    this.setAttributeValue(CPRODUCTORID, cproductorid);
  }

  /**
   * 获取质量等级
   * 
   * @return质量等级
   */
  public String getCqualitylevelid() {
    return (String) this.getAttributeValue(CQUALITYLEVELID);
  }

  /**
   * 设置质量等级
   * 
   * @param cqualitylevelid质量等级
   */
  public void setCqualitylevelid(String cqualitylevelid) {
    this.setAttributeValue(CQUALITYLEVELID, cqualitylevelid);
  }

  /**
   * 获取原产国
   * 
   * @return原产国
   */
  public String getCorigcountryid() {
    return (String) this.getAttributeValue(CORIGCOUNTRYID);
  }

  /**
   * 设置原产国
   * 
   * @param corigcountryid
   */
  public void setCorigcountryid(String corigcountryid) {
    this.setAttributeValue(CORIGCOUNTRYID, corigcountryid);
  }

  /**
   * 获取原产地区
   * 
   * @return原产地区
   */
  public String getCorigareaid() {
    return (String) this.getAttributeValue(CORIGAREAID);
  }

  /**
   * 设置原产地区
   * 
   * @param corigareaid原产地区
   */
  public void setCorigareaid(String corigareaid) {
    this.setAttributeValue(CORIGAREAID, corigareaid);
  }

  /**
   * 获取折本汇率
   * 
   * @return折本汇率
   */
  public UFDouble getNexchangerate() {
    return (UFDouble) this.getAttributeValue(NEXCHANGERATE);
  }

  /**
   * 设置折本汇率
   * 
   * @param nexchangerate 折本汇率
   */
  public void setNexchangerate(UFDouble nexchangerate) {
    this.setAttributeValue(NEXCHANGERATE, nexchangerate);
  }

  /**
   * 获取批次号
   * 
   * @return批次号
   */
  public String getVbatchcode() {
    return (String) this.getAttributeValue(VBATCHCODE);
  }

  /**
   * 设置批次号
   * 
   * @param vbatchcode批次号
   */
  public void setVbatchcode(String vbatchcode) {
    this.setAttributeValue(VBATCHCODE, vbatchcode);
  }

  /**
   * 获取批次档案
   * 
   * @return批次档案
   */
  public String getPk_batchcode() {
    return (String) this.getAttributeValue(PK_BATCHCODE);
  }

  /**
   * 设置批次档案
   * 
   * @param pk_batchcode批次档案
   */
  public void setPk_batchcode(String pk_batchcode) {
    this.setAttributeValue(PK_BATCHCODE, pk_batchcode);
  }

  /**
   * 获取赠品
   * 
   * @return赠品
   */
  public UFBoolean getBlargessflag() {
    return (UFBoolean) this.getAttributeValue(BLARGESSFLAG);
  }

  /**
   * 设置赠品
   * 
   * @param blargessflag赠品
   */
  public void setBlargessflag(UFBoolean blargessflag) {
    this.setAttributeValue(BLARGESSFLAG, blargessflag);
  }

  /**
   * 获取发货日期
   * 
   * @return发货日期
   */
  public UFDate getDsenddate() {
    return (UFDate) this.getAttributeValue(DSENDDATE);
  }

  /**
   * 设置发货日期
   * 
   * @param dsenddate发货日期
   */
  public void setDsenddate(UFDate dsenddate) {
    this.setAttributeValue(DSENDDATE, dsenddate);
  }

  /**
   * 获取到货日期
   * 
   * @return到货日期
   */
  public UFDate getDreceivedate() {
    return (UFDate) this.getAttributeValue(DRECEIVEDATE);
  }

  /**
   * 设置到货日期
   * 
   * @param dreceivedate到货日期
   */
  public void setDreceivedate(UFDate dreceivedate) {
    this.setAttributeValue(DRECEIVEDATE, dreceivedate);
  }

  /**
   * 获取收货客户
   * 
   * @return收货客户
   */
  public String getCreceivecustid() {
    return (String) this.getAttributeValue(CRECEIVECUSTID);
  }

  /**
   * 设置收货客户
   * 
   * @param creceivecustid收货客户
   */
  public void setCreceivecustid(String creceivecustid) {
    this.setAttributeValue(CRECEIVECUSTID, creceivecustid);
  }

  /**
   * 获取收货地区
   * 
   * @return收货地区
   */
  public String getCreceiveareaid() {
    return (String) this.getAttributeValue(CRECEIVEAREAID);
  }

  /**
   * 设置收货地区
   * 
   * @param creceiveareaid收货地区
   */
  public void setCreceiveareaid(String creceiveareaid) {
    this.setAttributeValue(CRECEIVEAREAID, creceiveareaid);
  }

  /**
   * 获取收货地点
   * 
   * @return收货地点
   */
  public String getCreceiveadddocid() {
    return (String) this.getAttributeValue(CRECEIVEADDDOCID);
  }

  /**
   * 设置收货地点
   * 
   * @param creceiveadddocid收货地点
   */
  public void setCreceiveadddocid(String creceiveadddocid) {
    this.setAttributeValue(CRECEIVEADDDOCID, creceiveadddocid);
  }

  /**
   * 获取收货地址
   * 
   * @return收货地址
   */
  public String getCreceiveaddrid() {
    return (String) this.getAttributeValue(CRECEIVEADDRID);
  }

  /**
   * 设置收货地址
   * 
   * @param creceiveaddrid收货地址
   */
  public void setCreceiveaddrid(String creceiveaddrid) {
    this.setAttributeValue(CRECEIVEADDRID, creceiveaddrid);
  }

  /**
   * 设置发货库存组织
   * 
   * @return发货库存组织
   */
  public String getCsendstockorgvid() {
    return (String) this.getAttributeValue(CSENDSTOCKORGVID);
  }

  /**
   * 设置发货库存组织
   * 
   * @param csendstockorgvid发货库存组织
   */
  public void setCsendstockorgvid(String csendstockorgvid) {
    this.setAttributeValue(CSENDSTOCKORGVID, csendstockorgvid);
  }

  /**
   * 获取发货仓库
   * 
   * @return发货仓库
   */
  public String getCsendstordocid() {
    return (String) this.getAttributeValue(CSENDSTORDOCID);
  }

  /**
   * 设置发货仓库
   * 
   * @param csendstordocid发货仓库
   */
  public void setCsendstordocid(String csendstordocid) {
    this.setAttributeValue(CSENDSTORDOCID, csendstordocid);
  }

  /**
   * 获取物流组织
   * 
   * @return物流组织
   */
  public String getCtrafficorgvid() {
    return (String) this.getAttributeValue(CTRAFFICORGVID);
  }

  /**
   * 设置物流组织
   * 
   * @param ctrafficorgvid物流组织
   */
  public void setCtrafficorgvid(String ctrafficorgvid) {
    this.setAttributeValue(CTRAFFICORGVID, ctrafficorgvid);
  }

  /**
   * 获取结算财务组织
   * 
   * @return结算财务组织
   */
  public String getCsettleorgvid() {
    return (String) this.getAttributeValue(CSETTLEORGVID);
  }

  /**
   * 设置结算财务组织
   * 
   * @param csettleorgvid结算财务组织
   */
  public void setCsettleorgvid(String csettleorgvid) {
    this.setAttributeValue(CSETTLEORGVID, csettleorgvid);
  }

  /**
   * 获取收货国家/地区
   * 
   * @return收货国家/地区
   */
  public String getCrececountryid() {
    return (String) this.getAttributeValue(CRECECOUNTRYID);
  }

  /**
   * 设置收货国家/地区
   * 
   * @param crececountryid收货国家/地区
   */
  public void setCrececountryid(String crececountryid) {
    this.setAttributeValue(CRECECOUNTRYID, crececountryid);
  }

  /**
   * 获取报税国家/地区
   * 
   * @return报税国家/地区
   */
  public String getCtaxcountryid() {
    return (String) this.getAttributeValue(CTAXCOUNTRYID);
  }

  /**
   * 设置报税国家/地区
   * 
   * @param ctaxcountryid报税国家/地区
   */
  public void setCtaxcountryid(String ctaxcountryid) {
    this.setAttributeValue(CTAXCOUNTRYID, ctaxcountryid);
  }

  /**
   * 获取发货国家/地区
   * 
   * @return发货国家/地区
   */
  public String getCsendcountryid() {
    return (String) this.getAttributeValue(CSENDCOUNTRYID);
  }

  /**
   * 设置发货国家/地区
   * 
   * @param csendcountryid发货国家/地区
   */
  public void setCsendcountryid(String csendcountryid) {
    this.setAttributeValue(CSENDCOUNTRYID, csendcountryid);
  }

  /**
   * 获取购销类型
   * 
   * @return购销类型
   */
  public Integer getFbuysellflag() {
    return (Integer) this.getAttributeValue(FBUYSELLFLAG);
  }

  /**
   * 设置购销类型
   * 
   * @param fbuysellflag购销类型
   */
  public void setFbuysellflag(Integer fbuysellflag) {
    this.setAttributeValue(FBUYSELLFLAG, fbuysellflag);
  }

  /**
   * 获取三角贸易
   * 
   * @return三角贸易
   */
  public UFBoolean getBtriatradeflag() {
    return (UFBoolean) this.getAttributeValue(BTRIATRADEFLAG);
  }

  /**
   * 设置三角贸易
   * 
   * @param btriatradeflag三角贸易
   */
  public void setBtriatradeflag(UFBoolean btriatradeflag) {
    this.setAttributeValue(BTRIATRADEFLAG, btriatradeflag);
  }

  /**
   * 获取应收组织
   * 
   * @return应收组织
   */
  public String getCarorgvid() {
    return (String) this.getAttributeValue(CARORGVID);
  }

  /**
   * 设置应收组织
   * 
   * @param carorgvid应收组织
   */
  public void setCarorgvid(String carorgvid) {
    this.setAttributeValue(CARORGVID, carorgvid);
  }

  /**
   * 获取利润中心
   * 
   * @return利润中心
   */
  public String getCprofitcentervid() {
    return (String) this.getAttributeValue(CPROFITCENTERVID);
  }

  /**
   * 设置利润中心
   * 
   * @param cprofitcentervid利润中心
   */
  public void setCprofitcentervid(String cprofitcentervid) {
    this.setAttributeValue(CPROFITCENTERVID, cprofitcentervid);
  }

  /**
   * 获取退货原因
   * 
   * @return退货原因
   */
  public String getCretreasonid() {
    return (String) this.getAttributeValue(CRETREASONID);
  }

  /**
   * 设置退货原因
   * 
   * @param cretreasonid 退货原因
   */
  public void setCretreasonid(final String cretreasonid) {
    this.setAttributeValue(CRETREASONID, cretreasonid);
  }

  /**
   * 获取退货责任处理方式
   * 
   * @return退货责任处理方式
   */
  public String getVreturnmode() {
    return (String) this.getAttributeValue(VRETURNMODE);
  }

  /**
   * 设置退货责任处理方式
   * 
   * @param vreturnmode退货责任处理方式
   */
  public void setVreturnmode(final String vreturnmode) {
    this.setAttributeValue(VRETURNMODE, vreturnmode);
  }

  /**
   * 获取退货政策
   * 
   * @return退货政策
   */
  public String getCretpolicyid() {
    return (String) this.getAttributeValue(CRETPOLICYID);
  }

  /**
   * 设置退货政策
   * 
   * @param cretpolicyid退货政策
   */
  public void setCretpolicyid(final String cretpolicyid) {
    this.setAttributeValue(CRETPOLICYID, cretpolicyid);
  }

  /**
   * 获取销售合同号
   * 
   * @return 销售合同号
   */
  public String getVctcode() {
    return (String) this.getAttributeValue(VCTCODE);
  }

  /**
   * 设置销售合同号
   * 
   * @param vctcode销售合同号
   */
  public void setVctcode(final String vctcode) {
    this.setAttributeValue(VCTCODE, vctcode);
  }

  /**
   * 获取合同主表
   * 
   * @return合同主表
   */
  public String getCctmanageid() {
    return (String) this.getAttributeValue(CCTMANAGEID);
  }

  /**
   * 设置合同主表
   * 
   * @param cctmanageid合同主表
   */
  public void setCctmanageid(final String cctmanageid) {
    this.setAttributeValue(CCTMANAGEID, cctmanageid);
  }

  /**
   * 获取合同子表
   * 
   * @return合同子表
   */
  public String getCctmanagebid() {
    return (String) this.getAttributeValue(CCTMANAGEBID);
  }

  /**
   * 设置合同子表
   * 
   * @param cctmanageid合同子表
   */
  public void setCctmanagebid(final String cctmanagebid) {
    this.setAttributeValue(CCTMANAGEBID, cctmanagebid);
  }

  /**
   * 获取来源单据类型
   * 
   * @return来源单据类型
   */
  public String getVsrctype() {
    return (String) this.getAttributeValue(VSRCTYPE);
  }

  /**
   * 设置来源单据类型
   * 
   * @param vsrctype来源单据类型
   */
  public void setVsrctype(final String vsrctype) {
    this.setAttributeValue(VSRCTYPE, vsrctype);
  }

  /**
   * 获取来源交易类型
   * 
   * @return来源交易类型
   */
  public String getVsrctrantype() {
    return (String) this.getAttributeValue(VSRCTRANTYPE);
  }

  /**
   * 设置来源交易类型
   * 
   * @param vsrctrantype来源交易类型
   */
  public void setVsrctrantype(final String vsrctrantype) {
    this.setAttributeValue(VSRCTRANTYPE, vsrctrantype);
  }

  /**
   * 获取来源单据主表
   * 
   * @return来源单据主表
   */
  public String getCsrcid() {
    return (String) this.getAttributeValue(CSRCID);
  }

  /**
   * 设置来源单据主表
   * 
   * @param csrcid来源单据主表
   */
  public void setCsrcid(final String csrcid) {
    this.setAttributeValue(CSRCID, csrcid);
  }

  /**
   * 获取来源单据附表
   * 
   * @return来源单据附表
   */
  public String getCsrcbid() {
    return (String) this.getAttributeValue(CSRCBID);
  }

  /**
   * 设置来源单据附表
   * 
   * @param csrcbid来源单据附表
   */
  public void setCsrcbid(final String csrcbid) {
    this.setAttributeValue(CSRCBID, csrcbid);
  }

  /**
   * 获取来源单据号
   * 
   * @return来源单据号
   */
  public String getVsrccode() {
    return (String) this.getAttributeValue(VSRCCODE);
  }

  /**
   * 设置来源单据号
   * 
   * @param vsrccode来源单据号
   */
  public void setVsrccode(final String vsrccode) {
    this.setAttributeValue(VSRCCODE, vsrccode);
  }

  /**
   * 设置来源单据号
   * 
   * @return来源单据号
   */
  public String getVsrcrowno() {
    return (String) this.getAttributeValue(VSRCROWNO);
  }

  /**
   * 获取来源单据号
   * 
   * @param vsrcrowno来源单据号
   */
  public void setVsrcrowno(final String vsrcrowno) {
    this.setAttributeValue(VSRCROWNO, vsrcrowno);
  }

  /**
   * 获取源头单据类型
   * 
   * @return源头单据类型
   */
  public String getVfirsttype() {
    return (String) this.getAttributeValue(VFIRSTTYPE);
  }

  /**
   * 设置源头单据类型
   * 
   * @param vfirsttype源头单据类型
   */
  public void setVfirsttype(final String vfirsttype) {
    this.setAttributeValue(VFIRSTTYPE, vfirsttype);
  }

  /**
   * 获取源头交易类型
   * 
   * @return源头交易类型
   */
  public String getVfirsttrantype() {
    return (String) this.getAttributeValue(VFIRSTTRANTYPE);
  }

  /**
   * 设置源头交易类型
   * 
   * @param vfirsttrantype源头交易类型
   */
  public void setVfirsttrantype(final String vfirsttrantype) {
    this.setAttributeValue(VFIRSTTRANTYPE, vfirsttrantype);
  }

  /**
   * 获取源头单据号
   * 
   * @return源头单据号
   */
  public String getVfirstcode() {
    return (String) this.getAttributeValue(VFIRSTCODE);
  }

  /**
   * 设置源头单据号
   * 
   * @param vfirstcode源头单据号
   */
  public void setVfirstcode(final String vfirstcode) {
    this.setAttributeValue(VFIRSTCODE, vfirstcode);
  }

  /**
   * 获取源头单据主表
   * 
   * @return源头单据主表
   */
  public String getCfirstid() {
    return (String) this.getAttributeValue(CFIRSTID);
  }

  /**
   * 设置源头单据主表
   * 
   * @param cfirstid源头单据主表
   */
  public void setCfirstid(final String cfirstid) {
    this.setAttributeValue(CFIRSTID, cfirstid);
  }

  /**
   * 源头单据子表
   * 
   * @return源头单据子表
   */
  public String getCfirstbid() {
    return (String) this.getAttributeValue(CFIRSTBID);
  }

  /**
   * 源头单据子表
   * 
   * @param cfirstbid源头单据子表
   */
  public void setCfirstbid(final String cfirstbid) {
    this.setAttributeValue(CFIRSTBID, cfirstbid);
  }

  /**
   * 源头单据行号
   * 
   * @return源头单据行号
   */
  public String getVfirstrowno() {
    return (String) this.getAttributeValue(VFIRSTROWNO);
  }

  /**
   * 源头单据行号
   * 
   * @param vfirstrowno源头单据行号
   */
  public void setVfirstrowno(final String vfirstrowno) {
    this.setAttributeValue(VFIRSTROWNO, vfirstrowno);
  }

  /**
   * 获取自由辅助属性1
   * 
   * @return自由辅助属性1
   */
  public String getVfree1() {
    return (String) this.getAttributeValue(VFREE1);
  }

  /**
   * 设置自由辅助属性1
   * 
   * @return自由辅助属性2
   */
  public void setVfree1(String vfree1) {
    this.setAttributeValue(VFREE1, vfree1);
  }

  /**
   * 获取自由辅助属性2
   * 
   * @return自由辅助属性3
   */
  public String getVfree2() {
    return (String) this.getAttributeValue(VFREE2);
  }

  /**
   * 设置自由辅助属性2
   * 
   * @return自由辅助属性2
   */
  public void setVfree2(String vfree2) {
    this.setAttributeValue(VFREE2, vfree2);
  }

  /**
   * 获取自由辅助属性3
   * 
   * @return自由辅助属性3
   */
  public String getVfree3() {
    return (String) this.getAttributeValue(VFREE3);
  }

  /**
   * 设置自由辅助属性3
   * 
   * @param vfree3自由辅助属性3
   */
  public void setVfree3(String vfree3) {
    this.setAttributeValue(VFREE3, vfree3);
  }

  /**
   * 获取自由辅助属性4
   * 
   * @return自由辅助属性4
   */
  public String getVfree4() {
    return (String) this.getAttributeValue(VFREE4);
  }

  /**
   * 设置自由辅助属性4
   * 
   * @param vfree3自由辅助属性4
   */
  public void setVfree4(String vfree4) {
    this.setAttributeValue(VFREE4, vfree4);
  }

  /**
   * 获取自由辅助属性5
   * 
   * @return自由辅助属性5
   */
  public String getVfree5() {
    return (String) this.getAttributeValue(VFREE5);
  }

  /**
   * 设置自由辅助属性5
   * 
   * @param vfree3自由辅助属性5
   */
  public void setVfree5(String vfree5) {
    this.setAttributeValue(VFREE5, vfree5);
  }

  /**
   * 获取自由辅助属性6
   * 
   * @return自由辅助属性6
   */
  public String getVfree6() {
    return (String) this.getAttributeValue(VFREE6);
  }

  /**
   * 设置自由辅助属性6
   * 
   * @param vfree6自由辅助属性6
   */
  public void setVfree6(String vfree6) {
    this.setAttributeValue(VFREE6, vfree6);
  }

  /**
   * 获取自由辅助属性7
   * 
   * @return自由辅助属性7
   */
  public String getVfree7() {
    return (String) this.getAttributeValue(VFREE7);
  }

  /**
   * 设置自由辅助属性7
   * 
   * @param vfree7自由辅助属性7
   */
  public void setVfree7(String vfree7) {
    this.setAttributeValue(VFREE7, vfree7);
  }

  /**
   * 获取自由辅助属性8
   * 
   * @return自由辅助属性8
   */
  public String getVfree8() {
    return (String) this.getAttributeValue(VFREE8);
  }

  /**
   * 设置自由辅助属性8
   * 
   * @param vfree8自由辅助属性8
   */
  public void setVfree8(String vfree8) {
    this.setAttributeValue(VFREE8, vfree8);
  }

  /**
   * 获取自由辅助属性9
   * 
   * @return自由辅助属性9
   */
  public String getVfree9() {
    return (String) this.getAttributeValue(VFREE9);
  }

  /**
   * 设置自由辅助属性9
   * 
   * @param vfree9自由辅助属性9
   */
  public void setVfree9(String vfree9) {
    this.setAttributeValue(VFREE9, vfree9);
  }

  /**
   * 获取自由辅助属性10
   * 
   * @return自由辅助属性10
   */
  public String getVfree10() {
    return (String) this.getAttributeValue(VFREE10);
  }

  /**
   * 设置自由辅助属性10
   * 
   * @param vfree10自由辅助属性10
   */
  public void setVfree10(String vfree10) {
    this.setAttributeValue(VFREE10, vfree10);
  }

  /**
   * 获取自定义项1
   * 
   * @return自定义项1
   */
  public String getVbdef1() {
    return (String) this.getAttributeValue(VBDEF1);
  }

  /**
   * 设置自定义项1
   * 
   * @param vbdef1
   */
  public void setVbdef1(String vbdef1) {
    this.setAttributeValue(VBDEF1, vbdef1);
  }

  /**
   * 获取自定义项2
   * 
   * @return自定义项2
   */
  public String getVbdef2() {
    return (String) this.getAttributeValue(VBDEF2);
  }

  /**
   * 设置自定义项2
   * 
   * @param vbdef2自定义项2
   */
  public void setVbdef2(String vbdef2) {
    this.setAttributeValue(VBDEF2, vbdef2);
  }

  /**
   * 获取自定义项3
   * 
   * @return自定义项3
   */
  public String getVbdef3() {
    return (String) this.getAttributeValue(VBDEF3);
  }

  /**
   * 设置自定义项3
   * 
   * @param vbdef3自定义项3
   */
  public void setVbdef3(String vbdef3) {
    this.setAttributeValue(VBDEF3, vbdef3);
  }

  /**
   * 获取自定义项4
   * 
   * @return自定义项4
   */
  public String getVbdef4() {
    return (String) this.getAttributeValue(VBDEF4);
  }

  /**
   * 设置自定义项4
   * 
   * @param vbdef4自定义项4
   */
  public void setVbdef4(String vbdef4) {
    this.setAttributeValue(VBDEF4, vbdef4);
  }

  /**
   * 获取自定义项5
   * 
   * @return自定义项5
   */
  public String getVbdef5() {
    return (String) this.getAttributeValue(VBDEF5);
  }

  /**
   * 设置自定义项5
   * 
   * @param vbdef5自定义项5
   */
  public void setVbdef5(String vbdef5) {
    this.setAttributeValue(VBDEF5, vbdef5);
  }

  /**
   * 获取自定义项6
   * 
   * @return自定义项6
   */
  public String getVbdef6() {
    return (String) this.getAttributeValue(VBDEF6);
  }

  /**
   * 设置自定义项6
   * 
   * @param vbdef6自定义项6
   */
  public void setVbdef6(String vbdef6) {
    this.setAttributeValue(VBDEF6, vbdef6);
  }

  /**
   * 获取自定义项7
   * 
   * @return自定义项7
   */
  public String getVbdef7() {
    return (String) this.getAttributeValue(VBDEF7);
  }

  /**
   * 设置自定义项7
   * 
   * @param vbdef7自定义项7
   */
  public void setVbdef7(String vbdef7) {
    this.setAttributeValue(VBDEF7, vbdef7);
  }

  /**
   * 获取自定义项8
   * 
   * @return自定义项8
   */
  public String getVbdef8() {
    return (String) this.getAttributeValue(VBDEF8);
  }

  /**
   * 设置自定义项8
   * 
   * @param vbdef8自定义项8
   */
  public void setVbdef8(String vbdef8) {
    this.setAttributeValue(VBDEF8, vbdef8);
  }

  /**
   * 获取自定义项9
   * 
   * @return自定义项9
   */
  public String getVbdef9() {
    return (String) this.getAttributeValue(VBDEF9);
  }

  /**
   * 设置自定义项9
   * 
   * @param vbdef9自定义项9
   */
  public void setVbdef9(String vbdef9) {
    this.setAttributeValue(VBDEF9, vbdef9);
  }

  /**
   * 获取自定义项10
   * 
   * @return自定义项10
   */
  public String getVbdef10() {
    return (String) this.getAttributeValue(VBDEF10);
  }

  /**
   * 设置自定义项10
   * 
   * @param vbdef10自定义项10
   */
  public void setVbdef10(String vbdef10) {
    this.setAttributeValue(VBDEF10, vbdef10);
  }

  /**
   * 获取自定义项11
   * 
   * @return自定义项11
   */
  public String getVbdef11() {
    return (String) this.getAttributeValue(VBDEF11);
  }

  /**
   * 设置自定义项11
   * 
   * @param vbdef11自定义项11
   */
  public void setVbdef11(String vbdef11) {
    this.setAttributeValue(VBDEF11, vbdef11);
  }

  /**
   * 获取自定义项12
   * 
   * @return自定义项12
   */
  public String getVbdef12() {
    return (String) this.getAttributeValue(VBDEF12);
  }

  /**
   * 设置自定义项12
   * 
   * @param vbdef12自定义项12
   */
  public void setVbdef12(String vbdef12) {
    this.setAttributeValue(VBDEF12, vbdef12);
  }

  /**
   * 获取自定义项13
   * 
   * @return自定义项13
   */
  public String getVbdef13() {
    return (String) this.getAttributeValue(VBDEF13);
  }

  /**
   * 设置自定义项13
   * 
   * @param vbdef13自定义项13
   */
  public void setVbdef13(String vbdef13) {
    this.setAttributeValue(VBDEF13, vbdef13);
  }

  /**
   * 获取自定义项14
   * 
   * @return自定义项14
   */
  public String getVbdef14() {
    return (String) this.getAttributeValue(VBDEF14);
  }

  /**
   * 设置自定义项14
   * 
   * @param vbdef14自定义项14
   */
  public void setVbdef14(String vbdef14) {
    this.setAttributeValue(VBDEF14, vbdef14);
  }

  /**
   * 获取自定义项15
   * 
   * @return自定义项15
   */
  public String getVbdef15() {
    return (String) this.getAttributeValue(VBDEF15);
  }

  /**
   * 设置自定义项15
   * 
   * @param vbdef15自定义项15
   */
  public void setVbdef15(String vbdef15) {
    this.setAttributeValue(VBDEF15, vbdef15);
  }

  /**
   * 获取自定义项16
   * 
   * @return自定义项16
   */
  public String getVbdef16() {
    return (String) this.getAttributeValue(VBDEF16);
  }

  /**
   * 设置自定义项16
   * 
   * @param vbdef16自定义项16
   */
  public void setVbdef16(String vbdef16) {
    this.setAttributeValue(VBDEF16, vbdef16);
  }

  /**
   * 获取自定义项17
   * 
   * @return自定义项17
   */
  public String getVbdef17() {
    return (String) this.getAttributeValue(VBDEF17);
  }

  /**
   * 设置自定义项17
   * 
   * @param vbdef17自定义项17
   */
  public void setVbdef17(String vbdef17) {
    this.setAttributeValue(VBDEF17, vbdef17);
  }

  /**
   * 获取自定义项18
   * 
   * @return自定义项18
   */
  public String getVbdef18() {
    return (String) this.getAttributeValue(VBDEF18);
  }

  /**
   * 设置自定义项18
   * 
   * @param vbdef18自定义项18
   */
  public void setVbdef18(String vbdef18) {
    this.setAttributeValue(VBDEF18, vbdef18);
  }

  /**
   * 获取自定义项19
   * 
   * @return自定义项19
   */
  public String getVbdef19() {
    return (String) this.getAttributeValue(VBDEF19);
  }

  /**
   * 设置自定义项19
   * 
   * @param vbdef19自定义项19
   */
  public void setVbdef19(String vbdef19) {
    this.setAttributeValue(VBDEF19, vbdef19);
  }

  /**
   * 获取自定义项20
   * 
   * @return自定义项20
   */
  public String getVbdef20() {
    return (String) this.getAttributeValue(VBDEF20);
  }

  /**
   * 设置自定义项20
   * 
   * @param vbdef20自定义项20
   */
  public void setVbdef20(String vbdef20) {
    this.setAttributeValue(VBDEF20, vbdef20);
  }

  /**
   * 获取行备注
   * 
   * @return行备注
   */
  public String getVrownote() {
    return (String) this.getAttributeValue(VROWNOTE);
  }

  /**
   * 设置行备注
   * 
   * @param vrownote行备注
   */
  public void setVrownote(String vrownote) {
    this.setAttributeValue(VROWNOTE, vrownote);
  }

  /**
   * 获取客户物料码
   * 
   * @return客户物料码
   */
  public String getCcustmaterialid() {
    return (String) this.getAttributeValue(CCUSTMATERIALID);
  }

  /**
   * 设置客户物料码
   * 
   * @param ccustmaterialid客户物料码
   */
  public void setCcustmaterialid(String ccustmaterialid) {
    this.setAttributeValue(CCUSTMATERIALID, ccustmaterialid);
  }

  /**
   * 获取单位
   * 
   * @return单位
   */
  public String getCastunitid() {
    return (String) this.getAttributeValue(CASTUNITID);
  }

  /**
   * 设置单位
   */
  public void setCastunitid(String castunitid) {
    this.setAttributeValue(CASTUNITID, castunitid);
  }

  /**
   * 获取主数量
   * 
   * @return 主数量
   */
  public UFDouble getNnum() {
    return (UFDouble) this.getAttributeValue(NNUM);
  }

  /**
   * 设置主数量
   * 
   * @param nnum 主数量
   */
  public void setNnum(UFDouble nnum) {
    this.setAttributeValue(NNUM, nnum);
  }

  /**
   * 获取数量
   * 
   * @return 数量
   */
  public UFDouble getNastnum() {
    return (UFDouble) this.getAttributeValue(NASTNUM);
  }

  /**
   * 设置数量
   * 
   * @param nastnum 数量
   */
  public void setNastnum(UFDouble nastnum) {
    this.setAttributeValue(NASTNUM, nastnum);
  }

  /**
   * 获取单品折扣
   * 
   * @return 单品折扣
   */
  public UFDouble getNitemdiscountrate() {
    return (UFDouble) this.getAttributeValue(NITEMDISCOUNTRATE);
  }

  /**
   * 设置单品折扣
   * 
   * @param nitemdiscountrate 单品折扣
   */
  public void setNitemdiscountrate(UFDouble nitemdiscountrate) {
    this.setAttributeValue(NITEMDISCOUNTRATE, nitemdiscountrate);
  }

  /**
   * 获取税率
   * 
   * @return 税率
   */
  public UFDouble getNtaxrate() {
    return (UFDouble) this.getAttributeValue(NTAXRATE);
  }

  /**
   * 设置税率
   * 
   * @param ntaxrate 税率
   */
  public void setNatxrate(UFDouble ntaxrate) {
    this.setAttributeValue(NTAXRATE, ntaxrate);
  }

  /**
   * 获取含税单价
   * 
   * @return含税单价
   */
  public UFDouble getNqtorigtaxprice() {
    return (UFDouble) this.getAttributeValue(NQTORIGTAXPRICE);
  }

  /**
   * 设置含税单价
   * 
   * @param nqtorigtaxprice含税单价
   */
  public void setNqtorigtaxprice(UFDouble nqtorigtaxprice) {
    this.setAttributeValue(NQTORIGTAXPRICE, nqtorigtaxprice);
  }

  /**
   * 获取无税单价
   * 
   * @return无税单价
   */
  public UFDouble getNqtorigprice() {
    return (UFDouble) this.getAttributeValue(NQTORIGPRICE);
  }

  /**
   * 设置无税单价
   * 
   * @param nqtorigprice无税单价
   */
  public void setNqtorigprice(UFDouble nqtorigprice) {
    this.setAttributeValue(NQTORIGPRICE, nqtorigprice);
  }

  /**
   * 获取含税净价
   * 
   * @return含税净价
   */
  public UFDouble getNqtorigtaxnetprc() {
    return (UFDouble) this.getAttributeValue(NQTORIGTAXNETPRC);
  }

  /**
   * 设置含税净价
   * 
   * @param nqtorigtaxnetprc含税净价
   */
  public void setNqtorigtaxnetprc(UFDouble nqtorigtaxnetprc) {
    this.setAttributeValue(NQTORIGTAXNETPRC, nqtorigtaxnetprc);
  }

  /**
   * 获取无税净价
   * 
   * @return无税净价
   */
  public UFDouble getNqtorignetprice() {
    return (UFDouble) this.getAttributeValue(NQTORIGNETPRICE);
  }

  /**
   * 设置无税净价
   * 
   * @param nqtorignetprice无税净价
   */
  public void setNqtorignetprice(UFDouble nqtorignetprice) {
    this.setAttributeValue(NQTORIGNETPRICE, nqtorignetprice);
  }

  /**
   * 获取无税金额
   * 
   * @return无税金额
   */
  public UFDouble getNorigmny() {
    return (UFDouble) this.getAttributeValue(NORIGMNY);
  }

  /**
   * 设置无税金额
   * 
   * @param norigmny无税金额
   */
  public void setNorigmny(UFDouble norigmny) {
    this.setAttributeValue(NORIGMNY, norigmny);
  }

  /**
   * 获取价税合计
   * 
   * @return价税合计
   */
  public UFDouble getNorigtaxmny() {
    return (UFDouble) this.getAttributeValue(NORIGTAXMNY);
  }

  /**
   * 设置价税合计
   * 
   * @param norigtaxmny价税合计
   */
  public void setNorigtaxmny(UFDouble norigtaxmny) {
    this.setAttributeValue(NORIGTAXMNY, norigtaxmny);
  }

  /**
   * 获取折扣额
   * 
   * @return折扣额
   */
  public UFDouble getNorigdiscount() {
    return (UFDouble) this.getAttributeValue(NORIGDISCOUNT);
  }

  /**
   * 设置折扣额
   * 
   * @param norigdiscount折扣额
   */
  public void setNorigdiscount(UFDouble norigdiscount) {
    this.setAttributeValue(NORIGDISCOUNT, norigdiscount);
  }

}
