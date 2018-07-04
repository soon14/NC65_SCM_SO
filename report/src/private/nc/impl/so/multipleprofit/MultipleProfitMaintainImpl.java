package nc.impl.so.multipleprofit;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.bs.ml.NCLangResOnserver;
import nc.impl.so.multipleprofit.temp.MultipleProfitTempTable;
import nc.impl.so.pub.summary.util.SQLCreateUtil;
import nc.itf.so.multipleprofit.IMultipleProfitMaintain;
import nc.pubitf.arap.receivable.IArapReceivableBillPubServiceForSCM;
import nc.pubitf.ia.mi5.so.IIAI5ForSOReportAnalyse;
import nc.ui.querytemplate.filter.DefaultFilter;
import nc.ui.querytemplate.filter.IFilter;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.ui.querytemplate.value.IFieldValueElement;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pub.query.ConditionVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.pubapp.query2.sql.process.QueryCondition;
import nc.vo.pubapp.query2.sql.process.QuerySchemeProcessor;
import nc.vo.pubapp.report.ReportQueryConUtil;
import nc.vo.pubapp.util.DefaultVOMerger;
import nc.vo.scmpub.report.SCMReportTempTableUtil;
import nc.vo.so.report.multipleprofit.MultProfitSourceSystem;
import nc.vo.so.report.multipleprofit.MultipleProfitViewMeta;
import nc.vo.so.report.multipleprofit.MultipleProfitViewVO;
import nc.vo.so.report.reportpub.ReportContext;
import nc.vo.so.report.reportpub.ReportLevelProcesser;
import nc.vo.so.report.reportpub.ReportUserObject;

import com.ufida.dataset.IContext;

/**
 * 实现综合毛利分析查询的接口12
 * 
 * @since 6.3
 * @version 2012-10-18 14:22:05
 * @author zhangkai4
 */
public class MultipleProfitMaintainImpl implements IMultipleProfitMaintain {

  @Override
  public String queryMultipleProfit(IContext context) throws BusinessException {
    // 1.创建临时表
    String temptable = this.createTempTable();

    // 2.判断查询条件是否为空
    ReportQueryConUtil qryconutil = new ReportQueryConUtil(context);

    if (qryconutil.isNull()) {
      return "select * from " + temptable;
    }
    ReportUserObject userObject = (ReportUserObject) qryconutil.getUserObject();
    // 3.获得数据
    MultipleProfitViewVO[] viewVO = this.getData(userObject);

    if (null == viewVO || viewVO.length == 0) {
      return this.getSelectSql(temptable, userObject);
    }

    // 4.处理数据
    MultipleProfitViewVO[] combinviews = this.processData(viewVO, userObject);

    // 5.插入临时表
    this.insertIntoTmpTable(temptable, combinviews);

    // 6.返回临时表数据
    return this.getSelectSql(temptable, userObject);
  }

  private void insertIntoTmpTable(String temptable,
      MultipleProfitViewVO[] combinviews) {

    String[] fieldnames =
        new MultipleProfitTempTable().getMetaData().getFieldNames();
    SCMReportTempTableUtil tmptableutil = new SCMReportTempTableUtil();
    tmptableutil.insertIntoTempTable(temptable, fieldnames, combinviews);

  }

  /**
   * 处理数据
   * 
   * @param viewVO
   * @param userObject
   * @return
   */
  private MultipleProfitViewVO[] processData(MultipleProfitViewVO[] viewVO,
      ReportUserObject userObject) {
    // 销售组织级次和物料分类级次处理
    int marLevel = 0;
    int saleorgLevel = 0;
    IQueryScheme queryScheme = userObject.getIQueryScheme();
    ConditionVO[] conds =
        (ConditionVO[]) queryScheme.get(IQueryScheme.KEY_LOGICAL_CONDITION);
    for (ConditionVO cond : conds) {
      if (cond.getFieldCode().equals(ReportContext.CMATERIALMARBASCLASSLEVEL)) {
        marLevel = Integer.valueOf(cond.getValue()).intValue();
      }
      else if (cond.getFieldCode().equals(ReportContext.SALEORGLEVEL)) {
        saleorgLevel = Integer.valueOf(cond.getValue()).intValue();
      }
    }
    ReportLevelProcesser levelProcesser = ReportLevelProcesser.getInstance();
    levelProcesser.processMarLevel(viewVO, MultipleProfitViewVO.CMATERIALID,
        MultipleProfitViewVO.PK_MARBASCLASS, marLevel);
    levelProcesser.processSaleorgLevel(viewVO, MultipleProfitViewVO.CSALEORGID,
        saleorgLevel);

    // 进行汇总
    // MultipleProfitViewVO[] combinviews =
    // this.fillSumInfo(viewVO, userObject.getSummaryKeys());
    this.calMnyField(viewVO);
    return viewVO;
  }

