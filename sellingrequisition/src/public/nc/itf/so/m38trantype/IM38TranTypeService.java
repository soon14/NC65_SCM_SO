package nc.itf.so.m38trantype;

import nc.vo.pub.BusinessException;
import nc.vo.so.m38trantype.entity.M38TranTypeVO;

/**
 * <b> 预订单交易类型服务接口 </b>
 * 
 * 
 * @since 6.0
 * @version 2010-11-3 上午10:48:33
 * @author 刘志伟
 */
public interface IM38TranTypeService {

  M38TranTypeVO queryTranTypeVO(String ctrantypeid) throws BusinessException;

  M38TranTypeVO[] queryTranTypeVOs(String[] ctrantypeids)
      throws BusinessException;
}
