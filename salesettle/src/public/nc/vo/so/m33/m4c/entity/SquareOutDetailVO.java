package nc.vo.so.m33.m4c.entity;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;
import nc.vo.so.m33.enumeration.SquareType;
import nc.vo.so.m33.m32.entity.SquareInvBVO;

/**
 * 销售出库单结算单明细实体
 * 
 * @since 6.1
 * @version 2013-05-08 13:59:51
 * @author yixl
 */
public class SquareOutDetailVO extends SuperVO {

  private static final long serialVersionUID = 4245259221757655909L;

  /**
   * 销售出库单结算单明细实体
   */
  public static final String ENTITYNAME = "so.SquareOutDetailVO";

  /**
   * 删除标志dr
   */
  public static final String DR = "dr";

  /**
   * 销售出库单待结算单voID(程序中临时用，元数据上没有)
   * 待结算vo与明细vo的对应关系，因为可能待结算vo拆成1行拆成多行SquareOutDetailVO
   * 所以无法用行id对应
   */
  public static final String CSQUAREOUTBVOID = "csquareoutbvoid";

  /**
   * 是否自动结算
   */
  public static final String BAUTOSQUAREFLAG = "bautosquareflag";

  /**
   * 是否出库对冲
   */
  public static final String BOUTRUSHFLAG = "boutrushflag";

  /**
   * 单位
   */
  public static final String CASTUNITID = "castunitid";

  /**
   * 本币
   */
  public static final String CCURRENCYID = "ccurrencyid";

  /**
   * 原币
   */
  public static final String CORIGCURRENCYID = "corigcurrencyid";

  /**
   * 对冲出库单子表id
   */
  public static final String CRUSHGENERALBID = "crushgeneralbid";

  /**
   * 对冲批次号
   */
  public static final String CRUSHOUTBATCHID = "crushoutbatchid";

  /**
   * 销售出库结算单子实体
   */
  public static final String CSALESQUAREBID = "csalesquarebid";

  /**
   * 销售出库结算单明细实体
   */
  public static final String CSALESQUAREDID = "csalesquaredid";

  /**
   * 销售出库结算单主实体
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
   * 主单位
   */
  public static final String CUNITID = "cunitid";

  /**
   * 销售出库单单据日期
   */
  public static final String DBILLDATE = "dbilldate";

  /**
   * 结算类型
   */
  public static final String FSQUARETYPE = "fsquaretype";

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
   * 本次结算数量
   */
  public static final String NSQUARENUM = "nsquarenum";

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
   * 集团
   */
  public static final String PK_GROUP = "pk_group";

  /**
   * 结算财务组织
   */
  public static final String PK_ORG = "pk_org";

  /**
   * 结算批次号
   */
  public static final String PROCESSEID = "processeid";

  /**
   * 时间戳
   */
  public static final String TS = "ts";

  /**
   * 单位换算率
   */
  public static final String VCHANGERATE = "vchangerate";

  /**
   * 对冲出库单号
   */
  public static final String VRUSHBILLCODE = "vrushbillcode";

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
   * 成本单价
   */
  public static final String NCOSTPRICE = "ncostprice";

  /**
   * 成本金额
   */
  public static final String NCOSTMNY = "ncostmny";

  /**
   * 获取是否自动结算
   * 
   * @return 是否自动结算
   */
  public UFBoolean getBautosquareflag() {
    return (UFBoolean) this
        .getAttributeValue(SquareOutDetailVO.BAUTOSQUAREFLAG);
  }

  /**
   * 设置是否自动结算
   * 
   * @param bautosquareflag 是否自动结算
   */
  public void setBautosquareflag(UFBoolean bautosquareflag) {
    this.setAttributeValue(SquareOutDetailVO.BAUTOSQUAREFLAG, bautosquareflag);
  }

