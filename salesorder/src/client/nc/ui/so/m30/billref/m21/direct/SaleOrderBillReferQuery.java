package nc.ui.so.m30.billref.m21.direct;

import java.awt.Container;

import nc.ui.bd.ref.AbstractRefModel;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pubapp.billref.src.DefaultBillReferQuery;
import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.ui.pubapp.uif2app.query2.refedit.IRefFilter;
import nc.ui.pubapp.uif2app.query2.totalvo.MarAssistantDealer;
import nc.ui.scmpub.query.refregion.QDeptFilter;
import nc.ui.scmpub.query.refregion.QFfileFilterByMaterCode;
import nc.ui.scmpub.query.refregion.QPsndocFilter;
import nc.ui.scmpub.query.refregion.RefCommonFilterListener;
import nc.ui.scmpub.ref.FilterTransTypeRefUtils;
import nc.vo.querytemplate.TemplateInfo;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30trantype.enumeration.DirectType;

public class SaleOrderBillReferQuery extends DefaultBillReferQuery {

  public SaleOrderBillReferQuery(Container c, TemplateInfo info) {
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
      SaleOrderHVO.DEST_PK_ORG
    });

    dlgDelegator.addQueryCondVODealer(new MarAssistantDealer());
  }

  private void initFilterRef(QueryConditionDLGDelegator condDLGDelegator) {
    // 订单交易类型:参照销售订单直运采购标记的交易类型录入。
    condDLGDelegator.setRefFilter(SaleOrderHVO.CTRANTYPEID, new IRefFilter() {

      @Override
      public void doFilter(UIRefPane refPane) {
        StringBuilder where = new StringBuilder();
        where.append(" and pk_billtypecode in(");
        where.append(" select so_m30trantype.vtrantypecode");
        where.append(" from so_m30trantype where so_m30trantype.fdirecttype=");
        where.append(DirectType.DIRECTTRAN_PO.getIntValue());
        where.append(")");
        AbstractRefModel refModel = refPane.getRefModel();
        refModel.addWherePart(where.toString());
        FilterTransTypeRefUtils refUtil =
            new FilterTransTypeRefUtils(refPane, "");
        refUtil.filterTranType(new String[] {
          SOBillType.Order.getCode()
        });
      }
    });

    RefCommonFilterListener filterutil =
        new RefCommonFilterListener(condDLGDelegator, SaleOrderHVO.PK_ORG);
    String[] needfiltkeys = new String[] {
      "so_saleorder_b.cmaterialid.pk_marbasclass", "so_saleorder_b.cmaterialid"
    };
    // 物料基本分类：参照采购组织可见的物料基本分类档案
    // 物料编码：参照采购组织可见的物料档案
    filterutil.addFilterMaps(needfiltkeys, SaleOrderHVO.DEST_PK_ORG);
    filterutil.addFilterMapsListeners();

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
    
    new QFfileFilterByMaterCode(condDLGDelegator, "so_saleorder_b.cmaterialid.code", "so_saleorder_b.cmffileid").addEditorListener();
    new QFfileFilterByMaterCode(condDLGDelegator, "so_saleorder_b.cmaterialid.code", "so_saleorder_b.cmffileid.vskucode").addEditorListener();
    filterutil.removeFilterMaps(new String[] {"so_saleorder_b.cmffileid","so_saleorder_b.cmffileid.vskucode"});
    // // 客户:如果查询一个销售组织的数据，则可参照该销售组织可见的客户，否则参照集团范围客户档案。
    // QCustomerFilter cust =
    // new QCustomerFilter(condDLGDelegator, SaleOrderHVO.CCUSTOMERID);
    // cust.setPk_orgCode(SaleOrderHVO.PK_ORG);
    // cust.addEditorListener();
    //
    // // 销售部门:参照销售组织范围的部门档案
    // QDeptFilter dept = new QDeptFilter(condDLGDelegator,
    // SaleOrderHVO.CDEPTID);
    // dept.setPk_orgCode(SaleOrderHVO.PK_ORG);
    // dept.addEditorListener();
    //
    // // 销售业务员：参照销售组织范围的人员档案
    // QPsndocFilter employee =
    // new QPsndocFilter(condDLGDelegator, SaleOrderHVO.CEMPLOYEEID);
    // employee.setPk_orgCode(SaleOrderHVO.PK_ORG);
    // employee.addEditorListener();
    //
    // // 物料基本分类：参照采购组织可见的物料基本分类档案
    // QMarbasclassFilter marbasClass =
    // new QMarbasclassFilter(condDLGDelegator,
    // "so_saleorder_b.cmaterialid.pk_marbasclass");
    // marbasClass.setPk_orgCode(SaleOrderHVO.DEST_PK_ORG);
    // marbasClass.addEditorListener();
    //
    // // 物料编码：参照采购组织可见的物料档案
    // QMarterialFilter marteral =
    // new QMarterialFilter(condDLGDelegator, SaleOrderHVO.DEST_PK_ORG,
    // "so_saleorder_b.cmaterialid");
    // marteral.addEditorListener();
    //
    // // 收货单位:如果查询一个销售组织的数据，则可参照该销售组织可见的客户，否则参照集团范围客户档案。
    // QCustomerFilter receivecust =
    // new QCustomerFilter(condDLGDelegator, "so_saleorder_b.creceivecustid");
    // receivecust.setPk_orgCode(SaleOrderHVO.PK_ORG);
    // receivecust.addEditorListener();

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
