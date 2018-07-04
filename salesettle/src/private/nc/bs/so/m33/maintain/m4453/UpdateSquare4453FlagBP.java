package nc.bs.so.m33.maintain.m4453;

import nc.vo.pub.lang.UFBoolean;
import nc.vo.so.m33.enumeration.SquareType;
import nc.vo.so.m33.m4453.entity.SquareWasBVO;
import nc.vo.so.m33.m4453.entity.SquareWasHVO;
import nc.vo.so.m33.m4453.entity.SquareWasVO;

/**
 * 仅在配置消息驱动时调用，手工结算不调用
 * 更新销售结算单结算标志BP
 * 用结算单数量设置本次结算数量
 * 
 * @author zhangcheng
 * 
 */
public class UpdateSquare4453FlagBP {

  /**
   * 销售出库单推式生成结算单的时候更新表体结算标志位
   * 
   * @param sqvos
   */
  public void updateSquareBFlagFor4453Push33(SquareWasVO[] sqvos) {

    // 设置相应标志位
    for (SquareWasVO vo : sqvos) {
      vo.getParentVO().setBautosquareincome(UFBoolean.FALSE);
      vo.getParentVO().setBautosquarecost(UFBoolean.FALSE);
      for (SquareWasBVO bvo : vo.getChildrenVO()) {
        bvo.setFpreartype((Integer) SquareType.SQUARETYPE_ET.value());
        bvo.setFpreiatype((Integer) SquareType.SQUARETYPE_REG_DEBIT.value());
      }
    }

  }

  /**
   * 结算单差额传应收更新标志位
   * 
   * @param sqvos
   */
  public void updateSquareBFlagForAdjustIncome(SquareWasVO[] sqvos) {

    // 设置相应标志位
    for (SquareWasVO vo : sqvos) {
      vo.getParentVO().setBautosquareincome(UFBoolean.TRUE);
      for (SquareWasBVO bvo : vo.getChildrenVO()) {
        bvo.setNthisnum(bvo.getNnum());
        bvo.setFpreartype((Integer) SquareType.SQUARETYPE_BALANCEAR.value());
      }
    }

    new UpdateSquare4453FieldsBP().updateFields(sqvos, new String[] {
      SquareWasHVO.BAUTOSQUAREINCOME
    }, new String[] {
      SquareWasBVO.FPREARTYPE
    });

  }

  /**
   * 结算单自动收入结算更新标志位
   * 
   * @param sqvos
   */
  public void updateSquareBFlagForAutoARIncome(SquareWasVO[] sqvos) {

    // 设置相应标志位
    for (SquareWasVO vo : sqvos) {
      vo.getParentVO().setBautosquareincome(UFBoolean.TRUE);
      for (SquareWasBVO bvo : vo.getChildrenVO()) {
        bvo.setNthisnum(bvo.getNnum());
        bvo.setFpreartype((Integer) SquareType.SQUARETYPE_AR.value());
      }
    }

    new UpdateSquare4453FieldsBP().updateFields(sqvos, new String[] {
      SquareWasHVO.BAUTOSQUAREINCOME
    }, new String[] {
      SquareWasBVO.FPREARTYPE
    });

  }

  /**
   * 结算单自动暂估应收更新标志位
   * 
   * @param sqvos
   */
  public void updateSquareBFlagForAutoETIncome(SquareWasVO[] sqvos) {

    // 设置相应标志位
    for (SquareWasVO vo : sqvos) {
      vo.getParentVO().setBautosquareincome(UFBoolean.TRUE);
      for (SquareWasBVO bvo : vo.getChildrenVO()) {
        bvo.setNthisnum(bvo.getNnum());
        bvo.setFpreartype((Integer) SquareType.SQUARETYPE_ET.value());
      }
    }

    new UpdateSquare4453FieldsBP().updateFields(sqvos, new String[] {
      SquareWasHVO.BAUTOSQUAREINCOME
    }, new String[] {
      SquareWasBVO.FPREARTYPE
    });

  }

  /**
   * 结算单自动成本结算更新标志位
   * 
   * @param sqvos
   */
  public void updateSquareBFlagForAutoIACost(SquareWasVO[] sqvos) {

    // 设置相应标志位
    for (SquareWasVO vo : sqvos) {
      vo.getParentVO().setBautosquarecost(UFBoolean.TRUE);
      for (SquareWasBVO bvo : vo.getChildrenVO()) {
        bvo.setNthisnum(bvo.getNnum());
        bvo.setFpreiatype((Integer) SquareType.SQUARETYPE_IA.value());
      }
    }

    new UpdateSquare4453FieldsBP().updateFields(sqvos, new String[] {
      SquareWasHVO.BAUTOSQUARECOST
    }, new String[] {
      SquareWasBVO.FPREIATYPE
    });

  }

  /**
   * 结算单自动计入发出商品更新标志位
   * 
   * @param sqvos
   */
  public void updateSquareBFlagForAutoIARegister(SquareWasVO[] sqvos) {

    // 设置相应标志位
    for (SquareWasVO vo : sqvos) {
      vo.getParentVO().setBautosquarecost(UFBoolean.TRUE);
      for (SquareWasBVO bvo : vo.getChildrenVO()) {
        bvo.setNthisnum(bvo.getNnum());
        bvo.setFpreiatype((Integer) SquareType.SQUARETYPE_REG_DEBIT.value());
      }
    }

    new UpdateSquare4453FieldsBP().updateFields(sqvos, new String[] {
      SquareWasHVO.BAUTOSQUARECOST
    }, new String[] {
      SquareWasBVO.FPREIATYPE
    });

  }

  /**
   * 结算单手工收入结算更新标志位
   * 
   * @param sqvos
   */
  public void updateSquareBFlagForManualARIncome(SquareWasVO[] sqvos) {

    // 设置相应标志位
    for (SquareWasVO vo : sqvos) {
      vo.getParentVO().setBautosquareincome(UFBoolean.FALSE);
      for (SquareWasBVO bvo : vo.getChildrenVO()) {
        bvo.setFpreartype((Integer) SquareType.SQUARETYPE_AR.value());
      }
    }

    new UpdateSquare4453FieldsBP().updateFields(sqvos, new String[] {
      SquareWasHVO.BAUTOSQUAREINCOME
    }, new String[] {
      SquareWasBVO.FPREARTYPE
    });

  }

  /**
   * 结算单手工成本结算更新标志位
   * 
   * @param sqvos
   */
  public void updateSquareBFlagForManualIACost(SquareWasVO[] sqvos) {

    // 设置相应标志位
    for (SquareWasVO vo : sqvos) {
      vo.getParentVO().setBautosquarecost(UFBoolean.FALSE);
      for (SquareWasBVO bvo : vo.getChildrenVO()) {
        bvo.setFpreiatype((Integer) SquareType.SQUARETYPE_IA.value());
      }
    }

    new UpdateSquare4453FieldsBP().updateFields(sqvos, new String[] {
      SquareWasHVO.BAUTOSQUARECOST
    }, new String[] {
      SquareWasBVO.FPREIATYPE
    });

  }

}
