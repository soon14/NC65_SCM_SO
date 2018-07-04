package nc.ui.so.m30.billref.m4c;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.ui.uif2.factory.AbstractJavaBeanDefinition;

public class M4CRef30Info extends AbstractJavaBeanDefinition{
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
  bean.setHeadVO(getSaleOrderHVO_b62662());
  bean.setBodyVO(getSaleOrderBVO_91f065());
  bean.setBillVO(getSaleOrderVO_13daf99());
  bean.setViewVO(getSaleOrderViewVO_d37e09());
  bean.setBillNodeKey("30to4C");
  bean.setBillViewNodeKey("30to4C_L");
  bean.setSourcevoPkName("csrcid");
  bean.setSourcevoBillNOName("vsrccode");
  bean.setQueryService(getQueryServiceFor4C_f7bfde());
  bean.setRefPanelInit(getM30RefUIInit_155b260());
  bean.setDefaultFilterItem(getManagedList0());
return bean;
}

public nc.vo.so.m30.entity.SaleOrderHVO getSaleOrderHVO_b62662(){
 if(context.get("nc.vo.so.m30.entity.SaleOrderHVO#b62662")!=null)
 return (nc.vo.so.m30.entity.SaleOrderHVO)context.get("nc.vo.so.m30.entity.SaleOrderHVO#b62662");
  nc.vo.so.m30.entity.SaleOrderHVO bean = new nc.vo.so.m30.entity.SaleOrderHVO();
  context.put("nc.vo.so.m30.entity.SaleOrderHVO#b62662",bean);
return bean;
}

public nc.vo.so.m30.entity.SaleOrderBVO getSaleOrderBVO_91f065(){
 if(context.get("nc.vo.so.m30.entity.SaleOrderBVO#91f065")!=null)
 return (nc.vo.so.m30.entity.SaleOrderBVO)context.get("nc.vo.so.m30.entity.SaleOrderBVO#91f065");
  nc.vo.so.m30.entity.SaleOrderBVO bean = new nc.vo.so.m30.entity.SaleOrderBVO();
  context.put("nc.vo.so.m30.entity.SaleOrderBVO#91f065",bean);
return bean;
}

public nc.vo.so.m30.entity.SaleOrderVO getSaleOrderVO_13daf99(){
 if(context.get("nc.vo.so.m30.entity.SaleOrderVO#13daf99")!=null)
 return (nc.vo.so.m30.entity.SaleOrderVO)context.get("nc.vo.so.m30.entity.SaleOrderVO#13daf99");
  nc.vo.so.m30.entity.SaleOrderVO bean = new nc.vo.so.m30.entity.SaleOrderVO();
  context.put("nc.vo.so.m30.entity.SaleOrderVO#13daf99",bean);
return bean;
}

public nc.vo.so.m30.entity.SaleOrderViewVO getSaleOrderViewVO_d37e09(){
 if(context.get("nc.vo.so.m30.entity.SaleOrderViewVO#d37e09")!=null)
 return (nc.vo.so.m30.entity.SaleOrderViewVO)context.get("nc.vo.so.m30.entity.SaleOrderViewVO#d37e09");
  nc.vo.so.m30.entity.SaleOrderViewVO bean = new nc.vo.so.m30.entity.SaleOrderViewVO();
  context.put("nc.vo.so.m30.entity.SaleOrderViewVO#d37e09",bean);
return bean;
}

public nc.ui.so.m30.billui.model.QueryServiceFor4C getQueryServiceFor4C_f7bfde(){
 if(context.get("nc.ui.so.m30.billui.model.QueryServiceFor4C#f7bfde")!=null)
 return (nc.ui.so.m30.billui.model.QueryServiceFor4C)context.get("nc.ui.so.m30.billui.model.QueryServiceFor4C#f7bfde");
  nc.ui.so.m30.billui.model.QueryServiceFor4C bean = new nc.ui.so.m30.billui.model.QueryServiceFor4C();
  context.put("nc.ui.so.m30.billui.model.QueryServiceFor4C#f7bfde",bean);
return bean;
}

