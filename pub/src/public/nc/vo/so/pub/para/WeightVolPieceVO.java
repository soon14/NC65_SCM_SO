package nc.vo.so.pub.para;

import java.io.Serializable;

import nc.vo.pub.lang.UFDouble;

public class WeightVolPieceVO implements Serializable {
  private static final long serialVersionUID = 1L;

  // 件数换算率
  private String npiece;

  // 标准体积
  private UFDouble nvol;

  // 标准重量
  private UFDouble nweight;

  public String getNpiece() {
    return this.npiece;
  }

  public UFDouble getNvol() {
    return this.nvol;
  }

  public UFDouble getNweight() {
    return this.nweight;
  }

  public void setNpiece(String npiece) {
    this.npiece = npiece;
  }

  public void setNvol(UFDouble nvol) {
    this.nvol = nvol;
  }

  public void setNweight(UFDouble nweight) {
    this.nweight = nweight;
  }
}
