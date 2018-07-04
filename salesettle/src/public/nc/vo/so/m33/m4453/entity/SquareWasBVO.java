package nc.vo.so.m33.m4453.entity;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m33.enumeration.SquareType;

public class SquareWasBVO extends SuperVO {

  private static final long serialVersionUID = -7719302456054625866L;

  /**
   * 特征码
   */
  public static final String CMFFILEID = "cmffileid";
  
  /**
   * 客户物料编码
   */
  public static final String CCUSTMATERIALID = "ccustmaterialid";

  /**
   * 设置客户物料编码
   * 
   */
  public void setCcustmaterialid(String ccustmaterialid) {
    this.setAttributeValue(SquareWasBVO.CCUSTMATERIALID, ccustmaterialid);
  }

  /**
   * 获取客户物料编码
   * 
   * @return 客户物料编码
   */
  public String getCcustmaterialid() {
    return (String) this.getAttributeValue(SquareWasBVO.CCUSTMATERIALID);
  }

  // 途损单待结算清单子实体路径
  public static final String ENTITYNAME = "so.SquareWasBVO";

  // 删除标志dr
  public static final String DR = "dr";

  /**
   * 是否可以成本结算
   */
  public static final String BCOST = "bcost";

  /**
   * 是否可以收入结算
   */
  public static final String BINCOME = "bincome";

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
   * 开户银行账户
   */
  public static final String CCUSTBANKACCID = "ccustbankaccid";

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
   * 订单开票客户
   */
  public static final String CINVOICECUSTID = "cinvoicecustid";

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
   * 订单收付款协议
   */
  public static final String CPAYTERMID = "cpaytermid";

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
   * 途损结算单子实体
   */
  public static final String CSALESQUAREBID = "csalesquarebid";

  /**
   * csalesquaredid（可持续化字段，可以远程传递值，但不能保存到数据库）
   */
  public static final String CSALESQUAREDID = "csalesquaredid";

  /**
   * 途损待结算单主实体_主键
   */
  public static final String CSALESQUAREID = "csalesquareid";

  /**
   * 途损单子实体
   */
  public static final String CSQUAREBILLBID = "csquarebillbid";

  /**
   * 途损单主实体
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
   * 途损单单据日期
   */
  public static final String DBILLDATE = "dbilldate";

  /**
   * 途损单业务日期
   */
  public static final String DBIZDATE = "dbizdate";

  /**
   * 应收单起效日期
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
   * 单品折扣
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
   * 税额
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
   * 获取是否可以成本结算
   * 
   * @return 是否可以成本结算
   */
  public UFBoolean getBcost() {
    return (UFBoolean) this.getAttributeValue(SquareWasBVO.BCOST);
  }

  /**
   * 设置是否可以成本结算
   * 
   * @param bcost 是否可以成本结算
   */
  public void setBcost(UFBoolean bcost) {
    this.setAttributeValue(SquareWasBVO.BCOST, bcost);
  }

  /**
   * 获取是否可以收入结算
   * 
   * @return 是否可以收入结算
   */
  public UFBoolean getBincome() {
    return (UFBoolean) this.getAttributeValue(SquareWasBVO.BINCOME);
  }

  /**
   * 设置是否可以收入结算
   * 
   * @param bincome 是否可以收入结算
   */
  public void setBincome(UFBoolean bincome) {
    this.setAttributeValue(SquareWasBVO.BINCOME, bincome);
  }

  /**
   * 获取是否赠品
   * 
   * @return 是否赠品
   */
  public UFBoolean getBlargessflag() {
    return (UFBoolean) this.getAttributeValue(SquareWasBVO.BLARGESSFLAG);
  }

  /**
   * 设置是否赠品
   * 
   * @param blargessflag 是否赠品
   */
  public void setBlargessflag(UFBoolean blargessflag) {
    this.setAttributeValue(SquareWasBVO.BLARGESSFLAG, blargessflag);
  }

  /**
   * 获取是否应收结算完成
   * 
   * @return 是否应收结算完成
   */
  public UFBoolean getBsquarearfinish() {
    return (UFBoolean) this.getAttributeValue(SquareWasBVO.BSQUAREARFINISH);
  }

  /**
   * 设置是否应收结算完成
   * 
   * @param bsquarearfinish 是否应收结算完成
   */
  public void setBsquarearfinish(UFBoolean bsquarearfinish) {
    this.setAttributeValue(SquareWasBVO.BSQUAREARFINISH, bsquarearfinish);
  }

  /**
   * 获取是否成本结算完成
   * 
   * @return 是否成本结算完成
   */
  public UFBoolean getBsquareiafinish() {
    return (UFBoolean) this.getAttributeValue(SquareWasBVO.BSQUAREIAFINISH);
  }

  /**
   * 设置是否成本结算完成
   * 
   * @param bsquareiafinish 是否成本结算完成
   */
  public void setBsquareiafinish(UFBoolean bsquareiafinish) {
    this.setAttributeValue(SquareWasBVO.BSQUAREIAFINISH, bsquareiafinish);
  }

  /**
   * 获取应收组织最新版本
   * 
   * @return 应收组织最新版本
   */
  public String getCarorgid() {
    return (String) this.getAttributeValue(SquareWasBVO.CARORGID);
  }

  /**
   * 设置应收组织最新版本
   * 
   * @param carorgid 应收组织最新版本
   */
  public void setCarorgid(String carorgid) {
    this.setAttributeValue(SquareWasBVO.CARORGID, carorgid);
  }

  /**
   * 获取应收组织版本
   * 
   * @return 应收组织版本
   */
  public String getCarorgvid() {
    return (String) this.getAttributeValue(SquareWasBVO.CARORGVID);
  }

  /**
   * 设置应收组织版本
   * 
   * @param carorgvid 应收组织版本
   */
  public void setCarorgvid(String carorgvid) {
    this.setAttributeValue(SquareWasBVO.CARORGVID, carorgvid);
  }

  /**
   * 获取单位
   * 
   * @return 单位
   */
  public String getCastunitid() {
    return (String) this.getAttributeValue(SquareWasBVO.CASTUNITID);
  }

  /**
   * 设置单位
   * 
   * @param castunitid 单位
   */
  public void setCastunitid(String castunitid) {
    this.setAttributeValue(SquareWasBVO.CASTUNITID, castunitid);
  }

  /**
   * 获取订单渠道类型
   * 
   * @return 订单渠道类型
   */
  public String getCchanneltypeid() {
    return (String) this.getAttributeValue(SquareWasBVO.CCHANNELTYPEID);
  }

  /**
   * 设置订单渠道类型
   * 
   * @param cchanneltypeid 订单渠道类型
   */
  public void setCchanneltypeid(String cchanneltypeid) {
    this.setAttributeValue(SquareWasBVO.CCHANNELTYPEID, cchanneltypeid);
  }

  /**
   * 获取成本域
   * 
   * @return 成本域
   */
  public String getCcostorgid() {
    return (String) this.getAttributeValue(SquareWasBVO.CCOSTORGID);
  }

  /**
   * 设置成本域
   * 
   * @param ccostorgid 成本域
   */
  public void setCcostorgid(String ccostorgid) {
    this.setAttributeValue(SquareWasBVO.CCOSTORGID, ccostorgid);
  }

  /**
   * 获取本币
   * 
   * @return 本币
   */
  public String getCcurrencyid() {
    return (String) this.getAttributeValue(SquareWasBVO.CCURRENCYID);
  }

  /**
   * 设置本币
   * 
   * @param ccurrencyid 本币
   */
  public void setCcurrencyid(String ccurrencyid) {
    this.setAttributeValue(SquareWasBVO.CCURRENCYID, ccurrencyid);
  }

  /**
   * 获取开户银行账户
   * 
   * @return 开户银行账户
   */
  public String getCcustbankaccid() {
    return (String) this.getAttributeValue(SquareWasBVO.CCUSTBANKACCID);
  }

