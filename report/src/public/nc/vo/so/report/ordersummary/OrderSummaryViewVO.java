package nc.vo.so.report.ordersummary;

import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.entity.view.AbstractDataView;
import nc.vo.pubapp.pattern.model.meta.entity.view.DataViewMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.view.IDataViewMeta;

/**
 * 销售订单执行汇总视图VO
 * 
 * @since 6.3
 * @version 2012-10-18 13:26:57
 * @author 梁吉明
 */
public class OrderSummaryViewVO extends AbstractDataView {

  /**
   * 
   */
  private static final long serialVersionUID = -5952394906291813369L;

  @Override
  public IDataViewMeta getMetaData() {
    IDataViewMeta viewMeta =
    // 视图工具类创建类，方法，获取元数据
        DataViewMetaFactory.getInstance().getDataViewMeta(
            OrderSummaryViewMeta.class);
    return viewMeta;
  }

  /**
   * 订单主实体_主键
   */
  public static final String CSALEORDERID = "csaleorderid";

  /**
   * 订单子实体_主键
   */
  public static final String CSALEORDERBID = "csaleorderbid";

  /**
   * 出库关闭
   */
  public static final String BBOUTENDFLAG = "bboutendflag";

  /**
   * 销售组织
   */
  public static final String PK_ORG = "pk_org";

  /**
   * 库存组织
   */
  public static final String CSENDSTOCKORGID = "csendstockorgid";

  /**
   * 订单号
   */
  public static final String VBILLCODE = "vbillcode";

  /**
   * 订单客户
   */
  public static final String CCUSTOMERID = "ccustomerid";

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
   * 销售部门
   */
  public static final String CDEPTID = "cdeptid";

  /**
   * 销售业务员
   */
  public static final String CEMPLOYEEID = "cemployeeid";

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
   * 物料基本分类
   */
  public static final String PK_MARBASCLASS = "pk_marbasclass";

  /**
   * 物料销售分类
   */
  public static final String PK_MARSALECLASS = "pk_marsaleclass";

  /**
   * 统计服务和价格折扣
   */
  /**
   * 订单类型
   */
  public static final String CTRANTYPEID = "ctrantypeid";

  /**
   * 赠品
   */
  public static final String BLARGESSFLAG = "blargessflag";

  /**
   * 币种
   */
  public static final String CORIGCURRENCYID = "ccurrencyid";

  /**
   * 主数量
   */
  public static final String NNUM = "nnum";

  /**
   * 主含税净价(报表处理)
   * 
   */
  public static final String NORIGTAXNETPRICE = "norigtaxnetprice";
  
  /**
   * 无税金额
   */
  public static final String NORIGMNY = "nmny";

  /**
   * 价税合计
   */
  public static final String NORIGTAXMNY = "ntaxmny";

  /**
   * 税额(本币)
   */
  public static final String NTAX = "ntax";

  /**
   * 折扣额
   */
  public static final String NORIGDISCOUNT = "ndiscount";

  /**
   * 出库主数量
   */
  public static final String NOUTNUM = "ntotaloutnum";

  /**
   * 签收主数量
   */
  public static final String NOUTSIGNNUM = "ntotalsignnum";

  /**
   * 途损主数量
   */
  public static final String NNORWASTNUM = "ntranslossnum";

  /**
   * 待出库主数量
   */
  public static final String NWAITOUTNUM = "nwaitoutnum";

  /**
   * 开票主数量
   */
  public static final String NINVOICENUM = "ntotalinvoicenum";

  /**
   * 开票金额
   */
  public static final String NINVOICEORIGTAXMNY = "ninvoiceorigtaxmny";

  /**
   * 应收主数量
   */
  public static final String NSHOULDRECEIVNUM = "nshouldreceivnum";

  /**
   * 应收金额
   */
  public static final String NSHOULDRECEIVMNY = "nshouldreceivmny";

  /**
   * 收款金额（报表处理）
   */
  public static final String NPAYMNY = "npaymny";
  /**
   * 应收余额
   */
  public static final String NTOTALRECEIVMNY = "ntotalreceivmny";

  /**
   * 成本金额
   */
  public static final String NTOTALCOSTMNY = "ntotalcostmny";

  /**
   * 成本单价
   */
  public static final String NCOSTPRICE = "ncostprice";

