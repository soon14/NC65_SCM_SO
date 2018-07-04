package nc.vo.so.report.outsummary;

import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.entity.view.AbstractDataView;
import nc.vo.pubapp.pattern.model.meta.entity.view.DataViewMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.view.IDataViewMeta;

/**
 * 销售出库执行汇总视图VO
 * 
 * @since 6.3
 * @version 2012-10-18 13:36:47
 * @author 梁吉明
 */
public class OutSummaryViewVO extends AbstractDataView {

  /**
   * 
   */
  private static final long serialVersionUID = -5218796592933427907L;

  /**
   * 销售出库单主实体
   */
  public static final String CGENERALHID = "cgeneralhid";

  /**
   * 销售出库单子实体
   */
  public static final String CGENERALBID = "cgeneralbid";

  /**
   * 销售组织
   */
  public static final String CSALEORGOID = "csaleorgoid";

  /**
   * 库存组织
   */
  public static final String PK_ORG = "pk_org";

  /**
   * 订单客户
   */
  public static final String CCUSTOMERID = "ccustomerid";

  /**
   * 销售渠道类型
   */
  public static final String CCHANNELTYPEID = "cchanneltypeid";

  /**
   * 销售部门
   */
  public static final String CDPTID = "cdptid";

  /**
   * 销售业务员
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
   * 开票客户
   */
  public static final String CINVOICECUSTID = "cinvoicecustid";

  /**
   * 收货客户
   */
  public static final String CRECEIEVEID = "creceieveid";

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
   * 主单位
   */
  public static final String CUNITID = "cunitid";

  /**
   * 批次号
   */
  public static final String VBATCHCODE = "vbatchcode";

  /**
   * 赠品
   */
  public static final String FLARGESS = "flargess";

  /**
   * 币种
   */
  public static final String CORIGCURRENCYID = "corigcurrencyid";

  /**
   * 出库主数量
   */
  public static final String NNUM = "nnum";

  /**
   * 出库金额
   */
  public static final String NORIGTAXMNY = "norigtaxmny";

  /**
   * 签收主数量
   */
  public static final String NACCUMOUTSIGNNUM = "naccumoutsignnum";

  /**
   * 途损主数量
   */
  public static final String NACCUMWASTNUM = "naccumwastnum";

  /**
   * 退回主数量
   */
  public static final String NACCUMOUTBACKNUM = "naccumoutbacknum";

  /**
   * 开票主数量
   */
  public static final String NSIGNNUM = "nsignnum";

  /**
   * 开票金额
   */
  public static final String NINVOICEMNY = "ninvoicemny";

  /**
   * 应收主数量
   */
  public static final String NARNUM = "narnum";

  /**
   * 应收金额
   */
  public static final String NARMNY = "narmny";

  /**
   * 应收余额
   */
  public static final String NARREMAINMNY = "narremainmny";

  /**
   * 收款金额
   */
  public static final String NPAYMNY = "npaymny";

  @Override
  public IDataViewMeta getMetaData() {
    IDataViewMeta viewMeta =
        DataViewMetaFactory.getInstance().getDataViewMeta(
            OutSummaryViewMeta.class);
    return viewMeta;

  }

  /**
   * 
   * @return 销售出库单主实体
   */
  public String getCgeneralhid() {
    return (String) super.getAttributeValue(OutSummaryViewVO.CGENERALHID);
  }

  /**
   * 设置销售出库单主实体
   * 
   * @param cgeneralhid
   */
  public void setCgeneralhid(String cgeneralhid) {
    super.setAttributeValue(OutSummaryViewVO.CGENERALHID, cgeneralhid);
  }

  /**
   * 
   * @return 销售出库单子实体
   */
  public String getCgeneralbid() {
    return (String) super.getAttributeValue(OutSummaryViewVO.CGENERALBID);
  }

  /**
   * 设置销售出库单子实体
   * 
   * @param cgeneralbid
   */
  public void setCgeneralbid(String cgeneralbid) {
    super.setAttributeValue(OutSummaryViewVO.CGENERALBID, cgeneralbid);
  }

  /**
   * 
   * @return 销售组织
   */
  public String getCsaleorgoid() {
    return (String) super.getAttributeValue(OutSummaryViewVO.CSALEORGOID);
  }

