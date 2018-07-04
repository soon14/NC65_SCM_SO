package nc.ui.so.m33.pub.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.ui.uif2.factory.AbstractJavaBeanDefinition;

public class saleoutUnSettle_config extends AbstractJavaBeanDefinition{
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

public nc.ui.so.pub.closingcheck.ClosingCheckModelService getModelService(){
 if(context.get("modelService")!=null)
 return (nc.ui.so.pub.closingcheck.ClosingCheckModelService)context.get("modelService");
  nc.ui.so.pub.closingcheck.ClosingCheckModelService bean = new nc.ui.so.pub.closingcheck.ClosingCheckModelService();
  context.put("modelService",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.so.pub.closingcheck.ClosingCheckObjectAdpaterFactory getBoadatorfactory(){
 if(context.get("boadatorfactory")!=null)
 return (nc.ui.so.pub.closingcheck.ClosingCheckObjectAdpaterFactory)context.get("boadatorfactory");
  nc.ui.so.pub.closingcheck.ClosingCheckObjectAdpaterFactory bean = new nc.ui.so.pub.closingcheck.ClosingCheckObjectAdpaterFactory();
  context.put("boadatorfactory",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.so.m33.closingcheck.IA2013Model getManageAppModel(){
 if(context.get("manageAppModel")!=null)
 return (nc.ui.so.m33.closingcheck.IA2013Model)context.get("manageAppModel");
  nc.ui.so.m33.closingcheck.IA2013Model bean = new nc.ui.so.m33.closingcheck.IA2013Model();
  context.put("manageAppModel",bean);
  bean.setContext(getContext());
  bean.setBusinessObjectAdapterFactory(getBoadatorfactory());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.so.pub.closingcheck.IABatchTable getListView(){
 if(context.get("listView")!=null)
 return (nc.ui.so.pub.closingcheck.IABatchTable)context.get("listView");
  nc.ui.so.pub.closingcheck.IABatchTable bean = new nc.ui.so.pub.closingcheck.IABatchTable();
  context.put("listView",bean);
  bean.setModel(getManageAppModel());
  bean.setVoClassName("nc.vo.ic.m4c.entity.SaleOutHeadVO");
  bean.setIsBodyAutoAddLine(false);
  bean.setBodyMultiSelectEnable(true);
  bean.setNodekey("query");
  bean.initUI();
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.funcnode.ui.action.GroupAction getPrintGroupAction(){
 if(context.get("printGroupAction")!=null)
 return (nc.funcnode.ui.action.GroupAction)context.get("printGroupAction");
  nc.funcnode.ui.action.GroupAction bean = new nc.funcnode.ui.action.GroupAction();
  context.put("printGroupAction",bean);
  bean.setCode("print");
  bean.setName(getI18nFB_e7c5a5());
  bean.setActions(getManagedList0());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private java.lang.String getI18nFB_e7c5a5(){
 if(context.get("nc.ui.uif2.I18nFB#e7c5a5")!=null)
 return (java.lang.String)context.get("nc.ui.uif2.I18nFB#e7c5a5");
  nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
    context.put("&nc.ui.uif2.I18nFB#e7c5a5",bean);  bean.setResDir("10140curtp");
  bean.setDefaultValue("¥Ú”°");
  bean.setResId("010140curtp0011");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
 try {
     Object product = bean.getObject();
    context.put("nc.ui.uif2.I18nFB#e7c5a5",product);
     return (java.lang.String)product;
}
catch(Exception e) { throw new RuntimeException(e);}}

private List getManagedList0(){  List list = new ArrayList();  list.add(getPrintAction());  list.add(getPreViewAction());  list.add(getOutputAction());  return list;}

public nc.ui.so.m33.closingcheck.ClosePrintAction getPreViewAction(){
 if(context.get("preViewAction")!=null)
 return (nc.ui.so.m33.closingcheck.ClosePrintAction)context.get("preViewAction");
  nc.ui.so.m33.closingcheck.ClosePrintAction bean = new nc.ui.so.m33.closingcheck.ClosePrintAction();
  context.put("preViewAction",bean);
  bean.setDirectPrint(true);
  bean.setModel(getManageAppModel());
  bean.setEditor(getListView());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.so.m33.closingcheck.ClosePrintAction getPrintAction(){
 if(context.get("printAction")!=null)
 return (nc.ui.so.m33.closingcheck.ClosePrintAction)context.get("printAction");
  nc.ui.so.m33.closingcheck.ClosePrintAction bean = new nc.ui.so.m33.closingcheck.ClosePrintAction();
  context.put("printAction",bean);
  bean.setDirectPrint(true);
  bean.setModel(getManageAppModel());
  bean.setEditor(getListView());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.so.pub.closingcheck.CloseOutPrintAction getOutputAction(){
 if(context.get("outputAction")!=null)
 return (nc.ui.so.pub.closingcheck.CloseOutPrintAction)context.get("outputAction");
  nc.ui.so.pub.closingcheck.CloseOutPrintAction bean = new nc.ui.so.pub.closingcheck.CloseOutPrintAction(getPrintAction());  context.put("outputAction",bean);
  bean.setModel(getManageAppModel());
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

public nc.ui.uif2.TangramContainer getContainer(){
 if(context.get("container")!=null)
 return (nc.ui.uif2.TangramContainer)context.get("container");
  nc.ui.uif2.TangramContainer bean = new nc.ui.uif2.TangramContainer();
  context.put("container",bean);
  bean.setTangramLayoutRoot(getVsnode());
  bean.setActions(getManagedList2());
  bean.initUI();
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.tangramlayout.node.TBNode getVsnode(){
 if(context.get("vsnode")!=null)
 return (nc.ui.uif2.tangramlayout.node.TBNode)context.get("vsnode");
  nc.ui.uif2.tangramlayout.node.TBNode bean = new nc.ui.uif2.tangramlayout.node.TBNode();
  context.put("vsnode",bean);
  bean.setShowMode("CardLayout");
  bean.setTabs(getManagedList1());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList1(){  List list = new ArrayList();  list.add(getCNode_60e16f());  return list;}

private nc.ui.uif2.tangramlayout.node.CNode getCNode_60e16f(){
 if(context.get("nc.ui.uif2.tangramlayout.node.CNode#60e16f")!=null)
 return (nc.ui.uif2.tangramlayout.node.CNode)context.get("nc.ui.uif2.tangramlayout.node.CNode#60e16f");
  nc.ui.uif2.tangramlayout.node.CNode bean = new nc.ui.uif2.tangramlayout.node.CNode();
  context.put("nc.ui.uif2.tangramlayout.node.CNode#60e16f",bean);
  bean.setComponent(getListView());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList2(){  List list = new ArrayList();  list.add(getSeparatorAction());  list.add(getCheckAction());  list.add(getPrintGroupAction());  return list;}

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

public nc.ui.org.closeaccbook.check.CheckAction getCheckAction(){
 if(context.get("checkAction")!=null)
 return (nc.ui.org.closeaccbook.check.CheckAction)context.get("checkAction");
  nc.ui.org.closeaccbook.check.CheckAction bean = new nc.ui.org.closeaccbook.check.CheckAction();
  context.put("checkAction",bean);
  bean.setModel(getManageAppModel());
  bean.setCode("000222000");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.uif2.actions.ActionContributors getToftpanelActionContributors(){
 if(context.get("toftpanelActionContributors")!=null)
 return (nc.ui.uif2.actions.ActionContributors)context.get("toftpanelActionContributors");
  nc.ui.uif2.actions.ActionContributors bean = new nc.ui.uif2.actions.ActionContributors();
  context.put("toftpanelActionContributors",bean);
  bean.setContributors(getManagedList3());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList3(){  List list = new ArrayList();  list.add((nc.ui.uif2.actions.IActionContributor)findBeanInUIF2BeanFactory("actionsOfQueryUI"));  return list;}

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
  bean.setRemoteCallers(getManagedList4());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList4(){  List list = new ArrayList();  list.add(getQueryTemplateContainer());  list.add(getTemplateContainer());  return list;}

}
