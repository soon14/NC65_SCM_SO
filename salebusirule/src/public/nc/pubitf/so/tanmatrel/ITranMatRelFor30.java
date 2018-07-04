package nc.pubitf.so.tanmatrel;

import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;

/**
 * 订单类型与物料关系业务函数检查
 * 
 * @since 6.0
 * @version 2011-4-19 下午08:32:20
 * @author 祝会征
 */
public interface ITranMatRelFor30 {
  /**
   * 订单类型与物料关系业务函数检查
   * 
   * @param paravos
   * @return
   * @throws BusinessException
   */
  UFBoolean checkTranMatRel(TranMatRelParaVO[] paravos)
      throws BusinessException;
}
