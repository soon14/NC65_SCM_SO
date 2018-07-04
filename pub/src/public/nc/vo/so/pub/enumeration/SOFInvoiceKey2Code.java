package nc.vo.so.pub.enumeration;

import nc.vo.so.entry.SaleInvoiceBVOCode;
import nc.vo.so.m32.entity.SaleInvoiceBVO;

public enum SOFInvoiceKey2Code {
  CCUSTMATERIALID(SaleInvoiceBVOCode.CCUSTMATERIALID,
      SaleInvoiceBVO.CCUSTMATERIALID/*客户物料码*/),
  CMARBASCALSSID(SaleInvoiceBVOCode.CMARBASCALSSID,
      SaleInvoiceBVO.CMARBASCALSSID/*物料基本分类*/),
  CMATERIALID(SaleInvoiceBVOCode.CMATERIALID, SaleInvoiceBVO.CMATERIALID/*物料最新版本*/),
  CMATERIALVID(SaleInvoiceBVOCode.CMATERIALVID, SaleInvoiceBVO.CMATERIALVID/*物料编码*/),
  CASTUNITID(SaleInvoiceBVOCode.CASTUNITID, SaleInvoiceBVO.CASTUNITID/*单位*/),
  CUNITID(SaleInvoiceBVOCode.CUNITID, SaleInvoiceBVO.CUNITID/*主单位*/),
  CQTUNITID(SaleInvoiceBVOCode.CQTUNITID, SaleInvoiceBVO.CQTUNITID/*报价单位*/),
  BDISCOUNTFLAG(SaleInvoiceBVOCode.BDISCOUNTFLAG, SaleInvoiceBVO.BDISCOUNTFLAG/*折扣类*/),
  BFREECUSTFLAG(SaleInvoiceBVOCode.BFREECUSTFLAG, SaleInvoiceBVO.BFREECUSTFLAG/*是否散户*/),
  BLABORFLAG(SaleInvoiceBVOCode.BLABORFLAG, SaleInvoiceBVO.BLABORFLAG/*服务类*/),
  BLARGESSFLAG(SaleInvoiceBVOCode.BLARGESSFLAG, SaleInvoiceBVO.BLARGESSFLAG/*赠品*/),
  CPRODLINEID(SaleInvoiceBVOCode.CPRODLINEID, SaleInvoiceBVO.CPRODLINEID/*产品线*/),
  PK_BATCHCODE(SaleInvoiceBVOCode.PK_BATCHCODE, SaleInvoiceBVO.PK_BATCHCODE/*批次档案*/),
  VBATCHCODE(SaleInvoiceBVOCode.VBATCHCODE, SaleInvoiceBVO.VBATCHCODE/*批次号*/),
  CCTMANAGEID(SaleInvoiceBVOCode.CCTMANAGEID, SaleInvoiceBVO.CCTMANAGEID/*合同号*/),
  CDEPTID(SaleInvoiceBVOCode.CDEPTID, SaleInvoiceBVO.CDEPTID/*销售部门最新版本*/),
  CDEPTVID(SaleInvoiceBVOCode.CDEPTVID, SaleInvoiceBVO.CDEPTVID/*销售部门*/),
  CEMPLOYEEID(SaleInvoiceBVOCode.CEMPLOYEEID, SaleInvoiceBVO.CEMPLOYEEID/*销售业务员*/),
  CTRANSPORTTYPEID(SaleInvoiceBVOCode.CTRANSPORTTYPEID,
      SaleInvoiceBVO.CTRANSPORTTYPEID/*运输方式*/),
  COPPOSESRCBID(SaleInvoiceBVOCode.COPPOSESRCBID, SaleInvoiceBVO.COPPOSESRCBID/*对冲来源子表*/),
  CORDERCUSTID(SaleInvoiceBVOCode.CORDERCUSTID, SaleInvoiceBVO.CORDERCUSTID/*订单客户*/),
  CFREECUSTID(SaleInvoiceBVOCode.CFREECUSTID, SaleInvoiceBVO.CFREECUSTID/*散户*/),
  CCOSTSUBJID(SaleInvoiceBVOCode.CCOSTSUBJID, SaleInvoiceBVO.CCOSTSUBJID/*收支项目*/),
  CPRODUCTORID(SaleInvoiceBVOCode.CPRODUCTORID, SaleInvoiceBVO.CPRODUCTORID/*生产厂商*/),
  CVENDORID(SaleInvoiceBVOCode.CVENDORID, SaleInvoiceBVO.CVENDORID/*供应商*/),
  CVMIVENDERID(SaleInvoiceBVOCode.CVMIVENDERID, SaleInvoiceBVO.CVMIVENDERID/*寄存供应商*/),
  CPROFITCENTERID(SaleInvoiceBVOCode.CPROFITCENTERID,
      SaleInvoiceBVO.CPROFITCENTERID/*利润中心最新版本*/),
  CPROFITCENTERVID(SaleInvoiceBVOCode.CPROFITCENTERVID,
      SaleInvoiceBVO.CPROFITCENTERVID/*利润中心*/),
  CPROJECTID(SaleInvoiceBVOCode.CPROJECTID, SaleInvoiceBVO.CPROJECTID/*项目*/),

