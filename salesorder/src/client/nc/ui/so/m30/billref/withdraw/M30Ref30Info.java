package nc.ui.so.m30.billref.withdraw;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.ui.uif2.factory.AbstractJavaBeanDefinition;

public class M30Ref30Info extends AbstractJavaBeanDefinition{
	private Map<String, Object> context = new HashMap();
public nc.ui.so.m30.billref.withdraw.QueryActionForWithdraw getQueryAction(){
 if(context.get("queryAction")!=null)
 return (nc.ui.so.m30.billref.withdraw.QueryActionForWithdraw)context.get("queryAction");
  nc.ui.so.m30.billref.withdraw.QueryActionForWithdraw bean = new nc.ui.so.m30.billref.withdraw.QueryActionForWithdraw();
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
  bean.setHeadVO(getSaleOrderHVO_1508dd1());
  bean.setBodyVO(getSaleOrderBVO_18ea152());
  bean.setBillVO(getSaleOrderVO_1014bd0());
  bean.setViewVO(getSaleOrderViewVO_185fdf());
  bean.setBillNodeKey("30to30");
  bean.setBillViewNodeKey("30to30_L");
  bean.setSourcevoPkName("csrcid");
  bean.setSourcevoBillNOName("vsrccode");
  bean.setQueryService(getQueryServiceForWithdraw_3e5edd());
  bean.setRefPanelInit(getM30RefUIInit_c60b9a());
  bean.setDefaultFilterItem(getManagedList0());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.vo.so.m30.entity.SaleOrderHVO getSaleOrderHVO_1508dd1(){
 if(context.get("nc.vo.so.m30.entity.SaleOrderHVO#1508dd1")!=null)
 return (nc.vo.so.m30.entity.SaleOrderHVO)context.get("nc.vo.so.m30.entity.SaleOrderHVO#1508dd1");
  nc.vo.so.m30.entity.SaleOrderHVO bean = new nc.vo.so.m30.entity.SaleOrderHVO();
  context.put("nc.vo.so.m30.entity.SaleOrderHVO#1508dd1",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.vo.so.m30.entity.SaleOrderBVO getSaleOrderBVO_18ea152(){
 if(context.get("nc.vo.so.m30.entity.SaleOrderBVO#18ea152")!=null)
 return (nc.vo.so.m30.entity.SaleOrderBVO)context.get("nc.vo.so.m30.entity.SaleOrderBVO#18ea152");
  nc.vo.so.m30.entity.SaleOrderBVO bean = new nc.vo.so.m30.entity.SaleOrderBVO();
  context.put("nc.vo.so.m30.entity.SaleOrderBVO#18ea152",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.vo.so.m30.entity.SaleOrderVO getSaleOrderVO_1014bd0(){
 if(context.get("nc.vo.so.m30.entity.SaleOrderVO#1014bd0")!=null)
 return (nc.vo.so.m30.entity.SaleOrderVO)context.get("nc.vo.so.m30.entity.SaleOrderVO#1014bd0");
  nc.vo.so.m30.entity.SaleOrderVO bean = new nc.vo.so.m30.entity.SaleOrderVO();
  context.put("nc.vo.so.m30.entity.SaleOrderVO#1014bd0",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.vo.so.m30.entity.SaleOrderViewVO getSaleOrderViewVO_185fdf(){
 if(context.get("nc.vo.so.m30.entity.SaleOrderViewVO#185fdf")!=null)
 return (nc.vo.so.m30.entity.SaleOrderViewVO)context.get("nc.vo.so.m30.entity.SaleOrderViewVO#185fdf");
  nc.vo.so.m30.entity.SaleOrderViewVO bean = new nc.vo.so.m30.entity.SaleOrderViewVO();
  context.put("nc.vo.so.m30.entity.SaleOrderViewVO#185fdf",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.so.m30.billui.model.QueryServiceForWithdraw getQueryServiceForWithdraw_3e5edd(){
 if(context.get("nc.ui.so.m30.billui.model.QueryServiceForWithdraw#3e5edd")!=null)
 return (nc.ui.so.m30.billui.model.QueryServiceForWithdraw)context.get("nc.ui.so.m30.billui.model.QueryServiceForWithdraw#3e5edd");
  nc.ui.so.m30.billui.model.QueryServiceForWithdraw bean = new nc.ui.so.m30.billui.model.QueryServiceForWithdraw();
  context.put("nc.ui.so.m30.billui.model.QueryServiceForWithdraw#3e5edd",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.so.m30.billref.M30RefUIInit getM30RefUIInit_c60b9a(){
 if(context.get("nc.ui.so.m30.billref.M30RefUIInit#c60b9a")!=null)
 return (nc.ui.so.m30.billref.M30RefUIInit)context.get("nc.ui.so.m30.billref.M30RefUIInit#c60b9a");
  nc.ui.so.m30.billref.M30RefUIInit bean = new nc.ui.so.m30.billref.M30RefUIInit();
  context.put("nc.ui.so.m30.billref.M30RefUIInit#c60b9a",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList0(){  List list = new ArrayList();  list.add("vbillcode");  list.add("dbilldate");  return list;}

public nc.ui.pubapp.uif2app.view.CompositeBillListDataPrepare getUserdefitemListPreparator(){
 if(context.get("userdefitemListPreparator")!=null)
 return (nc.ui.pubapp.uif2app.view.CompositeBillListDataPrepare)context.get("userdefitemListPreparator");
  nc.ui.pubapp.uif2app.view.CompositeBillListDataPrepare bean = new nc.ui.pubapp.uif2app.view.CompositeBillListDataPrepare();
  context.put("userdefitemListPreparator",bean);
  bean.setBillListDataPrepares(getManagedList1());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList1(){  List list = new ArrayList();  list.add(getListPreparator());  list.add(getMarAsstPreparator());  return list;}

public nc.ui.pubapp.uif2app.view.CompositeBillListDataPrepare getSingleUserdefitemListPreparator(){
 if(context.get("singleUserdefitemListPreparator")!=null)
 return (nc.ui.pubapp.uif2app.view.CompositeBillListDataPrepare)context.get("singleUserdefitemListPreparator");
  nc.ui.pubapp.uif2app.view.CompositeBillListDataPrepare bean = new nc.ui.pubapp.uif2app.view.CompositeBillListDataPrepare();
  context.put("singleUserdefitemListPreparator",bean);
  bean.setBillListDataPrepares(getManagedList2());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList2(){  List list = new ArrayList();  list.add(getSingleListPreparator());  list.add(getMarAsstPreparator());  return list;}

public nc.ui.uif2.editor.UserdefitemContainerListPreparator getListPreparator(){
 if(context.get("listPreparator")!=null)
 return (nc.ui.uif2.editor.UserdefitemContainerListPreparator)context.get("listPreparator");
  nc.ui.uif2.editor.UserdefitemContainerListPreparator bean = new nc.ui.uif2.editor.UserdefitemContainerListPreparator();
  context.put("listPreparator",bean);
  bean.setContainer(getUserdefitemContainer());
  bean.setParams(getManagedList3());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList3(){  List list = new ArrayList();  list.add(getUserdefQueryParam_e77663());  list.add(getUserdefQueryParam_d79be());  return list;}

private nc.ui.uif2.editor.UserdefQueryParam getUserdefQueryParam_e77663(){
 if(context.get("nc.ui.uif2.editor.UserdefQueryParam#e77663")!=null)
 return (nc.ui.uif2.editor.UserdefQueryParam)context.get("nc.ui.uif2.editor.UserdefQueryParam#e77663");
  nc.ui.uif2.editor.UserdefQueryParam bean = new nc.ui.uif2.editor.UserdefQueryParam();
  context.put("nc.ui.uif2.editor.UserdefQueryParam#e77663",bean);
  bean.setRulecode("30_H");
  bean.setPos(0);
  bean.setPrefix("vdef");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.editor.UserdefQueryParam getUserdefQueryParam_d79be(){
 if(context.get("nc.ui.uif2.editor.UserdefQueryParam#d79be")!=null)
 return (nc.ui.uif2.editor.UserdefQueryParam)context.get("nc.ui.uif2.editor.UserdefQueryParam#d79be");
  nc.ui.uif2.editor.UserdefQueryParam bean = new nc.ui.uif2.editor.UserdefQueryParam();
  context.put("nc.ui.uif2.editor.UserdefQueryParam#d79be",bean);
  bean.setRulecode("30_B");
  bean.setPos(1);
  bean.setPrefix("vbdef");
  bean.setTabcode("bodytable1");
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
  bean.setParams(getManagedList4());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList4(){  List list = new ArrayList();  list.add(getUserdefQueryParam_b3b441());  list.add(getUserdefQueryParam_1ff3fc4());  return list;}

private nc.ui.uif2.editor.UserdefQueryParam getUserdefQueryParam_b3b441(){
 if(context.get("nc.ui.uif2.editor.UserdefQueryParam#b3b441")!=null)
 return (nc.ui.uif2.editor.UserdefQueryParam)context.get("nc.ui.uif2.editor.UserdefQueryParam#b3b441");
  nc.ui.uif2.editor.UserdefQueryParam bean = new nc.ui.uif2.editor.UserdefQueryParam();
  context.put("nc.ui.uif2.editor.UserdefQueryParam#b3b441",bean);
  bean.setRulecode("30_H");
  bean.setPos(0);
  bean.setPrefix("vdef");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.editor.UserdefQueryParam getUserdefQueryParam_1ff3fc4(){
 if(context.get("nc.ui.uif2.editor.UserdefQueryParam#1ff3fc4")!=null)
 return (nc.ui.uif2.editor.UserdefQueryParam)context.get("nc.ui.uif2.editor.UserdefQueryParam#1ff3fc4");
  nc.ui.uif2.editor.UserdefQueryParam bean = new nc.ui.uif2.editor.UserdefQueryParam();
  context.put("nc.ui.uif2.editor.UserdefQueryParam#1ff3fc4",bean);
  bean.setRulecode("30_B");
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
  bean.setParams(getManagedList5());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList5(){  List list = new ArrayList();  list.add(getQueryParam_b39a68());  list.add(getQueryParam_15e2d7c());  list.add(getQueryParam_1fc5def());  return list;}

private nc.ui.uif2.userdefitem.QueryParam getQueryParam_b39a68(){
 if(context.get("nc.ui.uif2.userdefitem.QueryParam#b39a68")!=null)
 return (nc.ui.uif2.userdefitem.QueryParam)context.get("nc.ui.uif2.userdefitem.QueryParam#b39a68");
  nc.ui.uif2.userdefitem.QueryParam bean = new nc.ui.uif2.userdefitem.QueryParam();
  context.put("nc.ui.uif2.userdefitem.QueryParam#b39a68",bean);
  bean.setRulecode("30_H");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.userdefitem.QueryParam getQueryParam_15e2d7c(){
 if(context.get("nc.ui.uif2.userdefitem.QueryParam#15e2d7c")!=null)
 return (nc.ui.uif2.userdefitem.QueryParam)context.get("nc.ui.uif2.userdefitem.QueryParam#15e2d7c");
  nc.ui.uif2.userdefitem.QueryParam bean = new nc.ui.uif2.userdefitem.QueryParam();
  context.put("nc.ui.uif2.userdefitem.QueryParam#15e2d7c",bean);
  bean.setRulecode("30_B");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.userdefitem.QueryParam getQueryParam_1fc5def(){
 if(context.get("nc.ui.uif2.userdefitem.QueryParam#1fc5def")!=null)
 return (nc.ui.uif2.userdefitem.QueryParam)context.get("nc.ui.uif2.userdefitem.QueryParam#1fc5def");
  nc.ui.uif2.userdefitem.QueryParam bean = new nc.ui.uif2.userdefitem.QueryParam();
  context.put("nc.ui.uif2.userdefitem.QueryParam#1fc5def",bean);
  bean.setRulecode("materialassistant");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

}
