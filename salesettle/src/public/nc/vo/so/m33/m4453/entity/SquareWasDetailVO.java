package nc.vo.so.m33.m4453.entity;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;
import nc.vo.so.m33.enumeration.SquareType;

public class SquareWasDetailVO extends SuperVO {

  private static final long serialVersionUID = 4245259221757655909L;

  // 途损单结算明细实体路径
  public static final String ENTITYNAME = "so.SquareWasDetailVO";

  // 删除标志dr
  public static final String DR = "dr";

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
   * 途损结算单子实体
   */
  public static final String CSALESQUAREBID = "csalesquarebid";

  /**
   * 途损结算单明细实体
   */
  public static final String CSALESQUAREDID = "csalesquaredid";

  /**
   * 途损结算单主实体
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
   * 税码
   */
  public static final String CTAXCODEID = "ctaxcodeid";

  /**
   * 主单位
   */
  public static final String CUNITID = "cunitid";

  /**
   * 途损单单据日期
   */
  public static final String DBILLDATE = "dbilldate";

  /**
   * 结算类型
   */
  public static final String FSQUARETYPE = "fsquaretype";

  /**
   * 扣税类别
   */
  public static final String FTAXTYPEFLAG = "ftaxtypeflag";

  /**
   * 单位数量
   */
  public static final String NASTNUM = "nastnum";

  /**
   * 计税金额
   */
  public static final String NCALTAXMNY = "ncaltaxmny";

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
   * 原币税额
   */
  public static final String NORIGTAX = "norigtax";

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
   * 获取单位
   * 
   * @return 单位
   */
  public String getCastunitid() {
    return (String) this.getAttributeValue(SquareWasDetailVO.CASTUNITID);
  }

  /**
   * 设置单位
   * 
   * @param castunitid 单位
   */
  public void setCastunitid(String castunitid) {
    this.setAttributeValue(SquareWasDetailVO.CASTUNITID, castunitid);
  }

  /**
   * 获取本币
   * 
   * @return 本币
   */
  public String getCcurrencyid() {
    return (String) this.getAttributeValue(SquareWasDetailVO.CCURRENCYID);
  }

  /**
   * 设置本币
   * 
   * @param ccurrencyid 本币
   */
  public void setCcurrencyid(String ccurrencyid) {
    this.setAttributeValue(SquareWasDetailVO.CCURRENCYID, ccurrencyid);
  }

  /**
   * 获取原币
   * 
   * @return 原币
   */
  public String getCorigcurrencyid() {
    return (String) this.getAttributeValue(SquareWasDetailVO.CORIGCURRENCYID);
  }

  /**
   * 设置原币
   * 
   * @param corigcurrencyid 原币
   */
  public void setCorigcurrencyid(String corigcurrencyid) {
    this.setAttributeValue(SquareWasDetailVO.CORIGCURRENCYID, corigcurrencyid);
  }

  /**
   * 获取途损结算单子实体
   * 
   * @return 途损结算单子实体
   */
  public String getCsalesquarebid() {
    return (String) this.getAttributeValue(SquareWasDetailVO.CSALESQUAREBID);
  }

  /**
   * 设置途损结算单子实体
   * 
   * @param csalesquarebid 途损结算单子实体
   */
  public void setCsalesquarebid(String csalesquarebid) {
    this.setAttributeValue(SquareWasDetailVO.CSALESQUAREBID, csalesquarebid);
  }

  /**
   * 获取途损结算单明细实体
   * 
   * @return 途损结算单明细实体
   */
  public String getCsalesquaredid() {
    return (String) this.getAttributeValue(SquareWasDetailVO.CSALESQUAREDID);
  }

  /**
   * 设置途损结算单明细实体
   * 
   * @param csalesquaredid 途损结算单明细实体
   */
  public void setCsalesquaredid(String csalesquaredid) {
    this.setAttributeValue(SquareWasDetailVO.CSALESQUAREDID, csalesquaredid);
  }

  /**
   * 获取途损结算单主实体
   * 
   * @return 途损结算单主实体
   */
  public String getCsalesquareid() {
    return (String) this.getAttributeValue(SquareWasDetailVO.CSALESQUAREID);
  }

