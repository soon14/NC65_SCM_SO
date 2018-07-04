package nc.pubimpl.so.m30arrange.rule;

import java.util.Map;

import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderViewVO;
import nc.vo.so.pub.util.SOSysParaInitUtil;

import nc.pubitf.so.m30arrange.Rewrite30ArrangePara;

import nc.bs.ml.NCLangResOnserver;

import nc.impl.pubapp.env.BSContext;
import nc.impl.pubapp.pattern.rule.IRule;

/**
 * 
 * @description
 * 销售订单安排回写销售订单执行前的规则类(before)：
 * @scene
 * <p>
 * <b>所有累计安排数量不能大于销售订单主数量
 * <p>
 * <b>设置数量后检查与原符号是否一致
 * @param
 * 无
 *
 * @since 6.0
 * @version 2010-12-13 下午02:41:06
 * @author 刘志伟
 */
public class RewriteCheckArrangeNumRule implements IRule<SaleOrderViewVO> {

  @SuppressWarnings("unchecked")
  @Override
  public void process(SaleOrderViewVO[] vos) {
    Map<String, Rewrite30ArrangePara> index =
        (Map<String, Rewrite30ArrangePara>) BSContext.getInstance().getSession(
            Rewrite30ArrangePara.class.getName());
    for (SaleOrderViewVO vo : vos) {
      SaleOrderHVO head = vo.getHead();
      SaleOrderBVO body = vo.getBody();
      Rewrite30ArrangePara para = index.get(body.getCsaleorderbid());

      /**
       * 累计安排委外订单主数量 : narrangescornum
       * 累计安排请购单主数量 : narrangepoappnum
       * 累计安排调拨订单主数量 : narrangetoornum
       * 累计安排调拨申请主数量 : narrangetoappnum
       * 累计安排生产订单主数量 : narrangemonum
       * 累计安排采购订单主数量 : narrangeponum
       * 累计生成计划订单主数量: ntotalplonum
       */
      // 累计总安排主数量 = 累计安排委外订单主数量
      UFDouble totalArrangeNum = body.getNarrangescornum();
      // 累计总安排主数量 += 累计安排请购单主数量
      totalArrangeNum =
          MathTool.add(totalArrangeNum, body.getNarrangepoappnum());
      // 累计总安排主数量 += 累计安排调拨订单主数量
      totalArrangeNum =
          MathTool.add(totalArrangeNum, body.getNarrangetoornum());
      // 累计总安排主数量 += 累计安排调拨申请主数量
      totalArrangeNum =
          MathTool.add(totalArrangeNum, body.getNarrangetoappnum());
      // 累计总安排主数量 += 累计安排生产订单主数量
      totalArrangeNum = MathTool.add(totalArrangeNum, body.getNarrangemonum());
      // 累计总安排主数量 += 累计安排采购订单主数量
      totalArrangeNum = MathTool.add(totalArrangeNum, body.getNarrangeponum());
      // 累计总安排主数量 += 累计生成计划订单主数量
      totalArrangeNum = MathTool.add(totalArrangeNum, body.getNtotalplonum());
      // 累计总安排主数量 += 累计安排生成进口合同主数量
      totalArrangeNum = MathTool.add(totalArrangeNum, body.getNarrangeitcnum());
      // 累计总安排主数量 += 单据上累计总安排主数量
      totalArrangeNum = MathTool.add(totalArrangeNum, para.getNnum());
      UFDouble rate = UFDouble.ZERO_DBL;

      rate =
          SOSysParaInitUtil.getSO13(head.getPk_org()) == null ? UFDouble.ZERO_DBL
              : SOSysParaInitUtil.getSO13(head.getPk_org());

      // 可安排数量
      rate = rate.div(new UFDouble(100));
      UFDouble canrate = MathTool.add(UFDouble.ONE_DBL, rate);
      UFDouble canarrangenum = body.getNnum().multiply(canrate);
      if (MathTool.absCompareTo(totalArrangeNum, canarrangenum) > 0) {
        String message =
            NCLangResOnserver.getInstance().getStrByID("4006011_0",
                "04006011-0366", null, new String[] {
                  head.getVbillcode(), body.getCrowno()
                })/*累计总安排主数量 > 单据主数量 : 销售订单({0})第{1}行*/;

        ExceptionUtils.wrappBusinessException(message);
      }
      if (MathTool.isDiffSign(totalArrangeNum, body.getNnum())) {
        String message =
            NCLangResOnserver.getInstance().getStrByID("4006011_0",
                "04006011-0367")/*累计安排主数量不能与主数量符号相反！*/;
        String location =
            NCLangResOnserver.getInstance().getStrByID("4006011_0",
                "04006011-0340", null, new String[] {
                  head.getVbillcode(), body.getCrowno()
                })/*销售订单{0}第{1}行*/;
        ExceptionUtils.wrappBusinessException(message, location);
      }
    }
  }

}
