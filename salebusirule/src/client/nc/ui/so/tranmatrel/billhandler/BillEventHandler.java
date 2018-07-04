package nc.ui.so.tranmatrel.billhandler;

import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pub.bill.IBillItem;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailBeforeEditEvent;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.tranmatrel.entity.TranMatRelHVO;
import nc.vo.uif2.LoginContext;

@SuppressWarnings("unused")
public class BillEventHandler {

  public void handleEditEvent(CardBodyAfterEditEvent e) {
    // String key = e.getKey();
    // if (key.equals(SaleOrderBVO.CMATERIALVID)) {
    // materialChanged(e);
    // }

  }

  public void handleEditEvent(CardBodyBeforeEditEvent e) {
    BillItem billitem = e.getBillCardPanel().getBodyItem(e.getKey());
    this.setOrgForRef(e.getBillCardPanel(), e.getContext(), billitem);

  }

  public void handleEditEvent(CardHeadTailAfterEditEvent e) {
    // String key = e.getKey();
    // if (key.equals(SaleOrderHVO.CCUSTOMERID)) {
    // customerChanged(e);
    // }

  }

  public void handleEditEvent(CardHeadTailBeforeEditEvent e) {
    BillItem billitem = e.getBillCardPanel().getHeadTailItem(e.getKey());
    this.setOrgForRef(e.getBillCardPanel(), e.getContext(), billitem);

  }

  private void setOrgForRef(BillCardPanel billcardpanel,
      LoginContext logincontext, BillItem billitem) {

    if (billitem.getDataType() == IBillItem.UFREF) {
      UIRefPane refPanel = (UIRefPane) billitem.getComponent();
      if (refPanel.getRefModel() != null) {
        IKeyValue keyvalue = new CardKeyValue(billcardpanel);
        String csaleorgid = keyvalue.getHeadStringValue(TranMatRelHVO.PK_ORG);
        refPanel.getRefModel().setPk_org(csaleorgid);
        String cgroup = keyvalue.getHeadStringValue(TranMatRelHVO.PK_GROUP);
        refPanel.getRefModel().setPk_group(cgroup);
        refPanel.getRefModel().setPk_user(logincontext.getPk_loginUser());
      }
    }
  }

}
