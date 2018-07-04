package nc.ui.so.m4331.arrange.model;

import java.util.ArrayList;
import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.itf.scmpub.reference.uap.pf.PFConfig;
import nc.pubitf.to.m5x.so.m4331.IM5XRefQueryFor4331;
import nc.ui.pubapp.uif2app.query2.model.IQueryService;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.to.m5x.entity.BillViewVO;

public class M5XQueryService implements IQueryService {

  @Override
  public Object[] queryByQueryScheme(IQueryScheme queryScheme) throws Exception {

    BillViewVO[] bills = null;
    IM5XRefQueryFor4331 querysrv =
        NCLocator.getInstance().lookup(IM5XRefQueryFor4331.class);
    try {
      String pk_group = AppContext.getInstance().getPkGroup();
      String[] busis =
          PFConfig.queryBusiTypePks(pk_group, SOBillType.Delivery.getCode());
      List<String> list = new ArrayList<String>();
      for (String busi : busis) {
        list.add(busi);
      }
      queryScheme.put("TO_Order_Busi_Send", list);
      bills = querysrv.queryTransOrderFor4331Arrange(queryScheme);

    }
    catch (Exception e) {

      ExceptionUtils.wrappException(e);
    }
    return bills;
  }
}
