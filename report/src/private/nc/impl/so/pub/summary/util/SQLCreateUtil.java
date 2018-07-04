package nc.impl.so.pub.summary.util;

import nc.vo.pubapp.pattern.pub.SqlBuilder;

/**
 * 报表sql创建工具类
 * 
 * @since 6.36
 * @version 2015-6-24 下午1:57:41
 * @author 刘景
 */
public class SQLCreateUtil {

  /**
   * 用来创建高精度除法运算sql片段</br>
   * 
   * 错误处理：
   * select sum(5.00000000)/sum(0.30000000) from so_saleorder 返回的结果：16.666666</br>
   * 
   * 正确处理：
   * select cast(sum(5.00000000)as decimal(28,8))/cast(sum(0.30000000)as
   * decimal(28,8)) from so_saleorder 返回结果为：16.6666666666
   * 
   * @param field1 除数字段名称
   * @param field2 被除数字段名称
   * @return
   */
  public static String getSumDivsql(String field1, String field2) {
    SqlBuilder sql = new SqlBuilder();
    sql.append(" cast(");
    sql.append("sum(");
    sql.append(field1);
    sql.append(")");
    sql.append(" as decimal(28,8))");

    sql.append("/");

    sql.append(" cast(");
    sql.append(" sum(");
    sql.append(field2);
    sql.append(")");
    sql.append(" as decimal(28,8))");
    return sql.toString();
  }

}
