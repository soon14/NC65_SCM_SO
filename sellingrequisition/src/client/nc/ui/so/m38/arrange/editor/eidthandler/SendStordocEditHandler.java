package nc.ui.so.m38.arrange.editor.eidthandler;

import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillItem;
import nc.ui.pub.bill.BillListPanel;
import nc.ui.pubapp.billref.push.PushBillEvent;
import nc.ui.scmpub.ref.FilterWareHouseRefUtils;
import nc.ui.so.pub.keyvalue.ListKeyValue;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.util.SaleOrderTranTypeUtil;
import nc.vo.so.m30trantype.enumeration.DirectType;
import nc.vo.so.pub.enumeration.ListTemplateType;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.rule.SaleOrgRelationRule;

/**
 * 预订单集中安排销售订单发货仓库编辑事件
 * 
 * @since 6.0
 * @version 2012-3-28 下午04:32:26
 * @author fengjb
 */
public class SendStordocEditHandler {

  public void beforeEdit(BillListPanel listPanel, PushBillEvent e) {
    int row = e.getEditEvent().getRow();
    IKeyValue keyValue = new ListKeyValue(listPanel, row, ListTemplateType.SUB);
    // 集团
    String pk_group = keyValue.getHeadStringValue(SaleOrderHVO.PK_GROUP);
    // 订单类型
    String ctrantypeid = keyValue.getHeadStringValue(SaleOrderHVO.CTRANTYPEID);
    // 库存组织
    String sendStockOrg =
        keyValue.getBodyStringValue(row, SaleOrderBVO.CSENDSTOCKORGID);
    BillItem billItem = listPanel.getBodyItem(SaleOrderBVO.CSENDSTORDOCID);
    FilterWareHouseRefUtils stordocFilter =
        new FilterWareHouseRefUtils((UIRefPane) billItem.getComponent());
    stordocFilter.filterItemRefByGroup(pk_group);

    if (!PubAppTool.isNull(sendStockOrg)) {
      stordocFilter.filterItemRefByOrg(sendStockOrg);
    }
    else {
      stordocFilter.filterItemRefByOrg(null);
    }

    if (!PubAppTool.isNull(ctrantypeid)) {
      int flag = new SaleOrderTranTypeUtil().getDirectType(ctrantypeid);
      if (DirectType.DIRECTTRAN_NO.getIntValue() == flag) {
        // 1.非直运：过滤掉直运仓
        stordocFilter.filterDirectStorc();
      }
      else if (DirectType.DIRECTTRAN_TO.getIntValue() == flag) {
        // 2.直运调拨：直运仓
        stordocFilter.onlyDirectStorc();
      }
      else {
        // 3.直运采购：普通仓+直运仓
        stordocFilter.filterDirectAndNotDirectStorc();
      }
    }

  }

  public void afterEdit(BillListPanel listPanel, PushBillEvent e) {

    int row = e.getEditEvent().getRow();
    int[] rows = new int[] {
      row
    };
    IKeyValue keyValue = new ListKeyValue(listPanel, row, ListTemplateType.SUB);

    SaleOrgRelationRule relarule = new SaleOrgRelationRule(keyValue);
    // 设置物流组织
    relarule.setTrafficOrg(rows);
  }
}
