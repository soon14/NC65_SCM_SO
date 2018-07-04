package nc.vo.so.report.outprofit;

import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.entity.view.AbstractDataView;
import nc.vo.pubapp.pattern.model.meta.entity.view.DataViewMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.view.IDataViewMeta;

/**
 * 销售出库毛利分析视图VO
 * 
 * @since 6.0
 * @version 2012-7-27 上午8:55:39
 * @author 梁吉明
 */
public class OutProfitViewVO extends AbstractDataView {

  /**
   * 
   */
  private static final long serialVersionUID = -2120362819295999764L;

  /**
   * 赠品行
   */
  public static final String FLARGESS = "flargess";

  /**
   * 销售出库单子实体
   */
  public static final String CGENERALBID = "cgeneralbid";

  /**
   * 销售出库单主实体
   */

  public static final String CGENERALHID = "cgeneralhid";

  /**
   * 销售组织
   */
  public static final String CSALEORGOID = "csaleorgoid";

  /**
   * 销售部门
   */
  public static final String CDPTID = "cdptid";

  /**
   * 销售业务员
   */
  public static final String CBIZID = "cbizid";

  /**
   * 销售渠道类型
   */
  public static final String CCHANNELTYPEID = "cchanneltypeid";

  /**
   * 客户基本分类
   */
  public static final String PK_CUSTCLASS = "pk_custclass";

  /**
   * 客户销售分类
   */
  public static final String PK_CUSTSALECLASS = "pk_custsaleclass";

  /**
   * 地区分类
   */
  public static final String PK_AREACL = "pk_areacl";

  /**
   * 订单客户
   */
  public static final String CCUSTOMERID = "ccustomerid";

  /**
   * 物料基本分类
   */
  public static final String PK_MARBASCLASS = "pk_marbasclass";

  /**
   * 物料销售分类
   */
  public static final String PK_MARSALECLASS = "pk_marsaleclass";

  /**
   * 物料
   */
  public static final String CMATERIALOID = "cmaterialoid";

  /**
   * 物料最新版本id
   */
  public static final String CMATERIALVID = "cmaterialvid";

  /**
   * 主单位
   */
  public static final String CUNITID = "cunitid";

  /**
   * 批次号
   */
  public static final String VBATCHCODE = "vbatchcode";

  /**
   * 订单类型
   */
  public static final String CTRANTYPEID = "ctrantypeid";

  /**
   * 出库单号
   */
  public static final String VBILLCODE = "vbillcode";

  /**
   * 币种
   */
  public static final String CORIGCURRENCYID = "ccurrencyid";

  /**
   * 产品线
   */
  public static final String CPRODLINEID = "cprodlineid";

  /**
   * 主数量
   */
  public static final String NMAINNUM = "nmainnum";

  /**
   * 出库主数量
   */
  public static final String NNUM = "nnum";

  /**
   * 无税净价
   */
  public static final String NQTORIGNETPRICE = "nqtorignetprice";

  /**
   * 应收主数量
   */
  public static final String NSHOULDRECEIVNUM = "nshouldreceivnum";

  /**
   * 应收无税金额
   */
  public static final String NTOTALRECEIVMNY = "ntotalreceivmny";

  /**
   * 无税金额
   */
  public static final String NNOTAXMNY = "nnotaxmny";

  /**
   * 无税单价
   */
  public static final String NNOTAXPRICE = "nnotaxprice";

  /**
   * 成本结算主数量
   */
  public static final String NCOSTNUM = "ncostnum";

  /**
   * 成本结算成本金额
   */
  public static final String NCOSTMNY = "ncostmny";

  /**
   * 成本金额
   */
  public static final String NTOTALCOSTMNY = "ntotalcostmny";

  /**
   * 成本单价
   */
  public static final String NCOSTPRICE = "ncostprice";

  /**
   * 毛利
   */
  public static final String NPROFITMNY = "nprofitmny";

  /**
   * 毛利率
   */
  public static final String NPROFITRATE = "nprofitrate";

  /**
   * 已成本计算的金额
   * 
   */
  public static final String NCOST = "ncost";

  /**
   * 
   * @return 已成本计算的金额
   */
  public UFDouble getNcost() {
    return (UFDouble) super.getAttributeValue(OutProfitViewVO.NCOST);
  }

  /**
   * 设置已成本计算的金额
   * 
   * @param ncost
   */
  public void setNcost(UFDouble ncost) {
    super.setAttributeValue(OutProfitViewVO.NCOST, ncost);
  }

  /**
   * 未成本计算的数量
   */
  public static final String NOCOSTNUM = "nocostnum";

  /**
   * 
   * @return 未成本计算的数量
   */
  public UFDouble getNocostnum() {
    return (UFDouble) super.getAttributeValue(OutProfitViewVO.NOCOSTNUM);
  }

  /**
   * 设置未成本计算的数量
   * 
   * @param nocostnum
   */
  public void setNocostnum(UFDouble nocostnum) {
    super.setAttributeValue(OutProfitViewVO.NOCOSTNUM, nocostnum);
  }

  /**
   * 财务组织
   */
  public static final String CFANACEORGOID = "cfanaceorgoid";

