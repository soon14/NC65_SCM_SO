package nc.ui.so.tranmatrel.eventhandler;

import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.scmpub.ref.FilterTransTypeRefUtils;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.ui.so.tranmatrel.billhandler.BillEventHandler;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.tranmatrel.entity.TranMatRelBVO;
import nc.vo.so.tranmatrel.entity.TranMatRelHVO;

public class BodyBeforeEditEventDispatcher implements
    IAppEventHandler<CardBodyBeforeEditEvent> {

  private BillEventHandler billhandler;

  public BillEventHandler getBillhandler() {
    if (this.billhandler == null) {
      this.billhandler = new BillEventHandler();
    }
    return this.billhandler;
  }

  @Override
  public void handleAppEvent(CardBodyBeforeEditEvent e) {
    this.getBillhandler().handleEditEvent(e);

    Boolean iseditable = Boolean.TRUE;
    if (e.getKey().equals(TranMatRelBVO.TRANTYPE)) {
      IKeyValue keyvalue = new CardKeyValue(e.getBillCardPanel());
      String csaleorgid = keyvalue.getHeadStringValue(TranMatRelHVO.PK_ORG);

      BillItem trantypeitem =
          e.getBillCardPanel().getBodyItem(TranMatRelBVO.TRANTYPE);
      FilterTransTypeRefUtils filterutils =
          new FilterTransTypeRefUtils(trantypeitem, csaleorgid);
      filterutils.filterTranType(new String[] {
        SOBillType.Order.getCode()
      }, null);
    }
    iseditable = this.getIsEnable(e);
    e.setReturnValue(iseditable);
  }

  public void setBillhandler(BillEventHandler billhandler) {
    this.billhandler = billhandler;
  }

  private Boolean getIsEnable(CardBodyBeforeEditEvent e) {
    Boolean isEnable = Boolean.TRUE;
    String key = e.getKey();
    if (key.equals(TranMatRelBVO.PK_MATERIALBASECLASS)
        || key.equals(TranMatRelBVO.PK_MATERIALSALECLASS)) {
      String pk_material =
          (String) e.getBillCardPanel().getBodyValueAt(e.getRow(),
              TranMatRelBVO.PK_MATERIAL_V);
      if (null != pk_material) {
        isEnable = Boolean.FALSE;
      }
    }
    else if (key.equals(TranMatRelBVO.PK_MATERIAL_V)) {
      String pk_base =
          (String) e.getBillCardPanel().getBodyValueAt(e.getRow(),
              TranMatRelBVO.PK_MATERIALBASECLASS);
      String pk_sale =
          (String) e.getBillCardPanel().getBodyValueAt(e.getRow(),
              TranMatRelBVO.PK_MATERIALSALECLASS);
      if ((null != pk_base) || (null != pk_sale)) {
        isEnable = Boolean.FALSE;
      }
    }
    return isEnable;
  }

}
