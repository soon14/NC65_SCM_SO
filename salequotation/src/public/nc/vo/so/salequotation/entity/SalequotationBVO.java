package nc.vo.so.salequotation.entity;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

/**
 * <b> 在此处简要描述此类的功能 </b>
 * <p>
 * 在此处添加此类的描述信息
 * </p>
 * 创建日期:2009-10-17 12:34:25
 * 
 * @author
 * @version NCPrj ??
 */
/**
 * 
 * @since 6.1
 * @version 2013-09-24 15:42:19
 * @author zhangyfr
 */
/**
 * 
 * @since 6.1
 * @version 2013-09-24 15:42:37
 * @author zhangyfr
 */
public class SalequotationBVO extends SuperVO {

  /**
   * 客户物料编码
   */
  public static final String CCUSTMATERIALID = "ccustmaterialid";

  /**
   * 设置客户物料编码
   * 
   * @param ccustmaterialid
   * 
   */
  public void setCcustmaterialid(String ccustmaterialid) {
    this.setAttributeValue(SalequotationBVO.CCUSTMATERIALID, ccustmaterialid);
  }

  /**
   * 获取客户物料编码
   * 
   * @return 客户物料编码
   */
  public String getCcustmaterialid() {
    return (String) this.getAttributeValue(SalequotationBVO.CCUSTMATERIALID);
  }

  /**
   * 赠品
   */
  public static final String BLARGESSFLAG = "blargessflag";

  /**
   * 三角贸易
   */
  public static final String BTRIATRADEFLAG = "btriatradeflag";

  /**
   * 单位
   */
  public static final String CASTUNITID = "castunitid";

  /**
   * 报价单位
   */
  public static final String CQTUNITID = "cqtunitid";

  /**
   * 收货国家/地区
   */
  public static final String CRECECOUNTRYID = "crececountryid";

  /**
   * 行号
   */
  public static final String CROWNO = "crowno";

  /**
   * 发货国家/地区
   */
  public static final String CSENDCOUNTRYID = "csendcountryid";

  /**
   * 税码
   */
  public static final String CTAXCODEID = "ctaxcodeid";

  /**
   * 报税国家/地区
   */
  public static final String CTAXCOUNTRYID = "ctaxcountryid";

  /**
   * dr
   */
  public static final String DR = "dr";

  /**
   * 购销类型
   */
  public static final String FBUYSELLFLAG = "fbuysellflag";

  /**
   * 扣税类别
   */
  public static final String FTAXTYPEFLAG = "ftaxtypeflag";

  /** SalequotationBVO元数据路径(元数据主表上的子表聚合名称) */
  public static final String METAPATH = "salequotationdetail.";

  /**
   * 数量
   */
  public static final String NASSISTNUM = "nassistnum";

  /**
   * 换算率
   */
  public static final String NCHANGERATE = "nchangerate";

  /**
   * 累计生成合同主数量
   */
  public static final String NCONTRACTNUM = "ncontractnum";

  /**
   * 整单折扣(%)
   */
  public static final String NDISCOUNTRATE = "ndiscountrate";

  /**
   * 单品折扣(%)
   */
  public static final String NITEMDISCOUNTRATE = "nitemdiscountrate";

  /**
   * 主数量
   */
  public static final String NNUM = "nnum";

  /**
   * 累计生成订单主数量
   */
  public static final String NORDERNUM = "nordernum";

  /**
   * 折扣额
   */
  public static final String NORIGDISCOUNT = "norigdiscount";

  /**
   * 无税金额
   */
  public static final String NORIGMNY = "norigmny";

  /**
   * 主无税净价
   */
  public static final String NORIGNETPRICE = "norignetprice";

  /**
   * 主无税单价
   */
  public static final String NORIGPRICE = "norigprice";

  /**
   * 价税合计
   */
  public static final String NORIGTAXMNY = "norigtaxmny";

  /**
   * 主含税净价
   */
  public static final String NORIGTAXNETPRICE = "norigtaxnetprice";

  /**
   * 主含税单价
   */
  public static final String NORIGTAXPRICE = "norigtaxprice";

  /**
   * 报价换算率
   */
  public static final String NQTCHANGERATE = "nqtchangerate";

  /**
   * 报价数量
   */
  public static final String NQTNUM = "nqtnum";

  /**
   * 无税净价
   */
  public static final String NQTORIGNETPRICE = "nqtorignetprice";

  /**
   * 无税单价
   */
  public static final String NQTORIGPRICE = "nqtorigprice";

  /**
   * 含税净价
   */
  public static final String NQTORIGTAXNETPRC = "nqtorigtaxnetprc";

  /**
   * 含税单价
   */
  public static final String NQTORIGTAXPRICE = "nqtorigtaxprice";

  /**
   * 税率(%)
   */
  public static final String NTAXRATE = "ntaxrate";

  /**
   * 收货地区
   */
  public static final String PK_AREACL = "pk_areacl";

  /**
   * 集团
   */
  public static final String PK_GROUP = "pk_group";

  /**
   * 物料最新版本
   */
  public static final String PK_MATERIAL = "pk_material";

  /**
   * 物料编码
   */
  public static final String PK_MATERIAL_V = "pk_material_v";

  /**
   * 销售组织
   */
  public static final String PK_ORG = "pk_org";

  /**
   * 销售组织版本信息
   */
  public static final String PK_ORG_V = "pk_org_v";

  /**
   * 定价策略
   */
  public static final String PK_PRICEPOLICY = "pk_pricepolicy";

  /**
   * 价格项
   */
  public static final String PK_PRICETYPE = "pk_pricetype";

  /**
   * 生产厂商
   */
  public static final String PK_PRODUCTOR = "pk_productor";

  /**
   * 项目
   */
  public static final String PK_PROJECT = "pk_project";

  /**
   * 质量等级
   */
  public static final String PK_QUALITYLEVEL = "pk_qualitylevel";

  /**
   * 报价单表头_主键
   */
  public static final String PK_SALEQUOTATION = "pk_salequotation";

  /**
   * 销售报价单子表主键
   */
  public static final String PK_SALEQUOTATION_B = "pk_salequotation_b";

  /**
   * 供应商
   */
  public static final String PK_SUPPLIER = "pk_supplier";

  /**
   * 价目表
   */
  public static final String PK_TARIFFDEF = "pk_tariffdef";

  /**
   * 主单位
   */
  public static final String PK_UNIT = "pk_unit";

  private static final long serialVersionUID = 567895286598696462L;

  /**
   * 时间戳
   */
  public static final String TS = "ts";

  /**
   * 自定义项1
   */
  public static final String VBDEF1 = "vbdef1";

