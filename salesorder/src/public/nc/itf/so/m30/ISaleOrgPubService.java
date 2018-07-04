package nc.itf.so.m30;

import java.util.List;
import java.util.Map;

import nc.vo.org.OrgVO;
import nc.vo.pub.BusinessException;

/**
 * 销售组织 查询服务接口
 */
public interface ISaleOrgPubService {

  /**
   * 
   * 方法功能描述：简要描述本方法的功能。
   * <p>
   * <b>examples:</b>
   * <p>
   * 使用示例
   * <p>
   * <b>参数说明</b>
   * 
   * @param count
   * @return
   * @throws BusinessException
   *           <p>
   * @author gdsjw
   * @time 2010-6-18 下午12:17:45
   */
  String[] getOIDArray(int count) throws BusinessException;

  /**
   * 根据客户ID、销售组织ID、物料ID,得到默认结算财务组织VO、应收组织VO、利润中心VO，VO包含信息：ID、组织编码、组织名称、组织版本 、集团；
   * 逻辑：
   * 1、按照销售组织从客户档案中取得结算财务组织、应收组织、利润中心；
   * 2、客户档案中未指定固定结算财务组织，则按照销售组织、物料所属产品线、库存组织匹配销售业务委托关系，
   * 取得结算财务组织、应收组织、利润中心；
   * 3、如果第2步未找到符合条件的委托关系，按“销售组织”为指定组织ID而产品线为空的业务委托关系匹配，
   * 取得结算财务组织、应收组织、利润中心；
   * 4、如果经过上述步骤后，财务组织或应收组织仍为空，同时销售组织又是财务组织，相应为空的财务组织取销售组织ID；
   * 如果利润中心为空，而应收组织是利润中心，则利润中心取应收组织；
   */
  Map<String, List<OrgVO>> queryDefaultOtherOrgIDsFromSaleOrgRel(
      String customerID, String saleorgID, String[] materialids)
      throws BusinessException;

  /**
   * 根据销售组织ID、物料ID,得到默认发货库存组织VO，VO包含信息：ID、组织编码、组织名称、组织版本 、集团
   * 逻辑：
   * 1、根据“销售组织+物料所属产品线”唯一确定一个默认发货库存组织；
   * 2、如果“物料所属产品线”未指定或根据第1条没有找到库存组织，
   * 则根据“销售组织”为指定组织ID而产品线为空的业务委托关系确定默认发货库存组织；
   * 3、如果还没找到默认发货库存组织，同时销售组织又具有库存组织属性，则取销售组织为默认发货库存组织；
   */
  Map<String, OrgVO> queryDefaultStockOrgIDFromSaleOrgRel(String saleorgID,
      String[] materialids) throws BusinessException;

  /**
   * 根据销售组织ID、物料ID,得到允许发货的库存组织VO[]，VO包含信息：ID、组织编码、组织名称、组织版本 、集团
   * 逻辑：
   * 1、根据“销售组织+物料所属产品线”匹配销售业务委托关系，匹配上的库存组织允许发货；
   * 2、匹配“销售组织”为指定组织ID而产品线为空的业务委托关系，匹配上的库存组织允许发货；
   * 3、销售组织又具有库存组织属性，则销售组织作为发货库存组织允许发货；
   * 说明：返回的ID不应该有重复
   */
  Map<String, OrgVO[]> queryStockOrgIDsFromSaleOrgRel(String saleorgID,
      String[] materialids) throws BusinessException;

}
