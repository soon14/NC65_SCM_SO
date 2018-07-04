package nc.pubimpl.so.m32.pfxx;

import nc.itf.scmpub.reference.uap.pf.PfServiceScmUtil;
import nc.pubimpl.so.pfxx.AbstractSOPfxxPlugin;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m32.entity.SaleInvoiceVO;
import nc.vo.so.pub.SOConstant;

/**
 * 销售发票外部平台导入
 * 
 * @since 6.0
 * @version 2011-11-2 下午12:56:55
 * @author 么贵敬
 */
public class M32PfxxPlugin extends AbstractSOPfxxPlugin {

  @Override
  protected AggregatedValueObject insert(AggregatedValueObject vo) {
    SaleInvoiceVO[] insertvo = new SaleInvoiceVO[] {
      (SaleInvoiceVO) vo
    };
    SaleInvoiceVO[] retvos =
        (SaleInvoiceVO[]) PfServiceScmUtil.processBatch(SOConstant.WRITE,
            SOBillType.Invoice.getCode(), insertvo, null, null);
    if (null == retvos || retvos.length == 0) {
      return null;
    }
    return retvos[0];
  }

  @Override
  protected AggregatedValueObject update(AggregatedValueObject vo, String vopk) {
    SaleInvoiceVO[] updatevo = new SaleInvoiceVO[] {
      (SaleInvoiceVO) vo
    };
    SaleInvoiceVO[] retvos =
        (SaleInvoiceVO[]) PfServiceScmUtil.processBatch(SOConstant.WRITE,
            SOBillType.Invoice.getCode(), updatevo, null, null);
    if (null == retvos || retvos.length == 0) {
      return null;
    }
    return retvos[0];
  }
}
