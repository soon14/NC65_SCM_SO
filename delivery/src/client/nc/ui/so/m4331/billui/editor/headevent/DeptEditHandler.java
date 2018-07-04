package nc.ui.so.m4331.billui.editor.headevent;

import nc.vo.so.m4331.entity.DeliveryHVO;

import nc.ui.bd.ref.RefInitializeCondition;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailBeforeEditEvent;
import nc.ui.scmpub.ref.FilterDeptRefUtils;
import nc.ui.so.pub.keyvalue.CardKeyValue;

/**
 * 部门编辑事件
 * 
 * @since 6.0
 * @version 2011-3-24 上午11:34:44
 * @author 祝会征
 */
public class DeptEditHandler {

  /**
   * 
   * 部门编辑前
   * 
   * @param e
   */
  public void beforeEdit(CardHeadTailBeforeEditEvent e) {
    BillCardPanel cardPanel = e.getBillCardPanel();
    CardKeyValue keyValue = new CardKeyValue(cardPanel);
    String pk_org = keyValue.getHeadStringValue(DeliveryHVO.PK_ORG);
    BillItem item = cardPanel.getHeadItem(e.getKey());
    UIRefPane panel = (UIRefPane) item.getComponent();
    FilterDeptRefUtils filter = FilterDeptRefUtils
    .createFilterDeptRefUtilsOfTR(panel);
    filter.filterItemRefByOrg(pk_org);
  }

}
