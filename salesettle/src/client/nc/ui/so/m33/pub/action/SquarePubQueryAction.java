package nc.ui.so.m33.pub.action;

import nc.ui.ml.NCLangRes;
import nc.ui.pubapp.uif2app.query2.action.DefaultQueryAction;
import nc.ui.pubapp.uif2app.query2.model.IQueryService;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.ui.so.m33.pub.SquareOutManageModel;
import nc.ui.so.m33.pub.view.AbstractM33BillPubListView;
import nc.ui.uif2.IShowMsgConstant;
import nc.ui.uif2.ShowStatusBarMsgUtil;

public class SquarePubQueryAction extends DefaultQueryAction {

  private static final long serialVersionUID = -7729806069674838634L;

  private AbstractM33BillPubListView listView;

  private IQueryService service;

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
  protected void executeQuery(IQueryScheme queryScheme) {
    SquareOutManageModel m = (SquareOutManageModel) this.getModel();
    m.setQueryScheme(queryScheme);

    new SquareQueryActionProcess().processQuery(queryScheme, this.getService(),
        this.getListView(), m);

    this.showQueryInfo();

    if (this.getShowUpComponent() != null) {
      this.getShowUpComponent().showMeUp();
    }
  }

  @Override
  protected void showQueryInfo() {
    int size =
        this.getListView().getBillListPanel().getBodyBillModel().getRowCount();
    if (size > 0) {
      ShowStatusBarMsgUtil.showStatusBarMsg(NCLangRes.getInstance().getStrByID("4006010_0", "04006010-0083", null, new String[]{Integer.toString(size)})/*查询成功，已查到{0}张单据。*/, this
          .getModel().getContext());
    }
    else {
      ShowStatusBarMsgUtil.showStatusBarMsg(
          IShowMsgConstant.getQueryNullInfo(), this.getModel().getContext());
    }
  }

}
