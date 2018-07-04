package nc.pubitf.so.m4331.so.m33;

import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;

public class RewriteRushNumPara {
  /** 发货单表体id */
  private String cdeliverybid;

  /** 对冲数量（变化量） */
  private UFDouble rushnum;

  /**
   * sendnum 的构造子
   * 
   * @param csaleorderbid
   *          要回写销售订单表体行的id
   * @param sendnum
   *          对冲数量变化量
   */
  public RewriteRushNumPara(String cdeliverybid, UFDouble rushnum) {

    if (PubAppTool.isNull(cdeliverybid)) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006002_0", "04006002-0102")/*@res "要回写发货单表体行的id不可为空。"*/);
    }
    this.cdeliverybid = cdeliverybid;
    this.rushnum = rushnum;
  }

  public String getCdeliverybid() {
    return this.cdeliverybid;
  }

  public UFDouble getRushnum() {
    return this.rushnum;
  }

  public void setCdeliverybid(String cdeliverybid) {
    this.cdeliverybid = cdeliverybid;
  }

  public void setRushnum(UFDouble rushnum) {
    this.rushnum = rushnum;
  }
}
