package nc.pubimpl.so.rule;

import java.util.ArrayList;
import java.util.List;

import nc.impl.pubapp.env.BSContext;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.pubitf.so.m30.ReturnAssignMatchVO;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.so.mreturnassign.entity.ReturnAssignVO;
import nc.vo.so.pub.util.BaseSaleClassUtil;

/**
 * 查询匹配记录
 * 
 * @since 6.0
 * @version 2011-4-15 上午10:21:14
 * @author 祝会征
 */
public class ReturnAssignQueryRule {
  // 退货政策分配表
  private String table = "so_returnassign";

  // 临时表
  private String tempTable;

  /**
   * 
   * @param temptablename
   * @return
   */
  public ReturnAssignMatchVO[] queryReturnAssignMatchVO(String temptablename) {
    this.tempTable = temptablename;
    String querysql = this.getQuerySql();
    DataAccessUtils bo = new DataAccessUtils();
    IRowSet rowset = bo.query(querysql);
    List<ReturnAssignMatchVO> listvo = new ArrayList<ReturnAssignMatchVO>();
    while (rowset.next()) {
      ReturnAssignMatchVO vo = new ReturnAssignMatchVO();
      vo.setParaindex(rowset.getInteger(0));
      vo.setPk_returnassign(rowset.getString(1));
      vo.setPk_returnpolicy(rowset.getString(2));
      // vo.setPk_material(rowset.getString(index++));
      // vo.setPk_customer(rowset.getString(index++));
      // vo.setPk_saleorg(rowset.getString(index++));
      listvo.add(vo);
    }
    ReturnAssignMatchVO[] vos = new ReturnAssignMatchVO[listvo.size()];
    listvo.toArray(vos);
    return vos;
  }

  private String getJoinString(String tablename, String col) {
    return tablename + "." + col;
  }

  private String getQuerySql() {
    StringBuffer sql = new StringBuffer();
    this.getSelectSql(sql);
    sql.append(" from " + this.table + "," + this.tempTable);
    this.getWhereSql(sql);
    return sql.toString();
  }

  private void getSelectSql(StringBuffer sql) {
    sql.append("select "
        + this.getJoinString(this.tempTable, ReturnAssignMatchVO.PARAINDEX));
    sql.append(","
        + this.getJoinString(this.table, ReturnAssignVO.PK_RETURNASSIGN) + ",");
    sql.append(" "
        + this.getJoinString(this.table, ReturnAssignVO.PK_RETURNPOLICY));
    // sql.append("," + this.getJoinString(this.table,
    // ReturnAssignVO.PK_MATERIAL)
    // + ",");
    // sql.append("," + this.getJoinString(this.table,
    // ReturnAssignVO.PK_CUSTOMER)
    // + ",");
  }

  private void getWhereSql(StringBuffer sql) {
	 sql.append(" where " + this.getJoinString(this.table, ReturnAssignVO.DR)
	            + " =0");
    sql.append(" and ("
        + this.getJoinString(this.table, ReturnAssignVO.PK_SALEORG) + "="
        + this.getJoinString(this.tempTable, ReturnAssignMatchVO.PK_SALEORG));
    sql.append(" or "
        + this.getJoinString(this.table, ReturnAssignVO.PK_SALEORG) + "="
        + this.getJoinString(this.tempTable, ReturnAssignMatchVO.PK_GROUP));

    sql.append(" and ("
        + this.getJoinString(this.table, ReturnAssignVO.PK_MATERIAL) + "="
        + this.getJoinString(this.tempTable, ReturnAssignMatchVO.PK_MATERIAL));
    sql.append(" or "
        + this.getJoinString(this.table, ReturnAssignVO.PK_MATERIAL) + "='~')");
    String pk_group = BSContext.getInstance().getGroupID();
    if (BaseSaleClassUtil.isMarBaseClass(pk_group)) {
      sql.append(" and ("
          + this.getJoinString(this.table, ReturnAssignVO.PK_MARBASCLASS)
          + "="
          + this.getJoinString(this.tempTable,
              ReturnAssignMatchVO.PK_MARBASCLASS));
      sql.append(" or "
          + this.getJoinString(this.table, ReturnAssignVO.PK_MARBASCLASS) + " ='~')");
    }
    else {
      sql.append(" and ("
          + this.getJoinString(this.table, ReturnAssignVO.PK_MARSALECLASS)
          + "="
          + this.getJoinString(this.tempTable,
              ReturnAssignMatchVO.PK_MARSALECLASS));
      sql.append(" or "
          + this.getJoinString(this.table, ReturnAssignVO.PK_MARSALECLASS) + " ='~')");
    }
    sql.append(" and ("
        + this.getJoinString(this.table, ReturnAssignVO.PK_CUSTOMER) + "="
        + this.getJoinString(this.tempTable, ReturnAssignMatchVO.PK_CUSTOMER));
    sql.append(" or "
        + this.getJoinString(this.table, ReturnAssignVO.PK_CUSTOMER) + " ='~')");
    // 买赠主表客户分类等于临时表客户分类
    if (BaseSaleClassUtil.isCustBaseClass(pk_group)) {
      sql.append(" and ("
          + this.getJoinString(this.table, ReturnAssignVO.PK_CUSTCLASS)
          + "="
          + this
              .getJoinString(this.tempTable, ReturnAssignMatchVO.PK_CUSTCLASS));
      sql.append(" or "
          + this.getJoinString(this.table, ReturnAssignVO.PK_CUSTCLASS) + " ='~')");
    }
    else {
      sql.append(" and ("
          + this.getJoinString(this.table, ReturnAssignVO.PK_CUSTSALECLASS)
          + "="
          + this.getJoinString(this.tempTable,
              ReturnAssignMatchVO.PK_CUSTSALECLASS));
      sql.append(" or "
          + this.getJoinString(this.table, ReturnAssignVO.PK_CUSTSALECLASS) + " ='~')");
    }
    sql.append(")");
    sql.append(" order by "
        + this.getJoinString(this.tempTable, ReturnAssignMatchVO.PARAINDEX));
    sql.append(","
        + this.getJoinString(this.table, ReturnAssignVO.CPRIORITYCODE));
    sql.append(" desc ");
  }
}
