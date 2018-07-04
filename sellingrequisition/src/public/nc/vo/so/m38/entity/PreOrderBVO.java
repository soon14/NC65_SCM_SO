package nc.vo.so.m38.entity;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

/**
 * 预订单表体VO
 * 
 * @since 6.0
 * @version 2010-03-31 下午13:38:44
 * @author 刘志伟
 */
public class PreOrderBVO extends SuperVO {

  /**
   * 客户物料编码
   */
  public static final String CCUSTMATERIALID = "ccustmaterialid";

  /**
   * 设置客户物料编码
   * 
   * @param ccustmaterialid
   * 
   */
  public void setCcustmaterialid(String ccustmaterialid) {
    this.setAttributeValue(PreOrderBVO.CCUSTMATERIALID, ccustmaterialid);
  }

  /**
   * 获取客户物料编码
   * 
   * @return 客户物料编码
   */
  public String getCcustmaterialid() {
    return (String) this.getAttributeValue(PreOrderBVO.CCUSTMATERIALID);
  }

  /** 赠品 */
  public static final String BLARGESSFLAG = "blargessflag";

  /** 行关闭 */
  public static final String BLINECLOSE = "blineclose";

  /**
   * 三角贸易
   */
  public static final String BTRIATRADEFLAG = "btriatradeflag";

  /** 应收组织 */
  public static final String CARORGID = "carorgid";

  /** 应收组织版本 */
  public static final String CARORGVID = "carorgvid";

  /** 最后安排人 */
  public static final String CARRANGEID = "carrangeid";

  /** 单位 */
  public static final String CASTUNITID = "castunitid";

  /** 本位币 */
  public static final String CCURRENCYID = "ccurrencyid";

  /** 物料档案 */
  public static final String CMATERIALID = "cmaterialid";

  /** 物料版本 */
  public static final String CMATERIALVID = "cmaterialvid";

  /** 币种 */
  public static final String CORIGCURRENCYID = "corigcurrencyid";

  /** 预订单子表主键 */
  public static final String CPREORDERBID = "cpreorderbid";

  /** 预订单主表_主键 */
  public static final String CPREORDERID = "cpreorderid";

  /** 价格组成 */
  public static final String CPRICEFORMID = "cpriceformid";

  /** 价格项目 */
  public static final String CPRICEITEMID = "cpriceitemid";

  /** 价目表 */
  public static final String CPRICEITEMTABLEID = "cpriceitemtableid";

  /** 价格政策 */
  public static final String CPRICEPOLICYID = "cpricepolicyid";

  /** 生产厂商 */
  public static final String CPRODUCTORID = "cproductorid";

  /** 利润中心 */
  public static final String CPROFITCENTERID = "cprofitcenterid";

  /** 利润中心版本 */
  public static final String CPROFITCENTERVID = "cprofitcentervid";

  /** 项目ID */
  public static final String CPROJECTID = "cprojectid";

  /** 报价计量单位 */
  public static final String CQTUNITID = "cqtunitid";

  /** 质量等级 */
  public static final String CQUALITYLEVELID = "cqualitylevelid";

  /**
   * 收货国家/地区
   */
  public static final String CRECECOUNTRYID = "crececountryid";

  /** 收货地址 */
  public static final String CRECEIVEADDRID = "creceiveaddrid";

  /** 收货地区 */
  public static final String CRECEIVEAREAID = "creceiveareaid";

  /** 收货客户 */
  public static final String CRECEIVECUSTID = "creceivecustid";

  /** 收货地点 */
  public static final String CRECEIVESITEID = "creceivesiteid";

  /** 行号 */
  public static final String CROWNO = "crowno";

  /**
   * 发货国家/地区
   */
  public static final String CSENDCOUNTRYID = "csendcountryid";

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

  /**
   * 税码
   */
  public static final String CTAXCODEID = "ctaxcodeid";

  /**
   * 报税国家/地区
   */
  public static final String CTAXCOUNTRYID = "ctaxcountryid";

  /** 物流组织 */
  public static final String CTRAFFICORGID = "ctrafficorgid";

  /** 物流组织版本 */
  public static final String CTRAFFICORGVID = "ctrafficorgvid";

  /** 主单位 */
  public static final String CUNITID = "cunitid";

  /** 供应商 */
  public static final String CVENDORID = "cvendorid";

  /** 最后安排日期 */
  public static final String DARRDATE = "darrdate";

  /** 单据日期 */
  public static final String DBILLDATE = "dbilldate";

  /** 要求到货日期 */
  public static final String DRECEIVEDATE = "dreceivedate";

  /** 计划发货日期 */
  public static final String DSENDDATE = "dsenddate";

  /**
   * 购销类型
   */
  public static final String FBUYSELLFLAG = "fbuysellflag";

  /** 行状态 */
  public static final String FROWSTATUS = "frowstatus";

  /**
   * 扣税类别
   */
  public static final String FTAXTYPEFLAG = "ftaxtypeflag";

  /** 累计安排数量 */
  public static final String NARRNUM = "narrnum";

  /** 询价主无税净价 */
  public static final String NASKQTORIGNETPRICE = "naskqtorignetprice";

  /** 询价主无税单价 */
  public static final String NASKQTORIGPRICE = "naskqtorigprice";

  /** 询价主含税单价 */
  public static final String NASKQTORIGTAXPRC = "naskqtorigtaxprc";

  /** 询价主含税净价 */
  public static final String NASKQTORIGTXNTPRC = "naskqtorigtxntprc";

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

  /** 主本币无税净价 */
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

  // TODO 刘景 2012-2-17 删除原币税额字段
  /** 税额 */
  // public static final String NORIGTAX = "norigtax";

  /** 价税合计 */
  public static final String NORIGTAXMNY = "norigtaxmny";

  /** 主含税净价 */
  public static final String NORIGTAXNETPRICE = "norigtaxnetprice";

