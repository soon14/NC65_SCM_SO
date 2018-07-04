package nc.ui.so.m30.revise.model;

import nc.vo.so.m30.revise.entity.SaleOrderHistoryVO;
import nc.funcnode.ui.FuncletInitData;
import nc.ui.pubapp.uif2app.AppUiState;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.pubapp.uif2app.model.DefaultFuncNodeInitDataListener.IInitDataProcessor;
import nc.ui.uif2.UIState;
import nc.ui.uif2.model.AbstractUIAppModel;

/**
 * 
 * @since 6.1
 * @version 2018-03-29 ÏÂÎç11:08:15
 * @author Íõè÷Ü²
 */
public class AddChildInitDataProcessor implements IInitDataProcessor {

  private AbstractUIAppModel model;

  /**
   * 
   * @return model
   */
  public AbstractUIAppModel getModel() {
    return this.model;
  }

  @Override
  public void process(FuncletInitData data) {
    SaleOrderHistoryVO bills = (SaleOrderHistoryVO) data.getInitData();
    //((BillManageModel) this.model).setAppUiState(AppUiState.ADD);
    ((BillManageModel)this.getModel()).directlyAdd(bills);
  }

  /**
   * 
   * @param model
   */
  public void setModel(AbstractUIAppModel model) {
    this.model = model;
  }
}
