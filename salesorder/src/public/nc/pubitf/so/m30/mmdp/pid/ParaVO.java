package nc.pubitf.so.m30.mmdp.pid;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;

/**
 * 
 * @since 6.0
 * @version 2011-12-5 下午02:52:18
 * @author 么贵敬
 */
public class ParaVO implements Serializable {

  public enum ParaVOKeyEnum {
    CCUSTOMERID(SaleOrderHVO.CCUSTOMERID),
    CMATERIALID(SaleOrderBVO.CMATERIALID),
    CPRODUCTORID(SaleOrderBVO.CPRODUCTORID),
    CPROJECTID(SaleOrderBVO.CPROJECTID),
    CSENDSTOCKORGID(SaleOrderBVO.CSENDSTOCKORGID),
    CVENDORID(SaleOrderBVO.CVENDORID),
    VFREE1(SaleOrderBVO.VFREE1),
    VFREE10(SaleOrderBVO.VFREE10),
    VFREE2(SaleOrderBVO.VFREE2),
    VFREE3(SaleOrderBVO.VFREE3),
    VFREE4(SaleOrderBVO.VFREE4),
    VFREE5(SaleOrderBVO.VFREE5),
    VFREE6(SaleOrderBVO.VFREE6),
    VFREE7(SaleOrderBVO.VFREE7),
    VFREE8(SaleOrderBVO.VFREE8),
    VFREE9(SaleOrderBVO.VFREE9),
    CMFFILEID(SaleOrderBVO.CMFFILEID);

    private String keyname;

    private ParaVOKeyEnum(String value) {
      this.keyname = value;
    }

    public String getValue() {
      return this.keyname;
    }
  }

  // /** 订单客户 */
  // public static final String CCUSTOMERID = SaleOrderHVO.CCUSTOMERID;
  //
  // /** 物料ID */
  // public static final String CMATERIALID = SaleOrderBVO.CMATERIALID;
  //
  // /** 生产厂商 */
  // public static final String CPRODUCTORID = SaleOrderBVO.CPRODUCTORID;
  //
  // /** 项目 */
  // public static final String CPROJECTID = SaleOrderBVO.CPROJECTID;
  //
  // /** 发货组织ID */
  // public static final String CSENDSTOCKORGID = SaleOrderBVO.CSENDSTOCKORGID;
  //
  // /** 供应商 */
  // public static final String CVENDORID = SaleOrderBVO.CVENDORID;
  //
  // /** 自由辅助属性 */
  // public static final String VFREE1 = SaleOrderBVO.VBDEF1;
  //
  // /** 自由辅助属性 */
  // public static final String VFREE10 = SaleOrderBVO.VBDEF10;
  //
  // /** 自由辅助属性 */
  // public static final String VFREE2 = SaleOrderBVO.VBDEF2;
  //
  // /** 自由辅助属性 */
  // public static final String VFREE3 = SaleOrderBVO.VBDEF3;
  //
  // /** 自由辅助属性 */
  // public static final String VFREE4 = SaleOrderBVO.VBDEF4;
  //
  // /** 自由辅助属性 */
  // public static final String VFREE5 = SaleOrderBVO.VBDEF5;
  //
  // /** 自由辅助属性 */
  // public static final String VFREE6 = SaleOrderBVO.VBDEF6;
  //
  // /** 自由辅助属性 */
  // public static final String VFREE7 = SaleOrderBVO.VBDEF7;
  //
  // /** 自由辅助属性 */
  // public static final String VFREE8 = SaleOrderBVO.VBDEF8;
  //
  // /** 自由辅助属性 */
  // public static final String VFREE9 = SaleOrderBVO.VBDEF9;

  private static final long serialVersionUID = 6137272069299049888L;

  private Map<String, String> valuemap = new HashMap<String, String>();

  public Object getAttributeValue(String key) {
    return this.valuemap.get(key);
  }

  /**
   * 设置客户
   * 
   * @param ccustomerid 客户
   */
  public void setCcustomerid(String ccustomerid) {
    this.valuemap.put(SaleOrderHVO.CCUSTOMERID, ccustomerid);
  }

