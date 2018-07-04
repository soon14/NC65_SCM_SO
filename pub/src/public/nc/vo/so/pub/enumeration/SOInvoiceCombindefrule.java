package nc.vo.so.pub.enumeration;

import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.so.entry.SaleInvoiceBVOCode;

public enum SOInvoiceCombindefrule {

  VBDEF1(SaleInvoiceBVOCode.VBDEF1, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0061")/*自定义项1*/),
  VBDEF2(SaleInvoiceBVOCode.VBDEF2, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0072")/*自定义项2*/),
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
      .getStrByID("4006004_0", "04006004-0225")/*自定义项19*/),
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

  VBDEF20(SaleInvoiceBVOCode.VBDEF20, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0073")/*自定义项20*/);

  // 单据类型
  private String key;

  // 单据名称
  private String name;

  private SOInvoiceCombindefrule(String key, String name) {
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
