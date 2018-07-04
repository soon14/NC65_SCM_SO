package nc.ui.so.m30.arrange.editor;

import java.lang.reflect.Method;

import nc.ui.pub.beans.UIPanel;
import nc.ui.pubapp.billref.push.BillContext;
import nc.ui.pubapp.billref.push.PushBillEvent;
import nc.ui.pubapp.billref.src.view.RefListPanel;
import nc.ui.pubapp.util.RefListPanelValueUtils;
import nc.vo.ic.onhand.entity.OnhandDimVO;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m30.entity.SaleOrderBVO;

@SuppressWarnings("restriction")
public class OnRowChangeEventHandler {

  public void afterEdit(PushBillEvent e, BillContext context) {
    RefListPanel billtabpanel = context.getBillTabPanel().getListPanel();
    if (null != context.getBillTabPanel().getExtendedBottomPanel()) {
      int row = e.getRow();
      try {
        // 获取现存量维度vo
        OnhandDimVO dimVo = this.getDimVo(billtabpanel, row);
        // 刷新
        this.queryOnhand(context.getBillTabPanel().getExtendedBottomPanel(),
            dimVo);
      }
      catch (Exception ec) {
        ExceptionUtils.wrappException(ec);
      }
    }
  }

  private void queryOnhand(UIPanel panel, OnhandDimVO dimvo) throws Exception {
    Method setContextMethod =
        panel.getClass().getMethod("queryOnhand", OnhandDimVO.class);
    setContextMethod.invoke(panel, dimvo);
  }

  private OnhandDimVO getDimVo(RefListPanel billtabpanel, int row) {
    OnhandDimVO paraVO = new OnhandDimVO();
    RefListPanelValueUtils valueUtil = new RefListPanelValueUtils(billtabpanel);
    paraVO.setPk_group(AppContext.getInstance().getPkGroup());
    paraVO.setCastunitid(valueUtil.getStringValueAt(row,
        SaleOrderBVO.CASTUNITID));
    paraVO.setCmaterialoid(valueUtil.getStringValueAt(row,
        SaleOrderBVO.CMATERIALID));
    paraVO.setCmaterialvid(valueUtil.getStringValueAt(row,
        SaleOrderBVO.CMATERIALVID));
    paraVO.setCproductorid(valueUtil.getStringValueAt(row,
        SaleOrderBVO.CPRODUCTORID));
    paraVO.setCprojectid(valueUtil.getStringValueAt(row,
        SaleOrderBVO.CPROJECTID));
    paraVO
        .setCvendorid(valueUtil.getStringValueAt(row, SaleOrderBVO.CVENDORID));
    paraVO.setCwarehouseid(valueUtil.getStringValueAt(row,
        SaleOrderBVO.CSENDSTORDOCID));
    paraVO.setPk_batchcode(valueUtil.getStringValueAt(row,
        SaleOrderBVO.PK_BATCHCODE));
    paraVO.setVbatchcode(valueUtil.getStringValueAt(row,
        SaleOrderBVO.VBATCHCODE));
    paraVO.setPk_org(valueUtil.getStringValueAt(row,
        SaleOrderBVO.CSENDSTOCKORGID));
    paraVO.setVbatchcode(valueUtil.getStringValueAt(row,
        SaleOrderBVO.VBATCHCODE));
    paraVO.setVchangerate(valueUtil.getStringValueAt(row,
        SaleOrderBVO.VCHANGERATE));
    paraVO.setVfree1(valueUtil.getStringValueAt(row, SaleOrderBVO.VFREE1));
    paraVO.setVfree2(valueUtil.getStringValueAt(row, SaleOrderBVO.VFREE2));
    paraVO.setVfree3(valueUtil.getStringValueAt(row, SaleOrderBVO.VFREE3));
    paraVO.setVfree4(valueUtil.getStringValueAt(row, SaleOrderBVO.VFREE4));
    paraVO.setVfree5(valueUtil.getStringValueAt(row, SaleOrderBVO.VFREE5));
    paraVO.setVfree6(valueUtil.getStringValueAt(row, SaleOrderBVO.VFREE6));
    paraVO.setVfree7(valueUtil.getStringValueAt(row, SaleOrderBVO.VFREE7));
    paraVO.setVfree8(valueUtil.getStringValueAt(row, SaleOrderBVO.VFREE8));
    paraVO.setVfree9(valueUtil.getStringValueAt(row, SaleOrderBVO.VFREE9));
    paraVO.setVfree10(valueUtil.getStringValueAt(row, SaleOrderBVO.VFREE10));
    return paraVO;
  }
}
