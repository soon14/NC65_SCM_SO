package nc.vo.so.m30.entity;

import nc.vo.bd.feature.ffile.entity.AggFFileVO;
import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;
import nc.vo.so.m30.enumeration.Fretexchange;
import nc.vo.so.m30.enumeration.Largesstype;

/**
 * 销售订单附表VO
 * 
 * @since 6.0
 * @version 2011-6-10 下午01:53:17
 * @author fengjb
 */
public class SaleOrderBVO extends SuperVO {

  /**
   * 销售前台暂存特征码选配
   */
  private AggFFileVO aggffilevo;
  
  /** 
   * 特征码
   */
  public static final String CMFFILEID="cmffileid";
  
  /**
   * 特征价
   */
  public static final String NMFFILEPRICE="nmffileprice";
  
  /**
   * 赠品兑付的
   */
  public static final String BLRGCASHFLAG = "blrgcashflag";

  /**
   * 主记账单价
   */
  public static final String NACCPRICE = "naccprice";
  /**
   * add by lyw 2017-6-12
   * 交货日期
   */
  public static final String JIAOHUODATE ="jiaohuodate";
  
  public static UFDate jiaohuodate;
  
  /**
   * 获取主记账单价
   * 
   * @return 主记账单价
   */
  public UFDouble getNaccprice() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NACCPRICE);
  }

  /**
   * 设置主记账单价
   * 
   * @param naccprice
   */
  public void setNaccprice(UFDouble naccprice) {
    this.setAttributeValue(SaleOrderBVO.NACCPRICE, naccprice);
  }

  /**
   * 获取赠品兑付
   * 
   * @return 赠品兑付
   */
  public UFBoolean getBlrgcashflag() {
    return (UFBoolean) this.getAttributeValue(SaleOrderBVO.BLRGCASHFLAG);
  }

  /**
   * 设置赠品兑付
   * 
   * @param blrgcashflag
   */
  public void setBlrgcashflag(UFBoolean blrgcashflag) {
    this.setAttributeValue(SaleOrderBVO.BLRGCASHFLAG, blrgcashflag);
  }

  /**
   * 客户物料编码
   */
  public static final String CCUSTMATERIALID = "ccustmaterialid";

  /**
   * 设置客户物料编码
   * 
   */
  public void setCcustmaterialid(String ccustmaterialid) {
    this.setAttributeValue(SaleOrderBVO.CCUSTMATERIALID, ccustmaterialid);
  }

  /**
   * 获取客户物料编码
   * 
   * @return 客户物料编码
   */
  public String getCcustmaterialid() {
    return (String) this.getAttributeValue(SaleOrderBVO.CCUSTMATERIALID);
  }

  /**
   * 买赠活动
   */
  public static final String CBUYLARGESSACTID = "cbuylargessactid";

  /**
   * 价格促销活动
   */
  public static final String CPRICEPROMTACTID = "cpricepromtactid";

  /**
   * 买赠设置ID
   */
  public static final String CBUYLARGESSID = "cbuylargessid";

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
   * 结算利润中心最新版本
   */
  public static final String CPROFITCENTERID = "cprofitcenterid";

  /**
   * 结算利润中心
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
   * 累计安排进口合同主数量
   */
  public static final String NARRANGEITCNUM = "narrangeitcnum";

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
   * 买赠促销类型
   */
  public static final String CBUYPROMOTTYPEID = "cbuypromottypeid";

  /**
   * 询价促销类型
   */
  public static final String CPRCPROMOTTYPEID = "cprcpromottypeid";

  /**
   * 客户订单号
   */
  public static final String VCUSTOMBILLCODE = "vcustombillcode";
  
  /**
   * 发货利润中心最新版本
   */
  public static final String CSPROFITCENTERID = "csprofitcenterid";

  /**
   * 发货利润中心
   */
  public static final String CSPROFITCENTERVID = "csprofitcentervid";
  
  /**
   * 促销价格表行ID
   */
  public static final String CPROMOTPRICEID = "cpromotpriceid";
  
  
  /**
   * 新增表体字段 modified by wangzym 2017-01-18
   * */
  public static String bidding_no;
  public static String project_name;
  public static String Project_content;
  public static String supplier_requirements;
  public static UFDouble sumplannum;
  public static UFDouble sumnum;
  public static String typeplan;
  public static String typebuy;
  public static UFDouble ratereply;
  public static String bid_evaluation;
  public static String combination_standard;
  public static String procurementplan;
  public static String num_bj;
  public static String seq_bj;
  public static String offer_type;
  public static String qualification_way;
  public static String payment;
  public static String business_types;
  public static String procurement;
  public static String estimate;
  public static String delivery_term;
  public static String requirements;
  public static String supplier_code;
  public static String supplier;
  public static String no_delegate;
  public static String seq_delegate;
  public static String ccategoryid;
  public static String projectexecutor;
  public static String no_pasdoc;
  public static String customer_no;
  public static String customer_name;
  public static Integer plan_num;
  public static UFDate request_date;
  public static String host_name;
  public static String material;
  public static String rated_life;
  public static String manufacturer;
  public static UFDouble plan_pricea;
  public static UFDouble plan_priceb;
  public static UFDouble plansum_pricea;
  public static UFDouble Plansum_priceb;
  public static String factory_plan;
  public static String factory_code;
  public static String factory_name;
  public static String plan;
  public static String application_no;
  public static String application_line;
  public static String number_code;
  public static String tally;
  public static UFDate plan_time;
  public static UFDouble freight;
  public static UFDouble added_tax;
  public static UFDouble exchange_rate;
  public static String currency;
  public static String unit_leaders;
  public static String unit_sales;
  public static String unit_charge;
  public static String epein;
  public static String slysfs;
  public static String xlysfs;
  public static String ysfs;
  public static UFDate htqdsj;

  /**
   * 又新增表体字段 modified by wangzym 2017-03-01
   * */
  public static  String bjwlmc;
  
  public static  String materialnamex; 
  public static  String  chand;
  public static UFDouble cgjg;
  public static Integer csjhq;

  
  
  
  
  
  
  
  
  
  
  

  private static final long serialVersionUID = -8352354807354004228L;

  /**
   * 获取买赠活动
   * 
   * @return 买赠活动
   */
  public String getCbuylargessactid() {
    return (String) this.getAttributeValue(SaleOrderBVO.CBUYLARGESSACTID);
  }

  /**
   * 获取价格促销活动
   * 
   * @return 价格促销活动
   */
  public String getCpricepromtactid() {
    return (String) this.getAttributeValue(SaleOrderBVO.CPRICEPROMTACTID);
  }

  /**
   * 获取买赠设置
   * 
   * @return 买赠设置
   */
  public String getCbuylargessid() {
    return (String) this.getAttributeValue(SaleOrderBVO.CBUYLARGESSID);
  }

  /**
   * 获取是否货源安排完毕
   * 
   * @return 是否货源安排完毕
   */
  public UFBoolean getBarrangedflag() {
    return (UFBoolean) this.getAttributeValue(SaleOrderBVO.BARRANGEDFLAG);
  }

  /**
   * 获取收入结算关闭
   * 
   * @return 收入结算关闭
   */
  public UFBoolean getBbarsettleflag() {
    return (UFBoolean) this.getAttributeValue(SaleOrderBVO.BBARSETTLEFLAG);
  }

  /**
   * 获取成本结算关闭
   * 
   * @return 成本结算关闭
   */
  public UFBoolean getBbcostsettleflag() {
    return (UFBoolean) this.getAttributeValue(SaleOrderBVO.BBCOSTSETTLEFLAG);
  }

  /**
   * 获取捆绑存货
   * 
   * @return 捆绑存货
   */
  public UFBoolean getBbindflag() {
    return (UFBoolean) this.getAttributeValue(SaleOrderBVO.BBINDFLAG);
  }

  /**
   * 获取开票关闭
   * 
   * @return 开票关闭
   */
  public UFBoolean getBbinvoicendflag() {
    return (UFBoolean) this.getAttributeValue(SaleOrderBVO.BBINVOICENDFLAG);
  }

  /**
   * 获取出库关闭
   * 
   * @return 出库关闭
   */
  public UFBoolean getBboutendflag() {
    return (UFBoolean) this.getAttributeValue(SaleOrderBVO.BBOUTENDFLAG);
  }

  /**
   * 获取发货关闭
   * 
   * @return 发货关闭
   */
  public UFBoolean getBbsendendflag() {
    return (UFBoolean) this.getAttributeValue(SaleOrderBVO.BBSENDENDFLAG);
  }

  /**
   * 获取bbsettleendflag
   * 
   * @return bbsettleendflag
   */
  public UFBoolean getBbsettleendflag() {
    return (UFBoolean) this.getAttributeValue(SaleOrderBVO.BBSETTLEENDFLAG);
  }

  /**
   * 获取折扣类
   * 
   * @return 折扣类
   */
  public UFBoolean getBdiscountflag() {
    return (UFBoolean) this.getAttributeValue(SaleOrderBVO.BDISCOUNTFLAG);
  }

  /**
   * 获取借出转销售
   * 
   * @return 借出转销售
   */
  public UFBoolean getBjczxsflag() {
    return (UFBoolean) this.getAttributeValue(SaleOrderBVO.BJCZXSFLAG);
  }

  /**
   * 获取服务类
   * 
   * @return 服务类
   */
  public UFBoolean getBlaborflag() {
    return (UFBoolean) this.getAttributeValue(SaleOrderBVO.BLABORFLAG);
  }

  /**
   * 获取赠品
   * 
   * @return 赠品
   */
  public UFBoolean getBlargessflag() {
    return (UFBoolean) this.getAttributeValue(SaleOrderBVO.BLARGESSFLAG);
  }

  /**
   * 获取bprerowcloseflag
   * 
   * @return bprerowcloseflag
   */
  public UFBoolean getBprerowcloseflag() {
    return (UFBoolean) this.getAttributeValue(SaleOrderBVO.BPREROWCLOSEFLAG);
  }

  /**
   * 获取三角贸易
   * 
   * @return 三角贸易
   */
  public UFBoolean getBtriatradeflag() {
    return (UFBoolean) this.getAttributeValue(SaleOrderBVO.BTRIATRADEFLAG);
  }

  /**
   * 获取应收组织最新版本
   * 
   * @return 应收组织最新版本
   */
  public String getCarorgid() {
    return (String) this.getAttributeValue(SaleOrderBVO.CARORGID);
  }

  /**
   * 获取应收组织
   * 
   * @return 应收组织
   */
  public String getCarorgvid() {
    return (String) this.getAttributeValue(SaleOrderBVO.CARORGVID);
  }

  /**
   * 获取最后货源安排人
   * 
   * @return 最后货源安排人
   */
  public String getCarrangepersonid() {
    return (String) this.getAttributeValue(SaleOrderBVO.CARRANGEPERSONID);
  }

  /**
   * 获取单位
   * 
   * @return 单位
   */
  public String getCastunitid() {
    return (String) this.getAttributeValue(SaleOrderBVO.CASTUNITID);
  }

  /**
   * 获取捆绑件对应来源订单行ID
   * 
   * @return 捆绑件对应来源订单行ID
   */
  public String getCbindsrcid() {
    return (String) this.getAttributeValue(SaleOrderBVO.CBINDSRCID);
  }

  /**
   * 获取品牌
   * 
   * @return 品牌
   */
  public String getCbrandid() {
    return (String) this.getAttributeValue(SaleOrderBVO.CBRANDID);
  }

  /**
   * 获取合同子表
   * 
   * @return 合同子表
   */
  public String getCctmanagebid() {
    return (String) this.getAttributeValue(SaleOrderBVO.CCTMANAGEBID);
  }

  /**
   * 获取合同主表
   * 
   * @return 合同主表
   */
  public String getCctmanageid() {
    return (String) this.getAttributeValue(SaleOrderBVO.CCTMANAGEID);
  }

  /**
   * 获取本位币
   * 
   * @return 本位币
   */
  public String getCcurrencyid() {
    return (String) this.getAttributeValue(SaleOrderBVO.CCURRENCYID);
  }

  /**
   * 获取换货行对应退货行
   * 
   * @return 换货行对应退货行
   */
  public String getCexchangesrcretid() {
    return (String) this.getAttributeValue(SaleOrderBVO.CEXCHANGESRCRETID);
  }

  /**
   * 获取工厂
   * 
   * @return 工厂
   */
  public String getCfactoryid() {
    return (String) this.getAttributeValue(SaleOrderBVO.CFACTORYID);
  }

  /**
   * 获取源头单据子表
   * 
   * @return 源头单据子表
   */
  public String getCfirstbid() {
    return (String) this.getAttributeValue(SaleOrderBVO.CFIRSTBID);
  }

  /**
   * 获取源头单据主表
   * 
   * @return 源头单据主表
   */
  public String getCfirstid() {
    return (String) this.getAttributeValue(SaleOrderBVO.CFIRSTID);
  }

  /**
   * 获取赠品行对应来源订单行ID
   * 
   * @return 赠品行对应来源订单行ID
   */
  public String getClargesssrcid() {
    return (String) this.getAttributeValue(SaleOrderBVO.CLARGESSSRCID);
  }

  /**
   * 获取物料最新版本
   * 
   * @return 物料最新版本
   */
  public String getCmaterialid() {
    return (String) this.getAttributeValue(SaleOrderBVO.CMATERIALID);
  }

  /**
   * 获取物料编码
   * 
   * @return 物料编码
   */
  public String getCmaterialvid() {
    return (String) this.getAttributeValue(SaleOrderBVO.CMATERIALVID);
  }

  /**
   * 获取原产地区
   * 
   * @return 原产地区
   */
  public String getCorigareaid() {
    return (String) this.getAttributeValue(SaleOrderBVO.CORIGAREAID);
  }

  /**
   * 获取原产国
   * 
   * @return 原产国
   */
  public String getCorigcountryid() {
    return (String) this.getAttributeValue(SaleOrderBVO.CORIGCOUNTRYID);
  }

  /**
   * 获取价格组成
   * 
   * @return 价格组成
   */
  public String getCpriceformid() {
    return (String) this.getAttributeValue(SaleOrderBVO.CPRICEFORMID);
  }

  /**
   * 获取价格项目
   * 
   * @return 价格项目
   */
  public String getCpriceitemid() {
    return (String) this.getAttributeValue(SaleOrderBVO.CPRICEITEMID);
  }

  /**
   * 获取价目表
   * 
   * @return 价目表
   */
  public String getCpriceitemtableid() {
    return (String) this.getAttributeValue(SaleOrderBVO.CPRICEITEMTABLEID);
  }

  /**
   * 获取价格政策
   * 
   * @return 价格政策
   */
  public String getCpricepolicyid() {
    return (String) this.getAttributeValue(SaleOrderBVO.CPRICEPOLICYID);
  }

  /**
   * 获取产品线
   * 
   * @return 产品线
   */
  public String getCprodlineid() {
    return (String) this.getAttributeValue(SaleOrderBVO.CPRODLINEID);
  }

  /**
   * 获取生产厂商
   * 
   * @return 生产厂商
   */
  public String getCproductorid() {
    return (String) this.getAttributeValue(SaleOrderBVO.CPRODUCTORID);
  }

  /**
   * 获取结算利润中心最新版本
   * 
   * @return 结算利润中心最新版本
   */
  public String getCprofitcenterid() {
    return (String) this.getAttributeValue(SaleOrderBVO.CPROFITCENTERID);
  }

  /**
   * 获取结算利润中心
   * 
   * @return 结算利润中心
   */
  public String getCprofitcentervid() {
    return (String) this.getAttributeValue(SaleOrderBVO.CPROFITCENTERVID);
  }

  /**
   * 获取项目
   * 
   * @return 项目
   */
  public String getCprojectid() {
    return (String) this.getAttributeValue(SaleOrderBVO.CPROJECTID);
  }

  /**
   * 获取报价单位
   * 
   * @return 报价单位
   */
  public String getCqtunitid() {
    return (String) this.getAttributeValue(SaleOrderBVO.CQTUNITID);
  }

  /**
   * 获取质量等级
   * 
   * @return 质量等级
   */
  public String getCqualitylevelid() {
    return (String) this.getAttributeValue(SaleOrderBVO.CQUALITYLEVELID);
  }

  /**
   * 获取收货国家/地区
   * 
   * @return 收获国家/地区
   */
  public String getCrececountryid() {
    return (String) this.getAttributeValue(SaleOrderBVO.CRECECOUNTRYID);
  }

  /**
   * 获取收货地点
   * 
   * @return 收货地点
   */
  public String getCreceiveadddocid() {
    return (String) this.getAttributeValue(SaleOrderBVO.CRECEIVEADDDOCID);
  }

  /**
   * 获取收货地址
   * 
   * @return 收货地址
   */
  public String getCreceiveaddrid() {
    return (String) this.getAttributeValue(SaleOrderBVO.CRECEIVEADDRID);
  }

  /**
   * 获取收货地区
   * 
   * @return 收货地区
   */
  public String getCreceiveareaid() {
    return (String) this.getAttributeValue(SaleOrderBVO.CRECEIVEAREAID);
  }

  /**
   * 获取收货客户
   * 
   * @return 收货客户
   */
  public String getCreceivecustid() {
    return (String) this.getAttributeValue(SaleOrderBVO.CRECEIVECUSTID);
  }

  /**
   * 获取退货政策
   * 
   * @return 退货政策
   */
  public String getCretpolicyid() {
    return (String) this.getAttributeValue(SaleOrderBVO.CRETPOLICYID);
  }

  /**
   * 获取退货原因
   * 
   * @return 退货原因
   */
  public String getCretreasonid() {
    return (String) this.getAttributeValue(SaleOrderBVO.CRETREASONID);
  }

  /**
   * 获取行号
   * 
   * @return 行号
   */
  public String getCrowno() {
    return (String) this.getAttributeValue(SaleOrderBVO.CROWNO);
  }

  /**
   * 获取销售订单附表
   * 
   * @return 销售订单附表
   */
  public String getCsaleorderbid() {
    return (String) this.getAttributeValue(SaleOrderBVO.CSALEORDERBID);
  }

  /**
   * 获取销售订单主表_主键
   * 
   * @return 销售订单主表_主键
   */
  public String getCsaleorderid() {
    return (String) this.getAttributeValue(SaleOrderBVO.CSALEORDERID);
  }

  /**
   * 获取发货国家/地区
   * 
   * @return 发货国家/地区
   */
  public String getCsendcountryid() {
    return (String) this.getAttributeValue(SaleOrderBVO.CSENDCOUNTRYID);
  }

  /**
   * 获取发货库存组织最新版本
   * 
   * @return 发货库存组织最新版本
   */
  public String getCsendstockorgid() {
    return (String) this.getAttributeValue(SaleOrderBVO.CSENDSTOCKORGID);
  }

  /**
   * 获取发货库存组织
   * 
   * @return 发货库存组织
   */
  public String getCsendstockorgvid() {
    return (String) this.getAttributeValue(SaleOrderBVO.CSENDSTOCKORGVID);
  }

  /**
   * 获取发货仓库
   * 
   * @return 发货仓库
   */
  public String getCsendstordocid() {
    return (String) this.getAttributeValue(SaleOrderBVO.CSENDSTORDOCID);
  }

  /**
   * 获取结算财务组织最新版本
   * 
   * @return 结算财务组织最新版本
   */
  public String getCsettleorgid() {
    return (String) this.getAttributeValue(SaleOrderBVO.CSETTLEORGID);
  }

  /**
   * 获取结算财务组织
   * 
   * @return 结算财务组织
   */
  public String getCsettleorgvid() {
    return (String) this.getAttributeValue(SaleOrderBVO.CSETTLEORGVID);
  }

  /**
   * 获取来源单据附表
   * 
   * @return 来源单据附表
   */
  public String getCsrcbid() {
    return (String) this.getAttributeValue(SaleOrderBVO.CSRCBID);
  }

  /**
   * 获取来源单据主表
   * 
   * @return 来源单据主表
   */
  public String getCsrcid() {
    return (String) this.getAttributeValue(SaleOrderBVO.CSRCID);
  }

  /**
   * 获取税码
   * 
   * @return 税码
   */
  public String getCtaxcodeid() {
    return (String) this.getAttributeValue(SaleOrderBVO.CTAXCODEID);
  }

  /**
   * 获取报税国家和地区
   * 
   * @return 报税国家/地区
   */
  public String getCtaxcountryid() {
    return (String) this.getAttributeValue(SaleOrderBVO.CTAXCOUNTRYID);
  }

  /**
   * 获取物流组织最新版本
   * 
   * @return 物流组织最新版本
   */
  public String getCtrafficorgid() {
    return (String) this.getAttributeValue(SaleOrderBVO.CTRAFFICORGID);
  }

  /**
   * 获取物流组织
   * 
   * @return 物流组织
   */
  public String getCtrafficorgvid() {
    return (String) this.getAttributeValue(SaleOrderBVO.CTRAFFICORGVID);
  }

  /**
   * 获取主单位
   * 
   * @return 主单位
   */
  public String getCunitid() {
    return (String) this.getAttributeValue(SaleOrderBVO.CUNITID);
  }

  /**
   * 获取供应商
   * 
   * @return 供应商
   */
  public String getCvendorid() {
    return (String) this.getAttributeValue(SaleOrderBVO.CVENDORID);
  }

  /**
   * 获取单据日期
   * 
   * @return 单据日期
   */
  public UFDate getDbilldate() {
    return (UFDate) this.getAttributeValue(SaleOrderBVO.DBILLDATE);
  }

  public Integer getDr() {
    return (Integer) this.getAttributeValue(SaleOrderHVO.DR);
  }

  /**
   * 获取到货日期
   * 
   * @return 到货日期
   */
  public UFDate getDreceivedate() {
    return (UFDate) this.getAttributeValue(SaleOrderBVO.DRECEIVEDATE);
  }

  /**
   * 获取发货日期
   * 
   * @return 发货日期
   */
  public UFDate getDsenddate() {
    return (UFDate) this.getAttributeValue(SaleOrderBVO.DSENDDATE);
  }

  /**
   * 获取购销类型
   * 
   * @return 购销类型
   */
  public Integer getFbuysellflag() {
    return (Integer) this.getAttributeValue(SaleOrderBVO.FBUYSELLFLAG);
  }

  /**
   * 获取赠品价格分摊方式
   * 
   * @return 赠品价格分摊方式
   * @see Largesstype
   */
  public Integer getFlargesstypeflag() {
    return (Integer) this.getAttributeValue(SaleOrderBVO.FLARGESSTYPEFLAG);
  }

  /**
   * 获取退换货标记
   * 
   * @return 退换货标记
   * @see Fretexchange
   */
  public Integer getFretexchange() {
    return (Integer) this.getAttributeValue(SaleOrderBVO.FRETEXCHANGE);
  }

  /**
   * 获取行状态
   * 
   * @return 行状态
   * @see BillStatus
   */
  public Integer getFrowstatus() {
    return (Integer) this.getAttributeValue(SaleOrderBVO.FROWSTATUS);
  }

  /**
   * 获取报税类别
   * 
   * @return 报税类别
   */
  public Integer getFtaxtypeflag() {
    return (Integer) this.getAttributeValue(SaleOrderBVO.FTAXTYPEFLAG);
  }

  @Override
  public IVOMeta getMetaData() {
    IVOMeta meta = VOMetaFactory.getInstance().getVOMeta("so.so_saleorder_b");
    return meta;
  }

  /**
   * 获取累计安排生产订单数量
   * 
   * @return 累计安排生产订单数量
   */
  public UFDouble getNarrangemonum() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NARRANGEMONUM);
  }

  /**
   * 获取累计安排请购单数量
   * 
   * @return 累计安排请购单数量
   */
  public UFDouble getNarrangepoappnum() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NARRANGEPOAPPNUM);
  }

  /**
   * 获取累计安排采购订单数量
   * 
   * @return 累计安排采购订单数量
   */
  public UFDouble getNarrangeponum() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NARRANGEPONUM);
  }

  /**
   * 获取累计安排委外订单数量
   * 
   * @return 累计安排委外订单数量
   */
  public UFDouble getNarrangescornum() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NARRANGESCORNUM);
  }

  /**
   * 获取累计安排调拨申请数量
   * 
   * @return 累计安排调拨申请数量
   */
  public UFDouble getNarrangetoappnum() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NARRANGETOAPPNUM);
  }

  /**
   * 获取累计安排调拨订单数量
   * 
   * @return 累计安排调拨订单数量
   */
  public UFDouble getNarrangetoornum() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NARRANGETOORNUM);
  }

  /**
   * 获取询价原币无税净价
   * 
   * @return 询价原币无税净价
   */
  public UFDouble getNaskqtorignetprice() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NASKQTORIGNETPRICE);
  }

  /**
   * 获取询价原币无税单价
   * 
   * @return 询价原币无税单价
   */
  public UFDouble getNaskqtorigprice() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NASKQTORIGPRICE);
  }

  /**
   * 获取询价原币含税单价
   * 
   * @return 询价原币含税单价
   */
  public UFDouble getNaskqtorigtaxprc() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NASKQTORIGTAXPRC);
  }

  /**
   * 获取询价原币含税净价
   * 
   * @return 询价原币含税净价
   */
  public UFDouble getNaskqtorigtxntprc() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NASKQTORIGTXNTPRC);
  }

  /**
   * 获取数量
   * 
   * @return 数量
   */
  public UFDouble getNastnum() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NASTNUM);
  }

  /**
   * 获取nbforigsubmny
   * 
   * @return nbforigsubmny
   */
  public UFDouble getNbforigsubmny() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NBFORIGSUBMNY);
  }

  /**
   * 获取价税金额
   * 
   * @return 计税金额
   */
  public UFDouble getNcaltaxmny() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NCALTAXMNY);
  }

  /**
   * 获取本币折扣额
   * 
   * @return 本币折扣额
   */
  public UFDouble getNdiscount() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NDISCOUNT);
  }

  /**
   * 获取整单折扣
   * 
   * @return 整单折扣
   */
  public UFDouble getNdiscountrate() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NDISCOUNTRATE);
  }

  /**
   * 获取折本汇率
   * 
   * @return 折本汇率
   */
  public UFDouble getNexchangerate() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NEXCHANGERATE);
  }

  /**
   * 获取全局本位币汇率
   * 
   * @return 全局本位币汇率
   */
  public UFDouble getNglobalexchgrate() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NGLOBALEXCHGRATE);
  }

  /**
   * 获取全局本币无税金额
   * 
   * @return 全局本币无税金额
   */
  public UFDouble getNglobalmny() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NGLOBALMNY);
  }

  /**
   * 获取全局本币价税合计
   * 
   * @return 全局本币价税合计
   */
  public UFDouble getNglobaltaxmny() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NGLOBALTAXMNY);
  }

  /**
   * 获取集团本位币汇率
   * 
   * @return 集团本位币汇率
   */
  public UFDouble getNgroupexchgrate() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NGROUPEXCHGRATE);
  }

  /**
   * 获取集团本币无税金额
   * 
   * @return 集团本币无税金额
   */
  public UFDouble getNgroupmny() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NGROUPMNY);
  }

  /**
   * 获取集团本币价税合计
   * 
   * @return 集团本币价税合计
   */
  public UFDouble getNgrouptaxmny() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NGROUPTAXMNY);
  }

  /**
   * 获取ninvoiceauditnum
   * 
   * @return ninvoiceauditnum
   */
  public UFDouble getNinvoiceauditnum() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NINVOICEAUDITNUM);
  }

  /**
   * 获取ninvunfinisednum
   * 
   * @return ninvunfinisednum
   */
  public UFDouble getNinvunfinisednum() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NINVUNFINISEDNUM);
  }

  /**
   * 获取单品折扣率
   * 
   * @return 单品折扣率
   */
  public UFDouble getNitemdiscountrate() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NITEMDISCOUNTRATE);
  }

  /**
   * 获取赠品价格分摊前无税金额
   * 
   * @return 赠品价格分摊前无税金额
   */
  public UFDouble getNlargessmny() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NLARGESSMNY);
  }

  /**
   * 获取赠品价格分摊前价税合计
   * 
   * @return 赠品价格分摊前价税合计
   */
  public UFDouble getNlargesstaxmny() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NLARGESSTAXMNY);
  }

  /**
   * 获取nlossnotauditnum
   * 
   * @return nlossnotauditnum
   */
  public UFDouble getNlossnotauditnum() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NLOSSNOTAUDITNUM);
  }

  /**
   * 获取本币无税金额
   * 
   * @return 本币无税金额
   */
  public UFDouble getNmny() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NMNY);
  }

  /**
   * 获取主本币无税净价
   * 
   * @return 主本币无税净价
   */
  public UFDouble getNnetprice() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NNETPRICE);
  }

  /**
   * 获取nnotarnum
   * 
   * @return nnotarnum
   */
  public UFDouble getNnotarnum() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NNOTARNUM);
  }

  /**
   * 获取nnotcostnum
   * 
   * @return nnotcostnum
   */
  public UFDouble getNnotcostnum() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NNOTCOSTNUM);
  }

  /**
   * 获取主数量
   * 
   * @return 主数量
   */
  public UFDouble getNnum() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NNUM);
  }

  /**
   * 获取折扣额
   * 
   * @return 折扣额
   */
  public UFDouble getNorigdiscount() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NORIGDISCOUNT);
  }

  /**
   * 获取无税金额
   * 
   * @return 无税金额
   */
  public UFDouble getNorigmny() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NORIGMNY);
  }

  /**
   * 获取主无税净价
   * 
   * @return 主无税净价
   */
  public UFDouble getNorignetprice() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NORIGNETPRICE);
  }

  /**
   * 获取主无税单价
   * 
   * @return 主无税单价
   */
  public UFDouble getNorigprice() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NORIGPRICE);
  }

  /**
   * 获取累计冲抵金额
   * 
   * @return 累计冲抵金额
   */
  public UFDouble getNorigsubmny() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NORIGSUBMNY);
  }

  /**
   * 获取价税合计
   * 
   * @return 价税合计
   */
  public UFDouble getNorigtaxmny() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NORIGTAXMNY);
  }

  /**
   * 获取主含税净价
   * 
   * @return 主含税净价
   */
  public UFDouble getNorigtaxnetprice() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NORIGTAXNETPRICE);
  }

  /**
   * 获取税额
   * 
   * @return 税额
   */
  // public UFDouble getNorigtax() {
  // return (UFDouble) this.getAttributeValue(SaleOrderBVO.NORIGTAX);
  // }

  /**
   * 获取主含税单价
   * 
   * @return 主含税单价
   */
  public UFDouble getNorigtaxprice() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NORIGTAXPRICE);
  }

  /**
   * 获取noutauditnum
   * 
   * @return noutauditnum
   */
  public UFDouble getNoutauditnum() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NOUTAUDITNUM);
  }

  /**
   * 获取noutnotauditnum
   * 
   * @return noutnotauditnum
   */
  public UFDouble getNoutnotauditnum() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NOUTNOTAUDITNUM);
  }

  /**
   * 获取noutunfinisednum
   * 
   * @return noutunfinisednum
   */
  public UFDouble getNoutunfinisednum() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NOUTUNFINISEDNUM);
  }

  /**
   * 获取件数
   * 
   * @return 件数
   */
  public UFDouble getNpiece() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NPIECE);
  }

  /**
   * 获取主本币无税单价
   * 
   * @return 主本币无税单价
   */
  public UFDouble getNprice() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NPRICE);
  }

  /**
   * 获取本币无税净价
   * 
   * @return 本币无税净价
   */
  public UFDouble getNqtnetprice() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NQTNETPRICE);
  }

  /**
   * 获取无税净价
   * 
   * @return 无税净价
   */
  public UFDouble getNqtorignetprice() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NQTORIGNETPRICE);
  }

  /**
   * 获取无税单价
   * 
   * @return 无税单价
   */
  public UFDouble getNqtorigprice() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NQTORIGPRICE);
  }

  /**
   * 获取含税净价
   * 
   * @return 含税净价
   */
  public UFDouble getNqtorigtaxnetprc() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NQTORIGTAXNETPRC);
  }

  /**
   * 获取含税单价
   * 
   * @return 含税单价
   */
  public UFDouble getNqtorigtaxprice() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NQTORIGTAXPRICE);
  }

  /**
   * 获取本币无税单价
   * 
   * @return 本币无税单价
   */
  public UFDouble getNqtprice() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NQTPRICE);
  }

  /**
   * 获取本币含税净价
   * 
   * @return 本币含税净价
   */
  public UFDouble getNqttaxnetprice() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NQTTAXNETPRICE);
  }

  /**
   * 获取本币含税单价
   * 
   * @return 本币含税单价
   */
  public UFDouble getNqttaxprice() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NQTTAXPRICE);
  }

  /**
   * 获取报价单位数量
   * 
   * @return 报价单位数量
   */
  public UFDouble getNqtunitnum() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NQTUNITNUM);
  }

  /**
   * 获取预留数量
   * 
   * @return 预留数量
   */
  public UFDouble getNreqrsnum() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NREQRSNUM);
  }

  /**
   * 获取nsendauditnum
   * 
   * @return nsendauditnum
   */
  public UFDouble getNsendauditnum() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NSENDAUDITNUM);
  }

  /**
   * 获取nsendunfinisednum
   * 
   * @return nsendunfinisednum
   */
  public UFDouble getNsendunfinisednum() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NSENDUNFINISEDNUM);
  }

  /**
   * 获取本币税额
   * 
   * @return 本币税额
   */
  public UFDouble getNtax() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NTAX);
  }

  /**
   * 获取本币价税合计
   * 
   * @return 本币价税合计
   */
  public UFDouble getNtaxmny() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NTAXMNY);
  }

  /**
   * 获取主本币含税净价
   * 
   * @return 主本币含税净价
   */
  public UFDouble getNtaxnetprice() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NTAXNETPRICE);
  }

  /**
   * 获取主本币含税单价
   * 
   * @return 主本币含税单价
   */
  public UFDouble getNtaxprice() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NTAXPRICE);
  }

  /**
   * 获取税率
   * 
   * @return 税率
   */
  public UFDouble getNtaxrate() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NTAXRATE);
  }

  /**
   * 获取累计确认应收金额
   * 
   * @return 累计确认应收金额
   */
  public UFDouble getNtotalarmny() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NTOTALARMNY);
  }

  /**
   * 获取累计确认应收数量
   * 
   * @return 累计确认应收数量
   */
  public UFDouble getNtotalarnum() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NTOTALARNUM);
  }

  /**
   * 获取累计成本结算数量
   * 
   * @return 累计成本结算数量
   */
  public UFDouble getNtotalcostnum() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NTOTALCOSTNUM);
  }

  /**
   * 获取累计暂估应收金额
   * 
   * @return 累计暂估应收金额
   */
  public UFDouble getNtotalestarmny() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NTOTALESTARMNY);
  }

  /**
   * 获取累计暂估应收数量
   * 
   * @return 累计暂估应收数量
   */
  public UFDouble getNtotalestarnum() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NTOTALESTARNUM);
  }

  /**
   * 获取累计开票数量
   * 
   * @return 累计开票数量
   */
  public UFDouble getNtotalinvoicenum() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NTOTALINVOICENUM);
  }

  /**
   * 获取累计应发未出库数量
   * 
   * @return 累计应发未出库数量
   */
  public UFDouble getNtotalnotoutnum() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NTOTALNOTOUTNUM);
  }

  /**
   * 获取累计出库数量
   * 
   * @return 累计出库数量
   */
  public UFDouble getNtotaloutnum() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NTOTALOUTNUM);
  }

  /**
   * 获取累计财务核销金额
   * 
   * @return 累计财务核销金额
   */
  public UFDouble getNtotalpaymny() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NTOTALPAYMNY);
  }

  /**
   * 获取累计生成计划订单数量
   * 
   * @return 累计生成计划订单数量
   */
  public UFDouble getNtotalplonum() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NTOTALPLONUM);
  }

  /**
   * 获取累计退货数量
   * 
   * @return 累计退货数量
   */
  public UFDouble getNtotalreturnnum() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NTOTALRETURNNUM);
  }

  /**
   * 获取累计出库对冲数量
   * 
   * @return 累计出库对冲数量
   */
  public UFDouble getNtotalrushnum() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NTOTALRUSHNUM);
  }

  /**
   * 获取累计发货数量
   * 
   * @return 累计发货数量
   */
  public UFDouble getNtotalsendnum() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NTOTALSENDNUM);
  }

  /**
   * 获取累计签收数量
   * 
   * @return 累计签收数量
   */
  public UFDouble getNtotalsignnum() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NTOTALSIGNNUM);
  }

  /**
   * 获取累计发出商品数量
   * 
   * @return 累计发出商品数量
   */
  public UFDouble getNtotaltradenum() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NTOTALTRADENUM);
  }

  /**
   * 获取累计途损数量
   * 
   * @return 累计途损数量
   */
  public UFDouble getNtranslossnum() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NTRANSLOSSNUM);
  }

  /**
   * 获取体积
   * 
   * @return 体积
   */
  public UFDouble getNvolume() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NVOLUME);
  }

  /**
   * 获取重量
   * 
   * @return 重量
   */
  public UFDouble getNweight() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NWEIGHT);
  }

  /**
   * 获取批次档案
   * 
   * @return 批次档案
   */
  public String getPk_batchcode() {
    return (String) this.getAttributeValue(SaleOrderBVO.PK_BATCHCODE);
  }

  /**
   * 获取集团
   * 
   * @return 集团
   */
  public String getPk_group() {
    return (String) this.getAttributeValue(SaleOrderBVO.PK_GROUP);
  }

  /**
   * 获取销售组织
   * 
   * @return 销售组织
   */
  public String getPk_org() {
    return (String) this.getAttributeValue(SaleOrderBVO.PK_ORG);
  }

  /**
   * 获取来源时间戳
   * 
   * @return 时间戳
   */
  public UFDateTime getSrcbts() {
    return (UFDateTime) this.getAttributeValue(SaleOrderBVO.SRCBTS);
  }

  /**
   * 获取来源组织
   * 
   * @return srcorgid
   */
  public String getSrcorgid() {
    return (String) this.getAttributeValue(SaleOrderBVO.SRCORGID);
  }

  /**
   * 获取来源时间戳
   * 
   * @return 时间戳
   */
  public UFDateTime getSrcts() {
    return (UFDateTime) this.getAttributeValue(SaleOrderBVO.SRCTS);
  }

  /**
   * 获取最后货源安排时间
   * 
   * @return 最后货源安排时间
   */
  public UFDateTime getTlastarrangetime() {
    return (UFDateTime) this.getAttributeValue(SaleOrderBVO.TLASTARRANGETIME);
  }

  /**
   * 获取时间戳
   * 
   * @return 时间戳
   */
  public UFDateTime getTs() {
    return (UFDateTime) this.getAttributeValue(SaleOrderBVO.TS);
  }

  /**
   * 获取批次
   * 
   * @return 批次
   */
  public String getVbatchcode() {
    return (String) this.getAttributeValue(SaleOrderBVO.VBATCHCODE);
  }

  /**
   * 获取自定义项1
   * 
   * @return 自定义项1
   */
  public String getVbdef1() {
    return (String) this.getAttributeValue(SaleOrderBVO.VBDEF1);
  }

  /**
   * 获取自定义项10
   * 
   * @return 自定义项10
   */
  public String getVbdef10() {
    return (String) this.getAttributeValue(SaleOrderBVO.VBDEF10);
  }

  /**
   * 获取自定义项11
   * 
   * @return 自定义项11
   */
  public String getVbdef11() {
    return (String) this.getAttributeValue(SaleOrderBVO.VBDEF11);
  }

  /**
   * 获取自定义项12
   * 
   * @return 自定义项12
   */
  public String getVbdef12() {
    return (String) this.getAttributeValue(SaleOrderBVO.VBDEF12);
  }

  /**
   * 获取自定义项13
   * 
   * @return 自定义项13
   */
  public String getVbdef13() {
    return (String) this.getAttributeValue(SaleOrderBVO.VBDEF13);
  }

  /**
   * 获取自定义项14
   * 
   * @return 自定义项14
   */
  public String getVbdef14() {
    return (String) this.getAttributeValue(SaleOrderBVO.VBDEF14);
  }

  /**
   * 获取自定义项15
   * 
   * @return 自定义项15
   */
  public String getVbdef15() {
    return (String) this.getAttributeValue(SaleOrderBVO.VBDEF15);
  }

  /**
   * 获取自定义项16
   * 
   * @return 自定义项16
   */
  public String getVbdef16() {
    return (String) this.getAttributeValue(SaleOrderBVO.VBDEF16);
  }

  /**
   * 获取自定义项17
   * 
   * @return 自定义项17
   */
  public String getVbdef17() {
    return (String) this.getAttributeValue(SaleOrderBVO.VBDEF17);
  }

  /**
   * 获取自定义项18
   * 
   * @return 自定义项18
   */
  public String getVbdef18() {
    return (String) this.getAttributeValue(SaleOrderBVO.VBDEF18);
  }

  /**
   * 获取自定义项19
   * 
   * @return 自定义项19
   */
  public String getVbdef19() {
    return (String) this.getAttributeValue(SaleOrderBVO.VBDEF19);
  }

  /**
   * 获取自定义项2
   * 
   * @return 自定义项2
   */
  public String getVbdef2() {
    return (String) this.getAttributeValue(SaleOrderBVO.VBDEF2);
  }

  /**
   * 获取自定义项20
   * 
   * @return 自定义项20
   */
  public String getVbdef20() {
    return (String) this.getAttributeValue(SaleOrderBVO.VBDEF20);
  }

  /**
   * 获取自定义项3
   * 
   * @return 自定义项3
   */
  public String getVbdef3() {
    return (String) this.getAttributeValue(SaleOrderBVO.VBDEF3);
  }

  /**
   * 获取自定义项4
   * 
   * @return 自定义项4
   */
  public String getVbdef4() {
    return (String) this.getAttributeValue(SaleOrderBVO.VBDEF4);
  }

  /**
   * 获取自定义项5
   * 
   * @return 自定义项5
   */
  public String getVbdef5() {
    return (String) this.getAttributeValue(SaleOrderBVO.VBDEF5);
  }

  /**
   * 获取自定义项6
   * 
   * @return 自定义项6
   */
  public String getVbdef6() {
    return (String) this.getAttributeValue(SaleOrderBVO.VBDEF6);
  }

  /**
   * 获取自定义项7
   * 
   * @return 自定义项7
   */
  public String getVbdef7() {
    return (String) this.getAttributeValue(SaleOrderBVO.VBDEF7);
  }

  /**
   * 获取自定义项8
   * 
   * @return 自定义项8
   */
  public String getVbdef8() {
    return (String) this.getAttributeValue(SaleOrderBVO.VBDEF8);
  }

  /**
   * 获取自定义项9
   * 
   * @return 自定义项9
   */
  public String getVbdef9() {
    return (String) this.getAttributeValue(SaleOrderBVO.VBDEF9);
  }

  /**
   * 获取修订理由
   * 
   * @return 修订理由
   */
  public String getVbrevisereason() {
    return (String) this.getAttributeValue(SaleOrderBVO.VBREVISEREASON);
  }

  /**
   * 获取换算率
   * 
   * @return 换算率
   */
  public String getVchangerate() {
    return (String) this.getAttributeValue(SaleOrderBVO.VCHANGERATE);
  }

  /**
   * 获取关闭原因
   * 
   * @return 关闭原因
   */
  public String getVclosereason() {
    return (String) this.getAttributeValue(SaleOrderBVO.VCLOSEREASON);
  }

  /**
   * 获取销售合同号
   * 
   * @return 销售合同号
   */
  public String getVctcode() {
    return (String) this.getAttributeValue(SaleOrderBVO.VCTCODE);
  }

  /**
   * 获取vcttype
   * 
   * @return vcttype
   */
  public String getVcttype() {
    return (String) this.getAttributeValue(SaleOrderBVO.VCTTYPE);
  }

  /**
   * 获取源头单据号
   * 
   * @return 源头单据号
   */
  public String getVfirstcode() {
    return (String) this.getAttributeValue(SaleOrderBVO.VFIRSTCODE);
  }

  /**
   * 获取源头单据行号
   * 
   * @return 源头单据行号
   */
  public String getVfirstrowno() {
    return (String) this.getAttributeValue(SaleOrderBVO.VFIRSTROWNO);
  }

  /**
   * 获取源头交易类型
   * 
   * @return 源头交易类型
   */
  public String getVfirsttrantype() {
    return (String) this.getAttributeValue(SaleOrderBVO.VFIRSTTRANTYPE);
  }
  
  /**
   * 获取促销价格表行ID
   * 
   * @return 促销价格表行ID
   */
  public String getCpromotpriceid() {
    return (String) this.getAttributeValue(SaleOrderBVO.CPROMOTPRICEID);
  }

  /**
   * 获取源头单据类型
   * 
   * @return 源头单据类型
   */
  public String getVfirsttype() {
    return (String) this.getAttributeValue(SaleOrderBVO.VFIRSTTYPE);
  }

  /**
   * 获取自由辅助属性1
   * 
   * @return 自由辅助属性1
   */
  public String getVfree1() {
    return (String) this.getAttributeValue(SaleOrderBVO.VFREE1);
  }

  /**
   * 获取自由辅助属性10
   * 
   * @return 自由辅助属性10
   */
  public String getVfree10() {
    return (String) this.getAttributeValue(SaleOrderBVO.VFREE10);
  }

  /**
   * 获取自由辅助属性2
   * 
   * @return 自由辅助属性2
   */
  public String getVfree2() {
    return (String) this.getAttributeValue(SaleOrderBVO.VFREE2);
  }

  /**
   * 获取自由辅助属性3
   * 
   * @return 自由辅助属性3
   */
  public String getVfree3() {
    return (String) this.getAttributeValue(SaleOrderBVO.VFREE3);
  }

  /**
   * 获取自由辅助属性4
   * 
   * @return 自由辅助属性4
   */
  public String getVfree4() {
    return (String) this.getAttributeValue(SaleOrderBVO.VFREE4);
  }

  /**
   * 获取自由辅助属性5
   * 
   * @return 自由辅助属性5
   */
  public String getVfree5() {
    return (String) this.getAttributeValue(SaleOrderBVO.VFREE5);
  }

  /**
   * 获取自由辅助属性6
   * 
   * @return 自由辅助属性6
   */
  public String getVfree6() {
    return (String) this.getAttributeValue(SaleOrderBVO.VFREE6);
  }

  /**
   * 获取自由辅助属性7
   * 
   * @return 自由辅助属性7
   */
  public String getVfree7() {
    return (String) this.getAttributeValue(SaleOrderBVO.VFREE7);
  }

  /**
   * 获取自由辅助属性8
   * 
   * @return 自由辅助属性8
   */
  public String getVfree8() {
    return (String) this.getAttributeValue(SaleOrderBVO.VFREE8);
  }

  /**
   * 获取自由辅助属性9
   * 
   * @return 自由辅助属性9
   */
  public String getVfree9() {
    return (String) this.getAttributeValue(SaleOrderBVO.VFREE9);
  }

  /**
   * 获取报价换算率
   * 
   * @return 报价换算率
   */
  public String getVqtunitrate() {
    return (String) this.getAttributeValue(SaleOrderBVO.VQTUNITRATE);
  }

  /**
   * 获取退货责任
   * 
   * @return 退货责任
   */
  public String getVreturnmode() {
    return (String) this.getAttributeValue(SaleOrderBVO.VRETURNMODE);
  }

  /**
   * 获取行备注
   * 
   * @return 行备注
   */
  public String getVrownote() {
    return (String) this.getAttributeValue(SaleOrderBVO.VROWNOTE);
  }

  /**
   * 获取来源单据号
   * 
   * @return 来源单据号
   */
  public String getVsrccode() {
    return (String) this.getAttributeValue(SaleOrderBVO.VSRCCODE);
  }

  /**
   * 获取来源单据行号
   * 
   * @return 来源单据行号
   */
  public String getVsrcrowno() {
    return (String) this.getAttributeValue(SaleOrderBVO.VSRCROWNO);
  }

  /**
   * 获取来源交易类型
   * 
   * @return 来源交易类型
   */
  public String getVsrctrantype() {
    return (String) this.getAttributeValue(SaleOrderBVO.VSRCTRANTYPE);
  }

  /**
   * 获取来源单据类型
   * 
   * @return 来源单据类型
   */
  public String getVsrctype() {
    return (String) this.getAttributeValue(SaleOrderBVO.VSRCTYPE);
  }

  /**
   * 获取买赠促销类型
   * 
   * @return 买赠促销类型
   */
  public String getCbuypromottypeid() {
    return (String) this.getAttributeValue(SaleOrderBVO.CBUYPROMOTTYPEID);
  }

  /**
   * 获取询价促销类型
   * 
   * @return 询价促销类型
   */
  public String getCprcpromottypeid() {
    return (String) this.getAttributeValue(SaleOrderBVO.CPRCPROMOTTYPEID);
  }

  /**
   * 获取客户单据号
   * 
   * @return 客户单据号
   */
  public String getVcustombillcode() {
    return (String) this.getAttributeValue(SaleOrderBVO.VCUSTOMBILLCODE);
  }

  /**
   * 获取发货利润中心最新版本
   * 
   * @return 发货利润中心最新版本
   */
  public String getCsprofitcenterid() {
    return (String) this.getAttributeValue(SaleOrderBVO.CSPROFITCENTERID);
  }

  /**
   * 获取发货利润中心
   * 
   * @return 发货利润中心
   */
  public String getCsprofitcentervid() {
    return (String) this.getAttributeValue(SaleOrderBVO.CSPROFITCENTERVID);
  }

  /**
   * 设置是否货源安排完毕
   * 
   * @param barrangedflag 是否货源安排完毕
   */
  public void setBarrangedflag(UFBoolean barrangedflag) {
    this.setAttributeValue(SaleOrderBVO.BARRANGEDFLAG, barrangedflag);
  }

  /**
   * 设置收入结算关闭
   * 
   * @param bbarsettleflag 收入结算关闭
   */
  public void setBbarsettleflag(UFBoolean bbarsettleflag) {
    this.setAttributeValue(SaleOrderBVO.BBARSETTLEFLAG, bbarsettleflag);
  }

  /**
   * 设置成本结算关闭
   * 
   * @param bbcostsettleflag 成本结算关闭
   */
  public void setBbcostsettleflag(UFBoolean bbcostsettleflag) {
    this.setAttributeValue(SaleOrderBVO.BBCOSTSETTLEFLAG, bbcostsettleflag);
  }

  /**
   * 设置捆绑存货
   * 
   * @param bbindflag 捆绑存货
   */
  public void setBbindflag(UFBoolean bbindflag) {
    this.setAttributeValue(SaleOrderBVO.BBINDFLAG, bbindflag);
  }

  /**
   * 设置开票关闭
   * 
   * @param bbinvoicendflag 开票关闭
   */
  public void setBbinvoicendflag(UFBoolean bbinvoicendflag) {
    this.setAttributeValue(SaleOrderBVO.BBINVOICENDFLAG, bbinvoicendflag);
  }

  /**
   * 设置出库关闭
   * 
   * @param bboutendflag 出库关闭
   */
  public void setBboutendflag(UFBoolean bboutendflag) {
    this.setAttributeValue(SaleOrderBVO.BBOUTENDFLAG, bboutendflag);
  }

  /**
   * 设置发货关闭
   * 
   * @param bbsendendflag 发货关闭
   */
  public void setBbsendendflag(UFBoolean bbsendendflag) {
    this.setAttributeValue(SaleOrderBVO.BBSENDENDFLAG, bbsendendflag);
  }

  /**
   * 设置bbsettleendflag
   * 
   * @param bbsettleendflag bbsettleendflag
   */
  public void setBbsettleendflag(UFBoolean bbsettleendflag) {
    this.setAttributeValue(SaleOrderBVO.BBSETTLEENDFLAG, bbsettleendflag);
  }

  /**
   * 设置折扣类
   * 
   * @param bdiscountflag 折扣类
   */
  public void setBdiscountflag(UFBoolean bdiscountflag) {
    this.setAttributeValue(SaleOrderBVO.BDISCOUNTFLAG, bdiscountflag);
  }

  /**
   * 设置借出转销售
   * 
   * @param bjczxsflag 借出转销售
   */
  public void setBjczxsflag(UFBoolean bjczxsflag) {
    this.setAttributeValue(SaleOrderBVO.BJCZXSFLAG, bjczxsflag);
  }

  /**
   * 设置服务类
   * 
   * @param blaborflag 服务类
   */
  public void setBlaborflag(UFBoolean blaborflag) {
    this.setAttributeValue(SaleOrderBVO.BLABORFLAG, blaborflag);
  }

  /**
   * 设置赠品
   * 
   * @param blargessflag 赠品
   */
  public void setBlargessflag(UFBoolean blargessflag) {
    this.setAttributeValue(SaleOrderBVO.BLARGESSFLAG, blargessflag);
  }

  /**
   * 设置bprerowcloseflag
   * 
   * @param bprerowcloseflag bprerowcloseflag
   */
  public void setBprerowcloseflag(UFBoolean bprerowcloseflag) {
    this.setAttributeValue(SaleOrderBVO.BPREROWCLOSEFLAG, bprerowcloseflag);
  }

  /**
   * 设置 三角贸易
   * 
   * @param btriatradeflag 三角贸易
   */
  public void setBtriatradeflag(UFBoolean btriatradeflag) {
    this.setAttributeValue(SaleOrderBVO.BTRIATRADEFLAG, btriatradeflag);
  }

  /**
   * 设置应收组织最新版本
   * 
   * @param carorgid 应收组织最新版本
   */
  public void setCarorgid(String carorgid) {
    this.setAttributeValue(SaleOrderBVO.CARORGID, carorgid);
  }

  /**
   * 设置应收组织
   * 
   * @param carorgvid 应收组织
   */
  public void setCarorgvid(String carorgvid) {
    this.setAttributeValue(SaleOrderBVO.CARORGVID, carorgvid);
  }

  /**
   * 设置最后货源安排人
   * 
   * @param carrangepersonid 最后货源安排人
   */
  public void setCarrangepersonid(String carrangepersonid) {
    this.setAttributeValue(SaleOrderBVO.CARRANGEPERSONID, carrangepersonid);
  }

  /**
   * 设置单位
   * 
   * @param castunitid 单位
   */
  public void setCastunitid(String castunitid) {
    this.setAttributeValue(SaleOrderBVO.CASTUNITID, castunitid);
  }

  /**
   * 设置捆绑件对应来源订单行ID
   * 
   * @param cbindsrcid 捆绑件对应来源订单行ID
   */
  public void setCbindsrcid(String cbindsrcid) {
    this.setAttributeValue(SaleOrderBVO.CBINDSRCID, cbindsrcid);
  }

  /**
   * 设置品牌
   * 
   * @param cbrandid 品牌
   */
  public void setCbrandid(String cbrandid) {
    this.setAttributeValue(SaleOrderBVO.CBRANDID, cbrandid);
  }

  /**
   * 设置合同子表
   * 
   * @param cctmanagebid 合同子表
   */
  public void setCctmanagebid(String cctmanagebid) {
    this.setAttributeValue(SaleOrderBVO.CCTMANAGEBID, cctmanagebid);
  }

  /**
   * 设置合同主表
   * 
   * @param cctmanageid 合同主表
   */
  public void setCctmanageid(String cctmanageid) {
    this.setAttributeValue(SaleOrderBVO.CCTMANAGEID, cctmanageid);
  }

  /**
   * 设置本位币
   * 
   * @param ccurrencyid 本位币
   */
  public void setCcurrencyid(String ccurrencyid) {
    this.setAttributeValue(SaleOrderBVO.CCURRENCYID, ccurrencyid);
  }

  /**
   * 设置换货行对应退货行
   * 
   * @param cexchangesrcretid 换货行对应退货行
   */
  public void setCexchangesrcretid(String cexchangesrcretid) {
    this.setAttributeValue(SaleOrderBVO.CEXCHANGESRCRETID, cexchangesrcretid);
  }

  /**
   * 设置工厂
   * 
   * @param cfactoryid 工厂
   */
  public void setCfactoryid(String cfactoryid) {
    this.setAttributeValue(SaleOrderBVO.CFACTORYID, cfactoryid);
  }

  /**
   * 设置源头单据子表
   * 
   * @param cfirstbid 源头单据子表
   */
  public void setCfirstbid(String cfirstbid) {
    this.setAttributeValue(SaleOrderBVO.CFIRSTBID, cfirstbid);
  }

  /**
   * 设置源头单据主表
   * 
   * @param cfirstid 源头单据主表
   */
  public void setCfirstid(String cfirstid) {
    this.setAttributeValue(SaleOrderBVO.CFIRSTID, cfirstid);
  }

  /**
   * 设置赠品行对应来源订单行ID
   * 
   * @param clargesssrcid 赠品行对应来源订单行ID
   */
  public void setClargesssrcid(String clargesssrcid) {
    this.setAttributeValue(SaleOrderBVO.CLARGESSSRCID, clargesssrcid);
  }

  /**
   * 设置物料最新版本
   * 
   * @param cmaterialid 物料最新版本
   */
  public void setCmaterialid(String cmaterialid) {
    this.setAttributeValue(SaleOrderBVO.CMATERIALID, cmaterialid);
  }

  /**
   * 设置物料编码
   * 
   * @param cmaterialvid 物料编码
   */
  public void setCmaterialvid(String cmaterialvid) {
    this.setAttributeValue(SaleOrderBVO.CMATERIALVID, cmaterialvid);
  }

  /**
   * 设置原产地区
   * 
   * @param corigareaid 原产地区
   */
  public void setCorigareaid(String corigareaid) {
    this.setAttributeValue(SaleOrderBVO.CORIGAREAID, corigareaid);
  }

  /**
   * 设置原产国
   * 
   * @param corigcountryid 原产国
   */
  public void setCorigcountryid(String corigcountryid) {
    this.setAttributeValue(SaleOrderBVO.CORIGCOUNTRYID, corigcountryid);
  }

  /**
   * 设置价格组成
   * 
   * @param cpriceformid 价格组成
   */
  public void setCpriceformid(String cpriceformid) {
    this.setAttributeValue(SaleOrderBVO.CPRICEFORMID, cpriceformid);
  }

  /**
   * 设置价格项目
   * 
   * @param cpriceitemid 价格项目
   */
  public void setCpriceitemid(String cpriceitemid) {
    this.setAttributeValue(SaleOrderBVO.CPRICEITEMID, cpriceitemid);
  }

  /**
   * 设置价目表
   * 
   * @param cpriceitemtableid 价目表
   */
  public void setCpriceitemtableid(String cpriceitemtableid) {
    this.setAttributeValue(SaleOrderBVO.CPRICEITEMTABLEID, cpriceitemtableid);
  }

  /**
   * 设置价格政策
   * 
   * @param cpricepolicyid 价格政策
   */
  public void setCpricepolicyid(String cpricepolicyid) {
    this.setAttributeValue(SaleOrderBVO.CPRICEPOLICYID, cpricepolicyid);
  }

  /**
   * 设置产品线
   * 
   * @param cprodlineid 产品线
   */
  public void setCprodlineid(String cprodlineid) {
    this.setAttributeValue(SaleOrderBVO.CPRODLINEID, cprodlineid);
  }

  /**
   * 设置生产厂商
   * 
   * @param cproductorid 生产厂商
   */
  public void setCproductorid(String cproductorid) {
    this.setAttributeValue(SaleOrderBVO.CPRODUCTORID, cproductorid);
  }

  /**
   * 设置结算利润中心最新版本
   * 
   * @param cprofitcenterid 结算利润中心最新版本
   */
  public void setCprofitcenterid(String cprofitcenterid) {
    this.setAttributeValue(SaleOrderBVO.CPROFITCENTERID, cprofitcenterid);
  }

  /**
   * 设置结算利润中心
   * 
   * @param cprofitcentervid 结算利润中心
   */
  public void setCprofitcentervid(String cprofitcentervid) {
    this.setAttributeValue(SaleOrderBVO.CPROFITCENTERVID, cprofitcentervid);
  }

  /**
   * 设置项目
   * 
   * @param cprojectid 项目
   */
  public void setCprojectid(String cprojectid) {
    this.setAttributeValue(SaleOrderBVO.CPROJECTID, cprojectid);
  }

  /**
   * 设置报价单位
   * 
   * @param cqtunitid 报价单位
   */
  public void setCqtunitid(String cqtunitid) {
    this.setAttributeValue(SaleOrderBVO.CQTUNITID, cqtunitid);
  }

  /**
   * 设置质量等级
   * 
   * @param cqualitylevelid 质量等级
   */
  public void setCqualitylevelid(String cqualitylevelid) {
    this.setAttributeValue(SaleOrderBVO.CQUALITYLEVELID, cqualitylevelid);
  }

  /**
   * 设置收货国家/地区
   * 
   * @param crececountryid 收货国家/地区
   */
  public void setCrececountryid(String crececountryid) {
    this.setAttributeValue(SaleOrderBVO.CRECECOUNTRYID, crececountryid);
  }

  /**
   * 设置收货地点
   * 
   * @param creceiveadddocid 收货地点
   */
  public void setCreceiveadddocid(String creceiveadddocid) {
    this.setAttributeValue(SaleOrderBVO.CRECEIVEADDDOCID, creceiveadddocid);
  }

  /**
   * 设置收货地址
   * 
   * @param creceiveaddrid 收货地址
   */
  public void setCreceiveaddrid(String creceiveaddrid) {
    this.setAttributeValue(SaleOrderBVO.CRECEIVEADDRID, creceiveaddrid);
  }

  /**
   * 设置收货地区
   * 
   * @param creceiveareaid 收货地区
   */
  public void setCreceiveareaid(String creceiveareaid) {
    this.setAttributeValue(SaleOrderBVO.CRECEIVEAREAID, creceiveareaid);
  }

  /**
   * 设置收货客户
   * 
   * @param creceivecustid 收货客户
   */
  public void setCreceivecustid(String creceivecustid) {
    this.setAttributeValue(SaleOrderBVO.CRECEIVECUSTID, creceivecustid);
  }

  /**
   * 设置退货政策
   * 
   * @param cretpolicyid 退货政策
   */
  public void setCretpolicyid(String cretpolicyid) {
    this.setAttributeValue(SaleOrderBVO.CRETPOLICYID, cretpolicyid);
  }

  /**
   * 设置退货原因
   * 
   * @param cretreasonid 退货原因
   */
  public void setCretreasonid(String cretreasonid) {
    this.setAttributeValue(SaleOrderBVO.CRETREASONID, cretreasonid);
  }

  /**
   * 设置行号
   * 
   * @param crowno 行号
   */
  public void setCrowno(String crowno) {
    this.setAttributeValue(SaleOrderBVO.CROWNO, crowno);
  }

  /**
   * 设置销售订单附表
   * 
   * @param csaleorderbid 销售订单附表
   */
  public void setCsaleorderbid(String csaleorderbid) {
    this.setAttributeValue(SaleOrderBVO.CSALEORDERBID, csaleorderbid);
  }

  /**
   * 设置销售订单主表_主键
   * 
   * @param csaleorderid 销售订单主表_主键
   */
  public void setCsaleorderid(String csaleorderid) {
    this.setAttributeValue(SaleOrderBVO.CSALEORDERID, csaleorderid);
  }

  /**
   * 设置发货国家/地区
   * 
   * @param csendcountryid 发货国家/地区
   */
  public void setCsendcountryid(String csendcountryid) {
    this.setAttributeValue(SaleOrderBVO.CSENDCOUNTRYID, csendcountryid);
  }

  /**
   * 设置发货库存组织最新版本
   * 
   * @param csendstockorgid 发货库存组织最新版本
   */
  public void setCsendstockorgid(String csendstockorgid) {
    this.setAttributeValue(SaleOrderBVO.CSENDSTOCKORGID, csendstockorgid);
  }

  /**
   * 设置发货库存组织
   * 
   * @param csendstockorgvid 发货库存组织
   */
  public void setCsendstockorgvid(String csendstockorgvid) {
    this.setAttributeValue(SaleOrderBVO.CSENDSTOCKORGVID, csendstockorgvid);
  }

  /**
   * 设置发货仓库
   * 
   * @param csendstordocid 发货仓库
   */
  public void setCsendstordocid(String csendstordocid) {
    this.setAttributeValue(SaleOrderBVO.CSENDSTORDOCID, csendstordocid);
  }

  /**
   * 设置结算财务组织最新版本
   * 
   * @param csettleorgid 结算财务组织最新版本
   */
  public void setCsettleorgid(String csettleorgid) {
    this.setAttributeValue(SaleOrderBVO.CSETTLEORGID, csettleorgid);
  }

  /**
   * 设置结算财务组织
   * 
   * @param csettleorgvid 结算财务组织
   */
  public void setCsettleorgvid(String csettleorgvid) {
    this.setAttributeValue(SaleOrderBVO.CSETTLEORGVID, csettleorgvid);
  }

  /**
   * 设置来源单据附表
   * 
   * @param csrcbid 来源单据附表
   */
  public void setCsrcbid(String csrcbid) {
    this.setAttributeValue(SaleOrderBVO.CSRCBID, csrcbid);
  }

  /**
   * 设置来源单据主表
   * 
   * @param csrcid 来源单据主表
   */
  public void setCsrcid(String csrcid) {
    this.setAttributeValue(SaleOrderBVO.CSRCID, csrcid);
  }

  /**
   * 设置税码
   * 
   * @param ctaxcodeid 税码
   */
  public void setCtaxcodeid(String ctaxcodeid) {
    this.setAttributeValue(SaleOrderBVO.CTAXCODEID, ctaxcodeid);
  }

  /**
   * 设置报税国家/地区
   * 
   * @param ctaxcountryid 报税国家/地区
   */
  public void setCtaxcountryid(String ctaxcountryid) {
    this.setAttributeValue(SaleOrderBVO.CTAXCOUNTRYID, ctaxcountryid);
  }

  /**
   * 设置物流组织最新版本
   * 
   * @param ctrafficorgid 物流组织最新版本
   */
  public void setCtrafficorgid(String ctrafficorgid) {
    this.setAttributeValue(SaleOrderBVO.CTRAFFICORGID, ctrafficorgid);
  }

  /**
   * 设置物流组织
   * 
   * @param ctrafficorgvid 物流组织
   */
  public void setCtrafficorgvid(String ctrafficorgvid) {
    this.setAttributeValue(SaleOrderBVO.CTRAFFICORGVID, ctrafficorgvid);
  }

  /**
   * 设置主单位
   * 
   * @param cunitid 主单位
   */
  public void setCunitid(String cunitid) {
    this.setAttributeValue(SaleOrderBVO.CUNITID, cunitid);
  }

  /**
   * 设置供应商
   * 
   * @param cvendorid 供应商
   */
  public void setCvendorid(String cvendorid) {
    this.setAttributeValue(SaleOrderBVO.CVENDORID, cvendorid);
  }

  /**
   * 设置单据日期
   * 
   * @param dbilldate 单据日期
   */
  public void setDbilldate(UFDate dbilldate) {
    this.setAttributeValue(SaleOrderBVO.DBILLDATE, dbilldate);
  }

  public void setDr(Integer dr) {
    this.setAttributeValue(SaleOrderHVO.DR, dr);
  }

  /**
   * 设置到货日期
   * 
   * @param dreceivedate 到货日期
   */
  public void setDreceivedate(UFDate dreceivedate) {
    this.setAttributeValue(SaleOrderBVO.DRECEIVEDATE, dreceivedate);
  }

  /**
   * 设置发货日期
   * 
   * @param dsenddate 发货日期
   */
  public void setDsenddate(UFDate dsenddate) {
    this.setAttributeValue(SaleOrderBVO.DSENDDATE, dsenddate);
  }

  /**
   * 设置购销类型
   * 
   * @param fbuysellflag 购销类型
   */
  public void setFbuysellflag(Integer fbuysellflag) {
    this.setAttributeValue(SaleOrderBVO.FBUYSELLFLAG, fbuysellflag);
  }

  /**
   * 设置赠品价格分摊方式
   * 
   * @param flargesstypeflag 赠品价格分摊方式
   * @see Largesstype
   */
  public void setFlargesstypeflag(Integer flargesstypeflag) {
    this.setAttributeValue(SaleOrderBVO.FLARGESSTYPEFLAG, flargesstypeflag);
  }

  /**
   * 设置退换货标记
   * 
   * @param fretexchange 退换货标记
   * @see Fretexchange
   */
  public void setFretexchange(Integer fretexchange) {
    this.setAttributeValue(SaleOrderBVO.FRETEXCHANGE, fretexchange);
  }

  /**
   * 设置行状态
   * 
   * @param frowstatus 行状态
   * @see BillStatus
   */
  public void setFrowstatus(Integer frowstatus) {
    this.setAttributeValue(SaleOrderBVO.FROWSTATUS, frowstatus);
  }

  /**
   * 设置扣税类别
   * 
   * @param ftaxtypeflag 扣税类别
   */
  public void setFtaxtypeflag(Integer ftaxtypeflag) {
    this.setAttributeValue(SaleOrderBVO.FTAXTYPEFLAG, ftaxtypeflag);
  }

  /**
   * 设置累计安排生产订单数量
   * 
   * @param narrangemonum 累计安排生产订单数量
   */
  public void setNarrangemonum(UFDouble narrangemonum) {
    this.setAttributeValue(SaleOrderBVO.NARRANGEMONUM, narrangemonum);
  }

  /**
   * 设置累计安排请购单数量
   * 
   * @param narrangepoappnum 累计安排请购单数量
   */
  public void setNarrangepoappnum(UFDouble narrangepoappnum) {
    this.setAttributeValue(SaleOrderBVO.NARRANGEPOAPPNUM, narrangepoappnum);
  }

  /**
   * 设置累计安排采购订单数量
   * 
   * @param narrangeponum 累计安排采购订单数量
   */
  public void setNarrangeponum(UFDouble narrangeponum) {
    this.setAttributeValue(SaleOrderBVO.NARRANGEPONUM, narrangeponum);
  }

  /**
   * 设置累计安排委外订单数量
   * 
   * @param narrangescornum 累计安排委外订单数量
   */
  public void setNarrangescornum(UFDouble narrangescornum) {
    this.setAttributeValue(SaleOrderBVO.NARRANGESCORNUM, narrangescornum);
  }

  /**
   * 设置累计安排调拨申请数量
   * 
   * @param narrangetoappnum 累计安排调拨申请数量
   */
  public void setNarrangetoappnum(UFDouble narrangetoappnum) {
    this.setAttributeValue(SaleOrderBVO.NARRANGETOAPPNUM, narrangetoappnum);
  }

  /**
   * 设置累计安排调拨订单数量
   * 
   * @param narrangetoornum 累计安排调拨订单数量
   */
  public void setNarrangetoornum(UFDouble narrangetoornum) {
    this.setAttributeValue(SaleOrderBVO.NARRANGETOORNUM, narrangetoornum);
  }

  /**
   * 设置询价原币无税净价
   * 
   * @param naskqtorignetprice 询价原币无税净价
   */
  public void setNaskqtorignetprice(UFDouble naskqtorignetprice) {
    this.setAttributeValue(SaleOrderBVO.NASKQTORIGNETPRICE, naskqtorignetprice);
  }

  /**
   * 设置询价原币无税单价
   * 
   * @param naskqtorigprice 询价原币无税单价
   */
  public void setNaskqtorigprice(UFDouble naskqtorigprice) {
    this.setAttributeValue(SaleOrderBVO.NASKQTORIGPRICE, naskqtorigprice);
  }

  /**
   * 设置询价原币含税单价
   * 
   * @param naskqtorigtaxprc 询价原币含税单价
   */
  public void setNaskqtorigtaxprc(UFDouble naskqtorigtaxprc) {
    this.setAttributeValue(SaleOrderBVO.NASKQTORIGTAXPRC, naskqtorigtaxprc);
  }

  /**
   * 设置询价原币含税净价
   * 
   * @param naskqtorigtxntprc 询价原币含税净价
   */
  public void setNaskqtorigtxntprc(UFDouble naskqtorigtxntprc) {
    this.setAttributeValue(SaleOrderBVO.NASKQTORIGTXNTPRC, naskqtorigtxntprc);
  }

  /**
   * 设置数量
   * 
   * @param nastnum 数量
   */
  public void setNastnum(UFDouble nastnum) {
    this.setAttributeValue(SaleOrderBVO.NASTNUM, nastnum);
  }

  /**
   * 设置nbforigsubmny
   * 
   * @param nbforigsubmny nbforigsubmny
   */
  public void setNbforigsubmny(UFDouble nbforigsubmny) {
    this.setAttributeValue(SaleOrderBVO.NBFORIGSUBMNY, nbforigsubmny);
  }

  /**
   * 设置计税金额
   * 
   * @param ncaltaxmny 计税金额
   */
  public void setNcaltaxmny(UFDouble ncaltaxmny) {
    this.setAttributeValue(SaleOrderBVO.NCALTAXMNY, ncaltaxmny);
  }

  /**
   * 设置本币折扣额
   * 
   * @param ndiscount 本币折扣额
   */
  public void setNdiscount(UFDouble ndiscount) {
    this.setAttributeValue(SaleOrderBVO.NDISCOUNT, ndiscount);
  }

  /**
   * 设置整单折扣
   * 
   * @param ndiscountrate 整单折扣
   */
  public void setNdiscountrate(UFDouble ndiscountrate) {
    this.setAttributeValue(SaleOrderBVO.NDISCOUNTRATE, ndiscountrate);
  }

  /**
   * 设置折本汇率
   * 
   * @param nexchangerate 折本汇率
   */
  public void setNexchangerate(UFDouble nexchangerate) {
    this.setAttributeValue(SaleOrderBVO.NEXCHANGERATE, nexchangerate);
  }

  /**
   * 设置全局本位币汇率
   * 
   * @param nglobalexchgrate 全局本位币汇率
   */
  public void setNglobalexchgrate(UFDouble nglobalexchgrate) {
    this.setAttributeValue(SaleOrderBVO.NGLOBALEXCHGRATE, nglobalexchgrate);
  }

  /**
   * 设置全局本币无税金额
   * 
   * @param nglobalmny 全局本币无税金额
   */
  public void setNglobalmny(UFDouble nglobalmny) {
    this.setAttributeValue(SaleOrderBVO.NGLOBALMNY, nglobalmny);
  }

  /**
   * 设置全局本币价税合计
   * 
   * @param nglobaltaxmny 全局本币价税合计
   */
  public void setNglobaltaxmny(UFDouble nglobaltaxmny) {
    this.setAttributeValue(SaleOrderBVO.NGLOBALTAXMNY, nglobaltaxmny);
  }

  /**
   * 设置集团本位币汇率
   * 
   * @param ngroupexchgrate 集团本位币汇率
   */
  public void setNgroupexchgrate(UFDouble ngroupexchgrate) {
    this.setAttributeValue(SaleOrderBVO.NGROUPEXCHGRATE, ngroupexchgrate);
  }

  /**
   * 设置集团本币无税金额
   * 
   * @param ngroupmny 集团本币无税金额
   */
  public void setNgroupmny(UFDouble ngroupmny) {
    this.setAttributeValue(SaleOrderBVO.NGROUPMNY, ngroupmny);
  }

  /**
   * 设置集团本币价税合计
   * 
   * @param ngrouptaxmny 集团本币价税合计
   */
  public void setNgrouptaxmny(UFDouble ngrouptaxmny) {
    this.setAttributeValue(SaleOrderBVO.NGROUPTAXMNY, ngrouptaxmny);
  }

  /**
   * 设置ninvoiceauditnum
   * 
   * @param ninvoiceauditnum ninvoiceauditnum
   */
  public void setNinvoiceauditnum(UFDouble ninvoiceauditnum) {
    this.setAttributeValue(SaleOrderBVO.NINVOICEAUDITNUM, ninvoiceauditnum);
  }

  /**
   * 设置ninvunfinisednum
   * 
   * @param ninvunfinisednum ninvunfinisednum
   */
  public void setNinvunfinisednum(UFDouble ninvunfinisednum) {
    this.setAttributeValue(SaleOrderBVO.NINVUNFINISEDNUM, ninvunfinisednum);
  }

  /**
   * 设置单品折扣率
   * 
   * @param nitemdiscountrate 单品折扣率
   */
  public void setNitemdiscountrate(UFDouble nitemdiscountrate) {
    this.setAttributeValue(SaleOrderBVO.NITEMDISCOUNTRATE, nitemdiscountrate);
  }

  /**
   * 设置赠品价格分摊前无税金额
   * 
   * @param nlargessmny 赠品价格分摊前无税金额
   */
  public void setNlargessmny(UFDouble nlargessmny) {
    this.setAttributeValue(SaleOrderBVO.NLARGESSMNY, nlargessmny);
  }

  /**
   * 设置赠品价格分摊前价税合计
   * 
   * @param nlargesstaxmny 赠品价格分摊前价税合计
   */
  public void setNlargesstaxmny(UFDouble nlargesstaxmny) {
    this.setAttributeValue(SaleOrderBVO.NLARGESSTAXMNY, nlargesstaxmny);
  }

  /**
   * 设置nlossnotauditnum
   * 
   * @param nlossnotauditnum nlossnotauditnum
   */
  public void setNlossnotauditnum(UFDouble nlossnotauditnum) {
    this.setAttributeValue(SaleOrderBVO.NLOSSNOTAUDITNUM, nlossnotauditnum);
  }

  /**
   * 设置本币无税金额
   * 
   * @param nmny 本币无税金额
   */
  public void setNmny(UFDouble nmny) {
    this.setAttributeValue(SaleOrderBVO.NMNY, nmny);
  }

  /**
   * 设置主本币无税净价
   * 
   * @param nnetprice 主本币无税净价
   */
  public void setNnetprice(UFDouble nnetprice) {
    this.setAttributeValue(SaleOrderBVO.NNETPRICE, nnetprice);
  }

  /**
   * 设置nnotarnum
   * 
   * @param nnotarnum nnotarnum
   */
  public void setNnotarnum(UFDouble nnotarnum) {
    this.setAttributeValue(SaleOrderBVO.NNOTARNUM, nnotarnum);
  }

  /**
   * 设置nnotcostnum
   * 
   * @param nnotcostnum nnotcostnum
   */
  public void setNnotcostnum(UFDouble nnotcostnum) {
    this.setAttributeValue(SaleOrderBVO.NNOTCOSTNUM, nnotcostnum);
  }

  /**
   * 设置主数量
   * 
   * @param nnum 主数量
   */
  public void setNnum(UFDouble nnum) {
    this.setAttributeValue(SaleOrderBVO.NNUM, nnum);
  }

  /**
   * 设置折扣额
   * 
   * @param norigdiscount 折扣额
   */
  public void setNorigdiscount(UFDouble norigdiscount) {
    this.setAttributeValue(SaleOrderBVO.NORIGDISCOUNT, norigdiscount);
  }

  /**
   * 设置无税金额
   * 
   * @param norigmny 无税金额
   */
  public void setNorigmny(UFDouble norigmny) {
    this.setAttributeValue(SaleOrderBVO.NORIGMNY, norigmny);
  }

  /**
   * 设置主无税净价
   * 
   * @param norignetprice 主无税净价
   */
  public void setNorignetprice(UFDouble norignetprice) {
    this.setAttributeValue(SaleOrderBVO.NORIGNETPRICE, norignetprice);
  }

  /**
   * 设置主无税单价
   * 
   * @param norigprice 主无税单价
   */
  public void setNorigprice(UFDouble norigprice) {
    this.setAttributeValue(SaleOrderBVO.NORIGPRICE, norigprice);
  }

  /**
   * 设置累计冲抵金额
   * 
   * @param norigsubmny 累计冲抵金额
   */
  public void setNorigsubmny(UFDouble norigsubmny) {
    this.setAttributeValue(SaleOrderBVO.NORIGSUBMNY, norigsubmny);
  }

  /**
   * 设置税额
   * 
   * @param norigtax 税额
   */
  // public void setNorigtax(UFDouble norigtax) {
  // this.setAttributeValue(SaleOrderBVO.NORIGTAX, norigtax);
  // }

  /**
   * 设置价税合计
   * 
   * @param norigtaxmny 价税合计
   */
  public void setNorigtaxmny(UFDouble norigtaxmny) {
    this.setAttributeValue(SaleOrderBVO.NORIGTAXMNY, norigtaxmny);
  }

  /**
   * 设置主含税净价
   * 
   * @param norigtaxnetprice 主含税净价
   */
  public void setNorigtaxnetprice(UFDouble norigtaxnetprice) {
    this.setAttributeValue(SaleOrderBVO.NORIGTAXNETPRICE, norigtaxnetprice);
  }

  /**
   * 设置主含税单价
   * 
   * @param norigtaxprice 主含税单价
   */
  public void setNorigtaxprice(UFDouble norigtaxprice) {
    this.setAttributeValue(SaleOrderBVO.NORIGTAXPRICE, norigtaxprice);
  }

  /**
   * 设置noutauditnum
   * 
   * @param noutauditnum noutauditnum
   */
  public void setNoutauditnum(UFDouble noutauditnum) {
    this.setAttributeValue(SaleOrderBVO.NOUTAUDITNUM, noutauditnum);
  }

  /**
   * 设置noutnotauditnum
   * 
   * @param noutnotauditnum noutnotauditnum
   */
  public void setNoutnotauditnum(UFDouble noutnotauditnum) {
    this.setAttributeValue(SaleOrderBVO.NOUTNOTAUDITNUM, noutnotauditnum);
  }

  /**
   * 设置noutunfinisednum
   * 
   * @param noutunfinisednum noutunfinisednum
   */
  public void setNoutunfinisednum(UFDouble noutunfinisednum) {
    this.setAttributeValue(SaleOrderBVO.NOUTUNFINISEDNUM, noutunfinisednum);
  }

  /**
   * 设置件数
   * 
   * @param npiece 件数
   */
  public void setNpiece(UFDouble npiece) {
    this.setAttributeValue(SaleOrderBVO.NPIECE, npiece);
  }

  /**
   * 设置主本币无税单价
   * 
   * @param nprice 主本币无税单价
   */
  public void setNprice(UFDouble nprice) {
    this.setAttributeValue(SaleOrderBVO.NPRICE, nprice);
  }

  /**
   * 设置本币无税净价
   * 
   * @param nqtnetprice 本币无税净价
   */
  public void setNqtnetprice(UFDouble nqtnetprice) {
    this.setAttributeValue(SaleOrderBVO.NQTNETPRICE, nqtnetprice);
  }

  /**
   * 设置无税净价
   * 
   * @param nqtorignetprice 无税净价
   */
  public void setNqtorignetprice(UFDouble nqtorignetprice) {
    this.setAttributeValue(SaleOrderBVO.NQTORIGNETPRICE, nqtorignetprice);
  }

  /**
   * 设置无税单价
   * 
   * @param nqtorigprice 无税单价
   */
  public void setNqtorigprice(UFDouble nqtorigprice) {
    this.setAttributeValue(SaleOrderBVO.NQTORIGPRICE, nqtorigprice);
  }

  /**
   * 设置含税净价
   * 
   * @param nqtorigtaxnetprc 含税净价
   */
  public void setNqtorigtaxnetprc(UFDouble nqtorigtaxnetprc) {
    this.setAttributeValue(SaleOrderBVO.NQTORIGTAXNETPRC, nqtorigtaxnetprc);
  }

  /**
   * 设置含税单价
   * 
   * @param nqtorigtaxprice 含税单价
   */
  public void setNqtorigtaxprice(UFDouble nqtorigtaxprice) {
    this.setAttributeValue(SaleOrderBVO.NQTORIGTAXPRICE, nqtorigtaxprice);
  }

  /**
   * 设置本币无税单价
   * 
   * @param nqtprice 本币无税单价
   */
  public void setNqtprice(UFDouble nqtprice) {
    this.setAttributeValue(SaleOrderBVO.NQTPRICE, nqtprice);
  }

  /**
   * 设置本币含税净价
   * 
   * @param nqttaxnetprice 本币含税净价
   */
  public void setNqttaxnetprice(UFDouble nqttaxnetprice) {
    this.setAttributeValue(SaleOrderBVO.NQTTAXNETPRICE, nqttaxnetprice);
  }

  /**
   * 设置本币含税单价
   * 
   * @param nqttaxprice 本币含税单价
   */
  public void setNqttaxprice(UFDouble nqttaxprice) {
    this.setAttributeValue(SaleOrderBVO.NQTTAXPRICE, nqttaxprice);
  }

  /**
   * 设置报价单位数量
   * 
   * @param nqtunitnum 报价单位数量
   */
  public void setNqtunitnum(UFDouble nqtunitnum) {
    this.setAttributeValue(SaleOrderBVO.NQTUNITNUM, nqtunitnum);
  }

  /**
   * 设置预留数量
   * 
   * @param nreqrsnum 预留数量
   */
  public void setNreqrsnum(UFDouble nreqrsnum) {
    this.setAttributeValue(SaleOrderBVO.NREQRSNUM, nreqrsnum);
  }

  /**
   * 设置nsendauditnum
   * 
   * @param nsendauditnum nsendauditnum
   */
  public void setNsendauditnum(UFDouble nsendauditnum) {
    this.setAttributeValue(SaleOrderBVO.NSENDAUDITNUM, nsendauditnum);
  }

  /**
   * 设置nsendunfinisednum
   * 
   * @param nsendunfinisednum nsendunfinisednum
   */
  public void setNsendunfinisednum(UFDouble nsendunfinisednum) {
    this.setAttributeValue(SaleOrderBVO.NSENDUNFINISEDNUM, nsendunfinisednum);
  }

  /**
   * 设置本币税额
   * 
   * @param ntax 本币税额
   */
  public void setNtax(UFDouble ntax) {
    this.setAttributeValue(SaleOrderBVO.NTAX, ntax);
  }

  /**
   * 设置本币价税合计
   * 
   * @param ntaxmny 本币价税合计
   */
  public void setNtaxmny(UFDouble ntaxmny) {
    this.setAttributeValue(SaleOrderBVO.NTAXMNY, ntaxmny);
  }

  /**
   * 设置主本币含税净价
   * 
   * @param ntaxnetprice 主本币含税净价
   */
  public void setNtaxnetprice(UFDouble ntaxnetprice) {
    this.setAttributeValue(SaleOrderBVO.NTAXNETPRICE, ntaxnetprice);
  }

  /**
   * 设置主本币含税单价
   * 
   * @param ntaxprice 主本币含税单价
   */
  public void setNtaxprice(UFDouble ntaxprice) {
    this.setAttributeValue(SaleOrderBVO.NTAXPRICE, ntaxprice);
  }

  /**
   * 设置税率
   * 
   * @param ntaxrate 税率
   */
  public void setNtaxrate(UFDouble ntaxrate) {
    this.setAttributeValue(SaleOrderBVO.NTAXRATE, ntaxrate);
  }

  /**
   * 设置累计确认应收金额
   * 
   * @param ntotalarmny 累计确认应收金额
   */
  public void setNtotalarmny(UFDouble ntotalarmny) {
    this.setAttributeValue(SaleOrderBVO.NTOTALARMNY, ntotalarmny);
  }

  /**
   * 设置累计确认应收数量
   * 
   * @param ntotalarnum 累计确认应收数量
   */
  public void setNtotalarnum(UFDouble ntotalarnum) {
    this.setAttributeValue(SaleOrderBVO.NTOTALARNUM, ntotalarnum);
  }

  /**
   * 设置累计成本结算数量
   * 
   * @param ntotalcostnum 累计成本结算数量
   */
  public void setNtotalcostnum(UFDouble ntotalcostnum) {
    this.setAttributeValue(SaleOrderBVO.NTOTALCOSTNUM, ntotalcostnum);
  }

  /**
   * 设置累计暂估应收金额
   * 
   * @param ntotalestarmny 累计暂估应收金额
   */
  public void setNtotalestarmny(UFDouble ntotalestarmny) {
    this.setAttributeValue(SaleOrderBVO.NTOTALESTARMNY, ntotalestarmny);
  }

  /**
   * 设置累计暂估应收数量
   * 
   * @param ntotalestarnum 累计暂估应收数量
   */
  public void setNtotalestarnum(UFDouble ntotalestarnum) {
    this.setAttributeValue(SaleOrderBVO.NTOTALESTARNUM, ntotalestarnum);
  }

  /**
   * 设置累计开票数量
   * 
   * @param ntotalinvoicenum 累计开票数量
   */
  public void setNtotalinvoicenum(UFDouble ntotalinvoicenum) {
    this.setAttributeValue(SaleOrderBVO.NTOTALINVOICENUM, ntotalinvoicenum);
  }

  /**
   * 设置累计应发未出库数量
   * 
   * @param ntotalnotoutnum 累计应发未出库数量
   */
  public void setNtotalnotoutnum(UFDouble ntotalnotoutnum) {
    this.setAttributeValue(SaleOrderBVO.NTOTALNOTOUTNUM, ntotalnotoutnum);
  }

  /**
   * 设置累计出库数量
   * 
   * @param ntotaloutnum 累计出库数量
   */
  public void setNtotaloutnum(UFDouble ntotaloutnum) {
    this.setAttributeValue(SaleOrderBVO.NTOTALOUTNUM, ntotaloutnum);
  }

  /**
   * 设置累计财务核销金额
   * 
   * @param ntotalpaymny 累计财务核销金额
   */
  public void setNtotalpaymny(UFDouble ntotalpaymny) {
    this.setAttributeValue(SaleOrderBVO.NTOTALPAYMNY, ntotalpaymny);
  }

  /**
   * 设置累计生成计划订单数量
   * 
   * @param ntotalplonum 累计生成计划订单数量
   */
  public void setNtotalplonum(UFDouble ntotalplonum) {
    this.setAttributeValue(SaleOrderBVO.NTOTALPLONUM, ntotalplonum);
  }

  /**
   * 设置累计退货数量
   * 
   * @param ntotalreturnnum 累计退货数量
   */
  public void setNtotalreturnnum(UFDouble ntotalreturnnum) {
    this.setAttributeValue(SaleOrderBVO.NTOTALRETURNNUM, ntotalreturnnum);
  }

  /**
   * 设置累计出库对冲数量
   * 
   * @param ntotalrushnum 累计出库对冲数量
   */
  public void setNtotalrushnum(UFDouble ntotalrushnum) {
    this.setAttributeValue(SaleOrderBVO.NTOTALRUSHNUM, ntotalrushnum);
  }

  /**
   * 设置累计发货数量
   * 
   * @param ntotalsendnum 累计发货数量
   */
  public void setNtotalsendnum(UFDouble ntotalsendnum) {
    this.setAttributeValue(SaleOrderBVO.NTOTALSENDNUM, ntotalsendnum);
  }

  /**
   * 设置累计签收数量
   * 
   * @param ntotalsignnum 累计签收数量
   */
  public void setNtotalsignnum(UFDouble ntotalsignnum) {
    this.setAttributeValue(SaleOrderBVO.NTOTALSIGNNUM, ntotalsignnum);
  }

  /**
   * 设置累计发出商品数量
   * 
   * @param ntotaltradenum 累计发出商品数量
   */
  public void setNtotaltradenum(UFDouble ntotaltradenum) {
    this.setAttributeValue(SaleOrderBVO.NTOTALTRADENUM, ntotaltradenum);
  }

  /**
   * 设置累计途损数量
   * 
   * @param ntranslossnum 累计途损数量
   */
  public void setNtranslossnum(UFDouble ntranslossnum) {
    this.setAttributeValue(SaleOrderBVO.NTRANSLOSSNUM, ntranslossnum);
  }

  /**
   * 设置体积
   * 
   * @param nvolume 体积
   */
  public void setNvolume(UFDouble nvolume) {
    this.setAttributeValue(SaleOrderBVO.NVOLUME, nvolume);
  }

  /**
   * 设置重量
   * 
   * @param nweight 重量
   */
  public void setNweight(UFDouble nweight) {
    this.setAttributeValue(SaleOrderBVO.NWEIGHT, nweight);
  }

  /**
   * 设置批次档案
   * 
   * @param pk_batchcode 批次档案
   */
  public void setPk_batchcode(String pk_batchcode) {
    this.setAttributeValue(SaleOrderBVO.PK_BATCHCODE, pk_batchcode);
  }

  /**
   * 设置集团
   * 
   * @param pk_group 集团
   */
  public void setPk_group(String pk_group) {
    this.setAttributeValue(SaleOrderBVO.PK_GROUP, pk_group);
  }

  /**
   * 设置销售组织
   * 
   * @param pk_org 销售组织
   */
  public void setPk_org(String pk_org) {
    this.setAttributeValue(SaleOrderBVO.PK_ORG, pk_org);
  }

  /**
   * 设置来源时间戳
   * 
   * @param srcbts 来源时间戳
   * 
   */
  public void setSrcbts(UFDateTime srcbts) {
    this.setAttributeValue(SaleOrderBVO.SRCBTS, srcbts);
  }

  /**
   * 设置来源组织
   * 
   * @param srcorgid
   */
  public void setSrcorgid(String srcorgid) {
    this.setAttributeValue(SaleOrderBVO.SRCORGID, srcorgid);
  }

  /**
   * 设置来源时间戳
   * 
   * @param srcts 来源时间戳
   * 
   */
  public void setSrcts(UFDateTime srcts) {
    this.setAttributeValue(SaleOrderBVO.SRCTS, srcts);
  }

  /**
   * 设置最后货源安排时间
   * 
   * @param tlastarrangetime 最后货源安排时间
   */
  public void setTlastarrangetime(UFDateTime tlastarrangetime) {
    this.setAttributeValue(SaleOrderBVO.TLASTARRANGETIME, tlastarrangetime);
  }

  /**
   * 设置时间戳
   * 
   * @param ts 时间戳
   */
  public void setTs(UFDateTime ts) {
    this.setAttributeValue(SaleOrderBVO.TS, ts);
  }

  /**
   * 设置批次
   * 
   * @param vbatchcode 批次
   */
  public void setVbatchcode(String vbatchcode) {
    this.setAttributeValue(SaleOrderBVO.VBATCHCODE, vbatchcode);
  }

  /**
   * 设置自定义项1
   * 
   * @param vbdef1 自定义项1
   */
  public void setVbdef1(String vbdef1) {
    this.setAttributeValue(SaleOrderBVO.VBDEF1, vbdef1);
  }

  /**
   * 设置自定义项10
   * 
   * @param vbdef10 自定义项10
   */
  public void setVbdef10(String vbdef10) {
    this.setAttributeValue(SaleOrderBVO.VBDEF10, vbdef10);
  }

  /**
   * 设置自定义项11
   * 
   * @param vbdef11 自定义项11
   */
  public void setVbdef11(String vbdef11) {
    this.setAttributeValue(SaleOrderBVO.VBDEF11, vbdef11);
  }

  /**
   * 设置自定义项12
   * 
   * @param vbdef12 自定义项12
   */
  public void setVbdef12(String vbdef12) {
    this.setAttributeValue(SaleOrderBVO.VBDEF12, vbdef12);
  }

  /**
   * 设置自定义项13
   * 
   * @param vbdef13 自定义项13
   */
  public void setVbdef13(String vbdef13) {
    this.setAttributeValue(SaleOrderBVO.VBDEF13, vbdef13);
  }

  /**
   * 设置自定义项14
   * 
   * @param vbdef14 自定义项14
   */
  public void setVbdef14(String vbdef14) {
    this.setAttributeValue(SaleOrderBVO.VBDEF14, vbdef14);
  }

  /**
   * 设置自定义项15
   * 
   * @param vbdef15 自定义项15
   */
  public void setVbdef15(String vbdef15) {
    this.setAttributeValue(SaleOrderBVO.VBDEF15, vbdef15);
  }

  /**
   * 设置自定义项16
   * 
   * @param vbdef16 自定义项16
   */
  public void setVbdef16(String vbdef16) {
    this.setAttributeValue(SaleOrderBVO.VBDEF16, vbdef16);
  }

  /**
   * 设置自定义项17
   * 
   * @param vbdef17 自定义项17
   */
  public void setVbdef17(String vbdef17) {
    this.setAttributeValue(SaleOrderBVO.VBDEF17, vbdef17);
  }

  /**
   * 设置自定义项18
   * 
   * @param vbdef18 自定义项18
   */
  public void setVbdef18(String vbdef18) {
    this.setAttributeValue(SaleOrderBVO.VBDEF18, vbdef18);
  }

  /**
   * 设置自定义项19
   * 
   * @param vbdef19 自定义项19
   */
  public void setVbdef19(String vbdef19) {
    this.setAttributeValue(SaleOrderBVO.VBDEF19, vbdef19);
  }

  /**
   * 设置自定义项2
   * 
   * @param vbdef2 自定义项2
   */
  public void setVbdef2(String vbdef2) {
    this.setAttributeValue(SaleOrderBVO.VBDEF2, vbdef2);
  }

  /**
   * 设置自定义项20
   * 
   * @param vbdef20 自定义项20
   */
  public void setVbdef20(String vbdef20) {
    this.setAttributeValue(SaleOrderBVO.VBDEF20, vbdef20);
  }

  /**
   * 设置自定义项3
   * 
   * @param vbdef3 自定义项3
   */
  public void setVbdef3(String vbdef3) {
    this.setAttributeValue(SaleOrderBVO.VBDEF3, vbdef3);
  }

  /**
   * 设置自定义项4
   * 
   * @param vbdef4 自定义项4
   */
  public void setVbdef4(String vbdef4) {
    this.setAttributeValue(SaleOrderBVO.VBDEF4, vbdef4);
  }

  /**
   * 设置自定义项5
   * 
   * @param vbdef5 自定义项5
   */
  public void setVbdef5(String vbdef5) {
    this.setAttributeValue(SaleOrderBVO.VBDEF5, vbdef5);
  }

  /**
   * 设置自定义项6
   * 
   * @param vbdef6 自定义项6
   */
  public void setVbdef6(String vbdef6) {
    this.setAttributeValue(SaleOrderBVO.VBDEF6, vbdef6);
  }

  /**
   * 设置自定义项7
   * 
   * @param vbdef7 自定义项7
   */
  public void setVbdef7(String vbdef7) {
    this.setAttributeValue(SaleOrderBVO.VBDEF7, vbdef7);
  }

  /**
   * 设置自定义项8
   * 
   * @param vbdef8 自定义项8
   */
  public void setVbdef8(String vbdef8) {
    this.setAttributeValue(SaleOrderBVO.VBDEF8, vbdef8);
  }

  /**
   * 设置自定义项9
   * 
   * @param vbdef9 自定义项9
   */
  public void setVbdef9(String vbdef9) {
    this.setAttributeValue(SaleOrderBVO.VBDEF9, vbdef9);
  }

  /**
   * 设置修订理由
   * 
   * @param vbrevisereason 修订理由
   */
  public void setVbrevisereason(String vbrevisereason) {
    this.setAttributeValue(SaleOrderBVO.VBREVISEREASON, vbrevisereason);
  }

  /**
   * 设置换算率
   * 
   * @param vchangerate 换算率
   */
  public void setVchangerate(String vchangerate) {
    this.setAttributeValue(SaleOrderBVO.VCHANGERATE, vchangerate);
  }

  /**
   * 设置关闭原因
   * 
   * @param vclosereason 关闭原因
   */
  public void setVclosereason(String vclosereason) {
    this.setAttributeValue(SaleOrderBVO.VCLOSEREASON, vclosereason);
  }

  /**
   * 设置销售合同号
   * 
   * @param vctcode 销售合同号
   */
  public void setVctcode(String vctcode) {
    this.setAttributeValue(SaleOrderBVO.VCTCODE, vctcode);
  }

  /**
   * 设置vcttype
   * 
   * @param vcttype vcttype
   */
  public void setVcttype(String vcttype) {
    this.setAttributeValue(SaleOrderBVO.VCTTYPE, vcttype);
  }

  /**
   * 设置源头单据号
   * 
   * @param vfirstcode 源头单据号
   */
  public void setVfirstcode(String vfirstcode) {
    this.setAttributeValue(SaleOrderBVO.VFIRSTCODE, vfirstcode);
  }

  /**
   * 设置源头单据行号
   * 
   * @param vfirstrowno 源头单据行号
   */
  public void setVfirstrowno(String vfirstrowno) {
    this.setAttributeValue(SaleOrderBVO.VFIRSTROWNO, vfirstrowno);
  }

  /**
   * 设置源头交易类型
   * 
   * @param vfirsttrantype 源头交易类型
   */
  public void setVfirsttrantype(String vfirsttrantype) {
    this.setAttributeValue(SaleOrderBVO.VFIRSTTRANTYPE, vfirsttrantype);
  }

  /**
   * 设置源头单据类型
   * 
   * @param vfirsttype 源头单据类型
   */
  public void setVfirsttype(String vfirsttype) {
    this.setAttributeValue(SaleOrderBVO.VFIRSTTYPE, vfirsttype);
  }

  /**
   * 设置自由辅助属性1
   * 
   * @param vfree1 自由辅助属性1
   */
  public void setVfree1(String vfree1) {
    this.setAttributeValue(SaleOrderBVO.VFREE1, vfree1);
  }

  /**
   * 设置自由辅助属性10
   * 
   * @param vfree10 自由辅助属性10
   */
  public void setVfree10(String vfree10) {
    this.setAttributeValue(SaleOrderBVO.VFREE10, vfree10);
  }

  /**
   * 设置自由辅助属性2
   * 
   * @param vfree2 自由辅助属性2
   */
  public void setVfree2(String vfree2) {
    this.setAttributeValue(SaleOrderBVO.VFREE2, vfree2);
  }

  /**
   * 设置自由辅助属性3
   * 
   * @param vfree3 自由辅助属性3
   */
  public void setVfree3(String vfree3) {
    this.setAttributeValue(SaleOrderBVO.VFREE3, vfree3);
  }

  /**
   * 设置自由辅助属性4
   * 
   * @param vfree4 自由辅助属性4
   */
  public void setVfree4(String vfree4) {
    this.setAttributeValue(SaleOrderBVO.VFREE4, vfree4);
  }

  /**
   * 设置自由辅助属性5
   * 
   * @param vfree5 自由辅助属性5
   */
  public void setVfree5(String vfree5) {
    this.setAttributeValue(SaleOrderBVO.VFREE5, vfree5);
  }

  /**
   * 设置自由辅助属性6
   * 
   * @param vfree6 自由辅助属性6
   */
  public void setVfree6(String vfree6) {
    this.setAttributeValue(SaleOrderBVO.VFREE6, vfree6);
  }

  /**
   * 设置自由辅助属性7
   * 
   * @param vfree7 自由辅助属性7
   */
  public void setVfree7(String vfree7) {
    this.setAttributeValue(SaleOrderBVO.VFREE7, vfree7);
  }

  /**
   * 设置自由辅助属性8
   * 
   * @param vfree8 自由辅助属性8
   */
  public void setVfree8(String vfree8) {
    this.setAttributeValue(SaleOrderBVO.VFREE8, vfree8);
  }

  /**
   * 设置自由辅助属性9
   * 
   * @param vfree9 自由辅助属性9
   */
  public void setVfree9(String vfree9) {
    this.setAttributeValue(SaleOrderBVO.VFREE9, vfree9);
  }

  /**
   * 设置报价换算率
   * 
   * @param vqtunitrate 报价换算率
   */
  public void setVqtunitrate(String vqtunitrate) {
    this.setAttributeValue(SaleOrderBVO.VQTUNITRATE, vqtunitrate);
  }

  /**
   * 设置退货责任处理方式
   * 
   * @param vreturnmode 退货责任
   */
  public void setVreturnmode(String vreturnmode) {
    this.setAttributeValue(SaleOrderBVO.VRETURNMODE, vreturnmode);
  }

  /**
   * 设置行备注
   * 
   * @param vrownote 行备注
   */
  public void setVrownote(String vrownote) {
    this.setAttributeValue(SaleOrderBVO.VROWNOTE, vrownote);
  }

  /**
   * 设置来源单据号
   * 
   * @param vsrccode 来源单据号
   */
  public void setVsrccode(String vsrccode) {
    this.setAttributeValue(SaleOrderBVO.VSRCCODE, vsrccode);
  }

  /**
   * 设置来源单据行号
   * 
   * @param vsrcrowno 来源单据行号
   */
  public void setVsrcrowno(String vsrcrowno) {
    this.setAttributeValue(SaleOrderBVO.VSRCROWNO, vsrcrowno);
  }

  /**
   * 设置来源交易类型
   * 
   * @param vsrctrantype 来源交易类型
   */
  public void setVsrctrantype(String vsrctrantype) {
    this.setAttributeValue(SaleOrderBVO.VSRCTRANTYPE, vsrctrantype);
  }

  /**
   * 设置来源单据类型
   * 
   * @param vsrctype 来源单据类型
   */
  public void setVsrctype(String vsrctype) {
    this.setAttributeValue(SaleOrderBVO.VSRCTYPE, vsrctype);
  }

  /**
   * 设置买赠促销类型
   * 
   * @param cbuypromottypeid 买赠促销类型
   * 
   */
  public void setCbuypromottypeid(String cbuypromottypeid) {
    this.setAttributeValue(SaleOrderBVO.CBUYPROMOTTYPEID, cbuypromottypeid);
  }

  /**
   * 设置询价促销类型
   * 
   * @param cprcpromottypeid 询价促销类型
   * 
   */
  public void setCprcpromottypeid(String cprcpromottypeid) {
    this.setAttributeValue(SaleOrderBVO.CPRCPROMOTTYPEID, cprcpromottypeid);
  }

  /**
   * 设置客户单据号
   * 
   * @param vcustombillcode 客户单据号
   * 
   */
  public void setVcustombillcode(String vcustombillcode) {
    this.setAttributeValue(SaleOrderBVO.VCUSTOMBILLCODE, vcustombillcode);
  }

  /**
   * 买赠活动
   * 
   * @param cbuylargessactid 买赠活动
   * 
   */
  public void setCbuylargessactid(String cbuylargessactid) {
    this.setAttributeValue(SaleOrderBVO.CBUYLARGESSACTID, cbuylargessactid);
  }

  /**
   * 价格促销活动
   * 
   * @param cpricepromtactid 价格促销活动
   * 
   */
  public void setCpricepromtactid(String cpricepromtactid) {
    this.setAttributeValue(SaleOrderBVO.CPRICEPROMTACTID, cpricepromtactid);
  }

  /**
   * 买赠设置
   * 
   * @param cbuylargessid 买赠设置
   * 
   */
  public void setCbuylargessid(String cbuylargessid) {
    this.setAttributeValue(SaleOrderBVO.CBUYLARGESSID, cbuylargessid);
  }

  /**
   * 获取累计安排进口合同主数量
   * 
   * @return 累计安排进口合同主数量
   */
  public UFDouble getNarrangeitcnum() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NARRANGEITCNUM);
  }

  /**
   * 设置累计安排进口合同主数量
   * 
   * @param narrangeitcnum 累计安排进口合同主数量
   */
  public void setNarrangeitcnum(UFDouble narrangeitcnum) {
    this.setAttributeValue(SaleOrderBVO.NARRANGEITCNUM, narrangeitcnum);
  }

  /**
   * 设置发货利润中心最新版本
   * 
   * @param csprofitcenterid 发货利润中心最新版本
   */
  public void setCsprofitcenterid(String csprofitcenterid) {
    this.setAttributeValue(SaleOrderBVO.CSPROFITCENTERID, csprofitcenterid);
  }

  /**
   * 设置发货利润中心
   * 
   * @param csprofitcentervid 发货利润中心
   */
  public void setCsprofitcentervid(String csprofitcentervid) {
    this.setAttributeValue(SaleOrderBVO.CSPROFITCENTERVID, csprofitcentervid);
  }
  
  /**
   * 设置促销价格表行ID
   * 
   * @param cpromotpriceid 促销价格表行ID
   */
  public void setCPromotpriceid(String cpromotpriceid) {
    this.setAttributeValue(SaleOrderBVO.CPROMOTPRICEID, cpromotpriceid);
  }
  
  /**
   * 获取特征码选配暂存VO
   * 
   * @return 特征码选配暂存VO
   */
  public AggFFileVO getAggffilevo() {
	return aggffilevo;
  }
  /**
   * 设置特征码选配暂存VO
   * 
   * @param aggffilevo 特征码选配暂存VO
   */
  public void setAggffilevo(AggFFileVO aggffilevo) {
	this.aggffilevo = aggffilevo;
  }