  /**
   * 设置开户银行账户
   * 
   * @param ccustbankaccid 开户银行账户
   */
  public void setCcustbankaccid(String ccustbankaccid) {
    this.setAttributeValue(SquareWasBVO.CCUSTBANKACCID, ccustbankaccid);
  }

  /**
   * 获取销售部门最新版本
   * 
   * @return 销售部门最新版本
   */
  public String getCdeptid() {
    return (String) this.getAttributeValue(SquareWasBVO.CDEPTID);
  }

  /**
   * 设置销售部门最新版本
   * 
   * @param cdeptid 销售部门最新版本
   */
  public void setCdeptid(String cdeptid) {
    this.setAttributeValue(SquareWasBVO.CDEPTID, cdeptid);
  }

  /**
   * 获取销售部门版本
   * 
   * @return 销售部门版本
   */
  public String getCdeptvid() {
    return (String) this.getAttributeValue(SquareWasBVO.CDEPTVID);
  }

  /**
   * 设置销售部门版本
   * 
   * @param cdeptvid 销售部门版本
   */
  public void setCdeptvid(String cdeptvid) {
    this.setAttributeValue(SquareWasBVO.CDEPTVID, cdeptvid);
  }

  /**
   * 获取销售业务员
   * 
   * @return 销售业务员
   */
  public String getCemployeeid() {
    return (String) this.getAttributeValue(SquareWasBVO.CEMPLOYEEID);
  }

  /**
   * 设置销售业务员
   * 
   * @param cemployeeid 销售业务员
   */
  public void setCemployeeid(String cemployeeid) {
    this.setAttributeValue(SquareWasBVO.CEMPLOYEEID, cemployeeid);
  }

  /**
   * 获取源头单据子表
   * 
   * @return 源头单据子表
   */
  public String getCfirstbid() {
    return (String) this.getAttributeValue(SquareWasBVO.CFIRSTBID);
  }

  /**
   * 设置源头单据子表
   * 
   * @param cfirstbid 源头单据子表
   */
  public void setCfirstbid(String cfirstbid) {
    this.setAttributeValue(SquareWasBVO.CFIRSTBID, cfirstbid);
  }

  /**
   * 获取源头单据主表
   * 
   * @return 源头单据主表
   */
  public String getCfirstid() {
    return (String) this.getAttributeValue(SquareWasBVO.CFIRSTID);
  }

  /**
   * 设置源头单据主表
   * 
   * @param cfirstid 源头单据主表
   */
  public void setCfirstid(String cfirstid) {
    this.setAttributeValue(SquareWasBVO.CFIRSTID, cfirstid);
  }

  /**
   * 获取散户
   * 
   * @return 散户
   */
  public String getCfreecustid() {
    return (String) this.getAttributeValue(SquareWasBVO.CFREECUSTID);
  }

  /**
   * 设置散户
   * 
   * @param cfreecustid 散户
   */
  public void setCfreecustid(String cfreecustid) {
    this.setAttributeValue(SquareWasBVO.CFREECUSTID, cfreecustid);
  }

  /**
   * 获取订单开票客户
   * 
   * @return 订单开票客户
   */
  public String getCinvoicecustid() {
    return (String) this.getAttributeValue(SquareWasBVO.CINVOICECUSTID);
  }

  /**
   * 设置订单开票客户
   * 
   * @param cinvoicecustid 订单开票客户
   */
  public void setCinvoicecustid(String cinvoicecustid) {
    this.setAttributeValue(SquareWasBVO.CINVOICECUSTID, cinvoicecustid);
  }

  /**
   * 获取物料
   * 
   * @return 物料
   */
  public String getCmaterialid() {
    return (String) this.getAttributeValue(SquareWasBVO.CMATERIALID);
  }

  /**
   * 设置物料
   * 
   * @param cmaterialid 物料
   */
  public void setCmaterialid(String cmaterialid) {
    this.setAttributeValue(SquareWasBVO.CMATERIALID, cmaterialid);
  }

  /**
   * 获取物料版本
   * 
   * @return 物料版本
   */
  public String getCmaterialvid() {
    return (String) this.getAttributeValue(SquareWasBVO.CMATERIALVID);
  }

  /**
   * 设置物料版本
   * 
   * @param cmaterialvid 物料版本
   */
  public void setCmaterialvid(String cmaterialvid) {
    this.setAttributeValue(SquareWasBVO.CMATERIALVID, cmaterialvid);
  }

  /**
   * 获取订单客户
   * 
   * @return 订单客户
   */
  public String getCordercustid() {
    return (String) this.getAttributeValue(SquareWasBVO.CORDERCUSTID);
  }

  /**
   * 设置订单客户
   * 
   * @param cordercustid 订单客户
   */
  public void setCordercustid(String cordercustid) {
    this.setAttributeValue(SquareWasBVO.CORDERCUSTID, cordercustid);
  }

  /**
   * 获取原币
   * 
   * @return 原币
   */
  public String getCorigcurrencyid() {
    return (String) this.getAttributeValue(SquareWasBVO.CORIGCURRENCYID);
  }

  /**
   * 设置原币
   * 
   * @param corigcurrencyid 原币
   */
  public void setCorigcurrencyid(String corigcurrencyid) {
    this.setAttributeValue(SquareWasBVO.CORIGCURRENCYID, corigcurrencyid);
  }

  /**
   * 获取订单收付款协议
   * 
   * @return 订单收付款协议
   */
  public String getCpaytermid() {
    return (String) this.getAttributeValue(SquareWasBVO.CPAYTERMID);
  }

  /**
   * 设置订单收付款协议
   * 
   * @param cpaytermid 订单收付款协议
   */
  public void setCpaytermid(String cpaytermid) {
    this.setAttributeValue(SquareWasBVO.CPAYTERMID, cpaytermid);
  }

  /**
   * 获取生产厂商
   * 
   * @return 生产厂商
   */
  public String getCproductorid() {
    return (String) this.getAttributeValue(SquareWasBVO.CPRODUCTORID);
  }

  /**
   * 设置生产厂商
   * 
   * @param cproductorid 生产厂商
   */
  public void setCproductorid(String cproductorid) {
    this.setAttributeValue(SquareWasBVO.CPRODUCTORID, cproductorid);
  }

  /**
   * 获取利润中心最新版本
   * 
   * @return 利润中心最新版本
   */
  public String getCprofitcenterid() {
    return (String) this.getAttributeValue(SquareWasBVO.CPROFITCENTERID);
  }

  /**
   * 设置利润中心最新版本
   * 
   * @param cprofitcenterid 利润中心最新版本
   */
  public void setCprofitcenterid(String cprofitcenterid) {
    this.setAttributeValue(SquareWasBVO.CPROFITCENTERID, cprofitcenterid);
  }

  /**
   * 获取利润中心版本
   * 
   * @return 利润中心版本
   */
  public String getCprofitcentervid() {
    return (String) this.getAttributeValue(SquareWasBVO.CPROFITCENTERVID);
  }

  /**
   * 设置利润中心版本
   * 
   * @param cprofitcentervid 利润中心版本
   */
  public void setCprofitcentervid(String cprofitcentervid) {
    this.setAttributeValue(SquareWasBVO.CPROFITCENTERVID, cprofitcentervid);
  }

  /**
   * 获取项目ID
   * 
   * @return 项目ID
   */
  public String getCprojectid() {
    return (String) this.getAttributeValue(SquareWasBVO.CPROJECTID);
  }

  /**
   * 设置项目ID
   * 
   * @param cprojectid 项目ID
   */
  public void setCprojectid(String cprojectid) {
    this.setAttributeValue(SquareWasBVO.CPROJECTID, cprojectid);
  }

  /**
   * 获取产品线
   * 
   * @return 产品线
   */
  public String getCprolineid() {
    return (String) this.getAttributeValue(SquareWasBVO.CPROLINEID);
  }

