package nc.pubitf.so.m30.balend;

import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>销售订单结算关闭提供的接口服务类
 * </ul>
 * <p>
 * 
 * @version 本版本号 6.0
 * @since
 * @author fengjb
 * @time 2010-7-14 上午08:43:08
 */
public interface ISaleOrderBalEndSrv {
  /**
   * 方法功能描述：校验订单行是否成本结算关闭。
   * <p>
   * <b>参数说明</b>
   * 
   * @param saleorderbids
   * @return
   * @throws BusinessException
   *           <p>
   * @author fengjb
   * @time 2010-7-14 上午08:49:36
   */
  UFBoolean[] isCostBalEnd(String[] saleorderbids) throws BusinessException;

  /**
   * 方法功能描述：校验订单行是否应收结算关闭。
   * <p>
   * <b>参数说明</b>
   * 
   * @param saleordbids
   * @return
   * @throws BusinessException
   *           <p>
   * @author fengjb
   * @time 2010-7-14 上午08:47:13
   */
  UFBoolean[] isIncomeBalEnd(String[] saleorderbids) throws BusinessException;

  /**
   * 方法功能描述：处理订单行自动结算关闭,包括自动应收结算关闭和自动成本结算关闭。
   * <p>
   * <b>参数说明</b>
   * 
   * @param saleorderbids
   * @throws BusinessException
   *           <p>
   * @author fengjb
   * @time 2010-7-14 上午08:51:30
   */
  void processAutoBalEnd(BalEndPara para) throws BusinessException;

  /**
   * 方法功能描述：处理订单行自动结算打开，包括自动应收结算打开和自动成本结算打开。
   * <p>
   * <b>参数说明</b>
   * 
   * @param saleorderbids
   * @throws BusinessException
   *           <p>
   * @author fengjb
   * @time 2010-7-14 上午09:10:13
   */
  void processAutoBalOpen(BalOpenPara para) throws BusinessException;
}
