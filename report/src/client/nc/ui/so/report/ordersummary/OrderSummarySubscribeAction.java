package nc.ui.so.report.ordersummary;

import java.awt.Container;

import nc.bs.framework.common.NCLocator;
import nc.itf.iufo.freereport.extend.ISubscribeQueryCondition;
import nc.itf.uap.qrytemplate.IQueryTemplateQry;
import nc.scmmm.pub.scmpub.report.adapter.SCMRptBaseSubscribeCondition;
import nc.ui.iufo.ClientEnv;
import nc.ui.iufo.freereport.extend.DefaultSubscribeAction;
import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.ui.querytemplate.QueryConditionDLG;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.ui.scmpub.query.refregion.QDeptFilter;
import nc.ui.scmpub.query.refregion.QFfileFilterByMaterCode;
import nc.ui.scmpub.query.refregion.QPsndocFilter;
import nc.ui.scmpub.query.refregion.QTransTypeFilter;
import nc.ui.scmpub.query.refregion.RefCommonFilterListener;
import nc.ui.so.pub.query.refregion.QBatchCodeFilter;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.querytemplate.QryTempletVOWithInfo;
import nc.vo.querytemplate.TemplateInfo;
import nc.vo.querytemplate.queryscheme.QuerySchemeVO;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.report.ordersummary.OrderSummaryViewVO;

import com.ufida.dataset.IContext;
import com.ufida.report.anareport.FreeReportContextKey;
import com.ufida.report.anareport.model.AbsAnaReportModel;

/**
 * 销售订单执行汇总报表订阅条件设置
 * 
 * @since 6.3
 * @version 2012-10-18 13:44:09
 * @author 梁吉明
 */
public class OrderSummarySubscribeAction extends DefaultSubscribeAction {

  private QueryConditionDLGDelegator m_delegator;

  private QueryConditionDLG m_queryDlg;

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
          OrderSummaryViewVO.PK_ORG
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
    // 交易类型参照
    QTransTypeFilter trantype =
        new QTransTypeFilter(this.m_delegator, SOBillType.Order.getCode());
    trantype.filter();

    RefCommonFilterListener filterutil =
        new RefCommonFilterListener(this.m_delegator, OrderSummaryViewVO.PK_ORG);
    filterutil.removeFilterMaps(new String[] {
      "so_saleorder_b.csendstockorgvid",
      "so_saleorder_b.cmffileid","so_saleorder_b.cmffileid.vskucode"
    });
    filterutil.addFilterMapsListeners();
    
    //过滤批次档案
    QBatchCodeFilter batch = new QBatchCodeFilter();
    batch.filter(this.m_delegator,"so_saleorder_b.vbatchcode");
    
    // 销售业务员：参照销售组织可见的人员（销售组织非空且唯一）,否则参照集团可见的物料进行录入
    QPsndocFilter employee =
        QPsndocFilter.createQPsndocFilterOfSO(this.m_delegator, SaleOrderHVO.CEMPLOYEEID);
    employee.setPk_orgCode(SaleOrderHVO.PK_ORG);
    employee.addEditorListener();

    // 销售部门: 参照销售组织可见的部门（销售组织非空且唯一）,否则参照集团可见的物料进行录入
    QDeptFilter dept =  QDeptFilter.createDeptFilterOfSO(this.m_delegator, SaleOrderHVO.CDEPTID);
    dept.setPk_orgCode(SaleOrderHVO.PK_ORG);
    dept.addEditorListener();
    
    
    new QFfileFilterByMaterCode(this.m_delegator, "so_saleorder_b.cmaterialid.code", "so_saleorder_b.cmffileid").addEditorListener();
    new QFfileFilterByMaterCode(this.m_delegator, "so_saleorder_b.cmaterialid.code", "so_saleorder_b.cmffileid.vskucode").addEditorListener();

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