  CRECEIVEADDRID(SaleInvoiceBVOCode.CRECEIVEADDRID,
      SaleInvoiceBVO.CRECEIVEADDRID/*收货地址*/),
  CRECEIVECUSTID(SaleInvoiceBVOCode.CRECEIVECUSTID,
      SaleInvoiceBVO.CRECEIVECUSTID/*收货客户*/),
  CSALEORGID(SaleInvoiceBVOCode.CSALEORGID, SaleInvoiceBVO.CSALEORGID/*销售组织最新版本*/),
  CSALEORGVID(SaleInvoiceBVOCode.CSALEORGVID, SaleInvoiceBVO.CSALEORGVID/*销售组织*/),
  CARORGID(SaleInvoiceBVOCode.CARORGID, SaleInvoiceBVO.CARORGID/*应收组织最新版本*/),
  CARORGVID(SaleInvoiceBVOCode.CARORGVID, SaleInvoiceBVO.CARORGVID/*应收组织*/),
  CSENDSTOCKORGID(SaleInvoiceBVOCode.CSENDSTOCKORGID,
      SaleInvoiceBVO.CSENDSTOCKORGID/*库存组织最新版本*/),
  CSENDSTOCKORGVID(SaleInvoiceBVOCode.CSENDSTOCKORGVID,
      SaleInvoiceBVO.CSENDSTOCKORGVID/*库存组织*/),
  CSENDSTORDOCID(SaleInvoiceBVOCode.CSENDSTORDOCID,
      SaleInvoiceBVO.CSENDSTORDOCID/*仓库*/),
  CFIRSTBID(SaleInvoiceBVOCode.CFIRSTBID, SaleInvoiceBVO.CFIRSTBID/*源头单据子表*/),
  CFIRSTID(SaleInvoiceBVOCode.CFIRSTID, SaleInvoiceBVO.CFIRSTID/*源头单据主表*/),
  CSRCBID(SaleInvoiceBVOCode.CSRCBID, SaleInvoiceBVO.CSRCBID/*来源单据子表*/),
  CSRCID(SaleInvoiceBVOCode.CSRCID, SaleInvoiceBVO.CSRCID/*来源单据主表*/),
  VSRCCODE(SaleInvoiceBVOCode.VSRCCODE, SaleInvoiceBVO.VSRCCODE/*来源单据号*/),
  VSRCROWNO(SaleInvoiceBVOCode.VSRCROWNO, SaleInvoiceBVO.VSRCROWNO/*来源单据行号*/),
  VSRCTRANTYPE(SaleInvoiceBVOCode.VSRCTRANTYPE, SaleInvoiceBVO.VSRCTRANTYPE/*来源交易类型*/),
  VSRCTYPE(SaleInvoiceBVOCode.VSRCTYPE, SaleInvoiceBVO.VSRCTYPE/*来源单据类型*/),
  CSUMID(SaleInvoiceBVOCode.CSUMID, SaleInvoiceBVO.CSUMID/*消耗汇总主键*/),

