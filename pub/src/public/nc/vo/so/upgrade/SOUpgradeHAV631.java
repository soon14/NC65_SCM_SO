package nc.vo.so.upgrade;

import nc.bs.sm.accountmanage.IUpdateAccount;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import org.apache.commons.lang.StringUtils;

/**
 *  恒安补丁元数据升级类
 * 
 * @since 6.31
 * @version 2014-8-25 上午11:14:35
 * @author quyt
 */

public class SOUpgradeHAV631 implements IUpdateAccount {
  
  @Override
  public void doBeforeUpdateData(String oldVersion, String newVersion)
      throws Exception {
    if (StringUtils.isEmpty(oldVersion)){
      // 版本不知道的话，无法进行数据升级
      return;
    }
    if (oldVersion.startsWith("6.3")){
      // 升级赠品兑付字段
      this.updateM30TrantypeBlrgcashflag();
      // 升级 主记账单价取价规则字段     
      this.updateM30TrantypeNaccpricerule();
    }
  }
  
  @Override
  public void doBeforeUpdateDB(String oldVersion, String newVersion)
      throws Exception {
  }


  @Override
  public void doAfterUpdateData(String oldVersion, String newVersion)
      throws Exception {
  }

  /**
   * 升级销售订单交易类型，设置赠品兑付默认值
   * @throws Exception
   */
  private void updateM30TrantypeBlrgcashflag() throws Exception{
    try{
      DataAccessUtils accessutil = new DataAccessUtils();
      accessutil.update("update so_m30trantype set blrgcashflag='N' where blrgcashflag is null  and dr=0 ");
    }
    catch (Exception e){
      ExceptionUtils.marsh(e);
    }
  }
  /**
   * 升级销售订单交易类型，设置主记账单价取价规则默认值
   * @throws Exception
   */
  private void updateM30TrantypeNaccpricerule() throws Exception{
    try{
      DataAccessUtils accessutil = new DataAccessUtils();
      accessutil.update("update so_m30trantype set naccpricerule=4 where naccpricerule is null  and dr=0 ");
      }
    catch (Exception e){
      ExceptionUtils.marsh(e);
    }
  }

}
