package nc.itf.so.m38;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.BusinessException;
import nc.vo.so.m38.entity.PreOrderVO;

/**
 * 预订单维护操作
 * <p>
 * <b>本类主要完成以下功能：</b>
 * 
 * <ul>
 * <li>预订单删除操作。
 * <li>预订单新增保存操作。
 * <li>预订单修改保存操作。
 * <li>预订单查询操作。
 * </ul>
 * <p>
 * 
 * @since 6.0
 * @version 2010-11-3 上午10:41:33
 * @author 刘志伟
 */
public interface IPreOrderMaintain {
  /**
   * 方法功能描述：预订单删除操作。
   * 
   * @throws BusinessException
   * @since 6.0
   */
  void deletePreOrder(PreOrderVO[] bills) throws BusinessException;

  /**
   * 方法功能描述：预订单新增保存操作。
   * 
   * @throws BusinessException
   * @since 6.0
   */
  PreOrderVO insertPreOrder(PreOrderVO bill) throws BusinessException;

  /**
   * 预订单失效接口
   * 
   * @param vos
   * @throws BusinessException
   */
  PreOrderVO[] invalidationPreorder(PreOrderVO[] vos) throws BusinessException;

  /**
   * 方法功能描述：预订单查询操作
   * 
   * @param queryScheme
   * @throws BusinessException
   */
  PreOrderVO[] queryPreOrder(IQueryScheme queryScheme) throws BusinessException;

  /**
   * 方法功能描述：预订单查询操作。
   * 
   * @throws BusinessException
   * @since 6.0
   */
  PreOrderVO[] queryPreOrder(String sql) throws BusinessException;

  /**
   * 方法功能描述：提供给销售订单参照预订单查询操作
   * 
   * @param queryScheme
   * @throws BusinessException
   */
  PreOrderVO[] queryPreOrderFor30(IQueryScheme queryScheme)
      throws BusinessException;

  /**
   * 方法功能描述：预订单修改保存操作。
   * 
   * @throws BusinessException
   * @since 6.0
   */
  PreOrderVO[] updatePreOrder(PreOrderVO[] bill, PreOrderVO[] originBill)
      throws BusinessException;

}
