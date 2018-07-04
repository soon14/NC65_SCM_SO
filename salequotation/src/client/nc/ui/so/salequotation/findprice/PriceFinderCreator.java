package nc.ui.so.salequotation.findprice;

import nc.ui.so.salequotation.model.FindPriceService;
import nc.vo.so.m4310trantype.entity.M4310TranTypeVO;
import nc.vo.so.m4310trantype.entity.SalequoDataSource;

public class PriceFinderCreator {

  private static PriceFinderCreator instance = new PriceFinderCreator();

  private PriceFinderCreator() {
    // do nothing
  }

  public static PriceFinderCreator getInstance() {
    return PriceFinderCreator.instance;
  }

  public AbstractPriceFinder createPriceFinder(
      FindPriceService findPriceService, M4310TranTypeVO tranTypeVO) {
    AbstractPriceFinder priceFinder;
    // 价格管理
    if (SalequoDataSource.PRICE_MANAGEMENT.value().equals(
        tranTypeVO.getFsourceflag())) {
      priceFinder = new ManagementPriceFinder(tranTypeVO);
    }
    // 历史报价
    else if (SalequoDataSource.HISTORY_PRICE.value().equals(
        tranTypeVO.getFsourceflag())) {

      priceFinder = new HistoryPriceFinder(tranTypeVO);
    }
    // 不取价
    else {
      priceFinder = new NoPriceFinder();
    }
    priceFinder.setFindPriceService(findPriceService);
    priceFinder.setPk_group(tranTypeVO.getPk_group());
    return priceFinder;
  }
}