  NASTNUM(SaleInvoiceBVOCode.NASTNUM, SaleInvoiceBVO.NASTNUM/*数量*/),
  NBFORIGSUBMNY(SaleInvoiceBVOCode.NBFORIGSUBMNY, "nbforigsubmny"/*冲减前金额*/),
  // NCOSTMNY(SaleInvoiceBVOCode.NCOSTMNY, NCLangRes4VoTransl.getNCLangRes()
  // .getStrByID("4006004_0", "04006004-0118")/*成本金额*/),
  NDISCOUNT(SaleInvoiceBVOCode.NDISCOUNT, SaleInvoiceBVO.NDISCOUNT/*本币折扣额*/),
  NDISCOUNTRATE(SaleInvoiceBVOCode.NDISCOUNTRATE, SaleInvoiceBVO.NDISCOUNTRATE/*整单折扣*/),
  NGLOBALMNY(SaleInvoiceBVOCode.NGLOBALMNY, SaleInvoiceBVO.NGLOBALMNY/*全局本币无税金额*/),
  NGLOBALTAXMNY(SaleInvoiceBVOCode.NGLOBALTAXMNY, SaleInvoiceBVO.NGLOBALTAXMNY/*全局本币价税合计*/),
  NGROUPMNY(SaleInvoiceBVOCode.NGROUPMNY, SaleInvoiceBVO.NGROUPMNY/*集团本币无税金额*/),
  NGROUPTAXMNY(SaleInvoiceBVOCode.NGROUPTAXMNY, SaleInvoiceBVO.NGROUPTAXMNY/*集团本币价税合计*/),
  NINVOICEDISRATE(SaleInvoiceBVOCode.NINVOICEDISRATE,
      SaleInvoiceBVO.NINVOICEDISRATE/*发票折扣*/),
  NITEMDISCOUNTRATE(SaleInvoiceBVOCode.NITEMDISCOUNTRATE,
      SaleInvoiceBVO.NITEMDISCOUNTRATE/*单品折扣*/),

  NMNY(SaleInvoiceBVOCode.NMNY, SaleInvoiceBVO.NMNY/*本币无税金额*/),
  NNETPRICE(SaleInvoiceBVOCode.NNETPRICE, SaleInvoiceBVO.NNETPRICE/*主本币无税净价*/),
  NNUM(SaleInvoiceBVOCode.NNUM, SaleInvoiceBVO.NNUM/*主数量*/),
  NORIGDISCOUNT(SaleInvoiceBVOCode.NORIGDISCOUNT, SaleInvoiceBVO.NORIGDISCOUNT/*折扣额*/),
  NORIGMNY(SaleInvoiceBVOCode.NORIGMNY, SaleInvoiceBVO.NORIGMNY/*无税金额*/),
  NORIGNETPRICE(SaleInvoiceBVOCode.NORIGNETPRICE, SaleInvoiceBVO.NORIGNETPRICE/*主无税净价*/),
  NORIGPRICE(SaleInvoiceBVOCode.NORIGPRICE, SaleInvoiceBVO.NORIGPRICE/*主无税单价*/),
  NORIGSUBMNY(SaleInvoiceBVOCode.NORIGSUBMNY, SaleInvoiceBVO.NORIGSUBMNY/*费用冲抵金额*/),
  CTAXCODEID(SaleInvoiceBVOCode.CTAXCODEID, SaleInvoiceBVO.CTAXCODEID/*税码*/),
  FTAXTYPEFLAG(SaleInvoiceBVOCode.FTAXTYPEFLAG, SaleInvoiceBVO.FTAXTYPEFLAG/*扣税类别*/),

  NORIGTAXMNY(SaleInvoiceBVOCode.NORIGTAXMNY, SaleInvoiceBVO.NORIGTAXMNY/*价税合计*/),
  NORIGTAXNETPRICE(SaleInvoiceBVOCode.NORIGTAXNETPRICE,
      SaleInvoiceBVO.NORIGTAXNETPRICE/*主含税净价*/),
  NORIGTAXPRICE(SaleInvoiceBVOCode.NORIGTAXPRICE, SaleInvoiceBVO.NORIGTAXPRICE/*主含税单价*/),
  NPRICE(SaleInvoiceBVOCode.NPRICE, SaleInvoiceBVO.NPRICE/*主本币无税单价*/),

