package nc.pubitf.so.m30.mmdp.aid;

import java.util.List;

import nc.vo.pub.BusinessException;
import nc.vo.so.m30.entity.SaleOrderVO;

/**
 * 销售订单提供给实际独立需求的接口
 * 
 * @since 6.0
 * @version 2011-12-5 下午03:21:36
 * @author 么贵敬
 */
public interface ISaleOrderForAid {

  /**
   * 根据销售订单附表PK查询销售订单信息
   * 
   * @param csaleorderbids 表体ID
   * @return SaleOrderVO 订单聚合VO
   * @throws BusinessException
   * @author 王天文
   */
  SaleOrderVO[] queryOrderByBid(String[] csaleorderbids)
      throws BusinessException;

  /**
   * 实际独立需求界面支持查询符合指定工厂、物料分类、单据状态、客户、
   * 最后修改日期等查询条件的销售预订单、销售订单的单据信息；指定工厂匹配销售预订单、
   * 销售订单的计划发货组织
   * 
   * @param para
   *          para中起始单据日期dendbilldate,
   *          截止单据日期dbeginbilldate,
   *          销售订单发货组织列表 csendstockorgids,
   *          物料ID列表 cmaterialids,
   *          单据状态列表 fstatusflags属性必输
   * @return 结果VO
   * @throws BusinessException
   */
  List<ResultVO> queryOrderDetails(ParaVO para) throws BusinessException;
}
