package nc.pubitf.so.m38.mmdp.dt;

import nc.pubitf.so.m38.mmdp.dt.mapvo.SalePreOrderDTDemandMapVO;
import nc.vo.pub.BusinessException;

/**
 * 需求跟踪查询需求-销售预订单字段映射
 * 
 * 过滤条件
 * a) 未删除
 * b) 非红字单据
 * 
 * 约定：字段映射包括别名，别名都用表名。
 * 
 * @since 6.3
 * @version 2012-11-6 下午04:51:52
 * @author 梁吉明
 */
public interface IQuerySalePreOrderMapForDT {
  /**
   * 
   * 获取需求跟踪查询需求-销售预订单字段映射
   * 
   * @return SalePreOrderDTDemandMapVO
   * @throws BusinessException
   */
  SalePreOrderDTDemandMapVO getDTDemandMapVO() throws BusinessException;
}
