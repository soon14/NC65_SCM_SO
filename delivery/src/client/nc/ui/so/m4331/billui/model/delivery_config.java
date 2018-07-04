package nc.ui.so.m4331.billui.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.ui.uif2.factory.AbstractJavaBeanDefinition;

public class delivery_config extends AbstractJavaBeanDefinition{
	private Map<String, Object> context = new HashMap();
public nc.vo.uif2.LoginContext getContext(){
 if(context.get("context")!=null)
 return (nc.vo.uif2.LoginContext)context.get("context");
  nc.vo.uif2.LoginContext bean = new nc.vo.uif2.LoginContext();
  context.put("context",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.so.m4331.billui.model.DeliveryService getManageModelService(){
 if(context.get("ManageModelService")!=null)
 return (nc.ui.so.m4331.billui.model.DeliveryService)context.get("ManageModelService");
  nc.ui.so.m4331.billui.model.DeliveryService bean = new nc.ui.so.m4331.billui.model.DeliveryService();
  context.put("ManageModelService",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.view.value.AggVOMetaBDObjectAdapterFactory getBoadatorfactory(){
 if(context.get("boadatorfactory")!=null)
 return (nc.ui.pubapp.uif2app.view.value.AggVOMetaBDObjectAdapterFactory)context.get("boadatorfactory");
  nc.ui.pubapp.uif2app.view.value.AggVOMetaBDObjectAdapterFactory bean = new nc.ui.pubapp.uif2app.view.value.AggVOMetaBDObjectAdapterFactory();
  context.put("boadatorfactory",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.so.m4331.billui.model.DeliveryManageModel getManageAppModel(){
 if(context.get("ManageAppModel")!=null)
 return (nc.ui.so.m4331.billui.model.DeliveryManageModel)context.get("ManageAppModel");
  nc.ui.so.m4331.billui.model.DeliveryManageModel bean = new nc.ui.so.m4331.billui.model.DeliveryManageModel();
  context.put("ManageAppModel",bean);
  bean.setService(getManageModelService());
  bean.setBillType("4331");
  bean.setBusinessObjectAdapterFactory(getBoadatorfactory());
  bean.setContext(getContext());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.funcnode.ui.action.SeparatorAction getSeparatorAction(){
 if(context.get("separatorAction")!=null)
 return (nc.funcnode.ui.action.SeparatorAction)context.get("separatorAction");
  nc.funcnode.ui.action.SeparatorAction bean = new nc.funcnode.ui.action.SeparatorAction();
  context.put("separatorAction",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pub.beans.ActionsBar.ActionsBarSeparator getLineSeparatorAction(){
 if(context.get("lineSeparatorAction")!=null)
 return (nc.ui.pub.beans.ActionsBar.ActionsBarSeparator)context.get("lineSeparatorAction");
  nc.ui.pub.beans.ActionsBar.ActionsBarSeparator bean = new nc.ui.pub.beans.ActionsBar.ActionsBarSeparator();
  context.put("lineSeparatorAction",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.uif2.components.pagination.PaginationBar getPageBar(){
 if(context.get("pageBar")!=null)
 return (nc.ui.uif2.components.pagination.PaginationBar)context.get("pageBar");
  nc.ui.uif2.components.pagination.PaginationBar bean = new nc.ui.uif2.components.pagination.PaginationBar();
  context.put("pageBar",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.actions.pagination.BillModelPaginationDelegator getPageDelegator(){
 if(context.get("pageDelegator")!=null)
 return (nc.ui.pubapp.uif2app.actions.pagination.BillModelPaginationDelegator)context.get("pageDelegator");
  nc.ui.pubapp.uif2app.actions.pagination.BillModelPaginationDelegator bean = new nc.ui.pubapp.uif2app.actions.pagination.BillModelPaginationDelegator(getManageAppModel());  context.put("pageDelegator",bean);
  bean.setPaginationQuery(getPageQuery());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.so.m4331.billui.model.DeliveryPageService getPageQuery(){
 if(context.get("pageQuery")!=null)
 return (nc.ui.so.m4331.billui.model.DeliveryPageService)context.get("pageQuery");
  nc.ui.so.m4331.billui.model.DeliveryPageService bean = new nc.ui.so.m4331.billui.model.DeliveryPageService();
  context.put("pageQuery",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.scmpub.page.model.SCMBillPageMediator getPageMediator(){
 if(context.get("pageMediator")!=null)
 return (nc.ui.scmpub.page.model.SCMBillPageMediator)context.get("pageMediator");
  nc.ui.scmpub.page.model.SCMBillPageMediator bean = new nc.ui.scmpub.page.model.SCMBillPageMediator();
  context.put("pageMediator",bean);
  bean.setListView(getListView());
  bean.setRecordInPage(10);
  bean.setCachePages(10);
  bean.setPageDelegator(getPageDelegator());
  bean.init();
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.scmpub.page.model.SCMBillPageModelDataManager getModelDataManager(){
 if(context.get("modelDataManager")!=null)
 return (nc.ui.scmpub.page.model.SCMBillPageModelDataManager)context.get("modelDataManager");
  nc.ui.scmpub.page.model.SCMBillPageModelDataManager bean = new nc.ui.scmpub.page.model.SCMBillPageModelDataManager();
  context.put("modelDataManager",bean);
  bean.setModel(getManageAppModel());
  bean.setPageQuery(getPageQuery());
  bean.setPageDelegator(getPageDelegator());
  bean.setPagePanel(getListToolbarPnl());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.funcnode.trantype.TrantypeBillTemplateMender getTrantypeTempMender(){
 if(context.get("trantypeTempMender")!=null)
 return (nc.ui.pubapp.uif2app.funcnode.trantype.TrantypeBillTemplateMender)context.get("trantypeTempMender");
  nc.ui.pubapp.uif2app.funcnode.trantype.TrantypeBillTemplateMender bean = new nc.ui.pubapp.uif2app.funcnode.trantype.TrantypeBillTemplateMender(getContext());  context.put("trantypeTempMender",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.view.TemplateContainer getTemplateContainer(){
 if(context.get("templateContainer")!=null)
 return (nc.ui.pubapp.uif2app.view.TemplateContainer)context.get("templateContainer");
  nc.ui.pubapp.uif2app.view.TemplateContainer bean = new nc.ui.pubapp.uif2app.view.TemplateContainer();
  context.put("templateContainer",bean);
  bean.setContext(getContext());
  bean.setBillTemplateMender(getTrantypeTempMender());
  bean.load();
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.uif2.editor.QueryTemplateContainer getQueryTemplateContainer(){
 if(context.get("queryTemplateContainer")!=null)
 return (nc.ui.uif2.editor.QueryTemplateContainer)context.get("queryTemplateContainer");
  nc.ui.uif2.editor.QueryTemplateContainer bean = new nc.ui.uif2.editor.QueryTemplateContainer();
  context.put("queryTemplateContainer",bean);
  bean.setContext(getContext());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.uif2.editor.UIF2RemoteCallCombinatorCaller getRemoteCallCombinatorCaller(){
 if(context.get("remoteCallCombinatorCaller")!=null)
 return (nc.ui.uif2.editor.UIF2RemoteCallCombinatorCaller)context.get("remoteCallCombinatorCaller");
  nc.ui.uif2.editor.UIF2RemoteCallCombinatorCaller bean = new nc.ui.uif2.editor.UIF2RemoteCallCombinatorCaller();
  context.put("remoteCallCombinatorCaller",bean);
  bean.setRemoteCallers(getManagedList0());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList0(){  List list = new ArrayList();  list.add(getQueryTemplateContainer());  list.add(getTemplateContainer());  list.add(getUserdefitemContainer());  list.add(getPfAddInfoLoader());  return list;}

public nc.ui.pubapp.uif2app.actions.PfAddInfoLoader getPfAddInfoLoader(){
 if(context.get("pfAddInfoLoader")!=null)
 return (nc.ui.pubapp.uif2app.actions.PfAddInfoLoader)context.get("pfAddInfoLoader");
  nc.ui.pubapp.uif2app.actions.PfAddInfoLoader bean = new nc.ui.pubapp.uif2app.actions.PfAddInfoLoader();
  context.put("pfAddInfoLoader",bean);
  bean.setBillType("4331");
  bean.setModel(getManageAppModel());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.view.RowNoMediator getRownoMediator(){
 if(context.get("rownoMediator")!=null)
 return (nc.ui.pubapp.uif2app.view.RowNoMediator)context.get("rownoMediator");
  nc.ui.pubapp.uif2app.view.RowNoMediator bean = new nc.ui.pubapp.uif2app.view.RowNoMediator();
  context.put("rownoMediator",bean);
  bean.setModel(getManageAppModel());
  bean.setEditor(getBillFormEditor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.so.m4331.billui.view.DeliveryListView getListView(){
 if(context.get("listView")!=null)
 return (nc.ui.so.m4331.billui.view.DeliveryListView)context.get("listView");
  nc.ui.so.m4331.billui.view.DeliveryListView bean = new nc.ui.so.m4331.billui.view.DeliveryListView();
  context.put("listView",bean);
  bean.setModel(getManageAppModel());
  bean.setMultiSelectionEnable(true);
  bean.setPaginationBar(getPageBar());
  bean.setUserdefitemListPreparator(getUserdefAndMarAsstListPreparator());
  bean.setTemplateContainer(getTemplateContainer());
  bean.initUI();
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.view.CompositeBillListDataPrepare getUserdefAndMarAsstListPreparator(){
 if(context.get("userdefAndMarAsstListPreparator")!=null)
 return (nc.ui.pubapp.uif2app.view.CompositeBillListDataPrepare)context.get("userdefAndMarAsstListPreparator");
  nc.ui.pubapp.uif2app.view.CompositeBillListDataPrepare bean = new nc.ui.pubapp.uif2app.view.CompositeBillListDataPrepare();
  context.put("userdefAndMarAsstListPreparator",bean);
  bean.setBillListDataPrepares(getManagedList1());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList1(){  List list = new ArrayList();  list.add(getUserdefitemlistPreparator());  list.add(getMarAsstPreparator());  return list;}

public nc.ui.pubapp.uif2app.linkquery.LinkQueryHyperlinkMediator getAlinkQueryHyperlinkMediator(){
 if(context.get("alinkQueryHyperlinkMediator")!=null)
 return (nc.ui.pubapp.uif2app.linkquery.LinkQueryHyperlinkMediator)context.get("alinkQueryHyperlinkMediator");
  nc.ui.pubapp.uif2app.linkquery.LinkQueryHyperlinkMediator bean = new nc.ui.pubapp.uif2app.linkquery.LinkQueryHyperlinkMediator();
  context.put("alinkQueryHyperlinkMediator",bean);
  bean.setModel(getManageAppModel());
  bean.setSrcBillIdField("csrcid");
  bean.setSrcBillNOField("vsrccode");
  bean.setSrcBillTypeField("vsrctype");
  bean.setSrcBillTypeFieldPos(1);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.linkquery.LinkQueryHyperlinkMediator getLinkQueryHyperlinkMediator(){
 if(context.get("linkQueryHyperlinkMediator")!=null)
 return (nc.ui.pubapp.uif2app.linkquery.LinkQueryHyperlinkMediator)context.get("linkQueryHyperlinkMediator");
  nc.ui.pubapp.uif2app.linkquery.LinkQueryHyperlinkMediator bean = new nc.ui.pubapp.uif2app.linkquery.LinkQueryHyperlinkMediator();
  context.put("linkQueryHyperlinkMediator",bean);
  bean.setModel(getManageAppModel());
  bean.setSrcBillIdField("cfirstid");
  bean.setSrcBillNOField("vfirstcode");
  bean.setSrcBillTypeField("vfirsttype");
  bean.setSrcBillTypeFieldPos(1);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.so.m4331.billui.view.DeliveryEditor getBillFormEditor(){
 if(context.get("billFormEditor")!=null)
 return (nc.ui.so.m4331.billui.view.DeliveryEditor)context.get("billFormEditor");
  nc.ui.so.m4331.billui.view.DeliveryEditor bean = new nc.ui.so.m4331.billui.view.DeliveryEditor();
  context.put("billFormEditor",bean);
  bean.setModel(getManageAppModel());
  bean.setTemplateContainer(getTemplateContainer());
  bean.setTemplateNotNullValidate(true);
  bean.setShowTotalLine(true);
  bean.setAutoAddLine(false);
  bean.setBlankChildrenFilter(getMultiFieldsBlankChildrenFilter_1cece90());
  bean.setUserdefitemPreparator(getUserdefAndMarAsstCardPreparator());
  bean.setBodyLineActions(getManagedList3());
  bean.initUI();
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.pubapp.uif2app.view.value.MultiFieldsBlankChildrenFilter getMultiFieldsBlankChildrenFilter_1cece90(){
 if(context.get("nc.ui.pubapp.uif2app.view.value.MultiFieldsBlankChildrenFilter#1cece90")!=null)
 return (nc.ui.pubapp.uif2app.view.value.MultiFieldsBlankChildrenFilter)context.get("nc.ui.pubapp.uif2app.view.value.MultiFieldsBlankChildrenFilter#1cece90");
  nc.ui.pubapp.uif2app.view.value.MultiFieldsBlankChildrenFilter bean = new nc.ui.pubapp.uif2app.view.value.MultiFieldsBlankChildrenFilter();
  context.put("nc.ui.pubapp.uif2app.view.value.MultiFieldsBlankChildrenFilter#1cece90",bean);
  bean.setFilterMap(getManagedMap0());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private Map getManagedMap0(){  Map map = new HashMap();  map.put("cdeliverybid",getManagedList2());  return map;}

private List getManagedList2(){  List list = new ArrayList();  list.add("cmaterialvid");  return list;}

private List getManagedList3(){  List list = new ArrayList();  list.add(getDeliveryDelLineAction_5b7bf2());  list.add(getBodyCopyLineAction_268ca6());  list.add(getBodyPasteLineAction_dc1487());  list.add(getBodyPasteToTailAction_c7b7d1());  list.add(getLineSeparatorAction());  list.add(getRearrangeRowNoBodyLineAction_1f5070c());  list.add(getLineSeparatorAction());  list.add(getDefaultBodyZoomAction_1a1d60b());  return list;}

private nc.ui.so.m4331.billui.action.lineaction.DeliveryDelLineAction getDeliveryDelLineAction_5b7bf2(){
 if(context.get("nc.ui.so.m4331.billui.action.lineaction.DeliveryDelLineAction#5b7bf2")!=null)
 return (nc.ui.so.m4331.billui.action.lineaction.DeliveryDelLineAction)context.get("nc.ui.so.m4331.billui.action.lineaction.DeliveryDelLineAction#5b7bf2");
  nc.ui.so.m4331.billui.action.lineaction.DeliveryDelLineAction bean = new nc.ui.so.m4331.billui.action.lineaction.DeliveryDelLineAction();
  context.put("nc.ui.so.m4331.billui.action.lineaction.DeliveryDelLineAction#5b7bf2",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.pubapp.uif2app.actions.BodyCopyLineAction getBodyCopyLineAction_268ca6(){
 if(context.get("nc.ui.pubapp.uif2app.actions.BodyCopyLineAction#268ca6")!=null)
 return (nc.ui.pubapp.uif2app.actions.BodyCopyLineAction)context.get("nc.ui.pubapp.uif2app.actions.BodyCopyLineAction#268ca6");
  nc.ui.pubapp.uif2app.actions.BodyCopyLineAction bean = new nc.ui.pubapp.uif2app.actions.BodyCopyLineAction();
  context.put("nc.ui.pubapp.uif2app.actions.BodyCopyLineAction#268ca6",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.pubapp.uif2app.actions.BodyPasteLineAction getBodyPasteLineAction_dc1487(){
 if(context.get("nc.ui.pubapp.uif2app.actions.BodyPasteLineAction#dc1487")!=null)
 return (nc.ui.pubapp.uif2app.actions.BodyPasteLineAction)context.get("nc.ui.pubapp.uif2app.actions.BodyPasteLineAction#dc1487");
  nc.ui.pubapp.uif2app.actions.BodyPasteLineAction bean = new nc.ui.pubapp.uif2app.actions.BodyPasteLineAction();
  context.put("nc.ui.pubapp.uif2app.actions.BodyPasteLineAction#dc1487",bean);
  bean.setClearItems(getManagedList4());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList4(){  List list = new ArrayList();  list.add("cdeliverybid");  list.add("cdeliverybbid");  list.add("nreqrsnum");  list.add("ts");  return list;}

private nc.ui.pubapp.uif2app.actions.BodyPasteToTailAction getBodyPasteToTailAction_c7b7d1(){
 if(context.get("nc.ui.pubapp.uif2app.actions.BodyPasteToTailAction#c7b7d1")!=null)
 return (nc.ui.pubapp.uif2app.actions.BodyPasteToTailAction)context.get("nc.ui.pubapp.uif2app.actions.BodyPasteToTailAction#c7b7d1");
  nc.ui.pubapp.uif2app.actions.BodyPasteToTailAction bean = new nc.ui.pubapp.uif2app.actions.BodyPasteToTailAction();
  context.put("nc.ui.pubapp.uif2app.actions.BodyPasteToTailAction#c7b7d1",bean);
  bean.setClearItems(getManagedList5());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList5(){  List list = new ArrayList();  list.add("cdeliverybid");  list.add("cdeliverybbid");  list.add("nreqrsnum");  list.add("ts");  return list;}

private nc.ui.pubapp.uif2app.actions.RearrangeRowNoBodyLineAction getRearrangeRowNoBodyLineAction_1f5070c(){
 if(context.get("nc.ui.pubapp.uif2app.actions.RearrangeRowNoBodyLineAction#1f5070c")!=null)
 return (nc.ui.pubapp.uif2app.actions.RearrangeRowNoBodyLineAction)context.get("nc.ui.pubapp.uif2app.actions.RearrangeRowNoBodyLineAction#1f5070c");
  nc.ui.pubapp.uif2app.actions.RearrangeRowNoBodyLineAction bean = new nc.ui.pubapp.uif2app.actions.RearrangeRowNoBodyLineAction();
  context.put("nc.ui.pubapp.uif2app.actions.RearrangeRowNoBodyLineAction#1f5070c",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.pubapp.uif2app.actions.DefaultBodyZoomAction getDefaultBodyZoomAction_1a1d60b(){
 if(context.get("nc.ui.pubapp.uif2app.actions.DefaultBodyZoomAction#1a1d60b")!=null)
 return (nc.ui.pubapp.uif2app.actions.DefaultBodyZoomAction)context.get("nc.ui.pubapp.uif2app.actions.DefaultBodyZoomAction#1a1d60b");
  nc.ui.pubapp.uif2app.actions.DefaultBodyZoomAction bean = new nc.ui.pubapp.uif2app.actions.DefaultBodyZoomAction();
  context.put("nc.ui.pubapp.uif2app.actions.DefaultBodyZoomAction#1a1d60b",bean);
  bean.setPos(1);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.view.CompositeBillDataPrepare getUserdefAndMarAsstCardPreparator(){
 if(context.get("userdefAndMarAsstCardPreparator")!=null)
 return (nc.ui.pubapp.uif2app.view.CompositeBillDataPrepare)context.get("userdefAndMarAsstCardPreparator");
  nc.ui.pubapp.uif2app.view.CompositeBillDataPrepare bean = new nc.ui.pubapp.uif2app.view.CompositeBillDataPrepare();
  context.put("userdefAndMarAsstCardPreparator",bean);
  bean.setBillDataPrepares(getManagedList6());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList6(){  List list = new ArrayList();  list.add(getUserdefitemPreparator());  list.add(getMarAsstPreparator());  return list;}

public nc.ui.uif2.editor.UserdefQueryParam getUserQueryParams1(){
 if(context.get("userQueryParams1")!=null)
 return (nc.ui.uif2.editor.UserdefQueryParam)context.get("userQueryParams1");
  nc.ui.uif2.editor.UserdefQueryParam bean = new nc.ui.uif2.editor.UserdefQueryParam();
  context.put("userQueryParams1",bean);
  bean.setMdfullname(" ic.OnhandDimVO");
  bean.setPos(0);
  bean.setPrefix("vdef");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.uif2.userdefitem.QueryParam getQueryParams1(){
 if(context.get("queryParams1")!=null)
 return (nc.ui.uif2.userdefitem.QueryParam)context.get("queryParams1");
  nc.ui.uif2.userdefitem.QueryParam bean = new nc.ui.uif2.userdefitem.QueryParam();
  context.put("queryParams1",bean);
  bean.setMdfullname("ic.OnhandDimVO");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.uif2.editor.UserdefitemContainerPreparator getUserdefitemPreparator(){
 if(context.get("userdefitemPreparator")!=null)
 return (nc.ui.uif2.editor.UserdefitemContainerPreparator)context.get("userdefitemPreparator");
  nc.ui.uif2.editor.UserdefitemContainerPreparator bean = new nc.ui.uif2.editor.UserdefitemContainerPreparator();
  context.put("userdefitemPreparator",bean);
  bean.setContainer(getUserdefitemContainer());
  bean.setParams(getManagedList7());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList7(){  List list = new ArrayList();  list.add(getUserQueryParams1());  list.add(getUserdefQueryParam_1eb9505());  list.add(getUserdefQueryParam_1848059());  return list;}

private nc.ui.uif2.editor.UserdefQueryParam getUserdefQueryParam_1eb9505(){
 if(context.get("nc.ui.uif2.editor.UserdefQueryParam#1eb9505")!=null)
 return (nc.ui.uif2.editor.UserdefQueryParam)context.get("nc.ui.uif2.editor.UserdefQueryParam#1eb9505");
  nc.ui.uif2.editor.UserdefQueryParam bean = new nc.ui.uif2.editor.UserdefQueryParam();
  context.put("nc.ui.uif2.editor.UserdefQueryParam#1eb9505",bean);
  bean.setMdfullname("so.delivery");
  bean.setPos(0);
  bean.setPrefix("vdef");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.editor.UserdefQueryParam getUserdefQueryParam_1848059(){
 if(context.get("nc.ui.uif2.editor.UserdefQueryParam#1848059")!=null)
 return (nc.ui.uif2.editor.UserdefQueryParam)context.get("nc.ui.uif2.editor.UserdefQueryParam#1848059");
  nc.ui.uif2.editor.UserdefQueryParam bean = new nc.ui.uif2.editor.UserdefQueryParam();
  context.put("nc.ui.uif2.editor.UserdefQueryParam#1848059",bean);
  bean.setMdfullname("so.delivery_b");
  bean.setPos(1);
  bean.setPrefix("vbdef");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.view.material.assistant.MarAsstPreparator getMarAsstPreparator(){
 if(context.get("marAsstPreparator")!=null)
 return (nc.ui.pubapp.uif2app.view.material.assistant.MarAsstPreparator)context.get("marAsstPreparator");
  nc.ui.pubapp.uif2app.view.material.assistant.MarAsstPreparator bean = new nc.ui.pubapp.uif2app.view.material.assistant.MarAsstPreparator();
  context.put("marAsstPreparator",bean);
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

public nc.ui.uif2.userdefitem.UserDefItemContainer getUserdefitemContainer(){
 if(context.get("userdefitemContainer")!=null)
 return (nc.ui.uif2.userdefitem.UserDefItemContainer)context.get("userdefitemContainer");
  nc.ui.uif2.userdefitem.UserDefItemContainer bean = new nc.ui.uif2.userdefitem.UserDefItemContainer();
  context.put("userdefitemContainer",bean);
  bean.setContext(getContext());
  bean.setParams(getManagedList8());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList8(){  List list = new ArrayList();  list.add(getQueryParams1());  list.add(getQueryParam_678c4());  list.add(getQueryParam_e29935());  list.add(getQueryParam_b8766a());  return list;}

private nc.ui.uif2.userdefitem.QueryParam getQueryParam_678c4(){
 if(context.get("nc.ui.uif2.userdefitem.QueryParam#678c4")!=null)
 return (nc.ui.uif2.userdefitem.QueryParam)context.get("nc.ui.uif2.userdefitem.QueryParam#678c4");
  nc.ui.uif2.userdefitem.QueryParam bean = new nc.ui.uif2.userdefitem.QueryParam();
  context.put("nc.ui.uif2.userdefitem.QueryParam#678c4",bean);
  bean.setMdfullname("so.delivery");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.userdefitem.QueryParam getQueryParam_e29935(){
 if(context.get("nc.ui.uif2.userdefitem.QueryParam#e29935")!=null)
 return (nc.ui.uif2.userdefitem.QueryParam)context.get("nc.ui.uif2.userdefitem.QueryParam#e29935");
  nc.ui.uif2.userdefitem.QueryParam bean = new nc.ui.uif2.userdefitem.QueryParam();
  context.put("nc.ui.uif2.userdefitem.QueryParam#e29935",bean);
  bean.setMdfullname("so.delivery_b");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.userdefitem.QueryParam getQueryParam_b8766a(){
 if(context.get("nc.ui.uif2.userdefitem.QueryParam#b8766a")!=null)
 return (nc.ui.uif2.userdefitem.QueryParam)context.get("nc.ui.uif2.userdefitem.QueryParam#b8766a");
  nc.ui.uif2.userdefitem.QueryParam bean = new nc.ui.uif2.userdefitem.QueryParam();
  context.put("nc.ui.uif2.userdefitem.QueryParam#b8766a",bean);
  bean.setRulecode("materialassistant");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public java.lang.String getSelfDef(){
 if(context.get("selfDef")!=null)
 return (java.lang.String)context.get("selfDef");
  java.lang.String bean = new java.lang.String("vbdef");  context.put("selfDef",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.uif2.editor.UserdefitemContainerListPreparator getUserdefitemlistPreparator(){
 if(context.get("userdefitemlistPreparator")!=null)
 return (nc.ui.uif2.editor.UserdefitemContainerListPreparator)context.get("userdefitemlistPreparator");
  nc.ui.uif2.editor.UserdefitemContainerListPreparator bean = new nc.ui.uif2.editor.UserdefitemContainerListPreparator();
  context.put("userdefitemlistPreparator",bean);
  bean.setContainer(getUserdefitemContainer());
  bean.setParams(getManagedList9());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList9(){  List list = new ArrayList();  list.add(getUserdefQueryParam_66411c());  list.add(getUserdefQueryParam_13cbe80());  return list;}

private nc.ui.uif2.editor.UserdefQueryParam getUserdefQueryParam_66411c(){
 if(context.get("nc.ui.uif2.editor.UserdefQueryParam#66411c")!=null)
 return (nc.ui.uif2.editor.UserdefQueryParam)context.get("nc.ui.uif2.editor.UserdefQueryParam#66411c");
  nc.ui.uif2.editor.UserdefQueryParam bean = new nc.ui.uif2.editor.UserdefQueryParam();
  context.put("nc.ui.uif2.editor.UserdefQueryParam#66411c",bean);
  bean.setMdfullname("so.delivery");
  bean.setPos(0);
  bean.setPrefix("vdef");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.editor.UserdefQueryParam getUserdefQueryParam_13cbe80(){
 if(context.get("nc.ui.uif2.editor.UserdefQueryParam#13cbe80")!=null)
 return (nc.ui.uif2.editor.UserdefQueryParam)context.get("nc.ui.uif2.editor.UserdefQueryParam#13cbe80");
  nc.ui.uif2.editor.UserdefQueryParam bean = new nc.ui.uif2.editor.UserdefQueryParam();
  context.put("nc.ui.uif2.editor.UserdefQueryParam#13cbe80",bean);
  bean.setMdfullname("so.delivery_b");
  bean.setTabcode("cdeliverybid");
  bean.setPos(1);
  bean.setPrefix("vbdef");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.lazilyload.DefaultBillLazilyLoader getBillLazilyLoader(){
 if(context.get("billLazilyLoader")!=null)
 return (nc.ui.pubapp.uif2app.lazilyload.DefaultBillLazilyLoader)context.get("billLazilyLoader");
  nc.ui.pubapp.uif2app.lazilyload.DefaultBillLazilyLoader bean = new nc.ui.pubapp.uif2app.lazilyload.DefaultBillLazilyLoader();
  context.put("billLazilyLoader",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.lazilyload.LazilyLoadManager getLasilyLodadMediator(){
 if(context.get("LasilyLodadMediator")!=null)
 return (nc.ui.pubapp.uif2app.lazilyload.LazilyLoadManager)context.get("LasilyLodadMediator");
  nc.ui.pubapp.uif2app.lazilyload.LazilyLoadManager bean = new nc.ui.pubapp.uif2app.lazilyload.LazilyLoadManager();
  context.put("LasilyLodadMediator",bean);
  bean.setModel(getManageAppModel());
  bean.setLoader(getBillLazilyLoader());
  bean.setLazilyLoadSupporter(getManagedList10());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList10(){  List list = new ArrayList();  list.add(getCardPanelLazilyLoad_fefb7a());  list.add(getListPanelLazilyLoad_301054());  list.add(getActionLazilyLoad_1800c55());  return list;}

private nc.ui.pubapp.uif2app.lazilyload.CardPanelLazilyLoad getCardPanelLazilyLoad_fefb7a(){
 if(context.get("nc.ui.pubapp.uif2app.lazilyload.CardPanelLazilyLoad#fefb7a")!=null)
 return (nc.ui.pubapp.uif2app.lazilyload.CardPanelLazilyLoad)context.get("nc.ui.pubapp.uif2app.lazilyload.CardPanelLazilyLoad#fefb7a");
  nc.ui.pubapp.uif2app.lazilyload.CardPanelLazilyLoad bean = new nc.ui.pubapp.uif2app.lazilyload.CardPanelLazilyLoad();
  context.put("nc.ui.pubapp.uif2app.lazilyload.CardPanelLazilyLoad#fefb7a",bean);
  bean.setBillform(getBillFormEditor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.pubapp.uif2app.lazilyload.ListPanelLazilyLoad getListPanelLazilyLoad_301054(){
 if(context.get("nc.ui.pubapp.uif2app.lazilyload.ListPanelLazilyLoad#301054")!=null)
 return (nc.ui.pubapp.uif2app.lazilyload.ListPanelLazilyLoad)context.get("nc.ui.pubapp.uif2app.lazilyload.ListPanelLazilyLoad#301054");
  nc.ui.pubapp.uif2app.lazilyload.ListPanelLazilyLoad bean = new nc.ui.pubapp.uif2app.lazilyload.ListPanelLazilyLoad();
  context.put("nc.ui.pubapp.uif2app.lazilyload.ListPanelLazilyLoad#301054",bean);
  bean.setListView(getListView());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.pubapp.uif2app.lazilyload.ActionLazilyLoad getActionLazilyLoad_1800c55(){
 if(context.get("nc.ui.pubapp.uif2app.lazilyload.ActionLazilyLoad#1800c55")!=null)
 return (nc.ui.pubapp.uif2app.lazilyload.ActionLazilyLoad)context.get("nc.ui.pubapp.uif2app.lazilyload.ActionLazilyLoad#1800c55");
  nc.ui.pubapp.uif2app.lazilyload.ActionLazilyLoad bean = new nc.ui.pubapp.uif2app.lazilyload.ActionLazilyLoad();
  context.put("nc.ui.pubapp.uif2app.lazilyload.ActionLazilyLoad#1800c55",bean);
  bean.setModel(getManageAppModel());
  bean.setActionList(getManagedList11());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList11(){  List list = new ArrayList();  list.add(getPrintAction());  list.add(getPreviewAction());  list.add(getOutputAction());  list.add(getSplitPrintAction());  list.add(getReverseQueryAction());  return list;}

public nc.ui.pubapp.uif2app.view.FractionFixMediator getFractionFixMediator(){
 if(context.get("fractionFixMediator")!=null)
 return (nc.ui.pubapp.uif2app.view.FractionFixMediator)context.get("fractionFixMediator");
  nc.ui.pubapp.uif2app.view.FractionFixMediator bean = new nc.ui.pubapp.uif2app.view.FractionFixMediator(getManagedList12(),getManagedList13());  context.put("fractionFixMediator",bean);
  bean.initUI();
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList12(){  List list = new ArrayList();  list.add(getBillFormEditor());  return list;}

private List getManagedList13(){  List list = new ArrayList();  list.add(getListView());  return list;}

public nc.ui.pubapp.uif2app.model.BillBodySortMediator getBillBodySortMediator(){
 if(context.get("billBodySortMediator")!=null)
 return (nc.ui.pubapp.uif2app.model.BillBodySortMediator)context.get("billBodySortMediator");
  nc.ui.pubapp.uif2app.model.BillBodySortMediator bean = new nc.ui.pubapp.uif2app.model.BillBodySortMediator(getManageAppModel(),getBillFormEditor(),getListView());  context.put("billBodySortMediator",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.view.MouseClickShowPanelMediator getMouseclickshowpanelMediator(){
 if(context.get("mouseclickshowpanelMediator")!=null)
 return (nc.ui.pubapp.uif2app.view.MouseClickShowPanelMediator)context.get("mouseclickshowpanelMediator");
  nc.ui.pubapp.uif2app.view.MouseClickShowPanelMediator bean = new nc.ui.pubapp.uif2app.view.MouseClickShowPanelMediator();
  context.put("mouseclickshowpanelMediator",bean);
  bean.setListView(getListView());
  bean.setShowUpComponent(getBillFormEditor());
  bean.setHyperLinkColumn("vbillcode");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.view.CardPanelOrgSetterForAllRefMediator getCardpanelorgsetterforallrefMediator(){
 if(context.get("cardpanelorgsetterforallrefMediator")!=null)
 return (nc.ui.pubapp.uif2app.view.CardPanelOrgSetterForAllRefMediator)context.get("cardpanelorgsetterforallrefMediator");
  nc.ui.pubapp.uif2app.view.CardPanelOrgSetterForAllRefMediator bean = new nc.ui.pubapp.uif2app.view.CardPanelOrgSetterForAllRefMediator(getBillFormEditor());  context.put("cardpanelorgsetterforallrefMediator",bean);
  bean.setModel(getManageAppModel());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.model.AppEventHandlerMediator getAppeventhandlerMediator(){
 if(context.get("appeventhandlerMediator")!=null)
 return (nc.ui.pubapp.uif2app.model.AppEventHandlerMediator)context.get("appeventhandlerMediator");
  nc.ui.pubapp.uif2app.model.AppEventHandlerMediator bean = new nc.ui.pubapp.uif2app.model.AppEventHandlerMediator();
  context.put("appeventhandlerMediator",bean);
  bean.setModel(getManageAppModel());
  bean.setHandlerMap(getManagedMap1());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private Map getManagedMap1(){  Map map = new HashMap();  map.put("nc.ui.pubapp.uif2app.event.card.CardHeadTailBeforeEditEvent",getManagedList14());  map.put("nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent",getManagedList15());  map.put("nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent",getManagedList16());  map.put("nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent",getManagedList17());  map.put("nc.ui.pubapp.uif2app.event.OrgChangedEvent",getManagedList18());  return map;}

private List getManagedList14(){  List list = new ArrayList();  list.add(getHeadBeforeEditHandler_1a9a137());  return list;}

private nc.ui.so.m4331.billui.editor.headevent.HeadBeforeEditHandler getHeadBeforeEditHandler_1a9a137(){
 if(context.get("nc.ui.so.m4331.billui.editor.headevent.HeadBeforeEditHandler#1a9a137")!=null)
 return (nc.ui.so.m4331.billui.editor.headevent.HeadBeforeEditHandler)context.get("nc.ui.so.m4331.billui.editor.headevent.HeadBeforeEditHandler#1a9a137");
  nc.ui.so.m4331.billui.editor.headevent.HeadBeforeEditHandler bean = new nc.ui.so.m4331.billui.editor.headevent.HeadBeforeEditHandler();
  context.put("nc.ui.so.m4331.billui.editor.headevent.HeadBeforeEditHandler#1a9a137",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList15(){  List list = new ArrayList();  list.add(getHeadAfterEditHandler_1976255());  return list;}

private nc.ui.so.m4331.billui.editor.headevent.HeadAfterEditHandler getHeadAfterEditHandler_1976255(){
 if(context.get("nc.ui.so.m4331.billui.editor.headevent.HeadAfterEditHandler#1976255")!=null)
 return (nc.ui.so.m4331.billui.editor.headevent.HeadAfterEditHandler)context.get("nc.ui.so.m4331.billui.editor.headevent.HeadAfterEditHandler#1976255");
  nc.ui.so.m4331.billui.editor.headevent.HeadAfterEditHandler bean = new nc.ui.so.m4331.billui.editor.headevent.HeadAfterEditHandler();
  context.put("nc.ui.so.m4331.billui.editor.headevent.HeadAfterEditHandler#1976255",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList16(){  List list = new ArrayList();  list.add(getBodyBeforeEditHandler_1b8c064());  return list;}

private nc.ui.so.m4331.billui.editor.bodyevent.BodyBeforeEditHandler getBodyBeforeEditHandler_1b8c064(){
 if(context.get("nc.ui.so.m4331.billui.editor.bodyevent.BodyBeforeEditHandler#1b8c064")!=null)
 return (nc.ui.so.m4331.billui.editor.bodyevent.BodyBeforeEditHandler)context.get("nc.ui.so.m4331.billui.editor.bodyevent.BodyBeforeEditHandler#1b8c064");
  nc.ui.so.m4331.billui.editor.bodyevent.BodyBeforeEditHandler bean = new nc.ui.so.m4331.billui.editor.bodyevent.BodyBeforeEditHandler();
  context.put("nc.ui.so.m4331.billui.editor.bodyevent.BodyBeforeEditHandler#1b8c064",bean);
  bean.setEditor(getBillFormEditor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList17(){  List list = new ArrayList();  list.add(getBodyAfterEditHandler_5f26be());  return list;}

private nc.ui.so.m4331.billui.editor.bodyevent.BodyAfterEditHandler getBodyAfterEditHandler_5f26be(){
 if(context.get("nc.ui.so.m4331.billui.editor.bodyevent.BodyAfterEditHandler#5f26be")!=null)
 return (nc.ui.so.m4331.billui.editor.bodyevent.BodyAfterEditHandler)context.get("nc.ui.so.m4331.billui.editor.bodyevent.BodyAfterEditHandler#5f26be");
  nc.ui.so.m4331.billui.editor.bodyevent.BodyAfterEditHandler bean = new nc.ui.so.m4331.billui.editor.bodyevent.BodyAfterEditHandler();
  context.put("nc.ui.so.m4331.billui.editor.bodyevent.BodyAfterEditHandler#5f26be",bean);
  bean.setEditor(getBillFormEditor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList18(){  List list = new ArrayList();  list.add(getOrgEditHandler_e8a1e2());  return list;}

private nc.ui.so.m4331.billui.editor.orgevent.OrgEditHandler getOrgEditHandler_e8a1e2(){
 if(context.get("nc.ui.so.m4331.billui.editor.orgevent.OrgEditHandler#e8a1e2")!=null)
 return (nc.ui.so.m4331.billui.editor.orgevent.OrgEditHandler)context.get("nc.ui.so.m4331.billui.editor.orgevent.OrgEditHandler#e8a1e2");
  nc.ui.so.m4331.billui.editor.orgevent.OrgEditHandler bean = new nc.ui.so.m4331.billui.editor.orgevent.OrgEditHandler(getBillFormEditor(),getContext());  context.put("nc.ui.so.m4331.billui.editor.orgevent.OrgEditHandler#e8a1e2",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.uif2.TangramContainer getContainer(){
 if(context.get("container")!=null)
 return (nc.ui.uif2.TangramContainer)context.get("container");
  nc.ui.uif2.TangramContainer bean = new nc.ui.uif2.TangramContainer();
  context.put("container",bean);
  bean.setModel(getManageAppModel());
  bean.setTangramLayoutRoot(getTBNode_1061cc1());
  bean.initUI();
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.tangramlayout.node.TBNode getTBNode_1061cc1(){
 if(context.get("nc.ui.uif2.tangramlayout.node.TBNode#1061cc1")!=null)
 return (nc.ui.uif2.tangramlayout.node.TBNode)context.get("nc.ui.uif2.tangramlayout.node.TBNode#1061cc1");
  nc.ui.uif2.tangramlayout.node.TBNode bean = new nc.ui.uif2.tangramlayout.node.TBNode();
  context.put("nc.ui.uif2.tangramlayout.node.TBNode#1061cc1",bean);
  bean.setShowMode("CardLayout");
  bean.setTabs(getManagedList19());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList19(){  List list = new ArrayList();  list.add(getHSNode_3b12fc());  list.add(getVSNode_368b95());  return list;}

private nc.ui.uif2.tangramlayout.node.HSNode getHSNode_3b12fc(){
 if(context.get("nc.ui.uif2.tangramlayout.node.HSNode#3b12fc")!=null)
 return (nc.ui.uif2.tangramlayout.node.HSNode)context.get("nc.ui.uif2.tangramlayout.node.HSNode#3b12fc");
  nc.ui.uif2.tangramlayout.node.HSNode bean = new nc.ui.uif2.tangramlayout.node.HSNode();
  context.put("nc.ui.uif2.tangramlayout.node.HSNode#3b12fc",bean);
  bean.setLeft(getCNode_dc1bab());
  bean.setRight(getVSNode_900124());
  bean.setDividerLocation(0.22f);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.tangramlayout.node.CNode getCNode_dc1bab(){
 if(context.get("nc.ui.uif2.tangramlayout.node.CNode#dc1bab")!=null)
 return (nc.ui.uif2.tangramlayout.node.CNode)context.get("nc.ui.uif2.tangramlayout.node.CNode#dc1bab");
  nc.ui.uif2.tangramlayout.node.CNode bean = new nc.ui.uif2.tangramlayout.node.CNode();
  context.put("nc.ui.uif2.tangramlayout.node.CNode#dc1bab",bean);
  bean.setComponent(getQueryArea());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.tangramlayout.node.VSNode getVSNode_900124(){
 if(context.get("nc.ui.uif2.tangramlayout.node.VSNode#900124")!=null)
 return (nc.ui.uif2.tangramlayout.node.VSNode)context.get("nc.ui.uif2.tangramlayout.node.VSNode#900124");
  nc.ui.uif2.tangramlayout.node.VSNode bean = new nc.ui.uif2.tangramlayout.node.VSNode();
  context.put("nc.ui.uif2.tangramlayout.node.VSNode#900124",bean);
  bean.setUp(getCNode_9c9ef9());
  bean.setDown(getCNode_7ab72d());
  bean.setDividerLocation(25f);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.tangramlayout.node.CNode getCNode_9c9ef9(){
 if(context.get("nc.ui.uif2.tangramlayout.node.CNode#9c9ef9")!=null)
 return (nc.ui.uif2.tangramlayout.node.CNode)context.get("nc.ui.uif2.tangramlayout.node.CNode#9c9ef9");
  nc.ui.uif2.tangramlayout.node.CNode bean = new nc.ui.uif2.tangramlayout.node.CNode();
  context.put("nc.ui.uif2.tangramlayout.node.CNode#9c9ef9",bean);
  bean.setComponent(getListToolbarPnl());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.tangramlayout.node.CNode getCNode_7ab72d(){
 if(context.get("nc.ui.uif2.tangramlayout.node.CNode#7ab72d")!=null)
 return (nc.ui.uif2.tangramlayout.node.CNode)context.get("nc.ui.uif2.tangramlayout.node.CNode#7ab72d");
  nc.ui.uif2.tangramlayout.node.CNode bean = new nc.ui.uif2.tangramlayout.node.CNode();
  context.put("nc.ui.uif2.tangramlayout.node.CNode#7ab72d",bean);
  bean.setName(getI18nFB_88f0d3());
  bean.setComponent(getListView());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private java.lang.String getI18nFB_88f0d3(){
 if(context.get("nc.ui.uif2.I18nFB#88f0d3")!=null)
 return (java.lang.String)context.get("nc.ui.uif2.I18nFB#88f0d3");
  nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
    context.put("&nc.ui.uif2.I18nFB#88f0d3",bean);  bean.setResDir("common");
  bean.setResId("UC001-0000107");
  bean.setDefaultValue("列表");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
 try {
     Object product = bean.getObject();
    context.put("nc.ui.uif2.I18nFB#88f0d3",product);
     return (java.lang.String)product;
}
catch(Exception e) { throw new RuntimeException(e);}}

private nc.ui.uif2.tangramlayout.node.VSNode getVSNode_368b95(){
 if(context.get("nc.ui.uif2.tangramlayout.node.VSNode#368b95")!=null)
 return (nc.ui.uif2.tangramlayout.node.VSNode)context.get("nc.ui.uif2.tangramlayout.node.VSNode#368b95");
  nc.ui.uif2.tangramlayout.node.VSNode bean = new nc.ui.uif2.tangramlayout.node.VSNode();
  context.put("nc.ui.uif2.tangramlayout.node.VSNode#368b95",bean);
  bean.setUp(getCNode_5b7f55());
  bean.setDown(getCNode_180e10a());
  bean.setDividerLocation(30f);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.tangramlayout.node.CNode getCNode_5b7f55(){
 if(context.get("nc.ui.uif2.tangramlayout.node.CNode#5b7f55")!=null)
 return (nc.ui.uif2.tangramlayout.node.CNode)context.get("nc.ui.uif2.tangramlayout.node.CNode#5b7f55");
  nc.ui.uif2.tangramlayout.node.CNode bean = new nc.ui.uif2.tangramlayout.node.CNode();
  context.put("nc.ui.uif2.tangramlayout.node.CNode#5b7f55",bean);
  bean.setComponent(getCardToolbarPnl());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.tangramlayout.node.CNode getCNode_180e10a(){
 if(context.get("nc.ui.uif2.tangramlayout.node.CNode#180e10a")!=null)
 return (nc.ui.uif2.tangramlayout.node.CNode)context.get("nc.ui.uif2.tangramlayout.node.CNode#180e10a");
  nc.ui.uif2.tangramlayout.node.CNode bean = new nc.ui.uif2.tangramlayout.node.CNode();
  context.put("nc.ui.uif2.tangramlayout.node.CNode#180e10a",bean);
  bean.setComponent(getBillFormEditor());
  bean.setName(getI18nFB_104d9f3());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private java.lang.String getI18nFB_104d9f3(){
 if(context.get("nc.ui.uif2.I18nFB#104d9f3")!=null)
 return (java.lang.String)context.get("nc.ui.uif2.I18nFB#104d9f3");
  nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
    context.put("&nc.ui.uif2.I18nFB#104d9f3",bean);  bean.setResDir("common");
  bean.setResId("UC001-0000106");
  bean.setDefaultValue("卡片");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
 try {
     Object product = bean.getObject();
    context.put("nc.ui.uif2.I18nFB#104d9f3",product);
     return (java.lang.String)product;
}
catch(Exception e) { throw new RuntimeException(e);}}

public nc.ui.pubapp.uif2app.tangramlayout.UEQueryAreaShell getQueryArea(){
 if(context.get("queryArea")!=null)
 return (nc.ui.pubapp.uif2app.tangramlayout.UEQueryAreaShell)context.get("queryArea");
  nc.ui.pubapp.uif2app.tangramlayout.UEQueryAreaShell bean = new nc.ui.pubapp.uif2app.tangramlayout.UEQueryAreaShell();
  context.put("queryArea",bean);
  bean.setQueryAreaCreator(getQueryAction());
  bean.initUI();
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.uif2.tangramlayout.CardLayoutToolbarPanel getListToolbarPnl(){
 if(context.get("listToolbarPnl")!=null)
 return (nc.ui.uif2.tangramlayout.CardLayoutToolbarPanel)context.get("listToolbarPnl");
  nc.ui.uif2.tangramlayout.CardLayoutToolbarPanel bean = new nc.ui.uif2.tangramlayout.CardLayoutToolbarPanel();
  context.put("listToolbarPnl",bean);
  bean.setModel(getManageAppModel());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.tangramlayout.UECardLayoutToolbarPanel getCardToolbarPnl(){
 if(context.get("cardToolbarPnl")!=null)
 return (nc.ui.pubapp.uif2app.tangramlayout.UECardLayoutToolbarPanel)context.get("cardToolbarPnl");
  nc.ui.pubapp.uif2app.tangramlayout.UECardLayoutToolbarPanel bean = new nc.ui.pubapp.uif2app.tangramlayout.UECardLayoutToolbarPanel();
  context.put("cardToolbarPnl",bean);
  bean.setTitleAction(getReturnaction());
  bean.setModel(getManageAppModel());
  bean.setRightExActions(getManagedList20());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.pubapp.uif2app.actions.UEReturnAction getReturnaction(){
 if(context.get("returnaction")!=null)
 return (nc.ui.pubapp.uif2app.actions.UEReturnAction)context.get("returnaction");
  nc.ui.pubapp.uif2app.actions.UEReturnAction bean = new nc.ui.pubapp.uif2app.actions.UEReturnAction();
  context.put("returnaction",bean);
  bean.setGoComponent(getListView());
  bean.setSaveAction(getSaveAction());
  bean.setModel(getManageAppModel());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList20(){  List list = new ArrayList();  list.add(getActionsBar_ActionsBarSeparator_49c995());  list.add(getHeadZoomAction());  return list;}

private nc.ui.pub.beans.ActionsBar.ActionsBarSeparator getActionsBar_ActionsBarSeparator_49c995(){
 if(context.get("nc.ui.pub.beans.ActionsBar.ActionsBarSeparator#49c995")!=null)
 return (nc.ui.pub.beans.ActionsBar.ActionsBarSeparator)context.get("nc.ui.pub.beans.ActionsBar.ActionsBarSeparator#49c995");
  nc.ui.pub.beans.ActionsBar.ActionsBarSeparator bean = new nc.ui.pub.beans.ActionsBar.ActionsBarSeparator();
  context.put("nc.ui.pub.beans.ActionsBar.ActionsBarSeparator#49c995",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.pubapp.uif2app.actions.DefaultHeadZoomAction getHeadZoomAction(){
 if(context.get("headZoomAction")!=null)
 return (nc.ui.pubapp.uif2app.actions.DefaultHeadZoomAction)context.get("headZoomAction");
  nc.ui.pubapp.uif2app.actions.DefaultHeadZoomAction bean = new nc.ui.pubapp.uif2app.actions.DefaultHeadZoomAction();
  context.put("headZoomAction",bean);
  bean.setBillForm(getBillFormEditor());
  bean.setModel(getManageAppModel());
  bean.setPos(0);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.uif2.actions.ActionContributors getToftpanelActionContributors(){
 if(context.get("toftpanelActionContributors")!=null)
 return (nc.ui.uif2.actions.ActionContributors)context.get("toftpanelActionContributors");
  nc.ui.uif2.actions.ActionContributors bean = new nc.ui.uif2.actions.ActionContributors();
  context.put("toftpanelActionContributors",bean);
  bean.setContributors(getManagedList21());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList21(){  List list = new ArrayList();  list.add(getActionsOfList());  list.add(getActionsOfCard());  return list;}

public nc.ui.scmpub.listener.BillCodeEditMediator getBillcodeMediator(){
 if(context.get("billcodeMediator")!=null)
 return (nc.ui.scmpub.listener.BillCodeEditMediator)context.get("billcodeMediator");
  nc.ui.scmpub.listener.BillCodeEditMediator bean = new nc.ui.scmpub.listener.BillCodeEditMediator();
  context.put("billcodeMediator",bean);
  bean.setBillCodeKey("vbillcode");
  bean.setBillType("4331");
  bean.setBillForm(getBillFormEditor());
  bean.initUI();
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.uif2.actions.StandAloneToftPanelActionContainer getActionsOfList(){
 if(context.get("actionsOfList")!=null)
 return (nc.ui.uif2.actions.StandAloneToftPanelActionContainer)context.get("actionsOfList");
  nc.ui.uif2.actions.StandAloneToftPanelActionContainer bean = new nc.ui.uif2.actions.StandAloneToftPanelActionContainer(getListView());  context.put("actionsOfList",bean);
  bean.setActions(getManagedList22());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList22(){  List list = new ArrayList();  list.add(getAddMenuAction());  list.add(getEditAction());  list.add(getDeleteAction());  list.add(getSeparatorAction());  list.add(getQueryAction());  list.add(getListRefreshAction());  list.add(getSeparatorAction());  list.add(getSendGroupAction());  list.add(getApproveGroupAction());  list.add(getReturnManage());  list.add(getListAstFuncActionGroup());  list.add(getSeparatorAction());  list.add(getBrowLinkActionGroup());  list.add(getSeparatorAction());  list.add(getPrintActionGroup());  return list;}

public nc.ui.uif2.actions.StandAloneToftPanelActionContainer getActionsOfCard(){
 if(context.get("actionsOfCard")!=null)
 return (nc.ui.uif2.actions.StandAloneToftPanelActionContainer)context.get("actionsOfCard");
  nc.ui.uif2.actions.StandAloneToftPanelActionContainer bean = new nc.ui.uif2.actions.StandAloneToftPanelActionContainer(getBillFormEditor());  context.put("actionsOfCard",bean);
  bean.setModel(getManageAppModel());
  bean.setActions(getManagedList23());
  bean.setEditActions(getManagedList24());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList23(){  List list = new ArrayList();  list.add(getAddMenuAction());  list.add(getEditAction());  list.add(getDeleteAction());  list.add(getSeparatorAction());  list.add(getQueryAction());  list.add(getCardRefreshAction());  list.add(getSeparatorAction());  list.add(getSendGroupAction());  list.add(getApproveGroupAction());  list.add(getReturnManage());  list.add(getBrowAstFuncActionGroup());  list.add(getSeparatorAction());  list.add(getBrowLinkActionGroup());  list.add(getSeparatorAction());  list.add(getPrintActionGroup());  return list;}

private List getManagedList24(){  List list = new ArrayList();  list.add(getSaveAction());  list.add(getSaveandsendAction());  list.add(getSeparatorAction());  list.add(getCancelAction());  list.add(getSeparatorAction());  list.add(getRefAddLineAction());  list.add(getSeparatorAction());  list.add(getEditLinkActionGroup());  list.add(getSeparatorAction());  return list;}

public nc.ui.pubapp.uif2app.actions.AddMenuAction getAddMenuAction(){
 if(context.get("AddMenuAction")!=null)
 return (nc.ui.pubapp.uif2app.actions.AddMenuAction)context.get("AddMenuAction");
  nc.ui.pubapp.uif2app.actions.AddMenuAction bean = new nc.ui.pubapp.uif2app.actions.AddMenuAction();
  context.put("AddMenuAction",bean);
  bean.setBillType("4331");
  bean.setActions(getManagedList25());
  bean.setModel(getManageAppModel());
  bean.setPfAddInfoLoader(getPfAddInfoLoader());
  bean.initUI();
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList25(){  List list = new ArrayList();  list.add(getRefOrderAction());  list.add(getRefOutAction());  return list;}

public nc.ui.so.m4331.billui.action.addaction.RefOrderAddAction getRefOrderAction(){
 if(context.get("refOrderAction")!=null)
 return (nc.ui.so.m4331.billui.action.addaction.RefOrderAddAction)context.get("refOrderAction");
  nc.ui.so.m4331.billui.action.addaction.RefOrderAddAction bean = new nc.ui.so.m4331.billui.action.addaction.RefOrderAddAction();
  context.put("refOrderAction",bean);
  bean.setSourceBillType("30");
  bean.setSourceBillName(getI18nFB_515b80());
  bean.setModel(getManageAppModel());
  bean.setEditor(getBillFormEditor());
  bean.setTransferViewProcessor(getTransferViewProcessor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private java.lang.String getI18nFB_515b80(){
 if(context.get("nc.ui.uif2.I18nFB#515b80")!=null)
 return (java.lang.String)context.get("nc.ui.uif2.I18nFB#515b80");
  nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
    context.put("&nc.ui.uif2.I18nFB#515b80",bean);  bean.setResDir("4006002_0");
  bean.setResId("04006002-0168");
  bean.setDefaultValue("销售订单");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
 try {
     Object product = bean.getObject();
    context.put("nc.ui.uif2.I18nFB#515b80",product);
     return (java.lang.String)product;
}
catch(Exception e) { throw new RuntimeException(e);}}

public nc.ui.so.m4331.billui.action.addaction.RefOutAddAction getRefOutAction(){
 if(context.get("refOutAction")!=null)
 return (nc.ui.so.m4331.billui.action.addaction.RefOutAddAction)context.get("refOutAction");
  nc.ui.so.m4331.billui.action.addaction.RefOutAddAction bean = new nc.ui.so.m4331.billui.action.addaction.RefOutAddAction();
  context.put("refOutAction",bean);
  bean.setSourceBillType("5X");
  bean.setSourceBillName(getI18nFB_14e517d());
  bean.setModel(getManageAppModel());
  bean.setEditor(getBillFormEditor());
  bean.setTransferViewProcessor(getTransferViewProcessor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private java.lang.String getI18nFB_14e517d(){
 if(context.get("nc.ui.uif2.I18nFB#14e517d")!=null)
 return (java.lang.String)context.get("nc.ui.uif2.I18nFB#14e517d");
  nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
    context.put("&nc.ui.uif2.I18nFB#14e517d",bean);  bean.setResDir("4006002_0");
  bean.setResId("04006002-0169");
  bean.setDefaultValue("调拨订单");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
 try {
     Object product = bean.getObject();
    context.put("nc.ui.uif2.I18nFB#14e517d",product);
     return (java.lang.String)product;
}
catch(Exception e) { throw new RuntimeException(e);}}

public nc.ui.so.m4331.billui.action.DeliveryQueryAction getQueryAction(){
 if(context.get("queryAction")!=null)
 return (nc.ui.so.m4331.billui.action.DeliveryQueryAction)context.get("queryAction");
  nc.ui.so.m4331.billui.action.DeliveryQueryAction bean = new nc.ui.so.m4331.billui.action.DeliveryQueryAction();
  context.put("queryAction",bean);
  bean.setModel(getManageAppModel());
  bean.setDataManager(getModelDataManager());
  bean.setTemplateContainer(getQueryTemplateContainer());
  bean.setQryCondDLGInitializer(getQryCondDLGInitializer());
  bean.setShowUpComponent(getListView());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.uif2.tangramlayout.CardLayoutToolbarPanel getQueryInfo(){
 if(context.get("queryInfo")!=null)
 return (nc.ui.uif2.tangramlayout.CardLayoutToolbarPanel)context.get("queryInfo");
  nc.ui.uif2.tangramlayout.CardLayoutToolbarPanel bean = new nc.ui.uif2.tangramlayout.CardLayoutToolbarPanel();
  context.put("queryInfo",bean);
  bean.setModel(getManageAppModel());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.so.m4331.billui.view.dlg.DeliveryQueryDlg getQryCondDLGInitializer(){
 if(context.get("qryCondDLGInitializer")!=null)
 return (nc.ui.so.m4331.billui.view.dlg.DeliveryQueryDlg)context.get("qryCondDLGInitializer");
  nc.ui.so.m4331.billui.view.dlg.DeliveryQueryDlg bean = new nc.ui.so.m4331.billui.view.dlg.DeliveryQueryDlg();
  context.put("qryCondDLGInitializer",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.query2.action.DefaultRefreshAction getListRefreshAction(){
 if(context.get("listRefreshAction")!=null)
 return (nc.ui.pubapp.uif2app.query2.action.DefaultRefreshAction)context.get("listRefreshAction");
  nc.ui.pubapp.uif2app.query2.action.DefaultRefreshAction bean = new nc.ui.pubapp.uif2app.query2.action.DefaultRefreshAction();
  context.put("listRefreshAction",bean);
  bean.setDataManager(getModelDataManager());
  bean.setModel(getManageAppModel());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.actions.RefreshSingleAction getCardRefreshAction(){
 if(context.get("cardRefreshAction")!=null)
 return (nc.ui.pubapp.uif2app.actions.RefreshSingleAction)context.get("cardRefreshAction");
  nc.ui.pubapp.uif2app.actions.RefreshSingleAction bean = new nc.ui.pubapp.uif2app.actions.RefreshSingleAction();
  context.put("cardRefreshAction",bean);
  bean.setModel(getManageAppModel());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.actions.AddMenuAction getRefAddLineAction(){
 if(context.get("refAddLineAction")!=null)
 return (nc.ui.pubapp.uif2app.actions.AddMenuAction)context.get("refAddLineAction");
  nc.ui.pubapp.uif2app.actions.AddMenuAction bean = new nc.ui.pubapp.uif2app.actions.AddMenuAction();
  context.put("refAddLineAction",bean);
  bean.setBillType("4331");
  bean.setModel(getManageAppModel());
  bean.setCode("refaddline");
  bean.setName(getI18nFB_c7f493());
  bean.setActions(getManagedList26());
  bean.initUI();
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private java.lang.String getI18nFB_c7f493(){
 if(context.get("nc.ui.uif2.I18nFB#c7f493")!=null)
 return (java.lang.String)context.get("nc.ui.uif2.I18nFB#c7f493");
  nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
    context.put("&nc.ui.uif2.I18nFB#c7f493",bean);  bean.setResDir("4006002_0");
  bean.setResId("04006002-0174");
  bean.setDefaultValue("参照增行");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
 try {
     Object product = bean.getObject();
    context.put("nc.ui.uif2.I18nFB#c7f493",product);
     return (java.lang.String)product;
}
catch(Exception e) { throw new RuntimeException(e);}}

private List getManagedList26(){  List list = new ArrayList();  list.add(getRefOrderlineAction());  list.add(getRef5xlineAction());  return list;}

public nc.ui.so.m4331.billui.action.lineaction.RefOrderAddLineAction getRefOrderlineAction(){
 if(context.get("refOrderlineAction")!=null)
 return (nc.ui.so.m4331.billui.action.lineaction.RefOrderAddLineAction)context.get("refOrderlineAction");
  nc.ui.so.m4331.billui.action.lineaction.RefOrderAddLineAction bean = new nc.ui.so.m4331.billui.action.lineaction.RefOrderAddLineAction();
  context.put("refOrderlineAction",bean);
  bean.setSourceBillType("30");
  bean.setSourceBillName(getI18nFB_189df4b());
  bean.setModel(getManageAppModel());
  bean.setEditor(getBillFormEditor());
  bean.setTransferViewProcessor(getTransferViewProcessor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private java.lang.String getI18nFB_189df4b(){
 if(context.get("nc.ui.uif2.I18nFB#189df4b")!=null)
 return (java.lang.String)context.get("nc.ui.uif2.I18nFB#189df4b");
  nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
    context.put("&nc.ui.uif2.I18nFB#189df4b",bean);  bean.setResDir("4006002_0");
  bean.setResId("04006002-0168");
  bean.setDefaultValue("销售订单");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
 try {
     Object product = bean.getObject();
    context.put("nc.ui.uif2.I18nFB#189df4b",product);
     return (java.lang.String)product;
}
catch(Exception e) { throw new RuntimeException(e);}}

public nc.ui.so.m4331.billui.action.lineaction.Ref5XAddLineAction getRef5xlineAction(){
 if(context.get("ref5xlineAction")!=null)
 return (nc.ui.so.m4331.billui.action.lineaction.Ref5XAddLineAction)context.get("ref5xlineAction");
  nc.ui.so.m4331.billui.action.lineaction.Ref5XAddLineAction bean = new nc.ui.so.m4331.billui.action.lineaction.Ref5XAddLineAction();
  context.put("ref5xlineAction",bean);
  bean.setSourceBillType("5X");
  bean.setSourceBillName(getI18nFB_a2d99e());
  bean.setModel(getManageAppModel());
  bean.setEditor(getBillFormEditor());
  bean.setTransferViewProcessor(getTransferViewProcessor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private java.lang.String getI18nFB_a2d99e(){
 if(context.get("nc.ui.uif2.I18nFB#a2d99e")!=null)
 return (java.lang.String)context.get("nc.ui.uif2.I18nFB#a2d99e");
  nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
    context.put("&nc.ui.uif2.I18nFB#a2d99e",bean);  bean.setResDir("4006002_0");
  bean.setResId("04006002-0169");
  bean.setDefaultValue("调拨订单");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
 try {
     Object product = bean.getObject();
    context.put("nc.ui.uif2.I18nFB#a2d99e",product);
     return (java.lang.String)product;
}
catch(Exception e) { throw new RuntimeException(e);}}

public nc.ui.so.m4331.billui.action.DeliveryEditAction getEditAction(){
 if(context.get("editAction")!=null)
 return (nc.ui.so.m4331.billui.action.DeliveryEditAction)context.get("editAction");
  nc.ui.so.m4331.billui.action.DeliveryEditAction bean = new nc.ui.so.m4331.billui.action.DeliveryEditAction();
  context.put("editAction",bean);
  bean.setModel(getManageAppModel());
  bean.setInterceptor(getShowUpComponentInterceptor_17d2af6());
  bean.setView(getBillFormEditor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor getShowUpComponentInterceptor_17d2af6(){
 if(context.get("nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor#17d2af6")!=null)
 return (nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor)context.get("nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor#17d2af6");
  nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor bean = new nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor();
  context.put("nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor#17d2af6",bean);
  bean.setShowUpComponent(getBillFormEditor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.so.m4331.billui.action.DeliveryDeleteAction getDeleteAction(){
 if(context.get("deleteAction")!=null)
 return (nc.ui.so.m4331.billui.action.DeliveryDeleteAction)context.get("deleteAction");
  nc.ui.so.m4331.billui.action.DeliveryDeleteAction bean = new nc.ui.so.m4331.billui.action.DeliveryDeleteAction();
  context.put("deleteAction",bean);
  bean.setModel(getManageAppModel());
  bean.setEditor(getBillFormEditor());
  bean.setActionName("DELETE");
  bean.setBillType("4331");
  bean.setValidationService(getPowerdeletevalidservice());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.pub.power.PowerValidateService getPowerdeletevalidservice(){
 if(context.get("powerdeletevalidservice")!=null)
 return (nc.ui.pubapp.pub.power.PowerValidateService)context.get("powerdeletevalidservice");
  nc.ui.pubapp.pub.power.PowerValidateService bean = new nc.ui.pubapp.pub.power.PowerValidateService();
  context.put("powerdeletevalidservice",bean);
  bean.setActionCode("delete");
  bean.setBillCodeFiledName("vbillcode");
  bean.setPermissionCode("4331");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.so.m4331.billui.action.DeliverySaveAction getSaveAction(){
 if(context.get("saveAction")!=null)
 return (nc.ui.so.m4331.billui.action.DeliverySaveAction)context.get("saveAction");
  nc.ui.so.m4331.billui.action.DeliverySaveAction bean = new nc.ui.so.m4331.billui.action.DeliverySaveAction();
  context.put("saveAction",bean);
  bean.setModel(getManageAppModel());
  bean.setEditor(getBillFormEditor());
  bean.setFilledUpInFlow(false);
  bean.setActionName("WRITE");
  bean.setBillType("4331");
  bean.setRefreshAction(getCardRefreshAction());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.actions.CancelAction getCancelAction(){
 if(context.get("cancelAction")!=null)
 return (nc.ui.pubapp.uif2app.actions.CancelAction)context.get("cancelAction");
  nc.ui.pubapp.uif2app.actions.CancelAction bean = new nc.ui.pubapp.uif2app.actions.CancelAction();
  context.put("cancelAction",bean);
  bean.setModel(getManageAppModel());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.funcnode.ui.action.GroupAction getSendGroupAction(){
 if(context.get("sendGroupAction")!=null)
 return (nc.funcnode.ui.action.GroupAction)context.get("sendGroupAction");
  nc.funcnode.ui.action.GroupAction bean = new nc.funcnode.ui.action.GroupAction();
  context.put("sendGroupAction",bean);
  bean.setActions(getManagedList27());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList27(){  List list = new ArrayList();  list.add(getSendApproveAction());  list.add(getUnsendApproveAction());  return list;}

public nc.ui.so.m4331.billui.action.DeliverySaveCommitAction getSaveandsendAction(){
 if(context.get("saveandsendAction")!=null)
 return (nc.ui.so.m4331.billui.action.DeliverySaveCommitAction)context.get("saveandsendAction");
  nc.ui.so.m4331.billui.action.DeliverySaveCommitAction bean = new nc.ui.so.m4331.billui.action.DeliverySaveCommitAction(getSaveAction(),getSendApproveAction());  context.put("saveandsendAction",bean);
  bean.setModel(getManageAppModel());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.so.m4331.billui.action.DeliverySendApproveAction getSendApproveAction(){
 if(context.get("sendApproveAction")!=null)
 return (nc.ui.so.m4331.billui.action.DeliverySendApproveAction)context.get("sendApproveAction");
  nc.ui.so.m4331.billui.action.DeliverySendApproveAction bean = new nc.ui.so.m4331.billui.action.DeliverySendApproveAction();
  context.put("sendApproveAction",bean);
  bean.setModel(getManageAppModel());
  bean.setEditor(getBillFormEditor());
  bean.setFilledUpInFlow(true);
  bean.setActionName("SAVE");
  bean.setBillType("4331");
  bean.setValidationService(getPowersendapprovevalidservice());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.pub.power.PowerValidateService getPowersendapprovevalidservice(){
 if(context.get("powersendapprovevalidservice")!=null)
 return (nc.ui.pubapp.pub.power.PowerValidateService)context.get("powersendapprovevalidservice");
  nc.ui.pubapp.pub.power.PowerValidateService bean = new nc.ui.pubapp.pub.power.PowerValidateService();
  context.put("powersendapprovevalidservice",bean);
  bean.setActionCode("commit");
  bean.setBillCodeFiledName("vbillcode");
  bean.setPermissionCode("4331");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.so.m4331.billui.action.DeliveryUnSendApproveAction getUnsendApproveAction(){
 if(context.get("unsendApproveAction")!=null)
 return (nc.ui.so.m4331.billui.action.DeliveryUnSendApproveAction)context.get("unsendApproveAction");
  nc.ui.so.m4331.billui.action.DeliveryUnSendApproveAction bean = new nc.ui.so.m4331.billui.action.DeliveryUnSendApproveAction();
  context.put("unsendApproveAction",bean);
  bean.setModel(getManageAppModel());
  bean.setEditor(getBillFormEditor());
  bean.setFilledUpInFlow(true);
  bean.setActionName("UNSAVE");
  bean.setBillType("4331");
  bean.setValidationService(getPowerunsendvalidservice());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.pub.power.PowerValidateService getPowerunsendvalidservice(){
 if(context.get("powerunsendvalidservice")!=null)
 return (nc.ui.pubapp.pub.power.PowerValidateService)context.get("powerunsendvalidservice");
  nc.ui.pubapp.pub.power.PowerValidateService bean = new nc.ui.pubapp.pub.power.PowerValidateService();
  context.put("powerunsendvalidservice",bean);
  bean.setActionCode("uncommit");
  bean.setBillCodeFiledName("vbillcode");
  bean.setPermissionCode("4331");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.funcnode.ui.action.GroupAction getApproveGroupAction(){
 if(context.get("approveGroupAction")!=null)
 return (nc.funcnode.ui.action.GroupAction)context.get("approveGroupAction");
  nc.funcnode.ui.action.GroupAction bean = new nc.funcnode.ui.action.GroupAction();
  context.put("approveGroupAction",bean);
  bean.setActions(getManagedList28());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList28(){  List list = new ArrayList();  list.add(getApproveAction());  list.add(getUnApproveAction());  list.add(getSeparatorAction_8fb9c3());  list.add(getAuditFlowAction());  return list;}

private nc.funcnode.ui.action.SeparatorAction getSeparatorAction_8fb9c3(){
 if(context.get("nc.funcnode.ui.action.SeparatorAction#8fb9c3")!=null)
 return (nc.funcnode.ui.action.SeparatorAction)context.get("nc.funcnode.ui.action.SeparatorAction#8fb9c3");
  nc.funcnode.ui.action.SeparatorAction bean = new nc.funcnode.ui.action.SeparatorAction();
  context.put("nc.funcnode.ui.action.SeparatorAction#8fb9c3",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.so.m4331.billui.action.DeliveryApproveAction getApproveAction(){
 if(context.get("approveAction")!=null)
 return (nc.ui.so.m4331.billui.action.DeliveryApproveAction)context.get("approveAction");
  nc.ui.so.m4331.billui.action.DeliveryApproveAction bean = new nc.ui.so.m4331.billui.action.DeliveryApproveAction();
  context.put("approveAction",bean);
  bean.setModel(getManageAppModel());
  bean.setEditor(getBillFormEditor());
  bean.setFilledUpInFlow(true);
  bean.setActionName("APPROVE");
  bean.setBillType("4331");
  bean.setValidationService(getPowerapprovevalidservice());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.pub.power.PowerValidateService getPowerapprovevalidservice(){
 if(context.get("powerapprovevalidservice")!=null)
 return (nc.ui.pubapp.pub.power.PowerValidateService)context.get("powerapprovevalidservice");
  nc.ui.pubapp.pub.power.PowerValidateService bean = new nc.ui.pubapp.pub.power.PowerValidateService();
  context.put("powerapprovevalidservice",bean);
  bean.setActionCode("approve");
  bean.setBillCodeFiledName("vbillcode");
  bean.setPermissionCode("4331");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.so.m4331.billui.action.DeliveryUnApproveAction getUnApproveAction(){
 if(context.get("unApproveAction")!=null)
 return (nc.ui.so.m4331.billui.action.DeliveryUnApproveAction)context.get("unApproveAction");
  nc.ui.so.m4331.billui.action.DeliveryUnApproveAction bean = new nc.ui.so.m4331.billui.action.DeliveryUnApproveAction();
  context.put("unApproveAction",bean);
  bean.setModel(getManageAppModel());
  bean.setEditor(getBillFormEditor());
  bean.setFilledUpInFlow(true);
  bean.setActionName("UNAPPROVE");
  bean.setBillType("4331");
  bean.setValidationService(getPowerunapprovevalidservice());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.pub.power.PowerValidateService getPowerunapprovevalidservice(){
 if(context.get("powerunapprovevalidservice")!=null)
 return (nc.ui.pubapp.pub.power.PowerValidateService)context.get("powerunapprovevalidservice");
  nc.ui.pubapp.pub.power.PowerValidateService bean = new nc.ui.pubapp.pub.power.PowerValidateService();
  context.put("powerunapprovevalidservice",bean);
  bean.setActionCode("unapprove");
  bean.setBillCodeFiledName("vbillcode");
  bean.setPermissionCode("4331");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.funcnode.ui.action.MenuAction getReturnManage(){
 if(context.get("returnManage")!=null)
 return (nc.funcnode.ui.action.MenuAction)context.get("returnManage");
  nc.funcnode.ui.action.MenuAction bean = new nc.funcnode.ui.action.MenuAction();
  context.put("returnManage",bean);
  bean.setCode("editAstFunc");
  bean.setName(getI18nFB_1eccf3e());
  bean.setActions(getManagedList29());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private java.lang.String getI18nFB_1eccf3e(){
 if(context.get("nc.ui.uif2.I18nFB#1eccf3e")!=null)
 return (java.lang.String)context.get("nc.ui.uif2.I18nFB#1eccf3e");
  nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
    context.put("&nc.ui.uif2.I18nFB#1eccf3e",bean);  bean.setResDir("4006002_0");
  bean.setResId("04006002-0171");
  bean.setDefaultValue("退货处理");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
 try {
     Object product = bean.getObject();
    context.put("nc.ui.uif2.I18nFB#1eccf3e",product);
     return (java.lang.String)product;
}
catch(Exception e) { throw new RuntimeException(e);}}

private List getManagedList29(){  List list = new ArrayList();  list.add(getCheckBillAction());  list.add(getPriceChangeAction());  list.add(getCheckBillReportAction());  return list;}

public nc.ui.so.m4331.billui.action.returnaction.CheckBillAction getCheckBillAction(){
 if(context.get("checkBillAction")!=null)
 return (nc.ui.so.m4331.billui.action.returnaction.CheckBillAction)context.get("checkBillAction");
  nc.ui.so.m4331.billui.action.returnaction.CheckBillAction bean = new nc.ui.so.m4331.billui.action.returnaction.CheckBillAction();
  context.put("checkBillAction",bean);
  bean.setModel(getManageAppModel());
  bean.setEditor(getBillFormEditor());
  bean.setListview(getListView());
  bean.setRefreshAction(getCardRefreshAction());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.so.m4331.billui.action.returnaction.PriceChangeAction getPriceChangeAction(){
 if(context.get("priceChangeAction")!=null)
 return (nc.ui.so.m4331.billui.action.returnaction.PriceChangeAction)context.get("priceChangeAction");
  nc.ui.so.m4331.billui.action.returnaction.PriceChangeAction bean = new nc.ui.so.m4331.billui.action.returnaction.PriceChangeAction();
  context.put("priceChangeAction",bean);
  bean.setModel(getManageAppModel());
  bean.setEditor(getBillFormEditor());
  bean.setListview(getListView());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.so.m4331.billui.action.returnaction.CheckBillReportAction getCheckBillReportAction(){
 if(context.get("checkBillReportAction")!=null)
 return (nc.ui.so.m4331.billui.action.returnaction.CheckBillReportAction)context.get("checkBillReportAction");
  nc.ui.so.m4331.billui.action.returnaction.CheckBillReportAction bean = new nc.ui.so.m4331.billui.action.returnaction.CheckBillReportAction();
  context.put("checkBillReportAction",bean);
  bean.setModel(getManageAppModel());
  bean.setListview(getListView());
  bean.setEditor(getBillFormEditor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.funcnode.ui.action.MenuAction getBrowAstFuncActionGroup(){
 if(context.get("browAstFuncActionGroup")!=null)
 return (nc.funcnode.ui.action.MenuAction)context.get("browAstFuncActionGroup");
  nc.funcnode.ui.action.MenuAction bean = new nc.funcnode.ui.action.MenuAction();
  context.put("browAstFuncActionGroup",bean);
  bean.setCode("browAstFunc");
  bean.setName(getI18nFB_1a090d());
  bean.setActions(getManagedList30());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private java.lang.String getI18nFB_1a090d(){
 if(context.get("nc.ui.uif2.I18nFB#1a090d")!=null)
 return (java.lang.String)context.get("nc.ui.uif2.I18nFB#1a090d");
  nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
    context.put("&nc.ui.uif2.I18nFB#1a090d",bean);  bean.setResDir("4006002_0");
  bean.setResId("04006002-0173");
  bean.setDefaultValue("辅助功能");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
 try {
     Object product = bean.getObject();
    context.put("nc.ui.uif2.I18nFB#1a090d",product);
     return (java.lang.String)product;
}
catch(Exception e) { throw new RuntimeException(e);}}

private List getManagedList30(){  List list = new ArrayList();  list.add(getReverseQueryAction());  list.add(getReverseAction());  list.add(getDeliveryCloseAction());  list.add(getDeliveryOpenAction());  list.add(getRowCloseAction());  list.add(getRowOpenAction());  return list;}

public nc.funcnode.ui.action.MenuAction getListAstFuncActionGroup(){
 if(context.get("listAstFuncActionGroup")!=null)
 return (nc.funcnode.ui.action.MenuAction)context.get("listAstFuncActionGroup");
  nc.funcnode.ui.action.MenuAction bean = new nc.funcnode.ui.action.MenuAction();
  context.put("listAstFuncActionGroup",bean);
  bean.setCode("browAstFunc");
  bean.setName(getI18nFB_1484e2d());
  bean.setActions(getManagedList31());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private java.lang.String getI18nFB_1484e2d(){
 if(context.get("nc.ui.uif2.I18nFB#1484e2d")!=null)
 return (java.lang.String)context.get("nc.ui.uif2.I18nFB#1484e2d");
  nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
    context.put("&nc.ui.uif2.I18nFB#1484e2d",bean);  bean.setResDir("4006002_0");
  bean.setResId("04006002-0173");
  bean.setDefaultValue("辅助功能");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
 try {
     Object product = bean.getObject();
    context.put("nc.ui.uif2.I18nFB#1484e2d",product);
     return (java.lang.String)product;
}
catch(Exception e) { throw new RuntimeException(e);}}

private List getManagedList31(){  List list = new ArrayList();  list.add(getReverseQueryAction());  list.add(getDeliveryCloseAction());  list.add(getDeliveryOpenAction());  list.add(getRowCloseAction());  list.add(getRowOpenAction());  return list;}

public nc.ui.so.m4331.billui.action.assitfunc.DeliveryReverseAction getReverseAction(){
 if(context.get("reverseAction")!=null)
 return (nc.ui.so.m4331.billui.action.assitfunc.DeliveryReverseAction)context.get("reverseAction");
  nc.ui.so.m4331.billui.action.assitfunc.DeliveryReverseAction bean = new nc.ui.so.m4331.billui.action.assitfunc.DeliveryReverseAction();
  context.put("reverseAction",bean);
  bean.setModel(getManageAppModel());
  bean.setEditor(getBillFormEditor());
  bean.setRefreshAction(getCardRefreshAction());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.so.m4331.billui.action.assitfunc.DeliveryQueryReverseAction getReverseQueryAction(){
 if(context.get("reverseQueryAction")!=null)
 return (nc.ui.so.m4331.billui.action.assitfunc.DeliveryQueryReverseAction)context.get("reverseQueryAction");
  nc.ui.so.m4331.billui.action.assitfunc.DeliveryQueryReverseAction bean = new nc.ui.so.m4331.billui.action.assitfunc.DeliveryQueryReverseAction();
  context.put("reverseQueryAction",bean);
  bean.setModel(getManageAppModel());
  bean.setEditor(getBillFormEditor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.so.m4331.billui.action.assitfunc.DeliveryCloseAction getDeliveryCloseAction(){
 if(context.get("deliveryCloseAction")!=null)
 return (nc.ui.so.m4331.billui.action.assitfunc.DeliveryCloseAction)context.get("deliveryCloseAction");
  nc.ui.so.m4331.billui.action.assitfunc.DeliveryCloseAction bean = new nc.ui.so.m4331.billui.action.assitfunc.DeliveryCloseAction();
  context.put("deliveryCloseAction",bean);
  bean.setModel(getManageAppModel());
  bean.setEditor(getBillFormEditor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.so.m4331.billui.action.assitfunc.DeliveryOpenAction getDeliveryOpenAction(){
 if(context.get("deliveryOpenAction")!=null)
 return (nc.ui.so.m4331.billui.action.assitfunc.DeliveryOpenAction)context.get("deliveryOpenAction");
  nc.ui.so.m4331.billui.action.assitfunc.DeliveryOpenAction bean = new nc.ui.so.m4331.billui.action.assitfunc.DeliveryOpenAction();
  context.put("deliveryOpenAction",bean);
  bean.setModel(getManageAppModel());
  bean.setEditor(getBillFormEditor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.so.m4331.billui.action.assitfunc.DeliveryRowCloseAction getRowCloseAction(){
 if(context.get("rowCloseAction")!=null)
 return (nc.ui.so.m4331.billui.action.assitfunc.DeliveryRowCloseAction)context.get("rowCloseAction");
  nc.ui.so.m4331.billui.action.assitfunc.DeliveryRowCloseAction bean = new nc.ui.so.m4331.billui.action.assitfunc.DeliveryRowCloseAction();
  context.put("rowCloseAction",bean);
  bean.setModel(getManageAppModel());
  bean.setEditor(getBillFormEditor());
  bean.setRefreshAction(getCardRefreshAction());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.so.m4331.billui.action.assitfunc.DeliveryRowOpenAction getRowOpenAction(){
 if(context.get("rowOpenAction")!=null)
 return (nc.ui.so.m4331.billui.action.assitfunc.DeliveryRowOpenAction)context.get("rowOpenAction");
  nc.ui.so.m4331.billui.action.assitfunc.DeliveryRowOpenAction bean = new nc.ui.so.m4331.billui.action.assitfunc.DeliveryRowOpenAction();
  context.put("rowOpenAction",bean);
  bean.setModel(getManageAppModel());
  bean.setEditor(getBillFormEditor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.funcnode.ui.action.MenuAction getBrowLinkActionGroup(){
 if(context.get("browLinkActionGroup")!=null)
 return (nc.funcnode.ui.action.MenuAction)context.get("browLinkActionGroup");
  nc.funcnode.ui.action.MenuAction bean = new nc.funcnode.ui.action.MenuAction();
  context.put("browLinkActionGroup",bean);
  bean.setCode("browLink");
  bean.setName(getI18nFB_492f7());
  bean.setActions(getManagedList32());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private java.lang.String getI18nFB_492f7(){
 if(context.get("nc.ui.uif2.I18nFB#492f7")!=null)
 return (java.lang.String)context.get("nc.ui.uif2.I18nFB#492f7");
  nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
    context.put("&nc.ui.uif2.I18nFB#492f7",bean);  bean.setResDir("4006002_0");
  bean.setResId("04006002-0172");
  bean.setDefaultValue("联查");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
 try {
     Object product = bean.getObject();
    context.put("nc.ui.uif2.I18nFB#492f7",product);
     return (java.lang.String)product;
}
catch(Exception e) { throw new RuntimeException(e);}}

private List getManagedList32(){  List list = new ArrayList();  list.add(getLinkQueryAction());  list.add(getSeparatorAction_4cd3af());  list.add(getQueryOnhandAction());  list.add(getCustCreditAction());  list.add(getCustInfoAction());  list.add(getTransStatusAction());  return list;}

private nc.funcnode.ui.action.SeparatorAction getSeparatorAction_4cd3af(){
 if(context.get("nc.funcnode.ui.action.SeparatorAction#4cd3af")!=null)
 return (nc.funcnode.ui.action.SeparatorAction)context.get("nc.funcnode.ui.action.SeparatorAction#4cd3af");
  nc.funcnode.ui.action.SeparatorAction bean = new nc.funcnode.ui.action.SeparatorAction();
  context.put("nc.funcnode.ui.action.SeparatorAction#4cd3af",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.scmmm.ui.uif2.actions.SCMLinkQueryAction getLinkQueryAction(){
 if(context.get("linkQueryAction")!=null)
 return (nc.scmmm.ui.uif2.actions.SCMLinkQueryAction)context.get("linkQueryAction");
  nc.scmmm.ui.uif2.actions.SCMLinkQueryAction bean = new nc.scmmm.ui.uif2.actions.SCMLinkQueryAction();
  context.put("linkQueryAction",bean);
  bean.setModel(getManageAppModel());
  bean.setBillType("4331");
  bean.setOpenMode(1);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.so.pub.actions.SOQueryOnhandAction getQueryOnhandAction(){
 if(context.get("queryOnhandAction")!=null)
 return (nc.ui.so.pub.actions.SOQueryOnhandAction)context.get("queryOnhandAction");
  nc.ui.so.pub.actions.SOQueryOnhandAction bean = new nc.ui.so.pub.actions.SOQueryOnhandAction();
  context.put("queryOnhandAction",bean);
  bean.setCard(getBillFormEditor());
  bean.setModel(getManageAppModel());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.actions.pflow.PFApproveStatusInfoAction getAuditFlowAction(){
 if(context.get("auditFlowAction")!=null)
 return (nc.ui.pubapp.uif2app.actions.pflow.PFApproveStatusInfoAction)context.get("auditFlowAction");
  nc.ui.pubapp.uif2app.actions.pflow.PFApproveStatusInfoAction bean = new nc.ui.pubapp.uif2app.actions.pflow.PFApproveStatusInfoAction();
  context.put("auditFlowAction",bean);
  bean.setModel(getManageAppModel());
  bean.setEditor(getBillFormEditor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.so.m4331.billui.action.linkaction.QueryCustCreditAction getCustCreditAction(){
 if(context.get("custCreditAction")!=null)
 return (nc.ui.so.m4331.billui.action.linkaction.QueryCustCreditAction)context.get("custCreditAction");
  nc.ui.so.m4331.billui.action.linkaction.QueryCustCreditAction bean = new nc.ui.so.m4331.billui.action.linkaction.QueryCustCreditAction();
  context.put("custCreditAction",bean);
  bean.setModel(getManageAppModel());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.so.m4331.billui.action.linkaction.QueryCustInfoAction getCustInfoAction(){
 if(context.get("custInfoAction")!=null)
 return (nc.ui.so.m4331.billui.action.linkaction.QueryCustInfoAction)context.get("custInfoAction");
  nc.ui.so.m4331.billui.action.linkaction.QueryCustInfoAction bean = new nc.ui.so.m4331.billui.action.linkaction.QueryCustInfoAction();
  context.put("custInfoAction",bean);
  bean.setModel(getManageAppModel());
  bean.setEditor(getBillFormEditor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.so.m4331.billui.action.linkaction.TransportStatusAction getTransStatusAction(){
 if(context.get("transStatusAction")!=null)
 return (nc.ui.so.m4331.billui.action.linkaction.TransportStatusAction)context.get("transStatusAction");
  nc.ui.so.m4331.billui.action.linkaction.TransportStatusAction bean = new nc.ui.so.m4331.billui.action.linkaction.TransportStatusAction();
  context.put("transStatusAction",bean);
  bean.setModel(getManageAppModel());
  bean.setRefreshAction(getCardRefreshAction());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.funcnode.ui.action.MenuAction getEditLinkActionGroup(){
 if(context.get("editLinkActionGroup")!=null)
 return (nc.funcnode.ui.action.MenuAction)context.get("editLinkActionGroup");
  nc.funcnode.ui.action.MenuAction bean = new nc.funcnode.ui.action.MenuAction();
  context.put("editLinkActionGroup",bean);
  bean.setCode("editLink");
  bean.setName(getI18nFB_1ddc1cf());
  bean.setActions(getManagedList33());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private java.lang.String getI18nFB_1ddc1cf(){
 if(context.get("nc.ui.uif2.I18nFB#1ddc1cf")!=null)
 return (java.lang.String)context.get("nc.ui.uif2.I18nFB#1ddc1cf");
  nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
    context.put("&nc.ui.uif2.I18nFB#1ddc1cf",bean);  bean.setResDir("4006002_0");
  bean.setResId("04006002-0172");
  bean.setDefaultValue("联查");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
 try {
     Object product = bean.getObject();
    context.put("nc.ui.uif2.I18nFB#1ddc1cf",product);
     return (java.lang.String)product;
}
catch(Exception e) { throw new RuntimeException(e);}}

private List getManagedList33(){  List list = new ArrayList();  list.add(getQueryOnhandAction());  return list;}

public nc.funcnode.ui.action.GroupAction getPrintActionGroup(){
 if(context.get("printActionGroup")!=null)
 return (nc.funcnode.ui.action.GroupAction)context.get("printActionGroup");
  nc.funcnode.ui.action.GroupAction bean = new nc.funcnode.ui.action.GroupAction();
  context.put("printActionGroup",bean);
  bean.setCode("printMenuAction");
  bean.setName(getI18nFB_1df2e34());
  bean.setActions(getManagedList34());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private java.lang.String getI18nFB_1df2e34(){
 if(context.get("nc.ui.uif2.I18nFB#1df2e34")!=null)
 return (java.lang.String)context.get("nc.ui.uif2.I18nFB#1df2e34");
  nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
    context.put("&nc.ui.uif2.I18nFB#1df2e34",bean);  bean.setResDir("common");
  bean.setResId("UC001-0000007");
  bean.setDefaultValue("打印");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
 try {
     Object product = bean.getObject();
    context.put("nc.ui.uif2.I18nFB#1df2e34",product);
     return (java.lang.String)product;
}
catch(Exception e) { throw new RuntimeException(e);}}

private List getManagedList34(){  List list = new ArrayList();  list.add(getPrintAction());  list.add(getPreviewAction());  list.add(getOutputAction());  list.add(getSeparatorAction_15284fd());  list.add(getSplitPrintAction());  list.add(getPrintCountQueryAction());  return list;}

private nc.funcnode.ui.action.SeparatorAction getSeparatorAction_15284fd(){
 if(context.get("nc.funcnode.ui.action.SeparatorAction#15284fd")!=null)
 return (nc.funcnode.ui.action.SeparatorAction)context.get("nc.funcnode.ui.action.SeparatorAction#15284fd");
  nc.funcnode.ui.action.SeparatorAction bean = new nc.funcnode.ui.action.SeparatorAction();
  context.put("nc.funcnode.ui.action.SeparatorAction#15284fd",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.actions.OutputAction getOutputAction(){
 if(context.get("outputAction")!=null)
 return (nc.ui.pubapp.uif2app.actions.OutputAction)context.get("outputAction");
  nc.ui.pubapp.uif2app.actions.OutputAction bean = new nc.ui.pubapp.uif2app.actions.OutputAction();
  context.put("outputAction",bean);
  bean.setModel(getManageAppModel());
  bean.setParent(getBillFormEditor());
  bean.setBeforePrintDataProcess(getPrintProcessor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.actions.MetaDataBasedPrintAction getPreviewAction(){
 if(context.get("previewAction")!=null)
 return (nc.ui.pubapp.uif2app.actions.MetaDataBasedPrintAction)context.get("previewAction");
  nc.ui.pubapp.uif2app.actions.MetaDataBasedPrintAction bean = new nc.ui.pubapp.uif2app.actions.MetaDataBasedPrintAction();
  context.put("previewAction",bean);
  bean.setPreview(true);
  bean.setModel(getManageAppModel());
  bean.setBeforePrintDataProcess(getPrintProcessor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.actions.MetaDataBasedPrintAction getPrintAction(){
 if(context.get("printAction")!=null)
 return (nc.ui.pubapp.uif2app.actions.MetaDataBasedPrintAction)context.get("printAction");
  nc.ui.pubapp.uif2app.actions.MetaDataBasedPrintAction bean = new nc.ui.pubapp.uif2app.actions.MetaDataBasedPrintAction();
  context.put("printAction",bean);
  bean.setPreview(false);
  bean.setModel(getManageAppModel());
  bean.setBeforePrintDataProcess(getPrintProcessor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.so.m4331.billui.action.printaction.DeliverySplitPrintAction getSplitPrintAction(){
 if(context.get("splitPrintAction")!=null)
 return (nc.ui.so.m4331.billui.action.printaction.DeliverySplitPrintAction)context.get("splitPrintAction");
  nc.ui.so.m4331.billui.action.printaction.DeliverySplitPrintAction bean = new nc.ui.so.m4331.billui.action.printaction.DeliverySplitPrintAction();
  context.put("splitPrintAction",bean);
  bean.setModel(getManageAppModel());
  bean.setPrintAction(getPrintAction());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.so.m4331.billui.action.printaction.DeliveryPrintProcessor getPrintProcessor(){
 if(context.get("printProcessor")!=null)
 return (nc.ui.so.m4331.billui.action.printaction.DeliveryPrintProcessor)context.get("printProcessor");
  nc.ui.so.m4331.billui.action.printaction.DeliveryPrintProcessor bean = new nc.ui.so.m4331.billui.action.printaction.DeliveryPrintProcessor();
  context.put("printProcessor",bean);
  bean.setModel(getManageAppModel());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.scmpub.action.SCMPrintCountQueryAction getPrintCountQueryAction(){
 if(context.get("printCountQueryAction")!=null)
 return (nc.ui.scmpub.action.SCMPrintCountQueryAction)context.get("printCountQueryAction");
  nc.ui.scmpub.action.SCMPrintCountQueryAction bean = new nc.ui.scmpub.action.SCMPrintCountQueryAction();
  context.put("printCountQueryAction",bean);
  bean.setModel(getManageAppModel());
  bean.setBilldateFieldName("dbilldate");
  bean.setBillType("4331");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.billref.dest.TransferViewProcessor getTransferViewProcessor(){
 if(context.get("transferViewProcessor")!=null)
 return (nc.ui.pubapp.billref.dest.TransferViewProcessor)context.get("transferViewProcessor");
  nc.ui.pubapp.billref.dest.TransferViewProcessor bean = new nc.ui.pubapp.billref.dest.TransferViewProcessor();
  context.put("transferViewProcessor",bean);
  bean.setList(getListView());
  bean.setActionContainer(getActionsOfList());
  bean.setCardActionContainer(getActionsOfCard());
  bean.setSaveAction(getSaveAction());
  bean.setCommitAction(getSendApproveAction());
  bean.setCancelAction(getCancelAction());
  bean.setBillForm(getBillFormEditor());
  bean.setQueryAreaShell(getQueryArea());
  bean.setQueryInfoToolbarPanel(getListToolbarPnl());
  bean.setTransferLogic(getDefaultBillDataLogic_993e52());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.pubapp.billref.dest.DefaultBillDataLogic getDefaultBillDataLogic_993e52(){
 if(context.get("nc.ui.pubapp.billref.dest.DefaultBillDataLogic#993e52")!=null)
 return (nc.ui.pubapp.billref.dest.DefaultBillDataLogic)context.get("nc.ui.pubapp.billref.dest.DefaultBillDataLogic#993e52");
  nc.ui.pubapp.billref.dest.DefaultBillDataLogic bean = new nc.ui.pubapp.billref.dest.DefaultBillDataLogic();
  context.put("nc.ui.pubapp.billref.dest.DefaultBillDataLogic#993e52",bean);
  bean.setBillForm(getBillFormEditor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.model.DefaultFuncNodeInitDataListener getInitDataListener(){
 if(context.get("InitDataListener")!=null)
 return (nc.ui.pubapp.uif2app.model.DefaultFuncNodeInitDataListener)context.get("InitDataListener");
  nc.ui.pubapp.uif2app.model.DefaultFuncNodeInitDataListener bean = new nc.ui.pubapp.uif2app.model.DefaultFuncNodeInitDataListener();
  context.put("InitDataListener",bean);
  bean.setContext(getContext());
  bean.setModel(getManageAppModel());
  bean.setVoClassName("nc.vo.so.m4331.entity.DeliveryVO");
  bean.setAutoShowUpComponent(getBillFormEditor());
  bean.setQueryAction(getQueryAction());
  bean.setProcessorMap(getManagedMap2());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private Map getManagedMap2(){  Map map = new HashMap();  map.put("19",getM5XInitDataProcessor_ca9478());  return map;}

private nc.ui.so.m4331.billref.m5x.M5XInitDataProcessor getM5XInitDataProcessor_ca9478(){
 if(context.get("nc.ui.so.m4331.billref.m5x.M5XInitDataProcessor#ca9478")!=null)
 return (nc.ui.so.m4331.billref.m5x.M5XInitDataProcessor)context.get("nc.ui.so.m4331.billref.m5x.M5XInitDataProcessor#ca9478");
  nc.ui.so.m4331.billref.m5x.M5XInitDataProcessor bean = new nc.ui.so.m4331.billref.m5x.M5XInitDataProcessor();
  context.put("nc.ui.so.m4331.billref.m5x.M5XInitDataProcessor#ca9478",bean);
  bean.setTransferProcessor(getTransferViewProcessor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

}