  /**
   * 设置产品线
   * 
   * @param cprolineid 产品线
   */
  public void setCprolineid(String cprolineid) {
    this.setAttributeValue(SquareWasBVO.CPROLINEID, cprolineid);
  }

  /**
   * 获取销售组织最新版本
   * 
   * @return 销售组织最新版本
   */
  public String getCsaleorgid() {
    return (String) this.getAttributeValue(SquareWasBVO.CSALEORGID);
  }

  /**
   * 设置销售组织最新版本
   * 
   * @param csaleorgid 销售组织最新版本
   */
  public void setCsaleorgid(String csaleorgid) {
    this.setAttributeValue(SquareWasBVO.CSALEORGID, csaleorgid);
  }

  /**
   * 获取销售组织版本
   * 
   * @return 销售组织版本
   */
  public String getCsaleorgvid() {
    return (String) this.getAttributeValue(SquareWasBVO.CSALEORGVID);
  }

  /**
   * 设置销售组织版本
   * 
   * @param csaleorgvid 销售组织版本
   */
  public void setCsaleorgvid(String csaleorgvid) {
    this.setAttributeValue(SquareWasBVO.CSALEORGVID, csaleorgvid);
  }

  /**
   * 获取途损结算单子实体
   * 
   * @return 途损结算单子实体
   */
  public String getCsalesquarebid() {
    return (String) this.getAttributeValue(SquareWasBVO.CSALESQUAREBID);
  }

  /**
   * 设置途损结算单子实体
   * 
   * @param csalesquarebid 途损结算单子实体
   */
  public void setCsalesquarebid(String csalesquarebid) {
    this.setAttributeValue(SquareWasBVO.CSALESQUAREBID, csalesquarebid);
  }

  /**
   * 获取csalesquaredid（可持续化字段，可以远程传递值，但不能保存到数据库）
   * 
   * @return csalesquaredid
   */
  public String getCsalesquaredid() {
    return (String) this.getAttributeValue(SquareWasBVO.CSALESQUAREDID);
  }

  /**
   * 设置csalesquaredid（可持续化字段，可以远程传递值，但不能保存到数据库）
   * 
   * @param csalesquaredid csalesquaredid
   */
  public void setCsalesquaredid(String csalesquaredid) {
    this.setAttributeValue(SquareWasBVO.CSALESQUAREDID, csalesquaredid);
  }

  /**
   * 获取途损待结算单主实体_主键
   * 
   * @return 途损待结算单主实体_主键
   */
  public String getCsalesquareid() {
    return (String) this.getAttributeValue(SquareWasBVO.CSALESQUAREID);
  }

  /**
   * 设置途损待结算单主实体_主键
   * 
   * @param csalesquareid 途损待结算单主实体_主键
   */
  public void setCsalesquareid(String csalesquareid) {
    this.setAttributeValue(SquareWasBVO.CSALESQUAREID, csalesquareid);
  }

  /**
   * 获取途损单子实体
   * 
   * @return 途损单子实体
   */
  public String getCsquarebillbid() {
    return (String) this.getAttributeValue(SquareWasBVO.CSQUAREBILLBID);
  }

  /**
   * 设置途损单子实体
   * 
   * @param csquarebillbid 途损单子实体
   */
  public void setCsquarebillbid(String csquarebillbid) {
    this.setAttributeValue(SquareWasBVO.CSQUAREBILLBID, csquarebillbid);
  }

  /**
   * 获取途损单主实体
   * 
   * @return 途损单主实体
   */
  public String getCsquarebillid() {
    return (String) this.getAttributeValue(SquareWasBVO.CSQUAREBILLID);
  }

  /**
   * 设置途损单主实体
   * 
   * @param csquarebillid 途损单主实体
   */
  public void setCsquarebillid(String csquarebillid) {
    this.setAttributeValue(SquareWasBVO.CSQUAREBILLID, csquarebillid);
  }

  /**
   * 获取来源单据子表
   * 
   * @return 来源单据子表
   */
  public String getCsrcbid() {
    return (String) this.getAttributeValue(SquareWasBVO.CSRCBID);
  }

  /**
   * 设置来源单据子表
   * 
   * @param csrcbid 来源单据子表
   */
  public void setCsrcbid(String csrcbid) {
    this.setAttributeValue(SquareWasBVO.CSRCBID, csrcbid);
  }

  /**
   * 获取来源单据主表
   * 
   * @return 来源单据主表
   */
  public String getCsrcid() {
    return (String) this.getAttributeValue(SquareWasBVO.CSRCID);
  }

  /**
   * 设置来源单据主表
   * 
   * @param csrcid 来源单据主表
   */
  public void setCsrcid(String csrcid) {
    this.setAttributeValue(SquareWasBVO.CSRCID, csrcid);
  }

  /**
   * 获取税码
   * 
   * @return 税码
   */
  public String getCtaxcodeid() {
    return (String) this.getAttributeValue(SquareWasBVO.CTAXCODEID);
  }

  /**
   * 设置税码
   * 
   * @param ctaxcodeid 税码
   */
  public void setCtaxcodeid(String ctaxcodeid) {
    this.setAttributeValue(SquareWasBVO.CTAXCODEID, ctaxcodeid);
  }

  /**
   * 获取主单位
   * 
   * @return 主单位
   */
  public String getCunitid() {
    return (String) this.getAttributeValue(SquareWasBVO.CUNITID);
  }

  /**
   * 设置主单位
   * 
   * @param cunitid 主单位
   */
  public void setCunitid(String cunitid) {
    this.setAttributeValue(SquareWasBVO.CUNITID, cunitid);
  }

  /**
   * 获取供应商
   * 
   * @return 供应商
   */
  public String getCvendorid() {
    return (String) this.getAttributeValue(SquareWasBVO.CVENDORID);
  }

  /**
   * 设置供应商
   * 
   * @param cvendorid 供应商
   */
  public void setCvendorid(String cvendorid) {
    this.setAttributeValue(SquareWasBVO.CVENDORID, cvendorid);
  }

  /**
   * 获取途损单单据日期
   * 
   * @return 途损单单据日期
   */
  public UFDate getDbilldate() {
    return (UFDate) this.getAttributeValue(SquareWasBVO.DBILLDATE);
  }

  /**
   * 设置途损单单据日期
   * 
   * @param dbilldate 途损单单据日期
   */
  public void setDbilldate(UFDate dbilldate) {
    this.setAttributeValue(SquareWasBVO.DBILLDATE, dbilldate);
  }

  /**
   * 获取途损单业务日期
   * 
   * @return 途损单业务日期
   */
  public UFDate getDbizdate() {
    return (UFDate) this.getAttributeValue(SquareWasBVO.DBIZDATE);
  }

  /**
   * 设置途损单业务日期
   * 
   * @param dbizdate 途损单业务日期
   */
  public void setDbizdate(UFDate dbizdate) {
    this.setAttributeValue(SquareWasBVO.DBIZDATE, dbizdate);
  }

  /**
   * 获取应收单起效日期
   * 
   * @return 应收单起效日期
   */
  public UFDate getDeffectdate() {
    return (UFDate) this.getAttributeValue(SquareWasBVO.DEFFECTDATE);
  }

  /**
   * 设置应收单起效日期
   * 
   * @param deffectdate 应收单起效日期
   */
  public void setDeffectdate(UFDate deffectdate) {
    this.setAttributeValue(SquareWasBVO.DEFFECTDATE, deffectdate);
  }

  /**
   * 获取待收入结算类型
   * 
   * @return 待收入结算类型
   * @see SquareType
   */
  public Integer getFpreartype() {
    return (Integer) this.getAttributeValue(SquareWasBVO.FPREARTYPE);
  }

