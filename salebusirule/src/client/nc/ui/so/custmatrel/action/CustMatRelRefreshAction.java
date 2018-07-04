/**
 * 
 */
package nc.ui.so.custmatrel.action;

import java.awt.event.ActionEvent;

import nc.bs.framework.common.NCLocator;
import nc.itf.so.custmatrel.ICustMatRelQueryResult;
import nc.ui.ml.NCLangRes;
import nc.ui.pubapp.uif2app.query2.action.DefaultRefreshAction;
import nc.ui.pubapp.uif2app.query2.model.ModelDataManager;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.ui.so.custmatrel.view.CardForm;
import nc.ui.uif2.ShowStatusBarMsgUtil;
import nc.ui.uif2.model.BatchBillTableModel;
import nc.ui.uif2.model.BillManageModel;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.pubapp.query2.sql.process.QuerySchemeProcessor;
import nc.vo.so.custmatrel.entity.CustMatRelHVO;
import nc.vo.so.custmatrel.entity.CustMatRelVO;

/**
 * @author gdsjw
 * 
 */
@SuppressWarnings("serial")
public class CustMatRelRefreshAction extends DefaultRefreshAction {

  private CardForm editor = null;

  public CardForm getEditor() {
    return this.editor;
  }

  public void setEditor(CardForm editor) {
    this.editor = editor;
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {
    this.getDataManager().refresh();
    ModelDataManager data = (ModelDataManager) this.getDataManager();
    QuerySchemeProcessor qrySchemeProcessor =
        new QuerySchemeProcessor(data.getQueryScheme());
    // 获取查询条件
    String pk_org = this.getQueryValue(qrySchemeProcessor, "pk_org");
    String pk_custbaseclass =
        this.getQueryValue(qrySchemeProcessor,
            "pk_custmatrel_b.pk_custbaseclass");
    String pk_custsaleclass =
        this.getQueryValue(qrySchemeProcessor,
            "pk_custmatrel_b.pk_custsaleclass");
    String pk_customer =
        this.getQueryValue(qrySchemeProcessor, "pk_custmatrel_b.pk_customer");
    String pk_marbasclass =
        this.getQueryValue(qrySchemeProcessor,
            "pk_custmatrel_b.pk_materialbaseclass");
    String pk_marsaleclass =
        this.getQueryValue(qrySchemeProcessor,
            "pk_custmatrel_b.pk_materialsaleclass");
    String pk_material =
        this.getQueryValue(qrySchemeProcessor, "pk_custmatrel_b.pk_material");
    // 将查询模板上的组织赋值到表头
    this.getEditor().getOrgPanel().setPkOrg(pk_org);

    // 查询完后，在表头保留前面选择的查询条件
    saveQueryValue(pk_org, pk_custbaseclass, pk_custsaleclass, pk_customer,
        pk_marbasclass, pk_marsaleclass, pk_material);

  }

  /**
   * 保存查询条件的值到表头
   * 
   * @param pk_custbaseclass
   * @param pk_custsaleclass
   * @param pk_customer
   */
  private void saveQueryValue(String pk_org, String pk_custbaseclass,
      String pk_custsaleclass, String pk_customer, String pk_marbasclass,
      String pk_marsaleclass, String pk_material) {
    if (!PubAppTool.isNull(pk_custbaseclass)) {
      this.getEditor().getBillCardPanel().getHeadItem(CustMatRelHVO.PK_ORG)
          .setValue(pk_org);
    }
    if (!PubAppTool.isNull(pk_custbaseclass)) {
      this.getEditor().getBillCardPanel()
          .getHeadItem(CustMatRelHVO.PK_CUSTBASECLASS)
          .setValue(pk_custbaseclass);
    }
    if (!PubAppTool.isNull(pk_custsaleclass)) {
      this.getEditor().getBillCardPanel()
          .getHeadItem(CustMatRelHVO.PK_CUSTSALECLASS)
          .setValue(pk_custsaleclass);
    }
    if (!PubAppTool.isNull(pk_customer)) {
      this.getEditor().getBillCardPanel()
          .getHeadItem(CustMatRelHVO.PK_CUSTOMER).setValue(pk_customer);
    }
    if (!PubAppTool.isNull(pk_marbasclass)) {
      this.getEditor().getBillCardPanel()
          .getHeadItem(CustMatRelHVO.PK_MATERIALBASECLASS)
          .setValue(pk_marbasclass);
    }
    if (!PubAppTool.isNull(pk_marsaleclass)) {
      this.getEditor().getBillCardPanel()
          .getHeadItem(CustMatRelHVO.PK_MATERIALSALECLASS)
          .setValue(pk_marsaleclass);
    }
    if (!PubAppTool.isNull(pk_material)) {
      this.getEditor().getBillCardPanel()
          .getHeadItem(CustMatRelHVO.PK_MATERIAL).setValue(pk_material);
    }
  }

  /**
   * 获取查询条件值
   * 
   * @param qrySchemeProcessor
   * @param code
   * @return
   */
  private String getQueryValue(QuerySchemeProcessor qrySchemeProcessor,
      String code) {
    String[] queryConditionValue = null;
    if (qrySchemeProcessor.getQueryCondition(code) != null) {
      queryConditionValue =
          qrySchemeProcessor.getQueryCondition(code).getValues();
      if (queryConditionValue[0].trim().startsWith("select")) {
        // 获取相应的查询条件的值
        String field =
            NCLocator.getInstance().lookup(ICustMatRelQueryResult.class)
                .custMatRelQueryResult(queryConditionValue);
        return field;
      }
      // 由于每个查询条件内只允许选一个查询条件，这里取数组内第一个值
      return queryConditionValue[0];
    }
    return null;
  }

  @Override
  protected void showQueryInfo() {
    int size = 0;
    if (this.getModel() instanceof BillManageModel) {
      size = ((BillManageModel) this.getModel()).getData().size();

    }
    else if (this.getModel() instanceof BatchBillTableModel) {
      size = ((BatchBillTableModel) this.getModel()).getRows().size();
    }
    if (size > 0) {
      ShowStatusBarMsgUtil.showStatusBarMsg(
          NCLangRes.getInstance().getStrByID("4006007_0", "04006007-0010",
              null, new String[] {
                Integer.toString(size)
              })/*刷新完成，共刷新{0}张单据。*/, this.getModel().getContext());
    }
    else {
      ShowStatusBarMsgUtil.showStatusBarMsg(
          NCLangRes.getInstance().getStrByID("4006007_0", "04006007-0010",
              null, new String[] {
                Integer.toString(size)
              })/*刷新完成，共刷新{0}张单据。*/, this.getModel().getContext());
    }
  }
}
