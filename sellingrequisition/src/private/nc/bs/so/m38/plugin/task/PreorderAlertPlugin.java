package nc.bs.so.m38.plugin.task;

import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.so.m38.entity.PreOrderHVO;
import nc.vo.so.pub.enumeration.BillStatus;

import nc.md.model.impl.MDEnum;

import nc.bs.pub.pa.IPreAlertPlugin;
import nc.bs.pub.pa.PreAlertContext;
import nc.bs.pub.pa.PreAlertObject;
import nc.bs.pub.pa.PreAlertReturnType;

import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.so.task.SOAlertMetaDataSource;

/**
 * 预订单自动失效关闭预警插件
 * 
 * @since 6.0
 * @version 2011-11-03 下午08:51:31
 * @author 王天文
 */
public class PreorderAlertPlugin implements IPreAlertPlugin {

  /** 提前预警天数 */
  private static final String ALERTDAY = "alertDay";

  @Override
  public PreAlertObject executeTask(PreAlertContext context)
      throws BusinessException {

    PreAlertObject retObj = new PreAlertObject();
    try {
      PreOrderHVO[] toabdatehvos = this.getAlertInfo(context);
      if (toabdatehvos.length == 0) {
        retObj.setReturnType(PreAlertReturnType.RETURNNOTHING);
      }
      else {
        retObj.setReturnType(PreAlertReturnType.RETURNDATASOURCE);
        retObj.setReturnObj(new SOAlertMetaDataSource(toabdatehvos));
      }
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return retObj;
  }

  /**
   * 查询结果：预警单据实体集合
   * 
   * @param context 预警执行上下文
   * @return 预警单据实体集合
   */
  private PreOrderHVO[] getAlertInfo(PreAlertContext context) {

    VOQuery<PreOrderHVO> qrysrv = new VOQuery<PreOrderHVO>(PreOrderHVO.class);
    String where = this.getWhereSql(context);
    PreOrderHVO[] toabdatehvos = qrysrv.query(where, null);
    return toabdatehvos;
  }

  /**
   * 查询条件，过滤即将自动失效和关闭的单据的预警信息
   * 
   * @param context 预警执行环境
   * @return where 查询过滤条件： <li>单据状态——自由态/审批中/审批通过 <li>失效日期——即将到达失效日期单据
   */
  private String getWhereSql(PreAlertContext context) {

    SqlBuilder where = new SqlBuilder();
    where.append(" and ");
    where.append(PreOrderHVO.PK_GROUP, context.getGroupId());
    String[] pk_orgs = context.getPk_orgs();
    if (pk_orgs != null && pk_orgs.length > 0) {
      where.append(" and ");
      where.append(PreOrderHVO.PK_ORG, pk_orgs);
    }
    where.append(" and ");
    MDEnum[] status = new BillStatus[] {
      BillStatus.AUDIT, BillStatus.AUDITING, BillStatus.FREE
    };
    where.append(PreOrderHVO.FSTATUSFLAG, status);
    where.append(" and ");
    UFDate serviceDate = AppContext.getInstance().getServerTime().getDate();
    UFDate dateStart = serviceDate.getDateBefore(7).asBegin();
    where.append(PreOrderHVO.DABATEDATE, ">= ", dateStart.toString());
    where.append(" and ");
    int day =
        ValueUtils
            .getInt(context.getKeyMap().get(PreorderAlertPlugin.ALERTDAY));
    UFDate dateEnd = serviceDate.getDateAfter(day).asEnd();
    where.append(PreOrderHVO.DABATEDATE, "<= ", dateEnd.toString());
    return where.toString();
  }

}
