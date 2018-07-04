package nc.impl.so.m38.migrate.action;

import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

/**
 * @author liylr
 * @version 2015-02-09
 */
public class PreOrderAfterMigNodeAction {


	 /**
   * 预订单迁移后处理：
   */
  public void process() throws BusinessException {
    //销售管理参数 
    this.deleteM38SysinitPara();
    //预订单的单据号
    this.deleteM38BillCode();
    //后台任务
    this.deleteM38WorkPlugin();
    
  }
	
  /**
   * 删除业务组织参数
   */
  private void deleteM38SysinitPara() {
    String[] initCode = new String[] { "SO26" };
    DataAccessUtils accessutil = new DataAccessUtils();
    this.deleteUtilForM38("pub_sysinittemp", "INITCODE", initCode,
        accessutil);
    this.deleteUtilForM38("pub_sysinit", "INITCODE", initCode, accessutil);
  }

  /**
   * 删除单据号
   */
  private void deleteM38BillCode() {
    String[] code = new String[] { "38" };
    DataAccessUtils accessutil = new DataAccessUtils();
    SqlBuilder bcr_nbcrSql = new SqlBuilder();
    bcr_nbcrSql.append("select PK_NBCR from pub_bcr_nbcr where ");
    bcr_nbcrSql.append("CODE", code);
    IRowSet result = accessutil.query(bcr_nbcrSql.toString());
    if (result == null || result.size() == 0) {
      return;
    }
    String[] pk_nbcrs = result.toOneDimensionStringArray();
    this.deleteUtilForM38("pub_bcr_nbcr", "CODE", code, accessutil);
    this.deleteUtilForM38("pub_bcr_candiattr", "PK_NBCR", pk_nbcrs,
        accessutil);
  }

  /**
   * 删除动作任务
   */
  private void deleteM38WorkPlugin() {

    String[] pk_alerttypes = new String[] { "1001Z81000000000KURT",
        "1001Z810000000002PZU" };
    DataAccessUtils accessutil = new DataAccessUtils();
    // 预警类型注册
    this.deleteUtilForM38("pub_alerttype", "PK_ALERTTYPE", pk_alerttypes,
        accessutil);
    this.deleteUtilForM38("pub_alerttype_b", "PK_ALERTTYPE", pk_alerttypes,
        accessutil);
    // 消息模板类型、消息模板
    String[] tempCodes = new String[] { "nc.bs.so.m38.plugin.task.PreorderAlertPlugin" };
    this.deleteUtilForM38("pub_msgtemp_type", "TEMPCODE", tempCodes,
        accessutil);
    this.deleteUtilForM38("pub_msgtemp", "TYPECODE", tempCodes, accessutil);
  }

  /**
   * 预订单删除处理类
   * 
   * @param tableName
   *            要删除的表名
   * @param key
   * @param values
   * @param accessutil
   */
  private void deleteUtilForM38(String tableName, String key,
      String[] values, DataAccessUtils accessutil) {
    SqlBuilder sql = new SqlBuilder();
    sql.append("delete from ");
    sql.append(tableName);
    sql.append(" where ");
    sql.append(key, values);
    accessutil.update(sql.toString());
  }
}
