package nc.ui.so.salequotation.billref.m30;

import nc.bs.framework.common.NCLocator;
import nc.itf.so.salequotation.ISalequotationQry;
import nc.ui.pubapp.uif2app.query2.model.IRefQueryService;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.salequotation.entity.AggSalequotationHVO;

public class QueryServiceFor30 implements IRefQueryService {

  private ISalequotationQry bsQryService;

  @Override
  public Object[] queryByQueryScheme(IQueryScheme queryScheme) throws Exception {
    AggSalequotationHVO[] rets = null;
    try {
      rets = this.getBSQryService().queryByQuerySchemeFor30(queryScheme);
    }
    catch (BusinessException e) {

      ExceptionUtils.wrappException(e);
    }
    return rets;
  }

  @Override
  public AggSalequotationHVO[] queryByWhereSql(String whereSql)
      throws Exception {
    AggSalequotationHVO[] rets = null;
    return rets;
  }

  private ISalequotationQry getBSQryService() {
    if (this.bsQryService == null) {
      this.bsQryService =
          NCLocator.getInstance().lookup(ISalequotationQry.class);
    }
    return this.bsQryService;
  }
}
