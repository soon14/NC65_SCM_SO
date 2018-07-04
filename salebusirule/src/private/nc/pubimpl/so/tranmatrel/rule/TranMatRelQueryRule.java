package nc.pubimpl.so.tranmatrel.rule;

import java.util.ArrayList;
import java.util.List;

import nc.impl.pubapp.env.BSContext;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.pubitf.so.tanmatrel.TranMatRelParaVO;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.so.pub.util.BaseSaleClassUtil;
import nc.vo.so.tranmatrel.entity.TranMatRelBVO;
import nc.vo.so.tranmatrel.entity.TranMatRelHVO;

/**
 * 单据类型与物料关系定义:查询匹配记录
 * 
 * @since 6.0
 * @version 2011-4-15 上午10:21:14
 * @author 祝会征
 */
public class TranMatRelQueryRule {
  private String table_b = "so_tranmatrel_b";

  // 客户与物料主表
  private String table_h = "so_tranmatrel";

  // 临时表
  private String tempTable;

  /**
   * 
   * @param temptablename
   * @param set
   * @return
   */
  public TranMatRelParaVO[] queryTranMatRelParaVO(String temptablename) {
    this.tempTable = temptablename;
    String querysql = this.getQuerySql();
    DataAccessUtils bo = new DataAccessUtils();
    IRowSet rowset = bo.query(querysql);
    List<TranMatRelParaVO> listvo = new ArrayList<TranMatRelParaVO>();
    while (rowset.next()) {
      TranMatRelParaVO vo = new TranMatRelParaVO();
      vo.setParaindex(rowset.getInteger(0));
      vo.setAllowsale(rowset.getInteger(1));
      vo.setPk_tranmatrel_b(rowset.getString(2));
      vo.setExclude(rowset.getUFBoolean(3));
      vo.setCprioritycode(rowset.getString(4));
      listvo.add(vo);
    }
    TranMatRelParaVO[] vos = new TranMatRelParaVO[listvo.size()];
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
        + this.getJoinString(this.tempTable, TranMatRelParaVO.PARAINDEX));
    sql.append("," + this.getJoinString(this.table_h, TranMatRelHVO.ALLOWSALE));
    sql.append(","
        + this.getJoinString(this.table_b, TranMatRelBVO.PK_TRANMATREL_B));
    sql.append("," + this.getJoinString(this.table_b, TranMatRelBVO.EXCLUDE));
    sql.append("," + this.getJoinString(this.table_b, TranMatRelBVO.CPRIORITYCODE));
  }

  private void getWhereSql(StringBuffer sql) {
    sql.append(" where "
        + this.getJoinString(this.table_h, TranMatRelHVO.PK_TRANMATREL));
    sql.append(" = "
        + this.getJoinString(this.table_b, TranMatRelBVO.PK_TRANMATREL));
    sql.append(" and "
        + this.getJoinString(this.table_b, TranMatRelBVO.TRANTYPE));
    sql.append(" = "
        + this.getJoinString(this.tempTable, TranMatRelParaVO.TRANTYPE));
    sql.append(" and ("
        + this.getJoinString(this.table_h, TranMatRelHVO.PK_ORG));
    sql.append("="
        + this.getJoinString(this.tempTable, TranMatRelParaVO.PK_ORG));
    sql.append(" or (" + this.getJoinString(this.table_h, TranMatRelHVO.PK_ORG));
    sql.append("="
        + this.getJoinString(this.tempTable, TranMatRelParaVO.PK_FATHERORG));
    sql.append(" and "
        + this.getJoinString(this.table_h, TranMatRelHVO.APPLYLOWER));
    sql.append(" ='Y'))");
    sql.append(" and ("
        + this.getJoinString(this.table_b, TranMatRelBVO.PK_MATERIAL) + "="
        + this.getJoinString(this.tempTable, TranMatRelParaVO.PK_MATERIAL)
        + " or "+this.getJoinString(this.table_b, TranMatRelBVO.PK_MATERIAL)
        +"= '~')");
    this.joinCustAndMatClass(sql);
    sql.append(" and "
        + this.getJoinString(this.table_h, TranMatRelHVO.DR + "=0"));
    sql.append(" and "
        + this.getJoinString(this.table_b, TranMatRelBVO.DR + "=0"));
    sql.append(" order by "
        + this.getJoinString(this.tempTable, TranMatRelParaVO.PARAINDEX));
    sql.append(","
        + this.getJoinString(this.table_b, TranMatRelBVO.CPRIORITYCODE));
    sql.append(" desc");
  }

  /**
   * 拼写物料分类的查询条件
   * 
   * @param sql
   */
  private void joinCustAndMatClass(StringBuffer sql) {
    String pk_group = BSContext.getInstance().getGroupID();
    if (BaseSaleClassUtil.isMarBaseClass(pk_group)) {
      sql.append(" and ("
          + this
              .getJoinString(this.table_b, TranMatRelBVO.PK_MATERIALBASECLASS)
          + "=" + this.getJoinString(this.tempTable, "pk_materialclass")
          + " or "+this.getJoinString(this.table_b, TranMatRelBVO.PK_MATERIALBASECLASS)
          +"= '~')");
    }
    else {
      sql.append(" and ("
          + this
              .getJoinString(this.table_b, TranMatRelBVO.PK_MATERIALSALECLASS)
          + "=" + this.getJoinString(this.tempTable, "pk_materialclass")
          + " or "+this.getJoinString(this.table_b, TranMatRelBVO.PK_MATERIALSALECLASS)
          +"= '~')");
    }
  }
}
