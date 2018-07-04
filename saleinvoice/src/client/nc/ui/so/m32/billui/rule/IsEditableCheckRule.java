package nc.ui.so.m32.billui.rule;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.vo.so.pub.keyvalue.AbstractKeyValue.RowStatus;


/**
 * @description
 * 销售发票修订字段是否能编辑检查规则
 * 
 * @since 6.36
 * @version 2015-1-23 上午10:36:00
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
    boolean isRevise = false;
    if (row > -1) {
      isRevise = cardPanel.getBodyItem(itemKey).isM_bReviseFlag();
    }
    else {
      isRevise = cardPanel.getHeadTailItem(itemKey).isM_bReviseFlag();
    }
    if (!isRevise) {
      return isRevise;
    }
    // 4.根据字段是否包含在可编辑字段中判断
    isEditable = this.checkIsEditable(itemKey);

    // if (isEditable) {
    // // 规则2
    // }
    // if (isEditable) {
    // // 规则3
    // }
    return isEditable;
  }


  /**
   * 未生成下游发货单和出库单的单据，判断字段是否可修订
   * 
   * @param itemKey
   * @return
   */

  private boolean checkIsEditable(String itemKey) {
    int bodylength = EditableAndRewiteItems.BODYEDITABLEITEMKEY.length;
    int headlengh = EditableAndRewiteItems.HEADEDITABLEITEMKEY.length;
    for (int i = 0; i < bodylength; i++) {
      if (i < headlengh) {
        if (EditableAndRewiteItems.HEADEDITABLEITEMKEY[i].equals(itemKey)) {
          return true;
        }
      }
      if (EditableAndRewiteItems.BODYEDITABLEITEMKEY[i].equals(itemKey)) {
        return true;
      }
    }
    return false;
  }


}
