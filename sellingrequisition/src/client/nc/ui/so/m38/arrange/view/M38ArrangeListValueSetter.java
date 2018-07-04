package nc.ui.so.m38.arrange.view;

import nc.ui.pub.bill.BillListPanel;
import nc.ui.uif2.editor.BillListView.IBillListPanelValueSetter;
import nc.vo.pub.CircularlyAccessibleValueObject;

public class M38ArrangeListValueSetter implements IBillListPanelValueSetter {

  /**
   * 将viewVO设置在字段在表体的view模板上
   * 
   * @version 6.0
   * @author 刘志伟
   * @time 2010-6-30 上午09:44:39
   */
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
