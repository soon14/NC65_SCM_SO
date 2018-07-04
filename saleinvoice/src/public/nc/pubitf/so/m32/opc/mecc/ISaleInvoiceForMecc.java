package nc.pubitf.so.m32.opc.mecc;

import nc.vo.pub.BusinessException;
import nc.vo.so.m32.entity.SaleInvoiceViewVO;
import nc.vo.so.m32.opc.mecc.SaleInvoiceQueryConditionVO;

/**
 * 查询发票信息接口
 * 
 * @since 6.3
 * @version 2012-10-22 下午07:41:31
 * @author 梁吉明
 */
public interface ISaleInvoiceForMecc {
  /**
   * 根据销售订单查询发票
   * 
   * @param saleorderhids 销售订单表头ID数组
   * @param saleorderbids 销售订单表体ID数组
   * @param fieldnames 发票字段数组
   * @return 销售发票viewVO数组
   * @throws BusinessException
   */
  SaleInvoiceViewVO[] querySaleInvoiceByOrder(String[] saleorderhids,
      String[] saleorderbids, String[] fieldnames) throws BusinessException;

  /**
   * 根据客户、日期区间及单据状态查询发票信息
   * 
   * @param condition 查询条件VO
   * @return 销售发票viewVO数组
   * @throws BusinessException
   */
  SaleInvoiceViewVO[] querySaleInvoiceByCondition(
      SaleInvoiceQueryConditionVO condition, String[] fieldnames)
      throws BusinessException;
}
