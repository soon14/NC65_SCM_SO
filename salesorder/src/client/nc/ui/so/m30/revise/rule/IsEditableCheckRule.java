package nc.ui.so.m30.revise.rule;

import nc.bs.framework.common.NCLocator;
import nc.itf.so.m30trantype.IM30TranTypeService;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.rule.EditableAndRewiteItems;
import nc.vo.so.m30trantype.entity.M30TranTypeVO;
import nc.vo.so.pub.keyvalue.AbstractKeyValue.RowStatus;

/**
 * 销售订单修订字段是否能编辑检查规则
 * 
 * @since 6.0
 * @version 2011-3-8 上午10:38:44
 * @author 刘志伟
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
    // 2.修订行
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
    
    // if (isEditable) {
    // // 规则2
    // }
    // if (isEditable) {
    // // 规则3
    // }
    //根据鞍钢李鸣要求 都可以编辑2018-04-02
    return true;
  }

  /**
   * 是否属于修定的可以编辑字段
   * 
   * @param itemKey
   * @param row
   * @return
   */
  private boolean isEditableItem(String itemKey, int row) {
    if (isOut(itemKey, row)) {
      return checkIsEditableForOut(itemKey, row);
    }
    else {
      return checkIsEditable(itemKey);
    }
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

  /**
   * 已生成下游发货单和出库单的单据，判断字段是否可修订
   * 
   * @param itemKey
   * @return
   */
  private boolean checkIsEditableForOut(String itemKey, int row) {
    boolean isEditable = false;

    // 修订单价时，根据销售订单的交易类型属性决定是否可以编辑。
    int priceLength = EditableAndRewiteItems.PRICE.length;
    for (int i = 0; i < priceLength; i++) {
      String key = EditableAndRewiteItems.PRICE[i];
      if (key.equals(itemKey)) {
        return checkPriceIsEditable();
      }
    }
    int reviseForOutLength =
        EditableAndRewiteItems.EDITABLEITEMKEYFOROUT.length;
    for (int i = 0; i < reviseForOutLength; i++) {
      String key = EditableAndRewiteItems.EDITABLEITEMKEYFOROUT[i];
      if (key.equals(itemKey)) {
        isEditable = true;
      }
    }
    return isEditable;
  }

  /**
   * 修订单价时，根据销售订单的交易类型属性决定是否可以编辑。
   * 根据未询到价格是否可改、询到价格是否可改进行判断
   * 
   * @return
   */
  private boolean checkPriceIsEditable() {
    // 　获取交易类型vo
    M30TranTypeVO vo = queryM30TrantypeVO();
    if (vo != null) {
      // 询到价格是否可改
      if (UFBoolean.TRUE == vo.getBmodifyaskedqt()) {
        return true;
      }
      // 未询到价格是否可改
      if (UFBoolean.TRUE == vo.getBmodifyunaskedqt()) {
        return true;
      }
    }
    return false;
  }

  /**
   * 查询销售订单交易类型vo
   * 
   * @return
   */
  private M30TranTypeVO queryM30TrantypeVO() {

    M30TranTypeVO trantype = null;
    IM30TranTypeService service =
        NCLocator.getInstance().lookup(IM30TranTypeService.class);
    String ctrantypeid =
        this.keyValue.getHeadStringValue(SaleOrderHVO.CTRANTYPEID);
    try {
      trantype = service.queryTranTypeVO(ctrantypeid);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return trantype;
  }

  /**
   * 是否已经生成下有单据
   * 
   * @param itemKey
   * @param row
   * @return
   */
  private boolean isOut(String itemKey, int row) {
    UFDouble value = null;
    int reviseForOutlength = EditableAndRewiteItems.TOTALNUMKEY.length;
    for (int i = 0; i < reviseForOutlength; i++) {
      String key = EditableAndRewiteItems.TOTALNUMKEY[i];
      value = this.keyValue.getBodyUFDoubleValue(row, key);
      if (MathTool.absCompareTo(value, UFDouble.ZERO_DBL) > 0) {
        // 已经生成下游单据
        return true;
      }
    }
    return false;
  }

}
