package nc.pubimpl.so.m30.mobile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import nc.vo.bd.psn.PsndocVO;
import nc.vo.ml.MultiLangUtil;
import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.pf.mobileapp.MobileAppUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.pub.SOConstant;
import nc.vo.so.pub.SOFunc;
import nc.vo.so.pub.SOTable;
import nc.vo.so.pub.comparator.RowNoComparator;

import nc.itf.scmpub.reference.uap.bd.customer.CustomerPubService;
import nc.itf.scmpub.reference.uap.bd.psn.PsndocPubService;
import nc.itf.scmpub.reference.uap.permission.DataPermissionService;
import nc.itf.scmpub.reference.uap.permission.FunctionPermissionPubService;
import nc.itf.scmpub.reference.uap.template.BillAccessService;

import nc.pubitf.so.m30.mobile.IMySaleOrderQuery;
import nc.pubitf.so.m30.mobile.MyOrderParam;
import nc.pubitf.so.mobile.analy.MyOrderGrpType;

import nc.bs.logging.Logger;

import nc.impl.pubapp.pattern.data.bill.BillQuery;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;

/**
 * 我的订单查询分析实现类
 * 
 * @since 6.1
 * @version 2012-6-18 下午04:15:36
 * @author yixl
 */

public class MySaleOrderQueryImpl implements IMySaleOrderQuery {

  @Override
  public List<Map<String, Object>> getBillByCondition(String groupId,
      String userId, int startLine, int count, String condition) {
    Map<String, Object> result = new HashMap<String, Object>();
    List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
    try {
      String sql =
          this.buildBillByConditionSql(groupId, userId, condition, null);
      DataAccessUtils utils = new DataAccessUtils();
      this.setMaxRows(utils, startLine, count);
      IRowSet rowset = utils.query(sql);

      List<Map<String, String>> billList =
          this.myBillListPage(rowset, startLine, count);

      result.put(MyOrderParam.OPERATION_FLAG, SOConstant.OPRETAION_SUCCESS);
      result.put(MyOrderParam.QUERY_RESULT, billList);
      result.put(MyOrderParam.RETTURN_MSG, "");
    }
    catch (Exception e) {
      Logger.error(e);
      result.put(MyOrderParam.OPERATION_FLAG, SOConstant.OPRETAION_FAIL);
      result.put(MyOrderParam.RETTURN_MSG, e.getMessage());
    }
    resultList.add(result);
    return resultList;
  }

