package nc.pubitf.so.m30.so.m32;

import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;

/**
 * 销售发票回写销售订单的回写参数类
 * 
 * @author 刘志伟
 * @since 6.0
 * @time 2010-01-28 下午13:49:07
 */
public class Rewrite32Para {

  /** 销售订单表体id */
  private String csaleorderbid;

  /** 销售发票数量（变化量） */
  private UFDouble nchangenum;

  /**
   * Rewrite32Para 的构造子
   * 
   * @param csaleorderbid 要回写销售订单表体行的id
   * @param nchangenum 销售出库应发数量变化量
   */
  public Rewrite32Para(String csaleorderbid, UFDouble nchangenum) {
    if (PubAppTool.isNull(csaleorderbid)) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006011_0", "04006011-0208")/*@res "要回写销售订单表体行的id不可为空。"*/);
    }
    this.csaleorderbid = csaleorderbid;

    /**
     * 这个校验不需要了 折扣数量可能是null
     * */
    // if (nchangenum == null) {
    // ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0","04006011-0216")/*@res
    // "回写销售开票数量不可为空。"*/);
    // }
    this.nchangenum = nchangenum;
  }

  public String getCsaleorderbid() {
    return this.csaleorderbid;
  }

  public UFDouble getNchangenum() {
    return this.nchangenum;
  }

}
