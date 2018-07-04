package nc.pubitf.so.m30.pu.m20;

import java.util.Map;

import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;

/**
 * 销售订单提供给请购单服务接口
 * 
 * @since 6.0
 * @version 2011-5-11 下午02:59:33
 * @author 刘志伟
 */
public interface ISaleOrderFor20 {

  /**
   * 查询销售订单交易类型的直运类型是否直运采购类型
   * 
   * @param pk_group 集团
   * @param vtrantypecodes 销售订单交易类型[]
   * @return Map<String, UFBoolean> Map<销售订单交易类型,是否直运采购>
   */
  Map<String, UFBoolean> queryIsDirectPOType(String pk_group,
      String[] vtrantypecodes) throws BusinessException;
}
