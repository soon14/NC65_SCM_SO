/**
 * $文件说明$
 * 
 * @author gdsjw
 * @version
 * @see
 * @since
 * @time 2010-6-10 下午08:03:03
 */
package nc.bs.so.m30.rule.sobalance;

import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.pubitf.so.m30.so.balance.IRewrite30ForBalance;
import nc.pubitf.so.m30.so.balance.RewriteBalancePara;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m30.sobalance.entity.SoBalanceBVO;
import nc.vo.so.m30.sobalance.entity.SoBalanceVO;

/**
 * @description
 * 保存收款核销关系后：同步回写销售订单表头实际收款金额,实际预收款金额
 * @scene
 * 销售订单保存收款核销关系后
 * @param 
 * 无
 * @since 6.0
 * @version 2011-5-28 下午03:24:26
 * @author 刘志伟
 */
public class SynSaleorderRule implements IRule<SoBalanceVO> {

  /**
   * 订单核销与销售订单是一单对一单，只要把订单已收款金额和预收款金额对照到销售订单上即可
   */
  @Override
  public void process(SoBalanceVO[] vos) {
    int length = vos.length;
    RewriteBalancePara[] paras = new RewriteBalancePara[length];
    for (int i = 0; i < length; i++) {
      String id = vos[i].getParentVO().getCsaleorderid();
      UFDouble totalpaymny = vos[i].getParentVO().getNtotalpaymny();
      UFDouble totalpremny = this.getTotalPreMny(vos[i]);
      paras[i] = new RewriteBalancePara(id, totalpaymny, totalpremny);
    }
    IRewrite30ForBalance api =
        NCLocator.getInstance().lookup(IRewrite30ForBalance.class);
    try {
      api.rewrite30ReceivedMnyForBalance(paras);
    }
    catch (BusinessException ex) {
      ExceptionUtils.wrappException(ex);
    }
  }

  private UFDouble getTotalPreMny(SoBalanceVO soBalanceVO) {
    UFDouble ret = UFDouble.ZERO_DBL;
    SoBalanceBVO[] bodys = soBalanceVO.getChildrenVO();
    for (SoBalanceBVO body : bodys) {
      boolean preflag =
          body.getBpreceiveflag() == null ? false : body.getBpreceiveflag()
              .booleanValue();
      if (preflag) {
        ret = MathTool.add(ret, body.getNorigordbalmny());
      }
    }
    return ret;
  }

}
