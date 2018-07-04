package nc.ui.so.m30.billref.m21.direct;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.ui.uif2.factory.AbstractJavaBeanDefinition;

public class M21RefDirect30Info extends AbstractJavaBeanDefinition{
	private Map<String, Object> context = new HashMap();
public nc.ui.so.m30.billref.M30RefQueryAction getQueryAction(){
 if(context.get("queryAction")!=null)
 return (nc.ui.so.m30.billref.M30RefQueryAction)context.get("queryAction");
  nc.ui.so.m30.billref.M30RefQueryAction bean = new nc.ui.so.m30.billref.M30RefQueryAction();
  context.put("queryAction",bean);
  bean.setRefContext((nc.ui.pubapp.billref.src.RefContext)findBeanInUIF2BeanFactory("refContext"));
return bean;
}

public nc.ui.so.m30.billref.M30RefRefreshAction getRefreshAction(){
 if(context.get("refreshAction")!=null)
 return (nc.ui.so.m30.billref.M30RefRefreshAction)context.get("refreshAction");
  nc.ui.so.m30.billref.M30RefRefreshAction bean = new nc.ui.so.m30.billref.M30RefRefreshAction();
  context.put("refreshAction",bean);
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
  bean.setHeadVO(getSaleOrderHVO_1ce0ec2());
  bean.setBodyVO(getSaleOrderBVO_12b11a4());
  bean.setBillVO(getSaleOrderVO_934141());
  bean.setViewVO(getSaleOrderViewVO_1a12215());
  bean.setBillNodeKey("30to21");
  bean.setBillViewNodeKey("30to21_L");
  bean.setSourcevoPkName("csrcid");
  bean.setSourcevoBillNOName("vsrccode");
  bean.setQueryService(getQueryServiceFor21_1c4bc9e());
  bean.setRefPanelInit(getM30RefUIInit_13b4cda());
  bean.setDefaultFilterItem(getManagedList0());
return bean;
}

public nc.vo.so.m30.entity.SaleOrderHVO getSaleOrderHVO_1ce0ec2(){
 if(context.get("nc.vo.so.m30.entity.SaleOrderHVO#1ce0ec2")!=null)
 return (nc.vo.so.m30.entity.SaleOrderHVO)context.get("nc.vo.so.m30.entity.SaleOrderHVO#1ce0ec2");
  nc.vo.so.m30.entity.SaleOrderHVO bean = new nc.vo.so.m30.entity.SaleOrderHVO();
  context.put("nc.vo.so.m30.entity.SaleOrderHVO#1ce0ec2",bean);
return bean;
}

public nc.vo.so.m30.entity.SaleOrderBVO getSaleOrderBVO_12b11a4(){
 if(context.get("nc.vo.so.m30.entity.SaleOrderBVO#12b11a4")!=null)
 return (nc.vo.so.m30.entity.SaleOrderBVO)context.get("nc.vo.so.m30.entity.SaleOrderBVO#12b11a4");
  nc.vo.so.m30.entity.SaleOrderBVO bean = new nc.vo.so.m30.entity.SaleOrderBVO();
  context.put("nc.vo.so.m30.entity.SaleOrderBVO#12b11a4",bean);
return bean;
}

public nc.vo.so.m30.entity.SaleOrderVO getSaleOrderVO_934141(){
 if(context.get("nc.vo.so.m30.entity.SaleOrderVO#934141")!=null)
 return (nc.vo.so.m30.entity.SaleOrderVO)context.get("nc.vo.so.m30.entity.SaleOrderVO#934141");
  nc.vo.so.m30.entity.SaleOrderVO bean = new nc.vo.so.m30.entity.SaleOrderVO();
  context.put("nc.vo.so.m30.entity.SaleOrderVO#934141",bean);
return bean;
}

public nc.vo.so.m30.entity.SaleOrderViewVO getSaleOrderViewVO_1a12215(){
 if(context.get("nc.vo.so.m30.entity.SaleOrderViewVO#1a12215")!=null)
 return (nc.vo.so.m30.entity.SaleOrderViewVO)context.get("nc.vo.so.m30.entity.SaleOrderViewVO#1a12215");
  nc.vo.so.m30.entity.SaleOrderViewVO bean = new nc.vo.so.m30.entity.SaleOrderViewVO();
  context.put("nc.vo.so.m30.entity.SaleOrderViewVO#1a12215",bean);
return bean;
}

public nc.ui.so.m30.billui.model.QueryServiceFor21 getQueryServiceFor21_1c4bc9e(){
 if(context.get("nc.ui.so.m30.billui.model.QueryServiceFor21#1c4bc9e")!=null)
 return (nc.ui.so.m30.billui.model.QueryServiceFor21)context.get("nc.ui.so.m30.billui.model.QueryServiceFor21#1c4bc9e");
  nc.ui.so.m30.billui.model.QueryServiceFor21 bean = new nc.ui.so.m30.billui.model.QueryServiceFor21();
  context.put("nc.ui.so.m30.billui.model.QueryServiceFor21#1c4bc9e",bean);
return bean;
}

public nc.ui.so.m30.billref.M30RefUIInit getM30RefUIInit_13b4cda(){
 if(context.get("nc.ui.so.m30.billref.M30RefUIInit#13b4cda")!=null)
 return (nc.ui.so.m30.billref.M30RefUIInit)context.get("nc.ui.so.m30.billref.M30RefUIInit#13b4cda");
  nc.ui.so.m30.billref.M30RefUIInit bean = new nc.ui.so.m30.billref.M30RefUIInit();
  context.put("nc.ui.so.m30.billref.M30RefUIInit#13b4cda",bean);
return bean;
}

public List getManagedList0(){  List list = new ArrayList();  list.add("vbillcode");  list.add("dbilldate");  return list;}

public nc.ui.pubapp.uif2app.view.CompositeBillListDataPrepare getUserdefitemListPreparator(){
 if(context.get("userdefitemListPreparator")!=null)
 return (nc.ui.pubapp.uif2app.view.CompositeBillListDataPrepare)context.get("userdefitemListPreparator");
  nc.ui.pubapp.uif2app.view.CompositeBillListDataPrepare bean = new nc.ui.pubapp.uif2app.view.CompositeBillListDataPrepare();
  context.put("userdefitemListPreparator",bean);
  bean.setBillListDataPrepares(getManagedList1());
return bean;
}

public List getManagedList1(){  List list = new ArrayList();  list.add(getListPreparator());  list.add(getMarAsstPreparator());  return list;}

public nc.ui.pubapp.uif2app.view.CompositeBillListDataPrepare getSingleUserdefitemListPreparator(){
 if(context.get("singleUserdefitemListPreparator")!=null)
 return (nc.ui.pubapp.uif2app.view.CompositeBillListDataPrepare)context.get("singleUserdefitemListPreparator");
  nc.ui.pubapp.uif2app.view.CompositeBillListDataPrepare bean = new nc.ui.pubapp.uif2app.view.CompositeBillListDataPrepare();
  context.put("singleUserdefitemListPreparator",bean);
  bean.setBillListDataPrepares(getManagedList2());
return bean;
}

public List getManagedList2(){  List list = new ArrayList();  list.add(getSingleListPreparator());  list.add(getMarAsstPreparator());  return list;}

public nc.ui.uif2.editor.UserdefitemContainerListPreparator getListPreparator(){
 if(context.get("listPreparator")!=null)
 return (nc.ui.uif2.editor.UserdefitemContainerListPreparator)context.get("listPreparator");
  nc.ui.uif2.editor.UserdefitemContainerListPreparator bean = new nc.ui.uif2.editor.UserdefitemContainerListPreparator();
  context.put("listPreparator",bean);
  bean.setContainer(getUserdefitemContainer());
  bean.setParams(getManagedList3());
return bean;
}

public List getManagedList3(){  List list = new ArrayList();  list.add(getUserdefQueryParam_32b95d());  list.add(getUserdefQueryParam_27b4e1());  return list;}

public nc.ui.uif2.editor.UserdefQueryParam getUserdefQueryParam_32b95d(){
 if(context.get("nc.ui.uif2.editor.UserdefQueryParam#32b95d")!=null)
 return (nc.ui.uif2.editor.UserdefQueryParam)context.get("nc.ui.uif2.editor.UserdefQueryParam#32b95d");
  nc.ui.uif2.editor.UserdefQueryParam bean = new nc.ui.uif2.editor.UserdefQueryParam();
  context.put("nc.ui.uif2.editor.UserdefQueryParam#32b95d",bean);
  bean.setRulecode("30_H");
  bean.setPos(0);
  bean.setPrefix("vdef");
return bean;
}

public nc.ui.uif2.editor.UserdefQueryParam getUserdefQueryParam_27b4e1(){
 if(context.get("nc.ui.uif2.editor.UserdefQueryParam#27b4e1")!=null)
 return (nc.ui.uif2.editor.UserdefQueryParam)context.get("nc.ui.uif2.editor.UserdefQueryParam#27b4e1");
  nc.ui.uif2.editor.UserdefQueryParam bean = new nc.ui.uif2.editor.UserdefQueryParam();
  context.put("nc.ui.uif2.editor.UserdefQueryParam#27b4e1",bean);
  bean.setRulecode("30_B");
  bean.setPos(1);
  bean.setPrefix("vbdef");
  bean.setTabcode("bodytable1");
return bean;
}

public nc.ui.uif2.editor.UserdefitemContainerListPreparator getSingleListPreparator(){
 if(context.get("singleListPreparator")!=null)
 return (nc.ui.uif2.editor.UserdefitemContainerListPreparator)context.get("singleListPreparator");
  nc.ui.uif2.editor.UserdefitemContainerListPreparator bean = new nc.ui.uif2.editor.UserdefitemContainerListPreparator();
  context.put("singleListPreparator",bean);
  bean.setContainer(getUserdefitemContainer());
  bean.setParams(getManagedList4());
return bean;
}

public List getManagedList4(){  List list = new ArrayList();  list.add(getUserdefQueryParam_d42e37());  list.add(getUserdefQueryParam_2657c6());  return list;}

public nc.ui.uif2.editor.UserdefQueryParam getUserdefQueryParam_d42e37(){
 if(context.get("nc.ui.uif2.editor.UserdefQueryParam#d42e37")!=null)
 return (nc.ui.uif2.editor.UserdefQueryParam)context.get("nc.ui.uif2.editor.UserdefQueryParam#d42e37");
  nc.ui.uif2.editor.UserdefQueryParam bean = new nc.ui.uif2.editor.UserdefQueryParam();
  context.put("nc.ui.uif2.editor.UserdefQueryParam#d42e37",bean);
  bean.setRulecode("30_H");
  bean.setPos(0);
  bean.setPrefix("vdef");
return bean;
}

public nc.ui.uif2.editor.UserdefQueryParam getUserdefQueryParam_2657c6(){
 if(context.get("nc.ui.uif2.editor.UserdefQueryParam#2657c6")!=null)
 return (nc.ui.uif2.editor.UserdefQueryParam)context.get("nc.ui.uif2.editor.UserdefQueryParam#2657c6");
  nc.ui.uif2.editor.UserdefQueryParam bean = new nc.ui.uif2.editor.UserdefQueryParam();
  context.put("nc.ui.uif2.editor.UserdefQueryParam#2657c6",bean);
  bean.setRulecode("30_B");
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

public nc.ui.pubapp.billref.src.editor.BillRefUserDefItemContainer getUserdefitemContainer(){
 if(context.get("userdefitemContainer")!=null)
 return (nc.ui.pubapp.billref.src.editor.BillRefUserDefItemContainer)context.get("userdefitemContainer");
  nc.ui.pubapp.billref.src.editor.BillRefUserDefItemContainer bean = new nc.ui.pubapp.billref.src.editor.BillRefUserDefItemContainer();
  context.put("userdefitemContainer",bean);
  bean.setParams(getManagedList5());
return bean;
}

public List getManagedList5(){  List list = new ArrayList();  list.add(getQueryParam_14cad2b());  list.add(getQueryParam_fd09b2());  list.add(getQueryParam_87b8ea());  return list;}

public nc.ui.uif2.userdefitem.QueryParam getQueryParam_14cad2b(){
 if(context.get("nc.ui.uif2.userdefitem.QueryParam#14cad2b")!=null)
 return (nc.ui.uif2.userdefitem.QueryParam)context.get("nc.ui.uif2.userdefitem.QueryParam#14cad2b");
  nc.ui.uif2.userdefitem.QueryParam bean = new nc.ui.uif2.userdefitem.QueryParam();
  context.put("nc.ui.uif2.userdefitem.QueryParam#14cad2b",bean);
  bean.setRulecode("30_H");
return bean;
}

public nc.ui.uif2.userdefitem.QueryParam getQueryParam_fd09b2(){
 if(context.get("nc.ui.uif2.userdefitem.QueryParam#fd09b2")!=null)
 return (nc.ui.uif2.userdefitem.QueryParam)context.get("nc.ui.uif2.userdefitem.QueryParam#fd09b2");
  nc.ui.uif2.userdefitem.QueryParam bean = new nc.ui.uif2.userdefitem.QueryParam();
  context.put("nc.ui.uif2.userdefitem.QueryParam#fd09b2",bean);
  bean.setRulecode("30_B");
return bean;
}

public nc.ui.uif2.userdefitem.QueryParam getQueryParam_87b8ea(){
 if(context.get("nc.ui.uif2.userdefitem.QueryParam#87b8ea")!=null)
 return (nc.ui.uif2.userdefitem.QueryParam)context.get("nc.ui.uif2.userdefitem.QueryParam#87b8ea");
  nc.ui.uif2.userdefitem.QueryParam bean = new nc.ui.uif2.userdefitem.QueryParam();
  context.put("nc.ui.uif2.userdefitem.QueryParam#87b8ea",bean);
  bean.setRulecode("materialassistant");
return bean;
}

}
