package nc.ui.so.m30.closemanage.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.ui.uif2.factory.AbstractJavaBeanDefinition;

public class m30close_config extends AbstractJavaBeanDefinition{
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

public nc.ui.so.m30.closemanage.model.SaleOrderCloseManageService getManageModelService(){
 if(context.get("ManageModelService")!=null)
 return (nc.ui.so.m30.closemanage.model.SaleOrderCloseManageService)context.get("ManageModelService");
  nc.ui.so.m30.closemanage.model.SaleOrderCloseManageService bean = new nc.ui.so.m30.closemanage.model.SaleOrderCloseManageService();
  context.put("ManageModelService",bean);
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

public nc.ui.uif2.model.DefaultBatchValidationService getBatchValidateSerice(){
 if(context.get("batchValidateSerice")!=null)
 return (nc.ui.uif2.model.DefaultBatchValidationService)context.get("batchValidateSerice");
  nc.ui.uif2.model.DefaultBatchValidationService bean = new nc.ui.uif2.model.DefaultBatchValidationService();
  context.put("batchValidateSerice",bean);
  bean.setEditor(getList());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.model.BatchBillTableModel getBatchBillTableModel(){
 if(context.get("batchBillTableModel")!=null)
 return (nc.ui.pubapp.uif2app.model.BatchBillTableModel)context.get("batchBillTableModel");
  nc.ui.pubapp.uif2app.model.BatchBillTableModel bean = new nc.ui.pubapp.uif2app.model.BatchBillTableModel();
  context.put("batchBillTableModel",bean);
  bean.setContext(getContext());
  bean.setBusinessObjectAdapterFactory(getBoadatorfactory());
  bean.setValidationService(getBatchValidateSerice());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.so.m30.closemanage.model.CloseManageModelDataManager getModelDataManager(){
 if(context.get("modelDataManager")!=null)
 return (nc.ui.so.m30.closemanage.model.CloseManageModelDataManager)context.get("modelDataManager");
  nc.ui.so.m30.closemanage.model.CloseManageModelDataManager bean = new nc.ui.so.m30.closemanage.model.CloseManageModelDataManager();
  context.put("modelDataManager",bean);
  bean.setModel(getBatchBillTableModel());
  bean.setService(getManageModelService());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.so.m30.closemanage.model.M30CloseBillCardPanelValueAdapter getComponentValueManager(){
 if(context.get("componentValueManager")!=null)
 return (nc.ui.so.m30.closemanage.model.M30CloseBillCardPanelValueAdapter)context.get("componentValueManager");
  nc.ui.so.m30.closemanage.model.M30CloseBillCardPanelValueAdapter bean = new nc.ui.so.m30.closemanage.model.M30CloseBillCardPanelValueAdapter();
  context.put("componentValueManager",bean);
  bean.setBodyVOName("nc.vo.so.msaleorder.entity.SaleOrderViewVO");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.so.m30.closemanage.view.SaleOrderCloseManageListView getList(){
 if(context.get("list")!=null)
 return (nc.ui.so.m30.closemanage.view.SaleOrderCloseManageListView)context.get("list");
  nc.ui.so.m30.closemanage.view.SaleOrderCloseManageListView bean = new nc.ui.so.m30.closemanage.view.SaleOrderCloseManageListView();
  context.put("list",bean);
  bean.setModel(getBatchBillTableModel());
  bean.setComponentValueManager(getComponentValueManager());
  bean.setVoClassName("nc.vo.so.msaleorder.entity.SaleOrderViewVO");
  bean.setIsBodyAutoAddLine(false);
  bean.setBodyMultiSelectEnable(true);
  bean.initUI();
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.uif2.TangramContainer getContainer(){
 if(context.get("container")!=null)
 return (nc.ui.uif2.TangramContainer)context.get("container");
  nc.ui.uif2.TangramContainer bean = new nc.ui.uif2.TangramContainer();
  context.put("container",bean);
  bean.setTangramLayoutRoot(getCNode_15b05d1());
  bean.setActions(getManagedList0());
  bean.initUI();
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.tangramlayout.node.CNode getCNode_15b05d1(){
 if(context.get("nc.ui.uif2.tangramlayout.node.CNode#15b05d1")!=null)
 return (nc.ui.uif2.tangramlayout.node.CNode)context.get("nc.ui.uif2.tangramlayout.node.CNode#15b05d1");
  nc.ui.uif2.tangramlayout.node.CNode bean = new nc.ui.uif2.tangramlayout.node.CNode();
  context.put("nc.ui.uif2.tangramlayout.node.CNode#15b05d1",bean);
  bean.setComponent(getList());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList0(){  List list = new ArrayList();  list.add(getQueryAction());  list.add(getRefreshAction());  list.add(getSeparatorAction());  list.add(getBillCloseActionGroup());  list.add(getRowCloseActionGroup());  list.add(getSendCloseActionGroup());  list.add(getOutCloseActionGroup());  list.add(getInvoiceCloseActionGroup());  list.add(getSettleCloseActionGroup());  list.add(getSeparatorAction());  list.add(getLinkQueryAction());  list.add(getSeparatorAction());  list.add(getPrintActionGroup());  return list;}

public nc.funcnode.ui.action.SeparatorAction getSeparatorAction(){
 if(context.get("separatorAction")!=null)
 return (nc.funcnode.ui.action.SeparatorAction)context.get("separatorAction");
  nc.funcnode.ui.action.SeparatorAction bean = new nc.funcnode.ui.action.SeparatorAction();
  context.put("separatorAction",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.so.m30.closemanage.view.M30CloseQueryDLGInitializer getQueryDLGInitializer(){
 if(context.get("queryDLGInitializer")!=null)
 return (nc.ui.so.m30.closemanage.view.M30CloseQueryDLGInitializer)context.get("queryDLGInitializer");
  nc.ui.so.m30.closemanage.view.M30CloseQueryDLGInitializer bean = new nc.ui.so.m30.closemanage.view.M30CloseQueryDLGInitializer();
  context.put("queryDLGInitializer",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.query2.action.DefaultQueryAction getQueryAction(){
 if(context.get("queryAction")!=null)
 return (nc.ui.pubapp.uif2app.query2.action.DefaultQueryAction)context.get("queryAction");
  nc.ui.pubapp.uif2app.query2.action.DefaultQueryAction bean = new nc.ui.pubapp.uif2app.query2.action.DefaultQueryAction();
  context.put("queryAction",bean);
  bean.setModel(getBatchBillTableModel());
  bean.setDataManager(getModelDataManager());
  bean.setQryCondDLGInitializer(getQueryDLGInitializer());
  bean.setHasQueryArea(false);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.query2.action.DefaultRefreshAction getRefreshAction(){
 if(context.get("refreshAction")!=null)
 return (nc.ui.pubapp.uif2app.query2.action.DefaultRefreshAction)context.get("refreshAction");
  nc.ui.pubapp.uif2app.query2.action.DefaultRefreshAction bean = new nc.ui.pubapp.uif2app.query2.action.DefaultRefreshAction();
  context.put("refreshAction",bean);
  bean.setModel(getBatchBillTableModel());
  bean.setDataManager(getModelDataManager());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.funcnode.ui.action.MenuAction getBillCloseActionGroup(){
 if(context.get("billCloseActionGroup")!=null)
 return (nc.funcnode.ui.action.MenuAction)context.get("billCloseActionGroup");
  nc.funcnode.ui.action.MenuAction bean = new nc.funcnode.ui.action.MenuAction();
  context.put("billCloseActionGroup",bean);
  bean.setCode("billCloseGroup");
  bean.setName(getI18nFB_14588d9());
  bean.setActions(getManagedList1());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private java.lang.String getI18nFB_14588d9(){
 if(context.get("nc.ui.uif2.I18nFB#14588d9")!=null)
 return (java.lang.String)context.get("nc.ui.uif2.I18nFB#14588d9");
  nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
    context.put("&nc.ui.uif2.I18nFB#14588d9",bean);  bean.setResDir("4006011_0");
  bean.setResId("04006011-0404");
  bean.setDefaultValue("整单关闭");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
 try {
     Object product = bean.getObject();
    context.put("nc.ui.uif2.I18nFB#14588d9",product);
     return (java.lang.String)product;
}
catch(Exception e) { throw new RuntimeException(e);}}

private List getManagedList1(){  List list = new ArrayList();  list.add(getBillCloseAction());  list.add(getBillOpenAction());  return list;}

public nc.ui.so.m30.closemanage.action.M30CloseBillCloseAction getBillCloseAction(){
 if(context.get("billCloseAction")!=null)
 return (nc.ui.so.m30.closemanage.action.M30CloseBillCloseAction)context.get("billCloseAction");
  nc.ui.so.m30.closemanage.action.M30CloseBillCloseAction bean = new nc.ui.so.m30.closemanage.action.M30CloseBillCloseAction();
  context.put("billCloseAction",bean);
  bean.setModel(getBatchBillTableModel());
  bean.setBillTable(getList());
  bean.setSingleBillService(getM30CloseBillCloseService_1b6a7e());
  bean.setRefreshAction(getRefreshAction());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.so.m30.closemanage.model.M30CloseBillCloseService getM30CloseBillCloseService_1b6a7e(){
 if(context.get("nc.ui.so.m30.closemanage.model.M30CloseBillCloseService#1b6a7e")!=null)
 return (nc.ui.so.m30.closemanage.model.M30CloseBillCloseService)context.get("nc.ui.so.m30.closemanage.model.M30CloseBillCloseService#1b6a7e");
  nc.ui.so.m30.closemanage.model.M30CloseBillCloseService bean = new nc.ui.so.m30.closemanage.model.M30CloseBillCloseService();
  context.put("nc.ui.so.m30.closemanage.model.M30CloseBillCloseService#1b6a7e",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.so.m30.closemanage.action.M30CloseBillOpenAction getBillOpenAction(){
 if(context.get("billOpenAction")!=null)
 return (nc.ui.so.m30.closemanage.action.M30CloseBillOpenAction)context.get("billOpenAction");
  nc.ui.so.m30.closemanage.action.M30CloseBillOpenAction bean = new nc.ui.so.m30.closemanage.action.M30CloseBillOpenAction();
  context.put("billOpenAction",bean);
  bean.setModel(getBatchBillTableModel());
  bean.setBillTable(getList());
  bean.setSingleBillService(getM30CloseBillOpenService_1eca25c());
  bean.setRefreshAction(getRefreshAction());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.so.m30.closemanage.model.M30CloseBillOpenService getM30CloseBillOpenService_1eca25c(){
 if(context.get("nc.ui.so.m30.closemanage.model.M30CloseBillOpenService#1eca25c")!=null)
 return (nc.ui.so.m30.closemanage.model.M30CloseBillOpenService)context.get("nc.ui.so.m30.closemanage.model.M30CloseBillOpenService#1eca25c");
  nc.ui.so.m30.closemanage.model.M30CloseBillOpenService bean = new nc.ui.so.m30.closemanage.model.M30CloseBillOpenService();
  context.put("nc.ui.so.m30.closemanage.model.M30CloseBillOpenService#1eca25c",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.funcnode.ui.action.MenuAction getRowCloseActionGroup(){
 if(context.get("rowCloseActionGroup")!=null)
 return (nc.funcnode.ui.action.MenuAction)context.get("rowCloseActionGroup");
  nc.funcnode.ui.action.MenuAction bean = new nc.funcnode.ui.action.MenuAction();
  context.put("rowCloseActionGroup",bean);
  bean.setCode("rowCloseGroup");
  bean.setName(getI18nFB_97576a());
  bean.setActions(getManagedList2());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private java.lang.String getI18nFB_97576a(){
 if(context.get("nc.ui.uif2.I18nFB#97576a")!=null)
 return (java.lang.String)context.get("nc.ui.uif2.I18nFB#97576a");
  nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
    context.put("&nc.ui.uif2.I18nFB#97576a",bean);  bean.setResDir("4006011_0");
  bean.setResId("04006011-0405");
  bean.setDefaultValue("行关闭");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
 try {
     Object product = bean.getObject();
    context.put("nc.ui.uif2.I18nFB#97576a",product);
     return (java.lang.String)product;
}
catch(Exception e) { throw new RuntimeException(e);}}

private List getManagedList2(){  List list = new ArrayList();  list.add(getRowCloseAction());  list.add(getRowOpenAction());  return list;}

public nc.ui.so.m30.closemanage.action.M30CloseRowCloseAction getRowCloseAction(){
 if(context.get("rowCloseAction")!=null)
 return (nc.ui.so.m30.closemanage.action.M30CloseRowCloseAction)context.get("rowCloseAction");
  nc.ui.so.m30.closemanage.action.M30CloseRowCloseAction bean = new nc.ui.so.m30.closemanage.action.M30CloseRowCloseAction();
  context.put("rowCloseAction",bean);
  bean.setModel(getBatchBillTableModel());
  bean.setBillTable(getList());
  bean.setSingleBillService(getM30CloseRowCloseService_1ec0c26());
  bean.setRefreshAction(getRefreshAction());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.so.m30.closemanage.model.M30CloseRowCloseService getM30CloseRowCloseService_1ec0c26(){
 if(context.get("nc.ui.so.m30.closemanage.model.M30CloseRowCloseService#1ec0c26")!=null)
 return (nc.ui.so.m30.closemanage.model.M30CloseRowCloseService)context.get("nc.ui.so.m30.closemanage.model.M30CloseRowCloseService#1ec0c26");
  nc.ui.so.m30.closemanage.model.M30CloseRowCloseService bean = new nc.ui.so.m30.closemanage.model.M30CloseRowCloseService();
  context.put("nc.ui.so.m30.closemanage.model.M30CloseRowCloseService#1ec0c26",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.so.m30.closemanage.action.M30CloseRowOpenAction getRowOpenAction(){
 if(context.get("rowOpenAction")!=null)
 return (nc.ui.so.m30.closemanage.action.M30CloseRowOpenAction)context.get("rowOpenAction");
  nc.ui.so.m30.closemanage.action.M30CloseRowOpenAction bean = new nc.ui.so.m30.closemanage.action.M30CloseRowOpenAction();
  context.put("rowOpenAction",bean);
  bean.setModel(getBatchBillTableModel());
  bean.setBillTable(getList());
  bean.setSingleBillService(getM30CloseRowOpenService_1197e0());
  bean.setRefreshAction(getRefreshAction());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.so.m30.closemanage.model.M30CloseRowOpenService getM30CloseRowOpenService_1197e0(){
 if(context.get("nc.ui.so.m30.closemanage.model.M30CloseRowOpenService#1197e0")!=null)
 return (nc.ui.so.m30.closemanage.model.M30CloseRowOpenService)context.get("nc.ui.so.m30.closemanage.model.M30CloseRowOpenService#1197e0");
  nc.ui.so.m30.closemanage.model.M30CloseRowOpenService bean = new nc.ui.so.m30.closemanage.model.M30CloseRowOpenService();
  context.put("nc.ui.so.m30.closemanage.model.M30CloseRowOpenService#1197e0",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.funcnode.ui.action.MenuAction getSendCloseActionGroup(){
 if(context.get("sendCloseActionGroup")!=null)
 return (nc.funcnode.ui.action.MenuAction)context.get("sendCloseActionGroup");
  nc.funcnode.ui.action.MenuAction bean = new nc.funcnode.ui.action.MenuAction();
  context.put("sendCloseActionGroup",bean);
  bean.setCode("sendCloseGroup");
  bean.setName(getI18nFB_10a2880());
  bean.setActions(getManagedList3());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private java.lang.String getI18nFB_10a2880(){
 if(context.get("nc.ui.uif2.I18nFB#10a2880")!=null)
 return (java.lang.String)context.get("nc.ui.uif2.I18nFB#10a2880");
  nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
    context.put("&nc.ui.uif2.I18nFB#10a2880",bean);  bean.setResDir("4006011_0");
  bean.setResId("04006011-0406");
  bean.setDefaultValue("发货关闭");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
 try {
     Object product = bean.getObject();
    context.put("nc.ui.uif2.I18nFB#10a2880",product);
     return (java.lang.String)product;
}
catch(Exception e) { throw new RuntimeException(e);}}

private List getManagedList3(){  List list = new ArrayList();  list.add(getSendCloseAction());  list.add(getSendOpenAction());  return list;}

public nc.ui.so.m30.closemanage.action.M30CloseSendCloseAction getSendCloseAction(){
 if(context.get("sendCloseAction")!=null)
 return (nc.ui.so.m30.closemanage.action.M30CloseSendCloseAction)context.get("sendCloseAction");
  nc.ui.so.m30.closemanage.action.M30CloseSendCloseAction bean = new nc.ui.so.m30.closemanage.action.M30CloseSendCloseAction();
  context.put("sendCloseAction",bean);
  bean.setModel(getBatchBillTableModel());
  bean.setBillTable(getList());
  bean.setSingleBillService(getM30CloseSendCloseService_1e91a31());
  bean.setRefreshAction(getRefreshAction());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.so.m30.closemanage.model.M30CloseSendCloseService getM30CloseSendCloseService_1e91a31(){
 if(context.get("nc.ui.so.m30.closemanage.model.M30CloseSendCloseService#1e91a31")!=null)
 return (nc.ui.so.m30.closemanage.model.M30CloseSendCloseService)context.get("nc.ui.so.m30.closemanage.model.M30CloseSendCloseService#1e91a31");
  nc.ui.so.m30.closemanage.model.M30CloseSendCloseService bean = new nc.ui.so.m30.closemanage.model.M30CloseSendCloseService();
  context.put("nc.ui.so.m30.closemanage.model.M30CloseSendCloseService#1e91a31",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.so.m30.closemanage.action.M30CloseSendOpenAction getSendOpenAction(){
 if(context.get("sendOpenAction")!=null)
 return (nc.ui.so.m30.closemanage.action.M30CloseSendOpenAction)context.get("sendOpenAction");
  nc.ui.so.m30.closemanage.action.M30CloseSendOpenAction bean = new nc.ui.so.m30.closemanage.action.M30CloseSendOpenAction();
  context.put("sendOpenAction",bean);
  bean.setModel(getBatchBillTableModel());
  bean.setBillTable(getList());
  bean.setSingleBillService(getM30CloseSendOpenService_b6c477());
  bean.setRefreshAction(getRefreshAction());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.so.m30.closemanage.model.M30CloseSendOpenService getM30CloseSendOpenService_b6c477(){
 if(context.get("nc.ui.so.m30.closemanage.model.M30CloseSendOpenService#b6c477")!=null)
 return (nc.ui.so.m30.closemanage.model.M30CloseSendOpenService)context.get("nc.ui.so.m30.closemanage.model.M30CloseSendOpenService#b6c477");
  nc.ui.so.m30.closemanage.model.M30CloseSendOpenService bean = new nc.ui.so.m30.closemanage.model.M30CloseSendOpenService();
  context.put("nc.ui.so.m30.closemanage.model.M30CloseSendOpenService#b6c477",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.funcnode.ui.action.MenuAction getOutCloseActionGroup(){
 if(context.get("outCloseActionGroup")!=null)
 return (nc.funcnode.ui.action.MenuAction)context.get("outCloseActionGroup");
  nc.funcnode.ui.action.MenuAction bean = new nc.funcnode.ui.action.MenuAction();
  context.put("outCloseActionGroup",bean);
  bean.setCode("outCloseGroup");
  bean.setName(getI18nFB_11c9656());
  bean.setActions(getManagedList4());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private java.lang.String getI18nFB_11c9656(){
 if(context.get("nc.ui.uif2.I18nFB#11c9656")!=null)
 return (java.lang.String)context.get("nc.ui.uif2.I18nFB#11c9656");
  nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
    context.put("&nc.ui.uif2.I18nFB#11c9656",bean);  bean.setResDir("4006011_0");
  bean.setResId("04006011-0407");
  bean.setDefaultValue("出库关闭");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
 try {
     Object product = bean.getObject();
    context.put("nc.ui.uif2.I18nFB#11c9656",product);
     return (java.lang.String)product;
}
catch(Exception e) { throw new RuntimeException(e);}}

private List getManagedList4(){  List list = new ArrayList();  list.add(getOutCloseAction());  list.add(getOutOpenAction());  return list;}

public nc.ui.so.m30.closemanage.action.M30CloseOutCloseAction getOutCloseAction(){
 if(context.get("outCloseAction")!=null)
 return (nc.ui.so.m30.closemanage.action.M30CloseOutCloseAction)context.get("outCloseAction");
  nc.ui.so.m30.closemanage.action.M30CloseOutCloseAction bean = new nc.ui.so.m30.closemanage.action.M30CloseOutCloseAction();
  context.put("outCloseAction",bean);
  bean.setModel(getBatchBillTableModel());
  bean.setBillTable(getList());
  bean.setSingleBillService(getM30CloseOutCloseService_1d2045e());
  bean.setRefreshAction(getRefreshAction());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.so.m30.closemanage.model.M30CloseOutCloseService getM30CloseOutCloseService_1d2045e(){
 if(context.get("nc.ui.so.m30.closemanage.model.M30CloseOutCloseService#1d2045e")!=null)
 return (nc.ui.so.m30.closemanage.model.M30CloseOutCloseService)context.get("nc.ui.so.m30.closemanage.model.M30CloseOutCloseService#1d2045e");
  nc.ui.so.m30.closemanage.model.M30CloseOutCloseService bean = new nc.ui.so.m30.closemanage.model.M30CloseOutCloseService();
  context.put("nc.ui.so.m30.closemanage.model.M30CloseOutCloseService#1d2045e",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.so.m30.closemanage.action.M30CloseOutOpenAction getOutOpenAction(){
 if(context.get("outOpenAction")!=null)
 return (nc.ui.so.m30.closemanage.action.M30CloseOutOpenAction)context.get("outOpenAction");
  nc.ui.so.m30.closemanage.action.M30CloseOutOpenAction bean = new nc.ui.so.m30.closemanage.action.M30CloseOutOpenAction();
  context.put("outOpenAction",bean);
  bean.setModel(getBatchBillTableModel());
  bean.setBillTable(getList());
  bean.setSingleBillService(getM30CloseOutOpenService_11cd2ec());
  bean.setRefreshAction(getRefreshAction());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.so.m30.closemanage.model.M30CloseOutOpenService getM30CloseOutOpenService_11cd2ec(){
 if(context.get("nc.ui.so.m30.closemanage.model.M30CloseOutOpenService#11cd2ec")!=null)
 return (nc.ui.so.m30.closemanage.model.M30CloseOutOpenService)context.get("nc.ui.so.m30.closemanage.model.M30CloseOutOpenService#11cd2ec");
  nc.ui.so.m30.closemanage.model.M30CloseOutOpenService bean = new nc.ui.so.m30.closemanage.model.M30CloseOutOpenService();
  context.put("nc.ui.so.m30.closemanage.model.M30CloseOutOpenService#11cd2ec",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.funcnode.ui.action.MenuAction getInvoiceCloseActionGroup(){
 if(context.get("invoiceCloseActionGroup")!=null)
 return (nc.funcnode.ui.action.MenuAction)context.get("invoiceCloseActionGroup");
  nc.funcnode.ui.action.MenuAction bean = new nc.funcnode.ui.action.MenuAction();
  context.put("invoiceCloseActionGroup",bean);
  bean.setCode("invoiceCloseGroup");
  bean.setName(getI18nFB_15bbcf1());
  bean.setActions(getManagedList5());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private java.lang.String getI18nFB_15bbcf1(){
 if(context.get("nc.ui.uif2.I18nFB#15bbcf1")!=null)
 return (java.lang.String)context.get("nc.ui.uif2.I18nFB#15bbcf1");
  nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
    context.put("&nc.ui.uif2.I18nFB#15bbcf1",bean);  bean.setResDir("4006011_0");
  bean.setResId("04006011-0408");
  bean.setDefaultValue("开票关闭");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
 try {
     Object product = bean.getObject();
    context.put("nc.ui.uif2.I18nFB#15bbcf1",product);
     return (java.lang.String)product;
}
catch(Exception e) { throw new RuntimeException(e);}}

private List getManagedList5(){  List list = new ArrayList();  list.add(getInvoiceCloseAction());  list.add(getInvoiceOpenAction());  return list;}

public nc.ui.so.m30.closemanage.action.M30CloseInvoiceCloseAction getInvoiceCloseAction(){
 if(context.get("invoiceCloseAction")!=null)
 return (nc.ui.so.m30.closemanage.action.M30CloseInvoiceCloseAction)context.get("invoiceCloseAction");
  nc.ui.so.m30.closemanage.action.M30CloseInvoiceCloseAction bean = new nc.ui.so.m30.closemanage.action.M30CloseInvoiceCloseAction();
  context.put("invoiceCloseAction",bean);
  bean.setModel(getBatchBillTableModel());
  bean.setBillTable(getList());
  bean.setSingleBillService(getM30CloseInvoiceCloseService_1d199e4());
  bean.setRefreshAction(getRefreshAction());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.so.m30.closemanage.model.M30CloseInvoiceCloseService getM30CloseInvoiceCloseService_1d199e4(){
 if(context.get("nc.ui.so.m30.closemanage.model.M30CloseInvoiceCloseService#1d199e4")!=null)
 return (nc.ui.so.m30.closemanage.model.M30CloseInvoiceCloseService)context.get("nc.ui.so.m30.closemanage.model.M30CloseInvoiceCloseService#1d199e4");
  nc.ui.so.m30.closemanage.model.M30CloseInvoiceCloseService bean = new nc.ui.so.m30.closemanage.model.M30CloseInvoiceCloseService();
  context.put("nc.ui.so.m30.closemanage.model.M30CloseInvoiceCloseService#1d199e4",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.so.m30.closemanage.action.M30CloseInvoiceOpenAction getInvoiceOpenAction(){
 if(context.get("invoiceOpenAction")!=null)
 return (nc.ui.so.m30.closemanage.action.M30CloseInvoiceOpenAction)context.get("invoiceOpenAction");
  nc.ui.so.m30.closemanage.action.M30CloseInvoiceOpenAction bean = new nc.ui.so.m30.closemanage.action.M30CloseInvoiceOpenAction();
  context.put("invoiceOpenAction",bean);
  bean.setModel(getBatchBillTableModel());
  bean.setBillTable(getList());
  bean.setSingleBillService(getM30CloseInvoiceOpenService_1ce697e());
  bean.setRefreshAction(getRefreshAction());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.so.m30.closemanage.model.M30CloseInvoiceOpenService getM30CloseInvoiceOpenService_1ce697e(){
 if(context.get("nc.ui.so.m30.closemanage.model.M30CloseInvoiceOpenService#1ce697e")!=null)
 return (nc.ui.so.m30.closemanage.model.M30CloseInvoiceOpenService)context.get("nc.ui.so.m30.closemanage.model.M30CloseInvoiceOpenService#1ce697e");
  nc.ui.so.m30.closemanage.model.M30CloseInvoiceOpenService bean = new nc.ui.so.m30.closemanage.model.M30CloseInvoiceOpenService();
  context.put("nc.ui.so.m30.closemanage.model.M30CloseInvoiceOpenService#1ce697e",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.funcnode.ui.action.MenuAction getSettleCloseActionGroup(){
 if(context.get("settleCloseActionGroup")!=null)
 return (nc.funcnode.ui.action.MenuAction)context.get("settleCloseActionGroup");
  nc.funcnode.ui.action.MenuAction bean = new nc.funcnode.ui.action.MenuAction();
  context.put("settleCloseActionGroup",bean);
  bean.setCode("settleCloseGroup");
  bean.setName(getI18nFB_6fe39c());
  bean.setActions(getManagedList6());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private java.lang.String getI18nFB_6fe39c(){
 if(context.get("nc.ui.uif2.I18nFB#6fe39c")!=null)
 return (java.lang.String)context.get("nc.ui.uif2.I18nFB#6fe39c");
  nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
    context.put("&nc.ui.uif2.I18nFB#6fe39c",bean);  bean.setResDir("4006011_0");
  bean.setResId("04006011-0409");
  bean.setDefaultValue("结算关闭");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
 try {
     Object product = bean.getObject();
    context.put("nc.ui.uif2.I18nFB#6fe39c",product);
     return (java.lang.String)product;
}
catch(Exception e) { throw new RuntimeException(e);}}

private List getManagedList6(){  List list = new ArrayList();  list.add(getSettleCloseAction());  list.add(getSettleOpenAction());  return list;}

public nc.ui.so.m30.closemanage.action.M30CloseSettleCloseAction getSettleCloseAction(){
 if(context.get("settleCloseAction")!=null)
 return (nc.ui.so.m30.closemanage.action.M30CloseSettleCloseAction)context.get("settleCloseAction");
  nc.ui.so.m30.closemanage.action.M30CloseSettleCloseAction bean = new nc.ui.so.m30.closemanage.action.M30CloseSettleCloseAction();
  context.put("settleCloseAction",bean);
  bean.setModel(getBatchBillTableModel());
  bean.setBillTable(getList());
  bean.setSingleBillService(getM30CloseSettleCloseService_1c8483a());
  bean.setRefreshAction(getRefreshAction());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.so.m30.closemanage.model.M30CloseSettleCloseService getM30CloseSettleCloseService_1c8483a(){
 if(context.get("nc.ui.so.m30.closemanage.model.M30CloseSettleCloseService#1c8483a")!=null)
 return (nc.ui.so.m30.closemanage.model.M30CloseSettleCloseService)context.get("nc.ui.so.m30.closemanage.model.M30CloseSettleCloseService#1c8483a");
  nc.ui.so.m30.closemanage.model.M30CloseSettleCloseService bean = new nc.ui.so.m30.closemanage.model.M30CloseSettleCloseService();
  context.put("nc.ui.so.m30.closemanage.model.M30CloseSettleCloseService#1c8483a",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.so.m30.closemanage.action.M30CloseSettleOpenAction getSettleOpenAction(){
 if(context.get("settleOpenAction")!=null)
 return (nc.ui.so.m30.closemanage.action.M30CloseSettleOpenAction)context.get("settleOpenAction");
  nc.ui.so.m30.closemanage.action.M30CloseSettleOpenAction bean = new nc.ui.so.m30.closemanage.action.M30CloseSettleOpenAction();
  context.put("settleOpenAction",bean);
  bean.setModel(getBatchBillTableModel());
  bean.setBillTable(getList());
  bean.setSingleBillService(getM30CloseSettleOpenService_1fe01c8());
  bean.setRefreshAction(getRefreshAction());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.so.m30.closemanage.model.M30CloseSettleOpenService getM30CloseSettleOpenService_1fe01c8(){
 if(context.get("nc.ui.so.m30.closemanage.model.M30CloseSettleOpenService#1fe01c8")!=null)
 return (nc.ui.so.m30.closemanage.model.M30CloseSettleOpenService)context.get("nc.ui.so.m30.closemanage.model.M30CloseSettleOpenService#1fe01c8");
  nc.ui.so.m30.closemanage.model.M30CloseSettleOpenService bean = new nc.ui.so.m30.closemanage.model.M30CloseSettleOpenService();
  context.put("nc.ui.so.m30.closemanage.model.M30CloseSettleOpenService#1fe01c8",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.so.m30.closemanage.action.M30CloseLinkQueryAction getLinkQueryAction(){
 if(context.get("linkQueryAction")!=null)
 return (nc.ui.so.m30.closemanage.action.M30CloseLinkQueryAction)context.get("linkQueryAction");
  nc.ui.so.m30.closemanage.action.M30CloseLinkQueryAction bean = new nc.ui.so.m30.closemanage.action.M30CloseLinkQueryAction();
  context.put("linkQueryAction",bean);
  bean.setModel(getBatchBillTableModel());
  bean.setBillType("30");
  bean.setOpenMode(1);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.funcnode.ui.action.GroupAction getPrintActionGroup(){
 if(context.get("printActionGroup")!=null)
 return (nc.funcnode.ui.action.GroupAction)context.get("printActionGroup");
  nc.funcnode.ui.action.GroupAction bean = new nc.funcnode.ui.action.GroupAction();
  context.put("printActionGroup",bean);
  bean.setCode("printGroup");
  bean.setName(getI18nFB_1270c22());
  bean.setActions(getManagedList7());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private java.lang.String getI18nFB_1270c22(){
 if(context.get("nc.ui.uif2.I18nFB#1270c22")!=null)
 return (java.lang.String)context.get("nc.ui.uif2.I18nFB#1270c22");
  nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
    context.put("&nc.ui.uif2.I18nFB#1270c22",bean);  bean.setResDir("common");
  bean.setResId("UC001-0000007");
  bean.setDefaultValue("打印");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
 try {
     Object product = bean.getObject();
    context.put("nc.ui.uif2.I18nFB#1270c22",product);
     return (java.lang.String)product;
}
catch(Exception e) { throw new RuntimeException(e);}}

private List getManagedList7(){  List list = new ArrayList();  list.add(getPrintAction());  list.add(getPreviewAction());  return list;}

public nc.ui.so.m30.closemanage.action.M30ClosePreViewAction getPreviewAction(){
 if(context.get("previewAction")!=null)
 return (nc.ui.so.m30.closemanage.action.M30ClosePreViewAction)context.get("previewAction");
  nc.ui.so.m30.closemanage.action.M30ClosePreViewAction bean = new nc.ui.so.m30.closemanage.action.M30ClosePreViewAction();
  context.put("previewAction",bean);
  bean.setPreview(true);
  bean.setModel(getBatchBillTableModel());
  bean.setBeforePrintDataProcess(getPrintProcessor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.so.m30.closemanage.action.M30ClosePreViewAction getPrintAction(){
 if(context.get("printAction")!=null)
 return (nc.ui.so.m30.closemanage.action.M30ClosePreViewAction)context.get("printAction");
  nc.ui.so.m30.closemanage.action.M30ClosePreViewAction bean = new nc.ui.so.m30.closemanage.action.M30ClosePreViewAction();
  context.put("printAction",bean);
  bean.setPreview(false);
  bean.setModel(getBatchBillTableModel());
  bean.setBeforePrintDataProcess(getPrintProcessor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.so.m30.billui.action.printaction.SaleOrderPrintProcessor getPrintProcessor(){
 if(context.get("printProcessor")!=null)
 return (nc.ui.so.m30.billui.action.printaction.SaleOrderPrintProcessor)context.get("printProcessor");
  nc.ui.so.m30.billui.action.printaction.SaleOrderPrintProcessor bean = new nc.ui.so.m30.billui.action.printaction.SaleOrderPrintProcessor();
  context.put("printProcessor",bean);
  bean.setBatchmodel(getBatchBillTableModel());
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
  bean.setModel(getBatchBillTableModel());
  bean.setVoClassName("nc.vo.so.m30.entity.SaleOrderViewVO");
  bean.setQueryAction(getQueryAction());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

}
