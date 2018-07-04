package nc.pubitf.so.ic.m4c;

import java.util.List;
import java.util.Map;

import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.so.paravo.Para4CFor32SignBiz;

public interface ISaleFor4CParaQuery {

  /**
   * uap接口暂时放这
   * 用途： 根据销售组织得到允许发货的库存组织ID[]； 逻辑：
   * 1、根据销售组织匹配销售业务委托关系，匹配上的库存组织允许发货；
   * 2、销售组织又具有库存组织属性，则销售组织作为发货库存组织允许发货； 说明：返回的ID不应该有重复
   * 
   * @param saleorgID
   * @param materialID
   * @return
   * @throws BusinessException
   */
  Map<String, List<String>> getStockOrgIDSBySaleOrgID(String[] orgids)
      throws BusinessException;

  /**
   * 获取财务组织 是否赠品开票
   * 
   * @param cfinaceorgids 财务组织
   * @return
   * @throws BusinessException
   */
  Map<String, UFBoolean> getSO20(String[] cfinaceorgids)
      throws BusinessException;

  /**
   * 基于签收数量开票的业务流程
   * 
   * @param pk_orgs
   * @return
   * @throws BusinessException
   */
  Para4CFor32SignBiz[] querySignNumBusitype(Para4CFor32SignBiz[] paras)
      throws BusinessException;

}
