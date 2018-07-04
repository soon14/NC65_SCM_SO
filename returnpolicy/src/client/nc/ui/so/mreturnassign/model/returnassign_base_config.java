package nc.ui.so.mreturnassign.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.ui.uif2.factory.AbstractJavaBeanDefinition;

public class returnassign_base_config extends AbstractJavaBeanDefinition{
	private Map<String, Object> context = new HashMap();
public nc.vo.uif2.LoginContext getContext(){
 if(context.get("context")!=null)
 return (nc.vo.uif2.LoginContext)context.get("context");
  nc.vo.uif2.LoginContext bean = new nc.vo.uif2.LoginContext();
  context.put("context",bean);
return bean;
}

public nc.ui.pubapp.pub.smart.SmartBatchAppModelService getBatchModelService(){
 if(context.get("batchModelService")!=null)
 return (nc.ui.pubapp.pub.smart.SmartBatchAppModelService)context.get("batchModelService");
  nc.ui.pubapp.pub.smart.SmartBatchAppModelService bean = new nc.ui.pubapp.pub.smart.SmartBatchAppModelService();
  context.put("batchModelService",bean);
  bean.setVoClass("nc.vo.so.mreturnassign.entity.ReturnAssignVO");
  bean.setServiceItf("nc.itf.so.mreturnassign.IReturnAssignMaintain");
return bean;
}

public nc.vo.bd.meta.BDObjectAdpaterFactory getBoadatorfactory(){
 if(context.get("boadatorfactory")!=null)
 return (nc.vo.bd.meta.BDObjectAdpaterFactory)context.get("boadatorfactory");
  nc.vo.bd.meta.BDObjectAdpaterFactory bean = new nc.vo.bd.meta.BDObjectAdpaterFactory();
  context.put("boadatorfactory",bean);
return bean;
}

public nc.ui.so.mreturnassign.model.ReturnAssignValidationService getBatchValidateSerice(){
 if(context.get("batchValidateSerice")!=null)
 return (nc.ui.so.mreturnassign.model.ReturnAssignValidationService)context.get("batchValidateSerice");
  nc.ui.so.mreturnassign.model.ReturnAssignValidationService bean = new nc.ui.so.mreturnassign.model.ReturnAssignValidationService();
  context.put("batchValidateSerice",bean);
  bean.setEditor(getList());
return bean;
}

public nc.ui.pubapp.uif2app.model.BatchBillTableModel getBatchBillTableModel(){
 if(context.get("batchBillTableModel")!=null)
 return (nc.ui.pubapp.uif2app.model.BatchBillTableModel)context.get("batchBillTableModel");
  nc.ui.pubapp.uif2app.model.BatchBillTableModel bean = new nc.ui.pubapp.uif2app.model.BatchBillTableModel();
  context.put("batchBillTableModel",bean);
  bean.setContext(getContext());
  bean.setService(getBatchModelService());
  bean.setBusinessObjectAdapterFactory(getBoadatorfactory());
  bean.setValidationService(getBatchValidateSerice());
return bean;
}

public nc.ui.pubapp.uif2app.model.BatchModelDataManager getModelDataManager(){
 if(context.get("modelDataManager")!=null)
 return (nc.ui.pubapp.uif2app.model.BatchModelDataManager)context.get("modelDataManager");
  nc.ui.pubapp.uif2app.model.BatchModelDataManager bean = new nc.ui.pubapp.uif2app.model.BatchModelDataManager();
  context.put("modelDataManager",bean);
  bean.setModel(getBatchBillTableModel());
  bean.setService(getBatchModelService());
return bean;
}

public nc.ui.pubapp.uif2app.view.OrgPanel getOrgPanel(){
 if(context.get("orgPanel")!=null)
 return (nc.ui.pubapp.uif2app.view.OrgPanel)context.get("orgPanel");
  nc.ui.pubapp.uif2app.view.OrgPanel bean = new nc.ui.pubapp.uif2app.view.OrgPanel();
  context.put("orgPanel",bean);
  bean.setModel(getBatchBillTableModel());
  bean.setDataManager(getModelDataManager());
  bean.setType("销售组织");/*-=notranslate=-*/
  bean.initUI();
return bean;
}

public nc.ui.uif2.editor.value.BillCardPanelMetaDataValueAdapter getComponentValueManager(){
 if(context.get("componentValueManager")!=null)
 return (nc.ui.uif2.editor.value.BillCardPanelMetaDataValueAdapter)context.get("componentValueManager");
  nc.ui.uif2.editor.value.BillCardPanelMetaDataValueAdapter bean = new nc.ui.uif2.editor.value.BillCardPanelMetaDataValueAdapter();
  context.put("componentValueManager",bean);
return bean;
}

public nc.ui.so.mreturnassign.view.ReturnAssignBillView getList(){
 if(context.get("list")!=null)
 return (nc.ui.so.mreturnassign.view.ReturnAssignBillView)context.get("list");
  nc.ui.so.mreturnassign.view.ReturnAssignBillView bean = new nc.ui.so.mreturnassign.view.ReturnAssignBillView();
  context.put("list",bean);
  bean.setModel(getBatchBillTableModel());
  bean.setComponentValueManager(getComponentValueManager());
  bean.setIsBodyAutoAddLine(false);
  bean.setAddLineAction(getAddAction());
  bean.initUI();
return bean;
}

public nc.funcnode.ui.action.GroupAction getMaintainActionGroup(){
 if(context.get("maintainActionGroup")!=null)
 return (nc.funcnode.ui.action.GroupAction)context.get("maintainActionGroup");
  nc.funcnode.ui.action.GroupAction bean = new nc.funcnode.ui.action.GroupAction();
  context.put("maintainActionGroup",bean);
  bean.setCode("maintain");
  bean.setName("打印");/*-=notranslate=-*/
  bean.setActions(getManagedList0());
return bean;
}

public List getManagedList0(){  List list = new ArrayList();  list.add(getPrintAction());  list.add(getPreviewAction());  return list;}

public nc.ui.uif2.editor.TemplateContainer getTemplateContainer(){
 if(context.get("templateContainer")!=null)
 return (nc.ui.uif2.editor.TemplateContainer)context.get("templateContainer");
  nc.ui.uif2.editor.TemplateContainer bean = new nc.ui.uif2.editor.TemplateContainer();
  context.put("templateContainer",bean);
  bean.setContext(getContext());
  bean.load();
return bean;
}

public nc.ui.so.mreturnassign.action.ReturnAssignAddLineAction getAddAction(){
 if(context.get("addAction")!=null)
 return (nc.ui.so.mreturnassign.action.ReturnAssignAddLineAction)context.get("addAction");
  nc.ui.so.mreturnassign.action.ReturnAssignAddLineAction bean = new nc.ui.so.mreturnassign.action.ReturnAssignAddLineAction();
  context.put("addAction",bean);
  bean.setModel(getBatchBillTableModel());
  bean.setVoClassName("nc.vo.so.mreturnassign.entity.ReturnAssignVO");
return bean;
}

public nc.ui.so.mreturnassign.action.ReturnAssignEditAction getEditAction(){
 if(context.get("editAction")!=null)
 return (nc.ui.so.mreturnassign.action.ReturnAssignEditAction)context.get("editAction");
  nc.ui.so.mreturnassign.action.ReturnAssignEditAction bean = new nc.ui.so.mreturnassign.action.ReturnAssignEditAction();
  context.put("editAction",bean);
  bean.setModel(getBatchBillTableModel());
return bean;
}

public nc.ui.uif2.actions.batch.BatchDelLineAction getDelAction(){
 if(context.get("delAction")!=null)
 return (nc.ui.uif2.actions.batch.BatchDelLineAction)context.get("delAction");
  nc.ui.uif2.actions.batch.BatchDelLineAction bean = new nc.ui.uif2.actions.batch.BatchDelLineAction();
  context.put("delAction",bean);
  bean.setModel(getBatchBillTableModel());
return bean;
}

public nc.ui.uif2.actions.batch.BatchSaveAction getSaveAction(){
 if(context.get("saveAction")!=null)
 return (nc.ui.uif2.actions.batch.BatchSaveAction)context.get("saveAction");
  nc.ui.uif2.actions.batch.BatchSaveAction bean = new nc.ui.uif2.actions.batch.BatchSaveAction();
  context.put("saveAction",bean);
  bean.setModel(getBatchBillTableModel());
  bean.setEditor(getList());
  bean.setValidationService(getBatchValidateSerice());
return bean;
}

public nc.ui.so.mreturnassign.action.ReturnAssignCancleAction getCancelAction(){
 if(context.get("cancelAction")!=null)
 return (nc.ui.so.mreturnassign.action.ReturnAssignCancleAction)context.get("cancelAction");
  nc.ui.so.mreturnassign.action.ReturnAssignCancleAction bean = new nc.ui.so.mreturnassign.action.ReturnAssignCancleAction();
  context.put("cancelAction",bean);
  bean.setModel(getBatchBillTableModel());
  bean.setEditor(getList());
return bean;
}

public nc.ui.so.mreturnassign.action.ReturnAssignRefreshAction getRefreshAction(){
 if(context.get("refreshAction")!=null)
 return (nc.ui.so.mreturnassign.action.ReturnAssignRefreshAction)context.get("refreshAction");
  nc.ui.so.mreturnassign.action.ReturnAssignRefreshAction bean = new nc.ui.so.mreturnassign.action.ReturnAssignRefreshAction();
  context.put("refreshAction",bean);
  bean.setModel(getBatchBillTableModel());
  bean.setManager(getModelDataManager());
return bean;
}

public nc.ui.pubapp.uif2app.actions.SingleTablePrintAction getPrintAction(){
 if(context.get("printAction")!=null)
 return (nc.ui.pubapp.uif2app.actions.SingleTablePrintAction)context.get("printAction");
  nc.ui.pubapp.uif2app.actions.SingleTablePrintAction bean = new nc.ui.pubapp.uif2app.actions.SingleTablePrintAction();
  context.put("printAction",bean);
  bean.setModel(getBatchBillTableModel());
  bean.setEditor(getList());
  bean.setPreview(false);
return bean;
}

public nc.ui.pubapp.uif2app.actions.SingleTablePrintAction getPreviewAction(){
 if(context.get("previewAction")!=null)
 return (nc.ui.pubapp.uif2app.actions.SingleTablePrintAction)context.get("previewAction");
  nc.ui.pubapp.uif2app.actions.SingleTablePrintAction bean = new nc.ui.pubapp.uif2app.actions.SingleTablePrintAction();
  context.put("previewAction",bean);
  bean.setModel(getBatchBillTableModel());
  bean.setEditor(getList());
  bean.setPreview(true);
return bean;
}

public nc.ui.pubapp.uif2app.model.AppEventHandlerMediator getAppeventhandlerMediator(){
 if(context.get("appeventhandlerMediator")!=null)
 return (nc.ui.pubapp.uif2app.model.AppEventHandlerMediator)context.get("appeventhandlerMediator");
  nc.ui.pubapp.uif2app.model.AppEventHandlerMediator bean = new nc.ui.pubapp.uif2app.model.AppEventHandlerMediator();
  context.put("appeventhandlerMediator",bean);
  bean.setModel(getBatchBillTableModel());
  bean.setHandlerMap(getManagedMap0());
return bean;
}

public Map getManagedMap0(){  Map map = new HashMap();  map.put("nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent",getManagedList1());  return map;}

public List getManagedList1(){  List list = new ArrayList();  list.add(getBodyBeforeEditHandler_6dc9a1());  return list;}

public nc.ui.so.mreturnassign.editor.BodyBeforeEditHandler getBodyBeforeEditHandler_6dc9a1(){
 if(context.get("nc.ui.so.mreturnassign.editor.BodyBeforeEditHandler#6dc9a1")!=null)
 return (nc.ui.so.mreturnassign.editor.BodyBeforeEditHandler)context.get("nc.ui.so.mreturnassign.editor.BodyBeforeEditHandler#6dc9a1");
  nc.ui.so.mreturnassign.editor.BodyBeforeEditHandler bean = new nc.ui.so.mreturnassign.editor.BodyBeforeEditHandler();
  context.put("nc.ui.so.mreturnassign.editor.BodyBeforeEditHandler#6dc9a1",bean);
return bean;
}

}
