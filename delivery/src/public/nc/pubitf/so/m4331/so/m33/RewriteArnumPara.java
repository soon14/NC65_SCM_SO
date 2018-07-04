package nc.pubitf.so.m4331.so.m33;

import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;

public class RewriteArnumPara {
  /** 确认应收数量（变化量） */
  private UFDouble arnum;

  /** 发货单表体id */
  private String cdeliverybid;

  /**
   * sendnum 的构造子
   * 
   * @param csaleorderbid
   *          要回写销售订单表体行的id
   * @param arnum
   *          确认应收数量变化量
   */
  public RewriteArnumPara(String cdeliverybid, UFDouble arnum) {

    if (PubAppTool.isNull(cdeliverybid)) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006002_0", "04006002-0102")/*@res "要回写发货单表体行的id不可为空。"*/);
    }
    this.cdeliverybid = cdeliverybid;
    this.arnum = arnum;
  }

  public UFDouble getArnum() {
    return this.arnum;
  }

  public String getCdeliverybid() {
    return this.cdeliverybid;
  }

  public void setArnum(UFDouble arnum) {
    this.arnum = arnum;
  }

  public void setCdeliverybid(String cdeliverybid) {
    this.cdeliverybid = cdeliverybid;
  }
}
