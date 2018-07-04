package nc.ui.so.salequotation.billref.m30;

import java.awt.Container;
import nc.itf.scmpub.reference.uap.setting.defaultdata.DefaultDataSettingAccessor;
import nc.ui.pubapp.billref.src.DefaultBillReferQuery;
import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.ui.pubapp.uif2app.query2.totalvo.MarAssistantDealer;
import nc.ui.scmpub.query.refregion.QCustomerFilter;
import nc.ui.scmpub.query.refregion.QDeptFilter;
import nc.ui.scmpub.query.refregion.QMarSaleClassFilter;
import nc.ui.scmpub.query.refregion.QMarbasclassFilter;
import nc.ui.scmpub.query.refregion.QMarterialFilter;
import nc.ui.scmpub.query.refregion.QPsndocFilter;
import nc.ui.scmpub.query.refregion.QTransTypeFilter;
import nc.ui.scmpub.query.refregion.RefCommonFilterListener;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.querytemplate.TemplateInfo;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.pub.SOItemKey;
import nc.vo.so.salequotation.entity.SalequotationBVO;
import nc.vo.so.salequotation.entity.SalequotationHVO;

public class M4310TOM30BillReferQuery extends DefaultBillReferQuery {

  public M4310TOM30BillReferQuery(Container c, TemplateInfo info) {
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
      SOItemKey.PK_ORG
    });

    dlgDelegator.addQueryCondVODealer(new MarAssistantDealer());

    dlgDelegator.setPowerEnable(true);
  }

  private void initFilterRef(QueryConditionDLGDelegator condDLGDelegator) {
    // 交易类型参照
    QTransTypeFilter trantype =
        new QTransTypeFilter(condDLGDelegator,
            SOBillType.SaleQuotation.getCode());
    trantype.filter();
    
    RefCommonFilterListener filterUtil = 
    	new RefCommonFilterListener(condDLGDelegator,SalequotationHVO.PK_ORG);
    filterUtil.addFilterMapsListeners();


    //过滤销售部门
    QDeptFilter deptFilter = QDeptFilter.createDeptFilterOfSO(
    condDLGDelegator, SalequotationHVO.PK_DEPT);
    deptFilter.setPk_orgCode(SalequotationHVO.PK_ORG);
    deptFilter.addEditorListener();

    // 过滤业务员
    QPsndocFilter psnFilter = QPsndocFilter.createQPsndocFilterOfSO(
    condDLGDelegator, SalequotationHVO.CEMPLOYEEID);
    psnFilter.setPk_orgCode(SalequotationHVO.PK_ORG);
    psnFilter.addEditorListener();
//
//    // 销售部门
//    QDeptFilter dept =
//        new QDeptFilter(condDLGDelegator, SalequotationHVO.PK_DEPT);
//    dept.setPk_orgCode(SalequotationHVO.PK_ORG);
//    dept.addEditorListener();
//

//    // 销售业务员
//    QPsndocFilter employee =
//        new QPsndocFilter(condDLGDelegator, SalequotationHVO.CEMPLOYEEID);
//    employee.setPk_orgCode(SalequotationHVO.PK_ORG);
//    employee.addEditorListener();
  }

  private void processBodyItem(QueryConditionDLGDelegator condDLGDelegator) {
    // 销售组织
    condDLGDelegator.addRedundancyInfo(SalequotationHVO.PK_ORG,
        SalequotationBVO.METAPATH + "pk_org");
    // 单据日期
    /*    condDLGDelegator.addRedundancyInfo(SalequotationHVO.DQUOTEDATE,
            SalequotationBVO.METAPATH + "dbilldate");*/
  }

  private void setDefaultPk_org(QueryConditionDLGDelegator condDLGDelegator) {
    String defaultOrg = null;
    try {
      defaultOrg = DefaultDataSettingAccessor.getDefaultOrgUnit();
    }
    catch (Exception ex) {
      ExceptionUtils.wrappException(ex);
    }
    if (defaultOrg != null && defaultOrg.trim().length() > 0) {
      condDLGDelegator.setDefaultValue("so_salequotation.pk_org", defaultOrg);
    }
  }

}
