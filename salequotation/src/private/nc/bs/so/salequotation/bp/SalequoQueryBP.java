package nc.bs.so.salequotation.bp;

import nc.impl.pubapp.env.BSContext;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.BillQuery;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pub.smart.SmartServiceImpl;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.pubapp.query2.sql.process.QuerySchemeProcessor;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.scmpub.util.TimeUtils;
import nc.vo.so.pub.query.SOQueryApproveUtil;
import nc.vo.so.salequotation.entity.AggSalequotationHVO;
import nc.vo.so.salequotation.entity.SalequotationBVO;
import nc.vo.so.salequotation.entity.SalequotationHVO;
import nc.vo.uif2.LoginContext;
import nc.vo.util.VisibleUtil;

public class SalequoQueryBP extends SmartServiceImpl {
  public AggSalequotationHVO[] queryAllInvalidateBill() {
    DataAccessUtils utils = new DataAccessUtils();
    UFDate serviceDate =
        TimeUtils.getEndDate(BSContext.getInstance().getDate());
    StringBuffer sql = new StringBuffer();
    // 单据状态为自由、已审批、审批中的单据
    sql.append("select pk_salequotation from so_salequotation"
        + " where dr = 0 and fstatusflag in (-1,1,2) and denddate <= '"
        + serviceDate.toString() + "'");
    IRowSet rowSet = utils.query(sql.toString());
    String[] ids = rowSet.toOneDimensionStringArray();
    return new BillQuery<AggSalequotationHVO>(AggSalequotationHVO.class)
        .query(ids);
  }

  public SalequotationBVO[] queryBodyByHeadPk(String headPk) {
    String[] headPks = new String[] {
      headPk
    };
    return (SalequotationBVO[]) new BillQuery<AggSalequotationHVO>(
        AggSalequotationHVO.class).query(headPks)[0]
        .getChildren(SalequotationBVO.class);
  }

  public AggSalequotationHVO queryByPk(String pk) {
    return new BillQuery<AggSalequotationHVO>(AggSalequotationHVO.class)
        .query(new String[] {
          pk
        })[0];
  }

  public AggSalequotationHVO[] queryByPks(String[] pks) {
    return new BillQuery<AggSalequotationHVO>(AggSalequotationHVO.class)
        .query(pks);
  }

  public AggSalequotationHVO[] queryByQueryScheme(IQueryScheme querySheme) {
    AggSalequotationHVO[] bills = null;
    try {
      QuerySchemeProcessor processor = new QuerySchemeProcessor(querySheme);
      String headTableName = processor.getMainTableAlias();
      processor.appendCurrentGroup();
      processor.appendFuncPermissionOrgSql();
      // 排序
      SqlBuilder order = new SqlBuilder();
      order.append("order by ");
      order.append(headTableName);
      order.append(".vbillcode");
      // 改用懒加载
      BillLazyQuery<AggSalequotationHVO> qry =
          new BillLazyQuery<AggSalequotationHVO>(AggSalequotationHVO.class);
      bills = qry.query(querySheme, order.toString());
      // 过滤“待审批”
      bills =
          SOQueryApproveUtil.filterForApprove(querySheme, bills,
              SOBillType.SaleQuotation.getCode(), SalequotationHVO.VTRANTYPE);
    }
    catch (Exception ex) {
      ExceptionUtils.wrappException(ex);
    }
    return bills;
  }

  // public SalequotationBVO[] queryCurrentBodyByHeadPk(String headPk,
  // Class<? extends ISuperVO> childClz) {
  // BillLazyQuery<AggSalequotationHVO> lazyQuery =
  // new BillLazyQuery<AggSalequotationHVO>(AggSalequotationHVO.class,
  // childClz);
  // AggSalequotationHVO[] vos = lazyQuery.query(new String[] {
  // headPk
  // });
  // return (SalequotationBVO[]) vos[0].getChildrenVO();
  // }

  public AggSalequotationHVO[] queryHeadByCondtion(LoginContext context)
      throws Exception {
    String whereCon =
        VisibleUtil.getVisibleCondition(context, SalequotationHVO.class);
    return this.queryHeadByCondtion(whereCon);
  }

  public AggSalequotationHVO[] queryHeadByCondtion(String condition) {
    DataAccessUtils utils = new DataAccessUtils();
    StringBuffer sql = new StringBuffer();
    sql.append("select pk_salequotation from so_salequotation where dr = 0 ");
    if (condition != null) {
      sql.append(" and ");
      sql.append(condition);
    }
    IRowSet rowSet = utils.query(sql.toString());
    String[] ids = rowSet.toOneDimensionStringArray();
    return new BillQuery<AggSalequotationHVO>(AggSalequotationHVO.class)
        .query(ids);
  }

  public AggSalequotationHVO[] queryVOsBySql(String sql) {
    // TODO Auto-generated method stub
    DataAccessUtils utils = new DataAccessUtils();
    IRowSet rowSet = utils.query(sql.toString());
    String[] ids = rowSet.toOneDimensionStringArray();
    return new BillQuery<AggSalequotationHVO>(AggSalequotationHVO.class)
        .query(ids);
  }
}
