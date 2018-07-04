package nc.ui.so.m4331.billui.editor.bodyevent;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.scmpub.ref.FilterStockOrgRefUtils;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.ui.so.pub.util.BodyEditEventUtil;
import nc.vo.scmpub.res.billtype.TOBillType;
import nc.vo.so.m4331.entity.DeliveryBVO;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.rule.SOProfitCenterValueRule;

public class InstockHandler {
  /**
   * 方法功能描述：发货单表体收货库存组织编辑后事件
   * 
   * @author 祝会征
   * @time 2010-6-7 下午03:42:00
   */
  public void afterEdit(CardBodyAfterEditEvent e) {
    IKeyValue keyValue = new CardKeyValue(e.getBillCardPanel());
    int[] rows = BodyEditEventUtil.getInstance().getAfterEditRow(e);
    if (e.getValue() == null) {
      e.getBillCardPanel().getBodyItem(DeliveryBVO.CINSTORDOCID).setEdit(false);
      e.getBillCardPanel().setBodyValueAt(null, e.getRow(),
          DeliveryBVO.CINSTORDOCID);
    }
    else if (!e.getValue().equals(e.getOldValue())) {
      e.getBillCardPanel().getBodyItem(DeliveryBVO.CINSTORDOCID).setEdit(true);
      e.getBillCardPanel().setBodyValueAt(null, e.getRow(),
          DeliveryBVO.CINSTORDOCID);
    }
    // add by zhangby5 for 利润中心取值规则
    SOProfitCenterValueRule profitRule =
        new SOProfitCenterValueRule(keyValue, DeliveryBVO.CINSTORDOCID,
            DeliveryBVO.CINSTOCKORGID);
    profitRule.setProfitCenterValue(DeliveryBVO.CRPROFITCENTERVID,
        DeliveryBVO.CRPROFITCENTERID, rows);
  }

  /**
   * 方法功能描述：发货单表体收货库存组织编辑前事件
   * 
   * @author 祝会征
   * @time 2010-6-7 下午03:42:00
   */
  public void beforeEdit(CardBodyBeforeEditEvent e) {
    BillCardPanel cardPanel = e.getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);
    // 收货库存组织
    String vsrctype = keyValue.getBodyStringValue(e.getRow(), DeliveryBVO.VSRCTYPE);
    if (vsrctype != null
			&& TOBillType.TransOrder.isEqual((String) vsrctype)) {
    	e.setReturnValue(false);
    }
    BillItem cinstockorgid = cardPanel.getBodyItem(DeliveryBVO.CINSTOCKORGVID);
    FilterStockOrgRefUtils filter = new FilterStockOrgRefUtils(cinstockorgid);
    filter.setOrg(e.getContext().getPk_org());
  }
}
