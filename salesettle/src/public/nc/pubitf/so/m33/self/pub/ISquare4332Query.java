package nc.pubitf.so.m33.self.pub;

import java.util.Map;

import nc.vo.pub.lang.UFDouble;
import nc.vo.so.m33.m32.entity.SquareInvViewVO;

public interface ISquare4332Query {

  /**
   * 方法功能描述：查询下游销售发票累计传应收数据
   * <p>
   * <b>参数说明</b>
   * 
   * @param outBids -- 销售出库单行id
   * @return
   *         <p>
   * @author zhangcheng
   * @time 2010-9-1 上午11:48:42
   */
  Map<String, UFDouble> queryARNumBy4CBID(String[] outBids);

  /**
   * 根据销售发票表体id查询 已经成本结算的销售发票结算vo
   * 
   * @param invBids
   *          -- 销售发票表体id
   * @return 已经成本结算的销售发票结算vo
   */
  SquareInvViewVO[] queryCostSquareInvVOBy32BID(String[] invBids);

}
