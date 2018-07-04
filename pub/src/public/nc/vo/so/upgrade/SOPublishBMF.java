package nc.vo.so.upgrade;

import java.util.ArrayList;
import java.util.List;

import nc.scmmm.upgrade.scmpub.bmfpublish.AbstractSCMPublishBMF;
import nc.scmmm.upgrade.scmpub.bmfpublish.BmfFilePath;
import nc.scmmm.upgrade.scmpub.bmfpublish.MulitParentDirs;

/**
 * 633so模块补丁盘元数据发布程序
 * 
 * @since 6.33
 * @version 2014-6-26 上午10:58:13
 * @author 纪录
 */
public class SOPublishBMF extends AbstractSCMPublishBMF {

  @Override
  protected String getModuleDir() {
    return "so";
  }

  @Override
  protected BmfFilePath[] getBmfFilePath() {
    List<BmfFilePath> paths = new ArrayList<BmfFilePath>();

    paths.add(new BmfFilePath("ardeduction", "ardeduction.bmf")); // 客户费用单元数据
    paths.add(new BmfFilePath("m35trantype", "m35trantype.bmf")); // 销售费用单类型元数据
    paths.add(new BmfFilePath("SaleQuotation", "salequotation.bmf")); // 报价单元数据
    paths.add(new BmfFilePath("SO_Buylargess", "buylargess.bmf")); // 买赠设置元数据
    paths.add(new BmfFilePath("SO_SaleOrder", "saleorder.bmf")); // 销售订单元数据
    paths.add(new BmfFilePath("saleinvoice", "saleinvoice.bmf")); // 销售发票元数据
    paths.add(new BmfFilePath(new MulitParentDirs("SO_SaleSquare", "32"),
        "salesquare32.bmf")); // 销售结算元数据

    return paths.toArray(new BmfFilePath[0]);
  }
}
