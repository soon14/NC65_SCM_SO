package nc.pubimpl.so.m30.so.m32.rule;

import java.util.Map;

import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.env.BSContext;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.pubitf.so.m30.so.m32.Rewrite32Para;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderViewVO;

/**
 * 
 * @description
 * 销售发票回写销售订单执行前的规则类(before)：
 * @scene
 * <p><b>设置累计开票数量
 * <p><b>设置数量后检查与原符号是否一致
 * @param
 * 无
 *
 * @since 6.0
 * @version 2010-01-28 下午13:49:07
 * @author 刘志伟
 */
public class RewriteSetNumRule implements IRule<SaleOrderViewVO> {

  @SuppressWarnings("unchecked")
  @Override
  public void process(SaleOrderViewVO[] vos) {
    Map<String, Rewrite32Para> mParas =
        (Map<String, Rewrite32Para>) BSContext.getInstance().getSession(
            Rewrite32Para.class.getName());

    for (SaleOrderViewVO vo : vos) {
      SaleOrderHVO head = vo.getHead();
      SaleOrderBVO body = vo.getBody();
      Rewrite32Para para = mParas.get(body.getCsaleorderbid());
      // 设置累计开票数量
      UFDouble ntotalinvoicenum = body.getNtotalinvoicenum();
      ntotalinvoicenum = MathTool.add(ntotalinvoicenum, para.getNchangenum());
      if (MathTool.isDiffSign(ntotalinvoicenum, body.getNnum())) {
        String message = NCLangResOnserver.getInstance().getStrByID("4006011_0", "04006011-0354")/*累计开票数量不能与主数量符号相反！*/;
        String location =
            NCLangResOnserver.getInstance().getStrByID("4006011_0", "04006011-0340", null, new String[]{head.getVbillcode(),body.getCrowno()})/*销售订单{0}第{1}行*/;
        ExceptionUtils.wrappBusinessException(message, location);
      }
      body.setNtotalinvoicenum(ntotalinvoicenum);
    }
  }
}
