package nc.pubitf.so.m30.ct.mz3;

import java.util.Map;

import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;

/**
 * 销售订单提供给销售合同接口服务
 * 
 * @since 6.0
 * @version 2011-1-21 上午10:09:36
 * @author 刘志伟
 */
public interface ISaleOrderForZ3 {

  /**
   * 根据合同hids查询相关联销售订单下游应收单的原币余额
   * 
   * @param cthids 合同hids
   * @return Map<合同hid,原币余额合计>
   * @throws BusinessException
   */
  Map<String, UFDouble> queryOrigCurrencyBalance(String[] cthids)
      throws BusinessException;

  /**
   * 查询合同下游是否生成销售订单
   * 
   * @param cthids 合同hid
   * @return Map<String, UFBoolean> key:合同Hid value：是否有下游单据,有true;无false
   * @throws BusinessException
   */
  Map<String, UFBoolean> isExistNextOrder(String[] cthids)
      throws BusinessException;
}
