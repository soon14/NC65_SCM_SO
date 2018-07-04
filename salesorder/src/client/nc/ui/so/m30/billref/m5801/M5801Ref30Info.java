package nc.ui.so.m30.billref.m5801;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.ui.uif2.factory.AbstractJavaBeanDefinition;

public class M5801Ref30Info extends AbstractJavaBeanDefinition{
	private Map<String, Object> context = new HashMap();
public nc.ui.so.m30.billref.M30RefQueryAction getQueryAction(){
 if(context.get("queryAction")!=null)
 return (nc.ui.so.m30.billref.M30RefQueryAction)context.get("queryAction");
  nc.ui.so.m30.billref.M30RefQueryAction bean = new nc.ui.so.m30.billref.M30RefQueryAction();
  context.put("queryAction",bean);
  bean.setRefContext((nc.ui.pubapp.billref.src.RefContext)findBeanInUIF2BeanFactory("refContext"));
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.so.m30.billref.M30RefRefreshAction getRefreshAction(){
 if(context.get("refreshAction")!=null)
 return (nc.ui.so.m30.billref.M30RefRefreshAction)context.get("refreshAction");
  nc.ui.so.m30.billref.M30RefRefreshAction bean = new nc.ui.so.m30.billref.M30RefRefreshAction();
  context.put("refreshAction",bean);
  bean.setRefContext((nc.ui.pubapp.billref.src.RefContext)findBeanInUIF2BeanFactory("refContext"));
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.billref.src.RefInfo getRefInfo(){
 if(context.get("refInfo")!=null)
 return (nc.ui.pubapp.billref.src.RefInfo)context.get("refInfo");
  nc.ui.pubapp.billref.src.RefInfo bean = new nc.ui.pubapp.billref.src.RefInfo();
  context.put("refInfo",bean);
  bean.setUserdefitemListPreparator(getUserdefitemListPreparator());
  bean.setSingleUserdefitemListPreparator(getSingleUserdefitemListPreparator());
  bean.setHeadVO(getSaleOrderHVO_b67051());
  bean.setBodyVO(getSaleOrderBVO_187e9b2());
  bean.setBillVO(getSaleOrderVO_1ffaf22());
  bean.setViewVO(getSaleOrderViewVO_1430201());
  bean.setBillNodeKey("30to5801");
  bean.setBillViewNodeKey("30to5801_L");
  bean.setSourcevoPkName("csrcid");
  bean.setSourcevoBillNOName("vsrccode");
  bean.setQueryService(getQueryServiceFor5801_10fe524());
  bean.setRefPanelInit(getM30RefUIInit_8cb492());
  bean.setDefaultFilterItem(getManagedList0());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.vo.so.m30.entity.SaleOrderHVO getSaleOrderHVO_b67051(){
 if(context.get("nc.vo.so.m30.entity.SaleOrderHVO#b67051")!=null)
 return (nc.vo.so.m30.entity.SaleOrderHVO)context.get("nc.vo.so.m30.entity.SaleOrderHVO#b67051");
  nc.vo.so.m30.entity.SaleOrderHVO bean = new nc.vo.so.m30.entity.SaleOrderHVO();
  context.put("nc.vo.so.m30.entity.SaleOrderHVO#b67051",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.vo.so.m30.entity.SaleOrderBVO getSaleOrderBVO_187e9b2(){
 if(context.get("nc.vo.so.m30.entity.SaleOrderBVO#187e9b2")!=null)
 return (nc.vo.so.m30.entity.SaleOrderBVO)context.get("nc.vo.so.m30.entity.SaleOrderBVO#187e9b2");
  nc.vo.so.m30.entity.SaleOrderBVO bean = new nc.vo.so.m30.entity.SaleOrderBVO();
  context.put("nc.vo.so.m30.entity.SaleOrderBVO#187e9b2",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.vo.so.m30.entity.SaleOrderVO getSaleOrderVO_1ffaf22(){
 if(context.get("nc.vo.so.m30.entity.SaleOrderVO#1ffaf22")!=null)
 return (nc.vo.so.m30.entity.SaleOrderVO)context.get("nc.vo.so.m30.entity.SaleOrderVO#1ffaf22");
  nc.vo.so.m30.entity.SaleOrderVO bean = new nc.vo.so.m30.entity.SaleOrderVO();
  context.put("nc.vo.so.m30.entity.SaleOrderVO#1ffaf22",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.vo.so.m30.entity.SaleOrderViewVO getSaleOrderViewVO_1430201(){
 if(context.get("nc.vo.so.m30.entity.SaleOrderViewVO#1430201")!=null)
 return (nc.vo.so.m30.entity.SaleOrderViewVO)context.get("nc.vo.so.m30.entity.SaleOrderViewVO#1430201");
  nc.vo.so.m30.entity.SaleOrderViewVO bean = new nc.vo.so.m30.entity.SaleOrderViewVO();
  context.put("nc.vo.so.m30.entity.SaleOrderViewVO#1430201",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.so.m30.billui.model.QueryServiceFor5801 getQueryServiceFor5801_10fe524(){
 if(context.get("nc.ui.so.m30.billui.model.QueryServiceFor5801#10fe524")!=null)
 return (nc.ui.so.m30.billui.model.QueryServiceFor5801)context.get("nc.ui.so.m30.billui.model.QueryServiceFor5801#10fe524");
  nc.ui.so.m30.billui.model.QueryServiceFor5801 bean = new nc.ui.so.m30.billui.model.QueryServiceFor5801();
  context.put("nc.ui.so.m30.billui.model.QueryServiceFor5801#10fe524",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.so.m30.billref.M30RefUIInit getM30RefUIInit_8cb492(){
 if(context.get("nc.ui.so.m30.billref.M30RefUIInit#8cb492")!=null)
 return (nc.ui.so.m30.billref.M30RefUIInit)context.get("nc.ui.so.m30.billref.M30RefUIInit#8cb492");
  nc.ui.so.m30.billref.M30RefUIInit bean = new nc.ui.so.m30.billref.M30RefUIInit();
  context.put("nc.ui.so.m30.billref.M30RefUIInit#8cb492",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList0(){  List list = new ArrayList();  list.add("vbillcode");  list.add("dbilldate");  return list;}

public nc.ui.pubapp.uif2app.view.CompositeBillListDataPrepare getUserdefitemListPreparator(){
 if(context.get("userdefitemListPreparator")!=null)
 return (nc.ui.pubapp.uif2app.view.CompositeBillListDataPrepare)context.get("userdefitemListPreparator");
  nc.ui.pubapp.uif2app.view.CompositeBillListDataPrepare bean = new nc.ui.pubapp.uif2app.view.CompositeBillListDataPrepare();
  context.put("userdefitemListPreparator",bean);
  bean.setBillListDataPrepares(getManagedList1());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList1(){  List list = new ArrayList();  list.add(getListPreparator());  list.add(getMarAsstPreparator());  return list;}

public nc.ui.pubapp.uif2app.view.CompositeBillListDataPrepare getSingleUserdefitemListPreparator(){
 if(context.get("singleUserdefitemListPreparator")!=null)
 return (nc.ui.pubapp.uif2app.view.CompositeBillListDataPrepare)context.get("singleUserdefitemListPreparator");
  nc.ui.pubapp.uif2app.view.CompositeBillListDataPrepare bean = new nc.ui.pubapp.uif2app.view.CompositeBillListDataPrepare();
  context.put("singleUserdefitemListPreparator",bean);
  bean.setBillListDataPrepares(getManagedList2());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList2(){  List list = new ArrayList();  list.add(getSingleListPreparator());  list.add(getMarAsstPreparator());  return list;}

public nc.ui.uif2.editor.UserdefitemContainerListPreparator getListPreparator(){
 if(context.get("listPreparator")!=null)
 return (nc.ui.uif2.editor.UserdefitemContainerListPreparator)context.get("listPreparator");
  nc.ui.uif2.editor.UserdefitemContainerListPreparator bean = new nc.ui.uif2.editor.UserdefitemContainerListPreparator();
  context.put("listPreparator",bean);
  bean.setContainer(getUserdefitemContainer());
  bean.setParams(getManagedList3());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList3(){  List list = new ArrayList();  list.add(getUserdefQueryParam_1b3223f());  list.add(getUserdefQueryParam_18d644e());  return list;}

private nc.ui.uif2.editor.UserdefQueryParam getUserdefQueryParam_1b3223f(){
 if(context.get("nc.ui.uif2.editor.UserdefQueryParam#1b3223f")!=null)
 return (nc.ui.uif2.editor.UserdefQueryParam)context.get("nc.ui.uif2.editor.UserdefQueryParam#1b3223f");
  nc.ui.uif2.editor.UserdefQueryParam bean = new nc.ui.uif2.editor.UserdefQueryParam();
  context.put("nc.ui.uif2.editor.UserdefQueryParam#1b3223f",bean);
  bean.setRulecode("30_H");
  bean.setPos(0);
  bean.setPrefix("vdef");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.editor.UserdefQueryParam getUserdefQueryParam_18d644e(){
 if(context.get("nc.ui.uif2.editor.UserdefQueryParam#18d644e")!=null)
 return (nc.ui.uif2.editor.UserdefQueryParam)context.get("nc.ui.uif2.editor.UserdefQueryParam#18d644e");
  nc.ui.uif2.editor.UserdefQueryParam bean = new nc.ui.uif2.editor.UserdefQueryParam();
  context.put("nc.ui.uif2.editor.UserdefQueryParam#18d644e",bean);
  bean.setRulecode("30_B");
  bean.setPos(1);
  bean.setPrefix("vbdef");
  bean.setTabcode("bodytable1");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.uif2.editor.UserdefitemContainerListPreparator getSingleListPreparator(){
 if(context.get("singleListPreparator")!=null)
 return (nc.ui.uif2.editor.UserdefitemContainerListPreparator)context.get("singleListPreparator");
  nc.ui.uif2.editor.UserdefitemContainerListPreparator bean = new nc.ui.uif2.editor.UserdefitemContainerListPreparator();
  context.put("singleListPreparator",bean);
  bean.setContainer(getUserdefitemContainer());
  bean.setParams(getManagedList4());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList4(){  List list = new ArrayList();  list.add(getUserdefQueryParam_1d17499());  list.add(getUserdefQueryParam_a71273());  return list;}

private nc.ui.uif2.editor.UserdefQueryParam getUserdefQueryParam_1d17499(){
 if(context.get("nc.ui.uif2.editor.UserdefQueryParam#1d17499")!=null)
 return (nc.ui.uif2.editor.UserdefQueryParam)context.get("nc.ui.uif2.editor.UserdefQueryParam#1d17499");
  nc.ui.uif2.editor.UserdefQueryParam bean = new nc.ui.uif2.editor.UserdefQueryParam();
  context.put("nc.ui.uif2.editor.UserdefQueryParam#1d17499",bean);
  bean.setRulecode("30_H");
  bean.setPos(0);
  bean.setPrefix("vdef");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.editor.UserdefQueryParam getUserdefQueryParam_a71273(){
 if(context.get("nc.ui.uif2.editor.UserdefQueryParam#a71273")!=null)
 return (nc.ui.uif2.editor.UserdefQueryParam)context.get("nc.ui.uif2.editor.UserdefQueryParam#a71273");
  nc.ui.uif2.editor.UserdefQueryParam bean = new nc.ui.uif2.editor.UserdefQueryParam();
  context.put("nc.ui.uif2.editor.UserdefQueryParam#a71273",bean);
  bean.setRulecode("30_B");
  bean.setPos(0);
  bean.setPrefix("vbdef");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.billref.push.MarAsstBillRefPushPreparator getMarAsstPreparator(){
 if(context.get("marAsstPreparator")!=null)
 return (nc.ui.pubapp.billref.push.MarAsstBillRefPushPreparator)context.get("marAsstPreparator");
  nc.ui.pubapp.billref.push.MarAsstBillRefPushPreparator bean = new nc.ui.pubapp.billref.push.MarAsstBillRefPushPreparator();
  context.put("marAsstPreparator",bean);
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

public nc.ui.pubapp.billref.src.editor.BillRefUserDefItemContainer getUserdefitemContainer(){
 if(context.get("userdefitemContainer")!=null)
 return (nc.ui.pubapp.billref.src.editor.BillRefUserDefItemContainer)context.get("userdefitemContainer");
  nc.ui.pubapp.billref.src.editor.BillRefUserDefItemContainer bean = new nc.ui.pubapp.billref.src.editor.BillRefUserDefItemContainer();
  context.put("userdefitemContainer",bean);
  bean.setParams(getManagedList5());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList5(){  List list = new ArrayList();  list.add(getQueryParam_ac70ab());  list.add(getQueryParam_1191d29());  list.add(getQueryParam_1c982cc());  return list;}

private nc.ui.uif2.userdefitem.QueryParam getQueryParam_ac70ab(){
 if(context.get("nc.ui.uif2.userdefitem.QueryParam#ac70ab")!=null)
 return (nc.ui.uif2.userdefitem.QueryParam)context.get("nc.ui.uif2.userdefitem.QueryParam#ac70ab");
  nc.ui.uif2.userdefitem.QueryParam bean = new nc.ui.uif2.userdefitem.QueryParam();
  context.put("nc.ui.uif2.userdefitem.QueryParam#ac70ab",bean);
  bean.setRulecode("30_H");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.userdefitem.QueryParam getQueryParam_1191d29(){
 if(context.get("nc.ui.uif2.userdefitem.QueryParam#1191d29")!=null)
 return (nc.ui.uif2.userdefitem.QueryParam)context.get("nc.ui.uif2.userdefitem.QueryParam#1191d29");
  nc.ui.uif2.userdefitem.QueryParam bean = new nc.ui.uif2.userdefitem.QueryParam();
  context.put("nc.ui.uif2.userdefitem.QueryParam#1191d29",bean);
  bean.setRulecode("30_B");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.userdefitem.QueryParam getQueryParam_1c982cc(){
 if(context.get("nc.ui.uif2.userdefitem.QueryParam#1c982cc")!=null)
 return (nc.ui.uif2.userdefitem.QueryParam)context.get("nc.ui.uif2.userdefitem.QueryParam#1c982cc");
  nc.ui.uif2.userdefitem.QueryParam bean = new nc.ui.uif2.userdefitem.QueryParam();
  context.put("nc.ui.uif2.userdefitem.QueryParam#1c982cc",bean);
  bean.setRulecode("materialassistant");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

}
