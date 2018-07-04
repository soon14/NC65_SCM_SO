package nc.pubitf.so.m4331.so.m30;

import java.util.Map;

import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.so.m30.entity.SaleOrderViewVO;

/**
 * 发货单提供给销售订单的接口
 * 
 * @since 6.0
 * @version 2011-1-26 下午01:55:19
 * @author 祝会征
 */
public interface IDeliveryFor30 {

  /**
   * 根据销售订单表体id 查询发货单是否做过预留标记
   * 
   * @param bids
   * @return 发货单是否做过预留
   * @throws BusinessException
   */
  Map<String, UFBoolean> queryReverseFlag(String[] bids)
      throws BusinessException;

  /**
   * 销售订单修订价格，更新发货单的价格
   * 
   * @param paraMap key为销售订单表体id value为变化的key和变化后的值
   * @throws BusinessException
   */
  void renovatePrice(Map<String, IDeliveryPriceParaFor30> paraMap)
      throws BusinessException;

  /**
   * 根据订单id，查询审核发货单审核的数量
   * 
   * @param srcBids
   * @return 发货单审核数量
   * @throws BusinessException
   */
  Map<String, UFDouble> queryAppNum(String[] srcBids) throws BusinessException;

  /**
   * 回写发货单的收入结算关闭状态
   * 
   * @param orderids 订单id数组
   * @param orderbids 订单表体id数组
   * @param isclose 是关闭还是打开 true为关闭 , flase为打开
   * @throws BusinessException
   */
  void rewriteArSettle(SaleOrderViewVO[] viewvos) throws BusinessException;
}
