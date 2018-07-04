package nc.ui.so.m38.billui.editor.bodyevent;

import java.util.ArrayList;
import java.util.List;

import nc.ui.ml.NCLangRes;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.ui.so.m38.billui.pub.PreOrderCalculator;
import nc.ui.so.m38.billui.pub.PreOrderFindPriceConfig;
import nc.ui.so.pub.findprice.FindSalePrice;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.ui.so.pub.util.BodyEditEventUtil;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m38.entity.PreOrderBVO;
import nc.vo.so.m38.entity.PreOrderHVO;
import nc.vo.so.pub.enumeration.BillStatus;
import nc.vo.so.pub.keyvalue.IKeyValue;

/**
 * 报价单位数量编辑事件
 * 
 * @since 6.0
 * @version 2011-6-14 上午09:58:28
 * @author fengjb
 */
public class QtUnitNumEditHandler {

  public void afterEdit(CardBodyAfterEditEvent e) {

    int[] rows = BodyEditEventUtil.getInstance().getAfterEditRow(e);
    BillCardPanel cardPanel = e.getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);
    Integer fstatusflag = keyValue.getHeadIntegerValue(PreOrderHVO.FSTATUSFLAG);
    // 旧的主数量
    UFDouble[] old_nums = new UFDouble[rows.length];
    for (int i = 0; i < rows.length; i++) {
      old_nums[i] = keyValue.getBodyUFDoubleValue(rows[i], PreOrderBVO.NNUM);
    }
    // 1.计算数量
    PreOrderCalculator calcultor = new PreOrderCalculator(cardPanel);
    calcultor.calculate(rows, PreOrderBVO.NQTUNITNUM);

    List<Integer> listchgrow = new ArrayList<Integer>();

    // 修订时数量改小的行
    List<String> listerrorrow = new ArrayList<String>();
    if (BillStatus.AUDIT.equalsValue(fstatusflag)) {
      for (int i = 0; i < rows.length; i++) {
        UFDouble new_num =
            keyValue.getBodyUFDoubleValue(rows[i], PreOrderBVO.NNUM);
        UFDouble use_num =
            keyValue.getBodyUFDoubleValue(rows[i], PreOrderBVO.NARRNUM);
        if (MathTool.lessThan(new_num, use_num)) {
          keyValue.setBodyValue(rows[i], PreOrderBVO.NNUM, old_nums[i]);
          calcultor.calculate(rows, PreOrderBVO.NNUM);
          String rowno =
              keyValue.getBodyStringValue(rows[i], PreOrderBVO.CROWNO);
          listerrorrow.add(rowno);
          continue;
        }
        if (!MathTool.equals(old_nums[i], new_num)) {
          listchgrow.add(rows[i]);
        }
      }
    }
    else {
      for (int i = 0; i < rows.length; i++) {
        UFDouble new_num =
            keyValue.getBodyUFDoubleValue(rows[i], PreOrderBVO.NNUM);
        if (!MathTool.equals(old_nums[i], new_num)) {
          listchgrow.add(rows[i]);
        }
      }
    }

    // 主数量未变化
    int chgsize = listchgrow.size();
    if (chgsize == 0 && listerrorrow.size() == 0) {
      return;
    }

    int[] chgrows = new int[chgsize];
    for (int i = 0; i < chgsize; i++) {
      chgrows[i] = listchgrow.get(i);
    }
    PreOrderFindPriceConfig config = new PreOrderFindPriceConfig(cardPanel);
    FindSalePrice findprice = new FindSalePrice(cardPanel, config);
    findprice.findPriceAfterEdit(rows, PreOrderBVO.NQTUNITNUM);

    if (listerrorrow.size() > 0) {
      StringBuilder errMsg =
          new StringBuilder(NCLangRes.getInstance().getStrByID("4006012_0",
              "04006012-000000")/*预订单下列行:*/);

      for (String field : listerrorrow) {
        errMsg
            .append("[")
            .append(field)
            .append("]")
            .append(
                NCLangRes.getInstance().getStrByID("4006012_0",
                    "04006012-000001")/*、*/);
      }

      errMsg.deleteCharAt(errMsg.length() - 1);
      errMsg.append(NCLangRes.getInstance().getStrByID("4006012_0",
          "04006012-000002")/*数量不能比安排数量小*/);
      ExceptionUtils.wrappBusinessException(errMsg.toString());
    }
  }

}
