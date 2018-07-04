package nc.ui.so.m30.billref.m4331;

import nc.ui.pub.pf.BillSourceVar;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.ui.so.m30.billref.M30RefRefreshAction;
import nc.vo.so.m30.util.SaleOrderQuerySchemeUtils;
import nc.vo.so.m32.paravo.RefAddLineParaVO;

public class RefreshActionFor4331 extends M30RefRefreshAction {

  private static final long serialVersionUID = -6656412756872954691L;

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
    }
  }

}
