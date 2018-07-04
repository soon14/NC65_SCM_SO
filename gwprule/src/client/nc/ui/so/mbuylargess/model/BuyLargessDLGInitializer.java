package nc.ui.so.mbuylargess.model;

import nc.ui.pubapp.uif2app.query2.IQueryConditionDLGInitializer;
import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.ui.scmpub.query.refregion.QCustomerFilter;
import nc.vo.so.mbuylargess.entity.BuyLargessHVO;
import nc.vo.so.pub.SOItemKey;

public class BuyLargessDLGInitializer implements IQueryConditionDLGInitializer {

  @Override
  public void initQueryConditionDLG(QueryConditionDLGDelegator condDLGDelegator) {
    this.initRefCondition(condDLGDelegator);
    // 主组织权限
    condDLGDelegator.registerNeedPermissionOrgFieldCodes(new String[] {
      SOItemKey.PK_ORG
    });
  }

  private void initRefCondition(QueryConditionDLGDelegator condDLGDelegator) {
    // 客户过滤
    QCustomerFilter cordercustid =
        new QCustomerFilter(condDLGDelegator, BuyLargessHVO.PK_CUSTOMER);
    cordercustid.addEditorListener();
  }
}
