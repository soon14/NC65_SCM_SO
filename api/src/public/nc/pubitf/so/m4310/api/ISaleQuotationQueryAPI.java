package nc.pubitf.so.m4310.api;

import nc.itf.annotation.Component;

import nc.itf.annotation.OpenAPI;
import nc.itf.annotation.OpenLevel;
import nc.pubitf.so.pub.api.ISOQueryAPI;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.BusinessException;
import nc.vo.so.salequotation.entity.AggSalequotationHVO;
import nc.vo.so.salequotation.entity.SalequoViewVO;

/**
 * @description
 * <ul>
 * <li>根据报价单表体主键查询报价单视图VO
 * <li>根据报价单表体主键和需要查询的字段查询报价单视图VO
 * <li>根据报价单主键查询报价单VO
 * <li>根据报价单主键和需要查询的字段查询报价单VO
 * <li>根据查询方案查询报价单视图VO
 * <li>根据查询方案和需要查询的字段查询报价单视图VO
 * <li>根据查询方案查询条件查询报价单VO数组
 * <li>根据查询方案和需要查询的字段查询报价单VO数组
 * <li>根据报价单来源单据行id查询报价单视图VO
 * <li>根据报价单来源单据行id和需要查询的字段查询报价单视图VO
 * </ul>
 * @scene
 * 
 * @param
 * 
 * @functionName 报价单查询服务
 * 
 * @since 6.5
 * @version 2015-10-19 下午4:14:19
 * @author 刘景
 */
@Component("报价单")
@OpenAPI(value = OpenLevel.OPENED)
public interface ISaleQuotationQueryAPI  extends ISOQueryAPI{

  /**
  * 根据报价单表体主键查询报价单视图VO
  * 
  * @param bids 报价单表体主键数组
  * @return 报价单视图VO数组
  * @throws BusinessException 异常
  */
  SalequoViewVO[] queryViewVOByBIDs(String[] bids) throws BusinessException;

  /**
  * 根据报价单表体主键和需要查询的字段查询报价单视图VO
  * 
  * @param bids 报价单表体主键
  * @param queryFields 需要查询的字段
  * @return 报价单视图VO数组
  * @throws BusinessException 异常
  */
  SalequoViewVO[] queryViewVOByBIDs(String[] bids, String[] queryFields)
      throws BusinessException;

  /**
  * 根据报价单主键查询报价单VO
  * 
  * @param ids 报价单主键
  * @return 报价单VO数组
  * @throws BusinessException 异常
  */
  AggSalequotationHVO[] queryVOByIDs(String[] ids) throws BusinessException;

  /**
  * 根据报价单主键和需要查询的字段查询报价单VO
  * 
  * @param ids 报价单主键
  * @param queryFields 需要查询的字段
  * @return 报价单VO数组
  * @throws BusinessException 异常
  */
  AggSalequotationHVO[] queryVOByIDs(String[] ids, String[] queryFields)
      throws BusinessException;

  /**
   * 根据查询方案查询报价单视图VO
   * 
   * @param queryscheme 查询方案
   * @return 报价单视图VO数组
   * @throws BusinessException 异常
   */
  SalequoViewVO[] queryViewVOByScheme(IQueryScheme queryscheme)
      throws BusinessException;

  /**
   * 根据查询方案和需要查询的字段查询报价单视图VO
   * 
   * @param queryscheme 查询方案
   * @param queryFields 需要查询的字段
   * @return 报价单视图VO数组
   * @throws BusinessException 异常
   */
  SalequoViewVO[] queryViewVOByScheme(IQueryScheme queryscheme,
      String[] queryFields) throws BusinessException;

  /**
   * 根据查询方案查询条件查询报价单VO数组
   * 
   * @param queryscheme 查询方案
   * @return 报价单VO数组
   * @throws BusinessException 异常
   */
  AggSalequotationHVO[] queryVOByScheme(IQueryScheme queryscheme)
      throws BusinessException;

  /**
   * 根据查询方案和需要查询的字段查询报价单VO数组
   * 
   * @param queryscheme 查询方案
   * @param  queryFields 需要查询的字段
   * @return 报价单VO数组
   * @throws BusinessException 异常
   */
  AggSalequotationHVO[] queryVOByScheme(IQueryScheme queryscheme, String[] queryFields)
      throws BusinessException;

  /**
  * 根据报价单来源单据行id查询报价单视图VO
  * 
  * @param sourceBIDs 报价单来源行id
  * @return 报价单视图VO数组
  * @throws BusinessException 异常
  */
  SalequoViewVO[] queryViewVOBySourceBIDs(String[] sourceBIDs)
      throws BusinessException;

  /**
  * 根据报价单来源单据行id和需要查询的字段查询报价单视图VO
  * 
  * @param sourceBIDs 报价单来源行id
  * @param queryFields 需要查询的字段
  * @return 报价单视图VO数组
  * @throws BusinessException 异常
  */
  SalequoViewVO[] queryViewVOBySourceBIDs(String[] sourceBIDs, String[] queryFields)
      throws BusinessException;


}
