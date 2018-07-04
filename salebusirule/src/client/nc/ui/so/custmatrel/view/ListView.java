package nc.ui.so.custmatrel.view;

import java.awt.BorderLayout;

import nc.ui.pubapp.uif2app.view.ShowUpableBillListView;
import nc.ui.so.base.view.CustMatRelOrgPanel;
import nc.ui.so.custmatrel.model.CustMatRelBillManageModel;
import nc.ui.uif2.model.IAppModelDataManagerEx;
import nc.vo.so.custmatrel.entity.CustMatRelBVO;
import nc.vo.so.pub.util.BaseSaleClassUtil;

public class ListView extends ShowUpableBillListView {

  /**
   * 
   */
  private static final long serialVersionUID = 1157594445279931627L;

  private CustMatRelOrgPanel orgPanel;

  private IAppModelDataManagerEx dataManager;

  public IAppModelDataManagerEx getDataManager() {
    return this.dataManager;
  }

  public void setDataManager(IAppModelDataManagerEx dataManager) {
    this.dataManager = dataManager;
  }

  public CustMatRelOrgPanel getOrgPanel() {
    if (null == this.orgPanel) {
      this.orgPanel = this.createDefaultOrgPanel();
    }
    return this.orgPanel;
  }

  @Override
  public void initUI() {
    super.initUI();
    this.setBaseOrSaleClassShow();
    this.add(this.getOrgPanel(), BorderLayout.NORTH);
  }

  private void setBaseOrSaleClassShow() {
    String pk_group = this.getModel().getContext().getPk_group();
    if (BaseSaleClassUtil.isMarBaseClass(pk_group)) {
      this.getBillListPanel().hideBodyTableCol(
          CustMatRelBVO.PK_MATERIALSALECLASS);
    }
    else {
      this.getBillListPanel().hideBodyTableCol(
          CustMatRelBVO.PK_MATERIALBASECLASS);
    }

    if (BaseSaleClassUtil.isCustBaseClass(pk_group)) {
      this.getBillListPanel().hideBodyTableCol(CustMatRelBVO.PK_CUSTSALECLASS);
    }
    else {
      this.getBillListPanel().hideBodyTableCol(CustMatRelBVO.PK_CUSTBASECLASS);
    }
  }

  private CustMatRelOrgPanel createDefaultOrgPanel() {
	  CustMatRelOrgPanel orgPanel1 = new CustMatRelOrgPanel();
    CustMatRelBillManageModel model =
        (CustMatRelBillManageModel) this.getModel();
    orgPanel1.setModel(model);
    orgPanel1.setDataManager(this.getDataManager());
    orgPanel1.initUI();
    return orgPanel1;
  }

}
