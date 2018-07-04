package nc.pubimpl.mobile.analy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.pf.mobileapp.MobileAppUtil;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.so.pub.SOConstant;
import nc.vo.so.pub.SOFunc;
import nc.vo.so.pub.SOTable;
import nc.vo.so.pub.util.SOCurrencyUtil;
import nc.vo.so.report.analy.entity.SaleOrderAnalyVO;

import nc.itf.scmpub.reference.uap.permission.DataPermissionService;
import nc.itf.scmpub.reference.uap.permission.FunctionPermissionPubService;
import nc.itf.scmpub.reference.uap.template.BillAccessService;

import nc.pubitf.so.m30.mobile.MyOrderParam;
import nc.pubitf.so.mobile.analy.ISaleOrderAnaly;

import nc.bs.logging.Logger;

import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;

/**
 * 销售订单查询分析实现类
 * 
 * @since 6.1
 * @version 2012-6-18 下午04:13:59
 * @author yixl
 */
public class SaleOrderAnalyImpl implements ISaleOrderAnaly {

  @Override
  public List<Map<String, Object>> qryDayAnalysis(String groupid, String usrid,
      String qrydate, String grouptype, String productlineid, String brandid,
      String channeltypeid, String saleorgid, String customerid) {
    Map<String, Object> result = new HashMap<String, Object>();
    List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
    // 拼查询参数
    SaleOrderAnalyVO para = new SaleOrderAnalyVO();
    para.setPk_group(groupid);
    para.setPk_user(usrid);
    para.setDbilldate(new UFDate(qrydate));
    para.setGroupkey(this.getGroupKeyName(grouptype));
    para.setCprodlineid(productlineid);
    para.setCbrandid(brandid);
    para.setCchanneltypeid(channeltypeid);
    para.setPk_org(saleorgid);
    para.setCcustomerid(customerid);

    try {
      result = this.queryDayAnalyData(para);
      resultList.add(result);
      return resultList;
    }
    catch (Exception e) {
      result.put(MyOrderParam.OPERATION_FLAG, SOConstant.OPRETAION_FAIL);
      result.put(MyOrderParam.RETTURN_MSG, e.getMessage());
      Logger.error(e);
      resultList.add(result);
      return resultList;
    }
  }

  @Override
  public List<Map<String, Object>> qryDayReport(String groupid, String usrid,
      String qrydate, String currid, String grouptype, String bizmanid,
      String customerid, String invid) {
    List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
    Map<String, Object> result = new HashMap<String, Object>();
    // 拼查询参数
    SaleOrderAnalyVO para = new SaleOrderAnalyVO();
    para.setPk_group(groupid);
    para.setPk_user(usrid);
    para.setDbilldate(new UFDate(qrydate));
    para.setGroupkey(this.getGroupKeyNameForReport(grouptype));
    para.setCemployeeid(bizmanid);
    para.setCcustomerid(customerid);
    para.setCmaterialid(invid);
    para.setCorigcurrencyid(currid);
    try {
      result = this.queryAnalyData(para);
      resultList.add(result);
      return resultList;
    }
    catch (Exception e) {
      result.put(MyOrderParam.OPERATION_FLAG, SOConstant.OPRETAION_FAIL);
      result.put(MyOrderParam.RETTURN_MSG, e.getMessage());
      Logger.error(e);
      resultList.add(result);
      return resultList;
    }
  }