  NQTNETPRICE(SaleInvoiceBVOCode.NQTNETPRICE, SaleInvoiceBVO.NQTNETPRICE/*本币无税净价*/),
  NQTORIGNETPRICE(SaleInvoiceBVOCode.NQTORIGNETPRICE,
      SaleInvoiceBVO.NQTORIGNETPRICE/*无税净价*/),
  NQTORIGPRICE(SaleInvoiceBVOCode.NQTORIGPRICE, SaleInvoiceBVO.NQTORIGPRICE/*无税单价*/),
  NQTORIGTAXNETPRC(SaleInvoiceBVOCode.NQTORIGTAXNETPRC,
      SaleInvoiceBVO.NQTORIGTAXNETPRC/*含税净价*/),

  NQTORIGTAXPRICE(SaleInvoiceBVOCode.NQTORIGTAXPRICE,
      SaleInvoiceBVO.NQTORIGTAXPRICE/*含税单价*/),
  NQTPRICE(SaleInvoiceBVOCode.NQTPRICE, SaleInvoiceBVO.NQTPRICE/*本币无税单价*/),
  NQTTAXNETPRICE(SaleInvoiceBVOCode.NQTTAXNETPRICE,
      SaleInvoiceBVO.NQTTAXNETPRICE/*本币含税净价*/),
  NQTTAXPRICE(SaleInvoiceBVOCode.NQTTAXPRICE, SaleInvoiceBVO.NQTTAXPRICE/*本币含税单价*/),

  NQTUNITNUM(SaleInvoiceBVOCode.NQTUNITNUM, SaleInvoiceBVO.NQTUNITNUM/*报价数量*/),
  NTAX(SaleInvoiceBVOCode.NTAX, SaleInvoiceBVO.NTAX/*本币税额*/),
  NTAXMNY(SaleInvoiceBVOCode.NTAXMNY, SaleInvoiceBVO.NTAXMNY/*本币价税合计*/),
  NTAXNETPRICE(SaleInvoiceBVOCode.NTAXNETPRICE, SaleInvoiceBVO.NTAXNETPRICE/*主本币含税净价*/),

  NTAXPRICE(SaleInvoiceBVOCode.NTAXPRICE, SaleInvoiceBVO.NTAXPRICE/*主本币含税单价*/),
  NTAXRATE(SaleInvoiceBVOCode.NTAXRATE, SaleInvoiceBVO.NTAXRATE/*税率*/),

  VBDEF1(SaleInvoiceBVOCode.VBDEF1, SaleInvoiceBVO.VBDEF1/*自定义项1*/),
  VBDEF2(SaleInvoiceBVOCode.VBDEF2, SaleInvoiceBVO.VBDEF2/*自定义项2*/),
  VBDEF20(SaleInvoiceBVOCode.VBDEF20, SaleInvoiceBVO.VBDEF20/*自定义项20*/),
  VBDEF3(SaleInvoiceBVOCode.VBDEF3, SaleInvoiceBVO.VBDEF3/*自定义项3*/),
  VBDEF4(SaleInvoiceBVOCode.VBDEF4, SaleInvoiceBVO.VBDEF4/*自定义项4*/),
  VBDEF5(SaleInvoiceBVOCode.VBDEF5, SaleInvoiceBVO.VBDEF5/*自定义项5*/),
  VBDEF6(SaleInvoiceBVOCode.VBDEF6, SaleInvoiceBVO.VBDEF6/*自定义项6*/),
  VBDEF7(SaleInvoiceBVOCode.VBDEF7, SaleInvoiceBVO.VBDEF7/*自定义项7*/),
  VBDEF8(SaleInvoiceBVOCode.VBDEF8, SaleInvoiceBVO.VBDEF8/*自定义项8*/),
  VBDEF9(SaleInvoiceBVOCode.VBDEF9, SaleInvoiceBVO.VBDEF9/*自定义项9*/),
  VBDEF10(SaleInvoiceBVOCode.VBDEF10, SaleInvoiceBVO.VBDEF10/*自定义项10*/),
  VBDEF11(SaleInvoiceBVOCode.VBDEF11, SaleInvoiceBVO.VBDEF11/*自定义项11*/),
  VBDEF12(SaleInvoiceBVOCode.VBDEF12, SaleInvoiceBVO.VBDEF12/*自定义项12*/),

