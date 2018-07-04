package nc.pubitf.so.custmatrel;

import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;

/**
 * 销售订单客户物料关系检查
 * 
 * @since 6.0
 * @version 2011-4-19 下午08:09:21
 * @author 祝会征
 */
public interface ICustMatRelFor30 {
  /**
   * 检查是否满足销售订单客户物料关系
   * 
   * @param paravos
   * @return
   * @throws BusinessException
   */
  void checkCustMatRel(CustMatRelParaVO[] paravos) throws BusinessException;

  /**
   * 对每个传入的参数检查是否满足销售订单客户物料关系定义
   * 
   * @param paravos
   * @return
   * @throws BusinessException
   */
  UFBoolean[] getCustMatRelSaleFlag(CustMatRelParaVO[] paravos)
      throws BusinessException;
}