  /** 主含税单价 */
  public static final String NORIGTAXPRICE = "norigtaxprice";

  /** 主本币无税单价 */
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

  /** 报价单位数量 */
  public static final String NQTUNITNUM = "nqtunitnum";

  /** 税额 */
  public static final String NTAX = "ntax";

  /** 本币价税合计 */
  public static final String NTAXMNY = "ntaxmny";

  /** 主本币含税净价 */
  public static final String NTAXNETPRICE = "ntaxnetprice";

  /** 主本币含税单价 */
  public static final String NTAXPRICE = "ntaxprice";

  /** 税率 */
  public static final String NTAXRATE = "ntaxrate";

  /** 集团 */
  public static final String PK_GROUP = "pk_group";

  /** 销售组织 */
  public static final String PK_ORG = "pk_org";

  /** 时间戳 */
  public static final String TS = "ts";

  /** 批次号 */
  public static final String VBATCHCODE = "vbatchcode";

  /** 批次档案 */
  public static final String PK_BATCHCODE = "PK_BATCHCODE";

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

  /** 换算系数 */
  public static final String VCHANGERATE = "vchangerate";

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

  /** 报价计量单位换算率 */
  public static final String VQTUNITRATE = "vqtunitrate";

  /** 行备注 */
  public static final String VROWNOTE = "vrownote";

  private static final long serialVersionUID = 1L;

  /**
   * 按照默认方式创建构造子.
   * 
   * 创建日期:2010-03-22 13:38:44
   */
  public PreOrderBVO() {
    super();
  }

  public UFBoolean getBlargessflag() {
    return (UFBoolean) this.getAttributeValue(PreOrderBVO.BLARGESSFLAG);
  }

  public UFBoolean getBlineclose() {
    return (UFBoolean) this.getAttributeValue(PreOrderBVO.BLINECLOSE);
  }

  /**
   * 获取三角贸易
   * 
   * @return 三角贸易
   */
  public UFBoolean getBtriatradeflag() {
    return (UFBoolean) this.getAttributeValue(PreOrderBVO.BTRIATRADEFLAG);
  }

  public String getCarorgid() {
    return (String) this.getAttributeValue(PreOrderBVO.CARORGID);
  }

  public String getCarorgvid() {
    return (String) this.getAttributeValue(PreOrderBVO.CARORGVID);
  }

  public String getCarrangeid() {
    return (String) this.getAttributeValue(PreOrderBVO.CARRANGEID);
  }

  public String getCastunitid() {
    return (String) this.getAttributeValue(PreOrderBVO.CASTUNITID);
  }

  public String getCcurrencyid() {
    return (String) this.getAttributeValue(PreOrderBVO.CCURRENCYID);
  }

  public String getCmaterialid() {
    return (String) this.getAttributeValue(PreOrderBVO.CMATERIALID);
  }

  public String getCmaterialvid() {
    return (String) this.getAttributeValue(PreOrderBVO.CMATERIALVID);
  }

  public String getCorigcurrencyid() {
    return (String) this.getAttributeValue(PreOrderBVO.CORIGCURRENCYID);
  }

  public String getCpreorderbid() {
    return (String) this.getAttributeValue(PreOrderBVO.CPREORDERBID);
  }

  public String getCpreorderid() {
    return (String) this.getAttributeValue(PreOrderBVO.CPREORDERID);
  }

  public String getCpriceformid() {
    return (String) this.getAttributeValue(PreOrderBVO.CPRICEFORMID);
  }

  public String getCpriceitemid() {
    return (String) this.getAttributeValue(PreOrderBVO.CPRICEITEMID);
  }

  public String getCpriceitemtableid() {
    return (String) this.getAttributeValue(PreOrderBVO.CPRICEITEMTABLEID);
  }

  public String getCpricepolicyid() {
    return (String) this.getAttributeValue(PreOrderBVO.CPRICEPOLICYID);
  }

  public String getCproductorid() {
    return (String) this.getAttributeValue(PreOrderBVO.CPRODUCTORID);
  }

  public String getCprofitcenterid() {
    return (String) this.getAttributeValue(PreOrderBVO.CPROFITCENTERID);
  }

  public String getCprofitcentervid() {
    return (String) this.getAttributeValue(PreOrderBVO.CPROFITCENTERVID);
  }

  public String getCprojectid() {
    return (String) this.getAttributeValue(PreOrderBVO.CPROJECTID);
  }

  public String getCqtunitid() {
    return (String) this.getAttributeValue(PreOrderBVO.CQTUNITID);
  }

  public String getCqualitylevelid() {
    return (String) this.getAttributeValue(PreOrderBVO.CQUALITYLEVELID);
  }

  /**
   * 获取收货国家/地区
   * 
   * @return 收获国家/地区
   */
  public String getCrececountryid() {
    return (String) this.getAttributeValue(PreOrderBVO.CRECECOUNTRYID);
  }

  public String getCreceiveaddrid() {
    return (String) this.getAttributeValue(PreOrderBVO.CRECEIVEADDRID);
  }

  public String getCreceiveareaid() {
    return (String) this.getAttributeValue(PreOrderBVO.CRECEIVEAREAID);
  }

  public String getCreceivecustid() {
    return (String) this.getAttributeValue(PreOrderBVO.CRECEIVECUSTID);
  }

  public String getCreceivesiteid() {
    return (String) this.getAttributeValue(PreOrderBVO.CRECEIVESITEID);
  }

  public String getCrowno() {
    return (String) this.getAttributeValue(PreOrderBVO.CROWNO);
  }

  /**
   * 获取发货国家/地区
   * 
   * @return 发货国家/地区
   */
  public String getCsendcountryid() {
    return (String) this.getAttributeValue(PreOrderBVO.CSENDCOUNTRYID);
  }

  public String getCsendstockorgid() {
    return (String) this.getAttributeValue(PreOrderBVO.CSENDSTOCKORGID);
  }

