package nc.pubitf.so.m4331.ic.m4453;

import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;

public class RewritePara4331For4453 {
  /** 发货单表体id */
  private String cdeliverybid;

  /** 途损数量（变化量） */
  private UFDouble lossnum;

  /**
   * sendnum 的构造子
   *
   * @param csaleorderbid
   *          要回写销售订单表体行的id
   * @param lossNum
   *          发货数量变化量
   */
  public RewritePara4331For4453(String cdeliverybid, UFDouble lossnum) {

    if (PubAppTool.isNull(cdeliverybid)) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006002_0","04006002-0102")/*@res "要回写发货单表体行的id不可为空。"*/);
    }
    this.cdeliverybid = cdeliverybid;
    this.lossnum = lossnum;
  }

  public String getCdeliverybid() {
    return this.cdeliverybid;
  }

  public UFDouble getLossnum() {
    return this.lossnum;
  }
}