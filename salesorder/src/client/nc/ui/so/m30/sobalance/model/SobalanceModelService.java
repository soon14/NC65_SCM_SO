package nc.ui.so.m30.sobalance.model;

import nc.bs.framework.common.NCLocator;
import nc.itf.so.m30.sobalance.ISOBalanceMaintain;
import nc.ui.pubapp.uif2app.query2.model.IQueryService;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.ui.uif2.model.IAppModelService;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m30.sobalance.entity.SoBalanceVO;
import nc.vo.uif2.LoginContext;

public class SobalanceModelService implements IAppModelService, IQueryService {

  /**
   * 删除
   */
  @Override
  public void delete(Object object) throws Exception {

    // SaleOrderVO bill = (SaleOrderVO) object;
    // SaleOrderVO[] bills = new SaleOrderVO[] { bill };
    // ISaleOrderMaintain bo = (ISaleOrderMaintain) NCLocator.getInstance()
    // .lookup(ISaleOrderMaintain.class);
    // bo.deleteSaleOrder(bills);
    ExceptionUtils.unSupported();
  }

  /**
   * 新增
   */
  @Override
  public SoBalanceVO insert(Object object) throws Exception {
    // SoBalanceVO bill = (SoBalanceVO) object;

    // SaleOrderVO ret = null;
    // ISaleOrderMaintain service = NCLocator.getInstance().lookup(
    // ISaleOrderMaintain.class);
    // try {
    // ret = service.insertSaleOrder(bill);
    // // 后台变化VO与前台合并
    // ClientBillCombinServer util = new ClientBillCombinServer();
    // util.combine(new SaleOrderVO[] { bill }, new SaleOrderVO[] { ret });
    // } catch (BusinessException e) {
    // ExceptionUtils.wrappException(e);
    // }
    ExceptionUtils.unSupported();
    return (SoBalanceVO) object;
  }

  @Override
  public Object[] queryByDataVisibilitySetting(LoginContext context)
      throws Exception {
    return null;
  }

  /**
   * 修改保存
   */
  @Override
  public Object update(Object object) throws Exception {
    // SoBalanceVO bill = (SoBalanceVO) object;

    ExceptionUtils.unSupported();
    return object;
  }

  @Override
  public Object[] queryByQueryScheme(IQueryScheme queryScheme) throws Exception {
    SoBalanceVO[] rets = null;
    ISOBalanceMaintain service =
        NCLocator.getInstance().lookup(ISOBalanceMaintain.class);
    try {
      rets = service.querySoBalanceVO(queryScheme);
    }
    catch (BusinessException e) {

      ExceptionUtils.wrappException(e);
    }
    return rets;
  }
}