  public String getCsendstockorgvid() {
    return (String) this.getAttributeValue(PreOrderBVO.CSENDSTOCKORGVID);
  }

  public String getCsendstordocid() {
    return (String) this.getAttributeValue(PreOrderBVO.CSENDSTORDOCID);
  }

  public String getCsettleorgid() {
    return (String) this.getAttributeValue(PreOrderBVO.CSETTLEORGID);
  }

  public String getCsettleorgvid() {
    return (String) this.getAttributeValue(PreOrderBVO.CSETTLEORGVID);
  }

  /**
   * 获取税码
   * 
   * @return 税码
   */
  public String getCtaxcodeid() {
    return (String) this.getAttributeValue(PreOrderBVO.CTAXCODEID);
  }

  /**
   * 获取报税国家和地区
   * 
   * @return 报税国家/地区
   */
  public String getCtaxcountryid() {
    return (String) this.getAttributeValue(PreOrderBVO.CTAXCOUNTRYID);
  }

  public String getCtrafficorgid() {
    return (String) this.getAttributeValue(PreOrderBVO.CTRAFFICORGID);
  }

  public String getCtrafficorgvid() {
    return (String) this.getAttributeValue(PreOrderBVO.CTRAFFICORGVID);
  }

  public String getCunitid() {
    return (String) this.getAttributeValue(PreOrderBVO.CUNITID);
  }

  public String getCvendorid() {
    return (String) this.getAttributeValue(PreOrderBVO.CVENDORID);
  }

  public UFDate getDarrdate() {
    return (UFDate) this.getAttributeValue(PreOrderBVO.DARRDATE);
  }

  public UFDate getDbilldate() {
    return (UFDate) this.getAttributeValue(PreOrderBVO.DBILLDATE);
  }

  public UFDate getDreceivedate() {
    return (UFDate) this.getAttributeValue(PreOrderBVO.DRECEIVEDATE);
  }

  public UFDate getDsenddate() {
    return (UFDate) this.getAttributeValue(PreOrderBVO.DSENDDATE);
  }

  /**
   * 获取购销类型
   * 
   * @return 购销类型
   */
  public Integer getFbuysellflag() {
    return (Integer) this.getAttributeValue(PreOrderBVO.FBUYSELLFLAG);
  }

  public String getFrowstatus() {
    return (String) this.getAttributeValue(PreOrderBVO.FROWSTATUS);
  }

  /**
   * 获取报税类别
   * 
   * @return 报税类别
   */
  public Integer getFtaxtypeflag() {
    return (Integer) this.getAttributeValue(PreOrderBVO.FTAXTYPEFLAG);
  }

  @Override
  public IVOMeta getMetaData() {
    IVOMeta meta = VOMetaFactory.getInstance().getVOMeta("so.preorder_b");
    return meta;
  }

  public UFDouble getNarrnum() {
    return (UFDouble) this.getAttributeValue(PreOrderBVO.NARRNUM);
  }

  public UFDouble getNaskqtorignetprice() {
    return (UFDouble) this.getAttributeValue(PreOrderBVO.NASKQTORIGNETPRICE);
  }

  public UFDouble getNaskqtorigprice() {
    return (UFDouble) this.getAttributeValue(PreOrderBVO.NASKQTORIGPRICE);
  }

  public UFDouble getNaskqtorigtaxprc() {
    return (UFDouble) this.getAttributeValue(PreOrderBVO.NASKQTORIGTAXPRC);
  }

  public UFDouble getNaskqtorigtxntprc() {
    return (UFDouble) this.getAttributeValue(PreOrderBVO.NASKQTORIGTXNTPRC);
  }

  public UFDouble getNastnum() {
    return (UFDouble) this.getAttributeValue(PreOrderBVO.NASTNUM);
  }

  /**
   * 获取价税金额
   * 
   * @return 计税金额
   */
  public UFDouble getNcaltaxmny() {
    return (UFDouble) this.getAttributeValue(PreOrderBVO.NCALTAXMNY);
  }

  public UFDouble getNdiscount() {
    return (UFDouble) this.getAttributeValue(PreOrderBVO.NDISCOUNT);
  }

  public UFDouble getNdiscountrate() {
    return (UFDouble) this.getAttributeValue(PreOrderBVO.NDISCOUNTRATE);
  }

  public UFDouble getNexchangerate() {
    return (UFDouble) this.getAttributeValue(PreOrderBVO.NEXCHANGERATE);
  }

  public UFDouble getNglobalexchgrate() {
    return (UFDouble) this.getAttributeValue(PreOrderBVO.NGLOBALEXCHGRATE);
  }

  public UFDouble getNglobalmny() {
    return (UFDouble) this.getAttributeValue(PreOrderBVO.NGLOBALMNY);
  }

  public UFDouble getNglobaltaxmny() {
    return (UFDouble) this.getAttributeValue(PreOrderBVO.NGLOBALTAXMNY);
  }

  public UFDouble getNgroupexchgrate() {
    return (UFDouble) this.getAttributeValue(PreOrderBVO.NGROUPEXCHGRATE);
  }

  public UFDouble getNgroupmny() {
    return (UFDouble) this.getAttributeValue(PreOrderBVO.NGROUPMNY);
  }

  public UFDouble getNgrouptaxmny() {
    return (UFDouble) this.getAttributeValue(PreOrderBVO.NGROUPTAXMNY);
  }

  public UFDouble getNitemdiscountrate() {
    return (UFDouble) this.getAttributeValue(PreOrderBVO.NITEMDISCOUNTRATE);
  }

  public UFDouble getNmny() {
    return (UFDouble) this.getAttributeValue(PreOrderBVO.NMNY);
  }

  public UFDouble getNnetprice() {
    return (UFDouble) this.getAttributeValue(PreOrderBVO.NNETPRICE);
  }