  /**
   * 获取财务组织
   * 
   * @return 财务组织
   */
  public String getCfanaceorgoid() {
    return (String) this.getAttributeValue(OutProfitViewVO.CFANACEORGOID);
  }

  /**
   * 设置财务组织
   * 
   * @param cfanaceorgoid
   */
  public void setCfanaceorgoid(String cfanaceorgoid) {
    this.setAttributeValue(OutProfitViewVO.CFANACEORGOID, cfanaceorgoid);
  }

  /**
   * 仓库组织
   * 
   */
  public static final String CBODYWAREHOUSEID = "cbodywarehouseid";

  /**
   * 仓库组织
   * 
   * @return 仓库组织
   */
  public String getCbodywarehouseid() {
    return (String) this.getAttributeValue(OutProfitViewVO.CBODYWAREHOUSEID);
  }

  /**
   * 设置仓库组织
   * 
   * @param cbodywarehouseid
   */
  public void setCbodywarehouseid(String cbodywarehouseid) {
    this.setAttributeValue(OutProfitViewVO.CBODYWAREHOUSEID, cbodywarehouseid);
  }

  /**
   * 库存组织（主组织）
   */
  public static final String PK_ORG = "pk_org";

  /**
   * 获取库存组织
   * 
   * @return 库存组织
   */
  public String getPk_org() {
    return (String) this.getAttributeValue(OutProfitViewVO.PK_ORG);
  }

  /**
   * 设置库存组织
   * 
   * @param pk_org 库存组织
   */
  public void sePk_org(String pk_org) {
    this.setAttributeValue(OutProfitViewVO.PK_ORG, pk_org);
  }

  /**
   * 自定义项1
   */
  public static final String VDEF1 = "vdef1";

  /**
   * 自定义项10
   */
  public static final String VDEF10 = "vdef10";

  /**
   * 自定义项11
   */
  public static final String VDEF11 = "vdef11";

  /**
   * 自定义项12
   */
  public static final String VDEF12 = "vdef12";

  /**
   * 自定义项13
   */
  public static final String VDEF13 = "vdef13";

  /**
   * 自定义项14
   */
  public static final String VDEF14 = "vdef14";

  /**
   * 自定义项15
   */
  public static final String VDEF15 = "vdef15";

  /**
   * 自定义项16
   */
  public static final String VDEF16 = "vdef16";

  /**
   * 自定义项17
   */
  public static final String VDEF17 = "vdef17";

  /**
   * 自定义项18
   */
  public static final String VDEF18 = "vdef18";

  /**
   * 自定义项19
   */
  public static final String VDEF19 = "vdef19";

  /**
   * 自定义项2
   */
  public static final String VDEF2 = "vdef2";

  /**
   * 自定义项20
   */
  public static final String VDEF20 = "vdef20";

  /**
   * 自定义项3
   */
  public static final String VDEF3 = "vdef3";

  /**
   * 自定义项4
   */
  public static final String VDEF4 = "vdef4";

  /**
   * 自定义项5
   */
  public static final String VDEF5 = "vdef5";

  /**
   * 自定义项6
   */
  public static final String VDEF6 = "vdef6";

  /**
   * 自定义项7
   */
  public static final String VDEF7 = "vdef7";

  /**
   * 自定义项8
   */
  public static final String VDEF8 = "vdef8";

  /**
   * 自定义项9
   */
  public static final String VDEF9 = "vdef9";

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

  @Override
  public IDataViewMeta getMetaData() {
    // IDataViewMeta主要描述当前视图的组成实体
    IDataViewMeta viewMeta =
    // 视图工具类创建类，方法，获取元数据
        DataViewMetaFactory.getInstance().getDataViewMeta(
            OutProfitViewMeta.class);
    return viewMeta;
  }

  /**
   * 
   * @return 无税净价
   */
  public UFDouble getNqtorignetprice() {
    return (UFDouble) super.getAttributeValue(OutProfitViewVO.NQTORIGNETPRICE);
  }

  /**
   * 设置无税净价
   * 
   * @param nqtorignetprice
   * 
   */
  public void setNqtorignetprice(UFDouble nqtorignetprice) {
    super.setAttributeValue(OutProfitViewVO.NQTORIGNETPRICE, nqtorignetprice);
  }

  /**
   * 
   * @return 主数量
   */
  public UFDouble getNmainnum() {
    return (UFDouble) super.getAttributeValue(OutProfitViewVO.NMAINNUM);
  }

  /**
   * 设置主数量
   * 
   * @param nmainnum
   */
  public void setNmainnum(UFDouble nmainnum) {
    super.setAttributeValue(OutProfitViewVO.NMAINNUM, nmainnum);
  }

  /**
   * 
   * @return 应收主数量
   */
  public UFDouble getNshouldreceivnum() {
    return (UFDouble) super.getAttributeValue(OutProfitViewVO.NSHOULDRECEIVNUM);
  }

  /**
   * 设置应收主数量
   * 
   * @param nshouldreceivnum
   * 
   */
  public void setNshouldreceivnum(UFDouble nshouldreceivnum) {
    super.setAttributeValue(OutProfitViewVO.NSHOULDRECEIVNUM, nshouldreceivnum);
  }

