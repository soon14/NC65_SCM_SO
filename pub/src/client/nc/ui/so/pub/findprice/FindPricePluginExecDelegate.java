package nc.ui.so.pub.findprice;

import nc.impl.pubapp.plugin.IPluginExecDelegate;
import nc.impl.pubapp.plugin.RegistryVO;
import nc.itf.so.pub.findprice.ISOFindPrice;
import nc.itf.so.pub.ref.price.PriceServicesUtil;
import nc.vo.price.pub.entity.FindPriceParaVO;
import nc.vo.price.pub.entity.FindPriceResultVO;

/**
 * 销售询价插件执行代理
 * 
 * @since 6.0
 * @version 2010-11-8 上午10:36:19
 * @author 刘志伟
 */
public class FindPricePluginExecDelegate implements
    IPluginExecDelegate<ISOFindPrice> {

  private FindPriceParaVO[] paraVOs;

  private String saleOrg;

  private FindPriceResultVO[] vo;

  public FindPricePluginExecDelegate(FindPriceParaVO[] paraVOs, String saleOrg) {
    this.paraVOs = paraVOs;
    this.saleOrg = saleOrg;
  }

  @Override
  public void exec(ISOFindPrice plugin, RegistryVO registryVO) throws Exception {
    this.vo = plugin.findPrice(this.paraVOs, this.saleOrg);
  }

  @Override
  public void execDefault() throws Exception {
    this.vo = PriceServicesUtil.findPrice(this.paraVOs, this.saleOrg);
  }

  public FindPriceResultVO[] getFindPriceResultVOs() {
    return this.vo;
  }

}