/**
   * 获取特征码
   * 
   * @return 特征码
   */
  public String  getCmffileid() {
    return (String) this.getAttributeValue(SaleOrderBVO.CMFFILEID);
  }

  /**
   * 设置特征码
   * 
   * @param 特征码
   */
  public void setCmffileid(String cmffileid) {
    this.setAttributeValue(SaleOrderBVO.CMFFILEID, cmffileid);
  }
  
  
  /**
   * 获取特征价
   * 
   * @return 特征价
   */
  public UFDouble  getNmffileprice() {
    return (UFDouble) this.getAttributeValue(SaleOrderBVO.NMFFILEPRICE);
  }

  /**
   * 设置特征价
   * 
   * @param 特征价
   */
  public void setNmffileprice(UFDouble nmffileprice) {
    this.setAttributeValue(SaleOrderBVO.NMFFILEPRICE, nmffileprice);
  }

  
  /**
   * add by wangzym  2017-01-18  扩展子表字段的 getter setter方法
   * */
public static String getBidding_no() {
	return bidding_no;
}

public static void setBidding_no(String bidding_no) {
	SaleOrderBVO.bidding_no = bidding_no;
}

public static String getProject_name() {
	return project_name;
}

public static void setProject_name(String project_name) {
	SaleOrderBVO.project_name = project_name;
}

