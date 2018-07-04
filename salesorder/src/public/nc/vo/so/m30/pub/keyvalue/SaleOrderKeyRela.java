package nc.vo.so.m30.pub.keyvalue;

import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.pub.keyvalue.SOKeyRela;

public class SaleOrderKeyRela extends SOKeyRela {

  @Override
  public String getCsaleorgidKey() {
    return SaleOrderHVO.PK_ORG;
  }

  @Override
  public String getCsaleorgvidKey() {
    return SaleOrderHVO.PK_ORG_V;
  }
}
