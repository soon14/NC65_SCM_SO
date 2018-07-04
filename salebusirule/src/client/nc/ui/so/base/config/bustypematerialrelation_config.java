package nc.ui.so.base.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.ui.uif2.factory.AbstractJavaBeanDefinition;

public class bustypematerialrelation_config extends AbstractJavaBeanDefinition{
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

public nc.ui.so.tranmatrel.model.TranMatRelModelService getManageModelService(){
 if(context.get("ManageModelService")!=null)
 return (nc.ui.so.tranmatrel.model.TranMatRelModelService)context.get("ManageModelService");
  nc.ui.so.tranmatrel.model.TranMatRelModelService bean = new nc.ui.so.tranmatrel.model.TranMatRelModelService();
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

public nc.ui.so.tranmatrel.model.TranMatRelBillManageModel getManageAppModel(){
 if(context.get("ManageAppModel")!=null)
 return (nc.ui.so.tranmatrel.model.TranMatRelBillManageModel)context.get("ManageAppModel");
  nc.ui.so.tranmatrel.model.TranMatRelBillManageModel bean = new nc.ui.so.tranmatrel.model.TranMatRelBillManageModel();
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

public nc.ui.so.tranmatrel.model.TranMatRelDataManager getModelDataManager(){
 if(context.get("modelDataManager")!=null)
 return (nc.ui.so.tranmatrel.model.TranMatRelDataManager)context.get("modelDataManager");
  nc.ui.so.tranmatrel.model.TranMatRelDataManager bean = new nc.ui.so.tranmatrel.model.TranMatRelDataManager();
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

private List getManagedList0(){  List list = new ArrayList();  list.add("pk_tranmatrel_b");  list.add("ts");  return list;}

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

private List getManagedList1(){  List list = new ArrayList();  list.add("pk_tranmatrel_b");  list.add("ts");  return list;}

public nc.ui.so.tranmatrel.view.CardForm getBillFormEditor(){
 if(context.get("billFormEditor")!=null)
 return (nc.ui.so.tranmatrel.view.CardForm)context.get("billFormEditor");
  nc.ui.so.tranmatrel.view.CardForm bean = new nc.ui.so.tranmatrel.view.CardForm();
  context.put("billFormEditor",bean);
  bean.setModel(getManageAppModel());
  bean.setTemplateContainer(getTemplateContainer());
  bean.setDataManager(getModelDataManager());
  bean.setTemplateNotNullValidate(true);
  bean.setAutoAddLine(true);
  bean.setBlankChildrenFilter(getMultiFieldsBlankChildrenFilter_f37de3());
  bean.setBodyLineActions(getManagedList3());
  bean.initUI();
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.pubapp.uif2app.view.value.MultiFieldsBlankChildrenFilter getMultiFieldsBlankChildrenFilter_f37de3(){
 if(context.get("nc.ui.pubapp.uif2app.view.value.MultiFieldsBlankChildrenFilter#f37de3")!=null)
 return (nc.ui.pubapp.uif2app.view.value.MultiFieldsBlankChildrenFilter)context.get("nc.ui.pubapp.uif2app.view.value.MultiFieldsBlankChildrenFilter#f37de3");
  nc.ui.pubapp.uif2app.view.value.MultiFieldsBlankChildrenFilter bean = new nc.ui.pubapp.uif2app.view.value.MultiFieldsBlankChildrenFilter();
  context.put("nc.ui.pubapp.uif2app.view.value.MultiFieldsBlankChildrenFilter#f37de3",bean);
  bean.setFilterMap(getManagedMap0());
  bean.setNullAssertByOr(false);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private Map getManagedMap0(){  Map map = new HashMap();  map.put("pk_tranmatrel_b",getManagedList2());  return map;}

private List getManagedList2(){  List list = new ArrayList();  list.add("pk_materialbaseclass");  list.add("pk_materialsaleclass");  list.add("pk_material_v");  list.add("trantype");  return list;}

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

private List getManagedList4(){  List list = new ArrayList();  list.add(getHeadBeforeEditEventDispatcher_1e79aa());  return list;}

private nc.ui.so.tranmatrel.eventhandler.HeadBeforeEditEventDispatcher getHeadBeforeEditEventDispatcher_1e79aa(){
 if(context.get("nc.ui.so.tranmatrel.eventhandler.HeadBeforeEditEventDispatcher#1e79aa")!=null)
 return (nc.ui.so.tranmatrel.eventhandler.HeadBeforeEditEventDispatcher)context.get("nc.ui.so.tranmatrel.eventhandler.HeadBeforeEditEventDispatcher#1e79aa");
  nc.ui.so.tranmatrel.eventhandler.HeadBeforeEditEventDispatcher bean = new nc.ui.so.tranmatrel.eventhandler.HeadBeforeEditEventDispatcher();
  context.put("nc.ui.so.tranmatrel.eventhandler.HeadBeforeEditEventDispatcher#1e79aa",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList5(){  List list = new ArrayList();  list.add(getHeadAfterEditEventDispatcher_c5954b());  return list;}

private nc.ui.so.tranmatrel.eventhandler.HeadAfterEditEventDispatcher getHeadAfterEditEventDispatcher_c5954b(){
 if(context.get("nc.ui.so.tranmatrel.eventhandler.HeadAfterEditEventDispatcher#c5954b")!=null)
 return (nc.ui.so.tranmatrel.eventhandler.HeadAfterEditEventDispatcher)context.get("nc.ui.so.tranmatrel.eventhandler.HeadAfterEditEventDispatcher#c5954b");
  nc.ui.so.tranmatrel.eventhandler.HeadAfterEditEventDispatcher bean = new nc.ui.so.tranmatrel.eventhandler.HeadAfterEditEventDispatcher();
  context.put("nc.ui.so.tranmatrel.eventhandler.HeadAfterEditEventDispatcher#c5954b",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList6(){  List list = new ArrayList();  list.add(getBodyBeforeEditEventDispatcher_1cc9339());  return list;}

private nc.ui.so.tranmatrel.eventhandler.BodyBeforeEditEventDispatcher getBodyBeforeEditEventDispatcher_1cc9339(){
 if(context.get("nc.ui.so.tranmatrel.eventhandler.BodyBeforeEditEventDispatcher#1cc9339")!=null)
 return (nc.ui.so.tranmatrel.eventhandler.BodyBeforeEditEventDispatcher)context.get("nc.ui.so.tranmatrel.eventhandler.BodyBeforeEditEventDispatcher#1cc9339");
  nc.ui.so.tranmatrel.eventhandler.BodyBeforeEditEventDispatcher bean = new nc.ui.so.tranmatrel.eventhandler.BodyBeforeEditEventDispatcher();
  context.put("nc.ui.so.tranmatrel.eventhandler.BodyBeforeEditEventDispatcher#1cc9339",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList7(){  List list = new ArrayList();  list.add(getBodyAfterEditEventDispatcher_1d5af30());  return list;}

private nc.ui.so.tranmatrel.eventhandler.BodyAfterEditEventDispatcher getBodyAfterEditEventDispatcher_1d5af30(){
 if(context.get("nc.ui.so.tranmatrel.eventhandler.BodyAfterEditEventDispatcher#1d5af30")!=null)
 return (nc.ui.so.tranmatrel.eventhandler.BodyAfterEditEventDispatcher)context.get("nc.ui.so.tranmatrel.eventhandler.BodyAfterEditEventDispatcher#1d5af30");
  nc.ui.so.tranmatrel.eventhandler.BodyAfterEditEventDispatcher bean = new nc.ui.so.tranmatrel.eventhandler.BodyAfterEditEventDispatcher();
  context.put("nc.ui.so.tranmatrel.eventhandler.BodyAfterEditEventDispatcher#1d5af30",bean);
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

private List getManagedList8(){  List list = new ArrayList();  list.add(getTangramLayoutConstraint_bf837a());  return list;}

private nc.ui.uif2.tangramlayout.TangramLayoutConstraint getTangramLayoutConstraint_bf837a(){
 if(context.get("nc.ui.uif2.tangramlayout.TangramLayoutConstraint#bf837a")!=null)
 return (nc.ui.uif2.tangramlayout.TangramLayoutConstraint)context.get("nc.ui.uif2.tangramlayout.TangramLayoutConstraint#bf837a");
  nc.ui.uif2.tangramlayout.TangramLayoutConstraint bean = new nc.ui.uif2.tangramlayout.TangramLayoutConstraint();
  context.put("nc.ui.uif2.tangramlayout.TangramLayoutConstraint#bf837a",bean);
  bean.setNewComponent(getBillFormEditor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList9(){  List list = new ArrayList();  list.add(getAddAction());  list.add(getEditAction());  list.add(getDeleteAction());  list.add(getSeparatorAction());  list.add(getRefreshAction());  list.add(getSeparatorAction());  list.add(getActionGroupgt5());  return list;}

private List getManagedList10(){  List list = new ArrayList();  list.add(getSaveAction());  list.add(getSeparatorAction());  list.add(getCancelAction());  return list;}

public nc.ui.so.tranmatrel.action.TranMatRelAddAction getAddAction(){
 if(context.get("addAction")!=null)
 return (nc.ui.so.tranmatrel.action.TranMatRelAddAction)context.get("addAction");
  nc.ui.so.tranmatrel.action.TranMatRelAddAction bean = new nc.ui.so.tranmatrel.action.TranMatRelAddAction();
  context.put("addAction",bean);
  bean.setModel(getManageAppModel());
  bean.setBillForm(getBillFormEditor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.so.tranmatrel.action.TranMatRelEditAction getEditAction(){
 if(context.get("editAction")!=null)
 return (nc.ui.so.tranmatrel.action.TranMatRelEditAction)context.get("editAction");
  nc.ui.so.tranmatrel.action.TranMatRelEditAction bean = new nc.ui.so.tranmatrel.action.TranMatRelEditAction();
  context.put("editAction",bean);
  bean.setModel(getManageAppModel());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.so.tranmatrel.action.TranMatRelDeleteAction getDeleteAction(){
 if(context.get("deleteAction")!=null)
 return (nc.ui.so.tranmatrel.action.TranMatRelDeleteAction)context.get("deleteAction");
  nc.ui.so.tranmatrel.action.TranMatRelDeleteAction bean = new nc.ui.so.tranmatrel.action.TranMatRelDeleteAction();
  context.put("deleteAction",bean);
  bean.setModel(getManageAppModel());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.so.tranmatrel.action.TranMatRelSaveAction getSaveAction(){
 if(context.get("saveAction")!=null)
 return (nc.ui.so.tranmatrel.action.TranMatRelSaveAction)context.get("saveAction");
  nc.ui.so.tranmatrel.action.TranMatRelSaveAction bean = new nc.ui.so.tranmatrel.action.TranMatRelSaveAction();
  context.put("saveAction",bean);
  bean.setModel(getManageAppModel());
  bean.setView(getBillFormEditor());
  bean.setEditor(getBillFormEditor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.so.tranmatrel.action.TranMatRelCancelAction getCancelAction(){
 if(context.get("cancelAction")!=null)
 return (nc.ui.so.tranmatrel.action.TranMatRelCancelAction)context.get("cancelAction");
  nc.ui.so.tranmatrel.action.TranMatRelCancelAction bean = new nc.ui.so.tranmatrel.action.TranMatRelCancelAction();
  context.put("cancelAction",bean);
  bean.setModel(getManageAppModel());
  bean.setEditor(getBillFormEditor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.so.tranmatrel.action.TranMatRelRefreshAction getRefreshAction(){
 if(context.get("refreshAction")!=null)
 return (nc.ui.so.tranmatrel.action.TranMatRelRefreshAction)context.get("refreshAction");
  nc.ui.so.tranmatrel.action.TranMatRelRefreshAction bean = new nc.ui.so.tranmatrel.action.TranMatRelRefreshAction();
  context.put("refreshAction",bean);
  bean.setModel(getManageAppModel());
  bean.setDataManager(getModelDataManager());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.so.tranmatrel.action.TranMatRelPrintAction getPreviewAction(){
 if(context.get("previewAction")!=null)
 return (nc.ui.so.tranmatrel.action.TranMatRelPrintAction)context.get("previewAction");
  nc.ui.so.tranmatrel.action.TranMatRelPrintAction bean = new nc.ui.so.tranmatrel.action.TranMatRelPrintAction();
  context.put("previewAction",bean);
  bean.setDirectPrint(false);
  bean.setModel(getManageAppModel());
  bean.setEditor(getBillFormEditor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.so.tranmatrel.action.TranMatRelPrintAction getPrintAction(){
 if(context.get("printAction")!=null)
 return (nc.ui.so.tranmatrel.action.TranMatRelPrintAction)context.get("printAction");
  nc.ui.so.tranmatrel.action.TranMatRelPrintAction bean = new nc.ui.so.tranmatrel.action.TranMatRelPrintAction();
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
  bean.setName(getI18nFB_150e6f9());
  bean.setActions(getManagedList11());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private java.lang.String getI18nFB_150e6f9(){
 if(context.get("nc.ui.uif2.I18nFB#150e6f9")!=null)
 return (java.lang.String)context.get("nc.ui.uif2.I18nFB#150e6f9");
  nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
    context.put("&nc.ui.uif2.I18nFB#150e6f9",bean);  bean.setResDir("4006007_0");
  bean.setResId("04006007-0030");
  bean.setDefaultValue("¥Ú”°");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
 try {
     Object product = bean.getObject();
    context.put("nc.ui.uif2.I18nFB#150e6f9",product);
     return (java.lang.String)product;
}
catch(Exception e) { throw new RuntimeException(e);}}

private List getManagedList11(){  List list = new ArrayList();  list.add(getPrintAction());  list.add(getPreviewAction());  return list;}

}
