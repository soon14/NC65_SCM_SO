package nc.vo.so.m32.entity;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>销售发票子表VO
 * <li>...
 * </ul>
 * <p>
 * <b>变更历史（可选）：</b>
 * <p>
 * XXX版本增加XXX的支持。
 * <p>
 * <p>
 * 
 * @version 本版本号 V60
 * @since 上一版本号
 * @author fengjb
 * @time 2009-7-3 上午10:33:05
 */
public class SaleInvoiceBVO extends SuperVO {
  
  
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
    this.setAttributeValue(SaleInvoiceBVO.CCUSTMATERIALID, ccustmaterialid);
  }

  /**
   * 获取客户物料编码
   * 
   * @return 客户物料编码
   */
  public String getCcustmaterialid() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.CCUSTMATERIALID);
  }

  // 折扣类
  public static final String BDISCOUNTFLAG = "bdiscountflag";

  // 散户标志
  public static final String BFREECUSTFLAG = "bfreecustflag";

  // 服务类
  public static final String BLABORFLAG = "blaborflag";

  // 赠品
  public static final String BLARGESSFLAG = "blargessflag";

  // 应收组织最新版本
  public static final String CARORGID = "carorgid";

  // 应收组织
  public static final String CARORGVID = "carorgvid";

  // 单位
  public static final String CASTUNITID = "castunitid";

  /**
   * 销售渠道类型
   */
  public static final String CCHANNELTYPEID = "cchanneltypeid";

  // 收支项目
  public static final String CCOSTSUBJID = "ccostsubjid";

  // 合同号
  public static final String CCTMANAGEID = "cctmanageid";

  // 销售部门最新版本
  public static final String CDEPTID = "cdeptid";

  // 销售部门
  public static final String CDEPTVID = "cdeptvid";

  // 销售业务员
  public static final String CEMPLOYEEID = "cemployeeid";

  // 源头单据子表(不允许编辑)
  public static final String CFIRSTBID = "cfirstbid";

  // 源头单据主表(不允许编辑)
  public static final String CFIRSTID = "cfirstid";

  // 散户
  public static final String CFREECUSTID = "cfreecustid";

  // 物料基本分类(不允许编辑)
  public static final String CMARBASCALSSID = "cmarbascalssid";

  // 物料档案
  public static final String CMATERIALID = "cmaterialid";

  // 物料编码
  public static final String CMATERIALVID = "cmaterialvid";

  // 对冲来源子表
  public static final String COPPOSESRCBID = "copposesrcbid";

  // 订单客户
  public static final String CORDERCUSTID = "cordercustid";

  // 产品线
  public static final String CPRODLINEID = "cprodlineid";

  // 生产厂商
  public static final String CPRODUCTORID = "cproductorid";

  // 结算利润中心最新版本
  public static final String CPROFITCENTERID = "cprofitcenterid";

  // 结算利润中心
  public static final String CPROFITCENTERVID = "cprofitcentervid";

  // 项目
  public static final String CPROJECTID = "cprojectid";

  // 报价单位
  public static final String CQTUNITID = "cqtunitid";

  // 收货地址
  public static final String CRECEIVEADDRID = "creceiveaddrid";

  // 收货客户
  public static final String CRECEIVECUSTID = "creceivecustid";

  // 行号
  public static final String CROWNO = "crowno";

  // 发票子实体（不允许编辑）
  public static final String CSALEINVOICEBID = "csaleinvoicebid";

  // 销售发票主实体_主键（不允许编辑）
  public static final String CSALEINVOICEID = "csaleinvoiceid";

  // 销售组织最新版本（不允许编辑）
  public static final String CSALEORGID = "csaleorgid";

  // 销售组织
  public static final String CSALEORGVID = "csaleorgvid";

  // 库存组织最新版本（不允许编辑）
  public static final String CSENDSTOCKORGID = "csendstockorgid";

  // 库存组织
  public static final String CSENDSTOCKORGVID = "csendstockorgvid";

  // 仓库（不允许编辑）
  public static final String CSENDSTORDOCID = "csendstordocid";

  // 来源单据子表（不允许编辑）
  public static final String CSRCBID = "csrcbid";

  // 来源单据主表（不允许编辑）
  public static final String CSRCID = "csrcid";

  // 消耗汇总主键（不允许编辑）
  public static final String CSUMID = "csumid";

  /**
   * 税码(v61)
   */
  public static final String CTAXCODEID = "ctaxcodeid";

  // 运输方式
  public static final String CTRANSPORTTYPEID = "ctransporttypeid";

  // 主单位（不允许编辑）
  public static final String CUNITID = "cunitid";

  // 供应商
  public static final String CVENDORID = "cvendorid";

  // 寄存供应商（不允许编辑）
  public static final String CVMIVENDERID = "cvmivenderid";

  // 开票日期（不允许编辑）
  public static final String DBILLDATE = "dbilldate";

  // dr
  public static final String DR = "dr";

  /**
   * 扣税类别
   */
  public static final String FTAXTYPEFLAG = "ftaxtypeflag";

  /** SaleInvoiceBVO元数据路径(元数据主表上的子表聚合名称) */
  public static final String MAINMETAPATH = "csaleinvoicebid.";

  // 数量
  public static final String NASTNUM = "nastnum";

  /**
   * 计税金额
   */
  public static final String NCALTAXMNY = "ncaltaxmny";

  // ncanoutnum
  public static final String NCANOUTNUM = "ncanoutnum";

  // 本币折扣额（不允许编辑）
  public static final String NDISCOUNT = "ndiscount";

  // 整单折扣
  public static final String NDISCOUNTRATE = "ndiscountrate";

  // 全局本币无税金额（不允许编辑）
  public static final String NGLOBALMNY = "nglobalmny";

  // 全局本币价税合计（不允许编辑）
  public static final String NGLOBALTAXMNY = "nglobaltaxmny";

  // 集团本币无税金额（不允许编辑）
  public static final String NGROUPMNY = "ngroupmny";

  // 集团本币价税合计（不允许编辑）
  public static final String NGROUPTAXMNY = "ngrouptaxmny";

  // 发票折扣
  public static final String NINVOICEDISRATE = "ninvoicedisrate";

  // 冲抵前金额（不允许编辑）
  // public static final String NBFORIGSUBMNY = "nbforigsubmny";

  // 单品折扣
  public static final String NITEMDISCOUNTRATE = "nitemdiscountrate";

  // 本币无税金额（不允许编辑）
  public static final String NMNY = "nmny";

  // 主本币无税净价（不允许编辑）
  public static final String NNETPRICE = "nnetprice";

  // 主数量
  public static final String NNUM = "nnum";

  // 折扣额
  public static final String NORIGDISCOUNT = "norigdiscount";

  // 无税金额
  public static final String NORIGMNY = "norigmny";

  // 主无税净价
  public static final String NORIGNETPRICE = "norignetprice";

  // 主无税单价
  public static final String NORIGPRICE = "norigprice";

  // 费用冲抵金额（不允许编辑）
  public static final String NORIGSUBMNY = "norigsubmny";

  // 价税合计
  public static final String NORIGTAXMNY = "norigtaxmny";

  // 主含税净价
  public static final String NORIGTAXNETPRICE = "norigtaxnetprice";

  // 主含税单价
  public static final String NORIGTAXPRICE = "norigtaxprice";

  // 税额
  // public static final String NORIGTAX = "norigtax";

  // 主本币无税单价（不允许编辑）
  public static final String NPRICE = "nprice";

  // 本币无税净价（不允许编辑）
  public static final String NQTNETPRICE = "nqtnetprice";

  // 无税净价
  public static final String NQTORIGNETPRICE = "nqtorignetprice";

  // 无税单价
  public static final String NQTORIGPRICE = "nqtorigprice";

  // 含税净价
  public static final String NQTORIGTAXNETPRC = "nqtorigtaxnetprc";

  // 含税单价
  public static final String NQTORIGTAXPRICE = "nqtorigtaxprice";

  // 本币无税单价（不允许编辑）
  public static final String NQTPRICE = "nqtprice";

  // 本币含税净价（不允许编辑）
  public static final String NQTTAXNETPRICE = "nqttaxnetprice";

  // 本币含税单价（不允许编辑）
  public static final String NQTTAXPRICE = "nqttaxprice";

  // 报价数量
  public static final String NQTUNITNUM = "nqtunitnum";

  // 累计应发未出库数量（不允许编辑）
  public static final String NSHOULDOUTNUM = "nshouldoutnum";

  // nsrcnum
  public static final String NSRCNUM = "nsrcnum";

  // 本币税额（不允许编辑）
  public static final String NTAX = "ntax";

  // 本币价税合计（不允许编辑）
  public static final String NTAXMNY = "ntaxmny";

  // 主本币含税净价（不允许编辑）
  public static final String NTAXNETPRICE = "ntaxnetprice";

  // 主本币含税单价（不允许编辑）
  public static final String NTAXPRICE = "ntaxprice";

  // 税率
  public static final String NTAXRATE = "ntaxrate";

  // 累计成本结算数量（不允许编辑）
  public static final String NTOTALCOSTNUM = "ntotalcostnum";

  // 累计确认应收金额（不允许编辑）
  public static final String NTOTALINCOMEMNY = "ntotalincomemny";

  // 累计确认应收数量（不允许编辑）
  public static final String NTOTALINCOMENUM = "ntotalincomenum";

  // 累计出库数量（不允许编辑）
  public static final String NTOTALOUTNUM = "ntotaloutnum";

  // 累计收款金额（不允许编辑）
  public static final String NTOTALPAYMNY = "ntotalpaymny";

  // 批次档案
  public static final String PK_BATCHCODE = "pk_batchcode";

  // 集团
  public static final String PK_GROUP = "pk_group";

  // 开票组织
  public static final String PK_ORG = "pk_org";

  // srcbts（不允许编辑）
  public static final String SRCBTS = "srcbts";

  // srcts（不允许编辑）
  public static final String SRCTS = "srcts";

  // 时间戳（不允许编辑）
  public static final String TS = "ts";

  // 批次号
  public static final String VBATCHCODE = "vbatchcode";

  // 自定义项1
  public static final String VBDEF1 = "vbdef1";

  // 自定义项10
  public static final String VBDEF10 = "vbdef10";

  // 自定义项11
  public static final String VBDEF11 = "vbdef11";

  // 自定义项12
  public static final String VBDEF12 = "vbdef12";

  // 自定义项13
  public static final String VBDEF13 = "vbdef13";

  // 自定义项14
  public static final String VBDEF14 = "vbdef14";

  // 自定义项15
  public static final String VBDEF15 = "vbdef15";

  // 自定义项16
  public static final String VBDEF16 = "vbdef16";

  // 自定义项17
  public static final String VBDEF17 = "vbdef17";

  // 自定义项18
  public static final String VBDEF18 = "vbdef18";

  // 自定义项19
  public static final String VBDEF19 = "vbdef19";

  // 自定义项2
  public static final String VBDEF2 = "vbdef2";

  // 自定义项20
  public static final String VBDEF20 = "vbdef20";

  // 自定义项3
  public static final String VBDEF3 = "vbdef3";

  // 自定义项4
  public static final String VBDEF4 = "vbdef4";

  // 自定义项5
  public static final String VBDEF5 = "vbdef5";

  // 自定义项6
  public static final String VBDEF6 = "vbdef6";

  // 自定义项7
  public static final String VBDEF7 = "vbdef7";

  // 自定义项8
  public static final String VBDEF8 = "vbdef8";

  // 自定义项9
  public static final String VBDEF9 = "vbdef9";

  // 换算率
  public static final String VCHANGERATE = "vchangerate";

  // 源头单据号（不允许编辑）
  public static final String VFIRSTCODE = "vfirstcode";

  // 源头单据行号（不允许编辑）
  public static final String VFIRSTROWNO = "vfirstrowno";

  // 源头交易类型
  public static final String VFIRSTTRANTYPE = "vfirsttrantype";

  // 源头单据类型
  public static final String VFIRSTTYPE = "vfirsttype";

  // 物料辅助属性1
  public static final String VFREE1 = "vfree1";

  // 物料辅助属性10
  public static final String VFREE10 = "vfree10";

  // 物料辅助属性2
  public static final String VFREE2 = "vfree2";

  // 物料辅助属性3
  public static final String VFREE3 = "vfree3";

  // 物料辅助属性4
  public static final String VFREE4 = "vfree4";

  // 物料辅助属性5
  public static final String VFREE5 = "vfree5";

  // 物料辅助属性6
  public static final String VFREE6 = "vfree6";

  // 物料辅助属性7
  public static final String VFREE7 = "vfree7";

  // 物料辅助属性8
  public static final String VFREE8 = "vfree8";

  // 物料辅助属性9
  public static final String VFREE9 = "vfree9";

  // 报价单位换算率
  public static final String VQTUNITRATE = "vqtunitrate";

  // 备注
  public static final String VROWNOTE = "vrownote";

  // 来源单据号（不允许编辑）
  public static final String VSRCCODE = "vsrccode";

  // 来源单据行号（不允许编辑）
  public static final String VSRCROWNO = "vsrcrowno";

  // 来源交易类型
  public static final String VSRCTRANTYPE = "vsrctrantype";

  // 来源单据类型
  public static final String VSRCTYPE = "vsrctype";

  // 消耗汇总号
  public static final String VSUMCODE = "vsumcode";

  /** 发货利润中心 */
  public static final String CSPROFITCENTERVID = "csprofitcentervid";

  /** 发货利润中心版本 */
  public static final String CSPROFITCENTERID = "csprofitcenterid";

  /**
   * 
   */
  private static final long serialVersionUID = -5360485037679893882L;

  /**
   * SaleInvoiceBVO 的构造子
   */
  public SaleInvoiceBVO() {
    super();
  }

  public UFBoolean getBdiscountflag() {
    return (UFBoolean) this.getAttributeValue(SaleInvoiceBVO.BDISCOUNTFLAG);
  }

  public UFBoolean getBfreecustflag() {
    return (UFBoolean) this.getAttributeValue(SaleInvoiceBVO.BFREECUSTFLAG);
  }

  public UFBoolean getBlaborflag() {
    return (UFBoolean) this.getAttributeValue(SaleInvoiceBVO.BLABORFLAG);
  }

  public UFBoolean getBlargessflag() {
    return (UFBoolean) this.getAttributeValue(SaleInvoiceBVO.BLARGESSFLAG);
  }

  public String getCarorgid() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.CARORGID);
  }

  public String getCarorgvid() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.CARORGVID);
  }

  public String getCastunitid() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.CASTUNITID);
  }

  /**
   * 获取销售渠道类型
   * 
   * @return 销售渠道类型
   */
  public String getCchanneltypeid() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.CCHANNELTYPEID);
  }

  public String getCcostsubjid() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.CCOSTSUBJID);
  }

  public String getCctmanageid() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.CCTMANAGEID);
  }

  public String getCdeptid() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.CDEPTID);
  }

  public String getCdeptvid() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.CDEPTVID);
  }

  public String getCemployeeid() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.CEMPLOYEEID);
  }

  public String getCfirstbid() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.CFIRSTBID);
  }

  public String getCfirstid() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.CFIRSTID);
  }

  public String getCfreecustid() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.CFREECUSTID);
  }

  public String getCmarbascalssid() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.CMARBASCALSSID);
  }

  public String getCmaterialid() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.CMATERIALID);
  }

  public String getCmaterialvid() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.CMATERIALVID);
  }

  public String getCopposesrcbid() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.COPPOSESRCBID);
  }

  public String getCordercustid() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.CORDERCUSTID);
  }

  public String getCprodlineid() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.CPRODLINEID);
  }

  public String getCproductorid() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.CPRODUCTORID);
  }

  public String getCprofitcenterid() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.CPROFITCENTERID);
  }

  public String getCprofitcentervid() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.CPROFITCENTERVID);
  }

  public String getCprojectid() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.CPROJECTID);
  }

  public String getCqtunitid() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.CQTUNITID);
  }

  public String getCreceiveaddrid() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.CRECEIVEADDRID);
  }

  public String getCreceivecustid() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.CRECEIVECUSTID);
  }

  public String getCrowno() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.CROWNO);
  }

  public String getCsaleinvoicebid() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.CSALEINVOICEBID);
  }

  public String getCsaleinvoiceid() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.CSALEINVOICEID);
  }

  public String getCsaleorgid() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.CSALEORGID);
  }

  public String getCsaleorgvid() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.CSALEORGVID);
  }

  public String getCsendstockorgid() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.CSENDSTOCKORGID);
  }

  public String getCsendstockorgvid() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.CSENDSTOCKORGVID);
  }

  public String getCsendstordocid() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.CSENDSTORDOCID);
  }

  public String getCsrcbid() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.CSRCBID);
  }

  public String getCsrcid() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.CSRCID);
  }

  public String getCsumid() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.CSUMID);
  }

  /**
   * 获取税码
   * 
   * @return 税码
   */
  public String getCtaxcodeid() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.CTAXCODEID);
  }

  public String getCtransporttypeid() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.CTRANSPORTTYPEID);
  }

  public String getCunitid() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.CUNITID);
  }

  public String getCvendorid() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.CVENDORID);
  }

  public String getCvmivenderid() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.CVMIVENDERID);
  }

  public UFDate getDbilldate() {
    return (UFDate) this.getAttributeValue(SaleInvoiceBVO.DBILLDATE);
  }

  public Integer getDr() {
    return (Integer) this.getAttributeValue(SaleInvoiceBVO.DR);
  }

  /**
   * 获取计税类别
   * 
   * @return 计税类别
   */
  public Integer getFtaxtypeflag() {
    return (Integer) this.getAttributeValue(SaleInvoiceBVO.FTAXTYPEFLAG);
  }

  @Override
  public IVOMeta getMetaData() {
    IVOMeta meta = VOMetaFactory.getInstance().getVOMeta("so.saleinvoice_b");
    return meta;
  }

  public UFDouble getNastnum() {
    return (UFDouble) this.getAttributeValue(SaleInvoiceBVO.NASTNUM);
  }

  /**
   * 获取计税金额
   * 
   * @return 计税金额
   */
  public UFDouble getNcaltaxmny() {
    return (UFDouble) this.getAttributeValue(SaleInvoiceBVO.NCALTAXMNY);
  }

  public UFDouble getNcanoutnum() {
    return (UFDouble) this.getAttributeValue(SaleInvoiceBVO.NCANOUTNUM);
  }

  public UFDouble getNdiscount() {
    return (UFDouble) this.getAttributeValue(SaleInvoiceBVO.NDISCOUNT);
  }

  public UFDouble getNdiscountrate() {
    return (UFDouble) this.getAttributeValue(SaleInvoiceBVO.NDISCOUNTRATE);
  }

  public UFDouble getNglobalmny() {
    return (UFDouble) this.getAttributeValue(SaleInvoiceBVO.NGLOBALMNY);
  }

  public UFDouble getNglobaltaxmny() {
    return (UFDouble) this.getAttributeValue(SaleInvoiceBVO.NGLOBALTAXMNY);
  }

  public UFDouble getNgroupmny() {
    return (UFDouble) this.getAttributeValue(SaleInvoiceBVO.NGROUPMNY);
  }

  public UFDouble getNgrouptaxmny() {
    return (UFDouble) this.getAttributeValue(SaleInvoiceBVO.NGROUPTAXMNY);
  }

  public UFDouble getNinvoicedisrate() {
    return (UFDouble) this.getAttributeValue(SaleInvoiceBVO.NINVOICEDISRATE);
  }

  public UFDouble getNitemdiscountrate() {
    return (UFDouble) this.getAttributeValue(SaleInvoiceBVO.NITEMDISCOUNTRATE);
  }

  public UFDouble getNmny() {
    return (UFDouble) this.getAttributeValue(SaleInvoiceBVO.NMNY);
  }

  public UFDouble getNnetprice() {
    return (UFDouble) this.getAttributeValue(SaleInvoiceBVO.NNETPRICE);
  }

  public UFDouble getNnum() {
    return (UFDouble) this.getAttributeValue(SaleInvoiceBVO.NNUM);
  }

  public UFDouble getNorigdiscount() {
    return (UFDouble) this.getAttributeValue(SaleInvoiceBVO.NORIGDISCOUNT);
  }

  public UFDouble getNorigmny() {
    return (UFDouble) this.getAttributeValue(SaleInvoiceBVO.NORIGMNY);
  }

  public UFDouble getNorignetprice() {
    return (UFDouble) this.getAttributeValue(SaleInvoiceBVO.NORIGNETPRICE);
  }

  public UFDouble getNorigprice() {
    return (UFDouble) this.getAttributeValue(SaleInvoiceBVO.NORIGPRICE);
  }

  public UFDouble getNorigsubmny() {
    return (UFDouble) this.getAttributeValue(SaleInvoiceBVO.NORIGSUBMNY);
  }

  public UFDouble getNorigtaxmny() {
    return (UFDouble) this.getAttributeValue(SaleInvoiceBVO.NORIGTAXMNY);
  }

  public UFDouble getNorigtaxnetprice() {
    return (UFDouble) this.getAttributeValue(SaleInvoiceBVO.NORIGTAXNETPRICE);
  }

  public UFDouble getNorigtaxprice() {
    return (UFDouble) this.getAttributeValue(SaleInvoiceBVO.NORIGTAXPRICE);
  }

  // public UFDouble getNorigtax() {
  // return (UFDouble) this.getAttributeValue(SaleInvoiceBVO.NORIGTAX);
  // }

  public UFDouble getNprice() {
    return (UFDouble) this.getAttributeValue(SaleInvoiceBVO.NPRICE);
  }

  public UFDouble getNqtnetprice() {
    return (UFDouble) this.getAttributeValue(SaleInvoiceBVO.NQTNETPRICE);
  }

  public UFDouble getNqtorignetprice() {
    return (UFDouble) this.getAttributeValue(SaleInvoiceBVO.NQTORIGNETPRICE);
  }

  public UFDouble getNqtorigprice() {
    return (UFDouble) this.getAttributeValue(SaleInvoiceBVO.NQTORIGPRICE);
  }

  public UFDouble getNqtorigtaxnetprc() {
    return (UFDouble) this.getAttributeValue(SaleInvoiceBVO.NQTORIGTAXNETPRC);
  }

  public UFDouble getNqtorigtaxprice() {
    return (UFDouble) this.getAttributeValue(SaleInvoiceBVO.NQTORIGTAXPRICE);
  }

  public UFDouble getNqtprice() {
    return (UFDouble) this.getAttributeValue(SaleInvoiceBVO.NQTPRICE);
  }

  public UFDouble getNqttaxnetprice() {
    return (UFDouble) this.getAttributeValue(SaleInvoiceBVO.NQTTAXNETPRICE);
  }

  public UFDouble getNqttaxprice() {
    return (UFDouble) this.getAttributeValue(SaleInvoiceBVO.NQTTAXPRICE);
  }

  public UFDouble getNqtunitnum() {
    return (UFDouble) this.getAttributeValue(SaleInvoiceBVO.NQTUNITNUM);
  }

  public UFDouble getNshouldoutnum() {
    return (UFDouble) this.getAttributeValue(SaleInvoiceBVO.NSHOULDOUTNUM);
  }

  public UFDouble getNsrcnum() {
    return (UFDouble) this.getAttributeValue(SaleInvoiceBVO.NSRCNUM);
  }

  public UFDouble getNtax() {
    return (UFDouble) this.getAttributeValue(SaleInvoiceBVO.NTAX);
  }

  public UFDouble getNtaxmny() {
    return (UFDouble) this.getAttributeValue(SaleInvoiceBVO.NTAXMNY);
  }

  public UFDouble getNtaxnetprice() {
    return (UFDouble) this.getAttributeValue(SaleInvoiceBVO.NTAXNETPRICE);
  }

  public UFDouble getNtaxprice() {
    return (UFDouble) this.getAttributeValue(SaleInvoiceBVO.NTAXPRICE);
  }

  public UFDouble getNtaxrate() {
    return (UFDouble) this.getAttributeValue(SaleInvoiceBVO.NTAXRATE);
  }

  public UFDouble getNtotalcostnum() {
    return (UFDouble) this.getAttributeValue(SaleInvoiceBVO.NTOTALCOSTNUM);
  }

  public UFDouble getNtotalincomemny() {
    return (UFDouble) this.getAttributeValue(SaleInvoiceBVO.NTOTALINCOMEMNY);
  }

  public UFDouble getNtotalincomenum() {
    return (UFDouble) this.getAttributeValue(SaleInvoiceBVO.NTOTALINCOMENUM);
  }

  public UFDouble getNtotaloutnum() {
    return (UFDouble) this.getAttributeValue(SaleInvoiceBVO.NTOTALOUTNUM);
  }

  public UFDouble getNtotalpaymny() {
    return (UFDouble) this.getAttributeValue(SaleInvoiceBVO.NTOTALPAYMNY);
  }

  public String getPk_batchcode() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.PK_BATCHCODE);
  }

  public String getPk_group() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.PK_GROUP);
  }

  public String getPk_org() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.PK_ORG);
  }

  public UFDateTime getSrcbts() {
    return (UFDateTime) this.getAttributeValue(SaleInvoiceBVO.SRCBTS);
  }

  public UFDateTime getSrcts() {
    return (UFDateTime) this.getAttributeValue(SaleInvoiceBVO.SRCTS);
  }

  public UFDateTime getTs() {
    return (UFDateTime) this.getAttributeValue(SaleInvoiceBVO.TS);
  }

  public String getVbatchcode() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.VBATCHCODE);
  }

  public String getVbdef1() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.VBDEF1);
  }

  public String getVbdef10() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.VBDEF10);
  }

  public String getVbdef11() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.VBDEF11);
  }

  public String getVbdef12() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.VBDEF12);
  }

  public String getVbdef13() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.VBDEF13);
  }

  public String getVbdef14() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.VBDEF14);
  }

  public String getVbdef15() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.VBDEF15);
  }

  public String getVbdef16() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.VBDEF16);
  }

  public String getVbdef17() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.VBDEF17);
  }

  public String getVbdef18() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.VBDEF18);
  }

  public String getVbdef19() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.VBDEF19);
  }

  public String getVbdef2() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.VBDEF2);
  }

  public String getVbdef20() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.VBDEF20);
  }

  public String getVbdef3() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.VBDEF3);
  }

  public String getVbdef4() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.VBDEF4);
  }

  public String getVbdef5() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.VBDEF5);
  }

  public String getVbdef6() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.VBDEF6);
  }

  public String getVbdef7() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.VBDEF7);
  }

  public String getVbdef8() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.VBDEF8);
  }

  public String getVbdef9() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.VBDEF9);
  }

  public String getVchangerate() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.VCHANGERATE);
  }

  public String getVfirstcode() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.VFIRSTCODE);
  }

  public String getVfirstrowno() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.VFIRSTROWNO);
  }

  public String getVfirsttrantype() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.VFIRSTTRANTYPE);
  }

  public String getVfirsttype() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.VFIRSTTYPE);
  }

  public String getVfree1() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.VFREE1);
  }

  public String getVfree10() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.VFREE10);
  }

  public String getVfree2() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.VFREE2);
  }

  public String getVfree3() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.VFREE3);
  }

  public String getVfree4() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.VFREE4);
  }

  public String getVfree5() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.VFREE5);
  }

  public String getVfree6() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.VFREE6);
  }

  public String getVfree7() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.VFREE7);
  }

  public String getVfree8() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.VFREE8);
  }

  public String getVfree9() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.VFREE9);
  }

  public String getVqtunitrate() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.VQTUNITRATE);
  }

  public String getVrownote() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.VROWNOTE);
  }

  public String getVsrccode() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.VSRCCODE);
  }

  public String getVsrcrowno() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.VSRCROWNO);
  }

  public String getVsrctrantype() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.VSRCTRANTYPE);
  }

  public String getVsrctype() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.VSRCTYPE);
  }

  /**
   * 获取发货利润中心
   * 
   * @return 发货利润中心
   */
  public String getCsprofitcentervid() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.CSPROFITCENTERVID);
  }

  /**
   * 获取发货利润中心版本
   * 
   * @return 发货利润中心版本
   */
  public String getCsprofitcenterid() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.CSPROFITCENTERID);
  }

  public String getVsumcode() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.VSUMCODE);
  }

  public void setBdiscountflag(UFBoolean bdiscountflag) {
    this.setAttributeValue(SaleInvoiceBVO.BDISCOUNTFLAG, bdiscountflag);
  }

  public void setBfreecustflag(UFBoolean bfreecustflag) {
    this.setAttributeValue(SaleInvoiceBVO.BFREECUSTFLAG, bfreecustflag);
  }

  public void setBlaborflag(UFBoolean blaborflag) {
    this.setAttributeValue(SaleInvoiceBVO.BLABORFLAG, blaborflag);
  }

  public void setBlargessflag(UFBoolean blargessflag) {
    this.setAttributeValue(SaleInvoiceBVO.BLARGESSFLAG, blargessflag);
  }

  public void setCarorgid(String carorgid) {
    this.setAttributeValue(SaleInvoiceBVO.CARORGID, carorgid);
  }

  public void setCarorgvid(String carorgvid) {
    this.setAttributeValue(SaleInvoiceBVO.CARORGVID, carorgvid);
  }

  public void setCastunitid(String castunitid) {
    this.setAttributeValue(SaleInvoiceBVO.CASTUNITID, castunitid);
  }

  /**
   * 设置销售渠道类型
   * 
   * @param cchanneltypeid 销售渠道类型
   */
  public void setCchanneltypeid(String cchanneltypeid) {
    this.setAttributeValue(SaleInvoiceBVO.CCHANNELTYPEID, cchanneltypeid);
  }

  public void setCcostsubjid(String ccostsubjid) {
    this.setAttributeValue(SaleInvoiceBVO.CCOSTSUBJID, ccostsubjid);
  }

  public void setCctmanageid(String cctmanageid) {
    this.setAttributeValue(SaleInvoiceBVO.CCTMANAGEID, cctmanageid);
  }

  public void setCdeptid(String cdeptid) {
    this.setAttributeValue(SaleInvoiceBVO.CDEPTID, cdeptid);
  }

  public void setCdeptvid(String cdeptvid) {
    this.setAttributeValue(SaleInvoiceBVO.CDEPTVID, cdeptvid);
  }

  public void setCemployeeid(String cemployeeid) {
    this.setAttributeValue(SaleInvoiceBVO.CEMPLOYEEID, cemployeeid);
  }

  public void setCfirstbid(String cfirstbid) {
    this.setAttributeValue(SaleInvoiceBVO.CFIRSTBID, cfirstbid);
  }

  public void setCfirstid(String cfirstid) {
    this.setAttributeValue(SaleInvoiceBVO.CFIRSTID, cfirstid);
  }

  public void setCfreecustid(String cfreecustid) {
    this.setAttributeValue(SaleInvoiceBVO.CFREECUSTID, cfreecustid);
  }

  public void setCmarbascalssid(String cmarbascalssid) {
    this.setAttributeValue(SaleInvoiceBVO.CMARBASCALSSID, cmarbascalssid);
  }

  public void setCmaterialid(String cmaterialid) {
    this.setAttributeValue(SaleInvoiceBVO.CMATERIALID, cmaterialid);
  }

  public void setCmaterialvid(String cmaterialvid) {
    this.setAttributeValue(SaleInvoiceBVO.CMATERIALVID, cmaterialvid);
  }

  public void setCopposesrcbid(String copposesrcbid) {
    this.setAttributeValue(SaleInvoiceBVO.COPPOSESRCBID, copposesrcbid);
  }

  public void setCordercustid(String cordercustid) {
    this.setAttributeValue(SaleInvoiceBVO.CORDERCUSTID, cordercustid);
  }

  public void setCprodlineid(String cprodlineid) {
    this.setAttributeValue(SaleInvoiceBVO.CPRODLINEID, cprodlineid);
  }

  public void setCproductorid(String cproductorid) {
    this.setAttributeValue(SaleInvoiceBVO.CPRODUCTORID, cproductorid);
  }

  public void setCprofitcenterid(String cprofitcenterid) {
    this.setAttributeValue(SaleInvoiceBVO.CPROFITCENTERID, cprofitcenterid);
  }

  public void setCprofitcentervid(String cprofitcentervid) {
    this.setAttributeValue(SaleInvoiceBVO.CPROFITCENTERVID, cprofitcentervid);
  }

  public void setCprojectid(String cprojectid) {
    this.setAttributeValue(SaleInvoiceBVO.CPROJECTID, cprojectid);
  }

  public void setCqtunitid(String cqtunitid) {
    this.setAttributeValue(SaleInvoiceBVO.CQTUNITID, cqtunitid);
  }

  public void setCreceiveaddrid(String creceiveaddrid) {
    this.setAttributeValue(SaleInvoiceBVO.CRECEIVEADDRID, creceiveaddrid);
  }

  public void setCreceivecustid(String creceivecustid) {
    this.setAttributeValue(SaleInvoiceBVO.CRECEIVECUSTID, creceivecustid);
  }

  public void setCrowno(String crowno) {
    this.setAttributeValue(SaleInvoiceBVO.CROWNO, crowno);
  }

  public void setCsaleinvoicebid(String csaleinvoicebid) {
    this.setAttributeValue(SaleInvoiceBVO.CSALEINVOICEBID, csaleinvoicebid);
  }

  public void setCsaleinvoiceid(String csaleinvoiceid) {
    this.setAttributeValue(SaleInvoiceBVO.CSALEINVOICEID, csaleinvoiceid);
  }

  public void setCsaleorgid(String csaleorgid) {
    this.setAttributeValue(SaleInvoiceBVO.CSALEORGID, csaleorgid);
  }

  public void setCsaleorgvid(String csaleorgvid) {
    this.setAttributeValue(SaleInvoiceBVO.CSALEORGVID, csaleorgvid);
  }

  public void setCsendstockorgid(String csendstockorgid) {
    this.setAttributeValue(SaleInvoiceBVO.CSENDSTOCKORGID, csendstockorgid);
  }

  public void setCsendstockorgvid(String csendstockorgvid) {
    this.setAttributeValue(SaleInvoiceBVO.CSENDSTOCKORGVID, csendstockorgvid);
  }

  public void setCsendstordocid(String csendstordocid) {
    this.setAttributeValue(SaleInvoiceBVO.CSENDSTORDOCID, csendstordocid);
  }

  public void setCsrcbid(String csrcbid) {
    this.setAttributeValue(SaleInvoiceBVO.CSRCBID, csrcbid);
  }

  public void setCsrcid(String csrcid) {
    this.setAttributeValue(SaleInvoiceBVO.CSRCID, csrcid);
  }

  public void setCsumid(String csumid) {
    this.setAttributeValue(SaleInvoiceBVO.CSUMID, csumid);
  }

  public void setCtaxcodeid(String ctaxcodeid) {
    this.setAttributeValue(SaleInvoiceBVO.CTAXCODEID, ctaxcodeid);
  }

  public void setCtransporttypeid(String ctransporttypeid) {
    this.setAttributeValue(SaleInvoiceBVO.CTRANSPORTTYPEID, ctransporttypeid);
  }

  public void setCunitid(String cunitid) {
    this.setAttributeValue(SaleInvoiceBVO.CUNITID, cunitid);
  }

  public void setCvendorid(String cvendorid) {
    this.setAttributeValue(SaleInvoiceBVO.CVENDORID, cvendorid);
  }

  public void setCvmivenderid(String cvmivenderid) {
    this.setAttributeValue(SaleInvoiceBVO.CVMIVENDERID, cvmivenderid);
  }

  public void setDbilldate(UFDate dbilldate) {
    this.setAttributeValue(SaleInvoiceBVO.DBILLDATE, dbilldate);
  }

  public void setDr(Integer dr) {
    this.setAttributeValue(SaleInvoiceBVO.DR, dr);
  }

  public void setFtaxtypeflag(Integer ftaxtypeflag) {
    this.setAttributeValue(SaleInvoiceBVO.FTAXTYPEFLAG, ftaxtypeflag);
  }

  public void setNastnum(UFDouble nastnum) {
    this.setAttributeValue(SaleInvoiceBVO.NASTNUM, nastnum);
  }

  public void setNcaltaxmny(UFDouble ncaltaxmny) {
    this.setAttributeValue(SaleInvoiceBVO.NCALTAXMNY, ncaltaxmny);
  }

  public void setNcanoutnum(UFDouble ncanoutnum) {
    this.setAttributeValue(SaleInvoiceBVO.NCANOUTNUM, ncanoutnum);
  }

  public void setNdiscount(UFDouble ndiscount) {
    this.setAttributeValue(SaleInvoiceBVO.NDISCOUNT, ndiscount);
  }

  public void setNdiscountrate(UFDouble ndiscountrate) {
    this.setAttributeValue(SaleInvoiceBVO.NDISCOUNTRATE, ndiscountrate);
  }

  public void setNglobalmny(UFDouble nglobalmny) {
    this.setAttributeValue(SaleInvoiceBVO.NGLOBALMNY, nglobalmny);
  }

  public void setNglobaltaxmny(UFDouble nglobaltaxmny) {
    this.setAttributeValue(SaleInvoiceBVO.NGLOBALTAXMNY, nglobaltaxmny);
  }

  public void setNgroupmny(UFDouble ngroupmny) {
    this.setAttributeValue(SaleInvoiceBVO.NGROUPMNY, ngroupmny);
  }

  public void setNgrouptaxmny(UFDouble ngrouptaxmny) {
    this.setAttributeValue(SaleInvoiceBVO.NGROUPTAXMNY, ngrouptaxmny);
  }

  public void setNinvoicedisrate(UFDouble ninvoicedisrate) {
    this.setAttributeValue(SaleInvoiceBVO.NINVOICEDISRATE, ninvoicedisrate);
  }

  public void setNitemdiscountrate(UFDouble nitemdiscountrate) {
    this.setAttributeValue(SaleInvoiceBVO.NITEMDISCOUNTRATE, nitemdiscountrate);
  }

  public void setNmny(UFDouble nmny) {
    this.setAttributeValue(SaleInvoiceBVO.NMNY, nmny);
  }

  public void setNnetprice(UFDouble nnetprice) {
    this.setAttributeValue(SaleInvoiceBVO.NNETPRICE, nnetprice);
  }

  public void setNnum(UFDouble nnum) {
    this.setAttributeValue(SaleInvoiceBVO.NNUM, nnum);
  }

  public void setNorigdiscount(UFDouble norigdiscount) {
    this.setAttributeValue(SaleInvoiceBVO.NORIGDISCOUNT, norigdiscount);
  }

  public void setNorigmny(UFDouble norigmny) {
    this.setAttributeValue(SaleInvoiceBVO.NORIGMNY, norigmny);
  }

  public void setNorignetprice(UFDouble norignetprice) {
    this.setAttributeValue(SaleInvoiceBVO.NORIGNETPRICE, norignetprice);
  }

  public void setNorigprice(UFDouble norigprice) {
    this.setAttributeValue(SaleInvoiceBVO.NORIGPRICE, norigprice);
  }

  public void setNorigsubmny(UFDouble norigsubmny) {
    this.setAttributeValue(SaleInvoiceBVO.NORIGSUBMNY, norigsubmny);
  }

  // public void setNorigtax(UFDouble norigtax) {
  // this.setAttributeValue(SaleInvoiceBVO.NORIGTAX, norigtax);
  // }

  public void setNorigtaxmny(UFDouble norigtaxmny) {
    this.setAttributeValue(SaleInvoiceBVO.NORIGTAXMNY, norigtaxmny);
  }

  public void setNorigtaxnetprice(UFDouble norigtaxnetprice) {
    this.setAttributeValue(SaleInvoiceBVO.NORIGTAXNETPRICE, norigtaxnetprice);
  }

  public void setNorigtaxprice(UFDouble norigtaxprice) {
    this.setAttributeValue(SaleInvoiceBVO.NORIGTAXPRICE, norigtaxprice);
  }

  public void setNprice(UFDouble nprice) {
    this.setAttributeValue(SaleInvoiceBVO.NPRICE, nprice);
  }

  public void setNqtnetprice(UFDouble nqtnetprice) {
    this.setAttributeValue(SaleInvoiceBVO.NQTNETPRICE, nqtnetprice);
  }

  public void setNqtorignetprice(UFDouble nqtorignetprice) {
    this.setAttributeValue(SaleInvoiceBVO.NQTORIGNETPRICE, nqtorignetprice);
  }

  public void setNqtorigprice(UFDouble nqtorigprice) {
    this.setAttributeValue(SaleInvoiceBVO.NQTORIGPRICE, nqtorigprice);
  }

  public void setNqtorigtaxnetprc(UFDouble nqtorigtaxnetprc) {
    this.setAttributeValue(SaleInvoiceBVO.NQTORIGTAXNETPRC, nqtorigtaxnetprc);
  }

  public void setNqtorigtaxprice(UFDouble nqtorigtaxprice) {
    this.setAttributeValue(SaleInvoiceBVO.NQTORIGTAXPRICE, nqtorigtaxprice);
  }

  public void setNqtprice(UFDouble nqtprice) {
    this.setAttributeValue(SaleInvoiceBVO.NQTPRICE, nqtprice);
  }

  public void setNqttaxnetprice(UFDouble nqttaxnetprice) {
    this.setAttributeValue(SaleInvoiceBVO.NQTTAXNETPRICE, nqttaxnetprice);
  }

  public void setNqttaxprice(UFDouble nqttaxprice) {
    this.setAttributeValue(SaleInvoiceBVO.NQTTAXPRICE, nqttaxprice);
  }

  public void setNqtunitnum(UFDouble nqtunitnum) {
    this.setAttributeValue(SaleInvoiceBVO.NQTUNITNUM, nqtunitnum);
  }

  public void setNshouldoutnum(UFDouble nshouldoutnum) {
    this.setAttributeValue(SaleInvoiceBVO.NSHOULDOUTNUM, nshouldoutnum);
  }

  public void setNsrcnum(UFDouble nsrcnum) {
    this.setAttributeValue(SaleInvoiceBVO.NSRCNUM, nsrcnum);
  }

  public void setNtax(UFDouble ntax) {
    this.setAttributeValue(SaleInvoiceBVO.NTAX, ntax);
  }

  public void setNtaxmny(UFDouble ntaxmny) {
    this.setAttributeValue(SaleInvoiceBVO.NTAXMNY, ntaxmny);
  }

  public void setNtaxnetprice(UFDouble ntaxnetprice) {
    this.setAttributeValue(SaleInvoiceBVO.NTAXNETPRICE, ntaxnetprice);
  }

  public void setNtaxprice(UFDouble ntaxprice) {
    this.setAttributeValue(SaleInvoiceBVO.NTAXPRICE, ntaxprice);
  }

  public void setNtaxrate(UFDouble ntaxrate) {
    this.setAttributeValue(SaleInvoiceBVO.NTAXRATE, ntaxrate);
  }

  public void setNtotalcostnum(UFDouble ntotalcostnum) {
    this.setAttributeValue(SaleInvoiceBVO.NTOTALCOSTNUM, ntotalcostnum);
  }

  public void setNtotalincomemny(UFDouble ntotalincomemny) {
    this.setAttributeValue(SaleInvoiceBVO.NTOTALINCOMEMNY, ntotalincomemny);
  }

  public void setNtotalincomenum(UFDouble ntotalincomenum) {
    this.setAttributeValue(SaleInvoiceBVO.NTOTALINCOMENUM, ntotalincomenum);
  }

  public void setNtotaloutnum(UFDouble ntotaloutnum) {
    this.setAttributeValue(SaleInvoiceBVO.NTOTALOUTNUM, ntotaloutnum);
  }

  public void setNtotalpaymny(UFDouble ntotalpaymny) {
    this.setAttributeValue(SaleInvoiceBVO.NTOTALPAYMNY, ntotalpaymny);
  }

  public void setPk_batchcode(String pk_batchcode) {
    this.setAttributeValue(SaleInvoiceBVO.PK_BATCHCODE, pk_batchcode);
  }

  public void setPk_group(String pk_group) {
    this.setAttributeValue(SaleInvoiceBVO.PK_GROUP, pk_group);
  }

  public void setPk_org(String pk_org) {
    this.setAttributeValue(SaleInvoiceBVO.PK_ORG, pk_org);
  }

  public void setSrcbts(UFDateTime srcbts) {
    this.setAttributeValue(SaleInvoiceBVO.SRCBTS, srcbts);
  }

  public void setSrcts(UFDateTime srcts) {
    this.setAttributeValue(SaleInvoiceBVO.SRCTS, srcts);
  }

  public void setTs(UFDateTime ts) {
    this.setAttributeValue(SaleInvoiceBVO.TS, ts);
  }

  public void setVbatchcode(String vbatchcode) {
    this.setAttributeValue(SaleInvoiceBVO.VBATCHCODE, vbatchcode);
  }

  public void setVbdef1(String vbdef1) {
    this.setAttributeValue(SaleInvoiceBVO.VBDEF1, vbdef1);
  }

  public void setVbdef10(String vbdef10) {
    this.setAttributeValue(SaleInvoiceBVO.VBDEF10, vbdef10);
  }

  public void setVbdef11(String vbdef11) {
    this.setAttributeValue(SaleInvoiceBVO.VBDEF11, vbdef11);
  }

  public void setVbdef12(String vbdef12) {
    this.setAttributeValue(SaleInvoiceBVO.VBDEF12, vbdef12);
  }

  public void setVbdef13(String vbdef13) {
    this.setAttributeValue(SaleInvoiceBVO.VBDEF13, vbdef13);
  }

  public void setVbdef14(String vbdef14) {
    this.setAttributeValue(SaleInvoiceBVO.VBDEF14, vbdef14);
  }

  public void setVbdef15(String vbdef15) {
    this.setAttributeValue(SaleInvoiceBVO.VBDEF15, vbdef15);
  }

  public void setVbdef16(String vbdef16) {
    this.setAttributeValue(SaleInvoiceBVO.VBDEF16, vbdef16);
  }

  public void setVbdef17(String vbdef17) {
    this.setAttributeValue(SaleInvoiceBVO.VBDEF17, vbdef17);
  }

  public void setVbdef18(String vbdef18) {
    this.setAttributeValue(SaleInvoiceBVO.VBDEF18, vbdef18);
  }

  public void setVbdef19(String vbdef19) {
    this.setAttributeValue(SaleInvoiceBVO.VBDEF19, vbdef19);
  }

  public void setVbdef2(String vbdef2) {
    this.setAttributeValue(SaleInvoiceBVO.VBDEF2, vbdef2);
  }

  public void setVbdef20(String vbdef20) {
    this.setAttributeValue(SaleInvoiceBVO.VBDEF20, vbdef20);
  }

  public void setVbdef3(String vbdef3) {
    this.setAttributeValue(SaleInvoiceBVO.VBDEF3, vbdef3);
  }

  public void setVbdef4(String vbdef4) {
    this.setAttributeValue(SaleInvoiceBVO.VBDEF4, vbdef4);
  }

  public void setVbdef5(String vbdef5) {
    this.setAttributeValue(SaleInvoiceBVO.VBDEF5, vbdef5);
  }

  public void setVbdef6(String vbdef6) {
    this.setAttributeValue(SaleInvoiceBVO.VBDEF6, vbdef6);
  }

  public void setVbdef7(String vbdef7) {
    this.setAttributeValue(SaleInvoiceBVO.VBDEF7, vbdef7);
  }

  public void setVbdef8(String vbdef8) {
    this.setAttributeValue(SaleInvoiceBVO.VBDEF8, vbdef8);
  }

  public void setVbdef9(String vbdef9) {
    this.setAttributeValue(SaleInvoiceBVO.VBDEF9, vbdef9);
  }

  public void setVchangerate(String vchangerate) {
    this.setAttributeValue(SaleInvoiceBVO.VCHANGERATE, vchangerate);
  }

  public void setVfirstcode(String vfirstcode) {
    this.setAttributeValue(SaleInvoiceBVO.VFIRSTCODE, vfirstcode);
  }

  public void setVfirstrowno(String vfirstrowno) {
    this.setAttributeValue(SaleInvoiceBVO.VFIRSTROWNO, vfirstrowno);
  }

  public void setVfirsttrantype(String vfirsttrantype) {
    this.setAttributeValue(SaleInvoiceBVO.VFIRSTTRANTYPE, vfirsttrantype);
  }

  public void setVfirsttype(String vfirsttype) {
    this.setAttributeValue(SaleInvoiceBVO.VFIRSTTYPE, vfirsttype);
  }

  public void setVfree1(String vfree1) {
    this.setAttributeValue(SaleInvoiceBVO.VFREE1, vfree1);
  }

  public void setVfree10(String vfree10) {
    this.setAttributeValue(SaleInvoiceBVO.VFREE10, vfree10);
  }

  public void setVfree2(String vfree2) {
    this.setAttributeValue(SaleInvoiceBVO.VFREE2, vfree2);
  }

  public void setVfree3(String vfree3) {
    this.setAttributeValue(SaleInvoiceBVO.VFREE3, vfree3);
  }

  public void setVfree4(String vfree4) {
    this.setAttributeValue(SaleInvoiceBVO.VFREE4, vfree4);
  }

  public void setVfree5(String vfree5) {
    this.setAttributeValue(SaleInvoiceBVO.VFREE5, vfree5);
  }

  public void setVfree6(String vfree6) {
    this.setAttributeValue(SaleInvoiceBVO.VFREE6, vfree6);
  }

  public void setVfree7(String vfree7) {
    this.setAttributeValue(SaleInvoiceBVO.VFREE7, vfree7);
  }

  public void setVfree8(String vfree8) {
    this.setAttributeValue(SaleInvoiceBVO.VFREE8, vfree8);
  }

  public void setVfree9(String vfree9) {
    this.setAttributeValue(SaleInvoiceBVO.VFREE9, vfree9);
  }

  public void setVqtunitrate(String vqtunitrate) {
    this.setAttributeValue(SaleInvoiceBVO.VQTUNITRATE, vqtunitrate);
  }

  public void setVrownote(String vrownote) {
    this.setAttributeValue(SaleInvoiceBVO.VROWNOTE, vrownote);
  }

  public void setVsrccode(String vsrccode) {
    this.setAttributeValue(SaleInvoiceBVO.VSRCCODE, vsrccode);
  }

  public void setVsrcrowno(String vsrcrowno) {
    this.setAttributeValue(SaleInvoiceBVO.VSRCROWNO, vsrcrowno);
  }

  public void setVsrctrantype(String vsrctrantype) {
    this.setAttributeValue(SaleInvoiceBVO.VSRCTRANTYPE, vsrctrantype);
  }

  public void setVsrctype(String vsrctype) {
    this.setAttributeValue(SaleInvoiceBVO.VSRCTYPE, vsrctype);
  }

  public void setVsumcode(String vsumcode) {
    this.setAttributeValue(SaleInvoiceBVO.VSUMCODE, vsumcode);
  }

  /**
   * 设置发货利润中心
   * 
   * @param csprofitcentervid 发货利润中心
   */
  public void setCsprofitcentervid(String csprofitcentervid) {
    this.setAttributeValue(SaleInvoiceBVO.CSPROFITCENTERVID, csprofitcentervid);
  }

  /**
   * 设置发货利润中心版本
   * 
   * @param csprofitcenterid 发货利润中心版本
   */
  public void setCsprofitcenterid(String csprofitcenterid) {
    this.setAttributeValue(SaleInvoiceBVO.CSPROFITCENTERID, csprofitcenterid);
  }
  
  /**
   * 获取特征码
   * 
   * @return 特征码
   */
  public String  getCmffileid() {
    return (String) this.getAttributeValue(SaleInvoiceBVO.CMFFILEID);
  }

  /**
   * 设置特征码
   * 
   * @param 特征码
   */
  public void setCmffileid(String cmffileid) {
    this.setAttributeValue(SaleInvoiceBVO.CMFFILEID, cmffileid);
  }
  
}
