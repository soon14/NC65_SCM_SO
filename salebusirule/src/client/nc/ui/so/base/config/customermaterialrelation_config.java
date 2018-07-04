package nc.ui.so.base.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.ui.uif2.factory.AbstractJavaBeanDefinition;

public class customermaterialrelation_config extends AbstractJavaBeanDefinition{
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

public nc.ui.so.custmatrel.model.CustMatRelModelService getManageModelService(){
 if(context.get("ManageModelService")!=null)
 return (nc.ui.so.custmatrel.model.CustMatRelModelService)context.get("ManageModelService");
  nc.ui.so.custmatrel.model.CustMatRelModelService bean = new nc.ui.so.custmatrel.model.CustMatRelModelService();
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

public nc.ui.so.custmatrel.model.CustMatRelBillManageModel getManageAppModel(){
 if(context.get("ManageAppModel")!=null)
 return (nc.ui.so.custmatrel.model.CustMatRelBillManageModel)context.get("ManageAppModel");
  nc.ui.so.custmatrel.model.CustMatRelBillManageModel bean = new nc.ui.so.custmatrel.model.CustMatRelBillManageModel();
  context.put("ManageAppModel",bean);
  bean.setService(getManageModelService());
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

public nc.ui.so.custmatrel.model.CustMatRelModelDataManager getModelDataManager(){
 if(context.get("modelDataManager")!=null)
 return (nc.ui.so.custmatrel.model.CustMatRelModelDataManager)context.get("modelDataManager");
  nc.ui.so.custmatrel.model.CustMatRelModelDataManager bean = new nc.ui.so.custmatrel.model.CustMatRelModelDataManager();
  context.put("modelDataManager",bean);
  bean.setModel(getManageAppModel());
  bean.setService(getManageModelService());
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

public nc.ui.so.custmatrel.model.CustMatRelDLGInitializer getQryCondDLGInitializer(){
 if(context.get("qryCondDLGInitializer")!=null)
 return (nc.ui.so.custmatrel.model.CustMatRelDLGInitializer)context.get("qryCondDLGInitializer");
  nc.ui.so.custmatrel.model.CustMatRelDLGInitializer bean = new nc.ui.so.custmatrel.model.CustMatRelDLGInitializer();
  context.put("qryCondDLGInitializer",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.actions.BodyAddLineAction getAddLineAction(){
 if(context.get("AddLineAction")!=null)
 return (nc.ui.pubapp.uif2app.actions.BodyAddLineAction)context.get("AddLineAction");
  nc.ui.pubapp.uif2app.actions.BodyAddLineAction bean = new nc.ui.pubapp.uif2app.actions.BodyAddLineAction();
  context.put("AddLineAction",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.actions.BodyDelLineAction getDelLineAction(){
 if(context.get("DelLineAction")!=null)
 return (nc.ui.pubapp.uif2app.actions.BodyDelLineAction)context.get("DelLineAction");
  nc.ui.pubapp.uif2app.actions.BodyDelLineAction bean = new nc.ui.pubapp.uif2app.actions.BodyDelLineAction();
  context.put("DelLineAction",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.actions.BodyInsertLineAction getInsertLineAction(){
 if(context.get("InsertLineAction")!=null)
 return (nc.ui.pubapp.uif2app.actions.BodyInsertLineAction)context.get("InsertLineAction");
  nc.ui.pubapp.uif2app.actions.BodyInsertLineAction bean = new nc.ui.pubapp.uif2app.actions.BodyInsertLineAction();
  context.put("InsertLineAction",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.actions.BodyCopyLineAction getCopyLineAction(){
 if(context.get("CopyLineAction")!=null)
 return (nc.ui.pubapp.uif2app.actions.BodyCopyLineAction)context.get("CopyLineAction");
  nc.ui.pubapp.uif2app.actions.BodyCopyLineAction bean = new nc.ui.pubapp.uif2app.actions.BodyCopyLineAction();
  context.put("CopyLineAction",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.actions.BodyPasteLineAction getPasteLineAction(){
 if(context.get("PasteLineAction")!=null)
 return (nc.ui.pubapp.uif2app.actions.BodyPasteLineAction)context.get("PasteLineAction");
  nc.ui.pubapp.uif2app.actions.BodyPasteLineAction bean = new nc.ui.pubapp.uif2app.actions.BodyPasteLineAction();
  context.put("PasteLineAction",bean);
  bean.setClearItems(getManagedList0());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList0(){  List list = new ArrayList();  list.add("pk_custmatrel_b");  list.add("ts");  return list;}

public nc.ui.pubapp.uif2app.actions.BodyPasteToTailAction getPasteToTailAction(){
 if(context.get("PasteToTailAction")!=null)
 return (nc.ui.pubapp.uif2app.actions.BodyPasteToTailAction)context.get("PasteToTailAction");
  nc.ui.pubapp.uif2app.actions.BodyPasteToTailAction bean = new nc.ui.pubapp.uif2app.actions.BodyPasteToTailAction();
  context.put("PasteToTailAction",bean);
  bean.setClearItems(getManagedList1());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList1(){  List list = new ArrayList();  list.add("pk_custmatrel_b");  list.add("ts");  return list;}

public nc.ui.so.custmatrel.view.CardForm getBillFormEditor(){
 if(context.get("billFormEditor")!=null)
 return (nc.ui.so.custmatrel.view.CardForm)context.get("billFormEditor");
  nc.ui.so.custmatrel.view.CardForm bean = new nc.ui.so.custmatrel.view.CardForm();
  context.put("billFormEditor",bean);
  bean.setModel(getManageAppModel());
  bean.setTemplateContainer(getTemplateContainer());
  bean.setDataManager(getModelDataManager());
  bean.setTemplateNotNullValidate(true);
  bean.setAutoAddLine(true);
  bean.setBlankChildrenFilter(getMultiFieldsBlankChildrenFilter_1b727bc());
  bean.setBodyLineActions(getManagedList3());
  bean.initUI();
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.pubapp.uif2app.view.value.MultiFieldsBlankChildrenFilter getMultiFieldsBlankChildrenFilter_1b727bc(){
 if(context.get("nc.ui.pubapp.uif2app.view.value.MultiFieldsBlankChildrenFilter#1b727bc")!=null)
 return (nc.ui.pubapp.uif2app.view.value.MultiFieldsBlankChildrenFilter)context.get("nc.ui.pubapp.uif2app.view.value.MultiFieldsBlankChildrenFilter#1b727bc");
  nc.ui.pubapp.uif2app.view.value.MultiFieldsBlankChildrenFilter bean = new nc.ui.pubapp.uif2app.view.value.MultiFieldsBlankChildrenFilter();
  context.put("nc.ui.pubapp.uif2app.view.value.MultiFieldsBlankChildrenFilter#1b727bc",bean);
  bean.setFilterMap(getManagedMap0());
  bean.setNullAssertByOr(false);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private Map getManagedMap0(){  Map map = new HashMap();  map.put("pk_custmatrel_b",getManagedList2());  return map;}

private List getManagedList2(){  List list = new ArrayList();  list.add("pk_materialbaseclass");  list.add("pk_materialsaleclass");  list.add("pk_material_v");  list.add("pk_custsaleclass");  list.add("pk_customer");  list.add("pk_custbaseclass");  return list;}

private List getManagedList3(){  List list = new ArrayList();  list.add(getAddLineAction());  list.add(getInsertLineAction());  list.add(getDelLineAction());  list.add(getCopyLineAction());  list.add(getPasteLineAction());  list.add(getPasteToTailAction());  return list;}

public nc.ui.uif2.FunNodeClosingHandler getClosingListener(){
 if(context.get("ClosingListener")!=null)
 return (nc.ui.uif2.FunNodeClosingHandler)context.get("ClosingListener");
  nc.ui.uif2.FunNodeClosingHandler bean = new nc.ui.uif2.FunNodeClosingHandler();
  context.put("ClosingListener",bean);
  bean.setModel(getManageAppModel());
  bean.setSaveaction(getSaveAction());
  bean.setCancelaction(getCancelAction());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.view.FractionFixMediator getFractionFixMediator_0(){
 if(context.get("nc.ui.pubapp.uif2app.view.FractionFixMediator#0")!=null)
 return (nc.ui.pubapp.uif2app.view.FractionFixMediator)context.get("nc.ui.pubapp.uif2app.view.FractionFixMediator#0");
  nc.ui.pubapp.uif2app.view.FractionFixMediator bean = new nc.ui.pubapp.uif2app.view.FractionFixMediator(getBillFormEditor());  context.put("nc.ui.pubapp.uif2app.view.FractionFixMediator#0",bean);
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
  bean.setModel(getManageAppModel());
  bean.setHandlerMap(getManagedMap1());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private Map getManagedMap1(){  Map map = new HashMap();  map.put("nc.ui.pubapp.uif2app.event.card.CardHeadTailBeforeEditEvent",getManagedList4());  map.put("nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent",getManagedList5());  map.put("nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent",getManagedList6());  map.put("nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent",getManagedList7());  return map;}

private List getManagedList4(){  List list = new ArrayList();  list.add(getHeadBeforeEditEventDispatcher_d78588());  return list;}

private nc.ui.so.custmatrel.eventhandler.HeadBeforeEditEventDispatcher getHeadBeforeEditEventDispatcher_d78588(){
 if(context.get("nc.ui.so.custmatrel.eventhandler.HeadBeforeEditEventDispatcher#d78588")!=null)
 return (nc.ui.so.custmatrel.eventhandler.HeadBeforeEditEventDispatcher)context.get("nc.ui.so.custmatrel.eventhandler.HeadBeforeEditEventDispatcher#d78588");
  nc.ui.so.custmatrel.eventhandler.HeadBeforeEditEventDispatcher bean = new nc.ui.so.custmatrel.eventhandler.HeadBeforeEditEventDispatcher();
  context.put("nc.ui.so.custmatrel.eventhandler.HeadBeforeEditEventDispatcher#d78588",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList5(){  List list = new ArrayList();  list.add(getHeadAfterEditEventDispatcher_17fca1b());  return list;}

private nc.ui.so.custmatrel.eventhandler.HeadAfterEditEventDispatcher getHeadAfterEditEventDispatcher_17fca1b(){
 if(context.get("nc.ui.so.custmatrel.eventhandler.HeadAfterEditEventDispatcher#17fca1b")!=null)
 return (nc.ui.so.custmatrel.eventhandler.HeadAfterEditEventDispatcher)context.get("nc.ui.so.custmatrel.eventhandler.HeadAfterEditEventDispatcher#17fca1b");
  nc.ui.so.custmatrel.eventhandler.HeadAfterEditEventDispatcher bean = new nc.ui.so.custmatrel.eventhandler.HeadAfterEditEventDispatcher();
  context.put("nc.ui.so.custmatrel.eventhandler.HeadAfterEditEventDispatcher#17fca1b",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList6(){  List list = new ArrayList();  list.add(getBodyBeforeEditEventDispatcher_adb882());  return list;}

private nc.ui.so.custmatrel.eventhandler.BodyBeforeEditEventDispatcher getBodyBeforeEditEventDispatcher_adb882(){
 if(context.get("nc.ui.so.custmatrel.eventhandler.BodyBeforeEditEventDispatcher#adb882")!=null)
 return (nc.ui.so.custmatrel.eventhandler.BodyBeforeEditEventDispatcher)context.get("nc.ui.so.custmatrel.eventhandler.BodyBeforeEditEventDispatcher#adb882");
  nc.ui.so.custmatrel.eventhandler.BodyBeforeEditEventDispatcher bean = new nc.ui.so.custmatrel.eventhandler.BodyBeforeEditEventDispatcher();
  context.put("nc.ui.so.custmatrel.eventhandler.BodyBeforeEditEventDispatcher#adb882",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList7(){  List list = new ArrayList();  list.add(getBodyAfterEditEventDispatcher_12c999f());  return list;}

private nc.ui.so.custmatrel.eventhandler.BodyAfterEditEventDispatcher getBodyAfterEditEventDispatcher_12c999f(){
 if(context.get("nc.ui.so.custmatrel.eventhandler.BodyAfterEditEventDispatcher#12c999f")!=null)
 return (nc.ui.so.custmatrel.eventhandler.BodyAfterEditEventDispatcher)context.get("nc.ui.so.custmatrel.eventhandler.BodyAfterEditEventDispatcher#12c999f");
  nc.ui.so.custmatrel.eventhandler.BodyAfterEditEventDispatcher bean = new nc.ui.so.custmatrel.eventhandler.BodyAfterEditEventDispatcher();
  context.put("nc.ui.so.custmatrel.eventhandler.BodyAfterEditEventDispatcher#12c999f",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.uif2.TangramContainer getContainer(){
 if(context.get("container")!=null)
 return (nc.ui.uif2.TangramContainer)context.get("container");
  nc.ui.uif2.TangramContainer bean = new nc.ui.uif2.TangramContainer();
  context.put("container",bean);
  bean.setConstraints(getManagedList8());
  bean.setActions(getManagedList9());
  bean.setEditActions(getManagedList10());
  bean.setModel(getManageAppModel());
  bean.initUI();
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList8(){  List list = new ArrayList();  list.add(getTangramLayoutConstraint_119ccb1());  return list;}

private nc.ui.uif2.tangramlayout.TangramLayoutConstraint getTangramLayoutConstraint_119ccb1(){
 if(context.get("nc.ui.uif2.tangramlayout.TangramLayoutConstraint#119ccb1")!=null)
 return (nc.ui.uif2.tangramlayout.TangramLayoutConstraint)context.get("nc.ui.uif2.tangramlayout.TangramLayoutConstraint#119ccb1");
  nc.ui.uif2.tangramlayout.TangramLayoutConstraint bean = new nc.ui.uif2.tangramlayout.TangramLayoutConstraint();
  context.put("nc.ui.uif2.tangramlayout.TangramLayoutConstraint#119ccb1",bean);
  bean.setNewComponent(getBillFormEditor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList9(){  List list = new ArrayList();  list.add(getAddAction());  list.add(getEditAction());  list.add(getDeleteAction());  list.add(getSeparatorAction());  list.add(getQueryAction());  list.add(getRefreshAction());  list.add(getSeparatorAction());  list.add(getExportActionMenu());  list.add(getActionGroupgt5());  return list;}

private List getManagedList10(){  List list = new ArrayList();  list.add(getSaveAction());  list.add(getSeparatorAction());  list.add(getCancelAction());  return list;}

public nc.ui.so.custmatrel.action.CustMatRelAddAction getAddAction(){
 if(context.get("addAction")!=null)
 return (nc.ui.so.custmatrel.action.CustMatRelAddAction)context.get("addAction");
  nc.ui.so.custmatrel.action.CustMatRelAddAction bean = new nc.ui.so.custmatrel.action.CustMatRelAddAction();
  context.put("addAction",bean);
  bean.setModel(getManageAppModel());
  bean.setBillForm(getBillFormEditor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.so.custmatrel.action.CustMatRelEditAction getEditAction(){
 if(context.get("editAction")!=null)
 return (nc.ui.so.custmatrel.action.CustMatRelEditAction)context.get("editAction");
  nc.ui.so.custmatrel.action.CustMatRelEditAction bean = new nc.ui.so.custmatrel.action.CustMatRelEditAction();
  context.put("editAction",bean);
  bean.setModel(getManageAppModel());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.so.custmatrel.action.CustMatRelDeleteAction getDeleteAction(){
 if(context.get("deleteAction")!=null)
 return (nc.ui.so.custmatrel.action.CustMatRelDeleteAction)context.get("deleteAction");
  nc.ui.so.custmatrel.action.CustMatRelDeleteAction bean = new nc.ui.so.custmatrel.action.CustMatRelDeleteAction();
  context.put("deleteAction",bean);
  bean.setModel(getManageAppModel());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.so.custmatrel.action.CustMatRelSaveAction getSaveAction(){
 if(context.get("saveAction")!=null)
 return (nc.ui.so.custmatrel.action.CustMatRelSaveAction)context.get("saveAction");
  nc.ui.so.custmatrel.action.CustMatRelSaveAction bean = new nc.ui.so.custmatrel.action.CustMatRelSaveAction();
  context.put("saveAction",bean);
  bean.setModel(getManageAppModel());
  bean.setView(getBillFormEditor());
  bean.setEditor(getBillFormEditor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.so.custmatrel.action.CustMatRelCancelAction getCancelAction(){
 if(context.get("cancelAction")!=null)
 return (nc.ui.so.custmatrel.action.CustMatRelCancelAction)context.get("cancelAction");
  nc.ui.so.custmatrel.action.CustMatRelCancelAction bean = new nc.ui.so.custmatrel.action.CustMatRelCancelAction();
  context.put("cancelAction",bean);
  bean.setModel(getManageAppModel());
  bean.setView(getBillFormEditor());
  bean.setEditor(getBillFormEditor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.so.custmatrel.action.CustMatRelRefreshAction getRefreshAction(){
 if(context.get("refreshAction")!=null)
 return (nc.ui.so.custmatrel.action.CustMatRelRefreshAction)context.get("refreshAction");
  nc.ui.so.custmatrel.action.CustMatRelRefreshAction bean = new nc.ui.so.custmatrel.action.CustMatRelRefreshAction();
  context.put("refreshAction",bean);
  bean.setModel(getManageAppModel());
  bean.setDataManager(getModelDataManager());
  bean.setEditor(getBillFormEditor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.so.custmatrel.action.CustMatRelQueryAction getQueryAction(){
 if(context.get("queryAction")!=null)
 return (nc.ui.so.custmatrel.action.CustMatRelQueryAction)context.get("queryAction");
  nc.ui.so.custmatrel.action.CustMatRelQueryAction bean = new nc.ui.so.custmatrel.action.CustMatRelQueryAction();
  context.put("queryAction",bean);
  bean.setModel(getManageAppModel());
  bean.setEditor(getBillFormEditor());
  bean.setDataManager(getModelDataManager());
  bean.setQryCondDLGInitializer(getQryCondDLGInitializer());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.so.custmatrel.action.CustMaterExptableEditor getCustMaterExptableEditor(){
 if(context.get("custMaterExptableEditor")!=null)
 return (nc.ui.so.custmatrel.action.CustMaterExptableEditor)context.get("custMaterExptableEditor");
  nc.ui.so.custmatrel.action.CustMaterExptableEditor bean = new nc.ui.so.custmatrel.action.CustMaterExptableEditor();
  context.put("custMaterExptableEditor",bean);
  bean.setAddAction(getAddAction());
  bean.setCancelAction(getCancelAction());
  bean.setBillcardPanelEditor(getBillFormEditor());
  bean.setSaveAction(getSaveAction());
  bean.setAppModel(getManageAppModel());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.so.custmatrel.action.ExcelExpAction getExcelExportAction(){
 if(context.get("excelExportAction")!=null)
 return (nc.ui.so.custmatrel.action.ExcelExpAction)context.get("excelExportAction");
  nc.ui.so.custmatrel.action.ExcelExpAction bean = new nc.ui.so.custmatrel.action.ExcelExpAction();
  context.put("excelExportAction",bean);
  bean.setModel(getManageAppModel());
  bean.setCardform(getBillFormEditor());
  bean.setImportableEditor(getCustMaterExptableEditor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.so.custmatrel.action.CustMaterImportableEditor getCustMaterImportableEditor(){
 if(context.get("custMaterImportableEditor")!=null)
 return (nc.ui.so.custmatrel.action.CustMaterImportableEditor)context.get("custMaterImportableEditor");
  nc.ui.so.custmatrel.action.CustMaterImportableEditor bean = new nc.ui.so.custmatrel.action.CustMaterImportableEditor();
  context.put("custMaterImportableEditor",bean);
  bean.setAddAction(getAddAction());
  bean.setCancelAction(getCancelAction());
  bean.setBillcardPanelEditor(getBillFormEditor());
  bean.setSaveAction(getSaveAction());
  bean.setAppModel(getManageAppModel());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.so.custmatrel.action.ExcelImpAction getExcelImportAction(){
 if(context.get("excelImportAction")!=null)
 return (nc.ui.so.custmatrel.action.ExcelImpAction)context.get("excelImportAction");
  nc.ui.so.custmatrel.action.ExcelImpAction bean = new nc.ui.so.custmatrel.action.ExcelImpAction();
  context.put("excelImportAction",bean);
  bean.setModel(getManageAppModel());
  bean.setImportableEditor(getCustMaterImportableEditor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.so.custmatrel.action.ExportMenuAction getExportActionMenu(){
 if(context.get("exportActionMenu")!=null)
 return (nc.ui.so.custmatrel.action.ExportMenuAction)context.get("exportActionMenu");
  nc.ui.so.custmatrel.action.ExportMenuAction bean = new nc.ui.so.custmatrel.action.ExportMenuAction();
  context.put("exportActionMenu",bean);
  bean.setActions(getManagedList11());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList11(){  List list = new ArrayList();  list.add(getExcelImportAction());  list.add(getExcelExportAction());  return list;}

public nc.ui.so.base.view.CustMatRelOrgPanel getCustmatrelorgpanel(){
 if(context.get("custmatrelorgpanel")!=null)
 return (nc.ui.so.base.view.CustMatRelOrgPanel)context.get("custmatrelorgpanel");
  nc.ui.so.base.view.CustMatRelOrgPanel bean = new nc.ui.so.base.view.CustMatRelOrgPanel();
  context.put("custmatrelorgpanel",bean);
  bean.setModel(getManageAppModel());
  bean.initUI();
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.so.custmatrel.action.CustMatRelPrintAction getPreviewAction(){
 if(context.get("previewAction")!=null)
 return (nc.ui.so.custmatrel.action.CustMatRelPrintAction)context.get("previewAction");
  nc.ui.so.custmatrel.action.CustMatRelPrintAction bean = new nc.ui.so.custmatrel.action.CustMatRelPrintAction();
  context.put("previewAction",bean);
  bean.setDirectPrint(false);
  bean.setModel(getManageAppModel());
  bean.setEditor(getBillFormEditor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.so.custmatrel.action.CustMatRelPrintAction getPrintAction(){
 if(context.get("printAction")!=null)
 return (nc.ui.so.custmatrel.action.CustMatRelPrintAction)context.get("printAction");
  nc.ui.so.custmatrel.action.CustMatRelPrintAction bean = new nc.ui.so.custmatrel.action.CustMatRelPrintAction();
  context.put("printAction",bean);
  bean.setDirectPrint(true);
  bean.setModel(getManageAppModel());
  bean.setEditor(getBillFormEditor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.funcnode.ui.action.GroupAction getActionGroupgt5(){
 if(context.get("actionGroupgt5")!=null)
 return (nc.funcnode.ui.action.GroupAction)context.get("actionGroupgt5");
  nc.funcnode.ui.action.GroupAction bean = new nc.funcnode.ui.action.GroupAction();
  context.put("actionGroupgt5",bean);
  bean.setCode("printMenuAction");
  bean.setName(getI18nFB_cd97cd());
  bean.setActions(getManagedList12());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private java.lang.String getI18nFB_cd97cd(){
 if(context.get("nc.ui.uif2.I18nFB#cd97cd")!=null)
 return (java.lang.String)context.get("nc.ui.uif2.I18nFB#cd97cd");
  nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
    context.put("&nc.ui.uif2.I18nFB#cd97cd",bean);  bean.setResDir("4006007_0");
  bean.setResId("04006007-0030");
  bean.setDefaultValue("¥Ú”°");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
 try {
     Object product = bean.getObject();
    context.put("nc.ui.uif2.I18nFB#cd97cd",product);
     return (java.lang.String)product;
}
catch(Exception e) { throw new RuntimeException(e);}}

private List getManagedList12(){  List list = new ArrayList();  list.add(getPrintAction());  list.add(getPreviewAction());  return list;}

}
