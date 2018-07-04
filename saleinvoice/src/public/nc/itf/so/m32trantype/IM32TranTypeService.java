package nc.itf.so.m32trantype;

import nc.vo.pub.BusinessException;
import nc.vo.so.m32trantype.entity.M32TranTypeVO;

/**
 * 销售发票交易类型对外提供服务接口
 * 
 * @since 6.3
 * @version 2012-12-21 上午10:10:50
 * @author yaogj
 */
public interface IM32TranTypeService {

  /**
   * 查询销售发票交易类型vo数据
   * 
   * @param pk_group 集团
   * @param vtrantype 交易类型
   * @return 发票交易类型vo
   * @throws BusinessException
   */
  M32TranTypeVO queryTranType(String pk_group, String vtrantype)
      throws BusinessException;

}
