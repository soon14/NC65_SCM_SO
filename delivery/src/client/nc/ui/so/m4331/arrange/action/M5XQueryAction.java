package nc.ui.so.m4331.arrange.action;

import java.awt.event.ActionEvent;

import nc.bs.uif2.IActionCode;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pubapp.billref.push.BillPushConst;
import nc.ui.pubapp.billref.push.PushQueryAction;
import nc.ui.querytemplate.queryarea.IQueryExecutor;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.ui.uif2.actions.ActionInitializer;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.query2.sql.process.QueryConstants;
import nc.vo.scmpub.res.billtype.SOBillType;

public class M5XQueryAction extends PushQueryAction {

  private static final long serialVersionUID = 1L;

  private IQueryExecutor queryExecutor;

  public M5XQueryAction() {
    ActionInitializer.initializeAction(this, IActionCode.QUERY);
    this.queryExecutor = new IQueryExecutor() {

      @Override
      public void doQuery(IQueryScheme queryScheme) {
        M5XQueryAction.this.getQryDLGDelegator().fillQuerySheme(queryScheme);
        queryScheme.put(QueryConstants.MAX_QUERY_COUNT_CONSTANT,
            M5XQueryAction.this.getMaxQueryCount());
        queryScheme.put(QueryConstants.KEY_FUNC_NODE,
            M5XQueryAction.this.getFunNode());
        M5XQueryAction.this.executeQuery(queryScheme);
      }

    };
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {
    // super.doAction(e);
    try {
      if (this.getQryDLGDelegator().showModal() == UIDialog.ID_OK) {
        this.processQuery();
        this.showQueryInfo();
        if (this.getShowUpComponent() != null) {
          this.getShowUpComponent().showMeUp();
        }
        this.getBillContext().getMultiModel(BillPushConst.DEST)
            .getModelByBillType(SOBillType.Delivery.getCode()).initModel(null);
      }
    }
    catch (Exception ex) {
      ExceptionUtils.wrappException(ex);
    }
  }

  /**
   * 调用Processor执行查询行为
   */
  private void processQuery() {
    IQueryScheme queryScheme = this.getQryDLGDelegator().getQueryScheme();
    this.queryExecutor.doQuery(queryScheme);
  }

}
