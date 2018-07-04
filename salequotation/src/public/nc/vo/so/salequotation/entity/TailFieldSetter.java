package nc.vo.so.salequotation.entity;

import nc.bs.framework.common.ITimeService;
import nc.bs.framework.common.InvocationInfoProxy;
import nc.bs.framework.common.NCLocator;
import nc.vo.pub.lang.UFDateTime;

public class TailFieldSetter {
  private TailFieldSetter() {
    // do nothing
  }

  public static void setTailDefaultValue(AggSalequotationHVO[] vos,
      boolean isAdd) {
    InvocationInfoProxy proxy = InvocationInfoProxy.getInstance();
    if (vos == null) {
      return;
    }
    for (AggSalequotationHVO vo : vos) {
      if (vo == null) {
        continue;
      }
      SalequotationHVO hvo = (SalequotationHVO) vo.getParent();
      if (hvo != null) {
        UFDateTime serverTime =
            NCLocator.getInstance().lookup(ITimeService.class).getUFDateTime();
        if (isAdd) {
          hvo.setDbilldate(serverTime);
          // hvo.setOperator(proxy.getUserId());
          hvo.setBillmaker(proxy.getUserId());
          hvo.setCreator(proxy.getUserId());
        }
        else {
          hvo.setApprover(null);
          hvo.setDauditdate(null);
          hvo.setFstatusflag(Integer.valueOf(BillStatusEnum.C_FREE));
          hvo.setModifiedtime(serverTime);
          hvo.setModifier(proxy.getUserId());
        }
      }
    }
  }
}
