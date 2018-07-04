package nc.impl.so.m38.arrange;

import nc.impl.pubapp.env.BSContext;
import nc.impl.pubapp.pattern.data.view.ViewQuery;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.itf.so.m38.arrange.IPreOrderArrange;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.pubapp.query2.sql.process.QuerySchemeProcessor;
import nc.vo.so.m38.entity.PreOrderBVO;
import nc.vo.so.m38.entity.PreOrderHVO;
import nc.vo.so.m38.entity.PreOrderViewVO;
import nc.vo.so.pub.enumeration.BillStatus;

public class PreOrderArrangeImpl implements IPreOrderArrange {

  @Override
  public PreOrderViewVO[] queryPreOrderViewVO(IQueryScheme queryScheme)
      throws BusinessException {
    QuerySchemeProcessor processor = new QuerySchemeProcessor(queryScheme);
    String maintablename = processor.getMainTableAlias();
    SqlBuilder sqlbuild = new SqlBuilder();
    // 拼接whereSql必要条件
    sqlbuild.append(" and ");
    sqlbuild.append(maintablename + ".dr", 0);
    sqlbuild.append(" and ");
    String pk_group = BSContext.getInstance().getGroupID();
    sqlbuild.append(maintablename + ".pk_group", pk_group);
    // 单据状态
    sqlbuild.append(" and ");
    sqlbuild.append(maintablename + "." + PreOrderHVO.FSTATUSFLAG,
        BillStatus.AUDIT);

    String chidtable =
        processor.getTableAliasOfAttribute(PreOrderBVO.class,
            PreOrderBVO.PK_ORG);
    // 表体关闭状态
    sqlbuild.append(" and ");
    sqlbuild.append(chidtable + "." + PreOrderBVO.BLINECLOSE, UFBoolean.FALSE);
    // 表体安排值
    sqlbuild.append(" and ");
    sqlbuild.append(" abs( " + chidtable + ".nnum ) > abs( isnull(" + chidtable
        + ".narrnum,0)) ");

    processor.appendWhere(sqlbuild.toString());

    // 拼接完整sql
    sqlbuild.reset();
    sqlbuild.append("select distinct ");
    sqlbuild.append(chidtable + ".cpreorderbid ");
    sqlbuild.append(processor.getFinalFromWhere());

    DataAccessUtils utils = new DataAccessUtils();
    IRowSet rowset = utils.query(sqlbuild.toString());
    if (rowset.size() == 0) {
      return new PreOrderViewVO[0];
    }
    String[] ids = rowset.toOneDimensionStringArray();
    ViewQuery<PreOrderViewVO> query =
        new ViewQuery<PreOrderViewVO>(PreOrderViewVO.class);

    return query.query(ids);
  }

  @Override
  public PreOrderViewVO[] queryPreOrderViewVO(String sql)
      throws BusinessException {
    DataAccessUtils utils = new DataAccessUtils();
    IRowSet set = utils.query(sql);
    if (set.size() == 0) {
      return new PreOrderViewVO[0];
    }
    String[] ids = set.toOneDimensionStringArray();
    ViewQuery<PreOrderViewVO> query =
        new ViewQuery<PreOrderViewVO>(PreOrderViewVO.class);
    return query.query(ids);
  }

  @Override
  public PreOrderViewVO[] queryPreOrderViewVO(String[] prebids)
      throws BusinessException {
    ViewQuery<PreOrderViewVO> query =
        new ViewQuery<PreOrderViewVO>(PreOrderViewVO.class);
    return query.query(prebids);
  }

}
