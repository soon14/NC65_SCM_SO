package nc.ui.so.report.multipleprofit;

import java.awt.Container;

import nc.bs.framework.common.NCLocator;
import nc.bs.ml.NCLangResOnserver;
import nc.itf.iufo.freereport.extend.ISubscribeQueryCondition;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.itf.uap.qrytemplate.IQueryTemplateQry;
import nc.scmmm.pub.scmpub.report.adapter.SCMRptBaseSubscribeCondition;
import nc.ui.iufo.ClientEnv;
import nc.ui.iufo.freereport.extend.DefaultSubscribeAction;
import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.ui.querytemplate.QueryConditionDLG;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.ui.scmpub.query.refregion.QDeptFilter;
import nc.ui.scmpub.query.refregion.QPsndocFilter;
import nc.ui.scmpub.query.refregion.RefCommonFilterListener;
import nc.ui.so.pub.query.refregion.QBatchCodeFilter;
import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.querytemplate.QryTempletVOWithInfo;
import nc.vo.querytemplate.TemplateInfo;
import nc.vo.querytemplate.queryscheme.QuerySchemeVO;

import com.ufida.dataset.IContext;
import com.ufida.report.anareport.FreeReportContextKey;
import com.ufida.report.anareport.model.AbsAnaReportModel;
import com.ufida.zior.exception.MessageException;

/**
 * 综合毛利分析报表订阅条件设置
 * 
 * @since 6.3
 * @version 2012-10-23 16:26:15
 * @author zhangkai4
 */

public class MulProfitSubscribeAction extends DefaultSubscribeAction {

  private QueryConditionDLGDelegator m_delegator;

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
  

  /**
   * 需要从中获取查询方案面板
   * 
   * 
   * @param parent
   * @param context
   * @param reportModel
   * @param cond
   * @return m_queryDlg
   */
  @Override
  public QueryConditionDLG getQueryConditionDlg(Container parent,
      IContext context, AbsAnaReportModel reportModel,
      ISubscribeQueryCondition cond) {

    TemplateInfo tempinfo = this.getTempletInfo(context);
    if (this.hasQueryTemplet(tempinfo)) {
      if (null == this.m_delegator) {
        this.m_delegator = new QueryConditionDLGDelegator(parent, tempinfo);
      }
      // 1.处理字段过滤
      this.processFilter();
      this.m_delegator.registerNeedPermissionOrgFieldCodes(new String[] {
         "pk_org"
        });
      
      this.m_queryDlg = this.m_delegator.getQueryConditionDLG();
      return this.m_queryDlg;
    }

    return this.m_queryDlg;
  }

  @Override
  protected ISubscribeQueryCondition getConditionFromDlg(
      ISubscribeQueryCondition oldCondition, QueryConditionDLG queryDlg,
      AbsAnaReportModel reportModel, IContext context) {
    IQueryScheme scheme = queryDlg.getQueryScheme();
    if (scheme == null) {
      return null;
    }
    QuerySchemeVO schemeVO =
        this.m_queryDlg.getQryCondEditor().getSelectedQuerySchemeVO();
    if (schemeVO != null) {
      scheme.put(IQueryScheme.KEY_NAME, schemeVO.getName());
    }

    SCMRptBaseSubscribeCondition result =
        new SCMRptBaseSubscribeCondition(scheme);
    return result;
  }

  private void processFilter() {
    // 所有参照按照销售组织过滤
    RefCommonFilterListener filterutil =
        new RefCommonFilterListener(this.m_delegator, "pk_org");
    filterutil.removeFilterMaps(new String[] {
      "csaleorgid"
    });
    filterutil.addFilterMapsListeners();
    
    //过滤批次档案
    QBatchCodeFilter batch = new QBatchCodeFilter();
    batch.filter(this.m_delegator,"vbatchcode");
    
    // 销售业务员：参照销售组织可见的人员（销售组织非空且唯一）,否则参照集团可见的物料进行录入
    QPsndocFilter employee =
        QPsndocFilter.createQPsndocFilterOfSO(this.m_delegator, "cemployeeid");
    employee.setPk_orgCode("csaleorgid");
    employee.addEditorListener();

    // 销售部门: 参照销售组织可见的部门（销售组织非空且唯一）,否则参照集团可见的物料进行录入
    QDeptFilter dept =  QDeptFilter.createDeptFilterOfSO(this.m_delegator, "cdeptid");
    dept.setPk_orgCode("csaleorgid");
    dept.addEditorListener();
    
    
  }

  private TemplateInfo getTempletInfo(IContext context) {
    TemplateInfo tempinfo = new TemplateInfo();
    tempinfo.setPk_Org(ClientEnv.getInstance().getGroupID());
    tempinfo.setFunNode((String) context
        .getAttribute(FreeReportContextKey.REPORT_FUNCODE));
    tempinfo.setUserid(ClientEnv.getInstance().getLoginUserID());
    return tempinfo;
  }

  private boolean hasQueryTemplet(TemplateInfo ti) {
    IQueryTemplateQry qry =
        NCLocator.getInstance().lookup(IQueryTemplateQry.class);
    QryTempletVOWithInfo temp;
    try {
      temp = qry.findAndGetTemplateVO(ti);
      return temp != null;
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return false;
  }
}
