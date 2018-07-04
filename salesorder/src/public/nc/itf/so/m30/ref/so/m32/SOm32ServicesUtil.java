package nc.itf.so.m30.ref.so.m32;

import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.pubitf.so.m32.so.m30.IQuery32For30;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * SO——销售发票服务
 * 
 * @since 6.0
 * @version 2011-8-4 下午08:22:42
 * @author 刘志伟
 */
public class SOm32ServicesUtil {

  /**
   * 获得发票审批数量
   * 
   * @param bids
   * @return
   */
  public static Map<String, UFDouble> getInvoiceApproveNum(String[] ids,
      String[] bids) {
    try {
      IQuery32For30 m32Service =
          NCLocator.getInstance().lookup(IQuery32For30.class);
      return m32Service.getInvoiceApproveNum(ids, bids);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return null;
  }

  /**
   * 订单下游的发票是否全部审批
   * 
   * @param orderbids
   * @return Y：审批 N||NULL：没有审批
   */
  public static UFBoolean[] isInvoiceAllApprove(String[] orderids,
      String[] orderbids) {
    UFBoolean[] isapprove = null;
    IQuery32For30 querysrv =
        NCLocator.getInstance().lookup(IQuery32For30.class);
    try {
      isapprove = querysrv.isInvoiceAllApprove(orderids, orderbids);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return isapprove;
  }
}