  /**
   * 
   * @return 无税金额
   */
  public UFDouble getNnotaxmny() {
    return (UFDouble) super.getAttributeValue(OutProfitViewVO.NNOTAXMNY);
  }

  /**
   * 设置无税金额
   * 
   * @param nnotaxmny
   * 
   */
  public void setNnotaxmny(UFDouble nnotaxmny) {
    super.setAttributeValue(OutProfitViewVO.NNOTAXMNY, nnotaxmny);
  }

  /**
   * 
   * @return 无税单价
   */
  public UFDouble getNnotaxprice() {
    return (UFDouble) super.getAttributeValue(OutProfitViewVO.NNOTAXPRICE);
  }

  /**
   * 设置无税单价
   * 
   * @param nnotaxprice
   * 
   */
  public void setNnotaxprice(UFDouble nnotaxprice) {
    super.setAttributeValue(OutProfitViewVO.NNOTAXPRICE, nnotaxprice);
  }

  /**
   * 
   * @return 成本结算主数量
   */
  public UFDouble getNcostnum() {
    return (UFDouble) super.getAttributeValue(OutProfitViewVO.NCOSTNUM);
  }

  /**
   * 设置成本结算主数量
   * 
   * @param ncostnum
   * 
   */
  public void setNcostnum(UFDouble ncostnum) {
    super.setAttributeValue(OutProfitViewVO.NCOSTNUM, ncostnum);
  }

  /**
   * 
   * @return 成本结算成本金额
   */
  public UFDouble getNcostmny() {
    return (UFDouble) super.getAttributeValue(OutProfitViewVO.NCOSTMNY);
  }

  /**
   * 设置成本结算成本金额
   * 
   * @param ncostmny
   * 
   */
  public void setNcostmny(UFDouble ncostmny) {
    super.setAttributeValue(OutProfitViewVO.NCOSTMNY, ncostmny);
  }

  /**
   * 
   * @return 业务员
   */
  public String getCbizid() {
    return (String) super.getAttributeValue(OutProfitViewVO.CBIZID);
  }

  /**
   * 
   * @return 订单客户
   */
  public String getCcustomerid() {
    return (String) super.getAttributeValue(OutProfitViewVO.CCUSTOMERID);
  }

  /**
   * 
   * @return 部门
   */
  public String getCdptid() {
    return (String) super.getAttributeValue(OutProfitViewVO.CDPTID);
  }

  /**
   * 
   * @return 销售出库单子实体
   */
  public String getCgeneralbid() {
    return (String) super.getAttributeValue(OutProfitViewVO.CGENERALBID);
  }

  /**
   * 
   * @return 销售出库单主实体
   */
  public String getCgeneralhid() {
    return (String) super.getAttributeValue(OutProfitViewVO.CGENERALHID);
  }

  /**
   * 
   * @return 物料
   */
  public String getCmaterialoid() {
    return (String) super.getAttributeValue(OutProfitViewVO.CMATERIALOID);
  }

  /**
   * 
   * @return 物料VID
   */
  public String getCmaterialvid() {
    return (String) super.getAttributeValue(OutProfitViewVO.CMATERIALVID);
  }

  /**
   * 
   * @return 币种
   */
  public String getCorigcurrencyid() {
    return (String) super.getAttributeValue(OutProfitViewVO.CORIGCURRENCYID);
  }

  /**
   * 
   * @return 产品线
   */
  public String getCprodlineid() {
    return (String) super.getAttributeValue(OutProfitViewVO.CPRODLINEID);
  }

  /**
   * 
   * @return 销售组织
   */
  public String getCsaleorgoid() {
    return (String) super.getAttributeValue(OutProfitViewVO.CSALEORGOID);
  }

  /**
   * 
   * @return 订单类型
   */
  public String getCtrantypeid() {
    return (String) super.getAttributeValue(OutProfitViewVO.CTRANTYPEID);
  }

  /**
   * 
   * @return 主单位
   */
  public String getCunitid() {
    return (String) super.getAttributeValue(OutProfitViewVO.CUNITID);
  }

  /**
   * 
   * @return 成本单价
   */
  public UFDouble getNcostprice() {
    return (UFDouble) super.getAttributeValue(OutProfitViewVO.NCOSTPRICE);
  }

  /**
   * 
   * @return 出库主数量
   */
  public UFDouble getNnum() {
    return (UFDouble) super.getAttributeValue(OutProfitViewVO.NNUM);
  }

  /**
   * 
   * @return 毛利
   */
  public UFDouble getNprofitmny() {
    return (UFDouble) super.getAttributeValue(OutProfitViewVO.NPROFITMNY);
  }

  /**
   * 
   * @return 毛利率
   */
  public UFDouble getNprofitrate() {
    return (UFDouble) super.getAttributeValue(OutProfitViewVO.NPROFITRATE);
  }

  /**
   * 
   * @return 成本金额
   */
  public UFDouble getNtotalcostmny() {
    return (UFDouble) super.getAttributeValue(OutProfitViewVO.NTOTALCOSTMNY);
  }

