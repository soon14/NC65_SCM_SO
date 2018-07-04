package nc.ui.so.m30.billui.cash.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.ui.uif2.factory.AbstractJavaBeanDefinition;

public class cashsale_config extends AbstractJavaBeanDefinition{
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

public nc.ui.so.m30.billui.cash.model.CashSaleArsubModelService getCashSaleArsubModelService(){
 if(context.get("CashSaleArsubModelService")!=null)
 return (nc.ui.so.m30.billui.cash.model.CashSaleArsubModelService)context.get("CashSaleArsubModelService");
  nc.ui.so.m30.billui.cash.model.CashSaleArsubModelService bean = new nc.ui.so.m30.billui.cash.model.CashSaleArsubModelService();
  context.put("CashSaleArsubModelService",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.so.m30.billui.cash.model.CashSaleSobalanceModelService getCashSaleSobalanceModelService(){
 if(context.get("CashSaleSobalanceModelService")!=null)
 return (nc.ui.so.m30.billui.cash.model.CashSaleSobalanceModelService)context.get("CashSaleSobalanceModelService");
  nc.ui.so.m30.billui.cash.model.CashSaleSobalanceModelService bean = new nc.ui.so.m30.billui.cash.model.CashSaleSobalanceModelService();
  context.put("CashSaleSobalanceModelService",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.view.value.CAVO2BDObjectAdapterFactory getBoadatorfactory(){
 if(context.get("boadatorfactory")!=null)
 return (nc.ui.pubapp.uif2app.view.value.CAVO2BDObjectAdapterFactory)context.get("boadatorfactory");
  nc.ui.pubapp.uif2app.view.value.CAVO2BDObjectAdapterFactory bean = new nc.ui.pubapp.uif2app.view.value.CAVO2BDObjectAdapterFactory();
  context.put("boadatorfactory",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.so.m30.billui.cash.model.CashSaleArsubModel getArsubModel(){
 if(context.get("arsubModel")!=null)
 return (nc.ui.so.m30.billui.cash.model.CashSaleArsubModel)context.get("arsubModel");
  nc.ui.so.m30.billui.cash.model.CashSaleArsubModel bean = new nc.ui.so.m30.billui.cash.model.CashSaleArsubModel();
  context.put("arsubModel",bean);
  bean.setService(getCashSaleArsubModelService());
  bean.setBusinessObjectAdapterFactory(getBoadatorfactory());
  bean.setContext(getContext());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.so.m30.billui.cash.model.CashSaleSobalanceModel getSobalanceModel(){
 if(context.get("sobalanceModel")!=null)
 return (nc.ui.so.m30.billui.cash.model.CashSaleSobalanceModel)context.get("sobalanceModel");
  nc.ui.so.m30.billui.cash.model.CashSaleSobalanceModel bean = new nc.ui.so.m30.billui.cash.model.CashSaleSobalanceModel();
  context.put("sobalanceModel",bean);
  bean.setService(getCashSaleSobalanceModelService());
  bean.setBusinessObjectAdapterFactory(getBoadatorfactory());
  bean.setContext(getContext());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.so.m30.billui.cash.view.ArsubTemplateContainer getArsubtemplateContainer(){
 if(context.get("ArsubtemplateContainer")!=null)
 return (nc.ui.so.m30.billui.cash.view.ArsubTemplateContainer)context.get("ArsubtemplateContainer");
  nc.ui.so.m30.billui.cash.view.ArsubTemplateContainer bean = new nc.ui.so.m30.billui.cash.view.ArsubTemplateContainer();
  context.put("ArsubtemplateContainer",bean);
  bean.setContext(getContext());
  bean.setNodeKeies(getManagedList0());
  bean.load();
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList0(){  List list = new ArrayList();  list.add("35TO30OR32");  return list;}

public nc.ui.so.m30.billui.cash.view.SobalanceTemplateContainer getSobalancetemplateContainer(){
 if(context.get("SobalancetemplateContainer")!=null)
 return (nc.ui.so.m30.billui.cash.view.SobalanceTemplateContainer)context.get("SobalancetemplateContainer");
  nc.ui.so.m30.billui.cash.view.SobalanceTemplateContainer bean = new nc.ui.so.m30.billui.cash.view.SobalanceTemplateContainer();
  context.put("SobalancetemplateContainer",bean);
  bean.setContext(getContext());
  bean.setNodeKeies(getManagedList1());
  bean.load();
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList1(){  List list = new ArrayList();  list.add("4006039902");  return list;}

public nc.ui.so.m30.billui.cash.view.CashSaleTopPanel getToppanel(){
 if(context.get("toppanel")!=null)
 return (nc.ui.so.m30.billui.cash.view.CashSaleTopPanel)context.get("toppanel");
  nc.ui.so.m30.billui.cash.view.CashSaleTopPanel bean = new nc.ui.so.m30.billui.cash.view.CashSaleTopPanel();
  context.put("toppanel",bean);
  bean.initUI();
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.so.m30.billui.cash.view.CashBillCardPanelBodyVOValueAdapter getComponentValueManager(){
 if(context.get("componentValueManager")!=null)
 return (nc.ui.so.m30.billui.cash.view.CashBillCardPanelBodyVOValueAdapter)context.get("componentValueManager");
  nc.ui.so.m30.billui.cash.view.CashBillCardPanelBodyVOValueAdapter bean = new nc.ui.so.m30.billui.cash.view.CashBillCardPanelBodyVOValueAdapter();
  context.put("componentValueManager",bean);
  bean.setBodyVOName("nc.vo.so.m30.sobalance.entity.SoBalanceViewVO");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.so.m30.billui.cash.view.CashBillCardPanelBodyVOValueAdapter getArsubcomponentValueManager(){
 if(context.get("arsubcomponentValueManager")!=null)
 return (nc.ui.so.m30.billui.cash.view.CashBillCardPanelBodyVOValueAdapter)context.get("arsubcomponentValueManager");
  nc.ui.so.m30.billui.cash.view.CashBillCardPanelBodyVOValueAdapter bean = new nc.ui.so.m30.billui.cash.view.CashBillCardPanelBodyVOValueAdapter();
  context.put("arsubcomponentValueManager",bean);
  bean.setBodyVOName("nc.vo.so.m35.entity.ArsubViewVO");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.so.m30.billui.cash.view.CashSaleArsubView getArsublistView(){
 if(context.get("ArsublistView")!=null)
 return (nc.ui.so.m30.billui.cash.view.CashSaleArsubView)context.get("ArsublistView");
  nc.ui.so.m30.billui.cash.view.CashSaleArsubView bean = new nc.ui.so.m30.billui.cash.view.CashSaleArsubView();
  context.put("ArsublistView",bean);
  bean.setModel(getArsubModel());
  bean.setTemplateContainer(getArsubtemplateContainer());
  bean.setNodekey("35TO30OR32");
  bean.setComponentValueManager(getArsubcomponentValueManager());
  bean.setVoClassName("nc.vo.so.m35.entity.ArsubViewVO");
  bean.setIsBodyAutoAddLine(false);
  bean.setBodyMultiSelectEnable(true);
  bean.initUI();
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.so.m30.billui.cash.view.CashSaleSobalanceView getSobalanceListView(){
 if(context.get("SobalanceListView")!=null)
 return (nc.ui.so.m30.billui.cash.view.CashSaleSobalanceView)context.get("SobalanceListView");
  nc.ui.so.m30.billui.cash.view.CashSaleSobalanceView bean = new nc.ui.so.m30.billui.cash.view.CashSaleSobalanceView();
  context.put("SobalanceListView",bean);
  bean.setModel(getSobalanceModel());
  bean.setTemplateContainer(getSobalancetemplateContainer());
  bean.setNodekey("4006039902");
  bean.setComponentValueManager(getComponentValueManager());
  bean.setVoClassName("nc.vo.so.m30.sobalance.entity.SoBalanceViewVO");
  bean.setIsBodyAutoAddLine(false);
  bean.setBodyMultiSelectEnable(true);
  bean.initUI();
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.model.AppEventHandlerMediator getAppEventHandlerMediator(){
 if(context.get("appEventHandlerMediator")!=null)
 return (nc.ui.pubapp.uif2app.model.AppEventHandlerMediator)context.get("appEventHandlerMediator");
  nc.ui.pubapp.uif2app.model.AppEventHandlerMediator bean = new nc.ui.pubapp.uif2app.model.AppEventHandlerMediator();
  context.put("appEventHandlerMediator",bean);
  bean.setModel(getArsubModel());
  bean.setHandlerMap(getManagedMap0());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private Map getManagedMap0(){  Map map = new HashMap();  map.put("nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent",getManagedList2());  return map;}

private List getManagedList2(){  List list = new ArrayList();  list.add(getArsubBodyAfterEditHandler_1fe88eb());  return list;}

private nc.ui.so.m30.billui.cash.editor.bodyevent.ArsubBodyAfterEditHandler getArsubBodyAfterEditHandler_1fe88eb(){
 if(context.get("nc.ui.so.m30.billui.cash.editor.bodyevent.ArsubBodyAfterEditHandler#1fe88eb")!=null)
 return (nc.ui.so.m30.billui.cash.editor.bodyevent.ArsubBodyAfterEditHandler)context.get("nc.ui.so.m30.billui.cash.editor.bodyevent.ArsubBodyAfterEditHandler#1fe88eb");
  nc.ui.so.m30.billui.cash.editor.bodyevent.ArsubBodyAfterEditHandler bean = new nc.ui.so.m30.billui.cash.editor.bodyevent.ArsubBodyAfterEditHandler();
  context.put("nc.ui.so.m30.billui.cash.editor.bodyevent.ArsubBodyAfterEditHandler#1fe88eb",bean);
  bean.setTopPanel(getToppanel());
  bean.setArsubModel(getArsubModel());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.model.AppEventHandlerMediator getAappEventHandlerMediator(){
 if(context.get("aappEventHandlerMediator")!=null)
 return (nc.ui.pubapp.uif2app.model.AppEventHandlerMediator)context.get("aappEventHandlerMediator");
  nc.ui.pubapp.uif2app.model.AppEventHandlerMediator bean = new nc.ui.pubapp.uif2app.model.AppEventHandlerMediator();
  context.put("aappEventHandlerMediator",bean);
  bean.setModel(getSobalanceModel());
  bean.setHandlerMap(getManagedMap1());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private Map getManagedMap1(){  Map map = new HashMap();  map.put("nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent",getManagedList3());  return map;}

private List getManagedList3(){  List list = new ArrayList();  list.add(getSobalanceBodyAfterEditHandler_1cf1905());  return list;}

private nc.ui.so.m30.billui.cash.editor.bodyevent.SobalanceBodyAfterEditHandler getSobalanceBodyAfterEditHandler_1cf1905(){
 if(context.get("nc.ui.so.m30.billui.cash.editor.bodyevent.SobalanceBodyAfterEditHandler#1cf1905")!=null)
 return (nc.ui.so.m30.billui.cash.editor.bodyevent.SobalanceBodyAfterEditHandler)context.get("nc.ui.so.m30.billui.cash.editor.bodyevent.SobalanceBodyAfterEditHandler#1cf1905");
  nc.ui.so.m30.billui.cash.editor.bodyevent.SobalanceBodyAfterEditHandler bean = new nc.ui.so.m30.billui.cash.editor.bodyevent.SobalanceBodyAfterEditHandler();
  context.put("nc.ui.so.m30.billui.cash.editor.bodyevent.SobalanceBodyAfterEditHandler#1cf1905",bean);
  bean.setTopPanel(getToppanel());
  bean.setSobalanceModel(getSobalanceModel());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.uif2.TangramContainer getContainer(){
 if(context.get("container")!=null)
 return (nc.ui.uif2.TangramContainer)context.get("container");
  nc.ui.uif2.TangramContainer bean = new nc.ui.uif2.TangramContainer();
  context.put("container",bean);
  bean.setTangramLayoutRoot(getVSNode_1182672());
  bean.setActions(getManagedList6());
  bean.initUI();
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.tangramlayout.node.VSNode getVSNode_1182672(){
 if(context.get("nc.ui.uif2.tangramlayout.node.VSNode#1182672")!=null)
 return (nc.ui.uif2.tangramlayout.node.VSNode)context.get("nc.ui.uif2.tangramlayout.node.VSNode#1182672");
  nc.ui.uif2.tangramlayout.node.VSNode bean = new nc.ui.uif2.tangramlayout.node.VSNode();
  context.put("nc.ui.uif2.tangramlayout.node.VSNode#1182672",bean);
  bean.setDividerLocation(0.1f);
  bean.setUp(getCNode_10c3368());
  bean.setDown(getVSNode_1953dfa());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.tangramlayout.node.CNode getCNode_10c3368(){
 if(context.get("nc.ui.uif2.tangramlayout.node.CNode#10c3368")!=null)
 return (nc.ui.uif2.tangramlayout.node.CNode)context.get("nc.ui.uif2.tangramlayout.node.CNode#10c3368");
  nc.ui.uif2.tangramlayout.node.CNode bean = new nc.ui.uif2.tangramlayout.node.CNode();
  context.put("nc.ui.uif2.tangramlayout.node.CNode#10c3368",bean);
  bean.setName(getI18nFB_173abae());
  bean.setComponent(getToppanel());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private java.lang.String getI18nFB_173abae(){
 if(context.get("nc.ui.uif2.I18nFB#173abae")!=null)
 return (java.lang.String)context.get("nc.ui.uif2.I18nFB#173abae");
  nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
    context.put("&nc.ui.uif2.I18nFB#173abae",bean);  bean.setResDir("4006011_0");
  bean.setResId("04006011-0463");
  bean.setDefaultValue("现销处理");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
 try {
     Object product = bean.getObject();
    context.put("nc.ui.uif2.I18nFB#173abae",product);
     return (java.lang.String)product;
}
catch(Exception e) { throw new RuntimeException(e);}}

private nc.ui.uif2.tangramlayout.node.VSNode getVSNode_1953dfa(){
 if(context.get("nc.ui.uif2.tangramlayout.node.VSNode#1953dfa")!=null)
 return (nc.ui.uif2.tangramlayout.node.VSNode)context.get("nc.ui.uif2.tangramlayout.node.VSNode#1953dfa");
  nc.ui.uif2.tangramlayout.node.VSNode bean = new nc.ui.uif2.tangramlayout.node.VSNode();
  context.put("nc.ui.uif2.tangramlayout.node.VSNode#1953dfa",bean);
  bean.setDividerLocation(0.5f);
  bean.setUp(getTBNode_137c32e());
  bean.setDown(getTBNode_1db6063());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.tangramlayout.node.TBNode getTBNode_137c32e(){
 if(context.get("nc.ui.uif2.tangramlayout.node.TBNode#137c32e")!=null)
 return (nc.ui.uif2.tangramlayout.node.TBNode)context.get("nc.ui.uif2.tangramlayout.node.TBNode#137c32e");
  nc.ui.uif2.tangramlayout.node.TBNode bean = new nc.ui.uif2.tangramlayout.node.TBNode();
  context.put("nc.ui.uif2.tangramlayout.node.TBNode#137c32e",bean);
  bean.setTabs(getManagedList4());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList4(){  List list = new ArrayList();  list.add(getCNode_1f17cca());  return list;}

private nc.ui.uif2.tangramlayout.node.CNode getCNode_1f17cca(){
 if(context.get("nc.ui.uif2.tangramlayout.node.CNode#1f17cca")!=null)
 return (nc.ui.uif2.tangramlayout.node.CNode)context.get("nc.ui.uif2.tangramlayout.node.CNode#1f17cca");
  nc.ui.uif2.tangramlayout.node.CNode bean = new nc.ui.uif2.tangramlayout.node.CNode();
  context.put("nc.ui.uif2.tangramlayout.node.CNode#1f17cca",bean);
  bean.setName(getI18nFB_303d8());
  bean.setComponent(getArsublistView());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private java.lang.String getI18nFB_303d8(){
 if(context.get("nc.ui.uif2.I18nFB#303d8")!=null)
 return (java.lang.String)context.get("nc.ui.uif2.I18nFB#303d8");
  nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
    context.put("&nc.ui.uif2.I18nFB#303d8",bean);  bean.setResDir("40060304");
  bean.setResId("1400603040015");
  bean.setDefaultValue("费用冲抵");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
 try {
     Object product = bean.getObject();
    context.put("nc.ui.uif2.I18nFB#303d8",product);
     return (java.lang.String)product;
}
catch(Exception e) { throw new RuntimeException(e);}}

private nc.ui.uif2.tangramlayout.node.TBNode getTBNode_1db6063(){
 if(context.get("nc.ui.uif2.tangramlayout.node.TBNode#1db6063")!=null)
 return (nc.ui.uif2.tangramlayout.node.TBNode)context.get("nc.ui.uif2.tangramlayout.node.TBNode#1db6063");
  nc.ui.uif2.tangramlayout.node.TBNode bean = new nc.ui.uif2.tangramlayout.node.TBNode();
  context.put("nc.ui.uif2.tangramlayout.node.TBNode#1db6063",bean);
  bean.setTabs(getManagedList5());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList5(){  List list = new ArrayList();  list.add(getCNode_19651e8());  return list;}

private nc.ui.uif2.tangramlayout.node.CNode getCNode_19651e8(){
 if(context.get("nc.ui.uif2.tangramlayout.node.CNode#19651e8")!=null)
 return (nc.ui.uif2.tangramlayout.node.CNode)context.get("nc.ui.uif2.tangramlayout.node.CNode#19651e8");
  nc.ui.uif2.tangramlayout.node.CNode bean = new nc.ui.uif2.tangramlayout.node.CNode();
  context.put("nc.ui.uif2.tangramlayout.node.CNode#19651e8",bean);
  bean.setName(getI18nFB_1ef3d21());
  bean.setComponent(getSobalanceListView());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private java.lang.String getI18nFB_1ef3d21(){
 if(context.get("nc.ui.uif2.I18nFB#1ef3d21")!=null)
 return (java.lang.String)context.get("nc.ui.uif2.I18nFB#1ef3d21");
  nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
    context.put("&nc.ui.uif2.I18nFB#1ef3d21",bean);  bean.setResDir("40060304");
  bean.setResId("1400603040016");
  bean.setDefaultValue("收款核销");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
 try {
     Object product = bean.getObject();
    context.put("nc.ui.uif2.I18nFB#1ef3d21",product);
     return (java.lang.String)product;
}
catch(Exception e) { throw new RuntimeException(e);}}

private List getManagedList6(){  List list = new ArrayList();  list.add(getConfirmAction());  list.add(getCancelAction());  return list;}

public nc.ui.so.m30.billui.cash.actions.ConfirmAction getConfirmAction(){
 if(context.get("ConfirmAction")!=null)
 return (nc.ui.so.m30.billui.cash.actions.ConfirmAction)context.get("ConfirmAction");
  nc.ui.so.m30.billui.cash.actions.ConfirmAction bean = new nc.ui.so.m30.billui.cash.actions.ConfirmAction();
  context.put("ConfirmAction",bean);
  bean.setCode("confirmAction");
  bean.setBtnName(getI18nFB_842199());
  bean.setArsublistView(getArsublistView());
  bean.setSobalanceListView(getSobalanceListView());
  bean.setArsubmodel(getArsubModel());
  bean.setSobalancemodel(getSobalanceModel());
  bean.setToppanel(getToppanel());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private java.lang.String getI18nFB_842199(){
 if(context.get("nc.ui.uif2.I18nFB#842199")!=null)
 return (java.lang.String)context.get("nc.ui.uif2.I18nFB#842199");
  nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
    context.put("&nc.ui.uif2.I18nFB#842199",bean);  bean.setResDir("40060304");
  bean.setResId("1400603040017");
  bean.setDefaultValue("确定");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
 try {
     Object product = bean.getObject();
    context.put("nc.ui.uif2.I18nFB#842199",product);
     return (java.lang.String)product;
}
catch(Exception e) { throw new RuntimeException(e);}}

public nc.ui.so.m30.billui.cash.actions.CancelAction getCancelAction(){
 if(context.get("CancelAction")!=null)
 return (nc.ui.so.m30.billui.cash.actions.CancelAction)context.get("CancelAction");
  nc.ui.so.m30.billui.cash.actions.CancelAction bean = new nc.ui.so.m30.billui.cash.actions.CancelAction();
  context.put("CancelAction",bean);
  bean.setArsublistView(getArsublistView());
  bean.setSobalanceListView(getSobalanceListView());
  bean.setArsubmodel(getArsubModel());
  bean.setSobalancemodel(getSobalanceModel());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.so.m30.billui.cash.editor.listener.FuncNodeInitDataHandler getInitDataListener(){
 if(context.get("InitDataListener")!=null)
 return (nc.ui.so.m30.billui.cash.editor.listener.FuncNodeInitDataHandler)context.get("InitDataListener");
  nc.ui.so.m30.billui.cash.editor.listener.FuncNodeInitDataHandler bean = new nc.ui.so.m30.billui.cash.editor.listener.FuncNodeInitDataHandler();
  context.put("InitDataListener",bean);
  bean.setArsubmodel(getArsubModel());
  bean.setSobalancemodel(getSobalanceModel());
  bean.setToppanel(getToppanel());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

}
