package nc.vo.so.upgrade;

import org.apache.commons.lang.StringUtils;

import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.sm.accountmanage.IUpdateAccount;

import nc.impl.pubapp.pattern.database.DataAccessUtils;

/**
 * 销售V633升级处理类，与原V631升级类相同
 * 
 * @since 6.33
 * @version 2013-11-13 13:40:16
 * @author jilu
 */
public class SOUpgradeToV633 implements IUpdateAccount {

  @Override
  public void doBeforeUpdateData(String oldVersion, String newVersion)
      throws Exception {
    if (StringUtils.isEmpty(oldVersion) || StringUtils.isEmpty(newVersion)
        || newVersion.equals(oldVersion)) {
      // 旧版本、新版本不知道的话，无法进行数据升级
      return;
    }
    if (oldVersion.startsWith("6.3") && newVersion.startsWith("6.33")) {
      this.updateM35BilltypeName();
      this.updatePermissionName();
    }

  }

  @Override
  public void doBeforeUpdateDB(String oldVersion, String newVersion)
      throws Exception {
    //
  }

  @Override
  public void doAfterUpdateData(String oldVersion, String newVersion)
      throws Exception {
    if (StringUtils.isEmpty(oldVersion) || StringUtils.isEmpty(newVersion)
        || newVersion.equals(oldVersion)) {
      // 旧版本、新版本不知道的话，无法进行数据升级
      return;
    }
    if (oldVersion.startsWith("6.3") && newVersion.startsWith("6.33")) {
      this.doAfterUpdateDataFrom63To633();
    }
  }

  private void doAfterUpdateDataFrom63To633() throws Exception {

    try {
      DataAccessUtils accessutil = new DataAccessUtils();
      accessutil
          .update("update so_m35trantype set baddmanualflag='Y' where baddmanualflag is null  and dr=0 ");
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }

  }

  private void updateM35BilltypeName() throws Exception {

    try {
      DataAccessUtils accessutil = new DataAccessUtils();
      accessutil
          .update("update bd_billtype set BILLTYPENAME='~', BILLTYPENAME2='~',BILLTYPENAME3='~',BILLTYPENAME4='~',BILLTYPENAME5='~',BILLTYPENAME6='~' where PK_BILLTYPEID='35' ");
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }

  }

  private void updatePermissionName() throws Exception {

    try {
      DataAccessUtils accessutil = new DataAccessUtils();
      accessutil
          .update("update sm_permission_res set RESOURCENAME='~', RESOURCENAME2='~',RESOURCENAME3='~',RESOURCENAME4='~',RESOURCENAME5='~',RESOURCENAME6='~' where PK_PERMISSION_RES='1001Z810000000000NX6' ");
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }

  }
}
