package nc.ui.so.m30.report;

import java.awt.Container;
import java.util.HashMap;
import java.util.Map;

import com.ufida.dataset.IContext;
import com.ufida.report.anareport.base.BaseQueryCondition;
import com.ufida.report.anareport.model.AbsAnaReportModel;

import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.pub.query.ConditionVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.querytemplate.TemplateInfo;
import nc.vo.scmpub.report.SCMReportQueryConUtil;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.salequotation.entity.SalequotationHVO;

import nc.itf.iufo.freereport.extend.IQueryCondition;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;

import nc.bs.pubapp.report.ReportPermissionUtils;
import nc.bs.scmpub.report.ReportQueryCondition;
import nc.bs.scmpub.report.ReportScaleProcess;

import nc.ui.iufo.freereport.extend.DefaultQueryAction;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.ui.querytemplate.QueryConditionDLG;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.ui.scmpub.query.refregion.QDeptFilter;
import nc.ui.scmpub.query.refregion.QFfileFilterByMaterCode;
import nc.ui.scmpub.query.refregion.QPsndocFilter;
import nc.ui.scmpub.query.refregion.QTransTypeFilter;
import nc.ui.scmpub.query.refregion.RefCommonFilterListener;

/**
 * 销售订单执行查询
 * 
 * @since 6.0
 * @version 2011-3-14 下午03:04:27
 * @author 么贵敬
 */
@SuppressWarnings("restriction")
public class ReportQueryAction extends DefaultQueryAction {

  /**
   * 金额字段
   */
  private static final String[] MNYKEYS = {
    // 价税合计、暂估应收金额
    SaleOrderBVO.NORIGTAXMNY, SaleOrderBVO.NTOTALESTARMNY,
    SaleOrderBVO.NTAXMNY,
    // 累计确认应收金额、财务核销金额
    SaleOrderBVO.NTOTALARMNY, SaleOrderBVO.NTOTALPAYMNY, "ntotalpaymny",
    "ntotalnopaymny"
  };

  /**
   * 数量字段
   */
  private static final String[] NUMKEYS = {
    // 主数量、累计出库主数量、签收数量、
    SaleOrderBVO.NNUM, SaleOrderBVO.NTOTALOUTNUM, SaleOrderBVO.NTOTALSIGNNUM,
    // 累计途损数量
    SaleOrderBVO.NTRANSLOSSNUM,
    // 开票数量、累计暂估应收数量
    SaleOrderBVO.NTOTALINVOICENUM, SaleOrderBVO.NTOTALESTARNUM,
    // 累计确认应收数量、累计成本结算数量、待出库数量
    SaleOrderBVO.NTOTALARNUM, SaleOrderBVO.NTOTALCOSTNUM, "ntotalnooutnum"
  };

  /**
   * 合计字段
   */
  private static final String[] TOTALKEYS = {
    // 价税合计、暂估应收金额
    SaleOrderBVO.NORIGTAXMNY, SaleOrderBVO.NTOTALESTARMNY,
    SaleOrderBVO.NTAXMNY,
    // 累计确认应收金额、财务核销金额
    SaleOrderBVO.NTOTALARMNY, SaleOrderBVO.NTOTALPAYMNY, "ntotalnopaymny",
    // 主数量、累计出库主数量、签收数量、
    SaleOrderBVO.NNUM, SaleOrderBVO.NTOTALOUTNUM, SaleOrderBVO.NTOTALSIGNNUM,
    // 累计途损数量
    SaleOrderBVO.NTRANSLOSSNUM,
    // 开票数量、累计暂估应收数量
    SaleOrderBVO.NTOTALINVOICENUM, SaleOrderBVO.NTOTALESTARNUM,
    // 累计确认应收数量、累计成本结算数量、待出库数量
    SaleOrderBVO.NTOTALARNUM, SaleOrderBVO.NTOTALCOSTNUM, "ntotalnooutnum"
  };

  private QueryConditionDLGDelegator delegator;

  @Override
  public IQueryCondition doQueryByScheme(Container parent, IContext context,
      AbsAnaReportModel reportModel, IQueryScheme queryScheme) {

    IQueryCondition bascon =
        super.doQueryByScheme(parent, context, reportModel, queryScheme);
    SCMReportQueryConUtil conutil = new SCMReportQueryConUtil();
    conutil.addRedundancyInfo(SaleOrderHVO.DBILLDATE, "body_dbilldate");
    conutil.addRedundancyInfo(SaleOrderHVO.PK_ORG, "body_pk_org");
    return conutil.getQueryResultAfterAddReduncy(bascon, queryScheme);
  }

  public QueryConditionDLGDelegator getDelegator() {
    return this.delegator;
  }

  public void setDelegator(QueryConditionDLGDelegator delegator) {
    this.delegator = delegator;
  }

  /**
   * 权限和精度处理
   */
  @Override
  protected IQueryCondition createQueryCondition(IContext context) {
    // 权限
    ReportPermissionUtils utils = new ReportPermissionUtils(context);
    utils.setMainBeanClass(SaleOrderHVO.class);

    ReportQueryCondition condition = new ReportQueryCondition(true);
    ReportScaleProcess process = new ReportScaleProcess();
    // 金额精度
    process.setMnyDigits(SaleOrderHVO.CORIGCURRENCYID,
        ReportQueryAction.MNYKEYS);
    // 数量精度
    process.setNumDigits(SaleOrderBVO.CUNITID, ReportQueryAction.NUMKEYS);
    // 单价精度
    process.setPriceDigits(new String[] {
    SaleOrderBVO.NTAXNETPRICE
    },SaleOrderBVO.CCURRENCYID);
    
    
    // 单价精度
    process.setPriceDigits(new String[] {
      SaleOrderBVO.NORIGTAXNETPRICE
    },SaleOrderHVO.CORIGCURRENCYID);
    
    
    // 合计行精度
    process.setTotalFields(ReportQueryAction.TOTALKEYS);
    condition.setBusiFormat(process);
    return condition;
  }

