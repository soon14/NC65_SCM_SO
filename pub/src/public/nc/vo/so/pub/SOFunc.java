package nc.vo.so.pub;

/**
 * 销售功能节点枚举
 * 
 * @since 6.1
 * @version 2012-11-29 11:15:49
 * @author 冯加彬
 */
public enum SOFunc {

  /**
   * 移动应用-销售订单
   */
  A03002("A03002", "销售订单")/* -=notranslate=- */,
  /**
   * 移动应用-销售查询
   */
  A03006("A03006", "销售查询")/* -=notranslate=- */,
  /**
   * 移动应用-销售分析
   */
  A04002("A04002", "销售分析")/* -=notranslate=- */,
  /**
   * 销售订单
   */
  N40060301("40060301", "销售订单")/* -=notranslate=- */,
  /**
   * 发货单
   */
  N40060402("40060402", "发货单")/* -=notranslate=- */,

  /**
   * 销售发票
   */
  N40060501("40060501", "销售发票")/* -=notranslate=- */,

  /**
   * 预订单安排
   */
  N40060202("40060202", "预订单安排")/* -=notranslate=- */,
  /**
   * 发货安排
   */
  N40060401("40060401", "发货安排")/* -=notranslate=- */,
  /**
   * 调拨订单
   */
  N40093010("40093010", "调拨订单")/* -=notranslate=- */;

  private String funccode;

  private String funcname;

  private SOFunc(String funccode, String funcname) {
    this.funccode = funccode;
    this.funcname = funcname;
  }

  /**
   * 获得节点编码
   * 
   * @return 功能节点号
   */
  public String getCode() {
    return this.funccode;
  }

  /**
   * 获得节点名称
   * 
   * @return 功能节点中文名称
   */
  public String getName() {
    return this.funcname;
  }
}