  @Override
  public List<Map<String, Object>> getBillDetail(String groupId, String userId,
      String billId, int startLine, int count) {
    Logger.error("groupId" + groupId + "/userId" + userId + "/billId" + billId);
    Map<String, Object> result = new HashMap<String, Object>();
    List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
    try {
      BillQuery<SaleOrderVO> billQry =
          new BillQuery<SaleOrderVO>(SaleOrderVO.class);
      SaleOrderVO[] sales = billQry.query(new String[] {
        billId
      });
      Map<String, Map> retMap = new HashMap<String, Map>();

      if (sales.length <= 0) {
        result.put(MyOrderParam.OPERATION_FLAG, SOConstant.OPRETAION_FAIL);
        result.put(MyOrderParam.RETTURN_MSG, NCLangRes4VoTransl.getNCLangRes()
            .getStrByID("4006011_0", "04006011-0454")/* @res "没有查到有效数据!" */);
        resultList.add(result);
        return resultList;
      }

      Map<String, List<Map<String, Object>>> map =
          new HashMap<String, List<Map<String, Object>>>();

      SaleOrderBVO[] saleBVOs = sales[0].getChildrenVO();
      // 按表体行号排序
      Arrays.sort(saleBVOs, new RowNoComparator(SaleOrderBVO.CROWNO));
      SaleOrderBVO[] afterDealsaleBVOs = null;
      if (saleBVOs.length < 51) {
        afterDealsaleBVOs = new SaleOrderBVO[saleBVOs.length];
      }
      else {
        afterDealsaleBVOs = new SaleOrderBVO[51];
      }

      // 处理精度
      SaleOrderHVO saleHvo = sales[0].getParentVO();
      // 价税合计
      UFDouble ntotalorigmny = saleHvo.getNtotalorigmny();
      saleHvo.setNtotalorigmny(MobileAppUtil.adjust2Scale(ntotalorigmny));
      int cursor = 0;
      for (SaleOrderBVO saleBVO : saleBVOs) {
        // 移动应用只显示前50行记录
        if (cursor < 51 && null != saleBVO) {
          if (null != saleBVO.getNnum()) {
            saleBVO.setNnum(MobileAppUtil.adjust2Scale(saleBVO.getNnum()));
          }
          if (null != saleBVO.getNorigtaxnetprice()) {
            saleBVO.setNorigtaxnetprice(MobileAppUtil.adjust2Scale(saleBVO
                .getNorigtaxnetprice()));
          }
          if (null != saleBVO.getNorigtaxnetprice()) {
            saleBVO.setNorigtaxnetprice(MobileAppUtil.adjust2Scale(saleBVO
                .getNorigtaxnetprice()));
          }
          if (null != saleBVO.getNorigtaxmny()) {
            saleBVO.setNorigtaxmny(MobileAppUtil.adjust2Scale(saleBVO
                .getNorigtaxmny()));
          }
          if (null != saleBVO.getNtotaloutnum()) {
            saleBVO.setNtotaloutnum(MobileAppUtil.adjust2Scale(saleBVO
                .getNtotaloutnum()));
          }
          if (null != saleBVO.getNtotalinvoicenum()) {
            saleBVO.setNtotalinvoicenum(MobileAppUtil.adjust2Scale(saleBVO
                .getNtotalinvoicenum()));
          }
          if (null != saleBVO.getNtotalarmny()) {
            saleBVO.setNtotalarmny(MobileAppUtil.adjust2Scale(saleBVO
                .getNtotalarmny()));
          }
          if (null != saleBVO.getNtotalpaymny()) {
            saleBVO.setNtotalpaymny(MobileAppUtil.adjust2Scale(saleBVO
                .getNtotalpaymny()));
          }
          afterDealsaleBVOs[cursor] = saleBVO;
        }
        else {
          break;
        }
        cursor++;
      }
      sales[0].setChildrenVO(afterDealsaleBVOs);

      map =
          BillAccessService.saleorderToMap(SOConstant.ORDERDTL_BILLTEMPLET,
              sales[0]);
      // 描述业务员，业务员电话号码，客户id，客户名称的属性名称
      Map<String, String> namMap = new HashMap<String, String>();
      namMap.put(MyOrderParam.CEMPLOYEENAMEKEY, MyOrderParam.CEMPLOYEENAME);
      PsndocVO[] psndoc = PsndocPubService.queryPsndocByPks(new String[] {
        sales[0].getParentVO().getCemployeeid()
      }, new String[] {
        PsndocVO.MOBILE
      });

      String mobile = null != psndoc[0] ? psndoc[0].getMobile() : "";
      namMap.put(MyOrderParam.CEMPLOYEEMOBILE, mobile);
      namMap.put(MyOrderParam.CCUSTOMERID, sales[0].getParentVO()
          .getCcustomerid());
      namMap.put(MyOrderParam.CCUSTOMERNAMEKEY, MyOrderParam.CCUSTOMERNAME);

      // 将结果拼接成一个Map
      retMap.put(MyOrderParam.DESCIBE, namMap);
      retMap.put(MyOrderParam.BILLDATA, map);

      result.put(MyOrderParam.OPERATION_FLAG, SOConstant.OPRETAION_SUCCESS);
      result.put(MyOrderParam.QUERY_RESULT, retMap);
      result.put(MyOrderParam.RETTURN_MSG, "");

    }
    catch (Exception e) {
      Logger.error(e);
      result.put(MyOrderParam.OPERATION_FLAG, SOConstant.OPRETAION_FAIL);
      result.put(MyOrderParam.RETTURN_MSG, e.getMessage());
    }

    resultList.add(result);
    Logger.error("getBillDetail订单明细查询：" + resultList);
    return resultList;
  }

