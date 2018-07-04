package nc.itf.so.m30.closemanage;

import java.util.Map;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.BusinessException;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.entity.SaleOrderViewVO;
import nc.vo.so.pub.SOParameterVO;

/**
 * 销售订单关闭管理维护接口
 * <p>
 * <b>本类主要完成以下功能：</b>
 * 
 * <ul>
 * <li>销售订单查询
 * <li>销售订单出库关闭
 * <li>销售订单出库打开
 * <li>销售订单开票关闭
 * <li>销售订单开票打开
 * <li>销售订单发货关闭
 * <li>销售订单发货打开
 * <li>销售订单结算关闭
 * <li>销售订单结算打开
 * <li>销售订单行关闭
 * <li>销售订单行打开
 * <li>销售订单整单关闭
 * <li>销售订单整单打开
 * <li>销售订单冻结
 * <li>销售订单解冻
 * </ul>
 * 
 * @version 6.0
 * @author 刘志伟
 * @time 2010-6-17 上午10:25:34
 */
public interface ISaleOrderCloseManageMaintain {

  /**
   * 方法功能描述：关闭整单销售订单。
   * 
   * @throws BusinessException
   * @since 6.0
   */
  SaleOrderVO[] closeSaleOrder(SaleOrderVO[] bills) throws BusinessException;

  /**
   * 方法功能描述：关闭销售订单开票。
   * 
   * @throws BusinessException
   * @since 6.0
   */
  SaleOrderViewVO[] closeSaleOrderInvoice(SaleOrderVO[] vos)
      throws BusinessException;

  /**
   * 方法功能描述：关闭销售订单出库。
   * 
   * @throws BusinessException
   * @since 6.0
   */
  SaleOrderViewVO[] closeSaleOrderOut(SaleOrderVO[] vos)
      throws BusinessException;

  /**
   * 方法功能描述：关闭销售订单行。
   * 
   * @throws BusinessException
   * @since 6.0
   */
  SaleOrderViewVO[] closeSaleOrderRow(SaleOrderViewVO[] views)
      throws BusinessException;

  /**
   * 方法功能描述：关闭销售订单发货。
   * 
   * @throws BusinessException
   * @since 6.0
   */
  SaleOrderViewVO[] closeSaleOrderSend(SaleOrderVO[] vos)
      throws BusinessException;

  /**
   * 方法功能描述：关闭销售订单结算。
   * 
   * @throws BusinessException
   * @since 6.0
   */
  SaleOrderViewVO[] closeSaleOrderSettle(SaleOrderVO[] vos)
      throws BusinessException;

  /**
   * 方法功能描述：订单冻结操作。
   * 
   * @throws BusinessException
   * @since 6.0
   */
  SaleOrderVO[] freezeSaleOrder(SaleOrderVO[] billVOs) throws BusinessException;

  /**
   * 方法功能描述：打开整单销售订单。
   * 
   * @throws BusinessException
   * @since 6.0
   */
  SOParameterVO[] openSaleOrder(SaleOrderVO[] vos, boolean isAbandonATP)
      throws BusinessException;

  /**
   * 方法功能描述：打开销售订单开票。
   * 
   * @throws BusinessException
   * @since 6.0
   */
  SaleOrderViewVO[] openSaleOrderInvoice(SaleOrderVO[] vos)
      throws BusinessException;

  /**
   * 方法功能描述：打开销售订单出库。
   * 
   * @throws BusinessException
   * @since 6.0
   */
  SOParameterVO[] openSaleOrderOut(SaleOrderVO[] vos, boolean isAbandonATP)
      throws BusinessException;

  /**
   * 方法功能描述：打开销售订单行。
   * 
   * @throws BusinessException
   * @since 6.0
   */
  SaleOrderViewVO[] openSaleOrderRow(SaleOrderViewVO[] views)
      throws BusinessException;

  /**
   * 方法功能描述：打开销售订单发货。
   * 
   * @throws BusinessException
   * @since 6.0
   */
  SaleOrderViewVO[] openSaleOrderSend(SaleOrderVO[] vos)
      throws BusinessException;

  /**
   * 方法功能描述：打开销售订单结算。
   * 
   * @throws BusinessException
   * @since 6.0
   */
  SaleOrderViewVO[] openSaleOrderSettle(SaleOrderVO[] vos)
      throws BusinessException;

  /**
   * 方法功能描述：查询销售订单viewVO。
   * 
   * @throws BusinessException
   * @since 6.0
   */
  SaleOrderViewVO[] querySaleOrderViewVO(IQueryScheme queryScheme)
      throws BusinessException;

  /**
   * 方法功能描述：订单解冻操作。
   * 
   * @throws BusinessException
   * @since 6.0
   */
  SaleOrderVO[] unFreezeSaleOrder(SaleOrderVO[] billVOs,
      Map<String, Boolean> businessCheckMap) throws BusinessException;
}
