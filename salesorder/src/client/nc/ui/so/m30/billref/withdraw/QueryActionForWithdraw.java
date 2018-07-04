package nc.ui.so.m30.billref.withdraw;

import java.awt.event.ActionEvent;

import nc.ui.pub.beans.UIDialog;
import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.ui.pubapp.uif2app.query2.model.IRefQueryService;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

@SuppressWarnings("serial")
public class QueryActionForWithdraw extends
    nc.ui.pubapp.billref.src.action.QueryAction {

  @Override
  public void doAction(ActionEvent e) throws Exception {
    SaleOrderBillReferQuery referQuery =
        (SaleOrderBillReferQuery) this.getRefContext().getRefDialog()
            .getQueyDlg();
    QueryConditionDLGDelegator queryCondition = referQuery.getQryDLGDelegator();
    if (!this.isInitQuery()) {
      if (queryCondition.showModal() != UIDialog.ID_OK) {
        return;
      }
    }
    try {
      IQueryScheme qs = queryCondition.getQueryScheme();
      if (this.getRefContext().getRefInfo().getQueryService() != null) {
        IRefQueryService refqueryService =
            (IRefQueryService) this.getRefContext().getRefInfo()
                .getQueryService();
        AggregatedValueObject[] billvos =
            (AggregatedValueObject[]) refqueryService.queryByQueryScheme(qs);
        this.getRefBillModel().setBillVOs(billvos);
        return;
      }
      this.getRefBillModel().setQueryScheme(queryCondition.getQueryScheme());
    }
    catch (Exception ex) {
      ExceptionUtils.unmarsh(ex);
    }
    finally {
      this.setInitQuery(false);
    }
  }

}
