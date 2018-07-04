package nc.ui.so.m4331.billref.m4804;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.ui.uif2.factory.AbstractJavaBeanDefinition;

public class M4804RefInfo extends AbstractJavaBeanDefinition{
	private Map<String, Object> context = new HashMap();
public nc.ui.so.m4331.billref.m4804.QueryActionFor4804 getQueryAction(){
 if(context.get("queryAction")!=null)
 return (nc.ui.so.m4331.billref.m4804.QueryActionFor4804)context.get("queryAction");
  nc.ui.so.m4331.billref.m4804.QueryActionFor4804 bean = new nc.ui.so.m4331.billref.m4804.QueryActionFor4804();
  context.put("queryAction",bean);
  bean.setRefContext((nc.ui.pubapp.billref.src.RefContext)findBeanInUIF2BeanFactory("refContext"));
return bean;
}

public nc.ui.pubapp.billref.src.RefInfo getRefInfo(){
 if(context.get("refInfo")!=null)
 return (nc.ui.pubapp.billref.src.RefInfo)context.get("refInfo");
  nc.ui.pubapp.billref.src.RefInfo bean = new nc.ui.pubapp.billref.src.RefInfo();
  context.put("refInfo",bean);
  bean.setUserdefitemListPreparator(getUserdefitemListPreparator());
  bean.setSingleUserdefitemListPreparator(getSingleUserdefitemListPreparator());
  bean.setHeadVO(getDeliveryHVO_16668e3());
  bean.setBodyVO(getDeliveryBVO_7016fb());
  bean.setBillVO(getDeliveryVO_f4bf1e());
  bean.setViewVO(getDeliveryViewVO_2f544b());
  bean.setBillNodeKey("4331_C");
  bean.setBillViewNodeKey("4331_L");
  bean.setSourcevoPkName("csrcid");
  bean.setSourcevoBillNOName("vsrccode");
  bean.setQueryService(getQueryServiceFor4804_147090b());
  bean.setRefPanelInit(getM4331RefUIInit_a2eb46());
return bean;
}

public nc.vo.so.m4331.entity.DeliveryHVO getDeliveryHVO_16668e3(){
 if(context.get("nc.vo.so.m4331.entity.DeliveryHVO#16668e3")!=null)
 return (nc.vo.so.m4331.entity.DeliveryHVO)context.get("nc.vo.so.m4331.entity.DeliveryHVO#16668e3");
  nc.vo.so.m4331.entity.DeliveryHVO bean = new nc.vo.so.m4331.entity.DeliveryHVO();
  context.put("nc.vo.so.m4331.entity.DeliveryHVO#16668e3",bean);
return bean;
}

public nc.vo.so.m4331.entity.DeliveryBVO getDeliveryBVO_7016fb(){
 if(context.get("nc.vo.so.m4331.entity.DeliveryBVO#7016fb")!=null)
 return (nc.vo.so.m4331.entity.DeliveryBVO)context.get("nc.vo.so.m4331.entity.DeliveryBVO#7016fb");
  nc.vo.so.m4331.entity.DeliveryBVO bean = new nc.vo.so.m4331.entity.DeliveryBVO();
  context.put("nc.vo.so.m4331.entity.DeliveryBVO#7016fb",bean);
return bean;
}

public nc.vo.so.m4331.entity.DeliveryVO getDeliveryVO_f4bf1e(){
 if(context.get("nc.vo.so.m4331.entity.DeliveryVO#f4bf1e")!=null)
 return (nc.vo.so.m4331.entity.DeliveryVO)context.get("nc.vo.so.m4331.entity.DeliveryVO#f4bf1e");
  nc.vo.so.m4331.entity.DeliveryVO bean = new nc.vo.so.m4331.entity.DeliveryVO();
  context.put("nc.vo.so.m4331.entity.DeliveryVO#f4bf1e",bean);
return bean;
}

public nc.vo.so.m4331.entity.DeliveryViewVO getDeliveryViewVO_2f544b(){
 if(context.get("nc.vo.so.m4331.entity.DeliveryViewVO#2f544b")!=null)
 return (nc.vo.so.m4331.entity.DeliveryViewVO)context.get("nc.vo.so.m4331.entity.DeliveryViewVO#2f544b");
  nc.vo.so.m4331.entity.DeliveryViewVO bean = new nc.vo.so.m4331.entity.DeliveryViewVO();
  context.put("nc.vo.so.m4331.entity.DeliveryViewVO#2f544b",bean);
return bean;
}

public nc.ui.so.m4331.billref.QueryServiceFor4804 getQueryServiceFor4804_147090b(){
 if(context.get("nc.ui.so.m4331.billref.QueryServiceFor4804#147090b")!=null)
 return (nc.ui.so.m4331.billref.QueryServiceFor4804)context.get("nc.ui.so.m4331.billref.QueryServiceFor4804#147090b");
  nc.ui.so.m4331.billref.QueryServiceFor4804 bean = new nc.ui.so.m4331.billref.QueryServiceFor4804();
  context.put("nc.ui.so.m4331.billref.QueryServiceFor4804#147090b",bean);
return bean;
}

public nc.ui.so.m4331.billref.M4331RefUIInit getM4331RefUIInit_a2eb46(){
 if(context.get("nc.ui.so.m4331.billref.M4331RefUIInit#a2eb46")!=null)
 return (nc.ui.so.m4331.billref.M4331RefUIInit)context.get("nc.ui.so.m4331.billref.M4331RefUIInit#a2eb46");
  nc.ui.so.m4331.billref.M4331RefUIInit bean = new nc.ui.so.m4331.billref.M4331RefUIInit();
  context.put("nc.ui.so.m4331.billref.M4331RefUIInit#a2eb46",bean);
return bean;
}

public nc.ui.pubapp.uif2app.view.CompositeBillListDataPrepare getUserdefitemListPreparator(){
 if(context.get("userdefitemListPreparator")!=null)
 return (nc.ui.pubapp.uif2app.view.CompositeBillListDataPrepare)context.get("userdefitemListPreparator");
  nc.ui.pubapp.uif2app.view.CompositeBillListDataPrepare bean = new nc.ui.pubapp.uif2app.view.CompositeBillListDataPrepare();
  context.put("userdefitemListPreparator",bean);
  bean.setBillListDataPrepares(getManagedList0());
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
return bean;
}

public List getManagedList1(){  List list = new ArrayList();  list.add(getUserdefQueryParam_47969());  list.add(getUserdefQueryParam_1bee1a2());  return list;}

public nc.ui.uif2.editor.UserdefQueryParam getUserdefQueryParam_47969(){
 if(context.get("nc.ui.uif2.editor.UserdefQueryParam#47969")!=null)
 return (nc.ui.uif2.editor.UserdefQueryParam)context.get("nc.ui.uif2.editor.UserdefQueryParam#47969");
  nc.ui.uif2.editor.UserdefQueryParam bean = new nc.ui.uif2.editor.UserdefQueryParam();
  context.put("nc.ui.uif2.editor.UserdefQueryParam#47969",bean);
  bean.setRulecode("38_H");
  bean.setPos(0);
  bean.setPrefix("vdef");
return bean;
}

public nc.ui.uif2.editor.UserdefQueryParam getUserdefQueryParam_1bee1a2(){
 if(context.get("nc.ui.uif2.editor.UserdefQueryParam#1bee1a2")!=null)
 return (nc.ui.uif2.editor.UserdefQueryParam)context.get("nc.ui.uif2.editor.UserdefQueryParam#1bee1a2");
  nc.ui.uif2.editor.UserdefQueryParam bean = new nc.ui.uif2.editor.UserdefQueryParam();
  context.put("nc.ui.uif2.editor.UserdefQueryParam#1bee1a2",bean);
  bean.setRulecode("38_B");
  bean.setPos(1);
  bean.setPrefix("vbdef");
  bean.setTabcode("material");
return bean;
}

public nc.ui.uif2.editor.UserdefitemContainerListPreparator getSingleListPreparator(){
 if(context.get("singleListPreparator")!=null)
 return (nc.ui.uif2.editor.UserdefitemContainerListPreparator)context.get("singleListPreparator");
  nc.ui.uif2.editor.UserdefitemContainerListPreparator bean = new nc.ui.uif2.editor.UserdefitemContainerListPreparator();
  context.put("singleListPreparator",bean);
  bean.setContainer(getUserdefitemContainer());
  bean.setParams(getManagedList2());
return bean;
}

public List getManagedList2(){  List list = new ArrayList();  list.add(getUserdefQueryParam_cf691e());  list.add(getUserdefQueryParam_fe30fc());  return list;}

public nc.ui.uif2.editor.UserdefQueryParam getUserdefQueryParam_cf691e(){
 if(context.get("nc.ui.uif2.editor.UserdefQueryParam#cf691e")!=null)
 return (nc.ui.uif2.editor.UserdefQueryParam)context.get("nc.ui.uif2.editor.UserdefQueryParam#cf691e");
  nc.ui.uif2.editor.UserdefQueryParam bean = new nc.ui.uif2.editor.UserdefQueryParam();
  context.put("nc.ui.uif2.editor.UserdefQueryParam#cf691e",bean);
  bean.setRulecode("4331_H");
  bean.setPos(0);
  bean.setPrefix("vdef");
return bean;
}

public nc.ui.uif2.editor.UserdefQueryParam getUserdefQueryParam_fe30fc(){
 if(context.get("nc.ui.uif2.editor.UserdefQueryParam#fe30fc")!=null)
 return (nc.ui.uif2.editor.UserdefQueryParam)context.get("nc.ui.uif2.editor.UserdefQueryParam#fe30fc");
  nc.ui.uif2.editor.UserdefQueryParam bean = new nc.ui.uif2.editor.UserdefQueryParam();
  context.put("nc.ui.uif2.editor.UserdefQueryParam#fe30fc",bean);
  bean.setRulecode("4331_B");
  bean.setPos(0);
  bean.setPrefix("vbdef");
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
return bean;
}

public nc.ui.pubapp.uif2app.view.CompositeBillListDataPrepare getSingleUserdefitemListPreparator(){
 if(context.get("singleUserdefitemListPreparator")!=null)
 return (nc.ui.pubapp.uif2app.view.CompositeBillListDataPrepare)context.get("singleUserdefitemListPreparator");
  nc.ui.pubapp.uif2app.view.CompositeBillListDataPrepare bean = new nc.ui.pubapp.uif2app.view.CompositeBillListDataPrepare();
  context.put("singleUserdefitemListPreparator",bean);
  bean.setBillListDataPrepares(getManagedList3());
return bean;
}

public List getManagedList3(){  List list = new ArrayList();  list.add(getSingleListPreparator());  list.add(getMarAsstPreparator());  return list;}

public nc.ui.pubapp.billref.src.editor.BillRefUserDefItemContainer getUserdefitemContainer(){
 if(context.get("userdefitemContainer")!=null)
 return (nc.ui.pubapp.billref.src.editor.BillRefUserDefItemContainer)context.get("userdefitemContainer");
  nc.ui.pubapp.billref.src.editor.BillRefUserDefItemContainer bean = new nc.ui.pubapp.billref.src.editor.BillRefUserDefItemContainer();
  context.put("userdefitemContainer",bean);
  bean.setParams(getManagedList4());
return bean;
}

public List getManagedList4(){  List list = new ArrayList();  list.add(getQueryParam_420985());  list.add(getQueryParam_17efae4());  list.add(getQueryParam_189c7eb());  return list;}

public nc.ui.uif2.userdefitem.QueryParam getQueryParam_420985(){
 if(context.get("nc.ui.uif2.userdefitem.QueryParam#420985")!=null)
 return (nc.ui.uif2.userdefitem.QueryParam)context.get("nc.ui.uif2.userdefitem.QueryParam#420985");
  nc.ui.uif2.userdefitem.QueryParam bean = new nc.ui.uif2.userdefitem.QueryParam();
  context.put("nc.ui.uif2.userdefitem.QueryParam#420985",bean);
  bean.setRulecode("4331_H");
return bean;
}

public nc.ui.uif2.userdefitem.QueryParam getQueryParam_17efae4(){
 if(context.get("nc.ui.uif2.userdefitem.QueryParam#17efae4")!=null)
 return (nc.ui.uif2.userdefitem.QueryParam)context.get("nc.ui.uif2.userdefitem.QueryParam#17efae4");
  nc.ui.uif2.userdefitem.QueryParam bean = new nc.ui.uif2.userdefitem.QueryParam();
  context.put("nc.ui.uif2.userdefitem.QueryParam#17efae4",bean);
  bean.setRulecode("4331_B");
return bean;
}

public nc.ui.uif2.userdefitem.QueryParam getQueryParam_189c7eb(){
 if(context.get("nc.ui.uif2.userdefitem.QueryParam#189c7eb")!=null)
 return (nc.ui.uif2.userdefitem.QueryParam)context.get("nc.ui.uif2.userdefitem.QueryParam#189c7eb");
  nc.ui.uif2.userdefitem.QueryParam bean = new nc.ui.uif2.userdefitem.QueryParam();
  context.put("nc.ui.uif2.userdefitem.QueryParam#189c7eb",bean);
  bean.setRulecode("materialassistant");
return bean;
}

}
