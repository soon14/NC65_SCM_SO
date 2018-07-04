package nc.itf.so.pub.ref.price;

import nc.bs.framework.common.NCLocator;
import nc.itf.price.priceform.IPriceFormService;
import nc.itf.price.pub.service.IFindSalePrice;
import nc.vo.price.pub.entity.FindPriceParaVO;
import nc.vo.price.pub.entity.FindPriceResultVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class PriceServicesUtil {

  /**
   * 根据询价参数VO数组进行批询价
   * 
   * @param findPriceParas
   * @param saleOrg
   * @return FindPriceResultVO[]
   */
  public static FindPriceResultVO[] findPrice(FindPriceParaVO[] findPriceParas,
      String saleOrg) throws BusinessException {
    IFindSalePrice service =
        NCLocator.getInstance().lookup(IFindSalePrice.class);
    return service.findPrice(findPriceParas, saleOrg);
  }

  /**
   * 删除价格组成信息
   * 
   * @param cpriceformid 销售订单表体行价格组成ID
   */
  public static void deletePriceFormByBillPK(String cpriceformid)
      throws BusinessException {
    IPriceFormService service =
        NCLocator.getInstance().lookup(IPriceFormService.class);
    try {
      service.deletePriceFormByBillPK(cpriceformid);
    }
    catch (BusinessException e) {
      ExceptionUtils.marsh(e);
    }
  }
}