  /**
   * 自定义项10
   */
  public static final String VBDEF10 = "vbdef10";

  /**
   * 自定义项11
   */
  public static final String VBDEF11 = "vbdef11";

  /**
   * 自定义项12
   */
  public static final String VBDEF12 = "vbdef12";

  /**
   * 自定义项13
   */
  public static final String VBDEF13 = "vbdef13";

  /**
   * 自定义项14
   */
  public static final String VBDEF14 = "vbdef14";

  /**
   * 自定义项15
   */
  public static final String VBDEF15 = "vbdef15";

  /**
   * 自定义项16
   */
  public static final String VBDEF16 = "vbdef16";

  /**
   * 自定义项17
   */
  public static final String VBDEF17 = "vbdef17";

  /**
   * 自定义项18
   */
  public static final String VBDEF18 = "vbdef18";

  /**
   * 自定义项19
   */
  public static final String VBDEF19 = "vbdef19";

  /**
   * 自定义项2
   */
  public static final String VBDEF2 = "vbdef2";

  /**
   * 自定义项20
   */
  public static final String VBDEF20 = "vbdef20";

  /**
   * 自定义项3
   */
  public static final String VBDEF3 = "vbdef3";

  /**
   * 自定义项 4
   */
  public static final String VBDEF4 = "vbdef4";

  /**
   * 自定义项5
   */
  public static final String VBDEF5 = "vbdef5";

  /**
   * 自定义项6
   */
  public static final String VBDEF6 = "vbdef6";

  /**
   * 自定义项7
   */
  public static final String VBDEF7 = "vbdef7";

  /**
   * 自定义项8
   */
  public static final String VBDEF8 = "vbdef8";

  /**
   * 自定义项9
   */
  public static final String VBDEF9 = "vbdef9";

  /**
   * 备注
   */
  public static final String VBNOTE = "vbnote";

  /**
   * 自由辅助属性1
   */
  public static final String VFREE1 = "vfree1";

  /**
   * 自由辅助属性10
   */
  public static final String VFREE10 = "vfree10";

  /**
   * 自由辅助属性2
   */
  public static final String VFREE2 = "vfree2";

  /**
   * 自由辅助属性3
   */
  public static final String VFREE3 = "vfree3";

  /**
   * 自由辅助属性4
   */
  public static final String VFREE4 = "vfree4";

  /**
   * 自由辅助属性5
   */
  public static final String VFREE5 = "vfree5";

  /**
   * 自由辅助属性6
   */
  public static final String VFREE6 = "vfree6";

  /**
   * 自由辅助属性7
   */
  public static final String VFREE7 = "vfree7";

  /**
   * 自由辅助属性8
   */
  public static final String VFREE8 = "vfree8";

  /**
   * 自由辅助属性9
   */
  public static final String VFREE9 = "vfree9";

  /**
   * 来源单据附表
   */
  public static final String CSRCBID = "csrcbid";

  /**
   * 来源单据主表
   */
  public static final String CSRCID = "csrcid";

  /**
   * 来源单据号
   */
  public static final String VSRCCODE = "vsrccode";

  /**
   * 来源单据行号
   */
  public static final String VSRCROWNO = "vsrcrowno";

  /**
   * 来源交易类型
   */
  public static final String VSRCTRANTYPE = "vsrctrantype";

  /**
   * 来源单据类型
   */
  public static final String VSRCTYPE = "vsrctype";

  /**
   * 源头单据子表
   */
  public static final String CFIRSTBID = "cfirstbid";

  /**
   * 源头单据主表
   */
  public static final String CFIRSTID = "cfirstid";

  /**
   * 源头单据号
   */
  public static final String VFIRSTCODE = "vfirstcode";

  /**
   * 源头单据行号
   */
  public static final String VFIRSTROWNO = "vfirstrowno";

  /**
   * 源头交易类型
   */
  public static final String VFIRSTTRANTYPE = "vfirsttrantype";

  /**
   * 源头单据类型
   */
  public static final String VFIRSTTYPE = "vfirsttype";

  /**
   * srcbts（不允许编辑）
   */
  public static final String SRCBTS = "srcbts";

  /**
   * srcts（不允许编辑）
   */
  public static final String SRCTS = "srcts";

  // 价格组成
  public static final String VPRICEDETAIL = "vpricedetail";

  /**
   * 按照默认方式创建构造子.
   * 
   * 创建日期:2009-10-17 12:34:25
   */
  public SalequotationBVO() {
    super();
  }

  public UFBoolean getBlargessflag() {
    return (UFBoolean) this.getAttributeValue(SalequotationBVO.BLARGESSFLAG);
  }

  /**
   * 获取三角贸易
   * 
   * @return 三角贸易
   */
  public UFBoolean getBtriatradeflag() {
    return (UFBoolean) this.getAttributeValue(SalequotationBVO.BTRIATRADEFLAG);
  }

  public String getCastunitid() {
    return (String) this.getAttributeValue(SalequotationBVO.CASTUNITID);
  }

  public String getCqtunitid() {
    return (String) this.getAttributeValue(SalequotationBVO.CQTUNITID);
  }

  /**
   * 获取收货国家/地区
   * 
   * @return 收获国家/地区
   */
  public String getCrececountryid() {
    return (String) this.getAttributeValue(SalequotationBVO.CRECECOUNTRYID);
  }

  public String getCrowno() {
    return (String) this.getAttributeValue(SalequotationBVO.CROWNO);
  }

  /**
   * 获取发货国家/地区
   * 
   * @return 发货国家/地区
   */
  public String getCsendcountryid() {
    return (String) this.getAttributeValue(SalequotationBVO.CSENDCOUNTRYID);
  }

  /**
   * 获取税码
   * 
   * @return 税码
   */
  public String getCtaxcodeid() {
    return (String) this.getAttributeValue(SalequotationBVO.CTAXCODEID);
  }

  /**
   * 获取报税国家和地区
   * 
   * @return 报税国家/地区
   */
  public String getCtaxcountryid() {
    return (String) this.getAttributeValue(SalequotationBVO.CTAXCOUNTRYID);
  }

  public Integer getDr() {
    return (Integer) this.getAttributeValue(SalequotationBVO.DR);
  }

  /**
   * 获取购销类型
   * 
   * @return 购销类型
   */
  public Integer getFbuysellflag() {
    return (Integer) this.getAttributeValue(SalequotationBVO.FBUYSELLFLAG);
  }

  /**
   * 获取报税类别
   * 
   * @return 报税类别
   */
  public Integer getFtaxtypeflag() {
    return (Integer) this.getAttributeValue(SalequotationBVO.FTAXTYPEFLAG);
  }

