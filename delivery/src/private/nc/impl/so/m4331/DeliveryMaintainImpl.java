package nc.impl.so.m4331;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.bs.so.m4331.maintain.QueryDeliveryBP;
import nc.impl.pubapp.bill.convertor.BillToViewConvertor;
import nc.impl.pubapp.bill.convertor.ViewToBillConvertor;
import nc.impl.pubapp.pattern.data.view.SchemeViewQuery;
import nc.impl.pubapp.pattern.data.view.ViewQuery;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;
import nc.impl.pubapp.pattern.pub.LockOperator;
import nc.impl.so.m4331.action.maintain.DeliveryDeleteAction;
import nc.impl.so.m4331.action.maintain.DeliveryInsertAction;
import nc.impl.so.m4331.action.maintain.DeliveryPushWriteAction;
import nc.impl.so.m4331.action.maintain.DeliveryUpdateAction;
import nc.itf.so.m4331.IDeliveryMaintain;
import nc.pubitf.so.m4331.pub.VOCombinUtil;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.data.IObjectConvert;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.pubapp.query2.sql.process.QuerySchemeProcessor;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.scmpub.res.billtype.TOBillType;
import nc.vo.scmpub.util.CombineViewToAggUtil;
import nc.vo.so.m4331.entity.DeliveryBVO;
import nc.vo.so.m4331.entity.DeliveryHVO;
import nc.vo.so.m4331.entity.DeliveryVO;
import nc.vo.so.m4331.entity.DeliveryViewVO;
import nc.vo.so.pub.SOItemKey;
import nc.vo.so.pub.SOParameterVO;
import nc.vo.so.pub.SOTable;
import nc.vo.so.pub.comparator.RowNoComparator;
import nc.vo.so.pub.enumeration.BillStatus;
import nc.vo.so.pub.query.SOQuerySchemeUtils;

import org.apache.commons.lang.ArrayUtils;

/**
 * 发货单维护接口实现类
 * 
 * @since 6.1
 * @version 2012-11-29 10:57:45
 * @author 冯加彬
 */
public class DeliveryMaintainImpl implements IDeliveryMaintain {

  private void appendFixedWhr(IQueryScheme qs, QuerySchemeProcessor qsp) {
    String halias = qsp.getMainTableAlias();
    SqlBuilder whr = new SqlBuilder();

    // 业务流程过滤
    Object obusi = qs.get(SOItemKey.CBIZTYPEID);
    String[] sbusi = null;
    if (obusi != null) {
      sbusi = (String[]) obusi;
      whr.append(" and (");
      IDExQueryBuilder iq = new IDExQueryBuilder(SOTable.TMP_SO_ID1.getName());
      whr.append(iq.buildSQL(halias + "." + SOItemKey.CBIZTYPEID, sbusi));
      whr.append(") ");
    }
    // 交易类型
    Object otrantype = qs.get(SOItemKey.VTRANTYPECODE);
    String[] strantype = null;
    if (otrantype != null) {
      strantype = (String[]) otrantype;
      whr.append(" and (");
      IDExQueryBuilder iq = new IDExQueryBuilder(SOTable.TMP_SO_ID2.getName());
      whr.append(iq.buildSQL(halias + "." + SOItemKey.VTRANTYPECODE, strantype));
      whr.append(") ");
    }

    int[] fstaus = new int[] {
      BillStatus.AUDIT.getIntValue(), BillStatus.CLOSED.getIntValue()
    };
    // 单据状态
    whr.append(" and (");
    whr.append(halias);
    whr.append(".");
    whr.append(SOItemKey.FSTATUSFLAG, fstaus);
    whr.append(") ");

    qsp.appendWhere(whr.toString());
  }

  @Override
  public DeliveryVO[] approveDelivery(DeliveryVO[] vos)
      throws BusinessException {
    return null;
  }

  /**
   * 拼接排序sql 默认方法（单据号、行号排序）
   * 
   * @param queryScheme
   * @return
   */
  private String createOrderSql(IQueryScheme queryScheme) {
    // 根据单据号、行号排序
    SqlBuilder order = new SqlBuilder();
    QuerySchemeProcessor processor = new QuerySchemeProcessor(queryScheme);
    order.append(" order by ");
    String tableName =
        processor.getTableAliasOfAttribute(DeliveryHVO.class,
            DeliveryHVO.VBILLCODE);
    order.append(tableName);
    order.append(".");
    order.append(DeliveryHVO.VBILLCODE);
    order.append(",");
    tableName =
        processor.getTableAliasOfAttribute(DeliveryBVO.class,
            DeliveryBVO.CROWNO);
    order.append(tableName);
    order.append(".");
    order.append(DeliveryBVO.CROWNO);
    return order.toString();
  }

