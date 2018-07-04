package nc.ui.so.m30.sobalance.view;

import nc.itf.scmpub.reference.uap.setting.defaultdata.DefaultDataSettingAccessor;
import nc.ui.pubapp.uif2app.query2.IQueryConditionDLGInitializer;
import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.ui.pubapp.uif2app.query2.totalvo.MarAssistantDealer;
import nc.ui.scmpub.query.refregion.QCustomerFilter;
import nc.ui.scmpub.query.refregion.QDeptFilter;
import nc.ui.scmpub.query.refregion.QPaytermFilter;
import nc.ui.scmpub.query.refregion.QPsndocFilter;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.sobalance.entity.SoBalanceHVO;
import nc.vo.so.pub.SOItemKey;

public class SobalanceQueryDLGInitializer implements
    IQueryConditionDLGInitializer {

  @Override
  public void initQueryConditionDLG(QueryConditionDLGDelegator condDLGDelegator) {
    // 设置个性化中心默认主组织
    this.setDefaultPk_org(condDLGDelegator);
    // 初始参超过滤约束
    this.initFilterRef(condDLGDelegator);
    // 处理子表冗余字段
    // this.processBodyItem(condDLGDelegator);

    // 数据权限
    condDLGDelegator.setPowerEnable(true);

    // 主组织权限
    condDLGDelegator.registerNeedPermissionOrgFieldCodes(new String[] {
      SOItemKey.PK_ORG
    });

    condDLGDelegator.addQueryCondVODealer(new MarAssistantDealer());
  }

  private void initFilterRef(QueryConditionDLGDelegator condDLGDelegator) {

    // 客户:如果查询一个销售组织的数据，则可参照该销售组织可见的客户，否则参照集团范围客户档案。
    QCustomerFilter cust =
        new QCustomerFilter(condDLGDelegator, SoBalanceHVO.CCUSTOMERID);
    cust.setPk_orgCode(SoBalanceHVO.PK_ORG);
    cust.addEditorListener();

    // 开票客户:如果查询一个销售组织的数据，则可参照该销售组织可见的客户，否则参照集团范围客户档案。
    QCustomerFilter invoiceCust =
        new QCustomerFilter(condDLGDelegator, SoBalanceHVO.CINVOICECUSTID);
    invoiceCust.setPk_orgCode(SoBalanceHVO.PK_ORG);
    invoiceCust.addEditorListener();

    // 销售业务员：参照销售组织可见的人员（销售组织非空且唯一）,否则参照集团可见的物料进行录入
    QPsndocFilter employee =QPsndocFilter.createQPsndocFilterOfSO(
        condDLGDelegator, SaleOrderHVO.CEMPLOYEEID);
    employee.setPk_orgCode(SoBalanceHVO.PK_ORG);
    employee.addEditorListener();

    // 销售部门: 参照销售组织可见的部门（销售组织非空且唯一）,否则参照集团可见的物料进行录入
    QDeptFilter dept = QDeptFilter.createDeptFilterOfSO(
        condDLGDelegator, SaleOrderHVO.CDEPTID);
    dept.setPk_orgCode(SoBalanceHVO.PK_ORG);
    dept.addEditorListener();
    

    // 收付款协议：参照销售组织可见的客户（销售组织非空且唯一）,否则参照集团可见的物料进行录入
    QPaytermFilter payterm =
        new QPaytermFilter(condDLGDelegator, SoBalanceHVO.CPAYTERMID);
    payterm.setPk_orgCode(SoBalanceHVO.PK_ORG);
    payterm.addEditorListener();
  }

  // private void processBodyItem(QueryConditionDLGDelegator condDLGDelegator) {
  // // 销售组织
  // condDLGDelegator.addRedundancyInfo(SoBalanceHVO.PK_ORG,
  // "so_balance_b.pk_org");
  // }

  private void setDefaultPk_org(QueryConditionDLGDelegator condDLGDelegator) {
    String defaultOrg = null;
    try {
      defaultOrg = DefaultDataSettingAccessor.getDefaultSaleOrg();
    }
    catch (Exception ex) {
      ExceptionUtils.wrappException(ex);
    }
    if (defaultOrg != null && defaultOrg.trim().length() > 0) {
      condDLGDelegator.setDefaultValue(SaleOrderHVO.PK_ORG, defaultOrg);
    }
  }
}
