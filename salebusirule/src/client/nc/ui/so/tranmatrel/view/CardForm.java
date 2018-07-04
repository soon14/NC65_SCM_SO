package nc.ui.so.tranmatrel.view;

import java.awt.BorderLayout;

import nc.ui.pubapp.uif2app.view.BillForm;
import nc.ui.so.base.view.SaleOrgPanel;
import nc.ui.so.tranmatrel.model.TranMatRelBillManageModel;
import nc.ui.uif2.model.IAppModelDataManagerEx;
import nc.vo.so.depmatrel.entity.DepMatRelBVO;
import nc.vo.so.pub.util.BaseSaleClassUtil;
import nc.vo.so.tranmatrel.entity.TranMatRelBVO;

public class CardForm extends BillForm {
  /**
   * 
   */
  private static final long serialVersionUID = 4331100233114691192L;

  private IAppModelDataManagerEx dataManager;

  private SaleOrgPanel orgPanel;

  public IAppModelDataManagerEx getDataManager() {
    return this.dataManager;
  }

  public SaleOrgPanel getOrgPanel() {
    if (null == this.orgPanel) {
      this.orgPanel = this.createDefaultOrgPanel();
    }
    return this.orgPanel;
  }

  @Override
  public void initUI() {
    super.initUI();
    this.add(this.getOrgPanel(), BorderLayout.NORTH);
    // initOrgHandler();
  }

  public void setDataManager(IAppModelDataManagerEx dataManager) {
    this.dataManager = dataManager;
  }

  public void setOrgPanel(SaleOrgPanel orgPanel) {
    this.orgPanel = orgPanel;
  }

  @Override
  protected void initBillCardPanel() {
    super.initBillCardPanel();
    String pk_group = this.getModel().getContext().getPk_group();
    boolean materialbybaseclass = BaseSaleClassUtil.isMarBaseClass(pk_group);
    if (materialbybaseclass) {
      this.getBillCardPanel().hideBodyTableCol(
          TranMatRelBVO.PK_MATERIALSALECLASS);
    }
    else {
      this.getBillCardPanel().hideBodyTableCol(
          TranMatRelBVO.PK_MATERIALBASECLASS);
    }
  }

  private SaleOrgPanel createDefaultOrgPanel() {
    SaleOrgPanel orgPanel1 = new SaleOrgPanel();
    TranMatRelBillManageModel model =
        (TranMatRelBillManageModel) this.getModel();
    orgPanel1.setModel(model);
    orgPanel1.setDataManager(this.getDataManager());
    orgPanel1.initUI();
    return orgPanel1;
  }
  
  @Override
  public void setValue(Object obj) {
    // TODO Auto-generated method stub
    super.setValue(obj);
  
    int row = this.getBillCardPanel().getRowCount();
    for (int i = 0; i < row; i++) {
      this.getBillCardPanel().getBillModel()
          .loadEditRelationItemValue(i, TranMatRelBVO.PK_MATERIAL_V);
    }
  }

}
