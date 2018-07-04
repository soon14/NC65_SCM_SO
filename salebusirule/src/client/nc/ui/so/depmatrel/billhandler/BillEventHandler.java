package nc.ui.so.depmatrel.billhandler;

import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pub.bill.IBillItem;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailBeforeEditEvent;
import nc.ui.scmpub.ref.FilterDeptRefUtils;
import nc.ui.scmpub.ref.FilterPsndocRefUtils;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.vo.so.depmatrel.entity.DepMatRelBVO;
import nc.vo.so.depmatrel.entity.DepMatRelHVO;
import nc.vo.so.m4331.entity.DeliveryBVO;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.uif2.LoginContext;

public class BillEventHandler {

  public void handleEditEvent(CardBodyAfterEditEvent e) {
  }

  public void handleEditEvent(CardBodyBeforeEditEvent e) {
    BillItem billitem = e.getBillCardPanel().getBodyItem(e.getKey());
    this.setOrgForRef(e.getBillCardPanel(), e.getContext(), billitem);
    if(DepMatRelBVO.CEMPLOYEEID.equals(e.getKey())){
      empBeforEdit(e);
    }
    else if(DepMatRelBVO.PK_DEPT_V.equals(e.getKey())){
      deptBeforEdit(e);
    }
  }
  
  
  /**
   * 
   * 部门编辑前
   * 
   * @param e
   */
  public void deptBeforEdit(CardBodyBeforeEditEvent e) {
    BillCardPanel cardPanel = e.getBillCardPanel();
    CardKeyValue keyValue = new CardKeyValue(cardPanel);
    String pk_org = keyValue.getHeadStringValue(DepMatRelBVO.PK_ORG);
    BillItem item = cardPanel.getBodyItem(DepMatRelBVO.PK_DEPT_V);
    UIRefPane panel = (UIRefPane) item.getComponent();
    FilterDeptRefUtils filter = FilterDeptRefUtils
    .createFilterDeptRefUtilsOfSO(panel);
    filter.filterItemRefByOrg(pk_org);
  }
  
  
  /**
   * 
   * 人员编辑前
   * 
   * @param e
   */
  public void empBeforEdit(CardBodyBeforeEditEvent e) {
    BillCardPanel cardPanel = e.getBillCardPanel();
    CardKeyValue keyValue = new CardKeyValue(cardPanel);
    String pk_org = keyValue.getHeadStringValue(DepMatRelBVO.PK_ORG);
    BillItem item = cardPanel.getBodyItem(DepMatRelBVO.CEMPLOYEEID);
    UIRefPane panel = (UIRefPane) item.getComponent();
    FilterPsndocRefUtils filter = FilterPsndocRefUtils
        .createFilterPsndocRefUtilsOfSO(panel);
    filter.filterItemRefByOrg(pk_org);
  }

  public void handleEditEvent(CardHeadTailAfterEditEvent e) {
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
        String csaleorgid = keyvalue.getHeadStringValue(DepMatRelHVO.PK_ORG);
        refPanel.getRefModel().setPk_org(csaleorgid);
        String cgroup = keyvalue.getHeadStringValue(DepMatRelHVO.PK_GROUP);
        refPanel.getRefModel().setPk_group(cgroup);
        refPanel.getRefModel().setPk_user(logincontext.getPk_loginUser());
      }
    }
  }

}
