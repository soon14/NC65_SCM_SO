package nc.pubitf.so.m30.mmdp.altc;

import nc.vo.pub.BusinessException;
import nc.vo.so.m30.entity.SaleOrderViewVO;

/**
 * 变更控制中心接口
 * 
 * @since 6.36
 * @version 2015-1-22 下午5:12:05
 * @author 纪录
 */
public interface IQueryToExcuteSaleOrderViewVOs {

  /**
   * 根据销售订单明细主键查询未执行大于零、已审批、未关闭的销售订单
   * 为计划订单提供
   * 
   * @param csaleorderbids 销售订单明细主键
   * @return 销售订单视图
   * @throws BusinessException 异常
   */
  SaleOrderViewVO[] queryToExcuteSaleOrderViewVOs4PO(String[] csaleorderbids)
      throws BusinessException;

  /**
   * 根据销售订单明细主键查询未执行大于零、已审批、未关闭的销售订单
   * 为生产订单提供
   * 
   * @param csaleorderbids 销售订单明细主键
   * @return 销售订单视图
   * @throws BusinessException 异常
   */
  SaleOrderViewVO[] queryToExcuteSaleOrderViewVOs4MO(String[] csaleorderbids)
      throws BusinessException;
}