  public UFDouble getNnum() {
    return (UFDouble) this.getAttributeValue(PreOrderBVO.NNUM);
  }

  public UFDouble getNorigdiscount() {
    return (UFDouble) this.getAttributeValue(PreOrderBVO.NORIGDISCOUNT);
  }

  public UFDouble getNorigmny() {
    return (UFDouble) this.getAttributeValue(PreOrderBVO.NORIGMNY);
  }

  public UFDouble getNorignetprice() {
    return (UFDouble) this.getAttributeValue(PreOrderBVO.NORIGNETPRICE);
  }

  public UFDouble getNorigprice() {
    return (UFDouble) this.getAttributeValue(PreOrderBVO.NORIGPRICE);
  }

  // TODO 刘景 2012-2-17 删除原币税额字段
  /*public UFDouble getNorigtax() {
    return (UFDouble) this.getAttributeValue(PreOrderBVO.NORIGTAX);
  }*/

  public UFDouble getNorigtaxmny() {
    return (UFDouble) this.getAttributeValue(PreOrderBVO.NORIGTAXMNY);
  }

  public UFDouble getNorigtaxnetprice() {
    return (UFDouble) this.getAttributeValue(PreOrderBVO.NORIGTAXNETPRICE);
  }

  public UFDouble getNorigtaxprice() {
    return (UFDouble) this.getAttributeValue(PreOrderBVO.NORIGTAXPRICE);
  }

  public UFDouble getNprice() {
    return (UFDouble) this.getAttributeValue(PreOrderBVO.NPRICE);
  }

  public UFDouble getNqtnetprice() {
    return (UFDouble) this.getAttributeValue(PreOrderBVO.NQTNETPRICE);
  }

  public UFDouble getNqtorignetprice() {
    return (UFDouble) this.getAttributeValue(PreOrderBVO.NQTORIGNETPRICE);
  }

  public UFDouble getNqtorigprice() {
    return (UFDouble) this.getAttributeValue(PreOrderBVO.NQTORIGPRICE);
  }

  public UFDouble getNqtorigtaxnetprc() {
    return (UFDouble) this.getAttributeValue(PreOrderBVO.NQTORIGTAXNETPRC);
  }

  public UFDouble getNqtorigtaxprice() {
    return (UFDouble) this.getAttributeValue(PreOrderBVO.NQTORIGTAXPRICE);
  }

  public UFDouble getNqtprice() {
    return (UFDouble) this.getAttributeValue(PreOrderBVO.NQTPRICE);
  }

  public UFDouble getNqttaxnetprice() {
    return (UFDouble) this.getAttributeValue(PreOrderBVO.NQTTAXNETPRICE);
  }

  public UFDouble getNqttaxprice() {
    return (UFDouble) this.getAttributeValue(PreOrderBVO.NQTTAXPRICE);
  }

  public UFDouble getNqtunitnum() {
    return (UFDouble) this.getAttributeValue(PreOrderBVO.NQTUNITNUM);
  }

  public UFDouble getNtax() {
    return (UFDouble) this.getAttributeValue(PreOrderBVO.NTAX);
  }

  public UFDouble getNtaxmny() {
    return (UFDouble) this.getAttributeValue(PreOrderBVO.NTAXMNY);
  }

  public UFDouble getNtaxnetprice() {
    return (UFDouble) this.getAttributeValue(PreOrderBVO.NTAXNETPRICE);
  }

  public UFDouble getNtaxprice() {
    return (UFDouble) this.getAttributeValue(PreOrderBVO.NTAXPRICE);
  }

  public UFDouble getNtaxrate() {
    return (UFDouble) this.getAttributeValue(PreOrderBVO.NTAXRATE);
  }

  public String getPk_group() {
    return (String) this.getAttributeValue(PreOrderBVO.PK_GROUP);
  }

  public String getPk_org() {
    return (String) this.getAttributeValue(PreOrderBVO.PK_ORG);
  }

  public UFDateTime getTs() {
    return (UFDateTime) this.getAttributeValue(PreOrderBVO.TS);
  }

  public String getVbatchcode() {
    return (String) this.getAttributeValue(PreOrderBVO.VBATCHCODE);
  }

  public String getPk_batchcode() {
    return (String) this.getAttributeValue(PreOrderBVO.PK_BATCHCODE);
  }

  public String getVbdef1() {
    return (String) this.getAttributeValue(PreOrderBVO.VBDEF1);
  }

  public String getVbdef10() {
    return (String) this.getAttributeValue(PreOrderBVO.VBDEF10);
  }

  public String getVbdef11() {
    return (String) this.getAttributeValue(PreOrderBVO.VBDEF11);
  }

  public String getVbdef12() {
    return (String) this.getAttributeValue(PreOrderBVO.VBDEF12);
  }

  public String getVbdef13() {
    return (String) this.getAttributeValue(PreOrderBVO.VBDEF13);
  }

  public String getVbdef14() {
    return (String) this.getAttributeValue(PreOrderBVO.VBDEF14);
  }

  public String getVbdef15() {
    return (String) this.getAttributeValue(PreOrderBVO.VBDEF15);
  }

  public String getVbdef16() {
    return (String) this.getAttributeValue(PreOrderBVO.VBDEF16);
  }

  public String getVbdef17() {
    return (String) this.getAttributeValue(PreOrderBVO.VBDEF17);
  }

  public String getVbdef18() {
    return (String) this.getAttributeValue(PreOrderBVO.VBDEF18);
  }

  public String getVbdef19() {
    return (String) this.getAttributeValue(PreOrderBVO.VBDEF19);
  }

  public String getVbdef2() {
    return (String) this.getAttributeValue(PreOrderBVO.VBDEF2);
  }

  public String getVbdef20() {
    return (String) this.getAttributeValue(PreOrderBVO.VBDEF20);
  }

  public String getVbdef3() {
    return (String) this.getAttributeValue(PreOrderBVO.VBDEF3);
  }

