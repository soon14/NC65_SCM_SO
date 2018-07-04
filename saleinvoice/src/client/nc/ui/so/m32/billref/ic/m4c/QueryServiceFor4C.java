package nc.ui.so.m32.billref.ic.m4c;

import nc.bs.framework.common.NCLocator;
import nc.itf.so.m32.ISaleInvoiceMaintain;
import nc.ui.pubapp.uif2app.query2.model.IRefQueryService;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m32.entity.SaleInvoiceVO;

public class QueryServiceFor4C implements IRefQueryService {

  @Override
  public Object[] queryByQueryScheme(IQueryScheme queryScheme) throws Exception {
    SaleInvoiceVO[] rets = null;
    ISaleInvoiceMaintain service =
        NCLocator.getInstance().lookup(ISaleInvoiceMaintain.class);
    try {
      rets = service.querySaleInvoiceFor4C(queryScheme);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return rets;
  }

  @Override
  public Object[] queryByWhereSql(String whereSql) throws Exception {
    SaleInvoiceVO[] retbills = null;
    ISaleInvoiceMaintain service =
        NCLocator.getInstance().lookup(ISaleInvoiceMaintain.class);
    try {
      retbills = service.querySaleInvoice(whereSql);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return retbills;
  }

}
