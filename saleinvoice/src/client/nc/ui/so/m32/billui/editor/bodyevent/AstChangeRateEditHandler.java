package nc.ui.so.m32.billui.editor.bodyevent;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.vo.so.m32.rule.UnitChangeRateRule;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>换算率编辑事件
 * </ul>
 * <p>
 * 
 * @version 本版本号 6.0
 * @since 上一版本号 5.6
 * @author 冯加滨
 * @time 2010-4-26 下午06:05:40
 */
public class AstChangeRateEditHandler {
  /**
   * 方法功能描述：单位和主单位换算率编辑前事件。
   * <p>
   * <b>参数说明</b>
   * 
   * @param e
   *          <p>
   * @author 冯加滨
   * @time 2010-4-26 下午06:08:51
   */
  public void beforeEdit(CardBodyBeforeEditEvent e) {

    BillCardPanel cardPanel = e.getBillCardPanel();
    CardKeyValue keyValue = new CardKeyValue(cardPanel);
    UnitChangeRateRule changraterule = new UnitChangeRateRule(keyValue);
    // 根据是否固定换算率设置编辑性
    boolean value = changraterule.isAstFixedRate(e.getRow());
    e.setReturnValue(Boolean.valueOf(!value));
  }
}