  public String getVbdef4() {
    return (String) this.getAttributeValue(PreOrderBVO.VBDEF4);
  }

  public String getVbdef5() {
    return (String) this.getAttributeValue(PreOrderBVO.VBDEF5);
  }

  public String getVbdef6() {
    return (String) this.getAttributeValue(PreOrderBVO.VBDEF6);
  }

  public String getVbdef7() {
    return (String) this.getAttributeValue(PreOrderBVO.VBDEF7);
  }

  public String getVbdef8() {
    return (String) this.getAttributeValue(PreOrderBVO.VBDEF8);
  }

  public String getVbdef9() {
    return (String) this.getAttributeValue(PreOrderBVO.VBDEF9);
  }

  public String getVchangerate() {
    return (String) this.getAttributeValue(PreOrderBVO.VCHANGERATE);
  }

  public String getVfree1() {
    return (String) this.getAttributeValue(PreOrderBVO.VFREE1);
  }

  public String getVfree10() {
    return (String) this.getAttributeValue(PreOrderBVO.VFREE10);
  }

  public String getVfree2() {
    return (String) this.getAttributeValue(PreOrderBVO.VFREE2);
  }

  public String getVfree3() {
    return (String) this.getAttributeValue(PreOrderBVO.VFREE3);
  }

  public String getVfree4() {
    return (String) this.getAttributeValue(PreOrderBVO.VFREE4);
  }

  public String getVfree5() {
    return (String) this.getAttributeValue(PreOrderBVO.VFREE5);
  }

  public String getVfree6() {
    return (String) this.getAttributeValue(PreOrderBVO.VFREE6);
  }

  public String getVfree7() {
    return (String) this.getAttributeValue(PreOrderBVO.VFREE7);
  }

  public String getVfree8() {
    return (String) this.getAttributeValue(PreOrderBVO.VFREE8);
  }

  public String getVfree9() {
    return (String) this.getAttributeValue(PreOrderBVO.VFREE9);
  }

  public String getVqtunitrate() {
    return (String) this.getAttributeValue(PreOrderBVO.VQTUNITRATE);
  }

  public String getVrownote() {
    return (String) this.getAttributeValue(PreOrderBVO.VROWNOTE);
  }

  public void setBlargessflag(UFBoolean blargessflag) {
    this.setAttributeValue(PreOrderBVO.BLARGESSFLAG, blargessflag);
  }

  public void setBlineclose(UFBoolean blineclose) {
    this.setAttributeValue(PreOrderBVO.BLINECLOSE, blineclose);
  }

  /**
   * 设置 三角贸易
   * 
   * @param btriatradeflag 三角贸易
   */
  public void setBtriatradeflag(UFBoolean btriatradeflag) {
    this.setAttributeValue(PreOrderBVO.BTRIATRADEFLAG, btriatradeflag);
  }

  public void setCarorgid(String carorgid) {
    this.setAttributeValue(PreOrderBVO.CARORGID, carorgid);
  }

  public void setCarorgvid(String carorgvid) {
    this.setAttributeValue(PreOrderBVO.CARORGVID, carorgvid);
  }

  public void setCarrangeid(String carrangeid) {
    this.setAttributeValue(PreOrderBVO.CARRANGEID, carrangeid);
  }

  public void setCastunitid(String castunitid) {
    this.setAttributeValue(PreOrderBVO.CASTUNITID, castunitid);
  }

  public void setCcurrencyid(String ccurrencyid) {
    this.setAttributeValue(PreOrderBVO.CCURRENCYID, ccurrencyid);
  }

  public void setCmaterialid(String cmaterialid) {
    this.setAttributeValue(PreOrderBVO.CMATERIALID, cmaterialid);
  }

  public void setCmaterialvid(String cmaterialvid) {
    this.setAttributeValue(PreOrderBVO.CMATERIALVID, cmaterialvid);
  }

  public void setCorigcurrencyid(String corigcurrencyid) {
    this.setAttributeValue(PreOrderBVO.CORIGCURRENCYID, corigcurrencyid);
  }

  public void setCpreorderbid(String cpreorderbid) {
    this.setAttributeValue(PreOrderBVO.CPREORDERBID, cpreorderbid);
  }

  public void setCpreorderid(String cpreorderid) {
    this.setAttributeValue(PreOrderBVO.CPREORDERID, cpreorderid);
  }

  public void setCpriceformid(String cpriceformid) {
    this.setAttributeValue(PreOrderBVO.CPRICEFORMID, cpriceformid);
  }

  public void setCpriceitemid(String cpriceitemid) {
    this.setAttributeValue(PreOrderBVO.CPRICEITEMID, cpriceitemid);
  }

  public void setCpriceitemtableid(String cpriceitemtableid) {
    this.setAttributeValue(PreOrderBVO.CPRICEITEMTABLEID, cpriceitemtableid);
  }

  public void setCpricepolicyid(String cpricepolicyid) {
    this.setAttributeValue(PreOrderBVO.CPRICEPOLICYID, cpricepolicyid);
  }

  public void setCproductorid(String cproductorid) {
    this.setAttributeValue(PreOrderBVO.CPRODUCTORID, cproductorid);
  }

  public void setCprofitcenterid(String cprofitcenterid) {
    this.setAttributeValue(PreOrderBVO.CPROFITCENTERID, cprofitcenterid);
  }

  public void setCprofitcentervid(String cprofitcentervid) {
    this.setAttributeValue(PreOrderBVO.CPROFITCENTERVID, cprofitcentervid);
  }

  public void setCprojectid(String cprojectid) {
    this.setAttributeValue(PreOrderBVO.CPROJECTID, cprojectid);
  }

  public void setCqtunitid(String cqtunitid) {
    this.setAttributeValue(PreOrderBVO.CQTUNITID, cqtunitid);
  }

  public void setCqualitylevelid(String cqualitylevelid) {
    this.setAttributeValue(PreOrderBVO.CQUALITYLEVELID, cqualitylevelid);
  }

