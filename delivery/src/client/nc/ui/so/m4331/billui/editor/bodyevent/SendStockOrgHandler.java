package nc.ui.so.m4331.billui.editor.bodyevent;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nc.itf.scmpub.reference.uap.org.OrgUnitPubService;
import nc.itf.scmpub.reference.uap.org.SaleOrgPubService;
import nc.ui.bd.ref.AbstractRefModel;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.so.m4331.billui.pub.rule.CheckCountryChangeRule;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.ui.so.pub.util.BodyEditEventUtil;
import nc.vo.scmpub.res.billtype.TOBillType;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m4331.entity.DeliveryBVO;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.rule.SOProfitCenterValueRule;

public class SendStockOrgHandler {
  /**
   * 方法功能描述：发货单表体发货库存组织编辑后事件
   * 
   * @author 祝会征
   * @time 2010-6-7 下午03:42:00
   */
  public void afterEdit(CardBodyAfterEditEvent e) {
    IKeyValue keyValue = new CardKeyValue(e.getBillCardPanel());
    int[] rows = BodyEditEventUtil.getInstance().getAfterEditRow(e);
    if (e.getValue() == null) {
      e.getBillCardPanel().getBodyItem(DeliveryBVO.CSENDSTORDOCID)
      .setEdit(false);
      e.getBillCardPanel().setBodyValueAt(null, e.getRow(),
          DeliveryBVO.CSENDSTORDOCID);
    }
    else if (!e.getValue().equals(e.getOldValue())) {
      e.getBillCardPanel().getBodyItem(DeliveryBVO.CSENDSTORDOCID)
      .setEdit(true);
      e.getBillCardPanel().setBodyValueAt(null, e.getRow(),
          DeliveryBVO.CSENDSTORDOCID);
    }
    CheckCountryChangeRule rule = new CheckCountryChangeRule(keyValue);
    rule.checkSendAndTaxCountry(rows, DeliveryBVO.CSENDSTOCKORGVID,
        new String[] {
        (String) e.getOldValue()
    });
    // add by zhangby5 for 利润中心取值规则
    SOProfitCenterValueRule profitRule = new SOProfitCenterValueRule(keyValue);
    profitRule.setProfitCenterValue(DeliveryBVO.CSPROFITCENTERVID,
        DeliveryBVO.CSPROFITCENTERID, rows);
  }

  /**
   * 方法功能描述：发货单表体发货库存组织编辑前事件
   * 
   * @author 祝会征
   * @time 2010-6-7 下午03:42:00
   */
  public void beforeEdit(CardBodyBeforeEditEvent e) {

    int row = e.getRow();
    BillCardPanel cardPanel = e.getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);
    String vsrctype = keyValue.getBodyStringValue(row, DeliveryBVO.VSRCTYPE);
    if (vsrctype != null
			&& TOBillType.TransOrder.isEqual((String) vsrctype)) {
    	e.setReturnValue(false);
    }
    String cmaterialid =
        keyValue.getBodyStringValue(row, DeliveryBVO.CMATERIALID);
    // 发货库存组织VIDs
    String[] csendstockorgvids = null;
    // try {
    String sale_org = keyValue.getBodyStringValue(row, DeliveryBVO.CSALEORGID);
    String[] csendstockorgids =
        SaleOrgPubService.getStockOrgIDSBySaleOrgIDAndMaterialID(sale_org,
            cmaterialid);
    csendstockorgvids = this.getSendStockOrgVIDs(csendstockorgids);

    BillItem sendStockOrgItem =
        cardPanel.getBodyItem(SaleOrderBVO.CSENDSTOCKORGVID);
    UIRefPane sendStockOrgRefPane = (UIRefPane) sendStockOrgItem.getComponent();
    AbstractRefModel model = sendStockOrgRefPane.getRefModel();
    if (null != csendstockorgvids) {
      model.setFilterPks(csendstockorgvids);
    }
    else {
      model.setFilterPks(new String[0]);
    }
  }

  private String[] getSendStockOrgVIDs(String[] csendstockorgids) {
    String[] csendstockorgvids = null;
    if (null == csendstockorgids || csendstockorgids.length == 0) {
      return csendstockorgvids;
    }

    // 转VID
    Map<String, String> hmSendStockOrgIDs =
        OrgUnitPubService.getNewVIDSByOrgIDS(csendstockorgids);

    if (hmSendStockOrgIDs != null) {
      List<String> alSendStockOrgVIDs = new ArrayList<String>();
      for (String value : hmSendStockOrgIDs.values()) {
        if (value != null && value.length() > 0) {
          alSendStockOrgVIDs.add(value);
        }
      }
      if (alSendStockOrgVIDs.size() > 0) {
        csendstockorgvids = alSendStockOrgVIDs.toArray(new String[0]);
      }
    }
    return csendstockorgvids;
  }
}
