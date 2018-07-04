package nc.pubimpl.so.mbuylargess.pub;

import java.util.ArrayList;
import java.util.List;

import nc.vo.pub.JavaType;
import nc.vo.so.mbuylargess.match.BuyLargessMatchPara;

import nc.impl.pubapp.pattern.database.TempTable;

/**
 * 匹配买赠时创建参数临时表
 * 
 * @since 6.1
 * @version 2012-10-30 20:03:01
 * @author 冯加彬
 */
public class MatchParaTableCreate {

  /**
   * 创建参数临时表
   * 
   * @param tablename
   * @param paras
   * @return 临时表名
   */
  public String createParaTempTable(String tablename,
      BuyLargessMatchPara[] paras) {
    String[] columns = this.getColumns();
    List<List<Object>> listdata = new ArrayList<List<Object>>();
    for (BuyLargessMatchPara para : paras) {
      List<Object> rowdata = new ArrayList<Object>();
      for (String key : columns) {
        rowdata.add(para.getAttributeValue(key));
      }
      listdata.add(rowdata);
    }

    TempTable bo = new TempTable();

    String table =
        bo.getTempTable(tablename, columns, this.getColumnTypes(),
            this.getJavaTypes(), listdata);

    return table;
  }

  private String[] getColumns() {
    String[] columns =
        new String[] {
          BuyLargessMatchPara.PARAINDEX, BuyLargessMatchPara.CSALEORGID,
          BuyLargessMatchPara.CFATHERORGID, BuyLargessMatchPara.CMATERIALID,
          BuyLargessMatchPara.CMARBASECLID, BuyLargessMatchPara.CMARSALECLID,
          BuyLargessMatchPara.CCUSTOMERID, BuyLargessMatchPara.CCUSTCLID,
          BuyLargessMatchPara.CCUSTSALECLID, BuyLargessMatchPara.CCURRTYPEID,
          BuyLargessMatchPara.CASSUNITID, BuyLargessMatchPara.NBILLNUM,
          BuyLargessMatchPara.DBILLDATE

        };
    return columns;
  }

  private JavaType[] getJavaTypes() {
    JavaType[] types =
        new JavaType[] {
          JavaType.Integer, JavaType.String, JavaType.String, JavaType.String,
          JavaType.String, JavaType.String, JavaType.String, JavaType.String,
          JavaType.String, JavaType.String, JavaType.String, JavaType.UFDouble,
          JavaType.UFDate
        };
    return types;
  }

  private String[] getColumnTypes() {
    String[] columnTypes =
        new String[] {
          "integer not null", "varchar(20)", "varchar(20)", "varchar(20)",
          "varchar(20)", "varchar(20)", "varchar(20)", "varchar(20)",
          "varchar(20)", "varchar(20)", "varchar(20)", "decimal(28,8)",
          "char(19)"
        };
    return columnTypes;
  }
}
