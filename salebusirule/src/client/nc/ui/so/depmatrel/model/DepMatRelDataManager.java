package nc.ui.so.depmatrel.model;

import nc.ui.uif2.model.AbstractUIAppModel;
import nc.ui.uif2.model.IAppModelDataManagerEx;
import nc.ui.uif2.model.IAppModelService;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class DepMatRelDataManager implements IAppModelDataManagerEx {

  private AbstractUIAppModel model;

  private IAppModelService service;

  public AbstractUIAppModel getModel() {
    return this.model;
  }

  public IAppModelService getService() {
    return this.service;
  }

  public void setModel(AbstractUIAppModel model) {
    this.model = model;
  }

  public void setService(IAppModelService service) {
    this.service = service;
  }

  @Override
  public void setShowSealDataFlag(boolean showSealDataFlag) {
    //
  }

  @Override
  public void initModel() {
    try {
      Object[] datas =
          this.getService().queryByDataVisibilitySetting(
              this.getModel().getContext());
      this.getModel().initModel(datas);
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }

  }

  @Override
  public void initModelBySqlWhere(String sqlWhere) {
    //
  }

  @Override
  public void refresh() {
    this.initModel();
  }

}
