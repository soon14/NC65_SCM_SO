package nc.bs.so.m30.rule.maintainprocess;

import java.util.List;

import nc.impl.pubapp.env.BSContext;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.so.m30.sobalance.maintain.SobalanceBPFactoryForCashSale;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.sobalance.entity.SoBalanceHVO;
import nc.vo.so.m30.sobalance.entity.SoBalanceVO;

/**
 * @description
 * 销售订单修改保存后修改收款核销关系
 * @scene
 * 销售订单修改后存在收款核销关系时修改该收款核销关系
 * @param 
 * 无
 */
public class UpdateSoBalanceWhenUpdateRule implements IRule<SaleOrderVO> {

  @Override
  public void process(SaleOrderVO[] vos) {
    @SuppressWarnings("unchecked")
    List<SoBalanceVO> sobalancevos =
        (List<SoBalanceVO>) BSContext.getInstance().getSession(
            "cashsale.sobalancevos");
    if (sobalancevos == null || sobalancevos.size() == 0
        || sobalancevos.get(0) == null) {
      return;
    }
    if (sobalancevos.size() != vos.length) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0","04006011-0112")/*@res "参数非法，请检查前台传入的参数个数是否与订单的单据数一致。"*/);
    }
    // 如果订单表头费用冲抵或者订单收款核销金额不为空时，不允许再作现销处理:前台控制，能到这里的都是新增收款核销关系
    for (int i = 0; i < vos.length; i++) {
      SaleOrderVO saleordervo = vos[i];
      SoBalanceVO newbalancevo = sobalancevos.get(i);
      if (newbalancevo != null && newbalancevo.getChildrenVO() != null
          && newbalancevo.getChildrenVO().length > 0) {
        SaleOrderHVO soheadvo = saleordervo.getParentVO();
        SoBalanceHVO headvo = newbalancevo.getParentVO();
        headvo.setVbillcode(soheadvo.getVbillcode());
        headvo.setNtotalorigtaxmny(soheadvo.getNtotalorigmny());
        SobalanceBPFactoryForCashSale.getInstance().getInsertBP()
            .insert(new SoBalanceVO[] {
              newbalancevo
            });
      }

    }

  }

}