  /**
   * 
   * @return 应收无税金额
   */
  public UFDouble getNtotalreceivmny() {
    return (UFDouble) super.getAttributeValue(OutProfitViewVO.NTOTALRECEIVMNY);
  }

  /**
   * 
   * @return 地区分类
   */
  public String getPk_areacl() {
    return (String) super.getAttributeValue(OutProfitViewVO.PK_AREACL);
  }

  /**
   * 
   * @return 客户基本分类
   */
  public String getPk_custclass() {
    return (String) super.getAttributeValue(OutProfitViewVO.PK_CUSTCLASS);
  }

  /**
   * 
   * @return 客户销售分类
   */
  public String getPk_custsaleclass() {
    return (String) super.getAttributeValue(OutProfitViewVO.PK_CUSTSALECLASS);
  }

  /**
   * 
   * @return 物料基本分类
   */
  public String getPk_marbasclass() {
    return (String) super.getAttributeValue(OutProfitViewVO.PK_MARBASCLASS);
  }

  /**
   * 
   * @return 物料销售分类
   */
  public String getPk_marsaleclass() {
    return (String) super.getAttributeValue(OutProfitViewVO.PK_MARSALECLASS);
  }

  /**
   * 
   * @return 批次号
   */
  public String getVbatchcode() {
    return (String) super.getAttributeValue(OutProfitViewVO.VBATCHCODE);
  }

  /**
   * 
   * @return 出库单号
   */
  public String getVbillcode() {
    return (String) super.getAttributeValue(OutProfitViewVO.VBILLCODE);
  }

  /**
   * 设置业务员
   * 
   * @param cbizid
   */
  public void setCbizid(String cbizid) {
    super.setAttributeValue(OutProfitViewVO.CBIZID, cbizid);
  }

  /**
   * 设置订单客户
   * 
   * @param ccustomerid
   */
  public void setCcustomerid(String ccustomerid) {
    super.setAttributeValue(OutProfitViewVO.CCUSTOMERID, ccustomerid);
  }

  /**
   * 设置部门
   * 
   * @param cdptid
   */
  public void setCdptid(String cdptid) {
    super.setAttributeValue(OutProfitViewVO.CDPTID, cdptid);
  }

  /**
   * 设置销售出库单子实体
   * 
   * @param cgeneralbid
   */
  public void setCgeneralbid(String cgeneralbid) {
    super.setAttributeValue(OutProfitViewVO.CGENERALBID, cgeneralbid);
  }

  /**
   * 设置销售出库单主实体
   * 
   * @param cgeneralhid
   */
  public void setCgeneralhid(String cgeneralhid) {
    super.setAttributeValue(OutProfitViewVO.CGENERALHID, cgeneralhid);
  }

  /**
   * 设置物料
   * 
   * @param cmaterialoid
   */
  public void setCmaterialoid(String cmaterialoid) {
    super.setAttributeValue(OutProfitViewVO.CMATERIALOID, cmaterialoid);
  }

  /**
   * 设置物料
   * 
   * @param cmaterialvid
   */
  public void setCmaterialvid(String cmaterialvid) {
    super.setAttributeValue(OutProfitViewVO.CMATERIALVID, cmaterialvid);
  }

  /**
   * 设置币种
   * 
   * @param corigcurrencyid
   */
  public void setCorigcurrencyid(String corigcurrencyid) {
    super.setAttributeValue(OutProfitViewVO.CORIGCURRENCYID, corigcurrencyid);
  }

  /**
   * 设置产品线
   * 
   * @param cprodlineid
   */
  public void setCprodlineid(String cprodlineid) {
    super.setAttributeValue(OutProfitViewVO.CPRODLINEID, cprodlineid);
  }

  /**
   * 设置销售组织
   * 
   * @param csaleorgoid
   */
  public void setCsaleorgoid(String csaleorgoid) {
    super.setAttributeValue(OutProfitViewVO.CSALEORGOID, csaleorgoid);
  }

  /**
   * 设置订单类型
   * 
   * @param ctrantypeid
   */
  public void setCtrantypeid(String ctrantypeid) {
    super.setAttributeValue(OutProfitViewVO.CTRANTYPEID, ctrantypeid);
  }

  /**
   * 设置主单位
   * 
   * @param cunitid
   */
  public void setCunitid(String cunitid) {
    super.setAttributeValue(OutProfitViewVO.CUNITID, cunitid);
  }

  /**
   * 设置成本单价
   * 
   * @param ncostprice
   */
  public void setNcostprice(UFDouble ncostprice) {
    super.setAttributeValue(OutProfitViewVO.NCOSTPRICE, ncostprice);
  }

  /**
   * 设置出库主数量
   * 
   * @param nnum
   */
  public void setNnum(UFDouble nnum) {
    super.setAttributeValue(OutProfitViewVO.NNUM, nnum);
  }

  /**
   * 设置毛利
   * 
   * @param nprofitmny
   */
  public void setNprofitmny(UFDouble nprofitmny) {
    super.setAttributeValue(OutProfitViewVO.NPROFITMNY, nprofitmny);
  }

  /**
   * 设置毛利率
   * 
   * @param nprofitrate
   */
  public void setNprofitrate(UFDouble nprofitrate) {
    super.setAttributeValue(OutProfitViewVO.NPROFITRATE, nprofitrate);
  }

