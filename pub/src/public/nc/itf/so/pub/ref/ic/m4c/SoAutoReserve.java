package nc.itf.so.pub.ref.ic.m4c;

import nc.bs.framework.common.NCLocator;
import nc.pubitf.ic.reserve.AutoReserve;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;

public class SoAutoReserve {

  private SoAutoReserve() {
    super();
  }

  /**
   * 方法功能描述：针对需求单据的自动预留。 <b>参数说明</b>
   * 
   * @param billtype
   *          单据类型
   * @param vo
   *          修改后的单据
   * @return <p>
   * @author 祝会征
   * @time 2010-5-2 下午03:48:13
   */
  public static void autoReserveForReqBill(String billtype,
      AggregatedValueObject vo) throws BusinessException {
    AutoReserve auto = NCLocator.getInstance().lookup(AutoReserve.class);
    auto.autoReserveForReqBill(billtype, vo);
  }
}
