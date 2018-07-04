package nc.ui.so.m4310.report;

import java.awt.Container;
import java.util.HashMap;
import java.util.Map;

import com.ufida.dataset.IContext;
import com.ufida.report.anareport.base.BaseQueryCondition;
import com.ufida.report.anareport.model.AbsAnaReportModel;

import nc.vo.pub.query.ConditionVO;
import nc.vo.querytemplate.TemplateInfo;
import nc.vo.scmpub.report.SCMReportQueryConUtil;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.salequotation.entity.SalequotationBVO;
import nc.vo.so.salequotation.entity.SalequotationHVO;

import nc.itf.iufo.freereport.extend.IQueryCondition;

import nc.bs.pubapp.report.ReportPermissionUtils;
import nc.bs.scmpub.report.ReportQueryCondition;
import nc.bs.scmpub.report.ReportScaleProcess;

import nc.ui.iufo.freereport.extend.DefaultQueryAction;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.ui.querytemplate.QueryConditionDLG;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.ui.scmpub.query.refregion.QCustBaseClassFilter;
import nc.ui.scmpub.query.refregion.QCustSaleClassFilter;
import nc.ui.scmpub.query.refregion.QCustomerFilter;
import nc.ui.scmpub.query.refregion.QDeptFilter;
import nc.ui.scmpub.query.refregion.QMarSaleClassFilter;
import nc.ui.scmpub.query.refregion.QMarbasclassFilter;
import nc.ui.scmpub.query.refregion.QMarterialFilter;
import nc.ui.scmpub.query.refregion.QPsndocFilter;
import nc.ui.scmpub.query.refregion.QTransTypeFilter;

/**
 * 报价执行查询
 * 
 * @since 6.0
 * @version 2011-3-14 下午03:04:27
 * @author 么贵敬
 */
public class ReportQueryAction extends DefaultQueryAction {

  /**
   * 折扣
   */
  private static final String[] DISCOUNTRATE = {
    // 整单折扣、单品折扣
    SalequotationBVO.NDISCOUNTRATE, SalequotationBVO.NITEMDISCOUNTRATE,
  };

  /**
   * 金额字段
   */
  private static final String[] MNYKEYS = {
    // 价税合计、无税金额
    SalequotationBVO.NORIGTAXMNY, SalequotationBVO.NORIGMNY,
    // 折扣额
    SalequotationBVO.NORIGDISCOUNT
  };

  /**
   * 数量字段
   */
  private static final String[] NUMKEYS = {
    // 数量
      SalequotationBVO.NASSISTNUM
    };

  /**
   * 单价字段
   */
  private static final String[] PRICEMNYKEYS = {
    // 无税单价、含税单价
    SalequotationBVO.NQTORIGNETPRICE, SalequotationBVO.NQTORIGTAXNETPRC,
    SalequotationBVO.NQTORIGPRICE, SalequotationBVO.NQTORIGTAXPRICE
  };

  private static final String[] TOTALKEYS = {
    // 价税合计、无税金额
    SalequotationBVO.NORIGTAXMNY, SalequotationBVO.NORIGMNY,
    // 折扣额
    SalequotationBVO.NORIGDISCOUNT, SalequotationBVO.NASSISTNUM
  };

  // 查询对话框代理
  private QueryConditionDLGDelegator delegator;

  @Override
  public IQueryCondition doQueryByScheme(Container parent, IContext context,
      AbsAnaReportModel reportModel, IQueryScheme queryScheme) {
    IQueryCondition bascon =
        super.doQueryByScheme(parent, context, reportModel, queryScheme);
    // 设置子表冗余字段
    SCMReportQueryConUtil conutil = new SCMReportQueryConUtil();
    conutil.addRedundancyInfo(SalequotationHVO.PK_ORG, "body_pk_org");
    return conutil.getQueryResultAfterAddReduncy(bascon, queryScheme);
  }

  @Override
  protected IQueryCondition createQueryCondition(IContext context) {
    // 权限
    ReportPermissionUtils utils = new ReportPermissionUtils(context);
    utils.setMainBeanClass(SalequotationHVO.class);
    ReportQueryCondition condition = new ReportQueryCondition(true);

    ReportScaleProcess process = new ReportScaleProcess();
    // 金额精度
    process.setMnyDigits(SalequotationHVO.PK_CURRTYPE,
        ReportQueryAction.MNYKEYS);
    // 数量精度
    process
        .setNumDigits(SalequotationBVO.CASTUNITID, ReportQueryAction.NUMKEYS);
    // 税率精度
    process.setTaxRateDigits(new String[] {
      SalequotationBVO.NTAXRATE
    });
    // 折扣精度
    process.setSaleDiscountDigits(new String[] {
      SalequotationBVO.NDISCOUNTRATE, SalequotationBVO.NITEMDISCOUNTRATE
    });

    // 单价精度
    process.setPriceDigits(ReportQueryAction.PRICEMNYKEYS,SalequotationHVO.PK_CURRTYPE);

    // 税率精度
    process.setTaxRateDigits(new String[] {
      SalequotationBVO.NTAXRATE
    });

    // 折扣精度
    process.setSaleDiscountDigits(ReportQueryAction.DISCOUNTRATE);

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
    // 处理子表冗余字段
    this.processBodyItem();
    // 权限过滤
    this.processPowerFilter();
    return this.delegator.getQueryConditionDLG();
  }

