package nc.ui.so.m30.billref.m21.coop;

import java.awt.Container;

import nc.ui.pubapp.billref.src.DefaultBillReferQuery;
import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.ui.pubapp.uif2app.query2.totalvo.MarAssistantDealer;
import nc.ui.scmpub.query.refregion.QDeptFilter;
import nc.ui.scmpub.query.refregion.QFfileFilterByMaterCode;
import nc.ui.scmpub.query.refregion.QPsndocFilter;
import nc.ui.scmpub.query.refregion.QPurchaseOrgFilter;
import nc.ui.scmpub.query.refregion.QTransTypeFilter;
import nc.vo.querytemplate.TemplateInfo;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m30.entity.SaleOrderHVO;

public class CoopBillReferQuery extends DefaultBillReferQuery {

  /**
   * @param c
   * @param info
   */
  public CoopBillReferQuery(Container c, TemplateInfo info) {
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
      "pk_puorg"
    });

    dlgDelegator.addQueryCondVODealer(new MarAssistantDealer());
  }

  private void initFilterRef(QueryConditionDLGDelegator condDLGDelegator) {
    // 采购组织过滤 根据协同设置
    QPurchaseOrgFilter puorg =
        new QPurchaseOrgFilter(condDLGDelegator, "pk_puorg");
    puorg.filter();
    // 交易类型参照过滤
    QTransTypeFilter trantype =
        new QTransTypeFilter(condDLGDelegator, SOBillType.Order.getCode());
    trantype.filter();

    // // 物料编码:参照销售组织可见的物料（销售组织非空且唯一）,否则参照集团可见的物料进行录入
    // QMarterialFilter marteral =
    // new QMarterialFilter(condDLGDelegator, SaleOrderHVO.PK_ORG,
    // "so_saleorder_b.cmaterialid");
    // marteral.addEditorListener();
    //
    // // 物料基本分类
    // QMarbasclassFilter marbasClass =
    // new QMarbasclassFilter(condDLGDelegator,
    // "so_saleorder_b.cmaterialid.pk_marbasclass");
    // marbasClass.setPk_orgCode(SaleOrderHVO.PK_ORG);
    // marbasClass.addEditorListener();
    // // 收货客户
    // QCustomerFilter invoiceCust =
    // new QCustomerFilter(condDLGDelegator, "so_saleorder_b.creceivecustid");
    // invoiceCust.setPk_orgCode(SaleOrderHVO.PK_ORG);
    // invoiceCust.addEditorListener();

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

    new QFfileFilterByMaterCode(condDLGDelegator,
        "so_saleorder_b.cmaterialid.code", "so_saleorder_b.cmffileid")
        .addEditorListener();
    new QFfileFilterByMaterCode(condDLGDelegator,
        "so_saleorder_b.cmaterialid.code", "so_saleorder_b.cmffileid.vskucode")
        .addEditorListener();

  }

  private void processBodyItem(QueryConditionDLGDelegator condDLGDelegator) {
    // 主组织
    condDLGDelegator.addRedundancyInfo(SaleOrderHVO.PK_ORG,
        "so_saleorder_b.pk_org");
    // 单据日期
    condDLGDelegator.addRedundancyInfo(SaleOrderHVO.DBILLDATE,
        "so_saleorder_b.dbilldate");
  }
}