  @Override
  public IVOMeta getMetaData() {
    IVOMeta meta = VOMetaFactory.getInstance().getVOMeta("so.SalequotationBVO");
    return meta;
  }

  public UFDouble getNassistnum() {
    return (UFDouble) this.getAttributeValue(SalequotationBVO.NASSISTNUM);
  }

  public String getNchangerate() {
    return (String) this.getAttributeValue(SalequotationBVO.NCHANGERATE);
  }

  public UFDouble getNcontractnum() {
    return (UFDouble) this.getAttributeValue(SalequotationBVO.NCONTRACTNUM);
  }

  // /**
  // * 属性bcloseflag的Getter方法. 创建日期:2009-10-17 12:34:25
  // *
  // * @return nc.vo.pub.lang.UFBoolean
  // */
  // public nc.vo.pub.lang.UFBoolean getBcloseflag() {
  // return (UFBoolean) this.getAttributeValue("bcloseflag");
  // }

  public UFDouble getNitemdiscountrate() {
    return (UFDouble) this
        .getAttributeValue(SalequotationBVO.NITEMDISCOUNTRATE);
  }

  public UFDouble getNnum() {
    return (UFDouble) this.getAttributeValue(SalequotationBVO.NNUM);
  }

  public UFDouble getNordernum() {
    return (UFDouble) this.getAttributeValue(SalequotationBVO.NORDERNUM);
  }

  public UFDouble getNdiscountrate() {
    return (UFDouble) this.getAttributeValue(SalequotationBVO.NDISCOUNTRATE);
  }

  public UFDouble getNorigdiscount() {
    return (UFDouble) this.getAttributeValue(SalequotationBVO.NORIGDISCOUNT);
  }

  public UFDouble getNorigmny() {
    return (UFDouble) this.getAttributeValue(SalequotationBVO.NORIGMNY);
  }

  // /**
  // * 属性fhslcaltypeflag的Getter方法. 创建日期:2009-10-17 12:34:25
  // *
  // * @return java.lang.Integer
  // */
  // public Integer getFhslcaltypeflag() {
  // return (Integer) this.getAttributeValue("fhslcaltypeflag");
  // }

  public UFDouble getNorignetprice() {
    return (UFDouble) this.getAttributeValue(SalequotationBVO.NORIGNETPRICE);
  }

  public UFDouble getNorigprice() {
    return (UFDouble) this.getAttributeValue(SalequotationBVO.NORIGPRICE);
  }

  public UFDouble getNorigtaxmny() {
    return (UFDouble) this.getAttributeValue(SalequotationBVO.NORIGTAXMNY);
  }

  public UFDouble getNorigtaxnetprice() {
    return (UFDouble) this.getAttributeValue(SalequotationBVO.NORIGTAXNETPRICE);
  }

  // /**
  // * 属性ndiscount的Getter方法. 创建日期:2009-10-17 12:34:25
  // *
  // * @return java.lang.String
  // */
  // public nc.vo.pub.lang.UFDouble getNdiscount() {
  // return (nc.vo.pub.lang.UFDouble) this.getAttributeValue("ndiscount");
  // }

  public UFDouble getNorigtaxprice() {
    return (UFDouble) this.getAttributeValue(SalequotationBVO.NORIGTAXPRICE);
  }

  public String getNqtchangerate() {
    return (String) this.getAttributeValue(SalequotationBVO.NQTCHANGERATE);
  }

  // /**
  // * 属性nmny的Getter方法. 创建日期:2009-10-17 12:34:25
  // *
  // * @return java.lang.String
  // */
  // public nc.vo.pub.lang.UFDouble getNmny() {
  // return (nc.vo.pub.lang.UFDouble) this.getAttributeValue("nmny");
  // }

  // /**
  // * 属性nnetprice的Getter方法. 创建日期:2009-10-17 12:34:25
  // *
  // * @return nc.vo.pub.lang.UFDouble
  // */
  // public nc.vo.pub.lang.UFDouble getNnetprice() {
  // return (UFDouble) this.getAttributeValue("nnetprice");
  // }

  public UFDouble getNqtnum() {
    return (UFDouble) this.getAttributeValue(SalequotationBVO.NQTNUM);
  }

  public UFDouble getNqtorignetprice() {
    return (UFDouble) this.getAttributeValue(SalequotationBVO.NQTORIGNETPRICE);
  }

  public UFDouble getNqtorigprice() {
    return (UFDouble) this.getAttributeValue(SalequotationBVO.NQTORIGPRICE);
  }

  public UFDouble getNqtorigtaxnetprc() {
    return (UFDouble) this.getAttributeValue(SalequotationBVO.NQTORIGTAXNETPRC);
  }

  public UFDouble getNqtorigtaxprice() {
    return (UFDouble) this.getAttributeValue(SalequotationBVO.NQTORIGTAXPRICE);
  }

  public UFDouble getNtaxrate() {
    return (UFDouble) this.getAttributeValue(SalequotationBVO.NTAXRATE);
  }

  /**
   * <p>
   * 取得父VO主键字段.
   * <p>
   * 创建日期:2009-10-17 12:34:25
   * 
   * @return java.lang.String
   */
  @Override
  public java.lang.String getParentPKFieldName() {
    return "pk_salequotation";
  }

  public String getPk_areacl() {
    return (String) this.getAttributeValue(SalequotationBVO.PK_AREACL);
  }

  public String getPk_group() {
    return (String) this.getAttributeValue(SalequotationBVO.PK_GROUP);
  }

  // /**
  // * 属性nprice的Getter方法. 创建日期:2009-10-17 12:34:25
  // *
  // * @return nc.vo.pub.lang.UFDouble
  // */
  // public nc.vo.pub.lang.UFDouble getNprice() {
  // return (UFDouble) this.getAttributeValue("nprice");
  // }

  public String getPk_material() {
    return (String) this.getAttributeValue(SalequotationBVO.PK_MATERIAL);
  }

  // /**
  // * 属性nqtnetprice的Getter方法. 创建日期:2009-10-17 12:34:25
  // *
  // * @return nc.vo.pub.lang.UFDouble
  // */
  // public nc.vo.pub.lang.UFDouble getNqtnetprice() {
  // return (UFDouble) this.getAttributeValue("nqtnetprice");
  // }

  public String getPk_material_v() {
    return (String) this.getAttributeValue(SalequotationBVO.PK_MATERIAL_V);
  }

  public String getPk_org() {
    return (String) this.getAttributeValue(SalequotationBVO.PK_ORG);
  }

  public String getPk_org_v() {
    return (String) this.getAttributeValue(SalequotationBVO.PK_ORG_V);
  }

