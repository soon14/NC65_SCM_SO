package nc.ui.so.m30.billui.editor.headevent;

import nc.ui.bd.ref.model.FreeCustRefModel;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailBeforeEditEvent;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.pub.keyvalue.IKeyValue;

public class FreeCustEditHandler {

  public void beforeEdit(CardHeadTailBeforeEditEvent e) {

    BillCardPanel cardPanel = e.getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);
    UFBoolean bfreecust =
        keyValue.getHeadUFBooleanValue(SaleOrderHVO.BFREECUSTFLAG);

    String custid = keyValue.getHeadStringValue(SaleOrderHVO.CCUSTOMERID);

    if (PubAppTool.isNull(custid)
        || (null == bfreecust || !bfreecust.booleanValue())) {
      e.setReturnValue(Boolean.FALSE);
      return;
    }

    BillItem freecustitem = cardPanel.getHeadItem(SaleOrderHVO.CFREECUSTID);
    UIRefPane freecustref = (UIRefPane) freecustitem.getComponent();
    FreeCustRefModel freecustrefmodel =
        (FreeCustRefModel) freecustref.getRefModel();
    freecustrefmodel.setCustomSupplier(custid);
  }

}
