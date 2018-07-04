package nc.pubitf.so.m38.mmpps.planbusi;

import nc.pubitf.so.m38.mmpps.planbusi.mapvo.SalePreOrderSADemandMapVO;
import nc.vo.pub.BusinessException;

/**
 * 供给影响分析查询需求-销售预订单字段映射
 * 
 * 过滤条件
 * a) 未删除
 * b) 非红字单据
 * 
 * 约定：字段映射包括别名，别名都用表名。
 * 
 * @since 6.3
 * @version 2012-11-6 下午07:35:24
 * @author 梁吉明
 */
public interface IQuerySalePreOrderMapForSA {
  /**
   * 
   * 获取供给影响分析查询需求-销售预订单字段映射
   * 
   * @return SalePreOrderSADemandMapVO
   * @throws BusinessException
   */
  SalePreOrderSADemandMapVO getSADemandMapVO() throws BusinessException;
}
