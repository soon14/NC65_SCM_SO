package nc.ui.so.m4331.billui.editor.bodyevent;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m4331.entity.DeliveryBVO;
import nc.vo.so.m4331.entity.DeliveryHVO;
import nc.vo.so.pub.keyvalue.AbstractKeyValue.RowStatus;
import nc.vo.so.pub.rule.BodyValueRowRule;

/**
 * 表体合计处理类
 * 
 * @author zhangby5
 * 
 */
public class HeadTotalCalEditHandler {

  public void afterEdit(CardBodyAfterEditEvent e) {
    BillCardPanel cardPanel = e.getBillCardPanel();
    CardKeyValue keyValue = new CardKeyValue(cardPanel);
    BodyValueRowRule countutil = new BodyValueRowRule(keyValue);
    int[] rows = countutil.getMarNotNullRows();

    // 合计重量
    UFDouble ntotalweight = UFDouble.ZERO_DBL;
    // 合计体积
    UFDouble ntotalvolume = UFDouble.ZERO_DBL;
    // 合计件数
    UFDouble ntotalpiece = UFDouble.ZERO_DBL;
    for (int row : rows) {
      if (RowStatus.DELETED == keyValue.getRowStatus(row)) {
        continue;
      }
      UFDouble nweight =
          keyValue.getBodyUFDoubleValue(row, DeliveryBVO.NWEIGHT);
      ntotalweight = MathTool.add(ntotalweight, nweight);

      UFDouble nvolume =
          keyValue.getBodyUFDoubleValue(row, DeliveryBVO.NVOLUME);
      ntotalvolume = MathTool.add(ntotalvolume, nvolume);

      UFDouble npiece = keyValue.getBodyUFDoubleValue(row, DeliveryBVO.NPIECE);
      ntotalpiece = MathTool.add(ntotalpiece, npiece);
    }

    keyValue.setHeadValue(DeliveryHVO.NTOTALWEIGHT, ntotalweight);
    keyValue.setHeadValue(DeliveryHVO.NTOTALVOLUME, ntotalvolume);
    keyValue.setHeadValue(DeliveryHVO.NTOTALPIECE, ntotalpiece);
  }
}