  /**
   * 设置途损结算单主实体
   * 
   * @param csalesquareid 途损结算单主实体
   */
  public void setCsalesquareid(String csalesquareid) {
    this.setAttributeValue(SquareWasDetailVO.CSALESQUAREID, csalesquareid);
  }

  /**
   * 获取途损单子实体
   * 
   * @return 途损单子实体
   */
  public String getCsquarebillbid() {
    return (String) this.getAttributeValue(SquareWasDetailVO.CSQUAREBILLBID);
  }

  /**
   * 设置途损单子实体
   * 
   * @param csquarebillbid 途损单子实体
   */
  public void setCsquarebillbid(String csquarebillbid) {
    this.setAttributeValue(SquareWasDetailVO.CSQUAREBILLBID, csquarebillbid);
  }

  /**
   * 获取途损单主实体
   * 
   * @return 途损单主实体
   */
  public String getCsquarebillid() {
    return (String) this.getAttributeValue(SquareWasDetailVO.CSQUAREBILLID);
  }

  /**
   * 设置途损单主实体
   * 
   * @param csquarebillid 途损单主实体
   */
  public void setCsquarebillid(String csquarebillid) {
    this.setAttributeValue(SquareWasDetailVO.CSQUAREBILLID, csquarebillid);
  }

  /**
   * 获取税码
   * 
   * @return 税码
   */
  public String getCtaxcodeid() {
    return (String) this.getAttributeValue(SquareWasDetailVO.CTAXCODEID);
  }

  /**
   * 设置税码
   * 
   * @param ctaxcodeid 税码
   */
  public void setCtaxcodeid(String ctaxcodeid) {
    this.setAttributeValue(SquareWasDetailVO.CTAXCODEID, ctaxcodeid);
  }

  /**
   * 获取主单位
   * 
   * @return 主单位
   */
  public String getCunitid() {
    return (String) this.getAttributeValue(SquareWasDetailVO.CUNITID);
  }

  /**
   * 设置主单位
   * 
   * @param cunitid 主单位
   */
  public void setCunitid(String cunitid) {
    this.setAttributeValue(SquareWasDetailVO.CUNITID, cunitid);
  }

  /**
   * 获取途损单单据日期
   * 
   * @return 途损单单据日期
   */
  public UFDate getDbilldate() {
    return (UFDate) this.getAttributeValue(SquareWasDetailVO.DBILLDATE);
  }

  /**
   * 设置途损单单据日期
   * 
   * @param dbilldate 途损单单据日期
   */
  public void setDbilldate(UFDate dbilldate) {
    this.setAttributeValue(SquareWasDetailVO.DBILLDATE, dbilldate);
  }

  /**
   * 获取结算类型
   * 
   * @return 结算类型
   * @see SquareType
   */
  public Integer getFsquaretype() {
    return (Integer) this.getAttributeValue(SquareWasDetailVO.FSQUARETYPE);
  }

  /**
   * 设置结算类型
   * 
   * @param fsquaretype 结算类型
   * @see SquareType
   */
  public void setFsquaretype(Integer fsquaretype) {
    this.setAttributeValue(SquareWasDetailVO.FSQUARETYPE, fsquaretype);
  }

  /**
   * 获取扣税类别
   * 
   * @return 扣税类别
   */
  public Integer getFtaxtypeflag() {
    return (Integer) this.getAttributeValue(SquareWasDetailVO.FTAXTYPEFLAG);
  }

  /**
   * 设置扣税类别
   * 
   * @param ftaxtypeflag 扣税类别
   */
  public void setFtaxtypeflag(Integer ftaxtypeflag) {
    this.setAttributeValue(SquareWasDetailVO.FTAXTYPEFLAG, ftaxtypeflag);
  }

  /**
   * 获取单位数量
   * 
   * @return 单位数量
   */
  public UFDouble getNastnum() {
    return (UFDouble) this.getAttributeValue(SquareWasDetailVO.NASTNUM);
  }

  /**
   * 设置单位数量
   * 
   * @param nastnum 单位数量
   */
  public void setNastnum(UFDouble nastnum) {
    this.setAttributeValue(SquareWasDetailVO.NASTNUM, nastnum);
  }

  /**
   * 获取计税金额
   * 
   * @return 计税金额
   */
  public UFDouble getNcaltaxmny() {
    return (UFDouble) this.getAttributeValue(SquareWasDetailVO.NCALTAXMNY);
  }