  public String getPk_pricepolicy() {
    return (String) this.getAttributeValue(SalequotationBVO.PK_PRICEPOLICY);
  }

  public String getPk_pricetype() {
    return (String) this.getAttributeValue(SalequotationBVO.PK_PRICETYPE);
  }

  // /**
  // * 属性nqtprice的Getter方法. 创建日期:2009-10-17 12:34:25
  // *
  // * @return nc.vo.pub.lang.UFDouble
  // */
  // public nc.vo.pub.lang.UFDouble getNqtprice() {
  // return (UFDouble) this.getAttributeValue("nqtprice");
  // }

  // /**
  // * 属性nqttaxnetprice的Getter方法. 创建日期:2009-10-17 12:34:25
  // *
  // * @return nc.vo.pub.lang.UFDouble
  // */
  // public nc.vo.pub.lang.UFDouble getNqttaxnetprice() {
  // return (UFDouble) this.getAttributeValue("nqttaxnetprice");
  // }

  // /**
  // * 属性nqttaxprice的Getter方法. 创建日期:2009-10-17 12:34:25
  // *
  // * @return nc.vo.pub.lang.UFDouble
  // */
  // public nc.vo.pub.lang.UFDouble getNqttaxprice() {
  // return (UFDouble) this.getAttributeValue("nqttaxprice");
  // }
  //
  // /**
  // * 属性ntax的Getter方法. 创建日期:2009-10-17 12:34:25
  // *
  // * @return java.lang.String
  // */
  // public nc.vo.pub.lang.UFDouble getNtax() {
  // return (nc.vo.pub.lang.UFDouble) this.getAttributeValue("ntax");
  // }
  //
  // /**
  // * 属性ntaxmny的Getter方法. 创建日期:2009-10-17 12:34:25
  // *
  // * @return java.lang.String
  // */
  // public nc.vo.pub.lang.UFDouble getNtaxmny() {
  // return (nc.vo.pub.lang.UFDouble) this.getAttributeValue("ntaxmny");
  // }
  //
  // /**
  // * 属性ntaxnetprice的Getter方法. 创建日期:2009-10-17 12:34:25
  // *
  // * @return nc.vo.pub.lang.UFDouble
  // */
  // public nc.vo.pub.lang.UFDouble getNtaxnetprice() {
  // return (UFDouble) this.getAttributeValue("ntaxnetprice");
  // }
  //
  // /**
  // * 属性ntaxprice的Getter方法. 创建日期:2009-10-17 12:34:25
  // *
  // * @return nc.vo.pub.lang.UFDouble
  // */
  // public nc.vo.pub.lang.UFDouble getNtaxprice() {
  // return (UFDouble) this.getAttributeValue("ntaxprice");
  // }

  public String getPk_productor() {
    return (String) this.getAttributeValue(SalequotationBVO.PK_PRODUCTOR);
  }

  public String getPk_project() {
    return (String) this.getAttributeValue(SalequotationBVO.PK_PROJECT);
  }

  public String getPk_qualitylevel() {
    return (String) this.getAttributeValue(SalequotationBVO.PK_QUALITYLEVEL);
  }

  public String getPk_salequotation() {
    return (String) this.getAttributeValue(SalequotationBVO.PK_SALEQUOTATION);
  }

  public String getPk_salequotation_b() {
    return (String) this.getAttributeValue(SalequotationBVO.PK_SALEQUOTATION_B);
  }

  public String getPk_supplier() {
    return (String) this.getAttributeValue(SalequotationBVO.PK_SUPPLIER);
  }

  public String getPk_tariffdef() {
    return (String) this.getAttributeValue(SalequotationBVO.PK_TARIFFDEF);
  }

  public String getPk_unit() {
    return (String) this.getAttributeValue(SalequotationBVO.PK_UNIT);
  }

  /**
   * <p>
   * 取得表主键.
   * <p>
   * 创建日期:2009-10-17 12:34:25
   * 
   * @return java.lang.String
   */
  @Override
  public java.lang.String getPKFieldName() {
    return "pk_salequotation_b";
  }

  /**
   * <p>
   * 返回表名称.
   * <p>
   * 创建日期:2009-10-17 12:34:25
   * 
   * @return java.lang.String
   */
  @Override
  public java.lang.String getTableName() {
    return "so_salequotation_b";
  }

  public UFDateTime getTs() {
    return (UFDateTime) this.getAttributeValue(SalequotationBVO.TS);
  }

  public String getVbdef1() {
    return (String) this.getAttributeValue(SalequotationBVO.VBDEF1);
  }

  public String getVbdef10() {
    return (String) this.getAttributeValue(SalequotationBVO.VBDEF10);
  }

  public String getVbdef11() {
    return (String) this.getAttributeValue(SalequotationBVO.VBDEF11);
  }

  public String getVbdef12() {
    return (String) this.getAttributeValue(SalequotationBVO.VBDEF12);
  }

  public String getVbdef13() {
    return (String) this.getAttributeValue(SalequotationBVO.VBDEF13);
  }

  public String getVbdef14() {
    return (String) this.getAttributeValue(SalequotationBVO.VBDEF14);
  }

  public String getVbdef15() {
    return (String) this.getAttributeValue(SalequotationBVO.VBDEF15);
  }

  public String getVbdef16() {
    return (String) this.getAttributeValue(SalequotationBVO.VBDEF16);
  }

  public String getVbdef17() {
    return (String) this.getAttributeValue(SalequotationBVO.VBDEF17);
  }

  public String getVbdef18() {
    return (String) this.getAttributeValue(SalequotationBVO.VBDEF18);
  }

  public String getVbdef19() {
    return (String) this.getAttributeValue(SalequotationBVO.VBDEF19);
  }

  public String getVbdef2() {
    return (String) this.getAttributeValue(SalequotationBVO.VBDEF2);
  }

  public String getVbdef20() {
    return (String) this.getAttributeValue(SalequotationBVO.VBDEF20);
  }

  public String getVbdef3() {
    return (String) this.getAttributeValue(SalequotationBVO.VBDEF3);
  }

  public String getVbdef4() {
    return (String) this.getAttributeValue(SalequotationBVO.VBDEF4);
  }

  public String getVbdef5() {
    return (String) this.getAttributeValue(SalequotationBVO.VBDEF5);
  }

  public String getVbdef6() {
    return (String) this.getAttributeValue(SalequotationBVO.VBDEF6);
  }

  public String getVbdef7() {
    return (String) this.getAttributeValue(SalequotationBVO.VBDEF7);
  }

  public String getVbdef8() {
    return (String) this.getAttributeValue(SalequotationBVO.VBDEF8);
  }