  /**
   * 获取是否出库对冲
   * 
   * @return 是否出库对冲
   */
  public UFBoolean getBoutrushflag() {
    return (UFBoolean) this.getAttributeValue(SquareOutDetailVO.BOUTRUSHFLAG);
  }

  /**
   * 设置是否出库对冲
   * 
   * @param boutrushflag 是否出库对冲
   */
  public void setBoutrushflag(UFBoolean boutrushflag) {
    this.setAttributeValue(SquareOutDetailVO.BOUTRUSHFLAG, boutrushflag);
  }

  /**
   * 获取单位
   * 
   * @return 单位
   */
  public String getCastunitid() {
    return (String) this.getAttributeValue(SquareOutDetailVO.CASTUNITID);
  }

  /**
   * 设置单位
   * 
   * @param castunitid 单位
   */
  public void setCastunitid(String castunitid) {
    this.setAttributeValue(SquareOutDetailVO.CASTUNITID, castunitid);
  }

  /**
   * 获取本币
   * 
   * @return 本币
   */
  public String getCcurrencyid() {
    return (String) this.getAttributeValue(SquareOutDetailVO.CCURRENCYID);
  }

  /**
   * 设置本币
   * 
   * @param ccurrencyid 本币
   */
  public void setCcurrencyid(String ccurrencyid) {
    this.setAttributeValue(SquareOutDetailVO.CCURRENCYID, ccurrencyid);
  }

  /**
   * 获取原币
   * 
   * @return 原币
   */
  public String getCorigcurrencyid() {
    return (String) this.getAttributeValue(SquareOutDetailVO.CORIGCURRENCYID);
  }

  /**
   * 设置原币
   * 
   * @param corigcurrencyid 原币
   */
  public void setCorigcurrencyid(String corigcurrencyid) {
    this.setAttributeValue(SquareOutDetailVO.CORIGCURRENCYID, corigcurrencyid);
  }

  /**
   * 获取对冲出库单子表id
   * 
   * @return 对冲出库单子表id
   */
  public String getCrushgeneralbid() {
    return (String) this.getAttributeValue(SquareOutDetailVO.CRUSHGENERALBID);
  }

  /**
   * 设置对冲出库单子表id
   * 
   * @param crushgeneralbid 对冲出库单子表id
   */
  public void setCrushgeneralbid(String crushgeneralbid) {
    this.setAttributeValue(SquareOutDetailVO.CRUSHGENERALBID, crushgeneralbid);
  }

  /**
   * 获取对冲批次号
   * 
   * @return 对冲批次号
   */
  public String getCrushoutbatchid() {
    return (String) this.getAttributeValue(SquareOutDetailVO.CRUSHOUTBATCHID);
  }

  /**
   * 设置对冲批次号
   * 
   * @param crushoutbatchid 对冲批次号
   */
  public void setCrushoutbatchid(String crushoutbatchid) {
    this.setAttributeValue(SquareOutDetailVO.CRUSHOUTBATCHID, crushoutbatchid);
  }

  /**
   * 获取销售出库结算单子实体
   * 
   * @return 销售出库结算单子实体
   */
  public String getCsalesquarebid() {
    return (String) this.getAttributeValue(SquareOutDetailVO.CSALESQUAREBID);
  }

  /**
   * 设置销售出库结算单子实体
   * 
   * @param csalesquarebid 销售出库结算单子实体
   */
  public void setCsalesquarebid(String csalesquarebid) {
    this.setAttributeValue(SquareOutDetailVO.CSALESQUAREBID, csalesquarebid);
  }

  /**
   * 获取销售出库结算单明细实体
   * 
   * @return 销售出库结算单明细实体
   */
  public String getCsalesquaredid() {
    return (String) this.getAttributeValue(SquareOutDetailVO.CSALESQUAREDID);
  }

  /**
   * 设置销售出库结算单明细实体
   * 
   * @param csalesquaredid 销售出库结算单明细实体
   */
  public void setCsalesquaredid(String csalesquaredid) {
    this.setAttributeValue(SquareOutDetailVO.CSALESQUAREDID, csalesquaredid);
  }

