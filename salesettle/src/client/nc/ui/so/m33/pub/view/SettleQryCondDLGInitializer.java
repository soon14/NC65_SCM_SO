package nc.ui.so.m33.pub.view;

import nc.ui.pubapp.uif2app.query2.IQueryConditionDLGInitializer;
import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.ui.scmpub.query.refregion.QCustomerFilter;
import nc.ui.scmpub.query.refregion.QDeptFilter;
import nc.ui.scmpub.query.refregion.QFfileFilterByMaterCode;
import nc.ui.scmpub.query.refregion.QMarterialoidFilter;
import nc.ui.scmpub.query.refregion.QPsndocFilter;
import nc.ui.scmpub.query.refregion.QTransTypeFilter;
import nc.ui.scmpub.query.refregion.QWareHouseFilter;
import nc.ui.so.pub.query.refregion.QBatchCodeFilter;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m33.m4c.entity.SquareOutBVO;
import nc.vo.so.m33.m4c.entity.SquareOutHVO;
import nc.vo.so.pub.SOItemKey;

public class SettleQryCondDLGInitializer implements
    IQueryConditionDLGInitializer {

  @Override
  public void initQueryConditionDLG(QueryConditionDLGDelegator condDLGDelegator) {
    // 设置过滤
    this.setFilter(condDLGDelegator);

    // 设置表体冗余条件
    this.setRedundancyInfo(condDLGDelegator);

    // 主组织权限
    condDLGDelegator.registerNeedPermissionOrgFieldCodes(new String[] {
      SOItemKey.PK_ORG
    });
  }

  private void setFilter(QueryConditionDLGDelegator condDLGDelegator) {
    // 参照组织可见的开票客户
    new QCustomerFilter(condDLGDelegator, SquareOutBVO.CSALESQUAREBID + "."
        + SquareOutBVO.CINVOICECUSTID).addEditorListener();

    // 参照组织可见的部门
    QDeptFilter deptFilter =
        QDeptFilter.createDeptFilterOfSO(condDLGDelegator,
            SquareOutBVO.CSALESQUAREBID + "." + SquareOutBVO.CDEPTID);
    deptFilter.setPk_orgCode(SquareOutBVO.CSALESQUAREBID + "."
        + SquareOutBVO.CSALEORGID);
    deptFilter.addEditorListener();
    
    // 物料编码
    QMarterialoidFilter marteral =
        new QMarterialoidFilter(condDLGDelegator, "csendstockorgid",
            "csalesquarebid.cmaterialid.code");
    marteral.addEditorListener();
    
    // 参照组织可见的业务员
    QPsndocFilter psnFilter =
        QPsndocFilter.createQPsndocFilterOfSO(condDLGDelegator,
            "csalesquarebid.cemployeeid");
    psnFilter.setPk_orgCode(SquareOutBVO.CSALESQUAREBID + "."
        + SquareOutBVO.CSALEORGID);
    psnFilter.addEditorListener();

    // 按库存组织过滤仓库
    new QWareHouseFilter(condDLGDelegator, SquareOutHVO.CSENDSTOCKORGID,
        SquareOutHVO.CSENDSTORDOCID).addEditorListener();

    // 按单据类型过滤交易类型
    new QTransTypeFilter(condDLGDelegator, SquareOutBVO.CSALESQUAREBID + "."
        + SquareOutBVO.VFIRSTTRANTYPE, SOBillType.Order.getCode()).filter();

    // 过滤批次档案
    QBatchCodeFilter batch = new QBatchCodeFilter();
    batch.filter(condDLGDelegator, "csalesquarebid.vbatchcode");
    
    new QFfileFilterByMaterCode(condDLGDelegator, "csalesquarebid.cmaterialid.code", "csalesquarebid.cmffileid").addEditorListener();
    new QFfileFilterByMaterCode(condDLGDelegator, "csalesquarebid.cmaterialid.code", "csalesquarebid.cmffileid.vskucode").addEditorListener();

  }

  private void setRedundancyInfo(QueryConditionDLGDelegator condDLGDelegator) {
    condDLGDelegator.addRedundancyInfo(SquareOutHVO.DBILLDATE,
        SquareOutBVO.CSALESQUAREBID + "." + SquareOutBVO.DBILLDATE);
  }

}
