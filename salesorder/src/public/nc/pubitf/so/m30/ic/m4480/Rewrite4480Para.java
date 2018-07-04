package nc.pubitf.so.m30.ic.m4480;

import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;

/**
 * 预留回写销售订单接口参数类。
 *
 * @author 刘志伟
 * @since 6.0
 * @time 2010-06-08 上午09:59:07
 */
public class Rewrite4480Para {
  /** 销售订单表体id */
  private String csaleorderbid;

  /** 预留数量（变化量） */
  private UFDouble nreqrsnum;

  /**
   * Rewrite4480Para 的构造子
   *
   * @param csaleorderbid 要回写销售订单表体行的id
   * @param nreqrsnum 预留数量变化量
   */
  public Rewrite4480Para(String csaleorderbid, UFDouble nreqrsnum) {
    if (PubAppTool.isNull(csaleorderbid)) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0","04006011-0208")/*@res "要回写销售订单表体行的id不可为空。"*/);
    }
    this.csaleorderbid = csaleorderbid;

    if (nreqrsnum == null) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0","04006011-0211")/*@res "预留数量不可为空。"*/);
    }
    this.nreqrsnum = nreqrsnum;

  }

  public String getCsaleorderbid() {
    return this.csaleorderbid;
  }

  public UFDouble getNreqrsnum() {
    return this.nreqrsnum;
  }

}