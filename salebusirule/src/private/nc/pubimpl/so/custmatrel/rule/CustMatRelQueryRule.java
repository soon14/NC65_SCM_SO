package nc.pubimpl.so.custmatrel.rule;

import java.util.ArrayList;
import java.util.List;

import nc.impl.pubapp.env.BSContext;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.pubitf.so.custmatrel.CustMatRelParaVO;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.so.custmatrel.entity.CustMatRelBVO;
import nc.vo.so.custmatrel.entity.CustMatRelHVO;
import nc.vo.so.pub.util.BaseSaleClassUtil;

/**
 * 查询匹配记录
 * 
 * @since 6.0
 * @version 2011-4-15 上午10:21:14
 * @author 祝会征
 */
public class CustMatRelQueryRule {
  private String table_b = "so_custmatrel_b";

  // 客户与物料主表
  private String table_h = "so_custmatrel";

  // 临时表
  private String tempTable;

  /**
   * 
   * @param temptablename
   * @return
   */
  public CustMatRelParaVO[] queryCustMatRelParaVO(String temptablename) {
    this.tempTable = temptablename;
    String querysql = this.getQuerySql();
    DataAccessUtils bo = new DataAccessUtils();
    IRowSet rowset = bo.query(querysql);
    List<CustMatRelParaVO> listvo = new ArrayList<CustMatRelParaVO>();
    while (rowset.next()) {
      CustMatRelParaVO vo = new CustMatRelParaVO();
      vo.setParaindex(rowset.getInteger(0));
      vo.setAllowsale(rowset.getInteger(1));
      vo.setPk_custmatrel_b(rowset.getString(2));
      vo.setExclude(rowset.getUFBoolean(3));
      vo.setCprioritycode(rowset.getString(4));
      listvo.add(vo);
    }
    CustMatRelParaVO[] vos = new CustMatRelParaVO[listvo.size()];
    listvo.toArray(vos);
    return vos;
  }

  private String getJoinString(String tablename, String col) {
    return tablename + "." + col;
  }

  private String getQuerySql() {
    StringBuffer sql = new StringBuffer();
    this.getSelectSql(sql);
    sql.append(" from " + this.table_h + "," + this.table_b + ","
        + this.tempTable);
    this.getWhereSql(sql);
    return sql.toString();
  }

  private void getSelectSql(StringBuffer sql) {
    sql.append("select "
        + this.getJoinString(this.tempTable, CustMatRelParaVO.PARAINDEX));
    sql.append("," + this.getJoinString(this.table_h, CustMatRelHVO.ALLOWSALE));
    sql.append(","
        + this.getJoinString(this.table_b, CustMatRelBVO.PK_CUSTMATREL_B));
    sql.append("," + this.getJoinString(this.table_b, CustMatRelBVO.EXCLUDE));
    sql.append("," + this.getJoinString(this.table_b, CustMatRelBVO.CPRIORITYCODE));
  }

  private void getWhereSql(StringBuffer sql) {
    sql.append(" where "
        + this.getJoinString(this.table_h, CustMatRelHVO.PK_CUSTMATREL));
    sql.append(" = "
        + this.getJoinString(this.table_b, CustMatRelBVO.PK_CUSTMATREL));
    sql.append(" and " + this.getJoinString(this.table_h, CustMatRelHVO.PK_ORG)
        + "=" + this.getJoinString(this.tempTable, CustMatRelParaVO.PK_ORG));
    sql.append(" and ( "
        + this.getJoinString(this.table_b, CustMatRelBVO.PK_MATERIAL) + "="
        + this.getJoinString(this.tempTable, CustMatRelParaVO.PK_MATERIAL)
        + " or " + this.getJoinString(this.table_b, CustMatRelBVO.PK_MATERIAL)
        + "= '~')");
    this.joinCustAndMatClass(sql);
    sql.append(" and "
        + this.getJoinString(this.table_h, CustMatRelHVO.DR + "=0"));
    sql.append(" and "
        + this.getJoinString(this.table_b, CustMatRelBVO.DR + "=0"));
    sql.append(" order by "
        + this.getJoinString(this.tempTable, CustMatRelParaVO.PARAINDEX));
    sql.append(","
        + this.getJoinString(this.table_b, CustMatRelBVO.CPRIORITYCODE));
    sql.append(" desc ");
  }

  /**
   * 拼写物料和客户基本分类的查询条件
   * 
   * @param sql
   */
  private void joinCustAndMatClass(StringBuffer sql) {
    String pk_group = BSContext.getInstance().getGroupID();
    if (BaseSaleClassUtil.isMarBaseClass(pk_group)) {
      sql.append(" and ("
          + this
              .getJoinString(this.table_b, CustMatRelBVO.PK_MATERIALBASECLASS)
          + "="
          + this.getJoinString(this.tempTable, "pk_materialclass")
          + " or "
          + this
              .getJoinString(this.table_b, CustMatRelBVO.PK_MATERIALBASECLASS)
          + "='~')");
    }
    else {
      sql.append(" and ("
          + this
              .getJoinString(this.table_b, CustMatRelBVO.PK_MATERIALSALECLASS)
          + "="
          + this.getJoinString(this.tempTable, "pk_materialclass")
          + " or "
          + this
              .getJoinString(this.table_b, CustMatRelBVO.PK_MATERIALSALECLASS)
          + "='~')");
    }
    sql.append(" and ("
        + this.getJoinString(this.table_b, CustMatRelBVO.PK_CUSTOMER) + "="
        + this.getJoinString(this.tempTable, CustMatRelParaVO.PK_CUSTOMER)
        + " or " + this.getJoinString(this.table_b, CustMatRelBVO.PK_CUSTOMER)
        + "='~')");
    if (BaseSaleClassUtil.isCustBaseClass(pk_group)) {
      sql.append(" and ("
          + this.getJoinString(this.table_b, CustMatRelBVO.PK_CUSTBASECLASS)
          + "=" + this.getJoinString(this.tempTable, "pk_custclass") + " or "
          + this.getJoinString(this.table_b, CustMatRelBVO.PK_CUSTBASECLASS)
          + "='~')");
    }
    else {
      sql.append(" and ("
          + this.getJoinString(this.table_b, CustMatRelBVO.PK_CUSTSALECLASS)
          + "=" + this.getJoinString(this.tempTable, "pk_custclass") + " or "
          + this.getJoinString(this.table_b, CustMatRelBVO.PK_CUSTSALECLASS)
          + "='~')");
    }
  }
}