  /**
   * 服务类
   */
  public static final String BLABORFLAG = "blaborflag";

  /**
   * 折扣类
   */
  public static final String BDISCOUNTFLAG = "bdiscountflag";

  /**
   * 
   * @return 订单表头id
   */
  public String getCsaleorderid() {
    return (String) this.getAttributeValue(OrderSummaryViewVO.CSALEORDERID);
  }

  /**
   * 
   * @param csaleinvoiceid
   */
  public void setCsaleorderid(String csaleinvoiceid) {
    this.setAttributeValue(OrderSummaryViewVO.CSALEORDERID, csaleinvoiceid);
  }

  /**
   * 
   * @return 订单表体id
   */
  public String getCsaleorderbid() {
    return (String) this.getAttributeValue(OrderSummaryViewVO.CSALEORDERBID);
  }

  /**
   * 
   * @param csaleinvoicebid
   */
  public void setCsaleorderbid(String csaleinvoicebid) {
    this.setAttributeValue(OrderSummaryViewVO.CSALEORDERBID, csaleinvoicebid);
  }

  /**
   * 
   * @return 销售组织
   */
  public String getPk_org() {
    return (String) this.getAttributeValue(OrderSummaryViewVO.PK_ORG);
  }

  /**
   * 
   * @param pk_org
   */
  public void setPk_org(String pk_org) {
    this.setAttributeValue(OrderSummaryViewVO.PK_ORG, pk_org);
  }

  /**
   * 
   * @return 订单号
   */
  public String getVbillcode() {
    return (String) this.getAttributeValue(OrderSummaryViewVO.VBILLCODE);
  }

  /**
   * 
   * @param vbillcode
   */
  public void setVbillcode(String vbillcode) {
    this.setAttributeValue(OrderSummaryViewVO.VBILLCODE, vbillcode);
  }

  /**
   * 
   * @return 订单客户
   */
  public String getCordercustid() {
    return (String) this.getAttributeValue(OrderSummaryViewVO.CCUSTOMERID);
  }

  /**
   * 
   * @param cordercustid
   */
  public void setCordercustid(String cordercustid) {
    this.setAttributeValue(OrderSummaryViewVO.CCUSTOMERID, cordercustid);
  }

  /**
   * 
   * @return 销售渠道类型
   */
  public String getChanneltypeid() {
    return (String) this.getAttributeValue(OrderSummaryViewVO.CCHANNELTYPEID);
  }

  /**
   * 
   * @param channeltypeid
   */
  public void setChanneltypeid(String channeltypeid) {
    this.setAttributeValue(OrderSummaryViewVO.CCHANNELTYPEID, channeltypeid);
  }

  /**
   * 
   * @return 地区分类
   */
  public String getPk_areacl() {
    return (String) super.getAttributeValue(OrderSummaryViewVO.PK_AREACL);
  }

  /**
   * 
   * @return 客户基本分类
   */
  public String getPk_custclass() {
    return (String) super.getAttributeValue(OrderSummaryViewVO.PK_CUSTCLASS);
  }

  /**
   * 
   * @return 客户销售分类
   */
  public String getPk_custsaleclass() {
    return (String) super
        .getAttributeValue(OrderSummaryViewVO.PK_CUSTSALECLASS);
  }

  /**
   * 
   * @return 物料基本分类
   */
  public String getPk_marbasclass() {
    return (String) super.getAttributeValue(OrderSummaryViewVO.PK_MARBASCLASS);
  }

  /**
   * 
   * @return 物料销售分类
   */
  public String getPk_marsaleclass() {
    return (String) super.getAttributeValue(OrderSummaryViewVO.PK_MARSALECLASS);
  }

  /**
   * 设置地区分类
   * 
   * @param pk_areacl
   */
  public void setPk_areacl(String pk_areacl) {
    super.setAttributeValue(OrderSummaryViewVO.PK_AREACL, pk_areacl);
  }

  /**
   * 设置客户基本分类
   * 
   * @param pk_custclass
   */
  public void setPk_custclass(String pk_custclass) {
    super.setAttributeValue(OrderSummaryViewVO.PK_CUSTCLASS, pk_custclass);
  }

