package nc.pubitf.so.m38.mmdp.aid;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m38.entity.PreOrderBVO;
import nc.vo.so.m38.entity.PreOrderHVO;

/**
 * 取得指定发货库存组织+单据状态+物料+辅助属性+单据日期+最后修改日期的预订单明细的接口返回值
 * 
 * @since 6.0
 * @version 2011-12-5 下午03:22:00
 * @author 么贵敬
 */
public class ResultVO implements Serializable {

  private static final long serialVersionUID = 381670671356627470L;

  private Map<String, Object> valuemap = new HashMap<String, Object>();

  /**
   * 获取客户物料码 （V63新增）
   * 
   * @return 客户物料码
   */
  public String getCcustmaterialid() {
    return (String) this.valuemap.get(PreOrderBVO.CCUSTMATERIALID);
  }

  /**
   * 获取客户
   * 
   * @return 客户
   */
  public String getCcustomerid() {
    return (String) this.valuemap.get(PreOrderHVO.CCUSTOMERID);
  }

  /**
   * 获取物料最新版本
   * 
   * @return 物料最新版本
   */
  public String getCmaterialid() {
    return (String) this.valuemap.get(PreOrderBVO.CMATERIALID);
  }

  /**
   * 获取物料编码
   * 
   * @return 物料编码
   */
  public String getCmaterialvid() {
    return (String) this.valuemap.get(PreOrderBVO.CMATERIALVID);
  }

  /**
   * 获取预订单附表
   * 
   * @return 销售订单附表
   */
  public String getCPreOrderbid() {
    return (String) this.valuemap.get(PreOrderBVO.CPREORDERBID);
  }

  /**
   * 获取预订单主表_主键
   * 
   * @return 销售订单主表_主键
   */
  public String getCPreOrderid() {
    return (String) this.valuemap.get(PreOrderBVO.CPREORDERID);
  }

  /**
   * 获取生产厂商
   * 
   * @return 生产厂商
   */
  public String getCproductorid() {
    return (String) this.valuemap.get(PreOrderBVO.CPRODUCTORID);
  }

  /**
   * 获取项目
   * 
   * @return 项目
   */
  public String getCprojectid() {
    return (String) this.valuemap.get(PreOrderBVO.CPROJECTID);
  }

  /**
   * 获取行号
   * 
   * @return 行号
   */
  public String getCrowno() {
    return (String) this.valuemap.get(PreOrderBVO.CROWNO);
  }

  /**
   * 获取发货库存组织最新版本
   * 
   * @return 发货库存组织最新版本
   */
  public String getCsendstockorgid() {
    return (String) this.valuemap.get(PreOrderBVO.CSENDSTOCKORGID);
  }

  /**
   * 获取发货库存组织
   * 
   * @return 发货库存组织
   */
  public String getCsendstockorgvid() {
    return (String) this.valuemap.get(PreOrderBVO.CSENDSTOCKORGVID);
  }

  /**
   * 获取主单位
   * 
   * @return 主单位
   */
  public String getCunitid() {
    return (String) this.valuemap.get(PreOrderBVO.CUNITID);
  }

  /**
   * 获取供应商
   * 
   * @return 供应商
   */
  public String getCvendorid() {
    return (String) this.valuemap.get(PreOrderBVO.CVENDORID);
  }

  /**
   * 获取发货日期
   * 
   * @return 发货日期
   */
  public UFDate getDsenddate() {
    return (UFDate) this.valuemap.get(PreOrderBVO.DSENDDATE);
  }

  /**
   * 获取单据状态
   * 
   * @return 单据状态
   * 
   */
  public Integer getFstatusflag() {
    return (Integer) this.valuemap.get(PreOrderHVO.FSTATUSFLAG);
  }

  /**
   * 累计安排销售订单数量
   * 
   * @return 累计安排销售订单数量
   */
  public UFDouble getNarrnum() {
    UFDouble narrnum = (UFDouble) this.valuemap.get(PreOrderBVO.NARRNUM);
    return narrnum;
  }

  /**
   * 未执行量
   * 
   * @return 未执行量
   */
  public UFDouble getNnoarrnum() {
    UFDouble narrnum =
        MathTool.sub((UFDouble) this.valuemap.get(PreOrderBVO.NNUM),
            (UFDouble) this.valuemap.get(PreOrderBVO.NARRNUM));
    return narrnum;
  }

  /**
   * 获取主数量
   * 
   * @return 主数量
   */
  public UFDouble getNnum() {
    return (UFDouble) this.valuemap.get(PreOrderBVO.NNUM);
  }

  /**
   * 获取单据号
   * 
   * @return 单据号
   */
  public String getVbillcode() {
    return (String) this.valuemap.get(PreOrderHVO.VBILLCODE);
  }

  /**
   * 获取自由辅助属性1
   * 
   * @return 自由辅助属性1
   */
  public String getVfree1() {
    return (String) this.valuemap.get(PreOrderBVO.VFREE1);
  }

