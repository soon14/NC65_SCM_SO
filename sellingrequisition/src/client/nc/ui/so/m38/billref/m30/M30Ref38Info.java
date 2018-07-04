package nc.ui.so.m38.billref.m30;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.ui.uif2.factory.AbstractJavaBeanDefinition;

public class M30Ref38Info extends AbstractJavaBeanDefinition{
	private Map<String, Object> context = new HashMap();
public nc.ui.pubapp.billref.src.RefInfo getRefInfo(){
 if(context.get("refInfo")!=null)
 return (nc.ui.pubapp.billref.src.RefInfo)context.get("refInfo");
  nc.ui.pubapp.billref.src.RefInfo bean = new nc.ui.pubapp.billref.src.RefInfo();
  context.put("refInfo",bean);
  bean.setUserdefitemListPreparator(getUserdefitemListPreparator());
  bean.setSingleUserdefitemListPreparator(getSingleUserdefitemListPreparator());
  bean.setHeadVO(getPreOrderHVO_81f9a5());
  bean.setBodyVO(getPreOrderBVO_12cc0ec());
  bean.setBillVO(getPreOrderVO_12dd7fe());
  bean.setViewVO(getPreOrderViewVO_bf8d4e());
  bean.setBillNodeKey("38to30");
  bean.setBillViewNodeKey("38to30view");
  bean.setSourcevoPkName("csourcebillhid");
  bean.setSourcevoBillNOName("vsourcebillcode");
  bean.setQueryService(getPreOrderQueryServiceFor30_1d62238());
  bean.setRefPanelInit(getM30Ref38UIInit_160bec3());
return bean;
}

public nc.vo.so.m38.entity.PreOrderHVO getPreOrderHVO_81f9a5(){
 if(context.get("nc.vo.so.m38.entity.PreOrderHVO#81f9a5")!=null)
 return (nc.vo.so.m38.entity.PreOrderHVO)context.get("nc.vo.so.m38.entity.PreOrderHVO#81f9a5");
  nc.vo.so.m38.entity.PreOrderHVO bean = new nc.vo.so.m38.entity.PreOrderHVO();
  context.put("nc.vo.so.m38.entity.PreOrderHVO#81f9a5",bean);
return bean;
}

public nc.vo.so.m38.entity.PreOrderBVO getPreOrderBVO_12cc0ec(){
 if(context.get("nc.vo.so.m38.entity.PreOrderBVO#12cc0ec")!=null)
 return (nc.vo.so.m38.entity.PreOrderBVO)context.get("nc.vo.so.m38.entity.PreOrderBVO#12cc0ec");
  nc.vo.so.m38.entity.PreOrderBVO bean = new nc.vo.so.m38.entity.PreOrderBVO();
  context.put("nc.vo.so.m38.entity.PreOrderBVO#12cc0ec",bean);
return bean;
}

public nc.vo.so.m38.entity.PreOrderVO getPreOrderVO_12dd7fe(){
 if(context.get("nc.vo.so.m38.entity.PreOrderVO#12dd7fe")!=null)
 return (nc.vo.so.m38.entity.PreOrderVO)context.get("nc.vo.so.m38.entity.PreOrderVO#12dd7fe");
  nc.vo.so.m38.entity.PreOrderVO bean = new nc.vo.so.m38.entity.PreOrderVO();
  context.put("nc.vo.so.m38.entity.PreOrderVO#12dd7fe",bean);
return bean;
}

public nc.vo.so.m38.entity.PreOrderViewVO getPreOrderViewVO_bf8d4e(){
 if(context.get("nc.vo.so.m38.entity.PreOrderViewVO#bf8d4e")!=null)
 return (nc.vo.so.m38.entity.PreOrderViewVO)context.get("nc.vo.so.m38.entity.PreOrderViewVO#bf8d4e");
  nc.vo.so.m38.entity.PreOrderViewVO bean = new nc.vo.so.m38.entity.PreOrderViewVO();
  context.put("nc.vo.so.m38.entity.PreOrderViewVO#bf8d4e",bean);
return bean;
}

public nc.ui.so.m38.billui.model.PreOrderQueryServiceFor30 getPreOrderQueryServiceFor30_1d62238(){
 if(context.get("nc.ui.so.m38.billui.model.PreOrderQueryServiceFor30#1d62238")!=null)
 return (nc.ui.so.m38.billui.model.PreOrderQueryServiceFor30)context.get("nc.ui.so.m38.billui.model.PreOrderQueryServiceFor30#1d62238");
  nc.ui.so.m38.billui.model.PreOrderQueryServiceFor30 bean = new nc.ui.so.m38.billui.model.PreOrderQueryServiceFor30();
  context.put("nc.ui.so.m38.billui.model.PreOrderQueryServiceFor30#1d62238",bean);
return bean;
}

public nc.ui.so.m38.billref.m30.M30Ref38UIInit getM30Ref38UIInit_160bec3(){
 if(context.get("nc.ui.so.m38.billref.m30.M30Ref38UIInit#160bec3")!=null)
 return (nc.ui.so.m38.billref.m30.M30Ref38UIInit)context.get("nc.ui.so.m38.billref.m30.M30Ref38UIInit#160bec3");
  nc.ui.so.m38.billref.m30.M30Ref38UIInit bean = new nc.ui.so.m38.billref.m30.M30Ref38UIInit();
  context.put("nc.ui.so.m38.billref.m30.M30Ref38UIInit#160bec3",bean);
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

public List getManagedList2(){  List list = new ArrayList();  list.add(getUserdefQueryParam_8c1be5());  list.add(getUserdefQueryParam_7b5ccb());  return list;}

public nc.ui.uif2.editor.UserdefQueryParam getUserdefQueryParam_8c1be5(){
 if(context.get("nc.ui.uif2.editor.UserdefQueryParam#8c1be5")!=null)
 return (nc.ui.uif2.editor.UserdefQueryParam)context.get("nc.ui.uif2.editor.UserdefQueryParam#8c1be5");
  nc.ui.uif2.editor.UserdefQueryParam bean = new nc.ui.uif2.editor.UserdefQueryParam();
  context.put("nc.ui.uif2.editor.UserdefQueryParam#8c1be5",bean);
  bean.setRulecode("38_H");
  bean.setPos(0);
  bean.setPrefix("vdef");
return bean;
}

public nc.ui.uif2.editor.UserdefQueryParam getUserdefQueryParam_7b5ccb(){
 if(context.get("nc.ui.uif2.editor.UserdefQueryParam#7b5ccb")!=null)
 return (nc.ui.uif2.editor.UserdefQueryParam)context.get("nc.ui.uif2.editor.UserdefQueryParam#7b5ccb");
  nc.ui.uif2.editor.UserdefQueryParam bean = new nc.ui.uif2.editor.UserdefQueryParam();
  context.put("nc.ui.uif2.editor.UserdefQueryParam#7b5ccb",bean);
  bean.setRulecode("38_B");
  bean.setPos(1);
  bean.setPrefix("vbdef");
  bean.setTabcode("body");
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

public List getManagedList3(){  List list = new ArrayList();  list.add(getUserdefQueryParam_1094f2b());  list.add(getUserdefQueryParam_2cb896());  return list;}

public nc.ui.uif2.editor.UserdefQueryParam getUserdefQueryParam_1094f2b(){
 if(context.get("nc.ui.uif2.editor.UserdefQueryParam#1094f2b")!=null)
 return (nc.ui.uif2.editor.UserdefQueryParam)context.get("nc.ui.uif2.editor.UserdefQueryParam#1094f2b");
  nc.ui.uif2.editor.UserdefQueryParam bean = new nc.ui.uif2.editor.UserdefQueryParam();
  context.put("nc.ui.uif2.editor.UserdefQueryParam#1094f2b",bean);
  bean.setRulecode("38_H");
  bean.setPos(0);
  bean.setPrefix("vdef");
return bean;
}

public nc.ui.uif2.editor.UserdefQueryParam getUserdefQueryParam_2cb896(){
 if(context.get("nc.ui.uif2.editor.UserdefQueryParam#2cb896")!=null)
 return (nc.ui.uif2.editor.UserdefQueryParam)context.get("nc.ui.uif2.editor.UserdefQueryParam#2cb896");
  nc.ui.uif2.editor.UserdefQueryParam bean = new nc.ui.uif2.editor.UserdefQueryParam();
  context.put("nc.ui.uif2.editor.UserdefQueryParam#2cb896",bean);
  bean.setRulecode("38_B");
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

public List getManagedList4(){  List list = new ArrayList();  list.add(getQueryParam_ab755());  list.add(getQueryParam_18a64dd());  list.add(getQueryParam_e4039a());  return list;}

public nc.ui.uif2.userdefitem.QueryParam getQueryParam_ab755(){
 if(context.get("nc.ui.uif2.userdefitem.QueryParam#ab755")!=null)
 return (nc.ui.uif2.userdefitem.QueryParam)context.get("nc.ui.uif2.userdefitem.QueryParam#ab755");
  nc.ui.uif2.userdefitem.QueryParam bean = new nc.ui.uif2.userdefitem.QueryParam();
  context.put("nc.ui.uif2.userdefitem.QueryParam#ab755",bean);
  bean.setRulecode("38_H");
return bean;
}

public nc.ui.uif2.userdefitem.QueryParam getQueryParam_18a64dd(){
 if(context.get("nc.ui.uif2.userdefitem.QueryParam#18a64dd")!=null)
 return (nc.ui.uif2.userdefitem.QueryParam)context.get("nc.ui.uif2.userdefitem.QueryParam#18a64dd");
  nc.ui.uif2.userdefitem.QueryParam bean = new nc.ui.uif2.userdefitem.QueryParam();
  context.put("nc.ui.uif2.userdefitem.QueryParam#18a64dd",bean);
  bean.setRulecode("38_B");
return bean;
}

public nc.ui.uif2.userdefitem.QueryParam getQueryParam_e4039a(){
 if(context.get("nc.ui.uif2.userdefitem.QueryParam#e4039a")!=null)
 return (nc.ui.uif2.userdefitem.QueryParam)context.get("nc.ui.uif2.userdefitem.QueryParam#e4039a");
  nc.ui.uif2.userdefitem.QueryParam bean = new nc.ui.uif2.userdefitem.QueryParam();
  context.put("nc.ui.uif2.userdefitem.QueryParam#e4039a",bean);
  bean.setRulecode("materialassistant");
return bean;
}

}
