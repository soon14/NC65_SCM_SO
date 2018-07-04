package nc.ui.so.m4331.billref.m4c;

import java.awt.event.ActionEvent;
import java.util.List;

import nc.ui.pub.beans.UIDialog;
import nc.ui.pubapp.billref.src.action.QueryAction;
import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.ui.pubapp.uif2app.query2.model.IRefQueryService;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.pub.query.SOQuerySchemeUtils;

/**
 * 提供给销售出库的查询
 * 
 * @since 6.0
 * @version 2010-11-9 上午11:07:46
 * @author 祝会征
 */
public class QueryActionFor4C extends QueryAction {

  private static final long serialVersionUID = 1123875879974103332L;

  private SOQuerySchemeUtils querySchemeUtils;

  @Override
  public void doAction(ActionEvent e) throws Exception {
    // 弹出查询对话框
    DeliveryReferQuery referQuery =
        (DeliveryReferQuery) this.getRefContext().getRefDialog().getQueyDlg();
    QueryConditionDLGDelegator queryCondition = referQuery.getQryDLGDelegator();
    if (!this.isInitQuery()) {
      if (queryCondition.showModal() != UIDialog.ID_OK) {
        return;
      }
    }

    try {
      IQueryScheme qs = queryCondition.getQueryScheme();
      // 加业务流程过滤条件
      List<String> busiLst =
          this.getRefContext().getBillReferQuery().getBusitypes();
      List<String> tranList =
          this.getRefContext().getBillReferQuery().getTranstypes();
      if(null != busiLst){
        this.getQuerySchemeUtils().storeBusitype(qs, busiLst);
      }
      if (null != tranList) {
        this.getQuerySchemeUtils().storeTranType(qs, tranList);
      }

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
      ExceptionUtils.marsh(ex);
    }
    finally {
      this.setInitQuery(false);
    }
  }

  private SOQuerySchemeUtils getQuerySchemeUtils() {
    if (this.querySchemeUtils == null) {
      this.querySchemeUtils = new SOQuerySchemeUtils();
    }
    return this.querySchemeUtils;
  }

}
