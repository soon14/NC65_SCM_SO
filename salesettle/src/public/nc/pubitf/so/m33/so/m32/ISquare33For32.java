package nc.pubitf.so.m33.so.m32;

import java.util.Map;

import nc.vo.pub.BusinessException;
import nc.vo.so.m32.entity.SaleInvoiceVO;

/**
 * 销售结算提供给销售发票服务：
 * 
 * 1.销售发票推式生成销售结算单
 * 
 * 2.销售发票取消结算
 * 
 * @author zhangcheng
 * 
 */
public interface ISquare33For32 {

  /**
   * 销售发票取消结算
   * 
   * @param voInvoice
   * @throws BusinessException
   */
  void cancelSquareSrv(SaleInvoiceVO[] voInvoice) throws BusinessException;

  /**
   * 销售发票审核自动推式生成销售结算单
   * 
   * @param voInvoice
   * @throws BusinessException
   */
  void pushSquareSrv(SaleInvoiceVO[] voInvoice) throws BusinessException;

  /**
   * 结算单为销售发票提供的查询发票行结算单明细服务
   * 
   * @param invhids 发票表头ID数组
   * @param invbids 发票表体ID数组
   * @return Map<String,String> Key:结算单明细行ID Value:对应发票表体ID
   * @throws BusinessException
   * @author 梁吉明
   * @time 2012-9-20 下午07:45:44
   */
  Map<String, String> queryInvSquareDetail(String[] invhids, String[] invbids)
      throws BusinessException;
}
