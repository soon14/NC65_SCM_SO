package nc.itf.so.m33.ref.ic.m4c;

import java.util.HashMap;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.pubitf.ic.m4c.I4CQueryPubService;
import nc.pubitf.ic.m4c.m33.IRewrite4CFor33;
import nc.pubitf.ic.m4c.m33.IRewrite4CPriceFor33;
import nc.pubitf.ic.m4c.m33.ISaleOutQueryFor33;
import nc.pubitf.ic.m4c.m33.Parameter4CFor33;
import nc.vo.ic.m4c.entity.SaleOutBodyVO;
import nc.vo.ic.m4c.entity.SaleOutViewVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class ICM4CServiceUtil {

  private ICM4CServiceUtil() {
    super();
  }

  /**
   * 参数： outbids 销售出库单表体id数组
   * 返回值： 来源于此销售出库单的退回的销售出库单（包括销售出库单直接退回，和途损单退回的）全部签字完成
   * || 下游无退回销售出库单 -------- true
   * 有退回销售出库单，但没有全部签字--false
   * 
   * @param outbids
   * @return
   */
  public static boolean checkSaleOutIsSignByOutBids(String[] outbids) {
    ISaleOutQueryFor33 ioutSvr =
        NCLocator.getInstance().lookup(ISaleOutQueryFor33.class);
    try {
      return ioutSvr.checkSaleOutIsSignByOutBids(outbids);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return true;
  }

  /**
   * 根据销售出库单行id查询销售出库单表体出库业务日期、签字日期
   * 
   * @param bids
   * @return
   * @throws BusinessException
   */
  public static Map<String, UFDate[]> queryBizSignDateByBids(String[] bids)
      throws BusinessException {
    I4CQueryPubService bo =
        NCLocator.getInstance().lookup(I4CQueryPubService.class);
    return bo.queryBizSignDateByBids(bids);
  }

  /**
   * 根据销售发票行id查询销售出库单表体出库业务日期、签字日期
   * 
   * @param bids
   * @return
   * @throws BusinessException
   */
  public static Map<String, UFDate[]> queryBizSignDateByInvoiceBids(
      String[] bids) throws BusinessException {
    I4CQueryPubService bo =
        NCLocator.getInstance().lookup(I4CQueryPubService.class);
    return bo.queryBizSignDateByInvoiceBids(bids);
  }

  /**
   * 销售销售出库单表体信息
   * 
   * @param bids
   * @param queryItems
   * @return
   * @throws BusinessException
   */
  public static Map<String, SaleOutBodyVO> queryBodyItems(String[] bids,
      String[] queryItems) {
    I4CQueryPubService ioutSvr =
        NCLocator.getInstance().lookup(I4CQueryPubService.class);
    try {
      return ioutSvr.queryBodyItems(bids, queryItems);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return new HashMap<String, SaleOutBodyVO>();
  }

  public static String[] queryRetBidsBySource(String[] bids)
      throws BusinessException {
    ISaleOutQueryFor33 ioutSvr =
        NCLocator.getInstance().lookup(ISaleOutQueryFor33.class);
    return ioutSvr.queryRedBidsBySource(bids);
  }

  /**
   * 根据销售出库单表体行id数组，取表体累计开票数量,累计途损数量,累计出库对冲数量,累计签收数量
   * 
   * @param bids
   * @return
   * @throws BusinessException
   */
  public static SaleOutBodyVO[] queryTotalNumBy4CBids(String[] bids)
      throws BusinessException {
    SaleOutBodyVO[] bvos = null;
    ISaleOutQueryFor33 bo =
        NCLocator.getInstance().lookup(ISaleOutQueryFor33.class);
    bvos = bo.query4CNumFor33(bids);
    return bvos;
  }

  /**
   * 根据销售出库单表体id查询销售出库单视图vo
   * 
   * @param outbids
   * @return
   * @throws BusinessException
   */
  public static SaleOutViewVO[] queryViewVOsByBids(String[] outbids)
      throws BusinessException {
    ISaleOutQueryFor33 iqry =
        NCLocator.getInstance().lookup(ISaleOutQueryFor33.class);
    return iqry.queryViewVOsByBids(outbids);
  }

  /**
   * 回写销售出库单原币含税净价，同时用原币含税净价计算销售出库单行单价金额信息，最后保存
   * 
   * @param paraMap <销售出库单行ID,原币含税净价>
   */
  public static void renovatePrice(Map<String, UFDouble> paraMap) {
    IRewrite4CPriceFor33 ioutSvr =
        NCLocator.getInstance().lookup(IRewrite4CPriceFor33.class);
    try {
      ioutSvr.renovatePriceFor33(paraMap);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

  /**
   * 回写销售出库单累计出库对冲数量
   * 
   * @param bids
   * @return
   * @throws BusinessException
   */
  public static void rewrite4CRushFor33(Parameter4CFor33[] paras)
      throws BusinessException {
    IRewrite4CFor33 bo = NCLocator.getInstance().lookup(IRewrite4CFor33.class);
    bo.rewrite4CRushNumFor33(paras);
  }

}
