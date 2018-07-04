package nc.pubitf.so.m30.pu.m21;

import java.util.Map;

import nc.vo.pub.BusinessException;

/**
 * 采购订单协同销售订单回写接口
 * 
 * @since 6.0
 * @version 2011-3-18 下午04:51:24
 * @author 祝会征
 */
public interface IRewrite30For21 {
  /**
   * 回写采购订单单据号
   * 
   * @param codeMap
   * @throws BusinessException
   */
  void rewriteBillCode(Map<String, String> codeMap) throws BusinessException;

  /**
   * 协同销售生成的采购订单，删除时更新销售订单
   * 
   * @param ids
   * @throws BusinessException
   */
  void renovate30For21Delete(String[] ids) throws BusinessException;
}
