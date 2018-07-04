package nc.ui.so.m32.billui.editor.bodyevent;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.so.m32.entity.SaleInvoiceHVO;

/**
 * 
 * 冲抵之后控制字段编辑性编辑器动作
 * 
 * @since 6.0
 * @version 2011-5-20 上午11:06:45
 * @author 么贵敬
 */
public class OffsetBodyEditHandler {

  /**
   * 所有字段的编辑器动作
   * 
   * @param e
   */
  public void beforeEdit(CardBodyBeforeEditEvent e) {
    BillCardPanel cardPanel = e.getBillCardPanel();
    CardKeyValue keyValue = new CardKeyValue(cardPanel);
    UFBoolean bsubunitflag =
        keyValue.getHeadUFBooleanValue(SaleInvoiceHVO.BSUBUNITFLAG);
    if (bsubunitflag.booleanValue()) {
      e.setReturnValue(Boolean.FALSE);
      return;
    }

  }
}