public static String getProject_content() {
	return Project_content;
}

public static void setProject_content(String project_content) {
	Project_content = project_content;
}

public static String getSupplier_requirements() {
	return supplier_requirements;
}

public static void setSupplier_requirements(String supplier_requirements) {
	SaleOrderBVO.supplier_requirements = supplier_requirements;
}

public static UFDouble getSumplannum() {
	return sumplannum;
}

public static void setSumplannum(UFDouble sumplannum) {
	SaleOrderBVO.sumplannum = sumplannum;
}

public static UFDouble getSumnum() {
	return sumnum;
}

public static void setSumnum(UFDouble sumnum) {
	SaleOrderBVO.sumnum = sumnum;
}

public static String getTypeplan() {
	return typeplan;
}

public static void setTypeplan(String typeplan) {
	SaleOrderBVO.typeplan = typeplan;
}

public static String getTypebuy() {
	return typebuy;
}

public static void setTypebuy(String typebuy) {
	SaleOrderBVO.typebuy = typebuy;
}

public static UFDouble getRatereply() {
	return ratereply;
}

public static void setRatereply(UFDouble ratereply) {
	SaleOrderBVO.ratereply = ratereply;
}

public static String getBid_evaluation() {
	return bid_evaluation;
}

public static void setBid_evaluation(String bid_evaluation) {
	SaleOrderBVO.bid_evaluation = bid_evaluation;
}

