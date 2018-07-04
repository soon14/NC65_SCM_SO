package nc.ui.so.m38.arrange.editor.eidthandler;

import nc.ui.pub.bill.BillListPanel;
import nc.ui.pubapp.billref.push.PushBillEvent;
import nc.ui.so.pub.keyvalue.ListKeyValue;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.pub.enumeration.ListTemplateType;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.rule.SOUnitChangeRateRule;

public class ChangeRateEditHanlder {

  public void beforeEdit(BillListPanel listPanel, PushBillEvent e) {

    int row = e.getEditEvent().getRow();
    IKeyValue keyvalue = new ListKeyValue(listPanel, row, ListTemplateType.SUB);
    String cunitid = keyvalue.getBodyStringValue(row, SaleOrderBVO.CUNITID);
    String castunitid =
        keyvalue.getBodyStringValue(row, SaleOrderBVO.CASTUNITID);
    if (PubAppTool.isNull(cunitid) || PubAppTool.isNull(castunitid)) {
      e.setEditable(false);
      return;
    }
    SOUnitChangeRateRule changeraterule = new SOUnitChangeRateRule(keyvalue);
    boolean isfix = changeraterule.isAstFixedRate(row);
    e.setEditable(!isfix);
  }

}
