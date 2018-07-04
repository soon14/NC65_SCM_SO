package nc.ui.so.m4331.arrange.listener;

import nc.ui.pubapp.uif2app.query2.DefaultQueryConditionDLG;
import nc.ui.pubapp.uif2app.query2.IQueryConditionDLGInitializer;
import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.ui.scmpub.query.refregion.QCustomerFilter;
import nc.ui.scmpub.query.refregion.QMarbasclassFilter;
import nc.ui.scmpub.query.refregion.QMarterialoidFilter;
import nc.ui.scmpub.query.refregion.QTransTypeFilter;
import nc.ui.scmpub.query.refregion.QWareHouseFilter;
import nc.vo.querytemplate.TemplateInfo;
import nc.vo.scmpub.res.billtype.TOBillType;
import nc.vo.so.pub.SOFunc;
import nc.vo.to.m5x.entity.BillHeaderVO;
import nc.vo.to.m5x.entity.BillItemVO;

public class TransOrderQueryDLGInitializer implements
    IQueryConditionDLGInitializer {

  @Override
  public void initQueryConditionDLG(QueryConditionDLGDelegator condDLGDelegator) {
    // 参照调拨订单申请交易类型
    new QTransTypeFilter(condDLGDelegator, TOBillType.TransOrder.getCode())
        .filter();

    // 根据库存组织对物料进行过滤(动态的)
    QMarterialoidFilter marterialFilter =
        new QMarterialoidFilter(condDLGDelegator, BillHeaderVO.CTOUTSTOCKORGID,
            "bodyfk.cinventoryid");
    marterialFilter.addEditorListener();
    // 根据库存组织对物料进行过滤(动态的)
    QMarterialoidFilter marterialcodeFilter =
        new QMarterialoidFilter(condDLGDelegator, BillHeaderVO.CTOUTSTOCKORGID,
            "bodyfk.cinventoryid.code");
    marterialcodeFilter.addEditorListener();
    QMarbasclassFilter marbasclassFilter =
        new QMarbasclassFilter(condDLGDelegator,
            "bodyfk.cinventoryid.pk_marbasclass");
    marbasclassFilter.setPk_orgCode(BillHeaderVO.CTOUTSTOCKORGID);
    marbasclassFilter.addEditorListener();

    // 根据库存组织对仓库进行过滤
    QWareHouseFilter houseFilter1 =
        new QWareHouseFilter(condDLGDelegator, BillHeaderVO.PK_ORG,
            "bodyfk.coutstordocid");
    houseFilter1.addEditorListener();
    QWareHouseFilter houseFilter2 =
        new QWareHouseFilter(condDLGDelegator, BillHeaderVO.CTOUTSTOCKORGID,
            "bodyfk.ctoutstordocid");
    houseFilter2.addEditorListener();
    QWareHouseFilter houseFilter3 =
        new QWareHouseFilter(condDLGDelegator, BillHeaderVO.CINSTOCKORGID,
            "bodyfk.cinstordocid");
    houseFilter3.addEditorListener();

    // 收货客户过滤 根据调入库存组织
    QCustomerFilter customerFilter =
        new QCustomerFilter(condDLGDelegator, "bodyfk.creceivecustid");
    customerFilter.setPk_orgCode("cinstockorgid");
    customerFilter.addEditorListener();

    TemplateInfo tempalteinfo =
        ((DefaultQueryConditionDLG) condDLGDelegator.getQueryConditionDLG())
            .getTempInfo();
    tempalteinfo.setFunNode(SOFunc.N40060401.getCode());
    // 主组织权限
    condDLGDelegator.registerNeedPermissionOrgFieldCodes(new String[] {
      "bodyfk." + BillItemVO.CDELIVORGID
    });
    tempalteinfo.setFunNode(SOFunc.N40093010.getCode());
  }

}
