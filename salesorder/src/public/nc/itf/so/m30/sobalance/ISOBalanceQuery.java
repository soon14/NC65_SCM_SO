/**
 * 
 */
package nc.itf.so.m30.sobalance;

import nc.vo.pub.BusinessException;
import nc.vo.so.m30.sobalance.entity.SoBalanceVO;
import nc.vo.so.m30.sobalance.entity.SoBalanceViewVO;

/**
 * @author gdsjw
 * 
 */
public interface ISOBalanceQuery {
  // 根据订单IDs查询收款核销关系
  SoBalanceVO[] querySoBalanceVOBySaleOrderIDs(String[] saleorderids)
      throws BusinessException;

  // 根据收款单行IDs查询收款核销拉平关系
  SoBalanceViewVO[] querySoBalanceViewByGatheringBillBodyIDs(
      String[] paybillrowids) throws BusinessException;

}