  /**
   * 获取销售出库结算单主实体
   * 
   * @return 销售出库结算单主实体
   */
  public String getCsalesquareid() {
    return (String) this.getAttributeValue(SquareOutDetailVO.CSALESQUAREID);
  }

  /**
   * 设置销售出库结算单主实体
   * 
   * @param csalesquareid 销售出库结算单主实体
   */
  public void setCsalesquareid(String csalesquareid) {
    this.setAttributeValue(SquareOutDetailVO.CSALESQUAREID, csalesquareid);
  }

  /**
   * 获取销售出库单子实体
   * 
   * @return 销售出库单子实体
   */
  public String getCsquarebillbid() {
    return (String) this.getAttributeValue(SquareOutDetailVO.CSQUAREBILLBID);
  }

  /**
   * 设置销售出库单子实体
   * 
   * @param csquarebillbid 销售出库单子实体
   */
  public void setCsquarebillbid(String csquarebillbid) {
    this.setAttributeValue(SquareOutDetailVO.CSQUAREBILLBID, csquarebillbid);
  }

  /**
   * 获取销售出库单主实体
   * 
   * @return 销售出库单主实体
   */
  public String getCsquarebillid() {
    return (String) this.getAttributeValue(SquareOutDetailVO.CSQUAREBILLID);
  }

  /**
   * 设置销售出库单主实体
   * 
   * @param csquarebillid 销售出库单主实体
   */
  public void setCsquarebillid(String csquarebillid) {
    this.setAttributeValue(SquareOutDetailVO.CSQUAREBILLID, csquarebillid);
  }

  /**
   * 获取税码
   * 
   * @return 税码
   */
  public String getCtaxcodeid() {
    return (String) this.getAttributeValue(SquareOutDetailVO.CTAXCODEID);
  }

  /**
   * 设置税码
   * 
   * @param ctaxcodeid 税码
   */
  public void setCtaxcodeid(String ctaxcodeid) {
    this.setAttributeValue(SquareOutDetailVO.CTAXCODEID, ctaxcodeid);
  }

  /**
   * 获取主单位
   * 
   * @return 主单位
   */
  public String getCunitid() {
    return (String) this.getAttributeValue(SquareOutDetailVO.CUNITID);
  }

  /**
   * 设置主单位
   * 
   * @param cunitid 主单位
   */
  public void setCunitid(String cunitid) {
    this.setAttributeValue(SquareOutDetailVO.CUNITID, cunitid);
  }

  /**
   * 获取销售出库单单据日期
   * 
   * @return 销售出库单单据日期
   */
  public UFDate getDbilldate() {
    return (UFDate) this.getAttributeValue(SquareOutDetailVO.DBILLDATE);
  }

  /**
   * 设置销售出库单单据日期
   * 
   * @param dbilldate 销售出库单单据日期
   */
  public void setDbilldate(UFDate dbilldate) {
    this.setAttributeValue(SquareOutDetailVO.DBILLDATE, dbilldate);
  }

  /**
   * 获取结算类型
   * 
   * @return 结算类型
   * @see SquareType
   */
  public Integer getFsquaretype() {
    return (Integer) this.getAttributeValue(SquareOutDetailVO.FSQUARETYPE);
  }

  /**
   * 设置结算类型
   * 
   * @param fsquaretype 结算类型
   * @see SquareType
   */
  public void setFsquaretype(Integer fsquaretype) {
    this.setAttributeValue(SquareOutDetailVO.FSQUARETYPE, fsquaretype);
  }

  /**
   * 获取扣税类别
   * 
   * @return 扣税类别
   */
  public Integer getFtaxtypeflag() {
    return (Integer) this.getAttributeValue(SquareOutDetailVO.FTAXTYPEFLAG);
  }

