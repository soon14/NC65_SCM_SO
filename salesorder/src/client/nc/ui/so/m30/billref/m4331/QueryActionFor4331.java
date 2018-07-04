package nc.ui.so.m30.billref.m4331;

import nc.ui.pub.pf.BillSourceVar;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.ui.so.m30.billref.M30RefQueryAction;
import nc.vo.so.m30.util.SaleOrderQuerySchemeUtils;
import nc.vo.so.m32.paravo.RefAddLineParaVO;

public class QueryActionFor4331 extends M30RefQueryAction {

  private static final long serialVersionUID = 2245362388880738854L;
  
  @Override
  protected void executeQuery(IQueryScheme queryScheme) {
    this.appendRefAddLineWhr(queryScheme);
    super.executeQuery(queryScheme);
  }

  private void appendRefAddLineWhr(IQueryScheme qs) {
    BillSourceVar sourceVar = this.getRefContext().getRefInfo().getBillSrcVar();
    if (sourceVar.getUserObj() != null) {
      RefAddLineParaVO paravo = (RefAddLineParaVO) sourceVar.getUserObj();
      SaleOrderQuerySchemeUtils utils = new SaleOrderQuerySchemeUtils();
      if (null != paravo.getCfirstbids()) {
        utils.storeSrcbid(qs, paravo.getCfirstbids());
      }
//      if (null != paravo.getBusitypes()) {
//        utils.store4CTO32Business(qs, paravo.getBusitypes());
//      }
//      if (null != paravo.getIs30to32busitypes()) {
//        utils.storeIs30to32busitypes(qs, paravo.getIs30to32busitypes());
//      }
    }
  }

}
