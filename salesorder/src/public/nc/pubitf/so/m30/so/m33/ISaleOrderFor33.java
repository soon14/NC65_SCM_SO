package nc.pubitf.so.m30.so.m33;

import java.util.Map;

import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;

/**
 * 销售订单提供给销售结算接口服务
 * 
 * @since 6.0
 * @version 2011-1-28 下午04:07:37
 * @author 刘志伟
 */
public interface ISaleOrderFor33 {

  /**
   * 根据销售订单bid查询销售订单单据日期
   * 
   * @param bids
   * @return
   * @throws BusinessException
   */
  Map<String, UFDate> get30BusiDateBy30Bids(String[] bids)
      throws BusinessException;

  /**
   * 根据销售订单bid查询销售合同单据日期
   * 
   * @param bids
   * @return
   * @throws BusinessException
   */
  Map<String, UFDate> getZ3BusiDateBy30Bids(String[] bids)
      throws BusinessException;

  /**
   * 根据销售订单bid查询销售订单视图VO信息——结算发票
   * 
   * @param bids 销售订单bid[]
   * @return Map<bid, For33SquareInvVO> Map<销售订单附表ID, For33SquareInvVO>
   * @throws BusinessException
   */
  Map<String, For33SquareInvVO> query30ViewInfoForSquareInv(String[] bids)
      throws BusinessException;

  /**
   * 根据销售订单bid查询销售订单视图VO信息——结算出库
   * 
   * @param bids 销售订单bid[]
   * @return Map<bid, For33SquareOutVO> Map<销售订单附表ID, For33SquareOutVO>
   * @throws BusinessException
   */
  Map<String, For33SquareOutVO> query30ViewInfoForSquareOut(String[] bids)
      throws BusinessException;
}
