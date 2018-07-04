package nc.pubitf.so.mbuylagress.api;

import nc.itf.annotation.Component;

import nc.itf.annotation.OpenAPI;
import nc.itf.annotation.OpenLevel;
import nc.pubitf.so.pub.api.ISOQueryAPI;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.BusinessException;
import nc.vo.so.mbuylargess.entity.BuyLargessVO;
import nc.vo.so.mbuylargess.view.BuyLargessShowViewVO;


/**
 * @description
 * <ul>
 * <li>根据买赠设置表体主键查询买赠设置视图VO
 * <li>根据买赠设置表体主键和需要查询的字段查询买赠设置视图VO
 * <li>根据买赠设置主键查询买赠设置VO
 * <li>根据买赠设置主键和需要查询的字段查询买赠设置VO
 * <li>根据查询方案查询买赠设置视图VO
 * <li>根据查询方案和需要查询的字段查询买赠设置视图VO
 * <li>根据查询方案查询条件查询买赠设置VO数组
 * <li>根据查询方案和需要查询的字段查询买赠设置VO数组
 * </ul>
 * @scene
 * 
 * @param
 * 
 * @functionName 买赠设置查询服务
 * 
 * @since 6.5
 * @version 2015-10-19 下午4:14:19
 * @author 刘景
 */
@Component("买赠设置")
@OpenAPI(value = OpenLevel.OPENED)
public interface IGwpruleQueryAPI  extends ISOQueryAPI{
  

  /**
  * 根据买赠设置表体主键查询买赠设置视图VO
  * 
  * @param bids 买赠设置表体主键数组
  * @return 买赠设置视图VO数组
  * @throws BusinessException 异常
  */
  BuyLargessShowViewVO[] queryViewVOByBIDs(String[] bids) throws BusinessException;

  /**
  * 根据买赠设置表体主键和需要查询的字段查询买赠设置视图VO
  * 
  * @param bids 买赠设置表体主键
  * @param queryFields 需要查询的字段
  * @return 买赠设置视图VO数组
  * @throws BusinessException 异常
  */
  BuyLargessShowViewVO[] queryViewVOByBIDs(String[] bids, String[] queryFields)
      throws BusinessException;

  /**
  * 根据买赠设置主键查询买赠设置VO
  * 
  * @param ids 买赠设置主键
  * @return 买赠设置VO数组
  * @throws BusinessException 异常
  */
  BuyLargessVO[] queryVOByIDs(String[] ids) throws BusinessException;

  /**
  * 根据买赠设置主键和需要查询的字段查询买赠设置VO
  * 
  * @param ids 买赠设置主键
  * @param queryFields 需要查询的字段
  * @return 买赠设置VO数组
  * @throws BusinessException 异常
  */
  BuyLargessVO[] queryVOByIDs(String[] ids, String[] queryFields)
      throws BusinessException;

  /**
   * 根据查询方案查询买赠设置视图VO
   * 
   * @param queryscheme 查询方案
   * @return 买赠设置视图VO数组
   * @throws BusinessException 异常
   */
  BuyLargessShowViewVO[] queryViewVOByScheme(IQueryScheme queryscheme)
      throws BusinessException;

  /**
   * 根据查询方案和需要查询的字段查询买赠设置视图VO
   * 
   * @param queryscheme 查询方案
   * @param queryFields 需要查询的字段
   * @return 买赠设置视图VO数组
   * @throws BusinessException 异常
   */
  BuyLargessShowViewVO[] queryViewVOByScheme(IQueryScheme queryscheme,
      String[] queryFields) throws BusinessException;

  /**
   * 根据查询方案查询条件查询买赠设置VO数组
   * 
   * @param queryscheme 查询方案
   * @return 买赠设置VO数组
   * @throws BusinessException 异常
   */
  BuyLargessVO[] queryVOByScheme(IQueryScheme queryscheme)
      throws BusinessException;

  /**
   * 根据查询方案和需要查询的字段查询买赠设置VO数组
   * 
   * @param queryscheme 查询方案
   * @param  queryFields 需要查询的字段
   * @return 买赠设置VO数组
   * @throws BusinessException 异常
   */
  BuyLargessVO[] queryVOByScheme(IQueryScheme queryscheme, String[] queryFields)
      throws BusinessException;


}
