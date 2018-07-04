package nc.bs.so.m33.pub;

import java.util.HashMap;
import java.util.Map;

import nc.itf.so.m33.ref.so.m30.SOSaleOrderServicesUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderViewVO;

public class QuerySaleOrderEndInfoBP {

  /**
   * 查询销售订单结算关闭信息
   * 
   * @param ordbids
   * @return <订单bid,[0]是否应收结算关闭、[1]是否成本结算关闭>
   */
  public Map<String, UFBoolean[]> querySaleOrderEndInfo(String[] ordbids) {
    Map<String, UFBoolean[]> map = new HashMap<String, UFBoolean[]>();
    try {
      SaleOrderViewVO[] views =
          SOSaleOrderServicesUtil.querySaleOrderViewVOs(ordbids, new String[] {
            SaleOrderBVO.CSALEORDERBID, SaleOrderBVO.BBARSETTLEFLAG,
            SaleOrderBVO.BBCOSTSETTLEFLAG
          });
      for (SaleOrderViewVO view : views) {
        String bid = view.getBody().getCsaleorderbid();
        UFBoolean arflag =
            ValueUtils.getUFBoolean(view.getBody().getBbarsettleflag());
        UFBoolean costflag =
            ValueUtils.getUFBoolean(view.getBody().getBbcostsettleflag());
        UFBoolean[] flag = new UFBoolean[] {
          arflag, costflag
        };
        map.put(bid, flag);
      }
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return map;
  }
}
