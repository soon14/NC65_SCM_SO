package nc.pubitf.so.m32.ic.m4c;

import nc.vo.pub.BusinessException;
import nc.vo.so.m32.entity.SaleInvoiceBVO;
import nc.vo.so.m32.entity.SaleInvoiceViewVO;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>销售发票为出库单消耗汇总提供的服务接口
 * </ul>
 * <p>
 * 
 * @version 本版本号 6.0
 * @since
 * @author fengjb
 * @time 2010-7-27 上午10:19:21
 */
public interface ISaleInvoiceToVmi {

  /**
   * 方法功能描述：出库消耗汇总取消时清除销售发票上消耗汇总ID。
   * <p>
   * <b>参数说明</b>
   * 
   * @param invoicebids
   * @throws BusinessException
   *           <p>
   * @author fengjb
   * @time 2010-7-8 下午04:25:31
   */
  void clearM32SumID(String[] invoicebids) throws BusinessException;

  /**
   * 方法功能描述：传入查询条件数组，返回符合记录的销售发票视图VO
   * <p>
   * <b>参数说明</b>
   * 
   * @param fromwhereSql
   * @return
   * @throws BusinessException
   *           <p>
   * @author fengjb
   * @time 2010-7-8 下午04:17:18
   */
  SaleInvoiceViewVO[] queryConsumeInvoice(String sql) throws BusinessException;

  /**
   * 方法功能描述：根据销售发票子表ID查询销售发票视图VO。
   * <p>
   * <b>参数说明</b>
   * 
   * @param invoicebids
   * @return
   * @throws BusinessException
   *           <p>
   * @author fengjb
   * @time 2010-7-27 下午06:59:11
   */
  SaleInvoiceViewVO[] queryInvoiceBybids(String[] invoicebids)
      throws BusinessException;

  /**
   * 方法功能描述：根据消耗汇总ID查询销售发票视图VO。
   * <p>
   * <b>参数说明</b>
   * 
   * @param sumids
   * @return
   * @throws BusinessException
   *           <p>
   * @author fengjb
   * @time 2010-7-27 下午06:43:45
   */
  SaleInvoiceViewVO[] queryInvoiceBySumids(String[] sumids)
      throws BusinessException;

  /**
   * 
   * 方法功能描述：按照传入的表头和表体字段，对销售发票进行汇总后返回。
   * <p>
   * <b>参数说明</b>
   * 
   * @param invoicebids
   * @param headkeys
   * @param bodykeys
   * @return
   * @throws BusinessException
   *           <p>
   * @author fengjb
   * @time 2010-9-13 上午09:24:25
   */
  SaleInvoiceViewVO[] queryVmiSumInvoice(String[] invoicebids,
      String[] headkeys, String[] bodykeys) throws BusinessException;

  /**
   * 方法功能描述：出库汇总回写销售发票上消耗汇总ID。
   * <p>
   * <b>参数说明</b>
   * 
   * @param invoicebids
   * @param sumids
   * @throws BusinessException
   *           <p>
   * @author fengjb
   * @time 2010-7-8 下午04:23:43
   */
  void rewriteM32SumID(String[] invoicebids, String[] sumids)
      throws BusinessException;

  /**
   * 
   * 方法功能描述：根据来源单据ID查询发票表体信息。
   * 
   * @param srchids 来源单据主表ID数组
   * @param srcbids 来源单据子表ID数组
   * @param qrykeys 查询字段
   * @return
   */
  SaleInvoiceBVO[] queryInvoiceBodyBySrc(String[] srchids, String[] srcbids,
      String[] qrykeys) throws BusinessException;
}
