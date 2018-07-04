package nc.impl.so.salequotation.action;

import nc.bs.pub.compiler.AbstractCompiler2;
import nc.bs.pubapp.AppBsContext;
import nc.bs.so.salequotation.bp.SalequoApproveBP;
import nc.bs.so.salequotation.bp.SalequoCloseBP;
import nc.bs.so.salequotation.bp.SalequoCommitBP;
import nc.bs.so.salequotation.bp.SalequoInvalidateBP;
import nc.bs.so.salequotation.bp.SalequoMaintainBP;
import nc.bs.so.salequotation.bp.SalequoOpenBP;
import nc.bs.so.salequotation.bp.SalequoQueryBP;
import nc.bs.so.salequotation.bp.SalequoUnApproveBP;
import nc.impl.pubapp.env.BSContext;
import nc.impl.pubapp.pattern.data.bill.BillQuery;
import nc.impl.pubapp.pattern.data.view.SchemeViewQuery;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.itf.so.salequotation.ISalequotationQry;
import nc.itf.so.salequotation.ISalequotationService;
import nc.md.model.impl.MDEnum;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.pubapp.query2.sql.process.QuerySchemeProcessor;
import nc.vo.scmpub.res.billtype.CTBillType;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.scmpub.util.CombineViewToAggUtil;
import nc.vo.scmpub.util.TimeUtils;
import nc.vo.so.salequotation.entity.AggSalequotationHVO;
import nc.vo.so.salequotation.entity.BillStatusEnum;
import nc.vo.so.salequotation.entity.SalequoViewVO;
import nc.vo.so.salequotation.entity.SalequotationBVO;
import nc.vo.so.salequotation.entity.SalequotationHVO;

import org.apache.commons.lang.ArrayUtils;

