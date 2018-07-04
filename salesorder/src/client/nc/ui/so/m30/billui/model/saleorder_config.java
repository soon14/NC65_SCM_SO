package nc.ui.so.m30.billui.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.ui.uif2.factory.AbstractJavaBeanDefinition;

public class saleorder_config extends AbstractJavaBeanDefinition {
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

	public nc.ui.so.m30.billui.model.SaleOrderPageService getPageQuery() {
		if (context.get("pageQuery") != null)
			return (nc.ui.so.m30.billui.model.SaleOrderPageService) context
					.get("pageQuery");
		nc.ui.so.m30.billui.model.SaleOrderPageService bean = new nc.ui.so.m30.billui.model.SaleOrderPageService();
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
		bean.setBillListDataPrepares(getManagedList0());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList0() {
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
		bean.setUserdefitemPreparator(getCompositeBillDataPrepare_142a41());
		bean.setBlankChildrenFilter(getMultiFieldsBlankChildrenFilter_1356256());
		bean.setClearHyperlink(getManagedList3());
		bean.setBodyLineActions(getManagedList4());
		bean.initUI();
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.pubapp.uif2app.view.CompositeBillDataPrepare getCompositeBillDataPrepare_142a41() {
		if (context
				.get("nc.ui.pubapp.uif2app.view.CompositeBillDataPrepare#142a41") != null)
			return (nc.ui.pubapp.uif2app.view.CompositeBillDataPrepare) context
					.get("nc.ui.pubapp.uif2app.view.CompositeBillDataPrepare#142a41");
		nc.ui.pubapp.uif2app.view.CompositeBillDataPrepare bean = new nc.ui.pubapp.uif2app.view.CompositeBillDataPrepare();
		context.put(
				"nc.ui.pubapp.uif2app.view.CompositeBillDataPrepare#142a41",
				bean);
		bean.setBillDataPrepares(getManagedList1());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList1() {
		List list = new ArrayList();
		list.add(getUserdefitemPreparator());
		list.add(getMarAsstPreparator());
		return list;
	}

	private nc.ui.pubapp.uif2app.view.value.MultiFieldsBlankChildrenFilter getMultiFieldsBlankChildrenFilter_1356256() {
		if (context
				.get("nc.ui.pubapp.uif2app.view.value.MultiFieldsBlankChildrenFilter#1356256") != null)
			return (nc.ui.pubapp.uif2app.view.value.MultiFieldsBlankChildrenFilter) context
					.get("nc.ui.pubapp.uif2app.view.value.MultiFieldsBlankChildrenFilter#1356256");
		nc.ui.pubapp.uif2app.view.value.MultiFieldsBlankChildrenFilter bean = new nc.ui.pubapp.uif2app.view.value.MultiFieldsBlankChildrenFilter();
		context.put(
				"nc.ui.pubapp.uif2app.view.value.MultiFieldsBlankChildrenFilter#1356256",
				bean);
		bean.setFilterMap(getManagedMap0());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private Map getManagedMap0() {
		Map map = new HashMap();
		map.put("bodytable1", getManagedList2());
		return map;
	}

	private List getManagedList2() {
		List list = new ArrayList();
		list.add("cmaterialvid");
		return list;
	}

	private List getManagedList3() {
		List list = new ArrayList();
		list.add("vbillcode");
		return list;
	}

	private List getManagedList4() {
		List list = new ArrayList();
		list.add(getSaleOrderAddLineAction());
		list.add(getSaleOrderInsertLineAction());
		list.add(getSaleOrderDelLineAction());
		list.add(getSaleOrderCopyLineAction());
		list.add(getSaleOrderPasteLineAction());
		list.add(getSaleOrderPasteToTailAction());
		list.add(getActionsBar_ActionsBarSeparator_1cb25c1());
		list.add(getRearrangeRowNoBodyLineAction());
		list.add(getActionsBar_ActionsBarSeparator_1ba5e6());
		list.add(getDefaultBodyZoomAction());
		return list;
	}

	private nc.ui.pub.beans.ActionsBar.ActionsBarSeparator getActionsBar_ActionsBarSeparator_1cb25c1() {
		if (context
				.get("nc.ui.pub.beans.ActionsBar.ActionsBarSeparator#1cb25c1") != null)
			return (nc.ui.pub.beans.ActionsBar.ActionsBarSeparator) context
					.get("nc.ui.pub.beans.ActionsBar.ActionsBarSeparator#1cb25c1");
		nc.ui.pub.beans.ActionsBar.ActionsBarSeparator bean = new nc.ui.pub.beans.ActionsBar.ActionsBarSeparator();
		context.put("nc.ui.pub.beans.ActionsBar.ActionsBarSeparator#1cb25c1",
				bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.pub.beans.ActionsBar.ActionsBarSeparator getActionsBar_ActionsBarSeparator_1ba5e6() {
		if (context
				.get("nc.ui.pub.beans.ActionsBar.ActionsBarSeparator#1ba5e6") != null)
			return (nc.ui.pub.beans.ActionsBar.ActionsBarSeparator) context
					.get("nc.ui.pub.beans.ActionsBar.ActionsBarSeparator#1ba5e6");
		nc.ui.pub.beans.ActionsBar.ActionsBarSeparator bean = new nc.ui.pub.beans.ActionsBar.ActionsBarSeparator();
		context.put("nc.ui.pub.beans.ActionsBar.ActionsBarSeparator#1ba5e6",
				bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.so.m30.billui.action.line.SaleOrderBodyAddLineAction getSaleOrderAddLineAction() {
		if (context.get("SaleOrderAddLineAction") != null)
			return (nc.ui.so.m30.billui.action.line.SaleOrderBodyAddLineAction) context
					.get("SaleOrderAddLineAction");
		nc.ui.so.m30.billui.action.line.SaleOrderBodyAddLineAction bean = new nc.ui.so.m30.billui.action.line.SaleOrderBodyAddLineAction();
		context.put("SaleOrderAddLineAction", bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.so.m30.billui.action.line.SaleOrderBodyCopyLineAction getSaleOrderCopyLineAction() {
		if (context.get("SaleOrderCopyLineAction") != null)
			return (nc.ui.so.m30.billui.action.line.SaleOrderBodyCopyLineAction) context
					.get("SaleOrderCopyLineAction");
		nc.ui.so.m30.billui.action.line.SaleOrderBodyCopyLineAction bean = new nc.ui.so.m30.billui.action.line.SaleOrderBodyCopyLineAction();
		context.put("SaleOrderCopyLineAction", bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.so.m30.billui.action.line.SaleOrderBodyInsertLineAction getSaleOrderInsertLineAction() {
		if (context.get("SaleOrderInsertLineAction") != null)
			return (nc.ui.so.m30.billui.action.line.SaleOrderBodyInsertLineAction) context
					.get("SaleOrderInsertLineAction");
		nc.ui.so.m30.billui.action.line.SaleOrderBodyInsertLineAction bean = new nc.ui.so.m30.billui.action.line.SaleOrderBodyInsertLineAction();
		context.put("SaleOrderInsertLineAction", bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.so.m30.billui.action.line.SaleOrderDelLineAction getSaleOrderDelLineAction() {
		if (context.get("SaleOrderDelLineAction") != null)
			return (nc.ui.so.m30.billui.action.line.SaleOrderDelLineAction) context
					.get("SaleOrderDelLineAction");
		nc.ui.so.m30.billui.action.line.SaleOrderDelLineAction bean = new nc.ui.so.m30.billui.action.line.SaleOrderDelLineAction();
		context.put("SaleOrderDelLineAction", bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.so.m30.billui.action.line.SaleOrderPasteLineAction getSaleOrderPasteLineAction() {
		if (context.get("SaleOrderPasteLineAction") != null)
			return (nc.ui.so.m30.billui.action.line.SaleOrderPasteLineAction) context
					.get("SaleOrderPasteLineAction");
		nc.ui.so.m30.billui.action.line.SaleOrderPasteLineAction bean = new nc.ui.so.m30.billui.action.line.SaleOrderPasteLineAction();
		context.put("SaleOrderPasteLineAction", bean);
		bean.setClearItems(getManagedList5());
		bean.setEditor(getBillFormEditor());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList5() {
		List list = new ArrayList();
		list.add("csaleorderbid");
		list.add("ts");
		list.add("nreqrsnum");
		return list;
	}

	public nc.ui.so.m30.billui.action.line.SaleOrderPasteToTailAction getSaleOrderPasteToTailAction() {
		if (context.get("SaleOrderPasteToTailAction") != null)
			return (nc.ui.so.m30.billui.action.line.SaleOrderPasteToTailAction) context
					.get("SaleOrderPasteToTailAction");
		nc.ui.so.m30.billui.action.line.SaleOrderPasteToTailAction bean = new nc.ui.so.m30.billui.action.line.SaleOrderPasteToTailAction();
		context.put("SaleOrderPasteToTailAction", bean);
		bean.setClearItems(getManagedList6());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList6() {
		List list = new ArrayList();
		list.add("csaleorderbid");
		list.add("ts");
		list.add("nreqrsnum");
		return list;
	}

	public nc.ui.pub.beans.ActionsBar.ActionsBarSeparator getActionsBar_ActionsBarSeparator_0() {
		if (context.get("nc.ui.pub.beans.ActionsBar.ActionsBarSeparator#0") != null)
			return (nc.ui.pub.beans.ActionsBar.ActionsBarSeparator) context
					.get("nc.ui.pub.beans.ActionsBar.ActionsBarSeparator#0");
		nc.ui.pub.beans.ActionsBar.ActionsBarSeparator bean = new nc.ui.pub.beans.ActionsBar.ActionsBarSeparator();
		context.put("nc.ui.pub.beans.ActionsBar.ActionsBarSeparator#0", bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.actions.RearrangeRowNoBodyLineAction getRearrangeRowNoBodyLineAction_0() {
		if (context
				.get("nc.ui.pubapp.uif2app.actions.RearrangeRowNoBodyLineAction#0") != null)
			return (nc.ui.pubapp.uif2app.actions.RearrangeRowNoBodyLineAction) context
					.get("nc.ui.pubapp.uif2app.actions.RearrangeRowNoBodyLineAction#0");
		nc.ui.pubapp.uif2app.actions.RearrangeRowNoBodyLineAction bean = new nc.ui.pubapp.uif2app.actions.RearrangeRowNoBodyLineAction();
		context.put(
				"nc.ui.pubapp.uif2app.actions.RearrangeRowNoBodyLineAction#0",
				bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pub.beans.ActionsBar.ActionsBarSeparator getActionsBar_ActionsBarSeparator_1() {
		if (context.get("nc.ui.pub.beans.ActionsBar.ActionsBarSeparator#1") != null)
			return (nc.ui.pub.beans.ActionsBar.ActionsBarSeparator) context
					.get("nc.ui.pub.beans.ActionsBar.ActionsBarSeparator#1");
		nc.ui.pub.beans.ActionsBar.ActionsBarSeparator bean = new nc.ui.pub.beans.ActionsBar.ActionsBarSeparator();
		context.put("nc.ui.pub.beans.ActionsBar.ActionsBarSeparator#1", bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.actions.DefaultBodyZoomAction getDefaultBodyZoomAction_0() {
		if (context.get("nc.ui.pubapp.uif2app.actions.DefaultBodyZoomAction#0") != null)
			return (nc.ui.pubapp.uif2app.actions.DefaultBodyZoomAction) context
					.get("nc.ui.pubapp.uif2app.actions.DefaultBodyZoomAction#0");
		nc.ui.pubapp.uif2app.actions.DefaultBodyZoomAction bean = new nc.ui.pubapp.uif2app.actions.DefaultBodyZoomAction();
		context.put("nc.ui.pubapp.uif2app.actions.DefaultBodyZoomAction#0",
				bean);
		bean.setPos(1);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.actions.RearrangeRowNoBodyLineAction getRearrangeRowNoBodyLineAction() {
		if (context.get("RearrangeRowNoBodyLineAction") != null)
			return (nc.ui.pubapp.uif2app.actions.RearrangeRowNoBodyLineAction) context
					.get("RearrangeRowNoBodyLineAction");
		nc.ui.pubapp.uif2app.actions.RearrangeRowNoBodyLineAction bean = new nc.ui.pubapp.uif2app.actions.RearrangeRowNoBodyLineAction();
		context.put("RearrangeRowNoBodyLineAction", bean);
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
		bean.setBillType("30");
		bean.setBillForm(getBillFormEditor());
		bean.initUI();
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
		bean.setVoClassName("nc.vo.so.m30.entity.SaleOrderVO");
		bean.setAutoShowUpComponent(getBillFormEditor());
		bean.setQueryAction(getQueryAction());
		bean.setProcessorMap(getManagedMap1());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private Map getManagedMap1() {
		Map map = new HashMap();
		map.put("5801", getM58012M30DataProcessor_26eea1());
		map.put("28", getM28ToM30DataProcessor_1fc8f2e());
		map.put("0", getM3025801DataProcessor_1c4951());
		return map;
	}

	private nc.ui.so.m30.billui.push.M58012M30DataProcessor getM58012M30DataProcessor_26eea1() {
		if (context
				.get("nc.ui.so.m30.billui.push.M58012M30DataProcessor#26eea1") != null)
			return (nc.ui.so.m30.billui.push.M58012M30DataProcessor) context
					.get("nc.ui.so.m30.billui.push.M58012M30DataProcessor#26eea1");
		nc.ui.so.m30.billui.push.M58012M30DataProcessor bean = new nc.ui.so.m30.billui.push.M58012M30DataProcessor();
		context.put("nc.ui.so.m30.billui.push.M58012M30DataProcessor#26eea1",
				bean);
		bean.setTransferProcessor(getTransferViewProcessorForSelf());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.pp.m28.billref.M28ToM30DataProcessor getM28ToM30DataProcessor_1fc8f2e() {
		if (context.get("nc.ui.pp.m28.billref.M28ToM30DataProcessor#1fc8f2e") != null)
			return (nc.ui.pp.m28.billref.M28ToM30DataProcessor) context
					.get("nc.ui.pp.m28.billref.M28ToM30DataProcessor#1fc8f2e");
		nc.ui.pp.m28.billref.M28ToM30DataProcessor bean = new nc.ui.pp.m28.billref.M28ToM30DataProcessor();
		context.put("nc.ui.pp.m28.billref.M28ToM30DataProcessor#1fc8f2e", bean);
		bean.setTransferProcessor(getTransferViewProcessorForM28());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.so.m30.billui.push.M3025801DataProcessor getM3025801DataProcessor_1c4951() {
		if (context
				.get("nc.ui.so.m30.billui.push.M3025801DataProcessor#1c4951") != null)
			return (nc.ui.so.m30.billui.push.M3025801DataProcessor) context
					.get("nc.ui.so.m30.billui.push.M3025801DataProcessor#1c4951");
		nc.ui.so.m30.billui.push.M3025801DataProcessor bean = new nc.ui.so.m30.billui.push.M3025801DataProcessor();
		context.put("nc.ui.so.m30.billui.push.M3025801DataProcessor#1c4951",
				bean);
		bean.setTransferProcessor(getTransferViewProcessorFor5801());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pp.m28.billui.tranferbill.M28ToM30TransferBillDataLogic getTransferLogicforM30() {
		if (context.get("transferLogicforM30") != null)
			return (nc.ui.pp.m28.billui.tranferbill.M28ToM30TransferBillDataLogic) context
					.get("transferLogicforM30");
		nc.ui.pp.m28.billui.tranferbill.M28ToM30TransferBillDataLogic bean = new nc.ui.pp.m28.billui.tranferbill.M28ToM30TransferBillDataLogic();
		context.put("transferLogicforM30", bean);
		bean.setBillForm(getBillFormEditor());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.billref.dest.TransferViewProcessor getTransferViewProcessorForM28() {
		if (context.get("transferViewProcessorForM28") != null)
			return (nc.ui.pubapp.billref.dest.TransferViewProcessor) context
					.get("transferViewProcessorForM28");
		nc.ui.pubapp.billref.dest.TransferViewProcessor bean = new nc.ui.pubapp.billref.dest.TransferViewProcessor();
		context.put("transferViewProcessorForM28", bean);
		bean.setList(getListView());
		bean.setActionContainer(getActionsOfList());
		bean.setCardActionContainer(getActionsOfCard());
		bean.setTransferLogic(getTransferLogicforM30());
		bean.setBillForm(getBillFormEditor());
		bean.setCancelAction(getCancelAction());
		bean.setSaveAction(getSaveAction());
		bean.setCommitAction(getSendApproveAction());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.billref.dest.TransferViewProcessor getTransferViewProcessorForSelf() {
		if (context.get("transferViewProcessorForSelf") != null)
			return (nc.ui.pubapp.billref.dest.TransferViewProcessor) context
					.get("transferViewProcessorForSelf");
		nc.ui.pubapp.billref.dest.TransferViewProcessor bean = new nc.ui.pubapp.billref.dest.TransferViewProcessor();
		context.put("transferViewProcessorForSelf", bean);
		bean.setList(getListView());
		bean.setActionContainer(getActionsOfList());
		bean.setCardActionContainer(getActionsOfCard());
		bean.setTransferLogic(getTransferLogicforSelf());
		bean.setBillForm(getBillFormEditor());
		bean.setCancelAction(getCancelAction());
		bean.setSaveAction(getSaveAction());
		bean.setCommitAction(getSendApproveAction());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.billref.dest.TransferViewProcessor getTransferViewProcessorFor5801() {
		if (context.get("transferViewProcessorFor5801") != null)
			return (nc.ui.pubapp.billref.dest.TransferViewProcessor) context
					.get("transferViewProcessorFor5801");
		nc.ui.pubapp.billref.dest.TransferViewProcessor bean = new nc.ui.pubapp.billref.dest.TransferViewProcessor();
		context.put("transferViewProcessorFor5801", bean);
		bean.setList(getListView());
		bean.setActionContainer(getActionsOfList());
		bean.setCardActionContainer(getActionsOfCard());
		bean.setTransferLogic(getTransferLogicfor5801());
		bean.setBillForm(getBillFormEditor());
		bean.setCancelAction(getCancelAction());
		bean.setSaveAction(getSaveAction());
		bean.setCommitAction(getSendApproveAction());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.so.m30.billui.push.M3025801TransferBillDataLogic getTransferLogicfor5801() {
		if (context.get("transferLogicfor5801") != null)
			return (nc.ui.so.m30.billui.push.M3025801TransferBillDataLogic) context
					.get("transferLogicfor5801");
		nc.ui.so.m30.billui.push.M3025801TransferBillDataLogic bean = new nc.ui.so.m30.billui.push.M3025801TransferBillDataLogic();
		context.put("transferLogicfor5801", bean);
		bean.setBillForm(getBillFormEditor());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.so.m30.billui.push.TransferLogicforSelf getTransferLogicforSelf() {
		if (context.get("transferLogicforSelf") != null)
			return (nc.ui.so.m30.billui.push.TransferLogicforSelf) context
					.get("transferLogicforSelf");
		nc.ui.so.m30.billui.push.TransferLogicforSelf bean = new nc.ui.so.m30.billui.push.TransferLogicforSelf();
		context.put("transferLogicforSelf", bean);
		bean.setBillForm(getBillFormEditor());
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
		list.add(getUserQueryParams1());
		list.add(getUserdefQueryParam_1117969());
		list.add(getUserdefQueryParam_3a0a2e());
		return list;
	}

	private nc.ui.uif2.editor.UserdefQueryParam getUserdefQueryParam_1117969() {
		if (context.get("nc.ui.uif2.editor.UserdefQueryParam#1117969") != null)
			return (nc.ui.uif2.editor.UserdefQueryParam) context
					.get("nc.ui.uif2.editor.UserdefQueryParam#1117969");
		nc.ui.uif2.editor.UserdefQueryParam bean = new nc.ui.uif2.editor.UserdefQueryParam();
		context.put("nc.ui.uif2.editor.UserdefQueryParam#1117969", bean);
		bean.setMdfullname("so.so_saleorder");
		bean.setPos(0);
		bean.setPrefix("vdef");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.uif2.editor.UserdefQueryParam getUserdefQueryParam_3a0a2e() {
		if (context.get("nc.ui.uif2.editor.UserdefQueryParam#3a0a2e") != null)
			return (nc.ui.uif2.editor.UserdefQueryParam) context
					.get("nc.ui.uif2.editor.UserdefQueryParam#3a0a2e");
		nc.ui.uif2.editor.UserdefQueryParam bean = new nc.ui.uif2.editor.UserdefQueryParam();
		context.put("nc.ui.uif2.editor.UserdefQueryParam#3a0a2e", bean);
		bean.setMdfullname("so.so_saleorder_b");
		bean.setPos(1);
		bean.setPrefix("vbdef");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.uif2.editor.UserdefQueryParam getUserQueryParams1() {
		if (context.get("userQueryParams1") != null)
			return (nc.ui.uif2.editor.UserdefQueryParam) context
					.get("userQueryParams1");
		nc.ui.uif2.editor.UserdefQueryParam bean = new nc.ui.uif2.editor.UserdefQueryParam();
		context.put("userQueryParams1", bean);
		bean.setMdfullname(" ic.OnhandDimVO");
		bean.setPos(0);
		bean.setPrefix("vdef");
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
		bean.setParams(getManagedList8());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList8() {
		List list = new ArrayList();
		list.add(getUserdefQueryParam_725740());
		list.add(getUserdefQueryParam_be6052());
		return list;
	}

	private nc.ui.uif2.editor.UserdefQueryParam getUserdefQueryParam_725740() {
		if (context.get("nc.ui.uif2.editor.UserdefQueryParam#725740") != null)
			return (nc.ui.uif2.editor.UserdefQueryParam) context
					.get("nc.ui.uif2.editor.UserdefQueryParam#725740");
		nc.ui.uif2.editor.UserdefQueryParam bean = new nc.ui.uif2.editor.UserdefQueryParam();
		context.put("nc.ui.uif2.editor.UserdefQueryParam#725740", bean);
		bean.setMdfullname("so.so_saleorder");
		bean.setPos(0);
		bean.setPrefix("vdef");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.uif2.editor.UserdefQueryParam getUserdefQueryParam_be6052() {
		if (context.get("nc.ui.uif2.editor.UserdefQueryParam#be6052") != null)
			return (nc.ui.uif2.editor.UserdefQueryParam) context
					.get("nc.ui.uif2.editor.UserdefQueryParam#be6052");
		nc.ui.uif2.editor.UserdefQueryParam bean = new nc.ui.uif2.editor.UserdefQueryParam();
		context.put("nc.ui.uif2.editor.UserdefQueryParam#be6052", bean);
		bean.setMdfullname("so.so_saleorder_b");
		bean.setPos(1);
		bean.setTabcode("bodytable1");
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
		bean.setMaterialField("cmaterialvid");
		bean.setStoreStateField("cstateid");
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
		bean.setParams(getManagedList9());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList9() {
		List list = new ArrayList();
		list.add(getQueryParams1());
		list.add(getQueryParam_a5bd6a());
		list.add(getQueryParam_1369734());
		list.add(getQueryParam_100dba5());
		return list;
	}

	private nc.ui.uif2.userdefitem.QueryParam getQueryParam_a5bd6a() {
		if (context.get("nc.ui.uif2.userdefitem.QueryParam#a5bd6a") != null)
			return (nc.ui.uif2.userdefitem.QueryParam) context
					.get("nc.ui.uif2.userdefitem.QueryParam#a5bd6a");
		nc.ui.uif2.userdefitem.QueryParam bean = new nc.ui.uif2.userdefitem.QueryParam();
		context.put("nc.ui.uif2.userdefitem.QueryParam#a5bd6a", bean);
		bean.setMdfullname("so.so_saleorder");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.uif2.userdefitem.QueryParam getQueryParam_1369734() {
		if (context.get("nc.ui.uif2.userdefitem.QueryParam#1369734") != null)
			return (nc.ui.uif2.userdefitem.QueryParam) context
					.get("nc.ui.uif2.userdefitem.QueryParam#1369734");
		nc.ui.uif2.userdefitem.QueryParam bean = new nc.ui.uif2.userdefitem.QueryParam();
		context.put("nc.ui.uif2.userdefitem.QueryParam#1369734", bean);
		bean.setMdfullname("so.so_saleorder_b");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.uif2.userdefitem.QueryParam getQueryParam_100dba5() {
		if (context.get("nc.ui.uif2.userdefitem.QueryParam#100dba5") != null)
			return (nc.ui.uif2.userdefitem.QueryParam) context
					.get("nc.ui.uif2.userdefitem.QueryParam#100dba5");
		nc.ui.uif2.userdefitem.QueryParam bean = new nc.ui.uif2.userdefitem.QueryParam();
		context.put("nc.ui.uif2.userdefitem.QueryParam#100dba5", bean);
		bean.setRulecode("materialassistant");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.uif2.userdefitem.QueryParam getQueryParams1() {
		if (context.get("queryParams1") != null)
			return (nc.ui.uif2.userdefitem.QueryParam) context
					.get("queryParams1");
		nc.ui.uif2.userdefitem.QueryParam bean = new nc.ui.uif2.userdefitem.QueryParam();
		context.put("queryParams1", bean);
		bean.setMdfullname("ic.OnhandDimVO");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.view.FractionFixMediator getFractionFixMediator() {
		if (context.get("fractionFixMediator") != null)
			return (nc.ui.pubapp.uif2app.view.FractionFixMediator) context
					.get("fractionFixMediator");
		nc.ui.pubapp.uif2app.view.FractionFixMediator bean = new nc.ui.pubapp.uif2app.view.FractionFixMediator(
				getManagedList10(), getManagedList11());
		context.put("fractionFixMediator", bean);
		bean.initUI();
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList10() {
		List list = new ArrayList();
		list.add(getBillFormEditor());
		return list;
	}

	private List getManagedList11() {
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
				getManagedList12());
		map.put("nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent",
				getManagedList13());
		map.put("nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent",
				getManagedList14());
		map.put("nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent",
				getManagedList15());
		map.put("nc.ui.pubapp.uif2app.event.card.CardBodyAfterRowEditEvent",
				getManagedList16());
		map.put("nc.ui.pubapp.uif2app.event.card.CardBodyRowEditEvent",
				getManagedList17());
		map.put("nc.ui.pubapp.uif2app.event.OrgChangedEvent",
				getManagedList18());
		return map;
	}

	private List getManagedList12() {
		List list = new ArrayList();
		list.add(getHeadBeforeEditHandler_1545e5d());
		return list;
	}

	private nc.ui.so.m30.billui.editor.headevent.HeadBeforeEditHandler getHeadBeforeEditHandler_1545e5d() {
		if (context
				.get("nc.ui.so.m30.billui.editor.headevent.HeadBeforeEditHandler#1545e5d") != null)
			return (nc.ui.so.m30.billui.editor.headevent.HeadBeforeEditHandler) context
					.get("nc.ui.so.m30.billui.editor.headevent.HeadBeforeEditHandler#1545e5d");
		nc.ui.so.m30.billui.editor.headevent.HeadBeforeEditHandler bean = new nc.ui.so.m30.billui.editor.headevent.HeadBeforeEditHandler();
		context.put(
				"nc.ui.so.m30.billui.editor.headevent.HeadBeforeEditHandler#1545e5d",
				bean);
		bean.setBillform(getBillFormEditor());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList13() {
		List list = new ArrayList();
		list.add(getHeadAfterEditHandler_1ff44f8());
		return list;
	}

	private nc.ui.so.m30.billui.editor.headevent.HeadAfterEditHandler getHeadAfterEditHandler_1ff44f8() {
		if (context
				.get("nc.ui.so.m30.billui.editor.headevent.HeadAfterEditHandler#1ff44f8") != null)
			return (nc.ui.so.m30.billui.editor.headevent.HeadAfterEditHandler) context
					.get("nc.ui.so.m30.billui.editor.headevent.HeadAfterEditHandler#1ff44f8");
		nc.ui.so.m30.billui.editor.headevent.HeadAfterEditHandler bean = new nc.ui.so.m30.billui.editor.headevent.HeadAfterEditHandler();
		context.put(
				"nc.ui.so.m30.billui.editor.headevent.HeadAfterEditHandler#1ff44f8",
				bean);
		bean.setBillform(getBillFormEditor());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList14() {
		List list = new ArrayList();
		list.add(getBodyBeforeEditHandler_9031d());
		return list;
	}

	private nc.ui.so.m30.billui.editor.bodyevent.BodyBeforeEditHandler getBodyBeforeEditHandler_9031d() {
		if (context
				.get("nc.ui.so.m30.billui.editor.bodyevent.BodyBeforeEditHandler#9031d") != null)
			return (nc.ui.so.m30.billui.editor.bodyevent.BodyBeforeEditHandler) context
					.get("nc.ui.so.m30.billui.editor.bodyevent.BodyBeforeEditHandler#9031d");
		nc.ui.so.m30.billui.editor.bodyevent.BodyBeforeEditHandler bean = new nc.ui.so.m30.billui.editor.bodyevent.BodyBeforeEditHandler();
		context.put(
				"nc.ui.so.m30.billui.editor.bodyevent.BodyBeforeEditHandler#9031d",
				bean);
		bean.setBillform(getBillFormEditor());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList15() {
		List list = new ArrayList();
		list.add(getBodyAfterEditHandler_1ab88d6());
		return list;
	}

	private nc.ui.so.m30.billui.editor.bodyevent.BodyAfterEditHandler getBodyAfterEditHandler_1ab88d6() {
		if (context
				.get("nc.ui.so.m30.billui.editor.bodyevent.BodyAfterEditHandler#1ab88d6") != null)
			return (nc.ui.so.m30.billui.editor.bodyevent.BodyAfterEditHandler) context
					.get("nc.ui.so.m30.billui.editor.bodyevent.BodyAfterEditHandler#1ab88d6");
		nc.ui.so.m30.billui.editor.bodyevent.BodyAfterEditHandler bean = new nc.ui.so.m30.billui.editor.bodyevent.BodyAfterEditHandler();
		context.put(
				"nc.ui.so.m30.billui.editor.bodyevent.BodyAfterEditHandler#1ab88d6",
				bean);
		bean.setBillform(getBillFormEditor());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList16() {
		List list = new ArrayList();
		list.add(getCardBodyAfterRowEditHandler_a4d801());
		return list;
	}

	private nc.ui.so.m30.billui.editor.bodyevent.CardBodyAfterRowEditHandler getCardBodyAfterRowEditHandler_a4d801() {
		if (context
				.get("nc.ui.so.m30.billui.editor.bodyevent.CardBodyAfterRowEditHandler#a4d801") != null)
			return (nc.ui.so.m30.billui.editor.bodyevent.CardBodyAfterRowEditHandler) context
					.get("nc.ui.so.m30.billui.editor.bodyevent.CardBodyAfterRowEditHandler#a4d801");
		nc.ui.so.m30.billui.editor.bodyevent.CardBodyAfterRowEditHandler bean = new nc.ui.so.m30.billui.editor.bodyevent.CardBodyAfterRowEditHandler();
		context.put(
				"nc.ui.so.m30.billui.editor.bodyevent.CardBodyAfterRowEditHandler#a4d801",
				bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList17() {
		List list = new ArrayList();
		list.add(getCardBodyRowEditHandler_616160());
		return list;
	}

	private nc.ui.so.m30.billui.editor.bodyevent.CardBodyRowEditHandler getCardBodyRowEditHandler_616160() {
		if (context
				.get("nc.ui.so.m30.billui.editor.bodyevent.CardBodyRowEditHandler#616160") != null)
			return (nc.ui.so.m30.billui.editor.bodyevent.CardBodyRowEditHandler) context
					.get("nc.ui.so.m30.billui.editor.bodyevent.CardBodyRowEditHandler#616160");
		nc.ui.so.m30.billui.editor.bodyevent.CardBodyRowEditHandler bean = new nc.ui.so.m30.billui.editor.bodyevent.CardBodyRowEditHandler();
		context.put(
				"nc.ui.so.m30.billui.editor.bodyevent.CardBodyRowEditHandler#616160",
				bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList18() {
		List list = new ArrayList();
		list.add(getOrgEditHandler_1400391());
		return list;
	}

	private nc.ui.so.m30.billui.editor.orgevent.OrgEditHandler getOrgEditHandler_1400391() {
		if (context
				.get("nc.ui.so.m30.billui.editor.orgevent.OrgEditHandler#1400391") != null)
			return (nc.ui.so.m30.billui.editor.orgevent.OrgEditHandler) context
					.get("nc.ui.so.m30.billui.editor.orgevent.OrgEditHandler#1400391");
		nc.ui.so.m30.billui.editor.orgevent.OrgEditHandler bean = new nc.ui.so.m30.billui.editor.orgevent.OrgEditHandler(
				getBillFormEditor(), getContext());
		context.put(
				"nc.ui.so.m30.billui.editor.orgevent.OrgEditHandler#1400391",
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
		bean.setTangramLayoutRoot(getTBNode_c9c83e());
		bean.initUI();
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.uif2.tangramlayout.node.TBNode getTBNode_c9c83e() {
		if (context.get("nc.ui.uif2.tangramlayout.node.TBNode#c9c83e") != null)
			return (nc.ui.uif2.tangramlayout.node.TBNode) context
					.get("nc.ui.uif2.tangramlayout.node.TBNode#c9c83e");
		nc.ui.uif2.tangramlayout.node.TBNode bean = new nc.ui.uif2.tangramlayout.node.TBNode();
		context.put("nc.ui.uif2.tangramlayout.node.TBNode#c9c83e", bean);
		bean.setShowMode("CardLayout");
		bean.setTabs(getManagedList19());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList19() {
		List list = new ArrayList();
		list.add(getHSNode_1cc8284());
		list.add(getHSNode_7d185a());
		return list;
	}

	private nc.ui.uif2.tangramlayout.node.HSNode getHSNode_1cc8284() {
		if (context.get("nc.ui.uif2.tangramlayout.node.HSNode#1cc8284") != null)
			return (nc.ui.uif2.tangramlayout.node.HSNode) context
					.get("nc.ui.uif2.tangramlayout.node.HSNode#1cc8284");
		nc.ui.uif2.tangramlayout.node.HSNode bean = new nc.ui.uif2.tangramlayout.node.HSNode();
		context.put("nc.ui.uif2.tangramlayout.node.HSNode#1cc8284", bean);
		bean.setLeft(getCNode_1aa4d3c());
		bean.setRight(getVSNode_840a6());
		bean.setDividerLocation(0.22f);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.uif2.tangramlayout.node.CNode getCNode_1aa4d3c() {
		if (context.get("nc.ui.uif2.tangramlayout.node.CNode#1aa4d3c") != null)
			return (nc.ui.uif2.tangramlayout.node.CNode) context
					.get("nc.ui.uif2.tangramlayout.node.CNode#1aa4d3c");
		nc.ui.uif2.tangramlayout.node.CNode bean = new nc.ui.uif2.tangramlayout.node.CNode();
		context.put("nc.ui.uif2.tangramlayout.node.CNode#1aa4d3c", bean);
		bean.setComponent(getQueryArea());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.uif2.tangramlayout.node.VSNode getVSNode_840a6() {
		if (context.get("nc.ui.uif2.tangramlayout.node.VSNode#840a6") != null)
			return (nc.ui.uif2.tangramlayout.node.VSNode) context
					.get("nc.ui.uif2.tangramlayout.node.VSNode#840a6");
		nc.ui.uif2.tangramlayout.node.VSNode bean = new nc.ui.uif2.tangramlayout.node.VSNode();
		context.put("nc.ui.uif2.tangramlayout.node.VSNode#840a6", bean);
		bean.setUp(getCNode_1b4d977());
		bean.setDown(getCNode_897b75());
		bean.setDividerLocation(25f);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.uif2.tangramlayout.node.CNode getCNode_1b4d977() {
		if (context.get("nc.ui.uif2.tangramlayout.node.CNode#1b4d977") != null)
			return (nc.ui.uif2.tangramlayout.node.CNode) context
					.get("nc.ui.uif2.tangramlayout.node.CNode#1b4d977");
		nc.ui.uif2.tangramlayout.node.CNode bean = new nc.ui.uif2.tangramlayout.node.CNode();
		context.put("nc.ui.uif2.tangramlayout.node.CNode#1b4d977", bean);
		bean.setComponent(getListToolbarPnl());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.uif2.tangramlayout.node.CNode getCNode_897b75() {
		if (context.get("nc.ui.uif2.tangramlayout.node.CNode#897b75") != null)
			return (nc.ui.uif2.tangramlayout.node.CNode) context
					.get("nc.ui.uif2.tangramlayout.node.CNode#897b75");
		nc.ui.uif2.tangramlayout.node.CNode bean = new nc.ui.uif2.tangramlayout.node.CNode();
		context.put("nc.ui.uif2.tangramlayout.node.CNode#897b75", bean);
		bean.setName(getI18nFB_312d1f());
		bean.setComponent(getListView());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private java.lang.String getI18nFB_312d1f() {
		if (context.get("nc.ui.uif2.I18nFB#312d1f") != null)
			return (java.lang.String) context.get("nc.ui.uif2.I18nFB#312d1f");
		nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
		context.put("&nc.ui.uif2.I18nFB#312d1f", bean);
		bean.setResDir("common");
		bean.setResId("UC001-0000107");
		bean.setDefaultValue("列表");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		try {
			Object product = bean.getObject();
			context.put("nc.ui.uif2.I18nFB#312d1f", product);
			return (java.lang.String) product;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private nc.ui.uif2.tangramlayout.node.HSNode getHSNode_7d185a() {
		if (context.get("nc.ui.uif2.tangramlayout.node.HSNode#7d185a") != null)
			return (nc.ui.uif2.tangramlayout.node.HSNode) context
					.get("nc.ui.uif2.tangramlayout.node.HSNode#7d185a");
		nc.ui.uif2.tangramlayout.node.HSNode bean = new nc.ui.uif2.tangramlayout.node.HSNode();
		context.put("nc.ui.uif2.tangramlayout.node.HSNode#7d185a", bean);
		bean.setLeft(getVSNode_46669c());
		bean.setRight(getCNode_1fab4f0());
		bean.setDividerLocation(0.8f);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.uif2.tangramlayout.node.VSNode getVSNode_46669c() {
		if (context.get("nc.ui.uif2.tangramlayout.node.VSNode#46669c") != null)
			return (nc.ui.uif2.tangramlayout.node.VSNode) context
					.get("nc.ui.uif2.tangramlayout.node.VSNode#46669c");
		nc.ui.uif2.tangramlayout.node.VSNode bean = new nc.ui.uif2.tangramlayout.node.VSNode();
		context.put("nc.ui.uif2.tangramlayout.node.VSNode#46669c", bean);
		bean.setUp(getCNode_18ce173());
		bean.setDown(getCNode_e12122());
		bean.setDividerLocation(30f);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.uif2.tangramlayout.node.CNode getCNode_18ce173() {
		if (context.get("nc.ui.uif2.tangramlayout.node.CNode#18ce173") != null)
			return (nc.ui.uif2.tangramlayout.node.CNode) context
					.get("nc.ui.uif2.tangramlayout.node.CNode#18ce173");
		nc.ui.uif2.tangramlayout.node.CNode bean = new nc.ui.uif2.tangramlayout.node.CNode();
		context.put("nc.ui.uif2.tangramlayout.node.CNode#18ce173", bean);
		bean.setComponent(getCardToolbarPnl());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.uif2.tangramlayout.node.CNode getCNode_e12122() {
		if (context.get("nc.ui.uif2.tangramlayout.node.CNode#e12122") != null)
			return (nc.ui.uif2.tangramlayout.node.CNode) context
					.get("nc.ui.uif2.tangramlayout.node.CNode#e12122");
		nc.ui.uif2.tangramlayout.node.CNode bean = new nc.ui.uif2.tangramlayout.node.CNode();
		context.put("nc.ui.uif2.tangramlayout.node.CNode#e12122", bean);
		bean.setComponent(getBillFormEditor());
		bean.setName(getI18nFB_2573f9());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private java.lang.String getI18nFB_2573f9() {
		if (context.get("nc.ui.uif2.I18nFB#2573f9") != null)
			return (java.lang.String) context.get("nc.ui.uif2.I18nFB#2573f9");
		nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
		context.put("&nc.ui.uif2.I18nFB#2573f9", bean);
		bean.setResDir("common");
		bean.setResId("UC001-0000106");
		bean.setDefaultValue("卡片");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		try {
			Object product = bean.getObject();
			context.put("nc.ui.uif2.I18nFB#2573f9", product);
			return (java.lang.String) product;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private nc.ui.uif2.tangramlayout.node.CNode getCNode_1fab4f0() {
		if (context.get("nc.ui.uif2.tangramlayout.node.CNode#1fab4f0") != null)
			return (nc.ui.uif2.tangramlayout.node.CNode) context
					.get("nc.ui.uif2.tangramlayout.node.CNode#1fab4f0");
		nc.ui.uif2.tangramlayout.node.CNode bean = new nc.ui.uif2.tangramlayout.node.CNode();
		context.put("nc.ui.uif2.tangramlayout.node.CNode#1fab4f0", bean);
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
		bean.setActions(getManagedList20());
		bean.setTitleAction(getReturnaction());
		bean.setModel(getManageAppModel());
		bean.setRightExActions(getManagedList21());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList20() {
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

	private List getManagedList21() {
		List list = new ArrayList();
		list.add(getActionsBar_ActionsBarSeparator_1799745());
		list.add(getHeadZoomAction());
		return list;
	}

	private nc.ui.pub.beans.ActionsBar.ActionsBarSeparator getActionsBar_ActionsBarSeparator_1799745() {
		if (context
				.get("nc.ui.pub.beans.ActionsBar.ActionsBarSeparator#1799745") != null)
			return (nc.ui.pub.beans.ActionsBar.ActionsBarSeparator) context
					.get("nc.ui.pub.beans.ActionsBar.ActionsBarSeparator#1799745");
		nc.ui.pub.beans.ActionsBar.ActionsBarSeparator bean = new nc.ui.pub.beans.ActionsBar.ActionsBarSeparator();
		context.put("nc.ui.pub.beans.ActionsBar.ActionsBarSeparator#1799745",
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
		bean.setBesideWidgetlets(getManagedList22());
		bean.setContext(getContext());
		bean.setShowAllAction(getShowAllAction());
		bean.initUI();
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList22() {
		List list = new ArrayList();
		list.add(getCreditSideForm());
		list.add(getPaySideForm());
		list.add(getATPSideForm());
		return list;
	}

	public nc.ui.so.m30.billui.view.sideform.ShowAllAction getShowAllAction() {
		if (context.get("showAllAction") != null)
			return (nc.ui.so.m30.billui.view.sideform.ShowAllAction) context
					.get("showAllAction");
		nc.ui.so.m30.billui.view.sideform.ShowAllAction bean = new nc.ui.so.m30.billui.view.sideform.ShowAllAction();
		context.put("showAllAction", bean);
		bean.setMediator(getSideFormMediator());
		bean.setExceptionHandler(getExceptionHandler());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
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
		bean.setContributors(getManagedList23());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList23() {
		List list = new ArrayList();
		list.add(getActionsOfList());
		list.add(getActionsOfCard());
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
		bean.setSourceBillName(getI18nFB_1ba33c4());
		bean.setModel(getManageAppModel());
		bean.setEditor(getBillFormEditor());
		bean.setInterceptor(getFormInterceptor());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private java.lang.String getI18nFB_1ba33c4() {
		if (context.get("nc.ui.uif2.I18nFB#1ba33c4") != null)
			return (java.lang.String) context.get("nc.ui.uif2.I18nFB#1ba33c4");
		nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
		context.put("&nc.ui.uif2.I18nFB#1ba33c4", bean);
		bean.setResDir("4006011_0");
		bean.setResId("04006011-0393");
		bean.setDefaultValue("自制");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		try {
			Object product = bean.getObject();
			context.put("nc.ui.uif2.I18nFB#1ba33c4", product);
			return (java.lang.String) product;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public nc.ui.so.m30.billui.action.SaleOrderAddFromZ3Action getAddZ3Action() {
		if (context.get("addZ3Action") != null)
			return (nc.ui.so.m30.billui.action.SaleOrderAddFromZ3Action) context
					.get("addZ3Action");
		nc.ui.so.m30.billui.action.SaleOrderAddFromZ3Action bean = new nc.ui.so.m30.billui.action.SaleOrderAddFromZ3Action();
		context.put("addZ3Action", bean);
		bean.setSourceBillType("Z3");
		bean.setSourceBillName(getI18nFB_19cb4d4());
		bean.setFlowBillType(false);
		bean.setModel(getManageAppModel());
		bean.setEditor(getBillFormEditor());
		bean.setTransferViewProcessor(getTransferProcessorforZ3());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private java.lang.String getI18nFB_19cb4d4() {
		if (context.get("nc.ui.uif2.I18nFB#19cb4d4") != null)
			return (java.lang.String) context.get("nc.ui.uif2.I18nFB#19cb4d4");
		nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
		context.put("&nc.ui.uif2.I18nFB#19cb4d4", bean);
		bean.setResDir("4006011_0");
		bean.setResId("04006011-0394");
		bean.setDefaultValue("销售合同");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		try {
			Object product = bean.getObject();
			context.put("nc.ui.uif2.I18nFB#19cb4d4", product);
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

	public nc.ui.pubapp.billref.dest.TransferViewProcessor getTransferProcessorforZ3() {
		if (context.get("transferProcessorforZ3") != null)
			return (nc.ui.pubapp.billref.dest.TransferViewProcessor) context
					.get("transferProcessorforZ3");
		nc.ui.pubapp.billref.dest.TransferViewProcessor bean = new nc.ui.pubapp.billref.dest.TransferViewProcessor();
		context.put("transferProcessorforZ3", bean);
		bean.setList(getListView());
		bean.setActionContainer(getActionsOfList());
		bean.setCardActionContainer(getActionsOfCard());
		bean.setTransferLogic(getTransferLogicforZ3());
		bean.setBillForm(getBillFormEditor());
		bean.setCancelAction(getCancelAction());
		bean.setSaveAction(getSaveAction());
		bean.setCommitAction(getSendApproveAction());
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
		bean.setSourceBillName(getI18nFB_123e4c4());
		bean.setFlowBillType(false);
		bean.setModel(getManageAppModel());
		bean.setEditor(getBillFormEditor());
		bean.setTransferViewProcessor(getTransferProcessorfor4310());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private java.lang.String getI18nFB_123e4c4() {
		if (context.get("nc.ui.uif2.I18nFB#123e4c4") != null)
			return (java.lang.String) context.get("nc.ui.uif2.I18nFB#123e4c4");
		nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
		context.put("&nc.ui.uif2.I18nFB#123e4c4", bean);
		bean.setResDir("4006011_0");
		bean.setResId("04006011-0395");
		bean.setDefaultValue("销售报价单");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		try {
			Object product = bean.getObject();
			context.put("nc.ui.uif2.I18nFB#123e4c4", product);
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

	public nc.ui.pubapp.billref.dest.TransferViewProcessor getTransferProcessorfor4310() {
		if (context.get("transferProcessorfor4310") != null)
			return (nc.ui.pubapp.billref.dest.TransferViewProcessor) context
					.get("transferProcessorfor4310");
		nc.ui.pubapp.billref.dest.TransferViewProcessor bean = new nc.ui.pubapp.billref.dest.TransferViewProcessor();
		context.put("transferProcessorfor4310", bean);
		bean.setList(getListView());
		bean.setActionContainer(getActionsOfList());
		bean.setCardActionContainer(getActionsOfCard());
		bean.setTransferLogic(getTransferLogicfor4310());
		bean.setBillForm(getBillFormEditor());
		bean.setCancelAction(getCancelAction());
		bean.setCommitAction(getSendApproveAction());
		bean.setSaveAction(getSaveAction());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.so.m30.billui.tranferbill.M30Ref38TRansferBillDataLogic getTransferLogicfor38() {
		if (context.get("transferLogicfor38") != null)
			return (nc.ui.so.m30.billui.tranferbill.M30Ref38TRansferBillDataLogic) context
					.get("transferLogicfor38");
		nc.ui.so.m30.billui.tranferbill.M30Ref38TRansferBillDataLogic bean = new nc.ui.so.m30.billui.tranferbill.M30Ref38TRansferBillDataLogic();
		context.put("transferLogicfor38", bean);
		bean.setBillForm(getBillFormEditor());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.billref.dest.TransferViewProcessor getTransferProcessorfor38() {
		if (context.get("transferProcessorfor38") != null)
			return (nc.ui.pubapp.billref.dest.TransferViewProcessor) context
					.get("transferProcessorfor38");
		nc.ui.pubapp.billref.dest.TransferViewProcessor bean = new nc.ui.pubapp.billref.dest.TransferViewProcessor();
		context.put("transferProcessorfor38", bean);
		bean.setList(getListView());
		bean.setActionContainer(getActionsOfList());
		bean.setCardActionContainer(getActionsOfCard());
		bean.setTransferLogic(getTransferLogicfor38());
		bean.setBillForm(getBillFormEditor());
		bean.setCancelAction(getCancelAction());
		bean.setSaveAction(getSaveAction());
		bean.setCommitAction(getSendApproveAction());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.so.m30.billui.action.SaleOrderAddFrom4HAction getAdd4HAction() {
		if (context.get("add4HAction") != null)
			return (nc.ui.so.m30.billui.action.SaleOrderAddFrom4HAction) context
					.get("add4HAction");
		nc.ui.so.m30.billui.action.SaleOrderAddFrom4HAction bean = new nc.ui.so.m30.billui.action.SaleOrderAddFrom4HAction();
		context.put("add4HAction", bean);
		bean.setSourceBillType("4H");
		bean.setSourceBillName(getI18nFB_1803bf1());
		bean.setFlowBillType(false);
		bean.setModel(getManageAppModel());
		bean.setEditor(getBillFormEditor());
		bean.setTransferViewProcessor(getTransferProcessorfor4H());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private java.lang.String getI18nFB_1803bf1() {
		if (context.get("nc.ui.uif2.I18nFB#1803bf1") != null)
			return (java.lang.String) context.get("nc.ui.uif2.I18nFB#1803bf1");
		nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
		context.put("&nc.ui.uif2.I18nFB#1803bf1", bean);
		bean.setResDir("4006011_0");
		bean.setResId("04006011-0397");
		bean.setDefaultValue("库存借出单");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		try {
			Object product = bean.getObject();
			context.put("nc.ui.uif2.I18nFB#1803bf1", product);
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

	public nc.ui.pubapp.billref.dest.TransferViewProcessor getTransferProcessorfor4H() {
		if (context.get("transferProcessorfor4H") != null)
			return (nc.ui.pubapp.billref.dest.TransferViewProcessor) context
					.get("transferProcessorfor4H");
		nc.ui.pubapp.billref.dest.TransferViewProcessor bean = new nc.ui.pubapp.billref.dest.TransferViewProcessor();
		context.put("transferProcessorfor4H", bean);
		bean.setList(getListView());
		bean.setActionContainer(getActionsOfList());
		bean.setCardActionContainer(getActionsOfCard());
		bean.setTransferLogic(getTransferLogicfor4H());
		bean.setBillForm(getBillFormEditor());
		bean.setCancelAction(getCancelAction());
		bean.setSaveAction(getSaveAction());
		bean.setCommitAction(getSendApproveAction());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.so.m30.billui.action.SaleOrderAddFromECC1Action getAddECC1Action() {
		if (context.get("addECC1Action") != null)
			return (nc.ui.so.m30.billui.action.SaleOrderAddFromECC1Action) context
					.get("addECC1Action");
		nc.ui.so.m30.billui.action.SaleOrderAddFromECC1Action bean = new nc.ui.so.m30.billui.action.SaleOrderAddFromECC1Action();
		context.put("addECC1Action", bean);
		bean.setSourceBillType("ECC1");
		bean.setSourceBillName(getI18nFB_1df4f0f());
		bean.setFlowBillType(false);
		bean.setModel(getManageAppModel());
		bean.setEditor(getBillFormEditor());
		bean.setTransferViewProcessor(getTransferProcessorforECC1());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private java.lang.String getI18nFB_1df4f0f() {
		if (context.get("nc.ui.uif2.I18nFB#1df4f0f") != null)
			return (java.lang.String) context.get("nc.ui.uif2.I18nFB#1df4f0f");
		nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
		context.put("&nc.ui.uif2.I18nFB#1df4f0f", bean);
		bean.setResDir("4006011_0");
		bean.setResId("04006011-0534");
		bean.setDefaultValue("订单中心预订单");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		try {
			Object product = bean.getObject();
			context.put("nc.ui.uif2.I18nFB#1df4f0f", product);
			return (java.lang.String) product;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public nc.ui.so.m30.billui.tranferbill.M30RefECC1TransferBillDataLogic getTransferLogicforECC1() {
		if (context.get("transferLogicforECC1") != null)
			return (nc.ui.so.m30.billui.tranferbill.M30RefECC1TransferBillDataLogic) context
					.get("transferLogicforECC1");
		nc.ui.so.m30.billui.tranferbill.M30RefECC1TransferBillDataLogic bean = new nc.ui.so.m30.billui.tranferbill.M30RefECC1TransferBillDataLogic();
		context.put("transferLogicforECC1", bean);
		bean.setBillForm(getBillFormEditor());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.billref.dest.TransferViewProcessor getTransferProcessorforECC1() {
		if (context.get("transferProcessorforECC1") != null)
			return (nc.ui.pubapp.billref.dest.TransferViewProcessor) context
					.get("transferProcessorforECC1");
		nc.ui.pubapp.billref.dest.TransferViewProcessor bean = new nc.ui.pubapp.billref.dest.TransferViewProcessor();
		context.put("transferProcessorforECC1", bean);
		bean.setList(getListView());
		bean.setActionContainer(getActionsOfList());
		bean.setCardActionContainer(getActionsOfCard());
		bean.setTransferLogic(getTransferLogicforECC1());
		bean.setBillForm(getBillFormEditor());
		bean.setCancelAction(getCancelAction());
		bean.setCommitAction(getSendApproveAction());
		bean.setSaveAction(getSaveAction());
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

	public nc.ui.so.m30.billui.action.SaleOrderDeleteAction getDeleteAction() {
		if (context.get("deleteAction") != null)
			return (nc.ui.so.m30.billui.action.SaleOrderDeleteAction) context
					.get("deleteAction");
		nc.ui.so.m30.billui.action.SaleOrderDeleteAction bean = new nc.ui.so.m30.billui.action.SaleOrderDeleteAction();
		context.put("deleteAction", bean);
		bean.setModel(getManageAppModel());
		bean.setEditor(getBillFormEditor());
		bean.setActionName("DELETE");
		bean.setBillType("30");
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
		bean.setPermissionCode("30");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.so.m30.billui.action.SaleOrderSaveAction getSaveAction() {
		if (context.get("saveAction") != null)
			return (nc.ui.so.m30.billui.action.SaleOrderSaveAction) context
					.get("saveAction");
		nc.ui.so.m30.billui.action.SaleOrderSaveAction bean = new nc.ui.so.m30.billui.action.SaleOrderSaveAction();
		context.put("saveAction", bean);
		bean.setModel(getManageAppModel());
		bean.setEditor(getBillFormEditor());
		bean.setActionName("WRITE");
		bean.setBillType("30");
		bean.setRefreshAction(getCardRefreshAction());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.uif2.actions.IMGroupChatAction getIMAction() {
		if (context.get("IMAction") != null)
			return (nc.ui.uif2.actions.IMGroupChatAction) context
					.get("IMAction");
		nc.ui.uif2.actions.IMGroupChatAction bean = new nc.ui.uif2.actions.IMGroupChatAction();
		context.put("IMAction", bean);
		bean.setModel(getManageAppModel());
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

	public nc.ui.pubapp.uif2app.actions.RefreshSingleAction getCardRefreshAction() {
		if (context.get("cardRefreshAction") != null)
			return (nc.ui.pubapp.uif2app.actions.RefreshSingleAction) context
					.get("cardRefreshAction");
		nc.ui.pubapp.uif2app.actions.RefreshSingleAction bean = new nc.ui.pubapp.uif2app.actions.RefreshSingleAction();
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
		bean.setActions(getManagedList24());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList24() {
		List list = new ArrayList();
		list.add(getSendApproveAction());
		list.add(getUnsendApproveAction());
		return list;
	}

	public nc.ui.so.m30.billui.action.SaleOrderSendApproveAction getSendApproveAction() {
		if (context.get("sendApproveAction") != null)
			return (nc.ui.so.m30.billui.action.SaleOrderSendApproveAction) context
					.get("sendApproveAction");
		nc.ui.so.m30.billui.action.SaleOrderSendApproveAction bean = new nc.ui.so.m30.billui.action.SaleOrderSendApproveAction();
		context.put("sendApproveAction", bean);
		bean.setModel(getManageAppModel());
		bean.setEditor(getBillFormEditor());
		bean.setFilledUpInFlow(true);
		bean.setActionName("SAVE");
		bean.setBillType("30");
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

	public nc.ui.pubapp.pub.power.PowerValidateService getPowersendappvalidservice() {
		if (context.get("powersendappvalidservice") != null)
			return (nc.ui.pubapp.pub.power.PowerValidateService) context
					.get("powersendappvalidservice");
		nc.ui.pubapp.pub.power.PowerValidateService bean = new nc.ui.pubapp.pub.power.PowerValidateService();
		context.put("powersendappvalidservice", bean);
		bean.setActionCode("commit");
		bean.setBillCodeFiledName("vbillcode");
		bean.setPermissionCode("30");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.so.m30.billui.action.SaleOrderUnSendApproveAction getUnsendApproveAction() {
		if (context.get("unsendApproveAction") != null)
			return (nc.ui.so.m30.billui.action.SaleOrderUnSendApproveAction) context
					.get("unsendApproveAction");
		nc.ui.so.m30.billui.action.SaleOrderUnSendApproveAction bean = new nc.ui.so.m30.billui.action.SaleOrderUnSendApproveAction();
		context.put("unsendApproveAction", bean);
		bean.setModel(getManageAppModel());
		bean.setEditor(getBillFormEditor());
		bean.setFilledUpInFlow(true);
		bean.setActionName("UNSAVE");
		bean.setBillType("30");
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
		bean.setPermissionCode("30");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.so.m30.billui.action.SaleOrderApproveAction getApproveAction() {
		if (context.get("approveAction") != null)
			return (nc.ui.so.m30.billui.action.SaleOrderApproveAction) context
					.get("approveAction");
		nc.ui.so.m30.billui.action.SaleOrderApproveAction bean = new nc.ui.so.m30.billui.action.SaleOrderApproveAction();
		context.put("approveAction", bean);
		bean.setModel(getManageAppModel());
		bean.setEditor(getBillFormEditor());
		bean.setFilledUpInFlow(true);
		bean.setActionName("APPROVE");
		bean.setBillType("30");
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
		bean.setPermissionCode("30");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.so.m30.billui.action.SaleOrderUnApproveAction getUnApproveAction() {
		if (context.get("unApproveAction") != null)
			return (nc.ui.so.m30.billui.action.SaleOrderUnApproveAction) context
					.get("unApproveAction");
		nc.ui.so.m30.billui.action.SaleOrderUnApproveAction bean = new nc.ui.so.m30.billui.action.SaleOrderUnApproveAction();
		context.put("unApproveAction", bean);
		bean.setModel(getManageAppModel());
		bean.setEditor(getBillFormEditor());
		bean.setFilledUpInFlow(true);
		bean.setActionName("UNAPPROVE");
		bean.setBillType("30");
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
		bean.setPermissionCode("30");
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
		bean.setActions(getManagedList25());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList25() {
		List list = new ArrayList();
		list.add(getApproveAction());
		list.add(getUnApproveAction());
		list.add(getQueryAuditFlowAction());
		return list;
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
		bean.setCopyActionProcessor(getCopyActionProcessor_1bf9d37());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.so.m30.billui.action.CopyActionProcessor getCopyActionProcessor_1bf9d37() {
		if (context
				.get("nc.ui.so.m30.billui.action.CopyActionProcessor#1bf9d37") != null)
			return (nc.ui.so.m30.billui.action.CopyActionProcessor) context
					.get("nc.ui.so.m30.billui.action.CopyActionProcessor#1bf9d37");
		nc.ui.so.m30.billui.action.CopyActionProcessor bean = new nc.ui.so.m30.billui.action.CopyActionProcessor();
		context.put("nc.ui.so.m30.billui.action.CopyActionProcessor#1bf9d37",
				bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.so.m30.billui.action.SaleOrderGatheringAction getGatheringAction() {
		if (context.get("gatheringAction") != null)
			return (nc.ui.so.m30.billui.action.SaleOrderGatheringAction) context
					.get("gatheringAction");
		nc.ui.so.m30.billui.action.SaleOrderGatheringAction bean = new nc.ui.so.m30.billui.action.SaleOrderGatheringAction();
		context.put("gatheringAction", bean);
		bean.setModel(getManageAppModel());
		bean.setEditor(getBillFormEditor());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.so.m30.billui.action.SaleOrderReceiveVerifyAction getPayVerifyAction() {
		if (context.get("payVerifyAction") != null)
			return (nc.ui.so.m30.billui.action.SaleOrderReceiveVerifyAction) context
					.get("payVerifyAction");
		nc.ui.so.m30.billui.action.SaleOrderReceiveVerifyAction bean = new nc.ui.so.m30.billui.action.SaleOrderReceiveVerifyAction();
		context.put("payVerifyAction", bean);
		bean.setModel(getManageAppModel());
		bean.setEditor(getBillFormEditor());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.so.m30.billui.action.SaleOrderCashSaleAction getSaleOrderCashSaleAction() {
		if (context.get("saleOrderCashSaleAction") != null)
			return (nc.ui.so.m30.billui.action.SaleOrderCashSaleAction) context
					.get("saleOrderCashSaleAction");
		nc.ui.so.m30.billui.action.SaleOrderCashSaleAction bean = new nc.ui.so.m30.billui.action.SaleOrderCashSaleAction();
		context.put("saleOrderCashSaleAction", bean);
		bean.setModel(getManageAppModel());
		bean.setEditor(getBillFormEditor());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.funcnode.ui.action.MenuAction getGatherActionGroup() {
		if (context.get("gatherActionGroup") != null)
			return (nc.funcnode.ui.action.MenuAction) context
					.get("gatherActionGroup");
		nc.funcnode.ui.action.MenuAction bean = new nc.funcnode.ui.action.MenuAction();
		context.put("gatherActionGroup", bean);
		bean.setCode("gathergroup");
		bean.setName(getI18nFB_eaabb4());
		bean.setActions(getManagedList26());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private java.lang.String getI18nFB_eaabb4() {
		if (context.get("nc.ui.uif2.I18nFB#eaabb4") != null)
			return (java.lang.String) context.get("nc.ui.uif2.I18nFB#eaabb4");
		nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
		context.put("&nc.ui.uif2.I18nFB#eaabb4", bean);
		bean.setResDir("4006011_0");
		bean.setResId("04006011-0398");
		bean.setDefaultValue("订单收款");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		try {
			Object product = bean.getObject();
			context.put("nc.ui.uif2.I18nFB#eaabb4", product);
			return (java.lang.String) product;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private List getManagedList26() {
		List list = new ArrayList();
		list.add(getGatheringAction());
		list.add(getPayVerifyAction());
		list.add(getSaleOrderCashSaleAction());
		return list;
	}

	public nc.ui.so.m30.billui.action.SaleOrderSendArrangeAction getSendArrangeAction() {
		if (context.get("sendArrangeAction") != null)
			return (nc.ui.so.m30.billui.action.SaleOrderSendArrangeAction) context
					.get("sendArrangeAction");
		nc.ui.so.m30.billui.action.SaleOrderSendArrangeAction bean = new nc.ui.so.m30.billui.action.SaleOrderSendArrangeAction();
		context.put("sendArrangeAction", bean);
		bean.setModel(getManageAppModel());
		bean.setEditor(getBillFormEditor());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.so.m30.billui.action.SaleOrderBHArrangeAction getBhArrangeAction() {
		if (context.get("bhArrangeAction") != null)
			return (nc.ui.so.m30.billui.action.SaleOrderBHArrangeAction) context
					.get("bhArrangeAction");
		nc.ui.so.m30.billui.action.SaleOrderBHArrangeAction bean = new nc.ui.so.m30.billui.action.SaleOrderBHArrangeAction();
		context.put("bhArrangeAction", bean);
		bean.setModel(getManageAppModel());
		bean.setEditor(getBillFormEditor());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.so.m30.billui.action.SaleOrderZYArrangeAction getZyArrangeAction() {
		if (context.get("zyArrangeAction") != null)
			return (nc.ui.so.m30.billui.action.SaleOrderZYArrangeAction) context
					.get("zyArrangeAction");
		nc.ui.so.m30.billui.action.SaleOrderZYArrangeAction bean = new nc.ui.so.m30.billui.action.SaleOrderZYArrangeAction();
		context.put("zyArrangeAction", bean);
		bean.setModel(getManageAppModel());
		bean.setEditor(getBillFormEditor());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.funcnode.ui.action.MenuAction getArrangeActionGroup() {
		if (context.get("arrangeActionGroup") != null)
			return (nc.funcnode.ui.action.MenuAction) context
					.get("arrangeActionGroup");
		nc.funcnode.ui.action.MenuAction bean = new nc.funcnode.ui.action.MenuAction();
		context.put("arrangeActionGroup", bean);
		bean.setCode("gt2");
		bean.setName(getI18nFB_aed8be());
		bean.setActions(getManagedList27());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private java.lang.String getI18nFB_aed8be() {
		if (context.get("nc.ui.uif2.I18nFB#aed8be") != null)
			return (java.lang.String) context.get("nc.ui.uif2.I18nFB#aed8be");
		nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
		context.put("&nc.ui.uif2.I18nFB#aed8be", bean);
		bean.setResDir("4006011_0");
		bean.setResId("04006011-0399");
		bean.setDefaultValue("发货安排");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		try {
			Object product = bean.getObject();
			context.put("nc.ui.uif2.I18nFB#aed8be", product);
			return (java.lang.String) product;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private List getManagedList27() {
		List list = new ArrayList();
		list.add(getSendArrangeAction());
		list.add(getBhArrangeAction());
		list.add(getZyArrangeAction());
		return list;
	}

	public nc.ui.so.m30.billui.action.assist.FreezeAction getFreezeAction() {
		if (context.get("freezeAction") != null)
			return (nc.ui.so.m30.billui.action.assist.FreezeAction) context
					.get("freezeAction");
		nc.ui.so.m30.billui.action.assist.FreezeAction bean = new nc.ui.so.m30.billui.action.assist.FreezeAction();
		context.put("freezeAction", bean);
		bean.setModel(getManageAppModel());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.so.m30.billui.action.assist.UnFreezeAction getUnFreezeAction() {
		if (context.get("unFreezeAction") != null)
			return (nc.ui.so.m30.billui.action.assist.UnFreezeAction) context
					.get("unFreezeAction");
		nc.ui.so.m30.billui.action.assist.UnFreezeAction bean = new nc.ui.so.m30.billui.action.assist.UnFreezeAction();
		context.put("unFreezeAction", bean);
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
		bean.setSingleBillService(getM30CloseBillCloseService_117c111());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.so.m30.closemanage.model.M30CloseBillCloseService getM30CloseBillCloseService_117c111() {
		if (context
				.get("nc.ui.so.m30.closemanage.model.M30CloseBillCloseService#117c111") != null)
			return (nc.ui.so.m30.closemanage.model.M30CloseBillCloseService) context
					.get("nc.ui.so.m30.closemanage.model.M30CloseBillCloseService#117c111");
		nc.ui.so.m30.closemanage.model.M30CloseBillCloseService bean = new nc.ui.so.m30.closemanage.model.M30CloseBillCloseService();
		context.put(
				"nc.ui.so.m30.closemanage.model.M30CloseBillCloseService#117c111",
				bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.so.m30.billui.action.assist.SaleOrderReverseAction getReverseAction() {
		if (context.get("reverseAction") != null)
			return (nc.ui.so.m30.billui.action.assist.SaleOrderReverseAction) context
					.get("reverseAction");
		nc.ui.so.m30.billui.action.assist.SaleOrderReverseAction bean = new nc.ui.so.m30.billui.action.assist.SaleOrderReverseAction();
		context.put("reverseAction", bean);
		bean.setModel(getManageAppModel());
		bean.setEditor(getBillFormEditor());
		bean.setListView(getListView());
		bean.setRefreshAction(getCardRefreshAction());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.so.m30.billui.action.assist.SaleorderReverseQueryAction getQueryReverseAction() {
		if (context.get("queryReverseAction") != null)
			return (nc.ui.so.m30.billui.action.assist.SaleorderReverseQueryAction) context
					.get("queryReverseAction");
		nc.ui.so.m30.billui.action.assist.SaleorderReverseQueryAction bean = new nc.ui.so.m30.billui.action.assist.SaleorderReverseQueryAction();
		context.put("queryReverseAction", bean);
		bean.setModel(getManageAppModel());
		bean.setEditor(getBillFormEditor());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.so.m30.billui.action.assist.TransInfoAction getTransInfoAction() {
		if (context.get("transInfoAction") != null)
			return (nc.ui.so.m30.billui.action.assist.TransInfoAction) context
					.get("transInfoAction");
		nc.ui.so.m30.billui.action.assist.TransInfoAction bean = new nc.ui.so.m30.billui.action.assist.TransInfoAction();
		context.put("transInfoAction", bean);
		bean.setModel(getManageAppModel());
		bean.setEditor(getBillFormEditor());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.so.m30.billui.action.assist.SaleOrderPushTo21Action getPushTo21Action() {
		if (context.get("pushTo21Action") != null)
			return (nc.ui.so.m30.billui.action.assist.SaleOrderPushTo21Action) context
					.get("pushTo21Action");
		nc.ui.so.m30.billui.action.assist.SaleOrderPushTo21Action bean = new nc.ui.so.m30.billui.action.assist.SaleOrderPushTo21Action();
		context.put("pushTo21Action", bean);
		bean.setSourceBillName(getI18nFB_848239());
		bean.setFlowBillType(false);
		bean.setModel(getManageAppModel());
		bean.setEditor(getBillFormEditor());
		bean.setRefreshAction(getCardRefreshAction());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private java.lang.String getI18nFB_848239() {
		if (context.get("nc.ui.uif2.I18nFB#848239") != null)
			return (java.lang.String) context.get("nc.ui.uif2.I18nFB#848239");
		nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
		context.put("&nc.ui.uif2.I18nFB#848239", bean);
		bean.setResDir("4006011_0");
		bean.setResId("04006011-0464");
		bean.setDefaultValue("生成协同采购订单");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		try {
			Object product = bean.getObject();
			context.put("nc.ui.uif2.I18nFB#848239", product);
			return (java.lang.String) product;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public nc.ui.so.m30.billui.action.assist.SaleOrderRefCoop21Action getRefCoop21Action() {
		if (context.get("refCoop21Action") != null)
			return (nc.ui.so.m30.billui.action.assist.SaleOrderRefCoop21Action) context
					.get("refCoop21Action");
		nc.ui.so.m30.billui.action.assist.SaleOrderRefCoop21Action bean = new nc.ui.so.m30.billui.action.assist.SaleOrderRefCoop21Action();
		context.put("refCoop21Action", bean);
		bean.setSourceBillType("21");
		bean.setSourceBillName(getI18nFB_1819788());
		bean.setFlowBillType(false);
		bean.setModel(getManageAppModel());
		bean.setEditor(getBillFormEditor());
		bean.setTransferBillViewProcessor(getTransferProcessorforCoop21());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private java.lang.String getI18nFB_1819788() {
		if (context.get("nc.ui.uif2.I18nFB#1819788") != null)
			return (java.lang.String) context.get("nc.ui.uif2.I18nFB#1819788");
		nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
		context.put("&nc.ui.uif2.I18nFB#1819788", bean);
		bean.setResDir("4006011_0");
		bean.setResId("04006011-0401");
		bean.setDefaultValue("参照协同采购订单新增");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		try {
			Object product = bean.getObject();
			context.put("nc.ui.uif2.I18nFB#1819788", product);
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

	public nc.ui.so.m30.billui.action.assist.ReturnSaleOrderAddFrom30Action getReturnRef30Action() {
		if (context.get("returnRef30Action") != null)
			return (nc.ui.so.m30.billui.action.assist.ReturnSaleOrderAddFrom30Action) context
					.get("returnRef30Action");
		nc.ui.so.m30.billui.action.assist.ReturnSaleOrderAddFrom30Action bean = new nc.ui.so.m30.billui.action.assist.ReturnSaleOrderAddFrom30Action();
		context.put("returnRef30Action", bean);
		bean.setSourceBillType("30");
		bean.setSourceBillName(getI18nFB_1038665());
		bean.setFlowBillType(false);
		bean.setModel(getManageAppModel());
		bean.setEditor(getBillFormEditor());
		bean.setTransferViewProcessor(getTransferProcessorforwithdraw());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private java.lang.String getI18nFB_1038665() {
		if (context.get("nc.ui.uif2.I18nFB#1038665") != null)
			return (java.lang.String) context.get("nc.ui.uif2.I18nFB#1038665");
		nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
		context.put("&nc.ui.uif2.I18nFB#1038665", bean);
		bean.setResDir("4006011_0");
		bean.setResId("04006011-0402");
		bean.setDefaultValue("销售订单");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		try {
			Object product = bean.getObject();
			context.put("nc.ui.uif2.I18nFB#1038665", product);
			return (java.lang.String) product;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public nc.ui.pubapp.billref.dest.TransferViewProcessor getTransferProcessorforwithdraw() {
		if (context.get("transferProcessorforwithdraw") != null)
			return (nc.ui.pubapp.billref.dest.TransferViewProcessor) context
					.get("transferProcessorforwithdraw");
		nc.ui.pubapp.billref.dest.TransferViewProcessor bean = new nc.ui.pubapp.billref.dest.TransferViewProcessor();
		context.put("transferProcessorforwithdraw", bean);
		bean.setList(getListView());
		bean.setActionContainer(getActionsOfList());
		bean.setCardActionContainer(getActionsOfCard());
		bean.setTransferLogic(getTransferLogicforwithdraw());
		bean.setBillForm(getBillFormEditor());
		bean.setCancelAction(getCancelAction());
		bean.setSaveAction(getSaveAction());
		bean.setCommitAction(getSendApproveAction());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
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

	public nc.ui.so.m30.billui.action.assist.ReturnSaleOrderAddFrom4CAction getReturnRef4CAction() {
		if (context.get("returnRef4CAction") != null)
			return (nc.ui.so.m30.billui.action.assist.ReturnSaleOrderAddFrom4CAction) context
					.get("returnRef4CAction");
		nc.ui.so.m30.billui.action.assist.ReturnSaleOrderAddFrom4CAction bean = new nc.ui.so.m30.billui.action.assist.ReturnSaleOrderAddFrom4CAction();
		context.put("returnRef4CAction", bean);
		bean.setSourceBillType("4C");
		bean.setSourceBillName(getI18nFB_6c578f());
		bean.setFlowBillType(false);
		bean.setModel(getManageAppModel());
		bean.setEditor(getBillFormEditor());
		bean.setTransferViewProcessor(getTransferProcessorforsaleout());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private java.lang.String getI18nFB_6c578f() {
		if (context.get("nc.ui.uif2.I18nFB#6c578f") != null)
			return (java.lang.String) context.get("nc.ui.uif2.I18nFB#6c578f");
		nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
		context.put("&nc.ui.uif2.I18nFB#6c578f", bean);
		bean.setResDir("4006011_0");
		bean.setResId("04006011-0465");
		bean.setDefaultValue("销售出库单");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		try {
			Object product = bean.getObject();
			context.put("nc.ui.uif2.I18nFB#6c578f", product);
			return (java.lang.String) product;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public nc.ui.pubapp.billref.dest.TransferViewProcessor getTransferProcessorforsaleout() {
		if (context.get("transferProcessorforsaleout") != null)
			return (nc.ui.pubapp.billref.dest.TransferViewProcessor) context
					.get("transferProcessorforsaleout");
		nc.ui.pubapp.billref.dest.TransferViewProcessor bean = new nc.ui.pubapp.billref.dest.TransferViewProcessor();
		context.put("transferProcessorforsaleout", bean);
		bean.setList(getListView());
		bean.setActionContainer(getActionsOfList());
		bean.setCardActionContainer(getActionsOfCard());
		bean.setTransferLogic(getTransferLogicforsaleout());
		bean.setBillForm(getBillFormEditor());
		bean.setCancelAction(getCancelAction());
		bean.setSaveAction(getSaveAction());
		bean.setCommitAction(getSendApproveAction());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
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

	public nc.ui.pp.m28.action.SCMLinkQueryActionForM30 getLinkQueryAction() {
		if (context.get("linkQueryAction") != null)
			return (nc.ui.pp.m28.action.SCMLinkQueryActionForM30) context
					.get("linkQueryAction");
		nc.ui.pp.m28.action.SCMLinkQueryActionForM30 bean = new nc.ui.pp.m28.action.SCMLinkQueryActionForM30();
		context.put("linkQueryAction", bean);
		bean.setModel(getManageAppModel());
		bean.setBillType("30");
		bean.setOpenMode(1);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.so.m30.billui.action.link.QueryAuditFlowAction getQueryAuditFlowAction() {
		if (context.get("queryAuditFlowAction") != null)
			return (nc.ui.so.m30.billui.action.link.QueryAuditFlowAction) context
					.get("queryAuditFlowAction");
		nc.ui.so.m30.billui.action.link.QueryAuditFlowAction bean = new nc.ui.so.m30.billui.action.link.QueryAuditFlowAction();
		context.put("queryAuditFlowAction", bean);
		bean.setModel(getManageAppModel());
		bean.setEditor(getBillFormEditor());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.so.m30.billui.action.link.QueryCustInfoAction getQueryCustInfoAction() {
		if (context.get("queryCustInfoAction") != null)
			return (nc.ui.so.m30.billui.action.link.QueryCustInfoAction) context
					.get("queryCustInfoAction");
		nc.ui.so.m30.billui.action.link.QueryCustInfoAction bean = new nc.ui.so.m30.billui.action.link.QueryCustInfoAction();
		context.put("queryCustInfoAction", bean);
		bean.setModel(getManageAppModel());
		bean.setEditor(getBillFormEditor());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.so.m30.billui.action.link.SaleOrderProfitAction getCrossProfAction() {
		if (context.get("crossProfAction") != null)
			return (nc.ui.so.m30.billui.action.link.SaleOrderProfitAction) context
					.get("crossProfAction");
		nc.ui.so.m30.billui.action.link.SaleOrderProfitAction bean = new nc.ui.so.m30.billui.action.link.SaleOrderProfitAction();
		context.put("crossProfAction", bean);
		bean.setModel(getManageAppModel());
		bean.setEditor(getBillFormEditor());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.so.m30.billui.action.link.QueryExecInfoAction getQueryExecInfoAction() {
		if (context.get("queryExecInfoAction") != null)
			return (nc.ui.so.m30.billui.action.link.QueryExecInfoAction) context
					.get("queryExecInfoAction");
		nc.ui.so.m30.billui.action.link.QueryExecInfoAction bean = new nc.ui.so.m30.billui.action.link.QueryExecInfoAction();
		context.put("queryExecInfoAction", bean);
		bean.setModel(getManageAppModel());
		bean.setEditor(getBillFormEditor());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.so.m30.billui.action.link.SaleOrderRecPlanAction getReceivePlanAction() {
		if (context.get("ReceivePlanAction") != null)
			return (nc.ui.so.m30.billui.action.link.SaleOrderRecPlanAction) context
					.get("ReceivePlanAction");
		nc.ui.so.m30.billui.action.link.SaleOrderRecPlanAction bean = new nc.ui.so.m30.billui.action.link.SaleOrderRecPlanAction();
		context.put("ReceivePlanAction", bean);
		bean.setModel(getManageAppModel());
		bean.setEditor(getBillFormEditor());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.so.m30.billui.action.link.QueryCashArsubAction getQueryCashArsubAction() {
		if (context.get("queryCashArsubAction") != null)
			return (nc.ui.so.m30.billui.action.link.QueryCashArsubAction) context
					.get("queryCashArsubAction");
		nc.ui.so.m30.billui.action.link.QueryCashArsubAction bean = new nc.ui.so.m30.billui.action.link.QueryCashArsubAction();
		context.put("queryCashArsubAction", bean);
		bean.setModel(getManageAppModel());
		bean.setEditor(getBillFormEditor());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.funcnode.ui.action.MenuAction getBrowLinkGroupAction() {
		if (context.get("browLinkGroupAction") != null)
			return (nc.funcnode.ui.action.MenuAction) context
					.get("browLinkGroupAction");
		nc.funcnode.ui.action.MenuAction bean = new nc.funcnode.ui.action.MenuAction();
		context.put("browLinkGroupAction", bean);
		bean.setCode("browlinkgroup");
		bean.setName(getI18nFB_e89fc4());
		bean.setActions(getManagedList28());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private java.lang.String getI18nFB_e89fc4() {
		if (context.get("nc.ui.uif2.I18nFB#e89fc4") != null)
			return (java.lang.String) context.get("nc.ui.uif2.I18nFB#e89fc4");
		nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
		context.put("&nc.ui.uif2.I18nFB#e89fc4", bean);
		bean.setResDir("4006011_0");
		bean.setResId("04006011-0392");
		bean.setDefaultValue("联查");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		try {
			Object product = bean.getObject();
			context.put("nc.ui.uif2.I18nFB#e89fc4", product);
			return (java.lang.String) product;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private List getManagedList28() {
		List list = new ArrayList();
		list.add(getLinkQueryAction());
		list.add(getQueryOnhandAction());
		list.add(getCreditQueryAction());
		list.add(getQueryCustInfoAction());
		list.add(getCrossProfAction());
		list.add(getQueryExecInfoAction());
		list.add(getReceivePlanAction());
		list.add(getQueryCashArsubAction());
		list.add(getInterNNProAction());
		return list;
	}

	public nc.ui.so.m30.billui.action.InterNNProAction getInterNNProAction() {
		if (context.get("InterNNProAction") != null)
			return (nc.ui.so.m30.billui.action.InterNNProAction) context
					.get("InterNNProAction");
		nc.ui.so.m30.billui.action.InterNNProAction bean = new nc.ui.so.m30.billui.action.InterNNProAction();
		context.put("InterNNProAction", bean);
		bean.setModel(getManageAppModel());
		bean.setEditor(getBillFormEditor());
		bean.setListView(getListView());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
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
		bean.setBillType("30");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.funcnode.ui.action.GroupAction getPrintActionGroup() {
		if (context.get("printActionGroup") != null)
			return (nc.funcnode.ui.action.GroupAction) context
					.get("printActionGroup");
		nc.funcnode.ui.action.GroupAction bean = new nc.funcnode.ui.action.GroupAction();
		context.put("printActionGroup", bean);
		bean.setCode("printMenuAction");
		bean.setName(getI18nFB_511905());
		bean.setActions(getManagedList29());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private java.lang.String getI18nFB_511905() {
		if (context.get("nc.ui.uif2.I18nFB#511905") != null)
			return (java.lang.String) context.get("nc.ui.uif2.I18nFB#511905");
		nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
		context.put("&nc.ui.uif2.I18nFB#511905", bean);
		bean.setResDir("common");
		bean.setResId("UC001-0000007");
		bean.setDefaultValue("打印");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		try {
			Object product = bean.getObject();
			context.put("nc.ui.uif2.I18nFB#511905", product);
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
		list.add(getSeparatorAction());
		list.add(getSplitPrintAction());
		list.add(getPrintCountQueryAction());
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

	public nc.ui.so.m30.billui.action.SaleOrderExchangeAction getExchangeAction() {
		if (context.get("exchangeAction") != null)
			return (nc.ui.so.m30.billui.action.SaleOrderExchangeAction) context
					.get("exchangeAction");
		nc.ui.so.m30.billui.action.SaleOrderExchangeAction bean = new nc.ui.so.m30.billui.action.SaleOrderExchangeAction();
		context.put("exchangeAction", bean);
		bean.setModel(getManageAppModel());
		bean.setEditor(getBillFormEditor());
		bean.setLineaction(getSaleOrderInsertLineAction());
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
		if (context.get("largessApportionAction") != null)
			return (nc.ui.so.m30.billui.action.SaleOrderLargessApportionAction) context
					.get("largessApportionAction");
		nc.ui.so.m30.billui.action.SaleOrderLargessApportionAction bean = new nc.ui.so.m30.billui.action.SaleOrderLargessApportionAction();
		context.put("largessApportionAction", bean);
		bean.setModel(getManageAppModel());
		bean.setEditor(getBillFormEditor());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.so.m30.billui.action.SaleOrderUndoLargessApportionAction getUnLargessApportionAction() {
		if (context.get("unLargessApportionAction") != null)
			return (nc.ui.so.m30.billui.action.SaleOrderUndoLargessApportionAction) context
					.get("unLargessApportionAction");
		nc.ui.so.m30.billui.action.SaleOrderUndoLargessApportionAction bean = new nc.ui.so.m30.billui.action.SaleOrderUndoLargessApportionAction();
		context.put("unLargessApportionAction", bean);
		bean.setModel(getManageAppModel());
		bean.setEditor(getBillFormEditor());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
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

	public nc.funcnode.ui.action.MenuAction getEditAstActionGroup() {
		if (context.get("editAstActionGroup") != null)
			return (nc.funcnode.ui.action.MenuAction) context
					.get("editAstActionGroup");
		nc.funcnode.ui.action.MenuAction bean = new nc.funcnode.ui.action.MenuAction();
		context.put("editAstActionGroup", bean);
		bean.setCode("editastgroup");
		bean.setName(getI18nFB_41c9b0());
		bean.setActions(getManagedList30());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private java.lang.String getI18nFB_41c9b0() {
		if (context.get("nc.ui.uif2.I18nFB#41c9b0") != null)
			return (java.lang.String) context.get("nc.ui.uif2.I18nFB#41c9b0");
		nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
		context.put("&nc.ui.uif2.I18nFB#41c9b0", bean);
		bean.setResDir("4006011_0");
		bean.setResId("04006011-0400");
		bean.setDefaultValue("辅助功能");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		try {
			Object product = bean.getObject();
			context.put("nc.ui.uif2.I18nFB#41c9b0", product);
			return (java.lang.String) product;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private List getManagedList30() {
		List list = new ArrayList();
		list.add(getOffsetAction());
		list.add(getUnoffsetAction());
		list.add(getSaleOrderCashSaleAction());
		list.add(getExchangeAction());
		list.add(getFindPriceAction());
		list.add(getPriceFormAction());
		list.add(getLargessApportionAction());
		list.add(getUnLargessApportionAction());
		list.add(getFeatureSelectAction());
		return list;
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

	public nc.funcnode.ui.action.MenuAction getEditLinkActionGroup() {
		if (context.get("editLinkActionGroup") != null)
			return (nc.funcnode.ui.action.MenuAction) context
					.get("editLinkActionGroup");
		nc.funcnode.ui.action.MenuAction bean = new nc.funcnode.ui.action.MenuAction();
		context.put("editLinkActionGroup", bean);
		bean.setCode("editlinkgroup");
		bean.setName(getI18nFB_1205fb1());
		bean.setActions(getManagedList31());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private java.lang.String getI18nFB_1205fb1() {
		if (context.get("nc.ui.uif2.I18nFB#1205fb1") != null)
			return (java.lang.String) context.get("nc.ui.uif2.I18nFB#1205fb1");
		nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
		context.put("&nc.ui.uif2.I18nFB#1205fb1", bean);
		bean.setResDir("4006011_0");
		bean.setResId("04006011-0392");
		bean.setDefaultValue("联查");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		try {
			Object product = bean.getObject();
			context.put("nc.ui.uif2.I18nFB#1205fb1", product);
			return (java.lang.String) product;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private List getManagedList31() {
		List list = new ArrayList();
		list.add(getQueryOnhandAction());
		list.add(getCreditQueryAction());
		list.add(getQueryCustInfoAction());
		list.add(getCrossProfAction());
		list.add(getQueryExecInfoAction());
		list.add(getQueryCashArsubAction());
		return list;
	}

	public nc.ui.pubapp.uif2app.lazilyload.DefaultBillLazilyLoader getBillLazilyLoader() {
		if (context.get("billLazilyLoader") != null)
			return (nc.ui.pubapp.uif2app.lazilyload.DefaultBillLazilyLoader) context
					.get("billLazilyLoader");
		nc.ui.pubapp.uif2app.lazilyload.DefaultBillLazilyLoader bean = new nc.ui.pubapp.uif2app.lazilyload.DefaultBillLazilyLoader();
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
		bean.setLazilyLoadSupporter(getManagedList32());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList32() {
		List list = new ArrayList();
		list.add(getCardPanelLazilyLoad_6a78b6());
		list.add(getListPanelLazilyLoad_1b262d());
		list.add(getActionLazilyLoad_678cd4());
		return list;
	}

	private nc.ui.pubapp.uif2app.lazilyload.CardPanelLazilyLoad getCardPanelLazilyLoad_6a78b6() {
		if (context
				.get("nc.ui.pubapp.uif2app.lazilyload.CardPanelLazilyLoad#6a78b6") != null)
			return (nc.ui.pubapp.uif2app.lazilyload.CardPanelLazilyLoad) context
					.get("nc.ui.pubapp.uif2app.lazilyload.CardPanelLazilyLoad#6a78b6");
		nc.ui.pubapp.uif2app.lazilyload.CardPanelLazilyLoad bean = new nc.ui.pubapp.uif2app.lazilyload.CardPanelLazilyLoad();
		context.put(
				"nc.ui.pubapp.uif2app.lazilyload.CardPanelLazilyLoad#6a78b6",
				bean);
		bean.setBillform(getBillFormEditor());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.pubapp.uif2app.lazilyload.ListPanelLazilyLoad getListPanelLazilyLoad_1b262d() {
		if (context
				.get("nc.ui.pubapp.uif2app.lazilyload.ListPanelLazilyLoad#1b262d") != null)
			return (nc.ui.pubapp.uif2app.lazilyload.ListPanelLazilyLoad) context
					.get("nc.ui.pubapp.uif2app.lazilyload.ListPanelLazilyLoad#1b262d");
		nc.ui.pubapp.uif2app.lazilyload.ListPanelLazilyLoad bean = new nc.ui.pubapp.uif2app.lazilyload.ListPanelLazilyLoad();
		context.put(
				"nc.ui.pubapp.uif2app.lazilyload.ListPanelLazilyLoad#1b262d",
				bean);
		bean.setListView(getListView());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.pubapp.uif2app.lazilyload.ActionLazilyLoad getActionLazilyLoad_678cd4() {
		if (context
				.get("nc.ui.pubapp.uif2app.lazilyload.ActionLazilyLoad#678cd4") != null)
			return (nc.ui.pubapp.uif2app.lazilyload.ActionLazilyLoad) context
					.get("nc.ui.pubapp.uif2app.lazilyload.ActionLazilyLoad#678cd4");
		nc.ui.pubapp.uif2app.lazilyload.ActionLazilyLoad bean = new nc.ui.pubapp.uif2app.lazilyload.ActionLazilyLoad();
		context.put("nc.ui.pubapp.uif2app.lazilyload.ActionLazilyLoad#678cd4",
				bean);
		bean.setModel(getManageAppModel());
		bean.setActionList(getManagedList33());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList33() {
		List list = new ArrayList();
		list.add(getPrintAction());
		list.add(getPreviewAction());
		list.add(getOutputAction());
		list.add(getSplitPrintAction());
		list.add(getBhArrangeAction());
		list.add(getZyArrangeAction());
		list.add(getQueryReverseAction());
		return list;
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
		bean.setDataManager(getModelDataManager());
		bean.setQryCondDLGInitializer(getQueryDLGInitializer());
		bean.setModel(getManageAppModel());
		bean.setTemplateContainer(getQueryTemplateContainer());
		bean.setInterceptor(getListInterceptor());
		bean.setShowUpComponent(getListView());
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
		bean.setBillType("30");
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
		bean.setRemoteCallers(getManagedList34());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList34() {
		List list = new ArrayList();
		list.add(getQueryTemplateContainer());
		list.add(getTemplateContainer());
		list.add(getUserdefitemContainer());
		list.add(getPfAddInfoLoader());
		return list;
	}

	public nc.ui.uif2.actions.StandAloneToftPanelActionContainer getActionsOfList() {
		if (context.get("actionsOfList") != null)
			return (nc.ui.uif2.actions.StandAloneToftPanelActionContainer) context
					.get("actionsOfList");
		nc.ui.uif2.actions.StandAloneToftPanelActionContainer bean = new nc.ui.uif2.actions.StandAloneToftPanelActionContainer(
				getListView());
		context.put("actionsOfList", bean);
		bean.setActions(getManagedList35());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList35() {
		List list = new ArrayList();
		list.add(getAddMenuGroup());
		list.add(getEditAction());
		list.add(getDeleteAction());
		list.add(getCopyAction());
		list.add(getSeparatorAction());
		list.add(getQueryAction());
		list.add(getListRefreshAction());
		list.add(getSeparatorAction());
		list.add(getSendActionGroup());
		list.add(getApproveActionGroup());
		list.add(getGatherActionGroup());
		list.add(getArrangeActionGroup());
		list.add(getBrowAstActionGroup());
		list.add(getSeparatorAction());
		list.add(getBrowLinkGroupAction());
		list.add(getSeparatorAction());
		list.add(getToReviseM30Action());
		list.add(getReviseHistoryAction());
		list.add(getSeparatorAction());
		list.add(getPrintActionGroup());
		list.add(getXsddAction());
		return list;
	}

	public nc.ui.uif2.actions.StandAloneToftPanelActionContainer getActionsOfCard() {
		if (context.get("actionsOfCard") != null)
			return (nc.ui.uif2.actions.StandAloneToftPanelActionContainer) context
					.get("actionsOfCard");
		nc.ui.uif2.actions.StandAloneToftPanelActionContainer bean = new nc.ui.uif2.actions.StandAloneToftPanelActionContainer(
				getBillFormEditor());
		context.put("actionsOfCard", bean);
		bean.setActions(getManagedList36());
		bean.setEditActions(getManagedList37());
		bean.setModel(getManageAppModel());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList36() {
		List list = new ArrayList();
		list.add(getAddMenuGroup());
		list.add(getEditAction());
		list.add(getDeleteAction());
		list.add(getCopyAction());
		list.add(getSeparatorAction());
		list.add(getQueryAction());
		list.add(getCardRefreshAction());
		list.add(getSeparatorAction());
		list.add(getSendActionGroup());
		list.add(getApproveActionGroup());
		list.add(getGatherActionGroup());
		list.add(getArrangeActionGroup());
		list.add(getBrowAstActionGroup());
		list.add(getSeparatorAction());
		list.add(getBrowLinkGroupAction());
		list.add(getSeparatorAction());
		list.add(getToReviseM30Action());
		list.add(getReviseHistoryAction());
		list.add(getSeparatorAction());
		list.add(getPrintActionGroup());
		list.add(getSeparatorAction());
		list.add(getIMAction());
		list.add(getXsddAction());
		list.add(getSeparatorAction());
		list.add(getSend2gf());
		return list;
	}

	private List getManagedList37() {
		List list = new ArrayList();
		list.add(getSaveAction());
		list.add(getSaveandsendApproveAction());
		list.add(getSeparatorAction());
		list.add(getCancelAction());
		list.add(getSeparatorAction());
		list.add(getEditAstActionGroup());
		list.add(getSeparatorAction());
		list.add(getEditLinkActionGroup());
		list.add(getSeparatorAction());
		list.add(getAdd28LineAction());
		return list;
	}

	public nc.ui.pubapp.uif2app.actions.AddMenuAction getAddMenuGroup() {
		if (context.get("addMenuGroup") != null)
			return (nc.ui.pubapp.uif2app.actions.AddMenuAction) context
					.get("addMenuGroup");
		nc.ui.pubapp.uif2app.actions.AddMenuAction bean = new nc.ui.pubapp.uif2app.actions.AddMenuAction();
		context.put("addMenuGroup", bean);
		bean.setBillType("30");
		bean.setActions(getManagedList38());
		bean.setModel(getManageAppModel());
		bean.setPfAddInfoLoader(getPfAddInfoLoader());
		bean.initUI();
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList38() {
		List list = new ArrayList();
		list.add(getAddManualAction());
		list.add(getSeparatorAction());
		list.add(getAddZ3Action());
		list.add(getAdd4310Action());
		list.add(getAdd4HAction());
		list.add(getAddECC1Action());
		list.add(getAdd28Action());
		list.add(getAddLS41Action());
		list.add(getAdd5805Action());
		return list;
	}

	public nc.funcnode.ui.action.MenuAction getBrowAstActionGroup() {
		if (context.get("browAstActionGroup") != null)
			return (nc.funcnode.ui.action.MenuAction) context
					.get("browAstActionGroup");
		nc.funcnode.ui.action.MenuAction bean = new nc.funcnode.ui.action.MenuAction();
		context.put("browAstActionGroup", bean);
		bean.setCode("browastgroup");
		bean.setName(getI18nFB_4ecf3a());
		bean.setActions(getManagedList39());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private java.lang.String getI18nFB_4ecf3a() {
		if (context.get("nc.ui.uif2.I18nFB#4ecf3a") != null)
			return (java.lang.String) context.get("nc.ui.uif2.I18nFB#4ecf3a");
		nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
		context.put("&nc.ui.uif2.I18nFB#4ecf3a", bean);
		bean.setResDir("4006011_0");
		bean.setResId("04006011-0400");
		bean.setDefaultValue("辅助功能");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		try {
			Object product = bean.getObject();
			context.put("nc.ui.uif2.I18nFB#4ecf3a", product);
			return (java.lang.String) product;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private List getManagedList39() {
		List list = new ArrayList();
		list.add(getToReviseM30Action());
		list.add(getFreezeAction());
		list.add(getUnFreezeAction());
		list.add(getSeparatorAction());
		list.add(getCloseAction());
		list.add(getQueryReverseAction());
		list.add(getReverseAction());
		list.add(getTransInfoAction());
		list.add(getPushTo21Action());
		list.add(getRefCoop21Action());
		list.add(getReturnRef30Action());
		list.add(getReturnRef4CAction());
		list.add(getSeparatorAction());
		list.add(getPriceFormAction());
		list.add(getDocManageAction());
		return list;
	}

	public nc.ui.scmpub.listener.crossrule.CrossRuleMediator getCrossRuleMediator() {
		if (context.get("crossRuleMediator") != null)
			return (nc.ui.scmpub.listener.crossrule.CrossRuleMediator) context
					.get("crossRuleMediator");
		nc.ui.scmpub.listener.crossrule.CrossRuleMediator bean = new nc.ui.scmpub.listener.crossrule.CrossRuleMediator();
		context.put("crossRuleMediator", bean);
		bean.setModel(getManageAppModel());
		bean.setBillType("30");
		bean.init();
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.so.m30.billui.action.assist.XsddAction getXsddAction() {
		if (context.get("xsddAction") != null)
			return (nc.ui.so.m30.billui.action.assist.XsddAction) context
					.get("xsddAction");
		nc.ui.so.m30.billui.action.assist.XsddAction bean = new nc.ui.so.m30.billui.action.assist.XsddAction();
		context.put("xsddAction", bean);
		bean.setModel(getManageAppModel());
		bean.setEditor(getBillFormEditor());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.so.m30.billui.action.SendToGf getSend2gf() {
		if (context.get("send2gf") != null)
			return (nc.ui.so.m30.billui.action.SendToGf) context.get("send2gf");
		nc.ui.so.m30.billui.action.SendToGf bean = new nc.ui.so.m30.billui.action.SendToGf();
		context.put("send2gf", bean);
		bean.setModel(getManageAppModel());
		bean.setEditor(getBillFormEditor());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.so.m30.billrefFor28.Add28Action getAdd28Action() {
		if (context.get("Add28Action") != null)
			return (nc.ui.so.m30.billrefFor28.Add28Action) context
					.get("Add28Action");
		nc.ui.so.m30.billrefFor28.Add28Action bean = new nc.ui.so.m30.billrefFor28.Add28Action();
		context.put("Add28Action", bean);
		bean.setSourceBillType("28");
		bean.setSourceBillName("参照价格审批单");
		bean.setFlowBillType(false);
		bean.setModel(getManageAppModel());
		bean.setEditor(getBillFormEditor());
		bean.setTransferViewProcessor(getTransferProcessorforTR10());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.billref.dest.TransferViewProcessor getTransferProcessorforTR10() {
		if (context.get("transferProcessorforTR10") != null)
			return (nc.ui.pubapp.billref.dest.TransferViewProcessor) context
					.get("transferProcessorforTR10");
		nc.ui.pubapp.billref.dest.TransferViewProcessor bean = new nc.ui.pubapp.billref.dest.TransferViewProcessor();
		context.put("transferProcessorforTR10", bean);
		bean.setList(getListView());
		bean.setActionContainer(getContainer());
		bean.setCardActionContainer(getActionsOfCard());
		bean.setTransferLogic(getQG20RefTJ01TRansferBillDataLogic());
		bean.setBillForm(getBillFormEditor());
		bean.setCancelAction(getCancelAction());
		bean.setSaveAction(getSaveAction());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.so.m30.billrefFor28.M30RefM28TRansferBillDataLogic getQG20RefTJ01TRansferBillDataLogic() {
		if (context.get("QG20RefTJ01TRansferBillDataLogic") != null)
			return (nc.ui.so.m30.billrefFor28.M30RefM28TRansferBillDataLogic) context
					.get("QG20RefTJ01TRansferBillDataLogic");
		nc.ui.so.m30.billrefFor28.M30RefM28TRansferBillDataLogic bean = new nc.ui.so.m30.billrefFor28.M30RefM28TRansferBillDataLogic();
		context.put("QG20RefTJ01TRansferBillDataLogic", bean);
		bean.setBillForm(getBillFormEditor());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.so.m30.billrefFor28.Add28LineAction getAdd28LineAction() {
		if (context.get("Add28LineAction") != null)
			return (nc.ui.so.m30.billrefFor28.Add28LineAction) context
					.get("Add28LineAction");
		nc.ui.so.m30.billrefFor28.Add28LineAction bean = new nc.ui.so.m30.billrefFor28.Add28LineAction();
		context.put("Add28LineAction", bean);
		bean.setSourceBillType("28");
		bean.setSourceBillName("参照价格审批单行");
		bean.setFlowBillType(false);
		bean.setModel(getManageAppModel());
		bean.setEditor(getBillFormEditor());
		bean.setTransferViewProcessor(getTransferProcessorforTR10());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.so.m30.billrefForLS41.AddLS41Action getAddLS41Action() {
		if (context.get("AddLS41Action") != null)
			return (nc.ui.so.m30.billrefForLS41.AddLS41Action) context
					.get("AddLS41Action");
		nc.ui.so.m30.billrefForLS41.AddLS41Action bean = new nc.ui.so.m30.billrefForLS41.AddLS41Action();
		context.put("AddLS41Action", bean);
		bean.setSourceBillType("LS41");
		bean.setSourceBillName("参照历史代理协议");
		bean.setFlowBillType(false);
		bean.setModel(getManageAppModel());
		bean.setEditor(getBillFormEditor());
		bean.setTransferViewProcessor(getTransferProcessorforTR10());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.so.m30.billui.action.SaleOrderAddFrom5805Action getAdd5805Action() {
		if (context.get("add5805Action") != null)
			return (nc.ui.so.m30.billui.action.SaleOrderAddFrom5805Action) context
					.get("add5805Action");
		nc.ui.so.m30.billui.action.SaleOrderAddFrom5805Action bean = new nc.ui.so.m30.billui.action.SaleOrderAddFrom5805Action();
		context.put("add5805Action", bean);
		bean.setSourceBillType("5805");
		bean.setSourceBillName("参照进口明细");
		bean.setFlowBillType(false);
		bean.setModel(getManageAppModel());
		bean.setEditor(getBillFormEditor());
		bean.setTransferViewProcessor(getTransferProcessorfor5805());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.billref.dest.TransferViewProcessor getTransferProcessorfor5805() {
		if (context.get("transferProcessorfor5805") != null)
			return (nc.ui.pubapp.billref.dest.TransferViewProcessor) context
					.get("transferProcessorfor5805");
		nc.ui.pubapp.billref.dest.TransferViewProcessor bean = new nc.ui.pubapp.billref.dest.TransferViewProcessor();
		context.put("transferProcessorfor5805", bean);
		bean.setList(getListView());
		bean.setActionContainer(getActionsOfList());
		bean.setCardActionContainer(getActionsOfCard());
		bean.setTransferLogic(getTransferLogicfor5805());
		bean.setBillForm(getBillFormEditor());
		bean.setCancelAction(getCancelAction());
		bean.setSaveAction(getSaveAction());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.so.m30.billui.tranferbill.M30Ref5805TransferBillDataLogic getTransferLogicfor5805() {
		if (context.get("transferLogicfor5805") != null)
			return (nc.ui.so.m30.billui.tranferbill.M30Ref5805TransferBillDataLogic) context
					.get("transferLogicfor5805");
		nc.ui.so.m30.billui.tranferbill.M30Ref5805TransferBillDataLogic bean = new nc.ui.so.m30.billui.tranferbill.M30Ref5805TransferBillDataLogic();
		context.put("transferLogicfor5805", bean);
		bean.setBillForm(getBillFormEditor());
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

	public nc.ui.so.m30.billui.action.ToReviseM30Action getToReviseM30Action() {
		if (context.get("ToReviseM30Action") != null)
			return (nc.ui.so.m30.billui.action.ToReviseM30Action) context
					.get("ToReviseM30Action");
		nc.ui.so.m30.billui.action.ToReviseM30Action bean = new nc.ui.so.m30.billui.action.ToReviseM30Action();
		context.put("ToReviseM30Action", bean);
		bean.setModel(getManageAppModel());
		bean.setEditor(getBillFormEditor());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

}
