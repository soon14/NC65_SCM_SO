package nc.vo.so.report.outsummary;

import nc.vo.ic.m4c.entity.SaleOutBodyVO;
import nc.vo.ic.m4c.entity.SaleOutHeadVO;
import nc.vo.pub.JavaType;
import nc.vo.pubapp.pattern.model.meta.entity.view.DataViewMeta;
import nc.vo.pubapp.pattern.model.meta.entity.vo.Attribute;

/**
 * 销售出库执行汇总查询视图META
 * 
 * @since 6.3
 * @version 2012-10-14 下午6:00:12
 * @author 梁吉明
 */
public class OutSummaryViewMeta extends DataViewMeta {

  /**
   * 销售出库单主实体字段
   */
  public static final String[] SALEOUT_HKEYS = new String[] {
    OutSummaryViewVO.CGENERALHID, OutSummaryViewVO.CSALEORGOID,
    OutSummaryViewVO.PK_ORG, OutSummaryViewVO.CDPTID, OutSummaryViewVO.CBIZID,
    OutSummaryViewVO.CCUSTOMERID
  };

  /**
   * 出库单子实体属性字段名
   */
  public static final String[] SALEOUT_BKEYS = {
    OutSummaryViewVO.CGENERALBID, OutSummaryViewVO.CINVOICECUSTID,
    OutSummaryViewVO.CRECEIEVEID, OutSummaryViewVO.CMATERIALOID,
    OutSummaryViewVO.CUNITID, OutSummaryViewVO.VBATCHCODE,
    OutSummaryViewVO.FLARGESS, OutSummaryViewVO.CORIGCURRENCYID,
    OutSummaryViewVO.NNUM, OutSummaryViewVO.NORIGTAXMNY
  };

  /**
   * 出库单执行表属性字段名
   */
  public static final String[] SALEOUT_EXEKEYS = {
    OutSummaryViewVO.NACCUMOUTSIGNNUM, OutSummaryViewVO.NACCUMOUTBACKNUM,
    OutSummaryViewVO.NACCUMWASTNUM, OutSummaryViewVO.NSIGNNUM
  };

  /**
   * 扩展String字段
   * 临时表返回的String字段
   */
  public static final String[] EXTEND_STRKEYS = {
    OutSummaryViewVO.PK_CUSTCLASS, OutSummaryViewVO.PK_CUSTSALECLASS,
    OutSummaryViewVO.PK_MARBASCLASS, OutSummaryViewVO.PK_MARSALECLASS,
    OutSummaryViewVO.PK_AREACL, OutSummaryViewVO.CCHANNELTYPEID
  };

  /**
   * 扩展UFDouble字段
   * 临时表返回的UFDouble字段
   */
  public static final String[] EXTEND_UFKEYS = {
    OutSummaryViewVO.NACCUMOUTSIGNNUM, OutSummaryViewVO.NACCUMWASTNUM,
    OutSummaryViewVO.NACCUMOUTBACKNUM, OutSummaryViewVO.NSIGNNUM,
    OutSummaryViewVO.NINVOICEMNY, OutSummaryViewVO.NARNUM,
    OutSummaryViewVO.NARMNY, OutSummaryViewVO.NARREMAINMNY,
    OutSummaryViewVO.NPAYMNY
  };

  /**
   * 临时表返回的出库单字段
   */
  public static final String[] TMPTABLE_OUTKEYS = new String[] {
    OutSummaryViewVO.CSALEORGOID, OutSummaryViewVO.PK_ORG,
    OutSummaryViewVO.CDPTID, OutSummaryViewVO.CBIZID,
    OutSummaryViewVO.CCUSTOMERID, OutSummaryViewVO.CINVOICECUSTID,
    OutSummaryViewVO.CRECEIEVEID, OutSummaryViewVO.CMATERIALOID,
    OutSummaryViewVO.CUNITID, OutSummaryViewVO.VBATCHCODE,
    OutSummaryViewVO.FLARGESS, OutSummaryViewVO.CORIGCURRENCYID,
    OutSummaryViewVO.NNUM, OutSummaryViewVO.NORIGTAXMNY
  };

  /**
   * 构造
   */
  public OutSummaryViewMeta() {
    super(SaleOutHeadVO.class, OutSummaryViewMeta.SALEOUT_HKEYS);
    this.add(SaleOutBodyVO.class, OutSummaryViewMeta.SALEOUT_BKEYS);
    this.addRelation(SaleOutHeadVO.class, OutSummaryViewVO.CGENERALHID,
        SaleOutBodyVO.class, OutSummaryViewVO.CGENERALHID);
    this.addExtAttributes();
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

  private void addExtAttributes() {

    for (String field : OutSummaryViewMeta.EXTEND_STRKEYS) {
      this.addAttribute(field, JavaType.String);
    }
    for (String field : OutSummaryViewMeta.EXTEND_UFKEYS) {
      this.addAttribute(field, JavaType.UFDouble);
    }
  }

}
