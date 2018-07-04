package nc.ui.so.m30.billui.model;

import nc.bs.framework.common.NCLocator;
import nc.itf.so.m30.self.ISaleOrderMaintain;
import nc.ui.pubapp.uif2app.query2.model.IRefQueryService;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m30.entity.SaleOrderVO;

/**
 * 销售订单为采购订单提供的查询服务
 * 
 * @since 6.0
 * @version 2011-12-2 上午08:58:46
 * @author fengjb
 */
public class QueryServiceFor21 implements IRefQueryService {

  @Override
  public Object[] queryByQueryScheme(IQueryScheme queryScheme) throws Exception {
    SaleOrderVO[] rets = null;
    ISaleOrderMaintain service =
        NCLocator.getInstance().lookup(ISaleOrderMaintain.class);
    try {
      rets = service.querySaleOrderFor21(queryScheme);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return rets;
  }

  @Override
  public SaleOrderVO[] queryByWhereSql(String whereSql) throws Exception {
    return null;
  }
}
