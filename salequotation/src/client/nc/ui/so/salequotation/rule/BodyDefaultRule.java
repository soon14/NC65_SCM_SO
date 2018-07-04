package nc.ui.so.salequotation.rule;

import nc.vo.so.salequotation.entity.SalequotationBVO;
import nc.vo.so.pub.keyvalue.IKeyValue;

public class BodyDefaultRule {

  private static final String[] PRICE_KEY = new String[] {
    SalequotationBVO.PK_PRICEPOLICY, SalequotationBVO.PK_TARIFFDEF,
    SalequotationBVO.PK_PRICETYPE, SalequotationBVO.VPRICEDETAIL,
    SalequotationBVO.NQTORIGTAXPRICE, SalequotationBVO.NQTORIGTAXNETPRC,
    SalequotationBVO.NQTORIGPRICE, SalequotationBVO.NQTORIGNETPRICE,
    SalequotationBVO.NORIGTAXPRICE, SalequotationBVO.NORIGTAXNETPRICE,
    SalequotationBVO.NORIGPRICE, SalequotationBVO.NORIGNETPRICE,
    SalequotationBVO.NORIGTAXMNY, SalequotationBVO.NORIGMNY
  };

  private IKeyValue keyValue;

  public BodyDefaultRule(IKeyValue keyValue) {
    this.keyValue = keyValue;
  }

  public void setBodyDefaultRule(int[] rows) {
    for (int row : rows) {
      this.keyValue.setBodyValue(row, SalequotationBVO.NNUM, null);
      this.keyValue.setBodyValue(row, SalequotationBVO.NASSISTNUM, null);
      this.keyValue.setBodyValue(row, SalequotationBVO.NQTNUM, null);
    }
  }

  public void clearBodyPriceItemsValue(int[] rows) {
    for (int row : rows) {
      for (String str : PRICE_KEY) {
        this.keyValue.setBodyValue(row, str, null);
      }
    }
  }
}
