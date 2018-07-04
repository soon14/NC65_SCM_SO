package nc.itf.so.m33.ref.so.m32;

import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.pubitf.so.m32.credit.ISaleInvoiceForCredit;
import nc.pubitf.so.m32.so.m33.IRewrite32For33;
import nc.pubitf.so.m32.so.m33.RewritePara32For33OnVerify;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class SOSaleInvoiceServicesUtil {

  /**
   * 根据发票ID查询单据日期和审核日期
   * 
   * @param bids 销售发票行id数组
   * @return Map<String,UFDate[]> --- <销售发票行id,UFDate[0]:单据日期 UFDate[1]:审核日期>
   * @throws BusinessException
   */
  public static Map<String, UFDate[]> getBusiDateBy32Bids(String[] bids)
      throws BusinessException {
    ISaleInvoiceForCredit bo =
        NCLocator.getInstance().lookup(ISaleInvoiceForCredit.class);
    return bo.getBusiDateBy32Bids(bids);
  }

  /**
   * 根据出库单ID查询单据日期和审核日期
   * 
   * @param bids 销售出库单行id数组
   * @return
   * @throws BusinessException
   */
  public static Map<String, UFDate[]> getBusiDateBy4CBids(String[] bids)
      throws BusinessException {
    ISaleInvoiceForCredit bo =
        NCLocator.getInstance().lookup(ISaleInvoiceForCredit.class);
    return bo.getBusiDateBy4CBids(bids);
  }

  /**
   * 回写销售发票的累计财务核销金额
   * 
   * @param paras
   */
  public static void reWritePaymnyOnVerfy(RewritePara32For33OnVerify[] paras) {
    IRewrite32For33 bo = NCLocator.getInstance().lookup(IRewrite32For33.class);
    try {
      bo.reWritePaymnyOnVerfy(paras);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

}
