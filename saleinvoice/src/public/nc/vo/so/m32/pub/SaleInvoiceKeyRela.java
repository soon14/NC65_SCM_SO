package nc.vo.so.m32.pub;

import nc.vo.so.m32.entity.SaleInvoiceHVO;
import nc.vo.so.pub.keyvalue.SOKeyRela;

/**
 * 销售发票keyrela
 * 
 * @since 6.0
 * @version 2012-3-29 上午09:24:12
 * @author 么贵敬
 */
public class SaleInvoiceKeyRela extends SOKeyRela {

  @Override
  public String getCsettleorgidKey() {
    return SaleInvoiceHVO.PK_ORG;
  }

  @Override
  public String getCsettleorgvidKey() {
    return SaleInvoiceHVO.PK_ORG_V;
  }

}
