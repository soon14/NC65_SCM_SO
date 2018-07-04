package nc.pubitf.so.m30.sobalance;

import java.io.Serializable;

import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;

public class RewriteToArEngrossPara implements Serializable {

  /**
   *
   */
  private static final long serialVersionUID = 8304061881057221479L;

  /** 收款单表体id */
  private String cpaybillrowid;

  /** 收款金额占用量（变化量） */
  private UFDouble nmny;

  public RewriteToArEngrossPara(String cpaybillrowid, UFDouble nmny) {

    if (PubAppTool.isNull(cpaybillrowid)) {

      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0","04006011-0222")/*@res "要回写收款单表体id不可为空。"*/);
    }
    this.cpaybillrowid = cpaybillrowid;

    if (nmny == null) {

      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0","04006011-0223")/*@res "收款金额占用量不可为空。"*/);
    }
    this.nmny = nmny;
  }

  public String getCpaybillrowid() {
    return this.cpaybillrowid;
  }

  public UFDouble getNmny() {
    return this.nmny;
  }

}