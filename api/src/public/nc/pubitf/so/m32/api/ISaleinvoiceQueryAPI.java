package nc.pubitf.so.m32.api;

import nc.itf.annotation.Component;

import nc.itf.annotation.OpenAPI;
import nc.itf.annotation.OpenLevel;
import nc.pubitf.so.pub.api.ISOQueryAPI;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.BusinessException;
import nc.vo.so.m32.entity.SaleInvoiceVO;
import nc.vo.so.m32.entity.SaleInvoiceViewVO;

/**
 * @description
 * <ul>
 * <li>根据销售发票表体主键查询销售发票视图VO
 * <li>根据销售发票表体主键和需要查询的字段查询销售发票视图VO
 * <li>根据销售发票主键查询销售发票VO
 * <li>根据销售发票主键和需要查询的字段查询销售发票VO
 * <li>根据查询方案查询销售发票视图VO
 * <li>根据查询方案和需要查询的字段查询销售发票视图VO
 * <li>根据查询方案查询条件查询销售发票VO数组
 * <li>根据查询方案和需要查询的字段查询销售发票VO数组
 * <li>根据销售发票来源单据行id查询销售发票视图VO
 * <li>根据销售发票来源单据行id和需要查询的字段查询销售发票视图VO
 * </ul>
 * @scene
 * 
 * @param
 * 
 * @functionName 销售发票查询服务
 * 
 * @since 6.5
 * @version 2015-10-19 下午4:14:19
 * @author 刘景
 */
@Component("销售发票")
@OpenAPI(value = OpenLevel.OPENED)
public interface ISaleinvoiceQueryAPI extends ISOQueryAPI{
  

  /**
  * 根据销售发票表体主键查询销售发票视图VO
  * 
  * @param bids 销售发票表体主键数组
  * @return 销售发票视图VO数组
  * @throws BusinessException 异常
  */
  SaleInvoiceViewVO[] queryViewVOByBIDs(String[] bids) throws BusinessException;

  /**
  * 根据销售发票表体主键和需要查询的字段查询销售发票视图VO
  * 
  * @param bids 销售发票表体主键
  * @param queryFields 需要查询的字段
  * @return 销售发票视图VO数组
  * @throws BusinessException 异常
  */
  SaleInvoiceViewVO[] queryViewVOByBIDs(String[] bids, String[] queryFields)
      throws BusinessException;

  /**
  * 根据销售发票主键查询销售发票VO
  * 
  * @param ids 销售发票主键
  * @return 销售发票VO数组
  * @throws BusinessException 异常
  */
  SaleInvoiceVO[] queryVOByIDs(String[] ids) throws BusinessException;

  /**
  * 根据销售发票主键和需要查询的字段查询销售发票VO
  * 
  * @param ids 销售发票主键
  * @param queryFields 需要查询的字段
  * @return 销售发票VO数组
  * @throws BusinessException 异常
  */
  SaleInvoiceVO[] queryVOByIDs(String[] ids, String[] queryFields)
      throws BusinessException;

  /**
   * 根据查询方案查询销售发票视图VO
   * 
   * @param queryscheme 查询方案
   * @return 销售发票视图VO数组
   * @throws BusinessException 异常
   */
  SaleInvoiceViewVO[] queryViewVOByScheme(IQueryScheme queryscheme)
      throws BusinessException;

  /**
   * 根据查询方案和需要查询的字段查询销售发票视图VO
   * 
   * @param queryscheme 查询方案
   * @param queryFields 需要查询的字段
   * @return 销售发票视图VO数组
   * @throws BusinessException 异常
   */
  SaleInvoiceViewVO[] queryViewVOByScheme(IQueryScheme queryscheme,
      String[] queryFields) throws BusinessException;

  /**
   * 根据查询方案查询条件查询销售发票VO数组
   * 
   * @param queryscheme 查询方案
   * @return 销售发票VO数组
   * @throws BusinessException 异常
   */
  SaleInvoiceVO[] queryVOByScheme(IQueryScheme queryscheme)
      throws BusinessException;

  /**
   * 根据查询方案和需要查询的字段查询销售发票VO数组
   * 
   * @param queryscheme 查询方案
   * @param  queryFields 需要查询的字段
   * @return 销售发票VO数组
   * @throws BusinessException 异常
   */
  SaleInvoiceVO[] queryVOByScheme(IQueryScheme queryscheme, String[] queryFields)
      throws BusinessException;

  /**
  * 根据销售发票来源单据行id查询销售发票视图VO
  * 
  * @param sourceBIDs 销售发票来源行id
  * @return 销售发票视图VO数组
  * @throws BusinessException 异常
  */
  SaleInvoiceViewVO[] queryViewVOBySourceBIDs(String[] sourceBIDs)
      throws BusinessException;

  /**
  * 根据销售发票来源单据行id和需要查询的字段查询销售发票视图VO
  * 
  * @param sourceBIDs 销售发票来源行id
  * @param queryFields 需要查询的字段
  * @return 销售发票视图VO数组
  * @throws BusinessException 异常
  */
  SaleInvoiceViewVO[] queryViewVOBySourceBIDs(String[] sourceBIDs, String[] queryFields)
      throws BusinessException;


}
