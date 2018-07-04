package nc.ui.so.m38.billui.model;

import nc.bs.framework.common.NCLocator;
import nc.itf.so.m38.IPreOrderMaintain;
import nc.ui.pubapp.uif2app.query2.model.IQueryService;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.ui.uif2.model.IAppModelService;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m38.entity.PreOrderVO;
import nc.vo.uif2.LoginContext;

public class PreOrderModelService implements IAppModelService, IQueryService {

  @Override
  public void delete(Object object) throws Exception {
    // TODO 自动生成方法存根

  }

  @Override
  public Object insert(Object object) throws Exception {
    // 在saveAction中调用后台服务，此方法无需实现
    return null;
  }

  @Override
  public Object[] queryByDataVisibilitySetting(LoginContext context)
      throws Exception {
    // TODO 自动生成方法存根
    return null;
  }

  @Override
  public Object update(Object object) throws Exception {
    // 在saveAction中调用后台服务，此方法无需实现
    return null;
  }

  @Override
  public Object[] queryByQueryScheme(IQueryScheme queryScheme) throws Exception {

    PreOrderVO[] retbills = null;
    IPreOrderMaintain service =
        NCLocator.getInstance().lookup(IPreOrderMaintain.class);
    try {
      retbills = service.queryPreOrder(queryScheme);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return retbills;

  }

}
