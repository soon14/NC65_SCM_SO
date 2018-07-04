package nc.ui.so.m33.pub.action;

import java.awt.event.ActionEvent;

import nc.ui.ml.NCLangRes;
import nc.ui.pubapp.uif2app.query2.action.DefaultRefreshAction;
import nc.ui.pubapp.uif2app.query2.model.IQueryService;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.ui.so.m33.pub.SquareOutManageModel;
import nc.ui.so.m33.pub.view.AbstractM33BillPubListView;
import nc.ui.uif2.ShowStatusBarMsgUtil;

public class SquarePubRefreshAction extends DefaultRefreshAction {

  private static final long serialVersionUID = -1618498513027852735L;

  private AbstractM33BillPubListView listView;

  private IQueryService service;

  @Override
  public void doAction(ActionEvent e) throws Exception {
    SquareOutManageModel smodel = (SquareOutManageModel) this.getModel();
    IQueryScheme queryScheme = smodel.getQueryScheme();
    if (null == queryScheme) {
      return;
    }
    new SquareQueryActionProcess().processQuery(queryScheme, this.getService(),
        this.getListView(), smodel);
    this.showQueryInfo();
  }

  public AbstractM33BillPubListView getListView() {
    return this.listView;
  }

  public IQueryService getService() {
    return this.service;
  }

  public void setListView(AbstractM33BillPubListView listView1) {
    this.listView = listView1;
  }

  public void setService(IQueryService service) {
    this.service = service;
  }

  @Override
  protected boolean isActionEnable() {
    SquareOutManageModel smodel = (SquareOutManageModel) this.getModel();
    IQueryScheme queryScheme = smodel.getQueryScheme();

    return null != queryScheme;
  }

  @Override
  protected void showQueryInfo() {
    int size =
        this.getListView().getBillListPanel().getBodyBillModel().getRowCount();
    if (size > 0) {
      ShowStatusBarMsgUtil.showStatusBarMsg(
          NCLangRes.getInstance().getStrByID("4006010_0", "04006010-0084",
              null, new String[] {
                Integer.toString(size)
              })/*刷新成功，共刷新{0}张单据。*/, this.getModel().getContext());
    }
  }
}