  /**
   * 设置成本金额
   * 
   * @param ntotalcostmny
   */
  public void setNtotalcostmny(UFDouble ntotalcostmny) {
    super.setAttributeValue(OutProfitViewVO.NTOTALCOSTMNY, ntotalcostmny);
  }

  /**
   * 设置应收无税金额
   * 
   * @param ntotalreceivmny
   */
  public void setNtotalreceivmny(UFDouble ntotalreceivmny) {
    super.setAttributeValue(OutProfitViewVO.NTOTALRECEIVMNY, ntotalreceivmny);
  }

  /**
   * 设置地区分类
   * 
   * @param pk_areacl
   */
  public void setPk_areacl(String pk_areacl) {
    super.setAttributeValue(OutProfitViewVO.PK_AREACL, pk_areacl);
  }

  /**
   * 设置客户基本分类
   * 
   * @param pk_custclass
   */
  public void setPk_custclass(String pk_custclass) {
    super.setAttributeValue(OutProfitViewVO.PK_CUSTCLASS, pk_custclass);
  }

  /**
   * 设置客户销售分类
   * 
   * @param pk_custsaleclass
   */
  public void setPk_custsaleclass(String pk_custsaleclass) {
    super.setAttributeValue(OutProfitViewVO.PK_CUSTSALECLASS, pk_custsaleclass);
  }

  /**
   * 设置物料基本分类
   * 
   * @param pk_marbasclass
   */
  public void setPk_marbasclass(String pk_marbasclass) {
    super.setAttributeValue(OutProfitViewVO.PK_MARBASCLASS, pk_marbasclass);
  }

  /**
   * 设置物料销售分类
   * 
   * @param pk_marsaleclass
   */
  public void setPk_marsaleclass(String pk_marsaleclass) {
    super.setAttributeValue(OutProfitViewVO.PK_MARSALECLASS, pk_marsaleclass);
  }

  /**
   * 设置批次号
   * 
   * @param vbatchcode
   */
  public void setVbatchcode(String vbatchcode) {
    super.setAttributeValue(OutProfitViewVO.VBATCHCODE, vbatchcode);
  }

  /**
   * 设置出库单号
   * 
   * @param vbillcode
   */
  public void setVbillcode(String vbillcode) {
    super.setAttributeValue(OutProfitViewVO.VBILLCODE, vbillcode);
  }

  /**
   * 获取自定义项1
   * 
   * @return 自定义项1
   */
  public String getVdef1() {
    return (String) this.getAttributeValue(OutProfitViewVO.VDEF1);
  }

  /**
   * 获取自定义项10
   * 
   * @return 自定义项10
   */
  public String getVdef10() {
    return (String) this.getAttributeValue(OutProfitViewVO.VDEF10);
  }

  /**
   * 获取自定义项11
   * 
   * @return 自定义项11
   */
  public String getVdef11() {
    return (String) this.getAttributeValue(OutProfitViewVO.VDEF11);
  }

  /**
   * 获取自定义项12
   * 
   * @return 自定义项12
   */
  public String getVdef12() {
    return (String) this.getAttributeValue(OutProfitViewVO.VDEF12);
  }

  /**
   * 获取自定义项13
   * 
   * @return 自定义项13
   */
  public String getVdef13() {
    return (String) this.getAttributeValue(OutProfitViewVO.VDEF13);
  }

  /**
   * 获取自定义项14
   * 
   * @return 自定义项14
   */
  public String getVdef14() {
    return (String) this.getAttributeValue(OutProfitViewVO.VDEF14);
  }

  /**
   * 获取自定义项15
   * 
   * @return 自定义项15
   */
  public String getVdef15() {
    return (String) this.getAttributeValue(OutProfitViewVO.VDEF15);
  }

  /**
   * 获取自定义项16
   * 
   * @return 自定义项16
   */
  public String getVdef16() {
    return (String) this.getAttributeValue(OutProfitViewVO.VDEF16);
  }

  /**
   * 获取自定义项17
   * 
   * @return 自定义项17
   */
  public String getVdef17() {
    return (String) this.getAttributeValue(OutProfitViewVO.VDEF17);
  }

  /**
   * 获取自定义项18
   * 
   * @return 自定义项18
   */
  public String getVdef18() {
    return (String) this.getAttributeValue(OutProfitViewVO.VDEF18);
  }

  /**
   * 获取自定义项19
   * 
   * @return 自定义项19
   */
  public String getVdef19() {
    return (String) this.getAttributeValue(OutProfitViewVO.VDEF19);
  }

  /**
   * 获取自定义项2
   * 
   * @return 自定义项2
   */
  public String getVdef2() {
    return (String) this.getAttributeValue(OutProfitViewVO.VDEF2);
  }

  /**
   * 获取自定义项20
   * 
   * @return 自定义项20
   */
  public String getVdef20() {
    return (String) this.getAttributeValue(OutProfitViewVO.VDEF20);
  }

  /**
   * 获取自定义项3
   * 
   * @return 自定义项3
   */
  public String getVdef3() {
    return (String) this.getAttributeValue(OutProfitViewVO.VDEF3);
  }