  @Override
  protected QueryConditionDLG createQueryDlg(Container parent, TemplateInfo ti,
      IContext context, IQueryCondition oldCondition) {
    if (this.delegator == null) {
      this.delegator = new QueryConditionDLGDelegator(parent, ti);
    }
    // 为查询模板的字段参照等过滤
    this.setFilter();
    // 处理权限过滤
    this.processPowerFilter();
    return this.delegator.getQueryConditionDLG();
  }

  @Override
  protected IQueryCondition setQueryResult(IQueryCondition condition,
      QueryConditionDLG queryDlg, AbsAnaReportModel reportModel,
      IContext context) {

    ConditionVO[] conds = queryDlg.getLogicalConditionVOs();
    IQueryCondition bascon =
        super.setQueryResult(condition, queryDlg, reportModel, context);
    // 设置子表冗余字段
    SCMReportQueryConUtil conutil = new SCMReportQueryConUtil();
    conutil.addRedundancyInfo(SaleOrderHVO.DBILLDATE, "body_dbilldate");
    conutil.addRedundancyInfo(SaleOrderHVO.PK_ORG, "body_pk_org");

    conutil.changeCustSaleClassCon(condition, conds,
        "this.ccustomerid.sales.pk_custsaleclass", "ccustomerid");

    conutil.changeMarSaleClassCon(condition, conds,
        "this.cmaterialvid.materialsale.pk_marsaleclass", "cmaterialvid");

    IQueryScheme queryScheme = queryDlg.getQueryScheme();
    return conutil.getQueryResultAfterAddReduncy(bascon, queryScheme);
  }

  @Override
  protected IQueryCondition showQueryDialog(Container parent, IContext context,
      AbsAnaReportModel reportModel, TemplateInfo tempinfo,
      IQueryCondition oldCondition) {
    if (!SysInitGroupQuery.isAREnabled()) {
      ExceptionUtils
          .wrappBusinessException(NCLangRes4VoTransl.getNCLangRes().getStrByID(
              "4006011_0", "04006011-0471")/* 财务模块未启用，暂估应收金额等字段无法查询！ */);
    }

    QueryConditionDLG queryDlg =
        this.getQueryConditionDlg(parent, context, reportModel, oldCondition);
    QueryConditionDLGDelegator dlgDelegator = this.getDLGDelegator(queryDlg);
    if (dlgDelegator.showModal() == UIDialog.ID_OK) {
      IQueryCondition condition = this.createQueryCondition(context);
      condition =
          this.setQueryResult(condition, queryDlg, reportModel, context);
      return condition;
    }
    return new BaseQueryCondition(false);
  }

  private QueryConditionDLGDelegator getDLGDelegator(QueryConditionDLG queryDlg) {
    QueryConditionDLGDelegator condDLGDelegator =
        new QueryConditionDLGDelegator(queryDlg);

    return condDLGDelegator;
  }

  private void processPowerFilter() {
    this.delegator.registerNeedPermissionOrgFieldCodes(new String[] {
      SaleOrderHVO.PK_ORG
    });
    Map<String, String> columnMapping = new HashMap<String, String>();
    columnMapping.put("this.ccustomerid.code", SaleOrderHVO.CCUSTOMERID);
    columnMapping.put(SaleOrderHVO.CDEPTID, SaleOrderHVO.CDEPTID);
    columnMapping.put(SaleOrderHVO.CEMPLOYEEID, SaleOrderHVO.CEMPLOYEEID);
    this.delegator
        .registerRefPowerFilterInfo(SaleOrderHVO.class, columnMapping);

    Map<String, String> bcolumnMapping = new HashMap<String, String>();
    bcolumnMapping.put("this.cmaterialvid.code", SaleOrderBVO.CMATERIALID);
    this.delegator.registerRefPowerFilterInfo(SaleOrderBVO.class,
        bcolumnMapping);
  }

  private void setFilter() {
    // 交易类型参照
    QTransTypeFilter trantype =
        new QTransTypeFilter(this.delegator, SOBillType.Order.getCode());
    trantype.filter();

    // 所有参照按照销售组织过滤
    RefCommonFilterListener filterutil =
        new RefCommonFilterListener(this.delegator, SaleOrderHVO.PK_ORG);
    filterutil.addFilterMapsListeners();
    
    //过滤销售部门
    QDeptFilter deptFilter = QDeptFilter.createDeptFilterOfSO(
        this.delegator, SaleOrderHVO.CDEPTID);
    deptFilter.setPk_orgCode(SaleOrderHVO.PK_ORG);
    deptFilter.addEditorListener();

    // 过滤业务员
    QPsndocFilter psnFilter = QPsndocFilter.createQPsndocFilterOfSO(
        this.delegator, SaleOrderHVO.CEMPLOYEEID);
    psnFilter.setPk_orgCode(SaleOrderHVO.PK_ORG);
    psnFilter.addEditorListener();
    
    
    new QFfileFilterByMaterCode(this.delegator, "so_saleorder_b.cmaterialid.code", "so_saleorder_b.cmffileid").addEditorListener();
    new QFfileFilterByMaterCode(this.delegator, "so_saleorder_b.cmaterialid.code", "so_saleorder_b.cmffileid.vskucode").addEditorListener();
    filterutil.removeFilterMaps(new String[] {"so_saleorder_b.cmffileid","so_saleorder_b.cmffileid.vskucode"});
  }
}
