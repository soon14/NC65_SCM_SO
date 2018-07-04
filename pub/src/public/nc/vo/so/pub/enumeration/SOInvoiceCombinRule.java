package nc.vo.so.pub.enumeration;

import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.so.entry.SaleInvoiceBVOCode;

public enum SOInvoiceCombinRule {
  CCUSTMATERIALID(SaleInvoiceBVOCode.CCUSTMATERIALID, NCLangRes4VoTransl
      .getNCLangRes().getStrByID("4006004_0", "04006004-0227")/*客户物料码*/),
  CMARBASCALSSID(SaleInvoiceBVOCode.CMARBASCALSSID, NCLangRes4VoTransl
      .getNCLangRes().getStrByID("4006004_0", "04006004-0093")/*物料基本分类*/),
  CMATERIALID(SaleInvoiceBVOCode.CMATERIALID, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0094")/*物料最新版本*/),
  CMATERIALVID(SaleInvoiceBVOCode.CMATERIALVID, NCLangRes4VoTransl
      .getNCLangRes().getStrByID("4006004_0", "04006004-0095")/*物料编码*/),
  CASTUNITID(SaleInvoiceBVOCode.CASTUNITID, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0034")/*单位*/),
  CUNITID(SaleInvoiceBVOCode.CUNITID, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0033")/*主单位*/),
  CQTUNITID(SaleInvoiceBVOCode.CQTUNITID, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0103")/*报价单位*/),
  BDISCOUNTFLAG(SaleInvoiceBVOCode.BDISCOUNTFLAG, NCLangRes4VoTransl
      .getNCLangRes().getStrByID("4006004_0", "04006004-0080")/*折扣类*/),
  BFREECUSTFLAG(SaleInvoiceBVOCode.BFREECUSTFLAG, NCLangRes4VoTransl
      .getNCLangRes().getStrByID("4006004_0", "04006004-0081")/*是否散户*/),
  BLABORFLAG(SaleInvoiceBVOCode.BLABORFLAG, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0082")/*服务类*/),
  BLARGESSFLAG(SaleInvoiceBVOCode.BLARGESSFLAG, NCLangRes4VoTransl
      .getNCLangRes().getStrByID("4006004_0", "04006004-0083")/*赠品*/),
  CPRODLINEID(SaleInvoiceBVOCode.CPRODLINEID, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0098")/*产品线*/),
  PK_BATCHCODE(SaleInvoiceBVOCode.PK_BATCHCODE, NCLangRes4VoTransl
      .getNCLangRes().getStrByID("4006004_0", "04006004-0150")/*批次档案*/),
  VBATCHCODE(SaleInvoiceBVOCode.VBATCHCODE, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0151")/*批次号*/),
  CCTMANAGEID(SaleInvoiceBVOCode.CCTMANAGEID, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0086")/*合同号*/),
  CDEPTID(SaleInvoiceBVOCode.CDEPTID, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0087")/*销售部门最新版本*/),
  CDEPTVID(SaleInvoiceBVOCode.CDEPTVID, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0088")/*销售部门*/),
  CEMPLOYEEID(SaleInvoiceBVOCode.CEMPLOYEEID, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0089")/*销售业务员*/),
  CTRANSPORTTYPEID(SaleInvoiceBVOCode.CTRANSPORTTYPEID, NCLangRes4VoTransl
      .getNCLangRes().getStrByID("4006004_0", "04006004-0113")/*运输方式*/),
  COPPOSESRCBID(SaleInvoiceBVOCode.COPPOSESRCBID, NCLangRes4VoTransl
      .getNCLangRes().getStrByID("4006004_0", "04006004-0096")/*对冲来源子表*/),
  CORDERCUSTID(SaleInvoiceBVOCode.CORDERCUSTID, NCLangRes4VoTransl
      .getNCLangRes().getStrByID("4006004_0", "04006004-0097")/*订单客户*/),
  CFREECUSTID(SaleInvoiceBVOCode.CFREECUSTID, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0092")/*散户*/),
  CCOSTSUBJID(SaleInvoiceBVOCode.CCOSTSUBJID, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0085")/*收支项目*/),
  CPRODUCTORID(SaleInvoiceBVOCode.CPRODUCTORID, NCLangRes4VoTransl
      .getNCLangRes().getStrByID("4006004_0", "04006004-0099")/*生产厂商*/),
  CVENDORID(SaleInvoiceBVOCode.CVENDORID, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0114")/*供应商*/),
  CVMIVENDERID(SaleInvoiceBVOCode.CVMIVENDERID, NCLangRes4VoTransl
      .getNCLangRes().getStrByID("4006004_0", "04006004-0115")/*寄存供应商*/),
  CPROFITCENTERID(SaleInvoiceBVOCode.CPROFITCENTERID, NCLangRes4VoTransl
      .getNCLangRes().getStrByID("4006004_0", "04006004-0100")/*利润中心最新版本*/),
  CPROFITCENTERVID(SaleInvoiceBVOCode.CPROFITCENTERVID, NCLangRes4VoTransl
      .getNCLangRes().getStrByID("4006004_0", "04006004-0101")/*利润中心*/),
  CPROJECTID(SaleInvoiceBVOCode.CPROJECTID, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0102")/*项目*/),

  CRECEIVEADDRID(SaleInvoiceBVOCode.CRECEIVEADDRID, NCLangRes4VoTransl
      .getNCLangRes().getStrByID("4006004_0", "04006004-0104")/*收货地址*/),
  CRECEIVECUSTID(SaleInvoiceBVOCode.CRECEIVECUSTID, NCLangRes4VoTransl
      .getNCLangRes().getStrByID("4006004_0", "04006004-0051")/*收货客户*/),
  CSALEORGID(SaleInvoiceBVOCode.CSALEORGID, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0105")/*销售组织最新版本*/),
  CSALEORGVID(SaleInvoiceBVOCode.CSALEORGVID, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0106")/*销售组织*/),
  CARORGID(SaleInvoiceBVOCode.CARORGID, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0084")/*应收组织最新版本*/),
  CARORGVID(SaleInvoiceBVOCode.CARORGVID, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0041")/*应收组织*/),
  CSENDSTOCKORGID(SaleInvoiceBVOCode.CSENDSTOCKORGID, NCLangRes4VoTransl
      .getNCLangRes().getStrByID("4006004_0", "04006004-0107")/*库存组织最新版本*/),
  CSENDSTOCKORGVID(SaleInvoiceBVOCode.CSENDSTOCKORGVID, NCLangRes4VoTransl
      .getNCLangRes().getStrByID("4006004_0", "04006004-0108")/*库存组织*/),
  CSENDSTORDOCID(SaleInvoiceBVOCode.CSENDSTORDOCID, NCLangRes4VoTransl
      .getNCLangRes().getStrByID("4006004_0", "04006004-0109")/*仓库*/),
  CFIRSTBID(SaleInvoiceBVOCode.CFIRSTBID, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0090")/*源头单据子表*/),
  CFIRSTID(SaleInvoiceBVOCode.CFIRSTID, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0091")/*源头单据主表*/),
  CSRCBID(SaleInvoiceBVOCode.CSRCBID, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0110")/*来源单据子表*/),
  CSRCID(SaleInvoiceBVOCode.CSRCID, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0111")/*来源单据主表*/),
  VSRCCODE(SaleInvoiceBVOCode.VSRCCODE, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0169")/*来源单据号*/),
  VSRCROWNO(SaleInvoiceBVOCode.VSRCROWNO, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0170")/*来源单据行号*/),
  VSRCTRANTYPE(SaleInvoiceBVOCode.VSRCTRANTYPE, NCLangRes4VoTransl
      .getNCLangRes().getStrByID("4006004_0", "04006004-0171")/*来源交易类型*/),
  VSRCTYPE(SaleInvoiceBVOCode.VSRCTYPE, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0172")/*来源单据类型*/),
  CSUMID(SaleInvoiceBVOCode.CSUMID, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0112")/*消耗汇总主键*/),

  NASTNUM(SaleInvoiceBVOCode.NASTNUM, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0116")/*数量*/),
  NBFORIGSUBMNY(SaleInvoiceBVOCode.NBFORIGSUBMNY, NCLangRes4VoTransl
      .getNCLangRes().getStrByID("4006004_0", "04006004-0117")/*冲减前金额*/),
  // NCOSTMNY(SaleInvoiceBVOCode.NCOSTMNY, NCLangRes4VoTransl.getNCLangRes()
  // .getStrByID("4006004_0", "04006004-0118")/*成本金额*/),
  NDISCOUNT(SaleInvoiceBVOCode.NDISCOUNT, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0119")/*本币折扣额*/),
  NDISCOUNTRATE(SaleInvoiceBVOCode.NDISCOUNTRATE, NCLangRes4VoTransl
      .getNCLangRes().getStrByID("4006004_0", "04006004-0036")/*整单折扣*/),
  NGLOBALMNY(SaleInvoiceBVOCode.NGLOBALMNY, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0120")/*全局本币无税金额*/),
  NGLOBALTAXMNY(SaleInvoiceBVOCode.NGLOBALTAXMNY, NCLangRes4VoTransl
      .getNCLangRes().getStrByID("4006004_0", "04006004-0121")/*全局本币价税合计*/),
  NGROUPMNY(SaleInvoiceBVOCode.NGROUPMNY, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0122")/*集团本币无税金额*/),
  NGROUPTAXMNY(SaleInvoiceBVOCode.NGROUPTAXMNY, NCLangRes4VoTransl
      .getNCLangRes().getStrByID("4006004_0", "04006004-0123")/*集团本币价税合计*/),
  NINVOICEDISRATE(SaleInvoiceBVOCode.NINVOICEDISRATE, NCLangRes4VoTransl
      .getNCLangRes().getStrByID("4006004_0", "04006004-0037")/*发票折扣*/),
  NITEMDISCOUNTRATE(SaleInvoiceBVOCode.NITEMDISCOUNTRATE, NCLangRes4VoTransl
      .getNCLangRes().getStrByID("4006004_0", "04006004-0038")/*单品折扣*/),

  NMNY(SaleInvoiceBVOCode.NMNY, NCLangRes4VoTransl.getNCLangRes().getStrByID(
      "4006004_0", "04006004-0124")/*本币无税金额*/),
  NNETPRICE(SaleInvoiceBVOCode.NNETPRICE, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0125")/*主本币无税净价*/),
  NNUM(SaleInvoiceBVOCode.NNUM, NCLangRes4VoTransl.getNCLangRes().getStrByID(
      "4006004_0", "04006004-0126")/*主数量*/),
  NORIGDISCOUNT(SaleInvoiceBVOCode.NORIGDISCOUNT, NCLangRes4VoTransl
      .getNCLangRes().getStrByID("4006004_0", "04006004-0127")/*折扣额*/),
  NORIGMNY(SaleInvoiceBVOCode.NORIGMNY, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0128")/*无税金额*/),
  NORIGNETPRICE(SaleInvoiceBVOCode.NORIGNETPRICE, NCLangRes4VoTransl
      .getNCLangRes().getStrByID("4006004_0", "04006004-0129")/*主无税净价*/),
  NORIGPRICE(SaleInvoiceBVOCode.NORIGPRICE, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0130")/*主无税单价*/),
  NORIGSUBMNY(SaleInvoiceBVOCode.NORIGSUBMNY, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0131")/*费用冲抵金额*/),
  CTAXCODEID(SaleInvoiceBVOCode.CTAXCODEID, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0132")/*税码*/),
  FTAXTYPEFLAG(SaleInvoiceBVOCode.FTAXTYPEFLAG, NCLangRes4VoTransl
      .getNCLangRes().getStrByID("4006004_0", "04006004-0226")/*扣税类别*/),

  NORIGTAXMNY(SaleInvoiceBVOCode.NORIGTAXMNY, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0133")/*价税合计*/),
  NORIGTAXNETPRICE(SaleInvoiceBVOCode.NORIGTAXNETPRICE, NCLangRes4VoTransl
      .getNCLangRes().getStrByID("4006004_0", "04006004-0134")/*主含税净价*/),
  NORIGTAXPRICE(SaleInvoiceBVOCode.NORIGTAXPRICE, NCLangRes4VoTransl
      .getNCLangRes().getStrByID("4006004_0", "04006004-0135")/*主含税单价*/),
  NPRICE(SaleInvoiceBVOCode.NPRICE, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0136")/*主本币无税单价*/),

  NQTNETPRICE(SaleInvoiceBVOCode.NQTNETPRICE, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0137")/*本币无税净价*/),
  NQTORIGNETPRICE(SaleInvoiceBVOCode.NQTORIGNETPRICE, NCLangRes4VoTransl
      .getNCLangRes().getStrByID("4006004_0", "04006004-0138")/*无税净价*/),
  NQTORIGPRICE(SaleInvoiceBVOCode.NQTORIGPRICE, NCLangRes4VoTransl
      .getNCLangRes().getStrByID("4006004_0", "04006004-0139")/*无税单价*/),
  NQTORIGTAXNETPRC(SaleInvoiceBVOCode.NQTORIGTAXNETPRC, NCLangRes4VoTransl
      .getNCLangRes().getStrByID("4006004_0", "04006004-0140")/*含税净价*/),

  NQTORIGTAXPRICE(SaleInvoiceBVOCode.NQTORIGTAXPRICE, NCLangRes4VoTransl
      .getNCLangRes().getStrByID("4006004_0", "04006004-0141")/*含税单价*/),
  NQTPRICE(SaleInvoiceBVOCode.NQTPRICE, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0142")/*本币无税单价*/),
  NQTTAXNETPRICE(SaleInvoiceBVOCode.NQTTAXNETPRICE, NCLangRes4VoTransl
      .getNCLangRes().getStrByID("4006004_0", "04006004-0143")/*本币含税净价*/),
  NQTTAXPRICE(SaleInvoiceBVOCode.NQTTAXPRICE, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0144")/*本币含税单价*/),

  NQTUNITNUM(SaleInvoiceBVOCode.NQTUNITNUM, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0145")/*报价数量*/),
  NTAX(SaleInvoiceBVOCode.NTAX, NCLangRes4VoTransl.getNCLangRes().getStrByID(
      "4006004_0", "04006004-0146")/*本币税额*/),
  NTAXMNY(SaleInvoiceBVOCode.NTAXMNY, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0147")/*本币价税合计*/),
  NTAXNETPRICE(SaleInvoiceBVOCode.NTAXNETPRICE, NCLangRes4VoTransl
      .getNCLangRes().getStrByID("4006004_0", "04006004-0148")/*主本币含税净价*/),

  NTAXPRICE(SaleInvoiceBVOCode.NTAXPRICE, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0149")/*主本币含税单价*/),
  NTAXRATE(SaleInvoiceBVOCode.NTAXRATE, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0035")/*税率*/),

  VBDEF1(SaleInvoiceBVOCode.VBDEF1, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0061")/*自定义项1*/),
  VBDEF2(SaleInvoiceBVOCode.VBDEF2, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0072")/*自定义项2*/),
  VBDEF20(SaleInvoiceBVOCode.VBDEF20, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0073")/*自定义项20*/),
  VBDEF3(SaleInvoiceBVOCode.VBDEF3, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0074")/*自定义项3*/),
  VBDEF4(SaleInvoiceBVOCode.VBDEF4, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0075")/*自定义项4*/),
  VBDEF5(SaleInvoiceBVOCode.VBDEF5, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0076")/*自定义项5*/),
  VBDEF6(SaleInvoiceBVOCode.VBDEF6, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0077")/*自定义项6*/),
  VBDEF7(SaleInvoiceBVOCode.VBDEF7, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0078")/*自定义项7*/),
  VBDEF8(SaleInvoiceBVOCode.VBDEF8, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0079")/*自定义项8*/),
  VBDEF9(SaleInvoiceBVOCode.VBDEF9, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0225")/*自定义项9*/),
  VBDEF10(SaleInvoiceBVOCode.VBDEF10, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0062")/*自定义项10*/),
  VBDEF11(SaleInvoiceBVOCode.VBDEF11, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0063")/*自定义项11*/),
  VBDEF12(SaleInvoiceBVOCode.VBDEF12, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0064")/*自定义项12*/),

  VBDEF13(SaleInvoiceBVOCode.VBDEF13, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0065")/*自定义项13*/),
  VBDEF14(SaleInvoiceBVOCode.VBDEF14, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0066")/*自定义项14*/),
  VBDEF15(SaleInvoiceBVOCode.VBDEF15, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0067")/*自定义项15*/),
  VBDEF16(SaleInvoiceBVOCode.VBDEF16, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0068")/*自定义项16*/),
  VBDEF17(SaleInvoiceBVOCode.VBDEF17, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0069")/*自定义项17*/),
  VBDEF18(SaleInvoiceBVOCode.VBDEF18, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0070")/*自定义项18*/),
  VBDEF19(SaleInvoiceBVOCode.VBDEF19, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0071")/*自定义项19*/),

  VCHANGERATE(SaleInvoiceBVOCode.VCHANGERATE, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0152")/*换算率*/),
  VFIRSTCODE(SaleInvoiceBVOCode.VFIRSTCODE, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0153")/*源头单据号*/),
  VFIRSTROWNO(SaleInvoiceBVOCode.VFIRSTROWNO, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0154")/*源头单据行号*/),
  VFIRSTTRANTYPE(SaleInvoiceBVOCode.VFIRSTTRANTYPE, NCLangRes4VoTransl
      .getNCLangRes().getStrByID("4006004_0", "04006004-0155")/*源头交易类型*/),
  VFIRSTTYPE(SaleInvoiceBVOCode.VFIRSTTYPE, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0156")/*源头单据类型*/),
  VFREE1(SaleInvoiceBVOCode.VFREE1, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0157")/*自由辅助属性1*/),

  VFREE2(SaleInvoiceBVOCode.VFREE2, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0159")/*自由辅助属性2*/),
  VFREE3(SaleInvoiceBVOCode.VFREE3, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0160")/*自由辅助属性3*/),
  VFREE4(SaleInvoiceBVOCode.VFREE4, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0161")/*自由辅助属性4*/),
  VFREE5(SaleInvoiceBVOCode.VFREE5, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0162")/*自由辅助属性5*/),
  VFREE6(SaleInvoiceBVOCode.VFREE6, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0163")/*自由辅助属性6*/),
  VFREE7(SaleInvoiceBVOCode.VFREE7, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0164")/*自由辅助属性7*/),
  VFREE8(SaleInvoiceBVOCode.VFREE8, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0165")/*自由辅助属性8*/),
  VFREE9(SaleInvoiceBVOCode.VFREE9, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0166")/*自由辅助属性9*/),
  VFREE10(SaleInvoiceBVOCode.VFREE10, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0158")/*自由辅助属性10*/),
  VQTUNITRATE(SaleInvoiceBVOCode.VQTUNITRATE, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0167")/*报价单位换算率*/),
  VROWNOTE(SaleInvoiceBVOCode.VROWNOTE, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0168")/*备注*/),

  VSUMCODE(SaleInvoiceBVOCode.VSUMCODE, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0173")/*消耗汇总号*/);

  // 单据类型
  private String key;

  // 单据名称
  private String name;

  private SOInvoiceCombinRule(String key, String name) {
    this.key = key;
    this.name = name;
  }

  public String getKey() {
    return this.key;
  }

  public String getName() {
    return this.name;
  }

  public String getKeyCode() {
    return this.key;
  }

}
