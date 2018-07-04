package nc.pubimpl.so.m30.so.m4331.rule;

import java.util.Map;

import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.env.BSContext;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.pubitf.so.m30.so.m4331.Rewrite4331Para;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderViewVO;

/**
 * 
 * @description
 * 发货单回写销售订单执行前的规则类(before)：
 * @scene
 * <p><b>设置累计发货数量
 * <p><b>设置数量后检查与原符号是否一致
 * @param
 * 无
 *
 * @since 6.0
 * @version 2010-12-13 下午02:41:06
 * @author 刘志伟
 */
public class RewriteSetNumRule implements IRule<SaleOrderViewVO> {

  @SuppressWarnings("unchecked")
  @Override
  public void process(SaleOrderViewVO[] vos) {
    Map<String, Rewrite4331Para> mParas =
        (Map<String, Rewrite4331Para>) BSContext.getInstance().getSession(
            Rewrite4331Para.class.getName());

    for (SaleOrderViewVO vo : vos) {
      SaleOrderHVO head = vo.getHead();
      SaleOrderBVO body = vo.getBody();
      Rewrite4331Para para = mParas.get(body.getCsaleorderbid());

      // 原累计发货数量
      UFDouble ntotalsendnum = body.getNtotalsendnum();
      // 现累计发货数量
      ntotalsendnum = MathTool.add(ntotalsendnum, para.getNchangenum());
      if (MathTool.isDiffSign(ntotalsendnum, body.getNnum())) {
        String message = NCLangResOnserver.getInstance().getStrByID("4006011_0", "04006011-0388")/*累计发货数量不能与主数量符号相反！*/;
        String location =
            NCLangResOnserver.getInstance().getStrByID("4006011_0", "04006011-0340", null, new String[]{head.getVbillcode(),body.getCrowno()})/*销售订单{0}第{1}行*/;
        ExceptionUtils.wrappBusinessException(message, location);
      }
      body.setNtotalsendnum(ntotalsendnum);
    }
  }
}
