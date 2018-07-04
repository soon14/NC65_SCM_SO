package nc.ui.so.custmatrel.model;

import nc.ui.pubapp.uif2app.query2.IQueryConditionDLGInitializer;
import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.ui.scmpub.query.refregion.QCustomerFilter;
import nc.ui.scmpub.query.refregion.QTransTypeFilter;
import nc.ui.scmpub.query.refregion.RefCommonFilterListener;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.custmatrel.entity.CustMatRelHVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.mbuylargess.entity.BuyLargessHVO;
import nc.vo.so.pub.SOItemKey;

public class CustMatRelDLGInitializer implements IQueryConditionDLGInitializer {
	@Override
	  public void initQueryConditionDLG(QueryConditionDLGDelegator condDLGDelegator) {
	    this.initRefCondition(condDLGDelegator);
	    // 主组织权限
	    condDLGDelegator.registerNeedPermissionOrgFieldCodes(new String[] {
	      SOItemKey.PK_ORG
	    });
	    
	    // 初始参超过滤约束
	    this.initFilterRef(condDLGDelegator);
	    
	  }
	
	private void initFilterRef(QueryConditionDLGDelegator condDLGDelegator) {
	    RefCommonFilterListener filterutil =
	        new RefCommonFilterListener(condDLGDelegator, CustMatRelHVO.PK_ORG);

	    filterutil.addFilterMapsListeners();
	  }

	  private void initRefCondition(QueryConditionDLGDelegator condDLGDelegator) {
	    // 客户过滤
	    QCustomerFilter cordercustid =
	        new QCustomerFilter(condDLGDelegator, CustMatRelHVO.PK_CUSTOMER);
	    cordercustid.addEditorListener();
	  }
}
