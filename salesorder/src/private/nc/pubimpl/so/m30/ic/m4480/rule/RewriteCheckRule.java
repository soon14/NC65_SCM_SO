package nc.pubimpl.so.m30.ic.m4480.rule;

import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderViewVO;

/**
 * 
 * @description
 * 预留回写销售订单执行前的规则类(before)：
 * @scene
 * <p><b>检查是否红字订单
 * <p><b>检查是否数量 < 累计出库数量 + 预留数量(2011-10-14 冯加滨 王贺年 校验放给预留去校验)
 * @param
 * 无
 *
 * @version 6.0
 * @since 6.0
 * @author 刘志伟
 * @time 2010-9-20 上午11:24:21
 */
public class RewriteCheckRule implements IRule<SaleOrderViewVO> {

  // private Map<String, Rewrite4480Para> index;

  @Override
  public void process(SaleOrderViewVO[] vos) {
    // this.index =
    // (Map<String, Rewrite4480Para>) BSContext.getInstance().getSession(
    // Rewrite4480Para.class.getName());
    for (SaleOrderViewVO vo : vos) {
      this.checkRedBill(vo);
      // 2011-10-14 冯加滨 王贺年 校验放给预留去校验
      // this.checkReqrsNumRange(vo);
    }
  }

  private void checkRedBill(SaleOrderViewVO vo) {
    SaleOrderBVO body = vo.getBody();

    if (MathTool.compareTo(body.getNnum(), UFDouble.ZERO_DBL) < 0) {
      String message =
          NCLangResOnserver.getInstance().getStrByID("4006011_0",
              "04006011-0344")/*数量 < 0：红字订单不能被预留*/;

      ExceptionUtils.wrappBusinessException(message);
    }
  }

  // private void checkReqrsNumRange(SaleOrderViewVO vo) {
  // SaleOrderHVO head = vo.getHead();
  // SaleOrderBVO body = vo.getBody();
  // Rewrite4480Para para = this.index.get(body.getCsaleorderbid());
  //
  // UFDouble icNum =
  // MathTool.add(MathTool.add(body.getNreqrsnum(), para.getNreqrsnum()),
  // body.getNtotaloutnum());
  //
  // if (MathTool.compareTo(body.getNnum(), icNum) < 0) {
  // String message =
  // NCLangResOnserver.getInstance().getStrByID("4006011_0",
  // "04006011-0345")/*数量 < 累计出库数量 + 预留数量*/;
  // String location =
  // NCLangResOnserver.getInstance().getStrByID("4006011_0",
  // "04006011-0340", null, new String[] {
  // head.getVbillcode(), body.getCrowno()
  // })/*销售订单{0}第{1}行*/;
  //
  // ExceptionUtils.wrappBusinessException(location + message, location);
  // }
  // }
}
