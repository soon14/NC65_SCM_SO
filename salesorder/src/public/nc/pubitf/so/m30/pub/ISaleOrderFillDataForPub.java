package nc.pubitf.so.m30.pub;

import nc.vo.pub.BusinessException;
import nc.vo.so.m30.entity.SaleOrderVO;

/**
 * 
 * 销售订单针对外模块生成销售订单时提供的补全数据接口
 * 
 * @since 6.31
 * @version 2013-11-20 08:55:35
 * @author liujingn
 */
public interface ISaleOrderFillDataForPub {

  /**
   * 对销售订单基本业务规则数据进行填充包括：
   * 业务流程、开票客户、单据状态、单据日期、计划发货日期、计划发货日期、整单折扣、单品折扣、行状态、国家、购销类型
   * 、税信息相关、收款协议信息、 直运仓、集团、全局汇率计算
   * 
   * @param ordervos 销售订单VO
   * @return 销售订单VO
   * @throws BusinessException
   */
  SaleOrderVO[] getFillSaleorderVO(SaleOrderVO[] ordervos)
      throws BusinessException;

  /**
   * 计算销售订单表体数量单价金额。通过指定editkey来触发公共单价金额算法
   * 
   * @param ordervos 销售订单VO
   * 
   * @param editkey 触发单价金额算法字段
   * 
   * @throws BusinessException
   */
  void calSaleOrderNumpriceMny(SaleOrderVO[] ordervos, String editkey)
      throws BusinessException;

}
