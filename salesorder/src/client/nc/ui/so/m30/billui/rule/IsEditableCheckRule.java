package nc.ui.so.m30.billui.rule;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.vo.so.pub.keyvalue.AbstractKeyValue.RowStatus;

/**
 * @description
 *  销售订单审批中字段是否能编辑检查规则
 * @scene
 *  销售订单审批中支持修改 编辑前规则
 * @param
 * 
 *
 * @since 6.3
 * @version 2015-1-9 下午3:45:49
 * @author wangshu6
 */
public class IsEditableCheckRule {

  private CardKeyValue keyValue;

  /**
   * @param BillCardPanel
   * @param row 表头传-1
   * @param key 当前编辑itemKey
   */
  public boolean check(BillCardPanel cardPanel, int row, String itemKey) {
    this.keyValue = new CardKeyValue(cardPanel);

    // 1.新增行随便改
    RowStatus rowStatus = this.keyValue.getRowStatus(row);
    if (RowStatus.NEW == rowStatus) {
      return true;
    }
    // 2.修改行
    boolean isEditable = false;
    // 3.根据字段是否可修订判断
    if (row > -1) {
      isEditable = cardPanel.getBodyItem(itemKey).isM_bReviseFlag();
    }
    else {
      isEditable = cardPanel.getHeadTailItem(itemKey).isM_bReviseFlag();
    }
    return isEditable;
  }
}
