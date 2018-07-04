package nc.bs.so.upgrade;

import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.scmmm.upgrade.scmpub.v633.AbstractUpgradeToV633;
import nc.scmmm.upgrade.scmpub.v633.ISCMUpdateAccount;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import org.apache.commons.lang.StringUtils;

/**
 * 63升级到统一营销631时需要执行的升级类
 * 
 * @since 6.33
 * @version 2014-6-26 上午11:10:33
 * @author 纪录
 */
public class SOUpgrade63ToV633 extends AbstractUpgradeToV633 {

  @Override
  protected ISCMUpdateAccount[] getUpgradeAccount() {
    return new ISCMUpdateAccount[] {
      new SOUpgrade63ToV63eimm()
    };
  }

  @Override
  public void doAfterUpdateData(String oldVersion, String newVersion)
      throws Exception {
    super.doAfterUpdateData(oldVersion, newVersion);

    // 还有一些是63->633或者631->633都要执行的
    if (StringUtils.isEmpty(oldVersion) || StringUtils.isEmpty(newVersion)
        || newVersion.equals(oldVersion)) {
    }
    else {
      this.updateArsubDetailBillType(oldVersion, newVersion);
    }
  }

  /**
   * 63或者631升级上来的数据，需要把so_arsub_detail这张表中的cbilltypecode由原来的单据类型ID变成code，主要是F0
   * 
   * @param oldVersion
   * @param newVersion
   * @throws BusinessException
   */
  private void updateArsubDetailBillType(String oldVersion, String newVersion)
      throws BusinessException {
    if (oldVersion.startsWith("6.3")) {
      try {
        DataAccessUtils accessutil = new DataAccessUtils();
        accessutil
            .update("update so_arsub_detail set cbilltypecode='F0' where cbilltypecode = '0000Z3000000000000F0' and dr = 0 ");
      }
      catch (Exception e) {
        ExceptionUtils.marsh(e);
      }
    }
  }
}
