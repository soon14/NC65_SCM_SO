/**
 * 
 */
package nc.pubitf.so.m30.sobalance;

import nc.vo.pub.BusinessException;
import nc.vo.so.m30.sobalance.entity.SoBalanceViewVO;

/**
 * @author gdsjw
 *
 */
public interface ISOBalanceQueryForArap {

  /**
   * 查询可核销金额
   * @param paybillrowid 收款单行ID 
   * @param saleorderid 销售订单ID
   * @return
   * @throws BusinessException
   */
  SoBalanceViewVO[] querySoBalanceAccbalmnyForVerify(
      String paybillrowid, String saleorderid) throws BusinessException;

}
