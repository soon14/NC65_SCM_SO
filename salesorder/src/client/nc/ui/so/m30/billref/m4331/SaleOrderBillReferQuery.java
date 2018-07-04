package nc.ui.so.m30.billref.m4331;

import java.awt.Container;

import nc.ui.pubapp.billref.src.DefaultBillReferQuery;
import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.ui.pubapp.uif2app.query2.totalvo.MarAssistantDealer;
import nc.ui.scmpub.query.refregion.QDeptFilter;
import nc.ui.scmpub.query.refregion.QFfileFilterByMaterCode;
import nc.ui.scmpub.query.refregion.QPsndocFilter;
import nc.ui.scmpub.query.refregion.QTransTypeFilter;
import nc.ui.scmpub.query.refregion.RefCommonFilterListener;
import nc.vo.querytemplate.TemplateInfo;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;

public class SaleOrderBillReferQuery extends DefaultBillReferQuery {

  public SaleOrderBillReferQuery(Container c, TemplateInfo info) {
    super(c, info);
  }

  @Override
  protected void initQueryConditionDLG(QueryConditionDLGDelegator dlgDelegator) {
    // 初始参超过滤约束
    this.initFilterRef(dlgDelegator);
    // 处理子表冗余字段
    this.processBodyItem(dlgDelegator);

    // 数据权限
    dlgDelegator.setPowerEnable(true);

    // 主组织权限
    dlgDelegator.registerNeedPermissionOrgFieldCodes(new String[] {
      SaleOrderBVO.METAPATH + SaleOrderBVO.CTRAFFICORGID
    });

    dlgDelegator.addQueryCondVODealer(new MarAssistantDealer());
  }

  private void initFilterRef(QueryConditionDLGDelegator condDLGDelegator) {

    // 订单交易类型参照
    QTransTypeFilter trantype =
        new QTransTypeFilter(condDLGDelegator, SOBillType.Order.getCode());
    trantype.filter();

    RefCommonFilterListener filterutil =
        new RefCommonFilterListener(condDLGDelegator, SaleOrderHVO.PK_ORG);

    String sendstordocorgkey = "so_saleorder_b.csendstockorgid";
    // 仓库按照库存组织过滤
    filterutil.addFilterMaps(new String[] {
      "so_saleorder_b.csendstordocid"
    }, sendstordocorgkey);

    String[] removekeys =
        new String[] {
          sendstordocorgkey, "so_saleorder_b.csettleorgid",
          "so_saleorder_b.ctrafficorgid",
          "so_saleorder_b.cmffileid","so_saleorder_b.cmffileid.vskucode"
        };
    // 移除部分字段参照过滤
    filterutil.removeFilterMaps(removekeys);

    filterutil.addFilterMapsListeners();
    
    
    //过滤销售部门
    QDeptFilter deptFilter = QDeptFilter.createDeptFilterOfSO(
    condDLGDelegator, SaleOrderHVO.CDEPTID);
    deptFilter.setPk_orgCode(SaleOrderHVO.PK_ORG);
    deptFilter.addEditorListener();

    // 过滤业务员
    QPsndocFilter psnFilter = QPsndocFilter.createQPsndocFilterOfSO(
    condDLGDelegator, SaleOrderHVO.CEMPLOYEEID);
    psnFilter.setPk_orgCode(SaleOrderHVO.PK_ORG);
    psnFilter.addEditorListener();  
    
    new QFfileFilterByMaterCode(condDLGDelegator, "so_saleorder_b.cmaterialid.code", "so_saleorder_b.cmffileid").addEditorListener();
    new QFfileFilterByMaterCode(condDLGDelegator, "so_saleorder_b.cmaterialid.code", "so_saleorder_b.cmffileid.vskucode").addEditorListener();

    // // 客户
    // QCustomerFilter cust =
    // new QCustomerFilter(condDLGDelegator, SaleOrderHVO.CCUSTOMERID);
    // cust.setPk_orgCode(SaleOrderHVO.PK_ORG);
    // cust.addEditorListener();
    //
    // // 客户基本分类
    // QCustBaseClassFilter custbas =
    // new QCustBaseClassFilter(condDLGDelegator, "ccustomerid.pk_custclass");
    // custbas.setPk_orgCode(SaleOrderHVO.PK_ORG);
    // custbas.addEditorListener();
    //
    // // 客户销售分类
    // QCustSaleClassFilter custsale =
    // new QCustSaleClassFilter(condDLGDelegator,
    // "ccustomerid.sales.pk_custsaleclass");
    // custsale.setPk_orgCode(SaleOrderHVO.PK_ORG);
    // custsale.addEditorListener();
    //
    // // 销售部门
    // QDeptFilter dept = new QDeptFilter(condDLGDelegator,
    // SaleOrderHVO.CDEPTID);
    // dept.setPk_orgCode(SaleOrderHVO.PK_ORG);
    // dept.addEditorListener();
    //
    // // 物料基本分类
    // QMarbasclassFilter marclass =
    // new QMarbasclassFilter(condDLGDelegator,
    // "so_saleorder_b.cmaterialid.pk_marbasclass");
    // marclass.setPk_orgCode(SaleOrderHVO.PK_ORG);
    // marclass.addEditorListener();
    //
    // // 物料销售分类
    // QMarSaleClassFilter marSaleClass =
    // new QMarSaleClassFilter(condDLGDelegator,
    // "so_saleorder_b.cmaterialvid.materialsale.pk_marsaleclass");
    // marSaleClass.setPk_orgCode(SaleOrderHVO.PK_ORG);
    // marSaleClass.addEditorListener();
    //
    // // 物料编码
    // QMarterialFilter marteral =
    // new QMarterialFilter(condDLGDelegator, SaleOrderHVO.PK_ORG,
    // "so_saleorder_b.cmaterialid");
    // marteral.addEditorListener();
    //
    // // 销售业务员
    // QPsndocFilter employee =
    // new QPsndocFilter(condDLGDelegator, SaleOrderHVO.CEMPLOYEEID);
    // employee.setPk_orgCode(SaleOrderHVO.PK_ORG);
    // employee.addEditorListener();
    //
    // // 发货库存组织
    // QStockOrgFilter stockOrg =
    // new QStockOrgFilter(condDLGDelegator, "so_saleorder_b.csendstockorgid");
    // stockOrg.filter();
    //
    // // 发货仓库：默认为空，发货库存组织非空且唯一，参照允许储存发货库存组织物料的仓库
    // QWareHouseFilter sendstordoc =
    // new QWareHouseFilter(condDLGDelegator,
    // "so_saleorder_b.csendstockorgid", "so_saleorder_b.csendstordocid");
    // sendstordoc.addEditorListener();
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
