package nc.ui.so.m38.billui.model;

import nc.bs.framework.common.NCLocator;
import nc.itf.so.m38.IPreOrderMaintain;
import nc.ui.pubapp.uif2app.query2.model.IRefQueryService;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m38.entity.PreOrderVO;

public class PreOrderQueryServiceFor30 implements IRefQueryService {

  @Override
  public Object[] queryByQueryScheme(IQueryScheme queryScheme) throws Exception {
    PreOrderVO[] retbills = null;
    IPreOrderMaintain service =
        NCLocator.getInstance().lookup(IPreOrderMaintain.class);
    try {
      retbills = service.queryPreOrderFor30(queryScheme);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return retbills;
  }

  // IRefQueryService不集成IQueryService时可以删除
  @Override
  public Object[] queryByWhereSql(String whereSql) throws Exception {
    PreOrderVO[] rets = null;
    // IPreOrderMaintain service =
    // NCLocator.getInstance().lookup(IPreOrderMaintain.class);
    // try {
    // rets = service.queryPreOrder(whereSql);
    // }
    // catch (BusinessException e) {
    //
    // ExceptionUtils.wrappException(e);
    // }
    return rets;

  }
}