  @Override
  public List<Map<String, Object>> getBillListByCustomer(String groupId,
      String userId, int startLine, int count, String customerid) {
    Map<String, Object> result = new HashMap<String, Object>();
    List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
    try {
      String sql =
          this.buildBillByConditionSql(groupId, userId, null, customerid);
      DataAccessUtils utils = new DataAccessUtils();
      this.setMaxRows(utils, startLine, count);
      IRowSet rowset = utils.query(sql);

      List<Map<String, String>> billList =
          this.myBillListPage(rowset, startLine, count);

      result.put(MyOrderParam.OPERATION_FLAG, SOConstant.OPRETAION_SUCCESS);
      result.put(MyOrderParam.QUERY_RESULT, billList);
      result.put(MyOrderParam.RETTURN_MSG, "");
    }
    catch (Exception e) {
      Logger.error(e);
      result.put(MyOrderParam.OPERATION_FLAG, SOConstant.OPRETAION_FAIL);
      result.put(MyOrderParam.RETTURN_MSG, e.getMessage());
    }
    resultList.add(result);
    return resultList;
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<Map<String, Object>> getCustomerDetail(String groupid,
      String userid, String pk_customer) {
    List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
    Map<String, Object> result = new HashMap<String, Object>();
    Map<String, Object> vo = null;
    try {
      vo = CustomerPubService.getBaseCustAndLinkManByPK(pk_customer);

      String multilangseq = MultiLangUtil.getCurrentLangSeqSuffix();
      String custName = null;

      custName = (String) vo.get(MyOrderParam.CNAME + multilangseq);
      if (PubAppTool.isNull(custName)) {
        custName = (String) vo.get(MyOrderParam.CNAME);
      }

      result.put(MyOrderParam.CNAME, custName);
      Map<String, String> custmoerMap = null;
      List<Map<String, String>> custmoerList =
          (List<Map<String, String>>) vo.get(MyOrderParam.CONTACTINFOLIST);
      List<Map<String, String>> custDetail =
          new ArrayList<Map<String, String>>();
      if (custmoerList != null && custmoerList.size() > 0) {
        for (int i = 0; i < custmoerList.size(); i++) {
          custmoerMap = new HashMap<String, String>();
          custmoerMap.put(MyOrderParam.CONTACTPERSON,
              custmoerList.get(i).get(MyOrderParam.CONTACTPERSON));
          custmoerMap.put(MyOrderParam.PHONENUMBER,
              custmoerList.get(i).get(MyOrderParam.CELLNUMBER));
          custDetail.add(custmoerMap);
        }

      }
      else {
        custmoerMap = new HashMap<String, String>();
        custmoerMap.put(MyOrderParam.CONTACTPERSON, "");
        custmoerMap.put(MyOrderParam.PHONENUMBER, "");
        custDetail.add(custmoerMap);
      }

      result.put(MyOrderParam.CONTACTINFOLIST, custDetail);
      result.put(MyOrderParam.OPERATION_FLAG, SOConstant.OPRETAION_SUCCESS);
      result.put(MyOrderParam.RETTURN_MSG, "");
    }
    catch (Exception e) {
      Logger.error(e);
      result.put(MyOrderParam.OPERATION_FLAG, SOConstant.OPRETAION_FAIL);
      result.put(MyOrderParam.RETTURN_MSG, e.getMessage());
    }
    resultList.add(result);
    return resultList;
  }

  @Override
  public List<Map<String, Object>> getMyBillGrpType(String groupId,
      String userId) {
    Map<String, Object> result = new HashMap<String, Object>();
    List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
    try {
      Map<String, String> grpMap = new HashMap<String, String>();
      grpMap.put(MyOrderParam.GROUPTYPE,
          String.valueOf(MyOrderGrpType.GROUPDATE.getFuncCode()));
      grpMap
          .put(MyOrderParam.GROUPNAME, MyOrderGrpType.GROUPDATE.getFuncName());
      List<Map<String, String>> grpTypeList =
          new ArrayList<Map<String, String>>();
      grpTypeList.add(grpMap);

      result.put(MyOrderParam.OPERATION_FLAG, SOConstant.OPRETAION_SUCCESS);
      result.put(MyOrderParam.MYBILLGRPTYPE, grpTypeList);
      result.put(MyOrderParam.RETTURN_MSG, "");
    }
    catch (Exception e) {
      Logger.error(e);
      result.put(MyOrderParam.OPERATION_FLAG, SOConstant.OPRETAION_FAIL);
      result.put(MyOrderParam.RETTURN_MSG, e.getMessage());
    }
    resultList.add(result);
    return resultList;
  }

  @Override
  public List<Map<String, Object>> getMyBillList(String groupId, String userId,
      int startLine, int count, String groupType) {
    Map<String, Object> result = new HashMap<String, Object>();
    List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
    try {
      String sql = this.buildMyBillListSql(groupId, userId);
      DataAccessUtils utils = new DataAccessUtils();
      this.setMaxRows(utils, startLine, count);
      IRowSet rowset = utils.query(sql);
      List<Map<String, String>> billList =
          this.myBillListPage(rowset, startLine, count);

      result.put(MyOrderParam.OPERATION_FLAG, SOConstant.OPRETAION_SUCCESS);
      result.put(MyOrderParam.QUERY_RESULT, billList);
      result.put(MyOrderParam.RETTURN_MSG, "");
    }
    catch (Exception e) {
      Logger.error(e);
      result.put(MyOrderParam.OPERATION_FLAG, SOConstant.OPRETAION_FAIL);
      result.put(MyOrderParam.RETTURN_MSG, e.getMessage());
    }
    resultList.add(result);
    return resultList;
  }

  @Override
  public List<Map<String, Object>> getMyCustomerList(String pk_group,
      String pk_userid, int start, int count) {
    List<Map<String, String>> vo = null;
    List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
    Map<String, Object> result = new HashMap<String, Object>();
    try {
      vo =
          CustomerPubService.getCustomerListByNum(pk_userid, pk_group, start,
              count);
      List<Map<String, String>> retLsit = new ArrayList<Map<String, String>>();
      Map<String, String> custMap = new HashMap<String, String>();

      String multilangseq = MultiLangUtil.getCurrentLangSeqSuffix();
      String custName = null;

      if (vo != null && vo.size() > 0) {
        for (int i = 0; i < vo.size(); i++) {
          Map<String, String> retMap = new HashMap<String, String>();
          custMap = vo.get(i);

          custName = custMap.get(MyOrderParam.CUSTOMER_NAME + multilangseq);
          if (PubAppTool.isNull(custName)) {
            custName = custMap.get(MyOrderParam.CUSTOMER_NAME);
          }

          retMap.put(MyOrderParam.CCUSTOMERID,
              custMap.get(MyOrderParam.CCUSTOMERID));
          retMap.put(MyOrderParam.CUSTOMER_NAME, custName);
          retLsit.add(retMap);
        }
        result.put(MyOrderParam.RETTURN_MSG, "");
      }
      else {
        result.put(MyOrderParam.RETTURN_MSG, NCLangRes4VoTransl.getNCLangRes()
            .getStrByID("4006011_0", "04006011-0454")/* @res "没有查到有效数据!" */);
      }

      result.put(MyOrderParam.OPERATION_FLAG, SOConstant.OPRETAION_SUCCESS);
      result.put(MyOrderParam.QUERY_RESULT, retLsit);

    }
    catch (Exception e) {
      Logger.error(e);
      result.put(MyOrderParam.OPERATION_FLAG, SOConstant.OPRETAION_FAIL);
      result.put(MyOrderParam.RETTURN_MSG, e.getMessage());
    }
    resultList.add(result);
    return resultList;
  }

  @Override
  public List<Map<String, Object>> getSOSerchCondition(String groupId,
      String userId) {
    Map<String, Object> result = new HashMap<String, Object>();
    List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
    try {
      Map<String, String> map = new HashMap<String, String>();
      map.put(MyOrderParam.CONDITIONDESC, NCLangRes4VoTransl.getNCLangRes()
          .getStrByID("4006011_0", "04006011-0455")/* @res "单据号" */);
      List<Map<String, String>> grpTypeList =
          new ArrayList<Map<String, String>>();
      grpTypeList.add(map);

      result.put(MyOrderParam.OPERATION_FLAG, SOConstant.OPRETAION_SUCCESS);
      result.put(MyOrderParam.SERCHCONDITION, grpTypeList);
      result.put(MyOrderParam.RETTURN_MSG, "");
    }
    catch (Exception e) {
      Logger.error(e);
      result.put(MyOrderParam.OPERATION_FLAG, SOConstant.OPRETAION_FAIL);
      result.put(MyOrderParam.RETTURN_MSG, e.getMessage());
    }
    resultList.add(result);
    return resultList;
  }

  /**
   * 条件查询拼Sql
   * 
   * @param orderParam
   * @return
   * @throws BusinessException
   */
  private String buildBillByConditionSql(String groupId, String userId,
      String condition, String customeriId) {
    SqlBuilder querySql = new SqlBuilder();
    querySql.append("select csaleorderid,dbilldate,vbillcode,ccustomerid");
    querySql.append(" from so_mb_myorder");
    querySql.append(" where ");
    querySql.append("pk_group", groupId);

    // 主组织权限
    String[] funcPermissionOrgs =
        FunctionPermissionPubService.getUserPermissionPkOrgs(userId,
            SOFunc.A03002.getCode(), groupId);
    if (null == funcPermissionOrgs || funcPermissionOrgs.length <= 0) {
      funcPermissionOrgs = new String[] {
        ""
      };
    }
    querySql.append(" and ");
    IDExQueryBuilder builder =
        new IDExQueryBuilder(SOTable.TMP_SO_ID1.getName());
    String orgSqlPart = builder.buildSQL("pk_org", funcPermissionOrgs);
    querySql.append(orgSqlPart);

    // 业务员权限
    String dpTableName =
        DataPermissionService.getDataPermProfileTableNameByResourceCode(userId,
            SOConstant.PSNDOC, SOConstant.SCMDEFAULT);
    if (!PubAppTool.isNull(dpTableName)) {
      querySql.append(" and ");
      querySql.append("cemployeeid in ( select pk_doc from " + dpTableName
          + ")");
    }

    if (!PubAppTool.isNull(condition)) {
      querySql.append(" and ");
      querySql.append("vbillcode like '%" + condition + "%' ");
    }
    // 客户
    if (!PubAppTool.isNull(customeriId)) {
      querySql.append(" and ");
      querySql.append("ccustomerid like '%" + customeriId + "%' ");
    }

    querySql.append(" order by dbilldate desc");
    return querySql.toString();
  }

  private String buildMyBillListSql(String groupId, String userId) {
    SqlBuilder querySql = new SqlBuilder();
    querySql.append("select csaleorderid,dbilldate,vbillcode,ccustomerid");
    querySql.append(" from so_mb_myorder");
    querySql.append(" where ");
    querySql.append("pk_group", groupId);

    // 主组织权限
    String[] funcPermissionOrgs =
        FunctionPermissionPubService.getUserPermissionPkOrgs(userId,
            SOFunc.A03002.getCode(), groupId);
    if (null == funcPermissionOrgs || funcPermissionOrgs.length <= 0) {
      funcPermissionOrgs = new String[] {
        ""
      };
    }
    querySql.append(" and ");
    IDExQueryBuilder builder =
        new IDExQueryBuilder(SOTable.TMP_SO_ID1.getName());
    String orgSqlPart = builder.buildSQL("pk_org", funcPermissionOrgs);
    querySql.append(orgSqlPart);

    // 业务员权限
    String dpTableName =
        DataPermissionService.getDataPermProfileTableNameByResourceCode(userId,
            SOConstant.PSNDOC, SOConstant.SCMDEFAULT);
    if (!PubAppTool.isNull(dpTableName)) {
      querySql.append(" and ");
      querySql.append("cemployeeid in ( select pk_doc from " + dpTableName
          + ")");
    }
    querySql.append(" order by dbilldate desc");
    return querySql.toString();
  }

  /**
   * 查询订单明细分页
   * 
   * @param rowset
   * @param start
   * @param count
   * @return
   */
  private List<Map<String, String>> myBillListPage(IRowSet rowset, int start,
      int count) {

    // 移动应用开始位置从1开始
    int newstart = start - 1;

    int size = rowset.size();

    // 开始位置大于总结果数
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
      Map<String, List<Map<String, Object>>> map =
          new HashMap<String, List<Map<String, Object>>>();
      Map<String, String> resMap = new HashMap<String, String>();
      SaleOrderVO saleorder = new SaleOrderVO();
      SaleOrderHVO saleHvo = new SaleOrderHVO();
      saleHvo.setCsaleorderid(rowset.getString(0));
      saleHvo.setDbilldate(rowset.getUFDate(1));
      saleHvo.setVbillcode(rowset.getString(2));
      saleHvo.setCcustomerid(rowset.getString(3));
      saleorder.setParentVO(saleHvo);
      map =
          BillAccessService.saleorderToMap(SOConstant.MYORDER_BILLTEMPLET,
              saleorder);

      resMap = this.resolveBillMap(map);

      billList.add(resMap);
      cursor++;
    }
    return billList;
  }

