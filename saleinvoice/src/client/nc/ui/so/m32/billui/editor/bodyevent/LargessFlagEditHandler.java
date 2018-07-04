package nc.ui.so.m32.billui.editor.bodyevent;

import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.scmpub.vattax.vo.CalVatFieldValues;
import nc.vo.so.m32.entity.SaleInvoiceBVO;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.so.m32.billui.pub.CardVATCalculator;
import nc.ui.so.pub.keyvalue.CardKeyValue;

/**
 * 赠品标志位编辑事件
 * 
 * @since 6.1
 * @version 2012-11-21 13:35:29
 * @author 冯加彬
 */
public class LargessFlagEditHandler {

  /**
   * 方法功能描述：赠品标志编辑前事件，来源于上游的发票行赠品标志不可编辑。
   * <p>
   * <b>参数说明</b>
   * 
   * @param e
   *          <p>
   * @author 冯加滨
   * @time 2010-4-9 上午10:45:55
   */
  public void beforeEdit(CardBodyBeforeEditEvent e) {

    BillCardPanel cardPanel = e.getBillCardPanel();
    CardKeyValue keyValue = new CardKeyValue(cardPanel);
    // 来源单据ID不为空不可编辑
    String sourceid =
        keyValue.getBodyStringValue(e.getRow(), SaleInvoiceBVO.CSRCID);

    if (!PubAppTool.isNull(sourceid)) {
      e.setReturnValue(Boolean.FALSE);
      return;
    }
  }

  /**
   * 赠品标志编辑后事件
   * 
   * @param e
   */
  public void afterEdit(CardBodyAfterEditEvent e) {
    int row = e.getRow();
    BillCardPanel cardPanel = e.getBillCardPanel();
    // 计算VAT合计
    CardVATCalculator vatcal = new CardVATCalculator(cardPanel);
    CalVatFieldValues oldvat = vatcal.getVatFieldValues(row);
    UFBoolean oldlarflag = (UFBoolean) e.getOldValue();
    oldvat.setBlargessflag(oldlarflag);
    vatcal.calculateVatWhenEditVat(row, oldvat);
  }

}