  /**
   * 设置扣税类别
   * 
   * @param ftaxtypeflag 扣税类别
   */
  public void setFtaxtypeflag(Integer ftaxtypeflag) {
    this.setAttributeValue(SquareOutDetailVO.FTAXTYPEFLAG, ftaxtypeflag);
  }

  /**
   * 获取单位数量
   * 
   * @return 单位数量
   */
  public UFDouble getNastnum() {
    return (UFDouble) this.getAttributeValue(SquareOutDetailVO.NASTNUM);
  }

  /**
   * 设置单位数量
   * 
   * @param nastnum 单位数量
   */
  public void setNastnum(UFDouble nastnum) {
    this.setAttributeValue(SquareOutDetailVO.NASTNUM, nastnum);
  }

  /**
   * 获取计税金额
   * 
   * @return 计税金额
   */
  public UFDouble getNcaltaxmny() {
    return (UFDouble) this.getAttributeValue(SquareOutDetailVO.NCALTAXMNY);
  }

  /**
   * 获得成本单价
   * 
   * @return 成本单价
   */
  public UFDouble getNcostprice() {
    return (UFDouble) this.getAttributeValue(SquareOutDetailVO.NCOSTPRICE);
  }

  /**
   * 获得成本金额
   * 
   * @return 成本金额
   */
  public UFDouble getNcostmny() {
    return (UFDouble) this.getAttributeValue(SquareOutDetailVO.NCOSTMNY);
  }

  /**
   * 设置计税金额
   * 
   * @param ncaltaxmny 计税金额
   */
  public void setNcaltaxmny(UFDouble ncaltaxmny) {
    this.setAttributeValue(SquareOutDetailVO.NCALTAXMNY, ncaltaxmny);
  }

  /**
   * 设置成本单价
   * 
   * @param ncostprice
   */
  public void setNcostprice(UFDouble ncostprice) {
    this.setAttributeValue(SquareOutDetailVO.NCOSTPRICE, ncostprice);
  }

  /**
   * 设置成本金额
   * 
   * @param ncostmny
   */
  public void setNcostmny(UFDouble ncostmny) {
    this.setAttributeValue(SquareOutDetailVO.NCOSTMNY, ncostmny);
  }

  /**
   * 获取本币折扣额
   * 
   * @return 本币折扣额
   */
  public UFDouble getNdiscount() {
    return (UFDouble) this.getAttributeValue(SquareOutDetailVO.NDISCOUNT);
  }

  /**
   * 设置本币折扣额
   * 
   * @param ndiscount 本币折扣额
   */
  public void setNdiscount(UFDouble ndiscount) {
    this.setAttributeValue(SquareOutDetailVO.NDISCOUNT, ndiscount);
  }

  /**
   * 获取折本汇率
   * 
   * @return 折本汇率
   */
  public UFDouble getNexchangerate() {
    return (UFDouble) this.getAttributeValue(SquareOutDetailVO.NEXCHANGERATE);
  }

  /**
   * 设置折本汇率
   * 
   * @param nexchangerate 折本汇率
   */
  public void setNexchangerate(UFDouble nexchangerate) {
    this.setAttributeValue(SquareOutDetailVO.NEXCHANGERATE, nexchangerate);
  }

  /**
   * 获取全局本位币汇率
   * 
   * @return 全局本位币汇率
   */
  public UFDouble getNglobalexchgrate() {
    return (UFDouble) this
        .getAttributeValue(SquareOutDetailVO.NGLOBALEXCHGRATE);
  }

  /**
   * 设置全局本位币汇率
   * 
   * @param nglobalexchgrate 全局本位币汇率
   */
  public void setNglobalexchgrate(UFDouble nglobalexchgrate) {
    this.setAttributeValue(SquareOutDetailVO.NGLOBALEXCHGRATE, nglobalexchgrate);
  }

  /**
   * 获取全局本币无税金额
   * 
   * @return 全局本币无税金额
   */
  public UFDouble getNglobalmny() {
    return (UFDouble) this.getAttributeValue(SquareOutDetailVO.NGLOBALMNY);
  }

