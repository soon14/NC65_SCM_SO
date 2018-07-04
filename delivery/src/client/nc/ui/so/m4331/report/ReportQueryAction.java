package nc.ui.so.m4331.report;

import java.awt.Container;
import java.util.HashMap;
import java.util.Map;

import com.ufida.dataset.IContext;
import com.ufida.report.anareport.base.BaseQueryCondition;
import com.ufida.report.anareport.model.AbsAnaReportModel;

import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.query.ConditionVO;
import nc.vo.querytemplate.TemplateInfo;
import nc.vo.scmpub.report.SCMReportQueryConUtil;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m4331.entity.DeliveryBVO;
import nc.vo.so.m4331.entity.DeliveryHVO;

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
import nc.ui.scmpub.query.refregion.QCustomerFilter;
import nc.ui.scmpub.query.refregion.QDeptFilter;
import nc.ui.scmpub.query.refregion.QFfileFilterByMaterCode;
import nc.ui.scmpub.query.refregion.QMarSaleClassFilter;
import nc.ui.scmpub.query.refregion.QMarbasclassFilter;
import nc.ui.scmpub.query.refregion.QMarterialFilter;
import nc.ui.scmpub.query.refregion.QPsndocFilter;
import nc.ui.scmpub.query.refregion.QTransTypeFilter;
import nc.ui.scmpub.query.refregion.RefCommonFilterListener;

/**
 * 发货单执行查询
 *
 * @since 6.0
 * @version 2011-3-14 下午03:04:27
 * @author 么贵敬
 */
/**
 * 
 * @since 6.0
 * @version 2011-8-29 下午12:23:16
 * @author 么贵敬
 */
@SuppressWarnings("restriction")
public class ReportQueryAction extends DefaultQueryAction {

  /**
   * 数量字段
   */
  private static final String[] NUMKEYS = {
    // 主数量\应收主数量
    DeliveryBVO.NNUM, DeliveryBVO.NTOTALOUTNUM, DeliveryBVO.NTRANSLOSSNUM,
    DeliveryBVO.NTOTALTRANSNUM
  };

  /**
   * 合计字段
   */
  private static final String[] TOTALKEYS = {
    // 主数量\应收主数量
    DeliveryBVO.NNUM, DeliveryBVO.NTOTALOUTNUM, DeliveryBVO.NTRANSLOSSNUM,
    DeliveryBVO.NTOTALTRANSNUM, DeliveryBVO.NASTNUM, DeliveryBVO.NWEIGHT,
    DeliveryBVO.NVOLUME, DeliveryBVO.NPIECE
  };

  // 查询对话框代理
  private QueryConditionDLGDelegator delegator;

