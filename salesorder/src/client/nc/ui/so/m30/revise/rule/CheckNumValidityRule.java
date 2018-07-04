package nc.ui.so.m30.revise.rule;

import nc.ui.ml.NCLangRes;
import nc.ui.pub.bill.BillCardPanel;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.pub.keyvalue.IKeyValue;

public class CheckNumValidityRule {

  public boolean check(BillCardPanel cardPanel, IKeyValue keyValue, int row)
      throws BusinessException {
    UFDouble num = keyValue.getBodyUFDoubleValue(row, SaleOrderBVO.NNUM);
    UFDouble totalsendnum =
        keyValue.getBodyUFDoubleValue(row, SaleOrderBVO.NTOTALSENDNUM);
    UFDouble totalinvoicenum =
        keyValue.getBodyUFDoubleValue(row, SaleOrderBVO.NTOTALINVOICENUM);
    UFDouble totaloutnum =
        keyValue.getBodyUFDoubleValue(row, SaleOrderBVO.NTOTALOUTNUM);

    UFDouble maxTotalNum = UFDouble.ZERO_DBL;
    String maxTotalNumName = null;
    if (MathTool.absCompareTo(totalsendnum, maxTotalNum) > 0) {
      maxTotalNum = totalsendnum;
      maxTotalNumName =
          NCLangRes.getInstance().getStrByID("4006011_0", "04006011-0300")/*累计发货主数量!*/;
    }
    if (MathTool.absCompareTo(totalinvoicenum, maxTotalNum) > 0) {
      maxTotalNum = totalinvoicenum;
      maxTotalNumName =
          NCLangRes.getInstance().getStrByID("4006011_0", "04006011-0301")/*累计开票主数量!*/;
    }
    if (MathTool.absCompareTo(totaloutnum, maxTotalNum) > 0) {
      maxTotalNum = totaloutnum;
      maxTotalNumName =
          NCLangRes.getInstance().getStrByID("4006011_0", "04006011-0302")/*累计出库主数量!*/;
    }

    if (MathTool.absCompareTo(num, maxTotalNum) < 0) {

      // fengjb 2012.03.05 UE规范
      ExceptionUtils.wrappBusinessException(NCLangRes.getInstance().getStrByID(
          "4006011_0", "04006011-0303", null, new String[] {
            maxTotalNumName
          })/*销售订单修订后主数量不能小于{0}*/);
    }
    return true;
  }
}
