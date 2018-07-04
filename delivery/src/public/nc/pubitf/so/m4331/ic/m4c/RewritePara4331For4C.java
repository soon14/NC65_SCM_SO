package nc.pubitf.so.m4331.ic.m4c;

import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <li>发货单下游销售出库单回写发货单累计出库数量
 *
 * @version 本版本号 6.0
 * @since 上一版本号 5.6
 * @author 祝会征
 * @time 2010-3-23 下午06:36:26
 */
public class RewritePara4331For4C {

  /** 发货单表体id */
  private String cdeliverybid;
  
  /** 发货单质检表id */
  private String cdeliverybbid;

  /** 出库数量（变化量） */
  private UFDouble outnum;

  /** 应发未出库数量变化量 */
  private UFDouble noOutnum;

  /**
   * sendnum 的构造子
   *
   * @param csaleorderbid
   *          要回写销售订单表体行的id
   * @param sendnum
   *          发货数量变化量
   */
  public RewritePara4331For4C(String cdeliverybid, String cdeliverybbid,UFDouble outnum,
      UFDouble noOutnum) {

    if (PubAppTool.isNull(cdeliverybid)) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006002_0","04006002-0102")/*@res "要回写发货单表体行的id不可为空。"*/);
    }
    this.cdeliverybid = cdeliverybid;
    this.cdeliverybbid = cdeliverybbid;
    this.outnum = outnum;
    this.noOutnum = noOutnum;
  }

  public UFDouble getNoOutnum() {
    return this.noOutnum;
  }

  public String getCdeliverybid() {
    return this.cdeliverybid;
  }
  
  public String getCdeliverybbid() {
    return this.cdeliverybbid;
  }

  public UFDouble getOutnum() {
    return this.outnum;
  }
}