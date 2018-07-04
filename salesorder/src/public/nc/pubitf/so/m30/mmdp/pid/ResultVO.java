package nc.pubitf.so.m30.mmdp.pid;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;

/**
 * 
 * @since 6.0
 * @version 2011-12-5 ÏÂÎç03:22:00
 * @author Ã´¹ó¾´
 */
public class ResultVO implements Serializable {
  private static final long serialVersionUID = -1070767289976148318L;

  private Map<String, Object> valuemap = new HashMap<String, Object>();

  public String getCcustomerid() {
    return (String) this.valuemap.get(SaleOrderHVO.CCUSTOMERID);
  }

  public String getCmaterialid() {
    return (String) this.valuemap.get(SaleOrderBVO.CMATERIALID);
  }

  public String getCproductorid() {
    return (String) this.valuemap.get(SaleOrderBVO.CPRODUCTORID);
  }

  public String getCprojectid() {
    return (String) this.valuemap.get(SaleOrderBVO.CPROJECTID);
  }

  public String getCsendstockorgid() {
    return (String) this.valuemap.get(SaleOrderBVO.CSENDSTOCKORGID);
  }

  public String getCvendorid() {
    return (String) this.valuemap.get(SaleOrderBVO.CVENDORID);
  }

  public UFDate getDsenddate() {
    return (UFDate) this.valuemap.get(SaleOrderBVO.DSENDDATE);
  }

  public UFDouble getNnum() {
    return (UFDouble) this.valuemap.get(SaleOrderBVO.NNUM);
  }

  public String getVfree1() {
    return (String) this.valuemap.get(SaleOrderBVO.VFREE1);
  }

  public String getVfree10() {
    return (String) this.valuemap.get(SaleOrderBVO.VFREE10);
  }

  public String getVfree2() {
    return (String) this.valuemap.get(SaleOrderBVO.VFREE2);
  }

  public String getVfree3() {
    return (String) this.valuemap.get(SaleOrderBVO.VFREE3);
  }

  public String getVfree4() {
    return (String) this.valuemap.get(SaleOrderBVO.VFREE4);
  }

  public String getVfree5() {
    return (String) this.valuemap.get(SaleOrderBVO.VFREE5);
  }

  public String getVfree6() {
    return (String) this.valuemap.get(SaleOrderBVO.VFREE6);
  }

  public String getVfree7() {
    return (String) this.valuemap.get(SaleOrderBVO.VFREE7);
  }

  public String getVfree8() {
    return (String) this.valuemap.get(SaleOrderBVO.VFREE8);
  }

  public String getVfree9() {
    return (String) this.valuemap.get(SaleOrderBVO.VFREE9);
  }

  public void setAttributeValue(String key, Object value) {
    this.valuemap.put(key, value);
  }
  
  public String getCmffileid() {
    return (String) this.valuemap.get(SaleOrderBVO.CMFFILEID);
  }

}
