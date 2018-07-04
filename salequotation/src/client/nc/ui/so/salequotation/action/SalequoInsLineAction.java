package nc.ui.so.salequotation.action;

import nc.vo.so.salequotation.entity.SalequotationBVO;
import nc.vo.so.salequotation.entity.SalequotationHVO;

import nc.itf.scmpub.reference.uap.org.OrgUnitPubService;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillModel;
import nc.ui.pubapp.uif2app.actions.BodyInsertLineAction;

public class SalequoInsLineAction extends BodyInsertLineAction {

  /**
   * 
   */
  private static final long serialVersionUID = -1492965066669705508L;

  @Override
  protected void afterLineInsert(int index) {
    super.afterLineInsert(index);
    BillCardPanel cp = this.getCardPanel();
    BillModel billModel = cp.getBillModel();
    String pk_group = this.getModel().getContext().getPk_group();
    billModel.setValueAt(pk_group, index, SalequotationBVO.PK_GROUP);
    String pk_org = this.getModel().getContext().getPk_org();
    billModel.setValueAt(pk_org, index, SalequotationBVO.PK_ORG);
    String pk_org_v = OrgUnitPubService.getNewVIDByOrgID(pk_org);
    billModel.setValueAt(pk_org_v, index, SalequotationBVO.PK_ORG_V);
    // Õûµ¥ÕÛ¿Û
    billModel.setValueAt(cp.getHeadItem(SalequotationHVO.NDISCOUNT)
        .getValueObject(), index, SalequotationBVO.NDISCOUNTRATE);
    billModel.loadEditRelationItemValue(index, SalequotationBVO.PK_ORG);
    billModel.loadEditRelationItemValue(index, SalequotationBVO.PK_ORG_V);
  }
}
