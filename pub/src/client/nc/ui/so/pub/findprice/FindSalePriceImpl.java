package nc.ui.so.pub.findprice;

import nc.itf.so.pub.findprice.ISOFindPrice;
import nc.itf.so.pub.ref.price.PriceServicesUtil;
import nc.vo.price.pub.entity.FindPriceParaVO;
import nc.vo.price.pub.entity.FindPriceResultVO;
import nc.vo.pub.BusinessException;

/**
 * 询价插件测试实现类:没用应该删除的类
 * 
 */
public class FindSalePriceImpl implements ISOFindPrice {
  @Override
  public FindPriceResultVO[] findPrice(FindPriceParaVO[] findPriceParas,
      String saleOrg) throws BusinessException {
    // 客户根据需求自定义询价方式
    return PriceServicesUtil.findPrice(findPriceParas, saleOrg);
  }
}
