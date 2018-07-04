package nc.impl.so.custmatrel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.bs.dao.BaseDAO;
import nc.bs.dao.DAOException;
import nc.bs.framework.common.NCLocator;
import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.pattern.data.bill.BillQuery;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.database.IDQueryBuilder;
import nc.impl.pubapp.pattern.database.TempTable;
import nc.impl.so.custmatrel.action.DeleteAction;
import nc.impl.so.custmatrel.action.InsertAction;
import nc.impl.so.custmatrel.action.UpdateAction;
import nc.itf.scmpub.reference.uap.org.OrgUnitPubService;
import nc.itf.so.custmatrel.ICustMatRelMaintain;
import nc.jdbc.framework.SQLParameter;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.bd.cust.CustomerVO;
import nc.vo.bd.cust.saleinfo.CustsaleVO;
import nc.vo.bd.material.MaterialVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.JavaType;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.pubapp.query2.sql.process.QuerySchemeProcessor;
import nc.vo.scmpub.util.ArrayUtil;
import nc.vo.scmpub.util.ListUtil;
import nc.vo.so.custmatrel.entity.CustMatRelBVO;
import nc.vo.so.custmatrel.entity.CustMatRelHVO;
import nc.vo.so.custmatrel.entity.CustMatRelVO;
import nc.vo.so.custmatrel.paravo.CompareParaVO;

public class CustMatRelMaintainImpl implements ICustMatRelMaintain {

  @Override
  public CustMatRelVO update(CustMatRelVO bill) throws BusinessException {
    UpdateAction action = new UpdateAction();
    return action.update(bill);
  }

  @Override
  public CustMatRelVO delete(CustMatRelVO bill) throws BusinessException {
    DeleteAction action = new DeleteAction();
    return action.delete(bill);
  }

  @Override
  public CustMatRelVO insert(CustMatRelVO bill) throws BusinessException {
    InsertAction action = new InsertAction();
    return action.insert(bill);
  }

