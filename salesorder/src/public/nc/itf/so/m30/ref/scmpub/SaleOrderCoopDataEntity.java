package nc.itf.so.m30.ref.scmpub;

import nc.vo.scmf.coop.entity.AbstractCoopDataEntity;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;

public class SaleOrderCoopDataEntity extends
    AbstractCoopDataEntity<SaleOrderVO> {

  private static final long serialVersionUID = 1L;

  private String billtype;

  public SaleOrderCoopDataEntity(SaleOrderVO bill) {
    super(bill);
    this.setBillType(SOBillType.Order.getCode());
  }

  @Override
  public String getBillType() {
    return this.billtype;
  }

  @Override
  public String getCoopInnerCustomerPK() {
    return this.getBill().getParentVO().getCcustomerid();
  }

  @Override
  public String getCoopPkOrg() {
    return this.getBill().getParentVO().getPk_org();
  }

  @Override
  public String getFinancialOrgPK() {
    return this.getBill().getChildrenVO()[0].getCsettleorgid();
  }

  @Override
  public String getPk_transtype() {
    return this.getBill().getParentVO().getCtrantypeid();
  }

  @Override
  public String getSrcBodyPKFieldName() {
    return SaleOrderBVO.CSRCBID;
  }

  @Override
  public String getSrcHeadPKFieldName() {
    return SaleOrderBVO.CSRCID;
  }

  @Override
  public String getTransType() {
    return this.getBill().getParentVO().getVtrantypecode();
  }

  @Override
  public void setBillType(String billType) {
    this.billtype = billType;
  }

  @Override
  public void setCoopInnerCustomerPK(String coopInnerCustomerPK) {
    // 客户
    this.getBill().getParentVO().setCcustomerid(coopInnerCustomerPK);
    // 开票客户
    this.getBill().getParentVO().setCinvoicecustid(coopInnerCustomerPK);
    SaleOrderBVO[] bvos = this.getBill().getChildrenVO();
    for (SaleOrderBVO bvo : bvos) {
      // 收货客户
      bvo.setCreceivecustid(coopInnerCustomerPK);
    }
  }

  @Override
  public void setCoopPkOrg(String coopPkOrg) {
    this.getBill().getParentVO().setPk_org(coopPkOrg);
    SaleOrderBVO[] bvos = this.getBill().getChildrenVO();
    for (SaleOrderBVO bvo : bvos) {
      // 收货客户
      bvo.setPk_org(coopPkOrg);
    }
  }

  @Override
  public void setFinancialOrgPK(String financialOrgPK) {
    SaleOrderBVO[] bvos = this.getBill().getChildrenVO();
    for (SaleOrderBVO bvo : bvos) {
      // 结算财务组织
      bvo.setCsettleorgid(financialOrgPK);
      // 应收组织
      bvo.setCarorgid(financialOrgPK);
    }
  }

  @Override
  public void setPk_transtype(String pk_transtype) {
    SaleOrderHVO hvo = this.getBill().getParentVO();
    hvo.setCtrantypeid(pk_transtype);
  }

  @Override
  public void setTransType(String transType) {
    this.getBill().getParentVO().setVtrantypecode(transType);
  }

@Override
public String getSrcDocID() {
	// TODO Auto-generated method stub
	return null;
}
}
