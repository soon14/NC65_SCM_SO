package nc.ui.so.m30.billui.editor.headevent;

import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.AppUiContext;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailBeforeEditEvent;
import nc.ui.so.m30.billui.view.SaleOrderBillForm;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.util.SaleOrderClientContext;
import nc.vo.so.m30trantype.entity.M30TranTypeVO;
import nc.vo.so.pub.keyvalue.IKeyValue;

/**
 * 
 * @since 6.35
 * @version 2013-11-25 14:18:08
 * @author liangjm
 */
public class ArsubTypeEditHandler {

  /**
   * 
   * 
   * @param e
   */
  public void beforeEdit(CardHeadTailBeforeEditEvent e,
      SaleOrderBillForm billform) {
    BillCardPanel cardPanel = e.getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);
    SaleOrderClientContext cache = billform.getM30ClientContext();
    String pk_group = AppUiContext.getInstance().getPkGroup();
    String tranTypeCode =
        keyValue.getHeadStringValue(SaleOrderHVO.VTRANTYPECODE);
    if (tranTypeCode == null) {
      e.setReturnValue(Boolean.FALSE);
      return;
    }

    M30TranTypeVO m30transvo = cache.getTransType(tranTypeCode, pk_group);
    boolean isBlrgcashflag = m30transvo.getBlrgcashflag().booleanValue();
    if (!isBlrgcashflag) {
      e.setReturnValue(Boolean.FALSE);
    }

    UIRefPane uirefpane =
        (UIRefPane) cardPanel.getHeadItem(SaleOrderHVO.CARSUBTYPEID)
            .getComponent();
    String where =
        " pk_billtypeid in(select so.ctrantypeid from so_m35trantype so where fpaymentflag='4' and dr = 0) ";
    uirefpane.setWhereString(where);

  }

}
