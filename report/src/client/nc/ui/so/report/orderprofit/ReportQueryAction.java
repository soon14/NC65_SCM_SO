package nc.ui.so.report.orderprofit;

import java.awt.Container;

import nc.bs.scmpub.report.ReportQueryCondition;
import nc.bs.scmpub.report.ReportScaleProcess;
import nc.itf.iufo.freereport.extend.IQueryCondition;
import nc.itf.so.orderprofit.ProfitAggrDescriptor;
import nc.pub.smart.model.descriptor.Descriptor;
import nc.ui.iufo.freereport.extend.DefaultQueryAction;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.ui.querytemplate.QueryConditionDLG;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.ui.scmpub.query.refregion.QCustBaseClassFilter;
import nc.ui.scmpub.query.refregion.QCustSaleClassFilter;
import nc.ui.scmpub.query.refregion.QCustomerFilter;
import nc.ui.scmpub.query.refregion.QDeptFilter;
import nc.ui.scmpub.query.refregion.QFfileFilterByMaterCode;
import nc.ui.scmpub.query.refregion.QMarSaleClassFilter;
import nc.ui.scmpub.query.refregion.QMarbasclassFilter;
import nc.ui.scmpub.query.refregion.QMarterialFilter;
import nc.ui.scmpub.query.refregion.QPsndocFilter;
import nc.vo.pub.query.ConditionVO;
import nc.vo.pubapp.query2.sql.process.QuerySchemeProcessor;
import nc.vo.querytemplate.TemplateInfo;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.report.paravo.ProfitContext;
import nc.vo.so.report.paravo.ProfitQryInfoParaVO;
import nc.vo.so.report.paravo.ProfitUserObj;
import nc.vo.so.report.profit.OrderProfitViewVO;
import nc.vo.so.report.util.ProfitReportAdjustor;

/**
 * 销售订单毛利分析查询
 * 
 * @since 6.0
 * @version 2011-3-14 下午03:04:27
 * @author 么贵敬
 */
@SuppressWarnings("restriction")
public class ReportQueryAction extends DefaultQueryAction {

  /**
   * 金额
   */
  private static final String[] MNYKEYS = new String[] {
    OrderProfitViewVO.NMAINNUM, OrderProfitViewVO.NNTAXMNY,
    OrderProfitViewVO.NTOTALCOSTMNY, OrderProfitViewVO.NTOTALESTARNUM,
    OrderProfitViewVO.NTOTALARNUM, OrderProfitViewVO.NTOTALCOSTNUM,
    OrderProfitViewVO.NTOTALRECEIVNUM, OrderProfitViewVO.NORDERNNUM,

  };

  /**
   * 数量
   */
  private static final String[] NUMKEYS = new String[] {
    OrderProfitViewVO.NQUANTITY_DE, OrderProfitViewVO.NTOTALESTARMNY,
    OrderProfitViewVO.NPROFITMNY, OrderProfitViewVO.NTOTALRECEIVMNY,
    OrderProfitViewVO.NTOTALSETTLECOSTMNY, OrderProfitViewVO.NPROFITCATE,
    OrderProfitViewVO.NTAX_DE, OrderProfitViewVO.NTOTALARMNY
  };

  /**
   * 单价
   */
  private static final String[] PRICEMNYKEYS = new String[] {
    OrderProfitViewVO.NNETPRICE, OrderProfitViewVO.NPRICE,
    OrderProfitViewVO.NNTAXPRICE, OrderProfitViewVO.NCOSTPRICE,
  };

  private QueryConditionDLGDelegator delegator;

  @Override
  protected IQueryCondition createQueryCondition(
      com.ufida.dataset.IContext context) {
    ReportQueryCondition condition = new ReportQueryCondition(true);
    ReportScaleProcess process = new ReportScaleProcess();
    // 金额精度
    process.setMnyDigits(SaleOrderBVO.CCURRENCYID, ReportQueryAction.MNYKEYS);
    // 数量精度
    process.setNumDigits("cunitid", ReportQueryAction.NUMKEYS);
    // 单价精度
    process.setCostPriceDigits(ReportQueryAction.PRICEMNYKEYS);

    condition.setBusiFormat(process);
    return condition;
  }

