package nc.pubimpl.so.m4310.pfxx;

import nc.bs.framework.common.NCLocator;
import nc.itf.so.salequotation.ISalequotationService;
import nc.pubimpl.so.pfxx.AbstractSOPfxxPlugin;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.salequotation.entity.AggSalequotationHVO;

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
public class M4310PfxxPlugin extends AbstractSOPfxxPlugin {
  @Override
  protected AggregatedValueObject insert(AggregatedValueObject vo) {

    AggSalequotationHVO[] insertvo = new AggSalequotationHVO[] {
      (AggSalequotationHVO) vo
    };
    ISalequotationService srv =
        NCLocator.getInstance().lookup(ISalequotationService.class);
    // SalequoMaintainBP insertact = new SalequoMaintainBP();
    AggSalequotationHVO[] retvos = null;
    try {
      retvos = srv.saveBase(insertvo);
    }
    catch (Exception ex) {
      ExceptionUtils.wrappException(ex);
    }

    if (null == retvos || retvos.length == 0) {
      return null;
    }
    return retvos[0];
  }

  @Override
  protected AggregatedValueObject update(AggregatedValueObject vo, String vopk) {

    AggSalequotationHVO[] updatevo = new AggSalequotationHVO[] {
      (AggSalequotationHVO) vo
    };
    ISalequotationService srv =
        NCLocator.getInstance().lookup(ISalequotationService.class);
    // SalequoMaintainBP insertact = new SalequoMaintainBP();
    AggSalequotationHVO[] retvos = null;
    try {
      retvos = srv.saveBase(updatevo);
    }
    catch (Exception ex) {
      ExceptionUtils.wrappException(ex);
    }
    if (null == retvos || retvos.length == 0) {
      return null;
    }
    return retvos[0];
  }
}
