package nc.ui.so.m30.billref.m4816;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.ui.uif2.factory.AbstractJavaBeanDefinition;

public class M4816Ref30Info extends AbstractJavaBeanDefinition{
	private Map<String, Object> context = new HashMap();
public nc.ui.so.m30.billref.m4816.QueryActionFor4816 getQueryAction(){
 if(context.get("queryAction")!=null)
 return (nc.ui.so.m30.billref.m4816.QueryActionFor4816)context.get("queryAction");
  nc.ui.so.m30.billref.m4816.QueryActionFor4816 bean = new nc.ui.so.m30.billref.m4816.QueryActionFor4816();
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
  bean.setHeadVO(getSaleOrderHVO_b1c09c());
  bean.setBodyVO(getSaleOrderBVO_1dc61ec());
  bean.setBillVO(getSaleOrderVO_1a0d0fc());
  bean.setViewVO(getSaleOrderViewVO_f85b7b());
  bean.setBillNodeKey("30to4816");
  bean.setBillViewNodeKey("30to4816_L");
  bean.setSourcevoPkName("csrcid");
  bean.setSourcevoBillNOName("vsrccode");
  bean.setQueryService(getQueryServiceFor4816_13e222e());
  bean.setRefPanelInit(getM30RefUIInit_361b78());
return bean;
}

public nc.vo.so.m30.entity.SaleOrderHVO getSaleOrderHVO_b1c09c(){
 if(context.get("nc.vo.so.m30.entity.SaleOrderHVO#b1c09c")!=null)
 return (nc.vo.so.m30.entity.SaleOrderHVO)context.get("nc.vo.so.m30.entity.SaleOrderHVO#b1c09c");
  nc.vo.so.m30.entity.SaleOrderHVO bean = new nc.vo.so.m30.entity.SaleOrderHVO();
  context.put("nc.vo.so.m30.entity.SaleOrderHVO#b1c09c",bean);
return bean;
}

public nc.vo.so.m30.entity.SaleOrderBVO getSaleOrderBVO_1dc61ec(){
 if(context.get("nc.vo.so.m30.entity.SaleOrderBVO#1dc61ec")!=null)
 return (nc.vo.so.m30.entity.SaleOrderBVO)context.get("nc.vo.so.m30.entity.SaleOrderBVO#1dc61ec");
  nc.vo.so.m30.entity.SaleOrderBVO bean = new nc.vo.so.m30.entity.SaleOrderBVO();
  context.put("nc.vo.so.m30.entity.SaleOrderBVO#1dc61ec",bean);
return bean;
}

public nc.vo.so.m30.entity.SaleOrderVO getSaleOrderVO_1a0d0fc(){
 if(context.get("nc.vo.so.m30.entity.SaleOrderVO#1a0d0fc")!=null)
 return (nc.vo.so.m30.entity.SaleOrderVO)context.get("nc.vo.so.m30.entity.SaleOrderVO#1a0d0fc");
  nc.vo.so.m30.entity.SaleOrderVO bean = new nc.vo.so.m30.entity.SaleOrderVO();
  context.put("nc.vo.so.m30.entity.SaleOrderVO#1a0d0fc",bean);
return bean;
}

public nc.vo.so.m30.entity.SaleOrderViewVO getSaleOrderViewVO_f85b7b(){
 if(context.get("nc.vo.so.m30.entity.SaleOrderViewVO#f85b7b")!=null)
 return (nc.vo.so.m30.entity.SaleOrderViewVO)context.get("nc.vo.so.m30.entity.SaleOrderViewVO#f85b7b");
  nc.vo.so.m30.entity.SaleOrderViewVO bean = new nc.vo.so.m30.entity.SaleOrderViewVO();
  context.put("nc.vo.so.m30.entity.SaleOrderViewVO#f85b7b",bean);
return bean;
}

public nc.ui.so.m30.billui.model.QueryServiceFor4816 getQueryServiceFor4816_13e222e(){
 if(context.get("nc.ui.so.m30.billui.model.QueryServiceFor4816#13e222e")!=null)
 return (nc.ui.so.m30.billui.model.QueryServiceFor4816)context.get("nc.ui.so.m30.billui.model.QueryServiceFor4816#13e222e");
  nc.ui.so.m30.billui.model.QueryServiceFor4816 bean = new nc.ui.so.m30.billui.model.QueryServiceFor4816();
  context.put("nc.ui.so.m30.billui.model.QueryServiceFor4816#13e222e",bean);
return bean;
}

public nc.ui.so.m30.billref.M30RefUIInit getM30RefUIInit_361b78(){
 if(context.get("nc.ui.so.m30.billref.M30RefUIInit#361b78")!=null)
 return (nc.ui.so.m30.billref.M30RefUIInit)context.get("nc.ui.so.m30.billref.M30RefUIInit#361b78");
  nc.ui.so.m30.billref.M30RefUIInit bean = new nc.ui.so.m30.billref.M30RefUIInit();
  context.put("nc.ui.so.m30.billref.M30RefUIInit#361b78",bean);
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

public nc.ui.pubapp.uif2app.view.CompositeBillListDataPrepare getSingleUserdefitemListPreparator(){
 if(context.get("singleUserdefitemListPreparator")!=null)
 return (nc.ui.pubapp.uif2app.view.CompositeBillListDataPrepare)context.get("singleUserdefitemListPreparator");
  nc.ui.pubapp.uif2app.view.CompositeBillListDataPrepare bean = new nc.ui.pubapp.uif2app.view.CompositeBillListDataPrepare();
  context.put("singleUserdefitemListPreparator",bean);
  bean.setBillListDataPrepares(getManagedList1());
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
return bean;
}

public List getManagedList2(){  List list = new ArrayList();  list.add(getUserdefQueryParam_458f7f());  list.add(getUserdefQueryParam_d0198f());  return list;}

public nc.ui.uif2.editor.UserdefQueryParam getUserdefQueryParam_458f7f(){
 if(context.get("nc.ui.uif2.editor.UserdefQueryParam#458f7f")!=null)
 return (nc.ui.uif2.editor.UserdefQueryParam)context.get("nc.ui.uif2.editor.UserdefQueryParam#458f7f");
  nc.ui.uif2.editor.UserdefQueryParam bean = new nc.ui.uif2.editor.UserdefQueryParam();
  context.put("nc.ui.uif2.editor.UserdefQueryParam#458f7f",bean);
  bean.setRulecode("30_H");
  bean.setPos(0);
  bean.setPrefix("vdef");
return bean;
}

public nc.ui.uif2.editor.UserdefQueryParam getUserdefQueryParam_d0198f(){
 if(context.get("nc.ui.uif2.editor.UserdefQueryParam#d0198f")!=null)
 return (nc.ui.uif2.editor.UserdefQueryParam)context.get("nc.ui.uif2.editor.UserdefQueryParam#d0198f");
  nc.ui.uif2.editor.UserdefQueryParam bean = new nc.ui.uif2.editor.UserdefQueryParam();
  context.put("nc.ui.uif2.editor.UserdefQueryParam#d0198f",bean);
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
  bean.setParams(getManagedList3());
return bean;
}

public List getManagedList3(){  List list = new ArrayList();  list.add(getUserdefQueryParam_3e3318());  list.add(getUserdefQueryParam_18c96d6());  return list;}

public nc.ui.uif2.editor.UserdefQueryParam getUserdefQueryParam_3e3318(){
 if(context.get("nc.ui.uif2.editor.UserdefQueryParam#3e3318")!=null)
 return (nc.ui.uif2.editor.UserdefQueryParam)context.get("nc.ui.uif2.editor.UserdefQueryParam#3e3318");
  nc.ui.uif2.editor.UserdefQueryParam bean = new nc.ui.uif2.editor.UserdefQueryParam();
  context.put("nc.ui.uif2.editor.UserdefQueryParam#3e3318",bean);
  bean.setRulecode("30_H");
  bean.setPos(0);
  bean.setPrefix("vdef");
return bean;
}

public nc.ui.uif2.editor.UserdefQueryParam getUserdefQueryParam_18c96d6(){
 if(context.get("nc.ui.uif2.editor.UserdefQueryParam#18c96d6")!=null)
 return (nc.ui.uif2.editor.UserdefQueryParam)context.get("nc.ui.uif2.editor.UserdefQueryParam#18c96d6");
  nc.ui.uif2.editor.UserdefQueryParam bean = new nc.ui.uif2.editor.UserdefQueryParam();
  context.put("nc.ui.uif2.editor.UserdefQueryParam#18c96d6",bean);
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
  bean.setParams(getManagedList4());
return bean;
}

public List getManagedList4(){  List list = new ArrayList();  list.add(getQueryParam_102f5d7());  list.add(getQueryParam_15a19a6());  list.add(getQueryParam_4a04dc());  return list;}

public nc.ui.uif2.userdefitem.QueryParam getQueryParam_102f5d7(){
 if(context.get("nc.ui.uif2.userdefitem.QueryParam#102f5d7")!=null)
 return (nc.ui.uif2.userdefitem.QueryParam)context.get("nc.ui.uif2.userdefitem.QueryParam#102f5d7");
  nc.ui.uif2.userdefitem.QueryParam bean = new nc.ui.uif2.userdefitem.QueryParam();
  context.put("nc.ui.uif2.userdefitem.QueryParam#102f5d7",bean);
  bean.setRulecode("30_H");
return bean;
}

public nc.ui.uif2.userdefitem.QueryParam getQueryParam_15a19a6(){
 if(context.get("nc.ui.uif2.userdefitem.QueryParam#15a19a6")!=null)
 return (nc.ui.uif2.userdefitem.QueryParam)context.get("nc.ui.uif2.userdefitem.QueryParam#15a19a6");
  nc.ui.uif2.userdefitem.QueryParam bean = new nc.ui.uif2.userdefitem.QueryParam();
  context.put("nc.ui.uif2.userdefitem.QueryParam#15a19a6",bean);
  bean.setRulecode("30_B");
return bean;
}

public nc.ui.uif2.userdefitem.QueryParam getQueryParam_4a04dc(){
 if(context.get("nc.ui.uif2.userdefitem.QueryParam#4a04dc")!=null)
 return (nc.ui.uif2.userdefitem.QueryParam)context.get("nc.ui.uif2.userdefitem.QueryParam#4a04dc");
  nc.ui.uif2.userdefitem.QueryParam bean = new nc.ui.uif2.userdefitem.QueryParam();
  context.put("nc.ui.uif2.userdefitem.QueryParam#4a04dc",bean);
  bean.setRulecode("materialassistant");
return bean;
}

}
