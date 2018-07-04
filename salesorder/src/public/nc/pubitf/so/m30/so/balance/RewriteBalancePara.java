package nc.pubitf.so.m30.so.balance;

import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;

public class RewriteBalancePara {

  /** 销售订单表头id */
  private String csaleorderid;

  /** 实际收款金额 */
  private UFDouble nmny;

  /** 实际预收款金额 */
  private UFDouble npremny;

  public RewriteBalancePara(String csaleorderid, UFDouble nmny, UFDouble npremny) {
    if (PubAppTool.isNull(csaleorderid)) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0","04006011-0214")/*@res "要回写销售订单表头id不可为空。"*/);
    }
    this.csaleorderid = csaleorderid;

    if (nmny == null) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0","04006011-0215")/*@res "收款金额不可为空。"*/);
    }
    this.nmny = nmny;
    if (npremny == null) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0","04006011-0215")/*@res "收款金额不可为空。"*/);
    }
    this.npremny = npremny;
  }

  public String getCsaleorderid() {
    return this.csaleorderid;
  }

  public UFDouble getNmny() {
    return this.nmny;
  }

  public UFDouble getNpremny() {
    return this.npremny;
  }

}