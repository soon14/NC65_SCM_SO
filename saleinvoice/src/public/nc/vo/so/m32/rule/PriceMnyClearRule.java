package nc.vo.so.m32.rule;

import nc.vo.so.m32.entity.SaleInvoiceBVO;
import nc.vo.so.m32.entity.SaleInvoiceHVO;
import nc.vo.so.pub.keyvalue.IKeyValue;

/**
 * 单价金额清空
 * 
 * @since 6.0
 * @version 2011-1-24 下午04:15:21
 * @author fengjb1
 */
public class PriceMnyClearRule {

  /** 表体单价金额字段 **/
  private static final String[] BODY_PRICEMNYKEY = new String[] {
    SaleInvoiceBVO.NORIGTAXPRICE, SaleInvoiceBVO.NORIGPRICE,
    SaleInvoiceBVO.NORIGTAXNETPRICE, SaleInvoiceBVO.NORIGNETPRICE,

    SaleInvoiceBVO.NTAXPRICE, SaleInvoiceBVO.NPRICE,
    SaleInvoiceBVO.NTAXNETPRICE, SaleInvoiceBVO.NNETPRICE,

    SaleInvoiceBVO.NQTORIGTAXPRICE, SaleInvoiceBVO.NQTORIGPRICE,
    SaleInvoiceBVO.NQTORIGTAXNETPRC, SaleInvoiceBVO.NQTORIGNETPRICE,

    SaleInvoiceBVO.NQTTAXPRICE, SaleInvoiceBVO.NQTPRICE,
    SaleInvoiceBVO.NQTTAXNETPRICE, SaleInvoiceBVO.NQTNETPRICE,

    SaleInvoiceBVO.NORIGTAXMNY, SaleInvoiceBVO.NORIGDISCOUNT,
    SaleInvoiceBVO.NORIGMNY,

    SaleInvoiceBVO.NTAX, SaleInvoiceBVO.NTAXMNY, SaleInvoiceBVO.NDISCOUNT,
    SaleInvoiceBVO.NMNY,

    SaleInvoiceBVO.NGLOBALMNY, SaleInvoiceBVO.NGLOBALTAXMNY,
    SaleInvoiceBVO.NGROUPMNY, SaleInvoiceBVO.NGROUPTAXMNY, "nbforigsubmny",
    SaleInvoiceBVO.NORIGSUBMNY, SaleInvoiceBVO.NCALTAXMNY

  };

  /** 表头单价金额字段 **/
  private static final String[] HEAD_PRICEMNYKEY = new String[] {
    "ntotalnmy", SaleInvoiceHVO.NTOTALORIGSUBMNY, SaleInvoiceHVO.NTOTALORIGMNY
  };

  private IKeyValue keyValue;

  public PriceMnyClearRule(IKeyValue keyvalue) {
    this.keyValue = keyvalue;
  }

  public void clearAllBodyItem() {
    for (String key : PriceMnyClearRule.HEAD_PRICEMNYKEY) {
      this.keyValue.setHeadValue(key, null);
    }
    int iloop = this.keyValue.getBodyCount();
    for (int i = 0; i < iloop; i++) {
      for (String key : PriceMnyClearRule.BODY_PRICEMNYKEY) {
        this.keyValue.setBodyValue(i, key, null);
      }
    }
  }
}
