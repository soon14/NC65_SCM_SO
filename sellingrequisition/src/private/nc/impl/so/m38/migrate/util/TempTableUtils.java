package nc.impl.so.m38.migrate.util;


import java.util.ArrayList;
import java.util.List;

import nc.impl.pubapp.pattern.database.TempTable;
import nc.vo.pub.JavaType;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

public class TempTableUtils {
  private static final int MAX_IN_COUNT = 100;
  private static final String TEMP_TABLE_PREFIX = "TEMP_PREORDER_5H1";
  public static String buildSQL(String name, String[] ids) {
    SqlBuilder sql = new SqlBuilder();
    int length = ids.length;
    if (length <= MAX_IN_COUNT) {
      sql.append(name, ids);
    } else {
      sql.append(name);
      sql.append(" in ");
      sql.startParentheses();
      sql.append(" select id1 from ");
      String temptable = get(ids, TEMP_TABLE_PREFIX);
      sql.append(temptable);
      sql.endParentheses();
    }
    return sql.toString();
  }

  private static String get(String[] ids, String tableName) {
    List<List<Object>> data = new ArrayList<List<Object>>();

    int length = ids.length;
    for (int i = 0; i < length; i++) {
      List<Object> row = new ArrayList<Object>();
      data.add(row);
      row.add(ids[i]);
    }
    String[] columns = { "id1" };
    String[] columnTypes = { "VARCHAR2(20)" };
    JavaType[] types = new JavaType[] { JavaType.String };

    TempTable dao = new TempTable();
    String name = dao.getTempTable(tableName, columns, columnTypes,
        columns, types, data);
    return name;
  }
}