  /**
   * 设置销售组织
   * 
   * @param csaleorgoid
   */
  public void setCsaleorgoid(String csaleorgoid) {
    super.setAttributeValue(OutSummaryViewVO.CSALEORGOID, csaleorgoid);
  }

  /**
   * 
   * @return 库存组织
   */
  public String getPk_org() {
    return (String) super.getAttributeValue(OutSummaryViewVO.PK_ORG);
  }

  /**
   * 设置库存组织
   * 
   * @param pk_org
   */
  public void setPk_org(String pk_org) {
    super.setAttributeValue(OutSummaryViewVO.PK_ORG, pk_org);
  }

  /**
   * 
   * @return 渠道类型
   */
  public String getCchanneltypeid() {
    return (String) super.getAttributeValue(OutSummaryViewVO.CCHANNELTYPEID);
  }

  /**
   * 设置渠道类型
   * 
   * @param cchanneltypeid
   */
  public void setCchanneltypeid(String cchanneltypeid) {
    super.setAttributeValue(OutSummaryViewVO.CCHANNELTYPEID, cchanneltypeid);
  }

  /**
   * 
   * @return 部门
   */
  public String getCdptid() {
    return (String) super.getAttributeValue(OutSummaryViewVO.CDPTID);
  }

  /**
   * 设置部门
   * 
   * @param cdptid
   */
  public void setCdptid(String cdptid) {
    super.setAttributeValue(OutSummaryViewVO.CDPTID, cdptid);
  }

  /**
   * 
   * @return 业务员
   */
  public String getCbizid() {
    return (String) super.getAttributeValue(OutSummaryViewVO.CBIZID);
  }

  /**
   * 设置业务员
   * 
   * @param cbizid
   */
  public void setCbizid(String cbizid) {
    super.setAttributeValue(OutSummaryViewVO.CBIZID, cbizid);
  }

  /**
   * 
   * @return 客户基本分类
   */
  public String getPk_custclass() {
    return (String) super.getAttributeValue(OutSummaryViewVO.PK_CUSTCLASS);
  }

  /**
   * 设置客户基本分类
   * 
   * @param pk_custclass
   */
  public void setPk_custclass(String pk_custclass) {
    super.setAttributeValue(OutSummaryViewVO.PK_CUSTCLASS, pk_custclass);
  }

  /**
   * 
   * @return 客户销售分类
   */
  public String getPk_custsaleclass() {
    return (String) super.getAttributeValue(OutSummaryViewVO.PK_CUSTSALECLASS);
  }

  /**
   * 设置客户销售分类
   * 
   * @param pk_custsaleclass
   */
  public void setPk_custsaleclass(String pk_custsaleclass) {
    super
        .setAttributeValue(OutSummaryViewVO.PK_CUSTSALECLASS, pk_custsaleclass);
  }

  /**
   * 
   * @return 地区分类
   */
  public String getPk_areacl() {
    return (String) super.getAttributeValue(OutSummaryViewVO.PK_AREACL);
  }

  /**
   * 设置地区分类
   * 
   * @param pk_areacl
   */
  public void setPk_areacl(String pk_areacl) {
    super.setAttributeValue(OutSummaryViewVO.PK_AREACL, pk_areacl);
  }

  /**
   * 
   * @return 订单客户
   */
  public String getCcustomerid() {
    return (String) super.getAttributeValue(OutSummaryViewVO.CCUSTOMERID);
  }

  /**
   * 设置订单客户
   * 
   * @param ccustomerid
   */
  public void setCcustomerid(String ccustomerid) {
    super.setAttributeValue(OutSummaryViewVO.CCUSTOMERID, ccustomerid);
  }

  /**
   * 
   * @return 开票客户
   */
  public String getCinvoicecustid() {
    return (String) super.getAttributeValue(OutSummaryViewVO.CINVOICECUSTID);
  }

  /**
   * 设置开票客户
   * 
   * @param cinvoicecustid
   */
  public void setCinvoicecustid(String cinvoicecustid) {
    super.setAttributeValue(OutSummaryViewVO.CINVOICECUSTID, cinvoicecustid);
  }