  public String getVbdef9() {
    return (String) this.getAttributeValue(SalequotationBVO.VBDEF9);
  }

  public String getVbnote() {
    return (String) this.getAttributeValue(SalequotationBVO.VBNOTE);
  }

  public String getVfree1() {
    return (String) this.getAttributeValue(SalequotationBVO.VFREE1);
  }

  public String getVfree10() {
    return (String) this.getAttributeValue(SalequotationBVO.VFREE10);
  }

  public String getVfree2() {
    return (String) this.getAttributeValue(SalequotationBVO.VFREE2);
  }

  public String getVfree3() {
    return (String) this.getAttributeValue(SalequotationBVO.VFREE3);
  }

  public String getVfree4() {
    return (String) this.getAttributeValue(SalequotationBVO.VFREE4);
  }

  public String getVfree5() {
    return (String) this.getAttributeValue(SalequotationBVO.VFREE5);
  }

  public String getVfree6() {
    return (String) this.getAttributeValue(SalequotationBVO.VFREE6);
  }

  public String getVfree7() {
    return (String) this.getAttributeValue(SalequotationBVO.VFREE7);
  }

  public String getVfree8() {
    return (String) this.getAttributeValue(SalequotationBVO.VFREE8);
  }

  public String getVfree9() {
    return (String) this.getAttributeValue(SalequotationBVO.VFREE9);
  }

  public String getVpricedetail() {
    return (String) this.getAttributeValue(SalequotationBVO.VPRICEDETAIL);
  }

  /**
   * 获取源头单据子表
   * 
   * @return 源头单据子表
   */
  public String getCfirstbid() {
    return (String) this.getAttributeValue(SalequotationBVO.CFIRSTBID);
  }

  /**
   * 获取源头单据主表
   * 
   * @return 源头单据主表
   */
  public String getCfirstid() {
    return (String) this.getAttributeValue(SalequotationBVO.CFIRSTID);
  }

  /**
   * 获取源头单据号
   * 
   * @return 源头单据号
   */
  public String getVfirstcode() {
    return (String) this.getAttributeValue(SalequotationBVO.VFIRSTCODE);
  }

  /**
   * 获取源头单据行号
   * 
   * @return 源头单据行号
   */
  public String getVfirstrowno() {
    return (String) this.getAttributeValue(SalequotationBVO.VFIRSTROWNO);
  }

  /**
   * 获取源头交易类型
   * 
   * @return 源头交易类型
   */
  public String getVfirsttrantype() {
    return (String) this.getAttributeValue(SalequotationBVO.VFIRSTTRANTYPE);
  }

  /**
   * 获取源头单据类型
   * 
   * @return 源头单据类型
   */
  public String getVfirsttype() {
    return (String) this.getAttributeValue(SalequotationBVO.VFIRSTTYPE);
  }

  public void setBlargessflag(UFBoolean blargessflag) {
    this.setAttributeValue(SalequotationBVO.BLARGESSFLAG, blargessflag);
  }

  /**
   * 获取来源单据号
   * 
   * @return 来源单据号
   */
  public String getVsrccode() {
    return (String) this.getAttributeValue(SalequotationBVO.VSRCCODE);
  }

  /**
   * 获取来源单据行号
   * 
   * @return 来源单据行号
   */
  public String getVsrcrowno() {
    return (String) this.getAttributeValue(SalequotationBVO.VSRCROWNO);
  }

  /**
   * 获取来源交易类型
   * 
   * @return 来源交易类型
   */
  public String getVsrctrantype() {
    return (String) this.getAttributeValue(SalequotationBVO.VSRCTRANTYPE);
  }

  /**
   * 获取来源单据类型
   * 
   * @return 来源单据类型
   */
  public String getVsrctype() {
    return (String) this.getAttributeValue(SalequotationBVO.VSRCTYPE);
  }

  /**
   * 获取来源单据附表
   * 
   * @return 来源单据附表
   */
  public String getCsrcbid() {
    return (String) this.getAttributeValue(SalequotationBVO.CSRCBID);
  }

  /**
   * 获取来源单据主表
   * 
   * @return 来源单据主表
   */
  public String getCsrcid() {
    return (String) this.getAttributeValue(SalequotationBVO.CSRCID);
  }

  /**
   * 获取来源时间戳
   * 
   * @return 时间戳
   */
  public UFDateTime getSrcbts() {
    return (UFDateTime) this.getAttributeValue(SalequotationBVO.SRCBTS);
  }

  /**
   * 获取来源时间戳
   * 
   * @return 时间戳
   */
  public UFDateTime getSrcts() {
    return (UFDateTime) this.getAttributeValue(SalequotationBVO.SRCTS);
  }

  /**
   * 设置 三角贸易
   * 
   * @param btriatradeflag 三角贸易
   */
  public void setBtriatradeflag(UFBoolean btriatradeflag) {
    this.setAttributeValue(SalequotationBVO.BTRIATRADEFLAG, btriatradeflag);
  }

  public void setCastunitid(String castunitid) {
    this.setAttributeValue(SalequotationBVO.CASTUNITID, castunitid);
  }

  public void setCqtunitid(String cqtunitid) {
    this.setAttributeValue(SalequotationBVO.CQTUNITID, cqtunitid);
  }

  /**
   * 设置收货国家/地区
   * 
   * @param crececountryid 收货国家/地区
   */
  public void setCrececountryid(String crececountryid) {
    this.setAttributeValue(SalequotationBVO.CRECECOUNTRYID, crececountryid);
  }

  public void setCrowno(String crowno) {
    this.setAttributeValue(SalequotationBVO.CROWNO, crowno);
  }

  /**
   * 设置发货国家/地区
   * 
   * @param csendcountryid 发货国家/地区
   */
  public void setCsendcountryid(String csendcountryid) {
    this.setAttributeValue(SalequotationBVO.CSENDCOUNTRYID, csendcountryid);
  }

  /**
   * 设置税码
   * 
   * @param ctaxcodeid 税码
   */
  public void setCtaxcodeid(String ctaxcodeid) {
    this.setAttributeValue(SalequotationBVO.CTAXCODEID, ctaxcodeid);
  }

  /**
   * 设置报税国家/地区
   * 
   * @param ctaxcountryid 报税国家/地区
   */
  public void setCtaxcountryid(String ctaxcountryid) {
    this.setAttributeValue(SalequotationBVO.CTAXCOUNTRYID, ctaxcountryid);
  }

  // /**
  // * 属性bcloseflag的Setter方法. 创建日期:2009-10-17 12:34:25
  // *
  // * @param newBcloseflag
  // * nc.vo.pub.lang.UFBoolean
  // */
  // public void setBcloseflag(nc.vo.pub.lang.UFBoolean newBcloseflag) {
  // this.setAttributeValue("bcloseflag", newBcloseflag);
  // }

