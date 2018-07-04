package nc.pubitf.so.m32.so.m30;

import java.util.Map;

import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;

public interface IQuery32For30 {

  /**
   * 获得订单下游发票的审批数量
   * 
   * @param ids
   * @param bids 订单行ID
   * @return 订单行ID为KEY 审批数量为VALUE的值
   * @throws BusinessException
   */
  Map<String, UFDouble> getInvoiceApproveNum(String[] ids, String[] bids)
      throws BusinessException;

  /**
   * 检查订单行下游发票是否全部审核
   * 
   * @param orderid
   * @param orderbids 订单行ID
   * @return 是否全部审核
   * @throws BusinessException
   */
  UFBoolean[] isInvoiceAllApprove(String[] orderid, String[] orderbids)
      throws BusinessException;

  /**
   * 订单是否存在下游发票
   * 
   * @param orderhids
   * 
   * @return 是否存在下游发票
   * @throws BusinessException
   */
  Map<String, UFBoolean> isExistNextInvoice(String[] orderhids)
      throws BusinessException;

  /**
   * 获得订单下游发票的开票金额（价税合计）
   * 
   * @param ids
   * @param bids 订单行ID
   * @return 订单行ID为KEY 开票金额为VALUE的值
   * @throws BusinessException
   */
  Map<String, UFDouble> getInvoiceNorigtaxmny(String[] ids, String[] bids)
      throws BusinessException;
}
