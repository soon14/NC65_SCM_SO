package nc.ui.so.salequotation.model;

import nc.bs.framework.common.NCLocator;
import nc.itf.price.pub.service.IFindSalePrice;
import nc.itf.so.salequotation.IHistoryPriceQryService;
import nc.vo.price.pub.entity.FindPriceParaVO;
import nc.vo.price.pub.entity.FindPriceResultVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.so.m4310trantype.entity.M4310TranTypeVO;
import nc.vo.so.salequotation.entity.FindHistoryPriceParameter;

public class FindPriceService {
  private static IFindSalePrice findSalePrice;

  private static IHistoryPriceQryService historyPriceQryService;

  private static IFindSalePrice getFindSalePrice() {
    if (FindPriceService.findSalePrice == null) {
      FindPriceService.findSalePrice =
          NCLocator.getInstance().lookup(IFindSalePrice.class);
    }
    return FindPriceService.findSalePrice;
  }

  private static IHistoryPriceQryService getHistoryPriceQryService() {
    if (FindPriceService.historyPriceQryService == null) {
      FindPriceService.historyPriceQryService =
          NCLocator.getInstance().lookup(IHistoryPriceQryService.class);
    }
    return FindPriceService.historyPriceQryService;
  }

  public UFDouble[] findHistoryPrice(FindHistoryPriceParameter[] hisParaVOs,
      M4310TranTypeVO tranTypeVO) throws BusinessException {
    return FindPriceService.getHistoryPriceQryService().findHistoryPrice(
        hisParaVOs, tranTypeVO);
  }

  public FindPriceResultVO[] findPrice(FindPriceParaVO[] findPriceParas,
      String saleOrg) throws BusinessException {
    return FindPriceService.getFindSalePrice().findPrice(findPriceParas,
        saleOrg);
  }
}
