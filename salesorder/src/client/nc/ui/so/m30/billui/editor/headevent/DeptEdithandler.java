package nc.ui.so.m30.billui.editor.headevent;

import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailBeforeEditEvent;
import nc.ui.scmpub.ref.FilterDeptRefUtils;
import nc.ui.scmpub.ref.FilterPsndocRefUtils;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.pub.keyvalue.IKeyValue;


/**
 * 部门编辑事件
 * 
 * @since 6.36
 * @version 2015-2-6 下午2:18:12
 * @author 刘景
 */
public class DeptEdithandler {
  
  /**
   * 部门编辑前事件
   * 
   * @param e
   */
  public void beforeEdit(CardHeadTailBeforeEditEvent e) {

    BillCardPanel cardPanel = e.getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);
    String pk_org = keyValue.getHeadStringValue(SaleOrderHVO.PK_ORG);

    BillItem item = cardPanel.getHeadItem(SaleOrderHVO.CDEPTVID);
    UIRefPane refPane = (UIRefPane) item.getComponent();
    FilterDeptRefUtils  filter = FilterDeptRefUtils
    .createFilterDeptRefUtilsOfSO(refPane);
    filter.filterItemRefByOrg(pk_org);
  }

}
