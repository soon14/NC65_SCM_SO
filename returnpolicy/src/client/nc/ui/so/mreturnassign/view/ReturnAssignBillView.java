package nc.ui.so.mreturnassign.view;

import nc.ui.pubapp.uif2app.view.BatchBillTable;
import nc.ui.pubapp.uif2app.view.util.BillPanelUtils;
import nc.vo.so.pub.util.BaseSaleClassUtil;

public class ReturnAssignBillView extends BatchBillTable {
  private static final long serialVersionUID = 1L;

  @Override
  public void initUI() {
    super.initUI();

    String pk_group = this.getModel().getContext().getPk_group();
    boolean ismarbas = BaseSaleClassUtil.isMarBaseClass(pk_group);

    if (ismarbas) {/*-=notranslate=-*/
      this.getBillCardPanel().hideBodyTableCol("pk_marsaleclass");
      this.getBillCardPanel().hideBodyTableCol("pk_marsaleclass.name");
    }
    else {
      this.getBillCardPanel().hideBodyTableCol("pk_marbasclass");
      this.getBillCardPanel().hideBodyTableCol("pk_marbasclass.name");
    }
    boolean iscusbas = BaseSaleClassUtil.isCustBaseClass(pk_group);
    if (iscusbas) {/*-=notranslate=-*/
      this.getBillCardPanel().hideBodyTableCol("pk_custsaleclass");
      this.getBillCardPanel().hideBodyTableCol("pk_custsaleclass.name");
    }
    else {
      this.getBillCardPanel().hideBodyTableCol("pk_custclass");
      this.getBillCardPanel().hideBodyTableCol("pk_custclass.name");
    }
    this.getBillCardPanel().getBodyItem("pk_group").setEdit(false);
    this.getBillCardPanel().getBodyItem("pk_saleorg").setEdit(false);
  }

  @Override
  protected void onEdit() {
    super.onEdit();
    boolean oldValue = this.getBillCardPanel().isEnabled();
    this.firePropertyChange("editable", oldValue, true);

    BillPanelUtils.setOrgForAllRef(this.getBillCardPanel(), this.getModel()
        .getContext());
  }
}
