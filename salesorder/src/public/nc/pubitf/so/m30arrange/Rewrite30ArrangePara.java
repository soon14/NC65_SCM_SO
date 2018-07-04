package nc.pubitf.so.m30arrange;

import java.io.Serializable;

import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;

/**
 * 补货/安排下游单据回写销售订单的回写统一参数类：
 * <P>
 * 请购单、采购订单、调入申请、调拨订单、委外订单、生产订单、计划订单
 * </p>
 * 
 * @author 刘志伟
 * @modifier 王天文
 * @since 6.0
 * @time 2011-12-12 10:05
 */
public class Rewrite30ArrangePara implements Serializable {
  private static final long serialVersionUID = -4853816405706383644L;

  /** 单据类型 */
  private String billtype;

  /** 最后货源安排人 */
  private String carrangepersonid;

  /** 销售订单表体id */
  private String csaleorderbid;

  /** 安排数量（变化量） */
  private UFDouble nnum;

  public void setNnum(UFDouble nnum) {
    this.nnum = nnum;
  }

  /**
   * Rewrite30ArrangePara 的构造子
   * 
   * @param csaleorderbid 要回写销售订单表体行的id
   * @param nnum 安排数量（变化量）
   * @param carrangepersonid 安排人
   * @param billtype 单据类型
   */
  public Rewrite30ArrangePara(String csaleorderbid, UFDouble nnum,
      String carrangepersonid, String billtype) {
    if (PubAppTool.isNull(csaleorderbid)) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006011_0", "04006011-0208")/*@res "要回写销售订单表体行的id不可为空。"*/);
    }
    this.csaleorderbid = csaleorderbid;

    if (nnum == null) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006011_0", "04006011-0224")/*@res "回写的安排数量不可为空。"*/);
    }
    this.nnum = nnum;

    if (PubAppTool.isNull(carrangepersonid)) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006011_0", "04006011-0225")/*@res "销售订单货源安排人不可为空。"*/);
    }
    this.carrangepersonid = carrangepersonid;

    if (PubAppTool.isNull(billtype)) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006011_0", "04006011-0226")/*@res "下游单据类型不能为空"*/);
    }
    this.billtype = billtype;
  }

  public String getBilltype() {
    return this.billtype;
  }

  public String getCarrangepersonid() {
    return this.carrangepersonid;
  }

  public String getCsaleorderbid() {
    return this.csaleorderbid;
  }

  public UFDouble getNnum() {
    return this.nnum;
  }

}