  /**
   * 设置计税金额
   * 
   * @param ncaltaxmny 计税金额
   */
  public void setNcaltaxmny(UFDouble ncaltaxmny) {
    this.setAttributeValue(SquareWasDetailVO.NCALTAXMNY, ncaltaxmny);
  }

  /**
   * 获取本币折扣额
   * 
   * @return 本币折扣额
   */
  public UFDouble getNdiscount() {
    return (UFDouble) this.getAttributeValue(SquareWasDetailVO.NDISCOUNT);
  }

  /**
   * 设置本币折扣额
   * 
   * @param ndiscount 本币折扣额
   */
  public void setNdiscount(UFDouble ndiscount) {
    this.setAttributeValue(SquareWasDetailVO.NDISCOUNT, ndiscount);
  }

  /**
   * 获取折本汇率
   * 
   * @return 折本汇率
   */
  public UFDouble getNexchangerate() {
    return (UFDouble) this.getAttributeValue(SquareWasDetailVO.NEXCHANGERATE);
  }

  /**
   * 设置折本汇率
   * 
   * @param nexchangerate 折本汇率
   */
  public void setNexchangerate(UFDouble nexchangerate) {
    this.setAttributeValue(SquareWasDetailVO.NEXCHANGERATE, nexchangerate);
  }

  /**
   * 获取全局本位币汇率
   * 
   * @return 全局本位币汇率
   */
  public UFDouble getNglobalexchgrate() {
    return (UFDouble) this
        .getAttributeValue(SquareWasDetailVO.NGLOBALEXCHGRATE);
  }

  /**
   * 设置全局本位币汇率
   * 
   * @param nglobalexchgrate 全局本位币汇率
   */
  public void setNglobalexchgrate(UFDouble nglobalexchgrate) {
    this.setAttributeValue(SquareWasDetailVO.NGLOBALEXCHGRATE, nglobalexchgrate);
  }

  /**
   * 获取全局本币无税金额
   * 
   * @return 全局本币无税金额
   */
  public UFDouble getNglobalmny() {
    return (UFDouble) this.getAttributeValue(SquareWasDetailVO.NGLOBALMNY);
  }

  /**
   * 设置全局本币无税金额
   * 
   * @param nglobalmny 全局本币无税金额
   */
  public void setNglobalmny(UFDouble nglobalmny) {
    this.setAttributeValue(SquareWasDetailVO.NGLOBALMNY, nglobalmny);
  }

  /**
   * 获取全局本币价税合计
   * 
   * @return 全局本币价税合计
   */
  public UFDouble getNglobaltaxmny() {
    return (UFDouble) this.getAttributeValue(SquareWasDetailVO.NGLOBALTAXMNY);
  }

  /**
   * 设置全局本币价税合计
   * 
   * @param nglobaltaxmny 全局本币价税合计
   */
  public void setNglobaltaxmny(UFDouble nglobaltaxmny) {
    this.setAttributeValue(SquareWasDetailVO.NGLOBALTAXMNY, nglobaltaxmny);
  }

  /**
   * 获取集团本位币汇率
   * 
   * @return 集团本位币汇率
   */
  public UFDouble getNgroupexchgrate() {
    return (UFDouble) this.getAttributeValue(SquareWasDetailVO.NGROUPEXCHGRATE);
  }

  /**
   * 设置集团本位币汇率
   * 
   * @param ngroupexchgrate 集团本位币汇率
   */
  public void setNgroupexchgrate(UFDouble ngroupexchgrate) {
    this.setAttributeValue(SquareWasDetailVO.NGROUPEXCHGRATE, ngroupexchgrate);
  }

  /**
   * 获取集团本币无税金额
   * 
   * @return 集团本币无税金额
   */
  public UFDouble getNgroupmny() {
    return (UFDouble) this.getAttributeValue(SquareWasDetailVO.NGROUPMNY);
  }

  /**
   * 设置集团本币无税金额
   * 
   * @param ngroupmny 集团本币无税金额
   */
  public void setNgroupmny(UFDouble ngroupmny) {
    this.setAttributeValue(SquareWasDetailVO.NGROUPMNY, ngroupmny);
  }

