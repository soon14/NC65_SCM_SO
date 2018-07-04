package nc.ui.so.m33.pub;

import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.querytemplate.querytree.IQueryScheme;

public class SquareOutManageModel extends BillManageModel {
  private IQueryScheme queryScheme;

  public IQueryScheme getQueryScheme() {
    return this.queryScheme;
  }

  public void setQueryScheme(IQueryScheme queryScheme) {
    this.queryScheme = queryScheme;
  }
}
