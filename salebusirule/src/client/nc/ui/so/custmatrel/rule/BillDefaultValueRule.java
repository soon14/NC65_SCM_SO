package nc.ui.so.custmatrel.rule;

import nc.itf.scmpub.reference.uap.org.OrgUnitPubService;
import nc.ui.so.base.rule.IBillRule;
import nc.ui.uif2.model.AbstractAppModel;
import nc.vo.so.base.entity.AllowSale;
import nc.vo.so.custmatrel.entity.CustMatRelHVO;
import nc.vo.so.pub.keyvalue.IKeyValue;

public class BillDefaultValueRule implements IBillRule {

  @Override
  public void process(IKeyValue keyvalue, AbstractAppModel model) {
    String pk_org = model.getContext().getPk_org();
    keyvalue.setHeadValue(CustMatRelHVO.PK_ORG, pk_org);
    String pk_org_v = OrgUnitPubService.getNewVIDByOrgID(pk_org);
    keyvalue.setHeadValue(CustMatRelHVO.PK_ORG, pk_org);
    keyvalue.setHeadValue(CustMatRelHVO.PK_ORG_V, pk_org_v);
    keyvalue.setHeadValue(CustMatRelHVO.PK_GROUP, model.getContext()
        .getPk_group());
    keyvalue.setHeadValue(CustMatRelHVO.ALLOWSALE,
        AllowSale.ALLOW_SALE.getIntegerValue());
  }
}
