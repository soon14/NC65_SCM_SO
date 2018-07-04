package nc.ui.so.m30.sobalance.view;

import nc.ui.pubapp.uif2app.view.ShowUpableBillListView;
import nc.vo.pubapp.AppContext;

@SuppressWarnings("serial")
public class SobalanceBillListView extends ShowUpableBillListView {
  @Override
  public void initUI() {
    super.initUI();

    // …Ë÷√æ´∂»
    SobalancePrecision.getInstance().setListPrecision(
        AppContext.getInstance().getPkGroup(), this.getBillListPanel());
  }
}
