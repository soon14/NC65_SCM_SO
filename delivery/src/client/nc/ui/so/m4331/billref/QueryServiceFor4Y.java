package nc.ui.so.m4331.billref;

import nc.bs.framework.common.NCLocator;
import nc.itf.so.m4331.IDeliveryMaintain;
import nc.ui.pubapp.uif2app.query2.model.IRefQueryService;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m4331.entity.DeliveryVO;

public class QueryServiceFor4Y implements IRefQueryService {

  @Override
  public Object[] queryByQueryScheme(IQueryScheme queryScheme) throws Exception {
    DeliveryVO[] rets = null;
    IDeliveryMaintain service =
        NCLocator.getInstance().lookup(IDeliveryMaintain.class);
    try {
      rets = service.queryDeliveryFor4Y(queryScheme);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return rets;
  }

  @Override
  public Object[] queryByWhereSql(String whereSql) throws Exception {
    return null;
  }

}