  /**
   * 设置客户销售分类
   * 
   * @param pk_custsaleclass
   */
  public void setPk_custsaleclass(String pk_custsaleclass) {
    super.setAttributeValue(OrderSummaryViewVO.PK_CUSTSALECLASS,
        pk_custsaleclass);
  }

  /**
   * 设置物料基本分类
   * 
   * @param pk_marbasclass
   */
  public void setPk_marbasclass(String pk_marbasclass) {
    super.setAttributeValue(OrderSummaryViewVO.PK_MARBASCLASS, pk_marbasclass);
  }

  /**
   * 设置物料销售分类
   * 
   * @param pk_marsaleclass
   */
  public void setPk_marsaleclass(String pk_marsaleclass) {
    super
        .setAttributeValue(OrderSummaryViewVO.PK_MARSALECLASS, pk_marsaleclass);
  }

  /**
   * 
   * @return 部门
   */
  public String getCdeptid() {
    return (String) this.getAttributeValue(OrderSummaryViewVO.CDEPTID);
  }

  /**
   * 
   * @param cdeptvid
   */
  public void setCdeptid(String cdeptvid) {
    this.setAttributeValue(OrderSummaryViewVO.CDEPTID, cdeptvid);
  }

  /**
   * 
   * @return 业务员
   */
  public String getCemployeeid() {
    return (String) this.getAttributeValue(OrderSummaryViewVO.CEMPLOYEEID);
  }

  /**
   * 
   * @param cemployeeid
   */
  public void setCemployeeid(String cemployeeid) {
    this.setAttributeValue(OrderSummaryViewVO.CEMPLOYEEID, cemployeeid);
  }

  /**
   * 
   * @return 物料
   */
  public String getCmaterialid() {
    return (String) super.getAttributeValue(OrderSummaryViewVO.CMATERIALID);
  }

  /**
   * 设置物料
   * 
   * @param cmaterialid
   */
  public void setCmaterialid(String cmaterialid) {
    super.setAttributeValue(OrderSummaryViewVO.CMATERIALID, cmaterialid);
  }

  /**
   * 
   * @return 主单位
   */
  public String getCunitid() {
    return (String) super.getAttributeValue(OrderSummaryViewVO.CUNITID);
  }

  /**
   * 设置主单位
   * 
   * @param cunitid
   */
  public void setCunitid(String cunitid) {
    super.setAttributeValue(OrderSummaryViewVO.CUNITID, cunitid);
  }

  /**
   * 
   * @return 批次号
   */
  public String getVbatchcode() {
    return (String) super.getAttributeValue(OrderSummaryViewVO.VBATCHCODE);
  }

  /**
   * 设置批次号
   * 
   * @param vbatchcode
   */
  public void setVbatchcode(String vbatchcode) {
    super.setAttributeValue(OrderSummaryViewVO.VBATCHCODE, vbatchcode);
  }

  /**
   * 
   * @return 币种
   */
  public String getCorigcurrencyid() {
    return (String) super.getAttributeValue(OrderSummaryViewVO.CORIGCURRENCYID);
  }

  /**
   * 设置币种
   * 
   * @param corigcurrencyid
   */
  public void setCorigcurrencyid(String corigcurrencyid) {
    super
        .setAttributeValue(OrderSummaryViewVO.CORIGCURRENCYID, corigcurrencyid);
  }

  /**
   * 
   * @return 类型
   */
  public String getCtrantypeid() {
    return (String) super.getAttributeValue(OrderSummaryViewVO.CTRANTYPEID);
  }

  /**
   * 类型
   * 
   * @param ctrantypeid
   */
  public void setCtrantypeid(String ctrantypeid) {
    super.setAttributeValue(OrderSummaryViewVO.CTRANTYPEID, ctrantypeid);
  }

  /**
   * 
   * @return 赠品
   */
  public UFBoolean getBlargessflag() {
    return (UFBoolean) this.getAttributeValue(OrderSummaryViewVO.BLARGESSFLAG);
  }

  /**
   * 
   * @param blargessflag
   */
  public void setBlargessflag(UFBoolean blargessflag) {
    this.setAttributeValue(OrderSummaryViewVO.BLARGESSFLAG, blargessflag);
  }