  private void createSqlByQuerySchemeFor4804(IQueryScheme queryScheme) {
    QuerySchemeProcessor qsp = new QuerySchemeProcessor(queryScheme);
    // 增加固定where条件
    this.appendFixedWhr(queryScheme, qsp);

    // 增加集团
    qsp.appendCurrentGroup();

    // 得到子表别名
    String subTable =
        qsp.getTableAliasOfAttribute(DeliveryBVO.class, DeliveryBVO.BCHECKFLAG);

    SqlBuilder where = new SqlBuilder();

    where.append(" and (");
    where.append(subTable);
    where.append(".");
    where.append(DeliveryBVO.BTRANSENDFLAG, UFBoolean.FALSE.toString());
    where.append(") ");

    where.append(" and isnull(" + subTable + ".nnum,0)-isnull(" + subTable
        + ".ntotaltransnum,0)");
    where.append(" >0 ");

    // 增加上述where条件
    qsp.appendWhere(where.toString());

    Object obj = queryScheme.get("SRCBIDSARRAY");
    if (obj != null) {
      String[] srcBids = (String[]) obj;
      if (srcBids.length > 0) {
        SqlBuilder sql = new SqlBuilder();
        sql.appendNot(subTable + "." + DeliveryBVO.CDELIVERYBID, srcBids);
        qsp.appendWhere(" and ");
        qsp.appendWhere(sql.toString());
      }
    }
  }

  private void createSqlByQuerySchemeFor4C(IQueryScheme queryScheme) {
    QuerySchemeProcessor qsp = new QuerySchemeProcessor(queryScheme);
    // 增加固定where条件
    new SOQuerySchemeUtils().appendFixedWhr(queryScheme, qsp);

    // 增加集团
    qsp.appendCurrentGroup();

    // 得到子表别名
    String subTable =
        qsp.getTableAliasOfAttribute(DeliveryBVO.class, DeliveryBVO.BCHECKFLAG);

    SqlBuilder where = new SqlBuilder();
    where.append(" and (");
    where.append(subTable);
    where.append(".");
    where.append(DeliveryBVO.VSRCTYPE, SOBillType.Order.getCode());
    where.append(") ");

    where.append(" and (");
    where.append(subTable);
    where.append(".");
    where.append(DeliveryBVO.BOUTENDFLAG, UFBoolean.FALSE.toString());
    where.append(") ");

    where.append(" and abs(isnull(" + subTable + ".nnum,0))-abs(isnull("
        + subTable + ".ntotalnotoutnum,0))");
    where.append(" >0 ");

    // 增加上述where条件
    qsp.appendWhere(where.toString());

    // String select = "select distinct(" + subTable + ".cdeliverybid) ";

    // // sql语句
    // String sql = select + qsp.getFinalFromWhere();
    //
    // return sql.toString();
  }

  private void createSqlByQuerySchemeFor4Y(IQueryScheme queryScheme) {
    QuerySchemeProcessor qsp = new QuerySchemeProcessor(queryScheme);
    // 增加固定where条件
    new SOQuerySchemeUtils().appendFixedWhr(queryScheme, qsp);

    // 增加集团
    qsp.appendCurrentGroup();

    // 得到子表别名
    String subTable =
        qsp.getTableAliasOfAttribute(DeliveryBVO.class, DeliveryBVO.BCHECKFLAG);

    SqlBuilder where = new SqlBuilder();
    where.append(" and (");
    where.append(subTable);
    where.append(".");
    where.append(DeliveryBVO.VSRCTYPE, TOBillType.TransOrder.getCode());
    where.append(") ");

    where.append(" and (");
    where.append(subTable);
    where.append(".");
    where.append(DeliveryBVO.BOUTENDFLAG, UFBoolean.FALSE.toString());
    where.append(") ");
    // where.append(" and isnull(" + subTable + ".nnum,0)-isnull(" + subTable
    // + ".ntotalnotoutnum,0)");
    where.append(" and abs(isnull(" + subTable + ".nnum,0))-abs(isnull("
        + subTable + ".ntotalnotoutnum,0))");
    where.append(" >0 ");

    where.append(" and abs(isnull(" + subTable + ".nnum,0))-abs(isnull("
        + subTable + ".ntotaloutnum,0))");
    where.append(" >0 ");

    where.append(" and abs(isnull(" + subTable
        + ".ntotalnotoutnum,0))+abs(isnull(" + subTable + ".ntotaloutnum,0))");
    where.append(" < abs(isnull(" + subTable + ".nnum,0))");

    // 增加上述where条件
    qsp.appendWhere(where.toString());

    // String select = "select distinct(" + subTable + ".cdeliverybid) ";
    //
    // // sql语句
    // String sql = select + qsp.getFinalFromWhere();
    //
    // return sql.toString();
  }