  /**
   * 获取集团本币价税合计
   * 
   * @return 集团本币价税合计
   */
  public UFDouble getNgrouptaxmny() {
    return (UFDouble) this.getAttributeValue(SquareWasDetailVO.NGROUPTAXMNY);
  }

  /**
   * 设置集团本币价税合计
   * 
   * @param ngrouptaxmny 集团本币价税合计
   */
  public void setNgrouptaxmny(UFDouble ngrouptaxmny) {
    this.setAttributeValue(SquareWasDetailVO.NGROUPTAXMNY, ngrouptaxmny);
  }

  /**
   * 获取单品折扣率
   * 
   * @return 单品折扣率
   */
  public UFDouble getNitemdiscountrate() {
    return (UFDouble) this
        .getAttributeValue(SquareWasDetailVO.NITEMDISCOUNTRATE);
  }

  /**
   * 设置单品折扣率
   * 
   * @param nitemdiscountrate 单品折扣率
   */
  public void setNitemdiscountrate(UFDouble nitemdiscountrate) {
    this.setAttributeValue(SquareWasDetailVO.NITEMDISCOUNTRATE,
        nitemdiscountrate);
  }

  /**
   * 获取本币无税金额
   * 
   * @return 本币无税金额
   */
  public UFDouble getNmny() {
    return (UFDouble) this.getAttributeValue(SquareWasDetailVO.NMNY);
  }

  /**
   * 设置本币无税金额
   * 
   * @param nmny 本币无税金额
   */
  public void setNmny(UFDouble nmny) {
    this.setAttributeValue(SquareWasDetailVO.NMNY, nmny);
  }

  /**
   * 获取本币无税净价
   * 
   * @return 本币无税净价
   */
  public UFDouble getNnetprice() {
    return (UFDouble) this.getAttributeValue(SquareWasDetailVO.NNETPRICE);
  }

  /**
   * 设置本币无税净价
   * 
   * @param nnetprice 本币无税净价
   */
  public void setNnetprice(UFDouble nnetprice) {
    this.setAttributeValue(SquareWasDetailVO.NNETPRICE, nnetprice);
  }

  /**
   * 获取主单位数量
   * 
   * @return 主单位数量
   */
  public UFDouble getNnum() {
    return (UFDouble) this.getAttributeValue(SquareWasDetailVO.NNUM);
  }

  /**
   * 设置主单位数量
   * 
   * @param nnum 主单位数量
   */
  public void setNnum(UFDouble nnum) {
    this.setAttributeValue(SquareWasDetailVO.NNUM, nnum);
  }

  /**
   * 获取原币折扣额
   * 
   * @return 原币折扣额
   */
  public UFDouble getNorigdiscount() {
    return (UFDouble) this.getAttributeValue(SquareWasDetailVO.NORIGDISCOUNT);
  }

  /**
   * 设置原币折扣额
   * 
   * @param norigdiscount 原币折扣额
   */
  public void setNorigdiscount(UFDouble norigdiscount) {
    this.setAttributeValue(SquareWasDetailVO.NORIGDISCOUNT, norigdiscount);
  }

  /**
   * 获取原币无税金额
   * 
   * @return 原币无税金额
   */
  public UFDouble getNorigmny() {
    return (UFDouble) this.getAttributeValue(SquareWasDetailVO.NORIGMNY);
  }

  /**
   * 设置原币无税金额
   * 
   * @param norigmny 原币无税金额
   */
  public void setNorigmny(UFDouble norigmny) {
    this.setAttributeValue(SquareWasDetailVO.NORIGMNY, norigmny);
  }

  /**
   * 获取原币无税净价
   * 
   * @return 原币无税净价
   */
  public UFDouble getNorignetprice() {
    return (UFDouble) this.getAttributeValue(SquareWasDetailVO.NORIGNETPRICE);
  }

  /**
   * 设置原币无税净价
   * 
   * @param norignetprice 原币无税净价
   */
  public void setNorignetprice(UFDouble norignetprice) {
    this.setAttributeValue(SquareWasDetailVO.NORIGNETPRICE, norignetprice);
  }

  /**
   * 获取原币无税单价
   * 
   * @return 原币无税单价
   */
  public UFDouble getNorigprice() {
    return (UFDouble) this.getAttributeValue(SquareWasDetailVO.NORIGPRICE);
  }

