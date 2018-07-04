package nc.ui.so.m30.closemanage.model;

import nc.bs.framework.common.NCLocator;
import nc.itf.so.m30.closemanage.ISaleOrderCloseManageMaintain;
import nc.ui.pubapp.uif2app.query2.model.IQueryService;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m30.entity.SaleOrderViewVO;

public class SaleOrderCloseManageService implements IQueryService {

  @Override
  public Object[] queryByQueryScheme(IQueryScheme queryScheme) throws Exception {
    SaleOrderViewVO[] rets = null;
    ISaleOrderCloseManageMaintain service =
        NCLocator.getInstance().lookup(ISaleOrderCloseManageMaintain.class);
    try {
      rets = service.querySaleOrderViewVO(queryScheme);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return rets;
  }

}
