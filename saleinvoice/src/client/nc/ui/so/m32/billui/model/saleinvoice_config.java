package nc.ui.so.m32.billui.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.ui.uif2.factory.AbstractJavaBeanDefinition;

public class saleinvoice_config extends AbstractJavaBeanDefinition {
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

	public nc.ui.so.m32.billui.model.SaleInvoiceService getManageModelService() {
		if (context.get("ManageModelService") != null)
			return (nc.ui.so.m32.billui.model.SaleInvoiceService) context
					.get("ManageModelService");
		nc.ui.so.m32.billui.model.SaleInvoiceService bean = new nc.ui.so.m32.billui.model.SaleInvoiceService();
		context.put("ManageModelService", bean);
		bean.setModel(getManageAppModel());
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

	public nc.ui.pubapp.uif2app.actions.PfAddInfoLoader getPfAddInfoLoader() {
		if (context.get("pfAddInfoLoader") != null)
			return (nc.ui.pubapp.uif2app.actions.PfAddInfoLoader) context
					.get("pfAddInfoLoader");
		nc.ui.pubapp.uif2app.actions.PfAddInfoLoader bean = new nc.ui.pubapp.uif2app.actions.PfAddInfoLoader();
		context.put("pfAddInfoLoader", bean);
		bean.setBillType("32");
		bean.setModel(getManageAppModel());
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
		bean.setRemoteCallers(getManagedList0());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList0() {
		List list = new ArrayList();
		list.add(getQueryTemplateContainer());
		list.add(getTemplateContainer());
		list.add(getUserdefitemContainer());
		list.add(getPfAddInfoLoader());
		return list;
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

	public nc.ui.so.m32.billui.model.SaleInvoicePageService getPageQuery() {
		if (context.get("pageQuery") != null)
			return (nc.ui.so.m32.billui.model.SaleInvoicePageService) context
					.get("pageQuery");
		nc.ui.so.m32.billui.model.SaleInvoicePageService bean = new nc.ui.so.m32.billui.model.SaleInvoicePageService();
		context.put("pageQuery", bean);
		bean.setModel(getManageAppModel());
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

	public nc.ui.so.m32.billui.model.SaleInvoiceManageModel getManageAppModel() {
		if (context.get("ManageAppModel") != null)
			return (nc.ui.so.m32.billui.model.SaleInvoiceManageModel) context
					.get("ManageAppModel");
		nc.ui.so.m32.billui.model.SaleInvoiceManageModel bean = new nc.ui.so.m32.billui.model.SaleInvoiceManageModel();
		context.put("ManageAppModel", bean);
		bean.setService(getManageModelService());
		bean.setBillType("32");
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

	public nc.ui.pubapp.uif2app.funcnode.trantype.TrantypeBillTemplateMender getTrantypeTempMender() {
		if (context.get("trantypeTempMender") != null)
			return (nc.ui.pubapp.uif2app.funcnode.trantype.TrantypeBillTemplateMender) context
					.get("trantypeTempMender");
		nc.ui.pubapp.uif2app.funcnode.trantype.TrantypeBillTemplateMender bean = new nc.ui.pubapp.uif2app.funcnode.trantype.TrantypeBillTemplateMender(
				getContext());
		context.put("trantypeTempMender", bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.view.TemplateContainer getTemplateContainer() {
		if (context.get("templateContainer") != null)
			return (nc.ui.pubapp.uif2app.view.TemplateContainer) context
					.get("templateContainer");
		nc.ui.pubapp.uif2app.view.TemplateContainer bean = new nc.ui.pubapp.uif2app.view.TemplateContainer();
		context.put("templateContainer", bean);
		bean.setContext(getContext());
		bean.setBillTemplateMender(getTrantypeTempMender());
		bean.load();
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

	public nc.ui.so.m32.billui.view.SaleInvoiceListView getListView() {
		if (context.get("listView") != null)
			return (nc.ui.so.m32.billui.view.SaleInvoiceListView) context
					.get("listView");
		nc.ui.so.m32.billui.view.SaleInvoiceListView bean = new nc.ui.so.m32.billui.view.SaleInvoiceListView();
		context.put("listView", bean);
		bean.setModel(getManageAppModel());
		bean.setMultiSelectionMode(0);
		bean.setTemplateContainer(getTemplateContainer());
		bean.setPaginationBar(getPageBar());
		bean.setUserdefitemListPreparator(getCompositeBillListDataPrepare_c9eed2());
		bean.initUI();
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.pubapp.uif2app.view.CompositeBillListDataPrepare getCompositeBillListDataPrepare_c9eed2() {
		if (context
				.get("nc.ui.pubapp.uif2app.view.CompositeBillListDataPrepare#c9eed2") != null)
			return (nc.ui.pubapp.uif2app.view.CompositeBillListDataPrepare) context
					.get("nc.ui.pubapp.uif2app.view.CompositeBillListDataPrepare#c9eed2");
		nc.ui.pubapp.uif2app.view.CompositeBillListDataPrepare bean = new nc.ui.pubapp.uif2app.view.CompositeBillListDataPrepare();
		context.put(
				"nc.ui.pubapp.uif2app.view.CompositeBillListDataPrepare#c9eed2",
				bean);
		bean.setBillListDataPrepares(getManagedList1());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList1() {
		List list = new ArrayList();
		list.add(getUserdefitemlistPreparator());
		list.add(getMarAsstPreparator());
		return list;
	}

	public nc.ui.so.m32.billui.view.SaleInvoiceEditor getBillFormEditor() {
		if (context.get("billFormEditor") != null)
			return (nc.ui.so.m32.billui.view.SaleInvoiceEditor) context
					.get("billFormEditor");
		nc.ui.so.m32.billui.view.SaleInvoiceEditor bean = new nc.ui.so.m32.billui.view.SaleInvoiceEditor();
		context.put("billFormEditor", bean);
		bean.setModel(getManageAppModel());
		bean.setTemplateContainer(getTemplateContainer());
		bean.setTemplateNotNullValidate(true);
		bean.setAutoAddLine(true);
		bean.setUserdefitemPreparator(getCompositeBillDataPrepare_15e5af2());
		bean.setBlankChildrenFilter(getSingleFieldBlankChildrenFilter_1a4cec6());
		bean.setClearHyperlink(getManagedList3());
		bean.setBodyLineActions(getManagedList4());
		bean.initUI();
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.pubapp.uif2app.view.CompositeBillDataPrepare getCompositeBillDataPrepare_15e5af2() {
		if (context
				.get("nc.ui.pubapp.uif2app.view.CompositeBillDataPrepare#15e5af2") != null)
			return (nc.ui.pubapp.uif2app.view.CompositeBillDataPrepare) context
					.get("nc.ui.pubapp.uif2app.view.CompositeBillDataPrepare#15e5af2");
		nc.ui.pubapp.uif2app.view.CompositeBillDataPrepare bean = new nc.ui.pubapp.uif2app.view.CompositeBillDataPrepare();
		context.put(
				"nc.ui.pubapp.uif2app.view.CompositeBillDataPrepare#15e5af2",
				bean);
		bean.setBillDataPrepares(getManagedList2());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList2() {
		List list = new ArrayList();
		list.add(getUserdefitemPreparator());
		list.add(getMarAsstPreparator());
		return list;
	}

	private nc.ui.pubapp.uif2app.view.value.SingleFieldBlankChildrenFilter getSingleFieldBlankChildrenFilter_1a4cec6() {
		if (context
				.get("nc.ui.pubapp.uif2app.view.value.SingleFieldBlankChildrenFilter#1a4cec6") != null)
			return (nc.ui.pubapp.uif2app.view.value.SingleFieldBlankChildrenFilter) context
					.get("nc.ui.pubapp.uif2app.view.value.SingleFieldBlankChildrenFilter#1a4cec6");
		nc.ui.pubapp.uif2app.view.value.SingleFieldBlankChildrenFilter bean = new nc.ui.pubapp.uif2app.view.value.SingleFieldBlankChildrenFilter();
		context.put(
				"nc.ui.pubapp.uif2app.view.value.SingleFieldBlankChildrenFilter#1a4cec6",
				bean);
		bean.setFieldName("cmaterialvid");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList3() {
		List list = new ArrayList();
		list.add("vbillcode");
		return list;
	}

	private List getManagedList4() {
		List list = new ArrayList();
		list.add(getBodyAddLineAction_1034eef());
		list.add(getBodyInsertLineAction_335fb1());
		list.add(getInvoiceBodyDelLineAction_cf95fc());
		list.add(getBodyCopyLineAction_800c39());
		list.add(getInvoiceBodyPasteLineAction_a2b311());
		list.add(getInvoiceBodyPasteToTailAction_19a2584());
		list.add(getLinebarseparatorAction());
		list.add(getRearrangeRowNoBodyLineAction_1153760());
		list.add(getLinebarseparatorAction());
		list.add(getDefaultBodyZoomAction_1e69ffb());
		return list;
	}

	private nc.ui.pubapp.uif2app.actions.BodyAddLineAction getBodyAddLineAction_1034eef() {
		if (context
				.get("nc.ui.pubapp.uif2app.actions.BodyAddLineAction#1034eef") != null)
			return (nc.ui.pubapp.uif2app.actions.BodyAddLineAction) context
					.get("nc.ui.pubapp.uif2app.actions.BodyAddLineAction#1034eef");
		nc.ui.pubapp.uif2app.actions.BodyAddLineAction bean = new nc.ui.pubapp.uif2app.actions.BodyAddLineAction();
		context.put("nc.ui.pubapp.uif2app.actions.BodyAddLineAction#1034eef",
				bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.pubapp.uif2app.actions.BodyInsertLineAction getBodyInsertLineAction_335fb1() {
		if (context
				.get("nc.ui.pubapp.uif2app.actions.BodyInsertLineAction#335fb1") != null)
			return (nc.ui.pubapp.uif2app.actions.BodyInsertLineAction) context
					.get("nc.ui.pubapp.uif2app.actions.BodyInsertLineAction#335fb1");
		nc.ui.pubapp.uif2app.actions.BodyInsertLineAction bean = new nc.ui.pubapp.uif2app.actions.BodyInsertLineAction();
		context.put("nc.ui.pubapp.uif2app.actions.BodyInsertLineAction#335fb1",
				bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.so.m32.billui.action.line.InvoiceBodyDelLineAction getInvoiceBodyDelLineAction_cf95fc() {
		if (context
				.get("nc.ui.so.m32.billui.action.line.InvoiceBodyDelLineAction#cf95fc") != null)
			return (nc.ui.so.m32.billui.action.line.InvoiceBodyDelLineAction) context
					.get("nc.ui.so.m32.billui.action.line.InvoiceBodyDelLineAction#cf95fc");
		nc.ui.so.m32.billui.action.line.InvoiceBodyDelLineAction bean = new nc.ui.so.m32.billui.action.line.InvoiceBodyDelLineAction();
		context.put(
				"nc.ui.so.m32.billui.action.line.InvoiceBodyDelLineAction#cf95fc",
				bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.pubapp.uif2app.actions.BodyCopyLineAction getBodyCopyLineAction_800c39() {
		if (context
				.get("nc.ui.pubapp.uif2app.actions.BodyCopyLineAction#800c39") != null)
			return (nc.ui.pubapp.uif2app.actions.BodyCopyLineAction) context
					.get("nc.ui.pubapp.uif2app.actions.BodyCopyLineAction#800c39");
		nc.ui.pubapp.uif2app.actions.BodyCopyLineAction bean = new nc.ui.pubapp.uif2app.actions.BodyCopyLineAction();
		context.put("nc.ui.pubapp.uif2app.actions.BodyCopyLineAction#800c39",
				bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.so.m32.billui.action.line.InvoiceBodyPasteLineAction getInvoiceBodyPasteLineAction_a2b311() {
		if (context
				.get("nc.ui.so.m32.billui.action.line.InvoiceBodyPasteLineAction#a2b311") != null)
			return (nc.ui.so.m32.billui.action.line.InvoiceBodyPasteLineAction) context
					.get("nc.ui.so.m32.billui.action.line.InvoiceBodyPasteLineAction#a2b311");
		nc.ui.so.m32.billui.action.line.InvoiceBodyPasteLineAction bean = new nc.ui.so.m32.billui.action.line.InvoiceBodyPasteLineAction();
		context.put(
				"nc.ui.so.m32.billui.action.line.InvoiceBodyPasteLineAction#a2b311",
				bean);
		bean.setClearItems(getManagedList5());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList5() {
		List list = new ArrayList();
		list.add("csaleinvoicebid");
		list.add("ts");
		return list;
	}

	private nc.ui.so.m32.billui.action.line.InvoiceBodyPasteToTailAction getInvoiceBodyPasteToTailAction_19a2584() {
		if (context
				.get("nc.ui.so.m32.billui.action.line.InvoiceBodyPasteToTailAction#19a2584") != null)
			return (nc.ui.so.m32.billui.action.line.InvoiceBodyPasteToTailAction) context
					.get("nc.ui.so.m32.billui.action.line.InvoiceBodyPasteToTailAction#19a2584");
		nc.ui.so.m32.billui.action.line.InvoiceBodyPasteToTailAction bean = new nc.ui.so.m32.billui.action.line.InvoiceBodyPasteToTailAction();
		context.put(
				"nc.ui.so.m32.billui.action.line.InvoiceBodyPasteToTailAction#19a2584",
				bean);
		bean.setClearItems(getManagedList6());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList6() {
		List list = new ArrayList();
		list.add("csaleinvoicebid");
		list.add("ts");
		return list;
	}

	private nc.ui.pubapp.uif2app.actions.RearrangeRowNoBodyLineAction getRearrangeRowNoBodyLineAction_1153760() {
		if (context
				.get("nc.ui.pubapp.uif2app.actions.RearrangeRowNoBodyLineAction#1153760") != null)
			return (nc.ui.pubapp.uif2app.actions.RearrangeRowNoBodyLineAction) context
					.get("nc.ui.pubapp.uif2app.actions.RearrangeRowNoBodyLineAction#1153760");
		nc.ui.pubapp.uif2app.actions.RearrangeRowNoBodyLineAction bean = new nc.ui.pubapp.uif2app.actions.RearrangeRowNoBodyLineAction();
		context.put(
				"nc.ui.pubapp.uif2app.actions.RearrangeRowNoBodyLineAction#1153760",
				bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.pubapp.uif2app.actions.DefaultBodyZoomAction getDefaultBodyZoomAction_1e69ffb() {
		if (context
				.get("nc.ui.pubapp.uif2app.actions.DefaultBodyZoomAction#1e69ffb") != null)
			return (nc.ui.pubapp.uif2app.actions.DefaultBodyZoomAction) context
					.get("nc.ui.pubapp.uif2app.actions.DefaultBodyZoomAction#1e69ffb");
		nc.ui.pubapp.uif2app.actions.DefaultBodyZoomAction bean = new nc.ui.pubapp.uif2app.actions.DefaultBodyZoomAction();
		context.put(
				"nc.ui.pubapp.uif2app.actions.DefaultBodyZoomAction#1e69ffb",
				bean);
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
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.view.CardPanelOrgSetterForAllRefMediator getCardPanelOrgSetterForAllRefMediator() {
		if (context.get("cardPanelOrgSetterForAllRefMediator") != null)
			return (nc.ui.pubapp.uif2app.view.CardPanelOrgSetterForAllRefMediator) context
					.get("cardPanelOrgSetterForAllRefMediator");
		nc.ui.pubapp.uif2app.view.CardPanelOrgSetterForAllRefMediator bean = new nc.ui.pubapp.uif2app.view.CardPanelOrgSetterForAllRefMediator(
				getBillFormEditor());
		context.put("cardPanelOrgSetterForAllRefMediator", bean);
		bean.setModel(getManageAppModel());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
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
		bean.setHandlerMap(getManagedMap0());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private Map getManagedMap0() {
		Map map = new HashMap();
		map.put("nc.ui.pubapp.uif2app.event.card.CardHeadTailBeforeEditEvent",
				getManagedList7());
		map.put("nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent",
				getManagedList8());
		map.put("nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent",
				getManagedList9());
		map.put("nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent",
				getManagedList10());
		map.put("nc.ui.pubapp.uif2app.event.card.CardBodyAfterRowEditEvent",
				getManagedList11());
		map.put("nc.ui.pubapp.uif2app.event.OrgChangedEvent",
				getManagedList12());
		return map;
	}

	private List getManagedList7() {
		List list = new ArrayList();
		list.add(getHeadBeforeEditHandler_1e26cd6());
		return list;
	}

	private nc.ui.so.m32.billui.editor.headevent.HeadBeforeEditHandler getHeadBeforeEditHandler_1e26cd6() {
		if (context
				.get("nc.ui.so.m32.billui.editor.headevent.HeadBeforeEditHandler#1e26cd6") != null)
			return (nc.ui.so.m32.billui.editor.headevent.HeadBeforeEditHandler) context
					.get("nc.ui.so.m32.billui.editor.headevent.HeadBeforeEditHandler#1e26cd6");
		nc.ui.so.m32.billui.editor.headevent.HeadBeforeEditHandler bean = new nc.ui.so.m32.billui.editor.headevent.HeadBeforeEditHandler();
		context.put(
				"nc.ui.so.m32.billui.editor.headevent.HeadBeforeEditHandler#1e26cd6",
				bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList8() {
		List list = new ArrayList();
		list.add(getHeadAfterEditHandler_5784ba());
		return list;
	}

	private nc.ui.so.m32.billui.editor.headevent.HeadAfterEditHandler getHeadAfterEditHandler_5784ba() {
		if (context
				.get("nc.ui.so.m32.billui.editor.headevent.HeadAfterEditHandler#5784ba") != null)
			return (nc.ui.so.m32.billui.editor.headevent.HeadAfterEditHandler) context
					.get("nc.ui.so.m32.billui.editor.headevent.HeadAfterEditHandler#5784ba");
		nc.ui.so.m32.billui.editor.headevent.HeadAfterEditHandler bean = new nc.ui.so.m32.billui.editor.headevent.HeadAfterEditHandler();
		context.put(
				"nc.ui.so.m32.billui.editor.headevent.HeadAfterEditHandler#5784ba",
				bean);
		bean.setModel(getManageAppModel());
		bean.setEditor(getBillFormEditor());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList9() {
		List list = new ArrayList();
		list.add(getBodyBeforeEditHandler_1cb07ee());
		return list;
	}

	private nc.ui.so.m32.billui.editor.bodyevent.BodyBeforeEditHandler getBodyBeforeEditHandler_1cb07ee() {
		if (context
				.get("nc.ui.so.m32.billui.editor.bodyevent.BodyBeforeEditHandler#1cb07ee") != null)
			return (nc.ui.so.m32.billui.editor.bodyevent.BodyBeforeEditHandler) context
					.get("nc.ui.so.m32.billui.editor.bodyevent.BodyBeforeEditHandler#1cb07ee");
		nc.ui.so.m32.billui.editor.bodyevent.BodyBeforeEditHandler bean = new nc.ui.so.m32.billui.editor.bodyevent.BodyBeforeEditHandler();
		context.put(
				"nc.ui.so.m32.billui.editor.bodyevent.BodyBeforeEditHandler#1cb07ee",
				bean);
		bean.setModel(getManageAppModel());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList10() {
		List list = new ArrayList();
		list.add(getBodyAfterEditHandler_75b4d2());
		return list;
	}

	private nc.ui.so.m32.billui.editor.bodyevent.BodyAfterEditHandler getBodyAfterEditHandler_75b4d2() {
		if (context
				.get("nc.ui.so.m32.billui.editor.bodyevent.BodyAfterEditHandler#75b4d2") != null)
			return (nc.ui.so.m32.billui.editor.bodyevent.BodyAfterEditHandler) context
					.get("nc.ui.so.m32.billui.editor.bodyevent.BodyAfterEditHandler#75b4d2");
		nc.ui.so.m32.billui.editor.bodyevent.BodyAfterEditHandler bean = new nc.ui.so.m32.billui.editor.bodyevent.BodyAfterEditHandler();
		context.put(
				"nc.ui.so.m32.billui.editor.bodyevent.BodyAfterEditHandler#75b4d2",
				bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList11() {
		List list = new ArrayList();
		list.add(getCardBodyAfterRowEditHandler_1b27356());
		return list;
	}

	private nc.ui.so.m32.billui.editor.bodyevent.CardBodyAfterRowEditHandler getCardBodyAfterRowEditHandler_1b27356() {
		if (context
				.get("nc.ui.so.m32.billui.editor.bodyevent.CardBodyAfterRowEditHandler#1b27356") != null)
			return (nc.ui.so.m32.billui.editor.bodyevent.CardBodyAfterRowEditHandler) context
					.get("nc.ui.so.m32.billui.editor.bodyevent.CardBodyAfterRowEditHandler#1b27356");
		nc.ui.so.m32.billui.editor.bodyevent.CardBodyAfterRowEditHandler bean = new nc.ui.so.m32.billui.editor.bodyevent.CardBodyAfterRowEditHandler();
		context.put(
				"nc.ui.so.m32.billui.editor.bodyevent.CardBodyAfterRowEditHandler#1b27356",
				bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList12() {
		List list = new ArrayList();
		list.add(getOrgEditHandler_b238e6());
		return list;
	}

	private nc.ui.so.m32.billui.editor.orgevent.OrgEditHandler getOrgEditHandler_b238e6() {
		if (context
				.get("nc.ui.so.m32.billui.editor.orgevent.OrgEditHandler#b238e6") != null)
			return (nc.ui.so.m32.billui.editor.orgevent.OrgEditHandler) context
					.get("nc.ui.so.m32.billui.editor.orgevent.OrgEditHandler#b238e6");
		nc.ui.so.m32.billui.editor.orgevent.OrgEditHandler bean = new nc.ui.so.m32.billui.editor.orgevent.OrgEditHandler(
				getBillFormEditor(), getContext());
		context.put(
				"nc.ui.so.m32.billui.editor.orgevent.OrgEditHandler#b238e6",
				bean);
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

	public nc.ui.pubapp.uif2app.tangramlayout.UECardLayoutToolbarPanel getCardToolbarPnl() {
		if (context.get("cardToolbarPnl") != null)
			return (nc.ui.pubapp.uif2app.tangramlayout.UECardLayoutToolbarPanel) context
					.get("cardToolbarPnl");
		nc.ui.pubapp.uif2app.tangramlayout.UECardLayoutToolbarPanel bean = new nc.ui.pubapp.uif2app.tangramlayout.UECardLayoutToolbarPanel();
		context.put("cardToolbarPnl", bean);
		bean.setTitleAction(getReturnaction());
		bean.setModel(getManageAppModel());
		bean.setRightExActions(getManagedList13());
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

	private List getManagedList13() {
		List list = new ArrayList();
		list.add(getActionsBar_ActionsBarSeparator_b5b6a9());
		list.add(getHeadZoomAction());
		return list;
	}

	private nc.ui.pub.beans.ActionsBar.ActionsBarSeparator getActionsBar_ActionsBarSeparator_b5b6a9() {
		if (context
				.get("nc.ui.pub.beans.ActionsBar.ActionsBarSeparator#b5b6a9") != null)
			return (nc.ui.pub.beans.ActionsBar.ActionsBarSeparator) context
					.get("nc.ui.pub.beans.ActionsBar.ActionsBarSeparator#b5b6a9");
		nc.ui.pub.beans.ActionsBar.ActionsBarSeparator bean = new nc.ui.pub.beans.ActionsBar.ActionsBarSeparator();
		context.put("nc.ui.pub.beans.ActionsBar.ActionsBarSeparator#b5b6a9",
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

	public nc.ui.uif2.TangramContainer getContainer() {
		if (context.get("container") != null)
			return (nc.ui.uif2.TangramContainer) context.get("container");
		nc.ui.uif2.TangramContainer bean = new nc.ui.uif2.TangramContainer();
		context.put("container", bean);
		bean.setModel(getManageAppModel());
		bean.setTangramLayoutRoot(getTBNode_12e7ce1());
		bean.initUI();
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.uif2.tangramlayout.node.TBNode getTBNode_12e7ce1() {
		if (context.get("nc.ui.uif2.tangramlayout.node.TBNode#12e7ce1") != null)
			return (nc.ui.uif2.tangramlayout.node.TBNode) context
					.get("nc.ui.uif2.tangramlayout.node.TBNode#12e7ce1");
		nc.ui.uif2.tangramlayout.node.TBNode bean = new nc.ui.uif2.tangramlayout.node.TBNode();
		context.put("nc.ui.uif2.tangramlayout.node.TBNode#12e7ce1", bean);
		bean.setShowMode("CardLayout");
		bean.setTabs(getManagedList14());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList14() {
		List list = new ArrayList();
		list.add(getHSNode_11239d5());
		list.add(getVSNode_137fea2());
		return list;
	}

	private nc.ui.uif2.tangramlayout.node.HSNode getHSNode_11239d5() {
		if (context.get("nc.ui.uif2.tangramlayout.node.HSNode#11239d5") != null)
			return (nc.ui.uif2.tangramlayout.node.HSNode) context
					.get("nc.ui.uif2.tangramlayout.node.HSNode#11239d5");
		nc.ui.uif2.tangramlayout.node.HSNode bean = new nc.ui.uif2.tangramlayout.node.HSNode();
		context.put("nc.ui.uif2.tangramlayout.node.HSNode#11239d5", bean);
		bean.setLeft(getCNode_1eb230c());
		bean.setRight(getVSNode_b0952e());
		bean.setDividerLocation(0.22f);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.uif2.tangramlayout.node.CNode getCNode_1eb230c() {
		if (context.get("nc.ui.uif2.tangramlayout.node.CNode#1eb230c") != null)
			return (nc.ui.uif2.tangramlayout.node.CNode) context
					.get("nc.ui.uif2.tangramlayout.node.CNode#1eb230c");
		nc.ui.uif2.tangramlayout.node.CNode bean = new nc.ui.uif2.tangramlayout.node.CNode();
		context.put("nc.ui.uif2.tangramlayout.node.CNode#1eb230c", bean);
		bean.setComponent(getQueryArea());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.uif2.tangramlayout.node.VSNode getVSNode_b0952e() {
		if (context.get("nc.ui.uif2.tangramlayout.node.VSNode#b0952e") != null)
			return (nc.ui.uif2.tangramlayout.node.VSNode) context
					.get("nc.ui.uif2.tangramlayout.node.VSNode#b0952e");
		nc.ui.uif2.tangramlayout.node.VSNode bean = new nc.ui.uif2.tangramlayout.node.VSNode();
		context.put("nc.ui.uif2.tangramlayout.node.VSNode#b0952e", bean);
		bean.setUp(getCNode_4a0db7());
		bean.setDown(getCNode_1c852bf());
		bean.setDividerLocation(25f);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.uif2.tangramlayout.node.CNode getCNode_4a0db7() {
		if (context.get("nc.ui.uif2.tangramlayout.node.CNode#4a0db7") != null)
			return (nc.ui.uif2.tangramlayout.node.CNode) context
					.get("nc.ui.uif2.tangramlayout.node.CNode#4a0db7");
		nc.ui.uif2.tangramlayout.node.CNode bean = new nc.ui.uif2.tangramlayout.node.CNode();
		context.put("nc.ui.uif2.tangramlayout.node.CNode#4a0db7", bean);
		bean.setComponent(getListToolbarPnl());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.uif2.tangramlayout.node.CNode getCNode_1c852bf() {
		if (context.get("nc.ui.uif2.tangramlayout.node.CNode#1c852bf") != null)
			return (nc.ui.uif2.tangramlayout.node.CNode) context
					.get("nc.ui.uif2.tangramlayout.node.CNode#1c852bf");
		nc.ui.uif2.tangramlayout.node.CNode bean = new nc.ui.uif2.tangramlayout.node.CNode();
		context.put("nc.ui.uif2.tangramlayout.node.CNode#1c852bf", bean);
		bean.setName(getI18nFB_1ca78ea());
		bean.setComponent(getListView());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private java.lang.String getI18nFB_1ca78ea() {
		if (context.get("nc.ui.uif2.I18nFB#1ca78ea") != null)
			return (java.lang.String) context.get("nc.ui.uif2.I18nFB#1ca78ea");
		nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
		context.put("&nc.ui.uif2.I18nFB#1ca78ea", bean);
		bean.setResDir("common");
		bean.setResId("UC001-0000107");
		bean.setDefaultValue("列表");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		try {
			Object product = bean.getObject();
			context.put("nc.ui.uif2.I18nFB#1ca78ea", product);
			return (java.lang.String) product;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private nc.ui.uif2.tangramlayout.node.VSNode getVSNode_137fea2() {
		if (context.get("nc.ui.uif2.tangramlayout.node.VSNode#137fea2") != null)
			return (nc.ui.uif2.tangramlayout.node.VSNode) context
					.get("nc.ui.uif2.tangramlayout.node.VSNode#137fea2");
		nc.ui.uif2.tangramlayout.node.VSNode bean = new nc.ui.uif2.tangramlayout.node.VSNode();
		context.put("nc.ui.uif2.tangramlayout.node.VSNode#137fea2", bean);
		bean.setUp(getCNode_192e2fd());
		bean.setDown(getCNode_16ddbe());
		bean.setDividerLocation(30f);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.uif2.tangramlayout.node.CNode getCNode_192e2fd() {
		if (context.get("nc.ui.uif2.tangramlayout.node.CNode#192e2fd") != null)
			return (nc.ui.uif2.tangramlayout.node.CNode) context
					.get("nc.ui.uif2.tangramlayout.node.CNode#192e2fd");
		nc.ui.uif2.tangramlayout.node.CNode bean = new nc.ui.uif2.tangramlayout.node.CNode();
		context.put("nc.ui.uif2.tangramlayout.node.CNode#192e2fd", bean);
		bean.setComponent(getCardToolbarPnl());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.uif2.tangramlayout.node.CNode getCNode_16ddbe() {
		if (context.get("nc.ui.uif2.tangramlayout.node.CNode#16ddbe") != null)
			return (nc.ui.uif2.tangramlayout.node.CNode) context
					.get("nc.ui.uif2.tangramlayout.node.CNode#16ddbe");
		nc.ui.uif2.tangramlayout.node.CNode bean = new nc.ui.uif2.tangramlayout.node.CNode();
		context.put("nc.ui.uif2.tangramlayout.node.CNode#16ddbe", bean);
		bean.setName(getI18nFB_745f36());
		bean.setComponent(getBillFormEditor());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private java.lang.String getI18nFB_745f36() {
		if (context.get("nc.ui.uif2.I18nFB#745f36") != null)
			return (java.lang.String) context.get("nc.ui.uif2.I18nFB#745f36");
		nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
		context.put("&nc.ui.uif2.I18nFB#745f36", bean);
		bean.setResDir("common");
		bean.setResId("UC001-0000106");
		bean.setDefaultValue("卡片");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		try {
			Object product = bean.getObject();
			context.put("nc.ui.uif2.I18nFB#745f36", product);
			return (java.lang.String) product;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public nc.ui.uif2.actions.ActionContributors getToftpanelActionContributors() {
		if (context.get("toftpanelActionContributors") != null)
			return (nc.ui.uif2.actions.ActionContributors) context
					.get("toftpanelActionContributors");
		nc.ui.uif2.actions.ActionContributors bean = new nc.ui.uif2.actions.ActionContributors();
		context.put("toftpanelActionContributors", bean);
		bean.setContributors(getManagedList15());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList15() {
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
		bean.setActions(getManagedList16());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList16() {
		List list = new ArrayList();
		list.add(getAddMenuAction());
		list.add(getEditAction());
		list.add(getDeleteAction());
		list.add(getSeparatorAction());
		list.add(getQueryAction());
		list.add(getRefreshAction());
		list.add(getShowTypeAction());
		list.add(getSeparatorAction());
		list.add(getCommitGroupAction());
		list.add(getApproveGroupAction());
		list.add(getBrowAstFuncActionGroup());
		list.add(getSeparatorAction());
		list.add(getBrowLinkActionGroup());
		list.add(getSeparatorAction());
		list.add(getPrintActionGroup());
		list.add(getSeparatorAction());
		list.add(getReceiveAction());
		list.add(getSendAction());
		list.add(getSeparatorAction());
		list.add(getSendysdAction());
		list.add(getUNsendysdAction());
		return list;
	}

	public nc.ui.uif2.actions.StandAloneToftPanelActionContainer getActionsOfCard() {
		if (context.get("actionsOfCard") != null)
			return (nc.ui.uif2.actions.StandAloneToftPanelActionContainer) context
					.get("actionsOfCard");
		nc.ui.uif2.actions.StandAloneToftPanelActionContainer bean = new nc.ui.uif2.actions.StandAloneToftPanelActionContainer(
				getBillFormEditor());
		context.put("actionsOfCard", bean);
		bean.setModel(getManageAppModel());
		bean.setActions(getManagedList17());
		bean.setEditActions(getManagedList18());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList17() {
		List list = new ArrayList();
		list.add(getAddMenuAction());
		list.add(getEditAction());
		list.add(getDeleteAction());
		list.add(getSeparatorAction());
		list.add(getQueryAction());
		list.add(getCardRefreshAction());
		list.add(getShowTypeAction());
		list.add(getSeparatorAction());
		list.add(getCommitGroupAction());
		list.add(getApproveGroupAction());
		list.add(getBrowAstFuncActionGroup());
		list.add(getSeparatorAction());
		list.add(getBrowLinkActionGroup());
		list.add(getSeparatorAction());
		list.add(getPrintActionGroup());
		list.add(getSeparatorAction());
		list.add(getReceiveAction());
		list.add(getSendAction());
		list.add(getSeparatorAction());
		list.add(getSendysdAction());
		list.add(getUNsendysdAction());
		return list;
	}

	private List getManagedList18() {
		List list = new ArrayList();
		list.add(getSaveAction());
		list.add(getSaveandcommitAction());
		list.add(getSeparatorAction());
		list.add(getCancelAction());
		list.add(getSeparatorAction());
		list.add(getRefAddLineAction());
		list.add(getEditAstFuncActionGroup());
		list.add(getSeparatorAction());
		list.add(getEditLinkActionGroup());
		return list;
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

	public nc.ui.pub.beans.ActionsBar.ActionsBarSeparator getLinebarseparatorAction() {
		if (context.get("linebarseparatorAction") != null)
			return (nc.ui.pub.beans.ActionsBar.ActionsBarSeparator) context
					.get("linebarseparatorAction");
		nc.ui.pub.beans.ActionsBar.ActionsBarSeparator bean = new nc.ui.pub.beans.ActionsBar.ActionsBarSeparator();
		context.put("linebarseparatorAction", bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.so.m32.billui.action.SaleInvoiceShowChgAction getShowTypeAction() {
		if (context.get("showTypeAction") != null)
			return (nc.ui.so.m32.billui.action.SaleInvoiceShowChgAction) context
					.get("showTypeAction");
		nc.ui.so.m32.billui.action.SaleInvoiceShowChgAction bean = new nc.ui.so.m32.billui.action.SaleInvoiceShowChgAction();
		context.put("showTypeAction", bean);
		bean.setModel(getManageAppModel());
		bean.setEditor(getBillFormEditor());
		bean.setListView(getListView());
		bean.setLasilyLoder(getLasilyLodadMediator());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.so.m32.billui.action.SaleInvoiceCommitAction getCommitAction() {
		if (context.get("commitAction") != null)
			return (nc.ui.so.m32.billui.action.SaleInvoiceCommitAction) context
					.get("commitAction");
		nc.ui.so.m32.billui.action.SaleInvoiceCommitAction bean = new nc.ui.so.m32.billui.action.SaleInvoiceCommitAction();
		context.put("commitAction", bean);
		bean.setModel(getManageAppModel());
		bean.setEditor(getBillFormEditor());
		bean.setFilledUpInFlow(true);
		bean.setActionName("SAVE");
		bean.setBillType("32");
		bean.setValidationService(getPowercommitvalidservice());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.so.m32.billui.action.SaleInvoiceSaveCommitAction getSaveandcommitAction() {
		if (context.get("saveandcommitAction") != null)
			return (nc.ui.so.m32.billui.action.SaleInvoiceSaveCommitAction) context
					.get("saveandcommitAction");
		nc.ui.so.m32.billui.action.SaleInvoiceSaveCommitAction bean = new nc.ui.so.m32.billui.action.SaleInvoiceSaveCommitAction(
				getSaveAction(), getCommitAction());
		context.put("saveandcommitAction", bean);
		bean.setModel(getManageAppModel());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.so.m32.billui.action.SaleInvoiceUnCommitAction getUnCommitAction() {
		if (context.get("unCommitAction") != null)
			return (nc.ui.so.m32.billui.action.SaleInvoiceUnCommitAction) context
					.get("unCommitAction");
		nc.ui.so.m32.billui.action.SaleInvoiceUnCommitAction bean = new nc.ui.so.m32.billui.action.SaleInvoiceUnCommitAction();
		context.put("unCommitAction", bean);
		bean.setModel(getManageAppModel());
		bean.setEditor(getBillFormEditor());
		bean.setFilledUpInFlow(true);
		bean.setActionName("UNSAVE");
		bean.setBillType("32");
		bean.setValidationService(getPoweruncommitvalidservice());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.so.m32.billui.action.SaleInvoiceUnApproveAction getUnApproveAction() {
		if (context.get("unApproveAction") != null)
			return (nc.ui.so.m32.billui.action.SaleInvoiceUnApproveAction) context
					.get("unApproveAction");
		nc.ui.so.m32.billui.action.SaleInvoiceUnApproveAction bean = new nc.ui.so.m32.billui.action.SaleInvoiceUnApproveAction();
		context.put("unApproveAction", bean);
		bean.setModel(getManageAppModel());
		bean.setEditor(getBillFormEditor());
		bean.setFilledUpInFlow(true);
		bean.setActionName("UNAPPROVE");
		bean.setBillType("32");
		bean.setValidationService(getPowerunapprovevalidservice());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.so.m32.billui.action.SaleInvoiceApproveAction getApproveAction() {
		if (context.get("approveAction") != null)
			return (nc.ui.so.m32.billui.action.SaleInvoiceApproveAction) context
					.get("approveAction");
		nc.ui.so.m32.billui.action.SaleInvoiceApproveAction bean = new nc.ui.so.m32.billui.action.SaleInvoiceApproveAction();
		context.put("approveAction", bean);
		bean.setModel(getManageAppModel());
		bean.setEditor(getBillFormEditor());
		bean.setFilledUpInFlow(true);
		bean.setActionName("APPROVE");
		bean.setBillType("32");
		bean.setValidationService(getPowerapprovevalidservice());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.funcnode.ui.action.GroupAction getCommitGroupAction() {
		if (context.get("commitGroupAction") != null)
			return (nc.funcnode.ui.action.GroupAction) context
					.get("commitGroupAction");
		nc.funcnode.ui.action.GroupAction bean = new nc.funcnode.ui.action.GroupAction();
		context.put("commitGroupAction", bean);
		bean.setActions(getManagedList19());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList19() {
		List list = new ArrayList();
		list.add(getCommitAction());
		list.add(getUnCommitAction());
		return list;
	}

	public nc.funcnode.ui.action.GroupAction getApproveGroupAction() {
		if (context.get("approveGroupAction") != null)
			return (nc.funcnode.ui.action.GroupAction) context
					.get("approveGroupAction");
		nc.funcnode.ui.action.GroupAction bean = new nc.funcnode.ui.action.GroupAction();
		context.put("approveGroupAction", bean);
		bean.setActions(getManagedList20());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList20() {
		List list = new ArrayList();
		list.add(getApproveAction());
		list.add(getUnApproveAction());
		list.add(getSeparatorAction());
		list.add(getAuditFlowAction());
		return list;
	}

	public nc.ui.pubapp.uif2app.query2.action.DefaultQueryAction getQueryAction() {
		if (context.get("queryAction") != null)
			return (nc.ui.pubapp.uif2app.query2.action.DefaultQueryAction) context
					.get("queryAction");
		nc.ui.pubapp.uif2app.query2.action.DefaultQueryAction bean = new nc.ui.pubapp.uif2app.query2.action.DefaultQueryAction();
		context.put("queryAction", bean);
		bean.setModel(getManageAppModel());
		bean.setDataManager(getModelDataManager());
		bean.setTemplateContainer(getQueryTemplateContainer());
		bean.setQryCondDLGInitializer(getSaleinvoiceQryCondDLGInitializer());
		bean.setShowUpComponent(getListView());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.so.m32.billui.query.SaleInvoiceQryCondDLGInitializer getSaleinvoiceQryCondDLGInitializer() {
		if (context.get("saleinvoiceQryCondDLGInitializer") != null)
			return (nc.ui.so.m32.billui.query.SaleInvoiceQryCondDLGInitializer) context
					.get("saleinvoiceQryCondDLGInitializer");
		nc.ui.so.m32.billui.query.SaleInvoiceQryCondDLGInitializer bean = new nc.ui.so.m32.billui.query.SaleInvoiceQryCondDLGInitializer();
		context.put("saleinvoiceQryCondDLGInitializer", bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.query2.action.DefaultRefreshAction getRefreshAction() {
		if (context.get("refreshAction") != null)
			return (nc.ui.pubapp.uif2app.query2.action.DefaultRefreshAction) context
					.get("refreshAction");
		nc.ui.pubapp.uif2app.query2.action.DefaultRefreshAction bean = new nc.ui.pubapp.uif2app.query2.action.DefaultRefreshAction();
		context.put("refreshAction", bean);
		bean.setModel(getManageAppModel());
		bean.setDataManager(getModelDataManager());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.so.m32.billui.action.InvoiceRefreshSingleAction getCardRefreshAction() {
		if (context.get("cardRefreshAction") != null)
			return (nc.ui.so.m32.billui.action.InvoiceRefreshSingleAction) context
					.get("cardRefreshAction");
		nc.ui.so.m32.billui.action.InvoiceRefreshSingleAction bean = new nc.ui.so.m32.billui.action.InvoiceRefreshSingleAction();
		context.put("cardRefreshAction", bean);
		bean.setModel(getManageAppModel());
		bean.setDataManager(getModelDataManager());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.so.m32.billui.action.add.InvoiceAddMenuAction getAddMenuAction() {
		if (context.get("addMenuAction") != null)
			return (nc.ui.so.m32.billui.action.add.InvoiceAddMenuAction) context
					.get("addMenuAction");
		nc.ui.so.m32.billui.action.add.InvoiceAddMenuAction bean = new nc.ui.so.m32.billui.action.add.InvoiceAddMenuAction();
		context.put("addMenuAction", bean);
		bean.setBillType("32");
		bean.setActions(getManagedList21());
		bean.setModel(getManageAppModel());
		bean.setPfAddInfoLoader(getPfAddInfoLoader());
		bean.initUI();
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList21() {
		List list = new ArrayList();
		list.add(getRefOrderAction());
		list.add(getRefOutAction());
		list.add(getRefYlxsAction());
		list.add(getRefYffyAction());
		list.add(getRefCtxsAction());
		list.add(getRefGcdlAction());
		list.add(getRefFgdlAction());
		list.add(getRefJkylAction());
		list.add(getAddHyfjsdAction());
		return list;
	}

	public nc.ui.so.m32.billref.hyfjsd.AddUpActionHyfjsd getAddHyfjsdAction() {
		if (context.get("addHyfjsdAction") != null)
			return (nc.ui.so.m32.billref.hyfjsd.AddUpActionHyfjsd) context
					.get("addHyfjsdAction");
		nc.ui.so.m32.billref.hyfjsd.AddUpActionHyfjsd bean = new nc.ui.so.m32.billref.hyfjsd.AddUpActionHyfjsd();
		context.put("addHyfjsdAction", bean);
		bean.setSourceBillType("HY01");
		bean.setSourceBillName("海运费结算单");
		bean.setFlowBillType(false);
		bean.setModel(getManageAppModel());
		bean.setEditor(getBillFormEditor());
		bean.setTransferViewProcessor(getTransferProcessorforhyfjsd());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.billref.dest.TransferViewProcessor getTransferProcessorforhyfjsd() {
		if (context.get("transferProcessorforhyfjsd") != null)
			return (nc.ui.pubapp.billref.dest.TransferViewProcessor) context
					.get("transferProcessorforhyfjsd");
		nc.ui.pubapp.billref.dest.TransferViewProcessor bean = new nc.ui.pubapp.billref.dest.TransferViewProcessor();
		context.put("transferProcessorforhyfjsd", bean);
		bean.setList(getListView());
		bean.setActionContainer(getActionsOfList());
		bean.setCardActionContainer(getActionsOfCard());
		bean.setTransferLogic(getTransferLogicforhyfjsd());
		bean.setBillForm(getBillFormEditor());
		bean.setCancelAction(getCancelAction());
		bean.setSaveAction(getSaveAction());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.so.m32.billref.hyfjsd.UpRefDownTRansferBillDataLogicHyfjsd getTransferLogicforhyfjsd() {
		if (context.get("transferLogicforhyfjsd") != null)
			return (nc.ui.so.m32.billref.hyfjsd.UpRefDownTRansferBillDataLogicHyfjsd) context
					.get("transferLogicforhyfjsd");
		nc.ui.so.m32.billref.hyfjsd.UpRefDownTRansferBillDataLogicHyfjsd bean = new nc.ui.so.m32.billref.hyfjsd.UpRefDownTRansferBillDataLogicHyfjsd();
		context.put("transferLogicforhyfjsd", bean);
		bean.setBillForm(getBillFormEditor());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.so.m32.billui.action.add.RefOrderAddAction getRefOrderAction() {
		if (context.get("refOrderAction") != null)
			return (nc.ui.so.m32.billui.action.add.RefOrderAddAction) context
					.get("refOrderAction");
		nc.ui.so.m32.billui.action.add.RefOrderAddAction bean = new nc.ui.so.m32.billui.action.add.RefOrderAddAction();
		context.put("refOrderAction", bean);
		bean.setSourceBillType("30");
		bean.setSourceBillName(getI18nFB_108a4b1());
		bean.setModel(getManageAppModel());
		bean.setEditor(getBillFormEditor());
		bean.setTransferViewProcessor(getTransferViewProcessor());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private java.lang.String getI18nFB_108a4b1() {
		if (context.get("nc.ui.uif2.I18nFB#108a4b1") != null)
			return (java.lang.String) context.get("nc.ui.uif2.I18nFB#108a4b1");
		nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
		context.put("&nc.ui.uif2.I18nFB#108a4b1", bean);
		bean.setResDir("4006008_0");
		bean.setResId("04006008-0119");
		bean.setDefaultValue("销售订单");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		try {
			Object product = bean.getObject();
			context.put("nc.ui.uif2.I18nFB#108a4b1", product);
			return (java.lang.String) product;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public nc.ui.so.m32.billui.action.add.RefOutAddAction getRefOutAction() {
		if (context.get("refOutAction") != null)
			return (nc.ui.so.m32.billui.action.add.RefOutAddAction) context
					.get("refOutAction");
		nc.ui.so.m32.billui.action.add.RefOutAddAction bean = new nc.ui.so.m32.billui.action.add.RefOutAddAction();
		context.put("refOutAction", bean);
		bean.setSourceBillType("4C");
		bean.setSourceBillName(getI18nFB_747ebe());
		bean.setModel(getManageAppModel());
		bean.setEditor(getBillFormEditor());
		bean.setTransferViewProcessor(getTransferViewProcessor());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private java.lang.String getI18nFB_747ebe() {
		if (context.get("nc.ui.uif2.I18nFB#747ebe") != null)
			return (java.lang.String) context.get("nc.ui.uif2.I18nFB#747ebe");
		nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
		context.put("&nc.ui.uif2.I18nFB#747ebe", bean);
		bean.setResDir("4006008_0");
		bean.setResId("04006008-0120");
		bean.setDefaultValue("销售出库单");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		try {
			Object product = bean.getObject();
			context.put("nc.ui.uif2.I18nFB#747ebe", product);
			return (java.lang.String) product;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public nc.ui.so.m32.billui.action.add.RefYffyAddAction getRefYffyAction() {
		if (context.get("refYffyAction") != null)
			return (nc.ui.so.m32.billui.action.add.RefYffyAddAction) context
					.get("refYffyAction");
		nc.ui.so.m32.billui.action.add.RefYffyAddAction bean = new nc.ui.so.m32.billui.action.add.RefYffyAddAction();
		context.put("refYffyAction", bean);
		bean.setSourceBillType("LM21");
		bean.setSourceBillName("应付费用结算单");
		bean.setModel(getManageAppModel());
		bean.setEditor(getBillFormEditor());
		bean.setTransferViewProcessor(getTransferViewProcessor());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.so.m32.billui.action.add.RefYlxsAddAction getRefYlxsAction() {
		if (context.get("refYlxsAction") != null)
			return (nc.ui.so.m32.billui.action.add.RefYlxsAddAction) context
					.get("refYlxsAction");
		nc.ui.so.m32.billui.action.add.RefYlxsAddAction bean = new nc.ui.so.m32.billui.action.add.RefYlxsAddAction();
		context.put("refYlxsAction", bean);
		bean.setSourceBillType("IT02");
		bean.setSourceBillName("原料销售结算单");
		bean.setModel(getManageAppModel());
		bean.setEditor(getBillFormEditor());
		bean.setTransferViewProcessor(getTransferViewProcessor());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.so.m32.billui.action.add.RefCtxsAddAction getRefCtxsAction() {
		if (context.get("refCtxsAction") != null)
			return (nc.ui.so.m32.billui.action.add.RefCtxsAddAction) context
					.get("refCtxsAction");
		nc.ui.so.m32.billui.action.add.RefCtxsAddAction bean = new nc.ui.so.m32.billui.action.add.RefCtxsAddAction();
		context.put("refCtxsAction", bean);
		bean.setSourceBillType("IT01");
		bean.setSourceBillName("成套销售结算单");
		bean.setModel(getManageAppModel());
		bean.setEditor(getBillFormEditor());
		bean.setTransferViewProcessor(getTransferViewProcessor());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.so.m32.billui.action.add.RefGcdlAddAction getRefGcdlAction() {
		if (context.get("refGcdlAction") != null)
			return (nc.ui.so.m32.billui.action.add.RefGcdlAddAction) context
					.get("refGcdlAction");
		nc.ui.so.m32.billui.action.add.RefGcdlAddAction bean = new nc.ui.so.m32.billui.action.add.RefGcdlAddAction();
		context.put("refGcdlAction", bean);
		bean.setSourceBillType("ET03");
		bean.setSourceBillName("钢材代理结算单");
		bean.setModel(getManageAppModel());
		bean.setEditor(getBillFormEditor());
		bean.setTransferViewProcessor(getTransferViewProcessor());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.so.m32.billui.action.add.RefFgdlAddAction getRefFgdlAction() {
		if (context.get("refFgdlAction") != null)
			return (nc.ui.so.m32.billui.action.add.RefFgdlAddAction) context
					.get("refFgdlAction");
		nc.ui.so.m32.billui.action.add.RefFgdlAddAction bean = new nc.ui.so.m32.billui.action.add.RefFgdlAddAction();
		context.put("refFgdlAction", bean);
		bean.setSourceBillType("ET02");
		bean.setSourceBillName("非钢代理结算单");
		bean.setModel(getManageAppModel());
		bean.setEditor(getBillFormEditor());
		bean.setTransferViewProcessor(getTransferViewProcessor());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.so.m32.billui.action.add.RefJkyltestAddAction getRefJkylAction() {
		if (context.get("refJkylAction") != null)
			return (nc.ui.so.m32.billui.action.add.RefJkyltestAddAction) context
					.get("refJkylAction");
		nc.ui.so.m32.billui.action.add.RefJkyltestAddAction bean = new nc.ui.so.m32.billui.action.add.RefJkyltestAddAction();
		context.put("refJkylAction", bean);
		bean.setSourceBillType("LM40");
		bean.setSourceBillName("进口原料报账单");
		bean.setModel(getManageAppModel());
		bean.setEditor(getBillFormEditor());
		bean.setTransferViewProcessor(getTransferViewProcessor());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.so.m32.billui.action.add.InvoiceAddMenuAction getRefAddLineAction() {
		if (context.get("refAddLineAction") != null)
			return (nc.ui.so.m32.billui.action.add.InvoiceAddMenuAction) context
					.get("refAddLineAction");
		nc.ui.so.m32.billui.action.add.InvoiceAddMenuAction bean = new nc.ui.so.m32.billui.action.add.InvoiceAddMenuAction();
		context.put("refAddLineAction", bean);
		bean.setBillType("32");
		bean.setCode("RefAddLine");
		bean.setName(getI18nFB_2d06d9());
		bean.setActions(getManagedList22());
		bean.setModel(getManageAppModel());
		bean.initUI();
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private java.lang.String getI18nFB_2d06d9() {
		if (context.get("nc.ui.uif2.I18nFB#2d06d9") != null)
			return (java.lang.String) context.get("nc.ui.uif2.I18nFB#2d06d9");
		nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
		context.put("&nc.ui.uif2.I18nFB#2d06d9", bean);
		bean.setResDir("4006008_0");
		bean.setResId("04006008-0124");
		bean.setDefaultValue("参照增行");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		try {
			Object product = bean.getObject();
			context.put("nc.ui.uif2.I18nFB#2d06d9", product);
			return (java.lang.String) product;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private List getManagedList22() {
		List list = new ArrayList();
		list.add(getRefOrderlineAction());
		list.add(getRefOutlineAction());
		return list;
	}

	public nc.ui.so.m32.billui.action.line.RefOrderAddLineAction getRefOrderlineAction() {
		if (context.get("refOrderlineAction") != null)
			return (nc.ui.so.m32.billui.action.line.RefOrderAddLineAction) context
					.get("refOrderlineAction");
		nc.ui.so.m32.billui.action.line.RefOrderAddLineAction bean = new nc.ui.so.m32.billui.action.line.RefOrderAddLineAction();
		context.put("refOrderlineAction", bean);
		bean.setSourceBillType("30");
		bean.setSourceBillName(getI18nFB_1cb7a1());
		bean.setModel(getManageAppModel());
		bean.setEditor(getBillFormEditor());
		bean.setTransferViewProcessor(getTransferViewProcessor());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private java.lang.String getI18nFB_1cb7a1() {
		if (context.get("nc.ui.uif2.I18nFB#1cb7a1") != null)
			return (java.lang.String) context.get("nc.ui.uif2.I18nFB#1cb7a1");
		nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
		context.put("&nc.ui.uif2.I18nFB#1cb7a1", bean);
		bean.setResDir("4006008_0");
		bean.setResId("04006008-0119");
		bean.setDefaultValue("销售订单");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		try {
			Object product = bean.getObject();
			context.put("nc.ui.uif2.I18nFB#1cb7a1", product);
			return (java.lang.String) product;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public nc.ui.so.m32.billui.action.line.RefOutAddLineAction getRefOutlineAction() {
		if (context.get("refOutlineAction") != null)
			return (nc.ui.so.m32.billui.action.line.RefOutAddLineAction) context
					.get("refOutlineAction");
		nc.ui.so.m32.billui.action.line.RefOutAddLineAction bean = new nc.ui.so.m32.billui.action.line.RefOutAddLineAction();
		context.put("refOutlineAction", bean);
		bean.setSourceBillType("4C");
		bean.setSourceBillName(getI18nFB_15bbb7f());
		bean.setModel(getManageAppModel());
		bean.setEditor(getBillFormEditor());
		bean.setTransferViewProcessor(getTransferViewProcessor());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private java.lang.String getI18nFB_15bbb7f() {
		if (context.get("nc.ui.uif2.I18nFB#15bbb7f") != null)
			return (java.lang.String) context.get("nc.ui.uif2.I18nFB#15bbb7f");
		nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
		context.put("&nc.ui.uif2.I18nFB#15bbb7f", bean);
		bean.setResDir("4006008_0");
		bean.setResId("04006008-0120");
		bean.setDefaultValue("销售出库单");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		try {
			Object product = bean.getObject();
			context.put("nc.ui.uif2.I18nFB#15bbb7f", product);
			return (java.lang.String) product;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public nc.ui.so.m32.billui.action.SaleInvoiceEditAction getEditAction() {
		if (context.get("editAction") != null)
			return (nc.ui.so.m32.billui.action.SaleInvoiceEditAction) context
					.get("editAction");
		nc.ui.so.m32.billui.action.SaleInvoiceEditAction bean = new nc.ui.so.m32.billui.action.SaleInvoiceEditAction();
		context.put("editAction", bean);
		bean.setModel(getManageAppModel());
		bean.setEditor(getBillFormEditor());
		bean.setInterceptor(getShowUpComponentInterceptor_172ae51());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor getShowUpComponentInterceptor_172ae51() {
		if (context
				.get("nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor#172ae51") != null)
			return (nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor) context
					.get("nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor#172ae51");
		nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor bean = new nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor();
		context.put(
				"nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor#172ae51",
				bean);
		bean.setShowUpComponent(getBillFormEditor());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.so.m32.billui.action.SaleInvoiceDeleteAction getDeleteAction() {
		if (context.get("deleteAction") != null)
			return (nc.ui.so.m32.billui.action.SaleInvoiceDeleteAction) context
					.get("deleteAction");
		nc.ui.so.m32.billui.action.SaleInvoiceDeleteAction bean = new nc.ui.so.m32.billui.action.SaleInvoiceDeleteAction();
		context.put("deleteAction", bean);
		bean.setModel(getManageAppModel());
		bean.setEditor(getBillFormEditor());
		bean.setActionName("DELETE");
		bean.setBillType("32");
		bean.setValidationService(getPowerdeletevalidservice());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.so.m32.billui.action.SaleInvoiceSaveAction getSaveAction() {
		if (context.get("saveAction") != null)
			return (nc.ui.so.m32.billui.action.SaleInvoiceSaveAction) context
					.get("saveAction");
		nc.ui.so.m32.billui.action.SaleInvoiceSaveAction bean = new nc.ui.so.m32.billui.action.SaleInvoiceSaveAction();
		context.put("saveAction", bean);
		bean.setModel(getManageAppModel());
		bean.setEditor(getBillFormEditor());
		bean.setValidationService(getCompositevalidateService());
		bean.setActionName("WRITE");
		bean.setBillType("32");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.validation.CompositeValidation getCompositevalidateService() {
		if (context.get("compositevalidateService") != null)
			return (nc.ui.pubapp.uif2app.validation.CompositeValidation) context
					.get("compositevalidateService");
		nc.ui.pubapp.uif2app.validation.CompositeValidation bean = new nc.ui.pubapp.uif2app.validation.CompositeValidation();
		context.put("compositevalidateService", bean);
		bean.setValidators(getManagedList23());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList23() {
		List list = new ArrayList();
		list.add(getValidateService());
		return list;
	}

	public nc.ui.so.m32.billui.model.SaleInvoiceValidationService getValidateService() {
		if (context.get("validateService") != null)
			return (nc.ui.so.m32.billui.model.SaleInvoiceValidationService) context
					.get("validateService");
		nc.ui.so.m32.billui.model.SaleInvoiceValidationService bean = new nc.ui.so.m32.billui.model.SaleInvoiceValidationService();
		context.put("validateService", bean);
		bean.setModel(getManageAppModel());
		bean.setEditor(getBillFormEditor());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.so.m32.billui.action.SaleInvoiceCancelAction getCancelAction() {
		if (context.get("cancelAction") != null)
			return (nc.ui.so.m32.billui.action.SaleInvoiceCancelAction) context
					.get("cancelAction");
		nc.ui.so.m32.billui.action.SaleInvoiceCancelAction bean = new nc.ui.so.m32.billui.action.SaleInvoiceCancelAction();
		context.put("cancelAction", bean);
		bean.setModel(getManageAppModel());
		bean.setEditor(getBillFormEditor());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.funcnode.ui.action.MenuAction getBrowAstFuncActionGroup() {
		if (context.get("browAstFuncActionGroup") != null)
			return (nc.funcnode.ui.action.MenuAction) context
					.get("browAstFuncActionGroup");
		nc.funcnode.ui.action.MenuAction bean = new nc.funcnode.ui.action.MenuAction();
		context.put("browAstFuncActionGroup", bean);
		bean.setCode("BrowAstFunc");
		bean.setName(getI18nFB_12719d());
		bean.setActions(getManagedList24());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private java.lang.String getI18nFB_12719d() {
		if (context.get("nc.ui.uif2.I18nFB#12719d") != null)
			return (java.lang.String) context.get("nc.ui.uif2.I18nFB#12719d");
		nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
		context.put("&nc.ui.uif2.I18nFB#12719d", bean);
		bean.setResDir("4006008_0");
		bean.setResId("04006008-0121");
		bean.setDefaultValue("辅助功能");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		try {
			Object product = bean.getObject();
			context.put("nc.ui.uif2.I18nFB#12719d", product);
			return (java.lang.String) product;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private List getManagedList24() {
		List list = new ArrayList();
		list.add(getOpposeAddAction());
		list.add(getImptTaxCodeAction());
		list.add(getExptGoldTaxAction());
		list.add(getExptGoldTaxAction2());
		list.add(getExptGoldTaxAction3());
		list.add(getExptGoldTaxAction4());
		list.add(getSeparatorAction());
		list.add(getVatSubTotalAction());
		list.add(getSeparatorAction());
		list.add(getDocmngAction());
		return list;
	}

	public nc.ui.so.m32.billui.action.add.OpposeAddAction getOpposeAddAction() {
		if (context.get("opposeAddAction") != null)
			return (nc.ui.so.m32.billui.action.add.OpposeAddAction) context
					.get("opposeAddAction");
		nc.ui.so.m32.billui.action.add.OpposeAddAction bean = new nc.ui.so.m32.billui.action.add.OpposeAddAction();
		context.put("opposeAddAction", bean);
		bean.setSourceBillType("OpposeAdd");
		bean.setSourceBillName(getI18nFB_e11b47());
		bean.setModel(getManageAppModel());
		bean.setInterceptor(getCompositeActionInterceptor_19c902e());
		bean.setEditor(getBillFormEditor());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private java.lang.String getI18nFB_e11b47() {
		if (context.get("nc.ui.uif2.I18nFB#e11b47") != null)
			return (java.lang.String) context.get("nc.ui.uif2.I18nFB#e11b47");
		nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
		context.put("&nc.ui.uif2.I18nFB#e11b47", bean);
		bean.setResDir("4006008_0");
		bean.setResId("04006008-0122");
		bean.setDefaultValue("生成对冲发票");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		try {
			Object product = bean.getObject();
			context.put("nc.ui.uif2.I18nFB#e11b47", product);
			return (java.lang.String) product;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private nc.ui.pubapp.uif2app.actions.interceptor.CompositeActionInterceptor getCompositeActionInterceptor_19c902e() {
		if (context
				.get("nc.ui.pubapp.uif2app.actions.interceptor.CompositeActionInterceptor#19c902e") != null)
			return (nc.ui.pubapp.uif2app.actions.interceptor.CompositeActionInterceptor) context
					.get("nc.ui.pubapp.uif2app.actions.interceptor.CompositeActionInterceptor#19c902e");
		nc.ui.pubapp.uif2app.actions.interceptor.CompositeActionInterceptor bean = new nc.ui.pubapp.uif2app.actions.interceptor.CompositeActionInterceptor();
		context.put(
				"nc.ui.pubapp.uif2app.actions.interceptor.CompositeActionInterceptor#19c902e",
				bean);
		bean.setInterceptors(getManagedList25());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList25() {
		List list = new ArrayList();
		list.add(getShowUpComponentInterceptor_c61157());
		return list;
	}

	private nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor getShowUpComponentInterceptor_c61157() {
		if (context
				.get("nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor#c61157") != null)
			return (nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor) context
					.get("nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor#c61157");
		nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor bean = new nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor();
		context.put(
				"nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor#c61157",
				bean);
		bean.setShowUpComponent(getBillFormEditor());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.so.m32.billui.action.SaleInvoiceReceiveAction getReceiveAction() {
		if (context.get("receiveAction") != null)
			return (nc.ui.so.m32.billui.action.SaleInvoiceReceiveAction) context
					.get("receiveAction");
		nc.ui.so.m32.billui.action.SaleInvoiceReceiveAction bean = new nc.ui.so.m32.billui.action.SaleInvoiceReceiveAction();
		context.put("receiveAction", bean);
		bean.setModel(getManageAppModel());
		bean.setBtnName("接收");
		bean.setCode("cdessss");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.so.m32.billui.action.SaleInvoiceSendAction getSendAction() {
		if (context.get("sendAction") != null)
			return (nc.ui.so.m32.billui.action.SaleInvoiceSendAction) context
					.get("sendAction");
		nc.ui.so.m32.billui.action.SaleInvoiceSendAction bean = new nc.ui.so.m32.billui.action.SaleInvoiceSendAction();
		context.put("sendAction", bean);
		bean.setModel(getManageAppModel());
		bean.setBtnName("发送");
		bean.setCode("cdessss");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.so.m32.billui.action.ast.ImportTaxCodeAction getImptTaxCodeAction() {
		if (context.get("imptTaxCodeAction") != null)
			return (nc.ui.so.m32.billui.action.ast.ImportTaxCodeAction) context
					.get("imptTaxCodeAction");
		nc.ui.so.m32.billui.action.ast.ImportTaxCodeAction bean = new nc.ui.so.m32.billui.action.ast.ImportTaxCodeAction();
		context.put("imptTaxCodeAction", bean);
		bean.setModel(getManageAppModel());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.so.m32.billui.action.ast.ExportGoldTaxAction getExptGoldTaxAction() {
		if (context.get("exptGoldTaxAction") != null)
			return (nc.ui.so.m32.billui.action.ast.ExportGoldTaxAction) context
					.get("exptGoldTaxAction");
		nc.ui.so.m32.billui.action.ast.ExportGoldTaxAction bean = new nc.ui.so.m32.billui.action.ast.ExportGoldTaxAction();
		context.put("exptGoldTaxAction", bean);
		bean.setModel(getManageAppModel());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.so.m32.billui.action.ast.ExportGoldTaxAction getExptGoldTaxAction2() {
		if (context.get("exptGoldTaxAction2") != null)
			return (nc.ui.so.m32.billui.action.ast.ExportGoldTaxAction) context
					.get("exptGoldTaxAction2");
		nc.ui.so.m32.billui.action.ast.ExportGoldTaxAction bean = new nc.ui.so.m32.billui.action.ast.ExportGoldTaxAction();
		context.put("exptGoldTaxAction2", bean);
		bean.setModel(getManageAppModel());
		bean.setBusiType("1");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.so.m32.billui.action.ast.ExportGoldTaxAction getExptGoldTaxAction3() {
		if (context.get("exptGoldTaxAction3") != null)
			return (nc.ui.so.m32.billui.action.ast.ExportGoldTaxAction) context
					.get("exptGoldTaxAction3");
		nc.ui.so.m32.billui.action.ast.ExportGoldTaxAction bean = new nc.ui.so.m32.billui.action.ast.ExportGoldTaxAction();
		context.put("exptGoldTaxAction3", bean);
		bean.setModel(getManageAppModel());
		bean.setBusiType("2");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.so.m32.billui.action.ast.ExportGoldTaxAction getExptGoldTaxAction4() {
		if (context.get("exptGoldTaxAction4") != null)
			return (nc.ui.so.m32.billui.action.ast.ExportGoldTaxAction) context
					.get("exptGoldTaxAction4");
		nc.ui.so.m32.billui.action.ast.ExportGoldTaxAction bean = new nc.ui.so.m32.billui.action.ast.ExportGoldTaxAction();
		context.put("exptGoldTaxAction4", bean);
		bean.setModel(getManageAppModel());
		bean.setBusiType("3");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.so.m32.billui.action.ast.VatSubTotalAction getVatSubTotalAction() {
		if (context.get("vatSubTotalAction") != null)
			return (nc.ui.so.m32.billui.action.ast.VatSubTotalAction) context
					.get("vatSubTotalAction");
		nc.ui.so.m32.billui.action.ast.VatSubTotalAction bean = new nc.ui.so.m32.billui.action.ast.VatSubTotalAction();
		context.put("vatSubTotalAction", bean);
		bean.setModel(getManageAppModel());
		bean.setEditor(getBillFormEditor());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.so.pub.actions.SOManageDocumentAction getDocmngAction() {
		if (context.get("docmngAction") != null)
			return (nc.ui.so.pub.actions.SOManageDocumentAction) context
					.get("docmngAction");
		nc.ui.so.pub.actions.SOManageDocumentAction bean = new nc.ui.so.pub.actions.SOManageDocumentAction();
		context.put("docmngAction", bean);
		bean.setModel(getManageAppModel());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.funcnode.ui.action.MenuAction getEditAstFuncActionGroup() {
		if (context.get("editAstFuncActionGroup") != null)
			return (nc.funcnode.ui.action.MenuAction) context
					.get("editAstFuncActionGroup");
		nc.funcnode.ui.action.MenuAction bean = new nc.funcnode.ui.action.MenuAction();
		context.put("editAstFuncActionGroup", bean);
		bean.setCode("EditAstFunc");
		bean.setName(getI18nFB_1bdfb68());
		bean.setActions(getManagedList26());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private java.lang.String getI18nFB_1bdfb68() {
		if (context.get("nc.ui.uif2.I18nFB#1bdfb68") != null)
			return (java.lang.String) context.get("nc.ui.uif2.I18nFB#1bdfb68");
		nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
		context.put("&nc.ui.uif2.I18nFB#1bdfb68", bean);
		bean.setResDir("4006008_0");
		bean.setResId("04006008-0121");
		bean.setDefaultValue("辅助功能");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		try {
			Object product = bean.getObject();
			context.put("nc.ui.uif2.I18nFB#1bdfb68", product);
			return (java.lang.String) product;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private List getManagedList26() {
		List list = new ArrayList();
		list.add(getFetchCostAction());
		list.add(getSeparatorAction());
		list.add(getUniteArsubAction());
		list.add(getCancelUniteAction());
		list.add(getSeparatorAction());
		list.add(getVatSubTotalAction());
		return list;
	}

	public nc.ui.so.m32.billui.action.ast.FetchCostAction getFetchCostAction() {
		if (context.get("fetchCostAction") != null)
			return (nc.ui.so.m32.billui.action.ast.FetchCostAction) context
					.get("fetchCostAction");
		nc.ui.so.m32.billui.action.ast.FetchCostAction bean = new nc.ui.so.m32.billui.action.ast.FetchCostAction();
		context.put("fetchCostAction", bean);
		bean.setModel(getManageAppModel());
		bean.setEditor(getBillFormEditor());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.so.m32.billui.action.ast.UniteArsubAction getUniteArsubAction() {
		if (context.get("uniteArsubAction") != null)
			return (nc.ui.so.m32.billui.action.ast.UniteArsubAction) context
					.get("uniteArsubAction");
		nc.ui.so.m32.billui.action.ast.UniteArsubAction bean = new nc.ui.so.m32.billui.action.ast.UniteArsubAction();
		context.put("uniteArsubAction", bean);
		bean.setModel(getManageAppModel());
		bean.setEditor(getBillFormEditor());
		bean.setCancelUniteBtn(getCancelUniteAction());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.so.m32.billui.action.ast.CancelUniteAction getCancelUniteAction() {
		if (context.get("cancelUniteAction") != null)
			return (nc.ui.so.m32.billui.action.ast.CancelUniteAction) context
					.get("cancelUniteAction");
		nc.ui.so.m32.billui.action.ast.CancelUniteAction bean = new nc.ui.so.m32.billui.action.ast.CancelUniteAction();
		context.put("cancelUniteAction", bean);
		bean.setModel(getManageAppModel());
		bean.setEditor(getBillFormEditor());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.funcnode.ui.action.MenuAction getBrowLinkActionGroup() {
		if (context.get("browLinkActionGroup") != null)
			return (nc.funcnode.ui.action.MenuAction) context
					.get("browLinkActionGroup");
		nc.funcnode.ui.action.MenuAction bean = new nc.funcnode.ui.action.MenuAction();
		context.put("browLinkActionGroup", bean);
		bean.setCode("BrowLink");
		bean.setName(getI18nFB_1c49c00());
		bean.setActions(getManagedList27());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private java.lang.String getI18nFB_1c49c00() {
		if (context.get("nc.ui.uif2.I18nFB#1c49c00") != null)
			return (java.lang.String) context.get("nc.ui.uif2.I18nFB#1c49c00");
		nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
		context.put("&nc.ui.uif2.I18nFB#1c49c00", bean);
		bean.setResDir("4006008_0");
		bean.setResId("04006008-0123");
		bean.setDefaultValue("联查");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		try {
			Object product = bean.getObject();
			context.put("nc.ui.uif2.I18nFB#1c49c00", product);
			return (java.lang.String) product;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private List getManagedList27() {
		List list = new ArrayList();
		list.add(getLinkQueryAction());
		list.add(getCustCreditAction());
		list.add(getCustInfoAction());
		list.add(getEstProfitAction());
		list.add(getExecQueryAction());
		list.add(getQueryCashArsubAction());
		return list;
	}

	public nc.scmmm.ui.uif2.actions.SCMLinkQueryAction getLinkQueryAction() {
		if (context.get("linkQueryAction") != null)
			return (nc.scmmm.ui.uif2.actions.SCMLinkQueryAction) context
					.get("linkQueryAction");
		nc.scmmm.ui.uif2.actions.SCMLinkQueryAction bean = new nc.scmmm.ui.uif2.actions.SCMLinkQueryAction();
		context.put("linkQueryAction", bean);
		bean.setModel(getManageAppModel());
		bean.setBillType("32");
		bean.setOpenMode(1);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.actions.pflow.PFApproveStatusInfoAction getAuditFlowAction() {
		if (context.get("auditFlowAction") != null)
			return (nc.ui.pubapp.uif2app.actions.pflow.PFApproveStatusInfoAction) context
					.get("auditFlowAction");
		nc.ui.pubapp.uif2app.actions.pflow.PFApproveStatusInfoAction bean = new nc.ui.pubapp.uif2app.actions.pflow.PFApproveStatusInfoAction();
		context.put("auditFlowAction", bean);
		bean.setModel(getManageAppModel());
		bean.setEditor(getBillFormEditor());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.so.m32.billui.action.link.QueryCustCreditAction getCustCreditAction() {
		if (context.get("custCreditAction") != null)
			return (nc.ui.so.m32.billui.action.link.QueryCustCreditAction) context
					.get("custCreditAction");
		nc.ui.so.m32.billui.action.link.QueryCustCreditAction bean = new nc.ui.so.m32.billui.action.link.QueryCustCreditAction();
		context.put("custCreditAction", bean);
		bean.setModel(getManageAppModel());
		bean.setEditor(getBillFormEditor());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.so.m32.billui.action.link.QueryCustInfoAction getCustInfoAction() {
		if (context.get("custInfoAction") != null)
			return (nc.ui.so.m32.billui.action.link.QueryCustInfoAction) context
					.get("custInfoAction");
		nc.ui.so.m32.billui.action.link.QueryCustInfoAction bean = new nc.ui.so.m32.billui.action.link.QueryCustInfoAction();
		context.put("custInfoAction", bean);
		bean.setModel(getManageAppModel());
		bean.setEditor(getBillFormEditor());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.so.m32.billui.action.link.EstimateProfitAction getEstProfitAction() {
		if (context.get("estProfitAction") != null)
			return (nc.ui.so.m32.billui.action.link.EstimateProfitAction) context
					.get("estProfitAction");
		nc.ui.so.m32.billui.action.link.EstimateProfitAction bean = new nc.ui.so.m32.billui.action.link.EstimateProfitAction();
		context.put("estProfitAction", bean);
		bean.setModel(getManageAppModel());
		bean.setEditor(getBillFormEditor());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.so.m32.billui.action.link.QueryExecInfoAction getExecQueryAction() {
		if (context.get("execQueryAction") != null)
			return (nc.ui.so.m32.billui.action.link.QueryExecInfoAction) context
					.get("execQueryAction");
		nc.ui.so.m32.billui.action.link.QueryExecInfoAction bean = new nc.ui.so.m32.billui.action.link.QueryExecInfoAction();
		context.put("execQueryAction", bean);
		bean.setModel(getManageAppModel());
		bean.setEditor(getBillFormEditor());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.so.m32.billui.action.link.QueryArsubFor32Action getQueryCashArsubAction() {
		if (context.get("queryCashArsubAction") != null)
			return (nc.ui.so.m32.billui.action.link.QueryArsubFor32Action) context
					.get("queryCashArsubAction");
		nc.ui.so.m32.billui.action.link.QueryArsubFor32Action bean = new nc.ui.so.m32.billui.action.link.QueryArsubFor32Action();
		context.put("queryCashArsubAction", bean);
		bean.setModel(getManageAppModel());
		bean.setEditor(getBillFormEditor());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.funcnode.ui.action.MenuAction getEditLinkActionGroup() {
		if (context.get("editLinkActionGroup") != null)
			return (nc.funcnode.ui.action.MenuAction) context
					.get("editLinkActionGroup");
		nc.funcnode.ui.action.MenuAction bean = new nc.funcnode.ui.action.MenuAction();
		context.put("editLinkActionGroup", bean);
		bean.setCode("EditLink");
		bean.setName(getI18nFB_ddebe3());
		bean.setActions(getManagedList28());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private java.lang.String getI18nFB_ddebe3() {
		if (context.get("nc.ui.uif2.I18nFB#ddebe3") != null)
			return (java.lang.String) context.get("nc.ui.uif2.I18nFB#ddebe3");
		nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
		context.put("&nc.ui.uif2.I18nFB#ddebe3", bean);
		bean.setResDir("4006008_0");
		bean.setResId("04006008-0123");
		bean.setDefaultValue("联查");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		try {
			Object product = bean.getObject();
			context.put("nc.ui.uif2.I18nFB#ddebe3", product);
			return (java.lang.String) product;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private List getManagedList28() {
		List list = new ArrayList();
		list.add(getCustCreditAction());
		list.add(getCustInfoAction());
		list.add(getEstProfitAction());
		list.add(getQueryCashArsubAction());
		return list;
	}

	public nc.funcnode.ui.action.GroupAction getPrintActionGroup() {
		if (context.get("printActionGroup") != null)
			return (nc.funcnode.ui.action.GroupAction) context
					.get("printActionGroup");
		nc.funcnode.ui.action.GroupAction bean = new nc.funcnode.ui.action.GroupAction();
		context.put("printActionGroup", bean);
		bean.setCode("printMenuAction");
		bean.setName(getI18nFB_1a99804());
		bean.setActions(getManagedList29());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private java.lang.String getI18nFB_1a99804() {
		if (context.get("nc.ui.uif2.I18nFB#1a99804") != null)
			return (java.lang.String) context.get("nc.ui.uif2.I18nFB#1a99804");
		nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
		context.put("&nc.ui.uif2.I18nFB#1a99804", bean);
		bean.setResDir("common");
		bean.setResId("UC001-0000007");
		bean.setDefaultValue("打印");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		try {
			Object product = bean.getObject();
			context.put("nc.ui.uif2.I18nFB#1a99804", product);
			return (java.lang.String) product;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private List getManagedList29() {
		List list = new ArrayList();
		list.add(getPrintAction());
		list.add(getPreviewAction());
		list.add(getOutputAction());
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
		bean.setBeforePrintDataProcess(getPrintProcessor());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.so.m32.billui.action.print.InvoiceMetaDataBasedPrintAction getPreviewAction() {
		if (context.get("previewAction") != null)
			return (nc.ui.so.m32.billui.action.print.InvoiceMetaDataBasedPrintAction) context
					.get("previewAction");
		nc.ui.so.m32.billui.action.print.InvoiceMetaDataBasedPrintAction bean = new nc.ui.so.m32.billui.action.print.InvoiceMetaDataBasedPrintAction();
		context.put("previewAction", bean);
		bean.setPreview(true);
		bean.setModel(getManageAppModel());
		bean.setBeforePrintDataProcess(getPrintProcessor());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.so.m32.billui.action.print.InvoiceMetaDataBasedPrintAction getPrintAction() {
		if (context.get("printAction") != null)
			return (nc.ui.so.m32.billui.action.print.InvoiceMetaDataBasedPrintAction) context
					.get("printAction");
		nc.ui.so.m32.billui.action.print.InvoiceMetaDataBasedPrintAction bean = new nc.ui.so.m32.billui.action.print.InvoiceMetaDataBasedPrintAction();
		context.put("printAction", bean);
		bean.setPreview(false);
		bean.setModel(getManageAppModel());
		bean.setBeforePrintDataProcess(getPrintProcessor());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.so.m32.billui.action.print.SaleinvoicePrintProcessor getPrintProcessor() {
		if (context.get("printProcessor") != null)
			return (nc.ui.so.m32.billui.action.print.SaleinvoicePrintProcessor) context
					.get("printProcessor");
		nc.ui.so.m32.billui.action.print.SaleinvoicePrintProcessor bean = new nc.ui.so.m32.billui.action.print.SaleinvoicePrintProcessor();
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
		bean.setBillType("32");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.so.m32.billui.action.add.TransferSaleInvoiceLogic getTransferViewProcessor() {
		if (context.get("transferViewProcessor") != null)
			return (nc.ui.so.m32.billui.action.add.TransferSaleInvoiceLogic) context
					.get("transferViewProcessor");
		nc.ui.so.m32.billui.action.add.TransferSaleInvoiceLogic bean = new nc.ui.so.m32.billui.action.add.TransferSaleInvoiceLogic();
		context.put("transferViewProcessor", bean);
		bean.setList(getListView());
		bean.setActionContainer(getActionsOfList());
		bean.setCardActionContainer(getActionsOfCard());
		bean.setSaveAction(getSaveAction());
		bean.setCommitAction(getCommitAction());
		bean.setCancelAction(getCancelAction());
		bean.setBillForm(getBillFormEditor());
		bean.setTransferLogic(getSODefaultBillDataLogic_13b491b());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.so.pub.ref.add.SODefaultBillDataLogic getSODefaultBillDataLogic_13b491b() {
		if (context.get("nc.ui.so.pub.ref.add.SODefaultBillDataLogic#13b491b") != null)
			return (nc.ui.so.pub.ref.add.SODefaultBillDataLogic) context
					.get("nc.ui.so.pub.ref.add.SODefaultBillDataLogic#13b491b");
		nc.ui.so.pub.ref.add.SODefaultBillDataLogic bean = new nc.ui.so.pub.ref.add.SODefaultBillDataLogic();
		context.put("nc.ui.so.pub.ref.add.SODefaultBillDataLogic#13b491b", bean);
		bean.setBillForm(getBillFormEditor());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.view.FractionFixMediator getFractionFixMediator() {
		if (context.get("fractionFixMediator") != null)
			return (nc.ui.pubapp.uif2app.view.FractionFixMediator) context
					.get("fractionFixMediator");
		nc.ui.pubapp.uif2app.view.FractionFixMediator bean = new nc.ui.pubapp.uif2app.view.FractionFixMediator(
				getManagedList30(), getManagedList31());
		context.put("fractionFixMediator", bean);
		bean.initUI();
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList30() {
		List list = new ArrayList();
		list.add(getBillFormEditor());
		return list;
	}

	private List getManagedList31() {
		List list = new ArrayList();
		list.add(getListView());
		return list;
	}

	public nc.ui.uif2.editor.UserdefitemContainerPreparator getUserdefitemPreparator() {
		if (context.get("userdefitemPreparator") != null)
			return (nc.ui.uif2.editor.UserdefitemContainerPreparator) context
					.get("userdefitemPreparator");
		nc.ui.uif2.editor.UserdefitemContainerPreparator bean = new nc.ui.uif2.editor.UserdefitemContainerPreparator();
		context.put("userdefitemPreparator", bean);
		bean.setContainer(getUserdefitemContainer());
		bean.setParams(getManagedList32());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList32() {
		List list = new ArrayList();
		list.add(getUserdefQueryParam_13cae18());
		list.add(getUserdefQueryParam_c9dfa2());
		return list;
	}

	private nc.ui.uif2.editor.UserdefQueryParam getUserdefQueryParam_13cae18() {
		if (context.get("nc.ui.uif2.editor.UserdefQueryParam#13cae18") != null)
			return (nc.ui.uif2.editor.UserdefQueryParam) context
					.get("nc.ui.uif2.editor.UserdefQueryParam#13cae18");
		nc.ui.uif2.editor.UserdefQueryParam bean = new nc.ui.uif2.editor.UserdefQueryParam();
		context.put("nc.ui.uif2.editor.UserdefQueryParam#13cae18", bean);
		bean.setMdfullname("so.saleinvoice");
		bean.setPos(0);
		bean.setPrefix("vdef");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.uif2.editor.UserdefQueryParam getUserdefQueryParam_c9dfa2() {
		if (context.get("nc.ui.uif2.editor.UserdefQueryParam#c9dfa2") != null)
			return (nc.ui.uif2.editor.UserdefQueryParam) context
					.get("nc.ui.uif2.editor.UserdefQueryParam#c9dfa2");
		nc.ui.uif2.editor.UserdefQueryParam bean = new nc.ui.uif2.editor.UserdefQueryParam();
		context.put("nc.ui.uif2.editor.UserdefQueryParam#c9dfa2", bean);
		bean.setMdfullname("so.saleinvoice_b");
		bean.setPos(1);
		bean.setPrefix("vbdef");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.uif2.editor.UserdefitemContainerListPreparator getUserdefitemlistPreparator() {
		if (context.get("userdefitemlistPreparator") != null)
			return (nc.ui.uif2.editor.UserdefitemContainerListPreparator) context
					.get("userdefitemlistPreparator");
		nc.ui.uif2.editor.UserdefitemContainerListPreparator bean = new nc.ui.uif2.editor.UserdefitemContainerListPreparator();
		context.put("userdefitemlistPreparator", bean);
		bean.setContainer(getUserdefitemContainer());
		bean.setParams(getManagedList33());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList33() {
		List list = new ArrayList();
		list.add(getUserdefQueryParam_1790c43());
		list.add(getUserdefQueryParam_1437c7());
		return list;
	}

	private nc.ui.uif2.editor.UserdefQueryParam getUserdefQueryParam_1790c43() {
		if (context.get("nc.ui.uif2.editor.UserdefQueryParam#1790c43") != null)
			return (nc.ui.uif2.editor.UserdefQueryParam) context
					.get("nc.ui.uif2.editor.UserdefQueryParam#1790c43");
		nc.ui.uif2.editor.UserdefQueryParam bean = new nc.ui.uif2.editor.UserdefQueryParam();
		context.put("nc.ui.uif2.editor.UserdefQueryParam#1790c43", bean);
		bean.setMdfullname("so.saleinvoice");
		bean.setPos(0);
		bean.setPrefix("vdef");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.uif2.editor.UserdefQueryParam getUserdefQueryParam_1437c7() {
		if (context.get("nc.ui.uif2.editor.UserdefQueryParam#1437c7") != null)
			return (nc.ui.uif2.editor.UserdefQueryParam) context
					.get("nc.ui.uif2.editor.UserdefQueryParam#1437c7");
		nc.ui.uif2.editor.UserdefQueryParam bean = new nc.ui.uif2.editor.UserdefQueryParam();
		context.put("nc.ui.uif2.editor.UserdefQueryParam#1437c7", bean);
		bean.setMdfullname("so.saleinvoice_b");
		bean.setPos(1);
		bean.setPrefix("vbdef");
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
		bean.setParams(getManagedList34());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList34() {
		List list = new ArrayList();
		list.add(getQueryParam_15a08a1());
		list.add(getQueryParam_1ecacc7());
		list.add(getQueryParam_1435b8d());
		return list;
	}

	private nc.ui.uif2.userdefitem.QueryParam getQueryParam_15a08a1() {
		if (context.get("nc.ui.uif2.userdefitem.QueryParam#15a08a1") != null)
			return (nc.ui.uif2.userdefitem.QueryParam) context
					.get("nc.ui.uif2.userdefitem.QueryParam#15a08a1");
		nc.ui.uif2.userdefitem.QueryParam bean = new nc.ui.uif2.userdefitem.QueryParam();
		context.put("nc.ui.uif2.userdefitem.QueryParam#15a08a1", bean);
		bean.setMdfullname("so.saleinvoice");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.uif2.userdefitem.QueryParam getQueryParam_1ecacc7() {
		if (context.get("nc.ui.uif2.userdefitem.QueryParam#1ecacc7") != null)
			return (nc.ui.uif2.userdefitem.QueryParam) context
					.get("nc.ui.uif2.userdefitem.QueryParam#1ecacc7");
		nc.ui.uif2.userdefitem.QueryParam bean = new nc.ui.uif2.userdefitem.QueryParam();
		context.put("nc.ui.uif2.userdefitem.QueryParam#1ecacc7", bean);
		bean.setMdfullname("so.saleinvoice_b");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.uif2.userdefitem.QueryParam getQueryParam_1435b8d() {
		if (context.get("nc.ui.uif2.userdefitem.QueryParam#1435b8d") != null)
			return (nc.ui.uif2.userdefitem.QueryParam) context
					.get("nc.ui.uif2.userdefitem.QueryParam#1435b8d");
		nc.ui.uif2.userdefitem.QueryParam bean = new nc.ui.uif2.userdefitem.QueryParam();
		context.put("nc.ui.uif2.userdefitem.QueryParam#1435b8d", bean);
		bean.setRulecode("materialassistant");
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
		bean.setPermissionCode("32");
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
		bean.setPermissionCode("32");
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
		bean.setPermissionCode("32");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.pub.power.PowerValidateService getPowercommitvalidservice() {
		if (context.get("powercommitvalidservice") != null)
			return (nc.ui.pubapp.pub.power.PowerValidateService) context
					.get("powercommitvalidservice");
		nc.ui.pubapp.pub.power.PowerValidateService bean = new nc.ui.pubapp.pub.power.PowerValidateService();
		context.put("powercommitvalidservice", bean);
		bean.setActionCode("commit");
		bean.setBillCodeFiledName("vbillcode");
		bean.setPermissionCode("32");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.pub.power.PowerValidateService getPoweruncommitvalidservice() {
		if (context.get("poweruncommitvalidservice") != null)
			return (nc.ui.pubapp.pub.power.PowerValidateService) context
					.get("poweruncommitvalidservice");
		nc.ui.pubapp.pub.power.PowerValidateService bean = new nc.ui.pubapp.pub.power.PowerValidateService();
		context.put("poweruncommitvalidservice", bean);
		bean.setActionCode("uncommit");
		bean.setBillCodeFiledName("vbillcode");
		bean.setPermissionCode("32");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.so.m32.billui.action.SaleInvoiceSendysdAction getSendysdAction() {
		if (context.get("sendysdAction") != null)
			return (nc.ui.so.m32.billui.action.SaleInvoiceSendysdAction) context
					.get("sendysdAction");
		nc.ui.so.m32.billui.action.SaleInvoiceSendysdAction bean = new nc.ui.so.m32.billui.action.SaleInvoiceSendysdAction();
		context.put("sendysdAction", bean);
		bean.setModel(getManageAppModel());
		bean.setEditor(getBillFormEditor());
		bean.setBtnName("推应收单");
		bean.setCode("cdessss");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.so.m32.billui.action.CancleSaleInvoiceToRecbill getUNsendysdAction() {
		if (context.get("UNsendysdAction") != null)
			return (nc.ui.so.m32.billui.action.CancleSaleInvoiceToRecbill) context
					.get("UNsendysdAction");
		nc.ui.so.m32.billui.action.CancleSaleInvoiceToRecbill bean = new nc.ui.so.m32.billui.action.CancleSaleInvoiceToRecbill();
		context.put("UNsendysdAction", bean);
		bean.setModel(getManageAppModel());
		bean.setEditor(getBillFormEditor());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.so.m32.billui.query.SaleInvoiceBillLazilyLoader getBillLazilyLoader() {
		if (context.get("billLazilyLoader") != null)
			return (nc.ui.so.m32.billui.query.SaleInvoiceBillLazilyLoader) context
					.get("billLazilyLoader");
		nc.ui.so.m32.billui.query.SaleInvoiceBillLazilyLoader bean = new nc.ui.so.m32.billui.query.SaleInvoiceBillLazilyLoader();
		context.put("billLazilyLoader", bean);
		bean.setModel(getManageAppModel());
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
		bean.setLazilyLoadSupporter(getManagedList35());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList35() {
		List list = new ArrayList();
		list.add(getCardPanelLazilyLoad_12f9e38());
		list.add(getListPanelLazilyLoad_13ca78f());
		list.add(getActionLazilyLoad_11cad4e());
		return list;
	}

	private nc.ui.pubapp.uif2app.lazilyload.CardPanelLazilyLoad getCardPanelLazilyLoad_12f9e38() {
		if (context
				.get("nc.ui.pubapp.uif2app.lazilyload.CardPanelLazilyLoad#12f9e38") != null)
			return (nc.ui.pubapp.uif2app.lazilyload.CardPanelLazilyLoad) context
					.get("nc.ui.pubapp.uif2app.lazilyload.CardPanelLazilyLoad#12f9e38");
		nc.ui.pubapp.uif2app.lazilyload.CardPanelLazilyLoad bean = new nc.ui.pubapp.uif2app.lazilyload.CardPanelLazilyLoad();
		context.put(
				"nc.ui.pubapp.uif2app.lazilyload.CardPanelLazilyLoad#12f9e38",
				bean);
		bean.setBillform(getBillFormEditor());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.pubapp.uif2app.lazilyload.ListPanelLazilyLoad getListPanelLazilyLoad_13ca78f() {
		if (context
				.get("nc.ui.pubapp.uif2app.lazilyload.ListPanelLazilyLoad#13ca78f") != null)
			return (nc.ui.pubapp.uif2app.lazilyload.ListPanelLazilyLoad) context
					.get("nc.ui.pubapp.uif2app.lazilyload.ListPanelLazilyLoad#13ca78f");
		nc.ui.pubapp.uif2app.lazilyload.ListPanelLazilyLoad bean = new nc.ui.pubapp.uif2app.lazilyload.ListPanelLazilyLoad();
		context.put(
				"nc.ui.pubapp.uif2app.lazilyload.ListPanelLazilyLoad#13ca78f",
				bean);
		bean.setListView(getListView());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.pubapp.uif2app.lazilyload.ActionLazilyLoad getActionLazilyLoad_11cad4e() {
		if (context
				.get("nc.ui.pubapp.uif2app.lazilyload.ActionLazilyLoad#11cad4e") != null)
			return (nc.ui.pubapp.uif2app.lazilyload.ActionLazilyLoad) context
					.get("nc.ui.pubapp.uif2app.lazilyload.ActionLazilyLoad#11cad4e");
		nc.ui.pubapp.uif2app.lazilyload.ActionLazilyLoad bean = new nc.ui.pubapp.uif2app.lazilyload.ActionLazilyLoad();
		context.put("nc.ui.pubapp.uif2app.lazilyload.ActionLazilyLoad#11cad4e",
				bean);
		bean.setModel(getManageAppModel());
		bean.setActionList(getManagedList36());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList36() {
		List list = new ArrayList();
		list.add(getPrintAction());
		list.add(getPreviewAction());
		list.add(getOutputAction());
		list.add(getShowTypeAction());
		return list;
	}

	public nc.ui.scmpub.listener.BillCodeEditMediator getBillCodeMediator() {
		if (context.get("billCodeMediator") != null)
			return (nc.ui.scmpub.listener.BillCodeEditMediator) context
					.get("billCodeMediator");
		nc.ui.scmpub.listener.BillCodeEditMediator bean = new nc.ui.scmpub.listener.BillCodeEditMediator();
		context.put("billCodeMediator", bean);
		bean.setBillCodeKey("vbillcode");
		bean.setBillType("32");
		bean.setBillForm(getBillFormEditor());
		bean.initUI();
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.view.MouseClickShowPanelMediator getDoubleClickMediator() {
		if (context.get("doubleClickMediator") != null)
			return (nc.ui.pubapp.uif2app.view.MouseClickShowPanelMediator) context
					.get("doubleClickMediator");
		nc.ui.pubapp.uif2app.view.MouseClickShowPanelMediator bean = new nc.ui.pubapp.uif2app.view.MouseClickShowPanelMediator();
		context.put("doubleClickMediator", bean);
		bean.setListView(getListView());
		bean.setShowUpComponent(getBillFormEditor());
		bean.setHyperLinkColumn("vbillcode");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
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

	public nc.ui.so.m32.billui.view.YshtInitDataListener getInitDataListener() {
		if (context.get("InitDataListener") != null)
			return (nc.ui.so.m32.billui.view.YshtInitDataListener) context
					.get("InitDataListener");
		nc.ui.so.m32.billui.view.YshtInitDataListener bean = new nc.ui.so.m32.billui.view.YshtInitDataListener();
		context.put("InitDataListener", bean);
		bean.setModel(getManageAppModel());
		bean.setQueryAction(getQueryAction());
		bean.setMultiLinkQueryEnable(true);
		bean.setVoClassName("nc.vo.so.m32.entity.SaleInvoiceVO");
		bean.setAutoShowUpComponent(getBillFormEditor());
		bean.setProcessorMap(getManagedMap1());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private Map getManagedMap1() {
		Map map = new HashMap();
		map.put("0", getSaleInvoiceInitDataProcesser_1b6005b());
		return map;
	}

	private nc.ui.so.m32.billui.view.SaleInvoiceInitDataProcesser getSaleInvoiceInitDataProcesser_1b6005b() {
		if (context
				.get("nc.ui.so.m32.billui.view.SaleInvoiceInitDataProcesser#1b6005b") != null)
			return (nc.ui.so.m32.billui.view.SaleInvoiceInitDataProcesser) context
					.get("nc.ui.so.m32.billui.view.SaleInvoiceInitDataProcesser#1b6005b");
		nc.ui.so.m32.billui.view.SaleInvoiceInitDataProcesser bean = new nc.ui.so.m32.billui.view.SaleInvoiceInitDataProcesser();
		context.put(
				"nc.ui.so.m32.billui.view.SaleInvoiceInitDataProcesser#1b6005b",
				bean);
		bean.setModel(getManageAppModel());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

}