  public void setDr(Integer dr) {
    this.setAttributeValue(SalequotationBVO.DR, dr);
  }

  /**
   * 设置购销类型
   * 
   * @param fbuysellflag 购销类型
   */
  public void setFbuysellflag(Integer fbuysellflag) {
    this.setAttributeValue(SalequotationBVO.FBUYSELLFLAG, fbuysellflag);
  }

  /**
   * 设置扣税类别
   * 
   * @param ftaxtypeflag 扣税类别
   */
  public void setFtaxtypeflag(Integer ftaxtypeflag) {
    this.setAttributeValue(SalequotationBVO.FTAXTYPEFLAG, ftaxtypeflag);
  }

  public void setNassistnum(UFDouble nassistnum) {
    this.setAttributeValue(SalequotationBVO.NASSISTNUM, nassistnum);
  }

  // /**
  // * 属性fhslcaltypeflag的Setter方法. 创建日期:2009-10-17 12:34:25
  // *
  // * @param newFhslcaltypeflag
  // * java.lang.Integer
  // */
  // public void setFhslcaltypeflag(Integer newFhslcaltypeflag) {
  // this.setAttributeValue("fhslcaltypeflag", newFhslcaltypeflag);
  // }

  public void setNchangerate(String nchangerate) {
    this.setAttributeValue(SalequotationBVO.NCHANGERATE, nchangerate);
  }

  public void setNcontractnum(UFDouble ncontractnum) {
    this.setAttributeValue(SalequotationBVO.NCONTRACTNUM, ncontractnum);
  }

  public void setNdiscountrate(UFDouble ndiscountrate) {
    this.setAttributeValue(SalequotationBVO.NDISCOUNTRATE, ndiscountrate);
  }

  // /**
  // * 属性ndiscount的Setter方法. 创建日期:2009-10-17 12:34:25
  // *
  // * @param newNdiscount
  // * java.lang.String
  // */
  // public void setNdiscount(nc.vo.pub.lang.UFDouble newNdiscount) {
  // this.setAttributeValue("ndiscount", newNdiscount);
  // }

  public void setNitemdiscountrate(UFDouble nitemdiscountrate) {
    this.setAttributeValue(SalequotationBVO.NITEMDISCOUNTRATE,
        nitemdiscountrate);
  }

  public void setNnum(UFDouble nnum) {
    this.setAttributeValue(SalequotationBVO.NNUM, nnum);
  }

  // /**
  // * 属性nmny的Setter方法. 创建日期:2009-10-17 12:34:25
  // *
  // * @param newNmny
  // * java.lang.String
  // */
  // public void setNmny(nc.vo.pub.lang.UFDouble newNmny) {
  // this.setAttributeValue("nmny", newNmny);
  // }
  //
  // /**
  // * 属性nnetprice的Setter方法. 创建日期:2009-10-17 12:34:25
  // *
  // * @param newNnetprice
  // * nc.vo.pub.lang.UFDouble
  // */
  // public void setNnetprice(nc.vo.pub.lang.UFDouble newNnetprice) {
  // this.setAttributeValue("nnetprice", newNnetprice);
  // }

  public void setNordernum(UFDouble nordernum) {
    this.setAttributeValue(SalequotationBVO.NORDERNUM, nordernum);
  }

  public void setNorigdiscount(UFDouble norigdiscount) {
    this.setAttributeValue(SalequotationBVO.NORIGDISCOUNT, norigdiscount);
  }

  public void setNorigmny(UFDouble norigmny) {
    this.setAttributeValue(SalequotationBVO.NORIGMNY, norigmny);
  }

  public void setNorignetprice(UFDouble norignetprice) {
    this.setAttributeValue(SalequotationBVO.NORIGNETPRICE, norignetprice);
  }

  public void setNorigprice(UFDouble norigprice) {
    this.setAttributeValue(SalequotationBVO.NORIGPRICE, norigprice);
  }

  public void setNorigtaxmny(UFDouble norigtaxmny) {
    this.setAttributeValue(SalequotationBVO.NORIGTAXMNY, norigtaxmny);
  }

  public void setNorigtaxnetprice(UFDouble norigtaxnetprice) {
    this.setAttributeValue(SalequotationBVO.NORIGTAXNETPRICE, norigtaxnetprice);
  }

  public void setNorigtaxprice(UFDouble norigtaxprice) {
    this.setAttributeValue(SalequotationBVO.NORIGTAXPRICE, norigtaxprice);
  }

  public void setNqtchangerate(String nqtchangerate) {
    this.setAttributeValue(SalequotationBVO.NQTCHANGERATE, nqtchangerate);
  }

  public void setNqtnum(UFDouble nqtnum) {
    this.setAttributeValue(SalequotationBVO.NQTNUM, nqtnum);
  }

  // /**
  // * 属性nprice的Setter方法. 创建日期:2009-10-17 12:34:25
  // *
  // * @param newNprice
  // * nc.vo.pub.lang.UFDouble
  // */
  // public void setNprice(nc.vo.pub.lang.UFDouble newNprice) {
  // this.setAttributeValue("nprice", newNprice);
  // }

  public void setNqtorignetprice(UFDouble nqtorignetprice) {
    this.setAttributeValue(SalequotationBVO.NQTORIGNETPRICE, nqtorignetprice);
  }

  // /**
  // * 属性nqtnetprice的Setter方法. 创建日期:2009-10-17 12:34:25
  // *
  // * @param newNqtnetprice
  // * nc.vo.pub.lang.UFDouble
  // */
  // public void setNqtnetprice(nc.vo.pub.lang.UFDouble newNqtnetprice) {
  // this.setAttributeValue("nqtnetprice", newNqtnetprice);
  // }

  public void setNqtorigprice(UFDouble nqtorigprice) {
    this.setAttributeValue(SalequotationBVO.NQTORIGPRICE, nqtorigprice);
  }

  public void setNqtorigtaxnetprc(UFDouble nqtorigtaxnetprc) {
    this.setAttributeValue(SalequotationBVO.NQTORIGTAXNETPRC, nqtorigtaxnetprc);
  }

  public void setNqtorigtaxprice(UFDouble nqtorigtaxprice) {
    this.setAttributeValue(SalequotationBVO.NQTORIGTAXPRICE, nqtorigtaxprice);
  }

  public void setNtaxrate(UFDouble ntaxrate) {
    this.setAttributeValue(SalequotationBVO.NTAXRATE, ntaxrate);
  }

