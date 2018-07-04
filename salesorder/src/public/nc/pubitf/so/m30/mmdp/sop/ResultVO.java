package nc.pubitf.so.m30.mmdp.sop;

import java.io.Serializable;

import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;

/**
 * 
 * @since 6.0
 * @version 2011-12-5 下午03:22:00
 * @author 么贵敬
 */
public class ResultVO implements Serializable {

  private static final long serialVersionUID = -3862134590137164805L;

  /**
   * 物料
   */
  private String cmaterialid;

  /**
   * 库存组织
   */
  private String csendstockorgid;

  /**
   * 发货日期
   */
  private UFDate dsenddate;

  /**
   * 主数量
   */
  private UFDouble nnum;

  public String getCmaterialid() {
    return this.cmaterialid;
  }

  public String getCsendstockorgid() {
    return this.csendstockorgid;
  }

  public UFDate getDsenddate() {
    return this.dsenddate;
  }

  public UFDouble getNnum() {
    return this.nnum;
  }

  public void setCmaterialid(String cmaterialid) {
    this.cmaterialid = cmaterialid;
  }

  public void setCsendstockorgid(String csendstockorgid) {
    this.csendstockorgid = csendstockorgid;
  }

  public void setDsenddate(UFDate dsenddate) {
    this.dsenddate = dsenddate;
  }

  public void setNnum(UFDouble nnum) {
    this.nnum = nnum;
  }
}
