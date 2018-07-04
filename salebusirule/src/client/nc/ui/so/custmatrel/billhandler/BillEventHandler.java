package nc.ui.so.custmatrel.billhandler;

import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pub.bill.IBillItem;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailBeforeEditEvent;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.vo.so.custmatrel.entity.CustMatRelBVO;
import nc.vo.so.depmatrel.entity.DepMatRelHVO;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.uif2.LoginContext;

public class BillEventHandler {

  public void handleEditEvent(CardBodyAfterEditEvent e) {
    // TODO
  }

  public void handleEditEvent(CardBodyBeforeEditEvent e) {
    Boolean isEnable = Boolean.TRUE;
    BillItem billitem = e.getBillCardPanel().getBodyItem(e.getKey());
    this.setOrgForRef(e.getBillCardPanel(), e.getContext(), billitem);
    isEnable = this.getEditEnable(e);
    e.setReturnValue(isEnable);
  }

  public void handleEditEvent(CardHeadTailAfterEditEvent e) {
    // TODO
  }

  public void handleEditEvent(CardHeadTailBeforeEditEvent e) {
    BillItem billitem = e.getBillCardPanel().getHeadTailItem(e.getKey());
    this.setOrgForRef(e.getBillCardPanel(), e.getContext(), billitem);
  }

  /*
   * 设置客户和物料编辑性 
   * @param e
   */
  private Boolean getEditEnable(CardBodyBeforeEditEvent e) {
    Boolean isEnable = Boolean.TRUE;
    if (e.getKey().equals(CustMatRelBVO.PK_CUSTBASECLASS)
        || e.getKey().equals(CustMatRelBVO.PK_CUSTSALECLASS)) {
      String customer =
          (String) e.getBillCardPanel().getBodyValueAt(e.getRow(),
              CustMatRelBVO.PK_CUSTOMER);
      if (null != customer) {
        isEnable = Boolean.FALSE;
      }
    }
    else if (e.getKey().equals(CustMatRelBVO.PK_CUSTOMER)) {
      String pk_cust =
          (String) e.getBillCardPanel().getBodyValueAt(e.getRow(),
              CustMatRelBVO.PK_CUSTBASECLASS);
      String pk_sale =
          (String) e.getBillCardPanel().getBodyValueAt(e.getRow(),
              CustMatRelBVO.PK_CUSTSALECLASS);
      if ((null != pk_cust) || (null != pk_sale)) {
        isEnable = Boolean.FALSE;
      }
    }
    else if (e.getKey().equals(CustMatRelBVO.PK_MATERIALBASECLASS)
        || e.getKey().equals(CustMatRelBVO.PK_MATERIALSALECLASS)) {
      String pk_matrial =
          (String) e.getBillCardPanel().getBodyValueAt(e.getRow(),
              CustMatRelBVO.PK_MATERIAL_V);
      if (null != pk_matrial) {
        isEnable = Boolean.FALSE;
      }
    }
    else if (e.getKey().equals(CustMatRelBVO.PK_MATERIAL_V)) {
      String pk_cust =
          (String) e.getBillCardPanel().getBodyValueAt(e.getRow(),
              CustMatRelBVO.PK_MATERIALBASECLASS);
      String pk_sale =
          (String) e.getBillCardPanel().getBodyValueAt(e.getRow(),
              CustMatRelBVO.PK_MATERIALSALECLASS);
      if ((null != pk_cust) || (null != pk_sale)) {
        isEnable = Boolean.FALSE;
      }
    }
    return isEnable;
  }

  private void setOrgForRef(BillCardPanel billcardpanel,
      LoginContext logincontext, BillItem billitem) {

    if (billitem.getDataType() == IBillItem.UFREF) {
      UIRefPane refPanel = (UIRefPane) billitem.getComponent();
      if (refPanel.getRefModel() != null) {
        IKeyValue keyvalue = new CardKeyValue(billcardpanel);
        String csaleorgid = keyvalue.getHeadStringValue(DepMatRelHVO.PK_ORG);
        refPanel.getRefModel().setPk_org(csaleorgid);
        String cgroup = keyvalue.getHeadStringValue(DepMatRelHVO.PK_GROUP);
        refPanel.getRefModel().setPk_group(cgroup);
        refPanel.getRefModel().setPk_user(logincontext.getPk_loginUser());
      }
    }
  }
}
