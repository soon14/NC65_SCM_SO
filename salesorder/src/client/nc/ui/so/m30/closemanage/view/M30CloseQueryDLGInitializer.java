package nc.ui.so.m30.closemanage.view;

import nc.ui.pubapp.uif2app.query2.IQueryConditionDLGInitializer;
import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.ui.pubapp.uif2app.query2.totalvo.MarAssistantDealer;
import nc.ui.scmpub.query.refregion.QCustomerFilter;
import nc.ui.scmpub.query.refregion.QDeptFilter;
import nc.ui.scmpub.query.refregion.QFfileFilterByMaterCode;
import nc.ui.scmpub.query.refregion.QFreeCustFilter;
import nc.ui.scmpub.query.refregion.QMarterialFilter;
import nc.ui.scmpub.query.refregion.QPsndocFilter;
import nc.ui.scmpub.query.refregion.QStockOrgFilter;
import nc.ui.scmpub.query.refregion.QTransTypeFilter;
import nc.ui.scmpub.query.refregion.RefCommonFilterListener;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.pub.SOItemKey;
import nc.vo.so.salequotation.entity.SalequotationHVO;

public class M30CloseQueryDLGInitializer implements
    IQueryConditionDLGInitializer {

  @Override
  public void initQueryConditionDLG(QueryConditionDLGDelegator condDLGDelegator) {
    // 初始参超过滤约束
    this.initFilterRef(condDLGDelegator);
    // 处理子表冗余字段
    this.processBodyItem(condDLGDelegator);

    // 主组织权限
    condDLGDelegator.registerNeedPermissionOrgFieldCodes(new String[] {
      SOItemKey.PK_ORG
    });
    condDLGDelegator.addQueryCondVODealer(new MarAssistantDealer());
  }

  private void initFilterRef(QueryConditionDLGDelegator condDLGDelegator) {
    // 订单交易类型参照
    QTransTypeFilter trantype =
        new QTransTypeFilter(condDLGDelegator, SOBillType.Order.getCode());
    trantype.filter();
    
    RefCommonFilterListener filterutil =
        new RefCommonFilterListener(condDLGDelegator, SaleOrderHVO.PK_ORG);

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
    filterutil.removeFilterMaps(new String[] {"so_saleorder_b.cmffileid","so_saleorder_b.cmffileid.vskucode"});
//    // 客户
//    QCustomerFilter cust =
//        new QCustomerFilter(condDLGDelegator, SaleOrderHVO.CCUSTOMERID);
//    cust.addEditorListener();
//
//    // 物料编码
//    QMarterialFilter marteral =
//        new QMarterialFilter(condDLGDelegator, SaleOrderHVO.PK_ORG,
//            "so_saleorder_b.cmaterialvid");
//    marteral.addEditorListener();
//
//    // 发货库存组织
//    QStockOrgFilter stockOrg =
//        new QStockOrgFilter(condDLGDelegator, "so_saleorder_b.csendstockorgid");
//    stockOrg.filter();
//
//    // 散户
//    QFreeCustFilter freecust =
//        new QFreeCustFilter(condDLGDelegator, SaleOrderHVO.CFREECUSTID);
//    freecust.setPk_orgCode(SaleOrderHVO.PK_ORG);
//    freecust.addEditorListener();
//
//    // 销售业务员：参照销售组织可见的人员（销售组织非空且唯一）,否则参照集团可见的物料进行录入
//    QPsndocFilter employee =
//        new QPsndocFilter(condDLGDelegator, SaleOrderHVO.CEMPLOYEEID);
//    employee.setPk_orgCode(SaleOrderHVO.PK_ORG);
//    employee.addEditorListener();
//
//    // 销售部门: 参照销售组织可见的部门（销售组织非空且唯一）,否则参照集团可见的物料进行录入
//    QDeptFilter dept = new QDeptFilter(condDLGDelegator, SaleOrderHVO.CDEPTID);
//    dept.setPk_orgCode(SaleOrderHVO.PK_ORG);
//    dept.addEditorListener();

  }

  private void processBodyItem(QueryConditionDLGDelegator condDLGDelegator) {
    // 销售组织
    condDLGDelegator.addRedundancyInfo(SaleOrderHVO.PK_ORG,
        "so_saleorder_b.pk_org");
    // 单据日期
    condDLGDelegator.addRedundancyInfo(SaleOrderHVO.DBILLDATE,
        "so_saleorder_b.dbilldate");
  }

}