  /**
   * 获取自定义项4
   * 
   * @return 自定义项4
   */
  public String getVdef4() {
    return (String) this.getAttributeValue(OutProfitViewVO.VDEF4);
  }

  /**
   * 获取自定义项5
   * 
   * @return 自定义项5
   */
  public String getVdef5() {
    return (String) this.getAttributeValue(OutProfitViewVO.VDEF5);
  }

  /**
   * 获取自定义项6
   * 
   * @return 自定义项6
   */
  public String getVdef6() {
    return (String) this.getAttributeValue(OutProfitViewVO.VDEF6);
  }

  /**
   * 获取自定义项7
   * 
   * @return 自定义项7
   */
  public String getVdef7() {
    return (String) this.getAttributeValue(OutProfitViewVO.VDEF7);
  }

  /**
   * 获取自定义项8
   * 
   * @return 自定义项8
   */
  public String getVdef8() {
    return (String) this.getAttributeValue(OutProfitViewVO.VDEF8);
  }

  /**
   * 获取自定义项9
   * 
   * @return 自定义项9
   */
  public String getVdef9() {
    return (String) this.getAttributeValue(OutProfitViewVO.VDEF9);
  }

  /**
   * 设置自定义项1
   * 
   * @param vdef1 自定义项1
   */
  public void setVdef1(String vdef1) {
    this.setAttributeValue(OutProfitViewVO.VDEF1, vdef1);
  }

  /**
   * 设置自定义项10
   * 
   * @param vdef10 自定义项10
   */
  public void setVdef10(String vdef10) {
    this.setAttributeValue(OutProfitViewVO.VDEF10, vdef10);
  }

  /**
   * 设置自定义项11
   * 
   * @param vdef11 自定义项11
   */
  public void setVdef11(String vdef11) {
    this.setAttributeValue(OutProfitViewVO.VDEF11, vdef11);
  }

  /**
   * 设置自定义项12
   * 
   * @param vdef12 自定义项12
   */
  public void setVdef12(String vdef12) {
    this.setAttributeValue(OutProfitViewVO.VDEF12, vdef12);
  }

  /**
   * 设置自定义项13
   * 
   * @param vdef13 自定义项13
   */
  public void setVdef13(String vdef13) {
    this.setAttributeValue(OutProfitViewVO.VDEF13, vdef13);
  }

  /**
   * 设置自定义项14
   * 
   * @param vdef14 自定义项14
   */
  public void setVdef14(String vdef14) {
    this.setAttributeValue(OutProfitViewVO.VDEF14, vdef14);
  }

  /**
   * 设置自定义项15
   * 
   * @param vdef15 自定义项15
   */
  public void setVdef15(String vdef15) {
    this.setAttributeValue(OutProfitViewVO.VDEF15, vdef15);
  }

  /**
   * 设置自定义项16
   * 
   * @param vdef16 自定义项16
   */
  public void setVdef16(String vdef16) {
    this.setAttributeValue(OutProfitViewVO.VDEF16, vdef16);
  }

  /**
   * 设置自定义项17
   * 
   * @param vdef17 自定义项17
   */
  public void setVdef17(String vdef17) {
    this.setAttributeValue(OutProfitViewVO.VDEF17, vdef17);
  }

  /**
   * 设置自定义项18
   * 
   * @param vdef18 自定义项18
   */
  public void setVdef18(String vdef18) {
    this.setAttributeValue(OutProfitViewVO.VDEF18, vdef18);
  }

  /**
   * 设置自定义项19
   * 
   * @param vdef19 自定义项19
   */
  public void setVdef19(String vdef19) {
    this.setAttributeValue(OutProfitViewVO.VDEF19, vdef19);
  }

  /**
   * 设置自定义项2
   * 
   * @param vdef2 自定义项2
   */
  public void setVdef2(String vdef2) {
    this.setAttributeValue(OutProfitViewVO.VDEF2, vdef2);
  }

  /**
   * 设置自定义项20
   * 
   * @param vdef20 自定义项20
   */
  public void setVdef20(String vdef20) {
    this.setAttributeValue(OutProfitViewVO.VDEF20, vdef20);
  }

  /**
   * 设置自定义项3
   * 
   * @param vdef3 自定义项3
   */
  public void setVdef3(String vdef3) {
    this.setAttributeValue(OutProfitViewVO.VDEF3, vdef3);
  }

  /**
   * 设置自定义项4
   * 
   * @param vdef4 自定义项4
   */
  public void setVdef4(String vdef4) {
    this.setAttributeValue(OutProfitViewVO.VDEF4, vdef4);
  }

  /**
   * 设置自定义项5
   * 
   * @param vdef5 自定义项5
   */
  public void setVdef5(String vdef5) {
    this.setAttributeValue(OutProfitViewVO.VDEF5, vdef5);
  }

  /**
   * 设置自定义项6
   * 
   * @param vdef6 自定义项6
   */
  public void setVdef6(String vdef6) {
    this.setAttributeValue(OutProfitViewVO.VDEF6, vdef6);
  }

  /**
   * 设置自定义项7
   * 
   * @param vdef7 自定义项7
   */
  public void setVdef7(String vdef7) {
    this.setAttributeValue(OutProfitViewVO.VDEF7, vdef7);
  }

