package nc.bs.so.m30.mobile.task;

import nc.bs.pub.pa.PreAlertObject;
import nc.bs.pub.pa.PreAlertReturnType;
import nc.bs.pub.taskcenter.BgWorkingContext;
import nc.bs.pub.taskcenter.IBackgroundWorkPlugin;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

/**
 * 销售订单移动报表中间表转储
 * 
 * @since 6.1
 * @version 2012-5-17 上午09:57:55
 * @author yixl
 */

public class SaleOrderMobileQryBGWorkPlugin implements IBackgroundWorkPlugin {

  @Override
  public PreAlertObject executeTask(BgWorkingContext bgwc)
      throws BusinessException {
    PreAlertObject preobj = new PreAlertObject();

    // 向销售订单我的订单转储表插入数据
    this.insertDataToMyOrder();

    preobj.setReturnType(PreAlertReturnType.RETURNNOTHING);

    return preobj;
  }

  private String getMyOrderInsertSql() {
    UFDate busidate = AppContext.getInstance().getBusiDate();
    UFDate enddate = busidate.asEnd();

    UFDate bfthreemoth = busidate.getDateBefore(7);
    UFDate begindate = bfthreemoth.asBegin();

    SqlBuilder insertSql = new SqlBuilder();

    insertSql
        .append("insert into so_mb_myorder(csaleorderid,pk_group,dbilldate,");
    insertSql.append("pk_org,vbillcode,ccustomerid,ntotalorigmny,cemployeeid)");
    insertSql
        .append(" select soh.csaleorderid,soh.pk_group,soh.dbilldate,soh.pk_org,");
    insertSql
        .append("soh.vbillcode,soh.ccustomerid,soh.ntotalorigmny,soh.cemployeeid");

    insertSql.append(" from so_saleorder soh ");
    insertSql.append(" where ");
    insertSql.append(" soh.pk_group", AppContext.getInstance().getPkGroup());
    insertSql.append(" and ");
    insertSql.append(" soh.dr", 0);
    insertSql.append(" and ");
    insertSql.append(" soh.dbilldate ", " >=", begindate.toString());
    insertSql.append(" and ");
    insertSql.append(" soh.dbilldate ", " <=", enddate.toString());

    return insertSql.toString();
  }

  private void insertDataToMyOrder() {

    // 直接删除所有记录
    String delSql = "delete from so_mb_myorder";

    DataAccessUtils dataAcsUtils = new DataAccessUtils();
    dataAcsUtils.update(delSql); // 删除最近一星期的记录

    String insertSql = this.getMyOrderInsertSql();
    dataAcsUtils.update(insertSql); // 重新插入最近一星期的记录
  }

}
