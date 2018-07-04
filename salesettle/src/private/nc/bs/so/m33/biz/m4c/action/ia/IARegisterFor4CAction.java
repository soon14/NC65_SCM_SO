package nc.bs.so.m33.biz.m4c.action.ia;

import nc.bs.so.m33.biz.m4c.bp.square.ia.SquareIARegisterDebitFor4CBP;
import nc.vo.so.m33.m4c.entity.SquareOutVO;
import nc.vo.so.m33.m4c.entity.SquareOutVOUtils;

public class IARegisterFor4CAction {

  /**
   * 发出商品
   * 
   * @param vos
   */
  public void execCost(SquareOutVO[] vos) {
    // 检查是否可以传存货
    SquareOutVO[] sqvos = SquareOutVOUtils.getInstance().filterCostableVO(vos);
    if (sqvos == null) {
      return;
    }
    new SquareIARegisterDebitFor4CBP().square(sqvos);
  }

}
