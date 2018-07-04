package nc.ui.so.salequotation.view;

import nc.ui.pubapp.ClientContext;
import nc.ui.pubapp.uif2app.view.ShowUpableBillListView;
import nc.ui.so.salequotation.scale.SalequoScaleProcessor;

public class SalequoListView extends ShowUpableBillListView {

  /**
   * 
   */
  private static final long serialVersionUID = 1371773687060131380L;

  @Override
  public void initUI() {
    super.initUI();
    SalequoScaleProcessor.getInstance().setListPrecision(
        ClientContext.getInstance().getPk_group(), this.getBillListPanel());
  }

}
