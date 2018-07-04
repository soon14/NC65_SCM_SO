package nc.ui.so.salequotation.action;

import nc.ui.pubapp.uif2app.actions.MetaDataBasedPrintAction.IBeforePrintDataProcess;
import nc.ui.so.salequotation.scale.SalequoScaleProcessor;
import nc.ui.uif2.model.AbstractAppModel;
import nc.vo.so.salequotation.entity.AggSalequotationHVO;

public class SaleQuotationPrintProcessor implements IBeforePrintDataProcess {

  private AbstractAppModel model;

  /**
   * @return model
   */
  public AbstractAppModel getModel() {
    return this.model;
  }

  @Override
  public Object[] processData(Object[] datas) {
    AggSalequotationHVO[] vos = new AggSalequotationHVO[datas.length];
    for (int i = 0; i < datas.length; i++) {
      vos[i] = (AggSalequotationHVO) datas[i];
    }
    // 精度处理
    SalequoScaleProcessor handler = SalequoScaleProcessor.getInstance();
    handler.setVOPrecision(this.getModel().getContext().getPk_group(), vos);
    return vos;

  }

  /**
   * @param model
   */
  public void setModel(AbstractAppModel model) {
    this.model = model;
  }

}
