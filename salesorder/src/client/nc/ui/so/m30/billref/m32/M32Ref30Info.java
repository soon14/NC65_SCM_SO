package nc.ui.so.m30.billref.m32;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.ui.uif2.factory.AbstractJavaBeanDefinition;

public class M32Ref30Info extends AbstractJavaBeanDefinition{
	private Map<String, Object> context = new HashMap();
public nc.ui.so.m30.billref.m32.QueryActionFor32 getQueryAction(){
 if(context.get("queryAction")!=null)
 return (nc.ui.so.m30.billref.m32.QueryActionFor32)context.get("queryAction");
  nc.ui.so.m30.billref.m32.QueryActionFor32 bean = new nc.ui.so.m30.billref.m32.QueryActionFor32();
  context.put("queryAction",bean);
  bean.setRefContext((nc.ui.pubapp.billref.src.RefContext)findBeanInUIF2BeanFactory("refContext"));
return bean;
}

public nc.ui.so.m30.billref.m32.RefreshActionFor32 getRefreshAction(){
 if(context.get("refreshAction")!=null)
 return (nc.ui.so.m30.billref.m32.RefreshActionFor32)context.get("refreshAction");
  nc.ui.so.m30.billref.m32.RefreshActionFor32 bean = new nc.ui.so.m30.billref.m32.RefreshActionFor32();
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
  bean.setHeadVO(getSaleOrderHVO_19d6167());
  bean.setBodyVO(getSaleOrderBVO_165e863());
  bean.setBillVO(getSaleOrderVO_17bd0d0());
  bean.setViewVO(getSaleOrderViewVO_1ca39b0());
  bean.setBillNodeKey("30to32");
  bean.setBillViewNodeKey("30to32_L");
  bean.setSourcevoPkName("csrcid");
  bean.setSourcevoBillNOName("vsrccode");
  bean.setQueryService(getQueryServiceFor32_19155ce());
  bean.setRefPanelInit(getM30RefUIInit_987f94());
  bean.setDefaultFilterItem(getManagedList0());
return bean;
}

public nc.vo.so.m30.entity.SaleOrderHVO getSaleOrderHVO_19d6167(){
 if(context.get("nc.vo.so.m30.entity.SaleOrderHVO#19d6167")!=null)
 return (nc.vo.so.m30.entity.SaleOrderHVO)context.get("nc.vo.so.m30.entity.SaleOrderHVO#19d6167");
  nc.vo.so.m30.entity.SaleOrderHVO bean = new nc.vo.so.m30.entity.SaleOrderHVO();
  context.put("nc.vo.so.m30.entity.SaleOrderHVO#19d6167",bean);
return bean;
}

public nc.vo.so.m30.entity.SaleOrderBVO getSaleOrderBVO_165e863(){
 if(context.get("nc.vo.so.m30.entity.SaleOrderBVO#165e863")!=null)
 return (nc.vo.so.m30.entity.SaleOrderBVO)context.get("nc.vo.so.m30.entity.SaleOrderBVO#165e863");
  nc.vo.so.m30.entity.SaleOrderBVO bean = new nc.vo.so.m30.entity.SaleOrderBVO();
  context.put("nc.vo.so.m30.entity.SaleOrderBVO#165e863",bean);
return bean;
}

public nc.vo.so.m30.entity.SaleOrderVO getSaleOrderVO_17bd0d0(){
 if(context.get("nc.vo.so.m30.entity.SaleOrderVO#17bd0d0")!=null)
 return (nc.vo.so.m30.entity.SaleOrderVO)context.get("nc.vo.so.m30.entity.SaleOrderVO#17bd0d0");
  nc.vo.so.m30.entity.SaleOrderVO bean = new nc.vo.so.m30.entity.SaleOrderVO();
  context.put("nc.vo.so.m30.entity.SaleOrderVO#17bd0d0",bean);
return bean;
}

public nc.vo.so.m30.entity.SaleOrderViewVO getSaleOrderViewVO_1ca39b0(){
 if(context.get("nc.vo.so.m30.entity.SaleOrderViewVO#1ca39b0")!=null)
 return (nc.vo.so.m30.entity.SaleOrderViewVO)context.get("nc.vo.so.m30.entity.SaleOrderViewVO#1ca39b0");
  nc.vo.so.m30.entity.SaleOrderViewVO bean = new nc.vo.so.m30.entity.SaleOrderViewVO();
  context.put("nc.vo.so.m30.entity.SaleOrderViewVO#1ca39b0",bean);
return bean;
}

public nc.ui.so.m30.billui.model.QueryServiceFor32 getQueryServiceFor32_19155ce(){
 if(context.get("nc.ui.so.m30.billui.model.QueryServiceFor32#19155ce")!=null)
 return (nc.ui.so.m30.billui.model.QueryServiceFor32)context.get("nc.ui.so.m30.billui.model.QueryServiceFor32#19155ce");
  nc.ui.so.m30.billui.model.QueryServiceFor32 bean = new nc.ui.so.m30.billui.model.QueryServiceFor32();
  context.put("nc.ui.so.m30.billui.model.QueryServiceFor32#19155ce",bean);
return bean;
}

public nc.ui.so.m30.billref.M30RefUIInit getM30RefUIInit_987f94(){
 if(context.get("nc.ui.so.m30.billref.M30RefUIInit#987f94")!=null)
 return (nc.ui.so.m30.billref.M30RefUIInit)context.get("nc.ui.so.m30.billref.M30RefUIInit#987f94");
  nc.ui.so.m30.billref.M30RefUIInit bean = new nc.ui.so.m30.billref.M30RefUIInit();
  context.put("nc.ui.so.m30.billref.M30RefUIInit#987f94",bean);
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

public List getManagedList3(){  List list = new ArrayList();  list.add(getUserdefQueryParam_1dfff17());  list.add(getUserdefQueryParam_e45db6());  return list;}

public nc.ui.uif2.editor.UserdefQueryParam getUserdefQueryParam_1dfff17(){
 if(context.get("nc.ui.uif2.editor.UserdefQueryParam#1dfff17")!=null)
 return (nc.ui.uif2.editor.UserdefQueryParam)context.get("nc.ui.uif2.editor.UserdefQueryParam#1dfff17");
  nc.ui.uif2.editor.UserdefQueryParam bean = new nc.ui.uif2.editor.UserdefQueryParam();
  context.put("nc.ui.uif2.editor.UserdefQueryParam#1dfff17",bean);
  bean.setRulecode("30_H");
  bean.setPos(0);
  bean.setPrefix("vdef");
return bean;
}

public nc.ui.uif2.editor.UserdefQueryParam getUserdefQueryParam_e45db6(){
 if(context.get("nc.ui.uif2.editor.UserdefQueryParam#e45db6")!=null)
 return (nc.ui.uif2.editor.UserdefQueryParam)context.get("nc.ui.uif2.editor.UserdefQueryParam#e45db6");
  nc.ui.uif2.editor.UserdefQueryParam bean = new nc.ui.uif2.editor.UserdefQueryParam();
  context.put("nc.ui.uif2.editor.UserdefQueryParam#e45db6",bean);
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

public List getManagedList4(){  List list = new ArrayList();  list.add(getUserdefQueryParam_c4909a());  list.add(getUserdefQueryParam_f198e9());  return list;}

public nc.ui.uif2.editor.UserdefQueryParam getUserdefQueryParam_c4909a(){
 if(context.get("nc.ui.uif2.editor.UserdefQueryParam#c4909a")!=null)
 return (nc.ui.uif2.editor.UserdefQueryParam)context.get("nc.ui.uif2.editor.UserdefQueryParam#c4909a");
  nc.ui.uif2.editor.UserdefQueryParam bean = new nc.ui.uif2.editor.UserdefQueryParam();
  context.put("nc.ui.uif2.editor.UserdefQueryParam#c4909a",bean);
  bean.setRulecode("30_H");
  bean.setPos(0);
  bean.setPrefix("vdef");
return bean;
}

public nc.ui.uif2.editor.UserdefQueryParam getUserdefQueryParam_f198e9(){
 if(context.get("nc.ui.uif2.editor.UserdefQueryParam#f198e9")!=null)
 return (nc.ui.uif2.editor.UserdefQueryParam)context.get("nc.ui.uif2.editor.UserdefQueryParam#f198e9");
  nc.ui.uif2.editor.UserdefQueryParam bean = new nc.ui.uif2.editor.UserdefQueryParam();
  context.put("nc.ui.uif2.editor.UserdefQueryParam#f198e9",bean);
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

public List getManagedList5(){  List list = new ArrayList();  list.add(getQueryParam_16e1dcb());  list.add(getQueryParam_663bc9());  list.add(getQueryParam_1df149c());  return list;}

public nc.ui.uif2.userdefitem.QueryParam getQueryParam_16e1dcb(){
 if(context.get("nc.ui.uif2.userdefitem.QueryParam#16e1dcb")!=null)
 return (nc.ui.uif2.userdefitem.QueryParam)context.get("nc.ui.uif2.userdefitem.QueryParam#16e1dcb");
  nc.ui.uif2.userdefitem.QueryParam bean = new nc.ui.uif2.userdefitem.QueryParam();
  context.put("nc.ui.uif2.userdefitem.QueryParam#16e1dcb",bean);
  bean.setRulecode("30_H");
return bean;
}

public nc.ui.uif2.userdefitem.QueryParam getQueryParam_663bc9(){
 if(context.get("nc.ui.uif2.userdefitem.QueryParam#663bc9")!=null)
 return (nc.ui.uif2.userdefitem.QueryParam)context.get("nc.ui.uif2.userdefitem.QueryParam#663bc9");
  nc.ui.uif2.userdefitem.QueryParam bean = new nc.ui.uif2.userdefitem.QueryParam();
  context.put("nc.ui.uif2.userdefitem.QueryParam#663bc9",bean);
  bean.setRulecode("30_B");
return bean;
}

public nc.ui.uif2.userdefitem.QueryParam getQueryParam_1df149c(){
 if(context.get("nc.ui.uif2.userdefitem.QueryParam#1df149c")!=null)
 return (nc.ui.uif2.userdefitem.QueryParam)context.get("nc.ui.uif2.userdefitem.QueryParam#1df149c");
  nc.ui.uif2.userdefitem.QueryParam bean = new nc.ui.uif2.userdefitem.QueryParam();
  context.put("nc.ui.uif2.userdefitem.QueryParam#1df149c",bean);
  bean.setRulecode("materialassistant");
return bean;
}

}
