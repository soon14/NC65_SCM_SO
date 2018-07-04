package nc.itf.so.m30.revise;

import nc.vo.pub.BusinessException;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.revise.entity.SaleOrderHistoryVO;

/**
 * 销售订单修订维护操作
 * <p>
 * <b>本类主要完成以下功能：</b>
 * 
 * <ul>
 * <li>修订保存
 * <li>修订历史查询
 * <li>...
 * </ul>
 * 
 * <p>
 * 
 * @author 刘志伟
 * 
 */
public interface IM30ReviseMaintain {

  /**
   * 修订保存
   * 
   * @param bills 销售订单
   * @return SaleOrderVO[] 销售订单
   * @throws BusinessException
   */
  SaleOrderVO[] reviseSave(SaleOrderVO[] bills) throws BusinessException;

  // 新增销售订单修订保存方法 add by wangshu6 for 销售订单修订支持审批流
  /**
   * 修订保存
   * 
   * @param bills 销售订单修订vo
   * @return SaleOrderVO[] 销售订单修订vo
   * @throws BusinessException
   */
  SaleOrderHistoryVO[] reviseOrderHisVOSave(SaleOrderHistoryVO[] bills)
      throws BusinessException;

  /**
   * 修订历史查询(返回结果按版本倒序排列)
   * 
   * @param hid 销售订单HID
   * @return SaleOrderVO[]
   * @throws BusinessException
   */
  SaleOrderVO[] queryReviseHistory(String hid) throws BusinessException;
}