  /**
   * 设置全局本币无税金额
   * 
   * @param nglobalmny 全局本币无税金额
   */
  public void setNglobalmny(UFDouble nglobalmny) {
    this.setAttributeValue(SquareOutDetailVO.NGLOBALMNY, nglobalmny);
  }

  /**
   * 获取全局本币价税合计
   * 
   * @return 全局本币价税合计
   */
  public UFDouble getNglobaltaxmny() {
    return (UFDouble) this.getAttributeValue(SquareOutDetailVO.NGLOBALTAXMNY);
  }

  /**
   * 设置全局本币价税合计
   * 
   * @param nglobaltaxmny 全局本币价税合计
   */
  public void setNglobaltaxmny(UFDouble nglobaltaxmny) {
    this.setAttributeValue(SquareOutDetailVO.NGLOBALTAXMNY, nglobaltaxmny);
  }

  /**
   * 获取集团本位币汇率
   * 
   * @return 集团本位币汇率
   */
  public UFDouble getNgroupexchgrate() {
    return (UFDouble) this.getAttributeValue(SquareOutDetailVO.NGROUPEXCHGRATE);
  }

  /**
   * 设置集团本位币汇率
   * 
   * @param ngroupexchgrate 集团本位币汇率
   */
  public void setNgroupexchgrate(UFDouble ngroupexchgrate) {
    this.setAttributeValue(SquareOutDetailVO.NGROUPEXCHGRATE, ngroupexchgrate);
  }

  /**
   * 获取集团本币无税金额
   * 
   * @return 集团本币无税金额
   */
  public UFDouble getNgroupmny() {
    return (UFDouble) this.getAttributeValue(SquareOutDetailVO.NGROUPMNY);
  }

  /**
   * 设置集团本币无税金额
   * 
   * @param ngroupmny 集团本币无税金额
   */
  public void setNgroupmny(UFDouble ngroupmny) {
    this.setAttributeValue(SquareOutDetailVO.NGROUPMNY, ngroupmny);
  }

  /**
   * 获取集团本币价税合计
   * 
   * @return 集团本币价税合计
   */
  public UFDouble getNgrouptaxmny() {
    return (UFDouble) this.getAttributeValue(SquareOutDetailVO.NGROUPTAXMNY);
  }

  /**
   * 设置集团本币价税合计
   * 
   * @param ngrouptaxmny 集团本币价税合计
   */
  public void setNgrouptaxmny(UFDouble ngrouptaxmny) {
    this.setAttributeValue(SquareOutDetailVO.NGROUPTAXMNY, ngrouptaxmny);
  }

  /**
   * 获取单品折扣率
   * 
   * @return 单品折扣率
   */
  public UFDouble getNitemdiscountrate() {
    return (UFDouble) this
        .getAttributeValue(SquareOutDetailVO.NITEMDISCOUNTRATE);
  }

  /**
   * 设置单品折扣率
   * 
   * @param nitemdiscountrate 单品折扣率
   */
  public void setNitemdiscountrate(UFDouble nitemdiscountrate) {
    this.setAttributeValue(SquareOutDetailVO.NITEMDISCOUNTRATE,
        nitemdiscountrate);
  }

  /**
   * 获取本币无税金额
   * 
   * @return 本币无税金额
   */
  public UFDouble getNmny() {
    return (UFDouble) this.getAttributeValue(SquareOutDetailVO.NMNY);
  }

  /**
   * 设置本币无税金额
   * 
   * @param nmny 本币无税金额
   */
  public void setNmny(UFDouble nmny) {
    this.setAttributeValue(SquareOutDetailVO.NMNY, nmny);
  }

  /**
   * 获取本币无税净价
   * 
   * @return 本币无税净价
   */
  public UFDouble getNnetprice() {
    return (UFDouble) this.getAttributeValue(SquareOutDetailVO.NNETPRICE);
  }

