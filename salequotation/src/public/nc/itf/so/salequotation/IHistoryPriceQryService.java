package nc.itf.so.salequotation;

import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.so.m4310trantype.entity.M4310TranTypeVO;
import nc.vo.so.salequotation.entity.FindHistoryPriceParameter;

/**
 * 询历史报价服务
 * 
 * @author chenyyb
 * 
 */
public interface IHistoryPriceQryService {

  /**
   * 询历史报价
   * 
   * @param paraVOs
   *          询价参数
   * @param tranTypeVO
   *          交易类型
   * @return
   * @throws BusinessException
   */
  UFDouble[] findHistoryPrice(FindHistoryPriceParameter[] paraVOs,
      M4310TranTypeVO tranTypeVO) throws BusinessException;
}
