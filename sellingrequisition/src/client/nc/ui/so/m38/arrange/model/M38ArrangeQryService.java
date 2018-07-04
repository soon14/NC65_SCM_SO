package nc.ui.so.m38.arrange.model;

import nc.bs.framework.common.NCLocator;
import nc.itf.so.m38.arrange.IPreOrderArrange;
import nc.ui.pubapp.uif2app.query2.model.IQueryService;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m38.entity.PreOrderViewVO;

public class M38ArrangeQryService implements IQueryService {

  @Override
  public Object[] queryByQueryScheme(IQueryScheme queryScheme) throws Exception {
    PreOrderViewVO[] bills = null;
    IPreOrderArrange service =
        NCLocator.getInstance().lookup(IPreOrderArrange.class);
    try {
      bills = service.queryPreOrderViewVO(queryScheme);
    }
    catch (Exception e) {

      ExceptionUtils.wrappException(e);
    }
    return bills;
  }
}
