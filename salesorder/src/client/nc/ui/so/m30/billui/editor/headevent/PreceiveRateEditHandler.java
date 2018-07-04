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
import nc.vo.so.pub.SOConstant;
import nc.vo.so.pub.keyvalue.IKeyValue;

/**
 * 预收款比率编辑事件
 * 
 * @since 6.0
 * @version 2011-6-9 上午09:44:24
 * @author fengjb
 */
public class PreceiveRateEditHandler {

  public void afterEdit(CardHeadTailAfterEditEvent e) {
    BillCardPanel cardPanel = e.getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);

    // 预收款数据合法性检查
    UFDouble rate = keyValue.getHeadUFDoubleValue(SaleOrderHVO.NPRECEIVERATE);
    if (MathTool.compareTo(rate, UFDouble.ZERO_DBL) < 0
        || MathTool.compareTo(rate, SOConstant.ONEHUNDRED) > 0) {
      // fengjb 2012.03.05 UE提示规范
      ExceptionUtils.wrappBusinessException(NCLangRes.getInstance().getStrByID(
          "4006011_0", "04006011-0255")/*预收款比例值在0.0~100.0之间*/);
    }

    // 根据收款比例和价税合计,计算收款限额
    PreceiveQuotaRule prequotarule = new PreceiveQuotaRule(keyValue);
    prequotarule.calculatePreceiveQuoTa();
  }

}
