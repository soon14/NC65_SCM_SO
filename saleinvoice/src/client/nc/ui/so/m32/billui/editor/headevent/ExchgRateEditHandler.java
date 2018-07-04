package nc.ui.so.m32.billui.editor.headevent;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailBeforeEditEvent;
import nc.ui.so.m32.billui.pub.CardPanelCalculator;
import nc.ui.so.m32.billui.pub.CardVATCalculator;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m32.entity.SaleInvoiceHVO;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>销售发票表头折本汇率（包括折本汇率、集团本位币汇率、全局本位币汇率）编辑事件处理
 * </ul>
 * <p>
 * 
 * @version 本版本号 6.0
 * @since 上一版本号 5.6
 * @author 冯加滨
 * @time 2010-3-1 下午03:48:12
 */
public class ExchgRateEditHandler {

  /**
   * 方法功能描述：折本汇率编辑后事件。
   * <p>
   * <b>参数说明</b>
   * 
   * @param e
   *          <p>
   * @author 冯加滨
   * @time 2010-4-27 下午03:09:36
   */
  public void afterEdit(CardHeadTailAfterEditEvent e) {

    BillCardPanel cardpanel = e.getBillCardPanel();
    CardPanelCalculator calculator = new CardPanelCalculator(cardpanel);
    calculator.calculateAll(e.getKey());

    // 重新计算所有行的VAT信息
    CardVATCalculator vatcal = new CardVATCalculator(cardpanel);
    vatcal.calVatAll();
  }

  /**
   * 方法功能描述：折本汇率编辑前事件。
   * <p>
   * <b>参数说明</b>
   * 
   * @param e
   *          <p>
   * @author 冯加滨
   * @time 2010-4-27 下午03:09:02
   */
  public void beforeEdit(CardHeadTailBeforeEditEvent e) {
    BillCardPanel cardPanel = e.getBillCardPanel();
    CardKeyValue keyValue = new CardKeyValue(cardPanel);
    String orgcur = keyValue.getHeadStringValue(SaleInvoiceHVO.CORIGCURRENCYID);
    String currency = keyValue.getHeadStringValue(SaleInvoiceHVO.CCURRENCYID);
    UFDouble nglobalexchgrate = keyValue.getHeadUFDoubleValue(SaleInvoiceHVO.NGLOBALEXCHGRATE);
    UFDouble ngroupexchgrate = keyValue.getHeadUFDoubleValue(SaleInvoiceHVO.NGROUPEXCHGRATE);
    
    String editKey = e.getKey();
    if (SaleInvoiceHVO.NEXCHANGERATE.equals(editKey)
        && !StringUtil.isEmpty(orgcur) && !StringUtil.isEmpty(currency)
        && orgcur.equals(currency)) {
      e.setReturnValue(Boolean.FALSE);
    }
    else if (SaleInvoiceHVO.NGLOBALEXCHGRATE.equals(editKey)
        && MathTool.equals(nglobalexchgrate, UFDouble.ONE_DBL)) {
      e.setReturnValue(Boolean.FALSE);
    }
    else if (SaleInvoiceHVO.NGROUPEXCHGRATE.equals(editKey)
        && MathTool.equals(ngroupexchgrate, UFDouble.ONE_DBL)) {
      e.setReturnValue(Boolean.FALSE);
    }
    
  }
}
