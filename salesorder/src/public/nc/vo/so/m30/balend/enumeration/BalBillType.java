package nc.vo.so.m30.balend.enumeration;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>销售订单结算关闭组件结算单据类型
 * </ul>
 * <p>
 * 
 * @version 本版本号 6.0
 * @since
 * @author fengjb
 * @time 2010-7-13 下午06:58:41
 */
public enum BalBillType {

  // 出库单、发票都参与成本结算
  BOTHCOST("bothcost"),

  // 出库单、发票都参与应收结算
  BOTHINCOME("bothincome"),

  // 销售出库单发票都不参与结算
  NONEBAL("nonebal"),

  // 只有出库单参与成本结算
  OUTCOST("outcost"),

  // 只有出库单参与应收结算
  OUTINCOME("outincome"),

  // 只有发票上配置成本结算
  VOICECOST("voicecost"),

  // 只有发票上配置应收结算
  VOICEINCOME("voiceincome"),
  // 出库单手工结算时，只关心发票参与成本结算

  OnlyVOICECOST("onlyvoicecost"),
  // 出库单手工结算时，只关心发票参与收入结算

  OnlyVOICEINCOME("onlyvoiceincome");

  private String code;

  private BalBillType(String code) {
    this.code = code;
  }

  public String getCode() {
    return this.code;
  }

  public boolean equalsValue(BalBillType otherbaltype) {
    if (null == otherbaltype) {
      return false;
    }
    return this.getCode().equals(otherbaltype.getCode());
  }
}
