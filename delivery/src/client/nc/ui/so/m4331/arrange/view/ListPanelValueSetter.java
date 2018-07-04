package nc.ui.so.m4331.arrange.view;

import nc.ui.pub.bill.BillListPanel;
import nc.ui.uif2.editor.BillListView.IBillListPanelValueSetter;
import nc.vo.pub.CircularlyAccessibleValueObject;

public class ListPanelValueSetter implements IBillListPanelValueSetter {

  @Override
  public void setBodyData(BillListPanel listPanel, Object selectedData) {

    listPanel.getBillListData().setBodyValueVO(
        (CircularlyAccessibleValueObject[]) selectedData);
    listPanel.getBillListData().getBodyBillModel().loadLoadRelationItemValue();
    listPanel.getBillListData().getBodyBillModel().execLoadFormula();

  }

  @Override
  public void setHeaderDatas(BillListPanel listPanel, Object[] allDatas) {
    // TODO 自动生成方法存根

  }

  @Override
  public void setHeaderRowData(BillListPanel listPanel, Object rowData, int row) {
    // TODO 自动生成方法存根

  }

}
