package nc.ui.so.m4331.billref.m4y;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.ui.uif2.factory.AbstractJavaBeanDefinition;

public class M4YRef4331Info extends AbstractJavaBeanDefinition{
	private Map<String, Object> context = new HashMap();
public nc.ui.so.m4331.billref.m4y.QueryActionFor4Y getQueryAction(){
 if(context.get("queryAction")!=null)
 return (nc.ui.so.m4331.billref.m4y.QueryActionFor4Y)context.get("queryAction");
  nc.ui.so.m4331.billref.m4y.QueryActionFor4Y bean = new nc.ui.so.m4331.billref.m4y.QueryActionFor4Y();
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
  bean.setHeadVO(getDeliveryHVO_189358());
  bean.setBodyVO(getDeliveryBVO_1718e0c());
  bean.setBillVO(getDeliveryVO_24de93());
  bean.setViewVO(getDeliveryViewVO_90997c());
  bean.setBillNodeKey("4331to4Y_C");
  bean.setBillViewNodeKey("4331to4Y_L");
  bean.setSourcevoPkName("csrcid");
  bean.setSourcevoBillNOName("vsrccode");
  bean.setQueryService(getQueryServiceFor4Y_14ca255());
  bean.setRefPanelInit(getM4331RefUIInit_35b075());
return bean;
}

public nc.vo.so.m4331.entity.DeliveryHVO getDeliveryHVO_189358(){
 if(context.get("nc.vo.so.m4331.entity.DeliveryHVO#189358")!=null)
 return (nc.vo.so.m4331.entity.DeliveryHVO)context.get("nc.vo.so.m4331.entity.DeliveryHVO#189358");
  nc.vo.so.m4331.entity.DeliveryHVO bean = new nc.vo.so.m4331.entity.DeliveryHVO();
  context.put("nc.vo.so.m4331.entity.DeliveryHVO#189358",bean);
return bean;
}

public nc.vo.so.m4331.entity.DeliveryBVO getDeliveryBVO_1718e0c(){
 if(context.get("nc.vo.so.m4331.entity.DeliveryBVO#1718e0c")!=null)
 return (nc.vo.so.m4331.entity.DeliveryBVO)context.get("nc.vo.so.m4331.entity.DeliveryBVO#1718e0c");
  nc.vo.so.m4331.entity.DeliveryBVO bean = new nc.vo.so.m4331.entity.DeliveryBVO();
  context.put("nc.vo.so.m4331.entity.DeliveryBVO#1718e0c",bean);
return bean;
}

public nc.vo.so.m4331.entity.DeliveryVO getDeliveryVO_24de93(){
 if(context.get("nc.vo.so.m4331.entity.DeliveryVO#24de93")!=null)
 return (nc.vo.so.m4331.entity.DeliveryVO)context.get("nc.vo.so.m4331.entity.DeliveryVO#24de93");
  nc.vo.so.m4331.entity.DeliveryVO bean = new nc.vo.so.m4331.entity.DeliveryVO();
  context.put("nc.vo.so.m4331.entity.DeliveryVO#24de93",bean);
return bean;
}

public nc.vo.so.m4331.entity.DeliveryViewVO getDeliveryViewVO_90997c(){
 if(context.get("nc.vo.so.m4331.entity.DeliveryViewVO#90997c")!=null)
 return (nc.vo.so.m4331.entity.DeliveryViewVO)context.get("nc.vo.so.m4331.entity.DeliveryViewVO#90997c");
  nc.vo.so.m4331.entity.DeliveryViewVO bean = new nc.vo.so.m4331.entity.DeliveryViewVO();
  context.put("nc.vo.so.m4331.entity.DeliveryViewVO#90997c",bean);
return bean;
}

public nc.ui.so.m4331.billref.QueryServiceFor4Y getQueryServiceFor4Y_14ca255(){
 if(context.get("nc.ui.so.m4331.billref.QueryServiceFor4Y#14ca255")!=null)
 return (nc.ui.so.m4331.billref.QueryServiceFor4Y)context.get("nc.ui.so.m4331.billref.QueryServiceFor4Y#14ca255");
  nc.ui.so.m4331.billref.QueryServiceFor4Y bean = new nc.ui.so.m4331.billref.QueryServiceFor4Y();
  context.put("nc.ui.so.m4331.billref.QueryServiceFor4Y#14ca255",bean);
return bean;
}

public nc.ui.so.m4331.billref.M4331RefUIInit getM4331RefUIInit_35b075(){
 if(context.get("nc.ui.so.m4331.billref.M4331RefUIInit#35b075")!=null)
 return (nc.ui.so.m4331.billref.M4331RefUIInit)context.get("nc.ui.so.m4331.billref.M4331RefUIInit#35b075");
  nc.ui.so.m4331.billref.M4331RefUIInit bean = new nc.ui.so.m4331.billref.M4331RefUIInit();
  context.put("nc.ui.so.m4331.billref.M4331RefUIInit#35b075",bean);
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

public List getManagedList1(){  List list = new ArrayList();  list.add(getUserdefQueryParam_d3e4ec());  list.add(getUserdefQueryParam_15a498e());  return list;}

public nc.ui.uif2.editor.UserdefQueryParam getUserdefQueryParam_d3e4ec(){
 if(context.get("nc.ui.uif2.editor.UserdefQueryParam#d3e4ec")!=null)
 return (nc.ui.uif2.editor.UserdefQueryParam)context.get("nc.ui.uif2.editor.UserdefQueryParam#d3e4ec");
  nc.ui.uif2.editor.UserdefQueryParam bean = new nc.ui.uif2.editor.UserdefQueryParam();
  context.put("nc.ui.uif2.editor.UserdefQueryParam#d3e4ec",bean);
  bean.setRulecode("4331_H");
  bean.setPos(0);
  bean.setPrefix("vdef");
return bean;
}

public nc.ui.uif2.editor.UserdefQueryParam getUserdefQueryParam_15a498e(){
 if(context.get("nc.ui.uif2.editor.UserdefQueryParam#15a498e")!=null)
 return (nc.ui.uif2.editor.UserdefQueryParam)context.get("nc.ui.uif2.editor.UserdefQueryParam#15a498e");
  nc.ui.uif2.editor.UserdefQueryParam bean = new nc.ui.uif2.editor.UserdefQueryParam();
  context.put("nc.ui.uif2.editor.UserdefQueryParam#15a498e",bean);
  bean.setRulecode("4331_B");
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

public List getManagedList2(){  List list = new ArrayList();  list.add(getUserdefQueryParam_9347e0());  list.add(getUserdefQueryParam_153ceb4());  return list;}

public nc.ui.uif2.editor.UserdefQueryParam getUserdefQueryParam_9347e0(){
 if(context.get("nc.ui.uif2.editor.UserdefQueryParam#9347e0")!=null)
 return (nc.ui.uif2.editor.UserdefQueryParam)context.get("nc.ui.uif2.editor.UserdefQueryParam#9347e0");
  nc.ui.uif2.editor.UserdefQueryParam bean = new nc.ui.uif2.editor.UserdefQueryParam();
  context.put("nc.ui.uif2.editor.UserdefQueryParam#9347e0",bean);
  bean.setRulecode("4331_H");
  bean.setPos(0);
  bean.setPrefix("vdef");
return bean;
}

public nc.ui.uif2.editor.UserdefQueryParam getUserdefQueryParam_153ceb4(){
 if(context.get("nc.ui.uif2.editor.UserdefQueryParam#153ceb4")!=null)
 return (nc.ui.uif2.editor.UserdefQueryParam)context.get("nc.ui.uif2.editor.UserdefQueryParam#153ceb4");
  nc.ui.uif2.editor.UserdefQueryParam bean = new nc.ui.uif2.editor.UserdefQueryParam();
  context.put("nc.ui.uif2.editor.UserdefQueryParam#153ceb4",bean);
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

public List getManagedList4(){  List list = new ArrayList();  list.add(getQueryParam_1279b7d());  list.add(getQueryParam_15147d5());  list.add(getQueryParam_142f5ba());  return list;}

public nc.ui.uif2.userdefitem.QueryParam getQueryParam_1279b7d(){
 if(context.get("nc.ui.uif2.userdefitem.QueryParam#1279b7d")!=null)
 return (nc.ui.uif2.userdefitem.QueryParam)context.get("nc.ui.uif2.userdefitem.QueryParam#1279b7d");
  nc.ui.uif2.userdefitem.QueryParam bean = new nc.ui.uif2.userdefitem.QueryParam();
  context.put("nc.ui.uif2.userdefitem.QueryParam#1279b7d",bean);
  bean.setRulecode("4331_H");
return bean;
}

public nc.ui.uif2.userdefitem.QueryParam getQueryParam_15147d5(){
 if(context.get("nc.ui.uif2.userdefitem.QueryParam#15147d5")!=null)
 return (nc.ui.uif2.userdefitem.QueryParam)context.get("nc.ui.uif2.userdefitem.QueryParam#15147d5");
  nc.ui.uif2.userdefitem.QueryParam bean = new nc.ui.uif2.userdefitem.QueryParam();
  context.put("nc.ui.uif2.userdefitem.QueryParam#15147d5",bean);
  bean.setRulecode("4331_B");
return bean;
}

public nc.ui.uif2.userdefitem.QueryParam getQueryParam_142f5ba(){
 if(context.get("nc.ui.uif2.userdefitem.QueryParam#142f5ba")!=null)
 return (nc.ui.uif2.userdefitem.QueryParam)context.get("nc.ui.uif2.userdefitem.QueryParam#142f5ba");
  nc.ui.uif2.userdefitem.QueryParam bean = new nc.ui.uif2.userdefitem.QueryParam();
  context.put("nc.ui.uif2.userdefitem.QueryParam#142f5ba",bean);
  bean.setRulecode("materialassistant");
return bean;
}

}