  /**
   * 设置原币无税单价
   * 
   * @param norigprice 原币无税单价
   */
  public void setNorigprice(UFDouble norigprice) {
    this.setAttributeValue(SquareWasDetailVO.NORIGPRICE, norigprice);
  }

  /**
   * 获取原币税额
   * 
   * @return 原币税额
   */
  public UFDouble getNorigtax() {
    return (UFDouble) this.getAttributeValue(SquareWasDetailVO.NORIGTAX);
  }

  /**
   * 设置原币税额
   * 
   * @param norigtax 原币税额
   */
  public void setNorigtax(UFDouble norigtax) {
    this.setAttributeValue(SquareWasDetailVO.NORIGTAX, norigtax);
  }

  /**
   * 获取原币价税合计
   * 
   * @return 原币价税合计
   */
  public UFDouble getNorigtaxmny() {
    return (UFDouble) this.getAttributeValue(SquareWasDetailVO.NORIGTAXMNY);
  }

  /**
   * 设置原币价税合计
   * 
   * @param norigtaxmny 原币价税合计
   */
  public void setNorigtaxmny(UFDouble norigtaxmny) {
    this.setAttributeValue(SquareWasDetailVO.NORIGTAXMNY, norigtaxmny);
  }

  /**
   * 获取原币含税净价
   * 
   * @return 原币含税净价
   */
  public UFDouble getNorigtaxnetprice() {
    return (UFDouble) this
        .getAttributeValue(SquareWasDetailVO.NORIGTAXNETPRICE);
  }

  /**
   * 设置原币含税净价
   * 
   * @param norigtaxnetprice 原币含税净价
   */
  public void setNorigtaxnetprice(UFDouble norigtaxnetprice) {
    this.setAttributeValue(SquareWasDetailVO.NORIGTAXNETPRICE, norigtaxnetprice);
  }

  /**
   * 获取原币含税单价
   * 
   * @return 原币含税单价
   */
  public UFDouble getNorigtaxprice() {
    return (UFDouble) this.getAttributeValue(SquareWasDetailVO.NORIGTAXPRICE);
  }

  /**
   * 设置原币含税单价
   * 
   * @param norigtaxprice 原币含税单价
   */
  public void setNorigtaxprice(UFDouble norigtaxprice) {
    this.setAttributeValue(SquareWasDetailVO.NORIGTAXPRICE, norigtaxprice);
  }

  /**
   * 获取本币无税单价
   * 
   * @return 本币无税单价
   */
  public UFDouble getNprice() {
    return (UFDouble) this.getAttributeValue(SquareWasDetailVO.NPRICE);
  }

  /**
   * 设置本币无税单价
   * 
   * @param nprice 本币无税单价
   */
  public void setNprice(UFDouble nprice) {
    this.setAttributeValue(SquareWasDetailVO.NPRICE, nprice);
  }

  /**
   * 获取本次结算数量
   * 
   * @return 本次结算数量
   */
  public UFDouble getNsquarenum() {
    return (UFDouble) this.getAttributeValue(SquareWasDetailVO.NSQUARENUM);
  }

  /**
   * 设置本次结算数量
   * 
   * @param nsquarenum 本次结算数量
   */
  public void setNsquarenum(UFDouble nsquarenum) {
    this.setAttributeValue(SquareWasDetailVO.NSQUARENUM, nsquarenum);
  }

  /**
   * 获取本币税额
   * 
   * @return 本币税额
   */
  public UFDouble getNtax() {
    return (UFDouble) this.getAttributeValue(SquareWasDetailVO.NTAX);
  }

  /**
   * 设置本币税额
   * 
   * @param ntax 本币税额
   */
  public void setNtax(UFDouble ntax) {
    this.setAttributeValue(SquareWasDetailVO.NTAX, ntax);
  }

  /**
   * 获取本币价税合计
   * 
   * @return 本币价税合计
   */
  public UFDouble getNtaxmny() {
    return (UFDouble) this.getAttributeValue(SquareWasDetailVO.NTAXMNY);
  }

  /**
   * 设置本币价税合计
   * 
   * @param ntaxmny 本币价税合计
   */
  public void setNtaxmny(UFDouble ntaxmny) {
    this.setAttributeValue(SquareWasDetailVO.NTAXMNY, ntaxmny);
  }

