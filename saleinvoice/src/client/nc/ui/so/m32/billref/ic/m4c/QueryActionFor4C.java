package nc.ui.so.m32.billref.ic.m4c;

import java.util.List;

import nc.ui.pubapp.billref.src.action.QueryAction;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.so.pub.query.SOQuerySchemeUtils;

public class QueryActionFor4C extends QueryAction {

  /**
     * 
     */
  private static final long serialVersionUID = 1123875879974103332L;

  private SOQuerySchemeUtils querySchemeUtils;

  @Override
  protected void executeQuery(IQueryScheme queryScheme) {
    // BillSourceVar sourceVar =
    // this.getRefContext().getRefInfo().getBillSrcVar();

    this.appendBusitypeWhr(queryScheme);
    this.appendTranTypeWhr(queryScheme);

    super.executeQuery(queryScheme);
  }

  private void appendBusitypeWhr(IQueryScheme qs) {
    List<String> busiLst =
        this.getRefContext().getBillReferQuery().getBusitypes();
    this.getQuerySchemeUtils().storeBusitype(qs, busiLst);

  }

  private void appendTranTypeWhr(IQueryScheme qs) {
    List<String> tranList =
        this.getRefContext().getBillReferQuery().getTranstypes();
    // 把上游交易类型集合放到sheme中，在后台取出拼接相应的条件
    if (null != tranList) {
      this.getQuerySchemeUtils().storeTranType(qs, tranList);
    }
  }

  private SOQuerySchemeUtils getQuerySchemeUtils() {
    if (this.querySchemeUtils == null) {
      this.querySchemeUtils = new SOQuerySchemeUtils();
    }
    return this.querySchemeUtils;
  }
}
