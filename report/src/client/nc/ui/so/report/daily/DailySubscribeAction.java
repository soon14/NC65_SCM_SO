package nc.ui.so.report.daily;

import java.awt.Container;
import java.util.HashMap;
import java.util.Map;

import com.ufida.dataset.IContext;
import com.ufida.report.anareport.model.AbsAnaReportModel;
import com.ufida.zior.exception.MessageException;

import nc.vo.ic.general.define.ICBillFlag;
import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.querytemplate.QryTempletVOWithInfo;
import nc.vo.querytemplate.TemplateInfo;
import nc.vo.querytemplate.queryscheme.QuerySchemeVO;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.util.SaleOrderVOUtil;
import nc.vo.so.m32.util.SaleInvoiceVOUtil;
import nc.vo.so.m4331.entity.DeliveryHVO;
import nc.vo.so.m4331.pub.DeliveryVoUtil;
import nc.vo.so.report.enumeration.DailyRptQryFieldCode;

import nc.itf.iufo.freereport.extend.ISubscribeQueryCondition;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.itf.uap.qrytemplate.IQueryTemplateQry;

import nc.bs.framework.common.NCLocator;
import nc.bs.ml.NCLangResOnserver;

import nc.ui.iufo.ClientEnv;
import nc.ui.iufo.freereport.extend.DefaultSubscribeAction;
import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.ui.pubapp.uif2app.query2.totalvo.EnumRefRegisterInfo;
import nc.ui.querytemplate.QueryConditionDLG;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.ui.scmpub.query.refregion.QCustomerFilter;
import nc.ui.scmpub.query.refregion.QDeptFilter;
import nc.ui.scmpub.query.refregion.QMarSaleClassFilter;
import nc.ui.scmpub.query.refregion.QMarbasclassFilter;
import nc.ui.scmpub.query.refregion.QMarterialFilter;
import nc.ui.scmpub.query.refregion.QPsndocFilter;
import nc.ui.scmpub.query.refregion.QTransTypeFilter;

/**
 * 综合日报报表订阅条件设置
 * 
 * @since 6.0
 * @version 2011-8-3 下午03:04:17
 * @author 么贵敬
 */
public class DailySubscribeAction extends DefaultSubscribeAction {

  private QueryConditionDLGDelegator delegator;

  private QueryConditionDLG m_queryDlg;

