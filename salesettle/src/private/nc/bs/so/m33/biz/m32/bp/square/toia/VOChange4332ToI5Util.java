package nc.bs.so.m33.biz.m32.bp.square.toia;

import nc.itf.scmpub.reference.uap.pf.PfServiceScmUtil;
import nc.itf.so.m33.ref.pcia.m4635.PCIA4635For32ServicesUtil;
import nc.vo.ia.mi5.entity.I5BillVO;
import nc.vo.scmpub.res.billtype.IABillType;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m33.m32.entity.SquareInvVO;

/**
 * 销售发票待结算单到销售成本结转单vo交换工具类
 * 主要负责：
 * 1.vo交换前对上游单据统一进行分单处理
 * 2.进行vo交换
 * 
 * @since 6.0
 * @version 2011-11-25 下午12:59:55
 * @author zhangcheng
 */
public class VOChange4332ToI5Util {

  /**
   * 按照IA的要求上游按照来源单据ID+库存组织+仓库进行分单，然后vo交换
   * 
   * @param sqvos
   * @return
   */
  public I5BillVO[] exchange(SquareInvVO[] sqvos) {

    SquareInvVO[] snewvos = PCIA4635For32ServicesUtil.splitSquareInvVOs(sqvos);

    // 进行vo交换
    I5BillVO[] i5vos =
        (I5BillVO[]) PfServiceScmUtil.executeVOChange(
            SOBillType.SquareInvoice.getCode(), IABillType.XSCBJZ.getCode(),
            snewvos);

    return i5vos;
  }

}
