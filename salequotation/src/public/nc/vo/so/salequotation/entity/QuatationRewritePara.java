package nc.vo.so.salequotation.entity;

import java.io.Serializable;

import nc.vo.pub.lang.UFDouble;

public class QuatationRewritePara implements Serializable {
  /**
   * 
   */
  private static final long serialVersionUID = -7399892011925015359L;

  // 操作标识：增、删，删除：3；新增：2；修改：1
  private Integer operateFlag;

  // 主表PK
  private String pk_salequobill;

  // 子表PKs
  private String pk_salequobill_b;

  // 要回写的数量（变化量）
  private UFDouble nnum;

  public Integer getOperateFlag() {
    return this.operateFlag;
  }

  public void setOperateFlag(Integer operateFlag) {
    this.operateFlag = operateFlag;
  }

  public String getPk_salequobill() {
    return this.pk_salequobill;
  }

  public void setPk_salequobill(String pkSalequobill) {
    this.pk_salequobill = pkSalequobill;
  }

  public String getPk_salequobill_b() {
    return this.pk_salequobill_b;
  }

  public void setPk_salequobill_b(String pkSalequobillB) {
    this.pk_salequobill_b = pkSalequobillB;
  }

  public UFDouble getNnum() {
    return this.nnum;
  }

  public void setNnum(UFDouble nnum) {
    this.nnum = nnum;
  }

}
