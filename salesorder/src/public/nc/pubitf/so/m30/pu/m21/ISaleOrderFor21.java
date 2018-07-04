package nc.pubitf.so.m30.pu.m21;

import java.util.Map;

import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;

/**
 * 销售订单提供给采购订单服务接口
 * 
 * @since 6.0
 * @version 2011-5-11 下午02:59:33
 * @author 刘志伟
 */
public interface ISaleOrderFor21 {
	
  /**
	 * 根据销售订单id查询是否采购订单协同生成的
	 * 
	 * @param ids 销售订单id[]
	 * @return Map<String, Boolean> Map<销售订单id, 是否采购订单协同生成>
	 * @throws BusinessException
	 */	
  Map<String,UFBoolean> query30IsFromCoop(String[] ids)throws BusinessException;

  /**
   * 根据销售订单附表bid查询来源bid
   * 
   * @param ids 销售订单附表id[]
   * @return Map<String, String> Map<销售订单bid, 采购订单bid>
   * @throws BusinessException
   */
  Map<String, String> queryCoop21Bids(String[] bids) throws BusinessException;

  /**
   * 采购订单协同生成销售订单
   * 
   * @param ids 销售订单附表id[]
   * @return Map<String, String> Map<销售订单bid, 采购订单bid>
   * @throws BusinessException
   */
  void push21To30(OrderVO[] srcBills) throws BusinessException;

  /**
   * 查询已审核和关闭状态的销售订单上的物料数量
   * 
   * @param cmaterialid 物料ID
   * @param queryDate 查询开始日期
   * @param queryDay 时间短长度
   * @param pk_group 集团
   * @param pk_org 销售组织
   * @return 物料ID为KEY，数量为Value的Map
   */
  Map<String, UFDouble> getSaleOrderNumber(String[] cmaterialid,
      UFDate queryDate, Integer queryDay, String pk_group, String pk_org);

  /**
   * 交易类型是否直运采购
   * 
   * @param ctrantypeids 交易类型ID
   * @return Map<String, UFBoolean> Map<ctrantypeid, 是否直运采购>
   * @throws BusinessException
   */
  Map<String, UFBoolean> queryIsDirectPO(String[] ctrantypeids)
      throws BusinessException;
}