  @SuppressWarnings("unchecked")
  private Map<String, String> resolveBillMap(
      Map<String, List<Map<String, Object>>> map) {
    Map<String, String> retDesMap = new HashMap<String, String>();
    List<Map<String, Object>> headList = new ArrayList<Map<String, Object>>();
    Map<String, Object> detail = new LinkedHashMap<String, Object>();

    headList = map.get(MyOrderParam.HEAD);
    detail = (Map<String, Object>) headList.get(0).get(MyOrderParam.TABCONTENT);

    List<Map<String, String>> detailList = new ArrayList<Map<String, String>>();
    detailList =
        (List<Map<String, String>>) detail.get(MyOrderParam.BILLITEMDATA);

    retDesMap.put(MyOrderParam.BILLID,
        detailList.get(0).get(MyOrderParam.CSALEORDERID));
    retDesMap.put(MyOrderParam.BILLDATE,
        String.valueOf(detailList.get(1).get(MyOrderParam.DBILLDATE))
            .substring(0, 10));
    retDesMap.put(MyOrderParam.BILLNO,
        detailList.get(2).get(MyOrderParam.VBILLCODE));
    retDesMap.put(MyOrderParam.CUSTOMER,
        detailList.get(3).get(MyOrderParam.CCUSTOMERID_UAP));

    return retDesMap;
  }

  /**
   * 设置最大返回条数
   * 
   * @param utils
   * @param orderParam
   */
  private void setMaxRows(DataAccessUtils utils, int startLine, int count) {

    int maxRow = startLine + count + 1;
    utils.setMaxRows(maxRow);
  }

}