  /**
   * 根据查询条件从三张单（应收单、成本结转单、调拨出库单）获取数据并整合
   * 
   * @param userObject
   * @return 整合之后的数据
   */
  private MultipleProfitViewVO[] getData(ReportUserObject userObject) {
    ConditionVO[] conditions =
        (ConditionVO[]) userObject.getIQueryScheme().get(
            IQueryScheme.KEY_LOGICAL_CONDITION);
    MultProfitSourceSystem sourSys = this.getSourceSystem(userObject);
    String[] sumKeys = this.getSummaryKeys(userObject);

    // 从应收单获取信息
    MultipleProfitViewVO[] obtainViewVO =
        this.queryObtainViewVO(conditions, sourSys, sumKeys);
    // 从成本结转单、调拨出库单获取信息
    MultipleProfitViewVO[] costViewVO =
        this.queryCostViewVO(conditions, sourSys, sumKeys);

    // 汇总（应收单、成本结转单、调拨出库单）的信息
    MultipleProfitViewVO[] viewVO = this.getView(obtainViewVO, costViewVO);

    for (MultipleProfitViewVO view : viewVO) {
      if (view.getSourcesystem().equals("0")) {
        view.setSourcesystem(NCLangResOnserver.getInstance().getStrByID(
            "40060906", "1400609060028")/* 销售 */);
      }
      else if (view.getSourcesystem().equals("1")) {
        view.setSourcesystem(NCLangResOnserver.getInstance().getStrByID(
            "40060906", "1400609060029")/* 内部交易 */);
      }
    }
    return viewVO;
  }

  private String[] getSummaryKeys(ReportUserObject userObject) {
    Set<String> summkeySet = userObject.getSummaryKeys();
    // 添加销售组织和物料
    summkeySet.add(MultipleProfitViewVO.CSALEORGID);
    summkeySet.add(MultipleProfitViewVO.CMATERIALID);
    // summkeySet.add(MultipleProfitViewVO.CUNITID);
    summkeySet.add(MultipleProfitViewVO.CORIGCURRENCYID);
    String[] sumKeys = new String[summkeySet.size()];
    summkeySet.toArray(sumKeys);
    return sumKeys;
  }

  /**
   * 填写“应收无税单价”、“成本单价”、“毛利”、“毛利率”<br>
   * 计算公式：<br>
   * <ol>
   * <li>应收无税单价=应收无税金额 /应收主数量</li>
   * <li>成本单价=成本金额 /存货核算主数量</li>
   * <li>毛利=应收无税金额-成本金额</li>
   * <li>毛利率=毛利/无税金额</li>
   * </ol>
   * 
   * @param viewVO
   */
  private void calMnyField(MultipleProfitViewVO[] viewVOs) {
    for (MultipleProfitViewVO viewVO : viewVOs) {
      // 应收无税金额
      UFDouble totalReceivMny = viewVO.getNtotalreceivmny();
      // 成本金额
      UFDouble totalCostMny = viewVO.getNtotalcostmny();
      // 设置毛利
      viewVO.setNprofitmny(MathTool.sub(totalReceivMny, totalCostMny));

    }
  }

