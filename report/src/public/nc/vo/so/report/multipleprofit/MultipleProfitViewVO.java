package nc.vo.so.report.multipleprofit;

import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.entity.view.AbstractDataView;
import nc.vo.pubapp.pattern.model.meta.entity.view.DataViewMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.view.IDataViewMeta;

/**
 * 综合毛利分析实体ViewVO
 * 
 * @since 6.3
 * @version 2012-10-18 14:56:33
 * @author zhangkai4
 */
public class MultipleProfitViewVO extends AbstractDataView {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  @Override
  public IDataViewMeta getMetaData() {
    IDataViewMeta viewMeta =
    // 视图工具类创建类，方法，获取元数据
        DataViewMetaFactory.getInstance().getDataViewMeta(
            MultipleProfitViewMeta.class);
    return viewMeta;
  }

  /**
   * 结算财务组织
   */
  public static final String PK_ORG = "pk_org";

  /**
   * 来源系统
   */
  public static final String SOURCESYSTEM = "sourcesystem";

  /**
   * 销售组织
   */
  public static final String CSALEORGID = "csaleorgid";

  /**
   * 销售部门
   */
  public static final String CDPTID = "cdptid";

  /**
   * 业务员
   */
  public static final String CBIZID = "cbizid";

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
  public static final String CMATERIALID = "cmaterialid";

  /**
   * 主单位
   */
  public static final String CUNITID = "cunitid";

  /**
   * 批次号
   */
  public static final String VBATCHCODE = "vbatchcode";

  /**
   * 币种
   */
  public static final String CORIGCURRENCYID = "corigcurrencyid";

  /**
   * 产品线
   */
  public static final String CPRODLINEID = "cprodlineid";

  /**
   * 应收主数量
   */
  public static final String NSHOULDRECEIVNUM = "nshouldreceivnum";

  /**
   * 应收无税金额
   */
  public static final String NTOTALRECEIVMNY = "ntotalreceivmny";

  /**
   * 应收无税单价
   */
  public static final String NTOTALRECEIVEPRICE = "ntotalreceivprice";

  /**
   * 成本主数量
   */
  public static final String NMAINNUM = "nmainnum";

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
   * 
   * @return 主数量
   */
  public UFDouble getNmainnum() {
    return (UFDouble) super.getAttributeValue(MultipleProfitViewVO.NMAINNUM);
  }

  /**
   * 设置主数量
   * 
   * @param nmainnum
   */
  public void setNmainnum(UFDouble nmainnum) {
    super.setAttributeValue(MultipleProfitViewVO.NMAINNUM, nmainnum);
  }

  /**
   * 
   * @return 应收主数量
   */
  public UFDouble getNshouldreceivnum() {
    return (UFDouble) super
        .getAttributeValue(MultipleProfitViewVO.NSHOULDRECEIVNUM);
  }

  /**
   * 设置应收主数量
   * 
   * @param nshouldreceivnum
   * 
   */
  public void setNshouldreceivnum(UFDouble nshouldreceivnum) {
    super.setAttributeValue(MultipleProfitViewVO.NSHOULDRECEIVNUM,
        nshouldreceivnum);
  }

  /**
   * 
   * @return 应收无税单价
   */
  public UFDouble getNtotalReceiveprice() {
    return (UFDouble) super
        .getAttributeValue(MultipleProfitViewVO.NTOTALRECEIVEPRICE);
  }

  /**
   * 设置应收无税单价
   * 
   * @param ntotalreceivprice
   * 
   */
  public void setNtotalReceiveprice(UFDouble ntotalreceivprice) {
    super.setAttributeValue(MultipleProfitViewVO.NTOTALRECEIVEPRICE,
        ntotalreceivprice);
  }

  /**
   * 
   * @return 业务员
   */
  public String getCbizid() {
    return (String) super.getAttributeValue(MultipleProfitViewVO.CBIZID);
  }

  /**
   * 
   * @return 订单客户
   */
  public String getCcustomerid() {
    return (String) super.getAttributeValue(MultipleProfitViewVO.CCUSTOMERID);
  }

  /**
   * 
   * @return 部门
   */
  public String getCdptid() {
    return (String) super.getAttributeValue(MultipleProfitViewVO.CDPTID);
  }

  /**
   * 
   * @return 物料
   */
  public String getCmaterialid() {
    return (String) super.getAttributeValue(MultipleProfitViewVO.CMATERIALID);
  }