public class SalequotationImpl implements ISalequotationService,
    ISalequotationQry {

  @Override
  public Object approve(AggSalequotationHVO[] vos, AbstractCompiler2 script)
      throws BusinessException {
    try {
      return new SalequoApproveBP().approve(vos, script);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  @Override
  public AggSalequotationHVO[] close(AggSalequotationHVO[] aggVOs)
      throws BusinessException {
    try {
      return new SalequoCloseBP().close(aggVOs);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  @Override
  public AggSalequotationHVO[] commit(AggSalequotationHVO[] vos,
      AbstractCompiler2 script) throws BusinessException {
    try {
      return new SalequoCommitBP().commit(vos, script);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  @Override
  public void delete(AggSalequotationHVO[] aggVOs) throws Exception {
    try {
      new SalequoMaintainBP().delete(aggVOs);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
  }

  public AggSalequotationHVO[] insert(AggSalequotationHVO[] aggVOs)
      throws Exception {
    try {
      return new SalequoMaintainBP().insert(aggVOs);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  @Override
  public AggSalequotationHVO[] invalidate(AggSalequotationHVO[] aggVOs)
      throws Exception {
    try {
      return new SalequoInvalidateBP().invalidate(aggVOs);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  @Override
  public AggSalequotationHVO[] open(AggSalequotationHVO[] aggVOs)
      throws Exception {
    try {
      return new SalequoOpenBP().open(aggVOs);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  @Override
  public AggSalequotationHVO[] queryAllInvalidateBill(String pk_org) {
    DataAccessUtils utils = new DataAccessUtils();
    SqlBuilder sql = new SqlBuilder();
    // 单据状态为自由、已审批、审批中的单据
    sql.append("select pk_salequotation from so_salequotation where ");
    // 拼接后台固定条件
    sql.append(SalequotationHVO.DR, 0);
    sql.append(" and ");
    sql.append(SalequotationHVO.PK_GROUP, AppBsContext.getInstance()
        .getPkGroup());
    sql.append(" and ");
    MDEnum[] fstatus = new MDEnum[] {
      BillStatusEnum.FREE, BillStatusEnum.AUDITING, BillStatusEnum.NOPASS
    };
    sql.append(SalequotationHVO.FSTATUSFLAG, fstatus);

    if (!PubAppTool.isNull(pk_org)) {
      sql.append(" and ");
      sql.append(SalequotationHVO.PK_ORG, pk_org);
    }

    UFDateTime srvtime = AppBsContext.getInstance().getServerTime();
    sql.append(" and ");
    sql.append(SalequotationHVO.DENDDATE, " <= ", srvtime.toString());

    IRowSet rowSet = utils.query(sql.toString());
    String[] ids = rowSet.toOneDimensionStringArray();
    BillQuery<AggSalequotationHVO> billquery =
        new BillQuery<AggSalequotationHVO>(AggSalequotationHVO.class);
    return billquery.query(ids);
  }

  @Override
  public SalequotationBVO[] queryBodyByHeadPk(String headPk) throws Exception {
    return new SalequoQueryBP().queryBodyByHeadPk(headPk);
  }

  @Override
  public AggSalequotationHVO queryByPk(String pk) throws Exception {
    return new SalequoQueryBP().queryByPk(pk);
  }

  @Override
  public AggSalequotationHVO[] queryByPks(String[] pks) throws Exception {
    return new SalequoQueryBP().queryByPks(pks);
  }

  @Override
  public AggSalequotationHVO[] queryByQuerySchemeFor30(IQueryScheme querySheme)
      throws Exception {
    this.createRefSqlByQuerySchemeFor30(querySheme);
    // 拼接排序sql
    String ordersql = this.createOrderSql(querySheme);
    AggSalequotationHVO[] bills = null;
    try {
      bills = this.querySalequotationVOForSource(querySheme, ordersql);
      return bills;
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return bills;
  }

  @Override
  public AggSalequotationHVO[] queryByQuerySchemeForZ3(IQueryScheme querySheme)
      throws Exception {
    this.createRefSqlByQuerySchemeForZ3(querySheme);
    // 拼接排序sql
    String ordersql = this.createOrderSql(querySheme);
    AggSalequotationHVO[] bills = null;
    try {
      bills = this.querySalequotationVOForSource(querySheme, ordersql);
      return bills;
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return bills;
  }

  @Override
  public AggSalequotationHVO[] queryVOsBySql(String sql) throws Exception {
    return new SalequoQueryBP().queryVOsBySql(sql);
  }

  @Override
  public AggSalequotationHVO[] saveBase(AggSalequotationHVO[] aggVOs)
      throws Exception {
    try {
      return new SalequoMaintainBP().saveBase(aggVOs);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  @Override
  public AggSalequotationHVO[] unApprove(AggSalequotationHVO[] vos,
      AbstractCompiler2 script) throws BusinessException {
    try {
      return new SalequoUnApproveBP().unApprove(vos, script);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  public AggSalequotationHVO[] update(AggSalequotationHVO[] aggVOs)
      throws Exception {
    try {
      return new SalequoMaintainBP().update(aggVOs);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  private String createOrderSql(IQueryScheme queryScheme) {
    // 根据单据号、行号排序
    SqlBuilder order = new SqlBuilder();
    QuerySchemeProcessor processor = new QuerySchemeProcessor(queryScheme);
    order.append(" order by ");
    String tableName =
        processor.getTableAliasOfAttribute(SalequotationHVO.class,
            SalequotationHVO.VBILLCODE);
    order.append(tableName);
    order.append(".");
    order.append(SalequotationHVO.VBILLCODE);
    order.append(",");
    tableName =
        processor.getTableAliasOfAttribute(SalequotationBVO.class,
            SalequotationBVO.CROWNO);
    order.append(tableName);
    order.append(".");
    order.append(SalequotationBVO.CROWNO);
    return order.toString();

  }

  private String createRefSqlByQuerySchemeFor30(IQueryScheme queryScheme) {
    QuerySchemeProcessor qsp = new QuerySchemeProcessor(queryScheme);
    // 增加集团
    qsp.appendCurrentGroup();

    // 得到主子表别名
    String subTable =
        qsp.getTableAliasOfAttribute(SalequotationBVO.METAPATH
            + SalequotationBVO.BLARGESSFLAG);
    String mainTableAlias = qsp.getMainTableAlias();

    SqlBuilder where = new SqlBuilder();
    where.append(" and (");
    where.append(mainTableAlias);
    where.append(".");
    where.append(SalequotationHVO.FSTATUSFLAG, BillStatusEnum.C_AUDIT);
    where.append(") ");

    UFDate serviceDate =
        TimeUtils.getStartDate(AppContext.getInstance().getBusiDate());
    where.append(" and (");
    where.append(mainTableAlias);
    where.append(".");
    where.append(SalequotationHVO.DENDDATE + " >='" + serviceDate + "'");
    where.append(") ");

    // 增加上述where条件
    qsp.appendWhere(where.toString());
    qsp.appendRefTrantypeWhere(SOBillType.SaleQuotation.getCode(),
        SOBillType.Order.getCode(), SalequotationHVO.VTRANTYPE);
    String select = "select distinct(" + subTable + ".pk_salequotation_b) ";
    // sql语句
    String sql = select + qsp.getFinalFromWhere();

    return sql.toString();

  }

  private void createRefSqlByQuerySchemeForZ3(IQueryScheme queryScheme) {
    QuerySchemeProcessor qsp = new QuerySchemeProcessor(queryScheme);
    // 增加集团
    qsp.appendCurrentGroup();

    // 得到主子表别名
    String subTable =
        qsp.getTableAliasOfAttribute(SalequotationBVO.METAPATH
            + SalequotationBVO.BLARGESSFLAG);
    String mainTableAlias = qsp.getMainTableAlias();

    SqlBuilder where = new SqlBuilder();
    where.append(" and (");
    where.append(mainTableAlias);
    where.append(".");
    where.append(SalequotationHVO.FSTATUSFLAG, BillStatusEnum.C_AUDIT);
    where.append(") ");

    UFDate serviceDate =
        TimeUtils.getStartDate(BSContext.getInstance().getDate());
    where.append(" and (");
    where.append(mainTableAlias);
    where.append(".");
    where.append(SalequotationHVO.DENDDATE + " >='" + serviceDate + "'");
    where.append(") ");

    // 增加上述where条件
    qsp.appendWhere(where.toString());
    qsp.appendRefTrantypeWhere(SOBillType.SaleQuotation.getCode(),
        CTBillType.SaleDaily.getCode(), SalequotationHVO.VTRANTYPE);
    // String select = "select distinct(" + subTable + ".pk_salequotation_b) ";
    // sql语句
    // String sql = select + qsp.getFinalFromWhere();
    // return sql.toString();
  }

  private AggSalequotationHVO[] querySalequotationVOForSource(
      IQueryScheme queryScheme, String ordersql) {
    SchemeViewQuery<SalequoViewVO> query =
        new SchemeViewQuery<SalequoViewVO>(SalequoViewVO.class);
    SalequoViewVO[] views = query.query(queryScheme, ordersql);

    if (ArrayUtils.isEmpty(views)) {
      return null;
    }

    AggSalequotationHVO[] queryVos =
        new CombineViewToAggUtil<AggSalequotationHVO>(
            AggSalequotationHVO.class, SalequotationHVO.class,
            SalequotationBVO.class).combineViewToAgg(views,
            SalequotationHVO.PK_SALEQUOTATION);
    for (SalequoViewVO view : views) {
      SalequotationHVO headvo =
          (SalequotationHVO) view.getVO(SalequotationHVO.class);
      SalequotationBVO bodyvo =
          (SalequotationBVO) view.getVO(SalequotationBVO.class);
      headvo.setPk_group(bodyvo.getPk_group());
      headvo.setPk_org(bodyvo.getPk_org());
      headvo.setPk_org_v(bodyvo.getPk_org_v());
    }

    return queryVos;
  }
}