  /**
   * 设置收货国家/地区
   * 
   * @param crececountryid 收货国家/地区
   */
  public void setCrececountryid(String crececountryid) {
    this.setAttributeValue(PreOrderBVO.CRECECOUNTRYID, crececountryid);
  }

  public void setCreceiveaddrid(String creceiveaddrid) {
    this.setAttributeValue(PreOrderBVO.CRECEIVEADDRID, creceiveaddrid);
  }

  public void setCreceiveareaid(String creceiveareaid) {
    this.setAttributeValue(PreOrderBVO.CRECEIVEAREAID, creceiveareaid);
  }

  public void setCreceivecustid(String creceivecustid) {
    this.setAttributeValue(PreOrderBVO.CRECEIVECUSTID, creceivecustid);
  }

  public void setCreceivesiteid(String creceivesiteid) {
    this.setAttributeValue(PreOrderBVO.CRECEIVESITEID, creceivesiteid);
  }

  public void setCrowno(String crowno) {
    this.setAttributeValue(PreOrderBVO.CROWNO, crowno);
  }

  /**
   * 设置发货国家/地区
   * 
   * @param csendcountryid 发货国家/地区
   */
  public void setCsendcountryid(String csendcountryid) {
    this.setAttributeValue(PreOrderBVO.CSENDCOUNTRYID, csendcountryid);
  }

  public void setCsendstockorgid(String csendstockorgid) {
    this.setAttributeValue(PreOrderBVO.CSENDSTOCKORGID, csendstockorgid);
  }

  public void setCsendstockorgvid(String csendstockorgvid) {
    this.setAttributeValue(PreOrderBVO.CSENDSTOCKORGVID, csendstockorgvid);
  }

  public void setCsendstordocid(String csendstordocid) {
    this.setAttributeValue(PreOrderBVO.CSENDSTORDOCID, csendstordocid);
  }

  public void setCsettleorgid(String csettleorgid) {
    this.setAttributeValue(PreOrderBVO.CSETTLEORGID, csettleorgid);
  }

  public void setCsettleorgvid(String csettleorgvid) {
    this.setAttributeValue(PreOrderBVO.CSETTLEORGVID, csettleorgvid);
  }

  /**
   * 设置税码
   * 
   * @param ctaxcodeid 税码
   */
  public void setCtaxcodeid(String ctaxcodeid) {
    this.setAttributeValue(PreOrderBVO.CTAXCODEID, ctaxcodeid);
  }

  /**
   * 设置报税国家/地区
   * 
   * @param ctaxcountryid 报税国家/地区
   */
  public void setCtaxcountryid(String ctaxcountryid) {
    this.setAttributeValue(PreOrderBVO.CTAXCOUNTRYID, ctaxcountryid);
  }

  public void setCtrafficorgid(String ctrafficorgid) {
    this.setAttributeValue(PreOrderBVO.CTRAFFICORGID, ctrafficorgid);
  }

  public void setCtrafficorgvid(String ctrafficorgvid) {
    this.setAttributeValue(PreOrderBVO.CTRAFFICORGVID, ctrafficorgvid);
  }

  public void setCunitid(String cunitid) {
    this.setAttributeValue(PreOrderBVO.CUNITID, cunitid);
  }

  public void setCvendorid(String cvendorid) {
    this.setAttributeValue(PreOrderBVO.CVENDORID, cvendorid);
  }

  public void setDarrdate(UFDate darrdate) {
    this.setAttributeValue(PreOrderBVO.DARRDATE, darrdate);
  }

  public void setDbilldate(UFDate dbilldate) {
    this.setAttributeValue(PreOrderBVO.DBILLDATE, dbilldate);
  }

  public void setDreceivedate(UFDate dreceivedate) {
    this.setAttributeValue(PreOrderBVO.DRECEIVEDATE, dreceivedate);
  }

  public void setDsenddate(UFDate dsenddate) {
    this.setAttributeValue(PreOrderBVO.DSENDDATE, dsenddate);
  }

  /**
   * 设置购销类型
   * 
   * @param fbuysellflag 购销类型
   */
  public void setFbuysellflag(Integer fbuysellflag) {
    this.setAttributeValue(PreOrderBVO.FBUYSELLFLAG, fbuysellflag);
  }

  public void setFrowstatus(String frowstatus) {
    this.setAttributeValue(PreOrderBVO.FROWSTATUS, frowstatus);
  }

  /**
   * 设置扣税类别
   * 
   * @param ftaxtypeflag 扣税类别
   */
  public void setFtaxtypeflag(Integer ftaxtypeflag) {
    this.setAttributeValue(PreOrderBVO.FTAXTYPEFLAG, ftaxtypeflag);
  }

  public void setNarrnum(UFDouble narrnum) {
    this.setAttributeValue(PreOrderBVO.NARRNUM, narrnum);
  }

  public void setNaskqtorignetprice(UFDouble naskqtorignetprice) {
    this.setAttributeValue(PreOrderBVO.NASKQTORIGNETPRICE, naskqtorignetprice);
  }

  public void setNaskqtorigprice(UFDouble naskqtorigprice) {
    this.setAttributeValue(PreOrderBVO.NASKQTORIGPRICE, naskqtorigprice);
  }

  public void setNaskqtorigtaxprc(UFDouble naskqtorigtaxprc) {
    this.setAttributeValue(PreOrderBVO.NASKQTORIGTAXPRC, naskqtorigtaxprc);
  }

  public void setNaskqtorigtxntprc(UFDouble naskqtorigtxntprc) {
    this.setAttributeValue(PreOrderBVO.NASKQTORIGTXNTPRC, naskqtorigtxntprc);
  }

  public void setNastnum(UFDouble nastnum) {
    this.setAttributeValue(PreOrderBVO.NASTNUM, nastnum);
  }

