package nc.pubitf.so.m33.so.m30;

import nc.vo.so.m33.m32.entity.SquareInvViewVO;

/**
 * 销售发票结算单为订单关闭提供服务接口
 * 
 * @since 6.1
 * @version 2012-11-29 11:10:10
 * @author 冯加彬
 */
public interface ISquare32QryFor30SqEnd {

  /**
   * 方法功能描述：根据订单行ID查询销售订单下游发票行是否结算关闭
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
   * @time 2010-8-31 下午02:06:47
   */
  RetVOFor30[] querySqEndByOrdBID(String[] ordBids);

  /**
   * 方法功能描述：根据订单行ID查询销售订单下游结算单据销售发票信息
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
   * @time 2010-8-31 下午02:06:44
   */
  SquareInvViewVO[] queryViewVOByOrdBID(String[] ordBids);

}
