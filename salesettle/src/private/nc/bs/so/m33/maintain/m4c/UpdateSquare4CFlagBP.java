package nc.bs.so.m33.maintain.m4c;

import java.util.Map;

import nc.vo.pub.lang.UFBoolean;
import nc.vo.so.m33.enumeration.SquareType;
import nc.vo.so.m33.m4c.entity.SquareOutBVO;
import nc.vo.so.m33.m4c.entity.SquareOutHVO;
import nc.vo.so.m33.m4c.entity.SquareOutVO;
import nc.vo.so.pub.util.AggVOUtil;
import nc.vo.so.pub.util.biz.SOBusiMDEnum;
import nc.vo.so.pub.util.biz.SOBusiUtil;

/**
 * 仅在配置消息驱动时调用，手工结算不调用
 * 更新销售结算单结算标志BP
 * 用结算单数量设置本次结算数量
 * 
 * @author zhangcheng
 * 
 */
public class UpdateSquare4CFlagBP {

  /**
   * 销售出库单推式生成结算单的时候更新表体结算标志位
   * 
   * @param sqvos
   */
  public void updateSquareBFlagFor4CPush33(SquareOutVO[] sqvos) {
    String[] bizs =
        AggVOUtil.getDistinctHeadFieldArray(sqvos, SquareOutHVO.CBIZTYPEID,
            String.class);
    Map<String, SOBusiMDEnum> map = new SOBusiUtil().querySOBusiType(bizs);

    // 设置相应标志位
    for (SquareOutVO vo : sqvos) {
      vo.getParentVO().setBautosquareincome(UFBoolean.FALSE);
      vo.getParentVO().setBautosquarecost(UFBoolean.FALSE);
      Integer ar = SquareType.SQUARETYPE_ET.getIntegerValue();
      Integer ia = SquareType.SQUARETYPE_REG_DEBIT.getIntegerValue();
      // 业务流程类型
      String biz = vo.getParentVO().getCbiztypeid();
      SOBusiMDEnum sobusitype = map.get(biz);
      if (SOBusiMDEnum.SOBUSIMDENUM_INVOICEFIRST == sobusitype
          || SOBusiMDEnum.SOBUSIMDENUM_INVOUTPARALLEL == sobusitype) {
        ar = SquareType.SQUARETYPE_NULL.getIntegerValue();
        ia = SquareType.SQUARETYPE_NULL.getIntegerValue();
      }
      for (SquareOutBVO bvo : vo.getChildrenVO()) {
        bvo.setFpreartype(ar);
        bvo.setFpreiatype(ia);
      }
    }

  }

  /**
   * 结算单差额传应收更新标志位
   * 
   * @param sqvos
   */
  public void updateSquareBFlagForAdjustIncome(SquareOutVO[] sqvos) {

    // 设置相应标志位
    for (SquareOutVO vo : sqvos) {
      vo.getParentVO().setBautosquareincome(UFBoolean.TRUE);
      for (SquareOutBVO bvo : vo.getChildrenVO()) {
        bvo.setNthisnum(bvo.getNnum());
        bvo.setFpreartype((Integer) SquareType.SQUARETYPE_BALANCEAR.value());
      }
    }

    new UpdateSquare4CFieldsBP().updateFields(sqvos, new String[] {
      SquareOutHVO.BAUTOSQUAREINCOME
    }, new String[] {
      SquareOutBVO.FPREARTYPE
    });

  }

  /**
   * 结算单自动收入结算更新标志位
   * 
   * @param sqvos
   */
  public void updateSquareBFlagForAutoARIncome(SquareOutVO[] sqvos) {

    // 设置相应标志位
    for (SquareOutVO vo : sqvos) {
      vo.getParentVO().setBautosquareincome(UFBoolean.TRUE);
      for (SquareOutBVO bvo : vo.getChildrenVO()) {
        bvo.setNthisnum(bvo.getNnum());
        bvo.setFpreartype((Integer) SquareType.SQUARETYPE_AR.value());
      }
    }

    new UpdateSquare4CFieldsBP().updateFields(sqvos, new String[] {
      SquareOutHVO.BAUTOSQUAREINCOME
    }, new String[] {
      SquareOutBVO.FPREARTYPE
    });

  }

