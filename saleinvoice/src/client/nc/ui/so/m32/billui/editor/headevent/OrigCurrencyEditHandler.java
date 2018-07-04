package nc.ui.so.m32.billui.editor.headevent;

import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent;
import nc.ui.so.m32.billui.pub.CardPanelCalculator;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.m32.entity.SaleInvoiceBVO;
import nc.vo.so.m32.entity.SaleInvoiceHVO;
import nc.vo.so.m32.rule.ExchangeRateRule;
import nc.vo.so.m32.rule.PriceMnyClearRule;
import nc.vo.so.m32.util.BizTypeUtil;
import nc.vo.so.pub.keyvalue.IKeyValue;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>销售发票表头币种编辑事件处理
 * </ul>
 * <p>
 * 
 * @version 本版本号 6.0
 * @since 上一版本号 5.6
 * @author 冯加滨
 * @time 2010-3-1 下午03:41:32
 */
public class OrigCurrencyEditHandler {

  public void afterEdit(CardHeadTailAfterEditEvent e) {

    BillCardPanel cardPanel = e.getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);
    // 检查是否可以修改
    String bizType = keyValue.getHeadStringValue(SaleInvoiceHVO.CBIZTYPEID);
    String vtrantypecode =
        keyValue.getHeadStringValue(SaleInvoiceHVO.VTRANTYPECODE);
    if (BizTypeUtil.getInstance().isAdjustIncome(bizType, vtrantypecode)) {
      keyValue.setHeadValue(SaleInvoiceHVO.CORIGCURRENCYID, e.getOldValue());
      // fengjb 2012.03.05 UE提示规范
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006008_0", "04006008-0008")/*@res "销售发票配置差额传应收，不允许编辑币种"*/);
    }

    ExchangeRateRule exchghandler = new ExchangeRateRule(keyValue);
    exchghandler.calcExchangeRate();

    String ccurrencyid =
        keyValue.getHeadStringValue(SaleInvoiceHVO.CCURRENCYID);
    String corigcurrencyid =
        keyValue.getHeadStringValue(SaleInvoiceHVO.CORIGCURRENCYID);

    // 20140409 刘达、冯加滨 如果原币和本币一致，就重新取汇率以税额触发单价金额算法，否则就清空。
    if (PubAppTool.isEqual(ccurrencyid, corigcurrencyid)) {
      CardPanelCalculator calculator =
          new CardPanelCalculator(e.getBillCardPanel());
      calculator.calculateAll(SaleInvoiceBVO.NTAX);
    }
    else {
      // 清空表体单价金额字段
      PriceMnyClearRule clearrule = new PriceMnyClearRule(keyValue);
      clearrule.clearAllBodyItem();
    }
    //重新选择币种之后，直接清空客户银行账号, add by liylr 2015-07-11
    keyValue.setHeadValue(SaleInvoiceHVO.CCUSTBANKACCID, null);
    keyValue.setHeadValue(SaleInvoiceHVO.CCUSTBANKID, null);
  }
}
