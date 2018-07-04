package nc.vo.so.pub.res;

/**
 * 销售管理的参数列表
 * 
 * @author 祝会征
 */
public enum ParameterList {
  SO01("SO01", "订单限行"), /* -=notranslate=- */
  SO02("SO02", "配置销售是否统一报价"), /* -=notranslate=- */
  SO03("SO03", "订单表头记录预收款比例和预收款金额的优先规则"), /* -=notranslate=- */
  SO04("SO04", "超销售订单出库容差控制方式"), /* -=notranslate=- */
  SO05("SO05", "超订单发货容差控制方式"), /* -=notranslate=- */
  SO06("SO06", "超发货单出库容差控制方式"), /* -=notranslate=- */
  SO07("SO07", "销售订单开票控制方式"), /* -=notranslate=- */
  SO08("SO08", "销售出库单开票控制方式"), /* -=notranslate=- */
  SO10("SO10", "订单关闭时用订单出库数量修订销售合同执行数量"), /* -=notranslate=- */
  SO11("SO11", "订单收款核销条件"), /* -=notranslate=- */
  SO12("SO12", "销售出库单应收结算允许修改客户和单价"), /* -=notranslate=- */
  SO13("SO13", "补货/直运安排容差比率"), /* -=notranslate=- */
  SO14("SO14", "发票与冲应收单自动费用冲抵"), /* -=notranslate=- */
  SO15("SO15", "冲应收单费用冲抵时可以冲减发票金额的比例"), /* -=notranslate=- */
  SO16("SO16", "基于签收数量开票的业务流程"), /* -=notranslate=- */
  SO17("SO17", "发票限行"), /* -=notranslate=- */
  SO18("SO18", "销售订单分单打印规则"), /* -=notranslate=- */
  SO19("SO19", "发货单分单打印规则"), /* -=notranslate=- */
  SO20("SO20", "赠品是否开票"), /* -=notranslate=- */
  SO21("SO21", "销售询价触发条件"), /* -=notranslate=- */
  SO22("SO22", "存储询价过程明细"), /* -=notranslate=- */
  SO26("SO26", "超预订单安排订单控制方式"), /* -=notranslate=- */
  SO27("SO27", "发票表体默认显示方式"), /* -=notranslate=- */
  SO28("SO28", "发票汇总规则"), /* -=notranslate=- */
  SO29("SO29", "报价限行"), /* -=notranslate=- */
  SO30("SO30", "现销处理弹出详细核销界面"), /* -=notranslate=- */
  SO31("SO31", "销售发票行合并税额计算"),/* -=notranslate=- */
  SO32("SO32", "销售订单是否支持审批中修改"),/* -=notranslate=- */
  SO33("SO33", "销售发票是否支持审批中修改");/* -=notranslate=- */

  /**
   * #号标记符
   */
  public static final String BIGSPLITKEY = "#";

  /**
   * 逗号标记符
   */
  public static final String SPLITKEY = ",";

  /**
   * _V标记符
   */
  public static final String SUFFIX = "_V";

  /**
   * 美元符号
   */
  public static final String DOLLER = "$";

  // 参数编码
  private String code;

  // 参数名称
  private String name;

  private ParameterList(String code, String name) {
    this.code = code;
    this.name = name;
  }

  public String getCode() {
    return this.code;
  }

  public String getName() {
    return this.name;
  }
}
