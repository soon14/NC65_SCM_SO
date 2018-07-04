package nc.itf.so.m4331;

import nc.vo.pub.BusinessException;
import nc.vo.so.m4331.entity.DeliveryVO;
import nc.vo.so.pub.SOParameterVO;

/**
 * 发货单辅助功能操作
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>发货单整单关闭操作。
 * <li>发货单整单打开操作。
 * <li>发货单行关闭操作。
 * <li>发货单行打开操作。
 * <li>发货单预留。
 * </ul>
 * <p>
 * 
 * @author 祝会征
 * @time 2010-04-09 下午02:43:18
 */
public interface IDeliveryAssitFunc {

  /**
   * 方法功能描述：发货单整单关闭操作
   * 
   * @throws BusinessException
   * @since 6.0
   */
  DeliveryVO[] closeDelivery(DeliveryVO[] bills) throws BusinessException;
  
  /**
   * 方法功能描述：发货单整单关闭操作
   * 
   * @throws BusinessException
   * @since 6.0
   */
  DeliveryVO[] closeDelivery(SOParameterVO paravo) throws BusinessException;

  /**
   * 方法功能描述：发货单行关闭操作
   * 
   * @param bill
   *          单据VO
   * @param rows
   *          选中行
   * @throws BusinessException
   * @since 6.0
   */
  DeliveryVO[] closeDeliveryRows(DeliveryVO bill, int[] rows)
      throws BusinessException;

  /**
   * 方法功能描述：发货单行打开操作
   * 
   * @param bill
   *          单据VO
   * @param rows
   *          选中行
   * @throws BusinessException
   * @since 6.0
   */
  DeliveryVO[] openDeiveryRows(SOParameterVO paravo, int[] rows)
      throws BusinessException;

  /**
   * 方法功能描述：发货单整单打开操作
   * 
   * @throws BusinessException
   * @since 6.0
   */
  DeliveryVO[] openDelivery(SOParameterVO paravo) throws BusinessException;

  /**
   * 方法功能描述：发货单预留
   * 
   * @throws BusinessException
   * @since 6.0
   */
  DeliveryVO[] preKeepDelivery(DeliveryVO[] bills) throws BusinessException;
}
