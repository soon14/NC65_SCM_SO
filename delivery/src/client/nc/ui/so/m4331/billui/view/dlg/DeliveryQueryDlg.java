package nc.ui.so.m4331.billui.view.dlg;

import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m4331.entity.DeliveryBVO;
import nc.vo.so.m4331.entity.DeliveryHVO;
import nc.vo.so.m4331.pub.DeliveryVoUtil;
import nc.vo.so.pub.SOItemKey;
import nc.itf.scmpub.reference.uap.setting.defaultdata.DefaultDataSettingAccessor;

import nc.ui.pubapp.uif2app.query2.IQueryConditionDLGInitializer;
import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.ui.pubapp.uif2app.query2.totalvo.EnumRefRegisterInfo;
import nc.ui.pubapp.uif2app.query2.totalvo.MarAssistantDealer;
import nc.ui.scmpub.query.refregion.QDeptFilter;
import nc.ui.scmpub.query.refregion.QFfileFilterByMaterCode;
import nc.ui.scmpub.query.refregion.QPsndocFilter;
import nc.ui.scmpub.query.refregion.QTransTypeFilter;
import nc.ui.scmpub.query.refregion.RefCommonFilterListener;

/**
 * 
 * 
 * @since 6.1
 * @version 2013-01-18 12:12:16
 * @author liangjm
 */
public class DeliveryQueryDlg implements IQueryConditionDLGInitializer {

  @Override
  public void initQueryConditionDLG(QueryConditionDLGDelegator condDLGDelegator) {
    this.initRefCondition(condDLGDelegator);
    this.processBodyItem(condDLGDelegator);
    this.setDefaulValue(condDLGDelegator);
    // 数据权限
    condDLGDelegator.setPowerEnable(true);
    // 主组织权限
    condDLGDelegator.registerNeedPermissionOrgFieldCodes(new String[] {
      SOItemKey.PK_ORG
    });
    // 处理单据状态的枚举
    this.processBillStatusCond(condDLGDelegator);
    condDLGDelegator.addQueryCondVODealer(new MarAssistantDealer());
  }

  /**
   * 处理单据状态的枚举
   * 
   * @param condDLGDelegator
   */
  private void processBillStatusCond(QueryConditionDLGDelegator condDLGDelegator) {
    DeliveryVoUtil voutil = new DeliveryVoUtil();
    EnumRefRegisterInfo info =
        new EnumRefRegisterInfo(voutil.getBillStatusName(),
            voutil.getBillStatusValue());
    condDLGDelegator.setComboxItem(null, DeliveryHVO.FSTATUSFLAG, info);
  }

