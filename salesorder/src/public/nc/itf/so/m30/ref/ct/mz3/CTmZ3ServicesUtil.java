package nc.itf.so.m30.ref.ct.mz3;

import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.pubitf.ct.saledaily.salegather.ISaleQueryForGatherBill;
import nc.pubitf.ct.saledaily.saleorder.ISaleQueryForSaleOrder;
import nc.vo.ct.entity.CtBusinessVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * 合同提供所有服务工具类
 * 
 * @since 6.0
 * @version 2011-5-4 上午11:43:39
 * @author 刘志伟
 */
public class CTmZ3ServicesUtil {

  /**
   * 根据合同bids取合同业务信息VO{物料控制方式、物料基本分类ID。。。}
   */
  public static Map<String, CtBusinessVO> queryCtBusinessByPks(String[] ctbids)
      throws BusinessException {
    ISaleQueryForSaleOrder service =
        NCLocator.getInstance().lookup(ISaleQueryForSaleOrder.class);
    Map<String, CtBusinessVO> retMap = null;
    try {
      retMap = service.queryCtBusinessByPksFor30(ctbids);
    }
    catch (BusinessException e) {
      ExceptionUtils.marsh(e);
    }
    return retMap;
  }

  /**
   * 根据销售合同行id查询上游销售合同的单据日期
   * 
   * @param 参数：String[] bids --- 销售合同行id数组
   * @return Map<String,UFDate> --- <销售合同行id,UFDate:销售合同的单据日期>
   * @throws BusinessException
   */
  public static Map<String, UFDate> queryValidateDayForGatherBill(String[] bids)
      throws BusinessException {
    ISaleQueryForGatherBill service =
        NCLocator.getInstance().lookup(ISaleQueryForGatherBill.class);
    return service.queryValidateDayForGatherBill(bids);
  }

}