  /**
   * 设置计税金额
   * 
   * @param ncaltaxmny 计税金额
   */
  public void setNcaltaxmny(UFDouble ncaltaxmny) {
    this.setAttributeValue(PreOrderBVO.NCALTAXMNY, ncaltaxmny);
  }

  public void setNdiscount(UFDouble ndiscount) {
    this.setAttributeValue(PreOrderBVO.NDISCOUNT, ndiscount);
  }

  public void setNdiscountrate(UFDouble ndiscountrate) {
    this.setAttributeValue(PreOrderBVO.NDISCOUNTRATE, ndiscountrate);
  }

  public void setNexchangerate(UFDouble nexchangerate) {
    this.setAttributeValue(PreOrderBVO.NEXCHANGERATE, nexchangerate);
  }

  public void setNglobalexchgrate(UFDouble nglobalexchgrate) {
    this.setAttributeValue(PreOrderBVO.NGLOBALEXCHGRATE, nglobalexchgrate);
  }

  public void setNglobalmny(UFDouble nglobalmny) {
    this.setAttributeValue(PreOrderBVO.NGLOBALMNY, nglobalmny);
  }

  public void setNglobaltaxmny(UFDouble nglobaltaxmny) {
    this.setAttributeValue(PreOrderBVO.NGLOBALTAXMNY, nglobaltaxmny);
  }

  public void setNgroupexchgrate(UFDouble ngroupexchgrate) {
    this.setAttributeValue(PreOrderBVO.NGROUPEXCHGRATE, ngroupexchgrate);
  }

  public void setNgroupmny(UFDouble ngroupmny) {
    this.setAttributeValue(PreOrderBVO.NGROUPMNY, ngroupmny);
  }

  public void setNgrouptaxmny(UFDouble ngrouptaxmny) {
    this.setAttributeValue(PreOrderBVO.NGROUPTAXMNY, ngrouptaxmny);
  }

  public void setNitemdiscountrate(UFDouble nitemdiscountrate) {
    this.setAttributeValue(PreOrderBVO.NITEMDISCOUNTRATE, nitemdiscountrate);
  }

  public void setNmny(UFDouble nmny) {
    this.setAttributeValue(PreOrderBVO.NMNY, nmny);
  }

  public void setNnetprice(UFDouble nnetprice) {
    this.setAttributeValue(PreOrderBVO.NNETPRICE, nnetprice);
  }

  public void setNnum(UFDouble nnum) {
    this.setAttributeValue(PreOrderBVO.NNUM, nnum);
  }

  public void setNorigdiscount(UFDouble norigdiscount) {
    this.setAttributeValue(PreOrderBVO.NORIGDISCOUNT, norigdiscount);
  }

  public void setNorigmny(UFDouble norigmny) {
    this.setAttributeValue(PreOrderBVO.NORIGMNY, norigmny);
  }

  public void setNorignetprice(UFDouble norignetprice) {
    this.setAttributeValue(PreOrderBVO.NORIGNETPRICE, norignetprice);
  }

  public void setNorigprice(UFDouble norigprice) {
    this.setAttributeValue(PreOrderBVO.NORIGPRICE, norigprice);
  }

  // TODO 刘景 2012-2-17 删除原币税额字段
  /*public void setNorigtax(UFDouble norigtax) {
    this.setAttributeValue(PreOrderBVO.NORIGTAX, norigtax);
  }*/

  public void setNorigtaxmny(UFDouble norigtaxmny) {
    this.setAttributeValue(PreOrderBVO.NORIGTAXMNY, norigtaxmny);
  }

  public void setNorigtaxnetprice(UFDouble norigtaxnetprice) {
    this.setAttributeValue(PreOrderBVO.NORIGTAXNETPRICE, norigtaxnetprice);
  }

  public void setNorigtaxprice(UFDouble norigtaxprice) {
    this.setAttributeValue(PreOrderBVO.NORIGTAXPRICE, norigtaxprice);
  }

  public void setNprice(UFDouble nprice) {
    this.setAttributeValue(PreOrderBVO.NPRICE, nprice);
  }

  public void setNqtnetprice(UFDouble nqtnetprice) {
    this.setAttributeValue(PreOrderBVO.NQTNETPRICE, nqtnetprice);
  }

  public void setNqtorignetprice(UFDouble nqtorignetprice) {
    this.setAttributeValue(PreOrderBVO.NQTORIGNETPRICE, nqtorignetprice);
  }

  public void setNqtorigprice(UFDouble nqtorigprice) {
    this.setAttributeValue(PreOrderBVO.NQTORIGPRICE, nqtorigprice);
  }

  public void setNqtorigtaxnetprc(UFDouble nqtorigtaxnetprc) {
    this.setAttributeValue(PreOrderBVO.NQTORIGTAXNETPRC, nqtorigtaxnetprc);
  }

  public void setNqtorigtaxprice(UFDouble nqtorigtaxprice) {
    this.setAttributeValue(PreOrderBVO.NQTORIGTAXPRICE, nqtorigtaxprice);
  }

  public void setNqtprice(UFDouble nqtprice) {
    this.setAttributeValue(PreOrderBVO.NQTPRICE, nqtprice);
  }

  public void setNqttaxnetprice(UFDouble nqttaxnetprice) {
    this.setAttributeValue(PreOrderBVO.NQTTAXNETPRICE, nqttaxnetprice);
  }

  public void setNqttaxprice(UFDouble nqttaxprice) {
    this.setAttributeValue(PreOrderBVO.NQTTAXPRICE, nqttaxprice);
  }

  public void setNqtunitnum(UFDouble nqtunitnum) {
    this.setAttributeValue(PreOrderBVO.NQTUNITNUM, nqtunitnum);
  }

  public void setNtax(UFDouble ntax) {
    this.setAttributeValue(PreOrderBVO.NTAX, ntax);
  }

