package nc.bs.so.custmatrel.rule;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.bs.dao.BaseDAO;
import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;
import nc.impl.pubapp.pattern.database.IDQueryBuilder;
import nc.impl.pubapp.pattern.database.TempTable;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.jdbc.framework.processor.ResultSetProcessor;
import nc.vo.pub.BusinessException;
import nc.vo.pub.JavaType;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.scmpub.util.ArrayUtil;
import nc.vo.so.custmatrel.entity.CustMatRelBVO;
import nc.vo.so.custmatrel.entity.CustMatRelHVO;
import nc.vo.so.custmatrel.entity.CustMatRelVO;
import nc.vo.so.custmatrel.paravo.CompareParaVO;
import nc.vo.so.pub.SOTable;
import nc.vo.so.pub.util.ListUtil;
import nc.vo.trade.checkrule.VOChecker;

/**
 * @description
 * 客户物料关系导入校验规则
 * @scene
 * 客户物料关系导入前补全VO、校验前台界面或EXCL文件中的数据是否有重复、校验数据库中的数据是否有重复
 * @param
 * 无
 * @since 6.5
 * @version 2015-10-19 上午9:24:33
 * @author gdsjw
 */
public class CheckSaveBillRule implements IRule<CustMatRelVO> {

  public CheckSaveBillRule() {
    //
  }

  @Override
  public void process(CustMatRelVO[] vos) {
    for (CustMatRelVO vo : vos) {
      // 这个是补全VO，校验时可能需要区分行状态
      this.checkNotNull(vo);
      // this.checkUnique(vo);
      setPk_org(vo);
      try {
        // 校验前台界面或EXCL文件中的数据是否有重复
        this.checkVODataUnique(vo);
        // 校验数据库中的数据是否有重复
        this.checkDBUnique(vo);
      }
      catch (BusinessException e) {
        ExceptionUtils.wrappException(e);
      }
    }
  }

  private void checkVODataUnique(CustMatRelVO vo) {
      Set<String> relCollections = new HashSet<String>();
      CustMatRelBVO[] items = vo.getChildrenVO();
      if (items != null && items.length > 0) {
        String nullstring = "NULL";
        StringBuilder sbd = new StringBuilder();
        List<CustMatRelBVO> repeatBVOList = new ArrayList<>();
        for (CustMatRelBVO item : items) {
          int vostatus = item.getStatus();
          if (vostatus == VOStatus.DELETED) {
            // 不检查删除的行
            continue;
          }
          String pk_org = item.getPk_org();
          if (PubAppTool.isNull(pk_org)) {
            pk_org = nullstring;
          }
          String materialbaseclass = item.getPk_materialbaseclass();
          if (PubAppTool.isNull(materialbaseclass)) {
            materialbaseclass = nullstring;
          }
          String materialsaleclass = item.getPk_materialsaleclass();
          if (PubAppTool.isNull(materialsaleclass)) {
            materialsaleclass = nullstring;
          }
          String material_v = item.getPk_material_v();
          if (PubAppTool.isNull(material_v)) {
            material_v = nullstring;
          }
          String custbaseclass = item.getPk_custbaseclass();
          if (PubAppTool.isNull(custbaseclass)) {
            custbaseclass = nullstring;
          }
          String custsaleclass = item.getPk_custsaleclass();
          if (PubAppTool.isNull(custsaleclass)) {
            custsaleclass = nullstring;
          }
          String customer = item.getPk_customer();
          if (PubAppTool.isNull(customer)) {
            customer = nullstring;
          }
          sbd.delete(0, sbd.length());
          sbd.append(pk_org).append(materialbaseclass).append(materialsaleclass)
              .append(material_v).append(custbaseclass).append(custsaleclass)
              .append(customer);
          if (relCollections.contains(sbd.toString())) {
            repeatBVOList.add(item);
          }
          else {
            relCollections.add(sbd.toString());
          }
        }
        if(repeatBVOList.size()>0){
          showMessage(ListUtil.toArray(repeatBVOList), true);
        }
    }
    
  }

  private void setPk_org(CustMatRelVO vo) {
    String pk_group = AppContext.getInstance().getPkGroup();
    CustMatRelBVO[] custmatbvos = vo.getChildrenVO();
    if (custmatbvos == null) {
      return;
    }
    for (CustMatRelBVO item : custmatbvos) {
      if (vo.getParentVO() != null) {
        item.setPk_org(vo.getParentVO().getPk_org());
        item.setPk_group(pk_group);
      }
    }
  }