  /**
   * 设置待收入结算类型
   * 
   * @param fpreartype 待收入结算类型
   * @see SquareType
   */
  public void setFpreartype(Integer fpreartype) {
    this.setAttributeValue(SquareWasBVO.FPREARTYPE, fpreartype);
  }

  /**
   * 获取待成本结算类型
   * 
   * @return 待成本结算类型
   * @see SquareType
   */
  public Integer getFpreiatype() {
    return (Integer) this.getAttributeValue(SquareWasBVO.FPREIATYPE);
  }

  /**
   * 设置待成本结算类型
   * 
   * @param fpreiatype 待成本结算类型
   * @see SquareType
   */
  public void setFpreiatype(Integer fpreiatype) {
    this.setAttributeValue(SquareWasBVO.FPREIATYPE, fpreiatype);
  }

  /**
   * 获取扣税类别
   * 
   * @return 扣税类别
   */
  public Integer getFtaxtypeflag() {
    return (Integer) this.getAttributeValue(SquareWasBVO.FTAXTYPEFLAG);
  }

  /**
   * 设置扣税类别
   * 
   * @param ftaxtypeflag 扣税类别
   */
  public void setFtaxtypeflag(Integer ftaxtypeflag) {
    this.setAttributeValue(SquareWasBVO.FTAXTYPEFLAG, ftaxtypeflag);
  }

  /**
   * 获取累计回冲应收数量
   * 
   * @return 累计回冲应收数量
   */
  public UFDouble getNarrushnum() {
    return (UFDouble) this.getAttributeValue(SquareWasBVO.NARRUSHNUM);
  }

  /**
   * 设置累计回冲应收数量
   * 
   * @param narrushnum 累计回冲应收数量
   */
  public void setNarrushnum(UFDouble narrushnum) {
    this.setAttributeValue(SquareWasBVO.NARRUSHNUM, narrushnum);
  }

  /**
   * 获取单位数量
   * 
   * @return 单位数量
   */
  public UFDouble getNastnum() {
    return (UFDouble) this.getAttributeValue(SquareWasBVO.NASTNUM);
  }

  /**
   * 设置单位数量
   * 
   * @param nastnum 单位数量
   */
  public void setNastnum(UFDouble nastnum) {
    this.setAttributeValue(SquareWasBVO.NASTNUM, nastnum);
  }

  /**
   * 获取计税金额
   * 
   * @return 计税金额
   */
  public UFDouble getNcaltaxmny() {
    return (UFDouble) this.getAttributeValue(SquareWasBVO.NCALTAXMNY);
  }

  /**
   * 设置计税金额
   * 
   * @param ncaltaxmny 计税金额
   */
  public void setNcaltaxmny(UFDouble ncaltaxmny) {
    this.setAttributeValue(SquareWasBVO.NCALTAXMNY, ncaltaxmny);
  }

  /**
   * 获取本币折扣额
   * 
   * @return 本币折扣额
   */
  public UFDouble getNdiscount() {
    return (UFDouble) this.getAttributeValue(SquareWasBVO.NDISCOUNT);
  }

  /**
   * 设置本币折扣额
   * 
   * @param ndiscount 本币折扣额
   */
  public void setNdiscount(UFDouble ndiscount) {
    this.setAttributeValue(SquareWasBVO.NDISCOUNT, ndiscount);
  }

  /**
   * 获取折本汇率
   * 
   * @return 折本汇率
   */
  public UFDouble getNexchangerate() {
    return (UFDouble) this.getAttributeValue(SquareWasBVO.NEXCHANGERATE);
  }

  /**
   * 设置折本汇率
   * 
   * @param nexchangerate 折本汇率
   */
  public void setNexchangerate(UFDouble nexchangerate) {
    this.setAttributeValue(SquareWasBVO.NEXCHANGERATE, nexchangerate);
  }

  /**
   * 获取全局本位币汇率
   * 
   * @return 全局本位币汇率
   */
  public UFDouble getNglobalexchgrate() {
    return (UFDouble) this.getAttributeValue(SquareWasBVO.NGLOBALEXCHGRATE);
  }

  /**
   * 设置全局本位币汇率
   * 
   * @param nglobalexchgrate 全局本位币汇率
   */
  public void setNglobalexchgrate(UFDouble nglobalexchgrate) {
    this.setAttributeValue(SquareWasBVO.NGLOBALEXCHGRATE, nglobalexchgrate);
  }

  /**
   * 获取全局本币无税金额
   * 
   * @return 全局本币无税金额
   */
  public UFDouble getNglobalmny() {
    return (UFDouble) this.getAttributeValue(SquareWasBVO.NGLOBALMNY);
  }

  /**
   * 设置全局本币无税金额
   * 
   * @param nglobalmny 全局本币无税金额
   */
  public void setNglobalmny(UFDouble nglobalmny) {
    this.setAttributeValue(SquareWasBVO.NGLOBALMNY, nglobalmny);
  }

  /**
   * 获取全局本币价税合计
   * 
   * @return 全局本币价税合计
   */
  public UFDouble getNglobaltaxmny() {
    return (UFDouble) this.getAttributeValue(SquareWasBVO.NGLOBALTAXMNY);
  }

  /**
   * 设置全局本币价税合计
   * 
   * @param nglobaltaxmny 全局本币价税合计
   */
  public void setNglobaltaxmny(UFDouble nglobaltaxmny) {
    this.setAttributeValue(SquareWasBVO.NGLOBALTAXMNY, nglobaltaxmny);
  }

  /**
   * 获取集团本位币汇率
   * 
   * @return 集团本位币汇率
   */
  public UFDouble getNgroupexchgrate() {
    return (UFDouble) this.getAttributeValue(SquareWasBVO.NGROUPEXCHGRATE);
  }

  /**
   * 设置集团本位币汇率
   * 
   * @param ngroupexchgrate 集团本位币汇率
   */
  public void setNgroupexchgrate(UFDouble ngroupexchgrate) {
    this.setAttributeValue(SquareWasBVO.NGROUPEXCHGRATE, ngroupexchgrate);
  }

  /**
   * 获取集团本币无税金额
   * 
   * @return 集团本币无税金额
   */
  public UFDouble getNgroupmny() {
    return (UFDouble) this.getAttributeValue(SquareWasBVO.NGROUPMNY);
  }

  /**
   * 设置集团本币无税金额
   * 
   * @param ngroupmny 集团本币无税金额
   */
  public void setNgroupmny(UFDouble ngroupmny) {
    this.setAttributeValue(SquareWasBVO.NGROUPMNY, ngroupmny);
  }

  /**
   * 获取集团本币价税合计
   * 
   * @return 集团本币价税合计
   */
  public UFDouble getNgrouptaxmny() {
    return (UFDouble) this.getAttributeValue(SquareWasBVO.NGROUPTAXMNY);
  }

  /**
   * 设置集团本币价税合计
   * 
   * @param ngrouptaxmny 集团本币价税合计
   */
  public void setNgrouptaxmny(UFDouble ngrouptaxmny) {
    this.setAttributeValue(SquareWasBVO.NGROUPTAXMNY, ngrouptaxmny);
  }

  /**
   * 获取单品折扣率
   * 
   * @return 单品折扣率
   */
  public UFDouble getNitemdiscountrate() {
    return (UFDouble) this.getAttributeValue(SquareWasBVO.NITEMDISCOUNTRATE);
  }

  /**
   * 设置单品折扣率
   * 
   * @param nitemdiscountrate 单品折扣率
   */
  public void setNitemdiscountrate(UFDouble nitemdiscountrate) {
    this.setAttributeValue(SquareWasBVO.NITEMDISCOUNTRATE, nitemdiscountrate);
  }

  /**
   * 获取本币无税金额
   * 
   * @return 本币无税金额
   */
  public UFDouble getNmny() {
    return (UFDouble) this.getAttributeValue(SquareWasBVO.NMNY);
  }

