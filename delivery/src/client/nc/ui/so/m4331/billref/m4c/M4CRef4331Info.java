package nc.ui.so.m4331.billref.m4c;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.ui.uif2.factory.AbstractJavaBeanDefinition;

public class M4CRef4331Info extends AbstractJavaBeanDefinition{
	private Map<String, Object> context = new HashMap();
public nc.ui.so.m4331.billref.m4c.QueryActionFor4C getQueryAction(){
 if(context.get("queryAction")!=null)
 return (nc.ui.so.m4331.billref.m4c.QueryActionFor4C)context.get("queryAction");
  nc.ui.so.m4331.billref.m4c.QueryActionFor4C bean = new nc.ui.so.m4331.billref.m4c.QueryActionFor4C();
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
  bean.setFakePKUsed(true);
  bean.setHeadVO(getDeliveryHVO_22c2c2());
  bean.setBodyVO(getDeliveryBVO_2080e3());
  bean.setBillVO(getDeliveryVO_1ede448());
  bean.setViewVO(getDeliveryViewVO_12652bb());
  bean.setBillNodeKey("4331to4C_C");
  bean.setBillViewNodeKey("4331to4C_L");
  bean.setSourcevoPkName("csrcid");
  bean.setSourcevoBillNOName("vsrccode");
  bean.setQueryService(getQueryServiceFor4C_1019a8f());
  bean.setRefPanelInit(getM4331RefUIInit_4a29fb());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.vo.so.m4331.entity.DeliveryHVO getDeliveryHVO_22c2c2(){
 if(context.get("nc.vo.so.m4331.entity.DeliveryHVO#22c2c2")!=null)
 return (nc.vo.so.m4331.entity.DeliveryHVO)context.get("nc.vo.so.m4331.entity.DeliveryHVO#22c2c2");
  nc.vo.so.m4331.entity.DeliveryHVO bean = new nc.vo.so.m4331.entity.DeliveryHVO();
  context.put("nc.vo.so.m4331.entity.DeliveryHVO#22c2c2",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.vo.so.m4331.entity.DeliveryBVO getDeliveryBVO_2080e3(){
 if(context.get("nc.vo.so.m4331.entity.DeliveryBVO#2080e3")!=null)
 return (nc.vo.so.m4331.entity.DeliveryBVO)context.get("nc.vo.so.m4331.entity.DeliveryBVO#2080e3");
  nc.vo.so.m4331.entity.DeliveryBVO bean = new nc.vo.so.m4331.entity.DeliveryBVO();
  context.put("nc.vo.so.m4331.entity.DeliveryBVO#2080e3",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.vo.so.m4331.entity.DeliveryVO getDeliveryVO_1ede448(){
 if(context.get("nc.vo.so.m4331.entity.DeliveryVO#1ede448")!=null)
 return (nc.vo.so.m4331.entity.DeliveryVO)context.get("nc.vo.so.m4331.entity.DeliveryVO#1ede448");
  nc.vo.so.m4331.entity.DeliveryVO bean = new nc.vo.so.m4331.entity.DeliveryVO();
  context.put("nc.vo.so.m4331.entity.DeliveryVO#1ede448",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.vo.so.m4331.entity.DeliveryViewVO getDeliveryViewVO_12652bb(){
 if(context.get("nc.vo.so.m4331.entity.DeliveryViewVO#12652bb")!=null)
 return (nc.vo.so.m4331.entity.DeliveryViewVO)context.get("nc.vo.so.m4331.entity.DeliveryViewVO#12652bb");
  nc.vo.so.m4331.entity.DeliveryViewVO bean = new nc.vo.so.m4331.entity.DeliveryViewVO();
  context.put("nc.vo.so.m4331.entity.DeliveryViewVO#12652bb",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.so.m4331.billref.QueryServiceFor4C getQueryServiceFor4C_1019a8f(){
 if(context.get("nc.ui.so.m4331.billref.QueryServiceFor4C#1019a8f")!=null)
 return (nc.ui.so.m4331.billref.QueryServiceFor4C)context.get("nc.ui.so.m4331.billref.QueryServiceFor4C#1019a8f");
  nc.ui.so.m4331.billref.QueryServiceFor4C bean = new nc.ui.so.m4331.billref.QueryServiceFor4C();
  context.put("nc.ui.so.m4331.billref.QueryServiceFor4C#1019a8f",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.so.m4331.billref.M4331RefUIInit getM4331RefUIInit_4a29fb(){
 if(context.get("nc.ui.so.m4331.billref.M4331RefUIInit#4a29fb")!=null)
 return (nc.ui.so.m4331.billref.M4331RefUIInit)context.get("nc.ui.so.m4331.billref.M4331RefUIInit#4a29fb");
  nc.ui.so.m4331.billref.M4331RefUIInit bean = new nc.ui.so.m4331.billref.M4331RefUIInit();
  context.put("nc.ui.so.m4331.billref.M4331RefUIInit#4a29fb",bean);
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

public nc.ui.uif2.editor.UserdefitemContainerListPreparator getListPreparator(){
 if(context.get("listPreparator")!=null)
 return (nc.ui.uif2.editor.UserdefitemContainerListPreparator)context.get("listPreparator");
  nc.ui.uif2.editor.UserdefitemContainerListPreparator bean = new nc.ui.uif2.editor.UserdefitemContainerListPreparator();
  context.put("listPreparator",bean);
  bean.setContainer(getUserdefitemContainer());
  bean.setParams(getManagedList1());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public List getManagedList1(){  List list = new ArrayList();  list.add(getUserdefQueryParam_10a3788());  list.add(getUserdefQueryParam_1c3d3b0());  return list;}

public nc.ui.uif2.editor.UserdefQueryParam getUserdefQueryParam_10a3788(){
 if(context.get("nc.ui.uif2.editor.UserdefQueryParam#10a3788")!=null)
 return (nc.ui.uif2.editor.UserdefQueryParam)context.get("nc.ui.uif2.editor.UserdefQueryParam#10a3788");
  nc.ui.uif2.editor.UserdefQueryParam bean = new nc.ui.uif2.editor.UserdefQueryParam();
  context.put("nc.ui.uif2.editor.UserdefQueryParam#10a3788",bean);
  bean.setRulecode("4331_H");
  bean.setPos(0);
  bean.setPrefix("vdef");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.uif2.editor.UserdefQueryParam getUserdefQueryParam_1c3d3b0(){
 if(context.get("nc.ui.uif2.editor.UserdefQueryParam#1c3d3b0")!=null)
 return (nc.ui.uif2.editor.UserdefQueryParam)context.get("nc.ui.uif2.editor.UserdefQueryParam#1c3d3b0");
  nc.ui.uif2.editor.UserdefQueryParam bean = new nc.ui.uif2.editor.UserdefQueryParam();
  context.put("nc.ui.uif2.editor.UserdefQueryParam#1c3d3b0",bean);
  bean.setRulecode("4331_B");
  bean.setPos(1);
  bean.setPrefix("vbdef");
  bean.setTabcode("cdeliverybid");
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
  bean.setParams(getManagedList2());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public List getManagedList2(){  List list = new ArrayList();  list.add(getUserdefQueryParam_17f0330());  list.add(getUserdefQueryParam_1aa63da());  return list;}

public nc.ui.uif2.editor.UserdefQueryParam getUserdefQueryParam_17f0330(){
 if(context.get("nc.ui.uif2.editor.UserdefQueryParam#17f0330")!=null)
 return (nc.ui.uif2.editor.UserdefQueryParam)context.get("nc.ui.uif2.editor.UserdefQueryParam#17f0330");
  nc.ui.uif2.editor.UserdefQueryParam bean = new nc.ui.uif2.editor.UserdefQueryParam();
  context.put("nc.ui.uif2.editor.UserdefQueryParam#17f0330",bean);
  bean.setRulecode("4331_H");
  bean.setPos(0);
  bean.setPrefix("vdef");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.uif2.editor.UserdefQueryParam getUserdefQueryParam_1aa63da(){
 if(context.get("nc.ui.uif2.editor.UserdefQueryParam#1aa63da")!=null)
 return (nc.ui.uif2.editor.UserdefQueryParam)context.get("nc.ui.uif2.editor.UserdefQueryParam#1aa63da");
  nc.ui.uif2.editor.UserdefQueryParam bean = new nc.ui.uif2.editor.UserdefQueryParam();
  context.put("nc.ui.uif2.editor.UserdefQueryParam#1aa63da",bean);
  bean.setRulecode("4331_B");
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

public nc.ui.pubapp.uif2app.view.CompositeBillListDataPrepare getSingleUserdefitemListPreparator(){
 if(context.get("singleUserdefitemListPreparator")!=null)
 return (nc.ui.pubapp.uif2app.view.CompositeBillListDataPrepare)context.get("singleUserdefitemListPreparator");
  nc.ui.pubapp.uif2app.view.CompositeBillListDataPrepare bean = new nc.ui.pubapp.uif2app.view.CompositeBillListDataPrepare();
  context.put("singleUserdefitemListPreparator",bean);
  bean.setBillListDataPrepares(getManagedList3());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public List getManagedList3(){  List list = new ArrayList();  list.add(getSingleListPreparator());  list.add(getMarAsstPreparator());  return list;}

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

public List getManagedList4(){  List list = new ArrayList();  list.add(getQueryParam_181ad40());  list.add(getQueryParam_9574b5());  list.add(getQueryParam_214ec4());  return list;}

public nc.ui.uif2.userdefitem.QueryParam getQueryParam_181ad40(){
 if(context.get("nc.ui.uif2.userdefitem.QueryParam#181ad40")!=null)
 return (nc.ui.uif2.userdefitem.QueryParam)context.get("nc.ui.uif2.userdefitem.QueryParam#181ad40");
  nc.ui.uif2.userdefitem.QueryParam bean = new nc.ui.uif2.userdefitem.QueryParam();
  context.put("nc.ui.uif2.userdefitem.QueryParam#181ad40",bean);
  bean.setRulecode("4331_H");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.uif2.userdefitem.QueryParam getQueryParam_9574b5(){
 if(context.get("nc.ui.uif2.userdefitem.QueryParam#9574b5")!=null)
 return (nc.ui.uif2.userdefitem.QueryParam)context.get("nc.ui.uif2.userdefitem.QueryParam#9574b5");
  nc.ui.uif2.userdefitem.QueryParam bean = new nc.ui.uif2.userdefitem.QueryParam();
  context.put("nc.ui.uif2.userdefitem.QueryParam#9574b5",bean);
  bean.setRulecode("4331_B");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.uif2.userdefitem.QueryParam getQueryParam_214ec4(){
 if(context.get("nc.ui.uif2.userdefitem.QueryParam#214ec4")!=null)
 return (nc.ui.uif2.userdefitem.QueryParam)context.get("nc.ui.uif2.userdefitem.QueryParam#214ec4");
  nc.ui.uif2.userdefitem.QueryParam bean = new nc.ui.uif2.userdefitem.QueryParam();
  context.put("nc.ui.uif2.userdefitem.QueryParam#214ec4",bean);
  bean.setRulecode("materialassistant");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

}
