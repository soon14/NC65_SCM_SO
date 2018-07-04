package nc.vo.so.m30.bind;

import java.io.Serializable;

import nc.vo.bd.material.sale.MaterialBindleVO;

public class OrderBindMatchResult implements Serializable {

  private static final long serialVersionUID = 963135639230238281L;

  private MaterialBindleVO[] bindlevos;

  public void setMatchBindleVOs(MaterialBindleVO[] matchbindlevos) {
    this.bindlevos = matchbindlevos;
  }

  public MaterialBindleVO[] getMatchBindleVOs() {
    return this.bindlevos;
  }
}
