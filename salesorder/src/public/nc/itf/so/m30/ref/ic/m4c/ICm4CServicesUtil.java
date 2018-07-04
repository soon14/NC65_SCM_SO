package nc.itf.so.m30.ref.ic.m4c;

import java.util.HashMap;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.pubitf.ic.m4c.I4CQueryPubService;
import nc.pubitf.ic.m4c.m30.ICSaleOutNumInfoVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * IC——销售出库单服务
 * 
 * @since 6.0
 * @version 2011-7-5 上午09:41:12
 * @author 刘志伟
 */
public class ICm4CServicesUtil {

  /**
   * 提供给销售订单管理管理的查数接口，用来查询出库单的签字和未签字数量
   */
  public static Map<String, ICSaleOutNumInfoVO> query4CNumInfoFor30CloseMng(
      String[] orderbids) throws BusinessException {
	if(!SysInitGroupQuery.isICEnabled()) {
		return new HashMap<String, ICSaleOutNumInfoVO>();
	}
    I4CQueryPubService m4CService =
        NCLocator.getInstance().lookup(I4CQueryPubService.class);
    return m4CService.query4CNumInfoFor30CloseMng(orderbids);
  }

  /**
   * 订单下游的出库单是否全部审批
   * 
   * @param orderbids
   * @return Y：审批 N||NULL：没有审批
   */
  public static UFBoolean[] queryIsAllSigned(String[] orderbids) {
	if(!SysInitGroupQuery.isICEnabled()) {
	  return new UFBoolean[orderbids.length];
	}
    UFBoolean[] isapprove = null;
    I4CQueryPubService querysrv =
        NCLocator.getInstance().lookup(I4CQueryPubService.class);
    try {
      isapprove = querysrv.queryIsAllSigned(orderbids);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return isapprove;
  }
}
