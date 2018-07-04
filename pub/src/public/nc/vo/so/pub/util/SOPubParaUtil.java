package nc.vo.so.pub.util;

import nc.itf.scmpub.reference.uap.para.SysParaInitQuery;
import nc.vo.ml.NCLangRes4VoTransl;

public class SOPubParaUtil {

  public static final String CURTOLMONEY = NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0221")/*基于组织本位币计算*/;

  public static final String GLOBALLOCALCURDISENABLE = NCLangRes4VoTransl
      .getNCLangRes().getStrByID("4006004_0", "04006004-0218")/*不启用全局本位币*/;

  public static final String GROUPLOCALCURDISENABLE = NCLangRes4VoTransl
      .getNCLangRes().getStrByID("4006004_0", "04006004-0219")/*不启用集团本位币*/;

  public static final String ORIGCURTOMONEY = NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0220")/*基于原币计算*/;

  public static final String PK_GLOBLE = "GLOBLE00000000000000";

  private String nc001;

  private String nc002;

  private SOPubParaUtil() {
    super();
  }

  public static SOPubParaUtil getInstance() {
    return new SOPubParaUtil();
  }

  /**
   * 
   * 方法功能描述：返回参数NC001：集团本位币折算模式的值。
   * <p>
   * <b>参数说明</b>
   * 
   * @param pk_group
   * @return
   *         <p>
   * @author fengjb
   * @time 2010-7-6 下午08:01:39
   */
  public String getNC001(String pk_group) {
    if (null == this.nc001) {

      this.nc001 = SysParaInitQuery.getParaString(pk_group, "NC001");

      if (null == this.nc001) {
        this.nc001 = SOPubParaUtil.GROUPLOCALCURDISENABLE;
      }
    }
    return this.nc001;
  }

  /**
   * 
   * 方法功能描述：返回参数NC002：全局本位币折算模式的值。
   * <p>
   * <b>参数说明</b>
   * 
   * @param pk_group
   * @return
   *         <p>
   * @author fengjb
   * @time 2010-7-6 下午08:01:39
   */
  public String getNC002() {
    if (null == this.nc002) {

      this.nc002 =
          SysParaInitQuery.getParaString(SOPubParaUtil.PK_GLOBLE, "NC002");

      if (null == this.nc002) {
        this.nc002 = SOPubParaUtil.GLOBALLOCALCURDISENABLE;
      }
    }
    return this.nc002;
  }

  /**
   * 
   * 方法功能描述：返回是否启用全局本位币。
   * <p>
   * <b>参数说明</b>
   * 
   * @param pk_group
   * @return
   *         <p>
   * @author fengjb
   * @time 2010-7-6 下午08:13:38
   */
  public boolean isGlobalLocalCurrencyEnable() {
    if (SOPubParaUtil.GLOBALLOCALCURDISENABLE.equals(this.getNC002())) {
      return false;
    }
    return true;

  }

  /**
   * 
   * 方法功能描述：返回是否启用集团本位币。
   * <p>
   * <b>参数说明</b>
   * 
   * @param pk_group
   * @return
   *         <p>
   * @author fengjb
   * @time 2010-7-6 下午08:13:38
   */
  public boolean isGroupLocalCurrencyEnable(String pk_group) {
    if (SOPubParaUtil.GROUPLOCALCURDISENABLE.equals(this.getNC001(pk_group))) {
      return false;
    }
    return true;

  }

  public boolean isOrigCurToGlobalMoney() {
    if (SOPubParaUtil.ORIGCURTOMONEY.equals(this.getNC002())) {
      return true;
    }
    return false;

  }

  public boolean isOrigCurToGroupMoney(String pk_group) {
    if (SOPubParaUtil.ORIGCURTOMONEY.equals(this.getNC001(pk_group))) {
      return true;
    }
    return false;
  }

}
