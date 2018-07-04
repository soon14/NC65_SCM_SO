package nc.ui.so.m30.billref.withdraw;

import java.awt.Container;

import nc.itf.scmpub.reference.uap.setting.defaultdata.DefaultDataSettingAccessor;
import nc.ui.pubapp.billref.src.DefaultBillReferQuery;
import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.ui.pubapp.uif2app.query2.totalvo.MarAssistantDealer;
import nc.ui.scmpub.query.refregion.QCustomerFilter;
import nc.ui.scmpub.query.refregion.QDeptFilter;
import nc.ui.scmpub.query.refregion.QFfileFilterByMaterCode;
import nc.ui.scmpub.query.refregion.QFreeCustFilter;
import nc.ui.scmpub.query.refregion.QMarSaleClassFilter;
import nc.ui.scmpub.query.refregion.QMarbasclassFilter;
import nc.ui.scmpub.query.refregion.QMarterialFilter;
import nc.ui.scmpub.query.refregion.QPaytermFilter;
import nc.ui.scmpub.query.refregion.QPsndocFilter;
import nc.ui.scmpub.query.refregion.QStockOrgFilter;
import nc.ui.scmpub.query.refregion.QTransTypeFilter;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.querytemplate.TemplateInfo;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.pub.SOItemKey;

public class SaleOrderBillReferQuery extends DefaultBillReferQuery {

  public SaleOrderBillReferQuery(Container c, TemplateInfo info) {
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

    // 数据权限
    dlgDelegator.setPowerEnable(true);

    // 主组织权限
    dlgDelegator.registerNeedPermissionOrgFieldCodes(new String[] {
      SOItemKey.PK_ORG
    });

    dlgDelegator.addQueryCondVODealer(new MarAssistantDealer());
  }

  private void initFilterRef(QueryConditionDLGDelegator condDLGDelegator) {
    // 订单交易类型参照
    QTransTypeFilter trantype =
        new QTransTypeFilter(condDLGDelegator, SOBillType.Order.getCode());
    trantype.filter();

    // 客户编码
    QCustomerFilter iordercustcode =
        new QCustomerFilter(condDLGDelegator, "ccustomerid.code");
    iordercustcode.setPk_orgCode(SaleOrderHVO.PK_ORG);
    iordercustcode.addEditorListener();

    // 客户名称
    QCustomerFilter iordercustname =
        new QCustomerFilter(condDLGDelegator, "ccustomerid.name");
    iordercustname.setPk_orgCode(SaleOrderHVO.PK_ORG);
    iordercustname.addEditorListener();

    // 散户
    QFreeCustFilter freecust =
        new QFreeCustFilter(condDLGDelegator, SaleOrderHVO.CFREECUSTID);
    freecust.setPk_orgCode(SaleOrderHVO.PK_ORG);
    freecust.addEditorListener();

    // 开票客户
    QCustomerFilter invoicecust =
        new QCustomerFilter(condDLGDelegator, SaleOrderHVO.CINVOICECUSTID);
    invoicecust.setPk_orgCode(SaleOrderHVO.PK_ORG);
    invoicecust.addEditorListener();

    // 收货客户过滤
    QCustomerFilter creceivecustid =
        new QCustomerFilter(condDLGDelegator, "so_saleorder_b.creceivecustid");
    creceivecustid.setPk_orgCode(SaleOrderHVO.PK_ORG);
    creceivecustid.addEditorListener();

    // 物料编码
    QMarterialFilter marteralcode =
        new QMarterialFilter(condDLGDelegator, SaleOrderHVO.PK_ORG,
            "so_saleorder_b.cmaterialid.code");
    marteralcode.addEditorListener();

    // 物料名称
    QMarterialFilter marteralname =
        new QMarterialFilter(condDLGDelegator, SaleOrderHVO.PK_ORG,
            "so_saleorder_b.cmaterialid.name");
    marteralname.addEditorListener();

    // 物料基本分类
    QMarbasclassFilter marbasclass =
        new QMarbasclassFilter(condDLGDelegator,
            "so_saleorder_b.cmaterialid.pk_marbasclass");
    marbasclass.setPk_orgCode(SaleOrderHVO.PK_ORG);
    marbasclass.addEditorListener();

    // 物料销售分类
    QMarSaleClassFilter marSaleClass =
        new QMarSaleClassFilter(condDLGDelegator,
            "so_saleorder_b.cmaterialvid.materialsale.pk_marsaleclass");
    marSaleClass.setPk_orgCode(SaleOrderHVO.PK_ORG);
    marSaleClass.addEditorListener();

    // 过滤销售部门
    QDeptFilter deptFilter =
        QDeptFilter
            .createDeptFilterOfSO(condDLGDelegator, SaleOrderHVO.CDEPTID);
    deptFilter.setPk_orgCode(SaleOrderHVO.PK_ORG);
    deptFilter.addEditorListener();

    // 过滤业务员
    QPsndocFilter psnFilter =
        QPsndocFilter.createQPsndocFilterOfSO(condDLGDelegator,
            SaleOrderHVO.CEMPLOYEEID);
    psnFilter.setPk_orgCode(SaleOrderHVO.PK_ORG);
    psnFilter.addEditorListener();

    // 发货库存组织
    QStockOrgFilter stockOrg =
        new QStockOrgFilter(condDLGDelegator, "so_saleorder_b.csendstockorgid");
    stockOrg.filter();

    // 付款协议
    QPaytermFilter paytermfilter =
        new QPaytermFilter(condDLGDelegator, SaleOrderHVO.CPAYTERMID);
    paytermfilter.setPk_orgCode(SaleOrderHVO.PK_ORG);
    paytermfilter.addEditorListener();

    new QFfileFilterByMaterCode(condDLGDelegator,
        "so_saleorder_b.cmaterialid.code", "so_saleorder_b.cmffileid")
        .addEditorListener();
    new QFfileFilterByMaterCode(condDLGDelegator,
        "so_saleorder_b.cmaterialid.code", "so_saleorder_b.cmffileid.vskucode")
        .addEditorListener();
  }

  private void processBodyItem(QueryConditionDLGDelegator condDLGDelegator) {
    // 销售组织
    condDLGDelegator.addRedundancyInfo(SaleOrderHVO.PK_ORG,
        SaleOrderBVO.METAPATH + "pk_org");
    // 单据日期
    condDLGDelegator.addRedundancyInfo(SaleOrderHVO.DBILLDATE,
        SaleOrderBVO.METAPATH + "dbilldate");
  }

  private void setDefaultPk_org(QueryConditionDLGDelegator condDLGDelegator) {
    String defaultOrg = null;
    try {
      defaultOrg = DefaultDataSettingAccessor.getDefaultSaleOrg();
    }
    catch (Exception ex) {
      ExceptionUtils.wrappException(ex);
    }
    if (defaultOrg != null && defaultOrg.trim().length() > 0) {
      condDLGDelegator.setDefaultValue(SaleOrderHVO.PK_ORG, defaultOrg);
    }
  }

}
