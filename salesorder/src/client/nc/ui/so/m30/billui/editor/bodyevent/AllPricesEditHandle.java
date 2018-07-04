package nc.ui.so.m30.billui.editor.bodyevent;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.so.m30.billui.view.SaleOrderBillForm;
import nc.ui.so.m30.pub.SaleOrderFindPriceConfig;
import nc.ui.so.pub.editable.SOCardEditableSetter;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.util.SaleOrderClientContext;
import nc.vo.so.m30trantype.entity.M30TranTypeVO;
import nc.vo.so.pub.enumeration.AskPriceRule;
import nc.vo.so.pub.keyvalue.IKeyValue;

/**
 * 所有单价字段编辑事件
 * 
 * @since 6.3
 * @version 2013-8-20 下午03:40:18
 * @author 董礼
 */
@SuppressWarnings("restriction")
public class AllPricesEditHandle {

  /**
   * 单价金额编辑前事件
   * 
   * @param e
   * @param billform
   */
  public void beforeEdit(CardBodyBeforeEditEvent e, SaleOrderBillForm billform) {
    BillCardPanel cardPanel = e.getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);
    String editkey = e.getKey();
    int row = e.getRow();

    // 1.取交易类型参数
    String tranTypeCode =
        keyValue.getHeadStringValue(SaleOrderHVO.VTRANTYPECODE);
    String pk_group = AppContext.getInstance().getPkGroup();
    SaleOrderClientContext cache = billform.getM30ClientContext();
    M30TranTypeVO m30transvo = cache.getTransType(tranTypeCode, pk_group);
    SaleOrderFindPriceConfig config =
        new SaleOrderFindPriceConfig(cardPanel, m30transvo);
    // 2.询价策略,判定是否询价
    Integer askrule = config.getAskPriceRule();
    if (AskPriceRule.ASKPRICE_NO.equalsValue(askrule)) {
      return;
    }

    // 3.当edit=价格时，判断是否可编辑
    Boolean bmodifyaskedqt = config.isModifyAskSucess();
    Boolean bmodifyunaskedqt = config.isModifyAskFail();

    // 4.取出当前编辑项的值，判断是否询价成功
    // --当前项为空，应该是询价失败
    if (MathTool.isZero(keyValue.getBodyUFDoubleValue(row, editkey))) {
      // 询价失败是否可修改
      if (bmodifyunaskedqt) {
        return;
      }
      setPriceMnyCellEditable(cardPanel, row, false);
    }
    // --当前项不为空，应该是询价成功
    else {
      // 询价成功是否可改
      if (bmodifyaskedqt || bmodifyunaskedqt) {
        return;
      }
      setPriceMnyCellEditable(cardPanel, row, false);
    }
  }

  /**
   * 设置单价金额编辑性
   * 
   * @param cardPanel
   * @param row
   * @param editable
   */
  private void setPriceMnyCellEditable(BillCardPanel cardPanel, int row,
      boolean editable) {
    for (String key : SOCardEditableSetter.BODY_PRICEKEY) {
      cardPanel.setCellEditable(row, key, editable);
    }
    for (String key : SOCardEditableSetter.BODY_MNYKEY) {
      cardPanel.setCellEditable(row, key, editable);
    }
  }
}
