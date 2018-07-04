package nc.ui.so.m32.billref.ic.m4c;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.ui.uif2.factory.AbstractJavaBeanDefinition;

public class M4CRef32Info extends AbstractJavaBeanDefinition{
	private Map<String, Object> context = new HashMap();
public nc.ui.so.m32.billref.ic.m4c.QueryActionFor4C getQueryAction(){
 if(context.get("queryAction")!=null)
 return (nc.ui.so.m32.billref.ic.m4c.QueryActionFor4C)context.get("queryAction");
  nc.ui.so.m32.billref.ic.m4c.QueryActionFor4C bean = new nc.ui.so.m32.billref.ic.m4c.QueryActionFor4C();
  context.put("queryAction",bean);
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
  bean.setHeadVO(getSaleInvoiceHVO_1a1eb3a());
  bean.setBodyVO(getSaleInvoiceBVO_15a6b38());
  bean.setBillVO(getSaleInvoiceVO_13565b9());
  bean.setViewVO(getSaleInvoiceViewVO_a95654());
  bean.setBillNodeKey("32to4C");
  bean.setBillViewNodeKey("32to4C_L");
  bean.setSourcevoPkName("csrcid");
  bean.setSourcevoBillNOName("vsrccode");
  bean.setQueryService(getQueryServiceFor4C_472cc8());
  bean.setRefPanelInit(getM32RefUIInit_cd1841());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.vo.so.m32.entity.SaleInvoiceHVO getSaleInvoiceHVO_1a1eb3a(){
 if(context.get("nc.vo.so.m32.entity.SaleInvoiceHVO#1a1eb3a")!=null)
 return (nc.vo.so.m32.entity.SaleInvoiceHVO)context.get("nc.vo.so.m32.entity.SaleInvoiceHVO#1a1eb3a");
  nc.vo.so.m32.entity.SaleInvoiceHVO bean = new nc.vo.so.m32.entity.SaleInvoiceHVO();
  context.put("nc.vo.so.m32.entity.SaleInvoiceHVO#1a1eb3a",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.vo.so.m32.entity.SaleInvoiceBVO getSaleInvoiceBVO_15a6b38(){
 if(context.get("nc.vo.so.m32.entity.SaleInvoiceBVO#15a6b38")!=null)
 return (nc.vo.so.m32.entity.SaleInvoiceBVO)context.get("nc.vo.so.m32.entity.SaleInvoiceBVO#15a6b38");
  nc.vo.so.m32.entity.SaleInvoiceBVO bean = new nc.vo.so.m32.entity.SaleInvoiceBVO();
  context.put("nc.vo.so.m32.entity.SaleInvoiceBVO#15a6b38",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.vo.so.m32.entity.SaleInvoiceVO getSaleInvoiceVO_13565b9(){
 if(context.get("nc.vo.so.m32.entity.SaleInvoiceVO#13565b9")!=null)
 return (nc.vo.so.m32.entity.SaleInvoiceVO)context.get("nc.vo.so.m32.entity.SaleInvoiceVO#13565b9");
  nc.vo.so.m32.entity.SaleInvoiceVO bean = new nc.vo.so.m32.entity.SaleInvoiceVO();
  context.put("nc.vo.so.m32.entity.SaleInvoiceVO#13565b9",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.vo.so.m32.entity.SaleInvoiceViewVO getSaleInvoiceViewVO_a95654(){
 if(context.get("nc.vo.so.m32.entity.SaleInvoiceViewVO#a95654")!=null)
 return (nc.vo.so.m32.entity.SaleInvoiceViewVO)context.get("nc.vo.so.m32.entity.SaleInvoiceViewVO#a95654");
  nc.vo.so.m32.entity.SaleInvoiceViewVO bean = new nc.vo.so.m32.entity.SaleInvoiceViewVO();
  context.put("nc.vo.so.m32.entity.SaleInvoiceViewVO#a95654",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.so.m32.billref.ic.m4c.QueryServiceFor4C getQueryServiceFor4C_472cc8(){
 if(context.get("nc.ui.so.m32.billref.ic.m4c.QueryServiceFor4C#472cc8")!=null)
 return (nc.ui.so.m32.billref.ic.m4c.QueryServiceFor4C)context.get("nc.ui.so.m32.billref.ic.m4c.QueryServiceFor4C#472cc8");
  nc.ui.so.m32.billref.ic.m4c.QueryServiceFor4C bean = new nc.ui.so.m32.billref.ic.m4c.QueryServiceFor4C();
  context.put("nc.ui.so.m32.billref.ic.m4c.QueryServiceFor4C#472cc8",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.so.m32.billref.M32RefUIInit getM32RefUIInit_cd1841(){
 if(context.get("nc.ui.so.m32.billref.M32RefUIInit#cd1841")!=null)
 return (nc.ui.so.m32.billref.M32RefUIInit)context.get("nc.ui.so.m32.billref.M32RefUIInit#cd1841");
  nc.ui.so.m32.billref.M32RefUIInit bean = new nc.ui.so.m32.billref.M32RefUIInit();
  context.put("nc.ui.so.m32.billref.M32RefUIInit#cd1841",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.view.CompositeBillListDataPrepare getUserdefitemListPreparator(){
 if(context.get("userdefitemListPreparator")!=null)
 return (nc.ui.pubapp.uif2app.view.CompositeBillListDataPrepare)context.get("userdefitemListPreparator");
  nc.ui.pubapp.uif2app.view.CompositeBillListDataPrepare bean = new nc.ui.pubapp.uif2app.view.CompositeBillListDataPrepare();
  context.put("userdefitemListPreparator",bean);
  bean.setBillListDataPrepares(getManagedList0());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList0(){  List list = new ArrayList();  list.add(getListPreparator());  list.add(getMarAsstPreparator());  return list;}

public nc.ui.pubapp.uif2app.view.CompositeBillListDataPrepare getSingleUserdefitemListPreparator(){
 if(context.get("singleUserdefitemListPreparator")!=null)
 return (nc.ui.pubapp.uif2app.view.CompositeBillListDataPrepare)context.get("singleUserdefitemListPreparator");
  nc.ui.pubapp.uif2app.view.CompositeBillListDataPrepare bean = new nc.ui.pubapp.uif2app.view.CompositeBillListDataPrepare();
  context.put("singleUserdefitemListPreparator",bean);
  bean.setBillListDataPrepares(getManagedList1());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList1(){  List list = new ArrayList();  list.add(getSingleListPreparator());  list.add(getMarAsstPreparator());  return list;}

public nc.ui.uif2.editor.UserdefitemContainerListPreparator getListPreparator(){
 if(context.get("listPreparator")!=null)
 return (nc.ui.uif2.editor.UserdefitemContainerListPreparator)context.get("listPreparator");
  nc.ui.uif2.editor.UserdefitemContainerListPreparator bean = new nc.ui.uif2.editor.UserdefitemContainerListPreparator();
  context.put("listPreparator",bean);
  bean.setContainer(getUserdefitemContainer());
  bean.setParams(getManagedList2());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList2(){  List list = new ArrayList();  list.add(getUserdefQueryParam_3e1687());  list.add(getUserdefQueryParam_390cae());  return list;}

private nc.ui.uif2.editor.UserdefQueryParam getUserdefQueryParam_3e1687(){
 if(context.get("nc.ui.uif2.editor.UserdefQueryParam#3e1687")!=null)
 return (nc.ui.uif2.editor.UserdefQueryParam)context.get("nc.ui.uif2.editor.UserdefQueryParam#3e1687");
  nc.ui.uif2.editor.UserdefQueryParam bean = new nc.ui.uif2.editor.UserdefQueryParam();
  context.put("nc.ui.uif2.editor.UserdefQueryParam#3e1687",bean);
  bean.setRulecode("32_H");
  bean.setPos(0);
  bean.setPrefix("vdef");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.editor.UserdefQueryParam getUserdefQueryParam_390cae(){
 if(context.get("nc.ui.uif2.editor.UserdefQueryParam#390cae")!=null)
 return (nc.ui.uif2.editor.UserdefQueryParam)context.get("nc.ui.uif2.editor.UserdefQueryParam#390cae");
  nc.ui.uif2.editor.UserdefQueryParam bean = new nc.ui.uif2.editor.UserdefQueryParam();
  context.put("nc.ui.uif2.editor.UserdefQueryParam#390cae",bean);
  bean.setRulecode("32_B");
  bean.setPos(1);
  bean.setPrefix("vbdef");
  bean.setTabcode("saleinvoice_b");
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
  bean.setParams(getManagedList3());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList3(){  List list = new ArrayList();  list.add(getUserdefQueryParam_16f8f51());  list.add(getUserdefQueryParam_12a3dbc());  return list;}

private nc.ui.uif2.editor.UserdefQueryParam getUserdefQueryParam_16f8f51(){
 if(context.get("nc.ui.uif2.editor.UserdefQueryParam#16f8f51")!=null)
 return (nc.ui.uif2.editor.UserdefQueryParam)context.get("nc.ui.uif2.editor.UserdefQueryParam#16f8f51");
  nc.ui.uif2.editor.UserdefQueryParam bean = new nc.ui.uif2.editor.UserdefQueryParam();
  context.put("nc.ui.uif2.editor.UserdefQueryParam#16f8f51",bean);
  bean.setRulecode("32_H");
  bean.setPos(0);
  bean.setPrefix("vdef");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.editor.UserdefQueryParam getUserdefQueryParam_12a3dbc(){
 if(context.get("nc.ui.uif2.editor.UserdefQueryParam#12a3dbc")!=null)
 return (nc.ui.uif2.editor.UserdefQueryParam)context.get("nc.ui.uif2.editor.UserdefQueryParam#12a3dbc");
  nc.ui.uif2.editor.UserdefQueryParam bean = new nc.ui.uif2.editor.UserdefQueryParam();
  context.put("nc.ui.uif2.editor.UserdefQueryParam#12a3dbc",bean);
  bean.setRulecode("32_B");
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
  bean.setParams(getManagedList4());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList4(){  List list = new ArrayList();  list.add(getQueryParam_1517610());  list.add(getQueryParam_11e3e25());  list.add(getQueryParam_820d75());  return list;}

private nc.ui.uif2.userdefitem.QueryParam getQueryParam_1517610(){
 if(context.get("nc.ui.uif2.userdefitem.QueryParam#1517610")!=null)
 return (nc.ui.uif2.userdefitem.QueryParam)context.get("nc.ui.uif2.userdefitem.QueryParam#1517610");
  nc.ui.uif2.userdefitem.QueryParam bean = new nc.ui.uif2.userdefitem.QueryParam();
  context.put("nc.ui.uif2.userdefitem.QueryParam#1517610",bean);
  bean.setRulecode("32_H");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.userdefitem.QueryParam getQueryParam_11e3e25(){
 if(context.get("nc.ui.uif2.userdefitem.QueryParam#11e3e25")!=null)
 return (nc.ui.uif2.userdefitem.QueryParam)context.get("nc.ui.uif2.userdefitem.QueryParam#11e3e25");
  nc.ui.uif2.userdefitem.QueryParam bean = new nc.ui.uif2.userdefitem.QueryParam();
  context.put("nc.ui.uif2.userdefitem.QueryParam#11e3e25",bean);
  bean.setRulecode("32_B");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.userdefitem.QueryParam getQueryParam_820d75(){
 if(context.get("nc.ui.uif2.userdefitem.QueryParam#820d75")!=null)
 return (nc.ui.uif2.userdefitem.QueryParam)context.get("nc.ui.uif2.userdefitem.QueryParam#820d75");
  nc.ui.uif2.userdefitem.QueryParam bean = new nc.ui.uif2.userdefitem.QueryParam();
  context.put("nc.ui.uif2.userdefitem.QueryParam#820d75",bean);
  bean.setRulecode("materialassistant");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

}
