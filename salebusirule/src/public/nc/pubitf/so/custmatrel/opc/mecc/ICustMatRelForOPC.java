package nc.pubitf.so.custmatrel.opc.mecc;

import nc.vo.pub.BusinessException;

/**
 * 销售客户物料关系定义给订单统一处理中心提供的接口
 * 用于查询满足条件的客户物料集合
 * 根据输入的销售组织+客户+物料集合得到允许销售的客户+物料集合
 * 
 * @since 6.0
 * @version 2011-12-28 下午03:31:43
 * @author 刘景
 */

public interface ICustMatRelForOPC {

  /**
   * 根据输入的销售组织+客户+物料集合得到允许销售的客户+物料集合
   * 
   * @param paravos CustMatRelParaForOPCVO[]
   * @return 根据客户物料关系定义过滤出满足条件的客户+物料集合
   * @throws BusinessException
   */
  CustMatRelParaForOPCVO[] filterData(CustMatRelParaForOPCVO[] paravos)
      throws BusinessException;

}
