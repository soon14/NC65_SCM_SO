package nc.ui.so.custmatrel.view;

import java.awt.BorderLayout;

import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.view.BillForm;
import nc.ui.so.base.view.CustMatRelOrgPanel;
import nc.ui.so.custmatrel.model.CustMatRelBillManageModel;
import nc.ui.so.custmatrel.model.CustMatRelModelDataManager;
import nc.vo.so.custmatrel.entity.CustMatRelBVO;
import nc.vo.so.custmatrel.entity.CustMatRelHVO;
import nc.vo.so.custmatrel.entity.CustMatRelVO;
import nc.vo.so.pub.util.BaseSaleClassUtil;

public class CardForm extends BillForm {

  /**
   * 
   */
  private static final long serialVersionUID = 603904945129382759L;
  
  private CustMatRelVO cachecustmatervo;

  /**
   * 
   * @return CustMatRelVO
   */
  public CustMatRelVO getCachecustmatervo() {
    return this.cachecustmatervo;
  }

  /**
   * 
   * @param cachecustmatervo
   */
  public void setCachecustmatervo(CustMatRelVO cachecustmatervo) {
    this.cachecustmatervo = cachecustmatervo;
  }

  private static final String[] HEAD_NOEDIT = {
    CustMatRelHVO.PK_CUSTBASECLASS, CustMatRelHVO.PK_CUSTOMER
  };

  private CustMatRelOrgPanel orgPanel;

  private CustMatRelModelDataManager dataManager;

  public CustMatRelModelDataManager getDataManager() {
    return this.dataManager;
  }

  public void setDataManager(CustMatRelModelDataManager dataManager) {
    this.dataManager = dataManager;
  }

  public CustMatRelOrgPanel getOrgPanel() {
    if (null == this.orgPanel) {
      this.orgPanel = this.createDefaultOrgPanel();
    }
    return this.orgPanel;
  }

  public void setOrgPanel(CustMatRelOrgPanel orgPanel) {
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
          CustMatRelBVO.PK_MATERIALSALECLASS);
    }
    else {
      this.getBillCardPanel().hideBodyTableCol(
          CustMatRelBVO.PK_MATERIALBASECLASS);
    }

    if (BaseSaleClassUtil.isCustBaseClass(pk_group)) {
      this.getBillCardPanel().hideBodyTableCol(CustMatRelBVO.PK_CUSTSALECLASS);
      this.getBillCardPanel().hideHeadItem(new String[] {
        CustMatRelHVO.PK_CUSTSALECLASS
      });
    }
    else {
      this.getBillCardPanel().hideBodyTableCol(CustMatRelBVO.PK_CUSTBASECLASS);
      this.getBillCardPanel().hideHeadItem(new String[] {
        CustMatRelHVO.PK_CUSTBASECLASS
      });
    }
    
    if (BaseSaleClassUtil.isMarBaseClass(pk_group)) {
      this.getBillCardPanel().hideHeadItem(new String[] {
        CustMatRelHVO.PK_MATERIALSALECLASS
      });
    }
    else {
      this.getBillCardPanel().hideHeadItem(new String[] {
        CustMatRelHVO.PK_MATERIALBASECLASS
      });
    }
  }

  @Override
  public void initUI() {
    super.initUI();
    this.add(this.getOrgPanel(), BorderLayout.NORTH);
    initEditEnable();
  }

  /**
   * 销售发票卡片界面初始化能否编辑属性
   */
  private void initEditEnable() {
	  this.getOrgPanel().setEnabled(false);

    // 表头可编辑项
		for (String key : CardForm.HEAD_NOEDIT) {
			BillItem headitem = this.getBillCardPanel().getHeadTailItem(key);
			if (null != headitem) {
				headitem.setEdit(true);
				
	// 这六个字段不可编辑 add by quyt 20141021
				this.getBillCardPanel()
						.getHeadItem(CustMatRelHVO.PK_CUSTBASECLASS)
						.setEdit(false);
				this.getBillCardPanel()
						.getHeadItem(CustMatRelHVO.PK_CUSTSALECLASS)
						.setEdit(false);
				this.getBillCardPanel().getHeadItem(CustMatRelHVO.PK_CUSTOMER)
						.setEdit(false);
				this.getBillCardPanel()
						.getHeadItem(CustMatRelHVO.PK_MATERIALBASECLASS)
						.setEdit(false);
				this.getBillCardPanel()
						.getHeadItem(CustMatRelHVO.PK_MATERIALSALECLASS)
						.setEdit(false);
				this.getBillCardPanel().getHeadItem(CustMatRelHVO.PK_MATERIAL)
						.setEdit(false);
	// end
			}
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
