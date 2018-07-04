package nc.bs.so.m30.rule.maintaincheck;

import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;

/**
 * 
 * @description
 * 销售订单保存前校验
 * @scene
 * 校验销售订单表头赠品价税合计和表体赠品行价税合计是否不一致
 * @param
 * 无
 *
 * @since 6.1
 * @version 2013-12-24 10:30:07
 * @author liangjm
 */
public class CheckLrgTotalMoney implements IRule<SaleOrderVO> {

  @Override
  public void process(SaleOrderVO[] vos) {
    for (SaleOrderVO vo : vos) {
      this.HeadIsEqualsBodyLrg(vo);
    }
  }

  private void HeadIsEqualsBodyLrg(SaleOrderVO vo) {
    SaleOrderHVO hvo = vo.getParentVO();
    SaleOrderBVO[] bvos = vo.getChildrenVO();
    UFDouble hlrg = hvo.getNlrgtotalorigmny();
    UFDouble blrg = UFDouble.ZERO_DBL;
    for (SaleOrderBVO bvo : bvos) {
      if (bvo.getBlargessflag().booleanValue()
          && bvo.getStatus() != VOStatus.DELETED) {
        blrg = blrg.add(bvo.getNorigtaxmny());
      }
    }
    if (!hlrg.equals(blrg)) {
      ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance().getStrByID("4006011_0", "04006011-0494")/*销售订单表头赠品价税合计和表体赠品行价税合计不一致.*/);
    }
  }

}
