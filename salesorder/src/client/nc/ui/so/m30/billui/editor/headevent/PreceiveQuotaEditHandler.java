package nc.ui.so.m30.billui.editor.headevent;

import nc.ui.ml.NCLangRes;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.rule.PreceiveQuotaRule;
import nc.vo.so.pub.keyvalue.IKeyValue;

public class PreceiveQuotaEditHandler {

  public void afterEdit(CardHeadTailAfterEditEvent e) {
    BillCardPanel cardPanel = e.getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);

    // 收款限额数据合法性检查
    UFDouble quota = keyValue.getHeadUFDoubleValue(SaleOrderHVO.NPRECEIVEQUOTA);
    UFDouble totalorigmny =
        keyValue.getHeadUFDoubleValue(SaleOrderHVO.NTOTALORIGMNY);
    if (MathTool.absCompareTo(quota, totalorigmny) > 0) {
      // fengjb 2012.03.05 UE提示规范
      ExceptionUtils.wrappBusinessException(NCLangRes.getInstance().getStrByID(
          "4006011_0", "04006011-0254")/*订单收款限额不能大于价税合计*/);
    }
    // 根据收款限额和价税合计,计算收款比例
    PreceiveQuotaRule prequotarule = new PreceiveQuotaRule(keyValue);
    prequotarule.calculatePreceiveRate();
  }
}
