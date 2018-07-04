package nc.ui.so.m30.revise.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.ui.uif2.factory.AbstractJavaBeanDefinition;

public class saleorder_revise_config extends AbstractJavaBeanDefinition {
	private Map<String, Object> context = new HashMap();

	public nc.vo.uif2.LoginContext getContext() {
		if (context.get("context") != null)
			return (nc.vo.uif2.LoginContext) context.get("context");
		nc.vo.uif2.LoginContext bean = new nc.vo.uif2.LoginContext();
		context.put("context", bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.view.value.AggVOMetaBDObjectAdapterFactory getBoadatorfactory() {
		if (context.get("boadatorfactory") != null)
			return (nc.ui.pubapp.uif2app.view.value.AggVOMetaBDObjectAdapterFactory) context
					.get("boadatorfactory");
		nc.ui.pubapp.uif2app.view.value.AggVOMetaBDObjectAdapterFactory bean = new nc.ui.pubapp.uif2app.view.value.AggVOMetaBDObjectAdapterFactory();
		context.put("boadatorfactory", bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.uif2.components.pagination.PaginationBar getPageBar() {
		if (context.get("pageBar") != null)
			return (nc.ui.uif2.components.pagination.PaginationBar) context
					.get("pageBar");
		nc.ui.uif2.components.pagination.PaginationBar bean = new nc.ui.uif2.components.pagination.PaginationBar();
		context.put("pageBar", bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.actions.pagination.BillModelPaginationDelegator getPageDelegator() {
		if (context.get("pageDelegator") != null)
			return (nc.ui.pubapp.uif2app.actions.pagination.BillModelPaginationDelegator) context
					.get("pageDelegator");
		nc.ui.pubapp.uif2app.actions.pagination.BillModelPaginationDelegator bean = new nc.ui.pubapp.uif2app.actions.pagination.BillModelPaginationDelegator(
				getManageAppModel());
		context.put("pageDelegator", bean);
		bean.setPaginationQuery(getPageQuery());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.so.m30.revise.model.SaleOrderRevisePageService getPageQuery() {
		if (context.get("pageQuery") != null)
			return (nc.ui.so.m30.revise.model.SaleOrderRevisePageService) context
					.get("pageQuery");
		nc.ui.so.m30.revise.model.SaleOrderRevisePageService bean = new nc.ui.so.m30.revise.model.SaleOrderRevisePageService();
		context.put("pageQuery", bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.scmpub.page.model.SCMBillPageMediator getPageMediator() {
		if (context.get("pageMediator") != null)
			return (nc.ui.scmpub.page.model.SCMBillPageMediator) context
					.get("pageMediator");
		nc.ui.scmpub.page.model.SCMBillPageMediator bean = new nc.ui.scmpub.page.model.SCMBillPageMediator();
		context.put("pageMediator", bean);
		bean.setListView(getListView());
		bean.setRecordInPage(10);
		bean.setCachePages(10);
		bean.setPageDelegator(getPageDelegator());
		bean.init();
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.model.BillManageModel getManageAppModel() {
		if (context.get("ManageAppModel") != null)
			return (nc.ui.pubapp.uif2app.model.BillManageModel) context
					.get("ManageAppModel");
		nc.ui.pubapp.uif2app.model.BillManageModel bean = new nc.ui.pubapp.uif2app.model.BillManageModel();
		context.put("ManageAppModel", bean);
		bean.setBusinessObjectAdapterFactory(getBoadatorfactory());
		bean.setContext(getContext());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.scmpub.page.model.SCMBillPageModelDataManager getModelDataManager() {
		if (context.get("modelDataManager") != null)
			return (nc.ui.scmpub.page.model.SCMBillPageModelDataManager) context
					.get("modelDataManager");
		nc.ui.scmpub.page.model.SCMBillPageModelDataManager bean = new nc.ui.scmpub.page.model.SCMBillPageModelDataManager();
		context.put("modelDataManager", bean);
		bean.setModel(getManageAppModel());
		bean.setPageQuery(getPageQuery());
		bean.setPageDelegator(getPageDelegator());
		bean.setPagePanel(getListToolbarPnl());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.uif2.editor.TemplateContainer getTemplateContainer() {
		if (context.get("templateContainer") != null)
			return (nc.ui.uif2.editor.TemplateContainer) context
					.get("templateContainer");
		nc.ui.uif2.editor.TemplateContainer bean = new nc.ui.uif2.editor.TemplateContainer();
		context.put("templateContainer", bean);
		bean.setContext(getContext());
		bean.load();
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.so.m30.revise.lazilyload.M30ReviseDefaultBillLazilyLoader getBillLazilyLoader() {
		if (context.get("billLazilyLoader") != null)
			return (nc.ui.so.m30.revise.lazilyload.M30ReviseDefaultBillLazilyLoader) context
					.get("billLazilyLoader");
		nc.ui.so.m30.revise.lazilyload.M30ReviseDefaultBillLazilyLoader bean = new nc.ui.so.m30.revise.lazilyload.M30ReviseDefaultBillLazilyLoader();
		context.put("billLazilyLoader", bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.lazilyload.LazilyLoadManager getLasilyLodadMediator() {
		if (context.get("LasilyLodadMediator") != null)
			return (nc.ui.pubapp.uif2app.lazilyload.LazilyLoadManager) context
					.get("LasilyLodadMediator");
		nc.ui.pubapp.uif2app.lazilyload.LazilyLoadManager bean = new nc.ui.pubapp.uif2app.lazilyload.LazilyLoadManager();
		context.put("LasilyLodadMediator", bean);
		bean.setModel(getManageAppModel());
		bean.setLoader(getBillLazilyLoader());
		bean.setLazilyLoadSupporter(getManagedList0());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList0() {
		List list = new ArrayList();
		list.add(getCardPanelLazilyLoad_14a42f9());
		list.add(getListPanelLazilyLoad_7328ab());
		list.add(getActionLazilyLoad_7317b6());
		return list;
	}

	private nc.ui.pubapp.uif2app.lazilyload.CardPanelLazilyLoad getCardPanelLazilyLoad_14a42f9() {
		if (context
				.get("nc.ui.pubapp.uif2app.lazilyload.CardPanelLazilyLoad#14a42f9") != null)
			return (nc.ui.pubapp.uif2app.lazilyload.CardPanelLazilyLoad) context
					.get("nc.ui.pubapp.uif2app.lazilyload.CardPanelLazilyLoad#14a42f9");
		nc.ui.pubapp.uif2app.lazilyload.CardPanelLazilyLoad bean = new nc.ui.pubapp.uif2app.lazilyload.CardPanelLazilyLoad();
		context.put(
				"nc.ui.pubapp.uif2app.lazilyload.CardPanelLazilyLoad#14a42f9",
				bean);
		bean.setBillform(getBillFormEditor());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.pubapp.uif2app.lazilyload.ListPanelLazilyLoad getListPanelLazilyLoad_7328ab() {
		if (context
				.get("nc.ui.pubapp.uif2app.lazilyload.ListPanelLazilyLoad#7328ab") != null)
			return (nc.ui.pubapp.uif2app.lazilyload.ListPanelLazilyLoad) context
					.get("nc.ui.pubapp.uif2app.lazilyload.ListPanelLazilyLoad#7328ab");
		nc.ui.pubapp.uif2app.lazilyload.ListPanelLazilyLoad bean = new nc.ui.pubapp.uif2app.lazilyload.ListPanelLazilyLoad();
		context.put(
				"nc.ui.pubapp.uif2app.lazilyload.ListPanelLazilyLoad#7328ab",
				bean);
		bean.setListView(getListView());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.pubapp.uif2app.lazilyload.ActionLazilyLoad getActionLazilyLoad_7317b6() {
		if (context
				.get("nc.ui.pubapp.uif2app.lazilyload.ActionLazilyLoad#7317b6") != null)
			return (nc.ui.pubapp.uif2app.lazilyload.ActionLazilyLoad) context
					.get("nc.ui.pubapp.uif2app.lazilyload.ActionLazilyLoad#7317b6");
		nc.ui.pubapp.uif2app.lazilyload.ActionLazilyLoad bean = new nc.ui.pubapp.uif2app.lazilyload.ActionLazilyLoad();
		context.put("nc.ui.pubapp.uif2app.lazilyload.ActionLazilyLoad#7317b6",
				bean);
		bean.setModel(getManageAppModel());
		bean.setActionList(getManagedList1());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList1() {
		List list = new ArrayList();
		list.add(getPrintAction());
		list.add(getPreviewAction());
		list.add(getOutputAction());
		list.add(getSplitPrintAction());
		return list;
	}

	public nc.ui.so.m30.billui.view.SaleOrderBillListView getListView() {
		if (context.get("listView") != null)
			return (nc.ui.so.m30.billui.view.SaleOrderBillListView) context
					.get("listView");
		nc.ui.so.m30.billui.view.SaleOrderBillListView bean = new nc.ui.so.m30.billui.view.SaleOrderBillListView();
		context.put("listView", bean);
		bean.setModel(getManageAppModel());
		bean.setMultiSelectionMode(0);
		bean.setTemplateContainer(getTemplateContainer());
		bean.setPaginationBar(getPageBar());
		bean.setUserdefitemListPreparator(getUserdefAndMarAsstListPreparator());
		bean.initUI();
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.pubapp.uif2app.view.CompositeBillListDataPrepare getUserdefAndMarAsstListPreparator() {
		if (context.get("userdefAndMarAsstListPreparator") != null)
			return (nc.ui.pubapp.uif2app.view.CompositeBillListDataPrepare) context
					.get("userdefAndMarAsstListPreparator");
		nc.ui.pubapp.uif2app.view.CompositeBillListDataPrepare bean = new nc.ui.pubapp.uif2app.view.CompositeBillListDataPrepare();
		context.put("userdefAndMarAsstListPreparator", bean);
		bean.setBillListDataPrepares(getManagedList2());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList2() {
		List list = new ArrayList();
		list.add(getUserdefitemlistPreparator());
		list.add(getMarAsstPreparator());
		return list;
	}

	public nc.ui.pubapp.uif2app.linkquery.LinkQueryHyperlinkMediator getLinkQueryHyperlinkMediator() {
		if (context.get("linkQueryHyperlinkMediator") != null)
			return (nc.ui.pubapp.uif2app.linkquery.LinkQueryHyperlinkMediator) context
					.get("linkQueryHyperlinkMediator");
		nc.ui.pubapp.uif2app.linkquery.LinkQueryHyperlinkMediator bean = new nc.ui.pubapp.uif2app.linkquery.LinkQueryHyperlinkMediator();
		context.put("linkQueryHyperlinkMediator", bean);
		bean.setModel(getManageAppModel());
		bean.setSrcBillIdField("cfirstid");
		bean.setSrcBillNOField("vfirstcode");
		bean.setSrcBillTypeField("vfirsttype");
		bean.setSrcBillTypeFieldPos(1);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.linkquery.LinkQueryHyperlinkMediator getAlinkQueryHyperlinkMediator() {
		if (context.get("alinkQueryHyperlinkMediator") != null)
			return (nc.ui.pubapp.uif2app.linkquery.LinkQueryHyperlinkMediator) context
					.get("alinkQueryHyperlinkMediator");
		nc.ui.pubapp.uif2app.linkquery.LinkQueryHyperlinkMediator bean = new nc.ui.pubapp.uif2app.linkquery.LinkQueryHyperlinkMediator();
		context.put("alinkQueryHyperlinkMediator", bean);
		bean.setModel(getManageAppModel());
		bean.setSrcBillIdField("csrcid");
		bean.setSrcBillNOField("vsrccode");
		bean.setSrcBillTypeField("vsrctype");
		bean.setSrcBillTypeFieldPos(1);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.linkquery.LinkQueryHyperlinkMediator getBlinkQueryHyperlinkMediator() {
		if (context.get("blinkQueryHyperlinkMediator") != null)
			return (nc.ui.pubapp.uif2app.linkquery.LinkQueryHyperlinkMediator) context
					.get("blinkQueryHyperlinkMediator");
		nc.ui.pubapp.uif2app.linkquery.LinkQueryHyperlinkMediator bean = new nc.ui.pubapp.uif2app.linkquery.LinkQueryHyperlinkMediator();
		context.put("blinkQueryHyperlinkMediator", bean);
		bean.setModel(getManageAppModel());
		bean.setSrcBillIdField("cctmanageid");
		bean.setSrcBillNOField("vctcode");
		bean.setSrcBillTypeField("vsrctype");
		bean.setSrcBillTypeFieldPos(1);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.so.m30.billui.view.SaleOrderBillForm getBillFormEditor() {
		if (context.get("billFormEditor") != null)
			return (nc.ui.so.m30.billui.view.SaleOrderBillForm) context
					.get("billFormEditor");
		nc.ui.so.m30.billui.view.SaleOrderBillForm bean = new nc.ui.so.m30.billui.view.SaleOrderBillForm();
		context.put("billFormEditor", bean);
		bean.setModel(getManageAppModel());
		bean.setTemplateContainer(getTemplateContainer());
		bean.setTemplateNotNullValidate(true);
		bean.setAutoAddLine(true);
		bean.setUserdefitemPreparator(getUserdefAndMarAsstCardPreparator());
		bean.setBlankChildrenFilter(getMultiFieldsBlankChildrenFilter_189cd39());
		bean.setClearHyperlink(getManagedList5());
		bean.setBodyLineActions(getManagedList6());
		bean.initUI();
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.pubapp.uif2app.view.CompositeBillDataPrepare getUserdefAndMarAsstCardPreparator() {
		if (context.get("userdefAndMarAsstCardPreparator") != null)
			return (nc.ui.pubapp.uif2app.view.CompositeBillDataPrepare) context
					.get("userdefAndMarAsstCardPreparator");
		nc.ui.pubapp.uif2app.view.CompositeBillDataPrepare bean = new nc.ui.pubapp.uif2app.view.CompositeBillDataPrepare();
		context.put("userdefAndMarAsstCardPreparator", bean);
		bean.setBillDataPrepares(getManagedList3());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList3() {
		List list = new ArrayList();
		list.add(getUserdefitemPreparator());
		list.add(getMarAsstPreparator());
		return list;
	}

	private nc.ui.pubapp.uif2app.view.value.MultiFieldsBlankChildrenFilter getMultiFieldsBlankChildrenFilter_189cd39() {
		if (context
				.get("nc.ui.pubapp.uif2app.view.value.MultiFieldsBlankChildrenFilter#189cd39") != null)
			return (nc.ui.pubapp.uif2app.view.value.MultiFieldsBlankChildrenFilter) context
					.get("nc.ui.pubapp.uif2app.view.value.MultiFieldsBlankChildrenFilter#189cd39");
		nc.ui.pubapp.uif2app.view.value.MultiFieldsBlankChildrenFilter bean = new nc.ui.pubapp.uif2app.view.value.MultiFieldsBlankChildrenFilter();
		context.put(
				"nc.ui.pubapp.uif2app.view.value.MultiFieldsBlankChildrenFilter#189cd39",
				bean);
		bean.setFilterMap(getManagedMap0());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private Map getManagedMap0() {
		Map map = new HashMap();
		map.put("bodytable1", getManagedList4());
		return map;
	}

	private List getManagedList4() {
		List list = new ArrayList();
		list.add("cmaterialvid");
		return list;
	}

	private List getManagedList5() {
		List list = new ArrayList();
		list.add("vbillcode");
		return list;
	}

	private List getManagedList6() {
		List list = new ArrayList();
		list.add(getBodyAddLineAction_8d43());
		list.add(getBodyInsertLineAction_3a4774());
		list.add(getSaleOrderReviseDelLineAction_294f5d());
		list.add(getBodyCopyLineAction_1966da());
		list.add(getSaleOrderRevisePasteLineAction_1af9b4b());
		list.add(getSaleOrderRevisePasteToTailAction_1fc736f());
		list.add(getSeparatorAction());
		list.add(getDefaultBodyZoomAction());
		return list;
	}

	private nc.ui.pubapp.uif2app.actions.BodyAddLineAction getBodyAddLineAction_8d43() {
		if (context.get("nc.ui.pubapp.uif2app.actions.BodyAddLineAction#8d43") != null)
			return (nc.ui.pubapp.uif2app.actions.BodyAddLineAction) context
					.get("nc.ui.pubapp.uif2app.actions.BodyAddLineAction#8d43");
		nc.ui.pubapp.uif2app.actions.BodyAddLineAction bean = new nc.ui.pubapp.uif2app.actions.BodyAddLineAction();
		context.put("nc.ui.pubapp.uif2app.actions.BodyAddLineAction#8d43", bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.pubapp.uif2app.actions.BodyInsertLineAction getBodyInsertLineAction_3a4774() {
		if (context
				.get("nc.ui.pubapp.uif2app.actions.BodyInsertLineAction#3a4774") != null)
			return (nc.ui.pubapp.uif2app.actions.BodyInsertLineAction) context
					.get("nc.ui.pubapp.uif2app.actions.BodyInsertLineAction#3a4774");
		nc.ui.pubapp.uif2app.actions.BodyInsertLineAction bean = new nc.ui.pubapp.uif2app.actions.BodyInsertLineAction();
		context.put("nc.ui.pubapp.uif2app.actions.BodyInsertLineAction#3a4774",
				bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.so.m30.revise.action.line.SaleOrderReviseDelLineAction getSaleOrderReviseDelLineAction_294f5d() {
		if (context
				.get("nc.ui.so.m30.revise.action.line.SaleOrderReviseDelLineAction#294f5d") != null)
			return (nc.ui.so.m30.revise.action.line.SaleOrderReviseDelLineAction) context
					.get("nc.ui.so.m30.revise.action.line.SaleOrderReviseDelLineAction#294f5d");
		nc.ui.so.m30.revise.action.line.SaleOrderReviseDelLineAction bean = new nc.ui.so.m30.revise.action.line.SaleOrderReviseDelLineAction();
		context.put(
				"nc.ui.so.m30.revise.action.line.SaleOrderReviseDelLineAction#294f5d",
				bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.pubapp.uif2app.actions.BodyCopyLineAction getBodyCopyLineAction_1966da() {
		if (context
				.get("nc.ui.pubapp.uif2app.actions.BodyCopyLineAction#1966da") != null)
			return (nc.ui.pubapp.uif2app.actions.BodyCopyLineAction) context
					.get("nc.ui.pubapp.uif2app.actions.BodyCopyLineAction#1966da");
		nc.ui.pubapp.uif2app.actions.BodyCopyLineAction bean = new nc.ui.pubapp.uif2app.actions.BodyCopyLineAction();
		context.put("nc.ui.pubapp.uif2app.actions.BodyCopyLineAction#1966da",
				bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.so.m30.revise.action.line.SaleOrderRevisePasteLineAction getSaleOrderRevisePasteLineAction_1af9b4b() {
		if (context
				.get("nc.ui.so.m30.revise.action.line.SaleOrderRevisePasteLineAction#1af9b4b") != null)
			return (nc.ui.so.m30.revise.action.line.SaleOrderRevisePasteLineAction) context
					.get("nc.ui.so.m30.revise.action.line.SaleOrderRevisePasteLineAction#1af9b4b");
		nc.ui.so.m30.revise.action.line.SaleOrderRevisePasteLineAction bean = new nc.ui.so.m30.revise.action.line.SaleOrderRevisePasteLineAction();
		context.put(
				"nc.ui.so.m30.revise.action.line.SaleOrderRevisePasteLineAction#1af9b4b",
				bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.so.m30.revise.action.line.SaleOrderRevisePasteToTailAction getSaleOrderRevisePasteToTailAction_1fc736f() {
		if (context
				.get("nc.ui.so.m30.revise.action.line.SaleOrderRevisePasteToTailAction#1fc736f") != null)
			return (nc.ui.so.m30.revise.action.line.SaleOrderRevisePasteToTailAction) context
					.get("nc.ui.so.m30.revise.action.line.SaleOrderRevisePasteToTailAction#1fc736f");
		nc.ui.so.m30.revise.action.line.SaleOrderRevisePasteToTailAction bean = new nc.ui.so.m30.revise.action.line.SaleOrderRevisePasteToTailAction();
		context.put(
				"nc.ui.so.m30.revise.action.line.SaleOrderRevisePasteToTailAction#1fc736f",
				bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.funcnode.ui.action.SeparatorAction getSeparatorAction() {
		if (context.get("separatorAction") != null)
			return (nc.funcnode.ui.action.SeparatorAction) context
					.get("separatorAction");
		nc.funcnode.ui.action.SeparatorAction bean = new nc.funcnode.ui.action.SeparatorAction();
		context.put("separatorAction", bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.actions.DefaultBodyZoomAction getDefaultBodyZoomAction() {
		if (context.get("DefaultBodyZoomAction") != null)
			return (nc.ui.pubapp.uif2app.actions.DefaultBodyZoomAction) context
					.get("DefaultBodyZoomAction");
		nc.ui.pubapp.uif2app.actions.DefaultBodyZoomAction bean = new nc.ui.pubapp.uif2app.actions.DefaultBodyZoomAction();
		context.put("DefaultBodyZoomAction", bean);
		bean.setPos(1);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.view.MouseClickShowPanelMediator getMouseClickShowPanelMediator() {
		if (context.get("mouseClickShowPanelMediator") != null)
			return (nc.ui.pubapp.uif2app.view.MouseClickShowPanelMediator) context
					.get("mouseClickShowPanelMediator");
		nc.ui.pubapp.uif2app.view.MouseClickShowPanelMediator bean = new nc.ui.pubapp.uif2app.view.MouseClickShowPanelMediator();
		context.put("mouseClickShowPanelMediator", bean);
		bean.setListView(getListView());
		bean.setShowUpComponent(getBillFormEditor());
		bean.setHyperLinkColumn("vbillcode");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor getFormInterceptor() {
		if (context.get("formInterceptor") != null)
			return (nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor) context
					.get("formInterceptor");
		nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor bean = new nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor();
		context.put("formInterceptor", bean);
		bean.setShowUpComponent(getBillFormEditor());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor getListInterceptor() {
		if (context.get("listInterceptor") != null)
			return (nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor) context
					.get("listInterceptor");
		nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor bean = new nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor();
		context.put("listInterceptor", bean);
		bean.setShowUpComponent(getListView());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.scmpub.listener.BillCodeEditMediator getBillCodeMediator() {
		if (context.get("billCodeMediator") != null)
			return (nc.ui.scmpub.listener.BillCodeEditMediator) context
					.get("billCodeMediator");
		nc.ui.scmpub.listener.BillCodeEditMediator bean = new nc.ui.scmpub.listener.BillCodeEditMediator();
		context.put("billCodeMediator", bean);
		bean.setBillCodeKey("vbillcode");
		bean.setBillType("30R");
		bean.setBillForm(getBillFormEditor());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.uif2.FunNodeClosingHandler getClosingListener() {
		if (context.get("ClosingListener") != null)
			return (nc.ui.uif2.FunNodeClosingHandler) context
					.get("ClosingListener");
		nc.ui.uif2.FunNodeClosingHandler bean = new nc.ui.uif2.FunNodeClosingHandler();
		context.put("ClosingListener", bean);
		bean.setModel(getManageAppModel());
		bean.setSaveaction(getSaveAction());
		bean.setCancelaction(getCancelAction());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.model.DefaultFuncNodeInitDataListener getInitDataListener() {
		if (context.get("InitDataListener") != null)
			return (nc.ui.pubapp.uif2app.model.DefaultFuncNodeInitDataListener) context
					.get("InitDataListener");
		nc.ui.pubapp.uif2app.model.DefaultFuncNodeInitDataListener bean = new nc.ui.pubapp.uif2app.model.DefaultFuncNodeInitDataListener();
		context.put("InitDataListener", bean);
		bean.setContext(getContext());
		bean.setModel(getManageAppModel());
		bean.setQueryAction(getQueryAction());
		bean.setVoClassName("nc.vo.so.m30.revise.entity.SaleOrderHistoryVO");
		bean.setAutoShowUpComponent(getBillFormEditor());
		bean.setProcessorMap(getManagedMap1());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private Map getManagedMap1() {
		Map map = new HashMap();
		map.put("0", getAddChildInitDataProcessor_fea468());
		return map;
	}

	private nc.ui.so.m30.revise.model.AddChildInitDataProcessor getAddChildInitDataProcessor_fea468() {
		if (context
				.get("nc.ui.so.m30.revise.model.AddChildInitDataProcessor#fea468") != null)
			return (nc.ui.so.m30.revise.model.AddChildInitDataProcessor) context
					.get("nc.ui.so.m30.revise.model.AddChildInitDataProcessor#fea468");
		nc.ui.so.m30.revise.model.AddChildInitDataProcessor bean = new nc.ui.so.m30.revise.model.AddChildInitDataProcessor();
		context.put(
				"nc.ui.so.m30.revise.model.AddChildInitDataProcessor#fea468",
				bean);
		bean.setModel(getManageAppModel());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.view.RowNoMediator getRowNoMediator() {
		if (context.get("rowNoMediator") != null)
			return (nc.ui.pubapp.uif2app.view.RowNoMediator) context
					.get("rowNoMediator");
		nc.ui.pubapp.uif2app.view.RowNoMediator bean = new nc.ui.pubapp.uif2app.view.RowNoMediator();
		context.put("rowNoMediator", bean);
		bean.setModel(getManageAppModel());
		bean.setEditor(getBillFormEditor());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.uif2.editor.UserdefitemContainerPreparator getUserdefitemPreparator() {
		if (context.get("userdefitemPreparator") != null)
			return (nc.ui.uif2.editor.UserdefitemContainerPreparator) context
					.get("userdefitemPreparator");
		nc.ui.uif2.editor.UserdefitemContainerPreparator bean = new nc.ui.uif2.editor.UserdefitemContainerPreparator();
		context.put("userdefitemPreparator", bean);
		bean.setContainer(getUserdefitemContainer());
		bean.setParams(getManagedList7());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList7() {
		List list = new ArrayList();
		list.add(getUserdefQueryParam_1dbe5e7());
		list.add(getUserdefQueryParam_1d2abe7());
		return list;
	}

	private nc.ui.uif2.editor.UserdefQueryParam getUserdefQueryParam_1dbe5e7() {
		if (context.get("nc.ui.uif2.editor.UserdefQueryParam#1dbe5e7") != null)
			return (nc.ui.uif2.editor.UserdefQueryParam) context
					.get("nc.ui.uif2.editor.UserdefQueryParam#1dbe5e7");
		nc.ui.uif2.editor.UserdefQueryParam bean = new nc.ui.uif2.editor.UserdefQueryParam();
		context.put("nc.ui.uif2.editor.UserdefQueryParam#1dbe5e7", bean);
		bean.setMdfullname("so.so_saleorder");
		bean.setPos(0);
		bean.setPrefix("vdef");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.uif2.editor.UserdefQueryParam getUserdefQueryParam_1d2abe7() {
		if (context.get("nc.ui.uif2.editor.UserdefQueryParam#1d2abe7") != null)
			return (nc.ui.uif2.editor.UserdefQueryParam) context
					.get("nc.ui.uif2.editor.UserdefQueryParam#1d2abe7");
		nc.ui.uif2.editor.UserdefQueryParam bean = new nc.ui.uif2.editor.UserdefQueryParam();
		context.put("nc.ui.uif2.editor.UserdefQueryParam#1d2abe7", bean);
		bean.setMdfullname("so.so_saleorder_b");
		bean.setPos(1);
		bean.setPrefix("vbdef");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.uif2.editor.UserdefitemBillListDataPreparator getUserdefitemlistPreparator() {
		if (context.get("userdefitemlistPreparator") != null)
			return (nc.ui.uif2.editor.UserdefitemBillListDataPreparator) context
					.get("userdefitemlistPreparator");
		nc.ui.uif2.editor.UserdefitemBillListDataPreparator bean = new nc.ui.uif2.editor.UserdefitemBillListDataPreparator(
				getContext());
		context.put("userdefitemlistPreparator", bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.view.material.assistant.MarAsstPreparator getMarAsstPreparator() {
		if (context.get("marAsstPreparator") != null)
			return (nc.ui.pubapp.uif2app.view.material.assistant.MarAsstPreparator) context
					.get("marAsstPreparator");
		nc.ui.pubapp.uif2app.view.material.assistant.MarAsstPreparator bean = new nc.ui.pubapp.uif2app.view.material.assistant.MarAsstPreparator();
		context.put("marAsstPreparator", bean);
		bean.setModel(getManageAppModel());
		bean.setContainer(getUserdefitemContainer());
		bean.setPrefix("vfree");
		bean.setMaterialField("cmaterialid");
		bean.setProjectField("cprojectid");
		bean.setSupplierField("cvendorid");
		bean.setProductorField("cproductorid");
		bean.setSignatureField("cmffileid");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.uif2.userdefitem.UserDefItemContainer getUserdefitemContainer() {
		if (context.get("userdefitemContainer") != null)
			return (nc.ui.uif2.userdefitem.UserDefItemContainer) context
					.get("userdefitemContainer");
		nc.ui.uif2.userdefitem.UserDefItemContainer bean = new nc.ui.uif2.userdefitem.UserDefItemContainer();
		context.put("userdefitemContainer", bean);
		bean.setContext(getContext());
		bean.setParams(getManagedList8());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList8() {
		List list = new ArrayList();
		list.add(getQueryParam_13d6e9());
		list.add(getQueryParam_825f23());
		list.add(getQueryParam_1341b19());
		return list;
	}

	private nc.ui.uif2.userdefitem.QueryParam getQueryParam_13d6e9() {
		if (context.get("nc.ui.uif2.userdefitem.QueryParam#13d6e9") != null)
			return (nc.ui.uif2.userdefitem.QueryParam) context
					.get("nc.ui.uif2.userdefitem.QueryParam#13d6e9");
		nc.ui.uif2.userdefitem.QueryParam bean = new nc.ui.uif2.userdefitem.QueryParam();
		context.put("nc.ui.uif2.userdefitem.QueryParam#13d6e9", bean);
		bean.setMdfullname("so.orderhistory");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.uif2.userdefitem.QueryParam getQueryParam_825f23() {
		if (context.get("nc.ui.uif2.userdefitem.QueryParam#825f23") != null)
			return (nc.ui.uif2.userdefitem.QueryParam) context
					.get("nc.ui.uif2.userdefitem.QueryParam#825f23");
		nc.ui.uif2.userdefitem.QueryParam bean = new nc.ui.uif2.userdefitem.QueryParam();
		context.put("nc.ui.uif2.userdefitem.QueryParam#825f23", bean);
		bean.setMdfullname("so.orderhistory_b");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.uif2.userdefitem.QueryParam getQueryParam_1341b19() {
		if (context.get("nc.ui.uif2.userdefitem.QueryParam#1341b19") != null)
			return (nc.ui.uif2.userdefitem.QueryParam) context
					.get("nc.ui.uif2.userdefitem.QueryParam#1341b19");
		nc.ui.uif2.userdefitem.QueryParam bean = new nc.ui.uif2.userdefitem.QueryParam();
		context.put("nc.ui.uif2.userdefitem.QueryParam#1341b19", bean);
		bean.setRulecode("materialassistant");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.view.FractionFixMediator getFractionFixMediator() {
		if (context.get("fractionFixMediator") != null)
			return (nc.ui.pubapp.uif2app.view.FractionFixMediator) context
					.get("fractionFixMediator");
		nc.ui.pubapp.uif2app.view.FractionFixMediator bean = new nc.ui.pubapp.uif2app.view.FractionFixMediator(
				getManagedList9(), getManagedList10());
		context.put("fractionFixMediator", bean);
		bean.initUI();
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList9() {
		List list = new ArrayList();
		list.add(getBillFormEditor());
		return list;
	}

	private List getManagedList10() {
		List list = new ArrayList();
		list.add(getListView());
		return list;
	}

	public nc.ui.pubapp.uif2app.model.BillBodySortMediator getBillBodySortMediator() {
		if (context.get("billBodySortMediator") != null)
			return (nc.ui.pubapp.uif2app.model.BillBodySortMediator) context
					.get("billBodySortMediator");
		nc.ui.pubapp.uif2app.model.BillBodySortMediator bean = new nc.ui.pubapp.uif2app.model.BillBodySortMediator(
				getManageAppModel(), getBillFormEditor(), getListView());
		context.put("billBodySortMediator", bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.model.AppEventHandlerMediator getAppEventHandlerMediator() {
		if (context.get("appEventHandlerMediator") != null)
			return (nc.ui.pubapp.uif2app.model.AppEventHandlerMediator) context
					.get("appEventHandlerMediator");
		nc.ui.pubapp.uif2app.model.AppEventHandlerMediator bean = new nc.ui.pubapp.uif2app.model.AppEventHandlerMediator();
		context.put("appEventHandlerMediator", bean);
		bean.setModel(getManageAppModel());
		bean.setHandlerMap(getManagedMap2());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private Map getManagedMap2() {
		Map map = new HashMap();
		map.put("nc.ui.pubapp.uif2app.event.card.CardHeadTailBeforeEditEvent",
				getManagedList11());
		map.put("nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent",
				getManagedList12());
		map.put("nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent",
				getManagedList13());
		map.put("nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent",
				getManagedList14());
		map.put("nc.ui.pubapp.uif2app.event.card.CardBodyAfterRowEditEvent",
				getManagedList15());
		map.put("nc.ui.pubapp.uif2app.event.OrgChangedEvent",
				getManagedList16());
		return map;
	}

	private List getManagedList11() {
		List list = new ArrayList();
		list.add(getReviseHeadBeforeEditHandler_1df2ff5());
		return list;
	}

	private nc.ui.so.m30.revise.editor.headevent.ReviseHeadBeforeEditHandler getReviseHeadBeforeEditHandler_1df2ff5() {
		if (context
				.get("nc.ui.so.m30.revise.editor.headevent.ReviseHeadBeforeEditHandler#1df2ff5") != null)
			return (nc.ui.so.m30.revise.editor.headevent.ReviseHeadBeforeEditHandler) context
					.get("nc.ui.so.m30.revise.editor.headevent.ReviseHeadBeforeEditHandler#1df2ff5");
		nc.ui.so.m30.revise.editor.headevent.ReviseHeadBeforeEditHandler bean = new nc.ui.so.m30.revise.editor.headevent.ReviseHeadBeforeEditHandler();
		context.put(
				"nc.ui.so.m30.revise.editor.headevent.ReviseHeadBeforeEditHandler#1df2ff5",
				bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList12() {
		List list = new ArrayList();
		list.add(getHeadAfterEditHandler_1c5986e());
		return list;
	}

	private nc.ui.so.m30.billui.editor.headevent.HeadAfterEditHandler getHeadAfterEditHandler_1c5986e() {
		if (context
				.get("nc.ui.so.m30.billui.editor.headevent.HeadAfterEditHandler#1c5986e") != null)
			return (nc.ui.so.m30.billui.editor.headevent.HeadAfterEditHandler) context
					.get("nc.ui.so.m30.billui.editor.headevent.HeadAfterEditHandler#1c5986e");
		nc.ui.so.m30.billui.editor.headevent.HeadAfterEditHandler bean = new nc.ui.so.m30.billui.editor.headevent.HeadAfterEditHandler();
		context.put(
				"nc.ui.so.m30.billui.editor.headevent.HeadAfterEditHandler#1c5986e",
				bean);
		bean.setBillform(getBillFormEditor());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList13() {
		List list = new ArrayList();
		list.add(getReviseBodyBeforeEditHandler_19a5eaa());
		return list;
	}

	private nc.ui.so.m30.revise.editor.bodyevent.ReviseBodyBeforeEditHandler getReviseBodyBeforeEditHandler_19a5eaa() {
		if (context
				.get("nc.ui.so.m30.revise.editor.bodyevent.ReviseBodyBeforeEditHandler#19a5eaa") != null)
			return (nc.ui.so.m30.revise.editor.bodyevent.ReviseBodyBeforeEditHandler) context
					.get("nc.ui.so.m30.revise.editor.bodyevent.ReviseBodyBeforeEditHandler#19a5eaa");
		nc.ui.so.m30.revise.editor.bodyevent.ReviseBodyBeforeEditHandler bean = new nc.ui.so.m30.revise.editor.bodyevent.ReviseBodyBeforeEditHandler();
		context.put(
				"nc.ui.so.m30.revise.editor.bodyevent.ReviseBodyBeforeEditHandler#19a5eaa",
				bean);
		bean.setBillform(getBillFormEditor());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList14() {
		List list = new ArrayList();
		list.add(getReviseBodyAfterEditHandler_1bd15c7());
		return list;
	}

	private nc.ui.so.m30.revise.editor.bodyevent.ReviseBodyAfterEditHandler getReviseBodyAfterEditHandler_1bd15c7() {
		if (context
				.get("nc.ui.so.m30.revise.editor.bodyevent.ReviseBodyAfterEditHandler#1bd15c7") != null)
			return (nc.ui.so.m30.revise.editor.bodyevent.ReviseBodyAfterEditHandler) context
					.get("nc.ui.so.m30.revise.editor.bodyevent.ReviseBodyAfterEditHandler#1bd15c7");
		nc.ui.so.m30.revise.editor.bodyevent.ReviseBodyAfterEditHandler bean = new nc.ui.so.m30.revise.editor.bodyevent.ReviseBodyAfterEditHandler();
		context.put(
				"nc.ui.so.m30.revise.editor.bodyevent.ReviseBodyAfterEditHandler#1bd15c7",
				bean);
		bean.setBillform(getBillFormEditor());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList15() {
		List list = new ArrayList();
		list.add(getCardBodyAfterRowEditHandler_e0da74());
		return list;
	}

	private nc.ui.so.m30.billui.editor.bodyevent.CardBodyAfterRowEditHandler getCardBodyAfterRowEditHandler_e0da74() {
		if (context
				.get("nc.ui.so.m30.billui.editor.bodyevent.CardBodyAfterRowEditHandler#e0da74") != null)
			return (nc.ui.so.m30.billui.editor.bodyevent.CardBodyAfterRowEditHandler) context
					.get("nc.ui.so.m30.billui.editor.bodyevent.CardBodyAfterRowEditHandler#e0da74");
		nc.ui.so.m30.billui.editor.bodyevent.CardBodyAfterRowEditHandler bean = new nc.ui.so.m30.billui.editor.bodyevent.CardBodyAfterRowEditHandler();
		context.put(
				"nc.ui.so.m30.billui.editor.bodyevent.CardBodyAfterRowEditHandler#e0da74",
				bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList16() {
		List list = new ArrayList();
		list.add(getOrgEditHandler_2634b2());
		return list;
	}

	private nc.ui.so.m30.billui.editor.orgevent.OrgEditHandler getOrgEditHandler_2634b2() {
		if (context
				.get("nc.ui.so.m30.billui.editor.orgevent.OrgEditHandler#2634b2") != null)
			return (nc.ui.so.m30.billui.editor.orgevent.OrgEditHandler) context
					.get("nc.ui.so.m30.billui.editor.orgevent.OrgEditHandler#2634b2");
		nc.ui.so.m30.billui.editor.orgevent.OrgEditHandler bean = new nc.ui.so.m30.billui.editor.orgevent.OrgEditHandler(
				getBillFormEditor(), getContext());
		context.put(
				"nc.ui.so.m30.billui.editor.orgevent.OrgEditHandler#2634b2",
				bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.uif2.TangramContainer getContainer() {
		if (context.get("container") != null)
			return (nc.ui.uif2.TangramContainer) context.get("container");
		nc.ui.uif2.TangramContainer bean = new nc.ui.uif2.TangramContainer();
		context.put("container", bean);
		bean.setModel(getManageAppModel());
		bean.setTangramLayoutRoot(getTBNode_919e09());
		bean.initUI();
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.uif2.tangramlayout.node.TBNode getTBNode_919e09() {
		if (context.get("nc.ui.uif2.tangramlayout.node.TBNode#919e09") != null)
			return (nc.ui.uif2.tangramlayout.node.TBNode) context
					.get("nc.ui.uif2.tangramlayout.node.TBNode#919e09");
		nc.ui.uif2.tangramlayout.node.TBNode bean = new nc.ui.uif2.tangramlayout.node.TBNode();
		context.put("nc.ui.uif2.tangramlayout.node.TBNode#919e09", bean);
		bean.setShowMode("CardLayout");
		bean.setTabs(getManagedList17());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList17() {
		List list = new ArrayList();
		list.add(getHSNode_f08a06());
		list.add(getHSNode_932d85());
		return list;
	}

	private nc.ui.uif2.tangramlayout.node.HSNode getHSNode_f08a06() {
		if (context.get("nc.ui.uif2.tangramlayout.node.HSNode#f08a06") != null)
			return (nc.ui.uif2.tangramlayout.node.HSNode) context
					.get("nc.ui.uif2.tangramlayout.node.HSNode#f08a06");
		nc.ui.uif2.tangramlayout.node.HSNode bean = new nc.ui.uif2.tangramlayout.node.HSNode();
		context.put("nc.ui.uif2.tangramlayout.node.HSNode#f08a06", bean);
		bean.setLeft(getCNode_4db305());
		bean.setRight(getVSNode_15136fa());
		bean.setDividerLocation(0.22f);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.uif2.tangramlayout.node.CNode getCNode_4db305() {
		if (context.get("nc.ui.uif2.tangramlayout.node.CNode#4db305") != null)
			return (nc.ui.uif2.tangramlayout.node.CNode) context
					.get("nc.ui.uif2.tangramlayout.node.CNode#4db305");
		nc.ui.uif2.tangramlayout.node.CNode bean = new nc.ui.uif2.tangramlayout.node.CNode();
		context.put("nc.ui.uif2.tangramlayout.node.CNode#4db305", bean);
		bean.setComponent(getQueryArea());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.uif2.tangramlayout.node.VSNode getVSNode_15136fa() {
		if (context.get("nc.ui.uif2.tangramlayout.node.VSNode#15136fa") != null)
			return (nc.ui.uif2.tangramlayout.node.VSNode) context
					.get("nc.ui.uif2.tangramlayout.node.VSNode#15136fa");
		nc.ui.uif2.tangramlayout.node.VSNode bean = new nc.ui.uif2.tangramlayout.node.VSNode();
		context.put("nc.ui.uif2.tangramlayout.node.VSNode#15136fa", bean);
		bean.setUp(getCNode_1241392());
		bean.setDown(getCNode_30934());
		bean.setDividerLocation(25f);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.uif2.tangramlayout.node.CNode getCNode_1241392() {
		if (context.get("nc.ui.uif2.tangramlayout.node.CNode#1241392") != null)
			return (nc.ui.uif2.tangramlayout.node.CNode) context
					.get("nc.ui.uif2.tangramlayout.node.CNode#1241392");
		nc.ui.uif2.tangramlayout.node.CNode bean = new nc.ui.uif2.tangramlayout.node.CNode();
		context.put("nc.ui.uif2.tangramlayout.node.CNode#1241392", bean);
		bean.setComponent(getListToolbarPnl());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.uif2.tangramlayout.node.CNode getCNode_30934() {
		if (context.get("nc.ui.uif2.tangramlayout.node.CNode#30934") != null)
			return (nc.ui.uif2.tangramlayout.node.CNode) context
					.get("nc.ui.uif2.tangramlayout.node.CNode#30934");
		nc.ui.uif2.tangramlayout.node.CNode bean = new nc.ui.uif2.tangramlayout.node.CNode();
		context.put("nc.ui.uif2.tangramlayout.node.CNode#30934", bean);
		bean.setName(getI18nFB_180f7a7());
		bean.setComponent(getListView());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private java.lang.String getI18nFB_180f7a7() {
		if (context.get("nc.ui.uif2.I18nFB#180f7a7") != null)
			return (java.lang.String) context.get("nc.ui.uif2.I18nFB#180f7a7");
		nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
		context.put("&nc.ui.uif2.I18nFB#180f7a7", bean);
		bean.setResDir("common");
		bean.setResId("UC001-0000107");
		bean.setDefaultValue("列表");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		try {
			Object product = bean.getObject();
			context.put("nc.ui.uif2.I18nFB#180f7a7", product);
			return (java.lang.String) product;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private nc.ui.uif2.tangramlayout.node.HSNode getHSNode_932d85() {
		if (context.get("nc.ui.uif2.tangramlayout.node.HSNode#932d85") != null)
			return (nc.ui.uif2.tangramlayout.node.HSNode) context
					.get("nc.ui.uif2.tangramlayout.node.HSNode#932d85");
		nc.ui.uif2.tangramlayout.node.HSNode bean = new nc.ui.uif2.tangramlayout.node.HSNode();
		context.put("nc.ui.uif2.tangramlayout.node.HSNode#932d85", bean);
		bean.setLeft(getVSNode_4e59a3());
		bean.setRight(getCNode_1deece6());
		bean.setDividerLocation(0.8f);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.uif2.tangramlayout.node.VSNode getVSNode_4e59a3() {
		if (context.get("nc.ui.uif2.tangramlayout.node.VSNode#4e59a3") != null)
			return (nc.ui.uif2.tangramlayout.node.VSNode) context
					.get("nc.ui.uif2.tangramlayout.node.VSNode#4e59a3");
		nc.ui.uif2.tangramlayout.node.VSNode bean = new nc.ui.uif2.tangramlayout.node.VSNode();
		context.put("nc.ui.uif2.tangramlayout.node.VSNode#4e59a3", bean);
		bean.setUp(getCNode_1f5c5bd());
		bean.setDown(getCNode_360435());
		bean.setDividerLocation(30f);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.uif2.tangramlayout.node.CNode getCNode_1f5c5bd() {
		if (context.get("nc.ui.uif2.tangramlayout.node.CNode#1f5c5bd") != null)
			return (nc.ui.uif2.tangramlayout.node.CNode) context
					.get("nc.ui.uif2.tangramlayout.node.CNode#1f5c5bd");
		nc.ui.uif2.tangramlayout.node.CNode bean = new nc.ui.uif2.tangramlayout.node.CNode();
		context.put("nc.ui.uif2.tangramlayout.node.CNode#1f5c5bd", bean);
		bean.setComponent(getCardToolbarPnl());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.uif2.tangramlayout.node.CNode getCNode_360435() {
		if (context.get("nc.ui.uif2.tangramlayout.node.CNode#360435") != null)
			return (nc.ui.uif2.tangramlayout.node.CNode) context
					.get("nc.ui.uif2.tangramlayout.node.CNode#360435");
		nc.ui.uif2.tangramlayout.node.CNode bean = new nc.ui.uif2.tangramlayout.node.CNode();
		context.put("nc.ui.uif2.tangramlayout.node.CNode#360435", bean);
		bean.setComponent(getBillFormEditor());
		bean.setName(getI18nFB_88ff60());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private java.lang.String getI18nFB_88ff60() {
		if (context.get("nc.ui.uif2.I18nFB#88ff60") != null)
			return (java.lang.String) context.get("nc.ui.uif2.I18nFB#88ff60");
		nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
		context.put("&nc.ui.uif2.I18nFB#88ff60", bean);
		bean.setResDir("common");
		bean.setResId("UC001-0000106");
		bean.setDefaultValue("卡片");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		try {
			Object product = bean.getObject();
			context.put("nc.ui.uif2.I18nFB#88ff60", product);
			return (java.lang.String) product;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private nc.ui.uif2.tangramlayout.node.CNode getCNode_1deece6() {
		if (context.get("nc.ui.uif2.tangramlayout.node.CNode#1deece6") != null)
			return (nc.ui.uif2.tangramlayout.node.CNode) context
					.get("nc.ui.uif2.tangramlayout.node.CNode#1deece6");
		nc.ui.uif2.tangramlayout.node.CNode bean = new nc.ui.uif2.tangramlayout.node.CNode();
		context.put("nc.ui.uif2.tangramlayout.node.CNode#1deece6", bean);
		bean.setComponent(getBesidewidget());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.tangramlayout.UEQueryAreaShell getQueryArea() {
		if (context.get("queryArea") != null)
			return (nc.ui.pubapp.uif2app.tangramlayout.UEQueryAreaShell) context
					.get("queryArea");
		nc.ui.pubapp.uif2app.tangramlayout.UEQueryAreaShell bean = new nc.ui.pubapp.uif2app.tangramlayout.UEQueryAreaShell();
		context.put("queryArea", bean);
		bean.setQueryAreaCreator(getQueryAction());
		bean.initUI();
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.uif2.tangramlayout.CardLayoutToolbarPanel getListToolbarPnl() {
		if (context.get("listToolbarPnl") != null)
			return (nc.ui.uif2.tangramlayout.CardLayoutToolbarPanel) context
					.get("listToolbarPnl");
		nc.ui.uif2.tangramlayout.CardLayoutToolbarPanel bean = new nc.ui.uif2.tangramlayout.CardLayoutToolbarPanel();
		context.put("listToolbarPnl", bean);
		bean.setModel(getManageAppModel());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.so.m30.billui.view.SaleOrderUECardLayoutToolbarPanel getCardToolbarPnl() {
		if (context.get("cardToolbarPnl") != null)
			return (nc.ui.so.m30.billui.view.SaleOrderUECardLayoutToolbarPanel) context
					.get("cardToolbarPnl");
		nc.ui.so.m30.billui.view.SaleOrderUECardLayoutToolbarPanel bean = new nc.ui.so.m30.billui.view.SaleOrderUECardLayoutToolbarPanel();
		context.put("cardToolbarPnl", bean);
		bean.setActions(getManagedList18());
		bean.setTitleAction(getReturnaction());
		bean.setModel(getManageAppModel());
		bean.setRightExActions(getManagedList19());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList18() {
		List list = new ArrayList();
		list.add(getFirstLineAction());
		list.add(getPreLineAction());
		list.add(getNextLineAction());
		list.add(getLastLineAction());
		return list;
	}

	private nc.ui.uif2.actions.FirstLineAction getFirstLineAction() {
		if (context.get("firstLineAction") != null)
			return (nc.ui.uif2.actions.FirstLineAction) context
					.get("firstLineAction");
		nc.ui.uif2.actions.FirstLineAction bean = new nc.ui.uif2.actions.FirstLineAction();
		context.put("firstLineAction", bean);
		bean.setModel(getManageAppModel());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.uif2.actions.PreLineAction getPreLineAction() {
		if (context.get("preLineAction") != null)
			return (nc.ui.uif2.actions.PreLineAction) context
					.get("preLineAction");
		nc.ui.uif2.actions.PreLineAction bean = new nc.ui.uif2.actions.PreLineAction();
		context.put("preLineAction", bean);
		bean.setModel(getManageAppModel());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.uif2.actions.NextLineAction getNextLineAction() {
		if (context.get("nextLineAction") != null)
			return (nc.ui.uif2.actions.NextLineAction) context
					.get("nextLineAction");
		nc.ui.uif2.actions.NextLineAction bean = new nc.ui.uif2.actions.NextLineAction();
		context.put("nextLineAction", bean);
		bean.setModel(getManageAppModel());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.uif2.actions.LastLineAction getLastLineAction() {
		if (context.get("lastLineAction") != null)
			return (nc.ui.uif2.actions.LastLineAction) context
					.get("lastLineAction");
		nc.ui.uif2.actions.LastLineAction bean = new nc.ui.uif2.actions.LastLineAction();
		context.put("lastLineAction", bean);
		bean.setModel(getManageAppModel());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.pubapp.uif2app.actions.UEReturnAction getReturnaction() {
		if (context.get("returnaction") != null)
			return (nc.ui.pubapp.uif2app.actions.UEReturnAction) context
					.get("returnaction");
		nc.ui.pubapp.uif2app.actions.UEReturnAction bean = new nc.ui.pubapp.uif2app.actions.UEReturnAction();
		context.put("returnaction", bean);
		bean.setGoComponent(getListView());
		bean.setSaveAction(getSaveAction());
		bean.setModel(getManageAppModel());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList19() {
		List list = new ArrayList();
		list.add(getActionsBar_ActionsBarSeparator_1e8b38());
		list.add(getHeadZoomAction());
		return list;
	}

	private nc.ui.pub.beans.ActionsBar.ActionsBarSeparator getActionsBar_ActionsBarSeparator_1e8b38() {
		if (context
				.get("nc.ui.pub.beans.ActionsBar.ActionsBarSeparator#1e8b38") != null)
			return (nc.ui.pub.beans.ActionsBar.ActionsBarSeparator) context
					.get("nc.ui.pub.beans.ActionsBar.ActionsBarSeparator#1e8b38");
		nc.ui.pub.beans.ActionsBar.ActionsBarSeparator bean = new nc.ui.pub.beans.ActionsBar.ActionsBarSeparator();
		context.put("nc.ui.pub.beans.ActionsBar.ActionsBarSeparator#1e8b38",
				bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.pubapp.uif2app.actions.DefaultHeadZoomAction getHeadZoomAction() {
		if (context.get("headZoomAction") != null)
			return (nc.ui.pubapp.uif2app.actions.DefaultHeadZoomAction) context
					.get("headZoomAction");
		nc.ui.pubapp.uif2app.actions.DefaultHeadZoomAction bean = new nc.ui.pubapp.uif2app.actions.DefaultHeadZoomAction();
		context.put("headZoomAction", bean);
		bean.setBillForm(getBillFormEditor());
		bean.setModel(getManageAppModel());
		bean.setPos(0);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.uif2.components.widget.BesideWidget getBesidewidget() {
		if (context.get("besidewidget") != null)
			return (nc.ui.uif2.components.widget.BesideWidget) context
					.get("besidewidget");
		nc.ui.uif2.components.widget.BesideWidget bean = new nc.ui.uif2.components.widget.BesideWidget();
		context.put("besidewidget", bean);
		bean.setBesideWidgetlets(getManagedList20());
		bean.setContext(getContext());
		bean.initUI();
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList20() {
		List list = new ArrayList();
		list.add(getCreditSideForm());
		list.add(getPaySideForm());
		list.add(getATPSideForm());
		return list;
	}

	public nc.ui.so.m30.billui.view.sideform.CreditSideForm getCreditSideForm() {
		if (context.get("creditSideForm") != null)
			return (nc.ui.so.m30.billui.view.sideform.CreditSideForm) context
					.get("creditSideForm");
		nc.ui.so.m30.billui.view.sideform.CreditSideForm bean = new nc.ui.so.m30.billui.view.sideform.CreditSideForm();
		context.put("creditSideForm", bean);
		bean.setBillForm(getBillFormEditor());
		bean.setExceptionHandler(getExceptionHandler());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.so.m30.billui.view.sideform.PaySideForm getPaySideForm() {
		if (context.get("paySideForm") != null)
			return (nc.ui.so.m30.billui.view.sideform.PaySideForm) context
					.get("paySideForm");
		nc.ui.so.m30.billui.view.sideform.PaySideForm bean = new nc.ui.so.m30.billui.view.sideform.PaySideForm();
		context.put("paySideForm", bean);
		bean.setBillForm(getBillFormEditor());
		bean.setExceptionHandler(getExceptionHandler());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.so.m30.billui.view.sideform.ATPSideForm getATPSideForm() {
		if (context.get("ATPSideForm") != null)
			return (nc.ui.so.m30.billui.view.sideform.ATPSideForm) context
					.get("ATPSideForm");
		nc.ui.so.m30.billui.view.sideform.ATPSideForm bean = new nc.ui.so.m30.billui.view.sideform.ATPSideForm();
		context.put("ATPSideForm", bean);
		bean.setBillForm(getBillFormEditor());
		bean.setExceptionHandler(getExceptionHandler());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.so.m30.billui.view.sideform.SaleOrderSideFormMediator getSideFormMediator() {
		if (context.get("sideFormMediator") != null)
			return (nc.ui.so.m30.billui.view.sideform.SaleOrderSideFormMediator) context
					.get("sideFormMediator");
		nc.ui.so.m30.billui.view.sideform.SaleOrderSideFormMediator bean = new nc.ui.so.m30.billui.view.sideform.SaleOrderSideFormMediator();
		context.put("sideFormMediator", bean);
		bean.setModel(getManageAppModel());
		bean.setCreditSideForm(getCreditSideForm());
		bean.setPaySideForm(getPaySideForm());
		bean.setAtpSideForm(getATPSideForm());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.uif2.actions.ActionContributors getToftpanelActionContributors() {
		if (context.get("toftpanelActionContributors") != null)
			return (nc.ui.uif2.actions.ActionContributors) context
					.get("toftpanelActionContributors");
		nc.ui.uif2.actions.ActionContributors bean = new nc.ui.uif2.actions.ActionContributors();
		context.put("toftpanelActionContributors", bean);
		bean.setContributors(getManagedList21());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList21() {
		List list = new ArrayList();
		list.add(getActionsOfList());
		list.add(getActionsOfCard());
		return list;
	}

	public nc.ui.uif2.actions.StandAloneToftPanelActionContainer getActionsOfList() {
		if (context.get("actionsOfList") != null)
			return (nc.ui.uif2.actions.StandAloneToftPanelActionContainer) context
					.get("actionsOfList");
		nc.ui.uif2.actions.StandAloneToftPanelActionContainer bean = new nc.ui.uif2.actions.StandAloneToftPanelActionContainer(
				getListView());
		context.put("actionsOfList", bean);
		bean.setActions(getManagedList22());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList22() {
		List list = new ArrayList();
		list.add(getReviseAction());
		list.add(getReviseEditAction());
		list.add(getSeparatorAction());
		list.add(getDeleteAction());
		list.add(getSeparatorAction());
		list.add(getQueryAction());
		list.add(getListRefreshAction());
		list.add(getSeparatorAction());
		list.add(getSendActionGroup());
		list.add(getApproveActionGroup());
		list.add(getSeparatorAction());
		list.add(getActionGroupgt3());
		list.add(getSeparatorAction());
		list.add(getActionGroupgt4());
		list.add(getSeparatorAction());
		list.add(getActionGroupgt5());
		return list;
	}

	public nc.ui.uif2.actions.StandAloneToftPanelActionContainer getActionsOfCard() {
		if (context.get("actionsOfCard") != null)
			return (nc.ui.uif2.actions.StandAloneToftPanelActionContainer) context
					.get("actionsOfCard");
		nc.ui.uif2.actions.StandAloneToftPanelActionContainer bean = new nc.ui.uif2.actions.StandAloneToftPanelActionContainer(
				getBillFormEditor());
		context.put("actionsOfCard", bean);
		bean.setActions(getManagedList23());
		bean.setEditActions(getManagedList24());
		bean.setModel(getManageAppModel());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList23() {
		List list = new ArrayList();
		list.add(getReviseAction());
		list.add(getReviseEditAction());
		list.add(getSeparatorAction());
		list.add(getDeleteAction());
		list.add(getSeparatorAction());
		list.add(getQueryAction());
		list.add(getCardRefreshAction());
		list.add(getSeparatorAction());
		list.add(getSendActionGroup());
		list.add(getApproveActionGroup());
		list.add(getSeparatorAction());
		list.add(getActionGroupgt3());
		list.add(getSeparatorAction());
		list.add(getActionGroupgt4());
		list.add(getSeparatorAction());
		list.add(getActionGroupgt5());
		return list;
	}

	private List getManagedList24() {
		List list = new ArrayList();
		list.add(getM30RSaveForEditAction());
		list.add(getSaveAction());
		list.add(getSeparatorAction());
		list.add(getCancelAction());
		list.add(getSeparatorAction());
		list.add(getActionGroupgt6());
		list.add(getSeparatorAction());
		list.add(getActionGroupgt7());
		return list;
	}

	public nc.ui.pubapp.uif2app.actions.AddMenuAction getAddMenu() {
		if (context.get("addMenu") != null)
			return (nc.ui.pubapp.uif2app.actions.AddMenuAction) context
					.get("addMenu");
		nc.ui.pubapp.uif2app.actions.AddMenuAction bean = new nc.ui.pubapp.uif2app.actions.AddMenuAction();
		context.put("addMenu", bean);
		bean.setBillType("30R");
		bean.setActions(getManagedList25());
		bean.setModel(getManageAppModel());
		bean.initUI();
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList25() {
		List list = new ArrayList();
		list.add(getAddManualAction());
		list.add(getAdd2AAction());
		list.add(getAdd4310Action());
		list.add(getAdd38Action());
		list.add(getAdd4HAction());
		return list;
	}

	public nc.funcnode.ui.action.MenuAction getActionGroupgt3() {
		if (context.get("actionGroupgt3") != null)
			return (nc.funcnode.ui.action.MenuAction) context
					.get("actionGroupgt3");
		nc.funcnode.ui.action.MenuAction bean = new nc.funcnode.ui.action.MenuAction();
		context.put("actionGroupgt3", bean);
		bean.setCode("gt3");
		bean.setName(getI18nFB_14635b3());
		bean.setActions(getManagedList26());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private java.lang.String getI18nFB_14635b3() {
		if (context.get("nc.ui.uif2.I18nFB#14635b3") != null)
			return (java.lang.String) context.get("nc.ui.uif2.I18nFB#14635b3");
		nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
		context.put("&nc.ui.uif2.I18nFB#14635b3", bean);
		bean.setResDir("4006011_0");
		bean.setResId("04006011-0400");
		bean.setDefaultValue("辅助功能");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		try {
			Object product = bean.getObject();
			context.put("nc.ui.uif2.I18nFB#14635b3", product);
			return (java.lang.String) product;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private List getManagedList26() {
		List list = new ArrayList();
		list.add(getTransInfoAction());
		list.add(getPriceFormAction());
		list.add(getSeparatorAction());
		list.add(getDocManageAction());
		return list;
	}

	public nc.funcnode.ui.action.MenuAction getImportExportActionGroup() {
		if (context.get("importExportActionGroup") != null)
			return (nc.funcnode.ui.action.MenuAction) context
					.get("importExportActionGroup");
		nc.funcnode.ui.action.MenuAction bean = new nc.funcnode.ui.action.MenuAction();
		context.put("importExportActionGroup", bean);
		bean.setCode("importExport");
		bean.setName(getI18nFB_6fb3d7());
		bean.setTooltip(getI18nFB_1881ed8());
		bean.setActions(getManagedList27());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private java.lang.String getI18nFB_6fb3d7() {
		if (context.get("nc.ui.uif2.I18nFB#6fb3d7") != null)
			return (java.lang.String) context.get("nc.ui.uif2.I18nFB#6fb3d7");
		nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
		context.put("&nc.ui.uif2.I18nFB#6fb3d7", bean);
		bean.setResDir("4006011_0");
		bean.setResId("04006011-0466");
		bean.setDefaultValue("导入导出");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		try {
			Object product = bean.getObject();
			context.put("nc.ui.uif2.I18nFB#6fb3d7", product);
			return (java.lang.String) product;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private java.lang.String getI18nFB_1881ed8() {
		if (context.get("nc.ui.uif2.I18nFB#1881ed8") != null)
			return (java.lang.String) context.get("nc.ui.uif2.I18nFB#1881ed8");
		nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
		context.put("&nc.ui.uif2.I18nFB#1881ed8", bean);
		bean.setResDir("4006011_0");
		bean.setResId("04006011-0466");
		bean.setDefaultValue("导入导出");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		try {
			Object product = bean.getObject();
			context.put("nc.ui.uif2.I18nFB#1881ed8", product);
			return (java.lang.String) product;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private List getManagedList27() {
		List list = new ArrayList();
		list.add(getDocManageAction());
		return list;
	}

	public nc.ui.so.m30.revise.action.M30ReviseEditAction getReviseAction() {
		if (context.get("reviseAction") != null)
			return (nc.ui.so.m30.revise.action.M30ReviseEditAction) context
					.get("reviseAction");
		nc.ui.so.m30.revise.action.M30ReviseEditAction bean = new nc.ui.so.m30.revise.action.M30ReviseEditAction();
		context.put("reviseAction", bean);
		bean.setModel(getManageAppModel());
		bean.setEditor(getBillFormEditor());
		bean.setInterceptor(getFormInterceptor());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.so.m30.revise.action.M30REditAction getReviseEditAction() {
		if (context.get("reviseEditAction") != null)
			return (nc.ui.so.m30.revise.action.M30REditAction) context
					.get("reviseEditAction");
		nc.ui.so.m30.revise.action.M30REditAction bean = new nc.ui.so.m30.revise.action.M30REditAction();
		context.put("reviseEditAction", bean);
		bean.setModel(getManageAppModel());
		bean.setEditor(getBillFormEditor());
		bean.setInterceptor(getFormInterceptor());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.so.m30.revise.action.M30RSaveForEditAction getM30RSaveForEditAction() {
		if (context.get("m30RSaveForEditAction") != null)
			return (nc.ui.so.m30.revise.action.M30RSaveForEditAction) context
					.get("m30RSaveForEditAction");
		nc.ui.so.m30.revise.action.M30RSaveForEditAction bean = new nc.ui.so.m30.revise.action.M30RSaveForEditAction();
		context.put("m30RSaveForEditAction", bean);
		bean.setModel(getManageAppModel());
		bean.setEditor(getBillFormEditor());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.funcnode.ui.action.GroupAction getOffsetActionGroup() {
		if (context.get("offsetActionGroup") != null)
			return (nc.funcnode.ui.action.GroupAction) context
					.get("offsetActionGroup");
		nc.funcnode.ui.action.GroupAction bean = new nc.funcnode.ui.action.GroupAction();
		context.put("offsetActionGroup", bean);
		bean.setCode("offsetFunc");
		bean.setName(getI18nFB_1a11da5());
		bean.setActions(getManagedList28());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private java.lang.String getI18nFB_1a11da5() {
		if (context.get("nc.ui.uif2.I18nFB#1a11da5") != null)
			return (java.lang.String) context.get("nc.ui.uif2.I18nFB#1a11da5");
		nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
		context.put("&nc.ui.uif2.I18nFB#1a11da5", bean);
		bean.setResDir("4006011_0");
		bean.setResId("04006011-0467");
		bean.setDefaultValue("费用冲抵");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		try {
			Object product = bean.getObject();
			context.put("nc.ui.uif2.I18nFB#1a11da5", product);
			return (java.lang.String) product;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private List getManagedList28() {
		List list = new ArrayList();
		list.add(getOffsetAction());
		list.add(getUnoffsetAction());
		return list;
	}

	public nc.ui.so.m30.billui.action.SaleOrderOffsetAction getOffsetAction() {
		if (context.get("offsetAction") != null)
			return (nc.ui.so.m30.billui.action.SaleOrderOffsetAction) context
					.get("offsetAction");
		nc.ui.so.m30.billui.action.SaleOrderOffsetAction bean = new nc.ui.so.m30.billui.action.SaleOrderOffsetAction();
		context.put("offsetAction", bean);
		bean.setModel(getManageAppModel());
		bean.setEditor(getBillFormEditor());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.so.m30.billui.action.SaleOrderUnOffsetAction getUnoffsetAction() {
		if (context.get("unoffsetAction") != null)
			return (nc.ui.so.m30.billui.action.SaleOrderUnOffsetAction) context
					.get("unoffsetAction");
		nc.ui.so.m30.billui.action.SaleOrderUnOffsetAction bean = new nc.ui.so.m30.billui.action.SaleOrderUnOffsetAction();
		context.put("unoffsetAction", bean);
		bean.setModel(getManageAppModel());
		bean.setEditor(getBillFormEditor());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.so.m30.billui.action.SaleOrderAddAction getAddManualAction() {
		if (context.get("addManualAction") != null)
			return (nc.ui.so.m30.billui.action.SaleOrderAddAction) context
					.get("addManualAction");
		nc.ui.so.m30.billui.action.SaleOrderAddAction bean = new nc.ui.so.m30.billui.action.SaleOrderAddAction();
		context.put("addManualAction", bean);
		bean.setSourceBillType("MANUAL");
		bean.setSourceBillName(getI18nFB_a1678e());
		bean.setModel(getManageAppModel());
		bean.setEditor(getBillFormEditor());
		bean.setInterceptor(getFormInterceptor());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private java.lang.String getI18nFB_a1678e() {
		if (context.get("nc.ui.uif2.I18nFB#a1678e") != null)
			return (java.lang.String) context.get("nc.ui.uif2.I18nFB#a1678e");
		nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
		context.put("&nc.ui.uif2.I18nFB#a1678e", bean);
		bean.setResDir("4006011_0");
		bean.setResId("04006011-0393");
		bean.setDefaultValue("自制");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		try {
			Object product = bean.getObject();
			context.put("nc.ui.uif2.I18nFB#a1678e", product);
			return (java.lang.String) product;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public nc.ui.pubapp.billref.dest.DefaultBillDataLogic getTransferLogic() {
		if (context.get("transferLogic") != null)
			return (nc.ui.pubapp.billref.dest.DefaultBillDataLogic) context
					.get("transferLogic");
		nc.ui.pubapp.billref.dest.DefaultBillDataLogic bean = new nc.ui.pubapp.billref.dest.DefaultBillDataLogic();
		context.put("transferLogic", bean);
		bean.setBillForm(getBillFormEditor());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.billref.dest.TransferBillViewProcessor getTransferProcessor() {
		if (context.get("transferProcessor") != null)
			return (nc.ui.pubapp.billref.dest.TransferBillViewProcessor) context
					.get("transferProcessor");
		nc.ui.pubapp.billref.dest.TransferBillViewProcessor bean = new nc.ui.pubapp.billref.dest.TransferBillViewProcessor();
		context.put("transferProcessor", bean);
		bean.setList(getListView());
		bean.setActionContainer(getContainer());
		bean.setTransferLogic(getTransferLogic());
		bean.setBillForm(getBillFormEditor());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.so.m30.billui.action.SaleOrderAddFromZ3Action getAdd2AAction() {
		if (context.get("add2AAction") != null)
			return (nc.ui.so.m30.billui.action.SaleOrderAddFromZ3Action) context
					.get("add2AAction");
		nc.ui.so.m30.billui.action.SaleOrderAddFromZ3Action bean = new nc.ui.so.m30.billui.action.SaleOrderAddFromZ3Action();
		context.put("add2AAction", bean);
		bean.setSourceBillType("Z3");
		bean.setSourceBillName(getI18nFB_95bed5());
		bean.setFlowBillType(false);
		bean.setModel(getManageAppModel());
		bean.setEditor(getBillFormEditor());
		bean.setTransferBillViewProcessor(getTransferProcessorforZ3());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private java.lang.String getI18nFB_95bed5() {
		if (context.get("nc.ui.uif2.I18nFB#95bed5") != null)
			return (java.lang.String) context.get("nc.ui.uif2.I18nFB#95bed5");
		nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
		context.put("&nc.ui.uif2.I18nFB#95bed5", bean);
		bean.setResDir("4006011_0");
		bean.setResId("04006011-0394");
		bean.setDefaultValue("销售合同");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		try {
			Object product = bean.getObject();
			context.put("nc.ui.uif2.I18nFB#95bed5", product);
			return (java.lang.String) product;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public nc.ui.so.m30.billui.tranferbill.M30RefZ3TransferBillDataLogic getTransferLogicforZ3() {
		if (context.get("transferLogicforZ3") != null)
			return (nc.ui.so.m30.billui.tranferbill.M30RefZ3TransferBillDataLogic) context
					.get("transferLogicforZ3");
		nc.ui.so.m30.billui.tranferbill.M30RefZ3TransferBillDataLogic bean = new nc.ui.so.m30.billui.tranferbill.M30RefZ3TransferBillDataLogic();
		context.put("transferLogicforZ3", bean);
		bean.setBillForm(getBillFormEditor());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.billref.dest.TransferBillViewProcessor getTransferProcessorforZ3() {
		if (context.get("transferProcessorforZ3") != null)
			return (nc.ui.pubapp.billref.dest.TransferBillViewProcessor) context
					.get("transferProcessorforZ3");
		nc.ui.pubapp.billref.dest.TransferBillViewProcessor bean = new nc.ui.pubapp.billref.dest.TransferBillViewProcessor();
		context.put("transferProcessorforZ3", bean);
		bean.setList(getListView());
		bean.setActionContainer(getContainer());
		bean.setTransferLogic(getTransferLogicforZ3());
		bean.setBillForm(getBillFormEditor());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.so.m30.billui.action.SaleOrderAddFrom4310Action getAdd4310Action() {
		if (context.get("add4310Action") != null)
			return (nc.ui.so.m30.billui.action.SaleOrderAddFrom4310Action) context
					.get("add4310Action");
		nc.ui.so.m30.billui.action.SaleOrderAddFrom4310Action bean = new nc.ui.so.m30.billui.action.SaleOrderAddFrom4310Action();
		context.put("add4310Action", bean);
		bean.setSourceBillType("4310");
		bean.setSourceBillName(getI18nFB_123b313());
		bean.setFlowBillType(false);
		bean.setModel(getManageAppModel());
		bean.setEditor(getBillFormEditor());
		bean.setTransferBillViewProcessor(getTransferProcessorfor4310());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private java.lang.String getI18nFB_123b313() {
		if (context.get("nc.ui.uif2.I18nFB#123b313") != null)
			return (java.lang.String) context.get("nc.ui.uif2.I18nFB#123b313");
		nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
		context.put("&nc.ui.uif2.I18nFB#123b313", bean);
		bean.setResDir("4006011_0");
		bean.setResId("04006011-0395");
		bean.setDefaultValue("销售报价单");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		try {
			Object product = bean.getObject();
			context.put("nc.ui.uif2.I18nFB#123b313", product);
			return (java.lang.String) product;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public nc.ui.so.m30.billui.tranferbill.M30Ref4310TransferBillDataLogic getTransferLogicfor4310() {
		if (context.get("transferLogicfor4310") != null)
			return (nc.ui.so.m30.billui.tranferbill.M30Ref4310TransferBillDataLogic) context
					.get("transferLogicfor4310");
		nc.ui.so.m30.billui.tranferbill.M30Ref4310TransferBillDataLogic bean = new nc.ui.so.m30.billui.tranferbill.M30Ref4310TransferBillDataLogic();
		context.put("transferLogicfor4310", bean);
		bean.setBillForm(getBillFormEditor());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.billref.dest.TransferBillViewProcessor getTransferProcessorfor4310() {
		if (context.get("transferProcessorfor4310") != null)
			return (nc.ui.pubapp.billref.dest.TransferBillViewProcessor) context
					.get("transferProcessorfor4310");
		nc.ui.pubapp.billref.dest.TransferBillViewProcessor bean = new nc.ui.pubapp.billref.dest.TransferBillViewProcessor();
		context.put("transferProcessorfor4310", bean);
		bean.setList(getListView());
		bean.setActionContainer(getContainer());
		bean.setTransferLogic(getTransferLogicfor4310());
		bean.setBillForm(getBillFormEditor());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.so.m30.billui.action.SaleOrderAddFrom38Action getAdd38Action() {
		if (context.get("add38Action") != null)
			return (nc.ui.so.m30.billui.action.SaleOrderAddFrom38Action) context
					.get("add38Action");
		nc.ui.so.m30.billui.action.SaleOrderAddFrom38Action bean = new nc.ui.so.m30.billui.action.SaleOrderAddFrom38Action();
		context.put("add38Action", bean);
		bean.setSourceBillType("38");
		bean.setSourceBillName(getI18nFB_c1b878());
		bean.setFlowBillType(false);
		bean.setModel(getManageAppModel());
		bean.setEditor(getBillFormEditor());
		bean.setTransferBillViewProcessor(getTransferProcessor());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private java.lang.String getI18nFB_c1b878() {
		if (context.get("nc.ui.uif2.I18nFB#c1b878") != null)
			return (java.lang.String) context.get("nc.ui.uif2.I18nFB#c1b878");
		nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
		context.put("&nc.ui.uif2.I18nFB#c1b878", bean);
		bean.setResDir("4006011_0");
		bean.setResId("04006011-0396");
		bean.setDefaultValue("预订单");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		try {
			Object product = bean.getObject();
			context.put("nc.ui.uif2.I18nFB#c1b878", product);
			return (java.lang.String) product;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public nc.ui.so.m30.billui.action.SaleOrderAddFrom4HAction getAdd4HAction() {
		if (context.get("add4HAction") != null)
			return (nc.ui.so.m30.billui.action.SaleOrderAddFrom4HAction) context
					.get("add4HAction");
		nc.ui.so.m30.billui.action.SaleOrderAddFrom4HAction bean = new nc.ui.so.m30.billui.action.SaleOrderAddFrom4HAction();
		context.put("add4HAction", bean);
		bean.setSourceBillType("4H");
		bean.setSourceBillName(getI18nFB_35dafa());
		bean.setFlowBillType(false);
		bean.setModel(getManageAppModel());
		bean.setEditor(getBillFormEditor());
		bean.setTransferBillViewProcessor(getTransferProcessorfor4H());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private java.lang.String getI18nFB_35dafa() {
		if (context.get("nc.ui.uif2.I18nFB#35dafa") != null)
			return (java.lang.String) context.get("nc.ui.uif2.I18nFB#35dafa");
		nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
		context.put("&nc.ui.uif2.I18nFB#35dafa", bean);
		bean.setResDir("4006011_0");
		bean.setResId("04006011-0397");
		bean.setDefaultValue("库存借出单");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		try {
			Object product = bean.getObject();
			context.put("nc.ui.uif2.I18nFB#35dafa", product);
			return (java.lang.String) product;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public nc.ui.so.m30.billui.tranferbill.M30Ref4HTransferBillDataLogic getTransferLogicfor4H() {
		if (context.get("transferLogicfor4H") != null)
			return (nc.ui.so.m30.billui.tranferbill.M30Ref4HTransferBillDataLogic) context
					.get("transferLogicfor4H");
		nc.ui.so.m30.billui.tranferbill.M30Ref4HTransferBillDataLogic bean = new nc.ui.so.m30.billui.tranferbill.M30Ref4HTransferBillDataLogic();
		context.put("transferLogicfor4H", bean);
		bean.setBillForm(getBillFormEditor());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.billref.dest.TransferBillViewProcessor getTransferProcessorfor4H() {
		if (context.get("transferProcessorfor4H") != null)
			return (nc.ui.pubapp.billref.dest.TransferBillViewProcessor) context
					.get("transferProcessorfor4H");
		nc.ui.pubapp.billref.dest.TransferBillViewProcessor bean = new nc.ui.pubapp.billref.dest.TransferBillViewProcessor();
		context.put("transferProcessorfor4H", bean);
		bean.setList(getListView());
		bean.setActionContainer(getContainer());
		bean.setTransferLogic(getTransferLogicfor4H());
		bean.setBillForm(getBillFormEditor());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.so.m30.billui.view.SaleOrderQueryDLGInitializer getQueryDLGInitializer() {
		if (context.get("queryDLGInitializer") != null)
			return (nc.ui.so.m30.billui.view.SaleOrderQueryDLGInitializer) context
					.get("queryDLGInitializer");
		nc.ui.so.m30.billui.view.SaleOrderQueryDLGInitializer bean = new nc.ui.so.m30.billui.view.SaleOrderQueryDLGInitializer();
		context.put("queryDLGInitializer", bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.uif2.editor.QueryTemplateContainer getQueryTemplateContainer() {
		if (context.get("queryTemplateContainer") != null)
			return (nc.ui.uif2.editor.QueryTemplateContainer) context
					.get("queryTemplateContainer");
		nc.ui.uif2.editor.QueryTemplateContainer bean = new nc.ui.uif2.editor.QueryTemplateContainer();
		context.put("queryTemplateContainer", bean);
		bean.setContext(getContext());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.query2.action.DefaultQueryAction getQueryAction() {
		if (context.get("queryAction") != null)
			return (nc.ui.pubapp.uif2app.query2.action.DefaultQueryAction) context
					.get("queryAction");
		nc.ui.pubapp.uif2app.query2.action.DefaultQueryAction bean = new nc.ui.pubapp.uif2app.query2.action.DefaultQueryAction();
		context.put("queryAction", bean);
		bean.setModel(getManageAppModel());
		bean.setDataManager(getModelDataManager());
		bean.setQryCondDLGInitializer(getQueryDLGInitializer());
		bean.setTemplateContainer(getQueryTemplateContainer());
		bean.setInterceptor(getListInterceptor());
		bean.setShowUpComponent(getListView());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.so.m30.billui.action.SaleOrderEditAction getEditAction() {
		if (context.get("editAction") != null)
			return (nc.ui.so.m30.billui.action.SaleOrderEditAction) context
					.get("editAction");
		nc.ui.so.m30.billui.action.SaleOrderEditAction bean = new nc.ui.so.m30.billui.action.SaleOrderEditAction();
		context.put("editAction", bean);
		bean.setModel(getManageAppModel());
		bean.setEditor(getBillFormEditor());
		bean.setInterceptor(getFormInterceptor());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.so.m30.revise.action.M30RDeleteAction getDeleteAction() {
		if (context.get("deleteAction") != null)
			return (nc.ui.so.m30.revise.action.M30RDeleteAction) context
					.get("deleteAction");
		nc.ui.so.m30.revise.action.M30RDeleteAction bean = new nc.ui.so.m30.revise.action.M30RDeleteAction();
		context.put("deleteAction", bean);
		bean.setModel(getManageAppModel());
		bean.setEditor(getBillFormEditor());
		bean.setActionName("DELETE");
		bean.setBillType("30R");
		bean.setValidationService(getPowerdeletevalidservice());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.pub.power.PowerValidateService getPowerdeletevalidservice() {
		if (context.get("powerdeletevalidservice") != null)
			return (nc.ui.pubapp.pub.power.PowerValidateService) context
					.get("powerdeletevalidservice");
		nc.ui.pubapp.pub.power.PowerValidateService bean = new nc.ui.pubapp.pub.power.PowerValidateService();
		context.put("powerdeletevalidservice", bean);
		bean.setActionCode("delete");
		bean.setBillCodeFiledName("vbillcode");
		bean.setPermissionCode("30R");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.so.m30.revise.action.M30ReviseSaveAction getSaveAction() {
		if (context.get("saveAction") != null)
			return (nc.ui.so.m30.revise.action.M30ReviseSaveAction) context
					.get("saveAction");
		nc.ui.so.m30.revise.action.M30ReviseSaveAction bean = new nc.ui.so.m30.revise.action.M30ReviseSaveAction();
		context.put("saveAction", bean);
		bean.setModel(getManageAppModel());
		bean.setEditor(getBillFormEditor());
		bean.setActionName("REVISEWRITE");
		bean.setBillType("30R");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.so.m30.billui.action.SaleOrderSaveAndSendAction getSaveandsendApproveAction() {
		if (context.get("saveandsendApproveAction") != null)
			return (nc.ui.so.m30.billui.action.SaleOrderSaveAndSendAction) context
					.get("saveandsendApproveAction");
		nc.ui.so.m30.billui.action.SaleOrderSaveAndSendAction bean = new nc.ui.so.m30.billui.action.SaleOrderSaveAndSendAction(
				getSaveAction(), getSendApproveAction());
		context.put("saveandsendApproveAction", bean);
		bean.setModel(getManageAppModel());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.pub.power.PowerSaveValidateService getPowerwritevalidservice() {
		if (context.get("powerwritevalidservice") != null)
			return (nc.ui.pubapp.pub.power.PowerSaveValidateService) context
					.get("powerwritevalidservice");
		nc.ui.pubapp.pub.power.PowerSaveValidateService bean = new nc.ui.pubapp.pub.power.PowerSaveValidateService();
		context.put("powerwritevalidservice", bean);
		bean.setInsertActionCode("insert");
		bean.setEditActionCode("edit");
		bean.setBillCodeFiledName("vbillcode");
		bean.setPermissionCode("30R");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.so.m30.billui.action.SaleOrderCancelAction getCancelAction() {
		if (context.get("cancelAction") != null)
			return (nc.ui.so.m30.billui.action.SaleOrderCancelAction) context
					.get("cancelAction");
		nc.ui.so.m30.billui.action.SaleOrderCancelAction bean = new nc.ui.so.m30.billui.action.SaleOrderCancelAction();
		context.put("cancelAction", bean);
		bean.setModel(getManageAppModel());
		bean.setEditor(getBillFormEditor());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.query2.action.DefaultRefreshAction getListRefreshAction() {
		if (context.get("listRefreshAction") != null)
			return (nc.ui.pubapp.uif2app.query2.action.DefaultRefreshAction) context
					.get("listRefreshAction");
		nc.ui.pubapp.uif2app.query2.action.DefaultRefreshAction bean = new nc.ui.pubapp.uif2app.query2.action.DefaultRefreshAction();
		context.put("listRefreshAction", bean);
		bean.setDataManager(getModelDataManager());
		bean.setModel(getManageAppModel());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.so.m30.revise.action.M30ReviseRefreshSingleAction getCardRefreshAction() {
		if (context.get("cardRefreshAction") != null)
			return (nc.ui.so.m30.revise.action.M30ReviseRefreshSingleAction) context
					.get("cardRefreshAction");
		nc.ui.so.m30.revise.action.M30ReviseRefreshSingleAction bean = new nc.ui.so.m30.revise.action.M30ReviseRefreshSingleAction();
		context.put("cardRefreshAction", bean);
		bean.setModel(getManageAppModel());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.funcnode.ui.action.GroupAction getSendActionGroup() {
		if (context.get("sendActionGroup") != null)
			return (nc.funcnode.ui.action.GroupAction) context
					.get("sendActionGroup");
		nc.funcnode.ui.action.GroupAction bean = new nc.funcnode.ui.action.GroupAction();
		context.put("sendActionGroup", bean);
		bean.setActions(getManagedList29());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList29() {
		List list = new ArrayList();
		list.add(getSendApproveAction());
		list.add(getUnsendApproveAction());
		return list;
	}

	public nc.ui.so.m30.revise.action.M30ReviseSendApproveAction getSendApproveAction() {
		if (context.get("sendApproveAction") != null)
			return (nc.ui.so.m30.revise.action.M30ReviseSendApproveAction) context
					.get("sendApproveAction");
		nc.ui.so.m30.revise.action.M30ReviseSendApproveAction bean = new nc.ui.so.m30.revise.action.M30ReviseSendApproveAction();
		context.put("sendApproveAction", bean);
		bean.setModel(getManageAppModel());
		bean.setEditor(getBillFormEditor());
		bean.setFilledUpInFlow(true);
		bean.setActionName("SAVE");
		bean.setBillType("30R");
		bean.setValidationService(getPowersendappvalidservice());
		bean.setTpaProgressUtil(getTpaprogressutil());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.uif2.components.progress.TPAProgressUtil getTpaprogressutil() {
		if (context.get("tpaprogressutil") != null)
			return (nc.ui.uif2.components.progress.TPAProgressUtil) context
					.get("tpaprogressutil");
		nc.ui.uif2.components.progress.TPAProgressUtil bean = new nc.ui.uif2.components.progress.TPAProgressUtil();
		context.put("tpaprogressutil", bean);
		bean.setContext(getContext());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.pub.power.PowerValidateService getPowersendappvalidservice() {
		if (context.get("powersendappvalidservice") != null)
			return (nc.ui.pubapp.pub.power.PowerValidateService) context
					.get("powersendappvalidservice");
		nc.ui.pubapp.pub.power.PowerValidateService bean = new nc.ui.pubapp.pub.power.PowerValidateService();
		context.put("powersendappvalidservice", bean);
		bean.setActionCode("commit");
		bean.setBillCodeFiledName("vbillcode");
		bean.setPermissionCode("30R");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.so.m30.revise.action.M30ReviseUnSendApproveAction getUnsendApproveAction() {
		if (context.get("unsendApproveAction") != null)
			return (nc.ui.so.m30.revise.action.M30ReviseUnSendApproveAction) context
					.get("unsendApproveAction");
		nc.ui.so.m30.revise.action.M30ReviseUnSendApproveAction bean = new nc.ui.so.m30.revise.action.M30ReviseUnSendApproveAction();
		context.put("unsendApproveAction", bean);
		bean.setModel(getManageAppModel());
		bean.setEditor(getBillFormEditor());
		bean.setFilledUpInFlow(true);
		bean.setActionName("UNSAVE");
		bean.setBillType("30R");
		bean.setValidationService(getPowerunsendappvalidservice());
		bean.setTpaProgressUtil(getTpaprogressutil());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.pub.power.PowerValidateService getPowerunsendappvalidservice() {
		if (context.get("powerunsendappvalidservice") != null)
			return (nc.ui.pubapp.pub.power.PowerValidateService) context
					.get("powerunsendappvalidservice");
		nc.ui.pubapp.pub.power.PowerValidateService bean = new nc.ui.pubapp.pub.power.PowerValidateService();
		context.put("powerunsendappvalidservice", bean);
		bean.setActionCode("uncommit");
		bean.setBillCodeFiledName("vbillcode");
		bean.setPermissionCode("30R");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.funcnode.ui.action.GroupAction getApproveActionGroup() {
		if (context.get("approveActionGroup") != null)
			return (nc.funcnode.ui.action.GroupAction) context
					.get("approveActionGroup");
		nc.funcnode.ui.action.GroupAction bean = new nc.funcnode.ui.action.GroupAction();
		context.put("approveActionGroup", bean);
		bean.setActions(getManagedList30());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList30() {
		List list = new ArrayList();
		list.add(getApproveAction());
		list.add(getUnApproveAction());
		list.add(getQueryAuditFlowAction());
		return list;
	}

	public nc.ui.so.m30.revise.action.M30ReviseApproveAction getApproveAction() {
		if (context.get("approveAction") != null)
			return (nc.ui.so.m30.revise.action.M30ReviseApproveAction) context
					.get("approveAction");
		nc.ui.so.m30.revise.action.M30ReviseApproveAction bean = new nc.ui.so.m30.revise.action.M30ReviseApproveAction();
		context.put("approveAction", bean);
		bean.setModel(getManageAppModel());
		bean.setEditor(getBillFormEditor());
		bean.setFilledUpInFlow(true);
		bean.setActionName("APPROVE");
		bean.setBillType("30R");
		bean.setValidationService(getPowerapprovevalidservice());
		bean.setTpaProgressUtil(getTpaprogressutil());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.pub.power.PowerValidateService getPowerapprovevalidservice() {
		if (context.get("powerapprovevalidservice") != null)
			return (nc.ui.pubapp.pub.power.PowerValidateService) context
					.get("powerapprovevalidservice");
		nc.ui.pubapp.pub.power.PowerValidateService bean = new nc.ui.pubapp.pub.power.PowerValidateService();
		context.put("powerapprovevalidservice", bean);
		bean.setActionCode("approve");
		bean.setBillCodeFiledName("vbillcode");
		bean.setPermissionCode("30R");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.so.m30.revise.action.M30ReviseUnApproveAction getUnApproveAction() {
		if (context.get("unApproveAction") != null)
			return (nc.ui.so.m30.revise.action.M30ReviseUnApproveAction) context
					.get("unApproveAction");
		nc.ui.so.m30.revise.action.M30ReviseUnApproveAction bean = new nc.ui.so.m30.revise.action.M30ReviseUnApproveAction();
		context.put("unApproveAction", bean);
		bean.setModel(getManageAppModel());
		bean.setEditor(getBillFormEditor());
		bean.setFilledUpInFlow(true);
		bean.setActionName("UNAPPROVE");
		bean.setBillType("30R");
		bean.setValidationService(getPowerunapprovevalidservice());
		bean.setTpaProgressUtil(getTpaprogressutil());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.pub.power.PowerValidateService getPowerunapprovevalidservice() {
		if (context.get("powerunapprovevalidservice") != null)
			return (nc.ui.pubapp.pub.power.PowerValidateService) context
					.get("powerunapprovevalidservice");
		nc.ui.pubapp.pub.power.PowerValidateService bean = new nc.ui.pubapp.pub.power.PowerValidateService();
		context.put("powerunapprovevalidservice", bean);
		bean.setActionCode("unapprove");
		bean.setBillCodeFiledName("vbillcode");
		bean.setPermissionCode("30R");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.so.m30.billui.action.link.QueryAuditFlowAction getQueryAuditFlowAction() {
		if (context.get("QueryAuditFlowAction") != null)
			return (nc.ui.so.m30.billui.action.link.QueryAuditFlowAction) context
					.get("QueryAuditFlowAction");
		nc.ui.so.m30.billui.action.link.QueryAuditFlowAction bean = new nc.ui.so.m30.billui.action.link.QueryAuditFlowAction();
		context.put("QueryAuditFlowAction", bean);
		bean.setModel(getManageAppModel());
		bean.setEditor(getBillFormEditor());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.so.m30.billui.action.SaleOrderCopyAction getCopyAction() {
		if (context.get("copyAction") != null)
			return (nc.ui.so.m30.billui.action.SaleOrderCopyAction) context
					.get("copyAction");
		nc.ui.so.m30.billui.action.SaleOrderCopyAction bean = new nc.ui.so.m30.billui.action.SaleOrderCopyAction();
		context.put("copyAction", bean);
		bean.setModel(getManageAppModel());
		bean.setEditor(getBillFormEditor());
		bean.setInterceptor(getFormInterceptor());
		bean.setCopyActionProcessor(getCopyActionProcessor_a2ce76());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.so.m30.billui.action.CopyActionProcessor getCopyActionProcessor_a2ce76() {
		if (context
				.get("nc.ui.so.m30.billui.action.CopyActionProcessor#a2ce76") != null)
			return (nc.ui.so.m30.billui.action.CopyActionProcessor) context
					.get("nc.ui.so.m30.billui.action.CopyActionProcessor#a2ce76");
		nc.ui.so.m30.billui.action.CopyActionProcessor bean = new nc.ui.so.m30.billui.action.CopyActionProcessor();
		context.put("nc.ui.so.m30.billui.action.CopyActionProcessor#a2ce76",
				bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.so.m30.billui.action.SaleOrderGatheringAction getActiont2() {
		if (context.get("actiont2") != null)
			return (nc.ui.so.m30.billui.action.SaleOrderGatheringAction) context
					.get("actiont2");
		nc.ui.so.m30.billui.action.SaleOrderGatheringAction bean = new nc.ui.so.m30.billui.action.SaleOrderGatheringAction();
		context.put("actiont2", bean);
		bean.setModel(getManageAppModel());
		bean.setEditor(getBillFormEditor());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.so.m30.billui.action.SaleOrderReceiveVerifyAction getActiont3() {
		if (context.get("actiont3") != null)
			return (nc.ui.so.m30.billui.action.SaleOrderReceiveVerifyAction) context
					.get("actiont3");
		nc.ui.so.m30.billui.action.SaleOrderReceiveVerifyAction bean = new nc.ui.so.m30.billui.action.SaleOrderReceiveVerifyAction();
		context.put("actiont3", bean);
		bean.setModel(getManageAppModel());
		bean.setEditor(getBillFormEditor());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.so.m30.billui.action.SaleOrderSendArrangeAction getSendArrange() {
		if (context.get("sendArrange") != null)
			return (nc.ui.so.m30.billui.action.SaleOrderSendArrangeAction) context
					.get("sendArrange");
		nc.ui.so.m30.billui.action.SaleOrderSendArrangeAction bean = new nc.ui.so.m30.billui.action.SaleOrderSendArrangeAction();
		context.put("sendArrange", bean);
		bean.setModel(getManageAppModel());
		bean.setEditor(getBillFormEditor());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.so.m30.billui.action.SaleOrderBHArrangeAction getBHArrange() {
		if (context.get("BHArrange") != null)
			return (nc.ui.so.m30.billui.action.SaleOrderBHArrangeAction) context
					.get("BHArrange");
		nc.ui.so.m30.billui.action.SaleOrderBHArrangeAction bean = new nc.ui.so.m30.billui.action.SaleOrderBHArrangeAction();
		context.put("BHArrange", bean);
		bean.setModel(getManageAppModel());
		bean.setEditor(getBillFormEditor());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.so.m30.billui.action.SaleOrderZYArrangeAction getZYArrange() {
		if (context.get("ZYArrange") != null)
			return (nc.ui.so.m30.billui.action.SaleOrderZYArrangeAction) context
					.get("ZYArrange");
		nc.ui.so.m30.billui.action.SaleOrderZYArrangeAction bean = new nc.ui.so.m30.billui.action.SaleOrderZYArrangeAction();
		context.put("ZYArrange", bean);
		bean.setModel(getManageAppModel());
		bean.setEditor(getBillFormEditor());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.funcnode.ui.action.MenuAction getActionGroupgt2() {
		if (context.get("actionGroupgt2") != null)
			return (nc.funcnode.ui.action.MenuAction) context
					.get("actionGroupgt2");
		nc.funcnode.ui.action.MenuAction bean = new nc.funcnode.ui.action.MenuAction();
		context.put("actionGroupgt2", bean);
		bean.setCode("gt2");
		bean.setName(getI18nFB_a0af8f());
		bean.setActions(getManagedList31());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private java.lang.String getI18nFB_a0af8f() {
		if (context.get("nc.ui.uif2.I18nFB#a0af8f") != null)
			return (java.lang.String) context.get("nc.ui.uif2.I18nFB#a0af8f");
		nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
		context.put("&nc.ui.uif2.I18nFB#a0af8f", bean);
		bean.setResDir("4006011_0");
		bean.setResId("04006011-0399");
		bean.setDefaultValue("发货安排");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		try {
			Object product = bean.getObject();
			context.put("nc.ui.uif2.I18nFB#a0af8f", product);
			return (java.lang.String) product;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private List getManagedList31() {
		List list = new ArrayList();
		list.add(getSendArrange());
		list.add(getBHArrange());
		list.add(getZYArrange());
		return list;
	}

	public nc.ui.so.m30.billui.action.assist.FreezeAction getActiont7() {
		if (context.get("actiont7") != null)
			return (nc.ui.so.m30.billui.action.assist.FreezeAction) context
					.get("actiont7");
		nc.ui.so.m30.billui.action.assist.FreezeAction bean = new nc.ui.so.m30.billui.action.assist.FreezeAction();
		context.put("actiont7", bean);
		bean.setModel(getManageAppModel());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.so.m30.billui.action.assist.UnFreezeAction getActiont8() {
		if (context.get("actiont8") != null)
			return (nc.ui.so.m30.billui.action.assist.UnFreezeAction) context
					.get("actiont8");
		nc.ui.so.m30.billui.action.assist.UnFreezeAction bean = new nc.ui.so.m30.billui.action.assist.UnFreezeAction();
		context.put("actiont8", bean);
		bean.setModel(getManageAppModel());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.so.m30.billui.action.assist.SaleOrderCloseAction getCloseAction() {
		if (context.get("closeAction") != null)
			return (nc.ui.so.m30.billui.action.assist.SaleOrderCloseAction) context
					.get("closeAction");
		nc.ui.so.m30.billui.action.assist.SaleOrderCloseAction bean = new nc.ui.so.m30.billui.action.assist.SaleOrderCloseAction();
		context.put("closeAction", bean);
		bean.setModel(getManageAppModel());
		bean.setSingleBillService(getM30CloseBillCloseService_165fc96());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.so.m30.closemanage.model.M30CloseBillCloseService getM30CloseBillCloseService_165fc96() {
		if (context
				.get("nc.ui.so.m30.closemanage.model.M30CloseBillCloseService#165fc96") != null)
			return (nc.ui.so.m30.closemanage.model.M30CloseBillCloseService) context
					.get("nc.ui.so.m30.closemanage.model.M30CloseBillCloseService#165fc96");
		nc.ui.so.m30.closemanage.model.M30CloseBillCloseService bean = new nc.ui.so.m30.closemanage.model.M30CloseBillCloseService();
		context.put(
				"nc.ui.so.m30.closemanage.model.M30CloseBillCloseService#165fc96",
				bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.so.m30.billui.action.assist.SaleOrderReverseAction getActiont10() {
		if (context.get("actiont10") != null)
			return (nc.ui.so.m30.billui.action.assist.SaleOrderReverseAction) context
					.get("actiont10");
		nc.ui.so.m30.billui.action.assist.SaleOrderReverseAction bean = new nc.ui.so.m30.billui.action.assist.SaleOrderReverseAction();
		context.put("actiont10", bean);
		bean.setModel(getManageAppModel());
		bean.setEditor(getBillFormEditor());
		bean.setListView(getListView());
		bean.setRefreshAction(getCardRefreshAction());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.so.m30.billui.action.assist.TransInfoAction getTransInfoAction() {
		if (context.get("TransInfoAction") != null)
			return (nc.ui.so.m30.billui.action.assist.TransInfoAction) context
					.get("TransInfoAction");
		nc.ui.so.m30.billui.action.assist.TransInfoAction bean = new nc.ui.so.m30.billui.action.assist.TransInfoAction();
		context.put("TransInfoAction", bean);
		bean.setModel(getManageAppModel());
		bean.setEditor(getBillFormEditor());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.so.m30.billui.action.assist.SaleOrderPushTo21Action getActiont12() {
		if (context.get("actiont12") != null)
			return (nc.ui.so.m30.billui.action.assist.SaleOrderPushTo21Action) context
					.get("actiont12");
		nc.ui.so.m30.billui.action.assist.SaleOrderPushTo21Action bean = new nc.ui.so.m30.billui.action.assist.SaleOrderPushTo21Action();
		context.put("actiont12", bean);
		bean.setSourceBillName(getI18nFB_aa017f());
		bean.setFlowBillType(false);
		bean.setModel(getManageAppModel());
		bean.setEditor(getBillFormEditor());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private java.lang.String getI18nFB_aa017f() {
		if (context.get("nc.ui.uif2.I18nFB#aa017f") != null)
			return (java.lang.String) context.get("nc.ui.uif2.I18nFB#aa017f");
		nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
		context.put("&nc.ui.uif2.I18nFB#aa017f", bean);
		bean.setResDir("4006011_0");
		bean.setResId("04006011-0464");
		bean.setDefaultValue("生成协同采购订单");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		try {
			Object product = bean.getObject();
			context.put("nc.ui.uif2.I18nFB#aa017f", product);
			return (java.lang.String) product;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public nc.ui.so.m30.billui.tranferbill.M30RefCoop21TransferBillDataLogic getTransferLogicforCoop21() {
		if (context.get("transferLogicforCoop21") != null)
			return (nc.ui.so.m30.billui.tranferbill.M30RefCoop21TransferBillDataLogic) context
					.get("transferLogicforCoop21");
		nc.ui.so.m30.billui.tranferbill.M30RefCoop21TransferBillDataLogic bean = new nc.ui.so.m30.billui.tranferbill.M30RefCoop21TransferBillDataLogic();
		context.put("transferLogicforCoop21", bean);
		bean.setBillForm(getBillFormEditor());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.billref.dest.TransferBillViewProcessor getTransferProcessorforCoop21() {
		if (context.get("transferProcessorforCoop21") != null)
			return (nc.ui.pubapp.billref.dest.TransferBillViewProcessor) context
					.get("transferProcessorforCoop21");
		nc.ui.pubapp.billref.dest.TransferBillViewProcessor bean = new nc.ui.pubapp.billref.dest.TransferBillViewProcessor();
		context.put("transferProcessorforCoop21", bean);
		bean.setList(getListView());
		bean.setActionContainer(getContainer());
		bean.setTransferLogic(getTransferLogicforCoop21());
		bean.setBillForm(getBillFormEditor());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.so.m30.billui.action.assist.SaleOrderRefCoop21Action getRefCoop21Action() {
		if (context.get("refCoop21Action") != null)
			return (nc.ui.so.m30.billui.action.assist.SaleOrderRefCoop21Action) context
					.get("refCoop21Action");
		nc.ui.so.m30.billui.action.assist.SaleOrderRefCoop21Action bean = new nc.ui.so.m30.billui.action.assist.SaleOrderRefCoop21Action();
		context.put("refCoop21Action", bean);
		bean.setSourceBillType("21");
		bean.setSourceBillName(getI18nFB_1485d17());
		bean.setFlowBillType(false);
		bean.setModel(getManageAppModel());
		bean.setEditor(getBillFormEditor());
		bean.setTransferBillViewProcessor(getTransferProcessorforCoop21());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private java.lang.String getI18nFB_1485d17() {
		if (context.get("nc.ui.uif2.I18nFB#1485d17") != null)
			return (java.lang.String) context.get("nc.ui.uif2.I18nFB#1485d17");
		nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
		context.put("&nc.ui.uif2.I18nFB#1485d17", bean);
		bean.setResDir("4006011_0");
		bean.setResId("04006011-0401");
		bean.setDefaultValue("参照协同采购订单新增");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		try {
			Object product = bean.getObject();
			context.put("nc.ui.uif2.I18nFB#1485d17", product);
			return (java.lang.String) product;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public nc.ui.so.m30.billui.tranferbill.M30Ref30TransferBillDataLogic getTransferLogicforwithdraw() {
		if (context.get("transferLogicforwithdraw") != null)
			return (nc.ui.so.m30.billui.tranferbill.M30Ref30TransferBillDataLogic) context
					.get("transferLogicforwithdraw");
		nc.ui.so.m30.billui.tranferbill.M30Ref30TransferBillDataLogic bean = new nc.ui.so.m30.billui.tranferbill.M30Ref30TransferBillDataLogic();
		context.put("transferLogicforwithdraw", bean);
		bean.setBillForm(getBillFormEditor());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.billref.dest.TransferBillViewProcessor getTransferProcessorforwithdraw() {
		if (context.get("transferProcessorforwithdraw") != null)
			return (nc.ui.pubapp.billref.dest.TransferBillViewProcessor) context
					.get("transferProcessorforwithdraw");
		nc.ui.pubapp.billref.dest.TransferBillViewProcessor bean = new nc.ui.pubapp.billref.dest.TransferBillViewProcessor();
		context.put("transferProcessorforwithdraw", bean);
		bean.setList(getListView());
		bean.setActionContainer(getContainer());
		bean.setTransferLogic(getTransferLogicforwithdraw());
		bean.setBillForm(getBillFormEditor());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.so.m30.billui.action.assist.ReturnSaleOrderAddFrom30Action getReturnSaleOrderAction() {
		if (context.get("ReturnSaleOrderAction") != null)
			return (nc.ui.so.m30.billui.action.assist.ReturnSaleOrderAddFrom30Action) context
					.get("ReturnSaleOrderAction");
		nc.ui.so.m30.billui.action.assist.ReturnSaleOrderAddFrom30Action bean = new nc.ui.so.m30.billui.action.assist.ReturnSaleOrderAddFrom30Action();
		context.put("ReturnSaleOrderAction", bean);
		bean.setSourceBillType("30R");
		bean.setSourceBillName(getI18nFB_1d7b383());
		bean.setFlowBillType(false);
		bean.setModel(getManageAppModel());
		bean.setEditor(getBillFormEditor());
		bean.setTransferBillViewProcessor(getTransferProcessorforwithdraw());
		bean.setInterceptor(getFormInterceptor());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private java.lang.String getI18nFB_1d7b383() {
		if (context.get("nc.ui.uif2.I18nFB#1d7b383") != null)
			return (java.lang.String) context.get("nc.ui.uif2.I18nFB#1d7b383");
		nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
		context.put("&nc.ui.uif2.I18nFB#1d7b383", bean);
		bean.setResDir("4006011_0");
		bean.setResId("04006011-0402");
		bean.setDefaultValue("销售订单");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		try {
			Object product = bean.getObject();
			context.put("nc.ui.uif2.I18nFB#1d7b383", product);
			return (java.lang.String) product;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public nc.ui.so.m30.billui.tranferbill.M30Ref4CTransferBillDataLogic getTransferLogicforsaleout() {
		if (context.get("transferLogicforsaleout") != null)
			return (nc.ui.so.m30.billui.tranferbill.M30Ref4CTransferBillDataLogic) context
					.get("transferLogicforsaleout");
		nc.ui.so.m30.billui.tranferbill.M30Ref4CTransferBillDataLogic bean = new nc.ui.so.m30.billui.tranferbill.M30Ref4CTransferBillDataLogic();
		context.put("transferLogicforsaleout", bean);
		bean.setBillForm(getBillFormEditor());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.billref.dest.TransferBillViewProcessor getTransferProcessorforsaleout() {
		if (context.get("transferProcessorforsaleout") != null)
			return (nc.ui.pubapp.billref.dest.TransferBillViewProcessor) context
					.get("transferProcessorforsaleout");
		nc.ui.pubapp.billref.dest.TransferBillViewProcessor bean = new nc.ui.pubapp.billref.dest.TransferBillViewProcessor();
		context.put("transferProcessorforsaleout", bean);
		bean.setList(getListView());
		bean.setActionContainer(getContainer());
		bean.setTransferLogic(getTransferLogicforsaleout());
		bean.setBillForm(getBillFormEditor());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.so.m30.billui.action.assist.ReturnSaleOrderAddFrom4CAction getReturnSaleOutAction() {
		if (context.get("ReturnSaleOutAction") != null)
			return (nc.ui.so.m30.billui.action.assist.ReturnSaleOrderAddFrom4CAction) context
					.get("ReturnSaleOutAction");
		nc.ui.so.m30.billui.action.assist.ReturnSaleOrderAddFrom4CAction bean = new nc.ui.so.m30.billui.action.assist.ReturnSaleOrderAddFrom4CAction();
		context.put("ReturnSaleOutAction", bean);
		bean.setSourceBillType("4C");
		bean.setSourceBillName(getI18nFB_4a8041());
		bean.setFlowBillType(false);
		bean.setModel(getManageAppModel());
		bean.setEditor(getBillFormEditor());
		bean.setTransferBillViewProcessor(getTransferProcessorforsaleout());
		bean.setInterceptor(getFormInterceptor());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private java.lang.String getI18nFB_4a8041() {
		if (context.get("nc.ui.uif2.I18nFB#4a8041") != null)
			return (java.lang.String) context.get("nc.ui.uif2.I18nFB#4a8041");
		nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
		context.put("&nc.ui.uif2.I18nFB#4a8041", bean);
		bean.setResDir("4006011_0");
		bean.setResId("04006011-0465");
		bean.setDefaultValue("销售出库单");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		try {
			Object product = bean.getObject();
			context.put("nc.ui.uif2.I18nFB#4a8041", product);
			return (java.lang.String) product;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public nc.ui.so.m30.billui.action.assist.SaleOrderPriceFormAction getPriceFormAction() {
		if (context.get("priceFormAction") != null)
			return (nc.ui.so.m30.billui.action.assist.SaleOrderPriceFormAction) context
					.get("priceFormAction");
		nc.ui.so.m30.billui.action.assist.SaleOrderPriceFormAction bean = new nc.ui.so.m30.billui.action.assist.SaleOrderPriceFormAction();
		context.put("priceFormAction", bean);
		bean.setModel(getManageAppModel());
		bean.setEditor(getBillFormEditor());
		bean.setListview(getListView());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.so.pub.actions.SOManageDocumentAction getDocManageAction() {
		if (context.get("docManageAction") != null)
			return (nc.ui.so.pub.actions.SOManageDocumentAction) context
					.get("docManageAction");
		nc.ui.so.pub.actions.SOManageDocumentAction bean = new nc.ui.so.pub.actions.SOManageDocumentAction();
		context.put("docManageAction", bean);
		bean.setModel(getManageAppModel());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.so.m30.revise.action.SaleReviseLinkQueryAction getLinkQueryAction() {
		if (context.get("LinkQueryAction") != null)
			return (nc.ui.so.m30.revise.action.SaleReviseLinkQueryAction) context
					.get("LinkQueryAction");
		nc.ui.so.m30.revise.action.SaleReviseLinkQueryAction bean = new nc.ui.so.m30.revise.action.SaleReviseLinkQueryAction();
		context.put("LinkQueryAction", bean);
		bean.setModel(getManageAppModel());
		bean.setBillType("30R");
		bean.setOpenMode(1);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.so.m30.revise.action.M30ReviseHistoryAction getReviseHistoryAction() {
		if (context.get("ReviseHistoryAction") != null)
			return (nc.ui.so.m30.revise.action.M30ReviseHistoryAction) context
					.get("ReviseHistoryAction");
		nc.ui.so.m30.revise.action.M30ReviseHistoryAction bean = new nc.ui.so.m30.revise.action.M30ReviseHistoryAction();
		context.put("ReviseHistoryAction", bean);
		bean.setModel(getManageAppModel());
		bean.setEditor(getBillFormEditor());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.so.m30.billui.action.link.QueryCustInfoAction getQueryCustInfo() {
		if (context.get("QueryCustInfo") != null)
			return (nc.ui.so.m30.billui.action.link.QueryCustInfoAction) context
					.get("QueryCustInfo");
		nc.ui.so.m30.billui.action.link.QueryCustInfoAction bean = new nc.ui.so.m30.billui.action.link.QueryCustInfoAction();
		context.put("QueryCustInfo", bean);
		bean.setModel(getManageAppModel());
		bean.setEditor(getBillFormEditor());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.so.m30.billui.action.link.SaleOrderProfitAction getCrossProfitRptAction() {
		if (context.get("CrossProfitRptAction") != null)
			return (nc.ui.so.m30.billui.action.link.SaleOrderProfitAction) context
					.get("CrossProfitRptAction");
		nc.ui.so.m30.billui.action.link.SaleOrderProfitAction bean = new nc.ui.so.m30.billui.action.link.SaleOrderProfitAction();
		context.put("CrossProfitRptAction", bean);
		bean.setModel(getManageAppModel());
		bean.setEditor(getBillFormEditor());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.so.m30.billui.action.link.QueryExecInfoAction getQueryExecInfoAction() {
		if (context.get("QueryExecInfoAction") != null)
			return (nc.ui.so.m30.billui.action.link.QueryExecInfoAction) context
					.get("QueryExecInfoAction");
		nc.ui.so.m30.billui.action.link.QueryExecInfoAction bean = new nc.ui.so.m30.billui.action.link.QueryExecInfoAction();
		context.put("QueryExecInfoAction", bean);
		bean.setModel(getManageAppModel());
		bean.setEditor(getBillFormEditor());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.funcnode.ui.action.MenuAction getActionGroupgt4() {
		if (context.get("actionGroupgt4") != null)
			return (nc.funcnode.ui.action.MenuAction) context
					.get("actionGroupgt4");
		nc.funcnode.ui.action.MenuAction bean = new nc.funcnode.ui.action.MenuAction();
		context.put("actionGroupgt4", bean);
		bean.setCode("gt4");
		bean.setName(getI18nFB_12a2ea1());
		bean.setActions(getManagedList32());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private java.lang.String getI18nFB_12a2ea1() {
		if (context.get("nc.ui.uif2.I18nFB#12a2ea1") != null)
			return (java.lang.String) context.get("nc.ui.uif2.I18nFB#12a2ea1");
		nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
		context.put("&nc.ui.uif2.I18nFB#12a2ea1", bean);
		bean.setResDir("4006011_0");
		bean.setResId("04006011-0392");
		bean.setDefaultValue("联查");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		try {
			Object product = bean.getObject();
			context.put("nc.ui.uif2.I18nFB#12a2ea1", product);
			return (java.lang.String) product;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private List getManagedList32() {
		List list = new ArrayList();
		list.add(getLinkQueryAction());
		list.add(getReviseHistoryAction());
		list.add(getQueryOnhandAction());
		list.add(getCreditQueryAction());
		list.add(getQueryCustInfo());
		list.add(getCrossProfitRptAction());
		list.add(getQueryExecInfoAction());
		return list;
	}

	public nc.ui.so.m30.billui.action.printaction.SaleOrderPreviewAction getPreviewAction() {
		if (context.get("previewAction") != null)
			return (nc.ui.so.m30.billui.action.printaction.SaleOrderPreviewAction) context
					.get("previewAction");
		nc.ui.so.m30.billui.action.printaction.SaleOrderPreviewAction bean = new nc.ui.so.m30.billui.action.printaction.SaleOrderPreviewAction();
		context.put("previewAction", bean);
		bean.setPreview(true);
		bean.setModel(getManageAppModel());
		bean.setBeforePrintDataProcess(getPrintProcessor());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.so.m30.billui.action.printaction.SaleOrderPrintAction getPrintAction() {
		if (context.get("printAction") != null)
			return (nc.ui.so.m30.billui.action.printaction.SaleOrderPrintAction) context
					.get("printAction");
		nc.ui.so.m30.billui.action.printaction.SaleOrderPrintAction bean = new nc.ui.so.m30.billui.action.printaction.SaleOrderPrintAction();
		context.put("printAction", bean);
		bean.setPreview(false);
		bean.setModel(getManageAppModel());
		bean.setBeforePrintDataProcess(getPrintProcessor());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.so.m30.billui.action.printaction.SaleOrderPrintProcessor getPrintProcessor() {
		if (context.get("printProcessor") != null)
			return (nc.ui.so.m30.billui.action.printaction.SaleOrderPrintProcessor) context
					.get("printProcessor");
		nc.ui.so.m30.billui.action.printaction.SaleOrderPrintProcessor bean = new nc.ui.so.m30.billui.action.printaction.SaleOrderPrintProcessor();
		context.put("printProcessor", bean);
		bean.setModel(getManageAppModel());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.scmpub.action.SCMPrintCountQueryAction getPrintCountQueryAction() {
		if (context.get("printCountQueryAction") != null)
			return (nc.ui.scmpub.action.SCMPrintCountQueryAction) context
					.get("printCountQueryAction");
		nc.ui.scmpub.action.SCMPrintCountQueryAction bean = new nc.ui.scmpub.action.SCMPrintCountQueryAction();
		context.put("printCountQueryAction", bean);
		bean.setModel(getManageAppModel());
		bean.setBilldateFieldName("dbilldate");
		bean.setBillType("30R");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.so.m30.billui.action.printaction.SaleOrderSplitPrintAction getSplitPrintAction() {
		if (context.get("SplitPrintAction") != null)
			return (nc.ui.so.m30.billui.action.printaction.SaleOrderSplitPrintAction) context
					.get("SplitPrintAction");
		nc.ui.so.m30.billui.action.printaction.SaleOrderSplitPrintAction bean = new nc.ui.so.m30.billui.action.printaction.SaleOrderSplitPrintAction();
		context.put("SplitPrintAction", bean);
		bean.setModel(getManageAppModel());
		bean.setPrintAction(getPrintAction());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.funcnode.ui.action.GroupAction getActionGroupgt5() {
		if (context.get("actionGroupgt5") != null)
			return (nc.funcnode.ui.action.GroupAction) context
					.get("actionGroupgt5");
		nc.funcnode.ui.action.GroupAction bean = new nc.funcnode.ui.action.GroupAction();
		context.put("actionGroupgt5", bean);
		bean.setCode("gt5");
		bean.setName(getI18nFB_5b4ead());
		bean.setActions(getManagedList33());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private java.lang.String getI18nFB_5b4ead() {
		if (context.get("nc.ui.uif2.I18nFB#5b4ead") != null)
			return (java.lang.String) context.get("nc.ui.uif2.I18nFB#5b4ead");
		nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
		context.put("&nc.ui.uif2.I18nFB#5b4ead", bean);
		bean.setResDir("common");
		bean.setResId("UC001-0000007");
		bean.setDefaultValue("打印");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		try {
			Object product = bean.getObject();
			context.put("nc.ui.uif2.I18nFB#5b4ead", product);
			return (java.lang.String) product;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private List getManagedList33() {
		List list = new ArrayList();
		list.add(getPrintAction());
		list.add(getPreviewAction());
		list.add(getOutputAction());
		list.add(getSeparatorAction());
		list.add(getSplitPrintAction());
		list.add(getPrintCountQueryAction());
		return list;
	}

	public nc.ui.pubapp.uif2app.actions.OutputAction getOutputAction() {
		if (context.get("outputAction") != null)
			return (nc.ui.pubapp.uif2app.actions.OutputAction) context
					.get("outputAction");
		nc.ui.pubapp.uif2app.actions.OutputAction bean = new nc.ui.pubapp.uif2app.actions.OutputAction();
		context.put("outputAction", bean);
		bean.setModel(getManageAppModel());
		bean.setParent(getBillFormEditor());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.so.m30.billui.action.SaleOrderFindPriceAction getFindPriceAction() {
		if (context.get("findPriceAction") != null)
			return (nc.ui.so.m30.billui.action.SaleOrderFindPriceAction) context
					.get("findPriceAction");
		nc.ui.so.m30.billui.action.SaleOrderFindPriceAction bean = new nc.ui.so.m30.billui.action.SaleOrderFindPriceAction();
		context.put("findPriceAction", bean);
		bean.setModel(getManageAppModel());
		bean.setEditor(getBillFormEditor());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.so.m30.billui.action.SaleOrderLargessApportionAction getLargessApportionAction() {
		if (context.get("LargessApportionAction") != null)
			return (nc.ui.so.m30.billui.action.SaleOrderLargessApportionAction) context
					.get("LargessApportionAction");
		nc.ui.so.m30.billui.action.SaleOrderLargessApportionAction bean = new nc.ui.so.m30.billui.action.SaleOrderLargessApportionAction();
		context.put("LargessApportionAction", bean);
		bean.setModel(getManageAppModel());
		bean.setEditor(getBillFormEditor());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.so.m30.billui.action.SaleOrderUndoLargessApportionAction getUndoLargessApportionAction() {
		if (context.get("UndoLargessApportionAction") != null)
			return (nc.ui.so.m30.billui.action.SaleOrderUndoLargessApportionAction) context
					.get("UndoLargessApportionAction");
		nc.ui.so.m30.billui.action.SaleOrderUndoLargessApportionAction bean = new nc.ui.so.m30.billui.action.SaleOrderUndoLargessApportionAction();
		context.put("UndoLargessApportionAction", bean);
		bean.setModel(getManageAppModel());
		bean.setEditor(getBillFormEditor());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.funcnode.ui.action.MenuAction getActionGroupgt6() {
		if (context.get("actionGroupgt6") != null)
			return (nc.funcnode.ui.action.MenuAction) context
					.get("actionGroupgt6");
		nc.funcnode.ui.action.MenuAction bean = new nc.funcnode.ui.action.MenuAction();
		context.put("actionGroupgt6", bean);
		bean.setCode("gt6");
		bean.setName(getI18nFB_8a4a4b());
		bean.setActions(getManagedList34());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private java.lang.String getI18nFB_8a4a4b() {
		if (context.get("nc.ui.uif2.I18nFB#8a4a4b") != null)
			return (java.lang.String) context.get("nc.ui.uif2.I18nFB#8a4a4b");
		nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
		context.put("&nc.ui.uif2.I18nFB#8a4a4b", bean);
		bean.setResDir("4006011_0");
		bean.setResId("04006011-0400");
		bean.setDefaultValue("辅助功能");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		try {
			Object product = bean.getObject();
			context.put("nc.ui.uif2.I18nFB#8a4a4b", product);
			return (java.lang.String) product;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private List getManagedList34() {
		List list = new ArrayList();
		list.add(getFindPriceAction());
		list.add(getPriceFormAction());
		list.add(getFeatureSelectAction());
		return list;
	}

	public nc.ui.so.m30.billui.action.SaleOrderFeatureSelectAction getFeatureSelectAction() {
		if (context.get("featureSelectAction") != null)
			return (nc.ui.so.m30.billui.action.SaleOrderFeatureSelectAction) context
					.get("featureSelectAction");
		nc.ui.so.m30.billui.action.SaleOrderFeatureSelectAction bean = new nc.ui.so.m30.billui.action.SaleOrderFeatureSelectAction();
		context.put("featureSelectAction", bean);
		bean.setEditor(getBillFormEditor());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.so.pub.actions.SOQueryOnhandAction getQueryOnhandAction() {
		if (context.get("queryOnhandAction") != null)
			return (nc.ui.so.pub.actions.SOQueryOnhandAction) context
					.get("queryOnhandAction");
		nc.ui.so.pub.actions.SOQueryOnhandAction bean = new nc.ui.so.pub.actions.SOQueryOnhandAction();
		context.put("queryOnhandAction", bean);
		bean.setCard(getBillFormEditor());
		bean.setModel(getManageAppModel());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.so.m30.billui.action.link.SaleOrderCreditQueryAction getCreditQueryAction() {
		if (context.get("creditQueryAction") != null)
			return (nc.ui.so.m30.billui.action.link.SaleOrderCreditQueryAction) context
					.get("creditQueryAction");
		nc.ui.so.m30.billui.action.link.SaleOrderCreditQueryAction bean = new nc.ui.so.m30.billui.action.link.SaleOrderCreditQueryAction();
		context.put("creditQueryAction", bean);
		bean.setModel(getManageAppModel());
		bean.setEditor(getBillFormEditor());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.funcnode.ui.action.MenuAction getActionGroupgt7() {
		if (context.get("actionGroupgt7") != null)
			return (nc.funcnode.ui.action.MenuAction) context
					.get("actionGroupgt7");
		nc.funcnode.ui.action.MenuAction bean = new nc.funcnode.ui.action.MenuAction();
		context.put("actionGroupgt7", bean);
		bean.setCode("gt7");
		bean.setName(getI18nFB_113beb9());
		bean.setActions(getManagedList35());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private java.lang.String getI18nFB_113beb9() {
		if (context.get("nc.ui.uif2.I18nFB#113beb9") != null)
			return (java.lang.String) context.get("nc.ui.uif2.I18nFB#113beb9");
		nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
		context.put("&nc.ui.uif2.I18nFB#113beb9", bean);
		bean.setResDir("4006011_0");
		bean.setResId("04006011-0392");
		bean.setDefaultValue("联查");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		try {
			Object product = bean.getObject();
			context.put("nc.ui.uif2.I18nFB#113beb9", product);
			return (java.lang.String) product;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private List getManagedList35() {
		List list = new ArrayList();
		list.add(getQueryOnhandAction());
		list.add(getCreditQueryAction());
		list.add(getQueryCustInfo());
		list.add(getCrossProfitRptAction());
		list.add(getQueryExecInfoAction());
		return list;
	}

	public nc.ui.uif2.DefaultExceptionHanler getExceptionHandler() {
		if (context.get("exceptionHandler") != null)
			return (nc.ui.uif2.DefaultExceptionHanler) context
					.get("exceptionHandler");
		nc.ui.uif2.DefaultExceptionHanler bean = new nc.ui.uif2.DefaultExceptionHanler();
		context.put("exceptionHandler", bean);
		bean.setContext(getContext());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.uif2.editor.UIF2RemoteCallCombinatorCaller getRemoteCallCombinatorCaller() {
		if (context.get("remoteCallCombinatorCaller") != null)
			return (nc.ui.uif2.editor.UIF2RemoteCallCombinatorCaller) context
					.get("remoteCallCombinatorCaller");
		nc.ui.uif2.editor.UIF2RemoteCallCombinatorCaller bean = new nc.ui.uif2.editor.UIF2RemoteCallCombinatorCaller();
		context.put("remoteCallCombinatorCaller", bean);
		bean.setRemoteCallers(getManagedList36());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList36() {
		List list = new ArrayList();
		list.add(getQueryTemplateContainer());
		list.add(getTemplateContainer());
		list.add(getUserdefitemContainer());
		return list;
	}

}
