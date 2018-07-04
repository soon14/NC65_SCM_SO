package nc.ui.so.mreturnreason.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.ui.uif2.factory.AbstractJavaBeanDefinition;

public class returnreason_base_config extends AbstractJavaBeanDefinition{
	private Map<String, Object> context = new HashMap();
public nc.ui.pubapp.pub.smart.SmartBatchAppModelService getBatchModelService(){
 if(context.get("batchModelService")!=null)
 return (nc.ui.pubapp.pub.smart.SmartBatchAppModelService)context.get("batchModelService");
  nc.ui.pubapp.pub.smart.SmartBatchAppModelService bean = new nc.ui.pubapp.pub.smart.SmartBatchAppModelService();
  context.put("batchModelService",bean);
  bean.setVoClass("nc.vo.so.mreturnreason.entity.ReturnReasonVO");
  bean.setServiceItf("nc.itf.so.mreturnreason.IReturnReasonMaintain");
return bean;
}

public nc.vo.bd.meta.BDObjectAdpaterFactory getBoadatorfactory(){
 if(context.get("boadatorfactory")!=null)
 return (nc.vo.bd.meta.BDObjectAdpaterFactory)context.get("boadatorfactory");
  nc.vo.bd.meta.BDObjectAdpaterFactory bean = new nc.vo.bd.meta.BDObjectAdpaterFactory();
  context.put("boadatorfactory",bean);
return bean;
}

public nc.ui.so.mreturnreason.model.RetrunReasonValidationService getUiBatchValidateSerice(){
 if(context.get("uiBatchValidateSerice")!=null)
 return (nc.ui.so.mreturnreason.model.RetrunReasonValidationService)context.get("uiBatchValidateSerice");
  nc.ui.so.mreturnreason.model.RetrunReasonValidationService bean = new nc.ui.so.mreturnreason.model.RetrunReasonValidationService();
  context.put("uiBatchValidateSerice",bean);
  bean.setEditor(getList());
return bean;
}

public nc.ui.pubapp.uif2app.model.BatchBillTableModel getBatchBillTableModel(){
 if(context.get("batchBillTableModel")!=null)
 return (nc.ui.pubapp.uif2app.model.BatchBillTableModel)context.get("batchBillTableModel");
  nc.ui.pubapp.uif2app.model.BatchBillTableModel bean = new nc.ui.pubapp.uif2app.model.BatchBillTableModel();
  context.put("batchBillTableModel",bean);
  bean.setContext((nc.vo.uif2.LoginContext)findBeanInUIF2BeanFactory("context"));
  bean.setService(getBatchModelService());
  bean.setBusinessObjectAdapterFactory(getBoadatorfactory());
  bean.setValidationService(getUiBatchValidateSerice());
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

public nc.ui.so.mreturnreason.view.MreturnReasonBillView getList(){
 if(context.get("list")!=null)
 return (nc.ui.so.mreturnreason.view.MreturnReasonBillView)context.get("list");
  nc.ui.so.mreturnreason.view.MreturnReasonBillView bean = new nc.ui.so.mreturnreason.view.MreturnReasonBillView();
  context.put("list",bean);
  bean.setModel(getBatchBillTableModel());
  bean.setComponentValueManager(getComponentValueManager());
  bean.setMoreRowEdit(false);
  bean.setIsBodyAutoAddLine(false);
  bean.setBodyMultiSelectEnable(true);
  bean.setAddLineAction(getAddAction());
  bean.initUI();
return bean;
}

public nc.ui.uif2.editor.TemplateContainer getTemplateContainer(){
 if(context.get("templateContainer")!=null)
 return (nc.ui.uif2.editor.TemplateContainer)context.get("templateContainer");
  nc.ui.uif2.editor.TemplateContainer bean = new nc.ui.uif2.editor.TemplateContainer();
  context.put("templateContainer",bean);
  bean.setContext((nc.vo.uif2.LoginContext)findBeanInUIF2BeanFactory("context"));
  bean.load();
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

public nc.ui.so.mreturnreason.action.AddLineAction getAddAction(){
 if(context.get("addAction")!=null)
 return (nc.ui.so.mreturnreason.action.AddLineAction)context.get("addAction");
  nc.ui.so.mreturnreason.action.AddLineAction bean = new nc.ui.so.mreturnreason.action.AddLineAction();
  context.put("addAction",bean);
  bean.setModel(getBatchBillTableModel());
  bean.setVoClassName("nc.vo.so.mreturnreason.entity.ReturnReasonVO");
return bean;
}

public nc.ui.so.mreturnreason.action.EditAction getEditAction(){
 if(context.get("editAction")!=null)
 return (nc.ui.so.mreturnreason.action.EditAction)context.get("editAction");
  nc.ui.so.mreturnreason.action.EditAction bean = new nc.ui.so.mreturnreason.action.EditAction();
  context.put("editAction",bean);
  bean.setModel(getBatchBillTableModel());
return bean;
}

public nc.ui.so.mreturnreason.action.DeleteAction getDelAction(){
 if(context.get("delAction")!=null)
 return (nc.ui.so.mreturnreason.action.DeleteAction)context.get("delAction");
  nc.ui.so.mreturnreason.action.DeleteAction bean = new nc.ui.so.mreturnreason.action.DeleteAction();
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
  bean.setValidationService(getUiBatchValidateSerice());
return bean;
}

public nc.ui.so.mreturnreason.action.CancleAction getCancelAction(){
 if(context.get("cancelAction")!=null)
 return (nc.ui.so.mreturnreason.action.CancleAction)context.get("cancelAction");
  nc.ui.so.mreturnreason.action.CancleAction bean = new nc.ui.so.mreturnreason.action.CancleAction();
  context.put("cancelAction",bean);
  bean.setModel(getBatchBillTableModel());
  bean.setEditor(getList());
return bean;
}

public nc.ui.pubapp.uif2app.actions.SingleTablePrintAction getPrintAction(){
 if(context.get("printAction")!=null)
 return (nc.ui.pubapp.uif2app.actions.SingleTablePrintAction)context.get("printAction");
  nc.ui.pubapp.uif2app.actions.SingleTablePrintAction bean = new nc.ui.pubapp.uif2app.actions.SingleTablePrintAction();
  context.put("printAction",bean);
  bean.setModel(getBatchBillTableModel());
  bean.setPreview(false);
  bean.setEditor(getList());
return bean;
}

public nc.ui.pubapp.uif2app.actions.SingleTablePrintAction getPreviewAction(){
 if(context.get("previewAction")!=null)
 return (nc.ui.pubapp.uif2app.actions.SingleTablePrintAction)context.get("previewAction");
  nc.ui.pubapp.uif2app.actions.SingleTablePrintAction bean = new nc.ui.pubapp.uif2app.actions.SingleTablePrintAction();
  context.put("previewAction",bean);
  bean.setModel(getBatchBillTableModel());
  bean.setPreview(true);
  bean.setEditor(getList());
return bean;
}

public nc.ui.so.mreturnreason.action.RefreshAction getRefreshAction(){
 if(context.get("refreshAction")!=null)
 return (nc.ui.so.mreturnreason.action.RefreshAction)context.get("refreshAction");
  nc.ui.so.mreturnreason.action.RefreshAction bean = new nc.ui.so.mreturnreason.action.RefreshAction();
  context.put("refreshAction",bean);
  bean.setModel(getBatchBillTableModel());
  bean.setManager(getModelDataManager());
return bean;
}

public nc.ui.pubapp.uif2app.model.AppEventHandlerMediator getAppeventhandlermediator(){
 if(context.get("appeventhandlermediator")!=null)
 return (nc.ui.pubapp.uif2app.model.AppEventHandlerMediator)context.get("appeventhandlermediator");
  nc.ui.pubapp.uif2app.model.AppEventHandlerMediator bean = new nc.ui.pubapp.uif2app.model.AppEventHandlerMediator();
  context.put("appeventhandlermediator",bean);
  bean.setModel(getBatchBillTableModel());
  bean.setHandlerMap(getManagedMap0());
return bean;
}

public Map getManagedMap0(){  Map map = new HashMap();  map.put("nc.ui.pubapp.uif2app.event.OrgChangedEvent",getManagedList1());  return map;}

public List getManagedList1(){  List list = new ArrayList();  list.add(getOrgEditHandler_166b383());  return list;}

public nc.ui.so.mreturnreason.model.OrgEditHandler getOrgEditHandler_166b383(){
 if(context.get("nc.ui.so.mreturnreason.model.OrgEditHandler#166b383")!=null)
 return (nc.ui.so.mreturnreason.model.OrgEditHandler)context.get("nc.ui.so.mreturnreason.model.OrgEditHandler#166b383");
  nc.ui.so.mreturnreason.model.OrgEditHandler bean = new nc.ui.so.mreturnreason.model.OrgEditHandler();
  context.put("nc.ui.so.mreturnreason.model.OrgEditHandler#166b383",bean);
return bean;
}

}
