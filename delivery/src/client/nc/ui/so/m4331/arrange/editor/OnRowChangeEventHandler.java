package nc.ui.so.m4331.arrange.editor;

import nc.ui.ic.onhand.pane.QueryOnHandInfoPanel;
import nc.ui.pub.bill.BillListPanel;
import nc.ui.pubapp.billref.push.BillContext;
import nc.ui.pubapp.billref.push.BillTabPanel;
import nc.ui.pubapp.billref.push.PushBillEvent;
import nc.ui.pubapp.billref.src.view.RefListPanel;
import nc.ui.pubapp.util.RefListPanelValueUtils;
import nc.vo.ic.onhand.entity.OnhandDimVO;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m4331.entity.DeliveryBVO;

public class OnRowChangeEventHandler {

  public void afterEdit(PushBillEvent e, BillContext context) {
    BillTabPanel billtabpanel = context.getBillTabPanel();
 /*   // begin NCM qiwang 20150626 销售订单安排界面增加合计
    BillListPanel list = context.getListPanel();
    list.getChildListPanel().setTotalRowShow(true);
    // end NCM qiwang 20150626 销售订单安排界面增加合计
*/    if (null != billtabpanel.getExtendedBottomPanel()) {
      int row = e.getEditEvent().getRow();
      try {
        // 获取现存量维度vo
        OnhandDimVO dimVo = this.getDimVo(context, row);

        // begin-ncm-shenjzh-发货单更新存量纬度
        ((QueryOnHandInfoPanel) billtabpanel.getExtendedBottomPanel())
            .setQueryOnhandDimVO(dimVo);
        // end-ncm-shenjzh-发货单更新存量纬度
        
        // 刷新
        ((QueryOnHandInfoPanel) billtabpanel.getExtendedBottomPanel())
            .queryOnhand(dimVo);
      }
      catch (Exception e2) {
        ExceptionUtils.wrappException(e2);
      }
    }
  }

  private OnhandDimVO getDimVo(BillContext context, int row) throws Exception {
    RefListPanel reflist = context.getListPanel();
    RefListPanelValueUtils valueUtil = new RefListPanelValueUtils(reflist);
    OnhandDimVO paraVO = new OnhandDimVO();
    paraVO.setPk_group(AppContext.getInstance().getPkGroup());
    paraVO.setCastunitid(valueUtil
        .getStringValueAt(row, DeliveryBVO.CASTUNITID));
    paraVO
        .setClocationid(valueUtil.getStringValueAt(row, DeliveryBVO.CSPACEID));

    paraVO.setCmaterialoid(valueUtil.getStringValueAt(row,
        DeliveryBVO.CMATERIALID));
    paraVO.setCmaterialvid(valueUtil.getStringValueAt(row,
        DeliveryBVO.CMATERIALVID));
    paraVO.setCproductorid(valueUtil.getStringValueAt(row,
        DeliveryBVO.CPRODUCTORID));
    paraVO.setCprojectid(valueUtil
        .getStringValueAt(row, DeliveryBVO.CPROJECTID));
    paraVO.setCvendorid(valueUtil.getStringValueAt(row, DeliveryBVO.CVENDORID));
    paraVO.setCwarehouseid(valueUtil.getStringValueAt(row,
        DeliveryBVO.CSENDSTORDOCID));
    paraVO.setPk_batchcode(valueUtil.getStringValueAt(row,
        DeliveryBVO.PK_BATCHCODE));
    paraVO.setVbatchcode(valueUtil
        .getStringValueAt(row, DeliveryBVO.VBATCHCODE));
    paraVO.setPk_org(valueUtil.getStringValueAt(row,
        DeliveryBVO.CSENDSTOCKORGID));
    // paraVO.setPk_org_v(valueUtil.getStringValueAt(row,
    // DeliveryBVO.CSENDSTOCKORGVID));
    paraVO.setVbatchcode(valueUtil
        .getStringValueAt(row, DeliveryBVO.VBATCHCODE));
    paraVO.setVchangerate(valueUtil.getStringValueAt(row,
        DeliveryBVO.VCHANGERATE));
    paraVO.setVfree1(valueUtil.getStringValueAt(row, DeliveryBVO.VFREE1));
    paraVO.setVfree2(valueUtil.getStringValueAt(row, DeliveryBVO.VFREE2));
    paraVO.setVfree3(valueUtil.getStringValueAt(row, DeliveryBVO.VFREE3));
    paraVO.setVfree4(valueUtil.getStringValueAt(row, DeliveryBVO.VFREE4));
    paraVO.setVfree5(valueUtil.getStringValueAt(row, DeliveryBVO.VFREE5));
    paraVO.setVfree6(valueUtil.getStringValueAt(row, DeliveryBVO.VFREE6));
    paraVO.setVfree7(valueUtil.getStringValueAt(row, DeliveryBVO.VFREE7));
    paraVO.setVfree8(valueUtil.getStringValueAt(row, DeliveryBVO.VFREE8));
    paraVO.setVfree9(valueUtil.getStringValueAt(row, DeliveryBVO.VFREE9));
    paraVO.setVfree10(valueUtil.getStringValueAt(row, DeliveryBVO.VFREE10));
    return paraVO;
  }
}