  /**
   * 获取自由辅助属性10
   * 
   * @return 自由辅助属性10
   */
  public String getVfree10() {
    return (String) this.valuemap.get(PreOrderBVO.VFREE10);
  }

  /**
   * 获取自由辅助属性2
   * 
   * @return 自由辅助属性2
   */
  public String getVfree2() {
    return (String) this.valuemap.get(PreOrderBVO.VFREE2);
  }

  /**
   * 获取自由辅助属性3
   * 
   * @return 自由辅助属性3
   */
  public String getVfree3() {
    return (String) this.valuemap.get(PreOrderBVO.VFREE3);
  }

  /**
   * 获取自由辅助属性4
   * 
   * @return 自由辅助属性4
   */
  public String getVfree4() {
    return (String) this.valuemap.get(PreOrderBVO.VFREE4);
  }

  /**
   * 获取自由辅助属性5
   * 
   * @return 自由辅助属性5
   */
  public String getVfree5() {
    return (String) this.valuemap.get(PreOrderBVO.VFREE5);
  }

  /**
   * 获取自由辅助属性6
   * 
   * @return 自由辅助属性6
   */
  public String getVfree6() {
    return (String) this.valuemap.get(PreOrderBVO.VFREE6);
  }

  /**
   * 获取自由辅助属性7
   * 
   * @return 自由辅助属性7
   */
  public String getVfree7() {
    return (String) this.valuemap.get(PreOrderBVO.VFREE7);
  }

  /**
   * 获取自由辅助属性8
   * 
   * @return 自由辅助属性8
   */
  public String getVfree8() {
    return (String) this.valuemap.get(PreOrderBVO.VFREE8);
  }

  /**
   * 获取自由辅助属性9
   * 
   * @return 自由辅助属性9
   */
  public String getVfree9() {
    return (String) this.valuemap.get(PreOrderBVO.VFREE9);
  }

  /**
   * 
   * 
   * @param key
   * @param value
   */
  public void setAttributeValue(String key, Object value) {
    this.valuemap.put(key, value);
  }

