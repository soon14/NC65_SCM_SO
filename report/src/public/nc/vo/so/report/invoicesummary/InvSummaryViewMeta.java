package nc.vo.so.report.invoicesummary;

import nc.vo.pub.JavaType;
import nc.vo.pubapp.pattern.model.meta.entity.view.DataViewMeta;
import nc.vo.pubapp.pattern.model.meta.entity.vo.Attribute;
import nc.vo.so.m32.entity.SaleInvoiceBVO;
import nc.vo.so.m32.entity.SaleInvoiceHVO;

/**
 * 销售发票执行汇总视图META
 * 
 * @since 6.3
 * @version 2012-09-10 09:21:11
 * @author 梁吉明
 */
public class InvSummaryViewMeta extends DataViewMeta {

  /**
   * 主实体属性字段名
   */
  public static final String[] SALEINV_HKEYS = new String[] {
    InvSummaryViewVO.CSALEINVOICEID, InvSummaryViewVO.PK_ORG,
    InvSummaryViewVO.DBILLDATE, InvSummaryViewVO.CINVOICECUSTID,
    InvSummaryViewVO.CORIGCURRENCYID, InvSummaryViewVO.CTRANTYPEID
  };

  /**
   * 子实体属性字段名
   */
  public static final String[] SALEINV_BKEYS = {
    InvSummaryViewVO.CSALEINVOICEBID, InvSummaryViewVO.CORDERCUSTID,
    InvSummaryViewVO.CSALEORGID, InvSummaryViewVO.CDEPTID,
    InvSummaryViewVO.CEMPLOYEEID, InvSummaryViewVO.CSENDSTOCKORGID,
    InvSummaryViewVO.CMATERIALID, InvSummaryViewVO.CUNITID,
    InvSummaryViewVO.VBATCHCODE, InvSummaryViewVO.VFIRSTTRANTYPE,
    InvSummaryViewVO.NNUM, InvSummaryViewVO.BLARGESSFLAG,
    InvSummaryViewVO.NORIGMNY, InvSummaryViewVO.NORIGTAXMNY,
    InvSummaryViewVO.NTAX, InvSummaryViewVO.NORIGDISCOUNT,
    InvSummaryViewVO.BLABORFLAG, InvSummaryViewVO.BDISCOUNTFLAG
  };

  /**
   * 扩展String字段
   * 临时表返回的String字段
   */
  public static final String[] EXTEND_STRKEYS = {
    InvSummaryViewVO.PK_CUSTCLASS, InvSummaryViewVO.PK_CUSTSALECLASS,
    InvSummaryViewVO.PK_AREACL, InvSummaryViewVO.PK_MARBASCLASS,
    InvSummaryViewVO.PK_MARSALECLASS, InvSummaryViewVO.CCHANNELTYPEID
  };

  /**
   * 扩展UFDouble字段
   * 临时表返回的UFDouble字段
   */
  public static final String[] EXTEND_UFKEYS = {
    InvSummaryViewVO.NSHOULDRECEIVNUM, InvSummaryViewVO.NSHOULDRECEIVMNY,
    InvSummaryViewVO.NTOTALRECEIVMNY
  };

  /**
   * 临时表返回的销售发票字段
   */
  public static final String[] TMPTABLE_INVKEYS = new String[] {
    InvSummaryViewVO.PK_ORG, InvSummaryViewVO.CINVOICECUSTID,
    InvSummaryViewVO.CORIGCURRENCYID, InvSummaryViewVO.CTRANTYPEID,
    InvSummaryViewVO.CORDERCUSTID, InvSummaryViewVO.CSALEORGID,
    InvSummaryViewVO.CDEPTID, InvSummaryViewVO.CEMPLOYEEID,
    InvSummaryViewVO.CSENDSTOCKORGID, InvSummaryViewVO.CMATERIALID,
    InvSummaryViewVO.CUNITID, InvSummaryViewVO.VBATCHCODE,
    InvSummaryViewVO.BLARGESSFLAG, InvSummaryViewVO.VFIRSTTRANTYPE,
    InvSummaryViewVO.NNUM, InvSummaryViewVO.NORIGMNY,
    InvSummaryViewVO.NORIGTAXMNY, InvSummaryViewVO.NTAX,
    InvSummaryViewVO.NORIGDISCOUNT, InvSummaryViewVO.BLABORFLAG,
    InvSummaryViewVO.BDISCOUNTFLAG
  };

  /**
   * 构造方法
   */
  public InvSummaryViewMeta() {
    super(SaleInvoiceHVO.class, InvSummaryViewMeta.SALEINV_HKEYS);
    this.add(SaleInvoiceBVO.class, InvSummaryViewMeta.SALEINV_BKEYS);
    this.addRelation(SaleInvoiceHVO.class, InvSummaryViewVO.CSALEINVOICEID,
        SaleInvoiceBVO.class, InvSummaryViewVO.CSALEINVOICEID);
    this.addExtAttributes();
  }

  private void addExtAttributes() {
    for (String field : InvSummaryViewMeta.EXTEND_STRKEYS) {
      this.addAttribute(field, JavaType.String);
    }
    for (String field : InvSummaryViewMeta.EXTEND_UFKEYS) {
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