  /**
   * 获取本币含税净价
   * 
   * @return 本币含税净价
   */
  public UFDouble getNtaxnetprice() {
    return (UFDouble) this.getAttributeValue(SquareWasDetailVO.NTAXNETPRICE);
  }

  /**
   * 设置本币含税净价
   * 
   * @param ntaxnetprice 本币含税净价
   */
  public void setNtaxnetprice(UFDouble ntaxnetprice) {
    this.setAttributeValue(SquareWasDetailVO.NTAXNETPRICE, ntaxnetprice);
  }

  /**
   * 获取本币含税单价
   * 
   * @return 本币含税单价
   */
  public UFDouble getNtaxprice() {
    return (UFDouble) this.getAttributeValue(SquareWasDetailVO.NTAXPRICE);
  }

  /**
   * 设置本币含税单价
   * 
   * @param ntaxprice 本币含税单价
   */
  public void setNtaxprice(UFDouble ntaxprice) {
    this.setAttributeValue(SquareWasDetailVO.NTAXPRICE, ntaxprice);
  }

  /**
   * 获取税率
   * 
   * @return 税率
   */
  public UFDouble getNtaxrate() {
    return (UFDouble) this.getAttributeValue(SquareWasDetailVO.NTAXRATE);
  }

  /**
   * 设置税率
   * 
   * @param ntaxrate 税率
   */
  public void setNtaxrate(UFDouble ntaxrate) {
    this.setAttributeValue(SquareWasDetailVO.NTAXRATE, ntaxrate);
  }

  /**
   * 获取集团
   * 
   * @return 集团
   */
  public String getPk_group() {
    return (String) this.getAttributeValue(SquareWasDetailVO.PK_GROUP);
  }

  /**
   * 设置集团
   * 
   * @param pk_group 集团
   */
  public void setPk_group(String pk_group) {
    this.setAttributeValue(SquareWasDetailVO.PK_GROUP, pk_group);
  }

  /**
   * 获取结算财务组织
   * 
   * @return 结算财务组织
   */
  public String getPk_org() {
    return (String) this.getAttributeValue(SquareWasDetailVO.PK_ORG);
  }

  /**
   * 设置结算财务组织
   * 
   * @param pk_org 结算财务组织
   */
  public void setPk_org(String pk_org) {
    this.setAttributeValue(SquareWasDetailVO.PK_ORG, pk_org);
  }

  /**
   * 获取结算批次号
   * 
   * @return 结算批次号
   */
  public String getProcesseid() {
    return (String) this.getAttributeValue(SquareWasDetailVO.PROCESSEID);
  }

  /**
   * 设置结算批次号
   * 
   * @param processeid 结算批次号
   */
  public void setProcesseid(String processeid) {
    this.setAttributeValue(SquareWasDetailVO.PROCESSEID, processeid);
  }

  /**
   * 获取时间戳
   * 
   * @return 时间戳
   */
  public UFDateTime getTs() {
    return (UFDateTime) this.getAttributeValue(SquareWasDetailVO.TS);
  }

  /**
   * 设置时间戳
   * 
   * @param ts 时间戳
   */
  public void setTs(UFDateTime ts) {
    this.setAttributeValue(SquareWasDetailVO.TS, ts);
  }

  /**
   * 获取单位换算率
   * 
   * @return 单位换算率
   */
  public String getVchangerate() {
    return (String) this.getAttributeValue(SquareWasDetailVO.VCHANGERATE);
  }

  /**
   * 设置单位换算率
   * 
   * @param vchangerate 单位换算率
   */
  public void setVchangerate(String vchangerate) {
    this.setAttributeValue(SquareWasDetailVO.VCHANGERATE, vchangerate);
  }

  @Override
  public IVOMeta getMetaData() {
    IVOMeta meta =
        VOMetaFactory.getInstance().getVOMeta(SquareWasDetailVO.ENTITYNAME);
    return meta;
  }

  public void setDr(Integer dr) {
    this.setAttributeValue(SquareWasHVO.DR, dr);
  }

  public Integer getDr() {
    return (Integer) this.getAttributeValue(SquareWasHVO.DR);
  }
}