  // /**
  // * 设置物料最新版本
  // *
  // * @param cmaterialid 物料最新版本
  // */
  // public void setCmaterialid(String cmaterialid) {
  // this.setAttributeValue(PreOrderBVO.CMATERIALID, cmaterialid);
  // }
  //
  // /**
  // * 设置物料编码
  // *
  // * @param cmaterialvid 物料编码
  // */
  // public void setCmaterialvid(String cmaterialvid) {
  // this.setAttributeValue(PreOrderBVO.CMATERIALVID, cmaterialvid);
  // }
  //
  // public void setCrowno(String crowno) {
  // this.setAttributeValue(PreOrderBVO.CROWNO, crowno);
  // }
  //
  // /**
  // * 设置销售订单附表
  // *
  // * @param cPreOrderbid 销售订单附表
  // */
  // public void setCPreOrderbid(String cPreOrderbid) {
  // this.setAttributeValue(PreOrderBVO.CPreOrderBID, cPreOrderbid);
  // }
  //
  // /**
  // * 设置销售订单主表_主键
  // *
  // * @param cPreOrderid 销售订单主表_主键
  // */
  // public void setCPreOrderid(String cPreOrderid) {
  // this.setAttributeValue(PreOrderBVO.CPreOrderID, cPreOrderid);
  // }
  //
  // /**
  // * 设置发货库存组织最新版本
  // *
  // * @param csendstockorgid 发货库存组织最新版本
  // */
  // public void setCsendstockorgid(String csendstockorgid) {
  // this.setAttributeValue(PreOrderBVO.CSENDSTOCKORGID, csendstockorgid);
  // }
  //
  // /**
  // * 设置发货库存组织
  // *
  // * @param csendstockorgvid 发货库存组织
  // */
  // public void setCsendstockorgvid(String csendstockorgvid) {
  // this.setAttributeValue(PreOrderBVO.CSENDSTOCKORGVID, csendstockorgvid);
  // }
  //
  // /**
  // * 设置主单位
  // *
  // * @param cunitid 主单位
  // */
  // public void setCunitid(String cunitid) {
  // this.setAttributeValue(PreOrderBVO.CUNITID, cunitid);
  // }
  //
  // /**
  // * 设置发货日期
  // *
  // * @param dsenddate 发货日期
  // */
  // public void setDsenddate(UFDate dsenddate) {
  // this.setAttributeValue(PreOrderBVO.DSENDDATE, dsenddate);
  // }
  //
  // /**
  // * 设置单据状态
  // *
  // * @param fstatusflag 单据状态
  // * @see BillStatus
  // */
  // public void setFstatusflag(Integer fstatusflag) {
  // this.setAttributeValue(PreOrderHVO.FSTATUSFLAG, fstatusflag);
  // }
  //
  // /**
  // * 设置累计安排请购单数量
  // *
  // * @param narrangepoappnum 累计安排请购单数量
  // */
  // public void setNarrangepoappnum(UFDouble narrangepoappnum) {
  // this.setAttributeValue(PreOrderBVO.NARRANGEPOAPPNUM, narrangepoappnum);
  // }
  //
  // /**
  // * 设置累计安排采购订单数量
  // *
  // * @param narrangeponum 累计安排采购订单数量
  // */
  // public void setNarrangeponum(UFDouble narrangeponum) {
  // this.setAttributeValue(PreOrderBVO.NARRANGEPONUM, narrangeponum);
  // }
  //
  // /**
  // * 设置累计安排委外订单数量
  // *
  // * @param narrangescornum 累计安排委外订单数量
  // */
  // public void setNarrangescornum(UFDouble narrangescornum) {
  // this.setAttributeValue(PreOrderBVO.NARRANGESCORNUM, narrangescornum);
  // }
  //
  // /**
  // * 设置累计安排调拨申请数量
  // *
  // * @param narrangetoappnum 累计安排调拨申请数量
  // */
  // public void setNarrangetoappnum(UFDouble narrangetoappnum) {
  // this.setAttributeValue(PreOrderBVO.NARRANGETOAPPNUM, narrangetoappnum);
  // }
  //
  // /**
  // * 设置累计安排调拨订单数量
  // *
  // * @param narrangetoornum 累计安排调拨订单数量
  // */
  // public void setNarrangetoornum(UFDouble narrangetoornum) {
  // this.setAttributeValue(PreOrderBVO.NARRANGETOORNUM, narrangetoornum);
  // }
  //
  // /**
  // * 设置主数量
  // *
  // * @param nnum 主数量
  // */
  // public void setNnum(UFDouble nnum) {
  // this.setAttributeValue(PreOrderBVO.NNUM, nnum);
  // }
  //
  // /**
  // * 设置预留数量
  // *
  // * @param nreqrsnum 预留数量
  // */
  // public void setNreqrsnum(UFDouble nreqrsnum) {
  // this.setAttributeValue(PreOrderBVO.NREQRSNUM, nreqrsnum);
  // }
  //
  // /**
  // * 设置累计出库数量
  // *
  // * @param ntotaloutnum 累计出库数量
  // */
  // public void setNtotaloutnum(UFDouble ntotaloutnum) {
  // this.setAttributeValue(PreOrderBVO.NTOTALOUTNUM, ntotaloutnum);
  // }
  //
  // public void setVbillcode(String vbillcode) {
  // this.setAttributeValue(PreOrderHVO.VBILLCODE, vbillcode);
  // }
  //
  // /**
  // * 设置自由辅助属性1
  // *
  // * @param vfree1 自由辅助属性1
  // */
  // public void setVfree1(String vfree1) {
  // this.setAttributeValue(PreOrderBVO.VFREE1, vfree1);
  // }
  //
  // /**
  // * 设置自由辅助属性10
  // *
  // * @param vfree10 自由辅助属性10
  // */
  // public void setVfree10(String vfree10) {
  // this.setAttributeValue(PreOrderBVO.VFREE10, vfree10);
  // }
  //
  // /**
  // * 设置自由辅助属性2
  // *
  // * @param vfree2 自由辅助属性2
  // */
  // public void setVfree2(String vfree2) {
  // this.setAttributeValue(PreOrderBVO.VFREE2, vfree2);
  // }
  //
  // /**
  // * 设置自由辅助属性3
  // *
  // * @param vfree3 自由辅助属性3
  // */
  // public void setVfree3(String vfree3) {
  // this.setAttributeValue(PreOrderBVO.VFREE3, vfree3);
  // }
  //
  // /**
  // * 设置自由辅助属性4
  // *
  // * @param vfree4 自由辅助属性4
  // */
  // public void setVfree4(String vfree4) {
  // this.setAttributeValue(PreOrderBVO.VFREE4, vfree4);
  // }
  //
  // /**
  // * 设置自由辅助属性5
  // *
  // * @param vfree5 自由辅助属性5
  // */
  // public void setVfree5(String vfree5) {
  // this.setAttributeValue(PreOrderBVO.VFREE5, vfree5);
  // }
  //
  // /**
  // * 设置自由辅助属性6
  // *
  // * @param vfree6 自由辅助属性6
  // */
  // public void setVfree6(String vfree6) {
  // this.setAttributeValue(PreOrderBVO.VFREE6, vfree6);
  // }
  //
  // /**
  // * 设置自由辅助属性7
  // *
  // * @param vfree7 自由辅助属性7
  // */
  // public void setVfree7(String vfree7) {
  // this.setAttributeValue(PreOrderBVO.VFREE7, vfree7);
  // }
  //
  // /**
  // * 设置自由辅助属性8
  // *
  // * @param vfree8 自由辅助属性8
  // */
  // public void setVfree8(String vfree8) {
  // this.setAttributeValue(PreOrderBVO.VFREE8, vfree8);
  // }
  //
  // /**
  // * 设置自由辅助属性9
  // *
  // * @param vfree9 自由辅助属性9
  // */
  // public void setVfree9(String vfree9) {
  // this.setAttributeValue(PreOrderBVO.VFREE9, vfree9);
  // }

}
