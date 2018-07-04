package nc.vo.so.pub;

/**
 * 销售管理库表枚举类
 * 
 * @since 6.1
 * @version 2012-11-29 09:29:07
 * @author 冯加彬
 */
public enum SOTable {

  /**
   * 买赠主表
   */
  BUYLARGESS("so_buylargess"),
  /**
   * 买赠子表
   */
  BUYLARGESS_B("so_buylargess_b"),
  /**
   * 报价单交易类型
   */
  SALEQUOTATIONTYPE("so_salequotationtype"),

  /**
   * 报价单主表
   */
  SALEQUOTATION("so_salequotation"),

  /**
   * 报价单子表
   */
  SALEQUOTATION_B("so_salequotation_b"),

  /**
   * 预订单交易类型
   */
  M38TRANTYPE("so_m38trantype"),

  /**
   * 预订单主表
   */
  PREORDER("so_preorder"),

  /**
   * 预订单子表
   */
  PREORDER_B("so_preorder_b"),

  /**
   * 销售订单主表
   */
  SALEORDER("so_saleorder"),

  /**
   * 销售订单子表
   */
  SALEORDER_B("so_saleorder_b"),

  /**
   * 销售订单执行表
   */
  SALEORDER_EXE("so_saleorder_exe"),

  /**
   * 销售订单修订主表
   */
  ORDERHISTORY("so_orderhistory"),

  /**
   * 销售订单修订子表
   */
  ORDERHISTORY_B("so_orderhistory_b"),

  /**
   * 发货单主表
   */
  DELIVERY("so_delivery"),

  /**
   * 发货单子表
   */
  DELIVERY_B("so_delivery_b"),

  /**
   * 发货单质检表
   */
  DELIVERY_CHECK("so_delivery_check"),

  /**
   * 销售发票主表
   */
  SALEINVOICE("so_saleinvoice"),

  /**
   * 销售发票子表
   */
  SALEINVOICE_B("so_saleinvoice_b"),

  /**
   * 销售发票结算单主表
   */
  SQUAREINV("so_squareinv"),

  /**
   * 销售发票结算单子表
   */
  SQUAREINV_B("so_squareinv_b"),

  /**
   * 销售发票结算单明细表
   */
  SQUAREINV_D("so_squareinv_d"),

  /**
   * 销售出库结算单主表
   */
  SQUAREOUT("so_squareout"),

  /**
   * 销售出库结算单子表
   */
  SQUAREOUT_B("so_squareout_b"),

  /**
   * 销售出库结算单明细表
   */
  SQUAREOUT_D("so_squareout_d"),

  /**
   * 途损结算单主表
   */
  SQUAREWAS("so_squarewas"),

  /**
   * 途损结算单子表
   */
  SQUAREWAS_B("so_squarewas_b"),

  /**
   * 途损结算单明细表
   */
  SQUAREWAS_D("so_squarewas_d"),

  /**
   * 销售公用ID临时表1
   * 使用场景：数组字段超过200时公共临时表名
   */
  TMP_SO_ID1("tmp_so_id1"),
  /**
   * 销售公用ID临时表2
   * 使用场景：同时有两个数组字段超过200时第2个公共临时表名
   */
  TMP_SO_ID2("tmp_so_id2"),

  /**
   * 退货政策匹配参数临时表
   * 使用场景：退货政策匹配时存放扩展参数
   */
  TMP_SO_RETURNASSIGN("tmp_so_retasgn61"),

  /**
   * 销售组织ID临时表
   * 使用场景：销售订单、发票、订单收款核销为返利提供取数函数时，存放销售组织参数
   */
  TMP_SO_SALEORG("tmp_so_saleorg"),

  /**
   * 订单类型ID临时表
   * 使用场景：销售订单、发票、订单收款核销为返利提供取数函数时，存放订单类型参数
   */
  TMP_SO_ORDERTYPE("tmp_so_ordertype"),