  // /**
  // * 属性nqtprice的Setter方法. 创建日期:2009-10-17 12:34:25
  // *
  // * @param newNqtprice
  // * nc.vo.pub.lang.UFDouble
  // */
  // public void setNqtprice(nc.vo.pub.lang.UFDouble newNqtprice) {
  // this.setAttributeValue("nqtprice", newNqtprice);
  // }
  //
  // /**
  // * 属性nqttaxnetprice的Setter方法. 创建日期:2009-10-17 12:34:25
  // *
  // * @param newNqttaxnetprice
  // * nc.vo.pub.lang.UFDouble
  // */
  // public void setNqttaxnetprice(nc.vo.pub.lang.UFDouble newNqttaxnetprice) {
  // this.setAttributeValue("nqttaxnetprice", newNqttaxnetprice);
  // }
  //
  // /**
  // * 属性nqttaxprice的Setter方法. 创建日期:2009-10-17 12:34:25
  // *
  // * @param newNqttaxprice
  // * nc.vo.pub.lang.UFDouble
  // */
  // public void setNqttaxprice(nc.vo.pub.lang.UFDouble newNqttaxprice) {
  // this.setAttributeValue("nqttaxprice", newNqttaxprice);
  // }
  //
  // /**
  // * 属性ntax的Setter方法. 创建日期:2009-10-17 12:34:25
  // *
  // * @param newNtax
  // * java.lang.String
  // */
  // public void setNtax(nc.vo.pub.lang.UFDouble newNtax) {
  // this.setAttributeValue("ntax", newNtax);
  // }
  //
  // /**
  // * 属性ntaxmny的Setter方法. 创建日期:2009-10-17 12:34:25
  // *
  // * @param newNtaxmny
  // * java.lang.String
  // */
  // public void setNtaxmny(nc.vo.pub.lang.UFDouble newNtaxmny) {
  // this.setAttributeValue("ntaxmny", newNtaxmny);
  // }
  //
  // /**
  // * 属性ntaxnetprice的Setter方法. 创建日期:2009-10-17 12:34:25
  // *
  // * @param newNtaxnetprice
  // * nc.vo.pub.lang.UFDouble
  // */
  // public void setNtaxnetprice(nc.vo.pub.lang.UFDouble newNtaxnetprice) {
  // this.setAttributeValue("ntaxnetprice", newNtaxnetprice);
  // }
  //
  // /**
  // * 属性ntaxprice的Setter方法. 创建日期:2009-10-17 12:34:25
  // *
  // * @param newNtaxprice
  // * nc.vo.pub.lang.UFDouble
  // */
  // public void setNtaxprice(nc.vo.pub.lang.UFDouble newNtaxprice) {
  // this.setAttributeValue("ntaxprice", newNtaxprice);
  // }

  public void setPk_areacl(String pk_areacl) {
    this.setAttributeValue(SalequotationBVO.PK_AREACL, pk_areacl);
  }

  public void setPk_group(String pk_group) {
    this.setAttributeValue(SalequotationBVO.PK_GROUP, pk_group);
  }

  public void setPk_material(String pk_material) {
    this.setAttributeValue(SalequotationBVO.PK_MATERIAL, pk_material);
  }

  public void setPk_material_v(String pk_material_v) {
    this.setAttributeValue(SalequotationBVO.PK_MATERIAL_V, pk_material_v);
  }

  public void setPk_org(String pk_org) {
    this.setAttributeValue(SalequotationBVO.PK_ORG, pk_org);
  }

  public void setPk_org_v(String pk_org_v) {
    this.setAttributeValue(SalequotationBVO.PK_ORG_V, pk_org_v);
  }

  public void setPk_pricepolicy(String pk_pricepolicy) {
    this.setAttributeValue(SalequotationBVO.PK_PRICEPOLICY, pk_pricepolicy);
  }

  public void setPk_pricetype(String pk_pricetype) {
    this.setAttributeValue(SalequotationBVO.PK_PRICETYPE, pk_pricetype);
  }

  public void setPk_productor(String pk_productor) {
    this.setAttributeValue(SalequotationBVO.PK_PRODUCTOR, pk_productor);
  }

  public void setPk_project(String pk_project) {
    this.setAttributeValue(SalequotationBVO.PK_PROJECT, pk_project);
  }

  public void setPk_qualitylevel(String pk_qualitylevel) {
    this.setAttributeValue(SalequotationBVO.PK_QUALITYLEVEL, pk_qualitylevel);
  }

  public void setPk_salequotation(String pk_salequotation) {
    this.setAttributeValue(SalequotationBVO.PK_SALEQUOTATION, pk_salequotation);
  }

  public void setPk_salequotation_b(String pk_salequotation_b) {
    this.setAttributeValue(SalequotationBVO.PK_SALEQUOTATION_B,
        pk_salequotation_b);
  }

  public void setPk_supplier(String pk_supplier) {
    this.setAttributeValue(SalequotationBVO.PK_SUPPLIER, pk_supplier);
  }

  public void setPk_tariffdef(String pk_tariffdef) {
    this.setAttributeValue(SalequotationBVO.PK_TARIFFDEF, pk_tariffdef);
  }

  public void setPk_unit(String pk_unit) {
    this.setAttributeValue(SalequotationBVO.PK_UNIT, pk_unit);
  }

  public void setTs(UFDateTime ts) {
    this.setAttributeValue(SalequotationBVO.TS, ts);
  }

  public void setVbdef1(String vbdef1) {
    this.setAttributeValue(SalequotationBVO.VBDEF1, vbdef1);
  }

  public void setVbdef10(String vbdef10) {
    this.setAttributeValue(SalequotationBVO.VBDEF10, vbdef10);
  }

  public void setVbdef11(String vbdef11) {
    this.setAttributeValue(SalequotationBVO.VBDEF11, vbdef11);
  }

  public void setVbdef12(String vbdef12) {
    this.setAttributeValue(SalequotationBVO.VBDEF12, vbdef12);
  }

  public void setVbdef13(String vbdef13) {
    this.setAttributeValue(SalequotationBVO.VBDEF13, vbdef13);
  }

  public void setVbdef14(String vbdef14) {
    this.setAttributeValue(SalequotationBVO.VBDEF14, vbdef14);
  }

  public void setVbdef15(String vbdef15) {
    this.setAttributeValue(SalequotationBVO.VBDEF15, vbdef15);
  }

  public void setVbdef16(String vbdef16) {
    this.setAttributeValue(SalequotationBVO.VBDEF16, vbdef16);
  }

  public void setVbdef17(String vbdef17) {
    this.setAttributeValue(SalequotationBVO.VBDEF17, vbdef17);
  }

