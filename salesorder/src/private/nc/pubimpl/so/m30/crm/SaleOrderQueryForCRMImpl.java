package nc.pubimpl.so.m30.crm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.pattern.data.bill.BillQuery;
import nc.impl.pubapp.pattern.data.mapping.VORowSetMap;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.database.IDQueryBuilder;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ColumnListProcessor;
import nc.jdbc.framework.processor.ResultSetProcessor;
import nc.pubitf.so.m30.crm.ISaleOrderQueryForCRM;
import nc.pubitf.so.m4310.crm.CRMQueryPara;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.pubapp.query2.sql.process.NonMetaQuerySchemeProcessor;
import nc.vo.pubapp.query2.sql.process.QueryCondition;
import nc.vo.pubapp.query2.sql.process.QuerySchemeCreator;
import nc.vo.scmpub.util.ArrayUtil;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.pub.enumeration.BillStatus;

/**
 * 为CRM提供销售订单查询接口的实现类
 *
 * @since 6.3.1
 * @version 2013-08-08 09:17:09
 * @author 张云枫
 */
public class SaleOrderQueryForCRMImpl implements ISaleOrderQueryForCRM {

  @Override
  public SaleOrderHVO[] querySaleOrder(CRMQueryPara queryPara)
      throws BusinessException {
    try {
      String querysql = this.getQuerySql(queryPara);

      DataAccessUtils dao = new DataAccessUtils();
      IRowSet rowset = dao.query(querysql);

      int size = rowset.size();
      int start = queryPara.getNstartcount() - 1;
      int length = queryPara.getNendcount() - start;
      // 开始位置大于总结果数
      if (size <= 0 || start > size) {
        return null;
      }

      for (int i = 0; i < start; i++) {
        rowset.next();
      }
      int cursor = 0;
      List<String> list = new ArrayList<String>();
      while (cursor < length && rowset.next()) {
        list.add(rowset.getString(0));
        cursor++;
      }
      VOQuery<SaleOrderHVO> query =
          new VOQuery<SaleOrderHVO>(SaleOrderHVO.class);
      String[] ids = list.toArray(new String[list.size()]);
      SaleOrderHVO[] rets = query.query(ids);
      return rets;
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return null;

  }

  @Override
  public SaleOrderBVO[] querySaleOrderById(String id) throws BusinessException {

    SaleOrderBVO[] rets = null;
    try {
      VOQuery<SaleOrderBVO> query =
          new VOQuery<SaleOrderBVO>(SaleOrderBVO.class);
      SqlBuilder condition = new SqlBuilder();
      condition.append(" and csaleorderid = '" + id + "'");
      rets = query.query(condition.toString(), null);
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return rets;
  }

  @Override
  public SaleOrderBVO[] querySaleOrderBodyVOsByActID(String cmarketactid,
      String[] fieldnames) throws BusinessException {
    SaleOrderBVO[] retvos = null;
    try {
      SqlBuilder condition = new SqlBuilder();

      // 获取审批通过条件
      condition.append(this.getAuditSQlwhere());

      // 获取买赠活动或价格促销活动匹配条件
      condition.append(this.getBuyORPriceActWhere(cmarketactid));

      VOQuery<SaleOrderBVO> query =
          new VOQuery<SaleOrderBVO>(SaleOrderBVO.class, fieldnames);

      retvos = query.queryWithWhereKeyWord(condition.toString(), null);
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return retvos;
  }

  @Override
  public SaleOrderBVO[] querySaleOrderBodyVOsByLargessActID(
      String cmarketactid, String[] fieldnames) throws BusinessException {
    SaleOrderBVO[] retvos = null;
    try {
      SqlBuilder condition = new SqlBuilder();

      // 获取审批通过条件
      condition.append(this.getAuditSQlwhere());

      // 获取买赠活动匹配条件
      condition.append(this.getbuylrgessActWhere(cmarketactid));

      VOQuery<SaleOrderBVO> query =
          new VOQuery<SaleOrderBVO>(SaleOrderBVO.class, fieldnames);

      retvos = query.queryWithWhereKeyWord(condition.toString(), null);
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return retvos;
  }

  @Override
  public SaleOrderBVO[] querySaleOrderBodyVOsPriceActID(String cmarketactid,
      String[] fieldnames) throws BusinessException {
    SaleOrderBVO[] retvos = null;
    try {
      SqlBuilder condition = new SqlBuilder();

      // 获取审批通过条件
      condition.append(this.getAuditSQlwhere());

      // 获取促销价格活动匹配条件
      condition.append(this.getPricePromtActWhere(cmarketactid));

      VOQuery<SaleOrderBVO> query =
          new VOQuery<SaleOrderBVO>(SaleOrderBVO.class, fieldnames);

      retvos = query.queryWithWhereKeyWord(condition.toString(), null);
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return retvos;
  }

  @Override
  public SaleOrderHVO[] querySaleOrderHeadVOsByOrderIDs(String[] pk_saleorders)
      throws BusinessException {
    SaleOrderHVO[] retvos = null;
    try {
      VOQuery<SaleOrderHVO> query =
          new VOQuery<SaleOrderHVO>(SaleOrderHVO.class);
      retvos = query.query(pk_saleorders);
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return retvos;
  }

  @Override
  public SaleOrderVO querySaleOrderVOByActIDAndOrderID(String pk_saleorder,
      String cmarketactid) throws BusinessException {
    try {
      BillQuery<SaleOrderVO> query =
          new BillQuery<SaleOrderVO>(SaleOrderVO.class);
      SaleOrderVO[] retvos = query.query(new String[] {
        pk_saleorder
      });
      if (retvos == null || retvos.length == 0) {
        return new SaleOrderVO();
      }
      // 匹配到活动的订单表体行
      List<SaleOrderBVO> salebvolist = new ArrayList<SaleOrderBVO>();
      for (SaleOrderVO retvo : retvos) {
        SaleOrderBVO[] salebvos = retvo.getChildrenVO();
        for (SaleOrderBVO salebvo : salebvos) {
          String cbuylargessactid = salebvo.getCbuylargessactid();
          String cpricepromtactid = salebvo.getCpricepromtactid();
          if (PubAppTool.isEqual(cbuylargessactid, cmarketactid)
              || PubAppTool.isEqual(cpricepromtactid, cmarketactid)) {
            salebvolist.add(salebvo);
          }
        }
      }
      SaleOrderBVO[] salebvos =
          salebvolist.toArray(new SaleOrderBVO[salebvolist.size()]);
      retvos[0].setChildrenVO(salebvos);
      return retvos[0];
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return new SaleOrderVO();
  }

  @Override
  public String[] querySaleOrderIDsByActID(String cmarketactid,
      String[] orderFileds) throws BusinessException {
    try {
      SqlBuilder sql = new SqlBuilder();
      sql.append("select distinct so_saleorder.csaleorderid ");

      if (orderFileds != null && orderFileds.length > 0) {
        sql.append(",");
        for (String orderFiled : orderFileds) {
          sql.append("so_saleorder." + orderFiled);
        }
      }

      sql.append(" from so_saleorder so_saleorder ,so_saleorder_b so_saleorder_b");
      sql.append(" where so_saleorder.csaleorderid = so_saleorder_b.csaleorderid");
      // 匹配买赠活动或促销价格活动条件
      sql.append(this.getBuyORPriceActWhere(cmarketactid));
      sql.append(" and");
      sql.append(" so_saleorder.dr", 0);
      if (orderFileds != null && orderFileds.length > 0) {
        sql.append(" order by ");
        for (String orderFiled : orderFileds) {
          sql.append("so_saleorder." + orderFiled);
          sql.append(",");
        }
      }
      sql.deleteLastChar();
      IRowSet rowset = new DataAccessUtils().query(sql.toString());
      if (rowset == null) {
        return new String[0];
      }

      String[] pks = new String[rowset.size()];
      int i = 0;
      while (rowset.next()) {
        pks[i] = rowset.getString(0);
        i++;
      }

      return pks;
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return new String[0];
  }

  /**
   * 根据查询方案和元数据路径Map查询销售订单子表VO，只返回指定字段数据
   *
   * @param columnMapping
   *            元数据路径Map，如：columnMapping.put("brandid",
   *            "cinventoryvid.pk_brand.pk_brand");
   * @param queryScheme
   *            查询方案，主要是queryCondition
   * @param fieldnames
   *            需要查询的字段，使用者拼装，表体字段
   * @return SaleOrderBVO
   * @throws BusinessException
   */
  @Override
  public SaleOrderBVO[] querySaleOrderBVOsByQueryScheme(
      Map<String, String> columnMapping,
      QueryCondition[] queryConditions, String[] fieldnames)
      throws BusinessException {
    if (ArrayUtil.isEmpty(queryConditions) || ArrayUtil.isEmpty(fieldnames)
        || columnMapping == null) {
      return null;
    }
    try {
      // 1、先查询符合条件的表头
      String[] hids = querySaleOrderIDsByScheme(columnMapping,
          queryConditions);

      // 2、再根据已经查询出来的表头IDs和表体的那些condition再查询一遍，只返回表体
      SaleOrderBVO[] vos = querySaleOrderBVOsByScheme(columnMapping,
          queryConditions, fieldnames, hids);
      return vos;
    } catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return null;
  }

  /**
   * 先查询符合条件的表头
   *
   * @param columnMapping
   * @param queryScheme
   * @return
   * @throws BusinessException
   */
  private String[] querySaleOrderIDsByScheme(
      Map<String, String> columnMapping, QueryCondition[] queryConditions)
      throws BusinessException {
    IQueryScheme queryScheme = QuerySchemeCreator
        .createQueryScheme(queryConditions);
    // 拼装SQL，只查询主表ID
    String sql = this
        .getQuerySqlByScheme4HeadID(columnMapping, queryScheme);

    ResultSetProcessor processor = new ColumnListProcessor(SaleOrderHVO.CSALEORDERID);
    @SuppressWarnings("unchecked")
    List<Object> results = (List<Object>) NCLocator.getInstance()
        .lookup(IUAPQueryBS.class).executeQuery(sql, processor);

    if (results == null || results.size() == 0) {
      return null;
    }

    String[] hids = new String[results.size()];
    for (int i = 0; i < hids.length; i++) {
      hids[i] = results.get(i).toString();
    }

    return hids;
  }

  private SaleOrderBVO[] querySaleOrderBVOsByScheme(
      Map<String, String> columnMapping,
      QueryCondition[] queryConditions, String[] fieldnames, String[] hids) {
    if (ArrayUtil.isEmpty(hids)) {
      return null;
    }
    // 拼装SQL
    String sql = this.getQuerySqlByScheme(columnMapping, queryConditions,
        fieldnames, hids);

    // 查询数据
    DataAccessUtils utils = new DataAccessUtils();
    IRowSet rowset = utils.query(sql);

    // 转换类型
    VORowSetMap<SaleOrderBVO> mapping = new VORowSetMap<SaleOrderBVO>(
        SaleOrderBVO.class, fieldnames);
    SaleOrderBVO[] vos = mapping.convert(rowset);

    return vos;
  }

  /**
   * 根据查询方案和元数据路径Map拼装SQL
   *
   * @param columnMapping
   *            元数据路径Map，如：columnMapping.put("brandid",
   *            "cinventoryvid.pk_brand.pk_brand");
   * @param queryScheme
   *            查询方案，主要是queryCondition
   * @param fieldnames
   *            需要查询的字段，使用者拼装
   * @return 查询sql
   */
  private String getQuerySqlByScheme4HeadID(
      Map<String, String> columnMapping, IQueryScheme queryScheme) {
    NonMetaQuerySchemeProcessor processor = new NonMetaQuerySchemeProcessor(
        SaleOrderHVO.class, columnMapping, queryScheme);
    return this.getQuerySaleOrderHIDSQL(processor);
  }

  /**
   * 根据查询方案和元数据路径Map拼装SQL
   *
   * @param columnMapping
   *            元数据路径Map，如：columnMapping.put("brandid",
   *            "cinventoryvid.pk_brand.pk_brand");
   * @param queryScheme
   *            查询方案，主要是queryCondition
   * @param fieldnames
   *            需要查询的字段，使用者拼装
   * @return 查询sql
   */
  private String getQuerySqlByScheme(Map<String, String> columnMapping,
      QueryCondition[] queryConditions, String[] fieldnames, String[] hids) {
    // 把查询条件中的表体条件过滤出来，后面使用。即去掉表头的那些条件
    QueryCondition[] bodyConditions = this
        .getSchemeByfiltedSaleOrderBodyConditions(columnMapping,
            queryConditions);
    // 查询子表之前，要把ColumnMapping中的前缀"so_saleorder_b."去掉
    Map<String, String> columnMappingNew = this
        .getColumnMappingFilted(columnMapping);

    IDQueryBuilder buile = new IDQueryBuilder();
    String insql = buile.buildSQL("so_saleorder_b."
        + SaleOrderBVO.CSALEORDERID, hids);
    String finalSql;

    // 如果没有表体的过滤条件，则说明全是表头的过滤条件，则直接按照主表id去查即可
    if (bodyConditions == null) {
      String finalFromWhere = " from so_saleorder_b so_saleorder_b where so_saleorder_b.dr = 0 and "
          + insql;
      finalSql = getQuerySaleOrderBSQL(fieldnames, finalFromWhere);
    } else {
      IQueryScheme queryScheme = QuerySchemeCreator
          .createQueryScheme(bodyConditions);
      NonMetaQuerySchemeProcessor processor = new NonMetaQuerySchemeProcessor(
          SaleOrderBVO.class, columnMappingNew, queryScheme);
      finalSql = this.getQuerySaleOrderBSQL(processor, fieldnames);
      finalSql += " and " + insql;
    }
    return finalSql;
  }

  /**
   * 查询子表之前，要把ColumnMapping中的前缀"so_saleorder_b."去掉
   *
   * @param columnMapping
   * @return
   */
  private Map<String, String> getColumnMappingFilted(
      Map<String, String> columnMapping) {
    Map<String, String> newColumnMapping = new HashMap<String, String>();
    for (Map.Entry<String, String> entryVO : columnMapping.entrySet()) {
      String columnKey = entryVO.getKey();
      String columnValue = entryVO.getValue();
      if (columnValue.startsWith("so_saleorder_b.")) {
        columnValue = columnValue.substring(
            columnValue.indexOf(".") + 1, columnValue.length());
      }
      newColumnMapping.put(columnKey, columnValue);
    }
    return newColumnMapping;
  }

  /**
   * 把查询条件中的表体条件过滤出来，后面使用。即去掉表头的那些条件
   *
   * @param columnMapping
   * @param queryConditions
   * @return
   */
  private QueryCondition[] getSchemeByfiltedSaleOrderBodyConditions(
      Map<String, String> columnMapping, QueryCondition[] queryConditions) {
    List<QueryCondition> filtedConditions = new ArrayList<QueryCondition>();
    for (QueryCondition queryCondition : queryConditions) {
      String fieldCode = queryCondition.getFieldCode();
      String attrPath = columnMapping.get(fieldCode);

      if (attrPath == null) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4006011_0", "04006011-0479")/*@res 传入的接口参数错误，没有找到对应的元数据路径设置：*/
            + fieldCode);
      }

      // 如果路径中包含了so_saleorder_b，则说明是子表条件，需要进行第二次查询，前提是元数据不做修改
      if (attrPath != null && attrPath.contains("so_saleorder_b")) {
        filtedConditions.add(queryCondition);
      }
    }

    // 如果没有表体的过滤条件，则说明全是表头的过滤条件，则直接按照主表id去查即可
    if (filtedConditions.size() == 0) {
      return null;
    }
    return filtedConditions.toArray(new QueryCondition[0]);
  }

  /**
   * 拼装查询SQL，根据要查询的字段fieldnames拼装select部分
   *
   * @param processor
   * @param fieldnames
   * @return
   */
  private String getQuerySaleOrderBSQL(String[] fieldnames, String finalFromWhere) {

    SqlBuilder sql = new SqlBuilder();
    sql.append("select distinct ");
    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < fieldnames.length; i++) {
      sb.append(", so_saleorder_b.");
      sb.append(fieldnames[i]);
    }
    sql.append(sb.substring(1));
    sql.append(finalFromWhere);
    return sql.toString();
  }

  private String getQuerySaleOrderBSQL(NonMetaQuerySchemeProcessor processor,
      String[] fieldnames) {
    return getQuerySaleOrderBSQL(fieldnames, processor.getFinalFromWhere());
  }

  /**
   * 拼装查询SQL，第一次先根据查询条件查询符合条件的表头
   *
   * @param processor
   * @param fieldnames
   * @return sql
   */
  private String getQuerySaleOrderHIDSQL(NonMetaQuerySchemeProcessor processor) {
    SqlBuilder sql = new SqlBuilder();
    sql.append("select distinct so_saleorder." + SaleOrderHVO.CSALEORDERID + " ");
    sql.append(processor.getFinalFromWhere());
    return sql.toString();
  }

  /**
   * 拼接sql语句
   *
   * @param queryPara
   * @return
   */
  private String getQuerySql(CRMQueryPara queryPara) {
    SqlBuilder sql = new SqlBuilder();
    sql.append("select distinct so.csaleorderid, so.dbilldate ");
    sql.append("from so_saleorder so where so.dr = 0 ");
    if (!PubAppTool.isNull(queryPara.getBusid())) {
      sql.append("and ");
      sql.append("so.cbillsrcid", queryPara.getBusid());
    }
    sql.append(" and ");
    sql.append("so.ccustomerid", queryPara.getCustomerid());
    sql.append(" and ");
    sql.append("so.dbilldate >= '" + queryPara.getDfromdate() + "'");
    sql.append(" and ");
    sql.append("so.dbilldate <= '" + queryPara.getDtodate() + "'");
    sql.append("order by so.dbilldate desc ");

    return sql.toString();
  }

  private String getBuyORPriceActWhere(String actid) {
    SqlBuilder condition = new SqlBuilder();
    condition.append(" and (");
    condition.append("so_saleorder_b.cbuylargessactid", actid);
    condition.append(" or ");
    condition.append("so_saleorder_b.cpricepromtactid", actid);
    condition.append(") and so_saleorder_b.dr = 0 ");
    return condition.toString();
  }

  private String getbuylrgessActWhere(String actid) {
    SqlBuilder condition = new SqlBuilder();
    condition.append(" and ");
    condition.append("so_saleorder_b.cbuylargessactid", actid);
    condition.append(" and ");
    condition.append("so_saleorder_b.blargessflag", "Y");
    return condition.toString();
  }

  private String getPricePromtActWhere(String actid) {
    SqlBuilder condition = new SqlBuilder();
    condition.append(" and ");
    condition.append("so_saleorder_b.cpricepromtactid", actid);
    return condition.toString();
  }

  private String getAuditSQlwhere() {
    SqlBuilder condition = new SqlBuilder();
    condition.append(" ,so_saleorder");
    condition.append(" where");
    condition.append(" so_saleorder_b.csaleorderid =so_saleorder.csaleorderid");
    condition.append(" and");
    condition.append(" so_saleorder.fstatusflag in ('"
        + BillStatus.AUDIT.getStringValue() + "','"
        + BillStatus.CLOSED.getStringValue() + "') ");
    condition.append(" and so_saleorder.dr = 0 ");
    return condition.toString();
  }
}