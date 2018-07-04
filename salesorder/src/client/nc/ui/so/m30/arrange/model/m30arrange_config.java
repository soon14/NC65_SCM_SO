package nc.ui.so.m30.arrange.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.ui.uif2.factory.AbstractJavaBeanDefinition;

public class m30arrange_config extends AbstractJavaBeanDefinition{
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

public nc.ui.pubapp.billref.push.TabBeanList getSrcBeanList(){
 if(context.get("srcBeanList")!=null)
 return (nc.ui.pubapp.billref.push.TabBeanList)context.get("srcBeanList");
  nc.ui.pubapp.billref.push.TabBeanList bean = new nc.ui.pubapp.billref.push.TabBeanList();
  context.put("srcBeanList",bean);
  bean.setBeanList(getManagedList0());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList0(){  List list = new ArrayList();  list.add(getTabBeanInfo_456cd1());  list.add(getTabBeanInfo_1f714c8());  return list;}

private nc.ui.pubapp.billref.push.TabBeanInfo getTabBeanInfo_456cd1(){
 if(context.get("nc.ui.pubapp.billref.push.TabBeanInfo#456cd1")!=null)
 return (nc.ui.pubapp.billref.push.TabBeanInfo)context.get("nc.ui.pubapp.billref.push.TabBeanInfo#456cd1");
  nc.ui.pubapp.billref.push.TabBeanInfo bean = new nc.ui.pubapp.billref.push.TabBeanInfo();
  context.put("nc.ui.pubapp.billref.push.TabBeanInfo#456cd1",bean);
  bean.setModuleCode("4006");
  bean.setTabName(getI18nFB_1a2c15f());
  bean.setBeanId("saleOrder");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private java.lang.String getI18nFB_1a2c15f(){
 if(context.get("nc.ui.uif2.I18nFB#1a2c15f")!=null)
 return (java.lang.String)context.get("nc.ui.uif2.I18nFB#1a2c15f");
  nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
    context.put("&nc.ui.uif2.I18nFB#1a2c15f",bean);  bean.setResDir("4006011_0");
  bean.setResId("04006011-0402");
  bean.setDefaultValue("销售订单");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
 try {
     Object product = bean.getObject();
    context.put("nc.ui.uif2.I18nFB#1a2c15f",product);
     return (java.lang.String)product;
}
catch(Exception e) { throw new RuntimeException(e);}}

private nc.ui.pubapp.billref.push.TabBeanInfo getTabBeanInfo_1f714c8(){
 if(context.get("nc.ui.pubapp.billref.push.TabBeanInfo#1f714c8")!=null)
 return (nc.ui.pubapp.billref.push.TabBeanInfo)context.get("nc.ui.pubapp.billref.push.TabBeanInfo#1f714c8");
  nc.ui.pubapp.billref.push.TabBeanInfo bean = new nc.ui.pubapp.billref.push.TabBeanInfo();
  context.put("nc.ui.pubapp.billref.push.TabBeanInfo#1f714c8",bean);
  bean.setModuleCode("4009");
  bean.setTabName(getI18nFB_1eb3161());
  bean.setBeanId("transferOrder");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private java.lang.String getI18nFB_1eb3161(){
 if(context.get("nc.ui.uif2.I18nFB#1eb3161")!=null)
 return (java.lang.String)context.get("nc.ui.uif2.I18nFB#1eb3161");
  nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
    context.put("&nc.ui.uif2.I18nFB#1eb3161",bean);  bean.setResDir("4006011_0");
  bean.setResId("04006011-0403");
  bean.setDefaultValue("调拨订单");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
 try {
     Object product = bean.getObject();
    context.put("nc.ui.uif2.I18nFB#1eb3161",product);
     return (java.lang.String)product;
}
catch(Exception e) { throw new RuntimeException(e);}}

public nc.ui.pubapp.billref.push.TabBillInfo getSaleOrder(){
 if(context.get("saleOrder")!=null)
 return (nc.ui.pubapp.billref.push.TabBillInfo)context.get("saleOrder");
  nc.ui.pubapp.billref.push.TabBillInfo bean = new nc.ui.pubapp.billref.push.TabBillInfo();
  context.put("saleOrder",bean);
  bean.setMarAsstPreparator(getOrdermarAsstPreparator());
  bean.setDefDataPreparator(getSaleuserdefitemlistPreparator());
  bean.setBillType("30");
  bean.setBillNodeKey("30BZSource");
  bean.setBillViewNodeKey("30BZSource");
  bean.setNodeCode("40060301");
  bean.setViewShow(false);
  bean.setRewriteService(getSaleOrderArrangeService_530c93());
  bean.setInitDataListener(getM30InitDataListener_5eb32b());
  bean.setBillListDigitSetter(getM30DigitSetter_145008e());
  bean.setModuleCode("4006");
  bean.setHeadVOClass("nc.vo.so.m30.entity.SaleOrderHVO");
  bean.setBodyVOClass("nc.vo.so.m30.entity.SaleOrderBVO");
  bean.setBillVOClass("nc.vo.so.m30.entity.SaleOrderVO");
  bean.setViewVOClass("nc.vo.so.m30.entity.SaleOrderViewVO");
  bean.setLinkListener(getM30linkListener());
  bean.setListeners(getManagedList1());
  bean.setActions(getManagedList2());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.so.m30.arrange.model.SaleOrderArrangeService getSaleOrderArrangeService_530c93(){
 if(context.get("nc.ui.so.m30.arrange.model.SaleOrderArrangeService#530c93")!=null)
 return (nc.ui.so.m30.arrange.model.SaleOrderArrangeService)context.get("nc.ui.so.m30.arrange.model.SaleOrderArrangeService#530c93");
  nc.ui.so.m30.arrange.model.SaleOrderArrangeService bean = new nc.ui.so.m30.arrange.model.SaleOrderArrangeService();
  context.put("nc.ui.so.m30.arrange.model.SaleOrderArrangeService#530c93",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.so.m30.arrange.listener.M30InitDataListener getM30InitDataListener_5eb32b(){
 if(context.get("nc.ui.so.m30.arrange.listener.M30InitDataListener#5eb32b")!=null)
 return (nc.ui.so.m30.arrange.listener.M30InitDataListener)context.get("nc.ui.so.m30.arrange.listener.M30InitDataListener#5eb32b");
  nc.ui.so.m30.arrange.listener.M30InitDataListener bean = new nc.ui.so.m30.arrange.listener.M30InitDataListener();
  context.put("nc.ui.so.m30.arrange.listener.M30InitDataListener#5eb32b",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.so.m30.arrange.scale.M30DigitSetter getM30DigitSetter_145008e(){
 if(context.get("nc.ui.so.m30.arrange.scale.M30DigitSetter#145008e")!=null)
 return (nc.ui.so.m30.arrange.scale.M30DigitSetter)context.get("nc.ui.so.m30.arrange.scale.M30DigitSetter#145008e");
  nc.ui.so.m30.arrange.scale.M30DigitSetter bean = new nc.ui.so.m30.arrange.scale.M30DigitSetter();
  context.put("nc.ui.so.m30.arrange.scale.M30DigitSetter#145008e",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList1(){  List list = new ArrayList();  list.add(getEditorEventHandler_1279557());  return list;}

private nc.ui.so.m30.arrange.editor.EditorEventHandler getEditorEventHandler_1279557(){
 if(context.get("nc.ui.so.m30.arrange.editor.EditorEventHandler#1279557")!=null)
 return (nc.ui.so.m30.arrange.editor.EditorEventHandler)context.get("nc.ui.so.m30.arrange.editor.EditorEventHandler#1279557");
  nc.ui.so.m30.arrange.editor.EditorEventHandler bean = new nc.ui.so.m30.arrange.editor.EditorEventHandler();
  context.put("nc.ui.so.m30.arrange.editor.EditorEventHandler#1279557",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList2(){  List list = new ArrayList();  list.add(getM30ArrangeQueryAction_1efcb09());  list.add(getSeparatorAction_1faca33());  list.add(getMenuAction_1381575());  list.add(getMenuAction_183c747());  list.add(getSeparatorAction_903c6f());  list.add(getMenuAction_12dd87());  return list;}

private nc.ui.so.m30.arrange.action.M30ArrangeQueryAction getM30ArrangeQueryAction_1efcb09(){
 if(context.get("nc.ui.so.m30.arrange.action.M30ArrangeQueryAction#1efcb09")!=null)
 return (nc.ui.so.m30.arrange.action.M30ArrangeQueryAction)context.get("nc.ui.so.m30.arrange.action.M30ArrangeQueryAction#1efcb09");
  nc.ui.so.m30.arrange.action.M30ArrangeQueryAction bean = new nc.ui.so.m30.arrange.action.M30ArrangeQueryAction();
  context.put("nc.ui.so.m30.arrange.action.M30ArrangeQueryAction#1efcb09",bean);
  bean.setNodeKey("30toBZ");
  bean.setDataManager(getPushBillModelDataManager_4a1de1());
  bean.setQryCondDLGInitializer(getM30qryCondDLGInitializer());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.pubapp.billref.push.PushBillModelDataManager getPushBillModelDataManager_4a1de1(){
 if(context.get("nc.ui.pubapp.billref.push.PushBillModelDataManager#4a1de1")!=null)
 return (nc.ui.pubapp.billref.push.PushBillModelDataManager)context.get("nc.ui.pubapp.billref.push.PushBillModelDataManager#4a1de1");
  nc.ui.pubapp.billref.push.PushBillModelDataManager bean = new nc.ui.pubapp.billref.push.PushBillModelDataManager();
  context.put("nc.ui.pubapp.billref.push.PushBillModelDataManager#4a1de1",bean);
  bean.setService(getSaleOrderArrangeService_1bdaf74());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.so.m30.arrange.model.SaleOrderArrangeService getSaleOrderArrangeService_1bdaf74(){
 if(context.get("nc.ui.so.m30.arrange.model.SaleOrderArrangeService#1bdaf74")!=null)
 return (nc.ui.so.m30.arrange.model.SaleOrderArrangeService)context.get("nc.ui.so.m30.arrange.model.SaleOrderArrangeService#1bdaf74");
  nc.ui.so.m30.arrange.model.SaleOrderArrangeService bean = new nc.ui.so.m30.arrange.model.SaleOrderArrangeService();
  context.put("nc.ui.so.m30.arrange.model.SaleOrderArrangeService#1bdaf74",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.funcnode.ui.action.SeparatorAction getSeparatorAction_1faca33(){
 if(context.get("nc.funcnode.ui.action.SeparatorAction#1faca33")!=null)
 return (nc.funcnode.ui.action.SeparatorAction)context.get("nc.funcnode.ui.action.SeparatorAction#1faca33");
  nc.funcnode.ui.action.SeparatorAction bean = new nc.funcnode.ui.action.SeparatorAction();
  context.put("nc.funcnode.ui.action.SeparatorAction#1faca33",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.funcnode.ui.action.MenuAction getMenuAction_1381575(){
 if(context.get("nc.funcnode.ui.action.MenuAction#1381575")!=null)
 return (nc.funcnode.ui.action.MenuAction)context.get("nc.funcnode.ui.action.MenuAction#1381575");
  nc.funcnode.ui.action.MenuAction bean = new nc.funcnode.ui.action.MenuAction();
  context.put("nc.funcnode.ui.action.MenuAction#1381575",bean);
  bean.setCode("BHArrange");
  bean.setName(getI18nFB_1f0e89e());
  bean.setActions(getManagedList3());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private java.lang.String getI18nFB_1f0e89e(){
 if(context.get("nc.ui.uif2.I18nFB#1f0e89e")!=null)
 return (java.lang.String)context.get("nc.ui.uif2.I18nFB#1f0e89e");
  nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
    context.put("&nc.ui.uif2.I18nFB#1f0e89e",bean);  bean.setResDir("4006011_0");
  bean.setResId("04006011-0390");
  bean.setDefaultValue("补货安排");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
 try {
     Object product = bean.getObject();
    context.put("nc.ui.uif2.I18nFB#1f0e89e",product);
     return (java.lang.String)product;
}
catch(Exception e) { throw new RuntimeException(e);}}

private List getManagedList3(){  List list = new ArrayList();  list.add(getM30arrange20Action());  list.add(getM30arrange21Action());  list.add(getM30arrange5AAction());  list.add(getM30arrange5XAction());  list.add(getM30arrange61Action());  list.add(getM30arrangeA2Action());  return list;}

private nc.funcnode.ui.action.MenuAction getMenuAction_183c747(){
 if(context.get("nc.funcnode.ui.action.MenuAction#183c747")!=null)
 return (nc.funcnode.ui.action.MenuAction)context.get("nc.funcnode.ui.action.MenuAction#183c747");
  nc.funcnode.ui.action.MenuAction bean = new nc.funcnode.ui.action.MenuAction();
  context.put("nc.funcnode.ui.action.MenuAction#183c747",bean);
  bean.setCode("m30ZYArrange");
  bean.setName(getI18nFB_108e736());
  bean.setActions(getManagedList4());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private java.lang.String getI18nFB_108e736(){
 if(context.get("nc.ui.uif2.I18nFB#108e736")!=null)
 return (java.lang.String)context.get("nc.ui.uif2.I18nFB#108e736");
  nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
    context.put("&nc.ui.uif2.I18nFB#108e736",bean);  bean.setResDir("4006011_0");
  bean.setResId("04006011-0391");
  bean.setDefaultValue("直运安排");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
 try {
     Object product = bean.getObject();
    context.put("nc.ui.uif2.I18nFB#108e736",product);
     return (java.lang.String)product;
}
catch(Exception e) { throw new RuntimeException(e);}}

private List getManagedList4(){  List list = new ArrayList();  list.add(getM30zyarrange20Action());  list.add(getM30zyarrange21Action());  list.add(getM30zyarrange5AAction());  list.add(getM30zyarrange5XAction());  return list;}

private nc.funcnode.ui.action.SeparatorAction getSeparatorAction_903c6f(){
 if(context.get("nc.funcnode.ui.action.SeparatorAction#903c6f")!=null)
 return (nc.funcnode.ui.action.SeparatorAction)context.get("nc.funcnode.ui.action.SeparatorAction#903c6f");
  nc.funcnode.ui.action.SeparatorAction bean = new nc.funcnode.ui.action.SeparatorAction();
  context.put("nc.funcnode.ui.action.SeparatorAction#903c6f",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.funcnode.ui.action.MenuAction getMenuAction_12dd87(){
 if(context.get("nc.funcnode.ui.action.MenuAction#12dd87")!=null)
 return (nc.funcnode.ui.action.MenuAction)context.get("nc.funcnode.ui.action.MenuAction#12dd87");
  nc.funcnode.ui.action.MenuAction bean = new nc.funcnode.ui.action.MenuAction();
  context.put("nc.funcnode.ui.action.MenuAction#12dd87",bean);
  bean.setCode("linkQueryGroup");
  bean.setName(getI18nFB_16fc1a7());
  bean.setActions(getManagedList5());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private java.lang.String getI18nFB_16fc1a7(){
 if(context.get("nc.ui.uif2.I18nFB#16fc1a7")!=null)
 return (java.lang.String)context.get("nc.ui.uif2.I18nFB#16fc1a7");
  nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
    context.put("&nc.ui.uif2.I18nFB#16fc1a7",bean);  bean.setResDir("4006011_0");
  bean.setResId("04006011-0392");
  bean.setDefaultValue("联查");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
 try {
     Object product = bean.getObject();
    context.put("nc.ui.uif2.I18nFB#16fc1a7",product);
     return (java.lang.String)product;
}
catch(Exception e) { throw new RuntimeException(e);}}

private List getManagedList5(){  List list = new ArrayList();  list.add(getOrderlinkQueryAction());  list.add(getSpShowHiddenAction());  return list;}

public nc.ui.pubapp.billref.push.TabBillInfo getTransferOrder(){
 if(context.get("transferOrder")!=null)
 return (nc.ui.pubapp.billref.push.TabBillInfo)context.get("transferOrder");
  nc.ui.pubapp.billref.push.TabBillInfo bean = new nc.ui.pubapp.billref.push.TabBillInfo();
  context.put("transferOrder",bean);
  bean.setMarAsstPreparator(getTransordermarAsstPreparator());
  bean.setDefDataPreparator(getTranuserdefitemlistPreparator());
  bean.setBillType("5X");
  bean.setBillNodeKey("5Xto3Z");
  bean.setBillViewNodeKey("5Xto3Z");
  bean.setNodeCode("40093010");
  bean.setViewShow(false);
  bean.setRewriteService(getRewriteArrangedNumTo5X_8dfa51());
  bean.setBillListDigitSetter(getM5xPrecisionUtilFor3Z_12cf0a4());
  bean.setModuleCode("4009");
  bean.setHeadVOClass("nc.vo.to.m5x.entity.BillHeaderVO");
  bean.setBodyVOClass("nc.vo.to.m5x.entity.BillItemVO");
  bean.setBillVOClass("nc.vo.to.m5x.entity.BillVO");
  bean.setViewVOClass("nc.vo.to.m5x.entity.BillViewVO");
  bean.setLinkListener(getM5XlinkListener());
  bean.setActions(getManagedList6());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.to.m5x.billref.m3z.RewriteArrangedNumTo5X getRewriteArrangedNumTo5X_8dfa51(){
 if(context.get("nc.ui.to.m5x.billref.m3z.RewriteArrangedNumTo5X#8dfa51")!=null)
 return (nc.ui.to.m5x.billref.m3z.RewriteArrangedNumTo5X)context.get("nc.ui.to.m5x.billref.m3z.RewriteArrangedNumTo5X#8dfa51");
  nc.ui.to.m5x.billref.m3z.RewriteArrangedNumTo5X bean = new nc.ui.to.m5x.billref.m3z.RewriteArrangedNumTo5X();
  context.put("nc.ui.to.m5x.billref.m3z.RewriteArrangedNumTo5X#8dfa51",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.to.m5x.billref.m3z.M5xPrecisionUtilFor3Z getM5xPrecisionUtilFor3Z_12cf0a4(){
 if(context.get("nc.ui.to.m5x.billref.m3z.M5xPrecisionUtilFor3Z#12cf0a4")!=null)
 return (nc.ui.to.m5x.billref.m3z.M5xPrecisionUtilFor3Z)context.get("nc.ui.to.m5x.billref.m3z.M5xPrecisionUtilFor3Z#12cf0a4");
  nc.ui.to.m5x.billref.m3z.M5xPrecisionUtilFor3Z bean = new nc.ui.to.m5x.billref.m3z.M5xPrecisionUtilFor3Z();
  context.put("nc.ui.to.m5x.billref.m3z.M5xPrecisionUtilFor3Z#12cf0a4",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList6(){  List list = new ArrayList();  list.add(getM5Xto3ZQueryAction_e58f55());  list.add(getSeparatorAction_125319b());  list.add(getMenuAction_218ce1());  list.add(getSeparatorAction_a82ab8());  list.add(getMenuAction_169845a());  return list;}

private nc.ui.so.m30.arrange.action.M5Xto3ZQueryAction getM5Xto3ZQueryAction_e58f55(){
 if(context.get("nc.ui.so.m30.arrange.action.M5Xto3ZQueryAction#e58f55")!=null)
 return (nc.ui.so.m30.arrange.action.M5Xto3ZQueryAction)context.get("nc.ui.so.m30.arrange.action.M5Xto3ZQueryAction#e58f55");
  nc.ui.so.m30.arrange.action.M5Xto3ZQueryAction bean = new nc.ui.so.m30.arrange.action.M5Xto3ZQueryAction();
  context.put("nc.ui.so.m30.arrange.action.M5Xto3ZQueryAction#e58f55",bean);
  bean.setNodeKey("5Xto3ZQuery");
  bean.setDataManager(getPushBillModelDataManager_1134825());
  bean.setQryCondDLGInitializer(getM5XqryCondDLGInitializer());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.pubapp.billref.push.PushBillModelDataManager getPushBillModelDataManager_1134825(){
 if(context.get("nc.ui.pubapp.billref.push.PushBillModelDataManager#1134825")!=null)
 return (nc.ui.pubapp.billref.push.PushBillModelDataManager)context.get("nc.ui.pubapp.billref.push.PushBillModelDataManager#1134825");
  nc.ui.pubapp.billref.push.PushBillModelDataManager bean = new nc.ui.pubapp.billref.push.PushBillModelDataManager();
  context.put("nc.ui.pubapp.billref.push.PushBillModelDataManager#1134825",bean);
  bean.setService(getTransOrderQueryFor3ZService_16a9f96());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.to.m5x.billref.m3z.TransOrderQueryFor3ZService getTransOrderQueryFor3ZService_16a9f96(){
 if(context.get("nc.ui.to.m5x.billref.m3z.TransOrderQueryFor3ZService#16a9f96")!=null)
 return (nc.ui.to.m5x.billref.m3z.TransOrderQueryFor3ZService)context.get("nc.ui.to.m5x.billref.m3z.TransOrderQueryFor3ZService#16a9f96");
  nc.ui.to.m5x.billref.m3z.TransOrderQueryFor3ZService bean = new nc.ui.to.m5x.billref.m3z.TransOrderQueryFor3ZService();
  context.put("nc.ui.to.m5x.billref.m3z.TransOrderQueryFor3ZService#16a9f96",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.funcnode.ui.action.SeparatorAction getSeparatorAction_125319b(){
 if(context.get("nc.funcnode.ui.action.SeparatorAction#125319b")!=null)
 return (nc.funcnode.ui.action.SeparatorAction)context.get("nc.funcnode.ui.action.SeparatorAction#125319b");
  nc.funcnode.ui.action.SeparatorAction bean = new nc.funcnode.ui.action.SeparatorAction();
  context.put("nc.funcnode.ui.action.SeparatorAction#125319b",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.funcnode.ui.action.MenuAction getMenuAction_218ce1(){
 if(context.get("nc.funcnode.ui.action.MenuAction#218ce1")!=null)
 return (nc.funcnode.ui.action.MenuAction)context.get("nc.funcnode.ui.action.MenuAction#218ce1");
  nc.funcnode.ui.action.MenuAction bean = new nc.funcnode.ui.action.MenuAction();
  context.put("nc.funcnode.ui.action.MenuAction#218ce1",bean);
  bean.setCode("BHArrange");
  bean.setName(getI18nFB_dbc402());
  bean.setActions(getManagedList7());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private java.lang.String getI18nFB_dbc402(){
 if(context.get("nc.ui.uif2.I18nFB#dbc402")!=null)
 return (java.lang.String)context.get("nc.ui.uif2.I18nFB#dbc402");
  nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
    context.put("&nc.ui.uif2.I18nFB#dbc402",bean);  bean.setResDir("4006011_0");
  bean.setResId("04006011-0390");
  bean.setDefaultValue("补货安排");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
 try {
     Object product = bean.getObject();
    context.put("nc.ui.uif2.I18nFB#dbc402",product);
     return (java.lang.String)product;
}
catch(Exception e) { throw new RuntimeException(e);}}

private List getManagedList7(){  List list = new ArrayList();  list.add(getM5Xarrange20Action());  list.add(getM5Xarrange21Action());  list.add(getM5Xarrange5AAction());  list.add(getM5Xarrange5XAction());  list.add(getM5Xarrange61Action());  list.add(getM5XarrangeA2Action());  return list;}

private nc.funcnode.ui.action.SeparatorAction getSeparatorAction_a82ab8(){
 if(context.get("nc.funcnode.ui.action.SeparatorAction#a82ab8")!=null)
 return (nc.funcnode.ui.action.SeparatorAction)context.get("nc.funcnode.ui.action.SeparatorAction#a82ab8");
  nc.funcnode.ui.action.SeparatorAction bean = new nc.funcnode.ui.action.SeparatorAction();
  context.put("nc.funcnode.ui.action.SeparatorAction#a82ab8",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.funcnode.ui.action.MenuAction getMenuAction_169845a(){
 if(context.get("nc.funcnode.ui.action.MenuAction#169845a")!=null)
 return (nc.funcnode.ui.action.MenuAction)context.get("nc.funcnode.ui.action.MenuAction#169845a");
  nc.funcnode.ui.action.MenuAction bean = new nc.funcnode.ui.action.MenuAction();
  context.put("nc.funcnode.ui.action.MenuAction#169845a",bean);
  bean.setCode("linkQueryGroup");
  bean.setName(getI18nFB_8cd2e4());
  bean.setActions(getManagedList8());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private java.lang.String getI18nFB_8cd2e4(){
 if(context.get("nc.ui.uif2.I18nFB#8cd2e4")!=null)
 return (java.lang.String)context.get("nc.ui.uif2.I18nFB#8cd2e4");
  nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
    context.put("&nc.ui.uif2.I18nFB#8cd2e4",bean);  bean.setResDir("4006011_0");
  bean.setResId("04006011-0392");
  bean.setDefaultValue("联查");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
 try {
     Object product = bean.getObject();
    context.put("nc.ui.uif2.I18nFB#8cd2e4",product);
     return (java.lang.String)product;
}
catch(Exception e) { throw new RuntimeException(e);}}

private List getManagedList8(){  List list = new ArrayList();  list.add(getX5linkQueryAction());  list.add(getX5spShowHiddenAction());  return list;}

public nc.ui.so.m30.arrange.listener.QueryDLGInitializer getM30qryCondDLGInitializer(){
 if(context.get("m30qryCondDLGInitializer")!=null)
 return (nc.ui.so.m30.arrange.listener.QueryDLGInitializer)context.get("m30qryCondDLGInitializer");
  nc.ui.so.m30.arrange.listener.QueryDLGInitializer bean = new nc.ui.so.m30.arrange.listener.QueryDLGInitializer();
  context.put("m30qryCondDLGInitializer",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.to.m5x.billref.m3z.QueryDLGInitializerFor3Z getM5XqryCondDLGInitializer(){
 if(context.get("m5XqryCondDLGInitializer")!=null)
 return (nc.ui.to.m5x.billref.m3z.QueryDLGInitializerFor3Z)context.get("m5XqryCondDLGInitializer");
  nc.ui.to.m5x.billref.m3z.QueryDLGInitializerFor3Z bean = new nc.ui.to.m5x.billref.m3z.QueryDLGInitializerFor3Z();
  context.put("m5XqryCondDLGInitializer",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.billref.push.OpenNodePushActionAdapter getM30arrange20Action(){
 if(context.get("m30arrange20Action")!=null)
 return (nc.ui.pubapp.billref.push.OpenNodePushActionAdapter)context.get("m30arrange20Action");
  nc.ui.pubapp.billref.push.OpenNodePushActionAdapter bean = new nc.ui.pubapp.billref.push.OpenNodePushActionAdapter();
  context.put("m30arrange20Action",bean);
  bean.setModuleCode("4004");
  bean.setActionName("nc.ui.pubapp.billref.push.OpenNodePushAction");
  bean.setActionCode("30BH20");
  bean.setDestBillType("20");
  bean.setDestNodeCode("40040200");
  bean.setOpenNodePushBeforeVOChange(getM30beforeVOChange());
  bean.setShowOrgPanel(false);
  bean.setSourceVOUpdate(getM30PushSourceVOUpdate());
  bean.setSourceVOStrategy(getM30SourceVOStrategy());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.billref.push.OpenNodePushActionAdapter getM5Xarrange20Action(){
 if(context.get("m5Xarrange20Action")!=null)
 return (nc.ui.pubapp.billref.push.OpenNodePushActionAdapter)context.get("m5Xarrange20Action");
  nc.ui.pubapp.billref.push.OpenNodePushActionAdapter bean = new nc.ui.pubapp.billref.push.OpenNodePushActionAdapter();
  context.put("m5Xarrange20Action",bean);
  bean.setModuleCode("4004");
  bean.setActionName("nc.ui.pubapp.billref.push.OpenNodePushAction");
  bean.setActionCode("5XBH20");
  bean.setDestBillType("20");
  bean.setDestNodeCode("40040200");
  bean.setOpenNodePushBeforeVOChange(getM5XbeforeVOChange());
  bean.setShowOrgPanel(false);
  bean.setSourceVOUpdate(getM5XPushSourceVOUpdate());
  bean.setSourceVOStrategy(getM5XSourceVOStrategy());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.billref.push.OpenNodePushActionAdapter getM30arrange21Action(){
 if(context.get("m30arrange21Action")!=null)
 return (nc.ui.pubapp.billref.push.OpenNodePushActionAdapter)context.get("m30arrange21Action");
  nc.ui.pubapp.billref.push.OpenNodePushActionAdapter bean = new nc.ui.pubapp.billref.push.OpenNodePushActionAdapter();
  context.put("m30arrange21Action",bean);
  bean.setModuleCode("4004");
  bean.setActionName("nc.ui.so.m30.arrange.push.DefaultOpenNodePushAction");
  bean.setActionCode("30BH21");
  bean.setDestBillType("21");
  bean.setDestNodeCode("40040400");
  bean.setOpenNodePushBeforeVOChange(getM30beforeVOChange());
  bean.setSourceVOUpdate(getM30PushSourceVOUpdate());
  bean.setSourceVOStrategy(getM30SourceVOStrategy());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.billref.push.OpenNodePushActionAdapter getM5Xarrange21Action(){
 if(context.get("m5Xarrange21Action")!=null)
 return (nc.ui.pubapp.billref.push.OpenNodePushActionAdapter)context.get("m5Xarrange21Action");
  nc.ui.pubapp.billref.push.OpenNodePushActionAdapter bean = new nc.ui.pubapp.billref.push.OpenNodePushActionAdapter();
  context.put("m5Xarrange21Action",bean);
  bean.setModuleCode("4004");
  bean.setActionName("nc.ui.so.m30.arrange.push.DefaultOpenNodePushAction");
  bean.setActionCode("5XBH21");
  bean.setDestBillType("21");
  bean.setDestNodeCode("40040400");
  bean.setOpenNodePushBeforeVOChange(getM5XbeforeVOChange());
  bean.setSourceVOUpdate(getM5XPushSourceVOUpdate());
  bean.setSourceVOStrategy(getM5XSourceVOStrategy());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.billref.push.OpenNodePushActionAdapter getM30arrange5AAction(){
 if(context.get("m30arrange5AAction")!=null)
 return (nc.ui.pubapp.billref.push.OpenNodePushActionAdapter)context.get("m30arrange5AAction");
  nc.ui.pubapp.billref.push.OpenNodePushActionAdapter bean = new nc.ui.pubapp.billref.push.OpenNodePushActionAdapter();
  context.put("m30arrange5AAction",bean);
  bean.setModuleCode("4009");
  bean.setActionName("nc.ui.to.m5a.billref.m30.OpenNodePushActionM5A");
  bean.setActionCode("30BH5A");
  bean.setDestBillType("5A");
  bean.setDestNodeCode("40092010");
  bean.setOpenNodePushBeforeVOChange(getM30beforeVOChange());
  bean.setSourceVOUpdate(getM30PushSourceVOUpdate());
  bean.setSourceVOStrategy(getM30SourceVOStrategy());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.billref.push.OpenNodePushActionAdapter getM5Xarrange5AAction(){
 if(context.get("m5Xarrange5AAction")!=null)
 return (nc.ui.pubapp.billref.push.OpenNodePushActionAdapter)context.get("m5Xarrange5AAction");
  nc.ui.pubapp.billref.push.OpenNodePushActionAdapter bean = new nc.ui.pubapp.billref.push.OpenNodePushActionAdapter();
  context.put("m5Xarrange5AAction",bean);
  bean.setModuleCode("4009");
  bean.setActionName("nc.ui.to.m5a.billref.m30.OpenNodePushActionM5A");
  bean.setActionCode("5XBH5A");
  bean.setDestBillType("5A");
  bean.setDestNodeCode("40092010");
  bean.setOpenNodePushBeforeVOChange(getM5XbeforeVOChange());
  bean.setSourceVOUpdate(getM5XPushSourceVOUpdate());
  bean.setSourceVOStrategy(getM5XSourceVOStrategy());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.billref.push.OpenNodePushActionAdapter getM30arrange5XAction(){
 if(context.get("m30arrange5XAction")!=null)
 return (nc.ui.pubapp.billref.push.OpenNodePushActionAdapter)context.get("m30arrange5XAction");
  nc.ui.pubapp.billref.push.OpenNodePushActionAdapter bean = new nc.ui.pubapp.billref.push.OpenNodePushActionAdapter();
  context.put("m30arrange5XAction",bean);
  bean.setModuleCode("4009");
  bean.setActionName("nc.ui.to.m5x.billref.m30.OpenNodePushActionForM30");
  bean.setActionCode("30BH5X");
  bean.setDestBillType("5X");
  bean.setDestNodeCode("40093010");
  bean.setOpenNodePushBeforeVOChange(getM30beforeVOChange());
  bean.setSourceVOUpdate(getM30PushSourceVOUpdate());
  bean.setSourceVOStrategy(getM30SourceVOStrategy());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.billref.push.OpenNodePushActionAdapter getM5Xarrange5XAction(){
 if(context.get("m5Xarrange5XAction")!=null)
 return (nc.ui.pubapp.billref.push.OpenNodePushActionAdapter)context.get("m5Xarrange5XAction");
  nc.ui.pubapp.billref.push.OpenNodePushActionAdapter bean = new nc.ui.pubapp.billref.push.OpenNodePushActionAdapter();
  context.put("m5Xarrange5XAction",bean);
  bean.setModuleCode("4009");
  bean.setActionName("nc.ui.so.m30.arrange.push.DefaultOpenNodePushAction");
  bean.setActionCode("5XBH5X");
  bean.setDestBillType("5X");
  bean.setDestNodeCode("40093010");
  bean.setOpenNodePushBeforeVOChange(getM5XbeforeVOChange());
  bean.setSourceVOUpdate(getM5XPushSourceVOUpdate());
  bean.setSourceVOStrategy(getM5XSourceVOStrategy());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.billref.push.OpenNodePushActionAdapter getM30arrange61Action(){
 if(context.get("m30arrange61Action")!=null)
 return (nc.ui.pubapp.billref.push.OpenNodePushActionAdapter)context.get("m30arrange61Action");
  nc.ui.pubapp.billref.push.OpenNodePushActionAdapter bean = new nc.ui.pubapp.billref.push.OpenNodePushActionAdapter();
  context.put("m30arrange61Action",bean);
  bean.setModuleCode("4004");
  bean.setActionName("nc.ui.so.m30.arrange.push.DefaultOpenNodePushAction");
  bean.setActionCode("30BH61");
  bean.setDestBillType("61");
  bean.setDestNodeCode("40120101");
  bean.setOpenNodePushBeforeVOChange(getM30beforeVOChange());
  bean.setSourceVOUpdate(getM30PushSourceVOUpdate());
  bean.setSourceVOStrategy(getM30SourceVOStrategy());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.billref.push.OpenNodePushActionAdapter getM5Xarrange61Action(){
 if(context.get("m5Xarrange61Action")!=null)
 return (nc.ui.pubapp.billref.push.OpenNodePushActionAdapter)context.get("m5Xarrange61Action");
  nc.ui.pubapp.billref.push.OpenNodePushActionAdapter bean = new nc.ui.pubapp.billref.push.OpenNodePushActionAdapter();
  context.put("m5Xarrange61Action",bean);
  bean.setModuleCode("4004");
  bean.setActionName("nc.ui.so.m30.arrange.push.DefaultOpenNodePushAction");
  bean.setActionCode("5XBH61");
  bean.setDestBillType("61");
  bean.setDestNodeCode("40120101");
  bean.setOpenNodePushBeforeVOChange(getM5XbeforeVOChange());
  bean.setSourceVOUpdate(getM5XPushSourceVOUpdate());
  bean.setSourceVOStrategy(getM5XSourceVOStrategy());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.so.m30.arrange.action.OpenNodePushMMActionAdapter getM30arrangeA2Action(){
 if(context.get("m30arrangeA2Action")!=null)
 return (nc.ui.so.m30.arrange.action.OpenNodePushMMActionAdapter)context.get("m30arrangeA2Action");
  nc.ui.so.m30.arrange.action.OpenNodePushMMActionAdapter bean = new nc.ui.so.m30.arrange.action.OpenNodePushMMActionAdapter();
  context.put("m30arrangeA2Action",bean);
  bean.setActionName("nc.ui.mmpac.mo.ref.push.OpenNodePushActionForMO");
  bean.setActionCode("30BH55A2");
  bean.setDestBillType("55A2");
  bean.setDestNodeCode("50080002");
  bean.setOpenNodePushBeforeVOChange(getM30beforeVOChange());
  bean.setSourceVOUpdate(getM30PushSourceVOUpdate());
  bean.setSourceVOStrategy(getM30SourceVOStrategy());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.so.m30.arrange.action.OpenNodePushMMActionAdapter getM5XarrangeA2Action(){
 if(context.get("m5XarrangeA2Action")!=null)
 return (nc.ui.so.m30.arrange.action.OpenNodePushMMActionAdapter)context.get("m5XarrangeA2Action");
  nc.ui.so.m30.arrange.action.OpenNodePushMMActionAdapter bean = new nc.ui.so.m30.arrange.action.OpenNodePushMMActionAdapter();
  context.put("m5XarrangeA2Action",bean);
  bean.setActionName("nc.ui.mmpac.mo.ref.push.OpenNodePushActionForMO");
  bean.setActionCode("5XBH55A2");
  bean.setDestBillType("55A2");
  bean.setDestNodeCode("50080002");
  bean.setOpenNodePushBeforeVOChange(getM5XbeforeVOChange());
  bean.setSourceVOUpdate(getM5XPushSourceVOUpdate());
  bean.setSourceVOStrategy(getM5XSourceVOStrategy());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.billref.push.OpenNodePushActionAdapter getM30zyarrange20Action(){
 if(context.get("m30zyarrange20Action")!=null)
 return (nc.ui.pubapp.billref.push.OpenNodePushActionAdapter)context.get("m30zyarrange20Action");
  nc.ui.pubapp.billref.push.OpenNodePushActionAdapter bean = new nc.ui.pubapp.billref.push.OpenNodePushActionAdapter();
  context.put("m30zyarrange20Action",bean);
  bean.setModuleCode("4004");
  bean.setActionName("nc.ui.pubapp.billref.push.OpenNodePushAction");
  bean.setActionCode("30ZY20");
  bean.setDestBillType("20");
  bean.setDestNodeCode("40040200");
  bean.setUserObj("Y");
  bean.setOpenNodePushBeforeVOChange(getM30zyPObeforeVOChange());
  bean.setShowOrgPanel(false);
  bean.setSourceVOUpdate(getM30PushSourceVOUpdate());
  bean.setSourceVOStrategy(getM30SourceVOStrategy());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.billref.push.OpenNodePushActionAdapter getM30zyarrange21Action(){
 if(context.get("m30zyarrange21Action")!=null)
 return (nc.ui.pubapp.billref.push.OpenNodePushActionAdapter)context.get("m30zyarrange21Action");
  nc.ui.pubapp.billref.push.OpenNodePushActionAdapter bean = new nc.ui.pubapp.billref.push.OpenNodePushActionAdapter();
  context.put("m30zyarrange21Action",bean);
  bean.setModuleCode("4004");
  bean.setActionName("nc.ui.so.m30.arrange.push.DefaultOpenNodePushAction");
  bean.setActionCode("30ZY21");
  bean.setDestBillType("21");
  bean.setDestNodeCode("40040400");
  bean.setOpenNodePushBeforeVOChange(getM30zyPObeforeVOChange());
  bean.setSourceVOUpdate(getM30PushSourceVOUpdate());
  bean.setSourceVOStrategy(getM30SourceVOStrategy());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.billref.push.OpenNodePushActionAdapter getM30zyarrange5AAction(){
 if(context.get("m30zyarrange5AAction")!=null)
 return (nc.ui.pubapp.billref.push.OpenNodePushActionAdapter)context.get("m30zyarrange5AAction");
  nc.ui.pubapp.billref.push.OpenNodePushActionAdapter bean = new nc.ui.pubapp.billref.push.OpenNodePushActionAdapter();
  context.put("m30zyarrange5AAction",bean);
  bean.setModuleCode("4009");
  bean.setActionName("nc.ui.to.m5a.billref.m30.OpenNodePushActionM5A");
  bean.setActionCode("30ZY5A");
  bean.setDestBillType("5A");
  bean.setDestNodeCode("40092010");
  bean.setOpenNodePushBeforeVOChange(getM30zyTObeforeVOChange());
  bean.setSourceVOUpdate(getM30PushSourceVOUpdate());
  bean.setSourceVOStrategy(getM30SourceVOStrategy());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.billref.push.OpenNodePushActionAdapter getM30zyarrange5XAction(){
 if(context.get("m30zyarrange5XAction")!=null)
 return (nc.ui.pubapp.billref.push.OpenNodePushActionAdapter)context.get("m30zyarrange5XAction");
  nc.ui.pubapp.billref.push.OpenNodePushActionAdapter bean = new nc.ui.pubapp.billref.push.OpenNodePushActionAdapter();
  context.put("m30zyarrange5XAction",bean);
  bean.setModuleCode("4009");
  bean.setActionName("nc.ui.so.m30.arrange.push.DefaultOpenNodePushAction");
  bean.setActionCode("30ZY5X");
  bean.setDestBillType("5X");
  bean.setDestNodeCode("40093010");
  bean.setOpenNodePushBeforeVOChange(getM30zyTObeforeVOChange());
  bean.setSourceVOUpdate(getM30PushSourceVOUpdate());
  bean.setSourceVOStrategy(getM30SourceVOStrategy());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.so.m30.arrange.push.OpenNodePushBeforeVOChange getM30beforeVOChange(){
 if(context.get("m30beforeVOChange")!=null)
 return (nc.ui.so.m30.arrange.push.OpenNodePushBeforeVOChange)context.get("m30beforeVOChange");
  nc.ui.so.m30.arrange.push.OpenNodePushBeforeVOChange bean = new nc.ui.so.m30.arrange.push.OpenNodePushBeforeVOChange();
  context.put("m30beforeVOChange",bean);
  bean.setZyarrange("N");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.so.m30.arrange.push.OpenNodePushBeforeVOChange getM30zyPObeforeVOChange(){
 if(context.get("m30zyPObeforeVOChange")!=null)
 return (nc.ui.so.m30.arrange.push.OpenNodePushBeforeVOChange)context.get("m30zyPObeforeVOChange");
  nc.ui.so.m30.arrange.push.OpenNodePushBeforeVOChange bean = new nc.ui.so.m30.arrange.push.OpenNodePushBeforeVOChange();
  context.put("m30zyPObeforeVOChange",bean);
  bean.setZyarrange("ZYPO");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.so.m30.arrange.push.OpenNodePushBeforeVOChange getM30zyTObeforeVOChange(){
 if(context.get("m30zyTObeforeVOChange")!=null)
 return (nc.ui.so.m30.arrange.push.OpenNodePushBeforeVOChange)context.get("m30zyTObeforeVOChange");
  nc.ui.so.m30.arrange.push.OpenNodePushBeforeVOChange bean = new nc.ui.so.m30.arrange.push.OpenNodePushBeforeVOChange();
  context.put("m30zyTObeforeVOChange",bean);
  bean.setZyarrange("ZYTO");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.to.m5x.pub.OpenNodePushBeforeVOChangeImpl getM5XbeforeVOChange(){
 if(context.get("m5XbeforeVOChange")!=null)
 return (nc.ui.to.m5x.pub.OpenNodePushBeforeVOChangeImpl)context.get("m5XbeforeVOChange");
  nc.ui.to.m5x.pub.OpenNodePushBeforeVOChangeImpl bean = new nc.ui.to.m5x.pub.OpenNodePushBeforeVOChangeImpl();
  context.put("m5XbeforeVOChange",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.so.m30.arrange.push.PushSourceVOUpdate getM30PushSourceVOUpdate(){
 if(context.get("m30PushSourceVOUpdate")!=null)
 return (nc.ui.so.m30.arrange.push.PushSourceVOUpdate)context.get("m30PushSourceVOUpdate");
  nc.ui.so.m30.arrange.push.PushSourceVOUpdate bean = new nc.ui.so.m30.arrange.push.PushSourceVOUpdate();
  context.put("m30PushSourceVOUpdate",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.so.m30.arrange.push.SourceVOStrategy getM30SourceVOStrategy(){
 if(context.get("m30SourceVOStrategy")!=null)
 return (nc.ui.so.m30.arrange.push.SourceVOStrategy)context.get("m30SourceVOStrategy");
  nc.ui.so.m30.arrange.push.SourceVOStrategy bean = new nc.ui.so.m30.arrange.push.SourceVOStrategy();
  context.put("m30SourceVOStrategy",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.to.m5x.arrange.m3z.M5XRefreshService getM5XPushSourceVOUpdate(){
 if(context.get("m5XPushSourceVOUpdate")!=null)
 return (nc.ui.to.m5x.arrange.m3z.M5XRefreshService)context.get("m5XPushSourceVOUpdate");
  nc.ui.to.m5x.arrange.m3z.M5XRefreshService bean = new nc.ui.to.m5x.arrange.m3z.M5XRefreshService();
  context.put("m5XPushSourceVOUpdate",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.to.m5x.arrange.m3z.TransOrderVOStrategy getM5XSourceVOStrategy(){
 if(context.get("m5XSourceVOStrategy")!=null)
 return (nc.ui.to.m5x.arrange.m3z.TransOrderVOStrategy)context.get("m5XSourceVOStrategy");
  nc.ui.to.m5x.arrange.m3z.TransOrderVOStrategy bean = new nc.ui.to.m5x.arrange.m3z.TransOrderVOStrategy();
  context.put("m5XSourceVOStrategy",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.so.pub.actions.ArrangeLinkQueryAction getOrderlinkQueryAction(){
 if(context.get("OrderlinkQueryAction")!=null)
 return (nc.ui.so.pub.actions.ArrangeLinkQueryAction)context.get("OrderlinkQueryAction");
  nc.ui.so.pub.actions.ArrangeLinkQueryAction bean = new nc.ui.so.pub.actions.ArrangeLinkQueryAction();
  context.put("OrderlinkQueryAction",bean);
  bean.setBillType("30");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.so.pub.actions.ArrangeLinkQueryAction getX5linkQueryAction(){
 if(context.get("X5linkQueryAction")!=null)
 return (nc.ui.so.pub.actions.ArrangeLinkQueryAction)context.get("X5linkQueryAction");
  nc.ui.so.pub.actions.ArrangeLinkQueryAction bean = new nc.ui.so.pub.actions.ArrangeLinkQueryAction();
  context.put("X5linkQueryAction",bean);
  bean.setBillType("5X");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.so.pub.actions.SOQueryOnhandAction getSpShowHiddenAction(){
 if(context.get("spShowHiddenAction")!=null)
 return (nc.ui.so.pub.actions.SOQueryOnhandAction)context.get("spShowHiddenAction");
  nc.ui.so.pub.actions.SOQueryOnhandAction bean = new nc.ui.so.pub.actions.SOQueryOnhandAction();
  context.put("spShowHiddenAction",bean);
  bean.setTabBillInfo(getSaleOrder());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.so.pub.actions.SOQueryOnhandAction getX5spShowHiddenAction(){
 if(context.get("X5spShowHiddenAction")!=null)
 return (nc.ui.so.pub.actions.SOQueryOnhandAction)context.get("X5spShowHiddenAction");
  nc.ui.so.pub.actions.SOQueryOnhandAction bean = new nc.ui.so.pub.actions.SOQueryOnhandAction();
  context.put("X5spShowHiddenAction",bean);
  bean.setTabBillInfo(getTransferOrder());
  bean.setFillhead(getFillOnhandDlgHeadVOFor4X());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.so.pub.actions.FillOnhandDlgHeadVOFor4X getFillOnhandDlgHeadVOFor4X(){
 if(context.get("fillOnhandDlgHeadVOFor4X")!=null)
 return (nc.ui.so.pub.actions.FillOnhandDlgHeadVOFor4X)context.get("fillOnhandDlgHeadVOFor4X");
  nc.ui.so.pub.actions.FillOnhandDlgHeadVOFor4X bean = new nc.ui.so.pub.actions.FillOnhandDlgHeadVOFor4X();
  context.put("fillOnhandDlgHeadVOFor4X",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.billref.push.BillPushLinkedListner getM30linkListener(){
 if(context.get("m30linkListener")!=null)
 return (nc.ui.pubapp.billref.push.BillPushLinkedListner)context.get("m30linkListener");
  nc.ui.pubapp.billref.push.BillPushLinkedListner bean = new nc.ui.pubapp.billref.push.BillPushLinkedListner();
  context.put("m30linkListener",bean);
  bean.setSrcBillIDField("csaleorderid");
  bean.setSrcBillNOField("vbillcode");
  bean.setSrcBillType("30");
  bean.setBaseDocFileds(getManagedList9());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList9(){  List list = new ArrayList();  list.add("ccustomerid");  list.add("cinvoicecustid");  list.add("cmaterialvid");  list.add("creceivecustid");  return list;}

public nc.ui.pubapp.billref.push.BillPushLinkedListner getM5XlinkListener(){
 if(context.get("m5XlinkListener")!=null)
 return (nc.ui.pubapp.billref.push.BillPushLinkedListner)context.get("m5XlinkListener");
  nc.ui.pubapp.billref.push.BillPushLinkedListner bean = new nc.ui.pubapp.billref.push.BillPushLinkedListner();
  context.put("m5XlinkListener",bean);
  bean.setSrcBillIDField("cbillid");
  bean.setSrcBillNOField("vbillcode");
  bean.setSrcBillType("5X");
  bean.setBaseDocFileds(getManagedList10());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList10(){  List list = new ArrayList();  list.add("cmaterialvid");  return list;}

public nc.ui.pubapp.billref.push.MarAsstBillRefPushPreparator getOrdermarAsstPreparator(){
 if(context.get("ordermarAsstPreparator")!=null)
 return (nc.ui.pubapp.billref.push.MarAsstBillRefPushPreparator)context.get("ordermarAsstPreparator");
  nc.ui.pubapp.billref.push.MarAsstBillRefPushPreparator bean = new nc.ui.pubapp.billref.push.MarAsstBillRefPushPreparator();
  context.put("ordermarAsstPreparator",bean);
  bean.setContainer(getUserdefitemContainer());
  bean.setPrefix("vfree");
  bean.setMaterialField("cmaterialid");
  bean.setProjectField("cprojectid");
  bean.setSupplierField("cvendorid");
  bean.setProductorField("cproductorid");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.billref.push.PushDefDataPraparator getSaleuserdefitemlistPreparator(){
 if(context.get("saleuserdefitemlistPreparator")!=null)
 return (nc.ui.pubapp.billref.push.PushDefDataPraparator)context.get("saleuserdefitemlistPreparator");
  nc.ui.pubapp.billref.push.PushDefDataPraparator bean = new nc.ui.pubapp.billref.push.PushDefDataPraparator();
  context.put("saleuserdefitemlistPreparator",bean);
  bean.setContainer(getUserdefitemContainer());
  bean.setParams(getManagedList11());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList11(){  List list = new ArrayList();  list.add(getUserdefQueryParam_703aaa());  list.add(getUserdefQueryParam_1baa9e());  return list;}

private nc.ui.uif2.editor.UserdefQueryParam getUserdefQueryParam_703aaa(){
 if(context.get("nc.ui.uif2.editor.UserdefQueryParam#703aaa")!=null)
 return (nc.ui.uif2.editor.UserdefQueryParam)context.get("nc.ui.uif2.editor.UserdefQueryParam#703aaa");
  nc.ui.uif2.editor.UserdefQueryParam bean = new nc.ui.uif2.editor.UserdefQueryParam();
  context.put("nc.ui.uif2.editor.UserdefQueryParam#703aaa",bean);
  bean.setMdfullname("so.so_saleorder");
  bean.setTabcode("head");
  bean.setPos(0);
  bean.setPrefix("vdef");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.editor.UserdefQueryParam getUserdefQueryParam_1baa9e(){
 if(context.get("nc.ui.uif2.editor.UserdefQueryParam#1baa9e")!=null)
 return (nc.ui.uif2.editor.UserdefQueryParam)context.get("nc.ui.uif2.editor.UserdefQueryParam#1baa9e");
  nc.ui.uif2.editor.UserdefQueryParam bean = new nc.ui.uif2.editor.UserdefQueryParam();
  context.put("nc.ui.uif2.editor.UserdefQueryParam#1baa9e",bean);
  bean.setMdfullname("so.so_saleorder_b");
  bean.setTabcode("bodytable1");
  bean.setPos(1);
  bean.setPrefix("vbdef");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.billref.push.PushDefDataPraparator getTranuserdefitemlistPreparator(){
 if(context.get("tranuserdefitemlistPreparator")!=null)
 return (nc.ui.pubapp.billref.push.PushDefDataPraparator)context.get("tranuserdefitemlistPreparator");
  nc.ui.pubapp.billref.push.PushDefDataPraparator bean = new nc.ui.pubapp.billref.push.PushDefDataPraparator();
  context.put("tranuserdefitemlistPreparator",bean);
  bean.setContainer(getUserdefitemContainer());
  bean.setParams(getManagedList12());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList12(){  List list = new ArrayList();  list.add(getUserdefQueryParam_d33ca());  list.add(getUserdefQueryParam_c3243d());  return list;}

private nc.ui.uif2.editor.UserdefQueryParam getUserdefQueryParam_d33ca(){
 if(context.get("nc.ui.uif2.editor.UserdefQueryParam#d33ca")!=null)
 return (nc.ui.uif2.editor.UserdefQueryParam)context.get("nc.ui.uif2.editor.UserdefQueryParam#d33ca");
  nc.ui.uif2.editor.UserdefQueryParam bean = new nc.ui.uif2.editor.UserdefQueryParam();
  context.put("nc.ui.uif2.editor.UserdefQueryParam#d33ca",bean);
  bean.setMdfullname("to.to_bill");
  bean.setTabcode("head");
  bean.setPos(0);
  bean.setPrefix("vdef");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.editor.UserdefQueryParam getUserdefQueryParam_c3243d(){
 if(context.get("nc.ui.uif2.editor.UserdefQueryParam#c3243d")!=null)
 return (nc.ui.uif2.editor.UserdefQueryParam)context.get("nc.ui.uif2.editor.UserdefQueryParam#c3243d");
  nc.ui.uif2.editor.UserdefQueryParam bean = new nc.ui.uif2.editor.UserdefQueryParam();
  context.put("nc.ui.uif2.editor.UserdefQueryParam#c3243d",bean);
  bean.setMdfullname("to.to_bill_b");
  bean.setTabcode("base");
  bean.setPos(1);
  bean.setPrefix("vbdef");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.billref.push.MarAsstBillRefPushPreparator getTransordermarAsstPreparator(){
 if(context.get("transordermarAsstPreparator")!=null)
 return (nc.ui.pubapp.billref.push.MarAsstBillRefPushPreparator)context.get("transordermarAsstPreparator");
  nc.ui.pubapp.billref.push.MarAsstBillRefPushPreparator bean = new nc.ui.pubapp.billref.push.MarAsstBillRefPushPreparator();
  context.put("transordermarAsstPreparator",bean);
  bean.setContainer(getUserdefitemContainer());
  bean.setPrefix("vfree");
  bean.setMaterialField("cmaterialid");
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
  bean.setParams(getManagedList13());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList13(){  List list = new ArrayList();  list.add(getQueryParam_1802963());  list.add(getQueryParam_11d61ed());  list.add(getQueryParam_81c83());  list.add(getQueryParam_87eff6());  list.add(getQueryParam_9b2ff6());  return list;}

private nc.ui.uif2.userdefitem.QueryParam getQueryParam_1802963(){
 if(context.get("nc.ui.uif2.userdefitem.QueryParam#1802963")!=null)
 return (nc.ui.uif2.userdefitem.QueryParam)context.get("nc.ui.uif2.userdefitem.QueryParam#1802963");
  nc.ui.uif2.userdefitem.QueryParam bean = new nc.ui.uif2.userdefitem.QueryParam();
  context.put("nc.ui.uif2.userdefitem.QueryParam#1802963",bean);
  bean.setMdfullname("so.preorder");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.userdefitem.QueryParam getQueryParam_11d61ed(){
 if(context.get("nc.ui.uif2.userdefitem.QueryParam#11d61ed")!=null)
 return (nc.ui.uif2.userdefitem.QueryParam)context.get("nc.ui.uif2.userdefitem.QueryParam#11d61ed");
  nc.ui.uif2.userdefitem.QueryParam bean = new nc.ui.uif2.userdefitem.QueryParam();
  context.put("nc.ui.uif2.userdefitem.QueryParam#11d61ed",bean);
  bean.setMdfullname("so.preorder_b");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.userdefitem.QueryParam getQueryParam_81c83(){
 if(context.get("nc.ui.uif2.userdefitem.QueryParam#81c83")!=null)
 return (nc.ui.uif2.userdefitem.QueryParam)context.get("nc.ui.uif2.userdefitem.QueryParam#81c83");
  nc.ui.uif2.userdefitem.QueryParam bean = new nc.ui.uif2.userdefitem.QueryParam();
  context.put("nc.ui.uif2.userdefitem.QueryParam#81c83",bean);
  bean.setMdfullname("so.so_saleorder");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.userdefitem.QueryParam getQueryParam_87eff6(){
 if(context.get("nc.ui.uif2.userdefitem.QueryParam#87eff6")!=null)
 return (nc.ui.uif2.userdefitem.QueryParam)context.get("nc.ui.uif2.userdefitem.QueryParam#87eff6");
  nc.ui.uif2.userdefitem.QueryParam bean = new nc.ui.uif2.userdefitem.QueryParam();
  context.put("nc.ui.uif2.userdefitem.QueryParam#87eff6",bean);
  bean.setMdfullname("so.so_saleorder_b");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.userdefitem.QueryParam getQueryParam_9b2ff6(){
 if(context.get("nc.ui.uif2.userdefitem.QueryParam#9b2ff6")!=null)
 return (nc.ui.uif2.userdefitem.QueryParam)context.get("nc.ui.uif2.userdefitem.QueryParam#9b2ff6");
  nc.ui.uif2.userdefitem.QueryParam bean = new nc.ui.uif2.userdefitem.QueryParam();
  context.put("nc.ui.uif2.userdefitem.QueryParam#9b2ff6",bean);
  bean.setRulecode("materialassistant");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

}
