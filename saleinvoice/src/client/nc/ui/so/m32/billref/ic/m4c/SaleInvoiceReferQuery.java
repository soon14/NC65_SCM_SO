package nc.ui.so.m32.billref.ic.m4c;

import java.awt.Container;

import nc.itf.scmpub.reference.uap.setting.defaultdata.DefaultDataSettingAccessor;
import nc.ui.pubapp.billref.src.DefaultBillReferQuery;
import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.ui.pubapp.uif2app.query2.totalvo.MarAssistantDealer;
import nc.ui.scmpub.query.refregion.QFfileFilterByMaterCode;
import nc.ui.scmpub.query.refregion.QTransTypeFilter;
import nc.ui.scmpub.query.refregion.RefCommonFilterListener;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.querytemplate.TemplateInfo;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m32.entity.SaleInvoiceBVO;
import nc.vo.so.m32.entity.SaleInvoiceHVO;
import nc.vo.so.pub.SOItemKey;

public class SaleInvoiceReferQuery extends DefaultBillReferQuery {

  public SaleInvoiceReferQuery(Container c, TemplateInfo info) {
    super(c, info);
  }

  @Override
  protected void initQueryConditionDLG(QueryConditionDLGDelegator dlgDelegator) {
    // 设置个性化中心默认主组织
    this.setDefaultPk_org(dlgDelegator);
    // 初始参超过滤约束
    this.initFilterRef(dlgDelegator);
    // 处理子表冗余字段
    this.processBodyItem(dlgDelegator);

    // 主组织权限
    dlgDelegator.registerNeedPermissionOrgFieldCodes(new String[] {
      SaleInvoiceBVO.MAINMETAPATH + SaleInvoiceBVO.CSENDSTOCKORGID
    });

    dlgDelegator.addQueryCondVODealer(new MarAssistantDealer());

    dlgDelegator.setPowerEnable(true);
  }

  private void initFilterRef(QueryConditionDLGDelegator condDLGDelegator) {
    // 销售组织字段
    String saleorgkey = "csaleinvoicebid.csaleorgid";

    // 交易类型参照过滤
    QTransTypeFilter trantype =
        new QTransTypeFilter(condDLGDelegator, SOBillType.Invoice.getCode());
    trantype.filter();
    
    RefCommonFilterListener filterUtil = 
    	new RefCommonFilterListener(condDLGDelegator,SaleInvoiceHVO.PK_ORG);
    // 销售业务员
    // 销售部门
    filterUtil.addFilterMaps(new String[] {
    		"csaleinvoicebid.cemployeeid","csaleinvoicebid.cdeptid"
    }, saleorgkey);
    filterUtil.addFilterMapsListeners();
    
    new QFfileFilterByMaterCode(condDLGDelegator, "csaleinvoicebid.cmaterialid.code", "csaleinvoicebid.cmffileid").addEditorListener();
    new QFfileFilterByMaterCode(condDLGDelegator, "csaleinvoicebid.cmaterialid.code", "csaleinvoicebid.cmffileid.vskucode").addEditorListener();
    filterUtil.removeFilterMaps(new String[] {"csaleinvoicebid.cmffileid","csaleinvoicebid.cmffileid.vskucode"});
//    // 开票客户
//    QCustomerFilter invoicecust =
//        new QCustomerFilter(condDLGDelegator, SaleInvoiceHVO.CINVOICECUSTID);
//    invoicecust.setPk_orgCode(SaleInvoiceHVO.PK_ORG);
//    invoicecust.addEditorListener();
//
//    // 销售业务员
//    QPsndocFilter employee =
//        new QPsndocFilter(condDLGDelegator, "csaleinvoicebid.cemployeeid");
//    employee.setPk_orgCode(saleorgkey);
//    employee.addEditorListener();
//
//    // 销售部门
//    QDeptFilter dept =
//        new QDeptFilter(condDLGDelegator, "csaleinvoicebid.cdeptid");
//    dept.setPk_orgCode(saleorgkey);
//    dept.addEditorListener();

  }

  private void processBodyItem(QueryConditionDLGDelegator condDLGDelegator) {
    // 主组织
    condDLGDelegator.addRedundancyInfo(SaleInvoiceHVO.PK_ORG,
        "csaleinvoicebid.pk_org");
    // 单据日期
    condDLGDelegator.addRedundancyInfo(SaleInvoiceHVO.DBILLDATE,
        "csaleinvoicebid.dbilldate");
  }

  private void setDefaultPk_org(QueryConditionDLGDelegator condDLGDelegator) {
    String defaultOrg = null;
    try {
      defaultOrg = DefaultDataSettingAccessor.getDefaultSaleOrg();
    }
    catch (Exception ex) {
      ExceptionUtils.wrappException(ex);
    }
    if (!PubAppTool.isNull(defaultOrg)) {
      condDLGDelegator.setDefaultValue(SOItemKey.PK_ORG, defaultOrg);
    }
  }

}