  /**
   * 订单客户ID临时表
   * 使用场景：销售订单、发票、订单收款核销为返利提供取数函数时，存放订单客户参数
   */
  TMP_SO_ORDERCUST("tmp_so_ordercust"),

  /**
   * 开票客户ID临时表
   * 使用场景：销售订单、发票、订单收款核销为返利提供取数函数时，存放开票客户参数
   */
  TMP_SO_INVCUST("tmp_so_invcust"),

  /**
   * 结算方式ID临时表
   * 使用场景：销售订单、发票、订单收款核销为返利提供取数函数时，存放结算方式参数
   */
  TMP_SO_BALTYPE("tmp_so_baltype"),
  /**
   * 价格项ID临时表
   * 使用场景：销售订单、发票、订单收款核销为返利提供取数函数时，存放价格项参数
   */
  TMP_SO_PRCITEM("tmp_so_prcitem"),
  /**
   * 计划独立需求使用临时表
   * 使用场景：计划独立需求存放生产制造参数
   */
//  TMP_SO_PID("tmp_so_pid"), for V636 由于临时表增加了一个特征码维度，所以需要修改表名 jilu
  TMP_SO_PID("tmp_so_pid2"),
  /**
   * 订单毛利分析分页临时表
   * 使用场景：订单毛利分析报表存放分页订单明细数据
   */
  TMP_SO_ORDERPFPAGE("tmp_so_orderpfpage"),
  /**
   * 订单毛利分析数据加工结果临时表
   * 使用场景：订单毛利分析报表存放毛利计算结果数据
   */
  TMP_SO_ORDERPROFIT("tmp_so_orderprofit"),
  /**
   * 出库毛利分析分页临时表
   * 使用场景：出库毛利分析报表存放分页出库单明细数据
   */
  TMP_SO_OUTPFPAGE("tmp_so_outpfpage"),
  /**
   * 出库毛利分析数据加工结果临时表
   * 使用场景：出库毛利分析报表存放毛利计算结果数据
   */
  TMP_SO_OUTPROFIT("tmp_so_outprofit"),

  /**
   * 订单执行汇总分页临时表
   * 使用场景：订单执行汇总报表存放分页订单明细数据
   */
  TMP_SO_ORDERSUMPAGE("tmp_so_ordersumpage"),
  /**
   * 订单执行汇总数据加工结果临时表
   * 使用场景：订单执行汇总表存放数据加工执行结果数据
   */
  TMP_SO_ORDERSUMMARY("tmp_so_ordersummary"),
  /**
   * 发票执行汇总分页临时表
   * 使用场景：发票执行汇总报表存放分页发票明细数据
   */
  TMP_SO_INVSUMPAGE("tmp_so_invsumpage"),
  /**
   * 发票执行汇总数据加工结果临时表
   * 使用场景：发票执行汇总表存放数据加工执行结果数据
   */
  TMP_SO_INVSUMMARY("tmp_so_invsummary"),
  /**
   * 出库执行汇总分页临时表
   * 使用场景：出库执行汇总报表存放分页出库明细数据
   */
  TMP_SO_OUTSUMPAGE("tmp_so_outsumpage"),
  /**
   * 出库执行汇总数据加工结果临时表
   * 使用场景：出库执行汇总表存放数据加工执行结果数据
   */
  TMP_SO_OUTSUMMARY("tmp_so_outsummary"),
  /**
   * 综合毛利分析数据加工结果临时表
   * 使用场景：综合毛利分析报表存放数据加工执行结果数据
   */
  TMP_SO_MULTIPLEPROFIT("tmp_so_multprofit"),
  /**
   * 买赠匹配使用临时表
   * 使用场景：买赠匹配存放匹配参数
   */
  TMP_SO_LARMATCH("tmp_so_larmatch"),

  TMP_SO_PRCPROMTYPE("tmp_so_prcpromtype");

  private String name;

  private SOTable(String name) {
    this.name = name;
  }

  /**
   * 获得表名
   * 
   * @return 表名
   */
  public String getName() {
    return this.name;
  }
}
