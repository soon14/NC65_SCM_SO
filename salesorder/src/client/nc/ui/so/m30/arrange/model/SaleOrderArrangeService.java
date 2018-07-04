package nc.ui.so.m30.arrange.model;

import nc.bs.framework.common.NCLocator;
import nc.itf.so.m30.arrange.IM30ArrangeMaintain;
import nc.ui.pubapp.billref.push.IRewriteService;
import nc.ui.pubapp.billref.push.RewriteInfo;
import nc.ui.pubapp.uif2app.query2.model.IQueryService;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderVO;

public class SaleOrderArrangeService implements IQueryService, IRewriteService {

  private IM30ArrangeMaintain service = NCLocator.getInstance().lookup(
      IM30ArrangeMaintain.class);

  @Override
  public RewriteInfo[] getRewriterInfo(Object[] bill) {
    return null;
  }

  /**
   * 下游单据保存后调用：
   * <p>回写前台安排数量</p>
   */
  @Override
  public void setRewriterNum(Object saleorder, UFDouble num, String bodyId) {
    SaleOrderVO bill = (SaleOrderVO) saleorder;
    SaleOrderBVO[] items = bill.getChildrenVO();
    for (SaleOrderBVO item : items) {
      if (bodyId.equals(item.getCsaleorderbid())) {
        item.setNarrangetoornum(num);
      }
    }
  }

  @Override
  public Object[] queryByQueryScheme(IQueryScheme queryScheme) throws Exception {
    SaleOrderVO[] bills = null;
    try {
      bills = this.service.querySaleOrder(queryScheme);
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }
    return bills;
  }
}
