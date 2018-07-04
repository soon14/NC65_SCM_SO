package nc.pubitf.so.m38.so.m30;

import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;

/**
 * 销售订单回写预订单的回写参数类
 *
 * @since 6.0
 * @version 2010-11-3 上午10:28:31
 * @author 刘志伟
 */
public class Rewrite30Para {

  /** 预订单表体id */
  private String cpreorderbid;

  /** 销售订单数量（变化量） */
  private UFDouble nnum;

  /**
   * Rewrite30Para 的构造子
   *
   * @param cpreorderbid 要回写预订单表体行的id
   * @param nnum 销售订单表体行数量变化量
   */
  public Rewrite30Para(String cpreorderbid, UFDouble nnum) {
    if (PubAppTool.isNull(cpreorderbid)) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006012_0","04006012-0033")/*@res "要回写预订单表体行的id不可为空。"*/);
    }
    this.cpreorderbid = cpreorderbid;

    if ((nnum == null) || UFDouble.ZERO_DBL.equals(nnum)) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006012_0","04006012-0034")/*@res "销售订单表体行数量不可为空或0。"*/);
    }
    this.nnum = nnum;
  }

  public String getCpreorderbid() {
    return this.cpreorderbid;
  }

  public UFDouble getNnum() {
    return this.nnum;
  }
}