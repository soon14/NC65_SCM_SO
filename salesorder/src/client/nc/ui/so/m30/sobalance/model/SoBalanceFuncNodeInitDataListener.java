package nc.ui.so.m30.sobalance.model;

import nc.funcnode.ui.FuncletInitData;
import nc.ui.pub.linkoperate.ILinkMaintainData;
import nc.ui.pubapp.uif2app.model.DefaultFuncNodeInitDataListener;
import nc.ui.so.m30.sobalance.view.SobalanceBillForm;
import nc.ui.uif2.UIState;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.querytemplate.queryscheme.SimpleQuerySchemeVO;
import nc.vo.so.m30.sobalance.entity.SoBalanceVO;

public class SoBalanceFuncNodeInitDataListener extends
    DefaultFuncNodeInitDataListener {
  class SoBalanceInitDataProcessor implements
      SoBalanceFuncNodeInitDataListener.IInitDataProcessor {

    @Override
    public void process(FuncletInitData data) {
      if (data.getInitData() instanceof SimpleQuerySchemeVO) {
        SoBalanceFuncNodeInitDataListener.this.doInitDataByQueryScheme(data);
        return;
      }
      this.doLinkMaintain((ILinkMaintainData) data.getInitData());

      if (null != SoBalanceFuncNodeInitDataListener.this
          .getAutoShowUpComponent()) {
        SoBalanceFuncNodeInitDataListener.this.getAutoShowUpComponent()
            .showMeUp();
      }
    }

    protected void doLinkMaintain(ILinkMaintainData initData) {
      if (initData.getBillID() == null) {
        SoBalanceFuncNodeInitDataListener.this.getModel().initModel(null);
        SoBalanceFuncNodeInitDataListener.this.getModel().setUiState(
            UIState.ADD);
        SoBalanceVO[] vos = (SoBalanceVO[]) initData.getUserObject();
        SoBalanceFuncNodeInitDataListener.this.getEditor().setValue(vos[0]);
        SoBalanceFuncNodeInitDataListener.this.getEditor().setEditable(true);
        SoBalanceFuncNodeInitDataListener.this.getEditor().showMeUp();
      }
      else {
        Object obj =
            SoBalanceFuncNodeInitDataListener.this.getService().queryByPk(
                initData.getBillID());
        SoBalanceFuncNodeInitDataListener.this.getModel().initModel(obj);
        SoBalanceFuncNodeInitDataListener.this.getModel().setUiState(
            UIState.EDIT);
      }
    }

  }

  private SobalanceBillForm editor;

  @Override
  public void initData(FuncletInitData data) {
    if (null == data) {
      this.getModel().initModel(null);
      return;
    }
    IInitDataProcessor processor = new SoBalanceInitDataProcessor();

    try {
      processor.process(data);
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }
  }

  public SobalanceBillForm getEditor() {
    return this.editor;
  }

  public void setEditor(SobalanceBillForm editor) {
    this.editor = editor;
  }

}
