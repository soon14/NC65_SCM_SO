package nc.pubitf.so.m30.ic.m4c;

import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;

/**
 * 销售出库单回写销售订单的回写参数类
 *
 * @author 刘志伟
 * @since 6.0
 * @time 2010-01-28 下午13:49:07
 */
public class Rewrite4CPara {

  /** 是否强制行出库关闭，默认false */
  private UFBoolean bclosed;

  /** 是否退回出库单(非退货出库单)，默认false */
  private UFBoolean breturnfalg;

  /** 销售订单表体id */
  private String csaleorderbid;

  /** 销售出库实发数量（变化量） */
  private UFDouble nchangenum;

  /** 销售出库应发数量（变化量） */
  private UFDouble nchangenotoutnum;

  /**
   * Rewrite4CPara 的构造子
   *
   * @param csaleorderbid 要回写销售订单表体行的id
   * @param nchangenotoutnum 销售出库应发数量变化量
   * @param nchangenum 销售出库实发数量变化量
   * @param bclosed 是否强制行出库关闭
   * @param bclosed 是否退回出库单
   */
  public Rewrite4CPara(String csaleorderbid, UFDouble nchangenotoutnum,
      UFDouble nchangenum, UFBoolean bclosed, UFBoolean breturnfalg) {
    if (PubAppTool.isNull(csaleorderbid)) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0","04006011-0208")/*@res "要回写销售订单表体行的id不可为空。"*/);
    }
    this.csaleorderbid = csaleorderbid;

    if (nchangenum == null) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0","04006011-0212")/*@res "销售出库实发数量不可为空。"*/);
    }
    this.nchangenum = nchangenum;

    if (nchangenotoutnum == null) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0","04006011-0213")/*@res "销售出库应发数量不可为空。"*/);
    }
    this.nchangenotoutnum = nchangenotoutnum;

    this.bclosed = (bclosed == null) ? UFBoolean.FALSE : bclosed;

    this.breturnfalg = (breturnfalg == null) ? UFBoolean.FALSE : breturnfalg;
  }

  public UFBoolean getBclosed() {
    return this.bclosed;
  }

  public UFBoolean getBreturnfalg() {
    return this.breturnfalg;
  }

  public String getCsaleorderbid() {
    return this.csaleorderbid;
  }

  public UFDouble getNchangenum() {
    return this.nchangenum;
  }

  public UFDouble getNchangenotoutnum() {
    return this.nchangenotoutnum;
  }
}