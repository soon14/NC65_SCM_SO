package nc.pubitf.so.m4331.dm.m4804;

import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;

/**
 * 运输单回写发货单参数
 *
 * @since 6.0
 * @version 2011-3-17 下午01:53:17
 * @author 祝会征
 */
public class RewritePara4331For4804 {

  /** 发货单表体id */
  private String cdeliverybid;

  /** 运输数量（变化量） */
  private UFDouble transnum;

  public RewritePara4331For4804(String cdeliverybid, UFDouble outnum) {

    if (PubAppTool.isNull(cdeliverybid)) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006002_0","04006002-0102")/*@res "要回写发货单表体行的id不可为空。"*/);
    }
    this.cdeliverybid = cdeliverybid;
    this.transnum = outnum;
  }

  public String getCdeliverybid() {
    return this.cdeliverybid;
  }

  public UFDouble getTransnum() {
    return this.transnum;
  }
}