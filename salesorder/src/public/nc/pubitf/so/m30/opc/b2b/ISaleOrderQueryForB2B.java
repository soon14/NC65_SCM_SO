package nc.pubitf.so.m30.opc.b2b;

import java.util.List;
import java.util.Map;

import nc.vo.pub.BusinessException;

/**
 * 销售订单为B2B预订单提供查询接口
 * 
 * @since 6.5
 * @version 2014-04-02 14:44:54
 * @author zhangyfr
 */
public interface ISaleOrderQueryForB2B {

  /**
   * 根据销售订单来源行id，查询出B2B指定的结果集（用于B2B预订单回写促销价格表）
   * 
   * @param ids 销售订单来源行ID数组
   * @return map<来源ID，来源ID对应的list>
   * @throws BusinessException
   */
  public Map<String, List<SaleOrderForB2bResult>> query(String[] ids)
      throws BusinessException;

}
