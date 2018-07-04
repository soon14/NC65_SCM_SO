package nc.ui.so.m38.billref.m30;

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
import nc.ui.scmpub.query.refregion.QStockOrgFilter;
import nc.ui.scmpub.query.refregion.QTransTypeFilter;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.querytemplate.TemplateInfo;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m38.entity.PreOrderHVO;
import nc.vo.so.pub.SOItemKey;

public class M30Ref38BillReferQuery extends DefaultBillReferQuery {

  public M30Ref38BillReferQuery(Container c, TemplateInfo info) {
    super(c, info);
  }

  @Override
  public void initQueryConditionDLG(QueryConditionDLGDelegator dlgDelegator) {
    this.setDefaultPk_org(dlgDelegator);
    this.initFilterRef(dlgDelegator);
    this.initBodyRedundancyItem(dlgDelegator);

    // 主组织权限
    dlgDelegator.registerNeedPermissionOrgFieldCodes(new String[] {
      SOItemKey.PK_ORG
    });

    dlgDelegator.addQueryCondVODealer(new MarAssistantDealer());

    dlgDelegator.setPowerEnable(true);
  }

  private void initBodyRedundancyItem(QueryConditionDLGDelegator dlgDelegator) {
    // 销售组织
    dlgDelegator.addRedundancyInfo(PreOrderHVO.PK_ORG, "so_preorder_b.pk_org");
    // 单据日期
    dlgDelegator.addRedundancyInfo(PreOrderHVO.DBILLDATE,
        "so_preorder_b.dbilldate");
  }

  private void initFilterRef(QueryConditionDLGDelegator dlgDelegator) {
    // 预订单交易类型参照
    QTransTypeFilter trantype =
        new QTransTypeFilter(dlgDelegator, SOBillType.PreOrder.getCode());
    trantype.filter();

    // 客户
    QCustomerFilter invoicecust =
        new QCustomerFilter(dlgDelegator, PreOrderHVO.CCUSTOMERID);
    invoicecust.addEditorListener();

    // 销售部门
    QDeptFilter dept = new QDeptFilter(dlgDelegator, PreOrderHVO.CDEPTID);
    dept.setPk_orgCode(PreOrderHVO.PK_ORG);
    dept.addEditorListener();

    // 物料基本分类
    QMarbasclassFilter marclass =
        new QMarbasclassFilter(dlgDelegator,
            "so_preorder_b.cmaterialid.pk_marbasclass");
    marclass.addEditorListener();

    // 物料销售分类
    QMarSaleClassFilter marSaleClass =
        new QMarSaleClassFilter(dlgDelegator,
            "so_preorder_b.cmaterialvid.materialsale.pk_marsaleclass");
    marSaleClass.setPk_orgCode(PreOrderHVO.PK_ORG);
    marSaleClass.addEditorListener();

    // 物料编码
    QMarterialFilter marteral =
        new QMarterialFilter(dlgDelegator, PreOrderHVO.PK_ORG,
            "so_preorder_b.cmaterialid.code");
    marteral.addEditorListener();

    // 销售业务员
    QPsndocFilter employee =
        new QPsndocFilter(dlgDelegator, PreOrderHVO.CEMPLOYEEID);
    employee.setPk_orgCode(PreOrderHVO.PK_ORG);
    employee.addEditorListener();

    // 发货库存组织
    QStockOrgFilter stockOrg =
        new QStockOrgFilter(dlgDelegator, "so_preorder_b.csendstockorgid");
    stockOrg.filter();
  }

  private void setDefaultPk_org(QueryConditionDLGDelegator dlgDelegator) {
    String defaultOrg = null;
    try {
      defaultOrg = DefaultDataSettingAccessor.getDefaultSaleOrg();
    }
    catch (Exception ex) {
      ExceptionUtils.wrappException(ex);
    }
    if (defaultOrg != null && defaultOrg.trim().length() > 0) {
      dlgDelegator.setDefaultValue(SaleOrderHVO.PK_ORG, defaultOrg);
    }
  }
}