  @Override
  public List<Map<String, Object>> qrySOList(String groupid, String usrid,
      String qrydate, String currid, String bizmanid, String customerid,
      String invid, int startline, int count) {
    Map<String, Object> result = new HashMap<String, Object>();
    List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
    // 拼查询参数
    SaleOrderAnalyVO para = new SaleOrderAnalyVO();
    para.setPk_group(groupid);
    para.setPk_user(usrid);
    para.setDbilldate(new UFDate(qrydate));
    para.setCemployeeid(bizmanid);
    para.setCcustomerid(customerid);
    para.setCmaterialid(invid);
    para.setCorigcurrencyid(currid);

    if (PubAppTool.isNull(bizmanid) && PubAppTool.isNull(customerid)
        && PubAppTool.isNull(invid)) {
      result.put(MyOrderParam.OPERATION_FLAG, SOConstant.OPRETAION_FAIL);
      result
          .put(MyOrderParam.RETTURN_MSG, NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006011_0", "04006011-0456")/*@res "业务员、客户、物料三个参数不能同时为空!"*/);
      resultList.add(result);
      return resultList;
    }

    try {
      String sql = this.buildQuerySqlForSOList(para);
      List<Map<String, String>> retList = new ArrayList<Map<String, String>>();
      retList = this.mySOListQueryPage(sql, startline, count);

      result.put(OrderAnalyParam.BILLDATALIST, retList);
      result.put(MyOrderParam.OPERATION_FLAG, SOConstant.OPRETAION_SUCCESS);
      result.put(MyOrderParam.RETTURN_MSG, "");
      resultList.add(result);
      return resultList;
    }
    catch (Exception e) {
      result.put(MyOrderParam.OPERATION_FLAG, SOConstant.OPRETAION_FAIL);
      result.put(MyOrderParam.RETTURN_MSG, e.getMessage());
      Logger.error(e);
      resultList.add(result);
      return resultList;
    }
  }

  /**
   * 拼查询sql
   * 
   * @param para
   * @return
   */
  private String buildQuerySql(SaleOrderAnalyVO para) {
    SqlBuilder querySql = new SqlBuilder();

    // 拼Sql语句的select部分
    querySql.append("select ");
    querySql.append(para.getGroupkey() + ",");
    querySql.append("sum(" + SaleOrderAnalyVO.NNUM + "),");
    querySql.append("sum(" + SaleOrderAnalyVO.NORIGTAXMNY + ") totalmny");
    querySql.append(" from so_mb_orderanaly som");

    this.buildWhereSql(querySql, para);

    return querySql.toString();
  }

  private String buildQuerySqlForDayAnaly(SaleOrderAnalyVO para) {
    SqlBuilder querySql = new SqlBuilder();
    // 取集团本位币是否启用参数
    boolean isGroup = SOCurrencyUtil.getInstance().isGroupCurrencyEnable();

    String qryMoney = "";
    if (isGroup) {
      qryMoney = SaleOrderAnalyVO.NGROUPTAXMNY;
    }
    else {
      qryMoney = SaleOrderAnalyVO.NTAXMNY;
    }
    // 拼Sql语句的select部分
    querySql.append("select ");
    querySql.append(para.getGroupkey() + ",");
    querySql.append("sum(" + SaleOrderAnalyVO.NNUM + "),");
    querySql.append("sum(" + qryMoney + ") totalmny");
    querySql.append(" from so_mb_orderanaly som");
    this.buildWhereSql(querySql, para);
    return querySql.toString();
  }

  /**
   * 拼订单列表的查询sql
   * 
   * @param para
   * @return
   */
  private String buildQuerySqlForSOList(SaleOrderAnalyVO para) {
    SqlBuilder querySql = new SqlBuilder();

    // 拼Sql语句的select部分
    querySql.append("select ");
    querySql.append(SaleOrderAnalyVO.VBILLCODE + ",");
    querySql.append(SaleOrderAnalyVO.CSALEORDERID + ",");
    querySql.append("sum(" + SaleOrderAnalyVO.NNUM + "),");
    querySql.append("sum(" + SaleOrderAnalyVO.NORIGTAXMNY + ") totalmny");
    querySql.append(" from so_mb_orderanaly som");

    // 拼sql的where部分
    UFDate queryDate = para.getDbilldate();
    querySql.append(" where");
    querySql.append(" som.dbilldate ", " >=", queryDate.asBegin().toString());
    querySql.append(" and ");
    querySql.append(" som.dbilldate ", " <=", queryDate.asEnd().toString());

    String pkUser = para.getPk_user();
    String pkGroup = para.getPk_group();

    // 主组织权限
    String[] funcPermissionOrgs =
        FunctionPermissionPubService.getUserPermissionPkOrgs(pkUser,
            SOFunc.A03002.getCode(), pkGroup);
    if (null == funcPermissionOrgs || funcPermissionOrgs.length <= 0) {
      funcPermissionOrgs = new String[] {
        ""
      };
    }
    querySql.append(" and ");
    IDExQueryBuilder builder =
        new IDExQueryBuilder(SOTable.TMP_SO_ID1.getName());
    String orgSqlPart = builder.buildSQL("som.pk_org", funcPermissionOrgs);
    querySql.append(orgSqlPart);

    // 业务员权限
    String dpTableName =
        DataPermissionService.getDataPermProfileTableNameByResourceCode(pkUser,
            SOConstant.PSNDOC, SOConstant.SCMDEFAULT);
    if (!PubAppTool.isNull(dpTableName)) {
      querySql.append(" and ");
      querySql.append("som.cemployeeid in ( select pk_doc from " + dpTableName
          + ")");
    }

    // 业务员
    if (!PubAppTool.isNull(para.getCemployeeid())) {
      querySql.append(" and ");
      querySql.append(SaleOrderAnalyVO.CEMPLOYEEID, para.getCemployeeid());
    }
    // 客户
    if (!PubAppTool.isNull(para.getCcustomerid())) {
      querySql.append(" and ");
      querySql.append(SaleOrderAnalyVO.CCUSTOMERID, para.getCcustomerid());
    }
    // 物料
    if (!PubAppTool.isNull(para.getCmaterialid())) {
      querySql.append(" and ");
      querySql.append(SaleOrderAnalyVO.CMATERIALID, para.getCmaterialid());
    }
    // 币种
    if (!PubAppTool.isNull(para.getCorigcurrencyid())) {
      querySql.append(" and ");
      querySql.append(SaleOrderAnalyVO.CORIGCURRENCYID,
          para.getCorigcurrencyid());
    }

    // 分组
    querySql.append("group by " + SaleOrderAnalyVO.VBILLCODE + " ,"
        + SaleOrderAnalyVO.CSALEORDERID);

    // 排序
    querySql.append(" order by totalmny desc");

    return querySql.toString();

  }

  /**
   * 拼Sql语句的查询条件部分
   * 
   * @param querySql
   * @param para
   *          查询条件
   */
  private void buildWhereSql(SqlBuilder querySql, SaleOrderAnalyVO para) {
    // 拼sql的where部分
    UFDate queryDate = para.getDbilldate();
    querySql.append(" where");
    querySql.append(" som.dbilldate ", " >=", queryDate.asBegin().toString());
    querySql.append(" and ");
    querySql.append(" som.dbilldate ", " <=", queryDate.asEnd().toString());

    String pkUser = para.getPk_user();
    String pkGroup = para.getPk_group();

    // 主组织权限
    String[] funcPermissionOrgs =
        FunctionPermissionPubService.getUserPermissionPkOrgs(pkUser,
            SOFunc.A03002.getCode(), pkGroup);
    if (null == funcPermissionOrgs || funcPermissionOrgs.length <= 0) {
      funcPermissionOrgs = new String[] {
        ""
      };
    }
    querySql.append(" and ");
    IDExQueryBuilder builder =
        new IDExQueryBuilder(SOTable.TMP_SO_ID1.getName());
    String orgSqlPart = builder.buildSQL("som.pk_org", funcPermissionOrgs);
    querySql.append(orgSqlPart);

    // 业务员权限
    String dpTableName =
        DataPermissionService.getDataPermProfileTableNameByResourceCode(pkUser,
            SOConstant.PSNDOC, SOConstant.SCMDEFAULT);
    if (!PubAppTool.isNull(dpTableName)) {
      querySql.append(" and ");
      querySql.append("som.cemployeeid in ( select pk_doc from " + dpTableName
          + ")");
    }

    // 产品线
    if (!PubAppTool.isNull(para.getCprodlineid())) {
      querySql.append(" and ");
      querySql.append(SaleOrderAnalyVO.CPRODLINEID, para.getCprodlineid());
    }
    // 品牌
    if (!PubAppTool.isNull(para.getCbrandid())) {
      querySql.append(" and ");
      querySql.append(SaleOrderAnalyVO.CBRANDID, para.getCbrandid());
    }
    // 销售渠道类型
    if (!PubAppTool.isNull(para.getCchanneltypeid())) {
      querySql.append(" and ");
      querySql
          .append(SaleOrderAnalyVO.CCHANNELTYPEID, para.getCchanneltypeid());
    }
    // 销售组织
    if (!PubAppTool.isNull(para.getPk_org())) {
      querySql.append(" and ");
      querySql.append(SaleOrderAnalyVO.PK_ORG, para.getPk_org());
    }
    // 业务员
    if (!PubAppTool.isNull(para.getCemployeeid())) {
      querySql.append(" and ");
      querySql.append(SaleOrderAnalyVO.CEMPLOYEEID, para.getCemployeeid());
    }
    // 客户
    if (!PubAppTool.isNull(para.getCcustomerid())) {
      querySql.append(" and ");
      querySql.append(SaleOrderAnalyVO.CCUSTOMERID, para.getCcustomerid());
    }
    // 物料
    if (!PubAppTool.isNull(para.getCmaterialid())) {
      querySql.append(" and ");
      querySql.append(SaleOrderAnalyVO.CMATERIALID, para.getCmaterialid());
    }
    // 币种
    if (!PubAppTool.isNull(para.getCorigcurrencyid())) {
      querySql.append(" and ");
      querySql.append(SaleOrderAnalyVO.CORIGCURRENCYID,
          para.getCorigcurrencyid());
    }

    // 分组
    querySql.append("group by " + para.getGroupkey());

    // 排序
    querySql.append(" order by totalmny desc");

  }

  private Map<String, String> getbillMapValue(
      List<Map<String, String>> detailList, String groupKey) {
    Map<String, String> retMap = new HashMap<String, String>();
    if (SaleOrderAnalyVO.CPRODLINEID.equals(groupKey)) {
      String cprodlineId =
          detailList.get(6).get(groupKey + OrderAnalyParam.GROUP_ID);
      // 产品线为空传~
      if (!PubAppTool.isNull(cprodlineId)) {
        retMap.put(OrderAnalyParam.ITEMID, cprodlineId);
        retMap.put(OrderAnalyParam.ITEMNAME, detailList.get(6).get(groupKey));
      }
      else {
        retMap.put(OrderAnalyParam.ITEMID, "~");
        retMap.put(OrderAnalyParam.ITEMNAME, NCLangRes4VoTransl.getNCLangRes()
            .getStrByID("4006011_0", "04006011-0457")/*@res "产品线空"*/);
      }

    }
    else if (SaleOrderAnalyVO.CBRANDID.equals(groupKey)) {
      String cbrandId =
          detailList.get(7).get(groupKey + OrderAnalyParam.GROUP_ID);
      // 品牌为空传~
      if (!PubAppTool.isNull(cbrandId)) {
        retMap.put(OrderAnalyParam.ITEMID, cbrandId);
        retMap.put(OrderAnalyParam.ITEMNAME, detailList.get(7).get(groupKey));
      }
      else {
        retMap.put(OrderAnalyParam.ITEMID, "~");
        retMap.put(OrderAnalyParam.ITEMNAME, NCLangRes4VoTransl.getNCLangRes()
            .getStrByID("4006011_0", "04006011-0460")/*@res "品牌空"*/);
      }

    }
    else if (SaleOrderAnalyVO.CCHANNELTYPEID.equals(groupKey)) {
      String cchanneltypeid =
          detailList.get(4).get(groupKey + OrderAnalyParam.GROUP_ID);
      // 渠道类型为空传~
      if (!PubAppTool.isNull(cchanneltypeid)) {
        retMap.put(OrderAnalyParam.ITEMID, cchanneltypeid);
        retMap.put(OrderAnalyParam.ITEMNAME, detailList.get(4).get(groupKey));
      }
      else {
        retMap.put(OrderAnalyParam.ITEMID, "~");
        retMap.put(OrderAnalyParam.ITEMNAME, NCLangRes4VoTransl.getNCLangRes()
            .getStrByID("4006011_0", "04006011-0459")/*@res "渠道类型空"*/);
      }

    }
    else if (SaleOrderAnalyVO.PK_ORG.equals(groupKey)) {
      retMap.put(OrderAnalyParam.ITEMID,
          detailList.get(0).get(groupKey + OrderAnalyParam.GROUP_ID));
      retMap.put(OrderAnalyParam.ITEMNAME, detailList.get(0).get(groupKey));
    }
    else if (SaleOrderAnalyVO.CCUSTOMERID.equals(groupKey)) {
      retMap.put(OrderAnalyParam.ITEMID,
          detailList.get(2).get(groupKey + OrderAnalyParam.GROUP_ID));
      retMap.put(OrderAnalyParam.ITEMNAME, detailList.get(2).get(groupKey));
    }
    else if (SaleOrderAnalyVO.CEMPLOYEEID.equals(groupKey)) {
      String cemployeeid =
          detailList.get(3).get(groupKey + OrderAnalyParam.GROUP_ID);
      // 业务员为空传~
      if (!PubAppTool.isNull(cemployeeid)) {
        retMap.put(OrderAnalyParam.ITEMID, cemployeeid);
        retMap.put(OrderAnalyParam.ITEMNAME, detailList.get(3).get(groupKey));
      }
      else {
        retMap.put(OrderAnalyParam.ITEMID, "~");
        retMap.put(OrderAnalyParam.ITEMNAME, NCLangRes4VoTransl.getNCLangRes()
            .getStrByID("4006011_0", "04006011-0458")/*@res "业务员空"*/);
      }
    }
    else if (SaleOrderAnalyVO.CMATERIALID.equals(groupKey)) {
      retMap.put(OrderAnalyParam.ITEMID,
          detailList.get(5).get(groupKey + OrderAnalyParam.GROUP_ID));
      retMap.put(OrderAnalyParam.ITEMNAME, detailList.get(5).get(groupKey));
    }
    return retMap;
  }

  /**
   * 销售分析接口将分析项编码转成名称
   * 
   * @param grouptype
   * @return
   */
  private String getGroupKeyName(String grouptype) {
    if (OrderAnalyParam.ANALY_1.equals(grouptype)) {
      return SaleOrderAnalyVO.CPRODLINEID;
    }
    else if (OrderAnalyParam.ANALY_2.equals(grouptype)) {
      return SaleOrderAnalyVO.CBRANDID;
    }
    else if (OrderAnalyParam.ANALY_3.equals(grouptype)) {
      return SaleOrderAnalyVO.CCHANNELTYPEID;
    }
    else if (OrderAnalyParam.ANALY_4.equals(grouptype)) {
      return SaleOrderAnalyVO.PK_ORG;
    }
    else if (OrderAnalyParam.ANALY_5.equals(grouptype)) {
      return SaleOrderAnalyVO.CCUSTOMERID;
    }
    else {
      return "";
    }
  }

  /**
   * 销售日报接口将分析项编码转成名称
   * 
   * @param grouptype
   * @return
   */
  private String getGroupKeyNameForReport(String grouptype) {
    if (OrderAnalyParam.ANALY_1.equals(grouptype)) {
      return SaleOrderAnalyVO.CEMPLOYEEID;
    }
    else if (OrderAnalyParam.ANALY_2.equals(grouptype)) {
      return SaleOrderAnalyVO.CCUSTOMERID;
    }
    else if (OrderAnalyParam.ANALY_3.equals(grouptype)) {
      return SaleOrderAnalyVO.CMATERIALID;
    }
    else {
      return "";
    }
  }

  /**
   * 我的订单列表分页
   * 
   * @param rowset
   * @param start
   * @param count
   * @return
   */
  private List<Map<String, String>> mySOListQueryPage(String sql, int start,
      int count) {
    DataAccessUtils utils = new DataAccessUtils();
    utils.setMaxRows(start + count + 1);
    IRowSet rowset = utils.query(sql);
    int size = rowset.size();

    // 移动应用要求开始位置从1开始
    int newstart = start - 1;
    if (size <= 0 || newstart > size) {
      return null;
    }
    for (int i = 0; i < newstart; i++) {
      rowset.next();
    }
    // 转成Map返回
    List<Map<String, String>> billList = new ArrayList<Map<String, String>>();

    int cursor = 0;
    // 最大取count条数据
    while (cursor < count && rowset.next()) {
      Map<String, String> map = new HashMap<String, String>();
      SaleOrderAnalyVO retVO = new SaleOrderAnalyVO();

      retVO.setVbillcode(rowset.getString(0));
      retVO.setCsaleorderid(rowset.getString(1));
      retVO.setNnum(rowset.getUFDouble(2));
      retVO.setNorigtaxmny(rowset.getUFDouble(3));
      // 解析map
      map = this.resolveBillMapForSOList(retVO);
      billList.add(map);
      cursor++;
    }
    return billList;
  }

  private Map<String, Object> queryAnalyData(SaleOrderAnalyVO para) {
    List<Map<String, String>> retDesList = new ArrayList<Map<String, String>>();
    Map<String, Object> result = new HashMap<String, Object>();
    String sql = this.buildQuerySql(para);
    DataAccessUtils utils = new DataAccessUtils();
    IRowSet rowset = utils.query(sql);
    SaleOrderAnalyVO[] retSViewVO =
        this.turnToSaleOrderAnalyVO(para.getGroupkey(), rowset);

    if (null == retSViewVO || retSViewVO.length <= 0
        || PubAppTool.isNull(para.getCorigcurrencyid())) {
      result.put(MyOrderParam.OPERATION_FLAG, SOConstant.OPRETAION_FAIL);
      result.put(MyOrderParam.RETTURN_MSG, NCLangRes4VoTransl.getNCLangRes()
          .getStrByID("4006011_0", "04006011-0454")/*@res "没有查到有效数据!"*/);
      return result;
    }
    Map<String, String> retDesMap = null;
    for (SaleOrderAnalyVO saleAanalyVO : retSViewVO) {
      retDesMap = new HashMap<String, String>();
      if (null != saleAanalyVO) {
        retDesMap = this.resolveBillMap(saleAanalyVO, para.getGroupkey());
        retDesList.add(retDesMap);
      }

    }

    result.put(OrderAnalyParam.SALEDATALIST, retDesList);
    result.put(MyOrderParam.OPERATION_FLAG, SOConstant.OPRETAION_SUCCESS);
    result.put(MyOrderParam.RETTURN_MSG, "");

    return result;
  }

  private Map<String, Object> queryDayAnalyData(SaleOrderAnalyVO para) {
    List<Map<String, String>> retDesList = new ArrayList<Map<String, String>>();
    Map<String, Object> result = new HashMap<String, Object>();
    String sql = this.buildQuerySqlForDayAnaly(para);

    DataAccessUtils utils = new DataAccessUtils();
    IRowSet rowset = utils.query(sql);

    SaleOrderAnalyVO[] retSViewVO =
        this.turnToSaleOrderAnalyVO(para.getGroupkey(), rowset);

    if (null == retSViewVO || retSViewVO.length <= 0) {
      result.put(MyOrderParam.OPERATION_FLAG, SOConstant.OPRETAION_FAIL);
      result.put(MyOrderParam.RETTURN_MSG, NCLangRes4VoTransl.getNCLangRes()
          .getStrByID("4006011_0", "04006011-0454")/*@res "没有查到有效数据!"*/);
      return result;
    }
    Map<String, String> retDesMap = null;
    for (SaleOrderAnalyVO saleAanalyVO : retSViewVO) {
      if (null != saleAanalyVO) {
        retDesMap = new HashMap<String, String>();
        retDesMap = this.resolveBillMap(saleAanalyVO, para.getGroupkey());
        retDesList.add(retDesMap);
      }
    }

    result.put(OrderAnalyParam.SALEDATALIST, retDesList);
    result.put(MyOrderParam.OPERATION_FLAG, SOConstant.OPRETAION_SUCCESS);
    result.put(MyOrderParam.RETTURN_MSG, "");
    return result;
  }

  /**
   * 解析Uap返回的Map并组成新的map返回
   * 
   * @param saleAanalyVO
   * @param groupKey
   * @return
   */
  @SuppressWarnings("unchecked")
  private Map<String, String> resolveBillMap(SaleOrderAnalyVO saleAanalyVO,
      String groupKey) {
    Map<String, String> retDesMap = new HashMap<String, String>();
    Map<String, List<Map<String, Object>>> map =
        new HashMap<String, List<Map<String, Object>>>();
    List<Map<String, Object>> headList = new ArrayList<Map<String, Object>>();
    Map<String, Object> detail = new LinkedHashMap<String, Object>();

    map =
        BillAccessService.saleorderToMap(OrderAnalyParam.PK_BILLTEMPLET,
            saleAanalyVO);

    headList = map.get(OrderAnalyParam.HEAD);
    detail =
        (Map<String, Object>) headList.get(0).get(OrderAnalyParam.TABCONTENT);

    List<Map<String, String>> detailList = new ArrayList<Map<String, String>>();
    detailList =
        (List<Map<String, String>>) detail.get(OrderAnalyParam.BILLITEMDATA);

    retDesMap = this.getbillMapValue(detailList, groupKey);
    String amout = String.valueOf(detailList.get(8).get(SaleOrderAnalyVO.NNUM));
    if (!PubAppTool.isNull(amout)) {
      retDesMap.put(OrderAnalyParam.AMOUNT,
          String.valueOf(MobileAppUtil.adjust2Scale(new UFDouble(amout))));
    }
    else {
      retDesMap.put(OrderAnalyParam.AMOUNT, "");
    }
    String money =
        String.valueOf(detailList.get(9).get(SaleOrderAnalyVO.NORIGTAXMNY));
    if (!PubAppTool.isNull(money) && !"null".equals(money)) {
      retDesMap.put(OrderAnalyParam.MONEY,
          String.valueOf(MobileAppUtil.adjust2Scale(new UFDouble(money))));
    }
    else {
      retDesMap.put(OrderAnalyParam.MONEY, "");
    }

    return retDesMap;
  }

  /**
   * 解析Uap返回的Map并组成新的map返回供订单列表使用
   * 
   * @param saleAanalyVO
   * @return
   */
  @SuppressWarnings("unchecked")
  private Map<String, String> resolveBillMapForSOList(
      SaleOrderAnalyVO saleAanalyVO) {
    Map<String, String> retDesMap = new HashMap<String, String>();
    Map<String, List<Map<String, Object>>> map =
        new HashMap<String, List<Map<String, Object>>>();
    List<Map<String, Object>> headList = new ArrayList<Map<String, Object>>();
    Map<String, Object> detail = new LinkedHashMap<String, Object>();
    map =
        BillAccessService.saleorderToMap(OrderAnalyParam.PK_BILLTEMPLET,
            saleAanalyVO);

    headList = map.get(OrderAnalyParam.HEAD);
    detail =
        (Map<String, Object>) headList.get(0).get(OrderAnalyParam.TABCONTENT);
    List<Map<String, String>> detailList = new ArrayList<Map<String, String>>();
    detailList =
        (List<Map<String, String>>) detail.get(OrderAnalyParam.BILLITEMDATA);

    retDesMap.put(OrderAnalyParam.BILLID,
        detailList.get(10).get(SaleOrderAnalyVO.CSALEORDERID));
    retDesMap.put(OrderAnalyParam.BILLNO,
        detailList.get(1).get(SaleOrderAnalyVO.VBILLCODE));
    String amout =
        String.valueOf(String.valueOf(detailList.get(8).get(
            SaleOrderAnalyVO.NNUM)));
    if (!PubAppTool.isNull(amout)) {
      retDesMap.put(OrderAnalyParam.AMOUNT,
          String.valueOf(MobileAppUtil.adjust2Scale(new UFDouble(amout))));
    }
    else {
      retDesMap.put(OrderAnalyParam.AMOUNT, "");
    }
    String money =
        String.valueOf(detailList.get(9).get(SaleOrderAnalyVO.NORIGTAXMNY));
    if (!PubAppTool.isNull(amout)) {
      retDesMap.put(OrderAnalyParam.MONEY,
          String.valueOf(MobileAppUtil.adjust2Scale(new UFDouble(money))));
    }
    else {
      retDesMap.put(OrderAnalyParam.MONEY, "");
    }
    return retDesMap;
  }

  /**
   * 将Sql的查询结果转成SaleOrderAnalyVO实体
   * 
   * @param groupkey
   * @param rowset
   * @return
   */
  private SaleOrderAnalyVO[] turnToSaleOrderAnalyVO(String groupkey,
      IRowSet rowset) {
    SaleOrderAnalyVO[] retVO = null;
    if (rowset.size() > 0) {
      if (rowset.size() < 10) {
        retVO = new SaleOrderAnalyVO[rowset.size()];
      }
      else {
        retVO = new SaleOrderAnalyVO[10];
      }
    }
    else {
      return retVO;
    }
    int cursor = 0;
    while (rowset.next()) {
      if (cursor < 10) {
        retVO[cursor] = new SaleOrderAnalyVO();
        String attribute = rowset.getString(0);
        retVO[cursor].setAttributeValue(groupkey, attribute);
        retVO[cursor].setNnum(rowset.getUFDouble(1));
        retVO[cursor].setNorigtaxmny(rowset.getUFDouble(2));
      }
      else {
        break;
      }
      cursor++;
    }
    return retVO;
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<Map<String, Object>> qryCurAndReport(String groupid,
      String usrid, String qrydate, String grouptype, String bizmanid,
      String customerid, String invid) {
    List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
    Map<String, Object> result = new HashMap<String, Object>();
    SqlBuilder querySql = new SqlBuilder();
    querySql.append("select ");
    querySql.append("distinct " + SaleOrderAnalyVO.CORIGCURRENCYID);
    querySql.append(" from so_mb_orderanaly som");

    UFDate queryDate = new UFDate(qrydate);
    querySql.append(" where");
    querySql.append(" som.dbilldate ", " >=", queryDate.asBegin().toString());
    querySql.append(" and ");
    querySql.append(" som.dbilldate ", " <=", queryDate.asEnd().toString());

    // 主组织权限
    String[] funcPermissionOrgs =
        FunctionPermissionPubService.getUserPermissionPkOrgs(usrid,
            SOFunc.A03002.getCode(), groupid);
    if (null == funcPermissionOrgs || funcPermissionOrgs.length <= 0) {
      funcPermissionOrgs = new String[] {
        ""
      };
    }
    querySql.append(" and ");
    IDExQueryBuilder builder =
        new IDExQueryBuilder(SOTable.TMP_SO_ID1.getName());
    String orgSqlPart = builder.buildSQL("som.pk_org", funcPermissionOrgs);
    querySql.append(orgSqlPart);

    // 业务员权限
    String dpTableName =
        DataPermissionService.getDataPermProfileTableNameByResourceCode(usrid,
            SOConstant.PSNDOC, SOConstant.SCMDEFAULT);
    if (!PubAppTool.isNull(dpTableName)) {
      querySql.append(" and ");
      querySql.append("som.cemployeeid in ( select pk_doc from " + dpTableName
          + ")");
    }

    // 取币种排序
    querySql.append(" order by " + SaleOrderAnalyVO.CORIGCURRENCYID);

    DataAccessUtils utils = new DataAccessUtils();
    IRowSet rowset = utils.query(querySql.toString());
    String firstCorcurrency = "";
    List<Map<String, String>> retDesList = new ArrayList<Map<String, String>>();
    Map<String, String> retMap = null;
    int cursor = 0;
    while (rowset.next()) {
      if (0 == cursor) {
        firstCorcurrency = rowset.getString(0);
      }
      retMap = new LinkedHashMap<String, String>();
      SaleOrderAnalyVO retVO = new SaleOrderAnalyVO();
      retVO = new SaleOrderAnalyVO();
      retVO.setCorigcurrencyid(rowset.getString(0));
      retMap = this.resolveMapForCurrList(retVO);
      retDesList.add(retMap);
      cursor++;
    }
    if (PubAppTool.isNull(firstCorcurrency)) {
      result.put(MyOrderParam.OPERATION_FLAG, SOConstant.OPRETAION_FAIL);
      result.put(MyOrderParam.RETTURN_MSG, NCLangRes4VoTransl.getNCLangRes()
          .getStrByID("4006011_0", "04006011-0454")/*@res "没有查到有效数据!"*/);
      resultList.add(result);
      return resultList;
    }
    List<Map<String, Object>> DataList =
        this.qryDayReport(groupid, usrid, qrydate, firstCorcurrency, grouptype,
            bizmanid, customerid, invid);
    List<Map<String, String>> analyData = null;
    if (null != DataList && DataList.size() > 0) {
      Map<String, Object> dataMap = DataList.get(0);
      analyData =
          null == dataMap ? null : (List<Map<String, String>>) dataMap
              .get(OrderAnalyParam.SALEDATALIST);
    }
    result.put(OrderAnalyParam.CURRLIST, retDesList);
    result.put(OrderAnalyParam.SALEDATALIST, analyData);
    result.put(MyOrderParam.OPERATION_FLAG, SOConstant.OPRETAION_SUCCESS);
    result.put(MyOrderParam.RETTURN_MSG, "");
    resultList.add(result);
    return resultList;
  }

  @SuppressWarnings("unchecked")
  private Map<String, String> resolveMapForCurrList(SaleOrderAnalyVO saleAnalyVO) {
    Map<String, String> retDesMap = new HashMap<String, String>();
    Map<String, List<Map<String, Object>>> map =
        new HashMap<String, List<Map<String, Object>>>();
    List<Map<String, Object>> headList = new ArrayList<Map<String, Object>>();
    Map<String, Object> detail = new LinkedHashMap<String, Object>();

    map =
        BillAccessService.saleorderToMap(OrderAnalyParam.PK_BILLTEMPLET_CUR,
            saleAnalyVO);

    headList = map.get(OrderAnalyParam.HEAD);
    detail =
        (Map<String, Object>) headList.get(0).get(OrderAnalyParam.TABCONTENT);
    List<Map<String, String>> detailList = new ArrayList<Map<String, String>>();
    detailList =
        (List<Map<String, String>>) detail.get(OrderAnalyParam.BILLITEMDATA);
    retDesMap.put(OrderAnalyParam.CURRNAME,
        detailList.get(0).get(SaleOrderAnalyVO.CORIGCURRENCYID));
    retDesMap.put(
        OrderAnalyParam.CURRID,
        detailList.get(0).get(
            SaleOrderAnalyVO.CORIGCURRENCYID + OrderAnalyParam.GROUP_ID));
    retDesMap.put(OrderAnalyParam.CSYMBOL,
        detailList.get(1).get(OrderAnalyParam.CURRTYPESIGN));
    return retDesMap;
  }
}
