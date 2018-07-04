package nc.pubitf.so.m4331.so.m33;

import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;

public class RewriteEstarnumPara {
  /** 发货单表体id */
  private String cdeliverybid;

  /** 暂估数量（变化量） */
  private UFDouble estarnum;

  /**
   * sendnum 的构造子
   * 
   * @param csaleorderbid
   *          要回写销售订单表体行的id
   * @param estarnum
   *          暂估数量变化量
   */
  public RewriteEstarnumPara(String cdeliverybid, UFDouble estarnum) {

    if (PubAppTool.isNull(cdeliverybid)) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006002_0", "04006002-0102")/*@res "要回写发货单表体行的id不可为空。"*/);
    }
    this.cdeliverybid = cdeliverybid;
    this.estarnum = estarnum;
  }

  public String getCdeliverybid() {
    return this.cdeliverybid;
  }

  public UFDouble getEstarnum() {
    return this.estarnum;
  }

  public void setCdeliverybid(String cdeliverybid) {
    this.cdeliverybid = cdeliverybid;
  }

  public void setEstarnum(UFDouble estarnum) {
    this.estarnum = estarnum;
  }
}
