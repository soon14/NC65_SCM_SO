package nc.itf.so.m32;

import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pflow.PfUserObject;
import nc.vo.so.m32.entity.SaleInvoiceVO;

/**
 * 销售发票操作 用于业务日志配置的接口
 * <p>
 * <b>本类主要完成以下功能：</b>
 * 
 * <ul>
 * <li>新增、修改、删除操作。
 * </ul>
 * 
 * <p>
 * 
 * @author buxh
 * 
 */

public interface ISaleInvoiceScriptMaintain {

  /**
   * 销售发票修改保存功能。
   * 
   * @param vos
   * @param userObj
   * @param originBills
   * @return 更新后的vo
   * @throws BusinessException
   */
  SaleInvoiceVO[] saleInvoiceUpdate(SaleInvoiceVO[] vos, PfUserObject userObj,
      SaleInvoiceVO[] originBills) throws BusinessException;

  /**
   * 销售订单新增保存功能。
   * 
   * @param vos
   * @param userObj
   * @return 插入后的vo
   * @throws BusinessException
   */
  SaleInvoiceVO[] saleInvoiceInsert(SaleInvoiceVO[] vos, PfUserObject userObj)
      throws BusinessException;

  /**
   * 销售发票删除功能
   * 
   * @param vos
   * @throws BusinessException
   */
  void saleInvoiceDelete(SaleInvoiceVO[] vos) throws BusinessException;

}