  /**
   * 设置物料最新版本
   * 
   * @param cmaterialid 物料最新版本
   */
  public void setCmaterialid(String cmaterialid) {
    this.valuemap.put(SaleOrderBVO.CMATERIALID, cmaterialid);
  }

  /**
   * 设置生产厂商
   * 
   * @param cproductorid 生产厂商
   */
  public void setCproductorid(String cproductorid) {
    this.valuemap.put(SaleOrderBVO.CPRODUCTORID, cproductorid);
  }

  /**
   * 设置项目
   * 
   * @param cprojectid 项目
   */
  public void setCprojectid(String cprojectid) {
    this.valuemap.put(SaleOrderBVO.CPROJECTID, cprojectid);
  }

  /**
   * 设置发货库存组织最新版本
   * 
   * @param csendstockorgid 发货库存组织最新版本
   */
  public void setCsendstockorgid(String csendstockorgid) {
    this.valuemap.put(SaleOrderBVO.CSENDSTOCKORGID, csendstockorgid);
  }

  /**
   * 设置供应商
   * 
   * @param cvendorid 供应商
   */
  public void setCvendorid(String cvendorid) {
    this.valuemap.put(SaleOrderBVO.CVENDORID, cvendorid);
  }

  /**
   * 设置自由辅助属性1
   * 
   * @param vfree1 自由辅助属性1
   */
  public void setVfree1(String vfree1) {
    this.valuemap.put(SaleOrderBVO.VFREE1, vfree1);
  }

  /**
   * 设置自由辅助属性10
   * 
   * @param vfree10 自由辅助属性10
   */
  public void setVfree10(String vfree10) {
    this.valuemap.put(SaleOrderBVO.VFREE10, vfree10);
  }

  /**
   * 设置自由辅助属性2
   * 
   * @param vfree2 自由辅助属性2
   */
  public void setVfree2(String vfree2) {
    this.valuemap.put(SaleOrderBVO.VFREE2, vfree2);
  }

  /**
   * 设置自由辅助属性3
   * 
   * @param vfree3 自由辅助属性3
   */
  public void setVfree3(String vfree3) {
    this.valuemap.put(SaleOrderBVO.VFREE3, vfree3);
  }

  /**
   * 设置自由辅助属性4
   * 
   * @param vfree4 自由辅助属性4
   */
  public void setVfree4(String vfree4) {
    this.valuemap.put(SaleOrderBVO.VFREE4, vfree4);
  }

  /**
   * 设置自由辅助属性5
   * 
   * @param vfree5 自由辅助属性5
   */
  public void setVfree5(String vfree5) {
    this.valuemap.put(SaleOrderBVO.VFREE5, vfree5);
  }

  /**
   * 设置自由辅助属性6
   * 
   * @param vfree6 自由辅助属性6
   */
  public void setVfree6(String vfree6) {
    this.valuemap.put(SaleOrderBVO.VFREE6, vfree6);
  }

  /**
   * 设置自由辅助属性7
   * 
   * @param vfree7 自由辅助属性7
   */
  public void setVfree7(String vfree7) {
    this.valuemap.put(SaleOrderBVO.VFREE7, vfree7);
  }

  /**
   * 设置自由辅助属性8
   * 
   * @param vfree8 自由辅助属性8
   */
  public void setVfree8(String vfree8) {
    this.valuemap.put(SaleOrderBVO.VFREE8, vfree8);
  }

  /**
   * 设置自由辅助属性9
   * 
   * @param vfree9 自由辅助属性9
   */
  public void setVfree9(String vfree9) {
    this.valuemap.put(SaleOrderBVO.VFREE9, vfree9);
  }

  /**
   * 设置特征码
   * 
   * @param cmffileid 特征码
   */
  public void setCmffileid(String cmffileid) {
    this.valuemap.put(SaleOrderBVO.CMFFILEID, cmffileid);
  }

}
