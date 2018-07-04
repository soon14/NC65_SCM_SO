package nc.impl.so.m38.migrate.action;

import nc.impl.pubapp.pattern.database.DBTool;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.so.m38.migrate.constant.PreorderMigState;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

/**
 * 销售预订单迁移完成后记录迁移状态
 * @author liylr
 * @since 2015-05-22
 */
public class UpdatePreOrderMigStateAction {
  public void update() throws BusinessException{
    DBTool tool = new DBTool();
    String[] pks = tool.getOIDs(1);
    DataAccessUtils dau = new DataAccessUtils();
    SqlBuilder sql = new SqlBuilder();
    sql.append("insert into so_m38miglog(pk_miglog, fmigstatus) values('");
    sql.append(pks[0]);
    sql.append("',");
    sql.append(PreorderMigState.FINISHED);
    sql.append(")");
    dau.update(sql.toString());
  }
}
