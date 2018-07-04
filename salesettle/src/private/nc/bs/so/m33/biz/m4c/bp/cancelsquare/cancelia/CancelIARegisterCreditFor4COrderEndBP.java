package nc.bs.so.m33.biz.m4c.bp.cancelsquare.cancelia;

import nc.itf.so.m33.ref.ia.mi5.IAI5For4CServicesUtil;
import nc.vo.so.m33.m4c.entity.SquareOutDetailVO;
import nc.vo.so.pub.votools.SoVoTools;

public class CancelIARegisterCreditFor4COrderEndBP extends
    AbstractCancelIARegisterCreditFor4CBP {

  @Override
  protected void cancelIA(SquareOutDetailVO[] vos) {
    // 取消传发出商品
    IAI5For4CServicesUtil.deleteI5ForSOUnSquareEnd(
        SoVoTools.getVOsOnlyValues(vos, SquareOutDetailVO.CSQUAREBILLID),
        SoVoTools.getVOPKValues(vos));

  }

}
