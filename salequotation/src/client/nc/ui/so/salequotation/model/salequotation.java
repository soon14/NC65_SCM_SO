package nc.ui.so.salequotation.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.ui.uif2.factory.AbstractJavaBeanDefinition;

public class salequotation extends AbstractJavaBeanDefinition{
	private Map<String, Object> context = new HashMap();
public nc.vo.uif2.LoginContext getContext(){
 if(context.get("context")!=null)
 return (nc.vo.uif2.LoginContext)context.get("context");
  nc.vo.uif2.LoginContext bean = new nc.vo.uif2.LoginContext();
  context.put("context",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.vo.bd.meta.BDObjectAdpaterFactory getBoadapterfacotry(){
 if(context.get("boadapterfacotry")!=null)
 return (nc.vo.bd.meta.BDObjectAdpaterFactory)context.get("boadapterfacotry");
  nc.vo.bd.meta.BDObjectAdpaterFactory bean = new nc.vo.bd.meta.BDObjectAdpaterFactory();
  context.put("boadapterfacotry",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.so.salequotation.model.SalequoModelService getManageModelService(){
 if(context.get("manageModelService")!=null)
 return (nc.ui.so.salequotation.model.SalequoModelService)context.get("manageModelService");
  nc.ui.so.salequotation.model.SalequoModelService bean = new nc.ui.so.salequotation.model.SalequoModelService();
  context.put("manageModelService",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.so.salequotation.model.SalequoModel getManageAppModel(){
 if(context.get("manageAppModel")!=null)
 return (nc.ui.so.salequotation.model.SalequoModel)context.get("manageAppModel");
  nc.ui.so.salequotation.model.SalequoModel bean = new nc.ui.so.salequotation.model.SalequoModel();
  context.put("manageAppModel",bean);
  bean.setSalequoService(getManageModelService());
  bean.setBusinessObjectAdapterFactory(getBoadapterfacotry());
  bean.setContext(getContext());
  bean.setBillType("4310");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.lazilyload.DefaultBillLazilyLoader getBillLazilyLoader(){
 if(context.get("billLazilyLoader")!=null)
 return (nc.ui.pubapp.uif2app.lazilyload.DefaultBillLazilyLoader)context.get("billLazilyLoader");
  nc.ui.pubapp.uif2app.lazilyload.DefaultBillLazilyLoader bean = new nc.ui.pubapp.uif2app.lazilyload.DefaultBillLazilyLoader();
  context.put("billLazilyLoader",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.lazilyload.LazilyLoadManager getLasilyLodadMediator(){
 if(context.get("LasilyLodadMediator")!=null)
 return (nc.ui.pubapp.uif2app.lazilyload.LazilyLoadManager)context.get("LasilyLodadMediator");
  nc.ui.pubapp.uif2app.lazilyload.LazilyLoadManager bean = new nc.ui.pubapp.uif2app.lazilyload.LazilyLoadManager();
  context.put("LasilyLodadMediator",bean);
  bean.setModel(getManageAppModel());
  bean.setLoader(getBillLazilyLoader());
  bean.setLazilyLoadSupporter(getManagedList0());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList0(){  List list = new ArrayList();  list.add(getCardPanelLazilyLoad_19b54c7());  list.add(getListPanelLazilyLoad_daed3b());  list.add(getActionLazilyLoad_10cfd8d());  return list;}

private nc.ui.pubapp.uif2app.lazilyload.CardPanelLazilyLoad getCardPanelLazilyLoad_19b54c7(){
 if(context.get("nc.ui.pubapp.uif2app.lazilyload.CardPanelLazilyLoad#19b54c7")!=null)
 return (nc.ui.pubapp.uif2app.lazilyload.CardPanelLazilyLoad)context.get("nc.ui.pubapp.uif2app.lazilyload.CardPanelLazilyLoad#19b54c7");
  nc.ui.pubapp.uif2app.lazilyload.CardPanelLazilyLoad bean = new nc.ui.pubapp.uif2app.lazilyload.CardPanelLazilyLoad();
  context.put("nc.ui.pubapp.uif2app.lazilyload.CardPanelLazilyLoad#19b54c7",bean);
  bean.setBillform(getBillFormEditor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.pubapp.uif2app.lazilyload.ListPanelLazilyLoad getListPanelLazilyLoad_daed3b(){
 if(context.get("nc.ui.pubapp.uif2app.lazilyload.ListPanelLazilyLoad#daed3b")!=null)
 return (nc.ui.pubapp.uif2app.lazilyload.ListPanelLazilyLoad)context.get("nc.ui.pubapp.uif2app.lazilyload.ListPanelLazilyLoad#daed3b");
  nc.ui.pubapp.uif2app.lazilyload.ListPanelLazilyLoad bean = new nc.ui.pubapp.uif2app.lazilyload.ListPanelLazilyLoad();
  context.put("nc.ui.pubapp.uif2app.lazilyload.ListPanelLazilyLoad#daed3b",bean);
  bean.setListView(getList());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.pubapp.uif2app.lazilyload.ActionLazilyLoad getActionLazilyLoad_10cfd8d(){
 if(context.get("nc.ui.pubapp.uif2app.lazilyload.ActionLazilyLoad#10cfd8d")!=null)
 return (nc.ui.pubapp.uif2app.lazilyload.ActionLazilyLoad)context.get("nc.ui.pubapp.uif2app.lazilyload.ActionLazilyLoad#10cfd8d");
  nc.ui.pubapp.uif2app.lazilyload.ActionLazilyLoad bean = new nc.ui.pubapp.uif2app.lazilyload.ActionLazilyLoad();
  context.put("nc.ui.pubapp.uif2app.lazilyload.ActionLazilyLoad#10cfd8d",bean);
  bean.setModel(getManageAppModel());
  bean.setActionList(getManagedList1());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList1(){  List list = new ArrayList();  list.add(getPrintaction());  list.add(getPrintpreviewaction());  list.add(getOutputAction());  return list;}

public nc.ui.uif2.components.pagination.PaginationBar getPageBar(){
 if(context.get("pageBar")!=null)
 return (nc.ui.uif2.components.pagination.PaginationBar)context.get("pageBar");
  nc.ui.uif2.components.pagination.PaginationBar bean = new nc.ui.uif2.components.pagination.PaginationBar();
  context.put("pageBar",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.actions.pagination.BillModelPaginationDelegator getPageDelegator(){
 if(context.get("pageDelegator")!=null)
 return (nc.ui.pubapp.uif2app.actions.pagination.BillModelPaginationDelegator)context.get("pageDelegator");
  nc.ui.pubapp.uif2app.actions.pagination.BillModelPaginationDelegator bean = new nc.ui.pubapp.uif2app.actions.pagination.BillModelPaginationDelegator(getManageAppModel());  context.put("pageDelegator",bean);
  bean.setPaginationQuery(getPageQuery());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.so.salequotation.model.SalequoPageService getPageQuery(){
 if(context.get("pageQuery")!=null)
 return (nc.ui.so.salequotation.model.SalequoPageService)context.get("pageQuery");
  nc.ui.so.salequotation.model.SalequoPageService bean = new nc.ui.so.salequotation.model.SalequoPageService();
  context.put("pageQuery",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.scmpub.page.model.SCMBillPageMediator getPageMediator(){
 if(context.get("pageMediator")!=null)
 return (nc.ui.scmpub.page.model.SCMBillPageMediator)context.get("pageMediator");
  nc.ui.scmpub.page.model.SCMBillPageMediator bean = new nc.ui.scmpub.page.model.SCMBillPageMediator();
  context.put("pageMediator",bean);
  bean.setListView(getList());
  bean.setRecordInPage(10);
  bean.setCachePages(10);
  bean.setPageDelegator(getPageDelegator());
  bean.init();
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.scmpub.page.model.SCMBillPageModelDataManager getModelDataManager(){
 if(context.get("modelDataManager")!=null)
 return (nc.ui.scmpub.page.model.SCMBillPageModelDataManager)context.get("modelDataManager");
  nc.ui.scmpub.page.model.SCMBillPageModelDataManager bean = new nc.ui.scmpub.page.model.SCMBillPageModelDataManager();
  context.put("modelDataManager",bean);
  bean.setModel(getManageAppModel());
  bean.setPageQuery(getPageQuery());
  bean.setPageDelegator(getPageDelegator());
  bean.setPagePanel(getListToolbarPnl());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.so.salequotation.service.SalequoValidationService getValidateService(){
 if(context.get("validateService")!=null)
 return (nc.ui.so.salequotation.service.SalequoValidationService)context.get("validateService");
  nc.ui.so.salequotation.service.SalequoValidationService bean = new nc.ui.so.salequotation.service.SalequoValidationService();
  context.put("validateService",bean);
  bean.setEditor(getBillFormEditor());
  bean.setValidators(getManagedList2());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList2(){  List list = new ArrayList();  list.add(getDimesionValidator_1da60a1());  list.add(getPriceAndNumValidator_1fdf53c());  return list;}

private nc.ui.so.salequotation.view.validator.DimesionValidator getDimesionValidator_1da60a1(){
 if(context.get("nc.ui.so.salequotation.view.validator.DimesionValidator#1da60a1")!=null)
 return (nc.ui.so.salequotation.view.validator.DimesionValidator)context.get("nc.ui.so.salequotation.view.validator.DimesionValidator#1da60a1");
  nc.ui.so.salequotation.view.validator.DimesionValidator bean = new nc.ui.so.salequotation.view.validator.DimesionValidator();
  context.put("nc.ui.so.salequotation.view.validator.DimesionValidator#1da60a1",bean);
  bean.setEditor(getBillFormEditor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.so.salequotation.view.validator.PriceAndNumValidator getPriceAndNumValidator_1fdf53c(){
 if(context.get("nc.ui.so.salequotation.view.validator.PriceAndNumValidator#1fdf53c")!=null)
 return (nc.ui.so.salequotation.view.validator.PriceAndNumValidator)context.get("nc.ui.so.salequotation.view.validator.PriceAndNumValidator#1fdf53c");
  nc.ui.so.salequotation.view.validator.PriceAndNumValidator bean = new nc.ui.so.salequotation.view.validator.PriceAndNumValidator();
  context.put("nc.ui.so.salequotation.view.validator.PriceAndNumValidator#1fdf53c",bean);
  bean.setEditor(getBillFormEditor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.so.salequotation.view.SalequoListView getList(){
 if(context.get("list")!=null)
 return (nc.ui.so.salequotation.view.SalequoListView)context.get("list");
  nc.ui.so.salequotation.view.SalequoListView bean = new nc.ui.so.salequotation.view.SalequoListView();
  context.put("list",bean);
  bean.setModel(getManageAppModel());
  bean.setMultiSelectionEnable(true);
  bean.setTemplateContainer(getTemplateContainer());
  bean.setPaginationBar(getPageBar());
  bean.setUserdefitemListPreparator(getUserdefAndMarAsstListPreparator());
  bean.initUI();
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.pubapp.uif2app.view.CompositeBillListDataPrepare getUserdefAndMarAsstListPreparator(){
 if(context.get("userdefAndMarAsstListPreparator")!=null)
 return (nc.ui.pubapp.uif2app.view.CompositeBillListDataPrepare)context.get("userdefAndMarAsstListPreparator");
  nc.ui.pubapp.uif2app.view.CompositeBillListDataPrepare bean = new nc.ui.pubapp.uif2app.view.CompositeBillListDataPrepare();
  context.put("userdefAndMarAsstListPreparator",bean);
  bean.setBillListDataPrepares(getManagedList3());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList3(){  List list = new ArrayList();  list.add(getUserdefitemlistPreparator());  list.add(getMarAsstPreparator());  return list;}

public nc.ui.uif2.editor.UserdefitemContainerListPreparator getUserdefitemlistPreparator(){
 if(context.get("userdefitemlistPreparator")!=null)
 return (nc.ui.uif2.editor.UserdefitemContainerListPreparator)context.get("userdefitemlistPreparator");
  nc.ui.uif2.editor.UserdefitemContainerListPreparator bean = new nc.ui.uif2.editor.UserdefitemContainerListPreparator();
  context.put("userdefitemlistPreparator",bean);
  bean.setContainer(getUserdefitemContainer());
  bean.setParams(getManagedList4());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList4(){  List list = new ArrayList();  list.add(getUserdefQueryParam_1ef50ce());  list.add(getUserdefQueryParam_a238aa());  return list;}

private nc.ui.uif2.editor.UserdefQueryParam getUserdefQueryParam_1ef50ce(){
 if(context.get("nc.ui.uif2.editor.UserdefQueryParam#1ef50ce")!=null)
 return (nc.ui.uif2.editor.UserdefQueryParam)context.get("nc.ui.uif2.editor.UserdefQueryParam#1ef50ce");
  nc.ui.uif2.editor.UserdefQueryParam bean = new nc.ui.uif2.editor.UserdefQueryParam();
  context.put("nc.ui.uif2.editor.UserdefQueryParam#1ef50ce",bean);
  bean.setMdfullname("so.SalequotationHVO");
  bean.setPos(0);
  bean.setPrefix("vdef");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.editor.UserdefQueryParam getUserdefQueryParam_a238aa(){
 if(context.get("nc.ui.uif2.editor.UserdefQueryParam#a238aa")!=null)
 return (nc.ui.uif2.editor.UserdefQueryParam)context.get("nc.ui.uif2.editor.UserdefQueryParam#a238aa");
  nc.ui.uif2.editor.UserdefQueryParam bean = new nc.ui.uif2.editor.UserdefQueryParam();
  context.put("nc.ui.uif2.editor.UserdefQueryParam#a238aa",bean);
  bean.setMdfullname("so.SalequotationBVO");
  bean.setPos(1);
  bean.setPrefix("vbdef");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.uif2.editor.UserdefitemContainerPreparator getUserdefitemPreparator(){
 if(context.get("userdefitemPreparator")!=null)
 return (nc.ui.uif2.editor.UserdefitemContainerPreparator)context.get("userdefitemPreparator");
  nc.ui.uif2.editor.UserdefitemContainerPreparator bean = new nc.ui.uif2.editor.UserdefitemContainerPreparator();
  context.put("userdefitemPreparator",bean);
  bean.setContainer(getUserdefitemContainer());
  bean.setParams(getManagedList5());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList5(){  List list = new ArrayList();  list.add(getUserdefQueryParam_364313());  list.add(getUserdefQueryParam_6da905());  return list;}

private nc.ui.uif2.editor.UserdefQueryParam getUserdefQueryParam_364313(){
 if(context.get("nc.ui.uif2.editor.UserdefQueryParam#364313")!=null)
 return (nc.ui.uif2.editor.UserdefQueryParam)context.get("nc.ui.uif2.editor.UserdefQueryParam#364313");
  nc.ui.uif2.editor.UserdefQueryParam bean = new nc.ui.uif2.editor.UserdefQueryParam();
  context.put("nc.ui.uif2.editor.UserdefQueryParam#364313",bean);
  bean.setMdfullname("so.SalequotationHVO");
  bean.setPos(0);
  bean.setPrefix("vdef");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.editor.UserdefQueryParam getUserdefQueryParam_6da905(){
 if(context.get("nc.ui.uif2.editor.UserdefQueryParam#6da905")!=null)
 return (nc.ui.uif2.editor.UserdefQueryParam)context.get("nc.ui.uif2.editor.UserdefQueryParam#6da905");
  nc.ui.uif2.editor.UserdefQueryParam bean = new nc.ui.uif2.editor.UserdefQueryParam();
  context.put("nc.ui.uif2.editor.UserdefQueryParam#6da905",bean);
  bean.setMdfullname("so.SalequotationBVO");
  bean.setPos(1);
  bean.setPrefix("vbdef");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.view.material.assistant.MarAsstPreparator getMarAsstPreparator(){
 if(context.get("marAsstPreparator")!=null)
 return (nc.ui.pubapp.uif2app.view.material.assistant.MarAsstPreparator)context.get("marAsstPreparator");
  nc.ui.pubapp.uif2app.view.material.assistant.MarAsstPreparator bean = new nc.ui.pubapp.uif2app.view.material.assistant.MarAsstPreparator();
  context.put("marAsstPreparator",bean);
  bean.setModel(getManageAppModel());
  bean.setContainer(getUserdefitemContainer());
  bean.setPrefix("vfree");
  bean.setMaterialField("pk_material_v");
  bean.setProjectField("pk_project");
  bean.setSupplierField("pk_supplier");
  bean.setProductorField("pk_productor");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.uif2.userdefitem.UserDefItemContainer getUserdefitemContainer(){
 if(context.get("userdefitemContainer")!=null)
 return (nc.ui.uif2.userdefitem.UserDefItemContainer)context.get("userdefitemContainer");
  nc.ui.uif2.userdefitem.UserDefItemContainer bean = new nc.ui.uif2.userdefitem.UserDefItemContainer();
  context.put("userdefitemContainer",bean);
  bean.setContext(getContext());
  bean.setParams(getManagedList6());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList6(){  List list = new ArrayList();  list.add(getQueryParam_1738881());  list.add(getQueryParam_7054a());  list.add(getQueryParam_ec5c0f());  return list;}

private nc.ui.uif2.userdefitem.QueryParam getQueryParam_1738881(){
 if(context.get("nc.ui.uif2.userdefitem.QueryParam#1738881")!=null)
 return (nc.ui.uif2.userdefitem.QueryParam)context.get("nc.ui.uif2.userdefitem.QueryParam#1738881");
  nc.ui.uif2.userdefitem.QueryParam bean = new nc.ui.uif2.userdefitem.QueryParam();
  context.put("nc.ui.uif2.userdefitem.QueryParam#1738881",bean);
  bean.setMdfullname("so.SalequotationHVO");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.userdefitem.QueryParam getQueryParam_7054a(){
 if(context.get("nc.ui.uif2.userdefitem.QueryParam#7054a")!=null)
 return (nc.ui.uif2.userdefitem.QueryParam)context.get("nc.ui.uif2.userdefitem.QueryParam#7054a");
  nc.ui.uif2.userdefitem.QueryParam bean = new nc.ui.uif2.userdefitem.QueryParam();
  context.put("nc.ui.uif2.userdefitem.QueryParam#7054a",bean);
  bean.setMdfullname("so.SalequotationBVO");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.userdefitem.QueryParam getQueryParam_ec5c0f(){
 if(context.get("nc.ui.uif2.userdefitem.QueryParam#ec5c0f")!=null)
 return (nc.ui.uif2.userdefitem.QueryParam)context.get("nc.ui.uif2.userdefitem.QueryParam#ec5c0f");
  nc.ui.uif2.userdefitem.QueryParam bean = new nc.ui.uif2.userdefitem.QueryParam();
  context.put("nc.ui.uif2.userdefitem.QueryParam#ec5c0f",bean);
  bean.setRulecode("materialassistant");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.view.FractionFixMediator getFractionFixMediator(){
 if(context.get("fractionFixMediator")!=null)
 return (nc.ui.pubapp.uif2app.view.FractionFixMediator)context.get("fractionFixMediator");
  nc.ui.pubapp.uif2app.view.FractionFixMediator bean = new nc.ui.pubapp.uif2app.view.FractionFixMediator(getManagedList7(),getManagedList8());  context.put("fractionFixMediator",bean);
  bean.setKeys(getManagedList9());
  bean.initUI();
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList7(){  List list = new ArrayList();  list.add(getBillFormEditor());  return list;}

private List getManagedList8(){  List list = new ArrayList();  list.add(getList());  return list;}

private List getManagedList9(){  List list = new ArrayList();  list.add("nchangerate");  list.add("nqtchangerate");  return list;}

public nc.ui.so.salequotation.view.SalequoBillForm getBillFormEditor(){
 if(context.get("billFormEditor")!=null)
 return (nc.ui.so.salequotation.view.SalequoBillForm)context.get("billFormEditor");
  nc.ui.so.salequotation.view.SalequoBillForm bean = new nc.ui.so.salequotation.view.SalequoBillForm();
  context.put("billFormEditor",bean);
  bean.setContext(getContext());
  bean.setModel(getManageAppModel());
  bean.setTemplateNotNullValidate(true);
  bean.setTemplateContainer(getTemplateContainer());
  bean.setAutoAddLine(true);
  bean.setUserdefitemPreparator(getCompositeBillDataPrepare_19016b4());
  bean.setBlankChildrenFilter(getSingleFieldBlankChildrenFilter_c29aca());
  bean.setBodyLineActions(getManagedList11());
  bean.initUI();
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.pubapp.uif2app.view.CompositeBillDataPrepare getCompositeBillDataPrepare_19016b4(){
 if(context.get("nc.ui.pubapp.uif2app.view.CompositeBillDataPrepare#19016b4")!=null)
 return (nc.ui.pubapp.uif2app.view.CompositeBillDataPrepare)context.get("nc.ui.pubapp.uif2app.view.CompositeBillDataPrepare#19016b4");
  nc.ui.pubapp.uif2app.view.CompositeBillDataPrepare bean = new nc.ui.pubapp.uif2app.view.CompositeBillDataPrepare();
  context.put("nc.ui.pubapp.uif2app.view.CompositeBillDataPrepare#19016b4",bean);
  bean.setBillDataPrepares(getManagedList10());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList10(){  List list = new ArrayList();  list.add(getUserdefitemPreparator());  list.add(getMarAsstPreparator());  return list;}

private nc.ui.pubapp.uif2app.view.value.SingleFieldBlankChildrenFilter getSingleFieldBlankChildrenFilter_c29aca(){
 if(context.get("nc.ui.pubapp.uif2app.view.value.SingleFieldBlankChildrenFilter#c29aca")!=null)
 return (nc.ui.pubapp.uif2app.view.value.SingleFieldBlankChildrenFilter)context.get("nc.ui.pubapp.uif2app.view.value.SingleFieldBlankChildrenFilter#c29aca");
  nc.ui.pubapp.uif2app.view.value.SingleFieldBlankChildrenFilter bean = new nc.ui.pubapp.uif2app.view.value.SingleFieldBlankChildrenFilter();
  context.put("nc.ui.pubapp.uif2app.view.value.SingleFieldBlankChildrenFilter#c29aca",bean);
  bean.setFieldName("pk_material_v");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList11(){  List list = new ArrayList();  list.add(getSalequoAddLineAction_406c2b());  list.add(getSalequoInsLineAction_17da122());  list.add(getBodyDelLineAction_dde5e6());  list.add(getBodyCopyLineAction_2071f2());  list.add(getSalequoBodyPasteLineAction_18fa39());  list.add(getBodyPasteToTailAction_1d3bb6a());  list.add(getActionsBar_ActionsBarSeparator_1b53130());  list.add(getBodyLineEditAction_b05cbb());  list.add(getActionsBar_ActionsBarSeparator_97fb64());  list.add(getDefaultBodyZoomAction_1f98ea6());  return list;}

private nc.ui.so.salequotation.action.SalequoAddLineAction getSalequoAddLineAction_406c2b(){
 if(context.get("nc.ui.so.salequotation.action.SalequoAddLineAction#406c2b")!=null)
 return (nc.ui.so.salequotation.action.SalequoAddLineAction)context.get("nc.ui.so.salequotation.action.SalequoAddLineAction#406c2b");
  nc.ui.so.salequotation.action.SalequoAddLineAction bean = new nc.ui.so.salequotation.action.SalequoAddLineAction();
  context.put("nc.ui.so.salequotation.action.SalequoAddLineAction#406c2b",bean);
  bean.setModel(getManageAppModel());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.so.salequotation.action.SalequoInsLineAction getSalequoInsLineAction_17da122(){
 if(context.get("nc.ui.so.salequotation.action.SalequoInsLineAction#17da122")!=null)
 return (nc.ui.so.salequotation.action.SalequoInsLineAction)context.get("nc.ui.so.salequotation.action.SalequoInsLineAction#17da122");
  nc.ui.so.salequotation.action.SalequoInsLineAction bean = new nc.ui.so.salequotation.action.SalequoInsLineAction();
  context.put("nc.ui.so.salequotation.action.SalequoInsLineAction#17da122",bean);
  bean.setModel(getManageAppModel());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.pubapp.uif2app.actions.BodyDelLineAction getBodyDelLineAction_dde5e6(){
 if(context.get("nc.ui.pubapp.uif2app.actions.BodyDelLineAction#dde5e6")!=null)
 return (nc.ui.pubapp.uif2app.actions.BodyDelLineAction)context.get("nc.ui.pubapp.uif2app.actions.BodyDelLineAction#dde5e6");
  nc.ui.pubapp.uif2app.actions.BodyDelLineAction bean = new nc.ui.pubapp.uif2app.actions.BodyDelLineAction();
  context.put("nc.ui.pubapp.uif2app.actions.BodyDelLineAction#dde5e6",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.pubapp.uif2app.actions.BodyCopyLineAction getBodyCopyLineAction_2071f2(){
 if(context.get("nc.ui.pubapp.uif2app.actions.BodyCopyLineAction#2071f2")!=null)
 return (nc.ui.pubapp.uif2app.actions.BodyCopyLineAction)context.get("nc.ui.pubapp.uif2app.actions.BodyCopyLineAction#2071f2");
  nc.ui.pubapp.uif2app.actions.BodyCopyLineAction bean = new nc.ui.pubapp.uif2app.actions.BodyCopyLineAction();
  context.put("nc.ui.pubapp.uif2app.actions.BodyCopyLineAction#2071f2",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.so.salequotation.action.SalequoBodyPasteLineAction getSalequoBodyPasteLineAction_18fa39(){
 if(context.get("nc.ui.so.salequotation.action.SalequoBodyPasteLineAction#18fa39")!=null)
 return (nc.ui.so.salequotation.action.SalequoBodyPasteLineAction)context.get("nc.ui.so.salequotation.action.SalequoBodyPasteLineAction#18fa39");
  nc.ui.so.salequotation.action.SalequoBodyPasteLineAction bean = new nc.ui.so.salequotation.action.SalequoBodyPasteLineAction();
  context.put("nc.ui.so.salequotation.action.SalequoBodyPasteLineAction#18fa39",bean);
  bean.setClearItems(getManagedList12());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList12(){  List list = new ArrayList();  list.add("pk_salequotation_b");  list.add("ts");  return list;}

private nc.ui.pubapp.uif2app.actions.BodyPasteToTailAction getBodyPasteToTailAction_1d3bb6a(){
 if(context.get("nc.ui.pubapp.uif2app.actions.BodyPasteToTailAction#1d3bb6a")!=null)
 return (nc.ui.pubapp.uif2app.actions.BodyPasteToTailAction)context.get("nc.ui.pubapp.uif2app.actions.BodyPasteToTailAction#1d3bb6a");
  nc.ui.pubapp.uif2app.actions.BodyPasteToTailAction bean = new nc.ui.pubapp.uif2app.actions.BodyPasteToTailAction();
  context.put("nc.ui.pubapp.uif2app.actions.BodyPasteToTailAction#1d3bb6a",bean);
  bean.setClearItems(getManagedList13());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList13(){  List list = new ArrayList();  list.add("pk_salequotation_b");  list.add("ts");  return list;}

private nc.ui.pub.beans.ActionsBar.ActionsBarSeparator getActionsBar_ActionsBarSeparator_1b53130(){
 if(context.get("nc.ui.pub.beans.ActionsBar.ActionsBarSeparator#1b53130")!=null)
 return (nc.ui.pub.beans.ActionsBar.ActionsBarSeparator)context.get("nc.ui.pub.beans.ActionsBar.ActionsBarSeparator#1b53130");
  nc.ui.pub.beans.ActionsBar.ActionsBarSeparator bean = new nc.ui.pub.beans.ActionsBar.ActionsBarSeparator();
  context.put("nc.ui.pub.beans.ActionsBar.ActionsBarSeparator#1b53130",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.pubapp.uif2app.actions.BodyLineEditAction getBodyLineEditAction_b05cbb(){
 if(context.get("nc.ui.pubapp.uif2app.actions.BodyLineEditAction#b05cbb")!=null)
 return (nc.ui.pubapp.uif2app.actions.BodyLineEditAction)context.get("nc.ui.pubapp.uif2app.actions.BodyLineEditAction#b05cbb");
  nc.ui.pubapp.uif2app.actions.BodyLineEditAction bean = new nc.ui.pubapp.uif2app.actions.BodyLineEditAction();
  context.put("nc.ui.pubapp.uif2app.actions.BodyLineEditAction#b05cbb",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.pub.beans.ActionsBar.ActionsBarSeparator getActionsBar_ActionsBarSeparator_97fb64(){
 if(context.get("nc.ui.pub.beans.ActionsBar.ActionsBarSeparator#97fb64")!=null)
 return (nc.ui.pub.beans.ActionsBar.ActionsBarSeparator)context.get("nc.ui.pub.beans.ActionsBar.ActionsBarSeparator#97fb64");
  nc.ui.pub.beans.ActionsBar.ActionsBarSeparator bean = new nc.ui.pub.beans.ActionsBar.ActionsBarSeparator();
  context.put("nc.ui.pub.beans.ActionsBar.ActionsBarSeparator#97fb64",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.pubapp.uif2app.actions.DefaultBodyZoomAction getDefaultBodyZoomAction_1f98ea6(){
 if(context.get("nc.ui.pubapp.uif2app.actions.DefaultBodyZoomAction#1f98ea6")!=null)
 return (nc.ui.pubapp.uif2app.actions.DefaultBodyZoomAction)context.get("nc.ui.pubapp.uif2app.actions.DefaultBodyZoomAction#1f98ea6");
  nc.ui.pubapp.uif2app.actions.DefaultBodyZoomAction bean = new nc.ui.pubapp.uif2app.actions.DefaultBodyZoomAction();
  context.put("nc.ui.pubapp.uif2app.actions.DefaultBodyZoomAction#1f98ea6",bean);
  bean.setPos(1);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.uif2.actions.ActionContributors getToftpanelActionContributors(){
 if(context.get("toftpanelActionContributors")!=null)
 return (nc.ui.uif2.actions.ActionContributors)context.get("toftpanelActionContributors");
  nc.ui.uif2.actions.ActionContributors bean = new nc.ui.uif2.actions.ActionContributors();
  context.put("toftpanelActionContributors",bean);
  bean.setContributors(getManagedList14());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList14(){  List list = new ArrayList();  list.add(getActionsOfList());  list.add(getActionsOfCard());  return list;}

public nc.ui.uif2.actions.StandAloneToftPanelActionContainer getActionsOfList(){
 if(context.get("actionsOfList")!=null)
 return (nc.ui.uif2.actions.StandAloneToftPanelActionContainer)context.get("actionsOfList");
  nc.ui.uif2.actions.StandAloneToftPanelActionContainer bean = new nc.ui.uif2.actions.StandAloneToftPanelActionContainer(getList());  context.put("actionsOfList",bean);
  bean.setActions(getManagedList15());
  bean.setEditActions(getManagedList16());
  bean.setModel(getManageAppModel());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList15(){  List list = new ArrayList();  list.add(getAddaction());  list.add(getEditAction());  list.add(getDeleteAction());  list.add(getCopyAction());  list.add(getSeparatorAction());  list.add(getQueryaction());  list.add(getRefreshAllAction());  list.add(getSeparatorAction());  list.add(getCommitMenuAction());  list.add(getAuditMenuAction());  list.add(getColseMenuAction());  list.add(getAssistMenuAction());  list.add(getSeparatorAction());  list.add(getLinkQueryActionGroup());  list.add(getSeparatorAction());  list.add(getPrintManageActionGroup());  return list;}

private List getManagedList16(){  List list = new ArrayList();  list.add(getSaveaction());  list.add(getSaveandcommitAction());  list.add(getSeparatorAction());  list.add(getCancelaction());  list.add(getSeparatorAction());  list.add(getFindPriceAction());  list.add(getQuickeditAction());  list.add(getAssistMenuAction());  list.add(getSeparatorAction());  list.add(getLinkQueryActionGroup());  return list;}

public nc.ui.uif2.actions.StandAloneToftPanelActionContainer getActionsOfCard(){
 if(context.get("actionsOfCard")!=null)
 return (nc.ui.uif2.actions.StandAloneToftPanelActionContainer)context.get("actionsOfCard");
  nc.ui.uif2.actions.StandAloneToftPanelActionContainer bean = new nc.ui.uif2.actions.StandAloneToftPanelActionContainer(getBillFormEditor());  context.put("actionsOfCard",bean);
  bean.setActions(getManagedList17());
  bean.setEditActions(getManagedList18());
  bean.setModel(getManageAppModel());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList17(){  List list = new ArrayList();  list.add(getAddaction());  list.add(getEditAction());  list.add(getDeleteAction());  list.add(getCopyAction());  list.add(getSeparatorAction());  list.add(getQueryaction());  list.add(getRefreshSingleAction());  list.add(getSeparatorAction());  list.add(getCommitMenuAction());  list.add(getAuditMenuAction());  list.add(getColseMenuAction());  list.add(getAssistMenuAction());  list.add(getSeparatorAction());  list.add(getLinkQueryActionGroup());  list.add(getSeparatorAction());  list.add(getPrintManageActionGroup());  return list;}

private List getManagedList18(){  List list = new ArrayList();  list.add(getSaveaction());  list.add(getSaveandcommitAction());  list.add(getSeparatorAction());  list.add(getCancelaction());  list.add(getSeparatorAction());  list.add(getFindPriceAction());  list.add(getQuickeditAction());  list.add(getAssistMenuAction());  list.add(getSeparatorAction());  list.add(getLinkQueryActionGroup());  return list;}

public nc.ui.pubapp.uif2app.funcnode.trantype.TrantypeBillTemplateMender getTrantypeTempMender(){
 if(context.get("trantypeTempMender")!=null)
 return (nc.ui.pubapp.uif2app.funcnode.trantype.TrantypeBillTemplateMender)context.get("trantypeTempMender");
  nc.ui.pubapp.uif2app.funcnode.trantype.TrantypeBillTemplateMender bean = new nc.ui.pubapp.uif2app.funcnode.trantype.TrantypeBillTemplateMender(getContext());  context.put("trantypeTempMender",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.tangramlayout.UEQueryAreaShell getQueryArea(){
 if(context.get("queryArea")!=null)
 return (nc.ui.pubapp.uif2app.tangramlayout.UEQueryAreaShell)context.get("queryArea");
  nc.ui.pubapp.uif2app.tangramlayout.UEQueryAreaShell bean = new nc.ui.pubapp.uif2app.tangramlayout.UEQueryAreaShell();
  context.put("queryArea",bean);
  bean.setQueryAreaCreator(getQueryaction());
  bean.initUI();
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.uif2.TangramContainer getContainer(){
 if(context.get("container")!=null)
 return (nc.ui.uif2.TangramContainer)context.get("container");
  nc.ui.uif2.TangramContainer bean = new nc.ui.uif2.TangramContainer();
  context.put("container",bean);
  bean.setModel(getManageAppModel());
  bean.setTangramLayoutRoot(getTBNode_cc51e0());
  bean.initUI();
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.tangramlayout.node.TBNode getTBNode_cc51e0(){
 if(context.get("nc.ui.uif2.tangramlayout.node.TBNode#cc51e0")!=null)
 return (nc.ui.uif2.tangramlayout.node.TBNode)context.get("nc.ui.uif2.tangramlayout.node.TBNode#cc51e0");
  nc.ui.uif2.tangramlayout.node.TBNode bean = new nc.ui.uif2.tangramlayout.node.TBNode();
  context.put("nc.ui.uif2.tangramlayout.node.TBNode#cc51e0",bean);
  bean.setShowMode("CardLayout");
  bean.setTabs(getManagedList19());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList19(){  List list = new ArrayList();  list.add(getHSNode_b476bb());  list.add(getVSNode_14e1af7());  return list;}

private nc.ui.uif2.tangramlayout.node.HSNode getHSNode_b476bb(){
 if(context.get("nc.ui.uif2.tangramlayout.node.HSNode#b476bb")!=null)
 return (nc.ui.uif2.tangramlayout.node.HSNode)context.get("nc.ui.uif2.tangramlayout.node.HSNode#b476bb");
  nc.ui.uif2.tangramlayout.node.HSNode bean = new nc.ui.uif2.tangramlayout.node.HSNode();
  context.put("nc.ui.uif2.tangramlayout.node.HSNode#b476bb",bean);
  bean.setLeft(getCNode_1043e88());
  bean.setRight(getVSNode_1d703f());
  bean.setDividerLocation(0.22f);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.tangramlayout.node.CNode getCNode_1043e88(){
 if(context.get("nc.ui.uif2.tangramlayout.node.CNode#1043e88")!=null)
 return (nc.ui.uif2.tangramlayout.node.CNode)context.get("nc.ui.uif2.tangramlayout.node.CNode#1043e88");
  nc.ui.uif2.tangramlayout.node.CNode bean = new nc.ui.uif2.tangramlayout.node.CNode();
  context.put("nc.ui.uif2.tangramlayout.node.CNode#1043e88",bean);
  bean.setComponent(getQueryArea());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.tangramlayout.node.VSNode getVSNode_1d703f(){
 if(context.get("nc.ui.uif2.tangramlayout.node.VSNode#1d703f")!=null)
 return (nc.ui.uif2.tangramlayout.node.VSNode)context.get("nc.ui.uif2.tangramlayout.node.VSNode#1d703f");
  nc.ui.uif2.tangramlayout.node.VSNode bean = new nc.ui.uif2.tangramlayout.node.VSNode();
  context.put("nc.ui.uif2.tangramlayout.node.VSNode#1d703f",bean);
  bean.setUp(getCNode_1a58687());
  bean.setDown(getCNode_2884a8());
  bean.setDividerLocation(25f);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.tangramlayout.node.CNode getCNode_1a58687(){
 if(context.get("nc.ui.uif2.tangramlayout.node.CNode#1a58687")!=null)
 return (nc.ui.uif2.tangramlayout.node.CNode)context.get("nc.ui.uif2.tangramlayout.node.CNode#1a58687");
  nc.ui.uif2.tangramlayout.node.CNode bean = new nc.ui.uif2.tangramlayout.node.CNode();
  context.put("nc.ui.uif2.tangramlayout.node.CNode#1a58687",bean);
  bean.setComponent(getListToolbarPnl());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.tangramlayout.node.CNode getCNode_2884a8(){
 if(context.get("nc.ui.uif2.tangramlayout.node.CNode#2884a8")!=null)
 return (nc.ui.uif2.tangramlayout.node.CNode)context.get("nc.ui.uif2.tangramlayout.node.CNode#2884a8");
  nc.ui.uif2.tangramlayout.node.CNode bean = new nc.ui.uif2.tangramlayout.node.CNode();
  context.put("nc.ui.uif2.tangramlayout.node.CNode#2884a8",bean);
  bean.setName(getI18nFB_234775());
  bean.setComponent(getList());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private java.lang.String getI18nFB_234775(){
 if(context.get("nc.ui.uif2.I18nFB#234775")!=null)
 return (java.lang.String)context.get("nc.ui.uif2.I18nFB#234775");
  nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
    context.put("&nc.ui.uif2.I18nFB#234775",bean);  bean.setResDir("common");
  bean.setResId("UC001-0000107");
  bean.setDefaultValue("列表");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
 try {
     Object product = bean.getObject();
    context.put("nc.ui.uif2.I18nFB#234775",product);
     return (java.lang.String)product;
}
catch(Exception e) { throw new RuntimeException(e);}}

private nc.ui.uif2.tangramlayout.node.VSNode getVSNode_14e1af7(){
 if(context.get("nc.ui.uif2.tangramlayout.node.VSNode#14e1af7")!=null)
 return (nc.ui.uif2.tangramlayout.node.VSNode)context.get("nc.ui.uif2.tangramlayout.node.VSNode#14e1af7");
  nc.ui.uif2.tangramlayout.node.VSNode bean = new nc.ui.uif2.tangramlayout.node.VSNode();
  context.put("nc.ui.uif2.tangramlayout.node.VSNode#14e1af7",bean);
  bean.setUp(getCNode_1df00c9());
  bean.setDown(getCNode_a46e6e());
  bean.setDividerLocation(30f);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.tangramlayout.node.CNode getCNode_1df00c9(){
 if(context.get("nc.ui.uif2.tangramlayout.node.CNode#1df00c9")!=null)
 return (nc.ui.uif2.tangramlayout.node.CNode)context.get("nc.ui.uif2.tangramlayout.node.CNode#1df00c9");
  nc.ui.uif2.tangramlayout.node.CNode bean = new nc.ui.uif2.tangramlayout.node.CNode();
  context.put("nc.ui.uif2.tangramlayout.node.CNode#1df00c9",bean);
  bean.setComponent(getCardToolbarPnl());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.tangramlayout.node.CNode getCNode_a46e6e(){
 if(context.get("nc.ui.uif2.tangramlayout.node.CNode#a46e6e")!=null)
 return (nc.ui.uif2.tangramlayout.node.CNode)context.get("nc.ui.uif2.tangramlayout.node.CNode#a46e6e");
  nc.ui.uif2.tangramlayout.node.CNode bean = new nc.ui.uif2.tangramlayout.node.CNode();
  context.put("nc.ui.uif2.tangramlayout.node.CNode#a46e6e",bean);
  bean.setName(getI18nFB_1acc894());
  bean.setComponent(getBillFormEditor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private java.lang.String getI18nFB_1acc894(){
 if(context.get("nc.ui.uif2.I18nFB#1acc894")!=null)
 return (java.lang.String)context.get("nc.ui.uif2.I18nFB#1acc894");
  nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
    context.put("&nc.ui.uif2.I18nFB#1acc894",bean);  bean.setResDir("common");
  bean.setResId("UC001-0000106");
  bean.setDefaultValue("卡片");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
 try {
     Object product = bean.getObject();
    context.put("nc.ui.uif2.I18nFB#1acc894",product);
     return (java.lang.String)product;
}
catch(Exception e) { throw new RuntimeException(e);}}

public nc.ui.uif2.tangramlayout.CardLayoutToolbarPanel getListToolbarPnl(){
 if(context.get("listToolbarPnl")!=null)
 return (nc.ui.uif2.tangramlayout.CardLayoutToolbarPanel)context.get("listToolbarPnl");
  nc.ui.uif2.tangramlayout.CardLayoutToolbarPanel bean = new nc.ui.uif2.tangramlayout.CardLayoutToolbarPanel();
  context.put("listToolbarPnl",bean);
  bean.setModel(getManageAppModel());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.tangramlayout.UECardLayoutToolbarPanel getCardToolbarPnl(){
 if(context.get("cardToolbarPnl")!=null)
 return (nc.ui.pubapp.uif2app.tangramlayout.UECardLayoutToolbarPanel)context.get("cardToolbarPnl");
  nc.ui.pubapp.uif2app.tangramlayout.UECardLayoutToolbarPanel bean = new nc.ui.pubapp.uif2app.tangramlayout.UECardLayoutToolbarPanel();
  context.put("cardToolbarPnl",bean);
  bean.setTitleAction(getReturnaction());
  bean.setModel(getManageAppModel());
  bean.setRightExActions(getManagedList20());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.pubapp.uif2app.actions.UEReturnAction getReturnaction(){
 if(context.get("returnaction")!=null)
 return (nc.ui.pubapp.uif2app.actions.UEReturnAction)context.get("returnaction");
  nc.ui.pubapp.uif2app.actions.UEReturnAction bean = new nc.ui.pubapp.uif2app.actions.UEReturnAction();
  context.put("returnaction",bean);
  bean.setGoComponent(getList());
  bean.setSaveAction(getSaveaction());
  bean.setModel(getManageAppModel());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList20(){  List list = new ArrayList();  list.add(getActionsBar_ActionsBarSeparator_1355d8());  list.add(getHeadZoomAction());  return list;}

private nc.ui.pub.beans.ActionsBar.ActionsBarSeparator getActionsBar_ActionsBarSeparator_1355d8(){
 if(context.get("nc.ui.pub.beans.ActionsBar.ActionsBarSeparator#1355d8")!=null)
 return (nc.ui.pub.beans.ActionsBar.ActionsBarSeparator)context.get("nc.ui.pub.beans.ActionsBar.ActionsBarSeparator#1355d8");
  nc.ui.pub.beans.ActionsBar.ActionsBarSeparator bean = new nc.ui.pub.beans.ActionsBar.ActionsBarSeparator();
  context.put("nc.ui.pub.beans.ActionsBar.ActionsBarSeparator#1355d8",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.pubapp.uif2app.actions.DefaultHeadZoomAction getHeadZoomAction(){
 if(context.get("headZoomAction")!=null)
 return (nc.ui.pubapp.uif2app.actions.DefaultHeadZoomAction)context.get("headZoomAction");
  nc.ui.pubapp.uif2app.actions.DefaultHeadZoomAction bean = new nc.ui.pubapp.uif2app.actions.DefaultHeadZoomAction();
  context.put("headZoomAction",bean);
  bean.setBillForm(getBillFormEditor());
  bean.setModel(getManageAppModel());
  bean.setPos(0);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.model.DefaultFuncNodeInitDataListener getInitDataListener(){
 if(context.get("InitDataListener")!=null)
 return (nc.ui.pubapp.uif2app.model.DefaultFuncNodeInitDataListener)context.get("InitDataListener");
  nc.ui.pubapp.uif2app.model.DefaultFuncNodeInitDataListener bean = new nc.ui.pubapp.uif2app.model.DefaultFuncNodeInitDataListener();
  context.put("InitDataListener",bean);
  bean.setContext(getContext());
  bean.setModel(getManageAppModel());
  bean.setQueryAction(getQueryaction());
  bean.setVoClassName("nc.vo.so.salequotation.entity.AggSalequotationHVO");
  bean.setAutoShowUpComponent(getBillFormEditor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.uif2.FunNodeClosingHandler getClosingListener(){
 if(context.get("ClosingListener")!=null)
 return (nc.ui.uif2.FunNodeClosingHandler)context.get("ClosingListener");
  nc.ui.uif2.FunNodeClosingHandler bean = new nc.ui.uif2.FunNodeClosingHandler();
  context.put("ClosingListener",bean);
  bean.setModel(getManageAppModel());
  bean.setSaveaction(getSaveaction());
  bean.setCancelaction(getCancelaction());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.view.MouseClickShowPanelMediator getDoubleClickMediator(){
 if(context.get("doubleClickMediator")!=null)
 return (nc.ui.pubapp.uif2app.view.MouseClickShowPanelMediator)context.get("doubleClickMediator");
  nc.ui.pubapp.uif2app.view.MouseClickShowPanelMediator bean = new nc.ui.pubapp.uif2app.view.MouseClickShowPanelMediator();
  context.put("doubleClickMediator",bean);
  bean.setListView(getList());
  bean.setShowUpComponent(getBillFormEditor());
  bean.setHyperLinkColumn("vbillcode");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.view.RowNoMediator getRowNoMediator(){
 if(context.get("rowNoMediator")!=null)
 return (nc.ui.pubapp.uif2app.view.RowNoMediator)context.get("rowNoMediator");
  nc.ui.pubapp.uif2app.view.RowNoMediator bean = new nc.ui.pubapp.uif2app.view.RowNoMediator();
  context.put("rowNoMediator",bean);
  bean.setModel(getManageAppModel());
  bean.setEditor(getBillFormEditor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.scmpub.listener.BillCodeEditMediator getBillCodeMediator(){
 if(context.get("billCodeMediator")!=null)
 return (nc.ui.scmpub.listener.BillCodeEditMediator)context.get("billCodeMediator");
  nc.ui.scmpub.listener.BillCodeEditMediator bean = new nc.ui.scmpub.listener.BillCodeEditMediator();
  context.put("billCodeMediator",bean);
  bean.setBillForm(getBillFormEditor());
  bean.setBillCodeKey("vbillcode");
  bean.setBillType("4310");
  bean.initUI();
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.model.BillBodySortMediator getBillBodySortMediator(){
 if(context.get("billBodySortMediator")!=null)
 return (nc.ui.pubapp.uif2app.model.BillBodySortMediator)context.get("billBodySortMediator");
  nc.ui.pubapp.uif2app.model.BillBodySortMediator bean = new nc.ui.pubapp.uif2app.model.BillBodySortMediator(getManageAppModel(),getBillFormEditor(),getList());  context.put("billBodySortMediator",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.so.salequotation.model.FindPriceService getFindPriceService(){
 if(context.get("findPriceService")!=null)
 return (nc.ui.so.salequotation.model.FindPriceService)context.get("findPriceService");
  nc.ui.so.salequotation.model.FindPriceService bean = new nc.ui.so.salequotation.model.FindPriceService();
  context.put("findPriceService",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.model.AppEventHandlerMediator getAppEventHandlerMediator(){
 if(context.get("appEventHandlerMediator")!=null)
 return (nc.ui.pubapp.uif2app.model.AppEventHandlerMediator)context.get("appEventHandlerMediator");
  nc.ui.pubapp.uif2app.model.AppEventHandlerMediator bean = new nc.ui.pubapp.uif2app.model.AppEventHandlerMediator();
  context.put("appEventHandlerMediator",bean);
  bean.setModel(getManageAppModel());
  bean.setHandlerMap(getManagedMap0());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private Map getManagedMap0(){  Map map = new HashMap();  map.put("nc.ui.pubapp.uif2app.event.card.CardHeadTailBeforeEditEvent",getManagedList21());  map.put("nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent",getManagedList22());  map.put("nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent",getManagedList23());  map.put("nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent",getManagedList24());  return map;}

private List getManagedList21(){  List list = new ArrayList();  list.add(getSalequoHeadBeforeEditHandler_168df20());  return list;}

private nc.ui.so.salequotation.handler.SalequoHeadBeforeEditHandler getSalequoHeadBeforeEditHandler_168df20(){
 if(context.get("nc.ui.so.salequotation.handler.SalequoHeadBeforeEditHandler#168df20")!=null)
 return (nc.ui.so.salequotation.handler.SalequoHeadBeforeEditHandler)context.get("nc.ui.so.salequotation.handler.SalequoHeadBeforeEditHandler#168df20");
  nc.ui.so.salequotation.handler.SalequoHeadBeforeEditHandler bean = new nc.ui.so.salequotation.handler.SalequoHeadBeforeEditHandler();
  context.put("nc.ui.so.salequotation.handler.SalequoHeadBeforeEditHandler#168df20",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList22(){  List list = new ArrayList();  list.add(getSalequoBodyAfterEditHandler_10b9e1());  return list;}

private nc.ui.so.salequotation.handler.SalequoBodyAfterEditHandler getSalequoBodyAfterEditHandler_10b9e1(){
 if(context.get("nc.ui.so.salequotation.handler.SalequoBodyAfterEditHandler#10b9e1")!=null)
 return (nc.ui.so.salequotation.handler.SalequoBodyAfterEditHandler)context.get("nc.ui.so.salequotation.handler.SalequoBodyAfterEditHandler#10b9e1");
  nc.ui.so.salequotation.handler.SalequoBodyAfterEditHandler bean = new nc.ui.so.salequotation.handler.SalequoBodyAfterEditHandler();
  context.put("nc.ui.so.salequotation.handler.SalequoBodyAfterEditHandler#10b9e1",bean);
  bean.setModel(getManageAppModel());
  bean.setFindPriceService(getFindPriceService());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList23(){  List list = new ArrayList();  list.add(getSalequoHeadAfterEditHandler_1b9720());  list.add(getSalequoTranTypeEditHandler_15ee103());  return list;}

private nc.ui.so.salequotation.handler.SalequoHeadAfterEditHandler getSalequoHeadAfterEditHandler_1b9720(){
 if(context.get("nc.ui.so.salequotation.handler.SalequoHeadAfterEditHandler#1b9720")!=null)
 return (nc.ui.so.salequotation.handler.SalequoHeadAfterEditHandler)context.get("nc.ui.so.salequotation.handler.SalequoHeadAfterEditHandler#1b9720");
  nc.ui.so.salequotation.handler.SalequoHeadAfterEditHandler bean = new nc.ui.so.salequotation.handler.SalequoHeadAfterEditHandler();
  context.put("nc.ui.so.salequotation.handler.SalequoHeadAfterEditHandler#1b9720",bean);
  bean.setModel(getManageAppModel());
  bean.setFindPriceService(getFindPriceService());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.so.salequotation.handler.SalequoTranTypeEditHandler getSalequoTranTypeEditHandler_15ee103(){
 if(context.get("nc.ui.so.salequotation.handler.SalequoTranTypeEditHandler#15ee103")!=null)
 return (nc.ui.so.salequotation.handler.SalequoTranTypeEditHandler)context.get("nc.ui.so.salequotation.handler.SalequoTranTypeEditHandler#15ee103");
  nc.ui.so.salequotation.handler.SalequoTranTypeEditHandler bean = new nc.ui.so.salequotation.handler.SalequoTranTypeEditHandler();
  context.put("nc.ui.so.salequotation.handler.SalequoTranTypeEditHandler#15ee103",bean);
  bean.setService(getManageModelService());
  bean.setModel(getManageAppModel());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList24(){  List list = new ArrayList();  list.add(getSalequoBodyBeforeEditHandler_6bcdf());  return list;}

private nc.ui.so.salequotation.handler.SalequoBodyBeforeEditHandler getSalequoBodyBeforeEditHandler_6bcdf(){
 if(context.get("nc.ui.so.salequotation.handler.SalequoBodyBeforeEditHandler#6bcdf")!=null)
 return (nc.ui.so.salequotation.handler.SalequoBodyBeforeEditHandler)context.get("nc.ui.so.salequotation.handler.SalequoBodyBeforeEditHandler#6bcdf");
  nc.ui.so.salequotation.handler.SalequoBodyBeforeEditHandler bean = new nc.ui.so.salequotation.handler.SalequoBodyBeforeEditHandler();
  context.put("nc.ui.so.salequotation.handler.SalequoBodyBeforeEditHandler#6bcdf",bean);
  bean.setModel(getManageAppModel());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.so.salequotation.action.SalequoQuickEditAction getQuickeditAction(){
 if(context.get("quickeditAction")!=null)
 return (nc.ui.so.salequotation.action.SalequoQuickEditAction)context.get("quickeditAction");
  nc.ui.so.salequotation.action.SalequoQuickEditAction bean = new nc.ui.so.salequotation.action.SalequoQuickEditAction();
  context.put("quickeditAction",bean);
  bean.setEditor(getBillFormEditor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.funcnode.ui.action.SeparatorAction getSeparatorAction(){
 if(context.get("separatorAction")!=null)
 return (nc.funcnode.ui.action.SeparatorAction)context.get("separatorAction");
  nc.funcnode.ui.action.SeparatorAction bean = new nc.funcnode.ui.action.SeparatorAction();
  context.put("separatorAction",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.so.salequotation.query.SalequoQryCondDLGInitializer getSalequoQryCondDLGInitializer(){
 if(context.get("salequoQryCondDLGInitializer")!=null)
 return (nc.ui.so.salequotation.query.SalequoQryCondDLGInitializer)context.get("salequoQryCondDLGInitializer");
  nc.ui.so.salequotation.query.SalequoQryCondDLGInitializer bean = new nc.ui.so.salequotation.query.SalequoQryCondDLGInitializer();
  context.put("salequoQryCondDLGInitializer",bean);
  bean.setModel(getManageAppModel());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.query2.action.DefaultQueryAction getQueryaction(){
 if(context.get("queryaction")!=null)
 return (nc.ui.pubapp.uif2app.query2.action.DefaultQueryAction)context.get("queryaction");
  nc.ui.pubapp.uif2app.query2.action.DefaultQueryAction bean = new nc.ui.pubapp.uif2app.query2.action.DefaultQueryAction();
  context.put("queryaction",bean);
  bean.setModel(getManageAppModel());
  bean.setDataManager(getModelDataManager());
  bean.setQryCondDLGInitializer(getSalequoQryCondDLGInitializer());
  bean.setTemplateContainer(getQueryTemplateContainer());
  bean.setShowUpComponent(getList());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.actions.AddAction getAddaction(){
 if(context.get("addaction")!=null)
 return (nc.ui.pubapp.uif2app.actions.AddAction)context.get("addaction");
  nc.ui.pubapp.uif2app.actions.AddAction bean = new nc.ui.pubapp.uif2app.actions.AddAction();
  context.put("addaction",bean);
  bean.setModel(getManageAppModel());
  bean.setInterceptor(getShowUpComponentInterceptor_59fc7e());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor getShowUpComponentInterceptor_59fc7e(){
 if(context.get("nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor#59fc7e")!=null)
 return (nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor)context.get("nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor#59fc7e");
  nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor bean = new nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor();
  context.put("nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor#59fc7e",bean);
  bean.setShowUpComponent(getBillFormEditor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.so.salequotation.action.SalequoEditAction getEditAction(){
 if(context.get("editAction")!=null)
 return (nc.ui.so.salequotation.action.SalequoEditAction)context.get("editAction");
  nc.ui.so.salequotation.action.SalequoEditAction bean = new nc.ui.so.salequotation.action.SalequoEditAction();
  context.put("editAction",bean);
  bean.setModel(getManageAppModel());
  bean.setEditor(getBillFormEditor());
  bean.setInterceptor(getShowUpComponentInterceptor_1cfc2bc());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor getShowUpComponentInterceptor_1cfc2bc(){
 if(context.get("nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor#1cfc2bc")!=null)
 return (nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor)context.get("nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor#1cfc2bc");
  nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor bean = new nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor();
  context.put("nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor#1cfc2bc",bean);
  bean.setShowUpComponent(getBillFormEditor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.so.salequotation.action.SalequoDelAction getDeleteAction(){
 if(context.get("deleteAction")!=null)
 return (nc.ui.so.salequotation.action.SalequoDelAction)context.get("deleteAction");
  nc.ui.so.salequotation.action.SalequoDelAction bean = new nc.ui.so.salequotation.action.SalequoDelAction();
  context.put("deleteAction",bean);
  bean.setModel(getManageAppModel());
  bean.setEditor(getBillFormEditor());
  bean.setFilledUpInFlow(true);
  bean.setActionName("DELETE");
  bean.setBillType("4310");
  bean.setValidationService(getPowerdeletevalidservice());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.pub.power.PowerValidateService getPowerdeletevalidservice(){
 if(context.get("powerdeletevalidservice")!=null)
 return (nc.ui.pubapp.pub.power.PowerValidateService)context.get("powerdeletevalidservice");
  nc.ui.pubapp.pub.power.PowerValidateService bean = new nc.ui.pubapp.pub.power.PowerValidateService();
  context.put("powerdeletevalidservice",bean);
  bean.setActionCode("delete");
  bean.setBillCodeFiledName("vbillcode");
  bean.setPermissionCode("4310");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.so.salequotation.action.SalequoCopyProcessor getCopyActionProcessor(){
 if(context.get("copyActionProcessor")!=null)
 return (nc.ui.so.salequotation.action.SalequoCopyProcessor)context.get("copyActionProcessor");
  nc.ui.so.salequotation.action.SalequoCopyProcessor bean = new nc.ui.so.salequotation.action.SalequoCopyProcessor();
  context.put("copyActionProcessor",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.so.salequotation.action.SalequoCopyAction getCopyAction(){
 if(context.get("copyAction")!=null)
 return (nc.ui.so.salequotation.action.SalequoCopyAction)context.get("copyAction");
  nc.ui.so.salequotation.action.SalequoCopyAction bean = new nc.ui.so.salequotation.action.SalequoCopyAction();
  context.put("copyAction",bean);
  bean.setModel(getManageAppModel());
  bean.setEditor(getBillFormEditor());
  bean.setCopyActionProcessor(getCopyActionProcessor());
  bean.setInterceptor(getShowUpComponentInterceptor_f9f1ae());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor getShowUpComponentInterceptor_f9f1ae(){
 if(context.get("nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor#f9f1ae")!=null)
 return (nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor)context.get("nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor#f9f1ae");
  nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor bean = new nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor();
  context.put("nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor#f9f1ae",bean);
  bean.setShowUpComponent(getBillFormEditor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.funcnode.ui.action.GroupAction getCommitMenuAction(){
 if(context.get("commitMenuAction")!=null)
 return (nc.funcnode.ui.action.GroupAction)context.get("commitMenuAction");
  nc.funcnode.ui.action.GroupAction bean = new nc.funcnode.ui.action.GroupAction();
  context.put("commitMenuAction",bean);
  bean.setCode("commitMenuAction");
  bean.setName(getI18nFB_169db1c());
  bean.setActions(getManagedList25());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private java.lang.String getI18nFB_169db1c(){
 if(context.get("nc.ui.uif2.I18nFB#169db1c")!=null)
 return (java.lang.String)context.get("nc.ui.uif2.I18nFB#169db1c");
  nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
    context.put("&nc.ui.uif2.I18nFB#169db1c",bean);  bean.setResDir("common");
  bean.setResId("UC001-0000029");
  bean.setDefaultValue("提交");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
 try {
     Object product = bean.getObject();
    context.put("nc.ui.uif2.I18nFB#169db1c",product);
     return (java.lang.String)product;
}
catch(Exception e) { throw new RuntimeException(e);}}

private List getManagedList25(){  List list = new ArrayList();  list.add(getPfCommitAction());  list.add(getUnCommitAction());  return list;}

public nc.ui.so.salequotation.action.SalequoCommitAction getPfCommitAction(){
 if(context.get("pfCommitAction")!=null)
 return (nc.ui.so.salequotation.action.SalequoCommitAction)context.get("pfCommitAction");
  nc.ui.so.salequotation.action.SalequoCommitAction bean = new nc.ui.so.salequotation.action.SalequoCommitAction();
  context.put("pfCommitAction",bean);
  bean.setModel(getManageAppModel());
  bean.setEditor(getBillFormEditor());
  bean.setFilledUpInFlow(true);
  bean.setPreActionNames(getManagedList26());
  bean.setActionName("SAVE");
  bean.setBillType("4310");
  bean.setValidationService(getPowerCommitValidService());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList26(){  List list = new ArrayList();  list.add("WRITE");  return list;}

public nc.ui.pubapp.pub.power.PowerValidateService getPowerCommitValidService(){
 if(context.get("powerCommitValidService")!=null)
 return (nc.ui.pubapp.pub.power.PowerValidateService)context.get("powerCommitValidService");
  nc.ui.pubapp.pub.power.PowerValidateService bean = new nc.ui.pubapp.pub.power.PowerValidateService();
  context.put("powerCommitValidService",bean);
  bean.setActionCode("commit");
  bean.setBillCodeFiledName("vbillcode");
  bean.setPermissionCode("4310");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.so.salequotation.action.SalequoUnCommitAction getUnCommitAction(){
 if(context.get("unCommitAction")!=null)
 return (nc.ui.so.salequotation.action.SalequoUnCommitAction)context.get("unCommitAction");
  nc.ui.so.salequotation.action.SalequoUnCommitAction bean = new nc.ui.so.salequotation.action.SalequoUnCommitAction();
  context.put("unCommitAction",bean);
  bean.setModel(getManageAppModel());
  bean.setEditor(getBillFormEditor());
  bean.setFilledUpInFlow(true);
  bean.setActionName("UNSAVECOMMIT");
  bean.setBillType("4310");
  bean.setValidationService(getPowerUncommitValidService());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.pub.power.PowerValidateService getPowerUncommitValidService(){
 if(context.get("powerUncommitValidService")!=null)
 return (nc.ui.pubapp.pub.power.PowerValidateService)context.get("powerUncommitValidService");
  nc.ui.pubapp.pub.power.PowerValidateService bean = new nc.ui.pubapp.pub.power.PowerValidateService();
  context.put("powerUncommitValidService",bean);
  bean.setActionCode("uncommit");
  bean.setBillCodeFiledName("vbillcode");
  bean.setPermissionCode("4310");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.so.salequotation.action.SalequoSaveCommitAction getSaveandcommitAction(){
 if(context.get("saveandcommitAction")!=null)
 return (nc.ui.so.salequotation.action.SalequoSaveCommitAction)context.get("saveandcommitAction");
  nc.ui.so.salequotation.action.SalequoSaveCommitAction bean = new nc.ui.so.salequotation.action.SalequoSaveCommitAction(getSaveaction(),getPfCommitAction());  context.put("saveandcommitAction",bean);
  bean.setModel(getManageAppModel());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.validation.CompositeValidation getSaveandCommitValidateService(){
 if(context.get("SaveandCommitValidateService")!=null)
 return (nc.ui.pubapp.uif2app.validation.CompositeValidation)context.get("SaveandCommitValidateService");
  nc.ui.pubapp.uif2app.validation.CompositeValidation bean = new nc.ui.pubapp.uif2app.validation.CompositeValidation();
  context.put("SaveandCommitValidateService",bean);
  bean.setValidators(getManagedList27());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList27(){  List list = new ArrayList();  list.add(getValidateService());  list.add(getPowerCommitValidService());  return list;}

public nc.funcnode.ui.action.GroupAction getAuditMenuAction(){
 if(context.get("auditMenuAction")!=null)
 return (nc.funcnode.ui.action.GroupAction)context.get("auditMenuAction");
  nc.funcnode.ui.action.GroupAction bean = new nc.funcnode.ui.action.GroupAction();
  context.put("auditMenuAction",bean);
  bean.setCode("auditMenuAction");
  bean.setName(getI18nFB_b03505());
  bean.setActions(getManagedList28());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private java.lang.String getI18nFB_b03505(){
 if(context.get("nc.ui.uif2.I18nFB#b03505")!=null)
 return (java.lang.String)context.get("nc.ui.uif2.I18nFB#b03505");
  nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
    context.put("&nc.ui.uif2.I18nFB#b03505",bean);  bean.setResDir("common");
  bean.setResId("UC001-0000027");
  bean.setDefaultValue("审核");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
 try {
     Object product = bean.getObject();
    context.put("nc.ui.uif2.I18nFB#b03505",product);
     return (java.lang.String)product;
}
catch(Exception e) { throw new RuntimeException(e);}}

private List getManagedList28(){  List list = new ArrayList();  list.add(getApproveAction());  list.add(getUnApproveAction());  list.add(getSeparatorAction());  list.add(getApproveInfoAction());  return list;}

public nc.funcnode.ui.action.GroupAction getColseMenuAction(){
 if(context.get("colseMenuAction")!=null)
 return (nc.funcnode.ui.action.GroupAction)context.get("colseMenuAction");
  nc.funcnode.ui.action.GroupAction bean = new nc.funcnode.ui.action.GroupAction();
  context.put("colseMenuAction",bean);
  bean.setCode("colseMenuAction");
  bean.setName(getI18nFB_691b37());
  bean.setActions(getManagedList29());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private java.lang.String getI18nFB_691b37(){
 if(context.get("nc.ui.uif2.I18nFB#691b37")!=null)
 return (java.lang.String)context.get("nc.ui.uif2.I18nFB#691b37");
  nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
    context.put("&nc.ui.uif2.I18nFB#691b37",bean);  bean.setResDir("4006009_0");
  bean.setResId("04006009-0078");
  bean.setDefaultValue("关闭");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
 try {
     Object product = bean.getObject();
    context.put("nc.ui.uif2.I18nFB#691b37",product);
     return (java.lang.String)product;
}
catch(Exception e) { throw new RuntimeException(e);}}

private List getManagedList29(){  List list = new ArrayList();  list.add(getCloseAction());  list.add(getOpenAction());  return list;}

public nc.ui.so.salequotation.action.SaleQuotationFindPriceAction getFindPriceAction(){
 if(context.get("findPriceAction")!=null)
 return (nc.ui.so.salequotation.action.SaleQuotationFindPriceAction)context.get("findPriceAction");
  nc.ui.so.salequotation.action.SaleQuotationFindPriceAction bean = new nc.ui.so.salequotation.action.SaleQuotationFindPriceAction();
  context.put("findPriceAction",bean);
  bean.setModel(getManageAppModel());
  bean.setEditor(getBillFormEditor());
  bean.setFindPriceService(getFindPriceService());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.so.salequotation.action.SalequoApproveAction getApproveAction(){
 if(context.get("approveAction")!=null)
 return (nc.ui.so.salequotation.action.SalequoApproveAction)context.get("approveAction");
  nc.ui.so.salequotation.action.SalequoApproveAction bean = new nc.ui.so.salequotation.action.SalequoApproveAction();
  context.put("approveAction",bean);
  bean.setModel(getManageAppModel());
  bean.setEditor(getBillFormEditor());
  bean.setFilledUpInFlow(true);
  bean.setActionName("APPROVE");
  bean.setBillType("4310");
  bean.setValidationService(getPowerapprovevalidservice());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.pub.power.PowerValidateService getPowerapprovevalidservice(){
 if(context.get("powerapprovevalidservice")!=null)
 return (nc.ui.pubapp.pub.power.PowerValidateService)context.get("powerapprovevalidservice");
  nc.ui.pubapp.pub.power.PowerValidateService bean = new nc.ui.pubapp.pub.power.PowerValidateService();
  context.put("powerapprovevalidservice",bean);
  bean.setActionCode("approve");
  bean.setBillCodeFiledName("vbillcode");
  bean.setPermissionCode("4310");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.so.salequotation.action.SalequoUnApproveAction getUnApproveAction(){
 if(context.get("unApproveAction")!=null)
 return (nc.ui.so.salequotation.action.SalequoUnApproveAction)context.get("unApproveAction");
  nc.ui.so.salequotation.action.SalequoUnApproveAction bean = new nc.ui.so.salequotation.action.SalequoUnApproveAction();
  context.put("unApproveAction",bean);
  bean.setEditor(getBillFormEditor());
  bean.setModel(getManageAppModel());
  bean.setFilledUpInFlow(true);
  bean.setActionName("UNAPPROVE");
  bean.setBillType("4310");
  bean.setValidationService(getPowerunapprovevalidservice());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.pub.power.PowerValidateService getPowerunapprovevalidservice(){
 if(context.get("powerunapprovevalidservice")!=null)
 return (nc.ui.pubapp.pub.power.PowerValidateService)context.get("powerunapprovevalidservice");
  nc.ui.pubapp.pub.power.PowerValidateService bean = new nc.ui.pubapp.pub.power.PowerValidateService();
  context.put("powerunapprovevalidservice",bean);
  bean.setActionCode("unapprove");
  bean.setBillCodeFiledName("vbillcode");
  bean.setPermissionCode("4310");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.so.salequotation.action.SalequoOpenAction getOpenAction(){
 if(context.get("openAction")!=null)
 return (nc.ui.so.salequotation.action.SalequoOpenAction)context.get("openAction");
  nc.ui.so.salequotation.action.SalequoOpenAction bean = new nc.ui.so.salequotation.action.SalequoOpenAction();
  context.put("openAction",bean);
  bean.setEditor(getBillFormEditor());
  bean.setModel(getManageAppModel());
  bean.setFilledUpInFlow(false);
  bean.setActionName("OPEN");
  bean.setBillType("4310");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.so.salequotation.action.SalequoCloseAction getCloseAction(){
 if(context.get("closeAction")!=null)
 return (nc.ui.so.salequotation.action.SalequoCloseAction)context.get("closeAction");
  nc.ui.so.salequotation.action.SalequoCloseAction bean = new nc.ui.so.salequotation.action.SalequoCloseAction();
  context.put("closeAction",bean);
  bean.setEditor(getBillFormEditor());
  bean.setModel(getManageAppModel());
  bean.setFilledUpInFlow(false);
  bean.setActionName("CLOSE");
  bean.setBillType("4310");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.actions.RefreshSingleAction getRefreshSingleAction(){
 if(context.get("refreshSingleAction")!=null)
 return (nc.ui.pubapp.uif2app.actions.RefreshSingleAction)context.get("refreshSingleAction");
  nc.ui.pubapp.uif2app.actions.RefreshSingleAction bean = new nc.ui.pubapp.uif2app.actions.RefreshSingleAction();
  context.put("refreshSingleAction",bean);
  bean.setModel(getManageAppModel());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.query2.action.DefaultRefreshAction getRefreshAllAction(){
 if(context.get("refreshAllAction")!=null)
 return (nc.ui.pubapp.uif2app.query2.action.DefaultRefreshAction)context.get("refreshAllAction");
  nc.ui.pubapp.uif2app.query2.action.DefaultRefreshAction bean = new nc.ui.pubapp.uif2app.query2.action.DefaultRefreshAction();
  context.put("refreshAllAction",bean);
  bean.setModel(getManageAppModel());
  bean.setDataManager(getModelDataManager());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.scmpub.action.SCMPrintCountQueryAction getPrintCountQueryAction(){
 if(context.get("printCountQueryAction")!=null)
 return (nc.ui.scmpub.action.SCMPrintCountQueryAction)context.get("printCountQueryAction");
  nc.ui.scmpub.action.SCMPrintCountQueryAction bean = new nc.ui.scmpub.action.SCMPrintCountQueryAction();
  context.put("printCountQueryAction",bean);
  bean.setModel(getManageAppModel());
  bean.setBilldateFieldName("dbilldate");
  bean.setBillType("4310");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.funcnode.ui.action.GroupAction getPrintManageActionGroup(){
 if(context.get("printManageActionGroup")!=null)
 return (nc.funcnode.ui.action.GroupAction)context.get("printManageActionGroup");
  nc.funcnode.ui.action.GroupAction bean = new nc.funcnode.ui.action.GroupAction();
  context.put("printManageActionGroup",bean);
  bean.setCode("printManageActionGroup");
  bean.setName(getI18nFB_79c259());
  bean.setActions(getManagedList30());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private java.lang.String getI18nFB_79c259(){
 if(context.get("nc.ui.uif2.I18nFB#79c259")!=null)
 return (java.lang.String)context.get("nc.ui.uif2.I18nFB#79c259");
  nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
    context.put("&nc.ui.uif2.I18nFB#79c259",bean);  bean.setResDir("common");
  bean.setResId("UC001-0000007");
  bean.setDefaultValue("打印");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
 try {
     Object product = bean.getObject();
    context.put("nc.ui.uif2.I18nFB#79c259",product);
     return (java.lang.String)product;
}
catch(Exception e) { throw new RuntimeException(e);}}

private List getManagedList30(){  List list = new ArrayList();  list.add(getPrintaction());  list.add(getPrintpreviewaction());  list.add(getOutputAction());  list.add(getPrintCountQueryAction());  return list;}

public nc.ui.pubapp.uif2app.actions.OutputAction getOutputAction(){
 if(context.get("outputAction")!=null)
 return (nc.ui.pubapp.uif2app.actions.OutputAction)context.get("outputAction");
  nc.ui.pubapp.uif2app.actions.OutputAction bean = new nc.ui.pubapp.uif2app.actions.OutputAction();
  context.put("outputAction",bean);
  bean.setModel(getManageAppModel());
  bean.setParent(getBillFormEditor());
  bean.setBeforePrintDataProcess(getPrintProcessor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.actions.MetaDataBasedPrintAction getPrintpreviewaction(){
 if(context.get("printpreviewaction")!=null)
 return (nc.ui.pubapp.uif2app.actions.MetaDataBasedPrintAction)context.get("printpreviewaction");
  nc.ui.pubapp.uif2app.actions.MetaDataBasedPrintAction bean = new nc.ui.pubapp.uif2app.actions.MetaDataBasedPrintAction();
  context.put("printpreviewaction",bean);
  bean.setPreview(true);
  bean.setModel(getManageAppModel());
  bean.setBeforePrintDataProcess(getPrintProcessor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.actions.MetaDataBasedPrintAction getPrintaction(){
 if(context.get("printaction")!=null)
 return (nc.ui.pubapp.uif2app.actions.MetaDataBasedPrintAction)context.get("printaction");
  nc.ui.pubapp.uif2app.actions.MetaDataBasedPrintAction bean = new nc.ui.pubapp.uif2app.actions.MetaDataBasedPrintAction();
  context.put("printaction",bean);
  bean.setPreview(false);
  bean.setModel(getManageAppModel());
  bean.setBeforePrintDataProcess(getPrintProcessor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.so.salequotation.action.SaleQuotationPrintProcessor getPrintProcessor(){
 if(context.get("printProcessor")!=null)
 return (nc.ui.so.salequotation.action.SaleQuotationPrintProcessor)context.get("printProcessor");
  nc.ui.so.salequotation.action.SaleQuotationPrintProcessor bean = new nc.ui.so.salequotation.action.SaleQuotationPrintProcessor();
  context.put("printProcessor",bean);
  bean.setModel(getManageAppModel());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.so.salequotation.action.SalequoHisQryAction getSalequoHisAction(){
 if(context.get("salequoHisAction")!=null)
 return (nc.ui.so.salequotation.action.SalequoHisQryAction)context.get("salequoHisAction");
  nc.ui.so.salequotation.action.SalequoHisQryAction bean = new nc.ui.so.salequotation.action.SalequoHisQryAction();
  context.put("salequoHisAction",bean);
  bean.setModel(getManageAppModel());
  bean.setFindPriceService(getFindPriceService());
  bean.setEditor(getBillFormEditor());
  bean.setList(getList());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.funcnode.ui.action.MenuAction getAssistMenuAction(){
 if(context.get("assistMenuAction")!=null)
 return (nc.funcnode.ui.action.MenuAction)context.get("assistMenuAction");
  nc.funcnode.ui.action.MenuAction bean = new nc.funcnode.ui.action.MenuAction();
  context.put("assistMenuAction",bean);
  bean.setCode("assistMenuAction");
  bean.setName(getI18nFB_1c6614d());
  bean.setActions(getManagedList31());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private java.lang.String getI18nFB_1c6614d(){
 if(context.get("nc.ui.uif2.I18nFB#1c6614d")!=null)
 return (java.lang.String)context.get("nc.ui.uif2.I18nFB#1c6614d");
  nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
    context.put("&nc.ui.uif2.I18nFB#1c6614d",bean);  bean.setResDir("4006009_0");
  bean.setResId("04006009-0068");
  bean.setDefaultValue("辅助功能");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
 try {
     Object product = bean.getObject();
    context.put("nc.ui.uif2.I18nFB#1c6614d",product);
     return (java.lang.String)product;
}
catch(Exception e) { throw new RuntimeException(e);}}

private List getManagedList31(){  List list = new ArrayList();  list.add(getDocMngAction());  return list;}

public nc.ui.so.pub.actions.SOManageDocumentAction getDocMngAction(){
 if(context.get("docMngAction")!=null)
 return (nc.ui.so.pub.actions.SOManageDocumentAction)context.get("docMngAction");
  nc.ui.so.pub.actions.SOManageDocumentAction bean = new nc.ui.so.pub.actions.SOManageDocumentAction();
  context.put("docMngAction",bean);
  bean.setModel(getManageAppModel());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.funcnode.ui.action.MenuAction getLinkQueryActionGroup(){
 if(context.get("linkQueryActionGroup")!=null)
 return (nc.funcnode.ui.action.MenuAction)context.get("linkQueryActionGroup");
  nc.funcnode.ui.action.MenuAction bean = new nc.funcnode.ui.action.MenuAction();
  context.put("linkQueryActionGroup",bean);
  bean.setCode("assisFuncQuery");
  bean.setName(getI18nFB_11e23dd());
  bean.setActions(getManagedList32());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private java.lang.String getI18nFB_11e23dd(){
 if(context.get("nc.ui.uif2.I18nFB#11e23dd")!=null)
 return (java.lang.String)context.get("nc.ui.uif2.I18nFB#11e23dd");
  nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
    context.put("&nc.ui.uif2.I18nFB#11e23dd",bean);  bean.setResDir("4006009_0");
  bean.setResId("04006009-0069");
  bean.setDefaultValue("联查");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
 try {
     Object product = bean.getObject();
    context.put("nc.ui.uif2.I18nFB#11e23dd",product);
     return (java.lang.String)product;
}
catch(Exception e) { throw new RuntimeException(e);}}

private List getManagedList32(){  List list = new ArrayList();  list.add(getLinkQueryAction());  list.add(getCustInfoAction());  list.add(getQueryCreditAction());  list.add(getSalequoHisAction());  list.add(getPriceformAction());  return list;}

public nc.ui.pubapp.uif2app.actions.pflow.PFApproveStatusInfoAction getApproveInfoAction(){
 if(context.get("approveInfoAction")!=null)
 return (nc.ui.pubapp.uif2app.actions.pflow.PFApproveStatusInfoAction)context.get("approveInfoAction");
  nc.ui.pubapp.uif2app.actions.pflow.PFApproveStatusInfoAction bean = new nc.ui.pubapp.uif2app.actions.pflow.PFApproveStatusInfoAction();
  context.put("approveInfoAction",bean);
  bean.setModel(getManageAppModel());
  bean.setEditor(getBillFormEditor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.scmmm.ui.uif2.actions.SCMLinkQueryAction getLinkQueryAction(){
 if(context.get("linkQueryAction")!=null)
 return (nc.scmmm.ui.uif2.actions.SCMLinkQueryAction)context.get("linkQueryAction");
  nc.scmmm.ui.uif2.actions.SCMLinkQueryAction bean = new nc.scmmm.ui.uif2.actions.SCMLinkQueryAction();
  context.put("linkQueryAction",bean);
  bean.setModel(getManageAppModel());
  bean.setBillType("4310");
  bean.setOpenMode(1);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.so.salequotation.action.SalequoCustInfoAction getCustInfoAction(){
 if(context.get("custInfoAction")!=null)
 return (nc.ui.so.salequotation.action.SalequoCustInfoAction)context.get("custInfoAction");
  nc.ui.so.salequotation.action.SalequoCustInfoAction bean = new nc.ui.so.salequotation.action.SalequoCustInfoAction();
  context.put("custInfoAction",bean);
  bean.setModel(getManageAppModel());
  bean.setEditor(getBillFormEditor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.so.salequotation.action.QueryCreditAction getQueryCreditAction(){
 if(context.get("queryCreditAction")!=null)
 return (nc.ui.so.salequotation.action.QueryCreditAction)context.get("queryCreditAction");
  nc.ui.so.salequotation.action.QueryCreditAction bean = new nc.ui.so.salequotation.action.QueryCreditAction();
  context.put("queryCreditAction",bean);
  bean.setModel(getManageAppModel());
  bean.setEditor(getBillFormEditor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.so.salequotation.action.SalequoSaveAction getSaveaction(){
 if(context.get("saveaction")!=null)
 return (nc.ui.so.salequotation.action.SalequoSaveAction)context.get("saveaction");
  nc.ui.so.salequotation.action.SalequoSaveAction bean = new nc.ui.so.salequotation.action.SalequoSaveAction();
  context.put("saveaction",bean);
  bean.setEditor(getBillFormEditor());
  bean.setModel(getManageAppModel());
  bean.setActionName("WRITE");
  bean.setBillType("4310");
  bean.setValidationService(getCompositevalidateService());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.validation.CompositeValidation getCompositevalidateService(){
 if(context.get("compositevalidateService")!=null)
 return (nc.ui.pubapp.uif2app.validation.CompositeValidation)context.get("compositevalidateService");
  nc.ui.pubapp.uif2app.validation.CompositeValidation bean = new nc.ui.pubapp.uif2app.validation.CompositeValidation();
  context.put("compositevalidateService",bean);
  bean.setValidators(getManagedList33());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList33(){  List list = new ArrayList();  list.add(getValidateService());  return list;}

public nc.ui.pubapp.uif2app.actions.CancelAction getCancelaction(){
 if(context.get("cancelaction")!=null)
 return (nc.ui.pubapp.uif2app.actions.CancelAction)context.get("cancelaction");
  nc.ui.pubapp.uif2app.actions.CancelAction bean = new nc.ui.pubapp.uif2app.actions.CancelAction();
  context.put("cancelaction",bean);
  bean.setModel(getManageAppModel());
  bean.setEditor(getBillFormEditor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.so.salequotation.action.SalequoPriceFormAction getPriceformAction(){
 if(context.get("priceformAction")!=null)
 return (nc.ui.so.salequotation.action.SalequoPriceFormAction)context.get("priceformAction");
  nc.ui.so.salequotation.action.SalequoPriceFormAction bean = new nc.ui.so.salequotation.action.SalequoPriceFormAction();
  context.put("priceformAction",bean);
  bean.setModel(getManageAppModel());
  bean.setEditor(getBillFormEditor());
  bean.setListview(getList());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.view.TemplateContainer getTemplateContainer(){
 if(context.get("templateContainer")!=null)
 return (nc.ui.pubapp.uif2app.view.TemplateContainer)context.get("templateContainer");
  nc.ui.pubapp.uif2app.view.TemplateContainer bean = new nc.ui.pubapp.uif2app.view.TemplateContainer();
  context.put("templateContainer",bean);
  bean.setContext(getContext());
  bean.setBillTemplateMender(getTrantypeTempMender());
  bean.load();
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.uif2.editor.QueryTemplateContainer getQueryTemplateContainer(){
 if(context.get("queryTemplateContainer")!=null)
 return (nc.ui.uif2.editor.QueryTemplateContainer)context.get("queryTemplateContainer");
  nc.ui.uif2.editor.QueryTemplateContainer bean = new nc.ui.uif2.editor.QueryTemplateContainer();
  context.put("queryTemplateContainer",bean);
  bean.setContext(getContext());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.actions.PfAddInfoLoader getPfAddInfoLoader(){
 if(context.get("pfAddInfoLoader")!=null)
 return (nc.ui.pubapp.uif2app.actions.PfAddInfoLoader)context.get("pfAddInfoLoader");
  nc.ui.pubapp.uif2app.actions.PfAddInfoLoader bean = new nc.ui.pubapp.uif2app.actions.PfAddInfoLoader();
  context.put("pfAddInfoLoader",bean);
  bean.setBillType("30");
  bean.setModel(getManageAppModel());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.uif2.editor.UIF2RemoteCallCombinatorCaller getRemoteCallCombinatorCaller(){
 if(context.get("remoteCallCombinatorCaller")!=null)
 return (nc.ui.uif2.editor.UIF2RemoteCallCombinatorCaller)context.get("remoteCallCombinatorCaller");
  nc.ui.uif2.editor.UIF2RemoteCallCombinatorCaller bean = new nc.ui.uif2.editor.UIF2RemoteCallCombinatorCaller();
  context.put("remoteCallCombinatorCaller",bean);
  bean.setRemoteCallers(getManagedList34());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList34(){  List list = new ArrayList();  list.add(getQueryTemplateContainer());  list.add(getTemplateContainer());  list.add(getUserdefitemContainer());  list.add(getPfAddInfoLoader());  return list;}

}
