/**
 * 
 */
package nc.ui.so.m30.billui.action;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.actions.CopyAction;
import nc.ui.so.m30.billui.rule.AssociateConstractRule;
import nc.ui.so.m30.billui.rule.MatchLargessRule;
import nc.ui.so.m30.billui.view.SaleOrderBillForm;
import nc.ui.so.m30.pub.SaleOrderCalculator;
import nc.ui.so.m30.pub.SaleOrderFindPriceConfig;
import nc.ui.so.pub.findprice.FindSalePrice;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.rule.HeadTotalCalculateRule;
import nc.vo.so.m30.util.FeatureSelectUtil;
import nc.vo.so.m30.util.SaleOrderClientContext;
import nc.vo.so.m30trantype.entity.M30TranTypeVO;
import nc.vo.so.pub.SOConstant;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.rule.SOExchangeRateRule;
import nc.vo.so.pub.rule.SOGlobalExchangeRate;
import nc.vo.so.pub.rule.SOGroupExchangeRate;
import nc.vo.so.pub.util.ArrayUtil;

/**
 * 销售订单复制动作
 * 
 * @since 6.0
 * @version 2011-7-11 下午01:06:36
 * @author fengjb
 */
public class SaleOrderCopyAction extends CopyAction {

  private static final long serialVersionUID = -1413716422828904187L;

  @Override
  public void doAction(ActionEvent e) throws Exception {
    super.doAction(e);

    SaleOrderBillForm billform = (SaleOrderBillForm) this.getEditor();
    BillCardPanel cardPanel = billform.getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);
    // 1.V63 冯加滨、陈恩宇、吴玲 销售订单复制需要关联合同
    String tranTypeCode =
        keyValue.getHeadStringValue(SaleOrderHVO.VTRANTYPECODE);
    String pk_group = AppContext.getInstance().getPkGroup();
    SaleOrderClientContext cache = billform.getM30ClientContext();
    M30TranTypeVO m30transvo = cache.getTransType(tranTypeCode, pk_group);
    AssociateConstractRule ctrule =
        new AssociateConstractRule(cardPanel, m30transvo);
    int count = keyValue.getBodyCount();
    int[] rows = new int[count];
    for (int i = 0; i < count; i++) {
      rows[i] = i;
    }
    ctrule.associateCT(rows);

    // 2.V65赠品对付的销售订单复制后清空赠品、赠品兑付字段值
    boolean isBlrgcashflag = m30transvo.getBlrgcashflag().booleanValue();
    if (isBlrgcashflag) {
      this.setBlrgcashflagByTranType(keyValue, rows);
    }

    // 重新匹配买赠，因为买赠设置此时可能被修改过，或者过了有效期，所以要重新匹配一下 jilu for 633
    MatchLargessRule matchlarrule = new MatchLargessRule(cardPanel, m30transvo);
    matchlarrule.matchLargess(rows);

    UFDouble[] oldExrate =
        this.getBodyRateValue(keyValue, SaleOrderBVO.NEXCHANGERATE);

    SaleOrderCalculator calculator = new SaleOrderCalculator(cardPanel);

    // 复制单据日期有可能改变，所以需求重新取汇率
    // 重新计算表体行折本汇率
    SOExchangeRateRule exraterule = new SOExchangeRateRule(keyValue);
    exraterule.calcAllBodyExchangeRate();

    // 全局本位币汇率
    SOGlobalExchangeRate globalraterule = new SOGlobalExchangeRate(keyValue);
    if (globalraterule.isGlobalExchgRateChange(SaleOrderHVO.CORIGCURRENCYID)) {
      globalraterule.calcGlobalExchangeRate(rows);
      calculator.calculate(rows, SaleOrderBVO.NGLOBALEXCHGRATE);
    }

    // 集团本位币汇率
    SOGroupExchangeRate groupraterule = new SOGroupExchangeRate(keyValue);
    if (groupraterule.isGroupExchgRateChange(SaleOrderHVO.CORIGCURRENCYID)) {
      groupraterule.calcGroupExchangeRate(rows);
      calculator.calculate(rows, SaleOrderBVO.NGROUPEXCHGRATE);
    }

    UFDouble[] newExrate =
        this.getBodyRateValue(keyValue, SaleOrderBVO.NEXCHANGERATE);

    int[] changeExrateRows = this.getRateChangeRow(oldExrate, newExrate);

    if (!ArrayUtil.isEmpty(changeExrateRows)) {
      calculator.calculate(changeExrateRows, SaleOrderBVO.NEXCHANGERATE);
    }

    // add by quyt for 复制是否询价 20150122
    // 3.复制时如果交易类型内“复制是否询价”勾选了，则进行询价
    UFBoolean bcopyiseeprice = m30transvo.getBcopyiseprice();
    // add by wangshu6 2015-01-28 复制是否询价是新增字段，如果为空，默认为否
    if (bcopyiseeprice == null) {
      bcopyiseeprice = UFBoolean.FALSE;
    }
    // wangshu6 end
    if (bcopyiseeprice.equals(UFBoolean.TRUE)) {
      SaleOrderFindPriceConfig config =
          new SaleOrderFindPriceConfig(cardPanel, m30transvo);
      FindSalePrice findPrice = new FindSalePrice(cardPanel, config);
      findPrice.findPriceAfterEdit(rows, SaleOrderBVO.CMATERIALVID);
    }
    // end
    // 清空缓存特征码
    FeatureSelectUtil.clearAllRowValue(keyValue, SOConstant.AGGFFILEVO);

    String cbiztypeid = keyValue.getHeadStringValue(SaleOrderHVO.CBIZTYPEID);
    if (PubAppTool.isNull(cbiztypeid)) {
      keyValue.setHeadValue(SaleOrderHVO.VTRANTYPECODE, null);
      keyValue.setHeadValue(SaleOrderHVO.CTRANTYPEID, null);
    }

    // 计算表头合计
    HeadTotalCalculateRule totalrule = new HeadTotalCalculateRule(keyValue);
    totalrule.calculateHeadTotal();
  }

  private int[] getRateChangeRow(UFDouble[] oldExrate, UFDouble[] newExrate) {
    List<Integer> changeList = new ArrayList<>();
    int rowcount = oldExrate.length;
    for (int i = 0; i < rowcount; i++) {
      if (!MathTool.equals(oldExrate[i], newExrate[i])) {
        changeList.add(Integer.valueOf(i));
      }
    }
    int[] changerow = new int[changeList.size()];
    int coursor = 0;
    for (Integer row : changeList) {
      changerow[coursor++] = row.intValue();
    }
    return changerow;
  }

  private UFDouble[] getBodyRateValue(IKeyValue keyValue, String itemkey) {
    int count = keyValue.getBodyCount();
    UFDouble[] rateValues = new UFDouble[count];
    for (int i = 0; i < count; i++) {
      rateValues[i] = keyValue.getBodyUFDoubleValue(i, itemkey);
    }
    return rateValues;
  }

  /**
   * 根据交易类型设置标题赠品兑付字段
   * 
   * @param keyValue
   * @param rows
   */
  private void setBlrgcashflagByTranType(IKeyValue keyValue, int[] rows) {
    for (int row : rows) {
      keyValue.setBodyValue(row, SaleOrderBVO.BLRGCASHFLAG, UFBoolean.FALSE);
      keyValue.setBodyValue(row, SaleOrderBVO.BLARGESSFLAG, UFBoolean.FALSE);
    }
  }
}
