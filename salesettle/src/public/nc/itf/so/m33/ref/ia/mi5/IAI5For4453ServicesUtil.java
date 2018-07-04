package nc.itf.so.m33.ref.ia.mi5;

import nc.bs.framework.common.NCLocator;
import nc.pubitf.ia.mi5.so.IIAI5ForSO4453Intransit;
import nc.pubitf.ia.mi5.so.IIAI5ForSO4453Settle;
import nc.vo.ia.mi5.entity.I5BillVO;
import nc.vo.pub.BusinessException;

public class IAI5For4453ServicesUtil {

  private IAI5For4453ServicesUtil() {
    super();
  }
  
  /**
   * 方法功能描述：途损单取消发出商品
   * <p>
   * <b>参数说明</b>
   * 
   * @param ids
   *          <p>
   * @since 6.0
   * @author zhangcheng
   * @time 2010-4-3 下午02:26:47
   */
  public static void deleteI5ForSO4453UnIntransit(String[] wasids,
      String[] csettledetailids) throws BusinessException {
    IIAI5ForSO4453Intransit bo =
        NCLocator.getInstance().lookup(IIAI5ForSO4453Intransit.class);
    bo.deleteI5ForSO4453UnIntransit(wasids, csettledetailids);
  }

  /**
   * 方法功能描述：途损单取消成本结算
   * <p>
   * <b>参数说明</b>
   * 
   * @param ids
   *          <p>
   * @since 6.0
   * @author zhangcheng
   * @time 2010-4-3 下午02:26:47
   */
  public static void deleteI5ForSO4453UnSettle(String[] wasids,
      String[] csettledetailids) throws BusinessException {
    IIAI5ForSO4453Settle bo =
        NCLocator.getInstance().lookup(IIAI5ForSO4453Settle.class);
    bo.deleteI5ForSO4453UnSettle(wasids, csettledetailids);
  }

  /**
   * 方法功能描述：(途损单)销售结算单传发出商品
   * <p>
   * <b>参数说明</b>
   * 
   * @param ids
   *          <p>
   * @since 6.0
   * @author zhangcheng
   * @time 2010-4-3 下午02:26:47
   */
  public static void insertI5ForSO4453Intransit(I5BillVO[] i5vos)
      throws BusinessException {
    IIAI5ForSO4453Intransit bo =
        NCLocator.getInstance().lookup(IIAI5ForSO4453Intransit.class);
    bo.insertI5ForSO4453Intransit(i5vos);
  }

  /**
   * 方法功能描述：(途损单)销售结算单传成本
   * <p>
   * <b>参数说明</b>
   * 
   * @param ids
   *          <p>
   * @since 6.0
   * @author zhangcheng
   * @time 2010-4-3 下午02:26:47
   */
  public static void insertI5ForSO4453Settle(I5BillVO[] i5vos)
      throws BusinessException {
    IIAI5ForSO4453Settle bo =
        NCLocator.getInstance().lookup(IIAI5ForSO4453Settle.class);
    bo.insertI5ForSO4453Settle(i5vos);
  }

}
