package nc.itf.so.mbuylagress;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.BusinessException;
import nc.vo.so.mbuylargess.entity.BuyLargessVO;
import nc.vo.uif2.LoginContext;

public interface IBuyLargessMaintain {

  /**
   * 删除VO
   * 
   * @param todelVO
   * @throws BusinessException
   */
  void deleteBuylargess(BuyLargessVO todelVO) throws BusinessException;

  /**
   * 新增VO
   * 
   * @param newVO
   * @return
   * @throws BusinessException
   */
  BuyLargessVO insertBuylargess(BuyLargessVO newVO) throws BusinessException;

  /**
   * 根据where条件查询买赠数据
   * 
   * @param where
   * @return
   * @throws Exception
   */
  BuyLargessVO[] queryBuylargess(IQueryScheme queryScheme)
      throws BusinessException;

  /**
   * 根据物料分类查询对应的物料主计量单位是否唯一，若唯一返回该计量单位，否则返回null
   * 
   * @param materialclass
   * @param SO_79
   * @return
   * @throws BusinessException
   */
  String queryMaterialClassMeas(String materialclass) throws BusinessException;

  Object[] queryTariffDef(LoginContext context) throws BusinessException;

  /**
   * 修改VO
   * 
   * @param updateVO
   * @return
   * @throws BusinessException
   */
  BuyLargessVO updateBuylargess(BuyLargessVO updateVO) throws BusinessException;

}
