package nc.vo.so.m33.pub.constant;

public class QueryFlag {

  // 查询待暂估应收数据
  public static final int ET = 5;

  // 查询已暂估应收数据
  public static final int ETD = 6;

  /** 销售结算相关查询使用 *******************************/
  // 查询待出库对冲数据
  public static final int OUTRUSH = 4;

  // 查询待计入发出商品数据
  public static final int REG = 7;

  // 查询已计入发出商品数据
  public static final int REGD = 8;

  // 查询待结算数据
  public static final int SQUARE = 0;

  // 查询已结算数据
  public static final int SQUARED = 1;

  // 销售结算前台查询模板逻辑区"结算类型"字段名
  public static final String SQUAREFLAG = "squareflag";

  // 查询已出库对冲蓝字出库单数据
  public static final int UNOUTRUSHBLUE = 2;

  // 查询已出库对冲红字出库单数据
  public static final int UNOUTRUSHRED = 3;
  /** 销售结算相关查询使用 *******************************/

}
