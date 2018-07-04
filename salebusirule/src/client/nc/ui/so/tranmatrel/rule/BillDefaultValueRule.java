package nc.ui.so.tranmatrel.rule;

import nc.itf.scmpub.reference.uap.org.OrgUnitPubService;
import nc.ui.so.base.rule.IBillRule;
import nc.ui.uif2.model.AbstractAppModel;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.so.base.entity.AllowSale;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.tranmatrel.entity.TranMatRelHVO;

public class BillDefaultValueRule implements IBillRule {

  @Override
  public void process(IKeyValue keyvalue, AbstractAppModel model) {
    String pk_org = model.getContext().getPk_org();
    keyvalue.setHeadValue(TranMatRelHVO.PK_ORG, pk_org);
    String pk_org_v = OrgUnitPubService.getNewVIDByOrgID(pk_org);
    keyvalue.setHeadValue(TranMatRelHVO.PK_ORG, pk_org);
    keyvalue.setHeadValue(TranMatRelHVO.PK_ORG_V, pk_org_v);
    keyvalue.setHeadValue(TranMatRelHVO.PK_GROUP, model.getContext()
        .getPk_group());
    keyvalue.setHeadValue(TranMatRelHVO.ALLOWSALE,
        AllowSale.ALLOW_SALE.getIntegerValue());
    keyvalue.setHeadValue(TranMatRelHVO.APPLYLOWER, UFBoolean.FALSE);
  }
}
