package nc.ui.so.m32.billui.editor.bodyevent;

import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.m32.entity.SaleInvoiceBVO;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.rule.SOUnitChangeRateRule;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.scmpub.ref.FilterMeasdocRefUtils;
import nc.ui.so.m32.billui.pub.CardPanelCalculator;
import nc.ui.so.m32.billui.pub.CardVATCalculator;
import nc.ui.so.pub.keyvalue.CardKeyValue;

/**
 * 
 * <p>
 * <b>本类主要完成以下功能：</b>
 * 
 * <ul>
 * <li>销售发票辅单位编辑事件
 * </ul>
 * 
 * <p>
 * 
 * @version 本版本号 6.0
 * @since
 * @author fengjb
 * @time 2010-9-10 上午10:19:06
 */
public class AstUnitEditHandler {

  /**
   * 方法功能描述：销售发票表体单位编辑后事件，获取换算率。
   * <p>
   * <b>参数说明</b>
   * 
   * @param e
   *          <p>
   * @author 冯加滨
   * @time 2010-4-9 上午10:42:34
   */
  public void afterEdit(CardBodyAfterEditEvent e) {

    int row = e.getRow();
    int[] rows = new int[] {
      row
    };
    BillCardPanel cardPanel = e.getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);
    // 计算新的换算率
    SOUnitChangeRateRule chgraterule = new SOUnitChangeRateRule(keyValue);
    chgraterule.calcAstChangeRate(row);

    // 调用数量单价金额的换算
    CardPanelCalculator calculator = new CardPanelCalculator(cardPanel);
    calculator.calculate(rows, SaleInvoiceBVO.VCHANGERATE);

    // 计算VAT
    CardVATCalculator vatcal = new CardVATCalculator(cardPanel);
    vatcal.calculateVatWhenEditNoVat(row, SaleInvoiceBVO.VCHANGERATE);
  }

  /**
   * 方法功能描述：销售发票表体单位编辑前事件，根据物料设置编辑性和参照约束条件。
   * <p>
   * <b>参数说明</b>
   * 
   * @param e
   *          <p>
   * @author 冯加滨
   * @time 2010-3-12 下午03:44:34
   */
  public void beforeEdit(CardBodyBeforeEditEvent e) {

    BillCardPanel cardPanel = e.getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);
    // 物料为空，不可编辑
    String material =
        keyValue.getBodyStringValue(e.getRow(), SaleInvoiceBVO.CMATERIALID);

    if (PubAppTool.isNull(material)) {
      e.setReturnValue(Boolean.FALSE);
      return;
    }
    // 物料不为空，只能参照物料对应的计量单位
    BillItem astunititem = cardPanel.getBodyItem(SaleInvoiceBVO.CASTUNITID);
    FilterMeasdocRefUtils astunitFilter =
        new FilterMeasdocRefUtils(astunititem);
    astunitFilter.setMaterialPk(material);
  }

}