  /**
   * 设置本币无税金额
   * 
   * @param nmny 本币无税金额
   */
  public void setNmny(UFDouble nmny) {
    this.setAttributeValue(SquareWasBVO.NMNY, nmny);
  }

  /**
   * 获取本币无税净价
   * 
   * @return 本币无税净价
   */
  public UFDouble getNnetprice() {
    return (UFDouble) this.getAttributeValue(SquareWasBVO.NNETPRICE);
  }

  /**
   * 设置本币无税净价
   * 
   * @param nnetprice 本币无税净价
   */
  public void setNnetprice(UFDouble nnetprice) {
    this.setAttributeValue(SquareWasBVO.NNETPRICE, nnetprice);
  }

  /**
   * 获取主单位数量
   * 
   * @return 主单位数量
   */
  public UFDouble getNnum() {
    return (UFDouble) this.getAttributeValue(SquareWasBVO.NNUM);
  }

  /**
   * 设置主单位数量
   * 
   * @param nnum 主单位数量
   */
  public void setNnum(UFDouble nnum) {
    this.setAttributeValue(SquareWasBVO.NNUM, nnum);
  }

  /**
   * 获取原币折扣额
   * 
   * @return 原币折扣额
   */
  public UFDouble getNorigdiscount() {
    return (UFDouble) this.getAttributeValue(SquareWasBVO.NORIGDISCOUNT);
  }

  /**
   * 设置原币折扣额
   * 
   * @param norigdiscount 原币折扣额
   */
  public void setNorigdiscount(UFDouble norigdiscount) {
    this.setAttributeValue(SquareWasBVO.NORIGDISCOUNT, norigdiscount);
  }

  /**
   * 获取原币无税金额
   * 
   * @return 原币无税金额
   */
  public UFDouble getNorigmny() {
    return (UFDouble) this.getAttributeValue(SquareWasBVO.NORIGMNY);
  }

  /**
   * 设置原币无税金额
   * 
   * @param norigmny 原币无税金额
   */
  public void setNorigmny(UFDouble norigmny) {
    this.setAttributeValue(SquareWasBVO.NORIGMNY, norigmny);
  }

  /**
   * 获取原币无税净价
   * 
   * @return 原币无税净价
   */
  public UFDouble getNorignetprice() {
    return (UFDouble) this.getAttributeValue(SquareWasBVO.NORIGNETPRICE);
  }

  /**
   * 设置原币无税净价
   * 
   * @param norignetprice 原币无税净价
   */
  public void setNorignetprice(UFDouble norignetprice) {
    this.setAttributeValue(SquareWasBVO.NORIGNETPRICE, norignetprice);
  }

  /**
   * 获取原币无税单价
   * 
   * @return 原币无税单价
   */
  public UFDouble getNorigprice() {
    return (UFDouble) this.getAttributeValue(SquareWasBVO.NORIGPRICE);
  }

  /**
   * 设置原币无税单价
   * 
   * @param norigprice 原币无税单价
   */
  public void setNorigprice(UFDouble norigprice) {
    this.setAttributeValue(SquareWasBVO.NORIGPRICE, norigprice);
  }

  /**
   * 获取原币价税合计
   * 
   * @return 原币价税合计
   */
  public UFDouble getNorigtaxmny() {
    return (UFDouble) this.getAttributeValue(SquareWasBVO.NORIGTAXMNY);
  }

  /**
   * 设置原币价税合计
   * 
   * @param norigtaxmny 原币价税合计
   */
  public void setNorigtaxmny(UFDouble norigtaxmny) {
    this.setAttributeValue(SquareWasBVO.NORIGTAXMNY, norigtaxmny);
  }

  /**
   * 获取原币含税净价
   * 
   * @return 原币含税净价
   */
  public UFDouble getNorigtaxnetprice() {
    return (UFDouble) this.getAttributeValue(SquareWasBVO.NORIGTAXNETPRICE);
  }

  /**
   * 设置原币含税净价
   * 
   * @param norigtaxnetprice 原币含税净价
   */
  public void setNorigtaxnetprice(UFDouble norigtaxnetprice) {
    this.setAttributeValue(SquareWasBVO.NORIGTAXNETPRICE, norigtaxnetprice);
  }

  /**
   * 获取原币含税单价
   * 
   * @return 原币含税单价
   */
  public UFDouble getNorigtaxprice() {
    return (UFDouble) this.getAttributeValue(SquareWasBVO.NORIGTAXPRICE);
  }

  /**
   * 设置原币含税单价
   * 
   * @param norigtaxprice 原币含税单价
   */
  public void setNorigtaxprice(UFDouble norigtaxprice) {
    this.setAttributeValue(SquareWasBVO.NORIGTAXPRICE, norigtaxprice);
  }

  /**
   * 获取本币无税单价
   * 
   * @return 本币无税单价
   */
  public UFDouble getNprice() {
    return (UFDouble) this.getAttributeValue(SquareWasBVO.NPRICE);
  }

  /**
   * 设置本币无税单价
   * 
   * @param nprice 本币无税单价
   */
  public void setNprice(UFDouble nprice) {
    this.setAttributeValue(SquareWasBVO.NPRICE, nprice);
  }

  /**
   * 获取累计确认应收数量
   * 
   * @return 累计确认应收数量
   */
  public UFDouble getNsquarearnum() {
    return (UFDouble) this.getAttributeValue(SquareWasBVO.NSQUAREARNUM);
  }

  /**
   * 设置累计确认应收数量
   * 
   * @param nsquarearnum 累计确认应收数量
   */
  public void setNsquarearnum(UFDouble nsquarearnum) {
    this.setAttributeValue(SquareWasBVO.NSQUAREARNUM, nsquarearnum);
  }

  /**
   * 获取累计成本结算数量
   * 
   * @return 累计成本结算数量
   */
  public UFDouble getNsquareianum() {
    return (UFDouble) this.getAttributeValue(SquareWasBVO.NSQUAREIANUM);
  }

  /**
   * 设置累计成本结算数量
   * 
   * @param nsquareianum 累计成本结算数量
   */
  public void setNsquareianum(UFDouble nsquareianum) {
    this.setAttributeValue(SquareWasBVO.NSQUAREIANUM, nsquareianum);
  }

  /**
   * 获取累计发出商品数量
   * 
   * @return 累计发出商品数量
   */
  public UFDouble getNsquareregnum() {
    return (UFDouble) this.getAttributeValue(SquareWasBVO.NSQUAREREGNUM);
  }

  /**
   * 设置累计发出商品数量
   * 
   * @param nsquareregnum 累计发出商品数量
   */
  public void setNsquareregnum(UFDouble nsquareregnum) {
    this.setAttributeValue(SquareWasBVO.NSQUAREREGNUM, nsquareregnum);
  }

  /**
   * 获取本币税额
   * 
   * @return 本币税额
   */
  public UFDouble getNtax() {
    return (UFDouble) this.getAttributeValue(SquareWasBVO.NTAX);
  }

  /**
   * 设置本币税额
   * 
   * @param ntax 本币税额
   */
  public void setNtax(UFDouble ntax) {
    this.setAttributeValue(SquareWasBVO.NTAX, ntax);
  }

  /**
   * 获取本币价税合计
   * 
   * @return 本币价税合计
   */
  public UFDouble getNtaxmny() {
    return (UFDouble) this.getAttributeValue(SquareWasBVO.NTAXMNY);
  }

  /**
   * 设置本币价税合计
   * 
   * @param ntaxmny 本币价税合计
   */
  public void setNtaxmny(UFDouble ntaxmny) {
    this.setAttributeValue(SquareWasBVO.NTAXMNY, ntaxmny);
  }

  /**
   * 获取本币含税净价
   * 
   * @return 本币含税净价
   */
  public UFDouble getNtaxnetprice() {
    return (UFDouble) this.getAttributeValue(SquareWasBVO.NTAXNETPRICE);
  }

