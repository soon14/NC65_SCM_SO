package nc.itf.so.m33.ref.ic.m4453;

import nc.bs.framework.common.NCLocator;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.pubitf.ic.m4453.m33.IWastageServiceFor33;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class ICM4453ServiceUtil {

  private ICM4453ServiceUtil() {
    super();
  }

  /**
   * 参数： outbids 销售出库单表体id数组
   * 返回值： 来源于此销售出库单的途损单全部审批完成 || 下游无途损单 --------- true
   * 有途损单，但没有全部审批--------------------------------------- false
   * 
   * @param outbids
   * @return
   */
  public static boolean checkWasBillIsApproveByOutBids(String[] outbids) {
	if(!SysInitGroupQuery.isICEnabled()) {
	  return true;
	}
    IWastageServiceFor33 ioutSvr =
        NCLocator.getInstance().lookup(IWastageServiceFor33.class);
    try {
      return ioutSvr.checkWasBillIsApproveByOutBids(outbids);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return true;
  }

  public static String[] queryWastageBidsBySource(String[] bids)
      throws BusinessException {
    if(!SysInitGroupQuery.isICEnabled()) {
	  return new String[bids.length];
	}
    IWastageServiceFor33 ioutSvr =
        NCLocator.getInstance().lookup(IWastageServiceFor33.class);
    return ioutSvr.queryWastageBidsBySource(bids);
  }

}
