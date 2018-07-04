package nc.itf.so.m33;

import java.util.Map;

import nc.vo.pub.BusinessException;

/**
 * 结算单来源信息获取类
 * 
 * @since 6.1
 * @version 2013-05-09 10:55:55
 * @author yixl
 */
public interface IM33MainTain {

  /**
   * 获取结算单来源信息
   * 
   * @param bids
   * @return Map<String, String>
   * @throws BusinessException
   */
  Map<String, String> getSrcInfo(String[] bids) throws BusinessException;

}