public static String getCombination_standard() {
	return combination_standard;
}

public static void setCombination_standard(String combination_standard) {
	SaleOrderBVO.combination_standard = combination_standard;
}

public static String getProcurementplan() {
	return procurementplan;
}

public static void setProcurementplan(String procurementplan) {
	SaleOrderBVO.procurementplan = procurementplan;
}

public static String getNum_bj() {
	return num_bj;
}

public static void setNum_bj(String num_bj) {
	SaleOrderBVO.num_bj = num_bj;
}

public static String getSeq_bj() {
	return seq_bj;
}

public static void setSeq_bj(String seq_bj) {
	SaleOrderBVO.seq_bj = seq_bj;
}

public static String getOffer_type() {
	return offer_type;
}

public static void setOffer_type(String offer_type) {
	SaleOrderBVO.offer_type = offer_type;
}

public static String getQualification_way() {
	return qualification_way;
}

public static void setQualification_way(String qualification_way) {
	SaleOrderBVO.qualification_way = qualification_way;
}

public static String getPayment() {
	return payment;
}

public static void setPayment(String payment) {
	SaleOrderBVO.payment = payment;
}

public static String getBusiness_types() {
	return business_types;
}

public static void setBusiness_types(String business_types) {
	SaleOrderBVO.business_types = business_types;
}

