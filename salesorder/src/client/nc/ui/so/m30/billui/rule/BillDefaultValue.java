package nc.ui.so.m30.billui.rule;

import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.pub.enumeration.BillStatus;
import nc.vo.so.pub.keyvalue.IKeyValue;

public class BillDefaultValue {
  private static final UFDouble ONEHUNDRED = new UFDouble(100);

  public void process(IKeyValue keyvalue) {
    // µ¥¾Ý×´Ì¬
    keyvalue.setHeadValue(SaleOrderHVO.FSTATUSFLAG, BillStatus.FREE.value());
    keyvalue.setHeadValue(SaleOrderHVO.DBILLDATE, AppContext.getInstance()
        .getBusiDate());
    keyvalue.setHeadValue(SaleOrderHVO.NDISCOUNTRATE,
        BillDefaultValue.ONEHUNDRED);
  }

}
