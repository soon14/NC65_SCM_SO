package nc.itf.so.m30trantype;

import java.util.Map;

import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.so.m30trantype.entity.M30TranTypeVO;

/**
 * <b> 销售订单交易类型服务接口 </b>
 * 创建日期:2010-01-25 18:42:55
 * 
 * @author 刘志伟
 * @version 6.0
 */
public interface IM30TranTypeService {

  /**
   * 交易类型直运类型标记
   * 
   * @param ctrantypeids 交易类型ID
   * @return Map<String, Integer> Map<ctrantypeid, 直运类型标记>
   * @throws BusinessException
   */
  Map<String, Integer> queryDirectType(String[] ctrantypeids)
      throws BusinessException;

  /**
   * 交易类型直运类型标记是否直运采购
   * 
   * @param ctrantypeids 交易类型ID
   * @return Map<String, UFBoolean> Map<ctrantypeid, 是否直运采购>
   * @throws BusinessException
   */
  Map<String, UFBoolean> queryIsDirectPO(String[] ctrantypeids)
      throws BusinessException;

  /**
   * 交易类型直运类型标记是否直运调拨
   * 
   * @param ctrantypeids 交易类型ID
   * @return Map<String, UFBoolean> Map<ctrantypeid, 是否直运调拨>
   * @throws BusinessException
   */
  Map<String, UFBoolean> queryIsDirectTO(String[] ctrantypeids)
      throws BusinessException;

  /**
   * 查询当前集团下直运类型的所有交易类型
   * 
   * @author 刘志伟
   * @time 2010-08-03 下午01:32:25
   */
  String[] queryDirectTypeAllBillTypeCode(String pk_group)
      throws BusinessException;

  /**
   * 根据交易类型编码查询交易类型VO
   * 
   * @author 刘志伟
   * @time 2010-08-03 下午01:32:25
   */
  M30TranTypeVO queryTranType(String pk_group, String pk_billtypecode)
      throws BusinessException;

  /**
   * 根据交易类型编码[]查询交易类型VOs
   * 
   * @param pk_group 集团
   * @param pk_billtypecodes 交易类型[]
   * @return M30TranTypeVO[]
   */
  M30TranTypeVO[] queryTranTypeVOs(String pk_group, String[] pk_billtypecodes)
      throws BusinessException;

  /**
   * 根据交易类型ID查询交易类型VO
   * 
   * @param pk_group 集团
   * @param pk_billtypecodes 交易类型[]
   * @return M30TranTypeVO[]
   */
  M30TranTypeVO queryTranTypeVO(String trantypeid) throws BusinessException;

  /**
   * 根据交易类型IDs查询交易类型VOs
   * 
   * @param pk_group 集团
   * @param pk_billtypecodes 交易类型[]
   * @return M30TranTypeVO[]
   */
  M30TranTypeVO[] queryTranTypeVOs(String[] ctrantypeids)
      throws BusinessException;

  /**
   * 根据交易类型IDs
   * 
   * @param ctrantypeids 交易类型ID
   * @return Map<String, Integer> Map<ctrantypeid, 询价规则>
   * @throws BusinessException
   */
  Map<String, Integer> queryAskPriceRule(String[] ctrantypeids)
      throws BusinessException;
}
