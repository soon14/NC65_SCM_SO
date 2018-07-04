package nc.itf.so.m4310trantype;

import nc.vo.pub.BusinessException;
import nc.vo.so.m4310trantype.entity.M4310TranTypeVO;

public interface IM4310TranTypeService {

  /**
   * 查找单据类型下的所有交易类型
   * 
   * @param pkGroup
   *          集团
   * @param pkBilltype
   *          单据类型
   * @return
   * @throws BusinessException
   */
  M4310TranTypeVO[] queryAllTranType(String pkGroup, String pkBilltype)
      throws BusinessException;

  /**
   * 查找特定的交易类型
   * 
   * @param pk_group
   *          集团
   * @param pk_billtypecode
   *          交易类型
   * @return
   * @throws BusinessException
   */
  M4310TranTypeVO queryTranType(String pk_group, String pk_billtypecode)
      throws BusinessException;

  /**
   * 批量查找特定的交易类型
   * 
   * @param pk_group
   *          集团
   * @param pk_billtypecode
   *          交易类型数组
   * @return
   * @throws BusinessException
   */
  M4310TranTypeVO[] queryTranType(String pk_group, String[] pk_billtypecode)
      throws BusinessException;
}