  VBDEF13(SaleInvoiceBVOCode.VBDEF13, SaleInvoiceBVO.VBDEF13/*自定义项13*/),
  VBDEF14(SaleInvoiceBVOCode.VBDEF14, SaleInvoiceBVO.VBDEF14/*自定义项14*/),
  VBDEF15(SaleInvoiceBVOCode.VBDEF15, SaleInvoiceBVO.VBDEF15/*自定义项15*/),
  VBDEF16(SaleInvoiceBVOCode.VBDEF16, SaleInvoiceBVO.VBDEF16/*自定义项16*/),
  VBDEF17(SaleInvoiceBVOCode.VBDEF17, SaleInvoiceBVO.VBDEF17/*自定义项17*/),
  VBDEF18(SaleInvoiceBVOCode.VBDEF18, SaleInvoiceBVO.VBDEF18/*自定义项18*/),
  VBDEF19(SaleInvoiceBVOCode.VBDEF19, SaleInvoiceBVO.VBDEF19/*自定义项19*/),

  VCHANGERATE(SaleInvoiceBVOCode.VCHANGERATE, SaleInvoiceBVO.VCHANGERATE/*换算率*/),
  VFIRSTCODE(SaleInvoiceBVOCode.VFIRSTCODE, SaleInvoiceBVO.VFIRSTCODE/*源头单据号*/),
  VFIRSTROWNO(SaleInvoiceBVOCode.VFIRSTROWNO, SaleInvoiceBVO.VFIRSTROWNO/*源头单据行号*/),
  VFIRSTTRANTYPE(SaleInvoiceBVOCode.VFIRSTTRANTYPE,
      SaleInvoiceBVO.VFIRSTTRANTYPE/*源头交易类型*/),
  VFIRSTTYPE(SaleInvoiceBVOCode.VFIRSTTYPE, SaleInvoiceBVO.VFIRSTTYPE/*源头单据类型*/),
  VFREE1(SaleInvoiceBVOCode.VFREE1, SaleInvoiceBVO.VFREE1/*自由辅助属性1*/),

  VFREE2(SaleInvoiceBVOCode.VFREE2, SaleInvoiceBVO.VFREE2/*自由辅助属性2*/),
  VFREE3(SaleInvoiceBVOCode.VFREE3, SaleInvoiceBVO.VFREE3/*自由辅助属性3*/),
  VFREE4(SaleInvoiceBVOCode.VFREE4, SaleInvoiceBVO.VFREE4/*自由辅助属性4*/),
  VFREE5(SaleInvoiceBVOCode.VFREE5, SaleInvoiceBVO.VFREE5/*自由辅助属性5*/),
  VFREE6(SaleInvoiceBVOCode.VFREE6, SaleInvoiceBVO.VFREE6/*自由辅助属性6*/),
  VFREE7(SaleInvoiceBVOCode.VFREE7, SaleInvoiceBVO.VFREE7/*自由辅助属性7*/),
  VFREE8(SaleInvoiceBVOCode.VFREE8, SaleInvoiceBVO.VFREE8/*自由辅助属性8*/),
  VFREE9(SaleInvoiceBVOCode.VFREE9, SaleInvoiceBVO.VFREE9/*自由辅助属性9*/),
  VFREE10(SaleInvoiceBVOCode.VFREE10, SaleInvoiceBVO.VFREE10/*自由辅助属性10*/),
  VQTUNITRATE(SaleInvoiceBVOCode.VQTUNITRATE, SaleInvoiceBVO.VQTUNITRATE/*报价单位换算率*/),
  VROWNOTE(SaleInvoiceBVOCode.VROWNOTE, SaleInvoiceBVO.VROWNOTE/*备注*/),

  VSUMCODE(SaleInvoiceBVOCode.VSUMCODE, SaleInvoiceBVO.VSUMCODE/*消耗汇总号*/);

  // 编码密码
  private String code;

  // 编码值
  private String key;

  private SOFInvoiceKey2Code(String code, String key) {
    this.key = key;
    this.code = code;
  }

  public String getKey() {
    return this.key;
  }

  public String getCode() {
    return this.code;
  }

}
