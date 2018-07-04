package nc.vo.so.m32.paravo;

import nc.vo.so.m32.entity.SaleInvoiceBVO;

/**
 * 合并显示变量
 * 
 * @since 6.0
 * @version 2011-12-7 上午09:26:07
 * @author 么贵敬
 */
public class CombinContext {
  /**
   * 单品折扣、发票折扣、税率
   * 主含税单价、主无税单价、主含税净价、主无税净价
   * 主本币含税单价、主本币无税净价、本币无税净价、本币无税单价、本币含税净价、本币含税单价
   * 无税净价、无税单价、含税净价、含税单价
   */
  public static final String[] COMBIN_AVERAG = new String[] {
    SaleInvoiceBVO.NITEMDISCOUNTRATE, SaleInvoiceBVO.NINVOICEDISRATE,
    SaleInvoiceBVO.NTAXRATE, SaleInvoiceBVO.NORIGTAXPRICE,
    SaleInvoiceBVO.NORIGPRICE, SaleInvoiceBVO.NORIGTAXNETPRICE,
    SaleInvoiceBVO.NORIGNETPRICE, SaleInvoiceBVO.NTAXPRICE,
    SaleInvoiceBVO.NPRICE, SaleInvoiceBVO.NTAXNETPRICE,
    SaleInvoiceBVO.NNETPRICE, SaleInvoiceBVO.NQTNETPRICE,
    SaleInvoiceBVO.NQTPRICE, SaleInvoiceBVO.NQTTAXNETPRICE,
    SaleInvoiceBVO.NQTTAXPRICE, SaleInvoiceBVO.NQTORIGNETPRICE,
    SaleInvoiceBVO.NQTORIGPRICE, SaleInvoiceBVO.NQTORIGTAXNETPRC,
    SaleInvoiceBVO.NQTORIGTAXPRICE
  };

  /**
   * 主数量、数量、报价数量、税额、无税金额
   * 价税合计、本币税额、本币无税金额、本币价税合计
   * 本币折扣额、冲抵前金额、费用冲抵金额
   * 成本金额、集团本币无税金额、集团本币价税合计
   * 全局本币无税金额、全局本币价税合计\本币折扣额、折扣额
   */
  public static final String[] COMBIN_SUMKEYS = new String[] {
    SaleInvoiceBVO.NNUM, SaleInvoiceBVO.NASTNUM, SaleInvoiceBVO.NQTUNITNUM,
    SaleInvoiceBVO.NORIGMNY, SaleInvoiceBVO.NORIGTAXMNY, SaleInvoiceBVO.NTAX,
    SaleInvoiceBVO.NMNY, SaleInvoiceBVO.NTAXMNY, SaleInvoiceBVO.NDISCOUNT,
    SaleInvoiceBVO.NORIGSUBMNY, SaleInvoiceBVO.NGROUPMNY,
    SaleInvoiceBVO.NGROUPTAXMNY, SaleInvoiceBVO.NGLOBALMNY,
    SaleInvoiceBVO.NGLOBALTAXMNY, SaleInvoiceBVO.NORIGDISCOUNT,
    SaleInvoiceBVO.NCALTAXMNY
  };
}