  /**
   * 
   * @return 收货客户
   */
  public String getCreceieveid() {
    return (String) super.getAttributeValue(OutSummaryViewVO.CRECEIEVEID);
  }

  /**
   * 设置收货客户
   * 
   * @param creceieveid
   */
  public void setCreceieveid(String creceieveid) {
    super.setAttributeValue(OutSummaryViewVO.CRECEIEVEID, creceieveid);
  }

  /**
   * 
   * @return 物料基本分类
   */
  public String getPk_marbasclass() {
    return (String) super.getAttributeValue(OutSummaryViewVO.PK_MARBASCLASS);
  }

  /**
   * 设置物料基本分类
   * 
   * @param pk_marbasclass
   */
  public void setPk_marbasclass(String pk_marbasclass) {
    super.setAttributeValue(OutSummaryViewVO.PK_MARBASCLASS, pk_marbasclass);
  }

  /**
   * 
   * @return 物料销售分类
   */
  public String getPk_marsaleclass() {
    return (String) super.getAttributeValue(OutSummaryViewVO.PK_MARSALECLASS);
  }

  /**
   * 设置物料销售分类
   * 
   * @param pk_marsaleclass
   */
  public void setPk_marsaleclass(String pk_marsaleclass) {
    super.setAttributeValue(OutSummaryViewVO.PK_MARSALECLASS, pk_marsaleclass);
  }

  /**
   * 
   * @return 物料
   */
  public String getCmaterialoid() {
    return (String) super.getAttributeValue(OutSummaryViewVO.CMATERIALOID);
  }

  /**
   * 设置物料
   * 
   * @param cmaterialoid
   */
  public void setCmaterialoid(String cmaterialoid) {
    super.setAttributeValue(OutSummaryViewVO.CMATERIALOID, cmaterialoid);
  }

  /**
   * 
   * @return 主单位
   */
  public String getCunitid() {
    return (String) super.getAttributeValue(OutSummaryViewVO.CUNITID);
  }

  /**
   * 设置主单位
   * 
   * @param cunitid
   */
  public void setCunitid(String cunitid) {
    super.setAttributeValue(OutSummaryViewVO.CUNITID, cunitid);
  }

  /**
   * 
   * @return 批次号
   */
  public String getVbatchcode() {
    return (String) super.getAttributeValue(OutSummaryViewVO.VBATCHCODE);
  }

  /**
   * 设置批次号
   * 
   * @param vbatchcode
   */
  public void setVbatchcode(String vbatchcode) {
    super.setAttributeValue(OutSummaryViewVO.VBATCHCODE, vbatchcode);
  }

  /**
   * 
   * @return 赠品
   */
  public UFBoolean getflargess() {
    return (UFBoolean) super.getAttributeValue(OutSummaryViewVO.FLARGESS);
  }

  /**
   * 设置赠品
   * 
   * @param flargess
   */
  public void setflargess(UFBoolean flargess) {
    super.setAttributeValue(OutSummaryViewVO.FLARGESS, flargess);
  }

  /**
   * 
   * @return 币种
   */
  public String getCorigcurrencyid() {
    return (String) super.getAttributeValue(OutSummaryViewVO.CORIGCURRENCYID);
  }

  /**
   * 设置币种
   * 
   * @param corigcurrencyid
   */
  public void setCorigcurrencyid(String corigcurrencyid) {
    super.setAttributeValue(OutSummaryViewVO.CORIGCURRENCYID, corigcurrencyid);
  }

  /**
   * 
   * @return 出库主数量
   */
  public UFDouble getNnum() {
    return (UFDouble) super.getAttributeValue(OutSummaryViewVO.NNUM);
  }

  /**
   * 设置出库主数量
   * 
   * @param nnum
   */
  public void setNnum(UFDouble nnum) {
    super.setAttributeValue(OutSummaryViewVO.NNUM, nnum);
  }

  /**
   * 
   * @return 出库金额
   */
  public UFDouble getNorigtaxmny() {
    return (UFDouble) super.getAttributeValue(OutSummaryViewVO.NORIGTAXMNY);
  }

  /**
   * 设置出库金额
   * 
   * @param norigtaxmny
   */
  public void setNorigtaxmny(UFDouble norigtaxmny) {
    super.setAttributeValue(OutSummaryViewVO.NORIGTAXMNY, norigtaxmny);
  }

