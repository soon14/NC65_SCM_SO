package nc.vo.so.pub.util;

import nc.bs.framework.common.InvocationInfoProxy;
import nc.itf.org.IOrgConst;
import nc.itf.scmpub.reference.uap.bd.currency.CurrencyRate;
import nc.itf.scmpub.reference.uap.para.SysParaInitQuery;
import nc.pubitf.uapbd.CurrencyRateUtilHelper;
import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;

/**
 * 销售模块汇率工具类
 * 
 * @since 6.0
 * @version 2011-1-6 上午08:37:32
 * @author 么贵敬
 */
public class SOCurrencyUtil {

  public static final String GLOBAL_DISENABLE = NCLangRes4VoTransl.getNCLangRes().getStrByID("4006004_0", "04006004-0218")/*不启用全局本位币*/;

  public static final String GROUP_DISENABLE = NCLangRes4VoTransl.getNCLangRes().getStrByID("4006004_0", "04006004-0219")/*不启用集团本位币*/;

  public static final String LOCAL_CURRENCY = NCLangRes4VoTransl.getNCLangRes().getStrByID("4006004_0", "04006004-0220")/*基于原币计算*/;

  public static final String ORG_CURRENCY = NCLangRes4VoTransl.getNCLangRes().getStrByID("4006004_0", "04006004-0221")/*基于组织本位币计算*/;

  private static SOCurrencyUtil util = new SOCurrencyUtil();

  private SOCurrencyUtil() {
    super();
  }

  public static String getGlobalLocalCurrency() {
    return CurrencyRateUtilHelper.getInstance().getLocalCurrtypeByOrgID(
        IOrgConst.GLOBEORG);
  }

  /**
   * 销售模块取买入汇率(根据全局)
   * 
   * @param src_currency_pk
   * @param date
   * @return
   */
  public static UFDouble getGlobalLocalCurrencyBuyRate(String src_currency_pk,
      UFDate date) {
    UFDouble changestrate =
        CurrencyRate.getGlobalLocalCurrencyBuyRate(src_currency_pk, date);
    return changestrate;
  }

  public static String getGroupLocalCurrency() {
    String pk_group = InvocationInfoProxy.getInstance().getGroupId();
    return CurrencyRateUtilHelper.getInstance().getLocalCurrtypeByOrgID(
        pk_group);
  }

  /**
   * 销售模块取买入汇率(根据集团)
   * 
   * @param src_currency_pk
   * @param date
   * @return
   */
  public static UFDouble getGroupLocalCurrencyBuyRate(String src_currency_pk,
      UFDate date) {
    UFDouble changestrate =
        CurrencyRate.getGroupLocalCurrencyBuyRate(src_currency_pk, date);
    return changestrate;
  }

  /**
   * 销售模块取买入汇率(根据组织)
   * 
   * @param pk_org 主组织
   * @param src_currency_pk 来源币种
   * @param dest_currency_pk 目标币种
   * @param billdate 日期
   * @return 买入汇率
   */
  public static UFDouble getInCurrencyRateByOrg(String pk_org,
      String src_currency_pk, String dest_currency_pk, UFDate billdate) {
    UFDouble changestrate =
        CurrencyRate.getCurrencyBuyRateByOrg(pk_org, src_currency_pk,
            dest_currency_pk, billdate);
    return changestrate;
  }

  public static SOCurrencyUtil getInstance() {
    return SOCurrencyUtil.util;
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
  public boolean isGlobalCurrencyEnable() {

    return !SOCurrencyUtil.GLOBAL_DISENABLE.equals(this.getNC002());

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
  public boolean isGroupCurrencyEnable() {
    return !SOCurrencyUtil.GROUP_DISENABLE.equals(this.getNC001());

  }

  public boolean isLocalCurToGlobalMoney() {

    return SOCurrencyUtil.LOCAL_CURRENCY.equals(this.getNC002());
  }

  public boolean isLocalCurToGroupMoney() {
    return SOCurrencyUtil.LOCAL_CURRENCY.equals(this.getNC001());
  }

  public boolean isOrgCurToGlobalMoney() {

    return SOCurrencyUtil.ORG_CURRENCY.equals(this.getNC002());
  }

  public boolean isOrgCurToGroupMoney() {
    return SOCurrencyUtil.ORG_CURRENCY.equals(this.getNC001());
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
  private String getNC001() {
    String nc001 = null;

    String pk_group = InvocationInfoProxy.getInstance().getGroupId();
    nc001 = SysParaInitQuery.getParaString(pk_group, "NC001");

    if (null == nc001) {
      nc001 = SOCurrencyUtil.GROUP_DISENABLE;
    }
    return nc001;
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
  private String getNC002() {
    String nc002 = null;

    nc002 = SysParaInitQuery.getParaString(IOrgConst.GLOBEORG, "NC002");

    if (null == nc002) {
      nc002 = SOCurrencyUtil.GLOBAL_DISENABLE;
    }
    return nc002;
  }
}