  public void setVbdef18(String vbdef18) {
    this.setAttributeValue(SalequotationBVO.VBDEF18, vbdef18);
  }

  public void setVbdef19(String vbdef19) {
    this.setAttributeValue(SalequotationBVO.VBDEF19, vbdef19);
  }

  public void setVbdef2(String vbdef2) {
    this.setAttributeValue(SalequotationBVO.VBDEF2, vbdef2);
  }

  public void setVbdef20(String vbdef20) {
    this.setAttributeValue(SalequotationBVO.VBDEF20, vbdef20);
  }

  public void setVbdef3(String vbdef3) {
    this.setAttributeValue(SalequotationBVO.VBDEF3, vbdef3);
  }

  public void setVbdef4(String vbdef4) {
    this.setAttributeValue(SalequotationBVO.VBDEF4, vbdef4);
  }

  public void setVbdef5(String vbdef5) {
    this.setAttributeValue(SalequotationBVO.VBDEF5, vbdef5);
  }

  public void setVbdef6(String vbdef6) {
    this.setAttributeValue(SalequotationBVO.VBDEF6, vbdef6);
  }

  public void setVbdef7(String vbdef7) {
    this.setAttributeValue(SalequotationBVO.VBDEF7, vbdef7);
  }

  public void setVbdef8(String vbdef8) {
    this.setAttributeValue(SalequotationBVO.VBDEF8, vbdef8);
  }

  public void setVbdef9(String vbdef9) {
    this.setAttributeValue(SalequotationBVO.VBDEF9, vbdef9);
  }

  public void setVbnote(String vbnote) {
    this.setAttributeValue(SalequotationBVO.VBNOTE, vbnote);
  }

  public void setVfree1(String vfree1) {
    this.setAttributeValue(SalequotationBVO.VFREE1, vfree1);
  }

  public void setVfree10(String vfree10) {
    this.setAttributeValue(SalequotationBVO.VFREE10, vfree10);
  }

  public void setVfree2(String vfree2) {
    this.setAttributeValue(SalequotationBVO.VFREE2, vfree2);
  }

  public void setVfree3(String vfree3) {
    this.setAttributeValue(SalequotationBVO.VFREE3, vfree3);
  }

  public void setVfree4(String vfree4) {
    this.setAttributeValue(SalequotationBVO.VFREE4, vfree4);
  }

  public void setVfree5(String vfree5) {
    this.setAttributeValue(SalequotationBVO.VFREE5, vfree5);
  }

  public void setVfree6(String vfree6) {
    this.setAttributeValue(SalequotationBVO.VFREE6, vfree6);
  }

  public void setVfree7(String vfree7) {
    this.setAttributeValue(SalequotationBVO.VFREE7, vfree7);
  }

  public void setVfree8(String vfree8) {
    this.setAttributeValue(SalequotationBVO.VFREE8, vfree8);
  }

  public void setVfree9(String vfree9) {
    this.setAttributeValue(SalequotationBVO.VFREE9, vfree9);
  }

  public void setVpricedetail(String vpricedetail) {
    this.setAttributeValue(SalequotationBVO.VPRICEDETAIL, vpricedetail);
  }

  /**
   * 设置来源单据号
   * 
   * @param vsrccode 来源单据号
   */
  public void setVsrccode(String vsrccode) {
    this.setAttributeValue(SalequotationBVO.VSRCCODE, vsrccode);
  }

  /**
   * 设置来源单据行号
   * 
   * @param vsrcrowno 来源单据行号
   */
  public void setVsrcrowno(String vsrcrowno) {
    this.setAttributeValue(SalequotationBVO.VSRCROWNO, vsrcrowno);
  }

  /**
   * 设置来源交易类型
   * 
   * @param vsrctrantype 来源交易类型
   */
  public void setVsrctrantype(String vsrctrantype) {
    this.setAttributeValue(SalequotationBVO.VSRCTRANTYPE, vsrctrantype);
  }

  /**
   * 设置来源单据类型
   * 
   * @param vsrctype 来源单据类型
   */
  public void setVsrctype(String vsrctype) {
    this.setAttributeValue(SalequotationBVO.VSRCTYPE, vsrctype);
  }

  /**
   * 设置源头单据号
   * 
   * @param vfirstcode 源头单据号
   */
  public void setVfirstcode(String vfirstcode) {
    this.setAttributeValue(SalequotationBVO.VFIRSTCODE, vfirstcode);
  }

  /**
   * 设置源头单据行号
   * 
   * @param vfirstrowno 源头单据行号
   */
  public void setVfirstrowno(String vfirstrowno) {
    this.setAttributeValue(SalequotationBVO.VFIRSTROWNO, vfirstrowno);
  }

  /**
   * 设置源头交易类型
   * 
   * @param vfirsttrantype 源头交易类型
   */
  public void setVfirsttrantype(String vfirsttrantype) {
    this.setAttributeValue(SalequotationBVO.VFIRSTTRANTYPE, vfirsttrantype);
  }

  /**
   * 设置源头单据类型
   * 
   * @param vfirsttype 源头单据类型
   */
  public void setVfirsttype(String vfirsttype) {
    this.setAttributeValue(SalequotationBVO.VFIRSTTYPE, vfirsttype);
  }

  /**
   * 设置源头单据子表
   * 
   * @param cfirstbid 源头单据子表
   */
  public void setCfirstbid(String cfirstbid) {
    this.setAttributeValue(SalequotationBVO.CFIRSTBID, cfirstbid);
  }

  /**
   * 设置源头单据主表
   * 
   * @param cfirstid 源头单据主表
   */
  public void setCfirstid(String cfirstid) {
    this.setAttributeValue(SalequotationBVO.CFIRSTID, cfirstid);
  }

  /**
   * 设置来源单据附表
   * 
   * @param csrcbid 来源单据附表
   */
  public void setCsrcbid(String csrcbid) {
    this.setAttributeValue(SalequotationBVO.CSRCBID, csrcbid);
  }

  /**
   * 设置来源单据主表
   * 
   * @param csrcid 来源单据主表
   */
  public void setCsrcid(String csrcid) {
    this.setAttributeValue(SalequotationBVO.CSRCID, csrcid);
  }

  /**
   * 设置来源时间戳
   * 
   * @param ts 时间戳
   */
  public void setSrcbts(UFDateTime srcbts) {
    this.setAttributeValue(SalequotationBVO.SRCBTS, srcbts);
  }

  /**
   * 设置来源时间戳
   * 
   * @param ts 时间戳
   */
  public void setSrcts(UFDateTime srcts) {
    this.setAttributeValue(SalequotationBVO.SRCTS, srcts);
  }

}
