package nc.pubitf.so.m33.self.pub;

import java.util.Map;

import nc.vo.pub.lang.UFDouble;
import nc.vo.so.m33.m4453.entity.SquareWasDetailVO;
import nc.vo.so.m33.m4453.entity.SquareWasViewVO;

public interface ISquare4353Query {

  /**
   * 方法功能描述：查询下游途损单累计传确认应收数据
   * <p>
   * <b>参数说明</b>
   * 
   * @param outBids -- 销售出库单行id
   * @return <出库单行id,[0]累计应收数量,[1]累计应收含税金额,[2]累计应收无税金额>
   *         <p>
   * @author zhangcheng
   * @time 2010-9-1 上午11:48:42
   */
  Map<String, UFDouble[]> queryARNumBy4CBID(String[] outBids);

  /**
   * 方法功能描述：查询下游途损单累计传回冲应收数据
   * <p>
   * <b>参数说明</b>
   * 
   * @param outBids -- 销售出库单行id
   * @return <出库单行id,[0]累计应收数量,[1]累计应收含税金额,[2]累计应收无税金额>
   *         <p>
   * @author zhangcheng
   * @time 2010-9-1 上午11:48:42
   */
  Map<String, UFDouble[]> queryARRushNumBy4CBID(String[] outBids);

  /**
   * 方法功能描述：根据途损单表体id查询已经做过回冲应收的途损结算单
   * <p>
   * <b>参数说明</b>
   * 
   * @param wasBids -- 途损单表体id
   * @return
   *         <p>
   * @author zhangcheng
   * @time 2010-9-21 下午04:02:48
   */
  SquareWasDetailVO[] querySquareWasDetailVOByBIDForETRushSquare(
      String[] wasBids);

  /**
   * 方法功能描述：根据途损单表体id查询未做过结算的途损结算单
   * <p>
   * <b>参数说明</b>
   * 
   * @param wasBids -- 途损单表体id
   * @return
   *         <p>
   * @author zhangcheng
   * @time 2010-9-21 下午04:02:48
   */
  SquareWasViewVO[] querySquareWasDetailVOByBIDForNoSquare(String[] wasBids);

  /**
   * 方法功能描述：根据途损单表体id查询已经做过发出商品结算的途损结算单
   * <p>
   * <b>参数说明</b>
   * 
   * @param wasBids -- 途损单表体id
   * @return
   *         <p>
   * @author zhangcheng
   * @time 2010-9-21 下午04:02:48
   */
  SquareWasDetailVO[] querySquareWasDetailVOByBIDForREGSquare(String[] wasBids);

  /**
   * 方法功能描述：根据途损单表体id查询已经做过结算的途损结算单
   * <p>
   * <b>参数说明</b>
   * 
   * @param wasBids -- 途损单表体id
   * @return
   *         <p>
   * @author zhangcheng
   * @time 2010-9-21 下午04:02:48
   */
  SquareWasDetailVO[] querySquareWasDetailVOByBIDForSquare(String[] wasBids);

  /**
   * 方法功能描述：根据途损待结算单表体id查询途损待结算单视图vo
   * <p>
   * <b>参数说明</b>
   * 
   * @param bids -- 途损单表体id
   * @return
   *         <p>
   * @author zhangcheng
   * @time 2010-9-21 下午04:02:48
   */
  SquareWasViewVO[] querySquareWasViewVOByBID(String[] bids);

  /**
   * 方法功能描述：根据途损单表体id查询未回冲应收的途损待结算单视图vo
   * <p>
   * <b>参数说明</b>
   * 
   * @param wasBids -- 途损单表体id
   * @return
   *         <p>
   * @author zhangcheng
   * @time 2010-9-21 下午04:02:48
   */
  SquareWasViewVO[] querySquareWasViewVOByBIDForNoETRushSquare(String[] wasBids);

  /**
   * 方法功能描述：根据途损单表体id查询未发出商品结算的途损待结算单视图vo
   * <p>
   * <b>参数说明</b>
   * 
   * @param wasBids -- 途损单表体id
   * @return
   *         <p>
   * @author zhangcheng
   * @time 2010-9-21 下午04:02:48
   */
  SquareWasViewVO[] querySquareWasViewVOByBIDForNoREGSquare(String[] wasBids);
}
