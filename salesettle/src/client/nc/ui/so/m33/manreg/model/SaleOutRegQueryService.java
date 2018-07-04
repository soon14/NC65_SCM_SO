package nc.ui.so.m33.manreg.model;

import nc.bs.framework.common.NCLocator;
import nc.itf.so.m33.maintain.m4c.ISaleOutREGMaintain;
import nc.ui.pubapp.uif2app.query2.model.IQueryService;
import nc.ui.querytemplate.querytree.IQueryScheme;

public class SaleOutRegQueryService implements IQueryService {

  private ISaleOutREGMaintain service;

  public ISaleOutREGMaintain getSaleOutREGMaintainService() {
    if (this.service == null) {
      this.service = NCLocator.getInstance().lookup(ISaleOutREGMaintain.class);
    }
    return this.service;
  }

  @Override
  public Object[] queryByQueryScheme(IQueryScheme queryScheme) throws Exception {
    return this.getSaleOutREGMaintainService().querySquareOutFor4CManualREG(
        queryScheme);
  }

}
