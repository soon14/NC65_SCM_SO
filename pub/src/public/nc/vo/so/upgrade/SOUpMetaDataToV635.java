package nc.vo.so.upgrade;

import java.io.File;

import nc.vo.pub.BusinessException;

import nc.md.persist.designer.service.IPublishService;

import nc.bs.framework.common.NCLocator;
import nc.bs.framework.common.RuntimeEnv;
import nc.bs.logging.Logger;
import nc.bs.sm.accountmanage.AbstractPatchInstall;
import nc.bs.sm.accountmanage.PatchInstallContext;

/**
 * V635元数据升级类
 * 
 * @since 进出口的 6.35 + 利润中心的635
 * @version 2014-09-23 16:55:25
 * @author jilu
 */
public class SOUpMetaDataToV635 extends AbstractPatchInstall {

  private IPublishService service;

  private String[] bmfpath;

  /**
   * 构造子
   */
  public SOUpMetaDataToV635()

  {
    this.bmfpath =
        new String[] {
          "/modules/so/METADATA/so_delivery/delivery.bmf".replace('/',
              File.separatorChar),
          "/modules/so/METADATA/SO_SaleOrder/saleorder.bmf".replace('/',
              File.separatorChar),
          "/modules/so/METADATA/saleinvoice/saleinvoice.bmf".replace('/',
              File.separatorChar),
          "/modules/so/METADATA/SO_SaleSquare/4C/salesquare4C.bmf".replace('/',
              File.separatorChar)
        };
  }

  @Override
  public void pulishMetaData(PatchInstallContext context)
      throws BusinessException {
    String path;
    StringBuilder destPath;
    String nchome = RuntimeEnv.getInstance().getNCHome();
    String[] arr = this.bmfpath;
    int len = arr.length;
    for (int i = 0; i < len; ++i) {
      path = arr[i];
      destPath = new StringBuilder(nchome);
      destPath.append(path);

      Logger.info(new StringBuilder().append("发布元数据：")
          .append(destPath.toString()).toString());/*-=notranslate=-*/
      this.getPublishService().publishMetaDataForBMF(destPath.toString());
    }
  }

  /**
   * 
   * @return IPublishService
   */
  public IPublishService getPublishService() {
    if (this.service == null) {
      this.service = NCLocator.getInstance().lookup(IPublishService.class);
    }
    return this.service;
  }

}
