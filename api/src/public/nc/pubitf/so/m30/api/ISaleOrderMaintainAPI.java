package nc.pubitf.so.m30.api;

import nc.itf.annotation.Component;

import nc.itf.annotation.OpenAPI;
import nc.itf.annotation.OpenLevel;
import nc.vo.pub.BusinessException;
import nc.vo.so.m30.entity.SaleOrderVO;

/**
 * @description
 * <ul>
 * <li>新增保存销售订单
 * <li>根据销售订单主键删除销售订单
 * <li>根据销售订单来源单据ID删除单据
 * </ul>
 * @scene
 * 
 * @param
 * 
 * @functionName 销售订单持久化服务
 *
 * @since 6.5
 * @version 2015-10-20 下午1:49:10
 * @author 刘景
 */
@Component("销售订单")
@OpenAPI(value = OpenLevel.OPENED)
public interface ISaleOrderMaintainAPI {

  /**   
   * <B>新增保存销售订单</B>   
   * <li>服务会对传入的参数进行数据校验和默认值填充</li>      
   *  
   * <li>数据为空校验</li>   
   * 销售订单基本必输字段有：</br> 
   * 表头：销售组织（pk_org），部门 （cdeptid），交易类型（ctrantypeid），客户（ccustomerid），币种（corigcurrencyid）</br> 
   * 表体：物料（cmaterialvid）；主数量（nnum）附数量（nastnum）不能同时为空；含税单价（nqtorigtaxprice）、主含税单价（norigtaxprice） 、价税合计（norigtaxmny）不能同时为空；</br> 
   *    
   * <li>默认值填充（有值不覆盖）</li>   
   * 表头：单据状态、整单折扣、人员、交易类型编码、业务流程、收款协议、开票客户、单据日期</br>
   * 表体：主单位、单位、换算率、发货库存组织、物流组织、财务组织、应收组织、利润中心、本位币、折本汇率、收货客户、国家、购销类型、税率税码、仓库、集团、全局汇率、计划发货日期、要求到货日期、 整单折扣、单品折扣、行状态、表头合计字段。
   * 
   * <li>服务会重新计算数量、单价、金额</li>
   * 1.如果有主数量，优先用住数量触发单价金额算法，否则副数量。</br> 
   * 2.如果有价税合计，优先用价税合计触发单价金额算法，否则用单价触发单价金额算法。</br> 
   * 3.如果有含税单价，优先用含税单价触发单价金额算法，否则用主含税单价触发单价金额算法。</br> 
   *   
   *    
   * @param vos 销售订单VO数组   
   * @return 保存后的销售订单VO数组   
   * @throws BusinessException 异常   
   */
  SaleOrderVO[] insertBills(SaleOrderVO[] vos) throws BusinessException;

  /**   
   * <B>根据销售订单主键删除销售订单</B>    
   *    
   * @param ids 销售订单主键数组
   * @return 保存后的销售订单VO数组   
   * @throws BusinessException 异常   
   */
  void deleteBillsByID(String[] ids) throws BusinessException;

}