public static String getProcurement() {
	return procurement;
}

public static void setProcurement(String procurement) {
	SaleOrderBVO.procurement = procurement;
}

public static String getEstimate() {
	return estimate;
}

public static void setEstimate(String estimate) {
	SaleOrderBVO.estimate = estimate;
}

public static String getDelivery_term() {
	return delivery_term;
}

public static void setDelivery_term(String delivery_term) {
	SaleOrderBVO.delivery_term = delivery_term;
}

public static String getRequirements() {
	return requirements;
}

public static void setRequirements(String requirements) {
	SaleOrderBVO.requirements = requirements;
}

public static String getSupplier_code() {
	return supplier_code;
}

public static void setSupplier_code(String supplier_code) {
	SaleOrderBVO.supplier_code = supplier_code;
}

public static String getSupplier() {
	return supplier;
}

public static void setSupplier(String supplier) {
	SaleOrderBVO.supplier = supplier;
}

public static String getNo_delegate() {
	return no_delegate;
}

public static void setNo_delegate(String no_delegate) {
	SaleOrderBVO.no_delegate = no_delegate;
}

public static String getSeq_delegate() {
	return seq_delegate;
}

public static void setSeq_delegate(String seq_delegate) {
	SaleOrderBVO.seq_delegate = seq_delegate;
}

