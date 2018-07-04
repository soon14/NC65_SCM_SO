package nc.ui.so.m32.billui.query;

import nc.ui.pubapp.uif2app.query2.IQueryConditionDLGInitializer;
import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.ui.pubapp.uif2app.query2.totalvo.EnumRefRegisterInfo;
import nc.ui.pubapp.uif2app.query2.totalvo.MarAssistantDealer;
import nc.ui.scmpub.query.refregion.QDeptFilter;
import nc.ui.scmpub.query.refregion.QFfileFilterByMaterCode;
import nc.ui.scmpub.query.refregion.QPsndocFilter;
import nc.ui.scmpub.query.refregion.QTransTypeFilter;
import nc.ui.scmpub.query.refregion.RefCommonFilterListener;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m32.entity.SaleInvoiceHVO;
import nc.vo.so.m32.util.SaleInvoiceVOUtil;
import nc.vo.so.pub.SOItemKey;

/**
 * 销售发票查询对话框初始化监听实现类
 * 
 * @since 6.0
 * @version 2011-1-11 下午04:23:20
 * @author 么贵敬
 */
public class SaleInvoiceQryCondDLGInitializer implements
    IQueryConditionDLGInitializer {

  @Override
  public void initQueryConditionDLG(QueryConditionDLGDelegator condDLGDelegator) {
    // 初始化下拉列表
    this.initComBox(condDLGDelegator);
    // 初始化参照约束条件
    this.initRefCondition(condDLGDelegator);
    // 处理子表冗余字段
    this.processBodyItem(condDLGDelegator);

    condDLGDelegator.setPowerEnable(true);

    // 主组织权限
    condDLGDelegator.registerNeedPermissionOrgFieldCodes(new String[] {
      SOItemKey.PK_ORG
    });

    condDLGDelegator.addQueryCondVODealer(new MarAssistantDealer());
    
  }

  private void initComBox(QueryConditionDLGDelegator condDLGDelegator) {
    SaleInvoiceVOUtil util = new SaleInvoiceVOUtil();
    EnumRefRegisterInfo info =
        new EnumRefRegisterInfo(util.getBillStatusName(),
            util.getBillStatusValue());
    condDLGDelegator.setComboxItem(null, SaleInvoiceHVO.FSTATUSFLAG, info);
    // super.setComboxItem(null, SaleInvoiceHVO.FSTATUSFLAG, info);

  }

  private void initRefCondition(QueryConditionDLGDelegator condDLGDelegator) {
    // 销售组织字段
    String saleorgkey = "csaleinvoicebid.csaleorgid";
    // 销售发票交易类型参照
    QTransTypeFilter trantype =
        new QTransTypeFilter(condDLGDelegator, SOBillType.Invoice.getCode());
    trantype.filter();

    // 销售订单交易类型
    QTransTypeFilter ordertran =
        new QTransTypeFilter(condDLGDelegator,
            "csaleinvoicebid.vfirsttrantype", SOBillType.Order.getCode());
    ordertran.filter();

    RefCommonFilterListener filterutil =
        new RefCommonFilterListener(condDLGDelegator, SaleInvoiceHVO.PK_ORG);
    filterutil.addFilterMaps(new String[] {
      "cinvoicecustid.pk_custclass", "csaleinvoicebid.cmaterialid",
      "csaleinvoicebid.cmaterialvid.materialsale.pk_marsaleclass"
    }, saleorgkey);

    filterutil.addFilterMaps(new String[] {
      "csaleinvoicebid.csendstordocid"
    }, "csaleinvoicebid.csendstockorgid");
    filterutil.removeFilterMaps(new String[] {
      "csaleinvoicebid.csaleorgid", "csaleinvoicebid.csendstockorgid",
      "csaleinvoicebid.cmffileid","csaleinvoicebid.cmffileid.vskucode"
    });
    filterutil.addFilterMapsListeners();
    
    //过滤销售部门
    QDeptFilter deptFilter = QDeptFilter.createDeptFilterOfSO(
    condDLGDelegator,"csaleinvoicebid.cdeptid");
    deptFilter.setPk_orgCode("csaleinvoicebid.csaleorgid");
    deptFilter.addEditorListener();

    // 过滤业务员
    QPsndocFilter psnFilter = QPsndocFilter.createQPsndocFilterOfSO(
    condDLGDelegator, "csaleinvoicebid.cemployeeid");
    psnFilter.setPk_orgCode("csaleinvoicebid.csaleorgid");
    psnFilter.addEditorListener();
    
    new QFfileFilterByMaterCode(condDLGDelegator, "csaleinvoicebid.cmaterialid.code", "csaleinvoicebid.cmffileid").addEditorListener();
    new QFfileFilterByMaterCode(condDLGDelegator, "csaleinvoicebid.cmaterialid.code", "csaleinvoicebid.cmffileid.vskucode").addEditorListener();

  }

  private void processBodyItem(QueryConditionDLGDelegator condDLGDelegator) {
    // 开票组织
    condDLGDelegator.addRedundancyInfo(SaleInvoiceHVO.PK_ORG,
        "csaleinvoicebid.pk_org");
    // 单据日期
    condDLGDelegator.addRedundancyInfo(SaleInvoiceHVO.DBILLDATE,
        "csaleinvoicebid.dbilldate");
  }

}
