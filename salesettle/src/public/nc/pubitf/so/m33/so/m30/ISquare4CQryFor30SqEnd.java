package nc.pubitf.so.m33.so.m30;

import java.util.Map;

import nc.vo.so.m30.balend.enumeration.VirtualBalType;
import nc.vo.so.m33.m4c.entity.SquareOutViewVO;

/**
 * 销售出库结算单为订单关闭提供服务接口
 * 
 * @since 6.1
 * @version 2012-11-29 11:11:05
 * @author 冯加彬
 */
public interface ISquare4CQryFor30SqEnd {

  /**
   * 方法功能描述：对应的出库单虚权组件结算类型 VirtualBalType 数组
   * <p>
   * 使用示例
   * <p>
   * <b>参数说明</b>
   * 
   * @param ordBids --- 销售订单表体id
   * @return
   *         <p>
   *         // 出库单已结算 public static final VirtualBalType BALED =
   *         MDEnum.valueOf(VirtualBalType.class, Integer.valueOf(0));
   *         <p>
   *         // 出库单半结算(即销售出库单下游发票已经结算或者销售出库单做过出库对冲已经无法再结算)
   *         <p>
   *         public static final VirtualBalType HALFBAL =
   *         MDEnum.valueOf(VirtualBalType.class, Integer.valueOf(1));
   *         <p>
   *         // 出库单未结算 public static final VirtualBalType NOTBAL =
   *         MDEnum.valueOf(VirtualBalType.class, Integer.valueOf(2));
   *         <p>
   * @author zhangcheng
   * @time 2010-9-13 上午10:39:24
   */
  Map<String, VirtualBalType> query4CVirtualBalType(String[] ordBids);

  /**
   * 根据订单行ID查询销售订单下游已经暂估但未完成回冲完的销售出库待结算单信息
   * 
   * @param ordBids
   * @return 出库结算视图VO
   */
  SquareOutViewVO[] queryETViewVOByOrdBIDForOrderEnd(String[] ordBids);

  /**
   * 根据订单行ID查询销售订单下游已经发出商品借方未完全贷方回冲的销售出库待结算单
   * 
   * @param ordBids
   * @return 出库结算视图VO
   */
  SquareOutViewVO[] queryREGViewVOByOrdBIDForOrderEnd(String[] ordBids);

  /**
   * 方法功能描述：根据订单行ID查询销售订单下游出库单行是否结算关闭
   * <p>
   * <b>examples:</b>
   * <p>
   * 使用示例
   * <p>
   * <b>参数说明</b>
   * 
   * @param ordBids
   * @return RetVOFor30
   *         <p>
   * @author zhangcheng
   * @time 2010-8-31 下午02:08:39
   */
  RetVOFor30[] querySqEndByOrdBID(String[] ordBids);

  /**
   * 方法功能描述：根据订单行ID查询销售订单下游结算单据销售出库单信息
   * <p>
   * <b>examples:</b>
   * <p>
   * 使用示例
   * <p>
   * <b>参数说明</b>
   * 
   * @param ordBids
   * @return
   *         <p>
   * @author zhangcheng
   * @time 2010-8-31 下午02:07:16
   */
  SquareOutViewVO[] queryViewVOByOrdBID(String[] ordBids);
}
