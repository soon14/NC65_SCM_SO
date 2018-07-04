package nc.ui.so.m32.billui.editor.headevent;

import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m32.entity.SaleInvoiceHVO;

import nc.ui.ml.NCLangRes;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailBeforeEditEvent;
import nc.ui.so.pub.keyvalue.CardKeyValue;

/**
 * 发票折扣编辑事件
 * 
 * @since 6.1
 * @version 2013-04-12 14:25:57
 * @author yixl
 */
public class NhvoicedisrateEditHandler {

  /**
   * 编辑前事件
   * 
   * @param e
   */
  public void beforeEdit(CardHeadTailBeforeEditEvent e) {
    BillCardPanel cardPanel = e.getBillCardPanel();
    CardKeyValue keyValue = new CardKeyValue(cardPanel);
    // 是否费用冲抵
    UFBoolean bsubunitflag =
        keyValue.getHeadUFBooleanValue(SaleInvoiceHVO.BSUBUNITFLAG);

    if (bsubunitflag.booleanValue()) {
      ExceptionUtils.wrappBusinessException(NCLangRes.getInstance().getStrByID(
          "4006008_0", "04006008-0151")/*销售发票已做费用冲抵，不允许修改！*/);
    }
  }
}
