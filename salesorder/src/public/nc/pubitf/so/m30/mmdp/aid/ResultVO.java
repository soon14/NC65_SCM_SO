package nc.pubitf.so.m30.mmdp.aid;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;

/**
 * 取得指定发货库存组织+单据状态+物料+辅助属性+单据日期+最后修改日期的销售订单明细的接口返回值
 * 
 * @since 6.0
 * @version 2011-12-5 下午03:22:00
 * @author 么贵敬
 */
public class ResultVO implements Serializable {

  private static final long serialVersionUID = 381670671356627470L;

  private Map<String, Object> valuemap = new HashMap<String, Object>();

  /**
   * 订单交易类型 （V65新增）
   * 
   * @return 订单交易类型
   */
  public String getCbilltranstypeid() {
    return (String) this.valuemap.get(SaleOrderHVO.CTRANTYPEID);
  }
  
  /**
   * 获取客户物料码 （V63新增）
   * 
   * @return 客户物料码
   */
  public String getCcustmaterialid() {
    return (String) this.valuemap.get(SaleOrderBVO.CCUSTMATERIALID);
  }

  /**
   * 获取客户
   * 
   * @return 客户
   */
  public String getCcustomerid() {
    return (String) this.valuemap.get(SaleOrderHVO.CCUSTOMERID);
  }

  /**
   * 获取物料最新版本
   * 
   * @return 物料最新版本
   */
  public String getCmaterialid() {
    return (String) this.valuemap.get(SaleOrderBVO.CMATERIALID);
  }

  /**
   * 获取物料编码
   * 
   * @return 物料编码
   */
  public String getCmaterialvid() {
    return (String) this.valuemap.get(SaleOrderBVO.CMATERIALVID);
  }

  /**
   * 获取生产厂商
   * 
   * @return 生产厂商
   */
  public String getCproductorid() {
    return (String) this.valuemap.get(SaleOrderBVO.CPRODUCTORID);
  }

  /**
   * 获取项目
   * 
   * @return 项目
   */
  public String getCprojectid() {
    return (String) this.valuemap.get(SaleOrderBVO.CPROJECTID);
  }

  /**
   * 获取行号
   * 
   * @return 行号
   */
  public String getCrowno() {
    return (String) this.valuemap.get(SaleOrderBVO.CROWNO);
  }

  /**
   * 获取销售订单附表
   * 
   * @return 销售订单附表
   */
  public String getCsaleorderbid() {
    return (String) this.valuemap.get(SaleOrderBVO.CSALEORDERBID);
  }

  /**
   * 获取销售订单主表_主键
   * 
   * @return 销售订单主表_主键
   */
  public String getCsaleorderid() {
    return (String) this.valuemap.get(SaleOrderBVO.CSALEORDERID);
  }

  /**
   * 获取发货库存组织最新版本
   * 
   * @return 发货库存组织最新版本
   */
  public String getCsendstockorgid() {
    return (String) this.valuemap.get(SaleOrderBVO.CSENDSTOCKORGID);
  }

  /**
   * 获取发货库存组织
   * 
   * @return 发货库存组织
   */
  public String getCsendstockorgvid() {
    return (String) this.valuemap.get(SaleOrderBVO.CSENDSTOCKORGVID);
  }

  /**
   * 获取主单位
   * 
   * @return 主单位
   */
  public String getCunitid() {
    return (String) this.valuemap.get(SaleOrderBVO.CUNITID);
  }

  /**
   * 获取供应商
   * 
   * @return 供应商
   */
  public String getCvendorid() {
    return (String) this.valuemap.get(SaleOrderBVO.CVENDORID);
  }

  /**
   * 获取发货日期
   * 
   * @return 发货日期
   */
  public UFDate getDsenddate() {
    return (UFDate) this.valuemap.get(SaleOrderBVO.DSENDDATE);
  }

  /**
   * 获取单据状态
   * 
   * @return 单据状态
   * @see BillStatus
   */
  public Integer getFstatusflag() {
    return (Integer) this.valuemap.get(SaleOrderHVO.FSTATUSFLAG);
  }

  /**
   * 获取累计安排生产订单数量
   * 
   * @return 累计安排生产订单数量
   */
  public UFDouble getNarrangemonum() {
    return (UFDouble) this.valuemap.get(SaleOrderBVO.NARRANGEMONUM);
  }

