package nc.vo.so.m33.m4c.entity;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m33.enumeration.SquareType;
import nc.vo.so.m33.m32.entity.SquareInvBVO;

/**
 * 销售出库待结算子实体
 *
 */
public class SquareOutBVO extends SuperVO {
  
  private static final long serialVersionUID = -7719302456054625866L;
  
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
    this.setAttributeValue(SquareOutBVO.CCUSTMATERIALID, ccustmaterialid);
  }

  /**
   * 获取客户物料编码
   * 
   * @return 客户物料编码
   */
  public String getCcustmaterialid() {
    return (String) this.getAttributeValue(SquareOutBVO.CCUSTMATERIALID);
  }

  // 销售出库单待结算清单子实体
  public static final String ENTITYNAME = "so.SquareOutBVO";

  // 删除标志dr
  public static final String DR = "dr";

  // 元数据路径
  public static final String MAINMETAPATH = "csalesquarebid.";

  // ntotalsquarenum 模板上使用
  public static final String NTOTALSQUARENUM = "ntotalsquarenum";

  /**
   * 销售出库单待结算单voID(程序中临时用，元数据上没有)
   * 待结算vo与明细vo的对应关系，因为可能待结算vo拆成1行拆成多行SquareOutDetailVO
   * 所以无法用行id对应
   */
  public static final String CSQUAREOUTBVOID = "csquareoutbvoid";

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
   * boutrushflag（可持续化字段，可以远程传递值，但不能保存到数据库）
   */
  public static final String BOUTRUSHFLAG = "boutrushflag";

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
   * 结算利润中心最新版本
   */
  public static final String CPROFITCENTERID = "cprofitcenterid";

  /**
   * 结算利润中心
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
   * crushoutbatchid（可持续化字段，可以远程传递值，但不能保存到数据库）
   */
  public static final String CRUSHOUTBATCHID = "crushoutbatchid";

  /**
   * 销售组织
   */
  public static final String CSALEORGID = "csaleorgid";

  /**
   * 销售组织版本
   */
  public static final String CSALEORGVID = "csaleorgvid";

  /**
   * 销售出库结算单子实体
   */
  public static final String CSALESQUAREBID = "csalesquarebid";

  /**
   * csalesquaredid（可持续化字段，可以远程传递值，但不能保存到数据库）
   */
  public static final String CSALESQUAREDID = "csalesquaredid";

  /**
   * 销售出库待结算单主实体_主键
   */
  public static final String CSALESQUAREID = "csalesquareid";

  /**
   * 销售出库单子实体
   */
  public static final String CSQUAREBILLBID = "csquarebillbid";

  /**
   * 销售出库单主实体
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
   * 销售出库单单据日期
   */
  public static final String DBILLDATE = "dbilldate";

  /**
   * 销售出库单业务日期
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
   * 累计下游发票确认应收金额
   */
  public static final String NDOWNARMNY = "ndownarmny";

  /**
   * 累计下游发票确认应收数量
   */
  public static final String NDOWNARNUM = "ndownarnum";

  /**
   * 累计下游发票成本结算数量
   */
  public static final String NDOWNIANUM = "ndownianum";

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
   * 累计出库对冲数量
   */
  public static final String NRUSHNUM = "nrushnum";

  /**
   * 累计确认应收金额
   */
  public static final String NSQUAREARMNY = "nsquarearmny";

  /**
   * 累计确认应收数量
   */
  public static final String NSQUAREARNUM = "nsquarearnum";

  /**
   * 累计暂估应收数量
   */
  public static final String NSQUAREESTNUM = "nsquareestnum";

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
   * 累计出库及下游发票收款核销金额
   */
  public static final String NTOTALPAYMNY = "ntotalpaymny";

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
   * 表体自定义项1
   */
  public static final String VBDEF1 = "vbdef1";

  /**
   * 表体自定义项10
   */
  public static final String VBDEF10 = "vbdef10";

  /**
   * 表体自定义项11
   */
  public static final String VBDEF11 = "vbdef11";

  /**
   * 表体自定义项12
   */
  public static final String VBDEF12 = "vbdef12";

  /**
   * 表体自定义项13
   */
  public static final String VBDEF13 = "vbdef13";

  /**
   * 表体自定义项14
   */
  public static final String VBDEF14 = "vbdef14";

  /**
   * 表体自定义项15
   */
  public static final String VBDEF15 = "vbdef15";

  /**
   * 表体自定义项16
   */
  public static final String VBDEF16 = "vbdef16";

  /**
   * 表体自定义项17
   */
  public static final String VBDEF17 = "vbdef17";

  /**
   * 表体自定义项18
   */
  public static final String VBDEF18 = "vbdef18";

  /**
   * 表体自定义项19
   */
  public static final String VBDEF19 = "vbdef19";

  /**
   * 表体自定义项2
   */
  public static final String VBDEF2 = "vbdef2";

  /**
   * 表体自定义项20
   */
  public static final String VBDEF20 = "vbdef20";

  /**
   * 表体自定义项3
   */
  public static final String VBDEF3 = "vbdef3";

  /**
   * 表体自定义项4
   */
  public static final String VBDEF4 = "vbdef4";

  /**
   * 表体自定义项5
   */
  public static final String VBDEF5 = "vbdef5";

  /**
   * 表体自定义项6
   */
  public static final String VBDEF6 = "vbdef6";

  /**
   * 表体自定义项7
   */
  public static final String VBDEF7 = "vbdef7";

  /**
   * 表体自定义项8
   */
  public static final String VBDEF8 = "vbdef8";

  /**
   * 表体自定义项9
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
    return (String) this.getAttributeValue(SquareOutBVO.CSPROFITCENTERVID);
  }

  /**
   * 设置发货利润中心
   * 
   * @param csprofitcentervid 发货利润中心
   */
  public void setCsprofitcentervid(String csprofitcentervid) {
    this.setAttributeValue(SquareOutBVO.CSPROFITCENTERVID, csprofitcentervid);
  }

  /**
   * 获取发货利润中心版本
   * 
   * @return 发货利润中心版本
   */
  public String getCsprofitcenterid() {
    return (String) this.getAttributeValue(SquareOutBVO.CSPROFITCENTERID);
  }

  /**
   * 设置发货利润中心版本
   * 
   * @param csprofitcenterid 发货利润中心版本
   */
  public void setCsprofitcenterid(String csprofitcenterid) {
    this.setAttributeValue(SquareOutBVO.CSPROFITCENTERID, csprofitcenterid);
  }

  /**
   * 获取是否可以成本结算
   * 
   * @return 是否可以成本结算
   */
  public UFBoolean getBcost() {
    return (UFBoolean) this.getAttributeValue(SquareOutBVO.BCOST);
  }

  /**
   * 设置是否可以成本结算
   * 
   * @param bcost 是否可以成本结算
   */
  public void setBcost(UFBoolean bcost) {
    this.setAttributeValue(SquareOutBVO.BCOST, bcost);
  }

  /**
   * 获取是否可以收入结算
   * 
   * @return 是否可以收入结算
   */
  public UFBoolean getBincome() {
    return (UFBoolean) this.getAttributeValue(SquareOutBVO.BINCOME);
  }

  /**
   * 设置是否可以收入结算
   * 
   * @param bincome 是否可以收入结算
   */
  public void setBincome(UFBoolean bincome) {
    this.setAttributeValue(SquareOutBVO.BINCOME, bincome);
  }

  /**
   * 获取是否赠品
   * 
   * @return 是否赠品
   */
  public UFBoolean getBlargessflag() {
    return (UFBoolean) this.getAttributeValue(SquareOutBVO.BLARGESSFLAG);
  }

  /**
   * 设置是否赠品
   * 
   * @param blargessflag 是否赠品
   */
  public void setBlargessflag(UFBoolean blargessflag) {
    this.setAttributeValue(SquareOutBVO.BLARGESSFLAG, blargessflag);
  }

  /**
   * 获取boutrushflag（可持续化字段，可以远程传递值，但不能保存到数据库）
   * 
   * @return boutrushflag
   */
  public UFBoolean getBoutrushflag() {
    return (UFBoolean) this.getAttributeValue(SquareOutBVO.BOUTRUSHFLAG);
  }

  /**
   * 设置boutrushflag（可持续化字段，可以远程传递值，但不能保存到数据库）
   * 
   * @param boutrushflag boutrushflag
   */
  public void setBoutrushflag(UFBoolean boutrushflag) {
    this.setAttributeValue(SquareOutBVO.BOUTRUSHFLAG, boutrushflag);
  }

  /**
   * 获取是否应收结算完成
   * 
   * @return 是否应收结算完成
   */
  public UFBoolean getBsquarearfinish() {
    return (UFBoolean) this.getAttributeValue(SquareOutBVO.BSQUAREARFINISH);
  }

  /**
   * 设置是否应收结算完成
   * 
   * @param bsquarearfinish 是否应收结算完成
   */
  public void setBsquarearfinish(UFBoolean bsquarearfinish) {
    this.setAttributeValue(SquareOutBVO.BSQUAREARFINISH, bsquarearfinish);
  }

  /**
   * 获取是否成本结算完成
   * 
   * @return 是否成本结算完成
   */
  public UFBoolean getBsquareiafinish() {
    return (UFBoolean) this.getAttributeValue(SquareOutBVO.BSQUAREIAFINISH);
  }

  /**
   * 设置是否成本结算完成
   * 
   * @param bsquareiafinish 是否成本结算完成
   */
  public void setBsquareiafinish(UFBoolean bsquareiafinish) {
    this.setAttributeValue(SquareOutBVO.BSQUAREIAFINISH, bsquareiafinish);
  }

  /**
   * 获取应收组织最新版本
   * 
   * @return 应收组织最新版本
   */
  public String getCarorgid() {
    return (String) this.getAttributeValue(SquareOutBVO.CARORGID);
  }

  /**
   * 设置应收组织最新版本
   * 
   * @param carorgid 应收组织最新版本
   */
  public void setCarorgid(String carorgid) {
    this.setAttributeValue(SquareOutBVO.CARORGID, carorgid);
  }

  /**
   * 获取应收组织版本
   * 
   * @return 应收组织版本
   */
  public String getCarorgvid() {
    return (String) this.getAttributeValue(SquareOutBVO.CARORGVID);
  }

  /**
   * 设置应收组织版本
   * 
   * @param carorgvid 应收组织版本
   */
  public void setCarorgvid(String carorgvid) {
    this.setAttributeValue(SquareOutBVO.CARORGVID, carorgvid);
  }

  /**
   * 获取单位
   * 
   * @return 单位
   */
  public String getCastunitid() {
    return (String) this.getAttributeValue(SquareOutBVO.CASTUNITID);
  }

  /**
   * 设置单位
   * 
   * @param castunitid 单位
   */
  public void setCastunitid(String castunitid) {
    this.setAttributeValue(SquareOutBVO.CASTUNITID, castunitid);
  }

  /**
   * 获取订单渠道类型
   * 
   * @return 订单渠道类型
   */
  public String getCchanneltypeid() {
    return (String) this.getAttributeValue(SquareOutBVO.CCHANNELTYPEID);
  }

  /**
   * 设置订单渠道类型
   * 
   * @param cchanneltypeid 订单渠道类型
   */
  public void setCchanneltypeid(String cchanneltypeid) {
    this.setAttributeValue(SquareOutBVO.CCHANNELTYPEID, cchanneltypeid);
  }

  /**
   * 获取成本域
   * 
   * @return 成本域
   */
  public String getCcostorgid() {
    return (String) this.getAttributeValue(SquareOutBVO.CCOSTORGID);
  }

  /**
   * 设置成本域
   * 
   * @param ccostorgid 成本域
   */
  public void setCcostorgid(String ccostorgid) {
    this.setAttributeValue(SquareOutBVO.CCOSTORGID, ccostorgid);
  }

  /**
   * 获取本币
   * 
   * @return 本币
   */
  public String getCcurrencyid() {
    return (String) this.getAttributeValue(SquareOutBVO.CCURRENCYID);
  }

  /**
   * 设置本币
   * 
   * @param ccurrencyid 本币
   */
  public void setCcurrencyid(String ccurrencyid) {
    this.setAttributeValue(SquareOutBVO.CCURRENCYID, ccurrencyid);
  }

  /**
   * 获取开户银行账户
   * 
   * @return 开户银行账户
   */
  public String getCcustbankaccid() {
    return (String) this.getAttributeValue(SquareOutBVO.CCUSTBANKACCID);
  }

  /**
   * 设置开户银行账户
   * 
   * @param ccustbankaccid 开户银行账户
   */
  public void setCcustbankaccid(String ccustbankaccid) {
    this.setAttributeValue(SquareOutBVO.CCUSTBANKACCID, ccustbankaccid);
  }

  /**
   * 获取销售部门最新版本
   * 
   * @return 销售部门最新版本
   */
  public String getCdeptid() {
    return (String) this.getAttributeValue(SquareOutBVO.CDEPTID);
  }

  /**
   * 设置销售部门最新版本
   * 
   * @param cdeptid 销售部门最新版本
   */
  public void setCdeptid(String cdeptid) {
    this.setAttributeValue(SquareOutBVO.CDEPTID, cdeptid);
  }

  /**
   * 获取销售部门版本
   * 
   * @return 销售部门版本
   */
  public String getCdeptvid() {
    return (String) this.getAttributeValue(SquareOutBVO.CDEPTVID);
  }

  /**
   * 设置销售部门版本
   * 
   * @param cdeptvid 销售部门版本
   */
  public void setCdeptvid(String cdeptvid) {
    this.setAttributeValue(SquareOutBVO.CDEPTVID, cdeptvid);
  }

  /**
   * 获取销售业务员
   * 
   * @return 销售业务员
   */
  public String getCemployeeid() {
    return (String) this.getAttributeValue(SquareOutBVO.CEMPLOYEEID);
  }

  /**
   * 设置销售业务员
   * 
   * @param cemployeeid 销售业务员
   */
  public void setCemployeeid(String cemployeeid) {
    this.setAttributeValue(SquareOutBVO.CEMPLOYEEID, cemployeeid);
  }

  /**
   * 获取源头单据子表
   * 
   * @return 源头单据子表
   */
  public String getCfirstbid() {
    return (String) this.getAttributeValue(SquareOutBVO.CFIRSTBID);
  }

  /**
   * 设置源头单据子表
   * 
   * @param cfirstbid 源头单据子表
   */
  public void setCfirstbid(String cfirstbid) {
    this.setAttributeValue(SquareOutBVO.CFIRSTBID, cfirstbid);
  }

  /**
   * 获取源头单据主表
   * 
   * @return 源头单据主表
   */
  public String getCfirstid() {
    return (String) this.getAttributeValue(SquareOutBVO.CFIRSTID);
  }

  /**
   * 设置源头单据主表
   * 
   * @param cfirstid 源头单据主表
   */
  public void setCfirstid(String cfirstid) {
    this.setAttributeValue(SquareOutBVO.CFIRSTID, cfirstid);
  }

  /**
   * 获取散户
   * 
   * @return 散户
   */
  public String getCfreecustid() {
    return (String) this.getAttributeValue(SquareOutBVO.CFREECUSTID);
  }

  /**
   * 设置散户
   * 
   * @param cfreecustid 散户
   */
  public void setCfreecustid(String cfreecustid) {
    this.setAttributeValue(SquareOutBVO.CFREECUSTID, cfreecustid);
  }

  /**
   * 获取订单开票客户
   * 
   * @return 订单开票客户
   */
  public String getCinvoicecustid() {
    return (String) this.getAttributeValue(SquareOutBVO.CINVOICECUSTID);
  }

  /**
   * 设置订单开票客户
   * 
   * @param cinvoicecustid 订单开票客户
   */
  public void setCinvoicecustid(String cinvoicecustid) {
    this.setAttributeValue(SquareOutBVO.CINVOICECUSTID, cinvoicecustid);
  }

  /**
   * 获取物料
   * 
   * @return 物料
   */
  public String getCmaterialid() {
    return (String) this.getAttributeValue(SquareOutBVO.CMATERIALID);
  }

  /**
   * 设置物料
   * 
   * @param cmaterialid 物料
   */
  public void setCmaterialid(String cmaterialid) {
    this.setAttributeValue(SquareOutBVO.CMATERIALID, cmaterialid);
  }

  /**
   * 获取物料版本
   * 
   * @return 物料版本
   */
  public String getCmaterialvid() {
    return (String) this.getAttributeValue(SquareOutBVO.CMATERIALVID);
  }

  /**
   * 设置物料版本
   * 
   * @param cmaterialvid 物料版本
   */
  public void setCmaterialvid(String cmaterialvid) {
    this.setAttributeValue(SquareOutBVO.CMATERIALVID, cmaterialvid);
  }

  /**
   * 获取订单客户
   * 
   * @return 订单客户
   */
  public String getCordercustid() {
    return (String) this.getAttributeValue(SquareOutBVO.CORDERCUSTID);
  }

  /**
   * 设置订单客户
   * 
   * @param cordercustid 订单客户
   */
  public void setCordercustid(String cordercustid) {
    this.setAttributeValue(SquareOutBVO.CORDERCUSTID, cordercustid);
  }

  /**
   * 获取原币
   * 
   * @return 原币
   */
  public String getCorigcurrencyid() {
    return (String) this.getAttributeValue(SquareOutBVO.CORIGCURRENCYID);
  }

  /**
   * 设置原币
   * 
   * @param corigcurrencyid 原币
   */
  public void setCorigcurrencyid(String corigcurrencyid) {
    this.setAttributeValue(SquareOutBVO.CORIGCURRENCYID, corigcurrencyid);
  }

  /**
   * 获取订单收付款协议
   * 
   * @return 订单收付款协议
   */
  public String getCpaytermid() {
    return (String) this.getAttributeValue(SquareOutBVO.CPAYTERMID);
  }

  /**
   * 设置订单收付款协议
   * 
   * @param cpaytermid 订单收付款协议
   */
  public void setCpaytermid(String cpaytermid) {
    this.setAttributeValue(SquareOutBVO.CPAYTERMID, cpaytermid);
  }

  /**
   * 获取生产厂商
   * 
   * @return 生产厂商
   */
  public String getCproductorid() {
    return (String) this.getAttributeValue(SquareOutBVO.CPRODUCTORID);
  }

  /**
   * 设置生产厂商
   * 
   * @param cproductorid 生产厂商
   */
  public void setCproductorid(String cproductorid) {
    this.setAttributeValue(SquareOutBVO.CPRODUCTORID, cproductorid);
  }

  /**
   * 获取结算利润中心最新版本
   * 
   * @return 结算利润中心最新版本
   */
  public String getCprofitcenterid() {
    return (String) this.getAttributeValue(SquareOutBVO.CPROFITCENTERID);
  }

  /**
   * 设置结算利润中心最新版本
   * 
   * @param cprofitcenterid 结算利润中心最新版本
   */
  public void setCprofitcenterid(String cprofitcenterid) {
    this.setAttributeValue(SquareOutBVO.CPROFITCENTERID, cprofitcenterid);
  }

  /**
   * 获取结算利润中心版本
   * 
   * @return 结算利润中心版本
   */
  public String getCprofitcentervid() {
    return (String) this.getAttributeValue(SquareOutBVO.CPROFITCENTERVID);
  }

  /**
   * 设置结算利润中心版本
   * 
   * @param cprofitcentervid 结算利润中心版本
   */
  public void setCprofitcentervid(String cprofitcentervid) {
    this.setAttributeValue(SquareOutBVO.CPROFITCENTERVID, cprofitcentervid);
  }

  /**
   * 获取项目ID
   * 
   * @return 项目ID
   */
  public String getCprojectid() {
    return (String) this.getAttributeValue(SquareOutBVO.CPROJECTID);
  }

  /**
   * 设置项目ID
   * 
   * @param cprojectid 项目ID
   */
  public void setCprojectid(String cprojectid) {
    this.setAttributeValue(SquareOutBVO.CPROJECTID, cprojectid);
  }

  /**
   * 获取产品线
   * 
   * @return 产品线
   */
  public String getCprolineid() {
    return (String) this.getAttributeValue(SquareOutBVO.CPROLINEID);
  }

  /**
   * 设置产品线
   * 
   * @param cprolineid 产品线
   */
  public void setCprolineid(String cprolineid) {
    this.setAttributeValue(SquareOutBVO.CPROLINEID, cprolineid);
  }

  /**
   * 获取crushoutbatchid（可持续化字段，可以远程传递值，但不能保存到数据库）
   * 
   * @return crushoutbatchid
   */
  public String getCrushoutbatchid() {
    return (String) this.getAttributeValue(SquareOutBVO.CRUSHOUTBATCHID);
  }

  /**
   * 设置crushoutbatchid（可持续化字段，可以远程传递值，但不能保存到数据库）
   * 
   * @param crushoutbatchid crushoutbatchid
   */
  public void setCrushoutbatchid(String crushoutbatchid) {
    this.setAttributeValue(SquareOutBVO.CRUSHOUTBATCHID, crushoutbatchid);
  }

  /**
   * 获取销售组织
   * 
   * @return 销售组织
   */
  public String getCsaleorgid() {
    return (String) this.getAttributeValue(SquareOutBVO.CSALEORGID);
  }

  /**
   * 设置销售组织
   * 
   * @param csaleorgid 销售组织
   */
  public void setCsaleorgid(String csaleorgid) {
    this.setAttributeValue(SquareOutBVO.CSALEORGID, csaleorgid);
  }

  /**
   * 获取销售组织版本
   * 
   * @return 销售组织版本
   */
  public String getCsaleorgvid() {
    return (String) this.getAttributeValue(SquareOutBVO.CSALEORGVID);
  }

  /**
   * 设置销售组织版本
   * 
   * @param csaleorgvid 销售组织版本
   */
  public void setCsaleorgvid(String csaleorgvid) {
    this.setAttributeValue(SquareOutBVO.CSALEORGVID, csaleorgvid);
  }

  /**
   * 获取销售出库结算单子实体
   * 
   * @return 销售出库结算单子实体
   */
  public String getCsalesquarebid() {
    return (String) this.getAttributeValue(SquareOutBVO.CSALESQUAREBID);
  }

  /**
   * 设置销售出库结算单子实体
   * 
   * @param csalesquarebid 销售出库结算单子实体
   */
  public void setCsalesquarebid(String csalesquarebid) {
    this.setAttributeValue(SquareOutBVO.CSALESQUAREBID, csalesquarebid);
  }

  /**
   * 获取csalesquaredid（可持续化字段，可以远程传递值，但不能保存到数据库）
   * 
   * @return csalesquaredid
   */
  public String getCsalesquaredid() {
    return (String) this.getAttributeValue(SquareOutBVO.CSALESQUAREDID);
  }

  /**
   * 设置csalesquaredid（可持续化字段，可以远程传递值，但不能保存到数据库）
   * 
   * @param csalesquaredid csalesquaredid
   */
  public void setCsalesquaredid(String csalesquaredid) {
    this.setAttributeValue(SquareOutBVO.CSALESQUAREDID, csalesquaredid);
  }

  /**
   * 获取销售出库待结算单主实体_主键
   * 
   * @return 销售出库待结算单主实体_主键
   */
  public String getCsalesquareid() {
    return (String) this.getAttributeValue(SquareOutBVO.CSALESQUAREID);
  }

  /**
   * 设置销售出库待结算单主实体_主键
   * 
   * @param csalesquareid 销售出库待结算单主实体_主键
   */
  public void setCsalesquareid(String csalesquareid) {
    this.setAttributeValue(SquareOutBVO.CSALESQUAREID, csalesquareid);
  }

  /**
   * 获取销售出库单子实体
   * 
   * @return 销售出库单子实体
   */
  public String getCsquarebillbid() {
    return (String) this.getAttributeValue(SquareOutBVO.CSQUAREBILLBID);
  }

  /**
   * 设置销售出库单子实体
   * 
   * @param csquarebillbid 销售出库单子实体
   */
  public void setCsquarebillbid(String csquarebillbid) {
    this.setAttributeValue(SquareOutBVO.CSQUAREBILLBID, csquarebillbid);
  }

  /**
   * 获取销售出库单主实体
   * 
   * @return 销售出库单主实体
   */
  public String getCsquarebillid() {
    return (String) this.getAttributeValue(SquareOutBVO.CSQUAREBILLID);
  }

  /**
   * 设置销售出库单主实体
   * 
   * @param csquarebillid 销售出库单主实体
   */
  public void setCsquarebillid(String csquarebillid) {
    this.setAttributeValue(SquareOutBVO.CSQUAREBILLID, csquarebillid);
  }

  /**
   * 获取来源单据子表
   * 
   * @return 来源单据子表
   */
  public String getCsrcbid() {
    return (String) this.getAttributeValue(SquareOutBVO.CSRCBID);
  }

  /**
   * 设置来源单据子表
   * 
   * @param csrcbid 来源单据子表
   */
  public void setCsrcbid(String csrcbid) {
    this.setAttributeValue(SquareOutBVO.CSRCBID, csrcbid);
  }

  /**
   * 获取来源单据主表
   * 
   * @return 来源单据主表
   */
  public String getCsrcid() {
    return (String) this.getAttributeValue(SquareOutBVO.CSRCID);
  }

  /**
   * 设置来源单据主表
   * 
   * @param csrcid 来源单据主表
   */
  public void setCsrcid(String csrcid) {
    this.setAttributeValue(SquareOutBVO.CSRCID, csrcid);
  }

  /**
   * 获取税码
   * 
   * @return 税码
   */
  public String getCtaxcodeid() {
    return (String) this.getAttributeValue(SquareOutBVO.CTAXCODEID);
  }

  /**
   * 设置税码
   * 
   * @param ctaxcodeid 税码
   */
  public void setCtaxcodeid(String ctaxcodeid) {
    this.setAttributeValue(SquareOutBVO.CTAXCODEID, ctaxcodeid);
  }

  /**
   * 获取主单位
   * 
   * @return 主单位
   */
  public String getCunitid() {
    return (String) this.getAttributeValue(SquareOutBVO.CUNITID);
  }

  /**
   * 设置主单位
   * 
   * @param cunitid 主单位
   */
  public void setCunitid(String cunitid) {
    this.setAttributeValue(SquareOutBVO.CUNITID, cunitid);
  }

  /**
   * 获取供应商
   * 
   * @return 供应商
   */
  public String getCvendorid() {
    return (String) this.getAttributeValue(SquareOutBVO.CVENDORID);
  }

  /**
   * 设置供应商
   * 
   * @param cvendorid 供应商
   */
  public void setCvendorid(String cvendorid) {
    this.setAttributeValue(SquareOutBVO.CVENDORID, cvendorid);
  }

  /**
   * 获取销售出库单单据日期
   * 
   * @return 销售出库单单据日期
   */
  public UFDate getDbilldate() {
    return (UFDate) this.getAttributeValue(SquareOutBVO.DBILLDATE);
  }

  /**
   * 设置销售出库单单据日期
   * 
   * @param dbilldate 销售出库单单据日期
   */
  public void setDbilldate(UFDate dbilldate) {
    this.setAttributeValue(SquareOutBVO.DBILLDATE, dbilldate);
  }

  /**
   * 获取销售出库单业务日期
   * 
   * @return 销售出库单业务日期
   */
  public UFDate getDbizdate() {
    return (UFDate) this.getAttributeValue(SquareOutBVO.DBIZDATE);
  }

  /**
   * 设置销售出库单业务日期
   * 
   * @param dbizdate 销售出库单业务日期
   */
  public void setDbizdate(UFDate dbizdate) {
    this.setAttributeValue(SquareOutBVO.DBIZDATE, dbizdate);
  }

  /**
   * 获取应收单起效日期
   * 
   * @return 应收单起效日期
   */
  public UFDate getDeffectdate() {
    return (UFDate) this.getAttributeValue(SquareOutBVO.DEFFECTDATE);
  }

  /**
   * 设置应收单起效日期
   * 
   * @param deffectdate 应收单起效日期
   */
  public void setDeffectdate(UFDate deffectdate) {
    this.setAttributeValue(SquareOutBVO.DEFFECTDATE, deffectdate);
  }

  /**
   * 获取待收入结算类型
   * 
   * @return 待收入结算类型
   * @see SquareType
   */
  public Integer getFpreartype() {
    return (Integer) this.getAttributeValue(SquareOutBVO.FPREARTYPE);
  }

  /**
   * 设置待收入结算类型
   * 
   * @param fpreartype 待收入结算类型
   * @see SquareType
   */
  public void setFpreartype(Integer fpreartype) {
    this.setAttributeValue(SquareOutBVO.FPREARTYPE, fpreartype);
  }

  /**
   * 获取待成本结算类型
   * 
   * @return 待成本结算类型
   * @see SquareType
   */
  public Integer getFpreiatype() {
    return (Integer) this.getAttributeValue(SquareOutBVO.FPREIATYPE);
  }

  /**
   * 设置待成本结算类型
   * 
   * @param fpreiatype 待成本结算类型
   * @see SquareType
   */
  public void setFpreiatype(Integer fpreiatype) {
    this.setAttributeValue(SquareOutBVO.FPREIATYPE, fpreiatype);
  }

  /**
   * 获取扣税类别
   * 
   * @return 扣税类别
   */
  public Integer getFtaxtypeflag() {
    return (Integer) this.getAttributeValue(SquareOutBVO.FTAXTYPEFLAG);
  }

  /**
   * 设置扣税类别
   * 
   * @param ftaxtypeflag 扣税类别
   */
  public void setFtaxtypeflag(Integer ftaxtypeflag) {
    this.setAttributeValue(SquareOutBVO.FTAXTYPEFLAG, ftaxtypeflag);
  }

  /**
   * 获取累计回冲应收数量
   * 
   * @return 累计回冲应收数量
   */
  public UFDouble getNarrushnum() {
    return (UFDouble) this.getAttributeValue(SquareOutBVO.NARRUSHNUM);
  }

  /**
   * 设置累计回冲应收数量
   * 
   * @param narrushnum 累计回冲应收数量
   */
  public void setNarrushnum(UFDouble narrushnum) {
    this.setAttributeValue(SquareOutBVO.NARRUSHNUM, narrushnum);
  }

  /**
   * 获取单位数量
   * 
   * @return 单位数量
   */
  public UFDouble getNastnum() {
    return (UFDouble) this.getAttributeValue(SquareOutBVO.NASTNUM);
  }

  /**
   * 设置单位数量
   * 
   * @param nastnum 单位数量
   */
  public void setNastnum(UFDouble nastnum) {
    this.setAttributeValue(SquareOutBVO.NASTNUM, nastnum);
  }

  /**
   * 获取计税金额
   * 
   * @return 计税金额
   */
  public UFDouble getNcaltaxmny() {
    return (UFDouble) this.getAttributeValue(SquareOutBVO.NCALTAXMNY);
  }

  /**
   * 设置计税金额
   * 
   * @param ncaltaxmny 计税金额
   */
  public void setNcaltaxmny(UFDouble ncaltaxmny) {
    this.setAttributeValue(SquareOutBVO.NCALTAXMNY, ncaltaxmny);
  }

  /**
   * 获取本币折扣额
   * 
   * @return 本币折扣额
   */
  public UFDouble getNdiscount() {
    return (UFDouble) this.getAttributeValue(SquareOutBVO.NDISCOUNT);
  }

  /**
   * 设置本币折扣额
   * 
   * @param ndiscount 本币折扣额
   */
  public void setNdiscount(UFDouble ndiscount) {
    this.setAttributeValue(SquareOutBVO.NDISCOUNT, ndiscount);
  }

  /**
   * 获取累计下游发票确认应收金额
   * 
   * @return 累计下游发票确认应收金额
   */
  public UFDouble getNdownarmny() {
    return (UFDouble) this.getAttributeValue(SquareOutBVO.NDOWNARMNY);
  }

  /**
   * 设置累计下游发票确认应收金额
   * 
   * @param ndownarmny 累计下游发票确认应收金额
   */
  public void setNdownarmny(UFDouble ndownarmny) {
    this.setAttributeValue(SquareOutBVO.NDOWNARMNY, ndownarmny);
  }

  /**
   * 获取累计下游发票确认应收数量
   * 
   * @return 累计下游发票确认应收数量
   */
  public UFDouble getNdownarnum() {
    return (UFDouble) this.getAttributeValue(SquareOutBVO.NDOWNARNUM);
  }

  /**
   * 设置累计下游发票确认应收数量
   * 
   * @param ndownarnum 累计下游发票确认应收数量
   */
  public void setNdownarnum(UFDouble ndownarnum) {
    this.setAttributeValue(SquareOutBVO.NDOWNARNUM, ndownarnum);
  }

  /**
   * 获取累计下游发票成本结算数量
   * 
   * @return 累计下游发票成本结算数量
   */
  public UFDouble getNdownianum() {
    return (UFDouble) this.getAttributeValue(SquareOutBVO.NDOWNIANUM);
  }

  /**
   * 设置累计下游发票成本结算数量
   * 
   * @param ndownianum 累计下游发票成本结算数量
   */
  public void setNdownianum(UFDouble ndownianum) {
    this.setAttributeValue(SquareOutBVO.NDOWNIANUM, ndownianum);
  }

  /**
   * 获取折本汇率
   * 
   * @return 折本汇率
   */
  public UFDouble getNexchangerate() {
    return (UFDouble) this.getAttributeValue(SquareOutBVO.NEXCHANGERATE);
  }

  /**
   * 设置折本汇率
   * 
   * @param nexchangerate 折本汇率
   */
  public void setNexchangerate(UFDouble nexchangerate) {
    this.setAttributeValue(SquareOutBVO.NEXCHANGERATE, nexchangerate);
  }

  /**
   * 获取全局本位币汇率
   * 
   * @return 全局本位币汇率
   */
  public UFDouble getNglobalexchgrate() {
    return (UFDouble) this.getAttributeValue(SquareOutBVO.NGLOBALEXCHGRATE);
  }

  /**
   * 设置全局本位币汇率
   * 
   * @param nglobalexchgrate 全局本位币汇率
   */
  public void setNglobalexchgrate(UFDouble nglobalexchgrate) {
    this.setAttributeValue(SquareOutBVO.NGLOBALEXCHGRATE, nglobalexchgrate);
  }

  /**
   * 获取全局本币无税金额
   * 
   * @return 全局本币无税金额
   */
  public UFDouble getNglobalmny() {
    return (UFDouble) this.getAttributeValue(SquareOutBVO.NGLOBALMNY);
  }

  /**
   * 设置全局本币无税金额
   * 
   * @param nglobalmny 全局本币无税金额
   */
  public void setNglobalmny(UFDouble nglobalmny) {
    this.setAttributeValue(SquareOutBVO.NGLOBALMNY, nglobalmny);
  }

  /**
   * 获取全局本币价税合计
   * 
   * @return 全局本币价税合计
   */
  public UFDouble getNglobaltaxmny() {
    return (UFDouble) this.getAttributeValue(SquareOutBVO.NGLOBALTAXMNY);
  }

  /**
   * 设置全局本币价税合计
   * 
   * @param nglobaltaxmny 全局本币价税合计
   */
  public void setNglobaltaxmny(UFDouble nglobaltaxmny) {
    this.setAttributeValue(SquareOutBVO.NGLOBALTAXMNY, nglobaltaxmny);
  }

  /**
   * 获取集团本位币汇率
   * 
   * @return 集团本位币汇率
   */
  public UFDouble getNgroupexchgrate() {
    return (UFDouble) this.getAttributeValue(SquareOutBVO.NGROUPEXCHGRATE);
  }

  /**
   * 设置集团本位币汇率
   * 
   * @param ngroupexchgrate 集团本位币汇率
   */
  public void setNgroupexchgrate(UFDouble ngroupexchgrate) {
    this.setAttributeValue(SquareOutBVO.NGROUPEXCHGRATE, ngroupexchgrate);
  }

  /**
   * 获取集团本币无税金额
   * 
   * @return 集团本币无税金额
   */
  public UFDouble getNgroupmny() {
    return (UFDouble) this.getAttributeValue(SquareOutBVO.NGROUPMNY);
  }

  /**
   * 设置集团本币无税金额
   * 
   * @param ngroupmny 集团本币无税金额
   */
  public void setNgroupmny(UFDouble ngroupmny) {
    this.setAttributeValue(SquareOutBVO.NGROUPMNY, ngroupmny);
  }

  /**
   * 获取集团本币价税合计
   * 
   * @return 集团本币价税合计
   */
  public UFDouble getNgrouptaxmny() {
    return (UFDouble) this.getAttributeValue(SquareOutBVO.NGROUPTAXMNY);
  }

  /**
   * 设置集团本币价税合计
   * 
   * @param ngrouptaxmny 集团本币价税合计
   */
  public void setNgrouptaxmny(UFDouble ngrouptaxmny) {
    this.setAttributeValue(SquareOutBVO.NGROUPTAXMNY, ngrouptaxmny);
  }

  /**
   * 获取单品折扣率
   * 
   * @return 单品折扣率
   */
  public UFDouble getNitemdiscountrate() {
    return (UFDouble) this.getAttributeValue(SquareOutBVO.NITEMDISCOUNTRATE);
  }

  /**
   * 设置单品折扣率
   * 
   * @param nitemdiscountrate 单品折扣率
   */
  public void setNitemdiscountrate(UFDouble nitemdiscountrate) {
    this.setAttributeValue(SquareOutBVO.NITEMDISCOUNTRATE, nitemdiscountrate);
  }

  /**
   * 获取本币无税金额
   * 
   * @return 本币无税金额
   */
  public UFDouble getNmny() {
    return (UFDouble) this.getAttributeValue(SquareOutBVO.NMNY);
  }

  /**
   * 设置本币无税金额
   * 
   * @param nmny 本币无税金额
   */
  public void setNmny(UFDouble nmny) {
    this.setAttributeValue(SquareOutBVO.NMNY, nmny);
  }

  /**
   * 获取本币无税净价
   * 
   * @return 本币无税净价
   */
  public UFDouble getNnetprice() {
    return (UFDouble) this.getAttributeValue(SquareOutBVO.NNETPRICE);
  }

  /**
   * 设置本币无税净价
   * 
   * @param nnetprice 本币无税净价
   */
  public void setNnetprice(UFDouble nnetprice) {
    this.setAttributeValue(SquareOutBVO.NNETPRICE, nnetprice);
  }

  /**
   * 获取主单位数量
   * 
   * @return 主单位数量
   */
  public UFDouble getNnum() {
    return (UFDouble) this.getAttributeValue(SquareOutBVO.NNUM);
  }

  /**
   * 设置主单位数量
   * 
   * @param nnum 主单位数量
   */
  public void setNnum(UFDouble nnum) {
    this.setAttributeValue(SquareOutBVO.NNUM, nnum);
  }

  /**
   * 获取原币折扣额
   * 
   * @return 原币折扣额
   */
  public UFDouble getNorigdiscount() {
    return (UFDouble) this.getAttributeValue(SquareOutBVO.NORIGDISCOUNT);
  }

  /**
   * 设置原币折扣额
   * 
   * @param norigdiscount 原币折扣额
   */
  public void setNorigdiscount(UFDouble norigdiscount) {
    this.setAttributeValue(SquareOutBVO.NORIGDISCOUNT, norigdiscount);
  }

  /**
   * 获取原币无税金额
   * 
   * @return 原币无税金额
   */
  public UFDouble getNorigmny() {
    return (UFDouble) this.getAttributeValue(SquareOutBVO.NORIGMNY);
  }

  /**
   * 设置原币无税金额
   * 
   * @param norigmny 原币无税金额
   */
  public void setNorigmny(UFDouble norigmny) {
    this.setAttributeValue(SquareOutBVO.NORIGMNY, norigmny);
  }

  /**
   * 获取原币无税净价
   * 
   * @return 原币无税净价
   */
  public UFDouble getNorignetprice() {
    return (UFDouble) this.getAttributeValue(SquareOutBVO.NORIGNETPRICE);
  }

  /**
   * 设置原币无税净价
   * 
   * @param norignetprice 原币无税净价
   */
  public void setNorignetprice(UFDouble norignetprice) {
    this.setAttributeValue(SquareOutBVO.NORIGNETPRICE, norignetprice);
  }

  /**
   * 获取原币无税单价
   * 
   * @return 原币无税单价
   */
  public UFDouble getNorigprice() {
    return (UFDouble) this.getAttributeValue(SquareOutBVO.NORIGPRICE);
  }

  /**
   * 设置原币无税单价
   * 
   * @param norigprice 原币无税单价
   */
  public void setNorigprice(UFDouble norigprice) {
    this.setAttributeValue(SquareOutBVO.NORIGPRICE, norigprice);
  }

  /**
   * 获取原币价税合计
   * 
   * @return 原币价税合计
   */
  public UFDouble getNorigtaxmny() {
    return (UFDouble) this.getAttributeValue(SquareOutBVO.NORIGTAXMNY);
  }

  /**
   * 设置原币价税合计
   * 
   * @param norigtaxmny 原币价税合计
   */
  public void setNorigtaxmny(UFDouble norigtaxmny) {
    this.setAttributeValue(SquareOutBVO.NORIGTAXMNY, norigtaxmny);
  }

  /**
   * 获取原币含税净价
   * 
   * @return 原币含税净价
   */
  public UFDouble getNorigtaxnetprice() {
    return (UFDouble) this.getAttributeValue(SquareOutBVO.NORIGTAXNETPRICE);
  }

  /**
   * 设置原币含税净价
   * 
   * @param norigtaxnetprice 原币含税净价
   */
  public void setNorigtaxnetprice(UFDouble norigtaxnetprice) {
    this.setAttributeValue(SquareOutBVO.NORIGTAXNETPRICE, norigtaxnetprice);
  }

  /**
   * 获取原币含税单价
   * 
   * @return 原币含税单价
   */
  public UFDouble getNorigtaxprice() {
    return (UFDouble) this.getAttributeValue(SquareOutBVO.NORIGTAXPRICE);
  }

  /**
   * 设置原币含税单价
   * 
   * @param norigtaxprice 原币含税单价
   */
  public void setNorigtaxprice(UFDouble norigtaxprice) {
    this.setAttributeValue(SquareOutBVO.NORIGTAXPRICE, norigtaxprice);
  }

  /**
   * 获取本币无税单价
   * 
   * @return 本币无税单价
   */
  public UFDouble getNprice() {
    return (UFDouble) this.getAttributeValue(SquareOutBVO.NPRICE);
  }

  /**
   * 设置本币无税单价
   * 
   * @param nprice 本币无税单价
   */
  public void setNprice(UFDouble nprice) {
    this.setAttributeValue(SquareOutBVO.NPRICE, nprice);
  }

  /**
   * 获取累计出库对冲数量
   * 
   * @return 累计出库对冲数量
   */
  public UFDouble getNrushnum() {
    return (UFDouble) this.getAttributeValue(SquareOutBVO.NRUSHNUM);
  }

  /**
   * 设置累计出库对冲数量
   * 
   * @param nrushnum 累计出库对冲数量
   */
  public void setNrushnum(UFDouble nrushnum) {
    this.setAttributeValue(SquareOutBVO.NRUSHNUM, nrushnum);
  }

  /**
   * 获取累计确认应收金额
   * 
   * @return 累计确认应收金额
   */
  public UFDouble getNsquarearmny() {
    return (UFDouble) this.getAttributeValue(SquareOutBVO.NSQUAREARMNY);
  }

  /**
   * 设置累计确认应收金额
   * 
   * @param nsquarearmny 累计确认应收金额
   */
  public void setNsquarearmny(UFDouble nsquarearmny) {
    this.setAttributeValue(SquareOutBVO.NSQUAREARMNY, nsquarearmny);
  }

  /**
   * 获取累计确认应收数量
   * 
   * @return 累计确认应收数量
   */
  public UFDouble getNsquarearnum() {
    return (UFDouble) this.getAttributeValue(SquareOutBVO.NSQUAREARNUM);
  }

  /**
   * 设置累计确认应收数量
   * 
   * @param nsquarearnum 累计确认应收数量
   */
  public void setNsquarearnum(UFDouble nsquarearnum) {
    this.setAttributeValue(SquareOutBVO.NSQUAREARNUM, nsquarearnum);
  }

  /**
   * 获取累计暂估应收数量
   * 
   * @return 累计暂估应收数量
   */
  public UFDouble getNsquareestnum() {
    return (UFDouble) this.getAttributeValue(SquareOutBVO.NSQUAREESTNUM);
  }

  /**
   * 设置累计暂估应收数量
   * 
   * @param nsquareestnum 累计暂估应收数量
   */
  public void setNsquareestnum(UFDouble nsquareestnum) {
    this.setAttributeValue(SquareOutBVO.NSQUAREESTNUM, nsquareestnum);
  }

  /**
   * 获取累计成本结算数量
   * 
   * @return 累计成本结算数量
   */
  public UFDouble getNsquareianum() {
    return (UFDouble) this.getAttributeValue(SquareOutBVO.NSQUAREIANUM);
  }

  /**
   * 设置累计成本结算数量
   * 
   * @param nsquareianum 累计成本结算数量
   */
  public void setNsquareianum(UFDouble nsquareianum) {
    this.setAttributeValue(SquareOutBVO.NSQUAREIANUM, nsquareianum);
  }

  /**
   * 获取累计发出商品数量
   * 
   * @return 累计发出商品数量
   */
  public UFDouble getNsquareregnum() {
    return (UFDouble) this.getAttributeValue(SquareOutBVO.NSQUAREREGNUM);
  }

  /**
   * 设置累计发出商品数量
   * 
   * @param nsquareregnum 累计发出商品数量
   */
  public void setNsquareregnum(UFDouble nsquareregnum) {
    this.setAttributeValue(SquareOutBVO.NSQUAREREGNUM, nsquareregnum);
  }

  /**
   * 获取本币税额
   * 
   * @return 本币税额
   */
  public UFDouble getNtax() {
    return (UFDouble) this.getAttributeValue(SquareOutBVO.NTAX);
  }

  /**
   * 设置本币税额
   * 
   * @param ntax 本币税额
   */
  public void setNtax(UFDouble ntax) {
    this.setAttributeValue(SquareOutBVO.NTAX, ntax);
  }

  /**
   * 获取本币价税合计
   * 
   * @return 本币价税合计
   */
  public UFDouble getNtaxmny() {
    return (UFDouble) this.getAttributeValue(SquareOutBVO.NTAXMNY);
  }

  /**
   * 设置本币价税合计
   * 
   * @param ntaxmny 本币价税合计
   */
  public void setNtaxmny(UFDouble ntaxmny) {
    this.setAttributeValue(SquareOutBVO.NTAXMNY, ntaxmny);
  }

  /**
   * 获取本币含税净价
   * 
   * @return 本币含税净价
   */
  public UFDouble getNtaxnetprice() {
    return (UFDouble) this.getAttributeValue(SquareOutBVO.NTAXNETPRICE);
  }

  /**
   * 设置本币含税净价
   * 
   * @param ntaxnetprice 本币含税净价
   */
  public void setNtaxnetprice(UFDouble ntaxnetprice) {
    this.setAttributeValue(SquareOutBVO.NTAXNETPRICE, ntaxnetprice);
  }

  /**
   * 获取本币含税单价
   * 
   * @return 本币含税单价
   */
  public UFDouble getNtaxprice() {
    return (UFDouble) this.getAttributeValue(SquareOutBVO.NTAXPRICE);
  }

  /**
   * 设置本币含税单价
   * 
   * @param ntaxprice 本币含税单价
   */
  public void setNtaxprice(UFDouble ntaxprice) {
    this.setAttributeValue(SquareOutBVO.NTAXPRICE, ntaxprice);
  }

  /**
   * 获取税率
   * 
   * @return 税率
   */
  public UFDouble getNtaxrate() {
    return (UFDouble) this.getAttributeValue(SquareOutBVO.NTAXRATE);
  }

  /**
   * 设置税率
   * 
   * @param ntaxrate 税率
   */
  public void setNtaxrate(UFDouble ntaxrate) {
    this.setAttributeValue(SquareOutBVO.NTAXRATE, ntaxrate);
  }

  /**
   * 获取nthisnum（可持续化字段，可以远程传递值，但不能保存到数据库）
   * 
   * @return nthisnum
   */
  public UFDouble getNthisnum() {
    return (UFDouble) this.getAttributeValue(SquareOutBVO.NTHISNUM);
  }

  /**
   * 设置nthisnum（可持续化字段，可以远程传递值，但不能保存到数据库）
   * 
   * @param nthisnum nthisnum
   */
  public void setNthisnum(UFDouble nthisnum) {
    this.setAttributeValue(SquareOutBVO.NTHISNUM, nthisnum);
  }

  /**
   * 获取累计出库及下游发票收款核销金额
   * 
   * @return 累计出库及下游发票收款核销金额
   */
  public UFDouble getNtotalpaymny() {
    return (UFDouble) this.getAttributeValue(SquareOutBVO.NTOTALPAYMNY);
  }

  /**
   * 设置累计出库及下游发票收款核销金额
   * 
   * @param ntotalpaymny 累计出库及下游发票收款核销金额
   */
  public void setNtotalpaymny(UFDouble ntotalpaymny) {
    this.setAttributeValue(SquareOutBVO.NTOTALPAYMNY, ntotalpaymny);
  }

  /**
   * 获取批次号档案
   * 
   * @return 批次号档案
   */
  public String getPk_batchcode() {
    return (String) this.getAttributeValue(SquareOutBVO.PK_BATCHCODE);
  }

  /**
   * 设置批次号档案
   * 
   * @param pk_batchcode 批次号档案
   */
  public void setPk_batchcode(String pk_batchcode) {
    this.setAttributeValue(SquareOutBVO.PK_BATCHCODE, pk_batchcode);
  }

  /**
   * 获取集团
   * 
   * @return 集团
   */
  public String getPk_group() {
    return (String) this.getAttributeValue(SquareOutBVO.PK_GROUP);
  }

  /**
   * 设置集团
   * 
   * @param pk_group 集团
   */
  public void setPk_group(String pk_group) {
    this.setAttributeValue(SquareOutBVO.PK_GROUP, pk_group);
  }

  /**
   * 获取结算财务组织
   * 
   * @return 结算财务组织
   */
  public String getPk_org() {
    return (String) this.getAttributeValue(SquareOutBVO.PK_ORG);
  }

  /**
   * 设置结算财务组织
   * 
   * @param pk_org 结算财务组织
   */
  public void setPk_org(String pk_org) {
    this.setAttributeValue(SquareOutBVO.PK_ORG, pk_org);
  }

  /**
   * 获取processeid（可持续化字段，可以远程传递值，但不能保存到数据库）
   * 
   * @return processeid
   */
  public String getProcesseid() {
    return (String) this.getAttributeValue(SquareOutBVO.PROCESSEID);
  }

  /**
   * 设置processeid（可持续化字段，可以远程传递值，但不能保存到数据库）
   * 
   * @param processeid processeid
   */
  public void setProcesseid(String processeid) {
    this.setAttributeValue(SquareOutBVO.PROCESSEID, processeid);
  }

  /**
   * 获取时间戳
   * 
   * @return 时间戳
   */
  public UFDateTime getTs() {
    return (UFDateTime) this.getAttributeValue(SquareOutBVO.TS);
  }

  /**
   * 设置时间戳
   * 
   * @param ts 时间戳
   */
  public void setTs(UFDateTime ts) {
    this.setAttributeValue(SquareOutBVO.TS, ts);
  }

  /**
   * 获取批次号
   * 
   * @return 批次号
   */
  public String getVbatchcode() {
    return (String) this.getAttributeValue(SquareOutBVO.VBATCHCODE);
  }

  /**
   * 设置批次号
   * 
   * @param vbatchcode 批次号
   */
  public void setVbatchcode(String vbatchcode) {
    this.setAttributeValue(SquareOutBVO.VBATCHCODE, vbatchcode);
  }

  /**
   * 获取表体自定义项1
   * 
   * @return 表体自定义项1
   */
  public String getVbdef1() {
    return (String) this.getAttributeValue(SquareOutBVO.VBDEF1);
  }

  /**
   * 设置表体自定义项1
   * 
   * @param vbdef1 表体自定义项1
   */
  public void setVbdef1(String vbdef1) {
    this.setAttributeValue(SquareOutBVO.VBDEF1, vbdef1);
  }

  /**
   * 获取表体自定义项10
   * 
   * @return 表体自定义项10
   */
  public String getVbdef10() {
    return (String) this.getAttributeValue(SquareOutBVO.VBDEF10);
  }

  /**
   * 设置表体自定义项10
   * 
   * @param vbdef10 表体自定义项10
   */
  public void setVbdef10(String vbdef10) {
    this.setAttributeValue(SquareOutBVO.VBDEF10, vbdef10);
  }

  /**
   * 获取表体自定义项11
   * 
   * @return 表体自定义项11
   */
  public String getVbdef11() {
    return (String) this.getAttributeValue(SquareOutBVO.VBDEF11);
  }

  /**
   * 设置表体自定义项11
   * 
   * @param vbdef11 表体自定义项11
   */
  public void setVbdef11(String vbdef11) {
    this.setAttributeValue(SquareOutBVO.VBDEF11, vbdef11);
  }

  /**
   * 获取表体自定义项12
   * 
   * @return 表体自定义项12
   */
  public String getVbdef12() {
    return (String) this.getAttributeValue(SquareOutBVO.VBDEF12);
  }

  /**
   * 设置表体自定义项12
   * 
   * @param vbdef12 表体自定义项12
   */
  public void setVbdef12(String vbdef12) {
    this.setAttributeValue(SquareOutBVO.VBDEF12, vbdef12);
  }

  /**
   * 获取表体自定义项13
   * 
   * @return 表体自定义项13
   */
  public String getVbdef13() {
    return (String) this.getAttributeValue(SquareOutBVO.VBDEF13);
  }

  /**
   * 设置表体自定义项13
   * 
   * @param vbdef13 表体自定义项13
   */
  public void setVbdef13(String vbdef13) {
    this.setAttributeValue(SquareOutBVO.VBDEF13, vbdef13);
  }

  /**
   * 获取表体自定义项14
   * 
   * @return 表体自定义项14
   */
  public String getVbdef14() {
    return (String) this.getAttributeValue(SquareOutBVO.VBDEF14);
  }

  /**
   * 设置表体自定义项14
   * 
   * @param vbdef14 表体自定义项14
   */
  public void setVbdef14(String vbdef14) {
    this.setAttributeValue(SquareOutBVO.VBDEF14, vbdef14);
  }

  /**
   * 获取表体自定义项15
   * 
   * @return 表体自定义项15
   */
  public String getVbdef15() {
    return (String) this.getAttributeValue(SquareOutBVO.VBDEF15);
  }

  /**
   * 设置表体自定义项15
   * 
   * @param vbdef15 表体自定义项15
   */
  public void setVbdef15(String vbdef15) {
    this.setAttributeValue(SquareOutBVO.VBDEF15, vbdef15);
  }

  /**
   * 获取表体自定义项16
   * 
   * @return 表体自定义项16
   */
  public String getVbdef16() {
    return (String) this.getAttributeValue(SquareOutBVO.VBDEF16);
  }

  /**
   * 设置表体自定义项16
   * 
   * @param vbdef16 表体自定义项16
   */
  public void setVbdef16(String vbdef16) {
    this.setAttributeValue(SquareOutBVO.VBDEF16, vbdef16);
  }

  /**
   * 获取表体自定义项17
   * 
   * @return 表体自定义项17
   */
  public String getVbdef17() {
    return (String) this.getAttributeValue(SquareOutBVO.VBDEF17);
  }

  /**
   * 设置表体自定义项17
   * 
   * @param vbdef17 表体自定义项17
   */
  public void setVbdef17(String vbdef17) {
    this.setAttributeValue(SquareOutBVO.VBDEF17, vbdef17);
  }

  /**
   * 获取表体自定义项18
   * 
   * @return 表体自定义项18
   */
  public String getVbdef18() {
    return (String) this.getAttributeValue(SquareOutBVO.VBDEF18);
  }

  /**
   * 设置表体自定义项18
   * 
   * @param vbdef18 表体自定义项18
   */
  public void setVbdef18(String vbdef18) {
    this.setAttributeValue(SquareOutBVO.VBDEF18, vbdef18);
  }

  /**
   * 获取表体自定义项19
   * 
   * @return 表体自定义项19
   */
  public String getVbdef19() {
    return (String) this.getAttributeValue(SquareOutBVO.VBDEF19);
  }

  /**
   * 设置表体自定义项19
   * 
   * @param vbdef19 表体自定义项19
   */
  public void setVbdef19(String vbdef19) {
    this.setAttributeValue(SquareOutBVO.VBDEF19, vbdef19);
  }

  /**
   * 获取表体自定义项2
   * 
   * @return 表体自定义项2
   */
  public String getVbdef2() {
    return (String) this.getAttributeValue(SquareOutBVO.VBDEF2);
  }

  /**
   * 设置表体自定义项2
   * 
   * @param vbdef2 表体自定义项2
   */
  public void setVbdef2(String vbdef2) {
    this.setAttributeValue(SquareOutBVO.VBDEF2, vbdef2);
  }

  /**
   * 获取表体自定义项20
   * 
   * @return 表体自定义项20
   */
  public String getVbdef20() {
    return (String) this.getAttributeValue(SquareOutBVO.VBDEF20);
  }

  /**
   * 设置表体自定义项20
   * 
   * @param vbdef20 表体自定义项20
   */
  public void setVbdef20(String vbdef20) {
    this.setAttributeValue(SquareOutBVO.VBDEF20, vbdef20);
  }

  /**
   * 获取表体自定义项3
   * 
   * @return 表体自定义项3
   */
  public String getVbdef3() {
    return (String) this.getAttributeValue(SquareOutBVO.VBDEF3);
  }

  /**
   * 设置表体自定义项3
   * 
   * @param vbdef3 表体自定义项3
   */
  public void setVbdef3(String vbdef3) {
    this.setAttributeValue(SquareOutBVO.VBDEF3, vbdef3);
  }

  /**
   * 获取表体自定义项4
   * 
   * @return 表体自定义项4
   */
  public String getVbdef4() {
    return (String) this.getAttributeValue(SquareOutBVO.VBDEF4);
  }

  /**
   * 设置表体自定义项4
   * 
   * @param vbdef4 表体自定义项4
   */
  public void setVbdef4(String vbdef4) {
    this.setAttributeValue(SquareOutBVO.VBDEF4, vbdef4);
  }

  /**
   * 获取表体自定义项5
   * 
   * @return 表体自定义项5
   */
  public String getVbdef5() {
    return (String) this.getAttributeValue(SquareOutBVO.VBDEF5);
  }

  /**
   * 设置表体自定义项5
   * 
   * @param vbdef5 表体自定义项5
   */
  public void setVbdef5(String vbdef5) {
    this.setAttributeValue(SquareOutBVO.VBDEF5, vbdef5);
  }

  /**
   * 获取表体自定义项6
   * 
   * @return 表体自定义项6
   */
  public String getVbdef6() {
    return (String) this.getAttributeValue(SquareOutBVO.VBDEF6);
  }

  /**
   * 设置表体自定义项6
   * 
   * @param vbdef6 表体自定义项6
   */
  public void setVbdef6(String vbdef6) {
    this.setAttributeValue(SquareOutBVO.VBDEF6, vbdef6);
  }

  /**
   * 获取表体自定义项7
   * 
   * @return 表体自定义项7
   */
  public String getVbdef7() {
    return (String) this.getAttributeValue(SquareOutBVO.VBDEF7);
  }

  /**
   * 设置表体自定义项7
   * 
   * @param vbdef7 表体自定义项7
   */
  public void setVbdef7(String vbdef7) {
    this.setAttributeValue(SquareOutBVO.VBDEF7, vbdef7);
  }

  /**
   * 获取表体自定义项8
   * 
   * @return 表体自定义项8
   */
  public String getVbdef8() {
    return (String) this.getAttributeValue(SquareOutBVO.VBDEF8);
  }

  /**
   * 设置表体自定义项8
   * 
   * @param vbdef8 表体自定义项8
   */
  public void setVbdef8(String vbdef8) {
    this.setAttributeValue(SquareOutBVO.VBDEF8, vbdef8);
  }

  /**
   * 获取表体自定义项9
   * 
   * @return 表体自定义项9
   */
  public String getVbdef9() {
    return (String) this.getAttributeValue(SquareOutBVO.VBDEF9);
  }

  /**
   * 设置表体自定义项9
   * 
   * @param vbdef9 表体自定义项9
   */
  public void setVbdef9(String vbdef9) {
    this.setAttributeValue(SquareOutBVO.VBDEF9, vbdef9);
  }

  /**
   * 获取单位换算率
   * 
   * @return 单位换算率
   */
  public String getVchangerate() {
    return (String) this.getAttributeValue(SquareOutBVO.VCHANGERATE);
  }

  /**
   * 设置单位换算率
   * 
   * @param vchangerate 单位换算率
   */
  public void setVchangerate(String vchangerate) {
    this.setAttributeValue(SquareOutBVO.VCHANGERATE, vchangerate);
  }

  /**
   * 获取合同号
   * 
   * @return 合同号
   */
  public String getVctcode() {
    return (String) this.getAttributeValue(SquareOutBVO.VCTCODE);
  }

  /**
   * 设置合同号
   * 
   * @param vctcode 合同号
   */
  public void setVctcode(String vctcode) {
    this.setAttributeValue(SquareOutBVO.VCTCODE, vctcode);
  }

  /**
   * 获取源头单据号
   * 
   * @return 源头单据号
   */
  public String getVfirstcode() {
    return (String) this.getAttributeValue(SquareOutBVO.VFIRSTCODE);
  }

  /**
   * 设置源头单据号
   * 
   * @param vfirstcode 源头单据号
   */
  public void setVfirstcode(String vfirstcode) {
    this.setAttributeValue(SquareOutBVO.VFIRSTCODE, vfirstcode);
  }

  /**
   * 获取源头单据行号
   * 
   * @return 源头单据行号
   */
  public String getVfirstrowno() {
    return (String) this.getAttributeValue(SquareOutBVO.VFIRSTROWNO);
  }

  /**
   * 设置源头单据行号
   * 
   * @param vfirstrowno 源头单据行号
   */
  public void setVfirstrowno(String vfirstrowno) {
    this.setAttributeValue(SquareOutBVO.VFIRSTROWNO, vfirstrowno);
  }

  /**
   * 获取源头单据交易类型
   * 
   * @return 源头单据交易类型
   */
  public String getVfirsttrantype() {
    return (String) this.getAttributeValue(SquareOutBVO.VFIRSTTRANTYPE);
  }

  /**
   * 设置源头单据交易类型
   * 
   * @param vfirsttrantype 源头单据交易类型
   */
  public void setVfirsttrantype(String vfirsttrantype) {
    this.setAttributeValue(SquareOutBVO.VFIRSTTRANTYPE, vfirsttrantype);
  }

  /**
   * 获取源头单据类型
   * 
   * @return 源头单据类型
   */
  public String getVfirsttype() {
    return (String) this.getAttributeValue(SquareOutBVO.VFIRSTTYPE);
  }

  /**
   * 设置源头单据类型
   * 
   * @param vfirsttype 源头单据类型
   */
  public void setVfirsttype(String vfirsttype) {
    this.setAttributeValue(SquareOutBVO.VFIRSTTYPE, vfirsttype);
  }

  /**
   * 获取自由辅助属性1
   * 
   * @return 自由辅助属性1
   */
  public String getVfree1() {
    return (String) this.getAttributeValue(SquareOutBVO.VFREE1);
  }

  /**
   * 设置自由辅助属性1
   * 
   * @param vfree1 自由辅助属性1
   */
  public void setVfree1(String vfree1) {
    this.setAttributeValue(SquareOutBVO.VFREE1, vfree1);
  }

  /**
   * 获取自由辅助属性10
   * 
   * @return 自由辅助属性10
   */
  public String getVfree10() {
    return (String) this.getAttributeValue(SquareOutBVO.VFREE10);
  }

  /**
   * 设置自由辅助属性10
   * 
   * @param vfree10 自由辅助属性10
   */
  public void setVfree10(String vfree10) {
    this.setAttributeValue(SquareOutBVO.VFREE10, vfree10);
  }

  /**
   * 获取自由辅助属性2
   * 
   * @return 自由辅助属性2
   */
  public String getVfree2() {
    return (String) this.getAttributeValue(SquareOutBVO.VFREE2);
  }

  /**
   * 设置自由辅助属性2
   * 
   * @param vfree2 自由辅助属性2
   */
  public void setVfree2(String vfree2) {
    this.setAttributeValue(SquareOutBVO.VFREE2, vfree2);
  }

  /**
   * 获取自由辅助属性3
   * 
   * @return 自由辅助属性3
   */
  public String getVfree3() {
    return (String) this.getAttributeValue(SquareOutBVO.VFREE3);
  }

  /**
   * 设置自由辅助属性3
   * 
   * @param vfree3 自由辅助属性3
   */
  public void setVfree3(String vfree3) {
    this.setAttributeValue(SquareOutBVO.VFREE3, vfree3);
  }

  /**
   * 获取自由辅助属性4
   * 
   * @return 自由辅助属性4
   */
  public String getVfree4() {
    return (String) this.getAttributeValue(SquareOutBVO.VFREE4);
  }

  /**
   * 设置自由辅助属性4
   * 
   * @param vfree4 自由辅助属性4
   */
  public void setVfree4(String vfree4) {
    this.setAttributeValue(SquareOutBVO.VFREE4, vfree4);
  }

  /**
   * 获取自由辅助属性5
   * 
   * @return 自由辅助属性5
   */
  public String getVfree5() {
    return (String) this.getAttributeValue(SquareOutBVO.VFREE5);
  }

  /**
   * 设置自由辅助属性5
   * 
   * @param vfree5 自由辅助属性5
   */
  public void setVfree5(String vfree5) {
    this.setAttributeValue(SquareOutBVO.VFREE5, vfree5);
  }

  /**
   * 获取自由辅助属性6
   * 
   * @return 自由辅助属性6
   */
  public String getVfree6() {
    return (String) this.getAttributeValue(SquareOutBVO.VFREE6);
  }

  /**
   * 设置自由辅助属性6
   * 
   * @param vfree6 自由辅助属性6
   */
  public void setVfree6(String vfree6) {
    this.setAttributeValue(SquareOutBVO.VFREE6, vfree6);
  }

  /**
   * 获取自由辅助属性7
   * 
   * @return 自由辅助属性7
   */
  public String getVfree7() {
    return (String) this.getAttributeValue(SquareOutBVO.VFREE7);
  }

  /**
   * 设置自由辅助属性7
   * 
   * @param vfree7 自由辅助属性7
   */
  public void setVfree7(String vfree7) {
    this.setAttributeValue(SquareOutBVO.VFREE7, vfree7);
  }

  /**
   * 获取自由辅助属性8
   * 
   * @return 自由辅助属性8
   */
  public String getVfree8() {
    return (String) this.getAttributeValue(SquareOutBVO.VFREE8);
  }

  /**
   * 设置自由辅助属性8
   * 
   * @param vfree8 自由辅助属性8
   */
  public void setVfree8(String vfree8) {
    this.setAttributeValue(SquareOutBVO.VFREE8, vfree8);
  }

  /**
   * 获取自由辅助属性9
   * 
   * @return 自由辅助属性9
   */
  public String getVfree9() {
    return (String) this.getAttributeValue(SquareOutBVO.VFREE9);
  }

  /**
   * 设置自由辅助属性9
   * 
   * @param vfree9 自由辅助属性9
   */
  public void setVfree9(String vfree9) {
    this.setAttributeValue(SquareOutBVO.VFREE9, vfree9);
  }

  /**
   * 获取行备注
   * 
   * @return 行备注
   */
  public String getVrownote() {
    return (String) this.getAttributeValue(SquareOutBVO.VROWNOTE);
  }

  /**
   * 设置行备注
   * 
   * @param vrownote 行备注
   */
  public void setVrownote(String vrownote) {
    this.setAttributeValue(SquareOutBVO.VROWNOTE, vrownote);
  }

  /**
   * 获取来源单据号
   * 
   * @return 来源单据号
   */
  public String getVsrccode() {
    return (String) this.getAttributeValue(SquareOutBVO.VSRCCODE);
  }

  /**
   * 设置来源单据号
   * 
   * @param vsrccode 来源单据号
   */
  public void setVsrccode(String vsrccode) {
    this.setAttributeValue(SquareOutBVO.VSRCCODE, vsrccode);
  }

  /**
   * 获取来源单据行号
   * 
   * @return 来源单据行号
   */
  public String getVsrcrowno() {
    return (String) this.getAttributeValue(SquareOutBVO.VSRCROWNO);
  }

  /**
   * 设置来源单据行号
   * 
   * @param vsrcrowno 来源单据行号
   */
  public void setVsrcrowno(String vsrcrowno) {
    this.setAttributeValue(SquareOutBVO.VSRCROWNO, vsrcrowno);
  }

  /**
   * 获取来源单据交易类型
   * 
   * @return 来源单据交易类型
   */
  public String getVsrctrantype() {
    return (String) this.getAttributeValue(SquareOutBVO.VSRCTRANTYPE);
  }

  /**
   * 设置来源单据交易类型
   * 
   * @param vsrctrantype 来源单据交易类型
   */
  public void setVsrctrantype(String vsrctrantype) {
    this.setAttributeValue(SquareOutBVO.VSRCTRANTYPE, vsrctrantype);
  }

  /**
   * 获取来源单据类型
   * 
   * @return 来源单据类型
   */
  public String getVsrctype() {
    return (String) this.getAttributeValue(SquareOutBVO.VSRCTYPE);
  }

  /**
   * 设置来源单据类型
   * 
   * @param vsrctype 来源单据类型
   */
  public void setVsrctype(String vsrctype) {
    this.setAttributeValue(SquareOutBVO.VSRCTYPE, vsrctype);
  }

  @Override
  public IVOMeta getMetaData() {
    IVOMeta meta =
        VOMetaFactory.getInstance().getVOMeta(SquareOutBVO.ENTITYNAME);
    return meta;
  }

  public void setDr(Integer dr) {
    this.setAttributeValue(SquareInvBVO.DR, dr);
  }

  public Integer getDr() {
    return (Integer) this.getAttributeValue(SquareInvBVO.DR);
  }

  public void setNtotalsquarenum(UFDouble ntotalsquarenum) {
    this.setAttributeValue(SquareOutBVO.NTOTALSQUARENUM, ntotalsquarenum);
  }

  public UFDouble getNtotalsquarenum() {
    return (UFDouble) this.getAttributeValue(SquareOutBVO.NTOTALSQUARENUM);
  }

  public void setCsquareoutbvoid(String csquareoutbvoid) {
    this.setAttributeValue(SquareOutDetailVO.CSQUAREOUTBVOID, csquareoutbvoid);
  }

  public String getCsquareoutbvoid() {
    return (String) this.getAttributeValue(SquareOutDetailVO.CSQUAREOUTBVOID);
  }
  
  /**
   * 获取特征码
   * 
   * @return 特征码
   */
  public String  getCmffileid() {
    return (String) this.getAttributeValue(SquareOutBVO.CMFFILEID);
  }

  /**
   * 设置特征码
   * 
   * @param 特征码
   */
  public void setCmffileid(String cmffileid) {
    this.setAttributeValue(SquareOutBVO.CMFFILEID, cmffileid);
  }
}
