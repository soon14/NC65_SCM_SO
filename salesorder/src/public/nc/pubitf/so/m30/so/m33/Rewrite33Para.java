package nc.pubitf.so.m30.so.m33;

import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;

/**
 * 销售结算回写销售订单的回写参数类
 *
 * @author zhangcheng
 * @since 6.0
 * @time 2010-01-28 下午13:49:07
 */
public class Rewrite33Para {

  /** 销售订单表体id */
  private String csaleorderbid;

  /** 销售结算金额（变化量） */
  private UFDouble narmny;

  /** 销售结算数量（变化量） */
  private UFDouble narnum;

  public Rewrite33Para(String csaleorderbid, UFDouble narnum) {
    if (PubAppTool.isNull(csaleorderbid)) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0","04006011-0208")/*@res "要回写销售订单表体行的id不可为空。"*/);
    }
    this.csaleorderbid = csaleorderbid;

    if (narnum == null) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0","04006011-0217")/*@res "销售结算数量不可为空。"*/);
    }
    this.narnum = narnum;
  }

  /**
   * narnum 的构造子
   *
   * @param csaleorderbid 要回写销售订单表体行的id
   * @param nnum 销售应收结算数量变化量
   */
  public Rewrite33Para(String csaleorderbid, UFDouble narnum, UFDouble narmny) {
    if (PubAppTool.isNull(csaleorderbid)) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0","04006011-0208")/*@res "要回写销售订单表体行的id不可为空。"*/);
    }
    this.csaleorderbid = csaleorderbid;

    this.narnum = narnum;

    if (narmny == null) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0","04006011-0218")/*@res "销售结算金额不可为空。"*/);
    }
    this.narmny = narmny;
  }

  public String getCsaleorderbid() {
    return this.csaleorderbid;
  }

  public UFDouble getNarmny() {
    return this.narmny;
  }

  public UFDouble getNarnum() {
    return this.narnum;
  }

  public void setNarmny(UFDouble narmny) {
    this.narmny = narmny;
  }

  public void setNarnum(UFDouble narnum) {
    this.narnum = narnum;
  }

}