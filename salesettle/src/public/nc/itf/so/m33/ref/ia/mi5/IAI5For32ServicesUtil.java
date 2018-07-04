package nc.itf.so.m33.ref.ia.mi5;

import nc.bs.framework.common.NCLocator;
import nc.pubitf.ia.mi5.so.IIAI5ForSO32Intransit;
import nc.pubitf.ia.mi5.so.IIAI5ForSO32Settle;
import nc.vo.ia.mi5.entity.I5BillVO;
import nc.vo.pub.BusinessException;

public class IAI5For32ServicesUtil {
  
  private IAI5For32ServicesUtil() {
    super();
  }
  
  /**
   * 方法功能描述：销售发票取消发出商品
   * <p>
   * <b>参数说明</b>
   * 
   * @param ids
   *          <p>
   * @since 6.0
   * @author zhangcheng
   * @time 2010-4-3 下午02:26:47
   */
  public static void deleteI5ForSO32UnIntransit(String[] invids,
      String[] csettledetailids) throws BusinessException {
    IIAI5ForSO32Intransit bo =
        NCLocator.getInstance().lookup(IIAI5ForSO32Intransit.class);
    bo.deleteI5ForSO32UnIntransit(invids, csettledetailids);
  }

  /**
   * 方法功能描述：销售发票取消成本结算
   * <p>
   * <b>参数说明</b>
   * 
   * @param ids
   *          <p>
   * @since 6.0
   * @author zhangcheng
   * @time 2010-4-3 下午02:26:47
   */
  public static void deleteI5ForSO32UnSettle(String[] invids,
      String[] csettledetailids) throws BusinessException {
    IIAI5ForSO32Settle bo =
        NCLocator.getInstance().lookup(IIAI5ForSO32Settle.class);
    bo.deleteI5ForSO32UnSettle(invids, csettledetailids);
  }

  /**
   * 方法功能描述：(销售发票)销售结算单传发出商品
   * <p>
   * <b>参数说明</b>
   * 
   * @param ids
   *          <p>
   * @since 6.0
   * @author zhangcheng
   * @time 2010-4-3 下午02:26:47
   */
  public static void insertI5ForSO32Intransit(I5BillVO[] i5vos)
      throws BusinessException {
    IIAI5ForSO32Intransit bo =
        NCLocator.getInstance().lookup(IIAI5ForSO32Intransit.class);
    bo.insertI5ForSO32Intransit(i5vos);
  }

  /**
   * 方法功能描述：(销售发票)销售结算单传成本
   * <p>
   * <b>参数说明</b>
   * 
   * @param ids
   *          <p>
   * @since 6.0
   * @author zhangcheng
   * @time 2010-4-3 下午02:26:47
   */
  public static void insertI5ForSO32Settle(I5BillVO[] i5vos)
      throws BusinessException {
    IIAI5ForSO32Settle bo =
        NCLocator.getInstance().lookup(IIAI5ForSO32Settle.class);
    bo.insertI5ForSO32Settle(i5vos);
  }

}
