package nc.pubimpl.so.m30.so.m4331;

import nc.impl.pubapp.pattern.data.view.ViewQuery;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.pubitf.so.m30.so.m4331.IQueryFor4331Arrange;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.pubapp.query2.sql.process.QuerySchemeProcessor;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderViewVO;
import nc.vo.so.pub.query.SOQuerySchemeUtils;

public class QueryFor4331ArrangeImpl implements IQueryFor4331Arrange {

  @Override
  public SaleOrderViewVO[] queryArrangeSaleOrder(IQueryScheme queryScheme)
      throws BusinessException {
    String sql = this.createRefSqlByQuerySchemeFor4331(queryScheme);
    DataAccessUtils utils = new DataAccessUtils();
    String[] bids = utils.query(sql).toOneDimensionStringArray();
    SaleOrderViewVO[] views =
        new ViewQuery<SaleOrderViewVO>(SaleOrderViewVO.class).query(bids);
    return views;
  }

  private String createRefSqlByQuerySchemeFor4331(IQueryScheme queryScheme) {
    QuerySchemeProcessor qsp = new QuerySchemeProcessor(queryScheme);
    // 增加固定where条件
    new SOQuerySchemeUtils().appendFixedWhr(queryScheme, qsp);
    // 增加集团
    qsp.appendCurrentGroup();

    // 得到主子表别名
    String subTable =
        qsp.getTableAliasOfAttribute(SaleOrderBVO.METAPATH
            + SaleOrderBVO.BBOUTENDFLAG);
    String mainTableAlias = qsp.getMainTableAlias();

    SqlBuilder where = new SqlBuilder();
    where.append(" and (");
    where.append(subTable);
    where.append(".");
    where.append(SaleOrderBVO.BLABORFLAG, UFBoolean.FALSE.toString());
    where.append(") ");

    where.append(" and (");
    where.append(subTable);
    where.append(".");
    where.append(SaleOrderBVO.BDISCOUNTFLAG, UFBoolean.FALSE.toString());
    where.append(") ");

    where.append(" and (");
    where.append(mainTableAlias);
    where.append(".");
    where.append(SaleOrderHVO.BSENDENDFLAG, UFBoolean.FALSE.toString());
    where.append(") ");

    where.append(" and (");
    where.append(subTable);
    where.append(".");
    where.append(SaleOrderBVO.BBSENDENDFLAG, UFBoolean.FALSE.toString());
    where.append(") ");

    // 增加上述where条件
    qsp.appendWhere(where.toString());
    String select = "select distinct(" + subTable + ".csaleorderbid) ";
    // sql语句
    String sql = select + qsp.getFinalFromWhere();

    return sql.toString();

  }

}
