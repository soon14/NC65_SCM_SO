package nc.vo.so.pub.enumeration;

import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.so.m32.entity.SaleInvoiceBVO;

public enum InvoiceCombinRule {
  CCUSTMATERIALID(SaleInvoiceBVO.CCUSTMATERIALID, NCLangRes4VoTransl
      .getNCLangRes().getStrByID("4006004_0", "04006004-0227")/*客户物料码*/),
  // 1
  CMARBASCALSSID(SaleInvoiceBVO.CMARBASCALSSID, NCLangRes4VoTransl
      .getNCLangRes().getStrByID("4006004_0", "04006004-0093")/*物料基本分类*/),
  // 2
  CMATERIALID(SaleInvoiceBVO.CMATERIALID, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0094")/*物料最新版本*/),
  CMATERIALVID(SaleInvoiceBVO.CMATERIALVID, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0095")/*物料编码*/),
  CASTUNITID(SaleInvoiceBVO.CASTUNITID, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0034")/*单位*/),
  CUNITID(SaleInvoiceBVO.CUNITID, NCLangRes4VoTransl.getNCLangRes().getStrByID(
      "4006004_0", "04006004-0033")/*主单位*/),
  CQTUNITID(SaleInvoiceBVO.CQTUNITID, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0103")/*报价单位*/),
  BDISCOUNTFLAG(SaleInvoiceBVO.BDISCOUNTFLAG, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0080")/*折扣类*/),
  BFREECUSTFLAG(SaleInvoiceBVO.BFREECUSTFLAG, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0081")/*是否散户*/),
  BLABORFLAG(SaleInvoiceBVO.BLABORFLAG, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0082")/*服务类*/),
  BLARGESSFLAG(SaleInvoiceBVO.BLARGESSFLAG, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0083")/*赠品*/),
  CPRODLINEID(SaleInvoiceBVO.CPRODLINEID, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0098")/*产品线*/),
  PK_BATCHCODE(SaleInvoiceBVO.PK_BATCHCODE, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0150")/*批次档案*/),
  VBATCHCODE(SaleInvoiceBVO.VBATCHCODE, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0151")/*批次号*/),
  CCTMANAGEID(SaleInvoiceBVO.CCTMANAGEID, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0086")/*合同号*/),
  CDEPTID(SaleInvoiceBVO.CDEPTID, NCLangRes4VoTransl.getNCLangRes().getStrByID(
      "4006004_0", "04006004-0087")/*销售部门最新版本*/),
  CDEPTVID(SaleInvoiceBVO.CDEPTVID, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0088")/*销售部门*/),
  CEMPLOYEEID(SaleInvoiceBVO.CEMPLOYEEID, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0089")/*销售业务员*/),
  CTRANSPORTTYPEID(SaleInvoiceBVO.CTRANSPORTTYPEID, NCLangRes4VoTransl
      .getNCLangRes().getStrByID("4006004_0", "04006004-0113")/*运输方式*/),
  COPPOSESRCBID(SaleInvoiceBVO.COPPOSESRCBID, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0096")/*对冲来源子表*/),
  CORDERCUSTID(SaleInvoiceBVO.CORDERCUSTID, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0097")/*订单客户*/),
  CFREECUSTID(SaleInvoiceBVO.CFREECUSTID, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0092")/*散户*/),
  CCOSTSUBJID(SaleInvoiceBVO.CCOSTSUBJID, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0085")/*收支项目*/),
  CPRODUCTORID(SaleInvoiceBVO.CPRODUCTORID, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0099")/*生产厂商*/),
  CVENDORID(SaleInvoiceBVO.CVENDORID, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0114")/*供应商*/),
  CVMIVENDERID(SaleInvoiceBVO.CVMIVENDERID, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0115")/*寄存供应商*/),
  CPROFITCENTERID(SaleInvoiceBVO.CPROFITCENTERID, NCLangRes4VoTransl
      .getNCLangRes().getStrByID("4006004_0", "04006004-0100")/*利润中心最新版本*/),
  CPROFITCENTERVID(SaleInvoiceBVO.CPROFITCENTERVID, NCLangRes4VoTransl
      .getNCLangRes().getStrByID("4006004_0", "04006004-0101")/*利润中心*/),
  CPROJECTID(SaleInvoiceBVO.CPROJECTID, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0102")/*项目*/),

  CRECEIVEADDRID(SaleInvoiceBVO.CRECEIVEADDRID, NCLangRes4VoTransl
      .getNCLangRes().getStrByID("4006004_0", "04006004-0104")/*收货地址*/),
  CRECEIVECUSTID(SaleInvoiceBVO.CRECEIVECUSTID, NCLangRes4VoTransl
      .getNCLangRes().getStrByID("4006004_0", "04006004-0051")/*收货客户*/),
  CSALEORGID(SaleInvoiceBVO.CSALEORGID, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0105")/*销售组织最新版本*/),
  CSALEORGVID(SaleInvoiceBVO.CSALEORGVID, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0106")/*销售组织*/),
  CARORGID(SaleInvoiceBVO.CARORGID, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0084")/*应收组织最新版本*/),
  CARORGVID(SaleInvoiceBVO.CARORGVID, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0041")/*应收组织*/),
  CSENDSTOCKORGID(SaleInvoiceBVO.CSENDSTOCKORGID, NCLangRes4VoTransl
      .getNCLangRes().getStrByID("4006004_0", "04006004-0107")/*库存组织最新版本*/),
  CSENDSTOCKORGVID(SaleInvoiceBVO.CSENDSTOCKORGVID, NCLangRes4VoTransl
      .getNCLangRes().getStrByID("4006004_0", "04006004-0108")/*库存组织*/),
  CSENDSTORDOCID(SaleInvoiceBVO.CSENDSTORDOCID, NCLangRes4VoTransl
      .getNCLangRes().getStrByID("4006004_0", "04006004-0109")/*仓库*/),
  CFIRSTBID(SaleInvoiceBVO.CFIRSTBID, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0090")/*源头单据子表*/),
  CFIRSTID(SaleInvoiceBVO.CFIRSTID, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0091")/*源头单据主表*/),
  CSRCBID(SaleInvoiceBVO.CSRCBID, NCLangRes4VoTransl.getNCLangRes().getStrByID(
      "4006004_0", "04006004-0110")/*来源单据子表*/),
  CSRCID(SaleInvoiceBVO.CSRCID, NCLangRes4VoTransl.getNCLangRes().getStrByID(
      "4006004_0", "04006004-0111")/*来源单据主表*/),
  VSRCCODE(SaleInvoiceBVO.VSRCCODE, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0169")/*来源单据号*/),
  VSRCROWNO(SaleInvoiceBVO.VSRCROWNO, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0170")/*来源单据行号*/),
  VSRCTRANTYPE(SaleInvoiceBVO.VSRCTRANTYPE, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0171")/*来源交易类型*/),
  VSRCTYPE(SaleInvoiceBVO.VSRCTYPE, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0172")/*来源单据类型*/),
  CSUMID(SaleInvoiceBVO.CSUMID, NCLangRes4VoTransl.getNCLangRes().getStrByID(
      "4006004_0", "04006004-0112")/*消耗汇总主键*/),

  NASTNUM(SaleInvoiceBVO.NASTNUM, NCLangRes4VoTransl.getNCLangRes().getStrByID(
      "4006004_0", "04006004-0116")/*数量*/),
  NBFORIGSUBMNY("nbforigsubmny", NCLangRes4VoTransl.getNCLangRes().getStrByID(
      "4006004_0", "04006004-0117")/*冲减前金额*/),
  // NCOSTMNY(SaleInvoiceBVO.NCOSTMNY, NCLangRes4VoTransl.getNCLangRes()
  // .getStrByID("4006004_0", "04006004-0118")/*成本金额*/),
  NDISCOUNT(SaleInvoiceBVO.NDISCOUNT, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0119")/*本币折扣额*/),
  NDISCOUNTRATE(SaleInvoiceBVO.NDISCOUNTRATE, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0036")/*整单折扣*/),
  NGLOBALMNY(SaleInvoiceBVO.NGLOBALMNY, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0120")/*全局本币无税金额*/),
  NGLOBALTAXMNY(SaleInvoiceBVO.NGLOBALTAXMNY, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0121")/*全局本币价税合计*/),
  NGROUPMNY(SaleInvoiceBVO.NGROUPMNY, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0122")/*集团本币无税金额*/),
  NGROUPTAXMNY(SaleInvoiceBVO.NGROUPTAXMNY, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0123")/*集团本币价税合计*/),
  NINVOICEDISRATE(SaleInvoiceBVO.NINVOICEDISRATE, NCLangRes4VoTransl
      .getNCLangRes().getStrByID("4006004_0", "04006004-0037")/*发票折扣*/),
  NITEMDISCOUNTRATE(SaleInvoiceBVO.NITEMDISCOUNTRATE, NCLangRes4VoTransl
      .getNCLangRes().getStrByID("4006004_0", "04006004-0038")/*单品折扣*/),

  NMNY(SaleInvoiceBVO.NMNY, NCLangRes4VoTransl.getNCLangRes().getStrByID(
      "4006004_0", "04006004-0124")/*本币无税金额*/),
  NNETPRICE(SaleInvoiceBVO.NNETPRICE, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0125")/*主本币无税净价*/),
  NNUM(SaleInvoiceBVO.NNUM, NCLangRes4VoTransl.getNCLangRes().getStrByID(
      "4006004_0", "04006004-0126")/*主数量*/),
  NORIGDISCOUNT(SaleInvoiceBVO.NORIGDISCOUNT, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0127")/*折扣额*/),
  NORIGMNY(SaleInvoiceBVO.NORIGMNY, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0128")/*无税金额*/),
  NORIGNETPRICE(SaleInvoiceBVO.NORIGNETPRICE, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0129")/*主无税净价*/),
  NORIGPRICE(SaleInvoiceBVO.NORIGPRICE, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0130")/*主无税单价*/),
  NORIGSUBMNY(SaleInvoiceBVO.NORIGSUBMNY, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0131")/*费用冲抵金额*/),
  CTAXCODEID(SaleInvoiceBVO.CTAXCODEID, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0132")/*税码*/),
  FTAXTYPEFLAG(SaleInvoiceBVO.FTAXTYPEFLAG, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0226")/*扣税类别*/),

  NORIGTAXMNY(SaleInvoiceBVO.NORIGTAXMNY, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0133")/*价税合计*/),
  NORIGTAXNETPRICE(SaleInvoiceBVO.NORIGTAXNETPRICE, NCLangRes4VoTransl
      .getNCLangRes().getStrByID("4006004_0", "04006004-0134")/*主含税净价*/),
  NORIGTAXPRICE(SaleInvoiceBVO.NORIGTAXPRICE, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0135")/*主含税单价*/),
  NPRICE(SaleInvoiceBVO.NPRICE, NCLangRes4VoTransl.getNCLangRes().getStrByID(
      "4006004_0", "04006004-0136")/*主本币无税单价*/),

  NQTNETPRICE(SaleInvoiceBVO.NQTNETPRICE, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0137")/*本币无税净价*/),
  NQTORIGNETPRICE(SaleInvoiceBVO.NQTORIGNETPRICE, NCLangRes4VoTransl
      .getNCLangRes().getStrByID("4006004_0", "04006004-0138")/*无税净价*/),
  NQTORIGPRICE(SaleInvoiceBVO.NQTORIGPRICE, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0139")/*无税单价*/),
  NQTORIGTAXNETPRC(SaleInvoiceBVO.NQTORIGTAXNETPRC, NCLangRes4VoTransl
      .getNCLangRes().getStrByID("4006004_0", "04006004-0140")/*含税净价*/),

  NQTORIGTAXPRICE(SaleInvoiceBVO.NQTORIGTAXPRICE, NCLangRes4VoTransl
      .getNCLangRes().getStrByID("4006004_0", "04006004-0141")/*含税单价*/),
  NQTPRICE(SaleInvoiceBVO.NQTPRICE, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0142")/*本币无税单价*/),
  NQTTAXNETPRICE(SaleInvoiceBVO.NQTTAXNETPRICE, NCLangRes4VoTransl
      .getNCLangRes().getStrByID("4006004_0", "04006004-0143")/*本币含税净价*/),
  NQTTAXPRICE(SaleInvoiceBVO.NQTTAXPRICE, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0144")/*本币含税单价*/),

  NQTUNITNUM(SaleInvoiceBVO.NQTUNITNUM, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0145")/*报价数量*/),
  NTAX(SaleInvoiceBVO.NTAX, NCLangRes4VoTransl.getNCLangRes().getStrByID(
      "4006004_0", "04006004-0146")/*本币税额*/),
  NTAXMNY(SaleInvoiceBVO.NTAXMNY, NCLangRes4VoTransl.getNCLangRes().getStrByID(
      "4006004_0", "04006004-0147")/*本币价税合计*/),
  NTAXNETPRICE(SaleInvoiceBVO.NTAXNETPRICE, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0148")/*主本币含税净价*/),

  NTAXPRICE(SaleInvoiceBVO.NTAXPRICE, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0149")/*主本币含税单价*/),
  NTAXRATE(SaleInvoiceBVO.NTAXRATE, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0035")/*税率*/),

  VBDEF1(SaleInvoiceBVO.VBDEF1, NCLangRes4VoTransl.getNCLangRes().getStrByID(
      "4006004_0", "04006004-0061")/*自定义项1*/),
  VBDEF2(SaleInvoiceBVO.VBDEF2, NCLangRes4VoTransl.getNCLangRes().getStrByID(
      "4006004_0", "04006004-0072")/*自定义项2*/),
  VBDEF20(SaleInvoiceBVO.VBDEF20, NCLangRes4VoTransl.getNCLangRes().getStrByID(
      "4006004_0", "04006004-0073")/*自定义项20*/),
  VBDEF3(SaleInvoiceBVO.VBDEF3, NCLangRes4VoTransl.getNCLangRes().getStrByID(
      "4006004_0", "04006004-0074")/*自定义项3*/),
  VBDEF4(SaleInvoiceBVO.VBDEF4, NCLangRes4VoTransl.getNCLangRes().getStrByID(
      "4006004_0", "04006004-0075")/*自定义项4*/),
  VBDEF5(SaleInvoiceBVO.VBDEF5, NCLangRes4VoTransl.getNCLangRes().getStrByID(
      "4006004_0", "04006004-0076")/*自定义项5*/),
  VBDEF6(SaleInvoiceBVO.VBDEF6, NCLangRes4VoTransl.getNCLangRes().getStrByID(
      "4006004_0", "04006004-0077")/*自定义项6*/),
  VBDEF7(SaleInvoiceBVO.VBDEF7, NCLangRes4VoTransl.getNCLangRes().getStrByID(
      "4006004_0", "04006004-0078")/*自定义项7*/),
  VBDEF8(SaleInvoiceBVO.VBDEF8, NCLangRes4VoTransl.getNCLangRes().getStrByID(
      "4006004_0", "04006004-0079")/*自定义项8*/),
  VBDEF9(SaleInvoiceBVO.VBDEF9, NCLangRes4VoTransl.getNCLangRes().getStrByID(
      "4006004_0", "04006004-0225")/*自定义项9*/),
  VBDEF10(SaleInvoiceBVO.VBDEF10, NCLangRes4VoTransl.getNCLangRes().getStrByID(
      "4006004_0", "04006004-0062")/*自定义项10*/),
  VBDEF11(SaleInvoiceBVO.VBDEF11, NCLangRes4VoTransl.getNCLangRes().getStrByID(
      "4006004_0", "04006004-0063")/*自定义项11*/),
  VBDEF12(SaleInvoiceBVO.VBDEF12, NCLangRes4VoTransl.getNCLangRes().getStrByID(
      "4006004_0", "04006004-0064")/*自定义项12*/),

  VBDEF13(SaleInvoiceBVO.VBDEF13, NCLangRes4VoTransl.getNCLangRes().getStrByID(
      "4006004_0", "04006004-0065")/*自定义项13*/),
  VBDEF14(SaleInvoiceBVO.VBDEF14, NCLangRes4VoTransl.getNCLangRes().getStrByID(
      "4006004_0", "04006004-0066")/*自定义项14*/),
  VBDEF15(SaleInvoiceBVO.VBDEF15, NCLangRes4VoTransl.getNCLangRes().getStrByID(
      "4006004_0", "04006004-0067")/*自定义项15*/),
  VBDEF16(SaleInvoiceBVO.VBDEF16, NCLangRes4VoTransl.getNCLangRes().getStrByID(
      "4006004_0", "04006004-0068")/*自定义项16*/),
  VBDEF17(SaleInvoiceBVO.VBDEF17, NCLangRes4VoTransl.getNCLangRes().getStrByID(
      "4006004_0", "04006004-0069")/*自定义项17*/),
  VBDEF18(SaleInvoiceBVO.VBDEF18, NCLangRes4VoTransl.getNCLangRes().getStrByID(
      "4006004_0", "04006004-0070")/*自定义项18*/),
  VBDEF19(SaleInvoiceBVO.VBDEF19, NCLangRes4VoTransl.getNCLangRes().getStrByID(
      "4006004_0", "04006004-0071")/*自定义项19*/),

  VCHANGERATE(SaleInvoiceBVO.VCHANGERATE, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0152")/*换算率*/),
  VFIRSTCODE(SaleInvoiceBVO.VFIRSTCODE, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0153")/*源头单据号*/),
  VFIRSTROWNO(SaleInvoiceBVO.VFIRSTROWNO, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0154")/*源头单据行号*/),
  VFIRSTTRANTYPE(SaleInvoiceBVO.VFIRSTTRANTYPE, NCLangRes4VoTransl
      .getNCLangRes().getStrByID("4006004_0", "04006004-0155")/*源头交易类型*/),
  VFIRSTTYPE(SaleInvoiceBVO.VFIRSTTYPE, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0156")/*源头单据类型*/),
  VFREE1(SaleInvoiceBVO.VFREE1, NCLangRes4VoTransl.getNCLangRes().getStrByID(
      "4006004_0", "04006004-0157")/*自由辅助属性1*/),

  VFREE2(SaleInvoiceBVO.VFREE2, NCLangRes4VoTransl.getNCLangRes().getStrByID(
      "4006004_0", "04006004-0159")/*自由辅助属性2*/),
  VFREE3(SaleInvoiceBVO.VFREE3, NCLangRes4VoTransl.getNCLangRes().getStrByID(
      "4006004_0", "04006004-0160")/*自由辅助属性3*/),
  VFREE4(SaleInvoiceBVO.VFREE4, NCLangRes4VoTransl.getNCLangRes().getStrByID(
      "4006004_0", "04006004-0161")/*自由辅助属性4*/),
  VFREE5(SaleInvoiceBVO.VFREE5, NCLangRes4VoTransl.getNCLangRes().getStrByID(
      "4006004_0", "04006004-0162")/*自由辅助属性5*/),
  VFREE6(SaleInvoiceBVO.VFREE6, NCLangRes4VoTransl.getNCLangRes().getStrByID(
      "4006004_0", "04006004-0163")/*自由辅助属性6*/),
  VFREE7(SaleInvoiceBVO.VFREE7, NCLangRes4VoTransl.getNCLangRes().getStrByID(
      "4006004_0", "04006004-0164")/*自由辅助属性7*/),
  VFREE8(SaleInvoiceBVO.VFREE8, NCLangRes4VoTransl.getNCLangRes().getStrByID(
      "4006004_0", "04006004-0165")/*自由辅助属性8*/),
  VFREE9(SaleInvoiceBVO.VFREE9, NCLangRes4VoTransl.getNCLangRes().getStrByID(
      "4006004_0", "04006004-0166")/*自由辅助属性9*/),
  VFREE10(SaleInvoiceBVO.VFREE10, NCLangRes4VoTransl.getNCLangRes().getStrByID(
      "4006004_0", "04006004-0158")/*自由辅助属性10*/),
  VQTUNITRATE(SaleInvoiceBVO.VQTUNITRATE, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0167")/*报价单位换算率*/),
  VROWNOTE(SaleInvoiceBVO.VROWNOTE, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0168")/*备注*/),

  VSUMCODE(SaleInvoiceBVO.VSUMCODE, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0173")/*消耗汇总号*/);

  // 单据类型
  private String key;

  // 单据名称
  private String name;

  private InvoiceCombinRule(String key, String name) {
    this.key = key;
    this.name = name;
  }

  public String getKey() {
    return this.key;
  }

  public String getName() {
    return this.name;
  }

}