  /**
   * 
   * @return 币种
   */
  public String getCorigcurrencyid() {
    return (String) super
        .getAttributeValue(MultipleProfitViewVO.CORIGCURRENCYID);
  }

  /**
   * 
   * @return 产品线
   */
  public String getCprodlineid() {
    return (String) super.getAttributeValue(MultipleProfitViewVO.CPRODLINEID);
  }

  /**
   * 
   * @return 销售组织
   */
  public String getCsaleorgid() {
    return (String) super.getAttributeValue(MultipleProfitViewVO.CSALEORGID);
  }

  /**
   * 
   * @return 成本单价
   */
  public UFDouble getNcostprice() {
    return (UFDouble) super.getAttributeValue(MultipleProfitViewVO.NCOSTPRICE);
  }

  /**
   * 
   * @return 毛利
   */
  public UFDouble getNprofitmny() {
    return (UFDouble) super.getAttributeValue(MultipleProfitViewVO.NPROFITMNY);
  }

  /**
   * 
   * @return 毛利率
   */
  public UFDouble getNprofitrate() {
    return (UFDouble) super.getAttributeValue(MultipleProfitViewVO.NPROFITRATE);
  }

  /**
   * 
   * @return 成本金额
   */
  public UFDouble getNtotalcostmny() {
    return (UFDouble) super
        .getAttributeValue(MultipleProfitViewVO.NTOTALCOSTMNY);
  }

  /**
   * 
   * @return 应收无税金额
   */
  public UFDouble getNtotalreceivmny() {
    return (UFDouble) super
        .getAttributeValue(MultipleProfitViewVO.NTOTALRECEIVMNY);
  }

  /**
   * 
   * @return 地区分类
   */
  public String getPk_areacl() {
    return (String) super.getAttributeValue(MultipleProfitViewVO.PK_AREACL);
  }

  /**
   * 
   * @return 客户基本分类
   */
  public String getPk_custclass() {
    return (String) super.getAttributeValue(MultipleProfitViewVO.PK_CUSTCLASS);
  }

  /**
   * 
   * @return 客户销售分类
   */
  public String getPk_custsaleclass() {
    return (String) super
        .getAttributeValue(MultipleProfitViewVO.PK_CUSTSALECLASS);
  }

  /**
   * 
   * @return 物料基本分类
   */
  public String getPk_marbasclass() {
    return (String) super
        .getAttributeValue(MultipleProfitViewVO.PK_MARBASCLASS);
  }

  /**
   * 
   * @return 物料销售分类
   */
  public String getPk_marsaleclass() {
    return (String) super
        .getAttributeValue(MultipleProfitViewVO.PK_MARSALECLASS);
  }

  /**
   * 
   * @return 批次号
   */
  public String getVbatchcode() {
    return (String) super.getAttributeValue(MultipleProfitViewVO.VBATCHCODE);
  }

  /**
   * 设置业务员
   * 
   * @param cbizid
   */
  public void setCbizid(String cbizid) {
    super.setAttributeValue(MultipleProfitViewVO.CBIZID, cbizid);
  }

  /**
   * 设置订单客户
   * 
   * @param ccustomerid
   */
  public void setCcustomerid(String ccustomerid) {
    super.setAttributeValue(MultipleProfitViewVO.CCUSTOMERID, ccustomerid);
  }

  /**
   * 设置部门
   * 
   * @param cdptid
   */
  public void setCdptid(String cdptid) {
    super.setAttributeValue(MultipleProfitViewVO.CDPTID, cdptid);
  }

  /**
   * 设置物料
   * 
   * @param cmaterialid
   */
  public void setCmaterialid(String cmaterialid) {
    super.setAttributeValue(MultipleProfitViewVO.CMATERIALID, cmaterialid);
  }

  /**
   * 设置币种
   * 
   * @param corigcurrencyid
   */
  public void setCorigcurrencyid(String corigcurrencyid) {
    super.setAttributeValue(MultipleProfitViewVO.CORIGCURRENCYID,
        corigcurrencyid);
  }

  /**
   * 设置产品线
   * 
   * @param cprodlineid
   */
  public void setCprodlineid(String cprodlineid) {
    super.setAttributeValue(MultipleProfitViewVO.CPRODLINEID, cprodlineid);
  }

