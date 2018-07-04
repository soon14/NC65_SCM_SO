package nc.ui.so.m30.billref.m4816;

import nc.ui.pubapp.billref.src.action.QueryAction;
import nc.ui.querytemplate.querytree.IQueryScheme;

public class QueryActionFor4816 extends QueryAction {

  private static final long serialVersionUID = 202061596172202404L;

  @Override
  protected void executeQuery(IQueryScheme queryScheme) {
    // BillSourceVar sourceVar =
    // this.getRefContext().getRefInfo().getBillSrcVar();
    // if (sourceVar.getUserObj() == null) {
    // this.appendBusitypeWhr(queryScheme);
    // }
    super.executeQuery(queryScheme);
  }

  // private void appendBusitypeWhr(IQueryScheme qs) {
  // List<String> busiLst =
  // this.getRefContext().getBillReferQuery().getBusitypes();
  // new SOQuerySchemeUtils().storeBusitype(qs, busiLst);
  // }
}
