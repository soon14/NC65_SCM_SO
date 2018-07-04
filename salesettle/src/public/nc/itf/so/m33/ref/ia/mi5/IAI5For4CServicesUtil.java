package nc.itf.so.m33.ref.ia.mi5;

import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.pubitf.ia.mi5.so.IIAI5ForSO4CIntransit;
import nc.pubitf.ia.mi5.so.IIAI5ForSO4COutrush;
import nc.pubitf.ia.mi5.so.IIAI5ForSO4CSettle;
import nc.pubitf.ia.mi5.so.IIAI5ForSOSquareEnd;
import nc.vo.ia.mi5.entity.I5BillVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class IAI5For4CServicesUtil {

  private IAI5For4CServicesUtil() {
    super();
  }

  /**
   * 方法功能描述：销售出库单取消发出商品
   * <p>
   * <b>参数说明</b>
   * 
   * @param ids
   *          <p>
   * @since 6.0
   * @author zhangcheng
   * @time 2010-4-3 下午02:26:47
   */
  public static void deleteI5ForSO4CUnIntransit(String[] outids,
      String[] csettledetailids) throws BusinessException {
    IIAI5ForSO4CIntransit bo =
        NCLocator.getInstance().lookup(IIAI5ForSO4CIntransit.class);
    bo.deleteI5ForSO4CUnIntransit(outids, csettledetailids);
  }

  /**
   * 方法功能描述：销售出库单取消成本结算
   * <p>
   * <b>参数说明</b>
   * 
   * @param ids
   *          <p>
   * @since 6.0
   * @author zhangcheng
   * @time 2010-4-3 下午02:26:47
   */
  public static void deleteI5ForSO4CUnSettle(String[] outids,
      String[] csettledetailids) throws BusinessException {
    IIAI5ForSO4CSettle bo =
        NCLocator.getInstance().lookup(IIAI5ForSO4CSettle.class);
    bo.deleteI5ForSO4CUnSettle(outids, csettledetailids);
  }

  /**
   * 取消销售出库结算关闭删除存货核算单据
   * 
   * @param csrcids 来源单据ID(库存销售出库单ID)
   * @param csrcbids 来源单据行ID(销售结算明细ID)
   * @throws BusinessException
   */
  public static void deleteI5ForSOUnSquareEnd(String[] csrcids,
      String[] csrcbids) {
    IIAI5ForSOSquareEnd bo =
        NCLocator.getInstance().lookup(IIAI5ForSOSquareEnd.class);
    try {
      bo.deleteI5ForSOUnSquareEnd(csrcids, csrcbids);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

  /**
   * 方法功能描述：(销售出库单)销售结算单传发出商品借方
   * <p>
   * <b>参数说明</b>
   * 
   * @param ids
   *          <p>
   * @since 6.0
   * @author zhangcheng
   * @time 2010-4-3 下午02:26:47
   */
  public static void insertI5ForSO4CIntransit(I5BillVO[] i5vos)
      throws BusinessException {
    IIAI5ForSO4CIntransit bo =
        NCLocator.getInstance().lookup(IIAI5ForSO4CIntransit.class);
    bo.insertI5ForSO4CIntransit(i5vos);
  }

  /**
   * 方法功能描述：(销售出库单)销售结算单传发出商品贷方（出库对冲）
   * 
   * @param i5vos
   * @throws BusinessException
   */
  public static void insertI5ForSO4CIntransitForOutrush(I5BillVO[] i5vos) {
    IIAI5ForSO4COutrush bo =
        NCLocator.getInstance().lookup(IIAI5ForSO4COutrush.class);
    try {
      bo.insertI5ForSO4COutrush(i5vos);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

  /**
   * 方法功能描述：取消(销售出库单)销售结算单传发出商品贷方（出库对冲）
   * 
   * @param i5vos
   * @throws BusinessException
   */
  public static void insertI5ForSO4CIUnntransitForOutrush(String[] outids,
      String[] csettledetailids) {
    IIAI5ForSO4COutrush bo =
        NCLocator.getInstance().lookup(IIAI5ForSO4COutrush.class);
    try {
      bo.deleteI5ForSO4CUnOutrush(outids, csettledetailids);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

  /**
   * 方法功能描述：(销售出库单)销售结算单传成本
   * <p>
   * <b>参数说明</b>
   * 
   * @param ids
   *          <p>
   * @since 6.0
   * @author zhangcheng
   * @time 2010-4-3 下午02:26:47
   */
  public static void insertI5ForSO4CSettle(I5BillVO[] i5vos)
      throws BusinessException {
    IIAI5ForSO4CSettle bo =
        NCLocator.getInstance().lookup(IIAI5ForSO4CSettle.class);
    bo.insertI5ForSO4CSettle(i5vos);
  }

  /**
   * 销售出库结算关闭形成存货核算单据
   * 
   * @param bills 存货核算销售成本结转单数组
   * @throws BusinessException
   */
  public static void insertI5ForSOSquareEnd(I5BillVO[] bills) {
    IIAI5ForSOSquareEnd bo =
        NCLocator.getInstance().lookup(IIAI5ForSOSquareEnd.class);
    try {
      bo.insertI5ForSOSquareEnd(bills);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

  /**
   * 根据销售明细查询对应蓝字借方单据尚未回冲的数量
   * 
   * @param csrcids 来源单据ID数组（销售出库单表头ID）
   * @param csrcbids 来源单据行ID数组（销售出库待结算单明细ID）
   * @return MAP
   *         <li> key : 销售出库待结算单明细ID
   *         <li> value: 剩余需要传发出商品贷方的数量
   */
  public static Map<String, UFDouble> querySaleOutRegNotAllSquare(
      String[] csrcids, String[] csrcbids) {
    // Map<String, UFDouble> ret = new HashMap<String, UFDouble>();
    IIAI5ForSOSquareEnd bo =
        NCLocator.getInstance().lookup(IIAI5ForSOSquareEnd.class);
    try {
      Map<String, UFDouble> ret =
          bo.querySaleOutRegNotAllSquare(csrcids, csrcbids);
      return ret;
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return null;
  }

}
