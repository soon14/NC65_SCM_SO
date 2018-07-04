package nc.pubitf.so.m30.ic.m4453;

import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;

/**
 * 途损单回写销售订单服务接口参数类
 *
 * @version 6.0
 * @author 刘志伟
 * @time 2010-9-7 上午09:44:34
 */
public class Rewrite4453Para {
  /** 销售订单表体id */
  private String csaleorderbid;

  /** 签收数量（变化量） */
  private UFDouble nsignnum;

  /** 途损数量（变化量） */
  private UFDouble ntranslossnum;

  /**
   * Rewrite4453Para 的构造子
   *
   * @param csaleorderbid 要回写销售订单表体行的id
   * @param nsignnum 签收数量（变化量）
   * @param ntranslossnum 途损数量（变化量）
   */
  public Rewrite4453Para(String csaleorderbid, UFDouble nsignnum,
      UFDouble ntranslossnum) {
    if (PubAppTool.isNull(csaleorderbid)) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0","04006011-0208")/*@res "要回写销售订单表体行的id不可为空。"*/);
    }
    this.csaleorderbid = csaleorderbid;

    if (nsignnum == null) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0","04006011-0209")/*@res "签收数量不可为空。"*/);
    }
    this.nsignnum = nsignnum;

    if (ntranslossnum == null) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0","04006011-0210")/*@res "途损数量不可为空。"*/);
    }
    this.ntranslossnum = ntranslossnum;
  }

  public String getCsaleorderbid() {
    return this.csaleorderbid;
  }

  public UFDouble getNsignnum() {
    return this.nsignnum;
  }

  public UFDouble getNtranslossnum() {
    return this.ntranslossnum;
  }

}