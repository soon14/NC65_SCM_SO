package nc.vo.so.report.ordersummary;

import nc.vo.pub.JavaType;
import nc.vo.pubapp.pattern.model.meta.entity.view.DataViewMeta;
import nc.vo.pubapp.pattern.model.meta.entity.vo.Attribute;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;

/**
 * 销售订单执行汇总视图META
 * 
 * @since 6.3
 * @version 2012-10-18 13:28:50
 * @author 梁吉明
 */
public class OrderSummaryViewMeta extends DataViewMeta {

  /**
   * 销售订单主实体属性字段名
   */
  public static final String[] SALEORDER_HKEYS = new String[] {
    OrderSummaryViewVO.CSALEORDERID, OrderSummaryViewVO.PK_ORG,
    OrderSummaryViewVO.VBILLCODE, OrderSummaryViewVO.CDEPTID,
    OrderSummaryViewVO.CEMPLOYEEID, OrderSummaryViewVO.CCUSTOMERID,
    OrderSummaryViewVO.CTRANTYPEID
  };

  /**
   * 销售订库单子实体属性字段名
   */
  public static final String[] SALEORDER_BKEYS = {
    OrderSummaryViewVO.CSALEORDERBID, OrderSummaryViewVO.CSENDSTOCKORGID,
    OrderSummaryViewVO.CMATERIALID, OrderSummaryViewVO.CUNITID,
    OrderSummaryViewVO.VBATCHCODE, OrderSummaryViewVO.NNUM,
    OrderSummaryViewVO.NORIGMNY, OrderSummaryViewVO.NORIGTAXMNY,
    OrderSummaryViewVO.NTAX, OrderSummaryViewVO.NORIGDISCOUNT,
    OrderSummaryViewVO.BLARGESSFLAG, OrderSummaryViewVO.BLABORFLAG,
    OrderSummaryViewVO.BDISCOUNTFLAG, OrderSummaryViewVO.BBOUTENDFLAG,
    OrderSummaryViewVO.CORIGCURRENCYID, SaleOrderBVO.CSETTLEORGID,
    SaleOrderBVO.BBCOSTSETTLEFLAG, SaleOrderBVO.BBARSETTLEFLAG
  };

  /**
   * 销售订库单执行表属性字段名
   */
  public static final String[] SALEORDER_EXEKEYS = {
    OrderSummaryViewVO.NOUTNUM, OrderSummaryViewVO.NOUTSIGNNUM,
    OrderSummaryViewVO.NNORWASTNUM, OrderSummaryViewVO.NINVOICENUM
  };

  /**
   * 扩展String字段
   * 临时表返回的String字段
   */
  public static final String[] EXTEND_STRKEYS = {
    OrderSummaryViewVO.PK_CUSTCLASS, OrderSummaryViewVO.PK_CUSTSALECLASS,
    OrderSummaryViewVO.PK_AREACL, OrderSummaryViewVO.PK_MARBASCLASS,
    OrderSummaryViewVO.PK_MARSALECLASS, OrderSummaryViewVO.CCHANNELTYPEID
  };

  /**
   * 扩展UFDouble字段
   * 临时表返回的UFDouble字段
   */
  public static final String[] EXTEND_UFKEYS = {
    OrderSummaryViewVO.NOUTNUM, OrderSummaryViewVO.NOUTSIGNNUM,
    OrderSummaryViewVO.NNORWASTNUM, OrderSummaryViewVO.NWAITOUTNUM,
    OrderSummaryViewVO.NINVOICENUM, OrderSummaryViewVO.NINVOICEORIGTAXMNY,
    OrderSummaryViewVO.NSHOULDRECEIVNUM, OrderSummaryViewVO.NSHOULDRECEIVMNY,
    OrderSummaryViewVO.NTOTALRECEIVMNY, OrderSummaryViewVO.NTOTALCOSTMNY,
    /*OrderSummaryViewVO.NCOSTPRICE,*/ SaleOrderBVO.NTOTALESTARNUM,
    SaleOrderBVO.NTOTALARNUM
  };

  /**
   * 临时表返回的销售订单字段
   */
  public static final String[] TMPTABLE_ORDERKEYS = new String[] {
    OrderSummaryViewVO.PK_ORG, OrderSummaryViewVO.CDEPTID,
    OrderSummaryViewVO.CEMPLOYEEID, OrderSummaryViewVO.CCUSTOMERID,
    OrderSummaryViewVO.CTRANTYPEID, OrderSummaryViewVO.CSENDSTOCKORGID,
    OrderSummaryViewVO.CMATERIALID, OrderSummaryViewVO.CUNITID,
    OrderSummaryViewVO.VBATCHCODE, OrderSummaryViewVO.CORIGCURRENCYID,
    OrderSummaryViewVO.NNUM, OrderSummaryViewVO.NORIGMNY,
    OrderSummaryViewVO.NORIGTAXMNY, OrderSummaryViewVO.NTAX,
    OrderSummaryViewVO.NORIGDISCOUNT, OrderSummaryViewVO.BLARGESSFLAG,
    OrderSummaryViewVO.BLABORFLAG, OrderSummaryViewVO.BDISCOUNTFLAG
  };

  /**
   * 构造方法
   */
  public OrderSummaryViewMeta() {

    // 通过实体VO的Class添加实体元数据的指定属性到视图元数据中
    super(SaleOrderHVO.class, OrderSummaryViewMeta.SALEORDER_HKEYS);
    this.add(SaleOrderBVO.class, OrderSummaryViewMeta.SALEORDER_BKEYS);
    // 指定实体元数据之间的关联关系
    this.addRelation(SaleOrderHVO.class, OrderSummaryViewVO.CSALEORDERID,
        SaleOrderBVO.class, OrderSummaryViewVO.CSALEORDERID);
    this.addExtAttributes();
  }

  private void addExtAttributes() {
    for (String field : OrderSummaryViewMeta.EXTEND_STRKEYS) {
      this.addAttribute(field, JavaType.String);
    }
    for (String field : OrderSummaryViewMeta.EXTEND_UFKEYS) {
      this.addAttribute(field, JavaType.UFDouble);
    }
  }

  private void addAttribute(String itemkey, JavaType type) {
    Attribute attribute = new Attribute(itemkey, null, null);
    attribute.setJavaType(type);
    attribute.setCustom(false);
    attribute.setStatic(false);
    attribute.setPersistence(false);
    attribute.setSerializable(true);
    this.add(attribute);
  }
}
