package nc.pubitf.so.m30.ia.pub;

import java.util.Map;

import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;

/**
 * 销售订单提供给存货核算接口
 * 
 * @since 6.0
 * @version 2010-11-6 下午03:56:31
 * @author 刘志伟
 */
public interface ISaleOrderForIA {

  /**
   * 获得直运类型标记是否直运采购
   * 
   * @param ctrantypeids 交易类型IDs
   * @return Map<String, UFBoolean> Map<ctrantypeid, 是否直运采购>
   * @throws BusinessException
   */
  Map<String, UFBoolean> queryIsDirectPO(String[] ctrantypeids)
      throws BusinessException;
}