  /**
   * 获取累计安排请购单数量
   * 
   * @return 累计安排请购单数量
   */
  public UFDouble getNarrangepoappnum() {
    return (UFDouble) this.valuemap.get(SaleOrderBVO.NARRANGEPOAPPNUM);
  }

  /**
   * 获取累计安排采购订单数量
   * 
   * @return 累计安排采购订单数量
   */
  public UFDouble getNarrangeponum() {
    return (UFDouble) this.valuemap.get(SaleOrderBVO.NARRANGEPONUM);
  }

  /**
   * 获取累计安排委外订单数量
   * 
   * @return 累计安排委外订单数量
   */
  public UFDouble getNarrangescornum() {
    return (UFDouble) this.valuemap.get(SaleOrderBVO.NARRANGESCORNUM);
  }

  /**
   * 获取累计安排调拨申请数量
   * 
   * @return 累计安排调拨申请数量
   */
  public UFDouble getNarrangetoappnum() {
    return (UFDouble) this.valuemap.get(SaleOrderBVO.NARRANGETOAPPNUM);
  }

  /**
   * 获取累计安排调拨订单数量
   * 
   * @return 累计安排调拨订单数量
   */
  public UFDouble getNarrangetoornum() {
    return (UFDouble) this.valuemap.get(SaleOrderBVO.NARRANGETOORNUM);
  }

  /**
   * 获取主数量
   * 
   * @return 主数量
   */
  public UFDouble getNnum() {
    return (UFDouble) this.valuemap.get(SaleOrderBVO.NNUM);
  }

  /**
   * 获取预留数量
   * 
   * @return 预留数量
   */
  public UFDouble getNreqrsnum() {
    return (UFDouble) this.valuemap.get(SaleOrderBVO.NREQRSNUM);
  }

  /**
   * 获取累计出库数量
   * 
   * @return 累计出库数量
   */
  public UFDouble getNtotaloutnum() {
    return (UFDouble) this.valuemap.get(SaleOrderBVO.NTOTALOUTNUM);
  }

  /**
   * 获取累计生成计划订单数量
   * 
   * @return 累计生成计划订单数量
   */
  public UFDouble getNtotalplonum() {
    return (UFDouble) this.valuemap.get(SaleOrderBVO.NTOTALPLONUM);
  }

  /**
   * 获取单据号
   * 
   * @return 单据号
   */
  public String getVbillcode() {
    return (String) this.valuemap.get(SaleOrderHVO.VBILLCODE);
  }

  /**
   * 获取自由辅助属性1
   * 
   * @return 自由辅助属性1
   */
  public String getVfree1() {
    return (String) this.valuemap.get(SaleOrderBVO.VFREE1);
  }

  /**
   * 获取自由辅助属性10
   * 
   * @return 自由辅助属性10
   */
  public String getVfree10() {
    return (String) this.valuemap.get(SaleOrderBVO.VFREE10);
  }

  /**
   * 获取自由辅助属性2
   * 
   * @return 自由辅助属性2
   */
  public String getVfree2() {
    return (String) this.valuemap.get(SaleOrderBVO.VFREE2);
  }

  /**
   * 获取自由辅助属性3
   * 
   * @return 自由辅助属性3
   */
  public String getVfree3() {
    return (String) this.valuemap.get(SaleOrderBVO.VFREE3);
  }

  /**
   * 获取自由辅助属性4
   * 
   * @return 自由辅助属性4
   */
  public String getVfree4() {
    return (String) this.valuemap.get(SaleOrderBVO.VFREE4);
  }

  /**
   * 获取自由辅助属性5
   * 
   * @return 自由辅助属性5
   */
  public String getVfree5() {
    return (String) this.valuemap.get(SaleOrderBVO.VFREE5);
  }

  /**
   * 获取自由辅助属性6
   * 
   * @return 自由辅助属性6
   */
  public String getVfree6() {
    return (String) this.valuemap.get(SaleOrderBVO.VFREE6);
  }

  /**
   * 获取自由辅助属性7
   * 
   * @return 自由辅助属性7
   */
  public String getVfree7() {
    return (String) this.valuemap.get(SaleOrderBVO.VFREE7);
  }

  /**
   * 获取自由辅助属性8
   * 
   * @return 自由辅助属性8
   */
  public String getVfree8() {
    return (String) this.valuemap.get(SaleOrderBVO.VFREE8);
  }