  /**
   * 设置本币无税净价
   * 
   * @param nnetprice 本币无税净价
   */
  public void setNnetprice(UFDouble nnetprice) {
    this.setAttributeValue(SquareOutDetailVO.NNETPRICE, nnetprice);
  }

  /**
   * 获取主单位数量
   * 
   * @return 主单位数量
   */
  public UFDouble getNnum() {
    return (UFDouble) this.getAttributeValue(SquareOutDetailVO.NNUM);
  }

  /**
   * 设置主单位数量
   * 
   * @param nnum 主单位数量
   */
  public void setNnum(UFDouble nnum) {
    this.setAttributeValue(SquareOutDetailVO.NNUM, nnum);
  }

  /**
   * 获取原币折扣额
   * 
   * @return 原币折扣额
   */
  public UFDouble getNorigdiscount() {
    return (UFDouble) this.getAttributeValue(SquareOutDetailVO.NORIGDISCOUNT);
  }

  /**
   * 设置原币折扣额
   * 
   * @param norigdiscount 原币折扣额
   */
  public void setNorigdiscount(UFDouble norigdiscount) {
    this.setAttributeValue(SquareOutDetailVO.NORIGDISCOUNT, norigdiscount);
  }

  /**
   * 获取原币无税金额
   * 
   * @return 原币无税金额
   */
  public UFDouble getNorigmny() {
    return (UFDouble) this.getAttributeValue(SquareOutDetailVO.NORIGMNY);
  }

  /**
   * 设置原币无税金额
   * 
   * @param norigmny 原币无税金额
   */
  public void setNorigmny(UFDouble norigmny) {
    this.setAttributeValue(SquareOutDetailVO.NORIGMNY, norigmny);
  }

  /**
   * 获取原币无税净价
   * 
   * @return 原币无税净价
   */
  public UFDouble getNorignetprice() {
    return (UFDouble) this.getAttributeValue(SquareOutDetailVO.NORIGNETPRICE);
  }

  /**
   * 设置原币无税净价
   * 
   * @param norignetprice 原币无税净价
   */
  public void setNorignetprice(UFDouble norignetprice) {
    this.setAttributeValue(SquareOutDetailVO.NORIGNETPRICE, norignetprice);
  }

  /**
   * 获取原币无税单价
   * 
   * @return 原币无税单价
   */
  public UFDouble getNorigprice() {
    return (UFDouble) this.getAttributeValue(SquareOutDetailVO.NORIGPRICE);
  }

  /**
   * 设置原币无税单价
   * 
   * @param norigprice 原币无税单价
   */
  public void setNorigprice(UFDouble norigprice) {
    this.setAttributeValue(SquareOutDetailVO.NORIGPRICE, norigprice);
  }

  /**
   * 获取原币价税合计
   * 
   * @return 原币价税合计
   */
  public UFDouble getNorigtaxmny() {
    return (UFDouble) this.getAttributeValue(SquareOutDetailVO.NORIGTAXMNY);
  }

  /**
   * 设置原币价税合计
   * 
   * @param norigtaxmny 原币价税合计
   */
  public void setNorigtaxmny(UFDouble norigtaxmny) {
    this.setAttributeValue(SquareOutDetailVO.NORIGTAXMNY, norigtaxmny);
  }

  /**
   * 获取原币含税净价
   * 
   * @return 原币含税净价
   */
  public UFDouble getNorigtaxnetprice() {
    return (UFDouble) this
        .getAttributeValue(SquareOutDetailVO.NORIGTAXNETPRICE);
  }

  /**
   * 设置原币含税净价
   * 
   * @param norigtaxnetprice 原币含税净价
   */
  public void setNorigtaxnetprice(UFDouble norigtaxnetprice) {
    this.setAttributeValue(SquareOutDetailVO.NORIGTAXNETPRICE, norigtaxnetprice);
  }

