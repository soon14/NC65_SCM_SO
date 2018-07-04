package nc.pubitf.so.m30.so.balance;

import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;

public class RewriteVerifyPara {

  /** 销售订单表体bid */
  private String csaleorderbid;

  /** 核销金额 */
  private UFDouble nmny;

  public RewriteVerifyPara(String csaleorderbid, UFDouble nmny) {
    if (PubAppTool.isNull(csaleorderbid)) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006011_0", "04006011-0214")/*@res "要回写销售订单表体id不可为空。"*/);
    }
    this.csaleorderbid = csaleorderbid;

    if (nmny == null) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006011_0", "04006011-0215")/*@res "收款金额不可为空。"*/);
    }
    this.nmny = nmny;
  }

  public String getCsaleorderbid() {
    return this.csaleorderbid;
  }

  public UFDouble getNmny() {
    return this.nmny;
  }
}
