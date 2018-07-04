package nc.vo.so.m32.rule;

import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.so.m32.entity.SaleInvoiceBVO;
import nc.vo.so.m32.entity.SaleInvoiceHVO;
import nc.vo.so.pub.keyvalue.IKeyValue;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>销售发票子表默认值设置工具类
 * </ul>
 * <p>
 * 
 * @version 本版本号 6.0
 * @since 上一版本号 5.6
 * @author 冯加滨
 * @time 2010-4-23 下午03:56:56
 */
public class BodyDefaultValue {

  private final UFDouble hundred = new UFDouble(100);

  private IKeyValue keyValue;

  /**
   * BodyDefaultValue 的构造子
   * 
   * @param keyValue
   */
  public BodyDefaultValue(IKeyValue keyValue) {
    this.keyValue = keyValue;
  }

  /**
   * 方法功能描述：给销售发票子表所有行设置默认值。
   * <p>
   * <b>参数说明</b>
   * <p>
   * 
   * @author 冯加滨
   * @time 2010-4-26 下午05:03:44
   */
  public void setAllDefautValue() {
    int rowcount = this.keyValue.getBodyCount();
    for (int i = 0; i < rowcount; i++) {
      this.setDefaultValue(i);
    }
  }

  /**
   * 方法功能描述：给销售发票子表第index行设置默认值。
   * <p>
   * <b>参数说明</b>
   * 
   * @param index
   *          <p>
   * @author 冯加滨
   * @time 2010-4-23 下午03:57:16
   */
  public void setDefaultValue(int index) {
    // 开票组织、
    Object pk_org = this.keyValue.getHeadValue(SaleInvoiceHVO.PK_ORG);
    Object pk_group = this.keyValue.getHeadValue(SaleInvoiceHVO.PK_GROUP);
    this.keyValue.setBodyValue(index, SaleInvoiceBVO.PK_ORG, pk_org);
    this.keyValue.setBodyValue(index, SaleInvoiceBVO.PK_GROUP, pk_group);
    // 单据日期
    Object billdate = this.keyValue.getHeadValue(SaleInvoiceHVO.DBILLDATE);
    this.keyValue.setBodyValue(index, SaleInvoiceBVO.DBILLDATE, billdate);
    // 整单折扣、单品折扣
    this.keyValue.setBodyValue(index, SaleInvoiceBVO.NDISCOUNTRATE,
        this.hundred);
    this.keyValue.setBodyValue(index, SaleInvoiceBVO.NITEMDISCOUNTRATE,
        this.hundred);
    // 发票折扣
    Object hinvoicedisrate =
        this.keyValue.getHeadValue(SaleInvoiceHVO.NHVOICEDISRATE);
    this.keyValue.setBodyValue(index, SaleInvoiceBVO.NINVOICEDISRATE,
        hinvoicedisrate);
    // 费用冲抵金额
    this.keyValue.setBodyValue(index, SaleInvoiceBVO.NORIGSUBMNY,
        UFDouble.ZERO_DBL);
  }

  /**
   * 方法功能描述：给传入的发票子表VO设置默认值。
   * <p>
   * <b>参数说明</b>
   * 
   * @param newbvo
   *          <p>
   * @author 冯加滨
   * @time 2010-4-23 下午04:37:19
   */
  public void setVODefaultValue(SaleInvoiceBVO newbvo) {
    // 开票组织、
    String pk_org = this.keyValue.getHeadStringValue(SaleInvoiceHVO.PK_ORG);
    String pk_group = this.keyValue.getHeadStringValue(SaleInvoiceHVO.PK_GROUP);
    if (null == newbvo.getCarorgid()) {
      newbvo.setCarorgid(pk_org);
      newbvo.setCarorgvid(this.keyValue
          .getHeadStringValue(SaleInvoiceHVO.PK_ORG_V));
    }
    newbvo.setPk_org(pk_org);
    newbvo.setPk_group(pk_group);
    // 单据日期
    UFDate billdate =
        this.keyValue.getHeadUFDateValue(SaleInvoiceHVO.DBILLDATE);
    newbvo.setDbilldate(billdate);
    // 整单折扣、单品折扣
    newbvo.setNdiscountrate(this.hundred);
    newbvo.setNitemdiscountrate(this.hundred);
    // 发票折扣
    UFDouble hinvoicedisrate =
        this.keyValue.getHeadUFDoubleValue(SaleInvoiceHVO.NHVOICEDISRATE);
    newbvo.setNinvoicedisrate(hinvoicedisrate);
    // 费用冲抵金额
    newbvo.setNorigsubmny(new UFDouble(0));
  }
}
