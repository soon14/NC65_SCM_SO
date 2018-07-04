package nc.ui.so.m30.revise.model;

import nc.funcnode.ui.FuncletInitData;
import nc.ui.pubapp.uif2app.AppUiState;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.pubapp.uif2app.model.DefaultFuncNodeInitDataListener.IInitDataProcessor;
import nc.ui.uif2.model.AbstractUIAppModel;
import nc.vo.so.m30.entity.SaleOrderVO;

/**
 * 修订历史节点(40060302H)打开初始数据处理器
 * 
 * @since 6.0
 * @version 2011-6-10 下午01:49:21
 * @author 刘志伟
 */
public class M30ReviseInitDataProcessor implements IInitDataProcessor {
  private AbstractUIAppModel model;

  @Override
  public void process(FuncletInitData data) {
    SaleOrderVO[] bills = (SaleOrderVO[]) data.getInitData();
    ((BillManageModel) this.model).setAppUiState(AppUiState.NOT_EDIT);
    // 设置默认值
    this.model.initModel(bills);
  }

  public AbstractUIAppModel getModel() {
    return this.model;
  }

  public void setModel(AbstractUIAppModel model) {
    this.model = model;
  }
}
