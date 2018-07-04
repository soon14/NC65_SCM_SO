package nc.pubitf.so.m4331.qc.mc001;

import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;

public class RewritePara4331ForC001 {
  /** 是否报检 */
  private UFBoolean bcheckflag;

  /** 发货单表体id */
  private String cdeliverybid;

  /** 累计报检数量 */
  private UFDouble totalreportnum;

  /**
   *
   * @param cdeliverybid
   * @param lossnum
   */
  public RewritePara4331ForC001(String cdeliverybid, UFDouble totalreportnum,
      UFBoolean bcheckflag) {
    if (PubAppTool.isNull(cdeliverybid)) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006002_0","04006002-0102")/*@res "要回写发货单表体行的id不可为空。"*/);
    }
    this.cdeliverybid = cdeliverybid;
    this.totalreportnum = totalreportnum;
    this.bcheckflag = bcheckflag;
  }

  public UFBoolean getBcheckflag() {
    return this.bcheckflag;
  }

  public String getCdeliverybid() {
    return this.cdeliverybid;
  }

  public UFDouble getTotalreportnum() {
    return this.totalreportnum;
  }
}