  @Override
  protected QueryConditionDLG createQueryDlg(Container parent, TemplateInfo ti,
      com.ufida.dataset.IContext context, IQueryCondition oldCondition) {

    if (this.delegator == null) {
      this.delegator = new QueryConditionDLGDelegator(parent, ti);
    }
    // 为查询模板的字段参照等过滤
    this.setFilter();
    // 处理子表冗余字段
    this.processBodyItem();
    return this.delegator.getQueryConditionDLG();
  }

  @Override
  protected IQueryCondition setQueryResult(IQueryCondition qcond,
      QueryConditionDLG queryDlg,
      com.ufida.report.anareport.model.AbsAnaReportModel reportModel,
      com.ufida.dataset.IContext context) {
    if (qcond == null) {
      return qcond;
    }

    if (!(qcond instanceof com.ufida.report.anareport.base.BaseQueryCondition)) {
      return qcond;
    }
    com.ufida.report.anareport.base.BaseQueryCondition result =
        (com.ufida.report.anareport.base.BaseQueryCondition) qcond;

    IQueryScheme queryScheme = queryDlg.getQueryScheme();

    QuerySchemeProcessor qsp = new QuerySchemeProcessor(queryScheme);

    String subtablename = qsp.getTableAliasOfAttribute("so_saleorder_b.pk_org");
    // 增加集团
    qsp.appendCurrentGroup();

    this.appendForm(qsp, subtablename);

    String fromwhere = qsp.getFinalFromWhere();

    ProfitQryInfoParaVO paravo = new ProfitQryInfoParaVO();
    ConditionVO[] conds = queryDlg.getLogicalConditionVOs();

    ProfitUserObj userobj = new ProfitUserObj();
    userobj.setConds(conds);
    userobj.setWheresql(fromwhere);
    userobj.setSubtablename(subtablename);
    for (ConditionVO cond : conds) {
      if (cond.getFieldCode().equals(ProfitContext.SUMMARYCONDITIONS)) {
        paravo.setGroupcondtion(cond.getValue());
      }
    }

    result.setUserObject(userobj);
    result.setDescriptors(new Descriptor[] {
      new ProfitAggrDescriptor<ProfitQryInfoParaVO>(paravo)
    });
    result.setRoportAdjustor(new ProfitReportAdjustor());
    return result;
  }

  @Override
  protected IQueryCondition showQueryDialog(Container parent,
      com.ufida.dataset.IContext context,
      com.ufida.report.anareport.model.AbsAnaReportModel reportModel,
      TemplateInfo tempinfo, IQueryCondition oldCondition) {
    QueryConditionDLG queryDlg =
        this.getQueryConditionDlg(parent, context, reportModel, oldCondition);
    QueryConditionDLGDelegator dlgDelegator = this.getDLGDelegator(queryDlg);
    if (dlgDelegator.showModal() == UIDialog.ID_OK) {
      IQueryCondition condition = this.createQueryCondition(context);
      condition =
          this.setQueryResult(condition, queryDlg, reportModel, context);
      return condition;
    }
    return new com.ufida.report.anareport.base.BaseQueryCondition(false);
  }

  private void appendForm(QuerySchemeProcessor qsp, String subtablename) {
    // 客户资料表

    qsp.appendFrom(" inner join so_saleorder_exe so_saleorder_exe");
    qsp.appendFrom(" on " + subtablename
        + ".csaleorderbid = so_saleorder_exe.csaleorderbid");

    // qsp.appendFrom(" inner join bd_customer bd_customer");
    // qsp.appendFrom(" on so_saleorder.ccustomerid = bd_customer.pk_customer");
    //
    // qsp.appendFrom(" inner join bd_custsale bd_custsale ");
    // qsp.appendFrom(" on  bd_customer.pk_customer = bd_custsale.pk_customer");
    // qsp.appendFrom(" and bd_customer.pk_org=so_saleorder.pk_org");
    //
    // qsp.appendFrom(" inner join bd_material bd_material ");
    // qsp.appendFrom("  on  T1.cmaterialvid = bd_material.pk_material ");
    //
    // qsp.appendFrom(" inner join bd_materialsale bd_materialsale");
    // qsp.appendFrom(" on bd_material.pk_material = bd_materialsale.pk_material");
    // qsp.appendFrom(" and so_saleorder.pk_org=bd_materialsale.pk_org");
  }

