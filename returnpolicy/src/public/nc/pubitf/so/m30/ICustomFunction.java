package nc.pubitf.so.m30;

import nc.vo.pub.BusinessException;

public interface ICustomFunction {

  /**
   * 函数名称：申请退货单据金额
   * 输入参数：单据头的客户编码(解析时的变量)
   * 返回值：当前退货申请单的客户的当前单据各行价税合计金额之和。
   * 应用实例：选择业务函数"申请退货单据金额"，表示条件为"如果当前退货申请单的客户的申请退货总金额"。
   */
  double getAppRetBillMny(ReturnAssignMatchVO paravo);

  /**
   * 函数名称：申请退货数量
   * 输入参数：单据行的存货编码＋自由项(解析时的变量)
   * 返回值：当前行存货的申请退货的主数量。
   * 应用实例：选择业务函数"申请退货数量"，表示条件为"如果当前退货申请单的客户当前行存货的申请退货数量"
   */
  double getAppRetNum(ReturnAssignMatchVO paravo);

  /*
   * 函数名称：存货编码
   * 输入参数：单据行的存货编码 (解析时的变量)
   * 返回值：存货编码。
   * 应用实例：选择业务函数"存货"，选择"＝"，
   * 在字符串处输入"1876A"表示条件为"如果当前行存货编码为'1876A'。"。一般适用于某种存货准退或不准退等。
   */
  String getInvCode(ReturnAssignMatchVO paravo);

  /**
   * 函数名称：存货生命周期
   * 输入参数：单据行的存货编码＋自由项(解析时的变量)
   * 返回值：存货管理档案中的存货生命周期（0，1，2，3）。
   * 应用实例：选择业务函数"存货生命周期"，选择"＝"，在字符串处输入"0"表示条件为"如果当前行存货的生命周期为0(即试销期)"。
   */
  int getInvLifePrd(ReturnAssignMatchVO paravo) throws BusinessException;

  /**
   * 函数名称：销售订单金额（时间区间）
   * 输入参数：单据头的客户编码(解析时的变量) ，单据头的单据日期(解析时的变量)，时间区间（函数定义时的常量）
   * 返回值：日期区间在"单据日期"与"单据日期－时间区间"的所有销售订单的本币价税合计之和。
   * 应用实例：选择业务函数"销售订单金额"，选择"（"，在数字处输入"10"，选择"）"，构成函数"销售订单金额（10）"表示条件为
   * "如果当前单据的客户在单据日期往前倒推10日内的实际销售订单金额之和"（形成where子句的between (today-10) and
   * today）。
   */
  double getOrderMny(int iDays, ReturnAssignMatchVO paravo);

  /**
   * 函数名称：实际出库数量（时间区间）
   * 输入参数：单据行的存货编码＋自由项(解析时的变量)，单据头的客户编码(解析时的变量)，单据头的单据日期(解析时的变量)，时间区间（函数定义时的常量参数
   * ）
   * 返回值：日期区间在"单据日期"与"单据日期－时间区间"的所有销售出库单的实际出库的数量之和。
   * 应用实例：选择业务函数"实际出库数量"，选择"（"，在数字处输入"10"，选择"）"，构成函数"实际出库数量（10）"表示条件为
   * "如果当前单据的客户当前行存货的在单据日期往前倒推10日内的实际出库数量之和"（形成where子句的between (today-10) and
   * today）。
   */
  double getOutNumber(int iDays, ReturnAssignMatchVO paravo);

  /**
   * 函数名称：退货申请单日期。
   */
  String getResBillDate(ReturnAssignMatchVO paravo);

  /**
   * 函数名称：退货原因类型
   * 输入参数：单据体的退货原因编码
   * 返回值：退货原因档案中的退货原因类型取值。
   * 应用实例：选择业务函数"退货原因类型"，选择"＝"，在字符串处输入"0"
   * (质量退货)，表示条件为"如果当前退货申请单的当前行存货的退货原因的退货原因类型为"0"（质量退货）。一般用于判断是否因质量原因退回等。
   */
  int getRetRsnType(ReturnAssignMatchVO paravo);

  /**
   * 函数名称：源发票日期
   */
  String getSaleInvoiceBillDate(ReturnAssignMatchVO paravo);

  /**
   * 函数名称：源订单日期
   */
  String getSaleOrderBillDate(ReturnAssignMatchVO paravo);

  /**
   * 函数名称：源出库单日期
   */
  String getSaleOutBillDate(ReturnAssignMatchVO paravo);

  /**
   * 函数名称：源发票日期
   */
  int getSourceInvoiceDays(ReturnAssignMatchVO paravo);

  /**
   * 函数名称：源发票日期
   */
  int getSourceOrderDays(ReturnAssignMatchVO paravo);

  /**
   * 函数名称：源发票日期
   */
  int getSourceOutDays(ReturnAssignMatchVO paravo);

  /**
   * 函数名称：赠品
   * 输入参数：单据体的存货编码(解析时的变量)，单据体行的"赠品"标记
   * 返回值："是"或"否"。
   * 应用实例：选择业务函数"赠品"，选择"＝"，在字符串处输入"是"，表示条件为"如果当前退货申请单的当前行存货的赠品标志为"是
   * "。一般用于判断是否赠品销售的退回（在参照销售订单生成退货申请单的情况下有效）。
   */
  boolean isLargessFlag(ReturnAssignMatchVO paravo);

  boolean judge(String strConditionCode, ReturnAssignMatchVO paravo);

}
