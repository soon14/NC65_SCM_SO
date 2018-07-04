package nc.ui.so.m30.billui.view;

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
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.util.SaleOrderVOUtil;
import nc.vo.so.pub.SOItemKey;

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
  public void initQueryConditionDLG(QueryConditionDLGDelegator condDLGDelegator) {

    // 设置单据状态
    this.setBillStatusCombox(condDLGDelegator);

    // 处理子表冗余字段
    this.processBodyItem(condDLGDelegator);

    // 数据权限
    condDLGDelegator.setPowerEnable(true);

    // 主组织权限
    condDLGDelegator.registerNeedPermissionOrgFieldCodes(new String[] {
      SOItemKey.PK_ORG
    });

    condDLGDelegator.addQueryCondVODealer(new MarAssistantDealer());

    // 初始参超过滤约束
    this.initFilterRef(condDLGDelegator);
  }

  private void setBillStatusCombox(QueryConditionDLGDelegator condDLGDelegator) {
    SaleOrderVOUtil util = new SaleOrderVOUtil();
    EnumRefRegisterInfo statusinfo =
        new EnumRefRegisterInfo(util.getBillStatusName(),
            util.getBillStatusValue());

    condDLGDelegator.setComboxItem(null, SaleOrderHVO.FSTATUSFLAG, statusinfo);
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
    filterutil.removeFilterMaps(new String[] {
      "so_saleorder_b.csendstockorgid", "so_saleorder_b.csettleorgid",
      "so_saleorder_b.ctrafficorgid",
      "so_saleorder_b.cmffileid","so_saleorder_b.cmffileid.vskucode"
    });
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
