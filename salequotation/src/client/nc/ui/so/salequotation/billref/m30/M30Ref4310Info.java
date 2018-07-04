package nc.ui.so.salequotation.billref.m30;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.ui.uif2.factory.AbstractJavaBeanDefinition;

public class M30Ref4310Info extends AbstractJavaBeanDefinition{
	private Map<String, Object> context = new HashMap();
public nc.ui.pubapp.billref.src.action.QueryAction getQueryAction(){
 if(context.get("queryAction")!=null)
 return (nc.ui.pubapp.billref.src.action.QueryAction)context.get("queryAction");
  nc.ui.pubapp.billref.src.action.QueryAction bean = new nc.ui.pubapp.billref.src.action.QueryAction();
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
  bean.setHeadVO(getSalequotationHVO_445c28());
  bean.setBodyVO(getSalequotationBVO_1fe62f4());
  bean.setBillVO(getAggSalequotationHVO_128960());
  bean.setViewVO(getSalequoViewVO_f900bd());
  bean.setBillNodeKey("4310TO30");
  bean.setBillViewNodeKey("4310to30_L");
  bean.setQueryService(getQueryServiceFor30_19f8e55());
  bean.setRefPanelInit(getM4310RefUIInit_39901b());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.vo.so.salequotation.entity.SalequotationHVO getSalequotationHVO_445c28(){
 if(context.get("nc.vo.so.salequotation.entity.SalequotationHVO#445c28")!=null)
 return (nc.vo.so.salequotation.entity.SalequotationHVO)context.get("nc.vo.so.salequotation.entity.SalequotationHVO#445c28");
  nc.vo.so.salequotation.entity.SalequotationHVO bean = new nc.vo.so.salequotation.entity.SalequotationHVO();
  context.put("nc.vo.so.salequotation.entity.SalequotationHVO#445c28",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.vo.so.salequotation.entity.SalequotationBVO getSalequotationBVO_1fe62f4(){
 if(context.get("nc.vo.so.salequotation.entity.SalequotationBVO#1fe62f4")!=null)
 return (nc.vo.so.salequotation.entity.SalequotationBVO)context.get("nc.vo.so.salequotation.entity.SalequotationBVO#1fe62f4");
  nc.vo.so.salequotation.entity.SalequotationBVO bean = new nc.vo.so.salequotation.entity.SalequotationBVO();
  context.put("nc.vo.so.salequotation.entity.SalequotationBVO#1fe62f4",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.vo.so.salequotation.entity.AggSalequotationHVO getAggSalequotationHVO_128960(){
 if(context.get("nc.vo.so.salequotation.entity.AggSalequotationHVO#128960")!=null)
 return (nc.vo.so.salequotation.entity.AggSalequotationHVO)context.get("nc.vo.so.salequotation.entity.AggSalequotationHVO#128960");
  nc.vo.so.salequotation.entity.AggSalequotationHVO bean = new nc.vo.so.salequotation.entity.AggSalequotationHVO();
  context.put("nc.vo.so.salequotation.entity.AggSalequotationHVO#128960",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.vo.so.salequotation.entity.SalequoViewVO getSalequoViewVO_f900bd(){
 if(context.get("nc.vo.so.salequotation.entity.SalequoViewVO#f900bd")!=null)
 return (nc.vo.so.salequotation.entity.SalequoViewVO)context.get("nc.vo.so.salequotation.entity.SalequoViewVO#f900bd");
  nc.vo.so.salequotation.entity.SalequoViewVO bean = new nc.vo.so.salequotation.entity.SalequoViewVO();
  context.put("nc.vo.so.salequotation.entity.SalequoViewVO#f900bd",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.so.salequotation.billref.m30.QueryServiceFor30 getQueryServiceFor30_19f8e55(){
 if(context.get("nc.ui.so.salequotation.billref.m30.QueryServiceFor30#19f8e55")!=null)
 return (nc.ui.so.salequotation.billref.m30.QueryServiceFor30)context.get("nc.ui.so.salequotation.billref.m30.QueryServiceFor30#19f8e55");
  nc.ui.so.salequotation.billref.m30.QueryServiceFor30 bean = new nc.ui.so.salequotation.billref.m30.QueryServiceFor30();
  context.put("nc.ui.so.salequotation.billref.m30.QueryServiceFor30#19f8e55",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.so.salequotation.billref.pub.M4310RefUIInit getM4310RefUIInit_39901b(){
 if(context.get("nc.ui.so.salequotation.billref.pub.M4310RefUIInit#39901b")!=null)
 return (nc.ui.so.salequotation.billref.pub.M4310RefUIInit)context.get("nc.ui.so.salequotation.billref.pub.M4310RefUIInit#39901b");
  nc.ui.so.salequotation.billref.pub.M4310RefUIInit bean = new nc.ui.so.salequotation.billref.pub.M4310RefUIInit();
  context.put("nc.ui.so.salequotation.billref.pub.M4310RefUIInit#39901b",bean);
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

public List getManagedList0(){  List list = new ArrayList();  list.add(getListPreparator());  list.add(getMarAsstPreparator());  return list;}

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

public List getManagedList1(){  List list = new ArrayList();  list.add(getSingleListPreparator());  list.add(getMarAsstPreparator());  return list;}

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

public List getManagedList2(){  List list = new ArrayList();  list.add(getUserdefQueryParam_12406c9());  list.add(getUserdefQueryParam_399788());  return list;}

public nc.ui.uif2.editor.UserdefQueryParam getUserdefQueryParam_12406c9(){
 if(context.get("nc.ui.uif2.editor.UserdefQueryParam#12406c9")!=null)
 return (nc.ui.uif2.editor.UserdefQueryParam)context.get("nc.ui.uif2.editor.UserdefQueryParam#12406c9");
  nc.ui.uif2.editor.UserdefQueryParam bean = new nc.ui.uif2.editor.UserdefQueryParam();
  context.put("nc.ui.uif2.editor.UserdefQueryParam#12406c9",bean);
  bean.setRulecode("4310_H");
  bean.setPos(0);
  bean.setPrefix("vdef");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.uif2.editor.UserdefQueryParam getUserdefQueryParam_399788(){
 if(context.get("nc.ui.uif2.editor.UserdefQueryParam#399788")!=null)
 return (nc.ui.uif2.editor.UserdefQueryParam)context.get("nc.ui.uif2.editor.UserdefQueryParam#399788");
  nc.ui.uif2.editor.UserdefQueryParam bean = new nc.ui.uif2.editor.UserdefQueryParam();
  context.put("nc.ui.uif2.editor.UserdefQueryParam#399788",bean);
  bean.setRulecode("4310_B");
  bean.setPos(1);
  bean.setPrefix("vbdef");
  bean.setTabcode("salequotationdetail");
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

public List getManagedList3(){  List list = new ArrayList();  list.add(getUserdefQueryParam_10b744c());  list.add(getUserdefQueryParam_1004af0());  return list;}

public nc.ui.uif2.editor.UserdefQueryParam getUserdefQueryParam_10b744c(){
 if(context.get("nc.ui.uif2.editor.UserdefQueryParam#10b744c")!=null)
 return (nc.ui.uif2.editor.UserdefQueryParam)context.get("nc.ui.uif2.editor.UserdefQueryParam#10b744c");
  nc.ui.uif2.editor.UserdefQueryParam bean = new nc.ui.uif2.editor.UserdefQueryParam();
  context.put("nc.ui.uif2.editor.UserdefQueryParam#10b744c",bean);
  bean.setRulecode("4310_H");
  bean.setPos(0);
  bean.setPrefix("vdef");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.uif2.editor.UserdefQueryParam getUserdefQueryParam_1004af0(){
 if(context.get("nc.ui.uif2.editor.UserdefQueryParam#1004af0")!=null)
 return (nc.ui.uif2.editor.UserdefQueryParam)context.get("nc.ui.uif2.editor.UserdefQueryParam#1004af0");
  nc.ui.uif2.editor.UserdefQueryParam bean = new nc.ui.uif2.editor.UserdefQueryParam();
  context.put("nc.ui.uif2.editor.UserdefQueryParam#1004af0",bean);
  bean.setRulecode("4310_B");
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
  bean.setMaterialField("pk_material");
  bean.setProjectField("pk_project");
  bean.setSupplierField("pk_supplier");
  bean.setProductorField("pk_productor");
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

public List getManagedList4(){  List list = new ArrayList();  list.add(getQueryParam_37ff2f());  list.add(getQueryParam_5291db());  list.add(getQueryParam_e5d58b());  return list;}

public nc.ui.uif2.userdefitem.QueryParam getQueryParam_37ff2f(){
 if(context.get("nc.ui.uif2.userdefitem.QueryParam#37ff2f")!=null)
 return (nc.ui.uif2.userdefitem.QueryParam)context.get("nc.ui.uif2.userdefitem.QueryParam#37ff2f");
  nc.ui.uif2.userdefitem.QueryParam bean = new nc.ui.uif2.userdefitem.QueryParam();
  context.put("nc.ui.uif2.userdefitem.QueryParam#37ff2f",bean);
  bean.setRulecode("4310_H");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.uif2.userdefitem.QueryParam getQueryParam_5291db(){
 if(context.get("nc.ui.uif2.userdefitem.QueryParam#5291db")!=null)
 return (nc.ui.uif2.userdefitem.QueryParam)context.get("nc.ui.uif2.userdefitem.QueryParam#5291db");
  nc.ui.uif2.userdefitem.QueryParam bean = new nc.ui.uif2.userdefitem.QueryParam();
  context.put("nc.ui.uif2.userdefitem.QueryParam#5291db",bean);
  bean.setRulecode("4310_B");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.uif2.userdefitem.QueryParam getQueryParam_e5d58b(){
 if(context.get("nc.ui.uif2.userdefitem.QueryParam#e5d58b")!=null)
 return (nc.ui.uif2.userdefitem.QueryParam)context.get("nc.ui.uif2.userdefitem.QueryParam#e5d58b");
  nc.ui.uif2.userdefitem.QueryParam bean = new nc.ui.uif2.userdefitem.QueryParam();
  context.put("nc.ui.uif2.userdefitem.QueryParam#e5d58b",bean);
  bean.setRulecode("materialassistant");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

}
