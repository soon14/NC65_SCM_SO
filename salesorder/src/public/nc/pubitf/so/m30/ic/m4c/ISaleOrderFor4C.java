package nc.pubitf.so.m30.ic.m4c;

import java.util.Map;

import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.entity.SaleOrderViewVO;

import nc.ui.querytemplate.querytree.IQueryScheme;

/**
 * 销售订单提供销售出库单接口
 * 
 * @since 6.0
 * @version 2011-3-23 下午05:55:42
 * @author 刘志伟
 */
public interface ISaleOrderFor4C {

  /**
   * 根据hids关闭相应销售订单
   * 
   * @param hids 销售订单主表ID
   * @throws BusinessException
   */
  void close30For4C(String[] hids) throws BusinessException;

  /**
   * 根据销售订单附表id获得其参照的上游采购订单的附表 id 和表头id
   * 
   * @param bids 销售订单表体ID
   * @return Map<String, String[]> Map<销售订单主表ID, String[]{采购订单主表ID,采购订单附表ID}>
   * @throws BusinessException
   */
  Map<String, String[]> queryCoopPOBid(String[] bids) throws BusinessException;

  /**
   * 根据4C附表名称和4C附表源头表体id名称构造过滤掉30已发票关闭关联SQL片段
   * 
   * @param icbodytable 出库单附表ID
   * @param cfirstbid 源头附表ID
   * @return IQueryScheme sql片段
   * @throws BusinessException
   */
  IQueryScheme getInvoicEndSQL4Filter4C(String icbodytable, String cfirstbid)
      throws BusinessException;

  /**
   * 根据bids查询销售订单VOs
   * <p>
   * bids ->ids -> VOs(即VOs中包含数量bids > 参数bids)
   * </p>
   * 
   * @param bids 订单表体id[]
   * @return SaleOrderVO[] 销售订单VO[]
   * @throws BusinessException
   */
  SaleOrderVO[] querySaleOrderVOs(String[] bids) throws BusinessException;

  /**
   * 根据bids查询销售订单ViewVOs指定值
   * 
   * @param bids 订单表体id[]
   * @param names 需要查询的值
   * @return SaleOrderViewVO[] 销售订单ViewVO[]
   * @throws BusinessException
   */
  SaleOrderViewVO[] querySaleOrderViewVOs(String[] bids, String[] names)
      throws BusinessException;

  /**
   * 交易类型是否直运采购
   * 
   * @param ctrantypeids 交易类型ID
   * @return Map<String, UFBoolean> Map<ctrantypeid, 是否直运采购>
   * @throws BusinessException
   */
  Map<String, UFBoolean> queryIsDirectPO(String[] ctrantypeids)
      throws BusinessException;

  /**
   * 交易类型是否直运(即是直运采购也是直运调拨)
   * 
   * @param ctrantypeids 交易类型ID
   * @return Map<String, UFBoolean> Map<ctrantypeid, 是否直运>
   * @throws BusinessException
   */
  Map<String, UFBoolean> queryIsDirect(String[] ctrantypeids)
      throws BusinessException;

  /**
   * 交易类型是否途损补货
   * 
   * @param ctrantypeids 交易类型ID
   * @return Map<String, UFBoolean> Map<ctrantypeid, 是否途损补货>
   * @throws BusinessException
   */
  Map<String, UFBoolean> queryIsWastageAppend(String[] ctrantypeids)
      throws BusinessException;

  /**
   * 根据销售订单表体id 查询下游发货单是否做过预留标记
   * 
   * @param bids
   * @return 是否做过预留标志
   * @throws BusinessException
   */
  Map<String, UFBoolean> getReserveInfo(String[] bids) throws BusinessException;

  /**
   * 根据下游出库单表头ID和表体ID查询借出转销售的订单信息
   * 
   * @param srchids
   * @param srcbids
   * @return 销售订单视图VO
   * @throws BusinessException
   */
  SaleOrderViewVO[] queryJCSaleOrderViewVOs(String[] srchids, String[] srcbids)
      throws BusinessException;
}
