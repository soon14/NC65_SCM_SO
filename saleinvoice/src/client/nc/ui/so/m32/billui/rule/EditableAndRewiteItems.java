package nc.ui.so.m32.billui.rule;

import nc.vo.so.m32.entity.SaleInvoiceBVO;
import nc.vo.so.m32.entity.SaleInvoiceHVO;

/**
 * 销售发票审批中修改可编辑字段
 * 
 * @since 6.36
 * @version 2015-1-6 下午5:37:30
 * @author wangshu6
 */
public class EditableAndRewiteItems {

  // 注册表头可以编辑的字段
  public static final String[] HEADEDITABLEITEMKEY = new String[] {
    /** ---------- 表头 ---------- */
    SaleInvoiceHVO.VNOTE, 
    /** -------表头自定义项------ **/
    SaleInvoiceHVO.VDEF1, SaleInvoiceHVO.VDEF2, SaleInvoiceHVO.VDEF3,
    SaleInvoiceHVO.VDEF4, SaleInvoiceHVO.VDEF5, SaleInvoiceHVO.VDEF6,
    SaleInvoiceHVO.VDEF7, SaleInvoiceHVO.VDEF8, SaleInvoiceHVO.VDEF9,
    SaleInvoiceHVO.VDEF10, SaleInvoiceHVO.VDEF11, SaleInvoiceHVO.VDEF12,
    SaleInvoiceHVO.VDEF13, SaleInvoiceHVO.VDEF14, SaleInvoiceHVO.VDEF15,
    SaleInvoiceHVO.VDEF16, SaleInvoiceHVO.VDEF17, SaleInvoiceHVO.VDEF18,
    SaleInvoiceHVO.VDEF19, SaleInvoiceHVO.VDEF20,
    
  };

  // 注册表体修订可以编辑的字段
  public static final String[] BODYEDITABLEITEMKEY = new String[] {

    /** ---------- 表体 ---------- */
    
    // 数量、主数量、换算率
    SaleInvoiceBVO.NASTNUM,
    SaleInvoiceBVO.NNUM,
    SaleInvoiceBVO.VCHANGERATE,
    // 单位
    SaleInvoiceBVO.CASTUNITID,
    // 报价换算率、税率、报价数量
    SaleInvoiceBVO.VQTUNITRATE,
    SaleInvoiceBVO.NTAXRATE,
    SaleInvoiceBVO.NQTUNITNUM,
    //主含税单价、主无税单价  主含税净价、主无税净价、
    SaleInvoiceBVO.NORIGTAXPRICE,
    SaleInvoiceBVO.NORIGPRICE,
    SaleInvoiceBVO.NORIGTAXNETPRICE,
    SaleInvoiceBVO.NORIGNETPRICE,
    
    //含税单价 无税单价、含税净价、无税净价
    SaleInvoiceBVO.NQTORIGTAXPRICE,
    SaleInvoiceBVO.NQTORIGPRICE,
    SaleInvoiceBVO.NQTORIGTAXNETPRC,
    SaleInvoiceBVO.NQTORIGNETPRICE,
    // 无税金额、价税合计、计税金额
    SaleInvoiceBVO.NORIGMNY,
    SaleInvoiceBVO.NORIGTAXMNY,
    SaleInvoiceBVO.NCALTAXMNY,

    /** -------表体自定义项------ **/
    SaleInvoiceBVO.VBDEF1, SaleInvoiceBVO.VBDEF2, SaleInvoiceBVO.VBDEF3,
    SaleInvoiceBVO.VBDEF4, SaleInvoiceBVO.VBDEF5, SaleInvoiceBVO.VBDEF6,
    SaleInvoiceBVO.VBDEF7, SaleInvoiceBVO.VBDEF8, SaleInvoiceBVO.VBDEF9,
    SaleInvoiceBVO.VBDEF10, SaleInvoiceBVO.VBDEF11, SaleInvoiceBVO.VBDEF12,
    SaleInvoiceBVO.VBDEF13, SaleInvoiceBVO.VBDEF14, SaleInvoiceBVO.VBDEF15,
    SaleInvoiceBVO.VBDEF16, SaleInvoiceBVO.VBDEF17, SaleInvoiceBVO.VBDEF18,
    SaleInvoiceBVO.VBDEF19, SaleInvoiceBVO.VBDEF20
  };


}