  /**
   * 获取原币含税单价
   * 
   * @return 原币含税单价
   */
  public UFDouble getNorigtaxprice() {
    return (UFDouble) this.getAttributeValue(SquareOutDetailVO.NORIGTAXPRICE);
  }

  /**
   * 设置原币含税单价
   * 
   * @param norigtaxprice 原币含税单价
   */
  public void setNorigtaxprice(UFDouble norigtaxprice) {
    this.setAttributeValue(SquareOutDetailVO.NORIGTAXPRICE, norigtaxprice);
  }

  /**
   * 获取本币无税单价
   * 
   * @return 本币无税单价
   */
  public UFDouble getNprice() {
    return (UFDouble) this.getAttributeValue(SquareOutDetailVO.NPRICE);
  }

  /**
   * 设置本币无税单价
   * 
   * @param nprice 本币无税单价
   */
  public void setNprice(UFDouble nprice) {
    this.setAttributeValue(SquareOutDetailVO.NPRICE, nprice);
  }

  /**
   * 获取本次结算数量
   * 
   * @return 本次结算数量
   */
  public UFDouble getNsquarenum() {
    return (UFDouble) this.getAttributeValue(SquareOutDetailVO.NSQUARENUM);
  }

  /**
   * 设置本次结算数量
   * 
   * @param nsquarenum 本次结算数量
   */
  public void setNsquarenum(UFDouble nsquarenum) {
    this.setAttributeValue(SquareOutDetailVO.NSQUARENUM, nsquarenum);
  }

  /**
   * 获取本币税额
   * 
   * @return 本币税额
   */
  public UFDouble getNtax() {
    return (UFDouble) this.getAttributeValue(SquareOutDetailVO.NTAX);
  }

  /**
   * 设置本币税额
   * 
   * @param ntax 本币税额
   */
  public void setNtax(UFDouble ntax) {
    this.setAttributeValue(SquareOutDetailVO.NTAX, ntax);
  }

  /**
   * 获取本币价税合计
   * 
   * @return 本币价税合计
   */
  public UFDouble getNtaxmny() {
    return (UFDouble) this.getAttributeValue(SquareOutDetailVO.NTAXMNY);
  }

  /**
   * 设置本币价税合计
   * 
   * @param ntaxmny 本币价税合计
   */
  public void setNtaxmny(UFDouble ntaxmny) {
    this.setAttributeValue(SquareOutDetailVO.NTAXMNY, ntaxmny);
  }

  /**
   * 获取本币含税净价
   * 
   * @return 本币含税净价
   */
  public UFDouble getNtaxnetprice() {
    return (UFDouble) this.getAttributeValue(SquareOutDetailVO.NTAXNETPRICE);
  }

  /**
   * 设置本币含税净价
   * 
   * @param ntaxnetprice 本币含税净价
   */
  public void setNtaxnetprice(UFDouble ntaxnetprice) {
    this.setAttributeValue(SquareOutDetailVO.NTAXNETPRICE, ntaxnetprice);
  }

  /**
   * 获取本币含税单价
   * 
   * @return 本币含税单价
   */
  public UFDouble getNtaxprice() {
    return (UFDouble) this.getAttributeValue(SquareOutDetailVO.NTAXPRICE);
  }

  /**
   * 设置本币含税单价
   * 
   * @param ntaxprice 本币含税单价
   */
  public void setNtaxprice(UFDouble ntaxprice) {
    this.setAttributeValue(SquareOutDetailVO.NTAXPRICE, ntaxprice);
  }

  /**
   * 获取税率
   * 
   * @return 税率
   */
  public UFDouble getNtaxrate() {
    return (UFDouble) this.getAttributeValue(SquareOutDetailVO.NTAXRATE);
  }

  /**
   * 设置税率
   * 
   * @param ntaxrate 税率
   */
  public void setNtaxrate(UFDouble ntaxrate) {
    this.setAttributeValue(SquareOutDetailVO.NTAXRATE, ntaxrate);
  }