  public void setNtaxmny(UFDouble ntaxmny) {
    this.setAttributeValue(PreOrderBVO.NTAXMNY, ntaxmny);
  }

  public void setNtaxnetprice(UFDouble ntaxnetprice) {
    this.setAttributeValue(PreOrderBVO.NTAXNETPRICE, ntaxnetprice);
  }

  public void setNtaxprice(UFDouble ntaxprice) {
    this.setAttributeValue(PreOrderBVO.NTAXPRICE, ntaxprice);
  }

  public void setNtaxrate(UFDouble ntaxrate) {
    this.setAttributeValue(PreOrderBVO.NTAXRATE, ntaxrate);
  }

  public void setPk_group(String pk_group) {
    this.setAttributeValue(PreOrderBVO.PK_GROUP, pk_group);
  }

  public void setPk_org(String pk_org) {
    this.setAttributeValue(PreOrderBVO.PK_ORG, pk_org);
  }

  public void setTs(UFDateTime ts) {
    this.setAttributeValue(PreOrderBVO.TS, ts);
  }

  public void setVbatchcode(String vbatchcode) {
    this.setAttributeValue(PreOrderBVO.VBATCHCODE, vbatchcode);
  }

  public void setPk_batchcode(String pk_batchcode) {
    this.setAttributeValue(PreOrderBVO.PK_BATCHCODE, pk_batchcode);
  }

  public void setVbdef1(String vbdef1) {
    this.setAttributeValue(PreOrderBVO.VBDEF1, vbdef1);
  }

  public void setVbdef10(String vbdef10) {
    this.setAttributeValue(PreOrderBVO.VBDEF10, vbdef10);
  }

  public void setVbdef11(String vbdef11) {
    this.setAttributeValue(PreOrderBVO.VBDEF11, vbdef11);
  }

  public void setVbdef12(String vbdef12) {
    this.setAttributeValue(PreOrderBVO.VBDEF12, vbdef12);
  }

  public void setVbdef13(String vbdef13) {
    this.setAttributeValue(PreOrderBVO.VBDEF13, vbdef13);
  }

  public void setVbdef14(String vbdef14) {
    this.setAttributeValue(PreOrderBVO.VBDEF14, vbdef14);
  }

  public void setVbdef15(String vbdef15) {
    this.setAttributeValue(PreOrderBVO.VBDEF15, vbdef15);
  }

  public void setVbdef16(String vbdef16) {
    this.setAttributeValue(PreOrderBVO.VBDEF16, vbdef16);
  }

  public void setVbdef17(String vbdef17) {
    this.setAttributeValue(PreOrderBVO.VBDEF17, vbdef17);
  }

  public void setVbdef18(String vbdef18) {
    this.setAttributeValue(PreOrderBVO.VBDEF18, vbdef18);
  }

  public void setVbdef19(String vbdef19) {
    this.setAttributeValue(PreOrderBVO.VBDEF19, vbdef19);
  }

  public void setVbdef2(String vbdef2) {
    this.setAttributeValue(PreOrderBVO.VBDEF2, vbdef2);
  }

  public void setVbdef20(String vbdef20) {
    this.setAttributeValue(PreOrderBVO.VBDEF20, vbdef20);
  }

  public void setVbdef3(String vbdef3) {
    this.setAttributeValue(PreOrderBVO.VBDEF3, vbdef3);
  }

  public void setVbdef4(String vbdef4) {
    this.setAttributeValue(PreOrderBVO.VBDEF4, vbdef4);
  }

  public void setVbdef5(String vbdef5) {
    this.setAttributeValue(PreOrderBVO.VBDEF5, vbdef5);
  }

  public void setVbdef6(String vbdef6) {
    this.setAttributeValue(PreOrderBVO.VBDEF6, vbdef6);
  }

  public void setVbdef7(String vbdef7) {
    this.setAttributeValue(PreOrderBVO.VBDEF7, vbdef7);
  }

  public void setVbdef8(String vbdef8) {
    this.setAttributeValue(PreOrderBVO.VBDEF8, vbdef8);
  }

  public void setVbdef9(String vbdef9) {
    this.setAttributeValue(PreOrderBVO.VBDEF9, vbdef9);
  }

  public void setVchangerate(String vchangerate) {
    this.setAttributeValue(PreOrderBVO.VCHANGERATE, vchangerate);
  }

  public void setVfree1(String vfree1) {
    this.setAttributeValue(PreOrderBVO.VFREE1, vfree1);
  }

  public void setVfree10(String vfree10) {
    this.setAttributeValue(PreOrderBVO.VFREE10, vfree10);
  }

  public void setVfree2(String vfree2) {
    this.setAttributeValue(PreOrderBVO.VFREE2, vfree2);
  }

  public void setVfree3(String vfree3) {
    this.setAttributeValue(PreOrderBVO.VFREE3, vfree3);
  }

  public void setVfree4(String vfree4) {
    this.setAttributeValue(PreOrderBVO.VFREE4, vfree4);
  }

  public void setVfree5(String vfree5) {
    this.setAttributeValue(PreOrderBVO.VFREE5, vfree5);
  }

  public void setVfree6(String vfree6) {
    this.setAttributeValue(PreOrderBVO.VFREE6, vfree6);
  }

  public void setVfree7(String vfree7) {
    this.setAttributeValue(PreOrderBVO.VFREE7, vfree7);
  }

  public void setVfree8(String vfree8) {
    this.setAttributeValue(PreOrderBVO.VFREE8, vfree8);
  }

  public void setVfree9(String vfree9) {
    this.setAttributeValue(PreOrderBVO.VFREE9, vfree9);
  }

  public void setVqtunitrate(String vqtunitrate) {
    this.setAttributeValue(PreOrderBVO.VQTUNITRATE, vqtunitrate);
  }

  public void setVrownote(String vrownote) {
    this.setAttributeValue(PreOrderBVO.VROWNOTE, vrownote);
  }

}
