package nc.ui.so.m33.manest.model;

import nc.bs.framework.common.NCLocator;
import nc.itf.so.m33.maintain.m4c.ISaleOutETMaintain;
import nc.ui.pubapp.uif2app.query2.model.IQueryService;
import nc.ui.querytemplate.querytree.IQueryScheme;

public class SaleOutETQueryService implements IQueryService {

  private ISaleOutETMaintain service;

  public ISaleOutETMaintain getSaleOutETMaintainService() {
    if (this.service == null) {
      this.service = NCLocator.getInstance().lookup(ISaleOutETMaintain.class);
    }
    return this.service;
  }

  @Override
  public Object[] queryByQueryScheme(IQueryScheme queryScheme) throws Exception {
    return this.getSaleOutETMaintainService().querySquareOutFor4CManualET(
        queryScheme);
  }

}
