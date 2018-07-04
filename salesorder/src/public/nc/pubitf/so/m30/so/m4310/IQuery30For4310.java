package nc.pubitf.so.m30.so.m4310;

import java.util.Map;

import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;

/**
 * 为报价单提供查询销售订单服务
 * 
 * @since 6.1
 * @version 2013-06-04 11:05:35
 * @author yixl
 */
public interface IQuery30For4310 {

  /**
   * 查询报价下游是否生成销售订单
   * 
   * @param quotationhids
   * @return Map<String, UFBoolean> key:报价单Hid value：是否有下游单据
   * @throws BusinessException
   */
  Map<String, UFBoolean> isExistNextOrder(String[] quotationhids)
      throws BusinessException;
}
