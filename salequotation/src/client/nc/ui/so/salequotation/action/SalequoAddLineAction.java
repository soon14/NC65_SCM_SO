package nc.ui.so.salequotation.action;

import nc.itf.scmpub.reference.uap.org.OrgUnitPubService;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillModel;
import nc.ui.pubapp.uif2app.actions.BodyAddLineAction;
import nc.vo.pub.lang.UFDouble;
import nc.vo.so.salequotation.entity.SalequotationBVO;
import nc.vo.so.salequotation.entity.SalequotationHVO;

public class SalequoAddLineAction extends BodyAddLineAction {

  /**
   * 
   */
  private static final long serialVersionUID = -1608752155024085124L;

  @Override
  public void doAction() {
    // TODO Auto-generated method stub
    super.doAction();

  }

  @Override
  protected void afterLineInsert(int index) {
    super.afterLineInsert(index);
    BillCardPanel cp = this.getCardPanel();
    BillModel billModel = cp.getBillModel();
    String pk_group = this.getModel().getContext().getPk_group();
    billModel.setValueAt(pk_group, index, SalequotationBVO.PK_GROUP);
    // 销售组织
    String pk_org = this.getModel().getContext().getPk_org();
    if (pk_org != null) {
      String pk_org_vid = OrgUnitPubService.getNewVIDByOrgID(pk_org);
      billModel.setValueAt(pk_org_vid, index, SalequotationBVO.PK_ORG_V);
      billModel.loadEditRelationItemValue(index, SalequotationBVO.PK_ORG_V);
    }
    // 整单折扣
    billModel.setValueAt(cp.getHeadItem(SalequotationHVO.NDISCOUNT)
        .getValueObject(), index, SalequotationBVO.NDISCOUNTRATE);
    billModel.setValueAt(new UFDouble(100), index,
        SalequotationBVO.NITEMDISCOUNTRATE);
  }
}