  @Override
  protected IQueryCondition setQueryResult(IQueryCondition condition,
      QueryConditionDLG queryDlg, AbsAnaReportModel reportModel,
      IContext context) {

    IQueryCondition bascon =
        super.setQueryResult(condition, queryDlg, reportModel, context);
    // 设置子表冗余字段
    SCMReportQueryConUtil conutil = new SCMReportQueryConUtil();
    conutil.addRedundancyInfo(SalequotationHVO.PK_ORG, "body_pk_org");

    ConditionVO[] conds = queryDlg.getLogicalConditionVOs();
    conutil.changeCustSaleClassCon(condition, conds,
        "this.pk_customer.sales.pk_custsaleclass", "pk_customer");

    conutil.changeMarSaleClassCon(condition, conds,
        "this.pk_material_v.materialsale.pk_marsaleclass", "pk_material_v");
    IQueryScheme queryScheme = queryDlg.getQueryScheme();
    return conutil.getQueryResultAfterAddReduncy(bascon, queryScheme);
  }

  @Override
  protected IQueryCondition showQueryDialog(Container parent, IContext context,
      AbsAnaReportModel reportModel, TemplateInfo tempinfo,
      IQueryCondition oldCondition) {
    QueryConditionDLG queryDlg =
        this.getQueryConditionDlg(parent, context, reportModel, oldCondition);
    if (this.delegator.showModal() == UIDialog.ID_OK) {
      IQueryCondition condition = this.createQueryCondition(context);
      condition =
          this.setQueryResult(condition, queryDlg, reportModel, context);
      return condition;
    }
    return new BaseQueryCondition(false);
  }

  private void processBodyItem() {
    // 销售组织
    this.delegator.addRedundancyInfo(SalequotationHVO.PK_ORG,
        "salequotationdetail.pk_org");
    // // 单据日期
    // this.delegator.addRedundancyInfo(SalequotationHVO.DBILLDATE,
    // "salequotationdetail.dbilldate");
  }

  private void processPowerFilter() {
    this.delegator.registerNeedPermissionOrgFieldCodes(new String[] {
      SalequotationHVO.PK_ORG
    });

    Map<String, String> columnMapping = new HashMap<String, String>();

    columnMapping.put("this.pk_customer.code", SalequotationHVO.PK_CUSTOMER);
    columnMapping.put(SalequotationHVO.PK_DEPT, SalequotationHVO.PK_DEPT);
    columnMapping.put(SalequotationHVO.CEMPLOYEEID,
        SalequotationHVO.CEMPLOYEEID);
    this.delegator.registerRefPowerFilterInfo(SalequotationHVO.class,
        columnMapping);
    Map<String, String> BcolumnMapping = new HashMap<String, String>();
    BcolumnMapping.put("this.pk_material_v.code", SalequotationBVO.PK_MATERIAL);
    this.delegator.registerRefPowerFilterInfo(SalequotationBVO.class,
        BcolumnMapping);

  }

  private void setFilter() {

    // 交易类型参照
    QTransTypeFilter trantype =
        new QTransTypeFilter(this.delegator, SOBillType.SaleQuotation.getCode());
    trantype.filter();

    //过滤销售部门
    QDeptFilter deptFilter = QDeptFilter.createDeptFilterOfSO(
        delegator, SalequotationHVO.PK_DEPT);
    deptFilter.setPk_orgCode(SalequotationHVO.PK_ORG);
    deptFilter.addEditorListener();

    // 过滤业务员
    QPsndocFilter psnFilter = QPsndocFilter.createQPsndocFilterOfSO(
        delegator, SalequotationHVO.CEMPLOYEEID);
    psnFilter.setPk_orgCode(SalequotationHVO.PK_ORG);
    psnFilter.addEditorListener();
    
    // 客户:如果查询一个销售组织的数据，则可参照该销售组织可见的客户，否则参照集团范围客户档案。
    QCustomerFilter cust =
        new QCustomerFilter(this.delegator, "this.pk_customer.code");
    cust.setPk_orgCode(SalequotationHVO.PK_ORG);
    cust.addEditorListener();

    // 客户基本分类
    QCustBaseClassFilter marSaleClass =
        new QCustBaseClassFilter(this.delegator,
            "this.pk_customer.pk_custclass");
    marSaleClass.setPk_orgCode(SalequotationHVO.PK_ORG);
    marSaleClass.addEditorListener();
    // 客户销售分类
    QCustSaleClassFilter cusSaleClass =
        new QCustSaleClassFilter(this.delegator,
            "this.pk_customer.sales.pk_custsaleclass");
    cusSaleClass.setPk_orgCode(SalequotationHVO.PK_ORG);
    cusSaleClass.addEditorListener();

    // 物料编码:参照销售组织可见的物料（销售组织非空且唯一）,否则参照集团可见的物料进行录入
    QMarterialFilter marteral =
        new QMarterialFilter(this.delegator, SalequotationHVO.PK_ORG,
            "this.pk_material_v.code");
    marteral.addEditorListener();

    // 物料基本分类：参照集团物料基本分类
    QMarbasclassFilter marbasclass =
        new QMarbasclassFilter(this.delegator,
            "this.pk_material_v.pk_marbasclass");
    marbasclass.setPk_orgCode(SalequotationHVO.PK_ORG);
    marbasclass.addEditorListener();

    // 物料销售分类
    QMarSaleClassFilter marsaleclass =
        new QMarSaleClassFilter(this.delegator,
            "this.pk_material_v.materialsale.pk_marsaleclass");
    marsaleclass.setPk_orgCode(SalequotationHVO.PK_ORG);
    marsaleclass.addEditorListener();

  }
}
