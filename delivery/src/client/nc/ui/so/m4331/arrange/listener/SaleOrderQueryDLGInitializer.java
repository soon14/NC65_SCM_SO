package nc.ui.so.m4331.arrange.listener;

import nc.vo.querytemplate.TemplateInfo;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.pub.SOFunc;

import nc.ui.pubapp.uif2app.query2.DefaultQueryConditionDLG;
import nc.ui.pubapp.uif2app.query2.IQueryConditionDLGInitializer;
import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.ui.pubapp.uif2app.query2.totalvo.MarAssistantDealer;
import nc.ui.scmpub.query.refregion.QCustomerFilter;
import nc.ui.scmpub.query.refregion.QDeptFilter;
import nc.ui.scmpub.query.refregion.QFfileFilterByMaterCode;
import nc.ui.scmpub.query.refregion.QMarSaleClassFilter;
import nc.ui.scmpub.query.refregion.QMarbasclassFilter;
import nc.ui.scmpub.query.refregion.QMarterialoidFilter;
import nc.ui.scmpub.query.refregion.QPsndocFilter;
import nc.ui.scmpub.query.refregion.QStockOrgFilter;
import nc.ui.scmpub.query.refregion.QTransTypeFilter;
import nc.ui.scmpub.query.refregion.QWareHouseFilter;

/**
 * 销售订单查询对话框初始化
 * 
 * @since 6.0
 * @version 2011-1-10 下午04:38:40
 * @author 刘志伟
 */
public class SaleOrderQueryDLGInitializer implements
    IQueryConditionDLGInitializer {

  @Override
  public void initQueryConditionDLG(QueryConditionDLGDelegator dlgDelegator) {
    // 初始参超过滤约束
    this.initFilterRef(dlgDelegator);
    // 处理子表冗余字段
    this.processBodyItem(dlgDelegator);

    // 主组织权限
    TemplateInfo tempalteinfo =
        ((DefaultQueryConditionDLG) dlgDelegator.getQueryConditionDLG())
            .getTempInfo();
    tempalteinfo.setFunNode(SOFunc.N40060401.getCode());
    dlgDelegator.registerNeedPermissionOrgFieldCodes(new String[] {
      SaleOrderBVO.METAPATH + SaleOrderBVO.CTRAFFICORGID
    });
    tempalteinfo.setFunNode(SOFunc.N40060301.getCode());

    dlgDelegator.addQueryCondVODealer(new MarAssistantDealer());

    dlgDelegator.setPowerEnable(true);
  }

  private void initFilterRef(QueryConditionDLGDelegator condDLGDelegator) {
    // 下游单据主组织权限过滤
    // String[] pks = condDLGDelegator.getLogincontext();

    // 订单交易类型参照
    QTransTypeFilter trantype =
        new QTransTypeFilter(condDLGDelegator, SOBillType.Order.getCode());
    trantype.filter();

    // 客户
    QCustomerFilter cust =
        new QCustomerFilter(condDLGDelegator, SaleOrderHVO.CCUSTOMERID);
    cust.setPk_orgCode(SaleOrderHVO.PK_ORG);
    cust.addEditorListener();

    // 销售部门
    QDeptFilter dept = new QDeptFilter(condDLGDelegator, SaleOrderHVO.CDEPTID);
    dept.setPk_orgCode(SaleOrderHVO.PK_ORG);
    dept.addEditorListener();

    // 物料销售分类
    QMarSaleClassFilter marSaleClass =
        new QMarSaleClassFilter(condDLGDelegator,
            "so_saleorder_b.cmaterialvid.materialsale.pk_marsaleclass");
    marSaleClass.setPk_orgCode(SaleOrderHVO.PK_ORG);
    marSaleClass.addEditorListener();

    // 物料分类
    QMarbasclassFilter marbasclass =
        new QMarbasclassFilter(condDLGDelegator, SaleOrderHVO.PK_ORG,
            "so_saleorder_b.cmaterialid.pk_marbasclass");
    marbasclass.setPk_orgCode(SaleOrderHVO.PK_ORG);
    marbasclass.addEditorListener();

    // 物料编码
    QMarterialoidFilter marteral =
        new QMarterialoidFilter(condDLGDelegator, SaleOrderHVO.PK_ORG,
            "so_saleorder_b.cmaterialid");
    marteral.addEditorListener();
    
    // 物料编码
    QMarterialoidFilter marteralcode =
        new QMarterialoidFilter(condDLGDelegator, SaleOrderHVO.PK_ORG,
            "so_saleorder_b.cmaterialid.code");
    marteralcode.addEditorListener();

    // 销售业务员
    QPsndocFilter employee =
        new QPsndocFilter(condDLGDelegator, SaleOrderHVO.CEMPLOYEEID);
    employee.setPk_orgCode(SaleOrderHVO.PK_ORG);
    employee.addEditorListener();

    // 发货库存组织
    QStockOrgFilter stockOrg =
        new QStockOrgFilter(condDLGDelegator, "so_saleorder_b.csendstockorgid");
    stockOrg.filter();

    // 发货仓库：默认为空，发货库存组织非空且唯一，参照允许储存发货库存组织物料的仓库
    QWareHouseFilter sendstordoc =
        new QWareHouseFilter(condDLGDelegator,
            "so_saleorder_b.csendstockorgid", "so_saleorder_b.csendstordocid");
    sendstordoc.addEditorListener();
    
    new QFfileFilterByMaterCode(condDLGDelegator, "so_saleorder_b.cmaterialid.code", "so_saleorder_b.cmffileid").addEditorListener();
    new QFfileFilterByMaterCode(condDLGDelegator, "so_saleorder_b.cmaterialid.code", "so_saleorder_b.cmffileid.vskucode").addEditorListener();

  }

  private void processBodyItem(QueryConditionDLGDelegator condDLGDelegator) {
    // 销售组织
    condDLGDelegator.addRedundancyInfo(SaleOrderHVO.PK_ORG,
        SaleOrderBVO.METAPATH + "pk_org");
    // 单据日期
    condDLGDelegator.addRedundancyInfo(SaleOrderHVO.DBILLDATE,
        SaleOrderBVO.METAPATH + "dbilldate");
  }
}
