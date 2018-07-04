package nc.ui.so.m38.arrange.editor.eidthandler;

import nc.ui.pub.bill.BillListPanel;
import nc.ui.pubapp.billref.push.PushBillEvent;
import nc.ui.so.pub.keyvalue.ListKeyValue;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.pub.enumeration.ListTemplateType;
import nc.vo.so.pub.keyvalue.IKeyValue;

public class ExchangerateEditHandler {

  public void beforeEdit(BillListPanel listPanel, PushBillEvent e) {

    int row = e.getEditEvent().getRow();
    IKeyValue keyValue = new ListKeyValue(listPanel, row, ListTemplateType.SUB);
    // 原币币种
    String localcurid =
        keyValue.getHeadStringValue(SaleOrderHVO.CORIGCURRENCYID);
    // 组织本位币
    String orgcurid =
        keyValue.getBodyStringValue(row, SaleOrderBVO.CCURRENCYID);

    // 没有原币或者组织本位币
    if (PubAppTool.isNull(localcurid) || PubAppTool.isNull(orgcurid)
        || localcurid.equals(orgcurid)) {
      e.setEditable(false);
      return;
    }
    e.setEditable(true);
  }

}
