package nc.pubitf.so.m30.so.withdraw;

import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;

public class Rewrite30Para {

  /** 销售订单表体id */
  private String csaleorderbid;

  /** 销售订单退货（变化量） */
  private UFDouble nnum;

  public Rewrite30Para(String csaleorderbid, UFDouble nnum) {

    if (PubAppTool.isNull(csaleorderbid)) {

      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0","04006011-0208")/*@res "要回写销售订单表体行的id不可为空。"*/);
    }
    this.csaleorderbid = csaleorderbid;

    if (nnum == null) {

      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0","04006011-0221")/*@res "销售订单退货不可为空。"*/);
    }
    this.nnum = nnum;
  }

  public String getCsaleorderbid() {
    return this.csaleorderbid;
  }

  public UFDouble getNnum() {
    return this.nnum;
  }

}