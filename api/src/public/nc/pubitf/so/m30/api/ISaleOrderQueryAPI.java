package nc.pubitf.so.m30.api;

import nc.itf.annotation.OpenAPI;
import nc.itf.annotation.OpenLevel;
import nc.pubitf.so.pub.api.ISOQueryAPI;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.BusinessException;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.entity.SaleOrderViewVO;

import nc.itf.annotation.Component;

/**
 * @description
 * <ul>
 * <li>根据销售订单表体主键查询销售订单视图VO
 * <li>根据销售订单表体主键和需要查询的字段查询销售订单视图VO
 * <li>根据销售订单主键查询销售订单VO
 * <li>根据销售订单主键和需要查询的字段查询销售订单VO
 * <li>根据查询方案查询销售订单视图VO
 * <li>根据查询方案和需要查询的字段查询销售订单视图VO
 * <li>根据查询方案查询条件查询销售订单VO数组
 * <li>根据查询方案和需要查询的字段查询销售订单VO数组
 * <li>根据销售订单来源单据行id查询销售订单视图VO
 * <li>根据销售订单来源单据行id和需要查询的字段查询销售订单视图VO
 * <li>根据销售订单主键查询销售订单是否整单关闭
 * </ul>
 * @scene
 * 
 * @param
 * 
 * @functionName 销售订单查询服务
 * 
 * @since 6.5
 * @version 2015-10-19 下午4:14:19
 * @author 刘景
 */
@Component("销售订单")
@OpenAPI(value = OpenLevel.OPENED)
public interface ISaleOrderQueryAPI extends ISOQueryAPI{

  /**
  * 根据销售订单表体主键查询销售订单视图VO
  * 
  * @param bids 销售订单表体主键数组
  * @return 销售订单视图VO数组
  * @throws BusinessException 异常
  */
  SaleOrderViewVO[] queryViewVOByBIDs(String[] bids) throws BusinessException;

  /**
  * 根据销售订单表体主键和需要查询的字段查询销售订单视图VO
  * 
  * @param bids 销售订单表体主键
  * @param queryFields 需要查询的字段
  * @return 销售订单视图VO数组
  * @throws BusinessException 异常
  */
  SaleOrderViewVO[] queryViewVOByBIDs(String[] bids, String[] queryFields)
      throws BusinessException;

  /**
  * 根据销售订单主键查询销售订单VO
  * 
  * @param ids 销售订单主键
  * @return 销售订单VO数组
  * @throws BusinessException 异常
  */
  SaleOrderVO[] queryVOByIDs(String[] ids) throws BusinessException;

  /**
  * 根据销售订单主键和需要查询的字段查询销售订单VO
  * 
  * @param ids 销售订单主键
  * @param queryFields 需要查询的字段
  * @return 销售订单VO数组
  * @throws BusinessException 异常
  */
  SaleOrderVO[] queryVOByIDs(String[] ids, String[] queryFields)
      throws BusinessException;

  /**
   * 根据查询方案查询销售订单视图VO
   * 
   * @param queryscheme 查询方案
   * @return 销售订单视图VO数组
   * @throws BusinessException 异常
   */
  SaleOrderViewVO[] queryViewVOByScheme(IQueryScheme queryscheme)
      throws BusinessException;

  /**
   * 根据查询方案和需要查询的字段查询销售订单视图VO
   * 
   * @param queryscheme 查询方案
   * @param queryFields 需要查询的字段
   * @return 销售订单视图VO数组
   * @throws BusinessException 异常
   */
  SaleOrderViewVO[] queryViewVOByScheme(IQueryScheme queryscheme,
      String[] queryFields) throws BusinessException;

  /**
   * 根据查询方案查询条件查询销售订单VO数组
   * 
   * @param queryscheme 查询方案
   * @return 销售订单VO数组
   * @throws BusinessException 异常
   */
  SaleOrderVO[] queryVOByScheme(IQueryScheme queryscheme)
      throws BusinessException;

  /**
   * 根据查询方案和需要查询的字段查询销售订单VO数组
   * 
   * @param queryscheme 查询方案
   * @param  queryFields 需要查询的字段
   * @return 销售订单VO数组
   * @throws BusinessException 异常
   */
  SaleOrderVO[] queryVOByScheme(IQueryScheme queryscheme, String[] queryFields)
      throws BusinessException;

  /**
  * 根据销售订单来源单据行id查询销售订单视图VO
  * 
  * @param sourceBIDs 销售订单来源行id
  * @return 销售订单视图VO数组
  * @throws BusinessException 异常
  */
  SaleOrderViewVO[] queryViewVOBySourceBIDs(String[] sourceBIDs)
      throws BusinessException;

  /**
  * 根据销售订单来源单据行id和需要查询的字段查询销售订单视图VO
  * 
  * @param sourceBIDs 销售订单来源行id
  * @param queryFields 需要查询的字段
  * @return 销售订单视图VO数组
  * @throws BusinessException 异常
  */
  SaleOrderViewVO[] queryViewVOBySourceBIDs(String[] sourceBIDs,
      String[] queryFields) throws BusinessException;

  /**
   * 
   * 根据销售订单主键查询销售订单是否整单关闭
   * 
   * @param saleorderids 销售订单id
   * @return 返回没有关闭的订单单id
   * @throws BusinessException
   */
  String[] getNotCloseOrder(String[] ids) throws BusinessException;

}
