package nc.ui.so.m38.billui.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.ui.uif2.factory.AbstractJavaBeanDefinition;

public class preorderMigration_config extends AbstractJavaBeanDefinition{
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

public nc.ui.uif2.editor.TemplateContainer getTemplateContainer(){
 if(context.get("templateContainer")!=null)
 return (nc.ui.uif2.editor.TemplateContainer)context.get("templateContainer");
  nc.ui.uif2.editor.TemplateContainer bean = new nc.ui.uif2.editor.TemplateContainer();
  context.put("templateContainer",bean);
  bean.setContext(getContext());
  bean.load();
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.so.m38.billui.view.PreOrderMigratePanel getPreOrderMigratePanel(){
 if(context.get("preOrderMigratePanel")!=null)
 return (nc.ui.so.m38.billui.view.PreOrderMigratePanel)context.get("preOrderMigratePanel");
  nc.ui.so.m38.billui.view.PreOrderMigratePanel bean = new nc.ui.so.m38.billui.view.PreOrderMigratePanel();
  context.put("preOrderMigratePanel",bean);
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
  bean.setConstraints(getManagedList0());
  bean.setActions(getManagedList1());
  bean.initUI();
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList0(){  List list = new ArrayList();  list.add(getDown());  return list;}

private nc.ui.uif2.tangramlayout.TangramLayoutConstraint getDown(){
 if(context.get("down")!=null)
 return (nc.ui.uif2.tangramlayout.TangramLayoutConstraint)context.get("down");
  nc.ui.uif2.tangramlayout.TangramLayoutConstraint bean = new nc.ui.uif2.tangramlayout.TangramLayoutConstraint();
  context.put("down",bean);
  bean.setNewComponent(getPreOrderMigratePanel());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList1(){  List list = new ArrayList();  list.add(getPreOrderMigrateAction());  return list;}

public nc.ui.so.m38.billui.action.PreOrderMigrateAction getPreOrderMigrateAction(){
 if(context.get("preOrderMigrateAction")!=null)
 return (nc.ui.so.m38.billui.action.PreOrderMigrateAction)context.get("preOrderMigrateAction");
  nc.ui.so.m38.billui.action.PreOrderMigrateAction bean = new nc.ui.so.m38.billui.action.PreOrderMigrateAction();
  context.put("preOrderMigrateAction",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

}