  /**
   * 结算单自动暂估应收更新标志位
   * 
   * @param sqvos
   */
  public void updateSquareBFlagForAutoETIncome(SquareOutVO[] sqvos) {

    // 设置相应标志位
    for (SquareOutVO vo : sqvos) {
      vo.getParentVO().setBautosquareincome(UFBoolean.TRUE);
      for (SquareOutBVO bvo : vo.getChildrenVO()) {
        bvo.setNthisnum(bvo.getNnum());
        bvo.setFpreartype((Integer) SquareType.SQUARETYPE_ET.value());
      }
    }

    new UpdateSquare4CFieldsBP().updateFields(sqvos, new String[] {
      SquareOutHVO.BAUTOSQUAREINCOME
    }, new String[] {
      SquareOutBVO.FPREARTYPE
    });

  }

  /**
   * 结算单自动成本结算更新标志位
   * 
   * @param sqvos
   */
  public void updateSquareBFlagForAutoIACost(SquareOutVO[] sqvos) {

    // 设置相应标志位
    for (SquareOutVO vo : sqvos) {
      vo.getParentVO().setBautosquarecost(UFBoolean.TRUE);
      for (SquareOutBVO bvo : vo.getChildrenVO()) {
        bvo.setNthisnum(bvo.getNnum());
        bvo.setFpreiatype((Integer) SquareType.SQUARETYPE_IA.value());
      }
    }

    new UpdateSquare4CFieldsBP().updateFields(sqvos, new String[] {
      SquareOutHVO.BAUTOSQUARECOST
    }, new String[] {
      SquareOutBVO.FPREIATYPE
    });

  }

  /**
   * 结算单自动计入发出商品更新标志位
   * 
   * @param sqvos
   */
  public void updateSquareBFlagForAutoIARegister(SquareOutVO[] sqvos) {

    // 设置相应标志位
    for (SquareOutVO vo : sqvos) {
      vo.getParentVO().setBautosquarecost(UFBoolean.TRUE);
      for (SquareOutBVO bvo : vo.getChildrenVO()) {
        bvo.setNthisnum(bvo.getNnum());
        bvo.setFpreiatype((Integer) SquareType.SQUARETYPE_REG_DEBIT.value());
      }
    }

    new UpdateSquare4CFieldsBP().updateFields(sqvos, new String[] {
      SquareOutHVO.BAUTOSQUARECOST
    }, new String[] {
      SquareOutBVO.FPREIATYPE
    });

  }

  /**
   * 结算单手工收入结算更新标志位
   * 
   * @param sqvos
   */
  public void updateSquareBFlagForManualARIncome(SquareOutVO[] sqvos) {

    // 设置相应标志位
    for (SquareOutVO vo : sqvos) {
      vo.getParentVO().setBautosquareincome(UFBoolean.FALSE);
      for (SquareOutBVO bvo : vo.getChildrenVO()) {
        bvo.setFpreartype((Integer) SquareType.SQUARETYPE_AR.value());
      }
    }

    new UpdateSquare4CFieldsBP().updateFields(sqvos, new String[] {
      SquareOutHVO.BAUTOSQUAREINCOME
    }, new String[] {
      SquareOutBVO.FPREARTYPE
    });

  }

  /**
   * 结算单手工成本结算更新标志位
   * 
   * @param sqvos
   */
  public void updateSquareBFlagForManualIACost(SquareOutVO[] sqvos) {

    // 设置相应标志位
    for (SquareOutVO vo : sqvos) {
      vo.getParentVO().setBautosquarecost(UFBoolean.FALSE);
      for (SquareOutBVO bvo : vo.getChildrenVO()) {
        bvo.setFpreiatype((Integer) SquareType.SQUARETYPE_IA.value());
      }
    }

    new UpdateSquare4CFieldsBP().updateFields(sqvos, new String[] {
      SquareOutHVO.BAUTOSQUARECOST
    }, new String[] {
      SquareOutBVO.FPREIATYPE
    });

  }

}
