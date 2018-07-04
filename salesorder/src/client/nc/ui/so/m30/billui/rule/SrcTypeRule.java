package nc.ui.so.m30.billui.rule;

import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.scmpub.res.billtype.CTBillType;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.pub.keyvalue.IKeyValue;

public class SrcTypeRule {

  private IKeyValue keyValue;

  public SrcTypeRule(
      IKeyValue keyValue) {
    this.keyValue = keyValue;
  }

  // 单据是否来源于销售合同
  public boolean isBillSrcCT() {
    int bodycount = this.keyValue.getBodyCount();
    for (int i = 0; i < bodycount; i++) {
      String fromtype =
          this.keyValue.getBodyStringValue(i, SaleOrderBVO.VSRCTYPE);
      if (PubAppTool.isEqual(CTBillType.SaleDaily.getCode(), fromtype)) {
        return true;
      }
    }
    return false;
  }
}
