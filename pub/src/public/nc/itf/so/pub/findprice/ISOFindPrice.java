package nc.itf.so.pub.findprice;

import nc.impl.pubapp.plugin.IPlugin;
import nc.vo.price.pub.entity.FindPriceParaVO;
import nc.vo.price.pub.entity.FindPriceResultVO;
import nc.vo.pub.BusinessException;

public interface ISOFindPrice extends IPlugin {

  FindPriceResultVO[] findPrice(FindPriceParaVO[] findPriceParas, String saleOrg)
      throws BusinessException;
}