  /**
   * 设置本币含税净价
   * 
   * @param ntaxnetprice 本币含税净价
   */
  public void setNtaxnetprice(UFDouble ntaxnetprice) {
    this.setAttributeValue(SquareWasBVO.NTAXNETPRICE, ntaxnetprice);
  }

  /**
   * 获取本币含税单价
   * 
   * @return 本币含税单价
   */
  public UFDouble getNtaxprice() {
    return (UFDouble) this.getAttributeValue(SquareWasBVO.NTAXPRICE);
  }

  /**
   * 设置本币含税单价
   * 
   * @param ntaxprice 本币含税单价
   */
  public void setNtaxprice(UFDouble ntaxprice) {
    this.setAttributeValue(SquareWasBVO.NTAXPRICE, ntaxprice);
  }

  /**
   * 获取税率
   * 
   * @return 税率
   */
  public UFDouble getNtaxrate() {
    return (UFDouble) this.getAttributeValue(SquareWasBVO.NTAXRATE);
  }

  /**
   * 设置税率
   * 
   * @param ntaxrate 税率
   */
  public void setNtaxrate(UFDouble ntaxrate) {
    this.setAttributeValue(SquareWasBVO.NTAXRATE, ntaxrate);
  }

  /**
   * 获取nthisnum（可持续化字段，可以远程传递值，但不能保存到数据库）
   * 
   * @return nthisnum
   */
  public UFDouble getNthisnum() {
    return (UFDouble) this.getAttributeValue(SquareWasBVO.NTHISNUM);
  }

  /**
   * 设置nthisnum（可持续化字段，可以远程传递值，但不能保存到数据库）
   * 
   * @param nthisnum nthisnum
   */
  public void setNthisnum(UFDouble nthisnum) {
    this.setAttributeValue(SquareWasBVO.NTHISNUM, nthisnum);
  }

  /**
   * 获取批次号档案
   * 
   * @return 批次号档案
   */
  public String getPk_batchcode() {
    return (String) this.getAttributeValue(SquareWasBVO.PK_BATCHCODE);
  }

  /**
   * 设置批次号档案
   * 
   * @param pk_batchcode 批次号档案
   */
  public void setPk_batchcode(String pk_batchcode) {
    this.setAttributeValue(SquareWasBVO.PK_BATCHCODE, pk_batchcode);
  }

  /**
   * 获取集团
   * 
   * @return 集团
   */
  public String getPk_group() {
    return (String) this.getAttributeValue(SquareWasBVO.PK_GROUP);
  }

  /**
   * 设置集团
   * 
   * @param pk_group 集团
   */
  public void setPk_group(String pk_group) {
    this.setAttributeValue(SquareWasBVO.PK_GROUP, pk_group);
  }

  /**
   * 获取结算财务组织
   * 
   * @return 结算财务组织
   */
  public String getPk_org() {
    return (String) this.getAttributeValue(SquareWasBVO.PK_ORG);
  }

  /**
   * 设置结算财务组织
   * 
   * @param pk_org 结算财务组织
   */
  public void setPk_org(String pk_org) {
    this.setAttributeValue(SquareWasBVO.PK_ORG, pk_org);
  }

  /**
   * 获取processeid（可持续化字段，可以远程传递值，但不能保存到数据库）
   * 
   * @return processeid
   */
  public String getProcesseid() {
    return (String) this.getAttributeValue(SquareWasBVO.PROCESSEID);
  }

  /**
   * 设置processeid（可持续化字段，可以远程传递值，但不能保存到数据库）
   * 
   * @param processeid processeid
   */
  public void setProcesseid(String processeid) {
    this.setAttributeValue(SquareWasBVO.PROCESSEID, processeid);
  }

  /**
   * 获取时间戳
   * 
   * @return 时间戳
   */
  public UFDateTime getTs() {
    return (UFDateTime) this.getAttributeValue(SquareWasBVO.TS);
  }

  /**
   * 设置时间戳
   * 
   * @param ts 时间戳
   */
  public void setTs(UFDateTime ts) {
    this.setAttributeValue(SquareWasBVO.TS, ts);
  }

  /**
   * 获取批次号
   * 
   * @return 批次号
   */
  public String getVbatchcode() {
    return (String) this.getAttributeValue(SquareWasBVO.VBATCHCODE);
  }

  /**
   * 设置批次号
   * 
   * @param vbatchcode 批次号
   */
  public void setVbatchcode(String vbatchcode) {
    this.setAttributeValue(SquareWasBVO.VBATCHCODE, vbatchcode);
  }

  /**
   * 获取自定义项1
   * 
   * @return 自定义项1
   */
  public String getVbdef1() {
    return (String) this.getAttributeValue(SquareWasBVO.VBDEF1);
  }

  /**
   * 设置自定义项1
   * 
   * @param vbdef1 自定义项1
   */
  public void setVbdef1(String vbdef1) {
    this.setAttributeValue(SquareWasBVO.VBDEF1, vbdef1);
  }

  /**
   * 获取自定义项10
   * 
   * @return 自定义项10
   */
  public String getVbdef10() {
    return (String) this.getAttributeValue(SquareWasBVO.VBDEF10);
  }

  /**
   * 设置自定义项10
   * 
   * @param vbdef10 自定义项10
   */
  public void setVbdef10(String vbdef10) {
    this.setAttributeValue(SquareWasBVO.VBDEF10, vbdef10);
  }

  /**
   * 获取自定义项11
   * 
   * @return 自定义项11
   */
  public String getVbdef11() {
    return (String) this.getAttributeValue(SquareWasBVO.VBDEF11);
  }

  /**
   * 设置自定义项11
   * 
   * @param vbdef11 自定义项11
   */
  public void setVbdef11(String vbdef11) {
    this.setAttributeValue(SquareWasBVO.VBDEF11, vbdef11);
  }

  /**
   * 获取自定义项12
   * 
   * @return 自定义项12
   */
  public String getVbdef12() {
    return (String) this.getAttributeValue(SquareWasBVO.VBDEF12);
  }

  /**
   * 设置自定义项12
   * 
   * @param vbdef12 自定义项12
   */
  public void setVbdef12(String vbdef12) {
    this.setAttributeValue(SquareWasBVO.VBDEF12, vbdef12);
  }

  /**
   * 获取自定义项13
   * 
   * @return 自定义项13
   */
  public String getVbdef13() {
    return (String) this.getAttributeValue(SquareWasBVO.VBDEF13);
  }

  /**
   * 设置自定义项13
   * 
   * @param vbdef13 自定义项13
   */
  public void setVbdef13(String vbdef13) {
    this.setAttributeValue(SquareWasBVO.VBDEF13, vbdef13);
  }

  /**
   * 获取自定义项14
   * 
   * @return 自定义项14
   */
  public String getVbdef14() {
    return (String) this.getAttributeValue(SquareWasBVO.VBDEF14);
  }

  /**
   * 设置自定义项14
   * 
   * @param vbdef14 自定义项14
   */
  public void setVbdef14(String vbdef14) {
    this.setAttributeValue(SquareWasBVO.VBDEF14, vbdef14);
  }

  /**
   * 获取自定义项15
   * 
   * @return 自定义项15
   */
  public String getVbdef15() {
    return (String) this.getAttributeValue(SquareWasBVO.VBDEF15);
  }

  /**
   * 设置自定义项15
   * 
   * @param vbdef15 自定义项15
   */
  public void setVbdef15(String vbdef15) {
    this.setAttributeValue(SquareWasBVO.VBDEF15, vbdef15);
  }

  /**
   * 获取自定义项16
   * 
   * @return 自定义项16
   */
  public String getVbdef16() {
    return (String) this.getAttributeValue(SquareWasBVO.VBDEF16);
  }

  /**
   * 设置自定义项16
   * 
   * @param vbdef16 自定义项16
   */
  public void setVbdef16(String vbdef16) {
    this.setAttributeValue(SquareWasBVO.VBDEF16, vbdef16);
  }