  @Override
  public CustMatRelVO queryByOrg(String pk_org) throws BusinessException {
    CustMatRelVO bill = null;
    try {
      DataAccessUtils utils = new DataAccessUtils();
      StringBuffer whereSql = new StringBuffer();
      whereSql.append("so_custmatrel.pk_org = '" + pk_org + "' ").append(
          "and so_custmatrel.dr = 0 ");
      String sql = "select pk_custmatrel from so_custmatrel where " + whereSql;
      IRowSet rowset = utils.query(sql);
      String[] cbillids = rowset.toOneDimensionStringArray();

      // 根据id查询VO
      BillQuery<CustMatRelVO> query =
          new BillQuery<CustMatRelVO>(CustMatRelVO.class);
      CustMatRelVO[] bills = query.query(cbillids);
      if ((bills != null) && (bills.length > 0)) {
        bill = bills[0];
      }

    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return bill;
  }

  /**
   * 
   * 查询表头、表体VO再组装成聚合VO
   * 
   */
  @Override
  public CustMatRelVO[] queryCustMatRel(IQueryScheme queryScheme)
      throws BusinessException {
    QuerySchemeProcessor qrySchemeProcessor =
        new QuerySchemeProcessor(queryScheme);
    // 获取查询条件pk_org的值
    String pk_org = this.getQueryValue(qrySchemeProcessor, "pk_org");
    // 如果选择勾选下级，则查出的结构内就会包含该客户分类及其下级，获取查询条件pk_custbaseclass的值
    String[] pk_custbaseclass =
        this.getCustBaseClassQueryValue(qrySchemeProcessor,
            "pk_custmatrel_b.pk_custbaseclass");
    // 获取查询条件pk_custsaleclass的值
    String[] pk_custsaleclass =
        this.getCustBaseClassQueryValue(qrySchemeProcessor,
            "pk_custmatrel_b.pk_custsaleclass");
    // 获取查询条件pk_customer的值
    String pk_customer =
        this.getQueryValue(qrySchemeProcessor, "pk_custmatrel_b.pk_customer");
    // 获取查询条件pk_marbasclass的值
    String[] pk_marbasclass =
        this.getCustBaseClassQueryValue(qrySchemeProcessor,
            "pk_custmatrel_b.pk_materialbaseclass");
    // 获取查询条件pk_marsaleclass的值
    String[] pk_marsaleclass =
        this.getCustBaseClassQueryValue(qrySchemeProcessor,
            "pk_custmatrel_b.pk_materialsaleclass");
    // 获取查询条件pk_material的值
    String pk_material =
        this.getQueryValue(qrySchemeProcessor, "pk_custmatrel_b.pk_material");
    try {
      // 1、根据pk_org查询表头VO，应该只有一个
      CustMatRelHVO relHVO = this.queryCustMatRelHVOByORG(pk_org);
      if (relHVO == null) {
        return null;
      }

      // 2、根据HVO中的PK、客户分类、客户信息查询表体VO数组
      CustMatRelBVO[] relBVOs =
          this.queryCustMatRelBVOsByORG(relHVO, pk_custbaseclass,
              pk_custsaleclass, pk_customer, pk_marbasclass, pk_marsaleclass,
              pk_material);

      // 3、将表头VO和表体VO组装成聚合VO，并返回
      CustMatRelVO aggVO = this.buildCustMatRelVO(relHVO, relBVOs);
      return new CustMatRelVO[] {
        aggVO
      };
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return null;
  }

  /**
   * 获取查询条件的值
   * 
   * @param qrySchemeProcessor
   * @param code
   *          查询条件
   * @return pk
   */
  private String getQueryValue(QuerySchemeProcessor qrySchemeProcessor,
      String code) {
    String[] queryConditionValue = null;
    if (qrySchemeProcessor.getQueryCondition(code) != null) {
      queryConditionValue =
          qrySchemeProcessor.getQueryCondition(code).getValues();
      // 由于每个查询条件内只允许选一个查询条件，这里取数组内第一个值
      return queryConditionValue[0];
    }
    return null;
  }

  /**
   * 获取查询条件的值
   * 
   * @param qrySchemeProcessor
   * @param code
   *          查询条件
   * @return pk
   */
  private String[] getCustBaseClassQueryValue(
      QuerySchemeProcessor qrySchemeProcessor, String code) {
    String[] queryConditionValue = null;
    if (qrySchemeProcessor.getQueryCondition(code) != null) {
      queryConditionValue =
          qrySchemeProcessor.getQueryCondition(code).getValues();
      // 由于每个查询条件内只允许选一个查询条件，这里取数组内第一个值
      if (queryConditionValue[0].trim().startsWith("select")) {
        String sql = queryConditionValue[0];
        DataAccessUtils utils = new DataAccessUtils();
        IRowSet results = utils.query(sql.toString());
        return results.toOneDimensionStringArray();
      }
      return queryConditionValue;
    }
    return null;
  }

  /**
   * 将表头VO和表体VO组装成聚合VO，并返回
   * 
   * @param relHVO
   *          表头VO
   * @param relBVO
   *          表体vo
   * @return
   */
  private CustMatRelVO buildCustMatRelVO(CustMatRelHVO relHVO,
      CustMatRelBVO[] relBVO) {
    CustMatRelVO aggVO = new CustMatRelVO();
    aggVO.setParentVO(relHVO);
    aggVO.setChildrenVO(relBVO);
    return aggVO;
  }

  /**
   * 
   * 根据HVO中的PK、客户分类、客户信息查询表体VO数组
   * 
   * @param relHVO
   * @param pk_custbaseclass
   * @param pk_customer
   * @return
   * @throws BusinessException
   * 
   */
  private CustMatRelBVO[] queryCustMatRelBVOsByORG(CustMatRelHVO relHVO,
      String[] pk_custbaseclass, String[] pk_custsaleclass, String pk_customer,
      String[] pk_marbasclass, String[] pk_marsaleclass, String pk_material)
      throws BusinessException {
    StringBuilder querySql = new StringBuilder();
    querySql
        .append(" select distinct pk_custmatrel_b from so_custmatrel_b b where b.pk_custmatrel = '"
            + relHVO.getPk_custmatrel() + "'");
    querySql.append(" and b.dr = 0 ");

    StringBuilder inSQLALL = new StringBuilder();

    // 这里有个潜规则，pk_custbaseclass和pk_custsaleclass最多只会有一个有值，所以两个之间没有用and连接
    if (!ArrayUtil.isEmpty(pk_custbaseclass)) {
      // 当“客户基本分类”存在值，且存在下级分类时使用下面的构造sql方法
      if (inSQLALL.length() > 0) {
        inSQLALL.append(" or ");
      }
      inSQLALL.append(getSQLForClass(CustMatRelBVO.PK_CUSTBASECLASS,
          pk_custbaseclass));
      if (inSQLALL.length() > 0) {
        inSQLALL.append(" or ");
      }
      // 将客户基本分类下的客户都查询出来
      inSQLALL.append(getInSQLForCustomerBaseClass(CustomerVO.PK_CUSTCLASS,
          pk_custbaseclass));
    }
    if (!ArrayUtil.isEmpty(pk_custsaleclass)) {
      if (inSQLALL.length() > 0) {
        inSQLALL.append(" or ");
      }
      inSQLALL.append(getSQLForClass(CustMatRelBVO.PK_CUSTSALECLASS,
          pk_custsaleclass));
      if (inSQLALL.length() > 0) {
        inSQLALL.append(" or ");
      }
      // 将客户销售分类下的客户都查询出来
      inSQLALL.append(getInSQLForCustomerBaseClass(CustsaleVO.PK_CUSTSALECLASS,
          pk_custsaleclass));
    }
    // 这里有个潜规则，pk_marbasclass和pk_marsaleclass最多只会有一个有值，所以两个之间没有用and连接
    if (!ArrayUtil.isEmpty(pk_marbasclass)) {
      if (inSQLALL.length() > 0) {
        inSQLALL.append(" or ");
      }
      inSQLALL.append(getSQLForClass(CustMatRelBVO.PK_MATERIALBASECLASS,
          pk_marbasclass));

      if (inSQLALL.length() > 0) {
        inSQLALL.append(" or ");
      }
      inSQLALL.append(getInSQLForMaterialBaseClass(MaterialVO.PK_MATERIAL,
          pk_marbasclass));
    }
    if (!ArrayUtil.isEmpty(pk_marsaleclass)) {
      if (inSQLALL.length() > 0) {
        inSQLALL.append(" or ");
      }
      inSQLALL.append(getSQLForClass(CustMatRelBVO.PK_MATERIALSALECLASS,
          pk_marsaleclass));

      if (inSQLALL.length() > 0) {
        inSQLALL.append(" or ");
      }
      // 将物料销售分类下的物料都查询出来
      inSQLALL.append(getInSQLForMaterialBaseClass(MaterialVO.PK_MATERIAL,
          pk_marsaleclass));
    }
    StringBuilder customerSql = new StringBuilder();
    if (!PubAppTool.isNull(pk_customer)) {
      customerSql.append(" b.pk_customer = '");
      customerSql.append(pk_customer);
      customerSql.append("'");
      if (inSQLALL.length() > 0) {
        inSQLALL.append(" or ");
      }
      inSQLALL.append(customerSql);
    }
    StringBuilder materialSql = new StringBuilder();
    if (!PubAppTool.isNull(pk_material)) {
      materialSql.append(" b.pk_material = '");
      materialSql.append(pk_material);
      materialSql.append("'");

      if (inSQLALL.length() > 0) {
        inSQLALL.append(" or ");
      }
      inSQLALL.append(materialSql);
    }

    if (inSQLALL.length() > 0) {
      querySql.append(" and (");
      querySql.append(inSQLALL);
      querySql.append(" ) ");
    }

    return queryBvosByprimarykey(querySql);
  }

  private String getSQLForClass(String classKeyCode, String[] classValues) {
    IDQueryBuilder builder = new IDQueryBuilder();
    String inSql = builder.buildSQL(classKeyCode, classValues);
    return inSql;
  }

  private String getInSQLForMaterialBaseClass(String detailKey,
      String[] classValues) {
    SqlBuilder inSql = new SqlBuilder();
    inSql.append(" select ");
    inSql.append(MaterialVO.PK_MATERIAL);
    inSql.append(" from ");
    inSql.append(MaterialVO.getDefaultTableName());
    inSql.append(" where ");
    inSql.append(MaterialVO.getDefaultTableName() + ".dr", 0);
    inSql.append(" and ");
    inSql.append(MaterialVO.PK_MARBASCLASS, classValues);

    StringBuilder sql = new StringBuilder();
    sql.append(" ");
    sql.append(detailKey);
    sql.append(" in (");
    sql.append(inSql);
    sql.append(" )");
    return sql.toString();
  }

  /**
   * 
   * 
   * @param detailKey
   * @param classValues
   * @return
   */
  private String getInSQLForCustomerBaseClass(String detailKey,
      String[] classValues) {
    SqlBuilder inSql = new SqlBuilder();
    inSql.append(" select ");
    inSql.append(CustomerVO.PK_CUSTOMER);
    inSql.append(" from ");
    inSql.append(CustomerVO.getDefaultTableName());
    inSql.append(" where ");
    inSql.append(CustomerVO.getDefaultTableName() + ".dr", 0);
    inSql.append(" and ");
    inSql.append(detailKey, classValues);

    StringBuilder sql = new StringBuilder();
    sql.append(" ");
    sql.append(CustomerVO.PK_CUSTOMER);
    sql.append(" in (");
    sql.append(inSql);
    sql.append(" )");
    return sql.toString();
  }

  /**
   * 查询出符合条件的表体，并进行判断
   * 
   * @param querySql
   * @return
   */
  private CustMatRelBVO[] queryBvosByprimarykey(StringBuilder querySql)
      throws BusinessException {
    // 查询出符合条件的表体主键
    DataAccessUtils utils = new DataAccessUtils();
    IRowSet results = utils.query(querySql.toString());
    List<String> primaryKeys = new ArrayList<String>();
    for (String res : results.toOneDimensionStringArray()) {
      primaryKeys.add(res);
    }
    // 通过查询出的pks将符合条件的bvos都查出来
    if (ListUtil.isEmpty(primaryKeys)) {
      return null;
    }
    VOQuery<CustMatRelBVO> qrybvos =
        new VOQuery<CustMatRelBVO>(CustMatRelBVO.class);
    CustMatRelBVO[] bvos = qrybvos.query(ListUtil.toArray(primaryKeys));

    // 当表体行数大于一万行时要提示“标题数量过大，请再增加查询条件，缩小范围”
    if (bvos.length > 10000) {
      ExceptionUtils
          .wrappBusinessException(NCLangResOnserver.getInstance().getStrByID(
              "4006007_0", "04006007-0048")/*查询出的数据大于一万行，请增加查询条件重新查询！*/);
    }
    return bvos;
  }

  /**
   * 根据pk_org查询表头VO，应该只有一个
   * 
   * @param pk_org
   * @return
   */
  private CustMatRelHVO queryCustMatRelHVOByORG(String pk_org)
      throws BusinessException {
    StringBuffer whereSql = new StringBuffer();
    whereSql.append(" so_custmatrel.dr = 0 and ").append(
        "so_custmatrel.pk_org = ? ");

    // 构造查询参数
    SQLParameter params = new SQLParameter();
    if (!PubAppTool.isNull(pk_org)) {
      params.addParam(pk_org);
    }
    // 使用BaseDAO中的retrieveByClause方法来进行参数查询
    BaseDAO dao = new BaseDAO();
    @SuppressWarnings("unchecked")
    List<CustMatRelHVO> hvos =
        (List<CustMatRelHVO>) dao.retrieveByClause(CustMatRelHVO.class,
            whereSql.toString(), params);
    if (hvos != null && hvos.size() > 0) {
      // 这里查出的表头只有一个，
      return hvos.get(0);
    }
    else {
      return null;
    }
  }

  @Override
  public void importXLS(CustMatRelVO[] bills) throws BusinessException {
    // 校验bills中是否存在多个销售组织，如果是则抛出提示信息
    Set<String> orgSet = new HashSet<String>();
    String pk_group = AppContext.getInstance().getPkGroup();
    for (CustMatRelVO bill : bills) {
      CustMatRelHVO hvo = bill.getParentVO();
      orgSet.add(hvo.getPk_org());
      // 判断导入文件内组织
      showMessage(orgSet);
    }
    // 1.取表体VO中的字段
    CompareParaVO compareParaVO = combinParaByCusVO(bills);

    // 取销售组织编码与PK之间的映射关系
    Map<String, String> orgMap =
        setCodeTOPK("code", "pk_org", "org_orgs_v", compareParaVO.getOrgList());
    String org = orgMap.get(compareParaVO.getOrgList()[0]);
    // 判断导入文件的“允许/禁止销售”字段同数据库内该组织下的“允许/禁止销售”字段是否相同，不相同就不允许保存
    judgeAllowSaleIsCorrect(org, bills);
    // 取客户基本分类编码与PK之间的映射关系
    Map<String, String> custbaseclassMap =
        setCodeTOPK("code", "pk_custclass", "bd_custclass",
            compareParaVO.getCustBase(), org, "04006007-0068");
    // 取客户销售分类编码与PK之间的映射关系
    Map<String, String> custsaleclassMap =
        setCodeTOPK("code", "pk_custsaleclass", "bd_custsaleclass",
            compareParaVO.getCustSale(), org, "04006007-0069");
    // 取客户编码与PK之间的映射关系
    Map<String, String> customerMap =
        setCustomerCodeTOPK("code", "a.pk_customer", compareParaVO.getCustom(),
            org);
    // 校验导入文件内客户基本/销售分类，客户的正确性
    checkCustMessage(compareParaVO);
    // 取物料基本分类编码与PK之间的映射关系
    Map<String, String> marbasclassMap =
        setCodeTOPK("code", "pk_marbasclass", "bd_marbasclass",
            compareParaVO.getMatBase(), org, "04006007-0071");
    // 取物料销售分类编码与PK之间的映射关系
    Map<String, String> marsaleclassMap =
        setCodeTOPK("code", "pk_marsaleclass", "bd_marsaleclass",
            compareParaVO.getMatSale(), org, "04006007-0072");
    // 取物料编码与PK之间的映射关系
    Map<String, String> materialMap =
        setMaterialCodeTOPK("code", "a.pk_material",
            compareParaVO.getMaterial(), org);
    // 校验导入文件内物料基本/销售分类，物料的正确性
    checkCustMessage(compareParaVO);
    // 构建一个map，存放pk_org和pk_custmatrel
    Map<String, String> pkMap = getORGAndPKMap();
    // 根据pk_org获取pk_org_v并赋给bills
    bills = setPkOrgVValues(bills, orgMap);
    Map<String, String> matMap =
        getmaterial(materialMap.values()
            .toArray(new String[materialMap.size()]));

    for (CustMatRelVO bill : bills) {
      CustMatRelHVO hvoItem = bill.getParentVO();
      hvoItem.setAttributeValue("pk_group", pk_group);
      // 通过pk_org判断该组织是否存在数据，再修改表头状态
      boolean flag = false;
      if (pkMap.get(hvoItem.getPk_org()) != null) {
        hvoItem.setStatus(VOStatus.UPDATED);
        flag = true;
      }
      else {
        hvoItem.setStatus(VOStatus.NEW);
      }
      CustMatRelBVO[] bvoItems = bill.getChildrenVO();
      for (int i = 0; i < bvoItems.length; i++) {
        CustMatRelBVO bvoItem = bvoItems[i];
        bvoItem.setAttributeValue("pk_custbaseclass",
            custbaseclassMap.get(bvoItem.getPk_custbaseclass()));
        bvoItem.setAttributeValue("pk_custsaleclass",
            custsaleclassMap.get(bvoItem.getPk_custsaleclass()));
        bvoItem.setAttributeValue("pk_customer",
            customerMap.get(bvoItem.getPk_customer()));
        bvoItem.setAttributeValue("pk_materialbaseclass",
            marbasclassMap.get(bvoItem.getPk_materialbaseclass()));
        bvoItem.setAttributeValue("pk_materialsaleclass",
            marsaleclassMap.get(bvoItem.getPk_materialsaleclass()));
        bvoItem.setAttributeValue("pk_material_v",
            materialMap.get(bvoItem.getPk_material_v()));
        bvoItem.setAttributeValue("pk_material",
            matMap.get(bvoItem.getPk_material_v()));
        // 根据pk_material_v获取pk_material并赋给bvoItem
        bvoItem.setAttributeValue("pk_custmatrel",
            pkMap.get(hvoItem.getPk_org()));
        bvoItem.setAttributeValue("pk_org", hvoItem.getPk_org());
        bvoItem.setAttributeValue("pk_group", hvoItem.getPk_group());
        bvoItem.setStatus(VOStatus.NEW);
        bvoItems[i] = bvoItem;
      }
      // 通过pk_org获取表头pk并赋值给hvoItem
      hvoItem
          .setAttributeValue("pk_custmatrel", pkMap.get(hvoItem.getPk_org()));
      // 使用VOQuery查询出数据库内的表头
      VOQuery<CustMatRelHVO> sql =
          new VOQuery<CustMatRelHVO>(CustMatRelHVO.class);
      String[] pks = new String[] {
        hvoItem.getPk_custmatrel()
      };
      // 如果hvoItem有主键，将数据库内表头的Ts赋值给它
      if (hvoItem.getPk_custmatrel() != null) {
        CustMatRelHVO[] hvos = sql.query(pks);
        hvoItem.setAttributeValue("ts", hvos[0].getTs());
      }
      bill.setChildrenVO(bvoItems);
      bill.setParentVO(hvoItem);
      if (flag) {

        // 调用Update方法，对bill进行处理
        NCLocator.getInstance().lookup(ICustMatRelMaintain.class).update(bill);
      }
      else {
        // 调用insert方法，对bill进行处理
        NCLocator.getInstance().lookup(ICustMatRelMaintain.class).insert(bill);
      }
    }
  }

  /**
   * 判断导入文件的“是否允销”和数据库内的该组织是否相同
   * 
   * @param orgSet
   */
  /**
   * @param orgSet 存放的是组织编码
   * @param orgMap <组织编码，组织pk>
   * @param bills 导入文件vo
   */
  private void judgeAllowSaleIsCorrect(String org, CustMatRelVO[] bills) {
    StringBuilder whereSql = new StringBuilder();
    whereSql
        .append(" select distinct allowsale from so_custmatrel a where a.dr = 0 and a.pk_org = '");
    whereSql.append(org);
    whereSql.append("' ");
    DataAccessUtils utils = new DataAccessUtils();
    IRowSet results = utils.query(whereSql.toString());
    if (results.toOneDimensionStringArray() != null
        && results.toOneDimensionStringArray().length > 0) {
      String allowsale = results.toOneDimensionStringArray()[0];
      if (!allowsale.equals(bills[0].getParentVO().getAllowsale().toString())) {
        ExceptionUtils
            .wrappBusinessException(NCLangResOnserver.getInstance().getStrByID(
                "4006007_0", "04006007-0049")/*导入数据和数据库内数据【允许销售/禁止销售】字段的值不一致，不允许导入*/);
      }
    }
  }

  /**
   * 判断导入文件组织是否输入，是否是一个组织
   * 
   * @param orgSet
   */
  private void showMessage(Set<String> orgSet) {
    String[] pk_org = orgSet.toArray(new String[orgSet.size()]);
    if (pk_org.length > 1) {
      ExceptionUtils
          .wrappBusinessException(NCLangResOnserver.getInstance().getStrByID(
              "4006007_0", "04006007-0050")/*导入的数据中存在多个销售组织，请改为一个销售组织重新导入*/);
    }
    if (pk_org[0] == null || pk_org.length == 0) {
      ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
          .getStrByID("4006007_0", "04006007-0051")/*导入的数据中没有录入销售组织*/);
    }
  }

  /**
   * 判断传入的map是否为空，传入的3个map不能同时有值
   * 
   * @param map1
   * @param map2
   * @param map3
   */
  private void checkCustMessage(CompareParaVO compareParaVO) {
    StringBuilder strMes = new StringBuilder();
    for (int i = 0; i < compareParaVO.getExclude().length; i++) {
      if (isNotEmpty(compareParaVO.getCustBase()[i])
          + isNotEmpty(compareParaVO.getCustSale()[i])
          + isNotEmpty(compareParaVO.getCustom()[i]) >= 2) {
        if (!PubAppTool.isNull(compareParaVO.getCustBase()[i])) {
          strMes.append(NCLangResOnserver.getInstance().getStrByID("4006007_0",
              "04006007-0039", null, new String[] {
                compareParaVO.getCustBase()[i]
              })/*客户基本分类 [{0}],*/);
        }
        if (!PubAppTool.isNull(compareParaVO.getCustSale()[i])) {
          strMes.append(NCLangResOnserver.getInstance().getStrByID("4006007_0",
              "04006007-0052", null, new String[] {
                compareParaVO.getCustSale()[i]
              })/*客户销售分类 [{0}],*/);
        }
        if (!PubAppTool.isNull(compareParaVO.getCustom()[i])) {
          strMes.append(NCLangResOnserver.getInstance().getStrByID("4006007_0",
              "04006007-0053", null, new String[] {
                compareParaVO.getCustom()[i]
              })/*客户 [{0}],*/);
        }
        strMes.append(NCLangResOnserver.getInstance().getStrByID("4006007_0",
            "04006007-0054")/* 不能同时有值！\r\n */);
        ExceptionUtils.wrappBusinessException(strMes.toString());
      }
      else if (isNotEmpty(compareParaVO.getMatBase()[i])
          + isNotEmpty(compareParaVO.getMatSale()[i])
          + isNotEmpty(compareParaVO.getMaterial()[i]) >= 2) {
        if (!PubAppTool.isNull(compareParaVO.getMatBase()[i])) {
          strMes.append(NCLangResOnserver.getInstance().getStrByID("4006007_0",
              "04006007-0055", null, new String[] {
                compareParaVO.getMatBase()[i]
              })/*物料基本分类 [{0}],*/);
        }
        if (!PubAppTool.isNull(compareParaVO.getMatSale()[i])) {
          strMes.append(NCLangResOnserver.getInstance().getStrByID("4006007_0",
              "04006007-0056", null, new String[] {
                compareParaVO.getMatSale()[i]
              })/*物料销售分类 [{0}],*/);
        }
        if (!PubAppTool.isNull(compareParaVO.getMaterial()[i])) {
          strMes.append(NCLangResOnserver.getInstance().getStrByID("4006007_0",
              "04006007-0057", null, new String[] {
                compareParaVO.getMaterial()[i]
              })/*物料 [{0}],*/);
        }
        strMes.append(NCLangResOnserver.getInstance().getStrByID("4006007_0",
            "04006007-0054")/* 不能同时有值！\r\n */);
        ExceptionUtils.wrappBusinessException(strMes.toString());
      }
    }
  }

  /**
   * 判断传入的map是否为空
   * 
   * @param string
   * @return
   */
  private int isNotEmpty(String string) {
    if (string == null) {
      return 0;
    }
    return 1;
  }

  /**
   * 
   * 根据pk_org获取pk_org_v并赋给hvoItem
   * 
   * @param pk_org
   * @return
   */
  /**
   * 设置pk_org_v
   * 
   * @param orgMap
   * 
   * @param destordervos
   */
  private CustMatRelVO[] setPkOrgVValues(CustMatRelVO[] bills,
      Map<String, String> orgMap) {
    for (int i = 0; i < bills.length; i++) {
      CustMatRelHVO hvoItem = bills[i].getParentVO();
      hvoItem.setAttributeValue("pk_org", orgMap.get(hvoItem.getPk_org()));
      bills[i].setParentVO(hvoItem);
    }

    // pk_org集合
    Set<String> orgidset = new HashSet<String>();

    for (CustMatRelVO vo : bills) {
      CustMatRelHVO head = vo.getParentVO();
      orgidset.add(head.getPk_org());
    }
    Map<String, String> orgmapmap =
        OrgUnitPubService.getNewVIDSByOrgIDS(orgidset
            .toArray(new String[orgidset.size()]));

    // 设置pk_org_v
    for (int i = 0; i < bills.length; i++) {
      CustMatRelHVO head = bills[i].getParentVO();
      String orgid = head.getPk_org();
      head.setPk_org_v(orgmapmap.get(orgid));
      bills[i].setParentVO(head);
    }

    return bills;
  }

  /**
   * 根据pk_material_v获取pk_material并赋给bvoItem
   * 
   * @param string
   */
  private Map<String, String> getmaterial(String[] pk_materials) {
    if (ArrayUtil.isEmpty(pk_materials)) {
      return new HashMap<String, String>();
    }
    IDQueryBuilder builder = new IDQueryBuilder();
    String inSQL = builder.buildSQL(MaterialVO.PK_MATERIAL, pk_materials);
    String sql =
        " select " + MaterialVO.PK_SOURCE + ", " + MaterialVO.PK_MATERIAL
            + " from " + MaterialVO.getDefaultTableName()
            + " where dr = 0 and " + inSQL;
    DataAccessUtils utils = new DataAccessUtils();
    IRowSet results = utils.query(sql.toString());
    Map<String, String> sourceMap = new HashMap<String, String>();
    for (String[] str : results.toTwoDimensionStringArray()) {
      sourceMap.put(str[1], str[0]);
    }
    return sourceMap;
  }

  /**
   * 构建一个map，存放pk_org和pk_custmatrel
   * 
   * @param pk_org
   * @param primaryKey
   * @return
   */
  private Map<String, String> getORGAndPKMap() {
    StringBuilder querySql = new StringBuilder();
    querySql
        .append(" select pk_org,pk_custmatrel from so_custmatrel where dr = 0 ");
    DataAccessUtils utils = new DataAccessUtils();
    IRowSet results = utils.query(querySql.toString());
    Map<String, String> pkMap = new HashMap<String, String>();
    for (String[] res : results.toTwoDimensionStringArray()) {
      pkMap.put(res[0], res[1]);
    }
    return pkMap;
  }

  /**
   * 取表体VO中的字段
   * 
   * @param bill
   * @return
   */
  private CompareParaVO combinParaByCusVO(CustMatRelVO[] bills) {
    // 创建表体主要字段的数组，用来存放不同表体VO内相应字段的值
    // 创建组织的数组
    List<String> orgList = new ArrayList<String>();
    // 创建物客户基本分类的数组
    List<String> custbaseclassList = new ArrayList<String>();
    // 创建客户销售分类的数组
    List<String> custsaleclassList = new ArrayList<String>();
    // 创建物料基本分类的数组
    List<String> materialbaseclassList = new ArrayList<String>();
    // 创建物料销售分类的数组
    List<String> materialsaleclassList = new ArrayList<String>();
    // 创建物料的数组
    List<String> materialList = new ArrayList<String>();
    // 创建客户的数组
    List<String> customerList = new ArrayList<String>();
    // 创建包含的数组
    List<String> exclude = new ArrayList<String>();
    // 创建备注的数组
    List<String> vnote = new ArrayList<String>();

    CompareParaVO compareParaVO = new CompareParaVO();
    for (CustMatRelVO bill : bills) {
      CustMatRelHVO hvoItem = bill.getParentVO();
      orgList.add(hvoItem.getPk_org());
      CustMatRelBVO[] bvoItems = bill.getChildrenVO();
      if ((bvoItems != null) && bvoItems.length > 0) {
        for (CustMatRelBVO bvoItem : bvoItems) {
          // 取出表体vo的Pk_custbaseclass
          custbaseclassList.add(bvoItem.getPk_custbaseclass());
          // 取出表体vo的Pk_custsaleclass
          custsaleclassList.add(bvoItem.getPk_custsaleclass());
          // 取出表体vo的Pk_customer
          customerList.add(bvoItem.getPk_customer());
          // 取出表体vo的Pk_materialbaseclass
          materialbaseclassList.add(bvoItem.getPk_materialbaseclass());
          // 取出表体vo的Pk_materialsaleclass
          materialsaleclassList.add(bvoItem.getPk_materialsaleclass());
          // 取出表体vo的Pk_material_v
          materialList.add(bvoItem.getPk_material_v());
          // 取出表体vo的exclude
          exclude.add(bvoItem.getExclude() == null ? "N" : bvoItem.getExclude()
              .toString());
          // 取出表体vo的vnote
          vnote.add(bvoItem.getVnote());
        }
      }
    }

    return compareParaVO
        .getCompareParaVO(orgList.toArray(new String[orgList.size()]),
            materialbaseclassList.toArray(new String[materialbaseclassList
                .size()]), materialsaleclassList
                .toArray(new String[materialsaleclassList.size()]),
            materialList.toArray(new String[materialList.size()]),
            custbaseclassList.toArray(new String[custbaseclassList.size()]),
            custsaleclassList.toArray(new String[custsaleclassList.size()]),
            customerList.toArray(new String[customerList.size()]), exclude
                .toArray(new String[exclude.size()]), vnote
                .toArray(new String[vnote.size()]), null);
  }

  /**
   * 将导入文件内的组织编码转换为pk
   * 
   * @param code
   * @param primaryKey
   * @param tableName
   * @param list
   * @return
   */
  private Map<String, String> setCodeTOPK(String code, String primaryKey,
      String tableName, String[] list) {
    if (list == null || list.length == 0) {
      return new HashMap<String, String>();
    }
    Map<String, String> hashMap = new HashMap<String, String>();
    IDQueryBuilder whereSql = new IDQueryBuilder();
    StringBuilder sql = new StringBuilder();
    sql.append(" select distinct ");
    sql.append(code);
    sql.append(" , ");
    sql.append(primaryKey);
    sql.append(" from ");
    sql.append(tableName);
    sql.append(" where isnull(dr,0) = 0 and ");
    sql.append(whereSql.buildSQL(code, list));

    DataAccessUtils utils = new DataAccessUtils();
    IRowSet result = utils.query(sql.toString());
    Set<String> errorCode = new HashSet<String>();
    for (String[] rs : result.toTwoDimensionStringArray()) {
      // 取第一和第二个值分别作为键和值
      hashMap.put(rs[0], rs[1]);
    }
    for (int i = 0; i < list.length; i++) {
      String str = list[i];
      if (hashMap.get(str) == null && !PubAppTool.isNull(str)) {
        errorCode.add(str);
      }
    }
    // 如果写入的编码错误，报出提示信息
    if (errorCode.size() > 0) {
      StringBuilder msg = new StringBuilder();
      msg.append(NCLangResOnserver.getInstance().getStrByID("4006007_0",
          "04006007-0067")/*销售组织*/);
      msg.append(NCLangResOnserver.getInstance().getStrByID("4006007_0",
          "04006007-0058", null, new String[] {
          errorCode.toString().substring(1, errorCode.toString().length()-1)
          })/*【{0}】不存在！*/);
      ExceptionUtils.wrappBusinessException(msg.toString());
    }

    return hashMap;
  }

  /**
   * 将导入文件内的物料编码转换为pk，里面包括一些客户被分配到其他业务单元下的销售组织
   * 
   * @param code
   * @param primaryKey
   * @param tableName
   * @param list
   * @param pk_org
   * @return
   * @throws DAOException
   */
  private Map<String, String> setMaterialCodeTOPK(String code,
      String primaryKey, String[] list, String pk_org) throws DAOException {
    if (list == null || list.length == 0) {
      return new HashMap<String, String>();
    }
    Map<String, String> hashMap = new HashMap<String, String>();
    StringBuilder sql = new StringBuilder();
    sql.append(" select distinct ");
    sql.append(code);
    sql.append(" , ");
    sql.append(primaryKey);
    sql.append(" from bd_material_v a inner join bd_materialsale b on a.pk_material = b.pk_material ");
    sql.append(" where a.dr = 0 and b.dr = 0 and (b.pk_org = '");
    sql.append(pk_org);
    sql.append("' or a.pk_org in ('");
    sql.append(pk_org);
    sql.append("','" + AppContext.getInstance().getPkGroup()
        + "','GLOBLE00000000000000')) and ");
    sql.append(buildSQL(code, list));

    DataAccessUtils utils = new DataAccessUtils();
    IRowSet result = utils.query(sql.toString());
    Set<String> errorCode = new HashSet<String>();
    for (String[] rs : result.toTwoDimensionStringArray()) {
      // 取第一和第二个值分别作为键和值
      hashMap.put(rs[0], rs[1]);
    }
    for (int i = 0; i < list.length; i++) {
      String str = list[i];
      if (hashMap.get(str) == null && !PubAppTool.isNull(str)) {
        errorCode.add(str);
      }
    }

    // 如果写入的编码错误，报出提示信息
    if (errorCode.size() > 0) {
      StringBuilder msg = new StringBuilder();
      msg.append(NCLangResOnserver.getInstance().getStrByID("4006007_0",
          "04006007-0073")/*字段信息*/);
      msg.append(NCLangResOnserver.getInstance().getStrByID("4006007_0",
          "04006007-0058", null, new String[] {
          errorCode.toString().substring(1, errorCode.toString().length()-1)
          })/*【{0}】不存在！*/);
      ExceptionUtils.wrappBusinessException(msg.toString());
    }
    return hashMap;
  }

  /**
   * 将导入文件内的客户编码转换为pk，里面包括一些客户被分配到其他业务单元下的销售组织
   * 
   * @param code
   * @param primaryKey
   * @param tableName
   * @param list
   * @param pk_org
   * @return
   * @throws DAOException
   */
  private Map<String, String> setCustomerCodeTOPK(String code,
      String primaryKey, String[] list, String pk_org) throws DAOException {
    if (list == null || list.length == 0) {
      return new HashMap<String, String>();
    }
    Map<String, String> hashMap = new HashMap<String, String>();
    StringBuilder sql = new StringBuilder();
    sql.append(" select distinct ");
    sql.append(code);
    sql.append(" , ");
    sql.append(primaryKey);
    sql.append(" name ");
    sql.append(" from bd_customer a inner join bd_custsale b on a.pk_customer = b.pk_customer ");
    sql.append(" where a.dr = 0 and b.dr = 0 and (b.pk_org = '");
    sql.append(pk_org);
    sql.append("' or a.pk_org in ('");
    sql.append(pk_org);
    sql.append("','" + AppContext.getInstance().getPkGroup()
        + "','GLOBLE00000000000000')) and ");
    sql.append(buildSQL(code, list));

    DataAccessUtils utils = new DataAccessUtils();
    IRowSet result = utils.query(sql.toString());
    Set<String> errorCode = new HashSet<String>();
    for (String[] rs : result.toTwoDimensionStringArray()) {
      // 取第一和第二个值分别作为键和值
      hashMap.put(rs[0], rs[1]);
    }
    for (int i = 0; i < list.length; i++) {
      String str = list[i];
      if (hashMap.get(str) == null && !PubAppTool.isNull(str)) {
        errorCode.add(str);
      }
    }

    // 如果写入的编码错误，报出提示信息
    if (errorCode.size() > 0) {
      StringBuilder msg = new StringBuilder();
      msg.append(NCLangResOnserver.getInstance().getStrByID("4006007_0",
          "04006007-0070")/*字段信息*/);
      msg.append(NCLangResOnserver.getInstance().getStrByID("4006007_0",
          "04006007-0059", null, new String[] {
          errorCode.toString().substring(1, errorCode.toString().length()-1)
          })/*【{0}】不存在或者没有分配到导入文件的销售组织！*/);
      ExceptionUtils.wrappBusinessException(msg.toString());
    }
    return hashMap;
  }

  /**
   * 将导入文件内的编码转为pk
   * 
   * @param code
   * @param primaryKey
   * @param tableName
   * @param list
   * @param pk_org
   * @return
   * @throws DAOException
   */
  private Map<String, String> setCodeTOPK(String code, String primaryKey,
      String tableName, String[] list, String pk_org, String errorResid)
      throws DAOException {
    if (list == null || list.length == 0) {
      return new HashMap<String, String>();
    }
    Map<String, String> hashMap = new HashMap<String, String>();
    StringBuilder sql = new StringBuilder();
    sql.append(" select distinct ");
    sql.append(code);
    sql.append(" , ");
    sql.append(primaryKey);
    sql.append(" from ");
    sql.append(tableName);
    sql.append(" where isnull(dr,0) = 0 and ");
    sql.append(" pk_org in ('");
    sql.append(pk_org);
    sql.append("','" + AppContext.getInstance().getPkGroup()
        + "','GLOBLE00000000000000') and ");
    sql.append(buildSQL(code, list));

    DataAccessUtils utils = new DataAccessUtils();
    IRowSet result = utils.query(sql.toString());
    Set<String> errorCode = new HashSet<String>();
    for (String[] rs : result.toTwoDimensionStringArray()) {
      // 取第一和第二个值分别作为键和值
      hashMap.put(rs[0], rs[1]);
    }
    for (int i = 0; i < list.length; i++) {
      String str = list[i];
      if (hashMap.get(str) == null && !PubAppTool.isNull(str)) {
        errorCode.add(str);
      }
    }
    // 如果写入的编码错误，报出提示信息
    if (errorCode.size() > 0) {
      StringBuilder msg = new StringBuilder();
      msg.append(NCLangResOnserver.getInstance().getStrByID("4006007_0",
          errorResid)/*字段信息*/);
      msg.append(NCLangResOnserver.getInstance().getStrByID("4006007_0",
          "04006007-0058", null, new String[] {
          errorCode.toString().substring(1, errorCode.toString().length()-1)
          })/*【{0}】不存在！*/);
      ExceptionUtils.wrappBusinessException(msg.toString());
    }
    return hashMap;
  }

  /**
   * 根据传入的ID值构造查 询条件，传入的ID的值不能重复，也不能为空
   * 
   * @param name
   *          sql字段名
   * @param ids
   *          要查询的ID数组
   * @return 返回的查询条件，不会以 and开始
   */
  public String buildSQL(String name, String[] ids) {
    SqlBuilder sql = new SqlBuilder();
    sql.append(name);
    sql.append(" in ");
    sql.startParentheses();
    sql.append(" select id1 from ");
    String temptable = get(ids);
    sql.append(temptable);
    sql.endParentheses();
    return sql.toString();
  }

  /**
   * 包含一列ID字段的临时表，传入的ID的值不能重复，也不能为空。
   * 
   * @param ids
   *          编码数组
   * @return 一列主键临时表
   */
  public String get(String[] ids) {
    String tableName = this.get(ids, "TEMP_SCM_L1");
    return tableName;
  }

  /**
   * 返回临时表
   * 
   * @param ids
   * @param tableName
   * @return
   */
  private String get(String[] ids, String tableName) {
    List<List<Object>> data = new ArrayList<List<Object>>();

    int length = ids.length;
    for (int i = 0; i < length; i++) {
      List<Object> row = new ArrayList<Object>();
      data.add(row);
      row.add(ids[i]);
    }
    String[] columns = {
      "id1"
    };
    String[] columnTypes = {
      "VARCHAR(100)"
    };
    JavaType[] types = new JavaType[] {
      JavaType.String
    };

    TempTable dao = new TempTable();
    String name =
        dao.getTempTable(tableName, columns, columnTypes, columns, types, data);
    return name;
  }

}
