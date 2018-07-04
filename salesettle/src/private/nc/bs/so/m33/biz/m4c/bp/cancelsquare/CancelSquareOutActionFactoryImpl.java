package nc.bs.so.m33.biz.m4c.bp.cancelsquare;

import nc.bs.so.m33.biz.m4c.bp.cancelsquare.cancelar.CancelARIncomeFor4CBP;
import nc.bs.so.m33.biz.m4c.bp.cancelsquare.cancelar.CancelARRushIncomeFor4CBP;
import nc.bs.so.m33.biz.m4c.bp.cancelsquare.cancelar.CancelETIncomeFor4CBP;
import nc.bs.so.m33.biz.m4c.bp.cancelsquare.cancelia.CancelIACostFor4CBP;
import nc.bs.so.m33.biz.m4c.bp.cancelsquare.cancelia.CancelIARegisterCreditFor4COutRushBP;
import nc.bs.so.m33.biz.m4c.bp.cancelsquare.cancelia.CancelIARegisterDebitFor4CBP;
import nc.bs.so.m33.biz.m4c.bp.outrush.CancelOutRushFor4CBP;
import nc.itf.so.m33.biz.canclesquare.ICancelSquareAction;
import nc.itf.so.m33.biz.canclesquare.ICancelSquareActionFactory;
import nc.vo.so.m33.enumeration.SquareType;
import nc.vo.so.m33.m4c.entity.SquareOutDetailVO;

public class CancelSquareOutActionFactoryImpl implements
    ICancelSquareActionFactory<SquareOutDetailVO> {

  @Override
  public ICancelSquareAction<SquareOutDetailVO> createCancelSquareAction(
      SquareType type) {

    ICancelSquareAction<SquareOutDetailVO> cancelAct = null;

    if (SquareType.SQUARETYPE_AR.equalsValue(type.getIntegerValue())) {
      cancelAct = new CancelARIncomeFor4CBP();
    }
    else if (SquareType.SQUARETYPE_IA.equalsValue(type.getIntegerValue())) {
      cancelAct = new CancelIACostFor4CBP();
    }
    else if (SquareType.SQUARETYPE_REG_DEBIT
        .equalsValue(type.getIntegerValue())) {
      cancelAct = new CancelIARegisterDebitFor4CBP();
    }
    else if (SquareType.SQUARETYPE_REG_CREDIT.equalsValue(type
        .getIntegerValue())) {
      cancelAct = new CancelIARegisterCreditFor4COutRushBP();
    }
    else if (SquareType.SQUARETYPE_ET.equalsValue(type.getIntegerValue())) {
      cancelAct = new CancelETIncomeFor4CBP();
    }
    else if (SquareType.SQUARETYPE_ARRUSH.equalsValue(type.getIntegerValue())) {
      cancelAct = new CancelARRushIncomeFor4CBP();
    }
    else if (SquareType.SQUARETYPE_OUTRUSH.equalsValue(type.getIntegerValue())) {
      cancelAct = new CancelOutRushFor4CBP();
    }

    // TODO 增加其他单据结算动作

    return cancelAct;

  }
}
