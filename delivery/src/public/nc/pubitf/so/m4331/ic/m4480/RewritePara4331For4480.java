package nc.pubitf.so.m4331.ic.m4480;

import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;

/**
 * 预留回写发货单参数了
 *
 * @since 6.0
 * @version 2011-3-25 下午03:52:57
 * @author 祝会征
 */
public class RewritePara4331For4480 {
  /** 销售订单表体id */
  private String cdeliverybid;

  /** 预留数量（变化量） */
  private UFDouble nreqrsnum;

  public RewritePara4331For4480(String cdeliverybid, UFDouble nreqrsnum) {
    if (PubAppTool.isNull(cdeliverybid)) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006002_0","04006002-0102")/*@res "要回写发货单表体行的id不可为空。"*/);
    }
    this.cdeliverybid = cdeliverybid;
    if (nreqrsnum == null) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006002_0","04006002-0103")/*@res "预留数量不可为空。"*/);
    }
    this.nreqrsnum = nreqrsnum;
  }

  public String getCdeliverBid() {
    return this.cdeliverybid;
  }

  public UFDouble getNreqrsnum() {
    return this.nreqrsnum;
  }
}