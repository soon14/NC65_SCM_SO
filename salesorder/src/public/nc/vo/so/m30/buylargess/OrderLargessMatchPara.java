package nc.vo.so.m30.buylargess;

import java.io.Serializable;

import nc.pubitf.so.mbuylargess.pub.IBuyLargessMatchPara;

import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;

public class OrderLargessMatchPara implements IBuyLargessMatchPara,
    Serializable {

  private static final long serialVersionUID = -8300681515730824264L;

  private String csaleorgid;

  private String cmarterialid;

  private String ccustomerid;

  private String corigcurrencyid;

  private UFDate dbilldate;

  private UFDouble nastnum;

  private String csaleorderbid;

  private String castunitid;

  public void setCsaleorgid(String csaleorgid) {
    this.csaleorgid = csaleorgid;
  }

  @Override
  public String getCsaleorgid() {
    return this.csaleorgid;
  }

  public void setCmarterialid(String cmarterialvid) {
    this.cmarterialid = cmarterialvid;
  }

  @Override
  public String getCmarterialid() {
    return this.cmarterialid;
  }

  public void setCcustomerid(String ccustomerid) {
    this.ccustomerid = ccustomerid;
  }

  @Override
  public String getCcustomerid() {
    return this.ccustomerid;
  }

  public void setCorigcurrencyid(String corigcurrencyid) {
    this.corigcurrencyid = corigcurrencyid;
  }

  @Override
  public String getCorigcurrencyid() {
    return this.corigcurrencyid;
  }

  public void setDbilldate(UFDate dbilldate) {
    this.dbilldate = dbilldate;
  }

  @Override
  public UFDate getDbilldate() {
    return this.dbilldate;
  }

  public void setNnum(UFDouble nnum) {
    this.nastnum = nnum;
  }

  @Override
  public UFDouble getNastnum() {
    return this.nastnum;
  }

  public void setCsaleorderbid(String csaleorderbid) {
    this.csaleorderbid = csaleorderbid;
  }

  public String getCsaleorderbid() {
    return this.csaleorderbid;
  }

  public void setCastunitid(String castunitid) {
    this.castunitid = castunitid;
  }

  @Override
  public String getCastunitid() {
    return this.castunitid;
  }
}