  /**
   * 设置自定义项8
   * 
   * @param vdef8 自定义项8
   */
  public void setVdef8(String vdef8) {
    this.setAttributeValue(OutProfitViewVO.VDEF8, vdef8);
  }

  /**
   * 设置自定义项9
   * 
   * @param vdef9 自定义项9
   */
  public void setVdef9(String vdef9) {
    this.setAttributeValue(OutProfitViewVO.VDEF9, vdef9);
  }

  /**
   * 获取自定义项1
   * 
   * @return 自定义项1
   */
  public String getVbdef1() {
    return (String) this.getAttributeValue(OutProfitViewVO.VBDEF1);
  }

  /**
   * 获取自定义项10
   * 
   * @return 自定义项10
   */
  public String getVbdef10() {
    return (String) this.getAttributeValue(OutProfitViewVO.VBDEF10);
  }

  /**
   * 获取自定义项11
   * 
   * @return 自定义项11
   */
  public String getVbdef11() {
    return (String) this.getAttributeValue(OutProfitViewVO.VBDEF11);
  }

  /**
   * 获取自定义项12
   * 
   * @return 自定义项12
   */
  public String getVbdef12() {
    return (String) this.getAttributeValue(OutProfitViewVO.VBDEF12);
  }

  /**
   * 获取自定义项13
   * 
   * @return 自定义项13
   */
  public String getVbdef13() {
    return (String) this.getAttributeValue(OutProfitViewVO.VBDEF13);
  }

  /**
   * 获取自定义项14
   * 
   * @return 自定义项14
   */
  public String getVbdef14() {
    return (String) this.getAttributeValue(OutProfitViewVO.VBDEF14);
  }

  /**
   * 获取自定义项15
   * 
   * @return 自定义项15
   */
  public String getVbdef15() {
    return (String) this.getAttributeValue(OutProfitViewVO.VBDEF15);
  }

  /**
   * 获取自定义项16
   * 
   * @return 自定义项16
   */
  public String getVbdef16() {
    return (String) this.getAttributeValue(OutProfitViewVO.VBDEF16);
  }

  /**
   * 获取自定义项17
   * 
   * @return 自定义项17
   */
  public String getVbdef17() {
    return (String) this.getAttributeValue(OutProfitViewVO.VBDEF17);
  }

  /**
   * 获取自定义项18
   * 
   * @return 自定义项18
   */
  public String getVbdef18() {
    return (String) this.getAttributeValue(OutProfitViewVO.VBDEF18);
  }

  /**
   * 获取自定义项19
   * 
   * @return 自定义项19
   */
  public String getVbdef19() {
    return (String) this.getAttributeValue(OutProfitViewVO.VBDEF19);
  }

  /**
   * 获取自定义项2
   * 
   * @return 自定义项2
   */
  public String getVbdef2() {
    return (String) this.getAttributeValue(OutProfitViewVO.VBDEF2);
  }

  /**
   * 获取自定义项20
   * 
   * @return 自定义项20
   */
  public String getVbdef20() {
    return (String) this.getAttributeValue(OutProfitViewVO.VBDEF20);
  }

  /**
   * 获取自定义项3
   * 
   * @return 自定义项3
   */
  public String getVbdef3() {
    return (String) this.getAttributeValue(OutProfitViewVO.VBDEF3);
  }

  /**
   * 获取自定义项4
   * 
   * @return 自定义项4
   */
  public String getVbdef4() {
    return (String) this.getAttributeValue(OutProfitViewVO.VBDEF4);
  }

  /**
   * 获取自定义项5
   * 
   * @return 自定义项5
   */
  public String getVbdef5() {
    return (String) this.getAttributeValue(OutProfitViewVO.VBDEF5);
  }

  /**
   * 获取自定义项6
   * 
   * @return 自定义项6
   */
  public String getVbdef6() {
    return (String) this.getAttributeValue(OutProfitViewVO.VBDEF6);
  }

  /**
   * 获取自定义项7
   * 
   * @return 自定义项7
   */
  public String getVbdef7() {
    return (String) this.getAttributeValue(OutProfitViewVO.VBDEF7);
  }

  /**
   * 获取自定义项8
   * 
   * @return 自定义项8
   */
  public String getVbdef8() {
    return (String) this.getAttributeValue(OutProfitViewVO.VBDEF8);
  }

  /**
   * 获取自定义项9
   * 
   * @return 自定义项9
   */
  public String getVbdef9() {
    return (String) this.getAttributeValue(OutProfitViewVO.VBDEF9);
  }

  /**
   * 设置自定义项1
   * 
   * @param vbdef1 自定义项1
   */
  public void setVbdef1(String vbdef1) {
    this.setAttributeValue(OutProfitViewVO.VBDEF1, vbdef1);
  }

  /**
   * 设置自定义项10
   * 
   * @param vbdef10 自定义项10
   */
  public void setVbdef10(String vbdef10) {
    this.setAttributeValue(OutProfitViewVO.VBDEF10, vbdef10);
  }

