package nc.itf.so.m30.self;

import nc.vo.pub.BusinessException;
import nc.vo.so.m30.entity.SaleOrderVO;

/**
 * TODO tianft 此类无用，可删除！
 */
public interface ISaleOrder {

  /**
   * 删除VO
   * 
   * @param todelVO
   * @throws BusinessException
   */
  void deleteSaleOrder(SaleOrderVO todelVO) throws BusinessException;

  /**
   * 新增VO
   * 
   * @param newVO
   * @return
   * @throws BusinessException
   */
  SaleOrderVO insertSaleOrder(SaleOrderVO newVO) throws BusinessException;

  /**
   * 修改VO
   * 
   * @param updateVO
   * @return
   * @throws BusinessException
   */
  SaleOrderVO updateSaleOrder(SaleOrderVO updateVO) throws BusinessException;

  /**
   * 根据where条件查询销售订单数据
   * 
   * @param where
   * @return
   * @throws Exception
   */
  SaleOrderVO[] querySaleOrder(String where) throws BusinessException;

}