  /**
   * 获取自由辅助属性9
   * 
   * @return 自由辅助属性9
   */
  public String getVfree9() {
    return (String) this.valuemap.get(SaleOrderBVO.VFREE9);
  }

  public void setAttributeValue(String key, Object value) {
    this.valuemap.put(key, value);
  }

  // /**
  // * 设置物料最新版本
  // *
  // * @param cmaterialid 物料最新版本
  // */
  // public void setCmaterialid(String cmaterialid) {
  // this.setAttributeValue(SaleOrderBVO.CMATERIALID, cmaterialid);
  // }
  //
  // /**
  // * 设置物料编码
  // *
  // * @param cmaterialvid 物料编码
  // */
  // public void setCmaterialvid(String cmaterialvid) {
  // this.setAttributeValue(SaleOrderBVO.CMATERIALVID, cmaterialvid);
  // }
  //
  // public void setCrowno(String crowno) {
  // this.setAttributeValue(SaleOrderBVO.CROWNO, crowno);
  // }
  //
  // /**
  // * 设置销售订单附表
  // *
  // * @param csaleorderbid 销售订单附表
  // */
  // public void setCsaleorderbid(String csaleorderbid) {
  // this.setAttributeValue(SaleOrderBVO.CSALEORDERBID, csaleorderbid);
  // }
  //
  // /**
  // * 设置销售订单主表_主键
  // *
  // * @param csaleorderid 销售订单主表_主键
  // */
  // public void setCsaleorderid(String csaleorderid) {
  // this.setAttributeValue(SaleOrderBVO.CSALEORDERID, csaleorderid);
  // }
  //
  // /**
  // * 设置发货库存组织最新版本
  // *
  // * @param csendstockorgid 发货库存组织最新版本
  // */
  // public void setCsendstockorgid(String csendstockorgid) {
  // this.setAttributeValue(SaleOrderBVO.CSENDSTOCKORGID, csendstockorgid);
  // }
  //
  // /**
  // * 设置发货库存组织
  // *
  // * @param csendstockorgvid 发货库存组织
  // */
  // public void setCsendstockorgvid(String csendstockorgvid) {
  // this.setAttributeValue(SaleOrderBVO.CSENDSTOCKORGVID, csendstockorgvid);
  // }
  //
  // /**
  // * 设置主单位
  // *
  // * @param cunitid 主单位
  // */
  // public void setCunitid(String cunitid) {
  // this.setAttributeValue(SaleOrderBVO.CUNITID, cunitid);
  // }
  //
  // /**
  // * 设置发货日期
  // *
  // * @param dsenddate 发货日期
  // */
  // public void setDsenddate(UFDate dsenddate) {
  // this.setAttributeValue(SaleOrderBVO.DSENDDATE, dsenddate);
  // }
  //
  // /**
  // * 设置单据状态
  // *
  // * @param fstatusflag 单据状态
  // * @see BillStatus
  // */
  // public void setFstatusflag(Integer fstatusflag) {
  // this.setAttributeValue(SaleOrderHVO.FSTATUSFLAG, fstatusflag);
  // }
  //
  // /**
  // * 设置累计安排请购单数量
  // *
  // * @param narrangepoappnum 累计安排请购单数量
  // */
  // public void setNarrangepoappnum(UFDouble narrangepoappnum) {
  // this.setAttributeValue(SaleOrderBVO.NARRANGEPOAPPNUM, narrangepoappnum);
  // }
  //
  // /**
  // * 设置累计安排采购订单数量
  // *
  // * @param narrangeponum 累计安排采购订单数量
  // */
  // public void setNarrangeponum(UFDouble narrangeponum) {
  // this.setAttributeValue(SaleOrderBVO.NARRANGEPONUM, narrangeponum);
  // }
  //
  // /**
  // * 设置累计安排委外订单数量
  // *
  // * @param narrangescornum 累计安排委外订单数量
  // */
  // public void setNarrangescornum(UFDouble narrangescornum) {
  // this.setAttributeValue(SaleOrderBVO.NARRANGESCORNUM, narrangescornum);
  // }
  //
  // /**
  // * 设置累计安排调拨申请数量
  // *
  // * @param narrangetoappnum 累计安排调拨申请数量
  // */
  // public void setNarrangetoappnum(UFDouble narrangetoappnum) {
  // this.setAttributeValue(SaleOrderBVO.NARRANGETOAPPNUM, narrangetoappnum);
  // }
  //
  // /**
  // * 设置累计安排调拨订单数量
  // *
  // * @param narrangetoornum 累计安排调拨订单数量
  // */
  // public void setNarrangetoornum(UFDouble narrangetoornum) {
  // this.setAttributeValue(SaleOrderBVO.NARRANGETOORNUM, narrangetoornum);
  // }
  //
  // /**
  // * 设置主数量
  // *
  // * @param nnum 主数量
  // */
  // public void setNnum(UFDouble nnum) {
  // this.setAttributeValue(SaleOrderBVO.NNUM, nnum);
  // }
  //
  // /**
  // * 设置预留数量
  // *
  // * @param nreqrsnum 预留数量
  // */
  // public void setNreqrsnum(UFDouble nreqrsnum) {
  // this.setAttributeValue(SaleOrderBVO.NREQRSNUM, nreqrsnum);
  // }
  //
  // /**
  // * 设置累计出库数量
  // *
  // * @param ntotaloutnum 累计出库数量
  // */
  // public void setNtotaloutnum(UFDouble ntotaloutnum) {
  // this.setAttributeValue(SaleOrderBVO.NTOTALOUTNUM, ntotaloutnum);
  // }
  //
  // public void setVbillcode(String vbillcode) {
  // this.setAttributeValue(SaleOrderHVO.VBILLCODE, vbillcode);
  // }
  //
  // /**
  // * 设置自由辅助属性1
  // *
  // * @param vfree1 自由辅助属性1
  // */
  // public void setVfree1(String vfree1) {
  // this.setAttributeValue(SaleOrderBVO.VFREE1, vfree1);
  // }
  //
  // /**
  // * 设置自由辅助属性10
  // *
  // * @param vfree10 自由辅助属性10
  // */
  // public void setVfree10(String vfree10) {
  // this.setAttributeValue(SaleOrderBVO.VFREE10, vfree10);
  // }
  //
  // /**
  // * 设置自由辅助属性2
  // *
  // * @param vfree2 自由辅助属性2
  // */
  // public void setVfree2(String vfree2) {
  // this.setAttributeValue(SaleOrderBVO.VFREE2, vfree2);
  // }
  //
  // /**
  // * 设置自由辅助属性3
  // *
  // * @param vfree3 自由辅助属性3
  // */
  // public void setVfree3(String vfree3) {
  // this.setAttributeValue(SaleOrderBVO.VFREE3, vfree3);
  // }
  //
  // /**
  // * 设置自由辅助属性4
  // *
  // * @param vfree4 自由辅助属性4
  // */
  // public void setVfree4(String vfree4) {
  // this.setAttributeValue(SaleOrderBVO.VFREE4, vfree4);
  // }
  //
  // /**
  // * 设置自由辅助属性5
  // *
  // * @param vfree5 自由辅助属性5
  // */
  // public void setVfree5(String vfree5) {
  // this.setAttributeValue(SaleOrderBVO.VFREE5, vfree5);
  // }
  //
  // /**
  // * 设置自由辅助属性6
  // *
  // * @param vfree6 自由辅助属性6
  // */
  // public void setVfree6(String vfree6) {
  // this.setAttributeValue(SaleOrderBVO.VFREE6, vfree6);
  // }
  //
  // /**
  // * 设置自由辅助属性7
  // *
  // * @param vfree7 自由辅助属性7
  // */
  // public void setVfree7(String vfree7) {
  // this.setAttributeValue(SaleOrderBVO.VFREE7, vfree7);
  // }
  //
  // /**
  // * 设置自由辅助属性8
  // *
  // * @param vfree8 自由辅助属性8
  // */
  // public void setVfree8(String vfree8) {
  // this.setAttributeValue(SaleOrderBVO.VFREE8, vfree8);
  // }
  //
  // /**
  // * 设置自由辅助属性9
  // *
  // * @param vfree9 自由辅助属性9
  // */
  // public void setVfree9(String vfree9) {
  // this.setAttributeValue(SaleOrderBVO.VFREE9, vfree9);
  // }

  /**
   * 获取特征码
   * 
   * @return 特征码
   */
  public String getCmffileid() {
    return (String) this.valuemap.get(SaleOrderBVO.CMFFILEID);
  }
}