  /**
   * 
   * @return 出库关闭
   */
  public UFBoolean getBboutendflag() {
    return (UFBoolean) this.getAttributeValue(OrderSummaryViewVO.BBOUTENDFLAG);
  }

  /**
   * 
   * @param bboutendflag
   */
  public void setBboutendflag(UFBoolean bboutendflag) {
    this.setAttributeValue(OrderSummaryViewVO.BBOUTENDFLAG, bboutendflag);
  }

  /**
   * 
   * @return 成本金额
   */
  public UFDouble getNtotalcostmny() {
    return (UFDouble) super.getAttributeValue(OrderSummaryViewVO.NTOTALCOSTMNY);
  }

  /**
   * 设置成本金额
   * 
   * @param ntotalcostmny
   */
  public void setNtotalcostmny(UFDouble ntotalcostmny) {
    super.setAttributeValue(OrderSummaryViewVO.NTOTALCOSTMNY, ntotalcostmny);
  }

  /**
   * 
   * @return 主数量
   */
  public UFDouble getNnum() {
    return (UFDouble) this.getAttributeValue(OrderSummaryViewVO.NNUM);
  }

  /**
   * 
   * @param nnum
   */
  public void setNnum(UFDouble nnum) {
    this.setAttributeValue(OrderSummaryViewVO.NNUM, nnum);
  }

  /**
   * 
   * @return 出库主数量
   */
  public UFDouble getNoutnum() {
    return (UFDouble) this.getAttributeValue(OrderSummaryViewVO.NOUTNUM);
  }

  /**
   * 
   * @param noutnum
   */
  public void setNoutnum(UFDouble noutnum) {
    this.setAttributeValue(OrderSummaryViewVO.NOUTNUM, noutnum);
  }

  /**
   * 
   * @return 签收主数量
   */
  public UFDouble getNoutsignnum() {
    return (UFDouble) this.getAttributeValue(OrderSummaryViewVO.NOUTSIGNNUM);
  }

  /**
   * 
   * @param noutnum
   */
  public void setNoutsignnum(UFDouble noutnum) {
    this.setAttributeValue(OrderSummaryViewVO.NOUTSIGNNUM, noutnum);
  }

  /**
   * @return 途损主数量
   */
  public UFDouble getNnorwastnum() {
    return (UFDouble) this.getAttributeValue(OrderSummaryViewVO.NNORWASTNUM);
  }

  /**
   * 
   * @param noutnum
   */
  public void setNnorwastnum(UFDouble noutnum) {
    this.setAttributeValue(OrderSummaryViewVO.NNORWASTNUM, noutnum);
  }

  /**
   * @return 待出库主数量
   */
  public UFDouble getNwaitoutnum() {
    return (UFDouble) this.getAttributeValue(OrderSummaryViewVO.NWAITOUTNUM);
  }

  /**
   * 
   * @param noutnum
   */
  public void setNwaitoutnum(UFDouble noutnum) {
    this.setAttributeValue(OrderSummaryViewVO.NWAITOUTNUM, noutnum);
  }

  /**
   * @return 开票主数量
   */
  public UFDouble getNinvoicenum() {
    return (UFDouble) this.getAttributeValue(OrderSummaryViewVO.NINVOICENUM);
  }

  /**
   * 
   * @param noutnum
   */
  public void setNinvoicenum(UFDouble noutnum) {
    this.setAttributeValue(OrderSummaryViewVO.NINVOICENUM, noutnum);
  }

  /**
   * @return 开票金额
   */
  public UFDouble getNinvoiceorigtaxmny() {
    return (UFDouble) this
        .getAttributeValue(OrderSummaryViewVO.NINVOICEORIGTAXMNY);
  }

  /**
   * 
   * @param noutnum
   */
  public void setNinvoiceorigtaxmny(UFDouble noutnum) {
    this.setAttributeValue(OrderSummaryViewVO.NINVOICEORIGTAXMNY, noutnum);
  }

  /**
   * 
   * @return 无税金额
   */
  public UFDouble getNorigmny() {
    return (UFDouble) this.getAttributeValue(OrderSummaryViewVO.NORIGMNY);
  }

  /**
   * 
   * @param norigmny
   */
  public void setNorigmny(UFDouble norigmny) {
    this.setAttributeValue(OrderSummaryViewVO.NORIGMNY, norigmny);
  }

