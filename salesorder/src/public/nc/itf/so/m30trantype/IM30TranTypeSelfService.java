package nc.itf.so.m30trantype;

import nc.vo.pub.BusinessException;
import nc.vo.so.m30trantype.entity.M30TranTypeVO;

/**
 * 用于自己的界面查询编辑
 * 
 * @since 6.0
 * @version 2012-3-15 下午03:44:18
 * @author 么贵敬
 */
public interface IM30TranTypeSelfService {

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
   * 根据交易类型ID查询交易类型VO
   * 
   * @param ctrantypeid
   * @return
   * @throws BusinessException
   */
  M30TranTypeVO queryTranTypeVO(String ctrantypeid) throws BusinessException;

}
