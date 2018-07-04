package nc.ui.so.m4331.arrange.model;

import nc.bs.framework.common.NCLocator;
import nc.itf.scmpub.reference.uap.pf.PFConfig;
import nc.pubitf.so.m30.so.m4331.IQueryFor4331Arrange;
import nc.ui.pubapp.uif2app.query2.model.IQueryService;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderViewVO;

public class M30QueryService implements IQueryService {

  @Override
  public Object[] queryByQueryScheme(IQueryScheme queryScheme) throws Exception {
    SaleOrderViewVO[] bills = null;
    IQueryFor4331Arrange querysrv =
        NCLocator.getInstance().lookup(IQueryFor4331Arrange.class);
    try {
      String pk_group = AppContext.getInstance().getPkGroup();
      String[] busis =
          PFConfig.queryBusiTypePks(pk_group, SOBillType.Delivery.getCode());

      queryScheme.put(SaleOrderHVO.CBIZTYPEID, busis);
      bills = querysrv.queryArrangeSaleOrder(queryScheme);
    }
    catch (Exception e) {

      ExceptionUtils.wrappException(e);
    }
    return bills;
  }
}