  /**
   * 获取自定义项17
   * 
   * @return 自定义项17
   */
  public String getVbdef17() {
    return (String) this.getAttributeValue(SquareWasBVO.VBDEF17);
  }

  /**
   * 设置自定义项17
   * 
   * @param vbdef17 自定义项17
   */
  public void setVbdef17(String vbdef17) {
    this.setAttributeValue(SquareWasBVO.VBDEF17, vbdef17);
  }

  /**
   * 获取自定义项18
   * 
   * @return 自定义项18
   */
  public String getVbdef18() {
    return (String) this.getAttributeValue(SquareWasBVO.VBDEF18);
  }

  /**
   * 设置自定义项18
   * 
   * @param vbdef18 自定义项18
   */
  public void setVbdef18(String vbdef18) {
    this.setAttributeValue(SquareWasBVO.VBDEF18, vbdef18);
  }

  /**
   * 获取自定义项19
   * 
   * @return 自定义项19
   */
  public String getVbdef19() {
    return (String) this.getAttributeValue(SquareWasBVO.VBDEF19);
  }

  /**
   * 设置自定义项19
   * 
   * @param vbdef19 自定义项19
   */
  public void setVbdef19(String vbdef19) {
    this.setAttributeValue(SquareWasBVO.VBDEF19, vbdef19);
  }

  /**
   * 获取自定义项2
   * 
   * @return 自定义项2
   */
  public String getVbdef2() {
    return (String) this.getAttributeValue(SquareWasBVO.VBDEF2);
  }

  /**
   * 设置自定义项2
   * 
   * @param vbdef2 自定义项2
   */
  public void setVbdef2(String vbdef2) {
    this.setAttributeValue(SquareWasBVO.VBDEF2, vbdef2);
  }

  /**
   * 获取自定义项20
   * 
   * @return 自定义项20
   */
  public String getVbdef20() {
    return (String) this.getAttributeValue(SquareWasBVO.VBDEF20);
  }

  /**
   * 设置自定义项20
   * 
   * @param vbdef20 自定义项20
   */
  public void setVbdef20(String vbdef20) {
    this.setAttributeValue(SquareWasBVO.VBDEF20, vbdef20);
  }

  /**
   * 获取自定义项3
   * 
   * @return 自定义项3
   */
  public String getVbdef3() {
    return (String) this.getAttributeValue(SquareWasBVO.VBDEF3);
  }

  /**
   * 设置自定义项3
   * 
   * @param vbdef3 自定义项3
   */
  public void setVbdef3(String vbdef3) {
    this.setAttributeValue(SquareWasBVO.VBDEF3, vbdef3);
  }

  /**
   * 获取自定义项4
   * 
   * @return 自定义项4
   */
  public String getVbdef4() {
    return (String) this.getAttributeValue(SquareWasBVO.VBDEF4);
  }

  /**
   * 设置自定义项4
   * 
   * @param vbdef4 自定义项4
   */
  public void setVbdef4(String vbdef4) {
    this.setAttributeValue(SquareWasBVO.VBDEF4, vbdef4);
  }

  /**
   * 获取自定义项5
   * 
   * @return 自定义项5
   */
  public String getVbdef5() {
    return (String) this.getAttributeValue(SquareWasBVO.VBDEF5);
  }

  /**
   * 设置自定义项5
   * 
   * @param vbdef5 自定义项5
   */
  public void setVbdef5(String vbdef5) {
    this.setAttributeValue(SquareWasBVO.VBDEF5, vbdef5);
  }

  /**
   * 获取自定义项6
   * 
   * @return 自定义项6
   */
  public String getVbdef6() {
    return (String) this.getAttributeValue(SquareWasBVO.VBDEF6);
  }

  /**
   * 设置自定义项6
   * 
   * @param vbdef6 自定义项6
   */
  public void setVbdef6(String vbdef6) {
    this.setAttributeValue(SquareWasBVO.VBDEF6, vbdef6);
  }

  /**
   * 获取自定义项7
   * 
   * @return 自定义项7
   */
  public String getVbdef7() {
    return (String) this.getAttributeValue(SquareWasBVO.VBDEF7);
  }

  /**
   * 设置自定义项7
   * 
   * @param vbdef7 自定义项7
   */
  public void setVbdef7(String vbdef7) {
    this.setAttributeValue(SquareWasBVO.VBDEF7, vbdef7);
  }

  /**
   * 获取自定义项8
   * 
   * @return 自定义项8
   */
  public String getVbdef8() {
    return (String) this.getAttributeValue(SquareWasBVO.VBDEF8);
  }

  /**
   * 设置自定义项8
   * 
   * @param vbdef8 自定义项8
   */
  public void setVbdef8(String vbdef8) {
    this.setAttributeValue(SquareWasBVO.VBDEF8, vbdef8);
  }

  /**
   * 获取自定义项9
   * 
   * @return 自定义项9
   */
  public String getVbdef9() {
    return (String) this.getAttributeValue(SquareWasBVO.VBDEF9);
  }

  /**
   * 设置自定义项9
   * 
   * @param vbdef9 自定义项9
   */
  public void setVbdef9(String vbdef9) {
    this.setAttributeValue(SquareWasBVO.VBDEF9, vbdef9);
  }

  /**
   * 获取单位换算率
   * 
   * @return 单位换算率
   */
  public String getVchangerate() {
    return (String) this.getAttributeValue(SquareWasBVO.VCHANGERATE);
  }

  /**
   * 设置单位换算率
   * 
   * @param vchangerate 单位换算率
   */
  public void setVchangerate(String vchangerate) {
    this.setAttributeValue(SquareWasBVO.VCHANGERATE, vchangerate);
  }

  /**
   * 获取合同号
   * 
   * @return 合同号
   */
  public String getVctcode() {
    return (String) this.getAttributeValue(SquareWasBVO.VCTCODE);
  }

  /**
   * 设置合同号
   * 
   * @param vctcode 合同号
   */
  public void setVctcode(String vctcode) {
    this.setAttributeValue(SquareWasBVO.VCTCODE, vctcode);
  }

  /**
   * 获取源头单据号
   * 
   * @return 源头单据号
   */
  public String getVfirstcode() {
    return (String) this.getAttributeValue(SquareWasBVO.VFIRSTCODE);
  }

  /**
   * 设置源头单据号
   * 
   * @param vfirstcode 源头单据号
   */
  public void setVfirstcode(String vfirstcode) {
    this.setAttributeValue(SquareWasBVO.VFIRSTCODE, vfirstcode);
  }

  /**
   * 获取源头单据行号
   * 
   * @return 源头单据行号
   */
  public String getVfirstrowno() {
    return (String) this.getAttributeValue(SquareWasBVO.VFIRSTROWNO);
  }

  /**
   * 设置源头单据行号
   * 
   * @param vfirstrowno 源头单据行号
   */
  public void setVfirstrowno(String vfirstrowno) {
    this.setAttributeValue(SquareWasBVO.VFIRSTROWNO, vfirstrowno);
  }

  /**
   * 获取源头单据交易类型
   * 
   * @return 源头单据交易类型
   */
  public String getVfirsttrantype() {
    return (String) this.getAttributeValue(SquareWasBVO.VFIRSTTRANTYPE);
  }

  /**
   * 设置源头单据交易类型
   * 
   * @param vfirsttrantype 源头单据交易类型
   */
  public void setVfirsttrantype(String vfirsttrantype) {
    this.setAttributeValue(SquareWasBVO.VFIRSTTRANTYPE, vfirsttrantype);
  }

  /**
   * 获取源头单据类型
   * 
   * @return 源头单据类型
   */
  public String getVfirsttype() {
    return (String) this.getAttributeValue(SquareWasBVO.VFIRSTTYPE);
  }

