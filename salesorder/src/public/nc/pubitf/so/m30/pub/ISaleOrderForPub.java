package nc.pubitf.so.m30.pub;

import nc.vo.pub.BusinessException;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderViewVO;

/**
 * 销售订单提供外部的公共接口
 * 
 * @since 6.0
 * @version 2011-8-26 下午12:24:07
 * @author 么贵敬
 */
/**
 * 
 * @since 6.0
 * @version 2011-12-30 下午12:34:30
 * @author 么贵敬
 */
public interface ISaleOrderForPub {

  /**
   * 根据表体IDS查询销售订单表体VO数组
   * 当只查表体字段是比查询试图VO 效率高
   * 
   * @param ids 表体id数组
   * @param names 字段
   * @return
   * @throws BusinessException
   */
  SaleOrderBVO[] querySaleOrderBVOs(String[] bids, String[] names)
      throws BusinessException;

  /**
   * 根据表头IDS查询销售订单表头VO数组
   * 当只查表头字段是比查询试图VO 效率高
   * 
   * @param ids 表头id数组
   * @param names 字段
   * @return
   * @throws BusinessException
   */
  SaleOrderHVO[] querySaleOrderHVOs(String[] ids, String[] names)
      throws BusinessException;

  /**
   * 根据bids查询销售订单ViewVOs指定值
   * 
   * @param bids 订单表体id[]
   * @param String[] 需要查询的值
   * @return SaleOrderViewVO[] 销售订单ViewVO[]
   * @throws BusinessException
   */
  SaleOrderViewVO[] querySaleOrderViewVOs(String[] bids, String[] names)
      throws BusinessException;

  /**
   * 根据bids查询销售订单ViewVOs指定值
   * 
   * @param bids 订单表体id[]
   * @return SaleOrderViewVO[] 销售订单ViewVO[]
   * @throws BusinessException
   */
  SaleOrderViewVO[] querySaleOrderViewVOs(String[] bids)
      throws BusinessException;

  /**
   * 
   * 查询销售订单是否所有行都关闭
   * 
   * @param saleorderids 销售订单id
   * @return 返回没有关闭的订单单据号
   * @throws BusinessException
   */
  String[] getNotCloseOrder(String[] saleorderids) throws BusinessException;

}
