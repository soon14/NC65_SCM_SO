package nc.pubimpl.so.m4331.pfxx;

import nc.impl.pubapp.pattern.data.bill.BillQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillConcurrentTool;
import nc.impl.so.m4331.action.maintain.DeliveryInsertAction;
import nc.impl.so.m4331.action.maintain.DeliveryUpdateAction;
import nc.itf.scmpub.reference.uap.pf.PfServiceScmUtil;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m4331.entity.DeliveryVO;
import nc.vo.so.pub.SOConstant;

/**
 * <b> 在此处简要描述此类的功能 </b>
 * 
 * <p>
 * 在此处添加此类的描述信息
 * </p>
 * 
 * @author ufsoft
 * @version Your Project V60
 */
public class M4331PfxxPlugin extends nc.pubimpl.so.pfxx.AbstractSOPfxxPlugin {
  @Override
  protected AggregatedValueObject insert(AggregatedValueObject vo) {
    DeliveryVO[] insertvo = new DeliveryVO[] {
      (DeliveryVO) vo
    };
    DeliveryVO[] ret =
        (DeliveryVO[]) PfServiceScmUtil.processBatch(SOConstant.WRITE,
            SOBillType.Delivery.getCode(), insertvo, null, null);
    if (null == ret || ret.length == 0) {
      return null;
    }
    return ret[0];
  }

  @Override
  protected AggregatedValueObject update(AggregatedValueObject vo, String vopk) {
    DeliveryVO[] updatevo = new DeliveryVO[] {
      (DeliveryVO) vo
    };
    DeliveryVO[] retvos =
        (DeliveryVO[]) PfServiceScmUtil.processBatch(SOConstant.WRITE,
            SOBillType.Delivery.getCode(), updatevo, null, null);
    if (null == retvos || retvos.length == 0) {
      return null;
    }
    return retvos[0];
  }
}
