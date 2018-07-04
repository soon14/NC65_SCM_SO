package nc.ui.so.m32.billui.editor.bodyevent;

import nc.vo.so.m32.entity.SaleInvoiceBVO;
import nc.vo.so.m32.util.CalculatorUtil;
import nc.vo.so.m32.util.HeadTotalCalcUtil;
import nc.vo.so.pub.keyvalue.IKeyValue;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.ui.so.m32.billui.pub.CardPanelCalculator;
import nc.ui.so.m32.billui.pub.CardVATCalculator;
import nc.ui.so.pub.keyvalue.CardKeyValue;

/**
 * 
 * <p>
 * <b>本类主要完成以下功能：</b>
 * 
 * <ul>
 * <li>销售发票表体编辑后事件处理
 * </ul>
 * 
 * <p>
 * 
 * @version 本版本号 6.0
 * @since
 * @author fengjb
 * @time 2010-8-10 上午10:10:25
 */
public class BodyAfterEditHandler implements
    IAppEventHandler<CardBodyAfterEditEvent> {

  @Override
  public void handleAppEvent(CardBodyAfterEditEvent e) {
    // 编辑字段
    String editKey = e.getKey();
    // 物料
    if (SaleInvoiceBVO.CMATERIALVID.equals(editKey)) {
      MaterialEditHandler handler = new MaterialEditHandler();
      handler.afterEdit(e);
    }
    // 业务单位
    else if (SaleInvoiceBVO.CASTUNITID.equals(editKey)) {
      AstUnitEditHandler handler = new AstUnitEditHandler();
      handler.afterEdit(e);
    }
    // 报价单位
    else if (SaleInvoiceBVO.CQTUNITID.equals(editKey)) {
      QtUnitEditHandler handler = new QtUnitEditHandler();
      handler.afterEdit(e);
    }
    // 税码
    else if (SaleInvoiceBVO.CTAXCODEID.equals(editKey)) {
      TaxCodeEditHandler handler = new TaxCodeEditHandler();
      handler.afterEdit(e);
    }
    // 扣税类别
    else if (SaleInvoiceBVO.FTAXTYPEFLAG.equals(editKey)) {
      TaxTypeFlagEditHandler handler = new TaxTypeFlagEditHandler();
      handler.afterEdit(e);
    }
    // 税率
    else if (SaleInvoiceBVO.NTAXRATE.equals(editKey)) {
      TaxRateEditHandler handler = new TaxRateEditHandler();
      handler.afterEdit(e);
    }
    // 赠品标志位
    else if (SaleInvoiceBVO.BLARGESSFLAG.equals(editKey)) {
      LargessFlagEditHandler handler = new LargessFlagEditHandler();
      handler.afterEdit(e);
    }
    // 发货仓库
    else if (SaleInvoiceBVO.CSENDSTORDOCID.equals(editKey)) {
      CsendstockOrgEditHandler handler = new CsendstockOrgEditHandler();
      handler.afterEdit(e);
    }
    // 如果编辑字段不会影响到数量单价金额，不进行计算
    else if (CalculatorUtil.getInstance().getNeedCalKey().contains(editKey)) {
      int row = e.getRow();
      BillCardPanel cardpanel = e.getBillCardPanel();
      // 调用数量单价金额的换算
      CardPanelCalculator calculator = new CardPanelCalculator(cardpanel);
      calculator.calculate(row, editKey);
      // 计算VAT
      CardVATCalculator vatcal = new CardVATCalculator(cardpanel);
      vatcal.calculateVatWhenEditNoVat(row, editKey);
      IKeyValue keyValue = new CardKeyValue(cardpanel);
      HeadTotalCalcUtil.getInstance().calcHeadTotalValue(keyValue);
    }
  }

}
