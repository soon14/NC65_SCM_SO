package nc.ui.so.m30.arrange.listener;

import nc.ui.pubapp.uif2app.query2.IQueryConditionDLGInitializer;
import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.ui.pubapp.uif2app.query2.totalvo.MarAssistantDealer;
import nc.ui.scmpub.query.refregion.QFfileFilterByMaterCode;
import nc.ui.scmpub.query.refregion.QTransTypeFilter;
import nc.ui.scmpub.query.refregion.RefCommonFilterListener;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;

/**
 * 销售订单查询对话框初始化
 * 
 * @since 6.0
 * @version 2011-1-10 下午04:38:40
 * @author 刘志伟
 */
public class QueryDLGInitializer implements IQueryConditionDLGInitializer {
  @Override
  public void initQueryConditionDLG(QueryConditionDLGDelegator dlgDelegator) {

    // 初始参超过滤约束
    this.initFilterRef(dlgDelegator);
    // 处理子表冗余字段
    this.processBodyItem(dlgDelegator);

    // 主组织权限
    dlgDelegator.registerNeedPermissionOrgFieldCodes(new String[] {
      SaleOrderHVO.PK_ORG
    });
    dlgDelegator.addQueryCondVODealer(new MarAssistantDealer());
  }

  private void processBodyItem(QueryConditionDLGDelegator dlgDelegator) {
    // 销售组织
    dlgDelegator.addRedundancyInfo(SaleOrderHVO.PK_ORG, SaleOrderBVO.METAPATH
        + "pk_org");
    // 单据日期
    dlgDelegator.addRedundancyInfo(SaleOrderHVO.DBILLDATE,
        SaleOrderBVO.METAPATH + "dbilldate");
  }

  private void initFilterRef(QueryConditionDLGDelegator dlgDelegator) {

    // 订单交易类型参照
    QTransTypeFilter trantype =
        new QTransTypeFilter(dlgDelegator, SOBillType.Order.getCode());
    trantype.filter();

    RefCommonFilterListener filterutil =
        new RefCommonFilterListener(dlgDelegator, SaleOrderHVO.PK_ORG);

    String sendstordocorgkey = "so_saleorder_b.csendstockorgid";
    // 仓库按照库存组织过滤
    filterutil.addFilterMaps(new String[] {
      "so_saleorder_b.csendstordocid"
    }, sendstordocorgkey);

    String[] removekeys =
        new String[] {
          sendstordocorgkey, "so_saleorder_b.csettleorgid",
          "so_saleorder_b.ctrafficorgid","so_saleorder_b.cmffileid","so_saleorder_b.cmffileid.vskucode"
        };
    // 移除部分字段参照过滤
    filterutil.removeFilterMaps(removekeys);

    filterutil.addFilterMapsListeners();
    
    new QFfileFilterByMaterCode(dlgDelegator, "so_saleorder_b.cmaterialid.code", "so_saleorder_b.cmffileid").addEditorListener();
    new QFfileFilterByMaterCode(dlgDelegator, "so_saleorder_b.cmaterialid.code", "so_saleorder_b.cmffileid.vskucode").addEditorListener();

    // // 客户
    // QCustomerFilter cust =
    // new QCustomerFilter(dlgDelegator, SaleOrderHVO.CCUSTOMERID);
    // cust.addEditorListener();
    //
    // // 物料编码
    // QMarterialFilter marteral =
    // new QMarterialFilter(dlgDelegator, SaleOrderHVO.PK_ORG,
    // "so_saleorder_b.cmaterialid");
    // marteral.addEditorListener();
    //
    // // 发货库存组织
    // QStockOrgFilter stockOrg =
    // new QStockOrgFilter(dlgDelegator, "so_saleorder_b.csendstockorgid");
    // stockOrg.filter();
    //
    // // 发货仓库：默认为空，发货库存组织非空且唯一，参照允许储存发货库存组织物料的仓库
    // QWareHouseFilter sendstordoc =
    // new QWareHouseFilter(dlgDelegator, "so_saleorder_b.csendstockorgid",
    // "so_saleorder_b.csendstordocid");
    // sendstordoc.addEditorListener();
  }
}
