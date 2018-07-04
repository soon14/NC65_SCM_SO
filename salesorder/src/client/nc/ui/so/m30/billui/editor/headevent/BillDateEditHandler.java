package nc.ui.so.m30.billui.editor.headevent;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.AppUiContext;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailBeforeEditEvent;
import nc.ui.so.m30.billui.rule.SrcTypeRule;
import nc.ui.so.m30.billui.view.SaleOrderBillForm;
import nc.ui.so.m30.pub.SaleOrderCalculator;
import nc.ui.so.m30.pub.SaleOrderFindPriceConfig;
import nc.ui.so.pub.findprice.FindSalePrice;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.rule.HeadTotalCalculateRule;
import nc.vo.so.m30.util.SaleOrderClientContext;
import nc.vo.so.m30trantype.entity.M30TranTypeVO;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.rule.BodyUpdateByHead;
import nc.vo.so.pub.rule.BodyValueRowRule;
import nc.vo.so.pub.rule.SOExchangeRateRule;
import nc.vo.so.pub.rule.SOGlobalExchangeRate;
import nc.vo.so.pub.rule.SOGroupExchangeRate;

/**
 * 单据日期编辑事件
 * 
 * @since 6.0
 * @version 2011-6-8 上午10:19:26
 * @author fengjb
 */
public class BillDateEditHandler {

  private SaleOrderBillForm billform;

  /**
   * 设置billFrom
   * 
   * @param billform
   */
  public void setBillform(SaleOrderBillForm billform) {
    this.billform = billform;
  }

  /**
   * 获得billForm
   * 
   * @return SaleOrderBillForm
   */
  public SaleOrderBillForm getBillform() {
    return this.billform;
  }

  /**
   * 单据日期编辑前事件
   * 
   * @param e
   */
  public void beforeEdit(CardHeadTailBeforeEditEvent e) {
    // 来源是合同的：单据日期不可以编辑
    // jilu for 633 需求提出633中还是不可以编辑，65中再放开，令其可以编辑
    // BillCardPanel cardPanel = e.getBillCardPanel();
    // IKeyValue keyValue = new CardKeyValue(cardPanel);

    // SrcTypeRule srcrule = new SrcTypeRule(keyValue);
    // / e.setReturnValue(Boolean.valueOf(!srcrule.isBillSrcCT()));
  }

  /**
   * 编辑后事件
   * 
   * @param e
   */
  public void afterEdit(CardHeadTailAfterEditEvent e) {

    BillCardPanel cardPanel = e.getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);
    // 更新表体日期
    BodyUpdateByHead updaterule = new BodyUpdateByHead(keyValue);
    updaterule.updateAllBodyValueByHead(SaleOrderBVO.DBILLDATE,
        SaleOrderHVO.DBILLDATE);

    // 单据日期
    UFDate dbilldate = this.getBillform().getDbilldate();
    SaleOrderVO modelSale =
        (SaleOrderVO) this.getBillform().getModel().getSelectedData();
    if (null == dbilldate && null != modelSale) {
      dbilldate = modelSale.getParentVO().getDbilldate();
    }
    UFDate newDbilldate = keyValue.getHeadUFDateValue(SaleOrderHVO.DBILLDATE);
    if (null != dbilldate && null != newDbilldate) {
      int ilen = keyValue.getBodyCount();
      for (int i = 0; i < ilen; i++) {
        UFDate dsenddate =
            keyValue.getBodyUFDateValue(i, SaleOrderBVO.DSENDDATE);
        if (null != dsenddate
            && (dbilldate.isSameDate(dsenddate) || newDbilldate
                .compareTo(dsenddate) >= 0)) {
          keyValue.setBodyValue(i, SaleOrderBVO.DSENDDATE, newDbilldate);
        }

        UFDate dreceivedate =
            keyValue.getBodyUFDateValue(i, SaleOrderBVO.DRECEIVEDATE);
        if (null != dreceivedate
            && (dbilldate.isSameDate(dreceivedate) || newDbilldate
                .compareTo(dreceivedate) >= 0)) {
          keyValue.setBodyValue(i, SaleOrderBVO.DRECEIVEDATE, newDbilldate);
        }
      }
      this.getBillform().setDbilldate(newDbilldate);
    }

    BodyValueRowRule countutil = new BodyValueRowRule(keyValue);
    int[] rows = countutil.getMarNotNullRows();
    UFDouble[] oldexrates = new UFDouble[rows.length];
    int i = 0;
    for (int row : rows) {
      oldexrates[i] =
          keyValue.getBodyUFDoubleValue(row, SaleOrderBVO.NEXCHANGERATE);
      i++;
    }

    // 重新计算表体行折本汇率
    SOExchangeRateRule exraterule = new SOExchangeRateRule(keyValue);
    exraterule.calcAllBodyExchangeRate();

    // 全局本位币汇率
    SOGlobalExchangeRate globalraterule = new SOGlobalExchangeRate(keyValue);
    if (globalraterule.isGlobalExchgRateChange(SaleOrderHVO.CORIGCURRENCYID)) {
      globalraterule.calcGlobalExchangeRate(rows);
    }

    // 集团本位币汇率
    SOGroupExchangeRate groupraterule = new SOGroupExchangeRate(keyValue);
    if (groupraterule.isGroupExchgRateChange(SaleOrderHVO.CORIGCURRENCYID)) {
      groupraterule.calcGroupExchangeRate(rows);
    }

    // 重新计算
    SaleOrderCalculator calculator = new SaleOrderCalculator(cardPanel);
    int[] changerows = exraterule.getRateChangeRow();
    calculator.calculate(changerows, SaleOrderBVO.NEXCHANGERATE);

    // 4.询价
    String trantypecode =
        keyValue.getHeadStringValue(SaleOrderHVO.VTRANTYPECODE);
    String pk_group = AppUiContext.getInstance().getPkGroup();
    SaleOrderClientContext clientcontex = this.billform.getM30ClientContext();
    M30TranTypeVO trantypevo =
        clientcontex.getTransType(trantypecode, pk_group);
    SaleOrderFindPriceConfig config =
        new SaleOrderFindPriceConfig(cardPanel, trantypevo);
    FindSalePrice findprice = new FindSalePrice(cardPanel, config);
    findprice.findPriceAfterEdit(rows, SaleOrderHVO.DBILLDATE);

    // 5.合计
    HeadTotalCalculateRule totalrule = new HeadTotalCalculateRule(keyValue);
    totalrule.calculateHeadTotal();

  }
}
