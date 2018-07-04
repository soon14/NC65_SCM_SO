package nc.ui.so.m30.billref.m32;

import java.awt.Container;

import nc.ui.pubapp.billref.src.DefaultBillReferQuery;
import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.ui.pubapp.uif2app.query2.totalvo.MarAssistantDealer;
import nc.ui.scmpub.query.refregion.QDeptFilter;
import nc.ui.scmpub.query.refregion.QFfileFilterByMaterCode;
import nc.ui.scmpub.query.refregion.QPsndocFilter;
import nc.ui.scmpub.query.refregion.QTransTypeFilter;
import nc.ui.scmpub.query.refregion.RefCommonFilterListener;
import nc.ui.so.pub.query.KeyEditedDealer;
import nc.vo.querytemplate.TemplateInfo;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m32.paravo.RefAddLineParaVO;

public class SaleOrderBillReferQuery extends DefaultBillReferQuery {

  /**
   * @param c
   * @param info
   */
  public SaleOrderBillReferQuery(Container c, TemplateInfo info) {
    super(c, info);
  }

  @Override
  protected void initQueryConditionDLG(QueryConditionDLGDelegator dlgDelegator) {

    // 参照增行设置查询模板上的开票组织值和编辑性
    RefAddLineParaVO paravo =
        (RefAddLineParaVO) this.getBillSrcVar().getUserObj();
    if (null != paravo && null != paravo.getPk_org()) {
      dlgDelegator.setDefaultValue(SaleOrderBVO.METAPATH
          + SaleOrderBVO.CSETTLEORGID, paravo.getPk_org());
      KeyEditedDealer edited = new KeyEditedDealer();
      edited.setEditKey(SaleOrderBVO.METAPATH + SaleOrderBVO.CSETTLEORGID);
      dlgDelegator.addQueryCondVODealer(edited);
    }
    // 初始参超过滤约束
    this.initFilterRef(dlgDelegator);
    // 处理子表冗余字段
    this.processBodyItem(dlgDelegator);

    // 数据权限
    dlgDelegator.setPowerEnable(true);

    // 主组织权限
    dlgDelegator.registerNeedPermissionOrgFieldCodes(new String[] {
      SaleOrderBVO.METAPATH + SaleOrderBVO.CSETTLEORGID
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

    String csettleorgidkey = "so_saleorder_b.csettleorgid";
    // // 主组织
    // filterutil.addFilterMaps(new String[] {
    // csettleorgidkey
    // }, csettleorgidkey);

    String sendstordocorgkey = "so_saleorder_b.csendstockorgid";
    // 仓库按照库存组织过滤
    filterutil.addFilterMaps(new String[] {
      "so_saleorder_b.csendstordocid"
    }, sendstordocorgkey);
    // 移除部分字段参照过滤
    filterutil.removeFilterMaps(new String[] {
      csettleorgidkey, sendstordocorgkey,
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
        SaleOrderBVO.METAPATH + "pk_org");
    // 单据日期
    condDLGDelegator.addRedundancyInfo(SaleOrderHVO.DBILLDATE,
        SaleOrderBVO.METAPATH + "dbilldate");
  }
}
