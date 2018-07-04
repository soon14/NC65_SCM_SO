package nc.vo.so.pub.para;

import nc.itf.bd.pub.IBDMetaDataIDConst;
import nc.itf.scmpub.reference.uap.bd.accesor.GeneralAccessor;

public class SaleOrgPriorityCode extends LevelPriorityCode {

  public SaleOrgPriorityCode(
      String attrvalue, String pk_org) {
    super(attrvalue, pk_org);
  }

  @Override
  protected int getLevel(String value, String pk_org) {
    return GeneralAccessor.getBDNodeLevel(IBDMetaDataIDConst.ORGVIEW, pk_org,
        value);
  }

}