  private void initRefCondition(QueryConditionDLGDelegator condDLGDelegator) {
    // 发货单交易类型参照
    QTransTypeFilter trantype =
        new QTransTypeFilter(condDLGDelegator, SOBillType.Delivery.getCode());
    trantype.filter();

    RefCommonFilterListener filterutil =
        new RefCommonFilterListener(condDLGDelegator, DeliveryHVO.PK_ORG);

    String saleorgkey = DeliveryBVO.MAINMETAPATH + DeliveryBVO.CSALEORGID;
    String sendstockorgkey =
        DeliveryBVO.MAINMETAPATH + DeliveryBVO.CSENDSTOCKORGID;
    String instockorgkey = DeliveryBVO.MAINMETAPATH + DeliveryBVO.CINSTOCKORGID;
    // 客户,客户基本分类，客户销售分类 按照销售组织过滤
    filterutil.addFilterMaps(new String[] {
      DeliveryBVO.MAINMETAPATH + DeliveryBVO.CORDERCUSTID,
      "cdeliverybid.cordercustid.pk_custclass",
      "cdeliverybid.cordercustid.sales.pk_custsaleclass"
    }, saleorgkey);
    // 物料，物料基本分类，物料销售分类,发货仓库 按照发货库存组织过滤
    filterutil.addFilterMaps(new String[] {
      "cdeliverybid.cmaterialid", "cdeliverybid.cmaterialid.pk_marbasclass",
      "cdeliverybid.cmaterialvid.materialsale.pk_marsaleclass",
      "cdeliverybid.csendstordocid"
    }, sendstockorgkey);
    // 收货仓库 按照收货库存组织过滤
    filterutil.addFilterMaps(new String[] {
      "cdeliverybid.cinstordocid"
    }, instockorgkey);
    filterutil.removeFilterMaps(new String[] {
      "cdeliverybid.csendstockorgid", "cdeliverybid.cinstockorgid",
      "cdeliverybid.csaleorgid","cdeliverybid.cmffileid","cdeliverybid.cmffileid.vskucode"
    });
   
    
    //过滤销售部门
    QDeptFilter deptFilter = QDeptFilter.createDeptFilterOfTR(
    condDLGDelegator,DeliveryHVO.CSENDDEPTID);
    deptFilter.setPk_orgCode(DeliveryHVO.PK_ORG);
    deptFilter.addEditorListener();

    // 过滤业务员
    QPsndocFilter psnFilter = QPsndocFilter.createQPsndocFilterOfTR(
    condDLGDelegator, DeliveryHVO.CSENDEMPLOYEEID);
    psnFilter.setPk_orgCode(DeliveryHVO.PK_ORG);
    psnFilter.addEditorListener();
    filterutil.addFilterMapsListeners();
    
    // 过滤业务员
    QPsndocFilter csupercargoidFilter = QPsndocFilter.createQPsndocFilterOfTR(
    condDLGDelegator, "cdeliverybid.csupercargoid");
    csupercargoidFilter.setPk_orgCode(DeliveryHVO.PK_ORG);
    csupercargoidFilter.addEditorListener();
    filterutil.addFilterMapsListeners();
    
    new QFfileFilterByMaterCode(condDLGDelegator, "cdeliverybid.cmaterialid.code", "cdeliverybid.cmffileid").addEditorListener();
    new QFfileFilterByMaterCode(condDLGDelegator, "cdeliverybid.cmaterialid.code", "cdeliverybid.cmffileid.vskucode").addEditorListener();


    // // 发货库存组织过滤
    // QStockOrgFilter csendstockorgid =
    // new QStockOrgFilter(condDLGDelegator, DeliveryBVO.MAINMETAPATH
    // + DeliveryBVO.CSENDSTOCKORGID);
    // csendstockorgid.filter();
    // // 客户过滤
    // QCustomerFilter cordercustid =
    // new QCustomerFilter(condDLGDelegator, DeliveryBVO.MAINMETAPATH
    // + DeliveryBVO.CORDERCUSTID);
    // cordercustid.setPk_orgCode(DeliveryBVO.MAINMETAPATH
    // + DeliveryBVO.CSALEORGID);
    // cordercustid.addEditorListener();

    // // 承运商
    // QCarrierFilter carrier =
    // new QCarrierFilter(condDLGDelegator, "cdeliverybid.ctranscustid");
    // carrier.addEditorListener();
    //
    // // 客户基本分类：参照集团物料基本分类
    // QCustBaseClassFilter custbasclass =
    // new QCustBaseClassFilter(condDLGDelegator,
    // "cdeliverybid.cordercustid.pk_custclass");
    // cordercustid.setPk_orgCode(DeliveryBVO.MAINMETAPATH
    // + DeliveryBVO.CSALEORGID);
    // custbasclass.addEditorListener();
    //
    // // 客户销售分类：参照集团物料基本分类
    // QCustSaleClassFilter custsaleclass =
    // new QCustSaleClassFilter(condDLGDelegator,
    // "cdeliverybid.cordercustid.sales.pk_custsaleclass");
    // cordercustid.setPk_orgCode(DeliveryBVO.MAINMETAPATH
    // + DeliveryBVO.CSALEORGID);
    // custsaleclass.addEditorListener();

    // // 收货客户过滤
    // QCustomerFilter creceivecustid =
    // new QCustomerFilter(condDLGDelegator, DeliveryBVO.MAINMETAPATH
    // + DeliveryBVO.CRECEIVECUSTID);
    // creceivecustid.addEditorListener();
    // // 收货库存组织过滤
    // QStockOrgFilter cinstockorgid =
    // new QStockOrgFilter(condDLGDelegator, DeliveryBVO.MAINMETAPATH
    // + DeliveryBVO.CINSTOCKORGID);
    // cinstockorgid.filter();

    // // 押运员
    // QPsndocFilter supercargoid =
    // new QPsndocFilter(condDLGDelegator, "cdeliverybid.csupercargoid");
    // supercargoid.addEditorListener();

    // // 物料编码:参照发货库存组织可见的物料（销售组织非空且唯一）,否则参照集团可见的物料进行录入
    // QMarterialFilter marteral =
    // new QMarterialFilter(condDLGDelegator, DeliveryBVO.MAINMETAPATH
    // + DeliveryBVO.CSENDSTOCKORGID, "cdeliverybid.cmaterialid");
    // marteral.addEditorListener();
    //
    // // 物料基本分类：参照集团物料基本分类
    // QMarbasclassFilter marbasclass =
    // new QMarbasclassFilter(condDLGDelegator,
    // "cdeliverybid.cmaterialid.pk_marbasclass");
    // marbasclass.setPk_orgCode(DeliveryBVO.MAINMETAPATH
    // + DeliveryBVO.CSENDSTOCKORGID);
    // marbasclass.addEditorListener();
    //
    // // 物料销售分类：参照集团物料基本分类
    // QMarSaleClassFilter marsaleclass =
    // new QMarSaleClassFilter(condDLGDelegator,
    // "cdeliverybid.cmaterialvid.materialsale.pk_marsaleclass");
    // marsaleclass.setPk_orgCode(DeliveryBVO.MAINMETAPATH
    // + DeliveryBVO.CSENDSTOCKORGID);
    // marsaleclass.addEditorListener();
    //
    // // 运输路线
    // QRouteFilter routerfilter =
    // new QRouteFilter(condDLGDelegator, DeliveryHVO.CTRANSPORTROUTEID);
    // routerfilter.addEditorListener();

    condDLGDelegator.setPowerEnable(true);
  }

  private void processBodyItem(QueryConditionDLGDelegator condDLGDelegator) {
    // 开票组织
    condDLGDelegator.addRedundancyInfo(DeliveryHVO.PK_ORG,
        "cdeliverybid.pk_org");
    // 单据日期
    condDLGDelegator.addRedundancyInfo(DeliveryHVO.DBILLDATE,
        "cdeliverybid.dbilldate");
  }

  private void setDefaulValue(QueryConditionDLGDelegator condDLGDelegator) {
    String defaultOrg = null;
    try {
      defaultOrg = DefaultDataSettingAccessor.getDefaultSaleOrg();
    }
    catch (Exception ex) {
      ExceptionUtils.wrappException(ex);
    }
    if (defaultOrg != null && defaultOrg.trim().length() > 0) {
      condDLGDelegator.setDefaultValue(DeliveryHVO.PK_ORG, defaultOrg);
    }
  }
}