  /**
   * 设置源头单据类型
   * 
   * @param vfirsttype 源头单据类型
   */
  public void setVfirsttype(String vfirsttype) {
    this.setAttributeValue(SquareWasBVO.VFIRSTTYPE, vfirsttype);
  }

  /**
   * 获取自由辅助属性1
   * 
   * @return 自由辅助属性1
   */
  public String getVfree1() {
    return (String) this.getAttributeValue(SquareWasBVO.VFREE1);
  }

  /**
   * 设置自由辅助属性1
   * 
   * @param vfree1 自由辅助属性1
   */
  public void setVfree1(String vfree1) {
    this.setAttributeValue(SquareWasBVO.VFREE1, vfree1);
  }

  /**
   * 获取自由辅助属性10
   * 
   * @return 自由辅助属性10
   */
  public String getVfree10() {
    return (String) this.getAttributeValue(SquareWasBVO.VFREE10);
  }

  /**
   * 设置自由辅助属性10
   * 
   * @param vfree10 自由辅助属性10
   */
  public void setVfree10(String vfree10) {
    this.setAttributeValue(SquareWasBVO.VFREE10, vfree10);
  }

  /**
   * 获取自由辅助属性2
   * 
   * @return 自由辅助属性2
   */
  public String getVfree2() {
    return (String) this.getAttributeValue(SquareWasBVO.VFREE2);
  }

  /**
   * 设置自由辅助属性2
   * 
   * @param vfree2 自由辅助属性2
   */
  public void setVfree2(String vfree2) {
    this.setAttributeValue(SquareWasBVO.VFREE2, vfree2);
  }

  /**
   * 获取自由辅助属性3
   * 
   * @return 自由辅助属性3
   */
  public String getVfree3() {
    return (String) this.getAttributeValue(SquareWasBVO.VFREE3);
  }

  /**
   * 设置自由辅助属性3
   * 
   * @param vfree3 自由辅助属性3
   */
  public void setVfree3(String vfree3) {
    this.setAttributeValue(SquareWasBVO.VFREE3, vfree3);
  }

  /**
   * 获取自由辅助属性4
   * 
   * @return 自由辅助属性4
   */
  public String getVfree4() {
    return (String) this.getAttributeValue(SquareWasBVO.VFREE4);
  }

  /**
   * 设置自由辅助属性4
   * 
   * @param vfree4 自由辅助属性4
   */
  public void setVfree4(String vfree4) {
    this.setAttributeValue(SquareWasBVO.VFREE4, vfree4);
  }

  /**
   * 获取自由辅助属性5
   * 
   * @return 自由辅助属性5
   */
  public String getVfree5() {
    return (String) this.getAttributeValue(SquareWasBVO.VFREE5);
  }

  /**
   * 设置自由辅助属性5
   * 
   * @param vfree5 自由辅助属性5
   */
  public void setVfree5(String vfree5) {
    this.setAttributeValue(SquareWasBVO.VFREE5, vfree5);
  }

  /**
   * 获取自由辅助属性6
   * 
   * @return 自由辅助属性6
   */
  public String getVfree6() {
    return (String) this.getAttributeValue(SquareWasBVO.VFREE6);
  }

  /**
   * 设置自由辅助属性6
   * 
   * @param vfree6 自由辅助属性6
   */
  public void setVfree6(String vfree6) {
    this.setAttributeValue(SquareWasBVO.VFREE6, vfree6);
  }

  /**
   * 获取自由辅助属性7
   * 
   * @return 自由辅助属性7
   */
  public String getVfree7() {
    return (String) this.getAttributeValue(SquareWasBVO.VFREE7);
  }

  /**
   * 设置自由辅助属性7
   * 
   * @param vfree7 自由辅助属性7
   */
  public void setVfree7(String vfree7) {
    this.setAttributeValue(SquareWasBVO.VFREE7, vfree7);
  }

  /**
   * 获取自由辅助属性8
   * 
   * @return 自由辅助属性8
   */
  public String getVfree8() {
    return (String) this.getAttributeValue(SquareWasBVO.VFREE8);
  }

  /**
   * 设置自由辅助属性8
   * 
   * @param vfree8 自由辅助属性8
   */
  public void setVfree8(String vfree8) {
    this.setAttributeValue(SquareWasBVO.VFREE8, vfree8);
  }

  /**
   * 获取自由辅助属性9
   * 
   * @return 自由辅助属性9
   */
  public String getVfree9() {
    return (String) this.getAttributeValue(SquareWasBVO.VFREE9);
  }

  /**
   * 设置自由辅助属性9
   * 
   * @param vfree9 自由辅助属性9
   */
  public void setVfree9(String vfree9) {
    this.setAttributeValue(SquareWasBVO.VFREE9, vfree9);
  }

  /**
   * 获取行备注
   * 
   * @return 行备注
   */
  public String getVrownote() {
    return (String) this.getAttributeValue(SquareWasBVO.VROWNOTE);
  }

  /**
   * 设置行备注
   * 
   * @param vrownote 行备注
   */
  public void setVrownote(String vrownote) {
    this.setAttributeValue(SquareWasBVO.VROWNOTE, vrownote);
  }

  /**
   * 获取来源单据号
   * 
   * @return 来源单据号
   */
  public String getVsrccode() {
    return (String) this.getAttributeValue(SquareWasBVO.VSRCCODE);
  }

  /**
   * 设置来源单据号
   * 
   * @param vsrccode 来源单据号
   */
  public void setVsrccode(String vsrccode) {
    this.setAttributeValue(SquareWasBVO.VSRCCODE, vsrccode);
  }

  /**
   * 获取来源单据行号
   * 
   * @return 来源单据行号
   */
  public String getVsrcrowno() {
    return (String) this.getAttributeValue(SquareWasBVO.VSRCROWNO);
  }

  /**
   * 设置来源单据行号
   * 
   * @param vsrcrowno 来源单据行号
   */
  public void setVsrcrowno(String vsrcrowno) {
    this.setAttributeValue(SquareWasBVO.VSRCROWNO, vsrcrowno);
  }

  /**
   * 获取来源单据交易类型
   * 
   * @return 来源单据交易类型
   */
  public String getVsrctrantype() {
    return (String) this.getAttributeValue(SquareWasBVO.VSRCTRANTYPE);
  }

  /**
   * 设置来源单据交易类型
   * 
   * @param vsrctrantype 来源单据交易类型
   */
  public void setVsrctrantype(String vsrctrantype) {
    this.setAttributeValue(SquareWasBVO.VSRCTRANTYPE, vsrctrantype);
  }

  /**
   * 获取来源单据类型
   * 
   * @return 来源单据类型
   */
  public String getVsrctype() {
    return (String) this.getAttributeValue(SquareWasBVO.VSRCTYPE);
  }

  /**
   * 设置来源单据类型
   * 
   * @param vsrctype 来源单据类型
   */
  public void setVsrctype(String vsrctype) {
    this.setAttributeValue(SquareWasBVO.VSRCTYPE, vsrctype);
  }

  @Override
  public IVOMeta getMetaData() {
    IVOMeta meta =
        VOMetaFactory.getInstance().getVOMeta(SquareWasBVO.ENTITYNAME);
    return meta;
  }

  public void setDr(Integer dr) {
    this.setAttributeValue(SquareWasHVO.DR, dr);
  }

  public Integer getDr() {
    return (Integer) this.getAttributeValue(SquareWasHVO.DR);
  }
  
  /**
   * 获取特征码
   * 
   * @return 特征码
   */
  public String  getCmffileid() {
    return (String) this.getAttributeValue(SquareWasBVO.CMFFILEID);
  }
  
  /**
   * 设置特征码
   * 
   * @param 特征码
   */
  public void setCmffileid(String cmffileid) {
    this.setAttributeValue(SquareWasBVO.CMFFILEID, cmffileid);
  }
}