  private void checkNotNull(CustMatRelVO bill) {
    CustMatRelHVO header = bill.getParentVO();
    Integer allowsale = header.getAllowsale();
    if (VOChecker.isEmpty(allowsale)) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006007_0", "04006007-0004")/* @res "允许销售/禁止销售不可为空。" */);
    }
    String pk_org = header.getPk_org();
    if (VOChecker.isEmpty(pk_org)) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006007_0", "04006007-0005")/* @res "销售组织不可为空。" */);
    }

    CustMatRelBVO[] items = bill.getChildrenVO();
    if ((items != null) && (items.length > 0)) {
      for (CustMatRelBVO item : items) {
        int vostatus = item.getStatus();
        if ((vostatus == VOStatus.DELETED)) {
          // 不检查删除、没变化的行
          continue;
        }
        String materialbaseclass = item.getPk_materialbaseclass();
        boolean ismaterialbaseclassnull = false;
        if (PubAppTool.isNull(materialbaseclass)) {
          ismaterialbaseclassnull = true;
        }

        String materialsaleclass = item.getPk_materialsaleclass();
        boolean ismaterialsaleclassnull = false;
        if (PubAppTool.isNull(materialsaleclass)) {
          ismaterialsaleclassnull = true;
        }

        String material_v = item.getPk_material_v();
        boolean ismaterial_vnull = false;
        if (PubAppTool.isNull(material_v)) {
          ismaterial_vnull = true;
        }
        if (ismaterialbaseclassnull && ismaterialsaleclassnull
            && ismaterial_vnull) {
          ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
              .getNCLangRes().getStrByID("4006007_0", "04006007-0006")/*
                                                                      * @res
                                                                      * "物料分类与物料不能全部为空。"
                                                                      */);
        }

      }
    }
  }

  /**
   * 检查新增行是否和数据库内已有数据是否相同，相同则不允许保存
   * 
   * @param bill
   *          界面单据
   * 
   */
  private void checkDBUnique(CustMatRelVO bill) throws BusinessException {
    CompareParaVO compareParaVO = this.combinParaByCusVO(bill);
    if (ArrayUtil.isEmpty(compareParaVO.getOrgList())) {
      return;
    }

    custMatRelDBUnique(bill, compareParaVO);
  }

  /**
   * 取界面表体VO中的字段
   * 
   * @param bill
   * @return
   */
  private CompareParaVO combinParaByCusVO(CustMatRelVO bill) {
    // 创建表体主要字段的数组，用来存放不同表体VO内相应字段的值
    // 创建组织的数组
    List<String> orgList = new ArrayList<String>();
    // 创建物料基本分类的数组
    List<String> matBase = new ArrayList<String>();
    // 创建物料销售分类的数组
    List<String> matSale = new ArrayList<String>();
    // 创建物料的数组
    List<String> material = new ArrayList<String>();
    // 创建客户基本分类的数组
    List<String> custBase = new ArrayList<String>();
    // 创建客户销售分类的数组
    List<String> custSale = new ArrayList<String>();
    // 创建客户的数组
    List<String> custom = new ArrayList<String>();
    // 创建是否包含的数组
    List<String> exclude = new ArrayList<String>();
    // 创建备注的数组
    List<String> vnote = new ArrayList<String>();
    Set<String> custmatelbidSet = new HashSet<>();
    CustMatRelHVO hvoItem = bill.getParentVO();
    CustMatRelBVO[] items = bill.getChildrenVO();
    if (items == null || items.length == 0) {
      ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
          .getStrByID("4006007_0", "04006007-0038")/*表体不可为空！*/);
    }
    CompareParaVO compareParaVO = new CompareParaVO();
    if ((items != null) && (items.length > 0)) {
      for (CustMatRelBVO item : items) {
        orgList.add(getvalue(hvoItem.getPk_org()));
        // 取出表体vo的Pk_custbaseclass
        custBase.add(getvalue(item.getPk_custbaseclass()));
        // 取出表体vo的Pk_custsaleclass
        custSale.add(getvalue(item.getPk_custsaleclass()));
        // 取出表体vo的Pk_customer
        custom.add(getvalue(item.getPk_customer()));
        // 取出表体vo的Pk_materialbaseclass
        matBase.add(getvalue(item.getPk_materialbaseclass()));
        // 取出表体vo的Pk_materialsaleclass
        matSale.add(getvalue(item.getPk_materialsaleclass()));
        // 取出表体vo的Pk_material_v
        material.add(getvalue(item.getPk_material_v()));
        // 取出表体vo的exclude
        exclude.add(item.getExclude() == null ? "N" : item.getExclude()
            .toString());
        // 取出表体vo的vnote
        vnote.add(item.getVnote());
        if(!PubAppTool.isNull(item.getPk_custmatrel_b())){
          custmatelbidSet.add(item.getPk_custmatrel_b());
        }
      }
    }
    return compareParaVO.getCompareParaVO(
        orgList.toArray(new String[orgList.size()]),
        matBase.toArray(new String[matBase.size()]),
        matSale.toArray(new String[matSale.size()]),
        material.toArray(new String[material.size()]),
        custBase.toArray(new String[custBase.size()]),
        custSale.toArray(new String[custSale.size()]),
        custom.toArray(new String[custom.size()]),
        exclude.toArray(new String[exclude.size()]),
        vnote.toArray(new String[vnote.size()]),
        custmatelbidSet.toArray(new String[custmatelbidSet.size()]));
  }

  private String getvalue(String str) {
    if (PubAppTool.isNull(str)) {
      return "~";
    }
    else {
      return str;
    }
  }

  /**
   * 
   * 数据重复校验
   * 
   * @param bill
   * @param compareParaVO
   *          存放需要比较的字段值
   * @throws BusinessException
   */
  private void custMatRelDBUnique(CustMatRelVO bill, CompareParaVO compareParaVO)
      throws BusinessException {

    // 1.获取后台重复的VO(excel数据与后台数据比较)
    CustMatRelBVO[] dbbvos = getDBUnique(bill, compareParaVO);

    // 2.提示重新信息
    if (dbbvos != null && dbbvos.length > 0) {
      showMessage(dbbvos, false);
    }
  }

  private CustMatRelBVO[] getDBUnique(CustMatRelVO bill,
      CompareParaVO compareParaVO) {
    // 创建临时表进行sql对比
    String tabelname = createTempTable(compareParaVO);
    SqlBuilder compareSql = new SqlBuilder();
    compareSql.append("select b.pk_custmatrel_b from " + tabelname);
    compareSql.append(" a,so_custmatrel_b b");
    compareSql.append(" where b.dr = 0");
    compareSql.append(" and a.pk_org = b.pk_org");
    compareSql.append(" and a.pk_custbaseclass = b.pk_custbaseclass");
    compareSql.append(" and a.pk_custsaleclass = b.pk_custsaleclass");
    compareSql.append(" and a.pk_customer = b.pk_customer");
    compareSql.append(" and a.pk_materialbaseclass = b.pk_materialbaseclass");
    compareSql.append(" and a.pk_materialsaleclass = b.pk_materialsaleclass");
    compareSql.append(" and a.pk_material_v = b.pk_material_v");
    if(!ArrayUtil.isEmpty(compareParaVO.getCustmatelbid())){
      compareSql.append(this.buildNotInSQL(compareParaVO.getCustmatelbid()));
    }
    String[] pks = this.queryPKBySql(compareSql);
    if (ArrayUtil.isEmpty(pks)) {
      return new CustMatRelBVO[0];
    }
    VOQuery<CustMatRelBVO> qrysrv =
        new VOQuery<CustMatRelBVO>(CustMatRelBVO.class);
    CustMatRelBVO[] retvos = qrysrv.query(pks);

    return retvos;
  }

  private String buildNotInSQL(String[] custmatelbid) {
    if(custmatelbid.length == 1){
      SqlBuilder sqlBuilder = new SqlBuilder();
      sqlBuilder.append(" and ");
      sqlBuilder.append(" b.pk_custmatrel_b <> '");
      sqlBuilder.append(custmatelbid[0]);
      sqlBuilder.append("'");
      return sqlBuilder.toString();
    }
    IDQueryBuilder idBuilder = new IDQueryBuilder();
    return idBuilder.buildSQL(" and b.pk_custmatrel_b not ", custmatelbid);
  }

  /**
   * 查询出同临时表有相同数据的表体的主键
   * 
   * @param compareSql
   * @return
   */
  private String[] queryPKBySql(SqlBuilder compareSql) {
    String[] pks = null;
    BaseDAO dao = new BaseDAO();
    try {
      pks =
          (String[]) dao.executeQuery(compareSql.toString(),
              new ResultSetProcessor() {

                private static final long serialVersionUID = 1L;

                @Override
                public Object handleResultSet(ResultSet rs) throws SQLException {
                  List<String> pkList = new ArrayList<String>();
                  while (rs.next()) {
                    pkList.add(rs.getString(1));
                  }
                  return pkList.toArray(new String[pkList.size()]);
                }
              });
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }
    return pks;
  }

  /**
   * 创建临时表
   * 
   * @param compareParaVO
   *          return 临时表
   */
  private String createTempTable(CompareParaVO compareParaVO) {
    // 构建二维数组
    List<List<Object>> datas = new ArrayList<List<Object>>();
    for (int i = 0; i < compareParaVO.getMatBase().length; i++) {
      List<Object> tmpList = new ArrayList<Object>();
      tmpList.add(compareParaVO.getOrgList()[i]);
      // 放入符合条件表体vo的Pk_custbaseclass
      tmpList.add(compareParaVO.getCustBase()[i]);
      // 放入符合条件表体vo的pk_custsaleclass
      tmpList.add(compareParaVO.getCustSale()[i]);
      // 放入符合条件表体vo的pk_customer
      tmpList.add(compareParaVO.getCustom()[i]);
      // 放入符合条件表体vo的pk_materialbaseclass
      tmpList.add(compareParaVO.getMatBase()[i]);
      // 放入符合条件表体vo的pk_materialsaleclass
      tmpList.add(compareParaVO.getMatSale()[i]);
      // 放入符合条件表体vo的pk_material_v
      tmpList.add(compareParaVO.getMaterial()[i]);
      // 放入符合条件表体vo的exclude
      tmpList.add(compareParaVO.getExclude()[i]);
      // 放入符合条件表体vo的vnote
      tmpList.add(compareParaVO.getVnote()[i]);
      datas.add(tmpList);
    }
    // 设置临时表的列名
    String[] columns =
        new String[] {
          "pk_org", "pk_custbaseclass", "pk_custsaleclass", "pk_customer",
          "pk_materialbaseclass", "pk_materialsaleclass", "pk_material_v",
          "exclude", "vnote"
        };
    // 设置临时表的列类型
    String[] columnTypes =
        new String[] {
          "varchar(20)", "varchar(20)", "varchar(20)", "varchar(20)",
          "varchar(20)", "varchar(20)", "varchar(20)", "char(1)",
          "varchar(181)"
        };

    // 设置临时表要插入临时表的数据的数据类型
    JavaType[] javaTypes =
        new JavaType[] {
          JavaType.String, JavaType.String, JavaType.String, JavaType.String,
          JavaType.String, JavaType.String, JavaType.String,
          JavaType.UFBoolean, JavaType.String
        };
    // 创建临时表并且插入数据datas
    TempTable tmpTable = new TempTable();
    String tmpTabelName =
        tmpTable.getTempTable("temp_custmatrel_2", columns, columnTypes,
            javaTypes, datas);

    return tmpTabelName;
  }

  /**
   * 
   * 提示信息
   * 
   * @param results
   * @param deleteStatusIds
   */
  private void showMessage(CustMatRelBVO[] results, boolean isexcel) {

    // 获取销售组织的name值
    Map<String, String> pk_orgmap =
        getBillValue(results, "pk_org", "org_orgs", "pk_org");

    // 获取custbaseclass的name值
    Map<String, String> custbaseMap =
        getBillValue(results, "pk_custclass", "bd_custclass",
            "pk_custbaseclass");
    // 获取custsaleclass的name值
    Map<String, String> custsalemap =
        getBillValue(results, "pk_custsaleclass", "bd_custsaleclass",
            "pk_custsaleclass");
    // 获取customer的name值
    Map<String, String> customerMap =
        getBillValue(results, "pk_customer", "bd_customer", "pk_customer");
    // 获取materialbaseclass的name值
    Map<String, String> marbasMap =
        getBillValue(results, "pk_marbasclass", "bd_marbasclass",
            "pk_materialbaseclass");
    // 获取materialsaleclass的name值
    Map<String, String> marsaleMap =
        getBillValue(results, "pk_marsaleclass", "bd_marsaleclass",
            "pk_materialsaleclass");
    // 获取material的name值
    Map<String, String> materialMap =
        getBillValue(results, "pk_material", "bd_material_v", "pk_material_v");

    // 通过map键值对得到的name值来作为提示信息
    StringBuilder strMes = new StringBuilder();
    for (CustMatRelBVO result : results) {

      if (isNotNull(result.getPk_org())) {
        strMes.append(NCLangResOnserver.getInstance().getStrByID("4006007_0",
            "04006007-0060", null, new String[] {
              pk_orgmap.get(result.getPk_org())
            })/*销售组织 [{0}],*/);
      }
      if (isNotNull(result.getPk_custbaseclass())) {
        strMes.append(NCLangResOnserver.getInstance().getStrByID("4006007_0",
            "04006007-0039", null, new String[] {
              custbaseMap.get(result.getPk_custbaseclass())
            })/*客户基本分类 [{0}],*/);
      }
      if (isNotNull(result.getPk_custsaleclass())) {
        strMes.append(NCLangResOnserver.getInstance().getStrByID("4006007_0",
            "04006007-0040", null, new String[] {
              custsalemap.get(result.getPk_custsaleclass())
            })/*客户销售分类[{0}],*/);
      }
      if (isNotNull(result.getPk_customer())) {
        strMes.append(NCLangResOnserver.getInstance().getStrByID("4006007_0",
            "04006007-0041", null, new String[] {
              customerMap.get(result.getPk_customer())
            })/*客户[{0}],*/);
      }
      if (isNotNull(result.getPk_materialbaseclass())) {
        strMes.append(NCLangResOnserver.getInstance().getStrByID("4006007_0",
            "04006007-0042", null, new String[] {
              marbasMap.get(result.getPk_materialbaseclass())
            })/*物料基本分类[{0}],*/);
      }
      if (isNotNull(result.getPk_materialsaleclass())) {
        strMes.append(NCLangResOnserver.getInstance().getStrByID("4006007_0",
            "04006007-0043", null, new String[] {
              marsaleMap.get(result.getPk_materialsaleclass())
            })/*物料销售分类[{0}],*/);
      }
      if (isNotNull(result.getPk_material_v())) {
        strMes.append(NCLangResOnserver.getInstance().getStrByID("4006007_0",
            "04006007-0044", null, new String[] {
              materialMap.get(result.getPk_material_v())
            })/*物料[{0}]*/);
      }
      if (result.getExclude() != null) {
        strMes.append(NCLangResOnserver.getInstance().getStrByID("4006007_0",
            "04006007-0045", null, new String[] {
              result.getExclude().toString()
            })/*不包含[{0}]*/);
      }
      if (!PubAppTool.isNull(result.getVnote())) {
        strMes.append(NCLangResOnserver.getInstance().getStrByID("4006007_0",
            "04006007-0046", null, new String[] {
              result.getVnote()
            })/*备注[{0}]*/);
      }
      if (isexcel) {
        strMes.append(NCLangResOnserver.getInstance().getStrByID("4006007_0",
            "04006007-0063")/* 本条数据重复！操作之前，请先确保没有重复数据！\r\n */);
      }
      else {
        strMes.append(NCLangResOnserver.getInstance().getStrByID("4006007_0",
            "04006007-0061")/* 本条数据在系统中已经存在！\r\n */);
      }
    }

    ExceptionUtils.wrappBusinessException(strMes.toString());
  }
  
  private boolean isNotNull(String para){
    if(!PubAppTool.isNull(para)&& !"~".equals(para)){
      return true;
      
    }else{
      return false;
    }
  }

  /**
   * 
   * 获取相应pk的name值
   * 
   * @param results
   * @return map
   */
  private Map<String, String> getBillValue(CustMatRelBVO[] results,
      String primaryKey, String tableName, String field) {
    StringBuilder whereSql = new StringBuilder();
    // 构建查询语句，查出该字段的pk和与其左连接的表查出的name值
    whereSql.append(" select name,");
    whereSql.append(primaryKey);
    whereSql.append(" from ");
    whereSql.append(tableName);
    whereSql.append(" where ");
    Set<String> ids = new HashSet<String>();
    for (CustMatRelBVO result : results) {
      if (result.getAttributeValue(field) != null) {
        ids.add((String) result.getAttributeValue(field));
      }
    }
    Map<String, String> billMap = new HashMap<String, String>();
    if (ids.size() == 0) {
      return billMap;
    }
    IDExQueryBuilder iq = new IDExQueryBuilder(SOTable.TMP_SO_ID1.getName());
    whereSql.append(iq.buildSQL(primaryKey, ids.toArray(new String[0])));

    DataAccessUtils utils = new DataAccessUtils();
    IRowSet rs = utils.query(whereSql.toString());
    String[][] rsvalues = rs.toTwoDimensionStringArray();
    for (String[] value : rsvalues) {
      billMap.put(value[1], value[0]);
    }
    return billMap;
  }
}