  @Override
  protected IQueryCondition createQueryCondition(IContext context) {

    // 权限
    ReportPermissionUtils utils = new ReportPermissionUtils(context);
    utils.setMainBeanClass(DeliveryHVO.class);

    ReportQueryCondition condition = new ReportQueryCondition(true);

    ReportScaleProcess process = new ReportScaleProcess();

    // 主数量精度
    process.setNumDigits(DeliveryBVO.CUNITID, ReportQueryAction.NUMKEYS);

    // 辅数量精度
    process.setNumDigits(DeliveryBVO.CASTUNITID, new String[] {
      DeliveryBVO.NASTNUM
    });
    // 重量精度
    process.setStandWeightDigits(new String[] {
      DeliveryBVO.NWEIGHT
    });
    // 体积精度
    process.setStandardVolumnDigits(new String[] {
      DeliveryBVO.NVOLUME
    });
    // 件数精度
    process.setUnitDigits(DeliveryBVO.CMATERIALVID, new String[] {
      DeliveryBVO.NPIECE
    });

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
  public IQueryCondition doQueryByScheme(Container parent, IContext context,
      AbsAnaReportModel reportModel, IQueryScheme queryScheme) {
    IQueryCondition bascon =
        super.doQueryByScheme(parent, context, reportModel, queryScheme);
    SCMReportQueryConUtil conutil = new SCMReportQueryConUtil();
    conutil.addRedundancyInfo(DeliveryHVO.DBILLDATE, "body_dbilldate");
    conutil.addRedundancyInfo(DeliveryHVO.PK_ORG, "body_pk_org");
    return conutil.getQueryResultAfterAddReduncy(bascon, queryScheme);
  }

  private void processBodyItem() {
    // 销售组织
    this.delegator
        .addRedundancyInfo(DeliveryHVO.PK_ORG, "so_delivery_b.pk_org");
    // 单据日期
    this.delegator.addRedundancyInfo(DeliveryHVO.DBILLDATE,
        "so_delivery_b.dbilldate");
  }

  private void processPowerFilter() {
    this.delegator.registerNeedPermissionOrgFieldCodes(new String[] {
      DeliveryHVO.PK_ORG
    });

    Map<String, String> columnMapping = new HashMap<String, String>();

    columnMapping.put("this.cordercustid.code", DeliveryBVO.CORDERCUSTID);
    columnMapping.put("this.creceivecustid.code", DeliveryBVO.CRECEIVECUSTID);
    columnMapping.put(DeliveryBVO.CEMPLOYEEID, DeliveryBVO.CEMPLOYEEID);
    columnMapping.put(DeliveryBVO.CDEPTID, DeliveryBVO.CDEPTID);

    columnMapping.put("this.cmaterialvid.code", DeliveryBVO.CMATERIALID);
    this.delegator.registerRefPowerFilterInfo(DeliveryBVO.class, columnMapping);
  }

  private void setFilter() {
    // 交易类型参照
    QTransTypeFilter trantype =
        new QTransTypeFilter(this.delegator, SOBillType.Delivery.getCode());
    trantype.filter();

    // 客户:如果查询一个销售组织的数据，则可参照该销售组织可见的客户，否则参照集团范围客户档案。
    QCustomerFilter cust =
        new QCustomerFilter(this.delegator, "this.cordercustid.code");
    cust.setPk_orgCode(DeliveryHVO.PK_ORG);
    cust.addEditorListener();

    // 客户基本分类
    QCustBaseClassFilter marSaleClass =
        new QCustBaseClassFilter(this.delegator,
            "this.cordercustid.pk_custclass");
    marSaleClass.setPk_orgCode(DeliveryHVO.PK_ORG);
    marSaleClass.addEditorListener();

    // 收货客户
    QCustomerFilter reccust =
        new QCustomerFilter(this.delegator, "this.creceivecustid.code");
    reccust.setPk_orgCode(DeliveryHVO.PK_ORG);
    reccust.addEditorListener();

    // 过滤销售部门
    QDeptFilter deptFilter =
        QDeptFilter.createDeptFilterOfTR(this.delegator,
            DeliveryHVO.CSENDDEPTID);
    deptFilter.setPk_orgCode(DeliveryHVO.PK_ORG);
    deptFilter.addEditorListener();

    // 过滤业务员
    QPsndocFilter psnFilter =
        QPsndocFilter.createQPsndocFilterOfTR(this.delegator,
            DeliveryHVO.CSENDEMPLOYEEID);
    psnFilter.setPk_orgCode(DeliveryHVO.PK_ORG);
    psnFilter.addEditorListener();

    // 物料编码:参照销售组织可见的物料（销售组织非空且唯一）,否则参照集团可见的物料进行录入
    QMarterialFilter marteral =
        new QMarterialFilter(this.delegator, DeliveryHVO.PK_ORG,
            "this.cmaterialvid.code");
    marteral.addEditorListener();

    // 物料基本分类：参照集团物料基本分类
    QMarbasclassFilter marbasclass =
        new QMarbasclassFilter(this.delegator,
            "this.cmaterialvid.pk_marbasclass");
    marbasclass.setPk_orgCode(DeliveryHVO.PK_ORG);
    marbasclass.addEditorListener();

    // 物料销售分类
    QMarSaleClassFilter marsaleclass =
        new QMarSaleClassFilter(this.delegator,
            "this.cmaterialvid.materialsale.pk_marsaleclass");
    marsaleclass.setPk_orgCode(DeliveryHVO.PK_ORG);
    marsaleclass.addEditorListener();

    new QFfileFilterByMaterCode(this.delegator,
        "cdeliverybid.cmaterialid.code", "cdeliverybid.cmffileid")
        .addEditorListener();
    new QFfileFilterByMaterCode(this.delegator,
        "cdeliverybid.cmaterialid.code", "cdeliverybid.cmffileid.vskucode")
        .addEditorListener();
    
    
    //项目补丁增加 NCdp205265254 ，增加仓库查询条件无法参照
    // 发货仓库按照物流组织过滤
    RefCommonFilterListener filterutil =
        new RefCommonFilterListener(this.delegator, DeliveryHVO.PK_ORG);
    String sendstockorgkey =
        DeliveryBVO.MAINMETAPATH + DeliveryBVO.CSENDSTOCKORGID;
    filterutil.addFilterMaps(new String[] {
      "this.csendstordocid"
    }, sendstockorgkey);
    filterutil.addFilterMapsListeners();

  }

  @Override
  protected IQueryCondition setQueryResult(IQueryCondition condition,
      QueryConditionDLG queryDlg, AbsAnaReportModel reportModel,
      IContext context) {
    IQueryCondition bascon =
        super.setQueryResult(condition, queryDlg, reportModel, context);
    // 设置子表冗余字段
    SCMReportQueryConUtil conutil = new SCMReportQueryConUtil();
    conutil.addRedundancyInfo(DeliveryHVO.DBILLDATE, "body_dbilldate");
    conutil.addRedundancyInfo(DeliveryHVO.PK_ORG, "body_pk_org");

    ConditionVO[] conds = queryDlg.getLogicalConditionVOs();
    conutil.changeCustSaleClassCon(condition, conds,
        "this.cordercustid.sales.pk_custsaleclass", "cordercustid");

    conutil.changeMarSaleClassCon(condition, conds,
        "this.cmaterialvid.materialsale.pk_marsaleclass", "cmaterialvid");
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
}
