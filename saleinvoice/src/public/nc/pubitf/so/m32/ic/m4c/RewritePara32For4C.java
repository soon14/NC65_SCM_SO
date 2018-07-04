package nc.pubitf.so.m32.ic.m4c;

import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>销售发票下游销售出库单回写发票累计出库数量参数
 * </ul>
 * <p>
 * 
 * @version 本版本号 6.0
 * @since 上一版本号 5.6
 * @author 冯加滨
 * @time 2010-3-23 下午06:36:26
 */
public class RewritePara32For4C {
  /**
   * 销售发票bid
   */
  private String csaleinvoicebid;

  /**
   * 累计应发未出库变化量
   */
  private UFDouble nshouldnum;

  /**
   * 累计出库数量
   */
  private UFDouble ntotaloutnum;

  public RewritePara32For4C(String saleinvoicebid, UFDouble nshouldnum,
      UFDouble ntotaloutnum) {
    if (PubAppTool.isNull(saleinvoicebid)) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006008_0", "04006008-0063")/*@res "销售发票表体行ID不可为空。"*/);
    }
    this.csaleinvoicebid = saleinvoicebid;

    this.nshouldnum = nshouldnum;
    this.ntotaloutnum = ntotaloutnum;
  }

  /**
   * 方法功能描述：返回发票表体ID。
   * <p>
   * <b>参数说明</b>
   * 
   * @return <p>
   * @author 冯加滨
   * @time 2010-3-23 下午06:37:16
   */
  public String getCsaleinvoicebid() {
    return this.csaleinvoicebid;
  }

  /**
   * 方法功能描述：返回销售出库单实发数量变化值。
   * <p>
   * <b>参数说明</b>
   * 
   * @return <p>
   * @author 冯加滨
   * @time 2010-3-23 下午06:37:52
   */
  public UFDouble getNoutnum() {
    return this.ntotaloutnum;
  }

  /**
   * 方法功能描述：返回销售出库单应发数量变化值。
   * <p>
   * <b>参数说明</b>
   * 
   * @return <p>
   * @author 冯加滨
   * @time 2010-3-23 下午06:37:31
   */
  public UFDouble getNshouldoutnum() {
    return this.nshouldnum;
  }
}
