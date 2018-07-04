package nc.ui.so.m30.billui.editor.headevent;

import nc.ui.ml.NCLangRes;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent;
import nc.ui.so.m30.billui.view.SaleOrderBillForm;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.pub.keyvalue.IKeyValue;

/**
 * 本次收款金额编辑事件
 * 
 * @since 6.0
 * @version 2011-6-9 上午09:52:13
 * @author fengjb
 */
public class ThisReceiveMnyEditHandler {

  private SaleOrderBillForm billform;

  public void afterEdit(CardHeadTailAfterEditEvent e) {

    BillCardPanel panel = e.getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(panel);
    UFDouble thisreceivemny =
        keyValue.getHeadUFDoubleValue(SaleOrderHVO.NTHISRECEIVEMNY);
    UFDouble ntotalorigmny =
        keyValue.getHeadUFDoubleValue(SaleOrderHVO.NTOTALORIGMNY);
    if (MathTool.isDiffSign(thisreceivemny, ntotalorigmny)) {
      keyValue.setHeadValue(SaleOrderHVO.NTHISRECEIVEMNY, UFDouble.ZERO_DBL);
      // fengjb 2012.03.05 UE提示规范
      ExceptionUtils.wrappBusinessException(NCLangRes.getInstance().getStrByID(
          "4006011_0", "04006011-0412")
      /*销售订单价税合计与收款单金额算术符号不一致！*/);
    }
    this.getBillform().setThisGatheringMny(thisreceivemny);
  }

  public SaleOrderBillForm getBillform() {
    return this.billform;
  }

  public void setBillform(SaleOrderBillForm billform) {
    this.billform = billform;
  }

}
