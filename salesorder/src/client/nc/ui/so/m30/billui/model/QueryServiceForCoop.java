package nc.ui.so.m30.billui.model;

import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m30.entity.SaleOrderVO;

import nc.itf.so.m30.self.ISaleOrderMaintain;

import nc.bs.framework.common.NCLocator;

import nc.ui.pubapp.uif2app.query2.model.IRefQueryService;
import nc.ui.querytemplate.querytree.IQueryScheme;

public class QueryServiceForCoop implements IRefQueryService {

  @Override
  public Object[] queryByQueryScheme(IQueryScheme queryScheme) throws Exception {
    return null;
  }

  public Object[] queryByQueryScheme(IQueryScheme queryScheme, String pk_puorg) {
    SaleOrderVO[] rets = null;
    ISaleOrderMaintain service =
        NCLocator.getInstance().lookup(ISaleOrderMaintain.class);
    try {
      rets = service.querySaleOrderForCoop(queryScheme, pk_puorg);
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
