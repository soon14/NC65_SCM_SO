package nc.ui.so.m33.mansquare.model;

import nc.bs.framework.common.NCLocator;
import nc.itf.so.m33.maintain.m4c.ISaleOutSettleMaintain;
import nc.ui.pubapp.uif2app.query2.model.IQueryService;
import nc.ui.querytemplate.querytree.IQueryScheme;

public class SaleOutSettleQueryService implements IQueryService {

  private ISaleOutSettleMaintain service;

  public ISaleOutSettleMaintain getSaleSettleMaintainService() {
    if (this.service == null) {
      this.service =
          NCLocator.getInstance().lookup(ISaleOutSettleMaintain.class);
    }
    return this.service;
  }

  @Override
  public Object[] queryByQueryScheme(IQueryScheme queryScheme) throws Exception {
    return this.getSaleSettleMaintainService().querySquareOutFor4CManualSquare(
        queryScheme);
  }

}
