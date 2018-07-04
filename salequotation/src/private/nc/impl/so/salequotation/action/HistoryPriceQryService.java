package nc.impl.so.salequotation.action;

import nc.bs.so.salequotation.bp.HistoryPriceQryBP;
import nc.itf.so.salequotation.IHistoryPriceQryService;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m4310trantype.entity.HisSalequoPriceSource;
import nc.vo.so.m4310trantype.entity.M4310TranTypeVO;
import nc.vo.so.salequotation.entity.FindHistoryPriceParameter;

public class HistoryPriceQryService implements IHistoryPriceQryService {

  @Override
  public UFDouble[] findHistoryPrice(FindHistoryPriceParameter[] paraVOs,
      M4310TranTypeVO tranTypeVO) throws BusinessException {
    UFDouble[] price = null;
    try {
      HistoryPriceQryBP historyPriceQryBP = new HistoryPriceQryBP(tranTypeVO);
      int fhistoryflag = tranTypeVO.getFhistoryflag().intValue();
      if (fhistoryflag == ((Integer) HisSalequoPriceSource.AVERAGE_PRICE
          .value()).intValue()) {
        price = historyPriceQryBP.findAvgPrice(paraVOs);
      }
      else {
        price = historyPriceQryBP.findLastPrice(paraVOs);
      }
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return price;
  }

}