  /**
   * 
   * @return 签收主数量
   */
  public UFDouble getNaccumoutsignnum() {
    return (UFDouble) super
        .getAttributeValue(OutSummaryViewVO.NACCUMOUTSIGNNUM);
  }

  /**
   * 设置签收主数量
   * 
   * @param naccumoutsignnum
   */
  public void setNaccumoutsignnum(UFDouble naccumoutsignnum) {
    super
        .setAttributeValue(OutSummaryViewVO.NACCUMOUTSIGNNUM, naccumoutsignnum);
  }

  /**
   * 
   * @return 途损主数量
   */
  public UFDouble getNaccumwastnum() {
    return (UFDouble) super.getAttributeValue(OutSummaryViewVO.NACCUMWASTNUM);
  }

  /**
   * 设置途损主数量
   * 
   * @param naccumwastnum
   */
  public void setNaccumwastnum(UFDouble naccumwastnum) {
    super.setAttributeValue(OutSummaryViewVO.NACCUMWASTNUM, naccumwastnum);
  }

  /**
   * 
   * @return 退回主数量
   */
  public UFDouble getNaccumoutbacknum() {
    return (UFDouble) super
        .getAttributeValue(OutSummaryViewVO.NACCUMOUTBACKNUM);
  }

  /**
   * 设置退回主数量
   * 
   * @param naccumoutbacknum
   */
  public void setNaccumoutbacknum(UFDouble naccumoutbacknum) {
    super
        .setAttributeValue(OutSummaryViewVO.NACCUMOUTBACKNUM, naccumoutbacknum);
  }

  /**
   * 
   * @return 开票主数量
   */
  public UFDouble getNsignnum() {
    return (UFDouble) super.getAttributeValue(OutSummaryViewVO.NSIGNNUM);
  }

  /**
   * 设置开票主数量
   * 
   * @param nsignnum
   */
  public void setNsignnum(UFDouble nsignnum) {
    super.setAttributeValue(OutSummaryViewVO.NSIGNNUM, nsignnum);
  }

  /**
   * 
   * @return 开票金额
   */
  public UFDouble getNinvoicemny() {
    return (UFDouble) super.getAttributeValue(OutSummaryViewVO.NINVOICEMNY);
  }

  /**
   * 设置开票金额
   * 
   * @param ninvoicemny
   */
  public void setNinvoicemny(UFDouble ninvoicemny) {
    super.setAttributeValue(OutSummaryViewVO.NINVOICEMNY, ninvoicemny);
  }

  /**
   * 
   * @return 应收主数量
   */
  public UFDouble getNarnum() {
    return (UFDouble) super.getAttributeValue(OutSummaryViewVO.NARNUM);
  }

  /**
   * 设置应收主数量
   * 
   * @param narnum
   */
  public void setNarnum(UFDouble narnum) {
    super.setAttributeValue(OutSummaryViewVO.NARNUM, narnum);
  }

  /**
   * 
   * @return 应收金额
   */
  public UFDouble getNarmny() {
    return (UFDouble) super.getAttributeValue(OutSummaryViewVO.NARMNY);
  }

  /**
   * 设置应收金额
   * 
   * @param narmny
   */
  public void setNarmny(UFDouble narmny) {
    super.setAttributeValue(OutSummaryViewVO.NARMNY, narmny);
  }

  /**
   * 
   * @return 应收余额
   */
  public UFDouble getNarremainmny() {
    return (UFDouble) super.getAttributeValue(OutSummaryViewVO.NARREMAINMNY);
  }

  /**
   * 设置应收余额
   * 
   * @param narremainmny
   */
  public void setNarremainmny(UFDouble narremainmny) {
    super.setAttributeValue(OutSummaryViewVO.NARREMAINMNY, narremainmny);
  }

  /**
   * 
   * @return 收款金额
   */
  public UFDouble getNpaymny() {
    return (UFDouble) super.getAttributeValue(OutSummaryViewVO.NPAYMNY);
  }

  /**
   * 设置收款金额
   * 
   * @param npaymny
   */
  public void setNpaymny(UFDouble npaymny) {
    super.setAttributeValue(OutSummaryViewVO.NPAYMNY, npaymny);
  }
}
