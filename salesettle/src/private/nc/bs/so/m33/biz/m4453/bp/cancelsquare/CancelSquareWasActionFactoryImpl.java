package nc.bs.so.m33.biz.m4453.bp.cancelsquare;

import nc.bs.so.m33.biz.m4453.bp.cancelsquare.cancelar.CancelARIncomeFor4453BP;
import nc.bs.so.m33.biz.m4453.bp.cancelsquare.cancelar.CancelARRushIncomeFor4453BP;
import nc.bs.so.m33.biz.m4453.bp.cancelsquare.cancelia.CancelIACostFor4453BP;
import nc.bs.so.m33.biz.m4453.bp.cancelsquare.cancelia.CancelIARegisterFor4453BP;
import nc.itf.so.m33.biz.canclesquare.ICancelSquareAction;
import nc.itf.so.m33.biz.canclesquare.ICancelSquareActionFactory;
import nc.vo.so.m33.enumeration.SquareType;
import nc.vo.so.m33.m4453.entity.SquareWasDetailVO;

public class CancelSquareWasActionFactoryImpl implements
    ICancelSquareActionFactory<SquareWasDetailVO> {

  @Override
  public ICancelSquareAction<SquareWasDetailVO> createCancelSquareAction(
      SquareType type) {

    ICancelSquareAction<SquareWasDetailVO> cancelAct = null;

    if (SquareType.SQUARETYPE_AR.equalsValue(type.getIntegerValue())) {
      cancelAct = new CancelARIncomeFor4453BP();
    }
    else if (SquareType.SQUARETYPE_ARRUSH.equalsValue(type.getIntegerValue())) {
      cancelAct = new CancelARRushIncomeFor4453BP();
    }
    else if (SquareType.SQUARETYPE_IA.equalsValue(type.getIntegerValue())) {
      cancelAct = new CancelIACostFor4453BP();
    }
    else if (SquareType.SQUARETYPE_REG_DEBIT
        .equalsValue(type.getIntegerValue())) {
      cancelAct = new CancelIARegisterFor4453BP();
    }

    // TODO 增加其他单据结算动作

    return cancelAct;

  }

}
