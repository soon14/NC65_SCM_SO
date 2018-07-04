package nc.vo.so.m33.m32.entity;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;
import nc.vo.so.m33.enumeration.SquareType;

/**
 * 销售发票待结算清单子实体类
 * 
 * @since 6.0
 * @version 2012-1-5 上午08:50:38
 * @author fengjb
 */
public class SquareInvBVO extends SuperVO {

  private static final long serialVersionUID = -7719302456054625866L;
  
  /**
   * 特征码
   */
  public static final String CMFFILEID="cmffileid";

  /**
   * 差额传应收结算数量，不可持久化。（差额传应收时，数量和单价会清空，导致销售发票结算不关闭)
   */
  private static final String DIFFTOARSQUARENUM = "difftoarsquarenum";

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
    this.setAttributeValue(SquareInvBVO.CCUSTMATERIALID, ccustmaterialid);
  }

  /**
   * 获取客户物料编码
   * 
   * @return 客户物料编码
   */
  public String getCcustmaterialid() {
    return (String) this.getAttributeValue(SquareInvBVO.CCUSTMATERIALID);
  }

  /**
   * 销售发票待结算清单子表实体路径
   */
  public static final String ENTITYNAME = "so.SquareInvoiceBVO";

  /**
   * 删除标志dr
   * 
   */
  public static final String DR = "dr";

  /**
   * 是否可以成本结算
   */
  public static final String BCOST = "bcost";

  /**
   * 折扣类
   */
  public static final String BDISCOUNTFLAG = "bdiscountflag";

  /**
   * 是否可以收入结算
   */
  public static final String BINCOME = "bincome";

  /**
   * 服务类
   */
  public static final String BLABORFLAG = "blaborflag";

  /**
   * 是否赠品
   */
  public static final String BLARGESSFLAG = "blargessflag";

  /**
   * 是否应收结算完成
   */
  public static final String BSQUAREARFINISH = "bsquarearfinish";

  /**
   * 是否成本结算完成
   */
  public static final String BSQUAREIAFINISH = "bsquareiafinish";

  /**
   * 应收组织最新版本
   */
  public static final String CARORGID = "carorgid";

  /**
   * 应收组织版本
   */
  public static final String CARORGVID = "carorgvid";

  /**
   * 单位
   */
  public static final String CASTUNITID = "castunitid";

  /**
   * 订单渠道类型
   */
  public static final String CCHANNELTYPEID = "cchanneltypeid";

  /**
   * 成本域
   */
  public static final String CCOSTORGID = "ccostorgid";

  /**
   * 本币
   */
  public static final String CCURRENCYID = "ccurrencyid";

  /**
   * 销售部门最新版本
   */
  public static final String CDEPTID = "cdeptid";

  /**
   * 销售部门版本
   */
  public static final String CDEPTVID = "cdeptvid";

  /**
   * 销售业务员
   */
  public static final String CEMPLOYEEID = "cemployeeid";

  /**
   * 源头单据子表
   */
  public static final String CFIRSTBID = "cfirstbid";

  /**
   * 源头单据主表
   */
  public static final String CFIRSTID = "cfirstid";

  /**
   * 散户
   */
  public static final String CFREECUSTID = "cfreecustid";

  /**
   * 物料
   */
  public static final String CMATERIALID = "cmaterialid";

  /**
   * 物料版本
   */
  public static final String CMATERIALVID = "cmaterialvid";

  /**
   * 订单客户
   */
  public static final String CORDERCUSTID = "cordercustid";

  /**
   * 原币
   */
  public static final String CORIGCURRENCYID = "corigcurrencyid";

  /**
   * 生产厂商
   */
  public static final String CPRODUCTORID = "cproductorid";

  /**
   * 利润中心最新版本
   */
  public static final String CPROFITCENTERID = "cprofitcenterid";

  /**
   * 利润中心版本
   */
  public static final String CPROFITCENTERVID = "cprofitcentervid";

  /**
   * 项目ID
   */
  public static final String CPROJECTID = "cprojectid";

  /**
   * 产品线
   */
  public static final String CPROLINEID = "cprolineid";

  /**
   * 销售组织最新版本
   */
  public static final String CSALEORGID = "csaleorgid";

  /**
   * 销售组织版本
   */
  public static final String CSALEORGVID = "csaleorgvid";

  /**
   * 销售发票结算单子实体
   */
  public static final String CSALESQUAREBID = "csalesquarebid";

  /**
   * csalesquaredid（可持续化字段，可以远程传递值，但不能保存到数据库）
   */
  public static final String CSALESQUAREDID = "csalesquaredid";

  /**
   * 销售发票待结算单主实体_主键
   */
  public static final String CSALESQUAREID = "csalesquareid";

  /**
   * 库存组织最新版本
   */
  public static final String CSENDSTOCKORGID = "csendstockorgid";

  /**
   * 库存组织版本
   */
  public static final String CSENDSTOCKORGVID = "csendstockorgvid";

  /**
   * 仓库
   */
  public static final String CSENDSTORDOCID = "csendstordocid";

  /**
   * 销售发票子实体
   */
  public static final String CSQUAREBILLBID = "csquarebillbid";

  /**
   * 销售发票主实体
   */
  public static final String CSQUAREBILLID = "csquarebillid";

  /**
   * 来源单据子表
   */
  public static final String CSRCBID = "csrcbid";

  /**
   * 来源单据主表
   */
  public static final String CSRCID = "csrcid";

  /**
   * 主单位
   */
  public static final String CUNITID = "cunitid";

  /**
   * 供应商
   */
  public static final String CVENDORID = "cvendorid";

  /**
   * 销售发票单据日期
   */
  public static final String DBILLDATE = "dbilldate";

  /**
   * 应收单起效效日期
   */
  public static final String DEFFECTDATE = "deffectdate";

  /**
   * 待收入结算类型
   */
  public static final String FPREARTYPE = "fpreartype";

  /**
   * 待成本结算类型
   */
  public static final String FPREIATYPE = "fpreiatype";

  /**
   * 累计回冲应收数量
   */
  public static final String NARRUSHNUM = "narrushnum";

  /**
   * 单位数量
   */
  public static final String NASTNUM = "nastnum";

  /**
   * 本币折扣额
   */
  public static final String NDISCOUNT = "ndiscount";

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
   * 单品折扣率
   */
  public static final String NITEMDISCOUNTRATE = "nitemdiscountrate";

  /**
   * 本币无税金额
   */
  public static final String NMNY = "nmny";

  /**
   * 本币无税净价
   */
  public static final String NNETPRICE = "nnetprice";

  /**
   * 主单位数量
   */
  public static final String NNUM = "nnum";

  /**
   * 原币折扣额
   */
  public static final String NORIGDISCOUNT = "norigdiscount";

  /**
   * 原币无税金额
   */
  public static final String NORIGMNY = "norigmny";

  /**
   * 原币无税净价
   */
  public static final String NORIGNETPRICE = "norignetprice";

  /**
   * 原币无税单价
   */
  public static final String NORIGPRICE = "norigprice";

  /**
   * 原币价税合计
   */
  public static final String NORIGTAXMNY = "norigtaxmny";

  /**
   * 原币含税净价
   */
  public static final String NORIGTAXNETPRICE = "norigtaxnetprice";

  /**
   * 原币含税单价
   */
  public static final String NORIGTAXPRICE = "norigtaxprice";

  /**
   * 本币无税单价
   */
  public static final String NPRICE = "nprice";

  /**
   * 累计确认应收数量
   */
  public static final String NSQUAREARNUM = "nsquarearnum";

  /**
   * 累计成本结算数量
   */
  public static final String NSQUAREIANUM = "nsquareianum";

  /**
   * 累计发出商品数量
   */
  public static final String NSQUAREREGNUM = "nsquareregnum";

  /**
   * 本币税额
   */
  public static final String NTAX = "ntax";

  /**
   * 本币价税合计
   */
  public static final String NTAXMNY = "ntaxmny";

  /**
   * 本币含税净价
   */
  public static final String NTAXNETPRICE = "ntaxnetprice";

  /**
   * 本币含税单价
   */
  public static final String NTAXPRICE = "ntaxprice";

  /**
   * 税率
   */
  public static final String NTAXRATE = "ntaxrate";

  /**
   * nthisnum（可持续化字段，可以远程传递值，但不能保存到数据库）
   */
  public static final String NTHISNUM = "nthisnum";

  /**
   * 批次号档案
   */
  public static final String PK_BATCHCODE = "pk_batchcode";

  /**
   * 集团
   */
  public static final String PK_GROUP = "pk_group";

  /**
   * 结算财务组织
   */
  public static final String PK_ORG = "pk_org";

  /**
   * processeid（可持续化字段，可以远程传递值，但不能保存到数据库）
   */
  public static final String PROCESSEID = "processeid";

  /**
   * 时间戳
   */
  public static final String TS = "ts";

  /**
   * 批次号
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
   * 单位换算率
   */
  public static final String VCHANGERATE = "vchangerate";

  /**
   * 合同号
   */
  public static final String VCTCODE = "vctcode";

  /**
   * 源头单据号
   */
  public static final String VFIRSTCODE = "vfirstcode";

  /**
   * 源头单据行号
   */
  public static final String VFIRSTROWNO = "vfirstrowno";

  /**
   * 源头单据交易类型
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
   * 来源单据交易类型
   */
  public static final String VSRCTRANTYPE = "vsrctrantype";

  /**
   * 来源单据类型
   */
  public static final String VSRCTYPE = "vsrctype";

  /******* V61新增字段 *******/
  /**
   * 税码
   */
  public static final String CTAXCODEID = "ctaxcodeid";

  /**
   * 扣税类别
   */
  public static final String FTAXTYPEFLAG = "ftaxtypeflag";

  /**
   * 计税金额
   */
  public static final String NCALTAXMNY = "ncaltaxmny";

  /**
   * 计税金额
   */
  public static final String CCOSTSUBJID = "ccostsubjid";

  /**
   * 获取是否可以成本结算
   * 
   * @return 是否可以成本结算
   */
  public UFBoolean getBcost() {
    return (UFBoolean) this.getAttributeValue(SquareInvBVO.BCOST);
  }

  /**
   * 设置是否可以成本结算
   * 
   * @param bcost 是否可以成本结算
   */
  public void setBcost(UFBoolean bcost) {
    this.setAttributeValue(SquareInvBVO.BCOST, bcost);
  }

  /**
   * 获取折扣类
   * 
   * @return 折扣类
   */
  public UFBoolean getBdiscountflag() {
    return (UFBoolean) this.getAttributeValue(SquareInvBVO.BDISCOUNTFLAG);
  }

  /**
   * 设置折扣类
   * 
   * @param bdiscountflag 折扣类
   */
  public void setBdiscountflag(UFBoolean bdiscountflag) {
    this.setAttributeValue(SquareInvBVO.BDISCOUNTFLAG, bdiscountflag);
  }

  /**
   * 获取是否可以收入结算
   * 
   * @return 是否可以收入结算
   */
  public UFBoolean getBincome() {
    return (UFBoolean) this.getAttributeValue(SquareInvBVO.BINCOME);
  }

  /**
   * 设置是否可以收入结算
   * 
   * @param bincome 是否可以收入结算
   */
  public void setBincome(UFBoolean bincome) {
    this.setAttributeValue(SquareInvBVO.BINCOME, bincome);
  }

  /**
   * 获取服务类
   * 
   * @return 服务类
   */
  public UFBoolean getBlaborflag() {
    return (UFBoolean) this.getAttributeValue(SquareInvBVO.BLABORFLAG);
  }

  /**
   * 设置服务类
   * 
   * @param blaborflag 服务类
   */
  public void setBlaborflag(UFBoolean blaborflag) {
    this.setAttributeValue(SquareInvBVO.BLABORFLAG, blaborflag);
  }

  /**
   * 获取是否赠品
   * 
   * @return 是否赠品
   */
  public UFBoolean getBlargessflag() {
    return (UFBoolean) this.getAttributeValue(SquareInvBVO.BLARGESSFLAG);
  }

  /**
   * 设置是否赠品
   * 
   * @param blargessflag 是否赠品
   */
  public void setBlargessflag(UFBoolean blargessflag) {
    this.setAttributeValue(SquareInvBVO.BLARGESSFLAG, blargessflag);
  }

  /**
   * 获取是否应收结算完成
   * 
   * @return 是否应收结算完成
   */
  public UFBoolean getBsquarearfinish() {
    return (UFBoolean) this.getAttributeValue(SquareInvBVO.BSQUAREARFINISH);
  }

  /**
   * 设置是否应收结算完成
   * 
   * @param bsquarearfinish 是否应收结算完成
   */
  public void setBsquarearfinish(UFBoolean bsquarearfinish) {
    this.setAttributeValue(SquareInvBVO.BSQUAREARFINISH, bsquarearfinish);
  }

  /**
   * 获取是否成本结算完成
   * 
   * @return 是否成本结算完成
   */
  public UFBoolean getBsquareiafinish() {
    return (UFBoolean) this.getAttributeValue(SquareInvBVO.BSQUAREIAFINISH);
  }

  /**
   * 设置是否成本结算完成
   * 
   * @param bsquareiafinish 是否成本结算完成
   */
  public void setBsquareiafinish(UFBoolean bsquareiafinish) {
    this.setAttributeValue(SquareInvBVO.BSQUAREIAFINISH, bsquareiafinish);
  }

  /**
   * 获取应收组织最新版本
   * 
   * @return 应收组织最新版本
   */
  public String getCarorgid() {
    return (String) this.getAttributeValue(SquareInvBVO.CARORGID);
  }

  /**
   * 设置应收组织最新版本
   * 
   * @param carorgid 应收组织最新版本
   */
  public void setCarorgid(String carorgid) {
    this.setAttributeValue(SquareInvBVO.CARORGID, carorgid);
  }

  /**
   * 获取应收组织版本
   * 
   * @return 应收组织版本
   */
  public String getCarorgvid() {
    return (String) this.getAttributeValue(SquareInvBVO.CARORGVID);
  }

  /**
   * 设置应收组织版本
   * 
   * @param carorgvid 应收组织版本
   */
  public void setCarorgvid(String carorgvid) {
    this.setAttributeValue(SquareInvBVO.CARORGVID, carorgvid);
  }

  /**
   * 获取单位
   * 
   * @return 单位
   */
  public String getCastunitid() {
    return (String) this.getAttributeValue(SquareInvBVO.CASTUNITID);
  }

  /**
   * 设置单位
   * 
   * @param castunitid 单位
   */
  public void setCastunitid(String castunitid) {
    this.setAttributeValue(SquareInvBVO.CASTUNITID, castunitid);
  }

  /**
   * 获取订单渠道类型
   * 
   * @return 订单渠道类型
   */
  public String getCchanneltypeid() {
    return (String) this.getAttributeValue(SquareInvBVO.CCHANNELTYPEID);
  }

  /**
   * 设置订单渠道类型
   * 
   * @param cchanneltypeid 订单渠道类型
   */
  public void setCchanneltypeid(String cchanneltypeid) {
    this.setAttributeValue(SquareInvBVO.CCHANNELTYPEID, cchanneltypeid);
  }

  /**
   * 获取成本域
   * 
   * @return 成本域
   */
  public String getCcostorgid() {
    return (String) this.getAttributeValue(SquareInvBVO.CCOSTORGID);
  }

  /**
   * 设置成本域
   * 
   * @param ccostorgid 成本域
   */
  public void setCcostorgid(String ccostorgid) {
    this.setAttributeValue(SquareInvBVO.CCOSTORGID, ccostorgid);
  }

  /**
   * 获取本币
   * 
   * @return 本币
   */
  public String getCcurrencyid() {
    return (String) this.getAttributeValue(SquareInvBVO.CCURRENCYID);
  }

  /**
   * 设置本币
   * 
   * @param ccurrencyid 本币
   */
  public void setCcurrencyid(String ccurrencyid) {
    this.setAttributeValue(SquareInvBVO.CCURRENCYID, ccurrencyid);
  }

  /**
   * 获取销售部门最新版本
   * 
   * @return 销售部门最新版本
   */
  public String getCdeptid() {
    return (String) this.getAttributeValue(SquareInvBVO.CDEPTID);
  }

  /**
   * 设置销售部门最新版本
   * 
   * @param cdeptid 销售部门最新版本
   */
  public void setCdeptid(String cdeptid) {
    this.setAttributeValue(SquareInvBVO.CDEPTID, cdeptid);
  }

  /**
   * 获取销售部门版本
   * 
   * @return 销售部门版本
   */
  public String getCdeptvid() {
    return (String) this.getAttributeValue(SquareInvBVO.CDEPTVID);
  }

  /**
   * 设置销售部门版本
   * 
   * @param cdeptvid 销售部门版本
   */
  public void setCdeptvid(String cdeptvid) {
    this.setAttributeValue(SquareInvBVO.CDEPTVID, cdeptvid);
  }

  /**
   * 获取销售业务员
   * 
   * @return 销售业务员
   */
  public String getCemployeeid() {
    return (String) this.getAttributeValue(SquareInvBVO.CEMPLOYEEID);
  }

  /**
   * 设置销售业务员
   * 
   * @param cemployeeid 销售业务员
   */
  public void setCemployeeid(String cemployeeid) {
    this.setAttributeValue(SquareInvBVO.CEMPLOYEEID, cemployeeid);
  }

  /**
   * 获取源头单据子表
   * 
   * @return 源头单据子表
   */
  public String getCfirstbid() {
    return (String) this.getAttributeValue(SquareInvBVO.CFIRSTBID);
  }

  /**
   * 设置源头单据子表
   * 
   * @param cfirstbid 源头单据子表
   */
  public void setCfirstbid(String cfirstbid) {
    this.setAttributeValue(SquareInvBVO.CFIRSTBID, cfirstbid);
  }

  /**
   * 获取源头单据主表
   * 
   * @return 源头单据主表
   */
  public String getCfirstid() {
    return (String) this.getAttributeValue(SquareInvBVO.CFIRSTID);
  }

  /**
   * 设置源头单据主表
   * 
   * @param cfirstid 源头单据主表
   */
  public void setCfirstid(String cfirstid) {
    this.setAttributeValue(SquareInvBVO.CFIRSTID, cfirstid);
  }

  /**
   * 获取散户
   * 
   * @return 散户
   */
  public String getCfreecustid() {
    return (String) this.getAttributeValue(SquareInvBVO.CFREECUSTID);
  }

  /**
   * 设置散户
   * 
   * @param cfreecustid 散户
   */
  public void setCfreecustid(String cfreecustid) {
    this.setAttributeValue(SquareInvBVO.CFREECUSTID, cfreecustid);
  }

  /**
   * 获取物料
   * 
   * @return 物料
   */
  public String getCmaterialid() {
    return (String) this.getAttributeValue(SquareInvBVO.CMATERIALID);
  }

  /**
   * 设置物料
   * 
   * @param cmaterialid 物料
   */
  public void setCmaterialid(String cmaterialid) {
    this.setAttributeValue(SquareInvBVO.CMATERIALID, cmaterialid);
  }

  /**
   * 获取物料版本
   * 
   * @return 物料版本
   */
  public String getCmaterialvid() {
    return (String) this.getAttributeValue(SquareInvBVO.CMATERIALVID);
  }

  /**
   * 设置物料版本
   * 
   * @param cmaterialvid 物料版本
   */
  public void setCmaterialvid(String cmaterialvid) {
    this.setAttributeValue(SquareInvBVO.CMATERIALVID, cmaterialvid);
  }

  /**
   * 获取订单客户
   * 
   * @return 订单客户
   */
  public String getCordercustid() {
    return (String) this.getAttributeValue(SquareInvBVO.CORDERCUSTID);
  }

  /**
   * 设置订单客户
   * 
   * @param cordercustid 订单客户
   */
  public void setCordercustid(String cordercustid) {
    this.setAttributeValue(SquareInvBVO.CORDERCUSTID, cordercustid);
  }

  /**
   * 获取原币
   * 
   * @return 原币
   */
  public String getCorigcurrencyid() {
    return (String) this.getAttributeValue(SquareInvBVO.CORIGCURRENCYID);
  }

  /**
   * 设置原币
   * 
   * @param corigcurrencyid 原币
   */
  public void setCorigcurrencyid(String corigcurrencyid) {
    this.setAttributeValue(SquareInvBVO.CORIGCURRENCYID, corigcurrencyid);
  }

  /**
   * 获取生产厂商
   * 
   * @return 生产厂商
   */
  public String getCproductorid() {
    return (String) this.getAttributeValue(SquareInvBVO.CPRODUCTORID);
  }

  /**
   * 设置生产厂商
   * 
   * @param cproductorid 生产厂商
   */
  public void setCproductorid(String cproductorid) {
    this.setAttributeValue(SquareInvBVO.CPRODUCTORID, cproductorid);
  }

  /**
   * 获取利润中心最新版本
   * 
   * @return 利润中心最新版本
   */
  public String getCprofitcenterid() {
    return (String) this.getAttributeValue(SquareInvBVO.CPROFITCENTERID);
  }

  /**
   * 设置利润中心最新版本
   * 
   * @param cprofitcenterid 利润中心最新版本
   */
  public void setCprofitcenterid(String cprofitcenterid) {
    this.setAttributeValue(SquareInvBVO.CPROFITCENTERID, cprofitcenterid);
  }

  /**
   * 获取利润中心版本
   * 
   * @return 利润中心版本
   */
  public String getCprofitcentervid() {
    return (String) this.getAttributeValue(SquareInvBVO.CPROFITCENTERVID);
  }

  /**
   * 设置利润中心版本
   * 
   * @param cprofitcentervid 利润中心版本
   */
  public void setCprofitcentervid(String cprofitcentervid) {
    this.setAttributeValue(SquareInvBVO.CPROFITCENTERVID, cprofitcentervid);
  }

  /**
   * 获取项目ID
   * 
   * @return 项目ID
   */
  public String getCprojectid() {
    return (String) this.getAttributeValue(SquareInvBVO.CPROJECTID);
  }

  /**
   * 设置项目ID
   * 
   * @param cprojectid 项目ID
   */
  public void setCprojectid(String cprojectid) {
    this.setAttributeValue(SquareInvBVO.CPROJECTID, cprojectid);
  }

  /**
   * 获取产品线
   * 
   * @return 产品线
   */
  public String getCprolineid() {
    return (String) this.getAttributeValue(SquareInvBVO.CPROLINEID);
  }

  /**
   * 设置产品线
   * 
   * @param cprolineid 产品线
   */
  public void setCprolineid(String cprolineid) {
    this.setAttributeValue(SquareInvBVO.CPROLINEID, cprolineid);
  }

  /**
   * 获取销售组织最新版本
   * 
   * @return 销售组织最新版本
   */
  public String getCsaleorgid() {
    return (String) this.getAttributeValue(SquareInvBVO.CSALEORGID);
  }

  /**
   * 设置销售组织最新版本
   * 
   * @param csaleorgid 销售组织最新版本
   */
  public void setCsaleorgid(String csaleorgid) {
    this.setAttributeValue(SquareInvBVO.CSALEORGID, csaleorgid);
  }

  /**
   * 获取销售组织版本
   * 
   * @return 销售组织版本
   */
  public String getCsaleorgvid() {
    return (String) this.getAttributeValue(SquareInvBVO.CSALEORGVID);
  }

  /**
   * 设置销售组织版本
   * 
   * @param csaleorgvid 销售组织版本
   */
  public void setCsaleorgvid(String csaleorgvid) {
    this.setAttributeValue(SquareInvBVO.CSALEORGVID, csaleorgvid);
  }

  /**
   * 获取销售发票结算单子实体
   * 
   * @return 销售发票结算单子实体
   */
  public String getCsalesquarebid() {
    return (String) this.getAttributeValue(SquareInvBVO.CSALESQUAREBID);
  }

  /**
   * 设置销售发票结算单子实体
   * 
   * @param csalesquarebid 销售发票结算单子实体
   */
  public void setCsalesquarebid(String csalesquarebid) {
    this.setAttributeValue(SquareInvBVO.CSALESQUAREBID, csalesquarebid);
  }

  /**
   * 获取csalesquaredid（可持续化字段，可以远程传递值，但不能保存到数据库）
   * 
   * @return csalesquaredid
   */
  public String getCsalesquaredid() {
    return (String) this.getAttributeValue(SquareInvBVO.CSALESQUAREDID);
  }

  /**
   * 设置csalesquaredid（可持续化字段，可以远程传递值，但不能保存到数据库）
   * 
   * @param csalesquaredid csalesquaredid
   */
  public void setCsalesquaredid(String csalesquaredid) {
    this.setAttributeValue(SquareInvBVO.CSALESQUAREDID, csalesquaredid);
  }

  /**
   * 获取销售发票待结算单主实体_主键
   * 
   * @return 销售发票待结算单主实体_主键
   */
  public String getCsalesquareid() {
    return (String) this.getAttributeValue(SquareInvBVO.CSALESQUAREID);
  }

  /**
   * 设置销售发票待结算单主实体_主键
   * 
   * @param csalesquareid 销售发票待结算单主实体_主键
   */
  public void setCsalesquareid(String csalesquareid) {
    this.setAttributeValue(SquareInvBVO.CSALESQUAREID, csalesquareid);
  }

  /**
   * 获取库存组织最新版本
   * 
   * @return 库存组织最新版本
   */
  public String getCsendstockorgid() {
    return (String) this.getAttributeValue(SquareInvBVO.CSENDSTOCKORGID);
  }

  /**
   * 设置库存组织最新版本
   * 
   * @param csendstockorgid 库存组织最新版本
   */
  public void setCsendstockorgid(String csendstockorgid) {
    this.setAttributeValue(SquareInvBVO.CSENDSTOCKORGID, csendstockorgid);
  }

  /**
   * 获取库存组织版本
   * 
   * @return 库存组织版本
   */
  public String getCsendstockorgvid() {
    return (String) this.getAttributeValue(SquareInvBVO.CSENDSTOCKORGVID);
  }

  /**
   * 设置库存组织版本
   * 
   * @param csendstockorgvid 库存组织版本
   */
  public void setCsendstockorgvid(String csendstockorgvid) {
    this.setAttributeValue(SquareInvBVO.CSENDSTOCKORGVID, csendstockorgvid);
  }

  /**
   * 获取仓库
   * 
   * @return 仓库
   */
  public String getCsendstordocid() {
    return (String) this.getAttributeValue(SquareInvBVO.CSENDSTORDOCID);
  }

  /**
   * 设置仓库
   * 
   * @param csendstordocid 仓库
   */
  public void setCsendstordocid(String csendstordocid) {
    this.setAttributeValue(SquareInvBVO.CSENDSTORDOCID, csendstordocid);
  }

  /**
   * 获取销售发票子实体
   * 
   * @return 销售发票子实体
   */
  public String getCsquarebillbid() {
    return (String) this.getAttributeValue(SquareInvBVO.CSQUAREBILLBID);
  }

  /**
   * 设置销售发票子实体
   * 
   * @param csquarebillbid 销售发票子实体
   */
  public void setCsquarebillbid(String csquarebillbid) {
    this.setAttributeValue(SquareInvBVO.CSQUAREBILLBID, csquarebillbid);
  }

  /**
   * 获取销售发票主实体
   * 
   * @return 销售发票主实体
   */
  public String getCsquarebillid() {
    return (String) this.getAttributeValue(SquareInvBVO.CSQUAREBILLID);
  }

  /**
   * 设置销售发票主实体
   * 
   * @param csquarebillid 销售发票主实体
   */
  public void setCsquarebillid(String csquarebillid) {
    this.setAttributeValue(SquareInvBVO.CSQUAREBILLID, csquarebillid);
  }

  /**
   * 获取来源单据子表
   * 
   * @return 来源单据子表
   */
  public String getCsrcbid() {
    return (String) this.getAttributeValue(SquareInvBVO.CSRCBID);
  }

  /**
   * 设置来源单据子表
   * 
   * @param csrcbid 来源单据子表
   */
  public void setCsrcbid(String csrcbid) {
    this.setAttributeValue(SquareInvBVO.CSRCBID, csrcbid);
  }

  /**
   * 获取来源单据主表
   * 
   * @return 来源单据主表
   */
  public String getCsrcid() {
    return (String) this.getAttributeValue(SquareInvBVO.CSRCID);
  }

  /**
   * 设置来源单据主表
   * 
   * @param csrcid 来源单据主表
   */
  public void setCsrcid(String csrcid) {
    this.setAttributeValue(SquareInvBVO.CSRCID, csrcid);
  }

  /**
   * 获取税码
   * 
   * @return 税码
   */
  public String getCtaxcodeid() {
    return (String) this.getAttributeValue(SquareInvBVO.CTAXCODEID);
  }

  /**
   * 设置税码
   * 
   * @param ctaxcodeid 税码
   */
  public void setCtaxcodeid(String ctaxcodeid) {
    this.setAttributeValue(SquareInvBVO.CTAXCODEID, ctaxcodeid);
  }

  /**
   * 获取主单位
   * 
   * @return 主单位
   */
  public String getCunitid() {
    return (String) this.getAttributeValue(SquareInvBVO.CUNITID);
  }

  /**
   * 设置主单位
   * 
   * @param cunitid 主单位
   */
  public void setCunitid(String cunitid) {
    this.setAttributeValue(SquareInvBVO.CUNITID, cunitid);
  }

  /**
   * 获取供应商
   * 
   * @return 供应商
   */
  public String getCvendorid() {
    return (String) this.getAttributeValue(SquareInvBVO.CVENDORID);
  }

  /**
   * 设置供应商
   * 
   * @param cvendorid 供应商
   */
  public void setCvendorid(String cvendorid) {
    this.setAttributeValue(SquareInvBVO.CVENDORID, cvendorid);
  }

  /**
   * 获取销售发票单据日期
   * 
   * @return 销售发票单据日期
   */
  public UFDate getDbilldate() {
    return (UFDate) this.getAttributeValue(SquareInvBVO.DBILLDATE);
  }

  /**
   * 设置销售发票单据日期
   * 
   * @param dbilldate 销售发票单据日期
   */
  public void setDbilldate(UFDate dbilldate) {
    this.setAttributeValue(SquareInvBVO.DBILLDATE, dbilldate);
  }

  /**
   * 获取应收单起效效日期
   * 
   * @return 应收单起效效日期
   */
  public UFDate getDeffectdate() {
    return (UFDate) this.getAttributeValue(SquareInvBVO.DEFFECTDATE);
  }

  /**
   * 设置应收单起效效日期
   * 
   * @param deffectdate 应收单起效效日期
   */
  public void setDeffectdate(UFDate deffectdate) {
    this.setAttributeValue(SquareInvBVO.DEFFECTDATE, deffectdate);
  }

  /**
   * 获取待收入结算类型
   * 
   * @return 待收入结算类型
   * @see SquareType
   */
  public Integer getFpreartype() {
    return (Integer) this.getAttributeValue(SquareInvBVO.FPREARTYPE);
  }

  /**
   * 设置待收入结算类型
   * 
   * @param fpreartype 待收入结算类型
   * @see SquareType
   */
  public void setFpreartype(Integer fpreartype) {
    this.setAttributeValue(SquareInvBVO.FPREARTYPE, fpreartype);
  }

  /**
   * 获取待成本结算类型
   * 
   * @return 待成本结算类型
   * @see SquareType
   */
  public Integer getFpreiatype() {
    return (Integer) this.getAttributeValue(SquareInvBVO.FPREIATYPE);
  }

  /**
   * 设置待成本结算类型
   * 
   * @param fpreiatype 待成本结算类型
   * @see SquareType
   */
  public void setFpreiatype(Integer fpreiatype) {
    this.setAttributeValue(SquareInvBVO.FPREIATYPE, fpreiatype);
  }

  /**
   * 获取扣税类别
   * 
   * @return 扣税类别
   */
  public Integer getFtaxtypeflag() {
    return (Integer) this.getAttributeValue(SquareInvBVO.FTAXTYPEFLAG);
  }

  /**
   * 设置扣税类别
   * 
   * @param ftaxtypeflag 扣税类别
   */
  public void setFtaxtypeflag(Integer ftaxtypeflag) {
    this.setAttributeValue(SquareInvBVO.FTAXTYPEFLAG, ftaxtypeflag);
  }

  /**
   * 获取累计回冲应收数量
   * 
   * @return 累计回冲应收数量
   */
  public UFDouble getNarrushnum() {
    return (UFDouble) this.getAttributeValue(SquareInvBVO.NARRUSHNUM);
  }

  /**
   * 设置累计回冲应收数量
   * 
   * @param narrushnum 累计回冲应收数量
   */
  public void setNarrushnum(UFDouble narrushnum) {
    this.setAttributeValue(SquareInvBVO.NARRUSHNUM, narrushnum);
  }

  /**
   * 获取单位数量
   * 
   * @return 单位数量
   */
  public UFDouble getNastnum() {
    return (UFDouble) this.getAttributeValue(SquareInvBVO.NASTNUM);
  }

  /**
   * 设置单位数量
   * 
   * @param nastnum 单位数量
   */
  public void setNastnum(UFDouble nastnum) {
    this.setAttributeValue(SquareInvBVO.NASTNUM, nastnum);
  }

  /**
   * 获取计税金额
   * 
   * @return 计税金额
   */
  public UFDouble getNcaltaxmny() {
    return (UFDouble) this.getAttributeValue(SquareInvBVO.NCALTAXMNY);
  }

  /**
   * 设置计税金额
   * 
   * @param ncaltaxmny 计税金额
   */
  public void setNcaltaxmny(UFDouble ncaltaxmny) {
    this.setAttributeValue(SquareInvBVO.NCALTAXMNY, ncaltaxmny);
  }

  /**
   * 获取本币折扣额
   * 
   * @return 本币折扣额
   */
  public UFDouble getNdiscount() {
    return (UFDouble) this.getAttributeValue(SquareInvBVO.NDISCOUNT);
  }

  /**
   * 设置本币折扣额
   * 
   * @param ndiscount 本币折扣额
   */
  public void setNdiscount(UFDouble ndiscount) {
    this.setAttributeValue(SquareInvBVO.NDISCOUNT, ndiscount);
  }

  /**
   * 获取折本汇率
   * 
   * @return 折本汇率
   */
  public UFDouble getNexchangerate() {
    return (UFDouble) this.getAttributeValue(SquareInvBVO.NEXCHANGERATE);
  }

  /**
   * 设置折本汇率
   * 
   * @param nexchangerate 折本汇率
   */
  public void setNexchangerate(UFDouble nexchangerate) {
    this.setAttributeValue(SquareInvBVO.NEXCHANGERATE, nexchangerate);
  }

  /**
   * 获取全局本位币汇率
   * 
   * @return 全局本位币汇率
   */
  public UFDouble getNglobalexchgrate() {
    return (UFDouble) this.getAttributeValue(SquareInvBVO.NGLOBALEXCHGRATE);
  }

  /**
   * 设置全局本位币汇率
   * 
   * @param nglobalexchgrate 全局本位币汇率
   */
  public void setNglobalexchgrate(UFDouble nglobalexchgrate) {
    this.setAttributeValue(SquareInvBVO.NGLOBALEXCHGRATE, nglobalexchgrate);
  }

  /**
   * 获取全局本币无税金额
   * 
   * @return 全局本币无税金额
   */
  public UFDouble getNglobalmny() {
    return (UFDouble) this.getAttributeValue(SquareInvBVO.NGLOBALMNY);
  }

  /**
   * 设置全局本币无税金额
   * 
   * @param nglobalmny 全局本币无税金额
   */
  public void setNglobalmny(UFDouble nglobalmny) {
    this.setAttributeValue(SquareInvBVO.NGLOBALMNY, nglobalmny);
  }

  /**
   * 获取全局本币价税合计
   * 
   * @return 全局本币价税合计
   */
  public UFDouble getNglobaltaxmny() {
    return (UFDouble) this.getAttributeValue(SquareInvBVO.NGLOBALTAXMNY);
  }

  /**
   * 设置全局本币价税合计
   * 
   * @param nglobaltaxmny 全局本币价税合计
   */
  public void setNglobaltaxmny(UFDouble nglobaltaxmny) {
    this.setAttributeValue(SquareInvBVO.NGLOBALTAXMNY, nglobaltaxmny);
  }

  /**
   * 获取集团本位币汇率
   * 
   * @return 集团本位币汇率
   */
  public UFDouble getNgroupexchgrate() {
    return (UFDouble) this.getAttributeValue(SquareInvBVO.NGROUPEXCHGRATE);
  }

  /**
   * 设置集团本位币汇率
   * 
   * @param ngroupexchgrate 集团本位币汇率
   */
  public void setNgroupexchgrate(UFDouble ngroupexchgrate) {
    this.setAttributeValue(SquareInvBVO.NGROUPEXCHGRATE, ngroupexchgrate);
  }

  /**
   * 获取集团本币无税金额
   * 
   * @return 集团本币无税金额
   */
  public UFDouble getNgroupmny() {
    return (UFDouble) this.getAttributeValue(SquareInvBVO.NGROUPMNY);
  }

  /**
   * 设置集团本币无税金额
   * 
   * @param ngroupmny 集团本币无税金额
   */
  public void setNgroupmny(UFDouble ngroupmny) {
    this.setAttributeValue(SquareInvBVO.NGROUPMNY, ngroupmny);
  }

  /**
   * 获取集团本币价税合计
   * 
   * @return 集团本币价税合计
   */
  public UFDouble getNgrouptaxmny() {
    return (UFDouble) this.getAttributeValue(SquareInvBVO.NGROUPTAXMNY);
  }

  /**
   * 设置集团本币价税合计
   * 
   * @param ngrouptaxmny 集团本币价税合计
   */
  public void setNgrouptaxmny(UFDouble ngrouptaxmny) {
    this.setAttributeValue(SquareInvBVO.NGROUPTAXMNY, ngrouptaxmny);
  }

  /**
   * 获取单品折扣率
   * 
   * @return 单品折扣率
   */
  public UFDouble getNitemdiscountrate() {
    return (UFDouble) this.getAttributeValue(SquareInvBVO.NITEMDISCOUNTRATE);
  }

  /**
   * 设置单品折扣率
   * 
   * @param nitemdiscountrate 单品折扣率
   */
  public void setNitemdiscountrate(UFDouble nitemdiscountrate) {
    this.setAttributeValue(SquareInvBVO.NITEMDISCOUNTRATE, nitemdiscountrate);
  }

  /**
   * 获取本币无税金额
   * 
   * @return 本币无税金额
   */
  public UFDouble getNmny() {
    return (UFDouble) this.getAttributeValue(SquareInvBVO.NMNY);
  }

  /**
   * 设置本币无税金额
   * 
   * @param nmny 本币无税金额
   */
  public void setNmny(UFDouble nmny) {
    this.setAttributeValue(SquareInvBVO.NMNY, nmny);
  }

  /**
   * 获取本币无税净价
   * 
   * @return 本币无税净价
   */
  public UFDouble getNnetprice() {
    return (UFDouble) this.getAttributeValue(SquareInvBVO.NNETPRICE);
  }

  /**
   * 设置本币无税净价
   * 
   * @param nnetprice 本币无税净价
   */
  public void setNnetprice(UFDouble nnetprice) {
    this.setAttributeValue(SquareInvBVO.NNETPRICE, nnetprice);
  }

  /**
   * 获取主单位数量
   * 
   * @return 主单位数量
   */
  public UFDouble getNnum() {
    return (UFDouble) this.getAttributeValue(SquareInvBVO.NNUM);
  }

  /**
   * 设置主单位数量
   * 
   * @param nnum 主单位数量
   */
  public void setNnum(UFDouble nnum) {
    this.setAttributeValue(SquareInvBVO.NNUM, nnum);
  }

  /**
   * 获取原币折扣额
   * 
   * @return 原币折扣额
   */
  public UFDouble getNorigdiscount() {
    return (UFDouble) this.getAttributeValue(SquareInvBVO.NORIGDISCOUNT);
  }

  /**
   * 设置原币折扣额
   * 
   * @param norigdiscount 原币折扣额
   */
  public void setNorigdiscount(UFDouble norigdiscount) {
    this.setAttributeValue(SquareInvBVO.NORIGDISCOUNT, norigdiscount);
  }

  /**
   * 获取原币无税金额
   * 
   * @return 原币无税金额
   */
  public UFDouble getNorigmny() {
    return (UFDouble) this.getAttributeValue(SquareInvBVO.NORIGMNY);
  }

  /**
   * 设置原币无税金额
   * 
   * @param norigmny 原币无税金额
   */
  public void setNorigmny(UFDouble norigmny) {
    this.setAttributeValue(SquareInvBVO.NORIGMNY, norigmny);
  }

  /**
   * 获取原币无税净价
   * 
   * @return 原币无税净价
   */
  public UFDouble getNorignetprice() {
    return (UFDouble) this.getAttributeValue(SquareInvBVO.NORIGNETPRICE);
  }

  /**
   * 设置原币无税净价
   * 
   * @param norignetprice 原币无税净价
   */
  public void setNorignetprice(UFDouble norignetprice) {
    this.setAttributeValue(SquareInvBVO.NORIGNETPRICE, norignetprice);
  }

  /**
   * 获取原币无税单价
   * 
   * @return 原币无税单价
   */
  public UFDouble getNorigprice() {
    return (UFDouble) this.getAttributeValue(SquareInvBVO.NORIGPRICE);
  }

  /**
   * 设置原币无税单价
   * 
   * @param norigprice 原币无税单价
   */
  public void setNorigprice(UFDouble norigprice) {
    this.setAttributeValue(SquareInvBVO.NORIGPRICE, norigprice);
  }

  /**
   * 获取原币价税合计
   * 
   * @return 原币价税合计
   */
  public UFDouble getNorigtaxmny() {
    return (UFDouble) this.getAttributeValue(SquareInvBVO.NORIGTAXMNY);
  }

  /**
   * 设置原币价税合计
   * 
   * @param norigtaxmny 原币价税合计
   */
  public void setNorigtaxmny(UFDouble norigtaxmny) {
    this.setAttributeValue(SquareInvBVO.NORIGTAXMNY, norigtaxmny);
  }

  /**
   * 获取原币含税净价
   * 
   * @return 原币含税净价
   */
  public UFDouble getNorigtaxnetprice() {
    return (UFDouble) this.getAttributeValue(SquareInvBVO.NORIGTAXNETPRICE);
  }

  /**
   * 设置原币含税净价
   * 
   * @param norigtaxnetprice 原币含税净价
   */
  public void setNorigtaxnetprice(UFDouble norigtaxnetprice) {
    this.setAttributeValue(SquareInvBVO.NORIGTAXNETPRICE, norigtaxnetprice);
  }

  /**
   * 获取原币含税单价
   * 
   * @return 原币含税单价
   */
  public UFDouble getNorigtaxprice() {
    return (UFDouble) this.getAttributeValue(SquareInvBVO.NORIGTAXPRICE);
  }

  /**
   * 设置原币含税单价
   * 
   * @param norigtaxprice 原币含税单价
   */
  public void setNorigtaxprice(UFDouble norigtaxprice) {
    this.setAttributeValue(SquareInvBVO.NORIGTAXPRICE, norigtaxprice);
  }

  /**
   * 获取本币无税单价
   * 
   * @return 本币无税单价
   */
  public UFDouble getNprice() {
    return (UFDouble) this.getAttributeValue(SquareInvBVO.NPRICE);
  }

  /**
   * 设置本币无税单价
   * 
   * @param nprice 本币无税单价
   */
  public void setNprice(UFDouble nprice) {
    this.setAttributeValue(SquareInvBVO.NPRICE, nprice);
  }

  /**
   * 获取累计确认应收数量
   * 
   * @return 累计确认应收数量
   */
  public UFDouble getNsquarearnum() {
    return (UFDouble) this.getAttributeValue(SquareInvBVO.NSQUAREARNUM);
  }

  /**
   * 设置累计确认应收数量
   * 
   * @param nsquarearnum 累计确认应收数量
   */
  public void setNsquarearnum(UFDouble nsquarearnum) {
    this.setAttributeValue(SquareInvBVO.NSQUAREARNUM, nsquarearnum);
  }

  /**
   * 获取累计成本结算数量
   * 
   * @return 累计成本结算数量
   */
  public UFDouble getNsquareianum() {
    return (UFDouble) this.getAttributeValue(SquareInvBVO.NSQUAREIANUM);
  }

  /**
   * 设置累计成本结算数量
   * 
   * @param nsquareianum 累计成本结算数量
   */
  public void setNsquareianum(UFDouble nsquareianum) {
    this.setAttributeValue(SquareInvBVO.NSQUAREIANUM, nsquareianum);
  }

  /**
   * 获取累计发出商品数量
   * 
   * @return 累计发出商品数量
   */
  public UFDouble getNsquareregnum() {
    return (UFDouble) this.getAttributeValue(SquareInvBVO.NSQUAREREGNUM);
  }

  /**
   * 设置累计发出商品数量
   * 
   * @param nsquareregnum 累计发出商品数量
   */
  public void setNsquareregnum(UFDouble nsquareregnum) {
    this.setAttributeValue(SquareInvBVO.NSQUAREREGNUM, nsquareregnum);
  }

  /**
   * 获取本币税额
   * 
   * @return 本币税额
   */
  public UFDouble getNtax() {
    return (UFDouble) this.getAttributeValue(SquareInvBVO.NTAX);
  }

  /**
   * 设置本币税额
   * 
   * @param ntax 本币税额
   */
  public void setNtax(UFDouble ntax) {
    this.setAttributeValue(SquareInvBVO.NTAX, ntax);
  }

  /**
   * 获取本币价税合计
   * 
   * @return 本币价税合计
   */
  public UFDouble getNtaxmny() {
    return (UFDouble) this.getAttributeValue(SquareInvBVO.NTAXMNY);
  }

  /**
   * 设置本币价税合计
   * 
   * @param ntaxmny 本币价税合计
   */
  public void setNtaxmny(UFDouble ntaxmny) {
    this.setAttributeValue(SquareInvBVO.NTAXMNY, ntaxmny);
  }

  /**
   * 获取本币含税净价
   * 
   * @return 本币含税净价
   */
  public UFDouble getNtaxnetprice() {
    return (UFDouble) this.getAttributeValue(SquareInvBVO.NTAXNETPRICE);
  }

  /**
   * 设置本币含税净价
   * 
   * @param ntaxnetprice 本币含税净价
   */
  public void setNtaxnetprice(UFDouble ntaxnetprice) {
    this.setAttributeValue(SquareInvBVO.NTAXNETPRICE, ntaxnetprice);
  }

  /**
   * 获取本币含税单价
   * 
   * @return 本币含税单价
   */
  public UFDouble getNtaxprice() {
    return (UFDouble) this.getAttributeValue(SquareInvBVO.NTAXPRICE);
  }

  /**
   * 设置本币含税单价
   * 
   * @param ntaxprice 本币含税单价
   */
  public void setNtaxprice(UFDouble ntaxprice) {
    this.setAttributeValue(SquareInvBVO.NTAXPRICE, ntaxprice);
  }

  /**
   * 获取税率
   * 
   * @return 税率
   */
  public UFDouble getNtaxrate() {
    return (UFDouble) this.getAttributeValue(SquareInvBVO.NTAXRATE);
  }

  /**
   * 设置税率
   * 
   * @param ntaxrate 税率
   */
  public void setNtaxrate(UFDouble ntaxrate) {
    this.setAttributeValue(SquareInvBVO.NTAXRATE, ntaxrate);
  }

  /**
   * 获取nthisnum（可持续化字段，可以远程传递值，但不能保存到数据库）
   * 
   * @return nthisnum
   */
  public UFDouble getNthisnum() {
    return (UFDouble) this.getAttributeValue(SquareInvBVO.NTHISNUM);
  }

  /**
   * 设置nthisnum（可持续化字段，可以远程传递值，但不能保存到数据库）
   * 
   * @param nthisnum nthisnum
   */
  public void setNthisnum(UFDouble nthisnum) {
    this.setAttributeValue(SquareInvBVO.NTHISNUM, nthisnum);
  }

  /**
   * 获取批次号档案
   * 
   * @return 批次号档案
   */
  public String getPk_batchcode() {
    return (String) this.getAttributeValue(SquareInvBVO.PK_BATCHCODE);
  }

  /**
   * 设置批次号档案
   * 
   * @param pk_batchcode 批次号档案
   */
  public void setPk_batchcode(String pk_batchcode) {
    this.setAttributeValue(SquareInvBVO.PK_BATCHCODE, pk_batchcode);
  }

  /**
   * 获取集团
   * 
   * @return 集团
   */
  public String getPk_group() {
    return (String) this.getAttributeValue(SquareInvBVO.PK_GROUP);
  }

  /**
   * 设置集团
   * 
   * @param pk_group 集团
   */
  public void setPk_group(String pk_group) {
    this.setAttributeValue(SquareInvBVO.PK_GROUP, pk_group);
  }

  /**
   * 获取结算财务组织
   * 
   * @return 结算财务组织
   */
  public String getPk_org() {
    return (String) this.getAttributeValue(SquareInvBVO.PK_ORG);
  }

  /**
   * 设置结算财务组织
   * 
   * @param pk_org 结算财务组织
   */
  public void setPk_org(String pk_org) {
    this.setAttributeValue(SquareInvBVO.PK_ORG, pk_org);
  }

  /**
   * 获取processeid（可持续化字段，可以远程传递值，但不能保存到数据库）
   * 
   * @return processeid
   */
  public String getProcesseid() {
    return (String) this.getAttributeValue(SquareInvBVO.PROCESSEID);
  }

  /**
   * 设置processeid（可持续化字段，可以远程传递值，但不能保存到数据库）
   * 
   * @param processeid processeid
   */
  public void setProcesseid(String processeid) {
    this.setAttributeValue(SquareInvBVO.PROCESSEID, processeid);
  }

  /**
   * 获取时间戳
   * 
   * @return 时间戳
   */
  public UFDateTime getTs() {
    return (UFDateTime) this.getAttributeValue(SquareInvBVO.TS);
  }

  /**
   * 设置时间戳
   * 
   * @param ts 时间戳
   */
  public void setTs(UFDateTime ts) {
    this.setAttributeValue(SquareInvBVO.TS, ts);
  }

  /**
   * 获取批次号
   * 
   * @return 批次号
   */
  public String getVbatchcode() {
    return (String) this.getAttributeValue(SquareInvBVO.VBATCHCODE);
  }

  /**
   * 设置批次号
   * 
   * @param vbatchcode 批次号
   */
  public void setVbatchcode(String vbatchcode) {
    this.setAttributeValue(SquareInvBVO.VBATCHCODE, vbatchcode);
  }

  /**
   * 获取自定义项1
   * 
   * @return 自定义项1
   */
  public String getVbdef1() {
    return (String) this.getAttributeValue(SquareInvBVO.VBDEF1);
  }

  /**
   * 设置自定义项1
   * 
   * @param vbdef1 自定义项1
   */
  public void setVbdef1(String vbdef1) {
    this.setAttributeValue(SquareInvBVO.VBDEF1, vbdef1);
  }

  /**
   * 获取自定义项10
   * 
   * @return 自定义项10
   */
  public String getVbdef10() {
    return (String) this.getAttributeValue(SquareInvBVO.VBDEF10);
  }

  /**
   * 设置自定义项10
   * 
   * @param vbdef10 自定义项10
   */
  public void setVbdef10(String vbdef10) {
    this.setAttributeValue(SquareInvBVO.VBDEF10, vbdef10);
  }

  /**
   * 获取自定义项11
   * 
   * @return 自定义项11
   */
  public String getVbdef11() {
    return (String) this.getAttributeValue(SquareInvBVO.VBDEF11);
  }

  /**
   * 设置自定义项11
   * 
   * @param vbdef11 自定义项11
   */
  public void setVbdef11(String vbdef11) {
    this.setAttributeValue(SquareInvBVO.VBDEF11, vbdef11);
  }

  /**
   * 获取自定义项12
   * 
   * @return 自定义项12
   */
  public String getVbdef12() {
    return (String) this.getAttributeValue(SquareInvBVO.VBDEF12);
  }

  /**
   * 设置自定义项12
   * 
   * @param vbdef12 自定义项12
   */
  public void setVbdef12(String vbdef12) {
    this.setAttributeValue(SquareInvBVO.VBDEF12, vbdef12);
  }

  /**
   * 获取自定义项13
   * 
   * @return 自定义项13
   */
  public String getVbdef13() {
    return (String) this.getAttributeValue(SquareInvBVO.VBDEF13);
  }

  /**
   * 设置自定义项13
   * 
   * @param vbdef13 自定义项13
   */
  public void setVbdef13(String vbdef13) {
    this.setAttributeValue(SquareInvBVO.VBDEF13, vbdef13);
  }

  /**
   * 获取自定义项14
   * 
   * @return 自定义项14
   */
  public String getVbdef14() {
    return (String) this.getAttributeValue(SquareInvBVO.VBDEF14);
  }

  /**
   * 设置自定义项14
   * 
   * @param vbdef14 自定义项14
   */
  public void setVbdef14(String vbdef14) {
    this.setAttributeValue(SquareInvBVO.VBDEF14, vbdef14);
  }

  /**
   * 获取自定义项15
   * 
   * @return 自定义项15
   */
  public String getVbdef15() {
    return (String) this.getAttributeValue(SquareInvBVO.VBDEF15);
  }

  /**
   * 设置自定义项15
   * 
   * @param vbdef15 自定义项15
   */
  public void setVbdef15(String vbdef15) {
    this.setAttributeValue(SquareInvBVO.VBDEF15, vbdef15);
  }

  /**
   * 获取自定义项16
   * 
   * @return 自定义项16
   */
  public String getVbdef16() {
    return (String) this.getAttributeValue(SquareInvBVO.VBDEF16);
  }

  /**
   * 设置自定义项16
   * 
   * @param vbdef16 自定义项16
   */
  public void setVbdef16(String vbdef16) {
    this.setAttributeValue(SquareInvBVO.VBDEF16, vbdef16);
  }

  /**
   * 获取自定义项17
   * 
   * @return 自定义项17
   */
  public String getVbdef17() {
    return (String) this.getAttributeValue(SquareInvBVO.VBDEF17);
  }

  /**
   * 设置自定义项17
   * 
   * @param vbdef17 自定义项17
   */
  public void setVbdef17(String vbdef17) {
    this.setAttributeValue(SquareInvBVO.VBDEF17, vbdef17);
  }

  /**
   * 获取自定义项18
   * 
   * @return 自定义项18
   */
  public String getVbdef18() {
    return (String) this.getAttributeValue(SquareInvBVO.VBDEF18);
  }

  /**
   * 设置自定义项18
   * 
   * @param vbdef18 自定义项18
   */
  public void setVbdef18(String vbdef18) {
    this.setAttributeValue(SquareInvBVO.VBDEF18, vbdef18);
  }

  /**
   * 获取自定义项19
   * 
   * @return 自定义项19
   */
  public String getVbdef19() {
    return (String) this.getAttributeValue(SquareInvBVO.VBDEF19);
  }

  /**
   * 设置自定义项19
   * 
   * @param vbdef19 自定义项19
   */
  public void setVbdef19(String vbdef19) {
    this.setAttributeValue(SquareInvBVO.VBDEF19, vbdef19);
  }

  /**
   * 获取自定义项2
   * 
   * @return 自定义项2
   */
  public String getVbdef2() {
    return (String) this.getAttributeValue(SquareInvBVO.VBDEF2);
  }

  /**
   * 设置自定义项2
   * 
   * @param vbdef2 自定义项2
   */
  public void setVbdef2(String vbdef2) {
    this.setAttributeValue(SquareInvBVO.VBDEF2, vbdef2);
  }

  /**
   * 获取自定义项20
   * 
   * @return 自定义项20
   */
  public String getVbdef20() {
    return (String) this.getAttributeValue(SquareInvBVO.VBDEF20);
  }

  /**
   * 设置自定义项20
   * 
   * @param vbdef20 自定义项20
   */
  public void setVbdef20(String vbdef20) {
    this.setAttributeValue(SquareInvBVO.VBDEF20, vbdef20);
  }

  /**
   * 获取自定义项3
   * 
   * @return 自定义项3
   */
  public String getVbdef3() {
    return (String) this.getAttributeValue(SquareInvBVO.VBDEF3);
  }

  /**
   * 设置自定义项3
   * 
   * @param vbdef3 自定义项3
   */
  public void setVbdef3(String vbdef3) {
    this.setAttributeValue(SquareInvBVO.VBDEF3, vbdef3);
  }

  /**
   * 获取自定义项4
   * 
   * @return 自定义项4
   */
  public String getVbdef4() {
    return (String) this.getAttributeValue(SquareInvBVO.VBDEF4);
  }

  /**
   * 设置自定义项4
   * 
   * @param vbdef4 自定义项4
   */
  public void setVbdef4(String vbdef4) {
    this.setAttributeValue(SquareInvBVO.VBDEF4, vbdef4);
  }

  /**
   * 获取自定义项5
   * 
   * @return 自定义项5
   */
  public String getVbdef5() {
    return (String) this.getAttributeValue(SquareInvBVO.VBDEF5);
  }

  /**
   * 设置自定义项5
   * 
   * @param vbdef5 自定义项5
   */
  public void setVbdef5(String vbdef5) {
    this.setAttributeValue(SquareInvBVO.VBDEF5, vbdef5);
  }

  /**
   * 获取自定义项6
   * 
   * @return 自定义项6
   */
  public String getVbdef6() {
    return (String) this.getAttributeValue(SquareInvBVO.VBDEF6);
  }

  /**
   * 设置自定义项6
   * 
   * @param vbdef6 自定义项6
   */
  public void setVbdef6(String vbdef6) {
    this.setAttributeValue(SquareInvBVO.VBDEF6, vbdef6);
  }

  /**
   * 获取自定义项7
   * 
   * @return 自定义项7
   */
  public String getVbdef7() {
    return (String) this.getAttributeValue(SquareInvBVO.VBDEF7);
  }

  /**
   * 设置自定义项7
   * 
   * @param vbdef7 自定义项7
   */
  public void setVbdef7(String vbdef7) {
    this.setAttributeValue(SquareInvBVO.VBDEF7, vbdef7);
  }

  /**
   * 获取自定义项8
   * 
   * @return 自定义项8
   */
  public String getVbdef8() {
    return (String) this.getAttributeValue(SquareInvBVO.VBDEF8);
  }

  /**
   * 设置自定义项8
   * 
   * @param vbdef8 自定义项8
   */
  public void setVbdef8(String vbdef8) {
    this.setAttributeValue(SquareInvBVO.VBDEF8, vbdef8);
  }

  /**
   * 获取自定义项9
   * 
   * @return 自定义项9
   */
  public String getVbdef9() {
    return (String) this.getAttributeValue(SquareInvBVO.VBDEF9);
  }

  /**
   * 设置自定义项9
   * 
   * @param vbdef9 自定义项9
   */
  public void setVbdef9(String vbdef9) {
    this.setAttributeValue(SquareInvBVO.VBDEF9, vbdef9);
  }

  /**
   * 获取单位换算率
   * 
   * @return 单位换算率
   */
  public String getVchangerate() {
    return (String) this.getAttributeValue(SquareInvBVO.VCHANGERATE);
  }

  /**
   * 设置单位换算率
   * 
   * @param vchangerate 单位换算率
   */
  public void setVchangerate(String vchangerate) {
    this.setAttributeValue(SquareInvBVO.VCHANGERATE, vchangerate);
  }

  /**
   * 获取合同号
   * 
   * @return 合同号
   */
  public String getVctcode() {
    return (String) this.getAttributeValue(SquareInvBVO.VCTCODE);
  }

  /**
   * 设置合同号
   * 
   * @param vctcode 合同号
   */
  public void setVctcode(String vctcode) {
    this.setAttributeValue(SquareInvBVO.VCTCODE, vctcode);
  }

  /**
   * 获取源头单据号
   * 
   * @return 源头单据号
   */
  public String getVfirstcode() {
    return (String) this.getAttributeValue(SquareInvBVO.VFIRSTCODE);
  }

  /**
   * 设置源头单据号
   * 
   * @param vfirstcode 源头单据号
   */
  public void setVfirstcode(String vfirstcode) {
    this.setAttributeValue(SquareInvBVO.VFIRSTCODE, vfirstcode);
  }

  /**
   * 获取源头单据行号
   * 
   * @return 源头单据行号
   */
  public String getVfirstrowno() {
    return (String) this.getAttributeValue(SquareInvBVO.VFIRSTROWNO);
  }

  /**
   * 设置源头单据行号
   * 
   * @param vfirstrowno 源头单据行号
   */
  public void setVfirstrowno(String vfirstrowno) {
    this.setAttributeValue(SquareInvBVO.VFIRSTROWNO, vfirstrowno);
  }

  /**
   * 获取源头单据交易类型
   * 
   * @return 源头单据交易类型
   */
  public String getVfirsttrantype() {
    return (String) this.getAttributeValue(SquareInvBVO.VFIRSTTRANTYPE);
  }

  /**
   * 设置源头单据交易类型
   * 
   * @param vfirsttrantype 源头单据交易类型
   */
  public void setVfirsttrantype(String vfirsttrantype) {
    this.setAttributeValue(SquareInvBVO.VFIRSTTRANTYPE, vfirsttrantype);
  }

  /**
   * 获取源头单据类型
   * 
   * @return 源头单据类型
   */
  public String getVfirsttype() {
    return (String) this.getAttributeValue(SquareInvBVO.VFIRSTTYPE);
  }

  /**
   * 设置源头单据类型
   * 
   * @param vfirsttype 源头单据类型
   */
  public void setVfirsttype(String vfirsttype) {
    this.setAttributeValue(SquareInvBVO.VFIRSTTYPE, vfirsttype);
  }

  /**
   * 获取自由辅助属性1
   * 
   * @return 自由辅助属性1
   */
  public String getVfree1() {
    return (String) this.getAttributeValue(SquareInvBVO.VFREE1);
  }

  /**
   * 设置自由辅助属性1
   * 
   * @param vfree1 自由辅助属性1
   */
  public void setVfree1(String vfree1) {
    this.setAttributeValue(SquareInvBVO.VFREE1, vfree1);
  }

  /**
   * 获取自由辅助属性10
   * 
   * @return 自由辅助属性10
   */
  public String getVfree10() {
    return (String) this.getAttributeValue(SquareInvBVO.VFREE10);
  }

  /**
   * 设置自由辅助属性10
   * 
   * @param vfree10 自由辅助属性10
   */
  public void setVfree10(String vfree10) {
    this.setAttributeValue(SquareInvBVO.VFREE10, vfree10);
  }

  /**
   * 获取自由辅助属性2
   * 
   * @return 自由辅助属性2
   */
  public String getVfree2() {
    return (String) this.getAttributeValue(SquareInvBVO.VFREE2);
  }

  /**
   * 设置自由辅助属性2
   * 
   * @param vfree2 自由辅助属性2
   */
  public void setVfree2(String vfree2) {
    this.setAttributeValue(SquareInvBVO.VFREE2, vfree2);
  }

  /**
   * 获取自由辅助属性3
   * 
   * @return 自由辅助属性3
   */
  public String getVfree3() {
    return (String) this.getAttributeValue(SquareInvBVO.VFREE3);
  }

  /**
   * 设置自由辅助属性3
   * 
   * @param vfree3 自由辅助属性3
   */
  public void setVfree3(String vfree3) {
    this.setAttributeValue(SquareInvBVO.VFREE3, vfree3);
  }

  /**
   * 获取自由辅助属性4
   * 
   * @return 自由辅助属性4
   */
  public String getVfree4() {
    return (String) this.getAttributeValue(SquareInvBVO.VFREE4);
  }

  /**
   * 设置自由辅助属性4
   * 
   * @param vfree4 自由辅助属性4
   */
  public void setVfree4(String vfree4) {
    this.setAttributeValue(SquareInvBVO.VFREE4, vfree4);
  }

  /**
   * 获取自由辅助属性5
   * 
   * @return 自由辅助属性5
   */
  public String getVfree5() {
    return (String) this.getAttributeValue(SquareInvBVO.VFREE5);
  }

  /**
   * 设置自由辅助属性5
   * 
   * @param vfree5 自由辅助属性5
   */
  public void setVfree5(String vfree5) {
    this.setAttributeValue(SquareInvBVO.VFREE5, vfree5);
  }

  /**
   * 获取自由辅助属性6
   * 
   * @return 自由辅助属性6
   */
  public String getVfree6() {
    return (String) this.getAttributeValue(SquareInvBVO.VFREE6);
  }

  /**
   * 设置自由辅助属性6
   * 
   * @param vfree6 自由辅助属性6
   */
  public void setVfree6(String vfree6) {
    this.setAttributeValue(SquareInvBVO.VFREE6, vfree6);
  }

  /**
   * 获取自由辅助属性7
   * 
   * @return 自由辅助属性7
   */
  public String getVfree7() {
    return (String) this.getAttributeValue(SquareInvBVO.VFREE7);
  }

  /**
   * 设置自由辅助属性7
   * 
   * @param vfree7 自由辅助属性7
   */
  public void setVfree7(String vfree7) {
    this.setAttributeValue(SquareInvBVO.VFREE7, vfree7);
  }

  /**
   * 获取自由辅助属性8
   * 
   * @return 自由辅助属性8
   */
  public String getVfree8() {
    return (String) this.getAttributeValue(SquareInvBVO.VFREE8);
  }

  /**
   * 设置自由辅助属性8
   * 
   * @param vfree8 自由辅助属性8
   */
  public void setVfree8(String vfree8) {
    this.setAttributeValue(SquareInvBVO.VFREE8, vfree8);
  }

  /**
   * 获取自由辅助属性9
   * 
   * @return 自由辅助属性9
   */
  public String getVfree9() {
    return (String) this.getAttributeValue(SquareInvBVO.VFREE9);
  }

  /**
   * 设置自由辅助属性9
   * 
   * @param vfree9 自由辅助属性9
   */
  public void setVfree9(String vfree9) {
    this.setAttributeValue(SquareInvBVO.VFREE9, vfree9);
  }

  /**
   * 获取行备注
   * 
   * @return 行备注
   */
  public String getVrownote() {
    return (String) this.getAttributeValue(SquareInvBVO.VROWNOTE);
  }

  /**
   * 设置行备注
   * 
   * @param vrownote 行备注
   */
  public void setVrownote(String vrownote) {
    this.setAttributeValue(SquareInvBVO.VROWNOTE, vrownote);
  }

  /**
   * 获取来源单据号
   * 
   * @return 来源单据号
   */
  public String getVsrccode() {
    return (String) this.getAttributeValue(SquareInvBVO.VSRCCODE);
  }

  /**
   * 设置来源单据号
   * 
   * @param vsrccode 来源单据号
   */
  public void setVsrccode(String vsrccode) {
    this.setAttributeValue(SquareInvBVO.VSRCCODE, vsrccode);
  }

  /**
   * 获取来源单据行号
   * 
   * @return 来源单据行号
   */
  public String getVsrcrowno() {
    return (String) this.getAttributeValue(SquareInvBVO.VSRCROWNO);
  }

  /**
   * 设置来源单据行号
   * 
   * @param vsrcrowno 来源单据行号
   */
  public void setVsrcrowno(String vsrcrowno) {
    this.setAttributeValue(SquareInvBVO.VSRCROWNO, vsrcrowno);
  }

  /**
   * 获取来源单据交易类型
   * 
   * @return 来源单据交易类型
   */
  public String getVsrctrantype() {
    return (String) this.getAttributeValue(SquareInvBVO.VSRCTRANTYPE);
  }

  /**
   * 设置来源单据交易类型
   * 
   * @param vsrctrantype 来源单据交易类型
   */
  public void setVsrctrantype(String vsrctrantype) {
    this.setAttributeValue(SquareInvBVO.VSRCTRANTYPE, vsrctrantype);
  }

  /**
   * 获取来源单据类型
   * 
   * @return 来源单据类型
   */
  public String getVsrctype() {
    return (String) this.getAttributeValue(SquareInvBVO.VSRCTYPE);
  }

  /**
   * 设置来源单据类型
   * 
   * @param vsrctype 来源单据类型
   */
  public void setVsrctype(String vsrctype) {
    this.setAttributeValue(SquareInvBVO.VSRCTYPE, vsrctype);
  }

  /**
   * 获取收支项目
   * 
   * @return 收支项目
   */
  public String getCcostsubjid() {
    return (String) this.getAttributeValue(SquareInvBVO.CCOSTSUBJID);
  }

  /**
   * 设置收支项目
   * 
   * @param ccostsubjid 收支项目
   */
  public void setCcostsubjid(String ccostsubjid) {
    this.setAttributeValue(SquareInvBVO.CCOSTSUBJID, ccostsubjid);
  }

  @Override
  public IVOMeta getMetaData() {
    IVOMeta meta =
        VOMetaFactory.getInstance().getVOMeta(SquareInvBVO.ENTITYNAME);
    return meta;
  }

  /**
   * 
   * @param dr
   */
  public void setDr(Integer dr) {
    this.setAttributeValue(SquareInvBVO.DR, dr);
  }

  /**
   * 
   * @return Integer
   */
  public Integer getDr() {
    return (Integer) this.getAttributeValue(SquareInvBVO.DR);
  }

  /**
   * 差额传应收结算数量，不可持久化。（差额传应收时，数量和单价会清空，导致销售发票结算不关闭)
   * 
   * @return 差额传应收结算数量，不可持久化。
   */
  public UFDouble getDifftoarsquarenum() {
    return (UFDouble) this.getAttributeValue(SquareInvBVO.DIFFTOARSQUARENUM);
  }

  /**
   * 差额传应收结算数量，不可持久化。（差额传应收时，数量和单价会清空，导致销售发票结算不关闭)
   * 
   * @param difftoarsquarenum 差额传应收结算数量，不可持久化。
   */
  public void setDifftoarsquarenum(UFDouble difftoarsquarenum) {
    this.setAttributeValue(SquareInvBVO.DIFFTOARSQUARENUM, difftoarsquarenum);
  }
  
  /**
   * 获取特征码
   * 
   * @return 特征码
   */
  public String  getCmffileid() {
    return (String) this.getAttributeValue(SquareInvBVO.CMFFILEID);
  }
  
  /**
   * 设置特征码
   * 
   * @param 特征码
   */
  public void setCmffileid(String cmffileid) {
    this.setAttributeValue(SquareInvBVO.CMFFILEID, cmffileid);
  }
  
  /**
   * 发货利润中心
   */
  public static final String CSPROFITCENTERVID = "csprofitcentervid";

  /**
   * 发货利润中心版本
   */
  public static final String CSPROFITCENTERID = "csprofitcenterid";

  /**
   * 获取发货利润中心
   * 
   * @return 发货利润中心
   */
  public String getCsprofitcentervid() {
    return (String) this.getAttributeValue(SquareInvBVO.CSPROFITCENTERVID);
  }

  /**
   * 设置发货利润中心
   * 
   * @param csprofitcentervid 发货利润中心
   */
  public void setCsprofitcentervid(String csprofitcentervid) {
    this.setAttributeValue(SquareInvBVO.CSPROFITCENTERVID, csprofitcentervid);
  }

  /**
   * 获取发货利润中心版本
   * 
   * @return 发货利润中心版本
   */
  public String getCsprofitcenterid() {
    return (String) this.getAttributeValue(SquareInvBVO.CSPROFITCENTERID);
  }

  /**
   * 设置发货利润中心版本
   * 
   * @param csprofitcenterid 发货利润中心版本
   */
  public void setCsprofitcenterid(String csprofitcenterid) {
    this.setAttributeValue(SquareInvBVO.CSPROFITCENTERID, csprofitcenterid);
  }
}
