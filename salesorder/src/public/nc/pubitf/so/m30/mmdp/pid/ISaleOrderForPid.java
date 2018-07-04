package nc.pubitf.so.m30.mmdp.pid;

import java.util.List;

import nc.vo.pub.BusinessException;

/**
 * 销售订单提供给计划独立需求的接口
 * 
 * @since 6.0
 * @version 2011-12-5 下午03:21:36
 * @author 么贵敬
 */
public interface ISaleOrderForPid {

  /**
   * 销售订单数量：查询计划发货期介于对应计划独立需求时间段，对应物料+辅助属性，（发货库存组织 = 工厂），
   * 的已审批的销售订单主数量之和
   * 
   * @param parammvo
   * @return
   * @throws BusinessException
   */
  List<ResultVO> queryOrderNnum(ParaMMVO parammvo) throws BusinessException;
}
