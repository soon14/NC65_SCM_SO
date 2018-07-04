package nc.ui.so.m38.billui.view;

import nc.ui.pubapp.uif2app.view.ShowUpableBillListView;
import nc.ui.so.m38.billui.pub.PreOrderPrecision;
import nc.vo.pubapp.AppContext;

public class PreOrderListView extends ShowUpableBillListView {

  /**
   * 
   */
  private static final long serialVersionUID = 1126524884454146433L;

  @Override
  public void initUI() {
    super.initUI();
    String pk_group = AppContext.getInstance().getPkGroup();
    PreOrderPrecision.getInstance().setListPrecision(pk_group,
        this.getBillListPanel());
  }

}
