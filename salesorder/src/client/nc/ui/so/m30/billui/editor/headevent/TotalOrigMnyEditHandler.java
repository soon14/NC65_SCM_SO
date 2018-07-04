package nc.ui.so.m30.billui.editor.headevent;

import java.util.ArrayList;
import java.util.List;

import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.pub.SOConstant;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.rule.BodyUpdateByHead;
import nc.vo.so.pub.rule.BodyValueRowRule;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent;
import nc.ui.so.m30.pub.SaleOrderCalculator;
import nc.ui.so.pub.keyvalue.CardKeyValue;

/**
 * 表头价税合计编辑事件
 * 
 * @since 6.3
 * @version 2013-03-14 10:16:10
 * @author yixl
 */
public class TotalOrigMnyEditHandler {

  /**
   * 编辑后事件
   * 
   * @param e
   */
  public void afterEdit(CardHeadTailAfterEditEvent e) {

    BillCardPanel cardPanel = e.getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);

    UFDouble newtotalmny =
        keyValue.getHeadUFDoubleValue(SaleOrderHVO.NTOTALORIGMNY);
    // 1.计算新整单折扣
    List<Integer> listchgrow = this.calculateNewDiscountRate(keyValue);

    // 2.更新表体整单折扣
    BodyUpdateByHead updaterule = new BodyUpdateByHead(keyValue);
    updaterule.updateAllBodyValueByHead(SaleOrderBVO.NDISCOUNTRATE,
        SaleOrderHVO.NDISCOUNTRATE);

    // 3.数量单价金额运算
    SaleOrderCalculator calculator = new SaleOrderCalculator(cardPanel);
    BodyValueRowRule couutil = new BodyValueRowRule(keyValue);
    int[] rows = couutil.getMarNotNullRows();
    calculator.calculate(rows, SaleOrderHVO.NDISCOUNTRATE);

    // 4.处理尾差
    this.processMnyAdjust(newtotalmny, keyValue, listchgrow, calculator);

  }

  private void processMnyAdjust(UFDouble newtotalmny, IKeyValue keyValue,
      List<Integer> listchgrow, SaleOrderCalculator calculator) {
    UFDouble curtotal = UFDouble.ZERO_DBL;
    if (listchgrow.size() > 0) {
      int size = listchgrow.size() - 1;
      for (int i = 0; i < size; i++) {
        UFDouble origmny =
            keyValue.getBodyUFDoubleValue(listchgrow.get(i).intValue(),
                SaleOrderBVO.NORIGTAXMNY);
        curtotal = MathTool.add(curtotal, origmny);
      }
      UFDouble adjustmny = MathTool.sub(newtotalmny, curtotal);
      int lastrow = listchgrow.get(size).intValue();
      keyValue.setBodyValue(lastrow, SaleOrderBVO.NORIGTAXMNY, adjustmny);
      calculator.setChangePrice(UFBoolean.TRUE);
      calculator.calculate(lastrow, SaleOrderBVO.NORIGTAXMNY);
    }
  }

  private List<Integer> calculateNewDiscountRate(IKeyValue keyValue) {
    // 原价扣除单品折扣后的金额=报价数量×报价含税单价×单品折扣
    UFDouble bodysumdismny = UFDouble.ZERO_DBL;
    BodyValueRowRule countutil = new BodyValueRowRule(keyValue);
    int[] rows = countutil.getMarNotNullRows();

    List<Integer> listchgrow = new ArrayList<Integer>();
    for (int row : rows) {
      UFBoolean largessflag =
          keyValue.getBodyUFBooleanValue(row, SaleOrderBVO.BLARGESSFLAG);
      if (null != largessflag && largessflag.booleanValue()) {
        continue;
      }
      UFDouble nqtunitnum =
          keyValue.getBodyUFDoubleValue(row, SaleOrderBVO.NQTUNITNUM);
      if (null == nqtunitnum) {
        nqtunitnum = UFDouble.ZERO_DBL;
      }

      UFDouble nqtorigtaxprice =
          keyValue.getBodyUFDoubleValue(row, SaleOrderBVO.NQTORIGTAXPRICE);
      if (null == nqtorigtaxprice) {
        nqtorigtaxprice = UFDouble.ZERO_DBL;
      }

      UFDouble nitemdiscountrate =
          keyValue.getBodyUFDoubleValue(row, SaleOrderBVO.NITEMDISCOUNTRATE);
      if (null == nitemdiscountrate) {
        nitemdiscountrate = UFDouble.ONE_DBL;
      }
      else {
        nitemdiscountrate = nitemdiscountrate.div(SOConstant.ONEHUNDRED);
      }
      UFDouble tempItemDiscountmny =
          nqtunitnum.multiply(nqtorigtaxprice).multiply(nitemdiscountrate);
      bodysumdismny = MathTool.add(bodysumdismny, tempItemDiscountmny);

      listchgrow.add(Integer.valueOf(row));
    }

    // 整单折扣的计算公式s为：表头整单折扣=表头修改后总金额/Σ（原价扣除单品折扣后的金额）
    UFDouble ntotalorigmny =
        keyValue.getHeadUFDoubleValue(SaleOrderHVO.NTOTALORIGMNY);
    if (null == ntotalorigmny) {
      ntotalorigmny = UFDouble.ZERO_DBL;
    }

    if (UFDouble.ZERO_DBL.compareTo(bodysumdismny) != 0) {
      UFDouble newdiscountrate = ntotalorigmny.div(bodysumdismny).multiply(100);
      keyValue.setHeadValue(SaleOrderHVO.NDISCOUNTRATE, newdiscountrate);
    }

    return listchgrow;
  }
}
