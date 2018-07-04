package nc.pubimpl.so.m30.it.m5801.rule;

import java.util.Map;

import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderViewVO;
import nc.vo.so.pub.util.SOSysParaInitUtil;

import nc.pubitf.so.m30.it.m5801.Rewrite5801Para;

import nc.bs.ml.NCLangResOnserver;

import nc.impl.pubapp.env.BSContext;

/**
 * 根据"补货/直运安排容差比率"检查可安排主数量
 * 
 * @since JCK 6.31
 * @version 2014-03-19 15:29:26
 * @author zhangyfr
 */
public class RewriteCheckArrangeNumRule {

  /**
   * 
   * @param vos
   */
  public void process(SaleOrderViewVO[] vos) {
    @SuppressWarnings("unchecked")
    Map<String, Rewrite5801Para> index =
        (Map<String, Rewrite5801Para>) BSContext.getInstance().getSession(
            Rewrite5801Para.class.getName());
    for (SaleOrderViewVO vo : vos) {
      SaleOrderHVO head = vo.getHead();
      SaleOrderBVO body = vo.getBody();
      Rewrite5801Para para = index.get(body.getCsaleorderbid());

      /**
       * 累计安排委外订单主数量 : narrangescornum
       * 累计安排请购单主数量 : narrangepoappnum
       * 累计安排调拨订单主数量 : narrangetoornum
       * 累计安排调拨申请主数量 : narrangetoappnum
       * 累计安排生产订单主数量 : narrangemonum
       * 累计安排采购订单主数量 : narrangeponum
       * 累计生成计划订单主数量: ntotalplonum
       * 累计生成进口合同主数量: narrangeitcnum
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
      // 累计总安排主数量 += 累计生成进口合同主数量
      totalArrangeNum = MathTool.add(totalArrangeNum, body.getNarrangeitcnum());
      // 累计总安排主数量 += 单据上累计总安排主数量
      totalArrangeNum = MathTool.add(totalArrangeNum, para.getNchangenum());
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
                "04006011-0485");/*生成的进口合同主数量大于销售订单可安排量，进口合同不能保存*/

        ExceptionUtils.wrappBusinessException(message);
      }
      // 设置是否货源安排完毕
      if (!(MathTool.absCompareTo(totalArrangeNum, body.getNnum()) < 0)) {
        body.setBarrangedflag(UFBoolean.TRUE);
        body.setCarrangepersonid(AppContext.getInstance().getPkUser());
        body.setTlastarrangetime(AppContext.getInstance().getServerTime());
      }
      else {
        body.setBarrangedflag(UFBoolean.FALSE);
        body.setCarrangepersonid("~");
        body.setTlastarrangetime(null);
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
