package nc.pubimpl.so.m30.ic.m4c.rule;

import java.util.Map;

import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.env.BSContext;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.pubitf.so.m30.ic.m4c.Rewrite4CPara;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderViewVO;

/**
 * 
 * @description
 * 销售出库回写销售订单执行前的规则类(before)
 * @scene
 * <p><b>设置累计出库数量、累计应发为出库数量
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
    Map<String, Rewrite4CPara> mParas =
        (Map<String, Rewrite4CPara>) BSContext.getInstance().getSession(
            Rewrite4CPara.class.getName());

    for (SaleOrderViewVO vo : vos) {
      SaleOrderHVO head = vo.getHead();
      SaleOrderBVO body = vo.getBody();
      Rewrite4CPara para = mParas.get(body.getCsaleorderbid());

      // 设置累计实发数量
      UFDouble ntotaloutnum = body.getNtotaloutnum();
      ntotaloutnum = MathTool.add(ntotaloutnum, para.getNchangenum());
      if (MathTool.isDiffSign(ntotaloutnum, body.getNnum())) {
        String message = NCLangResOnserver.getInstance().getStrByID("4006011_0", "04006011-0368")/*累计出库数量不能与主数量符号相反！*/;
        String location =
            NCLangResOnserver.getInstance().getStrByID("4006011_0", "04006011-0340", null, new String[]{head.getVbillcode(),body.getCrowno()})/*销售订单{0}第{1}行*/;
        ExceptionUtils.wrappBusinessException(message, location);
      }
      body.setNtotaloutnum(ntotaloutnum);

      // 设置累计应发未出库数量
      UFDouble ntotalnotoutnum = body.getNtotalnotoutnum();
      ntotalnotoutnum =
          MathTool.add(ntotalnotoutnum, para.getNchangenotoutnum());
      // if (MathTool.isDiffSign(ntotalnotoutnum, body.getNnum())) {
      // String message = "累计应发未出库数量不能与主数量符号相反！";
      // String location =
      // "销售订单" + head.getVbillcode() + "第" + body.getCrowno() + "行";
      // ExceptionUtils.wrappBusinessException(message, location);
      // }
      body.setNtotalnotoutnum(ntotalnotoutnum);
    }
  }
}