public static String getCcategoryid() {
	return ccategoryid;
}

public static void setCcategoryid(String ccategoryid) {
	SaleOrderBVO.ccategoryid = ccategoryid;
}

public static String getProjectexecutor() {
	return projectexecutor;
}

public static void setProjectexecutor(String projectexecutor) {
	SaleOrderBVO.projectexecutor = projectexecutor;
}

public static String getNo_pasdoc() {
	return no_pasdoc;
}

public static void setNo_pasdoc(String no_pasdoc) {
	SaleOrderBVO.no_pasdoc = no_pasdoc;
}

public static String getCustomer_no() {
	return customer_no;
}

public static void setCustomer_no(String customer_no) {
	SaleOrderBVO.customer_no = customer_no;
}

public static String getCustomer_name() {
	return customer_name;
}

public static void setCustomer_name(String customer_name) {
	SaleOrderBVO.customer_name = customer_name;
}

public static Integer getPlan_num() {
	return plan_num;
}

public static void setPlan_num(Integer plan_num) {
	SaleOrderBVO.plan_num = plan_num;
}

public static UFDate getRequest_date() {
	return request_date;
}

public static void setRequest_date(UFDate request_date) {
	SaleOrderBVO.request_date = request_date;
}

public static String getHost_name() {
	return host_name;
}

