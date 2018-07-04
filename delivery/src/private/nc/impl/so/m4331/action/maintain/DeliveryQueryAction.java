package nc.impl.so.m4331.action.maintain;

import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.pubapp.query2.sql.process.QuerySchemeProcessor;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m4331.entity.DeliveryHVO;
import nc.vo.so.m4331.entity.DeliveryVO;
import nc.vo.so.pub.query.SOQueryApproveUtil;

/**
 * 查询发货单action
 * 
 * @since 6.0
 * @version 2011-1-12 上午09:52:07
 * @author 祝会征
 */
public class DeliveryQueryAction {
  public DeliveryVO[] query(IQueryScheme queryScheme) {
    QuerySchemeProcessor qrySchemeProcessor =
        new QuerySchemeProcessor(queryScheme);
    String headTableName = qrySchemeProcessor.getMainTableAlias();
    qrySchemeProcessor.appendCurrentGroup();
    qrySchemeProcessor.appendFuncPermissionOrgSql();
    // 排序
    SqlBuilder order = new SqlBuilder();
    order.append("order by ");
    order.append(headTableName);
    order.append(".vbillcode");
    // 改用懒加载
    BillLazyQuery<DeliveryVO> qry =
        new BillLazyQuery<DeliveryVO>(DeliveryVO.class);
    DeliveryVO[] bills = qry.query(queryScheme, order.toString());

    // 过滤"待审批"
    bills =
        SOQueryApproveUtil.filterForApprove(queryScheme, bills,
            SOBillType.Delivery.getCode(), DeliveryHVO.VTRANTYPECODE);

    return bills;
  }
}
