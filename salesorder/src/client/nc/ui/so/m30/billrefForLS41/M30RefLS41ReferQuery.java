package nc.ui.so.m30.billrefForLS41;

import java.awt.Container;

//import nc.bs.framework.common.NCLocator;
//import nc.itf.org.IOrgConst;
//import nc.pubitf.setting.defaultdata.OrgSettingAccessor;
import nc.ui.pubapp.billref.src.DefaultBillReferQuery;
import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.ui.pubapp.uif2app.query2.totalvo.MarAssistantDealer;
//import nc.vo.example.entity.FirstBillHVO;
//import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.querytemplate.TemplateInfo;

/*来源单据查询类*/

public class M30RefLS41ReferQuery extends DefaultBillReferQuery {

	public M30RefLS41ReferQuery(Container c, TemplateInfo info) {
		super(c, info);
	}

	@Override
	public void initQueryConditionDLG(QueryConditionDLGDelegator dlgDelegator) {
		this.setDefaultPk_org(dlgDelegator);
		// this.initFilterRef(dlgDelegator);
		this.initBodyRedundancyItem(dlgDelegator);

		// 主组织权限
		dlgDelegator.registerNeedPermissionOrgFieldCodes(new String[] { "pk_org" });
		dlgDelegator.addQueryCondVODealer(new MarAssistantDealer());
		dlgDelegator.setPowerEnable(true);
	}

	private void initBodyRedundancyItem(QueryConditionDLGDelegator dlgDelegator) {
		// // 销售组织
		// dlgDelegator.addRedundancyInfo(FirstBillHVO.PK_ORG,
		// "pk_salequotation_b.pk_org");
		// // 单据日期
		// dlgDelegator.addRedundancyInfo("dbilldate",
		// "pk_salequotation_b.dbilldate");
	}

	// private void initFilterRef(QueryConditionDLGDelegator dlgDelegator) {
	// // 预订单交易类型参照
	// QTransTypeFilter trantype =
	// new QTransTypeFilter(dlgDelegator, SOBillType.PreOrder.getCode());
	// trantype.filter();
	//
	// // 客户
	// QCustomerFilter invoicecust =
	// new QCustomerFilter(dlgDelegator, PreOrderHVO.CCUSTOMERID);
	// invoicecust.addEditorListener();
	//
	// // 销售部门
	// QDeptFilter dept = new QDeptFilter(dlgDelegator, PreOrderHVO.CDEPTID);
	// dept.setPk_orgCode(PreOrderHVO.PK_ORG);
	// dept.addEditorListener();
	//
	// // 物料基本分类
	// QMarbasclassFilter marclass =
	// new QMarbasclassFilter(dlgDelegator,
	// "so_preorder_b.cmaterialid.pk_marbasclass");
	// marclass.addEditorListener();
	//
	// // 物料销售分类
	// QMarSaleClassFilter marSaleClass =
	// new QMarSaleClassFilter(dlgDelegator,
	// "so_preorder_b.cmaterialvid.materialsale.pk_marsaleclass");
	// marSaleClass.setPk_orgCode(PreOrderHVO.PK_ORG);
	// marSaleClass.addEditorListener();
	//
	// // 物料编码
	// QMarterialFilter marteral =
	// new QMarterialFilter(dlgDelegator, PreOrderHVO.PK_ORG,
	// "so_preorder_b.cmaterialid.code");
	// marteral.addEditorListener();
	//
	// // 销售业务员
	// QPsndocFilter employee =
	// new QPsndocFilter(dlgDelegator, PreOrderHVO.CEMPLOYEEID);
	// employee.setPk_orgCode(PreOrderHVO.PK_ORG);
	// employee.addEditorListener();
	//
	// // 发货库存组织
	// QStockOrgFilter stockOrg =
	// new QStockOrgFilter(dlgDelegator, "so_preorder_b.csendstockorgid");
	// stockOrg.filter();
	// }

	private void setDefaultPk_org(QueryConditionDLGDelegator dlgDelegator) {

		// String defaultOrg = null;
		// try {
		// String pk_org = OrgSettingAccessor.getDefaultOrgUnit();
		// boolean isSaleOrg = NCLocator.getInstance()
		// .lookup(IOrgPubQuery.class)
		// .isTypeOf(pk_org, IOrgConst.SALEORGTYPE);
		// if (isSaleOrg) {
		// defaultOrg = pk_org;
		// }
		// } catch (Exception e) {
		// ExceptionUtils.wrappException(e);
		//
		// }
		// if (defaultOrg != null && defaultOrg.trim().length() > 0) {
		// dlgDelegator.setDefaultValue(FirstBillHVO.PK_ORG, defaultOrg);
		// }
	}
}