package nc.ui.so.depmatrel.view;

import java.awt.BorderLayout;

import nc.ui.pubapp.uif2app.view.BillForm;
import nc.ui.so.base.view.SaleOrgPanel;
import nc.ui.so.depmatrel.model.DepMatRelBillManageModel;
import nc.ui.uif2.model.IAppModelDataManagerEx;
import nc.vo.so.custmatrel.entity.CustMatRelBVO;
import nc.vo.so.depmatrel.entity.DepMatRelBVO;
import nc.vo.so.pub.util.BaseSaleClassUtil;

public class CardForm extends BillForm {

  /**
   * 
   */
  private static final long serialVersionUID = -2820495926910182708L;

  private SaleOrgPanel orgPanel;

  private IAppModelDataManagerEx dataManager;

  public IAppModelDataManagerEx getDataManager() {
    return this.dataManager;
  }

  public void setDataManager(IAppModelDataManagerEx dataManager) {
    this.dataManager = dataManager;
  }

  public SaleOrgPanel getOrgPanel() {
    if (null == this.orgPanel) {
      this.orgPanel = this.createDefaultOrgPanel();
    }
    return this.orgPanel;
  }

  public void setOrgPanel(SaleOrgPanel orgPanel) {
    this.orgPanel = orgPanel;
  }

  @Override
  protected void initBillCardPanel() {
    super.initBillCardPanel();
    this.setBaseOrSaleClassShow();

  }

  private void setBaseOrSaleClassShow() {
    String pk_group = this.getModel().getContext().getPk_group();
    if (BaseSaleClassUtil.isMarBaseClass(pk_group)) {
      this.getBillCardPanel().hideBodyTableCol(
          DepMatRelBVO.PK_MATERIALSALECLASS);
    }
    else {
      this.getBillCardPanel().hideBodyTableCol(
          DepMatRelBVO.PK_MATERIALBASECLASS);
    }
  }

  @Override
  public void initUI() {
    super.initUI();
    this.add(this.getOrgPanel(), BorderLayout.NORTH);
  }

  private SaleOrgPanel createDefaultOrgPanel() {
    SaleOrgPanel orgPanel1 = new SaleOrgPanel();
    DepMatRelBillManageModel model = (DepMatRelBillManageModel) this.getModel();
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
          .loadEditRelationItemValue(i, DepMatRelBVO.PK_MATERIAL_V);
    }
  }
}