public static void setHost_name(String host_name) {
	SaleOrderBVO.host_name = host_name;
}

public static String getMaterial() {
	return material;
}

public static void setMaterial(String material) {
	SaleOrderBVO.material = material;
}

public static String getRated_life() {
	return rated_life;
}

public static void setRated_life(String rated_life) {
	SaleOrderBVO.rated_life = rated_life;
}

public static String getManufacturer() {
	return manufacturer;
}

public static void setManufacturer(String manufacturer) {
	SaleOrderBVO.manufacturer = manufacturer;
}

public static UFDouble getPlan_pricea() {
	return plan_pricea;
}

public static void setPlan_pricea(UFDouble plan_pricea) {
	SaleOrderBVO.plan_pricea = plan_pricea;
}

public static UFDouble getPlan_priceb() {
	return plan_priceb;
}

public static void setPlan_priceb(UFDouble plan_priceb) {
	SaleOrderBVO.plan_priceb = plan_priceb;
}

public static UFDouble getPlansum_pricea() {
	return plansum_pricea;
}

public static void setPlansum_pricea(UFDouble plansum_pricea) {
	SaleOrderBVO.plansum_pricea = plansum_pricea;
}

public static UFDouble getPlansum_priceb() {
	return Plansum_priceb;
}

public static void setPlansum_priceb(UFDouble plansum_priceb) {
	Plansum_priceb = plansum_priceb;
}

