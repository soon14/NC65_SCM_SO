package nc.ui.so.m30.closemanage.model;

import nc.ui.pubapp.uif2app.model.IQueryService;
import nc.ui.uif2.model.AbstractBatchAppModel;
import nc.ui.uif2.model.IAppModelDataManagerEx;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class SaleOrderCloseManageDataManager implements IAppModelDataManagerEx {

  protected AbstractBatchAppModel model;

  private IQueryService service;

  private String sqlWhere;

  public AbstractBatchAppModel getModel() {
    return this.model;
  }

  public IQueryService getService() {
    return this.service;
  }

  @Override
  public void initModel() {
    // TODO 自动生成方法存根

  }

  /**
   * 通过查询条件初始化
   */
  @Override
  public void initModelBySqlWhere(String whereSql) {
    this.sqlWhere = whereSql;
    try {
      this.model.initModel(this.service.queryByWhereSql(this.sqlWhere));
    }
    catch (Exception e) {
      
      ExceptionUtils.wrappException(e);
    }
  }

  /**
   * 刷新
   */
  @Override
  public void refresh() {
    if (null != this.sqlWhere) {
      this.initModelBySqlWhere(this.sqlWhere);
    }
  }

  public void setModel(AbstractBatchAppModel model) {
    this.model = model;
  }

  public void setService(IQueryService service) {
    this.service = service;
  }

  @Override
  public void setShowSealDataFlag(boolean showSealDataFlag) {
    // TODO 自动生成方法存根

  }

}
