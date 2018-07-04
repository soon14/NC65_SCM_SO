package nc.vo.so.m30.bind;

import java.io.Serializable;

import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;

public class OrderBindMatchPara implements Serializable {

  private static final long serialVersionUID = -4754191748698385680L;

  private UFDate dbilldate;

  private String csaleorgid;

  private String cmarterialvid;

  private UFDouble nnum;

  private Integer paraindex;

  public OrderBindMatchPara(String csaleorgid, String cmarterialvid,
      UFDouble nnum, UFDate dbilldate) {
    this.csaleorgid = csaleorgid;
    this.cmarterialvid = cmarterialvid;
    this.nnum = nnum;
    this.dbilldate = dbilldate;
  }

  public UFDate getDbilldate() {
    return this.dbilldate;
  }

  public String getCsaleorgid() {
    return this.csaleorgid;
  }

  public String getCmarterialvid() {
    return this.cmarterialvid;
  }

  public void setParaindex(Integer paraindex) {
    this.paraindex = paraindex;
  }

  public Integer getParaindex() {
    return this.paraindex;
  }

  public UFDouble getNnum() {
    return this.nnum;
  }
}
