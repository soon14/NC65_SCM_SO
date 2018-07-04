package nc.ui.so.m4331.arrange.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.ui.uif2.factory.AbstractJavaBeanDefinition;

public class m4331arrange_config extends AbstractJavaBeanDefinition{
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

public nc.ui.pubapp.billref.push.PushBillModelDataManager getM30DataManager(){
 if(context.get("m30DataManager")!=null)
 return (nc.ui.pubapp.billref.push.PushBillModelDataManager)context.get("m30DataManager");
  nc.ui.pubapp.billref.push.PushBillModelDataManager bean = new nc.ui.pubapp.billref.push.PushBillModelDataManager();
  context.put("m30DataManager",bean);
  bean.setService(getM30QueryService_94f29d());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.so.m4331.arrange.model.M30QueryService getM30QueryService_94f29d(){
 if(context.get("nc.ui.so.m4331.arrange.model.M30QueryService#94f29d")!=null)
 return (nc.ui.so.m4331.arrange.model.M30QueryService)context.get("nc.ui.so.m4331.arrange.model.M30QueryService#94f29d");
  nc.ui.so.m4331.arrange.model.M30QueryService bean = new nc.ui.so.m4331.arrange.model.M30QueryService();
  context.put("nc.ui.so.m4331.arrange.model.M30QueryService#94f29d",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.billref.push.PushBillModelDataManager getM5xDataManager(){
 if(context.get("m5xDataManager")!=null)
 return (nc.ui.pubapp.billref.push.PushBillModelDataManager)context.get("m5xDataManager");
  nc.ui.pubapp.billref.push.PushBillModelDataManager bean = new nc.ui.pubapp.billref.push.PushBillModelDataManager();
  context.put("m5xDataManager",bean);
  bean.setService(getM5XQueryService_c4591c());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.so.m4331.arrange.model.M5XQueryService getM5XQueryService_c4591c(){
 if(context.get("nc.ui.so.m4331.arrange.model.M5XQueryService#c4591c")!=null)
 return (nc.ui.so.m4331.arrange.model.M5XQueryService)context.get("nc.ui.so.m4331.arrange.model.M5XQueryService#c4591c");
  nc.ui.so.m4331.arrange.model.M5XQueryService bean = new nc.ui.so.m4331.arrange.model.M5XQueryService();
  context.put("nc.ui.so.m4331.arrange.model.M5XQueryService#c4591c",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.billref.push.BillPushLinkedListner getLinkListener(){
 if(context.get("linkListener")!=null)
 return (nc.ui.pubapp.billref.push.BillPushLinkedListner)context.get("linkListener");
  nc.ui.pubapp.billref.push.BillPushLinkedListner bean = new nc.ui.pubapp.billref.push.BillPushLinkedListner();
  context.put("linkListener",bean);
  bean.setSrcBillIDField("csaleorderid");
  bean.setSrcBillNOField("vbillcode");
  bean.setSrcBillType("30");
  bean.setBaseDocFileds(getManagedList0());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList0(){  List list = new ArrayList();  list.add("ccustomerid");  list.add("cchanneltypeid");  list.add("cemployeeid");  list.add("cdeptid");  list.add("cinvoicecustid");  list.add("cmaterialvid");  list.add("castunitid");  list.add("cunitid");  list.add("creceivecustid");  list.add("ctrafficorgid");  list.add("csendstockorgid");  list.add("csendstordocid");  return list;}

public nc.ui.pubapp.billref.push.BillPushLinkedListner getTranlinkListener(){
 if(context.get("tranlinkListener")!=null)
 return (nc.ui.pubapp.billref.push.BillPushLinkedListner)context.get("tranlinkListener");
  nc.ui.pubapp.billref.push.BillPushLinkedListner bean = new nc.ui.pubapp.billref.push.BillPushLinkedListner();
  context.put("tranlinkListener",bean);
  bean.setSrcBillIDField("cbillid");
  bean.setSrcBillNOField("vbillcode");
  bean.setSrcBillType("5X");
  bean.setBaseDocFileds(getManagedList1());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList1(){  List list = new ArrayList();  list.add("cmaterialvid");  return list;}

public nc.funcnode.ui.action.SeparatorAction getSeparatorAction(){
 if(context.get("separatorAction")!=null)
 return (nc.funcnode.ui.action.SeparatorAction)context.get("separatorAction");
  nc.funcnode.ui.action.SeparatorAction bean = new nc.funcnode.ui.action.SeparatorAction();
  context.put("separatorAction",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.billref.push.MultiTabBillInfo getSrcMultiBillInfo(){
 if(context.get("srcMultiBillInfo")!=null)
 return (nc.ui.pubapp.billref.push.MultiTabBillInfo)context.get("srcMultiBillInfo");
  nc.ui.pubapp.billref.push.MultiTabBillInfo bean = new nc.ui.pubapp.billref.push.MultiTabBillInfo();
  context.put("srcMultiBillInfo",bean);
  bean.setBillInfoMap(getManagedMap0());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private Map getManagedMap0(){  Map map = new HashMap();  map.put(getI18nFB_b65126(),getTabBillInfo_1a90944());  map.put(getI18nFB_9f7523(),getTabBillInfo_1866d20());  return map;}

private java.lang.String getI18nFB_b65126(){
 if(context.get("nc.ui.uif2.I18nFB#b65126")!=null)
 return (java.lang.String)context.get("nc.ui.uif2.I18nFB#b65126");
  nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
    context.put("&nc.ui.uif2.I18nFB#b65126",bean);  bean.setResDir("4006002_0");
  bean.setResId("04006002-0168");
  bean.setDefaultValue("销售订单");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
 try {
     Object product = bean.getObject();
    context.put("nc.ui.uif2.I18nFB#b65126",product);
     return (java.lang.String)product;
}
catch(Exception e) { throw new RuntimeException(e);}}

private nc.ui.pubapp.billref.push.TabBillInfo getTabBillInfo_1a90944(){
 if(context.get("nc.ui.pubapp.billref.push.TabBillInfo#1a90944")!=null)
 return (nc.ui.pubapp.billref.push.TabBillInfo)context.get("nc.ui.pubapp.billref.push.TabBillInfo#1a90944");
  nc.ui.pubapp.billref.push.TabBillInfo bean = new nc.ui.pubapp.billref.push.TabBillInfo();
  context.put("nc.ui.pubapp.billref.push.TabBillInfo#1a90944",bean);
  bean.setMarAsstPreparator(getSaleordermarAsstPreparator());
  bean.setDefDataPreparator(getSaleuserdefitemlistPreparator());
  bean.setBillType("30");
  bean.setBillNodeKey("30TO4331_A");
  bean.setNodeCode("40060301");
  bean.setRewriteService(getRewriteM30Num_189a89e());
  bean.setListViewValueSetter(getListPanelValueSetter_9d3ca2());
  bean.setInitDataListener(getM30InitDataListener_f7bc4d());
  bean.setModuleCode("4006");
  bean.setHeadVOClass("nc.vo.so.m30.entity.SaleOrderHVO");
  bean.setBodyVOClass("nc.vo.so.m30.entity.SaleOrderBVO");
  bean.setBillVOClass("nc.vo.so.m30.entity.SaleOrderVO");
  bean.setViewVOClass("nc.vo.so.m30.entity.SaleOrderViewVO");
  bean.setBillModelDigitSetter(getM30DigitSetter_6ba24d());
  bean.setLinkListener(getLinkListener());
  bean.setActions(getManagedList2());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.so.m4331.arrange.write.RewriteM30Num getRewriteM30Num_189a89e(){
 if(context.get("nc.ui.so.m4331.arrange.write.RewriteM30Num#189a89e")!=null)
 return (nc.ui.so.m4331.arrange.write.RewriteM30Num)context.get("nc.ui.so.m4331.arrange.write.RewriteM30Num#189a89e");
  nc.ui.so.m4331.arrange.write.RewriteM30Num bean = new nc.ui.so.m4331.arrange.write.RewriteM30Num();
  context.put("nc.ui.so.m4331.arrange.write.RewriteM30Num#189a89e",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.so.m4331.arrange.view.ListPanelValueSetter getListPanelValueSetter_9d3ca2(){
 if(context.get("nc.ui.so.m4331.arrange.view.ListPanelValueSetter#9d3ca2")!=null)
 return (nc.ui.so.m4331.arrange.view.ListPanelValueSetter)context.get("nc.ui.so.m4331.arrange.view.ListPanelValueSetter#9d3ca2");
  nc.ui.so.m4331.arrange.view.ListPanelValueSetter bean = new nc.ui.so.m4331.arrange.view.ListPanelValueSetter();
  context.put("nc.ui.so.m4331.arrange.view.ListPanelValueSetter#9d3ca2",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.so.m4331.arrange.listener.M30InitDataListener getM30InitDataListener_f7bc4d(){
 if(context.get("nc.ui.so.m4331.arrange.listener.M30InitDataListener#f7bc4d")!=null)
 return (nc.ui.so.m4331.arrange.listener.M30InitDataListener)context.get("nc.ui.so.m4331.arrange.listener.M30InitDataListener#f7bc4d");
  nc.ui.so.m4331.arrange.listener.M30InitDataListener bean = new nc.ui.so.m4331.arrange.listener.M30InitDataListener();
  context.put("nc.ui.so.m4331.arrange.listener.M30InitDataListener#f7bc4d",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.so.m4331.arrange.scale.M30DigitSetter getM30DigitSetter_6ba24d(){
 if(context.get("nc.ui.so.m4331.arrange.scale.M30DigitSetter#6ba24d")!=null)
 return (nc.ui.so.m4331.arrange.scale.M30DigitSetter)context.get("nc.ui.so.m4331.arrange.scale.M30DigitSetter#6ba24d");
  nc.ui.so.m4331.arrange.scale.M30DigitSetter bean = new nc.ui.so.m4331.arrange.scale.M30DigitSetter();
  context.put("nc.ui.so.m4331.arrange.scale.M30DigitSetter#6ba24d",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList2(){  List list = new ArrayList();  list.add(getQueryAction());  list.add(getDeliveryRefreshAction_6c7cd5());  list.add(getSeparatorAction());  list.add(getArrangeAction_4faff5());  return list;}

private nc.ui.so.m4331.arrange.action.DeliveryRefreshAction getDeliveryRefreshAction_6c7cd5(){
 if(context.get("nc.ui.so.m4331.arrange.action.DeliveryRefreshAction#6c7cd5")!=null)
 return (nc.ui.so.m4331.arrange.action.DeliveryRefreshAction)context.get("nc.ui.so.m4331.arrange.action.DeliveryRefreshAction#6c7cd5");
  nc.ui.so.m4331.arrange.action.DeliveryRefreshAction bean = new nc.ui.so.m4331.arrange.action.DeliveryRefreshAction();
  context.put("nc.ui.so.m4331.arrange.action.DeliveryRefreshAction#6c7cd5",bean);
  bean.setDataManager(getM30DataManager());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.so.m4331.arrange.action.ArrangeAction getArrangeAction_4faff5(){
 if(context.get("nc.ui.so.m4331.arrange.action.ArrangeAction#4faff5")!=null)
 return (nc.ui.so.m4331.arrange.action.ArrangeAction)context.get("nc.ui.so.m4331.arrange.action.ArrangeAction#4faff5");
  nc.ui.so.m4331.arrange.action.ArrangeAction bean = new nc.ui.so.m4331.arrange.action.ArrangeAction();
  context.put("nc.ui.so.m4331.arrange.action.ArrangeAction#4faff5",bean);
  bean.setDestBillType("4331");
  bean.setDestViewVO(getDeliveryViewVO_779abc());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.vo.so.m4331.entity.DeliveryViewVO getDeliveryViewVO_779abc(){
 if(context.get("nc.vo.so.m4331.entity.DeliveryViewVO#779abc")!=null)
 return (nc.vo.so.m4331.entity.DeliveryViewVO)context.get("nc.vo.so.m4331.entity.DeliveryViewVO#779abc");
  nc.vo.so.m4331.entity.DeliveryViewVO bean = new nc.vo.so.m4331.entity.DeliveryViewVO();
  context.put("nc.vo.so.m4331.entity.DeliveryViewVO#779abc",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private java.lang.String getI18nFB_9f7523(){
 if(context.get("nc.ui.uif2.I18nFB#9f7523")!=null)
 return (java.lang.String)context.get("nc.ui.uif2.I18nFB#9f7523");
  nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
    context.put("&nc.ui.uif2.I18nFB#9f7523",bean);  bean.setResDir("4006002_0");
  bean.setResId("04006002-0169");
  bean.setDefaultValue("调拨订单");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
 try {
     Object product = bean.getObject();
    context.put("nc.ui.uif2.I18nFB#9f7523",product);
     return (java.lang.String)product;
}
catch(Exception e) { throw new RuntimeException(e);}}

private nc.ui.pubapp.billref.push.TabBillInfo getTabBillInfo_1866d20(){
 if(context.get("nc.ui.pubapp.billref.push.TabBillInfo#1866d20")!=null)
 return (nc.ui.pubapp.billref.push.TabBillInfo)context.get("nc.ui.pubapp.billref.push.TabBillInfo#1866d20");
  nc.ui.pubapp.billref.push.TabBillInfo bean = new nc.ui.pubapp.billref.push.TabBillInfo();
  context.put("nc.ui.pubapp.billref.push.TabBillInfo#1866d20",bean);
  bean.setMarAsstPreparator(getTransordermarAsstPreparator());
  bean.setDefDataPreparator(getTranuserdefitemlistPreparator());
  bean.setBillType("5X");
  bean.setBillNodeKey("5Xtosoarr");
  bean.setNodeCode("40093010");
  bean.setRewriteService(getRewriteM5XNum_c38ab3());
  bean.setListViewValueSetter(getListPanelValueSetter_1c704ba());
  bean.setModuleCode("4009");
  bean.setHeadVOClass("nc.vo.to.m5x.entity.BillHeaderVO");
  bean.setBodyVOClass("nc.vo.to.m5x.entity.BillItemVO");
  bean.setBillVOClass("nc.vo.to.m5x.entity.BillVO");
  bean.setViewVOClass("nc.vo.to.m5x.entity.BillViewVO");
  bean.setBillModelDigitSetter(getM5XDigitSetter_1077d62());
  bean.setLinkListener(getTranlinkListener());
  bean.setActions(getManagedList3());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.so.m4331.arrange.write.RewriteM5XNum getRewriteM5XNum_c38ab3(){
 if(context.get("nc.ui.so.m4331.arrange.write.RewriteM5XNum#c38ab3")!=null)
 return (nc.ui.so.m4331.arrange.write.RewriteM5XNum)context.get("nc.ui.so.m4331.arrange.write.RewriteM5XNum#c38ab3");
  nc.ui.so.m4331.arrange.write.RewriteM5XNum bean = new nc.ui.so.m4331.arrange.write.RewriteM5XNum();
  context.put("nc.ui.so.m4331.arrange.write.RewriteM5XNum#c38ab3",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.so.m4331.arrange.view.ListPanelValueSetter getListPanelValueSetter_1c704ba(){
 if(context.get("nc.ui.so.m4331.arrange.view.ListPanelValueSetter#1c704ba")!=null)
 return (nc.ui.so.m4331.arrange.view.ListPanelValueSetter)context.get("nc.ui.so.m4331.arrange.view.ListPanelValueSetter#1c704ba");
  nc.ui.so.m4331.arrange.view.ListPanelValueSetter bean = new nc.ui.so.m4331.arrange.view.ListPanelValueSetter();
  context.put("nc.ui.so.m4331.arrange.view.ListPanelValueSetter#1c704ba",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.so.m4331.arrange.scale.M5XDigitSetter getM5XDigitSetter_1077d62(){
 if(context.get("nc.ui.so.m4331.arrange.scale.M5XDigitSetter#1077d62")!=null)
 return (nc.ui.so.m4331.arrange.scale.M5XDigitSetter)context.get("nc.ui.so.m4331.arrange.scale.M5XDigitSetter#1077d62");
  nc.ui.so.m4331.arrange.scale.M5XDigitSetter bean = new nc.ui.so.m4331.arrange.scale.M5XDigitSetter();
  context.put("nc.ui.so.m4331.arrange.scale.M5XDigitSetter#1077d62",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList3(){  List list = new ArrayList();  list.add(getM5XQueryAction_b8b9c4());  list.add(getDeliveryRefreshAction_1ac10fa());  list.add(getSeparatorAction());  list.add(getArrangeAction_dff5dc());  return list;}

private nc.ui.so.m4331.arrange.action.M5XQueryAction getM5XQueryAction_b8b9c4(){
 if(context.get("nc.ui.so.m4331.arrange.action.M5XQueryAction#b8b9c4")!=null)
 return (nc.ui.so.m4331.arrange.action.M5XQueryAction)context.get("nc.ui.so.m4331.arrange.action.M5XQueryAction#b8b9c4");
  nc.ui.so.m4331.arrange.action.M5XQueryAction bean = new nc.ui.so.m4331.arrange.action.M5XQueryAction();
  context.put("nc.ui.so.m4331.arrange.action.M5XQueryAction#b8b9c4",bean);
  bean.setNodeKey("5XTO4331");
  bean.setDataManager(getM5xDataManager());
  bean.setQryCondDLGInitializer(getTranorderQryCondDLGInitializer());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.so.m4331.arrange.action.DeliveryRefreshAction getDeliveryRefreshAction_1ac10fa(){
 if(context.get("nc.ui.so.m4331.arrange.action.DeliveryRefreshAction#1ac10fa")!=null)
 return (nc.ui.so.m4331.arrange.action.DeliveryRefreshAction)context.get("nc.ui.so.m4331.arrange.action.DeliveryRefreshAction#1ac10fa");
  nc.ui.so.m4331.arrange.action.DeliveryRefreshAction bean = new nc.ui.so.m4331.arrange.action.DeliveryRefreshAction();
  context.put("nc.ui.so.m4331.arrange.action.DeliveryRefreshAction#1ac10fa",bean);
  bean.setDataManager(getM5xDataManager());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.so.m4331.arrange.action.ArrangeAction getArrangeAction_dff5dc(){
 if(context.get("nc.ui.so.m4331.arrange.action.ArrangeAction#dff5dc")!=null)
 return (nc.ui.so.m4331.arrange.action.ArrangeAction)context.get("nc.ui.so.m4331.arrange.action.ArrangeAction#dff5dc");
  nc.ui.so.m4331.arrange.action.ArrangeAction bean = new nc.ui.so.m4331.arrange.action.ArrangeAction();
  context.put("nc.ui.so.m4331.arrange.action.ArrangeAction#dff5dc",bean);
  bean.setDestBillType("4331");
  bean.setDestViewVO(getDeliveryViewVO_18ea89d());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.vo.so.m4331.entity.DeliveryViewVO getDeliveryViewVO_18ea89d(){
 if(context.get("nc.vo.so.m4331.entity.DeliveryViewVO#18ea89d")!=null)
 return (nc.vo.so.m4331.entity.DeliveryViewVO)context.get("nc.vo.so.m4331.entity.DeliveryViewVO#18ea89d");
  nc.vo.so.m4331.entity.DeliveryViewVO bean = new nc.vo.so.m4331.entity.DeliveryViewVO();
  context.put("nc.vo.so.m4331.entity.DeliveryViewVO#18ea89d",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.billref.push.PushBillCodeProcessor getBillcode(){
 if(context.get("billcode")!=null)
 return (nc.ui.pubapp.billref.push.PushBillCodeProcessor)context.get("billcode");
  nc.ui.pubapp.billref.push.PushBillCodeProcessor bean = new nc.ui.pubapp.billref.push.PushBillCodeProcessor();
  context.put("billcode",bean);
  bean.setBillCodeKey("vbillcode");
  bean.setPk_orgKey("pk_org");
  bean.setBillType("4331");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.billref.push.MultiTabBillInfo getDestMultiBillInfo(){
 if(context.get("destMultiBillInfo")!=null)
 return (nc.ui.pubapp.billref.push.MultiTabBillInfo)context.get("destMultiBillInfo");
  nc.ui.pubapp.billref.push.MultiTabBillInfo bean = new nc.ui.pubapp.billref.push.MultiTabBillInfo();
  context.put("destMultiBillInfo",bean);
  bean.setBillInfoMap(getManagedMap1());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private Map getManagedMap1(){  Map map = new HashMap();  map.put(getI18nFB_66631f(),getTabBillInfo_1150bbf());  return map;}

private java.lang.String getI18nFB_66631f(){
 if(context.get("nc.ui.uif2.I18nFB#66631f")!=null)
 return (java.lang.String)context.get("nc.ui.uif2.I18nFB#66631f");
  nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
    context.put("&nc.ui.uif2.I18nFB#66631f",bean);  bean.setResDir("4006002_0");
  bean.setResId("04006002-0170");
  bean.setDefaultValue("发货单");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
 try {
     Object product = bean.getObject();
    context.put("nc.ui.uif2.I18nFB#66631f",product);
     return (java.lang.String)product;
}
catch(Exception e) { throw new RuntimeException(e);}}

private nc.ui.pubapp.billref.push.TabBillInfo getTabBillInfo_1150bbf(){
 if(context.get("nc.ui.pubapp.billref.push.TabBillInfo#1150bbf")!=null)
 return (nc.ui.pubapp.billref.push.TabBillInfo)context.get("nc.ui.pubapp.billref.push.TabBillInfo#1150bbf");
  nc.ui.pubapp.billref.push.TabBillInfo bean = new nc.ui.pubapp.billref.push.TabBillInfo();
  context.put("nc.ui.pubapp.billref.push.TabBillInfo#1150bbf",bean);
  bean.setDefDataPreparator(getDeliveryuserdefitemlistPreparator());
  bean.setMarAsstPreparator(getDeliverymarAsstPreparator());
  bean.setCodeProcessor(getBillcode());
  bean.setBillType("4331");
  bean.setBillNodeKey("4331REF30");
  bean.setRewriteService(getRewriteM4331Srv_1f596ca());
  bean.setModuleCode("4006");
  bean.setHeadVOClass("nc.vo.so.m4331.entity.DeliveryHVO");
  bean.setBodyVOClass("nc.vo.so.m4331.entity.DeliveryBVO");
  bean.setBillVOClass("nc.vo.so.m4331.entity.DeliveryVO");
  bean.setViewVOClass("nc.vo.so.m4331.entity.DeliveryViewVO");
  bean.setListeners(getManagedList4());
  bean.setActions(getManagedList5());
  bean.setBillModelDigitSetter(getM4331DigitSetter_157c13e());
  bean.setListViewValueSetter(getListPanelValueSetter_4fdc7e());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.so.m4331.arrange.write.RewriteM4331Srv getRewriteM4331Srv_1f596ca(){
 if(context.get("nc.ui.so.m4331.arrange.write.RewriteM4331Srv#1f596ca")!=null)
 return (nc.ui.so.m4331.arrange.write.RewriteM4331Srv)context.get("nc.ui.so.m4331.arrange.write.RewriteM4331Srv#1f596ca");
  nc.ui.so.m4331.arrange.write.RewriteM4331Srv bean = new nc.ui.so.m4331.arrange.write.RewriteM4331Srv();
  context.put("nc.ui.so.m4331.arrange.write.RewriteM4331Srv#1f596ca",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList4(){  List list = new ArrayList();  list.add(getEditEventHandler_7eaad4());  return list;}

private nc.ui.so.m4331.arrange.editor.EditEventHandler getEditEventHandler_7eaad4(){
 if(context.get("nc.ui.so.m4331.arrange.editor.EditEventHandler#7eaad4")!=null)
 return (nc.ui.so.m4331.arrange.editor.EditEventHandler)context.get("nc.ui.so.m4331.arrange.editor.EditEventHandler#7eaad4");
  nc.ui.so.m4331.arrange.editor.EditEventHandler bean = new nc.ui.so.m4331.arrange.editor.EditEventHandler();
  context.put("nc.ui.so.m4331.arrange.editor.EditEventHandler#7eaad4",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList5(){  List list = new ArrayList();  list.add(getDestDelLineAction_16459());  list.add(getDeliverySaveAction_e9eb4f());  list.add(getATPShowHiddenAction());  return list;}

private nc.ui.pubapp.billref.push.DestDelLineAction getDestDelLineAction_16459(){
 if(context.get("nc.ui.pubapp.billref.push.DestDelLineAction#16459")!=null)
 return (nc.ui.pubapp.billref.push.DestDelLineAction)context.get("nc.ui.pubapp.billref.push.DestDelLineAction#16459");
  nc.ui.pubapp.billref.push.DestDelLineAction bean = new nc.ui.pubapp.billref.push.DestDelLineAction();
  context.put("nc.ui.pubapp.billref.push.DestDelLineAction#16459",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.so.m4331.arrange.action.DeliverySaveAction getDeliverySaveAction_e9eb4f(){
 if(context.get("nc.ui.so.m4331.arrange.action.DeliverySaveAction#e9eb4f")!=null)
 return (nc.ui.so.m4331.arrange.action.DeliverySaveAction)context.get("nc.ui.so.m4331.arrange.action.DeliverySaveAction#e9eb4f");
  nc.ui.so.m4331.arrange.action.DeliverySaveAction bean = new nc.ui.so.m4331.arrange.action.DeliverySaveAction();
  context.put("nc.ui.so.m4331.arrange.action.DeliverySaveAction#e9eb4f",bean);
  bean.setSingleBillService(getService());
  bean.setPushSourceVOUpdate(getRservice());
  bean.setExceptionHandler(getSOExceptionHandler_163477b());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.so.pub.exception.SOExceptionHandler getSOExceptionHandler_163477b(){
 if(context.get("nc.ui.so.pub.exception.SOExceptionHandler#163477b")!=null)
 return (nc.ui.so.pub.exception.SOExceptionHandler)context.get("nc.ui.so.pub.exception.SOExceptionHandler#163477b");
  nc.ui.so.pub.exception.SOExceptionHandler bean = new nc.ui.so.pub.exception.SOExceptionHandler();
  context.put("nc.ui.so.pub.exception.SOExceptionHandler#163477b",bean);
  bean.setBillPush(getATPShowHiddenAction());
  bean.setService(getService());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.so.m4331.arrange.scale.M4331DigitSetter getM4331DigitSetter_157c13e(){
 if(context.get("nc.ui.so.m4331.arrange.scale.M4331DigitSetter#157c13e")!=null)
 return (nc.ui.so.m4331.arrange.scale.M4331DigitSetter)context.get("nc.ui.so.m4331.arrange.scale.M4331DigitSetter#157c13e");
  nc.ui.so.m4331.arrange.scale.M4331DigitSetter bean = new nc.ui.so.m4331.arrange.scale.M4331DigitSetter();
  context.put("nc.ui.so.m4331.arrange.scale.M4331DigitSetter#157c13e",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.so.m4331.arrange.view.ListPanelValueSetter getListPanelValueSetter_4fdc7e(){
 if(context.get("nc.ui.so.m4331.arrange.view.ListPanelValueSetter#4fdc7e")!=null)
 return (nc.ui.so.m4331.arrange.view.ListPanelValueSetter)context.get("nc.ui.so.m4331.arrange.view.ListPanelValueSetter#4fdc7e");
  nc.ui.so.m4331.arrange.view.ListPanelValueSetter bean = new nc.ui.so.m4331.arrange.view.ListPanelValueSetter();
  context.put("nc.ui.so.m4331.arrange.view.ListPanelValueSetter#4fdc7e",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.so.pub.actions.SOQueryOnhandAction getATPShowHiddenAction(){
 if(context.get("ATPShowHiddenAction")!=null)
 return (nc.ui.so.pub.actions.SOQueryOnhandAction)context.get("ATPShowHiddenAction");
  nc.ui.so.pub.actions.SOQueryOnhandAction bean = new nc.ui.so.pub.actions.SOQueryOnhandAction();
  context.put("ATPShowHiddenAction",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.so.m4331.arrange.action.RefreshSrcAction getRservice(){
 if(context.get("rservice")!=null)
 return (nc.ui.so.m4331.arrange.action.RefreshSrcAction)context.get("rservice");
  nc.ui.so.m4331.arrange.action.RefreshSrcAction bean = new nc.ui.so.m4331.arrange.action.RefreshSrcAction();
  context.put("rservice",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.so.m4331.arrange.model.M4331PushSaveSrv getService(){
 if(context.get("service")!=null)
 return (nc.ui.so.m4331.arrange.model.M4331PushSaveSrv)context.get("service");
  nc.ui.so.m4331.arrange.model.M4331PushSaveSrv bean = new nc.ui.so.m4331.arrange.model.M4331PushSaveSrv();
  context.put("service",bean);
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
  bean.setParams(getManagedList6());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList6(){  List list = new ArrayList();  list.add(getUserdefQueryParam_b66192());  list.add(getUserdefQueryParam_1bfe09d());  return list;}

private nc.ui.uif2.editor.UserdefQueryParam getUserdefQueryParam_b66192(){
 if(context.get("nc.ui.uif2.editor.UserdefQueryParam#b66192")!=null)
 return (nc.ui.uif2.editor.UserdefQueryParam)context.get("nc.ui.uif2.editor.UserdefQueryParam#b66192");
  nc.ui.uif2.editor.UserdefQueryParam bean = new nc.ui.uif2.editor.UserdefQueryParam();
  context.put("nc.ui.uif2.editor.UserdefQueryParam#b66192",bean);
  bean.setMdfullname("to.to_bill");
  bean.setTabcode("main");
  bean.setPos(1);
  bean.setPrefix("vdef");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.editor.UserdefQueryParam getUserdefQueryParam_1bfe09d(){
 if(context.get("nc.ui.uif2.editor.UserdefQueryParam#1bfe09d")!=null)
 return (nc.ui.uif2.editor.UserdefQueryParam)context.get("nc.ui.uif2.editor.UserdefQueryParam#1bfe09d");
  nc.ui.uif2.editor.UserdefQueryParam bean = new nc.ui.uif2.editor.UserdefQueryParam();
  context.put("nc.ui.uif2.editor.UserdefQueryParam#1bfe09d",bean);
  bean.setMdfullname("to.to_bill_b");
  bean.setTabcode("main");
  bean.setPos(1);
  bean.setPrefix("vbdef");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.billref.push.PushDefDataPraparator getDeliveryuserdefitemlistPreparator(){
 if(context.get("deliveryuserdefitemlistPreparator")!=null)
 return (nc.ui.pubapp.billref.push.PushDefDataPraparator)context.get("deliveryuserdefitemlistPreparator");
  nc.ui.pubapp.billref.push.PushDefDataPraparator bean = new nc.ui.pubapp.billref.push.PushDefDataPraparator();
  context.put("deliveryuserdefitemlistPreparator",bean);
  bean.setContainer(getUserdefitemContainer());
  bean.setParams(getManagedList7());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList7(){  List list = new ArrayList();  list.add(getUserdefQueryParam_3377ce());  list.add(getUserdefQueryParam_fe88f9());  return list;}

private nc.ui.uif2.editor.UserdefQueryParam getUserdefQueryParam_3377ce(){
 if(context.get("nc.ui.uif2.editor.UserdefQueryParam#3377ce")!=null)
 return (nc.ui.uif2.editor.UserdefQueryParam)context.get("nc.ui.uif2.editor.UserdefQueryParam#3377ce");
  nc.ui.uif2.editor.UserdefQueryParam bean = new nc.ui.uif2.editor.UserdefQueryParam();
  context.put("nc.ui.uif2.editor.UserdefQueryParam#3377ce",bean);
  bean.setMdfullname("so.delivery");
  bean.setTabcode("delivery");
  bean.setPos(1);
  bean.setPrefix("vdef");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.editor.UserdefQueryParam getUserdefQueryParam_fe88f9(){
 if(context.get("nc.ui.uif2.editor.UserdefQueryParam#fe88f9")!=null)
 return (nc.ui.uif2.editor.UserdefQueryParam)context.get("nc.ui.uif2.editor.UserdefQueryParam#fe88f9");
  nc.ui.uif2.editor.UserdefQueryParam bean = new nc.ui.uif2.editor.UserdefQueryParam();
  context.put("nc.ui.uif2.editor.UserdefQueryParam#fe88f9",bean);
  bean.setMdfullname("so.delivery_b");
  bean.setTabcode("delivery");
  bean.setPos(1);
  bean.setPrefix("vbdef");
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
  bean.setParams(getManagedList8());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList8(){  List list = new ArrayList();  list.add(getUserdefQueryParam_d2d38b());  list.add(getUserdefQueryParam_13e60a3());  return list;}

private nc.ui.uif2.editor.UserdefQueryParam getUserdefQueryParam_d2d38b(){
 if(context.get("nc.ui.uif2.editor.UserdefQueryParam#d2d38b")!=null)
 return (nc.ui.uif2.editor.UserdefQueryParam)context.get("nc.ui.uif2.editor.UserdefQueryParam#d2d38b");
  nc.ui.uif2.editor.UserdefQueryParam bean = new nc.ui.uif2.editor.UserdefQueryParam();
  context.put("nc.ui.uif2.editor.UserdefQueryParam#d2d38b",bean);
  bean.setMdfullname("so.so_saleorder");
  bean.setTabcode("so_saleorder");
  bean.setPos(1);
  bean.setPrefix("vdef");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.editor.UserdefQueryParam getUserdefQueryParam_13e60a3(){
 if(context.get("nc.ui.uif2.editor.UserdefQueryParam#13e60a3")!=null)
 return (nc.ui.uif2.editor.UserdefQueryParam)context.get("nc.ui.uif2.editor.UserdefQueryParam#13e60a3");
  nc.ui.uif2.editor.UserdefQueryParam bean = new nc.ui.uif2.editor.UserdefQueryParam();
  context.put("nc.ui.uif2.editor.UserdefQueryParam#13e60a3",bean);
  bean.setMdfullname("so.so_saleorder_b");
  bean.setTabcode("so_saleorder");
  bean.setPos(1);
  bean.setPrefix("vbdef");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.uif2.userdefitem.UserDefItemContainer getUserdefitemContainer(){
 if(context.get("userdefitemContainer")!=null)
 return (nc.ui.uif2.userdefitem.UserDefItemContainer)context.get("userdefitemContainer");
  nc.ui.uif2.userdefitem.UserDefItemContainer bean = new nc.ui.uif2.userdefitem.UserDefItemContainer();
  context.put("userdefitemContainer",bean);
  bean.setParams(getManagedList9());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList9(){  List list = new ArrayList();  list.add(getQueryParam_3aaa7e());  list.add(getQueryParam_18f46b9());  list.add(getQueryParam_16c52da());  list.add(getQueryParam_b36c11());  list.add(getQueryParam_17c0bf2());  list.add(getQueryParam_f45538());  list.add(getQueryParam_1070d91());  return list;}

private nc.ui.uif2.userdefitem.QueryParam getQueryParam_3aaa7e(){
 if(context.get("nc.ui.uif2.userdefitem.QueryParam#3aaa7e")!=null)
 return (nc.ui.uif2.userdefitem.QueryParam)context.get("nc.ui.uif2.userdefitem.QueryParam#3aaa7e");
  nc.ui.uif2.userdefitem.QueryParam bean = new nc.ui.uif2.userdefitem.QueryParam();
  context.put("nc.ui.uif2.userdefitem.QueryParam#3aaa7e",bean);
  bean.setMdfullname("so.delivery");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.userdefitem.QueryParam getQueryParam_18f46b9(){
 if(context.get("nc.ui.uif2.userdefitem.QueryParam#18f46b9")!=null)
 return (nc.ui.uif2.userdefitem.QueryParam)context.get("nc.ui.uif2.userdefitem.QueryParam#18f46b9");
  nc.ui.uif2.userdefitem.QueryParam bean = new nc.ui.uif2.userdefitem.QueryParam();
  context.put("nc.ui.uif2.userdefitem.QueryParam#18f46b9",bean);
  bean.setMdfullname("so.delivery_b");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.userdefitem.QueryParam getQueryParam_16c52da(){
 if(context.get("nc.ui.uif2.userdefitem.QueryParam#16c52da")!=null)
 return (nc.ui.uif2.userdefitem.QueryParam)context.get("nc.ui.uif2.userdefitem.QueryParam#16c52da");
  nc.ui.uif2.userdefitem.QueryParam bean = new nc.ui.uif2.userdefitem.QueryParam();
  context.put("nc.ui.uif2.userdefitem.QueryParam#16c52da",bean);
  bean.setMdfullname("so.so_saleorder");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.userdefitem.QueryParam getQueryParam_b36c11(){
 if(context.get("nc.ui.uif2.userdefitem.QueryParam#b36c11")!=null)
 return (nc.ui.uif2.userdefitem.QueryParam)context.get("nc.ui.uif2.userdefitem.QueryParam#b36c11");
  nc.ui.uif2.userdefitem.QueryParam bean = new nc.ui.uif2.userdefitem.QueryParam();
  context.put("nc.ui.uif2.userdefitem.QueryParam#b36c11",bean);
  bean.setMdfullname("so.so_saleorder_b");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.userdefitem.QueryParam getQueryParam_17c0bf2(){
 if(context.get("nc.ui.uif2.userdefitem.QueryParam#17c0bf2")!=null)
 return (nc.ui.uif2.userdefitem.QueryParam)context.get("nc.ui.uif2.userdefitem.QueryParam#17c0bf2");
  nc.ui.uif2.userdefitem.QueryParam bean = new nc.ui.uif2.userdefitem.QueryParam();
  context.put("nc.ui.uif2.userdefitem.QueryParam#17c0bf2",bean);
  bean.setMdfullname("to.to_bill");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.userdefitem.QueryParam getQueryParam_f45538(){
 if(context.get("nc.ui.uif2.userdefitem.QueryParam#f45538")!=null)
 return (nc.ui.uif2.userdefitem.QueryParam)context.get("nc.ui.uif2.userdefitem.QueryParam#f45538");
  nc.ui.uif2.userdefitem.QueryParam bean = new nc.ui.uif2.userdefitem.QueryParam();
  context.put("nc.ui.uif2.userdefitem.QueryParam#f45538",bean);
  bean.setMdfullname("to.to_bill_b");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.userdefitem.QueryParam getQueryParam_1070d91(){
 if(context.get("nc.ui.uif2.userdefitem.QueryParam#1070d91")!=null)
 return (nc.ui.uif2.userdefitem.QueryParam)context.get("nc.ui.uif2.userdefitem.QueryParam#1070d91");
  nc.ui.uif2.userdefitem.QueryParam bean = new nc.ui.uif2.userdefitem.QueryParam();
  context.put("nc.ui.uif2.userdefitem.QueryParam#1070d91",bean);
  bean.setRulecode("materialassistant");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.billref.push.MarAsstBillRefPushPreparator getDeliverymarAsstPreparator(){
 if(context.get("deliverymarAsstPreparator")!=null)
 return (nc.ui.pubapp.billref.push.MarAsstBillRefPushPreparator)context.get("deliverymarAsstPreparator");
  nc.ui.pubapp.billref.push.MarAsstBillRefPushPreparator bean = new nc.ui.pubapp.billref.push.MarAsstBillRefPushPreparator();
  context.put("deliverymarAsstPreparator",bean);
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

public nc.ui.pubapp.billref.push.MarAsstBillRefPushPreparator getSaleordermarAsstPreparator(){
 if(context.get("saleordermarAsstPreparator")!=null)
 return (nc.ui.pubapp.billref.push.MarAsstBillRefPushPreparator)context.get("saleordermarAsstPreparator");
  nc.ui.pubapp.billref.push.MarAsstBillRefPushPreparator bean = new nc.ui.pubapp.billref.push.MarAsstBillRefPushPreparator();
  context.put("saleordermarAsstPreparator",bean);
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

public nc.ui.so.m4331.arrange.listener.SaleOrderQueryDLGInitializer getSaleorderQryCondDLGInitializer(){
 if(context.get("saleorderQryCondDLGInitializer")!=null)
 return (nc.ui.so.m4331.arrange.listener.SaleOrderQueryDLGInitializer)context.get("saleorderQryCondDLGInitializer");
  nc.ui.so.m4331.arrange.listener.SaleOrderQueryDLGInitializer bean = new nc.ui.so.m4331.arrange.listener.SaleOrderQueryDLGInitializer();
  context.put("saleorderQryCondDLGInitializer",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.so.m4331.arrange.listener.TransOrderQueryDLGInitializer getTranorderQryCondDLGInitializer(){
 if(context.get("tranorderQryCondDLGInitializer")!=null)
 return (nc.ui.so.m4331.arrange.listener.TransOrderQueryDLGInitializer)context.get("tranorderQryCondDLGInitializer");
  nc.ui.so.m4331.arrange.listener.TransOrderQueryDLGInitializer bean = new nc.ui.so.m4331.arrange.listener.TransOrderQueryDLGInitializer();
  context.put("tranorderQryCondDLGInitializer",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.so.m4331.arrange.action.M30QueryAction getQueryAction(){
 if(context.get("queryAction")!=null)
 return (nc.ui.so.m4331.arrange.action.M30QueryAction)context.get("queryAction");
  nc.ui.so.m4331.arrange.action.M30QueryAction bean = new nc.ui.so.m4331.arrange.action.M30QueryAction();
  context.put("queryAction",bean);
  bean.setNodeKey("30TO4331_Q");
  bean.setDataManager(getM30DataManager());
  bean.setQryCondDLGInitializer(getSaleorderQryCondDLGInitializer());
  bean.setHasQueryArea(false);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

}
