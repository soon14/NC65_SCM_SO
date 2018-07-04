package nc.pubitf.so.m4331.api;

import nc.itf.annotation.Component;

import nc.itf.annotation.OpenAPI;
import nc.itf.annotation.OpenLevel;
import nc.pubitf.so.pub.api.ISOQueryAPI;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.BusinessException;
import nc.vo.so.m4331.entity.DeliveryVO;
import nc.vo.so.m4331.entity.DeliveryViewVO;


/**
 * @description
 * <ul>
 * <li>根据发货单表体主键查询发货单视图VO
 * <li>根据发货单表体主键和需要查询的字段查询发货单视图VO
 * <li>根据发货单主键查询发货单VO
 * <li>根据发货单主键和需要查询的字段查询发货单VO
 * <li>根据查询方案查询发货单视图VO
 * <li>根据查询方案和需要查询的字段查询发货单视图VO
 * <li>根据查询方案查询条件查询发货单VO数组
 * <li>根据查询方案和需要查询的字段查询发货单VO数组
 * <li>根据发货单来源单据行id查询发货单视图VO
 * <li>根据发货单来源单据行id和需要查询的字段查询发货单视图VO
 * </ul>
 * @scene
 * 
 * @param
 * 
 * @functionName 发货单查询服务
 * 
 * @since 6.5
 * @version 2015-10-19 下午4:14:19
 * @author 刘景
 */
@Component("发货单")
@OpenAPI(value = OpenLevel.OPENED)
public interface IDeliveryQueryAPI  extends ISOQueryAPI{

  /**
  * 根据发货单表体主键查询发货单视图VO
  * 
  * @param bids 发货单表体主键数组
  * @return 发货单视图VO数组
  * @throws BusinessException 异常
  */
  DeliveryViewVO[] queryViewVOByBIDs(String[] bids) throws BusinessException;

  /**
  * 根据发货单表体主键和需要查询的字段查询发货单视图VO
  * 
  * @param bids 发货单表体主键
  * @param queryFields 需要查询的字段
  * @return 发货单视图VO数组
  * @throws BusinessException 异常
  */
  DeliveryViewVO[] queryViewVOByBIDs(String[] bids, String[] queryFields)
      throws BusinessException;

  /**
  * 根据发货单主键查询发货单VO
  * 
  * @param ids 发货单主键
  * @return 发货单VO数组
  * @throws BusinessException 异常
  */
  DeliveryVO[] queryVOByIDs(String[] ids) throws BusinessException;

  /**
  * 根据发货单主键和需要查询的字段查询发货单VO
  * 
  * @param ids 发货单主键
  * @param queryFields 需要查询的字段
  * @return 发货单VO数组
  * @throws BusinessException 异常
  */
  DeliveryVO[] queryVOByIDs(String[] ids, String[] queryFields)
      throws BusinessException;

  /**
   * 根据查询方案查询发货单视图VO
   * 
   * @param queryscheme 查询方案
   * @return 发货单视图VO数组
   * @throws BusinessException 异常
   */
  DeliveryViewVO[] queryViewVOByScheme(IQueryScheme queryscheme)
      throws BusinessException;

  /**
   * 根据查询方案和需要查询的字段查询发货单视图VO
   * 
   * @param queryscheme 查询方案
   * @param queryFields 需要查询的字段
   * @return 发货单视图VO数组
   * @throws BusinessException 异常
   */
  DeliveryViewVO[] queryViewVOByScheme(IQueryScheme queryscheme,
      String[] queryFields) throws BusinessException;

  /**
   * 根据查询方案查询条件查询发货单VO数组
   * 
   * @param queryscheme 查询方案
   * @return 发货单VO数组
   * @throws BusinessException 异常
   */
  DeliveryVO[] queryVOByScheme(IQueryScheme queryscheme)
      throws BusinessException;

  /**
   * 根据查询方案和需要查询的字段查询发货单VO数组
   * 
   * @param queryscheme 查询方案
   * @param  queryFields 需要查询的字段
   * @return 发货单VO数组
   * @throws BusinessException 异常
   */
  DeliveryVO[] queryVOByScheme(IQueryScheme queryscheme, String[] queryFields)
      throws BusinessException;

  /**
  * 根据发货单来源单据行id查询发货单视图VO
  * 
  * @param sourceBIDs 发货单来源行id
  * @return 发货单视图VO数组
  * @throws BusinessException 异常
  */
  DeliveryViewVO[] queryViewVOBySourceBIDs(String[] sourceBIDs)
      throws BusinessException;

  /**
  * 根据发货单来源单据行id和需要查询的字段查询发货单视图VO
  * 
  * @param sourceBIDs 发货单来源行id
  * @param queryFields 需要查询的字段
  * @return 发货单视图VO数组
  * @throws BusinessException 异常
  */
  DeliveryViewVO[] queryViewVOBySourceBIDs(String[] sourceBIDs, String[] queryFields)
      throws BusinessException;


}
