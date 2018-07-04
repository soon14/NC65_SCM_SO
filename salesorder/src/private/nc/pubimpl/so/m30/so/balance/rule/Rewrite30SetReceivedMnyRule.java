package nc.pubimpl.so.m30.so.balance.rule;

import java.util.Map;

import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m30.entity.SaleOrderHVO;

import nc.pubitf.so.m30.so.balance.RewriteBalancePara;

import nc.impl.pubapp.env.BSContext;
import nc.impl.pubapp.pattern.rule.IRule;

/**
 * 
 * @description
 * 收款核销回写销售订单实际收款金额
 * @scene
 * 收款核销回写销售订单实际收款金额
 * @param
 * 无
 *
 * @since 6.0
 * @version 2011-6-3 下午03:42:58
 * @author 刘志伟
 */
public class Rewrite30SetReceivedMnyRule implements IRule<SaleOrderHVO> {

  @SuppressWarnings("unchecked")
  @Override
  public void process(SaleOrderHVO[] heads) {
    Map<String, RewriteBalancePara> mParas =
        (Map<String, RewriteBalancePara>) BSContext.getInstance().getSession(
            RewriteBalancePara.class.getName());

    for (SaleOrderHVO head : heads) {
      RewriteBalancePara para = mParas.get(head.getCsaleorderid());
      // 实际收款金额
      UFDouble receivedMny = para.getNmny();
      head.setNreceivedmny(receivedMny);
      // 实际预收款金额
      UFDouble preceivemny = para.getNpremny();
      head.setNpreceivemny(preceivemny);
      UFBoolean preceiveflag = head.getBpreceiveflag();
      if (preceiveflag.booleanValue()
          && MathTool.compareTo(head.getNpreceivequota(), preceivemny) < 0) {
        String message =
            nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0",
                "04006011-0351")/*@res "实际预收款金额不能大于订单收款限额!"*/;
        ExceptionUtils.wrappBusinessException(message);
      }
    }
  }
}
