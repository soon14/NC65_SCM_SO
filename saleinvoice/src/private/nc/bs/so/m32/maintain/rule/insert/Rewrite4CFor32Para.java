package nc.bs.so.m32.maintain.rule.insert;

import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;

import nc.pubitf.ic.m4c.m32.IParameter4CFor32;

/**
 * 销售发票回写销售出库单的参数
 * 
 * @since 6.3
 * @version 2012-12-21 上午08:59:50
 * @author yaogj
 */
public class Rewrite4CFor32Para implements IParameter4CFor32 {

  // 销售出库单表体ID
  private String bid;

  // 销售出库单表头ID
  private String hid;

  // 开票数量
  private UFDouble nnum;

  // 超出库单开票容差控制方式
  private String so08;

  // 销售发票表体ID
  private String voicebid;

  /**
   * 构造方法
   * 
   * @param voicebid 发票表体id
   * @param hid 出库单表头id
   * @param bid 出库单表体id
   * @param nnum 数量
   */
  public Rewrite4CFor32Para(String voicebid, String hid, String bid,
      UFDouble nnum) {

    this.voicebid = voicebid;

    if (PubAppTool.isNull(hid)) {
      ExceptionUtils
          .wrappBusinessException(NCLangRes4VoTransl.getNCLangRes().getStrByID(
              "4006008_0", "04006008-0015")/*@res "要回写销售出库单主表id不可为空。"*/);
    }
    this.hid = hid;
    if (PubAppTool.isNull(bid)) {
      ExceptionUtils
          .wrappBusinessException(NCLangRes4VoTransl.getNCLangRes().getStrByID(
              "4006008_0", "04006008-0016")/*@res "要回写销售出库单表体行id不可为空。"*/);
    }
    this.bid = bid;

    if (nnum == null) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006008_0", "04006008-0017")/*@res "销售发票数量不可为空。"*/);
    }
    this.nnum = nnum;
  }

  @Override
  public String getBid() {
    return this.bid;
  }

  @Override
  public String getHid() {
    return this.hid;
  }

  @Override
  public UFDouble getNinnum() {
    return this.nnum;
  }

  @Override
  public String getSO08() {
    return this.so08;
  }

  /**
   * 
   * @return 发票表体id
   */
  public String getVoicebid() {
    return this.voicebid;
  }

  /**
   * 
   * @param newso08 出库容差参数
   */
  public void setSO08(String newso08) {
    this.so08 = newso08;
  }

}
