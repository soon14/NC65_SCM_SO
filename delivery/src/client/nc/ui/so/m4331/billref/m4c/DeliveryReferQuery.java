package nc.ui.so.m4331.billref.m4c;

import java.awt.Container;

import nc.itf.scmpub.reference.uap.setting.defaultdata.DefaultDataSettingAccessor;
import nc.ui.pubapp.billref.src.DefaultBillReferQuery;
import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.ui.pubapp.uif2app.query2.totalvo.MarAssistantDealer;
import nc.ui.scmpub.query.refregion.QCarrierFilter;
import nc.ui.scmpub.query.refregion.QCustomerFilter;
import nc.ui.scmpub.query.refregion.QFfileFilterByMaterCode;
import nc.ui.scmpub.query.refregion.QMarbasclassFilter;
import nc.ui.scmpub.query.refregion.QMarterialFilter;
import nc.ui.scmpub.query.refregion.QStockOrgFilter;
import nc.ui.scmpub.query.refregion.QTransTypeFilter;
import nc.ui.scmpub.query.refregion.QWareHouseFilter;
import nc.ui.scmpub.query.refregion.RefCommonFilterListener;
import nc.ui.so.pub.query.refregion.QBatchCodeFilter;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.querytemplate.TemplateInfo;
import nc.vo.so.m4331.entity.DeliveryBVO;
import nc.vo.so.m4331.entity.DeliveryHVO;
import nc.vo.so.pub.SOItemKey;

public class DeliveryReferQuery extends DefaultBillReferQuery {

  public DeliveryReferQuery(Container c, TemplateInfo info) {
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
      DeliveryBVO.MAINMETAPATH + DeliveryBVO.CSENDSTOCKORGID
    });

    dlgDelegator.addQueryCondVODealer(new MarAssistantDealer());
  }

  private void initFilterRef(QueryConditionDLGDelegator condDLGDelegator) {
    // 交易类型参照过滤
    QTransTypeFilter trantype =
        new QTransTypeFilter(condDLGDelegator, SOItemKey.VTRANTYPECODE);
    trantype.filter();
    
    RefCommonFilterListener filterutil =
        new RefCommonFilterListener(condDLGDelegator, DeliveryBVO.MAINMETAPATH
                + DeliveryBVO.CSENDSTOCKORGID);
    
    String instockorgkey = DeliveryBVO.MAINMETAPATH + DeliveryBVO.CINSTOCKORGID;
    // 仓库  按照收货库存组织过滤
    filterutil.addFilterMaps(new String[] {
    		DeliveryBVO.MAINMETAPATH + DeliveryBVO.CINSTORDOCID     
    }, instockorgkey);
    
    String[] removekeys =
        new String[] {
    		instockorgkey, DeliveryBVO.MAINMETAPATH
        + DeliveryBVO.CSENDSTOCKORGID,
        "cdeliverybid.cmffileid","cdeliverybid.cmffileid.vskucode"
        };
    // 移除部分字段参照过滤
    filterutil.removeFilterMaps(removekeys);

    filterutil.addFilterMapsListeners();
    
    //过滤批次档案
    QBatchCodeFilter batch = new QBatchCodeFilter();
    batch.filter(condDLGDelegator,"cdeliverybid.vbatchcode");
    
    new QFfileFilterByMaterCode(condDLGDelegator, "cdeliverybid.cmaterialid.code", "cdeliverybid.cmffileid").addEditorListener();
    new QFfileFilterByMaterCode(condDLGDelegator, "cdeliverybid.cmaterialid.code", "cdeliverybid.cmffileid.vskucode").addEditorListener();


//    // 仓库
//    QWareHouseFilter whfilter =
//        new QWareHouseFilter(condDLGDelegator, DeliveryBVO.MAINMETAPATH
//            + DeliveryBVO.CINSTOCKORGID, DeliveryBVO.MAINMETAPATH
//            + DeliveryBVO.CINSTORDOCID);
//    whfilter.addEditorListener();
//
//    // 发货库存组织过滤
//    QStockOrgFilter csendstockorgid =
//        new QStockOrgFilter(condDLGDelegator, DeliveryBVO.MAINMETAPATH
//            + DeliveryBVO.CSENDSTOCKORGID);
//    csendstockorgid.filter();
//
//    // 订单客户过滤
//    QCustomerFilter cordercustid =
//        new QCustomerFilter(condDLGDelegator, DeliveryBVO.MAINMETAPATH
//            + DeliveryBVO.CORDERCUSTID);
//    cordercustid.setPk_orgCode(DeliveryBVO.MAINMETAPATH
//        + DeliveryBVO.CSENDSTOCKORGID);
//    cordercustid.addEditorListener();
//
//    // 收货客户过滤
//    QCustomerFilter creceivecustid =
//        new QCustomerFilter(condDLGDelegator, DeliveryBVO.MAINMETAPATH
//            + DeliveryBVO.CRECEIVECUSTID);
//    creceivecustid.setPk_orgCode(DeliveryBVO.MAINMETAPATH
//        + DeliveryBVO.CSENDSTOCKORGID);
//    creceivecustid.addEditorListener();
//    // 收货库存组织过滤
//    QStockOrgFilter cinstockorgid =
//        new QStockOrgFilter(condDLGDelegator, DeliveryBVO.MAINMETAPATH
//            + DeliveryBVO.CINSTOCKORGID);
//    cinstockorgid.filter();
//
//    // 承运商
//    QCarrierFilter carrier =
//        new QCarrierFilter(condDLGDelegator, "cdeliverybid.ctranscustid");
//    carrier.setPk_orgCode(DeliveryBVO.MAINMETAPATH
//        + DeliveryBVO.CSENDSTOCKORGID);
//    carrier.addEditorListener();
//
//    // 物料编码:参照发货库存组织可见的物料（销售组织非空且唯一）,否则参照集团可见的物料进行录入
//    QMarterialFilter marteral =
//        new QMarterialFilter(condDLGDelegator, DeliveryBVO.MAINMETAPATH
//            + DeliveryBVO.CSENDSTOCKORGID, "cdeliverybid.cmaterialid");
//    marteral.addEditorListener();
//
//    // // 物料基本分类 (按销售组织过滤)
//    QMarbasclassFilter marbasclass =
//        new QMarbasclassFilter(condDLGDelegator,
//            "cdeliverybid.cmaterialid.pk_marbasclass");
//    marbasclass.setPk_orgCode(DeliveryBVO.MAINMETAPATH
//        + DeliveryBVO.CSENDSTOCKORGID);
//    marbasclass.addEditorListener();

  }

  private void processBodyItem(QueryConditionDLGDelegator condDLGDelegator) {
    // 主组织
    condDLGDelegator.addRedundancyInfo(DeliveryHVO.PK_ORG,
        "cdeliverybid.pk_org");
    // 单据日期
    condDLGDelegator.addRedundancyInfo(DeliveryHVO.DBILLDATE,
        "cdeliverybid.dbilldate");
  }

  private void setDefaultPk_org(QueryConditionDLGDelegator condDLGDelegator) {
    String defaultOrg = null;
    try {
      defaultOrg = DefaultDataSettingAccessor.getDefaultSaleOrg();
    }
    catch (Exception ex) {
      ExceptionUtils.wrappException(ex);
    }
    if (!PubAppTool.isNull(defaultOrg)) {
      condDLGDelegator.setDefaultValue("cdeliverybid.csendstockorgid",
          defaultOrg);
    }
  }

}
