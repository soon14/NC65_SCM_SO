package nc.vo.so.pub.enumeration;

import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.so.m32.entity.SaleInvoiceBVO;

public enum InvoiceCombindefrule {

  VBDEF1(SaleInvoiceBVO.VBDEF1, NCLangRes4VoTransl.getNCLangRes().getStrByID(
      "4006004_0", "04006004-0061")/*自定义项1*/),
  VBDEF2(SaleInvoiceBVO.VBDEF2, NCLangRes4VoTransl.getNCLangRes().getStrByID(
      "4006004_0", "04006004-0072")/*自定义项2*/),
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
      "4006004_0", "04006004-0225")/*自定义项19*/),
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

  VBDEF20(SaleInvoiceBVO.VBDEF20, NCLangRes4VoTransl.getNCLangRes().getStrByID(
      "4006004_0", "04006004-0073")/*自定义项20*/);

  // 单据类型
  private String key;

  // 单据名称
  private String name;

  private InvoiceCombindefrule(String key, String name) {
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
