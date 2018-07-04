package nc.ui.so.m38.billui.query;

import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m38.entity.PreOrderHVO;
import nc.vo.so.pub.SOItemKey;

import nc.itf.scmpub.reference.uap.setting.defaultdata.DefaultDataSettingAccessor;

import nc.ui.pubapp.uif2app.query2.IQueryConditionDLGInitializer;
import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.ui.pubapp.uif2app.query2.totalvo.MarAssistantDealer;
import nc.ui.scmpub.query.refregion.QTransTypeFilter;
import nc.ui.scmpub.query.refregion.RefCommonFilterListener;

/**
 * 查询条件对话框
 * 
 * @since 6.1
 * @version 2013-01-17 14:54:25
 * @author liangjm
 */
public class PreOrderQryCondDLGInitializer implements
    IQueryConditionDLGInitializer {

  @Override
  public void initQueryConditionDLG(QueryConditionDLGDelegator dlgDelegator) {
    // 设置个性化中心默认主组织
    this.setDefaultPk_org(dlgDelegator);
    // 初始化参照约束条件
    this.initRefCondition(dlgDelegator);
    // 初始化表体冗余字段
    this.initBodyRedundancyItem(dlgDelegator);

    // 数据权限
    dlgDelegator.setPowerEnable(true);

    // 主组织权限
    dlgDelegator.registerNeedPermissionOrgFieldCodes(new String[] {
      SOItemKey.PK_ORG
    });

    dlgDelegator.addQueryCondVODealer(new MarAssistantDealer());
  }

  private void initBodyRedundancyItem(QueryConditionDLGDelegator dlgDelegator) {
    // 销售组织
    dlgDelegator.addRedundancyInfo(PreOrderHVO.PK_ORG, "so_preorder_b.pk_org");
    // 单据日期
    dlgDelegator.addRedundancyInfo(PreOrderHVO.DBILLDATE,
        "so_preorder_b.dbilldate");
  }

  private void initRefCondition(QueryConditionDLGDelegator dlgDelegator) {
    // 预订单交易类型参照
    QTransTypeFilter trantype =
        new QTransTypeFilter(dlgDelegator, SOBillType.PreOrder.getCode());
    trantype.filter();

    RefCommonFilterListener filterutil =
        new RefCommonFilterListener(dlgDelegator, PreOrderHVO.PK_ORG);
    filterutil.removeFilterMaps(new String[] {
      "so_preorder_b.csettleorgid", "so_preorder_b.csendstockorgid"
    });

    filterutil.addFilterMapsListeners();

    // // 客户
    // QCustomerFilter cust =
    // new QCustomerFilter(dlgDelegator, PreOrderHVO.CCUSTOMERID);
    // cust.addEditorListener();
    //
    // // 销售部门
    // QDeptFilter dept = new QDeptFilter(dlgDelegator, PreOrderHVO.CDEPTID);
    // dept.setPk_orgCode(PreOrderHVO.PK_ORG);
    // dept.addEditorListener();
    //
    // // 物料基本分类
    // QMarbasclassFilter marclass =
    // new QMarbasclassFilter(dlgDelegator,
    // "so_preorder_b.cmaterialid.pk_marbasclass");
    // marclass.addEditorListener();
    // marclass.setPk_orgCode(PreOrderHVO.PK_ORG);
    // marclass.filterByGroup();
    //
    // // 物料销售分类
    // QMarSaleClassFilter marSaleClass =
    // new QMarSaleClassFilter(dlgDelegator,
    // "so_preorder_b.cmaterialvid.materialsale.pk_marsaleclass");
    // marSaleClass.setPk_orgCode(PreOrderHVO.PK_ORG);
    // marSaleClass.addEditorListener();
    //
    // // 物料编码
    // QMarterialFilter marteral =
    // new QMarterialFilter(dlgDelegator, PreOrderHVO.PK_ORG,
    // "so_preorder_b.cmaterialid");
    // marteral.addEditorListener();
    //
    // // 销售业务员
    // QPsndocFilter employee =
    // new QPsndocFilter(dlgDelegator, PreOrderHVO.CEMPLOYEEID);
    // employee.setPk_orgCode(PreOrderHVO.PK_ORG);
    // employee.addEditorListener();
    //
    // // 发货库存组织
    // QStockOrgFilter stockOrg =
    // new QStockOrgFilter(dlgDelegator, "so_preorder_b.csendstockorgid");
    // stockOrg.filter();
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
