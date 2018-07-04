package nc.itf.so.m30.ref.so.m33;

import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.pubitf.so.m33.so.m30.IProcess4CAdjustFor30BalEnd;
import nc.pubitf.so.m33.so.m30.IQuerySquareBillFor30SqEnd;

/**
 * SO——销售结算服务
 * 
 * @since 6.0
 * @version 2011-8-4 下午08:22:42
 * @author 刘志伟
 */
public class SOm33ServicesUtil {

  /**
   * 方法功能描述：订单结算关闭后出库单做过暂估应收需要生成对应的回冲应收单
   * <p>
   * <b>参数说明</b>
   * 
   * @param ordBids -- 下游出库单做过暂估应收的订单行ID数组
   *          <p>
   * @author zhangcheng
   * @time 2010-8-31 下午03:22:04
   */
  public static void process4CAdjust(String[] ordBids) {
    // 暂估应收结算关闭后生成回冲应收单
    IProcess4CAdjustFor30BalEnd adjustsrv =
        NCLocator.getInstance().lookup(IProcess4CAdjustFor30BalEnd.class);
    adjustsrv.process4CAdjust(ordBids);
  }

  /**
   * 方法功能描述：订单结算关闭后出库单做过发出商品需要生成贷方发出商品
   * <p>
   * <b>参数说明</b>
   * 
   * @param ordBids -- 下游出库单做过发出商品的订单行ID数组
   *          <p>
   * @author zhangcheng
   * @time 2010-8-31 下午03:22:04
   */
  public static void process4CReg(String[] ordBids) {
    IProcess4CAdjustFor30BalEnd adjustsrv =
        NCLocator.getInstance().lookup(IProcess4CAdjustFor30BalEnd.class);
    adjustsrv.process4CReg(ordBids);
  }

  /**
   * 方法功能描述：订单结算打开后出库单做过暂估应收，将生成对应的回冲应收单取消
   * <p>
   * <b>参数说明</b>
   * 
   * @param ordBids -- 下游出库单做过暂估应收的订单行ID数组
   *          <p>
   * @author zhangcheng
   * @time 2010-8-31 下午03:22:04
   */
  public static void unProcess4CAdjust(String[] ordBids) {
    IProcess4CAdjustFor30BalEnd adjustsrv =
        NCLocator.getInstance().lookup(IProcess4CAdjustFor30BalEnd.class);
    adjustsrv.unProcess4CAdjust(ordBids);
  }

  /**
   * 方法功能描述：订单结算打开后出库单做过发出商品，将生成对应的贷方发出商品取消
   * <p>
   * <b>参数说明</b>
   * 
   * @param ordBids -- 下游出库单做过发出商品的订单行ID数组
   *          <p>
   * @author zhangcheng
   * @time 2010-8-31 下午03:22:04
   */
  public static void unProcess4CReg(String[] ordBids) {
    IProcess4CAdjustFor30BalEnd adjustsrv =
        NCLocator.getInstance().lookup(IProcess4CAdjustFor30BalEnd.class);
    adjustsrv.unProcess4CReg(ordBids);
  }

  /**
   * 查询当前业务流程中结算单据类型，销售出库单结算或者销售发票结算
   * 
   * @param orderbids 销售订单行id(不可重复)
   * @param busiids 业务流程id (不可重复)
   * @return (业务流程ID,结算单据类型[]{0 应收结算单据类型 1 成本结算单据类型} )
   *         如果没有参与结算相应的值为null
   */
  public static Map<String, String[]> querySquareBillFor30SqEnd(
      String[] orderbids, String[] busiids) {
    IQuerySquareBillFor30SqEnd service =
        NCLocator.getInstance().lookup(IQuerySquareBillFor30SqEnd.class);
    return service.querySquareBillFor30SqEnd(orderbids, busiids);
  }
}
