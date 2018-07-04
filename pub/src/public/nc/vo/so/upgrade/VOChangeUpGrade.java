package nc.vo.so.upgrade;

import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

/**
 * VO转换违规主键升级处理
 * 
 * @since 6.36
 * @version 2015-3-24 下午7:23:14
 * @author 刘景
 */
public class VOChangeUpGrade {

  private final static String vochangepkname = "pk_vochange";

  private final static String vochange_b_name = "pk_vochange_b";

  private final static String vochange_s_name = "pk_vochange_s";

  private final static String tmp_vochange = "tmp_vochange";

  private final static String tmp_vochange_b = "tmp_vochange_b";

  private final static String tmp_vochange_s = "tmp_vochange_s";

  private final static String pk_group = "global00000000000000";

  private final static DataAccessUtils accessutil = new DataAccessUtils();

  private VOChangeUpGrade() {

  }

  public static void upGrade(String[] pk_vochange, String[] pk_vochange_b,
      String[] pk_vochange_s) {
    if (pk_vochange != null && pk_vochange.length > 0) {
      deleteVOChange(pk_vochange);
    }
    if (pk_vochange_b != null && pk_vochange_b.length > 0) {
      deleteVOChange_B(pk_vochange_b);
    }
    if (pk_vochange_b != null && pk_vochange_b.length > 0) {
      deleteVOChange_S(pk_vochange_b);
    }
  }

  public static void deleteVOChange(String[] pk_vochange) {
    SqlBuilder sql = new SqlBuilder();
    sql.append("delete pub_vochange where ");
    IDExQueryBuilder iq = new IDExQueryBuilder(tmp_vochange);
    sql.append(iq.buildSQL(vochangepkname, pk_vochange));
    sql.append(" and ");
    sql.append("pk_group", pk_group);
    accessutil.update(sql.toString());
  }

  public static void deleteVOChange_B(String[] pk_vochange_b) {
    SqlBuilder sql = new SqlBuilder();
    sql.append("delete pub_vochange_b where ");
    IDExQueryBuilder iq = new IDExQueryBuilder(tmp_vochange_b);
    sql.append(iq.buildSQL(vochange_b_name, pk_vochange_b));
    accessutil.update(sql.toString());
  }

  public static void deleteVOChange_S(String[] pk_vochange_s) {
    SqlBuilder sql = new SqlBuilder();
    sql.append("delete pub_vochange_s where ");
    IDExQueryBuilder iq = new IDExQueryBuilder(tmp_vochange_s);
    sql.append(iq.buildSQL(vochange_s_name, pk_vochange_s));
    accessutil.update(sql.toString());
  }
}
