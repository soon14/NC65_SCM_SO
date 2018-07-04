package nc.pubitf.so.m30.so.m4331;

import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;

/**
 * 发货单回写上游单据参数
 * 
 * @since 6.1
 * @version 2013-05-20 15:58:46
 * @author yixl
 */
public class Rewrite4331Para {

  /** 是否强制行发货关闭（取发货单行bclosesrcflag标记），默认false，发货安排用 */
  private UFBoolean bclosed = UFBoolean.FALSE;

  /** 销售订单表体id */
  private String csaleorderbid;

  /** 发货数量（变化量） */
  private UFDouble nchangenum;

  /**
   * 出库关闭状态
   */
  private UFBoolean bboutendflag;

  /**
   * Rewrite4331Para 的构造子
   * 
   * @param csaleorderbid 要回写销售订单表体行的id
   * @param nchangenum 发货数量变化量
   * @param bclosed
   * @param bboutendflag 出库关闭
   */
  public Rewrite4331Para(String csaleorderbid, UFDouble nchangenum,
      UFBoolean bclosed, UFBoolean bboutendflag) {
    if (PubAppTool.isNull(csaleorderbid)) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006011_0", "04006011-0219")/*@res "要回写发货单表体行的id不可为空。"*/);
    }
    this.csaleorderbid = csaleorderbid;

    if (nchangenum == null) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006011_0", "04006011-0220")/*@res "发货数量不可为空。"*/);
    }
    this.nchangenum = nchangenum;

    if (bclosed != null) {
      this.bclosed = bclosed;
    }

    this.bboutendflag = bboutendflag;
  }

  /**
   * 是否强制行发货关闭
   * 
   * @return UFBoolean
   */
  public UFBoolean getBclosed() {
    return this.bclosed;
  }

  /**
   * 获得订单表体ID
   * 
   * @return 销售订单表体ID
   */
  public String getCsaleorderbid() {
    return this.csaleorderbid;
  }

  /**
   * 获得发货数量
   * 
   * @return 发货数量
   */
  public UFDouble getNchangenum() {
    return this.nchangenum;
  }

  /**
   * 获得出库关闭
   * 
   * @return 出库关闭
   */
  public UFBoolean getBboutendflag() {
    return this.bboutendflag;
  }

}