  /**
   * 获取集团
   * 
   * @return 集团
   */
  public String getPk_group() {
    return (String) this.getAttributeValue(SquareOutDetailVO.PK_GROUP);
  }

  /**
   * 设置集团
   * 
   * @param pk_group 集团
   */
  public void setPk_group(String pk_group) {
    this.setAttributeValue(SquareOutDetailVO.PK_GROUP, pk_group);
  }

  /**
   * 获取结算财务组织
   * 
   * @return 结算财务组织
   */
  public String getPk_org() {
    return (String) this.getAttributeValue(SquareOutDetailVO.PK_ORG);
  }

  /**
   * 设置结算财务组织
   * 
   * @param pk_org 结算财务组织
   */
  public void setPk_org(String pk_org) {
    this.setAttributeValue(SquareOutDetailVO.PK_ORG, pk_org);
  }

  /**
   * 获取结算批次号
   * 
   * @return 结算批次号
   */
  public String getProcesseid() {
    return (String) this.getAttributeValue(SquareOutDetailVO.PROCESSEID);
  }

  /**
   * 设置结算批次号
   * 
   * @param processeid 结算批次号
   */
  public void setProcesseid(String processeid) {
    this.setAttributeValue(SquareOutDetailVO.PROCESSEID, processeid);
  }

  /**
   * 获取时间戳
   * 
   * @return 时间戳
   */
  public UFDateTime getTs() {
    return (UFDateTime) this.getAttributeValue(SquareOutDetailVO.TS);
  }

  /**
   * 设置时间戳
   * 
   * @param ts 时间戳
   */
  public void setTs(UFDateTime ts) {
    this.setAttributeValue(SquareOutDetailVO.TS, ts);
  }

  /**
   * 获取单位换算率
   * 
   * @return 单位换算率
   */
  public String getVchangerate() {
    return (String) this.getAttributeValue(SquareOutDetailVO.VCHANGERATE);
  }

  /**
   * 设置单位换算率
   * 
   * @param vchangerate 单位换算率
   */
  public void setVchangerate(String vchangerate) {
    this.setAttributeValue(SquareOutDetailVO.VCHANGERATE, vchangerate);
  }

  /**
   * 获取对冲出库单号
   * 
   * @return 对冲出库单号
   */
  public String getVrushbillcode() {
    return (String) this.getAttributeValue(SquareOutDetailVO.VRUSHBILLCODE);
  }

  /**
   * 设置对冲出库单号
   * 
   * @param vrushbillcode 对冲出库单号
   */
  public void setVrushbillcode(String vrushbillcode) {
    this.setAttributeValue(SquareOutDetailVO.VRUSHBILLCODE, vrushbillcode);
  }

  @Override
  public IVOMeta getMetaData() {
    IVOMeta meta =
        VOMetaFactory.getInstance().getVOMeta(SquareOutDetailVO.ENTITYNAME);
    return meta;
  }

  /**
   * 设置dr
   * 
   * @param dr
   */
  public void setDr(Integer dr) {
    this.setAttributeValue(SquareInvBVO.DR, dr);
  }

  /**
   * 获得 dr
   * 
   * @return dr
   */
  public Integer getDr() {
    return (Integer) this.getAttributeValue(SquareInvBVO.DR);
  }

  /**
   * 设置销售出库单待结算单voID
   * 
   * @param csquareoutbvoid
   */
  public void setCsquareoutbvoid(String csquareoutbvoid) {
    this.setAttributeValue(SquareOutDetailVO.CSQUAREOUTBVOID, csquareoutbvoid);
  }

  /**
   * 获得销售出库单待结算单voID
   * 
   * @return 销售出库单待结算单voID
   */
  public String getCsquareoutbvoid() {
    return (String) this.getAttributeValue(SquareOutDetailVO.CSQUAREOUTBVOID);
  }

}