public nc.ui.so.m30.billref.M30RefUIInit getM30RefUIInit_155b260(){
 if(context.get("nc.ui.so.m30.billref.M30RefUIInit#155b260")!=null)
 return (nc.ui.so.m30.billref.M30RefUIInit)context.get("nc.ui.so.m30.billref.M30RefUIInit#155b260");
  nc.ui.so.m30.billref.M30RefUIInit bean = new nc.ui.so.m30.billref.M30RefUIInit();
  context.put("nc.ui.so.m30.billref.M30RefUIInit#155b260",bean);
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

public List getManagedList3(){  List list = new ArrayList();  list.add(getUserdefQueryParam_131c063());  list.add(getUserdefQueryParam_14da952());  return list;}

public nc.ui.uif2.editor.UserdefQueryParam getUserdefQueryParam_131c063(){
 if(context.get("nc.ui.uif2.editor.UserdefQueryParam#131c063")!=null)
 return (nc.ui.uif2.editor.UserdefQueryParam)context.get("nc.ui.uif2.editor.UserdefQueryParam#131c063");
  nc.ui.uif2.editor.UserdefQueryParam bean = new nc.ui.uif2.editor.UserdefQueryParam();
  context.put("nc.ui.uif2.editor.UserdefQueryParam#131c063",bean);
  bean.setRulecode("30_H");
  bean.setPos(0);
  bean.setPrefix("vdef");
return bean;
}

public nc.ui.uif2.editor.UserdefQueryParam getUserdefQueryParam_14da952(){
 if(context.get("nc.ui.uif2.editor.UserdefQueryParam#14da952")!=null)
 return (nc.ui.uif2.editor.UserdefQueryParam)context.get("nc.ui.uif2.editor.UserdefQueryParam#14da952");
  nc.ui.uif2.editor.UserdefQueryParam bean = new nc.ui.uif2.editor.UserdefQueryParam();
  context.put("nc.ui.uif2.editor.UserdefQueryParam#14da952",bean);
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

public List getManagedList4(){  List list = new ArrayList();  list.add(getUserdefQueryParam_1a77f94());  list.add(getUserdefQueryParam_1a8f874());  return list;}

public nc.ui.uif2.editor.UserdefQueryParam getUserdefQueryParam_1a77f94(){
 if(context.get("nc.ui.uif2.editor.UserdefQueryParam#1a77f94")!=null)
 return (nc.ui.uif2.editor.UserdefQueryParam)context.get("nc.ui.uif2.editor.UserdefQueryParam#1a77f94");
  nc.ui.uif2.editor.UserdefQueryParam bean = new nc.ui.uif2.editor.UserdefQueryParam();
  context.put("nc.ui.uif2.editor.UserdefQueryParam#1a77f94",bean);
  bean.setRulecode("30_H");
  bean.setPos(0);
  bean.setPrefix("vdef");
return bean;
}

public nc.ui.uif2.editor.UserdefQueryParam getUserdefQueryParam_1a8f874(){
 if(context.get("nc.ui.uif2.editor.UserdefQueryParam#1a8f874")!=null)
 return (nc.ui.uif2.editor.UserdefQueryParam)context.get("nc.ui.uif2.editor.UserdefQueryParam#1a8f874");
  nc.ui.uif2.editor.UserdefQueryParam bean = new nc.ui.uif2.editor.UserdefQueryParam();
  context.put("nc.ui.uif2.editor.UserdefQueryParam#1a8f874",bean);
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

public List getManagedList5(){  List list = new ArrayList();  list.add(getQueryParam_4f94d5());  list.add(getQueryParam_b43e92());  list.add(getQueryParam_17816e4());  return list;}

public nc.ui.uif2.userdefitem.QueryParam getQueryParam_4f94d5(){
 if(context.get("nc.ui.uif2.userdefitem.QueryParam#4f94d5")!=null)
 return (nc.ui.uif2.userdefitem.QueryParam)context.get("nc.ui.uif2.userdefitem.QueryParam#4f94d5");
  nc.ui.uif2.userdefitem.QueryParam bean = new nc.ui.uif2.userdefitem.QueryParam();
  context.put("nc.ui.uif2.userdefitem.QueryParam#4f94d5",bean);
  bean.setRulecode("30_H");
return bean;
}

public nc.ui.uif2.userdefitem.QueryParam getQueryParam_b43e92(){
 if(context.get("nc.ui.uif2.userdefitem.QueryParam#b43e92")!=null)
 return (nc.ui.uif2.userdefitem.QueryParam)context.get("nc.ui.uif2.userdefitem.QueryParam#b43e92");
  nc.ui.uif2.userdefitem.QueryParam bean = new nc.ui.uif2.userdefitem.QueryParam();
  context.put("nc.ui.uif2.userdefitem.QueryParam#b43e92",bean);
  bean.setRulecode("30_B");
return bean;
}

public nc.ui.uif2.userdefitem.QueryParam getQueryParam_17816e4(){
 if(context.get("nc.ui.uif2.userdefitem.QueryParam#17816e4")!=null)
 return (nc.ui.uif2.userdefitem.QueryParam)context.get("nc.ui.uif2.userdefitem.QueryParam#17816e4");
  nc.ui.uif2.userdefitem.QueryParam bean = new nc.ui.uif2.userdefitem.QueryParam();
  context.put("nc.ui.uif2.userdefitem.QueryParam#17816e4",bean);
  bean.setRulecode("materialassistant");
return bean;
}

}
