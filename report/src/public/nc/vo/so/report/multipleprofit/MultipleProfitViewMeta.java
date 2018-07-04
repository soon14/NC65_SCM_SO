package nc.vo.so.report.multipleprofit;

import nc.vo.pub.JavaType;
import nc.vo.pubapp.pattern.model.meta.entity.view.DataViewMeta;
import nc.vo.pubapp.pattern.model.meta.entity.vo.Attribute;

/**
 * 综合毛利分析ViewMeta
 * 
 * @since 6.3
 * @version 2012-10-18 14:59:56
 * @author zhangkai4
 */
public class MultipleProfitViewMeta extends DataViewMeta {

  /**
   * String字段
   * 临时表返回的String字段
   */
  public static final String[] MULPROFIT_STRKEYS = new String[] {
    MultipleProfitViewVO.CBIZID, MultipleProfitViewVO.CCUSTOMERID,
    MultipleProfitViewVO.CDPTID, MultipleProfitViewVO.CMATERIALID,
    MultipleProfitViewVO.CORIGCURRENCYID, MultipleProfitViewVO.CPRODLINEID,
    MultipleProfitViewVO.CSALEORGID, MultipleProfitViewVO.PK_AREACL,
    MultipleProfitViewVO.PK_CUSTCLASS, MultipleProfitViewVO.PK_CUSTSALECLASS,
    MultipleProfitViewVO.PK_MARBASCLASS, MultipleProfitViewVO.PK_MARSALECLASS,
    MultipleProfitViewVO.PK_ORG, MultipleProfitViewVO.VBATCHCODE,
    MultipleProfitViewVO.CUNITID, MultipleProfitViewVO.SOURCESYSTEM

  };

  /**
   * UFDouble字段
   * 临时表返回的UFDouble字段
   */
  public static final String[] MULPROFIT_UFDKEYS = new String[] {
    MultipleProfitViewVO.NCOSTPRICE, MultipleProfitViewVO.NMAINNUM,
    MultipleProfitViewVO.NPROFITMNY, MultipleProfitViewVO.NPROFITRATE,
    MultipleProfitViewVO.NSHOULDRECEIVNUM, MultipleProfitViewVO.NTOTALCOSTMNY,
    MultipleProfitViewVO.NTOTALRECEIVEPRICE,
    MultipleProfitViewVO.NTOTALRECEIVMNY
  };

  /**
   * 所有汇总字段
   */
  public static final String[] AGGKEYS = new String[] {
    MultipleProfitViewVO.NMAINNUM, MultipleProfitViewVO.NSHOULDRECEIVNUM,
    MultipleProfitViewVO.NTOTALCOSTMNY, MultipleProfitViewVO.NTOTALRECEIVMNY,
    MultipleProfitViewVO.NPROFITMNY
  };

  // /**
  // * 需要计算的金额字段
  // */
  // public static final String[] CALCULATEKEYS = new String[] {
  // MultipleProfitViewVO.NCOSTPRICE, MultipleProfitViewVO.NPROFITMNY,
  // MultipleProfitViewVO.NTOTALRECEIVEPRICE, MultipleProfitViewVO.NPROFITRATE
  // };

  /**
   * 分组字段
   */
  public static final String[] GROUPKEYS = new String[] {
    MultipleProfitViewVO.CBIZID,
    MultipleProfitViewVO.CCUSTOMERID,
    MultipleProfitViewVO.CDPTID,
    MultipleProfitViewVO.CMATERIALID,
    // MultipleProfitViewVO.CORIGCURRENCYID,
    MultipleProfitViewVO.CPRODLINEID, MultipleProfitViewVO.CSALEORGID,
    MultipleProfitViewVO.PK_AREACL, MultipleProfitViewVO.PK_CUSTCLASS,
    MultipleProfitViewVO.PK_CUSTSALECLASS, MultipleProfitViewVO.PK_MARBASCLASS,
    MultipleProfitViewVO.PK_MARSALECLASS, MultipleProfitViewVO.PK_ORG,
    MultipleProfitViewVO.VBATCHCODE, MultipleProfitViewVO.SOURCESYSTEM
  };

  /**
   * 金额字段
   */
  public static final String[] MNYKEYS = new String[] {
    MultipleProfitViewVO.NTOTALCOSTMNY, MultipleProfitViewVO.NTOTALRECEIVMNY,
    MultipleProfitViewVO.NPROFITMNY,MultipleProfitViewVO.NPROFITRATE
  };

  /**
   * 数量字段
   */
  public static final String[] NUMKEY = new String[] {
    MultipleProfitViewVO.NMAINNUM, MultipleProfitViewVO.NSHOULDRECEIVNUM
  };

  /**
   * 单价字段
   */
  public static final String[] PRICEKEY = new String[] {
    MultipleProfitViewVO.NCOSTPRICE, MultipleProfitViewVO.NTOTALRECEIVEPRICE,

  };

  /**
   * 构造子
   */
  public MultipleProfitViewMeta() {
    // 设置字段属性
    this.addExtAttributes();
  }

  /**
   * 设置字段属性
   */
  private void addExtAttributes() {
    // String字段类型的设置
    for (String field : MultipleProfitViewMeta.MULPROFIT_STRKEYS) {
      this.addAttribute(field, JavaType.String);
    }
    // UFDouble字段类型的设置
    for (String field : MultipleProfitViewMeta.MULPROFIT_UFDKEYS) {
      this.addAttribute(field, JavaType.UFDouble);
    }
  }

  /**
   * 设置字段类型
   * 
   * @param itemkey 设置的字段
   * @param type 字段的类型
   */
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
