package nc.ui.so.salequotation.billref.mz3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.ui.uif2.factory.AbstractJavaBeanDefinition;

public class MZ3Ref4310Info extends AbstractJavaBeanDefinition{
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
  bean.setHeadVO(getSalequotationHVO_8f37e3());
  bean.setBodyVO(getSalequotationBVO_9e1e86());
  bean.setBillVO(getAggSalequotationHVO_e5f6e9());
  bean.setViewVO(getSalequoViewVO_1b5530c());
  bean.setBillNodeKey("4310TOZ3");
  bean.setBillViewNodeKey("4310toZ3_L");
  bean.setQueryService(getQueryServiceForZ3_15f7847());
  bean.setRefPanelInit(getM4310RefUIInit_d19ccb());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.vo.so.salequotation.entity.SalequotationHVO getSalequotationHVO_8f37e3(){
 if(context.get("nc.vo.so.salequotation.entity.SalequotationHVO#8f37e3")!=null)
 return (nc.vo.so.salequotation.entity.SalequotationHVO)context.get("nc.vo.so.salequotation.entity.SalequotationHVO#8f37e3");
  nc.vo.so.salequotation.entity.SalequotationHVO bean = new nc.vo.so.salequotation.entity.SalequotationHVO();
  context.put("nc.vo.so.salequotation.entity.SalequotationHVO#8f37e3",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.vo.so.salequotation.entity.SalequotationBVO getSalequotationBVO_9e1e86(){
 if(context.get("nc.vo.so.salequotation.entity.SalequotationBVO#9e1e86")!=null)
 return (nc.vo.so.salequotation.entity.SalequotationBVO)context.get("nc.vo.so.salequotation.entity.SalequotationBVO#9e1e86");
  nc.vo.so.salequotation.entity.SalequotationBVO bean = new nc.vo.so.salequotation.entity.SalequotationBVO();
  context.put("nc.vo.so.salequotation.entity.SalequotationBVO#9e1e86",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.vo.so.salequotation.entity.AggSalequotationHVO getAggSalequotationHVO_e5f6e9(){
 if(context.get("nc.vo.so.salequotation.entity.AggSalequotationHVO#e5f6e9")!=null)
 return (nc.vo.so.salequotation.entity.AggSalequotationHVO)context.get("nc.vo.so.salequotation.entity.AggSalequotationHVO#e5f6e9");
  nc.vo.so.salequotation.entity.AggSalequotationHVO bean = new nc.vo.so.salequotation.entity.AggSalequotationHVO();
  context.put("nc.vo.so.salequotation.entity.AggSalequotationHVO#e5f6e9",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.vo.so.salequotation.entity.SalequoViewVO getSalequoViewVO_1b5530c(){
 if(context.get("nc.vo.so.salequotation.entity.SalequoViewVO#1b5530c")!=null)
 return (nc.vo.so.salequotation.entity.SalequoViewVO)context.get("nc.vo.so.salequotation.entity.SalequoViewVO#1b5530c");
  nc.vo.so.salequotation.entity.SalequoViewVO bean = new nc.vo.so.salequotation.entity.SalequoViewVO();
  context.put("nc.vo.so.salequotation.entity.SalequoViewVO#1b5530c",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.so.salequotation.billref.mz3.QueryServiceForZ3 getQueryServiceForZ3_15f7847(){
 if(context.get("nc.ui.so.salequotation.billref.mz3.QueryServiceForZ3#15f7847")!=null)
 return (nc.ui.so.salequotation.billref.mz3.QueryServiceForZ3)context.get("nc.ui.so.salequotation.billref.mz3.QueryServiceForZ3#15f7847");
  nc.ui.so.salequotation.billref.mz3.QueryServiceForZ3 bean = new nc.ui.so.salequotation.billref.mz3.QueryServiceForZ3();
  context.put("nc.ui.so.salequotation.billref.mz3.QueryServiceForZ3#15f7847",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.so.salequotation.billref.pub.M4310RefUIInit getM4310RefUIInit_d19ccb(){
 if(context.get("nc.ui.so.salequotation.billref.pub.M4310RefUIInit#d19ccb")!=null)
 return (nc.ui.so.salequotation.billref.pub.M4310RefUIInit)context.get("nc.ui.so.salequotation.billref.pub.M4310RefUIInit#d19ccb");
  nc.ui.so.salequotation.billref.pub.M4310RefUIInit bean = new nc.ui.so.salequotation.billref.pub.M4310RefUIInit();
  context.put("nc.ui.so.salequotation.billref.pub.M4310RefUIInit#d19ccb",bean);
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

public List getManagedList2(){  List list = new ArrayList();  list.add(getUserdefQueryParam_18cf7bf());  list.add(getUserdefQueryParam_1dd53c2());  return list;}

public nc.ui.uif2.editor.UserdefQueryParam getUserdefQueryParam_18cf7bf(){
 if(context.get("nc.ui.uif2.editor.UserdefQueryParam#18cf7bf")!=null)
 return (nc.ui.uif2.editor.UserdefQueryParam)context.get("nc.ui.uif2.editor.UserdefQueryParam#18cf7bf");
  nc.ui.uif2.editor.UserdefQueryParam bean = new nc.ui.uif2.editor.UserdefQueryParam();
  context.put("nc.ui.uif2.editor.UserdefQueryParam#18cf7bf",bean);
  bean.setRulecode("4310_H");
  bean.setPos(0);
  bean.setPrefix("vdef");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.uif2.editor.UserdefQueryParam getUserdefQueryParam_1dd53c2(){
 if(context.get("nc.ui.uif2.editor.UserdefQueryParam#1dd53c2")!=null)
 return (nc.ui.uif2.editor.UserdefQueryParam)context.get("nc.ui.uif2.editor.UserdefQueryParam#1dd53c2");
  nc.ui.uif2.editor.UserdefQueryParam bean = new nc.ui.uif2.editor.UserdefQueryParam();
  context.put("nc.ui.uif2.editor.UserdefQueryParam#1dd53c2",bean);
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

public List getManagedList3(){  List list = new ArrayList();  list.add(getUserdefQueryParam_1a620fb());  list.add(getUserdefQueryParam_1bf637c());  return list;}

public nc.ui.uif2.editor.UserdefQueryParam getUserdefQueryParam_1a620fb(){
 if(context.get("nc.ui.uif2.editor.UserdefQueryParam#1a620fb")!=null)
 return (nc.ui.uif2.editor.UserdefQueryParam)context.get("nc.ui.uif2.editor.UserdefQueryParam#1a620fb");
  nc.ui.uif2.editor.UserdefQueryParam bean = new nc.ui.uif2.editor.UserdefQueryParam();
  context.put("nc.ui.uif2.editor.UserdefQueryParam#1a620fb",bean);
  bean.setRulecode("4310_H");
  bean.setPos(0);
  bean.setPrefix("vdef");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.uif2.editor.UserdefQueryParam getUserdefQueryParam_1bf637c(){
 if(context.get("nc.ui.uif2.editor.UserdefQueryParam#1bf637c")!=null)
 return (nc.ui.uif2.editor.UserdefQueryParam)context.get("nc.ui.uif2.editor.UserdefQueryParam#1bf637c");
  nc.ui.uif2.editor.UserdefQueryParam bean = new nc.ui.uif2.editor.UserdefQueryParam();
  context.put("nc.ui.uif2.editor.UserdefQueryParam#1bf637c",bean);
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

public List getManagedList4(){  List list = new ArrayList();  list.add(getQueryParam_1c9927());  list.add(getQueryParam_18b01c3());  list.add(getQueryParam_120cbd9());  return list;}

public nc.ui.uif2.userdefitem.QueryParam getQueryParam_1c9927(){
 if(context.get("nc.ui.uif2.userdefitem.QueryParam#1c9927")!=null)
 return (nc.ui.uif2.userdefitem.QueryParam)context.get("nc.ui.uif2.userdefitem.QueryParam#1c9927");
  nc.ui.uif2.userdefitem.QueryParam bean = new nc.ui.uif2.userdefitem.QueryParam();
  context.put("nc.ui.uif2.userdefitem.QueryParam#1c9927",bean);
  bean.setRulecode("4310_H");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.uif2.userdefitem.QueryParam getQueryParam_18b01c3(){
 if(context.get("nc.ui.uif2.userdefitem.QueryParam#18b01c3")!=null)
 return (nc.ui.uif2.userdefitem.QueryParam)context.get("nc.ui.uif2.userdefitem.QueryParam#18b01c3");
  nc.ui.uif2.userdefitem.QueryParam bean = new nc.ui.uif2.userdefitem.QueryParam();
  context.put("nc.ui.uif2.userdefitem.QueryParam#18b01c3",bean);
  bean.setRulecode("4310_B");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.uif2.userdefitem.QueryParam getQueryParam_120cbd9(){
 if(context.get("nc.ui.uif2.userdefitem.QueryParam#120cbd9")!=null)
 return (nc.ui.uif2.userdefitem.QueryParam)context.get("nc.ui.uif2.userdefitem.QueryParam#120cbd9");
  nc.ui.uif2.userdefitem.QueryParam bean = new nc.ui.uif2.userdefitem.QueryParam();
  context.put("nc.ui.uif2.userdefitem.QueryParam#120cbd9",bean);
  bean.setRulecode("materialassistant");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

}
