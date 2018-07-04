package nc.ui.so.m38.arrange.editor.eidthandler;

import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillItem;
import nc.ui.pub.bill.BillListPanel;
import nc.ui.pubapp.billref.push.PushBillEvent;
import nc.ui.scmpub.ref.FilterDeptRefUtils;
import nc.ui.so.pub.keyvalue.ListKeyValue;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.pub.enumeration.ListTemplateType;
import nc.vo.so.pub.keyvalue.IKeyValue;

/**
 * 预订单集中安排销售订单部门编辑事件
 * 
 * @since 6.0
 * @version 2012-3-28 下午04:05:50
 * @author fengjb
 */
public class DeptEditHandler {

  public void beforeEdit(BillListPanel listPanel, PushBillEvent e) {

    int row = e.getEditEvent().getRow();
    IKeyValue keyValue = new ListKeyValue(listPanel, row, ListTemplateType.SUB);
    String pk_org = keyValue.getHeadStringValue(SaleOrderHVO.PK_ORG);
    BillItem deptitem = listPanel.getBodyItem(SaleOrderHVO.CDEPTVID);
    FilterDeptRefUtils utils =
        new FilterDeptRefUtils((UIRefPane) deptitem.getComponent());
    utils.filterItemRefByOrg(pk_org);
  }
}
