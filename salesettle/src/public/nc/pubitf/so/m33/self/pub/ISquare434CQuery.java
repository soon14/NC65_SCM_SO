package nc.pubitf.so.m33.self.pub;

import java.util.Map;

import nc.vo.pub.lang.UFDouble;
import nc.vo.so.m33.m4c.entity.SquareOutDetailVO;
import nc.vo.so.m33.m4c.entity.SquareOutViewVO;

/**
 * 销售出库待结算单、结算单对外提供的查询服务
 * 
 */
public interface ISquare434CQuery {

  /**
   * 方法功能描述：查询下游签收开票退回出库单累计传确认应收数据
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
   * 方法功能描述：查询下游签收开票退回出库单累计传回冲应收数据
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
   * 根据出库单表体id查询 已经做过暂估应收的出库单表体id
   * 
   * @param outBids -- 出库单表体id
   * @return 已经做过暂估应收的出库单表体id
   */
  String[] queryETIncomeBidBy4CBID(String[] outBids);

  /**
   * 根据出库单表体id查询 已经做过暂估应收的出库单表体id
   * 
   * @param outBids -- 出库单表体id
   * @return 已经做过暂估应收的销售出库结算单
   */
  Map<String, SquareOutDetailVO> queryETIncomeDvosBy4CBID(String[] outBids);

  /**
   * 根据出库单表体id查询 已经做过暂估应收或者发出商品的出库单待结算单
   * 
   * @param outBids -- 出库单表体id
   * @return 已经做过暂估应收的出库单表体id
   */
  SquareOutViewVO[] queryETIncomeREGCostBidBy4CBID(String[] outBids);

  /**
   * 根据出库单表体id查询 已经做过暂估应收的出库单表体id
   * 
   * @param outBids -- 出库单表体id
   * @return 已经做过暂估应收的出库单表体id
   */
  String[] queryREGCostBidBy4CBID(String[] outBids);

  /**
   * 根据销售出库单bid查询销售出库结算单
   * 
   * @param bidValues -- 销售出库单表体id
   * @return SquareOutDetailVO[]
   */
  SquareOutDetailVO[] querySquareOutDetailVOBy4CBID(String[] bidValues);

  /**
   * 根据销售出库结算单PK查询销售出库结算单
   * 
   * @param OutDetailPKs
   * @return SquareOutDetailVO[]
   */
  SquareOutDetailVO[] querySquareOutDetailVOByPK(String[] outDetailPKs);

  /**
   * 根据销售出库单bid查询销售出库待结算单
   * 
   * @param bidValues -- 销售出库单表体id
   * @return SquareOutDetailVO[]
   */
  SquareOutViewVO[] querySquareOutViewVOBy4CBID(String[] outbids);

}
