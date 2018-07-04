package nc.pubitf.so.m30.pu.m21;

import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pub.BusinessException;

/**
 * 此类禁止使用 使用（nc.pubitf.so.m30.pu.m21.ISaleOrderFor21.push21To30）方法
 * 
 * @since 6.3
 * @version 2013-1-25 下午04:27:42
 * @author 祝会征
 */
@Deprecated
public interface IPush21To30 {

  /**
   * 
   * @param srcBills
   * @throws BusinessException
   */
  void push21To30(OrderVO[] srcBills) throws BusinessException;
}