  /**
   * 
   * @return 价税合计
   */
  public UFDouble getNorigtaxmny() {
    return (UFDouble) this.getAttributeValue(OrderSummaryViewVO.NORIGTAXMNY);
  }

  /**
   * 
   * @param norigtaxmny
   */
  public void setNorigtaxmny(UFDouble norigtaxmny) {
    this.setAttributeValue(OrderSummaryViewVO.NORIGTAXMNY, norigtaxmny);
  }

  /**
   * 
   * @return 税额
   */
  public UFDouble getNtax() {
    return (UFDouble) this.getAttributeValue(OrderSummaryViewVO.NTAX);
  }

  /**
   * 
   * @param ntax
   */
  public void setNtax(UFDouble ntax) {
    this.setAttributeValue(OrderSummaryViewVO.NTAX, ntax);
  }

  /**
   * 
   * @return 折扣额
   */
  public UFDouble getNorigdiscount() {
    return (UFDouble) this.getAttributeValue(OrderSummaryViewVO.NORIGDISCOUNT);
  }

  /**
   * 
   * @param norigdiscount
   */
  public void setNorigdiscount(UFDouble norigdiscount) {
    this.setAttributeValue(OrderSummaryViewVO.NORIGDISCOUNT, norigdiscount);
  }

  /**
   * 
   * @return 应收主数量
   */
  public UFDouble getNshouldreceivnum() {
    return (UFDouble) super
        .getAttributeValue(OrderSummaryViewVO.NSHOULDRECEIVNUM);
  }

  /**
   * 设置应收主数量
   * 
   * @param nshouldreceivnum
   * 
   */
  public void setNshouldreceivnum(UFDouble nshouldreceivnum) {
    super.setAttributeValue(OrderSummaryViewVO.NSHOULDRECEIVNUM,
        nshouldreceivnum);
  }

  /**
   * 设置应收金额
   * 
   * @param nshouldreceivmny
   */
  public void setNshouldreceivmny(UFDouble nshouldreceivmny) {
    super.setAttributeValue(OrderSummaryViewVO.NSHOULDRECEIVMNY,
        nshouldreceivmny);
  }

  /**
   * 
   * @return 应收金额
   */
  public UFDouble getNshouldreceivmny() {
    return (UFDouble) super
        .getAttributeValue(OrderSummaryViewVO.NSHOULDRECEIVMNY);
  }

  /**
   * 设置应收余额
   * 
   * @param ntotalreceivmny
   */
  public void setNtotalreceivmny(UFDouble ntotalreceivmny) {
    super
        .setAttributeValue(OrderSummaryViewVO.NTOTALRECEIVMNY, ntotalreceivmny);
  }

  /**
   * 
   * @return 应收余额
   */
  public UFDouble getNtotalreceivmny() {
    return (UFDouble) super
        .getAttributeValue(OrderSummaryViewVO.NTOTALRECEIVMNY);
  }

  /**
   * 
   * @return 服务类
   */
  public UFBoolean getBlaborflag() {
    return (UFBoolean) this.getAttributeValue(OrderSummaryViewVO.BLABORFLAG);
  }

  /**
   * 
   * @param blaborflag
   */
  public void setBlaborflag(UFBoolean blaborflag) {
    this.setAttributeValue(OrderSummaryViewVO.BLABORFLAG, blaborflag);
  }

  /**
   * 
   * @return 折扣类
   */
  public UFBoolean getBdiscountflag() {
    return (UFBoolean) this.getAttributeValue(OrderSummaryViewVO.BDISCOUNTFLAG);
  }

  /**
   * 
   * @param bdiscountflag
   */
  public void setBdiscountflag(UFBoolean bdiscountflag) {
    this.setAttributeValue(OrderSummaryViewVO.BDISCOUNTFLAG, bdiscountflag);
  }

  /**
   * 
   * @return 成本单价
   */
  public UFDouble getNcostprice() {
    return (UFDouble) super.getAttributeValue(OrderSummaryViewVO.NCOSTPRICE);
  }

  /**
   * 设置成本单价
   * 
   * @param ncostprice
   */
  public void setNcostprice(UFDouble ncostprice) {
    super.setAttributeValue(OrderSummaryViewVO.NCOSTPRICE, ncostprice);
  }
}
