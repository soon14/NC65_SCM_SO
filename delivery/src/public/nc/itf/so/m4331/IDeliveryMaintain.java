package nc.itf.so.m4331;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.BusinessException;
import nc.vo.so.m4331.entity.DeliveryVO;
import nc.vo.so.m4331.entity.DeliveryViewVO;
import nc.vo.so.pub.SOParameterVO;

/**
 * <b>本类主要完成以下功能：</b> <li>发货单操作接口： <li>发货单查询 <li>发货单新增保存 <li>发货单修改保存 <li>发货单批量删除
 * <li>发货单批量审核 <li>发货单批量弃审
 */
public interface IDeliveryMaintain {
  /**
   * 方法功能描述：发货单批量审核功能。
   */
  DeliveryVO[] approveDelivery(DeliveryVO[] vos) throws BusinessException;

  /**
   * 方法功能描述：发货单批量删除功能。
   */
  void deleteDelivery(DeliveryVO[] vos) throws BusinessException;

  /**
   * 方法功能描述：发货单新增保存功能。
   */
  DeliveryVO[] insertDelivery(DeliveryVO[] insertvos) throws BusinessException;

  /**
   * 方法功能描述：发货单推式保存功能。
   * <p>
   * <b>参数说明</b>
   * 
   * @param voDeliverys
   * @return
   * @throws BusinessException
   *           <p>
   * @author fengjb
   * @time 2010-5-31 下午03:15:37
   */
  DeliveryVO[] pushWriteDelivery(SOParameterVO paravo) throws BusinessException;

  DeliveryVO[] queryDelivery(String sql) throws BusinessException;

  /**
   * 根据发货单表体id 查询发货单信息
   * 
   * @param bids
   * @return
   * @throws BusinessException
   */
  DeliveryVO[] queryDelivery(String[] bids) throws BusinessException;

  /**
   * 方法功能描述：发货单到调拨出库单转单查询。
   */
  DeliveryViewVO[] queryDeliveryFor4804(IQueryScheme queryScheme)
      throws BusinessException;

  /**
   * 方法功能描述：发货单到销售出库单转单查询。
   */
  DeliveryVO[] queryDeliveryFor4C(IQueryScheme queryScheme)
      throws BusinessException;

  /**
   * 方法功能描述：发货单到调拨出库单转单查询。
   */
  DeliveryVO[] queryDeliveryFor4Y(IQueryScheme queryScheme)
      throws BusinessException;

  /**
   * 查询发货单视图vo
   * 
   * @param sql
   * @return
   * @throws BusinessException
   */
  DeliveryViewVO[] queryViewVO(String sql) throws BusinessException;

  /**
   * 方法功能描述：发货单批量弃审功能。
   */
  DeliveryVO[] unapproveDelivery(DeliveryVO[] vos) throws BusinessException;

  /**
   * 方法功能描述：发货单修改保存功能。
   */
  DeliveryVO[] updateDelivery(DeliveryVO[] updatevos, DeliveryVO[] originBills)
      throws BusinessException;
}