  /**
   * 设置销售组织
   * 
   * @param csaleorgid
   */
  public void setCsaleorgid(String csaleorgid) {
    super.setAttributeValue(MultipleProfitViewVO.CSALEORGID, csaleorgid);
  }

  /**
   * 设置成本单价
   * 
   * @param ncostprice
   */
  public void setNcostprice(UFDouble ncostprice) {
    super.setAttributeValue(MultipleProfitViewVO.NCOSTPRICE, ncostprice);
  }

  /**
   * 设置毛利
   * 
   * @param nprofitmny
   */
  public void setNprofitmny(UFDouble nprofitmny) {
    super.setAttributeValue(MultipleProfitViewVO.NPROFITMNY, nprofitmny);
  }

  /**
   * 设置毛利率
   * 
   * @param nprofitrate
   */
  public void setNprofitrate(UFDouble nprofitrate) {
    super.setAttributeValue(MultipleProfitViewVO.NPROFITRATE, nprofitrate);
  }

  /**
   * 设置成本金额
   * 
   * @param ntotalcostmny
   */
  public void setNtotalcostmny(UFDouble ntotalcostmny) {
    super.setAttributeValue(MultipleProfitViewVO.NTOTALCOSTMNY, ntotalcostmny);
  }

  /**
   * 设置应收无税金额
   * 
   * @param ntotalreceivmny
   */
  public void setNtotalreceivmny(UFDouble ntotalreceivmny) {
    super.setAttributeValue(MultipleProfitViewVO.NTOTALRECEIVMNY,
        ntotalreceivmny);
  }

  /**
   * 设置地区分类
   * 
   * @param pk_areacl
   */
  public void setPk_areacl(String pk_areacl) {
    super.setAttributeValue(MultipleProfitViewVO.PK_AREACL, pk_areacl);
  }

  /**
   * 设置客户基本分类
   * 
   * @param pk_custclass
   */
  public void setPk_custclass(String pk_custclass) {
    super.setAttributeValue(MultipleProfitViewVO.PK_CUSTCLASS, pk_custclass);
  }

  /**
   * 设置客户销售分类
   * 
   * @param pk_custsaleclass
   */
  public void setPk_custsaleclass(String pk_custsaleclass) {
    super.setAttributeValue(MultipleProfitViewVO.PK_CUSTSALECLASS,
        pk_custsaleclass);
  }

  /**
   * 设置物料基本分类
   * 
   * @param pk_marbasclass
   */
  public void setPk_marbasclass(String pk_marbasclass) {
    super
        .setAttributeValue(MultipleProfitViewVO.PK_MARBASCLASS, pk_marbasclass);
  }

  /**
   * 设置物料销售分类
   * 
   * @param pk_marsaleclass
   */
  public void setPk_marsaleclass(String pk_marsaleclass) {
    super.setAttributeValue(MultipleProfitViewVO.PK_MARSALECLASS,
        pk_marsaleclass);
  }

  /**
   * 设置批次号
   * 
   * @param vbatchcode
   */
  public void setVbatchcode(String vbatchcode) {
    super.setAttributeValue(MultipleProfitViewVO.VBATCHCODE, vbatchcode);
  }

  /**
   * 
   * @return 主单位
   */
  public String getCunitid() {
    return (String) super.getAttributeValue(MultipleProfitViewVO.CUNITID);
  }

  /**
   * 设置主单位
   * 
   * @param cunitid
   */
  public void setCunitid(String cunitid) {
    super.setAttributeValue(MultipleProfitViewVO.CUNITID, cunitid);
  }

  /**
   * 
   * @return 财务结算组织
   */
  public String getPk_org() {
    return (String) super.getAttributeValue(MultipleProfitViewVO.PK_ORG);

  }

  /**
   * 财务结算组织
   * 
   * @param pk_org
   */
  public void setPk_org(String pk_org) {
    super.setAttributeValue(MultipleProfitViewVO.CUNITID, pk_org);
  }

  /**
   * 来源系统
   * 
   * @return sourcesystem
   */
  public String getSourcesystem() {
    return (String) super.getAttributeValue(MultipleProfitViewVO.SOURCESYSTEM);
  }

  /**
   * 来源系统
   * 
   * @param sourcesystem
   */
  public void setSourcesystem(String sourcesystem) {
    super.setAttributeValue(MultipleProfitViewVO.SOURCESYSTEM, sourcesystem);
  }
}
