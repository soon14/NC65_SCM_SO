package nc.pubitf.so.pub.api;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.BusinessException;

/**
 * @description
 * 销售管理查询API接口
 * @scene
 *
 * @param
 *
 *
 * @since 6.5
 * @version 2015-11-9 下午1:59:16
 * @author 刘景
 */
public interface ISOQueryAPI {

  /**
   * 根据表体主键查询单据视图VO
   * 
   * @param bids
   * @return
   * @throws BusinessException
   */
  Object[] queryViewVOByBIDs(String[] bids) throws BusinessException;

  /**
   * 根据表体主键+需要查询的字段查询单据视图VO
   * @param bids
   * @param queryFields
   * @return
   * @throws BusinessException
   */
  Object[] queryViewVOByBIDs(String[] bids, String[] queryFields)
      throws BusinessException;

  /**
   * 根据表头主键查询单据VO
   * @param ids
   * @return
   * @throws BusinessException
   */
  Object[] queryVOByIDs(String[] ids) throws BusinessException;

  /**
   * 根据表头主键查询+需要查询的字段查询单据VO
   * @param ids
   * @param queryFields
   * @return
   * @throws BusinessException
   */
  Object[] queryVOByIDs(String[] ids, String[] queryFields)
      throws BusinessException;

  /**
   * 根据查询方案查询单据视图VO
   * 
   * @param queryscheme
   * @return
   * @throws BusinessException
   */
  Object[] queryViewVOByScheme(IQueryScheme queryscheme)
      throws BusinessException;

  /**
   * 根据查询方案+需要查询的字段查询单据视图VO
   * @param queryscheme
   * @param queryFields
   * @return
   * @throws BusinessException
   */
  Object[] queryViewVOByScheme(IQueryScheme queryscheme, String[] queryFields)
      throws BusinessException;

  /**
   * 根据查询方案查询查询单据VO
   * @param queryscheme
   * @return
   * @throws BusinessException
   */
  Object[] queryVOByScheme(IQueryScheme queryscheme) throws BusinessException;

  /**
   * 根据查询方案+需要查询的字段查询单据VO
   * @param queryscheme
   * @param queryFields
   * @return
   * @throws BusinessException
   */
  Object[] queryVOByScheme(IQueryScheme queryscheme, String[] queryFields)
      throws BusinessException;

  /**
   * 根据来源单据表体id查询单据视图VO
   * @param sourceBIDs
   * @return
   * @throws BusinessException
   */
  Object[] queryViewVOBySourceBIDs(String[] sourceBIDs)
      throws BusinessException;

  /**
   * 根据来源单据表体id+需要查询的字段查询单据视图VO
   * @param sourceBIDs
   * @param queryFields
   * @return
   * @throws BusinessException
   */
  Object[] queryViewVOBySourceBIDs(String[] sourceBIDs, String[] queryFields)
      throws BusinessException;

}
