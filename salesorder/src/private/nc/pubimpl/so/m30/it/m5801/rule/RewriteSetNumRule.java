package nc.pubimpl.so.m30.it.m5801.rule;

import java.util.Map;

import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderViewVO;

import nc.pubitf.so.m30.it.m5801.Rewrite5801Para;

import nc.bs.ml.NCLangResOnserver;

import nc.impl.pubapp.env.BSContext;
import nc.impl.pubapp.pattern.rule.IRule;

/**
 * @description
 * 进口合同回写销售订单累计安排进口合同数量
 * @scene
 * 进口合同回写销售订单累计安排进口合同数量时设置累计安排进口合同主数量
 * @param
 * 无
 * @since JCK 6.31
 * @version 2014-03-19 15:40:55
 * @author zhangyfr
 */
public class RewriteSetNumRule implements IRule<SaleOrderViewVO> {

  @Override
  public void process(SaleOrderViewVO[] vos) {

    @SuppressWarnings("unchecked")
    Map<String, Rewrite5801Para> mParas =
        (Map<String, Rewrite5801Para>) BSContext.getInstance().getSession(
            Rewrite5801Para.class.getName());

    for (SaleOrderViewVO vo : vos) {
      SaleOrderHVO head = vo.getHead();
      SaleOrderBVO body = vo.getBody();
      Rewrite5801Para para = mParas.get(body.getCsaleorderbid());

      // 原累计安排进口合同数量
      UFDouble narrangeitcnum = body.getNarrangeitcnum();
      // 现累计安排进口合同数量
      narrangeitcnum = MathTool.add(narrangeitcnum, para.getNchangenum());
      if (MathTool.isDiffSign(narrangeitcnum, body.getNnum())) {
        String message =
            NCLangResOnserver.getInstance().getStrByID("4006011_0",
                "04006011-0484")/*累计安排进口合同数量不能与主数量符号相反！*/;
        String location =
            NCLangResOnserver.getInstance().getStrByID("4006011_0",
                "04006011-0340", null, new String[] {
                  head.getVbillcode(), body.getCrowno()
                })/*销售订单{0}第{1}行*/;
        ExceptionUtils.wrappBusinessException(message, location);
      }
      body.setNarrangeitcnum(narrangeitcnum);
    }

  }

}