  @Override
  public ISubscribeQueryCondition doSubscribeAction(Container parent,
      IContext context, AbsAnaReportModel reportModel,
      ISubscribeQueryCondition oldCondition) {
    if (!SysInitGroupQuery.isAREnabled()) {
      throw new MessageException(NCLangRes4VoTransl.getNCLangRes().getStrByID(
          "4006005_0", "04006005-0025")/* 财务模块未启用，无法执行查询！ */);
    }

    if (!SysInitGroupQuery.isICEnabled()) {
      throw new MessageException(NCLangRes4VoTransl.getNCLangRes().getStrByID(
          "4006005_0", "04006005-0026")/* 库存模块未启用，无法执行查询！ */);
    }
    if (!SysInitGroupQuery.isIAEnabled()) {
      ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
          .getStrByID("4006005_0", "04006005-0029")/*存货核算模块未启用，无法执行查询！*/);
    }
    return super.doSubscribeAction(parent, context, reportModel, oldCondition);
  }

  @Override
  public QueryConditionDLG getQueryConditionDlg(Container parent,
      com.ufida.dataset.IContext context,
      com.ufida.report.anareport.model.AbsAnaReportModel reportModel,
      ISubscribeQueryCondition cond) {
    TemplateInfo tempinfo = this.getTempletInfo(context);
    if (this.hasQueryTemplet(tempinfo)) {
      if (null == this.delegator) {
        this.delegator = new QueryConditionDLGDelegator(parent, tempinfo);
      }

      // 处理字段过滤
      this.initListener();
      // 处理权限字段过滤
      this.processPowerFilter();
      // 处理单据状态查询条件
      this.processBillStatus();
      this.m_queryDlg = this.delegator.getQueryConditionDLG();
      this.m_queryDlg.getQryCondEditor().getQueryContext().setMultiTB(true);

      if (null == cond) {
        return this.m_queryDlg;
      }
      // 显示上次设置的条件
      if (cond instanceof com.ufida.report.anareport.base.BaseSubscribeCondition) {
        com.ufida.report.anareport.base.BaseSubscribeCondition baseCond =
            (com.ufida.report.anareport.base.BaseSubscribeCondition) cond;
        // 将IQueryScheme设置到界面上
        IQueryScheme scheme = baseCond.getScheme();
        if (scheme != null) {
          String qsName = scheme.getName();
          this.m_queryDlg.getQryCondEditor().setQsSelectedByName(qsName);
        }

      }
    }

    return this.m_queryDlg;
  }

  private void processBillStatus() {
    this.processStockBillStatus();// 销售出库
    this.processOrderkBillStatus();// 销售订单
    this.processInvoicekBillStatus();// 销售发票
    this.processDeliverkBillStatus();// 发货单状态
  }

  private void processOrderkBillStatus() {
    SaleOrderVOUtil util = new SaleOrderVOUtil();
    EnumRefRegisterInfo info =
        new EnumRefRegisterInfo(util.getBillStatusName(),
            util.getBillStatusValue());
    this.delegator.setComboxItem(null,
        DailyRptQryFieldCode.saleOrderStatus.getCode(), info);
  }

  private void processInvoicekBillStatus() {
    SaleInvoiceVOUtil util = new SaleInvoiceVOUtil();
    EnumRefRegisterInfo info =
        new EnumRefRegisterInfo(util.getBillStatusName(),
            util.getBillStatusValue());
    this.delegator.setComboxItem(null,
        DailyRptQryFieldCode.saleInvoiceStatus.getCode(), info);
  }

  private void processDeliverkBillStatus() {
    DeliveryVoUtil util = new DeliveryVoUtil();
    EnumRefRegisterInfo info =
        new EnumRefRegisterInfo(util.getBillStatusName(),
            util.getBillStatusValue());
    this.delegator.setComboxItem(null,
        DailyRptQryFieldCode.deliveryStatus.getCode(), info);
  }

  /**
   * 出库单单据状态处理
   */
  private void processStockBillStatus() {
    String[] names = new String[] {
      ICBillFlag.FREE.getName(),// 自由
      ICBillFlag.SIGN.getName()
    // 签字
        };
    String[] values = new String[] {
      ICBillFlag.FREE.toStringValue(), ICBillFlag.SIGN.toStringValue()
    };
    EnumRefRegisterInfo info = new EnumRefRegisterInfo(names, values);
    this.delegator.setComboxItem(null,
        DailyRptQryFieldCode.generalStatus.getCode(), info);
  }

  @Override
  protected ISubscribeQueryCondition getConditionFromDlg(
      ISubscribeQueryCondition oldCondition, QueryConditionDLG queryDlg,
      com.ufida.report.anareport.model.AbsAnaReportModel reportModel,
      com.ufida.dataset.IContext context) {
    IQueryScheme scheme = queryDlg.getQueryScheme();
    if (scheme == null) {
      return null;
    }
    QuerySchemeVO schemeVO =
        this.m_queryDlg.getQryCondEditor().getSelectedQuerySchemeVO();
    if (schemeVO != null) {
      scheme.put(IQueryScheme.KEY_NAME, schemeVO.getName());
    }

    com.ufida.report.anareport.base.BaseSubscribeCondition result =
        new com.ufida.report.anareport.base.BaseSubscribeCondition(scheme);
    return result;
  }

  private TemplateInfo getTempletInfo(com.ufida.dataset.IContext context) {
    TemplateInfo tempinfo = new TemplateInfo();
    tempinfo.setPk_Org(ClientEnv.getInstance().getGroupID());
    String report_funcode =
        com.ufida.report.anareport.FreeReportContextKey.REPORT_FUNCODE;
    tempinfo.setFunNode((String) context.getAttribute(report_funcode));
    tempinfo.setUserid(ClientEnv.getInstance().getLoginUserID());
    return tempinfo;
  }

  private boolean hasQueryTemplet(TemplateInfo ti) {
    IQueryTemplateQry qry =
        NCLocator.getInstance().lookup(IQueryTemplateQry.class);
    try {
      QryTempletVOWithInfo temp = qry.findAndGetTemplateVO(ti);
      return temp != null;
    }
    catch (BusinessException ex) {
      com.ufida.iufo.pub.tools.AppDebug.debug(ex);
    }
    return false;
  }

  /**
   * UAP默认监听事件先注册的后执行，因此将财务组织改变事件监听放到最后，让它可以最先执行，从而可以进行数据合法性校验
   * 
   * @param delegator
   */
  private void initListener() {
    String pk_org = "csaleorgid";
    // 销售订单交易类型
    QTransTypeFilter trantype =
        new QTransTypeFilter(this.delegator, SaleOrderHVO.CTRANTYPEID,
            SOBillType.Order.getCode());
    trantype.filter();

    // 客户:如果查询一个销售组织的数据，则可参照该销售组织可见的客户，否则参照集团范围客户档案。
    QCustomerFilter cust = new QCustomerFilter(this.delegator, "ccustomercode");
    cust.setPk_orgCode(pk_org);
    cust.addEditorListener();

    // 客户基本分类
    QMarSaleClassFilter marSaleClass =
        new QMarSaleClassFilter(this.delegator, "pk_custclass");
    marSaleClass.setPk_orgCode(SaleOrderHVO.PK_ORG);
    marSaleClass.addEditorListener();

    // 销售业务员：参照销售组织可见的人员（销售组织非空且唯一）,否则参照集团可见的物料进行录入
    QPsndocFilter employee =
        QPsndocFilter.createQPsndocFilterOfSO(this.delegator,
            SaleOrderHVO.CEMPLOYEEID);
    employee.setPk_orgCode(pk_org);
    employee.addEditorListener();

    // 销售部门: 参照销售组织可见的部门（销售组织非空且唯一）,否则参照集团可见的物料进行录入
    QDeptFilter dept =
        QDeptFilter.createDeptFilterOfSO(this.delegator, SaleOrderHVO.CDEPTID);
    dept.setPk_orgCode(pk_org);
    dept.addEditorListener();
    // 物料编码:参照销售组织可见的物料（销售组织非空且唯一）,否则参照集团可见的物料进行录入
    QMarterialFilter marteral =
        new QMarterialFilter(this.delegator, pk_org, "cmaterialcode");
    marteral.addEditorListener();

    // 物料基本分类：参照集团物料基本分类
    QMarbasclassFilter marbasclass =
        new QMarbasclassFilter(this.delegator, "pk_marbasclass");
    marbasclass.setPk_orgCode(pk_org);
    marbasclass.addEditorListener();
  }

  private void processPowerFilter() {
    this.delegator.registerNeedPermissionOrgFieldCodes(new String[] {
      "csaleorgid"
    });
    Map<String, String> columnMapping = new HashMap<String, String>();
    columnMapping.put("ccustomercode", SaleOrderHVO.CCUSTOMERID);
    columnMapping.put(SaleOrderHVO.CDEPTID, SaleOrderHVO.CDEPTID);
    this.delegator
        .registerRefPowerFilterInfo(SaleOrderHVO.class, columnMapping);

    Map<String, String> bcolumnMapping = new HashMap<String, String>();
    bcolumnMapping.put("cmaterialcode", SaleOrderBVO.CMATERIALID);
    this.delegator.registerRefPowerFilterInfo(SaleOrderBVO.class,
        bcolumnMapping);
  }
}
