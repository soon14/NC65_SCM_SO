package nc.pubitf.so.m30.api;

import nc.vo.pub.BusinessException;
import nc.vo.so.m30.entity.SaleOrderExternalVO;
import nc.vo.so.m30.entity.SaleOrderVO;

/**
 * 销售订单导入公共接口<br>
 * 
 * <b>主要功能：</b>
 * <ol>
 * 
 * <li>导入SaleOrderExternalVO</li>
 * <li>新增SaleOrderVO</li>
 * <li>删除SaleOrderVO</li>
 * </ol>
 * 
 * @since 6.3
 * @version 2013-06-06 15:40:45
 * @author 董礼
 */
public interface ISaleOrderExternal {

  /**
   * <b>导入SaleOrderExternalVO </b><br>
   * 
   * <b>主要功能：</b>
   * <ol>
   * 
   * <li>VO数据校验</li>
   * <ol>
   * <li>
   * 表头必输项：销售组织PK_ORG、订单类型CTRANTYPEID、单据日期DBILLDATE、客户CCUSTOMERID、部门CDEPTVID、
   * 原币CORIGCURRENCYID</li>
   * <li>表体必输项：物料编码CMATERIALVID、发货库存组织CSENDSTOCKORGVID、结算财务组织CSETTLEORGVID</li>
   * <li>数量单位必输：单位CASTUNITID、数量NASTNUM、价税合计NORIGTAXMNY</li>
   * </ol>
   * <li>SaleOrderExternalVO转SaleOrderVO</li> <li>数据补全：根据已输内容补全其他ID以及单据冗余</li>
   * </ol>
   * 
   * @param SaleOrderExternalVO[] vos
   *          该VO结构与销售订单VO一致，导入数据先传入SaleOrderExternalVO再做后续处理为销售订单的VO格式
   * 
   * @since 6.3
   * @version 2013-06-06 15:40:45
   * @author 董礼
   */
  SaleOrderVO[] insertInterfaceVOForSaleOrder(SaleOrderExternalVO[] vos)
      throws BusinessException;

  /**
   * <b>导入SaleOrderVO(补全数据) </b><br>
   * 
   * <b>主要功能：</b>
   * <ol>
   * 
   * <li>VO数据校验</li>
   * <ol>
   * <li>
   * 表头必输项：销售组织PK_ORG、订单类型CTRANTYPEID、单据日期DBILLDATE、客户CCUSTOMERID、部门CDEPTVID、
   * 原币CORIGCURRENCYID</li>
   * <li>表体必输项：物料编码CMATERIALVID、发货库存组织CSENDSTOCKORGVID、结算财务组织CSETTLEORGVID</li>
   * <li>数量单位必输：单位CASTUNITID、数量NASTNUM、价税合计NORIGTAXMNY</li>
   * </ol>
   * <li>数据补全：根据已输内容补全其他ID以及单据冗余</li>
   * </ol>
   * 
   * @param SaleOrderVO[] vos
   *          销售订单VO
   * 
   * @since 6.3
   * @version 2013-08-28 15:40:45
   * @author 董礼
   */
  SaleOrderVO[] insertSaleOrderVOFor30(SaleOrderVO[] vos)
      throws BusinessException;

  /**
   * 导入销售订单VO(不补全数据)<br>
   * 
   * <b>主要功能：</b>
   * <ol>
   * <li>导入标准SaleOrderVO</li>
   * <li>后续处理与销售订单保存后台逻辑一致</li>
   * </ol>
   * 
   * @param SaleOrderVOS(必须为完整VO)
   * @return
   * @throws BusinessException
   */
  SaleOrderVO[] insertSaleOrderVOForSaleOrder(SaleOrderVO[] SaleOrderVOS)
      throws BusinessException;

  /**
   * 删除SaleOrderVO<br>
   * 
   * <b>主要功能：</b>
   * <ol>
   * <li>删除SaleOrderVO</li>
   * <li>参数：销售订单主实体csaleorderid</li>
   * <ol>
   * <li>csaleorderid</li>
   * </ol>
   * </ol>
   * 
   * @param pks
   * @return
   * @throws BusinessException
   */
  void deleteSVOForSaleOrder(String[] pks) throws BusinessException;

}
