package nc.pubitf.so.m30.crm;

import java.util.Map;

import nc.pubitf.so.m4310.crm.CRMQueryPara;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.query2.sql.process.QueryCondition;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;

/**
 * 为CRM提供销售订单查询接口
 * 
 * @since 6.3.1
 * @version 2013-08-06 20:03:48
 * @author 张云枫
 */
public interface ISaleOrderQueryForCRM {

  /**
   * 根据CRM参数对象查询销售订单
   * 
   * @param queryPara CRM参数对象
   * 
   * @return SaleOrderHVO[]销售订单表头VO
   * @throws BusinessException
   */
  public SaleOrderHVO[] querySaleOrder(CRMQueryPara queryPara)
      throws BusinessException;

  /**
   * 根据主键查询销售订单
   * 
   * @param id 销售订单主键ID
   * 
   * @return 销售订单表体VO
   * @throws BusinessException
   */
  public SaleOrderBVO[] querySaleOrderById(String id) throws BusinessException;

  /**
   * 产出查询</br>
   * 
   * 基于【买赠活动】或【价格促销活动】查询销售订单只有一个匹配即可，只返回审批通过的订单行。
   * 
   * @param cmarketactid 营销活动id
   * @param fieldnames 要查询的字段
   * @return 销售订单表体VO
   * @throws BusinessException
   */
  SaleOrderBVO[] querySaleOrderBodyVOsByActID(String cmarketactid,
      String[] fieldnames) throws BusinessException;

  /**
   * 
   * 买赠费用接口</br>
   * 
   * 基于【买赠活动】查询销售订单(仅仅返回赠品行)，只返回审批通过的订单行。
   * 
   * @param cmarketactid 营销活动id
   * @param fieldnames 要查询的字段
   * @return 销售订单表体VO
   * @throws BusinessException
   */
  SaleOrderBVO[] querySaleOrderBodyVOsByLargessActID(String cmarketactid,
      String[] fieldnames) throws BusinessException;

  /**
   * 价格促销费用接口</br>
   * 
   * 基于【价格促销活动】查询销售订单。只返回审批通过的订单行
   * 
   * @param cmarketactid 营销活动id
   * @param fieldnames 要查询的字段
   * @return 销售订单表体VO
   * @throws BusinessException
   */
  SaleOrderBVO[] querySaleOrderBodyVOsPriceActID(String cmarketactid,
      String[] fieldnames) throws BusinessException;

  /**
   * 根据活动查询订单主表PK</br>
   * 
   * 根据活动id匹配销售订单买赠活动和促销价格活动，只要有一个匹配到则返回订单表头pk,不区别单据状态。
   * 
   * @param cmarketactid 营销活动id
   * @param orderbyFileds 排序字段，依次排序
   * @return 订单表头PK数组
   * @throws BusinessException
   */
  String[] querySaleOrderIDsByActID(String cmarketactid, String[] orderbyFileds)
      throws BusinessException;

  /**
   * 根据销售订单表头PK查询订单表头VO(批量)
   * 
   * @param pk_saleorders 销售订单主表PK数组
   * @return 销售订单表头VO
   * @throws BusinessException
   */
  SaleOrderHVO[] querySaleOrderHeadVOsByOrderIDs(String[] pk_saleorders)
      throws BusinessException;

  /**
   * 活动和订单PK查询订单主子表</br>
   * 
   * 返回订单主表及与活动匹配的表体行，订单的买赠活动或价格促销活动，</br>
   * 只有一个匹配即返回。其他匹配不上的表体行不返回。
   * 
   * @param pk_saleorder 销售订单主表PK
   * @param cmarketactid 营销活动id
   * @return 销售订单聚合VO
   * @throws BusinessException
   */
  SaleOrderVO querySaleOrderVOByActIDAndOrderID(String pk_saleorder,
      String cmarketactid) throws BusinessException;
  
	/**
	 * 根据查询方案和元数据路径Map查询销售订单子表VO，只返回指定字段数据
	 * 
	 * @param columnMapping
	 *            元数据路径Map，如：columnMapping.put("brandid",
	 *            "cinventoryvid.pk_brand.pk_brand");
	 * @param queryScheme
	 *            查询方案，主要是queryCondition
	 * @param fieldnames
	 *            需要查询的字段，使用者拼装
	 * @return SaleOrderBVO
	 * @throws BusinessException
	 */
	public SaleOrderBVO[] querySaleOrderBVOsByQueryScheme(
			Map<String, String> columnMapping,
			QueryCondition[] queryConditions, String[] fieldnames)
			throws BusinessException;

}
