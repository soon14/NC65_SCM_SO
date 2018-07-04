package nc.pubitf.so.m30.opc.mecc;

import nc.vo.pub.BusinessException;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderViewVO;

/**
 * 销售订单提供给订单统一处理中心的接口
 * 接口被调用场景：
 * 电子销售集成部署时提供用户在门户做ASN查询时,根据输入的查询条件，
 * 先找出符合条件的预订单编号或与预订单的集合，再查出所对应的销售订单或集合,
 * 最后才能联查出销售订单对应的出库单信息。
 * 
 * @since 6.0
 * @version 2012-2-13 下午03:41:13
 * @author 刘景
 */

public interface ISaleOrderQueryForMecc {

  /**
   * 根据订单统一处理中心预订单ID、行ID获取下游销售订单信息
   * 注意：传入预订单头ID、行ID的值不能 重复，也不能为空。
   * 
   * @param messids 订单统一处理中心预订单id
   * @param messbids 订单统一处理中心预订单表体id
   * @param fieldnames 查询的字段
   * @return
   * @throws BusinessException
   */
  SaleOrderBVO[] query(String[] messids, String[] messbids, String[] fieldnames)
      throws BusinessException;

  /**
   * 根据输入的销售订单编号查询销售订单信息
   * 
   * @param saleorerbids 销售订单体ID数组
   * @param fieldnames 订单查询的字段数组
   * @return 销售订单ViewVO数组
   * @throws BusinessException
   * @author 梁吉明
   */
  SaleOrderViewVO[] querySaleOrderViewVO(String[] saleorerbids,
      String[] fieldnames) throws BusinessException;

}
