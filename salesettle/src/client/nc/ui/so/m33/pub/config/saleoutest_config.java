package nc.ui.so.m33.pub.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.ui.uif2.factory.AbstractJavaBeanDefinition;

public class saleoutest_config extends AbstractJavaBeanDefinition{
	private Map<String, Object> context = new HashMap();
public nc.funcnode.ui.action.SeparatorAction getSeparatorAction(){
 if(context.get("separatorAction")!=null)
 return (nc.funcnode.ui.action.SeparatorAction)context.get("separatorAction");
  nc.funcnode.ui.action.SeparatorAction bean = new nc.funcnode.ui.action.SeparatorAction();
  context.put("separatorAction",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.linkquery.LinkQueryHyperlinkMediator getLinkQueryHyperlinkFixMediator(){
 if(context.get("linkQueryHyperlinkFixMediator")!=null)
 return (nc.ui.pubapp.uif2app.linkquery.LinkQueryHyperlinkMediator)context.get("linkQueryHyperlinkFixMediator");
  nc.ui.pubapp.uif2app.linkquery.LinkQueryHyperlinkMediator bean = new nc.ui.pubapp.uif2app.linkquery.LinkQueryHyperlinkMediator();
  context.put("linkQueryHyperlinkFixMediator",bean);
  bean.setModel(getManageAppModel());
  bean.setSrcBillIdField("csquarebillid");
  bean.setSrcBillNOField("vbillcode");
  bean.setSrcBillType("4C");
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

public nc.vo.uif2.LoginContext getContext(){
 if(context.get("context")!=null)
 return (nc.vo.uif2.LoginContext)context.get("context");
  nc.vo.uif2.LoginContext bean = new nc.vo.uif2.LoginContext();
  context.put("context",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.so.m33.manest.model.SaleOutETQueryService getQryService(){
 if(context.get("qryService")!=null)
 return (nc.ui.so.m33.manest.model.SaleOutETQueryService)context.get("qryService");
  nc.ui.so.m33.manest.model.SaleOutETQueryService bean = new nc.ui.so.m33.manest.model.SaleOutETQueryService();
  context.put("qryService",bean);
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

public nc.ui.so.m33.pub.SquareOutManageModel getManageAppModel(){
 if(context.get("ManageAppModel")!=null)
 return (nc.ui.so.m33.pub.SquareOutManageModel)context.get("ManageAppModel");
  nc.ui.so.m33.pub.SquareOutManageModel bean = new nc.ui.so.m33.pub.SquareOutManageModel();
  context.put("ManageAppModel",bean);
  bean.setBusinessObjectAdapterFactory(getBoadatorfactory());
  bean.setContext(getContext());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.query2.model.ModelDataManager getModelDataManager(){
 if(context.get("modelDataManager")!=null)
 return (nc.ui.pubapp.uif2app.query2.model.ModelDataManager)context.get("modelDataManager");
  nc.ui.pubapp.uif2app.query2.model.ModelDataManager bean = new nc.ui.pubapp.uif2app.query2.model.ModelDataManager();
  context.put("modelDataManager",bean);
  bean.setModel(getManageAppModel());
  bean.setService(getQryService());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.so.m33.pub.view.SettleQryCondDLGInitializer getQryCondDLGInit(){
 if(context.get("QryCondDLGInit")!=null)
 return (nc.ui.so.m33.pub.view.SettleQryCondDLGInitializer)context.get("QryCondDLGInit");
  nc.ui.so.m33.pub.view.SettleQryCondDLGInitializer bean = new nc.ui.so.m33.pub.view.SettleQryCondDLGInitializer();
  context.put("QryCondDLGInit",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor getUIInterceptor(){
 if(context.get("UIInterceptor")!=null)
 return (nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor)context.get("UIInterceptor");
  nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor bean = new nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor();
  context.put("UIInterceptor",bean);
  bean.setShowUpComponent(getListView());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.uif2.editor.TemplateContainer getTemplateContainer(){
 if(context.get("templateContainer")!=null)
 return (nc.ui.uif2.editor.TemplateContainer)context.get("templateContainer");
  nc.ui.uif2.editor.TemplateContainer bean = new nc.ui.uif2.editor.TemplateContainer();
  context.put("templateContainer",bean);
  bean.setContext(getContext());
  bean.load();
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.so.m33.pub.SquareVOListPanelValueSetter getValuesetter(){
 if(context.get("valuesetter")!=null)
 return (nc.ui.so.m33.pub.SquareVOListPanelValueSetter)context.get("valuesetter");
  nc.ui.so.m33.pub.SquareVOListPanelValueSetter bean = new nc.ui.so.m33.pub.SquareVOListPanelValueSetter();
  context.put("valuesetter",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.so.m33.manest.view.M33EstBillListView getListView(){
 if(context.get("listView")!=null)
 return (nc.ui.so.m33.manest.view.M33EstBillListView)context.get("listView");
  nc.ui.so.m33.manest.view.M33EstBillListView bean = new nc.ui.so.m33.manest.view.M33EstBillListView();
  context.put("listView",bean);
  bean.setModel(getManageAppModel());
  bean.setBillListPanelValueSetter(getValuesetter());
  bean.setMultiSelectionEnable(true);
  bean.setMultiSelectionMode(1);
  bean.setTemplateContainer(getTemplateContainer());
  bean.setUserdefitemListPreparator(getUserdefAndMarAsstListPreparator());
  bean.initUI();
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.view.FractionFixMediator getFractionFixMediator(){
 if(context.get("fractionFixMediator")!=null)
 return (nc.ui.pubapp.uif2app.view.FractionFixMediator)context.get("fractionFixMediator");
  nc.ui.pubapp.uif2app.view.FractionFixMediator bean = new nc.ui.pubapp.uif2app.view.FractionFixMediator(getListView());  context.put("fractionFixMediator",bean);
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
  bean.setBillListDataPrepares(getManagedList0());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList0(){  List list = new ArrayList();  list.add(getUserdefitemlistPreparator());  list.add(getMarAsstPreparator());  return list;}

public nc.ui.uif2.editor.UserdefitemBillListDataPreparator getUserdefitemlistPreparator(){
 if(context.get("userdefitemlistPreparator")!=null)
 return (nc.ui.uif2.editor.UserdefitemBillListDataPreparator)context.get("userdefitemlistPreparator");
  nc.ui.uif2.editor.UserdefitemBillListDataPreparator bean = new nc.ui.uif2.editor.UserdefitemBillListDataPreparator(getContext());  context.put("userdefitemlistPreparator",bean);
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
  bean.setMaterialField("cmaterialoid");
  bean.setStoreStateField("cstateid");
  bean.setProjectField("cprojectid");
  bean.setSupplierField("cvendorid");
  bean.setProductorField("cproductorid");
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
  bean.setParams(getManagedList1());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList1(){  List list = new ArrayList();  list.add(getQueryParam_16d7b92());  list.add(getQueryParam_cbaef9());  list.add(getQueryParam_4c7b97());  return list;}

private nc.ui.uif2.userdefitem.QueryParam getQueryParam_16d7b92(){
 if(context.get("nc.ui.uif2.userdefitem.QueryParam#16d7b92")!=null)
 return (nc.ui.uif2.userdefitem.QueryParam)context.get("nc.ui.uif2.userdefitem.QueryParam#16d7b92");
  nc.ui.uif2.userdefitem.QueryParam bean = new nc.ui.uif2.userdefitem.QueryParam();
  context.put("nc.ui.uif2.userdefitem.QueryParam#16d7b92",bean);
  bean.setMdfullname("so.SquareOutHVO");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.userdefitem.QueryParam getQueryParam_cbaef9(){
 if(context.get("nc.ui.uif2.userdefitem.QueryParam#cbaef9")!=null)
 return (nc.ui.uif2.userdefitem.QueryParam)context.get("nc.ui.uif2.userdefitem.QueryParam#cbaef9");
  nc.ui.uif2.userdefitem.QueryParam bean = new nc.ui.uif2.userdefitem.QueryParam();
  context.put("nc.ui.uif2.userdefitem.QueryParam#cbaef9",bean);
  bean.setMdfullname("so.SquareOutBVO");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.userdefitem.QueryParam getQueryParam_4c7b97(){
 if(context.get("nc.ui.uif2.userdefitem.QueryParam#4c7b97")!=null)
 return (nc.ui.uif2.userdefitem.QueryParam)context.get("nc.ui.uif2.userdefitem.QueryParam#4c7b97");
  nc.ui.uif2.userdefitem.QueryParam bean = new nc.ui.uif2.userdefitem.QueryParam();
  context.put("nc.ui.uif2.userdefitem.QueryParam#4c7b97",bean);
  bean.setRulecode("materialassistant");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.uif2.TangramContainer getContainer(){
 if(context.get("container")!=null)
 return (nc.ui.uif2.TangramContainer)context.get("container");
  nc.ui.uif2.TangramContainer bean = new nc.ui.uif2.TangramContainer();
  context.put("container",bean);
  bean.setTangramLayoutRoot(getCNode_145ffe3());
  bean.setActions(getManagedList2());
  bean.initUI();
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.tangramlayout.node.CNode getCNode_145ffe3(){
 if(context.get("nc.ui.uif2.tangramlayout.node.CNode#145ffe3")!=null)
 return (nc.ui.uif2.tangramlayout.node.CNode)context.get("nc.ui.uif2.tangramlayout.node.CNode#145ffe3");
  nc.ui.uif2.tangramlayout.node.CNode bean = new nc.ui.uif2.tangramlayout.node.CNode();
  context.put("nc.ui.uif2.tangramlayout.node.CNode#145ffe3",bean);
  bean.setComponent(getListView());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList2(){  List list = new ArrayList();  list.add(getQueryAction());  list.add(getRefreshAction());  list.add(getSeparatorAction());  list.add(getSquareAction());  list.add(getUnSquareAction());  list.add(getSeparatorAction());  list.add(getPrintActionGroup());  return list;}

public nc.ui.uif2.actions.StandAloneToftPanelActionContainer getActionsOfList(){
 if(context.get("actionsOfList")!=null)
 return (nc.ui.uif2.actions.StandAloneToftPanelActionContainer)context.get("actionsOfList");
  nc.ui.uif2.actions.StandAloneToftPanelActionContainer bean = new nc.ui.uif2.actions.StandAloneToftPanelActionContainer(getListView());  context.put("actionsOfList",bean);
  bean.setActions(getManagedList3());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList3(){  List list = new ArrayList();  return list;}

public nc.ui.so.m33.pub.editor.headevent.after.SquareRowSelectStateChangeHandler getList_head_row_selecthandler(){
 if(context.get("list_head_row_selecthandler")!=null)
 return (nc.ui.so.m33.pub.editor.headevent.after.SquareRowSelectStateChangeHandler)context.get("list_head_row_selecthandler");
  nc.ui.so.m33.pub.editor.headevent.after.SquareRowSelectStateChangeHandler bean = new nc.ui.so.m33.pub.editor.headevent.after.SquareRowSelectStateChangeHandler();
  context.put("list_head_row_selecthandler",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.model.AppEventHandlerMediator getAppEventHandlerMediator(){
 if(context.get("appEventHandlerMediator")!=null)
 return (nc.ui.pubapp.uif2app.model.AppEventHandlerMediator)context.get("appEventHandlerMediator");
  nc.ui.pubapp.uif2app.model.AppEventHandlerMediator bean = new nc.ui.pubapp.uif2app.model.AppEventHandlerMediator();
  context.put("appEventHandlerMediator",bean);
  bean.setModel(getManageAppModel());
  bean.setHandlerMap(getManagedMap0());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private Map getManagedMap0(){  Map map = new HashMap();  map.put("nc.ui.pubapp.uif2app.event.list.ListBodyBeforeEditEvent",getManagedList4());  map.put("nc.ui.pubapp.uif2app.event.list.ListBodyAfterEditEvent",getManagedList5());  return map;}

private List getManagedList4(){  List list = new ArrayList();  list.add(getBodyBeforeEditEventDispatcher_a2bee5());  return list;}

private nc.ui.so.m33.pub.editor.BodyBeforeEditEventDispatcher getBodyBeforeEditEventDispatcher_a2bee5(){
 if(context.get("nc.ui.so.m33.pub.editor.BodyBeforeEditEventDispatcher#a2bee5")!=null)
 return (nc.ui.so.m33.pub.editor.BodyBeforeEditEventDispatcher)context.get("nc.ui.so.m33.pub.editor.BodyBeforeEditEventDispatcher#a2bee5");
  nc.ui.so.m33.pub.editor.BodyBeforeEditEventDispatcher bean = new nc.ui.so.m33.pub.editor.BodyBeforeEditEventDispatcher();
  context.put("nc.ui.so.m33.pub.editor.BodyBeforeEditEventDispatcher#a2bee5",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList5(){  List list = new ArrayList();  list.add(getBodyAfterEditEventDispatcher_a2d5be());  return list;}

private nc.ui.so.m33.pub.editor.BodyAfterEditEventDispatcher getBodyAfterEditEventDispatcher_a2d5be(){
 if(context.get("nc.ui.so.m33.pub.editor.BodyAfterEditEventDispatcher#a2d5be")!=null)
 return (nc.ui.so.m33.pub.editor.BodyAfterEditEventDispatcher)context.get("nc.ui.so.m33.pub.editor.BodyAfterEditEventDispatcher#a2d5be");
  nc.ui.so.m33.pub.editor.BodyAfterEditEventDispatcher bean = new nc.ui.so.m33.pub.editor.BodyAfterEditEventDispatcher();
  context.put("nc.ui.so.m33.pub.editor.BodyAfterEditEventDispatcher#a2d5be",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.so.m33.manest.action.SquareEstAction getSquareAction(){
 if(context.get("squareAction")!=null)
 return (nc.ui.so.m33.manest.action.SquareEstAction)context.get("squareAction");
  nc.ui.so.m33.manest.action.SquareEstAction bean = new nc.ui.so.m33.manest.action.SquareEstAction();
  context.put("squareAction",bean);
  bean.setModel(getManageAppModel());
  bean.setListView(getListView());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.so.m33.manest.action.UnSquareEstAction getUnSquareAction(){
 if(context.get("unSquareAction")!=null)
 return (nc.ui.so.m33.manest.action.UnSquareEstAction)context.get("unSquareAction");
  nc.ui.so.m33.manest.action.UnSquareEstAction bean = new nc.ui.so.m33.manest.action.UnSquareEstAction();
  context.put("unSquareAction",bean);
  bean.setModel(getManageAppModel());
  bean.setListView(getListView());
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

public nc.ui.so.m33.pub.action.SquarePubQueryAction getQueryAction(){
 if(context.get("queryAction")!=null)
 return (nc.ui.so.m33.pub.action.SquarePubQueryAction)context.get("queryAction");
  nc.ui.so.m33.pub.action.SquarePubQueryAction bean = new nc.ui.so.m33.pub.action.SquarePubQueryAction();
  context.put("queryAction",bean);
  bean.setModel(getManageAppModel());
  bean.setDataManager(getModelDataManager());
  bean.setQryCondDLGInitializer(getQryCondDLGInit());
  bean.setTemplateContainer(getQueryTemplateContainer());
  bean.setInterceptor(getUIInterceptor());
  bean.setListView(getListView());
  bean.setService(getQryService());
  bean.setHasQueryArea(false);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.so.m33.pub.action.SquarePubRefreshAction getRefreshAction(){
 if(context.get("refreshAction")!=null)
 return (nc.ui.so.m33.pub.action.SquarePubRefreshAction)context.get("refreshAction");
  nc.ui.so.m33.pub.action.SquarePubRefreshAction bean = new nc.ui.so.m33.pub.action.SquarePubRefreshAction();
  context.put("refreshAction",bean);
  bean.setModel(getManageAppModel());
  bean.setDataManager(getModelDataManager());
  bean.setListView(getListView());
  bean.setService(getQryService());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.funcnode.ui.action.GroupAction getPrintActionGroup(){
 if(context.get("printActionGroup")!=null)
 return (nc.funcnode.ui.action.GroupAction)context.get("printActionGroup");
  nc.funcnode.ui.action.GroupAction bean = new nc.funcnode.ui.action.GroupAction();
  context.put("printActionGroup",bean);
  bean.setCode("printgroup");
  bean.setName(getI18nFB_7ee4aa());
  bean.setActions(getManagedList6());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private java.lang.String getI18nFB_7ee4aa(){
 if(context.get("nc.ui.uif2.I18nFB#7ee4aa")!=null)
 return (java.lang.String)context.get("nc.ui.uif2.I18nFB#7ee4aa");
  nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
    context.put("&nc.ui.uif2.I18nFB#7ee4aa",bean);  bean.setResDir("common");
  bean.setResId("UC001-0000007");
  bean.setDefaultValue("¥Ú”°");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
 try {
     Object product = bean.getObject();
    context.put("nc.ui.uif2.I18nFB#7ee4aa",product);
     return (java.lang.String)product;
}
catch(Exception e) { throw new RuntimeException(e);}}

private List getManagedList6(){  List list = new ArrayList();  list.add(getPrintAction());  list.add(getPreviewAction());  list.add(getOutputAction());  return list;}

public nc.ui.so.m33.pub.SquareOutPutAction getOutputAction(){
 if(context.get("outputAction")!=null)
 return (nc.ui.so.m33.pub.SquareOutPutAction)context.get("outputAction");
  nc.ui.so.m33.pub.SquareOutPutAction bean = new nc.ui.so.m33.pub.SquareOutPutAction();
  context.put("outputAction",bean);
  bean.setModel(getManageAppModel());
  bean.setParent(getListView());
  bean.setListView(getListView());
  bean.setDigitProcessor(getPrintProcessor());
  bean.setValueProcessor(getSquarePrintItemValueProcessor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.so.m33.pub.SquareMasterPrintAction getPreviewAction(){
 if(context.get("previewAction")!=null)
 return (nc.ui.so.m33.pub.SquareMasterPrintAction)context.get("previewAction");
  nc.ui.so.m33.pub.SquareMasterPrintAction bean = new nc.ui.so.m33.pub.SquareMasterPrintAction();
  context.put("previewAction",bean);
  bean.setPreview(true);
  bean.setModel(getManageAppModel());
  bean.setListView(getListView());
  bean.setDigitProcessor(getPrintProcessor());
  bean.setValueProcessor(getSquarePrintItemValueProcessor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.so.m33.pub.SquareMasterPrintAction getPrintAction(){
 if(context.get("printAction")!=null)
 return (nc.ui.so.m33.pub.SquareMasterPrintAction)context.get("printAction");
  nc.ui.so.m33.pub.SquareMasterPrintAction bean = new nc.ui.so.m33.pub.SquareMasterPrintAction();
  context.put("printAction",bean);
  bean.setPreview(false);
  bean.setModel(getManageAppModel());
  bean.setListView(getListView());
  bean.setDigitProcessor(getPrintProcessor());
  bean.setValueProcessor(getSquarePrintItemValueProcessor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.so.m33.pub.SquarePrintItemValueProcessor getSquarePrintItemValueProcessor(){
 if(context.get("SquarePrintItemValueProcessor")!=null)
 return (nc.ui.so.m33.pub.SquarePrintItemValueProcessor)context.get("SquarePrintItemValueProcessor");
  nc.ui.so.m33.pub.SquarePrintItemValueProcessor bean = new nc.ui.so.m33.pub.SquarePrintItemValueProcessor();
  context.put("SquarePrintItemValueProcessor",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.so.m33.pub.SaleOutPrintProcesser getPrintProcessor(){
 if(context.get("printProcessor")!=null)
 return (nc.ui.so.m33.pub.SaleOutPrintProcesser)context.get("printProcessor");
  nc.ui.so.m33.pub.SaleOutPrintProcesser bean = new nc.ui.so.m33.pub.SaleOutPrintProcesser();
  context.put("printProcessor",bean);
  bean.setModel(getManageAppModel());
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
  bean.setVoClassName("nc.vo.so.m33.m4c.entity.SquareOutVO");
  bean.setAutoShowUpComponent(getListView());
  bean.setQueryAction(getQueryAction());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.uif2.editor.UIF2RemoteCallCombinatorCaller getRemoteCallCombinatorCaller(){
 if(context.get("remoteCallCombinatorCaller")!=null)
 return (nc.ui.uif2.editor.UIF2RemoteCallCombinatorCaller)context.get("remoteCallCombinatorCaller");
  nc.ui.uif2.editor.UIF2RemoteCallCombinatorCaller bean = new nc.ui.uif2.editor.UIF2RemoteCallCombinatorCaller();
  context.put("remoteCallCombinatorCaller",bean);
  bean.setRemoteCallers(getManagedList7());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList7(){  List list = new ArrayList();  list.add(getQueryTemplateContainer());  list.add(getTemplateContainer());  return list;}

}
