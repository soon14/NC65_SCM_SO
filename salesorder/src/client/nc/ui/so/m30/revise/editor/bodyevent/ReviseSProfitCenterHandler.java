package nc.ui.so.m30.revise.editor.bodyevent;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.pub.keyvalue.IKeyValue;


public class ReviseSProfitCenterHandler {

  //累计下游数量
  private static final String[] TOTALNUMKEY = new String[] {
    // 累计发货数量、累计开票数量
    SaleOrderBVO.NTOTALSENDNUM, SaleOrderBVO.NTOTALINVOICENUM,
    // 累计出库数量、 累计应发未出库数量
    SaleOrderBVO.NTOTALOUTNUM, SaleOrderBVO.NTOTALNOTOUTNUM,
    // 累计签收数量、 累计途损数量
    SaleOrderBVO.NTOTALSIGNNUM, SaleOrderBVO.NTRANSLOSSNUM,
    // 累计出库对冲数量、累计暂估应收数量
    SaleOrderBVO.NTOTALRUSHNUM, SaleOrderBVO.NTOTALESTARNUM,
    // 累计确认应收数量、累计成本结算数量
    SaleOrderBVO.NTOTALARNUM, SaleOrderBVO.NTOTALCOSTNUM,
    // 累计暂估应收金额、 累计确认应收金额
    SaleOrderBVO.NTOTALESTARMNY, SaleOrderBVO.NTOTALARMNY,
    // 累计安排委外订单数量、累计安排请购单数量
    SaleOrderBVO.NARRANGESCORNUM, SaleOrderBVO.NARRANGEPOAPPNUM,
    // 累计安排调拨订单数量、累计安排调入申请数量
    SaleOrderBVO.NARRANGETOORNUM, SaleOrderBVO.NARRANGETOAPPNUM,
    // 累计安排生产订单数量、累计安排采购订单数量
    SaleOrderBVO.NARRANGEMONUM, SaleOrderBVO.NARRANGEPONUM,
    // 累计发出商品、 累计退货数量
    SaleOrderBVO.NTOTALRETURNNUM, SaleOrderBVO.NTOTALTRADENUM
  };

  public void beforeEdit(CardBodyBeforeEditEvent e) {
    BillCardPanel cardPanel = e.getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);
    UFDouble value = null;
    int length = ReviseSProfitCenterHandler.TOTALNUMKEY.length;
    for (int i = 0; i < length; i++) {
      String key = ReviseSProfitCenterHandler.TOTALNUMKEY[i];
      value = keyValue.getBodyUFDoubleValue(e.getRow(), key);
      if (MathTool.absCompareTo(value, UFDouble.ZERO_DBL) > 0) {
        e.setReturnValue(Boolean.FALSE);
      }
    }
    e.setReturnValue(Boolean.TRUE);
  }
}
