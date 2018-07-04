package nc.ui.so.m30.billui.editor.bodyevent;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.util.SaleOrderTranTypeUtil;
import nc.vo.so.pub.keyvalue.IKeyValue;

public class ArOrgEditHandler {

  public void beforeEdit(CardBodyBeforeEditEvent e) {

    BillCardPanel cardPanel = e.getBillCardPanel();
    IKeyValue keyvalue = new CardKeyValue(cardPanel);
    String trantypeid = keyvalue.getHeadStringValue(SaleOrderHVO.CTRANTYPEID);
    SaleOrderTranTypeUtil dirtypeutil = new SaleOrderTranTypeUtil();
    e.setReturnValue(!dirtypeutil.isDirectTran(trantypeid));

  }

}
