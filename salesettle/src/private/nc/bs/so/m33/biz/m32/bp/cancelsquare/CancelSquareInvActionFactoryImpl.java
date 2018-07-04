package nc.bs.so.m33.biz.m32.bp.cancelsquare;

import nc.bs.so.m33.biz.m32.bp.cancelsquare.cancelar.CancelARIncomeFor32BP;
import nc.bs.so.m33.biz.m32.bp.cancelsquare.cancelar.CancelARRushIncomeFor32BP;
import nc.bs.so.m33.biz.m32.bp.cancelsquare.cancelia.CancelIACostFor32BP;
import nc.bs.so.m33.biz.m32.bp.cancelsquare.cancelia.CancelIARegisterCreditFor32BP;
import nc.itf.so.m33.biz.canclesquare.ICancelSquareAction;
import nc.itf.so.m33.biz.canclesquare.ICancelSquareActionFactory;
import nc.vo.so.m33.enumeration.SquareType;
import nc.vo.so.m33.m32.entity.SquareInvDetailVO;

public class CancelSquareInvActionFactoryImpl implements
    ICancelSquareActionFactory<SquareInvDetailVO> {

  @Override
  public ICancelSquareAction<SquareInvDetailVO> createCancelSquareAction(
      SquareType type) {

    ICancelSquareAction<SquareInvDetailVO> cancelAct = null;

    if (SquareType.SQUARETYPE_AR.equalsValue(type.getIntegerValue())) {
      cancelAct = new CancelARIncomeFor32BP();
    }
    else if (SquareType.SQUARETYPE_IA.equalsValue(type.getIntegerValue())) {
      cancelAct = new CancelIACostFor32BP();
    }
    else if (SquareType.SQUARETYPE_REG_CREDIT.equalsValue(type
        .getIntegerValue())) {
      cancelAct = new CancelIARegisterCreditFor32BP();
    }
    else if (SquareType.SQUARETYPE_ARRUSH.equalsValue(type.getIntegerValue())) {
      cancelAct = new CancelARRushIncomeFor32BP();
    }

    // TODO 增加其他单据结算动作

    return cancelAct;

  }

}