public static String getFactory_plan() {
	return factory_plan;
}

public static void setFactory_plan(String factory_plan) {
	SaleOrderBVO.factory_plan = factory_plan;
}

public static String getFactory_code() {
	return factory_code;
}

public static void setFactory_code(String factory_code) {
	SaleOrderBVO.factory_code = factory_code;
}

public static String getFactory_name() {
	return factory_name;
}

public static void setFactory_name(String factory_name) {
	SaleOrderBVO.factory_name = factory_name;
}

public static String getPlan() {
	return plan;
}

public static void setPlan(String plan) {
	SaleOrderBVO.plan = plan;
}

public static String getApplication_no() {
	return application_no;
}

public static void setApplication_no(String application_no) {
	SaleOrderBVO.application_no = application_no;
}

public static String getApplication_line() {
	return application_line;
}

public static void setApplication_line(String application_line) {
	SaleOrderBVO.application_line = application_line;
}

public static String getNumber_code() {
	return number_code;
}

public static void setNumber_code(String number_code) {
	SaleOrderBVO.number_code = number_code;
}

public static String getTally() {
	return tally;
}

public static void setTally(String tally) {
	SaleOrderBVO.tally = tally;
}

public static UFDate getPlan_time() {
	return plan_time;
}

public static void setPlan_time(UFDate plan_time) {
	SaleOrderBVO.plan_time = plan_time;
}

public static UFDouble getFreight() {
	return freight;
}

public static void setFreight(UFDouble freight) {
	SaleOrderBVO.freight = freight;
}

public static UFDouble getAdded_tax() {
	return added_tax;
}

public static void setAdded_tax(UFDouble added_tax) {
	SaleOrderBVO.added_tax = added_tax;
}

public static UFDouble getExchange_rate() {
	return exchange_rate;
}

public static void setExchange_rate(UFDouble exchange_rate) {
	SaleOrderBVO.exchange_rate = exchange_rate;
}

public static String getCurrency() {
	return currency;
}

public static void setCurrency(String currency) {
	SaleOrderBVO.currency = currency;
}

public static String getUnit_leaders() {
	return unit_leaders;
}

public static void setUnit_leaders(String unit_leaders) {
	SaleOrderBVO.unit_leaders = unit_leaders;
}

public static String getUnit_sales() {
	return unit_sales;
}

public static void setUnit_sales(String unit_sales) {
	SaleOrderBVO.unit_sales = unit_sales;
}

public static String getUnit_charge() {
	return unit_charge;
}

public static void setUnit_charge(String unit_charge) {
	SaleOrderBVO.unit_charge = unit_charge;
}

public static String getEpein() {
	return epein;
}

public static void setEpein(String epein) {
	SaleOrderBVO.epein = epein;
}

public static String getSlysfs() {
	return slysfs;
}

public static void setSlysfs(String slysfs) {
	SaleOrderBVO.slysfs = slysfs;
}

public static String getXlysfs() {
	return xlysfs;
}

public static void setXlysfs(String xlysfs) {
	SaleOrderBVO.xlysfs = xlysfs;
}

public static String getYsfs() {
	return ysfs;
}

public static void setYsfs(String ysfs) {
	SaleOrderBVO.ysfs = ysfs;
}

public static UFDate getHtqdsj() {
	return htqdsj;
}

public static void setHtqdsj(UFDate htqdsj) {
	SaleOrderBVO.htqdsj = htqdsj;
}


/**
 * 2017-03-01新增 
 * @return bjwlmc
 */
public static String getBjwlmc() {
	return bjwlmc;
}

/**
 * @param bjwlmc 要设置的 bjwlmc
 */
public static void setBjwlmc(String bjwlmc) {
	SaleOrderBVO.bjwlmc = bjwlmc;
}

/**
 * @return materialnamex
 */
public static String getMaterialnamex() {
	return materialnamex;
}

/**
 * @param materialnamex 要设置的 materialnamex
 */
public static void setMaterialnamex(String materialnamex) {
	SaleOrderBVO.materialnamex = materialnamex;
}

/**
 * @return chand
 */
public static String getChand() {
	return chand;
}

/**
 * @param chand 要设置的 chand
 */
public static void setChand(String chand) {
	SaleOrderBVO.chand = chand;
}

/**
 * @return cgjg
 */
public static UFDouble getCgjg() {
	return cgjg;
}

/**
 * @param cgjg 要设置的 cgjg
 */
public static void setCgjg(UFDouble cgjg) {
	SaleOrderBVO.cgjg = cgjg;
}

/**
 * @return csjhq
 */
public static Integer getCsjhq() {
	return csjhq;
}

/**
 * @param csjhq 要设置的 csjhq
 */
public static void setCsjhq(Integer csjhq) {
	SaleOrderBVO.csjhq = csjhq;
}

/**
 * @return jiaohuodate
 */
public static UFDate getJiaohuodate() {
	return jiaohuodate;
}

/**
 * @param jiaohuodate 要设置的 jiaohuodate
 */
public static void setJiaohuodate(UFDate jiaohuodate) {
	SaleOrderBVO.jiaohuodate = jiaohuodate;
}
}
