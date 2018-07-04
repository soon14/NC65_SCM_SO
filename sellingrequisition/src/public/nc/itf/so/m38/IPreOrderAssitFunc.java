package nc.itf.so.m38;

import nc.vo.so.m38.entity.PreOrderHVO;

import nc.vo.pub.BusinessException;
import nc.vo.so.m38.entity.PreOrderVO;

/**
 * 预订单辅助功能操作
 * <p>
 * <b>本类主要完成以下功能：</b>
 * 
 * <ul>
 *  <li>预订单整单关闭操作。
 *  <li>预订单整单打开操作。
 *  <li>预订单行关闭操作。
 *  <li>预订单行打开操作。
 *  <li>预订单价格组成。
 *  <li>预订单修订。
 * </ul> 
 *
 * <p>
 * @since 6.0
 * @author 刘志伟
 * @time 2010-04-09 下午02:43:18
 */
public interface IPreOrderAssitFunc {

  /**
   * 方法功能描述：预订单整单关闭操作
   * 
   * @throws BusinessException
   * @since 6.0
   */
  PreOrderVO[] closePreOrder(PreOrderVO[] bills) throws BusinessException;  

  /**
   * 方法功能描述：预订单行关闭操作
   * 
   * @param bill 单据VO
   * @param rows 选中行
   * @throws BusinessException
   * @since 6.0
   */
  PreOrderVO[] closePreOrderRows(PreOrderVO bill, int[] rows)
      throws BusinessException;

  /**
   * 方法功能描述：预订单整单打开操作
   * 
   * @throws BusinessException
   * @since 6.0
   */
  PreOrderVO[] openPreOrder(PreOrderVO[] bills) throws BusinessException;

  /**
   * 方法功能描述：预订单行打开操作
   * 
   * @param bill 单据VO
   * @param rows 选中行
   * @throws BusinessException
   * @since 6.0
   */
  PreOrderVO[] openPreOrderRows(PreOrderVO bill, int[] rows)
      throws BusinessException;

}
