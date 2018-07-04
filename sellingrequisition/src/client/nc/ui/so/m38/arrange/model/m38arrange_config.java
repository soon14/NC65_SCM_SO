package nc.ui.so.m38.arrange.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.ui.uif2.factory.AbstractJavaBeanDefinition;

public class m38arrange_config extends AbstractJavaBeanDefinition{
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

public nc.ui.pubapp.billref.push.PushBillModelDataManager getDataManager(){
 if(context.get("dataManager")!=null)
 return (nc.ui.pubapp.billref.push.PushBillModelDataManager)context.get("dataManager");
  nc.ui.pubapp.billref.push.PushBillModelDataManager bean = new nc.ui.pubapp.billref.push.PushBillModelDataManager();
  context.put("dataManager",bean);
  bean.setService(getM38ArrangeQryService_14afa47());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.so.m38.arrange.model.M38ArrangeQryService getM38ArrangeQryService_14afa47(){
 if(context.get("nc.ui.so.m38.arrange.model.M38ArrangeQryService#14afa47")!=null)
 return (nc.ui.so.m38.arrange.model.M38ArrangeQryService)context.get("nc.ui.so.m38.arrange.model.M38ArrangeQryService#14afa47");
  nc.ui.so.m38.arrange.model.M38ArrangeQryService bean = new nc.ui.so.m38.arrange.model.M38ArrangeQryService();
  context.put("nc.ui.so.m38.arrange.model.M38ArrangeQryService#14afa47",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.billref.push.BillPushLinkedListner getLinkListener(){
 if(context.get("linkListener")!=null)
 return (nc.ui.pubapp.billref.push.BillPushLinkedListner)context.get("linkListener");
  nc.ui.pubapp.billref.push.BillPushLinkedListner bean = new nc.ui.pubapp.billref.push.BillPushLinkedListner();
  context.put("linkListener",bean);
  bean.setSrcBillIDField("cpreorderid");
  bean.setSrcBillNOField("vbillcode");
  bean.setSrcBillType("38");
  bean.setBaseDocFileds(getManagedList0());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList0(){  List list = new ArrayList();  list.add("ccustomerid");  list.add("cinvoicecustid");  list.add("cmaterialvid");  return list;}

public nc.ui.so.m38.billui.query.PreOrderQryCondDLGInitializer getPreorderQryCondDLGInitializer(){
 if(context.get("preorderQryCondDLGInitializer")!=null)
 return (nc.ui.so.m38.billui.query.PreOrderQryCondDLGInitializer)context.get("preorderQryCondDLGInitializer");
  nc.ui.so.m38.billui.query.PreOrderQryCondDLGInitializer bean = new nc.ui.so.m38.billui.query.PreOrderQryCondDLGInitializer();
  context.put("preorderQryCondDLGInitializer",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.so.m38.arrange.pub.M38ArrangeInitDataListener getM38ArrangeInitDataListener(){
 if(context.get("m38ArrangeInitDataListener")!=null)
 return (nc.ui.so.m38.arrange.pub.M38ArrangeInitDataListener)context.get("m38ArrangeInitDataListener");
  nc.ui.so.m38.arrange.pub.M38ArrangeInitDataListener bean = new nc.ui.so.m38.arrange.pub.M38ArrangeInitDataListener();
  context.put("m38ArrangeInitDataListener",bean);
  bean.setContext(getContext());
  bean.setVoClassName("nc.vo.so.m38.entity.PreOrderViewVO");
  bean.setQueryAction(getQueryAction());
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

private Map getManagedMap0(){  Map map = new HashMap();  map.put(getI18nFB_15c0a53(),getTabBillInfo_1ce67d0());  return map;}

private java.lang.String getI18nFB_15c0a53(){
 if(context.get("nc.ui.uif2.I18nFB#15c0a53")!=null)
 return (java.lang.String)context.get("nc.ui.uif2.I18nFB#15c0a53");
  nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
    context.put("&nc.ui.uif2.I18nFB#15c0a53",bean);  bean.setResDir("4006012_0");
  bean.setResId("04006012-0090");
  bean.setDefaultValue("预订单");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
 try {
     Object product = bean.getObject();
    context.put("nc.ui.uif2.I18nFB#15c0a53",product);
     return (java.lang.String)product;
}
catch(Exception e) { throw new RuntimeException(e);}}

private nc.ui.pubapp.billref.push.TabBillInfo getTabBillInfo_1ce67d0(){
 if(context.get("nc.ui.pubapp.billref.push.TabBillInfo#1ce67d0")!=null)
 return (nc.ui.pubapp.billref.push.TabBillInfo)context.get("nc.ui.pubapp.billref.push.TabBillInfo#1ce67d0");
  nc.ui.pubapp.billref.push.TabBillInfo bean = new nc.ui.pubapp.billref.push.TabBillInfo();
  context.put("nc.ui.pubapp.billref.push.TabBillInfo#1ce67d0",bean);
  bean.setInitDataListener(getM38ArrangeInitDataListener());
  bean.setMarAsstPreparator(getPreordermarAsstPreparator());
  bean.setDefDataPreparator(getPreorderuserdefitemlistPreparator());
  bean.setBillType("38");
  bean.setBillNodeKey("38arrange");
  bean.setNodeCode("40060201");
  bean.setRewriteService(getM38ArrangeService_1a9e355());
  bean.setListViewValueSetter(getM38ArrangeListValueSetter_8be76e());
  bean.setBillModelDigitSetter(getM38DigitSetter_10706d6());
  bean.setHeadVO(getPreOrderHVO_51f62f());
  bean.setBodyVO(getPreOrderBVO_f9c24b());
  bean.setBillVO(getPreOrderVO_142fcb0());
  bean.setViewVO(getPreOrderViewVO_dbc608());
  bean.setLinkListener(getLinkListener());
  bean.setActions(getManagedList1());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.so.m38.arrange.model.M38ArrangeService getM38ArrangeService_1a9e355(){
 if(context.get("nc.ui.so.m38.arrange.model.M38ArrangeService#1a9e355")!=null)
 return (nc.ui.so.m38.arrange.model.M38ArrangeService)context.get("nc.ui.so.m38.arrange.model.M38ArrangeService#1a9e355");
  nc.ui.so.m38.arrange.model.M38ArrangeService bean = new nc.ui.so.m38.arrange.model.M38ArrangeService();
  context.put("nc.ui.so.m38.arrange.model.M38ArrangeService#1a9e355",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.so.m38.arrange.view.M38ArrangeListValueSetter getM38ArrangeListValueSetter_8be76e(){
 if(context.get("nc.ui.so.m38.arrange.view.M38ArrangeListValueSetter#8be76e")!=null)
 return (nc.ui.so.m38.arrange.view.M38ArrangeListValueSetter)context.get("nc.ui.so.m38.arrange.view.M38ArrangeListValueSetter#8be76e");
  nc.ui.so.m38.arrange.view.M38ArrangeListValueSetter bean = new nc.ui.so.m38.arrange.view.M38ArrangeListValueSetter();
  context.put("nc.ui.so.m38.arrange.view.M38ArrangeListValueSetter#8be76e",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.so.m38.arrange.scale.M38DigitSetter getM38DigitSetter_10706d6(){
 if(context.get("nc.ui.so.m38.arrange.scale.M38DigitSetter#10706d6")!=null)
 return (nc.ui.so.m38.arrange.scale.M38DigitSetter)context.get("nc.ui.so.m38.arrange.scale.M38DigitSetter#10706d6");
  nc.ui.so.m38.arrange.scale.M38DigitSetter bean = new nc.ui.so.m38.arrange.scale.M38DigitSetter();
  context.put("nc.ui.so.m38.arrange.scale.M38DigitSetter#10706d6",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.vo.so.m38.entity.PreOrderHVO getPreOrderHVO_51f62f(){
 if(context.get("nc.vo.so.m38.entity.PreOrderHVO#51f62f")!=null)
 return (nc.vo.so.m38.entity.PreOrderHVO)context.get("nc.vo.so.m38.entity.PreOrderHVO#51f62f");
  nc.vo.so.m38.entity.PreOrderHVO bean = new nc.vo.so.m38.entity.PreOrderHVO();
  context.put("nc.vo.so.m38.entity.PreOrderHVO#51f62f",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.vo.so.m38.entity.PreOrderBVO getPreOrderBVO_f9c24b(){
 if(context.get("nc.vo.so.m38.entity.PreOrderBVO#f9c24b")!=null)
 return (nc.vo.so.m38.entity.PreOrderBVO)context.get("nc.vo.so.m38.entity.PreOrderBVO#f9c24b");
  nc.vo.so.m38.entity.PreOrderBVO bean = new nc.vo.so.m38.entity.PreOrderBVO();
  context.put("nc.vo.so.m38.entity.PreOrderBVO#f9c24b",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.vo.so.m38.entity.PreOrderVO getPreOrderVO_142fcb0(){
 if(context.get("nc.vo.so.m38.entity.PreOrderVO#142fcb0")!=null)
 return (nc.vo.so.m38.entity.PreOrderVO)context.get("nc.vo.so.m38.entity.PreOrderVO#142fcb0");
  nc.vo.so.m38.entity.PreOrderVO bean = new nc.vo.so.m38.entity.PreOrderVO();
  context.put("nc.vo.so.m38.entity.PreOrderVO#142fcb0",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.vo.so.m38.entity.PreOrderViewVO getPreOrderViewVO_dbc608(){
 if(context.get("nc.vo.so.m38.entity.PreOrderViewVO#dbc608")!=null)
 return (nc.vo.so.m38.entity.PreOrderViewVO)context.get("nc.vo.so.m38.entity.PreOrderViewVO#dbc608");
  nc.vo.so.m38.entity.PreOrderViewVO bean = new nc.vo.so.m38.entity.PreOrderViewVO();
  context.put("nc.vo.so.m38.entity.PreOrderViewVO#dbc608",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList1(){  List list = new ArrayList();  list.add(getQueryAction());  list.add(getM38ArrangePushAction_818eec());  return list;}

private nc.ui.so.m38.arrange.action.M38ArrangePushAction getM38ArrangePushAction_818eec(){
 if(context.get("nc.ui.so.m38.arrange.action.M38ArrangePushAction#818eec")!=null)
 return (nc.ui.so.m38.arrange.action.M38ArrangePushAction)context.get("nc.ui.so.m38.arrange.action.M38ArrangePushAction#818eec");
  nc.ui.so.m38.arrange.action.M38ArrangePushAction bean = new nc.ui.so.m38.arrange.action.M38ArrangePushAction();
  context.put("nc.ui.so.m38.arrange.action.M38ArrangePushAction#818eec",bean);
  bean.setDestBillType("30");
  bean.setDestViewVO(getSaleOrderViewVO_a66e07());
  bean.setPushBeforeVOChange(getPushbefchange());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.vo.so.m30.entity.SaleOrderViewVO getSaleOrderViewVO_a66e07(){
 if(context.get("nc.vo.so.m30.entity.SaleOrderViewVO#a66e07")!=null)
 return (nc.vo.so.m30.entity.SaleOrderViewVO)context.get("nc.vo.so.m30.entity.SaleOrderViewVO#a66e07");
  nc.vo.so.m30.entity.SaleOrderViewVO bean = new nc.vo.so.m30.entity.SaleOrderViewVO();
  context.put("nc.vo.so.m30.entity.SaleOrderViewVO#a66e07",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.so.m38.arrange.action.M38ArrangePushBeforeVOChange getPushbefchange(){
 if(context.get("pushbefchange")!=null)
 return (nc.ui.so.m38.arrange.action.M38ArrangePushBeforeVOChange)context.get("pushbefchange");
  nc.ui.so.m38.arrange.action.M38ArrangePushBeforeVOChange bean = new nc.ui.so.m38.arrange.action.M38ArrangePushBeforeVOChange();
  context.put("pushbefchange",bean);
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

private Map getManagedMap1(){  Map map = new HashMap();  map.put(getI18nFB_7677de(),getTabBillInfo_1e64e78());  return map;}

private java.lang.String getI18nFB_7677de(){
 if(context.get("nc.ui.uif2.I18nFB#7677de")!=null)
 return (java.lang.String)context.get("nc.ui.uif2.I18nFB#7677de");
  nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
    context.put("&nc.ui.uif2.I18nFB#7677de",bean);  bean.setResDir("4006012_0");
  bean.setResId("04006012-0091");
  bean.setDefaultValue("销售订单");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
 try {
     Object product = bean.getObject();
    context.put("nc.ui.uif2.I18nFB#7677de",product);
     return (java.lang.String)product;
}
catch(Exception e) { throw new RuntimeException(e);}}

private nc.ui.pubapp.billref.push.TabBillInfo getTabBillInfo_1e64e78(){
 if(context.get("nc.ui.pubapp.billref.push.TabBillInfo#1e64e78")!=null)
 return (nc.ui.pubapp.billref.push.TabBillInfo)context.get("nc.ui.pubapp.billref.push.TabBillInfo#1e64e78");
  nc.ui.pubapp.billref.push.TabBillInfo bean = new nc.ui.pubapp.billref.push.TabBillInfo();
  context.put("nc.ui.pubapp.billref.push.TabBillInfo#1e64e78",bean);
  bean.setMarAsstPreparator(getOrdermarAsstPreparator());
  bean.setDefDataPreparator(getSaleuserdefitemlistPreparator());
  bean.setBillType("30");
  bean.setBillNodeKey("30REF38");
  bean.setRewriteService(getM38ArrangeService_1b7a285());
  bean.setListViewValueSetter(getM38ArrangeListValueSetter_10ef9d4());
  bean.setBillModelDigitSetter(getM30DigitSetter_33efb1());
  bean.setHeadVO(getSaleOrderHVO_1bd53b9());
  bean.setBodyVO(getSaleOrderBVO_c3aa0a());
  bean.setBillVO(getSaleOrderVO_1d08f88());
  bean.setViewVO(getSaleOrderViewVO_17f6a40());
  bean.setListeners(getManagedList2());
  bean.setActions(getManagedList3());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.so.m38.arrange.model.M38ArrangeService getM38ArrangeService_1b7a285(){
 if(context.get("nc.ui.so.m38.arrange.model.M38ArrangeService#1b7a285")!=null)
 return (nc.ui.so.m38.arrange.model.M38ArrangeService)context.get("nc.ui.so.m38.arrange.model.M38ArrangeService#1b7a285");
  nc.ui.so.m38.arrange.model.M38ArrangeService bean = new nc.ui.so.m38.arrange.model.M38ArrangeService();
  context.put("nc.ui.so.m38.arrange.model.M38ArrangeService#1b7a285",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.so.m38.arrange.view.M38ArrangeListValueSetter getM38ArrangeListValueSetter_10ef9d4(){
 if(context.get("nc.ui.so.m38.arrange.view.M38ArrangeListValueSetter#10ef9d4")!=null)
 return (nc.ui.so.m38.arrange.view.M38ArrangeListValueSetter)context.get("nc.ui.so.m38.arrange.view.M38ArrangeListValueSetter#10ef9d4");
  nc.ui.so.m38.arrange.view.M38ArrangeListValueSetter bean = new nc.ui.so.m38.arrange.view.M38ArrangeListValueSetter();
  context.put("nc.ui.so.m38.arrange.view.M38ArrangeListValueSetter#10ef9d4",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.so.m38.arrange.scale.M30DigitSetter getM30DigitSetter_33efb1(){
 if(context.get("nc.ui.so.m38.arrange.scale.M30DigitSetter#33efb1")!=null)
 return (nc.ui.so.m38.arrange.scale.M30DigitSetter)context.get("nc.ui.so.m38.arrange.scale.M30DigitSetter#33efb1");
  nc.ui.so.m38.arrange.scale.M30DigitSetter bean = new nc.ui.so.m38.arrange.scale.M30DigitSetter();
  context.put("nc.ui.so.m38.arrange.scale.M30DigitSetter#33efb1",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.vo.so.m30.entity.SaleOrderHVO getSaleOrderHVO_1bd53b9(){
 if(context.get("nc.vo.so.m30.entity.SaleOrderHVO#1bd53b9")!=null)
 return (nc.vo.so.m30.entity.SaleOrderHVO)context.get("nc.vo.so.m30.entity.SaleOrderHVO#1bd53b9");
  nc.vo.so.m30.entity.SaleOrderHVO bean = new nc.vo.so.m30.entity.SaleOrderHVO();
  context.put("nc.vo.so.m30.entity.SaleOrderHVO#1bd53b9",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.vo.so.m30.entity.SaleOrderBVO getSaleOrderBVO_c3aa0a(){
 if(context.get("nc.vo.so.m30.entity.SaleOrderBVO#c3aa0a")!=null)
 return (nc.vo.so.m30.entity.SaleOrderBVO)context.get("nc.vo.so.m30.entity.SaleOrderBVO#c3aa0a");
  nc.vo.so.m30.entity.SaleOrderBVO bean = new nc.vo.so.m30.entity.SaleOrderBVO();
  context.put("nc.vo.so.m30.entity.SaleOrderBVO#c3aa0a",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.vo.so.m30.entity.SaleOrderVO getSaleOrderVO_1d08f88(){
 if(context.get("nc.vo.so.m30.entity.SaleOrderVO#1d08f88")!=null)
 return (nc.vo.so.m30.entity.SaleOrderVO)context.get("nc.vo.so.m30.entity.SaleOrderVO#1d08f88");
  nc.vo.so.m30.entity.SaleOrderVO bean = new nc.vo.so.m30.entity.SaleOrderVO();
  context.put("nc.vo.so.m30.entity.SaleOrderVO#1d08f88",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.vo.so.m30.entity.SaleOrderViewVO getSaleOrderViewVO_17f6a40(){
 if(context.get("nc.vo.so.m30.entity.SaleOrderViewVO#17f6a40")!=null)
 return (nc.vo.so.m30.entity.SaleOrderViewVO)context.get("nc.vo.so.m30.entity.SaleOrderViewVO#17f6a40");
  nc.vo.so.m30.entity.SaleOrderViewVO bean = new nc.vo.so.m30.entity.SaleOrderViewVO();
  context.put("nc.vo.so.m30.entity.SaleOrderViewVO#17f6a40",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList2(){  List list = new ArrayList();  list.add(getM38ArrangeHandleEventMediator_189398());  return list;}

private nc.ui.so.m38.arrange.editor.M38ArrangeHandleEventMediator getM38ArrangeHandleEventMediator_189398(){
 if(context.get("nc.ui.so.m38.arrange.editor.M38ArrangeHandleEventMediator#189398")!=null)
 return (nc.ui.so.m38.arrange.editor.M38ArrangeHandleEventMediator)context.get("nc.ui.so.m38.arrange.editor.M38ArrangeHandleEventMediator#189398");
  nc.ui.so.m38.arrange.editor.M38ArrangeHandleEventMediator bean = new nc.ui.so.m38.arrange.editor.M38ArrangeHandleEventMediator();
  context.put("nc.ui.so.m38.arrange.editor.M38ArrangeHandleEventMediator#189398",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList3(){  List list = new ArrayList();  list.add(getDestdellineaction());  list.add(getM38ArrangeSaveAction_1ce6239());  list.add(getM38ArrangeAskqtAction_92c587());  list.add(getArrangeSPShowHidAction());  return list;}

private nc.ui.so.m38.arrange.action.M38ArrangeSaveAction getM38ArrangeSaveAction_1ce6239(){
 if(context.get("nc.ui.so.m38.arrange.action.M38ArrangeSaveAction#1ce6239")!=null)
 return (nc.ui.so.m38.arrange.action.M38ArrangeSaveAction)context.get("nc.ui.so.m38.arrange.action.M38ArrangeSaveAction#1ce6239");
  nc.ui.so.m38.arrange.action.M38ArrangeSaveAction bean = new nc.ui.so.m38.arrange.action.M38ArrangeSaveAction();
  context.put("nc.ui.so.m38.arrange.action.M38ArrangeSaveAction#1ce6239",bean);
  bean.setSingleBillService(getArrangeservice());
  bean.setPushSourceVOUpdate(getRefreshservice());
  bean.setExceptionHandler(getSOExceptionHandler_1820001());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.so.pub.exception.SOExceptionHandler getSOExceptionHandler_1820001(){
 if(context.get("nc.ui.so.pub.exception.SOExceptionHandler#1820001")!=null)
 return (nc.ui.so.pub.exception.SOExceptionHandler)context.get("nc.ui.so.pub.exception.SOExceptionHandler#1820001");
  nc.ui.so.pub.exception.SOExceptionHandler bean = new nc.ui.so.pub.exception.SOExceptionHandler();
  context.put("nc.ui.so.pub.exception.SOExceptionHandler#1820001",bean);
  bean.setBillPush(getDestdellineaction());
  bean.setService(getArrangeservice());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.so.m38.arrange.action.M38ArrangeAskqtAction getM38ArrangeAskqtAction_92c587(){
 if(context.get("nc.ui.so.m38.arrange.action.M38ArrangeAskqtAction#92c587")!=null)
 return (nc.ui.so.m38.arrange.action.M38ArrangeAskqtAction)context.get("nc.ui.so.m38.arrange.action.M38ArrangeAskqtAction#92c587");
  nc.ui.so.m38.arrange.action.M38ArrangeAskqtAction bean = new nc.ui.so.m38.arrange.action.M38ArrangeAskqtAction();
  context.put("nc.ui.so.m38.arrange.action.M38ArrangeAskqtAction#92c587",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.so.m38.arrange.action.M38ArrangeSPShowHidAction getArrangeSPShowHidAction(){
 if(context.get("arrangeSPShowHidAction")!=null)
 return (nc.ui.so.m38.arrange.action.M38ArrangeSPShowHidAction)context.get("arrangeSPShowHidAction");
  nc.ui.so.m38.arrange.action.M38ArrangeSPShowHidAction bean = new nc.ui.so.m38.arrange.action.M38ArrangeSPShowHidAction();
  context.put("arrangeSPShowHidAction",bean);
  bean.setLogincontext(getContext());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.so.m38.arrange.model.M38ArrangeService getArrangeservice(){
 if(context.get("arrangeservice")!=null)
 return (nc.ui.so.m38.arrange.model.M38ArrangeService)context.get("arrangeservice");
  nc.ui.so.m38.arrange.model.M38ArrangeService bean = new nc.ui.so.m38.arrange.model.M38ArrangeService();
  context.put("arrangeservice",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.so.m38.arrange.model.M38RefreshService getRefreshservice(){
 if(context.get("refreshservice")!=null)
 return (nc.ui.so.m38.arrange.model.M38RefreshService)context.get("refreshservice");
  nc.ui.so.m38.arrange.model.M38RefreshService bean = new nc.ui.so.m38.arrange.model.M38RefreshService();
  context.put("refreshservice",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.billref.push.DestDelLineAction getDestdellineaction(){
 if(context.get("destdellineaction")!=null)
 return (nc.ui.pubapp.billref.push.DestDelLineAction)context.get("destdellineaction");
  nc.ui.pubapp.billref.push.DestDelLineAction bean = new nc.ui.pubapp.billref.push.DestDelLineAction();
  context.put("destdellineaction",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.uif2.userdefitem.UserDefItemContainer getUserdefitemContainer(){
 if(context.get("userdefitemContainer")!=null)
 return (nc.ui.uif2.userdefitem.UserDefItemContainer)context.get("userdefitemContainer");
  nc.ui.uif2.userdefitem.UserDefItemContainer bean = new nc.ui.uif2.userdefitem.UserDefItemContainer();
  context.put("userdefitemContainer",bean);
  bean.setParams(getManagedList4());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList4(){  List list = new ArrayList();  list.add(getQueryParam_129c807());  list.add(getQueryParam_b7253());  list.add(getQueryParam_17eb16c());  list.add(getQueryParam_b268cb());  list.add(getQueryParam_fe0588());  return list;}

private nc.ui.uif2.userdefitem.QueryParam getQueryParam_129c807(){
 if(context.get("nc.ui.uif2.userdefitem.QueryParam#129c807")!=null)
 return (nc.ui.uif2.userdefitem.QueryParam)context.get("nc.ui.uif2.userdefitem.QueryParam#129c807");
  nc.ui.uif2.userdefitem.QueryParam bean = new nc.ui.uif2.userdefitem.QueryParam();
  context.put("nc.ui.uif2.userdefitem.QueryParam#129c807",bean);
  bean.setMdfullname("so.preorder");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.userdefitem.QueryParam getQueryParam_b7253(){
 if(context.get("nc.ui.uif2.userdefitem.QueryParam#b7253")!=null)
 return (nc.ui.uif2.userdefitem.QueryParam)context.get("nc.ui.uif2.userdefitem.QueryParam#b7253");
  nc.ui.uif2.userdefitem.QueryParam bean = new nc.ui.uif2.userdefitem.QueryParam();
  context.put("nc.ui.uif2.userdefitem.QueryParam#b7253",bean);
  bean.setMdfullname("so.preorder_b");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.userdefitem.QueryParam getQueryParam_17eb16c(){
 if(context.get("nc.ui.uif2.userdefitem.QueryParam#17eb16c")!=null)
 return (nc.ui.uif2.userdefitem.QueryParam)context.get("nc.ui.uif2.userdefitem.QueryParam#17eb16c");
  nc.ui.uif2.userdefitem.QueryParam bean = new nc.ui.uif2.userdefitem.QueryParam();
  context.put("nc.ui.uif2.userdefitem.QueryParam#17eb16c",bean);
  bean.setMdfullname("so.so_saleorder");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.userdefitem.QueryParam getQueryParam_b268cb(){
 if(context.get("nc.ui.uif2.userdefitem.QueryParam#b268cb")!=null)
 return (nc.ui.uif2.userdefitem.QueryParam)context.get("nc.ui.uif2.userdefitem.QueryParam#b268cb");
  nc.ui.uif2.userdefitem.QueryParam bean = new nc.ui.uif2.userdefitem.QueryParam();
  context.put("nc.ui.uif2.userdefitem.QueryParam#b268cb",bean);
  bean.setMdfullname("so.so_saleorder_b");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.userdefitem.QueryParam getQueryParam_fe0588(){
 if(context.get("nc.ui.uif2.userdefitem.QueryParam#fe0588")!=null)
 return (nc.ui.uif2.userdefitem.QueryParam)context.get("nc.ui.uif2.userdefitem.QueryParam#fe0588");
  nc.ui.uif2.userdefitem.QueryParam bean = new nc.ui.uif2.userdefitem.QueryParam();
  context.put("nc.ui.uif2.userdefitem.QueryParam#fe0588",bean);
  bean.setRulecode("materialassistant");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.billref.push.MarAsstBillRefPushPreparator getPreordermarAsstPreparator(){
 if(context.get("preordermarAsstPreparator")!=null)
 return (nc.ui.pubapp.billref.push.MarAsstBillRefPushPreparator)context.get("preordermarAsstPreparator");
  nc.ui.pubapp.billref.push.MarAsstBillRefPushPreparator bean = new nc.ui.pubapp.billref.push.MarAsstBillRefPushPreparator();
  context.put("preordermarAsstPreparator",bean);
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

public nc.ui.pubapp.billref.push.PushDefDataPraparator getPreorderuserdefitemlistPreparator(){
 if(context.get("preorderuserdefitemlistPreparator")!=null)
 return (nc.ui.pubapp.billref.push.PushDefDataPraparator)context.get("preorderuserdefitemlistPreparator");
  nc.ui.pubapp.billref.push.PushDefDataPraparator bean = new nc.ui.pubapp.billref.push.PushDefDataPraparator();
  context.put("preorderuserdefitemlistPreparator",bean);
  bean.setContainer(getUserdefitemContainer());
  bean.setParams(getManagedList5());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList5(){  List list = new ArrayList();  list.add(getUserdefQueryParam_11d2f82());  list.add(getUserdefQueryParam_11a4100());  return list;}

private nc.ui.uif2.editor.UserdefQueryParam getUserdefQueryParam_11d2f82(){
 if(context.get("nc.ui.uif2.editor.UserdefQueryParam#11d2f82")!=null)
 return (nc.ui.uif2.editor.UserdefQueryParam)context.get("nc.ui.uif2.editor.UserdefQueryParam#11d2f82");
  nc.ui.uif2.editor.UserdefQueryParam bean = new nc.ui.uif2.editor.UserdefQueryParam();
  context.put("nc.ui.uif2.editor.UserdefQueryParam#11d2f82",bean);
  bean.setMdfullname("so.preorder");
  bean.setTabcode("body");
  bean.setPos(1);
  bean.setPrefix("vdef");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.editor.UserdefQueryParam getUserdefQueryParam_11a4100(){
 if(context.get("nc.ui.uif2.editor.UserdefQueryParam#11a4100")!=null)
 return (nc.ui.uif2.editor.UserdefQueryParam)context.get("nc.ui.uif2.editor.UserdefQueryParam#11a4100");
  nc.ui.uif2.editor.UserdefQueryParam bean = new nc.ui.uif2.editor.UserdefQueryParam();
  context.put("nc.ui.uif2.editor.UserdefQueryParam#11a4100",bean);
  bean.setMdfullname("so.preorder_b");
  bean.setTabcode("body");
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
  bean.setParams(getManagedList6());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList6(){  List list = new ArrayList();  list.add(getUserdefQueryParam_a192a6());  list.add(getUserdefQueryParam_1a2e834());  return list;}

private nc.ui.uif2.editor.UserdefQueryParam getUserdefQueryParam_a192a6(){
 if(context.get("nc.ui.uif2.editor.UserdefQueryParam#a192a6")!=null)
 return (nc.ui.uif2.editor.UserdefQueryParam)context.get("nc.ui.uif2.editor.UserdefQueryParam#a192a6");
  nc.ui.uif2.editor.UserdefQueryParam bean = new nc.ui.uif2.editor.UserdefQueryParam();
  context.put("nc.ui.uif2.editor.UserdefQueryParam#a192a6",bean);
  bean.setMdfullname("so.so_saleorder");
  bean.setTabcode("body");
  bean.setPos(1);
  bean.setPrefix("vdef");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.editor.UserdefQueryParam getUserdefQueryParam_1a2e834(){
 if(context.get("nc.ui.uif2.editor.UserdefQueryParam#1a2e834")!=null)
 return (nc.ui.uif2.editor.UserdefQueryParam)context.get("nc.ui.uif2.editor.UserdefQueryParam#1a2e834");
  nc.ui.uif2.editor.UserdefQueryParam bean = new nc.ui.uif2.editor.UserdefQueryParam();
  context.put("nc.ui.uif2.editor.UserdefQueryParam#1a2e834",bean);
  bean.setMdfullname("so.so_saleorder_b");
  bean.setTabcode("body");
  bean.setPos(1);
  bean.setPrefix("vbdef");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.so.m38.arrange.action.M38ArrangeQueryAction getQueryAction(){
 if(context.get("queryAction")!=null)
 return (nc.ui.so.m38.arrange.action.M38ArrangeQueryAction)context.get("queryAction");
  nc.ui.so.m38.arrange.action.M38ArrangeQueryAction bean = new nc.ui.so.m38.arrange.action.M38ArrangeQueryAction();
  context.put("queryAction",bean);
  bean.setDataManager(getDataManager());
  bean.setQryCondDLGInitializer(getPreorderQryCondDLGInitializer());
  bean.setHasQueryArea(false);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

}