  @Override
  public void deleteDelivery(DeliveryVO[] vos) throws BusinessException {
    try {
      DeliveryDeleteAction action = new DeliveryDeleteAction();
      action.delete(vos);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
  }

  @Override
  public DeliveryVO[] insertDelivery(DeliveryVO[] insertvos)
      throws BusinessException {
    DeliveryVO[] ret = null;
    try {
      DeliveryInsertAction action = new DeliveryInsertAction();
      ret = action.insert(insertvos);
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return ret;

  }

  private void lockBid(DeliveryVO[] vos) {
    if (null == vos) {
      return;
    }
    Set<String> bidSet = new HashSet<String>();
    for (DeliveryVO vo : vos) {
      DeliveryBVO[] bvos = vo.getChildrenVO();
      for (DeliveryBVO bvo : bvos) {
        bidSet.add(bvo.getCdeliverybid());
      }
    }
    String[] bids = new String[bidSet.size()];
    bidSet.toArray(bids);
    LockOperator lock = new LockOperator();
    lock.lock(
        bids,
        nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006002_0",
            "04006002-0075")/* @res "给发货单表体行加锁失败。" */);
  }

  @Override
  public DeliveryVO[] pushWriteDelivery(SOParameterVO paravo)
      throws BusinessException {
    DeliveryVO[] retvos = null;
    try {
      DeliveryPushWriteAction action = new DeliveryPushWriteAction();
      retvos = action.pushwrite(paravo);
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return retvos;

  }

  @Override
  public DeliveryVO[] queryDelivery(String sql) throws BusinessException {
    QueryDeliveryBP bp = new QueryDeliveryBP();
    return bp.query(sql);
  }

  @Override
  public DeliveryVO[] queryDelivery(String[] bids) throws BusinessException {
    DeliveryViewVO[] views =
        new ViewQuery<DeliveryViewVO>(DeliveryViewVO.class).query(bids);
    if (ArrayUtils.isEmpty(views)) {
      return null;
    }
    IObjectConvert<DeliveryViewVO, DeliveryVO> billconvert =
        new ViewToBillConvertor<DeliveryViewVO, DeliveryVO>(DeliveryVO.class);
    DeliveryVO[] queryVos = billconvert.convert(views);
    return queryVos;
  }

  @Override
  public DeliveryViewVO[] queryDeliveryFor4804(IQueryScheme queryScheme)
      throws BusinessException {
    // 处理默认条件
    this.createSqlByQuerySchemeFor4804(queryScheme);
    // 拼接排序sql
    String ordersql = this.createOrderSql(queryScheme);
    // String sql = this.createSqlByQuerySchemeFor4804(queryScheme);
    DeliveryVO[] queryVos = null;

    queryVos = this.queryDeliveryVOForSource(queryScheme, ordersql);

    if (null == queryVos || queryVos.length == 0) {
      return null;
    }
    IObjectConvert<DeliveryVO, DeliveryViewVO> convert =
        new BillToViewConvertor<DeliveryVO, DeliveryViewVO>(
            DeliveryViewVO.class);
    return convert.convert(queryVos);
  }

  @Override
  public DeliveryVO[] queryDeliveryFor4C(IQueryScheme queryScheme)
      throws BusinessException {
    // 处理默认条件
    this.createSqlByQuerySchemeFor4C(queryScheme);
    // 拼接排序sql
    String ordersql = this.createOrderSql(queryScheme);
    DeliveryVO[] queryVos =
        this.queryDeliveryVOForSource(queryScheme, ordersql);
    this.lockBid(queryVos);
    VOCombinUtil combin = new VOCombinUtil();
    return combin.combinTODeliveryvo(queryVos);
  }

  @Override
  public DeliveryVO[] queryDeliveryFor4Y(IQueryScheme queryScheme)
      throws BusinessException {
    // 处理默认条件
    this.createSqlByQuerySchemeFor4Y(queryScheme);
    // 拼接排序sql
    String ordersql = this.createOrderSql(queryScheme);
    DeliveryVO[] queryVos =
        this.queryDeliveryVOForSource(queryScheme, ordersql);
    VOCombinUtil combin = new VOCombinUtil();
    return combin.combinTODeliveryvo(queryVos);
  }

  /**
   * 
   * @param scheme 查询sql
   * @param order 排序sql
   * @return
   */
  private DeliveryVO[] queryDeliveryVOForSource(IQueryScheme scheme,
      String order) {
    SchemeViewQuery<DeliveryViewVO> query =
        new SchemeViewQuery<DeliveryViewVO>(DeliveryViewVO.class);
    DeliveryViewVO[] views = query.query(scheme, order);
    if (ArrayUtils.isEmpty(views)) {
      return null;
    }
    Set<String> sethid = new HashSet<String>();
    for (DeliveryViewVO view : views) {
      sethid.add(view.getHead().getCdeliveryid());
    }
    String[] hids = new String[sethid.size()];
    sethid.toArray(hids);

    String[] selkeys =
        new String[] {
          DeliveryHVO.CDELIVERYID, DeliveryHVO.CSENDDEPTVID,
          DeliveryHVO.CSENDDEPTID, DeliveryHVO.CSENDEMPLOYEEID
        };
    VOQuery<DeliveryHVO> qrysrv =
        new VOQuery<DeliveryHVO>(DeliveryHVO.class, selkeys);
    DeliveryHVO[] heads = qrysrv.query(hids);
    Map<String, DeliveryHVO> maphead = new HashMap<String, DeliveryHVO>();
    for (DeliveryHVO head : heads) {
      maphead.put(head.getCdeliveryid(), head);
    }
    // 补全表头的物流组织 否则转换的queryVos没有pk_org 生成的调拨出库单有问题
    for (DeliveryViewVO view : views) {
      DeliveryHVO head = view.getHead();
      DeliveryBVO body = view.getItem();

      head.setPk_group(body.getPk_group());
      head.setPk_org(body.getPk_org());
      head.setDbilldate(body.getDbilldate());
    }
    DeliveryVO[] queryVos =
        new CombineViewToAggUtil<DeliveryVO>(DeliveryVO.class,
            DeliveryHVO.class, DeliveryBVO.class).combineViewToAgg(views,
            DeliveryHVO.CDELIVERYID);
    // 根据行号排序(按数字）
    for (DeliveryVO vo : queryVos) {
      RowNoComparator c = new RowNoComparator(DeliveryBVO.CROWNO);
      Arrays.sort(vo.getChildrenVO(), c);
    }
    return queryVos;
  }

  // private DeliveryVO[] queryDeliveryVOForSource(String sql) {
  // DataAccessUtils utils = new DataAccessUtils();
  // String[] bids = utils.query(sql).toOneDimensionStringArray();
  // DeliveryViewVO[] views =
  // new ViewQuery<DeliveryViewVO>(DeliveryViewVO.class).query(bids);
  // if (ArrayUtils.isEmpty(views)) {
  // return null;
  // }
  // IObjectConvert<DeliveryViewVO, DeliveryVO> billconvert =
  // new ViewToBillConvertor<DeliveryViewVO, DeliveryVO>(DeliveryVO.class);
  // DeliveryVO[] queryVos = billconvert.convert(views);
  // // DeliveryVO[] queryVos =
  // // new CombineViewToAggUtil<DeliveryVO>(DeliveryVO.class,
  // // DeliveryHVO.class, DeliveryBVO.class).combineViewToAgg(views,
  // // DeliveryHVO.CDELIVERYID);
  //
  // return queryVos;
  // }

  @Override
  public DeliveryViewVO[] queryViewVO(String sql) throws BusinessException {
    DataAccessUtils utils = new DataAccessUtils();
    IRowSet rowset = utils.query(sql);
    if (rowset.size() == 0) {
      return new DeliveryViewVO[0];
    }
    String[] ids = rowset.toOneDimensionStringArray();
    ViewQuery<DeliveryViewVO> query =
        new ViewQuery<DeliveryViewVO>(DeliveryViewVO.class);
    return query.query(ids);
  }

  @Override
  public DeliveryVO[] unapproveDelivery(DeliveryVO[] vos)
      throws BusinessException {
    return null;
  }

  @Override
  public DeliveryVO[] updateDelivery(DeliveryVO[] updatevos,
      DeliveryVO[] originBills) throws BusinessException {
    DeliveryVO[] ret = null;
    try {
      DeliveryUpdateAction action = new DeliveryUpdateAction();
      ret = action.update(updatevos, originBills);
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return ret;
  }
}
