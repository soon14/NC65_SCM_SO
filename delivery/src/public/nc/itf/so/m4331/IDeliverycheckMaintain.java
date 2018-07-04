package nc.itf.so.m4331;

import nc.vo.pub.BusinessException;
import nc.vo.so.m4331.entity.DeliveryCheckVO;

/**
 * 发货单质检接口
 * 
 * @since 6.0
 * @version 2010-12-21 上午09:40:11
 * @author 祝会征
 */
public interface IDeliverycheckMaintain {
  /**
   * 方法功能描述：发货单批量删除功能。
   * isCheck true 代表是报检调用 false 代表是质检报告弃审调用
   */
  void deleteDeliverycheck(DeliveryCheckVO[] vos, boolean isCheck)
      throws BusinessException;

  /**
   * 方法功能描述：发货单新增保存功能。
   */
  DeliveryCheckVO[] insertDeliverycheck(DeliveryCheckVO[] vos)
      throws BusinessException;

  /**
   * 查询获得质检vo
   */
  DeliveryCheckVO[] queryDeliveryCheckVO(String sql);

  /**
   * 更新发货单质检表
   * 
   * @param vos
   * @return
   */
  DeliveryCheckVO[] updateDeliverycheck(DeliveryCheckVO[] vos);
}