  private QueryConditionDLGDelegator getDLGDelegator(QueryConditionDLG queryDlg) {
    QueryConditionDLGDelegator condDLGDelegator =
        new QueryConditionDLGDelegator(queryDlg);

    return condDLGDelegator;
  }

  private void processBodyItem() {
    // 销售组织
    this.delegator.addRedundancyInfo(SaleOrderHVO.PK_ORG,
        "so_saleorder_b.pk_org");
    // 单据日期
    this.delegator.addRedundancyInfo(SaleOrderHVO.DBILLDATE,
        "so_saleorder_b.dbilldate");
  }

  private void setFilter() {

    // 开票客户:如果查询一个销售组织的数据，则可参照该销售组织可见的客户，否则参照集团范围客户档案。
    QCustomerFilter invoicecust =
        new QCustomerFilter(this.delegator, "cinvoicecustid");
    invoicecust.setPk_orgCode(SaleOrderHVO.PK_ORG);
    invoicecust.addEditorListener();

    // 客户:如果查询一个销售组织的数据，则可参照该销售组织可见的客户，否则参照集团范围客户档案。
    QCustomerFilter cust = new QCustomerFilter(this.delegator, "ccustomerid");
    cust.setPk_orgCode(SaleOrderHVO.PK_ORG);
    cust.addEditorListener();

    // 客户基本分类
    QCustBaseClassFilter marSaleClass =
        new QCustBaseClassFilter(this.delegator, "ccustomerid.pk_custclass");
    marSaleClass.setPk_orgCode(SaleOrderHVO.PK_ORG);
    marSaleClass.addEditorListener();

    // 客户销售分类
    QCustSaleClassFilter custsaleclass =
        new QCustSaleClassFilter(this.delegator,
            "ccustomerid.sales.pk_custsaleclass");
    custsaleclass.setPk_orgCode(SaleOrderHVO.PK_ORG);
    custsaleclass.addEditorListener();

    // 销售业务员：参照销售组织可见的人员（销售组织非空且唯一）,否则参照集团可见的物料进行录入
    QPsndocFilter employee =
        new QPsndocFilter(this.delegator, SaleOrderHVO.CEMPLOYEEID);
    employee.setPk_orgCode(SaleOrderHVO.PK_ORG);
    employee.addEditorListener();

    // 销售部门: 参照销售组织可见的部门（销售组织非空且唯一）,否则参照集团可见的物料进行录入
    QDeptFilter dept = new QDeptFilter(this.delegator, SaleOrderHVO.CDEPTVID);
    dept.setPk_orgCode(SaleOrderHVO.PK_ORG);
    dept.addEditorListener();

    // 物料编码:参照销售组织可见的物料（销售组织非空且唯一）,否则参照集团可见的物料进行录入
    QMarterialFilter marteral =
        new QMarterialFilter(this.delegator, SaleOrderHVO.PK_ORG,
            "so_saleorder_b.cmaterialid");
    marteral.addEditorListener();

    // 物料基本分类：参照集团物料基本分类
    QMarbasclassFilter marbasclass =
        new QMarbasclassFilter(this.delegator,
            "so_saleorder_b.cmaterialid.pk_marbasclass");
    marbasclass.setPk_orgCode(SaleOrderHVO.PK_ORG);
    marbasclass.addEditorListener();

    // 物料销售分类
    QMarSaleClassFilter marsaleclass =
        new QMarSaleClassFilter(this.delegator,
            "so_saleorder_b.cmaterialvid.materialsale.pk_marsaleclass");
    marsaleclass.setPk_orgCode(SaleOrderHVO.PK_ORG);
    marsaleclass.addEditorListener();
    
    new QFfileFilterByMaterCode(this.delegator, "so_saleorder_b.cmaterialid.code", "so_saleorder_b.cmffileid").addEditorListener();
    new QFfileFilterByMaterCode(this.delegator, "so_saleorder_b.cmaterialid.code", "so_saleorder_b.cmffileid.vskucode").addEditorListener();

  }
}
