package nc.bs.so.m33.biz.m4c.action.ia;

import nc.bs.so.m33.biz.m4c.bp.square.ia.SquareIACostFor4CBP;
import nc.vo.so.m33.m4c.entity.SquareOutVO;
import nc.vo.so.m33.m4c.entity.SquareOutVOUtils;

public class IACostFor4CAction {

  /**
   * 成本结算
   * 
   * @param vos
   */
  public void execCost(SquareOutVO[] vos) {
    // 检查是否可以传存货
    SquareOutVO[] sqvos = SquareOutVOUtils.getInstance().filterCostableVO(vos);
    if (sqvos == null) {
      return;
    }

    new SquareIACostFor4CBP().square(sqvos);
  }

}
