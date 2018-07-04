package nc.ui.so.depmatrel.eventhandler;

import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.scmpub.ref.FilterDeptRefUtils;
import nc.ui.scmpub.ref.FilterPsndocRefUtils;
import nc.ui.so.depmatrel.billhandler.BillEventHandler;
import nc.vo.so.depmatrel.entity.DepMatRelBVO;
import nc.vo.so.depmatrel.entity.DepMatRelHVO;
import nc.vo.so.m30.entity.SaleOrderHVO;

public class BodyBeforeEditEventDispatcher implements
    IAppEventHandler<CardBodyBeforeEditEvent> {

  private BillEventHandler billhandler;

  @Override
  public void handleAppEvent(CardBodyBeforeEditEvent e) {
    this.getBillhandler().handleEditEvent(e);

    Boolean iseditable = this.getIsEnable(e);
    if (e.getKey().equals(DepMatRelBVO.PK_DEPT_V)) {
      String pk_org =
          (String) e.getBillCardPanel().getHeadItem(DepMatRelHVO.PK_ORG)
              .getValueObject();
      BillItem item = e.getBillCardPanel().getBodyItem(DepMatRelBVO.PK_DEPT_V);
      FilterDeptRefUtils  filter = FilterDeptRefUtils
          .createFilterDeptRefUtilsOfSO((UIRefPane) item.getComponent());
          filter.filterItemRefByOrg(pk_org);
    }
    else if (e.getKey().equals(DepMatRelBVO.CEMPLOYEEID)) {
      String pk_org =
          (String) e.getBillCardPanel().getHeadItem(DepMatRelHVO.PK_ORG)
              .getValueObject();
      BillItem item =
          e.getBillCardPanel().getBodyItem(DepMatRelBVO.CEMPLOYEEID);
      FilterPsndocRefUtils filter = FilterPsndocRefUtils
          .createFilterPsndocRefUtilsOfSO((UIRefPane) item.getComponent());
          filter.filterItemRefByOrg(pk_org);
    }
    e.setReturnValue(iseditable);
  }

  private Boolean getIsEnable(CardBodyBeforeEditEvent e) {
    Boolean isEnable = Boolean.TRUE;
    String key = e.getKey();
    if (key.equals(DepMatRelBVO.PK_MATERIALBASECLASS)
        || key.equals(DepMatRelBVO.PK_MATERIALSALECLASS)) {
      String pk_material =
          (String) e.getBillCardPanel().getBodyValueAt(e.getRow(),
              DepMatRelBVO.PK_MATERIAL_V);
      if (null != pk_material) {
        isEnable = Boolean.FALSE;
      }
    }
    else if (key.equals(DepMatRelBVO.PK_MATERIAL_V)) {
      String pk_base =
          (String) e.getBillCardPanel().getBodyValueAt(e.getRow(),
              DepMatRelBVO.PK_MATERIALBASECLASS);
      String pk_sale =
          (String) e.getBillCardPanel().getBodyValueAt(e.getRow(),
              DepMatRelBVO.PK_MATERIALSALECLASS);
      if (null != pk_base || null != pk_sale) {
        isEnable = Boolean.FALSE;
      }
    }
    return isEnable;
  }

  public BillEventHandler getBillhandler() {
    if (this.billhandler == null) {
      this.billhandler = new BillEventHandler();
    }
    return this.billhandler;
  }

  public void setBillhandler(BillEventHandler billhandler) {
    this.billhandler = billhandler;
  }

}
