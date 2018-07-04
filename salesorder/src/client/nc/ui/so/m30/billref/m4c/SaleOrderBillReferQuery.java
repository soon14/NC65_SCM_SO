package nc.ui.so.m30.billref.m4c;

import java.awt.Container;

import nc.pub.templet.converter.util.helper.ExceptionUtils;
import nc.pubitf.setting.defaultdata.OrgSettingAccessor;
import nc.ui.pubapp.billref.src.DefaultBillReferQuery;
import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.ui.pubapp.uif2app.query2.totalvo.MarAssistantDealer;
import nc.ui.scmpub.query.refregion.QDeptFilter;
import nc.ui.scmpub.query.refregion.QFfileFilterByMaterCode;
import nc.ui.scmpub.query.refregion.QPsndocFilter;
import nc.ui.scmpub.query.refregion.QTransTypeFilter;
import nc.ui.scmpub.query.refregion.RefCommonFilterListener;
import nc.vo.querytemplate.TemplateInfo;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;

/***
 * 引用查询处理
 * 
 * @since 6.0
 * @version 2013-5-9 上午09:08:18
 * @author
 */
public class SaleOrderBillReferQuery extends DefaultBillReferQuery {

  public SaleOrderBillReferQuery(Container c, TemplateInfo info) {
    super(c, info);
  }

  @Override
  protected void initQueryConditionDLG(QueryConditionDLGDelegator dlgDelegator) {

    dlgDelegator.addQueryCondVODealer(new MarAssistantDealer());
    // 初始参超过滤约束
    this.initFilterRef(dlgDelegator);
    // 处理子表冗余字段
    this.processBodyItem(dlgDelegator);
    // 数据权限
    dlgDelegator.setPowerEnable(true);
    // 主组织权限
    dlgDelegator.registerNeedPermissionOrgFieldCodes(new String[] {
      SaleOrderBVO.METAPATH + SaleOrderBVO.CSENDSTOCKORGID
    });
    // 默认组织
    this.setDefaultPk_org(dlgDelegator);

  }

  private void initFilterRef(QueryConditionDLGDelegator condDLGDelegator) {
    // 库存组织字段
    String csendstockorgkey = "so_saleorder_b.csendstockorgid";

    RefCommonFilterListener filterutil =
        new RefCommonFilterListener(condDLGDelegator, csendstockorgkey);
    filterutil.removeFilterMaps(new String[] {"so_saleorder_b.cmffileid","so_saleorder_b.cmffileid.vskucode","cdeptid","cemployeeid"});
    filterutil.addFilterMapsListeners();

    // 订单交易类型参照
    QTransTypeFilter trantype =
        new QTransTypeFilter(condDLGDelegator, SOBillType.Order.getCode());
    trantype.filter();

    // 过滤销售部门
    QDeptFilter deptFilter =
        QDeptFilter
            .createDeptFilterOfSO(condDLGDelegator, SaleOrderHVO.CDEPTID);
    deptFilter.setPk_orgCode(SaleOrderHVO.PK_ORG);
    deptFilter.addEditorListener();

    // 业务员
    QPsndocFilter employee =
        QPsndocFilter.createQPsndocFilterOfSO(condDLGDelegator,
            SaleOrderHVO.CEMPLOYEEID);
    employee.setPk_orgCode(SaleOrderHVO.PK_ORG);
    employee.addEditorListener();
    
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

  /**
   * 取个性化的默认组织
   * 
   * @param condDLGDelegator
   */
  private void setDefaultPk_org(QueryConditionDLGDelegator condDLGDelegator) {
    String defaultOrg = null;
    try {
      defaultOrg = OrgSettingAccessor.getDefaultOrgUnit();
    }
    catch (Exception ex) {
      ExceptionUtils.wrapException(ex);
    }
    if (defaultOrg != null && defaultOrg.trim().length() > 0) {
      condDLGDelegator.setDefaultValue(SaleOrderBVO.METAPATH
          + SaleOrderBVO.CSENDSTOCKORGID, defaultOrg);
    }
  }
}
