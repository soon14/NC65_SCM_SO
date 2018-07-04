package nc.ui.so.m4331.arrange.view.dlg;


public class M30QueryDlg {
  /**
   * 
   */
  private static final long serialVersionUID = 7889038793735005609L;

  // public M30QueryDlg(LoginContext context, TemplateInfo ti) {
  //
  // super(context, ti);
  // // 初始化参照约束条件
  // this.initRefCondition();
  // }
  //
  // /**
  // * 方法功能描述：返回销售订单附表别名。
  // * <p>
  // * <b>参数说明</b>
  // *
  // * @param where
  // * @return <p>
  // * @author fengjb
  // * @time 2010-5-27 上午10:54:32
  // */
  // private String getTableAlias(String where) {
  // SaleOrderBVO body = new SaleOrderBVO();
  // String table =
  // body.getMetaData().getPrimaryAttribute().getColumn().getTable()
  // .getName();
  // FromWhereParseUtil util = new FromWhereParseUtil(where);
  // return util.getTableAlias(table);
  // }
  //
  // /**
  // * 父类方法重写
  // */
  // @Override
  // public String getWhereSQL() {
  //
  // String fromsql = super.getFromPart();
  // // 获得子表别名
  // String tableAlias = this.getTableAlias(fromsql);
  // fromsql +=
  // " left outer join so_saleorder_exe exe on " + tableAlias
  // + ".csaleorderbid = exe.csaleorderbid ";
  // return " from " + fromsql + " where " + super.getWherePart();
  // }
  //
  // /**
  // * 方法功能描述：初始化参照约束条件。
  // * <p>
  // * <b>参数说明</b>
  // * <p>
  // *
  // * @author fengjb
  // * @time 2010-6-30 上午10:49:43
  // */
  // private void initRefCondition() {
  // // 订单交易类型
  // QTransTypeFilter tranfilter =
  // new QTransTypeFilter(this, SOBillType.Order.getCode());
  // tranfilter.filter();
  //
  // // 发货仓库
  // QWareHouseFilter warehouse =
  // new QWareHouseFilter(this, "so_saleorder_b.csendstockorgid",
  // "so_saleorder_b.csendstordocid");
  // warehouse.addEditorListener();
  // // 客户编码
  // QCustomerFilter ordercust = new QCustomerFilter(this, "ccustomerid");
  // ordercust.addEditorListener();
  // // 客户销售分类
  // // 客户基本分类
  // // 物料销售分类
  // QMarSaleClassFilter marSaleClass =
  // new QMarSaleClassFilter(this,
  // "so_saleorder_b.cmaterialvid.materialsale.pk_marsaleclass");
  // marSaleClass.addEditorListener();
  // // 物料基本分类
  // QMarbasclassFilter marclass =
  // new QMarbasclassFilter(this,
  // "so_saleorder_b.cmaterialvid.pk_marbasclass");
  // marclass.addEditorListener();
  // marclass.filterByGroup();
  // // 物料编码
  // QMarterialFilter marteral =
  // new QMarterialFilter(this, "pk_org", "so_saleorder_b.cmaterialvid");
  // marteral.addEditorListener();
  // }
}