  /**
   * 设置自定义项11
   * 
   * @param vbdef11 自定义项11
   */
  public void setVbdef11(String vbdef11) {
    this.setAttributeValue(OutProfitViewVO.VBDEF11, vbdef11);
  }

  /**
   * 设置自定义项12
   * 
   * @param vbdef12 自定义项12
   */
  public void setVbdef12(String vbdef12) {
    this.setAttributeValue(OutProfitViewVO.VBDEF12, vbdef12);
  }

  /**
   * 设置自定义项13
   * 
   * @param vbdef13 自定义项13
   */
  public void setVbdef13(String vbdef13) {
    this.setAttributeValue(OutProfitViewVO.VBDEF13, vbdef13);
  }

  /**
   * 设置自定义项14
   * 
   * @param vbdef14 自定义项14
   */
  public void setVbdef14(String vbdef14) {
    this.setAttributeValue(OutProfitViewVO.VBDEF14, vbdef14);
  }

  /**
   * 设置自定义项15
   * 
   * @param vbdef15 自定义项15
   */
  public void setVbdef15(String vbdef15) {
    this.setAttributeValue(OutProfitViewVO.VBDEF15, vbdef15);
  }

  /**
   * 设置自定义项16
   * 
   * @param vbdef16 自定义项16
   */
  public void setVbdef16(String vbdef16) {
    this.setAttributeValue(OutProfitViewVO.VBDEF16, vbdef16);
  }

  /**
   * 设置自定义项17
   * 
   * @param vbdef17 自定义项17
   */
  public void setVbdef17(String vbdef17) {
    this.setAttributeValue(OutProfitViewVO.VBDEF17, vbdef17);
  }

  /**
   * 设置自定义项18
   * 
   * @param vbdef18 自定义项18
   */
  public void setVbdef18(String vbdef18) {
    this.setAttributeValue(OutProfitViewVO.VBDEF18, vbdef18);
  }

  /**
   * 设置自定义项19
   * 
   * @param vbdef19 自定义项19
   */
  public void setVbdef19(String vbdef19) {
    this.setAttributeValue(OutProfitViewVO.VBDEF19, vbdef19);
  }

  /**
   * 设置自定义项2
   * 
   * @param vbdef2 自定义项2
   */
  public void setVbdef2(String vbdef2) {
    this.setAttributeValue(OutProfitViewVO.VBDEF2, vbdef2);
  }

  /**
   * 设置自定义项20
   * 
   * @param vbdef20 自定义项20
   */
  public void setVbdef20(String vbdef20) {
    this.setAttributeValue(OutProfitViewVO.VBDEF20, vbdef20);
  }

  /**
   * 设置自定义项3
   * 
   * @param vbdef3 自定义项3
   */
  public void setVbdef3(String vbdef3) {
    this.setAttributeValue(OutProfitViewVO.VBDEF3, vbdef3);
  }

  /**
   * 设置自定义项4
   * 
   * @param vbdef4 自定义项4
   */
  public void setVbdef4(String vbdef4) {
    this.setAttributeValue(OutProfitViewVO.VBDEF4, vbdef4);
  }

  /**
   * 设置自定义项5
   * 
   * @param vbdef5 自定义项5
   */
  public void setVbdef5(String vbdef5) {
    this.setAttributeValue(OutProfitViewVO.VBDEF5, vbdef5);
  }

  /**
   * 设置自定义项6
   * 
   * @param vbdef6 自定义项6
   */
  public void setVbdef6(String vbdef6) {
    this.setAttributeValue(OutProfitViewVO.VBDEF6, vbdef6);
  }

  /**
   * 设置自定义项7
   * 
   * @param vbdef7 自定义项7
   */
  public void setVbdef7(String vbdef7) {
    this.setAttributeValue(OutProfitViewVO.VBDEF7, vbdef7);
  }

  /**
   * 设置自定义项8
   * 
   * @param vbdef8 自定义项8
   */
  public void setVbdef8(String vbdef8) {
    this.setAttributeValue(OutProfitViewVO.VBDEF8, vbdef8);
  }

  /**
   * 设置自定义项9
   * 
   * @param vbdef9 自定义项9
   */
  public void setVbdef9(String vbdef9) {
    this.setAttributeValue(OutProfitViewVO.VBDEF9, vbdef9);
  }

  /**
   * 销售渠道类型
   * 
   * @return 销售渠道类型id
   */
  public String getCchanneltypeid() {
    return (String) super.getAttributeValue(OutProfitViewVO.CCHANNELTYPEID);
  }

  /**
   * 设置销售渠道类型
   * 
   * @param cchanneltypeid
   */
  public void setCchanneltypeid(String cchanneltypeid) {
    super.setAttributeValue(OutProfitViewVO.CCHANNELTYPEID, cchanneltypeid);
  }

  /**
   * 设置赠品
   * 
   * @param flargess 赠品
   */
  public void seFlargess(UFBoolean flargess) {
    this.setAttributeValue(OutProfitViewVO.FLARGESS, flargess);
  }

  /**
   * 
   * @return 已成本计算的金额
   */
  public UFBoolean getFlargess() {
    return (UFBoolean) super.getAttributeValue(OutProfitViewVO.FLARGESS);
  }
}