  /**
   * 进行汇总（财务组织、订单客户、产品线、客户销售分类、物料基本分类）
   * 
   * @param viewVO
   *          汇总前的VO
   * @return 汇总之后的VO
   */
  private MultipleProfitViewVO[] fillSumInfo(MultipleProfitViewVO[] views,
      Set<String> groupSet) {
    DefaultVOMerger mergertool = new DefaultVOMerger();
    String[] groupkeys = new String[groupSet.size()];
    groupSet.toArray(groupkeys); // 分组字段
    String[] sumkeys = MultipleProfitViewMeta.AGGKEYS;

    mergertool.setGroupingAttr(groupkeys);
    mergertool.setSummingAttr(sumkeys);
    try {
      MultipleProfitViewVO[] conbinviews =
          (MultipleProfitViewVO[]) mergertool.mergeByGroup1(views);
      return conbinviews;
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return null;
  }

  /**
   * 汇总（应收单,成本结转单、调拨出库单）的信息
   * 
   * @param allotViewVO
   *          应收单
   * @param costViewVO
   *          成本结转单
   * 
   * @return
   */
  private MultipleProfitViewVO[] getView(MultipleProfitViewVO[] obtainViewVO,
      MultipleProfitViewVO[] costViewVO) {
    // int length = obtainViewVO.length + costViewVO.length +
    // allotViewVO.length;
    List<MultipleProfitViewVO> listviews =
        new ArrayList<MultipleProfitViewVO>();
    // 应收单
    for (MultipleProfitViewVO viewVO : obtainViewVO) {
      listviews.add(viewVO);
    }
    // 成本结转单\调拨出库单
    for (MultipleProfitViewVO viewVO : costViewVO) {
      listviews.add(viewVO);
    }

    return listviews.toArray(new MultipleProfitViewVO[listviews.size()]);
  }

  /**
   * 从成本获取信息
   * 
   * @param summkey2
   * @param soursys2
   * @param condition
   * @return
   */
  private MultipleProfitViewVO[] queryCostViewVO(ConditionVO[] conditions,
      MultProfitSourceSystem sourSys, String[] sumKeys) {
    MultipleProfitViewVO[] viewVO = null;
    IIAI5ForSOReportAnalyse iasrv =
        NCLocator.getInstance().lookup(IIAI5ForSOReportAnalyse.class);
    try {
      viewVO =
          iasrv.queryCostDataForMultipleProfitForSO(conditions, sourSys,
              sumKeys);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }

    return viewVO;

  }

  /**
   * 从应收单获取信息
   * 
   * @param summkey2
   * @param soursys2
   * @param condition
   * 
   * @return
   */
  private MultipleProfitViewVO[] queryObtainViewVO(ConditionVO[] conditions,
      MultProfitSourceSystem sourSys, String[] sumKeys) {
    // 财务：应收的信息
    IArapReceivableBillPubServiceForSCM iArapForMulProfit =
        NCLocator.getInstance().lookup(
            IArapReceivableBillPubServiceForSCM.class);
    MultipleProfitViewVO[] obtainViewVO = null;
    try {
      obtainViewVO =
          iArapForMulProfit.queryMultProfit(conditions, sourSys, sumKeys);
      for (MultipleProfitViewVO viw : obtainViewVO) {
        if (viw.getSourcesystem().equals("3")) {
          viw.setSourcesystem("0");
        }
        else if (viw.getSourcesystem().equals("16")) {
          viw.setSourcesystem("1");
        }
      }
    }
    catch (BusinessException ex) {
      ExceptionUtils.wrappException(ex);
    }
    return obtainViewVO;

  }

  private MultProfitSourceSystem getSourceSystem(ReportUserObject userObject) {
    // 来源系统的获取
    IFilter[] filters =
        (IFilter[]) userObject.getIQueryScheme().get(IQueryScheme.KEY_FILTERS);

    int sourcesystem = 0;// 系统来源(1:销售组织、2：内部交易、3：销售组织+内部交易)
    for (IFilter filter : filters) {
      DefaultFilter deflilter = (DefaultFilter) filter;
      String fieldCode = deflilter.getFilterMeta().getFieldCode();
      if (fieldCode.equals("sourcesystem")) {// 来源系统
        List<IFieldValueElement> valueList =
            deflilter.getFieldValue().getFieldValues();
        for (IFieldValueElement value : valueList) {
          if (value.getSqlString().equals("0")) {
            sourcesystem += 1;
          }
          else if (value.getSqlString().equals("1")) {
            sourcesystem += 2;
          }
        }
      }
    }
    switch (sourcesystem) {
      case 1:
        return MultProfitSourceSystem.RESOURCE_SALE;
      case 2:
        return MultProfitSourceSystem.RESOURCE_INNER;
      default:
        return MultProfitSourceSystem.RESOURCE_SALE_INNER;
    }
  }

  /**
   * 
   * 创建临时表
   * 
   * @return 临时表的表名
   */
  private String createTempTable() {
    // 创建临时表
    MultipleProfitTempTable table = new MultipleProfitTempTable();
    String temptable = table.createMulProfitTemptable();
    return temptable;
  }

  /**
   * 根据临时表表名获得查询的sql语句
   * 
   * @param temptable
   *          临时表表名
   * @param userObject
   * @return 查询的sql语句
   */
  private String getSelectSql(String temptable, ReportUserObject userObject) {
    QuerySchemeProcessor qsp =
        new QuerySchemeProcessor(userObject.getIQueryScheme());
    QueryCondition qc = qsp.getQueryCondition(MultipleProfitViewVO.PK_ORG);
    Object[] orgs = qc.getValues();
    SqlBuilder colmsql = new SqlBuilder();
    String[] groupkeys = this.getGroupKeys(userObject);
    colmsql.append("select ");
    boolean isorgfalg = false;
    for (String key : groupkeys) {
      colmsql.append(key + ",");
      if (MultipleProfitViewVO.PK_ORG.equals(key)) {
        isorgfalg = true;
      }
    }
    if (!isorgfalg) {
      colmsql.append("'" + orgs[0] + "' " + MultipleProfitViewVO.PK_ORG + ",");
    }

    for (String key : MultipleProfitViewMeta.AGGKEYS) {
      colmsql.append("sum(" + key + ") " + key + ",");
    }

    String ntotalreceivprice =
        SQLCreateUtil.getSumDivsql(MultipleProfitViewVO.NTOTALRECEIVMNY,
            MultipleProfitViewVO.NSHOULDRECEIVNUM);
    colmsql.appendCaseWhen("sum(nshouldreceivnum)<>0", ntotalreceivprice, "0");
    colmsql.append(" ntotalreceivprice,");

    String ncostprice =
        SQLCreateUtil.getSumDivsql(MultipleProfitViewVO.NTOTALCOSTMNY,
            MultipleProfitViewVO.NMAINNUM);
    colmsql.appendCaseWhen("sum(nmainnum)<>0", ncostprice, "0");
    colmsql.append(" ncostprice,");

    SqlBuilder nprofitrate = new SqlBuilder();
    nprofitrate.append(SQLCreateUtil.getSumDivsql(
        MultipleProfitViewVO.NPROFITMNY, MultipleProfitViewVO.NTOTALRECEIVMNY));
    colmsql.appendCaseWhen("sum(ntotalreceivmny)<>0", nprofitrate.toString(),
        "0");
    colmsql.append(" nprofitrate ");

    colmsql.append(" from ");
    colmsql.append(temptable);
    colmsql.append(getSumGroupbySQL(groupkeys));
    return colmsql.toString();
  }

  private String getSumGroupbySQL(String[] groupkeys) {
    SqlBuilder sumsql = new SqlBuilder();
    sumsql.append(" group by  ");
    for (String sumkey : groupkeys) {
      sumsql.append(sumkey);
      sumsql.append(",");
    }
    sumsql.deleteLastChar();
    return sumsql.toString();
  }

  /**
   * 获得分组字段
   * 
   * @param userobject
   * @return
   */
  private String[] getGroupKeys(ReportUserObject userobject) {
    Set<String> listgroup = new HashSet<String>();
    Set<String> selgroups = userobject.getSummaryKeys();
    for (String key : selgroups) {
      listgroup.add(key);
      if (MultipleProfitViewVO.CMATERIALID.equals(key)) {
        listgroup.add(MultipleProfitViewVO.CUNITID);
      }
    }
    listgroup.add(MultipleProfitViewVO.CORIGCURRENCYID);
    String[] groupkeys = new String[listgroup.size()];
    listgroup.toArray(groupkeys);
    return groupkeys;
  }

}
