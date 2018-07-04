package nc.pubimpl.so.deptmatrel.rule;

import java.util.ArrayList;
import java.util.List;

import nc.impl.pubapp.env.BSContext;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.pubitf.so.deptmatrel.DeptMatRelParaVO;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.so.depmatrel.entity.DepMatRelBVO;
import nc.vo.so.depmatrel.entity.DepMatRelHVO;
import nc.vo.so.pub.util.BaseSaleClassUtil;

/**
 * 部门、业务员、物料：查询匹配记录
 * 
 * @since 6.0
 * @version 2011-4-15 上午10:21:14
 * @author 祝会征
 */
public class DeptMatRelQueryRule {
  private String table_b = "so_depmatrel_b";

  // 客户与物料主表
  private String table_h = "so_depmatrel";

  // 临时表
  private String tempTable;

  /**
   * 
   * @param temptablename
   * @return
   */
  public DeptMatRelParaVO[] queryDeptMatRelParaVO(String temptablename) {
    this.tempTable = temptablename;
    String querysql = this.getQuerySql();
    DataAccessUtils bo = new DataAccessUtils();
    IRowSet rowset = bo.query(querysql);
    List<DeptMatRelParaVO> listvo = new ArrayList<DeptMatRelParaVO>();
    while (rowset.next()) {
      DeptMatRelParaVO vo = new DeptMatRelParaVO();
      vo.setParaindex(rowset.getInteger(0));
      vo.setAllowsale(rowset.getInteger(1));
      vo.setPk_depmatrel_b(rowset.getString(2));
      vo.setExclude(rowset.getUFBoolean(3));
      vo.setCprioritycode(rowset.getString(4));
      listvo.add(vo);
    }
    DeptMatRelParaVO[] vos = new DeptMatRelParaVO[listvo.size()];
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
        + this.getJoinString(this.tempTable, DeptMatRelParaVO.PARAINDEX));
    sql.append("," + this.getJoinString(this.table_h, DepMatRelHVO.ALLOWSALE));
    sql.append(","
        + this.getJoinString(this.table_b, DepMatRelBVO.PK_DEPMATREL_B));
    sql.append("," + this.getJoinString(this.table_b, DepMatRelBVO.EXCLUDE));
    sql.append("," + this.getJoinString(this.table_b, DepMatRelBVO.CPRIORITYCODE));
  }

  private void getWhereSql(StringBuffer sql) {
    sql.append(" where "
        + this.getJoinString(this.table_h, DepMatRelHVO.PK_DEPMATREL));
    sql.append(" = "
        + this.getJoinString(this.table_b, DepMatRelBVO.PK_DEPMATREL));
    sql.append(" and " + this.getJoinString(this.table_h, DepMatRelHVO.PK_ORG)
        + "=" + this.getJoinString(this.tempTable, DeptMatRelParaVO.PK_ORG));
    sql.append(" and ("
        + this.getJoinString(this.table_b, DepMatRelBVO.CEMPLOYEEID));
    sql.append(" = "
        + this.getJoinString(this.tempTable, DeptMatRelParaVO.CEMPLOYEEID)
        + " or "+this.getJoinString(this.table_b, DepMatRelBVO.CEMPLOYEEID)
        + "= '~')");
    sql.append(" and " + this.getJoinString(this.table_b, DepMatRelBVO.PK_DEPT));
    sql.append(" = "
        + this.getJoinString(this.tempTable, DeptMatRelParaVO.PK_DEPT));
    sql.append(" and ("
        + this.getJoinString(this.table_b, DepMatRelBVO.PK_MATERIAL) + "="
        + this.getJoinString(this.tempTable, DeptMatRelParaVO.PK_MATERIAL)
        + " or "+this.getJoinString(this.table_b, DepMatRelBVO.PK_MATERIAL)
        +"= '~')");
    this.joinCustAndMatClass(sql);
    sql.append(" and "
        + this.getJoinString(this.table_h, DepMatRelHVO.DR + "=0"));
    sql.append(" and "
        + this.getJoinString(this.table_b, DepMatRelBVO.DR + "=0"));
    sql.append(" order by "
        + this.getJoinString(this.tempTable, DeptMatRelParaVO.PARAINDEX));
    sql.append(","
        + this.getJoinString(this.table_b, DepMatRelBVO.CPRIORITYCODE));
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
          + this.getJoinString(this.table_b, DepMatRelBVO.PK_MATERIALBASECLASS)
          + "=" + this.getJoinString(this.tempTable, "pk_materialclass")
          +" or "+this.getJoinString(this.table_b, DepMatRelBVO.PK_MATERIALBASECLASS)
          +"= '~')");
    }
    else {
      sql.append(" and ("
          + this.getJoinString(this.table_b, DepMatRelBVO.PK_MATERIALSALECLASS)
          + "=" + this.getJoinString(this.tempTable, "pk_materialclass")
          +" or "+this.getJoinString(this.table_b, DepMatRelBVO.PK_MATERIALSALECLASS)
          +"= '~')");
    }
  }
}
