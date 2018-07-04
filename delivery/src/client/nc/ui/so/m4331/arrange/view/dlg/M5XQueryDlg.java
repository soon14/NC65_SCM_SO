package nc.ui.so.m4331.arrange.view.dlg;

public class M5XQueryDlg {

  // /**
  // *
  // */
  // private static final long serialVersionUID = 6518073071673359423L;
  //
  // public M5XQueryDlg(LoginContext context, TemplateInfo ti) {
  // super(context, ti);
  // // 初始化参照约束条件
  // this.initRefCondition();
  // }
  //
  // private void initRefCondition() {
  // // 参照调拨订单申请交易类型
  // new QTransTypeFilter(this, TOBillType.TransOrder.getCode()).filter();
  //
  // // 根据库存组织对物料进行过滤(动态的)
  // QMarterialFilter marterialFilter =
  // new QMarterialFilter(this, BillHeaderVO.CTOUTSTOCKORGID,
  // "bodyfk.cinventoryvid");
  // marterialFilter.addEditorListener();
  // QMarbasclassFilter marbasclassFilter =
  // new QMarbasclassFilter(this, "bodyfk.cinventoryvid.pk_marbasclass");
  // marbasclassFilter.setPk_orgCode(BillHeaderVO.CTOUTSTOCKORGID);
  // marbasclassFilter.addEditorListener();
  //
  // // 根据库存组织对仓库进行过滤
  // QWareHouseFilter houseFilter1 =
  // new QWareHouseFilter(this, BillHeaderVO.PK_ORG, "bodyfk.coutstordocid");
  // houseFilter1.addEditorListener();
  // QWareHouseFilter houseFilter2 =
  // new QWareHouseFilter(this, BillHeaderVO.CTOUTSTOCKORGID,
  // "bodyfk.ctoutstordocid");
  // houseFilter2.addEditorListener();
  // QWareHouseFilter houseFilter3 =
  // new QWareHouseFilter(this, BillHeaderVO.CINSTOCKORGID,
  // "bodyfk.cinstordocid");
  // houseFilter3.addEditorListener();
  //
  // }
}
