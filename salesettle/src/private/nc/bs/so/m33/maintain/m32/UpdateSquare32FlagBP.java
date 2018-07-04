package nc.bs.so.m33.maintain.m32;

import nc.vo.pub.lang.UFBoolean;
import nc.vo.so.m33.enumeration.SquareType;
import nc.vo.so.m33.m32.entity.SquareInvBVO;
import nc.vo.so.m33.m32.entity.SquareInvHVO;
import nc.vo.so.m33.m32.entity.SquareInvVO;

/**
 * 仅在配置消息驱动时调用，手工结算不调用
 * 更新销售结算单结算标志BP
 * 用结算单数量设置本次结算数量
 * 
 * @author zhangcheng
 * 
 */
public class UpdateSquare32FlagBP {

  /**
   * 销售出库单推式生成结算单的时候更新表体结算标志位
   * 
   * @param sqvos
   */
  public void updateSquareBFlagFor4CPush33(SquareInvVO[] sqvos) {

    // 设置相应标志位
    for (SquareInvVO vo : sqvos) {
      vo.getParentVO().setBautosquareincome(UFBoolean.FALSE);
      vo.getParentVO().setBautosquarecost(UFBoolean.FALSE);
      for (SquareInvBVO bvo : vo.getChildrenVO()) {
        bvo.setFpreartype((Integer) SquareType.SQUARETYPE_ET.value());
        bvo.setFpreiatype((Integer) SquareType.SQUARETYPE_REG_CREDIT.value());
      }
    }

  }

  /**
   * 结算单差额传应收更新标志位
   * 
   * @param sqvos
   */
  public void updateSquareBFlagForAdjustIncome(SquareInvVO[] sqvos) {

    // 设置相应标志位
    for (SquareInvVO vo : sqvos) {
      vo.getParentVO().setBautosquareincome(UFBoolean.TRUE);
      for (SquareInvBVO bvo : vo.getChildrenVO()) {
        bvo.setNthisnum(bvo.getNnum());
        bvo.setFpreartype((Integer) SquareType.SQUARETYPE_BALANCEAR.value());
      }
    }

    new UpdateSquare32FieldsBP().updateFields(sqvos, new String[] {
      SquareInvHVO.BAUTOSQUAREINCOME
    }, new String[] {
      SquareInvBVO.FPREARTYPE
    });

  }

  /**
   * 结算单自动收入结算更新标志位
   * 
   * @param sqvos
   */
  public void updateSquareBFlagForAutoARIncome(SquareInvVO[] sqvos) {

    // 设置相应标志位
    for (SquareInvVO vo : sqvos) {
      vo.getParentVO().setBautosquareincome(UFBoolean.TRUE);
      for (SquareInvBVO bvo : vo.getChildrenVO()) {
        bvo.setNthisnum(bvo.getNnum());
        bvo.setFpreartype((Integer) SquareType.SQUARETYPE_AR.value());
      }
    }

    new UpdateSquare32FieldsBP().updateFields(sqvos, new String[] {
      SquareInvHVO.BAUTOSQUAREINCOME
    }, new String[] {
      SquareInvBVO.FPREARTYPE
    });

  }

  /**
   * 结算单自动暂估应收更新标志位
   * 
   * @param sqvos
   */
  public void updateSquareBFlagForAutoETIncome(SquareInvVO[] sqvos) {

    // 设置相应标志位
    for (SquareInvVO vo : sqvos) {
      vo.getParentVO().setBautosquareincome(UFBoolean.TRUE);
      for (SquareInvBVO bvo : vo.getChildrenVO()) {
        bvo.setNthisnum(bvo.getNnum());
        bvo.setFpreartype((Integer) SquareType.SQUARETYPE_ET.value());
      }
    }

    new UpdateSquare32FieldsBP().updateFields(sqvos, new String[] {
      SquareInvHVO.BAUTOSQUAREINCOME
    }, new String[] {
      SquareInvBVO.FPREARTYPE
    });

  }

  /**
   * 结算单自动成本结算更新标志位
   * 
   * @param sqvos
   */
  public void updateSquareBFlagForAutoIACost(SquareInvVO[] sqvos) {

    // 设置相应标志位
    for (SquareInvVO vo : sqvos) {
      vo.getParentVO().setBautosquarecost(UFBoolean.TRUE);
      for (SquareInvBVO bvo : vo.getChildrenVO()) {
        bvo.setNthisnum(bvo.getNnum());
        bvo.setFpreiatype((Integer) SquareType.SQUARETYPE_IA.value());
      }
    }

    new UpdateSquare32FieldsBP().updateFields(sqvos, new String[] {
      SquareInvHVO.BAUTOSQUARECOST
    }, new String[] {
      SquareInvBVO.FPREIATYPE
    });

  }

  /**
   * 结算单自动计入发出商品更新标志位
   * 
   * @param sqvos
   */
  public void updateSquareBFlagForAutoIARegister(SquareInvVO[] sqvos) {

    // 设置相应标志位
    for (SquareInvVO vo : sqvos) {
      vo.getParentVO().setBautosquarecost(UFBoolean.TRUE);
      for (SquareInvBVO bvo : vo.getChildrenVO()) {
        bvo.setNthisnum(bvo.getNnum());
        bvo.setFpreiatype((Integer) SquareType.SQUARETYPE_REG_CREDIT.value());
      }
    }

    new UpdateSquare32FieldsBP().updateFields(sqvos, new String[] {
      SquareInvHVO.BAUTOSQUARECOST
    }, new String[] {
      SquareInvBVO.FPREIATYPE
    });

  }

  /**
   * 结算单手工收入结算更新标志位
   * 
   * @param sqvos
   */
  public void updateSquareBFlagForManualARIncome(SquareInvVO[] sqvos) {

    // 设置相应标志位
    for (SquareInvVO vo : sqvos) {
      vo.getParentVO().setBautosquareincome(UFBoolean.FALSE);
      for (SquareInvBVO bvo : vo.getChildrenVO()) {
        bvo.setFpreartype((Integer) SquareType.SQUARETYPE_AR.value());
      }
    }

    new UpdateSquare32FieldsBP().updateFields(sqvos, new String[] {
      SquareInvHVO.BAUTOSQUAREINCOME
    }, new String[] {
      SquareInvBVO.FPREARTYPE
    });

  }

  /**
   * 结算单手工成本结算更新标志位
   * 
   * @param sqvos
   */
  public void updateSquareBFlagForManualIACost(SquareInvVO[] sqvos) {

    // 设置相应标志位
    for (SquareInvVO vo : sqvos) {
      vo.getParentVO().setBautosquarecost(UFBoolean.FALSE);
      for (SquareInvBVO bvo : vo.getChildrenVO()) {
        bvo.setFpreiatype((Integer) SquareType.SQUARETYPE_IA.value());
      }
    }

    new UpdateSquare32FieldsBP().updateFields(sqvos, new String[] {
      SquareInvHVO.BAUTOSQUARECOST
    }, new String[] {
      SquareInvBVO.FPREIATYPE
    });

  }

}
